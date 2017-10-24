package com.seatech.ttsp.ktvtm;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;


public class KTVTabmisDAO extends AppDAO {
    private String CLASS_NAME_VO = "com.seatech.ttsp.ktvtm.KTVTabmisVO";
    private String CLASS_NAME_DAO = "com.seatech.ttsp.ktvtm.KTVTabmisDAO";
    private static final String STRING_EMPTY = "";
    private transient Connection conn = null;

    public KTVTabmisDAO(Connection conn) {
        this.conn = conn;
    }

    private void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * @see: Get list KTVTabmis ko phan trang
     * */
    public Collection getKTVTabmisList(String strWhere,
                                       Vector v_param) throws Exception {

        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("select id, ma, ten, nguoi_tao, to_char(ngay_tao,'dd/mm/yyyy') ngay_tao, kb_id from sp_ktv_tabmis ");
            if (strWhere != null && !strWhere.equals("")) {
                strSQL.append(" where ");
                strSQL.append(strWhere);
            }
            return executeSelectStatement(strSQL.toString(), v_param,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getKTVTabmisList(strWhere, v_param): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

    }

    /**
     * @see: Get list KTVTabmis co phan trang
     * */
    public Collection getKTVTabmisList_ptrang(String strWhere, Vector vParam,
                                       Integer page, Integer count,
                                       Integer[] totalCount) throws Exception {
        StringBuffer strSQL = new StringBuffer();

        //
        //      SELECT a.id,a.ma, a.ten,s.ma_nsd, to_char(a.ngay_tao,'dd/mm/yyyy')
        //      FROM sp_ktv_tabmis a
        //        INNER JOIN sp_nsd s
        //                ON a.nguoi_tao = s.id

        strSQL.append("select a.id,a.ma, a.ten, s.ma_nsd, a.nguoi_tao, ");
        strSQL.append(" to_char(a.ngay_tao,'dd/mm/yyyy') ngay_tao ,a.kb_id from sp_ktv_tabmis a ");
        strSQL.append(" LEFT JOIN sp_nsd s ON a.nguoi_tao = s.id");
        strSQL.append(" LEFT JOIN sp_dm_htkb c on a.kb_id=c.id ");

        try {
            if (strWhere != null && !strWhere.equals("")) {
                strSQL.append(" where (1=1)  ");
                strSQL.append(strWhere);
            }
            strSQL.append(" ORDER BY a.ngay_tao DESC ");
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           CLASS_NAME_VO, page, count,
                                           totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getKTVTabmisList(strWhere, vParam, page, count, totalcount): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

    }
  public ResultSet getKTVTabmisResultSet(String strWhere, Vector vParam) throws Exception {
      StringBuffer strSQL = new StringBuffer();

      //
      //      SELECT a.id,a.ma, a.ten,s.ma_nsd, to_char(a.ngay_tao,'dd/mm/yyyy')
      //      FROM sp_ktv_tabmis a
      //        INNER JOIN sp_nsd s
      //                ON a.nguoi_tao = s.id

      strSQL.append("select a.id,a.ma, a.ten, s.ma_nsd, a.nguoi_tao, ");
      strSQL.append(" to_char(a.ngay_tao,'dd/mm/yyyy') ngay_tao ,a.kb_id from sp_ktv_tabmis a ");
      strSQL.append(" INNER JOIN sp_nsd s ON a.nguoi_tao = s.id");
      strSQL.append(" LEFT JOIN sp_dm_htkb c on a.kb_id=c.id ");

      try {
          if (strWhere != null && !strWhere.equals("")) {
              strSQL.append(" where (1=1)  ");
              strSQL.append(strWhere);
          }
          strSQL.append(" ORDER BY a.ngay_tao DESC ");
          return executeSelectStatement(strSQL.toString(), vParam,conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_DAO + ".getKTVTabmisResultSet(strWhere, vParam, page, count, totalcount): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }

  }
    /**
     * @see: Lay thong tin KTVtamis
     * */

    public KTVTabmisVO getKTVTabmis(String strWhere,
                                    Vector vParam) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("select id, ma, ten, nguoi_tao, to_char(ngay_tao,'dd/mm/yyyy') ngay_tao,kb_id from sp_ktv_tabmis ");
        try {
            if (strWhere != null && !strWhere.equals("")) {
                strSQL.append(" where (1=1) ");
                strSQL.append(strWhere);
            }
            return (KTVTabmisVO)findByPK(strSQL.toString(), vParam,
                                         CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getKTVTabmis(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

    }


    /**
     * @param: KTVTabmsVO
     * @return: 1: them moi thanh cong; 0: them moi khong thanh cong
     * @see: Them moi KTV tabmis
     * */
    public int insert(KTVTabmisVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        //Lay id tu seq
        Long id = getSeqNextValue("sp_ktv_tabmis_seq", conn);
        strSQL.append("insert into sp_ktv_tabmis (id ");
        strSQL2.append(") values (? ");
        v_param.add(new Parameter(id, true));
        if (vo.getMa() != null) {
            strSQL.append(", ma");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getMa(), true));
        }
        if (vo.getTen() != null) {
            strSQL.append(", ten");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getTen(), true));
        }
        if (vo.getNguoi_tao() != null) {
            strSQL.append(", nguoi_tao");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getNguoi_tao(), true));
        }
        if (vo.getMa_kb() != null) {
            strSQL.append(", kb_id");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getKb_id(), true));
        }
        if (vo.getNgay_tao() == null) {
            strSQL.append(", ngay_tao");
            strSQL2.append(", sysdate");
        }


        strSQL.append(strSQL2);
        strSQL.append(")");
        return executeStatement(strSQL.toString(), v_param, conn);
    }


    /**
     * @param: Long
     * @return: 1: xoa thanh cong; 0: Xoa khong thanh cong
     * @see: Xoa KTV tabmis
     * */
    public int delete(Long id) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" delete sp_ktv_tabmis where id = ?");
        int nExc = 0;
        if (id != null) {
            v_param.add(new Parameter(id, true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
        }
        return nExc;
    }

    /**
     * @param: KTVTabmisVO
     * @return: 1: update thanh cong; 0: Update khong thanh cong
     * @see: Sua ten KTV tabmis
     * */
    public int update(KTVTabmisVO vo) throws Exception {
        
        try{
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_ktv_tabmis set ");
        int nExc = 0;
       
            if (vo.getTen() != null) {
                strSQL.append(" ten = ? , ");
                v_param.add(new Parameter(vo.getTen(), true));
            } else {
                strSQL.append("");
            }
       
        if (vo.getMa() != null) {
            strSQL.append(" ma = ? , ");
            v_param.add(new Parameter(vo.getMa(), true));
        }
            
        if (vo.getKb_id() != null) {
            strSQL.append(" kb_id = ? ");
            v_param.add(new Parameter(vo.getKb_id(), true));
        }
        strSQL.append(" where id = ? ");
        v_param.add(new Parameter(vo.getId(), true));
        nExc = executeStatement(strSQL.toString(), v_param, conn);
        return nExc;
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(CLASS_NAME_DAO + ".update(KTVTabmisVO vo): " +
                                 ex.getMessage(), ex);
          return -1;
      }
     
    }

    public Collection getKTVTabmisListByNSD(Long nUserID,
                                            Long nKbId) throws Exception {

        try {
            StringBuffer strSQL = new StringBuffer();
            Vector vParam = new Vector();
            strSQL.append("SELECT a.id, a.ma, a.ten, a.nguoi_tao, a.ngay_tao,a.kb_id, ");
            strSQL.append("  (select count(*) from sp_chon_ktv_tabmis b where b.ktv_id = a.id and b.nsd_id = ?) trang_thai_chon ");
            vParam.add(new Parameter(nUserID, true));
            strSQL.append("  FROM sp_ktv_tabmis a ");
            strSQL.append("  WHERE a.kb_id =? ");
            vParam.add(new Parameter(nKbId, true));
            return executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getKTVTabmisListByNSD(strWhere, v_param): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

    }

    /**
     * @see: Dung cho check KTV tabmis trong LTT
     * */
    public String getKTVTabmisListByNSD(Long nUserID) throws Exception {
        StringBuffer szMaKTVList = new StringBuffer("");
        try {
            StringBuffer strSQL = new StringBuffer();
            Vector vParam = new Vector();
            strSQL.append("SELECT a.id, a.ma ");
            strSQL.append("FROM sp_ktv_tabmis a ");
            strSQL.append("inner join sp_chon_ktv_tabmis b on a.id = b.ktv_id ");
            strSQL.append("and b.ttv_id = ? ");
            vParam.add(new Parameter(nUserID, true));
            Collection colKTVList =
                executeSelectStatement(strSQL.toString(), vParam,
                                       CLASS_NAME_VO, conn);
            Iterator iter = colKTVList.iterator();
            int nCounter = 0;
            while (iter.hasNext()) {
                KTVTabmisVO ktvVO = (KTVTabmisVO)iter.next();
                if (nCounter == 0) {
                    szMaKTVList.append("'");
                    szMaKTVList.append(ktvVO.getMa());
                    szMaKTVList.append("'");
                } else {
                    szMaKTVList.append(", '");

                    szMaKTVList.append(ktvVO.getMa());
                    szMaKTVList.append("'");
                }
                nCounter++;
            }
            return szMaKTVList.toString();
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getKTVTabmisListByNSD(" +
                                 nUserID + "): " + ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

    }
    
  /**
   * @see: Dung de get TTV dua tren ktt_chuyen cua ltt
   * */
  public String getTTVByKTVChuyen(String strKTVID) throws Exception {
      String strTTV ="";
      try {
          StringBuffer strSQL = new StringBuffer();
          Vector vParam = new Vector();
          strSQL.append("select ttv_id from sp_chon_ktv_tabmis a ");
          strSQL.append("inner join sp_ktv_tabmis b on a.ktv_id = b.id ");
          strSQL.append("where b.ma = ? and rownum=1 ");
          vParam.add(new Parameter(strKTVID, true));
          ResultSet rs =  executeSelectStatement(strSQL.toString(), vParam, conn);
          if(rs.next()){
            strTTV = rs.getString(0);
          }
          return strTTV;
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_DAO + ".getTTVByKTVChuyen(" +
                               strKTVID + "): " + ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }

  }
}


