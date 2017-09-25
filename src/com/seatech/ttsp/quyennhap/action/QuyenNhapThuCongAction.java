package com.seatech.ttsp.quyennhap.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.quyennhap.QuyenNhapThuCongDAO;
import com.seatech.ttsp.quyennhap.QuyenNhapThuCongVO;
import com.seatech.ttsp.quyennhap.form.QuyenNhapThuCongForm;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class QuyenNhapThuCongAction extends AppAction {
    private static String SUCCESS = "success";
//    private static String FAILURE = "failure";
    private String forward = AppConstants.SUCCESS;
//    private static String STRING_EMPTY = "";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "QLY_NSD.QUYENNHAP.QNHAP_LTT")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
//        Collection reVal = null;
//        String whereClause = "";
//        Vector params = null;
        Long lkb_id = null;

        try {

            HttpSession session = request.getSession();
            lkb_id =
                    new Long(session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString());
            String strMaKB = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
           
            conn = getConnection(request);
            String trang_thai = "";
            QuyenNhapThuCongForm f = (QuyenNhapThuCongForm)form;
            if (f == null) {
                f = new QuyenNhapThuCongForm();
            }
            if (f.getTrangthai() != null) {
                trang_thai = f.getTrangthai();
            }
            String strWhere = null;
            String strInnerJ = null;
            Vector v_param = null;
            strWhere = "and c.loai_nhom = ? ";
            strWhere += "and a.kb_id = ? ";
//            strWhere += "and a.kb_id in (select id from sp_dm_htkb where id_cha = ?) ";
            v_param = new Vector();
            v_param.add(new Parameter("TTV", true));
            v_param.add(new Parameter(lkb_id, true));
            strInnerJ =
                    "INNER JOIN sp_nhom_nsd s ON a.chuc_danh = s.loai_nhom";
            QuyenNhapThuCongDAO dao = new QuyenNhapThuCongDAO(conn);
            QuyenNhapThuCongVO vo = new QuyenNhapThuCongVO();
            List listTTV = (List)dao.getNSDList(strWhere, v_param);
            ArrayList lstChecked = new ArrayList();
            if (listTTV != null || listTTV.size() > 0) {
                for (int i = 0; i < listTTV.size(); i++) {
                    vo = (QuyenNhapThuCongVO)listTTV.get(i);
                    if (vo.getTrang_thai_chon().equals("1")) {
                        lstChecked.add(vo);
                    }
                }
            }
            f.setTrangthai(trang_thai);
            request.setAttribute("lstTTV", listTTV);
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }

        return mapping.findForward(SUCCESS);
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "QLY_NSD.QUYENNHAP.QNHAP_LTT")) {
            forward = AppConstants.FAILURE;
            return mapping.findForward(forward);
        }

        Connection conn = null;
        HttpSession session = request.getSession();
//        String trang_thai = null;
        QuyenNhapThuCongForm f = null;


        try {
            if (isTokenValid(request)) {
                conn = getConnection(request);
                f = (QuyenNhapThuCongForm)form;
                String[] checks = request.getParameterValues("check");
                Long nUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                Long lkb_id =
                    new Long(session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString());
                QuyenNhapThuCongDAO dao = new QuyenNhapThuCongDAO(conn);
                QuyenNhapThuCongVO vo = new QuyenNhapThuCongVO();
                String strWhere = null;
                Vector v_param = null;
                Collection coll_xoa = new ArrayList();
                //lay ra các nsd để xóa
                String strWhere_xoa = null;
                Vector v_param_xoa = null;
                strWhere_xoa = "and c.loai_nhom = ? ";
                strWhere_xoa += "and a.kb_id = ? ";
//                strWhere_xoa += "and a.kb_id in (select id from sp_dm_htkb where id_cha = ?) ";
                v_param_xoa = new Vector();
                v_param_xoa.add(new Parameter("TTV", true));
                v_param_xoa.add(new Parameter(lkb_id, true));

                coll_xoa = dao.getNSDList(strWhere_xoa, v_param_xoa);
                Iterator iter = coll_xoa.iterator();
                int nCounter = 0;
                String strNhom_id = "";
                while (iter.hasNext()) {
                    vo = (QuyenNhapThuCongVO)iter.next();
                    if (nCounter == 0) {
                        strNhom_id += vo.getNsd_id();
                    }
                    if (nCounter > 0) {
                        strNhom_id += "," + vo.getNsd_id();
                    }
                    nCounter++;

                }
                //
                strWhere_xoa = " nsd_id in (" + strNhom_id + " )";
                int i1 = dao.delete(strWhere_xoa);
                if (checks == null) {
                    vo.setNguoi_rut(nUserID);
                    dao.update(vo);
                    f.setTrangthai("Bạn đã ghi thành công");
                    conn.commit();
                }
                if (checks != null) {
                    for (int i = 0; i < checks.length; i++) {
                        if (checks[i] != null) {
                            strWhere =
                                    " and a.den_ngay is null and nsd_id = ?";
                            v_param = new Vector();
                            v_param.add(new Parameter(checks[i], true));
                            Collection c =
                                dao.getLSuQNhapList(strWhere, v_param);
                            vo.setNsd_id(new Long(checks[i]));
                            dao.insertQNhap(vo);
                            if (c.isEmpty()) {
                                vo.setNguoi_gan(nUserID);
                                vo.setNsd_id(new Long(checks[i]));
                                dao.insertLSuQNhap(vo);
                            }

                        }
                    }
                    vo.setNguoi_rut(nUserID);
                    dao.update(vo);
                    request.setAttribute("status",
                                         "ttvcoqnhap.quyennhap.save.sucess");
                    saveVisitLog(conn, request.getSession(), "QLY_NSD.QUYENNHAP.QNHAP_LTT", "");
                    conn.commit();
                }

            }
            resetToken(request);
            saveToken(request);

        } catch (Exception e) {
            (new Exception("TTVCoQNhapAction.addExc: " + e)).printStackTrace();
            throw (new Exception("TTVCoQNhapAction.addExc: " + e));
        } finally {
            close(conn);
        }


        return list(mapping, f, request, response);

    }


}
