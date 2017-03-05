package net.tuxun.customer.module.admin.service;

import java.util.List;

import net.tuxun.component.config.bean.TreeNode;
import net.tuxun.core.base.service.IbaseService;
import net.tuxun.customer.module.admin.bean.Role;

public interface IRoleService extends IbaseService {

  List<TreeNode> getRoleTreeNodes(String dpmId);

  void save(Role bean, String[] romts);

  void modify(Role bean, String[] romts);

  Role getFullRole(String id);

}
