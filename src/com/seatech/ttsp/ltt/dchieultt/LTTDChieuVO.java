package com.seatech.ttsp.ltt.dchieultt;
/**
   * @creater: HungBM
   * @modify date: 01/11/2016  
   * @see: them lop VO cho chi tiet doi chieu TABMIS
   * */
public class LTTDChieuVO {
    
  //VO cho LIST
   private String id;
   private String shkb;
   private String kb_huyen;
   private String ngan_hang;
   private String ngay_dc;
   private String trang_thai;
   private String ma_nh;
   //VO cho VIEW
    //id da co
    private String lan_dc;
    //ngay_dc da co
    private String ma_kb;
    //SHKB da co
    private String ngay_tao;
    private String msg_id;
    //trang_thai da co
    private String ngay_nhan;
    private String ngay_thuc_dien_dc;
    private String tt_dc_tu_dong;
    private String tong_ltt_di;
    private String tong_ltt_den;
    private String ltt_thua_sp;
    private String ltt_thieu_sp;
    private String ltt_thua_tab;
    private String ltt_thieu_tab;
    private String tong_tien_di;
    private String tong_tien_den;
    
    //Chi tiet
    private String ngay_ct;
    private String nhkb_chuyen;
    private String ten_nhkb_chuyen;
    private String nhkb_nhan;
    private String ten_nhkb_nhan;
    private String so_tk_nhan;
    private String so_ltt;
    private String so_tien;
    private String trang_thai_lttctiet;
    private String loai_lenh;
    
    //Thong tin tong hop bang ke
    private String ma;
    private String so_tien_di;
    private String tong_di;
    private String so_tien_den;
    private String tong_den;
    
    private String nhkb_tinh;
    private String nhkb_huyen;
   private String dmuckb_tinh;
   private String dmuckb_huyen;
   private String ly_do;
   private String so_tien_qtoan_thu;
   private String tong_qtoan_thu;
   private String so_tien_qtoan_chi;
   private String tong_qtoan_chi;
   
   private String so_tien_lai_phi;
   private String tong_lai_phi;
   private String so_tien_ty_gia;
   private String tong_ty_gia;

   public void setId(String id) {
       this.id = id;
   }

   public String getId() {
       return id;
   }

    public void setShkb(String shkb) {
        this.shkb = shkb;
    }

    public String getShkb() {
        return shkb;
    }

    public void setKb_huyen(String kb_huyen) {
        this.kb_huyen = kb_huyen;
    }

    public String getKb_huyen() {
        return kb_huyen;
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

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }


    public void setLan_dc(String lan_dc) {
        this.lan_dc = lan_dc;
    }

