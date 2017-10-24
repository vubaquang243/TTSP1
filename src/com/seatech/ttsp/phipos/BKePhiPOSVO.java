package com.seatech.ttsp.phipos;

import java.math.BigDecimal;

public class BKePhiPOSVO {
    private String id;
    private String ma_nh;
    private String ma_kb;
    private String ma_dvcn_the;
    private String ten_dvcn_the;
    private String nguoi_tao;
    private String ngay_tao;
    private String nguoi_ks;
    private String ngay_ks;
    private String tu_ngay;
    private String den_ngay;
    private String so_tk;
    private BigDecimal phi;
    private BigDecimal vat;
    private BigDecimal phi_sau_vat;
    private String ngay_nhan;
    private String msg_id;

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

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setMa_dvcn_the(String ma_dvcn_the) {
        this.ma_dvcn_the = ma_dvcn_the;
    }

    public String getMa_dvcn_the() {
        return ma_dvcn_the;
    }

    public void setTen_dvcn_the(String ten_dvcn_the) {
        this.ten_dvcn_the = ten_dvcn_the;
    }

    public String getTen_dvcn_the() {
        return ten_dvcn_the;
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

    public void setNguoi_ks(String nguoi_ks) {
        this.nguoi_ks = nguoi_ks;
    }

    public String getNguoi_ks() {
        return nguoi_ks;
    }

    public void setNgay_ks(String ngay_ks) {
        this.ngay_ks = ngay_ks;
    }

    public String getNgay_ks() {
        return ngay_ks;
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

    public void setSo_tk(String so_tk) {
        this.so_tk = so_tk;
    }

    public String getSo_tk() {
        return so_tk;
    }

    public void setPhi(BigDecimal phi) {
        this.phi = phi;
    }

    public BigDecimal getPhi() {
        return phi;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setPhi_sau_vat(BigDecimal phi_sau_vat) {
        this.phi_sau_vat = phi_sau_vat;
    }

    public BigDecimal getPhi_sau_vat() {
        return phi_sau_vat;
    }

    public void setNgay_nhan(String ngay_nhan) {
        this.ngay_nhan = ngay_nhan;
    }

    public String getNgay_nhan() {
        return ngay_nhan;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }
}
