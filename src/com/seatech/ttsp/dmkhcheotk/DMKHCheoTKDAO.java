package com.seatech.ttsp.dmkhcheotk;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class DMKHCheoTKDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.dmkhcheotk.DMKHCheoTKDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.dmkhcheotk.DMKHCheoTKVO";

    public DMKHCheoTKDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach DM
     * @return: Collection
     * */
    public Collection getDMKHCheoTKList(String whereClause,
                                        Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.tk, a.ma_cap, a.ma_chuong, a.ma_nganh, a.ma_ndkt, a.ma_dvsdns, ");
            strSQL.append("a.ma_diaban, a.ma_quy, a.ma_ctmt, a.ma_nguon, a.ma_dphong ");
            strSQL.append("FROM sp_dm_kh_cheo_tk a ");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }

            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMKHCheoTKList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

        return reval;
    }

    public DMKHCheoTKVO getTKByID(String id) throws Exception {
        DMKHCheoTKVO vo = null;
        Vector v_param = null;
        StringBuffer sbSql = null;
        try {
            sbSql = new StringBuffer();
            v_param = new Vector();
            sbSql.append("select * from  sp_dm_kh_cheo_tk a where a.tk = ?");
            v_param.add(new Parameter(id, true));
            vo =
 (DMKHCheoTKVO)findByPK(sbSql.toString(), v_param, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".FindByKey(): " +
                                   ex.getMessage(), ex);
        }
        return vo;
    }

    public Collection getDMKHCheoTKListWithPaging(String whereClause,
                                                  Vector params, Integer page,
                                                  Integer count,
                                                  Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.tk, a.ma_cap, a.ma_chuong, a.ma_nganh, a.ma_ndkt, a.ma_dvsdns, ");
            strSQL.append("a.ma_diaban, a.ma_quy, a.ma_ctmt, a.ma_nguon, a.ma_dphong ");
            strSQL.append("FROM sp_dm_kh_cheo_tk a ");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            } else {
                strSQL.append(" order by a.tk desc ");
            }

            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), params, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMKHCheoTKList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

        return reval;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra thong tin 1 chuc nang
     * @return: ChucNangVO
     * */
    public DMKHCheoTKVO getDMKHCheoTK(String whereClause,
                                      Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.tk, a.ma_cap, a.ma_chuong, a.ma_nganh, a.ma_ndkt, a.ma_dvsdns, ");
            strSQL.append("a.ma_diaban, a.ma_quy, a.ma_ctmt, a.ma_nguon, a.ma_dphong ");
            strSQL.append("FROM sp_dm_kh_cheo_tk a ");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (DMKHCheoTKVO)findByPK(strSQL.toString(), params,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMKHCheoTK(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    /**
     *xoa tk
     * @param id
     * @throws Exception
     * 1: xoa thanh cong ; -1: xoa khong thanh cong
     */
    public int delete(String id) throws Exception {
        int i = 0;
        Vector v_param = null;
        StringBuffer sbSql = null;
        try {
            sbSql = new StringBuffer();
            v_param = new Vector();
            sbSql.append("delete sp_dm_kh_cheo_tk a where a.tk = ?");
            v_param.add(new Parameter(id, true));
            i = executeStatement(sbSql.toString(), v_param, conn);
        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".delete(): " +
                                   ex.getMessage(), ex);
        }
        return i;
    }

    public int update(DMKHCheoTKVO vo) throws Exception {
        int result = 0;
        Vector v_param = new Vector();
        Parameter param = null;
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = null;
        strSQL.append("update sp_dm_kh_cheo_tk set ");
        try {
            if (vo.getMa_cap() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" ma_cap = ?");
                } else {
                    strSQL2.append(" ,ma_cap = ?");
                }
                v_param.add(new Parameter(vo.getMa_cap(), true));
            }
            if (vo.getMa_chuong() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" ma_chuong = ?");
                } else {
                    strSQL2.append(" ,ma_chuong = ?");
                }
                v_param.add(new Parameter(vo.getMa_chuong(), true));
            }
            if (vo.getMa_nganh() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" ma_nganh = ?");
                } else {
                    strSQL2.append(" ,ma_nganh = ?");
                }
                v_param.add(new Parameter(vo.getMa_nganh(), true));
            }
            if (vo.getMa_ndkt() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" ma_ndkt = ?");
                } else {
                    strSQL2.append(" ,ma_ndkt = ?");
                }
                v_param.add(new Parameter(vo.getMa_ndkt(), true));
            }
            if (vo.getMa_dvsdns() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" ma_dvsdns = ?");
                } else {
                    strSQL2.append(" ,ma_dvsdns = ?");
                }
                v_param.add(new Parameter(vo.getMa_dvsdns(), true));
            }
            if (vo.getMa_diaban() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" ma_diaban = ?");
                } else {
                    strSQL2.append(" ,ma_diaban = ?");
                }
                v_param.add(new Parameter(vo.getMa_diaban(), true));
            }
            if (vo.getMa_quy() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" ma_quy = ?");
                } else {
                    strSQL2.append(" ,ma_quy = ?");
                }
                v_param.add(new Parameter(vo.getMa_quy(), true));
            }
            if (vo.getMa_ctmt() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" ma_ctmt = ?");
                } else {
                    strSQL2.append(" ,ma_ctmt = ?");
                }
                v_param.add(new Parameter(vo.getMa_ctmt(), true));
            }
            if (vo.getMa_nguon() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" ma_nguon = ?");
                } else {
                    strSQL2.append(" ,ma_nguon = ?");
                }
                v_param.add(new Parameter(vo.getMa_nguon(), true));
            }
            if (vo.getMa_dphong() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" ma_dphong = ?");
                } else {
                    strSQL2.append(" ,ma_dphong = ?");
                }
                v_param.add(new Parameter(vo.getMa_dphong(), true));
            }
            if (vo.getTk() != null) {
              
                strSQL2.append(" where tk = ?");             
                v_param.add(new Parameter(vo.getTk(), true));
            }
            strSQL.append(strSQL2.toString());
            result = executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".update(): " +
                                   ex.getMessage(), ex);
        }
        return result;
    }

    public String insert(DMKHCheoTKVO vo) throws Exception {
        String result = "";
        StringBuffer sbQuery = new StringBuffer();
        StringBuffer sbQuery2 = new StringBuffer();
        Vector v_param = new Vector();
        try {
            if (vo.getTk() != null && !"".equals(vo.getTk().trim())) {
                sbQuery.append("insert into sp_dm_kh_cheo_tk (tk");
                sbQuery2.append(") values (");
                sbQuery2.append("?");
                v_param.add(new Parameter(vo.getTk().trim(), true));
            }
            if (vo.getMa_cap() != null && !"".equals(vo.getMa_cap())) {
                sbQuery.append(",ma_cap");
                sbQuery2.append(",?");
                v_param.add(new Parameter(vo.getMa_cap().toString(), true));
            }
            if (vo.getMa_chuong() != null && !"".equals(vo.getMa_chuong())) {
                sbQuery.append(",ma_chuong");
                sbQuery2.append(",?");
                v_param.add(new Parameter(vo.getMa_chuong().toString(), true));
            }
            if (vo.getMa_nganh() != null && !"".equals(vo.getMa_nganh())) {
                sbQuery.append(",ma_nganh");
                sbQuery2.append(",?");
                v_param.add(new Parameter(vo.getMa_nganh().toString(), true));
            }
            if (vo.getMa_ndkt() != null && !"".equals(vo.getMa_ndkt())) {
                sbQuery.append(",ma_ndkt");
                sbQuery2.append(",?");
                v_param.add(new Parameter(vo.getMa_ndkt().toString(), true));
            }
            if (vo.getMa_dvsdns() != null && !"".equals(vo.getMa_dvsdns())) {
                sbQuery.append(",ma_dvsdns");
                sbQuery2.append(",?");
                v_param.add(new Parameter(vo.getMa_dvsdns().toString(), true));
            }
            if (vo.getMa_diaban() != null && !"".equals(vo.getMa_diaban())) {
                sbQuery.append(",ma_diaban");
                sbQuery2.append(",?");
                v_param.add(new Parameter(vo.getMa_diaban().toString(), true));
            }
            if (vo.getMa_quy() != null && !"".equals(vo.getMa_quy())) {
                sbQuery.append(",ma_quy");
                sbQuery2.append(",?");
                v_param.add(new Parameter(vo.getMa_quy(), true));
            }
            if (vo.getMa_ctmt() != null && !"".equals(vo.getMa_ctmt())) {
                sbQuery.append(",ma_ctmt");
                sbQuery2.append(",?");
                v_param.add(new Parameter(vo.getMa_ctmt(), true));
            }
            if (vo.getMa_nguon() != null && !"".equals(vo.getMa_nguon())) {
                sbQuery.append(",ma_nguon");
                sbQuery2.append(",?");
                v_param.add(new Parameter(vo.getMa_nguon(), true));
            }
            if (vo.getMa_dphong() != null && !"".equals(vo.getMa_dphong())) {
                sbQuery.append(",ma_dphong");
                sbQuery2.append(",?");
                v_param.add(new Parameter(vo.getMa_dphong(), true));
            }
            sbQuery.append(sbQuery2.toString());
            sbQuery.append(")");
            if (executeStatement(sbQuery.toString(), v_param, conn) == 1) {
                result = "inserted";
            } else {
                result = "failure";
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".insert(): " +
                                   ex.getMessage(), ex);
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            AppDAO a = new AppDAO();
            Connection c = a.getConnectionTest();
            DMKHCheoTKDAO dm = new DMKHCheoTKDAO(c);
            Vector p = new Vector();
            p.add(new Parameter("1187", true));
            DMKHCheoTKVO vo = dm.getDMKHCheoTK("a.tk = ?", p);

            c.close();
        } catch (Exception e) {
            // TODO: Add catch code
//            e.printStackTrace();
        }
    }
}

