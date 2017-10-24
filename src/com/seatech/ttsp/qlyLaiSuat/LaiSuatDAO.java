package com.seatech.ttsp.qlyLaiSuat;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.Parameter;

import com.seatech.framework.exception.DAOException;

import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.StoredProcedureException;
import com.seatech.ttsp.qlyLaiSuat.form.LaiSuatForm;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.Types;

import java.text.DecimalFormat;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/* 
 * @Modifier: ThuongDT
 * @Modify: Them ham de lay ket qua tinh lai
 * @Track: 20161212 
 * 
 * @Modifier: ThuongDT
 * @modify date: 15/02/2017
 * @Modify: sua khi querry lay tong lai truong hop khong tim kiem duoc ban ghi la null
 * @Track: 20170215-thuongdt 
 */
public class LaiSuatDAO extends AppDAO{
    private static final String CLASS_NAME_LAISUATVO = "com.seatech.ttsp.qlyLaiSuat.LaiSuatVO";
    private static final String CLASS_NAME_DAO = "com.seatech.ttsp.qlyLaiSuat.LaiSuatVO";
    private static final String CLASS_NAME_BKETINHLAIVO = "com.seatech.ttsp.qlyLaiSuat.BangKeTinhLaiVO";
    private Connection conn;
    
    public LaiSuatDAO(Connection conn){
        this.conn = conn;
    }
    
