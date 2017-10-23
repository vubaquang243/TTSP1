package com.seatech.ttsp.tracuukhobac.form;

import com.seatech.framework.strustx.AppForm;

public class TraCuuKhoBacForm extends AppForm{
    private String ma_kb;
    private String ten_kb;
    private String id_kb_tinh;
    private String id_kb_huyen;

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

    public void setId_kb_tinh(String id_kb_tinh) {
        this.id_kb_tinh = id_kb_tinh;
    }

    public String getId_kb_tinh() {
        return id_kb_tinh;
    }

    public void setId_kb_huyen(String id_kb_huyen) {
        this.id_kb_huyen = id_kb_huyen;
    }

    public String getId_kb_huyen() {
        return id_kb_huyen;
    }
}
