package com.seatech.ttsp.lstruycap;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.ttsp.chucnang.ChucNangDAO;
import com.seatech.ttsp.chucnang.ChucNangVO;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;


public class LSuTruyCapDAO extends AppDAO {
    Connection conn = null;
    private String CLASS_NAME_DAO = "com.seatech.ttsp.lstruycap.LSuTruyCapDAO";
    private String CLASS_NAME_VO = "com.seatech.ttsp.lstruycap.LSuTruyCapVO";
    private String STRING_EMPTY = "";

    public LSuTruyCapDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach Lich su truy cap
     * @return: Collection
     * */
    public Collection getLSuTruyCapList(String whereClause,
                                        Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.nsd_id, a.cnang_id, a.ip_tcap, a.tgian_tcap, a.mota, a.user_may_tcap, a.ten_may_tcap ");
            strSQL.append("FROM sp_lsu_truycap a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLSuTruyCapList(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

        return reval;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach Lich su truy cap co phan trang
     * @return: Collection
     * */
    public Collection getLSuTruyCapList(String strWhere, Vector vParam,
                                        Integer page, Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("Select b.ten_nsd, b.ma_nsd,b.chuc_danh, a.id, a.nsd_id,c.ma_cnang,c.ten_cnang,");
            strSQL.append("a.cnang_id, a.ip_tcap, TO_CHAR(a.tgian_tcap,'DD-MM-YYYY HH:MM:SS')tgian_tcap, a.mota, a.user_may_tcap, a.ten_may_tcap ");
            strSQL.append(" FROM sp_lsu_truycap a");
            strSQL.append(" INNER JOIN sp_nsd b ");
            strSQL.append(" ON a.nsd_id = b.id");
            strSQL.append(" INNER JOIN sp_chucnang c");
            strSQL.append(" ON c.id = a.cnang_id");
            if (strWhere != null && !STRING_EMPTY.equals(strWhere)) {
                strSQL.append(" where 1=1" + strWhere);
            }
            strSQL.append(" ORDER BY a.id DESC ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLSuTruyCapList(strWhere,vParam,page,count,totalCount) " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

        return reval;
    }
  public ResultSet getLSuTruyCapResultSet(String strWhere, Vector vParam) throws Exception {
      ResultSet reval = null;
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("Select b.ten_nsd, b.ma_nsd,b.chuc_danh, a.id, a.nsd_id,c.ma_cnang,c.ten_cnang,");
          strSQL.append("a.cnang_id, a.ip_tcap, TO_CHAR(a.tgian_tcap,'DD-MM-YYYY HH:MM:SS')tgian_tcap, nvl(a.mota,' ') mota, nvl(a.user_may_tcap,' ') user_may_tcap, nvl(a.ten_may_tcap,' ') ten_may_tcap ");
          strSQL.append(" FROM sp_lsu_truycap a");
          strSQL.append(" INNER JOIN sp_nsd b ");
          strSQL.append(" ON a.nsd_id = b.id");
          strSQL.append(" INNER JOIN sp_chucnang c");
          strSQL.append(" ON c.id = a.cnang_id");
          if (strWhere != null && !STRING_EMPTY.equals(strWhere)) {
              strSQL.append(" where 1=1" + strWhere);
          }
          strSQL.append(" ORDER BY a.id DESC ");
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, conn);
      } catch (Exception ex) {
          DAOException daoEx =
              new DAOException(CLASS_NAME_DAO + ".getLSuTruyCapList(strWhere,vParam,page,count,totalCount) " +
                               ex.getMessage(), ex);
//          daoEx.printStackTrace();
          throw daoEx;
      }

      return reval;
  }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra thong tin Lich su truy cap
     * @return: LSuTruyCapVO
     * */
    public LSuTruyCapVO getLSuTruyCap(String whereClause,
                                      Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.nsd_id, a.cnang_id, a.ip_tcap, a.tgian_tcap, a.mota, a.user_may_tcap, a.ten_may_tcap ");
            strSQL.append("FROM sp_lsu_truycap a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (LSuTruyCapVO)findByPK(strSQL.toString(), params,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLSuTruyCap(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    /**
     * @param: LSuTruyCapVO
     * @return: int "1" = thanh cong; "khac 1" = khong thanh cong
     * @see: Them moi DTS
     * */
    public int insert(LSuTruyCapVO vo) throws Exception {
        Vector v_param = new Vector();

        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        try {
            Long nID = getSeqNextValue("SP_LSU_TRUYCAP_SEQ", conn);

            strSQL.append("insert into SP_LSU_TRUYCAP (id ");
            strSQL2.append(" ) values ( ");
            strSQL2.append("? ");
            v_param.add(new Parameter(nID, true));

            if (vo.getNsd_id() != null) {
                strSQL.append(", nsd_id");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getNsd_id(), true));
            }
            if (vo.getCnang_id() != null) {
                strSQL.append(", cnang_id");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getCnang_id(), true));
            }
            if (vo.getIp_tcap() != null) {
                strSQL.append(", ip_tcap");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getIp_tcap(), true));
            }
            if (vo.getMota() != null) {
                strSQL.append(", mota");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getMota(), true));
            }
            if (vo.getUser_may_tcap() != null) {
                strSQL.append(", user_may_tcap");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getUser_may_tcap(), true));
            }
            if (vo.getTen_may_tcap() != null) {
                strSQL.append(", ten_may_tcap");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getTen_may_tcap(), true));
            }


            strSQL.append(", tgian_tcap");
            strSQL2.append(", ?");
            v_param.add(new DateParameter(new Date(), true));

            strSQL.append(strSQL2.toString());
            strSQL.append(")");

            return executeStatement(strSQL.toString(), v_param, conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".insert(): " +
                                 ex.getMessage(), ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }

    }

    public void saveVisitLog(String strUserID, String strKyHieuCNang,
                             String strIP, String strMota, String strOSUser, String strComputerName) throws Exception {
        //Lay chuc nang ID
        String strWhere = " UPPER(a.ky_hieu_cnang) = ? ";
        Vector vParam = new Vector();
        if (strKyHieuCNang == null)
            return;
        else
            strKyHieuCNang = strKyHieuCNang.toUpperCase();
        vParam.add(new Parameter(strKyHieuCNang, true));
        ChucNangDAO cndao = new ChucNangDAO(conn);
        ChucNangVO cnvo = cndao.getChucNang(strWhere, vParam);
        if (cnvo != null) {
            Long nChucNangID = cnvo.getId();
            //Set vao LSuTruyCapVO
            LSuTruyCapVO lstcVO = new LSuTruyCapVO();
            lstcVO.setCnang_id(nChucNangID);
            lstcVO.setNsd_id(new Long(strUserID.trim()));
            lstcVO.setIp_tcap(strIP);
            lstcVO.setMota(strMota);
            lstcVO.setUser_may_tcap(strOSUser);
            lstcVO.setTen_may_tcap(strComputerName);
            //Insert LSu truy cap
            LSuTruyCapDAO lstcDAO = new LSuTruyCapDAO(conn);
            lstcDAO.insert(lstcVO);
        }
    }
