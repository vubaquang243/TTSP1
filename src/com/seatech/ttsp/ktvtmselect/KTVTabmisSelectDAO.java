package com.seatech.ttsp.ktvtmselect;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;


public class KTVTabmisSelectDAO extends AppDAO {
    private String CLASS_NAME_VO =
        "com.seatech.ttsp.ktvtmselect.KTVTabmisSelectVO";
    private String CLASS_NAME_DAO =
        "com.seatech.ttsp.ktvtmselect.KTVTabmisSelectDAO";
    private static final String STRING_EMPTY = "";
    private Connection conn = null;

    public KTVTabmisSelectDAO(Connection conn) {
        this.conn = conn;
    }


    private void setConn(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Long
     * @return: 1: xoa thanh cong; 0: Xoa khong thanh cong
     * @see: Xoa NSD tabmis trong bảng chon_ktv_tabmis
     * */


    public void insert(KTVTabmisSelectVO vo) throws Exception {
        try {
            Vector v_param = new Vector();
            StringBuffer strSQL = new StringBuffer();
            StringBuffer strSQL2 = new StringBuffer();
            strSQL.append(" Insert into sp_chon_ktv_tabmis (id ");
            strSQL2.append(" values (? ");

            Long id = getSeqNextValue("sp_chon_ktv_tabmis_seq", conn);
            v_param.add(new Parameter(id, true));

            if (vo.getNsd_id() != null) {
                strSQL.append(", nsd_id");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getNsd_id(), true));
            }
            if (vo.getKtv_id() != null) {
                strSQL.append(", ktv_id");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getKtv_id(), true));
            }
            if (vo.getTtv_id() != null) {
                strSQL.append(", ttv_id");
                strSQL2.append(", ?");
                v_param.add(new Parameter(vo.getTtv_id(), true));
            }
            strSQL.append(", tgian_chon )");
            strSQL2.append(", ?");
            v_param.add(new DateParameter(new Date(), true));

            strSQL.append(strSQL2);
            strSQL.append(")");

            executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".insert: " + ex);
//            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    /**
     * @param: Long
     * @return: 1: xoa thanh cong; 0: Xoa khong thanh cong
     * @see: Xoa KTV tabmis id trong bảng chon_ktv_tabmis
     * */
    public int delete(String strWhere, Vector vParam) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("delete sp_chon_ktv_tabmis where ");
        if (strWhere != null && !STRING_EMPTY.equals(strWhere)) {
            strSQL.append(strWhere);
            return executeStatement(strSQL.toString(), vParam, conn);
        } else
            return 0;
    }

    /**
     * @param: Menh de where va vector param
     * @return: Danh sach NSD
     * @see:
     * */
    public Collection getListTTVSelectKTVTABMIST(String ma_ttv, String ma_ktv_tabmis,Long kb_id, String orderBy) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        Vector params = new Vector();
        // 2017-12-5 : taidd them tim kiem theo ma_ttv va mt_ktv_tabmis.
        params.add(new Parameter(kb_id, true));
        try {
            strSQL.append(" SELECT a.id as ttv_id, a.ma_nsd, a.ten_nsd, b.id as ktv_id, b.ma, b.ten, ");
            strSQL.append("(SELECT COUNT(*) FROM sp_chon_ktv_tabmis WHERE ktv_id=b.id AND ttv_id=a.id) as ischeck ");
            strSQL.append("       FROM ( ");
            strSQL.append("       SELECT distinct(a.id), a.kb_id, a.ten_nsd, a.ma_nsd ");
            strSQL.append("      FROM sp_nsd a,sp_nhom_nsd b, sp_nsd_nhom c ");
            strSQL.append(" WHERE a.id=c.nsd_id AND b.id=c.nhom_id ");
            strSQL.append(" AND b.loai_nhom='TTV' ");
            strSQL.append(" AND a.trang_thai='01' ");
            strSQL.append(" AND a.kb_id=?)  a INNER JOIN sp_ktv_tabmis b ON a.kb_id=b.kb_id ");
            strSQL.append(" WHERE a.ma_nsd LIKE '%" + ma_ttv.toUpperCase() + "%' AND b.ma LIKE '%"+ ma_ktv_tabmis.toUpperCase() +"%' ");
            strSQL.append(" ORDER BY ischeck "+orderBy+" , a.ten_nsd, lower(b.ten) ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getListTTVSelectKTVTABMIST(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }


}
