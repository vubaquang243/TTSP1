package com.seatech.ttsp.quyennhap;

import java.util.Date;

public class QuyenNhapThuCongVO {
    private Long id;
    private Long nsd_id;
    private Date tu_ngay;
    private Date den_ngay;
    private Long nguoi_gan;
    private String lido_gan;
    private Long nguoi_rut;
    private String lido_rut;
    private String ten_nsd;
    private String ma_nsd;
    private String trang_thai_chon;
    
    private String ten_nguoi_gan;
  private String ten_nguoi_rut;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNsd_id(Long nsd_id) {
        this.nsd_id = nsd_id;
    }

    public Long getNsd_id() {
        return nsd_id;
    }

    public void setTu_ngay(Date tu_ngay) {
        this.tu_ngay = tu_ngay;
    }

    public Date getTu_ngay() {
        return tu_ngay;
    }

    public void setDen_ngay(Date den_ngay) {
        this.den_ngay = den_ngay;
    }

    public Date getDen_ngay() {
        return den_ngay;
    }

    public void setNguoi_gan(Long nguoi_gan) {
        this.nguoi_gan = nguoi_gan;
    }

    public Long getNguoi_gan() {
        return nguoi_gan;
    }

    public void setLido_gan(String lido_gan) {
        this.lido_gan = lido_gan;
    }

    public String getLido_gan() {
        return lido_gan;
    }

    public void setNguoi_rut(Long nguoi_rut) {
        this.nguoi_rut = nguoi_rut;
    }

    public Long getNguoi_rut() {
        return nguoi_rut;
    }

    public void setLido_rut(String lido_rut) {
        this.lido_rut = lido_rut;
    }

    public String getLido_rut() {
        return lido_rut;
    }

    public void setTen_nsd(String ten_nsd) {
        this.ten_nsd = ten_nsd;
    }

    public String getTen_nsd() {
        return ten_nsd;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }

    public void setTrang_thai_chon(String trang_thai_chon) {
        this.trang_thai_chon = trang_thai_chon;
    }

    public String getTrang_thai_chon() {
        return trang_thai_chon;
    }

    public void setTen_nguoi_gan(String ten_nguoi_gan) {
        this.ten_nguoi_gan = ten_nguoi_gan;
    }

    public String getTen_nguoi_gan() {
        return ten_nguoi_gan;
    }

    public void setTen_nguoi_rut(String ten_nguoi_rut) {
        this.ten_nguoi_rut = ten_nguoi_rut;
    }

    public String getTen_nguoi_rut() {
        return ten_nguoi_rut;
    }
}
