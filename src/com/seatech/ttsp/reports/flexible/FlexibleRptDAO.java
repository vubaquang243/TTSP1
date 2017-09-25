package com.seatech.ttsp.reports.flexible;


import com.seatech.framework.datamanager.AppDAO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class FlexibleRptDAO extends AppDAO {
    Connection conn = null;

    public FlexibleRptDAO(Connection conn) {
        this.conn = conn;
    }

    public Collection getFlexibleParamList(String strWhere,
                                           Vector params) throws Exception {
        String strSQL =
            "select a.key_rpt, a.name_rpt, a.pkg_name, a.param1, a.param2, a.param3, " +
            "a.param4, a.param5, a.param6, a.param7, a.param8, a.param9, a.param10, " +
            "a.more from sp_flexible_rpt_param a where 1=1 ";
        strSQL = strSQL + strWhere;
        return executeSelectStatement(strSQL, params,
                                      "com.seatech.ttsp.reports.flexible.FlexibleRptVO",
                                      conn);
    }

    public FlexibleRptVO getFlexibleParam(String strWhere,
                                          Vector params) throws Exception {
        String strSQL =
            "select a.key_rpt, a.name_rpt, a.pkg_name, a.param1, a.param2, a.param3, " +
            "a.param4, a.param5, a.param6, a.param7, a.param8, a.param9, a.param10, " +
            "a.more from sp_flexible_rpt_param a where 1=1 ";
        strSQL = strSQL + strWhere;
        return (FlexibleRptVO)findByPK(strSQL, params,
                                       "com.seatech.ttsp.reports.flexible.FlexibleRptVO",
                                       conn);
    }
}
