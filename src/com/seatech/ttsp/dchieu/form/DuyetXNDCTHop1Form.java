package com.seatech.ttsp.dchieu.form;


import org.apache.struts.action.ActionForm;

public class DuyetXNDCTHop1Form extends ActionForm {
    private String id;
    private String lan_dc;
    private String ngay_dc;
    private String send_bank;
    private String receive_bank;
    private String loai_dc;

    private String created_date;
    private String creator;
    private String manager;
    private String verified_date;

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

    private String msg_id;
    private String trang_thai;
    private String insert_date;

    private String ma_nh;
    private String ten;
    private String ma_dv;

    private long sodu_kbnn;
    private long chenh_lech;

    private String ltt_kb_thua;
    private String ltt_kb_thieu;
    private String dts_kb_thieu;
    private String dts_kb_thua;
    private String bkq_id;
    private String ten_send_bank;
    // doi chieu chi tiet

    private String bk_id;
    private String mt_id;
    private String send_date;
    private long f20;
    private long f21;
    private long f32as3;
    private String ngay_ts;
    private String ghi_chu;
    private String mt_type;
    private String app_type;
    private String sai_yeu_to;
    private String ngay_ct;
    private String ltt;
    private String dts_hoi;
    private String dts_tra_loi;

    private String ket_qua;
    private long so_tien_thu_tcong;
    private long so_tien_thu_dtu;
    private String ket_qua_pht;
    private int khop_lech;
    private int khop_dung;
    private String ly_do_xn;

    private long tong_ps_pht;
    private long tong_mon_pht;
    private String ct;
    private String trang_thai_bk;
    private String trang_thai_kq;
    private String btton;
    private String trang_thai_kqdc;
    private String trang_thai_bkdc;
    private String chuky_ktt;
    private String noi_dung_ky;
    private String certserial;
    private String tthai_hidden;
  private String ngaydc_hidden;

  private String nguoi_tao;
  private String ngay_tao;
  private String nguoi_ks;
  private String ngay_ks;
  private String ngay_qtoan;
  private String loai_qtoan;
  private String qtoan_thu;
  private String qtoan_chi;
  private String ndung_qtoan;
  private String kq_dc_ttsp;
  private String kq_dc_pht;
  private String chu_ky;
  private String error_code;
  private String error_desc;
  private String cho_phep_sua;
  private String id_066;
  private String tthai_dxn_thop;
  private String ndung_ky_066;
  private Long so_thu;
  private Long so_chi;
  private Long id_tcong;
  private String ma_kb;
  private String loai_tien;

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

    public void setLoai_dc(String loai_dc) {
        this.loai_dc = loai_dc;
    }

