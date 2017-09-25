package com.seatech.ttsp.phipos;

import com.seatech.framework.datamanager.AppDAO;

import java.sql.Connection;

import java.util.List;
import java.util.Vector;

public class BKePhiPOSDAO extends AppDAO{
    Connection conn = null;
    String BKE_PHI_POS_VO = "com.seatech.ttsp.phipos.BKePhiPOSVO";
    String BKE_PHI_POS_CTIET_VO = "com.seatech.ttsp.phipos.BKePhiPOSCtietVO";
    String BKE_GD_POS_VO = "com.seatech.ttsp.phipos.BkeGiaoDichPOSVO";
    public BKePhiPOSDAO(Connection conn) {
        this.conn = conn;
    }
  public List getListBKePhiPOSPhanTrang(String condition,
                                              Vector vParam,
                                              Integer currentPage,
                                              Integer numberRowOnPage,
                                              Integer[] totalCount) throws Exception {
      String strSQL = 
            "  SELECT a.id, a.ma_nh, a.ma_kb, a.ma_dvcn_the, a.ten_dvcn_the," + 
            "       a.nguoi_tao, a.ngay_tao, a.nguoi_ks, a.ngay_ks, to_char(a.tu_ngay,'dd/mm/yyyy') tu_ngay," + 
            "       to_char(a.den_ngay,'dd/mm/yyyy') den_ngay, a.so_tk, a.phi, a.vat, a.phi_sau_vat, to_char(a.ngay_nhan,'dd/mm/yyyy hh24:mi:ss') ngay_nhan," + 
            "       a.msg_id" + 
            "  FROM sp_bke_phi_pos a, sp_dm_ngan_hang b, sp_dm_ngan_hang c WHERE" + 
            " a.ma_nh = b.ma_nh AND a.ma_kb = c.ma_nh " + condition + 
            " ORDER BY  a.den_ngay, a.tu_ngay DESC ";
      return (List)executeSelectWithPaging(conn, strSQL.toString(), vParam, BKE_PHI_POS_VO, currentPage, numberRowOnPage, totalCount);
  }
  public List getCTietPhiPOS(String condition,
                                              Vector vParam) throws Exception {
      String strSQL = "select mt_id, to_char(ngay_gd,'dd/mm/yyyy') ngay_gd, tong_so_ct, "+
                      "tong_so_tien, muc_phi, phi, du_phong  from sp_bke_phi_pos_ctiet where 1=1 "+
                      condition;
      return (List)executeSelectStatement(strSQL.toString(), vParam, BKE_PHI_POS_CTIET_VO, conn);
  }
  public List getListGDPOS(String condition,
                                              Vector vParam,
                                              Integer currentPage,
                                              Integer numberRowOnPage,
                                            Integer[] totalCount) throws Exception {
    String strSQL = "SELECT a.ma_gd, to_char(a.ngay_gd,'dd/mm/yyyy hh24:mi:ss') ngay_gd, a.ma_cc, a.ma_pos, a.so_the, a.tctd," + 
    "       a.so_tien, a.muc_phi, a.phi_tra, a.so_thamchieu, a.mt_id" + 
    "  FROM sp_bke_gd_pos_ctiet a where (a.mt_id in (select MAX(b.mt_id) from sp_bke_gd_pos b where 1=1 "+condition+"))"+
    " order by a.ngay_gd";
        
    return (List)executeSelectWithPaging(conn, strSQL.toString(), vParam, BKE_GD_POS_VO, currentPage, numberRowOnPage, totalCount);      
  }
}
