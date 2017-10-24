package com.seatech.ttsp.ngaynghi;

public class NgayNghiVO {
    private Long id;
    private Long ngay;
    private String mo_ta;
    private String created_date;
    private Long created_by;
    private Long day_of_week;
    private String status;
    //
    private String ngay_rpt;
    private String thu_rpt;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNgay(Long ngay) {
        this.ngay = ngay;
        this.ngay_rpt = getNgayRPT(ngay);
    }

    public Long getNgay() {
        return ngay;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_by(Long created_by) {
        this.created_by = created_by;
    }

    public Long getCreated_by() {
        return created_by;
    }

    public void setDay_of_week(Long day_of_week) {
        this.day_of_week = day_of_week;
        this.thu_rpt = getThuRPT(day_of_week);
    }

    public Long getDay_of_week() {
        return day_of_week;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setNgay_rpt(String ngay_rpt) {
        this.ngay_rpt = ngay_rpt;
    }

    public String getNgay_rpt() {
        return ngay_rpt;
    }

    public void setThu_rpt(String thu_rpt) {
        this.thu_rpt = thu_rpt;
    }

    public String getThu_rpt() {
        return thu_rpt;
    }

    private String getNgayRPT(Long ngay) {
        String strNgay = String.valueOf(ngay.longValue());
        return strNgay.substring(6, 8) + "/" + strNgay.substring(4, 6) + "/" +
            strNgay.substring(0, 4);
    }

    private String getThuRPT(Long thu) {
        if (thu.longValue() == 1) {
            return "Chu nhat";
        } else {
            return "Thu " + thu.longValue();
        }
    }
}
