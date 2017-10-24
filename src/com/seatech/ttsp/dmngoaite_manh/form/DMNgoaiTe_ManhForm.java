package com.seatech.ttsp.dmngoaite_manh.form;


import org.apache.struts.action.ActionForm;

public class DMNgoaiTe_ManhForm extends ActionForm {
    private boolean Empty;
    private String pageNumber;
    private String action_status;
    private String ma_nsd;
    private String id;
    private String ma;
    private String nguoi_tao;
    private String ngay_tao;
    private String ngay_hieu_luc;
    private String nguoi_ngung_hd;
    private String ngay_het_hieu_luc;
    private String trang_thai;
    private String nt;
    private String nhd;

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

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getAction_status() {
        return action_status;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public void setNguoi_tao(String nguoi_tao) {
        this.nguoi_tao = nguoi_tao;
    }

    public String getNguoi_tao() {
        return nguoi_tao;
    }

    public void setNgay_tao(String ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public String getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_hieu_luc(String ngay_hieu_luc) {
        this.ngay_hieu_luc = ngay_hieu_luc;
    }

    public String getNgay_hieu_luc() {
        return ngay_hieu_luc;
    }

    public void setNguoi_ngung_hd(String nguoi_ngung_hd) {
        this.nguoi_ngung_hd = nguoi_ngung_hd;
    }

    public String getNguoi_ngung_hd() {
        return nguoi_ngung_hd;
    }

    public void setNgay_het_hieu_luc(String ngay_het_hieu_luc) {
        this.ngay_het_hieu_luc = ngay_het_hieu_luc;
    }

    public String getNgay_het_hieu_luc() {
        return ngay_het_hieu_luc;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    public String getNt() {
        return nt;
    }

    public void setNhd(String nhd) {
        this.nhd = nhd;
    }

    public String getNhd() {
        return nhd;
    }
}
