package net.tuxun.customer.module.douzhipin.service.impl;

import net.tuxun.core.base.dao.IBaseDao;
import net.tuxun.core.base.service.AbstractBaseService;
import net.tuxun.customer.module.douzhipin.bean.Fahuodan;
import net.tuxun.customer.module.douzhipin.bean.Fahuodandetail;
import net.tuxun.customer.module.douzhipin.dao.IFahuodanDao;
import net.tuxun.customer.module.douzhipin.service.IFahuodanService;
import net.tuxun.customer.module.douzhipin.service.IFahuodandetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author Huang
 *
 */
@Service
public class FahuodanServiceImpl extends AbstractBaseService implements IFahuodanService {
  @Autowired
  IFahuodanDao dao;
  @Autowired
  IFahuodandetailService bcContractDetailService;
  @Override
  public IBaseDao getDao() {
    return dao;
  } 
  
  @Override
  public Fahuodan getFull(String id) {
    return dao.selectFull(id);
  }


    @Override
  @Transactional
  public void saveFull(Fahuodan bean) {
    this.save(bean);
    
    List<Fahuodandetail> bcContractDetails = bean.getDetails();
    if(bcContractDetails != null && !bcContractDetails.isEmpty()){
      for (Fahuodandetail bcContractDetail : bcContractDetails) {
        bcContractDetail.setApplyId(bean.getId());
        bcContractDetailService.save(bcContractDetail);
      }
    }
  }

  @Override
  @Transactional
  public void modifyFull(Fahuodan bean) {
    this.modifyNotNull(bean);
    
    List<Fahuodandetail> bcContractDetails = bean.getDetails();
    if(bcContractDetails != null && !bcContractDetails.isEmpty()){
      for (Fahuodandetail bcContractDetail : bcContractDetails) {
        bcContractDetail.setApplyId(bean.getId());
        bcContractDetailService.saveOrModify(bcContractDetail);
      }
    }
  }

  @Override
  @Transactional
  public void removeFull(String id) {
    this.remove(id);
    for (String primaryTableId  : id.split(",")) {
      bcContractDetailService.removeByPrimaryTableId(primaryTableId);
    }
  }

@Override
public Fahuodan findByKhbm(String ccode) {
	return dao.findByCkhbm(ccode);
}

@Override
public List<Fahuodan> listAll(Map<String, Object> contractMap) {
	return dao.listAll(contractMap);
}
  
}
