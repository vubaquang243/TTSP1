package com.seatech.ttsp.lsutacnghiep;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;


public class LSuTacNghiepDAO extends AppDAO {
    private String CLASS_NAME_VO = "com.seatech.ttsp.lsutacnghiep.LSuTacNghiepVO";
    private String CLASS_NAME_DAO = "com.seatech.ttsp.lsutacnghiep.LSuTacNghiepDAO";
    private Connection conn = null;

    public LSuTacNghiepDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean checkLogin(String username, String password) {
        return true;
    }

    private void setConn(Connection conn) {
        this.conn = conn;
    }

  /**
   * @param: Menh de where va vector param
   * @return: Danh sach NSD co phan trang
   * @see:
   * */
  public Collection getlist_ltt(String whereClause, Vector vParam,
                                       Integer page, Integer count,
                                       Integer[] totalCount ) throws Exception {
      Collection reval = null;
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT a.id, a.ltt_id, a.nhkb_nhan, a.nhkb_chuyen, a.nhan, a.ttv_nhan, " + 
          "       a.ngay_nhan, a.ktv_chuyen, a.ktt_duyet, a.ngay_ktt_duyet, " + 
          "       a.lydo_ktt_day_lai, a.gd_duyet, a.ngay_gd_duyet, " + 
          "       a.lydo_gd_day_lai, a.so_ct_goc, a.ngay_ct, a.nt_id, a.so_yctt, " + 
          "       a.ngay_tt, a.ndung_tt, a.tong_sotien, a.so_tk_chuyen, " + 
          "       a.ten_tk_chuyen, a.ttin_kh_chuyen, a.id_nhkb_chuyen, " + 
          "       a.ten_nhkb_chuyen, a.so_tk_nhan, a.ten_tk_nhan, a.ttin_kh_nhan, " + 
          "       a.id_nhkb_nhan, a.ten_nhkb_nhan, a.trang_thai, a.loai_hach_toan, " + 
          "       a.nguoi_nhap_nh, a.ngay_nhap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, " + 
          "       a.chuky_ktt, a.chuky_gd, to_char(a.tgian_ghi, 'dd/MM/yyy hh:mi:ss') tgian_ghi,b.ma_nsd ma_ttt, b.ten_nsd ten_ttt,c.ma_nsd ma_ktt_duyet, c.ten_nsd ten_ktt_duyet " + 
          "  FROM sp_ltt_lsu a , sp_nsd b,sp_nsd c where " + 
          "  (CASE WHEN a.gd_duyet is null THEN a.ktt_duyet " + 
          "        ELSE a.gd_duyet END)  =c.id and a.ttv_nhan=b.id");
          if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
              strSQL.append( whereClause);
          }
          strSQL.append(" ORDER BY a.id DESC ");
          reval = executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                       CLASS_NAME_VO, page, count,
                                       totalCount);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getUserList(): " +
                                 ex.getMessage(), ex);
      }

      return reval;
  }
  public ResultSet getResultSet_ltt(String whereClause, Vector vParam ) throws Exception {
      ResultSet reval = null;
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT a.id, a.ltt_id, a.nhkb_nhan, a.nhkb_chuyen, a.nhan, a.ttv_nhan, " + 
          "       a.ngay_nhan, a.ktv_chuyen, a.ktt_duyet, a.ngay_ktt_duyet, " + 
          "       a.lydo_ktt_day_lai, a.gd_duyet, a.ngay_gd_duyet, " + 
          "       a.lydo_gd_day_lai, a.so_ct_goc, a.ngay_ct, a.nt_id, a.so_yctt, " + 
          "       a.ngay_tt, a.ndung_tt, a.tong_sotien, a.so_tk_chuyen, " + 
          "       a.ten_tk_chuyen, a.ttin_kh_chuyen, a.id_nhkb_chuyen, " + 
          "       a.ten_nhkb_chuyen, a.so_tk_nhan, a.ten_tk_nhan, a.ttin_kh_nhan, " + 
          "       a.id_nhkb_nhan, a.ten_nhkb_nhan, a.trang_thai, a.loai_hach_toan, " + 
          "       a.nguoi_nhap_nh, a.ngay_nhap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, " + 
          "       a.chuky_ktt, a.chuky_gd, to_char(a.tgian_ghi, 'dd/MM/yyy hh:mi:ss') tgian_ghi,b.ma_nsd ma_ttt, b.ten_nsd ten_ttt,c.ma_nsd ma_ktt_duyet, c.ten_nsd ten_ktt_duyet " + 
          "  FROM sp_ltt_lsu a , sp_nsd b,sp_nsd c where " + 
          "  (CASE WHEN a.gd_duyet is null THEN a.ktt_duyet " + 
          "        ELSE a.gd_duyet END)  =c.id and a.ttv_nhan=b.id");
          if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
              strSQL.append( whereClause);
          }
          strSQL.append(" ORDER BY a.id DESC ");
          reval = executeSelectStatement(strSQL.toString(), vParam,conn);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getResultSet_ltt(): " +
                                 ex.getMessage(), ex);
      }

      return reval;
  }
}

