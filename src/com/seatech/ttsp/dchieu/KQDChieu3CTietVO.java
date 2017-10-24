package com.seatech.ttsp.dchieu;

import java.math.BigDecimal;

import java.util.Date;

public class KQDChieu3CTietVO {
  private  Long id;         
  private  String kq_id;    
  private  String bk_id;    
  private  Date insert_date;
  private  String ngay_ct;    
  private  String ma_kb;    
  private  String ten_kb;   
  private  String mt_id;    
  private  String so_tien;    
  private  String trang_thai;
  private  String mt_type;
  //out 
  private  Long mt900thieu;
  private  Long mt900thua;
  private  Long mt910thieu;
  private  Long mt910thua;
  private String send_bank;
  private String ten_send_bank;
  private String receive_bank;
  private String ten_receive_bank;
  private String ma_nh;
  
  

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

    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }

    public Date getInsert_date() {
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

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setMt_type(String mt_type) {
        this.mt_type = mt_type;
    }

    public String getMt_type() {
        return mt_type;
    }

    public void setMt900thieu(Long mt900thieu) {
        this.mt900thieu = mt900thieu;
    }

    public Long getMt900thieu() {
        return mt900thieu;
    }

    public void setMt900thua(Long mt900thua) {
        this.mt900thua = mt900thua;
    }

    public Long getMt900thua() {
        return mt900thua;
    }

    public void setMt910thieu(Long mt910thieu) {
        this.mt910thieu = mt910thieu;
    }

    public Long getMt910thieu() {
        return mt910thieu;
    }

    public void setMt910thua(Long mt910thua) {
        this.mt910thua = mt910thua;
    }

    public Long getMt910thua() {
        return mt910thua;
    }

    public void setSend_bank(String send_bank) {
        this.send_bank = send_bank;
    }

    public String getSend_bank() {
        return send_bank;
    }

    public void setTen_send_bank(String ten_send_bank) {
        this.ten_send_bank = ten_send_bank;
    }

    public String getTen_send_bank() {
        return ten_send_bank;
    }

    public void setReceive_bank(String receive_bank) {
        this.receive_bank = receive_bank;
    }

    public String getReceive_bank() {
        return receive_bank;
    }

    public void setTen_receive_bank(String ten_receive_bank) {
        this.ten_receive_bank = ten_receive_bank;
    }

    public String getTen_receive_bank() {
        return ten_receive_bank;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setSo_tien(String so_tien) {
        this.so_tien = so_tien;
    }

    public String getSo_tien() {
        return so_tien;
    }
}
