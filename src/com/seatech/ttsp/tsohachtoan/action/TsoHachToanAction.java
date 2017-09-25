package com.seatech.ttsp.tsohachtoan.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.tsohachtoan.TsoHachToanDAO;
import com.seatech.ttsp.tsohachtoan.TsoHachToanVO;
import com.seatech.ttsp.tsohachtoan.form.TsoHachToanForm;

import java.sql.Connection;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TsoHachToanAction extends AppAction {
    public static final int STATUS_UNSUCCESS = 1;
    public static final int STATUS_SUCCESS = 2;
    public static final int STATUS_FALSE = 3;


    public ActionForward list(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {
            conn = getConnection(request);
            TsoHachToanForm f = (TsoHachToanForm)actionForm;
            TsoHachToanDAO dao = new TsoHachToanDAO(conn);
            String strWhere = "";
            Vector vParam = new Vector();
            // lấy biến từ form
             String strType = f.getType();

            if ("delete".equals(strType)) {
                f.setCap_ns(null);
                f.setChuong(null);
                f.setCtmt(null);
                f.setDbhc(null);
                f.setDu_phong(null);
                f.setDvsdns(null);
                f.setGhi_chu(null);
                f.setLoai_htoan(null);
                f.setMa_kb(null);
                f.setMa_nh(null);
                f.setMo_ta(null);
                f.setNdkt(null);
                f.setNguon(null);
                f.setQuy(null);
                f.setTktn(null);
                f.setNganh_kt(null);
            }
           

            String strMa_nh = f.getMa_nh();
            String strLoai_htoan = f.getLoai_htoan();
            String strTktn = f.getTktn();
            String strPageNumber = f.getPageNumber();
            //Build menh de where

            if (!"".equals(strMa_nh) && strMa_nh != null) {
                strWhere += " and ma_nh = ?";
                vParam.add(new Parameter(strMa_nh, true));
            }
            if (!"".equals(strLoai_htoan) && strLoai_htoan != null) {
                strWhere += " and UPPER (loai_htoan) like  UPPER('%" + strLoai_htoan + "%')";
            }
            if (!"".equals(strTktn) && strTktn != null){
                strWhere += "and tktn = ?";
                vParam.add(new Parameter(strTktn, true));
            }
            
            
            //Cac thong so phan trang
            if (strPageNumber == null)
                strPageNumber = "1";
            int nCurrentPage = Integer.parseInt(strPageNumber);
            int nNumberRowOnPage = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            Integer totalCount[] = new Integer[1];
            //Select DB
            List listTso =
                (List)dao.getLstTso(strWhere, vParam, nCurrentPage,
                                     nNumberRowOnPage, totalCount);
            //Set acttribute
            request.setAttribute("listTso", listTso);
            if (listTso == null) {
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
    
  public ActionForward add(ActionMapping mapping, ActionForm actionForm,
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
          TsoHachToanForm f = (TsoHachToanForm)form;
          TsoHachToanDAO dao = new TsoHachToanDAO(conn);
          f.setAction_status(null);
          
          //Kiem tra ton tai
          String strWhere =
              " and ma_nh =? and loai_htoan = ? ";
          Vector vParam = new Vector();
          vParam.add(new Parameter(f.getMa_nh(), true));
          vParam.add(new Parameter(f.getLoai_htoan(), true));
          List listTso = (List)dao.getLstTso(strWhere, vParam);
          if (listTso.size() > 0) {
              throw new Exception(" Nhóm mã ngân hàng " + f.getMa_nh() + " và loại hạch toán " + f.getLoai_htoan() + " đã tồn tại.");
          }
          //****
          TsoHachToanVO vo = new TsoHachToanVO();

          vo.setMa_nh(f.getMa_nh());
          vo.setLoai_htoan(f.getLoai_htoan());
          vo.setMo_ta(f.getMo_ta());
          vo.setTktn(f.getTktn());
          vo.setQuy(f.getQuy());
          vo.setDvsdns(f.getDvsdns());
          vo.setCap_ns(f.getCap_ns());
          vo.setChuong(f.getChuong());
          vo.setNganh_kt(f.getNganh_kt());
          vo.setNdkt(f.getNdkt());
          vo.setDbhc(f.getDbhc());
          vo.setCtmt(f.getCtmt());
          vo.setNguon(f.getNguon());
          vo.setMa_kb(f.getMa_kb());
          vo.setDu_phong(f.getDu_phong());
          vo.setGhi_chu(f.getGhi_chu());
          vo.setId(f.getId());
          
          
          //Insert data
          long ts = dao.insert(vo);
          if (1 < ts) {
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
          TsoHachToanForm f = (TsoHachToanForm)form;
          TsoHachToanDAO dao = new TsoHachToanDAO(conn);
          f.setAction_status(null);   

        TsoHachToanVO vo = new TsoHachToanVO();
        vo.setMa_nh(f.getMa_nh());
        vo.setLoai_htoan(f.getLoai_htoan());
        vo.setMo_ta(f.getMo_ta());
        vo.setTktn(f.getTktn());
        vo.setQuy(f.getQuy());
        vo.setDvsdns(f.getDvsdns());
        vo.setCap_ns(f.getCap_ns());
        vo.setChuong(f.getChuong());
        vo.setNganh_kt(f.getNganh_kt());
        vo.setNdkt(f.getNdkt());
        vo.setDbhc(f.getDbhc());
        vo.setCtmt(f.getCtmt());
        vo.setNguon(f.getNguon());
        vo.setMa_kb(f.getMa_kb());
        vo.setDu_phong(f.getDu_phong());
        vo.setGhi_chu(f.getGhi_chu());
        vo.setId(f.getId());
          
          //Update CSDL
          long ts = dao.update(vo);
          if (ts < 1) {
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
          TsoHachToanForm f = (TsoHachToanForm)form;
          TsoHachToanDAO dao = new TsoHachToanDAO(conn);
          TsoHachToanVO vo = new TsoHachToanVO();
        
        vo.setMa_nh(f.getMa_nh());
        vo.setLoai_htoan(f.getLoai_htoan());
        vo.setMo_ta(f.getMo_ta());
        vo.setTktn(f.getTktn());
        vo.setQuy(f.getQuy());
        vo.setDvsdns(f.getDvsdns());
        vo.setCap_ns(f.getCap_ns());
        vo.setChuong(f.getChuong());
        vo.setNganh_kt(f.getNganh_kt());
        vo.setNdkt(f.getNdkt());
        vo.setDbhc(f.getDbhc());
        vo.setCtmt(f.getCtmt());
        vo.setNguon(f.getNguon());
        vo.setMa_kb(f.getMa_kb());
        vo.setDu_phong(f.getDu_phong());
        vo.setGhi_chu(f.getGhi_chu());
        vo.setId(f.getId());
          long ts;
          ts = dao.delete(vo);
          if (ts >= 1) {
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
