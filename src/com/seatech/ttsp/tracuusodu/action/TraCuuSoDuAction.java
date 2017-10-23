package com.seatech.ttsp.tracuusodu.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.tracuusodu.TraCuuSoDuDAO;
import com.seatech.ttsp.tracuusodu.TraCuuSoDuVO;
import com.seatech.ttsp.tracuusodu.form.TraCuuSoDuForm;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * 13/10/2017
 * Tra cứu số dư
 *
 */
public class TraCuuSoDuAction extends AppAction {

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "SYS.TK.TCSD")) {
           return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        Collection lstTimKiem = null;
        Collection lstNHKBTinh = null;
        Vector vParams;
        try {
            conn = getConnection();
            vParams = new Vector();

            TraCuuSoDuDAO dao = new TraCuuSoDuDAO(conn);
            TraCuuSoDuForm traCuuForm = (TraCuuSoDuForm)form;
            String strWhere = "";
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            lstNHKBTinh = dao.getNHKBTinh("", null);
            String page = traCuuForm.getPageNumber();
            if (page == null || page.equals(""))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            String id_kho_bac_tinh = request.getParameter("id_kho_bac_tinh");
            if (id_kho_bac_tinh != null && id_kho_bac_tinh != "") {
                if (!traCuuForm.getId_kho_bac_tinh().equals("")) {
                    strWhere +=
                            "AND (c.ma = '" + traCuuForm.getId_kho_bac_tinh() +
                            "' or c.ma_cha = '" +
                            traCuuForm.getId_kho_bac_tinh() + "') ";
                }
                if (!traCuuForm.getId_kho_bac_huyen().equals("") && (!traCuuForm.getId_kho_bac_huyen().equals("0000"))) {
                    strWhere +=
                            "AND c.ma = '" + traCuuForm.getId_kho_bac_huyen() +
                            "' ";
                }
                if (!traCuuForm.getId_ngan_hang().equals("")) {
                    strWhere +=
                            "AND b.ma_nh = '" + traCuuForm.getId_ngan_hang() +
                            "' ";
                }
                if(!traCuuForm.getHan_muc().equals("")){
                    if(traCuuForm.getTinh_trang_so_du().equals("01")){
                        strWhere += "AND a.han_muc_no > " + traCuuForm.getHan_muc() + " ";
                    }
                  if(traCuuForm.getTinh_trang_so_du().equals("02")){
                      strWhere += "AND a.han_muc_no = " + traCuuForm.getHan_muc() + " ";
                  }
                  if(traCuuForm.getTinh_trang_so_du().equals("03")){
                      strWhere += "AND a.han_muc_no < " + traCuuForm.getHan_muc() + " ";
                  }
                }
                if (!traCuuForm.getLoai_tai_khoan().equals("")) {
                    strWhere +=
                            " AND a.loai_tk = '" + traCuuForm.getLoai_tai_khoan() +
                            "' ";
                }
                if (!traCuuForm.getNgay_gd().equals("")) {
                    strWhere +=
                            " AND d.ngay_gd = TO_DATE('" + traCuuForm.getNgay_gd() +
                            "','dd/mm/yyyy') ";
                }
                lstTimKiem =
                        dao.traCuuSoDuList(strWhere, vParams, currentPage, numberRowOnPage,
                                           totalCount);
                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);
            }
            request.setAttribute("lstTimKiem", lstTimKiem);
            request.setAttribute("lstNHKBTinh", lstNHKBTinh);
        } catch (Exception e) {
            //throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    /*
   *
   * get thong tin kho bac huyen
   *
   */

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        Vector vParams = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        try {
            vParams = new Vector();
            conn = getConnection();
            TraCuuSoDuDAO dao = new TraCuuSoDuDAO(conn);
            String kb_id = request.getParameter("kb_id");
            String strWhere = " AND ma_cha = '" + kb_id + "' ";
            Collection lstNHKBHuyen = dao.getNHKBHuyen(strWhere, null);
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(lstNHKBHuyen);
            jsonObj.addProperty("lstNHKBHuyen", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("lstNHKBHuyen");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    /*
   *
   *
   *get list ngan hang
   *
   */

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        Vector vParams = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        try {
            vParams = new Vector();
            conn = getConnection();
            TraCuuSoDuDAO dao = new TraCuuSoDuDAO(conn);
            String kb_id = request.getParameter("kb_id_huyen");
            String strWhere1 = " AND c.ma = '" + kb_id + "' ";
            Collection lstNganHang = dao.getdmNganHang(strWhere1, null);
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(lstNganHang);
            jsonObj.addProperty("lstNganHang", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("lstNganHang");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    /*
     *
     *get loai tien
     *
     */

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        Vector vParams = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        try {
            conn = getConnection();
            TraCuuSoDuDAO dao = new TraCuuSoDuDAO(conn);
            String nganhang_id = request.getParameter("nganhang_id");
            String strWhere = "AND b.ma_nh = '" + nganhang_id + "' ";
            Collection lstLoaiTien = dao.getLoaiTien(strWhere, null);
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(lstLoaiTien);
            jsonObj.addProperty("lstLoaiTien", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("lstLoaiTien");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "SYS.TK.TCSD")) {
           return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        Vector vParams = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        String result = "";
        try {
            conn = getConnection();
            vParams = new Vector();
            TraCuuSoDuDAO dao = new TraCuuSoDuDAO(conn);
            String ma_nh = request.getParameter("ma_nh");
            String ma_kb = request.getParameter("ma_kb");
            String ngay_gd = request.getParameter("ngay_gd");
            String loai_tien = request.getParameter("loai_tien");
            String strWhere =
                "ma_kb = '" + ma_kb + "' AND ma_nh = '" + ma_nh + "' AND ngay_gd = TO_DATE('" +
                ngay_gd + "','dd/mm/yyyy') AND loai_tien = '" + loai_tien +
                "'";
            int kq = dao.deleteRecord(strWhere, vParams);
            if (kq > 0) {
                result = "success";
            } else
                result = "fail";
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(result);
            jsonObj.addProperty("result", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("result");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
}
