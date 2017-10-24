package com.seatech.framework.strustx;


import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.AppKeys;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.chucnang.ChucNangVO;
import com.seatech.ttsp.lstruycap.LSuTruyCapDAO;
import com.seatech.ttsp.thamso.ThamSoHThong;
import com.seatech.ttsp.tintuc.TinTucDAO;
import com.seatech.ttsp.tintuc.TinTucVO;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserVO;

import com.thoughtworks.xstream.XStream;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import oracle.jdbc.OracleConnection;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AppAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        try {
            String strMethodName = mapping.getParameter();

            if (!"login".equalsIgnoreCase(strMethodName)) { // &&
                // !"logout".equalsIgnoreCase(strMethodName)) {
                HttpSession ss = request.getSession();
                if (ss.getAttribute(AppConstants.APP_USER_ID_SESSION) ==
                    null) {
                    return mapping.findForward("login");
                }
                UserVO vo =
                    getUserInfo(request.getSession().getAttribute(AppConstants.APP_USER_ID_SESSION).toString());

                String strCurSessionID = request.getSession().getId();
                if (strCurSessionID != null) {
                    if (!strCurSessionID.equalsIgnoreCase(vo.getSession_id())) {
                        saveError(request,
                                  TTSPException.createException("TTSP-0002",
                                                                vo.getMa_nsd(),
                                                                vo.getIp_truycap()));
                        request.getSession().invalidate();
                        String accept = request.getHeader("Accept");
                        if (accept.indexOf(AppConstants.CONTENT_TYPE_JSON) !=
                            -1) {
                            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                            JsonObject strJson = new JsonObject();
                            strJson.addProperty("logout", true);
                            strJson.addProperty("ma_nsd", vo.getMa_nsd());
                            strJson.addProperty("ip_truycap",
                                                vo.getIp_truycap());
                            PrintWriter out = response.getWriter();
                            out.println(strJson.toString());
                            out.flush();
                            out.close();
                            return null;
                        } else {
                            return mapping.findForward("login");
                        }
                    }
                } else {
                    return mapping.findForward("login");
                }

                //Lay gio COT
                if (!"logout".equalsIgnoreCase(strMethodName)) {
                    String strCOT = "";
                    String strMaNHSP =
                        ss.getAttribute(AppConstants.APP_NH_SP_SESSION) ==
                        null ? "" :
                        ss.getAttribute(AppConstants.APP_NH_SP_SESSION).toString();
                    String strMaNHKB =
                        ss.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) ==
                        null ? "" :
                        ss.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
                  String htkb_code =
                      String.valueOf(ss.getAttribute(AppConstants.APP_KB_CODE_SESSION));
                  
                    if (!"".equals(strMaNHSP) && !"".equals(strMaNHKB)) {
                        Connection conn = null;
                        try {
                            conn = getConnection();
                            TTSPUtils ttspUtils = new TTSPUtils(conn);
                            String strCurDate = StringUtil.getCurrentDate();
                            strCOT =
                                    ttspUtils.getGioCutOfTime(strCurDate, strMaNHKB,
                                                              strMaNHSP);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            close(conn);
                        }
                    }
                  request.setAttribute("gio_cot_menu", strCOT);
                 // lay thong tin tin tuc   
                  Connection conn = null;
                  try {
                      conn = getConnection();
                    // lay thong tin tin tuc
                    TinTucDAO newsDAO = new TinTucDAO(conn);
                    TinTucVO newsVO = null;//new TinTucVO();
                    String strNews =
                        " AND TO_CHAR (ngay_dang,'YYYYMMDD') <=TO_CHAR (SYSDATE, 'YYYYMMDD')" +
                        "  AND  TO_CHAR (ngay_het_han,'YYYYMMDD') >=TO_CHAR (SYSDATE, 'YYYYMMDD') and trang_thai='0'";
                    //                          newsVO = newsDAO.getTinTuc(strNews, null);
                                        
                    String shkb = "";
                    String tin_tuc="";
                    List colTinTuc = new ArrayList();
                    colTinTuc = (List)newsDAO.getColTinTuc(strNews, null);
                        if(colTinTuc!=null){
                           Iterator iter = colTinTuc.iterator();
                           while (iter.hasNext()){
                             newsVO= (TinTucVO)iter.next();
                             String dv_dang = newsVO.getDv_dang().replace(",", "").trim();
                             if ("TQ".equals(dv_dang)) {
                               tin_tuc += newsVO.getNoi_dung() + "      ";
                             } else {
                                 for (int i = 0; i < dv_dang.length(); i++) {
                                     if (i % 4 == 0) {
                                         shkb = dv_dang.substring(i, i + 4);
                                       if (htkb_code.equals(shkb)) {
                                           tin_tuc+= newsVO.getNoi_dung() +"      ";
                                       }
                                     }
                                 }
                    
                             }
                           }
                        }
                      request.setAttribute("tin_tuc", tin_tuc);
                      
                  } catch (Exception ex) {
                      ex.printStackTrace();
                  } finally {
                      close(conn);
                  }
                }
                //
            }
            if (strMethodName != null) {
                if (strMethodName.equalsIgnoreCase("list")) {
                    return list(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("add")) {
                    return add(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("addExc")) {
                    return addExc(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("update")) {
                    return update(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("updateExc")) {
                    return updateExc(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("search")) {
                    return search(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("delete")) {
                    return delete(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("view")) {
                    return view(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("approvedLttExc")) {
                    return approvedLttExc(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("rejectLttExc")) {
                    return rejectLttExc(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("cancelLttExc")) {
                    return cancelLttExc(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("completeLttExc")) {
                    return completeLttExc(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("searchLttDi")) {
                    return searchLttDi(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("searchLttDiExc")) {
                    return searchLttDiExc(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("searchLttDen")) {
                    return searchLttDen(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("searchLttDenExc")) {
                    return searchLttDenExc(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("lttDiAdd")) {
                    return lttDiAdd(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("lttDiAddExc")) {
                    return lttDiAddExc(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("checkPhanDoanCOA")) {
                    return checkPhanDoanCOA(request, response, mapping);
                } else if (strMethodName.equalsIgnoreCase("fillTenFromCOA")) {
                    return fillTenFromCOA(request, response, mapping);
                } else if (strMethodName.equalsIgnoreCase("print")) {
                    return printAction(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("ycTraCuu")) {
                    return ycTraCuu(mapping, form, request, response);
                } else {
                    return executeAction(mapping, form, request, response);
                }
            } else {
                return executeAction(mapping, form, request, response);
            }
        } catch (TTSPException ex) {
            //            ex.printStackTrace();
            ServletContext context = servlet.getServletContext();
            HashMap errorMap =
                (HashMap)context.getAttribute(AppKeys.ERROR_LIST_APPLICATION_KEY);
            String msgError = ex.getMessage(errorMap);
            System.err.println(msgError);
            saveError(request, ex);
            return mapping.findForward("failure");
        } catch (Exception ex) {
            ex.printStackTrace();
            saveError(request,
                      TTSPException.createException("TTSP-0000", ex.getClass().getName() +
                                                    ": " + ex.getMessage()));
            return mapping.findForward("failure");
        }
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        return null;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward approvedLttExc(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        return null;
    }
    //khang moi them

    public ActionForward rejectLttExc(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward cancelLttExc(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward completeLttExc(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward searchLttDi(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward searchLttDiExc(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward searchLttDen(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward searchLttDenExc(ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {
        return null;
    }


    public ActionForward lttDiAdd(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward lttDiAddExc(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward checkPhanDoanCOA(HttpServletRequest request,
                                          HttpServletResponse response,
                                          ActionMapping mapping) throws Exception {
        return null;
    }

    public ActionForward fillTenFromCOA(HttpServletRequest request,
                                        HttpServletResponse response,
                                        ActionMapping mapping) throws Exception {
        return null;
    }

    public ActionForward ycTraCuu(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        return null;
    }

    protected ActionForward printAction(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        return null;
    }

    protected Connection getConnection() throws Exception {
        ServletContext context = servlet.getServletContext();
        DataSource ds =
            (DataSource)context.getAttribute(AppKeys.DATASOURCE_APPLICATION_KEY);
        Connection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(false);
        //Set quyen cho user cua ung dung
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        ttspUtils.grantAccess();

        return conn;
    }

    protected Connection getConnection(HttpServletRequest request) throws Exception {
        ServletContext context = servlet.getServletContext();
        DataSource ds =
            (DataSource)context.getAttribute(AppKeys.DATASOURCE_APPLICATION_KEY);
        Connection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(false);
        //Set quyen cho user cua ung dung
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        ttspUtils.grantAccess();

        setEndToEndMetrics(request, conn);

        return conn;
    }

    protected void saveError(HttpServletRequest request, String key) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(key));
        saveErrors(request, errors);
    }

    protected void saveError(HttpServletRequest request, String key,
                             String value0) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(key, value0));
        saveErrors(request, errors);
    }

    protected void saveError(HttpServletRequest request, String key,
                             String value0, String value1) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR,
                   new ActionError(key, value0, value1));
        saveErrors(request, errors);
    }

    protected void saveError(HttpServletRequest request, String key,
                             String value0, String value1, String value2) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR,
                   new ActionError(key, value0, value1, value2));
        saveErrors(request, errors);
    }

    protected void saveError(HttpServletRequest request, String key,
                             String value0, String value1, String value2,
                             String value3) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR,
                   new ActionError(key, value0, value1, value2, value3));
        saveErrors(request, errors);
    }

    protected void saveError(HttpServletRequest request, TTSPException ex) {
        ServletContext context = servlet.getServletContext();
        HashMap errorMap =
            (HashMap)context.getAttribute(AppKeys.ERROR_LIST_APPLICATION_KEY);
        String msgError = ex.getMessage(errorMap);
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR,
                   new ActionError("errors", msgError));
        saveErrors(request, errors);
    }

    protected void saveError2(HttpServletRequest request, TTSPException ex) {
        ServletContext context = servlet.getServletContext();
        HashMap errorMap =
            (HashMap)context.getAttribute(AppKeys.ERROR_LIST_APPLICATION_KEY);
        String strErrorHeader =
            ex.getMoTaLoi(errorMap).replace(":", "").replace("{0}",
                                                             "").replace("{1}",
                                                                         "").replace("{2}",
                                                                                     "");
        String strErrorDetail = ex.getMessage(errorMap);

        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR,
                   new ActionError("errors", strErrorHeader, strErrorDetail));
        saveErrors(request, errors);
    }

    protected void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
    // close ResultSet

    protected void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }

        }
    }

    // close PreparedStatement

    protected void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                pstmt = null;
            }

        }
    }

    // close Statement

    protected void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                stmt = null;
            }
        }
    }

    protected String populateBeanToXml(Object vo, String strValueObject,
                                       String rootElement) throws Exception {

        XStream xstream = new XStream();

        xstream.alias(rootElement, Class.forName(strValueObject));

        String xml = xstream.toXML(vo);

        return xml;
    }

    /**
     * @see: Kiem tra quyen cua User tren action
     * */
    protected boolean checkPermissionOnFunction(HttpServletRequest request,
                                                String strAction) throws Exception {
        HttpSession session = request.getSession();
        String action =
            request.getParameter(AppConstants.REQUEST_ACTION) != null ?
            request.getParameter(AppConstants.REQUEST_ACTION) : "";
        if (action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL)) {
            return true;
        }
        if ("".equals(strAction))
            return false;
        Collection colCNang =
            (Collection)session.getAttribute(AppConstants.APP_CHUC_NANG_LIST_SESSION);
        if (null == colCNang)
            return false;
        Iterator iter = colCNang.iterator();
        ChucNangVO cnangVO = null;
        while (iter.hasNext()) {
            cnangVO = (ChucNangVO)iter.next();
            if (strAction.equalsIgnoreCase(cnangVO.getKy_hieu_cnang())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see: Ghi log truy cap doi voi moi action
     * */
    //    protected void saveVisitLog(Connection conn, String strUserID,
    //                                String strAction, String strIP,
    //                                String strMoTa, String strOSUser, String strComputerName) throws Exception {
    //        LSuTruyCapDAO lSuTruyCapDAO = new LSuTruyCapDAO(conn);
    //        lSuTruyCapDAO.saveVisitLog(strUserID, strAction, strIP, strMoTa, strOSUser, strComputerName);
    //    }
    protected void saveVisitLog(Connection conn, HttpSession session,
                                String strAction,
                                String strMoTa) throws Exception {
        LSuTruyCapDAO lSuTruyCapDAO = new LSuTruyCapDAO(conn);
        String strUserID =
            session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
        String strIP =
            session.getAttribute(AppConstants.APP_IP_SESSION).toString();
        String strOSUser =
            session.getAttribute(AppConstants.APP_OS_USER_SESSION).toString();
        String strComputerName =
            session.getAttribute(AppConstants.APP_COMPUTER_NAME_SESSION).toString();
        lSuTruyCapDAO.saveVisitLog(strUserID, strAction, strIP, strMoTa,
                                   strOSUser, strComputerName);
    }

    /**
     * @see: Lay tham so he thong
     * @param:
     * @return: Tra ve danh sach tham so he thong
     * */
    protected Collection getThamSoHThong(HttpSession session) {
        return (Collection)session.getAttribute(AppConstants.APP_THAM_SO_SESSION);
    }
    //    protected Collection getThamSoHThong() {
    //        ServletContext context = servlet.getServletContext();
    //        return (Collection)context.getAttribute(AppKeys.PARAM_LIST_APPLICATION_KEY);
    //    }

    /**
     * @see: Lay tham so he thong
     * @param: truyen vao ten TSo
     * @return: Tra ve gia tri tham so he thong
     * */
    protected String getThamSoHThong(String strTenTSo, HttpSession session) {
        ThamSoHThong tso = new ThamSoHThong();
        Collection collThamSoList =
            (Collection)session.getAttribute(AppConstants.APP_THAM_SO_SESSION);
        return tso.getThamSoHThong(strTenTSo, collThamSoList);
    }
    //    protected String getThamSoHThong(String strTenTSo) {
    //        ServletContext context = servlet.getServletContext();
    //        ThamSoHThong tso = new ThamSoHThong();
    //        Collection collThamSoList =
    //            (Collection)context.getAttribute(AppKeys.PARAM_LIST_APPLICATION_KEY);
    //        return tso.getThamSoHThong(strTenTSo, collThamSoList);
    //    }

    /**
     * @see: Lay tham so he thong rieng cua moi kho bac
     * @param: Truyen vao ten TSo va ID cua KB (ID cua bang SP_DM_HTKB)
     * @return: Tra ve gia tri TSo he thong
     * */
    protected String getThamSoHThong(String strTenTSo, Long nKBacID,
                                     HttpSession session) {

        ThamSoHThong tso = new ThamSoHThong();
        Collection collThamSoList =
            (Collection)session.getAttribute(AppConstants.APP_THAM_SO_SESSION);
        return tso.getThamSoHThong(strTenTSo, nKBacID, collThamSoList);
    }
    //    protected String getThamSoHThong(String strTenTSo, Long nKBacID) {
    //        ServletContext context = servlet.getServletContext();
    //        ThamSoHThong tso = new ThamSoHThong();
    //        Collection collThamSoList =
    //            (Collection)context.getAttribute(AppKeys.PARAM_LIST_APPLICATION_KEY);
    //        return tso.getThamSoHThong(strTenTSo, nKBacID, collThamSoList);
    //    }

    /**
     * @see: Lay tham so he thong rieng cua moi kho bac
     * @param: Truyen vao ten TSo va ID cua KB (ID cua bang SP_DM_HTKB)
     * @return: Tra ve gia tri TSo he thong
     * */
    protected UserVO getUserInfo(String strUserID) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            UserDAO ssDAO = new UserDAO(conn);
            Vector vParams = new Vector();
            vParams.add(new Parameter(strUserID, true));
            UserVO vo = ssDAO.getUser("a.id = ?", vParams);
            return vo;
        } finally {
            close(conn);
        }
    }

    protected List getDSachCNang(HttpServletRequest request,
                                 String strAction) {
        List lstCNang = null;
        if ("".equals(strAction))
            return lstCNang;
        HttpSession session = request.getSession();
        Collection colCNang =
            (Collection)session.getAttribute(AppConstants.APP_CHUC_NANG_LIST_SESSION);
        Iterator iter = colCNang.iterator();
        ChucNangVO cnangVO = null;
        //Lay ID chuc nang cha
        boolean checkExist = false;
        while (iter.hasNext()) {
            cnangVO = (ChucNangVO)iter.next();
            if (strAction.equalsIgnoreCase(cnangVO.getKy_hieu_cnang())) {
                checkExist = true;
                break;
            }
        }
        if (!checkExist)
            return lstCNang;
        long nCNangChaID = cnangVO.getId().longValue();
        //Lay cac chuc nang con
        iter = colCNang.iterator();
        lstCNang = new ArrayList();
        while (iter.hasNext()) {
            cnangVO = (ChucNangVO)iter.next();
            if (cnangVO.getCnang_cha() == null)
                continue;
            if (nCNangChaID == cnangVO.getCnang_cha().longValue()) {
                lstCNang.add(cnangVO);
            }
        }
        return lstCNang;
    }

    public void setEndToEndMetrics(HttpServletRequest request,
                                   Connection conn) throws Exception {
        String[] metrics =
            new String[OracleConnection.END_TO_END_STATE_INDEX_MAX];
        //      metrics[OracleConnection.END_TO_END_ACTION_INDEX] = "ManhNVACtion";
        metrics[OracleConnection.END_TO_END_CLIENTID_INDEX] =
                request.getSession().getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
        metrics[OracleConnection.END_TO_END_ECID_INDEX] =
                request.getSession().getAttribute(AppConstants.APP_IP_SESSION).toString();

        ((OracleConnection)conn).setEndToEndMetrics(metrics, (short)0);

    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        return null;
    }
}
