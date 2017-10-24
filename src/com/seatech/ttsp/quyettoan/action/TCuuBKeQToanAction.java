package com.seatech.ttsp.quyettoan.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.quyettoan.BKE_QuyetToanVO;
import com.seatech.ttsp.quyettoan.QuyetToanDAO;
import com.seatech.ttsp.quyettoan.form.BKE_QuyetToanForm;
import com.seatech.ttsp.quyettoan.form.QuyetToanForm;
import com.seatech.ttsp.quyettoan.form.TCuuBKeQToanForm;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TCuuBKeQToanAction extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        //      if (!checkPermissionOnFunction(request, "DCHIEU.NT_TCUU_DCHIEU")) {
        //          return mapping.findForward("notRight");
        //      }

        Connection conn = null;
        try {
            conn = getConnection(request);
            TCuuBKeQToanForm frm = (TCuuBKeQToanForm)form;
//            QuyetToanDAO dao = new QuyetToanDAO(conn);
            Collection colTCuu = new ArrayList();
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            List tienTe =
                tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ", null);


            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            String page = frm.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];

//            String strTCuu =
//                " AND (a.ngay_htoan= trunc(sysdate) OR ( a.trang_thai='01' AND a.ngay_htoan < trunc(sysdate)))";
//
//            colTCuu =
//                    dao.getTCuuBKe_ptrang(strTCuu, null, currentPage, numberRowOnPage,
//                                          totalCount);

            request.setAttribute("colTCuu", colTCuu);
            request.setAttribute("tienTe", tienTe);

            PagingBean pagingBean = new PagingBean();
            if (currentPage == 1) {
                pagingBean.setCurrentPage(1);
                pagingBean.setNumberOfRow(1);
                pagingBean.setRowOnPage(15);
                request.setAttribute("PAGE_KEY", pagingBean);
            } else {
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);
            }

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
        //      if (!checkPermissionOnFunction(request, "DCHIEU.NT_TCUU_DCHIEU")) {
        //          return mapping.findForward("notRight");
        //      }

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            QuyetToanDAO dao = new QuyetToanDAO(conn);
            TCuuBKeQToanForm frm = (TCuuBKeQToanForm)form;
            Collection colTCuu = new ArrayList();
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            List tienTe =
                tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ", null);
            //            String tcuu="";

            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            String page = frm.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];

            String so_bke = frm.getSo_bke() == null ? "" : frm.getSo_bke();
            String tu_ngay = frm.getTu_ngay() == null ? "" : frm.getTu_ngay();
            String den_ngay =
                frm.getDen_ngay() == null ? "" : frm.getDen_ngay();
            String tthai =
                frm.getTrang_thai() == null ? "" : frm.getTrang_thai();
            String ma_nt =
                frm.getTcg_loai_tien() == null ? "" : frm.getTcg_loai_tien();

            String strTCuu = "";
            if (ma_nt != null && !"".equals(ma_nt)) {
                strTCuu += " and tcg_loai_tien='" + ma_nt + "'";
            }

            if (so_bke != null && !"".equals(so_bke)) {
                strTCuu += " AND UPPER(ID) LIKE UPPER('%" + so_bke + "%')";
            }
            if (tthai != null && !"".equals(tthai)) {
                strTCuu += " AND trang_thai='" + tthai + "'";
            }
            if ((den_ngay == null || "".equals(den_ngay)) &&
                (tu_ngay != null && !"".equals(tu_ngay))) {
                strTCuu +=
                        " and ((a.ngay_htoan <=  to_date(sysdate) and a.ngay_htoan >=  to_date('" +
                        tu_ngay + "','DD-MM-YYYY'))) ";
            } else if (den_ngay != null && !"".equals(den_ngay) &&
                       (tu_ngay == null || "".equals(tu_ngay))) {
                strTCuu +=
                        " and (a.ngay_htoan <=  to_date('" + den_ngay + "','DD-MM-YYYY')) ";
            } else if (tu_ngay != null && !"".equals(tu_ngay) &&
                       den_ngay != null && !"".equals(den_ngay)) {
                strTCuu +=
                        " and ((a.ngay_htoan >=  to_date('" + tu_ngay + "','DD-MM-YYYY') and a.ngay_htoan <=  to_date('" +
                        den_ngay + "','DD-MM-YYYY'))) ";
            }

            colTCuu =
                    dao.getTCuuBKe_ptrang(strTCuu, null, currentPage, numberRowOnPage,
                                          totalCount);
            String tcuu =
                "&trang_thai=" + tthai + "&tu_ngay=" + tu_ngay + "&den_ngay=" +
                den_ngay + "&so_bke=" + so_bke + "&currentPage=" + currentPage;
            request.setAttribute("colTCuu", colTCuu);
            request.setAttribute("tcuu", tcuu);
            request.setAttribute("tienTe", tienTe);

            request.setAttribute("loai_tien", ma_nt);
            PagingBean pagingBean = new PagingBean();
            if (currentPage == 1) {
                pagingBean.setCurrentPage(1);
                pagingBean.setNumberOfRow(1);
                pagingBean.setRowOnPage(15);
                request.setAttribute("PAGE_KEY", pagingBean);
            } else {
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            }

        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strWhereClause = null;
        QuyetToanDAO qtDAO = null;
        Vector params = null;
        Vector paramsQTVO = null;
        Vector paramsQT = null;
        BKE_QuyetToanVO vo = null;
        String whereClause = null;
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            //              TCuuBKeQToanForm frm = (TCuuBKeQToanForm)form;
            BKE_QuyetToanForm frm = (BKE_QuyetToanForm)form;

            String so_bke = frm.getSo_bke() == null ? "" : frm.getSo_bke();
            String tu_ngay = frm.getTu_ngay() == null ? "" : frm.getTu_ngay();
            String den_ngay =
                frm.getDen_ngay() == null ? "" : frm.getDen_ngay();
            String tthai =
                frm.getTrang_thai() == null ? "" : frm.getTrang_thai();

            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            if (strUserInfo.indexOf(AppConstants.NSD_TTV) != -1) {
                request.setAttribute("chucdanh", strUserInfo);
            } else if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
                request.setAttribute("chucdanh", strUserInfo);
            }else {
              request.setAttribute("chucdanh", "QT");
            }
            
            params = new Vector();
            qtDAO = new QuyetToanDAO(conn);
            strWhereClause = " a.id ='" + so_bke + "'";
            ArrayList<BKE_QuyetToanVO> lstDanhSachBKe =
                (ArrayList<BKE_QuyetToanVO>)qtDAO.getBKeQToanList(strWhereClause,
                                                                  params);
            request.setAttribute("lstDanhSachBKe", lstDanhSachBKe);
            request.setAttribute("rowSelected", "row_qt_0");
            // lay ra chi tiet bang ke
            paramsQTVO = new Vector();
            if (lstDanhSachBKe.size() > 0) {
                // danh sach quyet toan
                BKE_QuyetToanVO voFirst = lstDanhSachBKe.get(0);
                paramsQTVO.add(new Parameter(voFirst.getId(), true));
                vo = qtDAO.findBKeByPK(paramsQTVO);
                BeanUtils.copyProperties(frm, vo);
                String page = "1";
                if (page == null) {
                    page = "1";
                }
                // khai bao cac bien phan trang
                Integer currentPage = new Integer(page);
                Integer numberRowOnPage = new Integer(15);
                Integer totalCount[] = new Integer[1];
                whereClause = "";
                // kiem tra neu trang thai 04 - da duyet chi hien trong ngay
                whereClause = "a.so_bk ='" + so_bke + "'";

                ArrayList<QuyetToanForm> lstQuyetToan =
                    (ArrayList<QuyetToanForm>)qtDAO.getLQTListWithPading(whereClause,
                                                                         paramsQT,
                                                                         currentPage,
                                                                         numberRowOnPage,
                                                                         totalCount);
                String tcuu =
                    "?trang_thai=" + tthai + "&tu_ngay=" + tu_ngay + "&den_ngay=" +
                    den_ngay + "&currentPage=" + currentPage;
                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("lstQuyetToan", lstQuyetToan);
                request.setAttribute("PAGE_KEY", pagingBean);
                request.setAttribute("view", "view");
              request.setAttribute("tcuu", tcuu);
            }

        } catch (Exception e) {
            // TODO: Add catch code
            throw new Exception("X&#7917; l&#253; quy&#7871;t to&#225;n to&#224;n qu&#7889;c : " +
                                e.getMessage());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }


}
