package com.seatech.ttsp.iReport.lttreport;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;


public class LTTRPDAO extends AppDAO{
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private static String CLASS_NAME_VO = "com.seatech.ttsp.ltt.LTTVO";
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.iReport.lttreport.LTTRPDAO";
  
    public LTTRPDAO() {
        super();
    }
    public LTTRPDAO(Connection con) {
        this.conn = con;
    }
    
    public ResultSet getBangTongHopLTTDi(String whereClause,
                                   Vector params) throws Exception {
        Collection reval = null;
        ResultSet rs = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT SP_LTT.\"ID\" AS SP_LTT_ID, ");
            strSQL.append(" SP_LTT.NHKB_NHAN AS SP_LTT_NHKB_NHAN, SP_LTT.NHKB_CHUYEN AS SP_LTT_NHKB_CHUYEN, SP_LTT.NGAY_NHAN AS SP_LTT_NGAY_NHAN, \n" + 
              "SP_LTT.SO_CT_GOC AS SP_LTT_SO_CT_GOC, SP_LTT.SO_YCTT AS SP_LTT_SO_YCTT, SP_LTT.NGAY_TT AS SP_LTT_NGAY_TT,  ");
            strSQL.append(" SP_LTT.TONG_SOTIEN AS SP_LTT_TONG_SOTIEN, SP_LTT.SO_TK_CHUYEN AS SP_LTT_SO_TK_CHUYEN,  \n" + 
              "SP_LTT.TEN_TK_CHUYEN AS SP_LTT_TEN_TK_CHUYEN, SP_LTT.TTIN_KH_CHUYEN AS SP_LTT_TTIN_KH_CHUYEN, \n" + 
              "SP_LTT.ID_NHKB_CHUYEN AS SP_LTT_ID_NHKB_CHUYEN, SP_LTT.TEN_NHKB_CHUYEN AS SP_LTT_TEN_NHKB_CHUYEN, \n" + 
              "SP_LTT.SO_TK_NHAN AS SP_LTT_SO_TK_NHAN, SP_LTT.TEN_TK_NHAN AS SP_LTT_TEN_TK_NHAN, \n" + 
              "SP_LTT.TTIN_KH_NHAN AS SP_LTT_TTIN_KH_NHAN, SP_LTT.ID_NHKB_NHAN AS SP_LTT_ID_NHKB_NHAN, \n" + 
              "SP_LTT.TEN_NHKB_NHAN AS SP_LTT_TEN_NHKB_NHAN, SP_LTT.TRANG_THAI AS SP_LTT_TRANG_THAI, \n" + 
              "B.TEN AS TEN_NHKB_NHAN_COMMON, C.SUM_TONG_SOTIEN ");
            strSQL.append("FROM  (SELECT SUM(TONG_SOTIEN) AS SUM_TONG_SOTIEN FROM TTSP_TEST.SP_LTT ) C ");            
            strSQL.append(" , TTSP_TEST.SP_LTT SP_LTT ");            
            strSQL.append(" JOIN TTSP_TEST.SP_DM_NGAN_HANG B ON SP_LTT.NHKB_NHAN = B.ID  ");
              
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(whereClause);
            }
            
            rs = executeSelectStatement(strSQL.toString(), params, conn);
        } catch (Exception ex) {    
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getBangTongHopLTTDi(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    
      return rs;
    }
    public Collection getLTTDiKTTGDList(String whereClause,
                                   Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT t.id, t.nhkb_nhan, t.nhkb_chuyen, t.nhan, t.ttv_nhan, to_char(t.ngay_nhan, 'DD/MM/YYYY HH24:MI:SS') ngay_nhan, t.ktv_chuyen, t.ktt_duyet, to_char(t.ngay_ktt_duyet, 'DD/MM/YYYY HH24:MI:SS') ngay_ktt_duyet, t.lydo_ktt_day_lai ");
            strSQL.append(" , t.gd_duyet, to_char(t.ngay_gd_duyet, 'DD/MM/YYYY HH24:MI:SS') ngay_gd_duyet, t.lydo_gd_day_lai, t.so_ct_goc, t.ngay_ct, t.nt_id, t.so_yctt, t.ngay_tt, t.ndung_tt, t.tong_sotien, t.so_tk_chuyen, t.ten_tk_chuyen ");
            strSQL.append(" , t.ttin_kh_chuyen, t.id_nhkb_chuyen, t.ten_nhkb_chuyen, t.so_tk_nhan, t.ten_tk_nhan, t.ttin_kh_nhan, t.id_nhkb_nhan, t.ten_nhkb_nhan, t.trang_thai, t.loai_hach_toan ");
            strSQL.append(" , t.nguoi_nhap_nh, to_char(t.ngay_nhap_nh, 'DD/MM/YYYY') ngay_nhap_nh, t.nguoi_ks_nh, to_char(t.ngay_ks_nh, 'DD/MM/YYYY') ngay_ks_nh, t.chuky_ktt, t.chuky_gd, t.loai_quyet_toan ");
            strSQL.append(" FROM sp_ltt t ");
            
            strSQL.append("    INNER JOIN sp_dm_ma_thamchieu d ON t.trang_thai = d.rv_low_value ");
              
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
                if(whereClause.indexOf("ORDER BY") == -1){
                  strSQL.append(" ORDER BY t.trang_thai desc, t.ngay_nhan ASC ");
                }
            }
            
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
    
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTDiList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
  
      return reval;
    }
}
