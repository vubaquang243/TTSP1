package com.seatech.ttsp.chucnang.form;

import org.apache.struts.action.ActionForm;

public class PhanQuyenForm extends ActionForm{
  private String nhom_id;
  private String loai_nhom;
  private String ten_nhom;
  private String cnang_id;

    public void setNhom_id(String nhom_id) {
        this.nhom_id = nhom_id;
    }

    public String getNhom_id() {
        return nhom_id;
    }

    public void setLoai_nhom(String loai_nhom) {
        this.loai_nhom = loai_nhom;
    }

    public String getLoai_nhom() {
        return loai_nhom;
    }

    public void setTen_nhom(String ten_nhom) {
        this.ten_nhom = ten_nhom;
    }

    public String getTen_nhom() {
        return ten_nhom;
    }

    public void setCnang_id(String cnang_id) {
        this.cnang_id = cnang_id;
    }

    public String getCnang_id() {
        return cnang_id;
    }
}
