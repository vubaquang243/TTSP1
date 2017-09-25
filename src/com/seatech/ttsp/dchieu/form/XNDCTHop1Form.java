package com.seatech.ttsp.dchieu.form;

import org.apache.struts.action.ActionForm;

public class XNDCTHop1Form extends ActionForm {
    private String id;
    private String bk_id;
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

    private long so_mon_thu_tcong;
    private long so_tien_thu_tcong;
    private long somon_tcong; // so mon chi thu cong
    private long sotien_tcong; // so tien chi thu cong
    private long so_tien_thu_dtu;
    private long sotien_dtu; // so tien chi dien tu

    private long mon_thu_tcong_kbnn;
    private long mon_chi_tcong_kbnn;
    private long tien_thu_tcong_kbnn;
    private long tien_chi_tcong_kbnn;
    private long chenhlech_thu;
    private long chenhlech_chi;
    private long ps_thu;

    private String tthai_dxn_thop;
    private long chenh_lech_thu_tcong;

    private long chenh_lech_chi_tcong;
    private String app_type;
    private String trang_thai_bkdc;
    private String trang_thai_kqdc;

    private long somon_thu;
    private long somon_chi;
    private long somon_dtu;
    private long so_mon_thu_dtu;
    private long so_ketchuyen;

    private long mon_thu_dtu_kbnn;
    private long mon_chi_dtu_kbnn;
    private long tien_thu_dtu_kbnn;
    private long tien_chi_dtu_kbnn;

    private long so_du_dau_ngay;
    private long ket_chuyen_thu;
    private long ket_chuyen_chi;

    private long chenh_sdu_dngay;
    private long chenh_kchuyen_thu;
    private long chenh_kchuyen_chi;
    private long ps_chi; //  so ket chuyen chi
    private long chenh_tthu_dtu_pht;
    private long chenh_mthu_dtu_pht;
    private String txt_thu_tcong;
    private String txt_chi_tcong;
    private String dia_txt_thu_tcong;
    private String dia_txt_chi_tcong;
    private String rowSelected;

    // bien 066
    private String id_066;
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
    private String type;
    private String txt_noi_dung;
    private String qtoan_ko_dchieu;
    private String loai_gd;
    private String loai_kq066;
    private String ket_qua_ttsp;
    private String ttsp_pht;
    private Long so_thu;
    private Long so_chi;
    private Long id_tcong;
    private String ma_nh;
    private String ma_kb;
    private String loai_ts;
    private String cho_phep_nhap_tcong;
    private String cho_phep_qtoan_tam;
    private String dia_txt_noi_dung;
    private String noi_dung;
    //ManhNV - kiem tra lai cua don vi chuyen thu
    private String lai_phi_chuyen_thu;
    
    private String loai_tien;

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

    public void setSo_mon_thu_tcong(long so_mon_thu_tcong) {
        this.so_mon_thu_tcong = so_mon_thu_tcong;
    }

    public long getSo_mon_thu_tcong() {
        return so_mon_thu_tcong;
    }

    public void setSo_tien_thu_tcong(long so_tien_thu_tcong) {
        this.so_tien_thu_tcong = so_tien_thu_tcong;
    }

    public long getSo_tien_thu_tcong() {
        return so_tien_thu_tcong;
    }

    public void setSomon_tcong(long somon_tcong) {
        this.somon_tcong = somon_tcong;
    }

    public long getSomon_tcong() {
        return somon_tcong;
    }

    public void setSotien_tcong(long sotien_tcong) {
        this.sotien_tcong = sotien_tcong;
    }

