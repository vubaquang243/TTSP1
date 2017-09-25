package com.seatech.ttsp.ktvtm.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.ktvtm.KTVTabmisDAO;
import com.seatech.ttsp.ktvtm.KTVTabmisVO;
import com.seatech.ttsp.ktvtm.form.KTVTabmisForm;
import com.seatech.ttsp.ktvtmselect.KTVTabmisSelectDAO;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class KTVTabmisAction extends AppAction {
    private static String SUCCESS = "success";
    //    private static String FAILURE = "failure";
    //    private String forward = AppConstants.SUCCESS;
    //    private static String STRING_EMPTY = "";

    // action hiển thị và tìm kiếm KTV

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.KTVTABMIS.TRACUU")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String ma_kb =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString().trim();
            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            KTVTabmisForm f = (KTVTabmisForm)form;
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            String strid = null;
            String strten = null;
            String strma = null;
            String strid_kb = null;
            String strkb_id = null;
            if (null != f) {
                strid = f.getId();
                strten = f.getTen();
                strma = f.getMa();
                strid_kb = f.getTen_kb();
                strkb_id = f.getKb_id();
            }
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            String strWhere = null;
            Vector v_param = null;
            DMKBacDAO dao_kb = new DMKBacDAO(conn);
            Collection coll_kb = null;
            coll_kb = new ArrayList();
            String str_where_kb = "a.ma = " + ma_kb + " or a.ma_cha =" + ma_kb;
            Vector pram_kb = null;
            pram_kb = new Vector();
            coll_kb = dao_kb.getDMKBList(str_where_kb, pram_kb);
            request.setAttribute("lstKB", coll_kb);
            if (strkb_id == null || "".equals(strkb_id)) {
                strWhere =
                        " and (c.ma = " + ma_kb + " or c.ma_cha =" + ma_kb + ")";
            } else {
                strWhere = " and c.id = " + strkb_id;
            }
            if (null != strid && !"".equals(strid)) {
                strWhere = "and a.id=? ";
                v_param = new Vector();
                v_param.add(new Parameter(strid, true));
            }
            if (null != strten && !"".equals(strten)) {
                strWhere = "and lower(a.ten) like lower('%" + strten + "%') ";
            }
            if (null != strma && !"".equals(strma)) {
                strWhere += " and lower(a.ma) like lower('%" + strma + "%') ";
            }
            if (null != strkb_id && !"".equals(strkb_id)) {
                strWhere += " and a.kb_id =" + strkb_id;
            }
            if (!strWhere.equals("")) {
                strWhere +=
                        " and (c.ma = " + ma_kb + " or c.ma_cha =" + ma_kb +
                        ")";
            }
            KTVTabmisDAO ktvDAO = new KTVTabmisDAO(conn);
            List lstKTV = null;
            lstKTV =
                    (List)ktvDAO.getKTVTabmisList_ptrang(strWhere, v_param, currentPage,
                                                         numberRowOnPage,
                                                         totalCount);

            if (lstKTV.isEmpty()) {
                request.setAttribute("status",
                                     "ktvtabmis.listktvtabmis.warning.nhap.khongdung");
                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);
                request.setAttribute("lstKTV", lstKTV);
                return mapping.findForward(SUCCESS);
            } else {
                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);
                request.setAttribute("lstKTV", lstKTV);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            //          resetToken(request);
            //          saveToken(request);
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
    //Hiển thị trang add KTV
    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String fileName = "/DSachKTVTabmis";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.KTVTABMIS.TRACUU")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String ma_kb =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString().trim();

            KTVTabmisForm f = (KTVTabmisForm)form;
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            String strid = null;
            String strten = null;
            String strma = null;
            String strid_kb = null;
            String strkb_id = null;
            if (null != f) {
                strid = f.getId();
                strten = f.getTen();
                strma = f.getMa();
                strid_kb = f.getTen_kb();
                strkb_id = f.getKb_id();
            }
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";

            String strWhere = null;
            Vector v_param = null;
            DMKBacDAO dao_kb = new DMKBacDAO(conn);
            Collection coll_kb = null;
            coll_kb = new ArrayList();
            String str_where_kb = "a.ma = " + ma_kb + " or a.ma_cha =" + ma_kb;
            Vector pram_kb = null;
            pram_kb = new Vector();
            coll_kb = dao_kb.getDMKBList(str_where_kb, pram_kb);
            request.setAttribute("lstKB", coll_kb);
            if (strkb_id == null || "".equals(strkb_id)) {
                strWhere =
                        " and (c.ma = " + ma_kb + " or c.ma_cha =" + ma_kb + ")";
            } else {
                strWhere = " and c.id = " + strkb_id;
                sbSubHTML.append("<input type=\"hidden\" name=\"kb_id\" value=\"" +
                                 strkb_id + "\" id=\"kb_id\"></input>");
            }
            if (null != strid && !"".equals(strid)) {
                strWhere = "and a.id=? ";
                v_param = new Vector();
                v_param.add(new Parameter(strid, true));
                sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                                 strid + "\" id=\"id\"></input>");
            }
            if (null != strten && !"".equals(strten)) {
                strWhere = "and lower(a.ten) like lower('%" + strten + "%') ";
                sbSubHTML.append("<input type=\"hidden\" name=\"ten\" value=\"" +
                                 strten + "\" id=\"ten\"></input>");
            }
            if (null != strma && !"".equals(strma)) {
                strWhere += " and lower(a.ma) like lower('%" + strma + "%') ";
                sbSubHTML.append("<input type=\"hidden\" name=\"ma\" value=\"" +
                                 strma + "\" id=\"ma\"></input>");
            }
            if (null != strkb_id && !"".equals(strkb_id)) {
                strWhere += " and a.kb_id =" + strkb_id;
            }
            if (!strWhere.equals("")) {
                strWhere +=
                        " and (c.ma = " + ma_kb + " or c.ma_cha =" + ma_kb +
                        ")";
            }
            KTVTabmisDAO ktvDAO = new KTVTabmisDAO(conn);
            ResultSet lstKTV = null;
            lstKTV = ktvDAO.getKTVTabmisResultSet(strWhere, v_param);

            if (lstKTV == null) {
                request.setAttribute("status",
                                     "ktvtabmis.listktvtabmis.warning.nhap.khongdung");
            } else {
                JasperPrint jasperPrint = null;
                HashMap parameterMap = null;
                ReportUtility rpUtilites = new ReportUtility();
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
                JRDataSource jrDS = new JRResultSetDataSource(lstKTV);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     jrDS);

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "KTVTabmisPrintAction.do";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    REPORT_DIRECTORY + strFontTimeRoman;

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
//                e.printStackTrace();
            }
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.KTVTABMIS.THEM")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        try {
            // lấy kho bạc
            conn = getConnection();
            HttpSession session = request.getSession();
            String ma_kb =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString().trim();
            //            Vector v_param = null;
            DMKBacDAO dao_kb = new DMKBacDAO(conn);
            Collection coll_kb = null;
            coll_kb = new ArrayList();
            String str_where_kb = "a.ma = " + ma_kb + " or a.ma_cha =" + ma_kb;
            Vector pram_kb = null;
            pram_kb = new Vector();
            coll_kb = dao_kb.getDMKBList(str_where_kb, pram_kb);
            request.setAttribute("lstKB", coll_kb);

            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
            //          resetToken(request);
            //          saveToken(request);
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
    // Action thực hiện add KTV

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.KTVTABMIS.THEM")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                HttpSession session = request.getSession();
                Long nUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                Long nKBID =
                    new Long(session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString());
                conn = getConnection();
                KTVTabmisForm f = (KTVTabmisForm)form;
                KTVTabmisVO vo = new KTVTabmisVO();
                BeanUtils.copyProperties(vo, f);
                vo.setNguoi_tao(nUserID);
                vo.setKb_id(nKBID);
                KTVTabmisDAO ktvDAO = new KTVTabmisDAO(conn);
                //Kiem tra ma KTV da ton tai trong db chua
                String strWhere = " and LOWER(ma) = ? ";
                Vector vParam = new Vector();
                vParam.add(new Parameter(vo.getMa().toLowerCase(), true));
                KTVTabmisVO ktvVO = ktvDAO.getKTVTabmis(strWhere, vParam);
                request.setAttribute("nsdID", f.getMa());

                if (ktvVO != null) {
                    //�?ã tồn tại KTV mã số :

                    String ma_kb =
                        session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString().trim();
                    //                    Vector v_param = null;
                    DMKBacDAO dao_kb = new DMKBacDAO(conn);
                    Collection coll_kb = null;
                    coll_kb = new ArrayList();
                    String str_where_kb =
                        "a.ma = " + ma_kb + " or a.ma_cha =" + ma_kb;
                    Vector pram_kb = null;
                    pram_kb = new Vector();
                    coll_kb = dao_kb.getDMKBList(str_where_kb, pram_kb);
                    request.setAttribute("lstKB", coll_kb);
                    f.getTen();
                    request.setAttribute("status",
                                         "ktvtabmis.listktvtabmis.warning.sua.tontai");
                    return mapping.findForward("failure");
                }

                else {
                    vo.setKb_id(new Long(f.getKb_id().toString()));
                    int i = ktvDAO.insert(vo);
                    if (i == 1) {
                        request.setAttribute("status",
                                             "ktvtabmis.listktvtabmis.success.them");
                        saveVisitLog(conn, session, "QLY_NSD.KTVTABMIS.THEM", "");
                        f.setMa(null);
                        f.setTen(null);
                        f.setKb_id(null);
                        f.reset(mapping, request);
                        conn.commit();
                        list(mapping, f, request, response);
                        return mapping.findForward(SUCCESS);

                    } else {

                        request.setAttribute("status",
                                             "ktvtabmis.listktvtabmis.failure.them");
                    }
                    conn.commit();
                }
            }
            resetToken(request);
            saveToken(request);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    //Action hiển thị trang


    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.KTVTABMIS.SUA")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String ma_kb =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString().trim();
            //            Vector v_param = null;
            DMKBacDAO dao_kb = new DMKBacDAO(conn);
            Collection coll_kb = null;
            coll_kb = new ArrayList();
            String str_where_kb = "a.ma = " + ma_kb + " or a.ma_cha =" + ma_kb;
            Vector pram_kb = null;

            pram_kb = new Vector();
            coll_kb = dao_kb.getDMKBList(str_where_kb, pram_kb);
            request.setAttribute("lstKB", coll_kb);
            //
            KTVTabmisForm f = (KTVTabmisForm)form;
            f.setKb_id(request.getParameter("longkb_id").trim());
            f.setId(request.getParameter("longid").trim());
            f.setMa(request.getParameter("longma").trim());
            f.setTen(request.getParameter("strten").trim());
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
            //          resetToken(request);
            //          saveToken(request);
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
    //action deer thucj hien update

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "QLY_NSD.KTVTABMIS.SUA")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                HttpSession session = request.getSession();
                //                Long nUserID =
                //                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                conn = getConnection();
                KTVTabmisForm f = (KTVTabmisForm)form;
                KTVTabmisVO vo = new KTVTabmisVO();
                BeanUtils.copyProperties(vo, f);
                KTVTabmisDAO ktvDAO = new KTVTabmisDAO(conn);
                //ktra tn tai
                //                String strWhere = " and LOWER(ma) = ? ";
                Vector vParam = new Vector();
                vParam.add(new Parameter(vo.getMa().toLowerCase(), true));
                //                KTVTabmisVO ktvVO = ktvDAO.getKTVTabmis(strWhere, vParam);
                request.setAttribute("nsdID", f.getMa());

                f.setId(f.getId().trim());
                request.setAttribute("nsdID", f.getMa());
                //vo.setTen(f.getTen());
                // vo.setId(Long.valueOf(f.getId().trim()));

                BeanUtils.copyProperties(vo, f);
                int i = ktvDAO.update(vo);
                if (i > 0) {

                    request.setAttribute("status",
                                         "ktvtabmis.listktvtabmis.success.sua");
                    saveVisitLog(conn, session, "QLY_NSD.KTVTABMIS.SUA", "");
                    f.setMa(null);
                    f.setTen(null);
                    f.setKb_id(null);
                    f.setId(null);
                    f.reset(mapping, request);
                    conn.commit();
                    list(mapping, f, request, response);
                    return mapping.findForward(SUCCESS);
                } else {
                    request.setAttribute("status",
                                         "ktvtabmis.listktvtabmis.failure.sua");
                }


            }

            resetToken(request);
            saveToken(request);

        } catch (Exception e) {
            throw e;
        } finally {

            close(conn);
        }
        return mapping.findForward(SUCCESS);

    }

    //Action thực hiện update KTV

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.KTVTABMIS.XOA")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        try {

            HttpSession session = request.getSession();
            //            Long nUserID =
            //                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            conn = getConnection();
            KTVTabmisForm f = (KTVTabmisForm)form;
            f.setId(request.getParameter("longid").trim());
            request.setAttribute("nsdID", f.getMa());
            KTVTabmisVO vo = new KTVTabmisVO();
            KTVTabmisDAO dao = new KTVTabmisDAO(conn);
            KTVTabmisSelectDAO daoSelect = new KTVTabmisSelectDAO(conn);
            Vector vParam = new Vector();

            //Lay ra vo se bi xoa

            String strWhere = " and id = ? ";
            vParam.add(new Parameter(f.getId(), true));
            vo = dao.getKTVTabmis(strWhere, vParam);
            if (vo == null) {
                request.setAttribute("status",
                                     "ktvtabmis.listktvtabmis.failure.xoa.daxoa");
            } else {
                String strWhere1 = " ktv_id = ? ";
                Vector vParam1 = new Vector();
                vParam1.add(new Parameter(f.getId(), true));
                Long lID = new Long(f.getId());
                daoSelect.delete(strWhere1, vParam);
                int i = dao.delete(Long.valueOf(lID));
                if (i > 0) {
                    request.setAttribute("status",
                                         "ktvtabmis.listktvtabmis.success.xoa");
                    saveVisitLog(conn, session, "QLY_NSD.KTVTABMIS.XOA", "");
                    f.setId("");
                    conn.commit();
                    return mapping.findForward(SUCCESS);
                } else {
                    request.setAttribute("status",
                                         "ktvtabmis.listktvtabmis.failure.xoa");
                }


            }

        }


        catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

}

