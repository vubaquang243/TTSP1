package com.seatech.ttsp.nhomnsd;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;


public class NhomNSDDAO extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO = "com.seatech.ttsp.nhomnsd.NhomNSDDAO";
    private String CLASS_NAME_VO = "com.seatech.ttsp.nhomnsd.NhomNSDVO";
    private static String STRING_EMPTY = "";

    public NhomNSDDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach cac nhom NSD
     * @return: Collection
     * */
    public Collection getNhomNSDList(String whereClause,
                                     Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ten_nhom, a.loai_nhom  FROM sp_nhom_nsd a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY a.ten_nhom DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getNhomNSDList(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }
  public ResultSet getNhomNSDList(String whereClause) throws Exception {
      ResultSet reval = null;
      Vector params = null;
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT a.id, a.ten_nhom, a.loai_nhom  FROM sp_nhom_nsd a");

          if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
              strSQL.append(" WHERE " + whereClause);
          }
          strSQL.append(" ORDER BY a.id DESC ");
          reval =
                  executeSelectStatement(strSQL.toString(), params, conn);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getNhomNSDList(): " +
                                 ex.getMessage(), ex);
      }
      return reval;
  }
    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra thong tin nhom NSD
     * @return: NhomNSDVO
     * */
    public NhomNSDVO getNhomNSD(String whereClause,
                                Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ten_nhom, a.loai_nhom  FROM sp_nhom_nsd a");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (NhomNSDVO)findByPK(strSQL.toString(), params,
                                       CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getNhomNSD(): " +
                                   ex.getMessage(), ex);
        }
    }

    /**
     * @param: ID NSD
     * @see: Lay ra thong tin nhom NSD
     * @return: NhomNSDVO
     * */
    public Collection getNhomNSDListByUserID(Long nUserID) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ten_nhom, a.loai_nhom  FROM sp_nhom_nsd a inner join sp_nsd_nhom b ");
            strSQL.append("on a.id = b.nhom_id and b.nsd_id = ?");
            Vector vParam = new Vector();
            vParam.add(new Parameter(nUserID, true));
            return executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getNhomNSDListByUserID(): " +
                                   ex.getMessage(), ex);
        }
    }


    /**
     * @param: ID_NSD, id_nhom
     * @see: Lay ra thong tin nhom NSD
     * @return: NhomNSDVO
     * */
    public Collection getNhomNSDListByUserID(Long nUserID,
                                             Long nhom_id) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ten_nhom, a.loai_nhom  FROM sp_nhom_nsd a inner join sp_nsd_nhom b ");
            strSQL.append("on a.id = b.nhom_id and b.nsd_id = ?");
            Vector vParam = new Vector();
            vParam.add(new Parameter(nUserID, true));
            strSQL.append(" and nhom_id = ?");
            vParam.add(new Parameter(nhom_id, true));
            return executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getNhomNSDListByUserID(): " +
                                   ex.getMessage(), ex);
        }
    }

    /**
     * @param:
     * @return: 0: ko thanh cong; tra ve ID cua NNSD vua dc tao neu thanh cong
     * @see:
     * */
    public Long insert(NhomNSDVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        //L?y ID t? seq
        Long lID = getSeqNextValue("sp_nhom_nsd_seq", conn);
        strSQL.append("insert into sp_nhom_nsd (id ");
        strSQL2.append(") values (? ");
        v_param.add(new Parameter(lID, true));

        if (vo.getLoai_nhom() != null) {
            strSQL.append(", loai_nhom");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getLoai_nhom(), true));
        }
        if (vo.getTen_nhom() != null) {
            strSQL.append(", ten_nhom");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getTen_nhom(), true));
        }

        strSQL.append(strSQL2);
        strSQL.append(")");
        if (executeStatement(strSQL.toString(), v_param, conn) == 1)
            return lID;
        else
            return new Long("0");
    }

    /**
     * @param:
     * @return: 1: update thanh cong; 0: Update khong thanh cong
     * @see:
     * */
    public int update(NhomNSDVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("update sp_nhom_nsd set ");
        int nExc = 0;
        if (vo.getTen_nhom() != null) {
            strSQL.append(" ten_nhom = ? ");
            v_param.add(new Parameter(vo.getTen_nhom(), true));
        }
        if (vo.getLoai_nhom() != null) {
            strSQL.append(", loai_nhom = ? ");
            v_param.add(new Parameter(vo.getLoai_nhom(), true));
        }

        strSQL.append(" where id = ? ");
        v_param.add(new Parameter(vo.getId(), true));
        nExc = executeStatement(strSQL.toString(), v_param, conn);

        return nExc;
    }

    /**
     * @param: Long
     * @return: 1: xoa thanh cong; 0: Xoa khong thanh cong
     * @see: Xoa  nhom NSD
     * */
    public int delete(Long id) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" delete sp_nhom_nsd where id = ?");
        int nExc = 0;
        if (id != null) {
            v_param.add(new Parameter(id, true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
        }
        return nExc;
    }

    //bang sp_nsd_nhom

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra thong tin nhom NSD
     * @return: NhomNSDVO
     * */
    public NhomNSDVO getNSDNhom(String whereClause,
                                Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.nsd_id, a.nhom_id " );
            strSQL.append("FROM sp_nsd_nhom a");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (NhomNSDVO)findByPK(strSQL.toString(), params,
                                       CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getNSDNhom(): " +
                                   ex.getMessage(), ex);
        }
    }

    /**
     * @param: ID_NSD, id_nhom
     * @see: Lay ra thong tin nhom NSD
     * @return: NhomNSDVO
     * */
    public NhomNSDVO getNSDNhomByUserID(Long nUserID,
                                        Long nhom_id) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.nsd_id, a.nhom_id, a.created_date, a.created_by");
            strSQL.append("FROM sp_nsd_nhom a");
            Vector vParam = new Vector();
            strSQL.append("where a.id = ?");
            vParam.add(new Parameter(nUserID, true));
            strSQL.append(" and nhom_id = ?");
            vParam.add(new Parameter(nhom_id, true));
            return (NhomNSDVO)findByPK(strSQL.toString(), vParam,
                                       CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getNSDNhomByUserID(): " +
                                   ex.getMessage(), ex);
        }
    }
    
    
  /**
   * @param: Long
   * @return: 1: xoa thanh cong; 0: Xoa khong thanh cong
   * @see: Xoa  nhom NSD
   * */
  public int deleteNsd_nhom(String strwhere, Vector v_param) throws Exception {
    try {
      v_param = new Vector();
      int nExc = 0;
      StringBuffer strSQL = new StringBuffer();
      strSQL.append(" delete sp_nsd_nhom ");
      if(strwhere != null)
      {
            strSQL.append("where 1=1 "+ strwhere);
            nExc = executeStatement(strSQL.toString(), v_param, conn);
          }
      return nExc;
    } catch (Exception ex) {
        throw new DAOException(CLASS_NAME_DAO + ".getNSDNhom(): " +
                               ex.getMessage(), ex);
    }
}
}