package com.seatech.ttsp.chucnang;

import java.util.List;

public class ChucNangVO {
    private Long id;
    private Long cnang_cha;
    private String ten_cnang;
    private String mo_ta;
    private String ten_action;
    private String hien_thi;
    private String sap_xep;
    private String trang_thai;
    private String ma_cnang;
    private String loai_cnang;
    private String ky_hieu_cnang;
    private List dschucnang;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCnang_cha(Long cnang_cha) {
        this.cnang_cha = cnang_cha;
    }

    public Long getCnang_cha() {
        return cnang_cha;
    }

    public void setTen_cnang(String ten_cnang) {
        this.ten_cnang = ten_cnang;
    }

    public String getTen_cnang() {
        return ten_cnang;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setTen_action(String ten_action) {
        this.ten_action = ten_action;
    }

    public String getTen_action() {
        return ten_action;
    }

    public void setHien_thi(String hien_thi) {
        this.hien_thi = hien_thi;
    }

    public String getHien_thi() {
        return hien_thi;
    }

    public void setSap_xep(String sap_xep) {
        this.sap_xep = sap_xep;
    }

    public String getSap_xep() {
        return sap_xep;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setMa_cnang(String ma_cnang) {
        this.ma_cnang = ma_cnang;
    }

    public String getMa_cnang() {
        return ma_cnang;
    }

    public void setLoai_cnang(String loai_cnang) {
        this.loai_cnang = loai_cnang;
    }

    public String getLoai_cnang() {
        return loai_cnang;
    }

    public void setKy_hieu_cnang(String ky_hieu_cnang) {
        this.ky_hieu_cnang = ky_hieu_cnang;
    }

    public String getKy_hieu_cnang() {
        return ky_hieu_cnang;
    }

    public void setDschucnang(List dschucnang) {
        this.dschucnang = dschucnang;
    }

    public List getDschucnang() {
        return dschucnang;
    }
}
