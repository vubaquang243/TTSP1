package com.seatech.ttsp.doichieu.action;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.doichieu.DCNoiBoVO;
import com.seatech.ttsp.doichieu.DoiChieuDAO;
import com.seatech.ttsp.doichieu.StatisticsDCNoiBoVO;
import com.seatech.ttsp.doichieu.form.DCNoiBoForm;
import com.seatech.ttsp.quyettoan.QuyetToanDAO;
import com.seatech.ttsp.quyettoan.TThaiVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DCNoiBoAction extends AppAction {
    public DCNoiBoAction() {
        super();
    }
    private static String STRING_EMPTY = "";

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "DCHIEU.NBO")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        DoiChieuDAO dcDAO = null;
        JsonObject jsonObj = new JsonObject();
        DCNoiBoVO dcVO = new DCNoiBoVO();
        String strJson = null;
        try {
            conn = getConnection();
            dcDAO = new DoiChieuDAO(conn);
            jsonObj = new JsonObject();
            String strID = request.getParameter("id");
            String strTTDM = request.getParameter("ttDM");
            String strLyDo = request.getParameter("ly_do_dong_update");

            dcVO.setId(strID);
            dcVO.setTrang_thai_dong_mo(strTTDM);
            dcVO.setLy_do_dong(strLyDo);
            if (dcDAO.update(dcVO) > 0) {
                jsonObj.addProperty("update", AppConstants.SUCCESS);
            } else {
                jsonObj.addProperty("update", AppConstants.FAILURE);
            }
            conn.commit();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
            throw e;
        } finally {
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            if (out != null) {
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

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "DCHIEU.NBO")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        ArrayList<DCNoiBoVO> lstDCNoiBo = null;
        DoiChieuDAO dcDAO = null;
        String whereClause = null;
        Vector params = null;
        try {
            // lay danh sach ngan hang
            // lay danh sach kho bac
            // lay danh sach trang thai
            conn = getConnection();
            DCNoiBoForm f = (DCNoiBoForm)form;
            dcDAO = new DoiChieuDAO(conn);
            params = new Vector();
            if (f.getNgay_thuc_hien() != null &&
                !STRING_EMPTY.equals(f.getNgay_thuc_hien())) {
                if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                    whereClause +=
                            " and trunc(a.ngay_thuc_hien) = to_date(?,'DD/MM/YYYY')";
                } else {
                    whereClause =
                            " trunc(a.ngay_thuc_hien) = to_date(?,'DD/MM/YYYY')";
                }
                params.add(new Parameter(f.getNgay_thuc_hien(), true));
            }
            if (f.getTrang_thai() != null &&
                !STRING_EMPTY.equals(f.getTrang_thai())) {
                if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                    whereClause += " and a.trang_thai = ?";
                } else {
                    whereClause = " a.trang_thai = ?";
                }
                params.add(new Parameter(f.getTrang_thai(), true));
            }
            if (f.getNgan_hang() != null &&
                !STRING_EMPTY.equals(f.getNgan_hang())) {
                if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                    whereClause += " and a.receive_bank = ?";
                } else {
                    whereClause = " a.receive_bank = ?";
                }
                params.add(new Parameter(f.getNgan_hang(), true));
            }
            if (f.getKho_bac() != null &&
                !STRING_EMPTY.equals(f.getKho_bac())) {
                if (whereClause != null && !STRING_EMPTY.equals(whereClause)) {
                    whereClause += " and f.ma = ?";
                } else {
                    whereClause = " f.ma = ?";
                }
                params.add(new Parameter(f.getKho_bac(), true));
            }
            String page = f.getPageNumber();
            if (page == null) {
                page = "1";
            }
            // khai bao cac bien phan trang
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = new Integer(15);
            Integer totalCount[] = new Integer[1];
            lstDCNoiBo =
                    (ArrayList<DCNoiBoVO>)dcDAO.getDCNBoListWithPading(whereClause,
                                                                       params,
                                                                       currentPage,
                                                                       numberRowOnPage,
                                                                       totalCount);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("lstDCNBO", lstDCNoiBo);
            request.setAttribute("PAGE_KEY", pagingBean);
            executeAction(mapping, form, request, response);
        } catch (Exception e) {
            // TODO: Add catch code
            throw new Exception("&#272;&#7889;i chi&#7871;u n&#7897;i b&#7897;: " +
                                e.getMessage());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
    private static String SGD = "0003";
    private static String CAP_TINH = "5";

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "DCHIEU.NBO")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        DMNHangDAO dmnhDAO = null;
        DMKBacDAO dmKBDAO = null;
        DoiChieuDAO dcDAO = null;
        String strWhereClause = STRING_EMPTY;
        Vector params = null;
        ArrayList<TThaiVO> lstTThai = null;
        ArrayList<DMKBacVO> lstKBTinh = null;
        ArrayList<DMNHangVO> lstNganHang = null;
        ArrayList<StatisticsDCNoiBoVO> lstStatistic = null;
        try {
            conn = getConnection();
            QuyetToanDAO qtDAO = new QuyetToanDAO(conn);
            dmnhDAO = new DMNHangDAO(conn);
            dmKBDAO = new DMKBacDAO(conn);
            dcDAO = new DoiChieuDAO(conn);
            params = new Vector();
            // lay danh sach ngan hang
            strWhereClause = " id in (select nh_id from sp_tknh_kb )";
            lstNganHang =
                    (ArrayList<DMNHangVO>)dmnhDAO.getDMNHList(strWhereClause,
                                                              params);
            // lay danh sach kho bac

            strWhereClause = " (a.cap=? or a.ma='" + SGD + "') ";
            params = new Vector();
            params.add(new Parameter(CAP_TINH, true));
            lstKBTinh =
                    (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(strWhereClause,
                                                             params);
            // lay danh sach trang thai

            strWhereClause = "a.rv_domain=? ";
            params = new Vector();
            String strDomain = "SP_DC.TRANG_THAI";
            params.add(new Parameter(strDomain, true));
            lstTThai =
                    (ArrayList<TThaiVO>)qtDAO.getDSTrang_Thai(strWhereClause,
                                                              params);
            // lay ra danh sach thong ke
            strWhereClause = STRING_EMPTY;
            params = new Vector();
            lstStatistic =
                    (ArrayList<StatisticsDCNoiBoVO>)dcDAO.getStatisticDCNBo(strWhereClause,
                                                                            params);
        } catch (Exception e) {
            // TODO: Add catch code
            throw new Exception("&#272;&#7889;i chi&#7871;u n&#7897;i b&#7897;: " +
                                e.getMessage());
        } finally {
            request.setAttribute("lstTThai", lstTThai);
            request.setAttribute("lstNganHang", lstNganHang);
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstStatistic", lstStatistic);
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "DCHIEU.NBO")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        DCNoiBoVO dcNBoVO = null;
        DoiChieuDAO dcDAO = null;
        Vector params = null;
        String strJson = "";
        try {
            conn = getConnection();
            dcDAO = new DoiChieuDAO(conn);
            params = new Vector();
            String id = request.getParameter("id");
            if (id != null && !STRING_EMPTY.equals(id)) {
                params.add(new Parameter(id, true));
                dcNBoVO = dcDAO.getDCNoiBoByKey(params);
                Type listType = new TypeToken<DCNoiBoVO>() {
                }.getType();
                strJson = new Gson().toJson(dcNBoVO, listType);
            }
            //          executeAction(mapping, form, request, response);
        } catch (Exception e) {
            // TODO: Add catch code
            throw e;
        } finally {
            close(conn);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
