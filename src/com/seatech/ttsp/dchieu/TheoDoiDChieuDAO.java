package com.seatech.ttsp.dchieu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class TheoDoiDChieuDAO extends AppDAO {
  Connection conn = null;
  private String strValueObjectVO = "com.seatech.ttsp.dchieu.TheoDoiDChieuVO";
    public TheoDoiDChieuDAO(Connection conn) {
      this.conn = conn;
    }
  
  public Collection getSumTinhHinh(String strWhere,
                                   Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
          strSQL +=
                  "  SELECT   DECODE (a.BK_id, NULL, 'CHUA_CO_BKE', 'DA_CO_BKE') BKid," + 
                  " a.KQ_TTSP TTSP, a.KQ_PHT PHT, a.XN_TH XNTH, a.LOAI_QT LOAIQT, COUNT ( * ) tong_dv" + 
                  " FROM   sp_tinhhinh_dcqt a WHERE 1=1 " + strWhere+ 
                  " GROUP BY   DECODE (a.BK_id, NULL, 'CHUA_CO_BKE', 'DA_CO_BKE'), a.kq_ttsp," + 
                  " a.kq_pht, a.xn_th, a.loai_qt" +
                  " ORDER BY   a.XN_TH asc, length(DECODE (a.BK_id, NULL, 'CHUA_CO_BKE' , 'DA_CO_BKE')) asc,a.LOAI_QT ASC,  a.KQ_TTSP ASC";

          
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getListTinhHinh(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  } 
  
  public Collection getSumTinhHinh_066(String strWhere, String strDV, String strQT,
                                   Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
          strSQL +=
                  "WITH sp_bk_dc1_v" + 
                  " AS (SELECT   b.id, b.lan_dc, b.ngay_dc, b.send_bank,  b.receive_bank" + 
                  " FROM    sp_bk_dc1 b WHERE   b.id IN" + 
                  " (  SELECT   MAX (a.id)  FROM    sp_bk_dc1 a" + 
                  " WHERE   a.app_type = 'TTSP'" + strWhere +  " GROUP BY   a.receive_bank, a.send_bank))," + 
                  "	 sp_065_pht_v" + 
                  " AS (SELECT   b.id, b.bk_id, b.ngay_dc, b.ket_qua, b.lan_dc, b.send_bank, b.receive_bank" + 
                  " FROM sp_065 b WHERE   b.id IN" + 
                  " (  SELECT   MAX (a.id) FROM    sp_065 a" + 
                  " WHERE   a.app_type = 'PHT'" + strWhere + " GROUP BY   a.receive_bank, a.send_bank))," + 
                  "	 sp_065_ttsp_v" + 
                  " AS (SELECT   b.id, b.bk_id, b.ngay_dc, b.ket_qua," + 
                  " b.ket_qua_dxn_thop, b.tthai_dxn_thop, b.lan_dc, b.send_bank, b.receive_bank" + 
                  " FROM    sp_065 b WHERE   b.id IN" + 
                  " (  SELECT   MAX (a.id) FROM    sp_065 a" + 
                  " WHERE   a.app_type = 'TTSP' AND a.trang_thai <> '04'" + strWhere + " GROUP BY   a.receive_bank, a.send_bank))," +
                  " sp_qtoan_v " + 
                  " AS (SELECT   DISTINCT SUBSTR (t.nhkb_chuyen || c.nhkb_chuyen, 1, 8) nh_chuyen," + 
                  " SUBSTR (t.ma_kb || c.ma_kb, 1, 8) kb_nhan,  decode(t.lai_phi||c.lai_phi,'0301','01','0103','01',substr(t.lai_phi||c.lai_phi,1,2))  lai_phi" + 
                  " FROM (SELECT	a.nhkb_chuyen, a.ma_kb, MIN (a.lai_phi) lai_phi FROM	sp_quyet_toan a" + 
                  " WHERE a.qtoan_dvi = 'Y' AND a.loai_qtoan = 'D' AND a.lai_phi <> '02' "+strQT+" GROUP BY	a.nhkb_chuyen, a.ma_kb) t" + 
                  " FULL OUTER JOIN (SELECT	a.nhkb_chuyen, a.ma_kb, MIN (a.lai_phi) lai_phi" + 
                  " FROM	sp_quyet_toan a WHERE a.qtoan_dvi = 'Y' AND a.loai_qtoan = 'C' AND a.lai_phi in ('01','03') "+strQT+" GROUP BY	a.nhkb_chuyen, a.ma_kb) c" + 
                  " ON (t.nhkb_chuyen = c.nhkb_chuyen AND t.ma_kb = c.ma_kb))" +
                  "  SELECT   DECODE (bk.id, NULL, 'CHUA_CO_BKE', 'DA_CO_BKE') BKid," + 
                  " sp.ket_qua ttsp, pht.ket_qua pht, sp.tthai_dxn_thop tthai_066, qt.lai_phi loaiqt, COUNT ( * ) tong_dv" + 
                  "	 FROM   sp_bk_dc1_v bk, sp_065_ttsp_v sp, sp_065_pht_v pht, sp_ds_trienkhai tk, sp_qtoan_v qt "+ 
                  "	WHERE tk.ma_nh_kb = bk.receive_bank(+)" + 
                  " AND tk.ma_nh = bk.send_bank(+) AND bk.id = sp.bk_id(+) AND tk.ma_nh_kb = pht.send_bank(+)" + 
                  " AND tk.ma_nh = pht.receive_bank(+) and tk.ma_nh_kb=qt.kb_nhan(+) and tk.ma_nh=qt.nh_chuyen (+)" + strDV +
                  " GROUP BY DECODE (bk.id, NULL, 'CHUA_CO_BKE', 'DA_CO_BKE'), sp.ket_qua , pht.ket_qua , sp.tthai_dxn_thop, qt.lai_phi " + 
                  " ORDER BY DECODE (bk.id, NULL, 'CHUA_CO_BKE', 'DA_CO_BKE') DESC, sp.ket_qua, tthai_066 desc,  qt.lai_phi" ;
           
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getListTinhHinh(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  } 
  
  
  public Collection getListTinhHinh_PTrang(String strWhere, Vector vParam,
                                           Integer page, Integer count,
                                           Integer[] totalCount) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
          strSQL +=
                  "SELECT	DISTINCT d1.ma_nh ma_kb, d3.ma_nh ma_nh_kb, d2.ten ten_ngan_hang," + 
                  " d4.ten ten_kb_huyen, d5.ten ten_kb_tinh" + 
                  "  FROM	sp_tinhhinh_dcqt d1, sp_dm_ngan_hang d2, sp_dm_manh_shkb d3," + 
                  " sp_dm_htkb d4, sp_dm_htkb d5 " + 
                  " WHERE		 d1.ma_nh = d2.ma_nh" + 
                  " AND d3.shkb = d4.ma AND d5.id = d4.id_cha AND d1.ma_nh_kb = d3.ma_nh" + strWhere ;

          strSQL +=  " order by  ltrim(REPLACE(d5.ten,'KBNN','')), ltrim(REPLACE(d4.ten,'KBNN',''))";
        return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                       strValueObjectVO, page, count,
                                       totalCount);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getListTinhHinh(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
//      return reval;
  }    
  
  
//  public Collection getListTinhHinh_066_PTrang(String strWhere, Vector vParam,
//                                           Integer page, Integer count,
//                                           Integer[] totalCount) throws Exception {
//
//      Collection reval = null;
//      try {
//
//          String strSQL = "";
//          strSQL +=
//                  "SELECT DISTINCT d1.ma_nh ma_kb, d3.ma_nh ma_nh_kb, d2.ten ten_ngan_hang," + 
//                  " d4.ten ten_kb_huyen, d5.ten ten_kb_tinh" + 
//                  "  FROM sp_tinhhinh_066 d1, sp_dm_ngan_hang d2, sp_dm_manh_shkb d3," + 
//                  " sp_dm_htkb d4, sp_dm_htkb d5 " + 
//                  " WHERE    d1.ma_nh = d2.ma_nh" + 
//                  " AND d3.shkb = d4.ma AND d5.id = d4.id_cha AND d1.ma_nh_kb = d3.ma_nh" + strWhere ;
//
//          strSQL +=  " order by  ltrim(REPLACE(d5.ten,'KBNN','')), ltrim(REPLACE(d4.ten,'KBNN',''))";
//        return executeSelectWithPaging(conn, strSQL.toString(), vParam,
//                                       strValueObjectVO, page, count,
//                                       totalCount);
//      } catch (Exception ex) {
//          DAOException daoEx =
//              new DAOException(strValueObjectVO + ".getListTinhHinh_066_PTrang(): " +
//                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
//          throw daoEx;
//      }
//  //      return reval;
//  }  
  
  
    public Collection getListTinhHinh_066_PTrang(String strWhere, String ngay_tdoi, Vector vParam,
                                           Integer page, Integer count,
                                           Integer[] totalCount) throws Exception {
//      Collection reval = null;
      try {

          String strSQL = "";
          strSQL +=
                  "WITH sp_bk_dc1_v" + 
                  " AS (SELECT   b.id, b.lan_dc, b.ngay_dc, b.send_bank, b.receive_bank" + 
                  " FROM   sp_bk_dc1 b WHERE   b.id IN" + 
                  " ( SELECT   MAX (a.id) FROM   sp_bk_dc1 a" + 
                  " WHERE   a.app_type = 'TTSP' AND TO_CHAR (a.ngay_dc, 'dd/mm/yyyy') ="+ ngay_tdoi+ " GROUP BY   a.receive_bank, a.send_bank))," + 
                  " sp_065_pht_v" + 
                  " AS (SELECT   b.id, b.bk_id, b.ngay_dc, b.ket_qua, b.lan_dc, b.send_bank, b.receive_bank" + 
                  " FROM   sp_065 b WHERE   b.id IN" + 
                  " (  SELECT   MAX (a.id) FROM   sp_065 a WHERE   a.app_type = 'PHT'" + 
                  " AND TO_CHAR (a.ngay_dc, 'dd/mm/yyyy') ="+ ngay_tdoi +" GROUP BY   a.receive_bank, a.send_bank))," + 
                  "	 sp_065_ttsp_v" + 
                  " AS (SELECT   b.id, b.bk_id, b.ngay_dc, b.ket_qua, b.ket_qua_dxn_thop, b.tthai_dxn_thop, b.lan_dc," + 
                  " b.send_bank, b.receive_bank FROM   sp_065 b" + 
                  " WHERE   b.id IN (  SELECT   MAX (a.id) FROM   sp_065 a" + 
                  " WHERE 		a.app_type = 'TTSP' AND a.trang_thai <> '04'" + 
                  " AND TO_CHAR (a.ngay_dc, 'dd/mm/yyyy') ="+ ngay_tdoi +" GROUP BY   a.receive_bank, a.send_bank))," + 
                  "	 sp_qtoan_v" + 
                  " AS (SELECT   DISTINCT SUBSTR (t.nhkb_chuyen || c.nhkb_chuyen, 1, 8) nh_chuyen," + 
                  " SUBSTR (t.ma_kb || c.ma_kb, 1, 8) kb_nhan, SUBSTR (t.lai_phi || c.lai_phi, 1, 2) lai_phi" + 
                  " FROM 	  (SELECT	a.nhkb_chuyen, a.ma_kb, a.lai_phi FROM	sp_quyet_toan a" + 
                  " WHERE a.qtoan_dvi = 'Y' AND a.loai_qtoan = 'D' AND a.lai_phi in ('01','03') " + 
                  " AND TO_CHAR (a.ngay_qtoan, 'dd/mm/yyyy') ="+ ngay_tdoi +") t" + 
                  " FULL OUTER JOIN" + 
                  " (SELECT	a.nhkb_chuyen, a.ma_kb, a.lai_phi FROM	sp_quyet_toan a" + 
                  " WHERE		 a.qtoan_dvi = 'Y' AND a.loai_qtoan = 'C' AND a.lai_phi in ('01','03') " + 
                  " AND TO_CHAR (a.ngay_qtoan, 'dd/mm/yyyy') ="+ ngay_tdoi +") c" + 
                  " ON (t.nhkb_chuyen = c.nhkb_chuyen AND t.ma_kb = c.ma_kb))" + 
                  "  SELECT   DISTINCT tk.ma_nh ma_nh_kb , d3.ma_nh ma_kb , d2.ten ten_ngan_hang," + 
                  "  d4.ten ten_kb_huyen, d5.ten ten_kb_tinh" + 
                  "	 FROM   sp_bk_dc1_v bk, sp_065_ttsp_v sp, sp_065_pht_v pht," + 
                  " sp_ds_trienkhai tk, sp_qtoan_v qt, sp_dm_ngan_hang d2, sp_dm_manh_shkb d3, sp_dm_htkb d4, sp_dm_htkb d5" + 
                  "	WHERE 		tk.ma_nh_kb = bk.receive_bank(+)" + 
                  "			  AND tk.ma_nh = bk.send_bank(+)" + 
                  "			  AND bk.id = sp.bk_id(+)" + 
                  "			  AND tk.ma_nh_kb = pht.send_bank(+)" + 
                  "			  AND tk.ma_nh = pht.receive_bank(+)" + 
                  "			  AND tk.ma_nh_kb = qt.kb_nhan(+)" + 
                  "			  AND tk.ma_nh = qt.nh_chuyen(+)" + 
                  "     AND tk.ma_nh = d2.ma_nh" + 
                  "     AND d3.shkb = d4.ma" + 
                  "     AND d5.id = d4.id_cha" + 
                  "     AND tk.ma_nh_kb = d3.ma_nh "  + strWhere ;

          strSQL +=  " order by  ltrim(REPLACE(d5.ten,'KBNN','')), ltrim(REPLACE(d4.ten,'KBNN',''))";
        return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                       strValueObjectVO, page, count,
                                       totalCount);
          
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getListTinhHinh_066_PTrang(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
//        return reval;
  }  
  
  
  public TheoDoiDChieuVO getTongDVTDoi(String strWhere,
                               Vector vParam) throws Exception {

             Collection reval = null;
       try {

           String strSQL = "";

           strSQL +=
                   "SELECT   count(1) sum_dvi " + 
                   "	 FROM   sp_tinhhinh_dcqt a " + 
                   "	WHERE   1 = 1 " +
                   strWhere;

           TheoDoiDChieuVO vo =
               (TheoDoiDChieuVO)findByPK(strSQL.toString(), vParam,
                                     strValueObjectVO, conn);
           return vo;
       } catch (Exception ex) {
           DAOException daoEx =
               new DAOException(strValueObjectVO + ".getTongDV(): " +
                                ex.getMessage(), ex);
//           daoEx.printStackTrace();
           throw daoEx;
       }
   }  
  
}
