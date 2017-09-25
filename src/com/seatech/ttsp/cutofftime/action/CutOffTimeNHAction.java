package com.seatech.ttsp.cutofftime.action;


//import com.seatech.ttsp.dchieu.DChieu1DAO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.cutofftime.CutOffTimeDAO;
import com.seatech.ttsp.cutofftime.CutOffTimeVO;
import com.seatech.ttsp.cutofftime.form.CutOffTimeForm;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeDAO;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeVO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.proxy.giaodien.Send299;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class CutOffTimeNHAction extends AppAction {
    private static String SGD = "0003";
    private static String CAP_TINH = "5";

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        Collection colCOTDi = null;
        String whereClause = "";
        String action = "";
        boolean flag = true;
        Collection colDMNH = null;
        Collection lstKBTinh = null;
        Collection lstKBHuyen = null;
        String strKBHuyen = "";
        try {
            conn = getConnection(request);
            //Chinh 2015/04/22 theo quy trinh noi gio 2015 LDPTT moi co quyen vao chuc nang nay
            HttpSession session = request.getSession();
            //            if (!session.getAttribute("role_list").equals("CBPT-TTTT")) {
            //                return mapping.findForward("errorQuyen");
            //            }

            CutOffTimeForm cotForm = (CutOffTimeForm)form;
            action = request.getParameter(AppConstants.REQUEST_ACTION);
            if (session != null) {

                TTThanhToanDAO ttttDAO = new TTThanhToanDAO(conn);
                colDMNH = ttttDAO.getDMucNH(null, null);
                DMKBacDAO dao = new DMKBacDAO(conn);
                // danh sach kb tinh
                String kb_code =
                    session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
                if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                    whereClause = " ";
                    lstKBTinh = dao.getDMucKB_cha(whereClause, null);
                    lstKBHuyen = dao.getDMucKBHuyen(whereClause, null);
                } else {
                    String strWhere = " AND a.ma='0003' ";
                    lstKBTinh = dao.getDMucKB_cha(strWhere, null);
                    lstKBHuyen = dao.getDMucKBHuyen(whereClause, null);
                }
                //load KB Huyen
                Type listType2 = new TypeToken<Collection<DMKBacVO>>() {
                }.getType();
                strKBHuyen = new Gson().toJson(lstKBHuyen, listType2);

                String strListRole =
                    (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                if (flag) {
                    whereClause =
                            " a.nhkb_nhan='01701001' AND a.ngay_nhan>=trunc(sysdate) AND a.trang_thai in ('05','06','07') AND (a.cot_id IS NULL OR a.cot_id <> 0) ";
                }
                CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
                colCOTDi = cotDAO.getCOTList(whereClause, null);
                //response danh sach dien tra soat
                if (request.getAttribute("getJson") != null ||
                    (action != null &&
                     (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                      action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                    Type listType = new TypeToken<Collection<CutOffTimeVO>>() {
                    }.getType();
                    String strJson = new Gson().toJson(colCOTDi, listType);
                    response.reset();
                    response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                    PrintWriter out = response.getWriter();
                    out.println(strJson.toString());
                    out.flush();
                    out.close();
                } else {
                    request.setAttribute("listCOTDi", colCOTDi);
                    request.setAttribute("chucdanh", strListRole);
                }
            } else {
                return mapping.findForward("timeout");
            }
        } catch (Exception e) {
            if (request.getAttribute("getJson") != null ||
                (action != null && (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                                    action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                response.reset();
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
                out.println(jsonObj.toString());
                out.flush();
                out.close();

            } else {
                throw TTSPException.createException("list()", e.getMessage());
            }
        } finally {
            request.setAttribute("colDMNH", colDMNH);
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstKBHuyen", strKBHuyen);
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    /**
     *@author hungpd
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Vector params = null;
        JsonObject jsonObj = null;
        String strJson = "";
        Connection conn = null;
        Collection listKBNoiGio = null;
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        try {
            conn = getConnection(request);
            String action = request.getParameter(AppConstants.REQUEST_ACTION);
            if (action != null &&
                AppConstants.ACTION_FILL_DATA_TO_FORM.equalsIgnoreCase(action)) {
                String id = request.getParameter("id");
                CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
                String whereClause = "  a.id=? ";
                params = new Vector();
                params.add(new Parameter(id, true));
                CutOffTimeVO cotVO = cotDAO.getCTONH(whereClause, params);

                //20150806
                //Lay DS KB de nghi noi gio
                if (cotVO.getMa_kb_huyen() != null && !"".equals(cotVO.getMa_kb_huyen())) {
                    String strMaKBList = cotVO.getMa_kb_huyen().replace(",", "','");
                    String strSQL =
                        " ma_nh in ('" + strMaKBList + "') and tinh_trang = '1'";
                    DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
                    Collection KBList = dmnhDAO.getDMNHList(strSQL, null);
                    

                    String[] arrMaNHList = cotVO.getMa_kb_huyen().split(",");
                    DMNHangVO dmnhVO = null;
                    String strTenKBList = "";
                    for (int i = 0; i < arrMaNHList.length; i++) {
                       Iterator iter = KBList.iterator();
                        while (iter.hasNext()) {
                            dmnhVO = (DMNHangVO)iter.next();
                            if(dmnhVO.getMa_nh().equals(arrMaNHList[i])){
                              if ("".equals(strTenKBList)) {
                                  strTenKBList = dmnhVO.getTen();
                              } else {
                                  strTenKBList =
                                          strTenKBList + "@ttsp@" + dmnhVO.getTen();
                              }
                            }
                        }
                    }
                    cotVO.setTen_kb(strTenKBList);
                }
                //20150806
                //                String strKBHuyenList = cotVO.getMa_kb_huyen();
                //                String strCNNHList = cotVO.getMa_nh();
                //                String strCOTCuList = cotVO.getCot_cu_list();
                //                if (strKBHuyenList != null) {
                //                    String[] arrKBHuyen = strKBHuyenList.split(",");
                //                    String[] arrCNNH = strCNNHList.split(",");
                //                    String[] arrCOTCu = strCOTCuList.split(",");
                //                    listKBNoiGio = new ArrayList();
                //                    for (int i = 0; i < arrKBHuyen.length; i++) {
                //                        //Lay ten KB
                //                        DMNHangDAO daoNH = new DMNHangDAO(conn);
                //                        Vector vParam = new Vector();
                //                        if(arrKBHuyen[i] == null || "".equals(arrKBHuyen[i])){
                //                          continue;
                //                        }
                //                        vParam.add(new Parameter(arrKBHuyen[i], true));
                //                        DMNHangVO voNH =
                //                            daoNH.getDMNH(" a.ma_nh = ? ", vParam);
                //                        //***
                //                        TSoCutOfTimeVO vo = new TSoCutOfTimeVO();
                //                        vo.setMa_nh_kb(arrKBHuyen[i]);
                //                        vo.setMa_nh(arrCNNH[i]);
                //                        vo.setCut_of_time(arrCOTCu[i]);
                //                        vo.setTen_nh_kb(voNH.getTen());
                //                        listKBNoiGio.add(vo);
                //                    }
                //                    cotVO.setList_ma_nh_kb_huyen(listKBNoiGio);
                //
                //                }

                if (cotVO != null) {
                    Type listType = new TypeToken<CutOffTimeVO>() {
                    }.getType();
                    strJson = new Gson().toJson(cotVO, listType);
                }
            }
            //            }
        } catch (Exception e) {
            //            e.printStackTrace();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
        } finally {
            if (out != null) {
                out.println(strJson.toString());
                out.flush();
                out.close();
            }
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        //
        //        CutOffTimeVO cotVO = null;
        //        int i = 0;
        //        Connection conn = null;
        //        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        //        JsonObject jsonObj = null;
        //        PrintWriter out = response.getWriter();
        //        try {
        //
        //            CutOffTimeForm cotForm = (CutOffTimeForm)form;
        //            HttpSession session = request.getSession();
        //            if (session == null)
        //                return mapping.findForward(AppConstants.FAILURE);
        //            conn = getConnection(request);
        //            CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
        //            //select before for update
        //            String stateReq = cotForm.getTrang_thai();
        //            String stateAfterSelect =
        //                TTSPUtils.getStateBUCOT(request, response, conn);
        //            String userId =
        //                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
        //            if ((stateReq != null && stateAfterSelect != null) &&
        //                stateReq.equalsIgnoreCase(stateAfterSelect)) {
        //                String action =
        //                    request.getParameter(AppConstants.REQUEST_ACTION);
        //                //huy
        //                if (action != null && AppConstants.ACTION_YES.equals(action)) {
        //                    if (!checkPermissionOnFunction(request,
        //                                                   "SYS.QLY_COT.YC_NH_YES_NO")) {
        //                        jsonObj = new JsonObject();
        //                        jsonObj.addProperty(AppConstants.ERROR,
        //                                            AppConstants.FAILURE);
        //                        out.println(jsonObj.toString());
        //                    }
        //                    if (jsonObj == null) {
        //                        cotVO = new CutOffTimeVO();
        //                        cotVO.setId(cotForm.getId());
        //                        cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_DA_DONGY);
        //                        i = cotDAO.update(cotVO);
        //                        if (stateReq.equals(AppConstants.TRANG_THAI_COT_CHO_DONGY)) {
        //                            //insert
        //                            CutOffTimeVO voTemp =
        //                                getCutOffTimeVO(cotForm.getId(), cotDAO);
        //                            cotVO.setDong_y(AppConstants.ACTION_YES);
        //                            cotVO.setNgay_lap(new Date());
        //                            cotVO.setNguoi_lap(userId);
        //                            cotVO.setCot_id(cotForm.getId());
        //                            cotVO.setLydo_cot(voTemp.getLydo_cot());
        //                            cotVO.setMa_kb_huyen(voTemp.getMa_kb_huyen());
        //                            cotVO.setNhkb_nhan(cotForm.getMa_nh());
        //                            cotVO.setNhkb_chuyen(AppConstants.KBNN_SGD_BANK_CODE);
        //                            cotVO.setLoai_cot(cotForm.getLoai_cot());
        //                            cotVO.setTgian_cot_cu(convertCOT(cotForm.getF_ngay_cot(),
        //                                                             cotForm.getGio_cot_cu()));
        //                            cotVO.setTgian_cot_moi(convertCOT(cotForm.getF_ngay_cot(),
        //                                                              cotForm.getGio_cot_moi()));
        //                            cotDAO.insert(cotVO, "299");
        //                        } else {
        //                            cotVO.setId(cotForm.getCot_id());
        //                            cotVO.setDong_y(AppConstants.ACTION_YES);
        //                            cotVO.setLydo_ko_dongy("");
        //                            i = cotDAO.update(cotVO);
        //                        }
        //
        //                        saveVisitLog(conn, session, "SYS.QLY_COT.YC_NH_YES_NO",
        //                                     "");
        //                    }
        //
        //                } else if (action != null &&
        //                           AppConstants.ACTION_NO.equals(action)) {
        //                    if (!checkPermissionOnFunction(request,
        //                                                   "SYS.QLY_COT.YC_NH_YES_NO")) {
        //                        jsonObj = new JsonObject();
        //                        jsonObj.addProperty(AppConstants.ERROR,
        //                                            AppConstants.FAILURE);
        //                        out.println(jsonObj.toString());
        //                    }
        //                    if (jsonObj == null) {
        //                        cotVO = new CutOffTimeVO();
        //                        cotVO.setId(cotForm.getId());
        //                        cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_KHONG_DONGY);
        //                        i = cotDAO.update(cotVO);
        //                        if (stateReq.equals(AppConstants.TRANG_THAI_COT_CHO_DONGY)) {
        //                            //insert
        //                            CutOffTimeVO voTemp =
        //                                getCutOffTimeVO(cotForm.getId(), cotDAO);
        //                            cotVO.setDong_y(AppConstants.ACTION_NO);
        //                            cotVO.setNgay_lap(new Date());
        //                            cotVO.setNguoi_lap(userId);
        //                            cotVO.setCot_id(cotForm.getId());
        //                            cotVO.setMa_kb_huyen(voTemp.getMa_kb_huyen());
        //                            cotVO.setNhkb_nhan(cotForm.getMa_nh());
        //                            cotVO.setNhkb_chuyen(AppConstants.KBNN_SGD_BANK_CODE);
        //                            cotVO.setLoai_cot(cotForm.getLoai_cot());
        //                            cotVO.setLydo_ko_dongy(cotForm.getLydo_ko_dongy());
        //                            cotDAO.insert(cotVO, "299");
        //                        } else {
        //                            cotVO.setId(cotForm.getCot_id());
        //                            cotVO.setDong_y(AppConstants.ACTION_NO);
        //                            cotVO.setLydo_ko_dongy(cotForm.getLydo_ko_dongy());
        //                            i = cotDAO.update(cotVO);
        //                        }
        //                        saveVisitLog(conn, session, "SYS.QLY_COT.YC_NH_YES_NO",
        //                                     "khong dong y");
        //                    }
        //                } else if (action != null &&
        //                           AppConstants.ACTION_APPROVED.equalsIgnoreCase(action)) {
        //                    if (!checkPermissionOnFunction(request,
        //                                                   "SYS.QLY_COT.YC_NH_DUYET_DAYLAI")) {
        //                        jsonObj = new JsonObject();
        //                        jsonObj.addProperty(AppConstants.ERROR,
        //                                            AppConstants.FAILURE);
        //                        out.println(jsonObj.toString());
        //                    }
        //                    if (jsonObj == null) {
        //                        cotVO = new CutOffTimeVO();
        //                        cotVO.setId((cotForm.getId()));
        //                        cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_DA_DUYET_DONGY);
        //                        String strWSDL = getThamSoHThong("WSDL_PKI", session);
        //                        String strAppID = getThamSoHThong("APP_ID", session);
        //                        PKIService pkiService = new PKIService(strWSDL);
        //                        String userName =
        //                            session.getAttribute(AppConstants.APP_DOMAIN_SESSION).toString() +
        //                            "\\" +
        //                            session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
        //                        String strSign = request.getParameter("signature");
        //                        String strContentData =
        //                            request.getParameter("noi_dung_ky");
        //                        String strCertSerial =
        //                            request.getParameter("certSerial");
        //                        String[] resultPKI =
        //                            pkiService.VerifySignature(userName, strCertSerial,
        //                                                       strContentData, strSign,
        //                                                       strAppID);
        //                        if (resultPKI != null &&
        //                            (resultPKI[0].equals(AppConstants.INVALID) ||
        //                             resultPKI[0].equals(AppConstants.ERROR))) {
        //                            jsonObj = new JsonObject();
        //                            if (resultPKI[0].equalsIgnoreCase(AppConstants.INVALID))
        //                                jsonObj.addProperty(AppConstants.ERROR,
        //                                                    "TTSP-ERROR-0002");
        //                            else
        //                                jsonObj.addProperty(AppConstants.ERROR,
        //                                                    "TTSP-ERROR-0003");
        //                            out.println(jsonObj.toString());
        //                        } else {
        //                            i = cotDAO.update(cotVO);
        //                            cotVO.setId(cotForm.getCot_id());
        //                            cotVO.setNguoi_ks(userId);
        //                            cotVO.setNgay_ks(new Date());
        //                            cotVO.setLydo_cot(cotForm.getLydo_cot());
        //                            cotVO.setChuky_ktt(request.getParameter("signature"));
        //                            cotVO.setTgian_cot_cu(convertCOT(cotForm.getF_ngay_cot(),
        //                                                             cotForm.getGio_cot_cu()));
        //                            cotVO.setTgian_cot_moi(convertCOT(cotForm.getF_ngay_cot(),
        //                                                              cotForm.getGio_cot_moi()));
        //
        //                            //send 299
        //                            String user_id =
        //                                session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
        //                            Send299 send = new Send299(conn);
        //                            String mstID =
        //                                send.sendMessage(cotVO, null, null, user_id);
        //                            if (mstID != null) {
        //                                cotVO.setMsg_id(mstID);
        //                                i = cotDAO.update(cotVO);
        //
        //                                //update database thamso cot
        //                                if (i > 0) {
        //                                    TSoCutOfTimeDAO tdao =
        //                                        new TSoCutOfTimeDAO(conn);
        //                                    TSoCutOfTimeVO vo = new TSoCutOfTimeVO();
        //                                    vo.setCut_of_time(cotForm.getGio_cot_moi());
        //                                    vo.setSo_yc_cot(cotForm.getCot_id());
        //                                    String where =
        //                                        " WHERE ngay_gd =trunc(sysdate)";
        //
        //                                    if (cotForm.getLoai_cot().equals("01")) {
        //                                        Vector paramTemp = new Vector();
        //                                        paramTemp.add(new Parameter(cotForm.getId(),
        //                                                                    true));
        //                                        CutOffTimeVO cotTempVO =
        //                                            cotDAO.getCTO(" a.id = ? ",
        //                                                          paramTemp);
        //                                        String kb_huyen =
        //                                            "'" + cotTempVO.getMa_kb_huyen().trim().replace(",",
        //                                                                                            "','") +
        //                                            "'";
        //                                        where +=
        //                                                " AND ma_nh_kb in (" + kb_huyen +
        //                                                ")";
        //                                    }
        //                                    i = tdao.updateCondition(vo, where, null);
        //                                    if (i > 0) {
        //                                        //insert for tcs
        //                                        CutOffTimeVO voTemp =
        //                                            getCutOffTimeVO(cotForm.getId(),
        //                                                            cotDAO);
        //                                        cotVO.setNgay_lap(new Date());
        //                                        cotVO.setDong_y(AppConstants.ACTION_YES);
        //                                        cotVO.setCot_id(cotForm.getId());
        //                                        cotVO.setMa_kb_huyen(voTemp.getMa_kb_huyen());
        //                                        cotVO.setNhkb_nhan(voTemp.getNhkb_chuyen());
        //                                        cotVO.setNhkb_chuyen(AppConstants.KBNN_SGD_BANK_CODE);
        //                                        cotVO.setLoai_cot(cotForm.getLoai_cot());
        //                                        cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_DA_DUYET_DONGY);
        //                                        //send message to tcs
        //                                        mstID =
        //                                                send.sendMessage(cotVO,
        //                                                                 "TCS_KBA", null, user_id);
        //                                        cotVO.setMsg_id(mstID);
        //                                        cotDAO.insert(cotVO, "299");
        //                                    }
        //                                }
        //                            }
        //                            saveVisitLog(conn, session,
        //                                         "SYS.QLY_COT.YC_NH_DUYET_DAYLAI",
        //                                         "Duyet");
        //                        }
        //                    }
        //                } else if (action != null &&
        //                           action.equalsIgnoreCase(AppConstants.ACTION_REJECT)) {
        //                    if (!checkPermissionOnFunction(request,
        //                                                   "SYS.QLY_COT.YC_NH_DUYET_DAYLAI")) {
        //                        jsonObj = new JsonObject();
        //                        jsonObj.addProperty(AppConstants.ERROR,
        //                                            AppConstants.FAILURE);
        //                        out.println(jsonObj.toString());
        //                    }
        //                    if (jsonObj == null) {
        //                        cotVO = new CutOffTimeVO();
        //                        cotVO.setId(cotForm.getId());
        //                        cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_KHONG_DUYET_DONGY);
        //                        i = cotDAO.update(cotVO);
        //                        cotVO.setLydo_daylai(cotForm.getLydo_daylai());
        //                        cotVO.setId(cotForm.getCot_id());
        //                        cotVO.setNguoi_ks(userId);
        //                        cotVO.setNgay_ks(new Date());
        //                        i = cotDAO.update(cotVO);
        //                        saveVisitLog(conn, session,
        //                                     "SYS.QLY_COT.YC_NH_DUYET_DAYLAI", "");
        //
        //                    }
        //                } else {
        //                    jsonObj = new JsonObject();
        //                    jsonObj.addProperty(AppConstants.ERROR,
        //                                        AppConstants.FAILURE);
        //                    out.println(jsonObj.toString());
        //                }
        //                conn.commit();
        //                if (i > 0) {
        //                    request.setAttribute("getJson", true);
        //                    list(mapping, form, request, response);
        //                }
        //
        //            } else {
        //                jsonObj = new JsonObject();
        //                jsonObj.addProperty(AppConstants.ERROR, "TTSP-ERROR-0001");
        //                out.println(jsonObj.toString());
        //            }
        //        } catch (Exception e) {
        //            conn.rollback();
        //            //            e.printStackTrace();
        //            jsonObj = new JsonObject();
        //            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
        //            out.println(jsonObj.toString());
        //        } finally {
        //            if (out != null) {
        //                out.flush();
        //                out.close();
        //            }
        //            close(conn);
        //        }
        //        if (!response.isCommitted())
        //            return mapping.findForward(AppConstants.SUCCESS);
        //        else
        return null;
    }

    //    private CutOffTimeVO getCutOffTimeVO(Long id,
    //                                         CutOffTimeDAO cotDAO) throws Exception {
    //        CutOffTimeVO voTemp = null;
    //        String whereClause = " a.id = ? ";
    //        Vector vparam = new Vector();
    //        vparam.add(new Parameter(id, true));
    //        try {
    //            voTemp = cotDAO.getCTO(whereClause, vparam);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        return voTemp;
    //    }

    //    private Date convertCOT(String ngay_lap, String gio_cot) {
    //        String fullDateCOT = ngay_lap.concat(" ").concat(gio_cot);
    //        Date result = StringUtil.StringToDate(fullDateCOT, "dd/MM/yyyy HH:mm");
    //        return result;
    //    }


    @Override
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            CutOffTimeForm cotForm = (CutOffTimeForm)form;
            CutOffTimeVO cotVO = new CutOffTimeVO();
            HttpSession session = request.getSession();
            String ma_nsd =
                session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
            String id_nsd =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();

            //B1 cap nhat trang thai dong y

            CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
            cotVO.setId(cotForm.getId());
            cotVO.setLydo_cot(cotForm.getLydo_cot());
            cotVO.setMa_nsd(ma_nsd);
            cotVO.setMa_nsd_ks(ma_nsd);
            cotVO.setNguoi_lap(id_nsd);
            cotVO.setNguoi_ks(id_nsd);
            cotVO.setTrang_thai(cotForm.getTrang_thai());
            cotVO.setDong_y(cotForm.getDong_y());

            cotVO.setLoai_cot(cotForm.getLoai_cot());
            cotVO.setNew_cot(cotForm.getGio_cot_moi());
            cotVO.setCur_cot(cotForm.getGio_cot_cu());
            cotVO.setTn_cu(cotForm.getTn_cu());
            cotVO.setTn_moi(cotForm.getTn_moi());

            TTSPUtils ttspUtils = new TTSPUtils(conn);
            String strMTID = ttspUtils.getMT_ID("299", "");
            cotVO.setCot_id(Long.parseLong(strMTID));


            Send299 send = new Send299(conn);
            if ("Y".equals(cotForm.getDong_y())) {
                //Gui msg tra loi di NH
                cotVO.setDong_y("Y");
                //Gui msg di NH
                String msgID = send.sendMessage(cotVO, null);
                cotVO.setMsg_reply_bank(msgID);

                if (msgID != null || !"".equals(msgID)) {
                    //Gui msg di PHT de PHT noi gio trong TH dong y
                    String msgIDPHT = send.sendMessage(cotVO, "TCS_KBA");
                    cotVO.setMsg_reply_tcs(msgIDPHT);
                    //Cap nhat gio COT
                    int iUpdate = updateCOT(conn, cotForm);
                    if (iUpdate < 1) {
                        conn.rollback();
                        throw new Exception("Khong cap nhat duoc gio COT");
                    }
                } else {
                    conn.rollback();
                    throw new Exception("Khong gui duoc msg di NH");
                }
            } else {
                //Gui msg tra loi di NH
                cotVO.setDong_y("N");
                String msgID = send.sendMessage(cotVO, null);
                cotVO.setMsg_reply_bank(msgID);
            }
            if (cotDAO.update(cotVO) > 0) {
                conn.commit();

                //Chinh 25/04/2015 em lam tam cho no sendredirect ve ycaunoigioNH
                response.sendRedirect("ycnoigioNH.do");
            } else {
                throw new Exception("Thuc hien khong thanh cong. Khong cap nhat duoc ban ghi nao.");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }

    @Override
    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            CutOffTimeForm cotForm = (CutOffTimeForm)form;
            CutOffTimeVO cotVO = new CutOffTimeVO();
            HttpSession session = request.getSession();
            String ma_nsd = session.getAttribute("ma_nsd").toString();

            //B1 cap nhat trang thai dong y
            CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
            cotVO.setId(cotForm.getId());
            cotVO.setLydo_cot(cotForm.getLydo_cot());
            cotVO.setMa_nsd(ma_nsd);
            cotVO.setNguoi_lap(ma_nsd);
            cotVO.setNguoi_ks(ma_nsd);
            cotVO.setTrang_thai("07");
            if (cotDAO.update(cotVO) > 0) {
                conn.commit();
                //Chinh 25/04/2015 em lam tam cho no sendredirect ve ycaunoigioNH
                response.sendRedirect("ycnoigioNH.do");
            } else {
                throw new Exception("Update l?i.");
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        //Chinh 25/04/2015 em lam tam cho no sendredirect ve ycaunoigioNH
        response.sendRedirect("ycnoigioNH.do");
        return mapping.findForward("success");
    }

    private int updateCOT(Connection conn,
                          CutOffTimeForm cotForm) throws Exception {
        int i = 0;
        TSoCutOfTimeDAO tdao = new TSoCutOfTimeDAO(conn);
        CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);

        Vector paramTemp = new Vector();
        paramTemp.add(new Parameter(cotForm.getId(), true));
        CutOffTimeVO cotTempVO = cotDAO.getCTO(" a.id = ? ", paramTemp);

        TSoCutOfTimeVO vo = new TSoCutOfTimeVO();
        vo.setCut_of_time(cotTempVO.getCot_moi());
        vo.setSo_yc_cot(cotForm.getId());
        String where = " WHERE ngay_gd =trunc(sysdate) ";

        if ("01".equalsIgnoreCase(cotTempVO.getLoai_cot())) {
            //Kiem tra dieu kien tra loi dong y
            String strCheck =
                cotDAO.ktraDKienNoiGio(cotTempVO.getMa_nh().trim(),
                                       cotTempVO.getMa_kb_huyen().trim(),
                                       cotTempVO.getCot_moi(), "TLDV");
            if (!strCheck.equals("00")) {
                throw new Exception(strCheck);
            }
            //Update gio COT
            String strKBList =
                "'" + cotTempVO.getMa_kb_huyen().trim().replace(",", "','") +
                "'";
            String strNHList =
                "'" + cotTempVO.getMa_nh().trim().replace(",", "','") + "'";
            where +=
                    " AND ma_nh_kb in (" + strKBList + ") AND ma_nh in (" + strNHList +
                    ") ";

            i = tdao.updateCondition(vo, where, null);
        } else if ("00".equalsIgnoreCase(cotTempVO.getLoai_cot())) {
            //Kiem tra dieu kien tra loi dong y
            String strCheck =
                cotDAO.ktraDKienNoiGio(cotTempVO.getNhkb_chuyen(), null,
                                       cotTempVO.getCot_moi(), "TLHT");
            if (!strCheck.equals("00")) {
                throw new Exception(strCheck);
            }
            //Update gio COT
            String strMaNH = cotTempVO.getNhkb_chuyen();
            where += " AND ma_nh like '__" + strMaNH.substring(3, 3) + "%'";

            i = tdao.updateCondition(vo, where, null);
        } else {
            //Cap nhat gio truyen nhan
            String strSQLCapNhatGioTN =
                "UPDATE sp_thamso_ht SET giatri_ts = '" + cotForm.getTn_moi() +
                "' WHERE ten_ts in ('CUT_OF_TIME_TGIAN_TRE','THOI_GIAN_TRE_SAU_CUTOFTIME') AND TO_NUMBER(giatri_ts) < " +
                cotForm.getTn_moi();
            conn.createStatement().executeUpdate(strSQLCapNhatGioTN);
            i = 1;
        }

        return i;
    }
}
