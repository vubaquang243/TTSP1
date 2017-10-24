package com.seatech.ttsp.proxy.giaodien.ttnh;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Vector;


public class TTinNHangDAO extends AppDAO {
    Connection conn = null;
    private String CLASS_NAME_VO =
        "com.seatech.ttsp.proxy.giaodien.ttnh.TTinNHangVO";
    private String CLASS_NAME_DAO =
        "com.seatech.ttsp.proxy.giaodien.ttnh.TTinNHangDAO";
    private static String STRING_EMPTY = "";


    public TTinNHangDAO(Connection conn) {
        this.conn = conn;
    }

    public TTinNHangVO getTTinNHangTrade(String whereClause,
                                         Vector params) throws Exception {


        try {
            StringBuffer strSQL = new StringBuffer();

            strSQL.append("SELECT a.id, a.bank_code, a.original_code, a.original_name, a.app_code, ");
            strSQL.append("a.app_name, a.status ");
            strSQL.append("FROM sp_dm_ttin_nh a ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (TTinNHangVO)findByPK(strSQL.toString(), params,
                                         CLASS_NAME_VO, this.conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getDMKBList(): " +
                                   ex.getMessage(), ex);

        }
    }
}
