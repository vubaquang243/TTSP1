package com.seatech.ttsp.logduyetlo.vo;

import java.util.Date;

public class LogDuyetLoVO {
    private Long id;
    private Date insert_date;
    private String error_code;
    private String error_desc;
    private Long ltt_id;
    private String so_yctt;
    private String ma_nsd;
    private String ma_kb;
    private String ghi_chu;
    private Date ngay_duyet;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }

    public Date getInsert_date() {
        return insert_date;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setLtt_id(Long ltt_id) {
        this.ltt_id = ltt_id;
    }

    public Long getLtt_id() {
        return ltt_id;
    }

    public void setSo_yctt(String so_yctt) {
        this.so_yctt = so_yctt;
    }

    public String getSo_yctt() {
        return so_yctt;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setNgay_duyet(Date ngay_duyet) {
        this.ngay_duyet = ngay_duyet;
    }

    public Date getNgay_duyet() {
        return ngay_duyet;
    }
}
