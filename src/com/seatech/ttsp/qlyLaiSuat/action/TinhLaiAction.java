package com.seatech.ttsp.qlyLaiSuat.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.onlinettinttoan.OnlineTTinTToanDAO;
import com.seatech.ttsp.onlinettinttoan.OnlineTTinTToanVO;
import com.seatech.ttsp.onlinettinttoan.form.OnlineTTinTToanForm;
import com.seatech.ttsp.qlyLaiSuat.BangKeTinhLaiVO;
import com.seatech.ttsp.qlyLaiSuat.LaiSuatDAO;
import com.seatech.ttsp.qlyLaiSuat.form.LaiSuatForm;

import com.seatech.ttsp.tknhkb.TKNHKBacDAO;

import com.seatech.ttsp.tknhkb.TKNHKBacVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.math.BigDecimal;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Locale;
import java.util.Map;
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
   * 
   * @modify: HungBM
   * @modify date: 09/12/2016   
   * @see: them dieu kien tra cuu theo ma KB
   * */
public class TinhLaiAction extends AppAction {

    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        boolean execute = request.getParameter("execute") == null ? false : true;
        boolean check = request.getParameter("check") == null ? false : true;
        try{
            conn = getConnection(request);
            LaiSuatForm f = (LaiSuatForm)form;
            LaiSuatDAO lsDAO = new LaiSuatDAO(conn);
            TKNHKBacDAO tknhkbDAO = new TKNHKBacDAO(conn);
            HttpSession session = request.getSession();

            String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(); 
            String nh_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) == null ? "": session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
//            String ma_nh = session.getAttribute(AppConstants.APP_NH_SP_SESSION).toString();
            String id_nsd = session.getAttribute("id_nsd").toString();
            
