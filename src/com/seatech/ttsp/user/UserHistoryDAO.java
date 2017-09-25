package com.seatech.ttsp.user;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;


public class UserHistoryDAO extends AppDAO {
    Connection conn = null;
    private String CLASS_NAME_VO = "com.seatech.ttsp.user.UserHistoryVO";
    private String CLASS_NAME_DAO = "com.seatech.ttsp.user.UserHistoryDAO";

    public UserHistoryDAO(Connection conn) {
        this.conn = conn;
    }

    public int insert(UserHistoryVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        //Lay seq
        Long lID = getSeqNextValue("sp_nsd_lsu_seq", conn);

        strSQL.append("insert into sp_nsd_lsu (id ");
        strSQL2.append(") values (? ");
        v_param.add(new Parameter(lID, true));

        if (vo.getNsd_id() != null) {
            strSQL.append(", nsd_id");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getNsd_id(), true));
        }

        strSQL.append(", ngay_tdoi");
        strSQL2.append(", ?");
        v_param.add(new DateParameter(new Date(), true));
       
        if (vo.getNoi_dung_thaydoi() != null) {
            strSQL.append(", noi_dung_thaydoi");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getNoi_dung_thaydoi(), true));
        }
        if (vo.getNguoi_tdoi() != null) {
            strSQL.append(", nguoi_tdoi");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getNguoi_tdoi(), true));
        }
        strSQL.append(strSQL2);
        strSQL.append(")");
        return executeStatement(strSQL.toString(), v_param, conn);
    }

    /**
     * @param: Menh de where va vector param
     * @return: Danh sach LSU NSD co phan trang
     * @see:
     * */
    public Collection getLsuUserList(String whereClause, Vector vParam,
                                     Integer page, Integer count,
                                     Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.nsd_id, a.ngay_tdoi, a.noi_dung_thaydoi, a.nguoi_tdoi, b2.ten_nsd ten_ng_tdoi,b.ma_nsd, b.ten_nsd,b.chuc_danh  ");
            strSQL.append("FROM sp_nsd_lsu a  , sp_nsd b,  sp_nsd b2");
            strSQL.append(" WHERE b2.id = a.nguoi_tdoi and b.id = a.nsd_id ");
            if (whereClause != null && !"".equalsIgnoreCase(whereClause)) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getLsuUserList(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }
  public ResultSet getLsuUserList(String whereClause, Vector vParam) throws Exception {
      ResultSet reval = null;
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT a.id, a.nsd_id,to_char(a.ngay_tdoi,'DD/MM/YYYY HH24:MI:SS') ngay_tdoi, a.noi_dung_thaydoi, a.nguoi_tdoi, b2.ten_nsd ten_ng_tdoi,b.ma_nsd, b.ten_nsd,b.chuc_danh  ");
          strSQL.append("FROM sp_nsd_lsu a  , sp_nsd b,  sp_nsd b2");
          strSQL.append(" WHERE b2.id = a.nguoi_tdoi and b.id = a.nsd_id ");
          if (whereClause != null && !"".equalsIgnoreCase(whereClause)) {
              strSQL.append(whereClause);
          }
          strSQL.append(" ORDER BY a.id DESC ");
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, conn);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getLsuUserList(): " +
                                 ex.getMessage(), ex);
      }

      return reval;
  }
}
