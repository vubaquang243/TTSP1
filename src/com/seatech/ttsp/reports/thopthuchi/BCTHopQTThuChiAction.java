package com.seatech.ttsp.reports.thopthuchi;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.TTSPLoadDMuc;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.THopQTThuChiVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;

import java.io.InputStream;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;

import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BCTHopQTThuChiAction extends AppAction{
  public static final String REPORT_DIRECTORY = "/report";
  public static final String strFontTimeRoman = "/font/times.ttf";
  @Override
  protected ActionForward executeAction(ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
      Connection conn = null;
      Collection dmNH = null;
      List cacKhoBacTinh = null;
      if (!checkPermissionOnFunction(request, "BCAO.THSTCTQ")) {
        return mapping.findForward("errorQuyen");
      }
     
      try{
        conn = getConnection();
        TTSPLoadDMuc  loadDM = new TTSPLoadDMuc(conn); 
        DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
        DMNHangDAO NHDAO = new DMNHangDAO(conn);
          
        dmNH = NHDAO.getclDMNHangHO(" and ma_dv <> '701' ",null);
        List dmTienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);   
        request.setAttribute("dmTienTe", dmTienTe);          
         // thuc hien load form 
        loadDM.getDMTinh( conn, request);
          
        PagingBean pagingBean = new PagingBean();
        pagingBean.setCurrentPage(1);
        pagingBean.setNumberOfRow(0);
        pagingBean.setRowOnPage(15);
        request.setAttribute("PAGE_KEY", pagingBean);
        request.setAttribute("dmNH", dmNH);
      }catch(Exception e){
        throw e;
      }finally{
          if(!conn.isClosed())
            close(conn);
      }
      return mapping.findForward("success");
  }
  
 
  public ActionForward view(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
      Connection conn = null;
      Collection dmNH = null;
      Collection lstthuchi = null;
      THopQTThuChiVO thchitong = null;
      try{
        conn = getConnection();
          
        BCTHopQTThuChiForm qtForm  =(BCTHopQTThuChiForm)form; 
        TTSPLoadDMuc  loadDM = new TTSPLoadDMuc(conn); 
        DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
        DChieu1DAO  dcDAO = new DChieu1DAO(conn);
        DMNHangDAO NHDAO = new DMNHangDAO(conn);
        dmNH =  NHDAO.getclDMNHangHO(" and ma_dv <> '701' ",null);
          
        List dmTienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null); 
        String inKB = request.getParameter("inKB");
        String inNH = request.getParameter("inNH");
        request.setAttribute("dmTienTe", dmTienTe); 
        request.setAttribute("kb_huyen", inKB);
        request.setAttribute("ngan_hang", inNH);
         // thuc hien load form 
        loadDM.getDMTinh( conn, request);
        String loai_tien =  qtForm.getLoai_tien();
        if(loai_tien == null || loai_tien.equals("")){         
          loai_tien = "VND";  
        }
        
        
        String   strWhere ="";
        int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
        String page = qtForm.getPageNumber();
        //Bo sung kiem tra page bi trang
        if (page == null || "".equals(page))
            page = "1";
        Integer currentPage = new Integer(page);
        Integer numberRowOnPage = phantrang;
        Integer totalCount[] = new Integer[15];;
          
         
         strWhere = whereClause(qtForm);
         lstthuchi = dcDAO.getDSachTHopThuChi(strWhere, null, loai_tien,currentPage, numberRowOnPage, totalCount)  ; 
          if(lstthuchi.size()>0)
         thchitong = dcDAO.getTHopThuChi(strWhere, null, loai_tien)  ; 
        PagingBean pagingBean = new PagingBean();

        pagingBean.setCurrentPage(currentPage);
        pagingBean.setNumberOfRow(totalCount[0].intValue());
        pagingBean.setRowOnPage(numberRowOnPage);
        request.setAttribute("PAGE_KEY", pagingBean);
          
         request.setAttribute("lstthuchi", lstthuchi);
        request.setAttribute("thchitong", thchitong);
        request.setAttribute("dmNH", dmNH);
      }catch(Exception e){
        throw e;
      }finally{
          if(!conn.isClosed())
            close(conn);
      }
      return mapping.findForward("success");
  } 
  protected ActionForward printAction(ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
      Connection conn = null;
    String   strWhere ="";
    String reportName = "";
      try{
        conn = getConnection();
        HttpSession session = request.getSession();
        String id_kb =
            session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
        BCTHopQTThuChiForm qtForm  =(BCTHopQTThuChiForm)form; 
        TTSPLoadDMuc  loadDM = new TTSPLoadDMuc(conn); 
        DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
        List dmTienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);   
        request.setAttribute("dmTienTe", dmTienTe);          
         // thuc hien load form 
        loadDM.getDMTinh( conn, request);
          
        String strAction =
            request.getParameter(AppConstants.REQUEST_ACTION);
        String strPathFont = AppConstants.FONT_FOR_REPORT;
        InputStream reportStream = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = new HashMap();
        StringBuffer sbSubHTML = new StringBuffer();
        Locale rpt_local = new Locale("vi", "VI");
        String p_loai_tien = qtForm.getLoai_tien();
        String p_ma_kb = "";
        String p_sp065 = "SP_065";
        if(p_loai_tien == null || "".equals(p_loai_tien))
          p_loai_tien = "VND";
        String strLoaiTien = AppConstants.FORMAT_CURRENTCEY_PATERN_VND;
        if(!"VND".equals(p_loai_tien)){
          strLoaiTien = AppConstants.FORMAT_CURRENTCEY_PATERN_NT;  
          p_sp065 = "SP_065_NGOAI_TE";
        }
        if(qtForm.getNhkb_huyen() != null && !qtForm.getNhkb_huyen().equals("")){
          p_ma_kb = qtForm.getNhkb_huyen();   
        }else{
          p_ma_kb = qtForm.getNhkb_tinh();  
        }  
          
          
        strWhere = whereClause(qtForm);
       
          
        reportName = "/rptTHopQTThuChi";
          
        parameterMap.put("p_ma_kb", id_kb);
        parameterMap.put("p_where_clause", strWhere);  
        parameterMap.put("p_tu_ngay", qtForm.getTu_ngay());  
        parameterMap.put("p_den_ngay", qtForm.getTu_ngay()); 
        parameterMap.put("p_loai_tien", p_loai_tien);  
        parameterMap.put("p_065", p_sp065);   
        parameterMap.put("REPORT_LOCALE", rpt_local);
        parameterMap.put("p_CURRENCY_FRMT_PARTERN", strLoaiTien);
        reportStream =
                getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                        reportName +
                                                                                        ".jasper");
          
        jasperPrint =
                JasperFillManager.fillReport(reportStream, parameterMap,
                                             conn);
        String strTypePrintAction = strAction == null ? "" : strAction;
        String strActionName = "skeTKPrintAction.do";
        String strParameter = "";
        ReportUtility rpUtilites = new ReportUtility();
        rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                reportName, strPathFont, strActionName,
                                sbSubHTML.toString(), strParameter);
      }catch(Exception e){
        throw e;
      }finally{
          if(!conn.isClosed())
            close(conn);
      }
      return mapping.findForward("success");
  }  
  
  private String whereClause(BCTHopQTThuChiForm qtForm){
      String strReturn = "";
    String nhkb_tinh = qtForm.getNhkb_tinh();
    String nhkb_huyen = qtForm.getNhkb_huyen();
    String ma_ngan_hang = qtForm.getMa_ngan_hang();
    String loai_tien = qtForm.getLoai_tien();
    String tu_ngay = qtForm.getTu_ngay();
    String den_ngay = qtForm.getDen_ngay();
     
     if(nhkb_huyen != null && !nhkb_huyen.equals("")){
       strReturn += " and e.id = '"+nhkb_huyen+"'";   
     }
     if(nhkb_tinh != null && !nhkb_tinh.equals("")){
       strReturn += " and (e.id = '"+nhkb_tinh+"' or e.id_cha = '"+nhkb_tinh+"')";  
     }
     if(ma_ngan_hang != null && !ma_ngan_hang.equals("")){
       strReturn += " and a.RECEIVE_BANK like '__"+ma_ngan_hang+"%'";   
     }          
     if(loai_tien != null && !loai_tien.equals("")){
       strReturn += " and a.loai_tien = '"+loai_tien+"'";  
     }else{
       loai_tien = "VND";  
     }
     
     if(tu_ngay != null && !tu_ngay.equals("")){
       strReturn += " and a.ngay_dc >= to_date('"+tu_ngay+"','dd/MM/yyyy')";   
     }
     if(den_ngay != null && !den_ngay.equals("")){
       strReturn += " and a.ngay_dc <= to_date('"+den_ngay+"','dd/MM/yyyy')";   
     }
      return strReturn;
  }
}
