package com.seatech.ttsp.dchieungoaite;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;

import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.DNQTVO;
import com.seatech.ttsp.dchieu.DuyetKQDCVO;
import com.seatech.ttsp.dchieu.KQDChieu1VO;

import com.seatech.ttsp.dchieu.XNKQDCDataVO;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.Statement;

import java.util.Collection;
import java.util.Vector;

/**
   * @modify: HungBM
   * @modify date: 11/2016   
   * @see: nang cap tra cuu phu hop voi yeu cau so 16
   * @track: 20161212
   * */

 /**
    * @modify: HungBM
    * @modify date: 20/02/2017   
    * @see: Sua cau lenh query: Thong nhat su dung "ma_nt" cho loai tien
    *       Loai bo "loai_tien"
    * @track: 20170220
    * */
  /* @HungBM: 
   * @modify: Lay du chieu pham vi doi chieu
   * @see: 20170320
   */
  /**
   * @modify: thuongdt
   * @modify-date: 04/10/2017
   * @see: sua cau querry tim kiem chi tiet chenh lech doi chieu
   * @find: 20171004
   * */

public class DChieuNgoaiTeDAO extends AppDAO {
    Connection conn = null;
    private String strValueObject = "com.seatech.ttsp.dchieungoaite.DChieuNgoaiTeDAO";
    private String strValueObjectVO = "com.seatech.ttsp.dchieu.DChieu1VO";
    private String strValueObjectKQVO = "com.seatech.ttsp.dchieu.KQDChieu1VO";
    private String strValueObjectKQCTVO = "com.seatech.ttsp.dchieu.KQDCChiTietVO";
    private String strValueObjectXNDCVO = "com.seatech.ttsp.dchieu.DuyetKQDCVO";
    private String strValueObjectXNKQDCDataVO = "com.seatech.ttsp.dchieu.XNKQDCDataVO";
    private String strValueObjectDNQTVO = "com.seatech.ttsp.dchieu.DNQTVO";
    private String strGDTCongVO = "com.seatech.ttsp.dchieu.GDichTCongVO";
    
    public DChieuNgoaiTeDAO(Connection conn) {
        this.conn = conn;
    }
    
