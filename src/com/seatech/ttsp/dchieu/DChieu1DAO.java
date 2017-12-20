package com.seatech.ttsp.dchieu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.SelectStatementException;
import com.seatech.framework.utils.StringUtil;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

/* @HungBM: 
 * @modify: Them cac ham nang cap tra cuu
 * @see: 20161212
 */
/* @HungBM: 
 * @modify: Lay du chieu pham vi doi chieu
 * @see: 20170320
 */
 /**
    * @modify: HungBM
    * @modify date: 04/05/2017   
    * @see: Update TT_DC_TU_DONG  = 02 chi khi Tao Dien Xac Nhan
    * @track: 20170504
    * */

  /**
    * @modify: HungBM
    * @modify date:05/05/2017
    * @see: Ham kiem tra dieu kien truoc khi cho phep tao dien 066
    * @Find: HungBM-20170505
    */

   /**
    * @modify: thuongdt
    * @modify-date: 22/05/2017
    * @see: bo sung tin kiem them tt_dc_tu_dong, ket_qua trong ham getDChieuList,getListDC1  dung check cho phep doi chieu lai thu cong khi doi chieu tu dong chenh lech
    * @find: thuongdt-20170522
    **/

    /**
     * @modify: thuongdt
     * @modify-date: 04/10/2017
     * @see: sua cau querry tim kiem chi tiet chenh lech doi chieu
     * @find: 20171004
     **/

     /**
      * @modify: thuongdt
      * @modify-date: 09/10/2017
      * @see: sua cau querry bo sung tim kiem lai chuyen thu duoc tach ra tu gd thu cong
      * @find: 20171009
      **/
public class DChieu1DAO extends AppDAO {

    private String strValueObject = "com.seatech.ttsp.dchieu.DChieu1DAO";
    private String strValueObjectVO = "com.seatech.ttsp.dchieu.DChieu1VO";
    private String strValueObjectKQVO = "com.seatech.ttsp.dchieu.KQDChieu1VO";
    private String strValueObjectKQCTVO =
        "com.seatech.ttsp.dchieu.KQDCChiTietVO";
    private String strValueObjectXNDCVO =
        "com.seatech.ttsp.dchieu.DuyetKQDCVO";
    private String strValueObjectXNKQDCDataVO =
        "com.seatech.ttsp.dchieu.XNKQDCDataVO";
    private String strValueObjectDNQTVO = "com.seatech.ttsp.dchieu.DNQTVO"; 
  private String strGDTCongVO = "com.seatech.ttsp.dchieu.GDichTCongVO";
  //20171122 thuongdt bo sung them moi chuc nang tra cuu
  private String THopQTThuChiVO = "com.seatech.ttsp.dchieu.THopQTThuChiVO";
    Connection conn = null;

    public DChieu1DAO(Connection conn) {
        this.conn = conn;
    }


