
package com.seatech.ttsp.dmmanhshkb.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmmanhshkb.form.DMMaNHangSHKBDAO;
import com.seatech.ttsp.dmmanhshkb.form.DMMaNHangSHKBVO;

import java.sql.Connection;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DMMaNHangSHKBAction extends AppAction {
    public static final int STATUS_UNSUCCESS = 1;
    public static final int STATUS_SUCCESS = 2;
    public static final int STATUS_FALSE = 3;


    public DMMaNHangSHKBAction() {
        super();
    }


    public ActionForward list(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {
            conn = getConnection(request);
            DMMaNHangSHKBForm f = (DMMaNHangSHKBForm)actionForm;
            DMMaNHangSHKBDAO dmDataAccess = new DMMaNHangSHKBDAO(conn);
            String strWhere = "";
            Vector vParam = new Vector();
            //Lay bien tu form
            String strType = f.getType();

            if ("delete".equals(strType)) {
                f.setShkb(null);
                f.setMa_nh(null);
                f.setTen(null);
            }
            String strShkb = f.getShkb();
            String strTen = f.getTen();
            String strMaNH = f.getMa_nh();
            String strPageNumber = f.getPageNumber();
            //Build menh de where
            if (!"".equals(strTen) && strTen != null) {
                strWhere += " and ten like '%" + strTen + "%'";
            }
            if (!"".equals(strShkb) && strShkb != null) {
                strWhere += " and shkb = ?";
                vParam.add(new Parameter(strShkb, true));
            }
            if (!"".equals(strMaNH) && strMaNH != null) {
                strWhere += " and ma_nh = ?";
                vParam.add(new Parameter(strMaNH, true));
            }
            //Cac thong so phan trang
            if (strPageNumber == null)
                strPageNumber = "1";
            int nCurrentPage = Integer.parseInt(strPageNumber);
            int nNumberRowOnPage = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            Integer totalCount[] = new Integer[1];
            //Select DB
            List listDMSHKBac =
                (List)dmDataAccess.getLstDMuc(strWhere, vParam, nCurrentPage,
                                              nNumberRowOnPage, totalCount);
            //Set acttribute
            request.setAttribute("listDMSHKBac", listDMSHKBac);
            if (listDMSHKBac == null) {
                PagingBean pagingBean = new PagingBean();
                request.setAttribute("PAGE_KEY", pagingBean);
            } else {
                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(nCurrentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(nNumberRowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);
            }
            return mapping.findForward("success");
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }
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
            DMMaNHangSHKBForm f = (DMMaNHangSHKBForm)form;
            DMMaNHangSHKBDAO dmDataAccess = new DMMaNHangSHKBDAO(conn);
            f.setAction_status(null);
            
            //Kiem tra ton tai
            String strWhere = " and (ma_nh = ? or shkb = ?)";
            Vector vParam = new Vector();
            vParam.add(new Parameter(f.getMa_nh(), true));
            vParam.add(new Parameter(f.getShkb(), true));
            List listDMSHKBac =
                (List)dmDataAccess.getLstDMuc(strWhere, vParam);
            if (listDMSHKBac.size() > 0) {
                throw new Exception("Mã ngân hàng " + f.getMa_nh() +
                                    " hoặc số hiệu kho bạc " + f.getShkb() +
                                    "  đã được gắn với các mã tương ứng khác.");
            }
            //****
            DMMaNHangSHKBVO vo = new DMMaNHangSHKBVO();

            vo.setShkb(f.getShkb());
            vo.setMa_nh(f.getMa_nh());
            vo.setTen(f.getTen());
            //Insert data
            long iu = dmDataAccess.insert(vo);
            if (1 < iu) {
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
            DMMaNHangSHKBForm f = (DMMaNHangSHKBForm)form;
            DMMaNHangSHKBDAO dmDataAccess = new DMMaNHangSHKBDAO(conn);
            f.setAction_status(null);

            // kiểm tra tồn tại
            String strWhere = " and ma_nh = ? and shkb <> ? ";
            Vector vParam = new Vector();
            vParam.add(new Parameter(f.getMa_nh(), true));
            vParam.add(new Parameter(f.getShkb(),true));
            List listDMSHKBac =
                (List)dmDataAccess.getLstDMuc(strWhere, vParam);
            if (listDMSHKBac.size() > 0) {
                throw new Exception("Mã ngân hàng " + f.getMa_nh() +
                                    " đã được gắn với SHKB tương ứng khác.");
            }

            DMMaNHangSHKBVO vo = new DMMaNHangSHKBVO();
            vo.setTen(f.getTen());
            vo.setMa_nh(f.getMa_nh());
            vo.setShkb(f.getShkb());
            //Update CSDL
            long iu = dmDataAccess.update(vo);
            if (iu < 1) {
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
            DMMaNHangSHKBForm f = (DMMaNHangSHKBForm)form;
            DMMaNHangSHKBDAO dmDataAccess = new DMMaNHangSHKBDAO(conn);
            DMMaNHangSHKBVO vo = new DMMaNHangSHKBVO();
            vo.setShkb(f.getShkb());
            vo.setMa_nh(f.getMa_nh());
            vo.setTen(f.getTen());

            long iu;
            iu = dmDataAccess.delete(vo);
            if (iu >= 1) {
                f.setAction_status("Xóa thành công danh mục mã NH: "+f.getMa_nh()+" và SHKB: "+f.getShkb());
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