            List<BangKeTinhLaiVO> ketQuaTinhLai = null;
            List listNganHang = getNganHangCoTaiKhoan(tknhkbDAO,kb_id,null);
            //TTV va TTVSGD
//            if(kb_id.equals("3")){
//                listNganHang = getNganHangCoTaiKhoan(tknhkbDAO,kb_id,null);
//            }else{
//                listNganHang = getCacTaiKhoan(tknhkbDAO,kb_id,ma_nh);
//            }
            //Lanh dao
            if(listNganHang.isEmpty()){
                List dmucKB = null;
                DChieu1DAO dcDAO = new DChieu1DAO(conn);
                String kbCondition = null;
                if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                    kbCondition = " ";
                }else {
                    kbCondition = " and a.ma_cha=" + kb_code;
                }
                dmucKB = (List)dcDAO.getDMucKB_huyen(kbCondition, null);
                request.setAttribute("listKBTinh", dmucKB);
            }
            //TTV va TTVSGD thanh toan
            if(execute){
                Map result = lsDAO.operatorLaiSuat(nh_kb,f,id_nsd);
                if(result.containsKey("total")){
					//HungBM-Them dk tra cuu theo ma kho bac
                    String condition = " and c.ma ='"+kb_code+"' and a.SO_TK = ? and a.NGAY >= to_date(?,'DD/MM/YYYY') and a.NGAY <= to_date(?,'DD/MM/YYYY') and a.TINH_TRANG = '00' ";
                    Vector params = new Vector();
                    params.add(new Parameter(f.getSo_tk(),true));
                    params.add(new Parameter(f.getFromDate(),true));
                    params.add(new Parameter(f.getToDate(),true));
                    ketQuaTinhLai = lsDAO.getKetQuaTinhLai(condition,params);
                    request.setAttribute("total", new BigDecimal(result.get("total").toString()));
                    request.setAttribute("loai_tien_total", ketQuaTinhLai.get(0).getLoai_tien());
                    conn.commit();
                }else{
                    request.setAttribute("error", result.get("error"));
                }
            }
            //Lanh dao tra cuu
            if(check){
                String condition = " and c.id = ? and a.ma_nh = ? and a.SO_TK = ? and a.NGAY >= to_date(?,'DD/MM/YYYY') and a.NGAY <= to_date(?,'DD/MM/YYYY') and a.TINH_TRANG = '00' ";
                Vector params = new Vector();
                params.add(new Parameter(f.getMa_kb(),true));
                params.add(new Parameter(f.getMa_nh(),true));
                params.add(new Parameter(f.getSo_tk(),true));
                params.add(new Parameter(f.getFromDate(),true));
                params.add(new Parameter(f.getToDate(),true));
                ketQuaTinhLai = lsDAO.getKetQuaTinhLai(condition,params);
                long total = 0;
                for(int i = 0; i<ketQuaTinhLai.size();i++ ){
                    BangKeTinhLaiVO vo = (BangKeTinhLaiVO)ketQuaTinhLai.get(i);
                    total += vo.getLai();
                }
                request.setAttribute("total", total);
            }
            request.setAttribute("tkLastSelected", f.getSo_tk());
            request.setAttribute("listNganHang", listNganHang);
            request.setAttribute("listKetQua", ketQuaTinhLai);
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }
   
    private List<TKNHKBacVO> getCacTaiKhoan(TKNHKBacDAO tknhkbDAO,
                                String kb_id,String ma_nh) throws Exception {
        Vector params = new Vector();  
        String conditionTaiKhoan = "";      
        conditionTaiKhoan = " and a.KB_ID = ? ";
        params.add(new Parameter(kb_id,true));       
        if(ma_nh != null && !"".equals(ma_nh)){
            conditionTaiKhoan += " and b.ma_nh = '"+ma_nh+"' ";
        }
        return (List<TKNHKBacVO>)tknhkbDAO.getTK_NH_KB(conditionTaiKhoan, params);
    }

    private List getThongTinLai(LaiSuatDAO lsDAO,
                                String ma_nh,String whereCondition) throws Exception {
        Vector params = new Vector();
        if(ma_nh != null && !"".equals(ma_nh)){
            String ma_dv = ma_nh.substring(2, 5);
            params.add(new Parameter(ma_dv,true));
        }
        return (List)lsDAO.getListLaiSuat(whereCondition, params);
    }

    @Override
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection();
            HttpSession session = request.getSession();
            TKNHKBacDAO tknhkbDAO = new TKNHKBacDAO(conn);
            //JsonObject jsonObj = new JsonObject();
            String kb_id = "";
            if(request.getParameter("kb_id") == null){
                kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }else{
                kb_id = request.getParameter("kb_id").toString();
            }
            String ma_nh = request.getParameter("ma_nh");
            List<TKNHKBacVO> listTaiKhoan = getCacTaiKhoan(tknhkbDAO,kb_id,ma_nh);
            
            java.lang.reflect.Type listTaiKhoanType = new TypeToken<ArrayList<TKNHKBacVO>>() {
          }.getType();
            String valueJson = new Gson().toJson(listTaiKhoan, listTaiKhoanType);
            
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(valueJson.toString());
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    private List getNganHangCoTaiKhoan(TKNHKBacDAO tknhkbDAO,String kb_id, Object null1) throws Exception {
        Vector params = new Vector();
        String condition = " and b.KB_ID = ? ";
        params.add(new Parameter(kb_id,true));
        return (List)tknhkbDAO.getNganHangCoTaiKhoan(condition, params);
    }

    @Override
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
      Connection conn = null;
      try{
          conn = getConnection();
          TKNHKBacDAO tknhkbDAO = new TKNHKBacDAO(conn);
          //JsonObject jsonObj = new JsonObject();
          
          String kb_id = request.getParameter("kb_id");
          List listNganHang = getNganHangCoTaiKhoan(tknhkbDAO,kb_id,null);
          
          java.lang.reflect.Type listNganHangType = new TypeToken<ArrayList>() {
        }.getType();
          String valueJson = new Gson().toJson(listNganHang, listNganHangType);
          
          response.setContentType(AppConstants.CONTENT_TYPE_JSON);
          PrintWriter out = response.getWriter();
          out.println(valueJson.toString());
          out.flush();
          out.close();
      }catch(Exception e){
          e.printStackTrace();
      }finally {
          close(conn);
      }
      return mapping.findForward("success");
    }    
   //HungBM-20161110-Them chuc nang in bao cao tinh lai-begin     
  public static final String REPORT_DIRECTORY = "/report";
  public static final String strFontTimeRoman = "/font/times.ttf";
      public static final String fileName = "/hoTroTinhLai";

  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

      Connection conn = null;
      StringBuffer sbSubHTML = new StringBuffer();
      InputStream reportStream = null;      
      String condition = "";
      String strLoaiTien = "";
      String strTuNgay = "";
      String strDenNgay = "";
      String strTenTK = "";
      String strSoTK = "";
      String strloaiTK = "";
      String strLaiSuat = ".....";
      List<BangKeTinhLaiVO> ketQuaTinhLai = null;
      try {  
        conn = getConnection(request);
        LaiSuatForm f = (LaiSuatForm)form;
        strLoaiTien = f.getLoaiTien();
        strTuNgay = f.getFromDate();
        strDenNgay = f.getToDate();
        String strLoaiTienFormat = AppConstants.FORMAT_CURRENTCEY_PATERN_VND;
        Locale rpt_local = new Locale("vi", "VI");
        LaiSuatDAO lsDAO = new LaiSuatDAO(conn);
        TKNHKBacDAO tknhkbDAO = new TKNHKBacDAO(conn);
        HttpSession session = request.getSession();
        String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
        String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(); 
        String nh_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) == null ? "": session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
        String id_nsd = session.getAttribute("id_nsd").toString();
        boolean execute = request.getParameter("execute") == null ? false : true;
        boolean check = request.getParameter("check") == null ? false : true;   
        String tong_lai = "0";
        if(execute){
            Map result = lsDAO.operatorLaiSuat(nh_kb,f,id_nsd);
            if(result.containsKey("total")){
                condition = " and c.ma ='"+kb_code+"' and a.SO_TK = '"+f.getSo_tk()+"' and a.NGAY >= to_date('"+f.getFromDate()+"','DD/MM/YYYY') and a.NGAY <= to_date('"+f.getToDate()+"','DD/MM/YYYY') and a.TINH_TRANG = '00' "; 
              ketQuaTinhLai = lsDAO.getKetQuaTinhLai(condition,null);             
              tong_lai = result.get("total").toString();
              
            }
        }
        //Lanh dao tra cuu
        if(check){
            condition = " and c.id = '"+f.getMa_kb()+"' and a.ma_nh = '"+f.getMa_nh()+"' and a.SO_TK = '"+f.getSo_tk()+"' and a.NGAY >= to_date('"+f.getFromDate()+"','DD/MM/YYYY') and a.NGAY <= to_date('"+f.getToDate()+"','DD/MM/YYYY') and a.TINH_TRANG = '00' ";           
          
          tong_lai = lsDAO.getKetQuaTinhTongLai(condition);
          ketQuaTinhLai = lsDAO.getKetQuaTinhLai(condition,null);
        }
          if(ketQuaTinhLai.size()>0){
            BangKeTinhLaiVO vo = (BangKeTinhLaiVO)ketQuaTinhLai.get(0);
            strloaiTK = vo.getLoai_tk();
            strloaiTK = strloaiTK==null?"":strloaiTK;
            if(strloaiTK.equals("01"))
            strTenTK = "Tài khoản tiền gửi";
            else if(strloaiTK.equals("02"))
            strTenTK = "Tài khoản thanh toán";
            else if(strloaiTK.equals("03"))
            strTenTK = "Tài khoản chuyên thu";
            strSoTK = vo.getSo_tk();
            strLaiSuat = ""+vo.getLai_suat();
          }

        if("VND".equals(strLoaiTien)){
          strLoaiTien = AppConstants.FORMAT_CURRENTCEY_PATERN_NT;
          rpt_local = new Locale("en", "US");  
        }
         
        JasperPrint jasperPrint = null;
        HashMap parameterMap = new HashMap();
        ReportUtility rpUtilites = new ReportUtility();        
        parameterMap.put("p_whereClause", condition);
        parameterMap.put("p_tu_ngay", strTuNgay);
        parameterMap.put("p_den_ngay", strDenNgay);          
        parameterMap.put("p_lai_suat", strLaiSuat);
        parameterMap.put("p_ten_tk", strTenTK);
        parameterMap.put("p_so_tk", strSoTK);
        parameterMap.put("p_tonglai", tong_lai);
        parameterMap.put("p_CURRENCY_FRMT_PARTERN", strLoaiTienFormat);
        /**END**/
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
          
        String strActionName = "printHoTroTinhLai.do";
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
	   //HungBM-20161110-Them chuc nang in bao cao tinh lai-end
}
