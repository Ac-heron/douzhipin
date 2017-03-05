package net.tuxun.customer.module.admin.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.tuxun.customer.module.admin.bean.User;
import net.tuxun.customer.module.admin.service.IUserService;
import net.tuxun.customer.module.admin.shiro.exception.CaptchaException;
import net.tuxun.customer.module.admin.shiro.exception.UserLoginExcetion;
import net.tuxun.customer.module.admin.shiro.util.ContextUtil;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.CaptchaServiceException;

/**
 * 用户登录表单验证
 * <br>1.验证码验证
 * <br>2.登录认证
 * @author liuqiang
 *
 */
public class SecAuthenticationFilter extends FormAuthenticationFilter {
  
  // 验证码名称
  public static final String CAPTCHA_PARAM = "captcha";
  // 是否需要验证码
  public static final String CAPTCHA_REQUIRED_KEY = "shiroCaptchaRequired";
  // 登录验证错误次数
  public static final String CAPTCHA_ERROR_COUNT_KEY = "shiroCaptchaErrorCount";
  
  // 登录验证
  @Override
  protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
    AuthenticationToken token = createToken(request, response);
    if (token == null) {
        String msg = "createToken 方法实现不能返回null";
        throw new IllegalStateException(msg);
    }
    HttpServletRequest hsr = (HttpServletRequest) request;
    HttpServletResponse hsp = (HttpServletResponse) response;
    // 验证码认证
    if (isCaptchaSessionRequired(hsr, hsp)) {
      String captcha = request.getParameter(CAPTCHA_PARAM);// 取得验证码的值
      if (captcha != null) {
        if (!isValid(captchaService, hsr, captcha)) {
          return onLoginFailure(token, new CaptchaException("验证码异常"), request, response);
        }
      } else {
        return onLoginFailure(token, new UserLoginExcetion("账户异常"), request, response);
      }
    }
    // 登录认证
    String username = (String) token.getPrincipal();
    try {
      User user = null;
      if(username != null){   
        user = userNameService.getByUsername(username);
      }
      Subject subject = getSubject(request, response);
      subject.login(token);
      ContextUtil.put(user.getId(),hsr);
      return onLoginSuccess(token, subject, request, response);
    } catch (AuthenticationException e) {
      return onLoginFailure(token, e, request, response);
    }
  }

  @Override
  public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
      throws Exception {
    boolean isAllowed = isAccessAllowed(request, response, mappedValue);
    if (isAllowed && isLoginRequest(request, response)) {
      try {
        issueSuccessRedirect(request, response);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return false;
    }
    return isAllowed || onAccessDenied(request, response, mappedValue);
  }
  
  // 登录成功后
  @Override
  protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
      ServletRequest request, ServletResponse response) throws Exception {
    HttpServletRequest hsr = (HttpServletRequest) request;
    HttpSession session = hsr.getSession(false);
    if (session != null) {
        session.removeAttribute(CAPTCHA_REQUIRED_KEY);
        session.removeAttribute(CAPTCHA_ERROR_COUNT_KEY);
        session.removeAttribute(WebUtils.SAVED_REQUEST_KEY);
    }
    return super.onLoginSuccess(token, subject, request, response);
  }

  // 登录失败3次要求输入验证码
  @Override
  protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e,
      ServletRequest request, ServletResponse response) {
    HttpServletRequest hsr = (HttpServletRequest) request;
    HttpSession session = hsr.getSession();
    Integer errorCount = (Integer) session
            .getAttribute(CAPTCHA_ERROR_COUNT_KEY);
    if (errorCount != null) {
        errorCount++;
    } else {
        errorCount = 1;
    }
    session.setAttribute(CAPTCHA_ERROR_COUNT_KEY, errorCount);
    if (errorCount > 3) {
      // 加入需要验证码的session
      session.setAttribute(CAPTCHA_REQUIRED_KEY, true);
    }
    return super.onLoginFailure(token, e, request, response);
  }

  // 验证码是否正确
  public boolean isValid(CaptchaService service, HttpServletRequest request, String captcha) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return false;
    }
    try {
      return service.validateResponseForID(session.getId(), captcha);
    } catch (CaptchaServiceException e) {
      return false;
    }
  }
 
  // 是否需要验证
  protected boolean isCaptchaSessionRequired(HttpServletRequest request,
      HttpServletResponse response) {
  HttpSession session = request.getSession(false);
  if (session != null) {
      return session.getAttribute(CAPTCHA_REQUIRED_KEY) != null;
  }
  return false;
}
  
  @Autowired
  private CaptchaService captchaService;
  @Autowired
  private IUserService userNameService;
}
