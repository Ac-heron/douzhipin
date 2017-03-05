package net.tuxun.customer.module.douzhipin.bean;

/**
 * 数据库表[BC_CONTRACT_DETAIL]的数据模型
 * 包库合同子表
 * 
 * 
 * @author Huang
 * 
 */
@SuppressWarnings("serial")
public class Fahuodandetail implements java.io.Serializable {

    public Fahuodandetail() {
    }
    
    private String applyId;
    private String id;
    private String dcode;
    private String dname;
    private String dpackage;
    private String dprice;
    private String caigounum;
    private String yanshounum;
    private String remark;
    private String jine;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDcode() {
        return dcode;
    }

    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDpackage() {
        return dpackage;
    }

    public void setDpackage(String dpackage) {
        this.dpackage = dpackage;
    }

    public Fahuodandetail(String applyId, String id, String dcode, String dname, String dpackage, String dprice, String caigounum, String yanshounum, String remark, String jine) {
        this.applyId = applyId;
        this.id = id;
        this.dcode = dcode;
        this.dname = dname;
        this.dpackage = dpackage;
        this.dprice = dprice;
        this.caigounum = caigounum;
        this.yanshounum = yanshounum;
        this.remark = remark;
        this.jine = jine;
    }

    public String getDprice() {
        return dprice;
    }

    public void setDprice(String dprice) {
//        if(dprice.equals("")){
//            dprice = "0";
//        }
        this.dprice = dprice;
    }

    public String getCaigounum() {
        return caigounum;
    }

    public void setCaigounum(String caigounum) {
//        if(caigounum.equals("")){
//            caigounum = "0";
//        }
        this.caigounum = caigounum;
    }

    public String getYanshounum() {
        return yanshounum;
    }

    public void setYanshounum(String yanshounum) {
//        if(yanshounum.equals("")){
//            yanshounum = "0";
//        }
        this.yanshounum = yanshounum;
    }

    public String getJine() {
        return jine;
    }

    public void setJine(String jine) {
        if(jine.equals("")){
            jine = "0";
        }
        this.jine = jine;
    }
}


