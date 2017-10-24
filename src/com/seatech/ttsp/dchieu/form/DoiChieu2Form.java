package com.seatech.ttsp.dchieu.form;


import com.seatech.framework.strustx.AppForm;

import java.util.Date;


public class DoiChieu2Form extends AppForm {
  private  String id;          
  private  String lan_dc;      
  private  Date ngay_dc;       
  private  String send_bank;   
  private  String receive_bank;
  private  Date created_date;  
  private  String creator;     
  private  String manager;     
  private  Date verified_date; 
  private  Long sodu_daungay;  
  private  Long ketchuyen_chi; 
  private  Long ps_thu;        
  private  Long hanmuc;        
  private  Long ketchuyen_thu; 
  private  Long sodu_cuoingay; 
  private  Date insert_date;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLan_dc(String lan_dc) {
        this.lan_dc = lan_dc;
    }

    public String getLan_dc() {
        return lan_dc;
    }

    public void setNgay_dc(Date ngay_dc) {
        this.ngay_dc = ngay_dc;
    }

    public Date getNgay_dc() {
        return ngay_dc;
    }

    public void setSend_bank(String send_bank) {
        this.send_bank = send_bank;
    }

    public String getSend_bank() {
        return send_bank;
    }

    public void setReceive_bank(String receive_bank) {
        this.receive_bank = receive_bank;
    }

    public String getReceive_bank() {
        return receive_bank;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager() {
        return manager;
    }

    public void setVerified_date(Date verified_date) {
        this.verified_date = verified_date;
    }

    public Date getVerified_date() {
        return verified_date;
    }

    public void setSodu_daungay(Long sodu_daungay) {
        this.sodu_daungay = sodu_daungay;
    }

    public Long getSodu_daungay() {
        return sodu_daungay;
    }

    public void setKetchuyen_chi(Long ketchuyen_chi) {
        this.ketchuyen_chi = ketchuyen_chi;
    }

    public Long getKetchuyen_chi() {
        return ketchuyen_chi;
    }

    public void setPs_thu(Long ps_thu) {
        this.ps_thu = ps_thu;
    }

    public Long getPs_thu() {
        return ps_thu;
    }

    public void setHanmuc(Long hanmuc) {
        this.hanmuc = hanmuc;
    }

    public Long getHanmuc() {
        return hanmuc;
    }

    public void setKetchuyen_thu(Long ketchuyen_thu) {
        this.ketchuyen_thu = ketchuyen_thu;
    }

    public Long getKetchuyen_thu() {
        return ketchuyen_thu;
    }

    public void setSodu_cuoingay(Long sodu_cuoingay) {
        this.sodu_cuoingay = sodu_cuoingay;
    }

    public Long getSodu_cuoingay() {
        return sodu_cuoingay;
    }

    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }

    public Date getInsert_date() {
        return insert_date;
    }
}
