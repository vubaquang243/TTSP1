package com.seatech.ttsp.dchieu;

import java.util.Date;

public class DChieu4CTietVO {
  private  Long id;     
  private  String bk_id;
  private  Date ngay_ct;
  private  String ma_kb;
  private  String mt_id;
  private  Long so_tien;
  private String mt900_thua;
  private String mt900_thieu;
  private String mt910_thua;
  private String mt910_thieu;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setBk_id(String bk_id) {
        this.bk_id = bk_id;
    }

    public String getBk_id() {
        return bk_id;
    }

    public void setNgay_ct(Date ngay_ct) {
        this.ngay_ct = ngay_ct;
    }

    public Date getNgay_ct() {
        return ngay_ct;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
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


    public void setMt900_thua(String mt900_thua) {
        this.mt900_thua = mt900_thua;
    }

    public String getMt900_thua() {
        return mt900_thua;
    }

    public void setMt900_thieu(String mt900_thieu) {
        this.mt900_thieu = mt900_thieu;
    }

    public String getMt900_thieu() {
        return mt900_thieu;
    }

    public void setMt910_thua(String mt910_thua) {
        this.mt910_thua = mt910_thua;
    }

    public String getMt910_thua() {
        return mt910_thua;
    }

    public void setMt910_thieu(String mt910_thieu) {
        this.mt910_thieu = mt910_thieu;
    }

    public String getMt910_thieu() {
        return mt910_thieu;
    }
}
