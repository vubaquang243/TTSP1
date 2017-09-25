package com.seatech.ttsp.iReport.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.iReport.form.ReportForm;
import com.seatech.ttsp.reports.bkltt.ReportDAO;
import com.seatech.ttsp.reports.bkltt.ReportVO;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ReportAction extends AppAction {
    public ReportAction() {
        super();
    }
    public static final String REPORT_DIRECTORY = "/report";
    public static final String fileName = "/ReportSample";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String SRTING_EMPTY = "";

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
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
        conn = getConnection(request);              
        HttpSession session = request.getSession();
        if(request.getParameter("reportName") != null)
          reportName = request.getParameter("reportName");
        reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + "/" + reportName + ".jasper");
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
        parameterMap.put("SESS_ID", session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
        parameterMap.put("TRANG_THAI", "01");
          
        //De cau lenh SQL trong file .jasper
        jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
        //Neu de de cau lenh SQL trong Java
        //              jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, rsDataSource);
        ReportUtility rpUtil = new ReportUtility();
        JasperViewer jpViewer = null;
        jpViewer = rpUtil.getJasperViewer(jasperPrint, "TTSP >> Bang tong hop Lenh Thanh Toan");
        jpViewer.setVisible(true);          
      } catch (Exception e) {
//          e.printStackTrace();
      } finally {
          conn.close();
      }
      return mapping.getInputForward();
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            ReportForm f = (ReportForm)form;
            ReportDAO dao = new ReportDAO();
            ReportUtility rpUtilites = new ReportUtility();
            // tao Map truyen vao report
            // cac parameter dieu kien tra cuu
            // Hien thi da ta phan loai theo user
            HttpSession session = request.getSession();
            String strUserName =
                session.getAttribute(AppConstants.APP_USER_NAME_SESSION).toString();
            ReportVO vo = new ReportVO();
            BeanUtils.copyProperties(vo, f);
            HashMap map = null;

            InputStream reportStream = null;
            JasperPrint jasperPrint = null;
            JRExporter exporter = null;
            String strPathFont = null;
            ResultSet resultSet = null;
            // Khoi tao JRExporter
            exporter = rpUtilites.getExporter(f.getLoai_hienthi());
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                            fileName +
                                                                                            ".jasper");
            strPathFont =
                    getServlet().getServletContext().getContextPath() + REPORT_DIRECTORY +
                    strFontTimeRoman;
            if (reportStream != null) {
                String strWhereClause = "";
                Vector param = new Vector();
                if (f.getNhkb_chuyen() != null &&
                    !SRTING_EMPTY.equals(f.getNhkb_chuyen().trim())) {
                    strWhereClause += " and nhkb_chuyen like (?) ";
                    param.add(new Parameter("%" + f.getNhkb_chuyen().trim() +
                                            "%", true));
                }
                if (f.getNhkb_nhan() != null &&
                    !SRTING_EMPTY.equals(f.getNhkb_nhan().trim())) {
                    strWhereClause += " and nhkb_nhan like (?) ";
                    param.add(new Parameter("%" + f.getNhkb_nhan().trim() +
                                            "%", true));
                }
                if (f.getTu_ngay() != null &&
                    !SRTING_EMPTY.equals(f.getTu_ngay().trim())) {
                    strWhereClause +=
                            " and Ngay_ktt_duyet >=to_date(?,'DD/MM/YYYY')";
                    param.add(new Parameter(f.getTu_ngay().trim(), true));
                }
                if (f.getDen_ngay() != null &&
                    !SRTING_EMPTY.equals(f.getDen_ngay().trim())) {
                    strWhereClause +=
                            " and Ngay_ktt_duyet <=to_date(?,'DD/MM/YYYY')";
                    param.add(new Parameter(f.getDen_ngay().trim(), true));
                }
                // get ResultSet
                resultSet = dao.getData(conn, param, strWhereClause);
                //set tham so cua report
                map = dao.returnMap(strUserName);
                // export ra file JasperPrint
                // get JRPrint
                jasperPrint =
                        rpUtilites.exportJasperPrint(reportStream, map, new JRResultSetDataSource(resultSet));
                resultSet.close();
            } else
                System.out.println("khong tim thay file jasper");
            if (jasperPrint != null) {
                if (f.getLoai_hienthi().equalsIgnoreCase("PDF")) {
                    OutputStream ouputStream = response.getOutputStream();
                    response.setContentType("application/pdf");
                    //export pdf
                    /**
                     * @ouputStream
                     * @xlsReport
                     * @exporter
                     * @jasperPrint:
                     * */
                    rpUtilites.generalPDFReport(ouputStream, exporter,
                                                strPathFont, jasperPrint);
                    ouputStream.flush();
                    ouputStream.close();
                } else if (f.getLoai_hienthi().equalsIgnoreCase("EXCEL")) {
                    response.setContentType("application/xls");
                    response.setHeader("Content-Disposition",
                                       "attachment; filename=" + fileName +
                                       ".xls");
                    OutputStream xlsReport = response.getOutputStream();
                    // truyen tham so
                    /**
                     * @xlsReport
                     * @exporter
                     * @jasperPrint:
                     * */
                    rpUtilites.generalExcelReport(xlsReport, exporter,
                                                  jasperPrint);
                    xlsReport.flush();
                    xlsReport.close();
                } else if (f.getLoai_hienthi().equalsIgnoreCase("HTML")) {
                    OutputStream outputStream = response.getOutputStream();
                    response.setContentType("text/html; charset=UTF-8");
                    /**
                   * @printWriter
                   * @exporter
                   * @jasperPrint:
                   * */
                    rpUtilites.generalHtmlReport(outputStream, exporter,
                                                 jasperPrint);
                    outputStream.flush();
                    outputStream.close();
                }
            } else {
                System.out.println("có l?i x?y ra, xem l?i file jrprint");
            }
            reportStream.close();
            if (!response.isCommitted())
                return mapping.findForward(AppConstants.SUCCESS);
            else
                return null;
        } catch (Exception e) {
//            e.printStackTrace();
            // display stack trace in the browser
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
            if (!response.isCommitted())
                return mapping.findForward("failure");
            else
                return null;
        } finally {
            conn.close();
        }

    }
  
}
