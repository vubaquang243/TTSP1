package com.seatech.ttsp.bketinhlai.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.bketinhlai.BKeTinhLaiDAO;
import com.seatech.ttsp.bketinhlai.BKeTinhLaiVO;
import com.seatech.ttsp.bketinhlai.form.BKeTinhLaiForm;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangHOVO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanVO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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


public class BKeTinhLaiAction extends AppAction {
    private static final String VIEW_TK_ONLY_VND = "ONLY_VND";
    
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
//            String kb_id =
//                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();


            DChieu1DAO dao = new DChieu1DAO(conn);
            DChieu1VO vo = new DChieu1VO();

            List dmucNH = null;
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            dmucNH = (List)TTdao.getDMucNH(null,null);
            request.setAttribute("dmucNH", dmucNH);

            String strCap = " and ma=" + kb_code;
            vo = dao.getCap(strCap, null);
            String cap = vo.getCap();

			//ThuongDT-sua chi load DB tỉnh-20/10/2016-begin
            loadDMTinh(kb_code,cap, conn, request);
            
			//ThuongDT-sua chi load DB tỉnh-20/10/2016-end
            PagingBean pagingBean = new PagingBean();
            request.setAttribute("PAGE_KEY", pagingBean);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
//            String kb_id =
//                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();


            DChieu1DAO dao = new DChieu1DAO(conn);
            BKeTinhLaiDAO bkDAO = new BKeTinhLaiDAO(conn);
            BKeTinhLaiVO bkVO = new BKeTinhLaiVO();
            BKeTinhLaiForm frm = (BKeTinhLaiForm)form;
            DChieu1VO vo = new DChieu1VO();

            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            String page = frm.getPageNumber();
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];

            List dmucNH = null;
            Collection colBKTLai = null;

            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            dmucNH = (List)TTdao.getDMucNH(null,null);
            request.setAttribute("dmucNH", dmucNH);

            String strBke = "";
            String strCap = " and ma=" + kb_code;
            vo = dao.getCap(strCap, null);
            String cap = vo.getCap();
             //20170926 thuongdt turning lai code cho gon ko phai sua nhieu cho

             loadDMTinh(kb_code,cap, conn, request);
            

            String so_tk = frm.getSo_tk() == null ? "" : frm.getSo_tk();
            String tu_ngay = frm.getTu_ngay() == null ? "" : frm.getTu_ngay();
            String den_ngay =
                frm.getDen_ngay() == null ? "" : frm.getDen_ngay();
            String nhkb_huyen =
                frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
            String ma_nh =
                frm.getNgan_hang() == null ? "" : frm.getNgan_hang();

            String idxKB = frm.getIdxKB() == null ? "" : frm.getIdxKB();
            String idxNH = frm.getIdxNH() == null ? "" : frm.getIdxNH();
            String idxTK = frm.getIdxTK() == null ? "" : frm.getIdxTK();

            String char_dngay = null;
            String char_tngay = null;
            if (ma_nh != null && !"".equals(ma_nh)) {
                strBke += " AND a.send_bank='" + ma_nh + "'";
            }
            if (so_tk != null && !"".equals(so_tk)) {
                strBke += " AND a.so_tk='" + so_tk + "'";
            }
            if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
			//20170926 thuongdt bo sung tra cuu SGD va cuc KTNN
                if(nhkb_huyen.equals("2") || nhkb_huyen.equals("3"))
                  strBke += " AND (c.id= '2' or c.id= '3')" ;
                else
                strBke += " AND c.id=" + nhkb_huyen;
            }
            if (den_ngay != null && !"".equals(den_ngay)) {
                char_dngay =
                        StringUtil.DateToString(StringUtil.StringToDate(den_ngay,
                                                                        "dd/MM/yyyy"),
                                                "yyyy/MM/dd").replace("/", "");
            }
            if (tu_ngay != null && !"".equals(tu_ngay)) {
                char_tngay =
                        StringUtil.DateToString(StringUtil.StringToDate(tu_ngay,
                                                                        "dd/MM/yyyy"),
                                                "yyyy/MM/dd").replace("/", "");
            }

            if (tu_ngay != null && !"".equals(tu_ngay) && den_ngay != null &&
                !"".equals(den_ngay)) {
                strBke +=
                        " and (to_char(a.ngay,'YYYYMMDD') >=  '" + char_tngay +
                        "' and to_char(a.ngay,'YYYYMMDD') <=  '" + char_dngay +
                        "') ";
            }
            if ((den_ngay == null || "".equals(den_ngay)) && tu_ngay != null &&
                !"".equals(tu_ngay)) {
                strBke +=
                        " and (to_char(a.ngay,'YYYYMMDD') <=  to_char(sysdate,'YYYYMMDD') and to_char(a.ngay,'YYYYMMDD') >=  '" +
                        char_tngay + "') ";
            } else if (den_ngay != null && !"".equals(den_ngay) &&
                       (tu_ngay == null || "".equals(tu_ngay))) {
                strBke +=
                        " and to_char(a.ngay,'YYYYMMDD') <= '" + char_dngay + "'";
            }

            colBKTLai =
                    bkDAO.getListBKeTinhLai_PTrang(strBke, null, currentPage,
                                                   numberRowOnPage,
                                                   totalCount);
            request.setAttribute("colBKTLai", colBKTLai);
            bkVO = bkDAO.getSumBKeLai(strBke, null);
            if (colBKTLai.size() > 0) {
                String sum_sdu = bkVO.getSum_sdu();
                String sum_lai = bkVO.getSum_lai();
                request.setAttribute("sum_sdu", sum_sdu);
                request.setAttribute("sum_lai", sum_lai);
            }

            request.setAttribute("idxKB", idxKB);
            request.setAttribute("idxNH", idxNH);
            request.setAttribute("idxTK", idxTK);

            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(15);
            request.setAttribute("PAGE_KEY", pagingBean);

        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
     //20170926 thuongdt turning lai code cho gon ko phai sua nhieu cho
    public void loadDMTinh(String kb_code,String cap, Connection conn, HttpServletRequest request) throws Exception {
              DChieu1DAO dao = new DChieu1DAO(conn);
              List dmuckb_cha = null;  
              String strWhere ="";
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) { 
                strWhere = " and cap = '5' or  ma='0002' or ma='0003'"; //AND cap=5 OR ma='0003'
                request.setAttribute("QTHTTW", "QTHTTW");
            } else if ("0003".equals(kb_code)) {
                strWhere = " AND ma='0003' ";               
            } else if ("0002".equals(kb_code)) {
                strWhere = " AND ma='0002' ";               
            }else if ("5".equals(cap)) {
                strWhere = "";
                strWhere += " and cap =" + kb_code;
            } else {
                strWhere = "";
                strWhere +=
                        " and id in (select id_cha from sp_dm_htkb where ma=" +
                        kb_code + ")";
            }
          dmuckb_cha = (List)dao.getDMucKB_Tinh2(strWhere, null);
          request.setAttribute("dmuckb_tinh", dmuckb_cha);        
    }
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
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
            TTThanhToanVO vo = new TTThanhToanVO();
            String strCap = " and ma=" + kb_code;
            vo = ttdao.getCap(strCap, null);
            String strNH = " and b.id=" + strMaKB;
            colNH = ttdao.getListNH(strNH, null);

            java.lang.reflect.Type listNH =
                new TypeToken<Collection<TTThanhToanVO>>() {
            }.getType();
            strJSON = new Gson().toJson(colNH, listNH);

            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJSON.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            JSONObject jsonRes = new JSONObject();
            jsonRes.put("executeError",
                        FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));

            response.setHeader("X-JSON", jsonRes.toString());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

