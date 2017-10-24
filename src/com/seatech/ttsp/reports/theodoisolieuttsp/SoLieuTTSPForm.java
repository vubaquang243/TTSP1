package com.seatech.ttsp.reports.theodoisolieuttsp;

import org.apache.struts.action.ActionForm;

public class SoLieuTTSPForm extends ActionForm {

    private String ngay_dc;
    private String nhkb_tinh;
    private String nhkb_huyen;
    private String ngan_hang;
    private String ma_ngan_hang;
    private String loai_tien;


    public void setNgay_dc(String ngay_dc) {
        this.ngay_dc = ngay_dc;
    }

    public String getNgay_dc() {
        return ngay_dc;
    }

    public void setNhkb_tinh(String nhkb_tinh) {
        this.nhkb_tinh = nhkb_tinh;
    }

    public String getNhkb_tinh() {
        return nhkb_tinh;
    }

    public void setNhkb_huyen(String nhkb_huyen) {
        this.nhkb_huyen = nhkb_huyen;
    }

    public String getNhkb_huyen() {
        return nhkb_huyen;
    }

    public void setNgan_hang(String ngan_hang) {
        this.ngan_hang = ngan_hang;
    }

    public String getNgan_hang() {
        return ngan_hang;
    }

    public void setMa_ngan_hang(String ma_ngan_hang) {
        this.ma_ngan_hang = ma_ngan_hang;
    }

    public String getMa_ngan_hang() {
        return ma_ngan_hang;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }
}
