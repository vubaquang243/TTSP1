package com.seatech.ttsp.qtoanbu;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.utils.TTSPUtils;

import java.sql.Connection;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Vector;
/*
 * @modify: ThuongDT
 * @modify date: 13/02/2017
 * @see:them moi ham getQToanBu dung tim kiem thong tin bang sp_066 kem theo dieu kien where
 * @find: 20170213
 * */
public class QToanBuDAO extends AppDAO {
    private static final String CLASS_NAME_DAO = "com.seatech.ttsp.qtoanbu.QToanBuDAO";
    private static final String CLASS_QTOAN_VO = "com.seatech.ttsp.qtoanbu.QToanBuVO";
    private Connection conn;
    
    public QToanBuDAO(Connection conn){
        this.conn = conn;
    }
	
	
	  /*
   * @see: tim kiem thong tin bang sp_066 kem theo dieu kien where
   * @find: 20170213
   * */
  public QToanBuVO getQToanBu(String whereClause, Vector params) throws Exception{
    String query = "SELECT TO_CHAR(NGAY_QTOAN,'DD/MM/YYYY') NGAY_QTOAN ,"+
                   " NHKB_NHAN ma_nh,LOAI_TIEN, NHKB_CHUYEN ma_kb, "+
                   " QTOAN_THU,QTOAN_CHI,TRANG_THAI FROM SP_066 " + 
      "WHERE 1=1 "+whereClause;
    return (QToanBuVO)findByPK(query, params, CLASS_QTOAN_VO, conn);
  }
  
    
    public QToanBuVO getQToanBuByID(Vector params) throws Exception{
      String query = "SELECT TO_CHAR(NGAY_QTOAN,'DD/MM/YYYY') NGAY_QTOAN ,"+
                     " NHKB_NHAN ma_nh,LOAI_TIEN, NHKB_CHUYEN ma_kb, "+
                     " QTOAN_THU,QTOAN_CHI,TRANG_THAI FROM SP_066 " + 
        "WHERE ID = ?";
      return (QToanBuVO)findByPK(query, params, CLASS_QTOAN_VO, conn);
    }
    
    public Collection getListQToanBu(String condition, Vector params)throws Exception{
        StringBuilder query = new StringBuilder();
        query.append("/* Formatted on 10/10/2014 1:56:34 PM (QP5 v5.126) */ " + 
        "  SELECT  TO_CHAR (e.ngay_gd, 'DD/MM/YYYY') ngay_gd, " + 
        "          f.bi_danh ten_nh, " + 
        "          e.ma_nh, " + 
        "          a.so_tk, " + 
        "          a.ma_nt loai_tien, " + 
        "          c.ma_nh ma_kb, " + 
        "          b.ten ten_kb, " +
        "          c.shkb, " + 
        "          a.han_muc_no, " + 
        "          e.so_du, " + 
        "          e.so_du - a.han_muc_no qtoan_bu_thu, " + 
        "          a.han_muc_no - e.so_du qtoan_bu_chi " + 
        "    FROM  sp_tknh_kb a, " + 
        "          sp_dm_htkb b, " + 
        "          sp_dm_manh_shkb c, " + 
        "          sp_dm_ngan_hang d, " + 
        "          sp_so_du e, " + 
        "          sp_dm_nh_ho f, " + 
        "          sp_066 g " + 
        "   WHERE      a.kb_id = b.id " + 
        "          AND b.ma = c.shkb " + 
        "          AND a.nh_id = d.id " + 
        "          AND a.loai_tk <> '01' " + 
        "          AND c.ma_nh = e.ma_kb " + 
        "          AND d.ma_nh = e.ma_nh " + 
        "          AND SUBSTR (e.ma_nh, 3, 3) = f.ma_dv " + 
        "          AND e.ma_nh = g.nhkb_nhan " + 
        "          AND c.ma_nh = g.nhkb_chuyen " + 
        "          AND a.ma_nt = e.loai_tien " + 
        "          AND a.ma_nt = g.loai_tien " +
        "          AND e.ngay_gd = g.ngay_qtoan" + 
        "          AND TRUNC (e.ngay_gd) = TRUNC (SYSDATE) " + 
        "          AND (e.so_du - a.han_muc_no > a.han_muc_co OR e.so_du < a.han_muc_no) " + 
        "          AND g.loai_qtoan = '01' " + 
        "          AND g.trang_thai_qtoan = '02' " + 
        "          AND (e.so_du_cot " + 
        "               - (SELECT  NVL(sum(decode(loai_qtoan,'D', (-1*so_tien),so_tien)),0) " + 
        "                    FROM  sp_quyet_toan " + 
        "                   WHERE      ngay_qtoan = TRUNC (SYSDATE) " + 
        "                          AND ma_nh_chuyen = e.ma_nh AND lai_phi in ('01','03') AND qtoan_dvi = 'Y' AND ma_nt = e.loai_tien " + 
        "                          AND ma_kb = e.ma_kb) " + 
        "               - a.han_muc_no > a.han_muc_co " + 
        "               OR e.so_du_cot " + 
        "                 - (SELECT  NVL(sum(decode(loai_qtoan,'D', (-1*so_tien),so_tien)),0) " + 
        "                      FROM  sp_quyet_toan " + 
        "                     WHERE      ngay_qtoan = TRUNC (SYSDATE) " + 
        "                            AND ma_nh_chuyen = e.ma_nh AND lai_phi in ('01','03') AND qtoan_dvi = 'Y' AND ma_nt = e.loai_tien " + 
        "                            AND ma_kb = e.ma_kb) < a.han_muc_no) ");
        if(condition != null && !"".equals(condition))
            query.append(condition);
        query.append(" ORDER BY e.NGAY_GD,SUBSTR (e.ma_nh, 3, 3),a.ma_nt ");
        return executeSelectStatement(query.toString(), params, CLASS_QTOAN_VO, conn);
    }
    
