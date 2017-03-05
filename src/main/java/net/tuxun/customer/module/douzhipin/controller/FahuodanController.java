package net.tuxun.customer.module.douzhipin.controller;

import net.tuxun.core.base.controller.BaseController;
import net.tuxun.core.mybatis.page.PageNav;
import net.tuxun.core.mybatis.page.PageQuery;
import net.tuxun.core.util.UncDate;
import net.tuxun.customer.module.admin.bean.UserInfo;
import net.tuxun.customer.module.douzhipin.bean.Fahuodan;
import net.tuxun.customer.module.douzhipin.bean.Fahuodandetail;
import net.tuxun.customer.module.douzhipin.service.IFahuodanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 配送发货单
 *
 * @author AcheronHuang
 */
@Controller
@RequestMapping("/contract/bcContract")
public class FahuodanController extends BaseController {

    @RequestMapping("list.do")
    public String list(PageQuery query, Model model) {
        query.attr.setInfoSize(100);
        PageNav<Fahuodan> pageNav = service.pageResult(query);
        model.addAttribute("pageNav", pageNav);
        model.addAttribute("query", query);
        return "contract/bcContract/list";
    }

    @RequestMapping("printContract.do")
    public String print(String id, Model model) {
        Fahuodan bean = service.get(id);
        float totaljine = 0f;
        if(bean!=null && bean.getDetails()!=null) {
            List<Fahuodandetail> details = bean.getDetails();
            for(Fahuodandetail detail:details){
                totaljine += Float.parseFloat(detail.getJine());
            }
        }
        model.addAttribute("totaljine",totaljine);
        model.addAttribute("bean", bean);
        return "contract/bcContract/printContract";
    }

    
    @RequestMapping(value = "add.do", method = RequestMethod.GET)
    public String toAdd(String id, Model model) {
        Fahuodan bean = new Fahuodan();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        bean.setFahuodate(UncDate.parseDate(df.format(new Date())));
        if (id != null) {
            bean = service.get(id);
        }
        model.addAttribute("bean", bean);
        model.addAttribute(OPERATION, ADD);
        return "contract/bcContract/form";
    }

    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    public String add(UserInfo user, Fahuodan bean, String redirect, RedirectAttributes ra) {
        service.saveFull(bean);
        ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
        if (redirect.equals(ALTER)) {
            return "redirect:alter.do?id=" + bean.getId();
        } else if (redirect.equals(ADD)) {
            return "redirect:add.do";
        } else {
            return "redirect:list.do";
        }
    }

    @RequestMapping(value = "alter.do", method = RequestMethod.GET)
    public String toAlter(String id, Model model) {
        Fahuodan bean = service.get(id);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        bean.setFahuodate(UncDate.parseDate(df.format(new Date())));
        model.addAttribute("bean", bean);
        model.addAttribute(OPERATION, ALTER);
        return "contract/bcContract/form";
    }

    @RequestMapping(value = "alter.do", method = RequestMethod.POST)
    public String alter(UserInfo user, Fahuodan bean, String redirect, RedirectAttributes ra) {
        service.modifyFull(bean);
        ra.addFlashAttribute(MESSAGE, SAVE_SUCCESS);
        if (redirect.equals(ALTER)) {
            return "redirect:alter.do?id=" + bean.getId();
        } else if (redirect.equals(ADD)) {
            return "redirect:add.do";
        } else {
            return "redirect:list.do";
        }
    }

    @RequestMapping(value = "remove.do")
    public String remove(String id, RedirectAttributes ra) {
        service.removeFull(id);
        ra.addFlashAttribute(MESSAGE, REMOVE_SUCCESS);
        return "redirect:list.do";
    }


    @Autowired
    IFahuodanService service;

}
