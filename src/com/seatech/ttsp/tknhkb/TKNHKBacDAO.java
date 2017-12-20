package com.seatech.ttsp.tknhkb;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

/**
 * @modify: thuongdt
 * @modify-date: 30/10/2017
 * @see: chuyen han han_muc_no,han_muc_co tu long sang float de thiet lap duoc so du le
 * @find: 20171030
 * */
public class TKNHKBacDAO extends AppDAO {
    private static String DD_MM_YYYY_HH_MI_SS = "dd/MM/yyyy HH:mm:ss";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.tknhkb.TKNHKBacDAO";
    Connection conn = null;
    private String strValueObject = "com.seatech.ttsp.tknhkb.TKNHKBacVO";

    public TKNHKBacDAO(Connection conn) {
        this.conn = conn;
    }
    /*
 *  CHU Y !!!
 * */

    public Collection getTK_NH_KB(String strWhere,
                                  Vector vParam) throws Exception {
        try {
            String strSQL =
                "SELECT a.id, a.kb_id, a.so_tk, a.nh_id, a.trang_thai, a.han_muc_no, a.quyet_toan, a.loai_gd," +
                " a.han_muc_co,to_char( a.hieu_luc_tungay,'dd/mm/yyyy') hieu_luc_tungay, to_char(a.hieu_luc_den_ngay,'dd/mm/yyyy')hieu_luc_den_ngay, b.ma_nh, b.ten , a.ma_nt, a.loai_tk" +
                " FROM sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c where a.nh_id=b.id and a.kb_id = c.id ";
            if (strWhere != null) {
                strSQL += strWhere;
            }
            return executeSelectStatement(strSQL, vParam, strValueObject,
                                          conn);
        } catch (Exception e) {
            throw e;
        }

    }

