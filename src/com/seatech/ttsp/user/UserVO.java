package com.seatech.ttsp.user;

import java.util.Date;

public class UserVO {
    private Long id;
    private Long kb_id;
    private String ten_nsd;
    private String ma_tabmis;
    private String chuc_danh;
    private String ma_nsd;
    private String trang_thai;
    private String ngay_tao;
    private Long nguoi_tao;
    private String ip_truycap;
    private String session_id;
    private Date tgian_truycap;
    private String domain;
    private String sua_xoa;
    private String mac_address;
    private String ten_may_truycap;
    private String user_may_truycap;

    // sp_nsd_nhom trong UserVO getUser
    private Long nhom_id;


    private String ma;
    private String ma_cha;
    private Long login_failure;

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

    public void setTen_nsd(String ten_nsd) {
        this.ten_nsd = ten_nsd;
    }

    public String getTen_nsd() {
        return ten_nsd;
    }

    public void setMa_tabmis(String ma_tabmis) {
        this.ma_tabmis = ma_tabmis;
    }

    public String getMa_tabmis() {
        return ma_tabmis;
    }

    public void setChuc_danh(String chuc_danh) {
        this.chuc_danh = chuc_danh;
    }

    public String getChuc_danh() {
        return chuc_danh;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setNgay_tao(String ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public String getNgay_tao() {
        return ngay_tao;
    }

    public void setNguoi_tao(Long nguoi_tao) {
        this.nguoi_tao = nguoi_tao;
    }

    public Long getNguoi_tao() {
        return nguoi_tao;
    }

    public void setIp_truycap(String ip_truycap) {
        this.ip_truycap = ip_truycap;
    }

    public String getIp_truycap() {
        return ip_truycap;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setTgian_truycap(Date tgian_truycap) {
        this.tgian_truycap = tgian_truycap;
    }

    public Date getTgian_truycap() {
        return tgian_truycap;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public void setMa_cha(String ma_cha) {
        this.ma_cha = ma_cha;
    }

    public String getMa_cha() {
        return ma_cha;
    }

    public void setSua_xoa(String sua_xoa) {
        this.sua_xoa = sua_xoa;
    }

    public String getSua_xoa() {
        return sua_xoa;
    }

    public void setNhom_id(Long nhom_id) {
        this.nhom_id = nhom_id;
    }

    public Long getNhom_id() {
        return nhom_id;
    }

    public void setLogin_failure(Long login_failure) {
        this.login_failure = login_failure;
    }

    public Long getLogin_failure() {
        return login_failure;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setTen_may_truycap(String ten_may_truycap) {
        this.ten_may_truycap = ten_may_truycap;
    }

    public String getTen_may_truycap() {
        return ten_may_truycap;
    }

    public void setUser_may_truycap(String user_may_truycap) {
        this.user_may_truycap = user_may_truycap;
    }

    public String getUser_may_truycap() {
        return user_may_truycap;
    }
}
