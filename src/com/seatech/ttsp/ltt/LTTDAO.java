package com.seatech.ttsp.ltt;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.utils.StringUtil;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

public class LTTDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private static String CLASS_NAME_VO = "com.seatech.ttsp.ltt.LTTVO";
    private static String CLASS_NAME_DAO = "com.seatech.ttsp.ltt.LTTDAO";
    private static final String DATE_FORMAT_NGAY_GIO = "dd/MM/yyyy HH:mm:ss";
//    private static final String DATE_FORMAT_NGAY = "dd/MM/yyyy";

    public LTTDAO(Connection con) {
        this.conn = con;
    }

    /**
     * @see: Them moi LTT
     * @param: LTT VO
     * @return: Long 0: khong thanh cong; Long khac 0 insert thanh cong tra ve ID cua LTT
     * */
    public Long insert(LTTVO lTTVO) throws Exception {
        Vector v_param = new Vector();
        Parameter param = null;
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Long id = lTTVO.getId();
        try {
            strSQL.append("insert into sp_ltt ( ");
            strSQL2.append(" ) values ( ");

            if (lTTVO.getId() != null) {
                strSQL.append(" id");
                strSQL2.append(" ?");
                param = new Parameter(lTTVO.getId(), true);
                v_param.add(param);
            } else {
                //Neu khong co so lenh thanh toan bao loi Khong lay duoc so lenh thanh toan
                throw new Exception("Kh&#244;ng l&#7845;y &#273;&#432;&#7907;c s&#7889; L&#7879;nh thanh to&#225;n");
            }
            if (lTTVO.getNhkb_nhan() != null) {
                strSQL.append(", nhkb_nhan");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getNhkb_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getNhkb_chuyen() != null) {
                strSQL.append(", nhkb_chuyen");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getNhkb_chuyen(), true);
                v_param.add(param);
            }
            if (lTTVO.getNhan() != null) {
                strSQL.append(", nhan");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getNhan(), true);
                //param = new Parameter("N", true);
                v_param.add(param);
            }
            if (lTTVO.getTtv_nhan() != null) {
                strSQL.append(", ttv_nhan");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getTtv_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getNgay_nhan() != null) {
                strSQL.append(", ngay_nhan");
                strSQL2.append(", SYSDATE");
                //              strSQL2.append(", ?");
                //              param = new DateParameter(StringUtil.StringToDate(lTTVO.getNgay_nhan(), "dd/MM/yyyy"), true);
                //              v_param.add(param);
            }
            if (lTTVO.getSo_ct_goc() != null) {
                strSQL.append(", so_ct_goc");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getSo_ct_goc(), true);
                v_param.add(param);
            }
            if (lTTVO.getNgay_ct() != null) {
                strSQL.append(", ngay_ct");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getNgay_ct(), true);
                v_param.add(param);
            }
            if (lTTVO.getNt_id() != null) {
                strSQL.append(", nt_id");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getNt_id(), true);
                v_param.add(param);
            }
            if (lTTVO.getSo_yctt() != null) {
                strSQL.append(", so_yctt");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getSo_yctt(), true);
                v_param.add(param);
            }

            if (lTTVO.getNgay_tt() != null) {
                strSQL.append(", ngay_tt");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getNgay_tt(), true);
                v_param.add(param);
            }
            if (lTTVO.getNdung_tt() != null) {
                strSQL.append(", ndung_tt");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getNdung_tt(), true);
                v_param.add(param);
            }
            if (lTTVO.getTong_sotien() != null) {
                strSQL.append(", tong_sotien");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getTong_sotien(), true);
                v_param.add(param);
            }
            if (lTTVO.getSo_tk_chuyen() != null) {
                strSQL.append(", so_tk_chuyen");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getSo_tk_chuyen(), true);
                v_param.add(param);
            }
            if (lTTVO.getTen_tk_chuyen() != null) {
                strSQL.append(", ten_tk_chuyen");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getTen_tk_chuyen(), true);
                v_param.add(param);
            }
            if (lTTVO.getTtin_kh_chuyen() != null) {
                strSQL.append(", ttin_kh_chuyen");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getTtin_kh_chuyen(), true);
                v_param.add(param);
            }
            if (lTTVO.getId_nhkb_chuyen() != null) {
                strSQL.append(", id_nhkb_chuyen");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getId_nhkb_chuyen(), true);
                v_param.add(param);
            }
            if (lTTVO.getTen_nhkb_chuyen() != null) {
                strSQL.append(", ten_nhkb_chuyen");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getTen_nhkb_chuyen(), true);
                v_param.add(param);
            }
            if (lTTVO.getSo_tk_nhan() != null) {
                strSQL.append(", so_tk_nhan");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getSo_tk_nhan(), true);
                v_param.add(param);
            }

            if (lTTVO.getTen_tk_nhan() != null) {
                strSQL.append(", ten_tk_nhan");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getTen_tk_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getTtin_kh_nhan() != null) {
                strSQL.append(", ttin_kh_nhan");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getTtin_kh_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getId_nhkb_nhan() != null) {
                strSQL.append(", id_nhkb_nhan");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getId_nhkb_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getTen_nhkb_nhan() != null) {
                strSQL.append(", ten_nhkb_nhan");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getTen_nhkb_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getTtloai_lenh() != null) {
                strSQL.append(", ttloai_lenh");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getTtloai_lenh(), true);
                v_param.add(param);
            }
            if (lTTVO.getTrang_thai() != null) {
                strSQL.append(", trang_thai");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getTrang_thai(), true);
                v_param.add(param);
            }
            if (lTTVO.getNguoi_nhap_nh() != null) {
                strSQL.append(", nguoi_nhap_nh");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getNguoi_nhap_nh(), true);
                v_param.add(param);
            }
            if (lTTVO.getNgay_nhap_nh() != null &&
                !STRING_EMPTY.equals(lTTVO.getNgay_nhap_nh())) {
                strSQL.append(", ngay_nhap_nh");
                strSQL2.append(", ?");
                String strNgayNhapNH = lTTVO.getNgay_nhap_nh();
                if (strNgayNhapNH.length() < 10)
                    strNgayNhapNH = null;
                else if (strNgayNhapNH.length() == 10)
                    strNgayNhapNH = strNgayNhapNH + " 00:00:00";
                param =
                        new DateParameter(StringUtil.StringToDate(strNgayNhapNH,
                                                                  DATE_FORMAT_NGAY_GIO),
                                          true);
                v_param.add(param);
            }
            if (lTTVO.getNguoi_ks_nh() != null) {
                strSQL.append(", nguoi_ks_nh");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getNguoi_ks_nh(), true);
                v_param.add(param);
            }
            if (lTTVO.getNgay_ks_nh() != null &&
                !STRING_EMPTY.equals(lTTVO.getNgay_ks_nh())) {
                strSQL.append(", ngay_ks_nh");
                strSQL2.append(", ?");
                String strNgayKSNH = lTTVO.getNgay_ks_nh();
                if (strNgayKSNH.length() < 10)
                    strNgayKSNH = null;
                else if (strNgayKSNH.length() == 10)
                    strNgayKSNH = strNgayKSNH + " 00:00:00";
                param =
                        new DateParameter(StringUtil.StringToDate(strNgayKSNH, DATE_FORMAT_NGAY_GIO),
                                          true);
                v_param.add(param);
            }
            if (lTTVO.getNgay_hoan_thien() != null) {
                strSQL.append(", ngay_hoan_thien");
                strSQL2.append(", SYSDATE");
            }
            if (lTTVO.getMa_nt_mua_ban() != null) {
                strSQL.append(", ma_nt_mua_ban");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getMa_nt_mua_ban(), true);
                v_param.add(param);
            }
            if (lTTVO.getSo_tien_mua_ban() != null) {
                strSQL.append(", so_tien_mua_ban");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getSo_tien_mua_ban(), true);
                v_param.add(param);
            }
            if (lTTVO.getNh_tgian() != null) {
                strSQL.append(", nh_tgian");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getNh_tgian(), true);
                v_param.add(param);
            }
            if (lTTVO.getTen_nh_tgian() != null) {
                strSQL.append(", ten_nh_tgian");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getTen_nh_tgian(), true);
                v_param.add(param);
            }
            if (lTTVO.getPhi() != null) {
                strSQL.append(", phi");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getPhi(), true);
                v_param.add(param);
            }
            if (lTTVO.getVay_tra_no_nn() != null) {
                strSQL.append(", vay_tra_no_nn");
                strSQL2.append(", ?");
                param = new Parameter(lTTVO.getVay_tra_no_nn(), true);
                v_param.add(param);
            }
            strSQL.append(strSQL2.toString());
            strSQL.append(")");

            if (executeStatement(strSQL.toString(), v_param, conn) != 1)
                return new Long(0);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".insert(lttVO): " +
                                 ex.getMessage());

            throw daoEx;
        }
        return id;
    }

    /**
     * @see: Sua LTT di, danh cho truong hop TTV sua LTT di
     * */
    public int update(LTTVO lTTVO) throws Exception {
        Vector v_param = new Vector();
        Parameter param = null;
        StringBuffer strSQL = new StringBuffer();

        try {
            strSQL.append("update sp_ltt set ");

            if (lTTVO.getNhkb_chuyen() != null) {
                strSQL.append("nhkb_chuyen=?");
                param = new Parameter(lTTVO.getNhkb_chuyen(), true);
                v_param.add(param);
            }
            if (lTTVO.getNhkb_nhan() != null) {
                strSQL.append(", nhkb_nhan=?");
                param = new Parameter(lTTVO.getNhkb_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getTtv_nhan() != null) {
                strSQL.append(", ttv_nhan=?");
                param = new Parameter(lTTVO.getTtv_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getNgay_nhan() != null) {
                if ("SYSDATE".equalsIgnoreCase(lTTVO.getNgay_nhan())) {
                    strSQL.append(", ngay_nhan=SYSDATE");
                } else {
                    String strNgayNhan = lTTVO.getNgay_nhan();
                    if (strNgayNhan.length() < 10)
                        strNgayNhan = null;
                    else if (strNgayNhan.length() == 10)
                        strNgayNhan = strNgayNhan + " 00:00:00";
                    strSQL.append(", ngay_nhan=?");
                    param =
                            new DateParameter(StringUtil.StringToDate(strNgayNhan,
                                                                      DATE_FORMAT_NGAY_GIO),
                                              true);
                    v_param.add(param);
                }
            }
            if (lTTVO.getNgay_gui_nh() != null) {
                strSQL.append(", ngay_gui_nh=SYSDATE");
            }
            if (lTTVO.getSo_ct_goc() != null) {
                strSQL.append(", so_ct_goc=?");
                param = new Parameter(lTTVO.getSo_ct_goc(), true);
                v_param.add(param);
            }
            if (lTTVO.getNgay_ct() != null) {
                strSQL.append(", ngay_ct=?");
                param = new Parameter(lTTVO.getNgay_ct(), true);
                v_param.add(param);
            }
            if (lTTVO.getNt_id() != null) {
                strSQL.append(", nt_id=?");
                param = new Parameter(lTTVO.getNt_id(), true);
                v_param.add(param);
            }
            if (lTTVO.getSo_yctt() != null) {
                strSQL.append(", so_yctt=?");
                param = new Parameter(lTTVO.getSo_yctt(), true);
                v_param.add(param);
            }
            if (lTTVO.getNgay_tt() != null) {
                strSQL.append(", ngay_tt=?");
                param = new Parameter(lTTVO.getNgay_tt(), true);
                v_param.add(param);
            }
            if (lTTVO.getNdung_tt() != null) {
                strSQL.append(", ndung_tt=?");
                param = new Parameter(lTTVO.getNdung_tt(), true);
                v_param.add(param);
            }
            if (lTTVO.getTong_sotien() != null) {
                strSQL.append(", tong_sotien=?");
                param = new Parameter(lTTVO.getTong_sotien(), true);
                v_param.add(param);
            }

            if (lTTVO.getSo_tk_chuyen() != null) {
                strSQL.append(", so_tk_chuyen=?");
                param = new Parameter(lTTVO.getSo_tk_chuyen(), true);
                v_param.add(param);
            }
            if (lTTVO.getTen_tk_chuyen() != null) {
                strSQL.append(", ten_tk_chuyen=?");
                param = new Parameter(lTTVO.getTen_tk_chuyen(), true);
                v_param.add(param);
            }
            if (lTTVO.getTtin_kh_chuyen() != null) {
                strSQL.append(", ttin_kh_chuyen=?");
                param = new Parameter(lTTVO.getTtin_kh_chuyen(), true);
                v_param.add(param);
            }
            if (lTTVO.getId_nhkb_chuyen() != null) {
                strSQL.append(", id_nhkb_chuyen=?");
                param = new Parameter(lTTVO.getId_nhkb_chuyen(), true);
                v_param.add(param);
            }
            if (lTTVO.getTen_nhkb_chuyen() != null) {
                strSQL.append(", ten_nhkb_chuyen=?");
                param = new Parameter(lTTVO.getTen_nhkb_chuyen(), true);
                v_param.add(param);
            }

            if (lTTVO.getSo_tk_nhan() != null) {
                strSQL.append(", so_tk_nhan=?");
                param = new Parameter(lTTVO.getSo_tk_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getTen_tk_nhan() != null) {
                strSQL.append(", ten_tk_nhan=?");
                param = new Parameter(lTTVO.getTen_tk_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getTtin_kh_nhan() != null) {
                strSQL.append(", ttin_kh_nhan=?");
                param = new Parameter(lTTVO.getTtin_kh_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getId_nhkb_nhan() != null) {
                strSQL.append(", id_nhkb_nhan=?");
                param = new Parameter(lTTVO.getId_nhkb_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getTen_nhkb_nhan() != null) {
                strSQL.append(", ten_nhkb_nhan=?");
                param = new Parameter(lTTVO.getTen_nhkb_nhan(), true);
                v_param.add(param);
            }
            if (lTTVO.getTrang_thai() != null) {
                strSQL.append(", trang_thai=?");
                param = new Parameter(lTTVO.getTrang_thai(), true);
                v_param.add(param);
            }
            if (lTTVO.getLoai_hach_toan() != null) {
                strSQL.append(", loai_hach_toan =?");
                param = new Parameter(lTTVO.getLoai_hach_toan(), true);
                v_param.add(param);
            }

            if (lTTVO.getChuky_ktt() != null) {
                strSQL.append(", chuky_ktt=?");
                param = new Parameter(lTTVO.getChuky_ktt(), true);
                v_param.add(param);
            }
            if (lTTVO.getChuky_gd() != null) {
                strSQL.append(", chuky_gd=?");
                param = new Parameter(lTTVO.getChuky_gd(), true);
                v_param.add(param);
            }
            if (lTTVO.getTt_in() != null) {
                strSQL.append(", tt_in=?");
                param = new Parameter(lTTVO.getTt_in(), true);
                v_param.add(param);
            }
            if (lTTVO.getMsg_id() != null) {
                strSQL.append(", msg_id=?");
                param = new Parameter(lTTVO.getMsg_id(), true);
                v_param.add(param);
            }
            if (lTTVO.getNgay_hoan_thien() != null) {
                if ("SYSDATE".equalsIgnoreCase(lTTVO.getNgay_hoan_thien())) {
                    strSQL.append(", ngay_hoan_thien=SYSDATE");
                } else {
                    String strNgayHoanThien = lTTVO.getNgay_hoan_thien();
                    if (strNgayHoanThien.length() < 10)
                        strNgayHoanThien = null;
                    else if (strNgayHoanThien.length() == 10)
                        strNgayHoanThien = strNgayHoanThien + " 00:00:00";
                    strSQL.append(", ngay_hoan_thien=?");
                    param =
                            new DateParameter(StringUtil.StringToDate(strNgayHoanThien,
                                                                      DATE_FORMAT_NGAY_GIO),
                                              true);
                    v_param.add(param);
                }
            }
            if (lTTVO.getLoai_quyet_toan() != null) {
                strSQL.append(", loai_quyet_toan=?");
                param = new Parameter(lTTVO.getLoai_quyet_toan(), true);
                v_param.add(param);
            }
            if (lTTVO.getLydo_ktt_day_lai() != null) {
                strSQL.append(", lydo_ktt_day_lai=?");
                param = new Parameter(lTTVO.getLydo_ktt_day_lai(), true);
                v_param.add(param);
            }
            if (lTTVO.getLydo_gd_day_lai() != null) {
                strSQL.append(", lydo_gd_day_lai=?");
                param = new Parameter(lTTVO.getLydo_gd_day_lai(), true);
                v_param.add(param);
            }
            if (lTTVO.getTrang_thai_tw() != null) {
                strSQL.append(", trang_thai_tw=?");
                param = new Parameter(lTTVO.getTrang_thai_tw(), true);
                v_param.add(param);
            }
            if (lTTVO.getLy_do_htoan() != null) {
                strSQL.append(", ly_do_htoan=?");
                param = new Parameter(lTTVO.getLy_do_htoan(), true);
                v_param.add(param);
            }
            if (lTTVO.getSo_tien_mua_ban() != null) {
                strSQL.append(", so_tien_mua_ban=?");
                param = new Parameter(lTTVO.getSo_tien_mua_ban(), true);
                v_param.add(param);
            }
            if (lTTVO.getMa_nt_mua_ban() != null) {
                strSQL.append(", ma_nt_mua_ban=?");
                param = new Parameter(lTTVO.getMa_nt_mua_ban(), true);
                v_param.add(param);
            }
            if (lTTVO.getPhi() != null) {
                strSQL.append(", phi=?");
                param = new Parameter(lTTVO.getPhi(), true);
                v_param.add(param);
            }
            if (lTTVO.getNh_tgian() != null) {
                strSQL.append(", nh_tgian=?");
                param = new Parameter(lTTVO.getNh_tgian(), true);
                v_param.add(param);
            }
            if (lTTVO.getTen_nh_tgian() != null) {
                strSQL.append(", ten_nh_tgian=?");
                param = new Parameter(lTTVO.getTen_nh_tgian(), true);
                v_param.add(param);
            }
            if (lTTVO.getVay_tra_no_nn() != null) {
                strSQL.append(", vay_tra_no_nn=?");
                param = new Parameter(lTTVO.getVay_tra_no_nn(), true);
                v_param.add(param);
            }
            strSQL.append(" WHERE id = ?");
            param = new Parameter(String.valueOf(lTTVO.getId()), true);
            v_param.add(param);

            return executeStatement(strSQL.toString().replaceFirst("set , ",
                                                                   "set "),
                                    v_param, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".update(lttVO): " +
                                 ex.getMessage());

            throw daoEx;
        }
    }

    /**
     * @see: Thay doi trang thai, ngay hoan thien, ly do, nguoi duyet LTT
     * */
    public int updateStatus(Long nSoLTT, String strTrangThai, Long nUserID,
                            String strUserType, String strLyDoDayLai,
                            String strChuKy, String strMsgID, Date ngayDuyet,
                            Long lNgayTT, String strTrangThaiTW) throws Exception {      
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        String strSQLUpdateChuKy = null;
        Vector v_param_chuky = new Vector();

        try {
            strSQL.append("update SP_LTT set TRANG_THAI = ?");
            //Trang thai
            if(strTrangThai == null || STRING_EMPTY.equals(strTrangThai)){
              throw new Exception("Loi cap nhat trang thai lenh thanh toan, trang_thai khong duoc de trong.");
            }
            v_param.add(new Parameter(strTrangThai, true));

            if (strUserType.contains(AppConstants.NSD_KTT)) {
                if (strLyDoDayLai != null &&
                    !STRING_EMPTY.equals(strLyDoDayLai)) {
                    //Ly do day lai trong truong hop day lai
                    strLyDoDayLai = strLyDoDayLai.trim();
                    strSQL.append(", LYDO_KTT_DAY_LAI = ?");
                    v_param.add(new Parameter(strLyDoDayLai, true));
                }
                if (nUserID != null) {
                    //KTT duyet
                    strSQL.append(", KTT_DUYET = ?");
                    v_param.add(new Parameter(nUserID, true));
                }
//manhnv-20140926
//Sua: tach update chu ky ->ghi log aud$ chinh xac
//                if (strChuKy != null &&
//                    !STRING_EMPTY.equalsIgnoreCase(strChuKy)) {
//                    strSQL.append(", CHUKY_KTT = ?");
//                    v_param.add(new Parameter(strChuKy, true));
//                }
                if (strChuKy != null &&
                    !STRING_EMPTY.equalsIgnoreCase(strChuKy)) {
                    strSQLUpdateChuKy = "UPDATE sp_ltt SET CHUKY_KTT = ?";                    
                    v_param_chuky.add(new Parameter(strChuKy, true));
                }
//--------------
                //Ngay KTT duyet
                strSQL.append(", NGAY_KTT_DUYET = ?");
                if (ngayDuyet == null) {
                    ngayDuyet = new Date();
                }
                v_param.add(new DateParameter(ngayDuyet, true));
                if (lNgayTT != null) {
                    strSQL.append(", ngay_tt = ?");
                    v_param.add(new Parameter(lNgayTT, true));
                }

            } else if (strUserType.contains(AppConstants.NSD_GD)) {
                if (strLyDoDayLai != null &&
                    !STRING_EMPTY.equals(strLyDoDayLai)) {
                    //Ly do day lai trong truong hop GD day lai
                    strSQL.append(", LYDO_GD_DAY_LAI = ?");
                    v_param.add(new Parameter(strLyDoDayLai, true));
                }
                if (nUserID != null) {
                    //GD duyet
                    strSQL.append(", GD_DUYET = ?");
                    v_param.add(new Parameter(nUserID, true));
                }
//manhnv-20140926
//Sua: tach update chu ky ->ghi log aud$ chinh xac
//                if (strChuKy != null &&
//                    !STRING_EMPTY.equalsIgnoreCase(strChuKy)) {
//                    strSQL.append(", CHUKY_GD = ?");
//                    v_param.add(new Parameter(strChuKy, true));
//                }
                if (strChuKy != null &&
                    !STRING_EMPTY.equalsIgnoreCase(strChuKy)) {
                    if(strSQLUpdateChuKy == null){
                      strSQLUpdateChuKy = "UPDATE sp_ltt SET CHUKY_GD = ?";
                    }else{
                      strSQLUpdateChuKy += ", CHUKY_GD = ?";
                    }
                    v_param_chuky.add(new Parameter(strChuKy, true));
                }
//--------------
                //Ngay duyet
                strSQL.append(", NGAY_GD_DUYET = ?");
                if (ngayDuyet == null) {
                    ngayDuyet = new Date();
                }
                v_param.add(new DateParameter(ngayDuyet, true));
                if (lNgayTT != null) {
                    strSQL.append(", ngay_tt = ?");
                    v_param.add(new Parameter(lNgayTT, true));
                }
            }
            //Update trang thai cho TW duyet
            if( strTrangThaiTW == null || "".equals(strTrangThaiTW)){
               strTrangThaiTW = "00";
            }
            strSQL.append(", trang_thai_tw = ?");
            v_param.add(new Parameter(strTrangThaiTW, true));
            //-------------------------------
            if (strMsgID != null && !STRING_EMPTY.equalsIgnoreCase(strMsgID)) {
                strSQL.append(", MSG_ID = ?");
                v_param.add(new Parameter(strMsgID, true));
            }
            if (nSoLTT == null) {
                //throw TTSP Exception So lenh thanh toan = null
                throw TTSPException.createException("TTSP-1001",
                                                    "S&#7895; l&#7879;nh thanh to&#225;n = null");
            }            
            strSQL.append(" where ID = ?");
            v_param.add(new Parameter(nSoLTT, true));
//manhnv-20140926
//Sua: tach update chu ky ->ghi log aud$ chinh xac
            if(strSQLUpdateChuKy != null&& !STRING_EMPTY.equalsIgnoreCase(strSQLUpdateChuKy)){
              strSQLUpdateChuKy += " where ID = ?";
              v_param_chuky.add(new Parameter(nSoLTT, true));
              executeStatement(strSQLUpdateChuKy, v_param_chuky, conn);
            }            
//--------------        
            return executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".updateStatus(" + nSoLTT +
                                 "," + strTrangThai + "," + nUserID + "," +
                                 strUserType + "," + strLyDoDayLai + "," +
                                 lNgayTT + "): " + ex.getMessage());

            throw daoEx;
        }
    }
    

    public int updateDuyetLTTTW(Long nSoLTT, String ma_nsd, String strMsgID,
                                Date ngayDuyet, Long lNgayTT,
                                String strTrangThaiTW,String strLDoTW, String strLDoTinh) throws Exception {

        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();


        try {
            strSQL.append("update SP_LTT set TRANG_THAI_TW = ?");
            //Trang thai
            v_param.add(new Parameter(strTrangThaiTW, true));

          if ("03".equals(strTrangThaiTW)) {
            strSQL.append(", nguoi_duyet_TW = ?");
            v_param.add(new Parameter(ma_nsd, true));
            
            strSQL.append(", NGAY_DUYET_TW = ?");            
            if (ngayDuyet == null) {
                ngayDuyet = new Date();
            }
            v_param.add(new DateParameter(ngayDuyet, true));
          }
          if ("02".equals(strTrangThaiTW)) {
            strSQL.append(", nguoi_duyet_tinh = ?");
            v_param.add(new Parameter(ma_nsd, true));
            
            strSQL.append(", NGAY_DUYET_TINH = ?");            
            if (ngayDuyet == null) {
                ngayDuyet = new Date();
            }
            v_param.add(new DateParameter(ngayDuyet, true));
          }
          if (strLDoTinh != null && !"".equals(strLDoTinh)) {
              strSQL.append(", ly_do_tinh = ?");
              v_param.add(new Parameter(strLDoTinh, true));
          }
          if (strLDoTW != null && !"".equals(strLDoTW)) {
            strSQL.append(", ly_do_TW = ?");
            v_param.add(new Parameter(strLDoTW, true));
          }
                      
            if (lNgayTT != null && lNgayTT > 0) {
                strSQL.append(", ngay_tt = ?");
                v_param.add(new Parameter(lNgayTT, true));
            }
            if (strMsgID != null && !STRING_EMPTY.equalsIgnoreCase(strMsgID)) {
                strSQL.append(", MSG_ID = ?");
                v_param.add(new Parameter(strMsgID, true));
            }

            strSQL.append(" where ID = ?");
            v_param.add(new Parameter(nSoLTT, true));

            return executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".updateStatus(): " +
                                 ex.getMessage());

            throw daoEx;
        }
    }


    /**
     * Lay ra danh sach LTT
     * */
    public Collection getLTTDiList(String whereClause,
                                   Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT t.id, t.nhkb_nhan, t.nhkb_chuyen, t.nhan, t.ttv_nhan, to_char(t.ngay_nhan, 'DD/MM/YYYY HH24:MI:SS') ngay_nhan, t.ktv_chuyen, t.ktt_duyet, to_char(t.ngay_ktt_duyet, 'DD/MM/YYYY HH24:MI:SS') ngay_ktt_duyet, t.lydo_ktt_day_lai ");
            strSQL.append(" , t.gd_duyet, to_char(t.ngay_gd_duyet, 'DD/MM/YYYY HH24:MI:SS') ngay_gd_duyet, t.lydo_gd_day_lai, t.so_ct_goc, t.ngay_ct, t.nt_id, t.so_yctt, t.ngay_tt, t.ndung_tt, t.tong_sotien, t.so_tk_chuyen, t.ten_tk_chuyen ");
            strSQL.append(" , t.ttin_kh_chuyen, t.id_nhkb_chuyen, t.ten_nhkb_chuyen, t.so_tk_nhan, t.ten_tk_nhan, t.ttin_kh_nhan, t.id_nhkb_nhan, t.ten_nhkb_nhan, t.trang_thai, t.loai_hach_toan ");
            strSQL.append(" , t.nguoi_nhap_nh, to_char(t.ngay_nhap_nh, 'DD/MM/YYYY') ngay_nhap_nh, t.nguoi_ks_nh, to_char(t.ngay_ks_nh, 'DD/MM/YYYY') ngay_ks_nh, t.chuky_ktt, t.chuky_gd, to_char(t.ngay_hoan_thien, 'DD/MM/YYYY HH24:MI:SS') as \"ngay_hoan_thien\", t.loai_quyet_toan  ");
            strSQL.append(" FROM sp_ltt t inner join sp_dm_ma_thamchieu d on t.trang_thai = d.rv_low_value ");
            strSQL.append(" left join sp_dm_ngan_hang b on t.nhkb_chuyen = b.id ");
            strSQL.append(" left join sp_dm_ngan_hang c on  t.nhkb_nhan = c.id ");
            strSQL.append(" left join sp_dm_tiente e on  t.nt_id = e.id ");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" order by t.trang_thai ASC, substr(t.so_yctt, 1, 8),to_number(t.ktv_chuyen), to_number(substr(t.so_yctt,instr(t.so_yctt, '_',-1, 1)+1)), t.ngay_hoan_thien ASC,t.id desc ");

            //System.out.println(" LTT Master : "+strSQL.toString());
            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {

            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTDiList(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }

        return reval;
    }
    
  public Collection chkNgungLTT(String whereClause,
                                Vector params) throws Exception {


    Collection reval = null;
    try {
        String strSQL = "";
        strSQL = "SELECT count(0) chklist FROM sp_ltt t WHERE 1=1 " + whereClause; ;
        reval =
                executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                       conn);
        } catch (Exception ex) {

        DAOException daoEx =
            new DAOException(CLASS_NAME_DAO + ".chkNgungLTT(): " +
                             ex.getMessage(), ex);

        throw daoEx;
        }

        return reval;

  }
    
    

    /**
     * Lay ra danh sach LTT cho KTT, GD
     * */
    public Collection getLTTDiKTTGDList(String whereClause,
                                        Vector params) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT t.id, t.nhkb_nhan, t.nhkb_chuyen, t.nhan, t.ttv_nhan, to_char(t.ngay_nhan, 'DD/MM/YYYY HH24:MI:SS') ngay_nhan, t.ktv_chuyen, t.ktt_duyet, to_char(t.ngay_ktt_duyet, 'DD/MM/YYYY HH24:MI:SS') ngay_ktt_duyet, t.lydo_ktt_day_lai ");
            strSQL.append(" , t.gd_duyet, to_char(t.ngay_gd_duyet, 'DD/MM/YYYY HH24:MI:SS') ngay_gd_duyet, t.lydo_gd_day_lai, t.so_ct_goc, t.ngay_ct, t.nt_id, t.so_yctt, t.ngay_tt, t.ndung_tt, t.tong_sotien, t.so_tk_chuyen, t.ten_tk_chuyen ");
            strSQL.append(" , t.ttin_kh_chuyen, t.id_nhkb_chuyen, t.ten_nhkb_chuyen, t.so_tk_nhan, t.ten_tk_nhan, t.ttin_kh_nhan, t.id_nhkb_nhan, t.ten_nhkb_nhan, t.trang_thai, t.loai_hach_toan ");
            strSQL.append(" , t.nguoi_nhap_nh, to_char(t.ngay_nhap_nh, 'DD/MM/YYYY') ngay_nhap_nh, t.nguoi_ks_nh, to_char(t.ngay_ks_nh, 'DD/MM/YYYY') ngay_ks_nh, t.chuky_ktt, t.chuky_gd, t.loai_quyet_toan ");
            strSQL.append(" FROM sp_ltt t ");
            strSQL.append(" INNER JOIN sp_dm_ma_thamchieu d ON t.trang_thai = d.rv_low_value");
            strSQL.append(" left join sp_dm_ngan_hang b on t.nhkb_chuyen = b.id");
            strSQL.append(" left join sp_dm_ngan_hang c on  t.nhkb_nhan = c.id");
            strSQL.append(" left join sp_dm_tiente e on  t.nt_id = e.id ");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
                if (whereClause.indexOf("ORDER BY") == -1) {
                    strSQL.append(" ORDER BY d.rv_high_value,substr(t.so_yctt, 1, 8),to_number(t.ktv_chuyen), to_number(substr(t.so_yctt,instr(t.so_yctt, '_',-1, 1)+1)), t.ngay_nhan ASC ");
                }
            }

            reval =
                    executeSelectStatement(strSQL.toString(), params, CLASS_NAME_VO,
                                           conn);
        } catch (Exception ex) {

            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTDiList(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }

        return reval;
    }

    /**
     * @see: Lay danh sach LTT ResultSet
     * @param:
     * @return: Danh sach LTT theo so dong truuyen vao
     */
    public ResultSet getLTTDiListByPrint(String whereClause,
                                         Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT t.id,t.so_tham_chieu_gd,(select a.ma_nh from sp_dm_ngan_hang a where a.id = t.nhkb_nhan) ma_nhkb_nhan_cm, t.nhkb_nhan");
            strSQL.append(" , (select a.ma_nh from sp_dm_ngan_hang a where a.id = t.nhkb_chuyen) ma_nhkb_chuyen_cm, t.nhkb_chuyen, t.nhan ");
            strSQL.append(" , t.ttv_nhan, to_char(t.ngay_nhan, 'DD/MM/YYYY') ngay_nhan, t.ktv_chuyen, t.ktt_duyet, to_char(t.ngay_ktt_duyet, 'DD/MM/YYYY') ngay_ktt_duyet, t.lydo_ktt_day_lai ");
            strSQL.append(" , t.gd_duyet, to_char(t.ngay_gd_duyet, 'DD/MM/YYYY') ngay_gd_duyet, t.lydo_gd_day_lai, t.so_ct_goc, t.ngay_ct, t.nt_id, t.so_yctt, t.ngay_tt, t.ndung_tt, t.tong_sotien, t.so_tk_chuyen, t.ten_tk_chuyen ");
            strSQL.append(" , t.ttin_kh_chuyen, t.id_nhkb_chuyen, t.ten_nhkb_chuyen, t.so_tk_nhan, t.ten_tk_nhan, t.ttin_kh_nhan, t.id_nhkb_nhan, t.ten_nhkb_nhan, t.loai_hach_toan ");
            strSQL.append(" , t.nguoi_nhap_nh, to_char(t.ngay_nhap_nh, 'DD/MM/YYYY') ngay_nhap_nh, t.nguoi_ks_nh, to_char(t.ngay_ks_nh, 'DD/MM/YYYY') ngay_ks_nh, c.rv_meaning trang_thai ");
            strSQL.append(" FROM sp_ltt t, sp_dm_ma_thamchieu c, sp_dm_tiente d ");
            strSQL.append(" where t.nt_id = d.id AND t.trang_thai = c.rv_low_value and c.rv_domain = '" + // taidd them dieu kien select theo loai tien
                          AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT + "'");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" " + whereClause);
            }
            strSQL.append(" ORDER BY t.id DESC ");
            return executeSelectStatement(strSQL.toString(), params, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTDiListByPrint(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }


    }

    /**
     * @see: Lay tong so tien tim kiem duoc tu tra cuu ltt
     * @param:
     * @return: tra ve so tien
     */
    //    public String getSumTongTien(String whereClause,
    //                                 Vector params) throws Exception {
    //        StringBuffer sbWhereClause = new StringBuffer();
    //        try {
    //            sbWhereClause.append("select sum(t.tong_sotien) as tong_tien, count(1) tong_mon from sp_ltt t where 1=1 ");
    //            sbWhereClause.append(whereClause);
    //            ResultSet rs =
    //                executeSelectStatement(sbWhereClause.toString(), params, conn);
    //            if (rs.next()) {
    //                return rs.getString(1);
    //            } else {
    //                //Throw loi LTT khong ton tai
    //                throw new Exception(" Kh&#244;ng th&#7875; th&#7921;c hi&#7879;n ch&#7913;c n&#432;ng n&#224;y !");
    //            }
    //        } catch (Exception ex) {
    //            DAOException daoEx =
    //                new DAOException(CLASS_NAME_DAO + ".getCountLTT(): " +
    //                                 ex.getMessage(), ex);
    //
    //            throw daoEx;
    //        }
    //    }
    public LTTVO getSumTongTienTQ(String whereClause,
                                  Vector params) throws Exception {


        LTTVO lTTVO = null;
        try {
            String strSQL = "";
            strSQL +=
                    "select sum(t.tong_sotien) as tong_tien, count(1) tong_mon FROM sp_ltt t" +
                    " LEFT JOIN sp_nsd b ON t.ttv_nhan = b.id" +
                    " LEFT JOIN sp_dm_ngan_hang e ON t.nhkb_nhan = e.id" +
                    " LEFT JOIN sp_dm_ngan_hang f ON t.nhkb_chuyen = f.id" +
                    " LEFT JOIN sp_dm_ma_thamchieu c ON t.trang_thai = c.rv_low_value " +
                    " JOIN sp_dm_tiente a on t.nt_id = a.id " +
                    " WHERE c.rv_domain = '" +
                    AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT + "'";
            strSQL += whereClause;
            lTTVO =
                    (LTTVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getSumTongTien(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
        return lTTVO;

    }

    public LTTVO getSumTongTien(String whereClause,
                                Vector params) throws Exception {


        LTTVO lTTVO = null;
        try {
            String strSQL = "";
            strSQL +=
                    "select sum(t.tong_sotien) as tong_tien, count(1) tong_mon from sp_ltt t, sp_dm_ngan_hang a, sp_dm_ngan_hang b, sp_dm_ma_thamchieu c, sp_dm_tiente d " +
                    "	WHERE t.trang_thai = c.rv_low_value " +
                    " AND a.id = t.nhkb_nhan " + " AND b.id = t.nhkb_chuyen and t.nt_id = d.id " +
                    " AND c.rv_domain = '" +
                    AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT + "' ";
            strSQL += whereClause;
            lTTVO =
                    (LTTVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getSumTongTien(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
        return lTTVO;

    }


    /**
     * @see: Lay danh sach LTT co phan trang
     * @param:
     * @return: Danh sach LTT theo so dong truuyen vao
     */
    public Collection getLTTDiListWithPading(String whereClause, Vector params,
                                             Integer page, Integer count,
                                             Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT t.id,d.ma ma_nt,a.ma_nh as ma_nhkb_nhan_cm,a.ten  ten_nhkb_nhan_cm, t.nhkb_nhan,b.ma_nh ma_nhkb_chuyen_cm,b.ten ten_nhkb_chuyen_cm,");
            strSQL.append(" t.nhkb_chuyen, t.nhan  , t.ttv_nhan, to_char(t.ngay_nhan, 'DD/MM/YYYY') ngay_nhan, t.ktv_chuyen,t.ktt_duyet, to_char(t.ngay_ktt_duyet, 'DD/MM/YYYY') ngay_ktt_duyet, t.lydo_ktt_day_lai, t.so_tham_chieu_gd ,");
            strSQL.append(" t.gd_duyet, to_char(t.ngay_gd_duyet, 'DD/MM/YYYY') ngay_gd_duyet, t.lydo_gd_day_lai, t.so_ct_goc, t.ngay_ct, t.nt_id,t.so_yctt, t.ngay_tt, t.ndung_tt, t.tong_sotien, t.so_tk_chuyen, t.ten_tk_chuyen  , t.ttin_kh_chuyen, t.id_nhkb_chuyen,");
            strSQL.append(" t.ten_nhkb_chuyen, t.so_tk_nhan, t.ten_tk_nhan, t.ttin_kh_nhan, t.id_nhkb_nhan, t.ten_nhkb_nhan, t.loai_hach_toan,t.nguoi_nhap_nh, to_char(t.ngay_nhap_nh, 'DD/MM/YYYY') ngay_nhap_nh, t.nguoi_ks_nh,case when (instr(t.id, '701') = 3) then 'di' else 'den' end loai_lenh,");
            strSQL.append(" to_char(t.ngay_ks_nh, 'DD/MM/YYYY') ngay_ks_nh, t.ttloai_lenh, c.rv_meaning trang_thai FROM sp_ltt t,sp_dm_ngan_hang a,sp_dm_ngan_hang b, sp_dm_ma_thamchieu c, sp_dm_tiente d");
            strSQL.append(" where t.trang_thai = c.rv_low_value and a.id = t.nhkb_nhan and b.id = t.nhkb_chuyen and t.nt_id = d.id ");
            strSQL.append(" and c.rv_domain = '" +
                          AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT + "'");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" " + whereClause);
            }
            strSQL.append(" ORDER BY t.id DESC ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), params, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTDiListWithPading(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }

        return reval;
    }

    public Collection getLTTTQListWithPading(String whereClause, Vector params,
                                             Integer page, Integer count,
                                             Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT t.id,(SELECT c.ten from sp_dm_htkb a,sp_dm_manh_shkb b, sp_dm_htkb c where a.ma = b.shkb and b.ma_nh = ");
            strSQL.append(" CASE WHEN (INSTR (t.id, '701') = 3) THEN f.ma_nh WHEN (INSTR (t.id, '701') <> 3) THEN e.ma_nh END and a.id_cha = c.id) AS ten_kb_tinh, ");
            strSQL.append(" CASE WHEN (INSTR (t.id, '701') = 3) THEN f.ma_nh WHEN (INSTR (t.id, '701') <> 3) THEN e.ma_nh END AS ma_kb_huyen, ");
            strSQL.append(" CASE WHEN (INSTR (t.id, '701') = 3) THEN f.ten WHEN (INSTR (t.id, '701') <> 3) THEN e.ten END AS ten_kb_huyen, ");
            strSQL.append(" CASE WHEN (INSTR (t.id, '701') = 3) THEN e.ma_nh WHEN (INSTR (t.id, '701') <> 3) THEN f.ma_nh END AS ma_nh, ");
            strSQL.append(" CASE WHEN (INSTR (t.id, '701') = 3) THEN e.ten WHEN (INSTR (t.id, '701') <> 3) THEN f.ten END AS ten_nh, t.nhkb_chuyen, t.nhan  , ");
            strSQL.append(" t.ttv_nhan, to_char(t.ngay_nhan, 'DD/MM/YYYY') ngay_nhan, t.ktv_chuyen, t.ktt_duyet, to_char(t.ngay_ktt_duyet, 'DD/MM/YYYY') ngay_ktt_duyet, ");
            strSQL.append("  t.lydo_ktt_day_lai  , t.gd_duyet, to_char(t.ngay_gd_duyet, 'DD/MM/YYYY') ngay_gd_duyet, t.lydo_gd_day_lai, t.so_ct_goc, t.ngay_ct, t.nt_id, ");
            strSQL.append(" t.so_yctt, t.ngay_tt, t.ndung_tt, t.tong_sotien, t.so_tk_chuyen, t.ten_tk_chuyen  , t.ttin_kh_chuyen, t.id_nhkb_chuyen, t.ten_nhkb_chuyen, ");
            strSQL.append(" t.so_tk_nhan, t.ten_tk_nhan, t.ttin_kh_nhan, t.id_nhkb_nhan, t.ten_nhkb_nhan, t.loai_hach_toan  , t.nguoi_nhap_nh, to_char(t.ngay_nhap_nh,'DD/MM/YYYY') ngay_nhap_nh, ");
            strSQL.append(" t.nguoi_ks_nh, case when (instr(t.id, '701') = 3) then '01' else '02' end loai_lenh, to_char(t.ngay_ks_nh, 'DD/MM/YYYY') ngay_ks_nh, ");
            strSQL.append(" t.ttloai_lenh, c.rv_meaning trang_thai, t.vay_tra_no_nn  FROM sp_ltt t ");
            strSQL.append(" left JOIN sp_nsd b ON t.ttv_nhan = b.id ");
            strSQL.append(" left JOIN sp_dm_ngan_hang e ON t.nhkb_nhan = e.id ");
            strSQL.append(" left JOIN sp_dm_ngan_hang f ON t.nhkb_chuyen = f.id ");
            strSQL.append(" left JOIN sp_dm_ma_thamchieu c on  t.trang_thai = c.rv_low_value ");
            strSQL.append(" JOIN sp_dm_tiente a on t.nt_id = a.id ");
            strSQL.append("where c.rv_domain = '" +
                          AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT + "'");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" " + whereClause);
            }
            strSQL.append(" ORDER BY ma_kb_huyen,t.id DESC ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), params, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTDiListWithPading(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }

        return reval;
    }

    /**
     * @see: Lay ra thong tin LTT
     * */
    public LTTVO getLTTDi(String whereClause, Vector params) throws Exception {
        LTTVO lTTVO = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT t.id, t.nhkb_nhan, t.nhkb_chuyen, t.nhan, b.ma_nh as ma_nhkb_nhan, c.ma_nh as ma_nhkb_chuyen, t.ttv_nhan, to_char(t.ngay_nhan, 'DD/MM/YYYY HH24:MI:SS') ngay_nhan, t.ktv_chuyen, t.ktt_duyet, to_char(t.ngay_ktt_duyet, 'DD/MM/YYYY HH24:MI:SS') ngay_ktt_duyet, t.lydo_ktt_day_lai ");
            strSQL.append(" , t.gd_duyet,t.so_tham_chieu_gd, to_char(t.ngay_gd_duyet, 'DD/MM/YYYY HH24:MI:SS') ngay_gd_duyet, to_char(t.ngay_gui_nh, 'DD/MM/YYYY HH24:MI:SS') ngay_gui_nh, t.lydo_gd_day_lai, t.so_ct_goc, t.ngay_ct, t.nt_id,a.ma nt_code, t.so_yctt, t.ngay_tt, t.ndung_tt, t.tong_sotien, t.so_tk_chuyen, t.ten_tk_chuyen ");
            strSQL.append(" , t.ttin_kh_chuyen, t.id_nhkb_chuyen, t.ten_nhkb_chuyen, t.so_tk_nhan, t.ten_tk_nhan, t.ttin_kh_nhan, t.id_nhkb_nhan, t.ten_nhkb_nhan, t.trang_thai, t.loai_hach_toan, t.tt_in, t.error_code, t.error_desc ");
            strSQL.append(" , t.nguoi_nhap_nh, to_char(t.ngay_nhap_nh, 'DD/MM/YYYY HH24:MI:SS') ngay_nhap_nh, t.nguoi_ks_nh, to_char(t.ngay_ks_nh, 'DD/MM/YYYY HH24:MI:SS') ngay_ks_nh, t.chuky_ktt, t.chuky_gd, to_char(t.ngay_hoan_thien, 'DD/MM/YYYY HH24:MI:SS') as \"ngay_hoan_thien\", t.ttloai_lenh, t.loai_quyet_toan, t.ly_do_htoan  ");
            strSQL.append(" ,d.ma_nsd as ma_ktt_chuyen, e.ma_nsd as ma_gd_chuyen, t.ma_nt_mua_ban, t.so_tien_mua_ban, t.phi, t.nh_tgian, t.ten_nh_tgian, t.vay_tra_no_nn ");
            strSQL.append(" FROM sp_ltt t inner join sp_dm_tiente a on t.nt_id = a.id inner join sp_dm_ngan_hang b on b.id = t.nhkb_nhan inner join sp_dm_ngan_hang c on c.id = t.nhkb_chuyen");
            strSQL.append(" left join sp_nsd d on d.id = t.ktt_duyet left join sp_nsd e on e.id = t.gd_duyet");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            lTTVO =
                    (LTTVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTDi(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
        return lTTVO;
    }

    /**
     * @see: Lay ra trang thai cua LTT de kiem tra trang thai co cho phep update hay khong
     * @param: So lenh thanh toan
     * @throws: DaoException
     * @return: String trang thai LTT
     * */
    public String getStatusForUpdate(Long nSoLTT) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT t.trang_thai FROM sp_ltt t where t.id = ? for update");
            vParam.add(new Parameter(nSoLTT, true));
            ResultSet rs =
                executeSelectStatement(strSQL.toString(), vParam, conn);
            if (rs.next()) {
                return rs.getString("trang_thai");
            } else {
                //Throw loi LTT khong ton tai
                throw new Exception("L&#234;nh thanh to&#225;n kh&#244;ng t&#7891;n t&#7841;i");
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getStatusForUpdate(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
    }

    /**
     * @see: Lay ra TTV Nhan cua LTT de kiem tra LTT da Hoan thien hay chua
     * @param: So lenh thanh toan
     * @throws: DaoException
     * @return: Long TTV Nhan
     * */
    public Long getTTVNhanForUpdate(Long nSoLTT) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT t.ttv_nhan FROM sp_ltt t where t.id = ? for update");
            vParam.add(new Parameter(nSoLTT, true));
            ResultSet rs =
                executeSelectStatement(strSQL.toString(), vParam, conn);
            if (rs.next()) {
                return rs.getLong("ttv_nhan");
            } else {
                //Throw loi LTT da duoc hoan thien boi nguoi khac
                throw new Exception("L&#234;nh thanh to&#225;n &#273;&#227; &#273;&#432;&#7907;c ho&#224;n thi&#7879;n b&#7903;i ng&#432;&#7901;i kh&#225;c");
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getTTVNhanForUpdate(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
    }


    /**
     * @param whereClause
     * @param params
     * @return LTTVO
     * @throws Exception
     */
    public LTTVO getLTTVOForUpdate(String whereClause,
                                   Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        LTTVO lTTVO = null;
        try {
            strSQL.append("SELECT t.id, t.so_ct_goc, t.so_yctt, t.nhan, t.tong_sotien, t.ttv_nhan, t.ktv_chuyen, t.ktt_duyet, t.gd_duyet, t.trang_thai, t.nt_id, t.trang_thai_tw FROM sp_ltt t ");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" for update ");
            lTTVO =
                    (LTTVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTVOForUpdate(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
        return lTTVO;
    }
    public LTTVO getLTTVO(String whereClause,
                                   Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        LTTVO lTTVO = null;
        try {
            strSQL.append("SELECT t.id, t.so_ct_goc, t.so_yctt, t.nhan, t.tong_sotien, t.ttv_nhan, t.ktv_chuyen, t.ktt_duyet, t.gd_duyet, t.trang_thai, t.nt_id, t.trang_thai_tw FROM sp_ltt t ");
  
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
//            strSQL.append(" for update ");
            lTTVO =
                    (LTTVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTVOForUpdate(): " +
                                 ex.getMessage(), ex);
  
            throw daoEx;
        }
        return lTTVO;
    }

    /**
     * @see: Xoa LTT
     * @param id
     * @return int 1:Xoa thanh cong, khac 1: khong thanh cong
     * @throws Exception
     */
    public int delete(Long id) throws Exception {
        Parameter param = null;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("delete from sp_ltt where id=?");
            param = new Parameter(String.valueOf(id), true);
            v_param.add(param);

            return executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".delete(id): " +
                                 ex.getMessage());

            throw daoEx;
        }
    }

    public Collection traCuuChungTuGD(String whereClause, Vector params,
                                      Integer page, Integer count,
                                      Integer[] totalCount) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT t.id, t.nhkb_nhan, t.nhkb_chuyen, t.nhan, t.ttv_nhan, to_char(t.ngay_nhan, 'DD/MM/YYYY') ngay_nhan, t.ktv_chuyen, t.ktt_duyet, to_char(t.ngay_ktt_duyet, 'DD/MM/YYYY') ngay_ktt_duyet, t.lydo_ktt_day_lai ");
            strSQL.append(" , t.gd_duyet, to_char(t.ngay_gd_duyet, 'DD/MM/YYYY') ngay_gd_duyet, t.lydo_gd_day_lai, t.so_ct_goc, t.ngay_ct, t.nt_id, t.so_yctt, t.ngay_tt, t.ndung_tt, t.tong_sotien, t.so_tk_chuyen, t.ten_tk_chuyen ");
            strSQL.append(" , t.ttin_kh_chuyen, t.id_nhkb_chuyen, t.ten_nhkb_chuyen, t.so_tk_nhan, t.ten_tk_nhan, t.ttin_kh_nhan, t.id_nhkb_nhan, t.ten_nhkb_nhan, t.trang_thai, t.loai_hach_toan ");
            strSQL.append(" , t.nguoi_nhap_nh, to_char(t.ngay_nhap_nh, 'DD/MM/YYYY') ngay_nhap_nh, t.nguoi_ks_nh, to_char(t.ngay_ks_nh, 'DD/MM/YYYY') ngay_ks_nh, t.chuky_ktt, t.chuky_gd ");
            strSQL.append(" , c.dien_giai, c.id as id_chi_tiet, c.so_tien, c.ltt_id, c.sott_dong ");
            strSQL.append(" , d.ten as ten_ngoai_te, e.ma as ma_quy ");

            strSQL.append(" FROM sp_ltt_ctiet c ");
            strSQL.append(" left join sp_dm_ma_quy e on c.ma_quy_id=e.id ");
            strSQL.append(" inner join sp_ltt t  on t.id=c.ltt_id ");
            strSQL.append(" left join sp_dm_tiente d on t.nt_id=d.id ");

            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL.append(" WHERE " + whereClause);
            }
            strSQL.append(" ORDER BY t.id ASC, c.ltt_id ASC, c.sott_dong ASC ");

            String CLASS_NAME_VO = "com.seatech.ttsp.ltt.LTTTraCuuVO";
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), params, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".traCuuChungTuGD(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }

        return reval;
    }
    /*
     * Lay ra so LTT gan voi tabmis
     * Chuc
     * */

    public int getCountLTT(String whereClause,
                           Vector params) throws Exception {
      try {
          String strSQL = "";
          strSQL += "SELECT  count(0)" + 
          " FROM  sp_ltt t, sp_dm_ngan_hang b, sp_dm_ngan_hang c" + 
          " WHERE t.nhkb_chuyen = b.id(+) AND t.nhkb_nhan =  c.id(+) ";
            if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                strSQL +=  whereClause;
            }
            ResultSet rs =
                executeSelectStatement(strSQL, params, conn);
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                //Throw loi LTT khong ton tai
                throw new Exception(" Kh&#244;ng th&#7875; th&#7921;c hi&#7879;n ch&#7913;c n&#432;ng n&#224;y !");
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getCountLTT(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
    }

    public int getCountLTTForTinh(String whereClause,
                                  Vector params) throws Exception {
        StringBuffer sbWhereClause = new StringBuffer();
        try {
            sbWhereClause.append("select count(1) from sp_ltt t,sp_dm_ngan_hang b,sp_dm_ngan_hang c,sp_dm_manh_shkb d,sp_dm_manh_shkb e where ");
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
                new DAOException(CLASS_NAME_DAO + ".getCountLTT(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
    }

    public LTTVO getLTTForHachToan(String whereClause,
                                   Vector params) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        LTTVO lTTVO = null;
        try {
            strSQL.append("SELECT a.id, a.ttv_nhan, a.ktt_duyet, a.gd_duyet, ");
            strSQL.append("(select b.ma_nh from sp_dm_ngan_hang b where b.id = a.nhkb_nhan) ma_nhkb_nhan, ");
            strSQL.append("(select b.ma_nh from sp_dm_ngan_hang b where b.id = a.nhkb_chuyen) ma_nhkb_chuyen, ");
            strSQL.append("(select b.ma_nsd from sp_nsd b where b.id = a.ttv_nhan) ten_ttv_nhan, ");
            strSQL.append("to_char(a.ngay_nhan,'dd-mm-yyyy hh24:mi:ss') ngay_nhan, ");
            strSQL.append("(select b.ma_nsd from sp_nsd b where b.id = DECODE(a.gd_duyet,NULL,a.ktt_duyet, a.gd_duyet)) ten_nguoi_duyet, ");
            strSQL.append("to_char(DECODE(a.gd_duyet,NULL,a.ngay_ktt_duyet, a.ngay_gd_duyet),'dd-mm-yyyy hh24:mi:ss') ngay_ktt_duyet, ");
            strSQL.append("a.so_ct_goc, a.ngay_ct, ");
            strSQL.append("(select b.ma from sp_dm_tiente b where b.id = a.nt_id) ma_nt, ");
            strSQL.append("a.so_yctt, a.ngay_tt, sp_util_pkg.fnc_get_ndung_ltt(a.ndung_tt,a.id) ndung_tt, a.tong_sotien, a.so_tk_chuyen, ");
            strSQL.append("a.ten_tk_chuyen, a.ttin_kh_chuyen, (select b.ma_nh from sp_dm_ngan_hang b where b.id = a.id_nhkb_chuyen) ma_nhkb_chuyen_cm, ");
            strSQL.append("a.ten_nhkb_chuyen, a.so_tk_nhan, a.ten_tk_nhan, a.ttin_kh_nhan, ");
            strSQL.append("(select b.ma_nh from (select id, ma_nh from sp_dm_ngan_hang union all select id, bic_code || branch_code ma_nh from sp_dm_swift_code) b where b.id = a.id_nhkb_nhan) ma_nhkb_nhan_cm, ");
            strSQL.append("a.ten_nhkb_nhan, a.trang_thai, a.loai_hach_toan, ");
            strSQL.append("a.ngay_hoan_thien, a.so_tham_chieu_gd, a.msg_id, to_char(a.ngay_ks_nh,'dd-mm-yyyy hh24:mi:ss') ngay_ks_nh, ");
            strSQL.append("a.ma_nt_mua_ban, a.so_tien_mua_ban, a.phi, a.nh_tgian, a.ten_nh_tgian, a.vay_tra_no_nn ");
            strSQL.append("FROM sp_ltt a WHERE (1=1) ");

            strSQL.append(whereClause);

            lTTVO =
                    (LTTVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getLTTForHachToan(): " +
                                 ex.getMessage(), ex);
            throw daoEx;
        }
        return lTTVO;
    }

    /**
     * @see: Lay tong so tien , tong so mon
     * @param:
     * @return: tra ve so tien
     */
    public String getTongSoMon(String whereClause,
                               Vector params) throws Exception {
        StringBuffer sbWhereClause = new StringBuffer();
        try {
            sbWhereClause.append("select count(0) as tong_so_mon from sp_ltt a ");
            sbWhereClause.append("where a.ngay_nhan > SYSDATE-15 AND a.trang_thai <> '06' and a.ngay_tt >= to_char(SYSDATE,'YYYYmmDD') ");
            sbWhereClause.append(whereClause);
            ResultSet rs =
                executeSelectStatement(sbWhereClause.toString(), params, conn);
            if (rs.next()) {
                return rs.getString(1);
            } else {
                //Throw loi LTT khong ton tai
                throw new Exception(" Kh&#244;ng th&#7875; th&#7921;c hi&#7879;n ch&#7913;c n&#432;ng n&#224;y !");
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getCountLTT(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
    }

    public String getTongSoTien(String whereClause,
                                Vector params) throws Exception {
        StringBuffer sbWhereClause = new StringBuffer();
        try {
            sbWhereClause.append("select nvl(sum(a.tong_sotien),0) as tongsotien from sp_ltt a ");
            sbWhereClause.append("where a.ngay_nhan > SYSDATE-15 AND a.trang_thai <> '06' and a.ngay_tt >= to_char(SYSDATE,'YYYYmmDD') ");
            sbWhereClause.append(whereClause);
            ResultSet rs =
                executeSelectStatement(sbWhereClause.toString(), params, conn);
            if (rs.next()) {
                return rs.getString(1);
            } else {
                //Throw loi LTT khong ton tai
                throw new Exception(" Kh&#244;ng th&#7875; th&#7921;c hi&#7879;n ch&#7913;c n&#432;ng n&#224;y !");
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getCountLTT(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
    }

    public String getTongSoMonLTTDen(String whereClause,
                                     Vector params) throws Exception {
        StringBuffer sbWhereClause = new StringBuffer();
        try {
            sbWhereClause.append("select nvl(count(0),0) as tong_so_mon from sp_ltt a left join sp_dm_ngan_hang b on a.nhkb_chuyen = b.id left join sp_dm_ngan_hang c on  a.nhkb_nhan = c.id ");
            sbWhereClause.append("where a.ngay_nhan > SYSDATE-15 AND a.trang_thai <> '06' and ");
            sbWhereClause.append("a.ngay_ks_nh >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(?,c.ma_nh, b.ma_nh) ");
            sbWhereClause.append(whereClause);
            ResultSet rs =
                executeSelectStatement(sbWhereClause.toString(), params, conn);
            if (rs.next()) {
                return rs.getString(1);
            } else {
                //Throw loi LTT khong ton tai
                throw new Exception(" Kh&#244;ng th&#7875; th&#7921;c hi&#7879;n ch&#7913;c n&#432;ng n&#224;y !");
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getCountLTT(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
    }

    public String getTongSoTienLTTDen(String whereClause,
                                      Vector params) throws Exception {
        StringBuffer sbWhereClause = new StringBuffer();
        try {
            sbWhereClause.append("select nvl(sum(tong_sotien),0) as tongsotien from sp_ltt a left join sp_dm_ngan_hang b on a.nhkb_chuyen = b.id left join sp_dm_ngan_hang c on  a.nhkb_nhan = c.id ");
            sbWhereClause.append("where a.ngay_nhan > SYSDATE-15 AND a.trang_thai <> '06' and ");
            sbWhereClause.append("a.ngay_ks_nh >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(?,c.ma_nh, b.ma_nh) ");
            sbWhereClause.append(whereClause);
            ResultSet rs =
                executeSelectStatement(sbWhereClause.toString(), params, conn);
            if (rs.next()) {
                return rs.getString(1);
            } else {
                //Throw loi LTT khong ton tai
                throw new Exception(" Kh&#244;ng th&#7875; th&#7921;c hi&#7879;n ch&#7913;c n&#432;ng n&#224;y !");
            }
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getCountLTT(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
    }

    public LTTVO checkGDDuyet(String whereClause,
                              Vector params) throws Exception {


        LTTVO lTTVO = null;
        try {
            String strSQL = "";
            strSQL +=
                    "select ngay_gd_duyet, gd_duyet, trang_thai from sp_ltt where 1=1";
            strSQL += whereClause;
            lTTVO =
                    (LTTVO)findByPK(strSQL.toString(), params, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            DAOException daoEx =
                new DAOException(CLASS_NAME_DAO + ".getSumTongTien(): " +
                                 ex.getMessage(), ex);

            throw daoEx;
        }
        return lTTVO;

    }
    /*
     * End
     * */    
    
}

