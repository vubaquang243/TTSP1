package com.seatech.ttsp.tknhkb;

import com.seatech.framework.datamanager.EncodeUtil;

public class TKNHKBacVO {
    private Long id;
    private Long kb_id;
    private String so_tk;
    private Long nh_id;
    private String trang_thai;
    private Long han_muc_no;
    private Long han_muc_co;
    private String hieu_luc_tungay;
    private String hieu_luc_den_ngay;
    private String ma_nh;
    private String ten;
    private String ma_kb;
    private String ten_kb;
    private String shkb;
    private String ten_nh;
    private String loai_tk;
    private String loai_gd;
    private String ghi_chu;
    private String id_tk;
    private String ma_nt;
    private String quyet_toan;
    private String comboBoxPresentTK;
    
  
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setKb_id(Long kb_id) {
        this.kb_id = kb_id;
    }

    public Long getKb_id() {
        return kb_id;
    }

    public void setSo_tk(String so_tk) {
        this.so_tk = so_tk;
    }

    public String getSo_tk() {
        return so_tk;
    }

    public void setNh_id(Long nh_id) {
        this.nh_id = nh_id;
    }

    public Long getNh_id() {
        return nh_id;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setHan_muc_no(Long han_muc_no) {
        this.han_muc_no = han_muc_no;
    }

    public Long getHan_muc_no() {
        return han_muc_no;
    }

    public void setHan_muc_co(Long han_muc_co) {
        this.han_muc_co = han_muc_co;
    }

    public Long getHan_muc_co() {
        return han_muc_co;
    }

    public void setHieu_luc_tungay(String hieu_luc_tungay) {
        this.hieu_luc_tungay = hieu_luc_tungay;
    }

    public String getHieu_luc_tungay() {
        return hieu_luc_tungay;
    }

    public void setHieu_luc_den_ngay(String hieu_luc_den_ngay) {
        this.hieu_luc_den_ngay = hieu_luc_den_ngay;
    }

    public String getHieu_luc_den_ngay() {
        return hieu_luc_den_ngay;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setTen_kb(String ten_kb) {
        this.ten_kb = ten_kb;
    }

    public String getTen_kb() {
        return ten_kb;
    }

    public void setLoai_gd(String loai_gd) {
        this.loai_gd = loai_gd;
    }

    public String getLoai_gd() {
        return loai_gd;
    }

    public void setShkb(String shkb) {
        this.shkb = shkb;
    }

    public String getShkb() {
        return shkb;
    }

    public void setTen_nh(String ten_nh) {
        this.ten_nh = ten_nh;
    }

    public String getTen_nh() {
        return ten_nh;
    }

    public void setLoai_tk(String loai_tk) {
        this.loai_tk = loai_tk;
    }

    public String getLoai_tk() {
        return loai_tk;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setId_tk(String id_tk) {
        this.id_tk = id_tk;
    }

    public String getId_tk() {
        return id_tk;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }

    public void setQuyet_toan(String quyet_toan) {
        this.quyet_toan = quyet_toan;
    }

    public String getQuyet_toan() {
        return quyet_toan;
    }

    public String getComboBoxPresentTK() {
        String typeAccount = "";
        if(loai_tk.equals("01")) typeAccount = "TG";
        if(loai_tk.equals("02")) typeAccount = "TT";
        if(loai_tk.equals("03")) typeAccount = "CT";
        return "( "+typeAccount+" - "+this.ma_nt +" ) - " + this.so_tk;
    }
}
