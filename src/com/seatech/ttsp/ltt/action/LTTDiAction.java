package com.seatech.ttsp.ltt.action;


//import com.seatech.ttsp.ltt.form.LTTCTietForm;
//import com.seatech.ttsp.proxy.dm.DMCheo;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.AppKeys;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.DateUtils;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.chucnang.ChucNangVO;
import com.seatech.ttsp.dmdvsdns.DMDonViSdnsDAO;
import com.seatech.ttsp.dmdvsdns.DMDonViSdnsVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanDAO;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.dvgiaodich.DvGiaoDichDAO;
import com.seatech.ttsp.dvgiaodich.DvGiaoDichVO;
import com.seatech.ttsp.ktvtm.KTVTabmisDAO;
import com.seatech.ttsp.ltt.LTTCTietDAO;
import com.seatech.ttsp.ltt.LTTCTietVO;
import com.seatech.ttsp.ltt.LTTCommon;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.ltt.form.LTTForm;
import com.seatech.ttsp.proxy.giaodien.BuildMsgLTT;
//import com.seatech.ttsp.proxy.giaodien.SendLTToan;
//import com.seatech.ttsp.proxy.giaodien.mq.QueueManager;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserVO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.math.BigDecimal;

import java.sql.Connection;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * - ManhNV
 * - 22/11/2016
 * - Sua truyen dien 2 lan
 * - key tim kiem: DOUBLE_20161122
 ***/
 /**
  * - HungBM
  * - 17/04/2017
  * - Them ban in giay gioi thieu
  * - key tim kiem: 20170417
  ***/
