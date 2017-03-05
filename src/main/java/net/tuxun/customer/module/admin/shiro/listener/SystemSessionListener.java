package net.tuxun.customer.module.admin.shiro.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import net.tuxun.customer.module.admin.shiro.util.ContextUtil;

/**
 * 监听session事件
 * @author liuqiang
 *
 */
public class SystemSessionListener implements HttpSessionListener {

  /**
   * session创建事件
   */
  public void sessionCreated(HttpSessionEvent event) {
  }
  
  /**
   * session销毁事件
   */
  public void sessionDestroyed(HttpSessionEvent event) {
   ContextUtil.destroy(event.getSession());
  }
}
