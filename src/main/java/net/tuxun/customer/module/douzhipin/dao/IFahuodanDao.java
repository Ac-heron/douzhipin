package net.tuxun.customer.module.douzhipin.dao;

import net.tuxun.core.base.dao.IBaseDao;
import net.tuxun.customer.module.douzhipin.bean.Fahuodan;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Huang
 */
public interface IFahuodanDao extends IBaseDao {
    /**
     * 根据id查询合同对象
     *
     * @param id
     * @return
     */
    Fahuodan selectFull(String id);

    Fahuodan findByCkhbm(String ccode);

	List<Fahuodan> listAll(@Param("search") Map<String, Object> contractMap);
    
    
    //汇总
}
