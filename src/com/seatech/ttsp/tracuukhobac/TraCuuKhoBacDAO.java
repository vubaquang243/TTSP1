package com.seatech.ttsp.tracuukhobac;

import com.seatech.framework.datamanager.AppDAO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

public class TraCuuKhoBacDAO extends AppDAO{
    Connection conn;
  private String CLASS_NAME_VO =
      "com.seatech.ttsp.tracuukhobac.TraCuuKhoBacVO";
    
    public TraCuuKhoBacDAO(Connection conn){
        this.conn = conn;
    }
    
    public Collection getListKhoBac(String strWhere, Vector vParams) throws Exception{
        try{
            String strQuery = "Select ma, ten from sp_dm_htkb where 1=1 ";
            strQuery += strWhere;
            strQuery += " Order by ma asc";
            return executeSelectStatement(strQuery, vParams, CLASS_NAME_VO, conn);
        }catch(Exception e){
            throw e;
        }
    }
    
  public Collection getDMKHTinh(String strWhere, Vector vParams) throws Exception{
      try{
          String strQuery = "Select id_cha, ten from sp_dm_htkb where 1=1 ";
          strQuery += strWhere;
          strQuery += " Order by id asc";
          return executeSelectStatement(strQuery, vParams, CLASS_NAME_VO, conn);
      }catch(Exception e){
          throw e;
      }
  }
  
  public Collection getDMKHHuyen(String strWhere, Vector vParams) throws Exception{
      try{
          String strQuery = "Select id, ten from sp_dm_htkb where 1=1 ";
          strQuery += strWhere;
          strQuery += " Order by id asc";
          return executeSelectStatement(strQuery, vParams, CLASS_NAME_VO, conn);
      }catch(Exception e){
          throw e;
      }
  }
}
