package net.tuxun.customer.module.admin.shiro.exception;

import org.apache.shiro.authc.AccountException;

/**
 * 无开卡记录异常
 * @author liuqiang
 *
 */
@SuppressWarnings("serial")
public class NoDiskCodeExcetion extends AccountException{
  /**
   * Creates a new UserLoginExcetion.
   */
  public NoDiskCodeExcetion() {
      super();
  }

  /**
   * Constructs a new UserLoginExcetion.
   *
   * @param message the reason for the exception
   */
  public NoDiskCodeExcetion(String message) {
      super(message);
  }

  /**
   * Constructs a new UserLoginExcetion.
   *
   * @param cause the underlying Throwable that caused this exception to be thrown.
   */
  public NoDiskCodeExcetion(Throwable cause) {
      super(cause);
  }

  /**
   * Constructs a new UserLoginExcetion.
   *
   * @param message the reason for the exception
   * @param cause   the underlying Throwable that caused this exception to be thrown.
   */
  public NoDiskCodeExcetion(String message, Throwable cause) {
      super(message, cause);
  }
}
