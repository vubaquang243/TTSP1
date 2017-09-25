package com.seatech.ttsp.user.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.nhomnsd.NhomNSDDAO;
import com.seatech.ttsp.nhomnsd.NhomNSDVO;
import com.seatech.ttsp.nhomnsd.PhanNhomDAO;
import com.seatech.ttsp.nhomnsd.PhanNhomVO;
import com.seatech.ttsp.proxy.ad.ActiveDirectory;
import com.seatech.ttsp.proxy.ad.ActiveDirectoryBean;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserHistoryDAO;
import com.seatech.ttsp.user.UserHistoryVO;
import com.seatech.ttsp.user.UserVO;
import com.seatech.ttsp.user.form.QuanLyNSDForm;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class QuanLyNSDAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";
    //    private String forward = AppConstants.SUCCESS;
    //    private static String STRING_EMPTY = "";

    // action hiển thị và tìm kiếm KTV

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.QLY")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        String quyen = null;
        Long lkb_id = null;
        Long lma_kb = null;
        Long lnsd_id = null;
        //        String strwhere_nhom = null;
        String strNhom_id = null;

        try {

            conn = getConnection(request);
            QuanLyNSDForm f = (QuanLyNSDForm)form;
            //            if (("".equalsIgnoreCase(f.getMa_kb()) || null == f.getMa_kb()) &&
            //                ("".equalsIgnoreCase(f.getTen_kb()) ||
            //                 null == f.getTen_kb())) {
            //                f.setId_kb("");
            //            }
            HttpSession session = request.getSession();

            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            lnsd_id =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            quyen =
                    (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString());
            lkb_id =
                    new Long(session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString());
            //            lma_kb =
            //                    new Long(session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString());
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();


            String strid = f.getId();
            String strten = f.getTen_nsd();
            String strmaNsd = f.getMa_nsd();
            String strmaKB = f.getMa_kb();
            String strtrangThai = f.getTrang_thai();


            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            String strWhere = "";
            Vector v_param = null;

            DChieu1DAO dcdao = new DChieu1DAO(conn);
            DChieu1VO dcvo = new DChieu1VO();
            String strCap = " and ma=" + kb_code;
            dcvo = dcdao.getCap(strCap, null);
            String cap = dcvo.getCap();

            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                if (strten != null && !strten.equals("")) {
                    strWhere +=
                            " and lower(a.ten_nsd) like lower('%" + strten +
                            "%') ";
                }
                if (strmaNsd != null && !strmaNsd.equals("")) {
                    strWhere +=
                            " and lower(a.ma_nsd) like lower('%" + strmaNsd +
                            "%') ";
                }

                if (strten != null && !strten.equals("")) {
                    strWhere +=
                            " and lower(a.ten_nsd) like lower('%" + strten +
                            "%') ";
                }

                if (strmaKB != null && !strmaKB.equals("")) {
                    strWhere += " and b.ma =" + strmaKB;
                }
                if (strtrangThai != null && !strtrangThai.equals("")) {
                    strWhere +=
                            " and lower(a.trang_thai) like lower('%" + strtrangThai +
                            "%') ";
                }
            } else if ("5".equals(cap)) {

                if (strten != null && !strten.equals("")) {
                    strWhere +=
                            " and lower(a.ten_nsd) like lower('%" + strten +
                            "%') ";
                }
                if (strmaNsd != null && !strmaNsd.equals("")) {
                    strWhere +=
                            " and lower(a.ma_nsd) like lower('%" + strmaNsd +
                            "%') ";
                }
                if (lkb_id != null && !lkb_id.equals("")) {
                    strWhere +=
                            " and (b.id =" + lkb_id + " or  b.id_cha =" + lkb_id +
                            ")";
                }
                if (strtrangThai != null && !strtrangThai.equals("")) {
                    strWhere +=
                            " and lower(a.trang_thai) like lower('%" + strtrangThai +
                            "%') ";
                }
                if (strmaKB != null && !strmaKB.equals("")) {
                    strWhere += " and b.ma =" + strmaKB;
                }
            }


            UserDAO dao = new UserDAO(conn);
            List listNSD = null;
            listNSD =
                    (List)dao.getUserList_ptrang(strWhere, v_param, strNhom_id,
                                                 currentPage, numberRowOnPage,
                                                 totalCount);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstNSD", listNSD);
            if (listNSD.isEmpty()) {
                request.setAttribute("status",
                                     "quanlynsd.listnsd.warning.ketqua.null");
            }
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String fileName = "/DanhSachNSD";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.QLY")) {
            return mapping.findForward("errorQuyen");
        }
        String reportName = "/DanhSachNSD";
        Connection conn = null;
        String quyen = null;
        Long lkb_id = null;
        Long lma_kb = null;
        Long lnsd_id = null;
        //        String strwhere_nhom = null;
        String strNhom_id = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {

            conn = getConnection(request);
            QuanLyNSDForm f = (QuanLyNSDForm)form;
            if (("".equalsIgnoreCase(f.getMa_kb()) || null != f.getMa_kb()) &&
                ("".equalsIgnoreCase(f.getTen_kb()) ||
                 null != f.getTen_kb())) {
                f.setId_kb("");
            }
            HttpSession session = request.getSession();

            lnsd_id =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            quyen =
                    (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString());
            lkb_id =
                    new Long(session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString());
            lma_kb =
                    new Long(session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString());

            String[] temp = quyen.split("\\|");
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            String strid = null;
            String strten = null;
            String strmaNsd = null;
            String strmaKB = null;
            String strtrangThai = null;
            if (null != f.getId()) {
                strid = f.getId().toUpperCase().trim();
                sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                                 f.getId() + "\" id=\"id\"></input>");
            }
            if (null != f.getTen_nsd()) {
                strten = f.getTen_nsd().toUpperCase().trim();
                sbSubHTML.append("<input type=\"hidden\" name=\"ten_nsd\" value=\"" +
                                 f.getTen_nsd() +
                                 "\" id=\"ten_nsd\"></input>");
            }
            if (null != f.getMa_nsd()) {
                strmaNsd = f.getMa_nsd().toUpperCase().trim();
                sbSubHTML.append("<input type=\"hidden\" name=\"ma_nsd\" value=\"" +
                                 f.getMa_nsd() + "\" id=\"ma_nsd\"></input>");
            }
            if (null != f.getKb_id()) {
                strmaKB = f.getKb_id().toUpperCase().trim();
                sbSubHTML.append("<input type=\"hidden\" name=\"kb_id\" value=\"" +
                                 f.getKb_id() + "\" id=\"kb_id\"></input>");
            }
            if (null != f.getTrang_thai()) {
                strtrangThai = f.getTrang_thai().toUpperCase().trim();
                sbSubHTML.append("<input type=\"hidden\" name=\"trang_thai\" value=\"" +
                                 f.getTrang_thai() +
                                 "\" id=\"trang_thai\"></input>");
            }
            //lay ra nhom nsd cach 2
            String strNhom_nsd = "";
            NhomNSDDAO dao_nhom_nsd = new NhomNSDDAO(conn);
            NhomNSDVO vo_nhom = new NhomNSDVO();
            Collection coll_nhom_nsd = new ArrayList();
            Vector param_nhom_nsd = null;
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].toString().equalsIgnoreCase("QTHT-TW")) {
                    if (!"".equals(strNhom_nsd)) {
                        strNhom_nsd +=
                                " and a.loai_nhom in ('QTHT-TW','QTHT-DV','CBPT-TTTT','CB-TTTT')";
                    } else {
                        strNhom_nsd +=
                                " a.loai_nhom in ('QTHT-TW','QTHT-DV','CBPT-TTTT','CB-TTTT')";
                    }
                    coll_nhom_nsd =
                            dao_nhom_nsd.getNhomNSDList(strNhom_nsd, param_nhom_nsd);
                    Iterator iter = coll_nhom_nsd.iterator();
                    int nCounter = 0;

                    while (iter.hasNext()) {
                        vo_nhom = (NhomNSDVO)iter.next();

                        if (nCounter == 0) {
                            if (null != strNhom_id || "".equals(strNhom_id)) {
                                strNhom_id +=
                                        " or a.nhom_id =" + vo_nhom.getId();
                            } else {
                                strNhom_id = " a.nhom_id =" + vo_nhom.getId();
                            }
                        }
                        if (nCounter > 0) {
                            strNhom_id += " or a.nhom_id =" + vo_nhom.getId();
                        }
                        nCounter++;

                    }

                }
                if (temp[i].toString().equalsIgnoreCase("QTHT-DV")) {
                    if (!"".equals(strNhom_nsd)) {
                        strNhom_nsd += " and a.loai_nhom in ('TTV')";
                    } else {
                        strNhom_nsd += " a.loai_nhom in ('TTV')";
                    }
                    coll_nhom_nsd =
                            dao_nhom_nsd.getNhomNSDList(strNhom_nsd, param_nhom_nsd);
                    Iterator iter = coll_nhom_nsd.iterator();
                    int nCounter = 0;
                    while (iter.hasNext()) {
                        vo_nhom = (NhomNSDVO)iter.next();
                        if (nCounter == 0) {
                            if (null != strNhom_id || "".equals(strNhom_id)) {
                                strNhom_id +=
                                        " or a.nhom_id =" + vo_nhom.getId();
                            } else {
                                strNhom_id = " a.nhom_id =" + vo_nhom.getId();
                            }
                        }
                        if (nCounter > 0) {
                            strNhom_id += " or a.nhom_id =" + vo_nhom.getId();
                        }
                        nCounter++;

                    }

                }
                if (temp[i].toString().equalsIgnoreCase("CB-TTTT") ||
                    temp[i].toString().equalsIgnoreCase("CBPT-TTTT")) {

                    if (!"".equals(strNhom_nsd)) {
                        strNhom_nsd += " and a.loai_nhom in ('GD','KTT')";
                    } else {
                        strNhom_nsd += " a.loai_nhom in ('GD','KTT')";
                    }
                    coll_nhom_nsd =
                            dao_nhom_nsd.getNhomNSDList(strNhom_nsd, param_nhom_nsd);
                    Iterator iter = coll_nhom_nsd.iterator();
                    int nCounter = 0;
                    while (iter.hasNext()) {
                        vo_nhom = (NhomNSDVO)iter.next();
                        if (nCounter == 0) {
                            if (null != strNhom_id || "".equals(strNhom_id)) {
                                strNhom_id +=
                                        " or a.nhom_id =" + vo_nhom.getId();
                            } else {
                                strNhom_id = " a.nhom_id =" + vo_nhom.getId();
                            }
                        }
                        if (nCounter > 0) {
                            strNhom_id += " or a.nhom_id =" + vo_nhom.getId();
                        }
                        nCounter++;

                    }

                }
            }
            //          (select DECODE(count(*),0,0,1) from sp_nsd_nhom c where c.nsd_id = a.id and ( a.nhom_id = 201 OR a.nhom_id = 82 )) sua_xoa
            strNhom_id = "";

            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            //          Integer currentPage = new Integer(page);
            //          Integer numberRowOnPage = phantrang;
            //          Integer totalCount[] = new Integer[1];
            String strWhere = "";
            //          Vector v_param = null;
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].toString().equalsIgnoreCase("QTHT-TW")) {
                    if (strten != null && !strten.equals("")) {
                        strWhere =
                                "and lower(a.ten_nsd) like lower('%" + strten +
                                "%') ";
                    } else {
                        strWhere = "";
                    }
                    if (strmaNsd != null && !strmaNsd.equals("")) {
                        strWhere +=
                                " and lower(a.ma_nsd) like lower('%" + strmaNsd +
                                "%') ";
                    }

                    if (strmaKB != null && !strmaKB.equals("")) {
                        strWhere += " and a.kb_id =" + strmaKB;
                    }
                    if (strtrangThai != null && !strtrangThai.equals("")) {
                        strWhere +=
                                " and lower(a.trang_thai) like lower('%" + strtrangThai +
                                "%') ";
                    }

                }
                if (temp[i].toString().equalsIgnoreCase("CB-TTTT") ||
                    temp[i].toString().equalsIgnoreCase("CBPT-TTTT")) {
                    if (strten != null && !strten.equals("")) {
                        strWhere +=
                                " and lower(a.ten_nsd) like lower('%" + strten +
                                "%') ";
                    }
                    if (strmaNsd != null && !strmaNsd.equals("")) {
                        strWhere +=
                                " and lower(a.ma_nsd) like lower('%" + strmaNsd +
                                "%') ";
                    }

                    if (strten != null && !strten.equals("")) {
                        strWhere +=
                                " and lower(a.ten_nsd) like lower('%" + strten +
                                "%') ";
                    }

                    if (strmaKB != null && !strmaKB.equals("")) {
                        strWhere += " and a.kb_id =" + strmaKB;
                    }
                    if (strtrangThai != null && !strtrangThai.equals("")) {
                        strWhere +=
                                " and lower(a.trang_thai) like lower('%" + strtrangThai +
                                "%') ";
                    }
                }
                if (temp[i].toString().equalsIgnoreCase("QTHT-DV")) {

                    if (strten != null && !strten.equals("")) {
                        strWhere +=
                                " and lower(a.ten_nsd) like lower('%" + strten +
                                "%') ";
                    }
                    if (strmaNsd != null && !strmaNsd.equals("")) {
                        strWhere +=
                                " and lower(a.ma_nsd) like lower('%" + strmaNsd +
                                "%') ";
                    }
                    if (lma_kb != null && !lma_kb.equals("")) {
                        strWhere +=
                                " and (b.ma =" + lma_kb + "or  b.ma_cha =" +
                                lma_kb + ")";
                    }
                    if (strtrangThai != null && !strtrangThai.equals("")) {
                        strWhere +=
                                " and lower(a.trang_thai) like lower('%" + strtrangThai +
                                "%') ";
                    }
                    if (strmaKB != null && !strmaKB.equals("")) {
                        strWhere += " and a.kb_id =" + strmaKB;
                    }
                }
            }
            UserDAO dao = new UserDAO(conn);
            ResultSet rsNSD = dao.getUserList(strWhere);

            if (rsNSD == null) {
                request.setAttribute("status",
                                     "quanlynsd.listnsd.warning.ketqua.null");
            } else {

                JasperPrint jasperPrint = null;
                HashMap parameterMap = null;
                ReportUtility rpUtilites = new ReportUtility();
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                "/" +
                                                                                                reportName +
                                                                                                ".jasper");
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     new JRResultSetDataSource(rsNSD));

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "QuanLyNSDPrintAction.do";
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
            e.printStackTrace();
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

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {

        Connection conn = null;
        String strWhere = null;
        Vector params = null;
        String quyen = null;
        try {


            conn = getConnection(request);
            HttpSession session = request.getSession();

            quyen =
                    (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString());
            //            QuanLyNSDForm f = (QuanLyNSDForm)form;
            String[] temp = quyen.split("\\|");
            NhomNSDDAO dao = new NhomNSDDAO(conn);
            Collection listNhomNSD = new ArrayList();
            StringBuffer str1 = null;
            StringBuffer str2 = null;
            String str3 = "";
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].toString().equalsIgnoreCase("QTHT-TW")) {
                    // THI CAU LENH
                    str1 = new StringBuffer();
                    str1.append("'QTHT-TW','QTHT-DV','CB-TTTT','CBPT-TTTT','KTT','GD','TTV'");
                    str3 = " and id not in (347) ";
                }
                //                if (temp[i].toString().equalsIgnoreCase("CB-TTTT") ||
                //                    temp[i].toString().equalsIgnoreCase("CBPT-TTTT")) {
                //                    if (null != str1) {
                //                        str2 = new StringBuffer();
                //                        str2.append(",'KTT','GD'");
                //                        str1.append(str2);
                //                    } else {
                //                        str1 = new StringBuffer();
                //                        str1.append("'KTT','GD'");
                //                    }
                //                }
                if (temp[i].toString().equalsIgnoreCase("QTHT-DV")) {
                    if (null != str1) {
                        str2 = new StringBuffer();
                        str2.append(",'TTV'");
                        str1.append(str2);
                    } else {
                        str1 = new StringBuffer();
                        str1.append("'TTV'");
                    }
                    str3 = " and id not in (481, 482) ";

                }
            }
            strWhere = "loai_nhom in (" + str1 + ") " + str3;
            listNhomNSD = dao.getNhomNSDList(strWhere, params);

            request.setAttribute("listNNSD", listNhomNSD);
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }
    // Action thực hiện add NSD
    //
    //

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        //        String strWhere = null;
        //        Vector params = null;

        try {
            conn = getConnection();
            QuanLyNSDForm f = (QuanLyNSDForm)form;
            HttpSession session = request.getSession();
            String strWsdl = getThamSoHThong("WSDL_ACTIVE_DIRECTORY", session);
            ActiveDirectory ad = new ActiveDirectory(strWsdl);
            ActiveDirectoryBean bean = new ActiveDirectoryBean();
            bean = ad.getUserInfo(f.getDomain(), f.getMa_nsd());

            if (bean == null) {
                request.setAttribute("status",
                                     "quanlynsd.listnsd.warning.tennsd.active.chuatontai");
                f.setEnable("0");
            } else {
                f.setEnable("1");
                BeanUtils.copyProperties(f, bean);
            }
            return add(mapping, form, request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
    }


    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.QLY.THEMMOI")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        Collection reval = null;
        String nsdID = null;
        String quyen = null;
        try {
            conn = getConnection(request);
            //            if (isTokenValid(request)) {
            HttpSession session = request.getSession();
            QuanLyNSDForm f = (QuanLyNSDForm)form;
            Long nUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            quyen =
                    (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString());
            String[] temp = quyen.split("\\|");
            ;
            f = (QuanLyNSDForm)form;
            Long kb_moi = null;
            kb_moi = new Long(f.getKb_id().trim());
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(vo, f);
            //
            vo.setChuc_danh(f.getTitle());
            vo.setTen_nsd(f.getDisplayname());
            vo.setNguoi_tao(nUserID);
            vo.setDomain(f.getDomain().toUpperCase());
            vo.setMa_nsd(f.getMa_nsd().toUpperCase());
            //
            UserDAO nsdDAO = new UserDAO(conn);
            //Kiem tra ma NSD da ton tai trong db chua
            String strWhere = "  LOWER(ma_nsd) = lower(?) ";
            Vector vParam = new Vector();
            vParam.add(new Parameter(vo.getMa_nsd().toLowerCase(), true));
            //
            nsdID = vo.getMa_nsd();
            //            Long id_nsd = vo.getId();
            /////////////////////////////kiểm tra kho bạc hợp lệ
            //            StringBuffer str1 = null;
            //            StringBuffer str2 = null;
            Long id_khobac_nhap = null;
            Vector prama_kb = null;
            if (null != f.getKb_id()) {
                id_khobac_nhap = new Long(f.getKb_id().trim());
            }
            String strkb_id = null;
            strkb_id =
                    session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();

            for (int i = 0; i < temp.length; i++) {
                if (temp[i].toString().equalsIgnoreCase("QTHT-DV")) {

                    DMKBacDAO kb_dao = new DMKBacDAO(conn);
                    String str_kb = " a.ma_cha = " + strkb_id;
                    str_kb += " and a.id = ?";
                    prama_kb = new Vector();
                    Collection co_kb = null;
                    prama_kb.add(new Parameter(id_khobac_nhap, true));
                    co_kb = kb_dao.getDMKBList(str_kb, prama_kb);
                    if (co_kb.isEmpty()) {
                        request.setAttribute("status",
                                             "quanlynsd.listnsd.warning.khobac.khong.coquyen");
                        f.setMa_kb(null);
                        f.setTen_kb(null);
                        f.setTen_nhom(null);
                        f.setMa_nsd(null);
                        f.setTrang_thai(null);
                        f.setId_kb(null);
                        f.setTen_nsd(null);
                        f.setKb_id(null);
                        f.setMa_tabmis(null);
                        f.setId(null);
                        f.setChuc_danh(null);
                        add(mapping, f, request, response);
                        return mapping.findForward(FAILURE);
                    }
                }
                if (temp[i].toString().equalsIgnoreCase("QTHT-TW")) {
                    DMKBacDAO kb_dao = new DMKBacDAO(conn);
                    String str_kb = " a.ma_cha = '0001' or (a.ma_cha is null ";
                    str_kb += " and a.id = ?)";
                    prama_kb = new Vector();
                    Collection co_kb = null;
                    prama_kb.add(new Parameter(id_khobac_nhap, true));
                    co_kb = kb_dao.getDMKBList(str_kb, prama_kb);
                    if (co_kb.isEmpty()) {
                        request.setAttribute("status",
                                             "quanlynsd.listnsd.warning.khobac.khong.coquyen");
                        f.setMa_kb(null);
                        f.setTen_kb(null);
                        f.setTen_nhom(null);
                        f.setMa_nsd(null);
                        f.setTrang_thai(null);
                        f.setId_kb(null);
                        f.setTen_nsd(null);
                        f.setKb_id(null);
                        f.setMa_tabmis(null);
                        f.setId(null);
                        f.setChuc_danh(null);
                        add(mapping, f, request, response);
                        return mapping.findForward(FAILURE);
                    }
                }
            }
            //kiểm tra đã tồn tại nsd hay chưa
            reval = nsdDAO.getUserList(strWhere, vParam);
            if (!reval.isEmpty()) {
                // da ton tai nsd
                Collection reval1 = null;
                Collection reval1a = null;
                String strWhere1 = strWhere + " and trang_thai =03";
                reval1 = nsdDAO.getUserList(strWhere1, vParam);
                //
                String strWhere2 =
                    strWhere + " and (trang_thai =01 or trang_thai =02) ";
                reval1a = nsdDAO.getUserList(strWhere2, vParam);

                //kiểm tra kho bạc cũ mới có giống nhau k

                if ((!reval1.isEmpty() && reval1a.isEmpty()) ||
                    (reval1.isEmpty() && reval1a.isEmpty())) {
                    //trang thai bang 01 or 02
                    //                    Collection reval2 = null;
                    strWhere += " and kb_id = " + kb_moi;
                    UserVO vo_kb = new UserVO();
                    vo_kb = nsdDAO.getUser(strWhere, vParam);
                    if (null != vo_kb) {
                        //nếu kho bạc mới khác kho bạc cũ thì insert nsd bình thư�?ng
                        //                        Collection reval3 = null;
                        Long idnsd = null;
                        idnsd = vo_kb.getId();
                        if (null != f.getId_nhom() &&
                            !"".equals(f.getId_nhom())) {
                            // kiem tra xem nhom co khac "" hoac null khong
                            Long nhom_id = null;
                            nhom_id = new Long(f.getId_nhom());
                            PhanNhomDAO nhomdao = new PhanNhomDAO(conn);
                            PhanNhomVO nhomvo = new PhanNhomVO();
                            String strnhom =
                                " a.nsd_id = " + idnsd + " and a.nhom_id = " +
                                nhom_id;
                            Vector paranhom = new Vector();
                            nhomvo = nhomdao.getNhomNSD(strnhom, paranhom);

                            //                            if (null != nhomvo) {
                            //                                // k thay doi kho bac kiem tra xet nhom cu bang nhom moi thi se bao la k ton tai
                            //                                request.setAttribute("status",
                            //                                                     "quanlynsd.listnsd.warning.manv.tontai");
                            //                                f.setMa_kb(null);
                            //                                f.setTen_kb(null);
                            //                                f.setTen_nhom(null);
                            //                                f.setMa_nsd(null);
                            //                                f.setTrang_thai(null);
                            //                                f.setId_kb(null);
                            //                                f.setTen_nsd(null);
                            //                                f.setKb_id(null);
                            //                                f.setMa_tabmis(null);
                            //                                f.setId(null);
                            //                                f.setChuc_danh(null);
                            //                                return mapping.findForward(FAILURE);
                            //                            } else {
                            //                                //nhom cu khac nhom moi thi insert
                            Long a = nsdDAO.insert(vo);

                            if (a.longValue() == 0) {
                                // insert k thanh cong
                                request.setAttribute("status",
                                                     "quanlynsd.listnsd.failure.them");
                            } else {
                                // insert thanh cong2
                                PhanNhomDAO qlDAO = new PhanNhomDAO(conn);
                                PhanNhomVO qlVO = new PhanNhomVO();
                                if (null != f.getId_nhom() &&
                                    !"".equals(f.getId_nhom())) {
                                    //kiem tra nhom co ton tai hay khong
                                    // co insert nhom moi
                                    qlVO.setNsd_id(a);
                                    qlVO.setNhom_id(new Long(f.getId_nhom()));
                                    qlVO.setNguoi_tao(nUserID);
                                    int i = qlDAO.insertNsd_nhom(qlVO);

                                    request.setAttribute("status",
                                                         "quanlynsd.listnsd.success.them");
                                    request.setAttribute("nsdID", nsdID);

                                    if (i < 1) {
                                        // insert nhom k thanh cong
                                        request.setAttribute("status",
                                                             "quanlynsd.listnsd.failure.them");
                                        add(mapping, f, request, response);
                                        return mapping.findForward(FAILURE);
                                    } else {
                                        // insert thanh cong
                                        f.setMa_kb(null);
                                        f.setTen_kb(null);
                                        f.setTen_nhom(null);
                                        f.setMa_nsd(null);
                                        f.setTrang_thai(null);
                                        f.setId_kb(null);
                                        f.setTen_nsd(null);
                                        f.setKb_id(null);
                                        f.setMa_tabmis(null);
                                        f.setId(null);
                                        f.setChuc_danh(null);

                                        request.setAttribute("status",
                                                             "quanlynsd.listnsd.success.them");
                                        request.setAttribute("nsdID", nsdID);
                                        // Luu lich su thay doi
                                        UserHistoryVO userHisVO = null;
                                        UserHistoryDAO userHisDAO =
                                            new UserHistoryDAO(conn);
                                        String strNoiDung = "Them NSD " + a;
                                        userHisVO = new UserHistoryVO();
                                        userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                                        userHisVO.setNoi_dung_thaydoi(strNoiDung);
                                        userHisVO.setNsd_id(new Long(a));
                                        userHisDAO.insert(userHisVO);
                                        f.reset(mapping, request);
                                        conn.commit();
                                        f.setMa_kb(null);
                                        f.setTen_kb(null);
                                        f.setTen_nhom(null);
                                        f.setMa_nsd(null);
                                        f.setTrang_thai(null);
                                        f.setId_kb(null);
                                        f.setTen_nsd(null);
                                        f.setKb_id(null);
                                        f.setMa_tabmis(null);
                                        f.setId(null);
                                        f.setChuc_danh(null);
                                        list(mapping, f, request, response);
                                        return mapping.findForward(SUCCESS);

                                    }
                                }
                                //ghi save
                                f.setMa_kb(null);
                                f.setTen_kb(null);
                                f.setTen_nhom(null);
                                f.setMa_nsd(null);
                                f.setTrang_thai(null);
                                f.setId_kb(null);
                                f.setTen_nsd(null);
                                f.setKb_id(null);
                                f.setMa_tabmis(null);
                                f.setId(null);
                                f.setChuc_danh(null);
                                request.setAttribute("status",
                                                     "quanlynsd.listnsd.success.them");

                                request.setAttribute("nsdID", nsdID);
                                // Luu lich su thay doi
                                UserHistoryVO userHisVO = null;
                                UserHistoryDAO userHisDAO =
                                    new UserHistoryDAO(conn);
                                String strNoiDung = "Them NSD " + a;
                                userHisVO = new UserHistoryVO();
                                userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                                userHisVO.setNoi_dung_thaydoi(strNoiDung);
                                userHisVO.setNsd_id(new Long(a));
                                userHisDAO.insert(userHisVO);
                                //                                    saveVisitLog(conn, session,
                                //                                                 "QLY_NSD.QLY.THEMMOI",
                                //                                                  "");
                                f.reset(mapping, request);
                                conn.commit();
                                list(mapping, f, request, response);
                                return mapping.findForward(SUCCESS);
                            }
                        }
                        // nhom kho = "" hoac null
                        else {
                            // insert bt
                            Long a = nsdDAO.insert(vo);
                            if (a.longValue() == 0) {
                                // insert nsd k thanh cong
                                request.setAttribute("status",
                                                     "quanlynsd.listnsd.failure.them");
                            } else {
                                //insert nsd thanh cong
                                //ghi save
                                f.setMa_kb(null);
                                f.setTen_kb(null);
                                f.setTen_nhom(null);
                                f.setMa_nsd(null);
                                f.setTrang_thai(null);
                                f.setId_kb(null);
                                f.setTen_nsd(null);
                                f.setKb_id(null);
                                f.setMa_tabmis(null);
                                f.setId(null);
                                f.setChuc_danh(null);
                                request.setAttribute("status",
                                                     "quanlynsd.listnsd.success.them");

                                request.setAttribute("nsdID", nsdID);
                                // Luu lich su thay doi
                                UserHistoryVO userHisVO = null;
                                UserHistoryDAO userHisDAO =
                                    new UserHistoryDAO(conn);
                                String strNoiDung = "Them NSD " + a;
                                userHisVO = new UserHistoryVO();
                                userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                                userHisVO.setNoi_dung_thaydoi(strNoiDung);
                                userHisVO.setNsd_id(new Long(a));
                                userHisDAO.insert(userHisVO);
                                //                                saveVisitLog(conn, session,
                                //                                             "QLY_NSD.QLY.THEMMOI",
                                //                                              "");
                                f.reset(mapping, request);
                                conn.commit();
                                list(mapping, f, request, response);
                                return mapping.findForward(SUCCESS);
                            }
                        }
                    }
                    // kho bac moi khac kho bac cu
                    else {
                        Long a = nsdDAO.insert(vo);
                        if (a.longValue() == 0) {
                            request.setAttribute("status",
                                                 "quanlynsd.listnsd.failure.them");
                        } else {
                            PhanNhomDAO qlDAO = new PhanNhomDAO(conn);
                            PhanNhomVO qlVO = new PhanNhomVO();
                            if (null != f.getId_nhom() &&
                                !"".equals(f.getId_nhom())) {
                                qlVO.setNsd_id(a);
                                qlVO.setNhom_id(new Long(f.getId_nhom()));
                                qlVO.setNguoi_tao(nUserID);

                                int i = qlDAO.insertNsd_nhom(qlVO);
                                request.setAttribute("status",
                                                     "quanlynsd.listnsd.success.them");
                                request.setAttribute("nsdID", nsdID);
                                // Luu lich su thay doi
                                UserHistoryVO userHisVO = null;
                                UserHistoryDAO userHisDAO =
                                    new UserHistoryDAO(conn);
                                String strNoiDung = "Them NSD " + a;
                                userHisVO = new UserHistoryVO();
                                userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                                userHisVO.setNoi_dung_thaydoi(strNoiDung);
                                userHisVO.setNsd_id(new Long(a));
                                userHisDAO.insert(userHisVO);
                                if (i < 1) {
                                    request.setAttribute("status",
                                                         "quanlynsd.listnsd.failure.them");
                                    add(mapping, f, request, response);
                                    return mapping.findForward(FAILURE);
                                } else {
                                    request.setAttribute("status",
                                                         "quanlynsd.listnsd.success.them");
                                    request.setAttribute("nsdID", nsdID);
                                    // Luu lich su thay doi
                                    userHisVO = new UserHistoryVO();
                                    userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                                    userHisVO.setNoi_dung_thaydoi(strNoiDung);
                                    userHisVO.setNsd_id(new Long(a));
                                    userHisDAO.insert(userHisVO);
                                    f.reset(mapping, request);
                                    conn.commit();
                                    f.setMa_kb(null);
                                    f.setTen_kb(null);
                                    f.setTen_nhom(null);
                                    f.setMa_nsd(null);
                                    f.setTrang_thai(null);
                                    f.setId_kb(null);
                                    f.setTen_nsd(null);
                                    f.setKb_id(null);
                                    f.setMa_tabmis(null);
                                    f.setId(null);
                                    f.setChuc_danh(null);
                                    list(mapping, f, request, response);
                                    return mapping.findForward(SUCCESS);
                                }
                            }
                            //ghi save
                            f.setMa_kb(null);
                            f.setTen_kb(null);
                            f.setTen_nhom(null);
                            f.setMa_nsd(null);
                            f.setTrang_thai(null);
                            f.setId_kb(null);
                            f.setTen_nsd(null);
                            f.setKb_id(null);
                            f.setMa_tabmis(null);
                            f.setId(null);
                            f.setChuc_danh(null);
                            request.setAttribute("status",
                                                 "quanlynsd.listnsd.success.them");

                            request.setAttribute("nsdID", nsdID);
                            // Luu lich su thay doi
                            UserHistoryVO userHisVO = null;
                            UserHistoryDAO userHisDAO =
                                new UserHistoryDAO(conn);
                            String strNoiDung = "Them NSD " + a;
                            userHisVO = new UserHistoryVO();
                            userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                            userHisVO.setNoi_dung_thaydoi(strNoiDung);
                            userHisVO.setNsd_id(new Long(a));
                            userHisDAO.insert(userHisVO);
                            //                            saveVisitLog(conn, session,
                            //                                         "QLY_NSD.QLY.THEMMOI",
                            //                                          "");

                            f.reset(mapping, request);
                            conn.commit();
                            list(mapping, f, request, response);
                            return mapping.findForward(SUCCESS);
                        }
                    }
                } else {
                    //trang thai bang 03
                    request.setAttribute("status",
                                         "quanlynsd.listnsd.warning.manv.tontai");
                    f.setMa_kb(null);
                    f.setTen_kb(null);
                    f.setTen_nhom(null);
                    f.setMa_nsd(null);
                    f.setTrang_thai(null);
                    f.setId_kb(null);
                    f.setTen_nsd(null);
                    f.setKb_id(null);
                    f.setMa_tabmis(null);
                    f.setId(null);
                    f.setChuc_danh(null);
                    add(mapping, f, request, response);
                    return mapping.findForward(FAILURE);
                }

            }
            // chua ton tai nsd
            else {
                // save bt
                Long a = nsdDAO.insert(vo);

                if (a.longValue() == 0) {
                    request.setAttribute("status",
                                         "quanlynsd.listnsd.failure.them");
                } else {
                    PhanNhomDAO qlDAO = new PhanNhomDAO(conn);
                    PhanNhomVO qlVO = new PhanNhomVO();
                    if (null != f.getId_nhom() && !"".equals(f.getId_nhom())) {
                        qlVO.setNsd_id(a);
                        qlVO.setNhom_id(new Long(f.getId_nhom()));
                        qlVO.setNguoi_tao(nUserID);
                        int i = qlDAO.insertNsd_nhom(qlVO);
                        request.setAttribute("status",
                                             "quanlynsd.listnsd.success.them");
                        request.setAttribute("nsdID", nsdID);
                        PhanNhomDAO daonhom = new PhanNhomDAO(conn);
                        PhanNhomVO vonhom = new PhanNhomVO();
                        daonhom.insertNsd_nhom(vonhom);

                        if (i < 1) {
                            request.setAttribute("status",
                                                 "quanlynsd.listnsd.failure.them");
                            add(mapping, f, request, response);
                            return mapping.findForward(FAILURE);
                        } else {
                            request.setAttribute("status",
                                                 "quanlynsd.listnsd.success.them");
                            request.setAttribute("nsdID", nsdID);
                            // Luu lich su thay doi
                            UserHistoryVO userHisVO = null;
                            UserHistoryDAO userHisDAO =
                                new UserHistoryDAO(conn);
                            String strNoiDung = "Them NSD " + a;
                            userHisVO = new UserHistoryVO();
                            userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                            userHisVO.setNoi_dung_thaydoi(strNoiDung);
                            userHisVO.setNsd_id(new Long(a));
                            userHisDAO.insert(userHisVO);
                            f.reset(mapping, request);
                            conn.commit();
                            f.setMa_kb(null);
                            f.setTen_kb(null);
                            f.setTen_nhom(null);
                            f.setMa_nsd(null);
                            f.setTrang_thai(null);
                            f.setId_kb(null);
                            f.setTen_nsd(null);
                            f.setKb_id(null);
                            f.setMa_tabmis(null);
                            f.setId(null);
                            f.setChuc_danh(null);
                            list(mapping, f, request, response);
                            return mapping.findForward(SUCCESS);

                        }
                    }
                    //ghi save
                    request.setAttribute("status",
                                         "quanlynsd.listnsd.success.them");

                    request.setAttribute("nsdID", nsdID);
                    // Luu lich su thay doi
                    UserHistoryVO userHisVO = null;
                    UserHistoryDAO userHisDAO = new UserHistoryDAO(conn);
                    String strNoiDung = "Them NSD " + a;
                    userHisVO = new UserHistoryVO();
                    userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                    userHisVO.setNoi_dung_thaydoi(strNoiDung);
                    userHisVO.setNsd_id(new Long(a));
                    userHisDAO.insert(userHisVO);
                    //                    saveVisitLog(conn, session,
                    //                                 "QLY_NSD.QLY.THEMMOI",
                    //                                  "");
                    f.reset(mapping, request);
                    conn.commit();
                    f.setMa_kb(null);
                    f.setTen_kb(null);
                    f.setTen_nhom(null);
                    f.setMa_nsd(null);
                    f.setTrang_thai(null);
                    f.setId_kb(null);
                    f.setTen_nsd(null);
                    f.setKb_id(null);
                    f.setMa_tabmis(null);
                    f.setId(null);
                    f.setChuc_danh(null);
                    list(mapping, f, request, response);
                    return mapping.findForward(SUCCESS);
                }
            }
            //            }
            //
            //            resetToken(request);
            //            saveToken(request);
            //
            //            f = new QuanLyNSDForm();
        } catch (Exception e) {
            request.setAttribute("status", "all.error.system");
            request.setAttribute("nsdID", "addExc");
            throw e;
        } finally {

            close(conn);

        }
        return mapping.findForward(SUCCESS);

    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.QLY.SUA")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        Vector vParam = null;
        String quyen = null;
        try {
            HttpSession session = request.getSession();
            conn = getConnection(request);
            quyen =
                    (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString());
            String[] temp = quyen.split("\\|");
            QuanLyNSDForm f = (QuanLyNSDForm)form;
            //            String id_kb = request.getParameter("id_kb");
            f.setId(request.getParameter("longid").trim());
            f.setMa_kb(request.getParameter("makb").trim());
            f.setTen_nsd(request.getParameter("tennsd").trim());
            f.setChuc_danh(request.getParameter("chucdanh").trim());
            f.setTrang_thai(request.getParameter("trangthai").trim());
            f.setMa_tabmis(request.getParameter("matabims").trim());
            f.setMa_nsd(request.getParameter("masnd").trim());
            //            f.setMac_address(request.getParameter("macaddress").trim());
            f.setTen_may_truycap(request.getParameter("tenmaytruycap"));
            f.setUser_may_truycap(request.getParameter("usermaytruycap"));

            f.setNguoi_tao(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            String strwhere = " AND a.id =?";
            vParam = new Vector();
            vParam.add(new Parameter(f.getMa_kb().toLowerCase(), true));
            DMKBacDAO dao = new DMKBacDAO(conn);
            DMKBacVO vo = new DMKBacVO();
            vo = dao.getDMKB(strwhere, vParam);
            if (vo != null) {
                f.setMa_kb(vo.getMa());
                f.setTen_kb(vo.getTen());
            }
            //check quyen sua xoa
            UserDAO use_dao = new UserDAO(conn);
            UserVO use_vo = new UserVO();
            String strwhere_use = " a.id=? ";//and a.nguoi_tao=?";
            vParam = new Vector();
            vParam.add(new Parameter(f.getId(), true));
//            vParam.add(new Parameter(f.getNguoi_tao(), true));
            use_vo = use_dao.getUser(strwhere_use, vParam);
            //
            String strNhom_nsd = "";
            Collection coll_nhom_nsd = new ArrayList();
            NhomNSDDAO dao_nhom_nsd = new NhomNSDDAO(conn);
            NhomNSDVO vo_nhom = new NhomNSDVO();
            String strNhom_id = null;
            Vector param_nhom_nsd = new Vector();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].toString().equalsIgnoreCase("QTHT-TW")) {
                    if (!"".equals(strNhom_nsd)) {
                        strNhom_nsd +=
                                " and a.loai_nhom in ('QTHT-TW','QTHT-DV','CBPT-TTTT','CB-TTTT','KTT','GD')";
                    } else {
                        strNhom_nsd +=
                                " a.loai_nhom in ('QTHT-TW','QTHT-DV','CBPT-TTTT','CB-TTTT','KTT','GD')";
                    }
                    coll_nhom_nsd =
                            dao_nhom_nsd.getNhomNSDList(strNhom_nsd, param_nhom_nsd);
                    Iterator iter = coll_nhom_nsd.iterator();
                    int nCounter = 0;

                    while (iter.hasNext()) {
                        vo_nhom = (NhomNSDVO)iter.next();

                        if (nCounter == 0) {
                            if (null != strNhom_id || "".equals(strNhom_id)) {
                                strNhom_id +=
                                        " or a.nhom_id =" + vo_nhom.getId();
                            } else {
                                strNhom_id = " a.nhom_id =" + vo_nhom.getId();
                            }
                        }
                        if (nCounter > 0) {
                            strNhom_id += " or a.nhom_id =" + vo_nhom.getId();
                        }
                        nCounter++;

                    }
                    strNhom_id += " or a.nhom_id = 481";

                }
                if (temp[i].toString().equalsIgnoreCase("QTHT-DV")) {
                    if (!"".equals(strNhom_nsd)) {
                        strNhom_nsd += " and a.loai_nhom in ('TTV')";
                    } else {
                        strNhom_nsd += " a.loai_nhom in ('TTV')";
                    }
                    coll_nhom_nsd =
                            dao_nhom_nsd.getNhomNSDList(strNhom_nsd, param_nhom_nsd);
                    Iterator iter = coll_nhom_nsd.iterator();
                    int nCounter = 0;
                    while (iter.hasNext()) {
                        vo_nhom = (NhomNSDVO)iter.next();
                        if (nCounter == 0) {
                            if (null != strNhom_id || "".equals(strNhom_id)) {
                                strNhom_id +=
                                        " or a.nhom_id =" + vo_nhom.getId();
                            } else {
                                strNhom_id = " a.nhom_id =" + vo_nhom.getId();
                            }
                        }
                        if (nCounter > 0) {
                            strNhom_id += " or a.nhom_id =" + vo_nhom.getId();
                        }
                        nCounter++;

                    }

                }
                if (temp[i].toString().equalsIgnoreCase("CB-TTTT") ||
                    temp[i].toString().equalsIgnoreCase("CBPT-TTTT")) {

                    if (!"".equals(strNhom_nsd)) {
                        strNhom_nsd += " and a.loai_nhom in ('GD','KTT')";
                    } else {
                        strNhom_nsd += " a.loai_nhom in ('GD','KTT')";
                    }
                    coll_nhom_nsd =
                            dao_nhom_nsd.getNhomNSDList(strNhom_nsd, param_nhom_nsd);
                    Iterator iter = coll_nhom_nsd.iterator();
                    int nCounter = 0;


                    while (iter.hasNext()) {
                        vo_nhom = (NhomNSDVO)iter.next();
                        if (nCounter == 0) {
                            if (null != strNhom_id || "".equals(strNhom_id)) {
                                strNhom_id +=
                                        " or a.nhom_id =" + vo_nhom.getId();
                            } else {
                                strNhom_id = " a.nhom_id =" + vo_nhom.getId();
                            }
                        }
                        if (nCounter > 0) {
                            strNhom_id += " or a.nhom_id =" + vo_nhom.getId();
                        }
                        nCounter++;

                    }
                }
            }

            NhomNSDDAO nhom_dao = new NhomNSDDAO(conn);
            NhomNSDVO nhom_vo = new NhomNSDVO();
            strNhom_id = "a.nsd_id = ? and ( " + strNhom_id + ")";
            vParam = new Vector();
            vParam.add(new Parameter(f.getId(), true));
            nhom_vo = nhom_dao.getNSDNhom(strNhom_id, vParam);

            //
            if (null != use_vo || null != nhom_vo) {
                return mapping.findForward(SUCCESS);
            } else {
                f.setMa_kb(null);
                f.setTen_kb(null);
                f.setTen_nhom(null);
                f.setMa_nsd(null);
                f.setTrang_thai(null);
                f.setId_kb(null);
                f.setTen_nsd(null);
                f.setKb_id(null);
                f.setMa_tabmis(null);
                f.setId(null);
                f.setChuc_danh(null);
                request.setAttribute("status",
                                     "quanlynsd.listnsd.warning.nsd.xoa.failure.nsd.khongthuocquyen");
                return mapping.findForward("back");
            }

        } catch (Exception e) {
            throw e;
        } finally {

            close(conn);

        }


    }
    //action deer thucj hien update


    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "QLY_NSD.QLY.SUA")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        String nsdID = null;
        String strTrangThai = null;
        Long lid = 00L;
        String str_dts = "";

        try {
            HttpSession session = request.getSession();

            conn = getConnection(request);
            QuanLyNSDForm f = (QuanLyNSDForm)form;
            nsdID = f.getMa_nsd();
            UserVO vo = new UserVO();
            vo.setLogin_failure(0L);

            Long id_nsd = new Long(f.getId());
            vo.setId(new Long(f.getId()));
            vo.setTrang_thai(f.getTrang_thai());
            vo.setChuc_danh(f.getChuc_danh());
            vo.setTen_nsd(f.getTen_nsd());
            vo.setLogin_failure(new Long(0));
            vo.setMa_tabmis(f.getMa_tabmis());
            UserDAO useDAO = new UserDAO(conn);
            Vector vParam = new Vector();
            vParam.add(new Parameter(f.getId(), true));

            String strTrang_thai = f.getTrang_thai();
            if (strTrang_thai.equalsIgnoreCase("03")) {
                String strWhere1 = " a.id =?";
                UserVO vo2 = new UserVO();
                vo2 = useDAO.getUser(strWhere1, vParam);
                // check trong dts
                str_dts = " and (a.ttv_nhan = ? or a.ktt_duyet = ?)";
                Vector prama_dts = new Vector();
                prama_dts.add(new Parameter(vo2.getId(), true));
                prama_dts.add(new Parameter(vo2.getId(), true));
                str_dts +=
                        " and a.trang_thai in ('00','01','02','10','11','14') ";
                Collection coll_dts = null;
                coll_dts = new ArrayList();
                DTSoatDAO dao_dts = new DTSoatDAO(conn);
                coll_dts = dao_dts.getDTSList(str_dts, prama_dts);
                //check trong ltt
                String str_ltt =
                    " AND (t.ttv_nhan =? or t.ktt_duyet =? or t.gd_duyet =? )";
                Vector prama_ltt = new Vector();
                prama_ltt.add(new Parameter(vo2.getId(), true));
                prama_ltt.add(new Parameter(vo2.getId(), true));
                prama_ltt.add(new Parameter(vo2.getId(), true));

                str_ltt += " and t.trang_thai  in ('01','02','03','04','05','07','11')";

                LTTDAO dao_ltt = new LTTDAO(conn);
                ArrayList<LTTVO> coll_ltt =
                    (ArrayList<LTTVO>)dao_ltt.chkNgungLTT(str_ltt, prama_ltt);
                LTTVO lttVO = new LTTVO();
                lttVO = coll_ltt.get(0);
                String chkLTT = lttVO.getChklist();
                if (coll_dts.isEmpty() && ("0".equals(chkLTT))) {
                    if (null != vo.getId()) {
                        lid = vo.getId();
                    }
                    int i = useDAO.update(vo);
                    if (i > 0) {
                        request.setAttribute("status",
                                             "quanlynsd.listnsd.success.sua");

                        // Luu lich su thay doi
                        UserHistoryVO userHisVO = null;
                        UserHistoryDAO userHisDAO = new UserHistoryDAO(conn);
                        String strNoiDung = "Sua NSD " + id_nsd;
                        userHisVO = new UserHistoryVO();
                        userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                        userHisVO.setNoi_dung_thaydoi(strNoiDung);
                        userHisVO.setNsd_id(new Long(id_nsd));
                        userHisDAO.insert(userHisVO);

                        f.setMa_kb(null);
                        f.setTen_kb(null);
                        f.setTen_nhom(null);
                        f.setMa_nsd(null);
                        f.setTrang_thai(null);
                        f.setId_kb(null);
                        f.setTen_nsd(null);
                        f.setKb_id(null);
                        f.setMa_tabmis(null);
                        f.setId(null);
                        f.setChuc_danh(null);
                        request.setAttribute("nsdID", nsdID);
                        //                            saveVisitLog(conn, session,
                        //                                         "QLY_NSD.QLY.SUA",
                        //                                          "");
                        f.reset(mapping, request);
                        conn.commit();
                        list(mapping, f, request, response);
                        return mapping.findForward(SUCCESS);
                    } else {
                        f.setMa_kb(null);
                        f.setTen_kb(null);
                        f.setTen_nhom(null);
                        f.setMa_nsd(null);
                        f.setTrang_thai(null);
                        f.setId_kb(null);
                        f.setTen_nsd(null);
                        f.setKb_id(null);
                        f.setMa_tabmis(null);
                        f.setId(null);
                        f.setChuc_danh(null);
                        request.setAttribute("status",
                                             "quanlynsd.listnsd.failure.sua");
                        request.setAttribute("nsdID", nsdID);
                    }
                } else {
                    f.setMa_kb(null);
                    f.setMa_kb(null);
                    f.setTen_kb(null);
                    f.setTen_nhom(null);
                    f.setMa_nsd(null);
                    f.setTrang_thai(null);
                    f.setId_kb(null);
                    f.setTen_nsd(null);
                    f.setKb_id(null);
                    f.setMa_tabmis(null);
                    f.setId(null);
                    f.setChuc_danh(null);
                    request.setAttribute("status",
                                         "quanlynsd.listnsd.failure.sua.tontai.giaodich");
                    request.setAttribute("nsdID", nsdID);
                    list(mapping, f, request, response);
                    return mapping.findForward(SUCCESS);
                }
            } else {
                int i = useDAO.update(vo);
                if (i > 0) {
                    request.setAttribute("status",
                                         "quanlynsd.listnsd.success.sua");
                    // Luu lich su thay doi
                    UserHistoryVO userHisVO = null;
                    UserHistoryDAO userHisDAO = new UserHistoryDAO(conn);
                    String strNoiDung = "Sua NSD " + id_nsd;
                    userHisVO = new UserHistoryVO();
                    userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                    userHisVO.setNoi_dung_thaydoi(strNoiDung);
                    userHisVO.setNsd_id(new Long(id_nsd));
                    userHisDAO.insert(userHisVO);
                    f.setMa_kb(null);
                    f.setTen_kb(null);
                    f.setTen_nhom(null);
                    f.setMa_nsd(null);
                    f.setTrang_thai(null);
                    f.setId_kb(null);
                    f.setTen_nsd(null);
                    f.setKb_id(null);
                    f.setMa_tabmis(null);
                    f.setId(null);
                    f.setChuc_danh(null);
                    request.setAttribute("nsdID", nsdID);
                    //                    saveVisitLog(conn, session, "QLY_NSD.QLY.SUA",
                    //                                  "");
                    f.reset(mapping, request);
                    conn.commit();
                    list(mapping, f, request, response);
                    return mapping.findForward(SUCCESS);
                } else {
                    f.setMa_kb(null);
                    f.setTen_kb(null);
                    f.setTen_nhom(null);
                    f.setMa_nsd(null);
                    f.setTrang_thai(null);
                    f.setId_kb(null);
                    f.setTen_nsd(null);
                    f.setKb_id(null);
                    f.setMa_tabmis(null);
                    f.setId(null);
                    f.setChuc_danh(null);

                    request.setAttribute("status",
                                         "quanlynsd.listnsd.failure.sua");
                    request.setAttribute("nsdID", nsdID);
                }
            }
            saveVisitLog(conn, session, "QLY_NSD.QLY.SUA", "");
            conn.commit();

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);

        }
        return mapping.findForward(SUCCESS);

    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_NSD.QLY.XOA")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        String nsdID = null;
        Vector vParam = null;
        String quyen = null;

        try {
            //            if (isTokenValid(request)) {
            HttpSession session = request.getSession();
            Long nUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            QuanLyNSDForm f = (QuanLyNSDForm)form;
            conn = getConnection();
            quyen =
                    (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString());
            String[] temp = quyen.split("\\|");
            f = new QuanLyNSDForm();
            f.setId(request.getParameter("longid").trim());

            //longid=<bean:write name="NSDlist" property="id"/>
            //                         &masnd=<bean:write name="NSDlist" property="ma_nsd"/>'
            //check quyen xoa


            UserDAO use_dao = new UserDAO(conn);
            UserVO use_vo = new UserVO();
            Long id_nsd = new Long(f.getId());
            f.setNguoi_tao(nUserID.toString());
            String strwhere_use = " a.id=? and a.nguoi_tao=?";
            vParam = new Vector();
            vParam.add(new Parameter(f.getId(), true));
            vParam.add(new Parameter(f.getNguoi_tao(), true));
            use_vo = use_dao.getUser(strwhere_use, vParam);
            //
            String strNhom_nsd = "";
            Collection coll_nhom_nsd = new ArrayList();
            NhomNSDDAO dao_nhom_nsd = new NhomNSDDAO(conn);
            NhomNSDVO vo_nhom = new NhomNSDVO();
            String strNhom_id = null;
            Vector param_nhom_nsd = new Vector();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].toString().equalsIgnoreCase("QTHT-TW")) {
                    if (!"".equals(strNhom_nsd)) {
                        strNhom_nsd +=
                                " and a.loai_nhom in ('QTHT-TW','QTHT-DV','CBPT-TTTT','CB-TTTT','KTT','GD')";
                    } else {
                        strNhom_nsd +=
                                " a.loai_nhom in ('QTHT-TW','QTHT-DV','CBPT-TTTT','CB-TTTT','KTT','GD')";
                    }
                    coll_nhom_nsd =
                            dao_nhom_nsd.getNhomNSDList(strNhom_nsd, param_nhom_nsd);
                    Iterator iter = coll_nhom_nsd.iterator();
                    int nCounter = 0;

                    while (iter.hasNext()) {
                        vo_nhom = (NhomNSDVO)iter.next();

                        if (nCounter == 0) {
                            if (null != strNhom_id || "".equals(strNhom_id)) {
                                strNhom_id +=
                                        " or a.nhom_id =" + vo_nhom.getId();
                            } else {
                                strNhom_id = " a.nhom_id =" + vo_nhom.getId();
                            }
                        }
                        if (nCounter > 0) {
                            strNhom_id += " or a.nhom_id =" + vo_nhom.getId();
                        }
                        nCounter++;

                    }

                }
                if (temp[i].toString().equalsIgnoreCase("QTHT-DV")) {
                    if (!"".equals(strNhom_nsd)) {
                        strNhom_nsd += " and a.loai_nhom in ('TTV')";
                    } else {
                        strNhom_nsd += " a.loai_nhom in ('TTV')";
                    }
                    coll_nhom_nsd =
                            dao_nhom_nsd.getNhomNSDList(strNhom_nsd, param_nhom_nsd);
                    Iterator iter = coll_nhom_nsd.iterator();
                    int nCounter = 0;
                    while (iter.hasNext()) {
                        vo_nhom = (NhomNSDVO)iter.next();
                        if (nCounter == 0) {
                            if (null != strNhom_id || "".equals(strNhom_id)) {
                                strNhom_id +=
                                        " or a.nhom_id =" + vo_nhom.getId();
                            } else {
                                strNhom_id = " a.nhom_id =" + vo_nhom.getId();
                            }
                        }
                        if (nCounter > 0) {
                            strNhom_id += " or a.nhom_id =" + vo_nhom.getId();
                        }
                        nCounter++;

                    }

                }
                if (temp[i].toString().equalsIgnoreCase("CB-TTTT") ||
                    temp[i].toString().equalsIgnoreCase("CBPT-TTTT")) {

                    if (!"".equals(strNhom_nsd)) {
                        strNhom_nsd += " and a.loai_nhom in ('GD','KTT')";
                    } else {
                        strNhom_nsd += " a.loai_nhom in ('GD','KTT')";
                    }
                    coll_nhom_nsd =
                            dao_nhom_nsd.getNhomNSDList(strNhom_nsd, param_nhom_nsd);
                    Iterator iter = coll_nhom_nsd.iterator();
                    int nCounter = 0;


                    while (iter.hasNext()) {
                        vo_nhom = (NhomNSDVO)iter.next();
                        if (nCounter == 0) {
                            if (null != strNhom_id || "".equals(strNhom_id)) {
                                strNhom_id +=
                                        " or a.nhom_id =" + vo_nhom.getId();
                            } else {
                                strNhom_id = " a.nhom_id =" + vo_nhom.getId();
                            }
                        }
                        if (nCounter > 0) {
                            strNhom_id += " or a.nhom_id =" + vo_nhom.getId();
                        }
                        nCounter++;

                    }

                }
            }

            NhomNSDDAO nhom_dao = new NhomNSDDAO(conn);
            NhomNSDVO nhom_vo = new NhomNSDVO();
            strNhom_id = "a.nsd_id = ? and ( " + strNhom_id + ")";
            vParam = new Vector();
            vParam.add(new Parameter(f.getId(), true));
            nhom_vo = nhom_dao.getNSDNhom(strNhom_id, vParam);


            ///////////////////////////////

            if (null != use_vo || null != nhom_vo) {
                nsdID = request.getParameter("masnd").trim();
                UserVO vo = new UserVO();
                UserDAO dao = new UserDAO(conn);
                PhanNhomDAO qldao = new PhanNhomDAO(conn);
                String strWhere = "a.id = ? ";
                vParam = new Vector();
                vParam.add(new Parameter(f.getId(), true));
                vo = dao.getUser(strWhere, vParam);
                if (vo.getTgian_truycap() == null) {
                    if (vo == null) {
                        request.setAttribute("status",
                                             "quanlynsd.listnsd.warning.manv.daxoa");
                        request.setAttribute("nsdID", nsdID);
                    } else {
                        NhomNSDDAO nsddao = new NhomNSDDAO(conn);
                        Collection coll =
                            nsddao.getNhomNSDListByUserID(new Long(f.getId()));
                        if (!coll.isEmpty()) {
                            int i = qldao.delete(new Long(f.getId()));
                            if (i > 0) {
                                int j = dao.delete(new Long(f.getId()));
                                if (j > 0) {
                                    request.setAttribute("status",
                                                         "quanlynsd.listnsd.success.xoa");
                                    request.setAttribute("nsdID", nsdID);
                                    // Luu lich su thay doi
                                    UserHistoryVO userHisVO = null;
                                    UserHistoryDAO userHisDAO =
                                        new UserHistoryDAO(conn);
                                    String strNoiDung = "Xoa NSD " + id_nsd;
                                    userHisVO = new UserHistoryVO();
                                    userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                                    userHisVO.setNoi_dung_thaydoi(strNoiDung);
                                    userHisVO.setNsd_id(new Long(id_nsd));
                                    userHisDAO.insert(userHisVO);
                                } else {
                                    request.setAttribute("status",
                                                         "quanlynsd.listnsd.failure.xoa");
                                    request.setAttribute("nsdID", nsdID);
                                }
                            } else {
                                request.setAttribute("status",
                                                     "quanlynsd.listnsd.failure.xoa");
                                request.setAttribute("nsdID", nsdID);
                            }
                        } else {
                            int j = dao.delete(new Long(f.getId()));
                            if (j > 0) {
                                request.setAttribute("status",
                                                     "quanlynsd.listnsd.success.xoa");
                                request.setAttribute("nsdID", nsdID);
                                // Luu lich su thay doi
                                UserHistoryVO userHisVO = null;
                                UserHistoryDAO userHisDAO =
                                    new UserHistoryDAO(conn);
                                String strNoiDung = "Xoa NSD " + id_nsd;
                                userHisVO = new UserHistoryVO();
                                userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                                userHisVO.setNoi_dung_thaydoi(strNoiDung);
                                userHisVO.setNsd_id(new Long(id_nsd));
                                userHisDAO.insert(userHisVO);
                            } else {
                                request.setAttribute("status",
                                                     "quanlynsd.listnsd.failure.xoa");
                                request.setAttribute("nsdID", nsdID);
                            }
                        }
                    }
                } else {
                    request.setAttribute("status",
                                         "quanlynsd.listnsd.warning.nsd.xoa.failure.dadunghethong");
                    request.setAttribute("nsdID", nsdID);
                }

            } else {
                request.setAttribute("status",
                                     "quanlynsd.listnsd.warning.nsd.xoa.failure.nsd.khongthuocquyen");
                return mapping.findForward("back");
            }
            conn.commit();
            //            saveVisitLog(conn, session, "QLY_NSD.QLY.XOA",
            //                          "");

            //            }
            //
            //            resetToken(request);
            //            saveToken(request);
            //
            f.setMa_kb(null);
            f.setTen_kb(null);
            f.setTen_nhom(null);
            f.setMa_nsd(null);
            f.setTrang_thai(null);
            f.setId_kb(null);
            f.setTen_nsd(null);
            f.setKb_id(null);
            f.setMa_tabmis(null);
            f.setId(null);
            f.setChuc_danh(null);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);

        }
        return mapping.findForward(SUCCESS);
    }


}

