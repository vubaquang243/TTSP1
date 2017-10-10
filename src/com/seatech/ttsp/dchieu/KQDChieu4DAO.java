package com.seatech.ttsp.dchieu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class KQDChieu4DAO extends AppDAO {

    private String strValueObject = "com.seatech.ttsp.dchieu.KQDChieu4DAO";
    private String strValueObjectVO = "com.seatech.ttsp.dchieu.KQDChieu4VO";
    Connection conn = null;

    public KQDChieu4DAO(Connection conn) {
        this.conn = conn;
    }

    public KQDChieu4VO getKQDChieu4(String strWhereClause) throws Exception {
        String strSQL =
            "SELECT a.id, a.bk_id, a.lan_dc, a.ngay_dc, a.send_bank, a.receive_bank,\n" +
            "       a.creator, a.manager, a.created_date, a.verified_date, a.msg_id,\n" +
            "       a.insert_date, a.so_du_kbnn, a.chenh_lech, a.trang_thai,\n" +
            "       a.ket_qua\n" +
            "  FROM sp_kq_dc3_ngoai_te a where (1=1) " + strWhereClause;
        return (KQDChieu4VO)findByPK(strSQL, strValueObjectVO, conn);
    }

    public Collection getDChieuList(String strWhere,
                                    Vector vParam) throws Exception {

        Collection reval = null;
        try {
            String strSQL = "";

            strSQL +=
                    " SELECT a.id, a.lan_dc, a.ngay_dc, a.send_bank, a.receive_bank,\n" +
                    "       a.created_date, a.creator, a.manager, a.verified_date,\n" +
                    "       a.sodu_daungay, a.ketchuyen_chi, a.ps_thu, a.hanmuc,\n" +
                    "       a.ketchuyen_thu, a.sodu_cuoingay, a.insert_date, a.trang_thai\n" +
                    "  FROM sp_bk_dc2 a WHERE 1=1 ";

            strSQL +=
                    strWhere + " ORDER BY a.trang_thai asc ,a.insert_date, a.lan_dc desc ";
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, strValueObjectVO,
                                           conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getDChieuList(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }

    public int updateKQDC4(KQDChieu4VO vo) throws Exception {
        int exc = 0;
        Vector v_param = new Vector();
        StringBuffer sqlBuff = new StringBuffer("");
        sqlBuff.append(" UPDATE sp_kq_dc3_ngoai_te SET ngay_thien_dc= sysdate ,");

        if (vo.getLan_dc() != null) {
            sqlBuff.append(" LAN_DC=?,");
            v_param.add(new Parameter(vo.getLan_dc(), true));
        }
        if (vo.getNgay_dc() != null) {
            sqlBuff.append(" NGAY_DC=?,");
            v_param.add(new DateParameter(vo.getNgay_dc(), true));
        }
        if (vo.getSend_bank() != null) {
            sqlBuff.append(" SEND_BANK=?,");
            v_param.add(new Parameter(vo.getSend_bank(), true));
        }
        if (vo.getReceive_bank() != null) {
            sqlBuff.append(" RECEIVE_BANK=?,");
            v_param.add(new Parameter(vo.getReceive_bank(), true));
        }
        if (vo.getCreator() != null) {
            sqlBuff.append(" CREATOR=?,");
            v_param.add(new Parameter(vo.getCreator(), true));
        }
        if (vo.getManager() != null) {
            sqlBuff.append(" MANAGER=?,");
            v_param.add(new Parameter(vo.getManager(), true));
        }
        if (vo.getCreated_date() != null) {
            sqlBuff.append(" CREATED_DATE=?,");
            v_param.add(new DateParameter(vo.getCreated_date(), true));
        }
        if (vo.getVerified_date() != null) {
            sqlBuff.append(" VERIFIED_DATE=?,");
            v_param.add(new DateParameter(vo.getVerified_date(), true));
        }
        if (vo.getMsg_id() != null) {
            sqlBuff.append(" MSG_ID=?,");
            v_param.add(new Parameter(vo.getMsg_id(), true));
        }
        if (vo.getInsert_date() != null) {
            sqlBuff.append(" INSERT_DATE=?,");
            v_param.add(new DateParameter(vo.getInsert_date(), true));
        }
        if (vo.getSo_du_kbnn() != null) {
            sqlBuff.append(" SO_DU_KBNN=?,");
            v_param.add(new Parameter(vo.getSo_du_kbnn(), true));
        }
        if (vo.getChenh_lech() != null) {
            sqlBuff.append(" CHENH_LECH=?,");
            v_param.add(new Parameter(vo.getChenh_lech(), true));
        }
        if (vo.getTrang_thai() != null) {
            sqlBuff.append(" TRANG_THAI=?,");
            v_param.add(new Parameter(vo.getTrang_thai(), true));
        }
        if (vo.getTthai_ttin() != null) {
            sqlBuff.append(" tthai_ttin=?,");
            v_param.add(new Parameter(vo.getTthai_ttin(), true));
        }
        if (vo.getKet_qua() != null) {
            sqlBuff.append(" KET_QUA=?,");
            v_param.add(new Parameter(vo.getKet_qua(), true));
        }
        if (vo.getKet_qua() != null) {
            sqlBuff.append(" KET_QUA=?,");
            v_param.add(new Parameter(vo.getKet_qua(), true));
        }
        if (vo.getChuky_ktt() != null) {
            sqlBuff.append(" CHUKY_KTT=?,");
            v_param.add(new Parameter(vo.getChuky_ktt(), true));
        }
        sqlBuff = sqlBuff.deleteCharAt(sqlBuff.length() - 1);
        if (vo.getBk_id() != null) {
            sqlBuff.append(" WHERE BK_ID=? ");
            v_param.add(new Parameter(vo.getBk_id(), true));
        }
        if (vo.getId() != null) {
            sqlBuff.append(" WHERE ID=? ");
            v_param.add(new Parameter(vo.getId(), true));
        }
        exc = executeStatement(sqlBuff.toString(), v_param, conn);
        return exc;
    }

    public KQDChieu4VO getTenNH(String whereClause,
                                Vector params) throws Exception {
        try {
            String strSQL = "";
            strSQL +=
                    "select distinct b.ten ten_kb, c.ten ten from" + " sp_tknh_kb a, sp_dm_htkb b, sp_dm_ngan_hang c where a.kb_id=b.id and a.nh_id=c.id and c.ma_nh= " +
                    whereClause;
            KQDChieu4VO phtVO =
                (KQDChieu4VO)findByPK(strSQL.toString(), params,
                                      strValueObjectVO, conn);
            return phtVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getTenNH(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }

    public KQDChieu4VO getKQDC4(String strWhere,
                                Vector vParam) throws Exception {

        //      Collection reval = null;
        try {

            String strSQL = "";
            strSQL +=
                    "SELECT	a.id, a.bk_id, a.lan_dc, to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc, a.send_bank, a.receive_bank," +
                    " a.creator, a.manager, to_char(a.created_date,'DD-MM-YYYY HH24:mi:ss') created_date, a.verified_date, a.msg_id," +
                    " a.insert_date, a.so_du_kbnn, a.chenh_lech, a.trang_thai, a.ket_qua," +
                    " a.ngay_thien_dc, b.ma_nsd, a.msg_refid " +
                    " FROM	sp_kq_dc3_ngoai_te a, sp_nsd b WHERE	a.creator = b.id" +
                    strWhere;
            KQDChieu4VO dcVO =
                (KQDChieu4VO)findByPK(strSQL.toString(), vParam, strValueObjectVO,
                                      conn);
            return dcVO;
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(strValueObjectVO + ".getKQDC4(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }

}
