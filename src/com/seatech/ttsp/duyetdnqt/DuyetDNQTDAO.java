package com.seatech.ttsp.duyetdnqt;

import com.seatech.framework.datamanager.AppDAO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

public class DuyetDNQTDAO extends AppDAO {
    private String strValueObject = "com.seatech.ttsp.duyetdnqt.DuyetDNQTVO";
    private Connection conn;

    public DuyetDNQTDAO(Connection conn) {
        this.conn = conn;
    }

    public Collection getData(String sqlWhere, Vector params, Integer page, Integer count,
                              Integer[] totalCount) throws Exception {
        try {
            String strSQL =
                "select a.id soLenh, b.ten tenNH, a.qtoan_thu quyetToanThu, " +
                "a.qtoan_chi quyetToanChi, a.loai_tien loaiTien, a.loai_qtoan loaiQuyetToan, " +
                "a.ndung_qtoan noiDung from sp_066 a left join sp_dm_ngan_hang b ON a.nhkb_nhan = b.ma_nh " +
                "left join sp_tknh_kb c ON c.nh_id = b.ma_nh " +
                "WHERE a.trang_thai = 01 AND loai_qtoan IN ('04','05','06','07') ";

            if (sqlWhere.trim().length() > 0) {
                strSQL += sqlWhere;
            }
            return executeSelectWithPaging(conn, strSQL.toString(), params,
                                           strValueObject, page, count,
                                           totalCount);
        } catch (Exception e) {
            throw e;
        }
    }

    public int updateData(String id,String nguoiKS, Vector params) throws Exception {
        String query = "Update sp_066 Set trang_thai = '02', ngay_ks = sysdate, nguoi_ks = '"+nguoiKS+"'  Where id = " + id;
        try {
            return (int)(executeStatement(query, params, conn));
        } catch (Exception e) {
            throw e;
        }
    }

    public int changeStatusSp066(String id, Vector params) throws Exception {
        String query = "Update sp_066 set trang_thai = '03' Where id = " + id;
        try {
            return (int)(executeStatement(query, params, conn)) > 0 ? 1 : 0;
        } catch (Exception e) {
            throw e;
        }
    }

}
