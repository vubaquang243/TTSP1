package com.seatech.ttsp.ltt.action;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
import com.seatech.ttsp.dmtiente.DMTienTeVO;
import com.seatech.ttsp.dvgiaodich.DvGiaoDichDAO;
import com.seatech.ttsp.dvgiaodich.DvGiaoDichVO;
import com.seatech.ttsp.ltt.COACommon;
import com.seatech.ttsp.ltt.COAVO;
import com.seatech.ttsp.ltt.LTTCTietDAO;
import com.seatech.ttsp.ltt.LTTCTietVO;
import com.seatech.ttsp.ltt.LTTCommon;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.ltt.form.LTTForm;
import com.seatech.ttsp.proxy.dm.DMCheo;
//import com.seatech.ttsp.proxy.giaodien.SendLTToan;
//import com.seatech.ttsp.proxy.giaodien.mq.QueueManager;
import com.seatech.ttsp.proxy.giaodien.BuildMsgLTT;
import com.seatech.ttsp.proxy.giaodien.SendLTToan;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserVO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.math.BigDecimal;

import java.sql.Connection;

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
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @modify: thuongdt
 * @modify-date: 15-09-2017
 * @see: sua code dap ung tra cuu nhanh cho phep tra cuu tat ca cac loai tien
 * @find: 20170915
 **/
public class LTTDenAction extends AppAction {
    private String forward = AppConstants.SUCCESS;
    private static String STRING_EMPTY = "";