    public String getLoai_dc() {
        return loai_dc;
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

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setMa_dv(String ma_dv) {
        this.ma_dv = ma_dv;
    }

    public String getMa_dv() {
        return ma_dv;
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

    public void setTen_send_bank(String ten_send_bank) {
        this.ten_send_bank = ten_send_bank;
    }

    public String getTen_send_bank() {
        return ten_send_bank;
    }

    public void setBk_id(String bk_id) {
        this.bk_id = bk_id;
    }

    public String getBk_id() {
        return bk_id;
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

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getApp_type() {
        return app_type;
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

    public void setKet_qua(String ket_qua) {
        this.ket_qua = ket_qua;
    }

    public String getKet_qua() {
        return ket_qua;
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

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getCt() {
        return ct;
    }

    public void setChuky_ktt(String chuky_ktt) {
        this.chuky_ktt = chuky_ktt;
    }

    public String getChuky_ktt() {
        return chuky_ktt;
    }


    public void setNoi_dung_ky(String noi_dung_ky) {
        this.noi_dung_ky = noi_dung_ky;
    }

    public String getNoi_dung_ky() {
        return noi_dung_ky;
    }

    public void setCertserial(String certserial) {
        this.certserial = certserial;
    }

    public String getCertserial() {
        return certserial;
    }

    public void setTthai_hidden(String tthai_hidden) {
        this.tthai_hidden = tthai_hidden;
    }

    public String getTthai_hidden() {
        return tthai_hidden;
    }

    public void setNgaydc_hidden(String ngaydc_hidden) {
        this.ngaydc_hidden = ngaydc_hidden;
    }

    public String getNgaydc_hidden() {
        return ngaydc_hidden;
    }

    public void setNguoi_tao(String nguoi_tao) {
        this.nguoi_tao = nguoi_tao;
    }

    public String getNguoi_tao() {
        return nguoi_tao;
    }

    public void setNgay_tao(String ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public String getNgay_tao() {
        return ngay_tao;
    }

    public void setNguoi_ks(String nguoi_ks) {
        this.nguoi_ks = nguoi_ks;
    }

    public String getNguoi_ks() {
        return nguoi_ks;
    }

    public void setNgay_ks(String ngay_ks) {
        this.ngay_ks = ngay_ks;
    }

    public String getNgay_ks() {
        return ngay_ks;
    }

    public void setNgay_qtoan(String ngay_qtoan) {
        this.ngay_qtoan = ngay_qtoan;
    }

    public String getNgay_qtoan() {
        return ngay_qtoan;
    }

    public void setLoai_qtoan(String loai_qtoan) {
        this.loai_qtoan = loai_qtoan;
    }

    public String getLoai_qtoan() {
        return loai_qtoan;
    }

    public void setQtoan_thu(String qtoan_thu) {
        this.qtoan_thu = qtoan_thu;
    }

    public String getQtoan_thu() {
        return qtoan_thu;
    }

    public void setQtoan_chi(String qtoan_chi) {
        this.qtoan_chi = qtoan_chi;
    }

    public String getQtoan_chi() {
        return qtoan_chi;
    }

    public void setNdung_qtoan(String ndung_qtoan) {
        this.ndung_qtoan = ndung_qtoan;
    }

    public String getNdung_qtoan() {
        return ndung_qtoan;
    }

    public void setKq_dc_ttsp(String kq_dc_ttsp) {
        this.kq_dc_ttsp = kq_dc_ttsp;
    }

    public String getKq_dc_ttsp() {
        return kq_dc_ttsp;
    }

    public void setKq_dc_pht(String kq_dc_pht) {
        this.kq_dc_pht = kq_dc_pht;
    }

    public String getKq_dc_pht() {
        return kq_dc_pht;
    }

    public void setChu_ky(String chu_ky) {
        this.chu_ky = chu_ky;
    }

    public String getChu_ky() {
        return chu_ky;
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

    public void setCho_phep_sua(String cho_phep_sua) {
        this.cho_phep_sua = cho_phep_sua;
    }

    public String getCho_phep_sua() {
        return cho_phep_sua;
    }

    public void setId_066(String id_066) {
        this.id_066 = id_066;
    }

    public String getId_066() {
        return id_066;
    }

    public void setTthai_dxn_thop(String tthai_dxn_thop) {
        this.tthai_dxn_thop = tthai_dxn_thop;
    }

    public String getTthai_dxn_thop() {
        return tthai_dxn_thop;
    }

    public void setNdung_ky_066(String ndung_ky_066) {
        this.ndung_ky_066 = ndung_ky_066;
    }

    public String getNdung_ky_066() {
        return ndung_ky_066;
    }

    public void setSo_thu(Long so_thu) {
        this.so_thu = so_thu;
    }

    public Long getSo_thu() {
        return so_thu;
    }

    public void setSo_chi(Long so_chi) {
        this.so_chi = so_chi;
    }

    public Long getSo_chi() {
        return so_chi;
    }

    public void setId_tcong(Long id_tcong) {
        this.id_tcong = id_tcong;
    }

    public Long getId_tcong() {
        return id_tcong;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }
}
