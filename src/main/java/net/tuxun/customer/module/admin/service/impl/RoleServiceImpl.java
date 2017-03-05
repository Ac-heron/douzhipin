package net.tuxun.customer.module.admin.service.impl;

import java.util.List;

import net.tuxun.component.config.bean.TreeNode;
import net.tuxun.core.base.dao.IBaseDao;
import net.tuxun.core.base.service.AbstractBaseService;
import net.tuxun.customer.module.admin.bean.Role;
import net.tuxun.customer.module.admin.dao.IRoleDao;
import net.tuxun.customer.module.admin.service.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl extends AbstractBaseService implements IRoleService {

  @Autowired
  IRoleDao dao;
 // @Autowired
  //IRoleOrgDepartmentService roleOrgDepartmentService;

  @Override
  public IBaseDao getDao() {
    return dao;
  }

  @Override
  public List<TreeNode> getRoleTreeNodes(String dpmId) {
    return dao.listRoleTreeNodesByDpmId(dpmId);
  }

  @Override
  @Transactional
  public void save(Role bean, String[] romts) {
    super.save(bean);
    if (romts != null && romts.length > 0) {/*
      try {
        for (String romt : romts) {
          roleOrgDepartmentService.save(new RoleOrgDepartment(bean.getId(), romt.split("#")[0],
              romt.split("#")[1]));
        }
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("角色所属机构部门参数错误");
      }
    */}
  }

  @Override
  @Transactional
  public void modify(Role bean, String[] romts) {
    super.modify(bean);
   /* roleOrgDepartmentService.removeByRoleId(bean.getId());
    if (romts != null && romts.length > 0) {
      try {
        for (String romt : romts) {
          roleOrgDepartmentService.save(new RoleOrgDepartment(bean.getId(), romt.split("#")[0],
              romt.split("#")[1]));
        }
      } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("角色所属机构部门参数错误");
      }
    }*/
  }

  @Override
  @Transactional
  public <T> void remove(String id) {
    super.remove(id);
    if (id != null) {/*
      for (String roleId : id.split(",")) {
        roleOrgDepartmentService.removeByRoleId(roleId);
      }
    */}
  }

  @Override
  public Role getFullRole(String id) {
    return dao.selectFullRole(id);
  }

}