    public LTTDenAction() {
        super();
    }
    /**
     * - ManhNV
     * - 22/11/2016
     * - Sua truyen dien 2 lan
     * - key tim kiem: DOUBLE_20161122
     ***/
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        //response danh sach LTT den
        String strAction = "";
        strAction =
                request.getParameter("action") != null ? request.getParameter("action") :
                "";
        if (!strAction.equalsIgnoreCase("VIEW_LTTDenT4") &&
            !strAction.equalsIgnoreCase("VIEW_LTTDen")) {
            if (!checkPermissionOnFunction(request, AppConstants.LTT_DEN)) {
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
            String whereClauseTongSo = "AND a.nhkb_nhan =? AND a.nt_id = 177 ";
            String strNHKB_Tong =
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION) !=
                null ?
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString() :
                "";
            Vector param_Tong = new Vector();
            String strDate = StringUtil.getPreviousNextDate(-1);
            param_Tong.add(new Parameter(strDate, true));
            param_Tong.add(new Parameter(strNHKB_Tong, true));
            LTTDAO lttDAO = new LTTDAO(conn);
            String strTongSoMon =
                (!strAction.equalsIgnoreCase("VIEW_LTTDenT4") &&
                 !strAction.equalsIgnoreCase("VIEW_LTTDen")) ?
                lttDAO.getTongSoMonLTTDen(whereClauseTongSo, param_Tong) : "";
            String strTongSoTien =
                (!strAction.equalsIgnoreCase("VIEW_LTTDenT4") &&
                 !strAction.equalsIgnoreCase("VIEW_LTTDen")) ?
                lttDAO.getTongSoTienLTTDen(whereClauseTongSo, param_Tong) : "";
            f.setTong_so_tien(strTongSoTien);
            f.setTong_so_mon(strTongSoMon);
            // end
            //            if (f.getTtvnhan() != "" || f.getSoyctt() != "" ||
            //                f.getNhkbchuyennhan() != "" || f.getTrangthai() != "" ||
            //                f.getSotien() != "") {
            //                if (strAction == null || strAction == "")
            //                    strAction = "FIND_TKIEM_ACTION";
            //            }
            //end
            if (!strAction.equalsIgnoreCase("VIEW_LTTDenT4") &&
                !strAction.equalsIgnoreCase("VIEW_LTTDen")) {
                //Lay ra danh sach Trang thai cho viec tim kiem
                getTrangThaiLTTList(conn, request);

                //Lay ra danh sach TKTN Dac biet, An ninh
                String strSpecialTKTN =
                    getThamSoHThong("MA_TKTN_DAC_BIET", session);
                strSpecialTKTN = LTTCommon.getStringAsArray(strSpecialTKTN);
                request.setAttribute("StrSpecialTKTN", strSpecialTKTN);

                String strTKTNAnNinh =
                    getThamSoHThong("MA_TKTN_AN_NINH", session);
                strTKTNAnNinh = LTTCommon.getStringAsArray(strTKTNAnNinh);
                request.setAttribute("StrTKTNAnNinh", strTKTNAnNinh);
                // kiem tra ket noi
                //checkNgatKetNoi  thi ko dc nhap lenh, ko dc hoan thien
                String strKBacID = "";
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
                request.setAttribute("colDMNH", listDMNH);
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
            Collection colLttDenMaster = null;
            if ("REFRESH".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session, "");
                colLttDenMaster =
                        (Collection)request.getAttribute(AppKeys.LTT_LIST_REQUEST_KEY);
                request.removeAttribute(AppKeys.LTT_LIST_REQUEST_KEY);

                Type listType = new TypeToken<Collection<LTTVO>>() {
                }.getType();
                String strJson = new Gson().toJson(colLttDenMaster, listType);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
            } else if ("FIND_SoYCTT".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session,
                          AppConstants.LTT_DEN_CHUC_NANG_TIM_KIEM);
                colLttDenMaster =
                        (Collection)request.getAttribute(AppKeys.LTT_LIST_REQUEST_KEY);
                request.removeAttribute(AppKeys.LTT_LIST_REQUEST_KEY);

                Type listType = new TypeToken<Collection<LTTVO>>() {
                }.getType();
                String strJson = new Gson().toJson(colLttDenMaster, listType);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
            } else if ("VIEW_LTTDen".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session,
                          AppConstants.LTT_DEN_CHUC_NANG_CHI_TIET);
            } else if ("VIEW_LTTDenT4".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session,
                          AppConstants.LTT_DI_CHUC_NANG_CHI_TIET_T4);
            } else if ("FIND_TKIEM_ACTION".equalsIgnoreCase(strAction)) {
                lTTMaster(conn, request, session, "FIND_TKIEM_ACTION");
            } else {
                lTTMaster(conn, request, session, "");
            }
        } catch (Exception e) {
//            e.printStackTrace();
//            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
//            PrintWriter out = response.getWriter();
//            out.println(e.getMessage());
//            out.flush();
//            out.close();
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
            //throw TTSPException.createException("TTSP-1011", e.toString());
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

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request,
                                       AppConstants.LTT_DEN_CHUC_NANG_SUA)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        forward = AppConstants.SUCCESS;
        Connection conn = null;
        Vector params = null;
        Parameter param = null;
        String whereClause = "";
        String strMsg = "";
        String strAppID = AppConstants.APP_ID;
        JsonObject jsonObj = null;
        String strJson = null;
        StringBuffer strLog = new StringBuffer();
        //ManhNV-20141021
        String strID = null;
        TTSPUtils ttspUtil = null; 
        String strSetLock = "";
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
            String strQuyenLttDen = "";
            if (listCNang != null) {
                Iterator iter = listCNang.iterator();
                ChucNangVO cnangVO = null;
                while (iter.hasNext()) {
                    cnangVO = (ChucNangVO)iter.next();
                    strQuyenLttDen += cnangVO.getLoai_cnang();
                }
            }
            //checkNgatKetNoi = true, thi ko dc nhap lenh, ko dc hoan thien
            String strKBacID = "";
            if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) != null) {
                strKBacID =
                        session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }
            ttspUtil = new TTSPUtils(conn);
            if (ttspUtil.checkNgatKetNoi(strKBacID)) {
                //Lay ra danh sach LttDen (master) cho nguoi dung bi Ngat ket noi, danh sach Ngoai te
                lTTMaster(conn, request, session, "");

                strMsg = AppConstants.LTT_NGAT_KET_NOI;
                jsonObj.addProperty("strMsgEx", strMsg);
                return mapping.findForward(AppConstants.SUCCESS);
            } else if (strQuyenLttDen.indexOf("S") == -1) {
                //Lay ra danh sach LttDen, danh sach Ngoai te
                lTTMaster(conn, request, session, "");
                strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
                jsonObj.addProperty("strMsgEx", strMsg);
                return mapping.findForward(AppConstants.SUCCESS);
            } else {
                strLog.append("\n-------------------------------------------------\n" +
                        request.getParameter("idHT") + " at " +
                        StringUtil.DateToString(new Date(),
                                                "dd/MM/yyyy HH:mm:ss"));
                LTTDAO lTTDAO = new LTTDAO(conn);
                //                LTTForm lttForm = (LTTForm)form;
                // truong hop ko co COA
                String bCheckLTTDenCOA =
                    request.getParameter("checkLTT") != null ?
                    request.getParameter("checkLTT") : "";
                boolean bCheck = true;
                if (bCheckLTTDenCOA.equalsIgnoreCase("false")) {
                    bCheck = false;
                }
                //Check trong tung phan doan COA
                strLog.append("\n - request : ");
                //id=1420110300002228&trang_thai=02&tong_sotien=65.331.691.640
                strLog.append(request.getParameter("idHT"));
                strLog.append(" | " + request.getParameter("trang_thai"));
                strLog.append(" | " + request.getParameter("tong_sotien"));
                COAVO coaVOFRequest = getArraysInCOA(request);
                strLog.append("\n - COA So_tien :" +
                              request.getParameterValues("so_tien")[0]);
                strLog.append(" |  Ma_kb : " +
                              request.getParameterValues("kb_ma")[0]);
                strLog.append("|  Tkkt_ma :" +
                              request.getParameterValues("tkkt_ma")[0]);

                String strLoai_hach_toan =
                    request.getParameter("loai_hach_toan");
                COACommon coaCommon = new COACommon();
                if (strLoai_hach_toan.equals("01")) {
                    if (bCheck) {
                        String strCheckCOA =
                            coaCommon.checkPhanDoanCOA(coaVOFRequest,
                                                       session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(),
                                                       conn);
                        request.setAttribute("ErrorAt", strCheckCOA);
                        if (strCheckCOA != null &&
                            !STRING_EMPTY.equals(strCheckCOA)) {
                            jsonObj.addProperty("strMsgEx",
                                                strCheckCOA + " kh&#244;ng t&#7891;n t&#7841;i ho&#7863;c sai &#273;&#7883;nh d&#7841;ng, &#273;&#7873; ngh&#7883; ki&#7875;m tra l&#7841;i.");
                            return mapping.findForward(AppConstants.SUCCESS);
                        }
                    }
                }

                LTTVO lTTVO = new LTTVO();

                if (strUserID != null &&
                    !STRING_EMPTY.equalsIgnoreCase(strUserID))
                    lTTVO.setTtv_nhan(Long.parseLong(strUserID));
                lTTVO.setNgay_hoan_thien("SYSDATE");
                lTTVO.setTong_sotien(null);

                lTTVO.setLoai_hach_toan(strLoai_hach_toan);
                if (lTTVO.getNt_id() != null) {
                    if (lTTVO.getNt_id().compareTo(Long.parseLong("0")) == 0) {
                        lTTVO.setNt_id(null);
                    }
                }

                //Kiem tra LTT co cho phep update (trang thai # trang thai lay tu form la khong dc update. return listLttDenAdd, alert message)
                //So sanh trang thai truoc khi Hoan Thien voi trang thai lay duoc tu form
                Long nSoLTT = new Long(0);
                String strStatusNewest = "";
                String strTrangThai = "";

                LTTVO lttVOForUpdate = new LTTVO();
                strID = request.getParameter("idHT");
                String strTrang_thai = request.getParameter("trang_thai");
                nSoLTT = Long.parseLong(strID);
                strTrangThai = strTrang_thai;
                whereClause = " t.id = ? ";
                param = new Parameter(nSoLTT.toString(), true);
                params = new Vector();
                params.add(param);
//******************************************************************************
//Manhnv-20141021: Sua bo for update
//Kiem tra thay the for update
                strSetLock = ttspUtil.setLock(strID, "UPDATE-LTT", new Long(strUserID)); 
                if(!"SUCCESS".equalsIgnoreCase(strSetLock)){
                  throw new Exception("LTT &#273;ang &#273;&#432;&#7907;c x&#7917; l&#253; b&#7903;i NSD kh&#225;c("+strSetLock+"), h&#227;y ch&#7885;n LTT kh&#225;c &#273;&#7875; ti&#7871;p t&#7909;c.");    
                }
//                lttVOForUpdate = lTTDAO.getLTTVOForUpdate(whereClause, params);
                lttVOForUpdate = lTTDAO.getLTTVO(whereClause, params);
//******************************************************************************
//Manhnv-20141103: Sua kho cho phep lam 2 vai tren 1 LTT************************
                long lKTTDuyet = lttVOForUpdate.getKtt_duyet()==null?0:lttVOForUpdate.getKtt_duyet().longValue();
                if(Long.parseLong(strUserID) == lKTTDuyet){
                  throw new Exception("Kh&#244;ng ho&#224;n thi&#7879;n &#273;&#432;&#7907;c l&#7879;nh do m&#236;nh &#273;&#7849;y l&#7841;i &#7903; vai KTT");
                }
//******************************************************************************
                strStatusNewest = lttVOForUpdate.getTrang_thai();

                if (!strStatusNewest.equalsIgnoreCase(strTrangThai)) {
                    //LTT da dc hoan thien boi nguoi khac
                    strMsg = AppConstants.LTT_TRANG_THAI_DA_THAY_DOI;
                    jsonObj.addProperty("strMsgEx", strMsg);
                    return mapping.findForward(AppConstants.SUCCESS);
                }
                //                // kiem tra tong so tien - so tien COA
                BigDecimal lSo_Tien_COA = new BigDecimal(0);
                BigDecimal tong_so_tien = lttVOForUpdate.getTong_sotien();
                for (int i = 0; i < coaVOFRequest.getArrTkkt_ma().length;
                     i++) {
                    String strTongSoTienCTiet =
                        coaVOFRequest.getArrSo_tien()[i].trim();
//                    if (strTongSoTienCTiet.indexOf(" ") != -1)
//                        strTongSoTienCTiet =
//                                strTongSoTienCTiet.replace(" ", "");
//                    if (strTongSoTienCTiet.indexOf(".") != -1)
//                        strTongSoTienCTiet =
//                                strTongSoTienCTiet.replace(".", "");
//                    if (strTongSoTienCTiet.indexOf(",") != -1)
//                        strTongSoTienCTiet =
//                                strTongSoTienCTiet.replace(",", "");
                    if(strTongSoTienCTiet == null || STRING_EMPTY.equals(strTongSoTienCTiet)){
                      strTongSoTienCTiet = "0";
                    }                  
                  lSo_Tien_COA = lSo_Tien_COA.add(StringUtil.convertCurrencyToNumber(strTongSoTienCTiet, lttVOForUpdate.getNt_id().toString()));
//                            new Long((strTongSoTienCTiet != null && !STRING_EMPTY.equals(strTongSoTienCTiet)) ?
//                                     strTongSoTienCTiet : "0");
                }
                strLog.append("\n\t - so tien : " + tong_so_tien + "_ " +
                              lSo_Tien_COA);
                strLog.append("| kho bac : " +
                              session.getAttribute(AppConstants.APP_KB_CODE_SESSION) +
                              "_" + coaVOFRequest.getArrMa_kb()[0]);
                if (tong_so_tien.compareTo(lSo_Tien_COA) != 0) {
                    jsonObj.addProperty("strMsgEx", "ErrorTien");
                    return mapping.findForward(AppConstants.SUCCESS);
                }
                if (!session.getAttribute(AppConstants.APP_KB_CODE_SESSION).equals(coaVOFRequest.getArrMa_kb()[0])) {
                    jsonObj.addProperty("strMsgEx", "ErrorKB");
                    return mapping.findForward(AppConstants.SUCCESS);
                }

                strTrangThai = AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET;
                lTTVO.setTrang_thai(strTrangThai); //Cho KTT duyet
                lTTVO.setId(nSoLTT);
                // xoa thong tin day lai
                lTTVO.setLydo_ktt_day_lai(STRING_EMPTY);
                lTTVO.setLydo_gd_day_lai(STRING_EMPTY);
                lTTVO.setNgay_nhan(null);
                lTTVO.setLy_do_htoan(request.getParameter("ly_do_htoan"));
                //                Savepoint spBeforeUpdateLTTDen =
                //                    conn.setSavepoint("BeforeUpdateLTTDen");
                int result = lTTDAO.update(lTTVO);

                boolean bFlagErr = false;
                //                if (STRING_EMPTY.equals(arrSo_tien[0])) {
                //                    jsonObj.addProperty("strMsgEx", "ErrorTienEmpty");
                //                    return null;
                //                } else {
                //                    bFlagErr = true;
                //                }
                if (result == 1) {
                    bFlagErr = true;
                }
                // Thong tin chi tiet COA
                if (bFlagErr) {
                    LTTCTietDAO lttCTietDAO = new LTTCTietDAO(conn);
                    LTTCTietVO lttCTietVO = null;
                    //Xoa LTT chi tiet co ltt_id = id
                    result = 0;
                    boolean bChildByLttId = false;
                    whereClause = " ltt_id=? ";
                    param = new Parameter(nSoLTT, true);
                    params = new Vector();
                    params.add(param);
                    bChildByLttId =
                            lttCTietDAO.checkExistLTTById(whereClause, params);
                    strLog.append("\n\t - ltt :" + nSoLTT +
                                  (bChildByLttId ? " khong COA" : " co COA"));

                    if (bChildByLttId) {
                        result = lttCTietDAO.deleteByLTT(nSoLTT);
                        if (result < 1) {
                            bFlagErr = false;
                        }
                    }

                    if (bFlagErr) {
                        for (int i = 0;
                             i < coaVOFRequest.getArrTkkt_ma().length; i++) {
                            //Check ket hop cheo (goi webservice)
                            // NEU LOAI HACH TOAN == 01 - HACH TOAN DUNG THI KO CHECK
                            if (strLoai_hach_toan.equals("01")) {
                                String strWSDL =
                                    getThamSoHThong("WSDL_KET_HOP_CHEO",
                                                    session);
                                DMCheo dmCheo = new DMCheo(strWSDL);
                                String[] arrResultKHC;
                                strAppID = getThamSoHThong("APP_ID", session);
                                arrResultKHC =
                                        dmCheo.checkKHCDM(coaVOFRequest.getArrMa_quy()[i],
                                                          coaVOFRequest.getArrTkkt_ma()[i],
                                                          coaVOFRequest.getArrNdkt_ma()[i],
                                                          coaVOFRequest.getArrCapns_ma()[i],
                                                          coaVOFRequest.getArrDvsdns_ma()[i],
                                                          coaVOFRequest.getArrDbhc_ma()[i],
                                                          coaVOFRequest.getArrChuongns_ma()[i],
                                                          coaVOFRequest.getArrNganhkt_ma()[i],
                                                          coaVOFRequest.getArrCtmt_ma()[i],
                                                          coaVOFRequest.getArrMa_kb()[i],
                                                          coaVOFRequest.getArrMa_nguon()[i],
                                                          coaVOFRequest.getArrDu_phong_ma()[i],
                                                          strAppID);
                                if (arrResultKHC != null &&
                                    arrResultKHC.length == 2) {
                                    if ("INVALID".equalsIgnoreCase(arrResultKHC[0])) {
                                        conn.rollback();
                                        if (arrResultKHC[1] != null) {
                                            strMsg =
                                                    arrResultKHC[1].replace("\n",
                                                                            ". ");
                                            jsonObj.addProperty("strMsgEx",
                                                                strMsg);
                                        } else {
                                            strMsg = arrResultKHC[1];
                                            jsonObj.addProperty("strMsgEx",
                                                                strMsg);
                                        }
                                        // coaCommon.taoLaiCOA(request);
                                        return mapping.findForward(AppConstants.SUCCESS);
                                        //                                        return mapping.findForward(forward);
                                    } else if ("ERROR".equalsIgnoreCase(arrResultKHC[0])) {
                                        conn.rollback();
                                        if (arrResultKHC[1] != null) {
                                            strMsg =
                                                    arrResultKHC[1].replace("\n",
                                                                            ". ");
                                            jsonObj.addProperty("strMsgEx",
                                                                strMsg);
                                        } else {
                                            strMsg = arrResultKHC[1];
                                            jsonObj.addProperty("strMsgEx",
                                                                strMsg);
                                        }
                                        //coaCommon.taoLaiCOA(request);
                                        return mapping.findForward(AppConstants.SUCCESS);
                                    }
                                } else {
                                    bFlagErr = true;
                                    break;
                                }
                            }
                            lttCTietVO =
                                    coaCommon.getRowInCOAFromCode(i, nSoLTT,
                                                                  coaVOFRequest, lttVOForUpdate.getNt_id().toString());
                            strLog.append("\n -TEST COA So_tien :" +
                                          request.getParameterValues("so_tien")[0] +
                                          "|" + request.getParameter("idHT"));
                            strLog.append("\n - TEST:" + nSoLTT + "|" +
                                          coaVOFRequest.getArrTkkt_ma()[i] +
                                          "|" +
                                          coaVOFRequest.getArrMa_kb()[i] +
                                          "|" +
                                          coaVOFRequest.getArrSo_tien()[i]);

                            strLog.append("\n - arrSo_tien dong " + (i + 1) +
                                          ":" + lttCTietVO.getSo_tien());
                            strLog.append("| arrMa_kb :" +
                                          lttCTietVO.getKb_id());
                            strLog.append("| arrTkkt_ma : " +
                                          lttCTietVO.getTkkt_id());
                            strLog.append("| " +
                                          session.getAttribute(AppConstants.APP_USER_CODE_SESSION) +
                                          " - " +
                                          session.getAttribute(AppConstants.APP_USER_NAME_SESSION));

                            Long id_ct = lttCTietDAO.insert(lttCTietVO);

                            if (id_ct == 0) {
                                bFlagErr = true;
                                conn.rollback();
                                break;
                            }
                        } //end for
                        strLog.append("\n------------------------------------------------------------------");
                       // writeLog.write(strLog.toString());
                    }
                }

                if (!bFlagErr) {
                    //conn.rollback(spBeforeUpdateLTTDen);
                    conn.rollback();
                    strMsg = AppConstants.FAILURE;
                    jsonObj.addProperty("strMsgEx", strMsg);
                    return mapping.findForward(AppConstants.SUCCESS);
                } else {
                    strMsg = AppConstants.SUCCESS;
                    jsonObj.addProperty("strTrangThai", strTrangThai);
                    jsonObj.addProperty("strMsgEx", strMsg);
                }
                //Save log
                saveVisitLog(conn, session, AppConstants.LTT_DEN_CHUC_NANG_SUA,
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
              ttspUtil.unLock(strID, "UPDATE-LTT"); 
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
                                       AppConstants.LTT_DEN_CHUC_NANG_DUYET)) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }
        forward = AppConstants.SUCCESS;
        Connection conn = null;
        Vector params = null;
        Parameter param = null;
        String whereClause = null;
        String strQuyenLttDen = "";
        String strMsg = "";
        JsonObject jsonObj = null;
        String strJson = null;
        //ManhNV-20141021
        TTSPUtils ttspUtil = null;        
        Long nSoLTT = new Long(0);
        String strSetLock = "";
        //****
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            jsonObj = new JsonObject();
            ttspUtil = new TTSPUtils(conn);
            //Get danh sach chuc nang
            List listCNang = new ArrayList();
            listCNang = getDSachCNang(request, AppConstants.LTT_DI);
            if (listCNang != null) {
                Iterator iter = listCNang.iterator();
                ChucNangVO cnangVO = null;
                while (iter.hasNext()) {
                    cnangVO = (ChucNangVO)iter.next();
                    strQuyenLttDen += cnangVO.getLoai_cnang();
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
            } else if (strQuyenLttDen == null ||
                       STRING_EMPTY.equalsIgnoreCase(strQuyenLttDen) ||
                       strQuyenLttDen.indexOf("D") == -1) {
                strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
                jsonObj.addProperty("strMsgEx", strMsg);
                return mapping.findForward(AppConstants.SUCCESS);
            } else {
                LTTDAO lttDAO = new LTTDAO(conn);
                LTTVO lttVO = null;
                //Lay ve so LTT, id nguoi dung, loai nguoi dung: ktv, ktt, gd
//                Long nSoLTT = new Long(0);
                Long nUserID = new Long(0);
                String strUserType = "";
                String strTrangThai =
                    AppConstants.LTT_TRANG_THAI_CHO_HOAN_THIEN;
                String strLyDoDayLai = "";
                String strStatusNewest = "";
                String strKTT = null;
                String strGD = null;
                boolean bChoPDayL = false;
                String strChuKy = null;

                nSoLTT = new Long(request.getParameter("id"));
                if (!(nSoLTT != null && !STRING_EMPTY.equals(nSoLTT))) {
                    strMsg = "ID_NULL";
                    jsonObj.addProperty("strMsgEx", strMsg);
                    return mapping.findForward(AppConstants.SUCCESS);
                }
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
              //Manhnv-20141021: Sua bo for update
              //Kiem tra thay the for update
                              strSetLock = ttspUtil.setLock(nSoLTT.toString(), "REJECT-LTT", new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString())); 
                              if(!"SUCCESS".equalsIgnoreCase(strSetLock)){
                                throw new Exception("LTT &#273;ang &#273;&#432;&#7907;c x&#7917; l&#253; b&#7903;i NSD kh&#225;c("+strSetLock+"), h&#227;y ch&#7885;n LTT kh&#225;c &#273;&#7875; ti&#7871;p t&#7909;c.");    
                              }
              //                lttVO = lttDAO.getLTTVOForUpdate(whereClause, params);
                              lttVO = lttDAO.getLTTVO(whereClause, params);
              //******************************************************************************
              //ManhNV-20141031: Sua cho phep 2 vai
                              long lTtvNhan = lttVO.getTtv_nhan()==null?0:lttVO.getTtv_nhan().longValue();
                              if(lTtvNhan == nUserID.longValue()){
                                throw new Exception("Kh&#244;ng &#273;&#7849;y l&#7841;i &#273;&#432;&#7907;c l&#7879;nh do ch&#237;nh m&#236;nh nh&#7853;p ho&#7863;c ho&#224;n thi&#7879;n &#7903; vai tr&#242; TTV");
                              }
              //****************************************************************************** 
                if (lttVO != null) {
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
                            AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI.equalsIgnoreCase(strStatusNewest)) {
                            bChoPDayL = true;
                        } else if ((AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET.equalsIgnoreCase(strStatusNewest)) &&
                                   (strKTT.equalsIgnoreCase(nUserID.toString()) ||
                                    strKTT.equalsIgnoreCase("null") ||
                                    "".equalsIgnoreCase(strKTT))) {
                            bChoPDayL = true;
                        }
                        if (bChoPDayL) {
                            strLyDoDayLai =
                                    request.getParameter("lydo_ktt_day_lai");
                        }
                        strTrangThai =
                                AppConstants.LTT_TRANG_THAI_KTT_DUYET_DAY_LAI;
                    } else if (strUserType.contains(AppConstants.NSD_GD)) {
                        if (!strGD.equalsIgnoreCase(nUserID.toString()) &&
                            strStatusNewest.equalsIgnoreCase("05")) {
                            bChoPDayL = true;
                        }
                        if (bChoPDayL == true) {
                            strLyDoDayLai =
                                    request.getParameter("lydo_gd_day_lai");
                        }
                        strTrangThai =
                                AppConstants.LTT_TRANG_THAI_GD_DUYET_DAY_LAI;
                    }
                }
                String strMsgId = null;
                int result = 0;
                if (nSoLTT != 0 && bChoPDayL == true)
                    result =
                            lttDAO.updateStatus(nSoLTT, strTrangThai, nUserID, strUserType,
                                                strLyDoDayLai, strChuKy,
                                                strMsgId, null, null, null);

                if (result == 1) {
                    strMsg = AppConstants.SUCCESS;
                    jsonObj.addProperty("strMsgEx", strMsg);
                    jsonObj.addProperty("strTrangThai", strTrangThai);
                } else {
                    strMsg = AppConstants.FAILURE;
                    jsonObj.addProperty("strMsgEx", strMsg);
                    return mapping.findForward(AppConstants.SUCCESS);
                }
                //Save log
                saveVisitLog(conn, session,
                             AppConstants.LTT_DEN_CHUC_NANG_DUYET, "Day lai");
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

    //Duyet LTT Den

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
        String strQuyenLttDen = "";
        String strMsg = "";
        JsonObject jsonObj = new JsonObject();
        String strJson = null;
        TTSPUtils ttspUtil = null;
        Long nSoLTT = new Long(0);
        String strSetLock = "";

