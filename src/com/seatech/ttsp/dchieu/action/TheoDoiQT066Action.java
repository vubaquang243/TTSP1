package com.seatech.ttsp.dchieu.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.DNQTVO;
import com.seatech.ttsp.dchieu.form.TheoDoiQT066Form;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.tracuukhobac.TraCuuKhoBacDAO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TheoDoiQT066Action extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            DChieu1DAO dao = new DChieu1DAO(conn);
            DChieu1VO vo = new DChieu1VO();
            TraCuuKhoBacDAO traCuuDAO = new TraCuuKhoBacDAO(conn);
            //    int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            String strDC3 = "";
            //    if (page == null)
            //        page = "1";
            //    Integer currentPage = new Integer(page);
            //    Integer numberRowOnPage = phantrang;
            //    Integer totalCount[] = new Integer[15];

            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String kb_id =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

            List dmucNH = null;
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            dmucNH = (List)TTdao.getDMucNH(null,null);
            List dmTienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);
            request.setAttribute("dmucNH", dmucNH);
            request.setAttribute("dmTienTe", dmTienTe);

            String strCap = " and ma=" + kb_code;
            vo = dao.getCap(strCap, null);
            List dmuckb_cha = null;
            String cap = vo.getCap();
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                String strWhere = " AND cap = 5 ";
                dmuckb_cha = (List)traCuuDAO.getDMKHTinh(strWhere, null);
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
                request.setAttribute("dftinh", "dftinh");
                request.setAttribute("TTTT", "TTTT");
            } else if ("0003".equals(kb_code)) {
                String strWhere = " AND ma='0003' ";
                dmuckb_cha = (List)traCuuDAO.getDMKHTinh(strWhere, null);
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
            } else if ("5".equals(cap)) {
                String strWhere = "";
                strWhere += " and ma=" + kb_code;
                dmuckb_cha = (List)traCuuDAO.getDMKHTinh(strWhere, null);

                request.setAttribute("dmuckb_tinh", dmuckb_cha);
            } else {
                String strWhere = "";
                strWhere +=
                        " and id in (select id_cha from sp_dm_htkb where ma=" +
                        kb_code + ")";
                dmuckb_cha = (List)traCuuDAO.getDMKHTinh(strWhere, null);
                request.setAttribute("dmuckb_tinh", dmuckb_cha);
            }
            PagingBean pagingBean = new PagingBean();
            request.setAttribute("PAGE_KEY", pagingBean);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            DChieu1DAO dao = new DChieu1DAO(conn);
            DChieu1VO vo = new DChieu1VO();
            DNQTVO dnqtVO = new DNQTVO();
            Collection colLst066 = new ArrayList();
            TraCuuKhoBacDAO traCuuDAO = new TraCuuKhoBacDAO(conn);
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String kb_id =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            TheoDoiQT066Form frm = (TheoDoiQT066Form)form;
            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            String page = frm.getPageNumber();
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];
            List dmucNH = null;
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            dmucNH = (List)TTdao.getDMucNH(null,null);
            List dmTienTe = tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);
            request.setAttribute("dmTienTe", dmTienTe);
            request.setAttribute("dmucNH", dmucNH);

            String strCap = " and ma=" + kb_code;
            vo = dao.getCap(strCap, null);
            List dmuckb_cha = null;
            String cap = vo.getCap();
              if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                  String strWhere = " AND cap = 5 ";
                  dmuckb_cha = (List)traCuuDAO.getDMKHTinh(strWhere, null);
                  request.setAttribute("dmuckb_tinh", dmuckb_cha);
                  request.setAttribute("dftinh", "dftinh");
                  request.setAttribute("TTTT", "TTTT");
              } else if ("0003".equals(kb_code)) {
                  String strWhere = " AND ma='0003' ";
                  dmuckb_cha = (List)traCuuDAO.getDMKHTinh(strWhere, null);
                  request.setAttribute("dmuckb_tinh", dmuckb_cha);
              } else if ("5".equals(cap)) {
                  String strWhere = "";
                  strWhere += " and ma=" + kb_code;
                  dmuckb_cha = (List)traCuuDAO.getDMKHTinh(strWhere, null);

                  request.setAttribute("dmuckb_tinh", dmuckb_cha);
              } else {
                  String strWhere = "";
                  strWhere +=
                          " and id in (select id_cha from sp_dm_htkb where ma=" +
                          kb_code + ")";
                  dmuckb_cha = (List)traCuuDAO.getDMKHTinh(strWhere, null);
                  request.setAttribute("dmuckb_tinh", dmuckb_cha);
              }

            String nhkb_tinh =
                frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
            String nhkb_huyen =
                frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
            String tu_ngay = frm.getTu_ngay() == null ? "" : frm.getTu_ngay();
            String den_ngay =
                frm.getDen_ngay() == null ? "" : frm.getDen_ngay();
            String ma_dv =
                frm.getMa_dv() == null ? "" : frm.getMa_dv();
          String loai_qt =
              frm.getLoai_qt() == null ? "" : frm.getLoai_qt();
            String tthai_dxn =
                frm.getTthai_dxn() == null ? "" : frm.getTthai_dxn();
            String tthai_qt =
                frm.getTthai_qt() == null ? "" : frm.getTthai_qt();
          String so_dnqt =
              frm.getId() == null ? "" : frm.getId();
            String loai_tien = frm.getLoai_tien() == null ? "" : frm.getLoai_tien();
            String so_tien = frm.getSo_tien() == null ? "" : frm.getSo_tien();
            String soTien = "";
            if(!so_tien.equals(""))
            if(loai_tien.equals("VND")){
                soTien = so_tien.replace(".", "");
            }else{
              soTien = so_tien.replace(",", "");
            }
            String strNgayQT = "";
            String strLst066 = "";
            String char_dngay = "";
            String char_tngay = "";
            
          String inKB = request.getParameter("inKB");
          String inNH = request.getParameter("inNH");
            