    public String getQToanThuChi(String condition, Vector params)throws Exception{
        StringBuilder query = new StringBuilder("SELECT NVL(SUM(SO_TIEN),0) SO_TIEN" + 
        "  FROM  sp_quyet_toan " + 
        "  WHERE  qtoan_dvi = 'Y'  " + 
        "  AND loai_qtoan = ? " + 
        "  AND lai_phi <> '02' " + 
        "  AND ngay_qtoan = to_date(?,'DD/MM/YYYY')" + 
        "  AND ma_kb = ? AND nhkb_chuyen = ? ");
        if(condition != null && !"".equals(condition))
            query.append(condition);
        ResultSet rs = executeSelectStatement(query.toString(), params, conn);
        rs.next();
        String result = rs.getString("SO_TIEN");
        rs.close();
        return result;
    }
    
    public QToanBuVO getSoLieuQuyetToan(String condition, Vector params)throws Exception{
        StringBuilder query = new StringBuilder();
        query.append("/* Formatted on 10/10/2014 2:31:34 PM (QP5 v5.126) */ " + 
        "SELECT  e.so_du_cot, " + 
        "        e.so_du, " + 
        "        a.han_muc_no, " + 
        "        (a.han_muc_no - e.so_du_cot - (SELECT  NVL (SUM (so_tien), 0) " + 
        "                                         FROM  sp_quyet_toan " + 
        "                                        WHERE      ngay_qtoan = TRUNC (SYSDATE) " + 
        "                                               AND ma_nh_chuyen = e.ma_nh " + 
        "                                               AND lai_phi <> '02' " + 
        "                                               AND qtoan_dvi = 'Y' " + 
        "                                               AND ma_nt = e.loai_tien " + 
        "                                               AND ma_kb = e.ma_kb)) de_nghi_chi_bu " + 
        "  FROM  sp_tknh_kb a, " + 
        "        sp_dm_htkb b, " + 
        "        sp_dm_manh_shkb c, " + 
        "        sp_dm_ngan_hang d, " + 
        "        sp_so_du e " + 
        " WHERE      a.kb_id = b.id " + 
        "        AND b.ma = c.shkb " + 
        "        AND a.nh_id = d.id " + 
        "        AND c.ma_nh = e.ma_kb " + 
        "        AND d.ma_nh = e.ma_nh " + 
        "        AND a.ma_nt = e.loai_tien " + 
        "        AND a.loai_tk <> '01' " + 
        "        AND e.ngay_gd = TRUNC (SYSDATE) " + 
        "        AND (e.so_du_cot " + 
        "             - (SELECT  NVL (SUM (so_tien), 0) " + 
        "                  FROM  sp_quyet_toan " + 
        "                 WHERE      ngay_qtoan = TRUNC (SYSDATE) " + 
        "                        AND ma_nh_chuyen = e.ma_nh AND lai_phi in ('01','03') AND qtoan_dvi = 'Y' AND ma_nt = e.loai_tien " + 
        "                        AND ma_kb = e.ma_kb) - a.han_muc_no > a.han_muc_co " + 
        "             OR e.so_du_cot " + 
        "               - (SELECT  NVL (SUM (so_tien), 0) " + 
        "                    FROM  sp_quyet_toan " + 
        "                   WHERE      ngay_qtoan = TRUNC (SYSDATE) " + 
        "                          AND ma_nh_chuyen = e.ma_nh AND lai_phi in ('01','03') AND qtoan_dvi = 'Y' AND ma_nt = e.loai_tien " + 
        "                          AND ma_kb = e.ma_kb) < a.han_muc_no)");
        if(condition != null && !"".equals(condition))
            query.append(condition);
        return (QToanBuVO)findByPK(query.toString(), params, CLASS_QTOAN_VO, conn);
    }

