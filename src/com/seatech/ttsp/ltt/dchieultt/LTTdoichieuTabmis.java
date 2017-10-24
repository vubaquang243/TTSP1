package com.seatech.ttsp.ltt.dchieultt;

import com.seatech.framework.strustx.AppForm;


/**
   * @creater: HungBM
   * @modify date: 01/11/2016  
   * @see: Them moi lop form cho tra cuu doi chieu TABMIS
   * */
public class LTTdoichieuTabmis extends AppForm {  
    
    private String ngay_dc;
    private String id;
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
    private String nt_code;
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
    private String ngan_hang;

    private String trang_thai;
    private String loai_hach_toan;
    private String nguoi_nhap_nh;
    private String ngay_nhap_nh;
    private String nguoi_ks_nh;
    private String ngay_ks_nh;
    private String chuky_ktt;
    private String chuky_gd;

    private String ma_nhkb_chuyen;
    private String ma_nhkb_nhan;
    private String ma_nhkb_chuyen_cm;
    private String ma_nhkb_nhan_cm;
    private String ten_nhkb_chuyen_cm;
    private String ten_nhkb_nhan_cm;

    private String ma_ttv_nhan;
    private String ten_ttv_nhan;
    private String ma_ktt_duyet;
    private String ten_ktt_duyet;
    private String ma_gd_duyet;
    private String ten_gd_duyet;
    private String ten_nguoi_nhap_nh;
    private String ten_nguoi_ks_nh;

    private String ngay_hoan_thien;
    private String loai_quyet_toan;
    private String loai_quyet_toan_text;
    private String loai_lenh_tt;
    private String pageNumber="";

    //Chuc them
    private String tu_ngay;
    private String den_ngay;
    private String tu_ngay_nhan;
    private String den_ngay_nhan;
    
    //Ltt Chi Tiet
    private String id_chi_tiet;
    private String ltt_ct_ma;
    private String ltt_id;
    private String ma_quy;
    private String tkkt_ma;
    private String dvsdns_ma;
    private String capns_ma;
    private String chuongns_ma;
    private String nganhkt_ma;
    private String ndkt_ma;
    private String dbhc_ma;
    private String ctmt_ma;
    private String du_phong_ma;
    private String ma_nguon;
    private String so_tien;
    private String dien_giai;

    private String kb_ma;
    private String ma_nt;
    private String sott_dong;
    private String msg_id;
    private String ttloai_lenh;
    private String noi_dung_ky;

    // tim kiem
    private String ttvnhan;
    private String nhkbchuyennhan;
    private String sotien;
    private String soyctt;
    private String trangthai;
    // tong mon, tong tien
    private String tong_so_mon;
    private String tong_so_tien;
    private String error_desc;
    private String ly_do_htoan;
    
    // tim kiem theo tinh huyen
    private String kb_tinh;
    private String kb_huyen;
    private String tthai_doichieu;
    private String so_tham_chieu_gd;
    private String user;
    private String pass;
    private String nt_id_find;
    private String nt_id_tke_tong;
    //Dap ung mua ban ngoai te
    private String ma_nt_mua_ban;
    private String so_tien_mua_ban;
    private String phi;
    private String nh_tgian;
    private String ten_nh_tgian;
    private String id_nh_trung_gian;
    
    private String vay_tra_no_nn;
    private String ma_nhkb_nhan_cu_hiden;
    private String sua_ten_tk_flag;
    
    private String idxKB;
    private String idxNH;
   
   private String id_kq;
   //new find
   private String nhkb_tinh;
   private String nhkb_huyen;
   private String dmuckb_tinh;
   private String dmuckb_huyen;
   private String lan_dc;
   private String lydo;
    
    
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

    public void setLtt_ct_ma(String ltt_ct_ma) {
        this.ltt_ct_ma = ltt_ct_ma;
    }

    public String getLtt_ct_ma() {
        return ltt_ct_ma;
    }

    public void setLtt_id(String ltt_id) {
        this.ltt_id = ltt_id;
    }

    public String getLtt_id() {
        return ltt_id;
    }

    public void setMa_quy(String ma_quy) {
        this.ma_quy = ma_quy;
    }

    public String getMa_quy() {
        return ma_quy;
    }

    public void setTkkt_ma(String tkkt_ma) {
        this.tkkt_ma = tkkt_ma;
    }

    public String getTkkt_ma() {
        return tkkt_ma;
    }

    public void setDvsdns_ma(String dvsdns_ma) {
        this.dvsdns_ma = dvsdns_ma;
    }

    public String getDvsdns_ma() {
        return dvsdns_ma;
    }

    public void setCapns_ma(String capns_ma) {
        this.capns_ma = capns_ma;
    }

    public String getCapns_ma() {
        return capns_ma;
    }

    public void setChuongns_ma(String chuongns_ma) {
        this.chuongns_ma = chuongns_ma;
    }

    public String getChuongns_ma() {
        return chuongns_ma;
    }

    public void setNganhkt_ma(String nganhkt_ma) {
        this.nganhkt_ma = nganhkt_ma;
    }

