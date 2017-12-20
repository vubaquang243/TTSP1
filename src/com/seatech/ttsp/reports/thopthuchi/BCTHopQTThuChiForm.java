package com.seatech.ttsp.reports.thopthuchi;

import org.apache.struts.action.ActionForm;

public class BCTHopQTThuChiForm  extends ActionForm{
  private String nhkb_tinh;
  private String nhkb_huyen;
  private String ngan_hang;
  private String ma_ngan_hang;
  private String loai_tien;
  private String tu_ngay;
  private String den_ngay;
  private String pageNumber;

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

    public void setMa_ngan_hang(String ma_ngan_hang) {
        this.ma_ngan_hang = ma_ngan_hang;
    }

    public String getMa_ngan_hang() {
        return ma_ngan_hang;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }

    public void setTu_ngay(String tu_ngay) {
        this.tu_ngay = tu_ngay;
    }

    public String getTu_ngay() {
        return tu_ngay;
    }

    public void setDen_ngay(String den_ngay) {
        this.den_ngay = den_ngay;
    }

    public String getDen_ngay() {
        return den_ngay;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }
}
