package com.seatech.ttsp.user.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.user.UserHistoryDAO;
import com.seatech.ttsp.user.form.TraCuuLSuNSDForm;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TraCuuLSuNSDAction extends AppAction {
    private static String SUCCESS = "success";
    //    private static String FAILURE = "failure";
    //    private String forward = AppConstants.SUCCESS;
    //    private static String STRING_EMPTY = "";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "QLY_NSD.QLY.TRACUU_LSU_NSD")) {
            return mapping.findForward("errorQuyen");
        }

        Connection conn = null;
        TraCuuLSuNSDForm f = null;


        try {
            conn = getConnection();
            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            //          HttpSession session = request.getSession();
            f = (TraCuuLSuNSDForm)form;
            UserHistoryDAO dao = new UserHistoryDAO(conn);
            //          UserHistoryVO vo = new UserHistoryVO();
            String strWhere = null;
            Vector v_param = null;
            List lstLSuNSd = null;

            v_param = new Vector();
            if (f.getKb_id() != null && !f.getKb_id().equals("")) {
                strWhere = "and b.KB_ID=? ";
                v_param.add(new Parameter(f.getKb_id(), true));
            } else {
                strWhere = "";
            }
            if (f.getTen_nsd() != null && !f.getTen_nsd().equals("")) {

                strWhere +=
                        " and lower(b.ten_nsd) like lower('%" + f.getTen_nsd() +
                        "%') ";
            }
            if (f.getMa_nsd() != null && !f.getMa_nsd().equals("")) {

                strWhere +=
                        " and lower(b.ma_nsd) like lower('%" + f.getMa_nsd() +
                        "%') ";
            }


            if ((f.getTu_ngay() != null || f.getDen_ngay() != null) &&
                (!f.getTu_ngay().equals("") || !f.getDen_ngay().equals(""))) {
                strWhere += " and a.ngay_tdoi >=? and trunc(a.ngay_tdoi)<= ?";
                v_param.add(new DateParameter(StringUtil.StringToDate(f.getTu_ngay(),
                                                                      "dd/MM/yyyy"),
                                              true));
                v_param.add(new DateParameter(StringUtil.StringToDate(f.getDen_ngay(),
                                                                      "dd/MM/yyyy"),
                                              true));
            }
            //khai bao bien phan trang
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            lstLSuNSd =
                    (List)dao.getLsuUserList(strWhere, v_param, currentPage,
                                             numberRowOnPage, totalCount);
            if (lstLSuNSd.isEmpty()) {
                request.setAttribute("status",
                                     "tracuulsu.listlsu.warning.ketqua.null");
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstLSu", lstLSuNSd);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String fileName = "/LSuNSD";
    public static final String reportName = "/LSuNSD";


    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "QLY_NSD.QLY.TRACUU_LSU_NSD")) {
            return mapping.findForward("errorQuyen");
        }

        Connection conn = null;
        TraCuuLSuNSDForm f = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;

        try {
            conn = getConnection();

            //          HttpSession session = request.getSession();
            f = (TraCuuLSuNSDForm)form;
            UserHistoryDAO dao = new UserHistoryDAO(conn);
            //          UserHistoryVO vo = new UserHistoryVO();
            String strWhere = null;
            Vector v_param = null;

            ResultSet rsLSuNSD = null;

            v_param = new Vector();
            if (f.getKb_id() != null && !"".equals(f.getKb_id())) {
                strWhere = "and b.KB_ID=? ";
                v_param.add(new Parameter(f.getKb_id(), true));
                sbSubHTML.append("<input type=\"hidden\" name=\"kb_id\" value=\"" +
                                 f.getKb_id() + "\" id=\"kb_id\"></input>");
            } else {
                strWhere = "";
            }
            if (f.getTen_nsd() != null && !"".equals(f.getTen_nsd())) {

                strWhere +=
                        " and lower(b.ten_nsd) like lower('%" + f.getTen_nsd() +
                        "%') ";
                sbSubHTML.append("<input type=\"hidden\" name=\"ten_nsd\" value=\"" +
                                 f.getTen_nsd() +
                                 "\" id=\"ten_nsd\"></input>");
            }
            if (f.getMa_nsd() != null && !"".equals(f.getMa_nsd())) {

                strWhere +=
                        " and lower(b.ma_nsd) like lower('%" + f.getMa_nsd() +
                        "%') ";
                sbSubHTML.append("<input type=\"hidden\" name=\"ma_nsd\" value=\"" +
                                 f.getMa_nsd() + "\" id=\"ma_nsd\"></input>");
            }


            if ((f.getTu_ngay() != null || f.getDen_ngay() != null) &&
                (!"".equals(f.getTu_ngay()) || !"".equals(f.getDen_ngay()))) {
                strWhere += " and a.ngay_tdoi >=? and trunc(a.ngay_tdoi)<= ?";
                v_param.add(new DateParameter(StringUtil.StringToDate(f.getTu_ngay(),
                                                                      "dd/MM/yyyy"),
                                              true));
                v_param.add(new DateParameter(StringUtil.StringToDate(f.getDen_ngay(),
                                                                      "dd/MM/yyyy"),
                                              true));
                sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                                 f.getTu_ngay() +
                                 "\" id=\"tu_ngay\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay\" value=\"" +
                                 f.getDen_ngay() +
                                 "\" id=\"den_ngay\"></input>");
            }


            rsLSuNSD = dao.getLsuUserList(strWhere, v_param);
            if (rsLSuNSD == null) {
                request.setAttribute("status",
                                     "quanlynsd.listnsd.warning.ketqua.null");
            } else {
                ReportUtility rpUtilites = new ReportUtility();

                JasperPrint jasperPrint = null;
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                "/" +
                                                                                                reportName +
                                                                                                ".jasper");
                HashMap parameterMap = null;
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     new JRResultSetDataSource(rsLSuNSD));
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    REPORT_DIRECTORY + strFontTimeRoman;

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "PrintLSuNSDAction.do";
                String strParameter = "";

                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
            }

        } catch (Exception e) {
            throw e;

        } finally {
            try {
                reportStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
