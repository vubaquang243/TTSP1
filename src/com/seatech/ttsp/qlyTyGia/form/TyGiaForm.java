package com.seatech.ttsp.qlyTyGia.form;

import com.seatech.framework.strustx.AppForm;

public class TyGiaForm extends AppForm {
    private String ma_nh;
    private String ma_kb;
    private String ma_nt;
    private String fromDate;
    private String toDate;
    private String tu_ngay;
    private String den_ngay;
    private String loaiTyGia;

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
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

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }

    public void setLoaiTyGia(String loaiTyGia) {
        this.loaiTyGia = loaiTyGia;
    }

    public String getLoaiTyGia() {
        return loaiTyGia;
    }
}
