package com.seatech.ttsp.ttthanhtoan;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;

/*
 * @Modify: ThuongDT
 * @Modify date: 18/11/2016
 * @see: sua ham getListDS_TK_NHKB
 * @track: 20161118
 * */
public class TTThanhToanDAO extends AppDAO {
    private String strValueObject =
        "com.seatech.ttsp.ttthanhtoan.TTThanhToanVO";
    private String strValueObjectDMuc =
        "com.seatech.ttsp.ttthanhtoan.DMucNHVO";
  private String strValueObjectDSTK =
      "com.seatech.ttsp.ttthanhtoan.DSachTKhoanNHangKBacVO";
 
    Connection conn = null;

    public TTThanhToanDAO(Connection conn) {
      this.conn = conn;
    }

	
	//lay danh muc ngan hang
    public Collection getDMucNH(String where ,Vector vParam) throws Exception {

        Collection reval = null;
        try {

            StringBuffer strSQL = new StringBuffer();

            strSQL.append("SELECT a.ma_nh, a.ten_nh, a.ma_dv FROM sp_dm_nh_ho a where a.ma_dv <> '701' ");
            if(where != null && !"".equals(where)){
                strSQL.append(where);
            }
            strSQL.append(" order by  a.ma_nh");
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectDMuc,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectDMuc + ".getDMucNH(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

	//lay danh sach thong tin thanh toan
    public Collection getTHopTToan(String strWhere,
                                   Vector vParam) throws Exception {
        try {
            String strSQL =
                "select a.id, a.ten_kb, a.ma_kb, a.loai_tk, a.so_tk, a.ten_nh, a.ma_nh, a.han_muc_no, a.du_dau_ngay," +
                "a.ps_thu, a.ps_chi, a.chenh_lech  from " +
                "SP_TTIN_TTOAN a, SP_TRUYVAN_TTIN_TTOAN b where a.msg_refid=b.id  ";
            if (strWhere != null && !"".equals(strWhere)) {
                strSQL += strWhere;
            }
            strSQL += " order by a.id";
            return executeSelectStatement(strSQL, vParam, strValueObject,
                                          conn);
        } catch (Exception e) {
            throw e;
        }

    }

    public Collection getTHopTToan_ptrang(String strWhere, Vector vParam,
                                          Integer page, Integer count,
                                          Integer[] totalCount) throws Exception {
        try {
            String strSQL =
                "select a.id, a.ten_kb, a.ma_kb, a.loai_tk, a.so_tk, a.ten_nh, a.ma_nh, a.han_muc_no, a.du_dau_ngay," +
                "a.ps_thu, a.ps_chi, a.chenh_lech  from " +
                "SP_TTIN_TTOAN a, SP_TRUYVAN_TTIN_TTOAN b where a.msg_refid=b.id  ";
            if (strWhere != null && !"".equals(strWhere)) {
                strSQL += strWhere;
            }
            strSQL += " order by a.id";
            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           strValueObject, page, count,
                                           totalCount);
        } catch (Exception e) {
            throw e;
        }

    }


    public Collection getKQTToan(String strWhere,
                                 Vector vParam) throws Exception {
        try {
            String strSQL =
                "select a.id, a.ngay_tao, a.trang_thai, a.ma_kq, " +
                "(select ten from sp_dm_ngan_hang where ma_nh = a.nhkb_nhan) nhkb_nhan " +
                "from  SP_TRUYVAN_TTIN_TTOAN a where 1=1 ";
            if (strWhere != null && !"".equals(strWhere)) {
                strSQL += strWhere;
            }

            strSQL += " order by  a.ngay_tao desc ";
            return executeSelectStatement(strSQL, vParam, strValueObject,
                                          conn);
        } catch (Exception e) {
            throw e;
        }

    }


    public int getLanGui(String strWhere, Vector vParam) throws Exception {
        try {
            String strSQL =
                "select nvl(max(so_lan),0) so_lan  from  SP_TRUYVAN_TTIN_TTOAN  where 1=1 ";
            strSQL += strWhere;

            return executeStatement(strSQL.toString(), vParam, conn);


        } catch (Exception e) {
            throw e;
        }

    }

    public int insert(TTThanhToanVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        //Lấy ID từ seq

        strSQL.append("insert into SP_TRUYVAN_TTIN_TTOAN (");
        strSQL2.append(" values (");

        strSQL.append(" id");
        strSQL2.append("?");
        v_param.add(new Parameter(vo.getId(), true));

        strSQL.append(", nhkb_chuyen");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getNhkb_chuyen(), true));

