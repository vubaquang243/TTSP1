package com.seatech.ttsp.DongBoHoa;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

public class DongBoHoaDAO extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO = "com.seatech.ttsp.DongBoHoa.DongBoHoaDAO";
    private String CLASS_NAME_VO = "com.seatech.ttsp.DongBoHoa.DongBoHoaVO";

    public DongBoHoaDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @see: Get list Danh sach cac threads co phan trang
     * */
    public Collection getListDongBo(String strWhere,
                                    Vector vParam) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.user_id, a.thread_name, a.time_start, a.time_stop, ");
            strSQL.append(" a.time_sleeping, a.class_name, a.log_info, a.log_error, ");
            strSQL.append(" a.log_max_size, a.status, a.description, a.update_date,to_char(date_stop,'dd/MM/yyyy hh24:mi:ss') date_stop ");
            strSQL.append(" FROM sp_thread_dongbo a ");

            if (strWhere != null && !"".equals(strWhere)) {
                strSQL.append(" WHERE " + strWhere);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListDongBo(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }

    /**
     * @param: Menh de where va vector param
     * @return: Thong tin 1 Threa
     * @see:
     * */
    public DongBoHoaVO getThread(String whereClause,
                                 Vector params) throws Exception {
        DongBoHoaVO threadVO = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.user_id, a.thread_name, a.time_start, a.time_stop, ");
            strSQL.append(" a.time_sleeping, a.class_name, a.log_info, a.log_error, ");
            strSQL.append(" a.log_max_size, a.status, a.description, a.update_date ");
            strSQL.append(" FROM sp_thread_dongbo a ");

            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            threadVO =(DongBoHoaVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getUser(): " +
                                   ex.getMessage(), ex);
        }
        return threadVO;
    }

    /**
     * @param: UserVO
     * @return: 0: ko thanh cong; 1: thanh cong
     * @see:
     * */
    public int update(DongBoHoaVO vo) throws Exception {

        Vector v_param = new Vector();
        StringBuffer sqlBuff = new StringBuffer();
        int nExc = 0;
        try {
            sqlBuff.append("update sp_thread_dongbo set ");
            if (vo.getUser_id() != null) {
                sqlBuff.append(" USER_ID=?,");
                v_param.add(new Parameter(vo.getUser_id(), true));
            }
            if (vo.getThread_name() != null) {
                sqlBuff.append(" THREAD_NAME=?,");
                v_param.add(new Parameter(vo.getThread_name(), true));
            }
            if (vo.getTime_start() != null) {
                sqlBuff.append(" TIME_START=?,");
                v_param.add(new Parameter(vo.getTime_start(), true));
            }
            if (vo.getTime_stop() != null) {
                sqlBuff.append(" TIME_STOP=?,");
                v_param.add(new Parameter(vo.getTime_stop(), true));
            }
            if (vo.getTime_sleeping() != null) {
                sqlBuff.append(" TIME_SLEEPING=?,");
                v_param.add(new Parameter(vo.getTime_sleeping(), true));
            }
            if (vo.getClass_name() != null) {
                sqlBuff.append(" CLASS_NAME=?,");
                v_param.add(new Parameter(vo.getClass_name(), true));
            }
            if (vo.getLog_info() != null) {
                sqlBuff.append(" LOG_INFO=?,");
                v_param.add(new Parameter(vo.getLog_info(), true));
            }
            if (vo.getLog_error() != null) {
                sqlBuff.append(" LOG_ERROR=?,");
                v_param.add(new Parameter(vo.getLog_error(), true));
            }
//            if (vo.getLog_max_size() != null) {
//                sqlBuff.append(" LOG_MAX_SIZE=?,");
//                v_param.add(new Parameter(vo.getLog_max_size(), true));
//            }
            if (vo.getStatus() != null) {
                sqlBuff.append(" STATUS=?,");
                v_param.add(new Parameter(vo.getStatus(), true));
            }
            if (vo.getDescription() != null) {
                sqlBuff.append(" DESCRIPTION=?,");
                v_param.add(new Parameter(vo.getDescription(), true));
            }
            if (vo.getUpdate_date() != null) {
                sqlBuff.append(" UPDATE_DATE=?,");
                v_param.add(new DateParameter(vo.getUpdate_date(), true));
            }
            sqlBuff = sqlBuff.deleteCharAt(sqlBuff.length() - 1);
            sqlBuff.append(" WHERE ID=?");
            v_param.add(new Parameter(vo.getId(), true));
            nExc = executeStatement(sqlBuff.toString(), v_param, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".update(): " +ex.getMessage(), ex);
        }
        return nExc;

    }
}
