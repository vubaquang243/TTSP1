package com.seatech.ttsp.lsutacnghiep.form;


import org.apache.struts.action.ActionForm;

public class LSuTacNghiepForm extends ActionForm {
    private String id;
    private String ltt_id;
    private String nhkb_nhan;
    private String nhkb_chuyen;
    private String nhan;
    private String ttv_nhan;
    private String ngay_nhan;
    private String ktv_chuyen;
    private String ktt_duyet;
    private String ngay_ktt_duyet;
    private String lydo_ktt_day_lai;

    private String gd_duyet;
    private String ngay_gd_duyet;
    private String lydo_gd_day_lai;
    private String so_ct_goc;
    private String ngay_ct;
    private String nt_id;
    private String so_yctt;
    private String ngay_tt;
    private String ndung_tt;
    private String tong_sotien;

    private String so_tk_chuyen;
    private String ten_tk_chuyen;
    private String ttin_kh_chuyen;
    private String id_nhkb_chuyen;
    private String ten_nhkb_chuyen;

    private String so_tk_nhan;
    private String ten_tk_nhan;
    private String ttin_kh_nhan;
    private String id_nhkb_nhan;
    private String ten_nhkb_nhan;

    private String trang_thai;
    private String loai_hach_toan;
    private String nguoi_nhap_nh;
    private String ngay_nhap_nh;
    private String nguoi_ks_nh;
    private String ngay_ks_nh;
    private String chuky_ktt;
    private String chuky_gd;
    private String tgian_ghi;
  
    private String ma_nhkb_chuyen;
    private String ma_nhkb_nhan;
    private String ma_nhkb_chuyen_cm;
    private String ma_nhkb_nhan_cm;
    private String ten_nhkb_chuyen_cm;
    private String ten_nhkb_nhan_cm;
    private String task;
    private String loai_lenh_tt;
    private String pageNumber;

    //Chuc them
    private String tu_ngay;
    private String den_ngay;
    
    private String ma_kb;
    private String ten_kb;
    private String id_kb;
    private String ma_nv;
    private String ma_ttt;
    private String ten_ktt_duyet;
    private String ma_ktt_duyet;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNhkb_nhan(String nhkb_nhan) {
        this.nhkb_nhan = nhkb_nhan;
    }

    public String getNhkb_nhan() {
        return nhkb_nhan;
    }

    public void setNhkb_chuyen(String nhkb_chuyen) {
        this.nhkb_chuyen = nhkb_chuyen;
    }

    public String getNhkb_chuyen() {
        return nhkb_chuyen;
    }

    public void setNhan(String nhan) {
        this.nhan = nhan;
    }

    public String getNhan() {
        return nhan;
    }

    public void setTtv_nhan(String ttv_nhan) {
        this.ttv_nhan = ttv_nhan;
    }

    public String getTtv_nhan() {
        return ttv_nhan;
    }

    public void setNgay_nhan(String ngay_nhan) {
        this.ngay_nhan = ngay_nhan;
    }

    public String getNgay_nhan() {
        return ngay_nhan;
    }

    public void setKtv_chuyen(String ktv_chuyen) {
        this.ktv_chuyen = ktv_chuyen;
    }

    public String getKtv_chuyen() {
        return ktv_chuyen;
    }

    public void setKtt_duyet(String ktt_duyet) {
        this.ktt_duyet = ktt_duyet;
    }

    public String getKtt_duyet() {
        return ktt_duyet;
    }

    public void setNgay_ktt_duyet(String ngay_ktt_duyet) {
        this.ngay_ktt_duyet = ngay_ktt_duyet;
    }

    public String getNgay_ktt_duyet() {
        return ngay_ktt_duyet;
    }

    public void setLydo_ktt_day_lai(String lydo_ktt_day_lai) {
        this.lydo_ktt_day_lai = lydo_ktt_day_lai;
    }

    public String getLydo_ktt_day_lai() {
        return lydo_ktt_day_lai;
    }

    public void setGd_duyet(String gd_duyet) {
        this.gd_duyet = gd_duyet;
    }

    public String getGd_duyet() {
        return gd_duyet;
    }

    public void setNgay_gd_duyet(String ngay_gd_duyet) {
        this.ngay_gd_duyet = ngay_gd_duyet;
    }

