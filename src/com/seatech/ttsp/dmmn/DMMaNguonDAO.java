package com.seatech.ttsp.dmmn;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class DMMaNguonDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.dmmn.DMMaNguonDAO";
    private static String CLASS_NAME_VO = "com.seatech.ttsp.dmmn.DMMaNguonVO";

    public DMMaNguonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach DM ma nguon
     * @return: Collection
     * */
    public Collection getDMMaNguonList(String whereClause,
                                       Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.ma, a.ten, a.tinhtrang, a.id, a.id_cha, a.ma_cha, a.loai");
            strSQL.append("FROM sp_dm_nguonchi a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMMaNguonList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

        return reval;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra thong tin 1 ma du phong
     * @return: DMDuPhongVO
     * */
    public DMMaNguonVO getDMMaNguon(String whereClause,
                                    Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.ma, a.ten, a.tinhtrang, a.id, a.id_cha, a.ma_cha, a.loai ");
            strSQL.append(" FROM sp_dm_nguonchi a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (DMMaNguonVO)findByPK(strSQL.toString(), params,
                                         CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMMaNguon(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }
}
