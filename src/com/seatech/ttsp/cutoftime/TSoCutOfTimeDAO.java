package com.seatech.ttsp.cutoftime;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.ExecuteStatementException;
import com.seatech.framework.exception.SelectStatementException;
import com.seatech.framework.utils.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;
/**
 * @author: thuongdt
 * @date: 20/03/2017
 * @see: them moi ham getTSoCTONormal de tim thoi COT pho bien nhat cua he thong trong ngay
 * @find: thuongdt-20170320
 * */
public class TSoCutOfTimeDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.cutoftime.TSoCutOfTimeDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.cutoftime.TSoCutOfTimeVO";

    public TSoCutOfTimeDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @code hungpd
     * @param whereClause
     * @param params
     * @return
     * @throws Exception
     */
    public Collection getTSoCOTList(String whereClause,
                                    Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
           
            strSQL.append(" SELECT a.id, a.ma_nh_kb, a.ma_nh, to_char(a.ngay_gd,'dd/MM/yyyy')  ");
            strSQL.append(" as ngay_gd, a.cut_of_time, b.ten as ten_nh_kb,c.ten as ten_nh, ");
            strSQL.append(" a.insert_date, a.ghi_chu, a.so_yc_cot ");
            strSQL.append(" FROM sp_tso_cutoftime a inner join sp_dm_manh_shkb b on a.ma_nh_kb=b.ma_nh ");
            strSQL.append(" inner join sp_dm_ngan_hang  c on a.ma_nh=c.ma_nh ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY a.ngay_gd desc");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTSoCOTList(): " +
                                   ex.getMessage(), ex);

        }

        return reval;
    }

    /**
     * @code hungpd
     * @param whereClause
     * @param params
     * @return
     * @throws Exception
     */
    public Collection getTSoCOTList_ptrang(String whereClause,
                                           Vector parameters, Integer page,
                                           Integer count,
                                           Integer[] totalCount) throws DAOException {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.ma_nh_kb, a.ma_nh, to_char(a.ngay_gd,'dd/MM/yyyy') as ngay_gd, a.cut_of_time,b.ten as ten_nh_kb,c.ten as ten_nh, ");
            strSQL.append(" a.insert_date, a.ghi_chu, a.so_yc_cot ");
            strSQL.append(" FROM sp_tso_cutoftime a inner join sp_dm_manh_shkb b on a.ma_nh_kb=b.ma_nh ");
            strSQL.append(" inner join sp_dm_ngan_hang  c on a.ma_nh=c.ma_nh ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.ngay_gd desc");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), parameters,
                                            CLASS_NAME_VO, page, count,
                                            totalCount);

        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getTSoCOTList_ptrang(): " +
                                   ex.getMessage(), ex);


        }

        return reval;
    }

    /**
     *
     * @param whereClause
     * @param params
     * @return
     * @throws Exception
     */
    public TSoCutOfTimeVO getTSoCTO(String whereClause,
                                    Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.ma_nh_kb, a.ma_nh, to_char(a.ngay_gd,'dd/MM/yyyy') as ngay_gd, a.cut_of_time, b.ten as ten_nh_kb,c.ten as ten_nh, ");
            strSQL.append(" a.insert_date, a.ghi_chu, a.so_yc_cot ");
            strSQL.append(" FROM sp_tso_cutoftime a inner join sp_dm_manh_shkb b on a.ma_nh_kb=b.ma_nh ");
            strSQL.append(" inner join sp_dm_ngan_hang  c on a.ma_nh=c.ma_nh ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (TSoCutOfTimeVO)findByPK(strSQL.toString(), params,
                                            CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTSoCTO(): " +
                                   ex.getMessage(), ex);

        }
    }

    public Date getMinCOT(String whereClause, Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            Date dateReturn = null;
            strSQL.append(" select min(to_date(to_char(ngay_gd,'dd/mm/yyyy') || cut_of_time,'dd/mm/yyyy hh24:mi')) cot from sp_tso_cutoftime where 1=1 ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(whereClause);
            }
            ResultSet rs = executeSelectStatement(strSQL.toString(), params, conn);
            while(rs.next()){
              dateReturn = rs.getTimestamp("cot");
            }
            return dateReturn;
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTSoCTO(): " +
                                   ex.getMessage(), ex);

        }
    }
    
  /**
   *
   * @param whereClause
   * @param params
   * @return
   * @throws Exception
   */
  public TSoCutOfTimeVO getMinTSoCTO(String whereClause,
                                     Vector params) throws Exception {
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("  SELECT MIN(CUT_OF_TIME) as CUT_OF_TIME " + 
          " FROM SP_TSO_CUTOFTIME " + 
          " WHERE NGAY_GD=trunc(sysdate) ");
          if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
              strSQL.append(whereClause);
          }
          return (TSoCutOfTimeVO)findByPK(strSQL.toString(), params,
                                          CLASS_NAME_VO, conn);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getTSoCTO(): " +
                                 ex.getMessage(), ex);

      }
  }
  
  public Date getMaxCOT(String whereClause, Vector params) throws Exception {
      StringBuffer strSQL = new StringBuffer();
      try {
          Date dateReturn = null;
          strSQL.append(" select max(to_date(to_char(ngay_gd,'dd/mm/yyyy') || cut_of_time,'dd/mm/yyyy hh24:mi')) cot from sp_tso_cutoftime where 1=1 ");
          if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
              strSQL.append(whereClause);
          }
          ResultSet rs = executeSelectStatement(strSQL.toString(), params, conn);
          while(rs.next()){
            dateReturn = rs.getTimestamp("cot");
          }
          return dateReturn;
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getMaxCOT(): " + ex.getMessage(), ex);
      }
  }
  
  public TSoCutOfTimeVO getMAXTSoCOT(String whereClause,
                                     Vector params) throws Exception {
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("  SELECT MAX(CUT_OF_TIME) as CUT_OF_TIME " + 
          " FROM SP_TSO_CUTOFTIME " + 
          " WHERE 1=1 ");
          if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
              strSQL.append(whereClause);
          }
          return (TSoCutOfTimeVO)findByPK(strSQL.toString(), params,
                                          CLASS_NAME_VO, conn);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getTSoCTO(): " +
                                 ex.getMessage(), ex);

      }
  }

    public int insert(TSoCutOfTimeVO vo) throws DatabaseConnectionFailureException,
                                                ExecuteStatementException {
        Vector v_param = new Vector();
        StringBuffer sqlBuff = new StringBuffer();
        StringBuffer sqlBuff2 = new StringBuffer();
        sqlBuff.append("insert into sp_tso_cutoftime (");
        sqlBuff2.append(" values (sp_tso_cutoftime_seq.nextval");
        sqlBuff.append(" id");
        if (vo.getMa_nh_kb() != null) {
            sqlBuff.append(" ,MA_NH_KB");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getMa_nh_kb(), true));
        }
        if (vo.getMa_nh() != null) {
            sqlBuff.append(" ,MA_NH");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getMa_nh(), true));
        }
        if (vo.getNgay_gd() != null) {
            sqlBuff.append(" ,NGAY_GD");
            sqlBuff2.append(" ,?");
            v_param.add(new DateParameter(vo.getNgay_gd(), true));
        }
        if (vo.getCut_of_time() != null) {
            sqlBuff.append(" ,CUT_OF_TIME");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getCut_of_time(), true));
        }
        if (vo.getInsert_date() != null) {
            sqlBuff.append(" ,INSERT_DATE");
            sqlBuff2.append(" ,?");
            v_param.add(new DateParameter(vo.getInsert_date(), true));
        }
        if (vo.getGhi_chu() != null) {
            sqlBuff.append(" ,GHI_CHU");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getGhi_chu(), true));
        }
        if (vo.getSo_yc_cot() != null) {
            sqlBuff.append(" ,SO_YC_COT");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getSo_yc_cot(), true));
        }
        sqlBuff.append(")");
        sqlBuff.append(sqlBuff2);
        sqlBuff.append(")");
        return executeStatement(sqlBuff.toString(), v_param, conn);

    }

    public int update(TSoCutOfTimeVO vo) throws DatabaseConnectionFailureException,
                                                ExecuteStatementException {
        Vector v_param = new Vector();
        StringBuffer sqlBuff = new StringBuffer();
        sqlBuff.append(" Update sp_tso_cutoftime Set ");
        int nExc = 0;
        if (vo.getMa_nh_kb() != null) {
            sqlBuff.append(" MA_NH_KB=?,");
            v_param.add(new Parameter(vo.getMa_nh_kb(), true));
        }
        if (vo.getMa_nh() != null) {
            sqlBuff.append(" MA_NH=?,");
            v_param.add(new Parameter(vo.getMa_nh(), true));
        }
        if (vo.getNgay_gd() != null) {
            sqlBuff.append(" NGAY_GD=?,");
            v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_gd(),
                                                                  "dd/MM/yyyyy"),
                                          true));
        }
        if (vo.getCut_of_time() != null) {
            sqlBuff.append(" CUT_OF_TIME=?,");
            v_param.add(new Parameter(vo.getCut_of_time(), true));
        }
        if (vo.getInsert_date() != null) {
            sqlBuff.append(" INSERT_DATE=?,");
            v_param.add(new DateParameter(StringUtil.StringToDate(vo.getInsert_date(),
                                                                  "dd/MM/yyyyy"),
                                          true));
        }
        if (vo.getGhi_chu() != null) {
            sqlBuff.append(" GHI_CHU=?,");
            v_param.add(new Parameter(vo.getGhi_chu(), true));
        }
        if (vo.getSo_yc_cot() != null && vo.getSo_yc_cot() > 0) {
            sqlBuff.append(" SO_YC_COT=?,");
            v_param.add(new Parameter(vo.getSo_yc_cot(), true));
        }
        sqlBuff.deleteCharAt(sqlBuff.length() - 1);
        if (vo.getId() != null && vo.getId() > 0) {
            sqlBuff.append(" WHERE ID=?");
            v_param.add(new Parameter(vo.getId(), true));
        }
        nExc = executeStatement(sqlBuff.toString(), v_param, conn);

        return nExc;

    }

    public int updateCondition(TSoCutOfTimeVO vo, String where,
                               Vector params) throws DatabaseConnectionFailureException,
                                                     ExecuteStatementException {
        Vector v_param = new Vector();
        StringBuffer sqlBuff = new StringBuffer();
        sqlBuff.append(" Update sp_tso_cutoftime Set ");
        int nExc = 0;
        if (vo.getMa_nh_kb() != null) {
            sqlBuff.append(" MA_NH_KB=?,");
            v_param.add(new Parameter(vo.getMa_nh_kb(), true));
        }
        if (vo.getMa_nh() != null) {
            sqlBuff.append(" MA_NH=?,");
            v_param.add(new Parameter(vo.getMa_nh(), true));
        }
        if (vo.getNgay_gd() != null) {
            sqlBuff.append(" NGAY_GD=?,");
            v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_gd(),
                                                                  "dd/MM/yyyyy"),
                                          true));
        }
        if (vo.getCut_of_time() != null) {
            sqlBuff.append(" CUT_OF_TIME=?,");
            v_param.add(new Parameter(vo.getCut_of_time(), true));
        }
        if (vo.getInsert_date() != null) {
            sqlBuff.append(" INSERT_DATE=?,");
            v_param.add(new DateParameter(StringUtil.StringToDate(vo.getInsert_date(),
                                                                  "dd/MM/yyyyy"),
                                          true));
        }
        if (vo.getGhi_chu() != null) {
            sqlBuff.append(" GHI_CHU=?,");
            v_param.add(new Parameter(vo.getGhi_chu(), true));
        }
        if (vo.getSo_yc_cot() != null && vo.getSo_yc_cot() > 0) {
            sqlBuff.append(" SO_YC_COT=?,");
            v_param.add(new Parameter(vo.getSo_yc_cot(), true));
        }
        sqlBuff.deleteCharAt(sqlBuff.length() - 1);
        if (vo.getId() != null && vo.getId() > 0) {
            sqlBuff.append(" WHERE ID=?");
            v_param.add(new Parameter(vo.getId(), true));
        }
        if (where != null && !where.equals("")) {
            sqlBuff.append(where);
            if (params != null)
                v_param.addAll(params);
        }
        nExc = executeStatement(sqlBuff.toString(), v_param, conn);

        return nExc;

    }

  /**
   * @create date: 20/03/2017
   * @create by: ThuongDT
   * @see: them moi ham, muc dich tim trong he thong thoi gian COT pho bien nhat cua he thong trong ngay
   * @param whereClause
   * @param params
   * @return
   * @throws Exception
   * @find: thuongdt-20170320
   */
  public TSoCutOfTimeVO getTSoCTONormal(String whereClause,
                                     Vector params) throws Exception {
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append(" select cut_of_time from (SELECT cut_of_time,count(0)tong_so");
          strSQL.append(" FROM sp_tso_cutoftime a where 1=1 ");
          if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
              strSQL.append(whereClause);
          } 
          strSQL.append(" group by  cut_of_time order by count(0) desc) where rownum = 1 ");
          
          return (TSoCutOfTimeVO)findByPK(strSQL.toString(), params,
                                          CLASS_NAME_VO, conn);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getTSoCTO(): " +
                                 ex.getMessage(), ex);

      }
  }  

}
