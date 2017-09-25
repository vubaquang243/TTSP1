package com.seatech.ttsp.reports;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.ReportUtility;

import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.Connection;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JApplet;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


public class JRViewerApplet extends JApplet {
    private JasperViewer jasperViewer;
    public JRViewerApplet() {
        
    }

    private void jbInit() throws Exception {
      Connection conn = null;
      String reportName = "LTT"; 
      //String reportName = "LTT_e";
      PrintWriter printWriter = null;
      InputStream reportStream = null;
      JasperPrint jasperPrint = null;
      Map parameterMap = null;
      net.sf.jasperreports.engine.util.JRProperties.setProperty("net.sf.jasperreports.extension.registry.factory.htmlelement", "net.sf.jasperreports.extensions.HtmlElementExtensionsRegistryFactory");
      net.sf.jasperreports.engine.util.JRProperties.setProperty("net.sf.jasperreports.extension.registry.factory.htmlcomponent", "net.sf.jasperreports.components.html.HtmlComponentExtensionsRegistryFactory");
      try {
        AppDAO dao = new AppDAO();
        conn = dao.getConnectionTest();              
      
        //reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + "/" + reportName + ".jasper");
        //reportStream = new FileInputStream("C:\\Documents and Settings\\khanghoa\\Application Data\\JDeveloper\\system11.1.1.3.37.56.60\\o.j2ee\\drs\\KBNN\\TTSPWebApp.war\\report\\LTT.jasper");
                
      //        String strTuNgay = request.getParameter("tungay");
      //        String strDenNgay = request.getParameter("denngay");
      //        String strTrangThai = request.getParameter("trangthai");
          
        //param order: Tu_ngay, Den_ngay, trang_thai 
        
        parameterMap = new HashMap(); 
      //        parameterMap.put("TU_NGAY", strTuNgay);
      //        parameterMap.put("DEN_NGAY", strDenNgay);
      //        parameterMap.put("SESS_ID", session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
      //        parameterMap.put("TRANG_THAI", strTrangThai);
        parameterMap.put("TU_NGAY", "01/01/2009");
        parameterMap.put("DEN_NGAY", "01/01/2019");
//        parameterMap.put("SESS_ID", session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
        parameterMap.put("TRANG_THAI", "01");
          
        //De cau lenh SQL trong file .jasper
        jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
        //Neu de de cau lenh SQL trong Java
        //              jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, rsDataSource);
        ReportUtility rpUtil = new ReportUtility();
        JasperViewer jpViewer = null;
        jpViewer = rpUtil.getJasperViewer(jasperPrint, "TTSP >> Bang tong hop Lenh Thanh Toan");
        jpViewer.setVisible(true);          
          
        this.getContentPane().setLayout( null );
        this.add(jpViewer);
      } catch(Exception e){
            e.printStackTrace();
          }
    }

    public void init() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static {
        try {
        } catch (Exception e) {
        }
    }

    public void setJasperViewer(JasperViewer jasperViewer) {
        this.jasperViewer = jasperViewer;
    }

    public JasperViewer getJasperViewer() {
        return jasperViewer;
    }
}
