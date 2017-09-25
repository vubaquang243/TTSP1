package com.seatech.ttsp.tracuuctgd.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.DateUtils;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.ltt.LTTCommon;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.form.LTTForm;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TraCuuCTGDAction extends AppAction {
    private static String STRING_EMPTY = "";

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        String forward = AppConstants.SUCCESS;
        Connection conn = null;
        String whereClause = "";
        Vector params = null;
        Parameter param = null;
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            //Lay ma kb nhan
            String strKBacID = "";
            if (session.getAttribute(AppConstants.APP_KB_ID_SESSION) != null) {
                strKBacID =
                        session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            }
            Collection listDMNH = new ArrayList();
            DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
            String strWhere = " and c.id = ?";
            Vector vtParam = new Vector();
            vtParam.add(new Parameter(strKBacID, true));

            listDMNH = dmnhDAO.getDMNHListByKBId(strWhere, vtParam);
            DMNHangVO dmnhVO = null;
            for (Iterator it = listDMNH.iterator(); it.hasNext(); ) {
                dmnhVO = (DMNHangVO)it.next();
                if (dmnhVO != null)
                    break;
            }
            if (dmnhVO != null)
                request.setAttribute("Ma_nhkb_nhan_cm", dmnhVO.getMa_nh());
            request.setAttribute("colDMNH", listDMNH);

            LTTForm lttForm = (LTTForm)form;
            params = new Vector();

            String whereClau = "";
            Vector vParams = null;
            if (lttForm.getMa_nhkb_nhan() != null &&
                !STRING_EMPTY.equalsIgnoreCase(lttForm.getMa_nhkb_nhan())) {
                whereClau = " a.ma_nh = ? ";
                vParams = new Vector();
                vParams.add(new Parameter(lttForm.getMa_nhkb_nhan(), true));
                dmnhVO = LTTCommon.getDMNHang(whereClau, vParams, conn);
                if (dmnhVO != null) {
                    whereClause = "AND  t.nhkb_nhan = ? ";
                    params.add(new Parameter(dmnhVO.getId(), true));
                }
            }
            if (lttForm.getMa_nhkb_chuyen() != null &&
                !STRING_EMPTY.equalsIgnoreCase(lttForm.getMa_nhkb_chuyen())) {
                whereClau = " a.ma_nh = ? ";
                vParams = new Vector();
                vParams.add(new Parameter(lttForm.getMa_nhkb_chuyen(), true));
                dmnhVO = LTTCommon.getDMNHang(whereClau, vParams, conn);
                if (dmnhVO != null) {
                    whereClause += "AND t.nhkb_chuyen = ? ";
                    params.add(new Parameter(dmnhVO.getId(), true));
                }
            }
            if (lttForm.getSo_tien() != null &&
                !STRING_EMPTY.equalsIgnoreCase(lttForm.getSo_tien())) {
                String strSoTien = lttForm.getSo_tien();
                if (strSoTien.indexOf(".") != -1)
                    strSoTien = strSoTien.replace(".", "");
                if (strSoTien.indexOf(",") != -1)
                    strSoTien = strSoTien.replace(",", "");
                if (strSoTien.indexOf(" ") != -1)
                    strSoTien = strSoTien.replace(" ", "");

                whereClause += "AND c.so_tien = ? ";
                params.add(new Parameter(strSoTien, true));
            }
            if (lttForm.getId() != null &&
                !STRING_EMPTY.equalsIgnoreCase(lttForm.getId())) {
                whereClause += "AND t.id = ? ";
                param = new Parameter(Long.parseLong(lttForm.getId()), true);
                params.add(param);
            }
            if (lttForm.getNgay_tt() != null &&
                !STRING_EMPTY.equalsIgnoreCase(lttForm.getNgay_tt())) {
                whereClause += "AND t.ngay_tt >= ? ";
                String strTuNgay = lttForm.getNgay_tt();
                strTuNgay = DateUtils.DateToLong(strTuNgay) + "";
                param = new Parameter(Long.parseLong(strTuNgay), true);
                params.add(param);
            }
            if (lttForm.getNgay_ct() != null &&
                !STRING_EMPTY.equalsIgnoreCase(lttForm.getNgay_ct())) {
                whereClause += "AND t.ngay_tt <= ? ";
                String strDenNgay = lttForm.getNgay_ct();
                strDenNgay = DateUtils.DateToLong(strDenNgay) + "";
                param = new Parameter(Long.parseLong(strDenNgay), true);
                params.add(param);
            }

            // khai bao cac bien phan trang
            String page = lttForm.getPageNumber();
            if (page == null) {
                page = "1";
            }
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = new Integer(15);
            Integer totalCount[] = new Integer[1];
            //totalCount = null;
            Collection lstLTT = null;
            if (whereClause != null && !"".equalsIgnoreCase(whereClause)) {
                if (conn == null || conn.isClosed())
                    conn = getConnection();
                LTTDAO lttDAO = new LTTDAO(conn);
                if (whereClause.startsWith("AND")) {
                    whereClause = whereClause.substring(3);
                }
                lstLTT =
                        lttDAO.traCuuChungTuGD(whereClause, params, currentPage,
                                               numberRowOnPage, totalCount);

                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                if (totalCount != null)
                    pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);
            }

            request.setAttribute("MaNhkbNhan", lttForm.getMa_nhkb_nhan());

            request.setAttribute("lstLTT", lstLTT);
        } finally {
            conn.close();
        }

        return mapping.findForward(forward);
    }
}
