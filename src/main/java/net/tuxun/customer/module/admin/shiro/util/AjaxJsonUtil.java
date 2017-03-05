package net.tuxun.customer.module.admin.shiro.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class AjaxJsonUtil {

  // 被踢出
  public static final int STATUS_9 = 10;
  // 登录超时
  public static final int STATUS_10 = 10;
  // 登出成功
  public static final int STATUS_11 = 11;
  // 用户名或密码错误
  public static final int STATUS_12 = 12;
  // 验证码错误
  public static final int STATUS_13 = 13;
  // 无开卡记录异常
  public static final int STATUS_14 = 14;
  // U盾已过期异常
  public static final int STATUS_15 = 15;
  // 系统错误
  public static final int STATUS_500 = 500;
  // 禁止访问
  public static final int STATUS_403 = 403;

  /**
   * 执行ajax
   * 
   * @param status
   * @param errMsg
   * @param request
   * @param response
   * @return
   */
  public static boolean ajax(int status, String errMsg, HttpServletRequest request,
      HttpServletResponse response) {
    return isAjax(request) && AjaxBaic(status, errMsg, request, response);
  }

  /**
   * 是否是ajax请求
   * 
   * @param request
   * @return
   */
  public static boolean isAjax(HttpServletRequest request) {
    return isLocalAjax(request) || isCrossDomainAjax(request);
  }

  /**
   * 判断请求是否是本地XHR
   * 
   * @param request
   * @return
   */
  public static boolean isLocalAjax(HttpServletRequest request) {
    return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
  }

  /**
   * XHR是否跨域
   * 
   * @param request
   * @return
   */
  public static boolean isCrossDomainAjax(HttpServletRequest request) {
    return getJsonCallBack(request) != null;
  }

  /**
   * 取得跨域的回调函数名称
   * 
   * @param request
   * @return
   */
  public static String getJsonCallBack(HttpServletRequest request) {
    return request.getParameter("jsonCallback");
  }

  /**
   * 将status与errMsg组合成json字符串返回页面
   * 
   * @param status
   * @param errMsg
   * @param request
   * @param response
   * @return
   */
  public static boolean AjaxBaic(int status, String errMsg, HttpServletRequest request,
      HttpServletResponse response) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("status", Integer.valueOf(status));
    map.put("errMsg", errMsg);
    return AjaxBean(map, request, response);
  }

  /**
   * 将一个对象转换成json字符串返回页面
   * 
   * @param bean 对象
   * @param request
   * @param response
   * @return
   */
  public static boolean AjaxBean(Object bean, HttpServletRequest request,
      HttpServletResponse response) {
    String jsonCallback = getJsonCallBack(request);
    try {
      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(bean);
      print(jsonCallback == null ? json : jsonCallback + "(" + json + ")", response);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * json字符串返回
   * 
   * @param jsonStr json字符串
   * @param response 响应
   */
  public static void print(String jsonStr, HttpServletResponse response) {
    PrintWriter out = null;
    try {
      response.setCharacterEncoding("UTF-8");
      out = response.getWriter();
      out.println(jsonStr);
      out.flush();
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (out != null) {
        out.flush();
        out.close();
      }
    }
  }

  public static void main(String arg[]) {
    Map<String, String> map = new HashMap<String, String>();
    map.put("status", "1");
    map.put("errMsg", "错了");
    ObjectMapper mapper = new ObjectMapper();
    String json;
    try {
      json = mapper.writeValueAsString(map);
      System.out.println(json);
    } catch (JsonGenerationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