    public Collection getCheckKQDXN(String strWhere,
                                    Vector vParam) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT id, tthai_dxn_thop, ngay_dc, receive_bank FROM sp_065 WHERE id IN (SELECT max(id) FROM sp_065 WHERE 1=1 " +
                    strWhere + " GROUP BY ngay_dc) AND tthai_dxn_thop ='01'  ";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getDChieuList(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }

  public Collection getCheckKQDC(String strWhere,
                                  Vector vParam) throws Exception {
      Collection reval = null;
      try {
          String strSQL = "";
          strSQL +=
                  " SELECT id, tthai_dxn_thop, ket_qua , trang_thai , ngay_dc, receive_bank FROM sp_065 WHERE id IN (SELECT max(id) FROM sp_065 WHERE 1=1 " +
                  strWhere + " GROUP BY ngay_dc)";
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getDChieuList(): " +
                               ex.getMessage(), ex);
          throw daoEx;
      }
      return reval;
  }

/**
 * @modify: thuongdt
 * @modify-date: 22/05/2017
 * @see: bo sung tin kiem them tt_dc_tu_dong dung check cho phep doi chieu lai thu cong khi doi chieu tu dong chenh lech
 * @find: thuongdt-20170522
 **/
    public Collection getListDC1(String nhkb_nhan, String strNHKB,String ma_nh, String tu_ngay,
                                 Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT   TO_CHAR (b.ngay, 'DD/MM/YYYY') ngay, a.id, c.id kq_id, a.trang_thai,c.ket_qua," +
                    " c.trang_thai trang_thai_kq, c.tthai_dxn_thop, c.tthai_ttin, c.tthai_ttin_thop,c.tt_dc_tu_dong, a.lan_dc, b.ma_nh send_bank, b.ten" +
                    "	 FROM (SELECT	TRUNC (ngay_dc) ngay_dc, id, receive_bank, send_bank," +
                    " ngay_thuc_hien, app_type, loai_dc, trang_thai, lan_dc FROM	sp_bk_dc1" +
                    " WHERE	1 = 1 AND receive_bank = '" + nhkb_nhan +
                    "' AND SUBSTR(id,3,3) <> '701') a, (SELECT	tthai_dxn_thop, trang_thai, ket_qua, id, bk_id, tthai_ttin," +
                    " tthai_ttin_thop,tt_dc_tu_dong FROM	sp_065 WHERE	trang_thai <> '04') c," +
                    " (SELECT   DISTINCT TRUNC (ngay_gd+1) ngay, d3.ma_nh ma_nh, d3.ten ten, d3.id nh_id" +
                    " FROM   sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_ngan_hang d3" +
                    " WHERE  d1.ma_nh_kb = d2.ma_nh AND d1.ma_nh = d3.ma_nh AND d1.ma_nh_kb = '" +
                    nhkb_nhan + "'";
                    if(ma_nh!=null&&!"".equals(ma_nh)){
//                      strSQL += " AND d1.ma_nh <> '"+ ma_nh+"'" ;
//                        strSQL += " AND d1.ma_nh = '"+ ma_nh+"'" ;
                    }
                      
                    strSQL += " AND sp_util_pkg.fnc_get_ngay_trien_khai(d1.ma_nh_kb,d3.ma_nh,'VND') <= d1.ngay_gd AND TRUNC (d1.ngay_gd+1) <= TRUNC (SYSDATE) "+
                              " AND TRUNC (d1.ngay_gd+1) >= sp_util_pkg.fnc_get_ngay_trien_khai(d1.ma_nh_kb, d1.ma_nh, 'VND') " +//ManhNV-20150423
                    " AND TO_CHAR (ngay_gd+1, 'YYYYMMDD') NOT IN (  SELECT   ngay FROM sp_ngay_nghi) ORDER BY   ngay) b " +
                    //" ,SP_TKNH_KB e" +
                    "	WHERE 		a.ngay_dc(+) = b.ngay and a.send_bank(+)=b.ma_nh" +
                    //"  AND b.nh_id=e.NH_ID(+) AND e.loai_tk<>'01' " +
                    " AND b.ngay IS NOT NULL AND a.id = c.bk_id(+) AND (a.app_type = 'TTSP' OR a.app_type IS NULL)" +
                    " AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE) AND ( (c.ket_qua = '1' OR a.trang_thai = '00')" +
                    " AND a.id IN (SELECT   MAX (id) FROM   sp_bk_dc1 WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE)" +
                    " AND app_type = 'TTSP' and send_bank=a.send_bank AND receive_bank = '" +
                    nhkb_nhan + "' GROUP BY   ngay_dc, send_bank))" +
                    " OR a.trang_thai IS NULL) OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE)  AND a.id IN" +
                    " (  SELECT   MAX (id) FROM   sp_bk_dc1 WHERE   TO_DATE (ngay_dc) = TO_DATE (SYSDATE)" +
                    " AND app_type = 'TTSP' and send_bank=a.send_bank AND receive_bank = '" +
                    nhkb_nhan + "' GROUP BY   ngay_dc, send_bank))" +
                    " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.trang_thai <> '00') OR (TRUNC (a.ngay_thuc_hien) = TRUNC (SYSDATE)))" +
                    " ORDER BY   send_bank, TO_CHAR (b.ngay, 'YYYYMMDDHH'), lan_dc DESC";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getDChieuList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getListViewCTietBCao(String strWhere,
                                           Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "  SELECT   a.id, c.id kq_id, a.trang_thai, c.trang_thai trang_thai_kq, c.tthai_ttin, " +
                    " c.tthai_dxn_Thop, a.lan_dc, a.send_bank, to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc," +
                    " a.receive_bank, a.send_bank, a.ngay_thuc_hien, a.app_type, a.lan_dc " +
                    " FROM   sp_bk_dc1 a, sp_065 c" +
                    "	WHERE 		a.id = c.bk_id(+) ";
            strSQL +=
                    strWhere + "  ORDER BY a.send_bank, TO_CHAR (a.ngay_dc, 'YYYYMMDDHH'), a.lan_dc DESC";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getDChieuList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

 
/**
 * @modify: thuongdt
 * @modify-date: 22/05/2017
 * @see: bo sung them tt_dc_tu_dong, ket_qua trong ket qua tra cuu
 * @find: thuongdt-20170522 
 * */
    public Collection getDChieuList(String strWhere,
                                    Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "SELECT a.id, b.id kq_id, a.lan_dc, a.loai_dc, to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc, a.send_bank, a.receive_bank, a.created_date, a.creator, a.manager, a.verified_date, " +
                    " a.somon_thu, a.sotien_thu, a.somon_chi, a.sotien_chi, a.somon_dtu, a.sotien_dtu, a.somon_tcong, decode(to_char(sysdate,'DD/MM/YYYY'),TO_CHAR (a.ngay_dc, 'DD/MM/YYYY'),a.sotien_tcong,null) sotien_tcong, " +
                    " a.sodu_daungay, a.so_du, a.ps_thu, a.ps_chi, a.so_ketchuyen, a.msg_id, a.trang_thai, a.insert_date, decode(to_char(sysdate,'DD/MM/YYYY'),TO_CHAR (a.ngay_dc, 'DD/MM/YYYY'),a.so_tien_thu_tcong,null) so_tien_thu_tcong, a.so_tien_thu_dtu, a.so_mon_thu_tcong, to_char(a.ngay_thuc_hien,'DD/MM/YYYY') ngay_thuc_hien, " +
                    //Lay them tran gthai dien xac nhan tong hop
                    " b.trang_thai trang_thai_kq,b.TTHAI_DXN_THOP TTHAI_DXN_THOP, " +
                    " b.tt_dc_tu_dong, b.ket_qua " +
                    " FROM sp_bk_dc1 a, sp_065 b WHERE 1=1 and a.id=b.bk_id(+) ";

            strSQL +=
                    strWhere + " ORDER BY a.send_bank desc, a.trang_thai asc";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getDChieuList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getKQDChieu(String strbke_id,
                                  Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "SELECT a.id, a.bk_id, a.ket_qua, a.lan_dc, to_char(a.ngay_dc,'DD/MM/YYYY HH24:mi:ss') ngay_dc, a.send_bank," +
                    " a.receive_bank, a.created_date, a.creator, a.manager," +
                    " a.verified_date, a.sodu_kbnn, a.chenh_lech, a.msg_id," +
                    " to_char(a.insert_date,'DD/MM/YYYY HH24:mi:ss') insert_date, a.trang_thai trang_thai_kqdc, a.ket_qua_pht," +
                    " b.ltt_kb_thua, b.ltt_kb_thieu, b.dts_kb_thieu, b.dts_kb_thua, c.trang_thai trang_thai_bkdc" +
                    " FROM sp_065 a, sp_bk_dc1 c, (  " + " select" +
                    "(select count(0) from sp_065_dtl where 1=1 and bkq_id='" +
                    strbke_id + "'" +
                    " and trang_thai = 1 and MT_TYPE = '103')  ltt_kb_thua ," +
                    "(Select count(0) from sp_065_dtl where 1=1 and bkq_id= '" +
                    strbke_id + "'" +
                    " and trang_thai = 0 and MT_TYPE = '103') ltt_kb_thieu," +
                    "(select count(0) from sp_065_dtl where 1=1 and bkq_id= '" +
                    strbke_id + "'" +
                    " and trang_thai = 0 and MT_TYPE in ('195' ,'196')) dts_kb_thieu," +
                    "(select count(0) from sp_065_dtl where 1=1 and bkq_id= '" +
                    strbke_id + "'" +
                    " and trang_thai = 1 and MT_TYPE in ('195' ,'196')) dts_kb_thua" +
                    " from dual) b where 1=1 and a.id='" + strbke_id + "'" +
                    " and a.trang_thai <> '04' and a.bk_id=c.id ";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectKQVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectKQVO + ".getKQDChieu(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getCheckTThai(String whereClause,
                                    Vector params) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    "SELECT	a.id, a.trang_thai, a.ket_qua, a.tthai_dxn_thop, b.trang_thai FROM	sp_065 a, sp_066 b " +
                    " WHERE a.id=b.kq_dc_ttsp (+)  and 	a.id = ( SELECT max(id) FROM sp_065 WHERE  ngay_dc IN (SELECT   DISTINCT TRUNC (max(d1.ngay_gd + 1)) ngay " +
                    " FROM   sp_tso_cutoftime d1 WHERE 	1 = 1 " +
                    whereClause;
            reval =
                    executeSelectStatement(strSQL.toString(), params, strValueObjectVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObject + ".getCheckTThai(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }
    //ManhNV-20150423
    public Collection getNgayBDau(String ma_kb, String ma_nh, String ngay_dc) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=  "select MAX(ngay_tk) ngay from "+
            " (select max(ngay_trien_khai) ngay_tk from sp_ngay_trien_khai where ma_kb = '"+ma_kb+"' and ma_nh = '"+ma_nh+"' and ma_nt = 'VND') "+
            " where ngay_tk < TO_DATE ('"+ngay_dc+"', 'DD-MM-YYYY') ";
            
            reval =
                    executeSelectStatement(strSQL.toString(), null, strValueObjectVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObject + ".getNgayBDau(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
 
//    public Collection getNgayBDau(String whereClause,
//                                  Vector params) throws Exception {
//        Collection reval = null;
//        try {
//            String strSQL = "";
//            strSQL +=
//                    "SELECT	DISTINCT TRUNC (max(d1.ngay_gd+1)) ngay FROM	sp_tso_cutoftime d1 WHERE 1 = 1" +
//                    whereClause;
//            reval =
//                    executeSelectStatement(strSQL.toString(), params, strValueObjectVO,
//                                           conn);
//        } catch (Exception ex) {
//            DAOException daoEx =
//                new DAOException(strValueObject + ".getCheckTThai(): " +
//                                 ex.getMessage(), ex);
////            daoEx.printStackTrace();
//            throw daoEx;
//        }
//        return reval;
//    }


    public Collection getKQDCChiTiet(String strbke_id,
                                     Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";
          //20171004 thuongdt sua cau querry bo sung tim kiem chi tiet chenh lech
   
          strSQL +=
                 "WITH SP_065_DTL_CLech AS (SELECT mt_id," + 
                 "    CASE WHEN a.f32as3 <> b.f32as3 then 'SO_TIEN(f32as3); ' ELSE '' END ||" + 
                 "    CASE WHEN a.send_bank <> b.send_bank then 'SEND_BANK; ' ELSE '' END ||" + 
                 "    CASE WHEN a.receive_bank <> b.receive_bank then 'RECEIVE_BANK; ' ELSE '' END ||" + 
                 "    CASE WHEN a.ngay_ct <> b.ngay_ct then 'NGAY_CT; ' ELSE '' END ||" + 
                 "    CASE WHEN a.f20 <> b.f20 then 'F20; ' ELSE '' END ||" + 
                 "    CASE WHEN a.send_date <> b.send_date then 'SEND_DATE; ' ELSE '' END ||" + 
                 "    CASE WHEN a.ngay_ts <> b.ngay_ts then 'NGAY_TS; ' ELSE '' END ||" + 
                 "    CASE WHEN a.loai_tien <> b.loai_tien then 'LOAI_TIEN; ' ELSE '' END " + 
                 "    as ly_do," +
                 "    CASE WHEN a.f32as3 <> b.f32as3 then decode(a.TRANG_THAI,1,'KB:','NH:') ||a.f32as3||','|| decode(b.TRANG_THAI,1,'KB:','NH:') ||b.f32as3||'; ' ELSE '' END ||" + 
                 "    CASE WHEN a.send_bank <> b.send_bank then decode(a.TRANG_THAI,1,'KB:','NH:') ||a.send_bank||','|| decode(b.TRANG_THAI,1,'KB:','NH:') ||b.send_bank||'; ' ELSE '' END ||" + 
                 "    CASE WHEN a.receive_bank <> b.receive_bank then decode(a.TRANG_THAI,1,'KB:','NH:') ||a.receive_bank||','|| decode(b.TRANG_THAI,1,'KB:','NH:') ||b.receive_bank||'; ' ELSE '' END ||" + 
                 "    CASE WHEN a.ngay_ct <> b.ngay_ct then decode(a.TRANG_THAI,1,'KB:','NH:') ||a.ngay_ct||','|| decode(b.TRANG_THAI,1,'KB:','NH:') ||b.ngay_ct||'; ' ELSE '' END ||" + 
                 "    CASE WHEN a.f20 <> b.f20 then decode(a.TRANG_THAI,1,'KB:','NH:')||a.f20||','|| decode(b.TRANG_THAI,1,'KB:','NH:')||b.f20||'; ' ELSE '' END ||" + 
                 "    CASE WHEN a.send_date <> b.send_date then decode(a.TRANG_THAI,1,'KB:','NH:') ||a.send_date||','|| decode(b.TRANG_THAI,1,'KB:','NH:') ||b.send_date||'; ' ELSE '' END ||" + 
                 "    CASE WHEN a.ngay_ts <> b.ngay_ts then decode(a.TRANG_THAI,1,'KB:','NH:') ||a.ngay_ts||','|| decode(b.TRANG_THAI,1,'KB:','NH:') ||b.ngay_ts||'; ' ELSE '' END ||" + 
                 "    CASE WHEN a.loai_tien <> b.loai_tien then decode(a.TRANG_THAI,1,'KB:','NH:') ||a.loai_tien||','|| decode(b.TRANG_THAI,1,'KB:','NH:') ||b.loai_tien||'; ' ELSE '' END     as ctiet_ly_do "+
                 " FROM sp_065_dtl a" + 
                 " JOIN sp_065_dtl b using(mt_id) WHERE a.bkq_id = b.bkq_id AND a.trang_thai = '0' AND b.trang_thai = '1' AND a.bkq_id = '"+strbke_id+"') "+
          
                  //noi dung cau sql goc truoc khi sua          
                  "SELECT a.id, a.bkq_id, a.bk_id, a.mt_id, to_char(a.send_date,'DD/MM/YYYY HH24:mi:ss') send_date, a.f20, a.f21, a.tthai_duyet," +
                  " a.f32as3, to_char(a.ngay_ts,'DD/MM/YYYY') ngay_ts, a.ghi_chu, a.mt_type, a.app_type," +
                  " a.sai_yeu_to, a.trang_thai, a.insert_date, to_char(a.ngay_ct,'DD/MM/YYYY') ngay_ct, decode(substr(a.mt_id,3,3),'701','DI','DEN') di_den, decode(substr(a.mt_id,6,3),'196','DTS','195','DTS','LTT') ltt_dts," +
                  " c.send_bank, (select ten from sp_dm_ngan_hang where ma_nh=c.send_bank) ten_send_bank," +
                  " c.receive_bank, b.ten, " +
                 //bo sung them truong the hien chi tiet chenh lech
                 " dlt.ly_do ldo_clech,dlt.ctiet_ly_do ctiet_clech "+
                  " FROM sp_065_dtl a, sp_dm_ngan_hang b, sp_065 c, SP_065_DTL_CLech dlt" +                
                  " where 1=1 and a.bkq_id= c.id and c.receive_bank=b.ma_nh " +
                 //join bang tam
                  " and a.mt_id = dlt.mt_id(+) " +
                  " and a.bkq_id in ('" + strbke_id + "')";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectKQCTVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectKQVO + ".getKQDCChiTiet(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public KQDChieu1VO getKQPHT(String whereClause,
                                Vector params) throws Exception {
        try {
            String strSQL = "";
            strSQL +=
                    "SELECT a.id, a.bk_id, a.ket_qua, a.lan_dc, a.ngay_dc, a.send_bank," +
                    " a.receive_bank, a.created_date, a.creator, a.manager, a.ket_chuyen_thu, a.ket_chuyen_chi," +
                    " a.verified_date, a.sodu_kbnn, a.chenh_lech, a.msg_id," +
                    " a.insert_date, a.ket_qua_pht, a.tong_ps_pht," +
                    " a.tong_mon_pht, a.app_type, a.ly_do_xn, a.mon_thu_tcong_kbnn," +
                    " a.mon_chi_tcong_kbnn, a.tien_thu_tcong_kbnn," +
                    " a.tien_chi_tcong_kbnn, a.tthai_dxn_thop, a.chenh_lech_thu_tcong," +
                    " a.chenh_lech_chi_tcong, a.trang_thai trang_thai_kqdc, b.trang_thai trang_thai_bkdc, b.sotien_tcong, b.so_tien_thu_tcong, b.so_du, b.sodu_daungay  FROM sp_065 a, sp_bk_dc1 b WHERE 1=1 and a.bk_id=b.id(+) " +
                    whereClause;
            KQDChieu1VO phtVO =
                (KQDChieu1VO)findByPK(strSQL.toString(), params,
                                      strValueObjectKQVO, conn);
            return phtVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObject + ".getKQPHT(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }


  public DNQTVO chkDNQT(String strKB,
                              Vector params) throws Exception {
      try {
          String strSQL = "";
          strSQL +=
                  "SELECT	SUM (cho_duyet) chk_duyet, SUM (qtoan) chk_qtoan,sum(qtoan_tdong) qtoan_tdong, to_char(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE),'DD/MM/YYYY') ngay_chk " + 
                  " FROM	(SELECT	 COUNT (0) cho_duyet, 0 AS qtoan, 0 as qtoan_tdong " + 
                  " FROM	 sp_066 WHERE	 trunc(ngay_qtoan) <=  trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE - 1)) AND trang_thai = '01'"+ strKB  +  
                  " UNION ALL " + 
//ManhNV rao
//                  " SELECT	 0 AS cho_duyet, COUNT (0) AS qtoan, 0 as qtoan_tdong FROM	 sp_066 " + 
//                  " WHERE	 trunc(ngay_qtoan) <=  trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE - 1))" + strKB+  
//                  " AND id NOT IN (SELECT	 mt_refid FROM sp_quyet_toan)" +
//                  " union all " + 
                  " SELECT  0 as  cho_duyet, 0 AS qtoan, count(0) as qtoan_tdong " + 
                  "  FROM  sp_066 " + 
                  " WHERE  trunc(ngay_qtoan) <=  trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE - 1))" + strKB+  
                  " AND trang_thai = '01' and loai_qtoan ='01')" ;
          DNQTVO phtVO =
              (DNQTVO)findByPK(strSQL.toString(), params,
                                    strValueObjectDNQTVO, conn);
          return phtVO;
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObject + ".getKQPHT(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
  }





    public int updateKQ(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_065 set ");
        strSQL.append(" trang_thai = '01'");

        //        strSQL.append(", ket_qua = '?' ");
        //        v_param.add(new Parameter(vo.getKet_qua(), true));

        strSQL.append(" where trang_thai='00' and id = ? ");
        v_param.add(new Parameter(vo.getId(), true));
        exc = executeStatement(strSQL.toString(), v_param, conn);

        return exc;
    }

    public int updateKQDC1(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        if (vo.getTtsp_id() != null && !"".equals(vo.getTtsp_id())) {
            strSQL.append("update sp_065 set ");

            if (!"".equals(vo.getTien_thu_tcong_kbnn())) {
                strSQL.append(" tien_thu_tcong_kbnn=?,");
                v_param.add(new Parameter(vo.getTien_thu_tcong_kbnn(), true));
            }
            if (!"".equals(vo.getTien_chi_tcong_kbnn())) {
                strSQL.append(" tien_chi_tcong_kbnn=?");
                v_param.add(new Parameter(vo.getTien_chi_tcong_kbnn(), true));
            }

            strSQL.append(" where  id = ? ");
            v_param.add(new Parameter(vo.getTtsp_id(), true));
            exc = executeStatement(strSQL.toString(), v_param, conn);
        }
        return exc;
    }


  public int updateTTBK(KQDChieu1VO vo) throws Exception {
          int exc = 0;
          Vector v_param = new Vector();
          StringBuffer strSQL = new StringBuffer();
          strSQL.append("update sp_bk_dc1 set ");
          
          if ("1".equals(vo.getKet_qua())) {
              strSQL.append("trang_thai = '01' ");
              strSQL.append(", ngay_thuc_hien= to_date(sysdate) ");
            //20170504 - BEGIN
            if (!"".equals(vo.getTt_dc_tu_dong()) && vo.getTt_dc_tu_dong()!= null){
              strSQL.append(", TT_DC_TU_DONG = '02' ");
            }
            //20170504 - END
          } else if ("0".equals(vo.getKet_qua())) {
              strSQL.append(" trang_thai = '02' ");
              strSQL.append(", ngay_thuc_hien= to_date(sysdate) ");
            //20170504 - BEGIN
            if (!"".equals(vo.getTt_dc_tu_dong()) && vo.getTt_dc_tu_dong()!= null){
              strSQL.append(", TT_DC_TU_DONG = '02' ");
            }
            //20170504 - END
          }
        
          strSQL.append(" where trang_thai='00' and id = ? ");
          v_param.add(new Parameter(vo.getBk_id(), true));
          exc = executeStatement(strSQL.toString(), v_param, conn);

          return exc;
      }
    // Duyet Xac nhan doi chieu

    public Collection getListXNDChieu_066(String strWhere,String tu_ngay,
                                          Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";
       //20171121 thuongdt bo sung them ten ngan hang: b.ten
            strSQL +=
                    "SELECT distinct	a.id, a.bk_id,b.ten, TO_CHAR (b.ngay, 'DD/MM/YYYY') ngay_dc," +
                    "			TO_CHAR (a.ngay_thuc_hien_dc, 'DD/MM/YYYY') ngay_thuc_hien_dc," +
                    "			a.ket_qua, a.lan_dc, a.send_bank, a.trang_thai, a.tthai_dxn_thop," +
                    "			b.ma_nh receive_bank, a.ket_qua_dxn_thop, a.tthai_ttin," +
                    "			a.tthai_ttin_thop" +
                    "  FROM	(SELECT	 e.ngay_dc, e.trang_thai, e.id, e.tthai_dxn_thop," +
                    " e.tthai_ttin_thop, e.receive_bank, e.ngay_thuc_hien_dc," +
                    " e.tthai_ttin, e.send_bank, e.lan_dc, e.ket_qua, e.bk_id," +
                    "  e.ket_qua_dxn_thop FROM	 sp_065 e WHERE" +
                    " id in ( select max(id) from sp_065 WHERE	app_type = 'TTSP' AND send_bank = '"+strWhere+"' group by ngay_dc, receive_bank, send_bank)) a," +
                    " (	SELECT	DISTINCT TRUNC (ngay_gd + 1) ngay," +
                    " d3.ma_nh ma_nh, d3.ten ten" +
                    " FROM	sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_ngan_hang d3" +
                    " WHERE		 d1.ma_nh_kb = d2.ma_nh" +
                    " AND d1.ma_nh = d3.ma_nh AND d1.ma_nh_kb = '" + strWhere + "'" +
                    " AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" + 
                    " AND TRUNC (d1.ngay_gd+1) >= SYSDATE - 30" +
                    " AND TRUNC (d1.ngay_gd+1) >= sp_util_pkg.fnc_get_ngay_trien_khai(d1.ma_nh_kb, d1.ma_nh, 'VND') " +//ManhNV-20150423
                    " AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN (	SELECT	ngay FROM sp_ngay_nghi)" +
                     " ORDER BY	ngay) b, sp_066 c " +
                    " WHERE	a.id = c.kq_dc_ttsp(+) and a.ngay_dc(+) = b.ngay AND a.receive_bank(+) = b.ma_nh  AND (tthai_dxn_thop = '01' or a.tthai_dxn_thop is null OR (a.tthai_dxn_thop='00' AND c.trang_thai is not null) OR ( (a.tthai_dxn_thop = '02' or a.tthai_dxn_thop = '03'  or c.trang_thai='03')" +
                    " AND TO_DATE (ngay_thuc_hien_dc) = TO_DATE (SYSDATE)) OR (a.tthai_dxn_thop = '02' and c.trang_thai='01')) ORDER BY   TO_CHAR (b.ngay, 'DD/MM/YYYY')";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNDCVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNDCVO + ".getListXNDChieu(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getListXNDChieu(String strWhere,
                                      Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "SELECT DISTINCT a.id, a.bk_id, to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc,to_char(a.ngay_thuc_hien_dc,'DD/MM/YYYY') ngay_thuc_hien_dc,a.ket_qua, a.lan_dc, a.send_bank, a.trang_thai, a.tthai_dxn_thop, a.receive_bank, a.ket_qua_dxn_thop, a.tthai_ttin, a.tthai_ttin_thop" +
                    " FROM sp_065 a, sp_066 b WHERE a.id=b.kq_dc_ttsp (+) ";

            strSQL +=
                    strWhere; //+ " ORDER BY a.send_bank, a.ngay_dc,  a.lan_dc desc";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNDCVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNDCVO + ".getListXNDChieu(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getListView_066(String strWhere, String ma_kb,
                                      Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "SELECT	a.id, a.bk_id, TO_CHAR (b.ngay, 'DD/MM/YYYY') ngay_dc," +
                    " TO_CHAR (a.ngay_thuc_hien_dc, 'DD/MM/YYYY') ngay_thuc_hien_dc," +
                    " a.ket_qua, a.lan_dc, a.send_bank, a.trang_thai, a.tthai_dxn_thop," +
                    " b.ma_nh receive_bank, a.ket_qua_dxn_thop, a.tthai_ttin, a.tthai_ttin_thop" +
                    "  FROM	(SELECT	 e.ngay_dc, e.trang_thai, e.id, e.tthai_dxn_thop," +
                    " e.tthai_ttin_thop, e.receive_bank, e.ngay_thuc_hien_dc," +
                    " e.tthai_ttin, e.send_bank, e.lan_dc, e.ket_qua, e.bk_id, e.ket_qua_dxn_thop" +
                    " FROM	 sp_065 e WHERE	 e.app_type = 'TTSP' AND e.send_bank = '" +
                    ma_kb + "') a," +
                    " (	SELECT	DISTINCT TRUNC (ngay_gd + 1) ngay, d3.ma_nh ma_nh, d3.ten ten" +
                    " FROM	sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_ngan_hang d3" +
                    "	 WHERE d1.ma_nh_kb = d2.ma_nh AND d1.ma_nh = d3.ma_nh AND d1.ma_nh_kb = '" +
                    ma_kb + "'" +
                    " AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE) AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
                    " (	SELECT	ngay FROM sp_ngay_nghi) ORDER BY	ngay) b " +
                    " WHERE a.ngay_dc(+) = b.ngay AND a.receive_bank(+) = b.ma_nh ";

            strSQL +=
                    strWhere; //+ " ORDER BY a.send_bank, a.ngay_dc,  a.lan_dc desc";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNDCVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNDCVO + ".getListXNDChieu(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }


    public int updateTT(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_bk_dc1 set ");

        strSQL.append("trang_thai = '00' ");

        strSQL.append(" where id = ? ");
        v_param.add(new Parameter(vo.getBk_id(), true));
        exc = executeStatement(strSQL.toString(), v_param, conn);

        return exc;
    }

    public Collection getXNKQData(String strbke_id,
                                  Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT a.id, a.bk_id, a.ket_qua, a.lan_dc, to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc, a.send_bank, a.receive_bank, a.created_date, a.sodu_kbnn, a.app_type, " +
                    " a.chenh_lech, to_char(a.insert_date,'DD/MM/YYYY HH24:mi:ss') insert_date, a.trang_thai trang_thai_kqdc, a.ket_qua_pht, " +
                    " a.mon_thu_dtu_kbnn, a.mon_chi_dtu_kbnn, a.tien_thu_dtu_kbnn, a.tien_chi_dtu_kbnn, " +
                    " a.mon_thu_tcong_kbnn, a.mon_chi_tcong_kbnn, a.tien_thu_tcong_kbnn, a.tien_chi_tcong_kbnn, abs(a.tien_thu_tcong_kbnn-c.so_tien_thu_tcong) chenhlech_thu, abs(a.tien_chi_tcong_kbnn-c.sotien_tcong) chenhlech_chi," +
                    " a.so_du_dau_ngay, a.ket_chuyen_chi, a.ket_chuyen_thu," +
                    " b.ltt_kb_thua, b.ltt_kb_thieu, b.dts_kb_thieu, b.dts_kb_thua," +
                    " c.sotien_thu, c.sotien_chi, c.sotien_dtu, c.sotien_tcong," +
                    "	c.somon_thu, c.somon_chi, c.somon_dtu, c.somon_tcong," +
                    "	c.so_mon_thu_tcong, c.so_mon_thu_dtu, c.so_ketchuyen, c.sodu_daungay," +
                    "	c.so_du, c.trang_thai trang_thai_bkdc, c.so_tien_thu_tcong, c.ps_chi," +
                    "	c.so_tien_thu_dtu " + " FROM sp_065 a, sp_bk_dc1 c,( " +
                    " select " +
                    " (select count(0) from sp_065_dtl where 1=1 and bkq_id in (" +
                    strbke_id + ")" +
                    "  and trang_thai = 1 and MT_TYPE = '103')  ltt_kb_thua , " +
                    " (Select count(0) from sp_065_dtl where 1=1 and bkq_id in (" +
                    strbke_id + ")" +
                    "  and trang_thai = 0 and MT_TYPE = '103') ltt_kb_thieu, " +
                    " (select count(0) from sp_065_dtl where 1=1 and bkq_id in (" +
                    strbke_id + ")" +
                    "  and trang_thai = 0 and MT_TYPE in ('195' ,'196')) dts_kb_thieu, " +
                    " (select count(0) from sp_065_dtl where 1=1 and bkq_id in (" +
                    strbke_id + ")" +
                    "  and trang_thai = 1 and MT_TYPE in ('195' ,'196')) dts_kb_thua " +
                    " from dual) b where 1=1 and a.id in (" + strbke_id + ")" +
                    " and a.bk_id=c.id ORDER BY app_type";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNKQDCDataVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNKQDCDataVO + ".getXNKQData(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getTTSP_PHT(String strbke_id,
                                  Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";
 
            strSQL +=
                    " SELECT a.id, a.bk_id, a.ket_qua, a.sodu_kbnn, a.app_type, a.send_bank, a.receive_bank,a.tthai_dxn_thop, " +
                    " a.chenh_lech,  a.ket_qua_pht, to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc, " +
                    " a.mon_thu_dtu_kbnn, a.mon_chi_dtu_kbnn, a.tien_thu_dtu_kbnn, " +
                    "decode(a.tt_dc_tu_dong,'THUCONG', a.tien_chi_dtu_kbnn - (select decode(sum(g.F32AS3), null,0,sum(g.F32AS3)) so_tien_dtl from SP_065_DTL g,sp_ltt d where g.MT_ID = d.ID and d.TRANG_THAI = '15' and g.BKQ_ID=  a.ID) ,a.tien_chi_dtu_kbnn) tien_chi_dtu_kbnn, " +
                    " a.mon_thu_tcong_kbnn, a.mon_chi_tcong_kbnn, a.tien_thu_tcong_kbnn, a.tien_chi_tcong_kbnn," +
                    " a.so_du_dau_ngay, a.ket_chuyen_chi, a.ket_chuyen_thu,a.tong_ps_pht,a.tong_mon_pht," +
                    " c.sotien_thu, c.sotien_chi, c.sotien_dtu, c.sotien_tcong," +
                    " c.somon_thu, c.somon_chi, c.somon_dtu, c.somon_tcong," +
                    " c.so_mon_thu_tcong, c.so_mon_thu_dtu, c.so_ketchuyen, c.sodu_daungay," +
                    " c.so_du, c.trang_thai trang_thai_bkdc, c.so_tien_thu_tcong, c.ps_chi," +
                    " c.so_tien_thu_dtu " +
                    " FROM sp_065 a, sp_bk_dc1 c where 1=1 and a.id in (" +
                    strbke_id + " )" +
                    " and a.bk_id=c.id(+) ORDER BY a.app_type desc";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNKQDCDataVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNKQDCDataVO + ".getXNKQData(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }
    
  public Collection get065ByID(String strId) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";
  
          strSQL +=
                  " SELECT a.id, a.bk_id, a.ket_qua, a.sodu_kbnn, a.app_type, a.send_bank, a.receive_bank,a.tthai_dxn_thop, " +
                  " a.chenh_lech,  a.ket_qua_pht, to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc, " +
                  " a.mon_thu_dtu_kbnn, a.mon_chi_dtu_kbnn, a.tien_thu_dtu_kbnn, " +
                  "decode(a.tt_dc_tu_dong,'THUCONG', a.tien_chi_dtu_kbnn - (select decode(sum(g.F32AS3), null,0,sum(g.F32AS3)) so_tien_dtl from SP_065_DTL g,sp_ltt d where g.MT_ID = d.ID and d.TRANG_THAI = '15' and g.BKQ_ID=  a.ID) ,a.tien_chi_dtu_kbnn) tien_chi_dtu_kbnn, " +
                  " a.mon_thu_tcong_kbnn, a.mon_chi_tcong_kbnn, a.tien_thu_tcong_kbnn, a.tien_chi_tcong_kbnn," +
                  " a.so_du_dau_ngay, a.ket_chuyen_chi, a.ket_chuyen_thu,a.tong_ps_pht,a.tong_mon_pht," +
                  " c.sotien_thu, c.sotien_chi, c.sotien_dtu, c.sotien_tcong," +
                  " c.somon_thu, c.somon_chi, c.somon_dtu, c.somon_tcong," +
                  " c.so_mon_thu_tcong, c.so_mon_thu_dtu, c.so_ketchuyen, c.sodu_daungay," +
                  " c.so_du, c.trang_thai trang_thai_bkdc, c.so_tien_thu_tcong, c.ps_chi," +
                  " c.so_tien_thu_dtu " +
                  " FROM sp_065 a, sp_bk_dc1 c where 1=1 and a.id = '" + strId + "'" +
                  " and a.bk_id=c.id(+) ORDER BY a.app_type desc";

          reval =
                  executeSelectStatement(strSQL.toString(), null, strValueObjectXNKQDCDataVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectXNKQDCDataVO + ".getXNKQData(): " +
                               ex.getMessage(), ex);
  //            daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
  
 /**
  * @create: thuongdt
  * @Date: 09/10/2017
  * @see: them moi getPHT_PS_T7 ham phuc vu tr cuu bang ke PHT ngay nghi
  * @param: ma_nh ma ngan hang, ma_KB ma kho bac, ngay_ps ngay phat sinh
  * */   
  public Collection getPHT_PS_T7(String ma_nh,String ma_KB,String ngay_ps) throws Exception { 
    Collection reval = null; 
    ResultSet rs = null;
    try {
      String strSQL = "";      
       strSQL = "select sum(tong_mon_pht) tong_mon_pht,sum(tong_ps_pht) tong_ps_pht, sum(mon_thu_dtu_kbnn)mon_thu_dtu_kbnn,sum(tien_thu_dtu_kbnn)tien_thu_dtu_kbnn from sp_065 " +
           " where send_bank='"+ma_KB+"'and receive_bank='"+ma_nh+"' " +
           " and ngay_dc > sp_util_pkg.fnc_get_ngay_lam_viec_truoc(to_date('"+ngay_ps+"','dd/MM/yyyy')-1) " + 
           " and ngay_dc<to_Date('"+ngay_ps+"','dd/mm/yyyy') group by send_bank,receive_bank";       
      reval =
            executeSelectStatement(strSQL.toString(), null, strValueObjectXNKQDCDataVO,
                                   conn);      
    } catch (Exception ex) {
    DAOException daoEx =
        new DAOException(strValueObjectXNKQDCDataVO + ".getXNKQData(): " +
                         ex.getMessage(), ex);
    //            daoEx.printStackTrace();
    throw daoEx;
    }
    return reval;
  }

    public Collection getThuChi(String whereClause,
                                Vector vParam) throws Exception {

        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT  a.ket_chuyen_chi, a.ket_chuyen_thu FROM sp_065 a, sp_bk_dc1 b WHERE 1=1 and a.bk_id=b.id and a.id in (" +
                    whereClause + " )";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectKQVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectKQVO + ".getXNKQData(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }


    public Collection getXNTHData(String strbke_id,
                                  Vector vParam) throws Exception {

        Collection reval = null;
        try {
 
            String strSQL = "";

            strSQL +=
                    " SELECT distinct	a.id, a.sodu_kbnn, a.chenh_lech, a.tien_thu_dtu_kbnn," +
                    " a.tien_thu_tcong_kbnn,a.tien_chi_tcong_kbnn, a.so_du_dau_ngay, " +
                "decode(a.tt_dc_tu_dong,'THUCONG', a.ket_chuyen_chi- (select decode(sum(g.F32AS3), null,0,sum(g.F32AS3))so_tien_dtl from SP_065_DTL g,sp_ltt d where g.MT_ID = d.ID and d.TRANG_THAI = '15' and g.BKQ_ID=  a.ID) ,a.ket_chuyen_chi) ket_chuyen_chi,  " +
                "" +
                    " c.sodu_daungay, a.ket_chuyen_thu, c.sotien_thu, c.sotien_chi," +
                    " c.sotien_dtu, c.sotien_tcong, c.so_ketchuyen, c.so_du," +
                    " c.so_tien_thu_tcong, c.ps_chi, c.so_tien_thu_dtu" +
                    " FROM	sp_065 a, sp_bk_dc1 c" +
                    
                    " WHERE	1 = 1 AND c.id (+) = a.bk_id  AND a.id IN(" + strbke_id + ")";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNKQDCDataVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNKQDCDataVO + ".getXNKQData(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }
    
    public String getLaiChuyenThu(String strWhere) throws DatabaseConnectionFailureException,
                                                          SelectStatementException,
                                                          SQLException {
        String strReturn = "0";
        String strSQL = "SELECT	  a.ma_kb, a.ma_nh, a.ngay_gd,sum(nvl(a.so_thu,0)) lai_chuyen_thu " + 
       " FROM	sp_gd_thu_cong a "  + 
        "  WHERE a.NSD_ID is null  "+strWhere+"  group by a.ma_kb, a.ma_nh, a.ngay_gd";
       ResultSet rs = executeSelectStatement(strSQL, null, conn);
       if(rs.next()){
          strReturn = rs.getString("lai_chuyen_thu");
        }
        return strReturn;        
    }
    
   /**
  * @create: thuongdt
  * @Date: 09/10/2017
  * @see: them moi getXNTHData_PS_T7 ham phuc vu tr cuu bang ke PHT ngay nghi
  * @param: strbke_id ma bang ke,ma_nh ma ngan hang, ma_KB ma kho bac, ngay_ps ngay phat sinh
  * */   
  public Collection getXNTHData_PS_T7(String strbke_id,
                                Vector vParam,String ma_nh,String ma_KB,String ngay_ps) throws Exception {

      Collection reval = null;
      try {
  
          String strSQL = "";

          strSQL +=
                  " SELECT distinct a.id, a.sodu_kbnn, a.chenh_lech, a.tien_thu_dtu_kbnn," +
                  " a.tien_thu_tcong_kbnn,a.tien_chi_tcong_kbnn, a.so_du_dau_ngay, " +
                  " decode(a.tt_dc_tu_dong,'THUCONG', a.ket_chuyen_chi- (select decode(sum(g.F32AS3), null,0,sum(g.F32AS3))so_tien_dtl from SP_065_DTL g,sp_ltt d where g.MT_ID = d.ID and d.TRANG_THAI = '15' and g.BKQ_ID=  a.ID) ,a.ket_chuyen_chi) ket_chuyen_chi,  " +
                   // bo sung ngay thu 7
                  " c.sodu_daungay, (a.ket_chuyen_thu + nvl(d.tong_ps_pht,0)) ket_chuyen_thu," +
                 " c.sotien_thu, c.sotien_chi," +
                  " c.sotien_dtu, c.sotien_tcong, c.so_ketchuyen, c.so_du," +
                  " c.so_tien_thu_tcong, c.ps_chi, c.so_tien_thu_dtu" +
                  " FROM  sp_065 a, sp_bk_dc1 c," +                  
                  // bo sung ngay thu 7
                  " (select send_bank, receive_bank, sum(tong_mon_pht) tong_mon_pht,sum(tong_ps_pht) tong_ps_pht," +
                  " sum(mon_thu_dtu_kbnn)mon_thu_dtu_kbnn,sum(tien_thu_dtu_kbnn) tien_thu_dtu_kbnn from sp_065 " +
                  " where send_bank='"+ma_KB+"'and receive_bank='"+ma_nh+"' " +
                  " and ngay_dc > sp_util_pkg.fnc_get_ngay_lam_viec_truoc(to_date('"+ngay_ps+"','dd/MM/yyyy')-1) " + 
                  " and ngay_dc<to_Date('"+ngay_ps+"','dd/mm/yyyy') group by send_bank, receive_bank) d "+
                  
                  " WHERE 1 = 1 AND c.id (+) = a.bk_id  AND a.id IN(" + strbke_id + ")" +
                  // bo sung ngay thu 7
                  " and a.send_bank = d.send_bank(+) and a.receive_bank = d.receive_bank(+)" +
                  "";

          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNKQDCDataVO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectXNKQDCDataVO + ".getXNKQData(): " +
                               ex.getMessage(), ex);
  //            daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
 /**
  * 
  * 
  * 
  **/   
  public Collection getSoTCong(String strWhere,
                                Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";

//          strSQL +=
//                  " SELECT	 a.ma_kb, a.ma_nh, a.ngay_gd,  sum(nvl(a.so_thu,0)) so_thu, sum(nvl(a.so_chi,0)) so_chi," +
//                  " vietnamesenumbertowords.CONVERT (SUM (NVL (a.so_thu, 0))) so_thu_chu," + 
//                  " vietnamesenumbertowords.CONVERT (SUM (NVL (a.so_chi, 0))) so_chi_chu" + 
//                  "  FROM	sp_gd_thu_cong a WHERE 1=1 " +strWhere + " group by a.ma_kb, a.ma_nh, a.ngay_gd";
  
       //20171009 thuongdt sua sql bo sung tra cuu so lai chuyen thu
//       strSQL +="select  a.ma_kb, a.ma_nh, a.ngay_gd,  so_thu, so_chi, so_thu_chu, so_chi_chu,nvl(b.lai_chuyen_thu,0)lai_chuyen_thu,b.lai_chuyen_thu_chu   from ( " + 
//       " SELECT	 a.ma_kb, a.ma_nh, a.ngay_gd,  sum(nvl(a.so_thu,0)) so_thu, sum(nvl(a.so_chi,0)) so_chi, " + 
//       " vietnamesenumbertowords.CONVERT (SUM (NVL (a.so_thu, 0))) so_thu_chu, " + 
//       " vietnamesenumbertowords.CONVERT (SUM (NVL (a.so_chi, 0))) so_chi_chu " + 
//       " FROM	sp_gd_thu_cong a WHERE a.NSD_ID is not null "+strWhere+     
//       " group by a.ma_kb, a.ma_nh, a.ngay_gd)a, " + 
//       " (SELECT	  a.ma_kb, a.ma_nh, a.ngay_gd,sum(nvl(a.so_thu,0)) lai_chuyen_thu,vietnamesenumbertowords.CONVERT (SUM (NVL (a.so_thu, 0))) lai_chuyen_thu_chu  " + 
//       " FROM	sp_gd_thu_cong a WHERE a.NSD_ID is null "+strWhere+
//       " group by a.ma_kb, a.ma_nh, a.ngay_gd)b " + 
//       " where a.ma_kb = b.ma_kb(+) and a.ma_nh = b.ma_nh(+)";
//          
        strSQL +="select  b.ma_kb, b.ma_nh, b.ngay_gd,  nvl(so_thu,0) so_thu, nvl(so_chi,0) so_chi, vietnamesenumbertowords.CONVERT (nvl(so_thu,0)) so_thu_chu,vietnamesenumbertowords.CONVERT (nvl(so_chi,0)) so_chi_chu,nvl(b.lai_chuyen_thu,0)lai_chuyen_thu," + 
        " vietnamesenumbertowords.CONVERT (nvl(b.lai_chuyen_thu,0)) lai_chuyen_thu_chu " + 
        " from " + 
        " (SELECT	DISTINCT  a.ma_kb, a.ma_nh  " + 
        " FROM	sp_gd_thu_cong a WHERE 1=1 "+strWhere+") c," + 
        " ( " + 
        " SELECT	 a.ma_kb, a.ma_nh, a.ngay_gd,  sum(nvl(a.so_thu,0)) so_thu, sum(nvl(a.so_chi,0)) so_chi" + 
        " FROM	sp_gd_thu_cong a " + 
        " WHERE a.NSD_ID is not null  "+strWhere+"  group by a.ma_kb, a.ma_nh, a.ngay_gd)a, " + 
        " (SELECT	  a.ma_kb, a.ma_nh, a.ngay_gd,sum(nvl(a.so_thu,0)) lai_chuyen_thu" + 
        " FROM	sp_gd_thu_cong a " + 
        " WHERE a.NSD_ID is null  "+strWhere+"  group by a.ma_kb, a.ma_nh, a.ngay_gd)b  " + 
        " where c.ma_kb = a.ma_kb(+) and c.ma_nh = a.ma_nh(+)" + 
        " and c.ma_kb = b.ma_kb(+) and c.ma_nh = b.ma_nh(+)";

          reval = executeSelectStatement(strSQL.toString(), vParam, strGDTCongVO, conn);
      } catch (Exception ex) {
          DAOException daoEx = new DAOException(strGDTCongVO + ".getSoTCong(): " + ex.getMessage(), ex);
          throw daoEx;
      }
      return reval;
  }
    

    public Collection getData066(String strWhere,
                                 Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT  loai_tien,sp_util_pkg.fnc_lay_ndung_ky(a.id,'066','Y') ndung_ky_066, a.id, a.nguoi_tao, a.ngay_tao, a.nguoi_ks, a.ngay_ks," +
                    " to_char(a.ngay_qtoan,'DD-MM-YYYY') ngay_qtoan, a.loai_qtoan, a.qtoan_thu, a.qtoan_chi,kq_dxn_thop," +
                    " a.ndung_qtoan, a.kq_dc_ttsp, a.kq_dc_pht, a.msg_id," +
                    " a.trang_thai, a.error_code, a.error_desc " +
                    " FROM  sp_066 a WHERE 1 = 1 " + strWhere +"order by a.id desc ";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNKQDCDataVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNKQDCDataVO + ".getData066(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public DNQTVO getData066SendBank(String strWhere,
                                     Vector vParam) throws Exception {
        DNQTVO data066 = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT  a.id, b.ma_nsd nguoi_tao, TO_CHAR(a.ngay_tao,'dd-mm-yyyy hh24:mi:ss') ngay_tao, " +
                    " a.nguoi_ks, a.ngay_ks, a.nhkb_chuyen, a.nhkb_nhan, " +
                    " to_char(a.ngay_qtoan,'DD-MM-YYYY') ngay_qtoan, a.loai_qtoan, a.qtoan_thu, a.qtoan_chi," +
                    " a.ndung_qtoan, a.kq_dc_ttsp, a.kq_dc_pht, a.msg_id," +
                    " a.trang_thai, a.error_code, a.error_desc, a.loai_tien " +
                    " FROM  sp_066 a, sp_nsd b " +
                    " WHERE a.nguoi_tao = b.id ";

            data066 =
                    (DNQTVO)findByPK(strSQL + strWhere, vParam, strValueObjectDNQTVO,
                                     conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNKQDCDataVO + ".getData066SendBank(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
        return data066;
    }

    public Collection getXNTHData_PHT(String strbke_id,
                                      Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "SELECT	a.sodu_kbnn, a.chenh_lech, a.tien_thu_dtu_kbnn," +
                    " a.tien_thu_tcong_kbnn, a.tien_chi_tcong_kbnn, a.so_du_dau_ngay," +
                    " a.ket_chuyen_chi, a.ket_chuyen_thu FROM	sp_065 a" +
                    " WHERE	1 = 1 AND a.id IN (" + strbke_id + ")";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNKQDCDataVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNKQDCDataVO + ".getXNKQData(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }


    public Collection getDuyetXNDChieu1(String strbke_id,
                                        Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "  SELECT   a.id, a.bk_id, a.so_du_dau_ngay, c.so_du, SUM (nvl(c.sotien_thu,0)) sotien_thu,to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc, " +
                    " SUM (nvl(c.sotien_chi,0)) sotien_chi, SUM (nvl(c.sotien_tcong,0)) sotien_tcong, SUM (nvl(c.sotien_dtu,0)) sotien_dtu " +
                    " FROM   sp_065 a, sp_bk_dc1 c " +
                    "	WHERE   1 = 1 AND a.bk_id ='" + strbke_id +
                    "' AND a.bk_id = c.id " +
                    " GROUP BY   a.id, a.bk_id,a.so_du_dau_ngay, c.so_du,a.ngay_dc ";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNKQDCDataVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNKQDCDataVO + ".getXNKQData(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public int XNHuy_Duyet(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_065 set ");

        if ("huy".equals(vo.getBtton())) {
            strSQL.append(" trang_thai = ? ");
            v_param.add(new Parameter("04", true));
            strSQL.append(", ket_qua = null ");
            //            v_param.add(new Parameter(null, true));
            strSQL.append(", ngay_thuc_hien_dc= sysdate ");
        } else if ("duyet".equals(vo.getBtton())) {
            strSQL.append(" trang_thai = ? ");
            v_param.add(new Parameter("02", true));
            strSQL.append(", tthai_ttin = ? ");
            v_param.add(new Parameter("01", true));
            strSQL.append(", msg_id = ? ");
            v_param.add(new Parameter(vo.getMsg_id(), true));
            strSQL.append(", chuky_ktt = ? ");
            v_param.add(new Parameter(vo.getChuky_ktt(), true));
            strSQL.append(", ngay_thuc_hien_dc= sysdate ");
        }
        //        else if ("duyet_th".equals(vo.getBtton())) {
        //            strSQL.append(" tthai_dxn_thop = ? ");
        //            v_param.add(new Parameter("02", true));
        //          strSQL.append(", msg_th_id = ? ");
        //          v_param.add(new Parameter(vo.getMsg_th_id(), true));
        //          strSQL.append(", ngay_thuc_hien_dc= sysdate ");
        //        }

        strSQL.append(" where id = ? ");
        v_param.add(new Parameter(vo.getId(), true));
        exc = executeStatement(strSQL.toString(), v_param, conn);

        return exc;
    }

    public int DuyetDCTHop(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_065 set ngay_thuc_hien_dc= sysdate, verified_date= sysdate ");
        if (vo.getTthai_dxn_thop() != null && "" != vo.getTthai_dxn_thop()) {
            strSQL.append(", tthai_dxn_thop = ? ");
            v_param.add(new Parameter(vo.getTthai_dxn_thop(), true));
        }
      if (vo.getKet_qua_dxn_thop() != null && "" != vo.getKet_qua_dxn_thop()) {
          strSQL.append(", ket_qua_dxn_thop = ? ");
          v_param.add(new Parameter(vo.getKet_qua_dxn_thop(), true));
      }
        if (vo.getTthai_ttin_thop() != null && "" != vo.getTthai_ttin_thop()) {
            strSQL.append(", tthai_ttin_thop = ? ");
            v_param.add(new Parameter(vo.getTthai_ttin_thop(), true));
        }
        if (vo.getMsg_th_id() != null && "" != vo.getMsg_th_id()) {
            strSQL.append(", msg_th_id = ? ");
            v_param.add(new Parameter(vo.getMsg_th_id(), true));
        }
        if (vo.getManager() != null && "" != vo.getManager()) {
            strSQL.append(", manager = ? ");
            v_param.add(new Parameter(vo.getManager(), true));
        }
        if (vo.getChuky_ktt_dxn_thop() != null &&
            "" != vo.getChuky_ktt_dxn_thop()) {
            strSQL.append(", chuky_ktt_dxn_thop = ? ");
            v_param.add(new Parameter(vo.getChuky_ktt_dxn_thop(), true));
        } 

        strSQL.append(" where id = ? ");
        v_param.add(new Parameter(vo.getId(), true));
        exc = executeStatement(strSQL.toString(), v_param, conn);

        return exc;
    }

    // Duyet Xac nhan doi chieu
/**
 * @modify: thuongdt
 * @modify-date: 07/08/2017
 * @see: turning lai cau lenh sql khac phuc lap DNQT cham
 * **/
    public Collection getXNDCTHop(String strNHKB, String strMaKB,String tu_ngay,
                                  Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";
         /* strSQL +=
                  " SELECT   b.ma_nh receive_bank, b.ten, TO_CHAR (b.ngay, 'DD/MM/YYYY') ngay_dc," +
                  " a.id ttsp_id, a.tthai_dxn_thop, a.ket_qua_dxn_thop, a.tthai_ttin_thop, a.ngay_thuc_hien_dc, a.trang_thai," +
                  " (SELECT MAX (id) FROM sp_065 WHERE 1 = 1 AND receive_bank = b.ma_nh" +
                  " AND ngay_dc = b.ngay AND app_type = 'PHT' AND send_bank = '" + strNHKB +"') pht_id" +
                  " FROM   (SELECT  e.ngay_dc, e.trang_thai, e.id, e.tthai_dxn_thop, e.tthai_ttin_thop, e.receive_bank, " +
                  " e.ngay_thuc_hien_dc, e.ket_qua_dxn_thop FROM  sp_065 e" +
                  " WHERE 1 = 1  AND e.id IN (SELECT  MAX (id) FROM sp_065" +
                  " WHERE app_type = 'TTSP' AND send_bank = '" + strNHKB +
                  "' and NGAY_DC >= sysdate -30 GROUP BY  ngay_dc, receive_bank)) a,(SELECT   DISTINCT TRUNC (ngay_gd+1) ngay, d3.ma_nh ma_nh, d3.ten ten" +
                  " FROM   sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_ngan_hang d3" +
                  " WHERE     d1.ma_nh_kb = d2.ma_nh AND d1.ma_nh = d3.ma_nh" +
                  " AND d1.ma_nh_kb = '" + strNHKB +
                  "' AND TRUNC (d1.ngay_gd+1) <= TRUNC (SYSDATE) AND (d1.ngay_gd+1) >= SYSDATE-30 AND TRUNC (d1.ngay_gd+1) >= sp_util_pkg.fnc_get_ngay_trien_khai(d1.ma_nh_kb, d1.ma_nh, 'VND') " + 
                  " AND  TO_CHAR (ngay_gd+1, 'YYYYMMDD') NOT IN" +
                  " ( SELECT   ngay FROM sp_ngay_nghi) ORDER BY   ngay) b" +
                  " WHERE   a.ngay_dc(+) = b.ngay AND a.receive_bank(+) = b.ma_nh AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE)" +
                  " AND ( (" +
                  " a.tthai_dxn_thop IN ('00','01')" +
                  "   or (SELECT   COUNT (0)" +
                  " FROM   sp_066 WHERE   nhkb_nhan = a.receive_bank AND trang_thai = '01' AND kq_dc_ttsp = a.id )>0)" +

                    ")" +
                  " OR a.trang_thai IS NULL) "+
                  " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.tthai_dxn_thop <> '00')" +
                  " OR (TRUNC (a.ngay_thuc_hien_dc) = TRUNC (SYSDATE)))" +
                  " ORDER BY   b.ma_nh, ngay";
            */
         // ban goc   
          strSQL +=
                  "SELECT   b.ma_nh receive_bank, b.ten, TO_CHAR (b.ngay, 'DD/MM/YYYY') ngay_dc," +
                  " a.id ttsp_id, a.tthai_dxn_thop, a.ket_qua_dxn_thop, a.tthai_ttin_thop, a.ngay_thuc_hien_dc, a.trang_thai," +
                  " (SELECT MAX (id) FROM sp_065 WHERE 1 = 1 AND receive_bank = b.ma_nh" +
                  " AND ngay_dc = b.ngay AND app_type = 'PHT' AND send_bank = '" + strNHKB +"') pht_id" +
                  " FROM   (SELECT  e.ngay_dc, e.trang_thai, e.id, e.tthai_dxn_thop, e.tthai_ttin_thop, e.receive_bank, " +
                  " e.ngay_thuc_hien_dc, e.ket_qua_dxn_thop FROM  sp_065 e" +
                  " WHERE 1 = 1  AND e.id IN (SELECT  MAX (id) FROM sp_065" +
                  " WHERE app_type = 'TTSP' AND send_bank = '" + strNHKB +
                  "' and NGAY_DC >= sysdate -30 GROUP BY  ngay_dc, receive_bank)) a,(SELECT   DISTINCT TRUNC (ngay_gd+1) ngay, d3.ma_nh ma_nh, d3.ten ten" +
                  " FROM   sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_ngan_hang d3" +
                  " WHERE     d1.ma_nh_kb = d2.ma_nh AND d1.ma_nh = d3.ma_nh" +
                  " AND d1.ma_nh_kb = '" + strNHKB +
                  "' AND TRUNC (d1.ngay_gd+1) <= TRUNC (SYSDATE) AND (d1.ngay_gd+1) >= SYSDATE-30 AND TRUNC (d1.ngay_gd+1) >= sp_util_pkg.fnc_get_ngay_trien_khai(d1.ma_nh_kb, d1.ma_nh, 'VND') " + 
                  " AND  TO_CHAR (ngay_gd+1, 'YYYYMMDD') NOT IN" +
                  " ( SELECT   ngay FROM sp_ngay_nghi) ORDER BY   ngay) b" +
                  " WHERE   a.ngay_dc(+) = b.ngay AND a.receive_bank(+) = b.ma_nh AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE)" +
                  " AND ( ((a.tthai_dxn_thop = '00' or a.tthai_dxn_thop ='01')   or (SELECT   COUNT (0)" +
                  " FROM   sp_066 WHERE   nhkb_nhan = a.receive_bank AND trang_thai = '01' AND kq_dc_ttsp IN(  SELECT   MAX (id)" +
                  " FROM   sp_065 WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE) and ngay_dc =b.ngay " +
                  " AND app_type = 'TTSP' AND receive_bank = a.receive_bank" +
                  " AND send_bank = '" + strNHKB + "' )) >0) AND a.id IN" +
                  " ( SELECT   MAX (id) FROM   sp_065 WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE) and ngay_dc =b.ngay " +
                  " AND app_type = 'TTSP' and receive_bank=a.receive_bank AND send_bank = '" +
                  strNHKB + "' GROUP BY   ngay_dc, send_bank ))" +
                  " OR a.trang_thai IS NULL)  OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.id IN" +
                  " (  SELECT   MAX (id) FROM   sp_065 WHERE   TO_DATE (ngay_dc) = TO_DATE (SYSDATE)" +
                  " AND app_type = 'TTSP' and receive_bank=a.receive_bank AND send_bank = '" +
                  strNHKB + "'  GROUP BY   ngay_dc, send_bank))" +
                  " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.tthai_dxn_thop <> '00')" +
                  " OR (TRUNC (a.ngay_thuc_hien_dc) = TRUNC (SYSDATE)))" +
                  " ORDER BY   b.ma_nh, ngay";        
            
 
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNDCVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNDCVO + ".getXNDCTHop(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getXNDCTHop_PHT(String strNHKB, String strMaKB,
                                      Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "SELECT a.id ttsp_id, b.id pht_id, b.receive_bank," + " TO_CHAR (b.ngay_dc, 'DD/MM/YYYY') ngay_dc, a.tthai_dxn_thop, a.tthai_ttin_thop," +
                    " a.ket_qua_dxn_thop, a.ngay_thuc_hien_dc, a.trang_thai" +
                    " FROM   sp_065 a, sp_065 b" +
                    "	WHERE 		b.id = a.pht_id(+) AND TO_CHAR (b.ngay_dc, 'YYYYMMDD') NOT IN (SELECT ngay FROM sp_ngay_nghi) " +
                    " AND b.id IN (SELECT	MAX (id) FROM	sp_065 " +
                    " WHERE	app_type = 'PHT' AND send_bank = '" + strNHKB +
                    "'" +
                    " GROUP BY	ngay_dc, receive_bank)  AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE)" +
                    " AND ( (a.ket_qua_dxn_thop = '1' OR a.tthai_dxn_thop = '00') AND a.id IN" +
                    " ( SELECT   MAX (id) FROM   sp_065 WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE)" +
                    " AND app_type = 'TTSP' and receive_bank=a.receive_bank AND send_bank = '" +
                    strNHKB + "' GROUP BY   ngay_dc, send_bank))" +
                    " OR a.trang_thai IS NULL)  OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.id IN" +
                    " (  SELECT   MAX (id) FROM   sp_065 WHERE   TO_DATE (ngay_dc) = TO_DATE (SYSDATE)" +
                    " AND app_type = 'TTSP' and receive_bank=a.receive_bank AND send_bank = '" +
                    strNHKB + "' GROUP BY   ngay_dc, send_bank))" +
                    " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.tthai_dxn_thop <> '00')" +
                    " OR (TRUNC (a.ngay_thuc_hien_dc) = TRUNC (SYSDATE)))" +
                    " ORDER BY   b.ngay_dc";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNDCVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNDCVO + ".getXNDCTHop(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }


    public int insertSdu(KQDChieu1VO vo) throws Exception {
        int nExc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        strSQL.append(" insert into sp_so_du (id ");
        strSQL2.append(" ) values (sp_so_du_seq.NEXTVAL ");

        strSQL.append(", ma_kb");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getSend_bank().toString(), true));

        strSQL.append(",  ma_nh");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getReceive_bank(), true));

        strSQL.append(", ngay_gd");
        strSQL2.append(", ?");
        v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_dc(),
                                                              "dd/MM/yyyy"),
                                      true));

        strSQL.append(",  so_du");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getSo_du(), true));

        strSQL.append(",  insert_date");
        strSQL2.append(", SYSDATE ");


        strSQL.append(strSQL2);
        strSQL.append(")");

        nExc = executeStatement(strSQL.toString(), v_param, conn);

        return nExc;
    }

  public int insertTcong(XNKQDCDataVO vo) throws Exception {
      int nExc = 0;
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      StringBuffer strSQL2 = new StringBuffer();
      strSQL.append(" insert into sp_gd_thu_cong (id ");
      strSQL2.append(" ) values (sp_gd_thu_cong_seq.NEXTVAL ");

      strSQL.append(", ma_kb");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getMa_kb().toString(), true));

      strSQL.append(",  ma_nh");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getMa_nh(), true));

      strSQL.append(", ngay_gd");
      strSQL2.append(", ?");
      v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_dc(),
                                                            "dd/MM/yyyy"),
                                    true));

      strSQL.append(",  so_thu");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getSo_thu(), true));
      
      strSQL.append(",  so_chi");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getSo_chi(), true));
      
    

      strSQL.append(",  insert_date");
      strSQL2.append(", SYSDATE ");

    strSQL.append(",  nsd_id");
    strSQL2.append(", ?");
    v_param.add(new Parameter(vo.getNsd_id(), true));
    
    strSQL.append(",  ghi_chu");
    strSQL2.append(", ?");
    v_param.add(new Parameter(vo.getGhi_chu(), true));

      strSQL.append(strSQL2);
      strSQL.append(")");

      nExc = executeStatement(strSQL.toString(), v_param, conn);

      return nExc;
  }


    public int updateTCong(XNKQDCDataVO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_gd_thu_cong set");
                                          
        if(!"".equals(vo.getMa_kb()) && vo.getMa_kb()!=null){
        strSQL.append(" ma_kb = ?, ");
        v_param.add(new Parameter(vo.getMa_kb(), true));
         }
        if(!"".equals(vo.getMa_nh()) && vo.getMa_nh()!=null){
        strSQL.append(" ma_nh = ?, ");
        v_param.add(new Parameter(vo.getMa_nh(), true));
         }
        if(!"".equals(vo.getNsd_id()) && vo.getNsd_id()!=null){
        strSQL.append(" nsd_id = ?, ");
        v_param.add(new Parameter(vo.getNsd_id(), true));
         }
      if(!"".equals(vo.getSo_chi()) && vo.getSo_chi()!=null){
      strSQL.append(" so_chi = ?, ");
      v_param.add(new Parameter(vo.getSo_chi(), true));
       }
      if(!"".equals(vo.getSo_thu()) && vo.getSo_thu()!=null){
      strSQL.append(" so_thu = ?, ");
      v_param.add(new Parameter(vo.getSo_thu(), true));
       }
        strSQL.append(" insert_date = sysdate ");

//          int a=strSQL.lastIndexOf(",");
//          String testSQL=strSQL.substring(0, a);
//      StringBuffer strSQL1 = new StringBuffer();
//        strSQL1.append(testSQL);      
        strSQL.append(" where id = ? ");
        if(!"".equals(vo.getId_tcong())&&vo.getId_tcong()!=null){
          v_param.add(new Parameter(vo.getId_tcong(), true));
        }
        exc = executeStatement(strSQL.toString(), v_param, conn);

        return exc;
    }

    public int updateTThaiTHop(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_065 set  tthai_dxn_thop = ?");
        v_param.add(new Parameter(vo.getTthai_dxn_thop(), true));

        if (vo.getCreator() != null && !"".equals(vo.getCreator())) {
            strSQL.append(", creator = ?");
            v_param.add(new Parameter(vo.getCreator(), true));
        }
      if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
          strSQL.append(", trang_thai = ?");
          v_param.add(new Parameter(vo.getTrang_thai(), true));
      }
        if (vo.getNguoi_tao_xn_qtoan() != null &&
            !"".equals(vo.getNguoi_tao_xn_qtoan())) {
            strSQL.append(", nguoi_tao_xn_qtoan = ?");
            v_param.add(new Parameter(vo.getNguoi_tao_xn_qtoan(), true));
        }
        if (vo.getNgay_tao_xn_qtoan() != null &&
            !"".equals(vo.getNgay_tao_xn_qtoan())) {
            strSQL.append(", ngay_tao_xn_qtoan = ?");
            v_param.add(new Parameter(vo.getNgay_tao_xn_qtoan(), true));
        }

        if (vo.getKet_qua_dxn_thop() != null &&
            !"".equals(vo.getKet_qua_dxn_thop())) {
            strSQL.append(", ket_qua_dxn_thop = ?");
            v_param.add(new Parameter(vo.getKet_qua_dxn_thop(), true));
        }
        if (vo.getPht_id() != null && !"".equals(vo.getPht_id())) {
            strSQL.append(", pht_id = ?");
            v_param.add(new Parameter(vo.getPht_id(), true));
        }
        if (vo.getLy_do_xn() != null && !"".equals(vo.getLy_do_xn())) {
            strSQL.append(", ly_do_xn = ?");
            v_param.add(new Parameter(vo.getLy_do_xn(), true));
        }
        strSQL.append(", ngay_thuc_hien_dc = sysdate");
        strSQL.append(" where id ='" + vo.getTtsp_id() + "'");
        exc = executeStatement(strSQL.toString(), v_param, conn);

        return exc;
    }

    public int insert(KQDChieu1VO vo) throws Exception {
        int nExc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        strSQL.append(" Insert into sp_065 (id ");
        strSQL2.append(" ) values (?");
        v_param.add(new Parameter(vo.getId().toString(), true));

        strSQL.append(", bk_id");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getBk_id().toString(), true));

        strSQL.append(", ket_qua");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getKet_qua().toString(), true));

        strSQL.append(", lan_dc");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getLan_dc().toString(), true));

        strSQL.append(", ngay_dc");
        strSQL2.append(", ?");
        v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_dc(),
                                                              "dd/MM/yyyy"),
                                      true));

        strSQL.append(", send_bank");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getSend_bank().toString(), true));

        strSQL.append(", receive_bank");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getReceive_bank().toString(), true));

        strSQL.append(", created_date");
        strSQL2.append(", sysdate");
        //v_param.add(new Parameter("sysdate",true));

        strSQL.append(", creator");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getCreator(), true));

        strSQL.append(", manager");
        strSQL2.append(", ?");
        v_param.add(new Parameter(null, true));

        strSQL.append(", verified_date");
        strSQL2.append(", ?");
        v_param.add(new Parameter(null, true));

        strSQL.append(", sodu_kbnn");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getSodu_kbnn(), true));

        strSQL.append(", chenh_lech");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getChenh_lech(), true));

        strSQL.append(", insert_date");
        strSQL2.append(", sysdate");
        //v_param.add(new Parameter("sysdate",true));

        strSQL.append(", trang_thai");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getTrang_thai_kqdc().toString(), true));

        strSQL.append(", ket_qua_pht");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getKet_qua_pht().toString(), true));

        strSQL.append(", tong_ps_pht");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getTong_ps_pht(), true));

        strSQL.append(", tong_mon_pht");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getTong_mon_pht(), true));

        strSQL.append(", app_type");
        strSQL2.append(", ?");
        v_param.add(new Parameter("TH", true));

        if ("".equals(vo.getLy_do_xn()) || vo.getLy_do_xn() == null) {
            strSQL.append(", ly_do_xn");
            strSQL2.append(", ?");
            v_param.add(new Parameter(null, true));
        } else {
            strSQL.append(", ly_do_xn");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getLy_do_xn().toString(), true));
        }


        strSQL.append(", msg_id");
        strSQL2.append(", ?");
        v_param.add(new Parameter(null, true));

        strSQL.append(", mon_thu_tcong_kbnn");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getMon_thu_tcong_kbnn(), true));

        strSQL.append(", mon_chi_tcong_kbnn");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getMon_chi_tcong_kbnn(), true));

        strSQL.append(", tien_thu_tcong_kbnn");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getTien_thu_tcong_kbnn(), true));

        strSQL.append(", tien_chi_tcong_kbnn");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getTien_chi_tcong_kbnn(), true));

        strSQL.append(", tthai_dxn_thop");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getTthai_dxn_thop().toString(), true));

        strSQL.append(", chenh_lech_thu_tcong");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getChenh_lech_thu_tcong(), true));

        strSQL.append(", chenh_lech_chi_tcong");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getChenh_lech_chi_tcong(), true));

        strSQL.append(strSQL2);
        strSQL.append(")");

        nExc = executeStatement(strSQL.toString(), v_param, conn);

        return nExc;
    }
	//Them truong tra cuu va dieu kien DC3 20161212
    public Collection getTCuuDChieu_ptrang(String strWhere, String strDC3,
                                           Vector vParam, Integer page,
                                           Integer count,
                                           Integer[] totalCount) throws Exception {
        //HungBM-20161020-Sua ham getTCuuDChieu_ptrang de lay them trang thai dc tu dong, ma ngoai te
		try {
            String strSQL =
                " SELECT   bk_id, kq_id, lan_dc, ma_nh, send_bank, receive_bank, ma, nhkb_tinh," +
                "			  nhkb_huyen, ten, tthai_dxn_thop, ngay_dc, ngay_thien_dc," +
                "			  trang_thai_bk, ket_qua, trang_thai_kq, loai_dc, ket_qua_dxn_thop," +
                "			  ngay_order, tt_dc_tu_dong, ma_nt" +
                "	 FROM   (SELECT	DISTINCT a.id bk_id, b.id kq_id, a.lan_dc, c.ma_nh," +
                "										a.send_bank, a.receive_bank, c.ma," +
                "										c.ten_kb_tinh nhkb_tinh," +
                "										c.ten_kb_huyen nhkb_huyen, c.ten_ngan_hang ten," +
                "										b.tthai_dxn_thop," +
                "										TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc," +
                "										TO_CHAR (DECODE (" +
                "														b.ngay_thuc_hien_dc," +
                "														NULL, a.ngay_thuc_hien," +
                "														b.ngay_thuc_hien_dc" +
                "													), 'DD/MM/YYYY')" + "											ngay_thien_dc," +
                "										DECODE (a.trang_thai, NULL, '97', a.trang_thai)" +
                "											trang_thai_bk, b.ket_qua," +
                "										b.trang_thai trang_thai_kq, decode(a.loai_dc,null,'1',a.loai_dc) loai_dc," +
                "										b.ket_qua_dxn_thop,c.ngay_order," +
                "                   b.TT_DC_TU_DONG tt_dc_tu_dong, a.Loai_tien ma_nt	"+
                "				  FROM	sp_bk_dc1 a, sp_065 b," +
                "							(SELECT	 DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
                "													 d5.id id_kb_tinh, d4.id id_kb_huyen," +
                "													 d4.ma,d5.id_cha," +
                "													 d1.ma_nh ma_nh," +
                "													 d3.ma_nh ma_kb," +
                "													 d2.ten ten_ngan_hang," +
                "													 d4.ten ten_kb_huyen, d5.ten" +
                "														 ten_kb_tinh" +
                "								FROM	 sp_tso_cutoftime d1," +
                "										 sp_dm_ngan_hang d2," +
                "										 sp_dm_manh_shkb d3," +
                "										 sp_dm_htkb d4," + "										 sp_dm_htkb d5" +
                "							  WHERE		  d1.ma_nh_kb = d3.ma_nh" +
                "										 AND d3.shkb = d4.ma" +
                "										 AND d5.id = d4.id_cha" +
                "										 AND d1.ma_nh = d2.ma_nh" +
                "										 AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
                "										 AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
                "													 (SELECT   ngay FROM sp_ngay_nghi)) c" +
                "				 WHERE		 a.id = b.bk_id(+)" +
                "							AND a.ngay_dc(+) = c.ngay" +
                "							AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb " +
                strWhere;
            //                "	 UNION ALL" +
            //                " SELECT	DISTINCT a.bk_id, b.id kq_id, a.lan_dc, c.ma_nh," +
            //                "										a.send_bank, a.receive_bank, c.ma," +
            //                "										c.ten_kb_tinh nhkb_tinh," +
            //                "										c.ten_kb_huyen nhkb_huyen, c.ten_ngan_hang ten," +
            //                "										NULL AS tthai_dxn_thop," +
            //                "										TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc," +
            //                "										TO_CHAR (DECODE (" +
            //                "														b.ngay_thien_dc," +
            //                "														NULL, a.ngay_thien_dc," +
            //                "														b.ngay_thien_dc" +
            //                "													), 'DD/MM/YYYY')" + "											ngay_thien_dc," +
            //                "										DECODE (a.trang_thai, NULL, '98', a.trang_thai)" +
            //                "											trang_thai_bk, b.ket_qua," +
            //                "										b.trang_thai trang_thai_kq, decode(a.loai_dc,null,'2',a.loai_dc) loai_dc," +
            //                "										NULL AS ket_qua_dxn_thop,c.ngay_order" +
            //                "				  FROM	sp_bk_Dc2 a, sp_kq_dc2 b," +
            //                "							(SELECT	 DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
            //                "													 d5.id id_kb_tinh, d4.id id_kb_huyen," +
            //                "													 d1.ma_nh ma_nh," +
            //                "													 d4.ma,d5.id_cha," +
            //                "													 d3.ma_nh ma_kb," +
            //                "													 d2.ten ten_ngan_hang," +
            //                "													 d4.ten ten_kb_huyen," + "													 d5.ten" +
            //                "														 ten_kb_tinh" +
            //                "								FROM	 sp_tso_cutoftime d1," +
            //                "										 sp_dm_ngan_hang d2," +
            //                "										 sp_dm_manh_shkb d3," +
            //                "										 sp_dm_htkb d4," + "										 sp_dm_htkb d5" +
            //                "							  WHERE		  d1.ma_nh_kb = d3.ma_nh" +
            //                "										 AND d3.shkb = d4.ma" +
            //                "										 AND d5.id = d4.id_cha" +
            //                "										 AND d1.ma_nh = d2.ma_nh " +
            //                "										 AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
            //                "										 AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
            //                "													 (SELECT   ngay FROM sp_ngay_nghi)) c" +
            //                "				 WHERE		 a.bk_id = b.bk_id(+)" +
            //                "							AND a.ngay_dc(+) = c.ngay" +
            //                "							AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb" +
            //                strWhere;

            if (strDC3 != null && !"".equals(strDC3)) {
                strSQL += strDC3;
            }
            strSQL +=
                    ") ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC, kq_id desc, lan_dc DESC, send_bank ASC";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           strValueObjectKQVO, page, count,
                                           totalCount);
        } catch (Exception e) {
            throw e;
        }

    }

  //HungBM-20161212-Them ham de nang cap chuc nang tra cuu trang thai doi chieu cua don vi duoc goi tu TCuuTTinDChieuAction.java view-begin  
  //Them tieu chi tiem kiem theo loai quyet toan  
  //Lay danh sach kqdc phan trang	
  public Collection getTCuuDChieu_nangcap_ptrang(String strWhere, String strDC3,
                                         Vector vParam, Integer page,
                                         Integer count,
                                         Integer[] totalCount,String trang_thai_tk) throws Exception {
      try {
          String strSQL =
        //20170320 - Pham vi doi chieu quyet dinh boi lan doi chieu: 1-DonVi, 3-ToanQuoc 
              " SELECT   trang_thai_tk,bk_id, kq_id, substr(lan_dc,1,1) pham_vi_doi_chieu, lan_dc, ma_nh, send_bank, receive_bank, ma, nhkb_tinh," +
              "       nhkb_huyen, ten, tthai_dxn_thop, ngay_dc, ngay_thien_dc," +
              "       trang_thai_bk, ket_qua, trang_thai_kq, loai_dc, ket_qua_dxn_thop," +
              "       ngay_order, tt_dc_tu_dong, ma_nt" +
              "  FROM   (SELECT DISTINCT a.id bk_id, b.id kq_id, a.lan_dc, c.ma_nh," +
              "                   a.send_bank, a.receive_bank, c.ma," +
              "                   c.ten_kb_tinh nhkb_tinh," +
              "                   c.ten_kb_huyen nhkb_huyen, c.ten_ngan_hang ten," +
              "                   b.tthai_dxn_thop," +
              "                   TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc," +
              "                   TO_CHAR (DECODE (" +
              "                           b.ngay_thuc_hien_dc," +
              "                           NULL, a.ngay_thuc_hien," +
              "                           b.ngay_thuc_hien_dc" +
              "                         ), 'DD/MM/YYYY')" + "                     ngay_thien_dc," +
              "                   DECODE (a.trang_thai, NULL, '97', a.trang_thai)" +
              "                     trang_thai_bk, b.ket_qua," +
              "                   b.trang_thai trang_thai_kq, decode(a.loai_dc,null,'1',a.loai_dc) loai_dc," +
              "                   b.ket_qua_dxn_thop,c.ngay_order," +
              "                   b.TT_DC_TU_DONG tt_dc_tu_dong, a.Loai_tien ma_nt, c.trang_thai_tk  "+
              "         FROM  sp_bk_dc1 a, sp_065 b," +
              "             (SELECT  DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
              "                          d5.id id_kb_tinh, d4.id id_kb_huyen," +
              "                          d4.ma,d5.id_cha," +
              "                          d1.ma_nh ma_nh," +
              "                          d3.ma_nh ma_kb," +
              "                          d2.ten ten_ngan_hang," +
              "                          d4.ten ten_kb_huyen, d5.ten ten_kb_tinh, d6.trang_thai trang_thai_tk " +
              "                            " +
              "               FROM   sp_tso_cutoftime d1," +
              "                    sp_dm_ngan_hang d2," +
              "                    sp_dm_manh_shkb d3," +
              "                    sp_dm_htkb d4, sp_dm_htkb d5, SP_TKNH_KB d6" +
              "               WHERE     d1.ma_nh_kb = d3.ma_nh" +
              "                    AND d3.shkb = d4.ma" +
              "                    AND d5.id = d4.id_cha" +
              "                    AND d1.ma_nh = d2.ma_nh" +
              "                    AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
              "                    AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
              "                          (SELECT   ngay FROM sp_ngay_nghi) AND d6.nh_id = d2.id AND d6.kb_id = d4.id";
              if(trang_thai_tk != null && !"".equals(trang_thai_tk)){
               strSQL += " AND d6.trang_thai = '"+trang_thai_tk+"'";
              }
              strSQL += ") c" +
              "        WHERE     a.id = b.bk_id(+)" +
              "             AND a.ngay_dc(+) = c.ngay" +
              "             AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb " +
              strWhere;       

          if (strDC3 != null && !"".equals(strDC3)) {
              strSQL += strDC3;
          }
          strSQL +=
                  ") ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC, kq_id desc, lan_dc DESC, send_bank ASC";
          return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                         strValueObjectKQVO, page, count,
                                         totalCount);
      } catch (Exception e) {
          throw e;
      }

  } 
  
  //Lay danh sach kqdc lan 1 phan trang
   //Them tieu chi tiem kiem theo loai quyet toan 
   //HungBM-20170626 - BEGIN
  public Collection getTCuu1DChieu_nangcap_ptrang(String strWhere, Vector vParam,
                                          Integer page, Integer count,
                                          Integer[] totalCount,String trang_thai_tk) throws Exception {
    try {
        String strSQL =
            //20170320 - Pham vi doi chieu quyet dinh boi lan doi chieu: 1-DonVi, 3-ToanQuoc 
            "SELECT DISTINCT a.loai_tien ma_nt,c.trang_thai_tk, a.id bk_id, b.id kq_id, substr(a.lan_dc,1,1) pham_vi_doi_chieu, a.lan_dc, c.ma_nh," +
            "                   a.send_bank, a.receive_bank, c.ma," +
            "                   c.ten_kb_tinh nhkb_tinh," +
            "                   c.ten_kb_huyen nhkb_huyen, c.ten_ngan_hang ten," +
            "                   b.tthai_dxn_thop," +
            "                   TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc," +
            "                   TO_CHAR (DECODE (" +
            "                           b.ngay_thuc_hien_dc," +
            "                           NULL, a.ngay_thuc_hien," +
            "                           b.ngay_thuc_hien_dc" +
            "                         ), 'DD/MM/YYYY')" + "                     ngay_thien_dc," +
            "                   DECODE (a.trang_thai, NULL, '97', a.trang_thai)" +
            "                     trang_thai_bk, b.ket_qua," +
            "                   b.trang_thai trang_thai_kq, decode(a.loai_dc,null,'3',a.loai_dc) loai_dc," +
            "                   b.ket_qua_dxn_thop,c.ngay_order, b.tt_dc_tu_dong" +
            "         FROM  sp_bk_dc1 a, sp_065 b," +
            "             (SELECT  DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
            "                          d5.id id_kb_tinh," +
            "                          d4.id id_kb_huyen," + "                           d4.ma," +
            "                          d5.id_cha," + "                           d1.ma_nh ma_nh," +
            "                          d3.ma_nh ma_kb," +
            "                          d2.ten ten_ngan_hang," +
            "                          d4.ten ten_kb_huyen," + "                           d5.ten" +
            "                            ten_kb_tinh , d6.trang_thai trang_thai_tk " +
            "               FROM   sp_tso_cutoftime d1," +
            "                    sp_dm_ngan_hang d2," +
            "                    sp_dm_manh_shkb d3," +
            "                    sp_dm_htkb d4, sp_dm_htkb d5, SP_TKNH_KB d6" +
            "               WHERE     d1.ma_nh_kb = d3.ma_nh" +
            "                    AND d3.shkb = d4.ma" +
            "                    AND d5.id = d4.id_cha" +
            "                    AND d1.ma_nh = d2.ma_nh " +
            "                    AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
            "                    AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
            "                          (SELECT   ngay FROM sp_ngay_nghi) AND d6.nh_id = d2.id AND d6.kb_id = d4.id AND d6.loai_tk <>'01' ";
            
              if(trang_thai_tk != null && !"".equals(trang_thai_tk)){
               strSQL += " AND d6.trang_thai = '"+trang_thai_tk+"'";
              }
              strSQL += ") c" +
            "        WHERE     a.id = b.bk_id(+)" +
            "             AND a.ngay_dc(+) = c.ngay" +
            "             AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb ";

        if (strWhere != null && !"".equals(strWhere)) {
            strSQL += strWhere;
        }
        strSQL +=
                " ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC,kq_id desc, lan_dc DESC, send_bank ASC";
        return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                       strValueObjectKQVO, page, count,
                                       totalCount);
    } catch (Exception e) {
        throw e;
    }

    }
	//HungBM-20170626 - END
    public Collection getTCuu1DChieu_ptrang(String strWhere, Vector vParam,
                                            Integer page, Integer count,
                                            Integer[] totalCount) throws Exception {
        try {
            String strSQL =
                "SELECT	DISTINCT a.id bk_id, b.id kq_id, a.lan_dc, c.ma_nh," +
                "										a.send_bank, a.receive_bank, c.ma," +
                "										c.ten_kb_tinh nhkb_tinh," +
                "										c.ten_kb_huyen nhkb_huyen, c.ten_ngan_hang ten," +
                "										b.tthai_dxn_thop," +
                "										TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc," +
                "										TO_CHAR (DECODE (" +
                "														b.ngay_thuc_hien_dc," +
                "														NULL, a.ngay_thuc_hien," +
                "														b.ngay_thuc_hien_dc" +
                "													), 'DD/MM/YYYY')" + "											ngay_thien_dc," +
                "										DECODE (a.trang_thai, NULL, '97', a.trang_thai)" +
                "											trang_thai_bk, b.ket_qua," +
                "										b.trang_thai trang_thai_kq, decode(a.loai_dc,null,'3',a.loai_dc) loai_dc," +
                "										b.ket_qua_dxn_thop,c.ngay_order" +
                "				  FROM	sp_bk_dc1 a, sp_065 b," +
                "							(SELECT	 DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
                "													 d5.id id_kb_tinh," +
                "													 d4.id id_kb_huyen," + "													 d4.ma," +
                "													 d5.id_cha," + "													 d1.ma_nh ma_nh," +
                "													 d3.ma_nh ma_kb," +
                "													 d2.ten ten_ngan_hang," +
                "													 d4.ten ten_kb_huyen," + "													 d5.ten" +
                "														 ten_kb_tinh" +
                "								FROM	 sp_tso_cutoftime d1," +
                "										 sp_dm_ngan_hang d2," +
                "										 sp_dm_manh_shkb d3," +
                "										 sp_dm_htkb d4," + "										 sp_dm_htkb d5" +
                "							  WHERE		  d1.ma_nh_kb = d3.ma_nh" +
                "										 AND d3.shkb = d4.ma" +
                "										 AND d5.id = d4.id_cha" +
                "										 AND d1.ma_nh = d2.ma_nh " +
                "										 AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
                "										 AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
                "													 (SELECT   ngay FROM sp_ngay_nghi)) c" +
                "				 WHERE		 a.id = b.bk_id(+)" +
                "							AND a.ngay_dc(+) = c.ngay" +
                "							AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb ";

            if (strWhere != null && !"".equals(strWhere)) {
                strSQL += strWhere;
            }
            strSQL +=
                    " ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC,kq_id desc, lan_dc DESC, send_bank ASC";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           strValueObjectKQVO, page, count,
                                           totalCount);
        } catch (Exception e) {
            throw e;
        }

    }
  //Lay danh sach kqdc lan 2 phan trang
   //Them tieu chi tiem kiem theo loai quyet toan 
  public Collection getTCuu2DChieu_nangcap_ptrang(String strWhere, Vector vParam,
                                          Integer page, Integer count,
                                          Integer[] totalCount,String trang_thai_tk) throws Exception {
      try {
          String strSQL =
        //20170320 - Pham vi doi chieu quyet dinh boi lan doi chieu: 1-DonVi, 3-ToanQuoc 
              "SELECT DISTINCT a.loai_tien ma_nt, a.id bk_id, b.id kq_id, substr(a.lan_dc,1,1) pham_vi_doi_chieu, a.lan_dc, c.ma_nh," +
              "                   a.send_bank, a.receive_bank, c.ma," +
              "                   c.ten_kb_tinh nhkb_tinh," +
              "                   c.ten_kb_huyen nhkb_huyen, c.ten_ngan_hang ten," +
              "                   b.tthai_dxn_thop," +
              "                   TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc," +
              "                   TO_CHAR (DECODE (" +
              "                           b.ngay_thuc_hien_dc," +
              "                           NULL, a.ngay_thuc_hien," +
              "                           b.ngay_thuc_hien_dc" +
              "                         ), 'DD/MM/YYYY')" + "                     ngay_thien_dc," +
              "                   DECODE (a.trang_thai, NULL, '97', a.trang_thai)" +
              "                     trang_thai_bk, b.ket_qua," +
              "                   b.trang_thai trang_thai_kq, decode(a.loai_dc,null,'3',a.loai_dc) loai_dc," +
              "                   b.ket_qua_dxn_thop,c.ngay_order, c.trang_thai_tk " +
              "         FROM  sp_bk_dc1 a, sp_065 b," +
              "             (SELECT  DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
              "                          d5.id id_kb_tinh," +
              "                          d4.id id_kb_huyen," + "                           d4.ma," +
              "                          d5.id_cha," + "                           d1.ma_nh ma_nh," +
              "                          d3.ma_nh ma_kb," +
              "                          d2.ten ten_ngan_hang," +
              "                          d4.ten ten_kb_huyen," + "                           d5.ten" +
              "                            ten_kb_tinh , d6.trang_thai trang_thai_tk" +
              "               FROM   sp_tso_cutoftime d1," +
              "                    sp_dm_ngan_hang d2," +
              "                    sp_dm_manh_shkb d3," +
              "                    sp_dm_htkb d4,sp_dm_htkb d5 , SP_TKNH_KB d6" +
              "               WHERE     d1.ma_nh_kb = d3.ma_nh" +
              "                    AND d3.shkb = d4.ma" +
              "                    AND d5.id = d4.id_cha" +
              "                    AND d1.ma_nh = d2.ma_nh " +
              "                    AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
              "                    AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
              "                          (SELECT   ngay FROM sp_ngay_nghi) AND d6.nh_id = d2.id AND d6.kb_id = d4.id" ;
              if(trang_thai_tk != null && !"".equals(trang_thai_tk)){
               strSQL += " AND d6.trang_thai = '"+trang_thai_tk+"'";
              }
              strSQL += ") c" +
              "        WHERE     a.id = b.bk_id(+)" +
              "             AND a.ngay_dc(+) = c.ngay" +
              "             AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb ";

          if (strWhere != null && !"".equals(strWhere)) {
              strSQL += strWhere;
          }
          strSQL +=
                  " ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC,kq_id desc, lan_dc DESC, send_bank ASC";
          return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                         strValueObjectKQVO, page, count,
                                         totalCount);
      } catch (Exception e) {
          throw e;
      }

  }

    public Collection getTCuu2DChieu_ptrang(String strWhere, Vector vParam,
                                            Integer page, Integer count,
                                            Integer[] totalCount) throws Exception {
        try {
            String strSQL =
                "SELECT	DISTINCT a.bk_id, b.id kq_id, a.lan_dc, c.ma_nh," +
                "										a.send_bank, a.receive_bank, c.ma," +
                "										c.ten_kb_tinh nhkb_tinh," +
                "										c.ten_kb_huyen nhkb_huyen, c.ten_ngan_hang ten," +
                "										NULL AS tthai_dxn_thop," +
                "										TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc," +
                "										TO_CHAR (DECODE (" +
                "														b.ngay_thien_dc," +
                "														NULL, a.ngay_thien_dc," +
                "														b.ngay_thien_dc" +
                "													), 'DD/MM/YYYY')" + "											ngay_thien_dc," +
                "										DECODE (a.trang_thai, NULL, '98', a.trang_thai)" +
                "											trang_thai_bk, b.ket_qua," +
                "										b.trang_thai trang_thai_kq, decode(a.loai_dc,null,'2',a.loai_dc) loai_dc," +
                "										NULL AS ket_qua_dxn_thop,c.ngay_order" +
                "				  FROM	sp_bk_Dc2 a, sp_kq_dc2 b," +
                "							(SELECT	 DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
                "													 d5.id id_kb_tinh," +
                "													 d4.id id_kb_huyen," +
                "													 d1.ma_nh ma_nh," + "													 d4.ma," +
                "													 d5.id_cha," + "													 d3.ma_nh ma_kb," +
                "													 d2.ten ten_ngan_hang," +
                "													 d4.ten ten_kb_huyen," + "													 d5.ten" +
                "														 ten_kb_tinh" +
                "								FROM	 sp_tso_cutoftime d1," +
                "										 sp_dm_ngan_hang d2," +
                "										 sp_dm_manh_shkb d3," +
                "										 sp_dm_htkb d4," + "										 sp_dm_htkb d5" +
                "							  WHERE		  d1.ma_nh_kb = d3.ma_nh" +
                "										 AND d3.shkb = d4.ma" +
                "										 AND d5.id = d4.id_cha" +
                "										 AND d1.ma_nh = d2.ma_nh " +
                "										 AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
                "										 AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
                "													 (SELECT   ngay FROM sp_ngay_nghi)) c" +
                "				 WHERE		 a.bk_id = b.bk_id(+)" +
                "							AND a.ngay_dc(+) = c.ngay" +
                "							AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb ";

            if (strWhere != null && !"".equals(strWhere)) {
                strSQL += strWhere;
            }
            strSQL +=
                    " ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC,trang_thai_bk, lan_dc DESC, send_bank ASC";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           strValueObjectKQVO, page, count,
                                           totalCount);
        } catch (Exception e) {
            throw e;
        }

    }


  
  //Lay danh sach kqdc lan 3 phan trang
   //Them tieu chi tiem kiem theo loai quyet toan 
  public Collection getTCuu3DChieu_nangcap_ptrang(String strWhere, Vector vParam,
                                          Integer page, Integer count,
                                          Integer[] totalCount,String trang_thai_tk) throws Exception {
      try {
//          String strSQL =
        //20170320 - Pham vi doi chieu quyet dinh boi lan doi chieu: 1-DonVi, 3-ToanQuoc 
//              "SELECT DISTINCT a.id bk_id, b.id kq_id, substr(a.lan_dc,1,1) pham_vi_doi_chieu, a.lan_dc, c.ma_nh, a.send_bank," +
//              "           a.receive_bank, c.ma, c.ten_kb_tinh nhkb_tinh," +
//              "           c.ten_kb_huyen nhkb_huyen, c.ten_ngan_hang ten," +
//              "           NULL AS tthai_dxn_thop," +
//              "           TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc," +
//              "           TO_CHAR (DECODE (" + "                    b.ngay_thien_dc," +
//              "                   NULL, a.ngay_thien_dc," +
//              "                   b.ngay_thien_dc" + "                  ), 'DD/MM/YYYY')" +
//              "             ngay_thien_dc," +
//              "           DECODE (a.trang_thai, NULL, '99', a.trang_thai)" +
//              "             trang_thai_bk, b.ket_qua, b.trang_thai trang_thai_kq," +
//              "           a.loai_dc, NULL AS ket_qua_dxn_thop,c.ngay_order,c.trang_thai_tk, b.tt_dc_tu_dong" +
//              "  FROM sp_bk_dc3 a, sp_kq_dc3 b," +
//              "     (SELECT  DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
//              "                  d5.id id_kb_tinh, d4.ma, " +
//              "                  d4.id id_kb_huyen,d5.id_cha," +
//              "                  d1.ma_nh ma_nh," + "                  d3.ma_nh ma_kb," +
//              "                  d2.ten ten_ngan_hang," +
//              "                  d4.ten ten_kb_huyen," +
//              "                  d5.ten ten_kb_tinh, d6.trang_thai trang_thai_tk " +
//              "       FROM   sp_tso_cutoftime d1," +
//              "            sp_dm_ngan_hang d2," + "            sp_dm_manh_shkb d3," +
//              "            sp_dm_htkb d4, sp_dm_htkb d5, SP_TKNH_KB d6 " +
//              "       WHERE     d1.ma_nh_kb = d3.ma_nh" +
//              "            AND d3.shkb = d4.ma" + "            AND d5.id = d4.id_cha" +
//              "            AND d1.ma_nh = d2.ma_nh" +
//              "            AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
//              "            AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
//              "                  (SELECT   ngay FROM sp_ngay_nghi)  AND d6.nh_id = d2.id AND d6.kb_id = d4.id";
//            if(trang_thai_tk != null && !"".equals(trang_thai_tk)){
//             strSQL += " AND d6.trang_thai = '"+trang_thai_tk+"'";
//            }
//            strSQL += ") c" +
//              " WHERE    a.id = b.bk_id(+)" +
//              "     AND a.ngay_dc(+) = c.ngay " +
//              "     AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb  and c.ma='0002' " +//20170927 tai khoan chuyen sang cuc kt toan
 
       // turning code lap doi chieu cham
 
        String strSQL =
            "SELECT DISTINCT a.id bk_id, b.id kq_id, substr(a.lan_dc,1,1) pham_vi_doi_chieu, a.lan_dc, a.SEND_BANK ma_nh," + 
            " a.send_bank," + 
            " a.receive_bank,d.SHKB, (select ten from sp_dm_htkb where id  = c.id_cha and ROWNUM = 1) nhkb_tinh " + 
            " ,c.ten nhkb_huyen, e.ten ten,  NULL AS tthai_dxn_thop, TO_CHAR (a.NGAY_DC, 'DD/MM/YYYY') ngay_dc, " + 
            " TO_CHAR (DECODE (b.ngay_thien_dc,  NULL, a.ngay_thien_dc, b.ngay_thien_dc  ), 'DD/MM/YYYY')  ngay_thien_dc,  " + 
            " DECODE (a.trang_thai, NULL, '99', a.trang_thai) trang_thai_bk, b.ket_qua, b.trang_thai trang_thai_kq, " + 
            " a.loai_dc, NULL AS ket_qua_dxn_thop" + 
            " ,a.CREATED_DATE   ngay_order,'' trang_thai_tk" + 
            " , b.tt_dc_tu_dong  \n" + 
            " FROM sp_bk_dc3 a, sp_kq_dc3 b,sp_dm_htkb c, sp_dm_manh_shkb d,sp_dm_ngan_hang e" +
            "  WHERE    a.id = b.bk_id(+)  and ma = d.SHKB and a.RECEIVE_BANK = d.MA_NH and a.SEND_BANK= e.MA_NH " +//20170927 tai khoan chuyen sang cuc kt toan
              strWhere +
            " and a.RECEIVE_BANK in (SELECT ma_nh FROM sp_dm_manh_shkb  where shkb='0002')"+
            " ORDER BY  a.CREATED_DATE DESC, c.ten ASC,b.id desc, lan_dc DESC, send_bank ASC";

          return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                         strValueObjectKQVO, page, count,
                                         totalCount);
      } catch (Exception e) {
          throw e;
      }

  }