//        QueueManager queueManager = null;//DOUBLE_20161122

        if (!checkPermissionOnFunction(request,
                                       AppConstants.LTT_DEN_CHUC_NANG_DUYET)) {
            strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
            jsonObj.addProperty("strMsgEx", strMsg);
            return mapping.findForward(forward);
        }

        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            ttspUtil = new TTSPUtils(conn);
            //Khoi tao QueueManager
//            queueManager = new QueueManager(getThamSoHThong(session));//DOUBLE_20161122
            //Get danh sach chuc nang
            List listCNang = new ArrayList();
            listCNang = getDSachCNang(request, AppConstants.LTT_DEN);
            if (listCNang != null) {
                Iterator iter = listCNang.iterator();
                ChucNangVO cnangVO = null;
                while (iter.hasNext()) {
                    cnangVO = (ChucNangVO)iter.next();
                    strQuyenLttDen += cnangVO.getLoai_cnang();
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
            if (dvGiaoDichVO != null) {
                //Lay ra danh sach LttDi (master) cho nguoi dung bi Ngat ket noi, danh sach Ngoai te
                //                lTTMaster(conn, request, session, "", "");
                strMsg = "NGAT_KET_NOI";
                jsonObj.addProperty("strMsgEx", strMsg);
                jsonObj.addProperty("LyDoNgatKetNoi",
                                    dvGiaoDichVO.getLido_ngat());
                return mapping.findForward(AppConstants.SUCCESS);
            } else if (strQuyenLttDen == null ||
                       STRING_EMPTY.equalsIgnoreCase(strQuyenLttDen) ||
                       strQuyenLttDen.indexOf("D") == -1) {
                strMsg = AppConstants.LTT_KHONG_CO_QUYEN;
                jsonObj.addProperty("strMsgEx", strMsg);
                return mapping.findForward(AppConstants.SUCCESS);
            } else {
                LTTDAO lttDAO = new LTTDAO(conn);
                LTTVO lttVO = null;
                //Lay ve so LTT, id nguoi dung, loai nguoi dung: ktv, ktt, gd
//                Long nSoLTT = new Long(0);
                Long nUserID = new Long(0);
                String strUserType = "";
                String strTrangThai =
                    AppConstants.LTT_TRANG_THAI_CHO_HOAN_THIEN;
                String strLyDoDayLai = "";
                BigDecimal nTongSoTien = new BigDecimal(0);
                String strTongTien = "0";
                String strStatusNewest = "";
                String strKTT = null;
                boolean bChoPhepDuyet = false;
                String strChuKy = null;
                String strMsgId = null;

                nSoLTT = Long.parseLong(request.getParameter("id"));

                Long so_tien_coa = new Long(0);
                LTTCTietDAO ctietDAO = new LTTCTietDAO(conn);
                String strWhereClauseCTiet = " t.ltt_id=?";
                Vector paramsCTiet = new Vector();
                paramsCTiet.add(new Parameter(nSoLTT, true));
                ArrayList<LTTCTietVO> colCTiet =
                    (ArrayList<LTTCTietVO>)ctietDAO.getLTTDiCTietList(strWhereClauseCTiet,
                                                                      paramsCTiet);
                if (!colCTiet.isEmpty()) {
                    for (LTTCTietVO ctietVO : colCTiet) {
                        //                        so_tien_coa.valueOf(ctietVO.getSo_tien().longValueExact());
                        so_tien_coa += ctietVO.getSo_tien().longValue();
                    }
                }

                strTongTien = request.getParameter("tong_sotien").trim();
                String strNgoaiTeID = request.getParameter("nt_id").trim();
//                if (strTongTien.indexOf(" ") != -1)
//                    strTongTien = strTongTien.replace(" ", "");
//                if (strTongTien.indexOf(".") != -1)
//                    strTongTien = strTongTien.replace(".", "");
//                if (strTongTien.indexOf(",") != -1)
//                    strTongTien = strTongTien.replace(",", "");

                nTongSoTien = StringUtil.convertCurrencyToNumber(strTongTien, strNgoaiTeID);

                if (so_tien_coa.compareTo(nTongSoTien.longValue()) != 0) {
                    jsonObj.addProperty("strMsgEx",
                                        "S&#7889; ti&#7873;n chi ti&#7871;t kho&#7843;n m&#7909;c kh&#225;c t&#7893;ng s&#7889; ti&#7873;n c&#7911;a LTT");
                    return null;
                }

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
                String signature = request.getParameter("signature");
                String strAppID = getThamSoHThong("APP_ID", session);
                String[] arrResultKy =
                    pkiService.VerifySignature(userName, certSerial,
                                               contenData, signature,
                                               strAppID);
                //String[] arrResultKy = pkiService.VerifySignature(userName, certSerial, contenData, "as");//de test

                if (arrResultKy != null && arrResultKy.length == 2) {
                    if (arrResultKy[0].equalsIgnoreCase("VALID")) {
                        bVerifySignature = true;
                    } else if (arrResultKy[0].equalsIgnoreCase("INVALID")) {
                        jsonObj.addProperty("strMsgEx", arrResultKy[1]);
                        return mapping.findForward(AppConstants.SUCCESS);
                        //bVerifySignature = false;
                    } else if (arrResultKy[0].equalsIgnoreCase("ERROR")) {
                        jsonObj.addProperty("strMsgEx", arrResultKy[1]);
                        return mapping.findForward(AppConstants.SUCCESS);
                    }
                }

                if (bVerifySignature) {
                    if (!STRING_EMPTY.equalsIgnoreCase(strUserType)) {
                        if (strUserType.contains(AppConstants.NSD_KTT)) {
                            //So sanh trang thai truoc khi Huy voi trang thai lay duoc tu form
                            whereClause = " t.id = ? ";
                            param = new Parameter(nSoLTT.toString(), true);
                            params = new Vector();
                            params.add(param);
                          //******************************************************************************
                          //Manhnv-20141021: Sua bo for update
                          //Kiem tra thay the for update
                                          strSetLock = ttspUtil.setLock(nSoLTT.toString(), "APPROVE-LTT", new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString())); 
                                          if(!"SUCCESS".equalsIgnoreCase(strSetLock)){
                                            throw new Exception("LTT &#273;ang &#273;&#432;&#7907;c x&#7917; l&#253; b&#7903;i NSD kh&#225;c("+strSetLock+"), h&#227;y ch&#7885;n LTT kh&#225;c &#273;&#7875; ti&#7871;p t&#7909;c.");    
                                          }
                          //              lttVO = lttDAO.getLTTVOForUpdate(whereClause, params);
                                          lttVO = lttDAO.getLTTVO(whereClause, params);
                          //******************************************************************************
                          //ManhNV-20141031: Sua cho phep 2 vai
                                          long lTtvNhan = lttVO.getTtv_nhan()==null?0:lttVO.getTtv_nhan().longValue();
                                          if(lTtvNhan == nUserID.longValue()){
                                            throw new Exception("Kh&#244;ng k&#253; duy&#7879;t &#273;&#432;&#7907;c l&#7879;nh do ch&#237;nh m&#236;nh nh&#7853;p ho&#7863;c ho&#224;n thi&#7879;n &#7903; vai tr&#242; TTV");
                                          }
                          //******************************************************************************
                         
                            if (lttVO != null) {
                                strStatusNewest = lttVO.getTrang_thai();
                                strKTT = lttVO.getKtt_duyet() + "";
                            }

                            if (AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET.equalsIgnoreCase(strStatusNewest)) {
                                if (strKTT.equalsIgnoreCase(nUserID.toString()) ||
                                    strKTT.equalsIgnoreCase("null")) {
                                    bChoPhepDuyet = true;
                                }
                            }
                            if (bChoPhepDuyet) {
                                strTrangThai =
                                        AppConstants.LTT_TRANG_THAI_DA_DUYET; //Da gui
                                strChuKy = request.getParameter("chuky_ktt");
                                getArraysInCOA(request);
                                //                          COACommon coaCommon = new COACommon(arrTkkt_ma, arrMa_quy, arrDvsdns_ma, arrCapns_ma, arrChuongns_ma, arrNganhkt_ma, arrNdkt_ma,
                                //                                                            arrDbhc_ma, arrCtmt_ma, arrMa_nguon, arrDu_phong_ma, arrMa_kb, arrDien_giai, arrSo_tien);
                                //DOUBLE_20161122-BEGIN
//                                queueManager.setQueueManager();
//                                SendLTToan sendLTT =
//                                    new SendLTToan(conn, queueManager);
//                                DOUBLE_20161122-END
                                String strHachToanTheoNgayKSNH = "Y";
                                if (session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION) !=
                                    null) {
                                    strHachToanTheoNgayKSNH =
                                            session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION).toString();
                                }
                                BuildMsgLTT sendLTT = new BuildMsgLTT(conn);
                                strMsgId =
                                        sendLTT.sendMessage(nSoLTT.toString(),
                                                            session.getAttribute(AppConstants.APP_KB_CODE_SESSION) ==
                                                            null ? "" :
                                                            session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(),
                                                            session.getAttribute(AppConstants.APP_USER_CODE_SESSION) ==
                                                            null ? "" :
                                                            session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString(),
                                                            "",
                                                            strHachToanTheoNgayKSNH,
                                                            session, "");
                            }
                        }
                    }
                    //Phai dung lttVO vi tren Form nt_id da bi disable
                    if (strTongTien == null ||
                        STRING_EMPTY.equals(strTongTien) ||
                        lttVO.getNt_id() == null)
                        bChoPhepDuyet = false;
                    int result = 0;
                    if (nSoLTT != 0 && bChoPhepDuyet == true)
                        result =
                                lttDAO.updateStatus(nSoLTT, strTrangThai, nUserID,
                                                    strUserType, strLyDoDayLai,
                                                    strChuKy, strMsgId, null,
                                                    null, null);

                    if (result == 1) {
                        strMsg = AppConstants.SUCCESS;
                        jsonObj.addProperty("strMsgEx", strMsg);
                        jsonObj.addProperty("strTrangThai", strTrangThai);
                    } else {
                        strMsg =
                                AppConstants.FAILURE + "---ltt_di.error.ngoai_te_so_tien";
                        jsonObj.addProperty("strMsgEx", strMsg);
                        return mapping.findForward(AppConstants.SUCCESS);
                    }
                } else {
                    strMsg =
                            AppConstants.FAILURE + "---ltt_di.error.chu_ky_so";
                    jsonObj.addProperty("strMsgEx", strMsg);
                    return mapping.findForward(AppConstants.SUCCESS);
                }
                //Save log
                saveVisitLog(conn, session,
                             AppConstants.LTT_DEN_CHUC_NANG_DUYET, "Duyet");
                conn.commit();
