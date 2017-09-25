package com.seatech.ttsp.dchieungoaite;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class BKeDChieuNgoaiTeDAO extends AppDAO {
    
  Connection conn = null;
  private String strValueObjectVO = "com.seatech.ttsp.dchieu.BKeDChieuVO";

  public BKeDChieuNgoaiTeDAO(Connection conn) {
      this.conn = conn;
  }
  
  public Collection getBKeDChieu3CTiet(String strWhere,
                                   Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
          strSQL +=
                  " SELECT   a.id bk_id, b.id kq_id, a.lan_dc," + 
                  " TO_CHAR (a.ngay_dc, 'dd/MM/yyyy') AS ngay_dc, a.send_bank," + 
                  " a.receive_bank, a.creator, a.created_date, a.manager," + 
                  " a.verified_date, a.sodu_daungay, a.tong_thu, a.tong_chi," + 
                  " a.so_du_cuoi_ngay, a.trang_thai, b.ket_qua, a.trang_thai trang_thai_bk, a.msg_id," + 
                  " b.trang_thai trang_thai_kq, to_char(a.ngay_thien_dc,'dd/MM/yyyy') as ngay_thien_dc" + 
                  " FROM   sp_bk_dc3 a, sp_kq_dc3 b" + 
                  " WHERE   a.id = b.bk_id(+) ";

          strSQL +=
                  strWhere + " ORDER BY   a.send_bank, a.ngay_dc, a.lan_dc DESC";
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getBKeDChieu3CTiet(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }  
    
  public Collection getCTietDChieu3(String strWhere, Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL ="SELECT	a.bk_id, to_char(a.ngay_ct,'DD-MM-YYYY') ngay_ct, a.ma_kb, a.mt_id, a.so_tien, b.send_bank,c.ten_nh as ten_send_bank," + 
          " b.receive_bank, d.ten_nh as ten_receive_bank" + 
          " FROM	sp_bk_dc3_ctiet a, sp_bk_dc3 b, sp_dm_nh_ho c, sp_dm_nh_ho d" + 
          " WHERE	a.bk_id = b.id and b.send_bank = c.ma_nh and b.receive_bank = d.ma_nh";          
          strSQL += strWhere + " ORDER BY ngay_ct";
           reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,conn);
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(strValueObjectVO + ".getCTietDChieu3(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }    

  public Collection getBKeDChieu1CTiet(String strWhere,
                                   Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
          strSQL += "SELECT a.mt_id,a.id, b.id kq_id, a.loai_tien, a.lan_dc, a.loai_dc, to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc, a.send_bank, a.receive_bank, a.created_date, a.creator, a.manager, a.verified_date, " + 
                  " a.somon_thu, a.sotien_thu, a.somon_chi, a.sotien_chi, a.somon_dtu, a.sotien_dtu, a.somon_tcong, decode(to_char(sysdate,'DD/MM/YYYY'),TO_CHAR (a.ngay_dc, 'DD/MM/YYYY'),a.sotien_tcong,null) sotien_tcong, " + 
                  " a.sodu_daungay, a.so_du, a.ps_thu, a.ps_chi, a.so_ketchuyen, a.msg_id, a.trang_thai, a.insert_date, decode(to_char(sysdate,'DD/MM/YYYY'),TO_CHAR (a.ngay_dc, 'DD/MM/YYYY'),a.so_tien_thu_tcong,null) so_tien_thu_tcong, a.so_tien_thu_dtu, a.so_mon_thu_tcong, to_char(a.ngay_thuc_hien,'DD/MM/YYYY') ngay_thuc_hien, " + 
                  " b.trang_thai trang_thai_kq FROM sp_bk_dc1_ngoai_te a, sp_065_ngoai_te b WHERE 1=1 and a.mt_id=b.bk_id(+) AND a.loai_tien = b.loai_tien "+ strWhere + "ORDER BY a.loai_tien";
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getBKeDChieu3CTiet(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }  
  public Collection getCTietDChieu1(String strWhere, Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL ="SELECT DISTINCT	a.id, a.bk_id, a.mt_id, a.send_bank, a.receive_bank, to_char(a.send_date,'DD-MM-YYYY HH24:mi:ss') send_date," + 
          " a.f20, a.f21, a.f32as3, a.f75, a.f76, a.fxxx, to_char(a.f32as1,'DD-MM-YYYY') f32as1," + 
          " a.mt_type, a.trang_thai, a.so_ct_goc, a.created_date, " + 
          " DECODE (SUBSTR (a.mt_id, 3, 3), '701', 'DI', 'DEN') di_den," + 
          " DECODE (SUBSTR (a.mt_id, 6, 3), '196', 'DTS', '195', 'DTS', 'LTT')" + 
          " ltt_dts, c.send_bank, (SELECT   ten" + 
          " FROM   sp_dm_ngan_hang WHERE   ma_nh = c.send_bank)" + 
          " ten_send_bank, c.receive_bank, b.ten" + 
          "  FROM	sp_bk_dc1_ctiet_ngoai_te a, sp_dm_ngan_hang b, sp_bk_dc1_ngoai_te c" + 
          " WHERE		 1 = 1 AND a.bk_id = c.mt_id AND c.receive_bank = b.ma_nh";          
          strSQL += strWhere;
          reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,conn);
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(strValueObjectVO + ".getCTietDChieu3(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }    
    
}
