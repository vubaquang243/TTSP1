package com.seatech.ttsp.theodoiht.form;


import org.apache.struts.action.ActionForm;

public class TraCuuTTSPForm extends ActionForm {
    public TraCuuTTSPForm() {
        super();
    }
    private String makb_chuyen;
    private String manh_chuyen;
    private String sotien;
    private String manh_nhan;
    private String ngaythanhtoan;
    private String taikhoanno;
    private String so_yctt;

    public void setMakb_chuyen(String makb_chuyen) {
        this.makb_chuyen = makb_chuyen;
    }

    public String getMakb_chuyen() {
        return makb_chuyen;
    }

    public void setManh_chuyen(String manh_chuyen) {
        this.manh_chuyen = manh_chuyen;
    }

    public String getManh_chuyen() {
        return manh_chuyen;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

    public String getSotien() {
        return sotien;
    }

    public void setManh_nhan(String manh_nhan) {
        this.manh_nhan = manh_nhan;
    }

    public String getManh_nhan() {
        return manh_nhan;
    }

    public void setNgaythanhtoan(String ngaythanhtoan) {
        this.ngaythanhtoan = ngaythanhtoan;
    }

    public String getNgaythanhtoan() {
        return ngaythanhtoan;
    }

    public void setTaikhoanno(String taikhoanno) {
        this.taikhoanno = taikhoanno;
    }

    public String getTaikhoanno() {
        return taikhoanno;
    }

    public void setSo_yctt(String so_yctt) {
        this.so_yctt = so_yctt;
    }

    public String getSo_yctt() {
        return so_yctt;
    }
}