//HungBM-20161212-Them ham de nang cap chuc nang tra cuu trang thai doi chieu cua don vi duoc goi tu TCuuTTinDChieuAction.java view-end

    public Collection getTCuu3DChieu_ptrang(String strWhere, Vector vParam,
                                            Integer page, Integer count,
                                            Integer[] totalCount) throws Exception {
        try {
            String strSQL =
                "SELECT	DISTINCT a.id bk_id, b.id kq_id, a.lan_dc, c.ma_nh, a.send_bank," +
                "						a.receive_bank, c.ma, c.ten_kb_tinh nhkb_tinh," +
                "						c.ten_kb_huyen nhkb_huyen, c.ten_ngan_hang ten," +
                "						NULL AS tthai_dxn_thop," +
                "						TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc," +
                "						TO_CHAR (DECODE (" + "										b.ngay_thien_dc," +
                "										NULL, a.ngay_thien_dc," +
                "										b.ngay_thien_dc" + "									), 'DD/MM/YYYY')" +
                "							ngay_thien_dc," +
                "						DECODE (a.trang_thai, NULL, '99', a.trang_thai)" +
                "							trang_thai_bk, b.ket_qua, b.trang_thai trang_thai_kq," +
                "						a.loai_dc, NULL AS ket_qua_dxn_thop,c.ngay_order" +
                "  FROM	sp_bk_dc3 a, sp_kq_dc3 b," +
                "			(SELECT	 DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
                "									 d5.id id_kb_tinh, d4.ma, " +
                "                  d4.id id_kb_huyen,d5.id_cha," +
                "									 d1.ma_nh ma_nh," + "									 d3.ma_nh ma_kb," +
                "									 d2.ten ten_ngan_hang," +
                "									 d4.ten ten_kb_huyen," +
                "									 d5.ten ten_kb_tinh" +
                "				FROM	 sp_tso_cutoftime d1," +
                "						 sp_dm_ngan_hang d2," + "						 sp_dm_manh_shkb d3," +
                "						 sp_dm_htkb d4, sp_dm_htkb d5" +
                "			  WHERE		  d1.ma_nh_kb = d3.ma_nh" +
                "						 AND d3.shkb = d4.ma" + "						 AND d5.id = d4.id_cha" +
                "						 AND d1.ma_nh = d2.ma_nh" +
                "						 AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
                "						 AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
                "									 (SELECT   ngay FROM sp_ngay_nghi)) c" +
                " WHERE		 a.id = b.bk_id(+)" +
                "			AND a.ngay_dc(+) = c.ngay " +
                "			AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb  and c.ma='0003' " +
                strWhere +
                " ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC,kq_id desc, lan_dc DESC, send_bank ASC";

            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           strValueObjectKQVO, page, count,
                                           totalCount);
        } catch (Exception e) {
            throw e;
        }

    }


    public KQDChieu1VO getKQDC1(String strWhere,
                                Vector vParam) throws Exception {

        //      Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT a.id, a.bk_id, a.ket_qua, a.lan_dc, to_char(a.ngay_dc,'DD-MM-YYYY') ngay_dc, a.send_bank," +
                    " a.receive_bank, to_char(a.created_date,'DD-MM-YYYY HH24:mi:ss') created_date, a.creator, a.manager," +
                    " to_char(a.verified_date,'DD-MM-YYYY HH24:mi:ss') verified_date, a.sodu_kbnn, a.chenh_lech, a.msg_id," +
                    " to_char(a.insert_date,'DD-MM-YYYY HH24:mi:ss') insert_date, a.trang_thai, a.ket_qua_pht, a.tong_ps_pht," +
                    " a.tong_mon_pht, a.ly_do_xn, a.app_type, a.mon_thu_tcong_kbnn," +
                    " a.mon_chi_tcong_kbnn, a.tien_thu_tcong_kbnn," +
                    " a.tien_chi_tcong_kbnn, a.tthai_dxn_thop,a.ket_qua_dxn_thop, a.chenh_lech_thu_tcong," +
                    " a.chenh_lech_chi_tcong, a.mon_thu_dtu_kbnn, a.mon_chi_dtu_kbnn," +
                    " a.tien_thu_dtu_kbnn, a.tien_chi_dtu_kbnn, a.so_du_dau_ngay," +
                    " a.ket_chuyen_thu, a.ket_chuyen_chi, b.ma_nsd, a.msg_refid " +
                    "  FROM sp_065 a, sp_nsd b where a.creator = b.id " +
                    strWhere;

            KQDChieu1VO phtVO =
                (KQDChieu1VO)findByPK(strSQL.toString(), vParam,
                                      strValueObjectKQVO, conn);
            return phtVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObject + ".getKQDC1(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public DuyetKQDCVO getTSoDC1(String ttsp_id,
                                 Vector vparam) throws Exception {

        //      Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT	to_char(a.ngay_dc,'DD-MM-YYYY') ngay_dc, a.receive_bank, a.send_bank, a.ket_qua, (SELECT	 c2.ten" +
                    " FROM	 sp_dm_htkb c1, sp_dm_htkb c2, sp_dm_manh_shkb c3 " +
                    " WHERE	 c3.ma_nh = a.send_bank AND c2.ma = c1.ma_cha AND c3.shkb = c1.ma)" +
                    " ten_tinh, b.ten ten_huyen," +
                    " c.ten ten_nhang FROM	sp_065 a, sp_dm_ngan_hang b, sp_dm_ngan_hang c" +
                    " WHERE a.id = '" + ttsp_id +
                    "' AND a.send_bank = b.ma_nh" +
                    "	 AND a.receive_bank = c.ma_nh";

            DuyetKQDCVO phtVO =
                (DuyetKQDCVO)findByPK(strSQL.toString(), null, strValueObjectXNDCVO,
                                      conn);
            return phtVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNDCVO + ".getTSoDC1(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }


    public DChieu1VO checkSHKB(String ma_nhkb,
                               Vector vparam) throws Exception {

        //      Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "select decode(instr(giatri_ts,(SELECT a.shkb " + "  FROM sp_dm_manh_shkb a where ma_nh='" +
                    ma_nhkb +
                    "')),'0','CO_PHT','KO_PHT') check_shkb from sp_thamso_ht where id=101 ";

            DChieu1VO dcVO =
                (DChieu1VO)findByPK(strSQL.toString(), null, strValueObjectVO,
                                    conn);
            return dcVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNDCVO + ".checkSHKB(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public DChieu1VO getPre_Ngay_Dc(String strWhere,
                                    Vector vparam) throws Exception {

        //      Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT	 DISTINCT (a.ngay_dc) pre_ngay_dc" + " FROM	 ttsp_owner.sp_kq_Dc1 a" +
                    " WHERE 1=1 " + strWhere;

            DChieu1VO dcVO =
                (DChieu1VO)findByPK(strSQL.toString(), null, strValueObjectVO,
                                    conn);
            return dcVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNDCVO + ".getPre_Ngay_Dc(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }


    public DuyetKQDCVO getTSoDC2(String strid,
                                 Vector vparam) throws Exception {

        //      Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT	TO_CHAR (d.ngay_dc, 'DD-MM-YYYY') ngay_dc, d.send_bank," +
                    " d.receive_bank, d.lan_dc," +
                    " (SELECT	 c2.ten FROM	 sp_dm_htkb c1, sp_dm_htkb c2," +
                    " sp_dm_manh_shkb c3 WHERE		  c3.ma_nh = d.receive_bank" +
                    " AND c2.ma = c1.ma_cha AND c3.shkb = c1.ma) ten_tinh, b.ten ten_huyen, c.ten ten_nhang" +
                    "  FROM	sp_kq_dc2 a, sp_dm_ngan_hang b, sp_dm_ngan_hang c, sp_bk_dc2 d" +
                    " WHERE		 d.send_bank = b.ma_nh and a.bk_id(+) = d.bk_id " +
                    strid + " AND d.receive_bank = c.ma_nh";

            DuyetKQDCVO phtVO =
                (DuyetKQDCVO)findByPK(strSQL.toString(), null, strValueObjectXNDCVO,
                                      conn);
            return phtVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectXNDCVO + ".getTSoDC2(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }


    public Collection getKQDChieuCtiet(String strWhere,
                                       Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";
            strSQL +=
                    "SELECT a.id, a.bkq_id, a.bk_id, a.mt_id, to_char(a.send_date,'DD-MM-YYYY HH24:mi:ss') send_date, a.f20, a.f21," +
                    " a.f32as3, to_char(a.ngay_ts,'DD-MM-YYYY') ngay_ts, a.ghi_chu, a.mt_type, a.app_type," +
                    " a.sai_yeu_to, a.trang_thai, to_char(a.insert_date,'DD-MM-YYYY HH24:mi:ss') insert_date, to_char(a.ngay_ct,'DD-MM-YYYY') ngay_ct" +
                    " FROM sp_065_dtl a Where 1=1 " + strWhere;

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectKQCTVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectKQVO + ".getKQDCChiTiet(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getDMucKB_cha(String strWhere,
                                    Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT   DISTINCT a.id_cha, c.ten kb_tinh" + " FROM   sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c" +
                    "	WHERE   1 = 1 AND a.id = b.kb_id AND a.id_cha = c.id ";
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
	//HungBM-20161212-ham lay danh muc KB tinh
  public Collection getDMucKB_Tinh(String strWhere,
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
          throw daoEx;
      }
      return reval;
  }
  
  /*
  20171009 thuongdt bo sung ham lay danh muc KB tinh bo sung them SGD v� cuc KTNN
  */
  public Collection getDMucKB_Tinh2(String strWhere,
                                  Vector vParam) throws Exception {

      Collection reval = null;
      try {
          String strSQL = "";
          //20170927 thuongdt chi lay don vi cap tinh hoac so giao dich hoac cuc ke toan kbnn
          strSQL ="select id id_cha, ten kb_tinh from sp_dm_htkb  where 1=1 "+strWhere +" order by ltrim(REPLACE(ten,'KBNN',''))";
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

    public Collection getDMucKB_huyen(String strWhere,
                                      Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";
             // 20170927 thuongdt bo d.ma_nh neu de se nhan so huyen vi co the co 1 KB co nhieu TK NH
            strSQL +=
                   // " SELECT distinct  a.ma,c.ten kb_tinh, a.ten kb_huyen, a.id,a.ma_cha, a.id_cha, a.cap,d.ma_nh" +
                    " SELECT distinct  a.ma,c.ten kb_tinh, a.ten kb_huyen, a.id,a.ma_cha, a.id_cha, a.cap" +
                    " FROM   sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c,sp_dm_manh_shkb d " +
                    " WHERE   1 = 1  and a.id=b.kb_id and a.id_cha=c.id and d.shkb = a.ma ";
            strSQL += strWhere + " order by ltrim(a.ten)";

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

    public Collection getDMucKBHuyen(String strWhere,
                                     Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    " SELECT distinct  a.ma,c.ten kb_tinh, a.ten kb_huyen, a.id,a.ma_cha, a.id_cha, a.cap, d.ma_nh" +
                    " FROM   sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c, sp_dm_manh_shkb d " +
                    " WHERE   1 = 1  and a.id=b.kb_id and a.id_cha=c.id and a.ma=d.shkb ";
            strSQL += strWhere + " order by a.ma";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getDMucKBHuyen(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public DChieu1VO getCap(String strWhere, Vector vParam) throws Exception {
        DChieu1VO dmucvo = null;
        try {
            String strSQL = "select cap from sp_dm_htkb where 1=1 ";
            strSQL += strWhere;

            dmucvo =
                    (DChieu1VO)findByPK(strSQL.toString(), vParam, strValueObjectVO,
                                        conn);


        } catch (Exception e) {
            throw e;
        }
        return dmucvo;
    }


    public DChieu1VO checkPHT(String strWhere,
                              Vector vParam) throws Exception {
        DChieu1VO dmucvo = null;
        try {
            String strSQL =
                "select  decode(INSTR (giatri_ts, '" + strWhere + "'),'0','KO_PHT','CO_PHT') chk_pht  from sp_thamso_ht where UPPER(ten_ts) ='DSACH_NHKB_KHONG_PHT'";
            dmucvo =
                    (DChieu1VO)findByPK(strSQL.toString(), vParam, strValueObjectVO,
                                        conn);


        } catch (Exception e) {
            throw e;
        }
        return dmucvo;
    }

    public KQDChieu1VO getNgay_dc(String strWhere,
                                  Vector vParam) throws Exception {
        KQDChieu1VO dmucvo = null;
        try {
			//Select theo truong tt_dc_tu_dong
            String strSQL =
                "select to_char(ngay_dc,'DD/MM/YYYY') ngay_dc,tt_dc_tu_dong from sp_bk_dc1 WHERE 1=1 " +
                strWhere;
            dmucvo =
                    (KQDChieu1VO)findByPK(strSQL.toString(), vParam, strValueObjectKQVO,
                                          conn);


        } catch (Exception e) {
            throw e;
        }
        return dmucvo;
    }

    public DChieu1VO getKB_CODE(String strWhere,
                                Vector vParam) throws Exception {
        DChieu1VO dmucvo = null;
        try {
            String strSQL =
                "SELECT	a.ma_nh, a.shkb, a.ten, b.ma  " + "  FROM	sp_dm_manh_shkb a, sp_dm_htkb b where a.shkb=b.ma " +
                strWhere;
            dmucvo =
                    (DChieu1VO)findByPK(strSQL.toString(), vParam, strValueObjectVO,
                                        conn);


        } catch (Exception e) {
            throw e;
        }
        return dmucvo;
    }

    public DChieu1VO checkTTSP(String strWhere,
                               Vector vParam) throws Exception {
        DChieu1VO dmucvo = null;
        try {
            String strSQL =
                "select  decode(INSTR (giatri_ts, '" + strWhere + "'),'0','KO_TTSP','CO_TTSP') chk_ttsp  from sp_thamso_ht where UPPER(ten_ts) ='DSACH_NHKB_KHONG_TTSP'";
            dmucvo =
                    (DChieu1VO)findByPK(strSQL.toString(), vParam, strValueObjectVO,
                                        conn);


        } catch (Exception e) {
            throw e;
        }
        return dmucvo;
    }

    public int updateTTCTiet(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_065_CTIET set ");

        strSQL.append(" tthai_duyet = ? ");
        v_param.add(new Parameter(vo.getTthai_duyet(), true));
        strSQL.append(" where mt_id = ? ");
        v_param.add(new Parameter(vo.getMt_id(), true));
        exc = executeStatement(strSQL.toString(), v_param, conn);

        return exc;
    }

    public int getCountDC(String whereClause,
                          Vector paramsDC) throws DAOException {
        int result = 0;
        try {
            String strSQL = "";
            strSQL += "select count(1) from sp_bk_dc1 ";
            if (whereClause != null && !"".equals(whereClause)) {
                strSQL += "where " + whereClause;
            }
            ResultSet rs = executeSelectStatement(strSQL, paramsDC, this.conn);
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObject + ".getCountTKKB(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return result;
    }

    public int insert066(DNQTVO vo) throws Exception {
        int nExc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        strSQL.append(" Insert into sp_066 (id ");
        strSQL2.append(" ) values (? ");
        v_param.add(new Parameter(vo.getId(), true));

        strSQL.append(", nguoi_tao");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getNguoi_tao(), true));

        strSQL.append(", ngay_tao");
        strSQL2.append(", sysdate");

        strSQL.append(", ngay_qtoan");
        strSQL2.append(", ?");
        v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_qtoan(),
                                                              "dd/MM/yyyy"),
                                      true));

        strSQL.append(", loai_qtoan");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getLoai_qtoan(), true));

        strSQL.append(", qtoan_thu");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getQtoan_thu(), true));

        strSQL.append(", nhkb_nhan");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getNhkb_nhan(), true));

        strSQL.append(", nhkb_chuyen");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getNhkb_chuyen(), true));

        strSQL.append(", qtoan_chi");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getQtoan_chi(), true));

        strSQL.append(", ndung_qtoan");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getNdung_qtoan(), true));

        strSQL.append(", kq_dc_ttsp");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getKq_dc_ttsp(), true));

        strSQL.append(", kq_dc_pht");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getKq_dc_pht(), true));

        strSQL.append(", trang_thai");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getTrang_thai(), true));
        
        strSQL.append(", kq_dxn_thop");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getKq_dxn_thop(), true));
        
        strSQL.append(", loai_tien");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getLoai_tien(), true));

        strSQL.append(strSQL2);
        strSQL.append(")");

        nExc = executeStatement(strSQL.toString(), v_param, conn);

        return nExc;
    }

    public Collection getTso_KB(String strWhere,
                                Vector vParam) throws Exception {
        Collection reval = null;
        try {
            String strSQL =
                "SELECT a.ten_ts, a.giatri_ts, a.cho_phep_sua, a.kb_id FROM sp_thamso_kb a WHERE 1=1 " +
                strWhere;
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectDNQTVO,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectDNQTVO + ".getTso_KB(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }



// 20171117 thuongdt bo sung NGAY_BU_CHI theo yeu cau bo sung
    public Collection getLst066(String strWhere,
                                Vector vParam, Integer page, Integer count,
                                Integer[] totalCount) throws Exception {
        //      Collection reval = null;
        try {
            String strSQL =
                "SELECT	a.id,a.loai_tien, a.nhkb_chuyen, a.nhkb_nhan, a.nguoi_tao, a.ngay_tao," + 
                " a.nguoi_ks, a.ngay_ks, to_char(a.ngay_qtoan,'DD/MM/YYYY') ngay_qtoan, a.loai_qtoan,  trunc(a.qtoan_thu,2) qtoan_thu," + 
                "  trunc(a.qtoan_chi,2) qtoan_chi, a.ndung_qtoan, a.kq_dc_ttsp, a.kq_dc_pht, a.chu_ky," + 
                " a.msg_id, a.trang_thai,a.trang_thai_qtoan, decode(a.ERROR_CODE,null,null,a.ERROR_CODE || ' - ' || a.error_desc) mo_ta, to_char(a.NGAY_BU_CHI,'DD/MM/YYYY') NGAY_BU_CHI " + 
                " FROM	sp_066 a, sp_dm_manh_shkb c, sp_dm_htkb d" + 
                " WHERE  c.shkb = d.ma AND a.nhkb_chuyen = c.ma_nh " +
                strWhere + " order by to_char(a.ngay_qtoan,'YYYYMMDD') desc, nhkb_chuyen";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           strValueObjectDNQTVO, page, count,
                                           totalCount);
          } catch (Exception e) {
              throw e;
          }
    }

    public int update066(DNQTVO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_066 set ");
        if (vo.getTrang_thai() != null && "" != vo.getTrang_thai()) {
            strSQL.append(" trang_thai = ? ");
            v_param.add(new Parameter(vo.getTrang_thai(), true));
        }
        if (vo.getMsg_id() != null && "" != vo.getMsg_id()) {
            strSQL.append(", msg_id = ? ");
            v_param.add(new Parameter(vo.getMsg_id(), true));
        }
        if (vo.getNguoi_ks() != null && "" != vo.getNguoi_ks()) {
            strSQL.append(", nguoi_ks = ? ");
            v_param.add(new Parameter(vo.getNguoi_ks(), true));
        }
        //        if (vo.getNgay_ks() != null && "" != vo.getNgay_ks()) {
        strSQL.append(", ngay_ks = sysdate ");
        // v_param.add(new Parameter(vo.getNgay_ks(), true));
        //        }
        if (vo.getChu_ky() != null && "" != vo.getChu_ky()) {
            strSQL.append(", chu_ky = ? ");
            v_param.add(new Parameter(vo.getChu_ky(), true));
        }
        strSQL.append(" where id = ? ");
        v_param.add(new Parameter(vo.getId(), true));
        exc = executeStatement(strSQL.toString(), v_param, conn);

        return exc;
    }

  public XNKQDCDataVO getSoDuCOT(String strSoDu,
                              Vector params) throws Exception {
      try {
          String strSQL = "";
          strSQL +=
                  " SELECT a.ma_kb, a.ma_nh, a.so_du_cot," + 
                  " b.so_tien so_chi, c.so_tien so_thu," + 
                  " nvl((a.so_du_cot + NVL (c.so_tien, 0) - NVL (b.so_tien, 0)),0) chksodu" + 
                  "  FROM	sp_so_du a, (SELECT	 loai_qtoan, ngay_qtoan, sum(nvl(so_tien,0)) so_tien,ma_nh_chuyen,ma_kb" + 
                  " FROM	 sp_quyet_toan WHERE	 qtoan_dvi = 'Y' AND lai_phi in ('01','03') AND ma_nt = 'VND' " +
                  " and ngay_qtoan like sp_util_pkg.fnc_get_ngay_lam_viec_truoc(sysdate-1) and loai_qtoan='D' group by loai_qtoan, ngay_qtoan, ma_nh_chuyen,ma_kb) b," + 
                  " (SELECT	 loai_qtoan, ngay_qtoan, sum(nvl(so_tien,0)) so_tien,ma_nh_chuyen,ma_kb" + 
                  " FROM	 sp_quyet_toan WHERE	 qtoan_dvi = 'Y' AND lai_phi in ('01','03') AND ma_nt = 'VND' " +
                  " and ngay_qtoan like sp_util_pkg.fnc_get_ngay_lam_viec_truoc(sysdate-1) and loai_qtoan='C' group by loai_qtoan, ngay_qtoan, ma_nh_chuyen,ma_kb) c" + 
                  " WHERE		 a.ma_kb = b.ma_kb(+)" + 
                  " AND a.ma_nh = b.ma_nh_chuyen(+) AND a.loai_tien = 'VND' AND a.ma_kb = c.ma_kb(+) AND a.ma_nh = c.ma_nh_chuyen(+) AND ngay_gd like sp_util_pkg.fnc_get_ngay_lam_viec_truoc(sysdate-1)" + strSoDu;
          XNKQDCDataVO phtVO =
              (XNKQDCDataVO)findByPK(strSQL.toString(), params,
                                    strValueObjectXNKQDCDataVO, conn);
          return phtVO;
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectXNKQDCDataVO + ".getSoDuCOT(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
  }
  public int updateInit066(DNQTVO vo, String strCHK) throws Exception {
      int exc = 0;
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      strSQL.append("update sp_066 set ");
      if (vo.getTrang_thai() != null && "" != vo.getTrang_thai()) {
          strSQL.append(" trang_thai = ? ");
          v_param.add(new Parameter(vo.getTrang_thai(), true));
      }
      strSQL.append(" where nhkb_chuyen = ? AND trang_thai='01'");
     
      v_param.add(new Parameter(vo.getNhkb_chuyen(), true));
      strSQL.append(strCHK);
      exc = executeStatement(strSQL.toString(), v_param, conn);

      return exc;
  }
  
  public Collection chkSoDu(String strWhere,
                              Vector vParam) throws Exception {
      Collection reval = null;
      try {
          String strSQL =
              "SELECT a.id, a.so_du_cot FROM sp_so_du a WHERE 1=1 " +
              strWhere;
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectDNQTVO,
                                         this.conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectDNQTVO + ".getTso_KB(): " +
                               ex.getMessage(), ex);
          throw daoEx;
      }
      return reval;
  }


    public String getMaKB(String kbTinh) throws Exception {
        String result = "";
        Vector vector = new Vector();
        vector.add(new Parameter(kbTinh,true));
        String query = "select ma from sp_dm_htkb where id = ?";
        ResultSet rs = executeSelectStatement(query, vector, conn);
        if(rs.next())
          result = rs.getString("ma");
        return result;
    }

    public int updateInit065(DNQTVO vo,String giatri_ts) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("UPDATE SP_065 SET TTHAI_DXN_THOP = '00' ");
        strSQL.append(" WHERE TTHAI_DXN_THOP='01' AND TRUNC(NGAY_DC) <= TRUNC(SYSDATE) AND " +
            " trunc(NGAY_DC) >= trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE - " + giatri_ts + ")) " +
            " AND send_bank='" + vo.getNhkb_chuyen() + "'");
        return executeStatement(strSQL.toString(), new Vector(), conn);
    }
	//ThuongDT-20161212 - Lay danh sach de nghi quyet toan
  public Collection getDeNghiQuyetToan(String nhkb_chuyen, String nhkb_nhan, String ngayDC) throws Exception {
    Collection cllDeNghiQT = new ArrayList();
    XNKQDCDataVO xnkqVO = new XNKQDCDataVO();
      //XNKQDCDataVO
      CallableStatement cs = null;
      cs =
      conn.prepareCall("{call SP_DOI_CHIEU_TU_DONG_VND.pro_solieu_denghi_Quyettoan(?,?,?,?,?)}");
      cs.setString(1, ngayDC);
      cs.setString(2, nhkb_chuyen);
      cs.setString(3, nhkb_nhan);    
      cs.registerOutParameter(4, Types.VARCHAR);
      cs.registerOutParameter(5, java.sql.Types.VARCHAR);
      cs.execute();      
      String  p_so_mon_chi = cs.getString(4);
      String p_so_tien_chi = cs.getString(5);
      xnkqVO.setReceive_bank(nhkb_nhan);
      xnkqVO.setSend_bank(nhkb_chuyen);
      xnkqVO.setNgay_dc(ngayDC);
      xnkqVO.setMon_chi_dtu_kbnn(Long.parseLong(p_so_mon_chi));
      xnkqVO.setTien_chi_dtu_kbnn(new BigDecimal(p_so_tien_chi));
      xnkqVO.setSomon_chi(Long.parseLong(p_so_mon_chi));
      xnkqVO.setSotien_chi(new BigDecimal(p_so_tien_chi));
      xnkqVO.setSotien_thu(new BigDecimal("0"));
      cllDeNghiQT.add(xnkqVO);
      return cllDeNghiQT;
  }
  //ThuongDT-20161215-Lay chi tiet de nghi quyet toan
  public Collection getDeNghiQuyetToanTHData(String nhkb_chuyen, String nhkb_nhan, String ngayDC) throws Exception {
    Collection cllDeNghiQT = new ArrayList();
    XNKQDCDataVO xnkqVO = new XNKQDCDataVO();
      //XNKQDCDataVO
      CallableStatement cs = null;
      cs =
      conn.prepareCall("{call SP_DOI_CHIEU_TU_DONG_VND.pro_solieu_denghi_Quyettoan(?,?,?,?,?)}");
      cs.setString(1, ngayDC);
      cs.setString(2, nhkb_chuyen);
      cs.setString(3, nhkb_nhan);    
      cs.registerOutParameter(4, Types.VARCHAR);
      cs.registerOutParameter(5, java.sql.Types.VARCHAR);
      cs.execute();      
      String  p_so_mon_chi = cs.getString(4);
      String p_so_tien_chi = cs.getString(5);
     
    xnkqVO.setKet_chuyen_chi(new BigDecimal(p_so_tien_chi));
    xnkqVO.setPs_chi(new BigDecimal(p_so_tien_chi));
    xnkqVO.setKet_chuyen_chi(new BigDecimal(p_so_tien_chi));
    xnkqVO.setKet_chuyen_thu(new BigDecimal("0"));
    xnkqVO.setSo_du_dau_ngay(new BigDecimal("0"));

      cllDeNghiQT.add(xnkqVO);
      return cllDeNghiQT;
  }
  //HungBM-20170505
    public boolean checkDien066(String kq_id) throws Exception {
      boolean bCheck066 =false;
      Vector vector = new Vector();
      String query = "select 1 from SP_066 where loai_qtoan = '01' and trang_thai in ('01','02','04') and KQ_DC_TTSP = '"+kq_id+"'";
      try{
      ResultSet rs = executeSelectStatement(query, null, conn);
      bCheck066= rs.next();
      }catch (Exception ex) {
            DAOException daoEx =
                new DAOException("DChieu1DAO.checkDien066(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
      return bCheck066;
      
    }   
//20171201 thuongdt them moi ham getDSachTHopThuChi phuc vu bao cao tra cuu thu chi toan quoc
  public Collection getDSachTHopThuChi(String strWhere,
                              Vector vParam,String loai_tien, Integer page,
                                           Integer count,
                                           Integer[] totalCount) throws Exception {
      Collection reval = null;
    String str065 = "sp_065_ngoai_te";
      if("VND".equals(loai_tien) || "VNĐ".equals(loai_tien)  )
       str065 = "sp_065";
      try {
          String strSQL =
              " select to_char(a.NGAY_DC,'dd/MM/YYYY')NGAY_DC,e.TEN ten_kb,d.TEN ten_nh,a.LOAI_TIEN,a.TIEN_CHI_DTU_KBNN + a.TIEN_CHI_TCONG_KBNN  tong_ps_chi,a.TIEN_THU_DTU_KBNN + a.TIEN_THU_TCONG_KBNN + nvl(b.TONG_PS_PHT,0) tong_ps_thu " + 
              " from "+str065+" a,"+str065+" b, SP_DM_NGAN_HANG d," + 
              " SP_DM_HTKB e, SP_DM_MANH_SHKB f" + 
              " where a.PHT_ID=b.ID(+)" + 
              " and a.RECEIVE_BANK = d.MA_NH" + 
              " and e.MA = f.SHKB" + 
              " and f.MA_NH = a.SEND_BANK" + 
             // " and a.APP_TYPE = 'TTSP' and b.APP_TYPE = 'PHT' " + 
              " and a.APP_TYPE = 'TTSP'"+
              " and a.id in(  SELECT   MAX (id)   FROM   "+str065+" a " + 
                " WHERE   a.ket_qua = '0' " + strWhere+" GROUP BY   a.app_type," + 
                " a.send_bank,a.receive_bank,a.ngay_dc)"+              
              " order by a.NGAY_DC ";
        reval= executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                       THopQTThuChiVO, page, count,
                                       totalCount);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(THopQTThuChiVO + ".getDSachTHopThuChi(): " +
                               ex.getMessage(), ex);
  //            daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
  //20171201 thuongdt them moi ham getTHopThuChi lay tong ket qua tra cuu tu ham getDSachTHopThuChi phuc vu bao cao tra cuu thu chi toan quoc
  public THopQTThuChiVO getTHopThuChi(String strWhere,
                              Vector vParam,String loai_tien) throws Exception {
      THopQTThuChiVO reval = new THopQTThuChiVO();
    String str065 = "sp_065_ngoai_te";
      if("VND".equals(loai_tien) || "VNĐ".equals(loai_tien)  )
       str065 = "sp_065";
      try {
//          String strSQL =
//              " select sum(a.TIEN_CHI_DTU_KBNN + a.TIEN_CHI_TCONG_KBNN)  tong_ps_chi,sum(a.TIEN_THU_DTU_KBNN + a.TIEN_THU_TCONG_KBNN + nvl(b.TONG_PS_PHT,0)) tong_ps_thu" + 
//              " from "+str065+" a,"+str065+" b" + 
//              " where a.PHT_ID=b.ID(+)" +               
//              " and a.APP_TYPE = 'TTSP'" + 
//              " and a.KET_QUA = '0' " +
//              strWhere;
          
          String strSQL ="SELECT   SUM (b.tien_chi_dtu_kbnn) tong_ps_chi, " + 
          "         SUM (b.tien_thu_dtu_kbnn) tong_ps_thu " + 
          "  FROM   "+str065+" b " + 
          " where  b.id IN " + 
          "                 (  SELECT   MAX (a.id)" + 
          "                      FROM   "+str065+" a , SP_DM_HTKB e, SP_DM_MANH_SHKB f" + 
          "                     WHERE  e.MA = f.SHKB and f.MA_NH = a.SEND_BANK and a.ket_qua = '0' " + strWhere+
          "                  GROUP BY   a.app_type," + 
          "                             a.send_bank," + 
          "                             a.receive_bank," + 
          "                             a.ngay_dc)";
             
        reval= (THopQTThuChiVO)findByPK(strSQL, vParam, THopQTThuChiVO, conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(THopQTThuChiVO + ".getDSachTHopThuChi(): " +
                               ex.getMessage(), ex);
  //            daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
    
}

