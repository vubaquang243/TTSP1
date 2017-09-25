package com.seatech.ttsp.tsoSDuCOT;

import java.util.Date;

public class TSoKhoBacVO {
    private String ten_ts;
    private String giatri_ts;
    private String mo_ta;
    private String cho_phep_sua;
    private String kb_id;
    private Date ngay_cap_nhat;
    private String dvi_sua;
    private String ma_nsd;

    public void setTen_ts(String ten_ts) {
        this.ten_ts = ten_ts;
    }

    public String getTen_ts() {
        return ten_ts;
    }

    public void setGiatri_ts(String giatri_ts) {
        this.giatri_ts = giatri_ts;
    }

    public String getGiatri_ts() {
        return giatri_ts;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setCho_phep_sua(String cho_phep_sua) {
        this.cho_phep_sua = cho_phep_sua;
    }

    public String getCho_phep_sua() {
        return cho_phep_sua;
    }

    public void setKb_id(String kb_id) {
        this.kb_id = kb_id;
    }

    public String getKb_id() {
        return kb_id;
    }

    public void setNgay_cap_nhat(Date ngay_cap_nhat) {
        this.ngay_cap_nhat = ngay_cap_nhat;
    }

    public Date getNgay_cap_nhat() {
        return ngay_cap_nhat;
    }

    public void setDvi_sua(String dvi_sua) {
        this.dvi_sua = dvi_sua;
    }

    public String getDvi_sua() {
        return dvi_sua;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }
}
