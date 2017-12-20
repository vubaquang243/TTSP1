package com.seatech.ttsp.saoke;

import java.math.BigDecimal;

public class saoketkVO {
  private String id;
  private String ma_nh;
  private String ma_kb;
  private String so_tk;
  private String ngay_dc;
  private String ket_qua;
  private BigDecimal ps_co_nh;
  private BigDecimal ps_no_nh;
  private BigDecimal so_du_nh;
  private BigDecimal ps_co_sg_kb;
  private BigDecimal ps_no_sg_kb;
  private BigDecimal ps_co_tg_kb;
  private BigDecimal ps_no_tg_kb;
  private BigDecimal qtoan_thu;
  private BigDecimal qtoan_chi;
  private BigDecimal so_du_sg;
  private BigDecimal so_du_tg;
  private BigDecimal so_du_qt;
  private String trang_thai;
  private String noi_dung;
  private BigDecimal ps_pht_sg;
  private BigDecimal ps_pht_tg;
  
  private Long count_tt_065;
  private Long count_tt_sk;
  private Long count_tt_trc;
  private String abc;
  
  private String shkb;
  private String ten_kb;
  private String ten_nh;
  
  private Long dchieu_ske_id;
  private BigDecimal thu_pht_nh;
  private BigDecimal chi_pht_nh;
  private BigDecimal thu_ttsp_nh;
  private BigDecimal chi_ttsp_nh;
  private BigDecimal thu_khac_nh;
  private BigDecimal chi_khac_nh;
  private BigDecimal thu_pht_kb;
  private BigDecimal chi_pht_kb;
  private BigDecimal thu_ttsp_kb;
  private BigDecimal chi_ttsp_kb;
  private BigDecimal thu_ttsp_khac;
  private BigDecimal chi_ttsp_khac;
  private BigDecimal tong_so_du;
  private BigDecimal tong_ps_co;
  private BigDecimal tong_ps_no;
  private BigDecimal ss_thu_pht;
  private BigDecimal ss_chi_pht;
  private BigDecimal ss_thu_ttsp;
  private BigDecimal ss_chi_ttsp;
  private BigDecimal ss_chi_khac;
  private BigDecimal ss_thu_khac;
  private BigDecimal ss_ps_no;
  private BigDecimal ss_ps_co;
  private BigDecimal ss_so_du;
  private BigDecimal ps_co_ttsp_tg ;
  private BigDecimal ps_co_ttsp_sg;
  private BigDecimal ps_no_ttsp_tg;
  private BigDecimal ps_no_ttsp_sg;
  private String ma_nt;
  
  //20171130 thuongdt them tt_chot_so
  private String tt_chot_so;
  
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
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

    public void setSo_tk(String so_tk) {
        this.so_tk = so_tk;
    }

    public String getSo_tk() {
        return so_tk;
    }

    public void setNgay_dc(String ngay_dc) {
        this.ngay_dc = ngay_dc;
    }

    public String getNgay_dc() {
        return ngay_dc;
    }

    public void setKet_qua(String ket_qua) {
        this.ket_qua = ket_qua;
    }

    public String getKet_qua() {
        return ket_qua;
    }

    public void setPs_co_nh(BigDecimal ps_co_nh) {
        this.ps_co_nh = ps_co_nh;
    }

    public BigDecimal getPs_co_nh() {
        return ps_co_nh;
    }

    public void setPs_no_nh(BigDecimal ps_no_nh) {
        this.ps_no_nh = ps_no_nh;
    }

    public BigDecimal getPs_no_nh() {
        return ps_no_nh;
    }

    public void setSo_du_nh(BigDecimal so_du_nh) {
        this.so_du_nh = so_du_nh;
    }

    public BigDecimal getSo_du_nh() {
        return so_du_nh;
    }

    public void setPs_co_sg_kb(BigDecimal ps_co_sg_kb) {
        this.ps_co_sg_kb = ps_co_sg_kb;
    }

    public BigDecimal getPs_co_sg_kb() {
        return ps_co_sg_kb;
    }

    public void setPs_no_sg_kb(BigDecimal ps_no_sg_kb) {
        this.ps_no_sg_kb = ps_no_sg_kb;
    }

    public BigDecimal getPs_no_sg_kb() {
        return ps_no_sg_kb;
    }

    public void setPs_co_tg_kb(BigDecimal ps_co_tg_kb) {
        this.ps_co_tg_kb = ps_co_tg_kb;
    }

    public BigDecimal getPs_co_tg_kb() {
        return ps_co_tg_kb;
    }

    public void setPs_no_tg_kb(BigDecimal ps_no_tg_kb) {
        this.ps_no_tg_kb = ps_no_tg_kb;
    }

    public BigDecimal getPs_no_tg_kb() {
        return ps_no_tg_kb;
    }

    public void setQtoan_thu(BigDecimal qtoan_thu) {
        this.qtoan_thu = qtoan_thu;
    }

