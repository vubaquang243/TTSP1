package com.seatech.ttsp.nhomnsd;

import java.util.Date;

public class PhanNhomVO  {
    private Long id;
    private Long kb_id;
    private String ten_nsd;
    private String ma_tabmis;
    private String chuc_danh;
    private String ma_nsd;
    private String trang_thai;
    private Date ngay_tao;
    private Long nguoi_tao;
    private String ip_truycap;
    private String session_id;
    private Date tgian_truycap;
    private Long nsd_id;
    /// nhom nguoi su dung
    
    private Long nhom_id;
    private Date created_date;
    private Long created_by;
  

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

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public Date getNgay_tao() {
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

    public void setNhom_id(Long nhom_id) {
        this.nhom_id = nhom_id;
    }

    public Long getNhom_id() {
        return nhom_id;
    }

    public void setCreated_by(Long created_by) {
        this.created_by = created_by;
    }

    public Long getCreated_by() {
        return created_by;
    }

    public void setNsd_id(Long nsd_id) {
        this.nsd_id = nsd_id;
    }

    public Long getNsd_id() {
        return nsd_id;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getCreated_date() {
        return created_date;
    }
}