    public LaiSuatVO getLaiSuat(String condition, Vector params)throws Exception{
        LaiSuatVO vo = null;
        StringBuilder query = new StringBuilder();
        try{
            query.append("SELECT ID,HE_THONG_NH,to_char(NGAY_HIEU_LUC,'dd/mm/yyyy') NGAY_HIEU_LUC,to_char(NGAY_HET_HIEU_LUC,'dd/mm/yyyy') NGAY_HET_HIEU_LUC,LOAI_LAI_SUAT,LAI_SUAT,NGUOI_TAO,LOAI_TIEN ");
            query.append("FROM SP_LAI_SUAT a "); 
            query.append("JOIN SP_DM_NH_HO b ");
            query.append("ON a.HE_THONG_NH = b.MA_DV ");
            if (condition != null & !condition.isEmpty()) {
                query.append(" WHERE " + condition);
            }
            vo = (LaiSuatVO)findByPK(query.toString(), params, CLASS_NAME_LAISUATVO, conn);
        }catch(Exception e){
            DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".getLaiSuat: " + e.getMessage());
            daoEx.printStackTrace();
            throw daoEx;
        }
        return vo;
    }
    
    public Collection getLaiSuat_phantrang(String strWhere,
                                                  Vector v_param,
                                                  int currentPage,
                                                  int numberRowOnPage,
                                                  Integer[] totalCount) throws Exception {
        try{
            String query = "select a.id ,b.TEN_NH HE_THONG_NH,LAI_SUAT,LOAI_LAI_SUAT,to_char(NGAY_HIEU_LUC,'DD/MM/YYYY') NGAY_HIEU_LUC,to_char(NGAY_HET_HIEU_LUC,'DD/MM/YYYY') NGAY_HET_HIEU_LUC,LOAI_TIEN " +
                           "from SP_LAI_SUAT a join sp_dm_nh_ho b on a.HE_THONG_NH = b.MA_DV ";
            if(strWhere!=null && !"".equals(strWhere)) 
                query = query + " WHERE 1=1 " + strWhere;
            query += " order by b.ten_nh,loai_tien,loai_lai_suat desc";
            return executeSelectWithPaging(conn, query.toString(), v_param, CLASS_NAME_LAISUATVO, currentPage, numberRowOnPage, totalCount);
        }catch(Exception e){
            throw e;
        }
    }
    
    public Collection getListLaiSuat(String strWhere,Vector params) throws Exception {
        try{
            String query = "select a.id,b.TEN_NH HE_THONG_NH,LAI_SUAT,LOAI_LAI_SUAT,to_char(NGAY_HIEU_LUC,'DD/MM/YYYY') NGAY_HIEU_LUC,to_char(NGAY_HET_HIEU_LUC,'DD/MM/YYYY') NGAY_HET_HIEU_LUC,LOAI_TIEN " +
                           "from SP_LAI_SUAT a join sp_dm_nh_ho b on a.HE_THONG_NH = b.MA_DV ";
            if(strWhere!=null && !"".equals(strWhere)) 
                query = query + " WHERE 1=1 " + strWhere;
            query += " order by to_date(a.NGAY_HIEU_LUC,'DD/MM/YYYY') desc ";
            return executeSelectStatement(query, params, CLASS_NAME_LAISUATVO);
        }catch(Exception e){
            throw e;
        }
    }

    public int insertLaiSuat(LaiSuatVO laiSuat) throws Exception {
        Vector v_param = new Vector();
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        
        s1.append("INSERT INTO sp_lai_suat (ID ");
        long id = getSeqNextValue("sp_lai_suat_seq",conn);
        s2.append(") values (? ");
        v_param.add(new Parameter(id,true));
        if (laiSuat.getHe_thong_nh() != null && !"".equals(laiSuat.getHe_thong_nh())) {
            s1.append(", HE_THONG_NH");
            s2.append(", ?");
            v_param.add(new Parameter(laiSuat.getHe_thong_nh(), true));
        }
        if (laiSuat.getNgay_hieu_luc() != null && !"".equals(laiSuat.getNgay_hieu_luc())) {
            s1.append(", NGAY_HIEU_LUC");
            s2.append(", to_date(?,'DD/MM/YYYY')");
            v_param.add(new Parameter(laiSuat.getNgay_hieu_luc(), true));
        }
        if (laiSuat.getLoai_lai_suat() != null && !"".equals(laiSuat.getLoai_lai_suat())) {
            s1.append(", LOAI_LAI_SUAT");
            s2.append(", ?");
            v_param.add(new Parameter(laiSuat.getLoai_lai_suat(), true));
        }
        if (laiSuat.getLai_suat() != null && !"".equals(laiSuat.getLai_suat())) {
            s1.append(", LAI_SUAT");
            s2.append(", ?");
            v_param.add(new Parameter(laiSuat.getLai_suat(), true));
        }
        if (laiSuat.getNguoi_tao() != null && !"".equals(laiSuat.getNguoi_tao())) {
            s1.append(", NGUOI_TAO");
            s2.append(", ?");
            v_param.add(new Parameter(laiSuat.getNguoi_tao(), true));
        }
        if (laiSuat.getGhi_chu() != null && !"".equals(laiSuat.getGhi_chu())) {
            s1.append(", GHI_CHU");
            s2.append(", ?");
            v_param.add(new Parameter(laiSuat.getGhi_chu(), true));
        }
        if (laiSuat.getLoai_tien() != null && !"".equals(laiSuat.getLoai_tien())) {
            s1.append(", LOAI_TIEN");
            s2.append(", ?");
            v_param.add(new Parameter(laiSuat.getLoai_tien(), true));
        }
        s1.append(", NGAY_SUA_GAN_NHAT");
        s2.append(", SYSDATE");
        s1.append(", NGAY_TAO");
        s2.append(", SYSDATE");
        
        s1.append(s2);s1.append(")");
        return executeStatement(s1.toString(), v_param, conn);
    }
    
    public int checkExit(String where ,Vector params) throws Exception{
        int result = 0;
        StringBuilder query = new StringBuilder("select max(id) ID from SP_LAI_SUAT where HE_THONG_NH = ? and loai_tien = ?  ");
        query.append(" and ngay_het_hieu_luc is null ");
        if(where != null && !"".equals(where)) query.append(where);
        ResultSet rs = executeSelectStatement(query.toString(), params, conn);
        if(rs.next())
          result = rs.getInt("ID");
        return result;
    }

    public int setEndDate(Vector params) throws Exception {
        int result = 0;
        String query = "update SP_LAI_SUAT set NGAY_HET_HIEU_LUC = to_date(?,'DD/MM/YYYY')-1,NGAY_SUA_GAN_NHAT = sysdate " + 
                       "where id = ?";
        result = executeStatement(query, params, conn);
        return result;
    }

    public int removeLaiSuat(LaiSuatVO laiSuatVO) throws Exception {
        int result = 0;
        Vector params = new Vector();
        params.add(new Parameter(laiSuatVO.getNgay_hieu_luc(),true));
        params.add(new Parameter(laiSuatVO.getHe_thong_nh(),true));
        String sql = "DELETE SP_LAI_SUAT WHERE NGAY_HIEU_LUC = to_date(?,'DD/MM/YYYY') and HE_THONG_NH = (SELECT MA_DV FROM SP_DM_NH_HO WHERE TEN_NH = ?) ";
        if(laiSuatVO.getNgay_het_hieu_luc()!= null && !"".equals(laiSuatVO.getNgay_het_hieu_luc())){
            params.add(new Parameter(laiSuatVO.getNgay_het_hieu_luc(),true));
            sql += " and NGAY_HET_HIEU_LUC = to_date(?,'DD/MM/YYYY')";
        }
        result = executeStatement(sql, params, conn);
        return result;
    }
    
    public int updateLaiSuat(LaiSuatVO laiSuat){
        try{
            Vector v_param = new Vector();
            StringBuffer s1 = new StringBuffer();
            s1.append("update sp_lai_suat set ");
            int result = 0;
            if (laiSuat.getHe_thong_nh() != null && !"".equals(laiSuat.getHe_thong_nh())) {
                s1.append(" HE_THONG_NH = ? ");
                v_param.add(new Parameter(laiSuat.getHe_thong_nh(), true));
            }
            if (laiSuat.getNgay_hieu_luc() != null) {
                s1.append(", NGAY_HIEU_LUC = to_date(?,'dd/mm/yyyy') ");
                v_param.add(new Parameter(laiSuat.getNgay_hieu_luc(), true));
            }
            if (laiSuat.getNgay_het_hieu_luc() != null) {
                s1.append(", NGAY_HET_HIEU_LUC = to_date(?,'dd/mm/yyyy') ");
                v_param.add(new Parameter(laiSuat.getNgay_het_hieu_luc(), true));
            }
            if (laiSuat.getLoai_lai_suat() != null && !"".equals(laiSuat.getLoai_lai_suat())) {
                s1.append(", LOAI_LAI_SUAT = ? ");
                v_param.add(new Parameter(laiSuat.getLoai_lai_suat(), true));
            }
            if (laiSuat.getLai_suat() != null && !"".equals(laiSuat.getLai_suat())) {
                s1.append(", LAI_SUAT = ? ");
                v_param.add(new Parameter(laiSuat.getLai_suat(), true));
            }
            if (laiSuat.getNguoi_tao() != null && !"".equals(laiSuat.getNguoi_tao())) {
                s1.append(", NGUOI_TAO = ? ");
                v_param.add(new Parameter(laiSuat.getNguoi_tao(), true));
            }
            if (laiSuat.getGhi_chu() != null) {
                s1.append(", GHI_CHU = ? ");
                v_param.add(new Parameter(laiSuat.getGhi_chu(), true));
            }
            if (laiSuat.getLoai_tien() != null && !"".equals(laiSuat.getLoai_tien())) {
                s1.append(", LOAI_TIEN = ? ");
                v_param.add(new Parameter(laiSuat.getLoai_tien(), true));
            }              
            s1.append(" where id = ? ");
            v_param.add(new Parameter(laiSuat.getId(), true));
            result = executeStatement(s1.toString(), v_param, conn);
            return result;
        }catch(Exception e){
            DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".updateLaiSuat: " + e.getMessage(), e);
            return -1;
        }
    }

    public Map operatorLaiSuat(String nh_kb,LaiSuatForm f,String nsd) throws Exception {
        Map<String,String> result = new HashMap<String,String>();
        CallableStatement cs = null;
        cs = conn.prepareCall("{call pkg_lai_phi.pro_tinh_lai(?,?,?,?,?,?,?,?,?)}");
        cs.setString(1, nh_kb);
        cs.setString(2, f.getMa_nh());
        cs.setString(3, f.getSo_tk());
        cs.setString(4, f.getFromDate());
        cs.setString(5, f.getToDate());
        cs.setString(6, nsd);
        cs.registerOutParameter(7, java.sql.Types.VARCHAR);
        cs.registerOutParameter(8, java.sql.Types.VARCHAR);
        cs.registerOutParameter(9, Types.VARCHAR);
        cs.execute();
  
        String error = cs.getString(7);
        if(error.equals("00")){
            result.put("total", cs.getString(9));
        }else{
            result.put("error",  cs.getString(8));
        }
        return result;
    }
	//ThuongDT-20161212-Them bao cao tinh lai-begin
	//Lay danh sach tinh lai
    public List<BangKeTinhLaiVO> getKetQuaTinhLai(String condition,Vector params) throws Exception {
        String query = " /* Formatted on 4-Feb-2015 15:07:43 (QP5 v5.126) */ " + 
        "SELECT  a.ma_kb, " + 
        "        a.ty_gia, " + 
        "        a.ma_nh, " + 
        "        a.so_tk, " + 
        "        a.loai_tien, " + 
        "        a.nguoi_tao, " + 
        "        a.ngay_tao, " + 
        "        TO_CHAR (a.ngay, 'DD/MM/YYYY') ngay, " + 
        "        a.so_du_cuoi, " + 
        "        lai_suat, " + 
        "        a.loai_lai_suat, " + 
        "        a.lai, " + 
        "        a.ghi_chu, " + 
        "        a.tinh_trang,d.loai_tk " + 
        "  FROM  sp_bke_tinh_lai_kb a, " + 
        "        sp_dm_manh_shkb b, " + 
        "        sp_dm_htkb c, SP_TKNH_KB d " + 
        " WHERE      1 = 1 " + 
        "        AND a.ma_kb = b.ma_nh " + 
        "        AND b.shkb = c.ma " + 
        "        AND c.tinhtrang = 1 and d.kb_id = c.id and a.SO_TK = d.SO_TK ";
        if(condition!=null && !"".equals(condition)) 
            query = query + condition;
        query += " order by to_date(NGAY,'DD/MM/YYYY') desc ";
        return (List<BangKeTinhLaiVO>)executeSelectStatement(query, params, CLASS_NAME_BKETINHLAIVO);
    }
  //Lay tong lai  
  public String getKetQuaTinhTongLai(String condition) throws Exception {
      String kq = "";
      ResultSet rs;
      String query = 
      "SELECT sum(a.lai) lai " +      
      "  FROM  sp_bke_tinh_lai_kb a, " + 
      "        sp_dm_manh_shkb b, " + 
      "        sp_dm_htkb c " + 
      " WHERE      1 = 1 " + 
      "        AND a.ma_kb = b.ma_nh " + 
      "        AND b.shkb = c.ma " + 
      "        AND c.tinhtrang = 1 ";
      if(condition!=null && !"".equals(condition)) 
          query = query + condition;
    rs = executeSelectStatement(query,null, conn);
    if(rs.next()){
      //20170215-thuongdt sua lai trong truong hop khong tim thay gia tri ket qua la null
      //kq = rs.getString("lai").toString();
      kq = rs.getString("lai");
      kq = kq==null?"0":kq;
      //20170215-thuongdt end
    }
      return kq;
  }
  	//ThuongDT-20161212-Them bao cao tinh lai-end
}
