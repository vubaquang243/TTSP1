package com.seatech.ttsp.qlyhanmuc;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class QuanLyHanMucDAO extends AppDAO {

    String strValueObjectVO = "com.seatech.ttsp.dchieu.QuanLyHanMucVO";
    Connection conn = null;

    public QuanLyHanMucDAO(Connection conn) {
        this.conn = conn;
    }


    public Collection getHanMucList(String strWhere,
                                    Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL =
                " SELECT a.id, a.ma_kb, a.han_muc_du_no, a.ngay_tao, a.ngay_hieu_luc,\n" +
                "       a.ngay_het_hieu_luc, a.trang_thai\n" +
                "  FROM sp_qly_han_muc a WHERE 1=1 ";

            strSQL += strWhere;
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getHanMucList(): " +ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

}
