package com.seatech.ttsp.dmnh.form;

import org.apache.struts.action.ActionForm;

public class DMNHangForm extends ActionForm {
    private String id;
    private String ma_nh;
    private String ma_tinh;
    private String ma_huyen;
    private String ten;
    private String tinh_trang;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setMa_tinh(String ma_tinh) {
        this.ma_tinh = ma_tinh;
    }

    public String getMa_tinh() {
        return ma_tinh;
    }

    public void setMa_huyen(String ma_huyen) {
        this.ma_huyen = ma_huyen;
    }

    public String getMa_huyen() {
        return ma_huyen;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setTinh_trang(String tinh_trang) {
        this.tinh_trang = tinh_trang;
    }

    public String getTinh_trang() {
        return tinh_trang;
    }
}
