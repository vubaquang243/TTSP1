package com.seatech.ttsp.qlydmsohieukbmanh;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.ExecuteStatementException;
import com.seatech.framework.exception.SelectStatementException;

import com.seatech.ttsp.dmchuong.DMChuongVO;
import com.seatech.ttsp.dmkb.DMKBacVO;

import com.seatech.ttsp.qlydmsohieukbmanh.dmsohieukbmadvsdns.DMSoHieuKBMaDVSDNSDAO;
import com.seatech.ttsp.qlydmsohieukbmanh.dmsohieukbmadvsdns.DMSoHieuKBMaDVSDNSVO;

import com.seatech.ttsp.qlydmsohieukbmanh.sp_dm_htkb.HTKBDAO;
import com.seatech.ttsp.qlydmsohieukbmanh.sp_dm_htkb.HTKBVO;

import java.sql.Connection;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class DMSoHieuKBMaNHDAO extends AppDAO {

    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.qlydmsohieukbmanh.DMSoHieuKBMaNHVO";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.qlydmsohieukbmanh.DMSoHieuKBMaNHDAO";
    private transient Connection conn = null;

    public DMSoHieuKBMaNHDAO(Connection conn) {
        this.conn = conn;
    }

    public Collection getDMSoHieuKBMaNH_phantrang(String strWhere,
                                                  Vector v_param,
                                                  int currentPage,
                                                  int numberRowOnPage,
                                                  Integer[] totalCount) throws Exception {
        try{
        String query =
            " SELECT DISTINCT a.shkb,a.ma_nh,a.ten,b.ma_dvsdns,c.Ma_T,c.Ma_H," + 
            "c.cap , c.id_cha " +
           " FROM sp_dm_manh_shkb a " +
           " FULL OUTER JOIN sp_dm_shkb_dvsdns b " +
           " ON a.shkb=b.ma_kb " +
           " JOIN sp_dm_htkb c " +
           " ON c.ma = a.shkb " +
           " JOIN sp_tknh_kb d " +
           " ON c.id = d.kb_id " +
           " WHERE 1=1 " + strWhere + "ORDER BY a.shkb";
            
        return executeSelectWithPaging(conn, query.toString(), v_param,
                                       CLASS_NAME_VO, currentPage, numberRowOnPage,
                                       totalCount);
        }catch(Exception e){
          throw e;
        }
    }

    public Collection getKBTinh() throws Exception {
        Collection result = null;
        String query =
            "SELECT DISTINCT a.id_cha, c.ten FROM sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c " +
            "WHERE 1 = 1 AND a.id = b.kb_id AND a.id_cha = c.id " +
            "order by ltrim(REPLACE(c.ten,'KBNN',''))";
        try {
            result =
                    executeSelectStatement(query, null, "com.seatech.ttsp.dmkb.DMKBacVO",
                                           conn);
        } catch (Exception e) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_VO + e.getMessage(), e);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return result;
    }

    public DMSoHieuKBMaNHVO getDMSoHieuKBMaNH(String strWhere, Vector vParam) throws DAOException {
      DMSoHieuKBMaNHVO vo = null;
      StringBuffer strSQL = new StringBuffer();

      try {
          strSQL.append("SELECT MA_NH,SHKB,TEN ");
          strSQL.append("FROM sp_dm_manh_shkb ");
          if (strWhere != null & !strWhere.isEmpty()) {
              strSQL.append(" WHERE " + strWhere);
          }
          vo = (DMSoHieuKBMaNHVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO,
                                       conn);

      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_DAO + ".getTKKT(strCode): " +
                               ex.getMessage());
          daoEx.printStackTrace();
          throw daoEx;
      }
      
      return vo;
    }

  public int insert(DMSoHieuKBMaNHVO vo) throws DatabaseConnectionFailureException,
                                          ExecuteStatementException,
                                          SQLException {
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      StringBuffer strSQL2 = new StringBuffer();
      //Lay id tu seq
      strSQL.append("INSERT INTO sp_dm_manh_shkb (SHKB ");
      strSQL2.append(") values (? ");
      v_param.add(new Parameter(vo.getShkb(), true));
      if (vo.getMa_nh() != null) {
          strSQL.append(", MA_NH");
          strSQL2.append(", ?");
          v_param.add(new Parameter(vo.getMa_nh(), true));
      }
      if (vo.getTen() != null) {
          strSQL.append(", ten");
          strSQL2.append(", ?");
          v_param.add(new Parameter(vo.getTen(), true));
      }
      
      strSQL.append(strSQL2);
      strSQL.append(")");
      return executeStatement(strSQL.toString(), v_param, conn);
  }

    public int update(DMSoHieuKBMaNHVO vo) {
      try {
          Vector v_param = new Vector();
          StringBuffer strSQL = new StringBuffer();
          strSQL.append("update sp_dm_manh_shkb set ");
          int nExc = 0;

          if (vo.getShkb() != null) {
              if (vo.getTen() != null && !"".equals(vo.getTen())) {
                  strSQL.append(" ten = ? , ");
                  v_param.add(new Parameter(vo.getTen(), true));
              }
              if (vo.getMa_nh() != null && !"".equals(vo.getMa_nh())) {
                  strSQL.append(" ma_nh = ? ");
                  v_param.add(new Parameter(vo.getMa_nh(), true));
              }
              if (vo.getMa_dvsdns() != null) {
                  DMSoHieuKBMaDVSDNSVO dvsdnsVO = new DMSoHieuKBMaDVSDNSVO();
                  DMSoHieuKBMaDVSDNSDAO dvsdnsDAO = new DMSoHieuKBMaDVSDNSDAO(conn);
                  dvsdnsVO.setMa_kb(vo.getShkb());
                  dvsdnsVO.setMa_dvsdns(vo.getMa_dvsdns());
                  
                  if(!"".equals(vo.getMa_dvsdns())){
                    String strWhere = " MA_KB = ? AND ROWNUM = 1";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(dvsdnsVO.getMa_kb(), true));
                    DMSoHieuKBMaDVSDNSVO tempVO = dvsdnsDAO.getDMSoHieuKBMaDVSDNS(strWhere, vParam);
                    if(tempVO == null){
                      nExc = dvsdnsDAO.insert(dvsdnsVO);
                    }else{
                      nExc = dvsdnsDAO.update(dvsdnsVO);
                    }
                  }else{
                    nExc = dvsdnsDAO.update(dvsdnsVO);
                  }
                  if(nExc <= 0){
                    throw new Exception("Sua MaDVSDNS khong thanh cong");
                  }
                  nExc = 0;
              }
              
              if(true){
                  HTKBVO kbVO = new HTKBVO();
                  HTKBDAO kbdao = new HTKBDAO(conn);
                  kbVO.setMa(vo.getShkb());
                  kbVO.setMa_t(vo.getMa_t());
                  kbVO.setMa_h(vo.getMa_h());
                  kbVO.setCap(vo.getCap());
                  kbVO.setId_cha(vo.getId_cha());
                  kbVO.setTen(vo.getTen());
                  nExc = kbdao.update(kbVO);
                  if(nExc<=0){
                      throw new Exception("Sua HTKB khong thanh cong");
                  }
                  nExc = 0;
                  
                  nExc = kbdao.updateCapIDChaHTKB(vo.getShkb());
                  if(nExc<=0){
                      throw new Exception("updateCapIDChaHTKB khong thanh cong");
                  }
                  nExc = 0;
              }
              
              strSQL.append(" where shkb = ? ");
              v_param.add(new Parameter(vo.getShkb(), true));
              nExc = executeStatement(strSQL.toString(), v_param, conn);
          }

          return nExc;
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_DAO + ".update(DMSoHieuKBMaNHVO vo): " +
                               ex.getMessage(), ex);
          return -1;
      }
    }
}
