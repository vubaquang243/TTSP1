package com.seatech.ttsp.proxy.giaodien.ttnh;

public class TTinNHangVO {
    private Long id;
    private String bank_code;
    private String original_code;
    private String original_name;
    private String app_code;
    private String app_name;
    private String status;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setBank_code(String bank_code) {
        this.bank_code = bank_code;
    }

    public String getBank_code() {
        return bank_code;
    }

    public void setOriginal_code(String original_code) {
        this.original_code = original_code;
    }

    public String getOriginal_code() {
        return original_code;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setApp_code(String app_code) {
        this.app_code = app_code;
    }

    public String getApp_code() {
        return app_code;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
