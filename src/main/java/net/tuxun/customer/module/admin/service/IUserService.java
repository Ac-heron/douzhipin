package net.tuxun.customer.module.admin.service;

import java.util.List;

import net.tuxun.component.config.bean.TreeNode;
import net.tuxun.core.base.service.IbaseService;
import net.tuxun.customer.module.admin.bean.User;

public interface IUserService extends IbaseService{

  User getByUsername(String username);
  
  /**
   * 添加企业基本信息
   * @param bean
   */
  public void addBean(User bean);
  /**
   * 修改企业基本信息
   * @param bean
   */
  public void modifyBean(User bean);
  /**
   * 初始化企业密码
   * @param id
   */
  public void initializationPwd(String id);
  /**
   *得到人员的下拉列表 
   * 
   */
  List<TreeNode> getTreeNode(String id);
  
}
