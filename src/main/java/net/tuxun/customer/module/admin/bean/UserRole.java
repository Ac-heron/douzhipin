package net.tuxun.customer.module.admin.bean;

/**
 * 数据库表[U_USER_ROLE]的数据模型
 */

@SuppressWarnings("serial")
public class UserRole implements java.io.Serializable {

  /** 默认构造函数 */
  public UserRole() {}

  public UserRole(String userId, String roleId) {
    this.userId = userId;
    this.roleId = roleId;
  }

  // 主键
  private java.lang.String id;
  // 用户表主键
  private java.lang.String userId;
  // 角色表主键
  private java.lang.String roleId;

  public java.lang.String getId() {
    return this.id;
  }

  public void setId(java.lang.String id) {
    this.id = id;
  }

  public java.lang.String getUserId() {
    return this.userId;
  }

  public void setUserId(java.lang.String userId) {
    this.userId = userId;
  }

  public java.lang.String getRoleId() {
    return this.roleId;
  }

  public void setRoleId(java.lang.String roleId) {
    this.roleId = roleId;
  }
}
