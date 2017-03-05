package net.tuxun.customer.module.admin.controller;

import net.tuxun.component.config.bean.TreeNode;
import net.tuxun.core.base.controller.BaseController;
import net.tuxun.core.mybatis.page.PageNav;
import net.tuxun.core.mybatis.page.PageQuery;
import net.tuxun.core.util.StringUtil;
import net.tuxun.customer.module.admin.bean.Role;
import net.tuxun.customer.module.admin.bean.User;
import net.tuxun.customer.module.admin.bean.UserInfo;
import net.tuxun.customer.module.admin.service.IRoleService;
import net.tuxun.customer.module.admin.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Acheron
 * iacheron.com
 *
 */
@Controller
@RequestMapping("/entadmin/user")
public class UserController extends BaseController {  

  @Autowired
  IUserService service;
  @Autowired
  IRoleService roleService;
  
  // 修改个人资料
  @RequiresPermissions("admin:user:toAlterOwn")
  @RequestMapping(value = "own.do", method = RequestMethod.GET)
  public String toOwn(UserInfo userInfo, Model model) {
	User bean = service.get(userInfo.getId());
    model.addAttribute("bean", bean);
    return "entadmin/user/own";
  }
  
  @RequiresPermissions("admin:user:alterOwn")
  @RequestMapping(value = "own.do", method = RequestMethod.POST)
  public String own(UserInfo userInfo,User bean, String redirect, RedirectAttributes ra) {
    service.modifyNotNull(bean);
    if(redirect.equals("login")){
      SecurityUtils.getSubject().logout();
      ra.addFlashAttribute(MESSAGE, "资料修改成功,请重新登录");
      return "redirect:/back/login.do";
    }else{
      ra.addFlashAttribute(MESSAGE, "资料修改成功");
      return "redirect:own.do";
    }
  }
  
  // 修改密码
  @RequiresPermissions("admin:user:pwd")
  @RequestMapping(value = "pwd.do", method = RequestMethod.GET)
  public String toPwd(UserInfo userInfo, Model model) {
    model.addAttribute("bean", userInfo);
    return "entadmin/user/pwd";
  }
  
  @RequiresPermissions("admin:user:pwd")
  @RequestMapping(value = "pwd.do", method = RequestMethod.POST)
  public String pwd(UserInfo userInfo, String oldPwd, String newPwd, RedirectAttributes ra) {
    if(oldPwd == null || oldPwd.trim().equals("")){
      ra.addFlashAttribute(MESSAGE, "请输入旧密码");
      return "redirect:pwd.do";
    }
    if(newPwd == null || newPwd.trim().equals("")){
      ra.addFlashAttribute(MESSAGE, "请输入新密码");
      return "redirect:pwd.do";
    }
    if(!StringUtil.md5(oldPwd).equals(userInfo.getUserPwd())){
      ra.addFlashAttribute(MESSAGE, "旧密码输入不正确");
      return "redirect:pwd.do";
    }
    // 更新密码
    User ee = new User();
    ee.setId(userInfo.getId());
    ee.setUserPwd(StringUtil.md5(newPwd));
    service.modifyNotNull(ee);
    // 退出登录
    SecurityUtils.getSubject().logout();
    ra.addFlashAttribute(MESSAGE, "修改密码成功,请重新登录");
    return "redirect:/back/login.do";
  }
  
  // 用户成员信息列表
  @RequiresPermissions("user:userBasic:list")
  @RequestMapping("list.do")
  public String list(PageQuery query,Model model) {
   // String areaCode = query.getSearch().get("areaCode");
    //query.getSearch().put("areaCode", userNameUtil.trimAreaCode(areaCode));
    PageNav<User> pageNav = service.pageResult(query);
    //query.getSearch().put("areaCode", areaCode);
    model.addAttribute("pageNav", pageNav);
    model.addAttribute("query", query);
    return "user/user/list";
  }
  
