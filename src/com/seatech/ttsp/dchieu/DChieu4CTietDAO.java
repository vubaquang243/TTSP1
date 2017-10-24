package com.seatech.ttsp.dchieu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class DChieu4CTietDAO extends AppDAO {
  private String strValueObject ="com.seatech.ttsp.dchieu.DChieu4CTietDAO";
  private String strValueObjectVO ="com.seatech.ttsp.dchieu.DChieu4CTietVO";
  Connection conn = null;

  public DChieu4CTietDAO(Connection conn) {
      this.conn = conn;
  }


  public Collection getDChieuList(String strWhere, Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL ="";

          strSQL +=" SELECT a.id, a.lan_dc, a.ngay_dc, a.send_bank, a.receive_bank,\n" + 
          "       a.created_date, a.creator, a.manager, a.verified_date,\n" + 
          "       a.sodu_daungay, a.ketchuyen_chi, a.ps_thu, a.hanmuc,\n" + 
          "       a.ketchuyen_thu, a.sodu_cuoingay, a.insert_date, a.trang_thai\n" + 
          "  FROM sp_bk_dc2 a WHERE 1=1 ";
          
          strSQL += strWhere + " ORDER BY a.trang_thai asc ,a.insert_date, a.lan_dc desc ";
           reval = executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,conn);
      } catch (Exception ex) {
          DAOException daoEx =new DAOException(strValueObjectVO + ".getDChieuList(): " +
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
