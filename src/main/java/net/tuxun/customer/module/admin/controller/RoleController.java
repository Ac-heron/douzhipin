package net.tuxun.customer.module.admin.controller;

import java.util.List;

import net.tuxun.component.config.bean.TreeNode;
import net.tuxun.component.config.service.IMenuService;
import net.tuxun.core.base.controller.BaseController;
import net.tuxun.core.mybatis.page.PageNav;
import net.tuxun.core.mybatis.page.PageQuery;
import net.tuxun.customer.module.admin.bean.UserInfo;
import net.tuxun.customer.module.admin.bean.Role;
import net.tuxun.customer.module.admin.service.IRoleService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/entadmin/role")
public class RoleController extends BaseController {

  // 角色列表
  @RequiresPermissions("admin:role:list")
  @RequestMapping("list.do")
  public String list(UserInfo user, PageQuery query, Model model) {
   // query.searchDefault("orgid", user.getCurrentRoleId());
    PageNav<Role> pageNav = service.pageResult(query);
    model.addAttribute("pageNav", pageNav);
    model.addAttribute("query", query);
    return "entadmin/role/list";
  }

  // 查看角色
  @RequiresPermissions("admin:role:view")
  @RequestMapping("view.do")
  public String view(String id, Model model) {
    Role bean = service.get(id);
    model.addAttribute("bean", bean);
    return "entadmin/role/view";
  }

  // 添加页面
  @RequiresPermissions("admin:role:toAdd")
  @RequestMapping(value = "add.do", method = RequestMethod.GET)
  public String toAdd(String id, Model model) {
    Role bean = new Role();
    bean.setIsAllPerms(Role.NO);
    bean.setIsRangePerms(Role.ORG_RANGE);
    if (id != null) {
      bean = service.get(id);
    }
    model.addAttribute("bean", bean);
    model.addAttribute(OPERATION, ADD);
    return "entadmin/role/form";
  }

  // 添加角色
  @RequiresPermissions("admin:role:add")
  @RequestMapping(value = "add.do", method = RequestMethod.POST)
  public String add(Role bean, String[] romts, String redirect, RedirectAttributes ra) {
    service.save(bean, romts);
    ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
    if (redirect.equals(ALTER)) {
      return "redirect:alter.do?id=" + bean.getId();
    } else if (redirect.equals(ADD)) {
      return "redirect:add.do";
    } else {
      return "redirect:list.do";
    }
  }

  // 角色修改页面
  @RequiresPermissions("admin:role:toAlter")
  @RequestMapping(value = "alter.do", method = RequestMethod.GET)
  public String toAlter(String id, Model model) {
    Role bean = service.getFullRole(id);
    model.addAttribute("bean", bean);
    model.addAttribute(OPERATION, ALTER);
    return "entadmin/role/form";
  }

  // 修改角色
  @RequestMapping(value = "alter.do", method = RequestMethod.POST)
  @RequiresPermissions("admin:role:alter")
  public String alter(Role bean, String[] romts, String redirect, RedirectAttributes ra) {
    service.modify(bean, romts);
    ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
    if (redirect.equals(ALTER)) {
      return "redirect:alter.do?id=" + bean.getId();
    } else if (redirect.equals(ADD)) {
      return "redirect:add.do";
    } else {
      return "redirect:list.do";
    }
  }

  // 删除角色
  @RequiresPermissions("admin:role:remove")
  @RequestMapping(value = "remove.do")
  public String remove(String id, RedirectAttributes ra) {
    service.remove(id);
    ra.addFlashAttribute(MESSAGE, REMOVE_SUCCESS);
    return "redirect:list.do";
  }

  // 取得权限
  @RequestMapping(value = "perms.do")
  public @ResponseBody
  List<TreeNode> perms() {
    return menuService.getTreeNodes();
  }

  // 根据部门的ID取得对应的角色
  @RequestMapping("tree.do")
  public @ResponseBody
  List<TreeNode> tree(String dpmId) {
    return service.getRoleTreeNodes(dpmId);
  }

  @Autowired
  IRoleService service;
  @Autowired
  IMenuService menuService;
}
