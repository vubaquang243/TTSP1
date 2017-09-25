package com.seatech.ttsp.reports.theodoisolieuttsp;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.SelectStatementException;

import java.sql.Connection;

import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;

/* @Modifier: ThuongDT
 * @See: them dieu kien loc o getSoDuCOT
 * @Track: 20161212
 */

public class SoLieuTTSPDAO extends AppDAO {
    private static final String CLASS_NAME_VO = "com.seatech.ttsp.reports.theodoisolieuttsp.SoLieuTTSPVO";
    private static final String CLASS_NAME_DAO = "com.seatech.ttsp.reports.theodoisolieuttsp.SoLieuTTSPDAO";
    
    Connection conn = null;
    
    public SoLieuTTSPDAO(Connection conn){
      this.conn = conn;
    }
    
    public SoLieuTTSPVO getRecord(String where,Vector params) throws Exception{
      SoLieuTTSPVO result = null;
      StringBuffer sql = new StringBuffer();
      sql.append("SELECT a.id, a.ma ma_kb, a.ten ten_kb_huyen,b.ten ten_kb_tinh, c.HAN_MUC_NO, d.ma_nh ma_nh_kb");
      sql.append(" FROM sp_dm_htkb a ");
      sql.append(" JOIN sp_dm_htkb b ON a.id_cha = b.id ");
      sql.append(" JOIN sp_tknh_kb c ON a.id = c.kb_id ");
      sql.append(" JOIN sp_dm_manh_shkb d ON a.ma = d.shkb ");
      sql.append(" JOIN sp_dm_ngan_hang e ON c.nh_id = e.id ");
      sql.append(" WHERE " + where);
      result = (SoLieuTTSPVO)findByPK(sql.toString(), params , CLASS_NAME_VO,conn);
      return result;
    }
    
  public Collection getNoiDung(String strWhere,
                                  Vector vParam) throws Exception {
      Collection reval = null;
      try {
          String strSQL = "";
          strSQL +=
                  " SELECT  a.ma_nh, a.ma_kb, a.ngay_gd, a.ghi_chu " + 
                  " FROM sp_gd_thu_cong a where 1=1 " + strWhere + " ORDER BY a.id" ;
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                         conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_VO + ".getNoiDung(): " +
                               ex.getMessage(), ex);
          throw daoEx;
      }
      return reval;
  }

	//HungBM-20161212 - Them loai tien khi tra cuu-begin
    public long getSoDuCOT(String date,String makb,String manh,String loaiTien) throws Exception{
        long result = 0;
        String query = "select a.SO_DU_COT " + 
        "from sp_so_du a " + 
        " where a.ngay_gd = to_date('"+date+"','YYYYMMDD') " + 
        " and a.MA_KB = '"+makb+"' " +
        " and a.Ma_NH = '"+manh+"'"+
        " and a.loai_tien = '"+loaiTien+"'";
      
        try{
            ResultSet rs = executeSelectStatement(query, new Vector(),conn);
            if(rs.next())
                result = rs.getLong("SO_DU_COT");
        }catch(Exception ex){
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getSoDuCOT(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
        return result;
    }

    public long getSoThuChi(String type, String date,String makb,String manh,String loaitien) throws Exception {
        long result = 0;
        String query = "select sum(so_tien) so_tien       " + 
        "from SP_QUYET_TOAN       " + 
        "where loai_qtoan = '"+type+"'  " + 
        "and qtoan_dvi = 'Y'      " + 
        "and lai_phi in ('01','03') " + 
        "and ngay_qtoan = to_date('"+date+"','YYYYMMDD') " + 
        "and MA_KB = '"+makb+"' " +
        "and nhkb_chuyen = '"+manh+"'"+
        "and ma_nt = '"+loaitien+"'";      
        try{
            ResultSet rs = executeSelectStatement(query, new Vector(),conn);
            if(rs.next())
                result = rs.getLong("SO_TIEN");
        }catch(Exception ex){
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getSoThuChi(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
        return result;
    }
		//HungBM-20161212 - Them loai tien khi tra cuu-end
}
