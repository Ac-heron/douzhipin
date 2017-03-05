package net.tuxun.customer.module.douzhipin.controller;

import net.tuxun.core.base.controller.BaseController;
import net.tuxun.customer.module.douzhipin.bean.Fahuodandetail;
import net.tuxun.customer.module.douzhipin.service.IFahuodandetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author Huang
 *
 */
@Controller
@RequestMapping("/contract/bcContractDetail")
public class FahuodandetailController extends BaseController {
 
  // 包库合同子表列表
  @RequestMapping("list.do")
  @ResponseBody
  public List<Fahuodandetail> list(String primaryTableId) {
    return service.listByPrimaryTableId(primaryTableId);
  }
  
  // 查看包库合同子表
  @RequestMapping("view.do")
  @ResponseBody
  public Fahuodandetail view(String id) {
    return service.get(id);
  }
  
  // 添加包库合同子表
  @RequestMapping(value = "add.do")
  @ResponseBody
  public Map<String, Boolean> add(Fahuodandetail bean) {
    service.save(bean);
    return status(true);
  }


  // 修改包库合同子表
  @RequestMapping(value = "alter.do")
  @ResponseBody
  public Map<String, Boolean> alter(Fahuodandetail bean) {
    service.modifyNotNull(bean);
    return status(true);
  }
  
  // 删除包库合同子表
  @RequestMapping(value = "remove.do")
  @ResponseBody
  public Map<String, Boolean> remove(String id) {
    service.remove(id);
    return status(true);
  }
  
  @Autowired
  IFahuodandetailService service;
}
