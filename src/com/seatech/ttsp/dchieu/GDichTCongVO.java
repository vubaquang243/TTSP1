package com.seatech.ttsp.dchieu;

import java.math.BigDecimal;

public class GDichTCongVO {
  private Long id;
  private Long id_tcong;
  private String ma_kb;
  private String ma_nh;
  private String ngay_gd;
  private String so_tk;
  private BigDecimal so_thu;
  private BigDecimal so_chi;
  private String insert_date;
  private String nsd_id;
  private String ghi_chu;
  private String so_thu_chu;
  private String so_chi_chu;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setNgay_gd(String ngay_gd) {
        this.ngay_gd = ngay_gd;
    }

    public String getNgay_gd() {
        return ngay_gd;
    }

    public void setSo_tk(String so_tk) {
        this.so_tk = so_tk;
    }

    public String getSo_tk() {
        return so_tk;
    }
    

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setNsd_id(String nsd_id) {
        this.nsd_id = nsd_id;
    }

    public String getNsd_id() {
        return nsd_id;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setId_tcong(Long id_tcong) {
        this.id_tcong = id_tcong;
    }

    public Long getId_tcong() {
        return id_tcong;
    }

    public void setSo_thu_chu(String so_thu_chu) {
        this.so_thu_chu = so_thu_chu;
    }

    public String getSo_thu_chu() {
        return so_thu_chu;
    }

    public void setSo_chi_chu(String so_chi_chu) {
        this.so_chi_chu = so_chi_chu;
    }

    public String getSo_chi_chu() {
        return so_chi_chu;
    }

    public void setSo_thu(BigDecimal so_thu) {
        this.so_thu = so_thu;
    }

    public BigDecimal getSo_thu() {
        return so_thu;
    }

    public void setSo_chi(BigDecimal so_chi) {
        this.so_chi = so_chi;
    }

    public BigDecimal getSo_chi() {
        return so_chi;
    }
}
