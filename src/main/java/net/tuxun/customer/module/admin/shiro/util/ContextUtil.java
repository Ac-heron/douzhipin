package net.tuxun.customer.module.admin.shiro.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.tuxun.core.base.controller.BaseController;

public class ContextUtil {

  // 存储登录的用户信息
  private static final Map<String, HttpSession> USER_MAP = new HashMap<String, HttpSession>();
  private static final Map<String, String> SESSION_MAP = new HashMap<String, String>();
  
  private static final String KICK_MESSAGE = BaseController.MESSAGE;
  private static final String KICK_LOGIN = "kickOut";
  private static final String KICK_TIMEOUT = "timeOut";
  
  /**
   * 保存用户登录的session
   * @param userId
   * @param request
   */
  public static void put(String userId, HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    
    // 检测用户是否已经登录
    if(USER_MAP.containsKey(userId)){
      HttpSession hasLoginSession = USER_MAP.get(userId);
      // 如果已经登录标识用户在别处登录
      try{        
        hasLoginSession.setAttribute(KICK_MESSAGE, KICK_LOGIN);
      }catch(Exception e){
        USER_MAP.remove(userId);
      }
    }
    // 将新登录的用户session覆盖已登录用户的session
    USER_MAP.put(userId, session);
    SESSION_MAP.put(session.getId(), userId);
     
  }

 
  /**
   * 检测用户是否被踢出
   * @param request
   * @param httpServletResponse 
   * @return
   */
  public static boolean kickOut(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    
    // 检测是否被踢出
    Object obj = session.getAttribute(KICK_MESSAGE);
    if(obj != null){
      session.removeAttribute(KICK_MESSAGE);
      request.setAttribute(KICK_MESSAGE, KICK_LOGIN);
      return true;
    }else{
      return false;
    }
  }

  /**
   * session超时
   * @param request
   */
  public static void timeOut(HttpServletRequest request) {
    request.setAttribute(KICK_MESSAGE, KICK_TIMEOUT);
  }


  /**
   * 取得request中的参数信息
   * @param request
   * @return
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public static Map getParam(HttpServletRequest request) {
    Map map = null;
    Object message = request.getAttribute(KICK_MESSAGE);
    if(message != null){
      map = new HashMap();
      map.put(KICK_MESSAGE, message);
    }
    return map;
  }


  /**
   * 销毁ServletContext中存储的session
   * @param session
   */
  public static void destroy(HttpSession curSession) {
    String curSessionId = curSession.getId();
    if(SESSION_MAP.containsKey(curSessionId)){
      String userId = SESSION_MAP.get(curSessionId);
      if(userId != null && USER_MAP.containsKey(userId)){
        HttpSession hasSession = USER_MAP.get(userId);
        if(hasSession.getId().equals(curSessionId)){
          USER_MAP.remove(userId);
        }
      }
      SESSION_MAP.remove(curSessionId);
    }  
  }

}
