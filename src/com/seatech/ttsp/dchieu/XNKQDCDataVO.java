package com.seatech.ttsp.dchieu;

import java.math.BigDecimal;

public class XNKQDCDataVO {
  private String id;
  private String bk_id;
  private String ket_qua;
  private String lan_dc;
  private String ngay_dc;
  private String send_bank;
  private String receive_bank;
  private String created_date;
  private String ngay_het_hieu_luc;
  private String ly_do_chenh_lech;
  private String insert_date;
  private String trang_thai;
  private String ket_qua_pht;

  private String ltt_kb_thua;
  private String ltt_kb_thieu;
  private String dts_kb_thieu;
  private String dts_kb_thua;

  private BigDecimal sotien_thu;
  private BigDecimal sotien_chi;
  private BigDecimal sotien_dtu;
  private BigDecimal sotien_tcong;
  private BigDecimal sodu_daungay;
  private BigDecimal so_du;
  private BigDecimal sodu_kbnn;
  private long chenh_lech;
  
  private String trang_thai_kqdc;
  private String trang_thai_bkdc;
  private BigDecimal so_tien_thu_dtu;
  private BigDecimal so_tien_thu_tcong;
  private long so_mon_thu_tcong;
  
  private long mon_thu_tcong_kbnn;
  private long mon_chi_tcong_kbnn;
  private BigDecimal tien_thu_tcong_kbnn;
  private BigDecimal tien_chi_tcong_kbnn;
  private BigDecimal chenhlech_thu;
  private BigDecimal chenhlech_chi;
  
  private String app_type;
  
private long somon_tcong;
  private long somon_thu;
  private long somon_chi;
  private long somon_dtu;
  private long so_mon_thu_dtu;
  private BigDecimal so_ketchuyen;
  private long mon_thu_dtu_kbnn;
  private long mon_chi_dtu_kbnn;
  private BigDecimal tien_thu_dtu_kbnn;
  private BigDecimal tien_chi_dtu_kbnn;
  
  private BigDecimal so_du_dau_ngay;
  private BigDecimal ket_chuyen_thu;
  private BigDecimal ket_chuyen_chi;
  private BigDecimal chenh_sdu_dngay;
  private BigDecimal chenh_kchuyen_thu;
  private BigDecimal chenh_kchuyen_chi;
  private BigDecimal ket_chuyen_chi_kb;
  private BigDecimal ps_chi; //  so ket chuyen chi
  private BigDecimal han_muc_du_no;
  private BigDecimal ps_thu;
  private BigDecimal so_du_dau_ngay_kb;
  private BigDecimal sodu_cuoi_ngay;
  private BigDecimal chenh_mthu_dtu;
  private BigDecimal chenh_mthu_tcong;
  private BigDecimal chenh_mchi_dtu;
  private BigDecimal chenh_mchi_tcong;    
  private BigDecimal chenh_tthu_dtu;
  private BigDecimal chenh_tthu_tcong;
  private BigDecimal chenh_tchi_dtu;
  private BigDecimal chenh_tchi_tcong;
  private BigDecimal chenh_tthu_dtu_pht;
  private BigDecimal chenh_mthu_dtu_pht;
  private BigDecimal tong_ps_pht;
  private BigDecimal tong_mon_pht;
  private String tthai_dxn_thop;
  private String ngay_du_dau;
  
 
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
  
  private String ten_ts;
  private String giatri_ts;
  private String cho_phep_sua;
  private String kb_id; 
  private String kq_dxn_thop;
  private String ndung_ky_066;
  private BigDecimal so_thu;
  private BigDecimal so_chi;
  private Long id_tcong; 
  private String ma_nh;
  private String ma_kb;
  private String nsd_id;
  private BigDecimal so_du_cot;
  private Long chksodu;
  private String ghi_chu;
  
  private String loai_tien;
  private String ma_nt;//ma ngoai te

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

