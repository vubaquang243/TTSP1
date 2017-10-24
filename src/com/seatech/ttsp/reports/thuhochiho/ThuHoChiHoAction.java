package com.seatech.ttsp.reports.thuhochiho;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.InputStream;

import java.sql.Connection;

import java.util.Collection;

import java.util.HashMap;
import java.util.List;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/*
 * @modify: ThuongDT
 * @modify date: 29/11/2016
 * @See: them format tien theo VND(REPORT_LOCALE)
 * @track: 20161129
 * */
public class ThuHoChiHoAction extends AppAction {
    
    @Override
    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection();
            TTThanhToanDAO dao = new TTThanhToanDAO(conn);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            List dmuc_nh = (List)dao.getDMucNH(null,null);
            List dmTienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);
            request.setAttribute("dmucnh", dmuc_nh);
            request.setAttribute("dmTienTe", dmTienTe);
        }catch(Exception e){
            throw e;
        }finally{
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }
    
    @Override
    protected ActionForward printAction(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection();
            ThuHoChiHoForm f = (ThuHoChiHoForm)form;
            HashMap reportParams = new HashMap();
            StringBuffer sbSubHTML = new StringBuffer();
            String reportName = "/report/ReportThuHo_ChiHo.jasper";
            InputStream reportStream = null;
            JasperPrint jasperPrint = null;
            
            String strAction = request.getParameter(AppConstants.REQUEST_ACTION);
            String strPathFont = getServlet().getServletContext().getContextPath() + AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
          
            String ma_dv_nh = "__"+f.getMa_dv()+"%";
            String from_date = f.getFrom_date();
            String to_date = f.getTo_date();
            
            reportParams.put("ma_dv_nh", ma_dv_nh);
            reportParams.put("from_date", from_date);
            reportParams.put("to_date", to_date);
            reportParams.put("loai_tien",f.getLoai_tien());
            //29/11/2016 thuongdt bo sung format tien theo kieu VND 20161129
            reportParams.put("REPORT_LOCALE", new Locale("vi", "VI"));
            sbSubHTML.append("<input type=\"hidden\" name=\"ma_dv\" value=\"" +
                             f.getMa_dv() + "\" id=\"ma_dv\"></input>");
            
            reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(reportName);
            jasperPrint = JasperFillManager.fillReport(reportStream, reportParams, conn);
            
            String strTypePrintAction = strAction == null ? "" : strAction;
            String strActionName = "PrintReportThuHoChiHoAction.do";
            
            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    reportName, strPathFont, strActionName,
                                    sbSubHTML.toString(), "");
            
        }catch(Exception e){
            e.printStackTrace();
            }finally{
                close(conn);
            }
        return null;
    }
}