    public String getNgay_gd_duyet() {
        return ngay_gd_duyet;
    }

    public void setLydo_gd_day_lai(String lydo_gd_day_lai) {
        this.lydo_gd_day_lai = lydo_gd_day_lai;
    }

    public String getLydo_gd_day_lai() {
        return lydo_gd_day_lai;
    }

    public void setSo_ct_goc(String so_ct_goc) {
        this.so_ct_goc = so_ct_goc;
    }

    public String getSo_ct_goc() {
        return so_ct_goc;
    }

    public void setNgay_ct(String ngay_ct) {
        this.ngay_ct = ngay_ct;
    }

    public String getNgay_ct() {
        return ngay_ct;
    }

    public void setNt_id(String nt_id) {
        this.nt_id = nt_id;
    }

    public String getNt_id() {
        return nt_id;
    }

    public void setSo_yctt(String so_yctt) {
        this.so_yctt = so_yctt;
    }

    public String getSo_yctt() {
        return so_yctt;
    }

    public void setNgay_tt(String ngay_tt) {
        this.ngay_tt = ngay_tt;
    }

    public String getNgay_tt() {
        return ngay_tt;
    }

    public void setNdung_tt(String ndung_tt) {
        this.ndung_tt = ndung_tt;
    }

    public String getNdung_tt() {
        return ndung_tt;
    }

    public void setTong_sotien(String tong_sotien) {
        this.tong_sotien = tong_sotien;
    }

    public String getTong_sotien() {
        return tong_sotien;
    }

    public void setSo_tk_chuyen(String so_tk_chuyen) {
        this.so_tk_chuyen = so_tk_chuyen;
    }

    public String getSo_tk_chuyen() {
        return so_tk_chuyen;
    }

    public void setTen_tk_chuyen(String ten_tk_chuyen) {
        this.ten_tk_chuyen = ten_tk_chuyen;
    }

    public String getTen_tk_chuyen() {
        return ten_tk_chuyen;
    }

    public void setTtin_kh_chuyen(String ttin_kh_chuyen) {
        this.ttin_kh_chuyen = ttin_kh_chuyen;
    }

    public String getTtin_kh_chuyen() {
        return ttin_kh_chuyen;
    }

    public void setId_nhkb_chuyen(String id_nhkb_chuyen) {
        this.id_nhkb_chuyen = id_nhkb_chuyen;
    }

    public String getId_nhkb_chuyen() {
        return id_nhkb_chuyen;
    }

    public void setTen_nhkb_chuyen(String ten_nhkb_chuyen) {
        this.ten_nhkb_chuyen = ten_nhkb_chuyen;
    }

    public String getTen_nhkb_chuyen() {
        return ten_nhkb_chuyen;
    }

    public void setSo_tk_nhan(String so_tk_nhan) {
        this.so_tk_nhan = so_tk_nhan;
    }

    public String getSo_tk_nhan() {
        return so_tk_nhan;
    }

    public void setTen_tk_nhan(String ten_tk_nhan) {
        this.ten_tk_nhan = ten_tk_nhan;
    }

    public String getTen_tk_nhan() {
        return ten_tk_nhan;
    }

    public void setTtin_kh_nhan(String ttin_kh_nhan) {
        this.ttin_kh_nhan = ttin_kh_nhan;
    }

    public String getTtin_kh_nhan() {
        return ttin_kh_nhan;
    }

    public void setId_nhkb_nhan(String id_nhkb_nhan) {
        this.id_nhkb_nhan = id_nhkb_nhan;
    }

    public String getId_nhkb_nhan() {
        return id_nhkb_nhan;
    }

    public void setTen_nhkb_nhan(String ten_nhkb_nhan) {
        this.ten_nhkb_nhan = ten_nhkb_nhan;
    }

