package com.seatech.ttsp.dmdvsdns;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Vector;


public class DMDonViSdnsDAO extends AppDAO{
    private static String CLASS_NAME_VO = "com.seatech.ttsp.dmdvsdns.DMDonViSdnsVO";
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.dmdvsdns.DMDonViSdnsDAO";
    
    public DMDonViSdnsDAO(Connection conn) {
        this.conn = conn;
    }
  public DMDonViSdnsVO getDMDonViSdns(String whereClause, Vector params) throws Exception{
        DMDonViSdnsVO dvsdnsVO = null;
        StringBuffer strSQL = new StringBuffer();
        
        try{
            strSQL.append("SELECT a.ma, a.ten, a.tinh_trang, a.id, a.dia_chi, a.ma_dia_ban,\n" + 
            "       a.ma_bql, a.ma_cdt, a.ma_dtnt, a.ma_cu, a.nam, a.kieu, a.capdt,\n" + 
            "       a.ma_chuong, a.ten_chuong, a.tendv_gdt, a.ten_nhomda, a.ma_ctmt,\n" + 
            "       a.ten_ctmt, a.ma_loaida, a.ten_loaida, a.ma_nhomda, a.ma_loaiqh,\n" + 
            "       a.ten_loaiqh, a.ma_htda, a.ten_htda, a.ma_htql, a.ten_htql,\n" + 
            "       a.ngay_ps, a.ngay_cn_cuoi\n");
            strSQL.append(" FROM sp_dm_dvsdns a ");
            
            if(whereClause != null & !whereClause.isEmpty()){
                strSQL.append(" WHERE "+whereClause);
            }
            dvsdnsVO = (DMDonViSdnsVO) findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
            
        }catch(Exception ex){
            DAOException daoEx = new DAOException(CLASS_NAME_DAO + ".getTKKT(strCode): "+ex.getMessage());
//            daoEx.printStackTrace();
            throw daoEx;
        }
        
        return dvsdnsVO;
    }
}
