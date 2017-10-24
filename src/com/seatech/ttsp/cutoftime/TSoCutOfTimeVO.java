package com.seatech.ttsp.cutoftime;

public class TSoCutOfTimeVO {
    public TSoCutOfTimeVO() {
        super();
    }
    private Long id;
    private String ma_nh_kb;
    private String ten_nh_kb;
    private String ma_nh;
    private String ten_nh;
    private String ngay_gd;
    private String cut_of_time;
    private String insert_date;
    private String ghi_chu;
    private Long so_yc_cot;
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMa_nh_kb(String ma_nh_kb) {
        this.ma_nh_kb = ma_nh_kb;
    }

    public String getMa_nh_kb() {
        return ma_nh_kb;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setNgay_gd(String ngay_gd) {
        this.ngay_gd = ngay_gd;
    }

    public String getNgay_gd() {
        return ngay_gd;
    }

    public void setCut_of_time(String cut_of_time) {
        this.cut_of_time = cut_of_time;
    }

    public String getCut_of_time() {
        return cut_of_time;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setSo_yc_cot(Long so_yc_cot) {
        this.so_yc_cot = so_yc_cot;
    }

    public Long getSo_yc_cot() {
        return so_yc_cot;
    }


    public void setTen_nh_kb(String ten_nh_kb) {
        this.ten_nh_kb = ten_nh_kb;
    }

    public String getTen_nh_kb() {
        return ten_nh_kb;
    }

    public void setTen_nh(String ten_nh) {
        this.ten_nh = ten_nh;
    }

    public String getTen_nh() {
        return ten_nh;
    }

}
