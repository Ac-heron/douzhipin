package net.tuxun.customer.module.admin.bean;

/**
 * 数据库表[U_ROLE]的数据模型
 */

@SuppressWarnings("serial")
public class Role implements java.io.Serializable {

  /** 默认构造函数 */
  public Role() {}

  // 主键
  private java.lang.String id;
  // 角色名称
  private java.lang.String name;
  // 角色功能权限
  private java.lang.String perms;
  // 是否拥有所有的功能权限
  private java.lang.Integer isAllPerms;
  // 角色的简单描述
  private java.lang.String description;
  // 自身范围还是机构范围
  private java.lang.Integer isRangePerms;

  //private Org org; // 所属机构
  //private Department department; // 所属部门

  // 静态类型整理
  public static final int YES = 1;
  public static final int NO = 0;

  public static final int OWN_RANGE = 0;
  public static final int ORG_RANGE = 1;

  /**
   * 是否拥有所有权限
   * 
   * @return true,false
   */
  public boolean isHaveAllPerms() {
    return getIsAllPerms() == YES;
  }

  public java.lang.String getId() {
    return this.id;
  }

  public void setId(java.lang.String id) {
    this.id = id;
  }

  public java.lang.String getName() {
    return this.name;
  }

  public void setName(java.lang.String name) {
    this.name = name;
  }

  public java.lang.String getPerms() {
    return this.perms;
  }

  public void setPerms(java.lang.String perms) {
    this.perms = perms;
  }

  public java.lang.Integer getIsAllPerms() {
    return this.isAllPerms;
  }

  public void setIsAllPerms(java.lang.Integer isAllPerms) {
    this.isAllPerms = isAllPerms;
  }

  public java.lang.String getDescription() {
    return this.description;
  }

  public void setDescription(java.lang.String description) {
    this.description = description;
  }

  public java.lang.Integer getIsRangePerms() {
    return this.isRangePerms;
  }

  public void setIsRangePerms(java.lang.Integer isRangePerms) {
    this.isRangePerms = isRangePerms;
  }

 /* public Org getOrg() {
    return org;
  }

  public void setOrg(Org org) {
    this.org = org;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }*/
}
