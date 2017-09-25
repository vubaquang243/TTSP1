package com.seatech.ttsp.dmndkt;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class DMNDKTeDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.dmndkt.DMNDKTeDAO";
    private static String CLASS_NAME_VO = "com.seatech.ttsp.dmndkt.DMNDKTeVO";

    public DMNDKTeDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach DM Noi dung kinh te
     * @return: Collection
     * */
    public Collection getDMNDKTeList(String whereClause,
                                     Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ma, a.ten, a.id_cha, a.ma_cha, a.id_nhom, a.ma_nhom, ");
            strSQL.append("a.id_tnhom, a.ma_tnhom, a.tinh_trang, a.ma_muc, a.ma_tmuc ");
            strSQL.append("FROM sp_dm_ndkt a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMNDKTeList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

        return reval;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra thong tin 1 DM Noi dung kinh te
     * @return: DMCTMTieuVO
     * */
    public DMNDKTeVO getDMNDKTe(String whereClause,
                                Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ma, a.ten, a.id_cha, a.ma_cha, a.id_nhom, a.ma_nhom, ");
            strSQL.append("a.id_tnhom, a.ma_tnhom, a.tinh_trang, a.ma_muc, a.ma_tmuc ");
            strSQL.append("FROM sp_dm_ndkt a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (DMNDKTeVO)findByPK(strSQL.toString(), params,
                                       CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMNDKTe(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }
}
