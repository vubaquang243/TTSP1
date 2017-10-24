package com.seatech.ttsp.dts;

import java.math.BigDecimal;


public class DTSoatVO {
    private String id;
    private String so_tchieu;
    private String ltt_id;
    private String dts_id;
    private Long nhkb_nhan;
    private Long nhkb_chuyen;
    private String noidung;
    private String noidung_traloi;
    private String thong_tin_khac;
    private Long ttv_nhan;
    private String ngay_nhan;
    private Long ktt_duyet;
    private String ngay_duyet;
    private String lydo_ktt_day_lai;
    private String trang_thai;
    private String nguoi_nhap_nh;
    private String ngay_nhap_nh;
    private String nguoi_ks_nh;
    private String ngay_ks_nh;
    private String chuky_ktt;
    private String loai_dts;
    //  out
    private String ma_ttv_nhan;
    private String ma_ktt;
    private String ten_ttv_nhan;
    private String ten_ktt_duyet;
    private String ngay_thanh_toan;
    private String ten_don_vi_tra_soat;
    private String ma_don_vi_tra_soat;
    private String ten_don_vi_nhan_tra_soat;
    private String ma_don_vi_nhan_tra_soat;
    private String mo_ta_trang_thai;
    private String ngay_lap_lenh;
    private String loai_tra_soat;
    private String chuc_danh;
    private Long nhkb_nhan_ltt;
    private Long nhkb_chuyen_ltt;
    private String msg_id;
    private Long tt_in;
    private String thoi_gian_keo_dai;
    private BigDecimal tong_sotien;
    private String loai_dien;
    private String ten_kb_tinh;
    private String ten_kb_huyen;
    private String ten_nh;
    private String ma_kb_huyen;
    private String ma_nh;    
    
    private String nh_tk_chuyen;
    private String nh_tk_nhan;
    private String noi_dung_ky;
    // hien thi noi dung loi 
    private String error_code;
    private String error_desc;
    private String di_den;
    private String loai_tien;
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


    public void setNhkb_nhan(Long nhkb_nhan) {
        this.nhkb_nhan = nhkb_nhan;
    }

    public Long getNhkb_nhan() {
        return nhkb_nhan;
    }

    public void setNhkb_chuyen(Long nhkb_chuyen) {
        this.nhkb_chuyen = nhkb_chuyen;
    }

