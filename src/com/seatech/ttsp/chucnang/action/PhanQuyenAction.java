package com.seatech.ttsp.chucnang.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.chucnang.ChucNangDAO;
import com.seatech.ttsp.chucnang.PhanQuyenDAO;
import com.seatech.ttsp.chucnang.form.PhanQuyenForm;
import com.seatech.ttsp.nhomnsd.NhomNSDDAO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class PhanQuyenAction extends AppAction {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strWhere = null;
        Vector vParam = null;
        PhanQuyenForm f = null;
        Collection colCnangCoQuyen = null;
        Collection colCnangKoQuyen = null;
        try {
            //Check quyen
            if (!checkPermissionOnFunction(request,
                                           "QLY_NSD.QLY_NHOM.PHAN_QUYEN")) {
                return mapping.findForward(AppConstants.MAPING_NO_RIGHT);
            }
            conn = getConnection(request);

            f = (PhanQuyenForm)form;
            //Lay ra nhom NSD fill to select box
            NhomNSDDAO nhomDAO = new NhomNSDDAO(conn);
            Collection colNhomNSD = nhomDAO.getNhomNSDList(null, null);

//            UserDAO uDAO = new UserDAO(conn);
            ChucNangDAO cnangDAO = new ChucNangDAO(conn);

            //Lay ra cac chuc nang cha cho select box
            strWhere =
                    " a.cnang_cha is null and a.trang_thai = 'Y' ORDER BY a.ma_cnang";
            Collection colCNangCha = cnangDAO.getChucNangList(strWhere, null);

            if (f.getNhom_id() != null) {

                vParam = new Vector();

                strWhere =
                        " a.trang_thai = 'Y' and a.id in (select cnang_id from sp_cnang_nhom where nhom_id = ?)";
                vParam.add(new Parameter(f.getNhom_id(), true));

                if (f.getCnang_id() != null && !"".equals(f.getCnang_id())) {
                    strWhere =
                            strWhere + " AND a.ky_hieu_cnang like '" + f.getCnang_id() +
                            "%'";
                }

                strWhere = strWhere + " ORDER BY a.ma_cnang";
                colCnangCoQuyen = cnangDAO.getChucNangList(strWhere, vParam);


                vParam = new Vector();
                strWhere =
                        " a.trang_thai = 'Y' and (a.id not in (select a2.cnang_id from sp_cnang_nhom a2 where a2.nhom_id = ?) " +
                        //                         " or a.id in (select a3.cnang_cha from sp_chucnang a3 where a3.id not in "+
                        //                         " (select a4.cnang_id from sp_cnang_nhom a4 where a4.nhom_id = ?)))";
                        " or a.ma_cnang in (" + " select " + " case" +
                        " when MOD(a3.ma_cnang,10) <> 0 then a3.ma_cnang - MOD(a3.ma_cnang,10)" +
                        " else 0" + " end" +
                        " from sp_chucnang a3 where a3.id not in " +
                        " (select a4.cnang_id from sp_cnang_nhom a4 where a4.nhom_id = ?))" +
                        " or a.ma_cnang in (" + " select " + " case" +
                        " when MOD(a3.ma_cnang,100) <> 0 then a3.ma_cnang - MOD(a3.ma_cnang,100)" +
                        " else 0" +

                        " end" + " from sp_chucnang a3 where a3.id not in " +
                        " (select a4.cnang_id from sp_cnang_nhom a4 where a4.nhom_id = ?))" +
  
//                        " or a.ma_cnang in (" + " select " + " case" +
//                        " when MOD(a3.ma_cnang,1000) <> 0 then a3.ma_cnang - MOD(a3.ma_cnang,1000)" +
//                        " else 0" +
//                        " end" + " from sp_chucnang a3 where a3.id not in " +
//                        " (select a4.cnang_id from sp_cnang_nhom a4 where a4.nhom_id = ?))"+
                        ")";

                vParam.add(new Parameter(f.getNhom_id(), true));
                vParam.add(new Parameter(f.getNhom_id(), true));
                vParam.add(new Parameter(f.getNhom_id(), true));
//                vParam.add(new Parameter(f.getNhom_id(), true));

                if (f.getCnang_id() != null && !"".equals(f.getCnang_id())) {
                    strWhere =
                            strWhere + " AND a.ky_hieu_cnang like '" + f.getCnang_id() +
                            "%'";
                }

                strWhere = strWhere + " ORDER BY a.ma_cnang";
                colCnangKoQuyen = cnangDAO.getChucNangList(strWhere, vParam);


                request.setAttribute("colCnangCoQuyen", colCnangCoQuyen);
                request.setAttribute("colCnangKoQuyen", colCnangKoQuyen);
            }
            //Set attribute
            request.setAttribute("colNhomNSD", colNhomNSD);
            request.setAttribute("colCNangCha", colCNangCha);

            conn.commit();
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        Long nCNangID = null;
        String strCurrentUserID = null;
        Long nNhomID = null;
        try {
            //Chong F5
            if (!isTokenValid(request))
                return executeAction(mapping, form, request, response);
            //Check quyen
            if (!checkPermissionOnFunction(request,
                                           "QLY_NSD.QLY_NHOM.PHAN_QUYEN")) {
                return mapping.findForward(AppConstants.MAPING_NO_RIGHT);
            }
            HttpSession session = request.getSession();
            if (session == null)
                return mapping.findForward("login");
            //Lay phan nhom tren session
            strCurrentUserID =
                    session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String strFun = request.getParameter("arrAddFun");
            String strAction = request.getParameter("actionPQ");
            Object[] arrFun = strFun.split(",");

            PhanQuyenForm f = (PhanQuyenForm)form;
            if (f.getNhom_id() == null || "".equals(f.getNhom_id())) {

            } else {
                nNhomID = new Long(f.getNhom_id());
            }

            conn = getConnection(request);

            PhanQuyenDAO pqDAO = new PhanQuyenDAO(conn);
            //Phan quyen
            for (int i = arrFun.length - 1; i >= 0; i--) {
                if (arrFun[i] == null)
                    continue;

                if ("".equalsIgnoreCase(arrFun[i].toString().trim()))
                    continue;
                nCNangID = new Long(arrFun[i].toString().trim());
                if (strAction.equalsIgnoreCase("add")) {
                    //Neu chua dc phan quyen thi insert vao db
                    if (pqDAO.checkExist(nNhomID, nCNangID) <= 0) {
                        pqDAO.themQuyen(nNhomID, nCNangID);
                    }
                } else if (strAction.equalsIgnoreCase("remove")) {
                    if (pqDAO.checkExistCNangCon(nNhomID, nCNangID) <= 0) {
                        pqDAO.xoaQuyen(nNhomID, nCNangID);
                    }
                }
            }
            saveVisitLog(conn, session,
                         "QLY_NSD.QLY_NHOM.PHAN_QUYEN","Thuc hien phan quyen cho nhom NSD");


            conn.commit();
            resetToken(request);
            saveToken(request);
        } catch (Exception ex) {
            throw new Exception("PhanNhomAction: " + ex);
        } finally {
            close(conn);
        }
        return executeAction(mapping, form, request, response);
    }
}
