package com.seatech.ttsp.onlinettinttoan.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.onlinettinttoan.OnlineTTinTToanDAO;
import com.seatech.ttsp.onlinettinttoan.OnlineTTinTToanVO;
import com.seatech.ttsp.onlinettinttoan.form.OnlineTTinTToanForm;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import com.seatech.ttsp.ttthanhtoan.form.DSachTKhoanNHKBForm;

import java.io.InputStream;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
   * @modify: ThuongDT
   * @modify date: 11/2016   
   * @see: sua them format so trong bao cao
   * */
public class OnlineTTinTToanAction extends AppAction {
  String strMaNT = ""; 
  String whereClause = ""; 
  String whereClause2 = ""; 
  String dvTien = ""; 
  Vector v_param = new Vector();

  
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "QLY_TK.TRUYVAN_TTIN_TTOAN")) {
            return mapping.findForward("errorQuyen");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            saveVisitLog(conn, session, "QLY_TK.TRUYVAN_TTIN_TTOAN", "");
            OnlineTTinTToanForm f = (OnlineTTinTToanForm)form;
            
            TTThanhToanDAO ttttDAO = new TTThanhToanDAO(conn);
            OnlineTTinTToanDAO ttinttoanDAO = new OnlineTTinTToanDAO(conn);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            

            request.setAttribute("timer_refresh", getThamSoHThong(AppConstants.TIMER_REFRESH, session));


            getWhereClause(f , request);

			//Lay danh sach thoong tin thanh toan
            List<OnlineTTinTToanVO> colTTinTToan = (List<OnlineTTinTToanVO>)ttinttoanDAO.getTTinTToanList(whereClause, whereClause2, v_param, dvTien);
			//Lay tong TDoi
            List<OnlineTTinTToanVO> colSum = (List<OnlineTTinTToanVO>)ttinttoanDAO.getSumTDoi(whereClause, whereClause2, v_param, dvTien);
            colSum.get(0).setMa_nt(strMaNT);
			//Lay danh muc ngan hang
            Collection colDMNH = ttttDAO.getDMucNH(null,null);
            List tienTe = tienTeDAO.simpleMaNgoaiTe(" AND MA_NT <> 'VND' ", null);
            
            DChieu1DAO dcDao = new DChieu1DAO(conn);
			//Lay danh muc kho bac cha
            List dmuckb_cha = (List)dcDao.getDMucKB_Tinh("", null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
            
            request.setAttribute("colTTinTToan", colTTinTToan);
            request.setAttribute("colSum", colSum);
            request.setAttribute("colDMNH", colDMNH);
            request.setAttribute("dmTienTe", tienTe);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");

    }
    
  public static final String REPORT_DIRECTORY = "/report";
  public static final String strFontTimeRoman = "/font/times.ttf";
      public static final String fileName = "/TDoiTKhoanOnline";

	  //In bang ke
  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

      Connection conn = null;
      StringBuffer sbSubHTML = new StringBuffer();
      InputStream reportStream = null;
      OnlineTTinTToanForm f = (OnlineTTinTToanForm)form;
     
      try {    
        conn = getConnection(request);
        OnlineTTinTToanDAO ttinttoanDAO = new OnlineTTinTToanDAO(conn);
        OnlineTTinTToanVO ttinttoanVO;
        getWhereClausePrint( f , request);
        List<OnlineTTinTToanVO> colSum = (List<OnlineTTinTToanVO>)ttinttoanDAO.getSumTDoi(whereClause, whereClause2, v_param, dvTien);
        ttinttoanVO = colSum.get(0);
        JasperPrint jasperPrint = null;
        HashMap parameterMap = new HashMap();
        ReportUtility rpUtilites = new ReportUtility();        
        parameterMap.put("p_whereClause", whereClause);
        parameterMap.put("p_whereClause2", whereClause2);
        parameterMap.put("p_dv_tien", dvTien); 
        parameterMap.put("p_tong_hanmuc", ttinttoanVO.getHan_muc_no());
        parameterMap.put("p_tong_dudaungay", ttinttoanVO.getDu_dau_ngay());
        parameterMap.put("p_tong_ps_thu", ttinttoanVO.getPs_thu());
        parameterMap.put("p_tong_ps_chi", ttinttoanVO.getPs_chi());
        parameterMap.put("p_tong_sodutaithoidiem", ttinttoanVO.getChenh_lech()); 
        //thuongdt-20170518 bo sung them format so tien  
        parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
        parameterMap.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_VND); 
        if(!"VND".equals(strMaNT))         
          parameterMap.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_NT); 
        reportStream =
                getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                        fileName +
                                                                                        ".jasper"); 
        jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
        String strTypePrintAction =
            request.getParameter(AppConstants.REQUEST_ACTION) == null ?
            "" :
            request.getParameter(AppConstants.REQUEST_ACTION).toString();
          
        String strActionName = "printTDoiTKoanOnline.do";
        String strParameter = ""; 
        String strPathFont =
            getServlet().getServletContext().getContextPath() +
            REPORT_DIRECTORY + strFontTimeRoman;

        rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                fileName, strPathFont, strActionName,
                                sbSubHTML.toString(), strParameter);
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
    //build menh de where
    public void getWhereClause(OnlineTTinTToanForm form ,HttpServletRequest request){
      HttpSession session = request.getSession();
      String ma_kb = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
     strMaNT = "VND";
      
      whereClause = ""; 
      whereClause2 = ""; 
      dvTien = ""; 
      v_param = new Vector();
      if (ma_kb != null &&
          ((!"0001".equals(ma_kb) && !"0002".equals(ma_kb)) &&
           !"0003".equals(ma_kb))) {
          whereClause += " AND ma_kb=? ";
          String kb_id = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
          v_param.add(new Parameter(kb_id, true));
      }
      //20170209-manhnv-sualoitruyvan
	  /*
      if (form.getMa_dv() != null && !form.getMa_dv().equals("")) {
          whereClause += " AND substr(a.ma_nh,3,3)=? ";
          v_param.add(new Parameter(form.getMa_dv(), true));
      }
      
      whereClause += " AND c.ma_nt = ? ";
      if(form.getMa_nt() != null && !form.getMa_nt().equals("")){
          v_param.add(new Parameter(form.getMa_nt(), true));
          strMaNT = form.getMa_nt();
      }else{
          v_param.add(new Parameter("VND", true));
      }*/
       
	  
      if (form.getMa_dv() != null && !"".equals(form.getMa_dv())) {                 
          whereClause2 += " AND substr(a.ma_nh,3,3)= '"+form.getMa_dv()+"' ";                              
      }
      if(form.getMa_nt() != null && !"".equals(form.getMa_nt())){
          strMaNT = form.getMa_nt();
          whereClause2 += " AND c.ma_nt = '"+strMaNT+"' ";
      }else{
          whereClause2 += " AND c.ma_nt = 'VND' ";
      }
          //-end-2  
      //Tuan Anh sua 20150408        
        String strKBTinh = "NULL";
        String strKBHuyen = "NULL";
        if(form.getNhkb_tinh()!=null && !"".equals(form.getNhkb_tinh())){
          whereClause2 += " AND d.id_cha = "+form.getNhkb_tinh();
          strKBTinh = form.getNhkb_tinh();
        }
        if(form.getNhkb_huyen()!=null && !"".equals(form.getNhkb_huyen())){
          whereClause2 += " AND d.id = "+form.getNhkb_huyen();
          strKBHuyen = form.getNhkb_huyen();
        }
      dvTien = (form.getDv_tien() == null ? "1" : form.getDv_tien());
      request.setAttribute("strKBTinh",strKBTinh);
      request.setAttribute("strKBHuyen",strKBHuyen);
    }
	//Build menh de where cho tra cu thong tin thanh toan online
  public void getWhereClausePrint(OnlineTTinTToanForm form ,HttpServletRequest request){
    HttpSession session = request.getSession();
    String ma_kb = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
   strMaNT = "VND";    
    whereClause = ""; 
    whereClause2 = ""; 
    dvTien = ""; 
    v_param = new Vector();
    if (ma_kb != null &&
        ((!"0001".equals(ma_kb) && !"0002".equals(ma_kb)) &&
         !"0003".equals(ma_kb))) {        
        String kb_id = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
        whereClause += " AND ma_kb= '"+kb_id+"' ";
    }
    //20170209-manhnv-sualoitruyvan
    /*if (form.getMa_dv() != null && !form.getMa_dv().equals("")) {
        whereClause += " AND substr(a.ma_nh,3,3)= '"+form.getMa_dv()+"' ";       
    }
    
   
    if(form.getMa_nt() != null && !form.getMa_nt().equals("")){
        whereClause += " AND c.ma_nt = '"+ form.getMa_nt()+"' ";
    }else{
      whereClause += " AND c.ma_nt = 'VND' ";
    }*/
     
    if (form.getMa_dv() != null && !"".equals(form.getMa_dv())) {                 
        whereClause2 += " AND substr(a.ma_nh,3,3)= '"+form.getMa_dv()+"' ";                              
    }
    if(form.getMa_nt() != null && !"".equals(form.getMa_nt())){
        strMaNT = form.getMa_nt();
        whereClause2 += " AND c.ma_nt = '"+strMaNT+"' ";
    }else{
        whereClause2 += " AND c.ma_nt = 'VND' ";
    }
          //-end-2   
    //Tuan Anh sua 20150408 
      if(form.getNhkb_tinh()!=null && !"".equals(form.getNhkb_tinh())){
        whereClause2 += " AND d.id_cha = "+form.getNhkb_tinh();        
      }
      if(form.getNhkb_huyen()!=null && !"".equals(form.getNhkb_huyen())){
        whereClause2 += " AND d.id = "+form.getNhkb_huyen();
      }
    dvTien = (form.getDv_tien() == null ? "1" : form.getDv_tien());   
  }
}
