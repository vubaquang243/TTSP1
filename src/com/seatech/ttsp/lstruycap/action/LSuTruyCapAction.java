package com.seatech.ttsp.lstruycap.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.chucnang.ChucNangDAO;
import com.seatech.ttsp.chucnang.ChucNangVO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.lstruycap.LSuTruyCapDAO;
import com.seatech.ttsp.lstruycap.form.LSuTruyCapForm;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
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


public class LSuTruyCapAction extends AppAction {
    private static String SUCCESS = "success";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "QLY_NSD.QLY.TRACUU_LSU_DANGNHAP")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        LSuTruyCapForm f = null;
        String strwherecnang = null;
        Vector params = null;
        String strwhereList = null;
        //
        String strWhere = "";
        Vector v_param = null;
        List lstLSu = new ArrayList();
        List dsCNang = null;
        /**
         * - Nguoi sua: ManhNV
         * - Ngay sua: 05/11/2016
         * - Noi dung: Sua loi java heap size (ko select khi khong nhap dieu kien tu ngay, den ngay)
         * - Key tim kiem: 20161105-JVH-LSTC
         * */
        //20161105-JVH-LSTC - khoi tạo PagingBean
        PagingBean pagingBean = new PagingBean();
        try {
            conn = getConnection();
            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            HttpSession session = request.getSession();
            f = (LSuTruyCapForm)form;
            String strID_kb =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();

            //            b.kb_id in (select id from sp_dm_htkb where ma = ? or ma_cha = ?)
            v_param = new Vector();
            if (!strID_kb.equals("0001")) {
                if (!strID_kb.equals("0002")) {
                    strWhere =
                            " and b.kb_id in (select id from sp_dm_htkb where ma = ? or ma_cha = ?)";
                    v_param.add(new Parameter(strID_kb, true));
                    v_param.add(new Parameter(strID_kb, true));
                }
            }

            if (!"".equals(f.getMa_kb())) {
                if (null != f.getMa_kb()) {
                    if (!strID_kb.equals("0001")) {
                        if (!strID_kb.equals("0002")) {
                            DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
                            DMKBacVO dmkbVO = new DMKBacVO();
                            String strCheckkb = " AND  a.ma= ? and a.ma_cha = ?";
                            Vector v_param1 = new Vector();
                            v_param1.add(new Parameter(f.getMa_kb(), true));
                            v_param1.add(new Parameter(strID_kb, true));
                            dmkbVO = dmkbDAO.getDMKB(strCheckkb, v_param1);
                            // dmkbVO == null >>> ma KB vua nhap k thuoc KB cha.
                            if (null == dmkbVO) {
                                request.setAttribute("status",
                                                     "tracuclsu.listlsu.warning.khac.makb");
                                f = new LSuTruyCapForm();
                                lstLSu = null;
                                list(mapping, f, request, response);
                                request.setAttribute("lstLSu", lstLSu);
                                return mapping.findForward(SUCCESS);
                            }
                        }
                    }

                }
            }

            LSuTruyCapDAO dao = new LSuTruyCapDAO(conn);

            if (f.getKy_hieu_cnang() != "") {
                if (f.getKy_hieu_cnang() != null) {
                    strwherecnang =
                            "a.ky_hieu_cnang like '" + f.getKy_hieu_cnang() +
                            "%'";
                }
            }
            ChucNangDAO daocnang = new ChucNangDAO(conn);
            ChucNangVO vocnang = new ChucNangVO();
            vocnang = daocnang.getChucNang(strwherecnang, params);
            strwhereList = " a.cnang_cha is null";
            dsCNang = (List)daocnang.getChucNangList(strwhereList, params);
            request.setAttribute("lstCnang", dsCNang);
            //Khai bao bien find.
            Long lma_kb = null;
            String strma_nsd = null;
            String strten_nsd = null;
            String strnhomchnang = null;

            if (f != null) {
                if (!"".equals(f.getKb_id())) {
                    if (f.getKb_id() != null) {
                        lma_kb = new Long(f.getKb_id());
                    }
                }
                if (!"".equals(f.getMa_nsd())) {
                    if (f.getMa_nsd() != null) {
                        strma_nsd = f.getMa_nsd();
                    }
                }
                if (!"".equals(f.getTen_nsd())) {
                    if (f.getTen_nsd() != null) {
                        strten_nsd = f.getTen_nsd();
                    }
                }
                if (!"".equals(f.getKy_hieu_cnang())) {
                    if (f.getKy_hieu_cnang() != null) {
                        strnhomchnang = f.getKy_hieu_cnang();
                    }
                }
            }

            if (lma_kb != null && !lma_kb.equals("")) {
                strWhere += " and b.KB_ID=? ";
                v_param.add(new Parameter(lma_kb, true));
            } else {
                if (strWhere == null) {
                    strWhere = "";
                }
            }
            if (strma_nsd != null && !strma_nsd.equals("")) {

                strWhere +=
                        " and lower(b.ma_nsd) like lower('%" + strma_nsd + "%') ";
            }
            if (strten_nsd != null && !strten_nsd.equals("")) {

                strWhere +=
                        " and lower(b.ten_nsd) like lower('%" + strten_nsd +
                        "%') ";
            }

            if (strnhomchnang != null && !strnhomchnang.equals("")) {

                strWhere +=
                        " and lower(c.KY_HIEU_CNANG) like lower('" + strnhomchnang +
                        "%') ";
            }

            if ((f.getTu_ngay() != null || f.getDen_ngay() != null) &&
                (!f.getTu_ngay().equals("") || !f.getDen_ngay().equals(""))) {
                //                  and to_char(a.tgian_tcap,'DD/MM/YYYY') >= '27/12/2011' AND  to_char(a.tgian_tcap,'DD/MM/YYYY') <= '28/12/2011'  ORDER BY a.id DESC
                //                  strWhere += " and to_char(a.tgian_tcap,'DD/MM/YYYY') >= ?' AND  to_char(a.tgian_tcap,'DD/MM/YYYY') <= '"+f.getDen_ngay()+"'";
                strWhere +=
                        " and a.tgian_tcap >=? and trunc(a.tgian_tcap)<= ?";
                //                  strWhere += " and a.ngay_tdoi >=?               and trunc(a.ngay_tdoi)<= ?";
                v_param.add(new DateParameter(StringUtil.StringToDate(f.getTu_ngay(),
                                                                      "dd/MM/yyyy"),
                                              true));
                v_param.add(new DateParameter(StringUtil.StringToDate(f.getDen_ngay(),
                                                                      "dd/MM/yyyy"),
                                              true));
              
              //20161105-JVH-LSTC:Chuyen code query DB vao trong dieu kien if ve tu ngay, den ngay-begin
              String page = f.getPageNumber();
              if (page == null)
                  page = "1";
              Integer currentPage = new Integer(page);
              Integer numberRowOnPage = phantrang;
              Integer totalCount[] = new Integer[1];
              lstLSu =
                      (List)dao.getLSuTruyCapList(strWhere, v_param, currentPage,
                                                  numberRowOnPage, totalCount);
              
              pagingBean.setCurrentPage(currentPage);
              pagingBean.setNumberOfRow(totalCount[0].intValue());
              pagingBean.setRowOnPage(numberRowOnPage);              
            }else{
              pagingBean.setCurrentPage(0);
              pagingBean.setNumberOfRow(0);
              pagingBean.setRowOnPage(phantrang);              
            }
            //khai bao bien phan trang
//            String page = f.getPageNumber();
//            if (page == null)
//                page = "1";
//            Integer currentPage = new Integer(page);
//            Integer numberRowOnPage = phantrang;
//            Integer totalCount[] = new Integer[1];
//            lstLSu =
//                    (List)dao.getLSuTruyCapList(strWhere, v_param, currentPage,
//                                                numberRowOnPage, totalCount);
//            PagingBean pagingBean = new PagingBean();
//            pagingBean.setCurrentPage(currentPage);
//            pagingBean.setNumberOfRow(totalCount[0].intValue());
//            pagingBean.setRowOnPage(numberRowOnPage);
            //20161105-JVH-LSTC-end  
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstLSu", lstLSu);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public static final String fileName = "/LSuTruyCap";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "QLY_NSD.QLY.TRACUU_LSU_DANGNHAP")) {
            return mapping.findForward("errorQuyen");
        }

        Connection conn = null;
        LSuTruyCapForm f = null;
        String strwherecnang = null;
        Vector params = null;
        String strwhereList = null;
        //
        String strWhere = "";
        Vector v_param = null;
        List lstLSu = null;
        List dsCNang = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;

        try {
            conn = getConnection();

            HttpSession session = request.getSession();
            f = (LSuTruyCapForm)form;
            String strID_kb =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            v_param = new Vector();
            if (!strID_kb.equals("0001")) {
                if (!strID_kb.equals("0002")) {
                    strWhere =
                            " and b.kb_id in (select id from sp_dm_htkb where ma = ? or ma_cha = ?)";
                    v_param.add(new Parameter(strID_kb, true));
                    v_param.add(new Parameter(strID_kb, true));
                }
            }

            if (!"".equals(f.getMa_kb())) {
                if (null != f.getMa_kb()) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"ma_kb\" value=\"" +
                                     f.getMa_kb() +
                                     "\" id=\"ma_kb\"></input>");
                    if (!strID_kb.equals("0001")) {
                        if (!strID_kb.equals("0002")) {
                            DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
                            DMKBacVO dmkbVO = new DMKBacVO();
                            String strCheckkb = " AND  a.ma= ? and a.ma_cha = ?";
                            Vector v_param1 = new Vector();
                            v_param1.add(new Parameter(f.getMa_kb(), true));
                            v_param1.add(new Parameter(strID_kb, true));
                            dmkbVO = dmkbDAO.getDMKB(strCheckkb, v_param1);
                            // dmkbVO == null >>> ma KB vua nhap k thuoc KB cha.
                            if (null == dmkbVO) {
                                request.setAttribute("status",
                                                     "tracuclsu.listlsu.warning.khac.makb");
                                f = new LSuTruyCapForm();
                                lstLSu = null;
                                list(mapping, f, request, response);
                                request.setAttribute("lstLSu", lstLSu);
                                return mapping.findForward(SUCCESS);
                            }
                        }
                    }

                }
            }

            LSuTruyCapDAO dao = new LSuTruyCapDAO(conn);

            if (f.getKy_hieu_cnang() != "") {
                if (f.getKy_hieu_cnang() != null) {
                    strwherecnang =
                            "a.ky_hieu_cnang like '" + f.getKy_hieu_cnang() +
                            "%'";
                }
            }
            ChucNangDAO daocnang = new ChucNangDAO(conn);
            ChucNangVO vocnang = new ChucNangVO();
            vocnang = daocnang.getChucNang(strwherecnang, params);
            strwhereList = " a.cnang_cha is null";
            dsCNang = (List)daocnang.getChucNangList(strwhereList, params);
            request.setAttribute("lstCnang", dsCNang);
            //Khai bao bien find.
            Long lma_kb = null;
            String strma_nsd = null;
            String strten_nsd = null;
            String strnhomchnang = null;

            if (f != null) {
                if (!"".equals(f.getKb_id())) {
                    if (f.getKb_id() != null) {
                        lma_kb = new Long(f.getKb_id());
                        sbSubHTML.append("<input type=\"hidden\" name=\"kb_id\" value=\"" +
                                         f.getKb_id() +
                                         "\" id=\"kb_id\"></input>");
                    }
                }
                if (!"".equals(f.getMa_nsd())) {
                    if (f.getMa_nsd() != null) {
                        strma_nsd = f.getMa_nsd();
                        sbSubHTML.append("<input type=\"hidden\" name=\"ma_nsd\" value=\"" +
                                         f.getMa_nsd() +
                                         "\" id=\"ma_nsd\"></input>");
                    }
                }
                if (!"".equals(f.getTen_nsd())) {
                    if (f.getTen_nsd() != null) {
                        strten_nsd = f.getTen_nsd();
                        sbSubHTML.append("<input type=\"hidden\" name=\"ten_nsd\" value=\"" +
                                         f.getTen_nsd() +
                                         "\" id=\"ten_nsd\"></input>");
                    }
                }
                if (!"".equals(f.getKy_hieu_cnang())) {
                    if (f.getKy_hieu_cnang() != null) {
                        strnhomchnang = f.getKy_hieu_cnang();
                        sbSubHTML.append("<input type=\"hidden\" name=\"ky_hieu_cnang\" value=\"" +
                                         f.getKy_hieu_cnang() +
                                         "\" id=\"ky_hieu_cnang\"></input>");
                    }
                }
            }

            if (lma_kb != null && !lma_kb.equals("")) {
                strWhere += " and b.KB_ID=? ";
                v_param.add(new Parameter(lma_kb, true));
            } else {
                if (strWhere == null) {
                    strWhere = "";
                }
            }
            if (strma_nsd != null && !strma_nsd.equals("")) {

                strWhere +=
                        " and lower(b.ma_nsd) like lower('%" + strma_nsd + "%') ";
            }
            if (strten_nsd != null && !strten_nsd.equals("")) {

                strWhere +=
                        " and lower(b.ten_nsd) like lower('%" + strten_nsd +
                        "%') ";
            }

            if (strnhomchnang != null && !strnhomchnang.equals("")) {

                strWhere +=
                        " and lower(c.KY_HIEU_CNANG) like lower('" + strnhomchnang +
                        "%') ";
            }

            if ((f.getTu_ngay() != null || f.getDen_ngay() != null) &&
                (!f.getTu_ngay().equals("") || !f.getDen_ngay().equals(""))) {
                //                  and to_char(a.tgian_tcap,'DD/MM/YYYY') >= '27/12/2011' AND  to_char(a.tgian_tcap,'DD/MM/YYYY') <= '28/12/2011'  ORDER BY a.id DESC
                //                  strWhere += " and to_char(a.tgian_tcap,'DD/MM/YYYY') >= ?' AND  to_char(a.tgian_tcap,'DD/MM/YYYY') <= '"+f.getDen_ngay()+"'";
                strWhere +=
                        " and a.tgian_tcap >=? and trunc(a.tgian_tcap)<= ?";
                //                  strWhere += " and a.ngay_tdoi >=?               and trunc(a.ngay_tdoi)<= ?";
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

            ResultSet rsLSu = dao.getLSuTruyCapResultSet(strWhere, v_param);

            if (rsLSu == null) {
                request.setAttribute("status",
                                     "quanlynsd.listnsd.warning.ketqua.null");
            } else {

                JasperPrint jasperPrint = null;
                HashMap parameterMap = null;

                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
                JRDataSource jrDS = new JRResultSetDataSource(rsLSu);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     jrDS);

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "LSuTruyCapPrintAction.do";
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
}
