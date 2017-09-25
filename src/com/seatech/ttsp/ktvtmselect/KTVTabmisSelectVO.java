package com.seatech.ttsp.ktvtmselect;

public class KTVTabmisSelectVO {
    //table Chon_ktv_tabmis
    private Long nsd_id;
    private Long ktv_id;
    private String tgian_chon;
    private Long ttv_id;
    private String ma;
    private String ten;
    private String ma_nsd;
    private String ten_nsd;
    private Long ischeck;


    public void setNsd_id(Long nsd_id) {
        this.nsd_id = nsd_id;
    }

    public Long getNsd_id() {
        return nsd_id;
    }

    public void setKtv_id(Long ktv_id) {
        this.ktv_id = ktv_id;
    }

    public Long getKtv_id() {
        return ktv_id;
    }

    public void setTgian_chon(String tgian_chon) {
        this.tgian_chon = tgian_chon;
    }

    public String getTgian_chon() {
        return tgian_chon;
    }

    public void setTtv_id(Long ttv_id) {
        this.ttv_id = ttv_id;
    }

    public Long getTtv_id() {
        return ttv_id;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }

    public void setTen_nsd(String ten_nsd) {
        this.ten_nsd = ten_nsd;
    }

    public String getTen_nsd() {
        return ten_nsd;
    }

    public void setIscheck(Long ischeck) {
        this.ischeck = ischeck;
    }

    public Long getIscheck() {
        return ischeck;
    }
}
