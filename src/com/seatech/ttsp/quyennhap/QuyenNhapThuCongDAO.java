package com.seatech.ttsp.quyennhap;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;


public class QuyenNhapThuCongDAO extends AppDAO {
    private String CLASS_NAME_VO =
        "com.seatech.ttsp.quyennhap.QuyenNhapThuCongVO";
    private String CLASS_NAME_DAO =
        "com.seatech.ttsp.quyennhap.QuyenNhapThuCongDAO";
    private static final String STRING_EMPTY = "";
    private Connection conn = null;

    public QuyenNhapThuCongDAO(Connection conn) {
        this.conn = conn;
    }

    private void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * @see: Get list TTV
     * */
    public Collection getNSDList(String strWhere, 
                                 Vector v_param) throws Exception {

        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("select distinct(a.id)  nsd_id, a.ma_nsd, a.ten_nsd, ");
            strSQL.append("(SELECT count(b.nsd_id) FROM sp_nsd_co_qnhap b where a.id = b.nsd_id)trang_thai_chon");
            strSQL.append(" from sp_nsd a ");
            strSQL.append(" INNER JOIN sp_nsd_nhom b ON b.nsd_id = a.id ");
            strSQL.append(" INNER JOIN sp_nhom_nsd c ON b.nhom_id = c.id");

            if (strWhere != null && !strWhere.equals("")) {
                strSQL.append(" where (1=1) ");
                strSQL.append(strWhere); //and c.loai_nhom = ? (TTV)
            }
            return executeSelectStatement(strSQL.toString(), v_param,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getTTVList(strWhere, v_param): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }

    }


    /**
     * @see: Get list TTV
     * */
    public Collection getLSuQNhapList(String strWhere,
                                      Vector v_param) throws Exception {

        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("select a.id, a.nsd_id, a.tu_ngay, a.den_ngay, a.nguoi_gan, a.lido_gan, a.nguoi_rut, a.lido_rut ");
            strSQL.append(" from sp_nsd_co_qnhap_lsu a");
            if (strWhere != null && !strWhere.equals("")) {
                strSQL.append(" where (1=1) ");
                strSQL.append(strWhere);
            }
            return executeSelectStatement(strSQL.toString(), v_param,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getTTV_CoQNhap_LSu(strWhere, v_param): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }

    }


