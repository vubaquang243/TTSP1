package com.seatech.ttsp.dmctmt;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class DMCTMTieuDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.dmctmt.DMCTMTieuDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.dmctmt.DMCTMTieuVO";

    public DMCTMTieuDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach DM chuong trinh muc tieu du an
     * @return: Collection
     * */
    public Collection getDMCTMTieuList(String whereClause,
                                       Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.ma, a.ten, a.tinhtrang, a.id, a.id_cha, a.ma_cha, a.loai_khoan, ");
            strSQL.append("a.loai, a.ma_tinh ");
            strSQL.append("FROM sp_dm_ctmtda a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMCTMTieuList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

        return reval;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra thong tin 1 DM CTMT du an
     * @return: DMCTMTieuVO
     * */
    public DMCTMTieuVO getDMCTMTieu(String whereClause,
                                    Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.ma, a.ten, a.tinhtrang, a.id, a.id_cha, a.ma_cha, a.loai_khoan, ");
            strSQL.append("a.loai, a.ma_tinh ");
            strSQL.append("FROM sp_dm_ctmtda a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (DMCTMTieuVO)findByPK(strSQL.toString(), params,
                                         CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMCTMTieu(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }
}