    public Collection getDChieuList(String strWhere, Vector vParam) throws Exception {
  
        Collection reval = null;
        try {
            String strSQL = 
                "  SELECT DISTINCT a.id,a.mt_id,a.loai_tien, " +
                "          a.lan_dc, " + 
                "          a.loai_dc, " + 
                "          TO_CHAR (a.ngay_dc, 'DD/MM/YYYY') ngay_dc, " + 
                "          a.send_bank, " + 
                "          a.receive_bank, " + 
                "          a.created_date, " + 
                "          a.creator, " + 
                "          a.manager, " + 
                "          a.verified_date, " + 
                "          a.somon_thu, " + 
                "          a.sotien_thu, " + 
                "          a.somon_chi, " + 
                "          a.sotien_chi, " + 
                "          a.somon_dtu, " + 
                "          a.sotien_dtu, " + 
                "          a.somon_tcong, " + 
                "          DECODE (TO_CHAR (SYSDATE, 'DD/MM/YYYY'), " + 
                "                  TO_CHAR (a.ngay_dc, 'DD/MM/YYYY'), a.sotien_tcong, " + 
                "                  NULL) " + 
                "              sotien_tcong, " + 
                "          a.sodu_daungay, " + 
                "          a.so_du, " + 
                "          a.ps_thu, " + 
                "          a.ps_chi, " + 
                "          a.so_ketchuyen, " + 
                "          a.msg_id, " + 
                "          a.trang_thai, " + 
                "          a.insert_date, " + 
                "          DECODE (TO_CHAR (SYSDATE, 'DD/MM/YYYY'), " + 
                "                  TO_CHAR (a.ngay_dc, 'DD/MM/YYYY'), a.so_tien_thu_tcong, " + 
                "                  NULL) " + 
                "              so_tien_thu_tcong, " + 
                "          a.so_tien_thu_dtu, " + 
                "          a.so_mon_thu_tcong, " + 
                "          TO_CHAR (a.ngay_thuc_hien, 'DD/MM/YYYY') ngay_thuc_hien " + 
                "    FROM  sp_bk_dc1_ngoai_te a, sp_065_ngoai_te b " + 
                "   WHERE  1 = 1 AND a.mt_id = b.bk_id(+) ";
  
            strSQL += strWhere + " ORDER BY a.send_bank desc, a.trang_thai asc";
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectVO + ".getDChieuList(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getListDC(String nhkb_nhan, String strNHKB,String ma_nh, String tu_ngay, Vector vParam) throws Exception {
        Collection reval = null;
        try {
  
            String strSQL = "";
  
            strSQL +=
                    " SELECT  DISTINCT TO_CHAR (b.ngay, 'DD/MM/YYYY') ngay, a.mt_id, a.trang_thai,c.ket_qua," +
//                    " c.trang_thai trang_thai_kq,  c.tthai_ttin, c.tthai_ttin_thop, a.lan_dc, b.ma_nh send_bank, b.ten, c.mt_id kq_id" +
                    " c.trang_thai trang_thai_kq,  c.tthai_ttin, a.lan_dc, b.ma_nh send_bank, b.ten, c.mt_id kq_id" +
                    "  FROM (SELECT TRUNC (ngay_dc) ngay_dc, id, receive_bank, send_bank," +
                    " ngay_thuc_hien, app_type, loai_dc, trang_thai, lan_dc,mt_id FROM  sp_bk_dc1_ngoai_te" +
                    " WHERE 1 = 1 AND receive_bank = '" + nhkb_nhan +
                    "' AND SUBSTR(id,3,3) <> '701') a, (SELECT  tthai_dxn_thop, trang_thai, ket_qua, mt_id, bk_id, tthai_ttin," +
//                    " tthai_ttin_thop FROM  sp_065_ngoai_te WHERE  trang_thai <> '04') c," +
                    " tthai_ttin_thop FROM  sp_065_ngoai_te WHERE  trang_thai <> '04') c," +
                    " (SELECT   DISTINCT TRUNC (ngay_gd+1) ngay, d3.ma_nh ma_nh, d3.ten ten" +
                    " FROM   sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_ngan_hang d3" +
                    " WHERE  d1.ma_nh_kb = d2.ma_nh AND d1.ma_nh = d3.ma_nh AND d1.ma_nh_kb = '" +
                    nhkb_nhan + "'";
                    if(ma_nh!=null&&!"".equals(ma_nh)){
//                      strSQL += " AND d1.ma_nh <> '"+ ma_nh+"'" ;
//                        strSQL += " AND d1.ma_nh = '"+ ma_nh+"'" ;
                    }
                      
                    strSQL += " AND sp_util_pkg.fnc_get_ngay_trien_khai(d1.ma_nh_kb,d3.ma_nh,'NGOAI_TE') <= d1.ngay_gd " +
                    " AND TRUNC (d1.ngay_gd+1) <= TRUNC (SYSDATE) "+tu_ngay +
                    " AND TO_CHAR (ngay_gd+1, 'YYYYMMDD') NOT IN (  SELECT   ngay FROM sp_ngay_nghi) ORDER BY   ngay) b " +
                    " WHERE     a.ngay_dc(+) = b.ngay and a.send_bank(+)=b.ma_nh" +
                    " AND b.ngay IS NOT NULL AND a.mt_id = c.bk_id(+) AND (a.app_type = 'TTSP' OR a.app_type IS NULL)" +
                    " AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE) AND ( (c.ket_qua = '1' OR a.trang_thai = '00')" +
                    " AND a.id IN (SELECT   MAX (id) FROM   sp_bk_dc1_ngoai_te WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE)" +
                    " AND app_type = 'TTSP' and send_bank=a.send_bank AND receive_bank = '" +
                    nhkb_nhan + "' GROUP BY   ngay_dc, send_bank, loai_tien))" +
                    " OR a.trang_thai IS NULL) OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE)  AND a.id IN" +
                    " (  SELECT   MAX (id) FROM   sp_bk_dc1_ngoai_te WHERE   TO_DATE (ngay_dc) = TO_DATE (SYSDATE)" +
                    " AND app_type = 'TTSP' and send_bank=a.send_bank AND receive_bank = '" +
                    nhkb_nhan + "' GROUP BY   ngay_dc, send_bank, loai_tien))" +
                    " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.trang_thai <> '00') OR (TRUNC (a.ngay_thuc_hien) = TRUNC (SYSDATE)))" +
                    " ORDER BY  ngay, lan_dc DESC";
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectVO + ".getListDC(): " + ex.getMessage(), ex);
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
                    "SELECT DISTINCT a.mt_id, a.bk_id, a.ket_qua, a.lan_dc, to_char(a.ngay_dc,'DD/MM/YYYY HH24:mi:ss') ngay_dc, a.send_bank," +
                    " a.receive_bank, TRUNC(a.created_date), a.creator, a.manager," +
                    " a.verified_date, a.sodu_kbnn, a.chenh_lech, a.msg_id," +
                    " a.trang_thai trang_thai_kqdc, a.ket_qua_pht," +
                    "b.ltt_kb_thua, b.ltt_kb_thieu, b.dts_kb_thieu, b.dts_kb_thua, c.trang_thai trang_thai_bkdc, a.ly_do_chenh_lech " +
                    " FROM sp_065_ngoai_te a, sp_bk_dc1_ngoai_te c, (  " + " select" +
                    "(select count(0) from sp_065_dtl_ngoai_te where 1=1 and bkq_id='" +
                    strbke_id + "'" +
                    " and trang_thai = 1 and MT_TYPE = '103')  ltt_kb_thua ," +
                    "(Select count(0) from sp_065_dtl_ngoai_te where 1=1 and bkq_id= '" +
                    strbke_id + "'" +
                    " and trang_thai = 0 and MT_TYPE = '103') ltt_kb_thieu," +
                    "(select count(0) from sp_065_dtl_ngoai_te where 1=1 and bkq_id= '" +
                    strbke_id + "'" +
                    " and trang_thai = 0 and MT_TYPE in ('195' ,'196')) dts_kb_thieu," +
                    "(select count(0) from sp_065_dtl_ngoai_te where 1=1 and bkq_id= '" +
                    strbke_id + "'" +
                    " and trang_thai = 1 and MT_TYPE in ('195' ,'196')) dts_kb_thua" +
                    " from dual) b where 1=1 and a.mt_id='" + strbke_id + "'" +
                    " and a.trang_thai <> '04' and a.bk_id=c.mt_id and a.loai_tien = c.loai_tien ";
  
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectKQVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectKQVO + ".getKQDChieu(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getKQDetailNgoaiTe(String strbke_id,
                                  Vector vParam) throws Exception {
    
        Collection reval = null;
        try {
    
            String strSQL = "";
    
            strSQL +=
                    "SELECT DISTINCT a.mt_id, a.bk_id, a.ket_qua, a.lan_dc, to_char(a.ngay_dc,'DD/MM/YYYY HH24:mi:ss') ngay_dc, a.send_bank," +
                    " a.receive_bank, TRUNC(a.created_date), a.creator, a.manager," +
                    " a.verified_date, a.sodu_kbnn, a.chenh_lech, a.msg_id," +
                    " a.trang_thai trang_thai_kqdc, a.ket_qua_pht," +
                    "b.ltt_kb_thua, b.ltt_kb_thieu, b.dts_kb_thieu, b.dts_kb_thua, c.trang_thai trang_thai_bkdc, a.ly_do_chenh_lech " +
                    " FROM sp_065_ngoai_te a, sp_bk_dc1_ngoai_te c, (  " + " select" +
                    "(select count(0) from sp_065_dtl_ngoai_te where 1=1 and bkq_id='" +
                    strbke_id + "'" +
                    " and trang_thai = 1 and MT_TYPE = '103')  ltt_kb_thua ," +
                    "(Select count(0) from sp_065_dtl_ngoai_te where 1=1 and bkq_id= '" +
                    strbke_id + "'" +
                    " and trang_thai = 0 and MT_TYPE = '103') ltt_kb_thieu," +
                    "(select count(0) from sp_065_dtl_ngoai_te where 1=1 and bkq_id= '" +
                    strbke_id + "'" +
                    " and trang_thai = 0 and MT_TYPE in ('195' ,'196')) dts_kb_thieu," +
                    "(select count(0) from sp_065_dtl_ngoai_te where 1=1 and bkq_id= '" +
                    strbke_id + "'" +
                    " and trang_thai = 1 and MT_TYPE in ('195' ,'196')) dts_kb_thua" +
                    " from dual) b where 1=1 and a.id='" + strbke_id + "'" +
                    " and a.trang_thai <> '04' and a.bk_id=c.mt_id and a.loai_tien = c.loai_tien ";
    
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectKQVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectKQVO + ".getKQDChieu(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getKQDCChiTiet(String strbke_id,
                                     Vector vParam) throws Exception {
  
        Collection reval = null;
        try {
  
            String strSQL = "";
          //20171004 thuongdt sua cau querry bo sung tim kiem chi tiet chenh lech
          
          strSQL +=
                 //lay cac bang ke co chenh lech trung mt_id va khac trung tat ca
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
                  "    CASE WHEN a.f20 <> b.f20 then decode(a.TRANG_THAI,1,'KB:','NH:') ||a.f20||','|| decode(b.TRANG_THAI,1,'KB:','NH:') ||b.f20||'; ' ELSE '' END ||" + 
                  "    CASE WHEN a.send_date <> b.send_date then decode(a.TRANG_THAI,1,'KB:','NH:') ||a.send_date||','|| decode(b.TRANG_THAI,1,'KB:','NH:') ||b.send_date||'; ' ELSE '' END ||" + 
                  "    CASE WHEN a.ngay_ts <> b.ngay_ts then decode(a.TRANG_THAI,1,'KB:','NH:') ||a.ngay_ts||','|| decode(b.TRANG_THAI,1,'KB:','NH:') ||b.ngay_ts||'; ' ELSE '' END ||" + 
                  "    CASE WHEN a.loai_tien <> b.loai_tien then decode(a.TRANG_THAI,1,'KB:','NH:') ||a.loai_tien||','|| decode(b.TRANG_THAI,1,'KB:','NH:') ||b.loai_tien||'; ' ELSE '' END     as ctiet_ly_do "+
                  " FROM sp_065_dtl_ngoai_te a" + 
                  " JOIN sp_065_dtl_ngoai_te b using(mt_id) WHERE a.bkq_id = b.bkq_id AND a.trang_thai = '0' AND b.trang_thai = '1' AND  '"+strbke_id+"' like a.bkq_id||'%' ) "+
                  //noi dung cau sql goc truc khi sua
                  " SELECT DISTINCT a.id, a.bkq_id, a.bk_id, a.mt_id,a.loai_tien, to_char(a.send_date,'DD/MM/YYYY HH24:mi:ss') send_date, a.f20, a.f21, a.tthai_duyet," +
                  " a.f32as3, to_char(a.ngay_ts,'DD/MM/YYYY') ngay_ts, a.ghi_chu, a.mt_type, a.app_type," +
                  " a.sai_yeu_to, a.trang_thai, a.insert_date, to_char(a.ngay_ct,'DD/MM/YYYY') ngay_ct, decode(substr(a.mt_id,3,3),'701','DI','DEN') di_den, decode(substr(a.mt_id,6,3),'196','DTS','195','DTS','LTT') ltt_dts," +
                  " c.send_bank, (select ten from sp_dm_ngan_hang where ma_nh=c.send_bank) ten_send_bank," +
                  " c.receive_bank, b.ten, " +
                  //bo sung them truong the hien chi tiet chenh lech
                  " dlt.ly_do ldo_clech,dlt.ctiet_ly_do ctiet_clech "+
                  " FROM sp_065_dtl_ngoai_te a, sp_dm_ngan_hang b, sp_065_ngoai_te c, SP_065_DTL_CLech dlt" +
                  " where 1=1 and a.bkq_id= c.mt_id and c.receive_bank=b.ma_nh " +
                   //join bang tam
                  " and a.mt_id = dlt.mt_id(+) " +
                  " and  '" + strbke_id + "' like a.bkq_id||'%' ";
  
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectKQCTVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectKQVO + ".getKQDCChiTiet(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public KQDChieu1VO getNgay_dc(String strWhere, Vector vParam) throws Exception {
        KQDChieu1VO dmucvo = null;
        try {
            String strSQL = "select to_char(ngay_dc,'DD/MM/YYYY') ngay_dc from sp_bk_dc1_ngoai_te WHERE 1=1 " + strWhere;
            dmucvo = (KQDChieu1VO)findByPK(strSQL.toString(), vParam, strValueObjectKQVO, conn);
        } catch (Exception e) {
            throw e;
        }
        return dmucvo;
    }
    
    public Collection getCheckTThai(String whereClause,
                                    Vector params) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL += "SELECT a.id, a.trang_thai, a.ket_qua, a.tthai_dxn_thop, b.trang_thai FROM  sp_065_ngoai_te a, sp_066 b " +
                    " WHERE a.id=b.kq_dc_ttsp (+)  and  a.id = ( SELECT max(id) FROM sp_065_ngoai_te WHERE  ngay_dc IN (SELECT  TRUNC (max(d1.ngay_gd+1)) ngay " +
                    " FROM   sp_tso_cutoftime d1 WHERE  1 = 1 " + whereClause;
            reval = executeSelectStatement(strSQL.toString(), params, strValueObjectVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObject + ".getCheckTThai(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getNgayBDau(String whereClause,
                                  Vector params) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL += "SELECT DISTINCT TRUNC (max(d1.ngay_gd+1)) ngay FROM  sp_tso_cutoftime d1 WHERE 1 = 1" + whereClause;
            reval = executeSelectStatement(strSQL.toString(), params, strValueObjectVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObject + ".getNgayBDau(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getCheckKQDXN(String strWhere,
                                    Vector vParam) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT id, tthai_dxn_thop, ngay_dc, receive_bank FROM sp_065_ngoai_te WHERE id IN (SELECT max(id) FROM sp_065_ngoai_te WHERE 1=1 " +
                    strWhere + " GROUP BY ngay_dc) AND tthai_dxn_thop ='01'  ";
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectVO + ".getDChieuList(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getListSDThuCong(String whereClause, Vector params) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    "SELECT a.id, a.bk_id,a.mt_id, a.ket_qua, a.lan_dc, a.ngay_dc, a.send_bank," +
                    " a.receive_bank, TRUNC(a.created_date) created_date, a.creator, a.manager, a.ket_chuyen_thu, a.ket_chuyen_chi," +
                    " a.verified_date, a.sodu_kbnn, a.chenh_lech, a.msg_id," +
                    " TRUNC(a.insert_date) insert_date, a.ket_qua_pht, a.tong_ps_pht," +
                    " a.tong_mon_pht, a.app_type, a.ly_do_xn, a.mon_thu_tcong_kbnn," +
                    " a.mon_chi_tcong_kbnn, a.tien_thu_tcong_kbnn," +
                    " a.tien_chi_tcong_kbnn, a.tthai_dxn_thop, a.chenh_lech_thu_tcong," +
                    " a.chenh_lech_chi_tcong, a.trang_thai trang_thai_kqdc, b.trang_thai trang_thai_bkdc, b.sotien_tcong, b.so_tien_thu_tcong  FROM sp_065_ngoai_te a, sp_bk_dc1_ngoai_te b WHERE 1=1 and a.bk_id=b.mt_id " +
                    whereClause;
            reval = executeSelectStatement(strSQL.toString(), params, strValueObjectKQVO, conn);
            return reval;
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObject + ".getListSDThuCong(): " + ex.getMessage(), ex);
            throw daoEx;
        }
    }
    
    public int updateKQ(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_065_ngoai_te set ");
        strSQL.append(" trang_thai = '01'");
        strSQL.append(" where trang_thai='00' and mt_id = ? ");
        v_param.add(new Parameter(vo.getMt_id(), true));
        exc = executeStatement(strSQL.toString(), v_param, conn);
  
        return exc;
    }
    
    public int updateTTBK(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_bk_dc1_ngoai_te set ");
  
        if ("1".equals(vo.getKet_qua())) {
            strSQL.append("trang_thai = '01' ");
            strSQL.append(", ngay_thuc_hien= to_date(sysdate) ");
        } else if ("0".equals(vo.getKet_qua())) {
            strSQL.append(" trang_thai = '02' ");
            strSQL.append(", ngay_thuc_hien= to_date(sysdate) ");
        }
        strSQL.append(" where trang_thai='00' and mt_id = ? ");
        v_param.add(new Parameter(vo.getBk_id(), true));
        exc = executeStatement(strSQL.toString(), v_param, conn);
  
        return exc;
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
    
    public Collection getListXNDChieu(String strWhere,
                                      Vector vParam) throws Exception {
  
        Collection reval = null;
        try {
  
            String strSQL = "";
//ManhNV-20150126
            strSQL +=
//                    "SELECT DISTINCT a.id,a.mt_id, a.bk_id, to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc,to_char(a.ngay_thuc_hien_dc,'DD/MM/YYYY') ngay_thuc_hien_dc,a.ket_qua, a.lan_dc, a.send_bank, a.trang_thai, a.tthai_dxn_thop, a.receive_bank, a.ket_qua_dxn_thop, a.tthai_ttin, a.tthai_ttin_thop" +
                      "SELECT DISTINCT a.id, a.mt_id, a.bk_id, to_char(a.ngay_dc,'DD/MM/YYYY') " +
                      "ngay_dc,to_char(a.ngay_thuc_hien_dc,'DD/MM/YYYY') ngay_thuc_hien_dc,a.ket_qua, " +
                      "a.lan_dc, a.send_bank, a.trang_thai, a.receive_bank, a.tthai_ttin, a.tthai_dxn_thop, a.ket_qua_dxn_thop" +
                    " FROM sp_065_ngoai_te a, sp_066 b WHERE a.mt_id=b.kq_dc_ttsp (+) ";
  
            strSQL += strWhere; //+ " ORDER BY a.send_bank, a.ngay_dc,  a.lan_dc desc";
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNDCVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectXNDCVO + ".getListXNDChieu(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    //ManhNV-20150420
    public Collection getListXNDDuyetChieu(String strWhere,
                                    Vector vParam) throws Exception {
  
      Collection reval = null;
      try {
  
          String strSQL = "";
  //ManhNV-20150126
          strSQL +=
  //                    "SELECT DISTINCT a.id,a.mt_id, a.bk_id, to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc,to_char(a.ngay_thuc_hien_dc,'DD/MM/YYYY') ngay_thuc_hien_dc,a.ket_qua, a.lan_dc, a.send_bank, a.trang_thai, a.tthai_dxn_thop, a.receive_bank, a.ket_qua_dxn_thop, a.tthai_ttin, a.tthai_ttin_thop" +
                    "SELECT DISTINCT a.mt_id, a.bk_id, to_char(a.ngay_dc,'DD/MM/YYYY') " +
                    "ngay_dc,to_char(a.ngay_thuc_hien_dc,'DD/MM/YYYY') ngay_thuc_hien_dc,a.ket_qua, " +
                    "a.lan_dc, a.send_bank, a.trang_thai, a.receive_bank, a.tthai_ttin "+ //, a.ket_qua_dxn_thop" +
                  " FROM sp_065_ngoai_te a, sp_066 b WHERE a.mt_id=b.kq_dc_ttsp (+) ";
  
          strSQL += strWhere; //+ " ORDER BY a.send_bank, a.ngay_dc,  a.lan_dc desc";
          reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNDCVO, conn);
      } catch (Exception ex) {
          DAOException daoEx = new DAOException(strValueObjectXNDCVO + ".getListXNDChieu(): " + ex.getMessage(), ex);
          throw daoEx;
      }
      return reval;
  }
    
    public Collection getXNKQData(String strbke_id, Vector vParam) throws Exception {
  
        Collection reval = null;
        try {
  
            String strSQL = "";
  
            strSQL +=
                "  SELECT  DISTINCT " + 
                "          a.bk_id, " + 
                "          a.loai_tien, " + 
                "          a.ket_qua, " + 
                "          a.lan_dc, " + 
                "          TO_CHAR (a.ngay_dc, 'DD/MM/YYYY') ngay_dc, " + 
                "          a.send_bank, " + 
                "          a.receive_bank, " + 
                "          trunc(a.created_date) created_date, " + 
                "          a.sodu_kbnn, " + 
                "          a.app_type, " + 
                "          a.chenh_lech, " + 
                "          TO_CHAR (a.insert_date, 'DD/MM/YYYY') insert_date, " + 
                "          a.trang_thai trang_thai_kqdc, " + 
                "          a.ket_qua_pht, " + 
                "          a.mon_thu_dtu_kbnn, " + 
                "          a.mon_chi_dtu_kbnn, " + 
                "          a.tien_thu_dtu_kbnn, " + 
                "          a.tien_chi_dtu_kbnn, " + 
                "          a.mon_thu_tcong_kbnn, " + 
                "          a.mon_chi_tcong_kbnn, " + 
                "          a.tien_thu_tcong_kbnn, " + 
                "          a.tien_chi_tcong_kbnn, " + 
                "          ABS (a.tien_thu_tcong_kbnn - c.so_tien_thu_tcong) chenhlech_thu, " + 
                "          ABS (a.tien_chi_tcong_kbnn - c.sotien_tcong) chenhlech_chi, " + 
                "          a.so_du_dau_ngay, " + 
                "          a.ket_chuyen_chi, " + 
                "          a.ket_chuyen_thu, " + 
                "          b.ltt_kb_thua, " + 
                "          b.ltt_kb_thieu, " + 
                "          b.dts_kb_thieu, " + 
                "          b.dts_kb_thua, " + 
                "          c.sotien_thu, " + 
                "          c.sotien_chi, " + 
                "          c.sotien_dtu, " + 
                "          c.sotien_tcong, " + 
                "          c.somon_thu, " + 
                "          c.somon_chi, " + 
                "          c.somon_dtu, " + 
                "          c.somon_tcong, " + 
                "          c.so_mon_thu_tcong, " + 
                "          c.so_mon_thu_dtu, " + 
                "          c.trang_thai trang_thai_bkdc, " + 
                "          c.so_tien_thu_tcong, " + 
                "          c.so_tien_thu_dtu, a.ly_do_chenh_lech " + 
                "    FROM  sp_065_ngoai_te a, " + 
                "          sp_bk_dc1_ngoai_te c, " + 
                "          (SELECT  (SELECT  COUNT (0) " + 
                "                      FROM  sp_065_dtl_ngoai_te " + 
                "                     WHERE      1 = 1 " + 
                "                            AND bkq_id IN ("+strbke_id+") " + 
                "                            AND trang_thai = 1 " + 
                "                            AND mt_type = '103') " + 
                "                       ltt_kb_thua, " + 
                "                   (SELECT  COUNT (0) " + 
                "                      FROM  sp_065_dtl_ngoai_te " + 
                "                     WHERE      1 = 1 " + 
                "                            AND bkq_id IN ("+strbke_id+") " + 
                "                            AND trang_thai = 0 " + 
                "                            AND mt_type = '103') " + 
                "                       ltt_kb_thieu, " + 
                "                   (SELECT  COUNT (0) " + 
                "                      FROM  sp_065_dtl_ngoai_te " + 
                "                     WHERE      1 = 1 " + 
                "                            AND bkq_id IN ("+strbke_id+") " + 
                "                            AND trang_thai = 0 " + 
                "                            AND mt_type IN ('195', '196')) " + 
                "                       dts_kb_thieu, " + 
                "                   (SELECT  COUNT (0) " + 
                "                      FROM  sp_065_dtl_ngoai_te " + 
                "                     WHERE      1 = 1 " + 
                "                            AND bkq_id IN ("+strbke_id+") " + 
                "                            AND trang_thai = 1 " + 
                "                            AND mt_type IN ('195', '196')) " + 
                "                       dts_kb_thua " + 
                "             FROM  DUAL) b " + 
                "   WHERE  1 = 1 AND a.loai_tien = c.loai_tien  AND a.bk_id = c.mt_id AND a.mt_id in ("+strbke_id+") " + 
                "ORDER BY  app_type ";
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNKQDCDataVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectXNKQDCDataVO + ".getXNKQData(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public int XNHuy_Duyet(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_065_ngoai_te set ");
  
        if ("huy".equals(vo.getBtton())) {
            strSQL.append(" trang_thai = ? ");
            v_param.add(new Parameter("04", true));
            strSQL.append(", ket_qua = null ");
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
        
        strSQL.append(" where mt_id = ? ");
        v_param.add(new Parameter(vo.getMt_id(), true));
        exc = executeStatement(strSQL.toString(), v_param, conn);
  
        return exc;
    }
    
    public int updateTT(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_bk_dc1_ngoai_te set ");
  
        strSQL.append("trang_thai = '00' ");
  
        strSQL.append(" where mt_id = ? ");
        v_param.add(new Parameter(vo.getBk_id(), true));
        exc = executeStatement(strSQL.toString(), v_param, conn);
  
        return exc;
    }
    
    public DNQTVO chkDNQT(String strKB,
                                Vector params) throws Exception {
        try {
            String strSQL = "";
            strSQL +=
                    "SELECT SUM (cho_duyet) chk_duyet, SUM (qtoan) chk_qtoan,sum(qtoan_tdong) qtoan_tdong, to_char(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE),'DD/MM/YYYY') ngay_chk " + 
                    " FROM  (SELECT  COUNT (0) cho_duyet, 0 AS qtoan, 0 as qtoan_tdong " + 
                    " FROM   sp_066 WHERE  trunc(ngay_qtoan) <=  trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE - 1)) AND trang_thai = '01'"+ strKB  +  
                    " UNION ALL " +
//ManhNV rao20150311
//                    " SELECT   0 AS cho_duyet, COUNT (0) AS qtoan, 0 as qtoan_tdong FROM   sp_066 " + 
//                    " WHERE  trunc(ngay_qtoan) <=  trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE - 1))" + strKB+  
//                    " AND id NOT IN (SELECT  mt_refid FROM sp_quyet_toan)" +
//                    " union all " + 
                    " SELECT  0 as  cho_duyet, 0 AS qtoan, count(0) as qtoan_tdong " + 
                    "  FROM  sp_066 " + 
                    " WHERE  trunc(ngay_qtoan) <=  trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE - 1))" + strKB+  
                    " AND trang_thai = '01' and loai_qtoan ='01')" ;
            DNQTVO phtVO = (DNQTVO)findByPK(strSQL.toString(), params, strValueObjectDNQTVO, conn);
            return phtVO;
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObject + ".getKQPHT(): " + ex.getMessage(), ex);
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
    
    public Collection getXNDCTHop(String strNHKB, String strMaKB,String tu_ngay,
                                  Vector vParam) throws Exception {
  
        Collection reval = null;
        try {
  
            String strSQL = "";
  
            strSQL +=
                    "SELECT   b.ma_nh receive_bank, b.ten, TO_CHAR (b.ngay, 'DD/MM/YYYY') ngay_dc," +
                    " a.id ttsp_id, a.tthai_dxn_thop, a.ket_qua_dxn_thop, a.tthai_ttin_thop, a.ngay_thuc_hien_dc, a.trang_thai, b.ma_nt loai_tien, " +
                    " (SELECT MAX (id) FROM sp_065_ngoai_te WHERE 1 = 1 AND receive_bank = b.ma_nh" +
                    " AND ngay_dc = b.ngay AND loai_tien = b.ma_nt AND app_type = 'PHT' AND send_bank = '" + strNHKB +"') pht_id" +
                    " FROM   (SELECT  e.ngay_dc, e.trang_thai, e.id, e.tthai_dxn_thop, e.tthai_ttin_thop, e.receive_bank, " +
                    " e.ngay_thuc_hien_dc, e.ket_qua_dxn_thop, e.loai_tien FROM sp_065_ngoai_te e" +
                    " WHERE 1 = 1 AND e.id IN (SELECT MAX (id) FROM sp_065_ngoai_te" +
                    " WHERE app_type = 'TTSP' AND send_bank = '" + strNHKB +
                    "' GROUP BY loai_tien,ngay_dc, receive_bank)) a,(SELECT   DISTINCT TRUNC (ngay_gd+1) ngay, d3.ma_nh ma_nh, d3.ten ten, d4.ma_nt" +
                    " FROM   sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_ngan_hang d3, sp_tknh_kb d4, sp_dm_htkb d5, sp_dm_manh_shkb d6 " +
                    " WHERE  d1.ma_nh_kb = d2.ma_nh AND d1.ma_nh = d3.ma_nh AND d4.nh_id = d3.id AND d4.kb_id = d5.id " + 
                    " AND d5.ma = d6.shkb AND d6.ma_nh = d2.ma_nh " +
                    " AND d1.ma_nh_kb = '" + strNHKB + "' AND d4.quyet_toan = 'Y' AND d4.loai_tk <> '01' AND d4.ma_nt <> 'VND' " +
                    " AND sp_util_pkg.fnc_get_ngay_trien_khai(d2.ma_nh, d3.ma_nh ,d4.ma_nt) <= d1.ngay_gd " +
                    " AND TRUNC (d1.ngay_gd+1) <= TRUNC (SYSDATE)" + tu_ngay + 
                    " AND  TO_CHAR (ngay_gd+1, 'YYYYMMDD') NOT IN" +
                    " ( SELECT   ngay FROM sp_ngay_nghi) ORDER BY   ngay) b" +
                    " WHERE   a.ngay_dc(+) = b.ngay AND a.receive_bank(+) = b.ma_nh AND a.loai_tien(+) = b.ma_nt AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE)" +
                    " AND ( ((a.tthai_dxn_thop = '00' or a.tthai_dxn_thop ='01')   or (SELECT   COUNT (0)" +
                    " FROM   sp_066 WHERE   nhkb_nhan = a.receive_bank AND trang_thai = '01' AND kq_dc_ttsp IN(  SELECT   MAX (id)" +
                    " FROM   sp_065_ngoai_te WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE) and ngay_dc =b.ngay " +
                    " AND app_type = 'TTSP' AND receive_bank = a.receive_bank" +
                    " AND send_bank = '" + strNHKB + "' )) >0) AND a.id IN" +
                    " ( SELECT   MAX (id) FROM   sp_065_ngoai_te WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE) and ngay_dc =b.ngay " +
                    " AND app_type = 'TTSP' and receive_bank=a.receive_bank AND send_bank = '" +
                    strNHKB + "' GROUP BY   loai_tien,ngay_dc, send_bank ))" +
                    " OR a.trang_thai IS NULL)  OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.id IN" +
                    " (  SELECT   MAX (id) FROM   sp_065_ngoai_te WHERE   TO_DATE (ngay_dc) = TO_DATE (SYSDATE)" +
                    " AND app_type = 'TTSP' and receive_bank=a.receive_bank AND send_bank = '" +
                    strNHKB + "'  GROUP BY   loai_tien,ngay_dc, send_bank))" +
                    " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.tthai_dxn_thop <> '00')" +
                    " OR (TRUNC (a.ngay_thuc_hien_dc) = TRUNC (SYSDATE)))" +
                    " ORDER BY a.tthai_dxn_thop, b.ma_nh, ngay , loai_tien desc";
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNDCVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectXNDCVO + ".getXNDCTHop(): " + ex.getMessage(), ex);
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
                    " a.mon_thu_dtu_kbnn, a.mon_chi_dtu_kbnn, a.tien_thu_dtu_kbnn, a.tien_chi_dtu_kbnn, " +
                    " a.mon_thu_tcong_kbnn, a.mon_chi_tcong_kbnn, a.tien_thu_tcong_kbnn, a.tien_chi_tcong_kbnn," +
                    " a.so_du_dau_ngay, a.ket_chuyen_chi, a.ket_chuyen_thu,a.tong_ps_pht,a.tong_mon_pht," +
                    " c.sotien_thu, c.sotien_chi, c.sotien_dtu, c.sotien_tcong," +
                    " c.somon_thu, c.somon_chi, c.somon_dtu, c.somon_tcong," +
                    " c.so_mon_thu_tcong, c.so_mon_thu_dtu, c.so_ketchuyen, c.sodu_daungay," +
                    " c.so_du, c.trang_thai trang_thai_bkdc, c.so_tien_thu_tcong, c.ps_chi," +
                    " c.so_tien_thu_dtu " +
                    " FROM sp_065_ngoai_te a, sp_bk_dc1_ngoai_te c where 1=1 and a.id in (" +
                    strbke_id + " )" +
                    " and a.bk_id=c.mt_id(+) AND a.loai_tien = c.loai_tien(+) ORDER BY a.app_type desc";
  
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNKQDCDataVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectXNKQDCDataVO + ".getXNKQData(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getXNTHData(String strbke_id, Vector vParam) throws Exception {
  
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT distinct a.id, a.sodu_kbnn, a.chenh_lech, a.tien_thu_dtu_kbnn," +
                    " a.tien_thu_tcong_kbnn,a.tien_chi_tcong_kbnn, a.so_du_dau_ngay,  a.ket_chuyen_chi," +
                    " c.sodu_daungay, a.ket_chuyen_thu, c.sotien_thu, c.sotien_chi," +
                    " c.sotien_dtu, c.sotien_tcong, c.so_ketchuyen, c.so_du," +
                    " c.so_tien_thu_tcong, c.ps_chi, c.so_tien_thu_dtu" +
                    " FROM  sp_065_ngoai_te a, sp_bk_dc1_ngoai_te c" +
                    " WHERE 1 = 1 AND c.id (+) = a.bk_id  AND a.id IN(" + strbke_id + ")";
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNKQDCDataVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectXNKQDCDataVO + ".getXNKQData(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getData066(String strWhere, Vector vParam) throws Exception {
  
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT  sp_util_pkg.fnc_lay_ndung_ky(a.id,'066','Y') ndung_ky_066, a.id, a.nguoi_tao, a.ngay_tao, a.nguoi_ks, a.ngay_ks," +
                    " to_char(a.ngay_qtoan,'DD-MM-YYYY') ngay_qtoan, a.loai_qtoan, a.qtoan_thu, a.qtoan_chi,kq_dxn_thop," +
                    " a.ndung_qtoan, a.kq_dc_ttsp, a.kq_dc_pht, a.msg_id," +
                    " a.trang_thai,a.loai_tien, a.error_code, a.error_desc " +
                    " FROM  sp_066 a WHERE 1 = 1 " + strWhere +"order by a.id desc ";
  
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNKQDCDataVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectXNKQDCDataVO + ".getData066(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getSoTCong(String strWhere, Vector vParam) throws Exception {
  
        Collection reval = null;
        try {
            String strSQL = "";
//            strSQL +=
//                    " SELECT   a.ma_kb, a.ma_nh, a.ngay_gd,  sum(nvl(a.so_thu,0)) so_thu, sum(nvl(a.so_chi,0)) so_chi," +
//                    " vietnamesenumbertowords.fnc_doc_tien (SUM (NVL (a.so_thu, 0)), a.loai_tien) so_thu_chu," + 
//                    " vietnamesenumbertowords.fnc_doc_tien (SUM (NVL (a.so_chi, 0)), a.loai_tien) so_chi_chu" + 
//                    "  FROM sp_gd_thu_cong a WHERE 1=1 " +strWhere + " group by a.ma_kb, a.ma_nh, a.ngay_gd, a.loai_tien ";
            
            //20171009 thuongdt sua sql bo sung tra cuu so lai chuyen thu
           strSQL +="select  a.ma_kb, a.ma_nh, a.ngay_gd,  so_thu, so_chi, so_thu_chu, so_chi_chu,nvl(b.lai_chuyen_thu,0)lai_chuyen_thu,b.lai_chuyen_thu_chu   " +
            "from ( " + 
           " SELECT	 a.ma_kb, a.ma_nh, a.ngay_gd,  sum(nvl(a.so_thu,0)) so_thu, sum(nvl(a.so_chi,0)) so_chi, " + 
           " vietnamesenumbertowords.CONVERT (SUM (NVL (a.so_thu, 0))) so_thu_chu, " + 
           " vietnamesenumbertowords.CONVERT (SUM (NVL (a.so_chi, 0))) so_chi_chu " + 
           " FROM	sp_gd_thu_cong a WHERE a.NSD_ID is not null "+strWhere+     
           " group by a.ma_kb, a.ma_nh, a.ngay_gd)a, " + 
           " (SELECT	  a.ma_kb, a.ma_nh, a.ngay_gd,sum(nvl(a.so_thu,0)) lai_chuyen_thu,vietnamesenumbertowords.CONVERT (SUM (NVL (a.so_thu, 0))) lai_chuyen_thu_chu  " + 
           " FROM	sp_gd_thu_cong a WHERE a.NSD_ID is null "+strWhere+
           " group by a.ma_kb, a.ma_nh, a.ngay_gd)b " + 
           " where a.ma_kb = b.ma_kb(+) and a.ma_nh = b.ma_nh(+)"; 
            reval = executeSelectStatement(strSQL.toString(), vParam, strGDTCongVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strGDTCongVO + ".getSoTCong(): " + ex.getMessage(), ex);
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
        strSQL = "select tong_mon_pht,tong_ps_pht,mon_thu_dtu_kbnn,tien_thu_dtu_kbnn from sp_065_ngoai_te " +
            " where send_bank='"+ma_KB+"'and receive_bank='"+ma_nh+"' " +
            " and ngay_dc > sp_util_pkg.fnc_get_ngay_lam_viec_truoc(to_date('"+ngay_ps+"','dd/MM/yyyy')-1) " + 
            " and ngay_dc<to_Date('"+ngay_ps+"','dd/mm/yyyy')";       
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
    
    public Collection chkSoDu(String strWhere, Vector vParam) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "SELECT a.id, a.so_du_cot FROM sp_so_du a WHERE 1=1 " + strWhere;
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectDNQTVO, this.conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectDNQTVO + ".getTso_KB(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getNgayBDauTheoSoDu(String whereClause,
                                  Vector params) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    "SELECT TRUNC (max(a.ngay_gd+1)) ngay FROM  sp_so_du a WHERE 1 = 1" +
                    whereClause;
            reval = executeSelectStatement(strSQL.toString(), params, strValueObjectVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObject + ".getNgayBDauTheoSoDu(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    public boolean checkDonViMoiTK(String strMaKB, String strMaNH, String strLoaiTien, String strNgayDC) throws Exception {
        Collection reval = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            String strSQL = "SELECT COUNT(0) FROM DUAL WHERE "+
                            "sp_util_pkg.fnc_get_ngay_lam_viec_truoc(TO_DATE ('"+strNgayDC+"', 'DD/MM/YYYY')-1) = "+
                            "sp_util_pkg.fnc_get_ngay_trien_khai('"+strMaKB+"', '"+strMaNH+"', '"+strLoaiTien+"')";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strSQL);
            if(rs.next()){
                if(rs.getInt(1) > 0){
                  return true;
                }
            }            
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObject + ".checkDonViMoiTK(): " + ex.getMessage(), ex);
            throw daoEx;
        }finally{
            try { if (rs != null) rs.close(); } catch (Exception e) {};
            try { if (stmt != null) stmt.close(); } catch (Exception e) {};
        }
        return false;
    }
    public int updateTThaiTHop(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_065_ngoai_te set  tthai_dxn_thop = ?");
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
    
    public XNKQDCDataVO getSoDuCOT(String strSoDu, Vector params) throws Exception {
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT a.ma_kb, a.ma_nh, a.so_du_cot," + 
                    " b.so_tien so_chi, c.so_tien so_thu," + 
                    " nvl((a.so_du_cot + NVL (c.so_tien, 0) - NVL (b.so_tien, 0)),0) chksodu" + 
                    "  FROM sp_so_du a, (SELECT  ma_nt,loai_qtoan, ngay_qtoan, sum(nvl(so_tien,0)) so_tien,ma_nh_chuyen,ma_kb" + 
                    " FROM   sp_quyet_toan WHERE   qtoan_dvi = 'Y' AND lai_phi in ('01','03') " +
                    " and ngay_qtoan like sp_util_pkg.fnc_get_ngay_lam_viec_truoc(sysdate-1) and loai_qtoan='D' group by ma_nt,loai_qtoan, ngay_qtoan, ma_nh_chuyen,ma_kb) b," + 
                    " (SELECT  ma_nt,loai_qtoan, ngay_qtoan, sum(nvl(so_tien,0)) so_tien,ma_nh_chuyen,ma_kb" + 
                    " FROM   sp_quyet_toan WHERE   qtoan_dvi = 'Y' AND lai_phi in ('01','03') " +
                    " and ngay_qtoan like sp_util_pkg.fnc_get_ngay_lam_viec_truoc(sysdate-1) and loai_qtoan='C' group by ma_nt,loai_qtoan, ngay_qtoan, ma_nh_chuyen,ma_kb) c" + 
                    " WHERE    a.ma_kb = b.ma_kb(+)" + 
                    " AND a.ma_nh = b.ma_nh_chuyen(+) AND a.ma_kb = c.ma_kb(+) AND a.ma_nh = c.ma_nh_chuyen(+) AND a.loai_tien = c.ma_nt(+) AND a.loai_tien = b.ma_nt(+) AND ngay_gd like sp_util_pkg.fnc_get_ngay_lam_viec_truoc(sysdate-1)" + strSoDu;
            XNKQDCDataVO phtVO = (XNKQDCDataVO)findByPK(strSQL.toString(), params, strValueObjectXNKQDCDataVO, conn);
            return phtVO;
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectXNKQDCDataVO + ".getSoDuCOT(): " + ex.getMessage(), ex);
            throw daoEx;
        }
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
        v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_qtoan(), "dd/MM/yyyy"), true));
  
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
        
