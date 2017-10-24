package com.seatech.ttsp.tsoSDuCOT;

import com.seatech.framework.datamanager.AppDAO;


import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.SelectStatementException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.KQDChieu1VO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Collections;
import java.util.Vector;
/*
 * modify name: ThuongDT
 * modify date: 14/11/2016
 * see: them moi ham getSoDuCOT(TSoSDuCOTVO vo) lay so du tai thoi diem COT
 * track: 20161114
 * 
 * modify name: ThuongDT
 * modify date: 09/02/2017
 * see: them moi ham getSoDuCOT(TSoSDuCOTVO vo) lay so du tai thoi diem COT
 * track: 20170109
 * 
 * modify name: ThuongDT
 * modify date: 15/02/2017
 * see: them moi ham getSoDu(TSoSDuCOTVO vo) lay so du 
 * track: 20170215
 * */

  
public class TSoSDuCOTDAO extends AppDAO {
  private String strValueObject = "com.seatech.ttsp.tsoSDuCOT.TSoSDuCOTDAO";
  private String strValueObjectVO = "com.seatech.ttsp.tsoSDuCOT.TSoSDuCOTVO";
  private String tSoKhoBacVO = "com.seatech.ttsp.tsoSDuCOT.TSoKhoBacVO";
  
  Connection conn = null;
  public TSoSDuCOTDAO(Connection conn) {
      this.conn = conn;
  }

