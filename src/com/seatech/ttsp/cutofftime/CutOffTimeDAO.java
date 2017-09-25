package com.seatech.ttsp.cutofftime;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.DatabaseConnectionFailureException;
import com.seatech.framework.exception.ExecuteStatementException;
import com.seatech.framework.exception.SelectStatementException;
import com.seatech.framework.utils.TTSPUtils;

import com.seatech.ttsp.dmnh.DMNHangHOVO;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.SQLException;

import java.util.Collection;
import java.util.Vector;

public class CutOffTimeDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.cutofftime.CutOffTimeDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.cutofftime.CutOffTimeVO";

    public CutOffTimeDAO(Connection conn) {
        this.conn = conn;
    }


    /**
     * @code hungpd
     * @param whereClause
     * @param params
     * @return
     * @throws Exception
     */
    public Collection getCOTList(String whereClause,
                                 Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.cot_id, a.nhkb_nhan, a.nhkb_chuyen, a.loai_cot, ");
            strSQL.append("  a.ma_kb_huyen, a.nguoi_lap, a.ngay_lap, a.nguoi_ks, a.ngay_ks, ");
            strSQL.append(" a.tgian_cot_cu, a.tgian_cot_moi, a.lydo_cot, a.lydo_daylai,  a.dong_y,");
            strSQL.append(" a.trang_thai ");
            strSQL.append(" FROM sp_cot a inner join ( select * from sp_dm_ma_thamchieu  where rv_domain = 'SP_COT.TRANG_THAI') c  on  a.trang_thai=c.rv_low_value ");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" order by c.rv_high_value asc, a.id asc");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getCOTList(): " +
                                   ex.getMessage(), ex);

        }

        return reval;
    }

    /**
     *
     * @param whereClause
     * @param params
     * @return
     * @throws Exception
     */
    public CutOffTimeVO getCTO(String whereClause,
                               Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.cot_id, a.nhkb_nhan, a.ma_kb_tinh, a.nhkb_chuyen, a.loai_cot, ");
            strSQL.append(" a.ma_kb_huyen, a.nguoi_lap, a.ngay_lap, a.nguoi_ks, a.ngay_ks, ");
            strSQL.append(" a.tgian_cot_cu, a.tgian_cot_moi, a.lydo_cot, a.lydo_daylai, a.dong_y, a.lydo_cot_nh,");
            strSQL.append(" a.trang_thai,b.ten_nsd as ten_nguoi_lap, (select ten_nsd from sp_nsd where id=a.nguoi_ks) as ten_nguoi_ks, c.rv_meaning, a.tn_cu, a.tn_moi, a.ma_nh, ");
            strSQL.append(" to_char(a.tgian_cot_moi,'HH24:MI') cot_moi, cot_cu_list, ten_kb ");
            strSQL.append(" FROM sp_cot a left outer join sp_nsd b on a.nguoi_lap=b.id left outer join " +
                          "( select * from sp_dm_ma_thamchieu  where rv_domain = 'SP_COT.TRANG_THAI') c " +
                          "on a.trang_thai=c.rv_low_value ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE" + whereClause);
            }
            return (CutOffTimeVO)findByPK(strSQL.toString(), params,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getCTO(): " +
                                   ex.getMessage(), ex);

        }
    }

    /**
     *
     * @param whereClause
     * @param params
     * @return
     * @throws Exception
     */
    public CutOffTimeVO getCTONH(String whereClause,
                                 Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, d.id as cot_id, a.nhkb_nhan, a.nhkb_chuyen, a.loai_cot, ");
            strSQL.append(" a.ma_kb_huyen, a.ma_kb_tinh, a.dong_y, a.tgian_cot_cu, a.tgian_cot_moi, ");
            strSQL.append(" d.lydo_cot, d.dong_y, d.lydo_daylai, d.lydo_ko_dongy, a.trang_thai, a.lydo_cot_nh,");
            strSQL.append(" a.nguoi_lap_nh, a.ngay_lap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, c.rv_meaning, a.tn_cu, ");
            strSQL.append(" a.tn_moi, a.ma_nh, a.cot_cu_list, a.lydo_cot lydo_traloi, a.ten_kb, to_char(a.tgian_cot_cu,'HH24:MI') cot_cu, to_char(a.tgian_cot_moi,'HH24:MI') cot_moi ");
            strSQL.append(" FROM sp_cot a left outer join ( select * from sp_dm_ma_thamchieu  where rv_domain = 'SP_COT.TRANG_THAI') c ");
            strSQL.append(" on a.trang_thai=c.rv_low_value ");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" left outer join sp_cot d on a.id=d.cot_id WHERE" +
                              whereClause);
            }
            return (CutOffTimeVO)findByPK(strSQL.toString(), params,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getCTO(): " +
                                   ex.getMessage(), ex);

        }
    }

    public CutOffTimeVO getTTinXML(String whereClause,
                                   Vector params) throws Exception {
        String strSQL = "";
        try {
            strSQL +=
                    "SELECT a.id, a.cot_id, a.nhkb_nhan, a.nhkb_chuyen, a.loai_cot," +
                    " a.ma_kb_huyen, a.ma_kb_tinh, a.dong_y, a.nguoi_lap, a.ngay_lap , b.ma_nsd," +
                    " a.nguoi_ks, a.ngay_ks, a.tgian_cot_cu, a.tgian_cot_moi," +
                    " a.lydo_cot, a.lydo_daylai, a.lydo_ko_dongy, a.trang_thai," +
                    " a.nguoi_lap_nh, a.ngay_lap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, to_char(tgian_cot_cu,'HH24:mi') cur_cot ,to_char(tgian_cot_moi,'HH24:mi') new_cot," +
                    " a.ngay_nhan, a.lydo_cot_nh, a.tn_cu, a.tn_moi, a.loai_cot, a.cot_cu_list FROM sp_cot a, sp_nsd b where a.nguoi_lap=b.id(+) " +
                    whereClause;
            return (CutOffTimeVO)findByPK(strSQL.toString(), params,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getCTO(): " +
                                   ex.getMessage(), ex);

        }
    }

    public String insert(CutOffTimeVO vo, String type) throws SQLException,
                                                              DatabaseConnectionFailureException,
                                                              ExecuteStatementException,
                                                              Exception {
        TTSPUtils ttspUtil = new TTSPUtils(conn);
        String strID = ttspUtil.getSoCOT(type);
        Vector v_param = new Vector();
        StringBuffer sqlBuff = new StringBuffer();
        StringBuffer sqlBuff2 = new StringBuffer();
        sqlBuff.append("insert into sp_cot (");
        sqlBuff2.append(" values (");
        sqlBuff.append(" id");
        sqlBuff2.append(strID);
        if (vo.getCot_id() != null) {
            sqlBuff.append(" ,COT_ID");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getCot_id(), true));
        }
        if (vo.getNhkb_nhan() != null) {
            sqlBuff.append(" ,NHKB_NHAN");
            sqlBuff2.append(" ,?");
            String ma_nh = vo.getNhkb_nhan();
            if (ma_nh.length() == 3) {
                ma_nh = getMa_NHByMa_DV(vo.getNhkb_nhan());
            }
            v_param.add(new Parameter(ma_nh, true));
        }
        if (vo.getNhkb_chuyen() != null) {
            sqlBuff.append(" ,NHKB_CHUYEN");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getNhkb_chuyen(), true));
        }
        if (vo.getLoai_cot() != null) {
            sqlBuff.append(" ,LOAI_COT");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getLoai_cot(), true));
        }
        if (vo.getMa_kb_huyen() != null) {
            sqlBuff.append(" ,MA_KB_HUYEN");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getMa_kb_huyen(), true));
        }
        if (vo.getMa_kb_tinh() != null) {
            sqlBuff.append(" ,MA_KB_TINH");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getMa_kb_tinh(), true));
        }
        if (vo.getDong_y() != null) {
            sqlBuff.append(" ,DONG_Y");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getDong_y(), true));
        }
        if (vo.getNguoi_lap() != null) {
            sqlBuff.append(" ,NGUOI_LAP");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getNguoi_lap(), true));
        }
        if (vo.getNgay_lap() != null) {
            sqlBuff.append(" ,NGAY_LAP");
            sqlBuff2.append(" ,?");
            v_param.add(new DateParameter(vo.getNgay_lap(), true));
        }
        if (vo.getNguoi_ks() != null) {
            sqlBuff.append(" ,NGUOI_KS");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getNguoi_ks(), true));
        }
        if (vo.getNgay_ks() != null) {
            sqlBuff.append(" ,NGAY_KS");
            sqlBuff2.append(" ,?");
            v_param.add(new DateParameter(vo.getNgay_ks(), true));
        }
        if (vo.getTgian_cot_cu() != null) {
            sqlBuff.append(" ,TGIAN_COT_CU");
            sqlBuff2.append(" ,?");
            v_param.add(new DateParameter(vo.getTgian_cot_cu(), true));
        }
        if (vo.getTgian_cot_moi() != null) {
            sqlBuff.append(" ,TGIAN_COT_MOI");
            sqlBuff2.append(" ,?");
            v_param.add(new DateParameter(vo.getTgian_cot_moi(), true));
        }
        if (vo.getLydo_cot() != null) {
            sqlBuff.append(" ,LYDO_COT");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getLydo_cot(), true));
        }
        if (vo.getLydo_daylai() != null) {
            sqlBuff.append(" ,LYDO_DAYLAI");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getLydo_daylai(), true));
        }
        if (vo.getLydo_ko_dongy() != null) {
            sqlBuff.append(" ,LYDO_KO_DONGY");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getLydo_ko_dongy(), true));
        }
        if (vo.getTrang_thai() != null) {
            sqlBuff.append(" ,TRANG_THAI");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getTrang_thai(), true));
        }
        if (vo.getNguoi_lap_nh() != null) {
            sqlBuff.append(" ,NGUOI_LAP_NH");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getNguoi_lap_nh(), true));
        }
        if (vo.getNgay_lap_nh() != null) {
            sqlBuff.append(" ,NGAY_LAP_NH");
            sqlBuff2.append(" ,?");
            v_param.add(new DateParameter(vo.getNgay_lap_nh(), true));
        }
        if (vo.getNguoi_ks_nh() != null) {
            sqlBuff.append(" ,NGUOI_KS_NH");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getNguoi_ks_nh(), true));
        }
        if (vo.getNgay_ks_nh() != null) {
            sqlBuff.append(" ,NGAY_KS_NH");
            sqlBuff2.append(" ,?");
            v_param.add(new DateParameter(vo.getNgay_ks_nh(), true));
        }
        if (vo.getNgay_nhan() != null) {
            sqlBuff.append(" ,NGAY_NHAN");
            sqlBuff2.append(" ,?");
            v_param.add(new DateParameter(vo.getNgay_nhan(), true));
        }
        if (vo.getLydo_cot_nh() != null) {
            sqlBuff.append(" ,LYDO_COT_NH");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getLydo_cot_nh(), true));
        }
        if (vo.getMa_cn_nh() != null) {
            sqlBuff.append(" ,MA_CN_NH");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getMa_cn_nh(), true));
        }
        if (vo.getMsg_id() != null) {
            sqlBuff.append(" ,MSG_ID");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getMsg_id(), true));
        }
        if (vo.getChuky_ktt() != null) {
            sqlBuff.append(" ,CHUKY_KTT");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getChuky_ktt(), true));
        }
        if (vo.getTn_cu() != null && !"".equals(vo.getTn_cu())) {
            sqlBuff.append(" ,TN_CU");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getTn_cu(), true));
        }
        if (vo.getTn_moi() != null && !"".equals(vo.getTn_moi())) {
            sqlBuff.append(" ,TN_MOI");
            sqlBuff2.append(" ,?");
            v_param.add(new Parameter(vo.getTn_moi(), true));
        }
      if (vo.getMa_nh() != null && !"".equals(vo.getMa_nh())) {
          sqlBuff.append(" ,ma_nh");
          sqlBuff2.append(" ,?");
          v_param.add(new Parameter(vo.getMa_nh(), true));
      }
      if (vo.getCot_cu_list() != null && !"".equals(vo.getCot_cu_list())) {
          sqlBuff.append(" ,cot_cu_list");
          sqlBuff2.append(" ,?");
          v_param.add(new Parameter(vo.getCot_cu_list(), true));
      }
      if (vo.getTen_kb() != null && !"".equals(vo.getTen_kb())) {
          sqlBuff.append(" ,ten_kb");
          sqlBuff2.append(" ,?");
          v_param.add(new Parameter(vo.getTen_kb(), true));
      }
        sqlBuff.append(")");
        sqlBuff.append(sqlBuff2);
        sqlBuff.append(")");
        executeStatement(sqlBuff.toString(), v_param, conn);
        return strID;
    }

    public int update(CutOffTimeVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer sqlBuff = new StringBuffer();
        sqlBuff.append(" Update sp_cot Set ");
        int nExc = 0;

        if (vo.getCot_id() != null && vo.getCot_id() > 0) {
            sqlBuff.append(" COT_ID=?,");
            v_param.add(new Parameter(vo.getCot_id(), true));
        }
        if (vo.getNhkb_nhan() != null) {
            sqlBuff.append(" NHKB_NHAN=?,");
            v_param.add(new Parameter(vo.getNhkb_nhan(), true));
        }
        if (vo.getNhkb_chuyen() != null) {
            sqlBuff.append(" NHKB_CHUYEN=?,");
            v_param.add(new Parameter(vo.getNhkb_chuyen(), true));
        }
        if (vo.getLoai_cot() != null) {
            sqlBuff.append(" LOAI_COT=?,");
            v_param.add(new Parameter(vo.getLoai_cot(), true));
        }
        if (vo.getMa_kb_huyen() != null) {
            sqlBuff.append(" MA_KB_HUYEN=?,");
            v_param.add(new Parameter(vo.getMa_kb_huyen(), true));
        }
        if (vo.getMa_kb_tinh() != null) {
            sqlBuff.append(" MA_KB_TINH=?,");
            v_param.add(new Parameter(vo.getMa_kb_tinh(), true));
        }
        if (vo.getDong_y() != null) {
            sqlBuff.append(" DONG_Y=?,");
            v_param.add(new Parameter(vo.getDong_y(), true));
        }
        if (vo.getNguoi_lap() != null) {
            sqlBuff.append(" NGUOI_LAP=?,");
            v_param.add(new Parameter(vo.getNguoi_lap(), true));
        }
        if (vo.getNgay_lap() != null) {
            sqlBuff.append(" NGAY_LAP=?,");
            v_param.add(new DateParameter(vo.getNgay_lap(), true));
        }
//        else{
//            sqlBuff.append(" NGAY_LAP=SYSDATE,");
//        }
        if (vo.getNguoi_ks() != null) {
            sqlBuff.append(" NGUOI_KS=?,");
            v_param.add(new Parameter(vo.getNguoi_ks(), true));
        }
        if (vo.getNgay_ks() != null) {
            sqlBuff.append(" NGAY_KS=?,");
            v_param.add(new DateParameter(vo.getNgay_ks(), true));
        }
//        else{
//            sqlBuff.append(" NGAY_KS=SYSDATE,");
//        }
        if (vo.getTgian_cot_cu() != null) {
            sqlBuff.append(" TGIAN_COT_CU=?,");
            v_param.add(new DateParameter(vo.getTgian_cot_cu(), true));
        }
        if (vo.getTgian_cot_moi() != null) {
            sqlBuff.append(" TGIAN_COT_MOI=?,");
            v_param.add(new DateParameter(vo.getTgian_cot_moi(), true));
        }
        if (vo.getLydo_cot() != null) {
            sqlBuff.append(" LYDO_COT=?,");
            v_param.add(new Parameter(vo.getLydo_cot(), true));
        }
        if (vo.getLydo_cot_nh() != null) {
            sqlBuff.append(" LYDO_COT_NH=?,");
            v_param.add(new Parameter(vo.getLydo_cot_nh(), true));
        }
        if (vo.getLydo_daylai() != null) {
            sqlBuff.append(" LYDO_DAYLAI=?,");
            v_param.add(new Parameter(vo.getLydo_daylai(), true));
        }
        if (vo.getLydo_ko_dongy() != null) {
            sqlBuff.append(" LYDO_KO_DONGY=?,");
            v_param.add(new Parameter(vo.getLydo_ko_dongy(), true));
        }
        if (vo.getTrang_thai() != null) {
            sqlBuff.append(" TRANG_THAI=?,");
            v_param.add(new Parameter(vo.getTrang_thai(), true));
        }
        if (vo.getNguoi_lap_nh() != null) {
            sqlBuff.append(" NGUOI_LAP_NH=?,");
            v_param.add(new Parameter(vo.getNguoi_lap_nh(), true));
        }
        if (vo.getNgay_lap_nh() != null) {
            sqlBuff.append(" NGAY_LAP_NH=?,");
            v_param.add(new DateParameter(vo.getNgay_lap_nh(), true));
        }
        if (vo.getNguoi_ks_nh() != null) {
            sqlBuff.append(" NGUOI_KS_NH=?,");
            v_param.add(new Parameter(vo.getNguoi_ks_nh(), true));
        }
        if (vo.getNgay_ks_nh() != null) {
            sqlBuff.append(" NGAY_KS_NH=?,");
            v_param.add(new DateParameter(vo.getNgay_ks_nh(), true));
        }
        if (vo.getNgay_nhan() != null) {
            sqlBuff.append(" NGAY_NHAN=?,");
            v_param.add(new DateParameter(vo.getNgay_nhan(), true));
        }
        if (vo.getMsg_id() != null) {
            sqlBuff.append(" MSG_ID=?,");
            v_param.add(new Parameter(vo.getMsg_id(), true));
        }
        if (vo.getChuky_ktt() != null) {
            sqlBuff.append(" CHUKY_KTT=?,");
            v_param.add(new Parameter(vo.getChuky_ktt(), true));
        }
        if (vo.getTn_cu() != null) {
            sqlBuff.append(" TN_CU=?,");            
            v_param.add(new Parameter(vo.getTn_cu(), true));
        }
        if (vo.getTn_moi() != null) {
            sqlBuff.append(" TN_MOI=?,");            
            v_param.add(new Parameter(vo.getTn_moi(), true));
          }
        if (vo.getMsg_reply_bank() != null) {
            sqlBuff.append(" MSG_REPLY_BANK=?,");            
            v_param.add(new Parameter(vo.getMsg_reply_bank(), true));
        }
        if (vo.getMsg_reply_tcs() != null) {
            sqlBuff.append(" MSG_REPLY_TCS=?,");            
            v_param.add(new Parameter(vo.getMsg_reply_tcs(), true));
        }
        
        sqlBuff.deleteCharAt(sqlBuff.length() - 1);
        if (vo.getId() != null && vo.getId() > 0) {
            sqlBuff.append(" WHERE ID=?");
            v_param.add(new Parameter(vo.getId(), true));
        }
        
        nExc = executeStatement(sqlBuff.toString(), v_param, conn);

        return nExc;
    }

    /**
     * @param: Long
     * @return: 1: xoa thanh cong; 0: Xoa khong thanh cong
     * @see:
     * */
    public int delete(Long id) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" delete sp_cot where id = ?");
        int nExc = 0;
        if (id != null) {
            v_param.add(new Parameter(id, true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
        }
        return nExc;
    }

    /**
     * @see: Lay ra chi tiet mot DTS
     * @param: id
     * @return: DTSoatVO
     * */
    public CutOffTimeVO getCOTForUpdate(String whereClause,
                                        Vector params) throws Exception {
        CutOffTimeVO cotVO = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.cot_id, a.nhkb_nhan, a.nhkb_chuyen, a.loai_cot, ");
            strSQL.append("  a.ma_kb_huyen, a.nguoi_lap, a.ngay_lap, a.nguoi_ks, a.ngay_ks, ");
            strSQL.append(" a.tgian_cot_cu, a.tgian_cot_moi, a.lydo_cot, a.lydo_daylai,  a.dong_y,");
            strSQL.append(" a.trang_thai, a.ma_kb_tinh ");
            strSQL.append(" FROM sp_cot a ");
            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" FOR UPDATE ");
            cotVO =
                    (CutOffTimeVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);

        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + "getCOTForUpdate(): " +
                                   ex.getMessage(), ex);
        }
        return cotVO;
    }

    private String getMa_NHByMa_DV(String string) throws Exception {
        String result = null;
        String sql = "select ma_nh from sp_dm_nh_ho where ma_dv = ?";
        Vector v = new Vector();
        v.add(new Parameter(string, true));
        Collection collection;
        try {
            DMNHangHOVO vo =
                (DMNHangHOVO)findByPK(sql, v, "com.seatech.ttsp.dmnh.DMNHangHOVO",
                                      conn);
            result = vo.getMa_nh();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return result;
    }
    public String ktraDKienNoiGio(String strMaNH,String strKBList, String strCOTMoi, String strType) throws Exception{       
//        CallableStatement cs = null;
//        cs = conn.prepareCall("{call sp_noi_gio_cot_pkg.pro_ktra_dkien_noi_gio_di(?,?,?,?,?,?)}");
//        cs.setString(1, strMaNH);
//        cs.setString(2, strKBList);
//        cs.setString(3, strCOTMoi);
//        cs.setString(4, strType);
//        cs.registerOutParameter(5, java.sql.Types.VARCHAR);
//        cs.registerOutParameter(6, java.sql.Types.VARCHAR);
//         
//        cs.execute();
//        String p_err_code = cs.getString(5);
//        String p_err_desc = cs.getString(6);    
//        if("00".equals(p_err_code)){
//          return p_err_code;
//        }
//        return p_err_code+": "+p_err_desc;
        return "00";
    }
}