public class LTTDiAction extends AppAction {
    private String forward = AppConstants.SUCCESS;
    private static String STRING_EMPTY = "";
    

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        //response danh sach LTT di
        String strAction =
            request.getParameter("action") != null ? request.getParameter("action") :
            "";
        String strKBacID = "";
        if (!strAction.equalsIgnoreCase("VIEWS_LTTDIT4") &&
            !strAction.equalsIgnoreCase("VIEW_LTTDI") &&
            !strAction.equalsIgnoreCase("VIEW_LTTDi_DUYETLO") &&
            !strAction.equalsIgnoreCase("VIEW_LTTDi_DHV")) {
            if (!checkPermissionOnFunction(request, AppConstants.LTT_DI)) {
                forward = AppConstants.FAILURE;
                return mapping.findForward(forward);
            }
        }
        forward = AppConstants.SUCCESS;
        Connection conn = null;
        LTTForm f = (LTTForm)form;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            // tim kiem
            //            String strSoyctt =
            //                request.getParameter("soyctt") != null ? request.getParameter("soyctt") :
            //                "";
            //            String strNhkbchuyennhan =
            //                request.getParameter("nhkbchuyennhan") != null ?
            //                request.getParameter("nhkbchuyennhan") : "";
            //            String strTrangthai =
            //                request.getParameter("trangthai") != null ? request.getParameter("trangthai") :
            //                "";
            //            String strSotien =
            //                request.getParameter("sotien") != null ? request.getParameter("sotien") :
            //                "";
            //            String strTtvnhan =
            //                request.getParameter("ttvnhan") != null ? request.getParameter("ttvnhan") :
            //                "";
            //            f.setSoyctt(strSoyctt);
            //            f.setNhkbchuyennhan(strNhkbchuyennhan);
            //            f.setTrangthai(strTrangthai);
            //            f.setSotien(strSotien);
            //            f.setTtvnhan(strTtvnhan);
            // dem tong so mon tong so tien
            String whereClauseTongSo =
                "AND a.nhkb_chuyen =? AND a.nt_id = 177 ";
            String strNHKB_Tong =
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION) !=
                null ?
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString() :
                "";
            Vector param_Tong = new Vector();

            // lay ngay hien tai
            //            String formatter = "yyyyMMdd";
            //            String strCurrentDate =
            //                StringUtil.DateToString(new Date(), formatter);
            //            param_Tong.add(new Parameter(strCurrentDate, true));
            param_Tong.add(new Parameter(strNHKB_Tong, true));
            // end
            LTTDAO lttDAO = new LTTDAO(conn);
            String strTongSoMon =
                (!strAction.equalsIgnoreCase("VIEWS_LTTDIT4") &&
                 !strAction.equalsIgnoreCase("VIEW_LTTDI")) ?
                lttDAO.getTongSoMon(whereClauseTongSo, param_Tong) : "";
            String strTongSoTien =
                (!strAction.equalsIgnoreCase("VIEWS_LTTDIT4") &&
                 !strAction.equalsIgnoreCase("VIEW_LTTDI")) ?
                lttDAO.getTongSoTien(whereClauseTongSo, param_Tong) : "";
            f.setTong_so_tien(StringUtil.formatCurrency(strTongSoTien, 177));
            f.setTong_so_mon(strTongSoMon);
            // end
            //            if (strSoyctt != "" || strNhkbchuyennhan != "" ||
            //                strTrangthai != "" || strSotien != "" || strTtvnhan != "") {
            //                if (strAction == null || strAction == "")
            //                    strAction = "FIND_TKIEM_ACTION";
            //            }
            //end
            if ((!strAction.equalsIgnoreCase("VIEWS_LTTDIT4") &&
                 !strAction.equalsIgnoreCase("VIEW_LTTDI"))) {

                if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) !=
                    null) {
                    strKBacID =
                            session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
                }
                //Lay ra danh sach cac ngan hang ma kho bac mo tai khoan
                Collection listDMNH = new ArrayList();
                DMNHangDAO dmnhDAO = new DMNHangDAO(conn);

                String strWhere = " and c.id = ?";
                Vector vtParam = new Vector();
                vtParam.add(new Parameter(strKBacID, true));

                listDMNH = dmnhDAO.getDMNHListByKBId(strWhere, vtParam);

                DMNHangVO dmnhVO = null;
                Iterator it = listDMNH.iterator();
                int i = 0;
                while (it.hasNext()) {
                    if (i >= 1) {
                        break;
                    }
                    dmnhVO = (DMNHangVO)it.next();
                    i++;
                }

                //Khi them moi: Gan du lieu cho Nhkb_nhan phan thong tin chung
                if (dmnhVO != null) {
                    request.setAttribute("Ma_nhkb_nhan_cm", dmnhVO.getMa_nh());
                    request.setAttribute("Ten_nhkb_nhan_cm", dmnhVO.getTen());
                    request.setAttribute("Nhkb_nhan", dmnhVO.getId());
                }
                request.setAttribute("colDMNH", listDMNH);
                //Lay ra Danh sach Trang thai cho phan tim kiem
                getTrangThaiLTTList(conn, request);

                // Luu trang thai nguoi nhan
                request.setAttribute("so_tk_nhan_status",
                                     getThamSoHThong(AppConstants.PARAM_LTT_ID_TT_NG_NHAN_SOTK,
                                                     session));
                request.setAttribute("ten_tk_nhan_status",
                                     getThamSoHThong(AppConstants.PARAM_LTT_ID_TT_NG_NHAN_TENTK,
                                                     session));
                request.setAttribute("nhkb_nhan_status",
                                     getThamSoHThong(AppConstants.PARAM_LTT_ID_TT_NG_NHAN_NHKB,
                                                     session));
                request.setAttribute("tttk_nhan_status",
                                     getThamSoHThong(AppConstants.PARAM_LTT_ID_TT_NG_NHAN_TTTK,
                                                     session));
                //Lay ra danh sach TKTN Dac biet, An ninh
                String strSpecialTKTN =
                    getThamSoHThong("MA_DVQHNS_DAC_BIET", session);
                strSpecialTKTN = LTTCommon.getStringAsArray(strSpecialTKTN);
                request.setAttribute("StrSpecialTKTN", strSpecialTKTN);

                String strTKTNAnNinh =
                    getThamSoHThong("MA_DVQHNS_AN_NINH", session);
                strTKTNAnNinh = LTTCommon.getStringAsArray(strTKTNAnNinh);
                request.setAttribute("StrDVQHNSAnNinh", strTKTNAnNinh);
                // kiem tra ket noi
                //checkNgatKetNoi  thi ko dc nhap lenh, ko dc hoan thien
                if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) !=
                    null) {
                    strKBacID =
                            session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
                }
                DvGiaoDichDAO dvGiaoDichDAO = new DvGiaoDichDAO(conn);
                DvGiaoDichVO dvGiaoDichVO =
                    dvGiaoDichDAO.getDVGD(new Long(strKBacID));
                //          ttspUtil.checkNgatKetNoi(strKBacID)
                if (dvGiaoDichVO != null) {
                    //Lay ra danh sach LttDi (master) cho nguoi dung bi Ngat ket noi, danh sach Ngoai te
                    //                lTTMaster(conn, request, session, "", "");
                    String strCheckNgatKN = "NGAT_KET_NOI";
                    request.setAttribute("ngatkn", strCheckNgatKN);
                    request.setAttribute("LyDoNgatKetNoi",
                                         dvGiaoDichVO.getLido_ngat());
                }
            }

            Collection colLttDiMaster = null;
            if ("REFRESH".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session, "", "");
                colLttDiMaster =
                        (Collection)request.getAttribute(AppKeys.LTT_LIST_REQUEST_KEY);
                request.removeAttribute(AppKeys.LTT_LIST_REQUEST_KEY);

                Type listType = new TypeToken<Collection<LTTVO>>() {
                }.getType();
                String strJson = new Gson().toJson(colLttDiMaster, listType);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
            } else if ("FIND_SoYCTT".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session,
                          AppConstants.LTT_DI_CHUC_NANG_TIM_KIEM, "");
                colLttDiMaster =
                        (Collection)request.getAttribute(AppKeys.LTT_LIST_REQUEST_KEY);
                request.removeAttribute(AppKeys.LTT_LIST_REQUEST_KEY);

                Type listType = new TypeToken<Collection<LTTVO>>() {
                }.getType();
                String strJson = new Gson().toJson(colLttDiMaster, listType);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
            } else if ("VIEW_LTTDi".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session,
                          AppConstants.LTT_DI_CHUC_NANG_CHI_TIET, "");
            } else if ("VIEW_LTTDi_DUYETLO".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session,
                          AppConstants.LTT_DI_CHUC_NANG_CHI_TIET_DUYETLO, "");
            } else if ("VIEW_LTTDi_DHV".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session,
                          AppConstants.LTT_DI_CHUC_NANG_CHI_TIET_DHV, "");
            } else if ("VIEWS_LTTDIT4".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session,
                          AppConstants.LTT_DI_CHUC_NANG_CHI_TIET_T4, "");
            } else if ("FIND_TKIEM_ACTION".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session, "FIND_TKIEM_ACTION", "");
            } else {
                lTTMaster(conn, request, session, "", "");
            }
        } catch (Exception e) {
            if ("REFRESH".equalsIgnoreCase(strAction) &&
                "FIND_SoYCTT".equalsIgnoreCase(strAction)) {
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(e.getMessage());
                out.flush();
                out.close();
            } else {
                throw e;
            }
        } finally {
            // tim kiem
            request.setAttribute("soycttTK", f.getSoyctt());
            request.setAttribute("nhkbchuyennhanTK", f.getNhkbchuyennhan());
            request.setAttribute("trangthaiTK", f.getTrangthai());
            request.setAttribute("sotienTK", f.getSotien());
            close(conn);
        }

        if (!response.isCommitted())
            return mapping.findForward(forward);
        else
            return null;
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request,
                                       AppConstants.LTT_DI_CHUC_NANG_THEM)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        forward = AppConstants.SUCCESS;
        Connection conn = null;
        String strMsg = "";
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String strUserID = "";
            if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                null) {
                strUserID =
                        session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            }

            TTSPUtils ttspUtil = new TTSPUtils(conn);
            //Check quyen Nhap LTT =false la ko dc nhap (O day da bo qua Get danh sach chuc nang)
            if (!ttspUtil.checkQuyenNhapLTT(strUserID)) {
                strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
                request.setAttribute("MsgDialogLTTDi", strMsg);
                request.setAttribute("LastAction", AppConstants.ACTION_ADD);
                //                lTTMaster(conn, request, session, "", "");
                //                taoLaiCOA(request);
                return mapping.findForward(forward);
            }
            //checkNgatKetNoi = true, thi ko dc nhap lenh, ko dc hoan thien
            String strKBacID = "";
            if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) != null) {
                strKBacID =
                        session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }
            // kiem tra ket noi
            DvGiaoDichDAO dvGiaoDichDAO = new DvGiaoDichDAO(conn);
            DvGiaoDichVO dvGiaoDichVO =
                dvGiaoDichDAO.getDVGD(new Long(strKBacID));
            //          ttspUtil.checkNgatKetNoi(strKBacID)
            if (dvGiaoDichVO != null) {
                //Lay ra danh sach LttDi (master) cho nguoi dung bi Ngat ket noi, danh sach Ngoai te
                //                lTTMaster(conn, request, session, "", "");

                strMsg = AppConstants.LTT_NGAT_KET_NOI;
                request.setAttribute("MsgDialogLTTDi", strMsg);
                request.setAttribute("LyDoNgatKetNoi",
                                     dvGiaoDichVO.getLido_ngat());
                request.setAttribute("LastAction", AppConstants.ACTION_ADD);
                return mapping.findForward(forward);
            }
            LTTForm f = (LTTForm)form;

            String strTongSoTien = f.getTong_sotien().trim();

            f.setTong_sotien(StringUtil.convertCurrencyToNumber(strTongSoTien,
                                                                f.getNt_id()).toString());

            LTTVO lTTVO = new LTTVO();
            BeanUtils.copyProperties(lTTVO, f);

            String strNgay_ct = f.getNgay_ct();
            lTTVO.setNgay_ct(DateUtils.DateToLong(strNgay_ct));

            if (f.getNgay_tt() != null) {
                String strNgay_tt = f.getNgay_tt();
                while (strNgay_tt.contains("/")) {
                    strNgay_tt = strNgay_tt.replace("/", "");
                }
                strNgay_tt = ttspUtil.getNgayLamViec(strNgay_tt);
                if (strNgay_tt != null && strNgay_tt.length() == 8) {
                    strNgay_tt =
                            strNgay_tt.substring(0, 2) + "/" + strNgay_tt.substring(2,
                                                                                    4) +
                            "/" + strNgay_tt.substring(4, 8);
                    lTTVO.setNgay_tt(DateUtils.DateToLong(strNgay_tt));
                }
            }
            lTTVO.setNhan("N");
            lTTVO.setNgay_nhan(f.getNgay_hoan_thien());

            //lTTVO.setTong_sotien(new BigDecimal(strTongSoTien));

            if (strUserID != null && !STRING_EMPTY.equalsIgnoreCase(strUserID))
                lTTVO.setTtv_nhan(Long.parseLong(strUserID));

            lTTVO.setTrang_thai(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET);

            String strId = ttspUtil.getSoLTT("103");
            lTTVO.setId(Long.valueOf(strId));
            LTTDAO lTTDAO = new LTTDAO(conn);
            //Insert LTT
            Long id = lTTDAO.insert(lTTVO);
            
            Long id_ct = Long.parseLong("0");
            LTTCTietDAO lttCTietDAO = new LTTCTietDAO(conn);
            boolean bFlagErr = false;
            LTTCTietVO lttCTietVO = null;
            LTTCommon common = new LTTCommon();
            HashMap h_map = common.getArraysInCOA(request);
            String[] arrTkkt_ma = (String[])h_map.get("tkkt_ma");

            if (arrTkkt_ma.length > 0) {
                bFlagErr = true;
//                BigDecimal dbTongSoTienChiTiet = new BigDecimal(0);
                for (int i = 0; i < arrTkkt_ma.length; i++) {
                    lttCTietVO =
                            getRowInCOAFromCode(i, id, conn, h_map, f.getNt_id());
                    id_ct = lttCTietDAO.insert(lttCTietVO);
                }
            }
            if (id_ct == 0) {
                bFlagErr = false;
                conn.rollback();
            }

            if (bFlagErr) {
                strMsg = AppConstants.SUCCESS;
            } else {
                strMsg = AppConstants.FAILURE;
            }
            String strSoLTT = "";
            if (id != null) {
                strSoLTT = id.toString();
            }
            request.setAttribute("MsgDialogLTTDi", strMsg);
            request.setAttribute("LastAction", AppConstants.ACTION_ADD);
            request.setAttribute("SoLTTDi", strSoLTT);
            //            lTTMaster(conn, request, session,
            //                      AppConstants.LTT_DI_CHUC_NANG_THEM, strSoLTT);
            //Set Log
            saveVisitLog(conn, session, AppConstants.LTT_DI_CHUC_NANG_THEM,
                         "");
            conn.commit();
        } catch (TTSPException ttspEx) {
            throw ttspEx;
        } catch (Exception ex) {
            throw TTSPException.createException("TTSP-1000", ex.toString());
        } finally {
            close(conn);
        }
        return mapping.findForward(forward);
    }

    public ActionForward lttDiAdd(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request,
                                       AppConstants.LTT_DI_CHUC_NANG_THEM)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        Connection conn = null;
        Vector params = null;
        Parameter param = null;
        Collection reVal = null;
        String strKBacID = "";
        String strUserID = "";
        String strUserType = "";
        String whereClause = "";

        try {
            String strMsg = "";
            conn = getConnection(request);
            forward = AppConstants.SUCCESS;
            HttpSession session = request.getSession();
            if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) != null) {
                strKBacID =
                        session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }
            if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                null) {
                strUserID =
                        session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            }
            TTSPUtils ttspUtil = new TTSPUtils(conn);
            //Check quyen Nhap LTT =false la ko dc nhap (O day da bo qua Get danh sach chuc nang)
            if (!ttspUtil.checkQuyenNhapLTT(strUserID)) {
                strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
                request.setAttribute("MsgDialogLTTDi", strMsg);
                request.setAttribute("LastAction", AppConstants.ACTION_ADD);
                //lTTMaster(conn, request, session, "", "");
                //                taoLaiCOA(request);
                return mapping.findForward(forward);
            }
            // kiem tra ket noi
            //checkNgatKetNoi  thi ko dc nhap lenh, ko dc hoan thien
            if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) != null) {
                strKBacID =
                        session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }
            DvGiaoDichDAO dvGiaoDichDAO = new DvGiaoDichDAO(conn);
            DvGiaoDichVO dvGiaoDichVO =
                dvGiaoDichDAO.getDVGD(new Long(strKBacID));
            //          ttspUtil.checkNgatKetNoi(strKBacID)
            if (dvGiaoDichVO != null) {
                //Lay ra danh sach LttDi (master) cho nguoi dung bi Ngat ket noi, danh sach Ngoai te
                //                lTTMaster(conn, request, session, "", "");
                String strCheckNgatKN = "NGAT_KET_NOI";
                request.setAttribute("ngatkn", strCheckNgatKN);
                request.setAttribute("LyDoNgatKetNoi",
                                     dvGiaoDichVO.getLido_ngat());
            }
            //Lay ra danh sach tai khoan Dac biet, Danh sach tai khoan An ninh
            String strSpecialTKTN =
                getThamSoHThong("MA_TKTN_DAC_BIET", session);
            strSpecialTKTN = LTTCommon.getStringAsArray(strSpecialTKTN);
            request.setAttribute("StrSpecialTKTN", strSpecialTKTN);

            String strDVQHNSAnNinh =
                getThamSoHThong("MA_DVQHNS_AN_NINH", session);
            strDVQHNSAnNinh = LTTCommon.getStringAsArray(strDVQHNSAnNinh);
            request.setAttribute("StrDVQHNSAnNinh", strDVQHNSAnNinh);

            String strCheckAllowEditNHKB =
                getThamSoHThong("CHECK_ALLOW_EDIT_MOTAI_NHKB", session);
            if (strCheckAllowEditNHKB == null ||
                STRING_EMPTY.equals(strCheckAllowEditNHKB)) {
                strCheckAllowEditNHKB = "NO";
            }
            request.setAttribute("CheckAllowEditMoTaiNHKB",
                                 strCheckAllowEditNHKB);

            //Lay ra danh sach cac ngan hang ma kho bac mo tai khoan
            Collection listDMNH = new ArrayList();
            DMNHangDAO dmnhDAO = new DMNHangDAO(conn);

            String strWhere =
                " and c.id = ? and b.loai_tk = ? and b.trang_thai = ?";
            Vector vtParam = new Vector();
            vtParam.add(new Parameter(strKBacID, true));
            vtParam.add(new Parameter("02", true));
            vtParam.add(new Parameter("01", true));

            listDMNH = dmnhDAO.getDMNHListByKBId(strWhere, vtParam);
            DMNHangVO dmnhVO = null;
            Iterator it = listDMNH.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (i >= 1) {
                    break;
                }
                dmnhVO = (DMNHangVO)it.next();
                i++;
            }

            //Khi them moi: Gan du lieu cho Nhkb_nhan phan thong tin chung
            if (dmnhVO != null) {
                request.setAttribute("Ma_nhkb_nhan_cm", dmnhVO.getMa_nh());
                request.setAttribute("Ten_nhkb_nhan_cm", dmnhVO.getTen());
                request.setAttribute("Nhkb_nhan", dmnhVO.getId());
            }
            //Danh sach tien te
            DMTienTeDAO dmTienTeDAO = new DMTienTeDAO(conn);
            Collection listDMTienTe =
                dmTienTeDAO.getDMTienTeCuaDViList(" and a.tinh_trang = '01' and b.kb_id = " +
                                                  strKBacID +
                                                  " and b.loai_tk in ('02','03') ",
                                                  null);
          Collection listDMTienTeAll =
              dmTienTeDAO.getDMTienTeList("",
                                                null);
            request.setAttribute("listDMTienTe", listDMTienTe);
            request.setAttribute("listDMTienTeAll", listDMTienTeAll);
            /////
            request.setAttribute("colDMNH", listDMNH);

            //Danh sach Ltt Master
            if (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) !=
                null) {
                strUserType =
                        session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
            }
            //            String FORMAT_NGAY_24H = "DD/MM/YYYY hh24:mi:ss";
            String strCutOfTime =
                getThamSoHThong("CUT_OF_TIME", session); //16:30:00
            if (strCutOfTime == null || strCutOfTime.isEmpty() ||
                strCutOfTime.equalsIgnoreCase("null")) {
                throw TTSPException.createException("TTSP-1001",
                                                    "Khong lay duoc gio CUT OF TIME cua he thong");
                //strCutOfTime = "16:30:00";
            }

            String strCurrDate = StringUtil.getCurrentDate();
            String strPreviousDate = StringUtil.getPreviousNextDate(-1);

            //So sanh thoi gian hien tai va cutoftime
            if (!LTTCommon.isCurTimeLessThanCutOfTime(strCutOfTime.trim())) {
                strPreviousDate = StringUtil.getCurrentDate();
                strCurrDate = StringUtil.getPreviousNextDate(+1);
            }
            if (!strCutOfTime.startsWith(" "))
                strCutOfTime = " " + strCutOfTime;
            strPreviousDate += strCutOfTime;
            strCurrDate += strCutOfTime;
            if (strUserType.contains(AppConstants.NSD_TTV)) {
                params = new Vector();
                String strKTVTanbmis = "";
                KTVTabmisDAO ktvTabmisDAO = new KTVTabmisDAO(conn);
                strKTVTanbmis =
                        ktvTabmisDAO.getKTVTabmisListByNSD(Long.parseLong(strUserID));
                if (strKTVTanbmis == null ||
                    STRING_EMPTY.equalsIgnoreCase(strKTVTanbmis) ||
                    "'null'".equalsIgnoreCase(strKTVTanbmis) ||
                    "''".equalsIgnoreCase(strKTVTanbmis)) {
                    strKTVTanbmis = "'0'";
                }
                //whereClause = " (t.ktv_chuyen in ( "+strKTVTanbmis +") OR t.ktv_chuyen is null) AND ";
                whereClause =
                        whereClause + " ( (t.nhkb_chuyen = ? AND t.ttv_nhan = ? AND t.nhan = ? AND (t.trang_thai=? OR t.trang_thai=?)) " +
                        " OR (t.nhkb_chuyen = ? AND t.ttv_nhan = ? AND t.nhan = ? " +
                        " AND t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                        " AND t.ngay_hoan_thien <= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                        " AND (t.trang_thai = ? OR t.trang_thai = ? ) " +
                        ") ) ";
                param =
                        new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                      true);
                params.add(param);
                param = new Parameter(strUserID, true);
                params.add(param);
                param = new Parameter("N", true);
                params.add(param);
                param =
                        new Parameter(AppConstants.LTT_TRANG_THAI_KTT_DUYET_DAY_LAI,
                                      true);
                params.add(param);
                param =
                        new Parameter(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET,
                                      true);
                params.add(param);

                param =
                        new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                      true);
                params.add(param);
                param = new Parameter(strUserID, true);
                params.add(param);
                param = new Parameter("N", true);
                params.add(param);
                param =
                        new Parameter(AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG,
                                      true);
                params.add(param);
                param = new Parameter(AppConstants.LTT_TRANG_THAI_HUY, true);
                params.add(param);

                LTTDAO lTTDAO = new LTTDAO(conn);
                if (!STRING_EMPTY.equals(whereClause)) {
                    //whereClause += " AND (d.rv_domain = 'SP_LTT.TRANG_THAI') ";
                    whereClause +=
                            " AND (d.rv_domain = '" + AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT +
                            "') ";
                } else {
                    whereClause +=
                            " (d.rv_domain = '" + AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT +
                            "') ";
                }
                //                reVal = lTTDAO.getLTTDiList(" t.ngay_nhan > SYSDATE-30 and ("+whereClause+")", params);
                reVal = lTTDAO.getLTTDiList(whereClause, params);
                LTTVO lttVo = null;
                // co ban ghi moi set mo ta trang thai
                if (reVal.size() > 0) {
                    Iterator iter = reVal.iterator();
                    lttVo = (LTTVO)iter.next();
                    request.setAttribute("TrangThai", lttVo.getTrang_thai());
                } else {
                    request.setAttribute("TrangThai", "");
                }

                request.setAttribute(AppKeys.LTT_LIST_REQUEST_KEY, reVal);
                
            }

        } catch (TTSPException ttspEx) {
            throw ttspEx;
        } catch (Exception ex) {
            throw TTSPException.createException("TTSP-1000", ex.toString());
        } finally {
            close(conn);
        }

        return mapping.findForward(forward);
    }

    public ActionForward lttDiAddExc(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request,
                                       AppConstants.LTT_DI_CHUC_NANG_THEM)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        forward = AppConstants.SUCCESS;
        Connection conn = null;
        String strMsg = "";
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String strUserID = "";
            String strKBacID = "";
            if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                null) {
                strUserID =
                        session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            }
            if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) != null) {
                strKBacID =
                        session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }

            TTSPUtils ttspUtil = new TTSPUtils(conn);
            //Check quyen Nhap LTT =false la ko dc nhap (O day da bo qua Get danh sach chuc nang)
            if (!ttspUtil.checkQuyenNhapLTT(strUserID)) {
                strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
                request.setAttribute("MsgDialogLTTDi", strMsg);
                request.setAttribute("LastAction", AppConstants.ACTION_ADD);
                //lTTMaster(conn, request, session, "", "");
                //                taoLaiCOA(request);

                return mapping.findForward(forward);
            }
            //checkNgatKetNoi = true, thi ko dc nhap lenh, ko dc hoan thien
            // kiem tra ket noi
            DvGiaoDichDAO dvGiaoDichDAO = new DvGiaoDichDAO(conn);
            DvGiaoDichVO dvGiaoDichVO =
                dvGiaoDichDAO.getDVGD(new Long(strKBacID));
            //          ttspUtil.checkNgatKetNoi(strKBacID)
            if (dvGiaoDichVO != null) {
                //Lay ra danh sach LttDi (master) cho nguoi dung bi Ngat ket noi, danh sach Ngoai te
                //lTTMaster(conn, request, session, "", "");

                strMsg = AppConstants.LTT_NGAT_KET_NOI;
                request.setAttribute("MsgDialogLTTDi", strMsg);
                request.setAttribute("LastAction", AppConstants.ACTION_ADD);
                return mapping.findForward(forward);
            }

            LTTForm f = (LTTForm)form;

            String strTongSoTien = f.getTong_sotien().trim();
            f.setTong_sotien(StringUtil.convertCurrencyToNumber(strTongSoTien,
                                                                f.getNt_id()).toString());
            String strSoTienMuaBan = f.getSo_tien_mua_ban();
            if(!"".equals(strSoTienMuaBan) && strSoTienMuaBan != null && f.getMa_nt_mua_ban() != null && !"".equals(f.getMa_nt_mua_ban())){
              f.setSo_tien_mua_ban(StringUtil.convertCurrencyToNumber(strSoTienMuaBan,
                                                                  f.getMa_nt_mua_ban()).toString());
            }else{
              f.setSo_tien_mua_ban("0");
            }

            LTTVO lTTVO = new LTTVO();
            BeanUtils.copyProperties(lTTVO, f);
            if (f.getNgay_tt() != null) {
                String strNgay_tt =
                    f.getNgay_tt().substring(6, 10) + f.getNgay_tt().substring(3,
                                                                               5) +
                    f.getNgay_tt().substring(0, 2);

                strNgay_tt = ttspUtil.getNgayLamViec(strNgay_tt);
                lTTVO.setNgay_tt(new Long(strNgay_tt));
            }


            String strNgay_ct = f.getNgay_ct();
            lTTVO.setNgay_ct(DateUtils.DateToLong(strNgay_ct));

            if (f.getNgay_tt() != null) {
                String strNgay_tt = f.getNgay_tt();
                while (strNgay_tt.contains("/")) {
                    strNgay_tt = strNgay_tt.replace("/", "");
                }
                strNgay_tt = ttspUtil.getNgayLamViec(strNgay_tt);
                if (strNgay_tt != null && strNgay_tt.length() == 8) {
                    strNgay_tt =
                            strNgay_tt.substring(0, 2) + "/" + strNgay_tt.substring(2,
                                                                                    4) +
                            "/" + strNgay_tt.substring(4, 8);
                    lTTVO.setNgay_tt(DateUtils.DateToLong(strNgay_tt));
                }
            }
            lTTVO.setNhan("N");
            lTTVO.setNgay_nhan(f.getNgay_hoan_thien());

            //            lTTVO.setTong_sotien(new BigDecimal(strTongSoTien));

            if (strUserID != null && !STRING_EMPTY.equalsIgnoreCase(strUserID))
                lTTVO.setTtv_nhan(Long.parseLong(strUserID));

            lTTVO.setTrang_thai(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET);

            String strId = ttspUtil.getSoLTT("103");
            lTTVO.setId(Long.valueOf(strId));

            LTTDAO lTTDAO = new LTTDAO(conn);
            Long id = lTTDAO.insert(lTTVO);

            Long id_ct = Long.parseLong("0");
            boolean bFlagErr = false;
            //          if (id != 0 && arrTkkt_ma != null) {
            LTTCTietDAO lttCTietDAO = new LTTCTietDAO(conn);
            LTTCTietVO lttCTietVO = null;

            LTTCommon common = new LTTCommon();
            HashMap h_map = common.getArraysInCOA(request);
            String[] arrTkkt_ma = (String[])h_map.get("tkkt_ma");
            if (arrTkkt_ma.length > 0) {
                for (int i = 0; i < arrTkkt_ma.length; i++) {
                    lttCTietVO =
                            getRowInCOAFromCode(i, id, conn, h_map, f.getNt_id());
                    //20150120:Check tai khoan chi ngoai te*************************
                    if (!"VND".equalsIgnoreCase(f.getNt_id()) &&
                        !"177".equals(f.getNt_id())) {
                        if (!"00".equals(ttspUtil.checkTKNSNgoaiTe(lttCTietVO.getTkkt_id(),
                                                                   f.getNt_id()))) {
                            throw new Exception("T&#224;i kho&#7843;n chi ng&#226;n s&#225;ch kh&#244;ng h&#7907;p l&#7879;");
                        }
                    }


                    //20150115-manhnv:kiem tra tien chi tiet = 0****************
                    if (lttCTietVO.getSo_tien().compareTo(new BigDecimal("0")) ==
                        0) {
                        throw new Exception("S&#7889; ti&#7873;n chi ti&#7871;t ph&#7843;i kh&#225;c 0");
                    }
                    //**********************************************************

                    id_ct = lttCTietDAO.insert(lttCTietVO);
                }
            }
            //              }
            //          } //end if cho add COA

            if (!bFlagErr) {
                strMsg = AppConstants.SUCCESS;
            } else {
                conn.rollback();
                strMsg = AppConstants.FAILURE;
            }
            request.setAttribute("MsgDialogLTTDi", strMsg);
            request.setAttribute("LastAction", AppConstants.ACTION_ADD);
            String strSoLTT = "";
            if (id != null)
                strSoLTT = id.toString();
            request.setAttribute("SoLTTDi", strSoLTT);
            //Set Log
            saveVisitLog(conn, session, AppConstants.LTT_DI_CHUC_NANG_THEM,
                         "");
            conn.commit();
        }catch (TTSPException ttspEx) {
            throw ttspEx;
        } catch (Exception ex) {
            throw TTSPException.createException("TTSP-1000", ex.toString());
        } finally {
            close(conn);
        }
        return mapping.findForward(forward);
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request,
                                       AppConstants.LTT_DI_CHUC_NANG_SUA)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        forward = AppConstants.SUCCESS;
        Connection conn = null;
        Vector params = null;
        Parameter param = null;
        String whereClause = "";
        String strMsg = "";
        JsonObject jsonObj = null;
        String strJson = null;
        //ManhNV-20150226
        TTSPUtils ttspUtil = null;
        String strSetLock = "";
        Long nSoLTT = new Long(0);
        //*******
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            jsonObj = new JsonObject();
            String strUserID = "";
            if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                null) {
                strUserID =
                        session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            }
            //Get danh sach chuc nang
            List listCNang = new ArrayList();
            listCNang = getDSachCNang(request, AppConstants.LTT_DI);
            String strQuyenLttDi = "";
            if (listCNang != null) {
                Iterator iter = listCNang.iterator();
                ChucNangVO cnangVO = null;
                while (iter.hasNext()) {
                    cnangVO = (ChucNangVO)iter.next();
                    strQuyenLttDi += cnangVO.getLoai_cnang();
                }
            }
            //checkNgatKetNoi = true, thi ko dc nhap lenh, ko dc hoan thien
            String strKBacID = "";
            if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) != null) {
                strKBacID =
                        session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }
            // kiem tra ket noi
            DvGiaoDichDAO dvGiaoDichDAO = new DvGiaoDichDAO(conn);
            DvGiaoDichVO dvGiaoDichVO =
                dvGiaoDichDAO.getDVGD(new Long(strKBacID));
            //          ttspUtil.checkNgatKetNoi(strKBacID)
            if (strQuyenLttDi.indexOf("S") == -1) {
                //Lay ra danh sach LttDi, danh sach Ngoai te
                //                lTTMaster(conn, request, session, "", "");

                strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
                jsonObj.addProperty("strMsgEx", strMsg);
                return mapping.findForward(AppConstants.SUCCESS);
            } else if (dvGiaoDichVO != null) {
                //Lay ra danh sach LttDi (master) cho nguoi dung bi Ngat ket noi, danh sach Ngoai te
                //                lTTMaster(conn, request, session, "", "");

                strMsg = AppConstants.LTT_NGAT_KET_NOI;
                jsonObj.addProperty("strMsgEx", strMsg);
                jsonObj.addProperty("LyDoNgatKetNoi",
                                    dvGiaoDichVO.getLido_ngat());
                return mapping.findForward(AppConstants.SUCCESS);
            } else {
                LTTDAO lTTDAO = new LTTDAO(conn);
                LTTVO lTTVO = new LTTVO();
                String typeNhan =
                    request.getParameter("typeLtt") != null ? request.getParameter("typeLtt") :
                    "";
                //                Truong hop lenh thu cong
                if (typeNhan.equalsIgnoreCase("N")) {
                    String strTongSoTien =
                        request.getParameter("tong_sotien").trim();
                    //                    if (strTongSoTien.indexOf(" ") != -1)
                    //                        strTongSoTien = strTongSoTien.replace(" ", "");
                    //                    if (strTongSoTien.indexOf(".") != -1)
                    //                        strTongSoTien = strTongSoTien.replace(".", "");
                    //                    if (strTongSoTien.indexOf(",") != -1)
                    //                        strTongSoTien = strTongSoTien.replace(",", "");

                    lTTVO.setTong_sotien(StringUtil.convertCurrencyToNumber(strTongSoTien,
                                                                            request.getParameter("nt_id")));                    

                    lTTVO.setNgay_hoan_thien("SYSDATE");

                    String strNgay_ct = request.getParameter("ngay_ct");
                    lTTVO.setNgay_ct(DateUtils.DateToLong(strNgay_ct.trim()));
                    // loi #95
                    lTTVO.setLydo_ktt_day_lai(STRING_EMPTY);
                    lTTVO.setLydo_gd_day_lai(STRING_EMPTY);
                    // end
                    String strNgay_tt = request.getParameter("ngay_tt");
                    String strTen_tk_chuyen =
                        request.getParameter("ten_tk_chuyen");
                    String strNdung_tt = request.getParameter("ndung_tt");
                    lTTVO.setNgay_tt(DateUtils.DateToLong(strNgay_tt.trim()));
                    lTTVO.setTen_tk_chuyen(strTen_tk_chuyen.trim());
                    lTTVO.setNdung_tt(strNdung_tt);
                    // update tat ca tt trong form
                    //                String ma_nhkb_nhan_cm =
                    //                    request.getParameter("ma_nhkb_nhan_cm");
                    String nhkb_nhan = request.getParameter("nhkb_nhan");
                    String ma_nhkb_nhan = request.getParameter("ma_nhkb_nhan");
                    String id_nhkb_nhan = request.getParameter("id_nhkb_nhan");
                    String so_ct_goc = request.getParameter("so_ct_goc");
                    String so_yctt = request.getParameter("so_yctt");
                    String so_tk_chuyen = request.getParameter("so_tk_chuyen");
                    String ma_nhkb_chuyen =
                        request.getParameter("ma_nhkb_chuyen");
                    String id_nhkb_chuyen =
                        request.getParameter("id_nhkb_chuyen");
                    String ttin_kh_chuyen =
                        request.getParameter("ttin_kh_chuyen");
                    String ten_tk_nhan = request.getParameter("ten_tk_nhan");
                    String ttin_kh_nhan = request.getParameter("ttin_kh_nhan");
                    String so_tk_nhan = request.getParameter("so_tk_nhan");
                    String phi = request.getParameter("phi");
                    //              so_ct_goc,so_yctt,so_tk_chuyen,ma_nhkb_chuyen,id_nhkb_chuyen,ttin_kh_chuyen,ten_tk_nhan,ttin_kh_nhan,so_tk_nhan
                    //                lTTVO.setMa_nhkb_nhan_cm(ma_nhkb_nhan_cm);
                    lTTVO.setNhkb_nhan(new Long(nhkb_nhan));
                    lTTVO.setMa_nhkb_nhan(ma_nhkb_nhan);
                    lTTVO.setId_nhkb_nhan(new Long(id_nhkb_nhan));
                    lTTVO.setSo_ct_goc(so_ct_goc);
                    lTTVO.setSo_yctt(so_yctt);
                    lTTVO.setSo_tk_chuyen(so_tk_chuyen);
                    lTTVO.setMa_nhkb_chuyen(ma_nhkb_chuyen);
                    lTTVO.setId_nhkb_chuyen(new Long(id_nhkb_chuyen));
                    lTTVO.setTtin_kh_chuyen(ttin_kh_chuyen);
                    lTTVO.setTen_tk_nhan(ten_tk_nhan);
                    lTTVO.setTtin_kh_nhan(ttin_kh_nhan);
                    lTTVO.setSo_tk_nhan(so_tk_nhan);
                    lTTVO.setPhi(phi);
                    
                    lTTVO.setTen_nhkb_nhan(request.getParameter("ten_nhkb_nhan"));
                    
                    // end
                    Long lTTV_nhan =
                        Long.parseLong(request.getParameter("ttv_nhan"));
                    if (lTTV_nhan != 0 || lTTV_nhan != null)
                        lTTVO.setTtv_nhan(Long.parseLong(strUserID));
                    Long lNt_Id =
                        Long.parseLong(request.getParameter("nt_id"));
                    if (lNt_Id.compareTo(Long.parseLong("0")) == 0) {
                        lTTVO.setNt_id(null);
                    } else {
                        lTTVO.setNt_id(lNt_Id);
                    }
                    //20150619**************************************************                  
                    String nh_tgian = request.getParameter("nh_tgian");
                    String ten_nh_tgian = request.getParameter("ten_nh_tgian");
                    lTTVO.setNh_tgian(nh_tgian);
                    lTTVO.setTen_nh_tgian(ten_nh_tgian);
                    String vay_tra_no_nn = request.getParameter("vay_tra_no_nn");
                    lTTVO.setVay_tra_no_nn(vay_tra_no_nn);
                    //20150619**************************************************
                } else {
                    String strTen_tk_chuyen =
                        request.getParameter("ten_tk_chuyen");
                    String strNdung_tt = request.getParameter("ndung_tt");
                    String so_tk_nhan = request.getParameter("so_tk_nhan");
                    String ten_tk_nhan = request.getParameter("ten_tk_nhan");
                    String ma_nhkb_nhan = request.getParameter("ma_nhkb_nhan");
                    String id_nhkb_nhan = request.getParameter("id_nhkb_nhan");
                    String ttin_kh_nhan = request.getParameter("ttin_kh_nhan");
                  
                    
                    lTTVO.setNdung_tt(strNdung_tt);
                    //BEGIN-20150707: MANHNV: Chi sua khi tham so cho phep sua so TK duoc mo
                    String sua_ten_tk_flag = request.getParameter("sua_ten_tk_flag");
                    if("Y".equalsIgnoreCase(getThamSoHThong(AppConstants.PARAM_LTT_ID_TT_NG_NHAN_SOTK,session))){
                      lTTVO.setSo_tk_nhan(so_tk_nhan);
                    }
                    if("Y".equalsIgnoreCase(getThamSoHThong(AppConstants.PARAM_LTT_ID_TT_NG_NHAN_TENTK,session)) || "ten_tk_nhan".equalsIgnoreCase(sua_ten_tk_flag)){
                      lTTVO.setTen_tk_nhan(ten_tk_nhan);
                    }
                    if("Y".equalsIgnoreCase(getThamSoHThong(AppConstants.PARAM_LTT_ID_TT_NG_NHAN_NHKB,session))){
                      lTTVO.setTen_nhkb_nhan(request.getParameter("ten_nhkb_nhan"));
                      lTTVO.setId_nhkb_nhan(new Long(id_nhkb_nhan)); 
                    }
                    
                    String ma_nhkb_nhan_cu_hiden = request.getParameter("ma_nhkb_nhan_cu_hiden");
                    if("99999999".equals(ma_nhkb_nhan_cu_hiden)){
                      lTTVO.setTen_nhkb_nhan(request.getParameter("ten_nhkb_nhan"));
                      lTTVO.setId_nhkb_nhan(new Long(id_nhkb_nhan)); 
                    }
                    //END-20150707: MANHNV: Chi sua khi tham so cho phep sua so TK duoc mo
                    //----------------------------------------------------------
                    //BEGIN-20150810: MANHNV: Cho phep sua ten TK chuyen
                    lTTVO.setTen_tk_chuyen(strTen_tk_chuyen);
                    //END-20150810: MANHNV: Cho phep sua ten TK chuyen
                    
                    lTTVO.setMa_nhkb_nhan(ma_nhkb_nhan);
                    lTTVO.setTtin_kh_nhan(ttin_kh_nhan);                    
                    
                    //20150619**************************************************
                    String nh_tgian = request.getParameter("nh_tgian");
                    String ten_nh_tgian = request.getParameter("ten_nh_tgian");
                    lTTVO.setNh_tgian(nh_tgian);
                    lTTVO.setTen_nh_tgian(ten_nh_tgian);
                    
                    String phi = request.getParameter("phi");
                    lTTVO.setPhi(phi);
                    
                    String vay_tra_no_nn = request.getParameter("vay_tra_no_nn");
                    lTTVO.setVay_tra_no_nn(vay_tra_no_nn);
                    //20150619**************************************************
                    
                    Long lTTV_nhan =
                        Long.parseLong(request.getParameter("ttv_nhan"));
                    if (lTTV_nhan != 0 || lTTV_nhan != null)
                        lTTVO.setTtv_nhan(Long.parseLong(strUserID));
                }


                //Kiem tra LTT co cho phep update (trang thai # trang thai lay tu form la khong dc update. return listLttAdd, alert message)
                //So sanh trang thai truoc khi Hoan Thien voi trang thai lay duoc tu form
                //            Long nTtv_nhan = null;
//                Long nSoLTT = new Long(0);
                String strStatusNewest = "";
                String strTrangThai = "";

                LTTVO lttVOForUpdate = new LTTVO();

                nSoLTT = Long.parseLong(request.getParameter("id"));
                lTTVO.setId(nSoLTT);
                strTrangThai = request.getParameter("trang_thai");
                lTTVO.setTrang_thai(strTrangThai);
                whereClause = " t.id = ? ";
                param = new Parameter(nSoLTT.toString(), true);
                params = new Vector();
                params.add(param);

                //******************************************************************************
                //Manhnv-20150226: Sua bo for update
                //Kiem tra thay the for update
                ttspUtil = new TTSPUtils(conn);
                strSetLock =
                        ttspUtil.setLock(nSoLTT.toString(), "UPDATE-LTT", new Long(strUserID));
                if (!"SUCCESS".equalsIgnoreCase(strSetLock)) {
                    throw new Exception("LTT &#273;ang &#273;&#432;&#7907;c x&#7917; l&#253; b&#7903;i NSD kh&#225;c(" +
                                        strSetLock +
                                        "), h&#227;y ch&#7885;n LTT kh&#225;c &#273;&#7875; ti&#7871;p t&#7909;c.");
                }
                //lttVOForUpdate = lTTDAO.getLTTVOForUpdate(whereClause, params);
                lttVOForUpdate = lTTDAO.getLTTVO(whereClause, params);
                //******************************************************************************
                //******************************************************************************
                //Kiem tra khong cho phep 2 vai xly 1 LTT
                long lKTTDuyet =
                    lttVOForUpdate.getKtt_duyet() == null ? 0 : lttVOForUpdate.getKtt_duyet().longValue();
                if (Long.parseLong(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()) ==
                    lKTTDuyet) {
                    throw new Exception("Kh&#244;ng &#273;&#432;&#7907;c x&#7917; l&#253; hai vai tr&#234;n c&#249;ng m&#7897;t l&#7879;nh thanh to&#225;n");
                }
                //******************************************************************************

                strStatusNewest = lttVOForUpdate.getTrang_thai();

                if (!strStatusNewest.equalsIgnoreCase(strTrangThai)) {
                    //LTT da dc hoan thien boi nguoi khac
                    //Danh sach LTT master
                    strMsg = AppConstants.LTT_TRANG_THAI_DA_THAY_DOI;
                    jsonObj.addProperty("strMsgEx", strMsg);
                    return mapping.findForward(AppConstants.SUCCESS);
                }
                //lTTVO.setTrang_thai("03");//Cho KTT duyet
                lTTVO.setTrang_thai(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET);
                lTTVO.setLydo_gd_day_lai(STRING_EMPTY);
                lTTVO.setLydo_ktt_day_lai(STRING_EMPTY);
                //20150317-Sua dap ung mua ban ngoai te
                if(request.getParameter("so_tien_mua_ban") != null && !"".equals(request.getParameter("so_tien_mua_ban")) && 
                   request.getParameter("ma_nt_mua_ban") != null && !"".equals(request.getParameter("ma_nt_mua_ban"))){
                  lTTVO.setSo_tien_mua_ban(StringUtil.convertCurrencyToNumber(request.getParameter("so_tien_mua_ban"),request.getParameter("ma_nt_mua_ban")));                  
                }
                lTTVO.setMa_nt_mua_ban(request.getParameter("ma_nt_mua_ban"));
                //20150317
                int result = lTTDAO.update(lTTVO);

                boolean bFlagErr = false;
              /******************************************************************************/
              /*
               * Nguoi sua: ManhNV
               * Ngay sua: 19/06/2015
               * Noi dung: Chi cap nhat lai COA trong truong hop lenh nhap thu cong tren TTSP
               * */
                if (typeNhan.equalsIgnoreCase("N")) {
                // Thong tin chi tiet COA
                  if (result == 1) {
                      LTTCTietDAO lttCTietDAO = new LTTCTietDAO(conn);
                      LTTCTietVO lttCTietVO = null;
  
                      //Xoa LTT chi tiet co ltt_id = id
                      // xem xet bo check ton tai LTTID
                      result = 0;
                      boolean bChildByLttId = false;
                      whereClause = " ltt_id=? ";
                      param = new Parameter(lTTVO.getId(), true);
                      params = new Vector();
                      params.add(param);
                      bChildByLttId =
                              lttCTietDAO.checkExistLTTById(whereClause, params);
                      // neu co ban ghi thi xoa COA
                      if (bChildByLttId) {
                          result = lttCTietDAO.deleteByLTT(lTTVO.getId());
                          if (result < 1) {
                              bFlagErr = true;
                          }
                      }
  
                      if (!bFlagErr) {
                          /*
                         * add lai coa
                         * */
                          Long id_ct = Long.parseLong("0");
                          LTTCommon common = new LTTCommon();
                          HashMap h_map = common.getArraysInCOA(request);
                          String[] arrTkkt_ma = (String[])h_map.get("tkkt_ma");
  
                          if (arrTkkt_ma.length > 0) {
                              //                            BigDecimal dbTongSoTienChiTiet = new BigDecimal(0);
                              for (int i = 0; i < arrTkkt_ma.length; i++) {
                                  lttCTietVO =
                                          getRowInCOAFromCode(i, lTTVO.getId(),
                                                              conn, h_map,
                                                              lttVOForUpdate.getNt_id().toString());
                                  //Tinh tong chi tiet
                                  //                                dbTongSoTienChiTiet = dbTongSoTienChiTiet.add(lttCTietVO.getSo_tien());
                                  //
                                  if (lttCTietVO.getCapns_id() != null &&
                                      lttCTietVO.getCapns_id() != STRING_EMPTY) {
                                      id_ct = lttCTietDAO.insert(lttCTietVO);
                                      if (id_ct == 0) {
                                          bFlagErr = true;
                                          conn.rollback();
                                      }
                                  }
                              }
                          }
  
                          /*
                         *
                         * */
                      }
                  }
              }
              /******************************************************************************/
                if (bFlagErr) {
                    conn.rollback();
                    strMsg = AppConstants.FAILURE;
                    jsonObj.addProperty("strMsgEx", strMsg);
                    return mapping.findForward(AppConstants.SUCCESS);
                } else {
                    strMsg = AppConstants.SUCCESS;
                    strTrangThai = AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET;
                    jsonObj.addProperty("strMsgEx", strMsg);
                    jsonObj.addProperty("strTrangThai", strTrangThai);
                }
                //Save log
                saveVisitLog(conn, session, AppConstants.LTT_DI_CHUC_NANG_SUA,
                             "");
                conn.commit();
            }
        } catch (TTSPException ttspEx) {
            jsonObj.addProperty("strMsgEx", ttspEx.getMessage());
        } catch (Exception ex) {
            jsonObj.addProperty("strMsgEx", ex.getMessage());
        } finally {
            //unlock
            if("SUCCESS".equalsIgnoreCase(strSetLock)){
              if(ttspUtil == null){                
                ttspUtil = new TTSPUtils(conn);
              }
              ttspUtil.unLock(nSoLTT.toString(), "UPDATE-LTT"); 
            }
            //
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    //Day lai

    public ActionForward rejectLttExc(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        if (!checkPermissionOnFunction(request,
                                       AppConstants.LTT_DI_CHUC_NANG_DUYET)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        forward = AppConstants.SUCCESS;
        Connection conn = null;
        Vector params = null;
        Parameter param = null;
        String whereClause = null;
        String strQuyenLttDi = "";
        String strMsg = "";
        JsonObject jsonObj = null;
        String strJson = null;
        //ManhNV-20150226
        TTSPUtils ttspUtil = null;
        String nSoLTT = "";
        String strSetLock = "";
        //****
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            jsonObj = new JsonObject();
            //Get danh sach chuc nang
            List listCNang = new ArrayList();
            listCNang = getDSachCNang(request, AppConstants.LTT_DI);
            if (listCNang != null) {
                Iterator iter = listCNang.iterator();
                ChucNangVO cnangVO = null;
                while (iter.hasNext()) {
                    cnangVO = (ChucNangVO)iter.next();
                    strQuyenLttDi += cnangVO.getLoai_cnang();
                }
            }
            String strKBacID = "";
            if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) != null) {
                strKBacID =
                        session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }
            DvGiaoDichDAO dvGiaoDichDAO = new DvGiaoDichDAO(conn);
            DvGiaoDichVO dvGiaoDichVO =
                dvGiaoDichDAO.getDVGD(new Long(strKBacID));
            //          ttspUtil.checkNgatKetNoi(strKBacID)
            if (dvGiaoDichVO != null) {
                //Lay ra danh sach LttDi (master) cho nguoi dung bi Ngat ket noi, danh sach Ngoai te
                //                lTTMaster(conn, request, session, "", "");
                strMsg = AppConstants.LTT_NGAT_KET_NOI;
                jsonObj.addProperty("strMsgEx", strMsg);
                jsonObj.addProperty("LyDoNgatKetNoi",
                                    dvGiaoDichVO.getLido_ngat());
                return mapping.findForward(AppConstants.SUCCESS);
            } else if (strQuyenLttDi == null ||
                       STRING_EMPTY.equalsIgnoreCase(strQuyenLttDi) ||
                       strQuyenLttDi.indexOf("D") == -1) {
                strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
                forward = AppConstants.SUCCESS;
                //                request.setAttribute("MsgDialogLTTDi", strMsg);
                //                request.setAttribute("LastAction", AppConstants.ACTION_REJECT);
                jsonObj.addProperty("strMsgEx", strMsg);
                return mapping.findForward(AppConstants.SUCCESS);
            } else {
                LTTDAO lttDAO = new LTTDAO(conn);
                LTTVO lttVO = null;
                //            request.setAttribute("soycttTK", lttForm.getSoyctt());
                //            request.setAttribute("nhkbchuyennhanTK",
                //                                 lttForm.getNhkbchuyennhan());
                //            request.setAttribute("trangthaiTK", lttForm.getTrangthai());
                //            request.setAttribute("sotienTK", lttForm.getSotien());
                //Lay ve so LTT, id nguoi dung, loai nguoi dung: ktv, ktt, gd
//                String nSoLTT = "";
                Long nUserID = new Long(0);
                String strUserType = "";
                String strTrangThai = "02";
                String strLyDoDayLai = "";
                String strStatusNewest = "";
                String strKTT = null;
                String strGD = null;
                boolean bChoPDayL = false;
                String strChuKy = null;
                String strMsgId = null;
                //                Long lTtvNhan = new Long(0);

                //            if (lttForm != null) {
                //                nSoLTT = Long.parseLong(lttForm.getId());
                //            }
                nSoLTT = request.getParameter("id");
                if (!(nSoLTT != null && !STRING_EMPTY.equals(nSoLTT))) {
                    strMsg = "ID_NULL";
                    jsonObj.addProperty("strMsgEx", strMsg);
                    return mapping.findForward(AppConstants.SUCCESS);
                } else {
                    if (session.getAttribute("id_nsd") != null &&
                        !STRING_EMPTY.equalsIgnoreCase(session.getAttribute("id_nsd").toString()))
                        nUserID =
                                Long.parseLong(session.getAttribute("id_nsd").toString());

                    //So sanh trang thai truoc khi Huy voi trang thai lay duoc tu form
                    whereClause = " t.id = ? ";
                    param = new Parameter(nSoLTT.toString(), true);
                    params = new Vector();
                    params.add(param);
                    //******************************************************************************
                    //Manhnv-20150226: Sua bo for update
                    //Kiem tra thay the for update
                    ttspUtil = new TTSPUtils(conn);
                    strSetLock =
                            ttspUtil.setLock(nSoLTT, "REJECT-LTT", nUserID);
                    if (!"SUCCESS".equalsIgnoreCase(strSetLock)) {
                        throw new Exception("LTT &#273;ang &#273;&#432;&#7907;c x&#7917; l&#253; b&#7903;i NSD kh&#225;c(" +
                                            strSetLock +
                                            "), h&#227;y ch&#7885;n LTT kh&#225;c &#273;&#7875; ti&#7871;p t&#7909;c.");
                    }
                    //lttVO = lttDAO.getLTTVOForUpdate(whereClause, params);
                    lttVO = lttDAO.getLTTVO(whereClause, params);
                    //******************************************************************************

                    //ManhNV-20141032: Sua cho phep 2 vai***********************
                    long lUserID =
                        Long.parseLong(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                    long lTtvNhan =
                        lttVO.getTtv_nhan() == null ? 0 : lttVO.getTtv_nhan().longValue();
                    if (lUserID == lTtvNhan) {
                        throw new Exception("Kh&#244;ng &#273;&#7849;y l&#7841;i &#273;&#432;&#7907;c l&#7879;nh do ch&#237;nh m&#236;nh nh&#7853;p ho&#7863;c ho&#224;n thi&#7879;n &#7903; vai tr&#242; TTV");
                    }
                    //----------------------------------------------------------

                    if (lttVO != null) {
                      // PHuongNTM07 edited 20160926 start - Neu la dien 103 thi khong dong goi truong nay, neu khong thi dong goi
                      //lttVO.setSo_tk_nhan("");
                       if (!lttVO.getId().toString().subSequence(5, 7).equals("103"))
                         lttVO.setSo_tk_nhan("");
                      // PHuongNTM07 edited 20160926 end - Neu la dien 103 thi khong dong goi truong nay, neu khong thi dong goi
                        strStatusNewest = lttVO.getTrang_thai();
                        strKTT = lttVO.getKtt_duyet() + "";
                        strGD = lttVO.getGd_duyet() + "";
                    }
                    if (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) !=
                        null) {
                        strUserType =
                                session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
                    }
                    if (!STRING_EMPTY.equalsIgnoreCase(strUserType)) {
                        if (strUserType.contains(AppConstants.NSD_KTT)) {
                            if (strKTT.equalsIgnoreCase(nUserID.toString()) &&
                                strStatusNewest.equalsIgnoreCase("04")) {
                                bChoPDayL = true;
                            } else if (strStatusNewest.equalsIgnoreCase("03") &&
                                       (strKTT.equalsIgnoreCase(nUserID.toString()) ||
                                        strKTT.equalsIgnoreCase("null"))) {
                                bChoPDayL = true;
                            }
                            if (bChoPDayL) {
                                strLyDoDayLai =
                                        request.getParameter("lydo_ktt_day_lai");
                            }
                            strTrangThai = "01";
                            // kiem tra ttv nhan
                            // lay ktt chuyen de tim ra ttv
                            //                            String strKTV_chuyen = lttVO.getKtv_chuyen();
                            //                            KTVTabmisDAO tabmisDAO = new KTVTabmisDAO(conn);
                            //                            String strTTVTemp =
                            //                                tabmisDAO.getTTVByKTVChuyen(strKTV_chuyen.trim());
                            //                            //
                            //                            lTtvNhan = lttVO.getTtv_nhan();
                            //                            if (lTtvNhan != null || lTtvNhan != 0) {
                            //                                // kiem tra user
                            //                                UserDAO userDAO = new UserDAO(conn);
                            //                                String whereClauseTTV = " a.id=? ";
                            //                                Vector vParamTTV = new Vector();
                            //                                vParamTTV.add(new Parameter(lTtvNhan, true));
                            //                                UserVO vo =
                            //                                    userDAO.getUser(whereClauseTTV, vParamTTV);
                            //                                // neu khong phai user
                            //                                if (vo == null) {
                            //                                    lTtvNhan = new Long(strTTVTemp);
                            //                                }
                            //                            } else {
                            //                                lTtvNhan = new Long(strTTVTemp);
                            //                            }
                        } else if (strUserType.contains(AppConstants.NSD_GD)) {
                            if (strGD.equalsIgnoreCase("null") &&
                                strStatusNewest.equalsIgnoreCase("05")) {
                                bChoPDayL = true;
                            } else if (strGD.equalsIgnoreCase(nUserID.toString()) &&
                                       strStatusNewest.equalsIgnoreCase("05")) {
                                bChoPDayL = true;
                            }
                            if (bChoPDayL == true) {
                                strLyDoDayLai =
                                        request.getParameter("lydo_gd_day_lai");
                            }
                            strTrangThai = "04";
                        }
                    }

                    int result = 0;
                    if (bChoPDayL == true)
                        result =
                                lttDAO.updateStatus(new Long(nSoLTT), strTrangThai,
                                                    nUserID, strUserType,
                                                    strLyDoDayLai, strChuKy,
                                                    strMsgId, null, null,
                                                    null);

                    if (result == 1) {
                        strMsg = AppConstants.SUCCESS;
                        jsonObj.addProperty("strMsgEx", strMsg);
                        jsonObj.addProperty("strTrangThai", strTrangThai);
                    } else {
                        strMsg = AppConstants.FAILURE;
                        jsonObj.addProperty("strMsgEx", strMsg);
                        return mapping.findForward(AppConstants.SUCCESS);
                    }
                    //                request.setAttribute("MsgDialogLTTDi", strMsg);
                    //                request.setAttribute("LastAction", AppConstants.ACTION_REJECT);
                    //Set Log
                    saveVisitLog(conn, session,
                                 AppConstants.LTT_DI_CHUC_NANG_DUYET,
                                 "Day lai");
                    conn.commit();
                }
            }
        } catch (TTSPException ttspEx) {
            jsonObj.addProperty("strMsgEx", ttspEx.getMessage());
        } catch (Exception ex) {
            jsonObj.addProperty("strMsgEx", ex.getMessage());
        } finally {
          //unlock
          if("SUCCESS".equalsIgnoreCase(strSetLock)){
            if(ttspUtil == null){                
              ttspUtil = new TTSPUtils(conn);
            }
            ttspUtil.unLock(nSoLTT.toString(), "REJECT-LTT"); 
          }
          //
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
    //Huy

    public ActionForward cancelLttExc(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        if (!checkPermissionOnFunction(request,
                                       AppConstants.LTT_DI_CHUC_NANG_XOA)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        forward = AppConstants.SUCCESS;
        Connection conn = null;
        String whereClause = null;
        Vector params = null;
        Parameter param = null;
        String strQuyenLttDi = "";
        String strMsg = "";
        JsonObject jsonObj = null;
        String strJson = null;
      //ManhNV-20150226
      TTSPUtils ttspUtil = null; 
      String strSetLock = "";
      String nSoLTT = "";
      //*******
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            LTTDAO lttDAO = new LTTDAO(conn);
            LTTVO lttVO = null;
            jsonObj = new JsonObject();
            //            request.setAttribute("soycttTK", lttForm.getSoyctt());
            //            request.setAttribute("nhkbchuyennhanTK",
            //                                 lttForm.getNhkbchuyennhan());
            //            request.setAttribute("trangthaiTK", lttForm.getTrangthai());
            //            request.setAttribute("sotienTK", lttForm.getSotien());
            //Get danh sach chuc nang
            List listCNang = new ArrayList();
            listCNang = getDSachCNang(request, AppConstants.LTT_DI);
            if (listCNang != null) {
                Iterator iter = listCNang.iterator();
                ChucNangVO cnangVO = null;
                while (iter.hasNext()) {
                    cnangVO = (ChucNangVO)iter.next();
                    strQuyenLttDi += cnangVO.getLoai_cnang();
                }
            }
            String strKBacID = "";
            if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) != null) {
                strKBacID =
                        session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }
            DvGiaoDichDAO dvGiaoDichDAO = new DvGiaoDichDAO(conn);
            DvGiaoDichVO dvGiaoDichVO =
                dvGiaoDichDAO.getDVGD(new Long(strKBacID));
            //          ttspUtil.checkNgatKetNoi(strKBacID)
            if (dvGiaoDichVO != null) {
                //Lay ra danh sach LttDi (master) cho nguoi dung bi Ngat ket noi, danh sach Ngoai te
                //                lTTMaster(conn, request, session, "", "");
                strMsg = "NGAT_KET_NOI";
                jsonObj.addProperty("strMsgEx", strMsg);
                jsonObj.addProperty("LyDoNgatKetNoi",
                                    dvGiaoDichVO.getLido_ngat());
                return mapping.findForward(AppConstants.SUCCESS);
            } else if (strQuyenLttDi == null ||
                       STRING_EMPTY.equalsIgnoreCase(strQuyenLttDi) ||
                       strQuyenLttDi.indexOf("X") == -1) {
                strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
                // khong co quyen huy
                jsonObj.addProperty("strMsgEx", strMsg);
                return mapping.findForward(AppConstants.SUCCESS);
            } else {
                //Kiem tra LTT Di la Nhap thu cong, hay den tu Tabmis. Lay Trang thai LttDi
                //Nhan=N(Nhap thu cong), Nhan=Y(Den tu tabmis -> chi dc danh dau huy)
                String strNhan = null;
                String strTrangThai = "";
                String strStatusNewest = "";
                String strTTV_nhan = null;
                String strUserID = null;
//                String nSoLTT = "";
                int result = 0;
                if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                    null) {
                    strUserID =
                            session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
                }
                //            if (lttForm != null) {
                //                strNhan = lttForm.getNhan();
                //                nSoLTT = Long.parseLong(lttForm.getId());
                //            }
                nSoLTT = request.getParameter("id");
                strTrangThai = request.getParameter("trang_thai");
                strNhan = request.getParameter("nhan");
                //So sanh trang thai truoc khi Huy voi trang thai lay duoc tu form
                //Fai dung them cua TTV_Nhan
                //strStatusNewest = lttDAO.getStatusForUpdate(nSoLTT);
                whereClause = " t.id = ? ";
                param = new Parameter(nSoLTT.toString(), true);
                params = new Vector();
                params.add(param);
                //******************************************************************************
              //Manhnv-20150226: Sua bo for update
              //Kiem tra thay the for update
              ttspUtil = new TTSPUtils(conn);
              strSetLock =
                      ttspUtil.setLock(nSoLTT, "CANCEL-LTT", new Long(strUserID));
              if (!"SUCCESS".equalsIgnoreCase(strSetLock)) {
                  throw new Exception("LTT &#273;ang &#273;&#432;&#7907;c x&#7917; l&#253; b&#7903;i NSD kh&#225;c(" +
                                      strSetLock +
                                      "), h&#227;y ch&#7885;n LTT kh&#225;c &#273;&#7875; ti&#7871;p t&#7909;c.");
              }
              //lttVO = lttDAO.getLTTVOForUpdate(whereClause, params);
              lttVO = lttDAO.getLTTVO(whereClause, params);
              //******************************************************************************
                if (lttVO != null) {
                    //******************************************************************************
                    //Kiem tra khong cho phep 2 vai xly 1 LTT
                    long lKTTDuyet =
                        lttVO.getKtt_duyet() == null ? 0 : lttVO.getKtt_duyet().longValue();
                    if (Long.parseLong(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()) ==
                        lKTTDuyet) {
                        throw new Exception("Kh&#244;ng &#273;&#432;&#7907;c x&#7917; l&#253; hai vai tr&#234;n c&#249;ng m&#7897;t l&#7879;nh thanh to&#225;n");
                    }
                    //******************************************************************************

                    strStatusNewest = lttVO.getTrang_thai();
                    if (lttVO.getTtv_nhan() != null) {
                        strTTV_nhan = lttVO.getTtv_nhan().toString();
                    }
                }
                LTTVO lttVO2 = null;
                //Neu Nhap=N va trang_thai in(KTT day lai-01, Cho TTV Hoan Thien-02, Cho KTT duyet-03) => Cho phep xoa
                //Neu Nhap=Y va va trang_thai in(KTT day lai-01, Cho TTV Hoan Thien-02, Cho KTT duyet-03) =>  Danh dau trang_thai=06(Huy)
                if (strTTV_nhan == null) {
                    lttVO2 = new LTTVO();
                    lttVO2.setTrang_thai(AppConstants.LTT_TRANG_THAI_HUY);
                    lttVO2.setId(lttVO.getId());
                    lttVO2.setNgay_hoan_thien("SYSDATE");
                    lttVO2.setTtv_nhan(Long.parseLong(strUserID));
                    result = lttDAO.update(lttVO2);
                } else if (strTTV_nhan != null) {
                    if (strTrangThai.equalsIgnoreCase(strStatusNewest)) {
                        if (strNhan.equalsIgnoreCase("N")) {
                            if (AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET.equals(strTrangThai) ||
                                AppConstants.LTT_TRANG_THAI_CHO_HOAN_THIEN.equals(strTrangThai)) {
                                //Xoa trong sp_ltt_ctiet
                                LTTCTietDAO lttCTietDAO =
                                    new LTTCTietDAO(conn);
                                result =
                                        lttCTietDAO.deleteByLTT(new Long(nSoLTT));
                                //Xoa trong sp_ltt
                                if (result == 1) {
                                    result = lttDAO.delete(new Long(nSoLTT));
                                }
                            } else if (AppConstants.LTT_TRANG_THAI_KTT_DUYET_DAY_LAI.equals(strTrangThai)) {
                                lttVO2 = new LTTVO();
                                lttVO2.setTrang_thai(AppConstants.LTT_TRANG_THAI_HUY);
                                lttVO2.setNgay_hoan_thien("SYSDATE");
                                lttVO2.setId(lttVO.getId());
                                //                                lttVO2.setNgay_hoan_thien("SYSDATE");
                                result = lttDAO.update(lttVO2);
                            }
                        } else if (strNhan.equalsIgnoreCase("Y")) {
                            if (AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET.equals(strTrangThai) ||
                                AppConstants.LTT_TRANG_THAI_CHO_HOAN_THIEN.equals(strTrangThai) ||
                                AppConstants.LTT_TRANG_THAI_KTT_DUYET_DAY_LAI.equals(strTrangThai)) {
                                lttVO2 = new LTTVO();
                                lttVO2.setTrang_thai(AppConstants.LTT_TRANG_THAI_HUY);
                                lttVO2.setNgay_hoan_thien("SYSDATE");
                                lttVO2.setId(lttVO.getId());
                                lttVO2.setTtv_nhan(new Long(strTTV_nhan));
                                //                                lttVO2.setNgay_hoan_thien("SYSDATE");
                                result = lttDAO.update(lttVO2);
                            }
                        }
                    } else {
                        //Thong bao trang thai da duoc thay doi ...
                        //request.setAttribute("MsgCode", "TTSP-STATUS-CHANGED");
                        strMsg = AppConstants.LTT_TRANG_THAI_DA_THAY_DOI;
                        jsonObj.addProperty("strMsgEx", strMsg);
                        return mapping.findForward(AppConstants.SUCCESS);
                    }
                }

                if (result == 1) {
                    //request.setAttribute("MsgCode", "TTSP-SUCCESS");
                    strMsg = AppConstants.SUCCESS;
                    jsonObj.addProperty("strMsgEx", strMsg);
                } else {
                    //forward = AppConstants.FAILURE;
                    //request.setAttribute("MsgCode", "TTSP-FAILURE");
                    strMsg = AppConstants.FAILURE;
                    jsonObj.addProperty("strMsgEx", strMsg);
                    return mapping.findForward(AppConstants.SUCCESS);
                }
                //Set Log
                saveVisitLog(conn, session, AppConstants.LTT_DI_CHUC_NANG_XOA,
                             "");
                conn.commit();
            }

        } catch (TTSPException ttspEx) {
            jsonObj.addProperty("strMsgEx", ttspEx.getMessage());
            throw ttspEx;
        } catch (Exception ex) {
            jsonObj.addProperty("strMsgEx", ex.getMessage());
            throw TTSPException.createException("TTSP-1004", ex.toString());
        } finally {
          //unlock
          if("SUCCESS".equalsIgnoreCase(strSetLock)){
            if(ttspUtil == null){                
              ttspUtil = new TTSPUtils(conn);
            }
            ttspUtil.unLock(nSoLTT.toString(), "CANCEL-LTT"); 
          }
          //
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    //Duyet

    public ActionForward approvedLttExc(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        forward = AppConstants.SUCCESS;
        Connection conn = null;
        Vector params = null;
        Parameter param = null;
        String whereClause = null;
        String strQuyenLttDi = "";
        String strMsg = "";
        String strMsgErrDesLTTDi = "";
        JsonObject jsonObj = new JsonObject();
        String strJson = null;
        String strTrangThaiTW = "00";

//        QueueManager queueManager = null;//DOUBLE_20161122
        boolean checkCommitMQ = false;
        //ManhNV-20150226
        TTSPUtils ttspUtil = null; 
        String strSetLock = "";
        Long nSoLTT = new Long(0);
        //*******

        if (!checkPermissionOnFunction(request,
                                       AppConstants.LTT_DI_CHUC_NANG_DUYET)) {
            strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
            jsonObj.addProperty("strMsgEx", strMsg);
            //            request.setAttribute("LastAction", AppConstants.ACTION_APPROVED);
            return mapping.findForward(forward);
        }
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();

            //khoi tao QueueManager
//            queueManager = new QueueManager(getThamSoHThong(session));//DOUBLE_20161122

            String strCheckCOT = request.getParameter("checkCOT");
            //Get danh sach chuc nang
            List listCNang = new ArrayList();
            listCNang = getDSachCNang(request, AppConstants.LTT_DI);
            if (listCNang != null) {
                Iterator iter = listCNang.iterator();
                ChucNangVO cnangVO = null;
                while (iter.hasNext()) {
                    cnangVO = (ChucNangVO)iter.next();
                    strQuyenLttDi += cnangVO.getLoai_cnang();
                }
            }
            String strKBacID = "";
            if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) != null) {
                strKBacID =
                        session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }
            DvGiaoDichDAO dvGiaoDichDAO = new DvGiaoDichDAO(conn);
            DvGiaoDichVO dvGiaoDichVO =
                dvGiaoDichDAO.getDVGD(new Long(strKBacID));
            //          ttspUtil.checkNgatKetNoi(strKBacID)
            if (dvGiaoDichVO != null) {
                //Lay ra danh sach LttDi (master) cho nguoi dung bi Ngat ket noi, danh sach Ngoai te
                //                lTTMaster(conn, request, session, "", "");
                strMsg = "NGAT_KET_NOI";
                jsonObj.addProperty("strMsgEx", strMsg);
                jsonObj.addProperty("LyDoNgatKetNoi",
                                    dvGiaoDichVO.getLido_ngat());
                return mapping.findForward(AppConstants.SUCCESS);
            } else if (strQuyenLttDi == null ||
                       STRING_EMPTY.equalsIgnoreCase(strQuyenLttDi) ||
                       strQuyenLttDi.indexOf("D") == -1) {
                strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
                jsonObj.addProperty("strMsgEx", strMsg);
                return mapping.findForward(AppConstants.SUCCESS);
            } else {
                LTTDAO lttDAO = new LTTDAO(conn);
                //                LTTForm lttForm = (LTTForm)form;
                LTTVO lttVO = null;
                //Lay ve so LTT, id nguoi dung, loai nguoi dung: ktv, ktt, gd
//                Long nSoLTT = new Long(0);
                Long nUserID = null;
                String strUserType = "";
                String strTrangThai = "";
                Long lNgayTT = new Long(0);
                String strLyDoDayLai = "";
                BigDecimal nTongSoTien = new BigDecimal(0);
                String strTongTien = "0";
                String strStatusNewest = "";
                String strKTT = null;
                String strGD = null;
                boolean bChoPhepDuyet = false;
                String strChuKy = null;
                String strNhan = "";
                String strMsgId = null;
                boolean allowSend = false;

                nSoLTT = new Long(request.getParameter("id"));
                strTongTien = request.getParameter("tong_sotien").trim();
                nTongSoTien =
                        StringUtil.convertCurrencyToNumber(strTongTien, request.getParameter("nt_id"));

                strNhan = request.getParameter("nhan");
                if (session.getAttribute("id_nsd") != null &&
                    !STRING_EMPTY.equalsIgnoreCase(session.getAttribute("id_nsd").toString()))
                    nUserID =
                            Long.parseLong(session.getAttribute("id_nsd").toString());

                if (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) !=
                    null) {
                    strUserType =
                            session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
                }
                //Xac thuc chu ky: hop le moi cho gui
                boolean bVerifySignature = false;
                String strWSDL = getThamSoHThong("WSDL_PKI", session);
                PKIService pkiService = new PKIService(strWSDL);
                String userName =
                    session.getAttribute(AppConstants.APP_DOMAIN_SESSION) +
                    "\\" +
                    session.getAttribute(AppConstants.APP_USER_CODE_SESSION);
                String certSerial = request.getParameter("certserial");
                String contenData = request.getParameter("noi_dung_ky");
                //                lttForm.getNoi_dung_ky();
                String signature = request.getParameter("signature");
                String strAppID = getThamSoHThong("APP_ID", session);
                String[] arrResultKy =
                    pkiService.VerifySignature(userName, certSerial,
                                               contenData, signature,
                                               strAppID);

                if (arrResultKy != null && arrResultKy.length == 2) {
                    if (arrResultKy[0].equalsIgnoreCase("VALID")) {
                        bVerifySignature = true;
                    } else if (arrResultKy[0].equalsIgnoreCase("INVALID")) {
                        jsonObj.addProperty("strMsgEx", arrResultKy[1]);
                        return mapping.findForward(AppConstants.SUCCESS);
                    } else if (arrResultKy[0].equalsIgnoreCase("ERROR")) {
                        jsonObj.addProperty("strMsgEx", arrResultKy[1]);
                        return mapping.findForward(AppConstants.SUCCESS);
                    }
                }

                if (bVerifySignature) {
                    if (!STRING_EMPTY.equalsIgnoreCase(strUserType)) {
                        //So sanh trang thai truoc khi Huy voi trang thai lay duoc tu form
                        whereClause = " t.id = ? ";
                        param = new Parameter(nSoLTT.toString(), true);
                        params = new Vector();
                        params.add(param);
                      //******************************************************************************
                      //Manhnv-20150226: Sua bo for update
                      //Kiem tra thay the for update
                      ttspUtil = new TTSPUtils(conn);  
                      strSetLock =
                              ttspUtil.setLock(nSoLTT.toString(), "APPROVE-LTT", new Long(nUserID));
                      if (!"SUCCESS".equalsIgnoreCase(strSetLock)) {
                          throw new Exception("LTT &#273;ang &#273;&#432;&#7907;c x&#7917; l&#253; b&#7903;i NSD kh&#225;c(" +
                                              strSetLock +
                                              "), h&#227;y ch&#7885;n LTT kh&#225;c &#273;&#7875; ti&#7871;p t&#7909;c.");
                      }
                      //lttVO = lttDAO.getLTTVOForUpdate(whereClause, params);
                      lttVO = lttDAO.getLTTVO(whereClause, params);
                      //******************************************************************************
                        if (lttVO != null) {
                            strStatusNewest = lttVO.getTrang_thai();
                            strKTT = lttVO.getKtt_duyet() + "";
                            strGD = lttVO.getGd_duyet() + "";
                        }

                        if (strUserType.contains(AppConstants.NSD_KTT)) {
                            if (strKTT.equalsIgnoreCase(nUserID.toString())) {
                                if (AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET.equals(strStatusNewest) ||
                                    AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI.equals(strStatusNewest)) {
                                    bChoPhepDuyet = true;
                                }
                            } else if (AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET.equals(strStatusNewest) &&
                                       strKTT.equalsIgnoreCase("null")) {
                                bChoPhepDuyet = true;
                            }
                            if (bChoPhepDuyet) {
                                if (!STRING_EMPTY.equalsIgnoreCase(strNhan)) {
                                    if ("Y".equalsIgnoreCase(strNhan)) {
                                        allowSend = true;
                                        BigDecimal nSoTienGDDuyetTabmis = null;
                                        String strSoTienTabmis =
                                            getThamSoHThong(AppConstants.PARAM_GD_DUYET_TABMIS_LUONG_TIEN,
                                                            session);

                                        if (strSoTienTabmis != null &&
                                            !STRING_EMPTY.equalsIgnoreCase(strSoTienTabmis)) {
                                            nSoTienGDDuyetTabmis =
                                                    new BigDecimal(strSoTienTabmis);
                                        } else {
                                            jsonObj.addProperty("strMsgEx",
                                                                "TIEN_GD_DUYET_TM");
                                            //                                            throw TTSPException.createException("1012",
                                            //                                                                                "Loi lay luong tien GD duyet Tabmis");
                                        }
                                        //nTongSoTien >= soTienGDDuyetTabmis
                                        if (nTongSoTien.compareTo(nSoTienGDDuyetTabmis) >=
                                            0) {
                                            strTrangThai =
                                                    AppConstants.LTT_TRANG_THAI_CHO_GD_DUYET; //Cho GD duyet
                                            allowSend =
                                                    false; //Khong gui LTT di
                                        } else {
                                            strTrangThai =
                                                    AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG; //Da gui
                                        }
                                    } else if ("N".equalsIgnoreCase(strNhan)) {
                                        allowSend = true;
                                        BigDecimal nSoTienGDDuuyetThuCong =
                                            null;
                                        String strSoTienThuCong =
                                            getThamSoHThong(AppConstants.PARAM_GD_DUYET_THUCONG_LUONG_TIEN,
                                                            session);

                                        if (strSoTienThuCong != null &&
                                            !STRING_EMPTY.equalsIgnoreCase(strSoTienThuCong)) {
                                            nSoTienGDDuuyetThuCong =
                                                    new BigDecimal(strSoTienThuCong);
                                        } else {
                                            jsonObj.addProperty("strMsgEx",
                                                                "TIEN_GD_DUYET_TC");
                                            //                                            throw TTSPException.createException("1012",
                                            //                                                                                "Loi lay luong tien GD duyet Thu cong");
                                        }
                                        //nTongSoTien >= soTienGDDuuyetThuCong
                                        if (nTongSoTien.compareTo(nSoTienGDDuuyetThuCong) >=
                                            0) {
                                            strTrangThai =
                                                    AppConstants.LTT_TRANG_THAI_CHO_GD_DUYET; //Cho GD duyet
                                            allowSend =
                                                    false; //Khong gui LTT di
                                        } else {
                                            strTrangThai =
                                                    AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG; //Da gui
                                        }
                                    }
                                }
                            }
                            strChuKy = request.getParameter("chuky_ktt");
                        } else if (strUserType.contains(AppConstants.NSD_GD)) {
                            //                        if(!strGD.equalsIgnoreCase(nUserID.toString()) && AppConstants.LTT_TRANG_THAI_CHO_GD_DUYET.equals(strStatusNewest)){
                            //                            bChoPhepDuyet = true;
                            //                            allowSend = true;
                            //                        }
                            if (AppConstants.LTT_TRANG_THAI_CHO_GD_DUYET.equals(strStatusNewest)) {
                                if (strGD.equalsIgnoreCase(nUserID.toString()) ||
                                    strGD.equalsIgnoreCase("null")) {
                                    bChoPhepDuyet = true;
                                    allowSend = true;
                                }
                            }

                            strTrangThai =
                                    AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG;
                            strChuKy = request.getParameter("chuky_gd");
                        }
                    }
                    Date dateKy = new Date();
                    String strNgayKy =
                        StringUtil.DateToString(dateKy, AppConstants.DATE_TIME_FORMAT_SEND_ORDER);
                    if (strCheckCOT != null) {
                        if (strCheckCOT.equalsIgnoreCase("PASSTIME")) {
                            Date dateCurrent = new Date();
                            ttspUtil = new TTSPUtils(conn);
                            SimpleDateFormat dateFormat =
                                new SimpleDateFormat("yyyyMMdd");
                            int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
                            String strNextDate =
                                dateFormat.format(dateCurrent.getTime() +
                                                  MILLIS_IN_DAY);
                            String strNgayTT =
                                ttspUtil.getNgayLamViec(strNextDate);
                            lNgayTT = new Long(strNgayTT);

                        } else if (strCheckCOT.equalsIgnoreCase("INTIMEUPDATE")) {
                            String strNgayLamViec =
                                StringUtil.DateToString(new Date(),
                                                        "yyyyMMdd");
                            lNgayTT = new Long(strNgayLamViec);
                        }
                    }
                    int result = 0;
                    if (allowSend) {
//DOUBLE_20161122-BEGIN
//                        queueManager.setQueueManager();
//                        SendLTToan sendLTT =
//                            new SendLTToan(conn, queueManager);
//DOUBLE_20161122-END

                        String strHachToanTheoNgayKSNH = "Y";
                        if (session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION) !=
                            null) {
                            strHachToanTheoNgayKSNH =
                                    session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION).toString();
                        }
                        BuildMsgLTT sendLTT = new BuildMsgLTT(conn);//DOUBLE_20161122
                        strMsgId =
                                sendLTT.sendMessage(nSoLTT.toString(), session.getAttribute(AppConstants.APP_KB_CODE_SESSION) ==
                                                                       null ?
                                                                       "" :
                                                                       session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(),
                                                    session.getAttribute(AppConstants.APP_USER_CODE_SESSION) ==
                                                    null ? "" :
                                                    session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString(),
                                                    strNgayKy,
                                                    strHachToanTheoNgayKSNH,
                                                    session, "");


                        if (strMsgId == null ||
                            STRING_EMPTY.equals(strMsgId)) {
                            bChoPhepDuyet = false;
                            //                      request.setAttribute("MsgErrDesLTTDi", "ltt_di.error.khong_gui_duoc");
                            strMsg = "ltt_di.error.khong_gui_duoc";
                            jsonObj.addProperty("strMsgEx", strMsg);
                            return mapping.findForward(AppConstants.SUCCESS);
                        } else if (AppConstants.DIEU_HANH_VON.equalsIgnoreCase(strMsgId)) {
                            jsonObj.addProperty("strThongBaoDHV",
                                                AppConstants.DIEU_HANH_VON);
                            if (AppConstants.KBNN_SGD_BANK_CODE.equals(session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION.toString()))) {
                                strTrangThaiTW = "02";
                            } else {
                                strTrangThaiTW = "01";
                            }
                        } else {
                            checkCommitMQ = true;
                        }
                    }

                    if (nSoLTT != 0 && bChoPhepDuyet == true) {
                        result =
                                lttDAO.updateStatus(nSoLTT, strTrangThai, nUserID,
                                                    strUserType, strLyDoDayLai,
                                                    strChuKy, strMsgId, dateKy,
                                                    lNgayTT, strTrangThaiTW);
                    } else {
                        if (STRING_EMPTY.equals(strMsgErrDesLTTDi) ||
                            strMsgErrDesLTTDi == null) {
                            //                      request.setAttribute("MsgErrDesLTTDi", "ltt_di.error.khong_duoc_phep_duyet");
                            if (!bChoPhepDuyet) {
                                strMsg = "ltt_di.error.khong_duoc_phep_duyet";
                                jsonObj.addProperty("strMsgEx", strMsg);
                                return mapping.findForward(AppConstants.SUCCESS);
                            } else {
                                strMsg = "ltt_di.error.chu_ky_so";
                                jsonObj.addProperty("strMsgEx", strMsg);
                                return mapping.findForward(AppConstants.SUCCESS);
                            }
                        }
                    }

                    if (result == 1) {
                        strMsg = AppConstants.SUCCESS;
                        jsonObj.addProperty("strMsgEx", strMsg);
                        jsonObj.addProperty("strTrangThai", strTrangThai);
                    } else {
                        //20151030-ROLLBACK MQ khi ko cap nhat dc CSDL
//                        DOUBLE_20161122-BEGIN
//                        if (queueManager != null) {
//                            queueManager.rollbackMQ();
//                        }
//                        DOUBLE_20161122-END
                        //----                        
                        strMsg = AppConstants.FAILURE;
                        //request.setAttribute("MsgErrDesLTTDi", "ltt_di.error.cap_nhat_loi");
                        jsonObj.addProperty("strMsgEx", strMsg);
                        return mapping.findForward(AppConstants.SUCCESS);
                    }
                } else {
                    strMsg = "ltt_di.error.chu_ky_so";
                    jsonObj.addProperty("strMsgEx", strMsg);
                    return mapping.findForward(AppConstants.SUCCESS);
                }
                //Set Log
                saveVisitLog(conn, session,
                             AppConstants.LTT_DI_CHUC_NANG_DUYET, "Duyet");

                conn.commit();
