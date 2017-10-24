package com.seatech.ttsp.ngaytrienkhai;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.Collection;
import java.util.Vector;


public class NgayTrienKhaiDAO extends AppDAO {
    Connection conn = null;
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.ngaytrienkhai.NgayTrienKhaiDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.ngaytrienkhai.NgayTrienKhaiVO";

    public NgayTrienKhaiDAO(Connection conn) {
        this.conn = conn;
    }
    //Lay danh sach ngay trien khai co phan trang

    public Collection getLstNTrKhai(String strWhere, Vector vParam,
                                    Integer page, Integer count,
                                    Integer[] totalCount) throws Exception {
        try {

            String strSQL =
                "select ma_kb, ma_nh, ma_nt, TO_CHAR(ngay_trien_khai,'dd/mm/yyyy') ngay_trien_khai, ghi_chu, id from sp_ngay_trien_khai where 1=1 ";
            strSQL += strWhere + " ORDER BY ma_nh";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           CLASS_NAME_VO, page, count,
                                           totalCount);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLstNTrKhai(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }
    //Lay danh sach ngay trien khai khong phan trang

    public Collection getLstNTrKhai(String strWhere,
                                    Vector vParam) throws Exception {
        try {

            String strSQL =
                "select ma_kb, ma_nh, ma_nt, TO_CHAR(ngay_trien_khai,'dd/mm/yyyy') ngay_trien_khai, ghi_chu, id from sp_ngay_trien_khai where 1=1 ";
            strSQL += strWhere + " ORDER BY ma_nh";
            return executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLstNTrKhai(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }

    //Update danh muc

    public long update(NgayTrienKhaiVO vo) throws Exception {
        long ta = 0;
        String strSQL = "";
        try {
            strSQL =
                    "update sp_ngay_trien_khai set ngay_trien_khai = to_date(?,'dd/mm/yyyy'), ghi_chu =? where id =?";
            PreparedStatement pstt = conn.prepareStatement(strSQL);
            pstt.setString(1, vo.getNgay_trien_khai());
            pstt.setString(2, vo.getGhi_chu());
            pstt.setString(3, vo.getId());
            ta = pstt.executeUpdate();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + "error at : " + strSQL);
        }
        return ta;
    }
    //    Insert danh muc

    public long insert(NgayTrienKhaiVO vo) throws Exception {
        long ta = 0;
        String strSQL = "";
        try {
            strSQL =
                    "insert into sp_ngay_trien_khai(ma_kb, ma_nh, ma_nt, ngay_trien_khai, ghi_chu, id) values (?,?,?,TO_DATE(?,'dd/mm/yyyy'),?,sp_ngay_trien_khai_seq.nextval)";
            PreparedStatement pstt = conn.prepareStatement(strSQL);
            pstt.setString(1, vo.getMa_kb());
            pstt.setString(2, vo.getMa_nh());
            pstt.setString(3, vo.getMa_nt());
            pstt.setString(4, vo.getNgay_trien_khai());
            pstt.setString(5, vo.getGhi_chu());
            
            ta = pstt.executeUpdate();
        } catch (Exception e) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLstNTrKhai(): " +
                                 e.getMessage(), e);
            throw daoEx;
        }
        return ta;
    }
    
    
    
    
    //xóa danh mục
    public long delete(NgayTrienKhaiVO vo) throws Exception {
        long ta = 0;
        String strSQL = "";
        try {
            strSQL = "delete sp_ngay_trien_khai where id = ?";
            PreparedStatement pstt = conn.prepareStatement(strSQL);
            pstt.setString(1, vo.getId());

            ta = pstt.executeUpdate();
        } catch (Exception e) {
            throw new Exception(e.getMessage() + "error at : " + strSQL);
        }
        return ta;
    }
}
