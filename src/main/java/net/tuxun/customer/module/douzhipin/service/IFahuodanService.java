package net.tuxun.customer.module.douzhipin.service;

import net.tuxun.core.base.service.IbaseService;
import net.tuxun.customer.module.douzhipin.bean.Fahuodan;

import java.util.List;
import java.util.Map;

/**
 * @author Huang
 */
public interface IFahuodanService extends IbaseService {

    
    /**
     * 保存
     *
     * @param bean
     */
    void saveFull(Fahuodan bean);

    /**
     * 查询详细
     *
     * @param id
     * @return
     */
    Fahuodan getFull(String id);

    /**
     * 修改
     *
     * @param bean
     */
    void modifyFull(Fahuodan bean);

    /**
     * 删除
     *
     * @param id
     */
    void removeFull(String id);

    /**
     * 根据客户编码查找
     *
     * @param ccode
     * @return
     */
    Fahuodan findByKhbm(String ccode);
	/**
	 * @param contractMap
	 * @return  无分页查询客户状态正常的合同
	 */
	List<Fahuodan> listAll(Map<String, Object> contractMap);


}