    public void setNgay_het_hieu_luc(String ngay_het_hieu_luc) {
        this.ngay_het_hieu_luc = ngay_het_hieu_luc;
    }

    public String getNgay_het_hieu_luc() {
        return ngay_het_hieu_luc;
    }

    public void setLy_do_chenh_lech(String ly_do_chenh_lech) {
        this.ly_do_chenh_lech = ly_do_chenh_lech;
    }

    public String getLy_do_chenh_lech() {
        return ly_do_chenh_lech;
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

    public void setKet_qua_pht(String ket_qua_pht) {
        this.ket_qua_pht = ket_qua_pht;
    }

    public String getKet_qua_pht() {
        return ket_qua_pht;
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

    public void setSotien_thu(BigDecimal sotien_thu) {
        this.sotien_thu = sotien_thu;
    }

    public BigDecimal getSotien_thu() {
        return sotien_thu;
    }

    public void setSotien_chi(BigDecimal sotien_chi) {
        this.sotien_chi = sotien_chi;
    }

    public BigDecimal getSotien_chi() {
        return sotien_chi;
    }

    public void setSotien_dtu(BigDecimal sotien_dtu) {
        this.sotien_dtu = sotien_dtu;
    }

    public BigDecimal getSotien_dtu() {
        return sotien_dtu;
    }

    public void setSotien_tcong(BigDecimal sotien_tcong) {
        this.sotien_tcong = sotien_tcong;
    }

    public BigDecimal getSotien_tcong() {
        return sotien_tcong;
    }

    public void setSodu_daungay(BigDecimal sodu_daungay) {
        this.sodu_daungay = sodu_daungay;
    }

    public BigDecimal getSodu_daungay() {
        return sodu_daungay;
    }

    public void setSo_du(BigDecimal so_du) {
        this.so_du = so_du;
    }

    public BigDecimal getSo_du() {
        return so_du;
    }

    public void setSodu_kbnn(BigDecimal sodu_kbnn) {
        this.sodu_kbnn = sodu_kbnn;
    }

    public BigDecimal getSodu_kbnn() {
        return sodu_kbnn;
    }

    public void setChenh_lech(long chenh_lech) {
        this.chenh_lech = chenh_lech;
    }

    public long getChenh_lech() {
        return chenh_lech;
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

    public void setSo_tien_thu_dtu(BigDecimal so_tien_thu_dtu) {
        this.so_tien_thu_dtu = so_tien_thu_dtu;
    }

    public BigDecimal getSo_tien_thu_dtu() {
        return so_tien_thu_dtu;
    }

    public void setSo_tien_thu_tcong(BigDecimal so_tien_thu_tcong) {
        this.so_tien_thu_tcong = so_tien_thu_tcong;
    }

    public BigDecimal getSo_tien_thu_tcong() {
        return so_tien_thu_tcong;
    }

    public void setSo_mon_thu_tcong(long so_mon_thu_tcong) {
        this.so_mon_thu_tcong = so_mon_thu_tcong;
    }

    public long getSo_mon_thu_tcong() {
        return so_mon_thu_tcong;
    }

    public void setMon_thu_tcong_kbnn(long mon_thu_tcong_kbnn) {
        this.mon_thu_tcong_kbnn = mon_thu_tcong_kbnn;
    }

    public long getMon_thu_tcong_kbnn() {
        return mon_thu_tcong_kbnn;
    }

    public void setMon_chi_tcong_kbnn(long mon_chi_tcong_kbnn) {
        this.mon_chi_tcong_kbnn = mon_chi_tcong_kbnn;
    }

    public long getMon_chi_tcong_kbnn() {
        return mon_chi_tcong_kbnn;
    }

    public void setTien_thu_tcong_kbnn(BigDecimal tien_thu_tcong_kbnn) {
        this.tien_thu_tcong_kbnn = tien_thu_tcong_kbnn;
    }

    public BigDecimal getTien_thu_tcong_kbnn() {
        return tien_thu_tcong_kbnn;
    }

    public void setTien_chi_tcong_kbnn(BigDecimal tien_chi_tcong_kbnn) {
        this.tien_chi_tcong_kbnn = tien_chi_tcong_kbnn;
    }

    public BigDecimal getTien_chi_tcong_kbnn() {
        return tien_chi_tcong_kbnn;
    }

    public void setChenhlech_thu(BigDecimal chenhlech_thu) {
        this.chenhlech_thu = chenhlech_thu;
    }

    public BigDecimal getChenhlech_thu() {
        return chenhlech_thu;
    }

    public void setChenhlech_chi(BigDecimal chenhlech_chi) {
        this.chenhlech_chi = chenhlech_chi;
    }

    public BigDecimal getChenhlech_chi() {
        return chenhlech_chi;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setSomon_tcong(long somon_tcong) {
        this.somon_tcong = somon_tcong;
    }

    public long getSomon_tcong() {
        return somon_tcong;
    }

    public void setSomon_thu(long somon_thu) {
        this.somon_thu = somon_thu;
    }

    public long getSomon_thu() {
        return somon_thu;
    }

    public void setSomon_chi(long somon_chi) {
        this.somon_chi = somon_chi;
    }

    public long getSomon_chi() {
        return somon_chi;
    }

    public void setSomon_dtu(long somon_dtu) {
        this.somon_dtu = somon_dtu;
    }

    public long getSomon_dtu() {
        return somon_dtu;
    }

    public void setSo_mon_thu_dtu(long so_mon_thu_dtu) {
        this.so_mon_thu_dtu = so_mon_thu_dtu;
    }

    public long getSo_mon_thu_dtu() {
        return so_mon_thu_dtu;
    }

    public void setSo_ketchuyen(BigDecimal so_ketchuyen) {
        this.so_ketchuyen = so_ketchuyen;
    }

    public BigDecimal getSo_ketchuyen() {
        return so_ketchuyen;
    }

    public void setMon_thu_dtu_kbnn(long mon_thu_dtu_kbnn) {
        this.mon_thu_dtu_kbnn = mon_thu_dtu_kbnn;
    }

    public long getMon_thu_dtu_kbnn() {
        return mon_thu_dtu_kbnn;
    }

    public void setMon_chi_dtu_kbnn(long mon_chi_dtu_kbnn) {
        this.mon_chi_dtu_kbnn = mon_chi_dtu_kbnn;
    }

    public long getMon_chi_dtu_kbnn() {
        return mon_chi_dtu_kbnn;
    }

    public void setTien_thu_dtu_kbnn(BigDecimal tien_thu_dtu_kbnn) {
        this.tien_thu_dtu_kbnn = tien_thu_dtu_kbnn;
    }

    public BigDecimal getTien_thu_dtu_kbnn() {
        return tien_thu_dtu_kbnn;
    }

    public void setTien_chi_dtu_kbnn(BigDecimal tien_chi_dtu_kbnn) {
        this.tien_chi_dtu_kbnn = tien_chi_dtu_kbnn;
    }

    public BigDecimal getTien_chi_dtu_kbnn() {
        return tien_chi_dtu_kbnn;
    }

    public void setSo_du_dau_ngay(BigDecimal so_du_dau_ngay) {
        this.so_du_dau_ngay = so_du_dau_ngay;
    }

    public BigDecimal getSo_du_dau_ngay() {
        return so_du_dau_ngay;
    }

    public void setChenh_sdu_dngay(BigDecimal chenh_sdu_dngay) {
        this.chenh_sdu_dngay = chenh_sdu_dngay;
    }

    public BigDecimal getChenh_sdu_dngay() {
        return chenh_sdu_dngay;
    }

    public void setChenh_kchuyen_thu(BigDecimal chenh_kchuyen_thu) {
        this.chenh_kchuyen_thu = chenh_kchuyen_thu;
    }

    public BigDecimal getChenh_kchuyen_thu() {
        return chenh_kchuyen_thu;
    }

    public void setChenh_kchuyen_chi(BigDecimal chenh_kchuyen_chi) {
        this.chenh_kchuyen_chi = chenh_kchuyen_chi;
    }

    public BigDecimal getChenh_kchuyen_chi() {
        return chenh_kchuyen_chi;
    }

    public void setKet_chuyen_chi_kb(BigDecimal ket_chuyen_chi_kb) {
        this.ket_chuyen_chi_kb = ket_chuyen_chi_kb;
    }

    public BigDecimal getKet_chuyen_chi_kb() {
        return ket_chuyen_chi_kb;
    }

    public void setPs_chi(BigDecimal ps_chi) {
        this.ps_chi = ps_chi;
    }

    public BigDecimal getPs_chi() {
        return ps_chi;
    }

    public void setHan_muc_du_no(BigDecimal han_muc_du_no) {
        this.han_muc_du_no = han_muc_du_no;
    }

    public BigDecimal getHan_muc_du_no() {
        return han_muc_du_no;
    }

    public void setPs_thu(BigDecimal ps_thu) {
        this.ps_thu = ps_thu;
    }

    public BigDecimal getPs_thu() {
        return ps_thu;
    }

    public void setSo_du_dau_ngay_kb(BigDecimal so_du_dau_ngay_kb) {
        this.so_du_dau_ngay_kb = so_du_dau_ngay_kb;
    }

    public BigDecimal getSo_du_dau_ngay_kb() {
        return so_du_dau_ngay_kb;
    }

    public void setSodu_cuoi_ngay(BigDecimal sodu_cuoi_ngay) {
        this.sodu_cuoi_ngay = sodu_cuoi_ngay;
    }

    public BigDecimal getSodu_cuoi_ngay() {
        return sodu_cuoi_ngay;
    }

    public void setChenh_mthu_dtu(BigDecimal chenh_mthu_dtu) {
        this.chenh_mthu_dtu = chenh_mthu_dtu;
    }

    public BigDecimal getChenh_mthu_dtu() {
        return chenh_mthu_dtu;
    }

    public void setChenh_mthu_tcong(BigDecimal chenh_mthu_tcong) {
        this.chenh_mthu_tcong = chenh_mthu_tcong;
    }

    public BigDecimal getChenh_mthu_tcong() {
        return chenh_mthu_tcong;
    }

    public void setChenh_mchi_dtu(BigDecimal chenh_mchi_dtu) {
        this.chenh_mchi_dtu = chenh_mchi_dtu;
    }

    public BigDecimal getChenh_mchi_dtu() {
        return chenh_mchi_dtu;
    }

    public void setChenh_mchi_tcong(BigDecimal chenh_mchi_tcong) {
        this.chenh_mchi_tcong = chenh_mchi_tcong;
    }

    public BigDecimal getChenh_mchi_tcong() {
        return chenh_mchi_tcong;
    }

    public void setChenh_tthu_dtu(BigDecimal chenh_tthu_dtu) {
        this.chenh_tthu_dtu = chenh_tthu_dtu;
    }

    public BigDecimal getChenh_tthu_dtu() {
        return chenh_tthu_dtu;
    }

    public void setChenh_tthu_tcong(BigDecimal chenh_tthu_tcong) {
        this.chenh_tthu_tcong = chenh_tthu_tcong;
    }

    public BigDecimal getChenh_tthu_tcong() {
        return chenh_tthu_tcong;
    }

    public void setChenh_tchi_dtu(BigDecimal chenh_tchi_dtu) {
        this.chenh_tchi_dtu = chenh_tchi_dtu;
    }

    public BigDecimal getChenh_tchi_dtu() {
        return chenh_tchi_dtu;
    }

    public void setChenh_tchi_tcong(BigDecimal chenh_tchi_tcong) {
        this.chenh_tchi_tcong = chenh_tchi_tcong;
    }

    public BigDecimal getChenh_tchi_tcong() {
        return chenh_tchi_tcong;
    }

    public void setChenh_tthu_dtu_pht(BigDecimal chenh_tthu_dtu_pht) {
        this.chenh_tthu_dtu_pht = chenh_tthu_dtu_pht;
    }

    public BigDecimal getChenh_tthu_dtu_pht() {
        return chenh_tthu_dtu_pht;
    }

    public void setChenh_mthu_dtu_pht(BigDecimal chenh_mthu_dtu_pht) {
        this.chenh_mthu_dtu_pht = chenh_mthu_dtu_pht;
    }

    public BigDecimal getChenh_mthu_dtu_pht() {
        return chenh_mthu_dtu_pht;
    }

    public void setTong_ps_pht(BigDecimal tong_ps_pht) {
        this.tong_ps_pht = tong_ps_pht;
    }

    public BigDecimal getTong_ps_pht() {
        return tong_ps_pht;
    }

    public void setTong_mon_pht(BigDecimal tong_mon_pht) {
        this.tong_mon_pht = tong_mon_pht;
    }

    public BigDecimal getTong_mon_pht() {
        return tong_mon_pht;
    }

    public void setTthai_dxn_thop(String tthai_dxn_thop) {
        this.tthai_dxn_thop = tthai_dxn_thop;
    }

    public String getTthai_dxn_thop() {
        return tthai_dxn_thop;
    }

    public void setNgay_du_dau(String ngay_du_dau) {
        this.ngay_du_dau = ngay_du_dau;
    }

    public String getNgay_du_dau() {
        return ngay_du_dau;
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

    public void setTen_ts(String ten_ts) {
        this.ten_ts = ten_ts;
    }

    public String getTen_ts() {
        return ten_ts;
    }

    public void setGiatri_ts(String giatri_ts) {
        this.giatri_ts = giatri_ts;
    }

    public String getGiatri_ts() {
        return giatri_ts;
    }

    public void setCho_phep_sua(String cho_phep_sua) {
        this.cho_phep_sua = cho_phep_sua;
    }

    public String getCho_phep_sua() {
        return cho_phep_sua;
    }

    public void setKb_id(String kb_id) {
        this.kb_id = kb_id;
    }

    public String getKb_id() {
        return kb_id;
    }

    public void setKq_dxn_thop(String kq_dxn_thop) {
        this.kq_dxn_thop = kq_dxn_thop;
    }

    public String getKq_dxn_thop() {
        return kq_dxn_thop;
    }

    public void setNdung_ky_066(String ndung_ky_066) {
        this.ndung_ky_066 = ndung_ky_066;
    }

    public String getNdung_ky_066() {
        return ndung_ky_066;
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

    public void setId_tcong(Long id_tcong) {
        this.id_tcong = id_tcong;
    }

    public Long getId_tcong() {
        return id_tcong;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setNsd_id(String nsd_id) {
        this.nsd_id = nsd_id;
    }

    public String getNsd_id() {
        return nsd_id;
    }

    public void setSo_du_cot(BigDecimal so_du_cot) {
        this.so_du_cot = so_du_cot;
    }

    public BigDecimal getSo_du_cot() {
        return so_du_cot;
    }

    public void setChksodu(Long chksodu) {
        this.chksodu = chksodu;
    }

    public Long getChksodu() {
        return chksodu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }


    public void setKet_chuyen_thu(BigDecimal ket_chuyen_thu) {
        this.ket_chuyen_thu = ket_chuyen_thu;
    }

    public BigDecimal getKet_chuyen_thu() {
        return ket_chuyen_thu;
    }

    public void setKet_chuyen_chi(BigDecimal ket_chuyen_chi) {
        this.ket_chuyen_chi = ket_chuyen_chi;
    }

    public BigDecimal getKet_chuyen_chi() {
        return ket_chuyen_chi;
    }
}
