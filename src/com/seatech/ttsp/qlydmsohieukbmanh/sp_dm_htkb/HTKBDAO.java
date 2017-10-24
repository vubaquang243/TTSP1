package com.seatech.ttsp.qlydmsohieukbmanh.sp_dm_htkb;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.ttsp.qlydmsohieukbmanh.dmsohieukbmadvsdns.DMSoHieuKBMaDVSDNSVO;

import java.sql.Connection;

import java.util.Vector;

public class HTKBDAO extends AppDAO {

    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.qlydmsohieukbmanh.sp_dm_htkb.HTKBVO";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.qlydmsohieukbmanh.sp_dm_htkb.HTKBDAO";
    private transient Connection conn = null;

    public HTKBDAO(Connection conn) {
        this.conn = conn;
    }
    
  public int update(HTKBVO vo) {
      int result = 0;
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      try {
          
          strSQL.append("update sp_dm_htkb set ");
          if (vo.getMa() != null) {
              if (vo.getCap() != null) {
                  strSQL.append(" cap = ? ,");
                  v_param.add(new Parameter(vo.getCap(), true));
              }
              if (vo.getId_cha() != null) {
                  strSQL.append(" id_cha = ? ,");
                  v_param.add(new Parameter(vo.getId_cha(), true));
              }
              if (vo.getMa_h() != null) {
                  strSQL.append(" ma_h = ? ,");
                  v_param.add(new Parameter(vo.getMa_h(), true));
              }
              if (vo.getTen() != null) {
                  strSQL.append(" ten = ? ,");
                  v_param.add(new Parameter(vo.getTen(), true));
              }
              if (vo.getMa_t() != null) {
                  strSQL.append(" ma_t = ? ");
                  v_param.add(new Parameter(vo.getMa_t(), true));
              }
              strSQL.append(" where ma = ? ");
              v_param.add(new Parameter(vo.getMa(), true));
              result = executeStatement(strSQL.toString(), v_param, conn);
          }
          return result;
      } catch (Exception e) {
          DAOException daoEx =  new DAOException(CLASS_NAME_DAO + ".update(htkb vo): " + e.getMessage(), e);
          return -1;
      }
  }

    public int updateCapIDChaHTKB(String string) {
        int result = -1;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try{
            strSQL.append("UPDATE " + 
            "  (SELECT t1.ma,t1.cap cap1,t1.id_cha id_cha1,t2.ma,t2.cap cap2,t2.id_cha id_cha2 " + 
            "  FROM sp_dm_htkb t1, " + 
            "      (select c.ma," + 
            "        decode(substr(c.ma,3,2),'10','5','60','5','61','2','11','2','3') cap, " + 
            "        (select id from sp_dm_htkb where ma = c.ma_cha) id_cha " + 
            "        from sp_dm_htkb c where c.ma = ?) t2 " + 
            "  WHERE t1.ma = t2.ma)" + 
            "SET cap1 = cap2," + 
            "    id_cha1 = id_cha2");
            v_param.add(new Parameter(string,true));
            result = executeStatement(strSQL.toString(), v_param, conn);
            return result;
        }catch(Exception e){
          DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".updateCapIDChaHTKB(String string): " + e.getMessage(), e);
          return -1;
        }
    }
}
