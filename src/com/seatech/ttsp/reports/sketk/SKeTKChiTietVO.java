package com.seatech.ttsp.reports.sketk;

import java.math.BigDecimal;

public class SKeTKChiTietVO {
    public SKeTKChiTietVO() {
        super();
    }
    private String id;
    private String sao_ke_id;
    private String ma_loai_gd;
    private String ngay_ps;
    private String tinh_chat_ps;
    private String loai_tien;
    private String tk_tham_chieu;
    private String noi_dung;
    
    private BigDecimal tong_ps_co;
    private BigDecimal so_tien;
    private BigDecimal tong_ps_no;
    private String loai_ps;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSao_ke_id(String sao_ke_id) {
        this.sao_ke_id = sao_ke_id;
    }

    public String getSao_ke_id() {
        return sao_ke_id;
    }

    public void setMa_loai_gd(String ma_loai_gd) {
        this.ma_loai_gd = ma_loai_gd;
    }

    public String getMa_loai_gd() {
        return ma_loai_gd;
    }

    public void setNgay_ps(String ngay_ps) {
        this.ngay_ps = ngay_ps;
    }

    public String getNgay_ps() {
        return ngay_ps;
    }

    public void setTinh_chat_ps(String tinh_chat_ps) {
        this.tinh_chat_ps = tinh_chat_ps;
    }

    public String getTinh_chat_ps() {
        return tinh_chat_ps;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }

    public void setTk_tham_chieu(String tk_tham_chieu) {
        this.tk_tham_chieu = tk_tham_chieu;
    }

    public String getTk_tham_chieu() {
        return tk_tham_chieu;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setLoai_ps(String loai_ps) {
        this.loai_ps = loai_ps;
    }

    public String getLoai_ps() {
        return loai_ps;
    }

    public void setTong_ps_co(BigDecimal tong_ps_co) {
        this.tong_ps_co = tong_ps_co;
    }

    public BigDecimal getTong_ps_co() {
        return tong_ps_co;
    }

    public void setSo_tien(BigDecimal so_tien) {
        this.so_tien = so_tien;
    }

    public BigDecimal getSo_tien() {
        return so_tien;
    }

    public void setTong_ps_no(BigDecimal tong_ps_no) {
        this.tong_ps_no = tong_ps_no;
    }

    public BigDecimal getTong_ps_no() {
        return tong_ps_no;
    }
}
