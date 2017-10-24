package com.seatech.ttsp.dchieu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class BKeDChieu3NgoaiTeDAO extends AppDAO {
    
  Connection conn = null;
  private String strValueObjectVO = "com.seatech.ttsp.dchieu.BKeDChieu3NgoaiTeVO";

  public BKeDChieu3NgoaiTeDAO(Connection conn) {
      this.conn = conn;
  }
  
  public Collection getBKeDChieu4CTiet(String strWhere,
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
                  " FROM   sp_bk_dc3_ngoai_te a, sp_kq_dc3_ngoai_te b" + 
                  " WHERE   a.id = b.bk_id(+) ";

          strSQL +=
                  strWhere + " ORDER BY   a.send_bank, a.ngay_dc, a.lan_dc DESC";
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getBKeDChieu4CTiet(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }  
    
  public Collection getCTietDChieu4(String strWhere, Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL ="SELECT	a.bk_id, to_char(a.ngay_ct,'DD-MM-YYYY') ngay_ct, a.ma_kb, a.mt_id, a.so_tien, b.send_bank,c.ten_nh as ten_send_bank," + 
          " b.receive_bank, d.ten_nh as ten_receive_bank" + 
          " FROM	sp_bk_dc3_ngoai_te_ctiet a, sp_bk_dc3_ngoai_te b, sp_dm_nh_ho c, sp_dm_nh_ho d" + 
          " WHERE	a.bk_id = b.id and b.send_bank = c.ma_nh and b.receive_bank = d.ma_nh";          
          strSQL += strWhere + " ORDER BY ngay_ct";
           reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,conn);
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(strValueObjectVO + ".getCTietDChieu4(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }    
}
