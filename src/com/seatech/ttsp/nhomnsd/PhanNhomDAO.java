package com.seatech.ttsp.nhomnsd;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;


public class PhanNhomDAO extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO = "com.seatech.ttsp.nhomnsd.PhanNhomDAO";
    private String CLASS_NAME_VO = "com.seatech.ttsp.nhomnsd.PhanNhomVO";
    private static String STRING_EMPTY = "";

    public PhanNhomDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: QuanLyNSDVO
     * @return: 1: them moi thanh cong; 0: them moi khong thanh cong
     * @see: Them moi Nsd_nhom
     * */
    public int insertNsd_nhom(PhanNhomVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        //Lay id tu seq
        Long id = getSeqNextValue("sp_nsd_nhom_seq", conn);
        strSQL.append("insert into sp_nsd_nhom (id , nsd_id, nhom_id,CREATED_DATE,CREATED_BY");
        strSQL.append(") values (?");
        v_param.add(new Parameter(id, true));
        strSQL.append(",?");
        v_param.add(new Parameter(vo.getNsd_id(), true));
        strSQL.append(",?");
        v_param.add(new Parameter(vo.getNhom_id(), true));
        strSQL.append(",?");
        v_param.add(new DateParameter(new Date(), true));
        strSQL.append(",?");
        v_param.add(new Parameter(vo.getNguoi_tao(), true));
        strSQL.append(")");
        return executeStatement(strSQL.toString(), v_param, conn);
    }


    /**
     * @param:
     * @return:
     * @see:
     * */
    public int deleteNsd(Long id) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" delete sp_nsd where id = ?");
        int nExc = 0;
        if (id != null) {
            v_param.add(new Parameter(id, true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
        }
        return nExc;
    }


    /**
     * @param:
     * @return:
     * @see:
     * */
    public int deleteNsd_nhom(Long nsd_id) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" delete sp_nsd_nhom where nsd_id = ?");
        int nExc = 0;
        if (nsd_id != null) {
            v_param.add(new Parameter(nsd_id, true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
        }
        return nExc;
    }


    /**
     * @param:
     * @return: 1: update thanh cong; 0: Update khong thanh cong
     * @see:
     * */
    public int update(PhanNhomVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_nsd set ");
        int nExc = 0;
        if (vo.getTen_nsd() != null) {
            strSQL.append(" ten_nsd = ? ");
            v_param.add(new Parameter(vo.getTen_nsd(), true));
        }
        if (vo.getChuc_danh() != null) {
            strSQL.append(", chuc_danh = ? ");
            v_param.add(new Parameter(vo.getChuc_danh(), true));
        }
        if (vo.getTrang_thai() != null) {
            strSQL.append(", trang_thai = ? ");
            v_param.add(new Parameter(vo.getTrang_thai(), true));
        }
        strSQL.append(" where id = ? ");
        v_param.add(new Parameter(vo.getId(), true));
        nExc = executeStatement(strSQL.toString(), v_param, conn);

        return nExc;
    }

    /**
     * @param: Long
     * @return: 1: xoa thanh cong; 0: Xoa khong thanh cong
     * @see: Xoa nsd_nhom
     * */
    public int delete(Long id) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" delete sp_nsd_nhom where nsd_id = ? ");
        int nExc = 0;
        if (id != null) {
            v_param.add(new Parameter(id, true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
        }
        return nExc;
    }

    /**
     * @param: Long
     * @return: 1: xoa thanh cong; 0: Xoa khong thanh cong
     * @see: Xoa nsd_nhom
     * */
    public int delete(Long nNsdID, Long nNhomID) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" delete sp_nsd_nhom where nsd_id = ? and nhom_id = ?");
        int nExc = 0;
        if (nNsdID != null) {
            v_param.add(new Parameter(nNsdID, true));
            v_param.add(new Parameter(nNhomID, true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
        }
        return nExc;
    }
        
  /**
   * @param: Menh de where; vector tham so
   * @see: Lay ra thong tin nhom NSD
   * @return: Collection
   * */
  public Collection getNSDNhom(String whereClause,
                              Vector params) throws Exception {
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT a.id, a.nsd_id, a.nhom_id, a.created_date, a.created_by FROM sp_nsd_nhom a");
          if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
              strSQL.append(" WHERE " + whereClause);
          }
          return executeSelectStatement(strSQL.toString(), params,CLASS_NAME_VO,
                                      conn);
        
          
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getNSDNhom(): " +
                                 ex.getMessage(), ex);
      }
  }
  
  /**
   * @param: Menh de where; vector tham so
   * @see: Lay ra thong tin nhom NSD
   * @return: PhanNhomVO
   * */
  public PhanNhomVO getNhomNSD(String whereClause,
                              Vector params) throws Exception {
      StringBuffer strSQL = new StringBuffer();
      try {
        strSQL.append("SELECT a.id, a.nsd_id, a.nhom_id, a.created_date, a.created_by FROM sp_nsd_nhom a");
        if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
            strSQL.append(" WHERE " + whereClause);
        }
        return (PhanNhomVO)findByPK(strSQL.toString(), params,CLASS_NAME_VO, conn);

      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getNhomNSD(): " +
                                 ex.getMessage(), ex);
      }
  }

  
}



