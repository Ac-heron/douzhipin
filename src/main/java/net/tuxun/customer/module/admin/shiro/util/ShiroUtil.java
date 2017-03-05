package net.tuxun.customer.module.admin.shiro.util;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

/**
 * shiro工具类
 * @author liuqiang
 *
 */
public class ShiroUtil {
  
  public static void login(AuthorizingRealm realm, UsernamePasswordToken token){
    SecurityManager manager = SecurityUtils.getSecurityManager();
    
    if(manager instanceof DefaultWebSecurityManager){
      DefaultWebSecurityManager dwsManager = (DefaultWebSecurityManager)manager;
      
      // 定义自己的shiro的验证机制
      DefaultWebSecurityManager myManager = new DefaultWebSecurityManager();
      myManager.setRealm(realm);
      myManager.setCacheManager(dwsManager.getCacheManager());
      myManager.setRememberMeManager(dwsManager.getRememberMeManager());
      
      // 登录
      myManager.login(SecurityUtils.getSubject(), token);
    }
  }
  
}
