package com.seatech.ttsp.tcuudmuc.form;

import com.seatech.framework.strustx.AppForm;

public class TCuuDMucForm extends AppForm {
private String table_name;
private String pageNumber;
private String ma;
private String tinhtrang;
private String ten;
private String action_status;

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getAction_status() {
        return action_status;
    }
}
