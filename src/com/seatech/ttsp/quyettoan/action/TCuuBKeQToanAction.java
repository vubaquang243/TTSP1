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
            QuyetToanDAO dao = new QuyetToanDAO(conn);
            List tienTe =
                tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ", null);
			//20171207 QuangVB load DM ngan hang HO	
            Collection lstNganHang = dao.getDMNH("", null);

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
			//20171207 QuangVB load DM ngan hang HO	
            request.setAttribute("lstNganHang", lstNganHang);
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
/**
*@modify: QuangVB
*@modify-date: 11/12/2017
*@see: sua code ham view bo sung cac tieu chi tim kiem dao ung yeu cap nang cap ngoai hop dong 2017
*/
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
            String loai_quyet_toan =
                frm.getLoai_quyet_toan() == null ? "" : frm.getLoai_quyet_toan();
            String ma_nhan_vien =
                frm.getMa_thanh_toan_vien() == null ? "" : frm.getMa_thanh_toan_vien();
            String ngan_hang = frm.getNgan_hang() == null ? "" : frm.getNgan_hang();
            String ngay_bke = frm.getNgay_bke() == null ? "" : frm.getNgay_bke();
			//20171215 thuongdt them moi ngay_tao sua loi nsd thoat khong load lai du lieu
            String ngay_tao = frm.getNgay_tao() == null ? "" : frm.getNgay_tao();
            if(!ngay_tao.equals(""))
            frm.setNgay_bke(ngay_tao);
            String strTCuu = "";
            if (ma_nt != null && !"".equals(ma_nt)) {
                strTCuu += " and a.tcg_loai_tien='" + ma_nt + "'";
            }
            if (so_bke != null && !"".equals(so_bke)) {
                strTCuu += " AND UPPER(a.ID) LIKE UPPER('%" + so_bke + "%')";
            }
            if (frm.getNgay_bke() != null && !"".equals(frm.getNgay_bke())) {
                strTCuu +=
                        " AND trunc(a.ngay_tao) = to_date('" + frm.getNgay_bke() +
                        "','dd/mm/yyyy') ";
            }
            if (tthai != null && !"".equals(tthai)) {
                strTCuu += " AND a.trang_thai='" + tthai + "' ";
            }
            if (tu_ngay != null && !"".equals(tu_ngay)) {
                strTCuu +=
                        " and a.ngay_htoan >=  to_date('" + tu_ngay + "','DD-MM-YYYY') ";
            }
            if (den_ngay != null && !"".equals(den_ngay)) {
                strTCuu +=
                        " and a.ngay_htoan <= to_date('" + den_ngay + "','DD-MM-YYYY') ";
            }
            if (loai_quyet_toan != null && !"".equals(loai_quyet_toan)) {
                strTCuu +=
                        " and a.tcg_loai_qtoan = '" + loai_quyet_toan + "' ";
            }
            if (ma_nhan_vien != null && !"".equals(ma_nhan_vien)) {
				//20171215 thuongdt upper va like ma_nhan_vien cho phep nhap tu do khi tra cuu bke
                strTCuu += " and upper(d.ma_nsd) like upper('" + ma_nhan_vien + "%') ";
            }
            if (frm.getNgan_hang() != null && !"".equals(frm.getNgan_hang())) {
                strTCuu += " and b.ma_dv = '" + frm.getNgan_hang() + "' ";
            }
            //20171215 thuongdt them moi ma ngan hang them moi tieu chi tim kiem theo yeu cau nang cap ngoai hop dong 2017
          if (frm.getNgan_hang() != null && !"".equals(frm.getNgan_hang())) {
              strTCuu += " and b.ma_dv = '" + frm.getNgan_hang() + "' ";
          }
            colTCuu =
                    dao.getTCuuBKe_ptrang(strTCuu, null, currentPage, numberRowOnPage,
                                          totalCount);
            String tcuu =
                "&ma_thanh_toan_vien=" + ma_nhan_vien + "&ngan_hang=" +
                ngan_hang + "&trang_thai=" + tthai + "&tu_ngay=" +
                tu_ngay + "&den_ngay=" + den_ngay + "&so_bke1=" + so_bke +
                "&ngay_bang_ke=" + ngay_bke +"&loai_quyet_toan="+loai_quyet_toan+ "&currentPage=" +
                currentPage;

            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);

            Collection lstNganHang = dao.getDMNH("", null);
            request.setAttribute("lstNganHang", lstNganHang);
            request.setAttribute("colTCuu", colTCuu);
            request.setAttribute("tcuu", tcuu);
            request.setAttribute("tienTe", tienTe);

            request.setAttribute("loai_tien", ma_nt);

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
			//20171215 thuongdt them moi ngay_tao sua loi nsd thoat khong load lai du lieu
            String ngay_tao = frm.getNgay_tao() == null ? "" : frm.getNgay_tao();
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
            } else {
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
				//20171215 thuongdt them moi ngay_tao sua loi nsd thoat khong load lai du lieu														 
                String tcuu =
                    "?trang_thai=" + tthai + "&ngay_tao="+ngay_tao+"&tu_ngay=" + tu_ngay + "&den_ngay=" +
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
