package com.seatech.ttsp.cutofftime.action;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.cutofftime.CutOffTimeDAO;
import com.seatech.ttsp.cutofftime.CutOffTimeVO;
import com.seatech.ttsp.cutofftime.form.CutOffTimeForm;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeDAO;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeVO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.proxy.giaodien.Send299;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class CutOffTimeKBAction extends AppAction {
    private static String SGD = "0003";
    private static String CAP_TINH = "5";

    public CutOffTimeKBAction() {
        super();
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
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        Collection lstKBTinh = null;
        Collection lstKBHuyen = null;
        Collection colDMNH = null;
        String strKBHuyen = null;
        String whereClause = "";
        CutOffTimeForm cotForm = (CutOffTimeForm)form;
        try {
            //            if (!checkPermissionOnFunction(request, "SYS.QLY_COT.THEMMOI")) {
            //                return mapping.findForward(AppConstants.FAILURE);
            //            }
            HttpSession session = request.getSession();
            conn = getConnection(request);
            DMKBacDAO dao = new DMKBacDAO(conn);
            // danh sach kb tinh
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            if (request.getParameter("nd") != null) {
                whereClause = " AND substr(d.ma_nh,3,3)=?";
                Parameter p = new Parameter(cotForm.getMa_nh(), true);
                Vector v = new Vector();
                v.add(p);
                if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                    lstKBTinh = dao.getDMucKB_cha(whereClause, v);
                } else {
                    whereClause += " AND a.ma='0003' ";
                    lstKBTinh = dao.getDMucKB_cha(whereClause, v);
                }
                Type listType = new TypeToken<Collection<DMKBacVO>>() {
                }.getType();
                String strJson = new Gson().toJson(lstKBTinh, listType);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
            } else {

                String currentDate =
                    StringUtil.DateToString(new Date(), "dd/MM/yyyy");
                cotForm.setF_ngay_lap(currentDate);
                Long userid =
                    (Long)session.getAttribute(AppConstants.APP_USER_ID_SESSION);
                cotForm.setTen_nguoi_lap((String)session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
                cotForm.setNguoi_lap(String.valueOf(userid));
                cotForm.setF_ngay_cot(currentDate);
                cotForm.setGio_cot_cu(null);
                cotForm.setGio_cot_moi(null);

                TTThanhToanDAO ttttDAO = new TTThanhToanDAO(conn);
                colDMNH = ttttDAO.getDMucNH(null, null);
                //Danh sach gio cot cu
                //Check thoi gian tao dien khong dc qua cot cu min
                TSoCutOfTimeDAO spCot = new TSoCutOfTimeDAO(conn);
                Vector vector = new Vector();
                DateParameter params =
                    new DateParameter(StringUtil.StringToDate(currentDate,
                                                              "dd/MM/yyyy"),
                                      true);
                vector.add(params);
                Collection listCOTOld =
                    spCot.getTSoCOTList(" a.ngay_gd=?", vector);
                if (listCOTOld != null && listCOTOld.size() > 0) {
                    Type listTypeCOT =
                        new TypeToken<Collection<TSoCutOfTimeVO>>() {
                    }.getType();
                    String strColOld =
                        new Gson().toJson(listCOTOld, listTypeCOT);
                    request.setAttribute("colCOT", strColOld);
                }
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
                Type listType = new TypeToken<Collection<DMKBacVO>>() {
                }.getType();
                strKBHuyen = new Gson().toJson(lstKBHuyen, listType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (request.getParameter("nd") == null) {
                request.setAttribute("colDMNH", colDMNH);
                request.setAttribute("lstKBTinh", lstKBTinh);
                request.setAttribute("lstKBHuyen", strKBHuyen);
            }
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
        Connection conn = null;
        String whereClause = "";
        Vector params = null;
        Collection lstKBHuyen = null;
        String strJson = "";
        try {
            conn = getConnection(request);
            String ma_kb_huyen = request.getParameter("ma_kb_huyen");
            String ma_dv = request.getParameter("ma_dv");
            if (ma_kb_huyen != null && !ma_kb_huyen.equals("")) {
                TSoCutOfTimeDAO dao = new TSoCutOfTimeDAO(conn);
                whereClause =
                        " a.ngay_gd=? and a.ma_nh_kb=?  and substr(a.ma_nh,3,3)=?";
                params = new Vector();
                params.add(new DateParameter(StringUtil.StringToDate(StringUtil.DateToString(new Date(),
                                                                                             "dd/MM/yyyy"),
                                                                     "dd/MM/yyyy"),
                                             true));
                params.add(new Parameter(ma_kb_huyen, true));
                params.add(new Parameter(ma_dv, true));

                TSoCutOfTimeVO vo = dao.getTSoCTO(whereClause, params);
                JsonObject jobj = new JsonObject();
                if (vo != null) {
                    jobj.addProperty("cut_of_time", vo.getCut_of_time());
                } else
                    jobj.addProperty("cut_of_time", "");
                strJson = jobj.toString();
            } else {
                DMKBacDAO dmKBDAO = new DMKBacDAO(conn);
                whereClause = " ma_cha =?";
                params = new Vector();
                params.add(new Parameter(request.getParameter("ma_kb_tinh"),
                                         true));
                lstKBHuyen = dmKBDAO.getDMNHKBList(whereClause, params);
                Type listType = new TypeToken<Collection<DMKBacVO>>() {
                }.getType();
                strJson = new Gson().toJson(lstKBHuyen, listType);
            }
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            close(conn);
        }
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

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        Connection conn = null;
        JsonObject jsonObj = null;
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        //        conn = getConnection(request);
        try {
            //            if (!checkPermissionOnFunction(request, "SYS.QLY_COT.THEMMOI")) {
            //                jsonObj = new JsonObject();
            //                jsonObj.addProperty(AppConstants.ERROR, AppConstants.FAILURE);
            //            }
            HttpSession session = request.getSession();
            if (session != null && jsonObj == null) {
                CutOffTimeForm cotForm = (CutOffTimeForm)form;
                conn = getConnection(request);
                CutOffTimeVO cotVO = new CutOffTimeVO();
                CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
                BeanUtils.copyProperties(cotVO, cotForm);
                //                TSoCutOfTimeDAO tsoCOTdao = new TSoCutOfTimeDAO(conn);
                //Manh-20150420*****************************************************************
                //                String messageTime = null;
                String strKTraDKienNoiGio = "00";

                //                if (cotForm.getGio_cot_cu() == null ||
                //                    "".equals(cotForm.getGio_cot_cu())) {
                //                    if (cotForm.getMa_kb_huyen() == null ||
                //                        "".equals(cotForm.getMa_kb_huyen())) {
                if ("00".equals(cotForm.getLoai_cot())) {
                    //                        String strWhere = " AND ma_nh like '__"+cotForm.getMa_nh()+"%' AND ngay_gd like SYSDATE ";
                    //                        TSoCutOfTimeVO cotVOTemp = tsoCOTdao.getMAXTSoCOT(strWhere, null);
                    //                        String sysTime = cotVOTemp.getCut_of_time();
                    //                        messageTime = checkTime(sysTime,cotForm.getGio_cot_moi());
                    strKTraDKienNoiGio =
                            cotDAO.ktraDKienNoiGio(cotForm.getMa_nh(), "",
                                                   cotForm.getGio_cot_moi(),
                                                   "YCHT");
                } else if ("01".equals(cotForm.getLoai_cot())) {
                    //                        String kb_huyen = "'" + cotForm.getMa_kb_huyen().trim().replace(",","','") + "'";
                    //                        String where = " AND MA_NH_KB IN (" + kb_huyen + ") AND ngay_gd like SYSDATE ";
                    //                        TSoCutOfTimeVO cotVOTemp = tsoCOTdao.getMAXTSoCOT(where, null);
                    //                        String sysTime = cotVOTemp.getCut_of_time();
                    //                        messageTime = checkTime(sysTime,cotForm.getGio_cot_moi());
                    String strKBacList = "";
                    if (cotForm.getMa_kb_huyen() != null &&
                        !"".equals(cotForm.getMa_kb_huyen())) {
                        strKBacList = cotForm.getMa_kb_huyen().trim();
                    } else {
                        strKBacList = cotForm.getMa_kb_huyen_list().trim();
                    }
                    strKTraDKienNoiGio =
                            cotDAO.ktraDKienNoiGio(cotForm.getMa_nh(),
                                                   strKBacList,
                                                   cotForm.getGio_cot_moi(),
                                                   "YCDV");
                } else {
                    strKTraDKienNoiGio =
                            cotDAO.ktraDKienNoiGio(cotForm.getMa_nh(),
                                                   cotForm.getMa_kb_huyen().trim(),
                                                   cotForm.getGio_cot_moi(),
                                                   "YCTN");
                    //Lay gio TN cu
                    ThamSoHThongDAO tshtDAO = new ThamSoHThongDAO(conn);
                    ThamSoHThongVO tshtVO =
                        tshtDAO.getThamSo(" a.ten_ts = 'CUT_OF_TIME_TGIAN_TRE' ",
                                          null);
                    cotVO.setTn_cu(tshtVO.getGiatri_ts());
                }
                //                } else {
                //                    messageTime = checkTime(cotForm.getGio_cot_cu(),cotForm.getGio_cot_moi());
                //                }
                //                if (messageTime == null) {
                //Manh-20150420*****************************************************************
                if ("00".equals(strKTraDKienNoiGio)) {
                    cotVO.setNhkb_chuyen(AppConstants.KBNN_TW_BANK_CODE);
                    cotVO.setNhkb_nhan(cotForm.getMa_nh()); //note
                    cotVO.setNgay_lap(new Date());
                    if (cotForm.getGio_cot_cu() != null &&
                        !cotForm.getGio_cot_cu().equals("")) {
                        cotVO.setTgian_cot_cu(convertCOT(cotForm.getF_ngay_cot(),
                                                         cotForm.getGio_cot_cu()));
                    }
                    if (cotForm.getGio_cot_moi() != null &&
                        !cotForm.getGio_cot_moi().equals("")) {
                        cotVO.setTgian_cot_moi(convertCOT(cotForm.getF_ngay_cot(),
                                                          cotForm.getGio_cot_moi()));
                    }
                    cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_CHO_DUYET);
                    //20150804
                    String strCOTList = "";
                    String strNHList = "";
                    String strTenKBList = "";
                    Collection listKBNoiGio = null;
                                    String listKBHuyen = cotVO.getMa_kb_huyen();
                                    if (listKBHuyen != null) {
                                        listKBHuyen =
                                                "'" + listKBHuyen.trim().replace(",", "','") + "'";
                                        TSoCutOfTimeDAO tSoCOTDAO = new TSoCutOfTimeDAO(conn);
                                        String temp[] = cotVO.getNhkb_nhan().trim().split("");
                                        String ma_dv = "";
                                        if(cotVO.getNhkb_nhan().length() == 8){
                                          ma_dv = temp[3] + temp[4] + temp[5];
                                        }else{
                                          ma_dv = cotVO.getNhkb_nhan();
                                        }
                                        String where =
                                            " a.ma_nh_kb in (" + listKBHuyen + ") " + " and a.ngay_gd = trunc(sysdate) and a.ma_nh like '__" +
                                            ma_dv + "%'";
                                        listKBNoiGio = tSoCOTDAO.getTSoCOTList(where, null);
                                        if(listKBNoiGio != null){
                                          Iterator iter = listKBNoiGio.iterator();
                                          while(iter.hasNext()){
                                            TSoCutOfTimeVO tsCOT = (TSoCutOfTimeVO)iter.next();
                                              if(!"".equals(strCOTList)){
                                                strCOTList += ","+tsCOT.getCut_of_time();
                                              }else{
                                                strCOTList = tsCOT.getCut_of_time();
                                              }
                                              if(!"".equals(strNHList)){
                                                strNHList += ","+tsCOT.getMa_nh();
                                              }else{
                                                strNHList = tsCOT.getMa_nh();
                                              }
                                            if(!"".equals(strTenKBList)){
                                              strTenKBList += "@ttsp@"+tsCOT.getTen_nh_kb();
                                            }else{
                                              strTenKBList = tsCOT.getTen_nh_kb();
                                            }
                                          }
                                        }
                                    }
                  cotVO.setCot_cu_list(strCOTList);
                  cotVO.setMa_nh(strNHList);
                  cotVO.setTen_kb(strTenKBList);
                    //20150804
                    String strReturn = cotDAO.insert(cotVO, "299");
                    if (strReturn != null && !strReturn.equals("")) {
                        //set log
                        saveVisitLog(conn, session, "SYS.QLY_COT.THEMMOI",
                                     "Them moi thanh cong");
                        jsonObj = new JsonObject();
                        jsonObj.addProperty(AppConstants.SUCCESS, strReturn);
                    }
                    conn.commit();
                } else {
                    jsonObj = new JsonObject();
                    jsonObj.addProperty(AppConstants.ERROR,
                                        strKTraDKienNoiGio);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
        } finally {
            close(conn);
            if (out != null) {
                out.println(jsonObj.toString());
                out.flush();
                out.close();
            }
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
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        //        if (!checkPermissionOnFunction(request, "SYS.QLY_COT.DANHSACH")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Collection colCOTDi = null;
        String whereClause = "";
        Vector params = null;
        Connection conn = null;
        String action = "";
        boolean flag = true;
        String strListRole = "";
        Collection colDMNH = null;
        Collection lstKBTinh = null;
        Collection lstKBHuyen = null;
        String strKBHuyen = "";
        try {
            HttpSession session = request.getSession();
            action = request.getParameter(AppConstants.REQUEST_ACTION);
            if (session != null) {
                if (conn == null || conn.isClosed())
                    conn = getConnection(request);
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
                Type listType1 = new TypeToken<Collection<DMKBacVO>>() {
                }.getType();
                strKBHuyen = new Gson().toJson(lstKBHuyen, listType1);

                strListRole =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                params = new Vector();

                if ((strListRole.toUpperCase().indexOf("CB-TTTT") >= 0 &&
                     strListRole.toUpperCase().indexOf("CBPT-TTTT") == -1) &&
                    flag) {
                    whereClause =
                            "a.nhkb_chuyen=? and (a.ngay_lap>=trunc(sysdate) OR a.ngay_ks>=trunc(sysdate)) and a.trang_thai in ('" +
                            AppConstants.TRANG_THAI_COT_CHO_DUYET + "','" +
                            AppConstants.TRANG_THAI_COT_HUY + "','" +
                            AppConstants.TRANG_THAI_COT_DA_DUYET + "','" +
                            AppConstants.TRANG_THAI_COT_DAY_LAI +
                            "','18','19','08','09','10')";
                    /*
                     * -18: Da gui dien YC sang NH
                     * -19: Gui dien YC sang NH loi
                     * -08: Nh da dong y
                     * -09: NH da dong y nhung ve KB qua gio, ko duoc chap nhan noi gio
                     * -10: NH ko dong y noi gio
                     */
                    params = new Vector();
                    params.add(new Parameter(AppConstants.KBNN_TW_BANK_CODE,
                                             true));
                } else if ((strListRole.toUpperCase().indexOf("CB-TTTT") ==
                            -1 &&
                            strListRole.toUpperCase().indexOf("CBPT-TTTT") >=
                            0) && flag) {
                    whereClause =
                            "a.nhkb_chuyen=? and (a.ngay_lap>=trunc(sysdate) OR a.ngay_ks>=trunc(sysdate)) and a.trang_thai in ('" +
                            AppConstants.TRANG_THAI_COT_CHO_DUYET + "','" +
                            AppConstants.TRANG_THAI_COT_HUY + "','" +
                            AppConstants.TRANG_THAI_COT_DA_DUYET + "','" +
                            AppConstants.TRANG_THAI_COT_DAY_LAI +
                            "','18','19','08','09','10')";
                    /*
                                       * -18: Da gui dien YC sang NH
                                       * -19: Gui dien YC sang NH loi
                                       * -08: Nh da dong y
                                       * -09: NH da dong y nhung ve KB qua gio, ko duoc chap nhan noi gio
                                       * -10: NH ko dong y noi gio
                                       */
                    /*whereClause =
                            " a.nhkb_chuyen=? " + " AND  ((a.ngay_lap>=trunc(sysdate) and a.trang_thai='" +
                            AppConstants.TRANG_THAI_COT_CHO_DUYET + "') " +
                            "   OR ( a.ngay_ks>=trunc(sysdate) AND (a.trang_thai='" +
                            AppConstants.TRANG_THAI_COT_DA_DUYET +
                            "' OR trang_thai='" +
                            AppConstants.TRANG_THAI_COT_DAY_LAI + "')))";*/
                    params = new Vector();
                    params.add(new Parameter(AppConstants.KBNN_TW_BANK_CODE,
                                             true));
                }

                CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
                colCOTDi = cotDAO.getCOTList(whereClause, params);
                //response danh sach dien tra soat
                if (request.getAttribute("getJson") != null ||
                    (action != null &&
                     (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                      action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                    Type listType2 =
                        new TypeToken<Collection<CutOffTimeVO>>() {
                    }.getType();
                    String strJson = new Gson().toJson(colCOTDi, listType2);
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
            e.printStackTrace();
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
//        Collection listKBNoiGio = null;
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        try {
            //            if (!checkPermissionOnFunction(request, "SYS.QLY_COT.DANHSACH")) {
            //                jsonObj = new JsonObject();
            //                jsonObj.addProperty(AppConstants.ERROR, AppConstants.FAILURE);
            //            }
            //            if (jsonObj == null) {
            conn = getConnection(request);
            String action = request.getParameter(AppConstants.REQUEST_ACTION);
            if (action != null &&
                AppConstants.ACTION_FILL_DATA_TO_FORM.equalsIgnoreCase(action)) {
                String id = request.getParameter("id");
                CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
                String whereClause = "  a.id=? ";
                params = new Vector();
                params.add(new Parameter(id, true));
                CutOffTimeVO cotVO = cotDAO.getCTO(whereClause, params);
//                String listKBHuyen = cotVO.getMa_kb_huyen();
//                String listCOTCu = cotVO.getCot_cu_list() == null?"":cotVO.getCot_cu_list();
//                if (listKBHuyen != null) {
//                    listKBHuyen =
//                            "'" + listKBHuyen.trim().replace(",", "','") + "'";
//                    TSoCutOfTimeDAO tSoCOTDAO = new TSoCutOfTimeDAO(conn);
//                    String temp[] = cotVO.getNhkb_nhan().trim().split("");
//                    String ma_dv = temp[3] + temp[4] + temp[5];
//                    String where =
//                        " a.ma_nh_kb in (" + listKBHuyen + ") " + " and a.ngay_gd = trunc(sysdate) and a.ma_nh like '__" +
//                        ma_dv + "%'";
//                    listKBNoiGio = tSoCOTDAO.getTSoCOTList(where, null);
//                    cotVO.setList_ma_nh_kb_huyen(listKBNoiGio);
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

        CutOffTimeVO cotVO = null;
        int i = 0;
        Connection conn = null;
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        JsonObject jsonObj = null;
        PrintWriter out = response.getWriter();
        try {

            CutOffTimeForm cotForm = (CutOffTimeForm)form;
            HttpSession session = request.getSession();
            if (session == null)
                return mapping.findForward(AppConstants.FAILURE);
            conn = getConnection(request);
            CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
            //select before for update
            String stateReq = cotForm.getTrang_thai();
            String stateAfterSelect =
                TTSPUtils.getStateBUCOT(request, response, conn);
            String userId =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            if ((stateReq != null && stateAfterSelect != null) &&
                stateReq.equalsIgnoreCase(stateAfterSelect)) {
                String action =
                    request.getParameter(AppConstants.REQUEST_ACTION);
                //huy
                if (action != null &&
                    AppConstants.ACTION_CANCEL.equals(action)) {

                    if (jsonObj == null) {
                        if (cotForm.getTrang_thai().equalsIgnoreCase(AppConstants.TRANG_THAI_COT_CHO_DUYET) &&
                            (cotForm.getNguoi_ks() == null ||
                             cotForm.getNguoi_ks().equals(""))) {
                            //delete
                            i = cotDAO.delete(cotForm.getId());
                        } else {
                            //update
                            cotVO = new CutOffTimeVO();
                            cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_HUY);
                            cotVO.setId(cotForm.getId());
                            i = cotDAO.update(cotVO);
                        }
                        saveVisitLog(conn, session, "SYS.QLY_COT.HUY", "");
                    }

                } else if (action != null &&
                           AppConstants.ACTION_EDIT.equals(action)) {

                    if (jsonObj == null) {
                        cotVO = new CutOffTimeVO();
                        BeanUtils.copyProperties(cotVO, cotForm);
                        cotVO.setNhkb_chuyen(AppConstants.KBNN_TW_BANK_CODE);
                        cotVO.setNhkb_nhan(cotForm.getMa_nh());
                        cotVO.setNguoi_lap(userId);
                        cotVO.setNgay_lap(new Date());
                        if (cotForm.getGio_cot_cu() != null &&
                            !cotForm.getGio_cot_cu().equals("")) {
                            cotVO.setTgian_cot_cu(convertCOT(cotForm.getF_ngay_cot(),
                                                             cotForm.getGio_cot_cu()));
                        }
                        if (cotForm.getGio_cot_moi() != null &&
                            !cotForm.getGio_cot_moi().equals("")) {
                            cotVO.setTgian_cot_moi(convertCOT(cotForm.getF_ngay_cot(),
                                                              cotForm.getGio_cot_moi()));
                        }
                        cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_CHO_DUYET);
                        i = cotDAO.update(cotVO);
                        saveVisitLog(conn, session, "SYS.QLY_COT.SUA", "");
                    }
                } else if (action != null &&
                           AppConstants.ACTION_APPROVED.equalsIgnoreCase(action)) {
                    if (!checkPermissionOnFunction(request,
                                                   "SYS.QLY_COT.DUYET")) {
                        jsonObj = new JsonObject();
                        jsonObj.addProperty(AppConstants.ERROR,
                                            AppConstants.FAILURE);
                    }
                    if (jsonObj == null) {
                        cotVO = new CutOffTimeVO();
                        cotVO.setId((cotForm.getId() != null &&
                                     !cotForm.getId().equals("")) ?
                                    cotForm.getId() : null);
                        cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_DA_DUYET);
                        String strWSDL = getThamSoHThong("WSDL_PKI", session);
                        String strAppID = getThamSoHThong("APP_ID", session);
                        PKIService pkiService = new PKIService(strWSDL);
                        String userName =
                            session.getAttribute(AppConstants.APP_DOMAIN_SESSION).toString() +
                            "\\" +
                            session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                        String strSign = request.getParameter("signature");
                        String strContentData =
                            request.getParameter("noi_dung_ky");
                        String strCertSerial =
                            request.getParameter("certSerial");
                        String[] resultPKI =
                            pkiService.VerifySignature(userName, strCertSerial,
                                                       strContentData, strSign,
                                                       strAppID);
                        if (resultPKI != null &&
                            (resultPKI[0].equals(AppConstants.INVALID) ||
                             resultPKI[0].equals(AppConstants.ERROR))) {
                            jsonObj = new JsonObject();
                            if (resultPKI[0].equalsIgnoreCase(AppConstants.INVALID))
                                jsonObj.addProperty(AppConstants.ERROR,
                                                    "TTSP-ERROR-0002");
                            else
                                jsonObj.addProperty(AppConstants.ERROR,
                                                    "TTSP-ERROR-0003");
                        } else {
                            cotVO.setChuky_ktt(request.getParameter("signature"));
                        }
                        if (jsonObj == null) {
                            cotVO.setNguoi_ks(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                            String user_id =
                                session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                            cotVO.setNgay_ks(new Date());
                            String message = "00";
                            //                            if (cotForm.getMa_kb_huyen() == null ||
                            //                                  "".equals(cotForm.getMa_kb_huyen())) {
                            if ("00".equals(cotForm.getLoai_cot())) {
                                message =
                                        cotDAO.ktraDKienNoiGio(cotForm.getMa_nh(),
                                                               "",
                                                               cotForm.getGio_cot_moi(),
                                                               "YCHT");
                            } else if ("01".equals(cotForm.getLoai_cot())) {
                                String strKBacList = "";
                                if (cotForm.getMa_kb_huyen() != null &&
                                    !"".equals(cotForm.getMa_kb_huyen())) {
                                    strKBacList =
                                            cotForm.getMa_kb_huyen().trim();
                                } else {
                                    strKBacList =
                                            cotForm.getMa_kb_huyen_list().trim();
                                }
                                message =
                                        cotDAO.ktraDKienNoiGio(cotForm.getMa_nh(),
                                                               strKBacList,
                                                               cotForm.getGio_cot_moi(),
                                                               "YCDV");
                            } else if ("02".equals(cotForm.getLoai_cot())) {
                                message =
                                        cotDAO.ktraDKienNoiGio(cotForm.getMa_nh(),
                                                               cotForm.getMa_kb_huyen(),
                                                               cotForm.getGio_cot_moi(),
                                                               "YCTN");
                            }
                            if ("00".equals(message)) {
                                Send299 send = new Send299(conn);
                                cotVO.setDong_y(null);
                                cotVO.setMa_nsd_ks(user_id);
                                String mstID = send.sendMessage(cotVO, null);
                                if (mstID != null) {
                                    cotVO.setMsg_id(mstID);
                                    i = cotDAO.update(cotVO);
                                }
                            } else {
                                jsonObj = new JsonObject();
                                jsonObj.addProperty(AppConstants.ERROR,
                                                    message);
                            }
                            saveVisitLog(conn, session, "SYS.QLY_COT.DUYET",
                                         "Duyet");
                        }
                    }

                } else if (action != null &&
                           action.equalsIgnoreCase(AppConstants.ACTION_REJECT)) {
                    if (!checkPermissionOnFunction(request,
                                                   "SYS.QLY_COT.DAYLAI")) {
                        jsonObj = new JsonObject();
                        jsonObj.addProperty(AppConstants.ERROR,
                                            AppConstants.FAILURE);
                    }
                    if (jsonObj == null) {
                        cotVO = new CutOffTimeVO();
                        cotVO.setId((cotForm.getId() != null &&
                                     !cotForm.getId().equals("")) ?
                                    cotForm.getId() : null);
                        cotVO.setLydo_daylai(cotForm.getLydo_daylai());
                        cotVO.setTrang_thai(AppConstants.TRANG_THAI_COT_DAY_LAI);
                        cotVO.setNguoi_ks(userId);
                        cotVO.setNgay_ks(new Date());
                        saveVisitLog(conn, session, "SYS.QLY_COT.DAYLAI", "");
                        i = cotDAO.update(cotVO);
                    }
                } else if (action != null &&
                           action.equalsIgnoreCase(AppConstants.ACTION_ACCEPT)) {
                    cotVO = new CutOffTimeVO();
                    cotVO.setId(cotForm.getId());
                    cotVO.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
                    i = cotDAO.update(cotVO);

                } else {
                    jsonObj = new JsonObject();
                    jsonObj.addProperty(AppConstants.ERROR,
                                        AppConstants.FAILURE);
                }
                conn.commit();
                if (i > 0) {
                    request.setAttribute("getJson", true);
                    list(mapping, form, request, response);
                }

            } else {
                jsonObj = new JsonObject();
                jsonObj.addProperty(AppConstants.ERROR, "TTSP-ERROR-0001");
            }
        } catch (Exception e) {
            conn.rollback();
            jsonObj = new JsonObject();
            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
        } finally {
            if (out != null) {
                out.println(jsonObj.toString());
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

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        Collection lstKBTinh = null;
        Collection lstKBHuyen = null;
        Collection colDMNH = null;
        String strKBHuyen = null;
        String whereClause = "";
        try {
            //            if (!checkPermissionOnFunction(request, "SYS.QLY_COT.THEMMOI")) {
            //                return mapping.findForward(AppConstants.FAILURE);
            //            }
            HttpSession session = request.getSession();
            CutOffTimeForm cotForm = (CutOffTimeForm)form;
            String currentDate =
                StringUtil.DateToString(new Date(), "dd/MM/yyyy");
            cotForm.setF_ngay_lap(currentDate);
            Long userid =
                (Long)session.getAttribute(AppConstants.APP_USER_ID_SESSION);
            cotForm.setTen_nguoi_lap((String)session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
            cotForm.setNguoi_lap(String.valueOf(userid));
            cotForm.setF_ngay_cot(currentDate);
            cotForm.setGio_cot_cu(null);
            cotForm.setGio_cot_moi(null);
            conn = getConnection(request);
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
            Type listType = new TypeToken<Collection<DMKBacVO>>() {
            }.getType();
            strKBHuyen = new Gson().toJson(lstKBHuyen, listType);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            request.setAttribute("colDMNH", colDMNH);
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstKBHuyen", strKBHuyen);
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    private Date convertCOT(String ngay_lap, String gio_cot) {
        String fullDateCOT = ngay_lap.concat(" ").concat(gio_cot);
        Date result = StringUtil.StringToDate(fullDateCOT, "dd/MM/yyyy HH:mm");
        return result;
    }

    //    private String checkTime(String oldCot,String newCot) {
    //        String result = null;
    //        String arrOldCOT[] = oldCot.split(":");
    //        int oldHoursCOT = Integer.parseInt(arrOldCOT[0]);
    //        int oldMinuteCOT = Integer.parseInt(arrOldCOT[1]);
    //
    //        String arrNewCot[] = newCot.split(":");
    //        int newHoursCOT = Integer.parseInt(arrNewCot[0]);
    //        int newMinuteCOT = Integer.parseInt(arrNewCot[1]);
    //
    //        Calendar currentTime = Calendar.getInstance();
    //        currentTime.clear(Calendar.SECOND);
    //        currentTime.clear(Calendar.MILLISECOND);
    //
    //        Calendar newCotTime = Calendar.getInstance();
    //        newCotTime.set(Calendar.HOUR_OF_DAY, newHoursCOT);
    //        newCotTime.set(Calendar.MINUTE, newMinuteCOT);
    //        newCotTime.clear(Calendar.SECOND);
    //        newCotTime.clear(Calendar.MILLISECOND);
    //
    //        Calendar oldCotTime = Calendar.getInstance();
    //        oldCotTime.set(Calendar.HOUR_OF_DAY, oldHoursCOT);
    //        oldCotTime.set(Calendar.MINUTE, oldMinuteCOT);
    //        oldCotTime.clear(Calendar.SECOND);
    //        oldCotTime.clear(Calendar.MILLISECOND);
    //
    //        long c1 = newCotTime.getTime().getTime() - oldCotTime.getTime().getTime();
    //        if(c1 < 0){
    //            result = "Gi\u1EDD COT m\u1EDBi nh\u1ECF h\u01A1n gi\u1EDD COT c\u0169. COT c\u0169 l\u1EDBn nh\u1EA5t l\u00E0 : "+oldCot;
    //            return result;
    //        }
    //
    //        oldCotTime.add(Calendar.MINUTE, -30);
    //
    //        long c2 = oldCotTime.getTime().getTime() - currentTime.getTime().getTime();
    //        if (c2 < 0) {
    //            result = "\u0111\u00E3 h\u1EBFt th\u1EDDi gian g\u1EEDi \u0111i\u1EC7n n\u1EDBi gi\u1EDD COT. B\u00E2y gi\u1EDD l\u00E0 : "+currentTime.get(Calendar.HOUR_OF_DAY)+":"+currentTime.get(Calendar.MINUTE);
    //        }
    //        return result;
    //    }
}
