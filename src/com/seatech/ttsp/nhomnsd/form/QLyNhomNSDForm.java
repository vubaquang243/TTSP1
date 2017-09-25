package com.seatech.ttsp.nhomnsd.form;

import org.apache.struts.action.ActionForm;

public class QLyNhomNSDForm extends ActionForm {
    private String id;
    private String ten_nhom;
    private String loai_nhom;
    private String pageNumber;
    private String rv_domain;
    private String rv_low_value;
    private String rv_high_value;
    private String rv_abbreviation;
    private String rv_meaning;
    private String trangthai;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }


    public void setRv_domain(String rv_domain) {
        this.rv_domain = rv_domain;
    }

    public String getRv_domain() {
        return rv_domain;
    }

    public void setRv_low_value(String rv_low_value) {
        this.rv_low_value = rv_low_value;
    }

    public String getRv_low_value() {
        return rv_low_value;
    }

    public void setRv_high_value(String rv_high_value) {
        this.rv_high_value = rv_high_value;
    }

    public String getRv_high_value() {
        return rv_high_value;
    }

    public void setRv_abbreviation(String rv_abbreviation) {
        this.rv_abbreviation = rv_abbreviation;
    }

    public String getRv_abbreviation() {
        return rv_abbreviation;
    }

    public void setRv_meaning(String rv_meaning) {
        this.rv_meaning = rv_meaning;
    }

    public String getRv_meaning() {
        return rv_meaning;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTrangthai() {
        return trangthai;
    }
}
