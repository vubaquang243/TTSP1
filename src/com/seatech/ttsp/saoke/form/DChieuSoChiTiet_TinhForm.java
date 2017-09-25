package com.seatech.ttsp.saoke.form;

import com.seatech.framework.strustx.AppForm;

public class DChieuSoChiTiet_TinhForm extends AppForm{
    private String kb_tinh;
    private String ngay;

    public void setKb_tinh(String kb_tinh) {
        this.kb_tinh = kb_tinh;
    }

    public String getKb_tinh() {
        return kb_tinh;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getNgay() {
        return ngay;
    }
}
