package com.seatech.ttsp.dchieu.form;


import com.seatech.framework.strustx.AppForm;


public class KQDChieu3NgoaiTeForm extends AppForm {
  private  String id;          
  private  String bk_id;       
  private  String lan_dc;      
  private  String ngay_dc;       
  private  String send_bank;   
  private  String receive_bank;
  private  String creator;     
  private  String manager;     
  private  String created_date;  
  private  String verified_date; 
  private  String msg_id;      
  private  String insert_date;   
  private  Long so_du_kbnn;    
  private  Long chenh_lech;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBk_id(String bk_id) {
        this.bk_id = bk_id;
    }

    public String getBk_id() {
        return bk_id;
    }

    public void setLan_dc(String lan_dc) {
        this.lan_dc = lan_dc;
    }

    public String getLan_dc() {
        return lan_dc;
    }

    public void setNgay_dc(String ngay_dc) {
        this.ngay_dc = ngay_dc;
    }

    public String getNgay_dc() {
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

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setVerified_date(String verified_date) {
        this.verified_date = verified_date;
    }

    public String getVerified_date() {
        return verified_date;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setSo_du_kbnn(Long so_du_kbnn) {
        this.so_du_kbnn = so_du_kbnn;
    }

    public Long getSo_du_kbnn() {
        return so_du_kbnn;
    }

    public void setChenh_lech(Long chenh_lech) {
        this.chenh_lech = chenh_lech;
    }

    public Long getChenh_lech() {
        return chenh_lech;
    }
}
