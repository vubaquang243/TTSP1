package com.seatech.ttsp.duyetLTT.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.AppKeys;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.duyetLTT.duyetLTTDAO;
import com.seatech.ttsp.duyetLTT.duyetLTTVO;
import com.seatech.ttsp.duyetLTT.form.duyetLTTTinhForm;
import com.seatech.ttsp.logduyetlo.dao.LogDuyetLoDAO;
import com.seatech.ttsp.logduyetlo.vo.LogDuyetLoVO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class duyetLTTTinhAction extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();

            duyetLTTDAO lttDAO = new duyetLTTDAO(conn);
            DChieu1DAO dcDAO = new DChieu1DAO(conn);
            duyetLTTTinhForm frm = (duyetLTTTinhForm)form;
            List dmucNH = null;
            List dmuckb_cha = null;
            Collection colLTT = null;
            Collection colMonTien = null;

            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String kb_id =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            dmucNH = (List)TTdao.getDMucNH(null,null);
            request.setAttribute("dmucNH", dmucNH);

            String strWhere = "";
            strWhere += " and a.ma=" + kb_code;
            dmuckb_cha = (List)dcDAO.getDMucKB_cha(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            String page = frm.getPageNumber();
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];
            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);

            request.setAttribute("chuc_danh", strUserInfo);

            String strlstLTT = " ";
            strlstLTT += " AND t.trang_thai_tw='01'";
            String so_ltt = frm.getSo_ltt() == null ? "" : frm.getSo_ltt();

            String tien_max = frm.getTien_max();
            String tien_min = frm.getTien_min();
            String ma_dv = frm.getMa_dv() == null ? "" : frm.getMa_dv();
            String kb_tinh = frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
            String kb_huyen = frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
            String idxKB_huyen = request.getParameter("idxKB");


            if (kb_tinh != null && !"".equals(kb_tinh)) {
                strlstLTT += " AND (d.id_cha = " + kb_tinh + " OR d.id =" + kb_tinh + ")";
            } else if (kb_tinh == null || "".equals(kb_tinh)) {
//                strlstLTT +=
//                        " AND (d.id_cha = " + kb_id + " OR d.id =" + kb_id +
//                        ")";
              strlstLTT +=
                      " AND (d.id_cha = " + kb_id + " OR d.id =" + kb_id +
                      " OR d.id_cha in (select id_cha from sp_dm_htkb where id = "+kb_id+"))";
            }
            if (kb_huyen != null && !"".equals(kb_huyen)) {
                strlstLTT += " AND d.id=" + kb_huyen;
            }
            if (ma_dv != null && !"".equals(ma_dv)) {
                strlstLTT += " AND substr(f.ma_nh,3,3)='" + ma_dv + "'";
            }
            if (so_ltt != null && !"".equals(so_ltt)) {
                strlstLTT += " AND t.id like '%" + so_ltt.trim() + "%'";
            }
            if (tien_min != null && !"".equals(tien_min) &&
                !"0".equals(tien_min)) {
                strlstLTT += " AND t.tong_sotien >='" + tien_min.replace(".", "") + "'";

            }
            if (tien_max != null && !"".equals(tien_max) &&
                !"0".equals(tien_max)) {
                strlstLTT += " AND t.tong_sotien <='" + tien_max.replace(".", "") + "'";
            }

            colLTT = lttDAO.getlstLTTTW_PTrang(strlstLTT, null, currentPage, numberRowOnPage, totalCount);
            colMonTien = lttDAO.getTienMon(strlstLTT, null);

            request.setAttribute("colMonTien", colMonTien);
            request.setAttribute("colLTT", colLTT);
            request.setAttribute("idxKB_huyen", idxKB_huyen);
            PagingBean pagingBean = new PagingBean();

            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(15);
            request.setAttribute("PAGE_KEY", pagingBean);
            saveToken(request);

        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
  public static final String Trang_thai_Tinh_duyet="02";
  public static final String Trang_thai_Tinh_ko_duyet="04";
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                conn = getConnection(request);
                HttpSession session = request.getSession();
                LTTDAO lttDAO = new LTTDAO(conn);
                duyetLTTVO duyetVO = new duyetLTTVO();
                LogDuyetLoDAO log = new LogDuyetLoDAO(conn);
               duyetLTTTinhForm frm = (duyetLTTTinhForm)form;
                LogDuyetLoVO vo = null;
                LTTVO lttVO = new LTTVO();
                String[] idArr = request.getParameterValues("selector");
                String[] ly_do = request.getParameterValues("ly_do_tinh");

                int len = idArr.length;
                String idList = "";
                String ma_nsd =
                    (String)session.getAttribute(AppConstants.APP_USER_CODE_SESSION);
                duyetVO.setNgay_hoan_thien("SYSDATE");
                String strStatusNewest = "";
                String strTrangThaiTW = "";
                LTTVO lttVOForUpdate = new LTTVO();
                String whereClause = "";
                String strNgayKy = "";
                String strMsgId = "";
                String strNgayLamViec = "";
                String lst_yctt = "";
                String lst_yctt_false = "";
                String ex_false = "";
                String yctt = null;
                Long id = null;
                String type= frm.getType();

                for (int i = 0; i < len; ++i) {
                    if (!idArr[i].equals("")) {
                        try {
                            vo = new LogDuyetLoVO();
                            Date dateKy = new Date();
                            Long lNgayTT = new Long(0);
                            idList = idArr[i];
                            String strID =
                                idList.substring(0, idList.indexOf("se@techit"));
                            yctt =
idList.substring(idList.indexOf("se@techit") + 9,
                 idList.indexOf("yctt@seatechit"));

                            String ldo_Idx =
                                idList.substring(idList.indexOf("yctt@seatechit") +
                                                 14);

                            String ly_do_tinh =
                                ly_do[Integer.parseInt(ldo_Idx)];

                            strNgayKy =
                                    StringUtil.DateToString(dateKy, AppConstants.DATE_TIME_FORMAT_SEND_ORDER);

                            strNgayLamViec =
                                    StringUtil.DateToString(new Date(),
                                                            "yyyyMMdd");


                            lNgayTT = new Long(strNgayLamViec);

                            whereClause = " t.id = " + strID;
                            lttVOForUpdate =
                                    lttDAO.getLTTVOForUpdate(whereClause,
                                                             null);
                            strStatusNewest =
                                    lttVOForUpdate.getTrang_thai_tw();
                            if("DUYET".equals(type)){
                                if (!strStatusNewest.equalsIgnoreCase(Trang_thai_Tinh_duyet)) {
                                    strTrangThaiTW = Trang_thai_Tinh_duyet;
                                    lst_yctt = lst_yctt + ",\\n" +
                                            yctt;
                                } else if (strStatusNewest.equalsIgnoreCase(Trang_thai_Tinh_duyet)) {
                                    lst_yctt_false = lst_yctt_false + ",\\n" +
                                            yctt;
                                }
                            }else if("KO_DUYET".equals(type)){
                                if (!strStatusNewest.equalsIgnoreCase(Trang_thai_Tinh_ko_duyet)) {
                                    strTrangThaiTW = Trang_thai_Tinh_ko_duyet;                                    
                                } else if (strStatusNewest.equalsIgnoreCase(Trang_thai_Tinh_ko_duyet)) {
                                    lst_yctt_false = lst_yctt_false + ",\\n" +
                                            yctt;
                                }
                            }
                            id = Long.parseLong(strID);
                            lttVO.setId(id);
                            lttVO.setTrang_thai_tw(strTrangThaiTW);
                            lttDAO.updateDuyetLTTTW(id, ma_nsd, strMsgId,
                                                    dateKy, lNgayTT,
                                                    strTrangThaiTW, null,
                                                    ly_do_tinh);
                            conn.commit();

                            if (lst_yctt != null && !"".equals(lst_yctt)) {
                                vo.setError_code("00");
                                vo.setError_desc("Thanh cong");
                                vo.setGhi_chu("");
                                vo.setNgay_duyet(new Date());
                                vo.setLtt_id(id);
                                vo.setSo_yctt(yctt);
                                log.insertLogDuyetLo(vo);
                            }

                            //                       conn.commit();
                        } catch (TTSPException e) {

                            ServletContext context =
                                servlet.getServletContext();
                            HashMap errorMap =
                                (HashMap)context.getAttribute(AppKeys.ERROR_LIST_APPLICATION_KEY);
                            String msgError = e.getMessage(errorMap);
                            ex_false = ex_false + ",\\n" +
                                    yctt + msgError;
                            vo.setError_code("01");
                            vo.setError_desc(msgError);
                            vo.setGhi_chu("");
                            vo.setNgay_duyet(new Date());
                            vo.setLtt_id(id);
                            vo.setSo_yctt(yctt);
                            vo.setMa_kb(session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString());
                            vo.setMa_nsd(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());

                            log.insertLogDuyetLo(vo);
                            //                       conn.commit();
                        }

                    }

                }
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

}
