package com.seatech.ttsp.dts.form;


import org.apache.struts.action.ActionForm;


public class DTSoatForm extends ActionForm {
    private String id;
    private String ltt_id;
    private String dts_id;
    private String nhkb_nhan;
    private String nhkb_chuyen;
    private String noidung;
    private String noidung_traloi;
    private String thong_tin_khac;
    private String ttv_nhan;
    private String ngay_nhan;
    private String ktt_duyet;
    private String ngay_duyet;
    private String lydo_ktt_day_lai;
    private String trang_thai;
    private String nguoi_nhap_nh;
    private String ngay_nhap_nh;
    private String nguoi_ks_nh;
    private String ngay_ks_nh;
    private String ngay_thanh_toan;
    private String ten_don_vi_tra_soat;
    private String ma_don_vi_tra_soat;
    private String ten_don_vi_nhan_tra_soat;
    private String ma_don_vi_nhan_tra_soat;
    private String ma_ttv_nhan;
    private String ma_ktt;
    private String loai_ltt;
    private Boolean search_dts;
    private String mo_ta_trang_thai;
    private String ngay_lap_lenh;
    private String loai_tra_soat;
    private String tu_ngay_lap_dien;
    private String den_ngay_lap_dien;
    private String tu_ngay_lap_lenh;
    private String den_ngay_lap_lenh;
    private String pageNumber;
    private String loai_dts;
    private String chieu_dts;
    private String nhkb_nhan_ltt;
    private String nhkb_chuyen_ltt;
    private String ma_nsd;
    private String ten_nsd;
    private String msg_id;
    private String thoi_gian_keo_dai;
    private String gio_keo_dai;
    private String phut_keo_dai;
    private String ten_ttv_nhan;
    private String ten_ktt_duyet;
    private String so_tchieu;
    private String chuky_ktt;
    private String noi_dung_ky;
    private String certSerial;
    private String signature;
    private String error_des;
    
  private String kb_tinh;
  private String kb_huyen;
  private String di_den;
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setKtt_duyet(String ktt_duyet) {
        this.ktt_duyet = ktt_duyet;
    }

    public String getKtt_duyet() {
        return ktt_duyet;
    }

    public void setNgay_duyet(String ngay_duyet) {
        this.ngay_duyet = ngay_duyet;
    }

    public String getNgay_duyet() {
        return ngay_duyet;
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

    public void setLoai_ltt(String loai_ltt) {
        this.loai_ltt = loai_ltt;
    }

    public String getLoai_ltt() {
        return loai_ltt;
    }

    public void setSearch_dts(Boolean search_dts) {
        this.search_dts = search_dts;
    }

    public Boolean getSearch_dts() {
        return search_dts;
    }

    public void setMo_ta_trang_thai(String mo_ta_trang_thai) {
        this.mo_ta_trang_thai = mo_ta_trang_thai;
    }

    public String getMo_ta_trang_thai() {
        return mo_ta_trang_thai;
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

    public void setTu_ngay_lap_dien(String tu_ngay_lap_dien) {
        this.tu_ngay_lap_dien = tu_ngay_lap_dien;
    }

    public String getTu_ngay_lap_dien() {
        return tu_ngay_lap_dien;
    }

    public void setDen_ngay_lap_dien(String den_ngay_lap_dien) {
        this.den_ngay_lap_dien = den_ngay_lap_dien;
    }

    public String getDen_ngay_lap_dien() {
        return den_ngay_lap_dien;
    }

    public void setTu_ngay_lap_lenh(String tu_ngay_lap_lenh) {
        this.tu_ngay_lap_lenh = tu_ngay_lap_lenh;
    }

    public String getTu_ngay_lap_lenh() {
        return tu_ngay_lap_lenh;
    }

    public void setDen_ngay_lap_lenh(String den_ngay_lap_lenh) {
        this.den_ngay_lap_lenh = den_ngay_lap_lenh;
    }

    public String getDen_ngay_lap_lenh() {
        return den_ngay_lap_lenh;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setLoai_dts(String loai_dts) {
        this.loai_dts = loai_dts;
    }

    public String getLoai_dts() {
        return loai_dts;
    }

    public void setNhkb_nhan_ltt(String nhkb_nhan_ltt) {
        this.nhkb_nhan_ltt = nhkb_nhan_ltt;
    }

    public String getNhkb_nhan_ltt() {
        return nhkb_nhan_ltt;
    }

    public void setNhkb_chuyen_ltt(String nhkb_chuyen_ltt) {
        this.nhkb_chuyen_ltt = nhkb_chuyen_ltt;
    }

    public String getNhkb_chuyen_ltt() {
        return nhkb_chuyen_ltt;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }

    public void setTen_nsd(String ten_nsd) {
        this.ten_nsd = ten_nsd;
    }

    public String getTen_nsd() {
        return ten_nsd;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setChieu_dts(String chieu_dts) {
        this.chieu_dts = chieu_dts;
    }

    public String getChieu_dts() {
        return chieu_dts;
    }

    public void setThoi_gian_keo_dai(String thoi_gian_keo_dai) {
        this.thoi_gian_keo_dai = thoi_gian_keo_dai;
    }

    public String getThoi_gian_keo_dai() {
        return thoi_gian_keo_dai;
    }

    public void setGio_keo_dai(String gio_keo_dai) {
        this.gio_keo_dai = gio_keo_dai;
    }

    public String getGio_keo_dai() {
        return gio_keo_dai;
    }

    public void setPhut_keo_dai(String phut_keo_dai) {
        this.phut_keo_dai = phut_keo_dai;
    }

    public String getPhut_keo_dai() {
        return phut_keo_dai;
    }

    public void setTen_ttv_nhan(String ten_ttv_nhan) {
        this.ten_ttv_nhan = ten_ttv_nhan;
    }

    public String getTen_ttv_nhan() {
        return ten_ttv_nhan;
    }

    public void setTen_ktt_duyet(String ten_ktt_duyet) {
        this.ten_ktt_duyet = ten_ktt_duyet;
    }

    public String getTen_ktt_duyet() {
        return ten_ktt_duyet;
    }

    public void setSo_tchieu(String so_tchieu) {
        this.so_tchieu = so_tchieu;
    }

    public String getSo_tchieu() {
        return so_tchieu;
    }

    public void setChuky_ktt(String chuky_ktt) {
        this.chuky_ktt = chuky_ktt;
    }

    public String getChuky_ktt() {
        return chuky_ktt;
    }

    public void setNoi_dung_ky(String noi_dung_ky) {
        this.noi_dung_ky = noi_dung_ky;
    }

    public String getNoi_dung_ky() {
        return noi_dung_ky;
    }

    public void setCertSerial(String certSerial) {
        this.certSerial = certSerial;
    }

    public String getCertSerial() {
        return certSerial;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setError_des(String error_des) {
        this.error_des = error_des;
    }

    public String getError_des() {
        return error_des;
    }

    public void setKb_tinh(String kb_tinh) {
        this.kb_tinh = kb_tinh;
    }

    public String getKb_tinh() {
        return kb_tinh;
    }

    public void setKb_huyen(String kb_huyen) {
        this.kb_huyen = kb_huyen;
    }

    public String getKb_huyen() {
        return kb_huyen;
    }

    public void setDi_den(String di_den) {
        this.di_den = di_den;
    }

    public String getDi_den() {
        return di_den;
    }
}
