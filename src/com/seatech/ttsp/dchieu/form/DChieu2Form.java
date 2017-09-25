package com.seatech.ttsp.dchieu.form;

import com.seatech.framework.strustx.AppForm;

public class DChieu2Form extends AppForm {
  private String id;
  private String nhkb_chuyen;
  private String nhkb_nhan;
  private String ngay_htoan;
  private String ngay_qtoan;
  private String ngay_insert;
  private String loai_qtoan;
  private String qtoan_dvi;
  private String so_tchieu;
  private String nguoi_nhap_nh;
  private String ngay_nhap_nh;
  private String nguoi_ks_nh;
  private String ngay_ks_nh;
  private String ndung_tt;
  private Long so_tien;
  private String ma_nt;
  private Long ttv_chuyen_ks;
  private String ngay_chuyen_ks;
  private Long ktt_ks;
  private String ngay_ks;
  private String chu_ky;
  private String tk_chuyen;
  private String ma_nh_chuyen;
  private String ten_nh_chuyen;
  private String ten_kh_chuyen;
  private String tt_kh_chuyen;
  private String tk_nhan;
  private String ma_nh_nhan;
  private String ten_nh_nhan;
  private String ten_kh_nhan;
  private String tt_kh_nhan;
  private Long tt_in;
  private String trang_thai;
  private String err_code;
  private String err_desc;
  private String msg_id;
  private String mo_ta_trang_thai;
  private String chuc_danh;
  private String ten_don_vi_chuyen;
  private String ten_don_vi_nhan;
  private String ma_tchieu_gd;
  private String ldo_hach_toan;
  private String ldo_day_lai;
  private String loai_hach_toan;
  private String ten_ttv_hoanthien;
  private String ten_ktt_hoanthien;
  private String ma_ttv_hoanthien;
  private String ma_ktt_hoanthien;
  // them
  private String so_bk;
  private String ngay_gui;
  private String ma_kb;
  private String id_ref;
  private String ten;
  
  private long ket_chuyen_thu;
  private long ket_chuyen_chi;
  private long ket_chuyen_thu_nh;
  private long ket_chuyen_chi_nh;
  
  private String bk_id;
  private String ket_qua;
  private String lan_dc;
  private String ngay_dc;
  private String send_bank;
  private String receive_bank;
  private String created_date;

  private String insert_date;
  private String ket_qua_pht;

  private String ltt_kb_thua;
  private String ltt_kb_thieu;
  private String dts_kb_thieu;
  private String dts_kb_thua;

  private long sotien_thu;
  private long sotien_chi;
  private long sotien_dtu;
  private long sotien_tcong;
  private long sodu_daungay;
  private long so_du;
  private long sodu_kbnn;
  private long chenh_lech;
  
  private String trang_thai_kqdc;
  private String trang_thai_bkdc;
  private long so_tien_thu_dtu;
  
  private long so_tien_thu_tcong;
  private long so_mon_thu_tcong;
  
  private long mon_thu_tcong_kbnn;
  private long mon_chi_tcong_kbnn;
  private long tien_thu_tcong_kbnn;
  private long tien_chi_tcong_kbnn;
  private long chenhlech_thu;
  private long chenhlech_chi;
  
  private String app_type;
  
  private long somon_tcong;
  private long somon_thu;
  private long somon_chi;
  private long somon_dtu;
  private long so_mon_thu_dtu;
  private long so_ketchuyen;
  private long mon_thu_dtu_kbnn;
  private long mon_chi_dtu_kbnn;
  private long tien_thu_dtu_kbnn;
  private long tien_chi_dtu_kbnn;
  
  private long so_du_dau_ngay;
  private long chenh_sdu_dngay;
  private long chenh_kchuyen_thu;
  private long chenh_kchuyen_chi;
  private long ps_chi; //  so ket chuyen chi
  
  private String trang_thai_kq;
  
