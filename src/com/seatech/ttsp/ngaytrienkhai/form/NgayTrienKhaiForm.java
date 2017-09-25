package com.seatech.ttsp.ngaytrienkhai.form;

import org.apache.struts.action.ActionForm;


public class NgayTrienKhaiForm extends ActionForm {
   boolean Empty;
   private String pageNumber;
   private String type;
   private String action_status;
   private String ma_kb;
   private String ma_nh;
   private String ma_nt;
   private String ngay_trien_khai;
   private String ghi_chu;
   private String id;
   private String ten_nhkb_nhan;
   private String id_nhkb_nhan;
   private String ten_nhkb_nhan1;

    public void setEmpty(boolean Empty) {
        this.Empty = Empty;
    }

    public boolean isEmpty() {
        return Empty;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getAction_status() {
        return action_status;
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

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }

   

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }


    public void setNgay_trien_khai(String ngay_trien_khai) {
        this.ngay_trien_khai = ngay_trien_khai;
    }

    public String getNgay_trien_khai() {
        return ngay_trien_khai;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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
