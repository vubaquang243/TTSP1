package com.seatech.ttsp.reports.theodoisolieuttsp;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;

import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieu.DChieu1DAO;

import com.seatech.ttsp.dchieu.DChieu1VO;

import com.seatech.ttsp.dchieu.KQDCChiTietVO;

import com.seatech.ttsp.dmtiente.DMTienTeDAO;

import java.io.InputStream;

import java.sql.Connection;

import java.sql.Date;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
   * @track: 201611
   * */

 /**
    * @modify: ThuongDT
    * @modify date: 21/04/2017   
    * @see: sua trung so tai khoan KB loi bao cao 7c
    * @track: thuongdt-20170421
    * */
public class SoLieuTTSPAction extends AppAction {
	//thuc hien viec in bao cao
    @Override
    protected ActionForward printAction(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
      Connection conn = null;
      try{
          conn = getConnection();
          TTSPUtils ttspUtils = new TTSPUtils(conn);
          SoLieuTTSPForm f = (SoLieuTTSPForm)form;
          SoLieuTTSPDAO dao = new SoLieuTTSPDAO(conn);
          HashMap reportParams = new HashMap();
          Vector params = new Vector();
          StringBuffer sbSubHTML = new StringBuffer();
          String reportName = "/report/ReportSolieuTTSP.jasper";
          InputStream reportStream = null;
          JasperPrint jasperPrint = null;
          String strAction = request.getParameter(AppConstants.REQUEST_ACTION);
          String strPathFont = getServlet().getServletContext().getContextPath() + AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
          //thuongdt-20170421 bo sung trang thai truong hop trung so tai khoan
          String where = " a.id = ? AND e.ma_nh = ? and c.TRANG_THAI = ? ";
          
          
          params.add(new Parameter(f.getNhkb_huyen(),true));
          params.add(new Parameter(f.getNgan_hang(),true));
          //thuongdt-20170421 bo sung trang thai truong hop trung so tai khoan
          params.add(new Parameter("01",true));
          SoLieuTTSPVO vo = dao.getRecord(where, params);
          
          String p_ten_kb_tinh  =  vo.getTen_kb_tinh();
          String p_ten_kb_huyen = vo.getTen_kb_huyen();
          String p_ma_kb = vo.getMa_nh_kb();
          
          String turnBackADate = getDateBeforeDC(f);
          String[] tempDate = turnBackADate.split("/");
          String dateBeforeDC = ttspUtils.getNgayLamViecTruoc(tempDate[2]+StringUtil.lpad(tempDate[1], 2,'0')+StringUtil.lpad(tempDate[0], 2,'0'));
          //201611-sua lay so du dau nay dang string-begin
          String p_so_du_dau_ngay = getSoDuDauNgay(dateBeforeDC,vo.getMa_nh_kb(),dao,f.getNgan_hang(),f.getLoai_tien()).toString(); 
//          for(int i = p_so_du_dau_ngay.length()-3; i > 0 ; i-=3){
//              p_so_du_dau_ngay.insert(i, ",");
//          }
          //201611-sua lay so du dau nay dang string-end
          String tempHanMuc[] = vo.getHan_muc_no().split("");
//          String p_ma_nh= f.getNgan_hang();
          String hanMuc = tempHanMuc.length > 3 ? tempHanMuc[1]+tempHanMuc[2] : vo.getHan_muc_no();
		  
          String strWhere = " AND ma_kb='"+vo.getMa_nh_kb()+"' AND ma_nh='"+f.getNgan_hang()+"' AND to_char(ngay_gd,'DD/MM/YYYY')='"+ f.getNgay_dc()+"'";
          Collection colNoiDung = null;
          colNoiDung = dao.getNoiDung(strWhere, null);
          StringBuilder ghi_chu = new StringBuilder();
          if (colNoiDung != null) {
              Iterator iter1 = colNoiDung.iterator();
              int i = 1;
              while (iter1.hasNext()) {
                  vo = (SoLieuTTSPVO)iter1.next();
                  if(i==1 && !"null".equals(vo.getGhi_chu()) && vo.getGhi_chu()!=null && !"".equals(vo.getGhi_chu())){
                      if(colNoiDung.size()<2){
                        ghi_chu.append(vo.getGhi_chu());
                      }else{
                        ghi_chu.append("\n  - "+vo.getGhi_chu());
                      }
                  }else{
                      if(vo.getGhi_chu()!=null && !"".equals(vo.getGhi_chu()) && !"null".equals(vo.getGhi_chu()))
                        ghi_chu.append("\n  - "+ vo.getGhi_chu());
                  }
                  i++;
              }
          }
          reportParams.put("p_TEN_KB_TINH", p_ten_kb_tinh);
          reportParams.put("p_TEN_KB_HUYEN", p_ten_kb_huyen);
          reportParams.put("p_MA_KB", p_ma_kb);
          reportParams.put("p_MA_NH", f.getNgan_hang());
          reportParams.put("p_HAN_MUC", hanMuc);
          reportParams.put("p_NGAY_DC", f.getNgay_dc());
          reportParams.put("p_GHI_CHU", ghi_chu);          
          reportParams.put("p_SP_065", f.getLoai_tien().equals("VND") ? "sp_065" : "sp_065_ngoai_te");
          reportParams.put("p_LOAI_TIEN", f.getLoai_tien());
          //201611
          if ("VND".equalsIgnoreCase(f.getLoai_tien()) || "177".equalsIgnoreCase(f.getLoai_tien())) {
              reportParams.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_VND);
              reportParams.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
		  //ThuongDT-20161201-Sua format so tien-begin
           p_so_du_dau_ngay =  StringUtil.formatCurrency(p_so_du_dau_ngay.toString(),177);
          } else {
              reportParams.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_NT); //thuongdt-20170518 sua format tien
              //reportParams.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
              reportParams.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
            p_so_du_dau_ngay =  StringUtil.formatCurrency(p_so_du_dau_ngay.toString(),171);
          }          
          reportParams.put("p_SO_DU_DAU_NGAY", p_so_du_dau_ngay);
		  //ThuongDT-20161201-Sua format so tien-end          
          sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dc\" value=\"" +
                           f.getNgay_dc() + "\" id=\"ngay_dc\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_huyen\" value=\"" +
                           f.getNhkb_huyen() + "\" id=\"nhkb_huyen\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"ngan_hang\" value=\"" +
                           f.getNgan_hang() + "\" id=\"ngan_hang\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_tinh\" value=\"" +
                          f.getNhkb_tinh() + "\" id=\"nhkb_tinh\"></input>");
          
          reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(reportName);
          jasperPrint = JasperFillManager.fillReport(reportStream, reportParams, conn);
          String strTypePrintAction = strAction == null ? "" : strAction;
          String strActionName = "PrintReportSoLieuTTSP.do";
          String strParameter = "";
          ReportUtility rpUtilites = new ReportUtility();
          rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                  reportName, strPathFont, strActionName,
                                  sbSubHTML.toString(), strParameter);
      
        }catch(Exception e){
            throw e;
        }finally{
          close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    private String getDateBeforeDC(SoLieuTTSPForm f) {
      String[] tempDate = f.getNgay_dc().split("/");
      Calendar calendar = Calendar.getInstance();
      calendar.set(Integer.parseInt(tempDate[2]), Integer.parseInt(tempDate[1])-1, Integer.parseInt(tempDate[0]));
      calendar.add(Calendar.DATE,-1);
      String result = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
      return result;
    }

    @Override
    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        List cacKhoBacTinh = null;
        
        conn = getConnection();
        HttpSession session = request.getSession();
        DChieu1DAO dao = new DChieu1DAO(conn);
        DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
        DChieu1VO vo = new DChieu1VO();
        String kbCode = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
        try{
          vo = dao.getCap(" and ma = "+kbCode, null);
          String cap = vo.getCap();
          List dmTienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);
		  //ThuongDT-20161202-sua lay kho bac tinh-begin
          if("0001".equals(kbCode) || "0002".equals(kbCode)){
            cacKhoBacTinh = (List)dao.getDMucKB_Tinh("", null);
            request.setAttribute("dftinh", "dftinh");
            request.setAttribute("dchieu3", "dchieu3");
          }else if ("0003".equals(kbCode)){
            String whereClause = " AND a.ma = '0003' ";
            cacKhoBacTinh = (List)dao.getDMucKB_Tinh(whereClause, null);
          }else if ("5".equals(cap)){
            String whereClause = " AND a.ma = " + kbCode;
            cacKhoBacTinh = (List)dao.getDMucKB_Tinh(whereClause, null);
          }else{
            String whereClause = " and a.id_cha in (select id_cha from sp_dm_htkb where ma = " + kbCode + ")";
            cacKhoBacTinh = (List) dao.getDMucKB_Tinh(whereClause, null);
          }
		  //ThuongDT-20161202-sua lay kho bac tinh-end
          request.setAttribute("dmTienTe", dmTienTe);
        }catch(Exception e){
          throw e;
        }finally{
          close(conn);
        }
        request.setAttribute("dmuckb_tinh", cacKhoBacTinh);
        return mapping.findForward("success");
    }
	//201611
    private StringBuilder getSoDuDauNgay(String dateBeforeDC, String makb,SoLieuTTSPDAO dao,String manh,String loaitien) throws Exception {
        StringBuilder builder = new StringBuilder("");
        long soDuCOT = dao.getSoDuCOT(dateBeforeDC,makb,manh,loaitien);//bo sung tra cu theo loai tien
        long soThu = dao.getSoThuChi("D",dateBeforeDC,makb,manh,loaitien);//bo sung tra cu theo loai tien
        long soChi = dao.getSoThuChi("C",dateBeforeDC,makb,manh,loaitien);//bo sung tra cu theo loai tien
        builder.append(soDuCOT-soThu+soChi);
        return builder;
    }
}
