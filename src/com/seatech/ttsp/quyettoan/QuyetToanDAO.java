package com.seatech.ttsp.quyettoan;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;


public class QuyetToanDAO extends AppDAO {
    Connection conn = null;
    private static String CLASS_NAME_DAO =
        "com.seatech.ttsp.quyettoan.QuyetToanDAO";
    private static String CLASS_NAME_VO =
        "com.seatech.ttsp.quyettoan.QuyetToanVO";
    private static String CLASS_NAME_QTOANTQ_VO =
        "com.seatech.ttsp.quyettoan.BKE_QuyetToanVO";
	//20171115 Quang VB them moi VO
    private static String CLASS_NAME_UPDATEQTOAN_VO =
        "com.seatech.ttsp.quyettoan.UpdateQuyetToanVO";
    private static String CLASS_NAME_TT_VO =
        "com.seatech.ttsp.quyettoan.TThaiVO";
    private static String DD_MM_YYYY_HH_MI_SS = "dd/MM/yyyy HH:mm:ss";
    private static String STRING_EMPTY = "";
    private static String CLASS_NAME_LENHQTTC =
        "com.seatech.ttsp.quyettoan.XuLyLenhQuyetToanThuCongVO";

    public QuyetToanDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @see: Lay danh sach LTT co phan trang cho tra cuu
     * @param:
     * @return: Danh sach LTT theo so dong truuyen vao
     */
    public Collection getTCuuLQTListWithPading(String whereClause,
                                               Vector params, Integer page,
                                               Integer count,
                                               Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, d.rv_meaning as mo_ta_trang_thai, decode(a.lai_phi,'02','TTSGD',decode(a.ma_kb,'01701004','TTSGD',decode(k.trang_thai,'04','DG',l.rv_meaning))) as mo_ta_trang_thai_dv,  a.nhkb_chuyen, a.nhkb_nhan,to_char(a.ngay_htoan,'dd/mm/yyyy') ngay_htoan,to_char(a.ngay_qtoan,'dd/mm/yyyy') ngay_qtoan, ");
            strSQL.append(" e.ten AS ten_don_vi_nhan, f.ten AS ten_don_vi_chuyen, i.ten as ten_kb_tw , h.ten ten_kb_huyen, ");
            strSQL.append(" to_char(a.ngay_insert,'dd/mm/yyyy') ngay_insert, a.loai_qtoan, a.qtoan_dvi, a.so_tchieu, ");
            strSQL.append(" a.nguoi_nhap_nh,to_char(a.ngay_nhap_nh,'dd/mm/yyyy') ngay_nhap_nh , a.nguoi_ks_nh,to_char(a.ngay_ks_nh,'dd/mm/yyyy') ngay_ks_nh , ");
            strSQL.append(" a.ndung_tt, a.so_tien, a.ma_nt, a.ttv_chuyen_ks, (b.domain || '/' || b.ma_nsd) as ma_ttv_hoanthien, ");
            strSQL.append(" b.ten_nsd as ten_ttv_hoanthien, (c.domain || '/' || c.ma_nsd) as ma_ktt_hoanthien, ");
            strSQL.append(" c.ten_nsd  as ten_ktt_hoanthien, to_char(a.ngay_chuyen_ks,'dd/mm/yyyy') ngay_chuyen_ks, ");
            strSQL.append(" a.ktt_ks,to_char(a.ngay_ks,'dd/mm/yyyy') ngay_ks, a.chu_ky, a.tk_chuyen, a.ma_nh_chuyen, a.ten_nh_chuyen, a.ten_kh_chuyen, a.tt_kh_chuyen, ");
            strSQL.append(" a.tk_nhan, a.ma_nh_nhan, a.ten_nh_nhan, a.ten_kh_nhan, a.tt_kh_nhan, a.loai_hach_toan, a.tt_in, a.trang_thai, a.err_code, ");
            strSQL.append(" decode(a.err_desc,null,null,a.err_code || ' - ' || a.err_desc) as err_desc,a.msg_id,a.so_bk,to_char(a.ngay_gui,'dd/mm/yyyy') ngay_gui, a.ma_tchieu_gd,a.ldo_hach_toan, a.ldo_day_lai,a.ma_kb, ");
            strSQL.append(" a.id_ref, a.lai_phi, decode(k.err_desc,null,null,k.err_code || ' - ' || k.err_desc) as err_desc_dv  FROM sp_quyet_toan a left join sp_nsd b on b.id = a.ttv_chuyen_ks ");
            strSQL.append(" left join sp_nsd c on c.id = a.ktt_ks left JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value  and d.rv_domain = 'SP_QT.TRANG_THAI' ");
            strSQL.append(" left join sp_dm_ngan_hang e on a.nhkb_nhan=e.ma_nh left join sp_dm_ngan_hang f on a.nhkb_chuyen=f.ma_nh ");
            strSQL.append(" left join sp_dm_manh_shkb g on g.ma_nh=a.ma_kb left join sp_dm_htkb h on h.ma = g.shkb ");
            strSQL.append(" left join sp_dm_htkb i on i.ma = h.ma_cha left join sp_quyet_toan k on a.id_ref= k.id_ref and k.qtoan_dvi='N' ");
            strSQL.append(" left join sp_dm_ma_thamchieu l ON k.trang_thai = l.rv_low_value and l.rv_domain = 'SP_QT.TRANG_THAI' ");
            strSQL.append(" left join sp_066 sp on a.mt_refid = sp.id ");
            strSQL.append(" WHERE  a.qtoan_dvi='Y' ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                strSQL.append(" And " + whereClause);
            }
            strSQL.append(" order by i.ma,a.nhkb_chuyen,a.id ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), params, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getLQTListWithPading(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    /**
     * @see: Lay danh sach LTT co phan trang
     * @param:
     * @return: Danh sach LTT theo so dong truuyen vao
     */
    public Collection getLQTListWithPading(String whereClause, Vector params,
                                           Integer page, Integer count,
                                           Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, d.rv_meaning as mo_ta_trang_thai,  a.nhkb_chuyen, a.nhkb_nhan,to_char(a.ngay_htoan,'dd/mm/yyyy') ngay_htoan,to_char(a.ngay_qtoan,'dd/mm/yyyy') ngay_qtoan, ");
            strSQL.append(" e.ten AS ten_don_vi_nhan, f.ten AS ten_don_vi_chuyen, i.ten as ten_kb_tw , h.ten ten_kb_huyen, ");
            strSQL.append(" to_char(a.ngay_insert,'dd/mm/yyyy') ngay_insert, a.loai_qtoan, a.qtoan_dvi, a.so_tchieu, ");
            strSQL.append(" a.nguoi_nhap_nh,to_char(a.ngay_nhap_nh,'dd/mm/yyyy') ngay_nhap_nh , a.nguoi_ks_nh,to_char(a.ngay_ks_nh,'dd/mm/yyyy') ngay_ks_nh , ");
            strSQL.append(" a.ndung_tt, a.so_tien, a.ma_nt, a.ttv_chuyen_ks, (b.domain || '/' || b.ma_nsd) as ma_ttv_hoanthien, ");
            strSQL.append(" b.ten_nsd as ten_ttv_hoanthien, (c.domain || '/' || c.ma_nsd) as ma_ktt_hoanthien, ");
            strSQL.append(" c.ten_nsd  as ten_ktt_hoanthien, to_char(a.ngay_chuyen_ks,'dd/mm/yyyy') ngay_chuyen_ks, ");
            strSQL.append(" a.ktt_ks,to_char(a.ngay_ks,'dd/mm/yyyy') ngay_ks, a.chu_ky, a.tk_chuyen, a.ma_nh_chuyen, a.ten_nh_chuyen, a.ten_kh_chuyen, a.tt_kh_chuyen, ");
            strSQL.append(" a.tk_nhan, a.ma_nh_nhan, a.ten_nh_nhan, a.ten_kh_nhan, a.tt_kh_nhan, a.loai_hach_toan, a.tt_in, a.trang_thai, a.err_code, ");
            strSQL.append(" decode(a.err_desc,null,null,a.err_code || ' - ' || a.err_desc) as err_desc,a.msg_id,a.so_bk,to_char(a.ngay_gui,'dd/mm/yyyy') ngay_gui, a.ma_tchieu_gd,a.ldo_hach_toan, a.ldo_day_lai,a.ma_kb, ");
            strSQL.append(" a.id_ref, a.lai_phi  FROM sp_quyet_toan a left join sp_nsd b on b.id = a.ttv_chuyen_ks ");
            strSQL.append(" left join sp_nsd c on c.id = a.ktt_ks left JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value ");
            strSQL.append(" left join sp_dm_ngan_hang e on a.nhkb_nhan=e.ma_nh left join sp_dm_ngan_hang f on a.nhkb_chuyen=f.ma_nh ");
            strSQL.append(" left join sp_dm_manh_shkb g on g.ma_nh=a.ma_kb left join sp_dm_htkb h on h.ma = g.shkb ");
            strSQL.append(" left join sp_dm_htkb i on i.ma = h.ma_cha ");
            strSQL.append(" WHERE  d.rv_domain = 'SP_QT.TRANG_THAI' ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                strSQL.append(" And " + whereClause);
            }
            strSQL.append(" order by i.ma,a.nhkb_chuyen,a.id ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), params, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getLQTListWithPading(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    public BigDecimal getTongTien(String whereClause,
                                  Vector params) throws Exception {
        BigDecimal result = new BigDecimal(0);
        StringBuilder strSQL = new StringBuilder();
        try {
            strSQL.append(" SELECT sum(a.so_tien) tong_tien  FROM sp_quyet_toan a left join sp_nsd b on b.id = a.ttv_chuyen_ks ");
            strSQL.append(" left join sp_nsd c on c.id = a.ktt_ks left JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value ");
            strSQL.append(" left join sp_dm_ngan_hang e on a.nhkb_nhan=e.ma_nh left join sp_dm_ngan_hang f on a.nhkb_chuyen=f.ma_nh ");
            strSQL.append(" left join sp_dm_manh_shkb g on g.ma_nh=a.ma_kb left join sp_dm_htkb h on h.ma = g.shkb ");
            strSQL.append(" left join sp_dm_htkb i on i.ma = h.ma_cha ");
            strSQL.append(" WHERE  d.rv_domain = 'SP_QT.TRANG_THAI' ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                strSQL.append(" And " + whereClause);
            }
            ResultSet rs =
                executeSelectStatement(strSQL.toString(), params, conn);
            if (rs.next()) {
                result = rs.getBigDecimal(1);
            }
            rs.close();
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTongTien(): " +
                                   ex.getMessage(), ex);
        }
        return result;
    }

    public Collection getBKeQToanList(String whereClause,
                                      Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.trang_thai,to_char(a.ngay_tao,'dd/mm/yyyy') ngay_tao, a.nguoi_tao, a.nguoi_ks, ");
            strSQL.append("(select ten_nsd from sp_nsd where id=a.nguoi_tao) ten_nguoi_tao, ");
            strSQL.append("(select ten_nsd from sp_nsd where id=a.nguoi_ks) ten_nguoi_ks, ");
            strSQL.append("to_char(a.ngay_ks,'dd/mm/yyyy') ngay_ks, a.so_but_toan, a.so_tien,to_char(a.ngay_htoan,'dd/mm/yyyy') ngay_htoan, a.ky_hieu_bke, ");
            strSQL.append("a.tcg_ngan_hang, a.tcg_loai_qtoan, a.tcg_kb_tinh,to_char(a.tcg_ngay_qtoan,'dd/mm/yyyy') tcg_ngay_qtoan,to_char(a.tcg_ngay_ttoan,'dd/mm/yyyy') tcg_ngay_ttoan, a.lydo_daylai, a.loai_htoan, a.tcg_loai_tk, a.tcg_loai_tien ");
            strSQL.append("FROM sp_bke_qtoan a ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                if (whereClause.toUpperCase().indexOf("WHERE") == -1)
                    strSQL.append(" WHERE " + whereClause);
                else
                    strSQL.append(whereClause);
            }
            strSQL.append("order by a.trang_thai,a.id");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_QTOANTQ_VO,
                                           conn);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getBKeQToanList(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getQTList(String whereClause,
                                Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.nhkb_chuyen, a.nhkb_nhan, a.ngay_htoan, a.ngay_qtoan, f.ten AS ten_don_vi_nhan, ");
            strSQL.append(" b.ten AS ten_don_vi_chuyen, a.ngay_insert, a.loai_qtoan, a.qtoan_dvi, a.so_tchieu, a.nguoi_nhap_nh, ");
            strSQL.append(" a.ngay_nhap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, a.ndung_tt, a.so_tien, a.ma_nt, a.ttv_chuyen_ks, ");
            strSQL.append(" (c.domain || '/' || c.ma_nsd ) as ma_ttv_hoanthien, c.ten_nsd as ten_ttv_hoanthien, (e.domain || '/' || e.ma_nsd) as ma_ktt_hoanthien, ");
            strSQL.append(" e.ten_nsd as ten_ktt_hoanthien, a.ngay_chuyen_ks, a.ktt_ks, a.ngay_ks, a.chu_ky, a.tk_chuyen, a.ma_nh_chuyen, a.ten_nh_chuyen, a.ten_kh_chuyen, ");
            strSQL.append(" a.tt_kh_chuyen, a.tk_nhan, a.ma_nh_nhan, a.ten_nh_nhan, a.ten_kh_nhan, a.tt_kh_nhan, a.loai_hach_toan, ");
            strSQL.append(" a.tt_in, a.trang_thai, a.err_code, a.err_desc,a.msg_id,a.so_bk,a.ngay_gui, a.ma_tchieu_gd,a.ldo_hach_toan, ");
            strSQL.append(" a.ldo_day_lai,a.ma_kb, a.id_ref, a.lai_phi, a.nhap_thu_cong ");
            strSQL.append(" FROM sp_quyet_toan a inner join sp_dm_ngan_hang b on b.ma_nh =a.ma_nh_chuyen ");
            strSQL.append(" left join sp_nsd c on c.id= a.ttv_chuyen_ks ");
            strSQL.append(" inner join sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value ");
            strSQL.append(" left join sp_nsd e on e.id= a.ktt_ks ");
            strSQL.append(" left join sp_dm_ngan_hang f on f.ma_nh =a.nhkb_nhan  ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                if (whereClause.toUpperCase().indexOf("WHERE") == -1)
                    strSQL.append(" WHERE " + whereClause);
                else
                    strSQL.append(whereClause);
            }
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getQTList(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    /**
     * @see: lay danh sach trang thai
     * @param: whereclase,param
     * @return : collection
     * */
    public Collection getDSTrang_Thai(String whereClause,
                                      Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.rv_low_value, a.rv_meaning ");
            strSQL.append("FROM sp_dm_ma_thamchieu a ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                if (whereClause.toUpperCase().indexOf("WHERE") == -1)
                    strSQL.append(" WHERE " + whereClause);
                else
                    strSQL.append(whereClause);
            }
            strSQL.append(" order by a.rv_low_value");
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_TT_VO,
                                           conn);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getDSTrang_Thai(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    /**
     * @see: Lay ra chi tiet mot bk quyet toan
     * @param: id
     * @return: BKE_QuyetToanVO
     * */
    public BKE_QuyetToanVO findBKeByPK(Vector params) throws DAOException {
        BKE_QuyetToanVO vo = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.id, a.trang_thai,to_char(a.ngay_tao,'dd/mm/yyyy') ngay_tao, a.nguoi_tao, a.nguoi_ks, ");
            strSQL.append("(select ten_nsd from sp_nsd where id=a.nguoi_tao) ten_nguoi_tao, ");
            strSQL.append("(select ten_nsd from sp_nsd where id=a.nguoi_ks) ten_nguoi_ks,a.trang_thai mo_ta_trang_thai, ");
            strSQL.append("to_char(a.ngay_ks,'dd/mm/yyyy') ngay_ks, a.so_but_toan, a.so_tien,to_char(a.ngay_htoan,'dd/mm/yyyy') ngay_htoan, a.ky_hieu_bke, ");
            strSQL.append("a.tcg_ngan_hang, a.tcg_loai_qtoan, a.tcg_kb_tinh,to_char(a.tcg_ngay_qtoan,'dd/mm/yyyy') tcg_ngay_qtoan, to_char(a.tcg_ngay_ttoan,'dd/mm/yyyy') tcg_ngay_ttoan ,a.lydo_daylai, a.loai_htoan, a.tcg_loai_tk, a.tcg_loai_tien ");
            strSQL.append("FROM sp_bke_qtoan a ");
            strSQL.append(" WHERE a.id=?");
            vo =
 (BKE_QuyetToanVO)findByPK(strSQL.toString(), params, CLASS_NAME_QTOANTQ_VO,
                           conn);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".findBKeByPK(): " +
                                   ex.getMessage(), ex);
        }
        return vo;
    }

    /**
     * @see: Lay ra chi tiet mot lenh quyet toan
     * @param: id
     * @return: QuyetToanVO
     * */
    public QuyetToanVO findByPK(Vector params) throws DAOException {
        QuyetToanVO vo = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            if (params != null) {
                strSQL.append(" SELECT a.id, a.nhkb_chuyen, a.nhkb_nhan, to_char(a.ngay_htoan,'dd/mm/yyyy') ngay_htoan,to_char(a.ngay_qtoan,'dd/mm/yyyy') ngay_qtoan, ");
                strSQL.append("(SELECT ten FROM   sp_dm_ngan_hang WHERE   ma_nh =a.nhkb_nhan ) AS ten_don_vi_nhan, ");
                strSQL.append("(SELECT ten FROM   sp_dm_ngan_hang WHERE   ma_nh =a.ma_nh_chuyen ) AS ten_don_vi_chuyen, ");
                strSQL.append("a.ngay_insert, a.loai_qtoan, a.qtoan_dvi, a.so_tchieu, ");
                strSQL.append("a.nguoi_nhap_nh,to_char(a.ngay_nhap_nh,'dd/mm/yyyy hh:mi:ss') ngay_nhap_nh, a.nguoi_ks_nh, to_char(a.ngay_ks_nh,'dd/mm/yyyy hh:mi:ss') ngay_ks_nh, ");
                strSQL.append("a.ndung_tt, a.so_tien, a.ma_nt, a.ttv_chuyen_ks, ");
                strSQL.append("(select b.domain || '/' || b.ma_nsd  from sp_nsd b where b.id= a.ttv_chuyen_ks) as ma_ttv_hoanthien, ");
                strSQL.append("(select b.ten_nsd  from sp_nsd b where b.id= a.ttv_chuyen_ks) as ten_ttv_hoanthien, ");
                strSQL.append("(select b.domain || '/' || b.ma_nsd  from sp_nsd b where b.id= a.ktt_ks) as ma_ktt_hoanthien, ");
                strSQL.append("(select b.ten_nsd  from sp_nsd b where b.id= a.ktt_ks) as ten_ktt_hoanthien, ");
                strSQL.append("to_char(a.ngay_chuyen_ks,'dd/mm/yyyy') ngay_chuyen_ks, a.ktt_ks,to_char(a.ngay_ks,'dd/mm/yyyy') ngay_ks , a.chu_ky, a.tk_chuyen, ");
                strSQL.append("a.ma_nh_chuyen, a.ten_nh_chuyen, a.ten_kh_chuyen, a.tt_kh_chuyen, ");
                strSQL.append("a.tk_nhan, a.ma_nh_nhan, a.ten_nh_nhan, a.ten_kh_nhan, ");
                strSQL.append("a.tt_kh_nhan,a.loai_hach_toan, a.tt_in, a.trang_thai, a.err_code, a.err_desc,a.msg_id,a.so_bk,a.ngay_gui, ");
                strSQL.append("a.ma_tchieu_gd,a.ldo_hach_toan, a.ldo_day_lai,a.ma_kb, a.id_ref, a.lai_phi,d.rv_meaning as mo_ta_trang_thai, a.loai_tk, a.mt_refid ");
                strSQL.append(" FROM sp_quyet_toan a left JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value");
                strSQL.append(" WHERE d.rv_domain = 'SP_QT.TRANG_THAI' and a.id=?");
                vo =
 (QuyetToanVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".findByPK(): " +
                                   ex.getMessage(), ex);
        }
        return vo;
    }

    /**
     * @param: BKE_QuyetToanVO
     * @return: 1 = update thanh cong; -1 = update khong thanh cong
     * @see: Sua bang ke
     * */
    public int updateBke(BKE_QuyetToanVO vo) throws DAOException {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = null;
        strSQL.append("update sp_bke_qtoan set ");
        try {
            if (vo.getTrang_thai() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("trang_thai = ?");
                } else {
                    strSQL2.append(", trang_thai = ?");
                }
                v_param.add(new Parameter(vo.getTrang_thai(), true));
            }
            if (vo.getLydo_daylai() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("lydo_daylai = ?");
                } else {
                    strSQL2.append(", lydo_daylai = ?");
                }
                v_param.add(new Parameter(vo.getLydo_daylai(), true));
            }
            if (vo.getNgay_ks() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ngay_ks = SYSDATE");
                } else {
                    strSQL2.append(", ngay_ks = SYSDATE");
                }
            }
            if (vo.getNguoi_ks() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("nguoi_ks = ?");
                } else {
                    strSQL2.append(", nguoi_ks = ?");
                }
                v_param.add(new Parameter(vo.getNguoi_ks(), true));
            }
            if (vo.getMsg_id() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("msg_id = ?");
                } else {
                    strSQL2.append(", msg_id = ?");
                }
                v_param.add(new Parameter(vo.getMsg_id(), true));
            }
            if (vo.getChu_ky() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("chu_ky = ?");
                } else {
                    strSQL2.append(", chu_ky = ?");
                }
                v_param.add(new Parameter(vo.getChu_ky(), true));
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

    /**
     * @param: QuyetToanVO
     * @return: 1 = update thanh cong; -1 = update khong thanh cong
     * @see: Sua QuyetToan
     * */
    public int update(QuyetToanVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = null;
        strSQL.append("update sp_quyet_toan set ");
        try {
            if (vo.getTrang_thai() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("trang_thai = ?");
                } else {
                    strSQL2.append(", trang_thai = ?");
                }
                v_param.add(new Parameter(vo.getTrang_thai(), true));
            }
            if (vo.getLoai_hach_toan() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("loai_hach_toan = ?");
                } else {
                    strSQL2.append(", loai_hach_toan = ?");
                }
                v_param.add(new Parameter(vo.getLoai_hach_toan(), true));
            }
            if (vo.getTtv_chuyen_ks() != null && vo.getTtv_chuyen_ks() != 0) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ttv_chuyen_ks = ?");
                } else {
                    strSQL2.append(", ttv_chuyen_ks = ?");
                }
                v_param.add(new Parameter(vo.getTtv_chuyen_ks(), true));
            }
            if (vo.getMsg_id() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("msg_id = ?");
                } else {
                    strSQL2.append(", msg_id = ?");
                }
                v_param.add(new Parameter(vo.getMsg_id(), true));
            }
            if (vo.getNgay_chuyen_ks() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ngay_chuyen_ks = ?");
                } else {
                    strSQL2.append(", ngay_chuyen_ks = ?");
                }
                v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_chuyen_ks(),
                                                                      "dd/MM/yyyy"),
                                              true));
            }
            if (vo.getKtt_ks() != null && vo.getKtt_ks() != 0) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ktt_ks = ?");
                } else {
                    strSQL2.append(", ktt_ks = ?");
                }
                v_param.add(new Parameter(vo.getKtt_ks(), true));
            }
            if (vo.getNgay_ks() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ngay_ks = SYSDATE");
                } else {
                    strSQL2.append(", ngay_ks = SYSDATE");
                }
            }
            //                v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ks(),
            //                                                                      "dd/MM/yyyy"),
            //                                              true));
            if (vo.getLdo_hach_toan() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ldo_hach_toan = ?");
                } else {
                    strSQL2.append(", ldo_hach_toan = ?");
                }
                v_param.add(new Parameter(vo.getLdo_hach_toan(), true));
            }
            if (vo.getLdo_day_lai() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ldo_day_lai = ?");
                } else {
                    strSQL2.append(", ldo_day_lai = ?");
                }
                v_param.add(new Parameter(vo.getLdo_day_lai(), true));
            }
            if (vo.getSo_bk() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("so_bk = ?");
                } else {
                    strSQL2.append(", so_bk = ?");
                }
                v_param.add(new Parameter(vo.getSo_bk(), true));
            }
            if (vo.getTt_in() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("tt_in = ?");
                } else {
                    strSQL2.append(", tt_in = ?");
                }
                v_param.add(new Parameter(vo.getTt_in(), true));
            }
            if (vo.getChu_ky() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("chu_ky = ?");
                } else {
                    strSQL2.append(", chu_ky = ?");
                }
                v_param.add(new Parameter(vo.getChu_ky(), true));
            }
			//20171207 QuangVB them moi Nguoi_ks_nh vao cau tra cuu theo yeu cau nang cap ngoai hop dong 2017
            if(vo.getNguoi_ks_nh() != null){
                if(strSQL2 == null){
                  strSQL2 = new StringBuffer();
                  strSQL2.append("nguoi_ks_nh = ?");
                }else{
                  strSQL2.append(", nguoi_ks_nh = ?");
                }
              v_param.add(new Parameter(vo.getNguoi_ks_nh(), true));
            }
			//20171120 ThuongDT bo sung them ngay insert lay quyet toan trong ngay
            strSQL2.append(", ngay_insert = sysdate");
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

    /**
     * @param: QuyetToanVO
     * @return: 1 = update thanh cong; -1 = update khong thanh cong
     * @see: Sua QuyetToan
     * */
    public int remove(QuyetToanVO vo, String whereClause,
                      String sobk) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = null;
        strSQL.append("update sp_quyet_toan set ");
        try {
            if (vo.getTrang_thai() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("trang_thai = ?");
                } else {
                    strSQL2.append(", trang_thai = ?");
                }
                v_param.add(new Parameter(vo.getTrang_thai(), true));
            }
            if (vo.getSo_bk() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("so_bk = ?");
                } else {
                    strSQL2.append(", so_bk = ?");
                }
                v_param.add(new Parameter(vo.getSo_bk(), true));
            }
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL2.append(" WHERE ");
                strSQL2.append(whereClause);
                v_param.add(new Parameter(sobk, true));

            }
            strSQL.append(strSQL2.toString());
            return executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".remove(): " +
                                   ex.getMessage(), ex);
        }
    }

    /**
     * @param: BKE_QUYETTOAN_VO
     * @return: String "-1" = insert ko thanh cong;
     * @see: Them moi bang ke
     * */
    public int insert(BKE_QuyetToanVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        try {
            if (vo.getId() != null && !STRING_EMPTY.equals(vo.getId())) {
                strSQL.append("insert into sp_bke_qtoan (id ");
                strSQL2.append(" ) values ( ");
                strSQL2.append("? ");
                v_param.add(new Parameter(vo.getId(), true));
                if (vo.getTrang_thai() != null &&
                    !STRING_EMPTY.equals(vo.getTrang_thai())) {
                    strSQL.append(", trang_thai");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getTrang_thai(), true));
                }
                if (vo.getNgay_tao() != null &&
                    !STRING_EMPTY.equals(vo.getNgay_tao())) {
                    strSQL.append(", ngay_tao");
                    strSQL2.append(", ?");
                    v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_tao().length() ==
                                                                          10 ?
                                                                          vo.getNgay_tao() +
                                                                          " 00:00:00" :
                                                                          vo.getNgay_tao(),
                                                                          DD_MM_YYYY_HH_MI_SS),
                                                  true));
                }
                if (vo.getNguoi_tao() != null &&
                    !STRING_EMPTY.equals(vo.getNguoi_tao())) {
                    strSQL.append(", nguoi_tao");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getNguoi_tao(), true));
                }
                if (vo.getNguoi_ks() != null &&
                    !STRING_EMPTY.equals(vo.getNguoi_ks())) {
                    strSQL.append(", nguoi_ks");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getNguoi_ks(), true));
                }
                if (vo.getNgay_ks() != null &&
                    !STRING_EMPTY.equals(vo.getNgay_ks())) {
                    strSQL.append(", ngay_ks");
                    strSQL2.append(", ?");
                    v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ks().length() ==
                                                                          10 ?
                                                                          vo.getNgay_ks() +
                                                                          " 00:00:00" :
                                                                          vo.getNgay_ks(),
                                                                          DD_MM_YYYY_HH_MI_SS),
                                                  true));
                }
                if (vo.getSo_but_toan() != null &&
                    !STRING_EMPTY.equals(vo.getSo_but_toan())) {
                    strSQL.append(", so_but_toan");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getSo_but_toan(), true));
                }
                if (vo.getSo_tien() != null &&
                    !STRING_EMPTY.equals(vo.getSo_tien())) {
                    strSQL.append(", so_tien");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getSo_tien(), true));
                }
                if (vo.getNgay_htoan() != null &&
                    !STRING_EMPTY.equals(vo.getNgay_htoan())) {
                    strSQL.append(", ngay_htoan");
                    strSQL2.append(", ?");
                    v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_htoan().length() ==
                                                                          10 ?
                                                                          vo.getNgay_htoan() +
                                                                          " 00:00:00" :
                                                                          vo.getNgay_htoan(),
                                                                          DD_MM_YYYY_HH_MI_SS),
                                                  true));
                }
                if (vo.getKy_hieu_bke() != null &&
                    !STRING_EMPTY.equals(vo.getKy_hieu_bke())) {
                    strSQL.append(", ky_hieu_bke");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getKy_hieu_bke(), true));
                }
                if (vo.getTcg_ngan_hang() != null &&
                    !STRING_EMPTY.equals(vo.getTcg_ngan_hang())) {
                    strSQL.append(", tcg_ngan_hang");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getTcg_ngan_hang(), true));
                }
                if (vo.getTcg_loai_qtoan() != null &&
                    !STRING_EMPTY.equals(vo.getTcg_loai_qtoan())) {
                    strSQL.append(", tcg_loai_qtoan");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getTcg_loai_qtoan(), true));
                }
                if (vo.getTcg_kb_tinh() != null &&
                    !STRING_EMPTY.equals(vo.getTcg_kb_tinh())) {
                    strSQL.append(", tcg_kb_tinh");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getTcg_kb_tinh(), true));
                }
                if (vo.getTcg_ngay_qtoan() != null &&
                    !STRING_EMPTY.equals(vo.getTcg_ngay_qtoan())) {
                    strSQL.append(", tcg_ngay_qtoan");
                    strSQL2.append(", ?");
                    v_param.add(new DateParameter(StringUtil.StringToDate(vo.getTcg_ngay_qtoan().length() ==
                                                                          10 ?
                                                                          vo.getTcg_ngay_qtoan() +
                                                                          " 00:00:00" :
                                                                          vo.getTcg_ngay_qtoan(),
                                                                          DD_MM_YYYY_HH_MI_SS),
                                                  true));
                }
                if (vo.getTcg_ngay_ttoan() != null &&
                    !STRING_EMPTY.equals(vo.getTcg_ngay_ttoan())) {
                    strSQL.append(", tcg_ngay_ttoan");
                    strSQL2.append(", ?");
                    v_param.add(new DateParameter(StringUtil.StringToDate(vo.getTcg_ngay_ttoan().length() ==
                                                                          10 ?
                                                                          vo.getTcg_ngay_ttoan() +
                                                                          " 00:00:00" :
                                                                          vo.getTcg_ngay_ttoan(),
                                                                          DD_MM_YYYY_HH_MI_SS),
                                                  true));
                }
                strSQL.append(strSQL2.toString());
                strSQL.append(")");
            }
            return executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".insert(): " +
                                   ex.getMessage(), ex);
        }
    }

    public Collection getQTListForHachToan(String whereClause,
                                           Vector params) throws Exception {
        Collection reval = null;
        StringBuffer sbSQL = new StringBuffer();
        try {
            sbSQL.append("SELECT q.id, q.lai_phi, q.id_ref, to_char(q.ngay_insert,'DD-MM-YYYY') ngay_insert, ");
            sbSQL.append("q.nhkb_chuyen, c.ten, q.nhkb_nhan, to_char(q.ngay_htoan,'DD-MM-YYYY') ngay_htoan, q.loai_qtoan, ");
            sbSQL.append("q.ndung_tt, q.so_tien, q.ma_nt, q.loai_hach_toan, q.so_bk, ");
            sbSQL.append("(select b.shkb from sp_dm_manh_shkb b where b.ma_nh = q.ma_kb) ma_kb, q.loai_tk ");
            sbSQL.append("FROM sp_quyet_toan q, sp_dm_ngan_hang c WHERE (1=1) AND q.nhkb_chuyen= c.ma_nh ");
            sbSQL.append(whereClause);
            reval =
                    executeSelectStatement(sbSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getQTListForHachToan(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }

    public int getCountQToan(String whereClause,
                             Vector params) throws Exception {
        StringBuffer sbSQL = new StringBuffer();
        try {
            sbSQL.append("SELECT count(a.id) as tong_so_lenh ");
            sbSQL.append("FROM sp_quyet_toan a where ");
            sbSQL.append(whereClause);
            ResultSet rs =
                executeSelectStatement(sbSQL.toString(), params, conn);
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new Exception(" Kh&#244;ng th&#7875; th&#7921;c hi&#7879;n ch&#7913;c n&#432;ng n&#224;y !");
            }
        } catch (Exception ex) {
            throw ex;
        }

    }

    public Collection getTCuuBKe_ptrang(String strWhere, Vector vParam,
                                        Integer page, Integer count,
                                        Integer[] totalCount) throws Exception {
        String strSQL = "";
        try {
            strSQL =
                    " SELECT a.id, a.trang_thai, TO_CHAR (a.ngay_tao,'dd/mm/yyyy') ngay_tao, a.tcg_loai_qtoan, c.ten, " +
                    " d.ten_nsd nguoi_tao, a.nguoi_ks, (SELECT ten_nsd FROM sp_nsd" +
                    " WHERE id = a.nguoi_tao) ten_nguoi_tao," +
                    " (SELECT	ten_nsd FROM	sp_nsd WHERE id = a.nguoi_ks)" +
                    " ten_nguoi_ks, TO_CHAR(a.ngay_ks,'dd/mm/yyyy') ngay_ks," +
                    " a.so_but_toan, a.so_tien," +
                    " TO_CHAR(a.ngay_htoan,'dd/mm/yyyy') ngay_htoan, a.ky_hieu_bke," +
                    " a.tcg_ngan_hang, a.tcg_loai_qtoan, a.tcg_kb_tinh," +
                    " TO_CHAR(a.tcg_ngay_qtoan, 'dd/mm/yyyy') tcg_ngay_qtoan," +
                    " TO_CHAR(a.tcg_ngay_ttoan, 'dd/mm/yyyy') tcg_ngay_ttoan," +
                    " a.lydo_daylai, a.loai_htoan, a.tcg_loai_tk tcg_loai_tk, a.tcg_loai_tien " +
                    " FROM	sp_bke_qtoan a, sp_dm_nh_ho b, sp_dm_ngan_hang c, sp_nsd d " +
                    " WHERE	a.tcg_ngan_hang = b.ma_dv and b.ma_nh = c.ma_nh and a.nguoi_tao= d.id " +
                    strWhere;

            strSQL += " order by a.ngay_htoan, a.id";

            return executeSelectWithPaging(conn, strSQL.toString(), vParam,
                                           CLASS_NAME_QTOANTQ_VO, page, count,
                                           totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getBKeQToanList(): " +
                                   ex.getMessage(), ex);
        }
    }


    public Collection<QuyetToanVO> getTtien_Tmon(String strWhere,
                                                 Vector vParam) throws Exception {

        Collection reval = null;
        try {

            String strSQL = "";

            strSQL +=
                    "  SELECT  COUNT (1) AS tong_mon, SUM (a.so_tien) tong_tien" +
                    " FROM sp_quyet_toan a LEFT JOIN sp_nsd b" +
                    " ON b.id = a.ttv_chuyen_ks ";
            strSQL +=
                    " left join sp_nsd c on c.id = a.ktt_ks left JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value  and d.rv_domain = 'SP_QT.TRANG_THAI' ";
            strSQL +=
                    " left join sp_dm_ngan_hang e on a.nhkb_nhan=e.ma_nh left join sp_dm_ngan_hang f on a.nhkb_chuyen=f.ma_nh ";
            strSQL +=
                    " left join sp_dm_manh_shkb g on g.ma_nh=a.ma_kb left join sp_dm_htkb h on h.ma = g.shkb ";
            strSQL +=
                    " left join sp_dm_htkb i on i.ma = h.ma_cha left join sp_quyet_toan k on a.id_ref= k.id_ref and k.qtoan_dvi='N' ";
            strSQL +=
                    " left JOIN sp_dm_ma_thamchieu l ON k.trang_thai = l.rv_low_value and l.rv_domain = 'SP_QT.TRANG_THAI' ";
			//20171207 Quang them left join sp_066 theo mt_refid		
            strSQL += " left join sp_066 sp on a.mt_refid = sp.id ";
            strSQL += " WHERE  a.qtoan_dvi='Y' ";

            if (strWhere != null && !STRING_EMPTY.equals(strWhere))
                strSQL += "and" + strWhere;

            reval =
                    executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                           this.conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_VO + ".getTtien_Tmon(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
        return reval;
    }

    public int getCountQT(String whereClause,
                          Vector paramsDC) throws DAOException {
        int result = 0;
        try {
            String strSQL = "";
            strSQL += "select count(1) from sp_quyet_toan ";
            if (whereClause != null && !"".equals(whereClause)) {
                strSQL += "where " + whereClause;
            }
            ResultSet rs = executeSelectStatement(strSQL, paramsDC, this.conn);
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getCountQT(): " +
                                 ex.getMessage(), ex);
            daoEx.printStackTrace();
            throw daoEx;
        }
        return result;
    }

	/**
	*@create: QuangVB
	*@create-date: 15/11/2017
	*@see: them moi ham lay thong tin quyet toan
	*@param: id_ref id ref, vParams Parameter	
	*/
    public UpdateQuyetToanVO getThongTinQuyetToan(String id_ref,
                                                  Vector vParams) throws Exception {
        Collection reval = null;
        UpdateQuyetToanVO vo = null;
        try {
            String strQuery =
                "select a.id, a.nhkb_chuyen, a.nhkb_nhan, b.ten ten_nhkb_nhan, to_char(a.ngay_htoan,'dd/mm/yyyy') ngay_htoan, " +
                "to_char(a.ngay_qtoan,'dd/mm/yyyy') ngay_qtoan, loai_qtoan, a.so_tchieu, " +
                "a.nguoi_nhap_nh, to_char(a.ngay_nhap_nh,'dd/mm/yyyy hh24:mi:ss') ngay_nhap_nh, a.nguoi_ks_nh, " +
                "to_char(a.ngay_ks_nh,'dd/mm/yyyy hh24:mi:ss') ngay_ks_nh, a.ndung_tt, a.so_tien, a.ma_nt, " +
                "a.tk_chuyen, a.ma_nh_chuyen, a.ten_nh_chuyen, a.ten_kh_chuyen, a.tk_nhan, a.ma_nh_nhan, " +
                "a.ten_nh_nhan, a.ten_kh_nhan, a.loai_hach_toan, a.ldo_hach_toan, a.ma_tchieu_gd from sp_quyet_toan a, sp_dm_ngan_hang b ";
            strQuery +=
                    "where a.nhkb_nhan = b.ma_nh and a.id='" + id_ref + "' ";
            vo =
 (UpdateQuyetToanVO)findByPK(strQuery, CLASS_NAME_UPDATEQTOAN_VO, conn);
            return vo;
        } catch (Exception e) {
            throw e;
        }
    }
	/**
	*@create: QuangVB
	*@create-date: 15/11/2017
	*@see: them moi ham lay thong tin quyet toan theo id
	*@param: id id sp_quyet_toan, vParams Parameter	
	*/
    public XuLyLenhQuyetToanThuCongVO getThongTinQuyetToanById(String id,
                                                               Vector vParams) throws Exception {
        XuLyLenhQuyetToanThuCongVO vo = null;
        try {
            String strQuery =
                "SELECT a.id, a.nhkb_chuyen, a.nhkb_nhan, to_char(a.ngay_htoan,'dd/mm/yyyy') ngay_htoan, to_char(a.ngay_qtoan,'dd/mm/yyyy') ngay_qtoan," +
                " to_char(a.ngay_insert,'dd/mm/yyyy hh24:mi:ss') ngay_insert, a.loai_qtoan, a.qtoan_dvi, a.so_tchieu," +
                " a.nguoi_nhap_nh, to_char(a.ngay_nhap_nh,'dd/mm/yyyy hh24:mi:ss') ngay_nhap_nh, a.nguoi_ks_nh, to_char(a.ngay_ks_nh,'dd/mm/yyyy hh24:mi:ss') ngay_ks_nh," +
                " a.ndung_tt, a.so_tien, a.ma_nt, a.ttv_chuyen_ks," +
                " to_char(a.ngay_chuyen_ks,'dd/mm/yyyy') ngay_chuyen_ks, a.ktt_ks, to_char(a.ngay_ks,'dd/mm/yyyy hh24:mi:ss') ngay_ks, a.chu_ky, a.tk_chuyen," +
                " a.ma_nh_chuyen, a.ten_nh_chuyen, a.ten_kh_chuyen, a.tt_kh_chuyen," +
                " a.tk_nhan, a.ma_nh_nhan, a.ten_nh_nhan, a.ten_kh_nhan," +
                " a.tt_kh_nhan, a.tt_in, a.trang_thai," +
                " a.so_bk, a.loai_hach_toan, to_char(a.ngay_gui,'dd/mm/yyyy hh24:mi:ss') ngay_gui, a.ma_tchieu_gd," +
                " a.ldo_hach_toan, a.ldo_day_lai, a.ma_kb, a.id_ref," +
                " a.trang_thai_dc, a.lai_phi, a.loai_tk," +
                " a.nhap_thu_cong " + " FROM sp_quyet_toan a where a.id='" +
                id + "'";
            System.out.print(strQuery);
            vo =
 (XuLyLenhQuyetToanThuCongVO)findByPK(strQuery, CLASS_NAME_LENHQTTC, conn);
            return vo;
        } catch (Exception e) {
            throw e;
        }
    }
   /**
	*@create: QuangVB
	*@create-date: 07/12/2017
	*@see: them moi ham inert quyet toan thu cong
	*@param: XuLyLenhQuyetToanThuCongVO VO quyet toan, vParams Parameter	
	*/
    public int insertLenhQuyetToan(XuLyLenhQuyetToanThuCongVO vo,
                                   Vector vParams) throws Exception {
        try {
            String strQuery =
                "insert into sp_quyet_toan " + "(id, nhkb_chuyen, nhkb_nhan, ngay_htoan, ngay_qtoan," +
                "ngay_insert, loai_qtoan, qtoan_dvi, so_tchieu," +
                "nguoi_nhap_nh, ngay_nhap_nh, nguoi_ks_nh, ngay_ks_nh," +
                "ndung_tt, so_tien, ma_nt, ttv_chuyen_ks," +
                "ngay_chuyen_ks, ktt_ks, ngay_ks, tk_chuyen," +
                "ma_nh_chuyen, ten_nh_chuyen, ten_kh_chuyen," +
                "tk_nhan, ma_nh_nhan, ten_nh_nhan, ten_kh_nhan," +
                "tt_in, trang_thai, so_bk, loai_hach_toan, ngay_gui, ma_tchieu_gd," +
                "ldo_hach_toan, ldo_day_lai, ma_kb, id_ref," +
                "trang_thai_dc, lai_phi, loai_tk,nhap_thu_cong) values " +
                "(?,?,?,to_date(?,'dd/mm/yyyy'),to_date(?,'dd/mm/yyyy')," +
                "to_date(?,'dd/mm/yyyy hh24:mi:ss'),?,?,?," +
                "?,to_date(?,'dd/mm/yyyy hh24:mi:ss'),?,to_date(?,'dd/mm/yyyy hh24:mi:ss')," +
                "?,?,?,?," +
                "to_date(?,'dd/mm/yyyy'),?,to_date(?,'dd/mm/yyyy hh24:mi:ss')," +
                "?,?,?," + "?,?,?,?," +
                "?,?,?,?,?,to_date(?,'dd/mm/yyyy hh24:mi:ss'),?," +
                "?,?,?,?," + "?,?,?,?)";

            vParams.add(new Parameter(vo.getId(), true));
            vParams.add(new Parameter(vo.getNhkb_chuyen(), true));
            vParams.add(new Parameter(vo.getNhkb_nhan(), true));
            vParams.add(new Parameter(vo.getNgay_htoan(), true));
            vParams.add(new Parameter(vo.getNgay_qtoan(), true)); //1

            vParams.add(new Parameter(vo.getNgay_insert(), true));
            vParams.add(new Parameter(vo.getLoai_qtoan(), true));
            vParams.add(new Parameter(vo.getQtoan_dvi(), true));
            vParams.add(new Parameter(vo.getSo_tchieu(), true)); //2

            vParams.add(new Parameter(vo.getNguoi_nhap_nh(), true));
            vParams.add(new Parameter(vo.getNgay_nhap_nh(), true));
            vParams.add(new Parameter(vo.getNguoi_ks_nh(), true));
            vParams.add(new Parameter(vo.getNgay_ks_nh(), true)); //3

            vParams.add(new Parameter(vo.getNdung_tt(), true));
            vParams.add(new Parameter(vo.getSo_tien(), true));
            vParams.add(new Parameter(vo.getMa_nt(), true));
            vParams.add(new Parameter(vo.getTtv_chuyen_ks(), true)); //4

            vParams.add(new Parameter(vo.getNgay_chuyen_ks(), true));
            vParams.add(new Parameter(vo.getKtt_ks(), true));
            vParams.add(new Parameter(vo.getNgay_ks(), true));
            vParams.add(new Parameter(vo.getTk_chuyen(), true)); //5

            vParams.add(new Parameter(vo.getMa_nh_chuyen(), true));
            vParams.add(new Parameter(vo.getTen_nh_chuyen(), true));
            vParams.add(new Parameter(vo.getTen_kh_chuyen(), true)); //6

            vParams.add(new Parameter(vo.getTk_nhan(), true));
            vParams.add(new Parameter(vo.getMa_nh_nhan(), true));
            vParams.add(new Parameter(vo.getTen_nh_nhan(), true));
            vParams.add(new Parameter(vo.getTen_kh_nhan(), true));

            vParams.add(new Parameter(vo.getTt_in(), true));
            vParams.add(new Parameter(vo.getTrang_thai(), true));
            vParams.add(new Parameter(vo.getSo_bk(), true));
            vParams.add(new Parameter(vo.getLoai_hach_toan(), true));
            vParams.add(new Parameter(vo.getNgay_gui(), true));
            vParams.add(new Parameter(vo.getMa_tchieu_gd(), true));

            vParams.add(new Parameter(vo.getLdo_hach_toan(), true));
            vParams.add(new Parameter(vo.getLdo_day_lai(), true));
            vParams.add(new Parameter(vo.getMa_kb(), true));
            vParams.add(new Parameter(vo.getId_ref(), true));

            vParams.add(new Parameter(vo.getTrang_thai_dc(), true));
            vParams.add(new Parameter(vo.getLai_phi(), true));
            vParams.add(new Parameter(vo.getLoai_tk(), true));
            vParams.add(new Parameter(vo.getNhap_thu_cong(), true));

            return (int)executeStatement(strQuery, vParams, conn);

        } catch (Exception e) {
            throw e;
        }
    }
   /**
	*@create: QuangVB
	*@create-date: 07/12/2017
	*@see: them moi ham lay danh muc bang ngan hang HO bo sung them yeu cau tra cuu ngoai hop dong nang cap 2017
	*@param: strWhere menh de where, vParams Parameter	
	*/
    public Collection getDMNH(String strWhere, Vector vParams)throws Exception{
        try{
            String strQuery = "Select distinct ma_dv, ten_nh from sp_dm_nh_ho where ma_dv <> '701' " + strWhere;
            return executeSelectStatement(strQuery, vParams, CLASS_NAME_QTOANTQ_VO, conn);
        }catch(Exception e){
            throw e;
        }
    }
}
