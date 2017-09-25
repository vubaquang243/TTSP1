package com.seatech.ttsp.theodoiht.form;

import org.apache.struts.action.ActionForm;

public class TraCuuTabmisForm extends ActionForm {
    private String ma_kb_nhan;
    private String ma_nh_chuyen;
    private String so_ltt;
    private String so_tien;
    private String tu_ngay_tt;
    private String den_ngay_tt;

    public void setMa_kb_nhan(String ma_kb_nhan) {
        this.ma_kb_nhan = ma_kb_nhan;
    }

    public String getMa_kb_nhan() {
        return ma_kb_nhan;
    }

    public void setMa_nh_chuyen(String ma_nh_chuyen) {
        this.ma_nh_chuyen = ma_nh_chuyen;
    }

    public String getMa_nh_chuyen() {
        return ma_nh_chuyen;
    }

    public void setSo_ltt(String so_ltt) {
        this.so_ltt = so_ltt;
    }

    public String getSo_ltt() {
        return so_ltt;
    }

    public void setSo_tien(String so_tien) {
        this.so_tien = so_tien;
    }

    public String getSo_tien() {
        return so_tien;
    }

    public void setTu_ngay_tt(String tu_ngay_tt) {
        this.tu_ngay_tt = tu_ngay_tt;
    }

    public String getTu_ngay_tt() {
        return tu_ngay_tt;
    }

    public void setDen_ngay_tt(String den_ngay_tt) {
        this.den_ngay_tt = den_ngay_tt;
    }

    public String getDen_ngay_tt() {
        return den_ngay_tt;
    }
}
