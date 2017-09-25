package com.seatech.ttsp.dmtiente;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.List;
import java.util.Vector;


public class DMTienTeDAO extends AppDAO {
    private Connection conn = null;
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.dmtiente.DMTienTeVO";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.dmtiente.DMTienTeDAO";

    public DMTienTeDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach Danh muc tien te
     * @return: Collection
     * */
    public Collection getDMTienTeList(String whereClause,
                                      Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ma, a.ten, a.ten_ta, a.ten_qg, a.ty_gia, a.ma_dang_so, ");
            strSQL.append("a.ghi_chu, a.tinh_trang ");
            strSQL.append("FROM sp_dm_tiente a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY a.ma ASC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMTienTeList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

        return reval;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra thong tin tien te
     * @return: DMTienTeVO
     * */
    public DMTienTeVO getDMTienTe(String whereClause,
                                  Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ma, a.ten, a.ten_ta, a.ten_qg, a.ty_gia, a.ma_dang_so, ");
            strSQL.append("a.ghi_chu, a.tinh_trang ");
            strSQL.append("FROM sp_dm_tiente a");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (DMTienTeVO)findByPK(strSQL.toString(), params,
                                        CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMTienTe(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }
  /**
   * @param: Menh de where; vector tham so
   * @see: Lay ra danh sach Danh muc tien te
   * @return: Collection
   * */
    public Collection getDMTienTeCuaDViList(String whereClause,
                                      Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT DISTINCT a.id, a.ma, a.ten, a.ten_ta, a.ten_qg, a.ty_gia, a.ma_dang_so, ");
            strSQL.append("a.ghi_chu, a.tinh_trang ");
            strSQL.append("FROM sp_dm_tiente a, sp_tknh_kb b WHERE a.ma = b.ma_nt ");
  
            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY decode(a.ma,'VND','A',a.ma) ASC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMTienTeList(): " +
                                 ex.getMessage(), ex);
    //            daoEx.printStackTrace();
            throw daoEx;
        }
  
        return reval;
    }
    /*
     * Chi lay ma ngoai te trong bang sp_tknh_kb tai khoan ngan hang kho bac
     */
    public List simpleMaNgoaiTe(String condition, Vector params) throws Exception{
        List result = null;
        StringBuilder strSQL = new StringBuilder();
        try {
            strSQL.append("SELECT DISTINCT(a.MA_NT) ma FROM sp_tknh_kb a WHERE 1=1 ");
            if (condition != null && !condition.equals("")) {
                strSQL.append(condition);
            }
            strSQL.append(" ORDER BY ma DESC ");
            result = (List)executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".simpleMaNgoaiTe(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return result;
    }
}
