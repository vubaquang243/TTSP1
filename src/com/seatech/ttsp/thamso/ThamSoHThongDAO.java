package com.seatech.ttsp.thamso;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;
 
  /**
     * @Modifier:HungBM
	 * @Date: 12/12/2016
     * @see: Them bang tra cuu de loc theo kho bac dia phuong
     * track: 20161212
     * */

public class ThamSoHThongDAO extends AppDAO {
    private Connection conn = null;
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.thamso.ThamSoHThongVO";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.thamso.ThamSoHThongDAO";

    public ThamSoHThongDAO(Connection conn) {
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
            strSQL.append("SELECT a.id, a.ten_ts, a.giatri_ts, a.mo_ta, a.cho_phep_sua, a.kb_id, ");
            strSQL.append("to_char(a.ngay_cap_nhat,'dd/mm/yyyy') ngay_cap_nhat ");
            strSQL.append("FROM sp_thamso_ht a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getThamSoList(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    public Collection getThamSoReSend(String whereClause,
                                      Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ten_ts, a.giatri_ts, a.mo_ta, a.cho_phep_sua, a.kb_id, ");
            strSQL.append("to_char(a.ngay_cap_nhat,'dd/mm/yyyy') ngay_cap_nhat ");
            strSQL.append("FROM sp_thamso_ht_rs a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getThamSoReSend(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    public ResultSet getThamSoResultSet(String whereClause,
                                        Vector params) throws Exception {
        ResultSet reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ten_ts, a.giatri_ts, nvl(a.mo_ta,' ') mo_ta, a.cho_phep_sua, a.kb_id, ");
            strSQL.append("nvl(to_char(a.ngay_cap_nhat,'dd/mm/yyyy'),' ') ngay_cap_nhat ");
            strSQL.append("FROM sp_thamso_ht a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
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
     * @return: Collection
     * */
    public ThamSoHThongVO getThamSo(String whereClause,
                                    Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ten_ts, a.giatri_ts, a.mo_ta, a.cho_phep_sua, a.kb_id, ");
            strSQL.append("to_char(a.ngay_cap_nhat,'dd/mm/yyyy') ngay_cap_nhat ");
            strSQL.append("FROM sp_thamso_ht a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (ThamSoHThongVO)findByPK(strSQL.toString(), params,
                                            CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getThamSo(): " +
                                   ex.getMessage(), ex);
        }
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Sua Tham so
     * @return: int
     * */

    public int update(ThamSoHThongVO vo) throws Exception {
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
        if (vo.getCho_phep_sua() != null) {
            if (strSQL2 == null) {
                strSQL2 = new StringBuffer();
                strSQL2.append("cho_phep_sua = ? ");
            } else {
                strSQL2.append(", cho_phep_sua = ? ");
            }
            v_param.add(new Parameter(vo.getCho_phep_sua(), true));
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

        strSQL.append("where id = ? ");
        v_param.add(new Parameter(vo.getId(), true));

        return executeStatement(strSQL.toString(), v_param, conn);
    }


    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach tham so lich su
     * @return: Collection
     * */
    
    /* HungBM edit
     * 16/11/2016
     * Them dieu kien tim kiem theo kho bac
     * Them bang SP_DM_HTKB c vao query 
	 * 20161212
     * */
    public Collection getThamSoLSuList(String whereClause, Vector vParam,
                                       Integer page, Integer count,
                                       Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ten_ts, a.giatri_ts, a.giatri_ts_moi, a.shkb, a.mo_ta,");
            strSQL.append("to_char(a.ngay_cap_nhat,'dd/mm/yyyy') ngay_cap_nhat, a.nsd_id, b.ma_nsd , a.kb_id ");
            strSQL.append("FROM sp_thamso_ht_ls a , sp_nsd b, SP_DM_HTKB c where a.nsd_id=b.id and a.shkb = c.ma(+)  ");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getThamSoList(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }

    public ResultSet getThamSoLSuResultSet(String whereClause,
                                           Vector vParam) throws Exception {
        ResultSet reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ten_ts, a.giatri_ts, a.giatri_ts_moi, nvl(a.mo_ta,' ') mo_ta,");
            strSQL.append("nvl(to_char(a.ngay_cap_nhat,'dd/mm/yyyy'),' ') ngay_cap_nhat, a.nsd_id, a.kb_id ");
            strSQL.append("FROM sp_thamso_ht_ls a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval = executeSelectStatement(strSQL.toString(), vParam, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getThamSoList(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }

    /**
     * @param: ThamSoHThongVO
     * @return: 0: ko thanh cong; tra ve ID cua ts_id vua dc tao neu thanh cong
     * @see:
     * */
    public Long insert(ThamSoHThongVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        //Lấy ID từ seq
        Long lID = getSeqNextValue("sp_thamso_ht_ls_seq", conn);

        strSQL.append("insert into sp_thamso_ht_ls (id ");
        strSQL2.append(") values (? ");
        v_param.add(new Parameter(lID, true));
        if (vo.getTen_ts() != null) {
            strSQL.append(", ten_ts");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getTen_ts(), true));
        }
        if (vo.getGiatri_ts() != null) {
            strSQL.append(", giatri_ts");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getGiatri_ts(), true));
        }
        if (vo.getGiatri_ts_moi() != null) {
            strSQL.append(", giatri_ts_moi");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getGiatri_ts_moi(), true));
        }
        if (vo.getMo_ta() != null) {
            strSQL.append(", mo_ta");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getMo_ta(), true));
        }
        if (vo.getKb_id() != null) {
            strSQL.append(", kb_id");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getKb_id(), true));
        }
        if (vo.getNsd_id() != null) {
            strSQL.append(", nsd_id");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getNsd_id(), true));
        }
        if (vo.getShkb() != null) {
            strSQL.append(", shkb");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getShkb(), true));
        }
        strSQL.append(", ngay_cap_nhat");
        strSQL2.append(", sysdate");
        strSQL.append(strSQL2);
        strSQL.append(")");
        if (executeStatement(strSQL.toString(), v_param, conn) == 1)
            return lID;
        else
            return new Long("0");
    }

    public static void main(String[] a) {
        try {
            AppDAO dao = new AppDAO();
            Connection conn = dao.getConnectionTest();

            //         ThamSoHThongDAO dao2 = new ThamSoHThongDAO(conn);
            //        Collection coll = dao2.getThamSoList(null, null);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