  public int insertSDU(TSoSDuCOTVO vo) throws Exception {
      int nExc = 0;
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      StringBuffer strSQL2 = new StringBuffer();
      strSQL.append(" insert into sp_so_du (id ");
      strSQL2.append(" ) values (SP_SO_DU_SEQ.NEXTVAL ");

      strSQL.append(", ma_kb");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getMa_nh_kb(), true));

      strSQL.append(",  ma_nh");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getMa_nh(), true));

      strSQL.append(", ngay_gd");
      strSQL2.append(", sp_util_pkg.fnc_get_ngay_lam_viec_truoc(to_date(?,'DD/MM/YYYY') - INTERVAL '1' DAY)");
      v_param.add(new  Parameter (vo.getNgay_gd() , true));

      strSQL.append(",  so_du");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getSo_du(), true));
      
      strSQL.append(",  so_du_cot");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getSo_du(), true));
      
      strSQL.append(",  loai_tien");
      strSQL2.append(", ?");
      v_param.add(new Parameter(vo.getLoai_tien(), true));

      strSQL.append(",  insert_date");
      strSQL2.append(", SYSDATE ");
      
      

      strSQL.append(strSQL2);
      strSQL.append(")");

      nExc = executeStatement(strSQL.toString(), v_param, conn);

      return nExc;
  }
  //20161114 - HungBM - lay so du COT
  //20170109 - thuongdt bo sung lay the tu sp_065 trong truong hop khong tim thay o bang so du
  public Long getSoDuCOT(TSoSDuCOTVO vo) throws DAOException,
                                                  DatabaseConnectionFailureException,
                                                  SelectStatementException,
                                                  InstantiationException,
                                                  IllegalAccessException,
                                                  ClassNotFoundException {
    Long lReturn = 0l; 
    TSoSDuCOTVO sdVO = new TSoSDuCOTVO();
    String strSQL = "";
    try {
        strSQL = "select SO_DU_COT from SP_SO_DU WHERE MA_NH = '"+vo.getMa_nh() +"' AND MA_KB = '"+vo.getMa_nh_kb()+"' AND LOAI_TIEN = '"+vo.getLoai_tien()+"' AND TO_CHAR(NGAY_GD,'dd/MM/yyyy') = '"+vo.getNgay_gd()+"'";
      //  strSQL += whereClause ;
        sdVO =(TSoSDuCOTVO)findByPK(strSQL,strValueObjectVO);
        //20170109
        if(sdVO == null){
          strSQL = "select decode(SODU_KBNN,null,0,SODU_KBNN) SO_DU_COT from sp_065 where id in ( select max(id)  from sp_065 where send_bank = '"+vo.getMa_nh_kb()+"' and receive_bank  = '"+vo.getMa_nh() +"' and TO_CHAR(ngay_dc,'dd/MM/yyyy') = '"+vo.getNgay_gd()+"' and loai_tien = '"+vo.getLoai_tien()+"')";
          sdVO =(TSoSDuCOTVO)findByPK(strSQL,strValueObjectVO);
        }
      } catch (Exception ex) {
      }   
    if(sdVO!= null)
    lReturn = sdVO.getSo_du_cot();
   return lReturn;   
  }
  
  
  /*
   * @create: thuongdt
   * @create date: 15/02/2017
   * @see: lay thong tin so du trong ngay
   * @param: TSoSDuCOTVO object
   * @return: TSoSDuCOTVO
   * find: 20170215
   * */
  public TSoSDuCOTVO getSoDu(TSoSDuCOTVO vo) throws DAOException {
    Long lReturn = 0l; 
    TSoSDuCOTVO sdVO = new TSoSDuCOTVO();
    try {
        String strSQL = "select id,MA_KB,MA_NH,NGAY_GD,SO_DU,INSERT_DATE,SO_DU_COT,LOAI_TIEN from SP_SO_DU WHERE MA_NH = '"+vo.getMa_nh() +"' AND MA_KB = '"+vo.getMa_nh_kb()+"' AND LOAI_TIEN = '"+vo.getLoai_tien()+"' AND TO_CHAR(NGAY_GD,'dd/MM/yyyy') = '"+vo.getNgay_gd()+"'";
     
        sdVO =(TSoSDuCOTVO)findByPK(strSQL,strValueObjectVO);
       
      } catch (Exception ex) {
//      DAOException daoEx =
//          new DAOException(strValueObject + ".getTSoSDu(): " +
//                           ex.getMessage(), ex);
//      daoEx.printStackTrace();
//      throw daoEx;
      }   
   
   return sdVO;   
  }
  public int updateSDU(TSoSDuCOTVO vo) throws Exception {
    int exc = 0;
    Vector v_param = new Vector();
    StringBuffer strSQL = new StringBuffer();
    strSQL.append("update sp_so_du set insert_date=sysdate, ");
                                      
//    if(!"".equals(vo.getNgay_gd()) && vo.getNgay_gd()!=null){
//    strSQL.append(" ngay_gd = ?, ");
//       v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_gd(),
//                                                             "dd/MM/yyyy"),
//                                     true));
//     }
    if(!"".equals(vo.getSo_du()) && vo.getSo_du()!=null){
      strSQL.append(" so_du = ?, ");
      v_param.add(new Parameter(vo.getSo_du(), true));
       strSQL.append(" so_du_cot = ? ");
       v_param.add(new Parameter(vo.getSo_du(), true));
     }

    strSQL.append(" where ma_kb = ? and ma_nh =? and to_date(ngay_gd,'DD/MM/YYYY') = to_date(sp_util_pkg.fnc_get_ngay_lam_viec_truoc(to_date(?,'DD/MM/YYYY') - 1),'DD/MM/YYYY')");
    if(!"".equals(vo.getMa_nh_kb())&&vo.getMa_nh_kb()!=null){
      v_param.add(new Parameter(vo.getMa_nh_kb(), true));
    }
    if(!"".equals(vo.getMa_nh())&&vo.getMa_nh()!=null){
      v_param.add(new Parameter(vo.getMa_nh(), true));
    }
    if(!"".equals(vo.getNgay_gd()) && vo.getNgay_gd()!=null){
           v_param.add(new  Parameter( vo.getNgay_gd(), true));
         }
    
    exc = executeStatement(strSQL.toString(), v_param, conn);

    return exc;
  }
  
  public Collection getChkGhi(String whereClause, 
                              Vector params) throws Exception {
      Collection reval=null;
      try {
          String strSQL = "";
          strSQL += whereClause ;
          reval =
                  executeSelectStatement(strSQL.toString(), params, strValueObjectVO,
                                         conn);
        } catch (Exception ex) {
        DAOException daoEx =
            new DAOException(strValueObject + ".getChkGhi(): " +
                             ex.getMessage(), ex);
        daoEx.printStackTrace();
        throw daoEx;
        }
        return reval;
  }
  
    public Collection getTSoKhoBac(String whereClause, Vector params) throws Exception {
          Collection result=null;
          try {
              String strSQL = "SELECT TEN_TS FROM SP_THAMSO_KB WHERE 1=1 " + whereClause;
              result = executeSelectStatement(strSQL.toString(), params, tSoKhoBacVO, this.conn);
            } catch (Exception ex) {
                DAOException daoEx = new DAOException(strValueObject + ".getTSoKhoBac(): " + ex.getMessage(), ex);
                daoEx.printStackTrace();
                throw daoEx;
            }
            return result;
    }
    
    public int updateTSoKhoBac(TSoKhoBacVO kbVO,String name) {
      int result = -1;
      String insert1 = "INSERT INTO sp_thamso_kb " +
                       "VALUES('"+name+"','N','Cho phep cho khop dung trong doi chieu tong hop (Y/N)','Y',"+kbVO.getKb_id()+",SYSDATE,'QTHT-TW',NULL)";
      try{
          result += executeStatement(insert1, null, this.conn);
      } catch (Exception ex) {
        DAOException daoEx = new DAOException(strValueObject + ".updateTSoKhoBac(): " +   ex.getMessage(), ex); 
        daoEx.printStackTrace();
      }
      return result;
    }
}
