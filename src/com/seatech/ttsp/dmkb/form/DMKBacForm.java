package com.seatech.ttsp.dmkb.form;

import org.apache.struts.action.ActionForm;

public class DMKBacForm extends ActionForm{
    private String ten;
    private String ma;
    private String id;
    
    // hongnn
    private String nhkb_tinh;
    private String nhkb_huyen;
    private String id_cha;
    private String ngan_hang;
    private String ma_dv;
    private String pageNumber;
    private String nguoi_tao;

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setId_cha(String id_cha) {
        this.id_cha = id_cha;
    }

    public String getId_cha() {
        return id_cha;
    }

    public void setNgan_hang(String ngan_hang) {
        this.ngan_hang = ngan_hang;
    }

    public String getNgan_hang() {
        return ngan_hang;
    }

    public void setMa_dv(String ma_dv) {
        this.ma_dv = ma_dv;
    }

    public String getMa_dv() {
        return ma_dv;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setNguoi_tao(String nguoi_tao) {
        this.nguoi_tao = nguoi_tao;
    }

    public String getNguoi_tao() {
        return nguoi_tao;
    }
}
