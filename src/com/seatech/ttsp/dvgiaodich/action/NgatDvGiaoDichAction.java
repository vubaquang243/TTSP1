package com.seatech.ttsp.dvgiaodich.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dvgiaodich.DvGiaoDichDAO;
import com.seatech.ttsp.dvgiaodich.DvGiaoDichVO;
import com.seatech.ttsp.dvgiaodich.form.DvGiaoDichForm;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class NgatDvGiaoDichAction extends AppAction {
    private static String SUCCESS = "success";
    private static String SGD = "0003";
    private static String CAP_TINH = "5";

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request,
                                       "SYS.QLGD.NGAT_DONVI_GIAODICH")) {
            return mapping.findForward("errorQuyen");
        }
        Collection lstKBTinh = null;
        try {
            Connection conn = getConnection();
            HttpSession session = request.getSession();
            DChieu1DAO dao = new DChieu1DAO(conn);
            // danh sach kb tinh
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                String whereClause = " ";
                lstKBTinh = dao.getDMucKB_cha(whereClause, null);
            } else {
                String strWhere = " AND a.ma='0003' ";
                lstKBTinh = dao.getDMucKB_cha(strWhere, null);
            }
        } catch (TTSPException e) {
            throw e;
        } finally {
            request.setAttribute("lstKBTinh", lstKBTinh);
        }
        return mapping.findForward(SUCCESS);

    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request,
                                       "SYS.QLGD.NGAT_DONVI_GIAODICH")) {
            return mapping.findForward("errorQuyen");
        }

        Connection conn = null;

        try {
            HttpSession session = request.getSession();
            Long nUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            conn = getConnection();
            DvGiaoDichForm f = (DvGiaoDichForm)form;
            DvGiaoDichVO vo = new DvGiaoDichVO();

            DvGiaoDichDAO dao = new DvGiaoDichDAO(conn);
            vo = dao.getDVGD(new Long(f.getKb_id()));
            if (vo != null) {
                f.setTrangthai("ton tai");
            } else {
                vo = new DvGiaoDichVO();
                BeanUtils.copyProperties(vo, f);
                vo.setNguoi_ngat(nUserID);

                if (vo.getNguoi_noi() == 0) {
                    vo.setNguoi_noi(null);
                }
                int i = dao.insert(vo);
                if (i > 0) {
                    int a = dao.insertLsu(vo);
                    if (a > 0) {
                        //ghi save
                        f.setTrangthai("again");

                        saveVisitLog(conn, session,
                                     "SYS.QLGD.NGAT_DONVI_GIAODICH", "");

                        conn.commit();
                    }
                }
            }
            view(mapping, form, request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);

        }
        return mapping.findForward(SUCCESS);
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
            conn = getConnection();
            DChieu1DAO ttdao = new DChieu1DAO(conn);
            Collection col = null;
            //            Collection colNH = null;
            HttpSession session = request.getSession();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            strMaKB = request.getParameter("kb_id").toString();
            DChieu1VO vo = new DChieu1VO();
            String strCap = " and ma=" + kb_code;
            vo = ttdao.getCap(strCap, null);
            String cap = vo.getCap();
            if ("0001".equals(kb_code) || "0002".equals(kb_code) ||
                "0003".equals(kb_code)) { // SGD TTTT
                if ("3".equals(strMaKB) || "1".equals(strMaKB)) {
                    strWhereClause += " and a.ma='0003'";
                } else {
                    strWhereClause +=
                            " and a.id_cha = " + strMaKB + " and a.ma<>'0003'";
                }
                col = ttdao.getDMucKBHuyen(strWhereClause, null);
            } else {
                if ("5".equals(cap)) { // cap tinh
                    strWhereClause +=
                            " and a.id_cha = " + strMaKB; // + " and ma=" + kb_code;
                    col = ttdao.getDMucKB_cha(strWhereClause, null);
                } else {
                    strWhereClause += " and a.ma=" + kb_code;
                    col = ttdao.getDMucKBHuyen(strWhereClause, null);
                }
            }

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
