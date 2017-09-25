package com.seatech.ttsp.dchieu.form;

import com.seatech.framework.strustx.AppForm;
/*
 * @Modify: HungBM 
 * @Modify date: 18/11/2016 
 * @See: bo sung property trang_thai_tk
 * 
 * @Modify: HungBM 
 * @Modify date: 20/03/2017 
 * @See: bo sung property inPVDC va pham_vi_doi_chieu
 * */
public class TCuuTTinDChieuForm extends AppForm {
  private String id;
  private String bk_id;
  private String kq_id;
  private String ttsp_id;
  private String pht_id;
  private String ket_qua;
  private String lan_dc;
  private String ngay_dc;
  private String send_bank;
  private String receive_bank;
  private String created_date;
  private String creator;
  private String manager;
  private String verified_date;
  private long sodu_kbnn;
  private long chenh_lech;
  private String msg_id;
  private String insert_date;
  private String trang_thai;
  private String ltt_kb_thua;
  private String ltt_kb_thieu;
  private String dts_kb_thieu; 
  private String dts_kb_thua;
  private String bkq_id;
  private String ket_qua_pht;
  private int khop_lech;
  private int khop_dung;
  private String ly_do_xn;
  private long tong_ps_pht;
  private long tong_mon_pht;
  private String ten;
  private String app_type;
  private String tthai_dxn_thop;
  
  private String tu_ngay;
  private String den_ngay;
  
  
  private String ct;
  
  private String ngan_hang;
  private String nhkb_tinh;
  private String nhkb_huyen;
  private String pageNumber;
  
  private String loai_dc;


  private String somon_thu; 
  private long sotien_thu; 
  private String somon_chi; 
  private long sotien_chi; 

  private String somon_dtu; 
  private long sotien_dtu; 
  private String somon_tcong; 
  private long sotien_tcong; 

  private long sodu_daungay; 
  private long so_du; 
  private long ps_thu; 
  private int ps_chi; 
  private String so_ketchuyen; 
  private String type;
  
  private String ma_nh;

  private String ma_dv;

  private String ten_send_bank;  
  // doi chieu chi tiet
  

  private String mt_id;
  private String send_date;
  private long f20;
  private long f21;
  private long f32as3;
  private String ngay_ts;
  private String ghi_chu;
  private String mt_type;

  private String sai_yeu_to;
  private String ngay_ct;
  private String ltt;
  private String dts_hoi;
  private String dts_tra_loi;
  

  private long so_tien_thu_tcong;
  private long so_tien_thu_dtu;

  private String trang_thai_bk;
  private String trang_thai_kq;
  private String btton;
  private String trang_thai_kqdc;
  private String trang_thai_bkdc;
  private String ngay_thien_dc;
  private String currentPage;
  private String inKB;
  private String inNH;
  private String inxtthai;
  private String ngay_thuc_hien_dc;
  private String id_nhkb_tinh;
  private String id_nhkb_huyen;
  private String ket_qua_ttsp;
  private String ma_nt;
  
  //HungBM 3/11/2016
  private String inTinh;//index cua combobox KB tinh, phuc vu cho viec back trang
  private String inLanDC;//index cua combobox lan doi chieu, phuc vu cho viec back trang
  private String inTTTK;//index cua combobox trang thai tai khoan, phuc vu cho viec back trang
  private String trang_thai_tk;//trang thai tai khoan
  //HungBM 20/3/2016
  private String pham_vi_doi_chieu;//Pham vi doi chieu
  private String inPVDC;//index cua combobox Pham vi doi chieu., phuc vu cho viec back trang

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBk_id(String bk_id) {
        this.bk_id = bk_id;
    }

    public String getBk_id() {
        return bk_id;
    }

    public void setKet_qua(String ket_qua) {
        this.ket_qua = ket_qua;
    }

