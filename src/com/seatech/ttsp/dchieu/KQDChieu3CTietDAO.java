package com.seatech.ttsp.dchieu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class KQDChieu3CTietDAO extends AppDAO {
  private String strValueObjectVO ="com.seatech.ttsp.dchieu.KQDChieu3CTietVO";
  Connection conn = null;

  public KQDChieu3CTietDAO(Connection conn) {
      this.conn = conn;
  }


  public Collection getKQDChieu3CTietList(String strWhere, Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL =" SELECT a.id, a.kq_id, a.bk_id, a.insert_date, to_char(a.ngay_ct,'dd/MM/yyyy') as ngay_ct," +
          " a.ma_kb, a.ten_kb, a.mt_id, a.so_tien, a.trang_thai, a.mt_type, a.ma_nh, b.send_bank," + 
          " b.receive_bank, (SELECT ten_nh FROM sp_dm_nh_ho  where ma_nh= a.ma_nh) as ten_receive_bank, b.lan_dc " + 
          "  FROM sp_kq_dc3_ctiet a inner join sp_kq_dc3 b on b.id=a.kq_id WHERE 1=1 ";          
          strSQL += strWhere + " ORDER BY ma_kb,ngay_ct, a.trang_thai ASC ";
           reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,conn);
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(strValueObjectVO + ".getKQDChieu3CTietList(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
  
  public Collection getTongKQDChieu3(Vector vParam) throws Exception {
      Collection reval = null;
      try {
          String strSQL =" SELECT (SELECT count(*)\n" + 
          "  FROM sp_kq_dc3_ctiet a\n" + 
          "    where  mt_type='900'\n" + 
          "    and TRANG_THAI='00' and kq_id=?) as mt900thieu\n" + 
          "  ,\n" + 
          " (SELECT count(*)\n" + 
          "  FROM sp_kq_dc3_ctiet a\n" + 
          "    where  mt_type='900'\n" + 
          "    and TRANG_THAI='01' and kq_id=?)  as mt900thua\n" + 
          " ,\n" + 
          " (SELECT count(*) \n" + 
          "  FROM sp_kq_dc3_ctiet a\n" + 
          "    where  mt_type='910'\n" + 
          "    and TRANG_THAI='00' and kq_id=?) as mt910thieu, " + 
          " (SELECT count(*) \n" + 
          "  FROM sp_kq_dc3_ctiet a\n" + 
          "    where  mt_type='910'\n" + 
          "    and TRANG_THAI='01' and kq_id=?) as mt910thua" + 
          " FROM dual";
           reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,conn);
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(strValueObjectVO + ".getTongKQDChieu3(): " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }
  
  public int updateKQ(KQDChieu1VO vo) throws Exception{
    int exc=0;
      Vector v_param= new Vector();
      StringBuffer strSQL = new StringBuffer();
      strSQL.append("update sp_065 set ");
      strSQL.append(" trang_thai = ? ");
      v_param.add(new Parameter("01", true)); 

      if("0".equals(vo.getKet_qua_pht())){
        strSQL.append(", ket_qua = ? ");
        v_param.add(new Parameter("0", true)); 
      }
      else if(vo.getKhop_dung()==0){ // khop dung chenh lech so du
        strSQL.append(", ket_qua = ? ");
        v_param.add(new Parameter("0", true));
        strSQL.append(", ly_do_xn = ? ");
        v_param.add(new Parameter(vo.getLy_do_xn(), true));
      }
      else if(vo.getKhop_dung()==1){ // khop chenh lech
        strSQL.append(", ket_qua = ? ");
        v_param.add(new Parameter("1", true)); 
      }
      strSQL.append(" where trang_thai='00' and bk_id = ? ");
      v_param.add(new Parameter(vo.getBk_id(), true));
      exc = executeStatement(strSQL.toString(), v_param, conn);

    return exc;
  }
  public int updateTTBK(KQDChieu1VO vo) throws Exception{
    int exc=0;
      Vector v_param= new Vector();
      StringBuffer strSQL = new StringBuffer();
      strSQL.append("update sp_bk_dc1 set ");

      if("0".equals(vo.getKet_qua_pht())){
        strSQL.append(" trang_thai = ? ");
        v_param.add(new Parameter("02", true)); 
      }
      else if(vo.getKhop_dung()==0){ // khop dung chenh lech so du
        strSQL.append(" trang_thai = ? ");
        v_param.add(new Parameter("02", true));
      }
      else if(vo.getKhop_dung()==1){ // khop chenh lech
        strSQL.append(" trang_thai = ? ");
        v_param.add(new Parameter("01", true)); 
      }
      strSQL.append(" where trang_thai='00' and id = ? ");
      v_param.add(new Parameter(vo.getBk_id(), true));
      exc = executeStatement(strSQL.toString(), v_param, conn);

    return exc;
  }
}
