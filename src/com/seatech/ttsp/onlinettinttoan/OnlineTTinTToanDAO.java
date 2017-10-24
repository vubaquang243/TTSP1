package com.seatech.ttsp.onlinettinttoan;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

public class OnlineTTinTToanDAO extends AppDAO {

    private String CLASS_NAME_DAO;
    private String CLASS_NAME_VO;
    private Connection conn;

    public OnlineTTinTToanDAO(Connection conn) {
        CLASS_NAME_DAO = "com.seatech.ttsp.onlinettinttoan.OnlineTTinTToanDAO";
        CLASS_NAME_VO = "com.seatech.ttsp.onlinettinttoan.OnlineTTinTToanVO";
        this.conn = null;
        this.conn = conn;
    }

    public Collection<OnlineTTinTToanVO> getTTinTToanList(String whereClause, String whereClause2,
                                  Vector vParam,String dv_tien) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {

          strSQL.append("SELECT   a.id,");
          strSQL.append(" a.msg_refid,");
          strSQL.append(" a.nhkb_chuyen,");
          strSQL.append(" a.nhkb_nhan, a.ngay_tao, d.ma ma_kb, d.ten ten_kb,"); 
          strSQL.append(" c.loai_tk, a.so_tk, a.ma_nh, a.ten_nh, c.han_muc_no/"+dv_tien+" as han_muc_no,"); 
          strSQL.append(" a.du_dau_ngay/"+dv_tien+" as du_dau_ngay, a.ps_thu/"+dv_tien+" as ps_thu, a.ps_chi/"+dv_tien+" as ps_chi, a.chenh_lech/"+dv_tien+" as chenh_lech,"); 
          strSQL.append(" a.ngay_gui, a.msg_id,c.ma_nt");
          strSQL.append(" FROM   sp_ttin_ttoan a, sp_dm_ngan_hang b, sp_tknh_kb c, sp_dm_htkb d, sp_dm_manh_shkb e");
          strSQL.append(" WHERE   a.nhkb_chuyen = b.ma_nh and  d.id = c.kb_id AND"); 
          strSQL.append(" a.so_tk = c.so_tk AND b.id=c.nh_id AND e.shkb = d.ma AND e.ma_nh = a.ma_kb AND c.trang_thai = '01' "); 
          strSQL.append(whereClause2);
          strSQL.append(" and (a.id, a.so_tk, a.nhkb_chuyen, a.nhkb_nhan) IN (  SELECT   MAX (id), so_tk, nhkb_chuyen, nhkb_nhan"); 
          strSQL.append(" FROM   sp_ttin_ttoan ");             
          strSQL.append(" WHERE   so_tk IS NOT NULL ");
          strSQL.append(whereClause);            
          strSQL.append(" GROUP BY   so_tk, nhkb_chuyen, nhkb_nhan)"); 
          strSQL.append(" ORDER BY  d.ma ,loai_tk   "); 
            reval =
          executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                 conn);
        } catch (Exception ex) {
            throw new DAOException((new StringBuilder()).append(CLASS_NAME_DAO).append(".getTTinTToanList(): ").append(ex.getMessage()).toString(),
                                   ex);
        }
        return reval;
    }

  public Collection<OnlineTTinTToanVO> getSumTDoi(String strWhere, String strWhere2,
                                  Vector vParam,String dv_tien) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";

          strSQL +=
                  " SELECT   SUM (c.han_muc_no/"+dv_tien+") AS han_muc_no, SUM (a.du_dau_ngay/"+dv_tien+") AS du_dau_ngay," + 
                  " SUM (a.ps_thu/"+dv_tien+") AS ps_thu, SUM (a.ps_chi/"+dv_tien+") AS ps_chi," + 
                  " SUM (a.chenh_lech/"+dv_tien+") AS chenh_lech " + 
                  "	 FROM   sp_ttin_ttoan a, sp_dm_ngan_hang b, sp_tknh_kb c, sp_dm_htkb d, sp_dm_manh_shkb e" + 
                  "	WHERE   a.nhkb_chuyen = b.ma_nh AND a.so_tk = c.so_tk AND b.id = c.nh_id and c.kb_id = d.id" +
                  " AND e.shkb = d.ma AND e.ma_nh = a.ma_kb "+
                  " AND c.trang_thai = '01' " + 
                  strWhere2+
                  " AND (a.id, a.so_tk, a.nhkb_chuyen, a.nhkb_nhan) IN" + 
                  " (  SELECT   MAX ( id ), so_tk, nhkb_chuyen, nhkb_nhan" + 
                  " FROM   sp_ttin_ttoan WHERE   so_tk IS NOT NULL GROUP BY   so_tk, nhkb_chuyen, nhkb_nhan)" + strWhere;

          reval =
                  executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_VO + ".getSumTDoi(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }

}
