package net.tuxun.customer.module.admin.service;

import net.tuxun.core.base.service.IbaseService;

public interface IUserRoleService extends IbaseService {

  void removeByUserId(String userId);

}
