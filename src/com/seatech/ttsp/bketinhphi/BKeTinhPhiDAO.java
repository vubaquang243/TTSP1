package com.seatech.ttsp.bketinhphi;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import com.seatech.ttsp.bketinhphi_ngoaite.BKeTinhPhiNgoaiTeDAO;
import com.seatech.ttsp.bketinhphi_ngoaite.BKeTinhPhiNgoaiTeVO;

import java.sql.Connection;

import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;

public class BKeTinhPhiDAO extends AppDAO {
    private Connection conn = null;
    private static final String strValueObjectVO =
        "com.seatech.ttsp.bketinhphi.BKeTinhPhiVO";

    public BKeTinhPhiDAO(Connection conn) {
        this.conn = conn;
    }

    public Collection getListBKeTinhPhi_PTrang(String strWhere, Vector vParam,
                                               Integer page, Integer count,
                                               Integer[] totalCount) throws Exception {
        try {
            String strSQL = "/* Formatted on 4/15/2015 11:33:44 AM (QP5 v5.126) */ " + 
            "  SELECT  DISTINCT c.ten ten_kb, " + 
            "                   d.ten ten_nh, " + 
            "                   TO_CHAR (a.tu_ngay, 'DD/MM/YYYY') tu_ngay, " + 
            "                   TO_CHAR (a.den_ngay, 'DD/MM/YYYY') den_ngay, " + 
            "                   a.phi, " + 
            "                   a.so_tk, " + 
            "                   a.phi_sau_vat, " + 
            "                   a.id, " + 
            "                   a.msg_id " + 
            "    FROM  sp_bke_phi a, " + 
            "          sp_bke_phi_ctiet b, " + 
            "          sp_dm_manh_shkb c, " + 
            "          sp_dm_ngan_hang d, " + 
            "          sp_dm_htkb e " +                  
            "   WHERE      a.id = b.bk_id " + 
            "          AND a.receive_bank = c.ma_nh " + 
            "          AND a.send_bank = d.ma_nh " + 
            "          and c.shkb = e.ma " +                 
            "          AND d.tinh_trang = 1 " + strWhere +
            " ORDER BY  TO_DATE (den_ngay, 'DD/MM/YYYY') ASC, c.ten DESC, d.ten DESC ";
            
            
// ngay - ngay               "/* Formatted on 4/2/2015 4:53:46 PM (QP5 v5.126) */ " + 
//                "  SELECT  TO_CHAR (b.ngay_tt, 'DD/MM/YYYY') ngay_tt, " +
//                "          a.id, " + "          a.msg_id, " +
//                "          a.loai_tien, " + "          b.somon_trong, " +
//                "          b.phi_trong, " + "          b.somon_ngoai, " +
//                "          b.phi_ngoai, " + "          b.tong " +
//                "    FROM  sp_bke_phi a, sp_bke_phi_ctiet b " +
//                " WHERE a.id = b.bk_id " + strWhere;
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           strValueObjectVO, page, count,
                                           totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getListBKeTinhPhi_PTrang(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }

    public String tongTienBangChu(String tu_ngay, String den_ngay,
                                  String loai_tien) throws Exception {
        try {
            String sql = "SELECT  vietnamesenumbertowords.fnc_doc_tien ( " + 
            "            (SELECT  SUM (b.tong + (b.tong / 10)) " + 
            "               FROM  sp_bke_phi a, sp_bke_phi_ctiet b " + 
            "              WHERE      b.ngay_tt >= TO_DATE (?, 'DD/MM/YYYY') " + 
            "                     AND b.ngay_tt <= TO_DATE (?, 'DD/MM/YYYY') " + 
            "                     AND a.id = b.bk_id), ?) " + 
            "            tien_bang_chu " + 
            "  FROM  DUAL";
            Vector v = new Vector();
            v.add(new Parameter(tu_ngay,true));v.add(new Parameter(den_ngay,true));
            v.add(new Parameter(loai_tien,true));
            ResultSet rs = executeSelectStatement(sql, v, conn);
            rs.next();
            return rs.getString(1);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".tongTienBangChu(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }

    public BKeTinhPhiVO findBKePhi(Vector vParam) throws Exception {
        String sql = "SELECT * FROM sp_bke_phi WHERE ID = ?";
        return (BKeTinhPhiVO)findByPK(sql, vParam, BKeTinhPhiDAO.strValueObjectVO  , conn);
    }
}
