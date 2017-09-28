package com.seatech.ttsp.dchieu.form;


import com.seatech.framework.strustx.AppForm;


public class KQDChieu4CTietForm extends AppForm {
  private  Long id;         
  private  String kq_id;    
  private  String bk_id;    
  private  String insert_date;
  private  String ngay_ct;    
  private  String ma_kb;    
  private  String ten_kb;   
  private  String mt_id;    
  private  Long so_tien;    
  private  String tran_thai;
  private  String mt_type;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setKq_id(String kq_id) {
        this.kq_id = kq_id;
    }

    public String getKq_id() {
        return kq_id;
    }

    public void setBk_id(String bk_id) {
        this.bk_id = bk_id;
    }

    public String getBk_id() {
        return bk_id;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setNgay_ct(String ngay_ct) {
        this.ngay_ct = ngay_ct;
    }

    public String getNgay_ct() {
        return ngay_ct;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setTen_kb(String ten_kb) {
        this.ten_kb = ten_kb;
    }

    public String getTen_kb() {
        return ten_kb;
    }

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }

    public String getMt_id() {
        return mt_id;
    }

    public void setSo_tien(Long so_tien) {
        this.so_tien = so_tien;
    }

    public Long getSo_tien() {
        return so_tien;
    }

    public void setTran_thai(String tran_thai) {
        this.tran_thai = tran_thai;
    }

    public String getTran_thai() {
        return tran_thai;
    }

    public void setMt_type(String mt_type) {
        this.mt_type = mt_type;
    }

    public String getMt_type() {
        return mt_type;
    }
}