    public String getNganhkt_ma() {
        return nganhkt_ma;
    }

    public void setNdkt_ma(String ndkt_ma) {
        this.ndkt_ma = ndkt_ma;
    }

    public String getNdkt_ma() {
        return ndkt_ma;
    }

    public void setDbhc_ma(String dbhc_ma) {
        this.dbhc_ma = dbhc_ma;
    }

    public String getDbhc_ma() {
        return dbhc_ma;
    }

    public void setCtmt_ma(String ctmt_ma) {
        this.ctmt_ma = ctmt_ma;
    }

    public String getCtmt_ma() {
        return ctmt_ma;
    }

    public void setDu_phong_ma(String du_phong_ma) {
        this.du_phong_ma = du_phong_ma;
    }

    public String getDu_phong_ma() {
        return du_phong_ma;
    }

    public void setMa_nguon(String ma_nguon) {
        this.ma_nguon = ma_nguon;
    }

    public String getMa_nguon() {
        return ma_nguon;
    }

    public void setSo_tien(String so_tien) {
        this.so_tien = so_tien;
    }

    public String getSo_tien() {
        return so_tien;
    }

    public void setDien_giai(String dien_giai) {
        this.dien_giai = dien_giai;
    }

    public String getDien_giai() {
        return dien_giai;
    }

    public void setKb_ma(String kb_ma) {
        this.kb_ma = kb_ma;
    }

