package com.seatech.ttsp.dchieu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class QLyDChieuDAO extends AppDAO {
  private String strValueObject = "com.seatech.ttsp.dchieu.QLyDChieuDAO";
  private String strValueObjectVO = "com.seatech.ttsp.dchieu.QLyDChieuVO";
  Connection conn = null;

  public QLyDChieuDAO(Connection conn) {
      this.conn = conn;
  }
  public Collection getKQuaTKiem(String strWhere, Vector vParam) throws Exception {
    Collection reval = null;
    try {

        String strSQL = "";
        strSQL+="   SELECT   DISTINCT b.id kq_id, b.ket_qua, a.lan_dc, c.ma_nh send_bank,\n" + 
        " c.ma_kb receive_bank, TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc,\n" + 
        " b.ketchuyen_thu_nh, b.ketchuyen_chi_nh, b.ketchuyen_thu_kb,\n" + 
        " b.ketchuyen_chi_kb, a.trang_thai trang_thai_bk,\n" + 
        " b.trang_thai trang_thai_kq, b.ghi_chu ,a.ghi_chu ghi_chu_bk, b.tthai_dxn_thop,\n" + 
        " b.ket_qua_dxn_thop, b.tthai_ttin, b.tthai_dmo,\n" + 
        " TO_CHAR (b.ngay_thien_dc, 'DD/MM/YYYY HH24:mi:ss')\n" + 
        " ngay_thien_dc, c.ten_ngan_hang, c.ten_kb_huyen, c.ma_nh, c.ten_kb_tinh, c.ngay\n" + 
        "	 FROM   sp_bk_Dc2 a, sp_kq_dc2 b,\n" + 
        " (SELECT	DISTINCT TRUNC (ngay_gd + 1) ngay, d5.id id_kb_tinh, d1.ma_nh ma_nh, d3.ma_nh ma_kb,\n" + 
        " d2.ten ten_ngan_hang, d4.ten ten_kb_huyen, d5.ten ten_kb_tinh\n" + 
        " FROM	sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_manh_shkb d3, sp_dm_htkb d4, sp_dm_htkb d5\n" + 
        " WHERE		 d1.ma_nh_kb = d3.ma_nh AND d3.shkb = d4.ma\n" + 
        " AND d5.id = d4.id_cha AND d1.ma_nh = d2.ma_nh AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)\n" + 
        " AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN (SELECT	 ngay FROM sp_ngay_nghi)) c\n" + 
        "	WHERE 		a.bk_id = b.bk_id(+) " + 
        "			  AND a.ngay_dc(+) = c.ngay " + 
        "			  AND a.send_bank(+) = c.ma_nh " ;
    strSQL += strWhere + " ORDER BY    c.ma_kb asc, c.ma_nh asc, TO_CHAR (c.ngay, 'YYYYMMDD'), a.lan_dc ASC";
    reval =
            executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                   conn);
    } catch (Exception ex) {
    DAOException daoEx =
        new DAOException(strValueObjectVO + ".getKQuaTKiem(): " +
                         ex.getMessage(), ex);
//    daoEx.printStackTrace();
    throw daoEx;
    }
  return reval;
  }
  
  public Collection getKQuaTKiem_PTrang(String strWhere, Vector vParam, Integer page, Integer count,
                                           Integer[] totalCount) throws Exception {
  //    Collection reval = null;
    try {

        String strSQL = "";
        strSQL+="   SELECT   DISTINCT a.bk_id, b.id kq_id, b.ket_qua, a.lan_dc, c.ma_nh send_bank,\n" + 
        " c.ma_kb receive_bank, TO_CHAR (c.ngay, 'DD/MM/YYYY') ngay_dc,\n" + 
        " b.ketchuyen_thu_nh, b.ketchuyen_chi_nh, b.ketchuyen_thu_kb,\n" + 
        " b.ketchuyen_chi_kb, a.trang_thai trang_thai_bk,\n" + 
        " b.trang_thai trang_thai_kq, b.ghi_chu ,a.ghi_chu ghi_chu_bk, b.tthai_dxn_thop,\n" + 
        " b.ket_qua_dxn_thop, b.tthai_ttin, b.tthai_dmo,\n" + 
        " TO_CHAR (b.ngay_thien_dc, 'DD/MM/YYYY HH24:mi:ss')\n" + 
        " ngay_thien_dc, c.ten_ngan_hang, c.ten_kb_huyen, c.ma_nh, c.ten_kb_tinh, c.ngay\n" + 
        "  FROM   sp_bk_Dc2 a, sp_kq_dc2 b,\n" + 
        " (SELECT DISTINCT TRUNC (ngay_gd + 1) ngay, d5.id id_kb_tinh, d1.ma_nh ma_nh, d3.ma_nh ma_kb,\n" + 
        " d2.ten ten_ngan_hang, d4.ten ten_kb_huyen, d5.ten ten_kb_tinh\n" + 
        " FROM  sp_tso_cutoftime d1, sp_dm_ngan_hang d2, sp_dm_manh_shkb d3, sp_dm_htkb d4, sp_dm_htkb d5\n" + 
        " WHERE    d1.ma_nh_kb = d3.ma_nh AND d3.shkb = d4.ma\n" + 
        " AND d5.id = d4.id_cha AND d1.ma_nh = d2.ma_nh AND TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)\n" + 
        " AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN (SELECT   ngay FROM sp_ngay_nghi)) c\n" + 
        " WHERE     a.bk_id = b.bk_id(+) " + 
        "       AND a.ngay_dc(+) = c.ngay " + 
        "       AND a.send_bank(+) = c.ma_nh " ;
    strSQL += strWhere + " ORDER BY    TO_CHAR (c.ngay, 'YYYYMMDD'), c.ma_nh asc,c.ma_kb asc,   a.lan_dc ASC";                      
      return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                     strValueObjectVO, page, count,
                                     totalCount);
    } catch (Exception ex) {
    DAOException daoEx =
        new DAOException(strValueObjectVO + ".getKQuaTKiem(): " +
                         ex.getMessage(), ex);
    
//    daoEx.printStackTrace();
    throw daoEx;
    }
  }
  
  public Collection getKQDChieu1(String strWhere, Vector vParam) throws Exception {
    Collection reval = null;
    try {

        String strSQL = "";
        strSQL+="select tthai_dxn_thop, KET_QUA_DXN_THOP, trang_thai, ket_qua, ket_chuyen_thu, ket_chuyen_chi from sp_065 where id=(" + 
        " SELECT	max(id) FROM	sp_065 WHERE app_type='TTSP'"+ strWhere + ")";
    reval =
            executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                   conn);
    } catch (Exception ex) {
    DAOException daoEx =
        new DAOException(strValueObjectVO + ".getKQDChieu1(): " +
                         ex.getMessage(), ex);
//    daoEx.printStackTrace();
    throw daoEx;
    }
  return reval;
  }
  
  public Collection getKQDChieu2(String strWhere, Vector vParam) throws Exception {
    Collection reval = null;
    try {

        String strSQL = "";
        strSQL+="SELECT	DISTINCT a.trang_thai, b.trang_thai trang_thai_kq, b.ket_qua" + 
        "  FROM	sp_bk_dc2 a, sp_kq_dc2 b " + 
        " WHERE		 a.bk_id=b.bk_id(+)" + strWhere ;
    reval =
            executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                   conn);
    } catch (Exception ex) {
    DAOException daoEx =
        new DAOException(strValueObjectVO + ".getKQDChieu2(): " +
                         ex.getMessage(), ex);
//    daoEx.printStackTrace();
    throw daoEx;
    }
  return reval;
  }
  
  
  public int updateClose(QLyDChieuVO vo) throws Exception {
      int exc = 0;
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      strSQL.append("update SP_KQ_DC2 set tthai_dmo = ? ");

        v_param.add(new Parameter(vo.getTthai_dmo(), true));
       if (vo.getGhi_chu() != null && !"".equals(vo.getGhi_chu())) {
          strSQL.append(", ghi_chu = ? ");
        v_param.add(new Parameter(vo.getGhi_chu(), true));
      }
      strSQL.append(" where id = ? ");
      v_param.add(new Parameter(vo.getKq_id(), true));

      exc = executeStatement(strSQL.toString(), v_param, conn);

      return exc;
  }
  
  public Collection getSLieuTKe(String strWhere, Vector vParam) throws Exception {
    Collection reval = null;
    try {

        String strSQL = "";
        strSQL+="  SELECT   ngay_qt, SUM (dung) khop_dung, SUM (lech) khop_lech , (SUM (tong_nh)-SUM (dung)-SUM (lech)) chua_dc,\n" + 
        " SUM (tong_nh) tong_nh\n" + 
        "	 FROM   (  SELECT   c.ngay ngay_qt, COUNT ( * ) AS dung, 0 AS lech, 0 AS tong_nh" + 
        " FROM   (SELECT	DISTINCT TRUNC (ngay_dc) ngay_dc, bk_id," + 
        " receive_bank, send_bank, ngay_thien_dc, trang_thai, lan_dc" + 
        " FROM	sp_bk_dc2 ) a, sp_kq_Dc2 b," + 
        " (  SELECT   DISTINCT TRUNC (ngay_gd + 1) ngay FROM   sp_tso_cutoftime d1" + 
        " WHERE   TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" + 
        " AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN   SELECT   ngay FROM sp_ngay_nghi)" + 
        " ORDER BY   ngay) c" + 
        " WHERE 		a.ngay_dc(+) = c.ngay AND c.ngay IS NOT NULL" + 
        " AND a.bk_id = b.bk_id(+) AND (b.ket_qua = '0' AND b.trang_thai = '02') " +
         " GROUP BY   c.ngay UNION ALL " + 
        " SELECT   c.ngay ngay_qt, 0 AS dung, COUNT ( * ) AS lech, 0 AS tong_nh" + 
        " FROM   (SELECT	DISTINCT TRUNC (ngay_dc) ngay_dc, bk_id," + 
        " receive_bank, send_bank, ngay_thien_dc, trang_thai, lan_dc" + 
        " FROM	sp_bk_dc2 ) a, sp_kq_Dc2 b," + 
        " (  SELECT   DISTINCT TRUNC (ngay_gd + 1) ngay FROM   sp_tso_cutoftime d1" + 
        "	 WHERE   TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" + 
        " AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN" + 
        " (  SELECT   ngay FROM sp_ngay_nghi) ORDER BY   ngay) c " + 
        " WHERE a.ngay_dc(+) = c.ngay AND c.ngay IS NOT NULL AND a.bk_id = b.bk_id(+)" + 
        " AND (b.ket_qua = '1' AND b.trang_thai = '02') GROUP BY c.ngay" + 
        " UNION ALL " + 
        " SELECT   DISTINCT TRUNC (ngay_gd + 1) ngay_qt, 0 AS dung, 0 AS lech, COUNT (ma_nh) tong_nh" + 
        " FROM   sp_tso_cutoftime d1 WHERE   TRUNC (d1.ngay_gd + 1) <= TRUNC (SYSDATE)" + 
        " AND TO_CHAR (ngay_gd + 1, 'YYYYMMDD') NOT IN (  SELECT   ngay FROM sp_ngay_nghi)" + 
        "				GROUP BY   ngay_gd + 1 ORDER BY   ngay_qt) GROUP BY   ngay_qt ORDER BY   ngay_qt ";
    reval =
            executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                   conn);
    } catch (Exception ex) {
    DAOException daoEx =
        new DAOException(strValueObjectVO + ".getKQDChieu1(): " +
                         ex.getMessage(), ex);
//    daoEx.printStackTrace();
    throw daoEx;
    }
  return reval;
  }

  public int insert_bk(QLyDChieuVO vo) throws Exception {
      int nExc = 0;
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      StringBuffer strSQL2 = new StringBuffer();
      strSQL.append(" Insert into sp_bk_dc2 (id ");
      strSQL2.append(" ) values (SP_BK_DC2_SEQ.nextval ");
      //      v_param.add(new Parameter(, true));
      
      strSQL.append(", bk_id");
      strSQL2.append(", ?");
      v_param.add(new Parameter( StringUtil.DateToString(StringUtil.StringToDate(vo.getNgay_dc(),
                                                                    "dd/MM/yyyy"),
                                            "yyyy/MM/dd").replace("/", ""), true));
      strSQL.append(", lan_dc");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getLan_dc(), true));

      strSQL.append(", send_bank");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getSend_bank(), true));

      strSQL.append(", receive_bank");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getReceive_bank(), true));

      strSQL.append(", created_date");
      strSQL2.append(", sysdate");

      strSQL.append(", insert_date");
      strSQL2.append(", sysdate");
      //    v_param.add(new Parameter(vo.getNhkb_nhan(), true));

      strSQL.append(", trang_thai");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getTrang_thai(), true));
      
      strSQL.append(", ghi_chu");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getGhi_chu(), true));
        
      strSQL.append(", ngay_dc");
      strSQL2.append(", ?");
      v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_dc(),
                                                          "dd/MM/yyyy"),
                                  true));

      strSQL.append(strSQL2);
      strSQL.append(")");

      nExc = executeStatement(strSQL.toString(), v_param, conn);

      return nExc;
  }
  public int delete_bk2(String id) throws Exception {
      Vector params = new Vector();
      StringBuffer sqlbuff = null;
      try {
          sqlbuff = new StringBuffer(" DELETE  sp_bk_dc2 a  WHERE a.bk_id='"+id+"'");
//          params.add(new Parameter(id, true));
          return executeStatement(sqlbuff.toString(), null, conn);
      } catch (Exception ex) {
          conn.rollback();
//          ex.printStackTrace();
          throw new DAOException(strValueObjectVO + ".delete_bk2(): " +
                                 ex.getMessage(), ex);
      }
  }
}
