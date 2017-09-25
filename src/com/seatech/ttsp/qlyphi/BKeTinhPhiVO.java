package com.seatech.ttsp.qlyphi;

import java.math.BigDecimal;
//LOAI_GD,LTT_ID,SO_YCTT,TO_CHAR(NGAY_TT,'DD/MM/YYYY') NGAY_TT,SO_TIEN,MUC_PHI,TIEN_PHI,NVL(VAT,'0') VAT
public class BKeTinhPhiVO {
    private String ngay_tt;
    private long so_ltt;
    private BigDecimal so_tien;
    private String muc_phi;
    private BigDecimal phi;
    private String loai_phi;
    
    private String loai_gd;
    private String ltt_id;
    private String so_yctt;
    private BigDecimal tien_phi;
    private String vat;
    private String gio_phan_hoi;
    private String tong_phi_nguyen_te;
    private BigDecimal phi_nguyen_te;

    public void setSo_ltt(long so_ltt) {
        this.so_ltt = so_ltt;
    }

    public long getSo_ltt() {
        return so_ltt;
    }

    public void setNgay_tt(String ngay_tt) {
        this.ngay_tt = ngay_tt;
    }

    public String getNgay_tt() {
        return ngay_tt;
    }

    public void setMuc_phi(String muc_phi) {
        this.muc_phi = muc_phi;
    }

    public String getMuc_phi() {
        return muc_phi;
    }

    public void setLoai_gd(String loai_gd) {
        this.loai_gd = loai_gd;
    }

    public String getLoai_gd() {
        return loai_gd;
    }

    public void setLtt_id(String ltt_id) {
        this.ltt_id = ltt_id;
    }

    public String getLtt_id() {
        return ltt_id;
    }

    public void setSo_yctt(String so_yctt) {
        this.so_yctt = so_yctt;
    }

    public String getSo_yctt() {
        return so_yctt;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getVat() {
        return vat;
    }

    public void setGio_phan_hoi(String gio_phan_hoi) {
        this.gio_phan_hoi = gio_phan_hoi;
    }

    public String getGio_phan_hoi() {
        return gio_phan_hoi;
    }

    public void setSo_tien(BigDecimal so_tien) {
        this.so_tien = so_tien;
    }

    public BigDecimal getSo_tien() {
        return so_tien;
    }

    public void setPhi(BigDecimal phi) {
        this.phi = phi;
    }

    public BigDecimal getPhi() {
        return phi;
    }

    public void setTien_phi(BigDecimal tien_phi) {
        this.tien_phi = tien_phi;
    }

    public BigDecimal getTien_phi() {
        return tien_phi;
    }

    public void setTong_phi_nguyen_te(String tong_phi_nguyen_te) {
        this.tong_phi_nguyen_te = tong_phi_nguyen_te;
    }

    public String getTong_phi_nguyen_te() {
        return tong_phi_nguyen_te;
    }

    public void setLoai_phi(String loai_phi) {
        this.loai_phi = loai_phi;
    }

    public String getLoai_phi() {
        return loai_phi;
    }

    public void setPhi_nguyen_te(BigDecimal phi_nguyen_te) {
        this.phi_nguyen_te = phi_nguyen_te;
    }

    public BigDecimal getPhi_nguyen_te() {
        return phi_nguyen_te;
    }
}
