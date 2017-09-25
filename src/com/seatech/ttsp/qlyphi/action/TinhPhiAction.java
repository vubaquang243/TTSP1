package com.seatech.ttsp.qlyphi.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.qlyLaiSuat.LaiSuatDAO;
import com.seatech.ttsp.qlyLaiSuat.form.LaiSuatForm;

import com.seatech.ttsp.qlyphi.BKeTinhPhiVO;
import com.seatech.ttsp.qlyphi.QuanLyPhiDAO;
import com.seatech.ttsp.qlyphi.form.QuanLyPhiForm;

import com.seatech.ttsp.tknhkb.TKNHKBacDAO;

import com.seatech.ttsp.tknhkb.TKNHKBacVO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.math.BigDecimal;

import java.sql.Connection;

import java.sql.ParameterMetaData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
   * */
public class TinhPhiAction extends AppAction {
    private boolean isThanhToanVien = false;

    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        isThanhToanVien = false;
        boolean execute = request.getParameter("execute") == null ? false : true;
        try{
            conn = getConnection();
            QuanLyPhiForm f = (QuanLyPhiForm)form;
            QuanLyPhiDAO phiDAO = new QuanLyPhiDAO(conn);
            HttpSession session = request.getSession();
            DChieu1DAO dcDAO = new DChieu1DAO(conn);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            DChieu1VO dcVO = new DChieu1VO();
            
            String id_nsd = session.getAttribute("id_nsd").toString();
            String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(); 
            String nh_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) == null ? "": session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            
            dcVO = dcDAO.getCap(" and ma=" + kb_code, null);
            String cap = dcVO.getCap();
            
            List listKhoBac = null;
            List<BKeTinhPhiVO> ketQuaTinhPhiInternal = null;
            List<BKeTinhPhiVO> ketQuaTinhPhiExternal = null;
            //lay list phi va kb
            String condition = getConditionPerRole(kb_code, cap);
            listKhoBac = (List)dcDAO.getDMucKB_huyen(condition, null);
            List tienTe = tienTeDAO.simpleMaNgoaiTe(isThanhToanVien == true ? " and kb_id = " + kb_id : "", null);
            request.setAttribute("listKhoBac", listKhoBac); 
            request.setAttribute("tienTe", tienTe);
            
