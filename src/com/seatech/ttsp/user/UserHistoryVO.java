package com.seatech.ttsp.user;

public class UserHistoryVO {
    private Long id;
    private Long nsd_id;
    private String ngay_tdoi;
    private Long nguoi_tdoi;
    private String noi_dung_thaydoi;
    private String ten_ng_tdoi;
    private String ma_nsd;
    private String ten_nsd;
    private String chuc_danh;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNsd_id(Long nsd_id) {
        this.nsd_id = nsd_id;
    }

    public Long getNsd_id() {
        return nsd_id;
    }

    public void setNgay_tdoi(String ngay_tdoi) {
        this.ngay_tdoi = ngay_tdoi;
    }

    public String getNgay_tdoi() {
        return ngay_tdoi;
    }

    public void setNguoi_tdoi(Long nguoi_tdoi) {
        this.nguoi_tdoi = nguoi_tdoi;
    }

    public Long getNguoi_tdoi() {
        return nguoi_tdoi;
    }


    public void setNoi_dung_thaydoi(String noi_dung_thaydoi) {
        this.noi_dung_thaydoi = noi_dung_thaydoi;
    }

    public String getNoi_dung_thaydoi() {
        return noi_dung_thaydoi;
    }

    public void setTen_ng_tdoi(String ten_ng_tdoi) {
        this.ten_ng_tdoi = ten_ng_tdoi;
    }

    public String getTen_ng_tdoi() {
        return ten_ng_tdoi;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }

    public void setTen_nsd(String ten_nsd) {
        this.ten_nsd = ten_nsd;
    }

    public String getTen_nsd() {
        return ten_nsd;
    }

    public void setChuc_danh(String chuc_danh) {
        this.chuc_danh = chuc_danh;
    }

    public String getChuc_danh() {
        return chuc_danh;
    }
}