        strSQL.append(", nhkb_nhan");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getNhkb_nhan(), true));

        strSQL.append(", nguoi_tao");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getNguoi_tao(), true));

        strSQL.append(", msg_id");
        strSQL2.append(", ?");
        v_param.add(new Parameter(vo.getMsg_id(), true));

        strSQL.append(", ngay_tao");
        strSQL2.append(", sysdate ");

        strSQL.append(", trang_thai");
        strSQL2.append(", 'P'");

        strSQL.append(", ma_kq");
        strSQL2.append(", null");

        strSQL.append(")");
        strSQL.append(strSQL2);
        strSQL.append(")");


        return executeStatement(strSQL.toString(), v_param, conn);

    }


    public DMucNHVO getMa_rcv(String strWhere,
                              Vector vParam) throws Exception {
        DMucNHVO dmucvo = null;
        try {
            String strSQL =
                "select a.ma_nh, a.ten_nh, a.ma_dv from sp_dm_nh_ho a where 1=1 ";
            strSQL += strWhere;

            dmucvo =
                    (DMucNHVO)findByPK(strSQL.toString(), vParam, strValueObjectDMuc,
                                       conn);


        } catch (Exception e) {
            throw e;
        }
        return dmucvo;
    }

    public Collection getDMucKB_cha(String strWhere,
                                    Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "select ma, ten, id, ma_cha, id_cha, cap from sp_dm_htkb where 1=1 ";
            strSQL += strWhere + " order by ma";

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObject,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObject + ".getDMucKB_cha(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public Collection getDMucKB_con(String strWhere,
                                    Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = null;

            strSQL +=
                    "select ma, ten, id, ma_cha, id_cha, cap from sp_dm_htkb where 1=1 ";
            strSQL += strWhere + "order by ma";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObject,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObject + ".getDMucKB_con(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

  public Collection getListNH(String strWhere, Vector vParam) throws Exception {

      Collection reval = null;
      try {

          String strSQL = "";

          strSQL += "select distinct c.ma_nh, c.ten " +
              " from sp_tknh_kb a, sp_dm_htkb b, sp_dm_ngan_hang c where " + 
              " a.kb_id = b.id and a.nh_id = c.id";
          strSQL += strWhere + " order by ma" ;
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, strValueObject,
                                         this.conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(strValueObject + ".getListNH(): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }
      return reval;
  }

  public Collection getListTKNHKB(String strWhere, Vector vParam) throws Exception {

        Collection reval = null;
        try {
            String strSQL = "";
            strSQL += "select distinct c.ma_nh, c.ten, a.so_tk, a.ma_nt loai_tien, a.loai_tk " +
                " from sp_tknh_kb a, sp_dm_htkb b, sp_dm_ngan_hang c where " + 
                " a.kb_id = b.id and a.nh_id = c.id";
            strSQL += strWhere;
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObject,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObject + ".getListTKNHKB(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public ResultSet getTHopTToanRS(String strWhere,
                                    Vector vParam) throws Exception {
        try {
            String strSQL =
                "select a.id, a.msg_refid, a.ten_kb, a.ma_kb, a.loai_tk, a.so_tk,a.ma_nh, a.han_muc_no, a.du_dau_ngay," +
                "a.ps_thu, a.ps_chi, a.chenh_lech, a.nhkb_chuyen, b.ngay_tao, a.ten_nh, a.nhkb_nhan  from " +
                "SP_TTIN_TTOAN a, SP_TRUYVAN_TTIN_TTOAN b where a.msg_refid=b.id  ";
            if (strWhere != null && !"".equals(strWhere)) {
                strSQL += strWhere;
            }
            strSQL += " order by a.id";
            return executeSelectStatement(strSQL.toString(), vParam, conn);
        } catch (Exception e) {
            throw e;
        }

    }
  public TTThanhToanVO getCap(String strWhere, Vector vParam) throws Exception {
    TTThanhToanVO dmucvo= null;
      try {
          String strSQL = "select cap from sp_dm_htkb where 1=1 ";
          strSQL += strWhere;
          dmucvo = (TTThanhToanVO)findByPK(strSQL.toString(), vParam,strValueObject, conn);
      } catch (Exception e) {
          throw e;
      }
     return dmucvo;
  }
  
  /**
   * @Modify: ThuongDT
   * @Modify date: 18/11/2016
   * @see: lay them trang_thai trong ket qua tra cuu
   * @find: 20161118
   * 
   * @Modify date: 24/05/2017
   * @see: check lai phan quyen DB doi voi user ttsp_app
   * @find: 20170524
   * */
  public Collection getListDS_TK_NHKB(String strWhere, Vector vParam , Integer page, Integer count,
                                               Integer[] totalCount) throws Exception {
        Collection reval = null;
        try {
          String strSQL = "";
                    strSQL += " select a.ten ten_huyen, b.ten ten_tinh,d.ma_nh,d.ten ten_nh,c.so_tk, decode(c.loai_tk,'01','TK tiền gửi','02','TK thanh toán','03','TK chuyên thu',' ') loai_tk, " +
                      " decode(c.loai_gd,'01','TTSP+PHT','02','TTSP','03','PHT',' ') loai_gd,to_char(c.hieu_luc_tungay,'dd/MM/yyyy') hieu_luc_tungay,to_char(c.hieu_luc_den_ngay,'dd/MM/yyyy')hieu_luc_den_ngay "+
                       " ,c.ma_nt loai_tien,(select bi_danh from sp_dm_nh_ho where SUBSTR(d.ma_nh,3,3) = ma_dv and ROWNUM = 1) bi_danh, " +
                       " decode(c.trang_thai,'01','Hoạt động','Ngừng hoạt động') trang_thai"+        
                      " from  sp_dm_htkb a LEFT JOIN sp_dm_htkb b on a.id_cha = b.id,sp_tknh_kb c,sp_dm_ngan_hang d " + 
                      " where c.kb_id = a.id and c.nh_id = d.id  ";
                      strSQL += strWhere + "  order by d.ten ";            
          reval = executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                         strValueObjectDSTK, page, count,
                                         totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectDSTK + ".getListDS_TK_NHKB(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

}
