package com.seatech.ttsp.dmngoaite_manh.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmngoaite_manh.DMNgoaiTe_ManhDAO;
import com.seatech.ttsp.dmngoaite_manh.DMNgoaiTe_ManhVO;
import com.seatech.ttsp.dmngoaite_manh.form.DMNgoaiTe_ManhForm;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.dmtiente.DMTienTeVO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DMNgoaiTe_ManhAction extends AppAction {
    public static final int STATUS_UNSUCCESS = 1;
    public static final int STATUS_SUCCESS = 2;
    public static final int STATUS_FALSE = 3;


    public ActionForward list(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {
            conn = getConnection(request);
            DMNgoaiTe_ManhForm f = (DMNgoaiTe_ManhForm)actionForm;
            DMNgoaiTe_ManhDAO dao = new DMNgoaiTe_ManhDAO(conn);
            String strWhere = "";
            Vector vParam = new Vector();
            // lấy biến từ form


            String strMa = f.getMa();
            String strPageNumber = f.getPageNumber();
            String strNguoi_ngung_hd = f.getNguoi_ngung_hd();

            //Build menh de where

            if (!"".equals(strMa) && strMa != null) {
                strWhere += " and a.ma = ? ";
                vParam.add(new Parameter(strMa, true));
            }
            if (!"".equals(strNguoi_ngung_hd) && strNguoi_ngung_hd != null) {
                strWhere += " and a.nguoi_ngung_hd = ?";
            }


            //Cac thong so phan trang
            if (strPageNumber == null)
                strPageNumber = "1";
            int nCurrentPage = Integer.parseInt(strPageNumber);
            int nNumberRowOnPage = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            Integer totalCount[] = new Integer[1];
            //Select DB
            List Ngoai_te =
                (List)dao.getNgoai_te(strWhere, vParam, nCurrentPage,
                                      nNumberRowOnPage, totalCount);
            //Set acttribute
            request.setAttribute("Ngoai_te", Ngoai_te);
            if (Ngoai_te == null) {
                PagingBean pagingBean = new PagingBean();
                request.setAttribute("PAGE_KEY", pagingBean);
            } else {
                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(nCurrentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(nNumberRowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);
            }
            //Lay danh sach ma tien te
            DMTienTeDAO dmTien = new DMTienTeDAO(conn);
            ArrayList<DMTienTeVO> lstLoaiTien =
                (ArrayList<DMTienTeVO>)dmTien.getDMTienTeList(null, null);

            request.setAttribute("Ngoai_te", Ngoai_te);
            request.setAttribute("lstLoaiTien", lstLoaiTien);

            return mapping.findForward("success");
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }
    }

    public ActionForward add(ActionMapping mapping, ActionForm actionForm,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {

            conn = getConnection(request);
            DMNgoaiTe_ManhForm f = (DMNgoaiTe_ManhForm)actionForm;
            f.setMa(null);


            // lay danh sach ma ngoai te
            DMNgoaiTe_ManhDAO dao = new DMNgoaiTe_ManhDAO(conn);
            String strWhere = "";
            Vector vParam = new Vector();
            List Ngoai_te = (List)dao.getNgoai_te(strWhere, vParam);
            DMTienTeDAO dmTien = new DMTienTeDAO(conn);
            ArrayList<DMTienTeVO> lstLoaiTien =
                (ArrayList<DMTienTeVO>)dmTien.getDMTienTeList(null, null);

            request.setAttribute("Ngoai_te", Ngoai_te);
            request.setAttribute("lstLoaiTien", lstLoaiTien);
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            DMNgoaiTe_ManhForm f = (DMNgoaiTe_ManhForm)form;
            DMNgoaiTe_ManhDAO dao = new DMNgoaiTe_ManhDAO(conn);
            f.setAction_status(null);

            //Kiem tra ton tai
            String strWhere = " and ma = ? ";
            Vector vParam = new Vector();
            vParam.add(new Parameter(f.getMa(), true));

            List Ngoai_te = (List)dao.getNgoai_te(strWhere, vParam);

            //Lay danh sach ma tien te
            DMTienTeDAO dmTien = new DMTienTeDAO(conn);
            ArrayList<DMTienTeVO> lstLoaiTien =
                (ArrayList<DMTienTeVO>)dmTien.getDMTienTeList(null, null);

            request.setAttribute("Ngoai_te", Ngoai_te);
            request.setAttribute("lstLoaiTien", lstLoaiTien);

            if (Ngoai_te.size() > 0) {
                f.setAction_status(" Mã ngoại tệ " +
                                   f.getMa() + " đã tồn tại ");

                return mapping.findForward("success");
            }

            String nguoi_tao =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            //****
            DMNgoaiTe_ManhVO vo = new DMNgoaiTe_ManhVO();

            vo.setId(f.getId());
            vo.setMa(f.getMa());
            vo.setNguoi_tao(nguoi_tao);
            vo.setNgay_tao(f.getNgay_tao());
            vo.setNgay_hieu_luc(f.getNgay_hieu_luc());


            //Insert data
            long nt = dao.insert(vo);
            if (1 < nt) {
                f.setAction_status("Thiết lập không thành công. Không thêm được bản ghi nào của CSDL");
            }
            conn.commit();
            f.setAction_status("Thiết lập thành công");


        } catch (Exception ex) {
            throw ex;
        } finally {
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
            DMNgoaiTe_ManhForm f = (DMNgoaiTe_ManhForm)form;
            f.setMa(null);


            // lay danh sach ma ngoai te
            DMNgoaiTe_ManhDAO dao = new DMNgoaiTe_ManhDAO(conn);
            String strWhere = "";
            Vector vParam = new Vector();
            List Ngoai_te = (List)dao.getNgoai_te(strWhere, vParam);
            ArrayList<DMNgoaiTe_ManhVO> loaitien =
                (ArrayList<DMNgoaiTe_ManhVO>)dao.getMaNT(null, null);

            request.setAttribute("Ngoai_te", Ngoai_te);
            request.setAttribute("loaitien", loaitien);
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();

            DMNgoaiTe_ManhForm f = (DMNgoaiTe_ManhForm)form;
            DMNgoaiTe_ManhDAO dao = new DMNgoaiTe_ManhDAO(conn);
            f.setAction_status(null);
            // lay danh sach ma ngoai te
            String strWhere = "";
            Vector vParam = new Vector();
            List Ngoai_te = (List)dao.getNgoai_te(strWhere, vParam);
            ArrayList<DMNgoaiTe_ManhVO> loaitien =
                (ArrayList<DMNgoaiTe_ManhVO>)dao.getMaNT(null, null);

            request.setAttribute("Ngoai_te", Ngoai_te);
            request.setAttribute("loaitien", loaitien);
            String nguoi_ngung_hd =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();


            DMNgoaiTe_ManhVO vo = new DMNgoaiTe_ManhVO();

            vo.setId(f.getId());
            vo.setMa(f.getMa());
            vo.setNguoi_ngung_hd(nguoi_ngung_hd);
            vo.setNgay_het_hieu_luc(f.getNgay_het_hieu_luc());

            //Update CSDL
            long nt = dao.update(vo);
            if (nt < 1) {
                f.setAction_status("Ngừng hoạt động không thành công. Không có bản ghi nào được cập nhật CSDL");
            }
            conn.commit();
            f.setAction_status(" Đã ngừng hoạt động ");
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
}
