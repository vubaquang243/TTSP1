package com.seatech.ttsp.dmnh;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import com.seatech.ttsp.dmkb.DMKBacVO;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;

    /*
     * @Modify: ThuongDT
     * @Modify date: 17/11/2016
     * @see: them moi function getclDMNHangHO
     * */
    /**
     * @Modify: HungBM
     * @Modify date: 26/06/2017
     * @see: Them function phuc vu cho bao cao Sao Ke Tai Khoan
     * @find: 20170626
     * */
public class DMNHangDAO extends AppDAO {
    private String CLASS_NAME_VO = "com.seatech.ttsp.dmnh.DMNHangVO";
    private String CLASS_NAME_HOVO = "com.seatech.ttsp.dmnh.DMNHangHOVO";
    private String CLASS_NAME_DAO = "com.seatech.ttsp.dmnh.DMNHangDAO";
    Connection conn = null;

    public DMNHangDAO(Connection conn) {
        this.conn = conn;
    }

    public DMNHangVO getDMNH(String whereClause,
                             Vector params) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT a.id, a.ma_nh, a.ma_tinh, a.ma_huyen, a.ten, a.tinh_trang ");
            strSQL.append("FROM sp_dm_ngan_hang a ");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            DMNHangVO dmnhVO =
                (DMNHangVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                    conn);
            return dmnhVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMNH(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
    }
    public DMNHangVO getDMNH_NN(String whereClause,
                           Vector params) throws Exception {
      try {
          StringBuffer strSQL = new StringBuffer();
          strSQL.append("SELECT a.id, (a.bic_code||a.branch_code) ma_nh, (a.institution_name || ' '|| a.branch_information) ten ");
          strSQL.append("FROM sp_dm_swift_code a ");

          if (whereClause != null && !whereClause.equals("")) {
              strSQL.append(" WHERE " + whereClause);
          }
          DMNHangVO dmnhVO =
              (DMNHangVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                  conn);
          return dmnhVO;
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_DAO + ".getDMNH_NN(): " +
                               ex.getMessage(), ex);
          //            daoEx.printStackTrace();
          throw daoEx;
      }
  }
    public DMNHangVO getDMNHSBySHKB(String whereClause,
                                    Vector params) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT a.ma_nh ");
            strSQL.append("FROM sp_dm_manh_shkb a ");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            DMNHangVO dmnhVO =
                (DMNHangVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                    conn);
            return dmnhVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMNHSBySHKB(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    /**
     * @see: Danh sach cac ngan hang ma kho bac mo tai khoan
     * @return: DMNHangVO
     * @param: String: kb_id
     * */
    public Collection getDMNHListByKBId(String strWhere,
                                        Vector vParam) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT DISTINCT a.id, a.ma_nh, a.ma_tinh, a.ma_huyen, a.ten, a.tinh_trang ");
            strSQL.append("FROM sp_dm_ngan_hang a, SP_TKNH_KB b, SP_DM_HTKB c ");
            strSQL.append("where a.id = b.nh_id and b.kb_id = c.id ");
            strSQL.append(strWhere);

            return executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, this.conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMNHListByKBId(): " +
                                 ex.getMessage(), ex);
            //             daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public Collection getListNH(String strWhere,
                                Vector vParam) throws Exception {

        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    "select DISTINCT b.id,c.ma_nh, c.ten  from sp_tknh_kb a, sp_dm_htkb b, sp_dm_ngan_hang c where " +
                    " a.kb_id = b.id and a.loai_tk in ('02','03') and a.nh_id = c.id";
            strSQL += strWhere + " order by ma_nh";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getListNH(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }
    //HungBM - 20170626 - BEGIN
    public Collection getListNHSaoKeTK(String strWhere,
                                Vector vParam) throws Exception {

        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    "select DISTINCT b.id,c.ma_nh, c.ten  from sp_tknh_kb a, sp_dm_htkb b, sp_dm_ngan_hang c where " +
                    " a.kb_id = b.id and a.loai_tk in ('01','02','03') and a.nh_id = c.id";
            strSQL += strWhere + " order by ma_nh";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getListNHSaoKeTK(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }
    //HungBM - 20170626 - END

    public DMNHangVO getTKNH(String strWhere, Vector vParam) throws Exception {

        DMNHangVO reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    "select b.id,c.ma_nh, c.ten " + " from sp_tknh_kb a, sp_dm_htkb b, sp_dm_ngan_hang c where " +
                    " a.kb_id = b.id and a.nh_id = c.id";
            strSQL += strWhere + " order by ma";
            reval =
                    (DMNHangVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO,
                                        this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getListNH(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public int getCountTKKB() throws DAOException {
        int result = 0;
        try {
            String strSQL = "";
            strSQL += "select count(1) from sp_tknh_kb";
            ResultSet rs =
                executeSelectStatement(strSQL, new Vector(), this.conn);
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getCountTKKB(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
        return result;
    }

    public Collection getDMNH_KBByKBId(String strKBacID) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT a.id, a.ma_nh, a.ma_tinh, a.ma_huyen, a.ten, a.tinh_trang ");
            strSQL.append("FROM sp_dm_ngan_hang a, SP_TKNH_KB b, SP_DM_HTKB c ");
            strSQL.append("where a.id = b.nh_id and b.kb_id = c.id ");
            strSQL.append("and c.id = ?");
            strSQL.append(" union SELECT c.id, b.ma_nh, '' ma_tinh,'' ma_huyen, b.ten, c.tinhtrang tinh_trang ");
            strSQL.append(" from sp_dm_manh_shkb b, sp_dm_htkb c ");
            strSQL.append(" where b.shkb=c.ma ");
            strSQL.append(" and c.id = ?");
            Vector vParam = new Vector();
            vParam.add(new Parameter(strKBacID, true));
            vParam.add(new Parameter(strKBacID, true));
            return executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, this.conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMNHListByKBId(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public Collection getDMNHList(String whereClause,
                                  Vector params) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append(" SELECT id, ma_nh, ma_tinh,ma_huyen,ten,tinh_trang ");
            strSQL.append(" FROM sp_dm_ngan_hang ");
            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            return executeSelectStatement(strSQL.toString(), params,
                                          CLASS_NAME_VO, this.conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMNHList(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }

    }
    
  public Collection getDMNHLovList(String whereClause,
                                Vector params) throws Exception {
      try {
          StringBuffer strSQL = new StringBuffer();
          strSQL.append(" SELECT DISTINCT id, ma_nh,ten,tinh_trang FROM (");
          strSQL.append(" SELECT id, ma_nh,ten, tinh_trang ");
          strSQL.append(" FROM sp_dm_ngan_hang ");
          strSQL.append(" UNION ALL");
          strSQL.append(" SELECT id, bic_code||branch_code  ma_nh,institution_name||' '||branch_information ten, tinh_trang ");
          strSQL.append(" FROM sp_dm_swift_code) ");
          
          if (whereClause != null && !whereClause.equals("")) {
              strSQL.append(" WHERE " + whereClause);
          }
          return executeSelectStatement(strSQL.toString(), params,
                                        CLASS_NAME_VO, this.conn);

      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_DAO + ".getDMNHLovList(): " +
                               ex.getMessage(), ex);
          //            daoEx.printStackTrace();
          throw daoEx;
      }

  }


    public Collection getDMNHHOList(String whereClause,
                                    Vector params) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append(" SELECT a.ma_nh, b.id as id_nhkb, a.ten_nh, a.ma_dv, a.bi_danh, a.ma_ung_dung, ");
            strSQL.append("  a.ten_ung_dung, a.tk_thanh_toan, a.tk_cho_xly, a.tk_tt_qt_sgd_no, ");
            strSQL.append(" a.tk_tt_qt_sgd_co, a.tk_cxl_qt_sgd_no, a.tk_cxl_qt_sgd_co, ");
            strSQL.append(" a.tk_tt_qt_tquoc_no, a.tk_tt_qt_tquoc_co, a.tk_cxl_qt_tquoc_no, ");
            strSQL.append("  a.tk_cxl_qt_tquoc_co, a.tk_tt_qt_huyen_no, a.tk_tt_qt_huyen_co, ");
            strSQL.append(" a.tk_cxl_qt_huyen_no, a.tk_cxl_qt_huyen_co ");
            strSQL.append(" FROM sp_dm_nh_ho a,sp_dm_ngan_hang b where a.ma_nh=b.ma_nh ");
            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(whereClause);
            }
            return executeSelectStatement(strSQL.toString(), params,
                                          CLASS_NAME_HOVO, this.conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMNHHOList(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }

    }
    /*
     * @Creator: ThuongDT
     * @Creator date: 17/11/2016
     * @see: them moi function lay danh muc ngan hang 
     * BEGIN */
	 
  public Collection getclDMNHangHO(String strWhereClause,
                                  Vector parameters) throws Exception {
    Collection clReturn = null;
      String strSQL =
          "SELECT a.ma_nh, a.ten_nh, a.ma_dv, a.bi_danh, a.ma_ung_dung," +
          "       a.ten_ung_dung, a.tk_thanh_toan, a.tk_cho_xly, a.tk_tt_qt_sgd_no," +
          "       a.tk_tt_qt_sgd_co, a.tk_cxl_qt_sgd_no, a.tk_cxl_qt_sgd_co," +
          "       a.tk_tt_qt_tquoc_no, a.tk_tt_qt_tquoc_co, a.tk_cxl_qt_tquoc_no," +
          "       a.tk_cxl_qt_tquoc_co, a.tk_tt_qt_huyen_no, a.tk_tt_qt_huyen_co," +
          "       a.tk_cxl_qt_huyen_no, a.tk_cxl_qt_huyen_co" +
          "  FROM sp_dm_nh_ho a where (1=1) "+strWhereClause;
        clReturn = executeSelectStatement(strSQL.toString(), null,
                                              CLASS_NAME_HOVO, this.conn);
      return clReturn;
  }
	/*END*/
    public DMNHangHOVO getDMNHangHO(String strWhereClause,
                                    Vector parameters) throws Exception {
        String strSQL =
            "SELECT a.ma_nh, a.ten_nh, a.ma_dv, a.bi_danh, a.ma_ung_dung," +
            "       a.ten_ung_dung, a.tk_thanh_toan, a.tk_cho_xly, a.tk_tt_qt_sgd_no," +
            "       a.tk_tt_qt_sgd_co, a.tk_cxl_qt_sgd_no, a.tk_cxl_qt_sgd_co," +
            "       a.tk_tt_qt_tquoc_no, a.tk_tt_qt_tquoc_co, a.tk_cxl_qt_tquoc_no," +
            "       a.tk_cxl_qt_tquoc_co, a.tk_tt_qt_huyen_no, a.tk_tt_qt_huyen_co," +
            "       a.tk_cxl_qt_huyen_no, a.tk_cxl_qt_huyen_co" +
            "  FROM sp_dm_nh_ho a where (1=1) ";

        DMNHangHOVO vo =
            (DMNHangHOVO)findByPK(strSQL + strWhereClause, parameters,
                                  "com.seatech.ttsp.dmnh.DMNHangHOVO", conn);

        return vo;
    }

    public DMNHangHOVO getDMNHangBKLTT(String strWhereClause,
                                       Vector parameters) throws Exception {
        String strSQL =
            "SELECT a.ma_nh, a.ten_nh, a.ma_dv, a.bi_danh, a.ma_ung_dung," +
            "       a.ten_ung_dung, a.tk_thanh_toan, a.tk_cho_xly, a.tk_tt_qt_sgd_no," +
            "       a.tk_tt_qt_sgd_co, a.tk_cxl_qt_sgd_no, a.tk_cxl_qt_sgd_co," +
            "       a.tk_tt_qt_tquoc_no, a.tk_tt_qt_tquoc_co, a.tk_cxl_qt_tquoc_no," +
            "       a.tk_cxl_qt_tquoc_co, a.tk_tt_qt_huyen_no, a.tk_tt_qt_huyen_co," +
            "       a.tk_cxl_qt_huyen_no, a.tk_cxl_qt_huyen_co" +
            "  FROM  sp_bke_qtoan b inner join  sp_dm_nh_ho a on a.ma_dv = b.tcg_ngan_hang where (1=1) ";

        DMNHangHOVO vo =
            (DMNHangHOVO)findByPK(strSQL + strWhereClause, parameters,
                                  "com.seatech.ttsp.dmnh.DMNHangHOVO", conn);

        return vo;
    }

    public DMNHangVO getTsoInLTT(String whereClause,
                                 Vector params) throws Exception {
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT	DISTINCT a.ma, c.ten ten_tinh, a.ten ten_huyen, e.ma_nh ma_kb, d.ten ten_ngan_hang," +
                    " d.ma_nh, a.id, a.ma_cha, a.id_cha, a.cap" +
                    " FROM	sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c, sp_dm_ngan_hang d, sp_dm_manh_shkb e" +
                    " WHERE a.id = b.kb_id AND b.nh_id = d.id AND a.ma = e.shkb AND a.id_cha = c.id " +
                    whereClause;
            DMNHangVO vo =
                (DMNHangVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                    conn);
            return vo;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException("" + ".getTsoInLTT(): " + ex.getMessage(),
                                 ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public DMNHangVO getNH299(String whereClause,
                              Vector params) throws Exception {
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT  d.ma_nh ma_kb ,b.ma_nh, e.ma_nh ma_hoi_so, e.ma_ung_dung, e.ten_ung_dung" +
                    "  FROM  sp_dm_htkb a, sp_dm_ngan_hang b, sp_tknh_kb c, sp_dm_manh_shkb d, sp_dm_nh_ho e" +
                    " WHERE a.id = c.kb_id AND b.id = c.nh_id and a.ma= d.shkb and substr(b.ma_nh,3,3) = e.ma_dv" +
                    whereClause;
            DMNHangVO vo =
                (DMNHangVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                    conn);
            return vo;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException("" + ".getNH299(): " + ex.getMessage(), ex);
            //          daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public int update(DMNHangVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = null;
        int nExc = 0;
        try {
            strSQL.append("update sp_dm_ngan_hang set ");

            if (vo.getTen() != null) {
                strSQL2 = new StringBuffer();
                strSQL2.append("ten = ? ");
                v_param.add(new Parameter(vo.getTen(), true));
            }


            strSQL.append(strSQL2);
            strSQL.append("where id = ? ");
            v_param.add(new Parameter(vo.getId(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            throw ex;
        }

        return nExc;
    }

    public int insert(DMNHangVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        //Lay ID tu seq
        Long lID = getSeqNextValue("sp_dm_ngan_hang_seq", conn);

        strSQL.append("insert into sp_dm_ngan_hang (id ");
        strSQL2.append(") values (? ");
        v_param.add(new Parameter(lID, true));

        if (vo.getMa_nh() != null) {
            strSQL.append(", ma_nh");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getMa_nh(), true));
        }
        if (vo.getTen() != null) {
            strSQL.append(", ten");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getTen(), true));
        }
        if (vo.getTinh_trang() != null) {
            strSQL.append(", tinh_trang");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getTinh_trang(), true));
        }

        strSQL.append(strSQL2);
        strSQL.append(")");
        return executeStatement(strSQL.toString(), v_param, conn);
    }


}


