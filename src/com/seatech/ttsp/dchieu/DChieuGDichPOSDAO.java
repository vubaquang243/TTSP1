package com.seatech.ttsp.dchieu;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

public class DChieuGDichPOSDAO extends AppDAO{
 /**
   * @creater: ThuongDT
   * @creat date: 10/11/2016
   * @see: Lop DAO phuc vu doi chieu POS
   * */
  private String strValueObject ="com.seatech.ttsp.dchieu.DChieuGDichPOSDAO";
  private String strValueObjectVO ="com.seatech.ttsp.dchieu.DChieuGDichPOSVO";
  private String strValueObjectVO2 ="com.seatech.ttsp.dchieu.KQuaDChieuPOSVO";
  
  Connection conn = null;
    public DChieuGDichPOSDAO(Connection conn) {
      this.conn = conn;
    }
    //Lay danh sach ket qua doi chieu
  public Collection getDChieuList(String strWhere, Vector vParam,
     Integer page, Integer count, Integer[] totalCount) throws Exception {

      Collection reval = null;
      try {

          String strSQL ="";

          strSQL += "select d.id, a.mt_id, b.shkb,b.ten, to_char(a.ngay_bk,'dd/MM/yyyy') ngay_bk, a.ma_nh, a.ma_kb,  bi_danh,    \n" + 
          " decode(d.trang_thai,'01','Chênh lệch','02','Khớp đúng', '03', 'Hủy ĐC','04','Chênh lệch - Xác nhận đúng','Chưa đối chiếu') trang_thai, decode(d.trang_thai,null,'00',d.trang_thai) trang_thai_dc ,\n" + 
          "  sp_util_pkg.fnc_format_number_VND(decode(tong_pos,null,'0',tong_pos)) tong_pos ,  sp_util_pkg.fnc_format_number_VND(decode(tong_qtoan,null,'0',tong_qtoan)) tong_qtoan\n" + 
          " FROM sp_bke_gd_pos a, \n" + 
          " sp_dm_manh_shkb b,sp_dm_nh_ho c, \n" + 
          " (select id, lan_dc, ngay_dc, ma_nh, ngay_tao, trang_thai, tt_dc_tu_dong, tong_pos, tong_qtoan, ma_kb,  so_lenh_pos, so_lenh_qt, bk_id,so_tham_chieu from sp_kq_dc_pos \n" + 
          //"    where id in(select max(id) from sp_kq_dc_pos where to_char(ngay_dc, 'dd/MM/yyyy') = ? group by bk_id) \n" + 
          "    where id in(select id from sp_kq_dc_pos where to_char(ngay_dc, 'dd/MM/yyyy') = ?) \n" + 
          " )d \n" + 
          " ,sp_dm_htkb e \n" + 
          " where a.ma_kb = b.ma_nh and SUBSTR(a.ma_nh,3,3) = c.ma_dv(+)  \n" + 
          " and b.shkb = e.ma  \n" + 
          " and a.mt_id = d.bk_id(+)  ";
          
          strSQL += strWhere + " ORDER BY shkb desc ";
           reval =  executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                        strValueObjectVO, page, count,
                                       totalCount);
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(strValueObject + ".getDChieuList(): " +
                               ex.getMessage(), ex);
          throw daoEx;
      }
      return reval;
  } 
  
  
  
  //Lay chi tiet ket qua doi chieu
  public Collection getDChieuChiTiet(String strWhere, Vector vParam) throws Exception {   
    Collection reval = null;
    Integer totalCount[] = new Integer[15];
      try {
          String strSQL ="";
          strSQL +="SELECT a.bk_id,a.id, a.lan_dc, to_char(a.ngay_dc,'dd/MM/yyyy') ngay_dc, a.ma_nh, a.ngay_tao, a.trang_thai," + 
          "       a.tt_dc_tu_dong, sp_util_pkg.fnc_format_number_VND(a.tong_pos) tong_pos, sp_util_pkg.fnc_format_number_VND(a.tong_qtoan) tong_qtoan, a.ma_kb, a.so_lenh_pos," + 
          "       a.so_lenh_qt, decode(SUBSTR((so_lenh_pos-so_lenh_qt),0,1),'-',(-(so_lenh_pos-so_lenh_qt)),(so_lenh_pos-so_lenh_qt)) so_lenh_lech," +
          " sp_util_pkg.fnc_format_number_VND(decode(SUBSTR((tong_pos - tong_qtoan),0,1),'-',(-(tong_pos - tong_qtoan)),(tong_pos - tong_qtoan))) tong_lech," +
              "so_tham_chieu" +          
          //"  FROM sp_kq_dc_pos a  where id in(select max(id) from sp_kq_dc_pos  ";
          "  FROM sp_kq_dc_pos a  where id in(select id from sp_kq_dc_pos  ";
          strSQL +=" where  1=1"+ strWhere +")";
        reval =  executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                     strValueObjectVO2, 1, 20,
                                    totalCount);
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(strValueObject + ".getDChieuChiTietVO(): " +
                               ex.getMessage(), ex);
          throw daoEx;
      }
    return reval;
  } 
  
  
  //Lay chi tiet ket qua doi chieu
  public KQuaDChieuPOSVO getDChieuChiTietVO(String strWhere, Vector vParam) throws Exception {
    KQuaDChieuPOSVO dcVO = null;
      try {
          String strSQL ="";
          strSQL +="SELECT a.bk_id,a.id, a.lan_dc, to_char(a.ngay_dc,'dd/MM/yyyy') ngay_dc, a.ma_nh, a.ngay_tao, a.trang_thai," + 
          "       a.tt_dc_tu_dong, sp_util_pkg.fnc_format_number_VND(a.tong_pos) tong_pos, sp_util_pkg.fnc_format_number_VND(a.tong_qtoan) tong_qtoan, a.ma_kb, a.so_lenh_pos," + 
          "       a.so_lenh_qt, decode(SUBSTR((so_lenh_pos-so_lenh_qt),0,1),'-',(-(so_lenh_pos-so_lenh_qt)),(so_lenh_pos-so_lenh_qt)) so_lenh_lech," +
          " sp_util_pkg.fnc_format_number_VND(decode(SUBSTR((tong_pos - tong_qtoan),0,1),'-',(-(tong_pos - tong_qtoan)),(tong_pos - tong_qtoan))) tong_lech," +
              "so_tham_chieu" +          
          //"  FROM sp_kq_dc_pos a  where id in(select max(id) from sp_kq_dc_pos  ";
          "  FROM sp_kq_dc_pos a  where id in(select id from sp_kq_dc_pos  ";
          strSQL +=" where  1=1"+ strWhere +")";
        dcVO =  (KQuaDChieuPOSVO)findByPK(strSQL.toString(), strValueObjectVO2);
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(strValueObject + ".getDChieuChiTietVO(): " +
                               ex.getMessage(), ex);
          throw daoEx;
      }
      return dcVO;
  } 
  //Cap nhat KQDC POS
  public int updateDChieuChiTiet(String strSetClause,String strWhereClause, Vector vParam) throws Exception {
      int nReturn = 0;
    KQuaDChieuPOSVO dcVO = null;
      try {
          String strSQL ="";
          strSQL +=" update sp_kq_dc_pos set " + strSetClause+" where 1=1 "+strWhereClause;
        nReturn = executeStatement(strSQL.toString(), vParam,conn);
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(strValueObject + ".getDChieuChiTietVO(): " +
                               ex.getMessage(), ex);
          throw daoEx;
      }
      return nReturn;
  } 
  
  //Doi chieu lai (D/c thu cong)
  public String DChieuLaiBK(String strIDBK) throws Exception {
      String strReturn = "" ;
    KQuaDChieuPOSVO dcVO = null;
      try {
        CallableStatement cs = null;
        cs = conn.prepareCall("{call sp_doi_chieu_pos_pkg.proc_dc_pos_bang_tay(?,?,?,?)}"); //* chua viet moi
        cs.setString(1, strIDBK);
        cs.registerOutParameter(2, java.sql.Types.VARCHAR);
        cs.registerOutParameter(3, java.sql.Types.VARCHAR);
        cs.registerOutParameter(4, java.sql.Types.VARCHAR);
        cs.execute();
        String p_kq_id = cs.getString(2);
        String p_err_code = cs.getString(3);
        String p_err_desc = cs.getString(4);
        strReturn = p_kq_id+";"+p_err_code+";"+p_err_desc;
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(strValueObject + ".getDChieuChiTietVO(): " +
                               ex.getMessage(), ex);
          throw daoEx;
      }
      
      return strReturn;
  } 
  
}
