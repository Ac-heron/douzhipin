package net.tuxun.customer.module.admin.service.impl;

import net.tuxun.core.base.dao.IBaseDao;
import net.tuxun.core.base.service.AbstractBaseService;
import net.tuxun.customer.module.admin.dao.IUserRoleDao;
import net.tuxun.customer.module.admin.service.IUserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends AbstractBaseService implements IUserRoleService {



  @Override
  public IBaseDao getDao() {
    return dao;
  }

  @Autowired
  IUserRoleDao dao;

  @Override
  public void removeByUserId(String userId) {
    dao.deleteByUserId(userId);
  }
}
