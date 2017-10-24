package com.seatech.ttsp.ltt.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.ltt.form.inCTietLTTTQForm;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.InputStream;

import java.sql.Connection;

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

        /* Modifier: HungBM Print
         * Date: 11/11/2016
         * Bo sung tieu thuc "Da quyet toan"/"Chua quyet toan" 
         * =>All: Hien thi ca Da quyet toan va Chua Quyet Toan
         * =>Da quyet toan: code nhu cu, them dieu kien de ra Dien Tu
         * =>Chua Quyet toan: Them code moi.
         * track: 20161111
         * */

public class inCTietLTTTQAction extends AppAction {
  public ActionForward list(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
      Connection conn = null;
      try {
          conn = getConnection(request);
          HttpSession session = request.getSession();
          DChieu1DAO dao = new DChieu1DAO(conn);
          DChieu1VO vo = new DChieu1VO();

          String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
          String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

          List dmucNH = null;
          TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
          dmucNH = (List)TTdao.getDMucNH(null,null);
          request.setAttribute("dmucNH", dmucNH);

          String strCap = " and ma=" + kb_code;
          vo = dao.getCap(strCap, null);
          List dmuckb_cha = null;
          String cap = vo.getCap();
		  //HungBM-20161111-sua lay danh muc tinh-begin
          if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
              String strWhere = " ";
              dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
              request.setAttribute("dmuckb_tinh", dmuckb_cha);
              request.setAttribute("dftinh", "dftinh");
              request.setAttribute("TTTT", "TTTT");
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
              strWhere +=
                      " and c.id in (select id_cha from sp_dm_htkb where ma=" +
                      kb_code + ")";
              dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
              request.setAttribute("dmuckb_tinh", dmuckb_cha);
          }
  		  //HungBM-20161111-sua lay danh muc tinh-end
          DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
          List tienTe = tienTeDAO.simpleMaNgoaiTe(" " , null);
          request.setAttribute("tienTe", tienTe);
      } catch (Exception e) {
          throw e;

      } finally {
          close(conn);
      }
      return mapping.findForward("success");
  }
  
  public static final String REPORT_DIRECTORY = "/report";
  public static final String strFontTimeRoman = "/font/times.ttf";
  //    public static final String fileName = "/rpt_doi_chieu_1";

  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
								   
        /* Modifier: HungBM
         * Date: 11/11/2016
         * Bo sung tieu thuc "Da quyet toan"/"Chua quyet toan" 
         * =>All: Hien thi ca Da quyet toan va Chua Quyet Toan
         * =>Da quyet toan: code nhu cu, them dieu kien de ra Dien Tu
         * =>Chua Quyet toan: Them code moi.
         * 20161111
         * */
        /*--------------------20161111-START-------------------- */
      Connection conn = null;
      StringBuffer sbSubHTML = new StringBuffer();
      InputStream reportStream = null;
      try {
          conn = getConnection(request);
          inCTietLTTTQForm frm = (inCTietLTTTQForm)form;
          HttpSession session = request.getSession();
          String tu_ngay = frm.getTu_ngay();
          String den_ngay = frm.getDen_ngay();
          String ma_dv = frm.getMa_dv();
          String nhkb_huyen = frm.getNhkb_huyen();
          String nhkb_tinh = frm.getNhkb_tinh();
          String loai_qtoan = frm.getLoai_qtoan();
          String loai_tien = frm.getLoai_tien();
          String qtoan_dvi = frm.getQtoan_dvi();

          sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                           tu_ngay + "\" id=\"tu_ngay\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay\" value=\"" +
                           den_ngay + "\" id=\"den_ngay\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"ma_dv\" value=\"" +
                           ma_dv + "\" id=\"ma_dv\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_huyen\" value=\"" +
                           nhkb_huyen + "\" id=\"nhkb_huyen\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_tinh\" value=\"" +
                     nhkb_tinh + "\" id=\"nhkb_tinh\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"loai_qtoan\" value=\"" +
                     loai_qtoan + "\" id=\"loai_qtoan\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"loai_tien\" value=\"" +
                     loai_tien + "\" id=\"loai_tien\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"qtoan_dvi\" value=\"" +
                   qtoan_dvi + "\" id=\"qtoan_dvi\"></input>");

          JasperPrint jasperPrint = null;
          HashMap parameterMap = new HashMap();
          ReportUtility rpUtilites = new ReportUtility();
        
          //Truong hop "Trang Thai" = All
          String strsp_066 = "";
          
            if (qtoan_dvi == null || "".equals(qtoan_dvi)) {
                String fileName = "/rpt_CTiet_LTT_TQuoc";
                reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + fileName + ".jasper");
                String p_sql = "";
                //Them cac dieu kien cho chua quyet toan
                String tinhCQT ="";
                String huyenCQT = "";
                String dvCQT = "";               
                Vector params = null;
                params = new Vector();
                if (nhkb_tinh != null && !"".equals(nhkb_tinh)) {
                    p_sql += " AND f.id_cha= " +nhkb_tinh;
                    tinhCQT  = " AND b.id_cha = "+nhkb_tinh;
                }
                if (ma_dv != null && !"".equals(ma_dv)) {
                    p_sql += " AND substr(a.ma_nh_chuyen,3,3) = '"+ma_dv+"'";
                    dvCQT = " AND  substr(ltt.send_bank,3,3) ='"+ma_dv+"'";
                }
                if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
                    p_sql += " AND f.id= "+nhkb_huyen;
                    huyenCQT = " AND b.id="+nhkb_huyen;
                }
                if (loai_qtoan != null && !"".equals(loai_qtoan)) {
                    p_sql += " AND a.loai_qtoan= '"+loai_qtoan+"'";
                }
                if (loai_tien != null && !"".equals(loai_tien)) {
                    p_sql += " AND a.ma_nt= '"+loai_tien+"'";
                }
                
                //Them cac ban ghi Chua duoc quyet toan:
                //Them Query cac truong hop chua quyet toan:
                
                String strCQT = 
                " UNION " +
                " select '' id,'' id_ref,'' ngay_qtoan,'' loai_qtoan,0 so_tien,'' ma_kb, a.shkb,'' ten_nh,a.ten ten_kh_chuyen,'' lai_phi, '' trang_thai, '' trang_thai_gom, 'Ngày '|| to_char(sysdate,'DD')||' tháng ' || to_char(sysdate,'MM')||' năm ' || to_char(sysdate,'YYYY')||', '|| to_char(sysdate,'HH24')||' giờ ' ||to_char(sysdate,'mi') ||' phút ' ngay_lap \n" + 
                " from (select a.id id, (select ma_nh from SP_DM_NGAN_HANG where id = a.nhkb_nhan) SEND_BANK, \n" + 
                "              (select ma_nh from SP_DM_NGAN_HANG where id = a.nhkb_chuyen) RECEIVE_BANK  ,\n" + 
                "              to_date(a.ngay_tt,'yyyyMMdd') ngay_dc \n" + 
                "              from SP_Ltt a where SUBSTR(a.id,3,3)='701') ltt,\n" + 
                "              (Select id, RECEIVE_BANK,SEND_BANK,ngay_dc from SP_BK_DC1 ) bk,\n" + 
                "              (select id,nhkb_chuyen RECEIVE_BANK,nhkb_nhan SEND_BANK,ngay_tao ngay_dc, trang_thai from SP_066) t066,\n" + 
                "              (select id, nhkb_nhan RECEIVE_BANK, nhkb_chuyen SEND_BANK, ngay_qtoan ngay_dc,ma_nt,loai_qtoan from SP_QUYET_TOAN) qt,\n" + 
                "              SP_DM_MANH_SHKB a, sp_dm_htkb b\n" + 
                "              where  ltt.RECEIVE_BANK = bk.RECEIVE_BANK(+) and ltt.SEND_BANK = bk.SEND_BANK(+) and LTT.NGAY_DC = BK.ngay_dc(+)\n" + 
                "              and ltt.RECEIVE_BANK = t066.RECEIVE_BANK(+) and ltt.SEND_BANK = t066.SEND_BANK(+) and LTT.NGAY_DC = t066.ngay_dc(+)\n" + 
                "              and ltt.RECEIVE_BANK = qt.RECEIVE_BANK(+) and ltt.SEND_BANK = qt.SEND_BANK(+) and LTT.NGAY_DC = qt.ngay_dc(+)\n" + 
                "              and ltt.RECEIVE_BANK = a.ma_nh(+) and a.shkb = b.ma(+)\n" + 
                "              and (\n" + 
                "              (bk.id is not null and t066.id is not null and qt.id is null) \n" + 
                "              or (bk.id is not null and t066.id is null and qt.id is null) \n" + 
                "              or (bk.id is not null and t066.id is not null and qt.id is null)\n" + 
                "              or (bk.id is null and t066.id is not null and qt.id is null )\n" + 
                "              or (bk.id is null and t066.trang_thai = '04' and qt.id is null)\n" + 
                "              )  and ltt.ngay_dc >= to_date('"+tu_ngay+"','DD/MM/YYYY') and ltt.ngay_dc <= to_date('"+den_ngay+"','DD/MM/YYYY')  \n" + tinhCQT + dvCQT + huyenCQT+
                "    UNION              \n" + 
                "              \n" + 
                " select '' id,'' id_ref,'' ngay_qtoan,'' loai_qtoan,0 so_tien,'' ma_kb, a.shkb,'' ten_nh,a.ten ten_kh_chuyen,'' lai_phi, '' trang_thai, '' trang_thai_gom, 'Ngày '|| to_char(sysdate,'DD')||' tháng ' || to_char(sysdate,'MM')||' năm ' || to_char(sysdate,'YYYY')||', '|| to_char(sysdate,'HH24')||' giờ ' ||to_char(sysdate,'mi') ||' phút ' ngay_lap  \n" + 
                " from (select a.id id, (select ma_nh from SP_DM_NGAN_HANG where id = a.nhkb_nhan) RECEIVE_BANK, (select ma_nh from SP_DM_NGAN_HANG where id = a.nhkb_chuyen) SEND_BANK ,to_date(a.ngay_tt,'yyyyMMdd') ngay_dc \n" + 
                "              from SP_Ltt a where SUBSTR(a.id,3,3)<>'701') ltt,\n" + 
                "              (Select id, RECEIVE_BANK,SEND_BANK,ngay_dc from SP_BK_DC1 ) bk,\n" + 
                "              (select id,nhkb_chuyen RECEIVE_BANK,nhkb_nhan SEND_BANK,ngay_tao ngay_dc, trang_thai from SP_066) t066,\n" + 
                "              (select id, nhkb_nhan RECEIVE_BANK, nhkb_chuyen SEND_BANK, ngay_qtoan ngay_dc from SP_QUYET_TOAN) qt,\n" + 
                "              SP_DM_MANH_SHKB a, sp_dm_htkb b\n" + 
                "              where  ltt.RECEIVE_BANK = bk.RECEIVE_BANK(+) and ltt.SEND_BANK = bk.SEND_BANK(+) and LTT.NGAY_DC = BK.ngay_dc(+)\n" + 
                "              and ltt.RECEIVE_BANK = t066.RECEIVE_BANK(+) and ltt.SEND_BANK = t066.SEND_BANK(+) and LTT.NGAY_DC = t066.ngay_dc(+)\n" + 
                "              and ltt.RECEIVE_BANK = qt.RECEIVE_BANK(+) and ltt.SEND_BANK = qt.SEND_BANK(+) and LTT.NGAY_DC = qt.ngay_dc(+) \n" + 
                "              and ltt.RECEIVE_BANK = a.ma_nh(+) and a.shkb = b.ma(+)\n" + 
                "              and (\n" + 
                "              (bk.id is not null and t066.id is not null and qt.id is null) \n" + 
                "              or (bk.id is not null and t066.id is null and qt.id is null) \n" + 
                "              or (bk.id is not null and t066.id is not null and qt.id is null)\n" + 
                "              or (bk.id is null and t066.id is not null and qt.id is null )\n" + 
                "              or (bk.id is null and t066.trang_thai = '04' and qt.id is null)\n" + 
                "              ) and ltt.ngay_dc >= to_date('"+tu_ngay+"','DD/MM/YYYY') and ltt.ngay_dc <= to_date('"+den_ngay+"','DD/MM/YYYY') " + tinhCQT + dvCQT + huyenCQT+ "   ";
                
                p_sql +=strCQT;
                parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                parameterMap.put("p_TU_NGAY", tu_ngay);
                parameterMap.put("p_DEN_NGAY", den_ngay);
                parameterMap.put("p_SQL", p_sql);
                parameterMap.put("p_MA_DV", ma_dv);
                parameterMap.put("p_LOAI_TIEN", loai_tien);
                parameterMap.put("p_TRANG_THAI", qtoan_dvi);
                parameterMap.put("p_bk_066", strsp_066);
                jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
  
                String strTypePrintAction = request.getParameter(AppConstants.REQUEST_ACTION) == null ? "" :
                                                                      request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "printCTLTTTQ.do";
                String strParameter = "";
                String strPathFont =
                        getServlet().getServletContext().getContextPath() +
                        REPORT_DIRECTORY + strFontTimeRoman;
  
                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                            response, fileName, strPathFont,
                                            strActionName, sbSubHTML.toString(),
                                            strParameter);
            }
          
          //Truong hop Da Quyet toan Dien tu:
            if ("y".equalsIgnoreCase(qtoan_dvi)){
              String fileName = "/rpt_CTiet_LTT_TQuoc";
              reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + fileName + ".jasper");
              String p_sql = "";
              Vector params = null;
              params = new Vector();
              if (nhkb_tinh != null && !"".equals(nhkb_tinh)) {
                  p_sql += " AND f.id_cha= " +nhkb_tinh;
              }
              if (ma_dv != null && !"".equals(ma_dv)) {
                  p_sql += " AND substr(a.ma_nh_chuyen,3,3) = '"+ma_dv+"'";
              }
              if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
                  p_sql += " AND f.id= "+nhkb_huyen;
              }
              if (loai_qtoan != null && !"".equals(loai_qtoan)) {
                  p_sql += " AND a.loai_qtoan= '"+loai_qtoan+"'";
              }
              if (loai_tien != null && !"".equals(loai_tien)) {
                  p_sql += " AND a.ma_nt= '"+loai_tien+"'";
              }
              //HungBM: loc ra cac bang ke dien tu 
              strsp_066 = " , sp_066 g ";
              p_sql += " and a.mt_refid = g.id ";
              parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
              parameterMap.put("p_TU_NGAY", tu_ngay);
              parameterMap.put("p_DEN_NGAY", den_ngay);
              parameterMap.put("p_SQL", p_sql);
              parameterMap.put("p_MA_DV", ma_dv);
              parameterMap.put("p_LOAI_TIEN", loai_tien);
              parameterMap.put("p_TRANG_THAI", qtoan_dvi);
              parameterMap.put("p_bk_066", strsp_066);
              jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
  
              String strTypePrintAction = request.getParameter(AppConstants.REQUEST_ACTION) == null ? "" :
                                                                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
              String strActionName = "printCTLTTTQ.do";
              String strParameter = "";
              String strPathFont =
                      getServlet().getServletContext().getContextPath() +
                      REPORT_DIRECTORY + strFontTimeRoman;
  
              rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                          response, fileName, strPathFont,
                                          strActionName, sbSubHTML.toString(),
                                          strParameter);        
            }
          
          //Truong hop Chua quyet toan
          if ("n".equalsIgnoreCase(qtoan_dvi)){
            String fileName = "/rpt_CTiet_LTT_TQuoc_CQT";
            reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + fileName + ".jasper");
            String p_sql = "";
            Vector params = null;
            params = new Vector();
              if (nhkb_tinh != null && !"".equals(nhkb_tinh)) {
                  p_sql += " AND b.id_cha= " +nhkb_tinh;
               
              }
              if (ma_dv != null && !"".equals(ma_dv)) {
                  p_sql += " AND substr(ltt.send_bank,3,3) = '"+ma_dv+"'";
                
              }
              if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
                  p_sql += " AND b.id= "+nhkb_huyen;
                  
              }
            

            
            parameterMap.put("p_TU_NGAY", tu_ngay);
            parameterMap.put("p_DEN_NGAY", den_ngay);
            parameterMap.put("p_SQL", p_sql);
            parameterMap.put("p_MA_DV", ma_dv);
            parameterMap.put("p_LOAI_TIEN", loai_tien);
            parameterMap.put("p_TRANG_THAI", qtoan_dvi);
            
            jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
          
            String strTypePrintAction = request.getParameter(AppConstants.REQUEST_ACTION) == null ? "" :
                                                                  request.getParameter(AppConstants.REQUEST_ACTION).toString();
            String strActionName = "printCTLTTTQ.do";
            String strParameter = "";
            String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    REPORT_DIRECTORY + strFontTimeRoman;
          
            rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);        
          }
          //END Truong hop Chua quyet toan
          
          
      /*-----------------------20161111-END------------------------ */
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
}
