package net.tuxun.customer.module.admin.controller;

import net.tuxun.component.config.bean.Menu;
import net.tuxun.component.config.service.IMenuService;
import net.tuxun.core.base.controller.BaseController;
import net.tuxun.customer.module.admin.bean.UserInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Properties;

/**
 * 管理员登录后的页面初始化
 * @author liuqiang
 *
 */

@Controller
@RequestMapping("/back")
public class BackController extends BaseController{
  
  // 主页
  @RequestMapping("index.do")
  public String head (UserInfo user,Model model){
    List<Menu> menus = menuService.getMenus();
    model.addAttribute("menus", menus);
    model.addAttribute("user",user);
    return "index";
  }
  
  // 菜单
  @RequestMapping("menu.do")
  public String nav(String key, Model model,UserInfo userName) {
    List<Menu> menus = menuService.getChildMenus(key);
    model.addAttribute("menus",menus);
  		return "menu";
  }
  
  // 系统信息
  @RequiresPermissions("back:system")
  @RequestMapping("system.do")
  public String main (Model model){
    Properties props = System.getProperties();
    Runtime runtime = Runtime.getRuntime();
    long freeMemory = runtime.freeMemory();
    long totalMemory = runtime.totalMemory();
    long maxMemory = runtime.maxMemory();
    long usedMemory = totalMemory - freeMemory;
    long useableMemory = maxMemory - totalMemory + freeMemory;
    int div = 1000;
    double freeMemoryMB = ((double) freeMemory) / div / div;
    double totalMemoryMB = ((double) totalMemory) / div / div;
    double usedMemoryMB = ((double) usedMemory) / div / div;
    double maxMemoryMB = ((double) maxMemory) / div / div;
    double useableMemoryMB = ((double) useableMemory) / div / div;
    model.addAttribute("props", props);
    model.addAttribute("maxMemoryMB", maxMemoryMB);
    model.addAttribute("usedMemoryMB", usedMemoryMB);
    model.addAttribute("useableMemoryMB", useableMemoryMB);
    model.addAttribute("totalMemoryMB", totalMemoryMB);
    model.addAttribute("freeMemoryMB", freeMemoryMB);
    return "system";
  }
  
  @Autowired
  IMenuService menuService;
}
