package com.seatech.ttsp.dmthamsohachtoan;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Vector;


public class DmTSoHToanDAO extends AppDAO {
    Connection conn = null;
    String CLASS_NAME_HTKBVO =
        "com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanVO";
    String CLASS_NAME_DAO = "com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanDAO";

    public DmTSoHToanDAO(Connection conn) {
        this.conn = conn;
    }

    public DmTSoHToanVO getDmTSoHToan(String whereClause,
                                      Vector params) throws Exception {
        DmTSoHToanVO vo = null;
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT a.id, a.ma_nh, a.loai_htoan, a.mo_ta, a.tktn, a.quy, a.dvsdns, ");
            strSQL.append("       a.cap_ns, a.chuong, a.nganh_kt, a.ndkt, a.dbhc, a.ctmt, a.nguon, ");
            strSQL.append("       a.ma_kb, a.du_phong, a.ghi_chu ");
            strSQL.append("  FROM sp_dm_tso_htoan a Where (1=1) ");
            strSQL.append(whereClause);
            vo =
 (DmTSoHToanVO)findByPK(strSQL.toString(), params, CLASS_NAME_HTKBVO, conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDmTSoHToan(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
        return vo;
    }
}
