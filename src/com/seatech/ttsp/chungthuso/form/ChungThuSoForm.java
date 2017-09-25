package com.seatech.ttsp.chungthuso.form;

import org.apache.struts.action.ActionForm;

public class ChungThuSoForm extends ActionForm{
  private  String id;            
  private  String serial;      
  private  String nha_cung_cap;
  private  String trang_thai;
  private  String ten_nguoi_duoc_cap;
  private  String ma_nguoi_duoc_cap;
  private  String hieu_luc_tu_ngay;
  private  String hieu_luc_den_ngay;
  private  String noi_dung;
  private  String user_name;
  private  String kb_id;
  private  Boolean search_cts;
  private  String pageNumber;
 
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getSerial() {
        return serial;
    }

    public void setNha_cung_cap(String nha_cung_cap) {
        this.nha_cung_cap = nha_cung_cap;
    }

    public String getNha_cung_cap() {
        return nha_cung_cap;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setHieu_luc_tu_ngay(String hieu_luc_tu_ngay) {
        this.hieu_luc_tu_ngay = hieu_luc_tu_ngay;
    }

    public String getHieu_luc_tu_ngay() {
        return hieu_luc_tu_ngay;
    }

    public void setHieu_luc_den_ngay(String hieu_luc_den_ngay) {
        this.hieu_luc_den_ngay = hieu_luc_den_ngay;
    }

    public String getHieu_luc_den_ngay() {
        return hieu_luc_den_ngay;
    }

    public void setTen_nguoi_duoc_cap(String ten_nguoi_duoc_cap) {
        this.ten_nguoi_duoc_cap = ten_nguoi_duoc_cap;
    }

    public String getTen_nguoi_duoc_cap() {
        return ten_nguoi_duoc_cap;
    }

    public void setMa_nguoi_duoc_cap(String ma_nguoi_duoc_cap) {
        this.ma_nguoi_duoc_cap = ma_nguoi_duoc_cap;
    }

    public String getMa_nguoi_duoc_cap() {
        return ma_nguoi_duoc_cap;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setSearch_cts(Boolean search_cts) {
        this.search_cts = search_cts;
    }

    public Boolean getSearch_cts() {
        return search_cts;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }


    public void setKb_id(String kb_id) {
        this.kb_id = kb_id;
    }

    public String getKb_id() {
        return kb_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_name() {
        return user_name;
    }
}
