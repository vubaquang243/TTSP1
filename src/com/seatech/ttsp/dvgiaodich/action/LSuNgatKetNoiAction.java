package com.seatech.ttsp.dvgiaodich.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dvgiaodich.DvGiaoDichDAO;
import com.seatech.ttsp.dvgiaodich.form.DvGiaoDichForm;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LSuNgatKetNoiAction extends AppAction {
    private static String SUCCESS = "success";
    //    private static String FAILURE = "failure";
    //    private String forward = AppConstants.SUCCESS;
    //    private static String STRING_EMPTY = "";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "SYS.QLGD.LSU_NGAT_KET_NOI")) {
            return mapping.findForward("errorQuyen");
        }
        Long nUserID = null;
        //        String nUserName = null;
        //        Long nKbID = null;
        //        String nKbName = null;
        Connection conn = null;
        Collection lstKBTinh = null;
        try {

            HttpSession session = request.getSession();
            DvGiaoDichForm f = (DvGiaoDichForm)form;
            conn = getConnection();
            nUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());

            DvGiaoDichDAO dao = new DvGiaoDichDAO(conn);
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            String strwhere = null;
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            //Khai bao bien find.
            String strma_kb = null;
            String strten_kb = null;
            String strden_ngay = null;
            String strtu_ngay = null;
            String strng_noi = null;
            String strng_ngat = null;
            f.setTrangthai("");
            if (null != f) {
                strma_kb = f.getKb_id();
                strten_kb = f.getTen_kb();
                strden_ngay = f.getDen_ngay();
                strtu_ngay = f.getTu_ngay();
                strng_noi = f.getNguoi_noi();
                strng_ngat = f.getNguoi_ngat();
            }
            if (strma_kb != null && !strma_kb.equals("")) {
                strwhere = "and a.kb_id = " + strma_kb;
            } else {
                strwhere = "";
            }
            if (strng_noi != null && !strng_noi.equals("")) {
                strwhere +=
                        "and lower(b2.ten_nsd) like lower('%" + strng_noi + "%') ";
            }
            if (strng_ngat != null && !strng_ngat.equals("")) {
                strwhere +=
                        "and lower(b.ten_nsd) like lower('%" + strng_ngat + "%') ";
            }
            boolean bCheck = false;
            if ((strtu_ngay != null && !"".equals(strtu_ngay)) &&
                (strden_ngay != null && !"".equals(strden_ngay))) {
                bCheck = true;
            } else if ((strtu_ngay != null && !"".equals(strtu_ngay))) {
                bCheck = true;
            } else if ((strden_ngay != null && !"".equals(strden_ngay))) {
                bCheck = true;
            }
            if (bCheck) {
                if (strtu_ngay != null && !"".equals(strtu_ngay)) {
                    strwhere +=
                            " and to_char(a.tu_ngay,'DD/MM/yyyy')='" + f.getTu_ngay() +
                            "'";
                }
                if (strden_ngay != null && !"".equals(strden_ngay)) {
                    strwhere +=
                            " and to_char(a.den_ngay,'DD/MM/yyyy')= '" + f.getDen_ngay() +
                            "'";
                }
            }
            if ((!"".equals(strtu_ngay)) && strtu_ngay != null) {
                if (!"".equals(strden_ngay) && strden_ngay != null) {
                    strwhere +=
                            " and to_char(a.den_ngay,'DD/MM/yyyy')>='" + f.getTu_ngay() +
                            "' and to_char(a.tu_ngay,'DD/MM/yyyy')<= '" +
                            f.getDen_ngay() + "'";
                }
            }
            Vector param = null;
            List list =
                (List)dao.getListLSuNgat(strwhere, param, currentPage, numberRowOnPage,
                                         totalCount);

            if (list.isEmpty()) {
                request.setAttribute("status", "all.null.value");
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("ListLSuNgat", list);
            // kb tinh

            DChieu1DAO dao1 = new DChieu1DAO(conn);
            // danh sach kb tinh
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                String whereClause = " ";
                lstKBTinh = dao1.getDMucKB_cha(whereClause, null);
            } else {
                String strWhere = " AND a.ma='0003' ";
                lstKBTinh = dao1.getDMucKB_cha(strWhere, null);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            request.setAttribute("lstKBTinh", lstKBTinh);
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "SYS.QLGD.LSU_NGAT_KET_NOI")) {
            return mapping.findForward("errorQuyen");
        }
        Long nUserID = null;
        Connection conn = null;
        InputStream reportStream = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {

            HttpSession session = request.getSession();
            DvGiaoDichForm f = (DvGiaoDichForm)form;
            conn = getConnection();
            nUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());

            DvGiaoDichDAO dao = new DvGiaoDichDAO(conn);

            String strwhere = null;
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            //Khai bao bien find.
            String strma_kb = null;
            String strten_kb = null;
            String strden_ngay = null;
            String strtu_ngay = null;
            String strng_noi = null;
            String strng_ngat = null;
            f.setTrangthai("");
            if (null != f) {
                strma_kb = f.getKb_id();
                strten_kb = f.getTen_kb();
                strden_ngay = f.getDen_ngay();
                strtu_ngay = f.getTu_ngay();
                strng_noi = f.getNguoi_noi();
                strng_ngat = f.getNguoi_ngat();
            }
            if (strma_kb != null && !strma_kb.equals("")) {
                strwhere = "and a.kb_id = " + strma_kb;

                sbSubHTML.append("<input type=\"hidden\" name=\"kb_id\" value=\"" +
                                 strma_kb + "\" id=\"kb_id\"></input>");
            } else {
                strwhere = "";
            }
            if (strng_noi != null && !strng_noi.equals("")) {
                strwhere +=
                        "and lower(b2.ten_nsd) like lower('%" + strng_noi + "%') ";

                sbSubHTML.append("<input type=\"hidden\" name=\"nguoi_noi\" value=\"" +
                                 strng_noi + "\" id=\"nguoi_noi\"></input>");
            }
            if (strng_ngat != null && !strng_ngat.equals("")) {
                strwhere +=
                        "and lower(b.ten_nsd) like lower('%" + strng_ngat + "%') ";

                sbSubHTML.append("<input type=\"hidden\" name=\"nguoi_ngat\" value=\"" +
                                 strng_ngat + "\" id=\"nguoi_ngat\"></input>");
            }
            if (strtu_ngay != null && !"".equals(strtu_ngay)) {
                sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                                 strtu_ngay + "\" id=\"tu_ngay\"></input>");
            }
            if (strden_ngay != null && !"".equals(strden_ngay)) {
                sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay\" value=\"" +
                                 strden_ngay + "\" id=\"den_ngay\"></input>");
            }
            boolean bCheck = false;
            if ((strtu_ngay != null && !"".equals(strtu_ngay)) &&
                (strden_ngay != null && !"".equals(strden_ngay))) {
                bCheck = true;
            } else if ((strtu_ngay != null && !"".equals(strtu_ngay))) {
                bCheck = true;
            } else if ((strden_ngay != null && !"".equals(strden_ngay))) {
                bCheck = true;
            }
            if (bCheck) {
                if (strtu_ngay != null && !"".equals(strtu_ngay)) {
                    strwhere +=
                            " and to_char(a.tu_ngay,'DD/MM/yyyy')='" + f.getTu_ngay() +
                            "'";
                }
                if (strden_ngay != null && !"".equals(strden_ngay)) {
                    strwhere +=
                            " and to_char(a.den_ngay,'DD/MM/yyyy')= '" + f.getDen_ngay() +
                            "'";
                }
            }
            if ((!"".equals(strtu_ngay)) && strtu_ngay != null) {
                if (!"".equals(strden_ngay) && strden_ngay != null) {
                    strwhere +=
                            " and to_char(a.den_ngay,'DD/MM/yyyy')>='" + f.getTu_ngay() +
                            "' and to_char(a.tu_ngay,'DD/MM/yyyy')<= '" +
                            f.getDen_ngay() + "'";
                }
            }
            Vector param = null;
            ResultSet rs = dao.getRSLSuNgat(strwhere, param);

            JasperPrint jasperPrint = null;
            HashMap parameterMap = null;

            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                            fileName +
                                                                                            ".jasper");
            JRDataSource jrDS = new JRResultSetDataSource(rs);
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 jrDS);
            String strTypePrintAction =
                request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                "" :
                request.getParameter(AppConstants.REQUEST_ACTION).toString();
            String strActionName = "LSuNgatKetNoiPrintAction.do";
            String strParameter = "";
            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    fileName, strPathFont, strActionName,
                                    sbSubHTML.toString(), strParameter);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
            try {
                reportStream.close();
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
        return mapping.findForward(SUCCESS);
    }

    public static final String fileName = "/LSuNgatKetNoi";
}
