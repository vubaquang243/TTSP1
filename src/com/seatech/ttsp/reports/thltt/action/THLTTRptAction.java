package com.seatech.ttsp.reports.thltt.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.reports.thltt.forms.THLTTRptForm;

import java.io.InputStream;

import java.sql.Connection;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class THLTTRptAction extends AppAction {
    public THLTTRptAction() {
        super();
    }

    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String fileName = "/TongHopThanhToan";
    public static final String SRTING_EMPTY = "";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        return mapping.findForward(AppConstants.SUCCESS);
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        String reportName = "/TongHopThanhToan";
        //String reportName = "LTT_e";
        InputStream reportStream = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = null;
        net.sf.jasperreports.engine.util.JRProperties.setProperty("net.sf.jasperreports.extension.registry.factory.htmlelement",
                                                                  "net.sf.jasperreports.extensions.HtmlElementExtensionsRegistryFactory");
        net.sf.jasperreports.engine.util.JRProperties.setProperty("net.sf.jasperreports.extension.registry.factory.htmlcomponent",
                                                                  "net.sf.jasperreports.components.html.HtmlComponentExtensionsRegistryFactory");
        try {
            //check quyen
            //check session
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "BCAO.BANG_TH_LTT")) {
                return mapping.findForward("errorQuyen");
            } else {

                conn = getConnection(request);
                HttpSession session = request.getSession();
                if (request.getParameter("reportName") != null)
                    reportName = request.getParameter("reportName");
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                reportName +
                                                                                                ".jasper");
                if (form == null) {
                    return mapping.findForward(AppConstants.FAILURE);
                }
                THLTTRptForm f = (THLTTRptForm)form;
                if (f.getTu_ngay() == null ||
                    SRTING_EMPTY.equals(f.getTu_ngay())) {
                    return mapping.findForward(AppConstants.FAILURE);
                } else {
                    if (f.getDen_ngay() == null ||
                        SRTING_EMPTY.equals(f.getDen_ngay())) {
                        Date d = new Date();
                        f.setDen_ngay(StringUtil.DateToString(d,
                                                              "dd/MM/yyyy"));
                    }
                }

                String strTuNgay = f.getTu_ngay();
                String strDenNgay = f.getDen_ngay();
                parameterMap = new HashMap();
                parameterMap.put("TU_NGAY", strTuNgay.trim());
                parameterMap.put("DEN_NGAY", strDenNgay.trim());
                parameterMap.put("SESS_ID",
                                 session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);
                //Neu de de cau lenh SQL trong Java
                //              jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, rsDataSource);
                ReportUtility rpUtil = new ReportUtility();
                JasperViewer jpViewer = null;
                jpViewer =
                        rpUtil.getJasperViewer(jasperPrint, "TTSP >> Bang tong hop Lenh Thanh Toan");
                jpViewer.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return mapping.getInputForward(); //mapping.findForward(AppConstants.SUCCESS);
    }
}

