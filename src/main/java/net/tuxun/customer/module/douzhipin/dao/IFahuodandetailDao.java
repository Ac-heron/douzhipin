package net.tuxun.customer.module.douzhipin.dao;

import net.tuxun.core.base.dao.IBaseDao;
import net.tuxun.customer.module.douzhipin.bean.Fahuodandetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Huang
 */
public interface IFahuodandetailDao extends IBaseDao {

    /**
     * 根据主表id删除
     *
     * @param primaryTableId
     */
    void deleteByPrimaryTableId(String primaryTableId);

    /**
     * 根据主表id查询
     *
     * @param primaryTableId
     * @return
     */
    List<Fahuodandetail> listByPrimaryTableId(@Param("id") String primaryTableId);
}
