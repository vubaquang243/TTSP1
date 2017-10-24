package com.seatech.ttsp.dts.form;


import org.apache.struts.action.ActionForm;


public class TraCuuDTSToanQuocForm extends ActionForm {
    public TraCuuDTSToanQuocForm() {
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
    private Long so_tien;
    private String loai_lenh;
    private String so_dts;
    private String pageNumber;

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

    public void setSo_dts(String so_dts) {
        this.so_dts = so_dts;
    }

    public String getSo_dts() {
        return so_dts;
    }
}
