package com.seatech.ttsp.ttthanhtoan.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;

import com.seatech.framework.utils.FontUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangHOVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanVO;
import com.seatech.ttsp.ttthanhtoan.form.DSachTKhoanNHKBForm;

import java.io.InputStream;

import java.io.PrintWriter;

import java.sql.Connection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

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
  * @create date: 11/2016    
  *
  * @Modify: ThuongDT
  * @Modify date: 17/11/2016
  * @see: sua dieu kien tra cuu vao ham list
  * 
  * @Modify: ThuongDT
  * @Modify date: 18/11/2016
  * @see: sua dieu kien tra cuu vao ham getWhereClause
  * */
 /**
  * @Modify: ThuongDT
  * @Modify date: 18/07/2017
  * @see: check trang thai tai ham getWhereClause;
  * @find: 20170718
  * */ 

  /**
   * @Modify: ThuongDT
   * @Modify date: 28/08/2017
   * @see: sua dieu kien where ma_nh like '%ma%'sang '__ma' tai ham getWhereClause;
   * @find: 20170828
   * */
 
public class DSachTKhoanNHKBAction  extends AppAction{
  
  protected ActionForward executeAction(ActionMapping mapping,
                                        ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
      Connection conn = null;
     
      try {
        conn = getConnection(request);
        loadForm( conn, form, request); 
        PagingBean pagingBean = new PagingBean();
        request.setAttribute("PAGE_KEY", pagingBean);
      } catch (Exception e) {
          throw e;

      } finally {
          close(conn);
      }
      return mapping.findForward("success");
    }
	/*
	thuc hien lay thong tin tra cuu	
	*/	
  public ActionForward view(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
    Connection conn = null;
    Collection lisBangKe = null;
    String strWhereClause = "";
     DSachTKhoanNHKBForm dsForm = (DSachTKhoanNHKBForm)form;
    try {
      //conn = getConnection(request);
      conn = getConnection();
      loadForm( conn,form, request);
      int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
      String page = dsForm.getPageNumber();
      if (page == null || "".equals(page))
          page = "1";
      Integer currentPage = new Integer(page);
      Integer numberRowOnPage = phantrang;
      Integer totalCount[] = new Integer[15]; 
       //su ly vi tri commobox select
      String idxKB = dsForm.getIdxKB() == null ? "" : dsForm.getIdxKB();
      String idxNH = dsForm.getIdxNH() == null ? "" : dsForm.getIdxNH();
      String idxTK = dsForm.getIdxTK() == null ? "" : dsForm.getIdxTK();
      String idxTT = dsForm.getIdxTT() == null ? "" : dsForm.getIdxTT();
      TTThanhToanDAO ttttDAO = new TTThanhToanDAO(conn);
	  // lay dieu kien tra cuu
      strWhereClause = getWhereClause(dsForm);
	  //thuc hien tra cuu lay thong tin
      lisBangKe = ttttDAO.getListDS_TK_NHKB(strWhereClause, null, currentPage,
                                                   numberRowOnPage,
                                                   totalCount);
       
      request.setAttribute("idxKB", idxKB);
      request.setAttribute("idxNH", idxNH);
      request.setAttribute("idxTK", idxTK);
      request.setAttribute("idxTT", idxTT);
      
      request.setAttribute("lisBangKe", lisBangKe);

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
  /*
  in bao cao danh sach tai khoan  
  */
  public static final String REPORT_DIRECTORY = "/report";
  public static final String strFontTimeRoman = "/font/times.ttf";
  public static final String fileName = "/dsachTKhoanNHKB_L";

  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

      Connection conn = null;
      StringBuffer sbSubHTML = new StringBuffer();
      InputStream reportStream = null;
      DSachTKhoanNHKBForm dsForm = (DSachTKhoanNHKBForm)form;
      String strBke = "";
      String strLoaiTK = "";
      String strLoaiTien = "";
      String strNganHang = "";
      String strTrangThai = "";
      String strTThai = "Tất cả";
      String strNgayThangNam = "";
      String teNH = "";
      try {    
        conn = getConnection(request);
        strBke = getWhereClause(dsForm);
        
        JasperPrint jasperPrint = null;
        HashMap parameterMap = new HashMap();
        ReportUtility rpUtilites = new ReportUtility();
       // set parameter bao cao

          strLoaiTK = dsForm.getLoaiTk() == null ? "" : dsForm.getLoaiTk();
        strLoaiTien = dsForm.getLoaiTien() == null ? "" : dsForm.getLoaiTien();
        strNganHang = dsForm.getNgan_hang() == null ? "" : dsForm.getNgan_hang();
        strTrangThai = dsForm.getTrang_thai() == null ? "" : dsForm.getTrang_thai();
        //strTrangThai = dsForm.get
          
        DateFormat ngay = new SimpleDateFormat("dd");
        DateFormat thang = new SimpleDateFormat("MM");
        DateFormat nam = new SimpleDateFormat("yyyy");
        Date date = new Date();
          
        strNgayThangNam = "Ngày "+ ngay.format(date) + " tháng "+ thang.format(date) + " năm "+ nam.format(date);
        if(strLoaiTK.equals("01"))strLoaiTK = "TK tiền gửi";
        if(strLoaiTK.equals("02"))strLoaiTK = "TK thanh toán";
        if(strLoaiTK.equals("03"))strLoaiTK = "TK chuyên thu";
          
        if(strTrangThai.equals("01"))strTThai = "Hoạt động";
        else if(strTrangThai.equals("02"))strTThai = "Ngừng hoạt động";
        teNH = getTenNH(conn,dsForm.getNgan_hang());
        
        
       parameterMap.put("p_loai_tk", strLoaiTK);
        parameterMap.put("p_loai_tien", strLoaiTien);
        parameterMap.put("p_ngan_hang", teNH);
        parameterMap.put("p_trang_thai", strTThai);
        parameterMap.put("p_ngaythangnam", strNgayThangNam);
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
         //set action cho moi lan forward
        String strActionName = "printDSachTKoanNHKB.do";
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
  /**
   * @Create: ThuongDT
   * @Create date: 18/11/2016
   * @see: tim ten ngan hang theo ma;
   * */
  public String getTenNH(Connection conn,String ma_nh) throws Exception {
    String strReturn = "";  
    Collection cll;
    Iterator itr;
    DMNHangDAO tk = new DMNHangDAO(conn);
    DMNHangHOVO tkVO;
    Vector params = new Vector();
    String condition = " and ma_nh like '%"+ma_nh+"%' ";
   // params.add(new Parameter(ma_nh,true));
   
    cll = (Collection)tk.getclDMNHangHO(" and ma_dv = '"+ma_nh+"' ",null);
    itr = cll.iterator();
    if(itr.hasNext()){
      tkVO = (DMNHangHOVO)itr.next();
      strReturn = tkVO.getTen_nh();
    }
    return strReturn;  
  }
 /**
  * @Modify: ThuongDT
  * @Modify date: 18/11/2016
  * @see: sua dieu kien tra cuu   strBke = strBke+" and d.ma_nh like '%"+ma_nh+"%'";
  * */  
  /**
   * @Modify: ThuongDT
   * @Modify date: 18/07/2017
   * @see: bo tra cuu theo trang thai 01 vi o duoi da co if else lay theo tung loai trang thai;
   * @find: 20170718
   * */ 
  public String getWhereClause(DSachTKhoanNHKBForm frm){ 
    
    String strReturn = "";
    // 20170718 bo tra cuu theo trang thai 01 vi o duoi da co if else lay theo tung loai trang thai
    //String strBke = " and c.trang_thai = '01' ";
    
    String strBke = " ";
    String nhkb_tinh =
        frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
    String nhkb_huyen =
        frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
    String ma_nh =
        frm.getNgan_hang() == null ? "" : frm.getNgan_hang();
    String sotk =
        frm.getSoTk() == null ? "" : frm.getSoTk();
    String loai_TK =
        frm.getLoaiTk() == null ? "" : frm.getLoaiTk();
    String loai_tien =
        frm.getLoaiTien() == null ? "" : frm.getLoaiTien();
    String tu_ngay =
        frm.getHluc_tungay() == null ? "" : frm.getHluc_tungay();
    String den_ngay =
        frm.getHluc_denngay() == null ? "" : frm.getHluc_denngay();
    String trang_thai=
        frm.getTrang_thai() == null ? "" : frm.getTrang_thai();
    
    if(!"".equals(nhkb_tinh)){
      strBke = strBke+" and a.id_cha = '"+nhkb_tinh+"'";
    } 
    if(!"".equals(nhkb_huyen)){
      strBke = strBke+" and a.id = '"+nhkb_huyen+"'";
    } 
    if(!"".equals(ma_nh)){
        //20170828 thuongdt sua d.ma_nh like '%"+ma_nh+"%'
      strBke = strBke+" and d.ma_nh like '__"+ma_nh+"%'";
    }
    if(!"".equals(sotk)){
      strBke = strBke+" and c.so_tk = '"+sotk+"'";
    }
    if(!"".equals(loai_TK)){
      strBke = strBke+" and c.loai_tk = '"+loai_TK+"'";
    }
    if(!"".equals(loai_tien)){
      strBke = strBke+" and c.ma_nt = '"+loai_tien+"'";
    }
    if(!"".equals(tu_ngay)){
      strBke = strBke+" and trunc(c.hieu_luc_tungay) >= to_date('"+tu_ngay+"','dd/MM/yyyy')";
    }
    if(!"".equals(den_ngay)){
      strBke = strBke+" and trunc(c.hieu_luc_den_ngay) <= to_date('"+den_ngay+"','dd/MM/yyyy')";
    }
    if(!"".equals(trang_thai)){
      strBke = strBke+" and c.trang_thai = '"+trang_thai+"' ";
    }
    
    strReturn = strBke;
    return strReturn;
  }
  
  /*
  set ham dung chung thu hien load thong tin tra cuu len form
  */
  public void loadForm(Connection conn, ActionForm form,HttpServletRequest request) throws Exception {
    DSachTKhoanNHKBForm dsForm = (DSachTKhoanNHKBForm)form;
    List dmuckb_cha = null;
    Collection dmucTienTe = null;
    Collection dmucnh = null;
    HttpSession session = request.getSession();
    String kb_code =
        session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
   // DMKBacDAO dmKBDAO = new DMKBacDAO(conn);
    DChieu1DAO dao = new DChieu1DAO(conn);
    DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
    DMNHangDAO dmNHDAO = new DMNHangDAO(conn);
    DChieu1VO vo = new DChieu1VO();
    String strCap = " and ma=" + kb_code;
    vo = dao.getCap(strCap, null);
    String cap = vo.getCap();
    dmucnh = dmNHDAO.getclDMNHangHO(" and ma_dv <> '701' ",null);
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
    dmucTienTe = tienTeDAO.simpleMaNgoaiTe("", null);
    request.setAttribute("dmucnh", dmucnh);
    request.setAttribute("dmuctiente", dmucTienTe);
    request.setAttribute("idxTK", dsForm.getSoTk());
  }
  // ajax lay danh sach danh muc tra cuu
  
  /*
   * @Modify: ThuongDT
   * @Modify date: 17/11/2016
   * @see: sua dieu kien tra cuu ma ngan hang neu load theo ma_dv
   * */
  public ActionForward list(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

      Connection conn = null;
      try {
          conn = getConnection();
          TTThanhToanDAO ttdao = new TTThanhToanDAO(conn);
  //            HttpSession session = request.getSession();
          
          String maNH = request.getParameter("nh_id");
          String soTk = request.getParameter("soTk");
          String loai_tien = request.getParameter("loai_tien");
          String condition = "";
          if(soTk != null){//Theo tai khoan chi tiet
  //                String shkb = request.getParameter("shkb");
            if(maNH.length()==3)
              condition = " and c.ma_nh like '%" + maNH + "%' and a.so_tk= '"+soTk+"'";
            else
              condition = " and c.id ='" + maNH + "' and a.so_tk= '"+soTk+"'";
          }else{//Nhieu thang 
              String maKB = request.getParameter("nhkb_id");
            if(maNH.length()==3)
              condition = " and b.id=" + maKB + " and c.ma_nh like '%" + maNH + "%'"; 
            else
              condition = " and b.id=" + maKB + " and c.ma_nh ='" + maNH + "'";
          }
        if(loai_tien != null && !"".equals(loai_tien)){
            condition =condition + " and a.ma_nt ='" + loai_tien + "' ";
        }          
          Collection listTK = ttdao.getListTKNHKB(condition, null);

          java.lang.reflect.Type typeLabelVO = new TypeToken<Collection<TTThanhToanVO>>() {
          }.getType();
          String jsonResult = new Gson().toJson(listTK, typeLabelVO);

          response.setContentType(AppConstants.CONTENT_TYPE_JSON);
          PrintWriter out = response.getWriter();
          out.println(jsonResult.toString());
          out.flush();
          out.close();
      } catch (Exception e) {
          JSONObject jsonRes = new JSONObject();
          jsonRes.put("executeError", FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));
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
