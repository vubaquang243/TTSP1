package com.seatech.ttsp.dmthamsohachtoan;

public class DmTSoHToanVO {
    private String id;
    private String ma_nh;
    private String loai_htoan;
    private String mo_ta;
    private String tktn;
    private String quy;
    private String dvsdns;
    private String cap_ns;
    private String chuong;
    private String nganh_kt;
    private String ndkt;
    private String dbhc;
    private String ctmt;
    private String nguon;
    private String ma_kb;
    private String du_phong;
    private String ghi_chu;

    public DmTSoHToanVO() {
        super();
    }
    //  SELECT a.id, a.ma_nh, a.loai_htoan, a.mo_ta, a.tktn, a.quy, a.dvsdns,
    //         a.cap_ns, a.chuong, a.nganh_kt, a.ndkt, a.dbhc, a.ctmt, a.nguon,
    //         a.ma_kb, a.du_phong, a.ghi_chu
    //    FROM sp_dm_tso_htoan a

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

    public void setLoai_htoan(String loai_htoan) {
        this.loai_htoan = loai_htoan;
    }

    public String getLoai_htoan() {
        return loai_htoan;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setTktn(String tktn) {
        this.tktn = tktn;
    }

    public String getTktn() {
        return tktn;
    }

    public void setQuy(String quy) {
        this.quy = quy;
    }

    public String getQuy() {
        return quy;
    }

    public void setDvsdns(String dvsdns) {
        this.dvsdns = dvsdns;
    }

    public String getDvsdns() {
        return dvsdns;
    }

    public void setCap_ns(String cap_ns) {
        this.cap_ns = cap_ns;
    }

    public String getCap_ns() {
        return cap_ns;
    }

    public void setChuong(String chuong) {
        this.chuong = chuong;
    }

    public String getChuong() {
        return chuong;
    }

    public void setNganh_kt(String nganh_kt) {
        this.nganh_kt = nganh_kt;
    }

    public String getNganh_kt() {
        return nganh_kt;
    }

    public void setNdkt(String ndkt) {
        this.ndkt = ndkt;
    }

    public String getNdkt() {
        return ndkt;
    }

    public void setDbhc(String dbhc) {
        this.dbhc = dbhc;
    }

    public String getDbhc() {
        return dbhc;
    }

    public void setCtmt(String ctmt) {
        this.ctmt = ctmt;
    }

    public String getCtmt() {
        return ctmt;
    }

    public void setNguon(String nguon) {
        this.nguon = nguon;
    }

    public String getNguon() {
        return nguon;
    }

    public void setMa_kb(String ma_kb) {
        this.ma_kb = ma_kb;
    }

    public String getMa_kb() {
        return ma_kb;
    }

    public void setDu_phong(String du_phong) {
        this.du_phong = du_phong;
    }

    public String getDu_phong() {
        return du_phong;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }
}
