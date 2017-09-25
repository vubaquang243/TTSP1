package com.seatech.ttsp.ltt.form;

import org.apache.struts.action.ActionForm;

public class LTTToanQuoc extends ActionForm {
    public LTTToanQuoc() {
        super();
    }
    private String ma_nh;
    private String tinh;
    private String huyen;
    private String trang_thai;
    private String tu_ltt;
    private String den_ltt;
    private String tu_ngay;
    private String den_ngay;
    private String tu_ngay_nhan;
    private String den_ngay_nhan;
    private Long so_tien;
    private String loai_lenh;
    private String pageNumber;
    private String ma_dv;
    private String huyen_back;
    private String ma_nt;
    private String phi;

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public String getTinh() {
        return tinh;
    }

    public void setHuyen(String huyen) {
        this.huyen = huyen;
    }

    public String getHuyen() {
        return huyen;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTu_ltt(String tu_ltt) {
        this.tu_ltt = tu_ltt;
    }

    public String getTu_ltt() {
        return tu_ltt;
    }

    public void setDen_ltt(String den_ltt) {
        this.den_ltt = den_ltt;
    }

    public String getDen_ltt() {
        return den_ltt;
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

    public void setSo_tien(Long so_tien) {
        this.so_tien = so_tien;
    }

    public Long getSo_tien() {
        return so_tien;
    }

    public void setLoai_lenh(String loai_lenh) {
        this.loai_lenh = loai_lenh;
    }

    public String getLoai_lenh() {
        return loai_lenh;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setTu_ngay_nhan(String tu_ngay_nhan) {
        this.tu_ngay_nhan = tu_ngay_nhan;
    }

    public String getTu_ngay_nhan() {
        return tu_ngay_nhan;
    }

    public void setDen_ngay_nhan(String den_ngay_nhan) {
        this.den_ngay_nhan = den_ngay_nhan;
    }

    public String getDen_ngay_nhan() {
        return den_ngay_nhan;
    }

    public void setMa_dv(String ma_dv) {
        this.ma_dv = ma_dv;
    }

    public String getMa_dv() {
        return ma_dv;
    }

    public void setHuyen_back(String huyen_back) {
        this.huyen_back = huyen_back;
    }

    public String getHuyen_back() {
        return huyen_back;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }

    public void setPhi(String phi) {
        this.phi = phi;
    }

    public String getPhi() {
        return phi;
    }
}