            //tinh phi va lay du lieu hien thi
            if(execute){
                f.setLoai_tien(f.getLoai_tien().toUpperCase());
                if(isThanhToanVien){ //Thanh to�n vi�n th� tinh ph� va xem
                   Map result = phiDAO.operatorPhi(nh_kb,f,id_nsd);
                   if(!result.containsKey("success")){
                      throw new Exception(result.get("error").toString());
                   }
                }
                ketQuaTinhPhiInternal = getKetQuaTinhPhi("INT",f,phiDAO);
                ketQuaTinhPhiExternal = getKetQuaTinhPhi("EXT",f,phiDAO);
                long totalLtt = 0;
                BigDecimal totalSoTien = new BigDecimal(0);
                BigDecimal totalPhi = new BigDecimal(0);
                BigDecimal totalPhiNguyenTe = new BigDecimal(0);
                String vatSample = "";
                
                for(BKeTinhPhiVO vo : ketQuaTinhPhiInternal){
                    totalLtt += vo.getSo_ltt();
                    totalSoTien = totalSoTien.add(vo.getSo_tien());
                    totalPhi = totalPhi.add(vo.getPhi());
                    if(vo.getTong_phi_nguyen_te() != null)
                      totalPhiNguyenTe = totalPhiNguyenTe.add(new BigDecimal(vo.getTong_phi_nguyen_te()));
                    if(vo.getVat() != null && vatSample.equals(""))
                        vatSample = vo.getVat();
                }
                for(BKeTinhPhiVO vo : ketQuaTinhPhiExternal){
                    totalLtt += vo.getSo_ltt();
                    totalSoTien = totalSoTien.add(vo.getSo_tien());
                    totalPhi = totalPhi.add(vo.getPhi());
                    if(vo.getTong_phi_nguyen_te() != null)
                      totalPhiNguyenTe = totalPhiNguyenTe.add(new BigDecimal(vo.getTong_phi_nguyen_te()));
                    if(vo.getVat() != null && vatSample.equals(""))
                        vatSample = vo.getVat();
                }
                
                request.setAttribute("totalLtt", totalLtt);
                request.setAttribute("totalSoTien", totalSoTien);
                request.setAttribute("totalPhiNguyenTe", totalPhiNguyenTe);
                request.setAttribute("totalPhi", totalPhi);
                request.setAttribute("vatSample", vatSample);
            }
            request.setAttribute("phiInternal", ketQuaTinhPhiInternal);
            request.setAttribute("phiExternal", ketQuaTinhPhiExternal);
            if(f.getLoai_tien() == null || f.getLoai_tien().equals("VND")){
                request.setAttribute("locale", "VND");
            }else {
                request.setAttribute("locale", f.getLoai_tien());
            }
            request.setAttribute("ma_nh_selected_", f.getMa_nh());
        }catch(Exception e){
            conn.rollback();
            throw e;
        }finally{
           close(conn);
        }
        return mapping.findForward("success");
    }

    private String getConditionPerRole(String kb_code, String cap) {
        String kbWhereCondition = "";
        if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
            kbWhereCondition = " ";
        }else if ("0003".equals(kb_code)) {
            kbWhereCondition = " AND a.ma='0003' ";
            isThanhToanVien = true;
        }else if ("5".equals(cap)) {
            kbWhereCondition = " and a.ma_cha=" + kb_code;
        }else {
            kbWhereCondition = " and a.ma =" + kb_code;
            isThanhToanVien = true;
        }
        return kbWhereCondition;
    }

    private List<BKeTinhPhiVO> getKetQuaTinhPhi(String type,QuanLyPhiForm f,QuanLyPhiDAO phiDAO) throws Exception {
        String condition = " and a.NHKB_CHUYEN = (select a.ma_nh from sp_dm_manh_shkb a join sp_dm_htkb b on a.shkb = b.ma where b.id = ?) AND a.nhkb_nhan = ? AND a.LOAI_TIEN = ?";
        Vector params = new Vector();
        params.add(new Parameter(f.getTu_ngay(),true)); 
        params.add(new Parameter(f.getDen_ngay(),true));
        params.add(new Parameter(type,true));
        params.add(new Parameter(f.getMa_kb(),true));
        params.add(new Parameter(f.getMa_nh(),true));
        params.add(new Parameter(f.getLoai_tien(),true));
        return phiDAO.getKetQuaTinhPhi(condition,params);
    }
    

    @Override
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
      Connection conn = null;
      try{
          conn = getConnection();
          QuanLyPhiDAO phiDAO = new QuanLyPhiDAO(conn);
          String ma_nh = request.getParameter("ma_nh");
          Vector params = new Vector();
          String condition = " and a.HE_THONG_NH = SUBSTR(?,3,3) and (a.den_ngay is null or trunc(a.DEN_NGAY) >= trunc(sysdate)) ";
          params.add(new Parameter(ma_nh,true));
          List listPhi = (List)phiDAO.getPhi_phantrang(condition, params, 1, 15, new Integer[1]);
          
          java.lang.reflect.Type listPhiType = new TypeToken<ArrayList>() {
        }.getType();
          String valueJson = new Gson().toJson(listPhi, listPhiType);
          
          response.setContentType(AppConstants.CONTENT_TYPE_JSON);
          PrintWriter out = response.getWriter();
          out.println(valueJson.toString());
          out.flush();
          out.close();
      }catch(Exception e){
          throw e;
      }finally {
          close(conn);
      }
      return mapping.findForward("success");
    }

    @Override
    public ActionForward ycTraCuu(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        Connection conn = null;
        try{
            conn = getConnection();
            QuanLyPhiForm f = (QuanLyPhiForm)form;
            HttpSession session = request.getSession();
            QuanLyPhiDAO phiDAO = new QuanLyPhiDAO(conn);
//            String nh_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) == null ? "": session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            //initilize condition + params
            Vector params = new Vector();
            StringBuilder condition = new StringBuilder("");
            if(f.getMa_kb()!= null && !"".equals(f.getMa_kb())){
                condition.append(" AND NHKB_CHUYEN = (select a.ma_nh from sp_dm_manh_shkb a join sp_dm_htkb b on a.shkb = b.ma where b.id = ?) ");
                params.add(new Parameter(f.getMa_kb(),true));
            }
            if(f.getLoai_gd()!= null && !"".equals(f.getLoai_gd())){
                condition.append(" AND LOAI_GD = ? ");
                params.add(new Parameter(f.getLoai_gd(),true));
            }
            if(f.getLoai_phi()!= null && !"".equals(f.getLoai_phi())){
                condition.append(" AND LOAI_PHI = ? ");
                params.add(new Parameter(f.getLoai_phi(),true));
            }        
            if(f.getTu_ngay()!= null && !"".equals(f.getTu_ngay())){
                condition.append(" AND NGAY_TT >= to_date(?,'DD/MM/YYYY') ");
                params.add(new Parameter(f.getTu_ngay(),true));
            }        
            if(f.getDen_ngay()!= null && !"".equals(f.getDen_ngay())){
                condition.append(" AND NGAY_TT <= to_date(?,'DD/MM/YYYY') ");
                params.add(new Parameter(f.getDen_ngay(),true));
            }
            if(f.getMa_nh()!= null && !"".equals(f.getMa_nh())){
                condition.append(" AND nhkb_nhan = ? ");
                params.add(new Parameter(f.getMa_nh(),true));
            }
            if(f.getLoai_tien()!= null && !"".equals(f.getLoai_tien())){
                condition.append(" AND loai_tien = ? ");
                params.add(new Parameter(f.getLoai_tien(),true));
            }
            
            String pageNumber = f.getPageNumber();
            int currentPage = pageNumber == null ? 1 : Integer.parseInt(pageNumber);
            int rowOnPage = 20;
            Integer totalCount[] = new Integer[1];
            PagingBean pagingBean = new PagingBean();
            //chiTietPhi 
            List chiTietPhi = phiDAO.getChiTietPhi_phanTrang(condition.toString(),params,currentPage, rowOnPage, totalCount);
            request.setAttribute("chiTietPhi", chiTietPhi);
            request.setAttribute("loaiTien", f.getLoai_tien());
            
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setRowOnPage(rowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
        }catch(Exception e){
            throw e;
        }finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

	/**
       * - ThuongDT
       * - 11/2016
       * - nang cap format so tien
       ***/    
  public static final String REPORT_DIRECTORY = "/report";
  public static final String strFontTimeRoman = "/font/times.ttf";
  public static final String fileName = "/rptHoTroTinhPhi";  
  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

      Connection conn = null;
      StringBuffer sbSubHTML = new StringBuffer();
      InputStream reportStream = null;      
      String condition = "";
      String tenNH = "";
      String strLoaiTien = AppConstants.FORMAT_CURRENTCEY_PATERN_VND;
      Locale rpt_local = new Locale("vi", "VI");
      try {  
        conn = getConnection(request);
        HttpSession session = request.getSession();
        QuanLyPhiForm f = (QuanLyPhiForm)form;  
        QuanLyPhiDAO phiDAO = new QuanLyPhiDAO(conn);
        String id_nsd = session.getAttribute("id_nsd").toString();        
        String nh_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) == null ? "": session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
        
        f.setLoai_tien(f.getLoai_tien().toUpperCase());
        if(isThanhToanVien){ //Thanh to�n vi�n th� tinh ph� va xem
           Map result = phiDAO.operatorPhi(nh_kb,f,id_nsd);
           if(!result.containsKey("success")){
              throw new Exception(result.get("error").toString());
           }
        }
        condition = " and a.NGAY_TT >= to_date('"+f.getTu_ngay()+"','DD/MM/YYYY') and a.NGAY_TT <= to_date('"+f.getDen_ngay()+"','DD/MM/YYYY')";   
        condition =condition+ " and a.NHKB_CHUYEN = (select a.ma_nh from sp_dm_manh_shkb a join sp_dm_htkb b on a.shkb = b.ma where b.id = '"+f.getMa_kb()+"') AND a.nhkb_nhan = '"+f.getMa_nh()+"' AND a.LOAI_TIEN = '"+f.getLoai_tien()+"' ";
          if(!"VND".equals(f.getLoai_tien())){
            strLoaiTien = AppConstants.FORMAT_CURRENTCEY_PATERN_NT;
            rpt_local = new Locale("en", "US");
          }
       
        JasperPrint jasperPrint = null;
        HashMap parameterMap = new HashMap();
        ReportUtility rpUtilites = new ReportUtility();   
        tenNH  = getTenNH( conn, f.getMa_nh());
        parameterMap.put("p_ma_nhkb", f.getMa_nh());
        parameterMap.put("p_ten_nhkb", tenNH);
        parameterMap.put("p_tu_ngay", f.getTu_ngay());
        parameterMap.put("p_den_ngay", f.getDen_ngay());
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
          
        String strActionName = "printHoTroTinhPhi.do";
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
       * - ThuongDT
       * - 11/2016
       * - Xay dung cau query de tra cuu
       ***/  	
  public String getSQLPrint(String type,String whereClause){
      String sql = " select to_char(a.ngay_tt,'DD/MM/YYYY') ngay_tt,a.MUC_PHI, count(a.NGAY_TT) so_ltt, decode(a.loai_phi,'MON',MUC_PHI,'') mon,decode(a.loai_phi,'GIA_TRI',MUC_PHI,'') giatri,a.vat," + 
      " NVL(SUM (a.so_tien),0) tong_tien,           NVL(SUM (a.tien_phi),0) tong_phi," + 
      " NVL(SUM (a.phi_nguyen_te),0) tong_phi_nt" + 
      " from SP_BKE_TINH_PHI_KB a join SP_DM_NGAN_HANG b on a.NHKB_CHUYEN = b.MA_NH" + 
      " where 1=1 and a.LOAI_GD = '"+type+"' " + 
      whereClause+
      " and  a.NGAY_TT >= to_date('','DD/MM/YYYY') and a.NGAY_TT <= to_date('','DD/MM/YYYY') and a.NHKB_CHUYEN = (select a.ma_nh from sp_dm_manh_shkb a join sp_dm_htkb b on a.shkb = b.ma where b.id = '3') AND a.nhkb_nhan = '01204002' AND a.LOAI_TIEN = 'VND' " + 
      " GROUP BY ngay_tt,MUC_PHI,loai_phi,vat  ORDER BY ngay_tt desc";
      return sql;
  }
  
  /**
       * - ThuongDT
       * - 11/2016
       * - Lay ten ngan hang
       ***/  
  public String getTenNH(Connection conn,String ma_nh) throws Exception {
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
}