    public String getKb_ma() {
        return kb_ma;
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

    public void setMa_ttv_nhan(String ma_ttv_nhan) {
        this.ma_ttv_nhan = ma_ttv_nhan;
    }

    public String getMa_ttv_nhan() {
        return ma_ttv_nhan;
    }

    public void setMa_ktt_duyet(String ma_ktt_duyet) {
        this.ma_ktt_duyet = ma_ktt_duyet;
    }

    public String getMa_ktt_duyet() {
        return ma_ktt_duyet;
    }

    public void setMa_gd_duyet(String ma_gd_duyet) {
        this.ma_gd_duyet = ma_gd_duyet;
    }

    public String getMa_gd_duyet() {
        return ma_gd_duyet;
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

    public void setTen_gd_duyet(String ten_gd_duyet) {
        this.ten_gd_duyet = ten_gd_duyet;
    }

    public String getTen_gd_duyet() {
        return ten_gd_duyet;
    }

    public void setTen_nguoi_nhap_nh(String ten_nguoi_nhap_nh) {
        this.ten_nguoi_nhap_nh = ten_nguoi_nhap_nh;
    }

    public String getTen_nguoi_nhap_nh() {
        return ten_nguoi_nhap_nh;
    }

    public void setTen_nguoi_ks_nh(String ten_nguoi_ks_nh) {
        this.ten_nguoi_ks_nh = ten_nguoi_ks_nh;
    }

    public String getTen_nguoi_ks_nh() {
        return ten_nguoi_ks_nh;
    }

    public void setId_chi_tiet(String id_chi_tiet) {
        this.id_chi_tiet = id_chi_tiet;
    }

    public String getId_chi_tiet() {
        return id_chi_tiet;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }

    public void setSott_dong(String sott_dong) {
        this.sott_dong = sott_dong;
    }

    public String getSott_dong() {
        return sott_dong;
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

    public void setLoai_quyet_toan(String loai_quyet_toan) {
        this.loai_quyet_toan = loai_quyet_toan;
    }

    public String getLoai_quyet_toan() {
        return loai_quyet_toan;
    }

    public void setLoai_quyet_toan_text(String loai_quyet_toan_text) {
        this.loai_quyet_toan_text = loai_quyet_toan_text;
    }

    public String getLoai_quyet_toan_text() {
        if (loai_quyet_toan_text == null || "".equals(loai_quyet_toan_text)) {
            if ("C".equalsIgnoreCase(loai_quyet_toan)) {
                loai_quyet_toan_text = "Co";
            } else if ("D".equalsIgnoreCase(loai_quyet_toan)) {
                loai_quyet_toan_text = "No";
            }
        }
        return loai_quyet_toan_text;
    }

    public void setTtloai_lenh(String ttloai_lenh) {
        this.ttloai_lenh = ttloai_lenh;
    }

    public String getTtloai_lenh() {
        return ttloai_lenh;
    }

    public void setNoi_dung_ky(String noi_dung_ky) {
        this.noi_dung_ky = noi_dung_ky;
    }

    public String getNoi_dung_ky() {
        return noi_dung_ky;
    }

    public void setNt_code(String nt_code) {
        this.nt_code = nt_code;
    }

    public String getNt_code() {
        return nt_code;
    }

    public void setTtvnhan(String ttvnhan) {
        this.ttvnhan = ttvnhan;
    }

    public String getTtvnhan() {
        return ttvnhan;
    }

    public void setNhkbchuyennhan(String nhkbchuyennhan) {
        this.nhkbchuyennhan = nhkbchuyennhan;
    }

    public String getNhkbchuyennhan() {
        return nhkbchuyennhan;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSoyctt(String soyctt) {
        this.soyctt = soyctt;
    }

    public String getSoyctt() {
        return soyctt;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTong_so_mon(String tong_so_mon) {
        this.tong_so_mon = tong_so_mon;
    }

    public String getTong_so_mon() {
        return tong_so_mon;
    }

    public void setTong_so_tien(String tong_so_tien) {
        this.tong_so_tien = tong_so_tien;
    }

    public String getTong_so_tien() {
        return tong_so_tien;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setTu_ngay_nhan(String tu_ngay_nhan) {
        this.tu_ngay_nhan = tu_ngay_nhan;
    }

    public String getTu_ngay_nhan() {
        return tu_ngay_nhan;
    }

    public void setDen_ngay_nhan(String den_ngay_nhan) {
        this.den_ngay_nhan = den_ngay_nhan;
    }

    public String getDen_ngay_nhan() {
        return den_ngay_nhan;
    }

    public void setLy_do_htoan(String ly_do_htoan) {
        this.ly_do_htoan = ly_do_htoan;
    }

    public String getLy_do_htoan() {
        return ly_do_htoan;
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

    public void setSo_tham_chieu_gd(String so_tham_chieu_gd) {
        this.so_tham_chieu_gd = so_tham_chieu_gd;
    }

    public String getSo_tham_chieu_gd() {
        return so_tham_chieu_gd;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setNt_id_find(String nt_id_find) {
        this.nt_id_find = nt_id_find;
    }

    public String getNt_id_find() {
        return nt_id_find;
    }

    public void setNt_id_tke_tong(String nt_id_tke_tong) {
        this.nt_id_tke_tong = nt_id_tke_tong;
    }

    public String getNt_id_tke_tong() {
        return nt_id_tke_tong;
    }

    public void setMa_nt_mua_ban(String ma_nt_mua_ban) {
        this.ma_nt_mua_ban = ma_nt_mua_ban;
    }

    public String getMa_nt_mua_ban() {
        return ma_nt_mua_ban;
    }

    public void setSo_tien_mua_ban(String so_tien_mua_ban) {
        this.so_tien_mua_ban = so_tien_mua_ban;
    }

    public String getSo_tien_mua_ban() {
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

    public void setNh_tgian(String nh_tgian) {
        this.nh_tgian = nh_tgian;
    }

    public String getNh_tgian() {
        return nh_tgian;
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

    public void setMa_nhkb_nhan_cu_hiden(String ma_nhkb_nhan_cu_hiden) {
        this.ma_nhkb_nhan_cu_hiden = ma_nhkb_nhan_cu_hiden;
    }

    public String getMa_nhkb_nhan_cu_hiden() {
        return ma_nhkb_nhan_cu_hiden;
    }

    public void setSua_ten_tk_flag(String sua_ten_tk_flag) {
        this.sua_ten_tk_flag = sua_ten_tk_flag;
    }

    public String getSua_ten_tk_flag() {
        return sua_ten_tk_flag;
    }

    public void setTthai_doichieu(String tthai_doichieu) {
        this.tthai_doichieu = tthai_doichieu;
    }

    public String getTthai_doichieu() {
        return tthai_doichieu;
    }

    public void setNgan_hang(String ngan_hang) {
        this.ngan_hang = ngan_hang;
    }

    public String getNgan_hang() {
        return ngan_hang;
    }

    public void setNgay_dc(String ngay_dc) {
        this.ngay_dc = ngay_dc;
    }

    public String getNgay_dc() {
        return ngay_dc;
    }

    public void setIdxKB(String idxKB) {
        this.idxKB = idxKB;
    }

    public String getIdxKB() {
        return idxKB;
    }

    public void setIdxNH(String idxNH) {
        this.idxNH = idxNH;
    }

    public String getIdxNH() {
        return idxNH;
    }

    public void setNhkb_tinh(String nhkb_tinh) {
        this.nhkb_tinh = nhkb_tinh;
    }

    public String getNhkb_tinh() {
        return nhkb_tinh;
    }

    public void setNhkb_huyen(String nhkb_huyen) {
        this.nhkb_huyen = nhkb_huyen;
    }

    public String getNhkb_huyen() {
        return nhkb_huyen;
    }

    public void setDmuckb_tinh(String dmuckb_tinh) {
        this.dmuckb_tinh = dmuckb_tinh;
    }

    public String getDmuckb_tinh() {
        return dmuckb_tinh;
    }

    public void setDmuckb_huyen(String dmuckb_huyen) {
        this.dmuckb_huyen = dmuckb_huyen;
    }

    public String getDmuckb_huyen() {
        return dmuckb_huyen;
    }

    public void setLan_dc(String lan_dc) {
        this.lan_dc = lan_dc;
    }

    public String getLan_dc() {
        return lan_dc;
    }

   

    public void setId_kq(String id_kq) {
        this.id_kq = id_kq;
    }

    public String getId_kq() {
        return id_kq;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public String getLydo() {
        return lydo;
    }
}