  private String qtoan_id; 
  
  

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNhkb_chuyen(String nhkb_chuyen) {
        this.nhkb_chuyen = nhkb_chuyen;
    }

    public String getNhkb_chuyen() {
        return nhkb_chuyen;
    }

    public void setNhkb_nhan(String nhkb_nhan) {
        this.nhkb_nhan = nhkb_nhan;
    }

    public String getNhkb_nhan() {
        return nhkb_nhan;
    }

    public void setNgay_htoan(String ngay_htoan) {
        this.ngay_htoan = ngay_htoan;
    }

    public String getNgay_htoan() {
        return ngay_htoan;
    }

    public void setNgay_qtoan(String ngay_qtoan) {
        this.ngay_qtoan = ngay_qtoan;
    }

    public String getNgay_qtoan() {
        return ngay_qtoan;
    }

    public void setNgay_insert(String ngay_insert) {
        this.ngay_insert = ngay_insert;
    }

    public String getNgay_insert() {
        return ngay_insert;
    }

    public void setLoai_qtoan(String loai_qtoan) {
        this.loai_qtoan = loai_qtoan;
    }

    public String getLoai_qtoan() {
        return loai_qtoan;
    }

    public void setQtoan_dvi(String qtoan_dvi) {
        this.qtoan_dvi = qtoan_dvi;
    }

    public String getQtoan_dvi() {
        return qtoan_dvi;
    }

    public void setSo_tchieu(String so_tchieu) {
        this.so_tchieu = so_tchieu;
    }