    public Long getNhkb_chuyen() {
        return nhkb_chuyen;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung_traloi(String noidung_traloi) {
        this.noidung_traloi = noidung_traloi;
    }

    public String getNoidung_traloi() {
        return noidung_traloi;
    }

    public void setThong_tin_khac(String thong_tin_khac) {
        this.thong_tin_khac = thong_tin_khac;
    }

    public String getThong_tin_khac() {
        return thong_tin_khac;
    }

    public void setTtv_nhan(Long ttv_nhan) {
        this.ttv_nhan = ttv_nhan;
    }

    public Long getTtv_nhan() {
        return ttv_nhan;
    }


    public void setKtt_duyet(Long ktt_duyet) {
        this.ktt_duyet = ktt_duyet;
    }

    public Long getKtt_duyet() {
        return ktt_duyet;
    }


    public void setLydo_ktt_day_lai(String lydo_ktt_day_lai) {
        this.lydo_ktt_day_lai = lydo_ktt_day_lai;
    }

    public String getLydo_ktt_day_lai() {
        return lydo_ktt_day_lai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
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

    public void setNgay_nhan(String ngay_nhan) {
        this.ngay_nhan = ngay_nhan;
    }

    public String getNgay_nhan() {
        return ngay_nhan;
    }

    public void setNgay_duyet(String ngay_duyet) {
        this.ngay_duyet = ngay_duyet;
    }

    public String getNgay_duyet() {
        return ngay_duyet;
    }

    public void setNgay_thanh_toan(String ngay_thanh_toan) {
        this.ngay_thanh_toan = ngay_thanh_toan;
    }

    public String getNgay_thanh_toan() {
        return ngay_thanh_toan;
    }

    public void setTen_don_vi_tra_soat(String ten_don_vi_tra_soat) {
        this.ten_don_vi_tra_soat = ten_don_vi_tra_soat;
    }

    public String getTen_don_vi_tra_soat() {
        return ten_don_vi_tra_soat;
    }

    public void setMa_don_vi_tra_soat(String ma_don_vi_tra_soat) {
        this.ma_don_vi_tra_soat = ma_don_vi_tra_soat;
    }

    public String getMa_don_vi_tra_soat() {
        return ma_don_vi_tra_soat;
    }

    public void setTen_don_vi_nhan_tra_soat(String ten_don_vi_nhan_tra_soat) {
        this.ten_don_vi_nhan_tra_soat = ten_don_vi_nhan_tra_soat;
    }

    public String getTen_don_vi_nhan_tra_soat() {
        return ten_don_vi_nhan_tra_soat;
    }

    public void setMa_don_vi_nhan_tra_soat(String ma_don_vi_nhan_tra_soat) {
        this.ma_don_vi_nhan_tra_soat = ma_don_vi_nhan_tra_soat;
    }

    public String getMa_don_vi_nhan_tra_soat() {
        return ma_don_vi_nhan_tra_soat;
    }

    public void setMo_ta_trang_thai(String mo_ta_trang_thai) {
        this.mo_ta_trang_thai = mo_ta_trang_thai;
    }

    public String getMo_ta_trang_thai() {
        return mo_ta_trang_thai;
    }

    public void setMa_ttv_nhan(String ma_ttv_nhan) {
        this.ma_ttv_nhan = ma_ttv_nhan;
    }

    public String getMa_ttv_nhan() {
        return ma_ttv_nhan;
    }

    public void setMa_ktt(String ma_ktt) {
        this.ma_ktt = ma_ktt;
    }

    public String getMa_ktt() {
        return ma_ktt;
    }

    public void setNgay_lap_lenh(String ngay_lap_lenh) {
        this.ngay_lap_lenh = ngay_lap_lenh;
    }

    public String getNgay_lap_lenh() {
        return ngay_lap_lenh;
    }

    public void setLoai_tra_soat(String loai_tra_soat) {
        this.loai_tra_soat = loai_tra_soat;
    }

    public String getLoai_tra_soat() {
        return loai_tra_soat;
    }

    public void setChuky_ktt(String chuky_ktt) {
        this.chuky_ktt = chuky_ktt;
    }

    public String getChuky_ktt() {
        return chuky_ktt;
    }

    public String getTen_ttv_nhan() {
        return ten_ttv_nhan;
    }

    public void setTen_ttv_nhan(String ten_ttv_nhan) {
        this.ten_ttv_nhan = ten_ttv_nhan;
    }

    public String getTen_ktt_duyet() {
        return ten_ktt_duyet;
    }

    public void setTen_ktt_duyet(String ten_ktt_duyet) {
        this.ten_ktt_duyet = ten_ktt_duyet;
    }

    public void setLoai_dts(String loai_dts) {
        this.loai_dts = loai_dts;
    }

    public String getLoai_dts() {
        return loai_dts;
    }

    public String getChuc_danh() {
        return chuc_danh;
    }

    public void setChuc_danh(String chuc_danh) {
        this.chuc_danh = chuc_danh;
    }

    public void setNhkb_nhan_ltt(Long nhkb_nhan_ltt) {
        this.nhkb_nhan_ltt = nhkb_nhan_ltt;
    }

    public Long getNhkb_nhan_ltt() {
        return nhkb_nhan_ltt;
    }

    public void setNhkb_chuyen_ltt(Long nhkb_chuyen_ltt) {
        this.nhkb_chuyen_ltt = nhkb_chuyen_ltt;
    }

    public Long getNhkb_chuyen_ltt() {
        return nhkb_chuyen_ltt;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setLtt_id(String ltt_id) {
        this.ltt_id = ltt_id;
    }

    public String getLtt_id() {
        return ltt_id;
    }

    public void setDts_id(String dts_id) {
        this.dts_id = dts_id;
    }

    public String getDts_id() {
        return dts_id;
    }

    public void setTt_in(Long tt_in) {
        this.tt_in = tt_in;
    }

    public Long getTt_in() {
        return tt_in;
    }

    public void setThoi_gian_keo_dai(String thoi_gian_keo_dai) {
        this.thoi_gian_keo_dai = thoi_gian_keo_dai;
    }

    public String getThoi_gian_keo_dai() {
        return thoi_gian_keo_dai;
    }

    public void setSo_tchieu(String so_tchieu) {
        this.so_tchieu = so_tchieu;
    }

    public String getSo_tchieu() {
        return so_tchieu;
    }

    public void setTong_sotien(BigDecimal tong_sotien) {
        this.tong_sotien = tong_sotien;
    }

    public BigDecimal getTong_sotien() {
        return tong_sotien;
    }

    public void setLoai_dien(String loai_dien) {
        this.loai_dien = loai_dien;
    }

    public String getLoai_dien() {
        return loai_dien;
    }

    public void setTen_kb_tinh(String ten_kb_tinh) {
        this.ten_kb_tinh = ten_kb_tinh;
    }

    public String getTen_kb_tinh() {
        return ten_kb_tinh;
    }

    public void setTen_kb_huyen(String ten_kb_huyen) {
        this.ten_kb_huyen = ten_kb_huyen;
    }

    public String getTen_kb_huyen() {
        return ten_kb_huyen;
    }

    public void setTen_nh(String ten_nh) {
        this.ten_nh = ten_nh;
    }

    public String getTen_nh() {
        return ten_nh;
    }

    public void setMa_kb_huyen(String ma_kb_huyen) {
        this.ma_kb_huyen = ma_kb_huyen;
    }

    public String getMa_kb_huyen() {
        return ma_kb_huyen;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setNh_tk_chuyen(String nh_tk_chuyen) {
        this.nh_tk_chuyen = nh_tk_chuyen;
    }

    public String getNh_tk_chuyen() {
        return nh_tk_chuyen;
    }

    public void setNh_tk_nhan(String nh_tk_nhan) {
        this.nh_tk_nhan = nh_tk_nhan;
    }

    public String getNh_tk_nhan() {
        return nh_tk_nhan;
    }

    public void setNoi_dung_ky(String noi_dung_ky) {
        this.noi_dung_ky = noi_dung_ky;
    }

    public String getNoi_dung_ky() {
        return noi_dung_ky;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setDi_den(String di_den) {
        this.di_den = di_den;
    }

    public String getDi_den() {
        return di_den;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }
}
