package com.seatech.ttsp.dchieungoaite.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.BKeDChieuDAO;
import com.seatech.ttsp.dchieu.BKeDChieuVO;
import com.seatech.ttsp.dchieu.KQDChieu3DAO;
import com.seatech.ttsp.dchieu.KQDChieu3VO;
import com.seatech.ttsp.dchieu.form.BKeDChieuForm;

import com.seatech.ttsp.dchieungoaite.BKeDChieuNgoaiTeDAO;
import com.seatech.ttsp.dchieungoaite.DChieuNgoaiTeDAO;

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


public class BKeDChieuNgoaiTeAction extends AppAction {
    
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            
            BKeDChieuNgoaiTeDAO dcNgoaiTeDAO = new BKeDChieuNgoaiTeDAO(conn);
            BKeDChieuForm frm = (BKeDChieuForm)form;
            String strWhere = "";
            String strW = "";

            String type = frm.getType() == null ? "" : frm.getType();
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


            String mt_id = frm.getMt_id();
            ArrayList<BKeDChieuVO> colThop = new ArrayList();
            boolean isTraCuuBK = !(request.getParameter("tracuu") == null); //co phai tra cuu bk không
            if(isTraCuuBK){
                String bk_id = request.getParameter("bk_id").trim();
                strWhere +=" AND  a.id= '" + frm.getId() + "'";
                strW += " AND a.bk_id='" + bk_id + "'";
            }else{
                strWhere +=
                        " AND ( (b.trang_thai <> '04'" + " AND EXISTS (SELECT	 1 FROM	 sp_065_ngoai_te" +
                        " WHERE	 bk_id = '" + mt_id +
                        "' AND trang_thai IN ('01', '02', '00')))" +
                        " OR (b.trang_thai = '04' AND not EXISTS" +
                        " (SELECT	1 FROM	sp_065_ngoai_te WHERE	bk_id ='" + mt_id + "'" +
                        " AND trang_thai IN ('01', '02', '00'))and b.id = (select max(id) from sp_065_ngoai_te" +
                        " WHERE  bk_id ='" + mt_id +
                        "' and b.trang_thai='04')) or b.trang_thai is null )" +
                        " AND  a.mt_id= '" + mt_id + "'";
                strW += " AND a.bk_id='" + mt_id + "'";
            }
            colThop = (ArrayList<BKeDChieuVO>)dcNgoaiTeDAO.getBKeDChieu1CTiet(strWhere, null);

            if (colThop.isEmpty()) {
                return mapping.findForward("success");
            }
            if(isTraCuuBK){
                strW += " AND a.loai_tien = '" + colThop.get(0).getLoai_tien()+"'";
            }
            
            Collection colKQDCCT = new ArrayList();
            colKQDCCT = dcNgoaiTeDAO.getCTietDChieu1(strW, null);
            request.setAttribute("colKQDCCT", colKQDCCT);

            request.setAttribute("colThop", colThop);
            if (loai_dc != null && !"".equals(loai_dc)) {
                String tcuu =
                    "?nhkb_tinh=" + nhkb_tinh + "&nhkb_huyen=" + nhkb_huyen +
                    "&ngan_hang=" + ngan_hang + "&inKB=" + inKB + "&inNH=" +
                    inNH + "&inxtthai=" + inxtthai + "&tu_ngay=" + tu_ngay +
                    "&den_ngay=" + den_ngay + "&lan_dc=" + lan_dc +
                    "&currentPage=" + currentPage + "&loai_dc=" + loai_dc +
                    "&kq_id=" + kq_id + "&ma_nt="+frm.getLoai_tien() + 
                    "&trang_thai_kq=" + colThop.get(0).getTrang_thai_kq() + "&bkq_id="+colThop.get(0).getKq_id();
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
    
/*
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
                throw e;
            }
            close(conn);
        }

        return mapping.findForward("success");
    }
*/

}
