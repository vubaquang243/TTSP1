package com.seatech.ttsp.thamchieu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class MaThamChieuDAO extends AppDAO {
    Connection conn = null;
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.thamchieu.MaThamChieuDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.thamchieu.MaThamChieuVO";


    public MaThamChieuDAO(Connection conn) {
        this.conn = conn;
    }
  /**
   * @param: Menh de where; vector tham so
   * @see: Lay ra danh sach cac Ma tham chieu cua LTT den
   * @return: Collection
   * */
  public Collection getMaThamChieuLTTDenList(String whereClause,
                                       Vector params) throws Exception {
      Collection reval = null;
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT a.rv_domain, a.rv_low_value, a.rv_high_value, ");
          strSQL.append("a.rv_abbreviation, a.rv_meaning FROM SP_DM_MA_THAMCHIEU a");

          if (whereClause != null && !whereClause.equals("")) {
              strSQL.append(" WHERE " + whereClause);
          }
          strSQL.append(" ORDER BY a.rv_low_value DESC ");
          reval =
                  executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                         conn);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getMaThamChieuList(): " +
                                 ex.getMessage(), ex);
      }

      return reval;
  }
    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach cac Ma tham chieu
     * @return: Collection
     * */
    public Collection getMaThamChieuList(String whereClause,
                                         Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.rv_domain, a.rv_low_value, a.rv_high_value, ");
            strSQL.append("a.rv_abbreviation, a.rv_meaning FROM SP_DM_MA_THAMCHIEU a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY a.rv_low_value DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getMaThamChieuList(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra 1 ban ghi Ma tham chieu
     * @return: MaThamChieuVO
     * */
    public MaThamChieuVO getMaThamChieuByKey(String whereClause,
                                            Vector params) throws Exception {
        MaThamChieuVO matcVO = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.rv_domain, a.rv_low_value, a.rv_high_value, ");
            strSQL.append("a.rv_abbreviation, a.rv_meaning FROM SP_DM_MA_THAMCHIEU a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            matcVO =
                    (MaThamChieuVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                            conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getMaThamChieuByKey(): " +
                                   ex.getMessage(), ex);
        }

        return matcVO;
    }
}
