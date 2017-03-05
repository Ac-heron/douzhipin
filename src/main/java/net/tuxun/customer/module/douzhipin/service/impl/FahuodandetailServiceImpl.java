package net.tuxun.customer.module.douzhipin.service.impl;

import net.tuxun.core.base.dao.IBaseDao;
import net.tuxun.core.base.service.AbstractBaseService;
import net.tuxun.customer.module.douzhipin.bean.Fahuodandetail;
import net.tuxun.customer.module.douzhipin.dao.IFahuodandetailDao;
import net.tuxun.customer.module.douzhipin.service.IFahuodandetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * @author Huang
 *
 */
@Service
public class FahuodandetailServiceImpl extends AbstractBaseService implements IFahuodandetailService {
  @Autowired
  IFahuodandetailDao dao;

  @Override
  public IBaseDao getDao() {
    return dao;
  } 
  
  @Override
  @Transactional
  public void removeByPrimaryTableId(String primaryTableId) {
    dao.deleteByPrimaryTableId(primaryTableId);
  }

  @Override
  public List<Fahuodandetail> listByPrimaryTableId(String primaryTableId) {
    return dao.listByPrimaryTableId(primaryTableId);
  } 
  
}
