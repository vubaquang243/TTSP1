package com.seatech.ttsp.doichieu.form;

import com.seatech.framework.strustx.AppForm;

public class DCNoiBoForm extends AppForm {
    public DCNoiBoForm() {
        super();
    }
    private String ngay_thuc_hien;
    private String ngan_hang;
    private String kho_bac;
    private String trang_thai;
    private String pageNumber;
    
    public void setNgay_thuc_hien(String ngay_thuc_hien) {
        this.ngay_thuc_hien = ngay_thuc_hien;
    }

    public String getNgay_thuc_hien() {
        return ngay_thuc_hien;
    }

    public void setNgan_hang(String ngan_hang) {
        this.ngan_hang = ngan_hang;
    }

    public String getNgan_hang() {
        return ngan_hang;
    }

    public void setKho_bac(String kho_bac) {
        this.kho_bac = kho_bac;
    }

    public String getKho_bac() {
        return kho_bac;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }
}