  // 查看用户成员基本信息
  @RequiresPermissions("user:userBasic:view")
  @RequestMapping("view.do")
  public String view(String id, Model model) {
    User bean = service.get(id);
    model.addAttribute("bean", bean);
    return "user/user/view";
  }
  
  // 添加页面
  @RequiresPermissions("user:userBasic:toAdd")
  @RequestMapping(value = "add.do", method = RequestMethod.GET)
  public String toAdd(String id, Model model) {
    User bean = new User();
    if(id != null){
      bean = service.get(id);
    }
    model.addAttribute("bean", bean);
    
    //角色
    PageQuery query = new PageQuery();
    query.orderDefault("name", "desc");
    PageNav<Role> pageNavRole = roleService.pageResult(query);
    model.addAttribute("roles", pageNavRole);
    
    model.addAttribute(OPERATION, ADD);
    return "user/user/form";
  }

  // 添加用户成员基本信息
  @RequiresPermissions("user:userBasic:add")
  @RequestMapping(value = "add.do", method = RequestMethod.POST)
  public String add(User bean, String redirect, RedirectAttributes ra) {
    service.addBean(bean);
    ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
    if(redirect.equals(ALTER)){
      return "redirect:alter.do?id=" + bean.getId();
    }else if(redirect.equals(ADD)){
      return "redirect:add.do";
    }else{      
      return "redirect:list.do";
    }
  }
  
  // 用户成员基本信息修改页面
  @RequiresPermissions("user:userBasic:toAlter")
  @RequestMapping(value = "alter.do", method = RequestMethod.GET)
  public String toAlter(String id, Model model) {
    User bean = service.get(id);
    model.addAttribute("bean", bean);
    
    //角色
    PageQuery query = new PageQuery();
    query.orderDefault("name", "desc");
    PageNav<Role> pageNavRole = roleService.pageResult(query);
    model.addAttribute("roles", pageNavRole);
    
    model.addAttribute(OPERATION, ALTER);
    return "user/user/form";
  }

  // 修改用户成员基本信息
  @RequestMapping(value = "alter.do", method = RequestMethod.POST)
  @RequiresPermissions("user:userBasic:alter")
  public String alter(User bean, String redirect, RedirectAttributes ra) {
    service.modifyBean(bean);
    ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
    if(redirect.equals(ALTER)){
      return "redirect:alter.do?id=" + bean.getId();
    }else if(redirect.equals(ADD)){
      return "redirect:add.do";
    }else{      
      return "redirect:list.do";
    }
  }
  
  // 删除用户成员基本信息
  @RequiresPermissions("user:userBasic:remove")
  @RequestMapping(value = "remove.do")
  public String remove(String id, RedirectAttributes ra) {
    service.remove(id);
    ra.addFlashAttribute(MESSAGE, REMOVE_SUCCESS);
    return "redirect:list.do";
  }
  
  //初始化密码
  @RequiresPermissions("user:userBasic:alter")
  @RequestMapping(value="initializationpwd.do")
  public String initializationPwd(String id,RedirectAttributes ra){
      service.initializationPwd(id);
      ra.addFlashAttribute(MESSAGE, "密码初始化成功");
      return "redirect:list.do";
  }
  /**
   *得到人员列表 
   * 
   */
  @RequestMapping(value="tree.do")
  public @ResponseBody List<TreeNode> tree(String node){
      return service.getTreeNode(node);
  }
  
 //用户成员编码信息唯一性验证
  @RequestMapping(value="unique.do")
  public @ResponseBody Map<String,Boolean> unique(String fieldname,String value,String id){
    User bean = service.getByUsername(value);
    Map<String, Boolean> map = new HashMap<String, Boolean>();
    if(id != null && !id.equals("")){
      map.put("success", bean == null || bean.getId().equals(id));
    }else{
      map.put("success", bean == null) ;
    }
    return map;
  }
  
}
