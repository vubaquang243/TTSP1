package com.seatech.ttsp.error;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;


public class AppErrorDAO extends AppDAO{
    public AppErrorDAO() {     
    }
  private String CLASS_NAME_VO = "com.seatech.ttsp.error.AppErrorVO";
  private String CLASS_NAME_DAO = "com.seatech.ttsp.error.AppErrorDAO";
  public Collection getAppErrorList(Connection conn) throws Exception {
      Collection reval = null;
      StringBuffer sql = new StringBuffer();
      try {
          sql.append(" SELECT a.error_id, a.error_contents ");
          sql.append(" FROM SP_APP_ERROR a ");
          reval = executeSelectStatement(sql.toString(), null, CLASS_NAME_VO, conn);                  
          
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getUserList(): " +
                                 ex.getMessage(), ex);
      }
      return reval;
  }
}
