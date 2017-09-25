package com.seatech.ttsp.dts.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;
import com.seatech.ttsp.dts.form.DTSoatForm;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;

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

  /**
    * @modify: HungBM
    * @modify date:08/06/2017
    * @see: Thay doi cau query de lay them trang thai 15 cho dien tra soat - da gui va chua co phan hoi 
    * @Find: HungBM-20170608
    */

   /**
     * @modify: ThuongDT
     * @modify date:12/07/2017
     * @see: bo sung trang thai 15
     * @Find: thuongdt-20170712
     */
   
public class resendDTSAction extends AppAction {
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
          DTSoatForm frm = (DTSoatForm)form;
          List lstTrangThai = null;
          MaThamChieuDAO thamchieuDAO = new MaThamChieuDAO(conn);
        //HungBM-20170608 - BEGIn
          String strWhere = 
              "a.rv_domain in ( ?,? )and rv_low_value in ('03','18','04','19','15') and rv_high_value <> '07' "; // '03,16' truyen lai, update trang thai
          Vector vParam = new Vector();
          vParam.add(new Parameter(AppConstants.MA_THAM_CHIEU_TRANG_THAI_DTS,
                                   true));
          vParam.add(new Parameter(AppConstants.STATE_DTS_HOI_TU_NHTM,
                                 true));
        //HungBM-20170608 - END
          lstTrangThai =
                  (List)thamchieuDAO.getMaThamChieuList(strWhere, vParam);

          request.setAttribute("lstTrangThai", lstTrangThai);
          
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
        vParamsKB.add(new Parameter(frm.getKb_tinh(), true));
        lstKBHuyen =
                (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                         vParamsKB);

        request.setAttribute("lstKBTinh", lstKBTinh);
        request.setAttribute("lstKBHuyen", lstKBHuyen);
          
          
          
          //tra ve danh sach tim kiem va phan trang
          DTSoatDAO dao = new DTSoatDAO(conn);

          String page = frm.getPageNumber();
          if (page == null) {
              page = "1";
          }
          // khai bao cac bien phan trang
          Integer currentPage = new Integer(page);
          Integer numberRowOnPage = new Integer(30);
          Integer totalCount[] = new Integer[1];
          // khai bao cac bien tuong tac voi tang DAO
          String whereClause = "";
//          Vector v_Param_ltt = new Vector();
          Vector params = new Vector();;


        if (frm.getKb_tinh() != null && !"".equals(frm.getKb_tinh())) {
            if (frm.getKb_huyen() != null &&
                !"".equals(frm.getKb_huyen())) {
                String strWhereNHKB =
                    " (select id from sp_dm_ngan_hang where ma_nh = (select ma_nh from sp_dm_manh_shkb where shkb=?)) ";
                whereClause +=
                        " and (a.nhkb_nhan = " + strWhereNHKB + " or a.nhkb_chuyen= " +
                        strWhereNHKB + ")";
                params.add(new Parameter(frm.getKb_huyen(), true));
                params.add(new Parameter(frm.getKb_huyen(), true));
            } else {
                String strWhereNHKBTinh =
                    "(select id from sp_dm_ngan_hang where ma_nh in((select ma_nh from sp_dm_manh_shkb " +
                    "where shkb in(select ma from sp_dm_htkb where ma_cha=?))))";
                whereClause +=
                        " and (a.nhkb_nhan in(" + strWhereNHKBTinh +
                        ")" + " or a.nhkb_chuyen in(" +
                        strWhereNHKBTinh + "))";
                params.add(new Parameter(frm.getKb_tinh(), true));
                params.add(new Parameter(frm.getKb_tinh(), true));
            }
        }
        if (frm.getMsg_id() != null &&
            !"".equals(frm.getMsg_id())) {
            whereClause += " and a.msg_id = ?";
            params.add(new Parameter(frm.getMsg_id(), true));
        }
          
        if (frm.getNgay_nhan() != null &&
            !"".equals(frm.getNgay_nhan())) {
            whereClause += " and trunc(a.ngay_nhan) = to_date(?,'DD-MM-YYYY')";
            params.add(new Parameter(frm.getNgay_nhan(), true));
        }

        if (frm.getSo_tchieu() != null &&
            !"".equals(frm.getSo_tchieu())) {
            whereClause += " and a.so_tchieu = ?";
            params.add(new Parameter(frm.getSo_tchieu(), true));
        }
        if (frm.getLtt_id() != null &&
            !"".equals(frm.getLtt_id())) {
            whereClause += " and a.ltt_id = ?";
            params.add(new Parameter(frm.getLtt_id(), true));
        } 
          
        if (frm.getTrang_thai() != null &&
            !"".equals(frm.getTrang_thai())) {
            whereClause += " and a.trang_thai = ?";
            params.add(new Parameter(frm.getTrang_thai(), true));
        }
        if ((frm.getTrang_thai() == null ||
            "".equals(frm.getTrang_thai())) && !"".equals(whereClause)) {
            //thuongdt-20170712 bo sung them trang thai 15
            whereClause += " and a.trang_thai in ('03','04','18','19','15')";//in ('03','11','13','16')
//            params.add(new Parameter(frm.getTrang_thai(), true));
        }

        
//
//          //          /**
//          //           * Kiem tra TTTT
//          //           * 1. Hien thi tat ca neu la TTTT
//          //           * 2. Chi hien thi ban ghi don vi
//          //           * @param:strNHKB_code
//          //           * */
//          //
//          //
          
         if (whereClause.length()<=0 ||
                "".equals(whereClause)) {
              request.setAttribute("lstLTT", null);
              return mapping.findForward("success");   
            }
          
          whereClause += " and substr(a.id,6,3) in ('195','196','199')";
        ArrayList<DTSoatVO> lstLTT =
            (ArrayList<DTSoatVO>)dao.getDTSTQByConditionSearch(whereClause,
                                                                  params,
                                                                  currentPage,
                                                                  numberRowOnPage,
                                                                  totalCount);
          PagingBean pagingBean = new PagingBean();
          pagingBean.setCurrentPage(currentPage);
          pagingBean.setNumberOfRow(totalCount[0].intValue());
          pagingBean.setRowOnPage(numberRowOnPage);
          request.setAttribute("PAGE_KEY", pagingBean);
          request.setAttribute("lstLTT", lstLTT);
//          //          /** neu la trung tam thanh toan thi lay ra toan bo kb tinh de lua chon
//          //            * Lay ra danh sach Kho bac tinh
//          //            **/
          
      } catch (Exception ex) {
          throw new Exception("Tra Cuu LTT: " + ex);
      } finally {
          close(conn);
      }
      return mapping.findForward("success");
  }
}
