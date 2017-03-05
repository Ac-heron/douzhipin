package net.tuxun.customer.module.admin.shiro.exception;

import org.apache.shiro.authc.AccountException;

/**
 * 无开卡记录异常
 * @author liuqiang
 *
 */
@SuppressWarnings("serial")
public class OverdueDiskCodeExcetion extends AccountException{
  /**
   * Creates a new UserLoginExcetion.
   */
  public OverdueDiskCodeExcetion() {
      super();
  }

  /**
   * Constructs a new UserLoginExcetion.
   *
   * @param message the reason for the exception
   */
  public OverdueDiskCodeExcetion(String message) {
      super(message);
  }

  /**
   * Constructs a new UserLoginExcetion.
   *
   * @param cause the underlying Throwable that caused this exception to be thrown.
   */
  public OverdueDiskCodeExcetion(Throwable cause) {
      super(cause);
  }

  /**
   * Constructs a new UserLoginExcetion.
   *
   * @param message the reason for the exception
   * @param cause   the underlying Throwable that caused this exception to be thrown.
   */
  public OverdueDiskCodeExcetion(String message, Throwable cause) {
      super(message, cause);
  }
}
