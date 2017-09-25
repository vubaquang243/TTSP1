package com.seatech.ttsp.tcuudmuc.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.tcuudmuc.TCuuDMucDAO;
import com.seatech.ttsp.tcuudmuc.TCuuDMucVO;
import com.seatech.ttsp.tcuudmuc.form.TCuuDMucForm;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TCuuDMucAction extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            TCuuDMucDAO dmDAO = new TCuuDMucDAO(conn);
            TCuuDMucVO dmVO = new TCuuDMucVO();
            TCuuDMucForm frm = (TCuuDMucForm)form;
            Collection colDMuc = null;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            String page = frm.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];

            String table_name =
                frm.getTable_name() == null ? "" : frm.getTable_name();
            String ma = frm.getMa() == null ? "" : frm.getMa();
            String tinhtrang =
                frm.getTinhtrang() == null ? "" : frm.getTinhtrang();
            String ten = frm.getTen() == null ? "" : frm.getTen();
            String strDMuc = "";
            if (table_name.equalsIgnoreCase("SP_DM_TIENTE") ||
                table_name.equalsIgnoreCase("SP_DM_DVSDNS")) {
                strDMuc +=
                        " Select ma, ten, tinh_trang tinhtrang FROM " + table_name +
                        " WHERE 1=1";
            } else if (table_name.equalsIgnoreCase("SP_DM_NGAN_HANG")) {
                strDMuc +=
                        " Select ma_nh ma, ten, tinh_trang tinhtrang FROM " +
                        table_name + " WHERE 1=1";
            } else if (table_name != null && !"".equals(table_name)) {
                strDMuc +=
                        " Select ma, ten, tinhtrang tinhtrang FROM " + table_name +
                        " WHERE 1=1";
                ;
            }
            if (!"".equals(ma) && ma != null) {
                if (table_name.equalsIgnoreCase("SP_DM_NGAN_HANG")) {
                    strDMuc += " AND UPPER(ma_nh)= UPPER('" + ma + "')";
                } else {
                    strDMuc += " AND UPPER(ma)= UPPER('" + ma + "')";
                }
            }
            if (!"".equals(ten) && ten != null) {
                strDMuc += " AND UPPER(ten) like UPPER('%" + ten + "%')";
            }
            if ((!"".equals(tinhtrang) && tinhtrang != null) &&
                (table_name.equalsIgnoreCase("SP_DM_TIENTE") ||
                 table_name.equalsIgnoreCase("SP_DM_DVSDNS") ||
                 table_name.equalsIgnoreCase("SP_DM_NGAN_HANG") ||
                 table_name.equalsIgnoreCase("SP_DM_NGAN_HANG"))) {
                strDMuc += " AND tinh_trang=" + tinhtrang;
            } else if ((!"".equals(tinhtrang) && tinhtrang != null) &&
                       (!table_name.equalsIgnoreCase("SP_DM_TIENTE") &&
                        !table_name.equalsIgnoreCase("SP_DM_DVSDNS") &&
                        !table_name.equalsIgnoreCase("SP_DM_NGAN_HANG") &&
                        !table_name.equalsIgnoreCase("SP_DM_NGAN_HANG"))) {
                strDMuc += " AND tinhtrang=" + tinhtrang;
            }

            if (table_name != null && !"".equals(table_name)) {
                colDMuc = new ArrayList();
                colDMuc =
                        dmDAO.getLstDMuc(strDMuc, null, currentPage, numberRowOnPage,
                                         totalCount);
                if (colDMuc.isEmpty()) {
                    request.setAttribute("colDMuc", null);
                } else {
                    request.setAttribute("colDMuc", colDMuc);
                }
            }

            if (colDMuc == null) {
                PagingBean pagingBean = new PagingBean();
                request.setAttribute("PAGE_KEY", pagingBean);
            } else {
                PagingBean pagingBean = new PagingBean();
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

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        return mapping.findForward("success");
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            TCuuDMucDAO dmDAO = new TCuuDMucDAO(conn);

            TCuuDMucForm frm = (TCuuDMucForm)form;
            frm.setAction_status(null);

            String table_name =
                frm.getTable_name() == null ? "" : frm.getTable_name();
            String ma = frm.getMa() == null ? "" : frm.getMa();
            try {
                if ("".equals(ma)) {
                    frm.setAction_status("Phải nhập mã danh mục");
                    return mapping.findForward("success");
                }
                if (!"SP_DM_TIENTE".equalsIgnoreCase(table_name)) {
                    int iMa = Integer.parseInt(ma);
                }
            } catch (Exception ex) {
                frm.setAction_status("Mã danh mục không đúng định dạng");
                return mapping.findForward("success");
            }
            String ten = frm.getTen() == null ? "" : frm.getTen();

            String strTinhTrangColName = "tinhtrang";
            String strMaColName = "ma";
            if (table_name.equalsIgnoreCase("SP_DM_TIENTE")) {
                strTinhTrangColName = "tinh_trang";
                strMaColName = "ma";
                if (ma.length() != 3) {
                    frm.setAction_status("Mã tiền tệ phải có 3 ký tự");
                    return mapping.findForward("success");
                }
            } else if (table_name.equalsIgnoreCase("SP_DM_DVSDNS")) {
                strTinhTrangColName = "tinh_trang";
                strMaColName = "ma";
                if (ma.length() != 7) {
                    frm.setAction_status("Mã DVSDNS phải có 7 ký tự");
                    return mapping.findForward("success");
                }
            } else if (table_name.equalsIgnoreCase("SP_DM_NGAN_HANG")) {
                strTinhTrangColName = "tinh_trang";
                strMaColName = "ma_nh";
                if (ma.length() != 8) {
                    frm.setAction_status("Mã NH phải có 8 ký tự");
                    return mapping.findForward("success");
                }
            } else if (table_name.equalsIgnoreCase("SP_DM_HTKB")) {
                strTinhTrangColName = "tinhtrang";
                strMaColName = "ma";
                if (ma.length() != 4) {
                    frm.setAction_status("Mã KB phải có 4 ký tự");
                    return mapping.findForward("success");
                }
            } else if (table_name.equalsIgnoreCase("SP_DM_CAPNS")) {
                strTinhTrangColName = "tinhtrang";
                strMaColName = "ma";
                if (ma.length() != 1) {
                    frm.setAction_status("Mã CAP NS phải có 1 ký tự");
                    return mapping.findForward("success");
                }
            } else if (table_name.equalsIgnoreCase("SP_DM_TKKT")) {
                strTinhTrangColName = "tinhtrang";
                strMaColName = "ma";
                if (ma.length() != 1) {
                    frm.setAction_status("Mã tài khoản KT phải có 5 ký tự");
                    return mapping.findForward("success");
                }
            } else if (table_name.equalsIgnoreCase("SP_DM_NGUONCHI")) {
                strTinhTrangColName = "tinhtrang";
                strMaColName = "ma";
                if (ma.length() != 2) {
                    frm.setAction_status("Mã nguồn chi phải có 2 ký tự");
                    return mapping.findForward("success");
                }
            } else if (table_name.equalsIgnoreCase("SP_DM_NGANHKT")) {
                strTinhTrangColName = "tinhtrang";
                strMaColName = "ma";
                if (ma.length() != 3) {
                    frm.setAction_status("Mã ngành kinh tế phải có 3 ký tự");
                    return mapping.findForward("success");
                }
            } else if (table_name.equalsIgnoreCase("SP_DM_MA_QUY")) {
                strTinhTrangColName = "tinhtrang";
                strMaColName = "ma";
                if (ma.length() != 2) {
                    frm.setAction_status("Mã quỹ phải có 2 ký tự");
                    return mapping.findForward("success");
                }
            } else if (table_name.equalsIgnoreCase("SP_DM_CHUONG")) {
                strTinhTrangColName = "tinhtrang";
                strMaColName = "ma";
                if (ma.length() != 3) {
                    frm.setAction_status("Mã chương phải có 3 ký tự");
                    return mapping.findForward("success");
                }
            } else if (table_name != null && !"".equals(table_name)) {
                strTinhTrangColName = "tinhtrang";
                strMaColName = "ma";
            }

            if (table_name != null && !"".equals(table_name)) {
                String strSQLSelect =
                    "select ten as ma from " + table_name + " where " +
                    strMaColName + " = '" + ma + "' and " +
                    strTinhTrangColName + " = '1'";
                Collection colDMuc =
                    dmDAO.getLstDMuc(strSQLSelect, null, null, null, null);
                if (colDMuc.isEmpty()) {
                    String strSQL =
                        "INSERT INTO " + table_name + "(id, " + strMaColName +
                        ", ten," + strTinhTrangColName + ") values(" +
                        table_name + "_seq.nextval, '" + ma + "','" + ten +
                        "', '1')";
                    int rowNum = dmDAO.executeStatement(strSQL, null);
                    if (rowNum < 0) {
                        frm.setAction_status("Không cập nhật được danh mục");
                    }
                    conn.commit();
                    frm.setAction_status("Thêm mới thành công");
                } else {
                    frm.setAction_status("Đã tồn tại danh mục có mã " + ma +
                                         " trong bảng " + table_name);
                }
            } else {
                frm.setAction_status("Chọn loại danh mục để thêm mới");
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
}
