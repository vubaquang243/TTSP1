package com.seatech.ttsp.reports.sketk.forms;


import org.apache.struts.action.ActionForm;


public class SKeTKForm extends ActionForm {
    public SKeTKForm() {
        super();
    }
    private String manh;
    private String nhkb_huyen;
    private String nhkb_tinh;
    private String tennh;
    private String sotk;
    private String tu_ngay;
    private String den_ngay;
    private String thang;
    private String nam;
    private String pageNumber;
    private String send_bank;
    private String send_bank_name;
    private String receive_bank;
    private String receive_bank_name;
    private String mt_type;
    private String stt_mt;
    private String send_date;
    private String receive_date;
    private String loai_ctu;
    private String ky_hieu_mat;
    private String so_tham_chieu_gd;
    private String so_tk;
    private Long seq_number;
    private Long so_tien_du_dau;
    private String tinh_chat_du_dau;
    private String loai_tien_du_dau;
    private String ngay_du_dau;
    private Long so_tien_du_cuoi;
    private String tinh_chat_du_cuoi;
    private String loai_tien_du_cuoi;
    private String ngay_du_cuoi;
    private Long so_du_kha_dung;
    private String id;
    private String loai_sao_ke;
    private Long tong_ps_no;
    private Long tong_ps_co;
    private String loai_tien;
    public void setSotk(String sotk) {
        this.sotk = sotk;
    }

    public String getSotk() {
        return sotk;
    }

    public void setManh(String manh) {
        this.manh = manh;
    }

    public String getManh() {
        return manh;
    }

    public void setTennh(String tennh) {
        this.tennh = tennh;
    }

    public String getTennh() {
        return tennh;
    }
    public void setThang(String thang) {
        this.thang = thang;
    }

    public String getThang() {
        return thang;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getNam() {
        return nam;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setSend_bank(String send_bank) {
        this.send_bank = send_bank;
    }

    public String getSend_bank() {
        return send_bank;
    }

    public void setSend_bank_name(String send_bank_name) {
        this.send_bank_name = send_bank_name;
    }

    public String getSend_bank_name() {
        return send_bank_name;
    }

    public void setReceive_bank(String receive_bank) {
        this.receive_bank = receive_bank;
    }

    public String getReceive_bank() {
        return receive_bank;
    }

    public void setReceive_bank_name(String receive_bank_name) {
        this.receive_bank_name = receive_bank_name;
    }

    public String getReceive_bank_name() {
        return receive_bank_name;
    }

    public void setMt_type(String mt_type) {
        this.mt_type = mt_type;
    }

    public String getMt_type() {
        return mt_type;
    }

    public void setStt_mt(String stt_mt) {
        this.stt_mt = stt_mt;
    }

    public String getStt_mt() {
        return stt_mt;
    }

    public void setSend_date(String send_date) {
        this.send_date = send_date;
    }

    public String getSend_date() {
        return send_date;
    }

    public void setReceive_date(String receive_date) {
        this.receive_date = receive_date;
    }

    public String getReceive_date() {
        return receive_date;
    }

    public void setLoai_ctu(String loai_ctu) {
        this.loai_ctu = loai_ctu;
    }

    public String getLoai_ctu() {
        return loai_ctu;
    }

    public void setKy_hieu_mat(String ky_hieu_mat) {
        this.ky_hieu_mat = ky_hieu_mat;
    }

    public String getKy_hieu_mat() {
        return ky_hieu_mat;
    }

    public void setSo_tham_chieu_gd(String so_tham_chieu_gd) {
        this.so_tham_chieu_gd = so_tham_chieu_gd;
    }

    public String getSo_tham_chieu_gd() {
        return so_tham_chieu_gd;
    }

    public void setSo_tk(String so_tk) {
        this.so_tk = so_tk;
    }

    public String getSo_tk() {
        return so_tk;
    }

    public void setSeq_number(Long seq_number) {
        this.seq_number = seq_number;
    }

    public Long getSeq_number() {
        return seq_number;
    }

    public void setSo_tien_du_dau(Long so_tien_du_dau) {
        this.so_tien_du_dau = so_tien_du_dau;
    }

    public Long getSo_tien_du_dau() {
        return so_tien_du_dau;
    }

    public void setTinh_chat_du_dau(String tinh_chat_du_dau) {
        this.tinh_chat_du_dau = tinh_chat_du_dau;
    }

    public String getTinh_chat_du_dau() {
        return tinh_chat_du_dau;
    }

    public void setLoai_tien_du_dau(String loai_tien_du_dau) {
        this.loai_tien_du_dau = loai_tien_du_dau;
    }

    public String getLoai_tien_du_dau() {
        return loai_tien_du_dau;
    }

    public void setNgay_du_dau(String ngay_du_dau) {
        this.ngay_du_dau = ngay_du_dau;
    }

    public String getNgay_du_dau() {
        return ngay_du_dau;
    }

    public void setTinh_chat_du_cuoi(String tinh_chat_du_cuoi) {
        this.tinh_chat_du_cuoi = tinh_chat_du_cuoi;
    }

    public String getTinh_chat_du_cuoi() {
        return tinh_chat_du_cuoi;
    }

    public void setLoai_tien_du_cuoi(String loai_tien_du_cuoi) {
        this.loai_tien_du_cuoi = loai_tien_du_cuoi;
    }

    public String getLoai_tien_du_cuoi() {
        return loai_tien_du_cuoi;
    }

    public void setNgay_du_cuoi(String ngay_du_cuoi) {
        this.ngay_du_cuoi = ngay_du_cuoi;
    }

    public String getNgay_du_cuoi() {
        return ngay_du_cuoi;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLoai_sao_ke(String loai_sao_ke) {
        this.loai_sao_ke = loai_sao_ke;
    }

    public String getLoai_sao_ke() {
        return loai_sao_ke;
    }

    public void setNhkb_huyen(String nhkb_huyen) {
        this.nhkb_huyen = nhkb_huyen;
    }

    public String getNhkb_huyen() {
        return nhkb_huyen;
    }

    public void setNhkb_tinh(String nhkb_tinh) {
        this.nhkb_tinh = nhkb_tinh;
    }

    public String getNhkb_tinh() {
        return nhkb_tinh;
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

    public void setSo_tien_du_cuoi(Long so_tien_du_cuoi) {
        this.so_tien_du_cuoi = so_tien_du_cuoi;
    }

    public Long getSo_tien_du_cuoi() {
        return so_tien_du_cuoi;
    }

    public void setSo_du_kha_dung(Long so_du_kha_dung) {
        this.so_du_kha_dung = so_du_kha_dung;
    }

    public Long getSo_du_kha_dung() {
        return so_du_kha_dung;
    }

    public void setTong_ps_no(Long tong_ps_no) {
        this.tong_ps_no = tong_ps_no;
    }

    public Long getTong_ps_no() {
        return tong_ps_no;
    }

    public void setTong_ps_co(Long tong_ps_co) {
        this.tong_ps_co = tong_ps_co;
    }

    public Long getTong_ps_co() {
        return tong_ps_co;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }
}