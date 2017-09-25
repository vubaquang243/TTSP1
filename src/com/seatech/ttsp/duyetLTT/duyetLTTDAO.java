package com.seatech.ttsp.duyetLTT;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class duyetLTTDAO extends AppDAO {
    
  Connection conn = null;
  private String strValueObjectVO = "com.seatech.ttsp.duyetLTT.duyetLTTVO";

  public duyetLTTDAO(Connection conn) {
      this.conn = conn;
  }
  public Collection getDsachTTV(String strWhere,
                                   Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
          strSQL +=
                  "SELECT a.id, a.kb_id, a.ten_nsd, a.ma_tabmis, a.chuc_danh, a.ma_nsd, a.trang_thai, c.loai_nhom " + 
                  " FROM	sp_nsd a, sp_nsd_nhom b, sp_nhom_nsd c where a.trang_thai='01' AND c.id = b.nhom_id AND a.id = b.nsd_id";

          strSQL +=
                  strWhere + " order by ma_nsd";
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDsachTTV(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }

  public Collection getDsachKTV(String strWhere,
                                   Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
          strSQL +=
                  " SELECT a.id, a.ma, a.ma||' - '||a.ten ten, a.kb_id " + 
                  "  FROM sp_ktv_tabmis a WHERE 1=1 ";

          strSQL +=
                  strWhere + " order by a.ten";
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDsachTTV(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }

  public Collection getDMucNH(String strWhere,Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL ="";

          strSQL +=" SELECT distinct b.id,  b.ten ten_nh, b.tinh_trang, b.ma_nh, a.kb_id " + 
                "  FROM sp_tknh_kb a,  sp_dm_ngan_hang b where b.id=a.nh_id" + strWhere;
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         this.conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDMucNH(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
  
  public Collection getlstLTT(String strWhere,
                                   Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
          strSQL +=
                  "   SELECT   t.id, t.nhkb_nhan, t.nhkb_chuyen, t.nhan, t.ttv_nhan,  b.ten_nsd, b.ma_nsd," + 
                  " t.so_ct_goc, t.ngay_ct, t.so_yctt, t.ngay_tt, t.ndung_tt," + 
                  " t.tong_sotien, t.so_tk_nhan, t.ten_tk_nhan,  sp_util_pkg.fnc_lay_ndung_ky(t.id, '103','Y') ndung_ky, " + 
                  " t.ttin_kh_nhan, t.id_nhkb_nhan, t.ten_nhkb_nhan, t.trang_thai," + 
                  " t.loai_hach_toan, t.chuky_ktt, t.chuky_gd, t.so_tk_chuyen, t.ten_tk_chuyen kh_chuyen," + 
                  " t.so_tk_nhan || '-' || t.ten_tk_nhan kh_nhan," + 
                  " t.id_nhkb_nhan || '-' || t.ten_nhkb_nhan nh_nhan " + 
                  " FROM   sp_ltt t, sp_nsd b" + 
                  "	WHERE    INSTR (t.id, '701') = 3  and  t.ttv_nhan = b.id";

          strSQL +=
                  strWhere + " ORDER BY   t.id, t.trang_thai";
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDsachTTV(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
  
  public Collection getlstLTT_PTrang(String strWhere, Vector vParam, Integer page, Integer count,
                                           Integer[] totalCount) throws Exception {

      
      try {

          String strSQL = "";
          strSQL +=
                  "   SELECT   t.id, t.nhkb_nhan, t.nhkb_chuyen, t.nhan, t.ttv_nhan,  b.ten_nsd,  b.ma_nsd," + 
                  " t.so_ct_goc, t.ngay_ct, t.so_yctt, '...'||substr(so_yctt,10) yctt_short, t.ngay_tt, t.ndung_tt," + 
                  " t.tong_sotien, t.so_tk_nhan, t.ten_tk_nhan,  sp_util_pkg.fnc_lay_ndung_ky(t.id, '103','Y') ndung_ky, " + 
                  " t.ttin_kh_nhan, t.id_nhkb_nhan, t.ten_nhkb_nhan, t.trang_thai," + 
                  " t.loai_hach_toan, t.chuky_ktt, t.chuky_gd, t.so_tk_chuyen, t.ten_tk_chuyen kh_chuyen," + 
                  " t.so_tk_nhan || '-' || t.ten_tk_nhan kh_nhan," + 
                  " t.id_nhkb_nhan , t.ten_nhkb_nhan  " + 
                  " FROM   sp_ltt t, sp_nsd b" + 
                  " WHERE    INSTR (t.id, '701') = 3  and  t.ttv_nhan = b.id(+)";

          strSQL +=
                  strWhere + " ORDER BY   SUBSTR(t.so_yctt,INSTR(t.so_yctt,'_')+1,6),t.ktv_chuyen, to_number(SUBSTR(t.so_yctt,INSTR(t.so_yctt,'_',2,3)+1))  asc";
        return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                       strValueObjectVO, page, count,
                                       totalCount);
        } catch (Exception ex) {
        DAOException daoEx =
          new DAOException(strValueObjectVO + ".getKQuaTKiem(): " +
                           ex.getMessage(), ex);
        
//        daoEx.printStackTrace();
        throw daoEx;
        }
  }
  
  public Collection getlstLTTTW_PTrang(String strWhere, Vector vParam, Integer page, Integer count,
                                           Integer[] totalCount) throws Exception {

  //      Collection reval = null;
      try {

          String strSQL = "";
          strSQL +=
                  "   SELECT   t.id, d.id htkb_id, t.nhkb_nhan, t.nhkb_chuyen, t.nhan, t.ttv_nhan,  b.ten_nsd,  b.ma_nsd," + 
                  " t.so_ct_goc, t.ngay_ct, t.so_yctt, '...'||substr(so_yctt,10) yctt_short, t.ngay_tt, t.ndung_tt," + 
                  " t.tong_sotien, t.so_tk_nhan, t.ten_tk_nhan,  sp_util_pkg.fnc_lay_ndung_ky(t.id, '103','Y') ndung_ky, " + 
                  " t.ttin_kh_nhan, t.id_nhkb_nhan, t.ten_nhkb_nhan, t.trang_thai," + 
                  " t.loai_hach_toan, t.chuky_ktt, t.chuky_gd, t.so_tk_chuyen, t.ten_tk_chuyen kh_chuyen,substr(f.ma_nh,3,3) ma_dv," + 
                  " t.so_tk_nhan || '-' || t.ten_tk_nhan kh_nhan,to_char(t.ngay_duyet_tw,'DD/MM/YYYY HH24:mi:ss') ngay_duyet_tw, " + 
                  " t.id_nhkb_nhan , t.ten_nhkb_nhan , g.giatri_ts, t.trang_thai_tw, t.ly_do_tw,t.ly_do_tinh" + 
                  " FROM   sp_ltt t, sp_nsd b , sp_dm_ngan_hang c, sp_dm_htkb d, sp_dm_manh_shkb e, sp_dm_ngan_hang f, sp_thamso_kb g" + 
                  " WHERE    INSTR (t.id, '701') = 3  and  t.ttv_nhan = b.id and t.nhkb_nhan=f.id " +
                  " and d.ma = e.shkb AND c.ma_nh = e.ma_nh and t.nhkb_chuyen= c.id and d.id=g.kb_id and g.ten_ts ='HACH_TOAN_TABMIS_THEO_NGAY_KS_NH'";

          strSQL +=
                  strWhere + " ORDER BY  SUBSTR(t.so_yctt,INSTR(t.so_yctt,'_')+1,6),t.ktv_chuyen, to_number(SUBSTR(t.so_yctt,INSTR(t.so_yctt,'_',2,3)+1))  asc";
        return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                       strValueObjectVO, page, count,
                                       totalCount);
        } catch (Exception ex) {
        DAOException daoEx =
          new DAOException(strValueObjectVO + ".getKQuaTKiem(): " +
                           ex.getMessage(), ex);
        
//        daoEx.printStackTrace();
        throw daoEx;
        }
  }
  
  
  public Collection getTienMon(String strWhere,
                              Vector params) throws Exception {
    Collection reval = null;
      try {
          String strSQL = "";
        strSQL +=
                "   SELECT nvl(max(ROWNUM),0) tong_mon , nvl(sum(tong_sotien),0) tong_tien" + 
                " FROM   sp_ltt t , sp_dm_ngan_hang c,sp_dm_htkb d , sp_dm_manh_shkb e, sp_dm_ngan_hang f " + 
                " WHERE    INSTR (t.id, '701') = 3  AND  d.ma = e.shkb and t.nhkb_nhan=f.id  AND c.ma_nh = e.ma_nh  and t.nhkb_chuyen=c.id " + strWhere;

        reval =
                executeSelectStatement(strSQL.toString(), params, strValueObjectVO,
                                       conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(".getTienMon(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
    return reval;
  }
  
  
  public int update(duyetLTTVO duyetVO) throws Exception {
      Vector v_param = new Vector();
      Parameter param = null;
      StringBuffer strSQL = new StringBuffer();

      try {
          strSQL.append("update sp_ltt set ");

          if (duyetVO.getNhkb_chuyen() != null) {
              strSQL.append("nhkb_chuyen=?");
              param = new Parameter(duyetVO.getNhkb_chuyen(), true);
              v_param.add(param);
          }
          if (duyetVO.getNhkb_nhan() != null) {
              strSQL.append(", nhkb_nhan=?");
              param = new Parameter(duyetVO.getNhkb_nhan(), true);
              v_param.add(param);
          }
          if (duyetVO.getTtv_nhan() != null) {
              strSQL.append(", ttv_nhan=?");
              param = new Parameter(duyetVO.getTtv_nhan(), true);
              v_param.add(param);
          }
          if (duyetVO.getNgay_gui_nh() != null) {
              strSQL.append(", ngay_gui_nh=SYSDATE");
          }
          if (duyetVO.getSo_ct_goc() != null) {
              strSQL.append(", so_ct_goc=?");
              param = new Parameter(duyetVO.getSo_ct_goc(), true);
              v_param.add(param);
          }
          if (duyetVO.getNgay_ct() != null) {
              strSQL.append(", ngay_ct=?");
              param = new Parameter(duyetVO.getNgay_ct(), true);
              v_param.add(param);
          }
          if (duyetVO.getSo_yctt() != null) {
              strSQL.append(", so_yctt=?");
              param = new Parameter(duyetVO.getSo_yctt(), true);
              v_param.add(param);
          }
          if (duyetVO.getNgay_tt() != null) {
              strSQL.append(", ngay_tt=?");
              param = new Parameter(duyetVO.getNgay_tt(), true);
              v_param.add(param);
          }
          if (duyetVO.getNdung_tt() != null) {
              strSQL.append(", ndung_tt=?");
              param = new Parameter(duyetVO.getNdung_tt(), true);
              v_param.add(param);
          }
          if (duyetVO.getTong_sotien() != null) {
              strSQL.append(", tong_sotien=?");
              param = new Parameter(duyetVO.getTong_sotien(), true);
              v_param.add(param);
          }
          if (duyetVO.getSo_tk_nhan() != null) {
              strSQL.append(", so_tk_nhan=?");
              param = new Parameter(duyetVO.getSo_tk_nhan(), true);
              v_param.add(param);
          }
          if (duyetVO.getTen_tk_nhan() != null) {
              strSQL.append(", ten_tk_nhan=?");
              param = new Parameter(duyetVO.getTen_tk_nhan(), true);
              v_param.add(param);
          }
          if (duyetVO.getTtin_kh_nhan() != null) {
              strSQL.append(", ttin_kh_nhan=?");
              param = new Parameter(duyetVO.getTtin_kh_nhan(), true);
              v_param.add(param);
          }
          if (duyetVO.getId_nhkb_nhan() != null) {
              strSQL.append(", id_nhkb_nhan=?");
              param = new Parameter(duyetVO.getId_nhkb_nhan(), true);
              v_param.add(param);
          }
          if (duyetVO.getTen_nhkb_nhan() != null) {
              strSQL.append(", ten_nhkb_nhan=?");
              param = new Parameter(duyetVO.getTen_nhkb_nhan(), true);
              v_param.add(param);
          }
          if (duyetVO.getTrang_thai() != null) {
              strSQL.append(", trang_thai=?");
              param = new Parameter(duyetVO.getTrang_thai(), true);
              v_param.add(param);
          }
          if (duyetVO.getLoai_hach_toan() != null) {
              strSQL.append(", loai_hach_toan =?");
              param = new Parameter(duyetVO.getLoai_hach_toan(), true);
              v_param.add(param);
          }

          if (duyetVO.getChuky_ktt() != null) {
              strSQL.append(", chuky_ktt=?");
              param = new Parameter(duyetVO.getChuky_ktt(), true);
              v_param.add(param);
          }
          if (duyetVO.getChuky_gd() != null) {
              strSQL.append(", chuky_gd=?");
              param = new Parameter(duyetVO.getChuky_gd(), true);
              v_param.add(param);
          }
          if (duyetVO.getNgay_hoan_thien() != null) {
              if ("SYSDATE".equalsIgnoreCase(duyetVO.getNgay_hoan_thien())) {
                  strSQL.append(", ngay_hoan_thien=SYSDATE");
              } else {
                  String strNgayHoanThien = duyetVO.getNgay_hoan_thien();
                  if (strNgayHoanThien.length() < 10)
                      strNgayHoanThien = null;
                  else if (strNgayHoanThien.length() == 10)
                      strNgayHoanThien = strNgayHoanThien + " 00:00:00";
                  strSQL.append(", ngay_hoan_thien=?");
                  param =
                          new DateParameter(strNgayHoanThien, true);
                  v_param.add(param);
              }
          }

          strSQL.append(" WHERE id in (?)");
          param = new Parameter(String.valueOf(duyetVO.getStr_id()), true);
          v_param.add(param);

          return executeStatement(strSQL.toString().replaceFirst("set , ",
                                                                 "set "),
                                  v_param, conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(".update(duyetVO): " +
                               ex.getMessage());

          throw daoEx;
      }
  }
  
}
