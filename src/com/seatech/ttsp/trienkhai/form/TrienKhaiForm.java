package com.seatech.ttsp.trienkhai.form;

import java.math.BigDecimal;

import org.apache.struts.action.ActionForm;
/**
 * @modify: thuongdt
 * @modify-date: 30/10/2017
 * @see: chuyen han han_muc_no,han_muc_co tu long sang float de thiet lap duoc so du le
 * @find: 20171030
 * */
public class TrienKhaiForm extends ActionForm{
    private String shkb;
    private String ten;
    private Long shkb_id;
    private String ma_nh;
    private String ma_cn_nh;
    private Long nh_id;
    private String ma_dvsdns;
    private String ma;
    private String ma_cha;
    private String id_cha;
    private String ma_t;
    private String ma_h;
    private Long id;
    private String type;
    private String type_srv;
    private String step;
    private String cap;
    private String exeption;
    private String ngay_tk;
    private String so_tk;
    private String loai_tien;
    //20171030 thuongdt doi type =>> ngoai te co so le
    private String han_muc_no;
    private String han_muc_co;
    private String loai_tk;
    private String loai_gd;
    private String quyet_toan;
    private String loai_tien_kt;
    private BigDecimal so_du;
    private String ngay_du_cuoi;
    private String cut_of_time;
    
    public void setShkb(String shkb) {
        this.shkb = shkb;
    }

    public String getShkb() {
        return shkb;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setShkb_id(Long shkb_id) {
        this.shkb_id = shkb_id;
    }

    public Long getShkb_id() {
        return shkb_id;
    }

    public void setMa_nh(String ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getMa_nh() {
        return ma_nh;
    }

    public void setNh_id(Long nh_id) {
        this.nh_id = nh_id;
    }

    public Long getNh_id() {
        return nh_id;
    }

    public void setMa_dvsdns(String ma_dvsdns) {
        this.ma_dvsdns = ma_dvsdns;
    }

    public String getMa_dvsdns() {
        return ma_dvsdns;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMa() {
        return ma;
    }

    public void setMa_cha(String ma_cha) {
        this.ma_cha = ma_cha;
    }

    public String getMa_cha() {
        return ma_cha;
    }

    public void setId_cha(String id_cha) {
        this.id_cha = id_cha;
    }

    public String getId_cha() {
        return id_cha;
    }

    public void setMa_t(String ma_t) {
        this.ma_t = ma_t;
    }

    public String getMa_t() {
        return ma_t;
    }

    public void setMa_h(String ma_h) {
        this.ma_h = ma_h;
    }

    public String getMa_h() {
        return ma_h;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getStep() {
        return step;
    }

    public void setType_srv(String type_srv) {
        this.type_srv = type_srv;
    }

    public String getType_srv() {
        return type_srv;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCap() {
        return cap;
    }

    public void setExeption(String exeption) {
        this.exeption = exeption;
    }

    public String getExeption() {
        return exeption;
    }

    public void setNgay_tk(String ngay_tk) {
        this.ngay_tk = ngay_tk;
    }

    public String getNgay_tk() {
        return ngay_tk;
    }

    public void setSo_tk(String so_tk) {
        this.so_tk = so_tk;
    }

    public String getSo_tk() {
        return so_tk;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public String getLoai_tien() {
        return loai_tien;
    }

    public void setLoai_tk(String loai_tk) {
        this.loai_tk = loai_tk;
    }

    public String getLoai_tk() {
        return loai_tk;
    }

    public void setLoai_gd(String loai_gd) {
        this.loai_gd = loai_gd;
    }

    public String getLoai_gd() {
        return loai_gd;
    }

    public void setQuyet_toan(String quyet_toan) {
        this.quyet_toan = quyet_toan;
    }

    public String getQuyet_toan() {
        return quyet_toan;
    }

    public void setMa_cn_nh(String ma_cn_nh) {
        this.ma_cn_nh = ma_cn_nh;
    }

    public String getMa_cn_nh() {
        return ma_cn_nh;
    }

    public void setLoai_tien_kt(String loai_tien_kt) {
        this.loai_tien_kt = loai_tien_kt;
    }

    public String getLoai_tien_kt() {
        return loai_tien_kt;
    }

    public void setSo_du(BigDecimal so_du) {
        this.so_du = so_du;
    }

    public BigDecimal getSo_du() {
        return so_du;
    }

    public void setNgay_du_cuoi(String ngay_du_cuoi) {
        this.ngay_du_cuoi = ngay_du_cuoi;
    }

    public String getNgay_du_cuoi() {
        return ngay_du_cuoi;
    }

    public void setCut_of_time(String cut_of_time) {
        this.cut_of_time = cut_of_time;
    }

    public String getCut_of_time() {
        return cut_of_time;
    }


    public void setHan_muc_no(String han_muc_no) {
        this.han_muc_no = han_muc_no;
    }

    public String getHan_muc_no() {
        return han_muc_no;
    }

    public void setHan_muc_co(String han_muc_co) {
        this.han_muc_co = han_muc_co;
    }

    public String getHan_muc_co() {
        return han_muc_co;
    }
}
