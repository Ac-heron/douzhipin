package net.tuxun.customer.module.admin.shiro.exception;

import org.apache.shiro.authc.ConcurrentAccessException;

/**
 * 验证码错误异常
 * @author liuqiang
 *
 */
@SuppressWarnings("serial")
public class CaptchaException extends ConcurrentAccessException{
  /**
   * Creates a new CaptchaException.
   */
  public CaptchaException() {
      super();
  }

  /**
   * Constructs a new CaptchaException.
   *
   * @param message the reason for the exception
   */
  public CaptchaException(String message) {
      super(message);
  }

  /**
   * Constructs a new CaptchaException.
   *
   * @param cause the underlying Throwable that caused this exception to be thrown.
   */
  public CaptchaException(Throwable cause) {
      super(cause);
  }

  /**
   * Constructs a new CaptchaException.
   *
   * @param message the reason for the exception
   * @param cause   the underlying Throwable that caused this exception to be thrown.
   */
  public CaptchaException(String message, Throwable cause) {
      super(message, cause);
  }
}
