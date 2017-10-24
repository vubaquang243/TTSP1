package com.seatech.ttsp.denghiqt.form;

import com.seatech.framework.strustx.AppForm;

import java.util.List;

public class DeNghiQuyetToanForm extends AppForm {
    private String ngayQT;
    
    private String loaiQT;
    
    private String QToanChi;
    
    private String QToanBu;
    
    private String loaiTien;
    
    private String maNH;
    
    private String noiDungQToan;

    private List listData;
    
    public void setNgayQT(String ngayQT) {
        this.ngayQT = ngayQT;
    }

    public String getNgayQT() {
        return ngayQT;
    }

    public void setLoaiQT(String loaiQT) {
        this.loaiQT = loaiQT;
    }

    public String getLoaiQT() {
        return loaiQT;
    }

    public void setQToanChi(String QToanChi) {
        this.QToanChi = QToanChi;
    }

    public String getQToanChi() {
        return QToanChi;
    }

    public void setQToanBu(String QToanBu) {
        this.QToanBu = QToanBu;
    }

    public String getQToanBu() {
        return QToanBu;
    }

    public void setLoaiTien(String loaiTien) {
        this.loaiTien = loaiTien;
    }

    public String getLoaiTien() {
        return loaiTien;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public String getMaNH() {
        return maNH;
    }

    public void setNoiDungQToan(String noiDungQToan) {
        this.noiDungQToan = noiDungQToan;
    }

    public String getNoiDungQToan() {
        return noiDungQToan;
    }

    public void setListData(List listData) {
        this.listData = listData;
    }

    public List getListData() {
        return listData;
    }
}
