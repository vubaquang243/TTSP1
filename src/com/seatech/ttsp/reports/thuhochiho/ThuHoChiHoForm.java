package com.seatech.ttsp.reports.thuhochiho;

import org.apache.struts.action.ActionForm;

public class ThuHoChiHoForm  extends ActionForm{
    private String ma_dv;
    private String from_date;
    private String to_date;
    private String loai_tien;

    public void setMa_dv(String ma_dv) {
        this.ma_dv = ma_dv;
    }

    public String getMa_dv() {
        return ma_dv;
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

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }
}
