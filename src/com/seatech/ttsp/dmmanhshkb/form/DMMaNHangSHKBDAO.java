package com.seatech.ttsp.dmmanhshkb.form;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.Collection;
import java.util.Vector;


public class DMMaNHangSHKBDAO extends AppDAO {
    Connection conn = null;
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.dmmanhshkb.form.DMMaNHangSHKBDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.dmmanhshkb.form.DMMaNHangSHKBVO";

    public DMMaNHangSHKBDAO(Connection conn) {
        this.conn = conn;
    }
    //Lay danh sach danh muc co phan trang
    public Collection getLstDMuc(String strWhere, Vector vParam, Integer page,
                                 Integer count,
                                 Integer[] totalCount) throws Exception {
        try {

            String strSQL =
                "select ma_nh, shkb, ten from sp_dm_manh_shkb where 1=1 ";
            strSQL += strWhere + " ORDER BY shkb";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           CLASS_NAME_VO, page, count,
                                           totalCount);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLstDMuc(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }
    //Lay danh sach danh muc khong phan trang
    public Collection getLstDMuc(String strWhere,
                                 Vector vParam) throws Exception {
        try {

            String strSQL =
                "select ma_nh, shkb, ten from sp_dm_manh_shkb where 1=1 ";
            strSQL += strWhere + " ORDER BY shkb";
            return executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLstDMuc(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }
//    Insert danh muc
    public long insert(DMMaNHangSHKBVO vo) throws Exception {
        long iu = 0;
        String strSQL = "";
        try {
            strSQL =
                    "insert into sp_dm_manh_shkb(shkb,ma_nh,ten) values (?,?,?)";
            PreparedStatement pstt = conn.prepareStatement(strSQL);
            pstt.setString(1, vo.getShkb());
            pstt.setString(2, vo.getMa_nh());
            pstt.setString(3, vo.getTen());
            iu = pstt.executeUpdate();
        } catch (Exception e) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLstDMuc(): " +
                                 e.getMessage(), e);
            throw daoEx;
        }
        return iu;
    }
  //Update danh muc
    public long update(DMMaNHangSHKBVO vo) throws Exception {
        long iu = 0;
        String strSQL = "";
        try {
            strSQL =
                    "update sp_dm_manh_shkb set  ten = ?, ma_nh =? where  shkb = ?";
            PreparedStatement pstt = conn.prepareStatement(strSQL);
            pstt.setString(1, vo.getTen());
            pstt.setString(2, vo.getMa_nh());
            pstt.setString(3, vo.getShkb());
            iu = pstt.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + "error at : " + strSQL);
        }
        return iu;
    }
    //Xoa danh muc
    public long delete(DMMaNHangSHKBVO vo) throws Exception {
        long iu = 0;
        String strSQL = "";
        try {
            strSQL = "delete sp_dm_manh_shkb where shkb = ? and ma_nh = ?";
            PreparedStatement pstt = conn.prepareStatement(strSQL);
            pstt.setString(1, vo.getShkb());
            pstt.setString(2, vo.getMa_nh());
            iu = pstt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage() + "error at : " + strSQL);
        }
        return iu;
    }
}


