package com.seatech.ttsp.main.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.main.form.MainForm;

import com.seatech.ttsp.tintuc.TinTucDAO;

import com.seatech.ttsp.tintuc.TinTucVO;

import com.seatech.ttsp.tknhkb.TKNHKBacDAO;

import com.seatech.ttsp.tknhkb.TKNHKBacVO;

import java.sql.Connection;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.jms.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @modify: ThuongDT
 * @modify-date: 27/06/2017
 * @see: tra cuu DTS bo sung them ma ngan hang MB
 * @find: 20170627
 * */
 /**
  * @modify: ThuongDT
  * @modify-date: 27/06/2017
  * @see: doi vi tri cap NH-KB khi thong ke tong hop
  * @find: 20170712
  * */

public class MainAction extends AppAction {
    private final String TYPE_LTT_DEN = "LTT_DEN";
    private final String TYPE_LTT_GUI_TABMIS = "LTT_GUI_TABMIS";
    private final String TYPE_LTT_TABMIS_THANHCONG = "LTT_TABMIS_TC";
    private final String TYPE_LTT_TABMIS_THATBAI = "LTT_TABMIS_TB";
    private final String TYPE_LTT_DADUYET = "LTT_DADUYET";

    private final String TYPE_LTT_DI_NH_THANHCONG = "LTT_NH_TC";
    private final String TYPE_LTT_DI_NH_THATBAI = "LTT_NH_TB";
    private final String TYPE_LTT_HUY = "LTT_HUY";
    private final String TYPE_LTT_CHOTTV_XULY = "LTT_TTV_XLY";
    private final String TYPE_LTT_CHOKTT_XULY = "LTT_KTT_XLY";
    private final String TYPE_LTT_CHOGD_XULY = "LTT_GD_XLY";
    private final String TYPE_LTT_DI = "LTT_DI";

    private final String TYPE_DTS_DI = "DTS_DI";
    private final String TYPE_DTS_DI_CHOTTV = "DTS_DI_CHOTTV";
    private final String TYPE_DTS_DI_CHOKTT = "DTS_DI_CHOKTT";
    private final String TYPE_DTS_DI_DAGUI_NH = "DTS_DI_GUI_NH";
    private final String TYPE_DTS_DI_NH_TC = "DTS_DI_TH_TC";
    private final String TYPE_DTS_DI_NH_TB = "DTS_DI_NH_TB";
    private final String TYPE_DTS_DI_HUY = "DTS_DI_HUY";
    private final String TYPE_DTS_DEN = "DTS_DEN";
    private final String TYPE_DTS_DEN_DAXLY = "DTS_DEN_DAXLY";

    private final String TYPE_CHUANHAN_DC = "CHUA_DC";
    private final String TYPE_DC_CHENHLECH = "DC_CHENH";
    private final String TYPE_DC_KHOP = "DC_KHO";
    private final String TYPE_QT_NO = "QT_NO";
    private final String TYPE_QT_CO = "QT_CO";
    private static String CAP_NN = "1";
    private static String CAP_TINH = "5";
    private static String STRING_EMPTY = "";

    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        int countLTT_Den = 0;

        int countLTT_Di = 0;
        int countLTT_Di_Huy = 0;
        int countLTT_Di_DiNHTC = 0;
        int countLTT_Di_DiNHKTC = 0;
        //DTS
        int countDTS_Di = 0;

        int countDTS_Di_NH_TC = 0;
        int countDTS_Di_NH_TB = 0;
        int countDTS_Di_Huy = 0;
        int countDTS_Den = 0;

        try {
            conn = getConnection();
            MainForm mform = (MainForm)form;
            MainAction mAction = new MainAction();
            HttpSession session = request.getSession();
            String nhkb_id =
                String.valueOf(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION));
            String htkb_code =
                String.valueOf(session.getAttribute(AppConstants.APP_KB_CODE_SESSION));
            int iUserType = 0;
            // get cutoftime
            /**
             * Kiem tra phan loai kho bac
             * Type 1 : KBNN - KB CAP TINH - khong thuc hien gi
             * Type 2 : TTTT - hien thi bang ke
             * Type 3 : KB don vi - hien thi thong ke ltt va dts
             * */

            SimpleDateFormat sdfDate =
                new SimpleDateFormat("HH:mm"); //dd/MM/yyyy
            Date now = new Date();
            String strDate = sdfDate.format(now);
            request.setAttribute("strDate", strDate);


