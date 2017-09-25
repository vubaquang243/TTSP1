package com.seatech.ttsp.quyettoan.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.quyettoan.QuyetToanDAO;
import com.seatech.ttsp.quyettoan.QuyetToanVO;
import com.seatech.ttsp.quyettoan.form.QuyetToanForm;
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


public class resendLQTAction extends AppAction {
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
            QuyetToanForm frm = (QuyetToanForm)form;
            List lstTrangThai = null;
            MaThamChieuDAO thamchieuDAO = new MaThamChieuDAO(conn);
            String strWhere =
                "a.rv_domain = ? and rv_low_value in ('03','11','13','16')"; // '03,16' truyen lai, update trang thai
            Vector vParam = new Vector();
            vParam.add(new Parameter(AppConstants.MA_THAM_CHIEU_TRANG_THAI_QT,
                                     true));
            lstTrangThai =
                    (List)thamchieuDAO.getMaThamChieuList(strWhere, vParam);

            request.setAttribute("lstTrangThai", lstTrangThai);
            //tra ve danh sach tim kiem va phan trang
            QuyetToanDAO dao = new QuyetToanDAO(conn);
            List<QuyetToanVO> lstLTT = null;

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
            Vector v_Param_ltt = new Vector();
            Vector params = null;


            //          /*
            //           * @sea:kiem tra gia tri cua form va set gia tri cho vector
            //           * ttv_nhan,trang_thai,loai_lenh_tt,tong_sotien,NH_chuyen,NH_nhan,ngay_nhap,so YCTT,SLTT
            //           *             String strFromDate = lttForm.getNgay_nhap_nh();
            //                          String strToDate = lttForm.getNgay_nhap_nh();
            //           * */
            String actionBack = null;
            //                QuyetToanToanQuocForm f = (QuyetToanToanQuocForm)form;
            if (request.getParameter(AppConstants.REQUEST_ACTION) != null) {
                actionBack =
                        request.getParameter(AppConstants.REQUEST_ACTION).toString();
            }
            if (actionBack != null && actionBack.equalsIgnoreCase("Back")) {
                String hsc_nh_back = request.getParameter("hsc_nh_back");
                String kb_tinh_back = request.getParameter("kb_tinh_back");
                String kb_huyen_back = request.getParameter("kb_huyen_back");
                String ngay_qt_back = request.getParameter("ngay_qt_back");
                String ngay_tt_back = request.getParameter("ngay_tt_back");
                String loai_qt_back = request.getParameter("loai_qt_back");
                String pt_qt_back = request.getParameter("pt_qt_back");

                if (kb_tinh_back != null && !"".equals(kb_tinh_back)) {
                    frm.setKb_tinh(kb_tinh_back);
                } else {
                    frm.setKb_tinh(null);
                }
                if (kb_huyen_back != null && !"".equals(kb_huyen_back)) {
                    frm.setKb_huyen(kb_huyen_back);
                } else {
                    frm.setKb_huyen(null);
                }
                if (ngay_qt_back != null && !"".equals(ngay_qt_back)) {
                    frm.setNgay_qtoan(ngay_qt_back);
                } else {
                    frm.setNgay_qtoan(null);
                }

                if (loai_qt_back != null && !"".equals(loai_qt_back)) {
                    frm.setLoai_qtoan(loai_qt_back);
                } else {
                    frm.setLoai_qtoan(null);
                }

            }
            
            
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
            
            
            
              
            
            params = new Vector();
          
