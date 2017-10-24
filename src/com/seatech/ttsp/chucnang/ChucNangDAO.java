package com.seatech.ttsp.chucnang;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;


public class ChucNangDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.chucnang.ChucNangDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.chucnang.ChucNangVO";

    public ChucNangDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach cac chuc nang
     * @return: Collection
     * */
    public Collection getChucNangList(String whereClause,
                                      Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.cnang_cha, a.ten_cnang, a.mo_ta, a.ten_action, ");
            strSQL.append("a.hien_thi, a.sap_xep, a.trang_thai, a.ma_cnang , a.loai_cnang, a.ky_hieu_cnang ");
            strSQL.append("FROM sp_chucnang a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
          throw
                new DAOException(CLASS_NAME_DAO + ".getChucNangList(): " +
                                 ex.getMessage(), ex);
           
        }

        return reval;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra thong tin 1 chuc nang
     * @return: ChucNangVO
     * */
    public ChucNangVO getChucNang(String whereClause,
                                  Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.cnang_cha, a.ten_cnang, a.mo_ta, a.ten_action, ");
            strSQL.append("a.hien_thi, a.sap_xep, a.trang_thai, a.ma_cnang , a.loai_cnang, a.ky_hieu_cnang ");
            strSQL.append("FROM sp_chucnang a");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            return (ChucNangVO)findByPK(strSQL.toString(), params,
                                        CLASS_NAME_VO, conn);
        } catch (Exception ex) {
          throw
                new DAOException(CLASS_NAME_DAO + ".getChucNang(): " +
                                 ex.getMessage(), ex);
            
        }
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach cac chuc nang
     * @return: Collection
     * */
    public Collection getChucNangListByUserID(Long nUserID) throws Exception {
        Collection reval = null;
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT DISTINCT(a.id), a.cnang_cha, a.ten_cnang, a.mo_ta, a.ten_action, ");
            strSQL.append("a.hien_thi, a.sap_xep, a.trang_thai, a.ma_cnang, a.loai_cnang, a.ky_hieu_cnang ");
            strSQL.append("FROM sp_chucnang a ");
            strSQL.append("inner join sp_cnang_nhom b on a.id = b.cnang_id ");
            strSQL.append("inner join sp_nhom_nsd c on b.nhom_id = c.id ");
            strSQL.append("inner join sp_nsd_nhom d on d.nhom_id = c.id ");
            strSQL.append("inner join sp_nsd e on e.id = d.nsd_id ");
            strSQL.append("and e.id = ? and a.trang_thai = 'Y'  ");
            vParam.add(new Parameter(nUserID, true));

            strSQL.append(" ORDER BY a.ma_cnang ASC ");
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
          throw
                new DAOException(CLASS_NAME_DAO + ".getChucNangListByUserID(): " +
                                 ex.getMessage(), ex);
            
           
        }

        return reval;
    }

    public List getCNangMenuFromCNangList(Collection colCNang) throws Exception {
        List lstMenu = null;
        if (colCNang == null)
            return lstMenu;
        lstMenu = new ArrayList();        
        ChucNangVO cnVO = null;
        int nMaCNang = 0;
        int nMaCNangCap2 = 0;
        int nMaCNangCap3 = 0;
        try {
            Iterator iter = colCNang.iterator();
            while (iter.hasNext()) {
                cnVO = (ChucNangVO)iter.next();
                if ("Y".equalsIgnoreCase(cnVO.getHien_thi())) {

                    if (Integer.parseInt(cnVO.getMa_cnang()) % 1000 == 0) {
                        nMaCNang = Integer.parseInt(cnVO.getMa_cnang());
                    } else if (Integer.parseInt(cnVO.getMa_cnang()) % 100 == 0) {
                        if (Integer.parseInt(cnVO.getMa_cnang()) - nMaCNang >=
                            10000) {
                            continue;
                        } else {
                            nMaCNangCap2 =
                                    Integer.parseInt(cnVO.getMa_cnang());
                        }
                    } else if (Integer.parseInt(cnVO.getMa_cnang()) % 10 == 0) {
                        if (Integer.parseInt(cnVO.getMa_cnang()) -
                            nMaCNangCap2 >= 100) {
                            continue;
                        } else {
                            nMaCNangCap3 =
                                    Integer.parseInt(cnVO.getMa_cnang());
                        }
                    } else {
                        if (Integer.parseInt(cnVO.getMa_cnang()) -
                            nMaCNangCap3 >= 10) {
                            continue;
                        }
                    }
                    lstMenu.add(cnVO);
                }
            }
        } catch (Exception ex) {            
            throw new DAOException(CLASS_NAME_DAO + ".getChucNangListByUserID(): " +
                                 ex.getMessage(), ex);
            
        }
        return lstMenu;
    } 
    
}