//                if (checkCommitMQ) {
//                    DOUBLE_20161122-BEGIN
//                    try {
//                        queueManager.commitMQ();
//                    } catch (Exception eCommitMQ) {
//                        lttDAO.updateStatus(nSoLTT, "05", null, null, null,
//                                            null, "LOI COMMIT MQ", null, null,
//                                            null);
//                        conn.commit();
//                        throw eCommitMQ;
//                    }
//                    DOUBLE_20161122-END
//                }
            }
        } catch (Exception ex) {
            //ROLLBACK MQ
//            DOUBLE_20161122-BEGIN
//            if (queueManager != null) {
//                queueManager.rollbackMQ();
//            }
//            DOUBLE_20161122-END
            jsonObj.addProperty("strMsgEx", ex.getMessage());
        } finally {
            //unlock
            if("SUCCESS".equalsIgnoreCase(strSetLock)){
              if(ttspUtil == null){                
                ttspUtil = new TTSPUtils(conn);
              }
              ttspUtil.unLock(nSoLTT.toString(), "APPROVE-LTT"); 
            }
            //
            //Disconnect DB & MQ
            close(conn);
//            DOUBLE_20161122-BEGIN
//            if (queueManager != null) {
//                queueManager.disconnectMQ();
//            }
//            DOUBLE_20161122-END

            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    //Tim kiem So YCTT

    public ActionForward searchLttDi(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        forward = AppConstants.SUCCESS;

        return mapping.findForward(forward);
    }

    public ActionForward searchLttDiExc(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        forward = AppConstants.SUCCESS;
        return mapping.findForward(forward);
    }

    //LTT Master, strDSachCNang = "LTT.DI"

    public void lTTMaster(Connection conn, HttpServletRequest request,
                          HttpSession session, String strChucNang,
                          String strSoLTT) throws Exception {
        Collection reVal = null;
        Vector params = null;
        Parameter param = null;
        String whereClause = "";
        String strQuyenLttDi = "";
        String strUserType = "";
      /**- Nguoi sua: ManhNV
       * - Noi dung: 
       * + Bo sung dieu kien truy van theo ngay_nhan (ngay_nhan > SYSDATE-"tham_so")
       * + Dap ung sua loi: Java heap size
       * - Ngay sua 01/11/2016
       * - Key tim kiem: 20161101-JHS-MSTER
       * */
        String strSoNgayTruyVan = "100";
        try {
            LTTDAO lTTDAO = new LTTDAO(conn);
            boolean bFirstRowM = true;
            String strUserID = "";
            if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                null) {
                strUserID =
                        session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            }
          /***********20161101-JHS-MSTER-lay tham so tu session-BEGIN************/
          if (session.getAttribute("SO_NGAY_TRUY_VAN_XU_LY_LTT") !=
              null) {
              strSoNgayTruyVan =
                      session.getAttribute("SO_NGAY_TRUY_VAN_XU_LY_LTT").toString();
          }
          /***********20161101-JHS-MSTER-lay tham so tu session-END************/
            // kiem tra ket noi
            //            DvGiaoDichDAO dvGiaoDichDAO = new DvGiaoDichDAO(conn);
            //            DvGiaoDichVO dvGiaoDichVO =
            //                dvGiaoDichDAO.getDVGD(new Long(strKBacID));
            //            //          ttspUtil.checkNgatKetNoi(strKBacID)
            //            if (dvGiaoDichVO != null) {
            //                request.setAttribute("MessageLttDi",
            //                                     AppConstants.LTT_NGAT_KET_NOI);
            //            }

            //Get danh sach chuc nang
            List listCNang = new ArrayList();
            listCNang = getDSachCNang(request, AppConstants.LTT_DI);
            if (listCNang != null) {
                Iterator iter = listCNang.iterator();
                ChucNangVO cnangVO = null;
                while (iter.hasNext()) {
                    cnangVO = (ChucNangVO)iter.next();
                    strQuyenLttDi += cnangVO.getLoai_cnang();
                }
            }
            //            TTSPUtils ttspUtil = new TTSPUtils(conn);
            //            //check quyen nhap LTT =false la ko dc nhap
            //            if (!ttspUtil.checkQuyenNhapLTT(strUserID)) {
            //                if (strQuyenLttDi.contains("T")) {
            //                    strQuyenLttDi = strQuyenLttDi.replace("T", "");
            //                }
            //            } else {
            //                if (strQuyenLttDi.contains("T")) {
            //                    strQuyenLttDi = strQuyenLttDi.replace("T", "TT");
            //                }
            //            }
            //Kiem tra strQuyenLttDi xem user login la: TTV, KTT, GD de hien thi list tuong ung (cong voi cac LTT gan voi userID)

            if (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) !=
                null) {
                strUserType =
                        session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
            }
            
            if (AppConstants.LTT_DI_CHUC_NANG_CHI_TIET.equalsIgnoreCase(strChucNang)) {
                String strSo_ltt = "";
                strSo_ltt = request.getParameter("so_ltt_details");
                params = new Vector();
                if (strSo_ltt != null &&
                    !STRING_EMPTY.equalsIgnoreCase(strSo_ltt)) {
                    whereClause = " t.id = ? ";
                    param = new Parameter(Long.parseLong(strSo_ltt), true);
                    params.add(param);
                }
                strQuyenLttDi = "THOAT";
                strSoLTT = strSo_ltt;
                bFirstRowM = false;

            } else if (AppConstants.LTT_DI_CHUC_NANG_CHI_TIET_DUYETLO.equalsIgnoreCase(strChucNang)) {
                String tcuu = "";
                // request type
                String strTypeDuyetLo =
                    request.getParameter("typeLO") != null ?
                    request.getParameter("typeLO") : "";
                String strSo_ltt = "";
                strSo_ltt = request.getParameter("so_ltt_details");
                if (strTypeDuyetLo.equals(STRING_EMPTY)) {
                    String so_yctt =
                        request.getParameter("so_yctt") == null ? "" :
                        request.getParameter("so_yctt");
                    String so_tien =
                        request.getParameter("so_tien") == null ? "" :
                        request.getParameter("so_tien");
                    String ma_nsd =
                        request.getParameter("ma_nsd") == null ? "" :
                        request.getParameter("ma_nsd");
                    String ma_tabmis =
                        request.getParameter("ma_tabmis") == null ? "" :
                        request.getParameter("ma_tabmis");
                    String ma_nh =
                        request.getParameter("ma_nh") == null ? "" : request.getParameter("ma_nh");
                    String pageNumber =
                        request.getParameter("pageNumber") == null ? "" :
                        request.getParameter("pageNumber");
                    tcuu =
"&so_yctt=" + so_yctt + "&so_tien=" + so_tien + "&ma_nh=" + ma_nh +
 "&ma_nsd=" + ma_nsd + "&ma_tabmis=" + ma_tabmis + "&pageNumber=" + pageNumber;
                }

                params = new Vector();
                if (strSo_ltt != null &&
                    !STRING_EMPTY.equalsIgnoreCase(strSo_ltt)) {
                    whereClause = " t.id = ? ";
                    param = new Parameter(Long.parseLong(strSo_ltt), true);
                    params.add(param);
                }
                strQuyenLttDi = "LO";
                strSoLTT = strSo_ltt;
                bFirstRowM = false;
                request.setAttribute("tcuu", tcuu);

            } else if (AppConstants.LTT_DI_CHUC_NANG_CHI_TIET_DHV.equalsIgnoreCase(strChucNang)) {
                String strSo_ltt = "";
                strSo_ltt = request.getParameter("so_ltt_details");
                String so_yctt =
                    request.getParameter("so_yctt") == null ? "" : request.getParameter("so_yctt");
                String so_tien =
                    request.getParameter("so_tien") == null ? "" : request.getParameter("so_tien");
                String ma_nsd =
                    request.getParameter("ma_nsd") == null ? "" : request.getParameter("ma_nsd");
                String ma_tabmis =
                    request.getParameter("ma_tabmis") == null ? "" :
                    request.getParameter("ma_tabmis");
                String ma_nh =
                    request.getParameter("ma_nh") == null ? "" : request.getParameter("ma_nh");
                String pageNumber =
                    request.getParameter("pageNumber") == null ? "" :
                    request.getParameter("pageNumber");
                String tcuu =
                    "&so_yctt=" + so_yctt + "&so_tien=" + so_tien + "&ma_nh=" +
                    ma_nh + "&ma_nsd=" + ma_nsd + "&ma_tabmis=" + ma_tabmis +
                    "&pageNumber=" + pageNumber;
                params = new Vector();
                if (strSo_ltt != null &&
                    !STRING_EMPTY.equalsIgnoreCase(strSo_ltt)) {
                    whereClause = " t.id = ? ";
                    param = new Parameter(Long.parseLong(strSo_ltt), true);
                    params.add(param);
                }
                strQuyenLttDi = "DHV";
                strSoLTT = strSo_ltt;
                bFirstRowM = false;
                request.setAttribute("tcuu", tcuu);

            } else if (AppConstants.LTT_DI_CHUC_NANG_CHI_TIET_T4.equalsIgnoreCase(strChucNang)) {
                String strSo_ltt = "";
                strSo_ltt = request.getParameter("so_ltt");
                params = new Vector();
                if (strSo_ltt != null &&
                    !STRING_EMPTY.equalsIgnoreCase(strSo_ltt)) {
                    whereClause = " t.id = ? ";
                    param = new Parameter(Long.parseLong(strSo_ltt), true);
                    params.add(param);
                }

                strQuyenLttDi = "";
                strQuyenLttDi = "T4";
                strSoLTT = strSo_ltt;
                bFirstRowM = false;

            } else {
                String strTTdatacdong =
                    "'" + AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG +
                    "', " + "'" + AppConstants.LTT_TRANG_THAI_HUY + "', " +
                    "'" +
                    AppConstants.LTT_TRANG_THAI_DA_GUI_CHO_CHAY_GIAO_DIEN +
                    "', " + "'" +
                    AppConstants.LTT_TRANG_THAI_DA_GUI_GIAO_DIEN_THANH_CONG +
                    "', " + "'" +
                    AppConstants.LTT_TRANG_THAI_DA_GUI_GIAO_DIEN_THAT_BAI +
                    "', " + "'" +
                    AppConstants.LTT_TRANG_THAI_NGAN_HANG_DA_NHAN + "', " +
                    //                    "'" + AppConstants.LTT_TRANG_THAI_CHO_GD_DUYET + "', " +
                    "'" +
                    AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG_XL_THANH_CONG +
                    "', " + "'" +
                    AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG_XL_THAT_BAI +
                    "'";
                //              String FORMAT_NGAY_24H = "DD/MM/YYYY hh24:mi:ss";
                //              String strCutOfTime =
                //                  getThamSoHThong("CUT_OF_TIME", session); //16:30:00
                //              if (strCutOfTime == null || strCutOfTime.isEmpty() ||
                //                  strCutOfTime.equalsIgnoreCase("null")) {
                //                  throw TTSPException.createException("TTSP-1001",
                //                                                      "Khong lay duoc gio CUT OF TIME cua he thong");
                //                  //strCutOfTime = "16:30:00";
                //              }
                //
                //              String strCurrDate = StringUtil.getCurrentDate();
                //              String strPreviousDate = StringUtil.getPreviousNextDate(-1);
                //              //So sanh thoi gian hien tai va cutoftime
                //              if (!LTTCommon.isCurTimeLessThanCutOfTime(strCutOfTime.trim())) {
                //                  strPreviousDate = StringUtil.getCurrentDate();
                //                  strCurrDate = StringUtil.getPreviousNextDate(+1);
                //              }
                //              if (!strCutOfTime.startsWith(" "))
                //                  strCutOfTime = " " + strCutOfTime;
                //              strPreviousDate += strCutOfTime;
                //              strCurrDate += strCutOfTime;
                //******************************************************************************
                //Manhnv-20141103:Sua cho phep 2 vai
                StringBuffer sbWhereClause = new StringBuffer();
                params = new Vector();
                /***********20161101-JHS-MSTER-them dieu kien truy van-BEGIN************/
                sbWhereClause.append(" t.ngay_nhan > SYSDATE - " + strSoNgayTruyVan);
                sbWhereClause.append(" AND t.nhkb_chuyen = ? ");
//              sbWhereClause.append(" t.nhkb_chuyen = ? ");
                /***********20161101-JHS-MSTER-them dieu kien truy van-BEGIN************/
                
                params.add(new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                         true));
                //User co 2 vai KTT&TTV
                if (strUserType.contains(AppConstants.NSD_TTV) &&
                    strUserType.contains(AppConstants.NSD_KTT)) {
                    //                    strTTdatacdong =
                    //                            strTTdatacdong + ",'" + AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI +
                    //                            "'";

                    //Kiem tra KTV tabmis---------------------------------------
                    String strKTVTanbmis = "";
                    KTVTabmisDAO ktvTabmisDAO = new KTVTabmisDAO(conn);
                    strKTVTanbmis =
                            ktvTabmisDAO.getKTVTabmisListByNSD(Long.parseLong(strUserID));
                    if (strKTVTanbmis == null ||
                        STRING_EMPTY.equalsIgnoreCase(strKTVTanbmis) ||
                        "'null'".equalsIgnoreCase(strKTVTanbmis) ||
                        "''".equalsIgnoreCase(strKTVTanbmis)) {
                        strKTVTanbmis = "'0'";
                    }
                    //----------------------------------------------------------

                    sbWhereClause.append("AND ( ((t.ttv_nhan = ? OR t.ktt_duyet = ?) AND t.trang_thai in ('01','02','03','04','05')) ");
                    params.add(new Parameter(strUserID, true));
                    params.add(new Parameter(strUserID, true));
                    sbWhereClause.append("OR ((t.trang_thai IN ('02') AND t.ttv_nhan is null and (t.ktv_chuyen in ( " +
                                         strKTVTanbmis +
                                         ") OR t.ktv_chuyen is null)) ");
                    sbWhereClause.append("or (t.trang_thai ='03' AND t.ktt_duyet is null)) ");
                    sbWhereClause.append("OR t.trang_thai = '01' ");
                    sbWhereClause.append("OR ((t.ktt_duyet = ? OR t.ttv_nhan = ?) ");
                    params.add(new Parameter(strUserID, true));
                    params.add(new Parameter(strUserID, true));
                    //User chi co vai TTV
                } else if (strUserType.contains(AppConstants.NSD_TTV) &&
                           !strUserType.contains(AppConstants.NSD_KTT)) {
                    //                    strTTdatacdong =
                    //                            strTTdatacdong + ",'" + AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI +
                    //                            "'";
                    //Kiem tra KTV tabmis---------------------------------------
                    String strKTVTanbmis = "";
                    KTVTabmisDAO ktvTabmisDAO = new KTVTabmisDAO(conn);
                    strKTVTanbmis =
                            ktvTabmisDAO.getKTVTabmisListByNSD(Long.parseLong(strUserID));
                    if (strKTVTanbmis == null ||
                        STRING_EMPTY.equalsIgnoreCase(strKTVTanbmis) ||
                        "'null'".equalsIgnoreCase(strKTVTanbmis) ||
                        "''".equalsIgnoreCase(strKTVTanbmis)) {
                        strKTVTanbmis = "'0'";
                    }
                    //----------------------------------------------------------

                    sbWhereClause.append("AND ( (t.ttv_nhan = ? AND t.trang_thai in ('01','02','03','04','05')) ");
                    params.add(new Parameter(strUserID, true));
                    sbWhereClause.append("OR ((t.trang_thai IN ('02') AND t.ttv_nhan is null) and (t.ktv_chuyen in ( " +
                                         strKTVTanbmis +
                                         ") OR t.ktv_chuyen is null)) ");
                    sbWhereClause.append("OR t.trang_thai = '01' ");
                    sbWhereClause.append("OR (t.ttv_nhan = ? ");
                    params.add(new Parameter(strUserID, true));
                    //User chi co vai KTT
                } else if (strUserType.contains(AppConstants.NSD_KTT) &&
                           !strUserType.contains(AppConstants.NSD_TTV)) {
                    //                   strTTdatacdong =
                    //                          strTTdatacdong + ",'" + AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI +
                    //                          "'";
                    //menh de where
                    sbWhereClause.append("AND ( (t.ktt_duyet = ? AND t.trang_thai in ('01','03','04','05')) ");
                    params.add(new Parameter(strUserID, true));
                    sbWhereClause.append("OR (t.trang_thai = '03' AND t.ktt_duyet is null) ");
                    sbWhereClause.append("OR (t.ktt_duyet = ? ");
                    params.add(new Parameter(strUserID, true));
                } else if (strUserType.contains(AppConstants.NSD_GD)) {
                    //                    strTTdatacdong =
                    //                            strTTdatacdong + ",'" + AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI +
                    //                            "'";
                    //menh de where
                    sbWhereClause.append("AND ( (t.gd_duyet = ? AND t.trang_thai in ('04','05')) ");
                    params.add(new Parameter(strUserID, true));
                    sbWhereClause.append("OR (t.trang_thai = '05' AND t.gd_duyet is null) ");
                    sbWhereClause.append("OR (t.gd_duyet = ? ");
                    params.add(new Parameter(strUserID, true));
                }
                sbWhereClause.append(" AND ((((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                     " AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                                     " or (t.ngay_gd_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                     " AND t.ngay_gd_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                                     " AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                                     " OR" +
                                     " (((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                     "  AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                                     " or (t.ngay_gd_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                     " AND t.ngay_gd_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                                     " AND SYSDATE >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                                     " AND (t.trang_thai in (" +
                                     strTTdatacdong + ") ) " + ")  ) ");
                whereClause = sbWhereClause.toString();
                //20141103**********************************************************************

                /*                if (strUserType.contains(AppConstants.NSD_TTV) && !strUserType.contains(AppConstants.NSD_KTT)) {

                    strTTdatacdong =
                            strTTdatacdong + ",'" + AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI +
                            "'";
                    params = new Vector();
                    String strKTVTanbmis = "";
                    KTVTabmisDAO ktvTabmisDAO = new KTVTabmisDAO(conn);
                    strKTVTanbmis =
                            ktvTabmisDAO.getKTVTabmisListByNSD(Long.parseLong(strUserID));
                    if (strKTVTanbmis == null ||
                        STRING_EMPTY.equalsIgnoreCase(strKTVTanbmis) ||
                        "'null'".equalsIgnoreCase(strKTVTanbmis) ||
                        "''".equalsIgnoreCase(strKTVTanbmis)) {
                        strKTVTanbmis = "'0'";
                    }
                    whereClause =
                            " (t.ktv_chuyen in ( " + strKTVTanbmis + ") OR t.ktv_chuyen is null) AND ";
                    whereClause =
                            whereClause + " substr(t.id,3,3) = '701' AND ( (t.nhkb_chuyen = ? AND (t.trang_thai=? OR t.trang_thai=?))" +
                            " OR (t.nhkb_chuyen = ? AND t.trang_thai = ? AND t.ttv_nhan is null)" +
                            " OR (t.nhkb_chuyen = ? AND t.ttv_nhan = ?" +
                            " AND ((((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            " AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                            " or (t.ngay_ktt_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            " AND t.ngay_ktt_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                            " AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                            " OR" +
                            " (((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "  AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                            " or (t.ngay_ktt_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            " AND t.ngay_ktt_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                            " AND SYSDATE >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                            " AND (t.trang_thai in (" + strTTdatacdong +
                            ") ) " + ") ) ";

                    param =
                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                          true);
                    params.add(param);
                    //                    param = new Parameter(strUserID, true);
                    //                    params.add(param);
                    param =
                            new Parameter(AppConstants.LTT_TRANG_THAI_KTT_DUYET_DAY_LAI,
                                          true);
                    params.add(param);
                    param =
                            new Parameter(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET,
                                          true);
                    params.add(param);

                    param =
                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                          true);
                    params.add(param);
                    param =
                            new Parameter(AppConstants.LTT_TRANG_THAI_CHO_HOAN_THIEN,
                                          true);
                    params.add(param);

                    param =
                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                          true);
                    params.add(param);
                    param = new Parameter(strUserID, true);
                    params.add(param);

                } else if (strUserType.contains(AppConstants.NSD_KTT)) {

                    whereClause =
                            " substr(t.id,3,3) = '701' AND ((t.nhkb_chuyen = ? AND t.ktt_duyet = ? AND (t.trang_thai=? OR t.trang_thai=? OR t.trang_thai=? OR t.trang_thai=?)) " +
                            " OR (t.nhkb_chuyen = ? AND t.trang_thai = ? AND t.ktt_duyet is null) " +
                            " OR (t.nhkb_chuyen = ? AND t.ktt_duyet = ? " +
                            " AND ((((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            " AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                            " or (t.ngay_ktt_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            " AND t.ngay_ktt_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                            " AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                            " OR" +
                            " (((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "  AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                            " or (t.ngay_ktt_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            " AND t.ngay_ktt_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                            " AND SYSDATE >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                            " AND (t.trang_thai in (" + strTTdatacdong +
                            ") ) ) ) ";
                    params = new Vector();
                    param =
                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                          true);
                    params.add(param);
                    param = new Parameter(Long.parseLong(strUserID), true);
                    params.add(param);
                    param =
                            new Parameter(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET,
                                          true);
                    params.add(param);
                    param =
                            new Parameter(AppConstants.LTT_TRANG_THAI_KTT_DUYET_DAY_LAI,
                                          true);
                    params.add(param);
                    param =
                            new Parameter(AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI,
                                          true);
                    params.add(param);
                    param =
                            new Parameter(AppConstants.LTT_TRANG_THAI_CHO_GD_DUYET,
                                          true);
                    params.add(param);

                    param =
                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                          true);
                    params.add(param);
                    param =
                            new Parameter(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET,
                                          true);
                    params.add(param);

                    param =
                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                          true);
                    params.add(param);
                    param = new Parameter(Long.parseLong(strUserID), true);
                    params.add(param);

                } else if (strUserType.contains(AppConstants.NSD_GD)) {
                    strTTdatacdong =
                            strTTdatacdong + ",'" + AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI +
                            "'";
                    whereClause =
                            " substr(t.id,3,3)='701' AND ((t.nhkb_chuyen = ? AND t.gd_duyet = ? AND (t.trang_thai=? OR t.trang_thai=?)) " +
                            " OR (t.nhkb_chuyen = ? AND t.trang_thai = ? AND t.gd_duyet is null) " +
                            " OR (t.nhkb_chuyen = ? AND t.gd_duyet = ? " +
                            " AND ((((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            " AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                            " or (t.ngay_gd_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            " AND t.ngay_gd_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                            " AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                            " OR" +
                            " (((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "  AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                            " or (t.ngay_gd_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            " AND t.ngay_gd_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                            " AND SYSDATE >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)))" +
                            " AND (t.trang_thai in (" + strTTdatacdong +
                            ") ) ) ) ";

                    params = new Vector();

                    param =
                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                          true);
                    params.add(param);
                    param = new Parameter(Long.parseLong(strUserID), true);
                    params.add(param);
                    param =
                            new Parameter(AppConstants.LTT_TRANG_THAI_CHO_GD_DUYET,
                                          true);
                    params.add(param);
                    param =
                            new Parameter(AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI,
                                          true);
                    params.add(param);

                    param =
                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                          true);
                    params.add(param);
                    param =
                            new Parameter(AppConstants.LTT_TRANG_THAI_CHO_GD_DUYET,
                                          true);
                    params.add(param);

                    param =
                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                          true);
                    params.add(param);
                    param = new Parameter(Long.parseLong(strUserID), true);
                    params.add(param);
                }*/


                if ("FIND_TKIEM_ACTION".equalsIgnoreCase(strChucNang)) {
                    String strSo_tien = "";
                    String strNhkb_nhan = "";
                    String strTrang_thai = "";
                    String strSo_yctt = "";
                    String strTttvnhan = "";
                    strSo_tien = request.getParameter("sotien").trim();
                    strNhkb_nhan =
                            request.getParameter("nhkbchuyennhan").trim();
                    strSo_yctt = request.getParameter("soyctt").trim();
                    strTrang_thai = request.getParameter("trangthai").trim();
                    strTttvnhan = request.getParameter("ttvnhan").trim();
                    if (strTttvnhan != null &&
                        !STRING_EMPTY.equalsIgnoreCase(strTttvnhan)) {
                        if (strUserType.contains(AppConstants.NSD_TTV)) {
                            whereClause += " AND t.ttv_nhan = ? ";
                            param =
                                    new Parameter(Long.parseLong(strUserID), true);
                            params.add(param);
                        } else {
                            UserVO userVO = null;
                            UserDAO userDAO = new UserDAO(conn);
                            String strWhere = " UPPER(a.ma_nsd) like (?)";
                            Vector vParam = new Vector();
                            param =
                                    new Parameter("%" + strTttvnhan.toUpperCase() +
                                                  "%", true);
                            vParam.add(param);
                            Collection colUser =
                                userDAO.getUserList(strWhere, vParam);
                            String strListIdUser = "";
                            Iterator iter = colUser.iterator();
                            while (iter.hasNext()) {
                                userVO = (UserVO)iter.next();
                                if (userVO != null) {
                                    strListIdUser += userVO.getId() + ", ";
                                }
                            }
                            if (!STRING_EMPTY.equalsIgnoreCase(strListIdUser)) {
                                int lengthListId = strListIdUser.length();
                                if (lengthListId > 0 &&
                                    strListIdUser.endsWith(", ")) {
                                    strListIdUser =
                                            strListIdUser.substring(0, lengthListId -
                                                                    2);
                                }
                                whereClause +=
                                        " AND t.ttv_nhan in (" + strListIdUser +
                                        ") ";
                            } else {
                                whereClause += " AND t.ttv_nhan in (0) ";
                            }
                        }
                    }
                    if (strSo_tien != null &&
                        !STRING_EMPTY.equalsIgnoreCase(strSo_tien)) {
                        //                        if (strSo_tien.indexOf(".") != -1)
                        //                            strSo_tien = strSo_tien.replace(".", "");
                        //                        if (strSo_tien.indexOf(",") != -1)
                        //                            strSo_tien = strSo_tien.replace(",", "");
                        //                        if (strSo_tien.indexOf(" ") != -1)
                        //                            strSo_tien = strSo_tien.replace(" ", "");
                        //                        int i = strSo_tien.indexOf(",");
                        //                        if (i != -1) {
                        //                            //strSo_tien = strSo_tien.substring(0, i);
                        //                            strSo_tien = strSo_tien.replace(",", ".");
                        //                        }
                        BigDecimal bdSo_tien =
                            StringUtil.convertCurrencyToNumber(strSo_tien,
                                                               request.getParameter("nt_id"));
                        whereClause += " AND t.tong_sotien = ? ";
                        param = new Parameter(bdSo_tien, true);
                        params.add(param);
                    }
                    if (strNhkb_nhan != null &&
                        !STRING_EMPTY.equalsIgnoreCase(strNhkb_nhan)) {
                        String strWhere = " ma_nh = ? ";
                        Vector vParam = new Vector();
                        vParam.add(new Parameter(strNhkb_nhan, true));
                        DMNHangVO dmnhVO =
                            LTTCommon.getDMNHang(strWhere, vParam, conn);

                        if (dmnhVO != null && dmnhVO.getId() != null) {
                            whereClause += " AND t.nhkb_nhan = ? ";
                            param = new Parameter(dmnhVO.getId(), true);
                            params.add(param);
                        } else {
                            whereClause += " AND t.nhkb_nhan = ? ";
                            param = new Parameter(0, true);
                            params.add(param);
                        }
                    }
                    if (strSo_yctt != null &&
                        !STRING_EMPTY.equalsIgnoreCase(strSo_yctt)) {
                        whereClause += " AND t.so_yctt like (?) ";
                        param = new Parameter("%" + strSo_yctt + "%", true);
                        params.add(param);
                    }
                    //                  && strTrang_thai != ""
                    if (strTrang_thai != null &&
                        !STRING_EMPTY.equals(strTrang_thai) &&
                        !"00".equalsIgnoreCase(strTrang_thai)) {
                        whereClause += " AND t.trang_thai = ? ";
                        param = new Parameter(strTrang_thai, true);
                        params.add(param);
                    }
                }
                //Cho them dieu kien - Neu la tra cuu LTT
                if (AppConstants.LTT_DI_CHUC_NANG_TIM_KIEM.equalsIgnoreCase(strChucNang)) {
                    String strTtv_nhan = "";
                    String strSo_tien = "";
                    String strNhkb_nhan = "";
                    String strTrang_thai = "";
                    String strSo_yctt = "";
                    String strLoaiTien = request.getParameter("nt_id_find");

                    strTtv_nhan =
                            request.getParameter("ttvnhan") != null ? request.getParameter("ttvnhan").trim() :
                            "";
                    strSo_tien = request.getParameter("sotien").trim();

                    strNhkb_nhan =
                            request.getParameter("nhkbchuyennhan").trim();
                    strSo_yctt = request.getParameter("soyctt").trim();
                    strTrang_thai = request.getParameter("trangthai").trim();

                    if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                        null) {
                        strUserID =
                                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
                    }
                    if (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) !=
                        null) {
                        strUserType =
                                session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
                    }

                    if (strTtv_nhan != null &&
                        !STRING_EMPTY.equalsIgnoreCase(strTtv_nhan)) {
                        if (strUserType.contains(AppConstants.NSD_TTV)) {
                            whereClause += " AND t.ttv_nhan = ? ";
                            param =
                                    new Parameter(Long.parseLong(strUserID), true);
                            params.add(param);
                        } else {
                            UserVO userVO = null;
                            UserDAO userDAO = new UserDAO(conn);
                            String strWhere = " UPPER(a.ma_nsd) like (?)";
                            Vector vParam = new Vector();
                            param =
                                    new Parameter("%" + strTtv_nhan.toUpperCase() +
                                                  "%", true);
                            vParam.add(param);
                            Collection colUser =
                                userDAO.getUserList(strWhere, vParam);
                            String strListIdUser = "";
                            Iterator iter = colUser.iterator();
                            while (iter.hasNext()) {
                                userVO = (UserVO)iter.next();
                                if (userVO != null) {
                                    strListIdUser += userVO.getId() + ", ";
                                }
                            }
                            if (!STRING_EMPTY.equalsIgnoreCase(strListIdUser)) {
                                int lengthListId = strListIdUser.length();
                                if (lengthListId > 0 &&
                                    strListIdUser.endsWith(", ")) {
                                    strListIdUser =
                                            strListIdUser.substring(0, lengthListId -
                                                                    2);
                                }
                                whereClause +=
                                        " AND t.ttv_nhan in (" + strListIdUser +
                                        ") ";
                            } else {
                                whereClause += " AND t.ttv_nhan in (0) ";
                            }
                        }
                    }
                    if (strSo_tien != null &&
                        !STRING_EMPTY.equalsIgnoreCase(strSo_tien)) {
                        //                        if (strSo_tien.indexOf(".") != -1)
                        //                            strSo_tien = strSo_tien.replace(".", "");
                        //                        if (strSo_tien.indexOf(",") != -1)
                        //                            strSo_tien = strSo_tien.replace(",", "");
                        //                        if (strSo_tien.indexOf(" ") != -1)
                        //                            strSo_tien = strSo_tien.replace(" ", "");
                        //                        int i = strSo_tien.indexOf(",");
                        //                        if (i != -1) {
                        //                            //strSo_tien = strSo_tien.substring(0, i);
                        //                            strSo_tien = strSo_tien.replace(",", ".");
                        //                        }
                        BigDecimal bdSo_tien =
                            StringUtil.convertCurrencyToNumber(strSo_tien,
                                                               strLoaiTien);
                        if (bdSo_tien.longValue() > 0) {
                            whereClause += " AND t.tong_sotien = ? ";
                            param = new Parameter(bdSo_tien, true);
                            params.add(param);
                        }
                    }
                    if (strLoaiTien != null && !"".equals(strLoaiTien)) {
                        whereClause += " AND e.ma = ? ";
                        param = new Parameter(strLoaiTien, true);
                        params.add(param);
                    }
                    if (strNhkb_nhan != null &&
                        !STRING_EMPTY.equalsIgnoreCase(strNhkb_nhan)) {
                        String strWhere = " ma_nh = ? ";
                        Vector vParam = new Vector();
                        vParam.add(new Parameter(strNhkb_nhan, true));
                        DMNHangVO dmnhVO =
                            LTTCommon.getDMNHang(strWhere, vParam, conn);

                        if (dmnhVO != null && dmnhVO.getId() != null) {
                            whereClause += " AND t.nhkb_nhan = ? ";
                            param = new Parameter(dmnhVO.getId(), true);
                            params.add(param);
                        } else {
                            whereClause += " AND t.nhkb_nhan = ? ";
                            param = new Parameter(0, true);
                            params.add(param);
                        }
                    }
                    if (strSo_yctt != null &&
                        !STRING_EMPTY.equalsIgnoreCase(strSo_yctt)) {
                        whereClause += " AND t.so_yctt like (?) ";
                        param = new Parameter("%" + strSo_yctt + "%", true);
                        params.add(param);
                    }
                    if (strTrang_thai != null &&
                        !"00".equalsIgnoreCase(strTrang_thai)) {
                        whereClause += " AND t.trang_thai = ? ";
                        param = new Parameter(strTrang_thai, true);
                        params.add(param);
                    }
                } else if (AppConstants.LTT_DI_CHUC_NANG_THEM_LAI.equalsIgnoreCase(strChucNang) ||
                           AppConstants.LTT_DI_CHUC_NANG_THEM.equalsIgnoreCase(strChucNang)) {
                    bFirstRowM = false;
                } else if ("RefreshKeepFind".equalsIgnoreCase(strChucNang)) {
                    // sau khi them sua xoa
                }
                //              else if(AppConstants.LTT_DI_CHUC_NANG_THEM_LAI.equalsIgnoreCase(strChucNang) || AppConstants.LTT_DI_CHUC_NANG_SUA_LAI.equalsIgnoreCase(strChucNang)
                //                       || AppConstants.LTT_DI_CHUC_NANG_THEM.equalsIgnoreCase(strChucNang) || AppConstants.LTT_DI_CHUC_NANG_SUA.equalsIgnoreCase(strChucNang)){
                //                  bFirstRowM = false;
                //              }
            }
            //Danh sach LTT master
            if (strUserType.contains(AppConstants.NSD_TTV)) {
                if (!STRING_EMPTY.equals(whereClause)) {
                    //whereClause += " AND (d.rv_domain = 'SP_LTT.TRANG_THAI') ";
                    whereClause +=
                            " AND (d.rv_domain = '" + AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT +
                            "') ";
                } else {
                    whereClause +=
                            " (d.rv_domain = '" + AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT +
                            "') ";
                }
                //                reVal = lTTDAO.getLTTDiList(" t.ngay_nhan > SYSDATE-30 and ("+whereClause+")", params);
                reVal = lTTDAO.getLTTDiList(whereClause, params);
            } else if (strUserType.contains(AppConstants.NSD_KTT)) {
                if (!STRING_EMPTY.equals(whereClause)) {
                    //whereClause += " AND (d.rv_domain = 'SP_LTT.TRANG_THAI') ";
                    whereClause +=
                            " AND (d.rv_domain = '" + AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT +
                            "') ";
                } else {
                    whereClause +=
                            " (d.rv_domain = '" + AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT +
                            "') ";
                }

                //                reVal = lTTDAO.getLTTDiKTTGDList(" t.ngay_nhan > SYSDATE-30 and ("+whereClause+")", params);
                reVal = lTTDAO.getLTTDiKTTGDList(whereClause, params);
            } else if (strUserType.contains(AppConstants.NSD_GD)) {
                if (!STRING_EMPTY.equals(whereClause)) {
                    //whereClause += " AND (d.rv_domain = 'SP_LTT.TRANG_THAI') ";
                    whereClause +=
                            " AND (d.rv_domain = '" + AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT +
                            "') ";
                } else {
                    whereClause +=
                            " (d.rv_domain = '" + AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT +
                            "') ";
                }

                //                reVal = lTTDAO.getLTTDiKTTGDList(" t.ngay_nhan > SYSDATE-30 and ("+whereClause+")", params);
                reVal = lTTDAO.getLTTDiKTTGDList(whereClause, params);
            } else {
                if (!STRING_EMPTY.equals(whereClause)) {
                    //whereClause += " AND (d.rv_domain = 'SP_LTT.TRANG_THAI') ";
                    whereClause +=
                            " AND (d.rv_domain = '" + AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT +
                            "') ";
                } else {
                    whereClause +=
                            " (d.rv_domain = '" + AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT +
                            "') ";
                }
                //                reVal = lTTDAO.getLTTDiKTTGDList(" t.ngay_nhan > SYSDATE-30 and ("+whereClause+")", params);
                reVal = lTTDAO.getLTTDiKTTGDList(whereClause, params);
            }
            request.setAttribute(AppKeys.LTT_LIST_REQUEST_KEY, reVal);
            //            LTTVO lttVO = null;
            if ((strSoLTT == null ||
                 STRING_EMPTY.equalsIgnoreCase(strSoLTT)) &&
                request.getParameter("sid") != null) {
                strSoLTT = request.getParameter("sid");
                if (strSoLTT != null &&
                    !STRING_EMPTY.equalsIgnoreCase(strSoLTT))
                    bFirstRowM = false;
            }

            //end auto select first row master

            //            DMTienTeDAO dmTienTeDAO = new DMTienTeDAO(conn);
            //            Collection listDMTienTe = dmTienTeDAO.getDMTienTeList(null, null);

            //Danh sach tien te
            DMTienTeDAO dmTienTeDAO = new DMTienTeDAO(conn);
            Collection listDMTienTe = null;
            String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            if( kb_id.equals("1") || kb_id.equals("2")){
                listDMTienTe = dmTienTeDAO.getDMTienTeCuaDViList(" and a.tinh_trang = '01' and b.loai_tk in ('02','03') ", null);
            }else{
                listDMTienTe = dmTienTeDAO.getDMTienTeCuaDViList(" and a.tinh_trang = '01' and b.kb_id = " + 
                                                  kb_id + " and b.loai_tk in ('02','03') ", null);
            }
            //Cau hinh Mo tai NHKB phan thong tin nguoi chuyen
            String strCheckAllowEditNHKB =
                getThamSoHThong("CHECK_ALLOW_EDIT_MOTAI_NHKB", session);
            if (strCheckAllowEditNHKB == null ||
                STRING_EMPTY.equals(strCheckAllowEditNHKB)) {
                strCheckAllowEditNHKB = "NO";
            }
            request.setAttribute("CheckAllowEditMoTaiNHKB",
                                 strCheckAllowEditNHKB);

