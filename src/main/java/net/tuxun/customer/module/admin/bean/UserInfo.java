package net.tuxun.customer.module.admin.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * 企业用户登录的封装信息类
 * 
 * @author liuqiang
 *
 */
@SuppressWarnings("serial")
public class UserInfo extends User {
  
  // 记住我功能
  private boolean rememberMe;
  
//当前的角色
 private String currentRoleId;
 private String currentRoleName;

  public UserInfo(User uuser, boolean rememberMe) {
    super(uuser);
    this.rememberMe = rememberMe;
    this.currentRoleId = uuser.getCurrentRole().getId();
    this.currentRoleName = uuser.getCurrentRole().getName();
  }

  public boolean isRememberMe() {
    return rememberMe;
  }

  public void setRememberMe(boolean rememberMe) {
    this.rememberMe = rememberMe;
  }


  /**
   * 本函数输出将作为默认的<shiro:principal/>输出.
   */
  @Override
  public String toString() {
    return getUserName();
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, "username");
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj, "username");
  }
  
  public String getCurrentRoleId() {
    return currentRoleId;
  }

  public void setCurrentRoleId(String currentRoleId) {
    this.currentRoleId = currentRoleId;
  }
  public String getCurrentRoleName() {
    return currentRoleName;
  }

  public void setCurrentRoleName(String currentRoleName) {
    this.currentRoleName = currentRoleName;
  }
  
  public void setCurrentRole(Role currentRole) {
    super.currentRole = currentRole;
    this.currentRoleId = getCurrentRole().getId();
    this.currentRoleName = getCurrentRole().getName();
  }

 
}
