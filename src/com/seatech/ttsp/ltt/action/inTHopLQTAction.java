package com.seatech.ttsp.ltt.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.ltt.form.inCTietLTTTQForm;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.InputStream;

import java.sql.Connection;

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


public class inTHopLQTAction extends AppAction {

  public ActionForward list(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
      Connection conn = getConnection(request);
      TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
      DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
      
      List dmucNH = (List)TTdao.getDMucNH(null,null);
      List dmTienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);
      
      request.setAttribute("dmucNH", dmucNH);
      request.setAttribute("dmTienTe", dmTienTe);
      
      return mapping.findForward("success");
  }
  
  public static final String REPORT_DIRECTORY = "/report";
  public static final String strFontTimeRoman = "/font/times.ttf";
  //    public static final String fileName = "/rpt_doi_chieu_1";

  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

      Connection conn = null;
      StringBuffer sbSubHTML = new StringBuffer();
      InputStream reportStream = null;
      try {
          conn = getConnection(request);
          inCTietLTTTQForm frm = (inCTietLTTTQForm)form;
          HttpSession session = request.getSession();

          String tu_ngay = frm.getTu_ngay();
          String den_ngay = frm.getDen_ngay();
          String ma_dv = frm.getMa_dv();


          sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                   tu_ngay + "\" id=\"tu_ngay\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay\" value=\"" +
                   den_ngay + "\" id=\"den_ngay\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"ma_dv\" value=\"" +
                   ma_dv + "\" id=\"ma_dv\"></input>");

          JasperPrint jasperPrint = null;
          HashMap parameterMap = new HashMap();
          ReportUtility rpUtilites = new ReportUtility();

          String fileName = "/rpt_THop_QToan_TQuoc";
          reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + fileName + ".jasper");
          if(frm.getLoai_tien().equals("VND")){
              parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VN"));
          }else{
              parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
          }
          parameterMap.put("p_TU_NGAY", tu_ngay);
          parameterMap.put("p_DEN_NGAY", den_ngay);
          parameterMap.put("p_MA_DV", ma_dv);
          parameterMap.put("p_LOAI_TIEN", frm.getLoai_tien());
          
          jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);

          String strTypePrintAction =
                  request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                  "" :
                  request.getParameter(AppConstants.REQUEST_ACTION).toString();
          String strActionName = "printTHopLQT.do";
          String strParameter = "";
          String strPathFont =
                  getServlet().getServletContext().getContextPath() +
                  REPORT_DIRECTORY + strFontTimeRoman;

          rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                      response, fileName, strPathFont,
                                      strActionName, sbSubHTML.toString(),
                                      strParameter);

      } catch (Exception e) {
          throw e;
      } finally {
          try {
              reportStream.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
          close(conn);
      }

      return mapping.findForward("success");
  }

}
