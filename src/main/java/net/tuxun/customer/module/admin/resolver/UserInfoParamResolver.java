package net.tuxun.customer.module.admin.resolver;

import net.tuxun.customer.module.admin.bean.UserInfo;
import net.tuxun.customer.module.admin.bean.UserInfo;

import org.apache.shiro.SecurityUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class UserInfoParamResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType() == UserInfo.class || parameter.getParameterType()==UserInfo.class;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    UserInfo userNameInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
    return userNameInfo;
  }

}
