
package com.seatech.ttsp.chungthuso.action;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.chungthuso.form.ChungThuSoForm;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.proxy.pki.PKIBean;
import com.seatech.ttsp.proxy.pki.PKIPredicate;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.user.UserDAO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import vn.gov.vst.CertInfo;


public class TraCuuChungThuSoAction extends AppAction {
  
    public TraCuuChungThuSoAction() {
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
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Collection colCTS = null;
        Collection colUserName = null;
        Connection conn = null;
        Collection temColCTS = null;
        Collection colMaKhoBac = null;
        String strAppID = AppConstants.APP_ID;
        String strWSDL;
        try {            
            conn = getConnection();
            if (!checkPermissionOnFunction(request,
                                           "QLY_NSD.QLY_CTS.TRACUU")) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            ChungThuSoForm frmCTS = (ChungThuSoForm)form;
            String page = frmCTS.getPageNumber();
            if (page == null) {
                page = "1";
            }
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = new Integer(15);
            HttpSession session = request.getSession();           
            
            if (session != null) {
                strWSDL = getThamSoHThong("WSDL_PKI", session);
                strAppID = getThamSoHThong("APP_ID", session);
                PKIService pkiService = new PKIService(strWSDL);
//                if (frmCTS.getSearch_cts() != null && frmCTS.getSearch_cts()) {
                    temColCTS = (Collection)pkiService.getCerts(true, strAppID);
                    //loc dk tra cuu
                    String kb_id = frmCTS.getKb_id();
                    if (kb_id != null && !kb_id.equalsIgnoreCase("")) {
                        UserDAO userDao = new UserDAO(conn);
                        String whereClause = " a.kb_id=?";
                        Vector params = new Vector();
                        params.add(new Parameter(kb_id, true));
                        colUserName = userDao.getUserList(whereClause, params);
                        request.setAttribute("kbId", kb_id);
                    }
                    PKIPredicate pkiPredicate =
                        new PKIPredicate(frmCTS.getSerial(),
                                         frmCTS.getMa_nguoi_duoc_cap(),
                                         frmCTS.getHieu_luc_tu_ngay(),
                                         frmCTS.getHieu_luc_den_ngay(),
                                         colUserName);
                    if (temColCTS != null && temColCTS.size() > 0) {
                        CollectionUtils colUtils = new CollectionUtils();
                        temColCTS = colUtils.select(temColCTS, pkiPredicate);
                        if (temColCTS != null && temColCTS.size() > 0) {
                            PagingBean pagingBean = new PagingBean();
                            pagingBean.setCurrentPage(currentPage);
                            pagingBean.setNumberOfRow(temColCTS.size());
                            pagingBean.setRowOnPage(numberRowOnPage);
                            request.setAttribute("PAGE_KEY", pagingBean);
                            int begin = numberRowOnPage * (currentPage - 1);
                            int end = 0;
                            if (temColCTS.size() >
                                (currentPage * numberRowOnPage) - 1) {
                                end = (currentPage * numberRowOnPage);
                            } else {
                                end = temColCTS.size();
                            }
                            List tempList = (List)temColCTS;
                            temColCTS = tempList.subList(begin, end);

                        }

                    }

//                }
                if (temColCTS != null && temColCTS.size() > 0) {
                    colCTS = new ArrayList();
                    for (Iterator iter = temColCTS.iterator(); iter.hasNext();
                    ) {
                        CertInfo cInfo = (CertInfo)iter.next();
                        colCTS.add(pkiService.getCerInfo(cInfo));
                    }
                }
            }
          String id_kb =
            session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
            String strWhere = " a.id_cha = '" + id_kb + "'";
            colMaKhoBac = dmkbDAO.getDMKBList(strWhere, null);
            request.setAttribute("listKhoBac", colMaKhoBac);
            request.setAttribute("listCTSChuaDuyet", colCTS);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        CertInfo certInfoBean = null;
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        String strJson = null;
        String strAppID = AppConstants.APP_ID;
        String strWSDL;
        try {
            HttpSession session = request.getSession();
            strAppID = getThamSoHThong("APP_ID", session);
            strWSDL = getThamSoHThong("WSDL_PKI", session);
              
            ChungThuSoForm formCTS = (ChungThuSoForm)form;            
            
            PKIService pkiService = new PKIService(strWSDL);
            
            certInfoBean =
                    pkiService.getCertInfo(formCTS.getUser_name().replace('/',
                                                                          '\\'),
                                           formCTS.getSerial(), strAppID);
            PKIBean pkibean = pkiService.getCerInfo(certInfoBean);
            Type listType = new TypeToken<PKIBean>() {
            }.getType();
            strJson = new Gson().toJson(pkibean, listType);

        } catch (Exception e) {
//            e.printStackTrace();
            JsonObject jsonObj = new JsonObject();
            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
            strJson = jsonObj.toString();
        } finally {
            out.print(strJson.toString());
            out.flush();
            out.close();
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
        Connection conn = null;
        ChungThuSoForm frmCTS = null;
        String strAppID = AppConstants.APP_ID;
        try {
            frmCTS = (ChungThuSoForm)form;
            HttpSession session = request.getSession();
            String username = frmCTS.getMa_nguoi_duoc_cap().replace('/', '\\');
            String serial = frmCTS.getSerial();
            String fromdate = frmCTS.getHieu_luc_tu_ngay();
            String todate = frmCTS.getHieu_luc_den_ngay();
            String strWSDL;
            if (session != null) {
                strWSDL = getThamSoHThong("WSDL_PKI", session);  
                PKIService pkiService = new PKIService(strWSDL);
                strAppID = getThamSoHThong("APP_ID", session);
                pkiService.approveCert(username, serial, fromdate, todate,
                                      session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString(),strAppID);
                conn = getConnection();
                saveVisitLog(conn, session, "QLY_NSD.QLY_CTS.TRACUU",
                             "Tra cuu de sua thoi gian hieu luc chung thu so");
            }
        } catch (TTSPException ex) {
            throw TTSPException.createException("TTSP-9999",
                                                "Loi " + ex.getMessage());
        } finally {
            form = null;
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        Connection conn = null;
        String reportName = "tracuuctsRptForm";
        InputStream reportStream = null;
        ChungThuSoForm frmCTS = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = null;
        Collection colCTS = null;
       
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            conn = getConnection();
            // lay ra danh sach trang thai
            frmCTS = (ChungThuSoForm)form;
            HttpSession session = request.getSession();
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                            "/" +
                                                                                            reportName +
                                                                                            ".jasper");
            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
            if (reportStream != null) {

                //                PKIService pkiService = new PKIService();
                //                if (frmCTS.getSearch_cts() != null && frmCTS.getSearch_cts()) {
                //                    temColCTS = (Collection)pkiService.getCerts(true);
                //                    //loc dk tra cuu
                //                    String kb_id = frmCTS.getKb_id();
                //                    if (kb_id != null && !kb_id.equalsIgnoreCase("")) {
                //                        UserDAO userDao = new UserDAO(conn);
                //                        String whereClause = " a.kb_id=?";
                //                        Vector params = new Vector();
                //                        params.add(new Parameter(kb_id, true));
                //                        colUserName = userDao.getUserList(whereClause, params);
                //                        request.setAttribute("kbId", kb_id);
                //                    }
                //                    PKIPredicate pkiPredicate =
                //                        new PKIPredicate(frmCTS.getSerial(),
                //                                         frmCTS.getMa_nguoi_duoc_cap(),
                //                                         frmCTS.getHieu_luc_tu_ngay(),
                //                                         frmCTS.getHieu_luc_den_ngay(),
                //                                         colUserName);
                //                    if (temColCTS != null && temColCTS.size() > 0) {
                //                        CollectionUtils colUtils = new CollectionUtils();
                //                        temColCTS = colUtils.select(temColCTS, pkiPredicate);
                //                    }
                //
                //                }
                //                if (temColCTS != null && temColCTS.size() > 0) {
                //                    colCTS = new ArrayList();
                //                    for (Iterator iter = temColCTS.iterator(); iter.hasNext();
                //                    ) {
                //                        CertInfo cInfo = (CertInfo)iter.next();
                //                        colCTS.add(pkiService.getCerInfo(cInfo));
                //                    }
                //                }
                // test report
                colCTS = new ArrayList();
                for (int i = 0; i < 10; i++) {
                    CertInfo c = new CertInfo();
                    c.setUserName("abc" + i);
                    colCTS.add(c);
                }

                if (colCTS == null) {
                    request.setAttribute("status",
                                         "dang_ky_cts.page.warning.ketqua.null");
                } else {
                    reportStream =
                            getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                                    "/" +
                                                                                                    reportName +
                                                                                                    ".jasper");
                    parameterMap = new HashMap();
                    parameterMap.put("SESS_ID",
                                     session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
                    JRDataSource source =
                        new JRBeanCollectionDataSource(colCTS);
                    jasperPrint =
                            JasperFillManager.fillReport(reportStream, parameterMap,
                                                         source);
                    String strTypePrintAction =
                        request.getParameter(AppConstants.REQUEST_ACTION) ==
                        null ? "" :
                        request.getParameter(AppConstants.REQUEST_ACTION).toString();
                    String strActionName = "tracuuctsRptAction.do";
                    String strParameter = "";
                    ReportUtility rpUtilites = new ReportUtility();
                    rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                            response, reportName, strPathFont,
                                            strActionName,
                                            sbSubHTML.toString(),
                                            strParameter);

                }
            } else
                System.out.println("khong tim thay file jasper");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            reportStream.close();
            conn.close();
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
