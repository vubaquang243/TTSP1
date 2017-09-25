package com.seatech.ttsp.ltt.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;

import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ThLTTDiReportAction extends AppAction{
    public static final String REPORT_DIRECTORY = "/report";
  
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,  
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultset = null;
        String reportName = "LTT"; 
        //String reportName = "LTT_e";
        PrintWriter printWriter = null;
        InputStream reportStream = null;
        JasperPrint jasperPrint = null;
        Map parameterMap = null;
        Parameter param = null;
        Vector v_params = new Vector();
        
      net.sf.jasperreports.engine.util.JRProperties.setProperty("net.sf.jasperreports.extension.registry.factory.htmlelement", "net.sf.jasperreports.extensions.HtmlElementExtensionsRegistryFactory");
      net.sf.jasperreports.engine.util.JRProperties.setProperty("net.sf.jasperreports.extension.registry.factory.htmlcomponent", "net.sf.jasperreports.components.html.HtmlComponentExtensionsRegistryFactory");
                
        try{
              conn = getConnection(request);              
              HttpSession session = request.getSession();
              if(request.getParameter("reportName") != null)
                reportName = request.getParameter("reportName");
              reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + "/" + reportName + ".jasper");
              //reportStream = new FileInputStream("C:\\Documents and Settings\\khanghoa\\Application Data\\JDeveloper\\system11.1.1.3.37.56.60\\o.j2ee\\drs\\KBNN\\TTSPWebApp.war\\report\\LTT.jasper");
                      
              String strTuNgay = request.getParameter("tungay");
              String strDenNgay = request.getParameter("denngay");
              String strTrangThai = request.getParameter("trangthai");
              String strXuatRa = request.getParameter("xuatra");
              //param order: Tu_ngay, Den_ngay, trang_thai 
//              String strWhere = " WHERE ";              
//              if(strTuNgay != null && !"".equals(strTuNgay)){
//                  strWhere += "AND SP_LTT.\"NGAY_NHAN\" >= TO_DATE('"+strTuNgay+"', 'DD/MM/YYYY') ";  
//              }
//              if(strDenNgay != null && !"".equals(strDenNgay)){
//                  strWhere += "AND SP_LTT.\"NGAY_NHAN\" <= TO_DATE('"+strDenNgay+"', 'DD/MM/YYYY') ";              
//              }
//              if(strTrangThai != null && !"".equals(strTrangThai) && !"00".equals(strTrangThai)){
//                  strWhere += "AND SP_LTT.\"TRANG_THAI\" = '" + strTrangThai + "' ";
//              }
//              if(strWhere.contains(" WHERE AND"))
//                  strWhere = strWhere.replace(" WHERE AND", " WHERE ");            
//              if(strWhere.length() == 7)
//                  strWhere = "";
//              
//              LTTRPDAO lttrpDAO = new LTTRPDAO(conn);              
//              resultset = lttrpDAO.getBangTongHopLTTDi(strWhere, v_params) ;
//              //JRResultSetDataSource rsDataSource = new JRResultSetDataSource(resultset);            
//              JRDataSource rsDataSource = new JRResultSetDataSource(resultset);       
              
              parameterMap = new HashMap(); 
              parameterMap.put("TU_NGAY", strTuNgay);
              parameterMap.put("DEN_NGAY", strDenNgay);
              parameterMap.put("SESS_ID", session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
              parameterMap.put("TRANG_THAI", strTrangThai);
            
              jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
              
//              jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, rsDataSource);
              ReportUtility rpUtil = new ReportUtility();
              JasperViewer jpViewer = null;
              jpViewer = rpUtil.getJasperViewer(jasperPrint, "TTSP >> Bang tong hop Lenh Thanh Toan");
              jpViewer.setVisible(true);
        }catch(Exception ex){
          ex.printStackTrace();
        }finally{ 
          //resultset.close();
          close(conn);          
        }
        
        return mapping.getInputForward();
    }
}
