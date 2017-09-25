package com.seatech.ttsp.ktvtmselect.form;

import org.apache.struts.action.ActionForm;

public class KTVTabmisSelectForm extends ActionForm{
  //table Chon_ktv_tabmis
  private String nsd_id;
  private String nsd_name;
  private String kb_id;
  private String kb_name;
  private String ktv_id;
  private String tgian_chon;
  private String ma;
  private String ten;
  private String pageNumber;
  private String trang_thai_chon;
  private String trangthai;
  private boolean isCheckSort;
      

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

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setNsd_name(String nsd_name) {
        this.nsd_name = nsd_name;
    }

    public String getNsd_name() {
        return nsd_name;
    }

    public void setKb_id(String kb_id) {
        this.kb_id = kb_id;
    }

    public String getKb_id() {
        return kb_id;
    }

    public void setKb_name(String kb_name) {
        this.kb_name = kb_name;
    }

    public String getKb_name() {
        return kb_name;
    }

    public void setTrang_thai_chon(String trang_thai_chon) {
        this.trang_thai_chon = trang_thai_chon;
    }

    public String getTrang_thai_chon() {
        return trang_thai_chon;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setIsCheckSort(boolean isCheckSort) {
        this.isCheckSort = isCheckSort;
    }

    public boolean isIsCheckSort() {
        return isCheckSort;
    }
}
