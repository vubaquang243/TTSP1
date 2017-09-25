package com.seatech.ttsp.tcuudmuc;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class DMSHKBacDAO extends AppDAO {
  Connection conn = null;
  private static String CLASS_NAME_DAO =
      "com.seatech.ttsp.tcuudmuc.TCuuDMucDAO";
  private static String CLASS_NAME_VO =
      "com.seatech.ttsp.tcuudmuc.TCuuDMucVO";

  public DMSHKBacDAO(Connection conn) {
      this.conn = conn;
  }
  public Collection getLstDMuc(String strWhere,
                                   Vector vParam, Integer page,
                                           Integer count,
                                           Integer[] totalCount) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
          strSQL += strWhere + " ORDER BY ma";
        return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                       CLASS_NAME_VO, page, count,
                                       totalCount);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_VO + ".getLstDMuc(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
  }
  public int executeStatement(String strSQL, Vector Param) throws Exception{    
    return executeStatement(strSQL, Param, conn);
  }
}
