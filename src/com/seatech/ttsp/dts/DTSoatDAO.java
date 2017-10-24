package com.seatech.ttsp.dts;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Collection;
import java.util.Vector;

   /**
     * @modify: ThuongDT
     * @modify-date:12/07/2017
     * @see: lay tat ca rv_domain theo SP_DTS tu dm ma tham chieu
     * @Find: thuongdt-20170712
     */
public class DTSoatDAO extends AppDAO {
    Connection conn = null;
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.dts.DTSoatDAO";
    private static String CLASS_NAME_VO = "com.seatech.ttsp.dts.DTSoatVO";
    private static String DD_MM_YYYY_HH_MI_SS = "dd/MM/yyyy HH:mm:ss";
    private static String STRING_EMPTY = "";

    public DTSoatDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * @param: Menh de where; vector tham so
     * @see: Lay ra danh sach cac DTS
     * @return: Collection
     * */
    public Collection getDTSList(String whereClause,
                                 Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.ltt_id, a.dts_id, a.nhkb_nhan, a.nhkb_chuyen, a.noidung,  a.noidung_traloi, c.ten as ten_don_vi_nhan_tra_soat,");
            strSQL.append(" a.thong_tin_khac, a.ttv_nhan, to_char(a.ngay_nhan,'dd/mm/yyyy hh:mi:ss') ngay_nhan, b.ten as ten_don_vi_tra_soat,");
            strSQL.append(" d.ma_nsd as ma_ttv_nhan, d.ten_nsd ten_ttv_nhan,e.ma_nsd as ma_ktt, e.ten_nsd ten_ktt_duyet,h.tong_sotien AS tong_sotien,");
            strSQL.append(" a.ktt_duyet, to_char(a.ngay_duyet,'dd/mm/yyyy hh:mi:ss') ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai,");
            strSQL.append(" a.nguoi_nhap_nh, to_char(a.ngay_nhap_nh,'dd/mm/yyyy hh24:mi:ss') ngay_nhap_nh, a.nguoi_ks_nh,f.rv_meaning AS mo_ta_trang_thai, ");
            strSQL.append(" ( CASE WHEN a.ltt_id IS NULL AND a.dts_id IS NULL  THEN '197' WHEN a.dts_id IS NOT NULL THEN '196' WHEN a.ltt_id IS NOT NULL AND a.dts_id IS NULL THEN '195' END) ");
            strSQL.append(" AS loai_tra_soat, ");
            strSQL.append(" to_char(a.ngay_ks_nh,'dd/mm/yyyy hh24:mi:ss') ngay_ks_nh,to_char(a.thoi_gian_keo_dai,'dd/mm/yyyy hh:mi:ss') thoi_gian_keo_dai,");
            strSQL.append(" a.chuky_ktt, a.loai_dts, a.tt_in ,a.error_desc ,a.error_code FROM sp_dts a, sp_dm_ngan_hang b, sp_dm_ngan_hang c,sp_nsd d, sp_nsd e,sp_dm_ma_thamchieu f,sp_ltt h");
            strSQL.append(" WHERE a.nhkb_chuyen = b.id AND a.nhkb_nhan = c.id AND a.ttv_nhan = d.id(+) and a.ktt_duyet = e.id(+) and a.trang_thai = f.rv_low_value(+) ");
            strSQL.append(" and a.ltt_id = h.id(+) ");
            strSQL.append(whereClause);
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getDTSList(): " +
                                   ex.getMessage(), ex);
        }

        return reval;
    }

    public DTSoatVO getDTS(String whereClause,
                           Vector params) throws Exception {
        DTSoatVO dtsVO = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.ltt_id, c.rv_meaning as mo_ta_trang_thai, a.so_tchieu, a.nh_tk_chuyen, a.nh_tk_nhan, a.loai_tien, ");
            strSQL.append(" to_char(to_date(ngay_tt,'yyyyMMdd'),'dd/MM/yyyy') ngay_thanh_toan,  a.dts_id, a.nhkb_nhan, a.nhkb_chuyen,");
            strSQL.append(" b.nhkb_nhan as nhkb_nhan_ltt, b.nhkb_chuyen as nhkb_chuyen_ltt, a.noidung,  a.noidung_traloi, a.thong_tin_khac,");
            strSQL.append(" a.ttv_nhan,e.ma_nsd ma_ttv_nhan,h.ten as ten_don_vi_nhan_tra_soat,g.ten as ten_don_vi_tra_soat,");
            strSQL.append(" g.ma_nh as ma_don_vi_tra_soat,h.ma_nh as ma_don_vi_nhan_tra_soat,");
            strSQL.append(" to_char(a.ngay_nhan,'dd/mm/yyyy hh24:mi:ss') ngay_nhan,   a.ktt_duyet, d.ma_nsd  ma_ktt,");
            strSQL.append(" to_char(a.ngay_duyet,'dd/mm/yyyy hh24:mi:ss') ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai,  a.nguoi_nhap_nh,");
            strSQL.append(" to_char(a.ngay_nhap_nh,'dd/mm/yyyy hh24:mi:ss') ngay_nhap_nh, a.nguoi_ks_nh,a.error_code, a.error_desc,");
            strSQL.append(" to_char(a.ngay_ks_nh,'dd/mm/yyyy hh24:mi:ss') ngay_ks_nh, a.chuky_ktt, a.loai_dts, a.tt_in, a.loai_tien  FROM sp_dts a");
            strSQL.append(" left join sp_ltt b on a.ltt_id=b.id left JOIN sp_dm_ma_thamchieu  c ON a.trang_thai=c.rv_low_value");
            strSQL.append(" left join sp_nsd d on a.ktt_duyet = d.id left join sp_nsd e on a.ttv_nhan = e.id");
            strSQL.append(" left join sp_dm_ngan_hang g on a.nhkb_chuyen = g.id left join sp_dm_ngan_hang h on  a.nhkb_nhan = h.id");
            strSQL.append(" WHERE 1=1 ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                strSQL.append(whereClause);
            }
            dtsVO =
                    (DTSoatVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                       conn);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getDTS(): " +
                                   ex.getMessage(), ex);
        }
        return dtsVO;
    }

    /**
     * @see: Lay ra chi tiet mot DTS
     * @param: Menh de where va vetor tham so truyen vao
     * @return: DTSoatVO
     * */
    public DTSoatVO getDTSTuDoByKey(String whereClause,
                                    Vector params) throws Exception {
        DTSoatVO dtsVO = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.ltt_id, c.rv_meaning as mo_ta_trang_thai, ");
            strSQL.append(" to_char(to_date(ngay_tt,'yyyyMMdd'),'dd/MM/yyyy') ngay_thanh_toan, ");
            strSQL.append(" a.dts_id, a.nhkb_nhan, a.nhkb_chuyen, a.noidung, ");
            strSQL.append(" a.noidung_traloi, a.thong_tin_khac,a.ttv_nhan,(SELECT ma_nsd FROM sp_nsd where id=a.ttv_nhan) ma_ttv_nhan,");
            strSQL.append(" to_char(a.ngay_nhan,'dd/mm/yyyy') ngay_nhan,  ");
            strSQL.append(" a.ktt_duyet,(SELECT ma_nsd FROM sp_nsd where id=a.ktt_duyet) ma_ktt, ");
            strSQL.append(" to_char(a.ngay_duyet,'dd/mm/yyyy') ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai, ");
            strSQL.append(" a.nguoi_nhap_nh, to_char(a.ngay_nhap_nh,'dd/mm/yyyy') ngay_nhap_nh, a.nguoi_ks_nh, to_char(a.ngay_ks_nh,'dd/mm/yyyy') ngay_ks_nh, a.chuky_ktt, a.loai_dts, a.tt_in ");
            strSQL.append(" FROM sp_dts a left join sp_ltt b on a.ltt_id=b.id INNER JOIN sp_dm_ma_thamchieu c ON a.trang_thai=c.rv_high_value ");
            strSQL.append("	WHERE 1=1 ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                strSQL.append(whereClause);
            }
            dtsVO =
                    (DTSoatVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                       conn);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getDTS(): " +
                                   ex.getMessage(), ex);
        }
        return dtsVO;
    }

    /**
     * @see: Lay ra chi tiet mot DTS
     * @param: Menh de where va vetor tham so truyen vao
     * @return: DTSoatVO
     * */
    public Collection getDTSByConditionSearch(String whereClause,
                                              Vector params, Integer page,
                                              Integer count,
                                              Integer[] totalCount) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.ltt_id, a.dts_id, a.nhkb_nhan,g.loai_nhom as chuc_danh, (SELECT ten FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_nhan ) AS ten_don_vi_nhan_tra_soat, ");
            strSQL.append(" (SELECT ten	FROM   sp_dm_ngan_hang  WHERE   id =a.nhkb_chuyen ) AS ten_don_vi_tra_soat, (SELECT tong_sotien	FROM   sp_ltt  WHERE   id =a.ltt_id ) AS tong_sotien, ");
            strSQL.append(" a.nhkb_chuyen, a.noidung, ");
            strSQL.append(" a.noidung_traloi, a.thong_tin_khac, a.ttv_nhan, to_char(a.ngay_nhan,'dd/MM/yyyy') AS ngay_nhan, ");
            strSQL.append(" a.ktt_duyet, a.ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai, ");
            strSQL.append(" a.nguoi_nhap_nh, a.ngay_nhap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, ");
            strSQL.append(" a.chuky_ktt, a.loai_dts, a.tt_in, to_char(c.ngay_nhan,'dd/MM/yyyy') AS ngay_lap_lenh, ");
            strSQL.append(" d.rv_meaning AS mo_ta_trang_thai, ( CASE ");
            strSQL.append(" WHEN a.ltt_id IS NULL AND a.dts_id IS NULL  THEN '197' ");
            strSQL.append(" WHEN a.dts_id IS NOT NULL THEN '196' "); //th�m dts tra loi= 197
            strSQL.append(" WHEN a.ltt_id IS NOT NULL AND a.dts_id IS NULL THEN '195' END) ");
            strSQL.append(" AS loai_tra_soat ");
            strSQL.append(" FROM	sp_dts a LEFT JOIN sp_nsd b ON a.ttv_nhan = b.id ");
            strSQL.append(" LEFT JOIN sp_nsd_nhom e on b.id = e.nsd_id LEFT JOIN sp_nhom_nsd g on e.nhom_id = g.id ");
            strSQL.append("	LEFT OUTER JOIN sp_ltt c ON a.ltt_id = c.id ");
            strSQL.append(" INNER JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value ");
            strSQL.append(" WHERE	d.rv_domain = 'SP_DTS.TRANG_THAI' ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                strSQL.append(whereClause);
            }
            return executeSelectWithPaging(conn, strSQL.toString(), params,
                                           CLASS_NAME_VO, page, count,
                                           totalCount);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getDTSByConditionSearch(): " +
                                   ex.getMessage(), ex);

        }

    }
