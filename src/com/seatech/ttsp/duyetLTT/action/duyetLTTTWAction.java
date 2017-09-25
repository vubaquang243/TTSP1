package com.seatech.ttsp.duyetLTT.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.AppKeys;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.duyetLTT.duyetLTTDAO;
import com.seatech.ttsp.duyetLTT.duyetLTTVO;
import com.seatech.ttsp.duyetLTT.form.duyetLTTTWForm;
import com.seatech.ttsp.logduyetlo.dao.LogDuyetLoDAO;
import com.seatech.ttsp.logduyetlo.vo.LogDuyetLoVO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.proxy.giaodien.SendLTToan;
import com.seatech.ttsp.proxy.giaodien.mq.QueueManager;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class duyetLTTTWAction extends AppAction {

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();

            duyetLTTDAO lttDAO = new duyetLTTDAO(conn);
            DChieu1DAO dcDAO = new DChieu1DAO(conn);
            //            duyetLTTVO duyetVO = new duyetLTTVO();
            duyetLTTTWForm frm = (duyetLTTTWForm)form;
            List dmucNH = null;
            List dmuckb_cha = null;
            //            List colTTV = null;
            //            List colTTVTABMIS = null;
            Collection colLTT = null;
            Collection colMonTien = null;

            //          String strUserInfo =
            //              (String)session.getAttribute(AppConstants.APP_USER_CODE_SESSION);

            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            dmucNH = (List)TTdao.getDMucNH(null,null);
            request.setAttribute("dmucNH", dmucNH);

            String strWhere = "";
            dmuckb_cha = (List)dcDAO.getDMucKB_cha(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);

            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            String page = frm.getPageNumber();
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];

            //            String kb_id =
            //                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);

            request.setAttribute("chuc_danh", strUserInfo);


            String strlstLTT = " ";
            strlstLTT += " AND t.trang_thai_tw='02'";
            String so_ltt = frm.getSo_ltt() == null ? "" : frm.getSo_ltt();

            String tien_max = frm.getTien_max();
            String tien_min = frm.getTien_min();
            String ma_dv = frm.getMa_dv() == null ? "" : frm.getMa_dv();
            String kb_tinh =
                frm.getNhkb_tinh() == null ? "" : frm.getNhkb_tinh();
            String kb_huyen =
                frm.getNhkb_huyen() == null ? "" : frm.getNhkb_huyen();
            String idxKB_huyen = request.getParameter("idxKB");


            if (kb_tinh != null && !"".equals(kb_tinh)) {
                strlstLTT +=
                        " AND (d.id_cha = " + kb_tinh + " OR d.id =" + kb_tinh +
                        ")";
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
                strlstLTT +=
                        " AND t.tong_sotien >='" + tien_min.replace(".", "") +
                        "'";

            }
            if (tien_max != null && !"".equals(tien_max) &&
                !"0".equals(tien_max)) {
                strlstLTT +=
                        " AND t.tong_sotien <='" + tien_max.replace(".", "") +
                        "'";

            }

            colLTT =
                    lttDAO.getlstLTTTW_PTrang(strlstLTT, null, currentPage, numberRowOnPage,
                                              totalCount);
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

    public static final String Trang_thai_Tinh_duyet = "02";
    public static final String Trang_thai_TW_duyet = "03";
    public static final String Trang_thai_TW_ko_duyet = "05";

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        QueueManager queueManager = null;
        boolean checkCommitMQ = false;
        try {
            if (isTokenValid(request)) {
                conn = getConnection(request);
                HttpSession session = request.getSession();

                //            duyetLTTDAO duyetDAO = new duyetLTTDAO(conn);
                LTTDAO lttDAO = new LTTDAO(conn);
                duyetLTTTWForm frm = (duyetLTTTWForm)form;
                duyetLTTVO duyetVO = new duyetLTTVO();

                LogDuyetLoDAO log = new LogDuyetLoDAO(conn);
                LogDuyetLoVO vo = null;
                LTTVO lttVO = new LTTVO();
                String[] idArr = request.getParameterValues("selector");
                String[] ly_do = request.getParameterValues("ly_do_tw");

                int len = idArr.length;
                String idList = "";
                //                String idList_fail = "";
                String ma_nsd =
                    (String)session.getAttribute(AppConstants.APP_USER_CODE_SESSION);
                //            Long nUserID =
                //                Long.parseLong(session.getAttribute("id_nsd").toString());

                duyetVO.setNgay_hoan_thien("SYSDATE");
                String strStatusNewest = "";
                String strTrangThaiTW = "";
                //                String strChuKy = "";
                LTTVO lttVOForUpdate = new LTTVO();
                String whereClause = "";
                String strNgayKy = "";
                String strMsgId = "";
                String strNgayLamViec = "";
                String lst_yctt = "";
                String lst_yctt_false = "";
                String gui_false = "";
                String ex_false = "";
                String yctt = null;
                Long id = null;
                String type = frm.getType();
                
                queueManager = new QueueManager(getThamSoHThong(session));
                queueManager.setQueueManager();

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

                            String ly_do_tw = ly_do[Integer.parseInt(ldo_Idx)];
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
                            if ("DUYET".equals(type)) {
                                if (strStatusNewest.equalsIgnoreCase(Trang_thai_Tinh_duyet)) {
                                    strTrangThaiTW = Trang_thai_TW_duyet;
                                    lst_yctt = lst_yctt + ",\\n" +
                                            yctt;
                                } else {
                                    lst_yctt_false = lst_yctt_false + ",\\n" +
                                            yctt;
                                }
                            } else if ("KO_DUYET".equals(type)) {
                                if (strStatusNewest.equalsIgnoreCase(Trang_thai_Tinh_duyet)) {
                                    strTrangThaiTW = Trang_thai_TW_ko_duyet;
                                } else {
                                    lst_yctt_false = lst_yctt_false + ",\\n" +
                                            yctt;
                                }

                                id = Long.parseLong(strID);
                                lttVO.setId(id);
                                lttVO.setTrang_thai_tw(strTrangThaiTW);
                                lttDAO.updateDuyetLTTTW(id, ma_nsd, strMsgId,
                                                        dateKy, lNgayTT,
                                                        strTrangThaiTW,
                                                        ly_do_tw, null);
                                conn.commit();

                            }

                            if (Trang_thai_TW_duyet.equals(strTrangThaiTW)) {

                                SendLTToan sendLTT =
                                    new SendLTToan(conn, queueManager);
                                strMsgId =
                                        sendLTT.sendMessage(strID, session.getAttribute(AppConstants.APP_KB_CODE_SESSION) ==
                                                                   null ? "" :
                                                                   session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(),
                                                            null, strNgayKy,
                                                            "", session,
                                                            "TW"); //strHachToanTheoNgayKSNH


                                if (strMsgId == null || "".equals(strMsgId)) {
                                    gui_false = gui_false + ",\\n" +
                                            yctt;
                                } else if (strMsgId != null &&
                                           !"".equals(strMsgId)) {
                                    id = Long.parseLong(strID);
                                    lttVO.setId(id);
                                    lttVO.setTrang_thai_tw(strTrangThaiTW);
                                    lttDAO.updateDuyetLTTTW(id, ma_nsd,
                                                            strMsgId, dateKy,
                                                            lNgayTT,
                                                            strTrangThaiTW,
                                                            ly_do_tw, null);
                                    //                        conn.commit();

                                    if (lst_yctt != null &&
                                        !"".equals(lst_yctt)) {
                                        vo.setError_code("00");
                                        vo.setError_desc("Thanh cong");
                                        vo.setGhi_chu("");
                                        vo.setNgay_duyet(new Date());
                                        vo.setLtt_id(id);
                                        vo.setSo_yctt(yctt);

                                        log.insertLogDuyetLo(vo);

                                        checkCommitMQ = true;
                                    }
                                    conn.commit();
                                    if (checkCommitMQ) {
                                      queueManager.commitMQ();
                                    }
                                }
                            }
                        } catch (TTSPException e) {
                            if (queueManager != null) {
                                queueManager.rollbackMQ();
                            }

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
                            conn.commit();
                        } catch (Exception e) {
                            if (queueManager != null) {
                                queueManager.rollbackMQ();
                            }

                            ServletContext context =
                                servlet.getServletContext();
                            HashMap errorMap =
                                (HashMap)context.getAttribute(AppKeys.ERROR_LIST_APPLICATION_KEY);
                            String msgError = e.getMessage();
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
                            conn.commit();
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
            if (queueManager != null) {
                queueManager.disconnectMQ();
            }
        }
        return mapping.findForward("success");
    }


    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            String strMaKB;
            String strJSON;
            String strWhereClause = "";
            conn = getConnection(request);
            DChieu1DAO ttdao = new DChieu1DAO(conn);
            Collection col = null;

            HttpSession session = request.getSession();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            strMaKB = request.getParameter("kb_id").toString();
            DChieu1VO vo = new DChieu1VO();
            String strCap = " and ma=" + kb_code;
            vo = ttdao.getCap(strCap, null);
            //            String cap = vo.getCap();
            //          if ("0001".equals(kb_code) || "0002".equals(kb_code) ||
            //              "0003".equals(kb_code)) { // SGD TTTT
            if ("3".equals(strMaKB) || "1".equals(strMaKB)) {
                strWhereClause += " and a.ma='0003'";
            } else {
                strWhereClause +=
                        " and a.id_cha = " + strMaKB + " and a.ma<>'0003'";
            }
            col = ttdao.getDMucKB_huyen(strWhereClause, null);
            //          } else {
            //              if ("5".equals(cap)) { // cap tinh
            //                  strWhereClause +=
            //                          " and a.id_cha = " + strMaKB; // + " and ma=" + kb_code;
            //                  col = ttdao.getDMucKB_cha(strWhereClause, null);
            //                //strWhereClause += " and a.id_cha=" + strMaKB;
            //                col = ttdao.getDMucKB_huyen(strWhereClause, null);
            //              } else {
            //                  strWhereClause += " and a.ma=" + kb_code;
            //                  col = ttdao.getDMucKB_huyen(strWhereClause, null);
            //              }
            //          }

            JSONObject jsonRes = new JSONObject();
            response.setHeader("X-JSON", jsonRes.toString());
            java.lang.reflect.Type listType =
                new TypeToken<Collection<DChieu1VO>>() {
            }.getType();
            strJSON = new Gson().toJson(col, listType);

            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJSON.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            JSONObject jsonRes = new JSONObject();
            jsonRes.put("executeError",
                        FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));

            response.setHeader("X-JSON", jsonRes.toString());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }


}
