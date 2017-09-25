package com.seatech.ttsp.tcuuDHV.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanDAO;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanVO;
import com.seatech.ttsp.duyetLTT.duyetLTTDAO;
import com.seatech.ttsp.duyetLTT.duyetLTTVO;
import com.seatech.ttsp.tcuuDHV.form.TCuuDHVForm;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.InputStream;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TCuuDHVAction extends AppAction {

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();

            duyetLTTDAO lttDAO = new duyetLTTDAO(conn);
            DChieu1DAO dcDAO = new DChieu1DAO(conn);
            DChieu1VO vo = new DChieu1VO();
            TCuuDHVForm frm = (TCuuDHVForm)form;
            List dmucNH = null;
            List dmuckb_cha = null;
            Collection colLTT = null;
            Collection colMonTien = null;
            String strlstLTT = "";

            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String kb_id =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            dmucNH = (List)TTdao.getDMucNH(null,null);
            request.setAttribute("dmucNH", dmucNH);


            String strCap = " and ma=" + kb_code;
            vo = dcDAO.getCap(strCap, null);
            String cap = vo.getCap();
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                String strWhere = "";
                dmuckb_cha = (List)dcDAO.getDMucKB_cha(strWhere, null);
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
                request.setAttribute("dchieu3", "dchieu3");
                request.setAttribute("dftinh", "dftinh");
            } else if ("5".equals(cap)) {

                String strWhere = "";
                strlstLTT +=
                        " AND (d.id_cha = " + kb_id + " OR d.id =" + kb_id +
                        ")";

                strWhere += " and c.ma=" + kb_code;
                dmuckb_cha = (List)dcDAO.getDMucKB_cha(strWhere, null);

                request.setAttribute("dmuckb_tinh", dmuckb_cha);
            } else {
                String strWhere = "";
                strWhere +=
                        " and c.id in (select id_cha from sp_dm_htkb where ma=" +
                        kb_code + ")";
                strlstLTT += " AND d.id=" + kb_id;
                dmuckb_cha = (List)dcDAO.getDMucKB_cha(strWhere, null);
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
            }
            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            String page = frm.getPageNumber();
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];
            //          String strUserInfo =
            //              (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);

            //          request.setAttribute("chuc_danh", strUserInfo);
            strlstLTT +=
                    "  AND t.trang_thai_tw in ('01','02','03','04','05','06') and t.ngay_tt = to_char(sysdate,'YYYYMMDD')";


            colLTT =
                    lttDAO.getlstLTTTW_PTrang(strlstLTT, null, currentPage, numberRowOnPage,
                                              totalCount);
            colMonTien = lttDAO.getTienMon(strlstLTT, null);

            request.setAttribute("colMonTien", colMonTien);
            request.setAttribute("colLTT", colLTT);
            //          request.setAttribute("idxKB_huyen", idxKB_huyen);
            PagingBean pagingBean = new PagingBean();

            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(15);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("initAction", "initAction");
            saveToken(request);

        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();

            duyetLTTDAO lttDAO = new duyetLTTDAO(conn);
            DChieu1DAO dcDAO = new DChieu1DAO(conn);
            duyetLTTVO duyetVO = new duyetLTTVO();
            TCuuDHVForm frm = (TCuuDHVForm)form;
            DChieu1VO vo = new DChieu1VO();
            List dmucNH = null;
            List dmuckb_cha = null;
            List colTTV = null;
            List colTTVTABMIS = null;
            Collection colLTT = null;
            Collection colMonTien = null;

            //          String strUserInfo =
            //              (String)session.getAttribute(AppConstants.APP_USER_CODE_SESSION);
            String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            dmucNH = (List)TTdao.getDMucNH(null,null);
            request.setAttribute("dmucNH", dmucNH);


            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            String page = frm.getPageNumber();
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];

            String strCap = " and ma=" + kb_code;
            vo = dcDAO.getCap(strCap, null);
            String cap = vo.getCap();
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                String kb_huyen = frm.getNhkb_huyen();
                String strWhere = " ";
                dmuckb_cha = (List)dcDAO.getDMucKB_cha(strWhere, null);

                request.setAttribute("kb_huyen", kb_huyen);
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
                request.setAttribute("dchieu3", "dchieu3");
                request.setAttribute("dftinh", "dftinh");
            } else if ("5".equals(cap)) {

                String strWhere = "";
                String kb_huyen = frm.getNhkb_huyen();
                strWhere += " and c.ma=" + kb_code;
                dmuckb_cha = (List)dcDAO.getDMucKB_cha(strWhere, null);

                request.setAttribute("kb_huyen", kb_huyen);
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
                request.setAttribute("dftinh", "dftinh");
            } else {
                String strWhere = "";
                strWhere +=
                        " and c.id in (select id_cha from sp_dm_htkb where ma=" +
                        kb_code + ")";
                dmuckb_cha = (List)dcDAO.getDMucKB_cha(strWhere, null);
                request.setAttribute("huyen", "huyen");
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
            }

            String strUserInfo = (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);

            request.setAttribute("chuc_danh", strUserInfo);


            String strlstLTT = " ";
            //              strlstLTT += " AND t.trang_thai_tw='01'";
            String so_ltt = frm.getSo_ltt() == null ? "" : frm.getSo_ltt();

            String tien_max = frm.getTien_max();
            String tien_min = frm.getTien_min();
            String trang_thai_tw = frm.getTrang_thai_tw() == null ? "" : frm.getTrang_thai_tw();
            String ma_dv = frm.getMa_dv() == null ? "" : frm.getMa_dv();
            String tu_ngay = frm.getTu_ngay_tt() == null ? "" : frm.getTu_ngay_tt();
            String den_ngay = frm.getDen_ngay_tt() == null ? "" : frm.getDen_ngay_tt();
            String kb_tinh = frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
            String kb_huyen = frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
            String idxKB_huyen = request.getParameter("idxKB");

            String char_dngay = null;
            String char_tngay = null;
            if (den_ngay != null && !"".equals(den_ngay)) {
                char_dngay = StringUtil.DateToString(StringUtil.StringToDate(den_ngay, "dd/MM/yyyy"), "yyyy/MM/dd").replace("/", "");
            }
            if (tu_ngay != null && !"".equals(tu_ngay)) {
                char_tngay = StringUtil.DateToString(StringUtil.StringToDate(tu_ngay, "dd/MM/yyyy"), "yyyy/MM/dd").replace("/", "");
            }
            if (tu_ngay != null && !"".equals(tu_ngay) && den_ngay != null && !"".equals(den_ngay)) {
                strlstLTT += " and (t.ngay_tt >=  '" + char_tngay + "' and t.ngay_tt <=  '" + char_dngay + "') ";
            }
            if ((den_ngay == null || "".equals(den_ngay)) && tu_ngay != null && !"".equals(tu_ngay)) {
                strlstLTT += " and (t.ngay_tt <=  to_char(sysdate,'YYYYMMDD') and t.ngay_tt >=  '" + char_tngay + "') ";
            } else if (den_ngay != null && !"".equals(den_ngay) && (tu_ngay == null || "".equals(tu_ngay))) {
                strlstLTT += " and t.ngay_tt <= '" + char_dngay + "'";
            }
            if (kb_tinh != null && !"".equals(kb_tinh)) {
                strlstLTT += " AND (d.id_cha = " + kb_tinh + " OR d.id =" + kb_tinh + ")";
            }
            if (kb_huyen != null && !"".equals(kb_huyen)) {
                strlstLTT += " AND d.id=" + kb_huyen;
            }
            if (ma_dv != null && !"".equals(ma_dv)) {
                strlstLTT += " AND substr(f.ma_nh,3,3)='" + ma_dv + "'";
            }
            if (so_ltt != null && !"".equals(so_ltt)) {
                strlstLTT += " AND t.id like '%" + so_ltt.trim() + "%'";
            }
            if (tien_min != null && !"".equals(tien_min) && !"0".equals(tien_min)) {
                strlstLTT += " AND t.tong_sotien >='" + tien_min.replace(".", "") + "'";
            }
            if (tien_max != null && !"".equals(tien_max) && !"0".equals(tien_max)) { 
                strlstLTT += " AND t.tong_sotien <='" + tien_max.replace(".", "") + "'";
            }
            if (trang_thai_tw != null && !"".equals(trang_thai_tw)) {
                strlstLTT += " AND t.trang_thai_tw = '" + trang_thai_tw + "'";
            } else if (trang_thai_tw == null || "".equals(trang_thai_tw)) {
                strlstLTT += " AND t.trang_thai_tw in ('01','02','03','04','05','06')";
            }

            colLTT = lttDAO.getlstLTTTW_PTrang(strlstLTT, null, currentPage, numberRowOnPage, totalCount);
            colMonTien = lttDAO.getTienMon(strlstLTT, null);

            if (colLTT.isEmpty()) {
                request.setAttribute("colMonTien", null);
                request.setAttribute("colLTT", null);
            } else {
                request.setAttribute("colMonTien", colMonTien);
                request.setAttribute("colLTT", colLTT);
            }

            request.setAttribute("idxKB_huyen", idxKB_huyen);
            PagingBean pagingBean = new PagingBean();

            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(15);
            request.setAttribute("PAGE_KEY", pagingBean);
            saveToken(request);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        Connection conn = null;
        String reportName = null;
        InputStream reportStream = null;
        TCuuDHVForm frm = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = null;
        DmTSoHToanDAO tsoDAO = null;
        DmTSoHToanVO tsoVO = null;
        StringBuffer sbSubHTML = new StringBuffer();
        String strQuerry = "";
        try {
            conn = getConnection();
            frm = (TCuuDHVForm)form;
            tsoDAO = new DmTSoHToanDAO(conn);
            tsoVO = new DmTSoHToanVO();
            sbSubHTML.append("<input type=\"hidden\" name=\"so_ltt\" value=\"" +
                             frm.getSo_ltt() + "\" id=\"id\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"tien_max\" value=\"" +
                             frm.getTien_max() + "\" id=\"id\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"tien_min\" value=\"" +
                             frm.getTien_min() + "\" id=\"id\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"trang_thai_tw\" value=\"" +
                             frm.getTrang_thai_tw() + "\" id=\"id\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ma_dv\" value=\"" +
                             frm.getMa_dv() + "\" id=\"id\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay_tt\" value=\"" +
                             frm.getTu_ngay_tt() + "\" id=\"id\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay_tt\" value=\"" +
                             frm.getDen_ngay_tt() + "\" id=\"id\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_tinh\" value=\"" +
                             frm.getNhkb_tinh() + "\" id=\"id\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_huyen\" value=\"" +
                             frm.getNhkb_huyen() + "\" id=\"id\"></input>");
            String strlstLTT = " ";
            String so_ltt = frm.getSo_ltt() == null ? "" : frm.getSo_ltt();
            String tien_max = frm.getTien_max();
            String tien_min = frm.getTien_min();
            String trang_thai_tw =
                frm.getTrang_thai_tw() == null ? "" : frm.getTrang_thai_tw();
            String ma_dv = frm.getMa_dv() == null ? "" : frm.getMa_dv();
            String tu_ngay =
                frm.getTu_ngay_tt() == null ? "" : frm.getTu_ngay_tt();
            String den_ngay =
                frm.getDen_ngay_tt() == null ? "" : frm.getDen_ngay_tt();
            String kb_tinh =
                frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
            String kb_huyen =
                frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();

            String char_dngay = null;
            String char_tngay = null;
            if (den_ngay != null && !"".equals(den_ngay)) {
                char_dngay =
                        StringUtil.DateToString(StringUtil.StringToDate(den_ngay,
                                                                        "dd/MM/yyyy"),
                                                "yyyy/MM/dd").replace("/", "");
            }
            if (tu_ngay != null && !"".equals(tu_ngay)) {
                char_tngay =
                        StringUtil.DateToString(StringUtil.StringToDate(tu_ngay,
                                                                        "dd/MM/yyyy"),
                                                "yyyy/MM/dd").replace("/", "");
            }


            if (tu_ngay != null && !"".equals(tu_ngay) && den_ngay != null &&
                !"".equals(den_ngay)) {
                strlstLTT +=
                        " and (t.ngay_tt >=  '" + char_tngay + "' and t.ngay_tt <=  '" +
                        char_dngay + "') ";
            }
            if ((den_ngay == null || "".equals(den_ngay)) && tu_ngay != null &&
                !"".equals(tu_ngay)) {
                strlstLTT +=
                        " and (t.ngay_tt <=  to_char(sysdate,'YYYYMMDD') and t.ngay_tt >=  '" +
                        char_tngay + "') ";
            }
            //        else if ((den_ngay == null || "".equals(den_ngay)) && (tu_ngay == null ||
            //            "".equals(tu_ngay))) {
            //            strlstLTT +=
            //                    " and t.ngay_tt =  to_char(sysdate,'YYYYMMDD') ";
            //        }
            else if (den_ngay != null && !"".equals(den_ngay) &&
                     (tu_ngay == null || "".equals(tu_ngay))) {
                strlstLTT += " and t.ngay_tt <= '" + char_dngay + "'";
            }

            if (kb_tinh != null && !"".equals(kb_tinh)) {
                strQuerry +=
                        " AND (d.id_cha = " + kb_tinh + " OR d.id =" + kb_tinh +
                        ")";
            }

            if (kb_huyen != null && !"".equals(kb_huyen)) {
                strQuerry += " AND d.id=" + kb_huyen;
            }
            if (ma_dv != null && !"".equals(ma_dv)) {
                strQuerry += " AND substr(f.ma_nh,3,3)='" + ma_dv + "'";
            }
            if (so_ltt != null && !"".equals(so_ltt)) {
                strQuerry += " AND t.id like '%" + so_ltt.trim() + "%'";
            }
            if (tien_min != null && !"".equals(tien_min) &&
                !"0".equals(tien_min)) {
                strQuerry +=
                        " AND t.tong_sotien >='" + tien_min.replace(".", "") +
                        "'";

            }
            if (tien_max != null && !"".equals(tien_max) &&
                !"0".equals(tien_max)) {
                strQuerry +=
                        " AND t.tong_sotien <='" + tien_max.replace(".", "") +
                        "'";
            }
            if (trang_thai_tw != null && !"".equals(trang_thai_tw)) {
                strlstLTT += " AND t.trang_thai_tw = '" + trang_thai_tw + "'";
            } else if (trang_thai_tw == null || "".equals(trang_thai_tw)) {
                strlstLTT +=
                        " AND t.trang_thai_tw in ('01','02','03','04','05','06')";
            }
            reportName = "LTTTWPheDuyet";
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                            "/" +
                                                                                            reportName +
                                                                                            ".jasper");
            if (reportStream == null) {
                throw (new TTSPException().createException("TTSP-9999",
                                                           "Kh&#244;ng t&#236;m th&#7845;y file b&#225;o c&#225;o!"));
            }
            String strPathFont = AppConstants.FONT_FOR_REPORT;

            parameterMap = new HashMap();
            parameterMap.put("P_QUERRY", strQuerry);
            parameterMap.put("REPORT_LOCALE",
                             new java.util.Locale("vi", "VI"));
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 conn);
            //                jasperPrint.setOrientation(OrientationEnum.LANDSCAPE); // Pick

            String strTypePrintAction =
                request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                "" :
                request.getParameter(AppConstants.REQUEST_ACTION).toString();
            String strActionName = "printTCuuDHV.do";
            String strParameter = "";

            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    reportName, strPathFont, strActionName,
                                    sbSubHTML.toString(), strParameter);

        } catch (Exception e) {
            e.printStackTrace();
            throw (new TTSPException()).createException("TTSP-9999",
                                                        "In b&#225;o c&#225;o l&#7879;nh thanh to&#225;n : " +
                                                        e);
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

}
