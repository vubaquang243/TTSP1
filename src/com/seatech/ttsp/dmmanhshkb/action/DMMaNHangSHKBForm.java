package com.seatech.ttsp.dmmanhshkb.action;

import org.apache.struts.action.ActionForm;

public class DMMaNHangSHKBForm  extends ActionForm {
    boolean Empty;
    private String pageNumber;
    private String ma_nh;
    private String shkb;
    private String ten;    
    private String type;    
    private String action_status;

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setShkb(String shkb) {
        this.shkb = shkb;
    }

    public String getShkb() {
        return shkb;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }


    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getAction_status() {
        return action_status;
    }
}
