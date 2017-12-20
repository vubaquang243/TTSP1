package com.seatech.ttsp.ltt;

import java.math.BigDecimal;

public class LTTVO {
    private Long id;
    private Long nhkb_nhan;
    private Long nhkb_chuyen;
    private String nhan;
    private Long ttv_nhan;
    private String ngay_nhan;
    private String ktv_chuyen;    
    private Long ktt_duyet;
    private String ngay_ktt_duyet;
    private String lydo_ktt_day_lai;
    
    private Long gd_duyet;
    private String ngay_gd_duyet;
    private String lydo_gd_day_lai;
    private String so_ct_goc;
    private Long ngay_ct;
    private Long nt_id;
    private String nt_code;
    private String ma_nt;
    private String so_yctt;
    private Long ngay_tt;
    private String ngay_tt_tmp;
    private String ndung_tt;
    private BigDecimal tong_sotien;
    
    private String so_tk_chuyen;
    private String ten_tk_chuyen;
    private String ttin_kh_chuyen;
    private Long id_nhkb_chuyen;
    private String ten_nhkb_chuyen;
    
    private String so_tk_nhan;
    private String ten_tk_nhan;
    private String ttin_kh_nhan;
    private Long id_nhkb_nhan;
    private String ten_nhkb_nhan;
    
    private String trang_thai;
   private String trang_thai_tw;
    private String loai_hach_toan;
    private String nguoi_nhap_nh;
    private String ngay_nhap_nh;
    private String nguoi_ks_nh;
    private String ngay_ks_nh;
    private String chuky_ktt;
    private String chuky_gd;
    
    private String ma_nhkb_chuyen;
    private String ma_nhkb_nhan;
    private String ngay_hoan_thien;
    private String loai_quyet_toan;
    
    //ChucLH them
    private String ma_nhkb_chuyen_cm;
    private String ma_nhkb_nhan_cm; 
    private String ten_nhkb_chuyen_cm;
    private String ten_nhkb_nhan_cm;
    private Long tt_in;  
    private String msg_id;
    private String ten_ttv_nhan;
    private String ten_nguoi_duyet;
    private String nh_tgian;
    private String ngay_gui_nh;
    private String so_tham_chieu_gd;
    private String so_tham_chieu_lq;
    private String ttloai_lenh;
    private String loai_lenh;
    private String ten_kb_tinh;
    private String ten_kb_huyen;
    private String ten_nh;
    private String ma_kb_huyen;
    private String ma_nh;
    private Long tong_mon;
    private BigDecimal tong_tien;
    private String error_code;
    private String error_desc;
    private String ly_do_htoan;
    private String ma_ktt_chuyen;
    private String ma_gd_chuyen;
    private String chklist;
    
    //Dap ung mua ban ngoai te
    private String ma_nt_mua_ban;
    private BigDecimal so_tien_mua_ban;
    private String phi;
        
    private String ten_nh_tgian;
    private String id_nh_trung_gian;
    
    private String vay_tra_no_nn;
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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

    public void setNhan(String nhan) {
        this.nhan = nhan;
    }

    public String getNhan() {
        return nhan;
    }

    public void setTtv_nhan(Long ttv_nhan) {
        this.ttv_nhan = ttv_nhan;
    }

