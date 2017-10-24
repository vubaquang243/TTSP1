package com.seatech.ttsp.dchieu;

import java.math.BigDecimal;

public class KQDChieu1VO {
    private String id;
    private String bk_id;
    private String kq_id;
    private String qtoan_id;
    private String ket_qua;
    private String lan_dc;
    private String loai_dc;
    private String ngay_dc;
    private String send_bank;
    private String ngay_thien_dc;

    private String receive_bank;
    private String created_date;
    private String creator;
    private String manager;

    private String verified_date;
    private BigDecimal sodu_kbnn;
    private long chenh_lech;
    private String msg_id;
    private String msg_th_id;

    private String insert_date;
    private String trang_thai;

    private String ltt_kb_thua;
    private String ltt_kb_thieu;
    private String dts_kb_thieu;
    private String dts_kb_thua;
    private String bkq_id;
    private String quyet_toan;

    private String ket_qua_pht;
    private int khop_lech;
    private int khop_dung;
    private String ly_do_xn;
    private String btton;
    private String trang_thai_bkdc;
    private String trang_thai_kqdc;
    private String ly_do_chenh_lech;

    private long mon_thu_tcong_kbnn;
    private BigDecimal sotien_tcong;
    private BigDecimal so_tien_thu_tcong;
    private long mon_chi_tcong_kbnn;
    private BigDecimal tien_thu_tcong_kbnn;
    private BigDecimal tien_chi_tcong_kbnn;
    private BigDecimal tong_ps_pht;
    private long tong_mon_pht;
    private String app_type;
    private String ma_nsd;
    private String tthai_dxn_thop;
    private BigDecimal chenh_lech_thu_tcong;
    private String msg_refid;

    private long chenh_lech_chi_tcong;
    private String nhkb_tinh;
    private String nhkb_huyen;
    private String ma_nh;
    private String ma;
    private String ten;
    private String ttsp_id;
    private String pht_id;

    private long mon_thu_dtu_kbnn;

    private long mon_chi_dtu_kbnn;
    private BigDecimal tien_thu_dtu_kbnn;
    private BigDecimal tien_chi_dtu_kbnn;

    private BigDecimal so_du_dau_ngay;
    private BigDecimal ket_chuyen_thu;
    private BigDecimal ket_chuyen_chi;
    private BigDecimal so_du;
    private String trang_thai_bk;
    private String trang_thai_kq;

    private String chuky_ktt;
    private String chuky_ktt_dxn_thop;
    private String ket_qua_dxn_thop;
    private String tthai_ttin_thop;
    private String mt_id;
    private String tthai_duyet;
    private String ngay_order;
    private String nguoi_tao_xn_qtoan;
    private String ngay_tao_xn_qtoan;
    private String nguoi_ks_xn_qtoan;
    private String ngay_ks_xn_qtoan;

    private String loai_tien;
    private String tt_dc_tu_dong;//trang thai doi chieu tu dong
    private String ma_nt;//ma ngoai te
    private String trang_thai_tk;//trang thai tai khoan
    private String pham_vi_doi_chieu; //Pham vi doi chieu


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

    public void setKq_id(String kq_id) {
        this.kq_id = kq_id;
    }

    public String getKq_id() {
        return kq_id;
    }

    public void setQtoan_id(String qtoan_id) {
        this.qtoan_id = qtoan_id;
    }