        strSQL.append(", loai_tien");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getLoai_tien(), true));
        
        strSQL.append(", kq_dxn_thop");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getKq_dxn_thop(), true));
  
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
        v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_dc(),"dd/MM/yyyy"),true));
  
        strSQL.append(",  so_thu");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getSo_thu(), true));
        
        strSQL.append(",  so_chi");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getSo_chi(), true));
        strSQL.append(",  loai_tien");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getLoai_tien(), true));
  
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
    
    public Collection getTso_KB(String strWhere, Vector vParam) throws Exception {
        Collection reval = null;
        try {
            String strSQL =
                "SELECT a.ten_ts, a.giatri_ts, a.cho_phep_sua, a.kb_id FROM sp_thamso_kb a WHERE 1=1 " +
                strWhere;
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectDNQTVO, this.conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectDNQTVO + ".getTso_KB(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public DuyetKQDCVO getTSoDC1(String ttsp_id, Vector vparam) throws Exception {
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT  to_char(a.ngay_dc,'DD-MM-YYYY') ngay_dc, a.receive_bank, a.send_bank, a.ket_qua, (SELECT   c2.ten" +
                    " FROM   sp_dm_htkb c1, sp_dm_htkb c2, sp_dm_manh_shkb c3 " +
                    " WHERE  c3.ma_nh = a.send_bank AND c2.ma = c1.ma_cha AND c3.shkb = c1.ma)" +
                    " ten_tinh, b.ten ten_huyen," +
                    " c.ten ten_nhang FROM  sp_065_ngoai_te a, sp_dm_ngan_hang b, sp_dm_ngan_hang c" +
                    " WHERE a.id = '" + ttsp_id +
                    "' AND a.send_bank = b.ma_nh" +
                    "  AND a.receive_bank = c.ma_nh";
  
            DuyetKQDCVO phtVO = (DuyetKQDCVO)findByPK(strSQL.toString(), null, strValueObjectXNDCVO, conn);
            return phtVO;
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectXNDCVO + ".getTSoDC1(): " + ex.getMessage(), ex);
            throw daoEx;
        }
    }
    
    public Collection getListXNDChieu_066(String strWhere,String tu_ngay, Vector vParam) throws Exception {
  
        Collection reval = null;
        try {
            String strSQL = "";
            //20171121 thuongdt bo sung them ten ngan hang: b.ten
            strSQL +=
                    "SELECT distinct  a.id, a.bk_id,b.ten, TO_CHAR (b.ngay, 'DD/MM/YYYY') ngay_dc," +
                    "     TO_CHAR (a.ngay_thuc_hien_dc, 'DD/MM/YYYY') ngay_thuc_hien_dc," +
                    "     a.ket_qua, a.lan_dc, a.send_bank, a.trang_thai, a.tthai_dxn_thop," +
                    "     b.ma_nh receive_bank, a.ket_qua_dxn_thop, a.tthai_ttin," +
                    "     a.tthai_ttin_thop, b.ma_nt loai_tien" +
                    "  FROM (SELECT  e.ngay_dc, e.trang_thai, e.id, e.tthai_dxn_thop," +
                    " e.tthai_ttin_thop, e.receive_bank, e.ngay_thuc_hien_dc," +
                    " e.tthai_ttin, e.send_bank, e.lan_dc, e.ket_qua, e.bk_id, e.loai_tien," +
                    "  e.ket_qua_dxn_thop FROM   sp_065_ngoai_te e WHERE" +
                    " id in ( select max(id) from sp_065_ngoai_te WHERE  app_type = 'TTSP' AND send_bank = '"+strWhere+"' group by loai_tien,ngay_dc, receive_bank, send_bank)) a," +
                    " ( SELECT  DISTINCT TRUNC (ngay_gd + 1) ngay," +
                    " d3.ma_nh ma_nh, d3.ten ten ,d4.ma_nt" +
                    " FROM  sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_ngan_hang d3, sp_tknh_kb d4, sp_dm_htkb d5, sp_dm_manh_shkb d6" +
                    " WHERE  d1.ma_nh = d3.ma_nh AND d4.nh_id = d3.id AND d4.kb_id = d5.id AND d5.ma = d6.shkb AND d6.ma_nh = d2.ma_nh AND d4.ma_nt <> 'VND' AND d4.quyet_toan = 'Y'" +
                    " AND sp_util_pkg.fnc_get_ngay_trien_khai(d2.ma_nh, d3.ma_nh ,d4.ma_nt) <= d1.ngay_gd AND d1.ma_nh_kb = '" + strWhere + "'" +
                    " AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" + tu_ngay +
                    " AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN (  SELECT  ngay FROM sp_ngay_nghi)" +
                     " ORDER BY ngay) b, sp_066 c " +
                    " WHERE a.id = c.kq_dc_ttsp(+) and a.ngay_dc(+) = b.ngay AND a.loai_tien(+) = b.ma_nt AND a.receive_bank(+) = b.ma_nh  AND (tthai_dxn_thop = '01' or a.tthai_dxn_thop is null OR (a.tthai_dxn_thop='00' AND c.trang_thai is not null) OR ( (a.tthai_dxn_thop = '02' or a.tthai_dxn_thop = '03'  or c.trang_thai='03')" +
                    " AND TO_DATE (ngay_thuc_hien_dc) = TO_DATE (SYSDATE)) OR (a.tthai_dxn_thop = '02' and c.trang_thai='01')) ORDER BY TO_CHAR (b.ngay, 'DD/MM/YYYY'),loai_tien DESC";
  
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNDCVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectXNDCVO + ".getListXNDChieu(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public int DuyetDCTHop(KQDChieu1VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_065_ngoai_te set ngay_thuc_hien_dc= sysdate, verified_date= sysdate ");
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
        strSQL.append(", ngay_ks = sysdate ");
        if (vo.getChu_ky() != null && "" != vo.getChu_ky()) {
            strSQL.append(", chu_ky = ? ");
            v_param.add(new Parameter(vo.getChu_ky(), true));
        }
        strSQL.append(" where id = ? ");
        v_param.add(new Parameter(vo.getId(), true));
        exc = executeStatement(strSQL.toString(), v_param, conn);
  
        return exc;
    }
    
    public KQDChieu1VO getKQDCNgoaiTe(String strWhere, Vector vParam) throws Exception {
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
                    "  FROM sp_065_ngoai_te a, sp_nsd b where a.creator = b.id " +
                    strWhere;
  
            KQDChieu1VO phtVO = (KQDChieu1VO)findByPK(strSQL.toString(), vParam, strValueObjectKQVO, conn);
            return phtVO;
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObject + ".getKQDC1(): " + ex.getMessage(), ex);
            throw daoEx;
        }
    }
    
    public Collection getKQDChieuCtiet(String strWhere, Vector vParam) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    "SELECT a.id, a.bkq_id, a.bk_id, a.mt_id, to_char(a.send_date,'DD-MM-YYYY HH24:mi:ss') send_date, a.f20, a.f21," +
                    " a.f32as3, to_char(a.ngay_ts,'DD-MM-YYYY') ngay_ts, a.ghi_chu, a.mt_type, a.app_type," +
                    " a.sai_yeu_to, a.trang_thai, to_char(a.insert_date,'DD-MM-YYYY HH24:mi:ss') insert_date, to_char(a.ngay_ct,'DD-MM-YYYY') ngay_ct" +
                    " FROM sp_065_dtl_ngoai_te a Where 1=1 " + strWhere;
  
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectKQCTVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectKQVO + ".getKQDCChiTiet(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public DChieu1VO getCap(String strWhere, Vector vParam) throws Exception {
        DChieu1VO dmucvo = null;
        try {
            String strSQL = "select cap from sp_dm_htkb where 1=1 ";
            strSQL += strWhere;
            dmucvo = (DChieu1VO)findByPK(strSQL.toString(), vParam, strValueObjectVO, conn);
        } catch (Exception e) {
            throw e;
        }
        return dmucvo;
    }
    
    public Collection getDMucKB_cha(String strWhere, Vector vParam) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT   DISTINCT  a.id_cha, c.ten kb_tinh" + " FROM   sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c" +
                    " WHERE   1 = 1 AND a.id = b.kb_id AND a.id_cha = c.id ";
            strSQL += strWhere + " order by ltrim(REPLACE(c.ten,'KBNN',''))";
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO, this.conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectVO + ".getDMucKB_cha(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
  //HungBM: Them ham(getTCuuDChieu_nangcap_ptrang) nang cap chuc nang tra cuu trang thai doi chieu cua don vi (16) duoc goi tu TCuuTTinDChieuAction.java view
  //THem tieu chi tra cuu theo trang thai quyet toan
  //20161212
  public Collection getTCuuDChieu_nangcap_ptrang(String strWhere, String strDC3,
                                         Vector vParam, Integer page,
                                         Integer count,
                                         Integer[] totalCount,String trang_thai_tk) throws Exception {
    try {
        String strTTTK = "";
        if(trang_thai_tk == null || !"".equals(trang_thai_tk)){
         strTTTK = " AND c.trang_thai_tk = '"+trang_thai_tk+"' ";
        }
      //HungBM 20170220 --------------------BEGIN-----------------------
      //20170320 - Pham vi doi chieu quyet dinh boi lan doi chieu: 1-DonVi, 3-ToanQuoc 
        String strSQL =
            " SELECT   bk_id, kq_id, ma_nt, substr(lan_dc,1,1) pham_vi_doi_chieu, lan_dc, ma_nh, send_bank, receive_bank, ma, nhkb_tinh," +
            "       nhkb_huyen, ten, tthai_dxn_thop, ngay_dc, ngay_thien_dc," +
            "       trang_thai_bk, ket_qua, trang_thai_kq, loai_dc, ket_qua_dxn_thop," +
            "       ngay_order,quyet_toan,trang_thai_tk,tt_dc_tu_dong" +
            "  FROM   (SELECT DISTINCT a.id bk_id, b.id kq_id, c.ma_nt ma_nt, a.lan_dc, c.ma_nh," +
      //HungBM 20170220 --------------------END-----------------------
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
            "                   b.ket_qua_dxn_thop,c.ngay_order,c.quyet_toan, c.trang_thai_tk, b.tt_dc_tu_dong" +
            "         FROM  sp_bk_dc1_ngoai_te a, sp_065_ngoai_te b," +
            "             (SELECT  DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
            "                          d5.id id_kb_tinh, d4.id id_kb_huyen," +
            "                          d4.ma,d5.id_cha," +
            "                          d1.ma_nh ma_nh," +
            "                          d3.ma_nh ma_kb," +
            "                          d2.ten ten_ngan_hang," +
            "                          d4.ten ten_kb_huyen, d5.ten" +
            "                            ten_kb_tinh, d6.MA_NT, d6.quyet_toan, d6.trang_thai trang_thai_tk" +
            "               FROM   sp_tso_cutoftime d1," +
            "                    sp_dm_ngan_hang d2," +
            "                    sp_dm_manh_shkb d3," +
            "                    sp_dm_htkb d4," + "                     sp_dm_htkb d5, sp_tknh_kb d6" +
            "               WHERE     d1.ma_nh_kb = d3.ma_nh" +
            "                    AND d3.shkb = d4.ma" +
            "                    AND d5.id = d4.id_cha" +
            "                    AND d4.id = d6.kb_id" +
            "                    AND d2.id = d6.nh_id" +
            "                    AND d1.ma_nh = d2.ma_nh" +
            "                    AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE) " +
            //20171204 thuongdt bo gioi han ngay gdich, kiem soat lai tu ngay den ngay phai co 
            //"                     AND d1.ngay_gd > sysdate -10" +
            "                    AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
            "                          (SELECT   ngay FROM sp_ngay_nghi)) c" +
            "        WHERE     a.mt_id = b.bk_id(+)" +
            "             AND a.ngay_dc(+) = c.ngay" +
            "             AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb AND a.loai_tien = c.ma_nt AND b.loai_tien = c.ma_nt " + strTTTK +
            strWhere;
        if (strDC3 != null && !"".equals(strDC3)) {
            strSQL += strDC3;
        }
        strSQL += ") ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC, kq_id desc, lan_dc DESC, send_bank ASC";
        return executeSelectWithPaging(conn, strSQL.toString(), vParam, strValueObjectKQVO, page, count, totalCount);
    } catch (Exception e) {
        throw e;
    }
  }
    
    public Collection getTCuuDChieu_ptrang(String strWhere, String strDC3,
                                           Vector vParam, Integer page,
                                           Integer count,
                                           Integer[] totalCount) throws Exception {
        try {
            String strSQL =
                " SELECT   bk_id, kq_id, loai_tien, lan_dc, ma_nh, send_bank, receive_bank, ma, nhkb_tinh," +
                "       nhkb_huyen, ten, tthai_dxn_thop, ngay_dc, ngay_thien_dc," +
                "       trang_thai_bk, ket_qua, trang_thai_kq, loai_dc, ket_qua_dxn_thop," +
                "       ngay_order,quyet_toan" +
                "  FROM   (SELECT DISTINCT a.id bk_id, b.id kq_id, c.ma_nt loai_tien, a.lan_dc, c.ma_nh," +
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
                "                   b.ket_qua_dxn_thop,c.ngay_order,c.quyet_toan" +
                "         FROM  sp_bk_dc1_ngoai_te a, sp_065_ngoai_te b," +
                "             (SELECT  DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
                "                          d5.id id_kb_tinh, d4.id id_kb_huyen," +
                "                          d4.ma,d5.id_cha," +
                "                          d1.ma_nh ma_nh," +
                "                          d3.ma_nh ma_kb," +
                "                          d2.ten ten_ngan_hang," +
                "                          d4.ten ten_kb_huyen, d5.ten" +
                "                            ten_kb_tinh, d6.MA_NT, d6.quyet_toan" +
                "               FROM   sp_tso_cutoftime d1," +
                "                    sp_dm_ngan_hang d2," +
                "                    sp_dm_manh_shkb d3," +
                "                    sp_dm_htkb d4," + "                     sp_dm_htkb d5, sp_tknh_kb d6" +
                "               WHERE     d1.ma_nh_kb = d3.ma_nh" +
                "                    AND d3.shkb = d4.ma" +
                "                    AND d5.id = d4.id_cha" +
                "                    AND d4.id = d6.kb_id" +
                "                    AND d2.id = d6.nh_id" +
                "                    AND d1.ma_nh = d2.ma_nh" +
                "                    AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
                "                    AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
                "                          (SELECT   ngay FROM sp_ngay_nghi)) c" +
                "        WHERE     a.mt_id = b.bk_id(+)" +
                "             AND a.ngay_dc(+) = c.ngay" +
                "             AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb AND a.loai_tien = c.ma_nt AND b.loai_tien = c.ma_nt " + 
                strWhere;
            if (strDC3 != null && !"".equals(strDC3)) {
                strSQL += strDC3;
            }
            strSQL += ") ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC, kq_id desc, lan_dc DESC, send_bank ASC";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam, strValueObjectKQVO, page, count, totalCount);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection getDMucKB_huyen(String strWhere, Vector vParam) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT distinct  a.ma,c.ten kb_tinh, a.ten kb_huyen, a.id,a.ma_cha, a.id_cha, a.cap,d.ma_nh" +
                    " FROM   sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c,sp_dm_manh_shkb d " +
                    " WHERE   1 = 1  and a.id=b.kb_id and a.id_cha=c.id and d.shkb = a.ma ";
            strSQL += strWhere + " order by ltrim(a.ten)";
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO, this.conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectVO + ".getDMucKB_cha(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getTCuu1DChieu_ptrang(String strWhere, Vector vParam, Integer page, Integer count, Integer[] totalCount) throws Exception {
        try {
            String strSQL =
                "SELECT DISTINCT a.id bk_id, b.id kq_id, a.lan_dc, c.ma_nh," +
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
                "                   b.ket_qua_dxn_thop,c.ngay_order" +
                "         FROM  sp_bk_dc1_ngoai_te a, sp_065_ngoai_te b," +
                "             (SELECT  DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
                "                          d5.id id_kb_tinh," +
                "                          d4.id id_kb_huyen," + "                           d4.ma," +
                "                          d5.id_cha," + "                           d1.ma_nh ma_nh," +
                "                          d3.ma_nh ma_kb," +
                "                          d2.ten ten_ngan_hang," +
                "                          d4.ten ten_kb_huyen," + "                           d5.ten" +
                "                            ten_kb_tinh, d6.MA_NT" +
                "               FROM   sp_tso_cutoftime d1," +
                "                    sp_dm_ngan_hang d2," +
                "                    sp_dm_manh_shkb d3," +
                "                    sp_dm_htkb d4," + "                     sp_dm_htkb d5, sp_tknh_kb d6 " +
                "               WHERE     d1.ma_nh_kb = d3.ma_nh" +
                "                    AND d3.shkb = d4.ma" +
                "                    AND d5.id = d4.id_cha" +
                "                    AND d1.ma_nh = d2.ma_nh AND d4.id = d6.kb_id " +
                "                    AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
                "                    AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
                "                          (SELECT   ngay FROM sp_ngay_nghi)) c" +
                "        WHERE     a.id = b.bk_id(+)" +
                "             AND a.ngay_dc(+) = c.ngay" +
                "             AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb AND a.loai_tien(+) = c.ma_nt ";
  
            if (strWhere != null && !"".equals(strWhere)) {
                strSQL += strWhere;
            }
            strSQL += " ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC,kq_id desc, lan_dc DESC, send_bank ASC";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,strValueObjectKQVO, page, count,totalCount);
        } catch (Exception e) {
            throw e;
        }
  
    }
    
    public Collection getTCuu2DChieu_ptrang(String strWhere, Vector vParam, Integer page, Integer count, Integer[] totalCount) throws Exception {
        try {
            String strSQL =
                "SELECT DISTINCT a.bk_id, b.id kq_id, a.lan_dc, c.ma_nh," +
                "                   a.send_bank, a.receive_bank, c.ma," +
                "                   c.ten_kb_tinh nhkb_tinh," +
                "                   c.ten_kb_huyen nhkb_huyen, c.ten_ngan_hang ten," +
                "                   NULL AS tthai_dxn_thop," +
                "                   TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc," +
                "                   TO_CHAR (DECODE (" +
                "                           b.ngay_thien_dc," +
                "                           NULL, a.ngay_thien_dc," +
                "                           b.ngay_thien_dc" +
                "                         ), 'DD/MM/YYYY')" + "                     ngay_thien_dc," +
                "                   DECODE (a.trang_thai, NULL, '98', a.trang_thai)" +
                "                     trang_thai_bk, b.ket_qua," +
                "                   b.trang_thai trang_thai_kq, decode(a.loai_dc,null,'2',a.loai_dc) loai_dc," +
                "                   NULL AS ket_qua_dxn_thop,c.ngay_order" +
                "         FROM  sp_bk_Dc2 a, sp_kq_dc2 b," +
                "             (SELECT  DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
                "                          d5.id id_kb_tinh," +
                "                          d4.id id_kb_huyen," +
                "                          d1.ma_nh ma_nh," + "                          d4.ma," +
                "                          d5.id_cha," + "                           d3.ma_nh ma_kb," +
                "                          d2.ten ten_ngan_hang," +
                "                          d4.ten ten_kb_huyen," + "                           d5.ten" +
                "                            ten_kb_tinh" +
                "               FROM   sp_tso_cutoftime d1," +
                "                    sp_dm_ngan_hang d2," +
                "                    sp_dm_manh_shkb d3," +
                "                    sp_dm_htkb d4," + "                     sp_dm_htkb d5" +
                "               WHERE     d1.ma_nh_kb = d3.ma_nh" +
                "                    AND d3.shkb = d4.ma" +
                "                    AND d5.id = d4.id_cha" +
                "                    AND d1.ma_nh = d2.ma_nh " +
                "                    AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
                "                    AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
                "                          (SELECT   ngay FROM sp_ngay_nghi)) c" +
                "        WHERE     a.bk_id = b.bk_id(+)" +
                "             AND a.ngay_dc(+) = c.ngay" +
                "             AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb ";
  
            if (strWhere != null && !"".equals(strWhere)) {
                strSQL += strWhere;
            }
            strSQL += " ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC,trang_thai_bk, lan_dc DESC, send_bank ASC"; 
            return executeSelectWithPaging(conn, strSQL.toString(), vParam, strValueObjectKQVO, page, count, totalCount);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection getTCuu3DChieu_ptrang(String strWhere, Vector vParam, Integer page, Integer count, Integer[] totalCount) throws Exception {
        try {
            String strSQL =
                "SELECT DISTINCT a.id bk_id, b.id kq_id, a.lan_dc, c.ma_nh, a.send_bank," +
                "           a.receive_bank, c.ma, c.ten_kb_tinh nhkb_tinh," +
                "           c.ten_kb_huyen nhkb_huyen, c.ten_ngan_hang ten," +
                "           NULL AS tthai_dxn_thop," +
                "           TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc," +
                "           TO_CHAR (DECODE (" + "                    b.ngay_thien_dc," +
                "                   NULL, a.ngay_thien_dc," +
                "                   b.ngay_thien_dc" + "                  ), 'DD/MM/YYYY')" +
                "             ngay_thien_dc," +
                "           DECODE (a.trang_thai, NULL, '99', a.trang_thai)" +
                "             trang_thai_bk, b.ket_qua, b.trang_thai trang_thai_kq," +
                "           a.loai_dc, NULL AS ket_qua_dxn_thop,c.ngay_order" +
                "  FROM sp_bk_dc3 a, sp_kq_dc3 b," +
                "     (SELECT  DISTINCT TRUNC (ngay_gd + 1) ngay, TO_CHAR (TRUNC (ngay_gd + 1), 'YYYYMMDD') ngay_order," +
                "                  d5.id id_kb_tinh, d4.ma, " +
                "                  d4.id id_kb_huyen,d5.id_cha," +
                "                  d1.ma_nh ma_nh," + "                  d3.ma_nh ma_kb," +
                "                  d2.ten ten_ngan_hang," +
                "                  d4.ten ten_kb_huyen," +
                "                  d5.ten ten_kb_tinh" +
                "       FROM   sp_tso_cutoftime d1," +
                "            sp_dm_ngan_hang d2," + "            sp_dm_manh_shkb d3," +
                "            sp_dm_htkb d4, sp_dm_htkb d5" +
                "       WHERE     d1.ma_nh_kb = d3.ma_nh" +
                "            AND d3.shkb = d4.ma" + "            AND d5.id = d4.id_cha" +
                "            AND d1.ma_nh = d2.ma_nh" +
                "            AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" +
                "            AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
                "                  (SELECT   ngay FROM sp_ngay_nghi)) c" +
                " WHERE    a.id = b.bk_id(+)" +
                "     AND a.ngay_dc(+) = c.ngay " +
                "     AND a.send_bank(+) = c.ma_nh AND a.receive_bank(+) = c.ma_kb  and c.ma='0003' " +
                strWhere +
                " ORDER BY  ngay_order DESC,nhkb_tinh ASC, nhkb_huyen ASC,kq_id desc, lan_dc DESC, send_bank ASC";
  
            return executeSelectWithPaging(conn, strSQL.toString(), vParam, strValueObjectKQVO, page, count, totalCount);
        } catch (Exception e) {
            throw e;
        }
    }
    
    public Collection getListViewCTietBCao(String strWhere, Vector vParam) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    "  /* Formatted on 13-Oct-2014 9:31:52 (QP5 v5.126) */ " + 
                    "  SELECT   a.id, c.id kq_id ,a.mt_id, " + 
                    "          a.trang_thai, " + 
                    "          c.trang_thai trang_thai_kq, " + 
                    "          c.tthai_ttin, " + 
                    "          a.lan_dc, " + 
                    "          TO_CHAR (a.ngay_dc, 'DD/MM/YYYY') ngay_dc, " + 
                    "          a.receive_bank, " + 
                    "          a.send_bank, " + 
                    "          a.ngay_thuc_hien, " + 
                    "          a.app_type, " + 
                    "          a.lan_dc " + 
                    "    FROM  sp_bk_dc1_ngoai_te a, sp_065_ngoai_te c " + 
                    "   WHERE      a.mt_id = c.bk_id(+) ";
            strSQL += strWhere + "  ORDER BY  to_date (ngay_dc, 'DD/MM/YYYY'), a.send_bank DESC";
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectVO + ".getDChieuList(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public Collection getListView_066(String strWhere, String ma_kb, Vector vParam) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    "SELECT a.id, a.bk_id, TO_CHAR (b.ngay, 'DD/MM/YYYY') ngay_dc," +
                    " TO_CHAR (a.ngay_thuc_hien_dc, 'DD/MM/YYYY') ngay_thuc_hien_dc," +
                    " a.ket_qua, a.lan_dc, a.send_bank, a.trang_thai, a.tthai_dxn_thop," +
                    " b.ma_nh receive_bank, a.ket_qua_dxn_thop, a.tthai_ttin, a.tthai_ttin_thop" +
                    "  FROM (SELECT  e.ngay_dc, e.trang_thai, e.id, e.tthai_dxn_thop," +
                    " e.tthai_ttin_thop, e.receive_bank, e.ngay_thuc_hien_dc," +
                    " e.tthai_ttin, e.send_bank, e.lan_dc, e.ket_qua, e.bk_id, e.ket_qua_dxn_thop" +
                    " FROM   sp_065_ngoai_te e WHERE  e.app_type = 'TTSP' AND e.send_bank = '" +
                    ma_kb + "') a," +
                    " ( SELECT  DISTINCT TRUNC (ngay_gd + 1) ngay, d3.ma_nh ma_nh, d3.ten ten" +
                    " FROM  sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_ngan_hang d3" +
                    "  WHERE d1.ma_nh_kb = d2.ma_nh AND d1.ma_nh = d3.ma_nh AND d1.ma_nh_kb = '" +
                    ma_kb + "'" +
                    " AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE) AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" +
                    " ( SELECT  ngay FROM sp_ngay_nghi) ORDER BY  ngay) b " +
                    " WHERE a.ngay_dc(+) = b.ngay AND a.receive_bank(+) = b.ma_nh ";
  
            strSQL += strWhere;
            reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectXNDCVO, conn);
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectXNDCVO + ".getListXNDChieu(): " + ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }
    
    public DChieu1VO checkSHKB(String ma_nhkb, Vector vparam) throws Exception {
        try {
            String strSQL = "";
            strSQL +=
                    "select decode(instr(giatri_ts,(SELECT a.shkb " + "  FROM sp_dm_manh_shkb a where ma_nh='" +
                    ma_nhkb +
                    "')),'0','CO_PHT','KO_PHT') check_shkb from sp_thamso_ht where id=101 ";
            DChieu1VO dcVO = (DChieu1VO)findByPK(strSQL.toString(), null, strValueObjectVO, conn);
            return dcVO;
        } catch (Exception ex) {
            DAOException daoEx = new DAOException(strValueObjectXNDCVO + ".checkSHKB(): " + ex.getMessage(), ex);
            throw daoEx;
        }
    }

    public int updateInit065(DNQTVO vo,String giatri_ts) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("UPDATE SP_065_NGOAI_TE SET TTHAI_DXN_THOP = '00' ");
        strSQL.append(" WHERE TTHAI_DXN_THOP='01' AND TRUNC(NGAY_DC) <= TRUNC(SYSDATE) AND " +
            " trunc(NGAY_DC) >= trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE - " + giatri_ts + ")) " +
            " AND send_bank='" + vo.getNhkb_chuyen() + "'");
        return executeStatement(strSQL.toString(), new Vector(), conn);
    }
}
