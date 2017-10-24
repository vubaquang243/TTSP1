package com.seatech.ttsp.qlyLaiSuat;

import java.util.Date;

public class LaiSuatVO {
    private String id;
    private String he_thong_nh;
    private String ngay_hieu_luc;
    private String ngay_het_hieu_luc;
    private String loai_lai_suat;
    private String lai_suat;
    private String nguoi_tao;
    private String ghi_chu;
    private Date ngay_tao;
    private String loai_tien;


    public void setHe_thong_nh(String he_thong_nh) {
        this.he_thong_nh = he_thong_nh;
    }

    public String getHe_thong_nh() {
        return he_thong_nh;
    }

    public void setLoai_lai_suat(String loai_lai_suat) {
        this.loai_lai_suat = loai_lai_suat;
    }

    public String getLoai_lai_suat() {
        return loai_lai_suat;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public Date getNgay_tao() {
        return ngay_tao;
    }

    public void setLai_suat(String lai_suat) {
        this.lai_suat = lai_suat;
    }

    public String getLai_suat() {
        return lai_suat;
    }

    public void setNgay_hieu_luc(String ngay_hieu_luc) {
        this.ngay_hieu_luc = ngay_hieu_luc;
    }

    public String getNgay_hieu_luc() {
        return ngay_hieu_luc;
    }

    public void setNgay_het_hieu_luc(String ngay_het_hieu_luc) {
        this.ngay_het_hieu_luc = ngay_het_hieu_luc;
    }

    public String getNgay_het_hieu_luc() {
        return ngay_het_hieu_luc;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }

    public void setNguoi_tao(String nguoi_tao) {
        this.nguoi_tao = nguoi_tao;
    }

    public String getNguoi_tao() {
        return nguoi_tao;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
