package com.seatech.ttsp.dvgiaodich.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dvgiaodich.DvGiaoDichDAO;
import com.seatech.ttsp.dvgiaodich.DvGiaoDichVO;
import com.seatech.ttsp.dvgiaodich.form.DvGiaoDichForm;

import java.sql.Connection;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class MoDvGiaoDichAction extends AppAction {
    private static String SUCCESS = "success";
    //    private static String FAILURE = "failure";
    //    private String forward = AppConstants.SUCCESS;
    //    private static String STRING_EMPTY = "";

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request,
                                       "SYS.QLGD.MO_DONVI_GIAODICH")) {
            return mapping.findForward("errorQuyen");
        }
        Long nUserID = null;
        //        String nUserName = null;
        //        Long nKbID = null;
        //        String nKbName = null;
        Connection conn = null;
        Collection lstKBTinh = null;
        try {
            HttpSession session = request.getSession();
            DvGiaoDichForm f = (DvGiaoDichForm)form;
            conn = getConnection();
            //          Collection list = null;
            nUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            // lấy trong KTVtabmisDAO hàm getKTVTabmisListByNSD để hiển thị ng check
            DvGiaoDichDAO dao = new DvGiaoDichDAO(conn);
            String strwhere = null;
            //Khai bao bien find.
            String strma_kb = null;
            String strten_kb = null;
            String strden_ngay = null;
            String strtu_ngay = null;
            f.setTrangthai("");
            if (f != null) {
                strma_kb = f.getKb_id();
                strten_kb = f.getTen_kb();
                strden_ngay = f.getDen_ngay();
                strtu_ngay = f.getTu_ngay();
            }
            if (strma_kb != null && !"".equals(strma_kb)) {
                strwhere = "and a.kb_id = " + strma_kb;
            } else {
                strwhere = "";
            }
            boolean bCheck = false;
            if ((strtu_ngay != null && !"".equals(strtu_ngay)) &&
                (strden_ngay != null && !"".equals(strden_ngay))) {
                bCheck = true;
            } else if ((strtu_ngay != null && !"".equals(strtu_ngay))) {
                bCheck = true;
            } else if ((strden_ngay != null && !"".equals(strden_ngay))) {
                bCheck = true;
            }
            if (bCheck) {
                if (strtu_ngay != null && !"".equals(strtu_ngay)) {
                    strwhere +=
                            " and to_char(a.tu_ngay,'DD/MM/yyyy')='" + f.getTu_ngay() +
                            "'";
                }
                if (strden_ngay != null && !"".equals(strden_ngay)) {
                    strwhere +=
                            " and to_char(a.den_ngay,'DD/MM/yyyy')= '" + f.getDen_ngay() +
                            "'";
                }
            }
            //            if ((!"".equals(strtu_ngay)) || "".equals(strden_ngay)) {
            //                if (  strtu_ngay != null) {
            //                    strwhere +=
            //                            " and to_char(a.tu_ngay,'DD/MM/yyyy')='" + f.getTu_ngay() +
            //                            "'";
            //                }
            //            }
            //            if (("".equals(strtu_ngay))&& !"".equals(strden_ngay)) {
            //                if (strden_ngay != null) {
            //                    strwhere +=
            //                            "' and to_char(a.den_ngay,'DD/MM/yyyy')= '" + f.getDen_ngay() +
            //                            "'";
            //                }
            //            }
            if ((!"".equals(strtu_ngay)) && strtu_ngay != null) {
                if (!"".equals(strden_ngay) && strden_ngay != null) {
                    strwhere +=
                            " and to_char(a.den_ngay,'DD/MM/yyyy')>='" + f.getTu_ngay() +
                            "' and to_char(a.tu_ngay,'DD/MM/yyyy')<= '" +
                            f.getDen_ngay() + "'";

                }
            }
            Vector param = null;
            List list = (List)dao.getListNgatDvGD(strwhere, param);
            if (list.isEmpty()) {
                request.setAttribute("status",
                                     "donvigiaodich.moketnoi.warning.ketquatimkiem.null");
            }
            request.setAttribute("listDVGD", list);
            // kb tinh

            DChieu1DAO dao1 = new DChieu1DAO(conn);
            // danh sach kb tinh
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                String whereClause = " ";
                lstKBTinh = dao1.getDMucKB_cha(whereClause, null);
            } else {
                String strWhere = " AND a.ma='0003' ";
                lstKBTinh = dao1.getDMucKB_cha(strWhere, null);
            }

        } catch (Exception e) {
            request.setAttribute("status", "all.error.system");
            throw e;
        } finally {
            request.setAttribute("lstKBTinh", lstKBTinh);
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }


    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request,
                                       "SYS.QLGD.MO_DONVI_GIAODICH")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        //        Long nsdid = null;
        HttpSession session = request.getSession();
        Collection lstKBTinh = null;
        try {
            //            if (isTokenValid(request)) {
            DvGiaoDichForm f = (DvGiaoDichForm)form;
            conn = getConnection();
            String[] checks = request.getParameterValues("check");
            //            String strWhere = " a.kb_id = ?";
            Vector vParam = new Vector();
            Long nUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            vParam.add(new Parameter(nUserID, true));
            DvGiaoDichVO vo = new DvGiaoDichVO();

            vo.setNguoi_noi(nUserID);
            vo.setLido_noi(f.getLido_noi());
            DvGiaoDichDAO dao = new DvGiaoDichDAO(conn);
            if (checks != null) {
                for (int i = 0; i < checks.length; i++) {
                    if (checks[i] != null) {
                        Long id2 = dao.getIdKnLSu(new Long(checks[i].trim()));
                        int a = dao.delete(new Long(checks[i].trim()));
                        if (a > 0) {
                            vo.setNguoi_noi(nUserID);
                            vo.setId(new Long(id2));
                            //                            int b =
                            dao.update(vo);
                            f.setTrangthai("again");
                        }

                    }
                }
            }
            saveVisitLog(conn, session, "SYS.QLGD.MO_DONVI_GIAODICH", "");
            conn.commit();
            // kb tinh

            DChieu1DAO dao1 = new DChieu1DAO(conn);
            // danh sach kb tinh
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
                String whereClause = " ";
                lstKBTinh = dao1.getDMucKB_cha(whereClause, null);
            } else {
                String strWhere = " AND a.ma='0003' ";
                lstKBTinh = dao1.getDMucKB_cha(strWhere, null);
            }
            //
            //            }
            //            resetToken(request);
            //            saveToken(request);
        } catch (Exception e) {
            (new Exception("MoDvGiaoDichAction.addExc: " +
                           e)).printStackTrace();
            throw (new Exception("MoDvGiaoDichAction.addExc: " + e));
        } finally {
            request.setAttribute("lstKBTinh", lstKBTinh);
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
    //
    //  }
}
