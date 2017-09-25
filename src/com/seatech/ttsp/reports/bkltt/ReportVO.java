package com.seatech.ttsp.reports.bkltt;

public class ReportVO {
    public ReportVO() {
        super();
    }
    private String tu_ngay;
    private String den_ngay;

    public void setDen_ngay(String den_ngay) {
        this.den_ngay = den_ngay;
    }

    public String getDen_ngay() {
        return den_ngay;
    }

    public void setTu_ngay(String tu_ngay) {
        this.tu_ngay = tu_ngay;
    }

    public String getTu_ngay() {
        return tu_ngay;
    }
}
