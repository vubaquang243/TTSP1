package com.seatech.ttsp.saoke;

import java.util.List;

public class RowHeader {
    private String date;
    private List resultRow;


    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setResultRow(List resultRow) {
        this.resultRow = resultRow;
    }

    public List getResultRow() {
        return resultRow;
    }
}
