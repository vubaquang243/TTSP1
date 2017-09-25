package com.seatech.ttsp.sodu.form;

import org.apache.struts.action.ActionForm;

public class SoDuForm  extends ActionForm{
    boolean Empty;
    private String pageNumber;
    private String action_status;
    private String type;
    private String id;
    private String ma_kb;
    private String ma_nh;
    private String ngay_gd;
    private String so_du;
    private String insert_date;
    private String so_du_cot;
    private String loai_tien;
    private String ten_kb;
    private String ten_nhkb_nhan;
    private String id_nhkb_nhan;
    private String ten_nhkb_nhan1;

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getAction_status() {
        return action_status;
    }

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

    public void setNgay_gd(String ngay_gd) {
        this.ngay_gd = ngay_gd;
    }

    public String getNgay_gd() {
        return ngay_gd;
    }

    public void setSo_du(String so_du) {
        this.so_du = so_du;
    }

    public String getSo_du() {
        return so_du;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setSo_du_cot(String so_du_cot) {
        this.so_du_cot = so_du_cot;
    }

    public String getSo_du_cot() {
        return so_du_cot;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setEmpty(boolean Empty) {
        this.Empty = Empty;
    }

    public boolean isEmpty() {
        return Empty;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTen_kb(String ten_kb) {
        this.ten_kb = ten_kb;
    }

    public String getTen_kb() {
        return ten_kb;
    }

    public void setTen_nhkb_nhan(String ten_nhkb_nhan) {
        this.ten_nhkb_nhan = ten_nhkb_nhan;
    }

    public String getTen_nhkb_nhan() {
        return ten_nhkb_nhan;
    }

    public void setId_nhkb_nhan(String id_nhkb_nhan) {
        this.id_nhkb_nhan = id_nhkb_nhan;
    }

    public String getId_nhkb_nhan() {
        return id_nhkb_nhan;
    }

    public void setTen_nhkb_nhan1(String ten_nhkb_nhan1) {
        this.ten_nhkb_nhan1 = ten_nhkb_nhan1;
    }

    public String getTen_nhkb_nhan1() {
        return ten_nhkb_nhan1;
    }
}