    public String getKet_qua() {
        return ket_qua;
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

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreator() {
        return creator;
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

    public void setSodu_kbnn(long sodu_kbnn) {
        this.sodu_kbnn = sodu_kbnn;
    }

    public long getSodu_kbnn() {
        return sodu_kbnn;
    }

    public void setChenh_lech(long chenh_lech) {
        this.chenh_lech = chenh_lech;
    }

    public long getChenh_lech() {
        return chenh_lech;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setLtt_kb_thua(String ltt_kb_thua) {
        this.ltt_kb_thua = ltt_kb_thua;
    }

    public String getLtt_kb_thua() {
        return ltt_kb_thua;
    }

    public void setLtt_kb_thieu(String ltt_kb_thieu) {
        this.ltt_kb_thieu = ltt_kb_thieu;
    }

    public String getLtt_kb_thieu() {
        return ltt_kb_thieu;
    }

    public void setDts_kb_thieu(String dts_kb_thieu) {
        this.dts_kb_thieu = dts_kb_thieu;
    }

    public String getDts_kb_thieu() {
        return dts_kb_thieu;
    }

    public void setDts_kb_thua(String dts_kb_thua) {
        this.dts_kb_thua = dts_kb_thua;
    }

    public String getDts_kb_thua() {
        return dts_kb_thua;
    }

    public void setBkq_id(String bkq_id) {
        this.bkq_id = bkq_id;
    }

    public String getBkq_id() {
        return bkq_id;
    }

    public void setKet_qua_pht(String ket_qua_pht) {
        this.ket_qua_pht = ket_qua_pht;
    }

    public String getKet_qua_pht() {
        return ket_qua_pht;
    }

    public void setKhop_lech(int khop_lech) {
        this.khop_lech = khop_lech;
    }

    public int getKhop_lech() {
        return khop_lech;
    }

    public void setKhop_dung(int khop_dung) {
        this.khop_dung = khop_dung;
    }

    public int getKhop_dung() {
        return khop_dung;
    }

    public void setLy_do_xn(String ly_do_xn) {
        this.ly_do_xn = ly_do_xn;
    }

    public String getLy_do_xn() {
        return ly_do_xn;
    }

    public void setTong_ps_pht(long tong_ps_pht) {
        this.tong_ps_pht = tong_ps_pht;
    }

    public long getTong_ps_pht() {
        return tong_ps_pht;
    }

    public void setTong_mon_pht(long tong_mon_pht) {
        this.tong_mon_pht = tong_mon_pht;
    }

    public long getTong_mon_pht() {
        return tong_mon_pht;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setTthai_dxn_thop(String tthai_dxn_thop) {
        this.tthai_dxn_thop = tthai_dxn_thop;
    }

    public String getTthai_dxn_thop() {
        return tthai_dxn_thop;
    }

    public void setNgan_hang(String ngan_hang) {
        this.ngan_hang = ngan_hang;
    }

    public String getNgan_hang() {
        return ngan_hang;
    }

    public void setNhkb_tinh(String nhkb_tinh) {
        this.nhkb_tinh = nhkb_tinh;
    }

    public String getNhkb_tinh() {
        return nhkb_tinh;
    }

    public void setNhkb_huyen(String nhkb_huyen) {
        this.nhkb_huyen = nhkb_huyen;
    }

    public String getNhkb_huyen() {
        return nhkb_huyen;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getCt() {
        return ct;
    }

    public void setLoai_dc(String loai_dc) {
        this.loai_dc = loai_dc;
    }

    public String getLoai_dc() {
        return loai_dc;
    }

    public void setSomon_thu(String somon_thu) {
        this.somon_thu = somon_thu;
    }

    public String getSomon_thu() {
        return somon_thu;
    }

    public void setSotien_thu(long sotien_thu) {
        this.sotien_thu = sotien_thu;
    }

    public long getSotien_thu() {
        return sotien_thu;
    }

    public void setSomon_chi(String somon_chi) {
        this.somon_chi = somon_chi;
    }

    public String getSomon_chi() {
        return somon_chi;
    }

    public void setSotien_chi(long sotien_chi) {
        this.sotien_chi = sotien_chi;
    }

    public long getSotien_chi() {
        return sotien_chi;
    }

    public void setSomon_dtu(String somon_dtu) {
        this.somon_dtu = somon_dtu;
    }

    public String getSomon_dtu() {
        return somon_dtu;
    }

    public void setSotien_dtu(long sotien_dtu) {
        this.sotien_dtu = sotien_dtu;
    }

    public long getSotien_dtu() {
        return sotien_dtu;
    }

    public void setSomon_tcong(String somon_tcong) {
        this.somon_tcong = somon_tcong;
    }

    public String getSomon_tcong() {
        return somon_tcong;
    }

    public void setSotien_tcong(long sotien_tcong) {
        this.sotien_tcong = sotien_tcong;
    }

    public long getSotien_tcong() {
        return sotien_tcong;
    }

    public void setSodu_daungay(long sodu_daungay) {
        this.sodu_daungay = sodu_daungay;
    }

    public long getSodu_daungay() {
        return sodu_daungay;
    }

    public void setSo_du(long so_du) {
        this.so_du = so_du;
    }

    public long getSo_du() {
        return so_du;
    }

    public void setPs_thu(long ps_thu) {
        this.ps_thu = ps_thu;
    }

    public long getPs_thu() {
        return ps_thu;
    }

    public void setPs_chi(int ps_chi) {
        this.ps_chi = ps_chi;
    }

    public int getPs_chi() {
        return ps_chi;
    }

    public void setSo_ketchuyen(String so_ketchuyen) {
        this.so_ketchuyen = so_ketchuyen;
    }

    public String getSo_ketchuyen() {
        return so_ketchuyen;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setMa_dv(String ma_dv) {
        this.ma_dv = ma_dv;
    }

    public String getMa_dv() {
        return ma_dv;
    }

    public void setTen_send_bank(String ten_send_bank) {
        this.ten_send_bank = ten_send_bank;
    }

    public String getTen_send_bank() {
        return ten_send_bank;
    }

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }

    public String getMt_id() {
        return mt_id;
    }

    public void setSend_date(String send_date) {
        this.send_date = send_date;
    }

    public String getSend_date() {
        return send_date;
    }

    public void setF20(long f20) {
        this.f20 = f20;
    }

    public long getF20() {
        return f20;
    }

    public void setF21(long f21) {
        this.f21 = f21;
    }

    public long getF21() {
        return f21;
    }

    public void setF32as3(long f32as3) {
        this.f32as3 = f32as3;
    }

    public long getF32as3() {
        return f32as3;
    }

    public void setNgay_ts(String ngay_ts) {
        this.ngay_ts = ngay_ts;
    }

    public String getNgay_ts() {
        return ngay_ts;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setMt_type(String mt_type) {
        this.mt_type = mt_type;
    }

    public String getMt_type() {
        return mt_type;
    }

    public void setSai_yeu_to(String sai_yeu_to) {
        this.sai_yeu_to = sai_yeu_to;
    }

    public String getSai_yeu_to() {
        return sai_yeu_to;
    }

    public void setNgay_ct(String ngay_ct) {
        this.ngay_ct = ngay_ct;
    }

    public String getNgay_ct() {
        return ngay_ct;
    }

    public void setLtt(String ltt) {
        this.ltt = ltt;
    }

    public String getLtt() {
        return ltt;
    }

    public void setDts_hoi(String dts_hoi) {
        this.dts_hoi = dts_hoi;
    }

    public String getDts_hoi() {
        return dts_hoi;
    }

    public void setDts_tra_loi(String dts_tra_loi) {
        this.dts_tra_loi = dts_tra_loi;
    }

    public String getDts_tra_loi() {
        return dts_tra_loi;
    }

    public void setSo_tien_thu_tcong(long so_tien_thu_tcong) {
        this.so_tien_thu_tcong = so_tien_thu_tcong;
    }

    public long getSo_tien_thu_tcong() {
        return so_tien_thu_tcong;
    }

    public void setSo_tien_thu_dtu(long so_tien_thu_dtu) {
        this.so_tien_thu_dtu = so_tien_thu_dtu;
    }

    public long getSo_tien_thu_dtu() {
        return so_tien_thu_dtu;
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

    public void setBtton(String btton) {
        this.btton = btton;
    }

    public String getBtton() {
        return btton;
    }

    public void setTrang_thai_kqdc(String trang_thai_kqdc) {
        this.trang_thai_kqdc = trang_thai_kqdc;
    }

    public String getTrang_thai_kqdc() {
        return trang_thai_kqdc;
    }

    public void setTrang_thai_bkdc(String trang_thai_bkdc) {
        this.trang_thai_bkdc = trang_thai_bkdc;
    }

    public String getTrang_thai_bkdc() {
        return trang_thai_bkdc;
    }

    public void setTu_ngay(String tu_ngay) {
        this.tu_ngay = tu_ngay;
    }

    public String getTu_ngay() {
        return tu_ngay;
    }

    public void setDen_ngay(String den_ngay) {
        this.den_ngay = den_ngay;
    }

    public String getDen_ngay() {
        return den_ngay;
    }

    public void setKq_id(String kq_id) {
        this.kq_id = kq_id;
    }

    public String getKq_id() {
        return kq_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setNgay_thien_dc(String ngay_thien_dc) {
        this.ngay_thien_dc = ngay_thien_dc;
    }

    public String getNgay_thien_dc() {
        return ngay_thien_dc;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setTtsp_id(String ttsp_id) {
        this.ttsp_id = ttsp_id;
    }

    public String getTtsp_id() {
        return ttsp_id;
    }

    public void setPht_id(String pht_id) {
        this.pht_id = pht_id;
    }

    public String getPht_id() {
        return pht_id;
    }

    public void setInKB(String inKB) {
        this.inKB = inKB;
    }

    public String getInKB() {
        return inKB;
    }

    public void setInNH(String inNH) {
        this.inNH = inNH;
    }

    public String getInNH() {
        return inNH;
    }

    public void setInxtthai(String inxtthai) {
        this.inxtthai = inxtthai;
    }

    public String getInxtthai() {
        return inxtthai;
    }

    public void setNgay_thuc_hien_dc(String ngay_thuc_hien_dc) {
        this.ngay_thuc_hien_dc = ngay_thuc_hien_dc;
    }

    public String getNgay_thuc_hien_dc() {
        return ngay_thuc_hien_dc;
    }

    public void setId_nhkb_tinh(String id_nhkb_tinh) {
        this.id_nhkb_tinh = id_nhkb_tinh;
    }

    public String getId_nhkb_tinh() {
        return id_nhkb_tinh;
    }

    public void setId_nhkb_huyen(String id_nhkb_huyen) {
        this.id_nhkb_huyen = id_nhkb_huyen;
    }

    public String getId_nhkb_huyen() {
        return id_nhkb_huyen;
    }

    public void setKet_qua_ttsp(String ket_qua_ttsp) {
        this.ket_qua_ttsp = ket_qua_ttsp;
    }

    public String getKet_qua_ttsp() {
        return ket_qua_ttsp;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }

    public void setInTinh(String inTinh) {
        this.inTinh = inTinh;
    }

    public String getInTinh() {
        return inTinh;
    }

    public void setInLanDC(String inLanDC) {
        this.inLanDC = inLanDC;
    }

    public String getInLanDC() {
        return inLanDC;
    }

    public void setInTTTK(String inTTTK) {
        this.inTTTK = inTTTK;
    }

    public String getInTTTK() {
        return inTTTK;
    }

    public void setTrang_thai_tk(String trang_thai_tk) {
        this.trang_thai_tk = trang_thai_tk;
    }

    public String getTrang_thai_tk() {
        return trang_thai_tk;
    }

    public void setPham_vi_doi_chieu(String pham_vi_doi_chieu) {
        this.pham_vi_doi_chieu = pham_vi_doi_chieu;
    }

    public String getPham_vi_doi_chieu() {
        return pham_vi_doi_chieu;
    }

    public void setInPVDC(String inPVDC) {
        this.inPVDC = inPVDC;
    }

    public String getInPVDC() {
        return inPVDC;
    }
}
