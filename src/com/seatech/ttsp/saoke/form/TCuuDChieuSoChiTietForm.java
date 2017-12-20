package com.seatech.ttsp.saoke.form;

import com.seatech.framework.strustx.AppForm;

public class TCuuDChieuSoChiTietForm extends AppForm {
    private String pageNumber;
    private String kb_id;
    //20171124 thuongdt bo sung them tra cuu theo don vi tinh
    private String kb_tinh;
    private String kb_huyen;
    private String ngan_hang;
    private String trangthai;
    private String ketqua;
    
    private String from_date;
    private String to_date;
    private String so_tk;

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setNgan_hang(String ngan_hang) {
        this.ngan_hang = ngan_hang;
    }

    public String getNgan_hang() {
        return ngan_hang;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setKb_huyen(String kb_huyen) {
        this.kb_huyen = kb_huyen;
    }

    public String getKb_huyen() {
        return kb_huyen;
    }

    public void setKb_id(String kb_id) {
        this.kb_id = kb_id;
    }

    public String getKb_id() {
        return kb_id;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }

    public String getKetqua() {
        return ketqua;
    }

    public void setSo_tk(String so_tk) {
        this.so_tk = so_tk;
    }

    public String getSo_tk() {
        return so_tk;
    }

    public void setKb_tinh(String kb_tinh) {
        this.kb_tinh = kb_tinh;
    }

    public String getKb_tinh() {
        return kb_tinh;
    }
}
