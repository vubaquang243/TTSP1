package com.seatech.ttsp.nhomnsd;


public class NhomNSDVO {
    
    private Long id;
    //sp_nhom_nsd
    private String ten_nhom;
    private String loai_nhom;
    
    //sp_nsd_nhom
    private long nsd_id;
    private long nhom_id;

  

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTen_nhom(String ten_nhom) {
        this.ten_nhom = ten_nhom;
    }

    public String getTen_nhom() {
        return ten_nhom;
    }

    public void setLoai_nhom(String loai_nhom) {
        this.loai_nhom = loai_nhom;
    }

    public String getLoai_nhom() {
        return loai_nhom;
    }

    public void setNsd_id(long nsd_id) {
        this.nsd_id = nsd_id;
    }

    public long getNsd_id() {
        return nsd_id;
    }

    public void setNhom_id(long nhom_id) {
        this.nhom_id = nhom_id;
    }

    public long getNhom_id() {
        return nhom_id;
    }
}
