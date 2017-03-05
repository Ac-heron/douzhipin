package net.tuxun.customer.module.admin.dao;

import java.util.List;

import net.tuxun.component.config.bean.TreeNode;
import net.tuxun.core.base.dao.IBaseDao;
import net.tuxun.customer.module.admin.bean.User;

import org.apache.ibatis.annotations.Param;

public interface IUserDao extends IBaseDao {

  User selectByUsername(String userCode);
  
  void updateRoleId(@Param("userId") String id, @Param("roleId") String roleId);

    List<TreeNode> listTreeNodes();

}
