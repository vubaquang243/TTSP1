package com.seatech.ttsp.ltt;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;


public class LTTCTietDAO extends AppDAO {
    private Connection conn = null;
    private static String CLASS_NAME_VO = "com.seatech.ttsp.ltt.LTTCTietVO";
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.ltt.LTTCTietDAO";

    public LTTCTietDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @see: Them moi LTT Chi tiet
     * @param lttCTietVO
     * @return Long 0 : khong thanh cong, Long khac 0 : insert thanh cong - tra ve id LTTCTiet
     * @throws Exception
     */
    public Long insert(LTTCTietVO lttCTietVO) throws Exception {
        Vector v_param = new Vector();
        Parameter param = null;
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Long id = getSeqNextValue("sp_ltt_ctiet_seq", conn);


        try {
            strSQL.append("insert into sp_ltt_ctiet (id ");
            strSQL2.append(" ) values ( ");
            strSQL2.append(String.valueOf(id));

            if (lttCTietVO.getLtt_id() != null) {
                strSQL.append(", ltt_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getLtt_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getMa_quy_id() != null) {
                strSQL.append(", ma_quy_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getMa_quy_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getTkkt_id() != null) {
                strSQL.append(", tkkt_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getTkkt_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getDvsdns_id() != null) {
                strSQL.append(", dvsdns_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getDvsdns_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getCapns_id() != null) {
                strSQL.append(", capns_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getCapns_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getChuongns_id() != null) {
                strSQL.append(", chuongns_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getChuongns_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getNganhkt_id() != null) {
                strSQL.append(", nganhkt_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getNganhkt_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getNdkt_id() != null) {
                strSQL.append(", ndkt_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getNdkt_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getDbhc_id() != null) {
                strSQL.append(", dbhc_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getDbhc_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getCtmt_id() != null) {
                strSQL.append(", ctmt_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getCtmt_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getDu_phong_id() != null) {
                strSQL.append(", du_phong_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getDu_phong_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getNguon_id() != null) {
                strSQL.append(", nguon_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getNguon_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getSo_tien() != null) {
                strSQL.append(", so_tien");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getSo_tien(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getDien_giai() != null) {
                strSQL.append(", dien_giai");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getDien_giai(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getKb_id() != null) {
                strSQL.append(", kb_id");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getKb_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getSott_dong() != 0) {
                strSQL.append(", sott_dong");
                strSQL2.append(", ?");
                param = new Parameter(lttCTietVO.getSott_dong(), true);
                v_param.add(param);
            }
            strSQL.append(strSQL2.toString());
            strSQL.append(")");
            if (executeStatement(strSQL.toString(), v_param, conn) != 1)
                return new Long(0);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".insert(lttCTietVO): " +
                                 ex.getMessage());
            daoEx.printStackTrace();
            throw daoEx;
        }
        return id;
    }

    /**
     * @see: Cap nhat Chi tiet LTT
     * @param lttCTietVO
     * @return int 0: khong thanh cong, int 1: update thanh cong
     * @throws Exception
     */
    public int update(LTTCTietVO lttCTietVO) throws Exception {
        Vector v_param = new Vector();
        Parameter param = null;
        StringBuffer strSQL = new StringBuffer();

        try {
            strSQL.append("update sp_ltt_ctiet set ");

            if (lttCTietVO.getLtt_id() != null) {
                strSQL.append("ltt_id=?");
                param = new Parameter(lttCTietVO.getLtt_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getMa_quy_id() != null) {
                strSQL.append(", ma_quy_id=?");
                param = new Parameter(lttCTietVO.getMa_quy_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getTkkt_id() != null) {
                strSQL.append(", tkkt_id=?");
                param = new Parameter(lttCTietVO.getTkkt_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getDvsdns_id() != null) {
                strSQL.append(", dvsdns_id=?");
                param = new Parameter(lttCTietVO.getDvsdns_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getCapns_id() != null) {
                strSQL.append(", capns_id=?");
                param = new Parameter(lttCTietVO.getCapns_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getChuongns_id() != null) {
                strSQL.append(", chuongns_id=?");
                param = new Parameter(lttCTietVO.getChuongns_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getNganhkt_id() != null) {
                strSQL.append(", nganhkt_id=?");
                param = new Parameter(lttCTietVO.getNganhkt_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getNdkt_id() != null) {
                strSQL.append(", ndkt_id=?");
                param = new Parameter(lttCTietVO.getNdkt_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getDbhc_id() != null) {
                strSQL.append(", dbhc_id=?");
                param = new Parameter(lttCTietVO.getDbhc_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getCtmt_id() != null) {
                strSQL.append(", ctmt_id=?");
                param = new Parameter(lttCTietVO.getCtmt_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getDu_phong_id() != null) {
                strSQL.append(", du_phong_id=?");
                param = new Parameter(lttCTietVO.getDu_phong_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getNguon_id() != null) {
                strSQL.append(", nguon_id=?");
                param = new Parameter(lttCTietVO.getNguon_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getSo_tien() != null) {
                strSQL.append(", so_tien=?");
                param = new Parameter(lttCTietVO.getSo_tien(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getDien_giai() != null) {
                strSQL.append(", dien_giai=?");
                param = new Parameter(lttCTietVO.getDien_giai(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getKb_id() != null) {
                strSQL.append(", kb_id=?");
                param = new Parameter(lttCTietVO.getKb_id(), true);
                v_param.add(param);
            }
            if (lttCTietVO.getSott_dong() != 0) {
                strSQL.append(", sott_dong=?");
                param = new Parameter(lttCTietVO.getSott_dong(), true);
                v_param.add(param);
            }
            strSQL.append(" WHERE id = ?");
            param = new Parameter(String.valueOf(lttCTietVO.getId()), true);
            v_param.add(param);
            String strSQL1 = "";
            if (strSQL.toString().contains("set , ")) {
                strSQL1 = strSQL.toString().replaceFirst("set , ", "set ");
            } else {
                strSQL1 = strSQL.toString();
            }
            return executeStatement(strSQL1, v_param, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".insert(lttCTietVO): " +
                                 ex.getMessage());
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    /**
     * @see: Xoa du Chi tiet LTT
     * @param id
     * @return
     * @throws Exception
     */
    public int delete(Long id) throws Exception {
        Parameter param = null;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("delete from sp_ltt_ctiet where id=?");
            param = new Parameter(String.valueOf(id), true);
            v_param.add(param);

            return executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".delete(id): " +
                                 ex.getMessage());
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    /**
     * @see: Xoa du Chi tiet LTT theo lttId
     * @param lttId
     * @return 1 la thanh cong, khac 1 la false
     * @throws Exception
     */
    public int deleteByLTT(Long lttId) throws Exception {
        Parameter param = null;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("delete from sp_ltt_ctiet where ltt_id=?");
            param = new Parameter(String.valueOf(lttId), true);
            v_param.add(param);

            return executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".deleteByLTT(lttId): " +
                                 ex.getMessage());
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public Collection getLTTDiCTietList(String whereClause,
                                        Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("select t.id, t.ltt_id, nvl(t.dvsdns_id,'0000000') as dvsdns_ma, " +
                          " nvl(t.nguon_id,'00') as ma_nguon, nvl(t.so_tien,'0') as so_tien , sp_util_pkg.fnc_get_ndung_ltt(nvl(t.dien_giai, ' '),t.ltt_id) dien_giai, t.sott_dong, " +
                          " nvl(t.ma_quy_id,'00') as ma_quy, nvl(t.tkkt_id,'0000') as tkkt_ma, nvl(t.capns_id,'0') as capns_ma, nvl(t.chuongns_id,'000') as chuongns_ma, " +
                          " nvl(t.nganhkt_id,'000') as nganhkt_ma, nvl(t.ndkt_id,'0000') as ndkt_ma, nvl(t.dbhc_id,'00000') as dbhc_ma, " +
                          " nvl(t.ctmt_id,'00000') as ctmt_ma, nvl(t.du_phong_id,'000') as du_phong_ma, nvl(t.kb_id, '0000') as kb_ma ");
            strSQL.append(" FROM sp_ltt_ctiet t ");
            if (whereClause != null && !"".equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY t.sott_dong ASC ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {

            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTDiCTietList(): kh&#244;ng l&#7845;y &#273;&#432;&#7907;c chi ti&#7871;t c&#7911;a l&#7879;nh g&#7889;c c&#243; s&#7889; chi ti&#7871;t l&#224; : " +
                                 params.get(0) + ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public LTTCTietVO getLTTDiCTiet(String whereClause,
                                    Vector params) throws Exception {
        LTTCTietVO reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("select t.id, t.ltt_id, nvl(t.dvsdns_id,'0000000') as dvsdns_ma, " +
                          " nvl(t.nguon_id,'00') as ma_nguon, nvl(t.so_tien,'0') as so_tien , nvl(t.dien_giai,'') as dien_giai, t.sott_dong, " +
                          " nvl(t.ma_quy_id,'00') as ma_quy, nvl(t.tkkt_id,'0000') as tkkt_ma, nvl(t.capns_id,'0') as capns_ma, nvl(t.chuongns_id,'000') as chuongns_ma, " +
                          " nvl(t.nganhkt_id,'000') as nganhkt_ma, nvl(t.ndkt_id,'0000') as ndkt_ma, nvl(t.dbhc_id,'00000') as dbhc_ma, " +
                          " nvl(t.ctmt_id,'00000') as ctmt_ma, nvl(t.du_phong_id,'000') as du_phong_ma, nvl(t.kb_id, '0000') as kb_ma ");
            strSQL.append(" FROM sp_ltt_ctiet t ");
            if (whereClause != null && !"".equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            //strSQL.append(" ORDER BY t.sott_dong ASC ");
            reval =
                    (LTTCTietVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                         conn);
        } catch (Exception ex) {

            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTDiCTietList(): kh&#244;ng l&#7845;y &#273;&#432;&#7907;c chi ti&#7871;t c&#7911;a l&#7879;nh g&#7889;c c&#243; s&#7889; chi ti&#7871;t l&#224; : " +
                                 params.get(0) + ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return reval;
    }

    public boolean checkExistLTTById(String whereClause,
                                     Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        boolean result = false;
        Collection reVal = null;
        try {
            strSQL.append("select ltt_id  from sp_ltt_ctiet ");
            if (whereClause != null && !"".equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            reVal =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
            if (reVal != null && reVal.size() > 0)
                result = true;

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".checkExistLTTById(lttId): " +
                                 ex.getMessage());
            daoEx.printStackTrace();
            throw daoEx;
        }
        return result;
    }
}
