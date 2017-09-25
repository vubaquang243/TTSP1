package com.seatech.ttsp.quyettoan.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.quyettoan.QuyetToanDAO;
import com.seatech.ttsp.quyettoan.form.BKE_QuyetToanForm;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class resendBKLQTAction extends AppAction {
  public ActionForward list(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
      Connection conn = null;
      try {
          conn = getConnection(request);

          //          LTTForm lttForm = (LTTForm)form;
          //          if (lttForm.getTu_ngay() == null ||
          //              "".equals(lttForm.getTu_ngay())) {
          //              lttForm.setTu_ngay(StringUtil.DateToString(new Date(),
          //                                                         "dd/MM/yyyy"));
          //          }
          /*
         * MaThamChieuDAO
         * lstTrangThai
         * luu tru cac gia tri cua trang thai
         * */
          HttpSession session = request.getSession();
          BKE_QuyetToanForm frm = (BKE_QuyetToanForm)form;

          QuyetToanDAO dao = new QuyetToanDAO(conn);
          Collection colTCuu = null;

          String page = frm.getPageNumber();
          if (page == null) {
              page = "1";
          }
          // khai bao cac bien phan trang
          Integer currentPage = new Integer(page);
          Integer numberRowOnPage = new Integer(30);
          Integer totalCount[] = new Integer[1];

          String actionBack = null;
          //                QuyetToanToanQuocForm f = (QuyetToanToanQuocForm)form;
          if (request.getParameter(AppConstants.REQUEST_ACTION) != null) {
              actionBack =
                      request.getParameter(AppConstants.REQUEST_ACTION).toString();
          }
          
                  
          
        String bk_id = frm.getId() == null ? "" : frm.getId();
//        String tthai =
//            frm.getTrang_thai() == null ? "" : frm.getTrang_thai();

        String strTCuu = " AND trang_thai='02'";

        if (bk_id != null && !"".equals(bk_id)) {
            strTCuu += " AND UPPER(ID) LIKE UPPER('%" + bk_id + "%')";
        }
        if (frm.getNgay_qtoan() == null ||
            "".equals(frm.getNgay_qtoan())) {
            frm.setTcg_ngan_hang(StringUtil.DateToString(new Date(),
                                                       "dd/MM/yyyy"));
        }else if(frm.getNgay_qtoan()!= null ||
            !"".equals(frm.getNgay_qtoan())){
          strTCuu +="and a.tcg_ngay_qtoan >=  to_date('" + frm.getNgay_qtoan() + "','DD/MM/YYYY')" ;
        }

        colTCuu = dao.getTCuuBKe_ptrang(strTCuu, null, currentPage, numberRowOnPage,
                                      totalCount);
          PagingBean pagingBean = new PagingBean();
          pagingBean.setCurrentPage(currentPage);
          pagingBean.setNumberOfRow(totalCount[0].intValue());
          pagingBean.setRowOnPage(numberRowOnPage);
          request.setAttribute("PAGE_KEY", pagingBean);
          request.setAttribute("lstLTT", colTCuu);
          //          /** neu la trung tam thanh toan thi lay ra toan bo kb tinh de lua chon
          //            * Lay ra danh sach Kho bac tinh
          //            **/
          String strCapTinh = "5";
          String ma_kb_so_tai =
              (String)session.getAttribute(AppConstants.APP_KB_CODE_SESSION);
          String maSGD = "0003";
          Vector vParamsKB = null;
          String strWhereClauseKB = "";
          DMKBacDAO kbDAO = new DMKBacDAO(conn);
          ArrayList<DMKBacVO> lstKBTinh = new ArrayList<DMKBacVO>();
          ArrayList<DMKBacVO> lstKBHuyen = new ArrayList<DMKBacVO>();

          request.setAttribute("MAT4", ma_kb_so_tai);
          vParamsKB = new Vector();
          strWhereClauseKB = "a.cap=?  or a.ma='" + maSGD + "' ";
          vParamsKB.add(new Parameter(strCapTinh, true));
          lstKBTinh =
                  (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                           vParamsKB);
          vParamsKB = new Vector();
          strWhereClauseKB = " a.ma_cha=? ";
          vParamsKB.add(new Parameter(frm.getTcg_kb_tinh(), true));
          lstKBHuyen =
                  (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                           vParamsKB);

          request.setAttribute("lstKBTinh", lstKBTinh);
          request.setAttribute("lstKBHuyen", lstKBHuyen);
      } catch (Exception ex) {
          throw new Exception("Tra Cuu LTT: " + ex);
      } finally {
          close(conn);
      }
      return mapping.findForward("success");
  }
}
