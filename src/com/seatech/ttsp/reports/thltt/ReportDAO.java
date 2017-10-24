package com.seatech.ttsp.reports.thltt;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.SelectStatementException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Vector;


public class ReportDAO extends AppDAO {
    boolean bCheck = false;
    private static String key_User = "CreatorReport";

    public ReportDAO() {
        super();
    }
    // Map truyen tham so toi file report

    public HashMap returnMap(String creatorUser) {
        HashMap map = new HashMap();
        map.put(key_User, creatorUser);
        return map;
    }

    //    public ResultSet getData(Connection conn,
    //                             String strWhere) throws SQLException {
    //        ResultSet resultSet = null;
    //        Statement st = null;
    //        StringBuffer sbsql = new StringBuffer();
    //        sbsql.append("select * from sp_ltt ");
    //
    //        try {
    //            if (strWhere != null && !"".equalsIgnoreCase(strWhere)) {
    //                sbsql.append("where id is not null " + strWhere);
    //            }
    //            st =
    // conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    //
    //            resultSet = st.executeQuery(sbsql.toString());
    //        } catch (Exception ex) {
    //            ex.printStackTrace();
    //        }
    //        return resultSet;
    //    }

    public ResultSet getData(Connection conn, Vector param,
                             String strWhere) throws SQLException,
                                                     DatabaseConnectionFailureException,
                                                     SelectStatementException {
        ResultSet resultSet = null;
        String strWhereClause = "select * from sp_ltt where 1=1 ";
        if (strWhere != null && !"".equals(strWhere)) {
            strWhereClause += strWhere;
        }
        //            ps.set
        resultSet = executeSelectStatement(strWhereClause, param, conn);
        return resultSet;
    }
}
