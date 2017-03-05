package net.tuxun.customer.module.douzhipin.service;

import net.tuxun.core.base.service.IbaseService;
import net.tuxun.customer.module.douzhipin.bean.Fahuodandetail;

import java.util.List;

/**
 * @author Huang
 */
public interface IFahuodandetailService extends IbaseService {
    /**
     * 根据主表id删除
     *
     * @param primaryTableId
     */
    void removeByPrimaryTableId(String primaryTableId);

    /**
     * 根据主表id查询
     *
     * @param primaryTableId
     * @return
     */
    List<Fahuodandetail> listByPrimaryTableId(String primaryTableId);

}
