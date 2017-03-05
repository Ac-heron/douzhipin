package net.tuxun.customer.module.admin.dao;

import java.util.List;

import net.tuxun.component.config.bean.TreeNode;
import net.tuxun.core.base.dao.IBaseDao;
import net.tuxun.customer.module.admin.bean.Role;

public interface IRoleDao extends IBaseDao {

  List<TreeNode> listRoleTreeNodesByDpmId(String dpmId);

  Role selectFullRole(String id);

}
