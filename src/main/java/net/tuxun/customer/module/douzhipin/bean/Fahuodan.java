package net.tuxun.customer.module.douzhipin.bean;

import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Fahuodan implements java.io.Serializable {
  

    public Fahuodan() {
    }
    
    // 主键 
    private String id;
    private String shopname;
    private String yanshouman;
    private String shopmanager;
    private Date fahuodate;

    private List<Fahuodandetail> details;
    
     
    public  String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getYanshouman() {
        return yanshouman;
    }

    public void setYanshouman(String yanshouman) {
        this.yanshouman = yanshouman;
    }

    public String getShopmanager() {
        return shopmanager;
    }

    public void setShopmanager(String shopmanager) {
        this.shopmanager = shopmanager;
    }

    public Date getFahuodate() {
        return fahuodate;
    }

    public void setFahuodate(Date fahuodate) {
        this.fahuodate = fahuodate;
    }

    public List<Fahuodandetail> getDetails() {
        return details;
    }

    public void setDetails(List<Fahuodandetail> details) {
        this.details = details;
    }

    public Fahuodan(String id, String shopname, String yanshouman, String shopmanager, Date fahuodate, List<Fahuodandetail> details) {
    
        this.id = id;
        this.shopname = shopname;
        this.yanshouman = yanshouman;
        this.shopmanager = shopmanager;
        this.fahuodate = fahuodate;
        this.details = details;
    }
}
