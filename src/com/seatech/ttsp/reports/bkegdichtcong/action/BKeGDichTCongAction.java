package com.seatech.ttsp.reports.bkegdichtcong.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;

import com.seatech.ttsp.reports.bkegdichtcong.forms.BKeGDichTCongForm;

import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;

import java.io.InputStream;

import java.sql.Connection;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Locale;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/* @Creator: ThuongDT
 * @See: Them lop Action cho chuc nang bao cao giao dich thu cong
 * @date: 20161212
 */

public class BKeGDichTCongAction  extends AppAction{
  //LKhoi tao foem	
  public ActionForward list(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
      Connection conn = null;
      
      try {
        conn = getConnection(request);
        loadForm( conn, request); 
      } catch (Exception e) {
          throw e;

      } finally {
          close(conn);
      }
      return mapping.findForward("success");
    }
	
	// in bao cao danh sach giao dich thu cong
  public static final String REPORT_DIRECTORY = "/report";
  public static final String strFontTimeRoman = "/font/times.ttf";
  public static final String fileName = "/rptBKeGDichTCong";  
  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
      Connection conn = null;
      BKeGDichTCongForm f = (BKeGDichTCongForm)form;
      String tenNH = "";
      String condition = "";
      InputStream reportStream = null; 
      StringBuffer sbSubHTML = new StringBuffer();
      String ngay_lap = new SimpleDateFormat("yyyy-MM-dd").format( new Date());
      String[] arrNgayLap = ngay_lap.split("-");
      ngay_lap = "Ngày "+arrNgayLap[2]+" tháng "+arrNgayLap[1]+" năm "+arrNgayLap[0];
      try {
        conn = getConnection(request);
        String strLoaiTien = AppConstants.FORMAT_CURRENTCEY_PATERN_VND;
        Locale rpt_local = new Locale("vi", "VI");
        if(!"VND".equals(f.getLoaiTien())){
          strLoaiTien = AppConstants.FORMAT_CURRENTCEY_PATERN_NT;
          rpt_local = new Locale("en", "US");
        }          
        JasperPrint jasperPrint = null;
        HashMap parameterMap = new HashMap();
        ReportUtility rpUtilites = new ReportUtility();   
        tenNH  = getTenNH( conn, f.getNgan_hang());
       condition = getWhereClause(f);  
        parameterMap.put("p_ten_nh", tenNH);
        parameterMap.put("p_mau", ".....");
        parameterMap.put("p_so", ".....");
        parameterMap.put("p_ngay_qd", "......");
        parameterMap.put("p_loaitien", f.getLoaiTien());
        parameterMap.put("p_ngay_lap", ngay_lap);//Ngày    tháng    năm
        parameterMap.put("p_tu_ngay", f.getTuNgay());
        parameterMap.put("p_den_ngay", f.getDenNgay());
        parameterMap.put("p_whereclause", condition);
        parameterMap.put("p_CURRENCY_FRMT_PARTERN", strLoaiTien);
        parameterMap.put("REPORT_LOCALE", rpt_local);
        reportStream =
                getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                        fileName +
                                                                                        ".jasper"); 
        jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
        String strTypePrintAction =
            request.getParameter(AppConstants.REQUEST_ACTION) == null ?
            "" :
            request.getParameter(AppConstants.REQUEST_ACTION).toString();
          
        String strActionName = "printBKeGDichTCongAction.do";
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
          close(conn);
      }
      return mapping.findForward("success");
    }
	// lay chuoi dieu kien tra cuu
  private String getWhereClause(BKeGDichTCongForm f){
    String strReturn = "";  
    String nhkb_tinh = f.getNhkb_tinh();  
    String nhkb_huyen = f.getNhkb_huyen();  
    String ngan_hang = f.getNgan_hang();  
    String loaiTien = f.getLoaiTien();  
    String tuNgay = f.getTuNgay();  
    String denNgay = f.getDenNgay();  
    
    if (nhkb_tinh != null && !"".equals(nhkb_tinh)){
      strReturn+= " AND htkb.id_cha = '"+nhkb_tinh+"' ";
    }
    if (nhkb_huyen != null && !"".equals(nhkb_huyen)){
      strReturn+= " AND htkb.id='"+nhkb_huyen+"'";
    }
    if (ngan_hang != null && !"".equals(ngan_hang)){        
      strReturn+= " AND gdich.ma_nh like '%"+ngan_hang+"%'";
    }
    if (loaiTien != null && !"".equals(loaiTien)){
      strReturn+= " AND gdich.loai_tien='"+loaiTien+"'";
    }    
    if (tuNgay != null && !"".equals(tuNgay)){
      strReturn+= " AND trunc(gdich.insert_date) >= to_date('"+tuNgay+"','dd/MM/yyyy')";
    } 
    if (denNgay != null && !"".equals(denNgay)){
      strReturn+= " AND trunc(gdich.insert_date) <= to_date('"+denNgay+"','dd/MM/yyyy')";
    }
    return strReturn;
  } 
// lay ten ngan hang theo ma  
  private String getTenNH(Connection conn,String ma_nh) throws Exception {
    String strReturn = "";  
    Collection cll;
    Iterator itr;
    TKNHKBacDAO tk = new TKNHKBacDAO(conn);
    TKNHKBacVO tkVO;
    Vector params = new Vector();
    String condition = " and ma_nh = ? ";
    params.add(new Parameter(ma_nh,true));
    cll = (Collection)tk.getNganHangCoTaiKhoan(condition,params);
    itr = cll.iterator();
    if(itr.hasNext()){
      tkVO = (TKNHKBacVO)itr.next();
      strReturn = tkVO.getTen();
    }
    return strReturn;  
  }
  //load thong tin tra cuu form
  private void loadForm(Connection conn, HttpServletRequest request) throws Exception {
    List dmuckb_cha = null;
    Collection dmucTienTe = null;
    Collection dmucnh = null;
    HttpSession session = request.getSession();
    String kb_code =
        session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
    DChieu1DAO dao = new DChieu1DAO(conn);
    DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
    DMNHangDAO dmNHDAO = new DMNHangDAO(conn);
    DChieu1VO vo = new DChieu1VO();
    dmucnh = dmNHDAO.getclDMNHangHO(" and ma_dv <> '701' ",null);
    String strCap = " and ma=" + kb_code;
    vo = dao.getCap(strCap, null);
    String cap = vo.getCap();   
    if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
        String strWhere = " ";
        dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
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
    request.setAttribute("dmucnh", dmucnh);
    dmucTienTe = tienTeDAO.simpleMaNgoaiTe("", null);
    request.setAttribute("dmuctiente", dmucTienTe);    
  }
}
