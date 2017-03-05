package net.tuxun.customer.module.admin.bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * 数据库表[userName]的数据模型
 */

@SuppressWarnings("serial")
public class User implements java.io.Serializable {

    /** 默认构造函数 */
    public User() {
    }
    
    /** 默认构造函数 */
    public User(User user) {
      this.userTypeId = user.userTypeId;
      this.id = user.id;
      this.userCode = user.userCode;
      this.userName = user.userName;
      this.userPwd = user.userPwd;
      this.areaName = user.areaName;
      this.areaCode = user.areaCode;
      this.status = user.status;
      this.linkePhone = user.linkePhone;
      this.linkeName = user.linkeName;
      
      this.currentRoleId = user.currentRoleId;
      this.roles = user.roles;
      this.currentRole = user.currentRole;
    }
    
    /** 全参构造函数 */
    public User(java.lang.String userTypeId, java.lang.String id, java.lang.String userCode, java.lang.String userName, java.lang.String userPwd, java.lang.String areaName, java.lang.String areaCode, java.lang.String status, java.lang.String linkePhone, java.lang.String linkeName) {
      this.userTypeId = userTypeId;
      this.id = id;
      this.userCode = userCode;
      this.userName = userName;
      this.userPwd = userPwd;
      this.areaName = areaName;
      this.areaCode = areaCode;
      this.status = status;
      this.linkePhone = linkePhone;
      this.linkeName = linkeName;
    }
    // 主键ID 
    private java.lang.String id;
    // 用户类型ID
    private java.lang.String userTypeId;
    // 用户类型名称
    private java.lang.String userType;
    // 用户代码 
    private java.lang.String userCode;
    // 用户名称 
    private java.lang.String userName;
    // 用户登陆密码 
    private java.lang.String userPwd;
    // 所属地区名称 
    private java.lang.String areaName;
    // 区域代码 
    private java.lang.String areaCode;
    // 状态 0 注销 1有效 
    private java.lang.String status;
    // 联系电话 
    private java.lang.String linkePhone;
    // 联系人 
    private java.lang.String linkeName;
    
    // 当前角色的ID
    private String currentRoleId;
    // 拥有的角色
    private List<Role> roles;
    // 当前的角色
    public Role currentRole;
    
    
    /**
     * 取得角色的名称集合
     * 
     * @return
     */
    public Set<String> getRoleNames() {
      List<Role> roles = getRoles();
      if (roles == null) { // 没有分配角色
        return null;
      }
      Set<String> set = new HashSet<String>();
      for (Role role : roles) {
        set.add(role.getName());
      }
      return set;
    }

    /**
     * 取得权限
     * 
     * @return 所有权限集合
     */
    public Set<String> getPerms() {
      if (roles == null) { // 没有分配角色
        return null;
      }
      Set<String> set = new HashSet<String>();
      Role currentRole = this.getCurrentRole();
      if (currentRole != null) {
        if (currentRole.isHaveAllPerms()) {// 拥有所有权限
          set = new HashSet<String>();
          set.add("*");
          return set;
        } else {
          String perms = currentRole.getPerms();
          if (perms != null && StringUtils.isNotBlank(perms)) {
            for (String perm : StringUtils.split(perms, ',')) {
              set.add(perm);
            }
          }
        }
      }
      return set;
    }
    
    public List<Role> getRoles() {
      return roles;
    }

    public void setRoles(List<Role> roles) {
      this.roles = roles;
    }
    
    public Role getCurrentRole() {
      return currentRole;
    }

    public void setCurrentRole(Role currentRole) {
      this.currentRole = currentRole;
    }

    public String getCurrentRoleId() {
      return currentRoleId;
    }

    public void setCurrentRoleId(String currentRoleId) {
      this.currentRoleId = currentRoleId;
    }
     
    /*@Deprecated
    public  java.lang.String getuserTypeId() {
        return this.userTypeId;
    }
    @Deprecated
    public void setuserTypeId(java.lang.String userTypeId) {
        this.userTypeId = userTypeId;
    }*/
    public  java.lang.String getId() {
        return this.id;
    }
    public void setId(java.lang.String id) {
        this.id = id;
    }
   /* @Deprecated
    public  java.lang.String getuserCode() {
        return this.userCode;
    }
    @Deprecated
    public void setuserCode(java.lang.String userCode) {
        this.userCode = userCode;
    }*/
   /* @Deprecated
    public  java.lang.String getuserName() {
        return this.userName;
    }
    @Deprecated
    public void setuserName(java.lang.String userName) {
        this.userName = userName;
    }
    @Deprecated
    public  java.lang.String getuserPwd() {
        return this.userPwd;
    }
    @Deprecated
    public void setuserPwd(java.lang.String userPwd) {
        this.userPwd = userPwd;
    }*/
    public  java.lang.String getAreaName() {
        return this.areaName;
    }
    public java.lang.String getUserTypeId() {
      return userTypeId;
    }

    public java.lang.String getUserType() {
      return userType;
    }

    public java.lang.String getUserCode() {
      return userCode;
    }

    public java.lang.String getUserName() {
      return userName;
    }

    public java.lang.String getUserPwd() {
      return userPwd;
    }

    public void setUserTypeId(java.lang.String userTypeId) {
      this.userTypeId = userTypeId;
    }

    public void setUserType(java.lang.String userType) {
      this.userType = userType;
    }

    public void setUserCode(java.lang.String userCode) {
      this.userCode = userCode;
    }

    public void setUserName(java.lang.String userName) {
      this.userName = userName;
    }

    public void setUserPwd(java.lang.String userPwd) {
      this.userPwd = userPwd;
    }

    public void setAreaName(java.lang.String areaName) {
        this.areaName = areaName;
    }
    public  java.lang.String getAreaCode() {
        return this.areaCode;
    }
    public void setAreaCode(java.lang.String areaCode) {
        this.areaCode = areaCode;
    }
    public  java.lang.String getStatus() {
        return this.status;
    }
    public void setStatus(java.lang.String status) {
        this.status = status;
    }
    public  java.lang.String getLinkePhone() {
        return this.linkePhone;
    }
    public void setLinkePhone(java.lang.String linkePhone) {
        this.linkePhone = linkePhone;
    }
    public  java.lang.String getLinkeName() {
        return this.linkeName;
    }
    public void setLinkeName(java.lang.String linkeName) {
        this.linkeName = linkeName;
    }
    /*@Deprecated
    public java.lang.String getuserType() {
      return userType;
    }
    @Deprecated
    public void setuserType(java.lang.String userType) {
      this.userType = userType;
    }*/

   /* @Override
    public String getuserCode() {      
      return this.getuserCode();
    }

    @Override
    public String getUserName() {
      return this.getuserName();
    }*/
}