    public String getSo_tchieu() {
        return so_tchieu;
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

    public void setNdung_tt(String ndung_tt) {
        this.ndung_tt = ndung_tt;
    }

    public String getNdung_tt() {
        return ndung_tt;
    }

    public void setSo_tien(Long so_tien) {
        this.so_tien = so_tien;
    }

    public Long getSo_tien() {
        return so_tien;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }

    public void setTtv_chuyen_ks(Long ttv_chuyen_ks) {
        this.ttv_chuyen_ks = ttv_chuyen_ks;
    }

    public Long getTtv_chuyen_ks() {
        return ttv_chuyen_ks;
    }

    public void setNgay_chuyen_ks(String ngay_chuyen_ks) {
        this.ngay_chuyen_ks = ngay_chuyen_ks;
    }

    public String getNgay_chuyen_ks() {
        return ngay_chuyen_ks;
    }

    public void setKtt_ks(Long ktt_ks) {
        this.ktt_ks = ktt_ks;
    }

    public Long getKtt_ks() {
        return ktt_ks;
    }

    public void setNgay_ks(String ngay_ks) {
        this.ngay_ks = ngay_ks;
    }

    public String getNgay_ks() {
        return ngay_ks;
    }

    public void setChu_ky(String chu_ky) {
        this.chu_ky = chu_ky;
    }

    public String getChu_ky() {
        return chu_ky;
    }

    public void setTk_chuyen(String tk_chuyen) {
        this.tk_chuyen = tk_chuyen;
    }

    public String getTk_chuyen() {
        return tk_chuyen;
    }

    public void setMa_nh_chuyen(String ma_nh_chuyen) {
        this.ma_nh_chuyen = ma_nh_chuyen;
    }

    public String getMa_nh_chuyen() {
        return ma_nh_chuyen;
    }

    public void setTen_nh_chuyen(String ten_nh_chuyen) {
        this.ten_nh_chuyen = ten_nh_chuyen;
    }

    public String getTen_nh_chuyen() {
        return ten_nh_chuyen;
    }

    public void setTen_kh_chuyen(String ten_kh_chuyen) {
        this.ten_kh_chuyen = ten_kh_chuyen;
    }

    public String getTen_kh_chuyen() {
        return ten_kh_chuyen;
    }

    public void setTt_kh_chuyen(String tt_kh_chuyen) {
        this.tt_kh_chuyen = tt_kh_chuyen;
    }

    public String getTt_kh_chuyen() {
        return tt_kh_chuyen;
    }

    public void setTk_nhan(String tk_nhan) {
        this.tk_nhan = tk_nhan;
    }

    public String getTk_nhan() {
        return tk_nhan;
    }

    public void setMa_nh_nhan(String ma_nh_nhan) {
        this.ma_nh_nhan = ma_nh_nhan;
    }

    public String getMa_nh_nhan() {
        return ma_nh_nhan;
    }

    public void setTen_nh_nhan(String ten_nh_nhan) {
        this.ten_nh_nhan = ten_nh_nhan;
    }

    public String getTen_nh_nhan() {
        return ten_nh_nhan;
    }

    public void setTen_kh_nhan(String ten_kh_nhan) {
        this.ten_kh_nhan = ten_kh_nhan;
    }

    public String getTen_kh_nhan() {
        return ten_kh_nhan;
    }

    public void setTt_kh_nhan(String tt_kh_nhan) {
        this.tt_kh_nhan = tt_kh_nhan;
    }

    public String getTt_kh_nhan() {
        return tt_kh_nhan;
    }

    public void setTt_in(Long tt_in) {
        this.tt_in = tt_in;
    }

    public Long getTt_in() {
        return tt_in;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_desc(String err_desc) {
        this.err_desc = err_desc;
    }

    public String getErr_desc() {
        return err_desc;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMo_ta_trang_thai(String mo_ta_trang_thai) {
        this.mo_ta_trang_thai = mo_ta_trang_thai;
    }

    public String getMo_ta_trang_thai() {
        return mo_ta_trang_thai;
    }

    public void setChuc_danh(String chuc_danh) {
        this.chuc_danh = chuc_danh;
    }

    public String getChuc_danh() {
        return chuc_danh;
    }

    public void setTen_don_vi_chuyen(String ten_don_vi_chuyen) {
        this.ten_don_vi_chuyen = ten_don_vi_chuyen;
    }

    public String getTen_don_vi_chuyen() {
        return ten_don_vi_chuyen;
    }

    public void setTen_don_vi_nhan(String ten_don_vi_nhan) {
        this.ten_don_vi_nhan = ten_don_vi_nhan;
    }

    public String getTen_don_vi_nhan() {
        return ten_don_vi_nhan;
    }

    public void setMa_tchieu_gd(String ma_tchieu_gd) {
        this.ma_tchieu_gd = ma_tchieu_gd;
    }

    public String getMa_tchieu_gd() {
        return ma_tchieu_gd;
    }

    public void setLdo_hach_toan(String ldo_hach_toan) {
        this.ldo_hach_toan = ldo_hach_toan;
    }

    public String getLdo_hach_toan() {
        return ldo_hach_toan;
    }

    public void setLdo_day_lai(String ldo_day_lai) {
        this.ldo_day_lai = ldo_day_lai;
    }

    public String getLdo_day_lai() {
        return ldo_day_lai;
    }

    public void setLoai_hach_toan(String loai_hach_toan) {
        this.loai_hach_toan = loai_hach_toan;
    }

    public String getLoai_hach_toan() {
        return loai_hach_toan;
    }

    public void setTen_ttv_hoanthien(String ten_ttv_hoanthien) {
        this.ten_ttv_hoanthien = ten_ttv_hoanthien;
    }

    public String getTen_ttv_hoanthien() {
        return ten_ttv_hoanthien;
    }

    public void setTen_ktt_hoanthien(String ten_ktt_hoanthien) {
        this.ten_ktt_hoanthien = ten_ktt_hoanthien;
    }

    public String getTen_ktt_hoanthien() {
        return ten_ktt_hoanthien;
    }

    public void setMa_ttv_hoanthien(String ma_ttv_hoanthien) {
        this.ma_ttv_hoanthien = ma_ttv_hoanthien;
    }

    public String getMa_ttv_hoanthien() {
        return ma_ttv_hoanthien;
    }

    public void setMa_ktt_hoanthien(String ma_ktt_hoanthien) {
        this.ma_ktt_hoanthien = ma_ktt_hoanthien;
    }

    public String getMa_ktt_hoanthien() {
        return ma_ktt_hoanthien;
    }

    public void setSo_bk(String so_bk) {
        this.so_bk = so_bk;
    }

    public String getSo_bk() {
        return so_bk;
    }

    public void setNgay_gui(String ngay_gui) {
        this.ngay_gui = ngay_gui;
    }

    public String getNgay_gui() {
        return ngay_gui;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setId_ref(String id_ref) {
        this.id_ref = id_ref;
    }

    public String getId_ref() {
        return id_ref;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setKet_chuyen_thu(long ket_chuyen_thu) {
        this.ket_chuyen_thu = ket_chuyen_thu;
    }

    public long getKet_chuyen_thu() {
        return ket_chuyen_thu;
    }

    public void setKet_chuyen_chi(long ket_chuyen_chi) {
        this.ket_chuyen_chi = ket_chuyen_chi;
    }

    public long getKet_chuyen_chi() {
        return ket_chuyen_chi;
    }

    public void setKet_chuyen_thu_nh(long ket_chuyen_thu_nh) {
        this.ket_chuyen_thu_nh = ket_chuyen_thu_nh;
    }

    public long getKet_chuyen_thu_nh() {
        return ket_chuyen_thu_nh;
    }

    public void setKet_chuyen_chi_nh(long ket_chuyen_chi_nh) {
        this.ket_chuyen_chi_nh = ket_chuyen_chi_nh;
    }

    public long getKet_chuyen_chi_nh() {
        return ket_chuyen_chi_nh;
    }

    public void setBk_id(String bk_id) {
        this.bk_id = bk_id;
    }

    public String getBk_id() {
        return bk_id;
    }

    public void setKet_qua(String ket_qua) {
        this.ket_qua = ket_qua;
    }

    public String getKet_qua() {
        return ket_qua;
    }

    public void setLan_dc(String lan_dc) {
        this.lan_dc = lan_dc;
    }

    public String getLan_dc() {
        return lan_dc;
    }

    public void setNgay_dc(String ngay_dc) {
        this.ngay_dc = ngay_dc;
    }

    public String getNgay_dc() {
        return ngay_dc;
    }

    public void setSend_bank(String send_bank) {
        this.send_bank = send_bank;
    }

    public String getSend_bank() {
        return send_bank;
    }

    public void setReceive_bank(String receive_bank) {
        this.receive_bank = receive_bank;
    }

    public String getReceive_bank() {
        return receive_bank;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setKet_qua_pht(String ket_qua_pht) {
        this.ket_qua_pht = ket_qua_pht;
    }

    public String getKet_qua_pht() {
        return ket_qua_pht;
    }

    public void setLtt_kb_thua(String ltt_kb_thua) {
        this.ltt_kb_thua = ltt_kb_thua;
    }

    public String getLtt_kb_thua() {
        return ltt_kb_thua;
    }

    public void setLtt_kb_thieu(String ltt_kb_thieu) {
        this.ltt_kb_thieu = ltt_kb_thieu;
    }

    public String getLtt_kb_thieu() {
        return ltt_kb_thieu;
    }

    public void setDts_kb_thieu(String dts_kb_thieu) {
        this.dts_kb_thieu = dts_kb_thieu;
    }

    public String getDts_kb_thieu() {
        return dts_kb_thieu;
    }

    public void setDts_kb_thua(String dts_kb_thua) {
        this.dts_kb_thua = dts_kb_thua;
    }

    public String getDts_kb_thua() {
        return dts_kb_thua;
    }

    public void setSotien_thu(long sotien_thu) {
        this.sotien_thu = sotien_thu;
    }

    public long getSotien_thu() {
        return sotien_thu;
    }

    public void setSotien_chi(long sotien_chi) {
        this.sotien_chi = sotien_chi;
    }

    public long getSotien_chi() {
        return sotien_chi;
    }

    public void setSotien_dtu(long sotien_dtu) {
        this.sotien_dtu = sotien_dtu;
    }

    public long getSotien_dtu() {
        return sotien_dtu;
    }

    public void setSotien_tcong(long sotien_tcong) {
        this.sotien_tcong = sotien_tcong;
    }

    public long getSotien_tcong() {
        return sotien_tcong;
    }

    public void setSodu_daungay(long sodu_daungay) {
        this.sodu_daungay = sodu_daungay;
    }

    public long getSodu_daungay() {
        return sodu_daungay;
    }

    public void setSo_du(long so_du) {
        this.so_du = so_du;
    }

    public long getSo_du() {
        return so_du;
    }

    public void setSodu_kbnn(long sodu_kbnn) {
        this.sodu_kbnn = sodu_kbnn;
    }

    public long getSodu_kbnn() {
        return sodu_kbnn;
    }

    public void setChenh_lech(long chenh_lech) {
        this.chenh_lech = chenh_lech;
    }

    public long getChenh_lech() {
        return chenh_lech;
    }

    public void setTrang_thai_kqdc(String trang_thai_kqdc) {
        this.trang_thai_kqdc = trang_thai_kqdc;
    }

    public String getTrang_thai_kqdc() {
        return trang_thai_kqdc;
    }

    public void setTrang_thai_bkdc(String trang_thai_bkdc) {
        this.trang_thai_bkdc = trang_thai_bkdc;
    }

    public String getTrang_thai_bkdc() {
        return trang_thai_bkdc;
    }

    public void setSo_tien_thu_dtu(long so_tien_thu_dtu) {
        this.so_tien_thu_dtu = so_tien_thu_dtu;
    }

    public long getSo_tien_thu_dtu() {
        return so_tien_thu_dtu;
    }

    public void setSo_tien_thu_tcong(long so_tien_thu_tcong) {
        this.so_tien_thu_tcong = so_tien_thu_tcong;
    }

    public long getSo_tien_thu_tcong() {
        return so_tien_thu_tcong;
    }

    public void setSo_mon_thu_tcong(long so_mon_thu_tcong) {
        this.so_mon_thu_tcong = so_mon_thu_tcong;
    }

    public long getSo_mon_thu_tcong() {
        return so_mon_thu_tcong;
    }

    public void setMon_thu_tcong_kbnn(long mon_thu_tcong_kbnn) {
        this.mon_thu_tcong_kbnn = mon_thu_tcong_kbnn;
    }

    public long getMon_thu_tcong_kbnn() {
        return mon_thu_tcong_kbnn;
    }

    public void setMon_chi_tcong_kbnn(long mon_chi_tcong_kbnn) {
        this.mon_chi_tcong_kbnn = mon_chi_tcong_kbnn;
    }

    public long getMon_chi_tcong_kbnn() {
        return mon_chi_tcong_kbnn;
    }

    public void setTien_thu_tcong_kbnn(long tien_thu_tcong_kbnn) {
        this.tien_thu_tcong_kbnn = tien_thu_tcong_kbnn;
    }

    public long getTien_thu_tcong_kbnn() {
        return tien_thu_tcong_kbnn;
    }

    public void setTien_chi_tcong_kbnn(long tien_chi_tcong_kbnn) {
        this.tien_chi_tcong_kbnn = tien_chi_tcong_kbnn;
    }

    public long getTien_chi_tcong_kbnn() {
        return tien_chi_tcong_kbnn;
    }

    public void setChenhlech_thu(long chenhlech_thu) {
        this.chenhlech_thu = chenhlech_thu;
    }

    public long getChenhlech_thu() {
        return chenhlech_thu;
    }

    public void setChenhlech_chi(long chenhlech_chi) {
        this.chenhlech_chi = chenhlech_chi;
    }

    public long getChenhlech_chi() {
        return chenhlech_chi;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setSomon_tcong(long somon_tcong) {
        this.somon_tcong = somon_tcong;
    }

    public long getSomon_tcong() {
        return somon_tcong;
    }

    public void setSomon_thu(long somon_thu) {
        this.somon_thu = somon_thu;
    }

    public long getSomon_thu() {
        return somon_thu;
    }

    public void setSomon_chi(long somon_chi) {
        this.somon_chi = somon_chi;
    }

    public long getSomon_chi() {
        return somon_chi;
    }

    public void setSomon_dtu(long somon_dtu) {
        this.somon_dtu = somon_dtu;
    }

    public long getSomon_dtu() {
        return somon_dtu;
    }

    public void setSo_mon_thu_dtu(long so_mon_thu_dtu) {
        this.so_mon_thu_dtu = so_mon_thu_dtu;
    }

    public long getSo_mon_thu_dtu() {
        return so_mon_thu_dtu;
    }

    public void setSo_ketchuyen(long so_ketchuyen) {
        this.so_ketchuyen = so_ketchuyen;
    }

    public long getSo_ketchuyen() {
        return so_ketchuyen;
    }

    public void setMon_thu_dtu_kbnn(long mon_thu_dtu_kbnn) {
        this.mon_thu_dtu_kbnn = mon_thu_dtu_kbnn;
    }

    public long getMon_thu_dtu_kbnn() {
        return mon_thu_dtu_kbnn;
    }

    public void setMon_chi_dtu_kbnn(long mon_chi_dtu_kbnn) {
        this.mon_chi_dtu_kbnn = mon_chi_dtu_kbnn;
    }

    public long getMon_chi_dtu_kbnn() {
        return mon_chi_dtu_kbnn;
    }

    public void setTien_thu_dtu_kbnn(long tien_thu_dtu_kbnn) {
        this.tien_thu_dtu_kbnn = tien_thu_dtu_kbnn;
    }

    public long getTien_thu_dtu_kbnn() {
        return tien_thu_dtu_kbnn;
    }

    public void setTien_chi_dtu_kbnn(long tien_chi_dtu_kbnn) {
        this.tien_chi_dtu_kbnn = tien_chi_dtu_kbnn;
    }

    public long getTien_chi_dtu_kbnn() {
        return tien_chi_dtu_kbnn;
    }

    public void setSo_du_dau_ngay(long so_du_dau_ngay) {
        this.so_du_dau_ngay = so_du_dau_ngay;
    }

    public long getSo_du_dau_ngay() {
        return so_du_dau_ngay;
    }

    public void setChenh_sdu_dngay(long chenh_sdu_dngay) {
        this.chenh_sdu_dngay = chenh_sdu_dngay;
    }

    public long getChenh_sdu_dngay() {
        return chenh_sdu_dngay;
    }

    public void setChenh_kchuyen_thu(long chenh_kchuyen_thu) {
        this.chenh_kchuyen_thu = chenh_kchuyen_thu;
    }

    public long getChenh_kchuyen_thu() {
        return chenh_kchuyen_thu;
    }

    public void setChenh_kchuyen_chi(long chenh_kchuyen_chi) {
        this.chenh_kchuyen_chi = chenh_kchuyen_chi;
    }

    public long getChenh_kchuyen_chi() {
        return chenh_kchuyen_chi;
    }

    public void setPs_chi(long ps_chi) {
        this.ps_chi = ps_chi;
    }

    public long getPs_chi() {
        return ps_chi;
    }

    public void setTrang_thai_kq(String trang_thai_kq) {
        this.trang_thai_kq = trang_thai_kq;
    }

    public String getTrang_thai_kq() {
        return trang_thai_kq;
    }

    public void setQtoan_id(String qtoan_id) {
        this.qtoan_id = qtoan_id;
    }

    public String getQtoan_id() {
        return qtoan_id;
    }

  
}
