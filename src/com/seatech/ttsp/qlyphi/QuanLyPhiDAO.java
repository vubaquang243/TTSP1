package com.seatech.ttsp.qlyphi;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.SelectStatementException;
import com.seatech.ttsp.qlyLaiSuat.LaiSuatDAO;

import com.seatech.ttsp.qlyLaiSuat.LaiSuatVO;

import com.seatech.ttsp.qlyphi.form.QuanLyPhiForm;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Types;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class QuanLyPhiDAO extends AppDAO {
  private static final String CLASS_NAME_QUANLYPHIVO = "com.seatech.ttsp.qlyphi.QuanLyPhiVO";
  private static final String CLASS_NAME_BKETINHPHIVO = "com.seatech.ttsp.qlyphi.BKeTinhPhiVO";
  private static final String CLASS_NAME_DAO = "com.seatech.ttsp.qlyphi.QuanLyPhiDAO";
  private Connection conn;
  
  public QuanLyPhiDAO(Connection conn){
      this.conn = conn;
  }
  
  public Collection getPhi_phantrang(String strWhere,
                                                Vector v_param,
                                                int currentPage,
                                                int numberRowOnPage,
                                                Integer[] totalCount) throws Exception {
      StringBuilder query = new StringBuilder("SELECT a.ID, b.BI_DANH HE_THONG_NH,a.LOAI_GD,a.GIO_TU,a.GIO_DEN,a.GIA_TRI_TU, a.VAT , " + 
                                              "a.GIA_TRI_DEN,a.loai_tien,a.LOAI_PHI,a.MUC_PHI,a.SAN,a.TRAN,to_char(a.TU_NGAY,'dd/mm/yyyy') TU_NGAY,to_char(a.DEN_NGAY,'dd/mm/yyyy') DEN_NGAY " + 
                                              "FROM SP_MUC_PHI a " + 
                                              "JOIN SP_DM_NH_HO b " + 
                                              "ON a.HE_THONG_NH = b.MA_DV " + 
                                              "WHERE 1=1");
      if(strWhere!=null && !"".equals(strWhere)) 
          query.append(strWhere);
      query.append(" order by b.bi_danh,a.loai_gd,a.loai_phi,TO_DATE(a.TU_NGAY, 'DD/MM/YYYY') desc ");
      return executeSelectWithPaging(conn, query.toString(), v_param, CLASS_NAME_QUANLYPHIVO, currentPage, numberRowOnPage, totalCount);
      
  }
  public int insertPhi(QuanLyPhiVO phiVO) throws Exception {
      Vector v_param = new Vector();
      StringBuilder s1 = new StringBuilder();
      StringBuilder s2 = new StringBuilder();
      
      s1.append("INSERT INTO SP_MUC_PHI (ID ");
      long id = getSeqNextValue("SP_MUC_PHI_SEQ",conn); //lay next id
      s2.append(") values (? ");
      v_param.add(new Parameter(id,true));
      if (phiVO.getHe_thong_nh() != null && !"".equals(phiVO.getHe_thong_nh())) {
          s1.append(", HE_THONG_NH");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getHe_thong_nh(), true));
      }
      if (phiVO.getTu_ngay() != null && !"".equals(phiVO.getTu_ngay())) {
          s1.append(", TU_NGAY");
          s2.append(", to_date(?,'DD/MM/YYYY')");
          v_param.add(new Parameter(phiVO.getTu_ngay(), true));
      }
      if (phiVO.getLoai_gd() != null && !"".equals(phiVO.getLoai_gd())) {
          s1.append(", LOAI_GD");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getLoai_gd(), true));
      }
      if (phiVO.getLoai_phi() != null && !"".equals(phiVO.getLoai_phi())) {
          s1.append(", LOAI_PHI");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getLoai_phi(), true));
      }
      if (phiVO.getLoai_tien() != null && !"".equals(phiVO.getLoai_tien())) {
          s1.append(", LOAI_TIEN");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getLoai_tien(), true));
      }
      if (phiVO.getNguoi_tao() != null && !"".equals(phiVO.getNguoi_tao())) {
          s1.append(", NGUOI_TAO");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getNguoi_tao(), true));
      }
      if (phiVO.getGio_tu() != null && !"".equals(phiVO.getGio_tu())) {
          s1.append(", GIO_TU");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getGio_tu(), true));
      }
      if (phiVO.getGio_den() != null && !"".equals(phiVO.getGio_den())) {
          s1.append(", GIO_DEN");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getGio_den(), true));
      }
      if (phiVO.getGia_tri_tu() != null && !"".equals(phiVO.getGia_tri_tu())) {
          s1.append(", GIA_TRI_TU");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getGia_tri_tu(), true));
      }
      if (phiVO.getGia_tri_den() != null && !"".equals(phiVO.getGia_tri_den())) {
          s1.append(", GIA_TRI_DEN");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getGia_tri_den(), true));
      }
      if (phiVO.getTran() != null && !"".equals(phiVO.getTran())) {
          s1.append(", TRAN");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getTran(), true));
      }
      if (phiVO.getSan() != null && !"".equals(phiVO.getSan())) {
          s1.append(", SAN");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getSan(), true));
      }
      if (phiVO.getVat() != null && !"".equals(phiVO.getVat())) {
          s1.append(", VAT");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getVat(), true));
      }
      if (phiVO.getMuc_phi() != null && !"".equals(phiVO.getMuc_phi())) {
          s1.append(", MUC_PHI");
          s2.append(", ?");
          v_param.add(new Parameter(phiVO.getMuc_phi(), true));
      }
      s1.append(", NGAY_CAP_NHAT"); s2.append(", SYSDATE");
      s1.append(", NGAY_TAO"); s2.append(", SYSDATE");
      s1.append(", IS_USE"); s2.append(", ?");
      v_param.add(new Parameter("00", true));
      
      s1.append(s2);s1.append(")");
      return executeStatement(s1.toString(), v_param, conn);
  }
  
  public int removePhi(QuanLyPhiVO phiVO) throws Exception {
      int result = 0;
      Vector params = new Vector();
      params.add(new Parameter(phiVO.getTu_ngay() ,true));
      params.add(new Parameter(phiVO.getHe_thong_nh(),true));
      params.add(new Parameter(phiVO.getLoai_phi(),true));
      String sql = "DELETE SP_MUC_PHI WHERE TU_NGAY = to_date(?,'DD/MM/YYYY') and HE_THONG_NH = (SELECT MA_DV FROM SP_DM_NH_HO WHERE TEN_NH = ?) and LOAI_PHI = ?";
      if(phiVO.getDen_ngay() != null && !"".equals(phiVO.getDen_ngay())){
          params.add(new Parameter(phiVO.getDen_ngay(),true));
          sql += " and DEN_NGAY = to_date(?,'DD/MM/YYYY')";
      }
      result = executeStatement(sql, params, conn);
      return result;
  }
  
  public int removePhiByID(QuanLyPhiVO phiVO) throws Exception {
      int result = 0;
      Vector params = new Vector();
      params.add(new Parameter(phiVO.getId() ,true));
      String sql = "DELETE SP_MUC_PHI WHERE ID = ?";
      result = executeStatement(sql, params, conn);
      return result;
  }
  
  public int updatePhi(QuanLyPhiVO phiVO,boolean lock){
      try{
          Vector v_param = new Vector();
          StringBuffer s1 = new StringBuffer();
          s1.append("update SP_MUC_PHI set ");
          int result = 0;
          
          if (phiVO.getLoai_phi() != null && !"".equals(phiVO.getLoai_phi())) {
              s1.append(" LOAI_PHI = ? ");
              v_param.add(new Parameter(phiVO.getLoai_phi(),true));
          }
//          if (phiVO.getLoai_gd() != null && "".equals(phiVO.getLoai_gd())) {
//              s1.append(", LOAI_GD = ?");
//              v_param.add(new Parameter(phiVO.getLoai_gd(), true));
//          }
          if (phiVO.getLoai_tien() != null && !"".equals(phiVO.getLoai_tien())) {
              s1.append(", LOAI_TIEN = ? ");
              v_param.add(new Parameter(phiVO.getLoai_tien(), true));
          }
          if (phiVO.getGio_tu() != null) {
              s1.append(", GIO_TU = ? ");
              v_param.add(new Parameter(phiVO.getGio_tu(), true));
          }
          if (phiVO.getGio_den() != null) {
            s1.append(", GIO_DEN = ? ");
            v_param.add(new Parameter(phiVO.getGio_den(), true));
          }
          if (phiVO.getGia_tri_tu() != null) {
              s1.append(", GIA_TRI_TU = ? ");
              v_param.add(new Parameter(phiVO.getGia_tri_tu(), true));
          }
          if (phiVO.getGia_tri_den() != null) {
              s1.append(", GIA_TRI_DEN = ? ");
              v_param.add(new Parameter(phiVO.getGia_tri_den(), true));
          }
          if (phiVO.getTran() != null) {
              s1.append(", TRAN = ? ");
              v_param.add(new Parameter(phiVO.getTran(), true));
          }
          if (phiVO.getSan() != null) {
              s1.append(", SAN = ? ");
              v_param.add(new Parameter(phiVO.getSan(), true));
          }
          if (phiVO.getMuc_phi() != null && !"".equals(phiVO.getMuc_phi())) {
              s1.append(", MUC_PHI = ? ");
              v_param.add(new Parameter(phiVO.getMuc_phi(), true));
          }
          if (phiVO.getTu_ngay() != null && !"".equals(phiVO.getTu_ngay())) {
              s1.append(", TU_NGAY = to_date(?,'dd/mm/yyyy') ");
              v_param.add(new Parameter(phiVO.getTu_ngay(), true));
          }
          if (phiVO.getDen_ngay() != null && !"".equals(phiVO.getDen_ngay())) {
              s1.append(", DEN_NGAY = to_date(?,'dd/mm/yyyy') ");
              v_param.add(new Parameter(phiVO.getDen_ngay(), true));
          }
          if (phiVO.getVat() != null && !"".equals(phiVO.getVat())) {
              s1.append(", VAT = ? ");
              v_param.add(new Parameter(phiVO.getVat(), true));
          }
          if(lock){
              s1.append(" DEN_NGAY = SYSDATE ");
          }
          s1.append(", NGAY_CAP_NHAT = SYSDATE ");
          s1.append(" where id = ? ");
          v_param.add(new Parameter(phiVO.getId(), true));
          result = executeStatement(s1.toString(), v_param, conn);
          return result;
      }catch(Exception e){
          DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".updatePhi: " + e.getMessage(), e);
          return -1;
      }
  }

    public Map operatorPhi(String nh_kb,QuanLyPhiForm f, String id_nsd) throws Exception {
        Map<String,String> result = new HashMap<String,String>();
        CallableStatement cs = null;
        cs = conn.prepareCall("{call pkg_lai_phi.pro_tinh_phi(?,?,?,?,?,?,?,?)}");
        cs.setString(1, nh_kb);
        cs.setString(2, f.getMa_nh());
        cs.setString(3, f.getLoai_tien());
        cs.setString(4, f.getTu_ngay());
        cs.setString(5, f.getDen_ngay());
        cs.setString(6, id_nsd);
        cs.registerOutParameter(7, java.sql.Types.VARCHAR);
        cs.registerOutParameter(8, java.sql.Types.VARCHAR);
        cs.execute();
        String error = cs.getString(7);
        if(error.equals("00")){
            result.put("success", "ok");
        }else{
            result.put("error",  cs.getString(8));
        }
        return result;
    }
    
    public Map callProcValidatePhi(QuanLyPhiVO phiVO) throws Exception {
        Map<String,String> result = new HashMap<String,String>();
        CallableStatement cs = null;
        cs = conn.prepareCall("{call pkg_lai_phi.pro_ktra_muc_phi(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        cs.setString(1, phiVO.getHe_thong_nh());
        cs.setString(2, phiVO.getLoai_gd());
        cs.setString(3, phiVO.getLoai_phi());
        cs.setString(4, phiVO.getLoai_tien());
        cs.setString(5, phiVO.getTu_ngay());
        cs.setString(6, phiVO.getDen_ngay());
        cs.setString(7, phiVO.getGio_tu());
        cs.setString(8, phiVO.getGio_den());
        cs.setString(9, phiVO.getGia_tri_tu());
        cs.setString(10, phiVO.getGia_tri_den());
        cs.setLong(11, phiVO.getId());
        cs.registerOutParameter(12, java.sql.Types.VARCHAR);
        cs.registerOutParameter(13, java.sql.Types.VARCHAR);
        cs.execute();
        
        String error = cs.getString(12);
        if(error.equals("00")){
            result.put("success", "ok");
        }else{
            result.put("error",  cs.getString(13));
        }
        return result;
    }

    public QuanLyPhiVO getPhi(String condition, Vector params)throws Exception{
        QuanLyPhiVO vo = null;
        StringBuilder query = new StringBuilder();
        try{
            query.append("select ID,HE_THONG_NH,LOAI_GD,LOAI_PHI,LOAI_TIEN,to_char(TU_NGAY,'dd/mm/yyyy') TU_NGAY,to_char(DEN_NGAY,'dd/mm/yyyy') DEN_NGAY, ");
            query.append("GIO_TU,GIO_DEN,GIA_TRI_TU,GIA_TRI_DEN,TRAN,SAN,MUC_PHI,NGUOI_TAO,VAT,IS_USE "); 
            query.append("from sp_muc_phi ");
            if (condition != null & !condition.isEmpty()) {
                query.append(" WHERE " + condition);
            }
            vo = (QuanLyPhiVO)findByPK(query.toString(), params, CLASS_NAME_QUANLYPHIVO, conn);
        }catch(Exception e){
            DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".getPhi: " + e.getMessage());
            daoEx.printStackTrace();
            throw daoEx;
        }
        return vo;
    }

    public List<BKeTinhPhiVO> getKetQuaTinhPhi(String condition, Vector params) throws Exception{
        List<BKeTinhPhiVO> result = new ArrayList<BKeTinhPhiVO>();
        StringBuilder query = new StringBuilder();
        query.append("select to_char(a.ngay_tt,'DD/MM/YYYY') ngay_tt,a.MUC_PHI, count(a.NGAY_TT) so_ltt, a.loai_phi,a.vat, " +
        " NVL(SUM (a.so_tien),0) tong_tien, " + 
        "          NVL(SUM (a.tien_phi),0) tong_phi, " + 
        "          NVL(SUM (a.phi_nguyen_te),0) tong_phi_nt " + 
        "from SP_BKE_TINH_PHI_KB a " + 
        "join SP_DM_NGAN_HANG b " + 
        "on a.NHKB_CHUYEN = b.MA_NH " + 
        "where " + 
        "a.NGAY_TT >= to_date(?,'DD/MM/YYYY') and a.NGAY_TT <= to_date(?,'DD/MM/YYYY') " + 
        "and a.LOAI_GD = ? ");
        if(condition!=null && !"".equals(condition)) {
            query.append(condition);
        }
        query.append(" GROUP BY a.ngay_tt,a.MUC_PHI,a.loai_phi,a.vat ");
        query.append(" ORDER BY a.ngay_tt desc ");
        ResultSet rs = executeSelectStatement(query.toString(), params, conn);
        
        while(rs.next()){
            BKeTinhPhiVO vo = new BKeTinhPhiVO();
            vo.setNgay_tt(rs.getString("ngay_tt"));
            vo.setVat(rs.getString("vat"));
            vo.setSo_ltt(rs.getLong("so_ltt"));
            vo.setLoai_phi(rs.getString("loai_phi"));
            vo.setMuc_phi(rs.getString("muc_phi"));
            vo.setSo_tien(rs.getBigDecimal("tong_tien"));
            vo.setPhi(rs.getBigDecimal("tong_phi"));
            vo.setTong_phi_nguyen_te(rs.getString("tong_phi_nt"));
            result.add(vo);
        }
        return result;
    }

    public List getChiTietPhi_phanTrang(String strWhere,
                                                Vector params,
                                                int currentPage,
                                                int numberRowOnPage,
                                                Integer[] totalCount) throws Exception {
        StringBuilder query = new StringBuilder();
        query.append("SELECT  loai_gd, " + 
        "        ltt_id, " + 
        "        so_yctt, " + 
        "        TO_CHAR (ngay_tt, 'DD/MM/YYYY') ngay_tt, " + 
        "        loai_phi, " + 
        "        so_tien, " + 
        "        muc_phi, " + 
        "        tien_phi, " + 
        "        NVL (vat, '0') vat, " + 
        "        gio_phan_hoi, " + 
        "        NVL (phi_nguyen_te, '0') phi_nguyen_te " + 
        "  FROM  sp_bke_tinh_phi_kb " + 
                    " WHERE 1 = 1 ");
        if(strWhere != null && !"".equals(strWhere))
            query.append(strWhere);
        query.append(" order by NGAY_TT desc ");
        return (List)executeSelectWithPaging(conn, query.toString(), params, CLASS_NAME_BKETINHPHIVO, currentPage, numberRowOnPage, totalCount);
    }
}
