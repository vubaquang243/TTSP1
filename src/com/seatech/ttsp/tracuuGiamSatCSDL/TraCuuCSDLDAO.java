package com.seatech.ttsp.tracuuGiamSatCSDL;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class TraCuuCSDLDAO extends AppDAO {
    private String CLASS_NAME_VO =
        "com.seatech.ttsp.tracuuGiamSatCSDL.TraCuuCSDLVO";
    private String CLASS_NAME_DAO =
        "com.seatech.ttsp.tracuuGiamSatCSDL.TraCuuCSDLDAO";
    private Connection conn = null;

    public TraCuuCSDLDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where va vector param
     * @return: list CSDL
     * @see:
     * */
    public Collection getGiamSatCSDL(String whereClause,
                                     Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {

            strSQL.append("select (CASE WHEN (a.db_user || a.os_user || a.userhost || a.econtext_id) = 'TTSP_DEVKratosMANHNV-PC192.168.1.157'");
            strSQL.append("THEN  'TTSP'");
            strSQL.append("ELSE");
            strSQL.append("'KHAC'");
            strSQL.append("  END)loai_truy_cap,a.session_id, a.timestamp , a.db_user, a.os_user, a.userhost,");
            strSQL.append(" a.client_id, a.econtext_id, a.ext_name, a.object_schema, a.object_name, a.policy_name, a.scn, a.sql_text");
            strSQL.append(" ,a.sql_bind, a.comment$text, a.statement_type, TO_CHAR(a.extended_timestamp,'dd/MM/yyyy HH24:mi:ss') extended_time,");
            strSQL.append(" a.proxy_sessionid, a.global_uid, a.instance_number, a.os_process, a.transactionid");
            strSQL.append(", a.statementid, a.entryid");
            strSQL.append(" from dba_fga_audit_trail a");
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE" + whereClause);
            }
            strSQL.append(" ORDER BY session_id DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getGiamSatCSDL(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    /**
     * @param: Menh de where va vector param
     * @return:  list CSDL co phan trang
     * @see:
     * */
    public Collection getGiamSatCSDL_ptrang(String whereClause, Vector vParam,
                                            Integer page, Integer count,
                                            Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();


        try {
            String loai_truy_cap = AppConstants.PARAM_LOAI_TRUY_CAP;
            strSQL.append("select  a.session_id, to_char(a.timestamp,'DD/MM/YYYY HH24:mi:ss') timestamp  , a.db_user, a.os_user, a.userhost,");
            strSQL.append(" a.client_id, a.econtext_id, a.ext_name, b.ma_nsd,a.object_schema, a.object_name, a.policy_name, a.scn, a.sql_text");
            strSQL.append(" ,a.sql_bind, a.comment$text, a.statement_type"); 
            strSQL.append(" from dba_fga_audit_trail a");
            strSQL.append(" LEFT JOIN sp_nsd b ");
            strSQL.append(" on a.client_id =  b.id  WHERE  1=1 " + whereClause);
            
            strSQL.append(" ORDER BY  timestamp  DESC ");
             reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getGiamSatCSDL_ptrang(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    /**
     * @param: Menh de where va vector param
     * @return: thong tin CSDL
     * @see:
     * */
    public TraCuuCSDLVO getCSDL(String whereClause,
                                Vector params) throws Exception {
        TraCuuCSDLVO vo = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("select ");
            strSQL.append("a.session_id, a.timestamp , a.db_user, a.os_user, a.userhost,");
            strSQL.append(" a.client_id, a.econtext_id, a.ext_name, a.object_schema, a.object_name, a.policy_name, a.scn, a.sql_text");
            strSQL.append(" ,a.sql_bind, a.comment$text, a.statement_type, TO_CHAR(a.extended_timestamp,'dd/MM/yyyy HH24:mi:ss') extended_time,");
            strSQL.append(" a.proxy_sessionid, a.global_uid, a.instance_number, a.os_process, a.transactionid");
            strSQL.append(", a.statementid, a.entryid");
            strSQL.append(" from dba_fga_audit_trail a");

            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            vo =
 (TraCuuCSDLVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getUser(): " +
                                   ex.getMessage(), ex);
        }
        return vo;
    }

}
