package net.tuxun.customer.module.admin.shiro.cookie;

import org.apache.shiro.web.mgt.CookieRememberMeManager;

public class LightCookieRememberMeManager extends CookieRememberMeManager{
  public LightCookieRememberMeManager() {
    super();
    setSerializer(new SimplePrincipalSerializer());
} 
}
