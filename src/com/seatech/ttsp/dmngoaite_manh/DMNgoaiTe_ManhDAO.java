package com.seatech.ttsp.dmngoaite_manh;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.Collection;
import java.util.Vector;


public class DMNgoaiTe_ManhDAO extends AppDAO {
    Connection conn = null;
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.dmngoaite_manh.DMNgoaiTe_ManhDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.dmngoaite_manh.DMNgoaiTe_ManhVO";

    public DMNgoaiTe_ManhDAO(Connection conn) {
        this.conn = conn;
    }

    //Lấy danh sách ngoại tệ manh có phân trang

    public Collection getNgoai_te(String strWhere, Vector vParam, Integer page,
                                  Integer count,
                                  Integer[] totalCount) throws Exception {
        try {
            String strSQL =
                " select a.id, a.ma, a.nguoi_tao, TO_CHAR(a.ngay_hieu_luc,'dd/mm/yyyy') ngay_hieu_luc, a.nguoi_ngung_hd, TO_CHAR(a.ngay_tao,'dd/mm/yyyy') ngay_tao, TO_CHAR(a.ngay_het_hieu_luc,'dd/mm/yyyy') ngay_het_hieu_luc, a.trang_thai, b.ma_nsd nt, c.ma_nsd nhd from sp_dm_ngoai_te_manh a, sp_nsd b, sp_nsd c where a.nguoi_tao = b.id(+) and a.nguoi_ngung_hd = c.id(+) and a.trang_thai='00' ";
            strSQL += strWhere + " ORDER BY a.nguoi_tao";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           CLASS_NAME_VO, page, count,
                                           totalCount);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getNgoai_te(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }

    //Lấy danh sách ngoại tệ mạnh không phân trang

    public Collection getNgoai_te(String strWhere,
                                  Vector vParam) throws Exception {
        try {
            String strSQL =
                " select a.id, a.ma, a.nguoi_tao, TO_CHAR(a.ngay_hieu_luc,'dd/mm/yyyy') ngay_hieu_luc, TO_CHAR(a.ngay_het_hieu_luc,'dd/mm/yyyy') ngay_het_hieu_luc, a.nguoi_ngung_hd, TO_CHAR(a.ngay_tao,'dd/mm/yyyy') ngay_tao,  a.trang_thai, b.ma_nsd nt, c.ma_nsd nhd from sp_dm_ngoai_te_manh a, sp_nsd b, sp_nsd c where a.nguoi_tao = b.id(+) and a.nguoi_ngung_hd = c.id(+) and a.trang_thai='00' ";
            strSQL += strWhere + " ORDER BY a.nguoi_tao ";
            return executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getNgoai_te(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }

    //    Insert danh muc

    public long insert(DMNgoaiTe_ManhVO vo) throws Exception {
        long nt = 0;
        String strSQL = "";
        String strSQL1 = "";
        try {
            strSQL =
                    " insert into sp_dm_ngoai_te_manh(id, ma, nguoi_tao, ngay_tao, ngay_hieu_luc, trang_thai) values (sp_dm_ngoai_te_manh_seq.nextval, ?, ?, sysdate, to_date(?,'dd/mm/yyyy'),'00') ";
            PreparedStatement pstt = conn.prepareStatement(strSQL);
            pstt.setString(1, vo.getMa());
            pstt.setString(2, vo.getNguoi_tao());
            pstt.setString(3, vo.getNgay_hieu_luc());
            nt = pstt.executeUpdate();

            strSQL1 =
                    " update sp_tknh_kb set quyet_toan = 'Y' where ma_nt = ? ";
            PreparedStatement pst = conn.prepareStatement(strSQL1);
            pst.setString(1, vo.getMa());

        } catch (Exception e) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getNgoai_te(): " +
                                 e.getMessage(), e);
            throw daoEx;
        }
        return nt;
    }
    //Update danh muc

    public long update(DMNgoaiTe_ManhVO vo) throws Exception {
        long nt = 0;
        String strSQL = "";
        String strSQL1 = "";
        try {
            strSQL =
                    "update sp_dm_ngoai_te_manh set ngay_het_hieu_luc = to_date(?,'dd/mm/yyyy'), ma =?, nguoi_ngung_hd = ?, trang_thai = '01' where id =?";
            PreparedStatement pstt = conn.prepareStatement(strSQL);
            pstt.setString(1, vo.getNgay_het_hieu_luc());
            pstt.setString(2, vo.getMa());
            pstt.setString(3, vo.getNguoi_ngung_hd());
            pstt.setString(4, vo.getId());
            nt = pstt.executeUpdate();

            strSQL1 =
                    " update sp_tknh_kb set quyet_toan = 'N' where ma_nt = ? ";
            PreparedStatement pst = conn.prepareStatement(strSQL1);
            pst.setString(1, vo.getMa());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage() + "error at : " + strSQL);
        }
        return nt;
    }

    public Collection getMaNT(String whereClause,
                              Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" select a.id, a.ma, a.nguoi_tao, TO_CHAR(a.ngay_hieu_luc,'dd/mm/yyyy') ngay_hieu_luc, TO_CHAR(a.ngay_het_hieu_luc,'dd/mm/yyyy') ngay_het_hieu_luc, a.nguoi_ngung_hd, TO_CHAR(a.ngay_tao,'dd/mm/yyyy') ngay_tao,  a.trang_thai, b.ma_nsd nt, c.ma_nsd nhd from sp_dm_ngoai_te_manh a, sp_nsd b, sp_nsd c where a.nguoi_tao = b.id(+) and a.nguoi_ngung_hd = c.id(+) and a.trang_thai='00' ");


            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY a.ma ASC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getMaNT(): " +
                                 ex.getMessage(), ex);
            //            daoEx.printStackTrace();
            throw daoEx;
        }

        return reval;
    }
}
