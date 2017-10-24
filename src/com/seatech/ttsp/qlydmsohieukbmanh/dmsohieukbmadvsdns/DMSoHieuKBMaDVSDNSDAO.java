package com.seatech.ttsp.qlydmsohieukbmanh.dmsohieukbmadvsdns;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.ExecuteStatementException;
import com.seatech.ttsp.qlydmsohieukbmanh.DMSoHieuKBMaNHDAO;

import com.seatech.ttsp.qlydmsohieukbmanh.DMSoHieuKBMaNHVO;

import java.sql.Connection;

import java.util.Vector;

public class DMSoHieuKBMaDVSDNSDAO extends AppDAO {
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.qlydmsohieukbmanh.dmsohieukbmadvsdns.DMSoHieuKBMaDVSDNSVO";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.qlydmsohieukbmanh.dmsohieukbmadvsdns.DMSoHieuKBMaDVSDNSDAO";
    private transient Connection conn = null;

    public DMSoHieuKBMaDVSDNSDAO(Connection conn) {
        this.conn = conn;
    }

    public int update(DMSoHieuKBMaDVSDNSVO vo) {
        int result = 0;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("update sp_dm_shkb_dvsdns set ");
            if (vo.getMa_kb() != null) {
                if (vo.getMa_dvsdns() != null ) {
                    strSQL.append(" ma_dvsdns = ? ");
                    v_param.add(new Parameter(vo.getMa_dvsdns(), true));
                }
                if (vo.getLoai_gd() != null ) {
                    strSQL.append(", loai_gd = ? ");
                    v_param.add(new Parameter(vo.getLoai_gd(), true));
                }
                if(vo.getLoai_gd() == null || "".equals(vo.getLoai_gd())){
                  vo.setLoai_gd("LAI_PHI");
                }
                strSQL.append(" where ma_kb = ? and loai_gd = '"+vo.getLoai_gd()+"' ");
                v_param.add(new Parameter(vo.getMa_kb(), true));
                result = executeStatement(strSQL.toString(), v_param, conn);
                return result;
            }
        } catch (Exception e) {           
            return -1;
        }
        return result;
    }

    public int insert(DMSoHieuKBMaDVSDNSVO vo) throws DatabaseConnectionFailureException,
                                                      ExecuteStatementException {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        strSQL.append("INSERT INTO sp_dm_shkb_dvsdns (MA_KB ");
        strSQL2.append(") values (? ");
        v_param.add(new Parameter(vo.getMa_kb(), true));
        if (vo.getMa_dvsdns() != null) {
            strSQL.append(", MA_DVSDNS");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getMa_dvsdns(), true));
        }
        if (vo.getLoai_gd() != null) {
            strSQL.append(", LOAI_GD");
            strSQL2.append(", ?");
            v_param.add(new Parameter(vo.getLoai_gd(), true));
        }
        strSQL.append(strSQL2);
        strSQL.append(")");
        return executeStatement(strSQL.toString(), v_param, conn);
    }

    public DMSoHieuKBMaDVSDNSVO getDMSoHieuKBMaDVSDNS(String strWhere,
                                                      Vector vParam) throws DAOException {
        DMSoHieuKBMaDVSDNSVO vo = null;
        StringBuffer strSQL = new StringBuffer();

        try {
            strSQL.append("SELECT MA_KB,MA_DVSDNS ");
            strSQL.append("FROM sp_dm_shkb_dvsdns ");
            if (strWhere != null & !strWhere.isEmpty()) {
                strSQL.append(" WHERE " + strWhere);
            }
            vo =
 (DMSoHieuKBMaDVSDNSVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO,
                                conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ex.getMessage());
            daoEx.printStackTrace();
            throw daoEx;
        }

        return vo;
    }
}
