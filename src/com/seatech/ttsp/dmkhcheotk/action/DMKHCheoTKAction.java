package com.seatech.ttsp.dmkhcheotk.action;


import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmkhcheotk.DMKHCheoTKDAO;
import com.seatech.ttsp.dmkhcheotk.DMKHCheoTKVO;
import com.seatech.ttsp.dmkhcheotk.form.DMKHCheoTKForm;

import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DMKHCheoTKAction extends AppAction {
    private static final String PERMISSION_PAGE = "errorQuyen";
    private static final String DMKH_CHEO_TK = "SYS.KHCHEO";
    private static final String DMKH_CHEO_TK_THEM = "SYS.KHCHEO.THEM";
    private static final String DMKH_CHEO_TK_SUA = "SYS.KHCHEO.SUA";
    private static final String DMKH_CHEO_TK_XOA = "SYS.KHCHEO.XOA";
    private static String STRING_EMPTY = "";
    private static String action = null;

    public DMKHCheoTKAction() {
        super();
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        List lstDMTK = null;
        Vector params = null;
        String strWhereClause = "";
        Connection conn = null;
        try {
            String strActionAddSucess =
                request.getParameter(AppConstants.REQUEST_ACTION);
            DMKHCheoTKForm f = (DMKHCheoTKForm)form;

            if (strActionAddSucess != null &&
                strActionAddSucess.equalsIgnoreCase("AddSuccess")) {
                // set tai khoan = ''
                f.setTk("");
            }
            conn = getConnection();
            HttpSession session = request.getSession();
            //            Long lUserId =
            //                (Long)session.getAttribute(AppConstants.APP_USER_ID_SESSION);
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, DMKH_CHEO_TK)) {
                return mapping.findForward(PERMISSION_PAGE);
            } else {
                String page = f.getPageNumber();
                if (page == null) {
                    page = "1";
                }
                // khai bao cac bien phan trang
                Integer currentPage = new Integer(page);
                Integer numberRowOnPage =
                    new Integer(AppConstants.APP_NUMBER_ROW_ON_PAGE);
                //            Integer numberRowOnPage =
                //            new Integer(5);
                Integer totalCount[] = new Integer[1];
                DMKHCheoTKDAO dmkhDAO = new DMKHCheoTKDAO(conn);
                params = new Vector();
                if (f.getTk() != null && !STRING_EMPTY.equals(f.getTk())) {
                    strWhereClause += " a.tk like (?)";
                    params.add(new Parameter("%" + f.getTk().trim() + "%",
                                             true));
                }
                if (!STRING_EMPTY.equals(strWhereClause)) {
                    strWhereClause += " order by a.tk desc";
                }
                lstDMTK =
                        (List)dmkhDAO.getDMKHCheoTKListWithPaging(strWhereClause,
                                                                  params,
                                                                  currentPage,
                                                                  numberRowOnPage,
                                                                  totalCount);
                if (lstDMTK != null) {
                    PagingBean pagingBean = new PagingBean();
                    pagingBean.setCurrentPage(currentPage);
                    pagingBean.setNumberOfRow(totalCount[0].intValue());
                    pagingBean.setRowOnPage(numberRowOnPage);
                    request.setAttribute("PAGE_KEY", pagingBean);
                    request.setAttribute("danhsachDMKH", lstDMTK);
                }
                saveVisitLog(conn, session, DMKH_CHEO_TK, "");
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
        } finally {
            close(conn);
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
        InputStream reportStream = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            String strActionAddSucess =
                request.getParameter(AppConstants.REQUEST_ACTION);
            DMKHCheoTKForm f = (DMKHCheoTKForm)form;

            if (strActionAddSucess != null &&
                strActionAddSucess.equalsIgnoreCase("AddSuccess")) {
                // set tai khoan = ''
                f.setTk("");
            }
            conn = getConnection();
            HttpSession session = request.getSession();
            saveVisitLog(conn, session, DMKH_CHEO_TK, "");

            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, DMKH_CHEO_TK)) {
                return mapping.findForward(PERMISSION_PAGE);
            } else {

                String strSoTK = "";
                if (f.getTk() != null && !STRING_EMPTY.equals(f.getTk())) {

                    strSoTK = f.getTk();
                    sbSubHTML.append("<input type=\"hidden\" name=\"tk\" value=\"" +
                                     strSoTK + "\" id=\"tk\"></input>");
                }

                String reportName = "DMKetHopCheoTK";
                JasperPrint jasperPrint = null;
                Map parameterMap = null;

                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                                "/" +
                                                                                                reportName +
                                                                                                ".jasper");

                parameterMap = new HashMap();
                parameterMap.put("P_SO_TK", strSoTK);
                //De cau lenh SQL trong file .jasper
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "printDMKHCheoTK.do";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    AppConstants.REPORT_DIRECTORY +
                    AppConstants.FONT_FOR_REPORT;
                ReportUtility rpUtilites = new ReportUtility();
                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, reportName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);


            }
        } catch (Exception ex) {
//            ex.printStackTrace();
        } finally {
            try {
                reportStream.close();
            } catch (Exception e) {
//                e.printStackTrace();
            }
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            if (!checkPermissionOnFunction(request, DMKH_CHEO_TK_SUA) ||
                !checkPermissionOnFunction(request, DMKH_CHEO_TK_THEM)) {
                return mapping.findForward(PERMISSION_PAGE);
            } else {
                HttpSession session = request.getSession();
                //                Long lUserId =
                //                    (Long)session.getAttribute(AppConstants.APP_USER_ID_SESSION);
                conn = getConnection();
                action = request.getParameter(AppConstants.REQUEST_ACTION);
                if (action.equalsIgnoreCase(AppConstants.ACTION_EDIT)) {
                    DMKHCheoTKDAO dao = new DMKHCheoTKDAO(conn);
                    DMKHCheoTKVO vo =
                        dao.getTKByID(request.getParameter("idTK").trim());
                    if (vo != null) {
                        request.setAttribute("tk_field", vo.getTk());
                        request.setAttribute("ma_chuong_field",
                                             vo.getMa_chuong());
                        request.setAttribute("ma_ctmt_field", vo.getMa_ctmt());
                        request.setAttribute("ma_diaban_field",
                                             vo.getMa_diaban());
                        request.setAttribute("ma_dphong_field",
                                             vo.getMa_dphong());
                        request.setAttribute("ma_dvsdns_field",
                                             vo.getMa_dvsdns());
                        request.setAttribute("ma_ndkt_field", vo.getMa_ndkt());
                        request.setAttribute("ma_nganh_field",
                                             vo.getMa_nganh());
                        request.setAttribute("ma_nguon_field",
                                             vo.getMa_nguon());
                        request.setAttribute("ma_quy_field", vo.getMa_quy());
                        request.setAttribute("ma_cap_field", vo.getMa_cap());
                        request.setAttribute("action", "EDIT");
                    }
                } else {
                    request.setAttribute("action", "ADD");
                }
                saveVisitLog(conn, session, DMKH_CHEO_TK_THEM, "");
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        Connection conn = null;
        Vector params = null;
        boolean bResult = false;
        DMKHCheoTKVO voExist = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = null;
        try {
            HttpSession session = request.getSession();
            //            Long lUserId =
            //                (Long)session.getAttribute(AppConstants.APP_USER_ID_SESSION);
            if (action == null || STRING_EMPTY.equals(action)) {
                action = AppConstants.ACTION_CANCEL;
            }
            if (action != null && !STRING_EMPTY.equals(action)) {
                conn = getConnection();
                DMKHCheoTKDAO dao = new DMKHCheoTKDAO(conn);
                DMKHCheoTKForm dmkhForm = (DMKHCheoTKForm)form;
                String idKMKH = dmkhForm.getTk();
                if (!checkPermissionOnFunction(request, DMKH_CHEO_TK_THEM)) {
                    return mapping.findForward(PERMISSION_PAGE);
                } else {
                    if (action.equalsIgnoreCase(AppConstants.ACTION_ADD)) {
                        if (idKMKH != null && !STRING_EMPTY.equals(idKMKH))
                        // Kiem tra id trung
                        {
                            String strCheckExistItem = " a.tk = ?";
                            params = new Vector();
                            params.add(new Parameter(dmkhForm.getTk(), true));
                            voExist =
                                    dao.getDMKHCheoTK(strCheckExistItem, params);
                            if (voExist != null) {
                                bResult = true;
                                jsonObj.addProperty("Existed", bResult);
                                strJson = jsonObj.toString();
                                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                                PrintWriter writer = response.getWriter();
                                writer.println(strJson.toString());
                                writer.flush();
                                writer.close();
                            }
                        }
                        if (bResult) {
                              if (!response.isCommitted())
                                  return mapping.findForward(AppConstants.SUCCESS);
                              else
                                  return null;
                        } else {
                            // thuc hien add
                            voExist = new DMKHCheoTKVO();
                            BeanUtils util = new BeanUtils();
                            util.copyProperties(voExist, form);
                            if (voExist != null) {
                                String insert = dao.insert(voExist);
                                if (insert.equalsIgnoreCase("inserted")) {
                                    saveVisitLog(conn, session,
                                                 DMKH_CHEO_TK_THEM, "");
                                    conn.commit();
                                }
                            }
                        }
                    }
                }
                if (!checkPermissionOnFunction(request, DMKH_CHEO_TK_SUA)) {
                    return mapping.findForward(PERMISSION_PAGE);
                } else {
                    if (action.equalsIgnoreCase(AppConstants.ACTION_EDIT)) {
                        voExist = new DMKHCheoTKVO();
                        BeanUtils util = new BeanUtils();
                        util.copyProperties(voExist, form);
                        if (voExist != null) {
                            int i = dao.update(voExist);
                            if (i < 0) {
                                return mapping.findForward(AppConstants.FAILURE);
                            } else {
                                saveVisitLog(conn, session, DMKH_CHEO_TK_SUA,
                                             "");
                                conn.commit();
                            }
                        }
                    }
                }
                if (!checkPermissionOnFunction(request, DMKH_CHEO_TK_XOA)) {
                    return mapping.findForward(PERMISSION_PAGE);
                } else {
                    if (action.equalsIgnoreCase(AppConstants.ACTION_CANCEL)) {

                        String idTK = request.getParameter("idTK");
                        if (idTK != null) {
                            int deleteCorrect = dao.delete(idTK.trim());
                            if (deleteCorrect == 1) {
                                request.setAttribute("deleted", "deleted");
                                saveVisitLog(conn, session, DMKH_CHEO_TK_XOA,
                                             "");
                                conn.commit();
                            }
                        }
                    }
                }
            }
          if (action != null) {
              action = null;
          }
          if (strJson != null) {
              response.setContentType(AppConstants.CONTENT_TYPE_JSON);
              PrintWriter writer = response.getWriter();
              writer.println(strJson.toString());
              writer.flush();
              writer.close();
          }
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            close(conn);            
        }

        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