    public long getSotien_tcong() {
        return sotien_tcong;
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

    public void setTien_thu_tcong_kbnn(long tien_thu_tcong_kbnn) {
        this.tien_thu_tcong_kbnn = tien_thu_tcong_kbnn;
    }

    public long getTien_thu_tcong_kbnn() {
        return tien_thu_tcong_kbnn;
    }

    public void setTien_chi_tcong_kbnn(long tien_chi_tcong_kbnn) {
        this.tien_chi_tcong_kbnn = tien_chi_tcong_kbnn;
    }

    public long getTien_chi_tcong_kbnn() {
        return tien_chi_tcong_kbnn;
    }

    public void setSo_tien_thu_dtu(long so_tien_thu_dtu) {
        this.so_tien_thu_dtu = so_tien_thu_dtu;
    }

    public long getSo_tien_thu_dtu() {
        return so_tien_thu_dtu;
    }

    public void setSotien_dtu(long sotien_dtu) {
        this.sotien_dtu = sotien_dtu;
    }

    public long getSotien_dtu() {
        return sotien_dtu;
    }

    public void setChenhlech_thu(long chenhlech_thu) {
        this.chenhlech_thu = chenhlech_thu;
    }

    public long getChenhlech_thu() {
        return chenhlech_thu;
    }

    public void setChenhlech_chi(long chenhlech_chi) {
        this.chenhlech_chi = chenhlech_chi;
    }

    public long getChenhlech_chi() {
        return chenhlech_chi;
    }

    public void setTthai_dxn_thop(String tthai_dxn_thop) {
        this.tthai_dxn_thop = tthai_dxn_thop;
    }

    public String getTthai_dxn_thop() {
        return tthai_dxn_thop;
    }


    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setChenh_lech_thu_tcong(long chenh_lech_thu_tcong) {
        this.chenh_lech_thu_tcong = chenh_lech_thu_tcong;
    }

    public long getChenh_lech_thu_tcong() {
        return chenh_lech_thu_tcong;
    }

    public void setChenh_lech_chi_tcong(long chenh_lech_chi_tcong) {
        this.chenh_lech_chi_tcong = chenh_lech_chi_tcong;
    }

    public long getChenh_lech_chi_tcong() {
        return chenh_lech_chi_tcong;
    }

    public void setTrang_thai_bkdc(String trang_thai_bkdc) {
        this.trang_thai_bkdc = trang_thai_bkdc;
    }

    public String getTrang_thai_bkdc() {
        return trang_thai_bkdc;
    }

    public void setTrang_thai_kqdc(String trang_thai_kqdc) {
        this.trang_thai_kqdc = trang_thai_kqdc;
    }

    public String getTrang_thai_kqdc() {
        return trang_thai_kqdc;
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

    public void setSo_ketchuyen(long so_ketchuyen) {
        this.so_ketchuyen = so_ketchuyen;
    }

    public long getSo_ketchuyen() {
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

    public void setTien_thu_dtu_kbnn(long tien_thu_dtu_kbnn) {
        this.tien_thu_dtu_kbnn = tien_thu_dtu_kbnn;
    }

    public long getTien_thu_dtu_kbnn() {
        return tien_thu_dtu_kbnn;
    }

    public void setTien_chi_dtu_kbnn(long tien_chi_dtu_kbnn) {
        this.tien_chi_dtu_kbnn = tien_chi_dtu_kbnn;
    }

    public long getTien_chi_dtu_kbnn() {
        return tien_chi_dtu_kbnn;
    }

    public void setSo_du_dau_ngay(long so_du_dau_ngay) {
        this.so_du_dau_ngay = so_du_dau_ngay;
    }

    public long getSo_du_dau_ngay() {
        return so_du_dau_ngay;
    }

    public void setKet_chuyen_thu(long ket_chuyen_thu) {
        this.ket_chuyen_thu = ket_chuyen_thu;
    }

    public long getKet_chuyen_thu() {
        return ket_chuyen_thu;
    }

    public void setKet_chuyen_chi(long ket_chuyen_chi) {
        this.ket_chuyen_chi = ket_chuyen_chi;
    }

    public long getKet_chuyen_chi() {
        return ket_chuyen_chi;
    }

    public void setChenh_sdu_dngay(long chenh_sdu_dngay) {
        this.chenh_sdu_dngay = chenh_sdu_dngay;
    }

    public long getChenh_sdu_dngay() {
        return chenh_sdu_dngay;
    }

    public void setChenh_kchuyen_thu(long chenh_kchuyen_thu) {
        this.chenh_kchuyen_thu = chenh_kchuyen_thu;
    }

    public long getChenh_kchuyen_thu() {
        return chenh_kchuyen_thu;
    }

    public void setChenh_kchuyen_chi(long chenh_kchuyen_chi) {
        this.chenh_kchuyen_chi = chenh_kchuyen_chi;
    }

    public long getChenh_kchuyen_chi() {
        return chenh_kchuyen_chi;
    }

    public void setPs_chi(long ps_chi) {
        this.ps_chi = ps_chi;
    }

    public long getPs_chi() {
        return ps_chi;
    }

    public void setPs_thu(long ps_thu) {
        this.ps_thu = ps_thu;
    }

    public long getPs_thu() {
        return ps_thu;
    }

    public void setChenh_tthu_dtu_pht(long chenh_tthu_dtu_pht) {
        this.chenh_tthu_dtu_pht = chenh_tthu_dtu_pht;
    }

    public long getChenh_tthu_dtu_pht() {
        return chenh_tthu_dtu_pht;
    }

    public void setChenh_mthu_dtu_pht(long chenh_mthu_dtu_pht) {
        this.chenh_mthu_dtu_pht = chenh_mthu_dtu_pht;
    }

    public long getChenh_mthu_dtu_pht() {
        return chenh_mthu_dtu_pht;
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

    public void setRowSelected(String rowSelected) {
        this.rowSelected = rowSelected;
    }

    public String getRowSelected() {
        return rowSelected;
    }


    public void setTxt_thu_tcong(String txt_thu_tcong) {
        this.txt_thu_tcong = txt_thu_tcong;
    }

    public String getTxt_thu_tcong() {
        return txt_thu_tcong;
    }

    public void setTxt_chi_tcong(String txt_chi_tcong) {
        this.txt_chi_tcong = txt_chi_tcong;
    }

    public String getTxt_chi_tcong() {
        return txt_chi_tcong;
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

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTxt_noi_dung(String txt_noi_dung) {
        this.txt_noi_dung = txt_noi_dung;
    }

    public String getTxt_noi_dung() {
        return txt_noi_dung;
    }

    public void setQtoan_ko_dchieu(String qtoan_ko_dchieu) {
        this.qtoan_ko_dchieu = qtoan_ko_dchieu;
    }

    public String getQtoan_ko_dchieu() {
        return qtoan_ko_dchieu;
    }

    public void setLoai_gd(String loai_gd) {
        this.loai_gd = loai_gd;
    }

    public String getLoai_gd() {
        return loai_gd;
    }

    public void setId_066(String id_066) {
        this.id_066 = id_066;
    }

    public String getId_066() {
        return id_066;
    }


    public void setLoai_kq066(String loai_kq066) {
        this.loai_kq066 = loai_kq066;
    }

    public String getLoai_kq066() {
        return loai_kq066;
    }

    public void setKet_qua_ttsp(String ket_qua_ttsp) {
        this.ket_qua_ttsp = ket_qua_ttsp;
    }

    public String getKet_qua_ttsp() {
        return ket_qua_ttsp;
    }

    public void setTtsp_pht(String ttsp_pht) {
        this.ttsp_pht = ttsp_pht;
    }

    public String getTtsp_pht() {
        return ttsp_pht;
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

    public void setLoai_ts(String loai_ts) {
        this.loai_ts = loai_ts;
    }

    public String getLoai_ts() {
        return loai_ts;
    }

    public void setCho_phep_nhap_tcong(String cho_phep_nhap_tcong) {
        this.cho_phep_nhap_tcong = cho_phep_nhap_tcong;
    }

    public String getCho_phep_nhap_tcong() {
        return cho_phep_nhap_tcong;
    }

    public void setCho_phep_qtoan_tam(String cho_phep_qtoan_tam) {
        this.cho_phep_qtoan_tam = cho_phep_qtoan_tam;
    }

    public String getCho_phep_qtoan_tam() {
        return cho_phep_qtoan_tam;
    }

    public void setDia_txt_noi_dung(String dia_txt_noi_dung) {
        this.dia_txt_noi_dung = dia_txt_noi_dung;
    }

    public String getDia_txt_noi_dung() {
        return dia_txt_noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setLai_phi_chuyen_thu(String lai_phi_chuyen_thu) {
        this.lai_phi_chuyen_thu = lai_phi_chuyen_thu;
    }

    public String getLai_phi_chuyen_thu() {
        return lai_phi_chuyen_thu;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }

    public void setDia_txt_thu_tcong(String dia_txt_thu_tcong) {
        this.dia_txt_thu_tcong = dia_txt_thu_tcong;
    }

    public String getDia_txt_thu_tcong() {
        return dia_txt_thu_tcong;
    }

    public void setDia_txt_chi_tcong(String dia_txt_chi_tcong) {
        this.dia_txt_chi_tcong = dia_txt_chi_tcong;
    }

    public String getDia_txt_chi_tcong() {
        return dia_txt_chi_tcong;
    }
}