    public String getLan_dc() {
        return lan_dc;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setNgay_tao(String ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public String getNgay_tao() {
        return ngay_tao;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setNgay_nhan(String ngay_nhan) {
        this.ngay_nhan = ngay_nhan;
    }

    public String getNgay_nhan() {
        return ngay_nhan;
    }

    public void setNgay_thuc_dien_dc(String ngay_thuc_dien_dc) {
        this.ngay_thuc_dien_dc = ngay_thuc_dien_dc;
    }

    public String getNgay_thuc_dien_dc() {
        return ngay_thuc_dien_dc;
    }

    public void setTt_dc_tu_dong(String tt_dc_tu_dong) {
        this.tt_dc_tu_dong = tt_dc_tu_dong;
    }

    public String getTt_dc_tu_dong() {
        return tt_dc_tu_dong;
    }

    public void setTong_ltt_di(String tong_ltt_di) {
        this.tong_ltt_di = tong_ltt_di;
    }

    public String getTong_ltt_di() {
        return tong_ltt_di;
    }

    public void setTong_ltt_den(String tong_ltt_den) {
        this.tong_ltt_den = tong_ltt_den;
    }

    public String getTong_ltt_den() {
        return tong_ltt_den;
    }

    public void setLtt_thua_sp(String ltt_thua_sp) {
        this.ltt_thua_sp = ltt_thua_sp;
    }

    public String getLtt_thua_sp() {
        return ltt_thua_sp;
    }

    public void setLtt_thieu_sp(String ltt_thieu_sp) {
        this.ltt_thieu_sp = ltt_thieu_sp;
    }

    public String getLtt_thieu_sp() {
        return ltt_thieu_sp;
    }

    public void setLtt_thua_tab(String ltt_thua_tab) {
        this.ltt_thua_tab = ltt_thua_tab;
    }

    public String getLtt_thua_tab() {
        return ltt_thua_tab;
    }

    public void setLtt_thieu_tab(String ltt_thieu_tab) {
        this.ltt_thieu_tab = ltt_thieu_tab;
    }

    public String getLtt_thieu_tab() {
        return ltt_thieu_tab;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setTong_tien_di(String tong_tien_di) {
        this.tong_tien_di = tong_tien_di;
    }

    public String getTong_tien_di() {
        return tong_tien_di;
    }

    public void setTong_tien_den(String tong_tien_den) {
        this.tong_tien_den = tong_tien_den;
    }

    public String getTong_tien_den() {
        return tong_tien_den;
    }

    public void setNgay_ct(String ngay_ct) {
        this.ngay_ct = ngay_ct;
    }

    public String getNgay_ct() {
        return ngay_ct;
    }

    public void setNhkb_chuyen(String nhkb_chuyen) {
        this.nhkb_chuyen = nhkb_chuyen;
    }

    public String getNhkb_chuyen() {
        return nhkb_chuyen;
    }

    public void setTen_nhkb_chuyen(String ten_nhkb_chuyen) {
        this.ten_nhkb_chuyen = ten_nhkb_chuyen;
    }

    public String getTen_nhkb_chuyen() {
        return ten_nhkb_chuyen;
    }

    public void setNhkb_nhan(String nhkb_nhan) {
        this.nhkb_nhan = nhkb_nhan;
    }

    public String getNhkb_nhan() {
        return nhkb_nhan;
    }

    public void setTen_nhkb_nhan(String ten_nhkb_nhan) {
        this.ten_nhkb_nhan = ten_nhkb_nhan;
    }

    public String getTen_nhkb_nhan() {
        return ten_nhkb_nhan;
    }

    public void setSo_tk_nhan(String so_tk_nhan) {
        this.so_tk_nhan = so_tk_nhan;
    }

    public String getSo_tk_nhan() {
        return so_tk_nhan;
    }

    public void setSo_ltt(String so_ltt) {
        this.so_ltt = so_ltt;
    }

    public String getSo_ltt() {
        return so_ltt;
    }

    public void setSo_tien(String so_tien) {
        this.so_tien = so_tien;
    }

    public String getSo_tien() {
        return so_tien;
    }

    public void setTrang_thai_lttctiet(String trang_thai_lttctiet) {
        this.trang_thai_lttctiet = trang_thai_lttctiet;
    }

    public String getTrang_thai_lttctiet() {
        return trang_thai_lttctiet;
    }

    public void setLoai_lenh(String loai_lenh) {
        this.loai_lenh = loai_lenh;
    }

    public String getLoai_lenh() {
        return loai_lenh;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public void setSo_tien_di(String so_tien_di) {
        this.so_tien_di = so_tien_di;
    }

    public String getSo_tien_di() {
        return so_tien_di;
    }

    public void setTong_di(String tong_di) {
        this.tong_di = tong_di;
    }

    public String getTong_di() {
        return tong_di;
    }

    public void setSo_tien_den(String so_tien_den) {
        this.so_tien_den = so_tien_den;
    }

    public String getSo_tien_den() {
        return so_tien_den;
    }

    public void setTong_den(String tong_den) {
        this.tong_den = tong_den;
    }

    public String getTong_den() {
        return tong_den;
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

    public void setLy_do(String ly_do) {
        this.ly_do = ly_do;
    }

    public String getLy_do() {
        return ly_do;
    }

    public void setSo_tien_qtoan_thu(String so_tien_qtoan_thu) {
        this.so_tien_qtoan_thu = so_tien_qtoan_thu;
    }

    public String getSo_tien_qtoan_thu() {
        return so_tien_qtoan_thu;
    }

    public void setTong_qtoan_thu(String tong_qtoan_thu) {
        this.tong_qtoan_thu = tong_qtoan_thu;
    }

    public String getTong_qtoan_thu() {
        return tong_qtoan_thu;
    }

    public void setSo_tien_qtoan_chi(String so_tien_qtoan_chi) {
        this.so_tien_qtoan_chi = so_tien_qtoan_chi;
    }

    public String getSo_tien_qtoan_chi() {
        return so_tien_qtoan_chi;
    }

    public void setTong_qtoan_chi(String tong_qtoan_chi) {
        this.tong_qtoan_chi = tong_qtoan_chi;
    }

    public String getTong_qtoan_chi() {
        return tong_qtoan_chi;
    }

    public void setSo_tien_lai_phi(String so_tien_lai_phi) {
        this.so_tien_lai_phi = so_tien_lai_phi;
    }

    public String getSo_tien_lai_phi() {
        return so_tien_lai_phi;
    }

    public void setTong_lai_phi(String tong_lai_phi) {
        this.tong_lai_phi = tong_lai_phi;
    }

    public String getTong_lai_phi() {
        return tong_lai_phi;
    }

    public void setSo_tien_ty_gia(String so_tien_ty_gia) {
        this.so_tien_ty_gia = so_tien_ty_gia;
    }

    public String getSo_tien_ty_gia() {
        return so_tien_ty_gia;
    }

    public void setTong_ty_gia(String tong_ty_gia) {
        this.tong_ty_gia = tong_ty_gia;
    }

    public String getTong_ty_gia() {
        return tong_ty_gia;
    }
}
