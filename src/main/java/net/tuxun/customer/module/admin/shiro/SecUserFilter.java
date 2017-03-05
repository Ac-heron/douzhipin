package net.tuxun.customer.module.admin.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tuxun.customer.module.admin.shiro.util.ContextUtil;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

public class SecUserFilter extends UserFilter {
  
  private static final String LOGIN_URL = "/back/login.do";

  @Override
  protected void redirectToLogin(ServletRequest req, ServletResponse resp)
          throws IOException {
      HttpServletRequest request = WebUtils.toHttp(req);
      HttpServletResponse response = (HttpServletResponse) resp;
      WebUtils.issueRedirect(request, response, LOGIN_URL, ContextUtil.getParam(request));
  }

  @Override
  protected boolean isAccessAllowed(ServletRequest req, ServletResponse resp,
      Object mappedValue) {
    HttpServletRequest request = WebUtils.toHttp(req);
    boolean bool = super.isAccessAllowed(req, resp, mappedValue);
    if(bool){
      Subject subject = getSubject(req, resp); 
      if(ContextUtil.kickOut(request)){
        subject.logout();
        bool = false;
      }
    }else{
      ContextUtil.timeOut(request);
    }
    return bool;
  }
  
  
}
