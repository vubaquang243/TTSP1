package com.seatech.ttsp.reports.bkltt.action;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.dmtiente.DMTienTeVO;
import com.seatech.ttsp.reports.bkltt.forms.BKLTTDiDenForm;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanVO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class BKLTTRptAction extends AppAction {
    public BKLTTRptAction() {
        super();
    }
    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String STRING_EMPTY = "";

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "BCAO.BKLTT")) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            DChieu1DAO dao = new DChieu1DAO(conn);
            DChieu1VO vo = new DChieu1VO();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String kb_id =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

            String strCap = " and ma=" + kb_code;
            vo = dao.getCap(strCap, null);
            List dmuckb_cha = null;
            String cap = vo.getCap();
			/** 
			  * SEA: sua load form tra cuu 20170208
		 	  *	DChieu1DAO
			  * Thay doi ham su dung getDMucKB_cha  => getDMucKB_Tinh
			  */
			  /**************************START************************/
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                String strWhere = " ";
                dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
                request.setAttribute("dftinh", "dftinh");
            } else if ("0003".equals(kb_code)) {
                String strWhere = " AND a.ma='0003' ";
                dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
            } else if ("5".equals(cap)) {
                String strWhere = "";
                strWhere += " and a.ma= '" + kb_code + "' ";
                dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
            } else {
                String strWhere = "";
                strWhere +=
                        " and a.id_cha in (select id_cha from sp_dm_htkb where ma=" +
                        kb_code + ")";
                dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
			/**********************END************************/
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    // LAY THONG TIN KB HUYEN

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            String strMaKB;
            String strJSON;
            JsonObject jsonObj = new JsonObject();
            String strWhereClause = "";
            conn = getConnection();
            DChieu1DAO ttdao = new DChieu1DAO(conn);
            DMTienTeDAO dmtienDAO = new DMTienTeDAO(conn);
            Collection col = null;
            Collection dmtien = null;
            HttpSession session = request.getSession();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String kb_id =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            strMaKB = request.getParameter("kb_id").toString();
            DChieu1VO vo = new DChieu1VO();
            String strCap = " and ma=" + kb_code;
            vo = ttdao.getCap(strCap, null);
            String cap = vo.getCap();
            if ("0001".equals(kb_code) || "0002".equals(kb_code) ||
                "0003".equals(kb_code)) { // SGD TTTT
                if ("3".equals(strMaKB) || "1".equals(strMaKB)) {
                    strWhereClause += " and a.ma='0003'";
                } else {
                    strWhereClause +=
                            " and a.id_cha = " + strMaKB + " and a.ma<>'0003'";
                }
                //**************************************************************
                if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                    dmtien = dmtienDAO.getDMTienTeCuaDViList(" ", null);
                } else if ("0003".equals(kb_code)) {
                    dmtien =
                            dmtienDAO.getDMTienTeCuaDViList(" and b.kb_id = " +
                                                            kb_id + " ", null);
                }
                //**************************************************************
                col = ttdao.getDMucKB_huyen(strWhereClause, null);
            } else {
                if ("5".equals(cap)) { // cap tinh
                    strWhereClause +=
                            " and a.id_cha = " + strMaKB; // + " and ma=" + kb_code;
                    col = ttdao.getDMucKB_cha(strWhereClause, null);
                    //**************************************************************
                    dmtien =
                            dmtienDAO.getDMTienTeCuaDViList(" and b.kb_id in (select id from sp_dm_htkb where ma_cha = '" +
                                                            kb_code + "') ",
                                                            null);
                    //**************************************************************
                } else {
                    strWhereClause += " and a.ma=" + kb_code;
                    col = ttdao.getDMucKB_huyen(strWhereClause, null);
                    //**********************************************************
                    dmtien =
                            dmtienDAO.getDMTienTeCuaDViList(" and b.kb_id = " +
                                                            kb_id + " ", null);
                    //**********************************************************
                }
            }

            JSONObject jsonRes = new JSONObject();
            response.setHeader("X-JSON", jsonRes.toString());
            java.lang.reflect.Type listType =
                new TypeToken<Collection<DChieu1VO>>() {
            }.getType();
            strJSON = new Gson().toJson(col, listType);
            jsonObj.addProperty("json_dmkb", strJSON);

            java.lang.reflect.Type listType2 =
                new TypeToken<Collection<DMTienTeVO>>() {
            }.getType();
            strJSON = new Gson().toJson(dmtien, listType2);
            jsonObj.addProperty("json_dmtien", strJSON);


            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("json_dmkb");
            jsonArr.add(jsonEle);

            jsonEle = jsonObj.get("json_dmtien");
            jsonArr.add(jsonEle);

            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            JSONObject jsonRes = new JSONObject();
            jsonRes.put("executeError",
                        FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));

            response.setHeader("X-JSON", jsonRes.toString());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
    // LAY  THONG TIN NGAN HANG

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            String strMaKB;
            String strJSON;

            conn = getConnection();
            //            DMKBacDAO dmdao = new DMKBacDAO(conn);
            TTThanhToanDAO ttdao = new TTThanhToanDAO(conn);

            Collection colNH = null;
            HttpSession session = request.getSession();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            strMaKB = request.getParameter("nhkb_id").toString();
            TTThanhToanVO vo = new TTThanhToanVO();
            String strCap = " and ma=" + kb_code;
            vo = ttdao.getCap(strCap, null);
            String strNH = " and b.id=" + strMaKB;
            colNH = ttdao.getListNH(strNH, null);

            java.lang.reflect.Type listNH =
                new TypeToken<Collection<TTThanhToanVO>>() {
            }.getType();
            strJSON = new Gson().toJson(colNH, listNH);

            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJSON.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            JSONObject jsonRes = new JSONObject();
            jsonRes.put("executeError",
                        FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));

            response.setHeader("X-JSON", jsonRes.toString());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    // BK LENH THANH TOAN DI

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        Connection conn = null;
        String reportName = "";
        InputStream reportStream = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            String strAction =
                request.getParameter(AppConstants.REQUEST_ACTION);
            conn = getConnection();
            BKLTTDiDenForm f = (BKLTTDiDenForm)form;


            HttpSession session = request.getSession();
            String strUserID = "";
            if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                null) {
                strUserID =
                        session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            }
            String strCutOfTime =
                getThamSoHThong("CUT_OF_TIME", session); //16:30:00

            String strPTuNgay =
                f.getTu_ngay() != null && !STRING_EMPTY.equals(f.getTu_ngay()) ?
                f.getTu_ngay() : StringUtil.getCurrentDate();
            parameterMap = new HashMap();
            parameterMap.put("P_CurrDate", strPTuNgay);
            if (!strCutOfTime.startsWith(" "))
                strCutOfTime = " " + strCutOfTime;
            {
                strPTuNgay += strCutOfTime;
            }
            // report
            if (request.getParameter("reportName") != null)
                reportName = request.getParameter("reportName");

            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
            parameterMap.put("p_CUTOFTIME", strPTuNgay);
            //            parameterMap.put("P_TU_NGAY", strPTuNgay);
            //            parameterMap.put("P_DEN_NGAY", strPDenNgay);


            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            parameterMap.put("p_CUTOFTIME", strPTuNgay);
            //            parameterMap.put("P_TU_NGAY", strPTuNgay);
            //            parameterMap.put("P_DEN_NGAY", strPDenNgay);


            String nhkb_tinh =
                f.getNhkb_tinh() == null ? "" : f.getNhkb_tinh();
            String nhkb_huyen =
                f.getNhkb_huyen() == null ? "" : f.getNhkb_huyen();
            String ngan_hang =
                f.getNgan_hang() == null ? "" : f.getNgan_hang();
            String dk_loc = f.getDk_loc() == null ? "" : f.getDk_loc();
            String loai_tien = f.getLoai_tien();
            //          String dk_ma =
            //                        f.getDk_ma() == null ? "" : f.getDk_ma();
            //          String dk_yctt =
            //                        f.getDk_yctt() == null ? "" : f.getDk_yctt();

            sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                             f.getTu_ngay() + "\" id=\"tu_ngay\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_tinh\" value=\"" +
                             nhkb_tinh + "\" id=\"nhkb_tinh\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_huyen\" value=\"" +
                             nhkb_huyen + "\" id=\"nhkb_huyen\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngan_hang\" value=\"" +
                             ngan_hang + "\" id=\"ngan_hang\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"dk_loc\" value=\"" +
                             dk_loc + "\" id=\"dk_loc\"></input>");


            String strLTT = "";
            if (nhkb_tinh != null && !"".equals(nhkb_tinh)) {
                strLTT +=
                        " AND (c.id_cha = " + nhkb_tinh + " OR c.id =" + nhkb_tinh +
                        ")";
            }

            if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
                strLTT += " and  a.id = " + nhkb_huyen;
            }

            else if (nhkb_huyen == null || "".equals(nhkb_huyen)) {
                if (!"0001".equals(kb_code) && !"0002".equals(kb_code)) {
                    strLTT += " and  a.ma = " + kb_code;
                }
            }

            if (ngan_hang != null && !"".equals(ngan_hang)) {
                strLTT += " and d.ma_nh = '" + ngan_hang + "'";
            }
            String strOrder = "";

            if ("tien".equals(dk_loc)) {
                strOrder += " a.tong_sotien,";
            }
            if ("ma".equals(dk_loc)) {
                strOrder +=
                        " substr(a.so_yctt,10,instr(a.so_yctt,'_',10)-10), to_number(replace(a.so_yctt,'_','')),";
            }
            if (strOrder.length() > 0) {
                strOrder = strOrder.substring(0, strOrder.length() - 1);
            }
            DMNHangDAO dao = new DMNHangDAO(conn);
            DMNHangVO vo = new DMNHangVO();
            vo = dao.getTsoInLTT(strLTT, null);

            parameterMap.put("P_NHKB_TINH", vo.getTen_tinh());
            parameterMap.put("P_NHKB_CHUYEN", vo.getMa_kb());
            parameterMap.put("P_NHKB_NHAN", ngan_hang);
            parameterMap.put("P_TEN_KB_CHUYEN", vo.getTen_huyen());
            parameterMap.put("P_TEN_NHKB_CHUYEN", vo.getTen_ngan_hang());
            parameterMap.put("p_ORDER", strOrder);
            parameterMap.put("P_MA_LT", loai_tien);
            

            parameterMap.put("SESS_ID",
                             session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
            if ("VND".equalsIgnoreCase(loai_tien) ||
                "177".equalsIgnoreCase(loai_tien)) {
                parameterMap.put("P_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_VND);
                parameterMap.put("REPORT_LOCALE",
                                 new java.util.Locale("vi", "VI"));
            } else {
                parameterMap.put("P_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_NT);
                parameterMap.put("REPORT_LOCALE",
                                 new java.util.Locale("en", "US"));
            }

            reportName = "/BCBKLTTDI";
            response.getOutputStream();
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                            reportName +
                                                                                            ".jasper");
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 conn);
            //            JRHtmlExporter exporter = new JRHtmlExporter();
            //            ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
            //            exporter.setParameter(JRExporterParameter.JASPER_PRINT,
            //                                  jasperPrint);
            //            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
            //                                  xlsReport);
            //            exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
            //                                  Boolean.FALSE);
            //            exporter.exportReport();
            //            byte bytes[] = new byte[10];
            //            bytes = xlsReport.toByteArray();
            //            response.setContentType("text/html");
            //            response.setContentLength(bytes.length);
            //            xlsReport.close();
            //            OutputStream ouputStream = response.getOutputStream();
            //            ouputStream.write(bytes, 0, bytes.length);
            //            ouputStream.flush();
            //            ouputStream.close();
            //            jasperPrint =
            //                    JasperFillManager.fillReport(reportStream, parameterMap,
            //                                                 conn);
            String strTypePrintAction = strAction == null ? "" : strAction;
            String strActionName = "bkeLTTRptAction.do";
            String strParameter = "";
            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    reportName, strPathFont, strActionName,
                                    sbSubHTML.toString(), strParameter);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        Connection conn = null;
        String reportName = "";
        InputStream reportStream = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            String strAction =
                request.getParameter(AppConstants.REQUEST_ACTION);
            conn = getConnection();
            BKLTTDiDenForm f = (BKLTTDiDenForm)form;


            HttpSession session = request.getSession();
            String strUserID = "";
            if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                null) {
                strUserID =
                        session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            }

            String strCutOfTime =
                getThamSoHThong("CUT_OF_TIME", session); //16:30:00

            String strPTuNgay =
                f.getTu_ngay() != null && !STRING_EMPTY.equals(f.getTu_ngay()) ?
                f.getTu_ngay() : StringUtil.getCurrentDate();
            parameterMap = new HashMap();
            parameterMap.put("P_CurrDate", strPTuNgay);
            if (!strCutOfTime.startsWith(" "))
                strCutOfTime = " " + strCutOfTime;
            {
                strPTuNgay += strCutOfTime;
            }
            // report
            if (request.getParameter("reportName") != null)
                reportName = request.getParameter("reportName");

            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            parameterMap.put("p_CUTOFTIME", strPTuNgay);

            String nhkb_tinh =
                f.getNhkb_tinh() == null ? "" : f.getNhkb_tinh();
            String nhkb_huyen =
                f.getNhkb_huyen() == null ? "" : f.getNhkb_huyen();
            String ngan_hang =
                f.getNgan_hang() == null ? "" : f.getNgan_hang();
          String loai_lenh =
              f.getLoai_lenh() == null ? "" : f.getLoai_lenh();
            String dk_loc = f.getDk_loc() == null ? "" : f.getDk_loc();
            String loai_tien = f.getLoai_tien();


            sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                             f.getTu_ngay() + "\" id=\"tu_ngay\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_tinh\" value=\"" +
                             nhkb_tinh + "\" id=\"nhkb_tinh\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_huyen\" value=\"" +
                             nhkb_huyen + "\" id=\"nhkb_huyen\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngan_hang\" value=\"" +
                             ngan_hang + "\" id=\"ngan_hang\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"dk_loc\" value=\"" +
                             dk_loc + "\" id=\"dk_loc\"></input>");

            String strLTT = "";
            if (nhkb_tinh != null && !"".equals(nhkb_tinh)) {
                strLTT +=
                        " AND (c.id_cha = " + nhkb_tinh + " OR c.id =" + nhkb_tinh +
                        ")";
            }

            if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
                strLTT += " and  a.id = " + nhkb_huyen;
            }

            else if (nhkb_huyen == null || "".equals(nhkb_huyen)) {
                if (!"0001".equals(kb_code) && !"0002".equals(kb_code)) {
                    strLTT += " and  a.ma = " + kb_code;
                }
            }

            if (ngan_hang != null && !"".equals(ngan_hang)) {
                strLTT += " and d.ma_nh = '" + ngan_hang + "'";
            }
            String strOrder = "";

            if ("tien".equals(dk_loc)) {
                strOrder += " tong_sotien ";
            }
            if ("ma".equals(dk_loc)) {
                strOrder += " id";
            }

            if (strOrder.length() > 0) {
                strOrder = strOrder.substring(0, strOrder.length() - 1);
            }
            DMNHangDAO dao = new DMNHangDAO(conn);
            DMNHangVO vo = new DMNHangVO();
            vo = dao.getTsoInLTT(strLTT, null);

            parameterMap.put("P_NHKB_TINH", vo.getTen_tinh());
            parameterMap.put("P_NHKB_CHUYEN", vo.getMa_kb());
            parameterMap.put("P_NHKB_NHAN", ngan_hang);
            parameterMap.put("P_TEN_KB_CHUYEN", vo.getTen_huyen());
            parameterMap.put("P_TEN_NHKB_CHUYEN", vo.getTen_ngan_hang());
            parameterMap.put("p_ORDER", strOrder);
            parameterMap.put("SESS_ID",
                             session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
            parameterMap.put("P_MA_LT", loai_tien);
            
            if ("VND".equalsIgnoreCase(loai_tien) ||
                "177".equalsIgnoreCase(loai_tien)) {
                parameterMap.put("P_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_VND);
                parameterMap.put("REPORT_LOCALE",
                                 new java.util.Locale("vi", "VI"));
            } else {
                parameterMap.put("P_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_NT);
                parameterMap.put("REPORT_LOCALE",
                                 new java.util.Locale("en", "US"));
            }

            reportName = "/BCBKLTTDEN";
            if(loai_lenh.equals("LTT.DEN_BAOCO"))
            reportName = "/BCBKLTTDEN_BaoCo_KH";
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                            reportName +
                                                                                            ".jasper");
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 conn);
            String strTypePrintAction = strAction == null ? "" : strAction;
            String strActionName = "bkeLTTDenRptAction.do";
            String strParameter = "";
            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    reportName, strPathFont, strActionName,
                                    sbSubHTML.toString(), strParameter);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
