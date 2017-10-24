package com.seatech.ttsp.reports.thltt.forms;

import org.apache.struts.action.ActionForm;

public class THLTTRptForm extends ActionForm {
    private String tu_ngay;
    private String den_ngay;
    private String loai_hienthi;

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
}