//  public void saveVisitLog(String strUserID, String strKyHieuCNang,
//                           String strIP, String strMota) throws Exception {
//      //Lay chuc nang ID
//      String strWhere = " UPPER(a.ky_hieu_cnang) = ? ";
//      Vector vParam = new Vector();
//      if (strKyHieuCNang == null)
//          return;
//      else
//          strKyHieuCNang = strKyHieuCNang.toUpperCase();
//      vParam.add(new Parameter(strKyHieuCNang, true));
//      ChucNangDAO cndao = new ChucNangDAO(conn);
//      ChucNangVO cnvo = cndao.getChucNang(strWhere, vParam);
//      if (cnvo != null) {
//          Long nChucNangID = cnvo.getId();
//          //Set vao LSuTruyCapVO
//          LSuTruyCapVO lstcVO = new LSuTruyCapVO();
//          lstcVO.setCnang_id(nChucNangID);
//          lstcVO.setNsd_id(new Long(strUserID.trim()));
//          lstcVO.setIp_tcap(strIP);
//          lstcVO.setMota(strMota);
//        
//          //Insert LSu truy cap
//          LSuTruyCapDAO lstcDAO = new LSuTruyCapDAO(conn);
//          lstcDAO.insert(lstcVO);
//      }
//  }
}
