package com.seatech.ttsp.quyennhap.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.quyennhap.QuyenNhapThuCongDAO;
import com.seatech.ttsp.quyennhap.form.QuyenNhapThuCongForm;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

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


public class LSuQuyenNhapLenhAction extends AppAction {
    private static String SUCCESS = "success";
    //    private static String FAILURE = "failure";
    //    private String forward = AppConstants.SUCCESS;
    //    private static String STRING_EMPTY = "";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.QUYENNHAP.LSU_QUYEN_NHAP")) {
            return mapping.findForward("errorQuyen");
        }
        Long nUserID = null;
        //        String nUserName = null;
        //        Long nKbID = null;
        //        String nKbName = null;
        Connection conn = null;
        try {

            HttpSession session = request.getSession();
            QuyenNhapThuCongForm f = (QuyenNhapThuCongForm)form;
            conn = getConnection();
            nUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());

            QuyenNhapThuCongDAO dao = new QuyenNhapThuCongDAO(conn);
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            String strwhere = null;
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            //Khai bao bien find.
            String strng_rut = null;
            String strnsd = null;
            String strng_gan = null;
            String strtu_ngay = null;
            String strden_ngay = null;
            if (null != f) {
                strng_rut = f.getTen_nguoi_rut();
                strnsd = f.getTen_nsd();
                strng_gan = f.getTen_nguoi_gan();
                strtu_ngay = f.getTu_ngay();
                strden_ngay = f.getDen_ngay();
            }
            if (strng_rut != null && !strng_rut.equals("")) {
                strwhere =
                        "and lower(b2.ten_nsd) like lower('%" + strng_rut + "%') ";
            } else {
                strwhere = "";
            }
            if (strnsd != null && !strnsd.equals("")) {
                strwhere =
                        "and lower(b.ma_nsd) like lower('%" + strnsd + "%') ";

            }
            if (strng_gan != null && !strng_gan.equals("")) {
                strwhere =
                        "and lower(b1.ten_nsd) like lower('%" + strng_gan + "%') ";

            }
            if ((!"".equals(strtu_ngay)) && "".equals(strden_ngay)) {

                strwhere +=
                        " and to_char(a.tu_ngay,'DD/MM/yyyy')='" + f.getTu_ngay() +
                        "'";
            }
            if (("".equals(strtu_ngay)) && !"".equals(strden_ngay)) {

                strwhere +=
                        " and to_char(a.den_ngay,'DD/MM/yyyy')= '" + f.getDen_ngay() +
                        "'";
            }
            if ((!"".equals(strtu_ngay)) && strtu_ngay != null) {
                if (!"".equals(strden_ngay) && strden_ngay != null) {
                    strwhere +=
                            " and to_char(a.tu_ngay,'DD/MM/yyyy')>='" + f.getTu_ngay() +
                            "' and to_char(a.den_ngay,'DD/MM/yyyy')<= '" +
                            f.getDen_ngay() + "'";
                }
            }
            Vector param = null;
            List list =
                (List)dao.getLSuQNhapList(strwhere, param, currentPage, numberRowOnPage,
                                          totalCount);
            if (list.isEmpty()) {
                request.setAttribute("status", "lsuquyennhap.ketqua.null");
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("ListLSuQNhap", list);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.QUYENNHAP.LSU_QUYEN_NHAP")) {
            return mapping.findForward("errorQuyen");
        }
        Long nUserID = null;
        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {

            HttpSession session = request.getSession();
            QuyenNhapThuCongForm f = (QuyenNhapThuCongForm)form;
            conn = getConnection();
            nUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());

            QuyenNhapThuCongDAO dao = new QuyenNhapThuCongDAO(conn);
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            String strwhere = null;
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            //Khai bao bien find.
            String strng_rut = null;
            String strnsd = null;
            String strng_gan = null;
            String strtu_ngay = null;
            String strden_ngay = null;
            if (null != f) {
                strng_rut = f.getTen_nguoi_rut();
                strnsd = f.getTen_nsd();
                strng_gan = f.getTen_nguoi_gan();
                strtu_ngay = f.getTu_ngay();
                strden_ngay = f.getDen_ngay();
            }
            if (strng_rut != null && !"".equals(strng_rut)) {
                strwhere =
                        "and lower(b2.ten_nsd) like lower('%" + strng_rut + "%') ";
                sbSubHTML.append("<input type=\"hidden\" name=\"ten_nguoi_rut\" value=\"" +
                                 strng_rut +
                                 "\" id=\"ten_nguoi_rut\"></input>");
            } else {
                strwhere = "";
            }
            if (strnsd != null && !"".equals(strnsd)) {
                strwhere =
                        "and lower(b.ma_nsd) like lower('%" + strnsd + "%') ";
                sbSubHTML.append("<input type=\"hidden\" name=\"ten_nsd\" value=\"" +
                                 strnsd + "\" id=\"ten_nsd\"></input>");

            }
            if (strng_gan != null && !"".equals(strng_gan)) {
                strwhere =
                        "and lower(b1.ten_nsd) like lower('%" + strng_gan + "%') ";
                sbSubHTML.append("<input type=\"hidden\" name=\"ten_nguoi_gan\" value=\"" +
                                 strng_gan +
                                 "\" id=\"ten_nguoi_gan\"></input>");

            }
            if ((!"".equals(strtu_ngay)) && "".equals(strden_ngay)) {

                strwhere +=
                        " and to_char(a.tu_ngay,'DD/MM/yyyy')='" + f.getTu_ngay() +
                        "'";
            }
            if (("".equals(strtu_ngay)) && !"".equals(strden_ngay)) {

                strwhere +=
                        " and to_char(a.den_ngay,'DD/MM/yyyy')= '" + f.getDen_ngay() +
                        "'";
            }
            if ((!"".equals(strtu_ngay)) && strtu_ngay != null) {
                if (!"".equals(strden_ngay) && strden_ngay != null) {
                    strwhere +=
                            " and to_char(a.tu_ngay,'DD/MM/yyyy')>='" + f.getTu_ngay() +
                            "' and to_char(a.den_ngay,'DD/MM/yyyy')<= '" +
                            f.getDen_ngay() + "'";
                }
            }
            if (strtu_ngay != null && !"".equals(strtu_ngay)) {
                sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                                 strtu_ngay + "\" id=\"tu_ngay\"></input>");
            }
            if (strden_ngay != null && !"".equals(strden_ngay)) {
                sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay\" value=\"" +
                                 strden_ngay + "\" id=\"den_ngay\"></input>");
            }
            Vector param = null;
            ResultSet rsQuyenNhapLS =
                dao.getLSuQNhapResultSet(strwhere, param, currentPage,
                                         numberRowOnPage, totalCount);


            JasperPrint jasperPrint = null;
            HashMap parameterMap = null;

            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                            fileName +
                                                                                            ".jasper");
            JRDataSource jrDS = new JRResultSetDataSource(rsQuyenNhapLS);
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 jrDS);

            String strTypePrintAction =
                request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                "" :
                request.getParameter(AppConstants.REQUEST_ACTION).toString();
            String strActionName = "LSuQuyenNhapLenhPrintAction.do";
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
                e.printStackTrace();
            }
        }
        return mapping.findForward(SUCCESS);
    }

    public static final String fileName = "/LSuQuyenNhap";
}
