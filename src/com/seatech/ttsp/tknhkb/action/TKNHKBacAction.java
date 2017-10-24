package com.seatech.ttsp.tknhkb.action;


import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;
import com.seatech.ttsp.tknhkb.form.TKNHKBacForm;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TKNHKBacAction extends AppAction {
    private final String REQUEST_ACTION = "action";

    public TKNHKBacAction() {
        super();
    }

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	 
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

             DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
             TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
             
             List dmucNH = (List)TTdao.getDMucNH(null,null);
             List tienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ", null);
             request.setAttribute("dmucNH", dmucNH);
             request.setAttribute("tienTe", tienTe);

             String strCap = " and ma=" + kb_code;
             vo = dao.getCap(strCap, null);
             List dmuckb_cha = null;
             String cap = vo.getCap();
			 
			 
			 /** 
			  * SEA: sua load form tra cuu 20170208
		 	  *	DChieu1DAO
			  * Thay doi ham su dung getDMucKB_cha  => getDMucKB_Tinh
			  */
			  /**************************START************************/
			 
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
                 strWhere += " and c.id in (select id_cha from sp_dm_htkb where ma=" + kb_code + ")";
                 dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
                 request.setAttribute("dmuckb_tinh", dmuckb_cha);
             }
			 /***********************END************************/
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
        TKNHKBacForm frm = (TKNHKBacForm)form;
        HttpSession session = request.getSession();
        conn = getConnection();
        TKNHKBacDAO tkdao = new TKNHKBacDAO(conn);
        Collection colLstSoTK = null;
          
          
        int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
        String page = frm.getPageNumber();
        if (page == null || "".equals(page))
            page = "1";
        Integer currentPage = new Integer(page);
        Integer numberRowOnPage = phantrang;
        Integer totalCount[] = new Integer[15];
          
        DChieu1DAO dao = new DChieu1DAO(conn);
        
        DChieu1VO vo = new DChieu1VO();

        String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
        String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
        
        DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
        TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
        List dmucNH = (List)TTdao.getDMucNH(null,null);
        List tienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ", null);
        request.setAttribute("dmucNH", dmucNH);
        request.setAttribute("tienTe", tienTe);

        String strCap = " and ma=" + kb_code;
        vo = dao.getCap(strCap, null);
        List dmuckb_cha = null;
        String cap = vo.getCap();
        if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
            String strWhere = " ";
            dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
            request.setAttribute("dftinh", "dftinh");
            request.setAttribute("TTTT", "TTTT");
        } else if ("0003".equals(kb_code)) {
            String strWhere = " AND a.ma='0003' ";
            dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
        } else if ("5".equals(cap)) {
            String strWhere = "";
            strWhere += " and c.ma=" + kb_code;
            dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);

            request.setAttribute("dmuckb_tinh", dmuckb_cha);
        } else {
            String strWhere = "";
            strWhere += " and c.id in (select id_cha from sp_dm_htkb where ma=" + kb_code + ")";
            dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
        }
          
        String nhkb_tinh = frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
        String nhkb_huyen = frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
        String ma_dv = frm.getMa_dv() == null ? "" : frm.getMa_dv();
        String so_tk = frm.getSo_tk() == null ? "" : frm.getSo_tk();
        String loai_tk = frm.getLoai_tk() == null ? "" : frm.getLoai_tk();
        String loai_gd = frm.getLoai_gd() == null ? "" : frm.getLoai_gd();
        String quyet_toan = frm.getQuyet_toan() == null ? "" : frm.getQuyet_toan();
        
        String strLst = "";
        
        if (nhkb_tinh != null && !"".equals(nhkb_tinh)) {
            strLst += " AND (c.id_cha=" + nhkb_tinh + " or c.id = "+ nhkb_tinh +")";
        }
        if (ma_dv != null && !"".equals(ma_dv)) {
            strLst += " and substr(d.ma_nh,3,3) = '" + ma_dv + "'";
        }
        if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
            strLst += " AND c.id=" + nhkb_huyen;
        }
        if (so_tk != null && !"".equals(so_tk)) {
            strLst += " AND a.so_tk= '" + so_tk + "'";
        }
        if (loai_tk != null && !"".equals(loai_tk)) {
            strLst += " AND a.loai_tk= '" + loai_tk + "'";
        }
        if (loai_gd != null && !"".equals(loai_gd)) {
            strLst += " AND a.loai_gd= '" + loai_gd +"'";
        }
        if (quyet_toan != null && !"".equals(quyet_toan)) {
            strLst += " AND a.quyet_toan= '" + quyet_toan + "'";
        }
        if (frm.getMa_nt() != null && !"".equals(frm.getMa_nt())) {
            strLst += " AND a.ma_nt = '"+frm.getMa_nt()+"'";
        }
        if (frm.getTrang_thai() != null && !"".equals(frm.getTrang_thai())) {
            strLst += " AND a.trang_thai = '"+frm.getTrang_thai()+"'";
        }
        if(frm.getHieu_luc_tungay() != null && !"".equals(frm.getHieu_luc_tungay()) && frm.getHieu_luc_den_ngay() != null && !"".equals(frm.getHieu_luc_den_ngay())){
          strLst += " AND (a.hieu_luc_tungay, NVL(a.hieu_luc_den_ngay,SYSDATE+365)) overlaps (to_date('"+frm.getHieu_luc_tungay()+"','dd/mm/yyyy'),to_date('"+frm.getHieu_luc_den_ngay()+"','dd/mm/yyyy'))";
        }else if(frm.getHieu_luc_tungay() != null && !"".equals(frm.getHieu_luc_tungay())){
          strLst += " AND a.hieu_luc_tungay <= to_date('"+frm.getHieu_luc_tungay()+"','dd/mm/yyyy') AND NVL(a.hieu_luc_den_ngay,SYSDATE+365) >  to_date('"+frm.getHieu_luc_tungay()+"','dd/mm/yyyy')";
        }else if(frm.getHieu_luc_den_ngay() != null && !"".equals(frm.getHieu_luc_den_ngay())){
          strLst += " AND (a.hieu_luc_tungay > to_date('"+frm.getHieu_luc_den_ngay()+"','dd/mm/yyyy') OR NVL(a.hieu_luc_den_ngay,SYSDATE+365) <  to_date('"+frm.getHieu_luc_den_ngay()+"','dd/mm/yyyy'))";
        }
        
        colLstSoTK = tkdao.getLstTK(strLst, null, currentPage, numberRowOnPage, totalCount);
        
        request.setAttribute("colLstSoTK", colLstSoTK);
        PagingBean pagingBean = new PagingBean();
        pagingBean.setCurrentPage(currentPage);
        pagingBean.setNumberOfRow(totalCount[0].intValue());
        pagingBean.setRowOnPage(15);
        request.setAttribute("PAGE_KEY", pagingBean);
        
      } catch (Exception e) {
          throw e;
      } finally {
          conn.close();
      }
      return mapping.findForward(AppConstants.SUCCESS);
  }


    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        if (!checkPermissionOnFunction(request, "QLY_TK.TK_NHKB")) {
            return mapping.findForward("errorQuyen");
        }
        String strReturn = "";
        try {

            TKNHKBacForm tknhkbForm = (TKNHKBacForm)form;
            if (conn == null || conn.isClosed())
                conn = getConnection();
            TKNHKBacVO tknhkbVO = new TKNHKBacVO();
            TKNHKBacDAO tknhkbDAO = new TKNHKBacDAO(conn);
            BeanUtils.copyProperties(tknhkbVO, tknhkbForm);
            if (tknhkbDAO.checkExist(tknhkbVO.getNh_id().toString(), tknhkbVO.getSo_tk().toString().trim()) == true) {
                strReturn = "-1";
                request.setAttribute("EXIST", "KB");
            } else {
                strReturn = tknhkbDAO.insert(tknhkbVO);
                conn.commit();
                if (strReturn != null && !"-1".equalsIgnoreCase(strReturn.trim())) {
                    list(mapping, form, request, response);
                } else {
                    jsonObj.addProperty("error", "TTSP-ERROR-0001");
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            conn.close();
        }
        if (strReturn != null && !"-1".equalsIgnoreCase(strReturn.trim()) &&
            !"".equalsIgnoreCase(strReturn.trim())) {
            return mapping.findForward(AppConstants.SUCCESS);
        } else
            return mapping.findForward(AppConstants.FAILURE);
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        TKNHKBacVO TKNHKBacVO = null;
        int i = 0;
        Connection conn = null;
        if (!checkPermissionOnFunction(request, "QLY_TK.TK_NHKB")) {
            return mapping.findForward("errorQuyen");
        }
        try {
            TKNHKBacForm TKNHKBacForm = (TKNHKBacForm)form;
            HttpSession session = request.getSession();
            if (session == null)
                return mapping.findForward(AppConstants.FAILURE);
            if (conn == null || conn.isClosed())
                conn = getConnection();
            TKNHKBacDAO TKNHKBacDAO = new TKNHKBacDAO(conn);
            //update
            TKNHKBacVO = new TKNHKBacVO();
            TKNHKBacVO.setId(new Long(TKNHKBacForm.getId_tk()));
            BeanUtils.copyProperties(TKNHKBacVO, TKNHKBacForm);
            i = TKNHKBacDAO.update(TKNHKBacVO);

            // saveVisitLog(conn,"", "QLY_TK.TK_NHKB", request.getRemoteAddr(),"");            
            if (i > 0) {
              conn.commit();
              request.setAttribute("message", "S?a thành công");
                list(mapping, form, request, response);
            }

        } catch (Exception e) {
            request.setAttribute("message", "S?a kh�ng thành công");
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        if (!checkPermissionOnFunction(request, "QLY_TK.TK_NHKB")) {
            return mapping.findForward("errorQuyen");
        }
        try {
            HttpSession session = request.getSession();
            if (session == null)
                return mapping.findForward(AppConstants.FAILURE);
            if (conn == null || conn.isClosed())
                conn = getConnection();
            TKNHKBacDAO tknhkb = new TKNHKBacDAO(conn);
            TKNHKBacForm frm = (TKNHKBacForm)form;
            TKNHKBacVO tknhkbvo = new TKNHKBacVO();
            Long id= Long.parseLong(frm.getId());

           tknhkbvo.setId(id);
           tknhkb.deletetTKNHKB(tknhkbvo);

            conn.commit();
            list(mapping, form, request, response);
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            jsonObj.addProperty("error", e.getMessage());
            out.println(jsonObj.toString());
            out.flush();
            out.close();
            return mapping.findForward(AppConstants.FAILURE);
        } finally {
            conn.close();
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        Vector params = null;
        Collection listDMKB = null;
        Collection listTKNHKB = null;
        if (!checkPermissionOnFunction(request, "QLY_TK.TK_NHKB")) {
            return mapping.findForward("errorQuyen");
        }
        try {
            conn = getConnection();
            listDMKB = new ArrayList();
            String strWhere = "";
            String action = request.getParameter(REQUEST_ACTION);
            TKNHKBacForm tknhkbForm = (TKNHKBacForm)form;

            String shkb = request.getParameter("shkb");
            
            if (shkb != null && !"".equals(shkb)) {
                strWhere = " ma= ?";
                params = new Vector();
                params.add(new Parameter(shkb, true));
            }
            DMKBacDAO dmkb = new DMKBacDAO(conn);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            List tienTe = (List)tienTeDAO.getDMTienTeList(" a.tinh_trang = 1 ", null);
            request.setAttribute("tienTe", tienTe);
            listDMKB = dmkb.getDMKBList(strWhere, params);
            DMKBacVO dmkbvo = null;
            if (listDMKB != null) {
                Iterator iter = listDMKB.iterator();
                int i = 0;
                while (iter.hasNext()) {
                    if (i > 0)
                        break;
                    dmkbvo = (DMKBacVO)iter.next();
                    i++;
                }
            }
            if (action != null && action.equalsIgnoreCase("ADD")) {
                tknhkbForm.setMa_kb(dmkbvo.getMa());
                tknhkbForm.setTen_kb(dmkbvo.getTen());
                tknhkbForm.setKb_id(dmkbvo.getId().toString());
            } else {
                TKNHKBacDAO tknhkb = new TKNHKBacDAO(conn);
                TKNHKBacVO tknhkbvo = new TKNHKBacVO();
                String id_tk = tknhkbForm.getId_tk();
                if (shkb != null) {                  
                    strWhere = " and c.ma=? and a.id= "+id_tk;               
                    params = new Vector();
                    params.add(new Parameter(shkb, true));
                    listTKNHKB = tknhkb.getTK_NH_KB(strWhere, params);
                    if (listTKNHKB != null) {
                        Iterator iter = listTKNHKB.iterator();
                        int j = 0;
                        while (iter.hasNext()) {
                            if (j > 0)
                                break;
                            tknhkbvo = (TKNHKBacVO)iter.next();
                            j++;
                        }
                        BeanUtils.copyProperties(tknhkbForm, tknhkbvo);
                        tknhkbForm.setMa_kb(dmkbvo.getMa());
                        tknhkbForm.setTen_kb(dmkbvo.getTen());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.flush();
            out.close();
        } finally {
            conn.close();
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Connection conn = null;
        InputStream reportStream = null;
        String strParameter = "";
        if (!checkPermissionOnFunction(request, "QLY_TK.TK_NHKB")) {
            return mapping.findForward("errorQuyen");
        }
        try {

            if (conn == null || conn.isClosed())
                conn = getConnection();
            HttpSession session = request.getSession();
            saveVisitLog(conn, session, "QLY_TK.TK_NHKB", "");

            DMKBacDAO dmkb = new DMKBacDAO(conn);
            Vector params = new Vector();
            String strKBID = "";
            if (request.getParameter("id") != null) {
                strKBID = request.getParameter("id").toString();
                strParameter += "id=" + strKBID;
                params.add(new Parameter(strKBID, true));
            }

            DMKBacVO dmkbvo = dmkb.getDMKB(" AND a.id = ? ", params);
            String strTenKB = dmkbvo.getTen();

            String reportName = "DSachTKNHKB";

            JasperPrint jasperPrint = null;
            Map parameterMap = null;

            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                            "/" +
                                                                                            reportName +
                                                                                            ".jasper");

            parameterMap = new HashMap();
            parameterMap.put("P_KB_ID", strKBID);
            parameterMap.put("P_TEN_KB", strTenKB);

            //De cau lenh SQL trong file .jasper
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 conn);

            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;

            String strTypePrintAction =
                request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                "" :
                request.getParameter(AppConstants.REQUEST_ACTION).toString();
            String strActionName = "inTKNHKB.do";

            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    reportName, strPathFont, strActionName, "",
                                    strParameter);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn);
            try {
                reportStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }
}
