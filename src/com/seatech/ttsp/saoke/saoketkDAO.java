package com.seatech.ttsp.saoke;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.ttsp.quyettoan.QuyetToanDAO;

import com.seatech.ttsp.saoke.form.DChieuSoChiTiet_TinhForm;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


/**
 * @modify: thuongdt
 * @modify-date: 27/06/2017
 * @see: sua count tong so chi tiet da hoan thanh tai ham getResultRow
 * @find: 20170627
 **/
public class saoketkDAO extends AppDAO {
  Connection conn = null;
  private static String CLASS_NAME_DAO =
      "com.seatech.ttsp.saoke.saoketkDAO";
  private static String CLASS_NAME_VO =
      "com.seatech.ttsp.saoke.saoketkVO";

    public saoketkDAO(Connection conn) {
        this.conn = conn;
    }


    public List<saoketkVO> getCTietDChieuSKe(String strWhere,
                                        Vector vParam) throws Exception {

        List<saoketkVO> reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "SELECT a.id, a.ma_nh, a.ma_kb, a.so_tk, a.ngay_dc, a.ket_qua," +
                    " a.ps_co_nh, a.ps_no_nh, a.so_du_nh, a.ps_co_sg_kb, a.ps_no_sg_kb," +
                    " a.ps_co_tg_kb, a.ps_no_tg_kb, a.qtoan_thu, a.qtoan_chi," +
                    " a.so_du_sg, a.so_du_tg, a.so_du_qt, a.trang_thai, a.noi_dung," +
                    " a.ps_pht_sg, a.ps_pht_tg, a.qtoan_chi + a.ps_co_sg_kb + a.ps_co_tg_kb tong_ps_co," + 
                    " a.qtoan_thu + a.ps_no_sg_kb + a.ps_no_tg_kb tong_ps_no, tong_so_du, " +
                    " a.tong_so_du - a.so_du_nh ss_so_du,  a.ps_co_nh - (a.qtoan_chi + a.ps_co_sg_kb + a.ps_co_tg_kb) ss_ps_co," +
                    " a.ps_no_nh - (a.qtoan_thu + a.ps_no_sg_kb + a.ps_no_tg_kb) ss_ps_no, a.ps_co_ttsp_tg, a.ps_co_ttsp_sg, a.ps_no_ttsp_tg,\n" + 
                    " a.ps_no_ttsp_sg, a.ma_nt " +
                    " FROM sp_dc_sao_ke a WHERE 1=1 ";
            strSQL += strWhere;
            reval = (List<saoketkVO>)executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO, this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_VO + ".getCTietDChieuSKe(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }
    
  public Collection getCTietCLech(String strWhere,
                                      Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";

          strSQL +=
                  " SELECT a.dchieu_ske_id, a.ma_nh, a.ma_kb, a.ngay_dc, a.insert_date,  " + 
                  " a.thu_pht_nh, a.chi_pht_nh, a.thu_ttsp_nh, a.chi_ttsp_nh, " + 
                  " a.thu_khac_nh, a.chi_khac_nh, a.thu_pht_kb, a.chi_pht_kb, " + 
                  " a.thu_ttsp_kb, a.chi_ttsp_kb, a.thu_ttsp_khac, a.chi_ttsp_khac, a.so_tk, " +
                  " a.thu_pht_nh - a.thu_pht_kb ss_thu_pht ,  a.thu_ttsp_nh - a.thu_ttsp_kb ss_thu_ttsp, " +
                  " nvl(a.chi_pht_nh,0) - nvl(a.chi_pht_kb,0) ss_chi_pht,  a.chi_ttsp_nh - a.chi_ttsp_kb ss_chi_ttsp," +
                  " a.chi_khac_nh - a.chi_ttsp_khac ss_chi_khac,  a.thu_khac_nh - a.thu_ttsp_khac ss_thu_khac" +
                  " FROM sp_dc_ske_ctiet_clech a WHERE 1=1 ";
          strSQL += strWhere;
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                         this.conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_VO + ".getCTietCLech(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
  
  public List getChenhLech(String whereCondition, Vector params) throws Exception {
      List result = new ArrayList();
      StringBuilder query = new StringBuilder();
      query.append("SELECT id, ma_nh, ma_kb, so_tien_nh, so_tien_kb, so_mon_kb, so_mon_nh, tinh_chat FROM (");
      query.append("select a.id,a.ma_nh,a.ma_kb,to_char(a.so_tien,'999,999,999,999,999') so_tien_nh,a.so_mon so_mon_nh,to_char(b.so_tien,'999,999,999,999,999') so_tien_kb,b.so_mon so_mon_kb, NVL(a.tinh_chat,b.tinh_chat) tinh_chat from" + 
      "(select * from sp_dc_ske_ctiet where trang_thai = 0 and id_ref = ?) a " + 
      "full outer join " + 
      "(select * from sp_dc_ske_ctiet where trang_thai = 1 and id_ref = ?) b " + 
      "ON a.so_tien = b.so_tien and a.tinh_chat = b.tinh_chat " + 
      "where 1 = 1 ");
      if(whereCondition != null && !"".equals(whereCondition)){
          query.append(whereCondition);     
      }
      query.append(" order by a.trang_thai,nvl(a.so_tien,b.so_tien) desc ");
      query.append(" ) where NVL(so_mon_nh,0) <> NVL(so_mon_kb, 0)");
      
      ResultSet rs = executeSelectStatement(query.toString(), params, conn);
      while(rs.next()){
          ArrayList temp = new ArrayList();
          String[] chechLech = {rs.getString("ID"),rs.getString("MA_NH"),rs.getString("MA_KB"),rs.getString("SO_TIEN_NH"),rs.getString("SO_MON_NH"),rs.getString("SO_TIEN_KB"),rs.getString("SO_MON_KB"),rs.getString("TINH_CHAT")};
          result.add(chechLech);
      }
      
      return result;
  }
    

    public int updateDCSK(saoketkVO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_dc_sao_ke set ");

        if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
            strSQL.append(" trang_thai = ?,");
            v_param.add(new Parameter(vo.getTrang_thai(), true));
        }
        if (vo.getKet_qua() != null && !"".equals(vo.getKet_qua())) {
            strSQL.append(" ket_qua = ?,");
            v_param.add(new Parameter(vo.getKet_qua(), true));
        }
        if (vo.getNoi_dung() != null && !"".equals(vo.getNoi_dung())) {
            strSQL.append(" noi_dung = ?,");
            v_param.add(new Parameter(vo.getNoi_dung(), true));
        }

        strSQL.append(" VERIFIED_DATE = SYSDATE where 1=1 ");
        if (vo.getKet_qua() != null && !"".equals(vo.getKet_qua())) {
            strSQL.append(" and id = ? ");
            v_param.add(new Parameter(vo.getId(), true));
        }
        if (vo.getNgay_dc() != null && !"".equals(vo.getNgay_dc())) {
            strSQL.append(" and to_char(ngay_dc,'DD/MM/YYYY')= ? ");
            v_param.add(new Parameter(vo.getNgay_dc(), true));
        }
        if (vo.getMa_kb() != null && !"".equals(vo.getMa_kb())) {
            strSQL.append(" and ma_kb = ? ");
            v_param.add(new Parameter(vo.getMa_kb(), true));
        }
        if (vo.getMa_nh() != null && !"".equals(vo.getMa_nh())) {
            strSQL.append(" and ma_nh = ? ");
            v_param.add(new Parameter(vo.getMa_nh(), true));
        }
        if (vo.getSo_tk() != null && !"".equals(vo.getSo_tk())) {
            strSQL.append(" and so_tk = ? ");
            v_param.add(new Parameter(vo.getSo_tk(), true));
        }

        exc = executeStatement(strSQL.toString(), v_param, conn);

        return exc;
    }

  public Collection getCHKDChieuSKe(String ma_nh, String ma_kb,String ngay_dc, String so_tk,
                                      Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";

          strSQL +=
//                  "select max(abc) abc , nvl(sum(tthai_dxn_thop),0) count_tt_065, nvl(sum(trang_thai),0) count_tt_sk, nvl(sum(trang_thai_trc),0) count_tt_trc from  ( " + 
                  "select max(abc) abc , nvl(sum(tthai_dxn_thop),0) count_tt_065, nvl(sum(trang_thai),0) count_tt_sk, 1 count_tt_trc from  ( " + 
                  " select to_char(sysdate,'DD/MM/YYYY') abc,0 as tthai_dxn_thop, 0 as trang_thai, 0 AS trang_thai_trc from dual " + 
                  " union all " + 
                  " select null,  count(a.tthai_dxn_thop) tthai_dxn_thop, 0 as trang_thai, 0 AS trang_thai_trc " + 
                  " from "+
                    " (select a.tthai_dxn_thop from sp_065 a where a.send_bank ='"+ma_kb+"' and a.receive_bank ='"+ ma_nh +"' and a.ngay_dc = sp_util_pkg.fnc_get_ngay_lam_viec_sau(TO_DATE ('"+ngay_dc+"','DD/MM/YYYY')+1)" + 
                    " and a.app_type='TTSP' and a.tthai_dxn_thop in ('02','03') " + 
                    " AND a.loai_tien in (select ma_nt from sp_tknh_kb a, sp_dm_htkb b, sp_dm_manh_shkb c, sp_dm_ngan_hang d where a.kb_id = b.id and b.ma= c.shkb and a.nh_id = d.id "+
                    " and a.so_tk = '" + so_tk + "' and c.ma_nh = '"+ma_kb+"' and d.ma_nh ='"+ma_nh+"') "+
                    " union all"+
                    " select a.tthai_dxn_thop from sp_065_ngoai_te a where a.send_bank ='"+ma_kb+"' and a.receive_bank ='"+ ma_nh +"' and a.ngay_dc = sp_util_pkg.fnc_get_ngay_lam_viec_sau(TO_DATE ('"+ngay_dc+"','DD/MM/YYYY')+1)" + 
                    " and a.app_type='TTSP' and a.tthai_dxn_thop in ('02','03') " + 
                    " AND a.loai_tien in (select ma_nt from sp_tknh_kb a, sp_dm_htkb b, sp_dm_manh_shkb c, sp_dm_ngan_hang d where a.kb_id = b.id and b.ma= c.shkb and a.nh_id = d.id "+
                    " and a.so_tk = '" + so_tk + "' and c.ma_nh = '"+ma_kb+"' and d.ma_nh ='"+ma_nh+"')) a"+
                  " union all " + 
                  " select null, 0 as tthai_dxn_thop,  count(b.trang_thai) as trang_thai, 0 AS trang_thai_trc " + 
                  " from  sp_dc_sao_ke b where   b.id = ( SELECT max(id) FROM sp_dc_sao_ke WHERE ma_nh='"+ma_nh+"' " + 
                  " and ma_kb='"+ma_kb+"' and to_char(ngay_dc,'DD/MM/YYYY') ='"+ ngay_dc+"' AND trang_thai ='01' AND so_tk = '" + so_tk + "')" +
                  " union all " + 
                  " select null, 0 as tthai_dxn_thop, 0 as trang_thai, count(b.trang_thai) as trang_thai_trc " + 
                  " from  sp_dc_sao_ke b where   b.id = ( SELECT max(id) FROM sp_dc_sao_ke WHERE ma_nh='"+ma_nh+"'" + 
                  " and ma_kb='"+ma_kb+"' and to_char(ngay_dc,'YYYYMMDD') not in (select ngay from sp_ngay_nghi) and  ngay_dc = sp_util_pkg.fnc_get_ngay_lam_viec_truoc(TO_DATE ('"+ngay_dc+"','DD/MM/YYYY')-1) AND trang_thai ='01'  AND so_tk = '" + so_tk + "'))";
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                         this.conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_VO + ".getListTKNHKB(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }

  public Collection getCTietCLech(String ma_nh, String ma_kb,String ngay_dc,
                                      Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";

          strSQL +=
                  "SELECT	NVL (SUM (pht), 0) pht, NVL (SUM (ttsp), 0) ttsp, NVL (SUM (khac), 0) khac" + 
                  "  FROM	(SELECT	 SUM (b.so_tien) AS pht, 0 AS ttsp, 0 AS khac" + 
                  " FROM	 sp_saoke_tk a, sp_saoke_tk_ctiet b  WHERE		  a.id = b.sao_ke_id" + 
                  " AND a.send_bank  ='"+ma_nh+"' AND a.receive_bank  ='"+ma_kb+"' AND to_char(ngay_ks,'DD/MM/YYYY')='"+ngay_dc+"' AND b.app_type = 'PHT'" + 
                  " UNION ALL" + 
                  " SELECT	 0 AS pht, SUM (b.so_tien) AS ttsp, 0 AS khac " + 
                  " FROM	 sp_saoke_tk a, sp_saoke_tk_ctiet b  WHERE		  a.id = b.sao_ke_id" + 
                  " AND a.send_bank = '"+ma_nh+"' AND a.receive_bank  ='"+ma_kb+"' AND to_char(ngay_ks,'DD/MM/YYYY')='"+ngay_dc+"' AND b.app_type = 'TTSP'" + 
                  " UNION ALL" + 
                  " SELECT	 0 AS pht, 0 AS ttsp, SUM (b.so_tien) AS khac" + 
                  " FROM	 sp_saoke_tk a, sp_saoke_tk_ctiet b WHERE		  a.id = b.sao_ke_id" + 
                  " AND a.send_bank = '"+ma_nh+"' AND a.receive_bank  ='"+ma_kb+"' AND to_char(ngay_ks,'DD/MM/YYYY')='"+ngay_dc+"' AND b.app_type IS NULL)";
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                         this.conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_VO + ".getListTKNHKB(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }

public Collection getTCuuDChieuSoChiTiet_ptrang(String whereForResult,
                                              Vector v_param,
                                              int currentPage, int rowOnPage,
                                              Integer[] totalCount) throws DAOException {
        Collection result = null;
        try{
            String sql = "SELECT b.SHKB,a.MA_NH,a.MA_KB, b.TEN ten_kb, c.TEN ten_nh, a.TRANG_THAI,a.SO_TK,to_char(a.NGAY_DC,'DD/MM/YYYY') NGAY_DC,a.NGAY_DC ngay_order,a.ket_qua, a.ma_nt FROM SP_DC_SAO_KE a " + 
            "JOIN SP_DM_MANH_SHKB b " + 
            "ON a.MA_KB = b.MA_NH " + 
            "JOIN SP_DM_NGAN_HANG c " + 
            "ON c.MA_NH = a.MA_NH " + 
            "JOIN SP_DM_HTKB d " + 
            "ON b.SHKB = d.MA " +
            "WHERE 1 = 1 " + whereForResult +
            " ORDER BY ngay_order DESC";
            
            result = executeSelectWithPaging(conn, sql, v_param,
                                       CLASS_NAME_VO, currentPage, rowOnPage,
                                       totalCount);
            
        }catch(Exception e){
            DAOException daoEx = new DAOException(CLASS_NAME_VO + ".getTCuuDChieuSoChiTiet_ptrang(): " + e.getMessage(), e);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return result;
    }
/**
 * @modify: thuongdt
 * @modify-date: 27/06/2017
 * @see: sua count tong so chi tiet da hoan thanh - bo  count(DISTINCT send_bank)
 * @find: 20170627
 **/
    public String getResultRow(List param){
        String result = null;
        try{
            String query = "select (select count(0) from(select count(0) from SP_SAOKE_TK where ngay_du_dau = to_date(?,'DD/MM/YYYY') AND send_bank like ? group by send_bank,receive_bank) ) " + 
            "       || '/' || " + 
            "       (SELECT COUNT(DISTINCT a.ma_nh_kb) " + 
            "        FROM SP_TSO_CUTOFTIME a " + 
            "          JOIN sp_dm_manh_shkb b on a.ma_nh_kb = b.ma_nh " + 
            "          JOIN sp_dm_htkb c on b.shkb = c.ma " + 
            "          JOIN sp_tknh_kb d on d.kb_id = c.id " + 
            "        WHERE d.trang_thai = '01' " + 
            "          AND a.ma_nh LIKE ? " + 
            "          AND a.ngay_gd = to_date(?,'dd/mm/yyyy')) TONG_SO_DON_VI " + 
            "from dual ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, (String)param.get(0));
            ps.setString(2, (String)param.get(1));
            ps.setString(3, (String)param.get(2));
            ps.setString(4, (String)param.get(3));
            ResultSet rs = ps.executeQuery();
            rs.next();
            result = rs.getString("TONG_SO_DON_VI");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public List getListExistSaoKe(Vector vParam) {
        List result = new ArrayList();
        try{
            String query = "select distinct(b.SHKB),b.TEN ten_kb,(select '\u0110\u00E3 nh\u1EADn' from dual) TRANG_THAI " + 
                          "from sp_saoke_tk a " + 
                          "JOIN sp_dm_manh_shkb b " + 
                          "on a.RECEIVE_BANK = b.MA_NH " + 
                          "where a.ngay_du_dau = to_date( ? ,'DD/MM/YYYY') " + 
                          "and a.send_bank like ? " + 
                          "ORDER BY b.SHKB ";
          result = (List)executeSelectStatement(query, vParam, CLASS_NAME_VO,
                                         this.conn);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
    
    public List getListNotExistSaoKe(Vector vParam) {
        List result = new ArrayList();
        try{
            String query = " select distinct(b.SHKB) ,b.ten ten_kb,(select 'Ch\u01B0a nh\u1EADn' from dual) TRANG_THAI " + 
            "from SP_TSO_CUTOFTIME a " + 
            "  JOIN sp_dm_manh_shkb b ON a.ma_nh_kb = b.ma_nh " + 
            "  JOIN sp_dm_htkb c ON b.shkb = c.ma " + 
            "  JOIN sp_tknh_kb d ON d.kb_id = c.id " + 
            "where d.trang_thai = '01' " + 
            "  AND a.ma_nh LIKE ? " + 
            "  AND a.ngay_gd = to_date(?,'dd/mm/yyyy') " + 
            "  and not EXISTS " + 
            "          (select distinct(b.RECEIVE_BANK) " + 
            "          from sp_saoke_tk b " + 
            "           where b.ngay_du_dau = to_date(?,'DD/MM/YYYY') " + 
            "             and b.send_bank like ? " + 
            "             and a.ma_nh_kb = b.receive_bank) " + 
            "ORDER BY b.SHKB ";
          result = (List)executeSelectStatement(query, vParam, CLASS_NAME_VO,
                                         this.conn);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public Map runDoiChieuSoChiTietTinh(DChieuSoChiTiet_TinhForm f,String ma_kb) throws SQLException {
        Map<String,String> result = new HashMap<String,String>();
        CallableStatement cs = null;
        cs = conn.prepareCall("{call sp_dc_sao_ke_pkg.proc_dc_ske_tinh(?,?,?,?)}");
        cs.setString(1, f.getNgay());
        cs.setString(2, ma_kb);
        cs.registerOutParameter(3, java.sql.Types.VARCHAR);
        cs.registerOutParameter(4, java.sql.Types.VARCHAR);
        cs.execute();
        String error = cs.getString(3);
        if("00".equals(error)){
            result.put("success", "ok");
        }else{
            result.put("error",  cs.getString(4));
        }
        return result;
    }
    public Collection getDChieuSaoKeTW(String strWhere, Vector vParam) throws Exception {
  
        Collection result = null;
        StringBuilder query = new StringBuilder(" SELECT a.id, a.ma_nh, a.ma_kb, a.so_tk,b.ten ten_kb,c.ten ten_nh, a.ngay_dc, a.ket_qua, " + 
            "a.ps_co_nh, a.ps_no_nh, a.so_du_nh, " + 
            "a.qtoan_chi + a.ps_co_sg_kb + a.ps_co_tg_kb tong_ps_co, " + 
            "a.qtoan_thu + a.ps_no_sg_kb + a.ps_no_tg_kb tong_ps_no, tong_so_du, " + 
            "a.tong_so_du - a.so_du_nh ss_so_du,  " + 
            "a.ps_co_nh - (a.qtoan_chi + a.ps_co_sg_kb + a.ps_co_tg_kb) ss_ps_co, " + 
            "a.ps_no_nh - (a.qtoan_thu + a.ps_no_sg_kb + a.ps_no_tg_kb) ss_ps_no " + 
            "FROM sp_dc_sao_ke_tw a " + 
            "JOIN sp_dm_manh_shkb b " + 
            "on a.ma_kb = b.ma_nh " + 
            "JOIN sp_dm_ngan_hang c " + 
            "on a.ma_nh = c.ma_nh " + 
            "JOIN sp_dm_htkb d " + 
            "on b.shkb = d.ma " + 
            "WHERE 1=1 ");
        query.append(strWhere);
        result = executeSelectStatement(query.toString(), vParam, CLASS_NAME_VO, this.conn);
        return result;
    }
}