//Manhnv-20150317
            Collection listDMTienTeAll = dmTienTeDAO.getDMTienTeList("",null);            
            request.setAttribute("listDMTienTeAll", listDMTienTeAll);
//manhnv-2050317
       
            request.setAttribute("loai_hanh_dong", strChucNang);  
            request.setAttribute("listDMTienTe", listDMTienTe);
            request.setAttribute("QuyenLttDi", strQuyenLttDi);
            request.setAttribute("SoLTTDi", strSoLTT);
        } catch (TTSPException ttspEx) {
            throw ttspEx;
        } catch (Exception ex) {
            //            ex.printStackTrace();
            throw ex; //TTSPException.createException("TTSP-1000", ex.toString());
        }
    }

    public void coaKhongHopLe(HttpServletRequest request, ActionForm f,
                              String strLastAction, String strLastForm,
                              String strMsg) {
        request.setAttribute("MsgDialogLTTDi", strMsg);
        request.setAttribute("LastAction", strLastAction);
        request.setAttribute("LastForm", strLastForm);
        request.setAttribute("lTTForm", f);
    }


    public ActionForward checkPhanDoanCOA(HttpServletRequest request,
                                          HttpServletResponse response,
                                          ActionMapping mapping) throws Exception {
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        boolean bResult = false;
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            /** Kiem tra form submit va button submit
            * LTT di --> action_FormSubmit = lttDiAdd
            * actionButton = addnew ==> button ghi luc them moi
            * actionButton = update ==> button ghi luc hoan thien
            * LTT nhap thu cong --> action_FormSubmit = lttAdd
            */
            String action_FormSubmit =
                request.getParameter("actionFormSubmit");
            String actionButton = request.getParameter("actionButton");
            String so_yctt = request.getParameter("so_yctt");
            String id_selected =
                request.getParameter("id_selected") != null ? request.getParameter("id_selected") :
                "";
            LTTCommon common = new LTTCommon();
            String strCheckLTT = "";
            // truong hop lenh thanh toan di
            if (action_FormSubmit.equalsIgnoreCase("lttDiAdd")) {
                if (actionButton.equalsIgnoreCase("addnew") ||
                    actionButton.equalsIgnoreCase("update")) {
                    // Check COA
                    strCheckLTT = common.checkCOA(conn, request);
                    if (STRING_EMPTY.equalsIgnoreCase(strCheckLTT)) {
                        bResult = true;
                    }
                    //                    else {
                    //                        request.setAttribute("MsgDialogLTTDi",
                    //                                             AppConstants.LTT_MA_KHONG_HOP_LE);
                    //                    }
                    // kiem tra KH cheo
                    if (bResult) {
                        String strAppID = AppConstants.APP_ID;
                        strAppID = getThamSoHThong("APP_ID", session);
                        String strWSDL =
                            getThamSoHThong("WSDL_KET_HOP_CHEO", session);
                        String checkDMCheo =
                            common.checkDMKHCheo(request, strAppID, strWSDL);
                        if (checkDMCheo != null &&
                            !STRING_EMPTY.equals(checkDMCheo)) {
                            bResult = false;
                            jsonObj.addProperty("khcheo_err", checkDMCheo);
                        }
                    }
                    // END
                    // check SO_YCTT
                    // neu COA dung ==> strCheckCOA = ""
                    // tiep tuc check SO YCTT
                    if (bResult) {
                        if (so_yctt != null) {
                            // check soyctt
                            // bResult = false ==> khong trung
                            // bResult = true ==> co trung
                            bResult =
                                    common.checkExist_So_YCTT(conn, so_yctt, id_selected);
                            if (bResult) {
                                // kiem tra tiep trang thai khac huy
                                //                                bResult =
                                //                                        common.checkApproveYCTT(conn, so_yctt);
                                //                                if (bResult) {
                                strCheckLTT = "TRUNG_SO_YCTT_CANCEL";
                            } else {
                                strCheckLTT = "TRUNG_SO_YCTT";
                                //                                }
                            }
                        }
                    }
                }
            }
            // truong hop lenh thanh toan nhap thu cong
            else if (action_FormSubmit.equalsIgnoreCase("lttAdd")) {
                //Check COA
                strCheckLTT = common.checkCOA(conn, request);
                if (STRING_EMPTY.equalsIgnoreCase(strCheckLTT)) {
                    bResult = true;
                }
                //                else {
                //                    request.setAttribute("MsgDialogLTTDi",
                //                                         AppConstants.LTT_MA_KHONG_HOP_LE);
                //                }
                // kiem tra KH cheo
                if (bResult) {
                    String strAppID = getThamSoHThong("APP_ID", session);
                    String strWSDL =
                        getThamSoHThong("WSDL_KET_HOP_CHEO", session);
                    String checkDMCheo =
                        common.checkDMKHCheo(request, strAppID, strWSDL);
                    if (checkDMCheo != null &&
                        !STRING_EMPTY.equals(checkDMCheo)) {
                        bResult = false;
                        jsonObj.addProperty("khcheo_err", checkDMCheo);
                    }
                }
                // END
                // check SO_YCTT
                // neu COA dung ==> strCheckCOA = ""
                // tiep tuc check SO YCTT
                if (bResult) {
                    if (so_yctt != null) {
                        // check soyctt
                        // bResult = false ==> khong trung
                        // bResult = true ==> co trung
                        bResult =
                                common.checkExist_So_YCTT(conn, so_yctt, id_selected);

                        if (bResult) {
                            // kiem tra tiep trang thai khac huy
                            //                            bResult = common.checkApproveYCTT(conn, so_yctt);
                            //                            if (bResult) {
                            strCheckLTT = "TRUNG_SO_YCTT_CANCEL";
                        }
                        // Neu khong co lenh nao co so yctt , hoac co so yctt va trang thai la huy ==> khong hien thi confirm nua
                        request.setAttribute("MsgDialogLTTDi", strCheckLTT);
                        request.setAttribute("LastAction",
                                             AppConstants.ACTION_ADD);
                    }
                }
                //20150120:Check tai khoan chi ngoai te*************************
                //                if (STRING_EMPTY.equalsIgnoreCase(strCheckLTT)) {
                //                  String strMaNT =
                //                      request.getParameter("ma_nt") != null ? request.getParameter("ma_nt") :
                //                      "VND";
                //                  if(!"VND".equalsIgnoreCase(strMaNT) && !"177".equals(strMaNT)){
                //                    if (request.getParameterValues("tkkt_ma") != null) {
                //                      String[] arrTkkt_ma = (String[])request.getParameterValues("tkkt_ma");
                //                      TTSPUtils ttspUtil = new TTSPUtils(conn);
                //                      if(!"00".equals(ttspUtil.checkTKNSNgoaiTe(arrTkkt_ma[0].toString(), strMaNT))){
                //                        strCheckLTT = "TK_CHI_NGOAI_TE_KO_HOP_LE";
                //                      }
                //                    }
                //                  }
                //                }
                //***************************************************************
            }
            // truong hop lenh thanh toan den
            else if (action_FormSubmit.equalsIgnoreCase("lttDenAdd")) {
                // Check COA
                strCheckLTT = common.checkCOA(conn, request);
                if (STRING_EMPTY.equalsIgnoreCase(strCheckLTT)) {
                    bResult = true;
                }
                //                else {
                //                    request.setAttribute("MsgDialogLTTDi",
                //                                         AppConstants.LTT_MA_KHONG_HOP_LE);
                //                }
                // kiem tra KH cheo
                if (bResult) {
                    //                    String strAppID = getThamSoHThong("APP_ID", session);
                    //                    String strWSDL =
                    //                        getThamSoHThong("WSDL_KET_HOP_CHEO", session);
                    String checkDMCheo = "";
                    //                        common.checkDMKHCheo(request, strAppID, strWSDL);
                    if (checkDMCheo != null &&
                        !STRING_EMPTY.equals(checkDMCheo)) {
                        bResult = false;
                        jsonObj.addProperty("khcheo_err", checkDMCheo);
                    }
                }
                // END
            }

            jsonObj.addProperty("coa_err", strCheckLTT);
            strJson = jsonObj.toString();
            //            if (bResult) {
            //                taoLaiCOA(request);
            //            }
            //            request.setAttribute("ErrorAt", strCheckLTT);
        } catch (Exception e) {
            //            e.printStackTrace();
            jsonObj.addProperty("coa_err", e.getMessage());
            strJson = jsonObj.toString();
            throw TTSPException.createException("TTSP-1000", e.toString());
        } finally {
            close(conn);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }


    public LTTCTietVO getRowInCOAFromCode(int i, Long id, Connection conn,
                                          HashMap h_COA,
                                          String nt_id) throws Exception {
        LTTCTietVO lttCTietVO = null;
        String[] arrTkkt_ma = null, arrMa_quy = null, arrDien_giai =
            null, arrDvsdns_ma = null, arrCapns_ma = null, arrChuongns_ma =
            null, arrNganhkt_ma = null, arrNdkt_ma = null, arrDbhc_ma =
            null, arrCtmt_ma = null, arrMa_nguon = null, arrDu_phong_ma =
            null, arrMa_kb = null, arrSo_tien = null;

        try {
            lttCTietVO = new LTTCTietVO();
            lttCTietVO.setLtt_id(id);
            if (h_COA.get("tkkt_ma") != null)
                arrTkkt_ma = (String[])h_COA.get("tkkt_ma");
            if (!arrTkkt_ma[0].equals(STRING_EMPTY) && arrTkkt_ma[0] != null) {
                arrTkkt_ma = (String[])h_COA.get("tkkt_ma");
                arrMa_quy = (String[])h_COA.get("ma_quy");
                arrDvsdns_ma = (String[])h_COA.get("dvsdns_ma");
                arrCapns_ma = (String[])h_COA.get("capns_ma");
                arrChuongns_ma = (String[])h_COA.get("chuongns_ma");
                arrNganhkt_ma = (String[])h_COA.get("nganhkt_ma");
                arrNdkt_ma = (String[])h_COA.get("ndkt_ma");
                arrDbhc_ma = (String[])h_COA.get("dbhc_ma");
                arrCtmt_ma = (String[])h_COA.get("ctmt_ma");
                arrMa_nguon = (String[])h_COA.get("ma_nguon");
                arrDu_phong_ma = (String[])h_COA.get("du_phong_ma");
                arrMa_kb = (String[])h_COA.get("kb_ma");
                arrDien_giai = (String[])h_COA.get("dien_giai");
                arrSo_tien = (String[])h_COA.get("so_tien");
                if (arrTkkt_ma[i] != "") {
                    lttCTietVO.setTkkt_id(arrTkkt_ma[i].toString());
                }
                if (arrMa_quy[i] != "") {
                    lttCTietVO.setMa_quy_id(arrMa_quy[i].toString());
                }
                if (arrDvsdns_ma[i] != "") {
                    lttCTietVO.setDvsdns_id(arrDvsdns_ma[i].toString());
                }
                if (arrCapns_ma[i] != "") {
                    lttCTietVO.setCapns_id(arrCapns_ma[i].toString());
                }
                if (arrChuongns_ma[i] != "") {
                    lttCTietVO.setChuongns_id(arrChuongns_ma[i].toString());
                }
                if (arrNganhkt_ma[i] != "") {
                    lttCTietVO.setNganhkt_id(arrNganhkt_ma[i].toString());
                }
                if (arrNdkt_ma[i] != "") {
                    lttCTietVO.setNdkt_id(arrNdkt_ma[i].toString());
                }
                if (arrDbhc_ma[i] != "") {

                    lttCTietVO.setDbhc_id(arrDbhc_ma[i].toString());
                }
                if (arrCtmt_ma[i] != "") {

                    lttCTietVO.setCtmt_id(arrCtmt_ma[i].toString());
                }
                if (arrMa_nguon[i] != "") {

                    lttCTietVO.setNguon_id(arrMa_nguon[i].toString());
                }
                if (arrDu_phong_ma[i] != "") {

                    lttCTietVO.setDu_phong_id(arrDu_phong_ma[i].toString());
                }
                if (arrDien_giai[i] != "") {
                    lttCTietVO.setDien_giai(arrDien_giai[i]);
                }
                if (arrSo_tien[i] != "") {
                    String strSoTien = arrSo_tien[i].trim();
                    //                    if (strSoTien.indexOf(" ") != -1)
                    //                        strSoTien = strSoTien.replace(" ", "");
                    //                    if (strSoTien.indexOf(".") != -1)
                    //                        strSoTien = strSoTien.replace(".", "");
                    //                    if (strSoTien.indexOf(",") != -1)
                    //                        strSoTien = strSoTien.replace(",", "");
                    //                    lttCTietVO.setSo_tien(new BigDecimal(strSoTien));
                    lttCTietVO.setSo_tien(StringUtil.convertCurrencyToNumber(strSoTien,
                                                                             nt_id));
                }
                if (arrMa_kb[i] != "") {
                    lttCTietVO.setKb_id(arrMa_kb[i].toString());
                }
                int sott_dong = i + 1;
                lttCTietVO.setSott_dong(Long.parseLong(sott_dong + ""));
            }
        } catch (Exception ex) {
            throw TTSPException.createException("TTSP-1010", ex.toString());
        }

        return lttCTietVO;
    }

    public ActionForward fillTenFromCOA(HttpServletRequest request,
                                        HttpServletResponse response,
                                        ActionMapping mapping) throws Exception {

        String strJson = "";
        Connection conn = null;
        String whereClause = "";
        Vector params = null;
        DMDonViSdnsVO dvsdnsVO = null;
        DMDonViSdnsDAO dao = null;
        try {
            conn = getConnection(request);
            dao = new DMDonViSdnsDAO(conn);
            whereClause = " a.ma=?";
            params = new Vector();

            String strMaDVSDNS =
                request.getParameter("ma_dvns") != null ? request.getParameter("ma_dvns") :
                "";
            if (strMaDVSDNS != null && !STRING_EMPTY.equals(strMaDVSDNS)) {
                params.add(new Parameter(strMaDVSDNS.trim(), true));
                dvsdnsVO = dao.getDMDonViSdns(whereClause, params);
                Type listType = new TypeToken<DMDonViSdnsVO>() {
                }.getType();
                strJson = new Gson().toJson(dvsdnsVO, listType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        }
        if (!response.isCommitted())
            return mapping.findForward(forward);
        else
            return null;
    }

    public void getTrangThaiLTTList(Connection conn,
                                    HttpServletRequest request) throws Exception {
        try {
            Collection colTrangThai = null;
            MaThamChieuDAO thamchieuDAO = new MaThamChieuDAO(conn);
            String strWhere = " a.rv_domain = ? ";
            Vector vParam = new Vector();
            vParam.add(new Parameter(AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT,
                                     true));
            colTrangThai = thamchieuDAO.getMaThamChieuList(strWhere, vParam);

            request.setAttribute("colTrangThai", colTrangThai);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

//HUNGBM
    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        String strType_LTT = "LTTDI";
        Connection conn = null;
        String reportName = null;
        InputStream reportStream = null;
        LTTForm lttForm = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = null;
        DmTSoHToanDAO tsoDAO = null;
        DmTSoHToanVO tsoVO = null;
        Vector vParam = null;
        StringBuffer sbSubHTML = new StringBuffer();
        String nt_id = request.getParameter("rpt_nt_id");
        String so_tien_bang_chu = "";
        //20170417 - BEGIN
        String in_gioi_thieu=   request.getParameter("in_gioi_thieu");  
        //20170417 - END
        try {
            conn = getConnection(request);
            lttForm = (LTTForm)form;
            LTTDAO lttDAO = new LTTDAO(conn);
            tsoDAO = new DmTSoHToanDAO(conn);
            tsoVO = new DmTSoHToanVO();
            sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" + lttForm.getId() + "\" id=\"id\"></input>");
            String whereClause = " t.id = ? ";
            vParam = new Vector();
            vParam.add(new Parameter(lttForm.getId(), true));
            LTTVO lttVO = lttDAO.getLTTDi(whereClause, vParam);
            if (lttVO != null) {
                // kiem tra trang thai in
                // = 0 hoac = null ==> in lan dau tien
                parameterMap = new HashMap();
                if (lttVO.getTt_in() == null || 0 == lttVO.getTt_in()) {
                    lttVO.setTt_in(new Long("1"));
                    parameterMap.put("TT_IN", 1);
                    if (lttDAO.update(lttVO) == 1) {
                        conn.commit();
                    }
                } else {
                    parameterMap.put("TT_IN", 0);
                }
              //20170417 - BEGIN
                if (in_gioi_thieu.equalsIgnoreCase("ingioithieu")){
                  HttpSession session = request.getSession();
                  String ttin_kh_nhan = "";
                  
                  if (!"".equalsIgnoreCase(lttForm.getTtin_kh_nhan()) || lttForm.getTtin_kh_nhan() == null){
                    ttin_kh_nhan =    lttForm.getTtin_kh_nhan();           
                  }                  
                  String don_vi = session.getAttribute(AppConstants.APP_KB_NAME_SESSION).toString().replaceAll("KBNN","");
                  String so_tien = "";
                  String aa = lttForm.getSo_tien();
                       if(aa.indexOf(".")>0){
                          String a1 = aa.substring(0,aa.indexOf("."));
                          String a2 = aa.substring(aa.indexOf(".")+1);
                          so_tien = a1.replaceAll(",",".")+","+a2;
                       }else so_tien = lttForm.getSo_tien().replaceAll(",",".");
                  reportName = "rptGioiThieu";
                  parameterMap.put("KBNN",don_vi);// tinh/ huyen
                  parameterMap.put("TEN_NHKB_NHAN",lttForm.getTen_nhkb_nhan_cm()); // ten_nhkb_nhan_cm
                  parameterMap.put("TEN_NHKB_CHUYEN",lttForm.getTen_nhkb_chuyen_cm()); // ten_nhkb_chuyen_cm
                  parameterMap.put("TEN_NGUOI_DAI_DIEN",lttForm.getTen_tk_nhan()); //Ten_tk_nhan
                  parameterMap.put("TTIN_KH_NHAN",ttin_kh_nhan); // ttin_kh_nhan
                  parameterMap.put("TO_CHUC_NHAN_TIEN",lttForm.getTen_tk_chuyen()); //ten_tk_nhan
                  parameterMap.put("NH_DUOC_CU_DEN",lttForm.getTen_nhkb_nhan_cm()); //ten_nhkb_nhan_cm
                  parameterMap.put("SO_LTT",lttForm.getId()); //id
                  parameterMap.put("NGAY_TT",lttForm.getNgay_tt()); //ngay_tt                               
                  parameterMap.put("SO_TIEN", lttForm.getSo_tien());   
                  parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                  if (nt_id.equals("177")) {
                    parameterMap.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_VND);
                  } else {
                    parameterMap.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_NT); //thuongdt-20170518 sua format tien
                  }
                }else {
                  reportName = lttVO.getTtloai_lenh().equals("03") ? "ChungTuLenhThanhToanDi_MuaNgoaiTe" : "ChungTuLenhThanhToanDi";
                }         
              //20170417 - END
                reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY + "/" + reportName + ".jasper");
                if (reportStream == null) {
                    throw (new TTSPException().createException("TTSP-9999", "Kh&#244;ng t&#236;m th&#7845;y file b&#225;o c&#225;o!"));
                }
                String strPathFont = AppConstants.FONT_FOR_REPORT;
                parameterMap.put("NO_TK", "");
                parameterMap.put("CO_TK", "");
                parameterMap.put("P_ID", lttForm.getId());
                parameterMap.put("Type_LTT", strType_LTT);
                if (nt_id.equals("177")) {
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                } else {
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
                }
                jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ? 
                    "" : request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "lttDiRptAction.do";
                String strParameter = "";

                ReportUtility rpUtilites = new ReportUtility();
                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, reportName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
            } else {
                throw (new TTSPException().createException("TTSP-9999", "Kh&#244;ng t&#236;m th&#7845;y b&#7843;n ghi n&#224;o!")); 
            }
        } catch (Exception e) {
            throw (new TTSPException()).createException("TTSP-9999", "In b&#225;o c&#225;o l&#7879;nh thanh to&#225;n : " + e);
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        JsonObject jsonObj = new JsonObject();
        String strJson = null;
        String strMsgEx = "";
        Connection conn = null;
        String strResult = "";
        try {
            conn = getConnection(request);
            TTSPUtils utils = new TTSPUtils(conn);
            HttpSession session = request.getSession();
            String strID = request.getParameter("id");
            if (strID != null && !STRING_EMPTY.equals(strID)) {
                // session
                String strMaNHKB =
                    session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) !=
                    null ?
                    session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString() :
                    "";
                // lay trong lenh
                LTTDAO lttDAO = new LTTDAO(conn);
                String whereClause = " and a.id=? ";
                Vector params = new Vector();
                params.add(new Parameter(strID, true));
                LTTVO lttdi = lttDAO.getLTTForHachToan(whereClause, params);

                //ManhNV-20141030:Sua cho phep nhieu roll, check ko dc ky lenh cua NSD nhap
                long lTtvNhan =
                    lttdi.getTtv_nhan() == null ? 0 : lttdi.getTtv_nhan().longValue();
                //                long lKttDuyet =
                //                    lttdi.getKtt_duyet() == null ? 0 : lttdi.getKtt_duyet().longValue();
                long lUserID =
                    Long.parseLong(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                if (lTtvNhan == lUserID) {
                    strResult = "KHONG_DUOC_DUYET_CUNG_TAY";
                    //                } else if (lKttDuyet != 0 && lKttDuyet != lUserID) {
                    //                    strResult = "KHONG_DUOC_DUYET_LTT_CUA_KTT_KHAC";
                } else {
                    //ManhNV-20141030------------------------------------------------------------

                    String strMaNH = lttdi.getMa_nhkb_nhan();
                    String strNgay = StringUtil.getCurrentDate();
                    Date strNgayCutoftime =
                        utils.getNgayGioCutOfTime(strNgay, strMaNHKB, strMaNH);
                    Date NgayHienTai = new Date();
                    //ManhNV-20170119-begin-------------------------------------
                    //Sua: ky lenh = COT van di NH 
//                  if (strNgayCutoftime.before(NgayHienTai)) { //strNgayCutoftime<NgayHienTai
                    if (NgayHienTai.after(strNgayCutoftime)){
                    //ManhNV-20170119-end-------------------------------------
                        // cutoftime lon hon ngay gio hien tai ==> trong ngay
                        if (lttdi.getNgay_tt() <= NgayHienTai.getTime()) {
                            strResult = "PASSTIME";
                        } else {
                            strResult = "PASSTIMEUPDATE";
                        }
                    } else {
                        if (lttdi.getNgay_tt() < NgayHienTai.getTime()) {
                            strResult = "INTIMEUPDATE";
                        } else {
                            strResult = "INTIME";
                        }
                    }
                }
            } else {
                strMsgEx =
                        "L&#7895;i h&#7879; th&#7889;ng kh&#244;ng l&#7845;y &#273;&#432;&#7907;c th&#244;ng tin l&#7879;nh!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            strMsgEx = e.getMessage();
        } finally {
            close(conn);
            jsonObj.addProperty("resultValue", strResult);
            jsonObj.addProperty("exception_message", strMsgEx);
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    // truyen lai

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        JsonObject jsonObj = new JsonObject();
        String strJson = null;
        Connection conn = null;
        LTTDAO lttDAO = null;
        int result = 0;

//        QueueManager queueManager = null;//DOUBLE_20161122

        try {
            String lttID =
                request.getParameter("idTL") != null ? request.getParameter("idTL") :
                "";
            if (STRING_EMPTY.equals(lttID)) {
                jsonObj.addProperty("exception_message",
                                    "Kh&#244;ng l&#7845;y &#273;&#432;&#7907;c d&#7919; li&#7879;u b&#7843;n ghi");
            } else {
                conn = getConnection(request);

                TTSPUtils ttspUtil = new TTSPUtils(conn);
                lttDAO = new LTTDAO(conn);
                HttpSession session = request.getSession();
                //Khoi tao QueueManager
//                queueManager = new QueueManager(getThamSoHThong(session));//DOUBLE_20161122

                String strMsgId = null;
                // kiem tra dieu kien
                String whereClause = " t.id=? ";
                Vector params = new Vector();
                params.add(new Parameter(lttID, true));
                LTTVO lttCheckTL = lttDAO.getLTTDi(whereClause, params);
                //kiem tra neu NGAY_GD_DUYET, NGAY_KTT_DUYET) > 1/2 th?i gian tr?
                String strTGTre =
                    getThamSoHThong("CUT_OF_TIME_TGIAN_TRE", session);
                long ONE_MINUTE_IN_MILLIS = 60000;
                Long tgTreCheckReSend = new Long(strTGTre);
                Date dNgayGioTre =
                    ttspUtil.getNgayGioTGTre(StringUtil.getCurrentDate(),
                                             lttCheckTL.getMa_nhkb_chuyen(),
                                             lttCheckTL.getMa_nhkb_nhan());
                Long lTGTre =
                    dNgayGioTre != null ? dNgayGioTre.getTime() : new Long(0);
                Long lNgay_gui_nh = null;
                Long lNow = new Date().getTime();
                String strNgay_gui_nh =
                    lttCheckTL.getNgay_gui_nh() != null ? lttCheckTL.getNgay_gui_nh() :
                    (lttCheckTL.getNgay_gd_duyet() != null ?
                     lttCheckTL.getNgay_gd_duyet() :
                     lttCheckTL.getNgay_ktt_duyet());
                if (strNgay_gui_nh != null) {
                    lNgay_gui_nh =
                            strNgay_gui_nh != null ? StringUtil.StringToDate(strNgay_gui_nh,
                                                                             "dd/MM/yyyy HH:mm").getTime() :
                            new Long(0);
                }
                String strAccept_Resent =
                    StringUtil.DateToString(new Date(lNgay_gui_nh +
                                                     ((tgTreCheckReSend / 2))),
                                            "dd/MM/yyyy HH:mm");
                Long timeCheck =
                    (lNow / ONE_MINUTE_IN_MILLIS - lNgay_gui_nh / ONE_MINUTE_IN_MILLIS);
                Long lCheck = timeCheck - (tgTreCheckReSend / 2);
                if (lNow > lTGTre) {
                    jsonObj.addProperty("success",
                                        "g&#7917;i message th&#7845;t b&#7841;i v&#236; &#273;&#227; qu&#225; th&#7901;i gian tr&#7877; m&#224; h&#7879; th&#7889;ng cho ph&#233;p!");
                } else {
                    if (lCheck >= 0) {
                        //
                        String strNguoiDuyet =
                            lttCheckTL.getMa_gd_chuyen() != null ?
                            lttCheckTL.getMa_gd_chuyen() :
                            lttCheckTL.getMa_ktt_chuyen();
                        String strNgayDuyet =
                            lttCheckTL.getNgay_gd_duyet() != null ?
                            lttCheckTL.getNgay_gd_duyet() :
                            lttCheckTL.getNgay_ktt_duyet();

                        String strHachToanTheoNgayKSNH = "Y";
                        if (session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION) !=
                            null) {
                            strHachToanTheoNgayKSNH =
                                    session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION).toString();
                        }
                        //Send LTT
//                        DOUBLE_20161122-BEGIN
//                        queueManager.setQueueManager();
//                        SendLTToan sendLTT =
//                            new SendLTToan(conn, queueManager);
//                        DOUBLE_20161122-END
                        BuildMsgLTT sendLTT = new BuildMsgLTT(conn);//DOUBLE_20161122
                        strMsgId =
                                sendLTT.sendMessage(lttID, session.getAttribute(AppConstants.APP_KB_CODE_SESSION) ==
                                                           null ? "" :
                                                           session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(),
                                                    strNguoiDuyet,
                                                    strNgayDuyet.replace("/",
                                                                         "-"),
                                                    strHachToanTheoNgayKSNH,
                                                    session, "");
                        if (strMsgId == null) {
                            //gui loi
                            jsonObj.addProperty("success",
                                                "g&#7917;i message l&#7895;i!");
                        } else {
                            //COMMIT MQ
//                            queueManager.commitMQ();//DOUBLE_20161122
                            // gui thanh cong
                            LTTVO lTTVO = new LTTVO();
                            lTTVO.setId(new Long(lttID));
                            // update ngay gui nh
                            lTTVO.setNgay_gui_nh(StringUtil.getCurrentDate());
                            result = lttDAO.update(lTTVO);
                            // update thanh cong
                            if (result > 0) {
                                conn.commit();
                                jsonObj.addProperty("success",
                                                    AppConstants.SUCCESS);
                            }
                        }
                    } else {
                        jsonObj.addProperty("success",
                                            "B&#7841;n ch&#432;a &#273;&#432;&#7907;c ph&#233;p truy&#7873;n l&#7841;i, b&#7841;n c&#243; th&#7875; truy&#7873;n l&#7841;i v&#224;o l&#250;c :" +
                                            strAccept_Resent);
                    }
                }
            }
        } catch (Exception e) {
            //ROLLBACK MQ
//            queueManager.rollbackMQ();//DOUBLE_20161122
            jsonObj.addProperty("exception_message", e.getMessage());
        } finally {
            //Close DB & MQ
            close(conn);
//            queueManager.disconnectMQ();//DOUBLE_20161122

            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

}
