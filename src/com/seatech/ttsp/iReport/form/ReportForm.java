package com.seatech.ttsp.iReport.form;

import org.apache.struts.action.ActionForm;

public class ReportForm extends ActionForm {

    private String loai_hienthi;
    private String nhkb_nhan;
    private String nhkb_chuyen;
    private String tu_ngay;
    private String den_ngay;
    
    public void setLoai_hienthi(String loai_hienthi) {
        this.loai_hienthi = loai_hienthi;
    }

    public String getLoai_hienthi() {
        return loai_hienthi;
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

    public void setNhkb_nhan(String nhkb_nhan) {
        this.nhkb_nhan = nhkb_nhan;
    }

    public String getNhkb_nhan() {
        return nhkb_nhan;
    }

    public void setNhkb_chuyen(String nhkb_chuyen) {
        this.nhkb_chuyen = nhkb_chuyen;
    }

    public String getNhkb_chuyen() {
        return nhkb_chuyen;
    }
}