/**
 * 
 * 'SP_DTS%'
 * **/
    public Collection getDTSTQByConditionSearch(String whereClause,
                                                Vector params, Integer page,
                                                Integer count,
                                                Integer[] totalCount) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT   a.id,a.ltt_id,a.dts_id,a.msg_id,SUBSTR(a.id,3,3) di_den,(SELECT c.ten from sp_dm_htkb i,sp_dm_manh_shkb b, sp_dm_htkb c where i.ma = b.shkb and b.ma_nh = ");
            strSQL.append(" CASE WHEN (INSTR (a.id, '701') = 3) THEN f.ma_nh WHEN (INSTR (a.id, '701') <> 3) THEN e.ma_nh END and i.id_cha = c.id) AS ten_kb_tinh,");
            strSQL.append(" CASE WHEN (INSTR (a.id, '701') = 3) THEN f.ma_nh WHEN (INSTR (a.id, '701') <> 3) THEN e.ma_nh END AS ma_kb_huyen,");
            strSQL.append(" CASE WHEN (INSTR (a.id, '701') = 3) THEN f.ten WHEN (INSTR (a.id, '701') <> 3) THEN e.ten END AS ten_kb_huyen,");
            strSQL.append(" CASE WHEN (INSTR (a.id, '701') = 3) THEN e.ma_nh WHEN (INSTR (a.id, '701') <> 3) THEN f.ma_nh END AS ma_nh,");
            strSQL.append(" CASE WHEN (INSTR (a.id, '701') = 3) THEN e.ten WHEN (INSTR (a.id, '701') <> 3) THEN f.ten END AS ten_nh,");
            strSQL.append(" f.ten  AS ten_don_vi_tra_soat, c.tong_sotien AS tong_sotien, a.nhkb_chuyen, a.noidung, a.noidung_traloi,");
            strSQL.append(" a.thong_tin_khac, a.ttv_nhan, TO_CHAR (a.ngay_nhan, 'dd/MM/yyyy') AS ngay_nhan, a.ktt_duyet, a.ngay_duyet,");
            strSQL.append(" a.lydo_ktt_day_lai, a.trang_thai, a.nguoi_nhap_nh, a.ngay_nhap_nh, a.nguoi_ks_nh, a.ngay_ks_nh,");
            strSQL.append(" CASE WHEN (INSTR (a.id, '195') = 6) THEN '195' WHEN (INSTR (a.id, '196') = 6) THEN '196' else '199' END loai_dien,");
            strSQL.append(" a.chuky_ktt, a.loai_dts, a.tt_in, TO_CHAR (c.ngay_nhan, 'dd/MM/yyyy') AS ngay_lap_lenh, d.rv_meaning AS mo_ta_trang_thai,");
            strSQL.append(" (CASE WHEN a.ltt_id IS NULL AND a.dts_id IS NULL THEN '197' WHEN a.dts_id IS NOT NULL THEN '196' WHEN a.ltt_id IS NOT NULL AND a.dts_id IS NULL THEN '195' END) AS loai_tra_soat");
            strSQL.append(" FROM sp_dts a LEFT JOIN sp_nsd b ON a.ttv_nhan = b.id LEFT JOIN sp_ltt c ON a.ltt_id = c.id INNER JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value ");
            strSQL.append(" LEFT JOIN sp_dm_ngan_hang e ON a.nhkb_nhan = e.id LEFT JOIN sp_dm_ngan_hang f ON a.nhkb_chuyen = f.id WHERE d.rv_domain LIKE 'SP_DTS%' ");//thuongdt-2017071 d.rv_domain LIKE 'SP_DTS.%'
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                strSQL.append(whereClause);
            }
            strSQL.append(" order by ma_kb_huyen");
            return executeSelectWithPaging(conn, strSQL.toString(), params,
                                           CLASS_NAME_VO, page, count,
                                           totalCount);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getDTSByConditionSearch(): " +
                                   ex.getMessage(), ex);

        }

    }

    /**
     * @see: Lay ra ResultSet DTS
     * @param: Menh de where va vetor tham so truyen vao
     * @return: ResultSet
     * */
    public ResultSet getDTSByPrint(String whereClause,
                                   Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.ltt_id, a.dts_id, a.nhkb_nhan,g.loai_nhom as chuc_danh, (SELECT ten FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_nhan ) AS ten_don_vi_nhan_tra_soat, ");
            strSQL.append(" (SELECT ten FROM   sp_dm_ngan_hang  WHERE   id =a.nhkb_chuyen ) AS ten_don_vi_tra_soat, ");
            strSQL.append(" a.nhkb_chuyen, a.noidung, ");
            strSQL.append(" a.noidung_traloi, a.thong_tin_khac, a.ttv_nhan, to_char(a.ngay_nhan,'dd/MM/yyyy') AS ngay_nhan, ");
            strSQL.append(" a.ktt_duyet, a.ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai, ");
            strSQL.append(" a.nguoi_nhap_nh, a.ngay_nhap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, ");
            strSQL.append(" a.chuky_ktt, a.loai_dts, a.tt_in, to_char(c.ngay_nhan,'dd/MM/yyyy') AS ngay_lap_lenh, ");
            strSQL.append(" d.rv_meaning AS mo_ta_trang_thai, ( CASE ");
            strSQL.append(" WHEN a.ltt_id IS NULL AND a.dts_id IS NULL  THEN '197' ");
            strSQL.append(" WHEN a.dts_id IS NOT NULL THEN '196' "); //th�m dts tra loi= 197
            strSQL.append(" WHEN a.ltt_id IS NOT NULL AND a.dts_id IS NULL THEN '195' END) ");
            strSQL.append(" AS loai_tra_soat ");
            strSQL.append(" FROM  sp_dts a INNER JOIN sp_nsd b ON a.ttv_nhan = b.id ");
            strSQL.append(" INNER JOIN sp_nsd_nhom e on b.id = e.nsd_id INNER JOIN sp_nhom_nsd g on e.nhom_id = g.id ");
            strSQL.append(" LEFT OUTER JOIN sp_ltt c ON a.ltt_id = c.id ");
            strSQL.append(" INNER JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value ");
            strSQL.append(" WHERE d.rv_domain = 'SP_DTS.TRANG_THAI' ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                strSQL.append(whereClause);
            }
            return executeSelectStatement(strSQL.toString(), params, conn);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getDTSByPrint(): " +
                                   ex.getMessage(), ex);

        }

    }

    /**
     * @see: Lay ra chi tiet mot DTS
     * @param: Menh de where va vetor tham so truyen vao
     * @return: DTSoatVO
     * */
    public Collection getDTSBy4Report(String whereClause, Vector params,
                                      Integer page, Integer count,
                                      Integer[] totalCount) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.ltt_id, a.dts_id, a.nhkb_nhan,(SELECT ten FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_nhan ) AS ten_don_vi_nhan_tra_soat, ");
            strSQL.append(" (SELECT ten FROM   sp_dm_ngan_hang  WHERE   id =a.nhkb_chuyen ) AS ten_don_vi_tra_soat, ");
            strSQL.append(" a.nhkb_chuyen, a.noidung, ");
            strSQL.append(" a.noidung_traloi, a.thong_tin_khac, a.ttv_nhan, to_char(a.ngay_nhan,'dd/MM/yyyy') AS ngay_nhan, ");
            strSQL.append(" a.ktt_duyet, a.ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai, ");
            strSQL.append(" a.nguoi_nhap_nh, a.ngay_nhap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, ");
            strSQL.append(" a.chuky_ktt, a.loai_dts, a.tt_in, to_char(c.ngay_nhan,'dd/MM/yyyy') AS ngay_lap_lenh, ");
            strSQL.append(" d.rv_meaning AS mo_ta_trang_thai, ( CASE ");
            strSQL.append(" WHEN substr(a.id,6,3) = '199'  THEN '199' ");
            strSQL.append(" WHEN substr(a.id,6,3) = '195' THEN '195' "); //th�m dts tra loi= 197
            strSQL.append(" WHEN substr(a.id,6,3) = '299' THEN '299' "); //dien keo dai thoi gian
            strSQL.append(" WHEN substr(a.id,6,3) = '196' THEN '196' END) ");
            strSQL.append(" AS loai_tra_soat ");
            strSQL.append(" FROM  sp_dts a LEFT JOIN sp_nsd b ON a.ttv_nhan = b.id ");
            strSQL.append("  LEFT OUTER JOIN sp_ltt c ON a.ltt_id = c.id ");
            strSQL.append("    INNER JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value ");
            strSQL.append(" WHERE d.rv_domain like 'SP_DTS%' ");
            //          strSQL.append(" SELECT a.id, a.ltt_id, a.dts_id, a.nhkb_nhan,(SELECT ten FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_nhan ) AS ten_don_vi_nhan_tra_soat, ");
            //          strSQL.append(" (SELECT ten FROM   sp_dm_ngan_hang  WHERE   id =a.nhkb_chuyen ) AS ten_don_vi_tra_soat, ");
            //          strSQL.append(" a.nhkb_chuyen, a.noidung, ");
            //          strSQL.append(" a.noidung_traloi, a.thong_tin_khac, a.ttv_nhan, to_char(a.ngay_nhan,'dd/MM/yyyy') AS ngay_nhan, ");
            //          strSQL.append(" a.ktt_duyet, a.ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai, ");
            //          strSQL.append(" a.nguoi_nhap_nh, a.ngay_nhap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, ");
            //          strSQL.append(" a.chuky_ktt, a.loai_dts, to_char(c.ngay_nhan,'dd/MM/yyyy') AS ngay_lap_lenh, ");
            //          strSQL.append(" d.rv_meaning AS mo_ta_trang_thai, ( CASE ");
            //          strSQL.append(" WHEN a.ltt_id IS NULL THEN '197' ");
            //          strSQL.append(" WHEN a.dts_id IS NULL THEN '195' END) ");
            //          strSQL.append(" AS loai_tra_soat ");
            //          strSQL.append(" FROM  sp_dts a INNER JOIN sp_nsd b ON a.ttv_nhan = b.id ");
            //          strSQL.append("  LEFT OUTER JOIN sp_ltt c ON a.ltt_id = c.id ");
            //          strSQL.append("    INNER JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value ");
            //          strSQL.append(" WHERE d.rv_domain = 'SP_DTS.TRANG_THAI' ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                strSQL.append(whereClause);
            }
            return executeSelectWithPaging(conn, strSQL.toString(), params,
                                           CLASS_NAME_VO, page, count,
                                           totalCount);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getDTSBy4Report(): " +
                                   ex.getMessage(), ex);

        }

    }

    /**
     * @see: Lay ra tap hop DTS
     * @param: Menh de where va vetor tham so truyen vao
     * @return: DTSoatVO
     * */
    public ResultSet getDTSByForReport(String whereClause,
                                       Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.ltt_id, a.dts_id, a.nhkb_nhan,(SELECT ten FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_nhan ) AS ten_don_vi_nhan_tra_soat, ");
            strSQL.append(" (SELECT ten FROM   sp_dm_ngan_hang  WHERE   id =a.nhkb_chuyen ) AS ten_don_vi_tra_soat, ");
            strSQL.append(" a.nhkb_chuyen, a.noidung, ");
            strSQL.append(" a.noidung_traloi, a.thong_tin_khac, a.ttv_nhan, to_char(a.ngay_nhan,'dd/MM/yyyy') AS ngay_nhan, ");
            strSQL.append(" a.ktt_duyet, a.ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai, ");
            strSQL.append(" a.nguoi_nhap_nh, a.ngay_nhap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, ");
            strSQL.append(" a.chuky_ktt, a.loai_dts, a.tt_in, to_char(c.ngay_nhan,'dd/MM/yyyy') AS ngay_lap_lenh, ");
            strSQL.append(" d.rv_meaning AS mo_ta_trang_thai, ( CASE ");
            strSQL.append(" WHEN a.ltt_id IS NULL AND a.dts_id IS NULL  THEN '197' ");
            strSQL.append(" WHEN a.dts_id IS NOT NULL THEN '196' "); //th�m dts tra loi= 197
            strSQL.append(" WHEN a.ltt_id IS NOT NULL AND a.dts_id IS NULL THEN '195' END) ");
            strSQL.append(" AS loai_tra_soat ");
            strSQL.append(" FROM  sp_dts a LEFT JOIN sp_nsd b ON a.ttv_nhan = b.id ");
            strSQL.append("  LEFT OUTER JOIN sp_ltt c ON a.ltt_id = c.id ");
            strSQL.append("    INNER JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value ");
            strSQL.append(" WHERE (d.rv_domain = 'SP_DTS.TRANG_THAI' or d.rv_domain = 'SP_DTS_HOI_NH.STATE') ");
            //          strSQL.append(" SELECT a.id, a.ltt_id, a.dts_id, a.nhkb_nhan,(SELECT ten FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_nhan ) AS ten_don_vi_nhan_tra_soat, ");
            //          strSQL.append(" (SELECT ten FROM   sp_dm_ngan_hang  WHERE   id =a.nhkb_chuyen ) AS ten_don_vi_tra_soat, ");
            //          strSQL.append(" a.nhkb_chuyen, a.noidung, ");
            //          strSQL.append(" a.noidung_traloi, a.thong_tin_khac, a.ttv_nhan, to_char(a.ngay_nhan,'dd/MM/yyyy') AS ngay_nhan, ");
            //          strSQL.append(" a.ktt_duyet, a.ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai, ");
            //          strSQL.append(" a.nguoi_nhap_nh, a.ngay_nhap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, ");
            //          strSQL.append(" a.chuky_ktt, a.loai_dts, to_char(c.ngay_nhan,'dd/MM/yyyy') AS ngay_lap_lenh, ");
            //          strSQL.append(" d.rv_meaning AS mo_ta_trang_thai, ( CASE ");
            //          strSQL.append(" WHEN a.ltt_id IS NULL THEN '197' ");
            //          strSQL.append(" WHEN a.dts_id IS NULL THEN '195' END) ");
            //          strSQL.append(" AS loai_tra_soat ");
            //          strSQL.append(" FROM  sp_dts a INNER JOIN sp_nsd b ON a.ttv_nhan = b.id ");
            //          strSQL.append("  LEFT OUTER JOIN sp_ltt c ON a.ltt_id = c.id ");
            //          strSQL.append("    INNER JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_low_value ");
            //          strSQL.append(" WHERE d.rv_domain = 'SP_DTS.TRANG_THAI' ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                strSQL.append(whereClause);
            }
            return executeSelectStatement(strSQL.toString(), params, conn);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getDTSByForReport(): " +
                                   ex.getMessage(), ex);

        }

    }

    /**
     * @see: Lay ra chi tiet mot DTS
     * @param: Menh de where va vetor tham so truyen vao
     * @return: DTSoatVO
     * */
    public Collection getDTSByConditionSearchWithHighValue(String whereClause,
                                                           Vector params,
                                                           Integer page,
                                                           Integer count,
                                                           Integer[] totalCount) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.id, a.ltt_id, a.dts_id,g.loai_nhom as chuc_danh, a.nhkb_nhan,(SELECT ten FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_nhan ) AS ten_don_vi_nhan_tra_soat, ");
            strSQL.append(" (SELECT ten FROM   sp_dm_ngan_hang  WHERE   id =a.nhkb_chuyen ) AS ten_don_vi_tra_soat, ");
            strSQL.append(" a.nhkb_chuyen, a.noidung, ");
            strSQL.append(" a.noidung_traloi, a.thong_tin_khac, a.ttv_nhan, to_char(a.ngay_nhan,'dd/MM/yyyy') AS ngay_nhan, ");
            strSQL.append(" a.ktt_duyet, a.ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai, ");
            strSQL.append(" a.nguoi_nhap_nh, a.ngay_nhap_nh, a.nguoi_ks_nh, a.ngay_ks_nh, ");
            strSQL.append(" a.chuky_ktt, a.loai_dts, a.tt_in, to_char(c.ngay_nhan,'dd/MM/yyyy') AS ngay_lap_lenh, ");
            strSQL.append(" d.rv_meaning AS mo_ta_trang_thai, SUBSTR(a.id,6,3) ");
            strSQL.append(" AS loai_tra_soat ");
            strSQL.append(" FROM  sp_dts a INNER JOIN sp_nsd b ON a.ttv_nhan = b.id ");
            strSQL.append("  LEFT OUTER JOIN sp_ltt c ON a.ltt_id = c.id ");
            strSQL.append("    INNER JOIN sp_dm_ma_thamchieu d ON a.trang_thai = d.rv_high_value ");
            strSQL.append(" INNER JOIN sp_nsd_nhom e on b.id = e.nsd_id INNER JOIN sp_nhom_nsd g on e.nhom_id = g.id ");
            strSQL.append(" WHERE d.rv_domain = 'SP_DTS.TRANG_THAI' ");
            if (whereClause != null &&
                !STRING_EMPTY.equalsIgnoreCase(whereClause)) {
                strSQL.append(whereClause);
            }
            return executeSelectWithPaging(conn, strSQL.toString(), params,
                                           CLASS_NAME_VO, page, count,
                                           totalCount);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getDTSByConditionSearch(): " +
                                   ex.getMessage(), ex);

        }

    }

    /**
     * @see: Lay ra chi tiet mot DTS
     * @param: id
     * @return: DTSoatVO
     * */
    public DTSoatVO getDTSForUpdate(String whereClause,
                                    Vector params) throws Exception {
        DTSoatVO dtsVO = null;
        StringBuffer strSQL = new StringBuffer();
        try {

            strSQL.append(" SELECT a.id, a.ltt_id,a.dts_id, a.nhkb_nhan, a.nhkb_chuyen, a.noidung, ");
            strSQL.append(" a.noidung_traloi, a.thong_tin_khac, a.ttv_nhan, to_char(a.ngay_nhan,'dd/mm/yyyy') ngay_nhan, ");
            strSQL.append(" a.ktt_duyet, to_char(a.ngay_duyet,'dd/mm/yyyy') ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai, ");
            strSQL.append(" a.nguoi_nhap_nh, to_char(a.ngay_nhap_nh,'dd/mm/yyyy') ngay_nhap_nh, a.nguoi_ks_nh, to_char(a.ngay_ks_nh,'dd/mm/yyyy') ngay_ks_nh, a.chuky_ktt, a.loai_dts, a.tt_in ");
            strSQL.append(" FROM sp_dts a ");
            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" FOR UPDATE ");
            dtsVO =
                    (DTSoatVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                       conn);

        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getDTS(): " +
                                   ex.getMessage(), ex);
        }
        return dtsVO;
    }

    /**
     * @see: Lay ra chi tiet mot DTS
     * @param: id
     * @return: DTSoatVO
     * */
    public DTSoatVO findByPK(String id) throws Exception {
        DTSoatVO dtsVO = null;
        Vector params = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            if (id != null && !id.equals("")) {
                params.add(new Parameter(id, true));
                strSQL.append(" SELECT a.id, a.so_tchieu, a.ltt_id,a.dts_id, a.nhkb_nhan,(SELECT ten FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_nhan ) AS ten_don_vi_nhan_tra_soat, a.nhkb_chuyen,(SELECT ten FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_chuyen ) AS ten_don_vi_tra_soat, a.noidung, ");
                strSQL.append(" (SELECT ma_nh FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_nhan) ma_don_vi_nhan_tra_soat, ");
                strSQL.append(" (SELECT ma_nh FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_chuyen) ma_don_vi_tra_soat, ");
                strSQL.append(" (select ma_nsd from sp_nsd where id=a.ttv_nhan) ma_ttv_nhan, (select ten_nsd from sp_nsd where id=a.ttv_nhan) ten_ttv_nhan,");
                strSQL.append(" (select ma_nsd from sp_nsd where id=a.ttv_nhan) ma_ktt, (select ten_nsd from sp_nsd where id=a.ttv_nhan) ten_ktt_duyet,");
                strSQL.append(" a.noidung_traloi, a.thong_tin_khac, a.ttv_nhan, to_char(a.ngay_nhan,'dd/mm/yyyy') ngay_nhan, ");
                strSQL.append(" a.ktt_duyet, to_char(a.ngay_duyet,'dd/mm/yyyy') ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai,to_char(a.thoi_gian_keo_dai,'dd/mm/yyyy hh:mi:ss') thoi_gian_keo_dai, ");
                strSQL.append(" a.nguoi_nhap_nh, to_char(a.ngay_nhap_nh,'dd/mm/yyyy') ngay_nhap_nh, a.nguoi_ks_nh, to_char(a.ngay_ks_nh,'dd/mm/yyyy') ngay_ks_nh, a.chuky_ktt, a.loai_dts, a.tt_in ");
                strSQL.append(" FROM sp_dts a ");
                strSQL.append(" WHERE a.id=?");
                dtsVO =
                        (DTSoatVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getDTS(): " +
                                   ex.getMessage(), ex);
        }
        return dtsVO;
    }

    /**
     * @see: Lay ra chi tiet mot DTS
     * @param: id
     * @return: DTSoatVO
     * */
    public DTSoatVO findByPKWithDate(String id) throws Exception {
        DTSoatVO dtsVO = null;
        Vector params = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            if (id != null && !id.equals("")) {
                params.add(new Parameter(id, true));
                strSQL.append(" SELECT a.id, a.so_tchieu, a.ltt_id,a.dts_id, a.nhkb_nhan,(SELECT ten FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_nhan ) AS ten_don_vi_nhan_tra_soat, a.nhkb_chuyen,(SELECT ten FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_chuyen ) AS ten_don_vi_tra_soat, a.noidung, ");
                strSQL.append(" (SELECT ma_nh FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_nhan) ma_don_vi_nhan_tra_soat, ");
                strSQL.append(" (SELECT ma_nh FROM   sp_dm_ngan_hang WHERE   id =a.nhkb_chuyen) ma_don_vi_tra_soat, ");
                strSQL.append(" (select ma_nsd from sp_nsd where id=a.ttv_nhan) ma_ttv_nhan, (select ten_nsd from sp_nsd where id=a.ttv_nhan) ten_ttv_nhan,");
                strSQL.append(" (select ma_nsd from sp_nsd where id=a.ktt_duyet) ma_ktt, (select ten_nsd from sp_nsd where id=a.ktt_duyet) ten_ktt_duyet,");
                strSQL.append(" a.noidung_traloi, a.thong_tin_khac, a.ttv_nhan, to_char(a.ngay_nhan,'dd/mm/yyyy HH24:MI:SS') ngay_nhan, ");
                strSQL.append(" a.ktt_duyet, to_char(a.ngay_duyet,'dd/mm/yyyy HH24:MI:SS') ngay_duyet, a.lydo_ktt_day_lai, a.trang_thai,to_char(a.thoi_gian_keo_dai,'dd/mm/yyyy HH24:MI:SS') thoi_gian_keo_dai, ");
                strSQL.append(" a.nguoi_nhap_nh, to_char(a.ngay_nhap_nh,'dd/mm/yyyy HH24:MI:SS') ngay_nhap_nh, a.nguoi_ks_nh, to_char(a.ngay_ks_nh,'dd/mm/yyyy HH24:MI:SS') ngay_ks_nh, a.chuky_ktt, a.loai_dts, a.tt_in ");
                strSQL.append(" FROM sp_dts a ");
                strSQL.append(" WHERE a.id=?");
                dtsVO =
                        (DTSoatVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".findByPKWithDate(): " +
                                   ex.getMessage(), ex);
        }
        return dtsVO;
    }

    /**
     * @param: DTSoatVO, type: Loai lenh vd: DTS hoi = 195; DTS tra loi = 196
     * @return: String "-1" = update ko thanh cong; String strID 16 so = insert thanh cong
     * @see: Them moi DTS
     * */
    public String insert(DTSoatVO vo, String type) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        try {
            TTSPUtils ttspUtil = new TTSPUtils(conn);
            String strID = "";
            if (type.length() <= 3)
                strID = ttspUtil.getSoDTS(type);
            else
                strID = type;
            if (strID != null && !"".equals(strID.trim())) {
                strSQL.append("insert into sp_dts (id ");
                strSQL2.append(" ) values ( ");
                strSQL2.append("? ");
                v_param.add(new Parameter(strID, true));

                if (vo.getLtt_id() != null &&
                    !"0".equals(vo.getLtt_id().toString())) {
                    strSQL.append(", ltt_id");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getLtt_id(), true));
                }
                if (vo.getDts_id() != null &&
                    !"0".equals(vo.getDts_id().toString())) {
                    strSQL.append(", dts_id");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getDts_id(), true));
                }
                if (vo.getNhkb_nhan() != null &&
                    !"0".equals(vo.getNhkb_nhan().toString())) {
                    strSQL.append(", nhkb_nhan");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getNhkb_nhan(), true));
                }
                if (vo.getNhkb_chuyen() != null &&
                    !"0".equals(vo.getNhkb_chuyen().toString())) {
                    strSQL.append(", nhkb_chuyen");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getNhkb_chuyen(), true));
                }
                if (vo.getNoidung() != null) {
                    strSQL.append(", noidung");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getNoidung(), true));
                }
                if (vo.getSo_tchieu() != null) {
                    strSQL.append(", so_tchieu");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getSo_tchieu(), true));
                }
                if (vo.getNoidung_traloi() != null) {
                    strSQL.append(", noidung_traloi");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getNoidung_traloi(), true));
                }
                if (vo.getThong_tin_khac() != null) {
                    strSQL.append(", thong_tin_khac");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getThong_tin_khac(), true));
                }
                if (vo.getTtv_nhan() != null &&
                    !"0".equals(vo.getTtv_nhan().toString())) {
                    strSQL.append(", ttv_nhan");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getTtv_nhan(), true));
                }
                strSQL.append(", ngay_nhan");
                strSQL2.append(", SYSDATE");
                //                strSQL2.append(", ?");
                //                v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_nhan().length() ==
                //                                                                      10 ?
                //                                                                      vo.getNgay_nhan() +
                //                                                                      " 00:00:00" :
                //                                                                      vo.getNgay_nhan(),
                //                                                                      DD_MM_YYYY_HH_MI_SS),
                //                                              true));
                if (vo.getTrang_thai() != null) {
                    strSQL.append(", trang_thai");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getTrang_thai(), true));
                }
                if (vo.getNguoi_nhap_nh() != null) {
                    strSQL.append(", nguoi_nhap_nh");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getNguoi_nhap_nh(), true));
                }
                if (vo.getNgay_nhap_nh() != null) {
                    strSQL.append(", ngay_nhap_nh");
                    strSQL2.append(", ?");
                    v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_nhap_nh().length() ==
                                                                          10 ?
                                                                          vo.getNgay_nhap_nh() +
                                                                          " 00:00:00" :
                                                                          vo.getNgay_nhap_nh(),
                                                                          DD_MM_YYYY_HH_MI_SS),
                                                  true));
                }
                if (vo.getNguoi_ks_nh() != null) {
                    strSQL.append(", nguoi_ks_nh");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getNguoi_ks_nh(), true));
                }
                if (vo.getNgay_ks_nh() != null) {
                    strSQL.append(", ngay_ks_nh");
                    strSQL2.append(", ?");
                    v_param.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_ks_nh().length() ==
                                                                          10 ?
                                                                          vo.getNgay_ks_nh() +
                                                                          " 00:00:00" :
                                                                          vo.getNgay_ks_nh(),
                                                                          DD_MM_YYYY_HH_MI_SS),
                                                  true));
                }
                if (vo.getThoi_gian_keo_dai() != null) {
                    strSQL.append(", thoi_gian_keo_dai");
                    strSQL2.append(", ?");
                    v_param.add(new DateParameter(StringUtil.StringToDate(vo.getThoi_gian_keo_dai().length() ==
                                                                          10 ?
                                                                          vo.getThoi_gian_keo_dai() +
                                                                          " 00:00:00" :
                                                                          vo.getThoi_gian_keo_dai(),
                                                                          DD_MM_YYYY_HH_MI_SS),
                                                  true));
                }
                if (vo.getChuky_ktt() != null) {
                    strSQL.append(", chuky_ktt");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getChuky_ktt(), true));
                }

                if (vo.getLoai_dts() != null) {
                    strSQL.append(", loai_dts");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getLoai_dts(), true));
                }
                if (vo.getLoai_tien() != null) {
                    strSQL.append(", loai_tien");
                    strSQL2.append(", ?");
                    v_param.add(new Parameter(vo.getLoai_tien(), true));
                }

                strSQL.append(strSQL2.toString());
                strSQL.append(")");

                if (executeStatement(strSQL.toString(), v_param, conn) == 1)
                    return strID;
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".insert(): " +
                                   ex.getMessage(), ex);
        }
        return "-1";
    }

    /**
     * @param: DTSoatVO
     * @return: 1 = update thanh cong; -1 = update khong thanh cong
     * @see: Sua DTS
     * */
    public int update(DTSoatVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = null;
        strSQL.append("update sp_dts set ");
        try {
            if (vo.getLtt_id() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ltt_id = ?");
                } else {
                    strSQL2.append(", ltt_id = ?");
                }
                v_param.add(new Parameter(vo.getLtt_id(), true));
            }
            if (vo.getDts_id() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("dts_id = ?");
                } else {
                    strSQL2.append(", dts_id = ?");
                }
                v_param.add(new Parameter(vo.getDts_id(), true));
            }
            if (vo.getNhkb_nhan() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("nhkb_nhan = ?");
                } else {
                    strSQL2.append(", nhkb_nhan = ?");
                }
                v_param.add(new Parameter(vo.getNhkb_nhan(), true));
            }
            if (vo.getNhkb_chuyen() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("nhkb_chuyen = ?");
                } else {
                    strSQL2.append(", nhkb_chuyen = ?");
                }
                v_param.add(new Parameter(vo.getNhkb_chuyen(), true));
            }
            if (vo.getNoidung() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("noidung = ?");
                } else {
                    strSQL2.append(", noidung = ?");
                }
                v_param.add(new Parameter(vo.getNoidung(), true));
            }
            if (vo.getNoidung_traloi() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("noidung_traloi = ?");
                } else {
                    strSQL2.append(", noidung_traloi = ?");
                }
                v_param.add(new Parameter(vo.getNoidung_traloi(), true));
            }
            if (vo.getThong_tin_khac() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("thong_tin_khac = ?");
                } else {
                    strSQL2.append(", thong_tin_khac = ?");
                }
                v_param.add(new Parameter(vo.getThong_tin_khac(), true));
            }
            if (vo.getTtv_nhan() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ttv_nhan = ?");
                } else {
                    strSQL2.append(", ttv_nhan = ?");
                }
                v_param.add(new Parameter((vo.getTtv_nhan().toString().equals("0") ?
                                           "" : vo.getTtv_nhan()), true));
            }
            if (vo.getNgay_nhan() != null) {

                String strNgayNhan = vo.getNgay_nhan();
                if (strNgayNhan.length() == 10)
                    strNgayNhan = strNgayNhan + " 00:00:00";

                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    if (strNgayNhan.equalsIgnoreCase("")) {
                        strSQL2.append(" ngay_nhan = null");
                    } else {
                        strSQL2.append(" ngay_nhan = sysdate");
                    }
                } else {
                    if (strNgayNhan.equalsIgnoreCase("")) {
                        strSQL2.append(", ngay_nhan = null");
                    } else {
                        strSQL2.append(", ngay_nhan = sysdate");
                    }
                }
            }
            if (vo.getThoi_gian_keo_dai() != null) {

                String strThoiGianKeoDai = vo.getThoi_gian_keo_dai();
                if (strThoiGianKeoDai.length() == 10)
                    strThoiGianKeoDai = strThoiGianKeoDai + " 00:00:00";

                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    if (strThoiGianKeoDai.equalsIgnoreCase("")) {
                        strSQL2.append(" thoi_gian_keo_dai = null");
                    } else {
                        strSQL2.append(" thoi_gian_keo_dai = ?");
                        v_param.add(new DateParameter(StringUtil.StringToDate(strThoiGianKeoDai,
                                                                              DD_MM_YYYY_HH_MI_SS),
                                                      true));
                    }
                } else {
                    if (strThoiGianKeoDai.equalsIgnoreCase("")) {
                        strSQL2.append(", thoi_gian_keo_dai = null");
                    } else {
                        strSQL2.append(", thoi_gian_keo_dai = ?");
                        v_param.add(new DateParameter(StringUtil.StringToDate(strThoiGianKeoDai,
                                                                              DD_MM_YYYY_HH_MI_SS),
                                                      true));
                    }

                }
            }
            if (vo.getKtt_duyet() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ktt_duyet = ?");
                } else {
                    strSQL2.append(", ktt_duyet = ?");
                }
                v_param.add(new Parameter(vo.getKtt_duyet(), true));
            }
            if (vo.getNgay_duyet() != null) {

                String strNgayDuyet = vo.getNgay_duyet();
                if (strNgayDuyet.length() == 10)
                    strNgayDuyet = strNgayDuyet + " 00:00:00";
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ngay_duyet = ?");
                } else {
                    strSQL2.append(", ngay_duyet = ?");
                }
                v_param.add(new DateParameter(StringUtil.StringToDate(strNgayDuyet.replace("-", "/"),
                                                                      DD_MM_YYYY_HH_MI_SS),
                                              true));
            }
            if (vo.getLydo_ktt_day_lai() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("lydo_ktt_day_lai = ?");
                } else {
                    strSQL2.append(", lydo_ktt_day_lai = ?");
                }
                v_param.add(new Parameter(vo.getLydo_ktt_day_lai(), true));
            }
            if (vo.getTrang_thai() != null) {

                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("trang_thai = ?");
                } else {
                    strSQL2.append(", trang_thai = ?");
                }
                v_param.add(new Parameter(vo.getTrang_thai(), true));
            }
            if (vo.getNguoi_nhap_nh() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("nguoi_nhap_nh = ?");
                } else {
                    strSQL2.append(", nguoi_nhap_nh = ?");
                }
                v_param.add(new Parameter(vo.getNguoi_nhap_nh(), true));
            }
            if (vo.getNgay_nhap_nh() != null) {

                String strNgayNhap = vo.getNgay_nhap_nh();
                if (strNgayNhap.length() == 10)
                    strNgayNhap = strNgayNhap + " 00:00:00";
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ngay_nhap_nh = ?");
                } else {
                    strSQL2.append(", ngay_nhap_nh = ?");
                }
                v_param.add(new DateParameter(StringUtil.StringToDate(strNgayNhap,
                                                                      DD_MM_YYYY_HH_MI_SS),
                                              true));
            }
            if (vo.getNguoi_ks_nh() != null) {

                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("nguoi_ks_nh = ?");
                } else {
                    strSQL2.append(", nguoi_ks_nh = ?");
                }
                v_param.add(new Parameter(vo.getNguoi_ks_nh(), true));
            }
            if (vo.getNgay_ks_nh() != null) {

                String strNgayNhap = vo.getNgay_ks_nh();
                if (strNgayNhap.length() == 10)
                    strNgayNhap = strNgayNhap + " 00:00:00";
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append("ngay_ks_nh = ?");
                } else {
                    strSQL2.append(", ngay_ks_nh = ?");
                }
                v_param.add(new Parameter(StringUtil.StringToDate(strNgayNhap,
                                                                  DD_MM_YYYY_HH_MI_SS),
                                          true));
            }
            if (vo.getChuky_ktt() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" chuky_ktt = ?");
                } else {
                    strSQL2.append(", chuky_ktt = ?");
                }
                v_param.add(new Parameter(vo.getChuky_ktt(), true));
            }
            if (vo.getTt_in() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" tt_in = ?");
                } else {
                    strSQL2.append(", tt_in = ?");
                }
                v_param.add(new Parameter(vo.getTt_in(), true));
            }
            if (vo.getMsg_id() != null) {
                if (strSQL2 == null) {
                    strSQL2 = new StringBuffer();
                    strSQL2.append(" msg_id = ?");
                } else {
                    strSQL2.append(", msg_id = ?");
                }
                v_param.add(new Parameter(vo.getMsg_id(), true));
            }
            //            if (vo.getId() != null) {
            //                if (strSQL2 == null) {
            //                    strSQL2 = new StringBuffer();
            //                    strSQL2.append("id=? WHERE id=?");
            //                    v_param.add(new Parameter(vo.getId(), true));
            //                } else {
            //                    strSQL2.append(" WHERE id=?");
            //                }
            //                v_param.add(new Parameter(vo.getId(), true));
            //
            //            }
            strSQL2.append(" WHERE id=?");
            v_param.add(new Parameter(vo.getId(), true));

            strSQL.append(strSQL2.toString());
            return executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".update(): " +
                                   ex.getMessage(), ex);
        }
    }

    /**
     *xoa dts
     * @param id
     * @throws Exception
     * 1: xoa thanh cong ; -1: xoa khong thanh cong
     */
    public int deleteDTS(String id) throws Exception {
        Vector params = new Vector();
        StringBuffer sqlbuff = null;
        try {
            sqlbuff = new StringBuffer(" DELETE  sp_dts a  WHERE a.id=?");
            params.add(new Parameter(id, true));
            return executeStatement(sqlbuff.toString(), params, conn);
        } catch (Exception ex) {
            conn.rollback();
//            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".delete(): " +
                                   ex.getMessage(), ex);
        }


    }

    public int getCounterDTS(String whereClause,
                             Vector params) throws DAOException, Exception {
        
        try {
            String strSQL="";
            strSQL="SELECT	count(0) FROM	sp_dts t, sp_dm_ngan_hang b, sp_dm_ngan_hang c WHERE t.nhkb_chuyen = b.id(+) AND t.nhkb_nhan = c.id(+)";
            //            sbWhereClause.append("select count(1) as dts from sp_dts t");
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL += whereClause;
            }
            ResultSet rs =
                executeSelectStatement(strSQL, params, conn);
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new Exception(" Kh&#244;ng th&#7875; th&#7921;c hi&#7879;n ch&#7913;c n&#432;ng n&#224;y !");
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getCounterDTS(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
    }

    public int getCounterDTSForTinh(String whereClause,
                                    Vector params) throws Exception {
        StringBuffer sbWhereClause = new StringBuffer();
        try {
            sbWhereClause.append("select count(1) from sp_dts t,sp_dm_ngan_hang b,sp_dm_ngan_hang c,sp_dm_manh_shkb d,sp_dm_manh_shkb e where ");
            sbWhereClause.append("t.nhkb_chuyen = b.id(+) and t.nhkb_nhan = c.id(+) and b.ma_nh = d.ma_nh(+) and c.ma_nh = e.ma_nh(+) ");
            sbWhereClause.append(whereClause);
            ResultSet rs =
                executeSelectStatement(sbWhereClause.toString(), params, conn);
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                //Throw loi LTT khong ton tai
                throw new Exception(" Kh&#244;ng th&#7875; th&#7921;c hi&#7879;n ch&#7913;c n&#432;ng n&#224;y !");
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getCounterDTSForTinh(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
    }

      public String getSo_TChieu(String strWhere, Vector vParam) throws Exception {
          try {
              String strSQL =
                  "select so_tchieu from sp_dts where 1=1 ";
              strSQL += strWhere;
              ResultSet rs = null;
              Statement stm = null;
              stm = conn.createStatement();
              String lan_dc = null;
              rs = stm.executeQuery(strSQL);
              if (rs.next()) {
                  lan_dc = rs.getString("so_tchieu");
              }
              rs.close();
              stm.close();
              //return executeStatement(strSQL.toString(), vParam, conn);
              return lan_dc;
  
          } catch (Exception e) {
              throw e;
          }
      }
}