//              DOUBLE_20161122-BEGIN
//                try {
//                    queueManager.commitMQ();
//                } catch (Exception ex) {
//                    lttDAO.updateStatus(nSoLTT, "03", null, null, null, null,
//                                        null, null, null, null);
//                    conn.commit();//ManhNV-20161101-BO SUNG COMMIT
//                    throw ex;
//                }
//              DOUBLE_20161122-END
            }
        } catch (Exception ex) {
            //ROLLBACK MQ
//            DOUBLE_20161122-BEGIN
//            if (queueManager != null) {
//                queueManager.rollbackMQ();
//            }
//            DOUBLE_20161122-END
            jsonObj.addProperty("strMsgEx", "ERR: "+ex.getMessage());
        } finally {
            //unlock
            if("SUCCESS".equalsIgnoreCase(strSetLock)){
              if(ttspUtil == null){                
                ttspUtil = new TTSPUtils(conn);
              }
              ttspUtil.unLock(nSoLTT.toString(), "APPROVE-LTT"); 
            }
            //
            //DISCONNECT DB MQ
            close(conn);
//          DOUBLE_20161122-BEGIN
//            if (queueManager != null) {
//                queueManager.disconnectMQ();
//            }
//          DOUBLE_20161122-END

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

    public ActionForward searchLttDen(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        forward = AppConstants.SUCCESS;

        return mapping.findForward(forward);
    }

    public ActionForward searchLttDenExc(ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        forward = AppConstants.SUCCESS;
        return mapping.findForward(forward);
    }

    //LTT Master, strDSachCNang = "LTT.DEN"

    public void lTTMaster(Connection conn, HttpServletRequest request,
                          HttpSession session,
                          String strAction) throws Exception {
        Collection reVal = null;
        Vector params = null;
        Parameter param = null;
        String whereClause = "";
        String strQuyenLttDen = "";
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
            String strKBacID = "";
            String strUserID = "";
            String strSoLTT = "";
            if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) != null) {
                strKBacID =
                        session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }
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
            TTSPUtils ttspUtil = new TTSPUtils(conn);
            //checkNgatKetNoi = true, thi ko dc nhap lenh
            if (ttspUtil.checkNgatKetNoi(strKBacID)) {
                request.setAttribute("MessageLttDen",
                                     AppConstants.LTT_NGAT_KET_NOI);
            }

            //Get danh sach chuc nang
            List listCNang = new ArrayList();
            listCNang = getDSachCNang(request, AppConstants.LTT_DEN);
            if (listCNang != null) {
                Iterator iter = listCNang.iterator();
                ChucNangVO cnangVO = null;
                while (iter.hasNext()) {
                    cnangVO = (ChucNangVO)iter.next();
                    strQuyenLttDen += cnangVO.getLoai_cnang();
                }
            }

            if (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) !=
                null) {
                strUserType =
                        session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
            }
            if (AppConstants.LTT_DEN_CHUC_NANG_CHI_TIET.equalsIgnoreCase(strAction)) {
                String strSo_ltt = "";
                strSo_ltt = request.getParameter("so_ltt_details");
                params = new Vector();
                if (strSo_ltt != null &&
                    !STRING_EMPTY.equalsIgnoreCase(strSo_ltt)) {
                    whereClause = " t.id = ? ";
                    param = new Parameter(Long.parseLong(strSo_ltt), true);
                    params.add(param);
                }
                strQuyenLttDen = "THOAT";
                strSoLTT = strSo_ltt;
                bFirstRowM = false;
            } else if (AppConstants.LTT_DI_CHUC_NANG_CHI_TIET_T4.equalsIgnoreCase(strAction)) {
                String strSo_ltt = "";
                strSo_ltt = request.getParameter("so_ltt");
                params = new Vector();
                if (strSo_ltt != null &&
                    !STRING_EMPTY.equalsIgnoreCase(strSo_ltt)) {
                    whereClause = " t.id = ? ";
                    param = new Parameter(Long.parseLong(strSo_ltt), true);
                    params.add(param);
                }
                strQuyenLttDen = "T4";
                strSoLTT = strSo_ltt;
                bFirstRowM = false;

            } else {
                String strTTdatacdong =
                    "'" + AppConstants.LTT_TRANG_THAI_DA_DUYET + "', " + "'" +
                    AppConstants.LTT_TRANG_THAI_HUY + "', " + "'" +
                    AppConstants.LTT_TRANG_THAI_DA_GUI_CHO_CHAY_GIAO_DIEN +
                    "', " + "'" +
                    AppConstants.LTT_TRANG_THAI_DA_GUI_GIAO_DIEN_THANH_CONG +
                    "', " + "'" +
                    AppConstants.LTT_TRANG_THAI_DA_GUI_GIAO_DIEN_THAT_BAI +
                    "', " + "'" +
                    AppConstants.LTT_TRANG_THAI_NGAN_HANG_DA_NHAN + "', " +
                    "'" +
                    AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG_XL_THANH_CONG +
                    "', " + "'" +
                    AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG_XL_THAT_BAI +
                    "'";
                //                String FORMAT_NGAY_24H = "DD/MM/YYYY hh24:mi:ss";
                //                boolean bCheckCOT = false;
                //                String strCutOfTime =
                //                    getThamSoHThong("CUT_OF_TIME", session); //16:30:00
                //                if (strCutOfTime == null || strCutOfTime.isEmpty()) {
                //                    throw TTSPException.createException("TTSP-1001",
                //                                                        "Khong lay duoc gio CUT OF TIME cua he thong");
                //                    //strCutOfTime = "16:30:00";
                //                }
                //
                //                String strCurrDate = StringUtil.getCurrentDate();
                //                String strPreviousDate = StringUtil.getPreviousNextDate(-1);
                //
                //So sanh thoi gian hien tai va cutoftime
                //                if (!LTTCommon.isCurTimeLessThanCutOfTime(strCutOfTime.trim())) {
                //                    bCheckCOT = true;
                //                }
                //                if (!strCutOfTime.startsWith(" "))
                //                    strCutOfTime = " " + strCutOfTime;
                //                strCurrDate += strCutOfTime;
                //                strPreviousDate += strCutOfTime;


//ManhNV-20141103: Sua cho phep 2 vai ******************************************
                params = new Vector();
                StringBuffer sbWhereClause = new StringBuffer("");
              /***********20161101-JHS-MSTER-them dieu kien truy van-BEGIN************/
              sbWhereClause.append(" t.ngay_nhan > SYSDATE - " + strSoNgayTruyVan);
              sbWhereClause.append(" AND t.nhkb_nhan = ? ");
              //              sbWhereClause.append(" t.nhkb_nhan = ? ");
              /***********20161101-JHS-MSTER-them dieu kien truy van-BEGIN************/
                params.add(new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),true));
                
                //User co 2 vai KTT&TTV
                if (strUserType.contains(AppConstants.NSD_TTV) && strUserType.contains(AppConstants.NSD_KTT)) {
                    sbWhereClause.append("AND ( ((t.ttv_nhan = ? OR t.ktt_duyet = ?) AND t.trang_thai in ('01','02','03')) ");
                    params.add(new Parameter(strUserID, true));
                    params.add(new Parameter(strUserID, true));
                    sbWhereClause.append("OR (t.trang_thai in ('02','03') AND (t.ttv_nhan is null or t.ktt_duyet is null)) ");
                    sbWhereClause.append("OR ((t.ktt_duyet = ? OR t.ttv_nhan = ?) ");
                    params.add(new Parameter(strUserID, true));
                    params.add(new Parameter(strUserID, true));
                //User chi co vai TTV
                }else if (strUserType.contains(AppConstants.NSD_TTV) && !strUserType.contains(AppConstants.NSD_KTT)) {
                    sbWhereClause.append("AND ( (t.ttv_nhan = ? AND t.trang_thai in ('01','02','03')) ");
                    params.add(new Parameter(strUserID, true));
                    sbWhereClause.append("OR (t.trang_thai = '02' AND t.ttv_nhan is null) ");
                    sbWhereClause.append("OR (t.ttv_nhan = ? ");
                    params.add(new Parameter(strUserID, true));
                    
//                    params = new Vector();
//                    whereClause =
//                            " (substr(t.id,3,3) <> '701') AND ( (t.nhkb_nhan = ? AND t.ttv_nhan = ? AND (t.trang_thai=? OR t.trang_thai=? OR t.trang_thai='02')) " +
//                            " OR (t.nhkb_nhan = ? AND t.trang_thai = ? AND t.ttv_nhan is null) " +
//                            " OR (t.nhkb_nhan = ? AND t.ttv_nhan = ? " +
//                            " AND ((((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
//                            " AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh))" +
//                            " or (t.ngay_ktt_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
//                            " AND t.ngay_ktt_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)))" +
//                            " AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh))" +
//                            " OR" +
//                            " (((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
//                            "  AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh))" +
//                            " or (t.ngay_ktt_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
//                            " AND t.ngay_ktt_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)))" +
//                            " AND SYSDATE >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)))" +
//                            " AND (t.trang_thai in (" + strTTdatacdong +
//                            ") ) " + ") ) ";
//                    param =
//                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
//                                          true);
//                    params.add(param);
//                    param = new Parameter(strUserID, true);
//                    params.add(param);
//                    param =
//                            new Parameter(AppConstants.LTT_TRANG_THAI_KTT_DUYET_DAY_LAI,
//                                          true);
//                    params.add(param);
//                    param =
//                            new Parameter(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET,
//                                          true);
//                    params.add(param);
//
//                    param =
//                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
//                                          true);
//                    params.add(param);
//                    param =
//                            new Parameter(AppConstants.LTT_TRANG_THAI_CHO_HOAN_THIEN,
//                                          true);
//                    params.add(param);
//
//                    param =
//                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
//                                          true);
//                    params.add(param);
//                    param = new Parameter(strUserID, true);
//                    params.add(param);
                //User chi co vai KTT
                } else if (strUserType.contains(AppConstants.NSD_KTT) && !strUserType.contains(AppConstants.NSD_TTV)) {
                    strTTdatacdong =
                            strTTdatacdong + ",'" + AppConstants.LTT_TRANG_THAI_KTT_DUYET_DAY_LAI +
                            "'";
                    //menh de where
                    sbWhereClause.append("AND ( (t.ktt_duyet = ? AND t.trang_thai in ('01','03')) ");
                    params.add(new Parameter(strUserID, true));
                    sbWhereClause.append("OR (t.trang_thai = '03' AND t.ktt_duyet is null) ");
                    sbWhereClause.append("OR (t.ktt_duyet = ? ");
                    params.add(new Parameter(strUserID, true));
                    
                    
//                    whereClause =
//                            " (substr(t.id,3,3) <> 701) AND ( (t.nhkb_nhan = ? AND t.ktt_duyet = ? AND t.trang_thai=?) " +
//                            " OR (t.nhkb_nhan = ? AND t.trang_thai = ? AND t.ktt_duyet is null) " +
//                            " OR (t.nhkb_nhan = ? AND t.ktt_duyet = ? " +
//                            " AND ((((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
//                            " AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh))" +
//                            " or (t.ngay_ktt_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
//                            " AND t.ngay_ktt_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)))" +
//                            " AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh))" +
//                            " OR" +
//                            " (((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
//                            "  AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh))" +
//                            " or (t.ngay_ktt_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
//                            " AND t.ngay_ktt_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)))" +
//                            " AND SYSDATE >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)))" +
//                            " AND (t.trang_thai in (" + strTTdatacdong +
//                            ") ) " + ")  ) ";
//                    params = new Vector();
//
//                    param =
//                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
//                                          true);
//                    params.add(param);
//                    param = new Parameter(Long.parseLong(strUserID), true);
//                    params.add(param);
//                    param =
//                            new Parameter(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET,
//                                          true);
//                    params.add(param);
//
//                    param =
//                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
//                                          true);
//                    params.add(param);
//                    param =
//                            new Parameter(AppConstants.LTT_TRANG_THAI_CHO_KTT_DUYET,
//                                          true);
//                    params.add(param);
//
//                    param =
//                            new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
//                                          true);
//                    params.add(param);
//                    param = new Parameter(Long.parseLong(strUserID), true);
//                    params.add(param);
                }
                sbWhereClause.append(" AND ((((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                                          " AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh))" +
                                          " or (t.ngay_ktt_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                                          " AND t.ngay_ktt_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)))" +
                                          " AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh))" +
                                          " OR" +
                                          " (((t.ngay_hoan_thien >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                                          "  AND t.ngay_hoan_thien < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh))" +
                                          " or (t.ngay_ktt_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                                          " AND t.ngay_ktt_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)))" +
                                          " AND SYSDATE >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)))" +
                                          " AND (t.trang_thai in (" + strTTdatacdong +
                                          ") ) " + ")  ) ");
              whereClause = sbWhereClause.toString();
