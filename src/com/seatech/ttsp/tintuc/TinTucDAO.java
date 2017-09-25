package com.seatech.ttsp.tintuc;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class TinTucDAO extends AppDAO {
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.tintuc.TinTucDAO";
    Connection conn = null;
    private String strValueObject = "com.seatech.ttsp.tintuc.TinTucVO";

    public TinTucDAO(Connection conn) {
        this.conn = conn;
    }

    public int insertTinTuc(TinTucVO vo) throws Exception {
        int nExc = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        strSQL.append(" insert into sp_thong_bao (id ");
        strSQL2.append(" ) values (sp_thong_bao_seq.NEXTVAL ");

        strSQL.append(", tieu_de");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getTieu_de(), true));

        strSQL.append(",  noi_dung");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getNoi_dung(), true));

        strSQL.append(", ngay_tao");
        strSQL2.append(", SYSDATE");

        strSQL.append(",  ngay_dang");
        strSQL2.append(", ? ");
        v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_dang(),
                                                              "dd/MM/yyyy"),
                                      true));

        strSQL.append(",  ngay_het_han");
        strSQL2.append(", ? ");
        v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_het_han(),
                                                              "dd/MM/yyyy"),
                                      true));

        strSQL.append(",  nguoi_tao");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getNguoi_tao(), true));

        strSQL.append(",  trang_thai");
        strSQL2.append(", ? ");
        v_param.add(new Parameter(vo.getTrang_thai(), true));

        strSQL.append(",  dv_dang");
        strSQL2.append(", ? ");
        v_param.add(new Parameter(vo.getDv_dang(), true));


        strSQL.append(strSQL2);
        strSQL.append(")");

        nExc = executeStatement(strSQL.toString(), v_param, conn);

        return nExc;
    }

    public TinTucVO getTinTuc(String strWhere,
                              Vector params) throws Exception {
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT a.id, a.tieu_de, a.noi_dung, a.ngay_tao, to_char(a.ngay_dang,'DD/MM/YYYY') ngay_dang," +
                    " to_char(a.ngay_het_han,'DD/MM/YYYY') ngay_het_han, a.nguoi_tao, a.trang_thai, a.dv_dang " +
                    "  FROM sp_thong_bao a where 1=1 " + strWhere + " ORDER BY   to_char(a.ngay_tao,'YYYYMMDD')desc,to_char(a.ngay_dang,'YYYYMMDD') desc";
            TinTucVO newVO =
                (TinTucVO)findByPK(strSQL.toString(), params, strValueObject,
                                   conn);
            return newVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObject + ".getTinTuc(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public Collection getColTinTuc(String strWhere,
                                   Vector params) throws Exception {
        Collection reval = null;
        try {
            String strSQL = "";
            strSQL +=
                    " SELECT a.id, a.tieu_de, a.noi_dung, a.ngay_tao, a.ngay_dang," +
                    " a.ngay_het_han, a.nguoi_tao, a.trang_thai, a.dv_dang " +
                    "  FROM sp_thong_bao a where 1=1 " + strWhere + " ORDER BY   to_char(a.ngay_tao,'YYYYMMDD') desc";
            reval =
                    executeSelectStatement(strSQL.toString(), params, strValueObject,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObject + ".getColTinTuc(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }


    public Collection getLstTinTuc(String strWhere,
                                   Vector vParam, Integer page,
                                            Integer count,
                                            Integer[] totalCount) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";
            strSQL +=
                    "SELECT a.id, a.tieu_de, a.noi_dung, a.ngay_tao, to_char(a.ngay_dang,'DD/MM/YYYY') ngay_dang," +
                    " to_char(a.ngay_het_han,'DD/MM/YYYY') ngay_het_han, a.nguoi_tao, a.trang_thai, a.dv_dang, b.ma_nsd " +
                    " FROM sp_thong_bao a, sp_nsd b where a.nguoi_tao=b.id";

            strSQL += strWhere + " ORDER BY   to_char(a.ngay_tao,'YYYYMMDD') desc,to_char(a.ngay_dang,'YYYYMMDD') desc, a.tieu_de desc";
          return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                         strValueObject, page, count,
                                         totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObject + ".getLstTinTuc(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

  public int updateTTuc(TinTucVO vo) throws Exception {
      int exc = 0;
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      strSQL.append("update sp_thong_bao set ");
      
      if(vo.getTrang_thai()!=null && !"".equals(vo.getTrang_thai())){
        strSQL.append(" trang_thai = ? ");
        v_param.add(new Parameter(vo.getTrang_thai(), true));
      }
      if(vo.getNguoi_tao()!=null && !"".equals(vo.getNguoi_tao())){
        strSQL.append(", nguoi_tao = ? ");
        v_param.add(new Parameter(vo.getNguoi_tao(), true));
      }
      if(vo.getTieu_de()!=null && !"".equals(vo.getTieu_de())){
        strSQL.append(", tieu_de = ? ");
        v_param.add(new Parameter(vo.getTieu_de(), true));
      }
    if(vo.getNoi_dung()!=null && !"".equals(vo.getNoi_dung())){
      strSQL.append(", noi_dung = ? ");
      v_param.add(new Parameter(vo.getNoi_dung(), true));
    }
    if(vo.getNgay_dang()!=null && !"".equals(vo.getNgay_dang())){
      strSQL.append(", ngay_dang = ? ");
      v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_dang(),
                                                            "dd/MM/yyyy"),
                                    true));
    }
    if(vo.getNgay_het_han()!=null && !"".equals(vo.getNgay_het_han())){
      strSQL.append(", ngay_het_han = ? ");
      v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_het_han(),
                                                            "dd/MM/yyyy"),
                                    true));
    }
    if(vo.getDv_dang()!=null && !"".equals(vo.getDv_dang())){
      strSQL.append(", dv_dang = ? ");
      v_param.add(new Parameter(vo.getDv_dang(), true));
    }
    
      strSQL.append(" where id = ? ");
      v_param.add(new Parameter(vo.getId(), true));
      exc = executeStatement(strSQL.toString(), v_param, conn);

      return exc;
  }

}
