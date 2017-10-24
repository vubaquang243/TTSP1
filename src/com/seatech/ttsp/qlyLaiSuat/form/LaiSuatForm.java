package com.seatech.ttsp.qlyLaiSuat.form;

import com.seatech.framework.strustx.AppForm;

public class LaiSuatForm extends AppForm{
    private String ma_dv;
    private String fromDate;
    private String toDate;
    private String laiSuat;
    private String loaiLaiSuat;
    private String loaiTien;
    private String pageNumber;
    private String ghiChu;
    
    private String ma_kb;
    private String ma_nh;
    private String so_tk;

    public void setMa_dv(String ma_dv) {
        this.ma_dv = ma_dv;
    }

    public String getMa_dv() {
        return ma_dv;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setLaiSuat(String laiSuat) {
        this.laiSuat = laiSuat;
    }

    public String getLaiSuat() {
        return laiSuat;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setLoaiLaiSuat(String loaiLaiSuat) {
        this.loaiLaiSuat = loaiLaiSuat;
    }

    public String getLoaiLaiSuat() {
        return loaiLaiSuat;
    }

    public void setLoaiTien(String loaiTien) {
        this.loaiTien = loaiTien;
    }

    public String getLoaiTien() {
        return loaiTien;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setSo_tk(String so_tk) {
        this.so_tk = so_tk;
    }

    public String getSo_tk() {
        return so_tk;
    }
}
