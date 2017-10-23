package com.seatech.ttsp.thamsokb;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;


public class ThamSoKBDAO extends AppDAO {
    private Connection conn = null;
    private static String CLASS_NAME_VO = "com.seatech.ttsp.thamsokb.ThamSoKBVO";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.thamsokb.ThamSoKBDAO";

    public ThamSoKBDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach tham so
     * @return: Collection
     * */
    public Collection getThamSoList(String whereClause,
                                    Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.ten_ts, a.giatri_ts, a.mo_ta, a.cho_phep_sua, a.kb_id, ");
            strSQL.append("to_char(a.ngay_cap_nhat,'dd/mm/yyyy') ngay_cap_nhat ");
            strSQL.append("FROM sp_thamso_kb a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.ten_ts DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getThamSoList(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

  /**
   * @create-date: 19/11/2017
   * @see: Thuongdt them moi ham dung chung check tham so
   * @return: boolean
   * */
  public boolean checkThamSo(String ma_kb,String ma_nh, String tham_so, String gia_tri) throws Exception {
      boolean bReturn = false;
      ResultSet reval = null;
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT 1 FROM sp_thamso_kb a where 1=1 ");
        if(ma_kb != null && !"".equals(ma_kb))
          strSQL.append(" AND kb_id= '"+ma_kb+"'");
        if(ma_nh != null && !"".equals(ma_nh))
          strSQL.append(" AND ma_nh= '"+ma_nh+"'");
        if(tham_so != null && !"".equals(tham_so))
          strSQL.append(" AND ten_ts= '"+tham_so+"' ");
        if(gia_tri != null && !"".equals(gia_tri))
          strSQL.append(" AND GIATRI_TS= '"+gia_tri+"' ");
          if (strSQL.indexOf("AND")>0 ) {
            reval = executeSelectStatement(strSQL.toString(), null,  conn);
            bReturn =  reval.next();
          }
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getThamSoList(): " +
                                 ex.getMessage(), ex);
      }

      return bReturn;
  }

    public ResultSet getThamSoResultSet(String whereClause,
                                        Vector params) throws Exception {
        ResultSet reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.ten_ts, a.giatri_ts, nvl(a.mo_ta,' ') mo_ta, a.cho_phep_sua, a.kb_id, ");
            strSQL.append("nvl(to_char(a.ngay_cap_nhat,'dd/mm/yyyy'),' ') ngay_cap_nhat ");
            strSQL.append("FROM sp_thamso_kb a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.ten_ts DESC ");
            reval = executeSelectStatement(strSQL.toString(), params, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getThamSoListResultSet(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra thong tin tham so
     * @return: 
     * llection
     * */
    public ThamSoKBVO getThamSoKB(String whereClause,
                                Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.ten_ts, a.giatri_ts, a.mo_ta, a.cho_phep_sua, a.kb_id, ");
            strSQL.append("to_char(a.ngay_cap_nhat,'dd/mm/yyyy') ngay_cap_nhat ");
            strSQL.append("FROM sp_thamso_kb a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (ThamSoKBVO)findByPK(strSQL.toString(), params,
                                        CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getThamSo(): " +
                                   ex.getMessage(), ex);
        }
    }

  public ThamSoKBVO getLoai_GD(String strWhere, Vector vParam) throws Exception {
    ThamSoKBVO dmucvo= null;
      try {
          String strSQL =
              "SELECT	distinct a.nh_id,a.kb_id kb_id, a.loai_gd, b.ma_nh ma_nh , c.ma_nh ma_kb, e.giatri_ts giatri_ts, e.ten_ts " + 
              "  FROM	sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_manh_shkb c, sp_dm_htkb d, sp_thamso_kb e " + 
              " WHERE	a.nh_id = b.id AND c.shkb= d.ma and a.kb_id=d.id and a.kb_id = e.kb_id "+strWhere;
        dmucvo = (ThamSoKBVO)findByPK(strSQL.toString(), vParam,
                                    CLASS_NAME_VO, conn);


        } catch (Exception e) {
          throw e;
        }
        return dmucvo;
  }  

    /**
     * @param: Menh de where; vector tham so
     * @see: Sua Tham so
     * @return: int
     * */

    public int update(ThamSoKBVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = null;

        strSQL.append("update sp_thamso_ht set ");

        if (vo.getTen_ts() != null) {
            strSQL2 = new StringBuffer();
            strSQL2.append("ten_ts = ? ");
            v_param.add(new Parameter(vo.getTen_ts(), true));
        }
        if (vo.getMo_ta() != null) {
            if (strSQL2 == null) {
                strSQL2 = new StringBuffer();
                strSQL2.append("mo_ta = ? ");
            } else {
                strSQL2.append(", mo_ta = ? ");
            }
            v_param.add(new Parameter(vo.getMo_ta(), true));
        }
        if (vo.getGiatri_ts_moi() != null) {
            if (strSQL2 == null) {
                strSQL2 = new StringBuffer();
                strSQL2.append("giatri_ts = ? ");
            } else {
                strSQL2.append(", giatri_ts = ? ");
            }
            v_param.add(new Parameter(vo.getGiatri_ts_moi(), true));
        }
        if (vo.getKb_id() != null) {
            if (strSQL2 == null) {
                strSQL2 = new StringBuffer();
                strSQL2.append("kb_id = ? ");
            } else {
                strSQL2.append(", kb_id = ? ");
            }
            v_param.add(new Parameter(vo.getKb_id(), true));
        }

        if (strSQL2 == null) {
            strSQL2 = new StringBuffer();
            strSQL2.append("ngay_cap_nhat = ? ");
        } else {
            strSQL2.append(", ngay_cap_nhat = sysdate ");
        }


        strSQL.append(strSQL2);

        strSQL.append("where ten_ts = ? ");
        v_param.add(new Parameter(vo.getTen_ts(), true));

        return executeStatement(strSQL.toString(), v_param, conn);
    }


  /**
   * @date: 17/10/2017
   * @param: Menh de setValue; Menh de whereValue;
   * @see: them moi ham update
   * @return: int
   * */

  public int update(String setValue, String whereValue) throws Exception {
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      strSQL.append("update sp_thamso_ht set "+setValue +" where 1=1 "+whereValue);
      return executeStatement(strSQL.toString(), v_param, conn);
  }

  public int update_TSKB(ThamSoKBVO vo) throws Exception {
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      StringBuffer strSQL2 = null;

      strSQL.append("update sp_thamso_kb set ");

        if (vo.getGiatri_ts() != null) {
            if (strSQL2 == null) {
                strSQL2 = new StringBuffer();
                strSQL2.append("giatri_ts = ? ");
            } else {
                strSQL2.append(", giatri_ts = ? ");
            }
            v_param.add(new Parameter(vo.getGiatri_ts(), true));
        }

      strSQL.append(strSQL2);

      strSQL.append("where ten_ts in  ('CHO_PHEP_QUYET_TOAN_TAM','CHO_PHEP_NHAP_THU_CONG') and kb_id=? ");
//      v_param.add(new Parameter(vo.getTen_ts(), true));
      v_param.add(new Parameter(vo.getKb_id(), true));

      return executeStatement(strSQL.toString(), v_param, conn);
  }
    
    

    

    public static void main(String[] a) {
        try {
            AppDAO dao = new AppDAO();
            Connection conn = dao.getConnectionTest();

                        ThamSoKBDAO dao2 = new ThamSoKBDAO(conn);
                        Collection coll = dao2.getThamSoList(null, null);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
