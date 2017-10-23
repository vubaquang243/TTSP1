package com.seatech.ttsp.tsotabmis;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

/**
 * @modify: thuongdt
 * @modify-date: 16/10/2017
 * @see: sua query dap ung quan ly theo ma NH- KB
 * @find: 20171016
 * */
public class tsoTabmisDAO extends AppDAO {
    public tsoTabmisDAO(Connection conn) {
        this.conn = conn;
    }
    private String strValueObjectVO = "com.seatech.ttsp.tsotabmis.tsoTabmisVO";

    public Collection getLstTSoKB_ptrang(String strWhere, Vector vParam,
                                         Integer page, Integer count,
                                         Integer[] totalCount) throws Exception {

        Collection reval = null;
        String strSQL = "";
        try {
            strSQL +=
                    "SELECT	DISTINCT d5.id id_kb_tinh, d5.id_cha, d4.ma, d4.id id_kb_huyen," +
                    " d1.ma_nh ma_nh, d3.ma_nh ma_kb, d2.ten ten_ngan_hang," +
                    " d4.ten ten_kb_huyen, d5.ten ten_kb_tinh, a.ten_ts," +
                    " a.giatri_ts, a.mo_ta, a.cho_phep_sua, a.kb_id," +
                    " to_char(a.ngay_cap_nhat,'dd/MM/yyyy') ngay_cap_nhat, a.dvi_sua,ma_nsd" +
                    "  FROM	sp_thamso_kb a, sp_tso_cutoftime d1, sp_dm_ngan_hang d2," +
                    " sp_dm_manh_shkb d3, sp_dm_htkb d4, sp_dm_htkb d5" +
                    " WHERE d1.ma_nh_kb = d3.ma_nh" +
                    " AND d3.shkb = d4.ma AND d5.id = d4.id_cha" +
                    " AND d1.ma_nh = d2.ma_nh AND d4.id = a.kb_id" +
                    " AND a.cho_phep_sua='Y' " + strWhere + " and d1.NGAY_GD like sysdate "+
                    " ORDER BY  ltrim(REPLACE(ten_kb_tinh,'KBNN','')),ltrim(REPLACE(ten_kb_huyen,'KBNN','')), a.ten_ts";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           strValueObjectVO, page, count,
                                           totalCount);
        } catch (Exception e) {
            throw e;
        }
    }
  public Collection getLstTSo(Vector vParam) throws Exception {

      Collection reval = null;
      try {

          StringBuffer strSQL = new StringBuffer();

          strSQL.append("select distinct ten_ts from sp_thamso_kb where cho_phep_sua='Y'");
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                         this.conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getLstTSo(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
  
  public Collection getTenTSo(Vector vParam) throws Exception {

      Collection reval = null;
      try {

          StringBuffer strSQL = new StringBuffer();

          strSQL.append("select distinct ten_ts from sp_thamso_kb");
          reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO, this.conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObjectVO + ".getTenTSo(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }

  public int update(tsoTabmisVO vo) throws Exception {
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      strSQL.append("update sp_thamso_kb set ngay_cap_nhat =SYSDATE ");
      int nExc = 0;
      if (vo.getGiatri_ts() != null&& !"".equals(vo.getGiatri_ts())) {
          strSQL.append(", giatri_ts = ? ");
          v_param.add(new Parameter(vo.getGiatri_ts(), true));
      }
      if (vo.getMo_ta() != null&& !"".equals(vo.getMo_ta())) {
          strSQL.append(", mo_ta = ? ");
          v_param.add(new Parameter(vo.getMo_ta(), true));
      }
      if (vo.getMa_nsd() != null&& !"".equals(vo.getMa_nsd())) {
          strSQL.append(", ma_nsd = ? ");
          v_param.add(new Parameter(vo.getMa_nsd(), true));
      }      
      if (vo.getId_kb_tinh() != null && !"".equals(vo.getId_kb_tinh())) {
          strSQL.append(" where kb_id in (SELECT	DISTINCT a.id " + 
          " FROM	sp_dm_htkb a, sp_tknh_kb b, sp_dm_htkb c " + 
          " WHERE	a.id = b.kb_id AND a.id_cha = c.id and c.id=? )");
          v_param.add(new Parameter(vo.getId_kb_tinh(), true));
      }
      strSQL.append(" where 1=1 ");
      
      if (vo.getId_kb_huyen() != null&& !"".equals(vo.getId_kb_huyen())){
          strSQL.append(" and kb_id = ? ");
          v_param.add(new Parameter(vo.getId_kb_huyen(), true));                       
      }
      if (vo.getTen_ts() != null&& !"".equals(vo.getTen_ts())) {
          strSQL.append(" AND ten_ts  = ? ");
          v_param.add(new Parameter(vo.getTen_ts(), true));
      }
      //20171016 bo sung them ma_nh dap ung quan ly theo ma NH-KB
      if (vo.getMa_nh() != null&& !"".equals(vo.getMa_nh())) {
          strSQL.append(" and  ma_nh = ? ");
          v_param.add(new Parameter(vo.getMa_nh(), true));
      }
      nExc = executeStatement(strSQL.toString(), v_param, conn);

      return nExc;
  }  
}
