package com.seatech.ttsp.dmnhkb;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import com.seatech.ttsp.dmnh.DMNHangVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class DMNHKBacDAO extends AppDAO {
    private String CLASS_NAME_VO = "com.seatech.ttsp.dmnhkb.DMNHKBacVO";
    private String CLASS_NAME_HTKBVO = "com.seatech.ttsp.dmnhkb.DMHTKBacVO";
    private String CLASS_NAME_DAO = "com.seatech.ttsp.dmnh.DMNHKBacDAO";
    Connection conn = null;

    public DMNHKBacDAO(Connection conn) {
        this.conn = conn;
    }

    public DMHTKBacVO getDMHTKBac(String whereClause,
                                  Vector params) throws Exception {
        DMHTKBacVO vo = null;
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT * FROM sp_dm_htkb ");
            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            vo =
 (DMHTKBacVO)findByPK(strSQL.toString(), params, CLASS_NAME_HTKBVO, conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".isTTTT(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
        return vo;
    }

    public DMNHKBacVO getDMNHKBac(String whereClause,
                                  Vector params) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT a.ma_nh, a.shkb, a.ten FROM sp_dm_manh_shkb a ");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            DMNHKBacVO dmVO =
                (DMNHKBacVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                     conn);
            return dmVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMNHKBac(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public Collection getDMNHKBacList(String whereClause,
                                      Vector params) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT a.ma_nh, a.shkb, a.ten FROM sp_dm_manh_shkb a ");

            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }

            return executeSelectStatement(strSQL.toString(), params,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDMNHKBac(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public int insert(DMNHKBacVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("insert into sp_dm_manh_shkb (ma_nh, shkb, ten) values(?,?,?)");

        v_param.add(new Parameter(vo.getMa_nh(), true));
        v_param.add(new Parameter(vo.getShkb(), true));
        v_param.add(new Parameter(vo.getTen(), true));

        return executeStatement(strSQL.toString(), v_param, conn);
    }
}
