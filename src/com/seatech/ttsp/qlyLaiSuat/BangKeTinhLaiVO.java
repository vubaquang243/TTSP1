package com.seatech.ttsp.qlyLaiSuat;

import java.math.BigDecimal;

public class BangKeTinhLaiVO {
    private String id;
    private String ma_kb;
    private String ma_nh;
    private String so_tk;
    private String loai_tien;
    private String nguoi_tao;
    private String ngay_tao;
    private String ngay;
    private long so_du_cuoi;
    private double lai_suat;
    private String loai_lai_suat;
    private long lai;
    private BigDecimal ty_gia;
    private String ghi_chu;
    private String tinh_trang;
	//Bo sung truong loai tai khoan
    private String loai_tk;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setSo_tk(String so_tk) {
        this.so_tk = so_tk;
    }

    public String getSo_tk() {
        return so_tk;
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

    public void setNgay_tao(String ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public String getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getNgay() {
        return ngay;
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

    public void setTinh_trang(String tinh_trang) {
        this.tinh_trang = tinh_trang;
    }

    public String getTinh_trang() {
        return tinh_trang;
    }

    public void setSo_du_cuoi(long so_du_cuoi) {
        this.so_du_cuoi = so_du_cuoi;
    }

    public long getSo_du_cuoi() {
        return so_du_cuoi;
    }

    public void setLai(long lai) {
        this.lai = lai;
    }

    public long getLai() {
        return lai;
    }

    public void setLai_suat(double lai_suat) {
        this.lai_suat = lai_suat;
    }

    public double getLai_suat() {
        return lai_suat;
    }

    public void setTy_gia(BigDecimal ty_gia) {
        this.ty_gia = ty_gia;
    }

    public BigDecimal getTy_gia() {
        return ty_gia;
    }

    public void setLoai_tk(String loai_tk) {
        this.loai_tk = loai_tk;
    }

    public String getLoai_tk() {
        return loai_tk;
    }
}
