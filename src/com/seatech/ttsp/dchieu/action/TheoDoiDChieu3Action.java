package com.seatech.ttsp.dchieu.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;

import com.seatech.framework.utils.FontUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;

import com.seatech.ttsp.dchieu.DChieu3DAO;
import com.seatech.ttsp.dchieu.form.TheoDoiDChieu3Form;

import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;

import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanVO;

import java.io.InputStream;

import java.io.PrintWriter;

import java.sql.Connection;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
/**
   * @creater: ThuongDT
   * @creat date: 20/11/2016
   * @see: Thuc hien doi chieu quyet toan toan quoc (d/c lan 3)   
   * */

 /**
    * @modify: ThuongDT
    * @modify-date: 13/07/2017
    * @see: bo sung file jasper theodoiDChieu3_2 dung tong hop so lieu truoc ngay hien tai
    * @find: 20170713
    * */

  /**
     * @modify: ThuongDT
     * @modify-date: 12007/2017
     * @see: bo sung ngaydc phuc vu tra cuu thong tin ngay doi chieu cu
     * @find: 20170720
     * */
public class TheoDoiDChieu3Action  extends AppAction{
 //Load trang JSP lan dau
  @Override
  protected ActionForward executeAction(ActionMapping mapping,
                                        ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
      Connection conn = null;
    
      try {
        conn = getConnection(request);
        loadForm( conn, request);
       // request.setAttribute("dmuctien_te", dmuckb_cha);  
        PagingBean pagingBean = new PagingBean();
        request.setAttribute("PAGE_KEY", pagingBean);
      } catch (Exception e) {
          throw e;

      } finally {
          close(conn);
      }
      return mapping.findForward("success");
    }
  //Xem chi tiet ket qua doi chieu
  public ActionForward view(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
    String strDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());  
    Connection conn = null;
    Collection lisBangKe = null;
    String strDVDC = "";
    try {
        conn = getConnection(request);
      loadForm( conn, request);
      DChieu3DAO dchieu3DAO = new DChieu3DAO(conn);
      String strBke = "";
      String strWhereLT = "";
      TheoDoiDChieu3Form frm = (TheoDoiDChieu3Form)form;  
      String idxKB = frm.getIdxKB() == null ? "" : frm.getIdxKB();
      String idxNH = frm.getIdxNH() == null ? "" : frm.getIdxNH();
        
       //20170720 
      String strLoaiTien = frm.getLoaiTien() == null ? "" : frm.getLoaiTien();
      strLoaiTien = "".equals(strLoaiTien)?"VND":strLoaiTien;  
      strLoaiTien = strLoaiTien.equals("VNĐ")?"VND":strLoaiTien;
        
      String page = frm.getPageNumber();
      int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
      if (page == null || "".equals(page))
          page = "1";
      Integer currentPage = new Integer(page);
      Integer numberRowOnPage = phantrang;
      Integer totalCount[] = new Integer[15];
      String ngay_dc =
          frm.getNgayDC() == null ? "" : frm.getNgayDC();
      if(!"".equals(strLoaiTien))
        strWhereLT = " and loai_tien = '"+strLoaiTien+"' ";
      strBke = getWhereClause(frm);
     // strtong = getWhereClausetong(frm);
        
		//lay danh sach bang ke da duoc phan trang
      lisBangKe = dchieu3DAO.getDChieu3List_PTrang(strBke,ngay_dc,strLoaiTien,strWhereLT,null,currentPage,numberRowOnPage, totalCount);
      strDVDC = dchieu3DAO.getDChieu3_tongDC(strBke,ngay_dc,strLoaiTien,strWhereLT,"<br/>", null);
      request.setAttribute("idxKB", idxKB);
      request.setAttribute("idxNH", idxNH);
      request.setAttribute("dvdc", strDVDC);
      request.setAttribute("lisBangKe", lisBangKe);
      //20170720 
      if(!ngay_dc.equals(strDate))
      request.setAttribute("ngaydc", ngay_dc);

      request.setAttribute("idxKB", idxKB);
      PagingBean pagingBean = new PagingBean();
      pagingBean.setCurrentPage(currentPage);
      pagingBean.setNumberOfRow(totalCount[0].intValue());
      pagingBean.setRowOnPage(numberRowOnPage);
      request.setAttribute("PAGE_KEY", pagingBean);
    } catch (Exception e) {
        throw e;

    } finally {
        close(conn);
    }
      