    public long insertQToanBu(QToanBuVO qToanBu)throws Exception {
        Vector params = new Vector();
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        String idQToan =  ttspUtils.getMT_ID("066", null);
        String query = "INSERT INTO SP_066 (ID,NHKB_CHUYEN,NHKB_NHAN,NGUOI_TAO,NGAY_TAO,NGAY_QTOAN,LOAI_QTOAN, "+
                       "QTOAN_THU,QTOAN_CHI,NDUNG_QTOAN,TRANG_THAI,LOAI_TIEN) " + 
                       "VALUES (?,?,?,?,SYSDATE,to_date(?,'DD/MM/YYYY'),?,?,?,?,?,?)";
        params.add(new Parameter(idQToan,true));
        params.add(new Parameter(qToanBu.getMa_kb(),true));
        params.add(new Parameter(qToanBu.getMa_nh(),true));
        params.add(new Parameter(qToanBu.getNguoi_tao(),true));
        params.add(new Parameter(qToanBu.getNgay_qtoan(),true));
        params.add(new Parameter(qToanBu.getLoai_qtoan(),true));
        params.add(new Parameter(qToanBu.getDe_nghi_thu_bu(),true));
        params.add(new Parameter(qToanBu.getDe_nghi_chi_bu(),true));
        params.add(new Parameter(qToanBu.getNoi_dung(),true));
        params.add(new Parameter(qToanBu.getTrang_thai_066(),true));
        params.add(new Parameter(qToanBu.getLoai_tien(),true));
        
        return executeStatement(query, params, conn) > 0 ? Long.parseLong(idQToan) : 0;
    }

    public boolean isDuplicate(QToanBuVO qToanBu)throws Exception {
        Vector params = new Vector();
        params.add(new Parameter(qToanBu.getMa_nh(),true));
        params.add(new Parameter(qToanBu.getMa_kb(),true));
        params.add(new Parameter(qToanBu.getNgay_qtoan(),true));
        params.add(new Parameter(qToanBu.getLoai_tien(),true));
        String query = "SELECT COUNT(*) number_record FROM SP_066 " + 
        "WHERE LOAI_QTOAN = '03' " + 
        "AND NHKB_CHUYEN = ? " + 
        "AND NHKB_NHAN = ? " + 
        "AND NGAY_QTOAN = TO_DATE(?,'DD/MM/YYYY') " + 
        "AND LOAI_TIEN = ? AND TRANG_THAI <> '03'";
        ResultSet rs = executeSelectStatement(query, params, conn);
        rs.next();
        return rs.getInt("number_record") > 0;
    }

    public Collection getDanhSachDeNghiQToanBu(String condition, Vector params) throws Exception {
        StringBuilder query = new StringBuilder("SELECT TO_CHAR(NGAY_QTOAN,'DD/MM/YYYY') NGAY_QTOAN,ID id_066,QTOAN_THU,QTOAN_CHI,LOAI_TIEN,TRANG_THAI trang_thai_066,sp_util_pkg.fnc_lay_ndung_ky(ID,'066','Y') noidung_ky FROM SP_066 " + 
        "WHERE LOAI_QTOAN = '03' " + 
        "AND NHKB_CHUYEN = ? " + 
        "AND NHKB_NHAN = ? " + 
        "AND LOAI_TIEN = ? " + 
        "AND NGAY_QTOAN = TO_DATE(?,'dd/mm/yyyy')");
        
        if(condition != null && !"".equals(condition)){
            query.append(condition);
        }
        return executeSelectStatement(query.toString(), params, CLASS_QTOAN_VO, conn);
    }

    public boolean updateStatusQToanBu(Vector params) throws Exception{
        String query = "UPDATE SP_066 SET TRANG_THAI = ?, NGAY_KS = SYSDATE, NGUOI_KS = ?, CHU_KY = ?, MSG_ID = ? WHERE ID = ?";
        return executeStatement(query, params, conn) > 0;
    }

    public boolean checkTrangThaiQToan(String condition, Vector params) throws Exception {
        StringBuilder query = new StringBuilder("SELECT TRANG_THAI_QTOAN FROM SP_066 WHERE 1=1 ");
        if(condition != null && !"".equals(condition)){
            query.append(condition);
        }
        ResultSet rs = executeSelectStatement(query.toString(), params, conn);
        return rs.next();
    }
}
