package com.seatech.ttsp.dts.action;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;
import com.seatech.ttsp.dts.form.DTSoatForm;
import com.seatech.ttsp.proxy.giaodien.SendDTS;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;
import com.seatech.ttsp.thamchieu.MaThamChieuVO;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DTSoatNBoDenAction extends AppAction {
    private static final String PERMISSION_PAGE = "errorQuyen";
    private static final String DTSNBO_DEN = "DTS.TS_NOIBO.XULY_DEN";
    private static final String DTSNBO_DEN_DETAILS =
        "DTS.TS_NOIBO.XULY_DEN_DETAILS";
    private static final String DTSNBO_XACNHAN = "DTS.TS_NOIBO.XULY_DEN.XNHAN";
    private static final String DTSNBO_DUYET = "DTS.TS_NOIBO.XULY_DEN.DUYET";
    private static final String STRING_EMPTY = "";
    private static String CD_KTT = "KTT";
    private static String CD_TTV = "TTV";
    private static final String DATE_FORMAT_CUTOFTIME =
        "dd/MM/yyyy HH24:MI:SS";
    private static String strCutOfTime, strCurrDate, strPreviousDate;

    public DTSoatNBoDenAction() {
        super();
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Connection conn = null;
        DTSoatVO vo = null;
        DTSoatForm dtsForm;
        int i = 0;
        String chucdanh = "";
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        String strActionStatus = "FAILURE";
        String strMsgEx = "";
        try {
            // can check them
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
//            if (!checkPermissionOnFunction(request, DTSNBO_DUYET)) {
//                return mapping.findForward(PERMISSION_PAGE);
//            }
            if (!isTokenValid(request)) {
                dtsForm = (DTSoatForm)form;
                conn = getConnection();
                DTSoatDAO dtsDao = new DTSoatDAO(conn);

                HttpSession session = request.getSession();
                //String strTempState = getStateBU(request, response, conn);

                Long nUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                String strPhanQuyen =
                    (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                String[] arrPhanQuyen = strPhanQuyen.split("\\|");
                String ngay_duyet = StringUtil.getCurrentDate();
                String temp = null;
                for (int count = 0; count < arrPhanQuyen.length; count++) {
                    chucdanh = arrPhanQuyen[count];
                    if (chucdanh != null && !STRING_EMPTY.equals(chucdanh)) {
                        String action =
                            request.getParameter(AppConstants.REQUEST_ACTION);

                        // split chuc danh - cl
                        if (chucdanh.equalsIgnoreCase(AppConstants.NSD_TTV)) {
                            if (action != null &&
                                AppConstants.ACTION_WAIT_PROCESS.equals(action)) {
                                saveVisitLog(conn, session, DTSNBO_XACNHAN,
                                             "");
                                vo = new DTSoatVO();
                              vo.setTtv_nhan(nUserID);
                                vo.setId((dtsForm.getId() != null &&
                                          !dtsForm.getId().equals(STRING_EMPTY)) ?
                                         dtsForm.getId() : null);
                                vo.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
                                i = dtsDao.update(vo);
                            }

                        }else if (chucdanh.equalsIgnoreCase("CB-TTTT")) {
                            if (action != null &&
                                AppConstants.ACTION_WAIT_PROCESS.equals(action)) {
                                saveVisitLog(conn, session, DTSNBO_XACNHAN,
                                             "");
                                vo = new DTSoatVO();
                                vo.setTtv_nhan(nUserID);
                                vo.setId((dtsForm.getId() != null &&
                                          !dtsForm.getId().equals(STRING_EMPTY)) ?
                                         dtsForm.getId() : null);
                                vo.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
                                i = dtsDao.update(vo);
                            }

                        } else if (AppConstants.NSD_KTT.equalsIgnoreCase(chucdanh)) {
                            Date dateDuyet = new Date();
                            String strNgayDuyet =
                                StringUtil.DateToString(dateDuyet,
                                                        AppConstants.DATE_TIME_FORMAT_SEND_ORDER);
                            String strNguoiDuyet =
                                session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                            //                            String strNgayDuyet = "";
                            if (temp == null) {
                                vo = new DTSoatVO();
                                vo.setId((dtsForm.getId() != null &&
                                          !dtsForm.getId().equals(STRING_EMPTY)) ?
                                         dtsForm.getId() : null);
                                if (action != null &&
                                    AppConstants.ACTION_APPROVED.equalsIgnoreCase(action)) {
                                    saveVisitLog(conn, session, DTSNBO_DUYET,
                                                 "");
                                    vo.setId((dtsForm.getId() != null &&
                                              !dtsForm.getId().equals(STRING_EMPTY)) ?
                                             dtsForm.getId() : null);
                                    vo.setKtt_duyet(nUserID);
                                    vo.setNgay_duyet(ngay_duyet);
                                    vo.setTrang_thai(AppConstants.TRANG_THAI_DUYET);
                                    //ky duyet
                                    String strWSDL =
                                        getThamSoHThong("WSDL_PKI", session);
                                    PKIService pki = new PKIService(strWSDL);
                                    String certSerial =
                                        request.getParameter("certSerial");
                                    String strSign =
                                        request.getParameter("signature");

                                    //Noi dung ky
                                    String contenData =
                                        dtsForm.getNoidung().trim();
                                    //Kiem tra chu ky
                                    //Select for update
                                    String whereClause = "where a.id = ?";
                                    Vector params = new Vector();
                                    params.add(new Parameter(dtsForm.getId(),
                                                             true));
                                    DTSoatVO dtsVOForUpdate =
                                        dtsDao.getDTSForUpdate(whereClause,
                                                               params);
                                    //******************************************
                                    long ttv = dtsVOForUpdate.getTtv_nhan().longValue();
                                    long nsd = nUserID.longValue();
                                    if(ttv == nsd){
                                      throw new Exception("Khong cho phep vua lap vua duyet DTS");
                                    }
                                    //******************************************
                                    if (AppConstants.TRANG_THAI_CHO_DUYET.equals(dtsVOForUpdate.getTrang_thai())) {
                                        //Update trang thai DTS -> Da gui

                                        //Send DTS to HTTH
                                        Collection colThamSoHT =
                                            getThamSoHThong(session);
                                        SendDTS sendDTS = new SendDTS(conn);
                                        DTSoatVO voTemp =
                                            TTSPUtils.getDTSById(vo.getId(),
                                                                 AppConstants.STATE_DTS_LTT_DI,
                                                                 conn);
                                        BeanUtils.copyProperties(dtsForm,
                                                                 voTemp);
                                        dtsForm.setKtt_duyet(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());
                                        String strMsgID = "";
                                        //add msg_id and sign into vo
                                        vo.setMsg_id(strMsgID);
                                        vo.setChuky_ktt(strSign);
                                        i = dtsDao.update(vo);
                                        if (i > 0) {
                                            strActionStatus = "SUCCESS";
                                        }
                                    }
                                    jsonObj.addProperty("action_status",
                                                        strActionStatus);

                                }
                            }
                            temp = "complete";
                        }else if ("CBPT-TTTT".equalsIgnoreCase(chucdanh)) {
                            Date dateDuyet = new Date();
                            String strNgayDuyet =
                                StringUtil.DateToString(dateDuyet,
                                                        AppConstants.DATE_TIME_FORMAT_SEND_ORDER);
                            String strNguoiDuyet =
                                session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                            //                            String strNgayDuyet = "";
                            if (temp == null) {
                                vo = new DTSoatVO();
                                vo.setId((dtsForm.getId() != null &&
                                          !dtsForm.getId().equals(STRING_EMPTY)) ?
                                         dtsForm.getId() : null);
                                if (action != null &&
                                    AppConstants.ACTION_APPROVED.equalsIgnoreCase(action)) {
                                    saveVisitLog(conn, session, DTSNBO_DUYET,
                                                 "");
                                    vo.setId((dtsForm.getId() != null &&
                                              !dtsForm.getId().equals(STRING_EMPTY)) ?
                                             dtsForm.getId() : null);
                                    vo.setKtt_duyet(nUserID);
                                    vo.setNgay_duyet(ngay_duyet);
                                    vo.setTrang_thai(AppConstants.TRANG_THAI_DUYET);
                                    //ky duyet
                                    String strWSDL =
                                        getThamSoHThong("WSDL_PKI", session);
                                    PKIService pki = new PKIService(strWSDL);
                                    String certSerial =
                                        request.getParameter("certSerial");
                                    String strSign =
                                        request.getParameter("signature");

                                    //Noi dung ky
                                    String contenData =
                                        dtsForm.getNoidung().trim();
                                    //Kiem tra chu ky
                                    //Select for update
                                    String whereClause = "where a.id = ?";
                                    Vector params = new Vector();
                                    params.add(new Parameter(dtsForm.getId(),
                                                             true));
                                    DTSoatVO dtsVOForUpdate =
                                        dtsDao.getDTSForUpdate(whereClause,
                                                               params);
                                    if (AppConstants.TRANG_THAI_CHO_DUYET.equals(dtsVOForUpdate.getTrang_thai())) {
                                        //Update trang thai DTS -> Da gui

                                        //Send DTS to HTTH
                                        Collection colThamSoHT =
                                            getThamSoHThong(session);
                                        SendDTS sendDTS = new SendDTS(conn);
                                        DTSoatVO voTemp =
                                            TTSPUtils.getDTSById(vo.getId(),
                                                                 AppConstants.STATE_DTS_LTT_DI,
                                                                 conn);
                                        BeanUtils.copyProperties(dtsForm,
                                                                 voTemp);
                                        dtsForm.setKtt_duyet(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());
                                        String strMsgID = "";
                                        //add msg_id and sign into vo
                                        vo.setMsg_id(strMsgID);
                                        vo.setChuky_ktt(strSign);
                                        i = dtsDao.update(vo);
                                        if (i > 0) {
                                            strActionStatus = "SUCCESS";
                                        }
                                    }
                                    jsonObj.addProperty("action_status",
                                                        strActionStatus);

                                }
                            }
                            temp = "complete";
                        }
                    }
                }
                conn.commit();
            }
            saveToken(request);
        } catch (Exception ex) {
//            ex.printStackTrace();
            strMsgEx = ex.getMessage();
            jsonObj.addProperty("exception_message", strMsgEx);
        } finally {
            jsonObj.addProperty("sohieu_kb", "uncorrect");
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
            close(conn);
        }
        request.setAttribute("exception_message", strMsgEx);
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String strPhanQuyen = null;
        String strWhereClause = null;
        String action = null;
        String chucdanh = null;
        Vector params = null;
        Connection conn = null;
        List lstSDTS = new ArrayList();
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, DTSNBO_DEN)) {
            return mapping.findForward(PERMISSION_PAGE);
        } else {
            // kiem tra quyen NSD
            try {
                conn = getConnection();
                HttpSession session = request.getSession();
                strPhanQuyen =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                Long lUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                String strNHKB_id =
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();

                action = request.getParameter(AppConstants.REQUEST_ACTION);
                DTSoatForm f = (DTSoatForm)form;
                if (strPhanQuyen != null) {
                    //              request.setAttribute("lstSDTS", lstSDTS);
                    // tim kiem
                    String whereClauseFind = "";
                    Vector paramFind = null;
                    if (action != null &&
                        action.equalsIgnoreCase(AppConstants.ACTION_FIND)) {
                        paramFind = new Vector();
                        if (f.getTtv_nhan() != null &&
                            (!f.getTtv_nhan().equals("") &&
                             !f.getTtv_nhan().equals("null"))) {
                            whereClauseFind =
                                    whereClauseFind + " and (lower(d.ma_nsd) like (?) OR lower(d.ten_nsd) like(?)) ";
                            //                            whereClauseFind +=
                            //                                    "'%" + f.getTtv_nhan().toLowerCase().trim() +
                            //                                    "%')) ";
                            paramFind.add(new Parameter("%" +
                                                        f.getTtv_nhan().toLowerCase().trim() +
                                                        "%", true));
                            paramFind.add(new Parameter("%" +
                                                        f.getTtv_nhan().toLowerCase().trim() +
                                                        "%", true));
                        }
                        if (f.getId() != null &&
                            (!f.getId().equals("") && !f.getId().equals("null"))) {
                            whereClauseFind =
                                    whereClauseFind + " AND a.id like ?";
                            //                            whereClauseFind += "'%" + f.getId().trim() + "%' ";
                            //                            paramsFind.add(new Parameter("'%" + f.getId() + "%'",
                            //                                                         true));
                            paramFind.add(new Parameter("%" +
                                                        f.getId().trim() + "%",
                                                        true));
                        }
                        if (f.getNhkb_nhan() != null &&
                            (!STRING_EMPTY.equals(f.getNhkb_nhan()) &&
                             !f.getNhkb_nhan().equals("null"))) {
                            whereClauseFind =
                                    whereClauseFind + " and b.ma_nh like (?) ";
                            //                            whereClauseFind +=
                            //                                    "'%" + f.getNhkb_nhan().trim() + "%')";
                            //                            paramsFind.add(new Parameter("'%" +
                            //                                                         f.getNhkb_nhan() +
                            //                                                         "%'", true));
                            paramFind.add(new Parameter("%" +
                                                        f.getNhkb_nhan().trim() +
                                                        "%", true));
                        }
                    }
                    // end
                    strWhereClause = "";
                    String[] strArrPhanQuyen = strPhanQuyen.split("\\|");
                    DTSoatDAO dtsDAO = new DTSoatDAO(conn);
                    if (action != null &&
                        action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL)) {
                        String id = request.getParameter("id");
                        strWhereClause +=
                                "and a.id=? AND f.rv_domain like 'SP_DTS.%' ";
                        params = new Vector();
                        params.add(new Parameter(id, true));
                        request.setAttribute("typeExit", "true");
                        lstSDTS =
                                (ArrayList)dtsDAO.getDTSList(strWhereClause, params);
                    } else {
                        // neu la ttv ==> trang thai == cho xu ly , cho duyet , da duyet
                        // ktt == cho ktt duyet , da duyet
                        ArrayList lstSDTSTTV = new ArrayList();
                        ArrayList lstSDTSKTT = new ArrayList();
                        ArrayList lstTemp = new ArrayList();
                        for (int i = 0; i < strArrPhanQuyen.length; i++) {
                            chucdanh = strArrPhanQuyen[i];
                            if (chucdanh != null &&
                                chucdanh.equalsIgnoreCase(CD_TTV)) {
                                request.setAttribute("typeExit", "false");
                                params = new Vector();
                                String strWhereClauseTTV = " ";
                                // id,nhkb cua user tren session
                                strWhereClauseTTV +=
                                        " and a.LOAI_DTS= 'N' and a.nhkb_nhan = ?  and f.rv_domain like 'SP_DTS.%'";
                                strWhereClauseTTV +=
                                        " and (a.trang_thai = '" + AppConstants.TRANG_THAI_CHO_XULY +
                                        "' " + "or a.trang_thai = '" +
                                        AppConstants.TRANG_THAI_CHO_DUYET +
                                        "' or (a.trang_thai = '" +
                                        AppConstants.TRANG_THAI_DUYET + "' " +
                                        "AND decode(a.ngay_duyet,null,a.ngay_nhan,a.ngay_duyet) >= trunc(sysdate) )) ";
                               
                              params.add(new Parameter(strNHKB_id, true));
                                if (paramFind != null) {
                                    params.addAll(paramFind);
                                }
                                //                               += ") and a.ngay_nhan>=TRUNC(sysdate) ";
                                strWhereClauseTTV +=
                                        whereClauseFind + " order by a.trang_thai,a.id";
                                lstSDTSTTV =
                                        (ArrayList)dtsDAO.getDTSList(strWhereClauseTTV,
                                                                     params);
                            }else if (chucdanh != null &&
                                chucdanh.equalsIgnoreCase("CB-TTTT")) {
                                request.setAttribute("typeExit", "false");
                                params = new Vector();
                                String strWhereClauseTTV = " ";
                                // id,nhkb cua user tren session
                                strWhereClauseTTV +=
                                        " and a.LOAI_DTS= 'TW' and a.nhkb_nhan = ?  and f.rv_domain like 'SP_DTS.%'";
                                strWhereClauseTTV +=
                                        " and (a.trang_thai = '" + AppConstants.TRANG_THAI_CHO_XULY +
                                        "' " + "or a.trang_thai = '" +
                                        AppConstants.TRANG_THAI_CHO_DUYET +
                                        "' or (a.trang_thai = '" +
                                        AppConstants.TRANG_THAI_DUYET + "' " +
                                        "AND decode(a.ngay_duyet,null,a.ngay_nhan,a.ngay_duyet) >= trunc(sysdate) )) ";
                                params.add(new Parameter(strNHKB_id, true));
//                              params.add(new Parameter(strNHKB_id, true));
                                if (paramFind != null) {
                                    params.addAll(paramFind);
                                }
                                //                               += ") and a.ngay_nhan>=TRUNC(sysdate) ";
                                strWhereClauseTTV +=
                                        whereClauseFind + " order by a.trang_thai,a.id";
                                lstSDTSTTV =
                                        (ArrayList)dtsDAO.getDTSList(strWhereClauseTTV,
                                                                     params);
                            } else if (chucdanh != null && chucdanh.equalsIgnoreCase(CD_KTT) ) {
                                params = new Vector();
                                String strWhereClauseKTT =
                                    " and  a.LOAI_DTS= 'N'  and a.nhkb_nhan = ?  and f.rv_domain like 'SP_DTS.%' " +
                                    "and (a.TRANG_THAI = '" +
                                    AppConstants.TRANG_THAI_CHO_DUYET +
                                    "' or (a.TRANG_THAI = '" +
                                    AppConstants.TRANG_THAI_DUYET +
                                    "' and decode(a.ngay_duyet,null,a.ngay_nhan,a.ngay_duyet) >= trunc(sysdate) ))";
                                strWhereClauseKTT +=
                                        " and (a.ktt_duyet=? or a.ktt_duyet is null) ";
                                params.add(new Parameter(strNHKB_id, true));
//                              params.add(new Parameter(strNHKB_id, true));
                                params.add(new Parameter(lUserID, true));
                                strWhereClauseKTT +=
                                        whereClauseFind + " ORDER BY a.ngay_nhan desc";
                                if (paramFind != null) {
                                    params.addAll(paramFind);
                                }
                                lstSDTSKTT =
                                        (ArrayList)dtsDAO.getDTSList(strWhereClauseKTT,
                                                                     params);
                                request.setAttribute("typeExit", "false");
                            }else if (chucdanh != null && chucdanh.equalsIgnoreCase("CBPT-TTTT")) {
                                params = new Vector();
                                String strWhereClauseKTT =
                                    " and  a.LOAI_DTS= 'TW'  and a.nhkb_nhan = ?  and f.rv_domain like 'SP_DTS.%' " +
                                    "and (a.TRANG_THAI = '" +
                                    AppConstants.TRANG_THAI_CHO_DUYET +
                                    "' or (a.TRANG_THAI = '" +
                                    AppConstants.TRANG_THAI_DUYET +
                                    "' and decode(a.ngay_duyet,null,a.ngay_nhan,a.ngay_duyet) >= trunc(sysdate) ))";
                                strWhereClauseKTT +=
                                        " and (a.ktt_duyet=? or a.ktt_duyet is null) ";
                                params.add(new Parameter(strNHKB_id, true));
//                              params.add(new Parameter(strNHKB_id, true));
                                params.add(new Parameter(lUserID, true));
                                strWhereClauseKTT +=
                                        whereClauseFind + " ORDER BY a.ngay_nhan desc";
                                if (paramFind != null) {
                                    params.addAll(paramFind);
                                }
                                lstSDTSKTT =
                                        (ArrayList)dtsDAO.getDTSList(strWhereClauseKTT,
                                                                     params);
                                request.setAttribute("typeExit", "false");
                            }

                        }
                        // gan list
                        if ((lstSDTSTTV != null && !lstSDTSTTV.isEmpty()) &&
                            (lstSDTSKTT != null && !lstSDTSKTT.isEmpty())) {
                            Iterator iterTTV = lstSDTSTTV.iterator();
                            Iterator iterKTT = null;
                            DTSoatVO vo = null;
                            DTSoatVO vo2 = null;
                            String strID = "";
                            int count = 0;
                            lstTemp.addAll(lstSDTSTTV);
                            while(iterTTV.hasNext()){
                              vo = (DTSoatVO)iterTTV.next();
                              strID = vo.getId();
                              count = 0;
                              iterKTT = lstSDTSKTT.iterator();
                              while(iterKTT.hasNext()){
                                vo2 = (DTSoatVO)iterKTT.next();
                                if(strID.equals(vo2.getId())){
                                  count = 1;  
                                  break;    
                                }                                
                              }
                              if(count == 0){  
                                lstTemp.add(vo);
                              }
                            }
//                            lstSDTSKTT.addAll(lstSDTSTTV);
                            lstSDTS = Collections.synchronizedList(lstTemp);
                        } else if (lstSDTSTTV != null &&
                                   lstSDTSTTV.size() > 0) {
                            lstSDTS = Collections.synchronizedList(lstSDTSTTV);
                        } else if (lstSDTSKTT != null &&
                                   lstSDTSKTT.size() > 0) {
                            lstSDTS = Collections.synchronizedList(lstSDTSKTT);
                        } else {
                            lstSDTS = new ArrayList();
                        }
                    }

                    // tiim va refresh
                    if (request.getAttribute("getJson") != null ||
                        (action != null &&
                         (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                          action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                        Type listType = new TypeToken<Collection<DTSoatVO>>() {
                        }.getType();
                        String strJson = new Gson().toJson(lstSDTS, listType);
                        response.setContentType(AppConstants.CONTENT_TYPE_JSON_UTF);
                        PrintWriter out = response.getWriter();
                        out.println(strJson.toString());
                        out.flush();
                        out.close();
                    }
                    //                  else{
                    //            throw TTSPException.createException("list()", e.getMessage());
                    //          }
                    //                if (lUserID != null) {
                    //                    action = request.getParameter(AppConstants.REQUEST_ACTION);
                    //                    if (action != null) {
                    //                    // cho nay de lam details noi bo
                    //                    }
                    //                } else {
                    //                    return mapping.findForward(AppConstants.FAILURE);
                    //                }
                    request.setAttribute("lstSDTS", lstSDTS);
                    request.setAttribute("chucdanh", strPhanQuyen);
                    saveVisitLog(conn, session, DTSNBO_DEN, "");
                } else {
                    request.setAttribute("typeExit", "false");
                    return mapping.findForward(PERMISSION_PAGE);
                }
            } catch (Exception e) {
//                e.printStackTrace();
                if (request.getAttribute("getJson") != null ||
                    (action != null &&
                     (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                      action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                    response.reset();
                    response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                    PrintWriter out = response.getWriter();
                    JsonObject jsonObj = new JsonObject();
                    jsonObj.addProperty("error", e.getMessage());
                    out.println(jsonObj.toString());
                    out.flush();
                    out.close();

                } else {
                    throw new Exception(" list() : " + e.getMessage());
                }
            } finally {
                close(conn);
            }
        }

        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Collection colDMNH = null;
        DTSoatVO dtsoatVO = null;
        String whereClause = "";
        Vector params = null;
        DMNHangVO dmNHVO = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        UserVO userVO = null;
        Connection conn = null;
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }

        try {
            HttpSession session = request.getSession();
            Long lUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            conn = getConnection();
            DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
            String action = request.getParameter(AppConstants.REQUEST_ACTION);
            if (action != null && !"".equalsIgnoreCase(action)) {
                //Lay trang thai truoc khi update
                if (action.equalsIgnoreCase(AppConstants.ACTION_FILL_DATA_TO_FORM)) {
                    whereClause = " AND  a.id=?";
                    String id = request.getParameter("id");
                    if (id != null && !id.equals("")) {
                        params = new Vector();
                        params.add(new Parameter(id, true));
                        dtsoatVO = dtsoatDAO.getDTS(whereClause, params);
                        if (dtsoatVO != null) {
                            // lay ten ttv va ktt
                            if (dtsoatVO.getTtv_nhan() != null) {
                                UserDAO userDAO = new UserDAO(conn);
                                String strwhereClauseUser = " a.id = ?";
                                params = new Vector();
                                params.add(new Parameter(dtsoatVO.getTtv_nhan(),
                                                         true));
                                userVO =
                                        userDAO.getUser(strwhereClauseUser, params);
                                dtsoatVO.setTen_ttv_nhan(userVO.getTen_nsd());
                            }
                            if (dtsoatVO.getKtt_duyet() != null) {
                                UserDAO userDAO = new UserDAO(conn);
                                String strwhereClauseUser = " a.id = ?";
                                params = new Vector();
                                params.add(new Parameter(dtsoatVO.getKtt_duyet(),
                                                         true));
                                userVO =
                                        userDAO.getUser(strwhereClauseUser, params);
                                dtsoatVO.setTen_ktt_duyet(userVO.getTen_nsd());
                            }
                            // end
                            //lay ten ma danh muc ngan hang
                            whereClause = " id in(?,?) ";
                            params = new Vector();
                            params.add(new Parameter(dtsoatVO.getNhkb_chuyen(),
                                                     true));
                            params.add(new Parameter(dtsoatVO.getNhkb_nhan(),
                                                     true));
                            DMNHangDAO dmNHangDAO = new DMNHangDAO(conn);
                            colDMNH =
                                    dmNHangDAO.getDMNHList(whereClause, params);
                            if (colDMNH != null && colDMNH.size() > 0) {
                                for (Iterator iter = colDMNH.iterator();
                                     iter.hasNext(); ) {
                                    dmNHVO = (DMNHangVO)iter.next();
                                    if (dtsoatVO.getNhkb_chuyen().toString().equals(dtsoatVO.getNhkb_nhan().toString())) {
                                        dtsoatVO.setMa_don_vi_tra_soat(dmNHVO.getMa_nh());
                                        dtsoatVO.setTen_don_vi_tra_soat(dmNHVO.getTen());
                                        dtsoatVO.setMa_don_vi_nhan_tra_soat(dmNHVO.getMa_nh());
                                        dtsoatVO.setTen_don_vi_nhan_tra_soat(dmNHVO.getTen());
                                    } else {
                                        if (dtsoatVO.getNhkb_chuyen().toString().trim().equalsIgnoreCase(dmNHVO.getId().toString())) {
                                            dtsoatVO.setMa_don_vi_tra_soat(dmNHVO.getMa_nh());
                                            dtsoatVO.setTen_don_vi_tra_soat(dmNHVO.getTen());
                                        } else {
                                            dtsoatVO.setMa_don_vi_nhan_tra_soat(dmNHVO.getMa_nh());
                                            dtsoatVO.setTen_don_vi_nhan_tra_soat(dmNHVO.getTen());
                                        }
                                    }
                                }
                            }
                        }
                        Vector vParam = new Vector();
                        vParam.add(new Parameter(dtsoatVO.getTrang_thai(),
                                                 true));
                        String strWhereClauseMTC =
                            " rv_domain = 'SP_DTS.TRANG_THAI' and rv_low_value = ?";
                        MaThamChieuDAO matcDAO = new MaThamChieuDAO(conn);
                        MaThamChieuVO matcVO =
                            matcDAO.getMaThamChieuByKey(strWhereClauseMTC,
                                                        vParam);
                        String strPhanQuyen =
                            (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                        dtsoatVO.setChuc_danh(strPhanQuyen);
                        dtsoatVO.setTrang_thai(matcVO.getRv_low_value());
                        dtsoatVO.setMo_ta_trang_thai(matcVO.getRv_meaning());
                        Type listType = new TypeToken<DTSoatVO>() {
                        }.getType();
                        strJson = new Gson().toJson(dtsoatVO, listType);
                    }
                } else if (action.equalsIgnoreCase("fillNHKB")) {
                    DMNHangDAO dmNHangDAO = new DMNHangDAO(conn);
                    if (dtsoatForm.getMa_don_vi_nhan_tra_soat() != null ||
                        dtsoatForm.getMa_don_vi_nhan_tra_soat().trim() != "") {
                        String strWhereClause = "a.ma_nh = ?";
                        Parameter param =
                            new Parameter(dtsoatForm.getMa_don_vi_nhan_tra_soat(),
                                          true);
                        params = new Vector();
                        params.add(param);
                        dmNHVO = dmNHangDAO.getDMNH(strWhereClause, params);
                        jsonObj = new JsonObject();
                        jsonObj.addProperty("ten_don_vi_nhan_tra_soat",
                                            dmNHVO.getTen());
                        jsonObj.addProperty("ma_nh", dmNHVO.getMa_nh());
                        strJson = jsonObj.toString();
                    }

                }
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
                saveVisitLog(conn, session, DTSNBO_DEN_DETAILS, "");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
            out.flush();
            out.close();
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