    /**
     * @see: Insert TTV co quyen nhap
     * */
    public int insertQNhap(QuyenNhapThuCongVO vo) throws Exception {
        try {
            Vector v_param = new Vector();
            StringBuffer strSQL = new StringBuffer();
            StringBuffer strSQL2 = new StringBuffer();
            //Lay id tu seq
            Long id = getSeqNextValue("sp_nsd_co_qnhap_seq", conn);
            strSQL.append("insert into sp_nsd_co_qnhap (id ");
            strSQL2.append(") values (? ");
            v_param.add(new Parameter(id, true));
            strSQL.append(", nsd_id");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getNsd_id(), true));
            strSQL.append(strSQL2);
            strSQL.append(")");
            return executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".insertQNhap(TTVCoQNhapVO vo): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }

    }

    /**
     * @see: Insert TTV co quyen nhap lich su
     * */

    public int insertLSuQNhap(QuyenNhapThuCongVO vo) throws Exception {
        try {
            Vector v_param = new Vector();
            StringBuffer strSQL = new StringBuffer();
            StringBuffer strSQL2 = new StringBuffer();
            //Lay id tu seq
            Long id = getSeqNextValue("sp_nsd_co_qnhap_lsu_seq", conn);
            strSQL.append("insert into sp_nsd_co_qnhap_lsu (id ");
            strSQL2.append(") values (? ");
            v_param.add(new Parameter(id, true));

            strSQL.append(", nsd_id");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getNsd_id(), true));

            strSQL.append(", tu_ngay");
            strSQL2.append(", sysdate");

            strSQL.append(", nguoi_gan ");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getNguoi_gan(), true));

            strSQL.append(strSQL2);
            strSQL.append(")");
            return executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ". insertTTV_CoQNhap_LSu(TTVCoQNhapVO vo) vo: " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    /**
     * @param: Long
     * @return: 1: xoa thanh cong; 0: Xoa khong thanh cong
     * @see: Xoa TTV  trong sp_nsd_co_qnhap
     * */
    public int delete(String where) throws Exception {
        try {
            Vector v_param = new Vector();
            StringBuffer strSQL = new StringBuffer();
            strSQL.append(" delete sp_nsd_co_qnhap ");
            int nExc = 0;
            if(where != null)
            {
                  strSQL.append(" WHERE "+where);
                }
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            return nExc;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ". delete(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
    }


    /**
     * @param: QuyenNhapThuCongVO
     * @return: 1: update thanh cong; 0: Update khong thanh cong
     * @see: update TTV trong bang sp_nsd_co_qnhap_lsu
     * */
    public int update(QuyenNhapThuCongVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_nsd_co_qnhap_lsu set ");
        int nExc = 0;
        if (vo.getNguoi_rut() != null) {
            strSQL.append(" den_ngay = sysdate ,  ");
            strSQL.append(" nguoi_rut = ? ");
            v_param.add(new Parameter(vo.getNguoi_rut(), true));
            strSQL.append(" where nsd_id not in ");
            strSQL.append("(select nsd_id from sp_nsd_co_qnhap ) ");
            strSQL.append("and den_ngay is null");
            nExc = executeStatement(strSQL.toString(), v_param, conn);

        }

        return nExc;
    }


    // LSu QUyen Nhap


    /**
     * @see: Get list TTV
     * */
    public Collection getLSDQNhapList(String strWhere,
                                      Vector v_param) throws Exception {

        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("SELECT a.id, a.nsd_id,b.ten_nsd, a.tu_ngay, a.den_ngay, a.nguoi_gan,b1.ten_nsd, a.lido_gan,");
            strSQL.append(" a.nguoi_rut,b2.ten_nsd, a.lido_rut ");
            strSQL.append(" FROM sp_nsd_co_qnhap_lsu a ");
            strSQL.append(" LEFT JOIN sp_nsd b ");
            strSQL.append(" on a.nsd_id=b.id ");
            strSQL.append(" LEFT JOIN sp_nsd b1 ");
            strSQL.append(" on  a.nguoi_gan= b1.id ");
            strSQL.append(" LEFT JOIN sp_nsd b2 ");
            strSQL.append(" on  a.nguoi_rut = b2.id ");
            if (strWhere != null && !strWhere.equals("")) {
                strSQL.append(" where (1=1) ");
                strSQL.append(strWhere); //and c.loai_nhom = ? (TTV)
            }
            return executeSelectStatement(strSQL.toString(), v_param,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLSDQNhapList(strWhere, v_param): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }

    }


    /**
     * @see: Get list TTV
     * */
    public Collection getLSuQNhapList(String strWhere, Vector v_param,
                                       Integer page, Integer count,
                                       Integer[] totalCount) throws Exception {

        try {
            StringBuffer strSQL = new StringBuffer();
          strSQL.append("SELECT a.id, a.nsd_id,b.ma_nsd, a.tu_ngay, a.den_ngay, a.nguoi_gan,(b1.ten_nsd)ten_nguoi_gan, a.lido_gan,");
          strSQL.append(" a.nguoi_rut,(b2.ten_nsd)ten_nguoi_rut, a.lido_rut ");
          strSQL.append(" FROM sp_nsd_co_qnhap_lsu a ");
          strSQL.append(" LEFT JOIN sp_nsd b ");
          strSQL.append(" on a.nsd_id=b.id ");
          strSQL.append(" LEFT JOIN sp_nsd b1 ");
          strSQL.append(" on  a.nguoi_gan= b1.id ");
          strSQL.append(" LEFT JOIN sp_nsd b2 ");
          strSQL.append(" on  a.nguoi_rut = b2.id ");
            if (strWhere != null && !strWhere.equals("")) {
                strSQL.append(" where (1=1) ");
                strSQL.append(strWhere);
            }
            return executeSelectWithPaging(conn, strSQL.toString(), v_param,
                                           CLASS_NAME_VO, page, count,totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getTTV_CoQNha p_LSu(strWhere, v_param): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }

    }
  public ResultSet getLSuQNhapResultSet(String strWhere, Vector v_param,
                                     Integer page, Integer count,
                                     Integer[] totalCount) throws Exception {

      try {
          StringBuffer strSQL = new StringBuffer();
        strSQL.append("SELECT a.id, a.nsd_id,b.ma_nsd, to_char(a.tu_ngay,'dd/mm/yyyy HH24:MI:SS') tu_ngay, nvl(to_char(a.den_ngay,'dd/mm/yyyy HH24:MI:SS'),' ') den_ngay, a.nguoi_gan,(b1.ten_nsd)ten_nguoi_gan, nvl(a.lido_gan,' ') lido_gan,");
        strSQL.append(" nvl(a.nguoi_rut,'') nguoi_rut,nvl(b2.ten_nsd,' ') ten_nguoi_rut, nvl(a.lido_rut,' ') lido_rut ");
        strSQL.append(" FROM sp_nsd_co_qnhap_lsu a ");
        strSQL.append(" LEFT JOIN sp_nsd b ");
        strSQL.append(" on a.nsd_id=b.id ");
        strSQL.append(" LEFT JOIN sp_nsd b1 ");
        strSQL.append(" on  a.nguoi_gan= b1.id ");
        strSQL.append(" LEFT JOIN sp_nsd b2 ");
        strSQL.append(" on  a.nguoi_rut = b2.id ");
          if (strWhere != null && !strWhere.equals("")) {
              strSQL.append(" where (1=1) ");
              strSQL.append(strWhere);
          }
          return executeSelectStatement(strSQL.toString(), v_param, conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_DAO + ".getLSuQNhapResultSet(strWhere, v_param): " +
                               ex.getMessage(), ex);
          daoEx.printStackTrace();
          throw daoEx;
      }

  }

}
