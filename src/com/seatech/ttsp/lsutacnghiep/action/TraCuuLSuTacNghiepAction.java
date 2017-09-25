package com.seatech.ttsp.lsutacnghiep.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.lsutacnghiep.LSuTacNghiepDAO;
import com.seatech.ttsp.lsutacnghiep.form.LSuTacNghiepForm;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.HashMap;
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


public class TraCuuLSuTacNghiepAction extends AppAction {
    private static String SUCCESS = "success";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "SYS.LS_TACNGHIEP")) {
            return mapping.findForward("errorQuyen");
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Collection lst_lsu = null;
        Connection conn = null;
        if (!checkPermissionOnFunction(request, "SYS.LS_TACNGHIEP")) {
            return mapping.findForward("errorQuyen");
        }
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            //          Long nUserID =
            //            new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            saveVisitLog(conn, session, "SYS.LS_TACNGHIEP", "");
            Vector params = new Vector();
            SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyyy");
            LSuTacNghiepForm lsu_tn = (LSuTacNghiepForm)form;
            if (lsu_tn != null) {
                String whereClause = "";
                if (lsu_tn.getId_kb() != null &&
                    !"".equalsIgnoreCase(lsu_tn.getId_kb())) {
                    whereClause += " AND (a.nhkb_nhan=? or a.nhkb_chuyen=? )";
                    params.add(new Parameter(lsu_tn.getId_kb(), true));
                    params.add(new Parameter(lsu_tn.getId_kb(), true));
                }
                if (lsu_tn.getMa_nv() != null &&
                    !"".equalsIgnoreCase(lsu_tn.getMa_nv())) {
                    whereClause +=
                            " AND ( upper(b.ma_nsd) like upper(?) or upper(b.ten_nsd) like upper(?) ) ";
                    params.add(new Parameter("%" + lsu_tn.getMa_nv() + "%",
                                             true));
                    params.add(new Parameter("%" + lsu_tn.getMa_nv() + "%",
                                             true));
                }
                if (lsu_tn.getTu_ngay() != null &&
                    !"".equalsIgnoreCase(lsu_tn.getTu_ngay())) {
                    whereClause += " AND TRUNC(a.tgian_ghi) >= ? ";
                    params.add(new DateParameter(fm.parse(lsu_tn.getTu_ngay()),
                                                 true));
                }
                if (lsu_tn.getDen_ngay() != null &&
                    !"".equalsIgnoreCase(lsu_tn.getDen_ngay())) {
                    whereClause += " AND TRUNC(a.tgian_ghi) <=? ";
                    params.add(new DateParameter(fm.parse(lsu_tn.getDen_ngay()),
                                                 true));
                }
                if (lsu_tn.getLoai_lenh_tt() != null &&
                    !"".equalsIgnoreCase(lsu_tn.getLoai_lenh_tt())) {
                    if (lsu_tn.getLoai_lenh_tt().equals("01"))
                        whereClause += " AND a.ltt_id like '__701%' ";
                    else
                        whereClause += " AND a.ltt_id like '__203%' ";

                }
                String page = lsu_tn.getPageNumber();
                if (page == null) {
                    page = "1";
                }
                // khai bao cac bien phan trang
                Integer currentPage = new Integer(page);
                Integer numberRowOnPage = new Integer(15);
                Integer totalCount[] = new Integer[1];
                LSuTacNghiepDAO lsu_tnDAO = new LSuTacNghiepDAO(conn);
                lst_lsu =
                        lsu_tnDAO.getlist_ltt(whereClause, params, currentPage,
                                              numberRowOnPage, totalCount);
                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            throw e;
        } finally {
            request.setAttribute("lstlsutacnghiep", lst_lsu);
            conn.close();
        }

        return mapping.findForward(SUCCESS);
    }
    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String fileName = "/LSuTacNghiep";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        ResultSet rsLsuTacNghiep = null;
        Connection conn = null;
        InputStream reportStream = null;
        StringBuffer sbSubHTML = new StringBuffer();
        if (!checkPermissionOnFunction(request, "SYS.LS_TACNGHIEP")) {
            return mapping.findForward("errorQuyen");
        }
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            //          Long nUserID =
            //            new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            saveVisitLog(conn, session, "SYS.LS_TACNGHIEP", "");
            Vector params = new Vector();
            SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyyy");
            LSuTacNghiepForm lsu_tn = (LSuTacNghiepForm)form;
            if (lsu_tn != null) {
                String whereClause = "";
                if (lsu_tn.getId_kb() != null &&
                    !"".equalsIgnoreCase(lsu_tn.getId_kb())) {
                    whereClause += " AND (a.nhkb_nhan=? or a.nhkb_chuyen=? )";
                    params.add(new Parameter(lsu_tn.getId_kb(), true));
                    params.add(new Parameter(lsu_tn.getId_kb(), true));
                    sbSubHTML.append("<input type=\"hidden\" name=\"id_kb\" value=\"" +
                                     lsu_tn.getId_kb() +
                                     "\" id=\"id_kb\"></input>");
                }
                if (lsu_tn.getMa_nv() != null &&
                    !"".equalsIgnoreCase(lsu_tn.getMa_nv())) {
                    whereClause +=
                            " AND ( upper(b.ma_nsd) like upper(?) or upper(b.ten_nsd) like upper(?) ) ";
                    params.add(new Parameter("%" + lsu_tn.getMa_nv() + "%",
                                             true));
                    params.add(new Parameter("%" + lsu_tn.getMa_nv() + "%",
                                             true));
                    sbSubHTML.append("<input type=\"hidden\" name=\"ma_nv\" value=\"" +
                                     lsu_tn.getMa_nv() +
                                     "\" id=\"ma_nv\"></input>");
                }
                if (lsu_tn.getTu_ngay() != null &&
                    !"".equalsIgnoreCase(lsu_tn.getTu_ngay())) {
                    whereClause += " AND TRUNC(a.tgian_ghi) >= ? ";
                    params.add(new DateParameter(fm.parse(lsu_tn.getTu_ngay()),
                                                 true));
                    sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                                     lsu_tn.getTu_ngay() +
                                     "\" id=\"tu_ngay\"></input>");
                }
                if (lsu_tn.getDen_ngay() != null &&
                    !"".equalsIgnoreCase(lsu_tn.getDen_ngay())) {
                    whereClause += " AND TRUNC(a.tgian_ghi) <=? ";
                    params.add(new DateParameter(fm.parse(lsu_tn.getDen_ngay()),
                                                 true));
                    sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay\" value=\"" +
                                     lsu_tn.getDen_ngay() +
                                     "\" id=\"den_ngay\"></input>");
                }
                if (lsu_tn.getLoai_lenh_tt() != null &&
                    !"".equalsIgnoreCase(lsu_tn.getLoai_lenh_tt())) {
                    if (lsu_tn.getLoai_lenh_tt().equals("01"))
                        whereClause += " AND a.ltt_id like '__701%' ";
                    else
                        whereClause += " AND a.ltt_id like '__203%' ";

                    sbSubHTML.append("<input type=\"hidden\" name=\"loai_lenh_tt\" value=\"" +
                                     lsu_tn.getLoai_lenh_tt() +
                                     "\" id=\"loai_lenh_tt\"></input>");

                }
                LSuTacNghiepDAO lsTacNghiepDAO = new LSuTacNghiepDAO(conn);
                rsLsuTacNghiep =
                        lsTacNghiepDAO.getResultSet_ltt(whereClause, params);

                JasperPrint jasperPrint = null;
                HashMap parameterMap = null;

                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
                JRDataSource jrDS = new JRResultSetDataSource(rsLsuTacNghiep);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     jrDS);

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "inLSuTacNghiep.do";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    AppConstants.REPORT_DIRECTORY +
                    AppConstants.FONT_FOR_REPORT;
                ReportUtility rpUtilites = new ReportUtility();
                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
            }
        } catch (Exception e) {
            throw e;
//            e.printStackTrace();
        } finally {
            conn.close();
            try {
                reportStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mapping.findForward(SUCCESS);
    }
}
