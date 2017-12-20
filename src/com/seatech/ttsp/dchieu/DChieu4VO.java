package com.seatech.ttsp.dchieu;


import com.seatech.framework.strustx.AppForm;

public class DChieu4VO extends AppForm {
  private  String id;           
  private  String lan_dc;       
  private  String ngay_dc;        
  private  String send_bank;    
  private  String receive_bank; 
  private  String creator;      
  private  String created_date;   
  private  String manager;      
  private  String verified_date;  
  private  Long sodu_daungay;   
  private  Long tong_thu;       
  private  Long tong_chi;       
  private  Long so_du_cuoi_ngay;
  private  String msg_id;       
  private  String trang_thai;
  //out
  private String ngay_ct;   
  private String mt_id;      
  private  Long so_tien;   
  private  Long ma_kb;       
  private  Long mt900kbnn;       
  private  Long mt910kbnn;
  private  Long mt900nhtm;       
  private  Long mt910nhtm;
  private  Long chikbnn ;       
  private  Long thukbnn;
  private String bk_id; 
  private String kq_id; 
  private String ket_qua; 
  private String ngay_thien_dc; 
  private String trang_thai_bk;
  private String trang_thai_kq;
  private String loai_tien;
  
  private Long mt900_thua;
  private Long mt910_thua;
  private Long mt900_thieu;
  private Long mt910_thieu;
private String ma_nh;
  private String tthai_ttin;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLan_dc(String lan_dc) {
        this.lan_dc = lan_dc;
    }

    public String getLan_dc() {
        return lan_dc;
    }

    public void setNgay_dc(String ngay_dc) {
        this.ngay_dc = ngay_dc;
    }

    public String getNgay_dc() {
        return ngay_dc;
    }

    public void setSend_bank(String send_bank) {
        this.send_bank = send_bank;
    }

    public String getSend_bank() {
        return send_bank;
    }

    public void setReceive_bank(String receive_bank) {
        this.receive_bank = receive_bank;
    }

    public String getReceive_bank() {
        return receive_bank;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManager() {
        return manager;
    }

    public void setVerified_date(String verified_date) {
        this.verified_date = verified_date;
    }

    public String getVerified_date() {
        return verified_date;
    }

    public void setSodu_daungay(Long sodu_daungay) {
        this.sodu_daungay = sodu_daungay;
    }

    public Long getSodu_daungay() {
        return sodu_daungay;
    }

    public void setTong_thu(Long tong_thu) {
        this.tong_thu = tong_thu;
    }

    public Long getTong_thu() {
        return tong_thu;
    }

    public void setTong_chi(Long tong_chi) {
        this.tong_chi = tong_chi;
    }

    public Long getTong_chi() {
        return tong_chi;
    }

    public void setSo_du_cuoi_ngay(Long so_du_cuoi_ngay) {
        this.so_du_cuoi_ngay = so_du_cuoi_ngay;
    }

    public Long getSo_du_cuoi_ngay() {
        return so_du_cuoi_ngay;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setNgay_ct(String ngay_ct) {
        this.ngay_ct = ngay_ct;
    }

    public String getNgay_ct() {
        return ngay_ct;
    }

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }

    public String getMt_id() {
        return mt_id;
    }

    public void setSo_tien(Long so_tien) {
        this.so_tien = so_tien;
    }

    public Long getSo_tien() {
        return so_tien;
    }

    public void setMa_kb(Long ma_kb) {
        this.ma_kb = ma_kb;
    }

    public Long getMa_kb() {
        return ma_kb;
    }

    public void setMt900kbnn(Long mt900kbnn) {
        this.mt900kbnn = mt900kbnn;
    }

    public Long getMt900kbnn() {
        return mt900kbnn;
    }

    public void setMt910kbnn(Long mt910kbnn) {
        this.mt910kbnn = mt910kbnn;
    }

    public Long getMt910kbnn() {
        return mt910kbnn;
    }

    public void setMt900nhtm(Long mt900nhtm) {
        this.mt900nhtm = mt900nhtm;
    }

    public Long getMt900nhtm() {
        return mt900nhtm;
    }

    public void setMt910nhtm(Long mt910nhtm) {
        this.mt910nhtm = mt910nhtm;
    }

    public Long getMt910nhtm() {
        return mt910nhtm;
    }

    public void setChikbnn(Long chikbnn) {
        this.chikbnn = chikbnn;
    }

    public Long getChikbnn() {
        return chikbnn;
    }

    public void setThukbnn(Long thukbnn) {
        this.thukbnn = thukbnn;
    }

    public Long getThukbnn() {
        return thukbnn;
    }

    public void setBk_id(String bk_id) {
        this.bk_id = bk_id;
    }

    public String getBk_id() {
        return bk_id;
    }

    public void setKq_id(String kq_id) {
        this.kq_id = kq_id;
    }

    public String getKq_id() {
        return kq_id;
    }

    public void setKet_qua(String ket_qua) {
        this.ket_qua = ket_qua;
    }

    public String getKet_qua() {
        return ket_qua;
    }

    public void setNgay_thien_dc(String ngay_thien_dc) {
        this.ngay_thien_dc = ngay_thien_dc;
    }

    public String getNgay_thien_dc() {
        return ngay_thien_dc;
    }

    public void setTrang_thai_bk(String trang_thai_bk) {
        this.trang_thai_bk = trang_thai_bk;
    }

    public String getTrang_thai_bk() {
        return trang_thai_bk;
    }

    public void setTrang_thai_kq(String trang_thai_kq) {
        this.trang_thai_kq = trang_thai_kq;
    }

    public String getTrang_thai_kq() {
        return trang_thai_kq;
    }

    public void setMt900_thua(Long mt900_thua) {
        this.mt900_thua = mt900_thua;
    }

    public Long getMt900_thua() {
        return mt900_thua;
    }

    public void setMt910_thua(Long mt910_thua) {
        this.mt910_thua = mt910_thua;
    }

    public Long getMt910_thua() {
        return mt910_thua;
    }

    public void setMt900_thieu(Long mt900_thieu) {
        this.mt900_thieu = mt900_thieu;
    }

    public Long getMt900_thieu() {
        return mt900_thieu;
    }

    public void setMt910_thieu(Long mt910_thieu) {
        this.mt910_thieu = mt910_thieu;
    }

    public Long getMt910_thieu() {
        return mt910_thieu;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setTthai_ttin(String tthai_ttin) {
        this.tthai_ttin = tthai_ttin;
    }

    public String getTthai_ttin() {
        return tthai_ttin;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }
}
