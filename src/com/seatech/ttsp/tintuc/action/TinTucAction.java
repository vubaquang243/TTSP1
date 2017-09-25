package com.seatech.ttsp.tintuc.action;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.tintuc.TinTucDAO;
import com.seatech.ttsp.tintuc.TinTucVO;
import com.seatech.ttsp.tintuc.form.TinTucForm;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TinTucAction extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            
            TinTucDAO newsDAO= new TinTucDAO(conn);
            TinTucVO newsVO = null;
            TinTucForm frm = (TinTucForm)form;

            Collection colCOTDi = null;
            String whereClause = "";
            String action = "";
//            Collection colDMNH = null;
            Collection lstKBTinh = null;
            Collection lstKBHuyen = null;
            String strKBHuyen = "";
            try {
                action = request.getParameter(AppConstants.REQUEST_ACTION);
              
                if (session != null) {
                    if (conn == null || conn.isClosed())
                        conn = getConnection(request);
                    TTThanhToanDAO ttttDAO = new TTThanhToanDAO(conn);
//                    colDMNH = ttttDAO.getDMucNH(null);
                    DMKBacDAO dao = new DMKBacDAO(conn);
                    // danh sach kb tinh
                    String kb_code =
                        session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
                    if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                        whereClause = " ";
                        lstKBTinh = dao.getDMucKB_cha(whereClause, null);
                        lstKBHuyen = dao.getDMucKBHuyen(whereClause, null);
                    } else {
                        String strWhere = " AND a.ma='0003' ";
                        lstKBTinh = dao.getDMucKB_cha(strWhere, null);
                        lstKBHuyen = dao.getDMucKBHuyen(whereClause, null);
                    }
                    //load KB Huyen
                    Type listType1 = new TypeToken<Collection<DMKBacVO>>() {
                    }.getType();
                    strKBHuyen = new Gson().toJson(lstKBHuyen, listType1);

                    request.setAttribute("lstKBTinh", lstKBTinh);
                    request.setAttribute("lstKBHuyen", strKBHuyen);
                    
                    String ttuc_id= frm.getId();
                        if(ttuc_id!=null&&!"".equals(ttuc_id)){
                           newsVO =  new TinTucVO();
                           String strNews= " AND id="+ttuc_id;
                          newsVO = newsDAO.getTinTuc(strNews, null);
                          request.setAttribute("noi_dung", newsVO.getNoi_dung());
                          request.setAttribute("tieu_de", newsVO.getTieu_de());
                          request.setAttribute("ngay_dang", newsVO.getNgay_dang());
                          request.setAttribute("ngay_het_han", newsVO.getNgay_het_han());
                        }
                } else {
                    return mapping.findForward("timeout");
                }
              PagingBean pagingBean = new PagingBean();
              request.setAttribute("PAGE_KEY", pagingBean);
            } catch (Exception e) {
                e.printStackTrace();
                if (request.getAttribute("getJson") != null ||
                    (action != null &&
                     (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                      action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                    response.reset();
                    response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                    PrintWriter out = response.getWriter();
                    JsonObject jsonObj = new JsonObject();
                    jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
                    out.println(jsonObj.toString());
                    out.flush();
                    out.close();

                } else {
                    throw TTSPException.createException("list()",
                                                        e.getMessage());
                }
            } finally {
                close(conn);

            }
        } catch (Exception e) {
            throw e;

        } finally {
          saveToken(request);
            close(conn);
        }
        return mapping.findForward("success");
    }


    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        String whereClause = "";
        Vector params = null;
        Collection lstKBHuyen = null;
        String strJson = "";
        try {
            conn = getConnection(request);
            String ma_kb_huyen = request.getParameter("ma_kb_huyen");
            String ma_dv = request.getParameter("ma_dv");
            if (ma_kb_huyen != null && !ma_kb_huyen.equals("")) {
                //              TSoCutOfTimeDAO dao = new TSoCutOfTimeDAO(conn);
                whereClause =
                        " a.ngay_gd=? and a.ma_nh_kb=?  and substr(a.ma_nh,3,3)=?";
                params = new Vector();
                params.add(new DateParameter(StringUtil.StringToDate(StringUtil.DateToString(new Date(),
                                                                                             "dd/MM/yyyy"),
                                                                     "dd/MM/yyyy"),
                                             true));
                params.add(new Parameter(ma_kb_huyen, true));
                params.add(new Parameter(ma_dv, true));

                DMKBacDAO dmKBDAO = new DMKBacDAO(conn);
                whereClause = " ma_cha =?";
                params = new Vector();
                params.add(new Parameter(request.getParameter("ma_kb_tinh"),
                                         true));
                lstKBHuyen = dmKBDAO.getDMNHKBList(whereClause, params);
                Type listType = new TypeToken<Collection<DMKBacVO>>() {
                }.getType();
                strJson = new Gson().toJson(lstKBHuyen, listType);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
          saveToken(request);
            close(conn);
        }
        return null;
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            TinTucDAO dao = new TinTucDAO(conn);
            TinTucVO vo = new TinTucVO();
            TinTucForm frm = (TinTucForm)form;
          if (isTokenValid(request)) {
            String type= frm.getType();
            String noi_dung = frm.getNoi_dung().replaceAll("<br />", " ");
            String tieu_de = frm.getTieu_de();
            String ngay_dang = frm.getNgay_dang();
            String ngay_het_han = frm.getNgay_het_han();
            String kb_huyen = frm.getKb_huyen();
            String nguoi_tao =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String dv_dang = "";
            
                if (frm.getMa_kb_tinh() != null &&
                    !"".equals(frm.getMa_kb_tinh())) {
                    if (kb_huyen == null ||
                        "".equals(kb_huyen)) {
                        dv_dang = frm.getMa_kb_tinh();
                    }
                    if (kb_huyen != null &&
                        !"".equals(kb_huyen)) {
                        dv_dang = kb_huyen;
                    }
                }else if (frm.getMa_kb_tinh() == null ||
                    "".equals(frm.getMa_kb_tinh())) { 
                      dv_dang = "TQ";
                    }
                vo.setTrang_thai("0");
                vo.setTieu_de(tieu_de);
                vo.setDv_dang(dv_dang);               
                vo.setNoi_dung(noi_dung);
                vo.setNgay_dang(ngay_dang);
                vo.setNgay_het_han(ngay_het_han);
                vo.setNguoi_tao(nguoi_tao);
              if("CREATE".equals(type)){
                
                dao.insertTinTuc(vo);
              }else if("UPDATE".equals(type)){
                  String id= frm.getId();
                  vo.setId(id);
                dao.updateTTuc(vo);
              }
            conn.commit();
          }
        } catch (Exception e) {
            throw e;

        } finally {
          resetToken(request);
          saveToken(request);
            close(conn);
        }
        return mapping.findForward("success");
    }
    
  public ActionForward update(ActionMapping mapping, ActionForm form,
                           HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
      Connection conn = null;
      try {
          conn = getConnection(request);
          HttpSession session = request.getSession();
        if (isTokenValid(request)) {
          TinTucDAO dao = new TinTucDAO(conn);
          TinTucVO vo = new TinTucVO();
          TinTucForm frm = (TinTucForm)form;

          String id = frm.getId();

          String nguoi_tao =
              session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
          vo.setId(id);
          vo.setTrang_thai("1");
          vo.setNguoi_tao(nguoi_tao);
            int i = dao.updateTTuc(vo);
          if(i!=0){
            conn.commit();
          }
        }
      } catch (Exception e) {
          throw e;

      } finally {
        resetToken(request);
        saveToken(request);
          close(conn);
      }
      return mapping.findForward("success");
  }
  
  
  public ActionForward search(ActionMapping mapping, ActionForm form,
                           HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
      Connection conn = null;
      try {
          conn = getConnection(request);
          HttpSession session = request.getSession();
          
          
          
          TinTucDAO dao = new TinTucDAO(conn);
          TinTucVO vo = new TinTucVO();
          TinTucForm frm = (TinTucForm)form;
          Collection colTinTuc = new ArrayList();
          
        int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
        String page = frm.getPageNumber();
        if (page == null || "".equals(page))
            page = "1";
        Integer currentPage = new Integer(page);
        Integer numberRowOnPage = phantrang;
        Integer totalCount[] = new Integer[15];
          
        String strWhere="";
          String tieu_de = frm.getTieu_de();
          String ngay_dang = frm.getNgay_dang();
          if(ngay_dang!=null && !"".equals(ngay_dang)){
             strWhere +=" AND to_char(a.ngay_dang,'DD/MM/YYYY')='"+ngay_dang+"'";
          }
          if(tieu_de!=null && !"".equals(tieu_de.trim())){
            strWhere +=" AND lower(a.tieu_de) like lower('%"+tieu_de+"%')";
          }
          colTinTuc= dao.getLstTinTuc(strWhere, null, currentPage,
                                                 numberRowOnPage, totalCount);
          if(colTinTuc.isEmpty()){
            request.setAttribute("colTinTuc", null);
          }else{
          request.setAttribute("colTinTuc", colTinTuc);
          }
        PagingBean pagingBean = new PagingBean();
        pagingBean.setCurrentPage(currentPage);
        pagingBean.setNumberOfRow(totalCount[0].intValue());
        pagingBean.setRowOnPage(15);
        request.setAttribute("PAGE_KEY", pagingBean);
      } catch (Exception e) {
          throw e;

      } finally {
        saveToken(request);
          close(conn);
      }
      return mapping.findForward("success");
  }

  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
      Vector params = null;
      JsonObject jsonObj = null;
      String strJson = "";
      Connection conn = null;
      response.setContentType(AppConstants.CONTENT_TYPE_JSON);
      PrintWriter out = response.getWriter();
      try {
              jsonObj = new JsonObject();
//              jsonObj.addProperty(AppConstants.ERROR, AppConstants.FAILURE);
//          if (jsonObj == null) {
              conn = getConnection(request);
              String action =
                  request.getParameter(AppConstants.REQUEST_ACTION);
              if (action != null &&
                  AppConstants.ACTION_FILL_DATA_TO_FORM.equalsIgnoreCase(action)) {
                  String id = request.getParameter("id");
                  TinTucDAO newDAO = new TinTucDAO(conn);
                  String whereClause = " AND a.id=? ";
                  params = new Vector();
                  params.add(new Parameter(id, true));
                  TinTucVO newVO = newDAO.getTinTuc(whereClause, params);
                  if (newVO != null) {
                      Type listType = new TypeToken<TinTucVO>() {
                      }.getType();
                      strJson = new Gson().toJson(newVO, listType);
                  }
              }
//          }
      } catch (Exception e) {
          e.printStackTrace();
          response.setContentType(AppConstants.CONTENT_TYPE_JSON);
          jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
      } finally {
          if (out != null) {
              out.println(strJson.toString());
              out.flush();
              out.close();
          }
          close(conn);
      }
      if (!response.isCommitted())
          return mapping.findForward(AppConstants.SUCCESS);
      else
          return null;
  }

}
