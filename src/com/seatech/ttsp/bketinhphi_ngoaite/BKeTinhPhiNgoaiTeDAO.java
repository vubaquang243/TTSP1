package com.seatech.ttsp.bketinhphi_ngoaite;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.Timestamp;

import java.util.List;
import java.util.Vector;

public class BKeTinhPhiNgoaiTeDAO extends AppDAO {
    private Connection conn = null;
    private static final String nameBKeTinhPhiVO = "com.seatech.ttsp.bketinhphi_ngoaite.BKeTinhPhiNgoaiTeVO";
  
    public BKeTinhPhiNgoaiTeDAO(Connection conn) {
        this.conn = conn;
    }

    public List getListBKeTinhPhiNgoaiTe_PTrang(String string, Vector vector,
                                  Integer currentPage, Integer numberRowOnPage,
                                  Integer[] totalCount) throws Exception {
        try {
            //Cho ngay den ngay
//            String strSQL = " SELECT  to_char(b.ngay_tt,'DD/MM/YYYY') ngay_tt,a.id,a.msg_id,b.loai_ct,b.ma_nt,b.phi_nt,b.ty_gia,b.phi_vnd " + 
//            "  FROM  sp_bke_phi_ngoai_te a, sp_bke_phi_ctiet_ngoai_te b, sp_dm_manh_shkb c " + 
//            " WHERE  a.id = b.bk_id AND a.receive_bank = c.ma_nh " + string+"  ORDER BY ngay_tt DESC,loai_ct DESC ,ma_nt DESC ";
            String strSQL = "  SELECT  DISTINCT c.ten ten_kb,  d.ten ten_nh, to_char(a.tu_ngay,'DD/MM/YYYY') tu_ngay, to_char(a.den_ngay,'DD/MM/YYYY') den_ngay, a.so_tk, a.phi, a.phi_sau_vat, a.id, a.msg_id " + 
            "    FROM  sp_bke_phi_ngoai_te a, sp_bke_phi_ctiet_ngoai_te b, sp_dm_manh_shkb c, sp_dm_ngan_hang d, sp_tknh_kb e, sp_dm_htkb f " + 
            "   WHERE  a.id = b.bk_id AND a.receive_bank = c.ma_nh AND a.send_bank = d.ma_nh AND d.tinh_trang = 1 AND f.ma = c.shkb AND e.kb_id = f.id AND e.nh_id = d.id AND e.so_tk = a.so_tk " + string + 
            " ORDER BY  TO_DATE (den_ngay, 'DD/MM/YYYY') ASC, c.ten DESC, d.ten DESC";
            return (List)executeSelectWithPaging(conn, strSQL, vector, nameBKeTinhPhiVO, currentPage, numberRowOnPage, totalCount);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(nameBKeTinhPhiVO + ".getListBKeTinhPhiNgoaiTe_PTrang(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        
    }
    
    public BKeTinhPhiNgoaiTeVO findBKePhiNgoaiTe(Vector vParam) throws Exception{
        String sql = " SELECT a.id, a.send_bank, a.receive_bank, a.created_date, a.creator, " + 
        "       a.manager, a.verified_date, a.tu_ngay, a.den_ngay, a.so_tk, " + 
        "       a.loai_tien, a.phi, a.vat, a.phi_sau_vat, a.insert_date, " + 
        "       a.msg_id,vietnamesenumbertowords.fnc_doc_tien (a.phi_sau_vat, 'VND') tien_bang_chu " + 
        "  FROM sp_bke_phi_ngoai_te a WHERE ID = ?";
        return (BKeTinhPhiNgoaiTeVO)findByPK(sql, vParam, BKeTinhPhiNgoaiTeDAO.nameBKeTinhPhiVO  , conn);
    }
}