            if (frm.getMsg_id() != null && !"".equals(frm.getMsg_id())) {
                if (whereClause != null && !"".equals(whereClause)) {
                    whereClause += " and a.msg_id = ?";
                } else {
                    whereClause = " a.msg_id = ?";
                }
                params.add(new Parameter(frm.getMsg_id(), true));
            }
            if (frm.getKb_tinh() != null && !"".equals(frm.getKb_tinh())) {
                if (frm.getKb_huyen() != null &&
                    !"".equals(frm.getKb_huyen())) {
                    if (whereClause != null && !"".equals(whereClause)) {
                        whereClause +=
                                " and a.ma_kb = (select ma_nh From sp_dm_manh_shkb where shkb=?)  ";
                    } else {
                        whereClause =
                                " a.ma_kb = (select ma_nh From sp_dm_manh_shkb where shkb=?) ";
                    }
                    params.add(new Parameter(frm.getKb_huyen(), true));
                } else {
                    if (whereClause != null && !"".equals(whereClause)) {
                        whereClause +=
                                " and a.ma_kb in ( select ma_nh from sp_dm_manh_shkb where SHKB in(select ma from sp_dm_htkb where (ma_cha = ? or ma = ?))) ";
                    } else {
                        whereClause =
                                " a.ma_kb in ( select ma_nh from sp_dm_manh_shkb where SHKB in(select ma from sp_dm_htkb where (ma_cha = ? or ma = ?))) ";
                    }
                    params.add(new Parameter(frm.getKb_tinh(), true));
                    params.add(new Parameter(frm.getKb_tinh(), true));
                }
            }
            if (frm.getLoai_qtoan() != null &&
                !"".equals(frm.getLoai_qtoan())) {
                if (whereClause != null && !"".equals(whereClause)) {
                    // phi TT - 01 : lai_phi=='02' ,loai_qtoan=='D'
                    // lai sdtk - 02 : lai_phi=='02' ,loai_qtoan=='C'
                    if (frm.getLoai_qtoan().equals("01")) {
                        whereClause +=
                                " and a.loai_qtoan = 'D' ";
                    } else if (frm.getLoai_qtoan().equals("02")) {
                        whereClause +=
                                " and a.loai_qtoan = 'C' ";
                    } else {
                        whereClause +=
                                " and a.loai_qtoan = ? ";
                        params.add(new Parameter(frm.getLoai_qtoan(), true));
                    }
                } else {
                    if (frm.getLoai_qtoan().equals("01")) {
                        whereClause +=
                                " a.loai_qtoan = 'D' and a.lai_phi in ('01','03') ";
                    } else if (frm.getLoai_qtoan().equals("02")) {
                        whereClause +=
                                " a.loai_qtoan = 'C' and a.lai_phi in ('01','03') ";
                    } else {
                        whereClause += " a.loai_qtoan = ? and a.lai_phi in ('01','03')";
                        params.add(new Parameter(frm.getLoai_qtoan(), true));
                    }
                }
            }
            if (frm.getNgay_qtoan() != null &&
                !"".equals(frm.getNgay_qtoan())) {
                if (whereClause != null && !"".equals(whereClause)) {
                    whereClause +=
                            " and trunc(a.ngay_qtoan) = to_date(?,'DD/MM/YYYY')";
                } else {
                    whereClause =
                            " trunc(a.ngay_qtoan) = to_date(?,'DD/MM/YYYY')";
                }
                params.add(new Parameter(frm.getNgay_qtoan(), true));
            }
            if (frm.getId() != null && !"".equals(frm.getId())) {
                if (whereClause != null && !"".equals(whereClause)) {
                    whereClause += " and a.id = ?";
                } else {
                    whereClause = " a.id = ?";
                }
                params.add(new Parameter(frm.getId(), true));
            }
            if (frm.getMa_tchieu_gd() != null &&
                !"".equals(frm.getMa_tchieu_gd())) {
                if (whereClause != null && !"".equals(whereClause)) {
                    whereClause += " and a.ma_tchieu_gd = ?";
                } else {
                    whereClause = " a.ma_tchieu_gd = ?";
                }
                params.add(new Parameter(frm.getMa_tchieu_gd(), true));
            }
          if (frm.getSo_tien()!= null &&
              !"".equals(frm.getSo_tien())) {
            if (whereClause != null && !"".equals(whereClause)) {
              whereClause += " and a.so_tien like '%"+ frm.getSo_tien().trim().replace(".", "") +"%'";
            } else {
              whereClause += " a.so_tien like '%"+ frm.getSo_tien().trim().replace(".", "") +"%'";
            }
             
          //              params.add(new Parameter(frm.getSo_tien().trim().replace(".", ""), true));
          }
          if (frm.getTrang_thai() != null &&
              !"".equals(frm.getTrang_thai())) {
            if (whereClause != null && !"".equals(whereClause)) {
                whereClause += " and a.trang_thai = ?";
            } else {
                whereClause = " a.trang_thai = ?";
            }
              params.add(new Parameter(frm.getTrang_thai(), true));
          }else if ((frm.getTrang_thai() == null ||
              "".equals(frm.getTrang_thai()))&& !"".equals(whereClause)) {
            if (whereClause != null && !"".equals(whereClause)) {
              whereClause += " and a.trang_thai in ('03','11','13','16')";
            } else {
              whereClause += "  a.trang_thai in ('03','11','13','16')";
            }
              //in ('03','11','13','16')
          //            params.add(new Parameter(frm.getTrang_thai(), true));
          }
          
          

            //          /**
            //           * Kiem tra TTTT
            //           * 1. Hien thi tat ca neu la TTTT
            //           * 2. Chi hien thi ban ghi don vi
            //           * @param:strNHKB_code
            //           * */
            //
            //
            
            if (whereClause.length()<=0 ||
                "".equals(whereClause)) {
              request.setAttribute("lstLTT", null);
              return mapping.findForward("success");   
            }
            
            lstLTT =
                    (List<QuyetToanVO>)dao.getTCuuLQTListWithPading(whereClause,
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
            //          /** neu la trung tam thanh toan thi lay ra toan bo kb tinh de lua chon
            //            * Lay ra danh sach Kho bac tinh
            //            **/
            
        } catch (Exception ex) {
            throw new Exception("Tra Cuu LTT: " + ex);
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
}
