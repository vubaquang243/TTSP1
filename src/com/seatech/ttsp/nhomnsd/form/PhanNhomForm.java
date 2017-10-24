package com.seatech.ttsp.nhomnsd.form;

import org.apache.struts.action.ActionForm;

public class PhanNhomForm extends ActionForm{
    private String kb_id;
    private String ma_kb;
    private String ten_kb;
    private String nhom_id;
    private String ten_nhom;
    private String loai_nhom;
    private String nsd_id;
    private String ten_nsd;
    private String ma_nsd;

    public void setKb_id(String kb_id) {
        this.kb_id = kb_id;
    }

    public String getKb_id() {
        return kb_id;
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

    public void setNhom_id(String nhom_id) {
        this.nhom_id = nhom_id;
    }

    public String getNhom_id() {
        return nhom_id;
    }

    public void setTen_nhom(String ten_nhom) {
        this.ten_nhom = ten_nhom;
    }

    public String getTen_nhom() {
        return ten_nhom;
    }

    public void setLoai_nhom(String loai_nhom) {
        this.loai_nhom = loai_nhom;
    }

    public String getLoai_nhom() {
        return loai_nhom;
    }

    public void setNsd_id(String nsd_id) {
        this.nsd_id = nsd_id;
    }

    public String getNsd_id() {
        return nsd_id;
    }

    public void setTen_nsd(String ten_nsd) {
        this.ten_nsd = ten_nsd;
    }

    public String getTen_nsd() {
        return ten_nsd;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }
}
