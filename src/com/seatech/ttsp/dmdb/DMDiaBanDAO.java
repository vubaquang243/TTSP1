package com.seatech.ttsp.dmdb;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class DMDiaBanDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.dmdb.DMDiaBanDAO";
    private static String CLASS_NAME_VO = "com.seatech.ttsp.dmdb.DMDiaBanVO";

    public DMDiaBanDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach DM dia ban hanh chinh
     * @return: Collection
     * */
    public Collection getDMDiaBanList(String whereClause,
                                      Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.ma, a.macha, a.ten, a.tinhtrang, a.id, a.id_cha, a.loai, ");
            strSQL.append("a.ma_v, a.ma_t, a.ma_h, a.ma_x, a.ma_cu ");
            strSQL.append("FROM sp_dm_dbhc a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMDiaBanList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

        return reval;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra thong tin 1 DM dia ban hanh chinh
     * @return: DMCTMTieuVO
     * */
    public DMDiaBanVO getDMDiaBan(String whereClause,
                                  Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.ma, a.macha, a.ten, a.tinhtrang, a.id, a.id_cha, a.loai, ");
            strSQL.append("a.ma_v, a.ma_t, a.ma_h, a.ma_x, a.ma_cu ");
            strSQL.append("FROM sp_dm_dbhc a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (DMDiaBanVO)findByPK(strSQL.toString(), params,
                                        CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMDiaBan(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }
}

