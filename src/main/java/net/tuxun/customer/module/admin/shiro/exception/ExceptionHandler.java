package net.tuxun.customer.module.admin.shiro.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tuxun.core.security.GlobalException;
import net.tuxun.core.util.DateUtil;
import net.tuxun.customer.module.admin.shiro.util.AjaxJsonUtil;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class ExceptionHandler extends SimpleMappingExceptionResolver {

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    final Logger log = LoggerFactory.getLogger(handler.getClass());
    String errNumber = DateUtil.minLongTime(new Date());
    if (ex instanceof AuthorizationException) {
      if (AjaxJsonUtil.isAjax(request)
          && AjaxJsonUtil.AjaxBaic(AjaxJsonUtil.STATUS_403, "未授权", request, response)) {
        return null;
      } else {
        return super.resolveException(request, response, handler, new AuthorizationException(
            errNumber));
      }
    } else {
      log.error("**************************************************************");
      log.error("异常[" + errNumber + "] : ", ex);
      log.error("**************************************************************");
      if (AjaxJsonUtil.isAjax(request)
          && AjaxJsonUtil.AjaxBaic(AjaxJsonUtil.STATUS_500,
              "[" + errNumber + "]" + ex.getMessage(), request, response)) {
        return null;
      } else {
        return super.resolveException(request, response, handler, new GlobalException(errNumber));
      }
    }

  }

}
