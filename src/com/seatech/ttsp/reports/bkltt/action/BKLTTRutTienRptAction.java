package com.seatech.ttsp.reports.bkltt.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.DNQTVO;
import com.seatech.ttsp.dchieu.form.XNDCTHop1Form;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.reports.bkltt.forms.BKLTTDiDenForm;

import java.io.InputStream;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.jms.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class BKLTTRutTienRptAction extends AppAction {
    public BKLTTRutTienRptAction() {
        super();
    }

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
            String ma_nhang = f.getNgan_hang();
            sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                             f.getTu_ngay() + "\" id=\"tu_ngay\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay\" value=\"" +
                             f.getDen_ngay() + "\" id=\"den_ngay\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngan_hang\" value=\"" +
                           f.getNgan_hang() + "\" id=\"ngan_hang\"></input>");
            HttpSession session = request.getSession();
            // report
            if (request.getParameter("reportName") != null)
                reportName = request.getParameter("reportName");

            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
            String strTuNgay = f.getTu_ngay();
            String strDenNgay = f.getDen_ngay();
            parameterMap = new HashMap();
            parameterMap.put("P_TUNGAY",
                             strTuNgay != null ? strTuNgay.trim() :
                             StringUtil.getCurrentDate());
            parameterMap.put("P_DENNGAY",
                             strDenNgay != null ? strDenNgay.trim() :
                             StringUtil.getCurrentDate());
            DMKBacVO dmkbVO = new DMKBacVO();
            DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
            String whereKBClause =
                " AND a.ma = (select ma_cha from sp_dm_htkb where ma=?)";
            Vector paramKB = new Vector();
            paramKB.add(new Parameter(session.getAttribute(AppConstants.APP_KB_CODE_SESSION),
                                      true));
            dmkbVO = dmkbDAO.getDMKB(whereKBClause, paramKB);
            parameterMap.put("P_NHDP", dmkbVO.getTen());
            parameterMap.put("P_MANH",
                             session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION));
            parameterMap.put("P_MA_NHANG", ma_nhang);
            parameterMap.put("P_TENNH",
                             session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION));
            parameterMap.put("P_SESSID",
                             session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
            parameterMap.put("P_LOAI_TIEN", f.getLoai_tien());
            if(f.getLoai_tien().equals("VND")){
                parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                reportName = "/BCBKRutTien";
            }else{
                parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
                reportName = "/BCBKRutTien_ngoaite";
            }
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                            reportName +
                                                                                            ".jasper");
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 conn);
            String strTypePrintAction = strAction == null ? "" : strAction;
            String strActionName = "bkeLTTRutTienRptAction.do";
            String strParameter = "";
            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    reportName, strPathFont, strActionName,
                                    sbSubHTML.toString(), strParameter);
        } catch (Exception e) {
            // TODO: Add catch code
//            e.printStackTrace();
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
//        if (isCancelled(request)) {
//            return mapping.findForward(AppConstants.FAILURE);
//        }
//        if (!checkPermissionOnFunction(request, "BCAO.BKLTT_RUTTIEN")) {
//            return mapping.findForward(AppConstants.FAILURE);
//        }
      Connection conn= null;
      String strWhere ="";      
      try {
          conn = getConnection(request);
          HttpSession session = request.getSession(); 
          String kb_code =
              session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();         
          String ma_kb =
              session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
          DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
          DMKBacDAO dao = new DMKBacDAO(conn);     
          List colNH = null;
          strWhere  = " AND d.ma_nh ='"+ma_kb+"'";
          colNH= (List)dao.getDMucKBHuyen(strWhere, null);
          if(colNH.size()<=0){
              request.setAttribute("colNH", null);
              return mapping.findForward("failure");
          }else{
              request.setAttribute("kb_id", ma_kb);
              request.setAttribute("colNH", colNH);
          }
          List dmTienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);
          request.setAttribute("dmTienTe", dmTienTe);
        } finally {
          conn.close();
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
  
//  public ActionForward view(ActionMapping mapping, ActionForm form,
//                            HttpServletRequest request,
//                            HttpServletResponse response) throws Exception {
//      Connection conn = null;
//      conn = getConnection(request);
//      HttpSession session = request.getSession();
//
//      try {
//          DChieu1DAO dao = new DChieu1DAO(conn);
//          BKLTTDiDenForm frm = (BKLTTDiDenForm)form;
//          Collection colTSKB = null;
//          String strJSON = null;
//          String id_kb =
//              session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
//          String ma_kb =
//            session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
//          String ma_nh= frm.getMa_nhang();
//          String strWhere = " AND send_bank ='"+ma_kb+"' AND receive_bank ='"+ma_nh+"'";
//          
//          colTSKB = dao.getCheckKQDC(strWhere, null);
//
//
//          java.lang.reflect.Type kqua_dchieu =
//              new TypeToken<Collection<DChieu1VO>>() {
//          }.getType();
//          strJSON = new Gson().toJson(colTSKB, kqua_dchieu);
//
//          response.setContentType(AppConstants.CONTENT_TYPE_JSON);
//          PrintWriter out = response.getWriter();
//          out.println(strJSON.toString());
//          out.flush();
//          out.close();
//      } catch (Exception e) {
//          JSONObject jsonRes = new JSONObject();
//          jsonRes.put("executeError",
//                      FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));
//
//          response.setHeader("X-JSON", jsonRes.toString());
//      } finally {
//          conn.close();
//      }
//      return mapping.findForward("success");
//  }  
//  
    
    
}
