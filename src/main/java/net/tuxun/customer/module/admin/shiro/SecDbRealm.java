package net.tuxun.customer.module.admin.shiro;

import net.tuxun.core.util.StringUtil;
import net.tuxun.customer.module.admin.bean.User;
import net.tuxun.customer.module.admin.bean.UserInfo;
import net.tuxun.customer.module.admin.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 取得用户的角色及权限字符串
 * @author liuqiang
 *
 */
public class SecDbRealm extends AuthorizingRealm {
  private static Logger log=LoggerFactory.getLogger(SecDbRealm.class);
  @Autowired
  IUserService userNameService;
  

  // 获取认证信息
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
      throws AuthenticationException {
    UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
    token.setPassword(StringUtil.md5(String.valueOf(token.getPassword())).toCharArray());
    User userName = userNameService.getByUsername(token.getUsername());
    if (userName != null && userName.getStatus()!=null && userName.getStatus().trim().equals("1")) {
      return new SimpleAuthenticationInfo(new UserInfo(userName,token.isRememberMe()), userName.getUserPwd(), getName());
    } else {
      return null;
    }
  }

  // 获取授权信息
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    UserInfo userName = (UserInfo) principals.getPrimaryPrincipal();// 认证时加入的
    SimpleAuthorizationInfo auth = new SimpleAuthorizationInfo();
    if (userName != null) {
      
     /* Set<String> perms = new HashSet<String>();
      perms.add("*"); 
      auth.setStringPermissions(perms);*/
      
      Set<String> perms = userName.getPerms();
      
        if (!CollectionUtils.isEmpty(perms)) {
          auth.setStringPermissions(perms);
        }
      
    }
    return auth;
  }



}
