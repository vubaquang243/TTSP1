package com.seatech.ttsp.ngaytrienkhai.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.dmtiente.DMTienTeVO;
import com.seatech.ttsp.ngaytrienkhai.NgayTrienKhaiDAO;
import com.seatech.ttsp.ngaytrienkhai.NgayTrienKhaiVO;
import com.seatech.ttsp.ngaytrienkhai.form.NgayTrienKhaiForm;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class NgayTrienKhaiAction extends AppAction {
    public static final int STATUS_UNSUCCESS = 1;
    public static final int STATUS_SUCCESS = 2;
    public static final int STATUS_FALSE = 3;


    public NgayTrienKhaiAction() {
        super();
    }


    public ActionForward list(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {
            conn = getConnection(request);
            NgayTrienKhaiForm f = (NgayTrienKhaiForm)actionForm;
            NgayTrienKhaiDAO dao = new NgayTrienKhaiDAO(conn);
            String strWhere = "";
            Vector vParam = new Vector();
            // lấy biến từ form
            String strType = f.getType();

            if ("delete".equals(strType)) {
                f.setId(null);
                f.setMa_kb(null);
                f.setMa_nh(null);
                f.setMa_nt(null);
                f.setNgay_trien_khai(null);
                f.setGhi_chu(null);
            }

            String strMa_kb = f.getMa_kb();
            String strMa_nh = f.getMa_nh();
            String strMa_nt = f.getMa_nt();
            String strNgay_trien_khai = f.getNgay_trien_khai();
            String strGhi_chu = f.getGhi_chu();
            String strId = f.getId();
            String strPageNumber = f.getPageNumber();
            //Build menh de where
            if (!"".equals(strMa_kb) && strMa_kb != null) {
                strWhere += " and ma_kb like '%" + strMa_kb + "%'";
            }
            if (!"".equals(strMa_nh) && strMa_nh != null) {
                strWhere += " and ma_nh = ?";
                vParam.add(new Parameter(strMa_nh, true));
            }
            if (!"".equals(strMa_nt) && strMa_nt != null) {
                strWhere += " and ma_nt = ?";
                vParam.add(new Parameter(strMa_nt, true));
            }
            if (!"".equals(strNgay_trien_khai) && strNgay_trien_khai != null) {
                strWhere += " and ngay_trien_khai = TO_DATE(?,'DD/MM/YYYY')";
                vParam.add(new Parameter(strNgay_trien_khai, true));
            }
            if (!"".equals(strGhi_chu) && strGhi_chu != null) {
                strWhere += " and ghi_chu = ?";
                vParam.add(new Parameter(strGhi_chu, true));
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
            List listNgayTrienKhai =
                (List)dao.getLstNTrKhai(strWhere, vParam, nCurrentPage,
                                        nNumberRowOnPage, totalCount);
            //Set acttribute
            request.setAttribute("listNgayTrienKhai", listNgayTrienKhai);
            if (listNgayTrienKhai == null) {
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

            request.setAttribute("listNgayTrienKhai", listNgayTrienKhai);
            request.setAttribute("lstLoaiTien", lstLoaiTien);

            return mapping.findForward("success");
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }
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
            NgayTrienKhaiForm f = (NgayTrienKhaiForm)form;
            NgayTrienKhaiDAO dao = new NgayTrienKhaiDAO(conn);
            f.setAction_status(null);

            // kiểm tra tồn tại
            String strWhere =
                "and ngay_trien_khai = TO_DATE(?,'DD/MM/YYYY') and ma_kb = ? and ma_nh = ? and ma_nt = ? and id <> ? ";
            Vector vParam = new Vector();
            vParam.add(new Parameter(f.getNgay_trien_khai(), true));
            vParam.add(new Parameter(f.getMa_kb(), true));
            vParam.add(new Parameter(f.getMa_nh(), true));
            vParam.add(new Parameter(f.getMa_nt(), true));
            vParam.add(new Parameter(f.getId(), true));

            List listNgayTrienKhai = (List)dao.getLstNTrKhai(strWhere, vParam);
            if (listNgayTrienKhai.size() > 0) {
                throw new Exception(" Ngày triển khai " +
                                    f.getNgay_trien_khai() +
                                    " hiện đã tồn tại với các mã tương ứng khác");
            }

            NgayTrienKhaiVO vo = new NgayTrienKhaiVO();
            vo.setId((f.getId()));
            vo.setMa_kb(f.getMa_kb());
            vo.setMa_nh(f.getMa_nh());
            vo.setMa_nt(f.getMa_nt());
            vo.setNgay_trien_khai(f.getNgay_trien_khai());
            vo.setGhi_chu(f.getGhi_chu());
            //Update CSDL
            long ta = dao.update(vo);
            if (ta < 1) {
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

    public ActionForward add(ActionMapping mapping, ActionForm actionForm,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {

            conn = getConnection(request);
            NgayTrienKhaiForm f = (NgayTrienKhaiForm)actionForm;
            f.setAction_status(null);
            f.setGhi_chu(null);
            f.setId(null);
            f.setMa_kb(null);
            f.setMa_nh(null);
            f.setMa_nt(null);

            NgayTrienKhaiDAO dao = new NgayTrienKhaiDAO(conn);
            String strWhere = "";
            Vector vParam = new Vector();
            List listNgayTrienKhai = (List)dao.getLstNTrKhai(strWhere, vParam);
            DMTienTeDAO dmTien = new DMTienTeDAO(conn);
            ArrayList<DMTienTeVO> lstLoaiTien =
                (ArrayList<DMTienTeVO>)dmTien.getDMTienTeList(null, null);

            request.setAttribute("listNgayTrienKhai", listNgayTrienKhai);
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
            NgayTrienKhaiForm f = (NgayTrienKhaiForm)form;
            NgayTrienKhaiDAO dao = new NgayTrienKhaiDAO(conn);
            f.setAction_status(null);


            //Kiem tra ton tai
            String strWhere =
                " and (ma_kb = ? or ma_nh = ?) and ma_nt = ? and ngay_trien_khai = TO_DATE(?,'dd/mm/yyyy') ";
            Vector vParam = new Vector();
            vParam.add(new Parameter(f.getMa_kb(), true));
            vParam.add(new Parameter(f.getMa_nh(), true));
            vParam.add(new Parameter(f.getMa_nt(), true));
            vParam.add(new Parameter(f.getNgay_trien_khai(), true));
            List listNgayTrienKhai = (List)dao.getLstNTrKhai(strWhere, vParam);

            //Lay danh sach ma tien te
            DMTienTeDAO dmTien = new DMTienTeDAO(conn);
            ArrayList<DMTienTeVO> lstLoaiTien =
                (ArrayList<DMTienTeVO>)dmTien.getDMTienTeList(null, null);
            
            request.setAttribute("listNgayTrienKhai", listNgayTrienKhai);
            request.setAttribute("lstLoaiTien", lstLoaiTien);

            if (listNgayTrienKhai.size() > 0) {
                f.setAction_status(" Nhóm mã kho bạc " + f.getMa_kb() +
                                   " và mã ngân hàng " + f.getMa_nh() +
                                    "  đã tồn tại.");

                return mapping.findForward("success");
            }
            //****
            NgayTrienKhaiVO vo = new NgayTrienKhaiVO();

            vo.setMa_kb(f.getMa_kb());
            vo.setMa_nh(f.getMa_nh());
            vo.setMa_nt(f.getMa_nt());
            vo.setNgay_trien_khai(f.getNgay_trien_khai());
            vo.setGhi_chu(f.getGhi_chu());
            //Insert data
            long ta = dao.insert(vo);
            if (1 < ta) {
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

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            NgayTrienKhaiForm f = (NgayTrienKhaiForm)form;
            NgayTrienKhaiDAO dao = new NgayTrienKhaiDAO(conn);
            NgayTrienKhaiVO vo = new NgayTrienKhaiVO();
            vo.setId(f.getId());
            vo.setMa_kb(f.getMa_kb());
            vo.setMa_nh(f.getMa_nh());
            vo.setMa_nt(f.getMa_nt());
            vo.setGhi_chu(f.getGhi_chu());
            vo.setNgay_trien_khai(f.getNgay_trien_khai());

            long ta;
            ta = dao.delete(vo);
            if (ta >= 1) {
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