            if (htkb_code != null && !STRING_EMPTY.equals(htkb_code)) {
                DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
                //                TinTucDAO newsDAO = new TinTucDAO(conn);
                //                TinTucVO newsVO = null;//new TinTucVO();
                //                String strNews =
                //                    " AND TO_CHAR (ngay_dang,'YYYYMMDD') <=TO_CHAR (SYSDATE, 'YYYYMMDD')" +
                //                    "  AND  TO_CHAR (ngay_het_han,'YYYYMMDD') >=TO_CHAR (SYSDATE, 'YYYYMMDD') and trang_thai='0'";
                //                newsVO = newsDAO.getTinTuc(strNews, null);
                //
                //                String shkb = "";
                //                String tin_tuc="";
                //                Collection colTinTuc = newsDAO.getColTinTuc(strNews, null);
                //                    if(colTinTuc!=null){
                //                       Iterator iter = colTinTuc.iterator();
                //                       while (iter.hasNext()){
                //                         newsVO= (TinTucVO)iter.next();
                //                         String dv_dang = newsVO.getDv_dang().replace(",", "").trim();
                //                         if ("TQ".equals(dv_dang)) {
                //                           tin_tuc += newsVO.getNoi_dung() + "      ";
                //                         } else {
                //                             for (int i = 0; i < dv_dang.length(); i++) {
                //                                 if (i % 4 == 0) {
                //                                     shkb = dv_dang.substring(i, i + 4);
                //                                   if (htkb_code.equals(shkb)) {
                //                                       tin_tuc+= newsVO.getNoi_dung() +"      ";
                //                                   }
                //                                 }
                //                             }
                //
                //                         }
                //                       }
                //                    }
                //               request.setAttribute("tin_tuc", tin_tuc);

                DMKBacVO dmkbVO = null;
                String whereClause = " AND a.ma=?";
                Vector params = new Vector();
                params.add(new Parameter(htkb_code, true));
                dmkbVO = dmkbDAO.getDMKB(whereClause, params);

                if ("0001".equals(htkb_code) || "0002".equals(htkb_code)) {
                    iUserType = 2;
                }
                if (iUserType == 0) {
                    //1 KBNN - KB CAP Tinh
                    if (dmkbVO.getCap().equalsIgnoreCase(CAP_NN) ||
                        dmkbVO.getCap().equalsIgnoreCase(CAP_TINH)) {
                        iUserType = 1;
                        nhkb_id = dmkbVO.getId().toString();
                    }
                    //3 kb don vi
                    else {
                        iUserType = 3;
                    }
                }
            }
            /*
             * End
             * */
            if (iUserType == 2) {

                countLTT_Den =
                        mAction.countLTT(conn, null, htkb_code, TYPE_LTT_DEN,
                                         null, iUserType);
                // count di
                countLTT_Di_DiNHTC =
                        mAction.countLTT(conn, null, htkb_code, TYPE_LTT_DI,
                                         TYPE_LTT_DI_NH_THANHCONG, iUserType);
                countLTT_Di_DiNHKTC =
                        mAction.countLTT(conn, null, htkb_code, TYPE_LTT_DI,
                                         TYPE_LTT_DI_NH_THATBAI, iUserType);
                countLTT_Di =
                        mAction.countLTT(conn, null, htkb_code, TYPE_LTT_DI,
                                         null, iUserType);
                countLTT_Di_Huy =
                        countLTT_Di - countLTT_Di_DiNHKTC - countLTT_Di_DiNHTC;
                /*
                * DTS
                * */
                countDTS_Di_NH_TC =
                        mAction.countDTS(conn, null, htkb_code, TYPE_DTS_DI,
                                         TYPE_DTS_DI_NH_TC, iUserType);
                countDTS_Di_NH_TB =
                        mAction.countDTS(conn, null, htkb_code, TYPE_DTS_DI,
                                         TYPE_DTS_DI_NH_TB, iUserType);
                countDTS_Di =
                        mAction.countDTS(conn, null, htkb_code, TYPE_DTS_DI,
                                         null, iUserType);
                countDTS_Di_Huy =
                        countDTS_Di - countDTS_Di_NH_TB - countDTS_Di_NH_TC;

                countDTS_Den =
                        mAction.countDTS(conn, null, htkb_code, TYPE_DTS_DEN,
                                         null, iUserType);


                mform.setLttDen(Integer.toString(countLTT_Den));
                // di
                mform.setLttDi(Integer.toString(countLTT_Di));
                mform.setLttDi_dinh_tb(Integer.toString(countLTT_Di_DiNHKTC));
                mform.setLttDi_dinh_tc(Integer.toString(countLTT_Di_DiNHTC));
                mform.setLttDi_huy(Integer.toString(countLTT_Di_Huy));

                // set gia tri cho form dts
                mform.setDtsDi(Integer.toString(countDTS_Di));
                mform.setDtsDi_guinh_tc(Integer.toString(countDTS_Di_NH_TC));
                mform.setDtsDi_guinh_tb(Integer.toString(countDTS_Di_NH_TB));
                mform.setDtsDi_huy(Integer.toString(countDTS_Di_Huy));
                mform.setDtsDen(Integer.toString(countDTS_Den));


            } else if (iUserType == 3 || iUserType == 1) {
                /*
               * LTT
               * */
                if (nhkb_id != null && nhkb_id != "null") {
                    countLTT_Den =
                            mAction.countLTT(conn, nhkb_id, htkb_code, TYPE_LTT_DEN,
                                             null, iUserType);
                    // count di
                    countLTT_Di_DiNHTC =
                            mAction.countLTT(conn, nhkb_id, htkb_code,
                                             TYPE_LTT_DI,
                                             TYPE_LTT_DI_NH_THANHCONG,
                                             iUserType);
                    countLTT_Di_DiNHKTC =
                            mAction.countLTT(conn, nhkb_id, htkb_code,
                                             TYPE_LTT_DI,
                                             TYPE_LTT_DI_NH_THATBAI,
                                             iUserType);
                    countLTT_Di =
                            mAction.countLTT(conn, nhkb_id, htkb_code, TYPE_LTT_DI,
                                             null, iUserType);
                    countLTT_Di_Huy =
                            countLTT_Di - countLTT_Di_DiNHKTC - countLTT_Di_DiNHTC;
                    /*
                      * DTS
                      * */
                    countDTS_Di_NH_TC =
                            mAction.countDTS(conn, nhkb_id, htkb_code,
                                             TYPE_DTS_DI, TYPE_DTS_DI_NH_TC,
                                             iUserType);
                    countDTS_Di_NH_TB =
                            mAction.countDTS(conn, nhkb_id, htkb_code,
                                             TYPE_DTS_DI, TYPE_DTS_DI_NH_TB,
                                             iUserType);
                    countDTS_Di =
                            mAction.countDTS(conn, nhkb_id, htkb_code, TYPE_DTS_DI,
                                             null, iUserType);
                    countDTS_Di_Huy =
                            countDTS_Di - countDTS_Di_NH_TB - countDTS_Di_NH_TC;
                    countDTS_Den =
                            mAction.countDTS(conn, nhkb_id, htkb_code, TYPE_DTS_DEN,
                                             null, iUserType);
                }

                // set gia tri cho form
                mform.setLttDen(Integer.toString(countLTT_Den));
                ;
                // di
                mform.setLttDi(Integer.toString(countLTT_Di));
                mform.setLttDi_dinh_tb(Integer.toString(countLTT_Di_DiNHKTC));
                mform.setLttDi_dinh_tc(Integer.toString(countLTT_Di_DiNHTC));
                mform.setLttDi_huy(Integer.toString(countLTT_Di_Huy));

                // set gia tri cho form dts
                mform.setDtsDi(Integer.toString(countDTS_Di));
                ;
                mform.setDtsDi_guinh_tc(Integer.toString(countDTS_Di_NH_TC));
                mform.setDtsDi_guinh_tb(Integer.toString(countDTS_Di_NH_TB));
                mform.setDtsDi_huy(Integer.toString(countDTS_Di_Huy));
                mform.setDtsDen(Integer.toString(countDTS_Den));
            }
            request.setAttribute("userType", iUserType);
        } catch (Exception ex) {
            throw new Exception("executeAction form MainAction: " + ex);
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            TinTucDAO newDAO = new TinTucDAO(conn);
            String strNews =
                " AND TO_CHAR (ngay_dang,'YYYYMMDD') <=TO_CHAR (SYSDATE, 'YYYYMMDD')" +
                "  AND  TO_CHAR (ngay_het_han,'YYYYMMDD') >=TO_CHAR (SYSDATE, 'YYYYMMDD') and trang_thai='0'";
            Collection news = newDAO.getColTinTuc(strNews, null);
            request.setAttribute("news", news);

            /**********************************************************************************************************************/
            HttpSession session = request.getSession();
            TTSPUtils ttspUtils = new TTSPUtils(conn);
            String[] strArrDHV = new String[3];
            String strUserType = session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
            String strCapKB = session.getAttribute(AppConstants.APP_KB_CAP_SESSION).toString();
            String strKiemTraDHV = session.getAttribute(AppConstants.APP_KIEM_TRA_DIEU_HANH_VON_SESSION).toString();

            if ("Y".equalsIgnoreCase(strKiemTraDHV)) {
                if (strUserType.contains(AppConstants.NSD_QTHT_TW) ||
                    strUserType.contains(AppConstants.NSD_CB_TTTT) ||
                    strUserType.contains(AppConstants.NSD_QTHT_DV) ||
                    strUserType.contains(AppConstants.NSD_CBPT_PTTT) ||
                    "5".equals(strCapKB) || "2".equals(strCapKB)) {
                    Long lHanMucSoDuICB = new Long(session.getAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_ICB_SESSION).toString());
                    Long lHanMucSoDuBIDV = new Long(session.getAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_BIDV_SESSION).toString());
                    Long lHanMucSoDuAGR = new Long(session.getAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_AGR_SESSION).toString());

                    Long lSoDuOnlineICB = ttspUtils.getSoDuOnlineTKTheoHThongNH("201", "");
                    Long lSoDuOnlineBIDV = ttspUtils.getSoDuOnlineTKTheoHThongNH("202", "");
                    Long lSoDuOnlineAGR = ttspUtils.getSoDuOnlineTKTheoHThongNH("204", "");


                    if (lHanMucSoDuICB.longValue() > lSoDuOnlineICB.longValue()) {
                        strArrDHV[0] = "RUN_OUT_OF_MONEY";
                    } else {
                        strArrDHV[0] = "";
                    }
                    if (lHanMucSoDuBIDV.longValue() > lSoDuOnlineBIDV.longValue()) {
                        strArrDHV[1] = "RUN_OUT_OF_MONEY";
                    } else {
                        strArrDHV[1] = "";
                    }
                    if (lHanMucSoDuAGR.longValue() > lSoDuOnlineAGR.longValue()) {
                        strArrDHV[2] = "RUN_OUT_OF_MONEY";
                    } else {
                        strArrDHV[2] = "";
                    }
                } else {
                    TKNHKBacDAO dmtkDAO = new TKNHKBacDAO(conn);
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(session.getAttribute(AppConstants.APP_KB_ID_SESSION), true));

                    Collection colDMTK = dmtkDAO.getTK_NH_KB(" and a.kb_id = ?", vParam);
                    Iterator iter = colDMTK.iterator();
                    while (iter.hasNext()) {
                        TKNHKBacVO _DMTKKTVO = (TKNHKBacVO)iter.next();
                        String strMaNH = _DMTKKTVO.getMa_nh();
                        strMaNH = strMaNH.substring(2, 5);
                        if ("201".equals(strMaNH)) {
                            Long lSoDuOnline = ttspUtils.getSoDuOnlineTKTheoHThongNH(strMaNH,                       "");
                            Long lHanMucSoDu = new Long(session.getAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_ICB_SESSION).toString());
                            if (lHanMucSoDu.longValue() > lSoDuOnline.longValue()) {
                                strArrDHV[0] = "RUN_OUT_OF_MONEY";
                            } else {
                                strArrDHV[0] = "";
                            }

                        } else if ("202".equals(strMaNH)) {
                            Long lSoDuOnline = ttspUtils.getSoDuOnlineTKTheoHThongNH(strMaNH, "");
                            Long lHanMucSoDu = new Long(session.getAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_BIDV_SESSION).toString());

                            if (lHanMucSoDu.longValue() > lSoDuOnline.longValue()) {
                                strArrDHV[1] = "RUN_OUT_OF_MONEY";
                            } else {
                                strArrDHV[1] = "";
                            }
                        } else if ("204".equals(strMaNH)) {
                            Long lSoDuOnline = ttspUtils.getSoDuOnlineTKTheoHThongNH(strMaNH,"");
                            Long lHanMucSoDu = new Long(session.getAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_AGR_SESSION).toString());

                            if (lHanMucSoDu.longValue() > lSoDuOnline.longValue()) {
                                strArrDHV[2] = "RUN_OUT_OF_MONEY";
                            } else {
                                strArrDHV[2] = "";
                            }
                        }
                    }
                }
            }
            request.setAttribute("strArrDHV", strArrDHV);
            /**********************************************************************************************************************/
        } catch (Exception e) {
            throw new Exception("executeAction form MainAction: " + e);
        } finally {
            close(conn);
            return mapping.findForward(AppConstants.SUCCESS);
        }
    }

    public MainAction() {
        super();
    }
    /* phan biet ltt di hay ltt den
     * c1 : so ltt yy701 : ltt di
     * yyxxx (xxx != 701) : ltt den
     * c2: nhkb nhan = kb : ltt di
     * */

    //

    public int countDTS(Connection conn, String nhkb_nhan, String htkb_code,
                        String type, String child,
                        int iUserType) throws Exception {
        int _countDTS = 0;
        Vector params = null;
        String strWhereClause = "";
        try {
            params = new Vector();

            if (type != null && !STRING_EMPTY.equals(type)) {
                if (type.equalsIgnoreCase(TYPE_DTS_DEN)) {
                    if (iUserType == 3) {
                        strWhereClause += " AND t.nhkb_nhan = ? ";
                        params.add(new Parameter(nhkb_nhan, true));
                    } else if (iUserType == 1) {
                        strWhereClause +=
                                "and e.shkb in (select ma from sp_dm_htkb where ma_cha=?) ";
                        params.add(new Parameter(htkb_code, true));
                    }
                    //                    strWhereClause += "nhkb_nhan = ? ";
                    //                    params.add(new Parameter(nhkb_nhan, true));
                    // truong hop la thanh phan cua dien tra soat

                    if (STRING_EMPTY.equals(child) || child == null) {
                        //20170627 bo sung them ngan hang MB t.id like '__31%'
                        //20170712 thuongdt sua lay COT fnc_get_ngay_gio_cutoftime doi vi tri cap NH-KB tu b.ma_nh, c.ma_nh sang c.ma_nh, b.ma_nh 
                        strWhereClause +=
                                " AND (t.id like '__20%' or t.id like '__31%') AND ((t.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime (TO_CHAR (SYSDATE - 1, 'dd/mm/yyyy'), c.ma_nh, b.ma_nh))" +
                                " OR (t.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime (TO_CHAR (SYSDATE - 1, 'dd/mm/yyyy'), c.ma_nh,  b.ma_nh)))";
                    }
                    if (!STRING_EMPTY.equals(child) && child != null) {
                        if (child.equalsIgnoreCase(TYPE_DTS_DI_CHOTTV)) {
                            strWhereClause +=
                                    " and t.trang_thai in ('" + AppConstants.TRANG_THAI_CHO_XULY +
                                    "','" + AppConstants.TRANG_THAI_DAY_LAI +
                                    "','" +
                                    AppConstants.SP_DTS_HOI_NH_STATE_10 +
                                    "','" +
                                    AppConstants.SP_DTS_HOI_NH_STATE_11 + "')";
                        } else if (child.equalsIgnoreCase(TYPE_DTS_DI_CHOKTT)) {

                            strWhereClause += " and t.trang_thai in (?,?)";
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_14,
                                                     true));
                            params.add(new Parameter(AppConstants.TRANG_THAI_CHO_DUYET,
                                                     true));

                        } else if (child.equalsIgnoreCase(TYPE_DTS_DEN_DAXLY)) {
                            strWhereClause +=
                                    " and (" + "    ( " + "      (" + "        ((" +
                                    "        t.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                                    "        and " +
                                    "        t.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                                    "        )  " + "        or " +
                                    "        (" +
                                    "          t.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                                    "          and " +
                                    "          t.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)" +
                                    "        ))" +
                                    "        AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                                    "      )" + "      or" + "        (" +
                                    "          ((" +
                                    "            t.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                                    "            and " +
                                    "            t.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                                    "          )  " + "          or " +
                                    "          (" +
                                    "            t.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                                    "            and " +
                                    "            t.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)" +
                                    "          ))" +
                                    "          AND SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                                    "        )" + "    )  " + "    AND " +
                                    "    (t.trang_thai in (?,?,?,?,?,?,?) )" +
                                    "  ) ";
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_12,
                                                     true));
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_13,
                                                     true));
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_15,
                                                     true));
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_16,
                                                     true));
                            params.add(new Parameter(AppConstants.TRANG_THAI_DUYET,
                                                     true));
                            params.add(new Parameter(AppConstants.TRANG_THAI_DA_XULY,
                                                     true));
                            params.add(new Parameter(AppConstants.TRANG_THAI_DA_XACHNHAN,
                                                     true));
                        }
                    }
                }
                if (type.equalsIgnoreCase(TYPE_DTS_DI)) {
                    if (iUserType == 3) {
                        strWhereClause += " AND t.nhkb_chuyen = ? ";
                        params.add(new Parameter(nhkb_nhan, true));
                    } else if (iUserType == 1) {
                        strWhereClause +=
                                "and d.shkb in (select ma from sp_dm_htkb where ma_cha=?) ";
                        params.add(new Parameter(htkb_code, true));
                    }
                    //                    strWhereClause += "nhkb_nhan = ? ";
                    //                    params.add(new Parameter(nhkb_nhan, true));
                    // truong hop la thanh phan cua dien tra soat
                    if (STRING_EMPTY.equals(child) || child == null) {
                        strWhereClause +=
                                " AND t.id like '__70%' AND ((t.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime (TO_CHAR (SYSDATE - 1, 'dd/mm/yyyy'), b.ma_nh, c.ma_nh))" +
                                " OR (t.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime (TO_CHAR (SYSDATE - 1, 'dd/mm/yyyy'),  b.ma_nh, c.ma_nh)))";
                    }
                    if (!STRING_EMPTY.equals(child) && child != null) {
                        if (child.equalsIgnoreCase(TYPE_DTS_DI_CHOTTV)) {
                            strWhereClause +=
                                    " and t.trang_thai in ('" + AppConstants.TRANG_THAI_CHO_XULY +
                                    "','" + AppConstants.TRANG_THAI_DAY_LAI +
                                    "','" +
                                    AppConstants.SP_DTS_HOI_NH_STATE_10 +
                                    "','" +
                                    AppConstants.SP_DTS_HOI_NH_STATE_11 + "')";
                        } else if (child.equalsIgnoreCase(TYPE_DTS_DI_DAGUI_NH)) {
                            strWhereClause +=
                                    " and (" + "    ( " + "      (" + "        ((" +
                                    "        t.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                    "        and " +
                                    "        t.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                                    "        )  " + "        or " +
                                    "        (" +
                                    "          t.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                                    "          and " +
                                    "          t.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , b.ma_nh, c.ma_nh)" +
                                    "        ))" +
                                    "        AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                    "      )" + "      or" + "        (" +
                                    "          ((" +
                                    "            t.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                    "            and " +
                                    "            t.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                                    "          )  " + "          or " +
                                    "          (" +
                                    "            t.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                                    "            and " +
                                    "            t.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy') , b.ma_nh, c.ma_nh)" +
                                    "          ))" +
                                    "          AND SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                    "        )" + "    )  " + "    AND " +
                                    "    (t.trang_thai in (?,?,?) )" + "  ) ";
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_15,
                                                     true));
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_18,
                                                     true));
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_19,
                                                     true));

                        } else if (child.equalsIgnoreCase(TYPE_DTS_DI_CHOKTT)) {
                            strWhereClause += " and t.trang_thai in (?,?) ";
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_14,
                                                     true));
                            params.add(new Parameter(AppConstants.TRANG_THAI_CHO_DUYET,
                                                     true));
                        } else if (child.equalsIgnoreCase(TYPE_DTS_DI_NH_TC)) {
                            strWhereClause +=
                                    " AND ((t.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime (TO_CHAR (SYSDATE - 1, 'dd/mm/yyyy'), b.ma_nh, c.ma_nh))" +
                                    " OR (t.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime (TO_CHAR (SYSDATE - 1, 'dd/mm/yyyy'),  b.ma_nh, c.ma_nh))) " +
                                    " AND t.trang_thai = ? ";
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_18,
                                                     true));
                        } else if (child.equalsIgnoreCase(TYPE_DTS_DI_NH_TB)) {
                            strWhereClause +=
                                    " AND ((t.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime (TO_CHAR (SYSDATE - 1, 'dd/mm/yyyy'), b.ma_nh, c.ma_nh)) " +
                                    " OR (t.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime (TO_CHAR (SYSDATE - 1, 'dd/mm/yyyy'),  b.ma_nh, c.ma_nh)))  " +
                                    " AND t.trang_thai = ? ";
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_19,
                                                     true));
                        } else if (child.equalsIgnoreCase(TYPE_DTS_DI_HUY)) {
                            strWhereClause +=
                                    " and (" + "    ( " + "      (" + "        ((" +
                                    "        t.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                    "        and " +
                                    "        t.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                                    "        )  " + "        or " +
                                    "        (" +
                                    "          t.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                                    "          and " +
                                    "          t.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , b.ma_nh, c.ma_nh)" +
                                    "        ))" +
                                    "        AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                    "      )" + "      or" + "        (" +
                                    "          ((" +
                                    "            t.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                    "            and " +
                                    "            t.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                                    "          )  " + "          or " +
                                    "          (" +
                                    "            t.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                                    "            and " +
                                    "            t.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy') , b.ma_nh, c.ma_nh)" +
                                    "          ))" +
                                    "          AND SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                    "        )" + "    )  " + "    AND " +
                                    "    (t.trang_thai in (?,?) )" + "  ) ";
                            params.add(new Parameter(AppConstants.SP_DTS_HOI_NH_STATE_17,
                                                     true));
                            params.add(new Parameter(AppConstants.TRANG_THAI_HUY,
                                                     true));
                        }
                    }
                }
                //                // dem tat ca dien tra soat
                //                if (null != strWhereClause &&
                //                    STRING_EMPTY.equals(strWhereClause)) {
                //
                //                }
            }
            DTSoatDAO dtsDAO = new DTSoatDAO(conn);
            if (iUserType == 3 || iUserType == 2)
                _countDTS = dtsDAO.getCounterDTS(strWhereClause, params);
            else
                _countDTS =
                        dtsDAO.getCounterDTSForTinh(strWhereClause, params);
        } catch (Exception ex) {
            throw new Exception("countDTS(): " + ex);
        }
        return _countDTS;

    }

    public int countLTT(Connection conn, String nhkb_nhan, String htkb_code,
                        String type, String child,
                        int iUserType) throws Exception {
        int _countLTT = 0;
        Vector params = new Vector();
        String strWhereClause = "";
        try {
            if (type != null && !STRING_EMPTY.equals(type)) {
                if (type.equalsIgnoreCase(TYPE_LTT_DEN)) {
                    String strDate = StringUtil.getPreviousNextDate(-1);
                    // kb huyen
                    if (iUserType == 3) {
                        strWhereClause += " and t.nhkb_nhan = ? ";
                        params.add(new Parameter(nhkb_nhan, true));
                    } else if (iUserType == 1) {
                        strWhereClause +=
                                "and e.shkb in (select ma from sp_dm_htkb where ma_cha=?) ";
                        params.add(new Parameter(htkb_code, true));
                    }
                  //20170627 bo sung them ngan hang MB
                    if (STRING_EMPTY.equals(child) || child == null) {
                        strWhereClause +=
                                " and  t.ngay_ks_nh >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(?,c.ma_nh, b.ma_nh) and (t.id like '__20%' or t.id like '__31%')";
                        params.add(new Parameter(strDate, true));
                    }
                    if (!STRING_EMPTY.equals(child) && child != null) {
                        if (child.equalsIgnoreCase(TYPE_LTT_CHOTTV_XULY)) {
                            strWhereClause +=
                                    " and t.trang_thai in (?,?) and t.ngay_tt >= to_char(sp_util_pkg.fnc_get_ngay_gio_cutoftime(?,c.ma_nh, b.ma_nh),'YYYYMMDD') ";
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_CHO_HOAN_THIEN,
                                                     true));
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_KTT_DUYET_DAY_LAI,
                                                     true));
                            params.add(new Parameter(strDate, true));
                        } else if (child.equalsIgnoreCase(TYPE_LTT_CHOKTT_XULY)) {
                            strWhereClause +=
                                    " and t.trang_thai in (?,?) and t.ngay_ks_nh >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(?,c.ma_nh, b.ma_nh) ";
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET,
                                                     true));
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI,
                                                     true));
                            params.add(new Parameter(strDate, true));
                        } else if (child.equalsIgnoreCase(TYPE_LTT_GUI_TABMIS)) {
                            strWhereClause +=
                                    " and t.trang_thai in (?,?,?) and t.ngay_ks_nh >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(?,c.ma_nh, b.ma_nh) ";

                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_DA_GUI_CHO_CHAY_GIAO_DIEN,
                                                     true));
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_DA_GUI_GIAO_DIEN_THANH_CONG,
                                                     true));
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_DA_GUI_GIAO_DIEN_THAT_BAI,
                                                     true));
                            params.add(new Parameter(strDate, true));
                        } else if (child.equalsIgnoreCase(TYPE_LTT_TABMIS_THANHCONG)) {
                            strWhereClause +=
                                    " and t.trang_thai =? and t.ngay_ks_nh >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(?,c.ma_nh, b.ma_nh) ";

                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_DA_GUI_GIAO_DIEN_THANH_CONG,
                                                     true));
                            params.add(new Parameter(strDate, true));
                        } else if (child.equalsIgnoreCase(TYPE_LTT_TABMIS_THATBAI)) {
                            strWhereClause +=
                                    " and t.trang_thai =? and t.ngay_ks_nh >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(?,c.ma_nh, b.ma_nh) ";
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_DA_GUI_GIAO_DIEN_THAT_BAI,
                                                     true));
                            params.add(new Parameter(strDate, true));
                        }
                    }
                } else if (type.equalsIgnoreCase(TYPE_LTT_DI)) {
                    // lay ngay hien tai
                    String formatter = "yyyyMMdd";
                    String strCurrentDate =
                        StringUtil.DateToString(new Date(), formatter);
                    // kb huyen
                    if (iUserType == 3) {
                        strWhereClause += " and t.nhkb_chuyen = ? ";
                        params.add(new Parameter(nhkb_nhan, true));
                    } else if (iUserType == 1) {
                        strWhereClause +=
                                "and d.shkb in (select ma from sp_dm_htkb where ma_cha=?) ";
                        params.add(new Parameter(htkb_code, true));
                    }
                    if (STRING_EMPTY.equals(child) || child == null) {
                        strWhereClause +=
                                " and  t.ngay_tt >= ? and t.id like '__70%'";
                        params.add(new Parameter(strCurrentDate, true));
                    }
                    if (!STRING_EMPTY.equals(child) && child != null) {
                        if (child.equalsIgnoreCase(TYPE_LTT_HUY)) {
                            strWhereClause +=
                                    " and t.trang_thai=? and t.ngay_tt >= ? ";
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_HUY,
                                                     true));
                            params.add(new Parameter(strCurrentDate, true));
                        } else if (child.equalsIgnoreCase(TYPE_LTT_CHOTTV_XULY)) {
                            strWhereClause +=
                                    " and t.trang_thai in (?,?) and t.ngay_tt >= ? ";
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_CHO_HOAN_THIEN,
                                                     true));
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_KTT_DUYET_DAY_LAI,
                                                     true));
                            params.add(new Parameter(strCurrentDate, true));
                        } else if (child.equalsIgnoreCase(TYPE_LTT_CHOKTT_XULY)) {
                            strWhereClause +=
                                    " and t.trang_thai in (?,?)  and t.ngay_tt >= ? ";
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET,
                                                     true));
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI,
                                                     true));
                            params.add(new Parameter(strCurrentDate, true));
                        } else if (child.equalsIgnoreCase(TYPE_LTT_CHOGD_XULY)) {
                            strWhereClause +=
                                    "  and t.trang_thai  =? and t.ngay_tt >= ? ";
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_CHO_GD_DUYET,
                                                     true));
                            params.add(new Parameter(strCurrentDate, true));
                        } else if (child.equalsIgnoreCase(TYPE_LTT_DADUYET)) {
                            strWhereClause +=
                                    " and t.trang_thai in (?,?,?) and t.ngay_tt >= ? ";
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG,
                                                     true));
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_NGAN_HANG_DA_NHAN,
                                                     true));
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG_XL_THAT_BAI,
                                                     true));
                            params.add(new Parameter(strCurrentDate, true));
                        } else if (child.equalsIgnoreCase(TYPE_LTT_DI_NH_THATBAI)) {
                            strWhereClause +=
                                    " and t.trang_thai  in ('15','16') and t.ngay_tt >= ? ";
                            //                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG_XL_THAT_BAI,
                            //                                                     true));
                            params.add(new Parameter(strCurrentDate, true));
                        } else if (child.equalsIgnoreCase(TYPE_LTT_DI_NH_THANHCONG)) {
                            strWhereClause +=
                                    " and t.trang_thai =? and t.ngay_tt >= ? ";
                            params.add(new Parameter(AppConstants.LTT_TRANG_THAI_NGAN_HANG_DA_NHAN,
                                                     true));
                            params.add(new Parameter(strCurrentDate, true));
                        }
                    }
                }
            }
            LTTDAO lttDAO = new LTTDAO(conn);
            if (iUserType == 3 || iUserType == 2)
                _countLTT = lttDAO.getCountLTT(strWhereClause, params);
            else
                _countLTT = lttDAO.getCountLTTForTinh(strWhereClause, params);
        } catch (Exception ex) {
            throw new Exception("countLTT(): " + ex);
        }
        return _countLTT;
    }


}
