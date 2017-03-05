package net.tuxun.customer.module.admin.shiro.exception;

import org.apache.shiro.authc.AccountException;

/**
 * 用户登录异常
 * @author liuqiang
 *
 */
@SuppressWarnings("serial")
public class UserLoginExcetion extends AccountException{
  /**
   * Creates a new UserLoginExcetion.
   */
  public UserLoginExcetion() {
      super();
  }

  /**
   * Constructs a new UserLoginExcetion.
   *
   * @param message the reason for the exception
   */
  public UserLoginExcetion(String message) {
      super(message);
  }

  /**
   * Constructs a new UserLoginExcetion.
   *
   * @param cause the underlying Throwable that caused this exception to be thrown.
   */
  public UserLoginExcetion(Throwable cause) {
      super(cause);
  }

  /**
   * Constructs a new UserLoginExcetion.
   *
   * @param message the reason for the exception
   * @param cause   the underlying Throwable that caused this exception to be thrown.
   */
  public UserLoginExcetion(String message, Throwable cause) {
      super(message, cause);
  }
}
