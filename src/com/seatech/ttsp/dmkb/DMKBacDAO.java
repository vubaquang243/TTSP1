package com.seatech.ttsp.dmkb;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.SelectStatementException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;


public class DMKBacDAO extends AppDAO {
    private String CLASS_NAME_VO = "com.seatech.ttsp.dmkb.DMKBacVO";
    private String CLASS_NAME_DAO = "com.seatech.ttsp.dmkb.DMKBacDAO";
    private static String STRING_EMPTY = "";
    Connection conn = null;

    public DMKBacDAO(Connection conn) {
        this.conn = conn;
    }

    public Collection getDMKBList(String whereClause,
                                  Vector params) throws Exception {

        Collection reval = null;
        try {

            StringBuffer strSQL = new StringBuffer();

            strSQL.append("SELECT a.ma, a.ten, a.tinhtrang, a.id, a.ma_cha, a.id_cha, a.loai, ");
            strSQL.append("a.ma_t, a.ma_h, a.cap, b.ma_nt ");
            strSQL.append("FROM sp_dm_htkb a LEFT JOIN sp_tknh_kb b ON a.ma = b.id ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" order by  ltrim(REPLACE(ten,'KBNN',''))");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMKBList(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getDMKBTCUUList(String whereClause,
                                      Vector params) throws Exception {

        Collection reval = null;
        try {

            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT  DISTINCT a.ma ma_nh, a.ten,a.id,a.id_cha, c.ma ma_cha, c.ten kb_tinh, b.ma_nt ");
            strSQL.append("FROM  sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE  1 = 1 AND a.id_cha = c.id " +
                              whereClause);
            }
            strSQL.append(" order by  a.ma");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMKBList(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getDMNHKBList(String whereClause,
                                    Vector params) throws Exception {

        Collection reval = null;
        try {

            StringBuffer strSQL = new StringBuffer();

            strSQL.append("SELECT a.ma, b.ma_nh, a.ten, a.tinhtrang, a.id, a.ma_cha, a.id_cha, a.loai, ");
            strSQL.append("a.ma_t, a.ma_h, a.cap ");
            strSQL.append("FROM sp_dm_htkb a left join sp_dm_manh_shkb b on a.ma=b.shkb ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" order by  a.ten");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMKBList(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getDMKBListWithPading(String whereClause, Vector params,
                                            Integer page, Integer rowPerPage,
                                            Integer[] totalCount) throws Exception {

        Collection reval = null;
        try {

            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT a.ma, a.ten, a.tinhtrang, a.id, a.ma_cha, a.id_cha, a.loai, ");
            strSQL.append("a.ma_t, a.ma_h, a.cap ");
            strSQL.append("FROM sp_dm_htkb a ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY id ");

            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), params, CLASS_NAME_VO,
                                            page, rowPerPage, totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMKBListWithPading(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public DMKBacVO getDMKB(String whereClause,
                            Vector params) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT a.ma, b.ma_nh,  a.ten, a.tinhtrang, a.id, a.ma_cha, a.id_cha, a.loai, ");
            strSQL.append("a.ma_t, a.ma_h, a.cap ");
            strSQL.append("FROM sp_dm_htkb a, sp_dm_manh_shkb b WHERE a.ma= b.shkb(+) " +
                          whereClause);
            strSQL.append(" order by  a.ten");
            DMKBacVO dmkbVO =
                (DMKBacVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                   conn);
            return dmkbVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMKB(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public DMKBacVO getDMKBNH(String whereClause,
                              Vector params) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT a.ma,b.ma_nh as ma_nh, a.ten, a.tinhtrang, a.id, a.ma_cha, a.id_cha, a.loai, ");
            strSQL.append("a.ma_t, a.ma_h, a.cap ");
            strSQL.append("FROM sp_dm_htkb a left join sp_dm_manh_shkb b on a.ma = b.shkb");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            DMKBacVO dmkbVO =
                (DMKBacVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                   conn);
            return dmkbVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMKB(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public String getMaKB8So(String strMaKB) throws DAOException {
        ResultSet rs = null;
        try {
            StringBuffer strSQL = new StringBuffer();

            strSQL.append("SELECT a.ma_nh, a.shkb, a.ten FROM sp_dm_manh_shkb a ");
            strSQL.append("WHERE trim(a.shkb) = ?");
            Vector vParam = new Vector();
            vParam.add(new Parameter(strMaKB, true));
            rs = executeSelectStatement(strSQL.toString(), vParam, conn);
            if (rs.next()) {
                return rs.getString("ma_nh");
            }
            return "0";
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getMaKB8So(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        } finally {
            close(rs);
        }

    }

    public String getMaDVSDNSFromMaKB(String strMaKB, String strLoaiGD) throws Exception {
//        String strMaDVSDNS = "";
        ResultSet rs = null;
        try {
            String strSQL =
                "select ma_dvsdns from sp_dm_shkb_dvsdns where ma_kb = ? and loai_gd = ?";
            Vector vParam = new Vector();
            vParam.add(new Parameter(strMaKB, true));
            vParam.add(new Parameter(strLoaiGD, true));
            rs = executeSelectStatement(strSQL.toString(), vParam, conn);
            if (rs.next()) {
                return rs.getString("ma_dvsdns");
            }
            return "0000000";
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(rs);
        }
    }

    public int update(DMKBacVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = null;
        int nExc = 0;
        try {
            strSQL.append("update sp_dm_htkb set ");

            if (vo.getTen() != null) {
                strSQL2 = new StringBuffer();
                strSQL2.append("ten = ? ");
                v_param.add(new Parameter(vo.getTen(), true));
            }
            if (vo.getId_cha() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("id_cha = ? ");
                } else {
                    strSQL2.append(", id_cha = ? ");
                }
                v_param.add(new Parameter(vo.getId_cha(), true));
            }
            if (vo.getMa_cha() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ma_cha = ? ");
                } else {
                    strSQL2.append(", ma_cha = ? ");
                }
                v_param.add(new Parameter(vo.getMa_cha(), true));
            }
            if (vo.getMa_h() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ma_h = ? ");
                } else {
                    strSQL2.append(", ma_h = ? ");
                }
                v_param.add(new Parameter(vo.getMa_h(), true));
            }
            if (vo.getMa_t() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ma_t = ? ");
                } else {
                    strSQL2.append(", ma_t = ? ");
                }
                v_param.add(new Parameter(vo.getMa_t(), true));
            }
            if (vo.getCap() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("cap = ? ");
                } else {
                    strSQL2.append(", cap = ? ");
                }
                v_param.add(new Parameter(vo.getCap(), true));
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

    public int insert(DMKBacVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        //Lay ID tu seq
        Long lID = getSeqNextValue("sp_dm_htkb_seq", conn);

        strSQL.append("insert into sp_dm_htkb (id ");
        strSQL2.append(") values (? ");
        v_param.add(new Parameter(lID, true));

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
        if (vo.getId_cha() != null) {
            strSQL.append(", id_cha");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getId_cha(), true));
        }
        if (vo.getMa_cha() != null) {
            strSQL.append(", ma_cha");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getMa_cha(), true));
        }
        if (vo.getMa_h() != null) {
            strSQL.append(", ma_h");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getMa_h(), true));
        }
        if (vo.getMa_t() != null) {
            strSQL.append(", ma_t");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getMa_t(), true));
        }
        if (vo.getTinhtrang() != null) {
            strSQL.append(", tinhtrang");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getTinhtrang(), true));
        }
        if (vo.getCap() != null) {
            strSQL.append(", cap");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getCap(), true));
        }

        strSQL.append(strSQL2);
        strSQL.append(")");
        return executeStatement(strSQL.toString(), v_param, conn);
    }

    /**
     * Lay danh sach KB Huyen
     * @param strWhere
     * @param vParam
     * @return
     * @throws Exception
     */
    public Collection getDMucKBHuyen(String strWhere,
                                     Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT distinct  a.ma, a.ten,e.ten ten_nh, a.id,a.ma_cha, a.id_cha, a.cap, d.ma_nh,e.ma_nh ma_nhang,substr(e.ma_nh,3,3) as ma_dv" +
                    " FROM   sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c, sp_dm_manh_shkb d, sp_dm_ngan_hang e " +
                    " WHERE  a.id=b.kb_id and a.id_cha=c.id and a.ma=d.shkb and b.nh_id=e.id ";
            strSQL += strWhere + " order by a.ma_cha";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_VO + ".getDMucKBHuyen(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    /**
     *Lay danh sach KB Tinh
     * @param strWhere
     * @param vParam
     * @return
     * @throws Exception
     */
    public Collection getDMucKB_cha(String strWhere,
                                    Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT   DISTINCT  a.id_cha, c.ten, c.ma FROM   sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c, sp_dm_ngan_hang d" +
                    " WHERE   1 = 1 AND a.id = b.kb_id AND a.id_cha = c.id AND d.id=b.nh_id";
            strSQL += strWhere + " order by ltrim(REPLACE(c.ten,'KBNN',''))";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_VO + ".getDMucKB_cha(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }
    

    //    public static void main(String[] args) {
    //        try {
    //            AppDAO a = new AppDAO(); //TTSP_KBA 130801 000006
    //            Connection conn = a.getConnectionTest();
    //            DMKBacDAO dm = new DMKBacDAO(conn);
    //            String ma = dm.getMaDVSDNSFromMaKB("0133");
    //            System.out.println(ma);
    //        } catch (Exception ex) {
    //            ex.printStackTrace();
    //        }
    //    }
}
