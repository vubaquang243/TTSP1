package com.seatech.ttsp.cutofftime;

import com.seatech.ttsp.cutoftime.TSoCutOfTimeVO;

import java.util.Collection;
import java.util.Date;

public class CutOffTimeVO {
   
    private Long id;
    private Long cot_id;
    private String nhkb_nhan;
    private String nhkb_chuyen;
    private String loai_cot;
    private String ma_kb_huyen;
    private String ten_kb_huyen;
    private String nguoi_lap;
    private Date ngay_lap;
    private String nguoi_ks;
    private Date ngay_ks;
    private Date tgian_cot_cu;
    private Date tgian_cot_moi;
    private String lydo_cot;
    private String lydo_daylai;
    private String lydo_ko_dongy;
    private String trang_thai;
    private String rv_meaning;
    private String ten_nguoi_lap;
    private String ten_nguoi_ks;
    private String ma_kb_tinh;
    private String dong_y;
    private String nguoi_lap_nh;
    private Date ngay_lap_nh;
    private String nguoi_ks_nh;
    private Date ngay_ks_nh;
    private Date ngay_nhan;
    private String lydo_cot_nh;
    private String cur_cot;
    private String new_cot;
    private String ma_nsd;
    private String msg_id;
    private String chuky_ktt;
    private String ma_cn_nh;
    private String tn_cu;
    private String tn_moi;
    private String ma_nsd_ks;
    private String ma_nh;
  private String cot_cu;
  private String cot_moi;
  private String cot_cu_list;
  private String msg_reply_bank;
  private String msg_reply_tcs;
  private String ten_kb;
  private String lydo_traloi;
    private Collection<TSoCutOfTimeVO> list_ma_nh_kb_huyen;
    
    

    public CutOffTimeVO() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setCot_id(Long cot_id) {
        this.cot_id = cot_id;
    }

