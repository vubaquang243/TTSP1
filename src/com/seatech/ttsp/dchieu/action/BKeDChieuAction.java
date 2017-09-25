package com.seatech.ttsp.dchieu.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.BKeDChieuDAO;
import com.seatech.ttsp.dchieu.BKeDChieuVO;
import com.seatech.ttsp.dchieu.KQDChieu3DAO;
import com.seatech.ttsp.dchieu.KQDChieu3VO;
import com.seatech.ttsp.dchieu.form.BKeDChieuForm;

import java.io.InputStream;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class BKeDChieuAction extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {
            conn = getConnection(request);
            BKeDChieuDAO dao = new BKeDChieuDAO(conn);
            //            BKeDChieuVO vo = new BKeDChieuVO();
            BKeDChieuForm frm = (BKeDChieuForm)form;
            //            Collection colTHBK = new ArrayList();
            //            Collection TongKQBK = new ArrayList();
            Collection colMT900 = new ArrayList();
            Collection colMT910 = new ArrayList();

            String type = frm.getType() == null ? "" : frm.getType();
            String loai_dc = frm.getLoai_dc() == null ? "" : frm.getLoai_dc();
            String nhkb_tinh =
                frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
            String nhkb_huyen =
                frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
            String ngan_hang =
                frm.getNgan_hang() == null ? "" : frm.getNgan_hang();
            String inxtthai =
                frm.getInxtthai() == null ? "" : frm.getInxtthai();
            String tu_ngay = frm.getTu_ngay() == null ? "" : frm.getTu_ngay();
            String den_ngay =
                frm.getDen_ngay() == null ? "" : frm.getDen_ngay();
            String lan_dc = frm.getLan_dc() == null ? "" : frm.getLan_dc();
            String currentPage =
                frm.getCurrentPage() == null ? "" : frm.getCurrentPage();
            String inKB = frm.getInKB() == null ? "" : frm.getInKB();
            String inNH = frm.getInNH() == null ? "" : frm.getInNH();
            String idxNH =
                request.getParameter("idxNH") == null ? "" : request.getParameter("idxNH");

            if (idxNH != null && !"".equals(idxNH)) {
                request.setAttribute("idxNH", idxNH);
            }
            request.setAttribute("type", type);

            String strWhere = " AND a.id = '" + frm.getBk_id() + "'";
            ArrayList<BKeDChieuVO> colTHBK =
                (ArrayList<BKeDChieuVO>)dao.getBKeDChieu3CTiet(strWhere, null);
            if (colTHBK.isEmpty()) {
                return mapping.findForward(AppConstants.SUCCESS);
            }

            request.setAttribute("colTHBK", colTHBK);

            //                      String kq_id= frm.getKq_id();
            strWhere =
                    " AND a.bk_id = '" + frm.getBk_id() + "' AND a.mt_type='900'";
            colMT900 = dao.getCTietDChieu3(strWhere, null);

            strWhere =
                    " AND a.bk_id = '" + frm.getBk_id() + "' AND a.mt_type='910'";
            colMT910 = dao.getCTietDChieu3(strWhere, null);


            request.setAttribute("colTHBK", colTHBK);
            request.setAttribute("TongKQBK", null);
            request.setAttribute("colMT900", colMT900);
            request.setAttribute("colMT910", colMT910);

            if (loai_dc != null && !"".equals(loai_dc)) {
                String tcuu =
                    "?nhkb_tinh=" + nhkb_tinh + "&nhkb_huyen=" + nhkb_huyen +
                    "&ngan_hang=" + ngan_hang + "&inKB=" + inKB + "&inNH=" +
                    inNH + "&inxtthai=" + inxtthai + "&tu_ngay=" + tu_ngay +
                    "&den_ngay=" + den_ngay + "&lan_dc=" + lan_dc +
                    "&currentPage=" + currentPage + "&loai_dc=" + loai_dc;
                request.setAttribute("tcuu", tcuu);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            conn.close();
        }
        return mapping.findForward("success");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            //            HttpSession session = request.getSession();
            BKeDChieuDAO dao = new BKeDChieuDAO(conn);
            //          BKeDChieuVO vo = new BKeDChieuVO();
            BKeDChieuForm frm = (BKeDChieuForm)form;
            String strWhere = "";
            String strW = "";

            String type = frm.getType() == null ? "" : frm.getType();
            String ma_nt = request.getParameter("ma_nt") == null ? "" : request.getParameter("ma_nt");
            String trang_thai_kq = request.getParameter("trang_thai_kq") == null ? "" : request.getParameter("trang_thai_kq");
            String kq_id = frm.getKq_id() == null ? "" : frm.getKq_id();
            String loai_dc = frm.getLoai_dc() == null ? "" : frm.getLoai_dc();
            String nhkb_tinh = frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
            String nhkb_huyen = frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
            String ngan_hang = frm.getNgan_hang() == null ? "" : frm.getNgan_hang();
            String inxtthai = frm.getInxtthai() == null ? "" : frm.getInxtthai();
            String tu_ngay = frm.getTu_ngay() == null ? "" : frm.getTu_ngay();
            String den_ngay = frm.getDen_ngay() == null ? "" : frm.getDen_ngay();
            String lan_dc = frm.getLan_dc() == null ? "" : frm.getLan_dc();
            String currentPage = frm.getCurrentPage() == null ? "" : frm.getCurrentPage();
            String inKB = frm.getInKB() == null ? "" : frm.getInKB();
            String inNH = frm.getInNH() == null ? "" : frm.getInNH();

            request.setAttribute("type", type);


            String bk_id = frm.getBk_id();
            Collection colThop = new ArrayList();
            strWhere +=
                    " AND ( (b.trang_thai <> '04'" + " AND EXISTS (SELECT	 1 FROM	 sp_065" +
                    " WHERE	 bk_id = '" + bk_id +
                    "' AND trang_thai IN ('01', '02', '00')))" +
                    " OR (b.trang_thai = '04' AND not EXISTS" +
                    " (SELECT	1 FROM	sp_065 WHERE	bk_id ='" + bk_id + "'" +
                    " AND trang_thai IN ('01', '02', '00'))and b.id = (select max(id) from sp_065" +
                    " WHERE  bk_id ='" + bk_id +
                    "' and b.trang_thai='04')) or b.trang_thai is null )" +
                    " AND  a.id= '" + bk_id + "'";
            colThop = dao.getBKeDChieu1CTiet(strWhere, null);

            if (colThop.isEmpty()) {
                return mapping.findForward("success");
            }

            strW += " AND a.bk_id='" + bk_id + "'";
            Collection colKQDCCT = new ArrayList();
            colKQDCCT = dao.getCTietDChieu1(strW, null);
            request.setAttribute("colKQDCCT", colKQDCCT);

            request.setAttribute("colThop", colThop);
            if (loai_dc != null && !"".equals(loai_dc)) {
                String tcuu =
                    "?nhkb_tinh=" + nhkb_tinh + "&nhkb_huyen=" + nhkb_huyen +
                    "&ngan_hang=" + ngan_hang + "&inKB=" + inKB + "&inNH=" +
                    inNH + "&inxtthai=" + inxtthai + "&tu_ngay=" + tu_ngay +
                    "&den_ngay=" + den_ngay + "&lan_dc=" + lan_dc +
                    "&currentPage=" + currentPage + "&loai_dc=" + loai_dc +
                    "&kq_id=" + kq_id + "&ma_nt="+ma_nt+"&trang_thai_kq=" +trang_thai_kq;
                request.setAttribute("tcuu", tcuu);
            }

        } catch (Exception e) {
            throw e;

        } finally {
            saveToken(request);
            close(conn);
        }
        return mapping.findForward("success");
    }

    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;

        try {
            conn = getConnection(request);
            BKeDChieuForm f = (BKeDChieuForm)form;
            String p_type = f.getType();
            KQDChieu3DAO kqDChieu3DAO = new KQDChieu3DAO(conn);
            KQDChieu3VO kqDChieu3VO = new KQDChieu3VO();
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            //          HttpSession session = request.getSession();
            String ngay_dc = f.getNgay_dc();
            String bk_id = f.getBk_id();
            String lan_dc = f.getLan_dc();
            ngay_dc = ngay_dc.replace("/", "-");
            String send_bank = f.getSend_bank();
            String strW = "'" + send_bank + "'";
            kqDChieu3VO = kqDChieu3DAO.getTenNH(strW, null);
            String tenNH = kqDChieu3VO.getTen();
            String p_ten_kb = kqDChieu3VO.getTen_kb();

            if ("dc3".equals(p_type)) {
                String p_nh = send_bank.substring(2, 5);
                String fileName = "/rpt_Bke_Dchieu_TGui";

                sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dc\" value=\"" +
                                 ngay_dc + "\" id=\"ngay_dc\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"bk_id\" value=\"" +
                                 bk_id + "\" id=\"bk_id\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"lan_dc\" value=\"" +
                                 lan_dc + "\" id=\"lan_dc\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"send_bank\" value=\"" +
                                 send_bank + "\" id=\"send_bank\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"tenNH\" value=\"" +
                                 tenNH + "\" id=\"tenNH\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"type\" value=\"" +
                                 p_type + "\" id=\"type\"></input>");
                JasperPrint jasperPrint = null;
                HashMap parameterMap = new HashMap();
                ReportUtility rpUtilites = new ReportUtility();
                parameterMap.put("REPORT_LOCALE",
                                 new java.util.Locale("vi", "VI"));
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
                parameterMap.put("p_NGAY", ngay_dc);
                parameterMap.put("p_ID_BK", bk_id);
                parameterMap.put("p_MA_NH", p_nh);
                parameterMap.put("p_TEN_NH", tenNH);
                parameterMap.put("p_LAN", lan_dc);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "printBKeDChieuAction.do";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    REPORT_DIRECTORY + strFontTimeRoman;

                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
            }
            if ("dc1".equals(p_type)) {
                String p_ma_kb = f.getReceive_bank();
                String fileName = "/rpt_Bke_Dchieu_1";
                sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dc\" value=\"" +
                                 ngay_dc + "\" id=\"ngay_dc\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"bk_id\" value=\"" +
                                 bk_id + "\" id=\"bk_id\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"lan_dc\" value=\"" +
                                 lan_dc + "\" id=\"lan_dc\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"send_bank\" value=\"" +
                                 send_bank + "\" id=\"send_bank\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"receive_bank\" value=\"" +
                                 p_ma_kb + "\" id=\"receive_bank\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"type\" value=\"" +
                                 p_type + "\" id=\"type\"></input>");
                JasperPrint jasperPrint = null;
                HashMap parameterMap = new HashMap();
                ReportUtility rpUtilites = new ReportUtility();
                parameterMap.put("REPORT_LOCALE",
                                 new java.util.Locale("vi", "VI"));
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
                parameterMap.put("p_NGAY", ngay_dc);
                //                parameterMap.put("p_ID_BK", bk_id);
                parameterMap.put("p_MA_NH", send_bank);
                parameterMap.put("p_MA_KB", p_ma_kb);
                parameterMap.put("p_TEN_NH", tenNH);
                parameterMap.put("p_TEN_KB", p_ten_kb);
                parameterMap.put("p_LAN", lan_dc);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "printBKeDChieuAction.do";
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

        return mapping.findForward("success");
    }


}
