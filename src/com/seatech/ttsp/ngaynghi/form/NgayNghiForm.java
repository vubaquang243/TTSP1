package com.seatech.ttsp.ngaynghi.form;

import org.apache.struts.action.ActionForm;

public class NgayNghiForm extends ActionForm{
    private Long id;
    private Long ngay;
    private String mo_ta;
    private String created_date;
    private Long created_by;
    private Long day_of_week;    
    private String status;
    private String ngaytk;
    private String thangtk;
    private String namtk; 
    

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNgay(Long ngay) {
        this.ngay = ngay;
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

    public void setNgaytk(String ngaytk) {
        this.ngaytk = ngaytk;
    }

    public String getNgaytk() {
        return ngaytk;
    }

    public void setThangtk(String thangtk) {
        this.thangtk = thangtk;
    }

    public String getThangtk() {
        return thangtk;
    }

    public void setNamtk(String namtk) {
        this.namtk = namtk;
    }

    public String getNamtk() {
        return namtk;
    }


}