/* AJAX load listTK
 * @input1 : id kho bac   - requestParam : nhkb_id
 * @input2 : Ma 7 so ngan hang - requestParam : nh_id
 * @option1 : Chi xem tai khoan VND - requestParam : typeDisplay = VIEW_TK_ONLY_VND
 * @option2 : Chi lay 1 kho bac- requestParam : soTk
 * @output : json listTK day du.
 * */
    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection();
            TTThanhToanDAO ttdao = new TTThanhToanDAO(conn);
//            HttpSession session = request.getSession();
            
            String maNH = request.getParameter("nh_id");
            String soTk = request.getParameter("soTk");
            
            String condition = "";
            if(soTk != null){//Theo tai khoan chi tiet
//                String shkb = request.getParameter("shkb");
                condition = " and c.ma_nh ='" + maNH + "' and a.so_tk= '"+soTk+"'";
            }else{//Nhieu thang 
                String maKB = request.getParameter("nhkb_id");
				//20170926 thuongdt bo sung tra cuu SGD va cuc KTNN
                if("2".equals(maKB)||"3".equals(maKB))
                condition = " and ( b.id='2' or  b.id='3')  and c.ma_nh ='" + maNH + "'";
                else
                condition = " and b.id=" + maKB + " and c.ma_nh ='" + maNH + "'";
            }
            
            String typeDisplay = request.getParameter("typeDisplay");
            if(typeDisplay != null){
              condition += typeDisplay.equals(VIEW_TK_ONLY_VND) ? " AND a.ma_nt = 'VND' " : ""; 
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
    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
        public static final String fileName = "/rpt_Bke_Tlai";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection();
            BKeTinhLaiForm frm = (BKeTinhLaiForm)form;
            BKeTinhLaiVO bkVO = new BKeTinhLaiVO();
            BKeTinhLaiDAO bkDAO = new BKeTinhLaiDAO(conn);
            HttpSession session = request.getSession();
            TKNHKBacDAO tknhkbDAO = new TKNHKBacDAO(conn);
            
       
            String strBke = "";

            //          String ma_nh= frm.getNgan_hang();
            String ma_nh = frm.getNgan_hang() == null ? "" : frm.getNgan_hang();
            String so_tk = frm.getSo_tk() == null ? "" : frm.getSo_tk();
            String tu_ngay = frm.getTu_ngay() == null ? "" : frm.getTu_ngay();
            String den_ngay = frm.getDen_ngay() == null ? "" : frm.getDen_ngay();
            if (den_ngay==null || "".equals(den_ngay)){
                 den_ngay = StringUtil.DateToString(new Date(), AppConstants.DATE_FORMAT_SEND_ORDER);
            }
            String nhkb_huyen = frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
 
            if (ma_nh != null && !"".equals(ma_nh)) {
                strBke += " AND a.send_bank='" + ma_nh + "'";
            }
            if (so_tk != null && !"".equals(so_tk)) {
                strBke += " AND a.so_tk='" + so_tk + "'";
            }
            if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
                strBke += " AND c.id=" + nhkb_huyen;
            } 

          String ten_kb = "";
          String ten_nh = "";
          String ma_kb= "";
          String loai_tk= "";

            bkVO = bkDAO.getTSoBCao(strBke, null);
 
             ten_kb = bkVO.getTen_kb();
             ten_nh = bkVO.getTen_nh();
             ma_kb= bkVO.getReveive_bank();
             loai_tk= bkVO.getLoai_tk();
 
            
          Vector vtParam = new Vector(); 
          vtParam.add(new Parameter(ma_nh.substring(2, 5), true));
          DMNHangDAO dmdao = new DMNHangDAO(conn);
          DMNHangHOVO dmvo = dmdao.getDMNHangHO(" and a.ma_dv = ? ", vtParam);
          vtParam.clear();
          vtParam.add(new Parameter(ma_nh,true)); vtParam.add(new Parameter(so_tk,true));
          TKNHKBacVO tknhkbVO = tknhkbDAO.getTK_NH_KB_VO(" and b.ma_nh= ? and a.so_tk = ? ", vtParam);
            
          String strLaiSuat = getThamSoHThong("LAI_SUAT_"+dmvo.getBi_danh(), session);
          String strLoaiLS = getThamSoHThong("LOAI_LAI_SUAT_"+dmvo.getBi_danh(), session);

            sbSubHTML.append("<input type=\"hidden\" name=\"ngan_hang\" value=\"" +
                             ma_nh + "\" id=\"ngan_hang\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"nhkb_huyen\" value=\"" +
                             nhkb_huyen + "\" id=\"nhkb_huyen\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"so_tk\" value=\"" +
                             so_tk + "\" id=\"so_tk\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                             tu_ngay + "\" id=\"tu_ngay\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay\" value=\"" +
                             den_ngay + "\" id=\"den_ngay\"></input>");


            JasperPrint jasperPrint = null;
            HashMap parameterMap = new HashMap();
            ReportUtility rpUtilites = new ReportUtility();

            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                            fileName +
                                                                                            ".jasper");
                      parameterMap.put("p_TU_NGAY", tu_ngay.replace("/", "-"));
                      parameterMap.put("p_DEN_NGAY", den_ngay.replace("/", "-"));
                      parameterMap.put("p_SO_TK", so_tk);
                      parameterMap.put("p_MA_KB", ma_kb);
                      parameterMap.put("p_TEN_KB", ten_kb);
                      parameterMap.put("p_MA_NH", ma_nh);
                      parameterMap.put("p_TEN_NH", ten_nh);
                      parameterMap.put("p_TEN_TK", loai_tk);
                      parameterMap.put("p_laisuat", strLaiSuat);
                      parameterMap.put("p_SO_NGAY", strLoaiLS);
            if (tknhkbVO.getMa_nt().equals("VND")){
                parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
            }else{
                parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
            }
            jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);

            String strTypePrintAction =
                request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                "" :
                request.getParameter(AppConstants.REQUEST_ACTION).toString();
            String strActionName = "printBKeTinhLai.do";
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
}
