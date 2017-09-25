package com.seatech.ttsp.sodu.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.dmtiente.DMTienTeVO;
import com.seatech.ttsp.sodu.SoDuDAO;
import com.seatech.ttsp.sodu.SoDuVO;
import com.seatech.ttsp.sodu.form.SoDuForm;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class SoDuAction extends AppAction {
    public static final int STATUS_UNSUCCESS = 1;
    public static final int STATUS_SUCCESS = 2;
    public static final int STATUS_FALSE = 3;


    public ActionForward list(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {
            conn = getConnection(request);
            SoDuForm f = (SoDuForm)actionForm;
            SoDuDAO dao = new SoDuDAO(conn);
            String strWhere = "";
            Vector vParam = new Vector();
            // lấy biến từ form
            String strType = f.getType();

            if ("delete".equals(strType)) {
                f.setId(null);
                f.setMa_kb(null);
                f.setMa_nh(null);
                f.setInsert_date(null);
                f.setNgay_gd(null);
                f.setSo_du(null);
                f.setSo_du_cot(null);
                f.setLoai_tien(null);
            }


            String strMa_kb = f.getMa_kb();
            String strMa_nh = f.getMa_nh();
            String strNgay_gd = f.getNgay_gd();
            String strSo_du = f.getSo_du();
            String strInsert_date = f.getInsert_date();
            String strSo_du_cot = f.getSo_du_cot();
            String strLoai_tien = f.getLoai_tien();
            String strPageNumber = f.getPageNumber();
            String strId = f.getId();
            //Build menh de where

            if (!"".equals(strMa_kb) && strMa_kb != null) {
                strWhere += " and ma_kb like '%" + strMa_kb + "%'";
            }
            if (!"".equals(strMa_nh) && strMa_nh != null) {
                strWhere += " and ma_nh = ?";
                vParam.add(new Parameter(strMa_nh, true));
            }
            if (!"".equals(strNgay_gd) && strNgay_gd != null) {
                strWhere += " and ngay_gd = TO_DATE(?,'DD/MM/YYYY')";
                vParam.add(new Parameter(strNgay_gd, true));
            }
            if (!"".equals(strSo_du) && strSo_du != null) {
                strWhere += " and so_du = ?";
                vParam.add(new Parameter(strSo_du, true));
            }
            if (!"".equals(strInsert_date) && strInsert_date != null) {
                strWhere += " and insert_date = TO_DATE(?,'DD/MM/YYYY')";
                vParam.add(new Parameter(strInsert_date, true));
            }
            if (!"".equals(strSo_du_cot) && strSo_du_cot != null) {
                strWhere += " and so_du_cot = ?";
                vParam.add(new Parameter(strSo_du_cot, true));
            }
            if (!"".equals(strLoai_tien) && strLoai_tien != null) {
                strWhere += " and loai_tien = ?";
                vParam.add(new Parameter(strLoai_tien, true));
            }
            if (!"".equals(strId) && strId != null) {
                strWhere += " and id =?";
                vParam.add(new Parameter(strId, true));
            }

            //Cac thong so phan trang
            if (strPageNumber == null)
                strPageNumber = "1";
            int nCurrentPage = Integer.parseInt(strPageNumber);
            int nNumberRowOnPage = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            Integer totalCount[] = new Integer[1];
            //Select DB
            List listSoDu =
                (List)dao.getLstSoDu(strWhere, vParam, nCurrentPage,
                                     nNumberRowOnPage, totalCount);
            //Set acttribute
            request.setAttribute("listSoDu", listSoDu);
            if (listSoDu == null) {
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

            request.setAttribute("listSoDu", listSoDu);
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
            SoDuForm f = (SoDuForm)actionForm;
            f.setAction_status(null);
            f.setNgay_gd(null);
            f.setId(null);
            f.setMa_kb(null);
            f.setMa_nh(null);
            f.setLoai_tien(null);
            f.setSo_du(null);
            f.setSo_du_cot(null);

            SoDuDAO dao = new SoDuDAO(conn);
            String strWhere = "";
            Vector vParam = new Vector();
            List listSoDu = (List)dao.getLstSoDu(strWhere, vParam);
            DMTienTeDAO dmTien = new DMTienTeDAO(conn);
            ArrayList<DMTienTeVO> lstLoaiTien =
                (ArrayList<DMTienTeVO>)dmTien.getDMTienTeList(null, null);

            request.setAttribute("listSoDu", listSoDu);
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
            SoDuForm f = (SoDuForm)form;
            SoDuDAO dao = new SoDuDAO(conn);
            f.setAction_status(null);


            //Kiem tra ton tai
            String strWhere =
                " and (ma_kb = ? or ma_nh = ?) and ngay_gd = TO_DATE(?,'dd/mm/yy') and loai_tien = ? ";
            Vector vParam = new Vector();
            vParam.add(new Parameter(f.getMa_kb(), true));
            vParam.add(new Parameter(f.getMa_nh(), true));
            vParam.add(new Parameter(f.getNgay_gd(), true));
            vParam.add(new Parameter(f.getLoai_tien(), true));
            List listSoDu = (List)dao.getLstSoDu(strWhere, vParam);

            //Lay danh sach ma tien te
            DMTienTeDAO dmTien = new DMTienTeDAO(conn);
            ArrayList<DMTienTeVO> lstLoaiTien =
                (ArrayList<DMTienTeVO>)dmTien.getDMTienTeList(null, null);

            request.setAttribute("listSoDu", listSoDu);
            request.setAttribute("lstLoaiTien", lstLoaiTien);


            if (listSoDu.size() > 0) {
                f.setAction_status(" Nhóm mã kho bạc " + f.getMa_kb() +
                                   " và mã ngân hàng " + f.getMa_nh() +
                                   "  đã tồn tại.");

                return mapping.findForward("success");
            }


            //****
            SoDuVO vo = new SoDuVO();

            vo.setMa_kb(f.getMa_kb());
            vo.setMa_nh(f.getMa_nh());
            vo.setNgay_gd(f.getNgay_gd());
            vo.setSo_du(f.getSo_du());
            vo.setInsert_date(f.getInsert_date());
            vo.setSo_du_cot(f.getSo_du_cot());
            vo.setLoai_tien(f.getLoai_tien());

            //Insert data
            long sd = dao.insert(vo);
            if (1 < sd) {
                f.setAction_status("Thêm mới không thanh công. Không thêm được bản ghi nào của CSDL");
            }
            conn.commit();
            f.setAction_status("Thêm mới thành công");


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
        return mapping.findForward("success");
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);
            SoDuForm f = (SoDuForm)form;
            SoDuDAO dao = new SoDuDAO(conn);
            f.setAction_status(null);


            SoDuVO vo = new SoDuVO();
            vo.setId((f.getId()));
            vo.setMa_kb(f.getMa_kb());
            vo.setMa_nh(f.getMa_nh());
            vo.setSo_du(f.getSo_du());
            vo.setSo_du_cot(f.getSo_du_cot());
            vo.setInsert_date(f.getInsert_date());
            vo.setLoai_tien(f.getLoai_tien());
            vo.setNgay_gd(f.getNgay_gd());
            //Update CSDL
            long sd = dao.update(vo);
            if (sd < 1) {
                f.setAction_status("Sửa không thành công. Không có bản ghi nào được cập nhật CSDL");
            }

            conn.commit();
            f.setAction_status("Sửa thành công");


        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            SoDuForm f = (SoDuForm)form;
            SoDuDAO dao = new SoDuDAO(conn);
            SoDuVO vo = new SoDuVO();
            vo.setId(f.getId());
            vo.setMa_kb(f.getMa_kb());
            vo.setMa_nh(f.getMa_nh());
            vo.setLoai_tien(f.getLoai_tien());
            vo.setSo_du(f.getSo_du());
            vo.setSo_du_cot(f.getSo_du_cot());
            vo.setInsert_date(f.getInsert_date());
            vo.setNgay_gd(f.getNgay_gd());

            long sd;
            sd = dao.delete(vo);
            if (sd >= 1) {
                f.setAction_status("Xóa thành công");
            } else {
                f.setAction_status("Xóa không thành công");
            }
            conn.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

}