//            if (ma_nh != null && !"".equals(ma_nh)) {
//                strLst066 += " AND a.nhkb_nhan='" + ma_nh + "'";
//            }
            if (nhkb_tinh != null && !"".equals(nhkb_tinh) && (nhkb_huyen == null || "".equals(nhkb_huyen))) {
                strLst066 += " AND d.id_cha=" + nhkb_tinh;
            }
            if (loai_tien != null && !"".equals(loai_tien)) {
                strLst066 += " AND a.loai_tien='" + loai_tien+"'";
            }
            if(soTien != null && !soTien.equals("")){
                strLst066 += " AND (a.qtoan_chi="+ soTien +" OR a.qtoan_thu="+soTien+")";
            }
          if (ma_dv != null && !"".equals(ma_dv)) {
              strLst066 += " and substr(a.nhkb_nhan,3,3) = '" + ma_dv + "'";
          }
            if (so_dnqt != null && !"".equals(so_dnqt)) {
                strLst066 += " AND a.id like '%" + so_dnqt+"%'";
            }
            if (nhkb_huyen != null && !"".equals(nhkb_huyen)) {
                strLst066 += " AND d.id=" + nhkb_huyen;
            }
            if (tthai_dxn != null && !"".equals(tthai_dxn)) {
                strLst066 += " AND a.trang_thai=" + tthai_dxn;
            }
            if (loai_qt != null && !"".equals(loai_qt)) {
                strLst066 += " AND a.loai_qtoan=" + loai_qt;
            }
            if (tthai_qt != null && !"".equals(tthai_qt)) {
                strLst066 += " AND a.trang_thai_qtoan=" + tthai_qt;
            }
            if (den_ngay != null && !"".equals(den_ngay)) {
                char_dngay =
                        StringUtil.DateToString(StringUtil.StringToDate(den_ngay,
                                                                        "dd/MM/yyyy"),
                                                "yyyy/MM/dd").replace("/", "");
            }
            if (tu_ngay != null && !"".equals(tu_ngay)) {
                char_tngay =
                        StringUtil.DateToString(StringUtil.StringToDate(tu_ngay,
                                                                        "dd/MM/yyyy"),
                                                "yyyy/MM/dd").replace("/", "");
            }

            if (tu_ngay != null && !"".equals(tu_ngay) && den_ngay != null &&
                !"".equals(den_ngay)) {
                strNgayQT +=
                        " and (to_char(a.ngay_qtoan,'YYYYMMDD') >=  '" + char_tngay +
                        "' and to_char(a.ngay_qtoan,'YYYYMMDD') <=  '" +
                        char_dngay + "') ";
            }
            if ((den_ngay == null || "".equals(den_ngay)) && tu_ngay != null &&
                !"".equals(tu_ngay)) {
                strNgayQT +=
                        " and (to_char(a.ngay_qtoan,'YYYYMMDD') <=  to_char(sysdate,'YYYYMMDD') and to_char(a.ngay_qtoan,'YYYYMMDD') >=  '" +
                        char_tngay + "') ";
            } else if (den_ngay != null && !"".equals(den_ngay) &&
                       (tu_ngay == null || "".equals(tu_ngay))) {
                strNgayQT +=
                        " and to_char(a.ngay_qtoan,'YYYYMMDD') <= '" + char_dngay +
                        "'";
            }
          
            strLst066 +=  strNgayQT;

            colLst066 =
                    dao.getLst066(strLst066,  null, currentPage, numberRowOnPage,
                                  totalCount);
            request.setAttribute("colLst066", colLst066);
            request.setAttribute("kb_huyen", inKB);
            request.setAttribute("ngan_hang", inNH);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(15);
            request.setAttribute("PAGE_KEY", pagingBean);

        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
}
