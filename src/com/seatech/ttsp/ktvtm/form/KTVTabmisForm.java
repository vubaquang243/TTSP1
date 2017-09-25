package com.seatech.ttsp.ktvtm.form;


import org.apache.struts.action.ActionForm;


public class KTVTabmisForm extends ActionForm {
    private String id;
    private String ma;
    private String ten;
    private String nguoi_tao;
    private String ngay_tao;
    private String ma_nsd;
    private String ten_nsd;
    private String id_kb;
    private String ma_kb;
    private String ten_kb;
    private String trangthai;
    private String pageNumber;
    private String kb_id;
    
    
  //table Chon_ktv_tabmis
  private String nsd_id;
  private String ktv_id;
  private String tgian_chon;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setNguoi_tao(String nguoi_tao) {
        this.nguoi_tao = nguoi_tao;
    }

    public String getNguoi_tao() {
        return nguoi_tao;
    }

    public void setNgay_tao(String ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public String getNgay_tao() {
        return ngay_tao;
    }


    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setTen_nsd(String ten_nsd) {
        this.ten_nsd = ten_nsd;
    }

    public String getTen_nsd() {
        return ten_nsd;
    }

    public void setTen_kb(String ten_kb) {
        this.ten_kb = ten_kb;
    }

    public String getTen_kb() {
        return ten_kb;
    }

    public void setNsd_id(String nsd_id) {
        this.nsd_id = nsd_id;
    }

    public String getNsd_id() {
        return nsd_id;
    }

    public void setKtv_id(String ktv_id) {
        this.ktv_id = ktv_id;
    }

    public String getKtv_id() {
        return ktv_id;
    }

    public void setTgian_chon(String tgian_chon) {
        this.tgian_chon = tgian_chon;
    }

    public String getTgian_chon() {
        return tgian_chon;
    }

    public void setId_kb(String id_kb) {
        this.id_kb = id_kb;
    }

    public String getId_kb() {
        return id_kb;
    }

    public void setKb_id(String kb_id) {
        this.kb_id = kb_id;
    }

    public String getKb_id() {
        return kb_id;
    }
}