    public String getTen_nhkb_nhan() {
        return ten_nhkb_nhan;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setLoai_hach_toan(String loai_hach_toan) {
        this.loai_hach_toan = loai_hach_toan;
    }

    public String getLoai_hach_toan() {
        return loai_hach_toan;
    }


    public void setMa_nhkb_nhan(String ma_nhkb_nhan) {
        this.ma_nhkb_nhan = ma_nhkb_nhan;
    }

    public String getMa_nhkb_nhan() {
        return ma_nhkb_nhan;
    }

    public void setMa_nhkb_chuyen(String ma_nhkb_chuyen) {
        this.ma_nhkb_chuyen = ma_nhkb_chuyen;
    }

    public String getMa_nhkb_chuyen() {
        return ma_nhkb_chuyen;
    }

    public void setTen_nhkb_chuyen_cm(String ten_nhkb_chuyen_cm) {
        this.ten_nhkb_chuyen_cm = ten_nhkb_chuyen_cm;
    }

    public String getTen_nhkb_chuyen_cm() {
        return ten_nhkb_chuyen_cm;
    }

    public void setTen_nhkb_nhan_cm(String ten_nhkb_nhan_cm) {
        this.ten_nhkb_nhan_cm = ten_nhkb_nhan_cm;
    }

    public String getTen_nhkb_nhan_cm() {
        return ten_nhkb_nhan_cm;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setNguoi_nhap_nh(String nguoi_nhap_nh) {
        this.nguoi_nhap_nh = nguoi_nhap_nh;
    }

    public String getNguoi_nhap_nh() {
        return nguoi_nhap_nh;
    }

    public void setNgay_nhap_nh(String ngay_nhap_nh) {
        this.ngay_nhap_nh = ngay_nhap_nh;
    }

    public String getNgay_nhap_nh() {
        return ngay_nhap_nh;
    }

    public void setNguoi_ks_nh(String nguoi_ks_nh) {
        this.nguoi_ks_nh = nguoi_ks_nh;
    }

    public String getNguoi_ks_nh() {
        return nguoi_ks_nh;
    }

    public void setNgay_ks_nh(String ngay_ks_nh) {
        this.ngay_ks_nh = ngay_ks_nh;
    }

    public String getNgay_ks_nh() {
        return ngay_ks_nh;
    }

    public void setMa_nhkb_chuyen_cm(String ma_nhkb_chuyen_cm) {
        this.ma_nhkb_chuyen_cm = ma_nhkb_chuyen_cm;
    }

    public String getMa_nhkb_chuyen_cm() {
        return ma_nhkb_chuyen_cm;
    }

    public void setMa_nhkb_nhan_cm(String ma_nhkb_nhan_cm) {
        this.ma_nhkb_nhan_cm = ma_nhkb_nhan_cm;
    }

    public String getMa_nhkb_nhan_cm() {
        return ma_nhkb_nhan_cm;
    }

    public void setLoai_lenh_tt(String loai_lenh_tt) {
        this.loai_lenh_tt = loai_lenh_tt;
    }

    public String getLoai_lenh_tt() {
        return loai_lenh_tt;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
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

    public void setLtt_id(String ltt_id) {
        this.ltt_id = ltt_id;
    }

    public String getLtt_id() {
        return ltt_id;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setMa_nv(String ma_nv) {
        this.ma_nv = ma_nv;
    }

    public String getMa_nv() {
        return ma_nv;
    }

    public void setChuky_ktt(String chuky_ktt) {
        this.chuky_ktt = chuky_ktt;
    }

    public String getChuky_ktt() {
        return chuky_ktt;
    }

    public void setChuky_gd(String chuky_gd) {
        this.chuky_gd = chuky_gd;
    }

    public String getChuky_gd() {
        return chuky_gd;
    }

    public void setTgian_ghi(String tgian_ghi) {
        this.tgian_ghi = tgian_ghi;
    }

    public String getTgian_ghi() {
        return tgian_ghi;
    }

    public void setTen_kb(String ten_kb) {
        this.ten_kb = ten_kb;
    }

    public String getTen_kb() {
        return ten_kb;
    }

    public void setId_kb(String id_kb) {
        this.id_kb = id_kb;
    }

    public String getId_kb() {
        return id_kb;
    }

    public void setMa_ttt(String ma_ttt) {
        this.ma_ttt = ma_ttt;
    }

    public String getMa_ttt() {
        return ma_ttt;
    }

    public void setTen_ktt_duyet(String ten_ktt_duyet) {
        this.ten_ktt_duyet = ten_ktt_duyet;
    }

    public String getTen_ktt_duyet() {
        return ten_ktt_duyet;
    }

    public void setMa_ktt_duyet(String ma_ktt_duyet) {
        this.ma_ktt_duyet = ma_ktt_duyet;
    }

    public String getMa_ktt_duyet() {
        return ma_ktt_duyet;
    }
}

