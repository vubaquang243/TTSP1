package com.seatech.ttsp.reports.bkegdichtcong.forms;

import org.apache.struts.action.ActionForm;
/**
   * @create: ThuongDT
   * @create date: 11/2016 
   * @see: Them form cho chuc nang bao cao giao dich thu cong
   * */
public class BKeGDichTCongForm  extends ActionForm{
  private String nhkb_tinh;  
  private String nhkb_huyen;
  private String ngan_hang; 
  private String loaiTien;
  private String tuNgay;
  private String denNgay;

    public void setNhkb_tinh(String nhkb_tinh) {
        this.nhkb_tinh = nhkb_tinh;
    }

    public String getNhkb_tinh() {
        return nhkb_tinh;
    }

    public void setNhkb_huyen(String nhkb_huyen) {
        this.nhkb_huyen = nhkb_huyen;
    }

    public String getNhkb_huyen() {
        return nhkb_huyen;
    }

    public void setNgan_hang(String ngan_hang) {
        this.ngan_hang = ngan_hang;
    }

    public String getNgan_hang() {
        return ngan_hang;
    }

    public void setLoaiTien(String loaiTien) {
        this.loaiTien = loaiTien;
    }

    public String getLoaiTien() {
        return loaiTien;
    }

    public void setTuNgay(String tuNgay) {
        this.tuNgay = tuNgay;
    }

    public String getTuNgay() {
        return tuNgay;
    }

    public void setDenNgay(String denNgay) {
        this.denNgay = denNgay;
    }

    public String getDenNgay() {
        return denNgay;
    }
}
