package com.seatech.ttsp.dchieu;

import java.math.BigDecimal;
/**
 * @modify: thuongDT
 * @modify-date: 04/10/2017
 * @see: bo sung them the hien chi tiet chenh lech 
 * @see: 20171004
 **/
public class KQDCChiTietVO {
  private String id;
  private String bkq_id;
  private String bk_id;
  private String mt_id;
  private String send_date;
  private String f20;
  private String f21;

  private BigDecimal f32as3;
  private String ngay_ts;
  private String ghi_chu;
  private String mt_type;
  private String app_type;

  private String sai_yeu_to;
  private String trang_thai;
  private String insert_date;
  private String ngay_ct;
  private String ltt;
  private String dts_hoi;
  private String dts_tra_loi;

  private String ket_qua;
  private String lan_dc;
  private String ngay_dc;
  private String send_bank;
  private String  ten_send_bank;

  private String receive_bank;
  private String ten;
  private String created_date;
  private String creator;
  private String manager;

  private String verified_date;
  private BigDecimal sodu_kbnn;
  private long chenh_lech;
  
  private long mon_thu_tcong_kbnn;
  private long mon_chi_tcong_kbnn;
  private BigDecimal tien_thu_tcong_kbnn;
  private BigDecimal tien_chi_tcong_kbnn;
  private String di_den;
  private String ltt_dts;
  private String tthai_duyet;
  
  private String loai_tien;
  private String ma_nt;//Ma ngoai te
  
  //20171004 bo sung them chi tiet chenh lech
  private String ldo_clech;
  private String ctiet_clech;


    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBkq_id(String bkq_id) {
        this.bkq_id = bkq_id;
    }

    public String getBkq_id() {
        return bkq_id;
    }

    public void setBk_id(String bk_id) {
        this.bk_id = bk_id;
    }

    public String getBk_id() {
        return bk_id;
    }

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }

    public String getMt_id() {
        return mt_id;
    }

    public void setSend_date(String send_date) {
        this.send_date = send_date;
    }

    public String getSend_date() {
        return send_date;
    }

    public void setF20(String f20) {
        this.f20 = f20;
    }

    public String getF20() {
        return f20;
    }

    public void setF21(String f21) {
        this.f21 = f21;
    }

    public String getF21() {
        return f21;
    }

    public void setNgay_ts(String ngay_ts) {
        this.ngay_ts = ngay_ts;
    }

    public String getNgay_ts() {
        return ngay_ts;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setMt_type(String mt_type) {
        this.mt_type = mt_type;
    }

    public String getMt_type() {
        return mt_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setSai_yeu_to(String sai_yeu_to) {
        this.sai_yeu_to = sai_yeu_to;
    }

    public String getSai_yeu_to() {
        return sai_yeu_to;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setNgay_ct(String ngay_ct) {
        this.ngay_ct = ngay_ct;
    }

    public String getNgay_ct() {
        return ngay_ct;
    }

    public void setLtt(String ltt) {
        this.ltt = ltt;
    }

    public String getLtt() {
        return ltt;
    }

    public void setDts_hoi(String dts_hoi) {
        this.dts_hoi = dts_hoi;
    }

    public String getDts_hoi() {
        return dts_hoi;
    }

    public void setDts_tra_loi(String dts_tra_loi) {
        this.dts_tra_loi = dts_tra_loi;
    }

    public String getDts_tra_loi() {
        return dts_tra_loi;
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

    public void setTen_send_bank(String ten_send_bank) {
        this.ten_send_bank = ten_send_bank;
    }

    public String getTen_send_bank() {
        return ten_send_bank;
    }

    public void setReceive_bank(String receive_bank) {
        this.receive_bank = receive_bank;
    }

    public String getReceive_bank() {
        return receive_bank;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager() {
        return manager;
    }

    public void setVerified_date(String verified_date) {
        this.verified_date = verified_date;
    }

    public String getVerified_date() {
        return verified_date;
    }
   
    public void setChenh_lech(long chenh_lech) {
        this.chenh_lech = chenh_lech;
    }

    public long getChenh_lech() {
        return chenh_lech;
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

    public void setDi_den(String di_den) {
        this.di_den = di_den;
    }

    public String getDi_den() {
        return di_den;
    }

    public void setLtt_dts(String ltt_dts) {
        this.ltt_dts = ltt_dts;
    }

    public String getLtt_dts() {
        return ltt_dts;
    }

    public void setTthai_duyet(String tthai_duyet) {
        this.tthai_duyet = tthai_duyet;
    }

    public String getTthai_duyet() {
        return tthai_duyet;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }

    public void setF32as3(BigDecimal f32as3) {
        this.f32as3 = f32as3;
    }

    public BigDecimal getF32as3() {
        return f32as3;
    }

    public void setSodu_kbnn(BigDecimal sodu_kbnn) {
        this.sodu_kbnn = sodu_kbnn;
    }

    public BigDecimal getSodu_kbnn() {
        return sodu_kbnn;
    }

    public void setTien_thu_tcong_kbnn(BigDecimal tien_thu_tcong_kbnn) {
        this.tien_thu_tcong_kbnn = tien_thu_tcong_kbnn;
    }

    public BigDecimal getTien_thu_tcong_kbnn() {
        return tien_thu_tcong_kbnn;
    }

    public void setTien_chi_tcong_kbnn(BigDecimal tien_chi_tcong_kbnn) {
        this.tien_chi_tcong_kbnn = tien_chi_tcong_kbnn;
    }

    public BigDecimal getTien_chi_tcong_kbnn() {
        return tien_chi_tcong_kbnn;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }

    public void setLdo_clech(String ldo_clech) {
        this.ldo_clech = ldo_clech;
    }

    public String getLdo_clech() {
        return ldo_clech;
    }

    public void setCtiet_clech(String ctiet_clech) {
        this.ctiet_clech = ctiet_clech;
    }

    public String getCtiet_clech() {
        return ctiet_clech;
    }
}
