package com.seatech.ttsp.bketinhphi;

import org.apache.struts.action.ActionForm;

public class BKeTinhPhiForm extends ActionForm {
    private String nhkb_tinh;
    private String pageNumber;
    private String sendBank;
    private String receiveBank;
    private String tuNgay;
    private String denNgay;
    private String soTk;
    
    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setSendBank(String sendBank) {
        this.sendBank = sendBank;
    }

    public String getSendBank() {
        return sendBank;
    }

    public void setReceiveBank(String receiveBank) {
        this.receiveBank = receiveBank;
    }

    public String getReceiveBank() {
        return receiveBank;
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

    public void setSoTk(String soTk) {
        this.soTk = soTk;
    }

    public String getSoTk() {
        return soTk;
    }

    public void setNhkb_tinh(String nhkb_tinh) {
        this.nhkb_tinh = nhkb_tinh;
    }

    public String getNhkb_tinh() {
        return nhkb_tinh;
    }
}
