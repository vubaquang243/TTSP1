package com.seatech.ttsp.duyetdnqt;

import com.seatech.framework.datamanager.AppDAO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;
/**
 * @create: QuangVB
 * @create-date: 09/2017
 * @see: them moi Class dùng cho việc duyệt đè nghị quyết toán
 */
/**
 * @modify: QuangVB
 * @modify-date: 01/12/2017
 * @see: them nguoi tao khi tra cuu select dien 066
 * @find: 20171201
 * */
public class DuyetDNQTDAO extends AppDAO {
    private String strValueObject = "com.seatech.ttsp.duyetdnqt.DuyetDNQTVO";
    private Connection conn;

    public DuyetDNQTDAO(Connection conn) {
        this.conn = conn;
    }

    public Collection getData(String sqlWhere, Vector params, Integer page, Integer count,
                              Integer[] totalCount) throws Exception {
        try {
            //20171201
            String strSQL ="select distinct a.id soLenh, b.ten tenNH, a.qtoan_thu quyetToanThu, " +
                "a.qtoan_chi quyetToanChi, a.loai_tien loaiTien, a.loai_qtoan loaiQuyetToan, " +
                "a.ndung_qtoan noiDung, to_char(ngay_tao,'dd/mm/yyyy') ngay_tao, a.trang_thai, " +
                "a.nguoi_tao from sp_066 a,sp_dm_ngan_hang b,sp_tknh_kb c,sp_dm_htkb d where " +
                "a.nhkb_nhan = b.ma_nh and a.trang_thai = 01 AND a.loai_qtoan IN ('04','05','06','07') " +
                "and c.nh_id=b.id and d.id=c.kb_id and to_date(a.ngay_tao,'dd/mm/yyyy') = to_date(sysdate,'dd/mm/yyyy') ";
            if (sqlWhere.trim().length() > 0) {
                strSQL += sqlWhere;
            }
            strSQL+=" order by a.id desc";
            return executeSelectWithPaging(conn, strSQL.toString(), params,
                                           strValueObject, page, count,
                                           totalCount);
        } catch (Exception e) {
            throw e;
        }
    }

    public int updateData(String id, String nguoiKS, String msg_id, Vector params) throws Exception {
        String query = "Update sp_066 Set trang_thai = '02', ngay_ks = sysdate, nguoi_ks = '"+nguoiKS+"', msg_id='"+msg_id+"'  Where id = " + id;
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
    
    public void changeTrangThaiHetHieuLuc(Vector params) throws Exception{
        String query = "UPDATE SP_066 SET TRANG_THAI='06' WHERE to_date(ngay_tao,'dd/mm/yyyy') = to_date(sysdate-1,'dd/mm/yyyy')";
      try {
          executeStatement(query, params, conn);
      } catch (Exception e) {
          throw e;
      }
    }
    
}
