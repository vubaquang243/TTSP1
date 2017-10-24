package com.seatech.ttsp.lov;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LovAction extends AppAction {
    public LovAction() {
        super();
    }

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String kb_id =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            //            conn = getConnection();
            String strMa = request.getParameter("ma");
            String strTen = request.getParameter("ten");
            String strLoai = request.getParameter("loai");
            String whereClause = "";
            Vector params = new Vector();
            Collection collDM = null;
            Type listType = null;
            if ("DMNH".equalsIgnoreCase(strLoai)) {
                whereClause =
                        " UPPER(ma_nh) like UPPER('%" + strMa + "%') and UPPER(ten) like UPPER('%" +
                        strTen + "%') and tinh_trang='1' ";
                DMNHangDAO dmdao = new DMNHangDAO(conn);
                collDM = dmdao.getDMNHLovList(whereClause, params);
                listType = new TypeToken<Collection<DMNHangVO>>() {
                    }.getType();
            } else if ("DMKB".equalsIgnoreCase(strLoai)) {
                whereClause =
                        " ma like '%" + strMa + "%' and ten like '%" + strTen +
                        "%'";
                DMKBacDAO dmdao = new DMKBacDAO(conn);
                collDM = dmdao.getDMKBList(whereClause, params);
            } else if ("DMKBTCUU".equalsIgnoreCase(strLoai)) {
                DChieu1DAO dao = new DChieu1DAO(conn);
                DChieu1VO vo = new DChieu1VO();
                String strCap = " and ma=" + kb_code;
                vo = dao.getCap(strCap, null);
                String cap = vo.getCap();

                if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                    if ((strMa != null && !"".equals(strMa)) ||
                        (strTen != null && !"".equals(strTen))) {
                        whereClause =
                                " AND (a.id = b.kb_id or a.id in (select id_cha from sp_dm_htkb)) AND a.ma like '%" +
                                strMa + "%' and UPPER(a.ten) like UPPER('%" +
                                strTen + "%')";
                    } else if ((strMa == null || "".equals(strMa)) ||
                               (strTen == null || "".equals(strTen))) {
                        whereClause =
                                " AND (a.id = b.kb_id or a.id in (select id_cha from sp_dm_htkb))";
                    }
                } else if ("0003".equals(kb_code)) {
                    whereClause =
                            " AND a.id = b.kb_id AND a.ma like '%" + strMa +
                            "%' and UPPER(a.ten) like UPPER('%" + strTen +
                            "%')  AND a.ma='0003' ";
                } else if ("5".equals(cap)) {
                    whereClause =
                            "  AND a.id = b.kb_id AND a.ma like '%" + strMa +
                            "%' and UPPER(a.ten) like UPPER('%" + strTen +
                            "%') and (a.id=" + kb_id + " or a.id_cha=" +
                            kb_id + ")";
                } else {
                    whereClause =
                            "  AND a.id = b.kb_id AND a.ma like '%" + strMa +
                            "%' and UPPER(a.ten) like UPPER('%" + strTen +
                            "%') and a.id =" + kb_id;
                }
                DMKBacDAO dmdao = new DMKBacDAO(conn);
                collDM = dmdao.getDMKBTCUUList(whereClause, params);
                listType = new TypeToken<Collection<DMNHangVO>>() {
                    }.getType();
            } else if ("DMKBTCUUQT".equalsIgnoreCase(strLoai)) {
                DChieu1DAO dao = new DChieu1DAO(conn);
                DChieu1VO vo = new DChieu1VO();
                String strCap = " and ma=" + kb_code;
                vo = dao.getCap(strCap, null);
                String cap = vo.getCap();

                if ("0001".equals(kb_code) || "0002".equals(kb_code) ||
                    "0003".equals(kb_code)) {
                    whereClause =
                            " AND (a.id = b.kb_id or a.id in (select id_cha from sp_dm_htkb)) AND a.ma like '%" +
                            strMa + "%' and UPPER(a.ten) like UPPER('%" +
                            strTen + "%')";
                } else if ("5".equals(cap)) {
                    whereClause =
                            "  AND a.id = b.kb_id AND a.ma like '%" + strMa +
                            "%' and UPPER(a.ten) like UPPER('%" + strTen +
                            "%') and (a.id=" + kb_id + " or a.id_cha=" +
                            kb_id + ")";
                } else {
                    whereClause =
                            "  AND a.id = b.kb_id AND a.ma like '%" + strMa +
                            "%' and UPPER(a.ten) like UPPER('%" + strTen +
                            "%') and a.id =" + kb_id;
                }
                DMKBacDAO dmdao = new DMKBacDAO(conn);
                collDM = dmdao.getDMKBTCUUList(whereClause, params);
                listType = new TypeToken<Collection<DMNHangVO>>() {
                    }.getType();
            }
//            } else if ("DMKBSODU".equalsIgnoreCase(strLoai)) {
//                DChieu1DAO dao = new DChieu1DAO(conn);
//                DChieu1VO vo = new DChieu1VO();
//                String strCap = " and ma=" + kb_code;
//                vo = dao.getCap(strCap, null);
//                String cap = vo.getCap();
//
//                if ("0001".equals(kb_code) || "0002".equals(kb_code) ||
//                    "0003".equals(kb_code)) {
//                    whereClause =
//                            " AND a.cap IN (2,3) AND (a.id = b.kb_id or a.id in (select id_cha from sp_dm_htkb)) AND a.ma like '%" +
//                            strMa + "%' and UPPER(a.ten) like UPPER('%" +
//                            strTen + "%')";
//                } else if ("5".equals(cap)) {
//                    whereClause =
//                            "  AND a.cap IN (2,3) AND a.id = b.kb_id AND a.ma like '%" + strMa +
//                            "%' and UPPER(a.ten) like UPPER('%" + strTen +
//                            "%') and (a.id=" + kb_id + " or a.id_cha=" +
//                            kb_id + ")";
//                } else {
//                    whereClause =
//                            " AND a.cap IN (2,3) AND a.id = b.kb_id AND a.ma like '%" + strMa +
//                            "%' and UPPER(a.ten) like UPPER('%" + strTen +
//                            "%') and a.id =" + kb_id;
//                }
//                DMKBacDAO dmdao = new DMKBacDAO(conn);
//                collDM = dmdao.getDMKBTCUUList(whereClause, params);
//                listType = new TypeToken<Collection<DMNHangVO>>() {
//                    }.getType();
//            }

            String strJson = new Gson().toJson(collDM, listType);

            response.reset();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        } catch (TTSPException ttspEx) {
            throw ttspEx;
        } catch (Exception ex) {
            throw TTSPException.createException("TTSP-1000", ex.toString());
        } finally {
            close(conn);
        }

        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
