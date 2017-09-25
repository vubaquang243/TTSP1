package com.seatech.ttsp.ktvtm;

public class KTVTabmisVO {
  private Long id;
  private String ma;
  private String ten;
  private Long nguoi_tao;
  private String ngay_tao;
  private Long kb_id;
  private String ma_nsd;
  private String ten_nsd;
  private Long ma_kb;
  private String ten_kb;
  private String trang_thai_chon;
  
  
 
  

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setNguoi_tao(Long nguoi_tao) {
        this.nguoi_tao = nguoi_tao;
    }

    public Long getNguoi_tao() {
        return nguoi_tao;
    }

    public void setNgay_tao(String ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public String getNgay_tao() {
        return ngay_tao;
    }

    public void setTen_nsd(String ten_nsd) {
        this.ten_nsd = ten_nsd;
    }

    public String getTen_nsd() {
        return ten_nsd;
    }

    public void setMa_kb(Long ma_kb) {
        this.ma_kb = ma_kb;
    }

    public Long getMa_kb() {
        return ma_kb;
    }

    public void setTen_kb(String ten_kb) {
        this.ten_kb = ten_kb;
    }

    public String getTen_kb() {
        return ten_kb;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }


    public void setTrang_thai_chon(String trang_thai_chon) {
        this.trang_thai_chon = trang_thai_chon;
    }

    public String getTrang_thai_chon() {
        return trang_thai_chon;
    }

    public void setKb_id(Long kb_id) {
        this.kb_id = kb_id;
    }

    public Long getKb_id() {
        return kb_id;
    }
}
