package net.tuxun.customer.module.admin.service.impl;


import java.util.ArrayList;
import java.util.List;

import net.tuxun.component.config.bean.TreeNode;
import net.tuxun.core.base.dao.IBaseDao;
import net.tuxun.core.base.service.AbstractBaseService;
import net.tuxun.core.util.IDGenerator;
import net.tuxun.core.util.StringUtil;
import net.tuxun.customer.module.admin.bean.User;
import net.tuxun.customer.module.admin.bean.UserRole;
import net.tuxun.customer.module.admin.dao.IUserDao;
import net.tuxun.customer.module.admin.service.IUserService;
import net.tuxun.customer.module.admin.service.IUserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends AbstractBaseService implements IUserService {
  @Autowired
  IUserDao dao;

  @Override
  public IBaseDao getDao() {
    return dao;
  }

  @Override
  public User getByUsername(String userCode) {
    return dao.selectByUsername(userCode);
  }

  @Override
  @Transactional
  public void addBean(User bean) {
    bean.setId(IDGenerator.generateId());
    // TODO 企业code暂时手填
    //String userCode=etbean.getuserNameCode()+bean.getAreaCode().substring(0, 4)+serviceddcs.getCodeInc(bean.getAreaCode(), etbean.getuserNameCode(), 5);
    // String userCode=etbean.getuserNameCode()+bean.getAreaCode().substring(0, 4);
    //bean.setuserCode(userCode);
    bean.setUserPwd(StringUtil.md5("123456"));
    save(bean);
    
    //保存角色
    userRoleService.save(new UserRole(bean.getId(), bean.getCurrentRoleId()));
  }

  @Override
  @Transactional
  public void modifyBean(User bean) {
    modifyNotNull(bean);
    
    // 清除角色
    userRoleService.removeByUserId(bean.getId());
    // 保存修改后的角色
    userRoleService.save(new UserRole(bean.getId(), bean.getCurrentRoleId()));
  }

  @Override
  public void initializationPwd(String id) {
    User bean = get(id);
    if(bean!=null){
      bean.setUserPwd(StringUtil.md5("123456"));
    }
    modify(bean);
    
  } 
  
  @Override
  @Transactional
  public <T> void remove(String id) {
    super.remove(id);
    if (id != null) {
      for (String userId : id.split(",")) {
        userRoleService.removeByUserId(userId);
      }
    }
  }
  
  
  @Autowired
  IUserRoleService userRoleService;
  //@Autowired
  //IDocsetDocnumCodeService serviceddcs; 

@Override
public List<TreeNode> getTreeNode(String id) {
    List<TreeNode> n = new ArrayList<TreeNode>();
    List<TreeNode> nodes = dao.listTreeNodes();
    for (TreeNode treeNode : nodes) {
         treeNode.setState("open");
         n.add(treeNode);
    }
    return n;
}
}
