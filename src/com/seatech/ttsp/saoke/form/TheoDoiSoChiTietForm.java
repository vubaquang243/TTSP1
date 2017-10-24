package com.seatech.ttsp.saoke.form;

import com.seatech.framework.strustx.AppForm;

public class TheoDoiSoChiTietForm extends AppForm{
    private String from_date;
    private String to_date;


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
}
