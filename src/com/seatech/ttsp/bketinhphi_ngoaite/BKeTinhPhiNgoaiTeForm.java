package com.seatech.ttsp.bketinhphi_ngoaite;

import org.apache.struts.action.ActionForm;

public class BKeTinhPhiNgoaiTeForm extends ActionForm {
    private String pageNumber;
    private String nhkb_tinh;
    private String receiveBank;
    private String sendBank;
    private String soTk;
    private String tuNgay;
    private String denNgay;
    private String loaiTien;

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setNhkb_tinh(String nhkb_tinh) {
        this.nhkb_tinh = nhkb_tinh;
    }

    public String getNhkb_tinh() {
        return nhkb_tinh;
    }

    public void setReceiveBank(String receiveBank) {
        this.receiveBank = receiveBank;
    }

    public String getReceiveBank() {
        return receiveBank;
    }

    public void setSendBank(String sendBank) {
        this.sendBank = sendBank;
    }

    public String getSendBank() {
        return sendBank;
    }

    public void setSoTk(String soTk) {
        this.soTk = soTk;
    }

    public String getSoTk() {
        return soTk;
    }

    public void setTuNgay(String tuNgay) {
        this.tuNgay = tuNgay;
    }

    public String getTuNgay() {
        return tuNgay;
    }

    public void setDenNgay(String denNgay) {
        this.denNgay = denNgay;
    }

    public String getDenNgay() {
        return denNgay;
    }

    public void setLoaiTien(String loaiTien) {
        this.loaiTien = loaiTien;
    }

    public String getLoaiTien() {
        return loaiTien;
    }
}