    public String getQtoan_id() {
        return qtoan_id;
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

    public void setLoai_dc(String loai_dc) {
        this.loai_dc = loai_dc;
    }

    public String getLoai_dc() {
        return loai_dc;
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

    public void setNgay_thien_dc(String ngay_thien_dc) {
        this.ngay_thien_dc = ngay_thien_dc;
    }

    public String getNgay_thien_dc() {
        return ngay_thien_dc;
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

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_th_id(String msg_th_id) {
        this.msg_th_id = msg_th_id;
    }

    public String getMsg_th_id() {
        return msg_th_id;
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

    public void setBtton(String btton) {
        this.btton = btton;
    }

    public String getBtton() {
        return btton;
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

    public void setLy_do_chenh_lech(String ly_do_chenh_lech) {
        this.ly_do_chenh_lech = ly_do_chenh_lech;
    }

    public String getLy_do_chenh_lech() {
        return ly_do_chenh_lech;
    }

    public void setMon_thu_tcong_kbnn(long mon_thu_tcong_kbnn) {
        this.mon_thu_tcong_kbnn = mon_thu_tcong_kbnn;
    }

    public long getMon_thu_tcong_kbnn() {
        return mon_thu_tcong_kbnn;
    }

    public void setSotien_tcong(BigDecimal sotien_tcong) {
        this.sotien_tcong = sotien_tcong;
    }

    public BigDecimal getSotien_tcong() {
        return sotien_tcong;
    }

    public void setSo_tien_thu_tcong(BigDecimal so_tien_thu_tcong) {
        this.so_tien_thu_tcong = so_tien_thu_tcong;
    }

    public BigDecimal getSo_tien_thu_tcong() {
        return so_tien_thu_tcong;
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

    public void setTong_ps_pht(BigDecimal tong_ps_pht) {
        this.tong_ps_pht = tong_ps_pht;
    }

    public BigDecimal getTong_ps_pht() {
        return tong_ps_pht;
    }

    public void setTong_mon_pht(long tong_mon_pht) {
        this.tong_mon_pht = tong_mon_pht;
    }

    public long getTong_mon_pht() {
        return tong_mon_pht;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setMa_nsd(String ma_nsd) {
        this.ma_nsd = ma_nsd;
    }

    public String getMa_nsd() {
        return ma_nsd;
    }

    public void setTthai_dxn_thop(String tthai_dxn_thop) {
        this.tthai_dxn_thop = tthai_dxn_thop;
    }

    public String getTthai_dxn_thop() {
        return tthai_dxn_thop;
    }

    public void setChenh_lech_thu_tcong(BigDecimal chenh_lech_thu_tcong) {
        this.chenh_lech_thu_tcong = chenh_lech_thu_tcong;
    }

    public BigDecimal getChenh_lech_thu_tcong() {
        return chenh_lech_thu_tcong;
    }

    public void setMsg_refid(String msg_refid) {
        this.msg_refid = msg_refid;
    }

    public String getMsg_refid() {
        return msg_refid;
    }

    public void setChenh_lech_chi_tcong(long chenh_lech_chi_tcong) {
        this.chenh_lech_chi_tcong = chenh_lech_chi_tcong;
    }

    public long getChenh_lech_chi_tcong() {
        return chenh_lech_chi_tcong;
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

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
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

    public void setSo_du(BigDecimal so_du) {
        this.so_du = so_du;
    }

    public BigDecimal getSo_du() {
        return so_du;
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

    public void setChuky_ktt(String chuky_ktt) {
        this.chuky_ktt = chuky_ktt;
    }

    public String getChuky_ktt() {
        return chuky_ktt;
    }

    public void setChuky_ktt_dxn_thop(String chuky_ktt_dxn_thop) {
        this.chuky_ktt_dxn_thop = chuky_ktt_dxn_thop;
    }

    public String getChuky_ktt_dxn_thop() {
        return chuky_ktt_dxn_thop;
    }

    public void setKet_qua_dxn_thop(String ket_qua_dxn_thop) {
        this.ket_qua_dxn_thop = ket_qua_dxn_thop;
    }

    public String getKet_qua_dxn_thop() {
        return ket_qua_dxn_thop;
    }

    public void setTthai_ttin_thop(String tthai_ttin_thop) {
        this.tthai_ttin_thop = tthai_ttin_thop;
    }

    public String getTthai_ttin_thop() {
        return tthai_ttin_thop;
    }

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }

    public String getMt_id() {
        return mt_id;
    }

    public void setTthai_duyet(String tthai_duyet) {
        this.tthai_duyet = tthai_duyet;
    }

    public String getTthai_duyet() {
        return tthai_duyet;
    }

    public void setNgay_order(String ngay_order) {
        this.ngay_order = ngay_order;
    }

    public String getNgay_order() {
        return ngay_order;
    }

    public void setNguoi_tao_xn_qtoan(String nguoi_tao_xn_qtoan) {
        this.nguoi_tao_xn_qtoan = nguoi_tao_xn_qtoan;
    }

    public String getNguoi_tao_xn_qtoan() {
        return nguoi_tao_xn_qtoan;
    }

    public void setNgay_tao_xn_qtoan(String ngay_tao_xn_qtoan) {
        this.ngay_tao_xn_qtoan = ngay_tao_xn_qtoan;
    }

    public String getNgay_tao_xn_qtoan() {
        return ngay_tao_xn_qtoan;
    }

    public void setNguoi_ks_xn_qtoan(String nguoi_ks_xn_qtoan) {
        this.nguoi_ks_xn_qtoan = nguoi_ks_xn_qtoan;
    }

    public String getNguoi_ks_xn_qtoan() {
        return nguoi_ks_xn_qtoan;
    }

    public void setNgay_ks_xn_qtoan(String ngay_ks_xn_qtoan) {
        this.ngay_ks_xn_qtoan = ngay_ks_xn_qtoan;
    }

    public String getNgay_ks_xn_qtoan() {
        return ngay_ks_xn_qtoan;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }

    public void setQuyet_toan(String quyet_toan) {
        this.quyet_toan = quyet_toan;
    }

    public String getQuyet_toan() {
        return quyet_toan;
    }

    public void setTt_dc_tu_dong(String tt_dc_tu_dong) {
        this.tt_dc_tu_dong = tt_dc_tu_dong;
    }

    public String getTt_dc_tu_dong() {
        return tt_dc_tu_dong;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
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
}
