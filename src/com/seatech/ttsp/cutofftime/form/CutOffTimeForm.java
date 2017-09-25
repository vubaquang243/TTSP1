package com.seatech.ttsp.cutofftime.form;

import com.seatech.framework.strustx.AppForm;

import com.seatech.ttsp.cutoftime.TSoCutOfTimeVO;

import java.util.Collection;
import java.util.Date;


public class CutOffTimeForm extends AppForm {
    private Long id;
    private Long cot_id;
    private String nhkb_nhan;
    private String nhkb_chuyen;
    private String ma_nh;
    private String loai_cot;
    private String ma_kb_huyen;
    private String nguoi_lap;
    private String ten_nguoi_lap;
    private String f_ngay_lap;
    private String nguoi_ks;
    private String ten_nguoi_ks;
    private String f_ngay_ks;
    private String f_ngay_cot;
    private String gio_cot_moi;
    private String gio_cot_cu;
    private String lydo_cot;
    private String lydo_daylai;
    private String lydo_ko_dongy;
    private String trang_thai;
    private String ma_kb_tinh;
    private String dong_y;
    private String nguoi_lap_nh;
    private String f_ngay_lap_nh;
    private String nguoi_ks_nh;
    private String f_ngay_ks_nh;
    private String lydo_cot_nh;
    private String chuky_ktt;
    private String ma_3so_nh;
    private String maxTime;
  private String tn_cu;
  private String tn_moi;
  private String ma_kb_huyen_list;
  private String ten_kb;
    
    

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

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
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


    public void setNguoi_ks(String nguoi_ks) {
        this.nguoi_ks = nguoi_ks;
    }

    public String getNguoi_ks() {
        return nguoi_ks;
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


    public void setGio_cot_moi(String gio_cot_moi) {
        this.gio_cot_moi = gio_cot_moi;
    }

    public String getGio_cot_moi() {
        return gio_cot_moi;
    }

    public void setGio_cot_cu(String gio_cot_cu) {
        this.gio_cot_cu = gio_cot_cu;
    }

    public String getGio_cot_cu() {
        return gio_cot_cu;
    }

    public void setMa_kb_tinh(String ma_kb_tinh) {
        this.ma_kb_tinh = ma_kb_tinh;
    }

    public String getMa_kb_tinh() {
        return ma_kb_tinh;
    }

    public void setF_ngay_lap(String f_ngay_lap) {
        this.f_ngay_lap = f_ngay_lap;
    }

    public String getF_ngay_lap() {
        return f_ngay_lap;
    }

    public void setF_ngay_ks(String f_ngay_ks) {
        this.f_ngay_ks = f_ngay_ks;
    }

    public String getF_ngay_ks() {
        return f_ngay_ks;
    }

    public void setF_ngay_cot(String f_ngay_cot) {
        this.f_ngay_cot = f_ngay_cot;
    }

    public String getF_ngay_cot() {
        return f_ngay_cot;
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

    public void setDong_y(String dong_y) {
        this.dong_y = dong_y;
    }

    public String getDong_y() {
        return dong_y;
    }

    public void setLydo_ko_dongy(String lydo_ko_dongy) {
        this.lydo_ko_dongy = lydo_ko_dongy;
    }

    public String getLydo_ko_dongy() {
        return lydo_ko_dongy;
    }

    public void setNguoi_lap_nh(String nguoi_lap_nh) {
        this.nguoi_lap_nh = nguoi_lap_nh;
    }

    public String getNguoi_lap_nh() {
        return nguoi_lap_nh;
    }

    
    public void setNguoi_ks_nh(String nguoi_ks_nh) {
        this.nguoi_ks_nh = nguoi_ks_nh;
    }

    public String getNguoi_ks_nh() {
        return nguoi_ks_nh;
    }

  
    public void setLydo_cot_nh(String lydo_cot_nh) {
        this.lydo_cot_nh = lydo_cot_nh;
    }

    public String getLydo_cot_nh() {
        return lydo_cot_nh;
    }

    public void setF_ngay_lap_nh(String f_ngay_lap_nh) {
        this.f_ngay_lap_nh = f_ngay_lap_nh;
    }

    public String getF_ngay_lap_nh() {
        return f_ngay_lap_nh;
    }

    public void setF_ngay_ks_nh(String f_ngay_ks_nh) {
        this.f_ngay_ks_nh = f_ngay_ks_nh;
    }

    public String getF_ngay_ks_nh() {
        return f_ngay_ks_nh;
    }
	public void setChuky_ktt(String chuky_ktt) {
        this.chuky_ktt = chuky_ktt;
    }

    public String getChuky_ktt() {
        return chuky_ktt;
    }
    public void setMa_3so_nh(String ma_3so_nh) {
        this.ma_3so_nh = ma_3so_nh;
    }

    public String getMa_3so_nh() {
        return ma_3so_nh;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public String getMaxTime() {
        return maxTime;
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

    public void setMa_kb_huyen_list(String ma_kb_huyen_list) {
        this.ma_kb_huyen_list = ma_kb_huyen_list;
    }

    public String getMa_kb_huyen_list() {
        return ma_kb_huyen_list;
    }

   

    public void setTen_kb(String ten_kb) {
        this.ten_kb = ten_kb;
    }

    public String getTen_kb() {
        return ten_kb;
    }
}
