package com.seatech.ttsp.onlinettinttoan;

import java.math.BigDecimal;

import java.util.Date;


public class OnlineTTinTToanVO {
    private Long id;
    private String msg_refid;
    private Long nhkb_chuyen;
    private Long nhkb_nhan;
    private Date ngay_tao;
    private String ma_kb;
    private String ten_kb;
    private String loai_tk;
    private String so_tk;
    private String ma_nh;
    private String ten_nh;
    private BigDecimal han_muc_no;
    private BigDecimal du_dau_ngay;
    private BigDecimal ps_thu;
    private BigDecimal ps_chi;
    private BigDecimal chenh_lech;
    private Date ngay_gui;
    private String msg_id;
    private String ma_nt;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMsg_refid(String msg_refid) {
        this.msg_refid = msg_refid;
    }

    public String getMsg_refid() {
        return msg_refid;
    }

    public void setNhkb_chuyen(Long nhkb_chuyen) {
        this.nhkb_chuyen = nhkb_chuyen;
    }

    public Long getNhkb_chuyen() {
        return nhkb_chuyen;
    }

    public void setNhkb_nhan(Long nhkb_nhan) {
        this.nhkb_nhan = nhkb_nhan;
    }

    public Long getNhkb_nhan() {
        return nhkb_nhan;
    }

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public Date getNgay_tao() {
        return ngay_tao;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setTen_kb(String ten_kb) {
        this.ten_kb = ten_kb;
    }

    public String getTen_kb() {
        return ten_kb;
    }

    public void setLoai_tk(String loai_tk) {
        this.loai_tk = loai_tk;
    }

    public String getLoai_tk() {
        return loai_tk;
    }

    public void setSo_tk(String so_tk) {
        this.so_tk = so_tk;
    }

    public String getSo_tk() {
        return so_tk;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setTen_nh(String ten_nh) {
        this.ten_nh = ten_nh;
    }

    public String getTen_nh() {
        return ten_nh;
    }

    public void setHan_muc_no(BigDecimal han_muc_no) {
        this.han_muc_no = han_muc_no;
    }

    public BigDecimal getHan_muc_no() {
        return han_muc_no;
    }

    public void setDu_dau_ngay(BigDecimal du_dau_ngay) {
        this.du_dau_ngay = du_dau_ngay;
    }

    public BigDecimal getDu_dau_ngay() {
        return du_dau_ngay;
    }

    public void setPs_thu(BigDecimal ps_thu) {
        this.ps_thu = ps_thu;
    }

    public BigDecimal getPs_thu() {
        return ps_thu;
    }

    public void setPs_chi(BigDecimal ps_chi) {
        this.ps_chi = ps_chi;
    }

    public BigDecimal getPs_chi() {
        return ps_chi;
    }

    public void setChenh_lech(BigDecimal chenh_lech) {
        this.chenh_lech = chenh_lech;
    }

    public BigDecimal getChenh_lech() {
        return chenh_lech;
    }

    public void setNgay_gui(Date ngay_gui) {
        this.ngay_gui = ngay_gui;
    }

    public Date getNgay_gui() {
        return ngay_gui;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }
}