    return mapping.findForward("success");       
  }
  
  //In bao cao ket qua doi chieu
  public static final String REPORT_DIRECTORY = "/report";
  public static final String strFontTimeRoman = "/font/times.ttf";
  public static final String fileName1 = "/theodoiDChieu3"; 
  //20170713
  public static final String fileName2 = "/theodoiDChieu3_2";  
  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {   
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      Connection conn = null;
      InputStream reportStream = null;
      StringBuffer sbSubHTML = new StringBuffer();
      String strBke = "";
      String strBke2 = "";
      String strDVDC = "";
      String strWhereLT = "";
      String strQuyetToan = "sp_065";
      String strttsp = "sp_bk_dc1";
      
      try {
        conn = getConnection(request);
        TheoDoiDChieu3Form frm = (TheoDoiDChieu3Form)form; 
        DChieu3DAO dchieu3DAO = new DChieu3DAO(conn);  
        int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
        String page = frm.getPageNumber();
        if (page == null || "".equals(page))
            page = "1";
	//20170720 Kiem tra loai tien
       // String strLoaiTien = frm.getLoaiTien() == null ? "" : frm.getLoaiTien();       
       String strLoaiTien = frm.getLoaiTien() == null ? "" : frm.getLoaiTien();
       strLoaiTien = "".equals(strLoaiTien)?"VND":strLoaiTien;  
       strLoaiTien = strLoaiTien.equals("VNĐ")?"VND":strLoaiTien;  
          
        if(!"".equals(strLoaiTien))
          strWhereLT = " and loai_tien = '"+strLoaiTien+"' ";
          if(!"VND".equals(strLoaiTien)){
            strQuyetToan = "sp_065_ngoai_te"; 
            strttsp = "sp_bk_dc1_ngoai_te";
          }
        Integer currentPage = new Integer(page);           
        strBke2 = " where ROWNUM >="+((phantrang*currentPage)-phantrang+1)+" and ROWNUM <="+(phantrang*currentPage);
		
		//build dieu kien cho bao cao
        String ngay_dc =
            frm.getNgayDC() == null ? "" : frm.getNgayDC();
        //20170713
        String fileName = fileName1;
        String strDate = dateFormat.format(new Date());
         if(!strDate.equals(ngay_dc)) 
           fileName = fileName2;
          
        strBke = getWhereClause(frm);
        JasperPrint jasperPrint = null;
        HashMap parameterMap = new HashMap();
        ReportUtility rpUtilites = new ReportUtility();
        parameterMap.put("p_loai_tien", strWhereLT);
        parameterMap.put("p_quyet_toan", strQuyetToan);  
        parameterMap.put("p_ttsp", strttsp);  
        parameterMap.put("p_so_bc", "");
        parameterMap.put("p_ngay_bc", dateFormat.format(new Date()));
        parameterMap.put("p_dv_tgia", strDVDC);
        parameterMap.put("p_ngay_dc", ngay_dc);
        parameterMap.put("p_whereclause", strBke);
          
        reportStream =
                getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                        fileName +
                                                                                        ".jasper");  
        jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
        String strTypePrintAction =
            request.getParameter(AppConstants.REQUEST_ACTION) == null ?
            "" :
            request.getParameter(AppConstants.REQUEST_ACTION).toString();
          
        String strActionName = "printTheodoiDChieu3.do";
        String strParameter = "";
        String strPathFont =
            getServlet().getServletContext().getContextPath() +
            REPORT_DIRECTORY + strFontTimeRoman;
		//Xuat bao cao
        rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                fileName, strPathFont, strActionName,
                                sbSubHTML.toString(), strParameter);
          
      } catch (Exception e) {
          throw e;

      } finally {
          close(conn);
      }
      return mapping.findForward("success");
    }
	//Build menh de where, phuc vu tra cuu
  public String getWhereClause(TheoDoiDChieu3Form frm){   
    String strReturn = "";
    String strBke = " and 1=1 ";
    String nhkb_tinh =
        frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
    
      String nhkb_huyen =
          frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
      String ma_nh =
          frm.getNgan_hang() == null ? "" : frm.getNgan_hang();
//    String ngay_dc =
//        frm.getNgayDC() == null ? "" : frm.getNgayDC();
      String trang_thai_dc =
          frm.getTrangThaiDC() == null ? "" : frm.getTrangThaiDC();
      String loai_tien =  frm.getLoaiTien() == null ? "" : frm.getLoaiTien();
    if(!"".equals(nhkb_tinh)){
      strBke = strBke+" and id_cha = '"+nhkb_tinh+"'";
    }  
      if(!"".equals(nhkb_huyen)){
        strBke = strBke+" and id = '"+nhkb_huyen+"'";
      } 
      if(!"".equals(ma_nh)){
        strBke = strBke+" and INSTR(ma_nh, '"+ma_nh+"') = 3 ";
      }
      // chuyen sang where trong truong hop cu the
//      if(!"".equals(ngay_dc)){
//      strBke = strBke+" and trunc(a.ngay_dc) = to_date('"+ngay_dc+"', 'dd/MM/yyyy')";
//      }
      if(!"".equals(trang_thai_dc)){
          if("00".equals(trang_thai_dc))
            strBke = strBke+" and (e.trang_thai = '"+trang_thai_dc+"' or e.trang_thai is null)";
          else
            strBke = strBke+" and e.trang_thai = '"+trang_thai_dc+"'";
      }
//    if(!"".equals(loai_tien)){
//      strBke = strBke+" and a.ma_nt = '"+loai_tien+"'";
//    }
      strReturn = strBke;
     return strReturn;
  }
   
   //load du lieu cac comboboox tra cuu
  public void loadForm(Connection conn,HttpServletRequest request) throws Exception {
    List dmuckb_cha = null;
    List dmuctien_te = null;
    List dmucnh = null;
    HttpSession session = request.getSession();
    String kb_code =
        session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
   // DMKBacDAO dmKBDAO = new DMKBacDAO(conn);
   // DMKBacVO vo = new DMKBacVO();
    DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
    DChieu1DAO dao = new DChieu1DAO(conn);
    DChieu1VO vo = new DChieu1VO();
    List tienTe = tienTeDAO.simpleMaNgoaiTe("", null);
    TTThanhToanDAO ttttDAO = new TTThanhToanDAO(conn);
   
    String strCap = " and ma=" + kb_code;
    vo = dao.getCap(strCap, null);
    String cap = vo.getCap();
    
    if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
        String strWhere = " ";
       // strWhere = " (a.cap='"+cap+"' or a.ma='" + kb_code + "' ) ";
        dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
       // dmuckb_cha = (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(strWhere, null);
        request.setAttribute("dmuckb_tinh", dmuckb_cha);
        request.setAttribute("QTHTTW", "QTHTTW");
    } else if ("0003".equals(kb_code)) {
       String strWhere = " AND a.ma='0003' ";
       dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
       request.setAttribute("dmuckb_tinh", dmuckb_cha);
    } else if ("5".equals(cap)) {
       String strWhere = "";
       strWhere += " and c.ma=" + kb_code;
       dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
       request.setAttribute("dmuckb_tinh", dmuckb_cha);
    } else {
       String strWhere = "";
       strWhere += " and c.id in (select id_cha from sp_dm_htkb where ma=" + kb_code + ")";
       dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
       request.setAttribute("dmuckb_tinh", dmuckb_cha);
    }
    dmucnh = (List)ttttDAO.getDMucNH("",null);
    
    
    request.setAttribute("tienTe", tienTe);
    request.setAttribute("dmucnh", dmucnh);
    
  }
  
  
   //Cap nhat thong tin thanh toan   
  public ActionForward addExc(ActionMapping mapping, ActionForm form,
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
          
          String strNH = " and b.id=" + strMaKB;
		  //Lay danh sach ngan hang
          colNH = ttdao.getListNH(strNH, null);

          java.lang.reflect.Type listNH =
              new TypeToken<Collection<TTThanhToanVO>>() {
          }.getType();
		  
		  //build JSON
          strJSON = new Gson().toJson(colNH, listNH);

          response.setContentType(AppConstants.CONTENT_TYPE_JSON);
          PrintWriter out = response.getWriter();
          out.println(strJSON.toString());
          out.flush();
          out.close();
      } catch (Exception e) {
          JSONObject jsonRes = new JSONObject();
          jsonRes.put("executeError",
                      FontUtil.unicodeEscape("Lá»—i: " + e.getMessage()));

          response.setHeader("X-JSON", jsonRes.toString());
      } finally {
          close(conn);
      }
      if (!response.isCommitted())
          return mapping.findForward(AppConstants.SUCCESS);
      else
          return null;
  }
}