    public Long getCot_id() {
        return cot_id;
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

    public void setLoai_cot(String loai_cot) {
        this.loai_cot = loai_cot;
    }

    public String getLoai_cot() {
        return loai_cot;
    }

    public void setMa_kb_huyen(String ma_kb_huyen) {
        this.ma_kb_huyen = ma_kb_huyen;
    }

    public String getMa_kb_huyen() {
        return ma_kb_huyen;
    }

    public void setNguoi_lap(String nguoi_lap) {
        this.nguoi_lap = nguoi_lap;
    }

    public String getNguoi_lap() {
        return nguoi_lap;
    }

    public void setNgay_lap(Date ngay_lap) {
        this.ngay_lap = ngay_lap;
    }

    public Date getNgay_lap() {
        return ngay_lap;
    }

    public void setNguoi_ks(String nguoi_ks) {
        this.nguoi_ks = nguoi_ks;
    }

    public String getNguoi_ks() {
        return nguoi_ks;
    }

    public void setNgay_ks(Date ngay_ks) {
        this.ngay_ks = ngay_ks;
    }

    public Date getNgay_ks() {
        return ngay_ks;
    }

    public void setTgian_cot_cu(Date tgian_cot_cu) {
        this.tgian_cot_cu = tgian_cot_cu;
    }

    public Date getTgian_cot_cu() {
        return tgian_cot_cu;
    }

    public void setTgian_cot_moi(Date tgian_cot_moi) {
        this.tgian_cot_moi = tgian_cot_moi;
    }

    public Date getTgian_cot_moi() {
        return tgian_cot_moi;
    }

    public void setLydo_cot(String lydo_cot) {
        this.lydo_cot = lydo_cot;
    }

    public String getLydo_cot() {
        return lydo_cot;
    }

    public void setLydo_daylai(String lydo_daylai) {
        this.lydo_daylai = lydo_daylai;
    }

    public String getLydo_daylai() {
        return lydo_daylai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setRv_meaning(String rv_meaning) {
        this.rv_meaning = rv_meaning;
    }

    public String getRv_meaning() {
        return rv_meaning;
    }

    public void setTen_nguoi_lap(String ten_nguoi_lap) {
        this.ten_nguoi_lap = ten_nguoi_lap;
    }

    public String getTen_nguoi_lap() {
        return ten_nguoi_lap;
    }

    public void setTen_nguoi_ks(String ten_nguoi_ks) {
        this.ten_nguoi_ks = ten_nguoi_ks;
    }

    public String getTen_nguoi_ks() {
        return ten_nguoi_ks;
    }

    public void setMa_kb_tinh(String ma_kb_tinh) {
        this.ma_kb_tinh = ma_kb_tinh;
    }

    public String getMa_kb_tinh() {
        return ma_kb_tinh;
    }

    public void setDong_y(String dong_y) {
        this.dong_y = dong_y;
    }

    public String getDong_y() {
        return dong_y;
    }

    public void setNguoi_lap_nh(String nguoi_lap_nh) {
        this.nguoi_lap_nh = nguoi_lap_nh;
    }

    public String getNguoi_lap_nh() {
        return nguoi_lap_nh;
    }

    public void setNgay_lap_nh(Date ngay_lap_nh) {
        this.ngay_lap_nh = ngay_lap_nh;
    }

    public Date getNgay_lap_nh() {
        return ngay_lap_nh;
    }

    public void setNguoi_ks_nh(String nguoi_ks_nh) {
        this.nguoi_ks_nh = nguoi_ks_nh;
    }

    public String getNguoi_ks_nh() {
        return nguoi_ks_nh;
    }

    public void setNgay_ks_nh(Date ngay_ks_nh) {
        this.ngay_ks_nh = ngay_ks_nh;
    }

    public Date getNgay_ks_nh() {
        return ngay_ks_nh;
    }

    public void setLydo_ko_dongy(String lydo_ko_dongy) {
        this.lydo_ko_dongy = lydo_ko_dongy;
    }

    public String getLydo_ko_dongy() {
        return lydo_ko_dongy;
    }

    public void setNgay_nhan(Date ngay_nhan) {
        this.ngay_nhan = ngay_nhan;
    }

    public Date getNgay_nhan() {
        return ngay_nhan;
    }

    public void setTen_kb_huyen(String ten_kb_huyen) {
        this.ten_kb_huyen = ten_kb_huyen;
    }

    public String getTen_kb_huyen() {
        return ten_kb_huyen;
    }

    public void setLydo_cot_nh(String lydo_cot_nh) {
        this.lydo_cot_nh = lydo_cot_nh;
    }

    public String getLydo_cot_nh() {
        return lydo_cot_nh;
    }

    public void setCur_cot(String cur_cot) {
        this.cur_cot = cur_cot;
    }

    public String getCur_cot() {
        return cur_cot;
    }

    public void setNew_cot(String new_cot) {
        this.new_cot = new_cot;
    }

    public String getNew_cot() {
        return new_cot;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setChuky_ktt(String chuky_ldptttt) {
        this.chuky_ktt = chuky_ldptttt;
    }

    public String getChuky_ktt() {
        return chuky_ktt;
    }

    public void setMa_cn_nh(String ma_cn_nh) {
        this.ma_cn_nh = ma_cn_nh;
    }

    public String getMa_cn_nh() {
        return ma_cn_nh;
    }

    public void setList_ma_nh_kb_huyen(Collection list_ma_nh_kb_huyen) {
        this.list_ma_nh_kb_huyen = list_ma_nh_kb_huyen;
    }

    public Collection getList_ma_nh_kb_huyen() {
        return list_ma_nh_kb_huyen;
    }

    public void setTn_cu(String tn_cu) {
        this.tn_cu = tn_cu;
    }

    public String getTn_cu() {
        return tn_cu;
    }

    public void setTn_moi(String tn_moi) {
        this.tn_moi = tn_moi;
    }

    public String getTn_moi() {
        return tn_moi;
    }

    public void setMa_nsd_ks(String ma_nsd_ks) {
        this.ma_nsd_ks = ma_nsd_ks;
    }

    public String getMa_nsd_ks() {
        return ma_nsd_ks;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setCot_cu(String cot_cu) {
        this.cot_cu = cot_cu;
    }

    public String getCot_cu() {
        return cot_cu;
    }

    public void setCot_moi(String cot_moi) {
        this.cot_moi = cot_moi;
    }

    public String getCot_moi() {
        return cot_moi;
    }

    public void setCot_cu_list(String cot_cu_list) {
        this.cot_cu_list = cot_cu_list;
    }

    public String getCot_cu_list() {
        return cot_cu_list;
    }

    public void setMsg_reply_bank(String msg_reply_bank) {
        this.msg_reply_bank = msg_reply_bank;
    }

    public String getMsg_reply_bank() {
        return msg_reply_bank;
    }

    public void setMsg_reply_tcs(String msg_reply_tcs) {
        this.msg_reply_tcs = msg_reply_tcs;
    }

    public String getMsg_reply_tcs() {
        return msg_reply_tcs;
    }

   

    public void setTen_kb(String ten_kb) {
        this.ten_kb = ten_kb;
    }

    public String getTen_kb() {
        return ten_kb;
    }

    public void setLydo_traloi(String lydo_traloi) {
        this.lydo_traloi = lydo_traloi;
    }

    public String getLydo_traloi() {
        return lydo_traloi;
    }
}
