package com.seatech.ttsp.logduyetlo.dao;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.ttsp.logduyetlo.vo.LogDuyetLoVO;

import java.sql.Connection;

import java.util.Date;
import java.util.Vector;


public class LogDuyetLoDAO extends AppDAO {
    Connection conn = null;

    public LogDuyetLoDAO(Connection conn) {
        this.conn = conn;
    }

    public int insertLogDuyetLo(LogDuyetLoVO vo) {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        try {
            Long lID = getSeqNextValue("SP_LOG_DUYET_LO_SEQ", conn);

            if (lID != null) {
                strSQL.append("INSERT INTO sp_log_duyet_lo (id ");
                strSQL2.append(" ) values ( ");
                strSQL2.append("? ");
                v_param.add(new Parameter(lID, true));

                if (vo.getError_code() != null) {
                    strSQL.append(", error_code");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getError_code(), true));
                }
                if (vo.getError_desc() != null) {
                    strSQL.append(", error_desc");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getError_desc(), true));
                }
                if (vo.getGhi_chu() != null) {
                    strSQL.append(", ghi_chu");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getGhi_chu(), true));
                }
//                if (vo.getInsert_date() != null) {
                    strSQL.append(", insert_date");
                    strSQL2.append(", ?");
                    v_param.add(new DateParameter(new Date(), true));
//                }
                if (vo.getNgay_duyet() != null) {
                    strSQL.append(", ngay_duyet");
                    strSQL2.append(", ?");
                    v_param.add(new DateParameter(vo.getNgay_duyet(), true));
                }
                if (vo.getLtt_id() != null) {
                    strSQL.append(", ltt_id");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getLtt_id(), true));
                }
                if (vo.getMa_kb() != null) {
                    strSQL.append(", ma_kb");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getMa_kb(), true));
                }
                if (vo.getSo_yctt() != null) {
                    strSQL.append(", so_yctt");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getSo_yctt(), true));
                }
                if (vo.getMa_nsd() != null) {
                    strSQL.append(", ma_nsd");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getMa_nsd(), true));
                }

                strSQL.append(strSQL2.toString());
                strSQL.append(")");

                return executeStatement(strSQL.toString(), v_param, conn);
            }
        } catch (Exception ex) {
//            ex.printStackTrace();

        }
        return -1;
    }

    public static void main(String[] args) {
        try {


            AppDAO a = new AppDAO(); //TTSP_KBA 130801 000006
            Connection conn = a.getConnectionTest();
            LogDuyetLoDAO log = new LogDuyetLoDAO(conn);
            LogDuyetLoVO vo = new LogDuyetLoVO();
            vo.setError_code("error_code");
            vo.setError_desc("error_desc");
            vo.setGhi_chu("ghi_chu");
            vo.setNgay_duyet(new Date());
            vo.setLtt_id(new Long("1234567"));
            vo.setSo_yctt("so_yctt");
            vo.setMa_kb("ma_kb");
            vo.setMa_nsd("ma_nsd");
           
            log.insertLogDuyetLo(vo);
            conn.commit();

        } catch (Exception e) {
            // TODO: Add catch code
//            e.printStackTrace();
        }

    }
}
