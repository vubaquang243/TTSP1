package com.seatech.ttsp.error;

public class AppErrorVO {
    private String error_id;
    private String error_contents;

    public void setError_id(String error_id) {
        this.error_id = error_id;
    }

    public String getError_id() {
        return error_id;
    }

    public void setError_contents(String error_contents) {
        this.error_contents = error_contents;
    }

    public String getError_contents() {
        return error_contents;
    }
}
