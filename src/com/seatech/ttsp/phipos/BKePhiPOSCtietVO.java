package com.seatech.ttsp.phipos;

import java.math.BigDecimal;

public class BKePhiPOSCtietVO {
    private String mt_id;
    private String ngay_gd;
    private String tong_so_ct;
    private BigDecimal tong_so_tien;
    private BigDecimal muc_phi;
    private BigDecimal phi;
    private String du_phong;

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }

    public String getMt_id() {
        return mt_id;
    }

    public void setNgay_gd(String ngay_gd) {
        this.ngay_gd = ngay_gd;
    }

    public String getNgay_gd() {
        return ngay_gd;
    }

    public void setTong_so_ct(String tong_so_ct) {
        this.tong_so_ct = tong_so_ct;
    }

    public String getTong_so_ct() {
        return tong_so_ct;
    }

    public void setTong_so_tien(BigDecimal tong_so_tien) {
        this.tong_so_tien = tong_so_tien;
    }

    public BigDecimal getTong_so_tien() {
        return tong_so_tien;
    }

    public void setMuc_phi(BigDecimal muc_phi) {
        this.muc_phi = muc_phi;
    }

    public BigDecimal getMuc_phi() {
        return muc_phi;
    }

    public void setPhi(BigDecimal phi) {
        this.phi = phi;
    }

    public BigDecimal getPhi() {
        return phi;
    }

    public void setDu_phong(String du_phong) {
        this.du_phong = du_phong;
    }

    public String getDu_phong() {
        return du_phong;
    }
}
