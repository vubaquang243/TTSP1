package com.seatech.ttsp.doichieu;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;


public class DoiChieuDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";

    private static String CLASS_NAME_DCNB_VO =
        "com.seatech.ttsp.dchieu.DuyetXNDChieu2VO";
    private static String CLASS_NAME_STATISTICS_VO =
        "com.seatech.ttsp.doichieu.StatisticsDCNoiBoVO";
    private static String CLASS_NAME_DAO = "com.seatech.doichieu.DoiChieuDAO";


    public DoiChieuDAO(Connection con) {
        this.conn = con;
    }

    public DCNoiBoVO getDCNoiBoByKey(Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.ma_kb, a.ket_chuyen_thu_kb, a.ket_chuyen_chi_kb, ");
            strSQL.append("a.so_tien_bao_co_sgd, a.so_tien_bao_no_sgd, a.trang_thai, ");
            strSQL.append("to_char(a.ngay_dc,'dd/mm/yyyy') ngay_dc,to_char(a.ngay_thuc_hien,'dd/mm/yyyy') ngay_thuc_hien, a.trang_thai_dong_mo, a.ly_do_dong ");
            strSQL.append("FROM sp_kqdc_noi_bo a where a.id=?");
            return (DCNoiBoVO)findByPK(strSQL.toString(), params,
                                       CLASS_NAME_DCNB_VO, conn);
        } catch (Exception ex) {
            ex.printStackTrace();
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDCNoiBoByKey(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
    }

    /**
     * @see: Lay danh sach doi chieu noi bo co phan trang
     * @param:
     * @return: Danh sach doi chieu theo so dong truuyen vao
     */
    public Collection getStatisticDCNBo(String whereClause, Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" select  a.created_date as ngay_quyet_toan,count(0) as so_khop_dung, ");
            strSQL.append(" count(0) as so_chenh_lech,count(0) as so_chua_th,count(0) as so_khong_hd from sp_kq_dc2 a group by a.created_date ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                if (whereClause.toUpperCase().indexOf("WHERE") == -1)
                    strSQL.append(" WHERE " + whereClause);
                else
                    strSQL.append(whereClause);
            }
            strSQL.append(" order by a.id ");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_DCNB_VO,
                                           conn);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getStatisticDCNBo(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    /**
     * @see: Lay danh sach doi chieu noi bo co phan trang
     * @param:
     * @return: Danh sach doi chieu theo so dong truuyen vao
     */
    public Collection getDCNBoListWithPading(String whereClause, Vector params,
                                             Integer page, Integer count,
                                             Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" select a.id, a.bk_id, a.ket_qua, a.lan_dc, a.send_bank, f.ten ten_kb_tinh, a.receive_bank,  b.ten as ten_nh, c.ten as ten_kb_huyen, ");
            strSQL.append(" a.created_date, a.creator, a.manager, a.verified_date,a.sodu_cuoingay_kb, a.sodu_clech, a.ketchuyen_thu_kb, a.ketchuyen_thu_nh, ");
            strSQL.append(" a.ketchuyen_thu_clech, a.ketchuyen_chi_kb, a.ketchuyen_chi_nh, to_char(a.ngay_thien_dc,'DD/MM/YYYY') ngay_thien_dc, to_char(a.ngay_dc,'DD/MM/YYYY') ngay_dc, a.ketchuyen_chi_clech,to_char(a.insert_date,'DD/MM/YYYY') insert_date, a.trang_thai from sp_kq_dc2 a ");
            strSQL.append(" inner join sp_dm_ngan_hang b  on a.receive_bank= b.ma_nh ");
            strSQL.append(" inner join sp_dm_ngan_hang c on a.send_bank = c.ma_nh ");
            strSQL.append(" inner join sp_dm_manh_shkb d on d.ma_nh = a.send_bank ");
            strSQL.append(" inner join sp_dm_htkb e on e.ma = d.shkb ");
            strSQL.append(" inner join sp_dm_htkb f on f.ma = e.ma_cha ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                if (whereClause.toUpperCase().indexOf("WHERE") == -1)
                    strSQL.append(" WHERE " + whereClause);
                else
                    strSQL.append(whereClause);
            }
            strSQL.append(" order by a.id ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), params, CLASS_NAME_DCNB_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getDCNBoListWithPading(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    /**
     * @see: update doi chieu
     * @param: DCNoiBoVO
     * @return: 1 = update thanh cong; -1 = update khong thanh cong
     * */
    public int update(DCNoiBoVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = null;
        strSQL.append("update sp_kqdc_noi_bo set ");
        try {
            if (vo.getTrang_thai_dong_mo() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" trang_thai_dong_mo = ?");
                } else {
                    strSQL2.append(", trang_thai_dong_mo = ?");
                }
                v_param.add(new Parameter(vo.getTrang_thai_dong_mo(), true));
            }
            if (vo.getLy_do_dong() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" ly_do_dong = ?");
                } else {
                    strSQL2.append(", ly_do_dong = ?");
                }
                v_param.add(new Parameter(vo.getLy_do_dong(), true));
            }
            if (vo.getId() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("id=? WHERE id=?");
                    v_param.add(new Parameter(vo.getId(), true));
                } else {
                    strSQL2.append(" WHERE id=?");
                }
                v_param.add(new Parameter(vo.getId(), true));

            }
            strSQL.append(strSQL2.toString());
            return executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".update(): " +
                                   ex.getMessage(), ex);
        }
    }


    public ResultSet getTotalMsg(String send_bank) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("select DISTINCT a.bk_sum ,a.BK_ID,a.STATUS_CODE, DECODE(instr(a.mt_id,'103'),6,1,instr(a.mt_id,'101'),6,0)loai_bk from sp_bkdc_ltt a where a.received_date >= trunc(sysdate)and a.BK_ID=(select max(bk_id) from sp_bkdc_ltt)  and a.send_bank like ?");
            vParam.add(new Parameter("%" + send_bank + "%", true));
            ResultSet rs =
                executeSelectStatement(strSQL.toString(), vParam, conn);
            if (rs.next()) {
                return rs;
            } else
                return null;

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getmsgreceive(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public int getCountMsg(String send_bank) throws Exception {
        StringBuffer sbWhereClause = new StringBuffer();
        Vector vParam = new Vector();
        try {
            sbWhereClause.append("select count(*) tong_msg from (" +
                                 " select c.msg_id from (" +
                                 " select a.msg_id msg_id from sp_bkdc_dts a where a.received_date >= trunc(sysdate) and a.send_bank like (?) and a.BK_ID=(select max(bk_id) from sp_bkdc_dts)" +
                                 " union all " +
                                 " select b.msg_id msg_id from sp_bkdc_ltt b where b.received_date >= trunc(sysdate) and b.send_bank like (?) and b.BK_ID=(select max(bk_id) from sp_bkdc_ltt) ) c group by c.msg_id)");
            vParam.add(new Parameter("%" + send_bank + "%", true));
            vParam.add(new Parameter("%" + send_bank + "%", true));
            ResultSet rs =
                executeSelectStatement(sbWhereClause.toString(), vParam, conn);
            if (rs.next()) {
                return rs.getInt("tong_msg");
            } else {
                //Throw loi LTT khong ton tai
                throw new Exception(" Kh&#244;ng th&#7875; th&#7921;c hi&#7879;n ch&#7913;c n&#432;ng n&#224;y !");
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getCountMsg(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public void runprc_doichieu(Vector params) throws Exception {
        try {
            String strPrc_ltt = "prc_doichieu_ltt";
            executeStoredProcedure(strPrc_ltt, params, conn);
            strPrc_ltt = "prc_doichieu_dts";
            executeStoredProcedure(strPrc_ltt, params, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".runprc_doichieu(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public void runprc_doichieulqt(Vector params) throws Exception {
        try {
            String strPrc_ltt = "prc_doichieu_lqt";
            executeStoredProcedure(strPrc_ltt, params, conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".runprc_doichieulqt(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public Collection getDM_NH(String whereClause,
                               Vector params) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            //            strSQL.append("SELECT   DISTINCT a.send_bank ma_nh, b.ten, b.id\n" +
            //            " FROM sp_bkdc_ltt a  INNER JOIN sp_dm_ngan_hang b\n" +
            //            " ON a.send_bank = b.ma_nh\n" +
            //            " WHERE   received_date >= TRUNC (SYSDATE)\n" +
            //            " and BK_ID=(select max(bk_id) from sp_bkdc_ltt)");
            strSQL.append(" select c.id,c.ma_nh,c.ten from sp_tknh_kb a inner join sp_dm_htkb b on a.kb_id=b.id " +
                          "INNER JOIN sp_dm_ngan_hang c ON a.nh_id = c.id  ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(whereClause);
            }
            return executeSelectStatement(strSQL.toString(), params,
                                          "com.seatech.ttsp.dmnh.DMNHangVO",
                                          conn);

        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getDM_NH(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

    public Collection TTDC_KB(String whereClause, Vector params,
                              String ngay_dc, Integer page, Integer count,
                              Integer[] totalCount) throws Exception {
        try {
            StringBuffer strSQL = new StringBuffer();
            strSQL.append("select rownum ,a.* from (select max(a.status_code) status_code , max(a.tt_dc) tt_dc , b.shkb nhkb_nhan,  b.ten ten_nhkb_nhan ,h. ma_nh, h.ten ten_nh " +
                          " from sp_dm_manh_shkb b  INNER JOIN sp_dm_htkb c ON c.ma = b.shkb\n" +
                    "                                     INNER JOIN sp_tknh_kb t ON c.id = t.kb_id\n" +
                    "                                     INNER JOIN sp_dm_ngan_hang h ON t.nh_id = h.id\n" +
                    "                                     LEFT JOIN sp_bkdc_ltt a\n" +
                    "                                     ON (a.receive_bank = b.ma_nh AND h.ma_nh = a.send_bank and  TO_CHAR (a.received_date, 'dd/mm/yyyy') ='" +
                    ngay_dc + "' )" + " Where 1=1 ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(whereClause);
            }
            strSQL.append(" group by  b.shkb, b.ten, h.ma_nh,  h.ten) a ");
            return executeSelectWithPaging(conn, strSQL.toString(), params,
                                           "com.seatech.ttsp.doichieu.DoiChieuLTTDTSVO",
                                           page, count, totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".TTDC_KB(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
    }

}
