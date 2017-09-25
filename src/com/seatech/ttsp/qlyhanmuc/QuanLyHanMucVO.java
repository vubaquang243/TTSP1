package com.seatech.ttsp.qlyhanmuc;

import java.util.Date;

public class QuanLyHanMucVO {
  private  Long id;               
  private  String ma_kb;          
  private  Long han_muc_du_no;    
  private  Date ngay_tao;         
  private  Date ngay_hieu_luc;    
  private  Date ngay_het_hieu_luc;
  private  String trang_thai;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setHan_muc_du_no(Long han_muc_du_no) {
        this.han_muc_du_no = han_muc_du_no;
    }

    public Long getHan_muc_du_no() {
        return han_muc_du_no;
    }

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public Date getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_hieu_luc(Date ngay_hieu_luc) {
        this.ngay_hieu_luc = ngay_hieu_luc;
    }

    public Date getNgay_hieu_luc() {
        return ngay_hieu_luc;
    }

    public void setNgay_het_hieu_luc(Date ngay_het_hieu_luc) {
        this.ngay_het_hieu_luc = ngay_het_hieu_luc;
    }

    public Date getNgay_het_hieu_luc() {
        return ngay_het_hieu_luc;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }
}