//20141103**********************************************************************
                //Cho them dieu kien - Neu la tra cuu LTT
                //              String strSo_yctt = "";
                if ("FIND_TKIEM_ACTION".equalsIgnoreCase(strAction)) {
                    String strSo_tien = "";
                    String strNhkb_chuyen = "";
                    String strTrang_thai = "";
                    String strSo_yctt = "";
                    String strTttvnhan = "";
                    strSo_tien = request.getParameter("sotien").trim();
                    
                    strNhkb_chuyen =
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
                        String strNgoaiTeID = request.getParameter("nt_id").trim();
                        BigDecimal bdSoTien = StringUtil.convertCurrencyToNumber(strSo_tien, strNgoaiTeID);
                        whereClause += " AND t.tong_sotien = ? ";
                        param = new Parameter(bdSoTien, true);
                        params.add(param);
                    }
                    if (strNhkb_chuyen != null &&
                        !STRING_EMPTY.equalsIgnoreCase(strNhkb_chuyen)) {
                        String strWhere = " ma_nh = ? ";
                        Vector vParam = new Vector();
                        vParam.add(new Parameter(strNhkb_chuyen, true));
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
                        !STRING_EMPTY.equals(strTrang_thai) &&
                        !"00".equalsIgnoreCase(strTrang_thai)) {
                        whereClause += " AND t.trang_thai = ? ";
                        param = new Parameter(strTrang_thai, true);
                        params.add(param);
                    }
                }
                if (AppConstants.LTT_DEN_CHUC_NANG_TIM_KIEM.equalsIgnoreCase(strAction)) {
                    String strTtv_nhan = "";
                    String strSo_tien = "";
                    String strNhkb_chuyen = "";
                    String strTrang_thai = "";
                    String strSoLtt = "";
                    String strLoaiTien = request.getParameter("nt_id_find");

                    strTtv_nhan = request.getParameter("ttvnhan");
                    strSo_tien = request.getParameter("sotien");
                    strNhkb_chuyen = request.getParameter("nhkbchuyennhan");

                    strTrang_thai = request.getParameter("trangthai");
                    strSoLtt = request.getParameter("soltt");

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
                    if (strSo_tien != null && !STRING_EMPTY.equalsIgnoreCase(strSo_tien)) {
                        //thuongdt-20170915 bo sung sua tra cuu tat ca theo loai tien start
                        String StrFormat = "VND";
                        if(!"".equals(strLoaiTien) && !"VND".equals(strLoaiTien))
                          StrFormat = "USD";
                        BigDecimal bdSo_tien = StringUtil.convertCurrencyToNumber(strSo_tien, StrFormat);
                      //thuongdt-20170915 bo sung sua tra cuu tat ca theo loai tien end
                        if(bdSo_tien.longValue() > 0){
                          whereClause += " AND t.tong_sotien = ? ";
                          param = new Parameter(bdSo_tien, true);
                          params.add(param);  
                        }    
                    }
                    if(strLoaiTien != null && !"".equals(strLoaiTien)){
                      whereClause += " AND e.ma = ? ";
                      param = new Parameter(strLoaiTien, true);
                      params.add(param);  
                    }
                    if (strNhkb_chuyen != null &&
                        !STRING_EMPTY.equalsIgnoreCase(strNhkb_chuyen)) {
                        String strWhere = " ma_nh = ? ";
                        Vector vParam = new Vector();
                        vParam.add(new Parameter(strNhkb_chuyen, true));
                        DMNHangVO dmnhVO =
                            LTTCommon.getDMNHang(strWhere, vParam, conn);

                        if (dmnhVO != null && dmnhVO.getId() != null) {
                            whereClause += " AND t.nhkb_chuyen = ? ";
                            param = new Parameter(dmnhVO.getId(), true);
                            params.add(param);
                        } else {
                            whereClause += " AND t.nhkb_chuyen = ? ";
                            param = new Parameter(0, true);
                            params.add(param);
                        }
                    }
                    if (strTrang_thai != null &&
                        !"00".equalsIgnoreCase(strTrang_thai)) {
                        whereClause += " AND t.trang_thai = ? ";
                        param = new Parameter(strTrang_thai, true);
                        params.add(param);
                    }
                    if (strSoLtt != null &&
                        !STRING_EMPTY.equalsIgnoreCase(strSoLtt)) {
                        whereClause += " AND t.id like (?) ";
                        param =
                                new Parameter("%" + Long.parseLong(strSoLtt) + "%",
                                              true);
                        params.add(param);
                    }

                } else if ("LTT.DEN.SUA.LAI".equalsIgnoreCase(strAction)) {
                    bFirstRowM = false;
                }
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
                reVal = lTTDAO.getLTTDiList(whereClause, params);
              
            } else if (strUserType.contains(AppConstants.NSD_KTT) ||
                       strUserType.contains(AppConstants.NSD_GD)) {          
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
                whereClause +=
                        " ORDER BY d.rv_high_value ASC, t.ttv_nhan ASC, t.ngay_hoan_thien ASC ";

                reVal = lTTDAO.getLTTDiKTTGDList(whereClause, params);
            } else {            
                whereClause +=
                        " AND (d.rv_domain = '" + AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT +
                        "') ";
                reVal = lTTDAO.getLTTDiKTTGDList(whereClause, params);
            }
            request.setAttribute(AppKeys.LTT_LIST_REQUEST_KEY, reVal);

            LTTVO lttVO = null;

            //Lay first Row in Master, de day du lieu ra form
            //            if (!bFirstRowM) {
            //                if (strSoLTT != null &&
            //                    !STRING_EMPTY.equalsIgnoreCase(strSoLTT)) {
            //                    //Chi tiet, cap nhat
            //                    Iterator iter = reVal.iterator();
            //                    int i = 0;
            //                    while (iter.hasNext()) {
            //                        if (lttVO != null &&
            //                            strSoLTT.equalsIgnoreCase(lttVO.getId().toString()))
            //                            break;
            //                        lttVO = (LTTVO)iter.next();
            //                        i++;
            //                    }
            //                }
            //            } else if (reVal != null && reVal.size() > 0) {
            //                //Lay dong dau tien trong LTT master
            //                Iterator iter = reVal.iterator();
            //                int i = 0;
            //                while (iter.hasNext()) {
            //                    if (i >= 1)
            //                        break;
            //                    lttVO = (LTTVO)iter.next();
            //                    i++;
            //                }
            //            }
            boolean bExistNgoaiTe = true;
            if (lttVO != null) {
                LTTForm lttForm = new LTTForm();
                if (lttVO != null) {
                    BeanUtils.copyProperties(lttForm, lttVO);
                }
                if (lttVO.getNgay_ct() != null)
                    lttForm.setNgay_ct(DateUtils.LongToStrDateDDMMYYYY(lttVO.getNgay_ct()));

                if (lttVO.getNgay_tt() != null)
                    lttForm.setNgay_tt(DateUtils.LongToStrDateDDMMYYYY(lttVO.getNgay_ct()));
                int ntId = 0;
                if (lttForm.getNt_id() != null) {
                    ntId = Integer.parseInt(lttForm.getNt_id());
                    lttForm.setTong_sotien(StringUtil.formatCurrency(lttForm.getTong_sotien(),
                                                                     ntId));
                } else {
                    lttForm.setNt_id("0");
                    bExistNgoaiTe = false;
                    //Neu throw se xuat hien File Download
                    //throw TTSPException.createException("TTSP-1013", "Loi do Lenh thanh toan khong co ma ngoai te");
                }

                //Lay ra chi tiet COA cho first Row in Master
                //                LTTCTietDAO lttCTietDAO = new LTTCTietDAO(conn);
                //                Collection colLTTCTiet = new ArrayList<LTTForm>();
                //                whereClause = " t.ltt_id = ? ";
                //                params = new Vector();
                //                param = new Parameter(Long.parseLong(lttForm.getId()), true);
                //                params.add(param);
                //                colLTTCTiet =
                //                        lttCTietDAO.getLTTDiCTietList(whereClause, params);
                //
                //                Iterator iter = colLTTCTiet.iterator();
                //                String strSo_tien_formated = "";
                //                LTTCTietVO lttCTietVO = null;
                //                LTTCTietForm lttCTietForm = null;
                //                colLTTCTiet = new ArrayList<LTTForm>();
                //
                //                while (iter.hasNext()) {
                //                    strSo_tien_formated = "";
                //                    lttCTietVO = (LTTCTietVO)iter.next();
                //                    if (lttCTietVO != null) {
                //                        strSo_tien_formated =
                //                                StringUtil.formatCurrency(lttCTietVO.getSo_tien().toString(),
                //                                                          ntId);
                //                        lttCTietForm = new LTTCTietForm();
                //                        BeanUtils.copyProperties(lttCTietForm, lttCTietVO);
                //                        lttCTietForm.setSo_tien(strSo_tien_formated);
                //                        colLTTCTiet.add(lttCTietForm);
                //                    }
                //                }
                //
                //                request.setAttribute("colLTTCTiet", colLTTCTiet);

                DMNHangVO dmnhVO = null;

                //lay Ma NHKB chuyen, Ma NHKB nhan
                if (lttVO.getNhkb_chuyen() != null) {
                    dmnhVO =
                            LTTCommon.getDMNHang(lttVO.getNhkb_chuyen(), conn);
                    if (dmnhVO != null) {
                        lttForm.setMa_nhkb_chuyen_cm(dmnhVO.getMa_nh());
                        lttForm.setTen_nhkb_chuyen_cm(dmnhVO.getTen());
                    }
                }
                if (lttVO.getId_nhkb_chuyen() != null) {
                    dmnhVO =
                            LTTCommon.getDMNHang(lttVO.getId_nhkb_chuyen(), conn);
                    if (dmnhVO != null) {
                        lttForm.setMa_nhkb_chuyen(dmnhVO.getMa_nh());
                    }
                }

                if (lttVO.getNhkb_nhan() != null) {
                    dmnhVO = LTTCommon.getDMNHang(lttVO.getNhkb_nhan(), conn);
                    if (dmnhVO != null) {
                        lttForm.setMa_nhkb_nhan_cm(dmnhVO.getMa_nh());
                        lttForm.setTen_nhkb_nhan_cm(dmnhVO.getTen());
                    }
                }
                if (lttVO.getId_nhkb_nhan() != null) {
                    dmnhVO =
                            LTTCommon.getDMNHang(lttVO.getId_nhkb_nhan(), conn);
                    if (dmnhVO != null)
                        lttForm.setMa_nhkb_nhan(dmnhVO.getMa_nh());
                }

                //Lay ra Ten Nguoi nhap theo TTV_Nhan
                if (lttVO.getTtv_nhan() != null) {
                    UserVO userVO = null;
                    userVO = getUserInfo(lttVO.getTtv_nhan().toString());
                    if (userVO != null) {
                        lttForm.setMa_ttv_nhan(userVO.getMa_nsd());
                        lttForm.setTen_ttv_nhan(userVO.getTen_nsd());
                    }
                }
                request.setAttribute("lTTForm", lttForm);
                request.setAttribute("TrangThai", lttForm.getTrang_thai());
            } //end auto select first row master

            
            //Danh sach tien te
            DMTienTeDAO dmTienTeDAO = new DMTienTeDAO(conn);
            Collection listDMTienTe = null;
            if(strKBacID.equals("1") || strKBacID.equals("2")){
              listDMTienTe = dmTienTeDAO.getDMTienTeCuaDViList(" and a.tinh_trang = '01' and b.loai_tk in ('02','03') ", null);
            }else{
                listDMTienTe = dmTienTeDAO.getDMTienTeCuaDViList(" and a.tinh_trang = '01' and b.kb_id = " + strKBacID +
                                                      " and b.loai_tk in ('02','03') ", null);
            }
            
            
            //Them "Chon" voi muc dich khi LTT Den khong co ma Ngoai te
            DMTienTeVO dmTienTeVO = new DMTienTeVO();
            dmTienTeVO.setId(Long.parseLong("0"));
            dmTienTeVO.setMa("Chon");
            listDMTienTe.add(dmTienTeVO);
            request.setAttribute("listDMTienTe", listDMTienTe);

            request.setAttribute("QuyenLttDen", strQuyenLttDen);
        
        } catch (Exception ex) {
            throw ex;
        }
    }

    public COAVO getArraysInCOA(HttpServletRequest request) {
        String[] arrTkkt_ma;
        String[] arrMa_quy;
        String[] arrDvsdns_ma;
        String[] arrCapns_ma;
        String[] arrChuongns_ma;
        String[] arrNganhkt_ma;
        String[] arrNdkt_ma;
        String[] arrDbhc_ma;
        String[] arrCtmt_ma;
        String[] arrMa_nguon;
        String[] arrDu_phong_ma;
        String[] arrMa_kb;
        String[] arrDien_giai;
        String[] arrSo_tien;
        arrTkkt_ma = request.getParameterValues("tkkt_ma");
        arrMa_quy = request.getParameterValues("ma_quy");
        arrDvsdns_ma = request.getParameterValues("dvsdns_ma");
        arrCapns_ma = request.getParameterValues("capns_ma");
        arrChuongns_ma = request.getParameterValues("chuongns_ma");
        arrNganhkt_ma = request.getParameterValues("nganhkt_ma");
        arrNdkt_ma = request.getParameterValues("ndkt_ma");
        arrDbhc_ma = request.getParameterValues("dbhc_ma");
        arrCtmt_ma = request.getParameterValues("ctmt_ma");
        arrMa_nguon = request.getParameterValues("ma_nguon");
        arrDu_phong_ma = request.getParameterValues("du_phong_ma");
        arrMa_kb = request.getParameterValues("kb_ma");
        arrDien_giai = request.getParameterValues("dien_giai");
        arrSo_tien = request.getParameterValues("so_tien");
        COAVO coaVOFromRequest =
            new COAVO(arrTkkt_ma, arrMa_quy, arrDvsdns_ma, arrCapns_ma,
                      arrChuongns_ma, arrNganhkt_ma, arrNdkt_ma, arrDbhc_ma,
                      arrCtmt_ma, arrMa_nguon, arrDu_phong_ma, arrMa_kb,
                      arrDien_giai, arrSo_tien);
        return coaVOFromRequest;
    }

    public void getTrangThaiLTTList(Connection conn,
                                    HttpServletRequest request) throws Exception {
        try {
            Collection colTrangThai = null;
            MaThamChieuDAO thamchieuDAO = new MaThamChieuDAO(conn);
            String strWhere =
                " a.rv_domain = ? and a.rv_low_value in (01,02,03,07,08,11,12,13)";
            Vector vParam = new Vector();
            vParam.add(new Parameter(AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT,
                                     true));
            colTrangThai =
                    thamchieuDAO.getMaThamChieuLTTDenList(strWhere, vParam);

            request.setAttribute("colTrangThai", colTrangThai);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
            LTTCTietDAO ctietDAO = new LTTCTietDAO(conn);
            String ltt_id =
                request.getParameter("ltt_id") != null ? request.getParameter("ltt_id") :
                "";
            String whereClause = " ltt_id = ?";
            Vector params = new Vector();
            params.add(new Parameter(ltt_id, true));
            // Check COA
            bResult = ctietDAO.checkExistLTTById(whereClause, params);
            // END
            jsonObj.addProperty("coa_err", bResult);
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
            //            if (bResult) {
            //                taoLaiCOA(request);
            //            }
            //            request.setAttribute("ErrorAt", strCheckLTT);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }


    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }

        String strType_LTT = "LTTDEN";
        Connection conn = null;
        String reportName = null;
        InputStream reportStream = null;
        LTTForm lttForm = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = null;
        Vector vParam = null;
        StringBuffer sbSubHTML = new StringBuffer();
        String nt_id = request.getParameter("rpt_nt_id");
        try {
	    HttpSession session = request.getSession();
            conn = getConnection(request);
            lttForm = (LTTForm)form;
            LTTDAO lttDAO = new LTTDAO(conn);
            sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                             lttForm.getId() + "\" id=\"id\"></input>");
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
                reportName = "ChungTuLenhThanhToanDen";
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                                "/" +
                                                                                                reportName +
                                                                                                ".jasper");
                if (reportStream == null) {
                    throw (new TTSPException().createException("TTSP-9999",
                                                               "Kh&#244;ng t&#236;m th&#7845;y file b&#225;o c&#225;o!"));
                }
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    AppConstants.REPORT_DIRECTORY +
                    AppConstants.FONT_FOR_REPORT;

                String strPathImage = "";

                String strNH = "";
                // xac dinh NH
                if (lttVO.getNhkb_chuyen() != null) {
                    DMNHangDAO nhDAO = new DMNHangDAO(conn);
                    String strWhereClauseNH = " a.id=? ";
                    Vector paramNH = new Vector();
                    paramNH.add(new Parameter(lttVO.getNhkb_chuyen(), true));
                    DMNHangVO nhVO = nhDAO.getDMNH(strWhereClauseNH, paramNH);
                    if (nhVO != null) {
                        strPathImage = "report/image/sign";
                        if (nhVO.getMa_nh().substring(2, 5).equals("201")) {
                            strNH = "201";
                        } else if (nhVO.getMa_nh().substring(2,
                                                             5).equals("204")) {
                            strNH = "204";
                        } else if (nhVO.getMa_nh().substring(2,
                                                             5).equals("202")) {
                            strNH = "202";
                        } else if (nhVO.getMa_nh().substring(2,
                                                             5).equals("203")) {
                            strNH = "203";
                        }
                    }
                    if(nt_id.equals("177")){
                        parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                    }else{
                        parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
                    }
                    DmTSoHToanVO tsoVO = null;
                    DmTSoHToanDAO tsoDAO = new DmTSoHToanDAO(conn);
                    String strMaNH = "";
                    if (lttForm.getMa_nhkb_chuyen_cm() != null) {
                        if (lttForm.getMa_nhkb_chuyen_cm().trim().length() ==
                            8) {
                            strMaNH =
                                    lttForm.getMa_nhkb_chuyen_cm().trim().substring(2,
                                                                                    5);
                        }
                    }
                    if ("".equals(strMaNH) || null == strMaNH) {
                        DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
                        Vector params = new Vector();
                        params.add(new Parameter(lttVO.getNhkb_chuyen(),
                                                 true));
                        DMNHangVO dmnhVO =
                            dmnhDAO.getDMNH(" a.id = ? ", params);
                        strMaNH = dmnhVO.getMa_nh().substring(2, 5);
                    }

                    String strParamForLTT = "";
                    if ("02".equals(lttVO.getLoai_hach_toan())) {
                      if(lttVO.getNt_code().equals("VND")){
                          strParamForLTT = "HACH_TOAN_CHO_XLY_LTT_NO";
                      }else{
                          strParamForLTT = "NGOAI_TE_HACH_TOAN_CHO_XLY_LTT_NO";
                      }
                    } else {
                        if(lttVO.getNt_code().equals("VND")){
                          strParamForLTT = "HACH_TOAN_DUNG_LTT";
                        }else{
                          strParamForLTT = "NGOAI_TE_HACH_TOAN_DUNG_LTT";
                            try {
                                String strMaKB =
                                    session.getAttribute(AppConstants.APP_KB_CODE_SESSION) !=
                                    null ?
                                    session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString() :
                                    "";
                                TKNHKBacDAO tknhDAO = new TKNHKBacDAO(conn);
                                String strWhereTK =
                                    " AND b.ma_nh = ? AND c.ma = ? AND a.ma_nt = ? AND a.trang_thai = '01' and a.loai_tk in ('02','03') ";
                                Vector vtParamTK = new Vector();
                                vtParamTK.add(new Parameter(nhVO.getMa_nh(),
                                                            true));
                                vtParamTK.add(new Parameter(strMaKB, true));
                                vtParamTK.add(new Parameter(lttVO.getNt_code(),
                                                            true));
                                TKNHKBacVO tkVO =
                                    tknhDAO.getTK_NH_KB_VO(strWhereTK,
                                                           vtParamTK);
                                if ("N".equalsIgnoreCase(tkVO.getQuyet_toan())) {
                                    strParamForLTT =
                                            "NGTE_KO_QTOAN_HACH_TOAN_DUNG_LTT";
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        
                    }

                    String strWhereClauseTSo =
                        " AND a.loai_htoan = ? AND a.ma_nh = ? ";
                    Vector paramsForTSo = new Vector();
                    paramsForTSo.add(new Parameter(strParamForLTT, true));
                    paramsForTSo.add(new Parameter(strMaNH, true));
                    tsoVO =
                            tsoDAO.getDmTSoHToan(strWhereClauseTSo, paramsForTSo);
                    parameterMap.put("NO_TK",
                                     tsoVO != null ? tsoVO.getTktn() : "");

                    String strTKCo = "";
                    if ("02".equals(lttVO.getLoai_hach_toan())) {
                      if(lttVO.getNt_code().equals("VND"))
                        strParamForLTT = "HACH_TOAN_CHO_XLY_LTT_CO";
                      else 
                        strParamForLTT = "NGOAI_TE_HACH_TOAN_CHO_XLY_LTT_CO";
                      
                        paramsForTSo = new Vector();
                        paramsForTSo.add(new Parameter(strParamForLTT, true));
                        paramsForTSo.add(new Parameter(strMaNH, true));
                        tsoVO =
                                tsoDAO.getDmTSoHToan(strWhereClauseTSo, paramsForTSo);
                        strTKCo = tsoVO != null ? tsoVO.getTktn() : "";
                    } else {
                        // lay rownum =1 cua ltt ctiet
                        LTTCTietDAO ctietDAO = new LTTCTietDAO(conn);
                        String whereCtiet = " t.ltt_id=? and rownum=1 ";
                        Vector paramsCtiet = new Vector();
                        paramsCtiet.add(new Parameter(lttForm.getId(), true));
                        LTTCTietVO ctietVO =
                            ctietDAO.getLTTDiCTiet(whereCtiet, paramsCtiet);
                        if (ctietVO != null) {
                            strTKCo = ctietVO.getTkkt_ma();
                        } else {
                            if (lttVO.getSo_tk_nhan().length() >= 4) {
                                strTKCo =
                                        lttVO.getSo_tk_nhan().substring(0, 4);
                            }
                        }
                    }
                    parameterMap.put("CO_TK", strTKCo);

                    parameterMap.put("P_ID", lttForm.getId());
                    parameterMap.put("Type_LTT", strType_LTT);
                    parameterMap.put("p_imageApprove", strPathImage);
                    parameterMap.put("p_dvApprove", strNH);
                    //                parameterMap.put("SUBREPORT_DIR", reportStreamSub);
                    jasperPrint =
                            JasperFillManager.fillReport(reportStream, parameterMap,
                                                         conn);
                    request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
                                                      jasperPrint);
                    String strTypePrintAction =
                        request.getParameter(AppConstants.REQUEST_ACTION) ==
                        null ? "" :
                        request.getParameter(AppConstants.REQUEST_ACTION).toString();
                    String strActionName = "lttLoRptAction.do";
                    String strParameter = "";

                    ReportUtility rpUtilites = new ReportUtility();
                    rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                            response, reportName, strPathFont,
                                            strActionName,
                                            sbSubHTML.toString(),
                                            strParameter);
                } else {
                    throw (new TTSPException().createException("TTSP-9999",
                                                               "Kh�ng t�m th?y b?n ghi n�o!"));
                }
            }
        } catch (Exception e) {
            //throw (new TTSPException()).createException("", "");
            e.printStackTrace();
            throw e;
        } finally {
            if (reportStream != null)
                reportStream.close();
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
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Connection conn = null;
        try {
            conn = getConnection(request);
            LTTCTietDAO ctietDAO = new LTTCTietDAO(conn);
            String ltt_id =
                request.getParameter("ltt_id") != null ? request.getParameter("ltt_id") :
                "";
            String whereClause = " ltt_id = ?";
            Vector params = new Vector();
            params.add(new Parameter(ltt_id, true));
            Collection collLttDtl =
                ctietDAO.getLTTDiCTietList(whereClause, params);
            // lay ra ten dvqhns
            // uu tien dvqhns ban ghi gan nhat
            if (collLttDtl != null && collLttDtl.size() > 0) {
                Iterator ito = collLttDtl.iterator();
                DMDonViSdnsDAO dao = new DMDonViSdnsDAO(conn);
                String strWhereClauseDVQHNS = " a.ma=? ";
                while (ito.hasNext()) {
                    Vector paramDVQHNS = new Vector();
                    LTTCTietVO voCOA = (LTTCTietVO)ito.next();
                    DMDonViSdnsVO dvsdnsVO = null;
                    paramDVQHNS.add(new Parameter(voCOA.getDvsdns_ma(), true));
                    dvsdnsVO =
                            dao.getDMDonViSdns(strWhereClauseDVQHNS, paramDVQHNS);
                    if (dvsdnsVO != null) {
                        Type TypeDVQHNS = new TypeToken<DMDonViSdnsVO>() {
                        }.getType();
                        strJson = new Gson().toJson(dvsdnsVO, TypeDVQHNS);
                        jsonObj.addProperty("TenCOA", strJson);
                        break;
                    }
                }
                Type listTypeDetail = null;
                listTypeDetail = new TypeToken<ArrayList<LTTCTietVO>>() {
                    }.getType();
                strJson = new Gson().toJson(collLttDtl, listTypeDetail);

                jsonObj.addProperty("details", strJson);

                JsonArray jsonArr = new JsonArray();
                JsonElement jsonEle = jsonObj.get("details");
                jsonArr.add(jsonEle);

                jsonEle = jsonObj.get("TenCOA");
                if (jsonObj.get("TenCOA") == null) {
                    Type TypeDVQHNS = new TypeToken<DMDonViSdnsVO>() {
                    }.getType();
                    strJson =
                            new Gson().toJson(new DMDonViSdnsVO(), TypeDVQHNS);
                    jsonObj.addProperty("TenCOA", strJson);
                    jsonEle = jsonObj.get("TenCOA");
                }
                jsonArr.add(jsonEle);

                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(jsonArr.getAsJsonArray().toString());
                out.flush();
                out.close();
            } else {
                jsonObj.addProperty("error",
                                    "kh&#244;ng t&#236;m th&#7845;y chi ti&#7871;t kho&#7843;n m&#7909;c c&#7911;a l&#7879;nh " +
                                    ltt_id);
                strJson = jsonObj.toString();
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            jsonObj.addProperty("error", e.getMessage());
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        } finally {

            close(conn);
        }
        return null;
    }

}
