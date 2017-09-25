package com.seatech.ttsp.phipos;

import org.apache.struts.action.ActionForm;

public class BKePhiPOSForm extends ActionForm {
    private String pageNumber;
    private String so_tk;
    private String nhkb_tinh;
    private String ma_nh;
    private String ma_kb;
    private String tu_ngay;
    private String den_ngay;
    private String ma_nt;
    private String mt_id;
    private String ngay_gd;
    private String ma_nh_2;
    private String ma_kb_2;

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setSo_tk(String so_tk) {
        this.so_tk = so_tk;
    }

    public String getSo_tk() {
        return so_tk;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
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

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }

    public void setNhkb_tinh(String nhkb_tinh) {
        this.nhkb_tinh = nhkb_tinh;
    }

    public String getNhkb_tinh() {
        return nhkb_tinh;
    }

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }

    public String getMt_id() {
        return mt_id;
    }

    public void setNgay_gd(String ngay_gd) {
        this.ngay_gd = ngay_gd;
    }

    public String getNgay_gd() {
        return ngay_gd;
    }

    public void setMa_nh_2(String ma_nh_2) {
        this.ma_nh_2 = ma_nh_2;
    }

    public String getMa_nh_2() {
        return ma_nh_2;
    }

    public void setMa_kb_2(String ma_kb_2) {
        this.ma_kb_2 = ma_kb_2;
    }

    public String getMa_kb_2() {
        return ma_kb_2;
    }
}
