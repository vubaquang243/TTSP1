package com.seatech.ttsp.thamso.form;

import org.apache.struts.action.ActionForm;
    /* HungBM: edit
     * Date: 16/10/2016
     * Them 2 tham so nhkb_huyen va nhkb_tinh
     * */
public class ThamSoHThongForm extends ActionForm {
    private String ten_ts;
    private String mo_ta;
    private String id;
    private String pageNumber;
    private String giatri_ts;
    private String giatri_ts_moi;
    private String cho_phep_sua;
    private String kb_id;
    private String ngay_cap_nhat;
    private String tu_ngay;
    private String den_ngay;
    
    private String nhkb_tinh;//Ngan hang kho bac tinh
    private String nhkb_huyen;//Ngan hang kho bac huyen
    private String idxKB;////index combox KB, phuc vu back trang


    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setGiatri_ts(String giatri_ts) {
        this.giatri_ts = giatri_ts;
    }

    public String getGiatri_ts() {
        return giatri_ts;
    }

    public void setCho_phep_sua(String cho_phep_sua) {
        this.cho_phep_sua = cho_phep_sua;
    }

    public String getCho_phep_sua() {
        return cho_phep_sua;
    }

    public void setKb_id(String kb_id) {
        this.kb_id = kb_id;
    }

    public String getKb_id() {
        return kb_id;
    }

    public void setNgay_cap_nhat(String ngay_cap_nhat) {
        this.ngay_cap_nhat = ngay_cap_nhat;
    }

    public String getNgay_cap_nhat() {
        return ngay_cap_nhat;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setTen_ts(String ten_ts) {
        this.ten_ts = ten_ts;
    }

    public String getTen_ts() {
        return ten_ts;
    }

    public void setGiatri_ts_moi(String giatri_ts_moi) {
        this.giatri_ts_moi = giatri_ts_moi;
    }

    public String getGiatri_ts_moi() {
        return giatri_ts_moi;
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

    public void setIdxKB(String idxKB) {
        this.idxKB = idxKB;
    }

    public String getIdxKB() {
        return idxKB;
    }
}