    public BigDecimal getQtoan_thu() {
        return qtoan_thu;
    }

    public void setQtoan_chi(BigDecimal qtoan_chi) {
        this.qtoan_chi = qtoan_chi;
    }

    public BigDecimal getQtoan_chi() {
        return qtoan_chi;
    }

    public void setSo_du_sg(BigDecimal so_du_sg) {
        this.so_du_sg = so_du_sg;
    }

    public BigDecimal getSo_du_sg() {
        return so_du_sg;
    }

    public void setSo_du_tg(BigDecimal so_du_tg) {
        this.so_du_tg = so_du_tg;
    }

    public BigDecimal getSo_du_tg() {
        return so_du_tg;
    }

    public void setSo_du_qt(BigDecimal so_du_qt) {
        this.so_du_qt = so_du_qt;
    }

    public BigDecimal getSo_du_qt() {
        return so_du_qt;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setPs_pht_sg(BigDecimal ps_pht_sg) {
        this.ps_pht_sg = ps_pht_sg;
    }

    public BigDecimal getPs_pht_sg() {
        return ps_pht_sg;
    }

    public void setPs_pht_tg(BigDecimal ps_pht_tg) {
        this.ps_pht_tg = ps_pht_tg;
    }

    public BigDecimal getPs_pht_tg() {
        return ps_pht_tg;
    }

    public void setCount_tt_065(Long count_tt_065) {
        this.count_tt_065 = count_tt_065;
    }

    public Long getCount_tt_065() {
        return count_tt_065;
    }

    public void setCount_tt_sk(Long count_tt_sk) {
        this.count_tt_sk = count_tt_sk;
    }

    public Long getCount_tt_sk() {
        return count_tt_sk;
    }

    public void setCount_tt_trc(Long count_tt_trc) {
        this.count_tt_trc = count_tt_trc;
    }

    public Long getCount_tt_trc() {
        return count_tt_trc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }

    public String getAbc() {
        return abc;
    }

    public void setShkb(String shkb) {
        this.shkb = shkb;
    }

    public String getShkb() {
        return shkb;
    }

    public void setTen_kb(String ten_kb) {
        this.ten_kb = ten_kb;
    }

    public String getTen_kb() {
        return ten_kb;
    }

    public void setTen_nh(String ten_nh) {
        this.ten_nh = ten_nh;
    }

    public String getTen_nh() {
        return ten_nh;
    }

    public void setDchieu_ske_id(Long dchieu_ske_id) {
        this.dchieu_ske_id = dchieu_ske_id;
    }

    public Long getDchieu_ske_id() {
        return dchieu_ske_id;
    }

    public void setThu_pht_nh(BigDecimal thu_pht_nh) {
        this.thu_pht_nh = thu_pht_nh;
    }

    public BigDecimal getThu_pht_nh() {
        return thu_pht_nh;
    }

    public void setChi_pht_nh(BigDecimal chi_pht_nh) {
        this.chi_pht_nh = chi_pht_nh;
    }

    public BigDecimal getChi_pht_nh() {
        return chi_pht_nh;
    }

    public void setThu_ttsp_nh(BigDecimal thu_ttsp_nh) {
        this.thu_ttsp_nh = thu_ttsp_nh;
    }

    public BigDecimal getThu_ttsp_nh() {
        return thu_ttsp_nh;
    }

    public void setChi_ttsp_nh(BigDecimal chi_ttsp_nh) {
        this.chi_ttsp_nh = chi_ttsp_nh;
    }

    public BigDecimal getChi_ttsp_nh() {
        return chi_ttsp_nh;
    }

    public void setThu_khac_nh(BigDecimal thu_khac_nh) {
        this.thu_khac_nh = thu_khac_nh;
    }

    public BigDecimal getThu_khac_nh() {
        return thu_khac_nh;
    }

    public void setChi_khac_nh(BigDecimal chi_khac_nh) {
        this.chi_khac_nh = chi_khac_nh;
    }

    public BigDecimal getChi_khac_nh() {
        return chi_khac_nh;
    }

    public void setThu_pht_kb(BigDecimal thu_pht_kb) {
        this.thu_pht_kb = thu_pht_kb;
    }

    public BigDecimal getThu_pht_kb() {
        return thu_pht_kb;
    }

    public void setChi_pht_kb(BigDecimal chi_pht_kb) {
        this.chi_pht_kb = chi_pht_kb;
    }

    public BigDecimal getChi_pht_kb() {
        return chi_pht_kb;
    }

    public void setThu_ttsp_kb(BigDecimal thu_ttsp_kb) {
        this.thu_ttsp_kb = thu_ttsp_kb;
    }

    public BigDecimal getThu_ttsp_kb() {
        return thu_ttsp_kb;
    }

    public void setChi_ttsp_kb(BigDecimal chi_ttsp_kb) {
        this.chi_ttsp_kb = chi_ttsp_kb;
    }

    public BigDecimal getChi_ttsp_kb() {
        return chi_ttsp_kb;
    }

    public void setThu_ttsp_khac(BigDecimal thu_ttsp_khac) {
        this.thu_ttsp_khac = thu_ttsp_khac;
    }

    public BigDecimal getThu_ttsp_khac() {
        return thu_ttsp_khac;
    }

    public void setChi_ttsp_khac(BigDecimal chi_ttsp_khac) {
        this.chi_ttsp_khac = chi_ttsp_khac;
    }

    public BigDecimal getChi_ttsp_khac() {
        return chi_ttsp_khac;
    }

    public void setTong_so_du(BigDecimal tong_so_du) {
        this.tong_so_du = tong_so_du;
    }

    public BigDecimal getTong_so_du() {
        return tong_so_du;
    }

    public void setTong_ps_co(BigDecimal tong_ps_co) {
        this.tong_ps_co = tong_ps_co;
    }

    public BigDecimal getTong_ps_co() {
        return tong_ps_co;
    }

    public void setTong_ps_no(BigDecimal tong_ps_no) {
        this.tong_ps_no = tong_ps_no;
    }

    public BigDecimal getTong_ps_no() {
        return tong_ps_no;
    }

    public void setSs_thu_pht(BigDecimal ss_thu_pht) {
        this.ss_thu_pht = ss_thu_pht;
    }

    public BigDecimal getSs_thu_pht() {
        return ss_thu_pht;
    }

    public void setSs_chi_pht(BigDecimal ss_chi_pht) {
        this.ss_chi_pht = ss_chi_pht;
    }

    public BigDecimal getSs_chi_pht() {
        return ss_chi_pht;
    }

    public void setSs_thu_ttsp(BigDecimal ss_thu_ttsp) {
        this.ss_thu_ttsp = ss_thu_ttsp;
    }

    public BigDecimal getSs_thu_ttsp() {
        return ss_thu_ttsp;
    }

    public void setSs_chi_ttsp(BigDecimal ss_chi_ttsp) {
        this.ss_chi_ttsp = ss_chi_ttsp;
    }

    public BigDecimal getSs_chi_ttsp() {
        return ss_chi_ttsp;
    }

    public void setSs_chi_khac(BigDecimal ss_chi_khac) {
        this.ss_chi_khac = ss_chi_khac;
    }

    public BigDecimal getSs_chi_khac() {
        return ss_chi_khac;
    }

    public void setSs_thu_khac(BigDecimal ss_thu_khac) {
        this.ss_thu_khac = ss_thu_khac;
    }

    public BigDecimal getSs_thu_khac() {
        return ss_thu_khac;
    }

    public void setSs_ps_no(BigDecimal ss_ps_no) {
        this.ss_ps_no = ss_ps_no;
    }

    public BigDecimal getSs_ps_no() {
        return ss_ps_no;
    }

    public void setSs_ps_co(BigDecimal ss_ps_co) {
        this.ss_ps_co = ss_ps_co;
    }

    public BigDecimal getSs_ps_co() {
        return ss_ps_co;
    }

    public void setSs_so_du(BigDecimal ss_so_du) {
        this.ss_so_du = ss_so_du;
    }

    public BigDecimal getSs_so_du() {
        return ss_so_du;
    }

    public void setPs_co_ttsp_tg(BigDecimal ps_co_ttsp_tg) {
        this.ps_co_ttsp_tg = ps_co_ttsp_tg;
    }

    public BigDecimal getPs_co_ttsp_tg() {
        return ps_co_ttsp_tg;
    }

    public void setPs_co_ttsp_sg(BigDecimal ps_co_ttsp_sg) {
        this.ps_co_ttsp_sg = ps_co_ttsp_sg;
    }

    public BigDecimal getPs_co_ttsp_sg() {
        return ps_co_ttsp_sg;
    }

    public void setPs_no_ttsp_tg(BigDecimal ps_no_ttsp_tg) {
        this.ps_no_ttsp_tg = ps_no_ttsp_tg;
    }

    public BigDecimal getPs_no_ttsp_tg() {
        return ps_no_ttsp_tg;
    }

    public void setPs_no_ttsp_sg(BigDecimal ps_no_ttsp_sg) {
        this.ps_no_ttsp_sg = ps_no_ttsp_sg;
    }

    public BigDecimal getPs_no_ttsp_sg() {
        return ps_no_ttsp_sg;
    }

    public void setMa_nt(String ma_nt) {
        this.ma_nt = ma_nt;
    }

    public String getMa_nt() {
        return ma_nt;
    }

    public void setTt_chot_so(String tt_chot_so) {
        this.tt_chot_so = tt_chot_so;
    }

    public String getTt_chot_so() {
        return tt_chot_so;
    }
}
