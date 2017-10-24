package com.seatech.ttsp.dchieu.form;

import com.seatech.framework.strustx.AppForm;

public class TCuuDChieuPOSForm extends AppForm{
  /**
   * @creater: ThuongDT
   * @creat date: 10/11/2016
   * @see: Lop doi tuong Doi chieu POS
   * */
   private String id;
  private String nhkb_tinh;  
  private String nhkb_huyen;
  private String ngayDC;
  private String trangThaiDC;
  private String loaiTien;
  private String ngan_hang;
  private String pageNumber;
  private String idxKB;
  private String idxNH;
  private String idxTT;
  
  private String bkid;
  private String kqid;
  
  private String lydo;
  private String ma_nh;
  private String ma_kb;
  private String err_code;
  private String err_desc;

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

    public void setNgayDC(String ngayDC) {
        this.ngayDC = ngayDC;
    }

    public String getNgayDC() {
        return ngayDC;
    }

    public void setTrangThaiDC(String trangThaiDC) {
        this.trangThaiDC = trangThaiDC;
    }

    public String getTrangThaiDC() {
        return trangThaiDC;
    }

    public void setLoaiTien(String loaiTien) {
        this.loaiTien = loaiTien;
    }

    public String getLoaiTien() {
        return loaiTien;
    }

    public void setNgan_hang(String ngan_hang) {
        this.ngan_hang = ngan_hang;
    }

    public String getNgan_hang() {
        return ngan_hang;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setIdxKB(String idxKB) {
        this.idxKB = idxKB;
    }

    public String getIdxKB() {
        return idxKB;
    }

    public void setIdxNH(String idxNH) {
        this.idxNH = idxNH;
    }

    public String getIdxNH() {
        return idxNH;
    }

    public void setIdxTT(String idxTT) {
        this.idxTT = idxTT;
    }

    public String getIdxTT() {
        return idxTT;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public String getLydo() {
        return lydo;
    }

    public void setBkid(String bkid) {
        this.bkid = bkid;
    }

    public String getBkid() {
        return bkid;
    }

    public void setKqid(String kqid) {
        this.kqid = kqid;
    }

    public String getKqid() {
        return kqid;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_desc(String err_desc) {
        this.err_desc = err_desc;
    }

    public String getErr_desc() {
        return err_desc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
