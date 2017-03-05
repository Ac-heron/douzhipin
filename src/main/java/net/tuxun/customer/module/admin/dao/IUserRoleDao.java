package net.tuxun.customer.module.admin.dao;

import net.tuxun.core.base.dao.IBaseDao;

public interface IUserRoleDao extends IBaseDao {

  void deleteByUserId(String userId);

}