    public Collection getNH_KB(String strWhere,
                               Vector vParam) throws Exception {
        try {
            String strSQL =
                "SELECT DISTINCT(b.ma_nh), b.ten, a.loai_tk" + " FROM sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c where a.nh_id=b.id and a.kb_id = c.id ";
            if (strWhere != null) {
                strSQL += strWhere;
            }
            return executeSelectStatement(strSQL, vParam, strValueObject,
                                          conn);
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection getLT_NH_KB(String strWhere,
                                  Vector vParam) throws Exception {
        try {
            String strSQL =
                "SELECT DISTINCT a.ma_nt" + " FROM sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c where a.nh_id=b.id and a.kb_id = c.id ";
            if (strWhere != null) {
                strSQL += strWhere;
            }
            return executeSelectStatement(strSQL, vParam, strValueObject,
                                          conn);
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection getLstTK(String strWhere, Vector vParam, Integer page,
                               Integer count,
                               Integer[] totalCount) throws Exception {
        try {
            String strSQL =
                " SELECT	a.id id_tk, a.kb_id, c.id, b.shkb, a.ma_nt,  a.so_tk, b.ma_nh ma_kb, b.ten ten_kb, d.ma_nh, d.ten ten_nh, " +
                " a.han_muc_co, a.han_muc_no, a.loai_tk, a.loai_gd, a.ghi_chu, a.quyet_toan, " +
                " to_char( a.hieu_luc_tungay,'dd/mm/yyyy') hieu_luc_tungay, to_char(a.hieu_luc_den_ngay,'dd/mm/yyyy')hieu_luc_den_ngay, a.trang_thai" +
                " FROM	sp_tknh_kb a, sp_dm_manh_shkb b, sp_dm_htkb c, sp_dm_ngan_hang d" +
                " WHERE	b.shkb = c.ma AND a.kb_id = c.id AND a.nh_id = d.id" +
                strWhere + " order by b.shkb";

            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           strValueObject, page, count,
                                           totalCount);
        } catch (Exception e) {
            throw e;
        }

    }


    public boolean checkExist(String ma_nh, String so_tk) throws Exception {
        int rowcount = -1;
        try {
            String strSQL =
                "SELECT a.id " + " FROM sp_tknh_kb a where a.nh_id='" + ma_nh +
                "'  and a.so_tk='" + so_tk + "'";
            rowcount = executeStatement(strSQL.toString(), null, conn);
            if (rowcount > 0)
                return true;
            else
                return false;
        } catch (Exception e) {
            throw e;
        }

    }

    public int deletetTKNHKB(TKNHKBacVO vo) throws Exception {
        Vector params = new Vector();
        StringBuffer sqlbuff = null;
        try {
            sqlbuff =
                    new StringBuffer(" DELETE  sp_tknh_kb  a  WHERE a.id= ? ");
            params.add(new Parameter(vo.getId(), true));
            return executeStatement(sqlbuff.toString(), params, conn);
        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
            throw new DAOException(".delete(): " + ex.getMessage(), ex);
        }
    }

    public String insert(TKNHKBacVO vo) throws Exception {
        try {
            Vector v_param = new Vector();
            StringBuffer strSQL = new StringBuffer();
            StringBuffer strSQL2 = new StringBuffer();
            strSQL.append(" Insert into sp_tknh_kb (id ");
            strSQL2.append(" ) values ( ");
            strSQL2.append("? ");

            Long id = getSeqNextValue("sp_tknhkb_seq", conn);
            v_param.add(new Parameter(id, true));

            if (vo.getKb_id() != null) {
                strSQL.append(", kb_id");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getKb_id(), true));
            }
            if (vo.getSo_tk() != null) {
                strSQL.append(", so_tk");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getSo_tk(), true));
            }
            if (vo.getNh_id() != null) {
                strSQL.append(", nh_id");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getNh_id(), true));
            }
            if (vo.getTrang_thai() != null) {
                strSQL.append(", trang_thai");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getTrang_thai(), true));
            }
            if (vo.getLoai_gd() != null) {
                strSQL.append(", loai_gd");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getLoai_gd(), true));
            }
            if (vo.getLoai_tk() != null) {
                strSQL.append(", loai_tk");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getLoai_tk(), true));
            }
            //20171030
            if (vo.getHan_muc_no() != null) {
                strSQL.append(", han_muc_no");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getHan_muc_no(), true));
            }
            //20171030
            if (vo.getHan_muc_co() != null) {
                strSQL.append(", han_muc_co");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getHan_muc_co(), true));
            }
            if (vo.getHieu_luc_tungay() != null) {
                strSQL.append(", hieu_luc_tungay");
                strSQL2.append(", ?");
                v_param.add(new DateParameter(StringUtil.StringToDate(vo.getHieu_luc_tungay() +
                                                                      " 00:00:00",
                                                                      DD_MM_YYYY_HH_MI_SS),
                                              true));
            }
            if (vo.getHieu_luc_den_ngay() != null) {
                strSQL.append(", hieu_luc_den_ngay");
                strSQL2.append(", ?");
                v_param.add(new DateParameter(StringUtil.StringToDate(vo.getHieu_luc_den_ngay() +
                                                                      " 00:00:00",
                                                                      DD_MM_YYYY_HH_MI_SS),
                                              true));
            }
            if (vo.getMa_nt() != null) {
                strSQL.append(", ma_nt");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getMa_nt(), true));
            }
            if (vo.getQuyet_toan() != null) {
                strSQL.append(", quyet_toan");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getQuyet_toan(), true));
            }
            strSQL.append(strSQL2);
            strSQL.append(")");

            if (executeStatement(strSQL.toString(), v_param, conn) == 1)
                return "1";
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".insert: " + ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return "-1";
    }

    public int update(TKNHKBacVO vo) throws Exception {
        Vector v_param = new Vector();
        Parameter param = null;
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = null;
        strSQL.append("update sp_tknh_kb set ");
        try {
            if (vo.getTrang_thai() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("trang_thai = ?");
                } else {
                    strSQL2.append(", trang_thai = ?");
                }
                v_param.add(new Parameter(vo.getTrang_thai(), true));
            }
          //20171030
            if (vo.getHan_muc_no() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("han_muc_no = ?");
                } else {
                    strSQL2.append(", han_muc_no = ?");
                }
                v_param.add(new Parameter(vo.getHan_muc_no(), true));
            }
          //20171030
            if (vo.getHan_muc_co() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("han_muc_co = ?");
                } else {
                    strSQL2.append(", han_muc_co = ?");
                }
                v_param.add(new Parameter(vo.getHan_muc_co(), true));
            }
            if (vo.getLoai_tk() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("loai_tk = ?");
                } else {
                    strSQL2.append(", loai_tk = ?");
                }
                v_param.add(new Parameter(vo.getLoai_tk(), true));
            }
            if (vo.getLoai_gd() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("loai_gd = ?");
                } else {
                    strSQL2.append(", loai_gd = ?");
                }
                v_param.add(new Parameter(vo.getLoai_gd(), true));
            }
            if (vo.getSo_tk() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("so_tk = ?");
                } else {
                    strSQL2.append(", so_tk = ?");
                }
                v_param.add(new Parameter(vo.getSo_tk().trim(), true));
            }
            if (vo.getMa_nt() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ma_nt = ?");
                } else {
                    strSQL2.append(", ma_nt = ?");
                }
                v_param.add(new Parameter(vo.getMa_nt(), true));
            }
            if (vo.getQuyet_toan() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("quyet_toan = ?");
                } else {
                    strSQL2.append(", quyet_toan = ?");
                }
                v_param.add(new Parameter(vo.getQuyet_toan(), true));
            }
            if (vo.getHieu_luc_tungay() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("hieu_luc_tungay = ?");
                } else {
                    strSQL2.append(", hieu_luc_tungay = ?");
                }
                v_param.add(new DateParameter(StringUtil.StringToDate(vo.getHieu_luc_tungay() +
                                                                      " 00:00:00",
                                                                      DD_MM_YYYY_HH_MI_SS),
                                              true));
            }
            if (vo.getHieu_luc_den_ngay() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("hieu_luc_den_ngay = ?");
                } else {
                    strSQL2.append(", hieu_luc_den_ngay = ?");
                }
                v_param.add(new DateParameter(StringUtil.StringToDate(vo.getHieu_luc_den_ngay() +
                                                                      " 00:00:00",
                                                                      DD_MM_YYYY_HH_MI_SS),
                                              true));
            }
            if (strSQL2.toString() != "") {
                strSQL.append(strSQL2.toString());
                strSQL.append(" where  id= ?");
                v_param.add(new Parameter(vo.getId(), true));
                return executeStatement(strSQL.toString(), v_param, conn);
            } else
                return 0;


        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".update(): " +
                                   ex.getMessage(), ex);
        }
    }

    public TKNHKBacVO getTK_NH_KB_VO(String strWhere,
                                     Vector vParam) throws Exception {
        String strSQL =
            "SELECT a.id,a.ma_nt, a.kb_id, a.so_tk, a.nh_id, a.trang_thai, a.han_muc_no, a.loai_gd, quyet_toan, " +
            " a.han_muc_co,to_char( a.hieu_luc_tungay,'dd/mm/yyyy') hieu_luc_tungay, to_char(a.hieu_luc_den_ngay,'dd/mm/yyyy')hieu_luc_den_ngay, b.ma_nh, b.ten " +
            " FROM sp_tknh_kb a, sp_dm_ngan_hang b, sp_dm_htkb c where a.nh_id=b.id and a.kb_id = c.id ";
        if (strWhere != null) {
            strSQL += strWhere;
        }
        return (TKNHKBacVO)findByPK(strSQL, vParam, strValueObject, conn);
    }

    public TKNHKBacVO getTK_NH_KB_VO_2(String strWhere,
                                       Vector vParam) throws Exception {
        String strSQL =
            " /* Formatted on 7-Apr-2015 9:49:04 (QP5 v5.126) */ " +
            "SELECT  a.id, " + "        a.kb_id, " + "        a.so_tk, " +
            "        a.nh_id, " + "        a.trang_thai, " +
            "        a.han_muc_no, " + "        a.quyet_toan, " +
            "        a.loai_gd, " + "        a.han_muc_co, " +
            "        TO_CHAR (a.hieu_luc_tungay, 'dd/mm/yyyy') hieu_luc_tungay, " +
            "        TO_CHAR (a.hieu_luc_den_ngay, 'dd/mm/yyyy') hieu_luc_den_ngay, " +
            "        b.ma_nh, " + "        b.ten, " + "        a.ma_nt, " +
            "        a.loai_tk, " + "        c.ten ten_kb, " +
            "        d.ten_nh, " + "        e.ma_nh ma_kb " +
            "  FROM  sp_tknh_kb a, " + "        sp_dm_ngan_hang b, " +
            "        sp_dm_htkb c, " + "        sp_dm_nh_ho d, " +
            "        sp_dm_manh_shkb e " + " WHERE      a.nh_id = b.id " +
            "        AND a.kb_id = c.id " + "        AND e.shkb = c.ma " +
            "        AND SUBSTR (b.ma_nh, 3, 3) = d.ma_dv ";
        if (strWhere != null) {
            strSQL += strWhere;
        }
        return (TKNHKBacVO)findByPK(strSQL, vParam, strValueObject, conn);
    }

    public Collection getNganHangCoTaiKhoan(String condition,
                                            Vector params) throws Exception {
        String sql =
            "select distinct ten , ma_nh " + "from sp_dm_ngan_hang a " +
            "join sp_tknh_kb b " + "on a.id = b.nh_id " + "where 1=1 ";
        if (condition != null && !"".equals(condition)) {
            sql += condition;
        }
        return executeSelectStatement(sql, params, strValueObject, conn);
    }
}
