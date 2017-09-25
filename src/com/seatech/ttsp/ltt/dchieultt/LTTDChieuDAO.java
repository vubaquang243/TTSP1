package com.seatech.ttsp.ltt.dchieultt;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.utils.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;


/* @creator: HungBM
 *
 * 17/11/2016
 * Loai bo cac lien ket tra cuu den SP_BK_DC_TAB va CTIET
 * Xoa cac Function update SP_BK_DC_TAB và CTIET
 * 
 * @modify: THUONGDT
 * @modify date: 26/12/2016
 * @see: sua, lay thong tin tu bang tong hop (SP_KQ_DC_TAB_THOP)
 * @find: KQ_DC_TAB_THOP
 */
public class LTTDChieuDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private String strValueObjectVO = "com.seatech.ttsp.dchieu.DChieu1VO";
    private static String CLASS_NAME_VO = "com.seatech.ttsp.ltt.dchieultt.LTTDChieuVO";
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.ltt.dchieultt.LTTDChieuDAO";
    private static String CLASS_NAME_KQ_VO = "com.seatech.ttsp.ltt.dchieultt.LTT_KQ_DChieuVO";
    private static final String DATE_FORMAT_NGAY_GIO = "dd/MM/yyyy HH:mm:ss";


    public LTTDChieuDAO(Connection con) {
        this.conn = con;
    }

  /* Lay chi tiet bang ke LTT
   */
  public Collection getLTTDChieuCtiet(String whereClause,
                                     Vector params) throws Exception {
      Collection reval = null;
      StringBuffer strSQL = new StringBuffer();
      try {
          //New query
                    strSQL.append("select id, lan_dc, TO_CHAR (a.ngay_dc,'DD/MM/YYYY') ngay_dc, ma_kb, shkb, ngay_tao, msg_id, " +
                                         "trang_thai, ngay_thuc_hien_dc, tt_dc_tu_dong,       "+
                                  "(select count(0) from SP_KQ_DC_TAB_CTIET where loai_lenh = 'DI' and SP_KQ_DC_TAB_CTIET.KQ_ID = a.id)  tong_ltt_di,  "   +
                                  "(select count(0) from SP_KQ_DC_TAB_CTIET where (loai_lenh = 'DEN' or loai_lenh = 'QT') and SP_KQ_DC_TAB_CTIET.KQ_ID = a.id)  tong_ltt_den, "+
                                  "(select count(0) from  sp_kq_dc_tab_ctiet  where  chenh_lech = 'TTSP' and (loai_lenh = 'DEN' or loai_lenh = 'QT')  and kq_id = a.id)ltt_thua_sp,"+
                                  "(select count(0) from  sp_kq_dc_tab_ctiet  where  chenh_lech = 'TABMIS' and loai_lenh = 'DEN'  and kq_id = a.id)ltt_thieu_sp,"+
                                  "(select count(0) from  sp_kq_dc_tab_ctiet  where  chenh_lech = 'TABMIS' and loai_lenh = 'DI' and kq_id = a.id)ltt_thua_tab,"+
                                  "(select count(0) from  sp_kq_dc_tab_ctiet  where  chenh_lech = 'TTSP' and loai_lenh = 'DI' and kq_id = a.id)ltt_thieu_tab "+
                                  " from sp_kq_dc_tab a where 1=1 ");

          if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
              strSQL.append(" " + whereClause);
          }
          
        reval = executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,conn);
          //reval = executeSelectStatement(strSQL.toString(), params, conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_DAO + ".getLTTDChieuCtiet(): " +
                               ex.getMessage(), ex);

          throw daoEx;
      }
      return reval;
  }
    
	//lay ket qua tong hop doi chieu chi tiet
    public Collection getLTTDChieuKQCtiet(String whereClause,
                                       Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {

            strSQL.append( "SELECT a.kq_id, a.nhkb_nhan, a.loai_lenh,a.chenh_lech,count(0) tonglenh,b.trang_thai" + 
            "  FROM sp_kq_dc_tab_ctiet a,sp_kq_dc_tab b where b.id = a.kq_id(+) ");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" " + whereClause);
            }
          strSQL.append(" group by  a.kq_id, a.nhkb_nhan, a.loai_lenh,a.chenh_lech,b.trang_thai ");
          reval = executeSelectStatement(strSQL.toString(), params, CLASS_NAME_KQ_VO,conn);
            //reval = executeSelectStatement(strSQL.toString(), params, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTDChieuCtiet(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
        return reval;
    }

    


   
    /* Lay thong tin tong HOP cua bang ke
     */
    public Collection getTTinTHopBKe(String id,String ma_kb,String ngay_dc,String qtoan_dvi)throws Exception {
      Collection reval = null;
      StringBuffer strSQL = new StringBuffer();
      try {
        strSQL.append("select ma,id,so_tien_di,tong_di,so_tien_den,tong_den, so_tien_qtoan_thu,tong_qtoan_thu , so_tien_qtoan_chi,tong_qtoan_chi, so_tien_lai_phi, tong_lai_phi, so_tien_ty_gia, tong_ty_gia  " + 
        "from(\n" + 
        "select a.ma,a.id,decode(b.so_tien_di,null,0,b.so_tien_di) so_tien_di,decode(b.tong_di,null,0,b.tong_di) tong_di, " + 
        "decode(c.so_tien_den,null,0,c.so_tien_den) so_tien_den, decode(c.tong_den,null,0,c.tong_den) tong_den, " +
        "decode( d.so_tien_qtoan_thu,null,0,d.so_tien_qtoan_thu) so_tien_qtoan_thu, decode(d.tong_qtoan_thu,null,0,d.tong_qtoan_thu) tong_qtoan_thu, " +
        "decode( e.so_tien_qtoan_chi,null,0,e.so_tien_qtoan_chi) so_tien_qtoan_chi, decode(e.tong_qtoan_chi,null,0,e.tong_qtoan_chi) tong_qtoan_chi, " +
        "decode( f.so_tien_lai_phi,null,0,f.so_tien_lai_phi) so_tien_lai_phi, decode(f.tong_lai_phi,null,0,f.tong_lai_phi) tong_lai_phi, " +
        "decode( g.so_tien_ty_gia,null,0,g.so_tien_ty_gia) so_tien_ty_gia, decode(g.tong_ty_gia,null,0,g.tong_ty_gia) tong_ty_gia " +
        " from SP_DM_TIENTE a,\n" + 
        "(select ma_nt,sum(TONG_TIEN) so_tien_di,SUM(TONG_LENH) tong_di from SP_KQ_DC_TAB_THOP where KQ_ID='"+id+"' AND UPPER(LOAI_LENH)='DI' group by ma_nt)b, " + 
        "(select ma_nt,sum(TONG_TIEN) so_tien_den,SUM(TONG_LENH) tong_den from SP_KQ_DC_TAB_THOP where KQ_ID='"+id+"' AND UPPER(LOAI_LENH)='DEN'group by ma_nt)c, " + 
        "(select ma_nt,sum(TONG_TIEN) so_tien_qtoan_thu,SUM(TONG_LENH) tong_qtoan_thu from SP_KQ_DC_TAB_THOP where KQ_ID='"+id+"' AND UPPER(LOAI_LENH)='QTT' group by ma_nt)d, " +
        "(select ma_nt,sum(TONG_TIEN) so_tien_qtoan_chi,SUM(TONG_LENH) tong_qtoan_chi from SP_KQ_DC_TAB_THOP where KQ_ID='"+id+"' AND UPPER(LOAI_LENH)='QTC' group by ma_nt)e, "+
        "(select ma_nt,sum(TONG_TIEN) so_tien_lai_phi,SUM(TONG_LENH) tong_lai_phi from SP_KQ_DC_TAB_THOP where KQ_ID='"+id+"' AND UPPER(LOAI_LENH)='LAP' group by ma_nt)f, " +
        "(select ma_nt,sum(TONG_TIEN) so_tien_ty_gia,SUM(TONG_LENH) tong_ty_gia from SP_KQ_DC_TAB_THOP where KQ_ID='"+id+"' AND UPPER(LOAI_LENH)='TYG' group by ma_nt)g "+                    
        " where a.id = b.ma_nt(+) and a.id = c.ma_nt(+)  and a.id = d.ma_nt(+) and a.id = e.ma_nt(+) and a.id = f.ma_nt(+) and a.id = g.ma_nt(+)) " +
        " where so_tien_di >0 or tong_di >0 or so_tien_den >0 or tong_den >0 " +
        " or so_tien_qtoan_thu>0 or tong_qtoan_thu>0 or so_tien_qtoan_chi>0 or tong_qtoan_chi>0 " +
        " or so_tien_lai_phi>0 or tong_lai_phi>0 or so_tien_ty_gia>0 or tong_ty_gia>0 " +
        " ORDER BY MA");
        reval =
                executeSelectStatement(strSQL.toString(), null, CLASS_NAME_VO,
                                       conn);

      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_DAO + ".getTTinTHopBKe(): " +
                               ex.getMessage(), ex);

          throw daoEx;
      }

      return reval;
    }
    //Lay bang ke chi tiet cua ltt 
    public Collection getLTT_KQ_Ctiet(String id,String loai_lenh) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
          strSQL.append("SELECT a.loai_tk trang_thai,a.id, a.kq_id, a.nhkb_chuyen," +
              "decode((select ten from SP_DM_MANH_SHKB where MA_NH = a.nhkb_chuyen),'',(select ten from SP_DM_NGAN_HANG where MA_NH = a.nhkb_chuyen),(select ten from SP_DM_MANH_SHKB where MA_NH = a.nhkb_chuyen)) ten_nhkb_chuyen," +
              "a.nhkb_nhan," +
              "decode((select ten from SP_DM_MANH_SHKB where MA_NH = a.nhkb_nhan),'',(select ten from SP_DM_NGAN_HANG where MA_NH = a.nhkb_nhan),(select ten from SP_DM_MANH_SHKB where MA_NH = a.nhkb_nhan)) ten_nhkb_nhan, " +
              "a.so_ltt, a.so_yctt," + 
          "       a.so_ctu_goc, a.ma_nhkb_chuyen, a.ma_nhkb_nhan, a.so_tk_chuyen," + 
          "       a.ten_tk_chuyen, a.so_tk_nhan, a.ten_tk_nhan, a.so_tien, (select ma from SP_DM_TIENTE where id  =  a.ma_nt) ma_nt," + 
          "       a.ngay_ct, a.ngay_tt, a.ngay_ktt_ky, a.loai_hach_toan," + 
          "       a.chenh_lech, a.loai_lenh FROM sp_kq_dc_tab_ctiet a,sp_kq_dc_tab b where b.id = a.kq_id and b.id='" +
                        id + "' and UPPER(loai_lenh)='"+loai_lenh+"' ORDER BY a.ma_nt");
          reval =
                  executeSelectStatement(strSQL.toString(), null, CLASS_NAME_KQ_VO,
                                         conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTT_KQ_Ctiet(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }

        return reval;
    }
    
	//Lay danh muc kho bac
  public Collection getDMucKB_cha(String strWhere,
                                  Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";

          strSQL +=
                  " SELECT   DISTINCT a.id_cha, c.ten kb_tinh" + " FROM   sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c" +
                  " WHERE   1 = 1 AND a.id = b.kb_id AND a.id_cha = c.id ";
          strSQL += strWhere + " order by ltrim(REPLACE(c.ten,'KBNN',''))";

          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         this.conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDMucKB_cha(): " +
                               ex.getMessage(), ex);
  //            daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
  
  /*Cap nhanh ly do chenh lech ket qua
   */
  public int updateChenhLechKQ(String p_id, String ly_do) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        int reval = 0;
        try {
            strSQL.append("update SP_KQ_DC_TAB set SP_KQ_DC_TAB.trang_thai='04',SP_KQ_DC_TAB.ly_do='"+ly_do+"'  where SP_KQ_DC_TAB.id='"+p_id+"'");
            reval = executeStatement(strSQL.toString(), null, conn);
        }
        catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".updateChenhLechKQ(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
        return reval;
    }
  
  
      //Cap nhat loai doi chieu
    public int updateLoaiDC(String p_kq_id) throws Exception{
      StringBuffer strSQL = new StringBuffer();
      int reval = 0;
      try{
        strSQL.append("update sp_KQ_DC_TAB set tt_dc_tu_dong ='THUCONG' where ID='"+p_kq_id+"'");
        reval = executeStatement(strSQL.toString(), null, conn);
      }
      
      catch (Exception ex) {
                  DAOException daoEx =
                      new DAOException(CLASS_NAME_DAO + ".updateLoaiDC(): " +
                                       ex.getMessage(), ex);

                  throw daoEx;
              }
      return reval;
    }

  /* lay danh sach ltt chi tiet da phan trang
   */
    public Collection getLTTDChieuListWithPading(String whereClause,
                                                 Vector params, Integer page,
                                                 Integer count,
                                                 Integer[] totalCount) throws Exception {
        String a = "";
        String cap_ma = "";
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {            

            strSQL.append("Select b.id id, b.shkb SHKB, c.ten KB_Huyen,\n" + 
            " d.TEN Ngan_hang,d.ma_nh ma_nh,\n" + 
            " TO_CHAR (b.ngay_dc, 'DD/MM/YYYY') ngay_dc, NVL(b.trang_thai,'00') trang_thai,(select decode(SUM(TONG_LENH),null,0,SUM(TONG_LENH)) from SP_KQ_DC_TAB_THOP " +
            " where (loai_lenh = 'DEN' or loai_lenh = 'QTT' or loai_lenh = 'QTC' or loai_lenh = 'LAP' or loai_lenh = 'TYG') and KQ_ID = b.id) tong_ltt_den," +
            " (select decode(SUM(TONG_LENH),null,0,SUM(TONG_LENH)) from SP_KQ_DC_TAB_THOP where loai_lenh = 'DI' and KQ_ID = b.id) tong_ltt_di \n" + 
            " from SP_KQ_DC_TAB b, sp_dm_htkb c, SP_DM_NGAN_HANG d " + 
            " where b.MA_KB=d.MA_NH AND c.ma = b.shkb ");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" " + whereClause + " ");
            }
            strSQL.append(" ORDER BY ngay_dc DESC ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), params, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTDiListWithPading(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }

        return reval;
    }
}