    public Long getTtv_nhan() {
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

    public void setKtt_duyet(Long ktt_duyet) {
        this.ktt_duyet = ktt_duyet;
    }

    public Long getKtt_duyet() {
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

    public void setGd_duyet(Long gd_duyet) {
        this.gd_duyet = gd_duyet;
    }

    public Long getGd_duyet() {
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

    public void setNgay_ct(Long ngay_ct) {
        this.ngay_ct = ngay_ct;
    }

    public Long getNgay_ct() {
        return ngay_ct;
    }

    public void setNt_id(Long nt_id) {
        this.nt_id = nt_id;
    }

    public Long getNt_id() {
        return nt_id;
    }

    public void setSo_yctt(String so_yctt) {
        this.so_yctt = so_yctt;
    }

    public String getSo_yctt() {
        return so_yctt;
    }

    public void setNgay_tt(Long ngay_tt) {
        this.ngay_tt = ngay_tt;
    }

    public Long getNgay_tt() {
        return ngay_tt;
    }

    public void setNdung_tt(String ndung_tt) {
        this.ndung_tt = ndung_tt;
    }

    public String getNdung_tt() {
        return ndung_tt;
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

    public void setId_nhkb_chuyen(Long id_nhkb_chuyen) {
        this.id_nhkb_chuyen = id_nhkb_chuyen;
    }

    public Long getId_nhkb_chuyen() {
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

    public void setId_nhkb_nhan(Long id_nhkb_nhan) {
        this.id_nhkb_nhan = id_nhkb_nhan;
    }

    public Long getId_nhkb_nhan() {
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

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setNgay_hoan_thien(String ngay_hoan_thien) {
        this.ngay_hoan_thien = ngay_hoan_thien;
    }

    public String getNgay_hoan_thien() {
        return ngay_hoan_thien;
    }

    public void setTong_sotien(BigDecimal tong_sotien) {
        this.tong_sotien = tong_sotien;
    }

    public BigDecimal getTong_sotien() {
        return tong_sotien;
    }

    public void setLoai_quyet_toan(String loai_quyet_toan) {
        this.loai_quyet_toan = loai_quyet_toan;
    }

    public String getLoai_quyet_toan() {
        return loai_quyet_toan;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }

    public void setTt_in(Long tt_in) {
        this.tt_in = tt_in;
    }

    public Long getTt_in() {
        return tt_in;
    }

    public void setTen_ttv_nhan(String ten_ttv_nhan) {
        this.ten_ttv_nhan = ten_ttv_nhan;
    }

    public String getTen_ttv_nhan() {
        return ten_ttv_nhan;
    }

    public void setTen_nguoi_duyet(String ten_nguoi_duyet) {
        this.ten_nguoi_duyet = ten_nguoi_duyet;
    }

    public String getTen_nguoi_duyet() {
        return ten_nguoi_duyet;
    }

    public void setNh_tgian(String nh_tgian) {
        this.nh_tgian = nh_tgian;
    }

    public String getNh_tgian() {
        return nh_tgian;
    }

    public void setNgay_gui_nh(String ngay_gui_nh) {
        this.ngay_gui_nh = ngay_gui_nh;
    }

    public String getNgay_gui_nh() {
        return ngay_gui_nh;
    }

    public void setSo_tham_chieu_gd(String so_tham_chieu_gd) {
        this.so_tham_chieu_gd = so_tham_chieu_gd;
    }

    public String getSo_tham_chieu_gd() {
        return so_tham_chieu_gd;
    }

    public void setSo_tham_chieu_lq(String so_tham_chieu_lq) {
        this.so_tham_chieu_lq = so_tham_chieu_lq;
    }

    public String getSo_tham_chieu_lq() {
        return so_tham_chieu_lq;
    }

    public void setTtloai_lenh(String ttloai_lenh) {
        this.ttloai_lenh = ttloai_lenh;
    }

    public String getTtloai_lenh() {
        return ttloai_lenh;
    }

    public void setLoai_lenh(String loai_lenh) {
        this.loai_lenh = loai_lenh;
    }

    public String getLoai_lenh() {
        return loai_lenh;
    }

    public void setNgay_tt_tmp(String ngay_tt_tmp) {
        this.ngay_tt_tmp = ngay_tt_tmp;
    }

    public String getNgay_tt_tmp() {
        return ngay_tt_tmp;
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

    public void setNt_code(String nt_code) {
        this.nt_code = nt_code;
    }

    public String getNt_code() {
        return nt_code;
    }

    public void setTong_mon(Long tong_mon) {
        this.tong_mon = tong_mon;
    }

    public Long getTong_mon() {
        return tong_mon;
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

    public void setLy_do_htoan(String ly_do_htoan) {
        this.ly_do_htoan = ly_do_htoan;
    }

    public String getLy_do_htoan() {
        return ly_do_htoan;
    }

    public void setMa_ktt_chuyen(String ma_ktt_chuyen) {
        this.ma_ktt_chuyen = ma_ktt_chuyen;
    }

    public String getMa_ktt_chuyen() {
        return ma_ktt_chuyen;
    }

    public void setMa_gd_chuyen(String ma_gd_chuyen) {
        this.ma_gd_chuyen = ma_gd_chuyen;
    }

    public String getMa_gd_chuyen() {
        return ma_gd_chuyen;
    }

    public void setTrang_thai_tw(String trang_thai_tw) {
        this.trang_thai_tw = trang_thai_tw;
    }

    public String getTrang_thai_tw() {
        return trang_thai_tw;
    }

    public void setChklist(String chklist) {
        this.chklist = chklist;
    }

    public String getChklist() {
        return chklist;
    }

    public void setMa_nt_mua_ban(String ma_nt_mua_ban) {
        this.ma_nt_mua_ban = ma_nt_mua_ban;
    }

    public String getMa_nt_mua_ban() {
        return ma_nt_mua_ban;
    }

    public void setSo_tien_mua_ban(BigDecimal so_tien_mua_ban) {
        this.so_tien_mua_ban = so_tien_mua_ban;
    }

    public BigDecimal getSo_tien_mua_ban() {
        return so_tien_mua_ban;
    }

    public void setPhi(String phi) {
        this.phi = phi;
    }

    public String getPhi() {
        return phi;
    }
    public void setId_nh_trung_gian(String id_nh_trung_gian) {
        this.id_nh_trung_gian = id_nh_trung_gian;
    }

    public String getId_nh_trung_gian() {
        return id_nh_trung_gian;
    }

    public void setTen_nh_tgian(String ten_nh_tgian) {
        this.ten_nh_tgian = ten_nh_tgian;
    }

    public String getTen_nh_tgian() {
        return ten_nh_tgian;
    }

    public void setVay_tra_no_nn(String vay_tra_no_nn) {
        this.vay_tra_no_nn = vay_tra_no_nn;
    }

    public String getVay_tra_no_nn() {
        return vay_tra_no_nn;
    }

    public void setTong_tien(BigDecimal tong_tien) {
        this.tong_tien = tong_tien;
    }

    public BigDecimal getTong_tien() {
        return tong_tien;
    }
}
