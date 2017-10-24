package com.seatech.ttsp.chungthuso.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.chungthuso.form.ChungThuSoForm;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.proxy.pki.PKIPredicate;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.user.UserDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import vn.gov.vst.CertInfo;


public class QuanLyDKyCTSAction extends AppAction {
    public QuanLyDKyCTSAction() {
        super();
    }

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        Collection colCTS = null;
        Collection colMaKhoBac = null;
        Collection colUserName = null;
        Collection tempColCTS = null;
        String strAppID = AppConstants.APP_ID;
        // khai bao cac bien phan trang

        try {
            conn = getConnection();
            if (!checkPermissionOnFunction(request, "QLY_NSD.QLY_CTS.DUYET")) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            ChungThuSoForm frmCTS = (ChungThuSoForm)form;
            String page = frmCTS.getPageNumber();
            if (page == null) {
                page = "1";
            }
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = new Integer(15);
            HttpSession session = request.getSession();
            if (session != null) {
                String strWSDL = getThamSoHThong("WSDL_PKI", session);
                PKIService pkiService = new PKIService(strWSDL);
                strAppID = getThamSoHThong("APP_ID", session);
                tempColCTS = (Collection)pkiService.getCerts(false, strAppID);
                if (tempColCTS != null && tempColCTS.size() > 0) {
                    CollectionUtils colUtils = new CollectionUtils();
                    String kb_id = frmCTS.getKb_id();
                    if (kb_id != null && !kb_id.equalsIgnoreCase("")) {
                        UserDAO userDao = new UserDAO(conn);
                        String whereClause = " a.kb_id=?";
                        Vector params = new Vector();
                        params.add(new Parameter(kb_id, true));
                        colUserName = userDao.getUserList(whereClause, params);
                        request.setAttribute("kbId", kb_id);
                    }
                    PKIPredicate pkiPredicate =
                        new PKIPredicate(frmCTS.getSerial(),
                                         frmCTS.getMa_nguoi_duoc_cap(), null,
                                         null, colUserName);
                    tempColCTS = colUtils.select(tempColCTS, pkiPredicate);
                    if (tempColCTS != null && tempColCTS.size() > 0) {
                        PagingBean pagingBean = new PagingBean();
                        pagingBean.setCurrentPage(currentPage);
                        pagingBean.setNumberOfRow(tempColCTS.size());
                        pagingBean.setRowOnPage(numberRowOnPage);
                        request.setAttribute("PAGE_KEY", pagingBean);
                        int begin = numberRowOnPage * (currentPage - 1);
                        int end = 0;
                        if (tempColCTS.size() >
                            (currentPage * numberRowOnPage) - 1) {
                            end = (currentPage * numberRowOnPage);
                        } else {
                            end = tempColCTS.size();
                        }
                        List temListCTS = (List)tempColCTS;
                        tempColCTS = temListCTS.subList(begin, end);
                    }
                }
                if (tempColCTS != null && tempColCTS.size() > 0) {
                    colCTS = new ArrayList();
                    for (Iterator iter = tempColCTS.iterator(); iter.hasNext();
                    ) {
                        CertInfo cerInfo = (CertInfo)iter.next();
                        colCTS.add(pkiService.getCerInfo(cerInfo));
                    }
                }
            }

            String id_kb =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
            String strWhere = " a.id_cha = '" + id_kb + "'";
            colMaKhoBac = dmkbDAO.getDMKBList(strWhere, null);
            request.setAttribute("listKhoBac", colMaKhoBac);
            request.setAttribute("listCTSChuaDuyet", colCTS);


        } catch (Exception e) {
            //            e.printStackTrace();
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strAppID = AppConstants.APP_ID;
        try {
            ChungThuSoForm frmCTS = (ChungThuSoForm)form;
            HttpSession session = request.getSession();
            String seria = frmCTS.getSerial();
            String username = frmCTS.getUser_name();
            String[] arrSeria = (seria != null) ? seria.split(":") : null;
            String[] arrUserName =
                (username != null) ? username.split(":") : null;
            String fromdate = frmCTS.getHieu_luc_tu_ngay();
            String todate = frmCTS.getHieu_luc_den_ngay();
            String userApprove =
                session.getAttribute(AppConstants.APP_DOMAIN_SESSION) + "\\" +
                session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
            conn = getConnection();
            if (session != null) {
                String strWSDL = getThamSoHThong("WSDL_PKI", session);
                PKIService pkiService = new PKIService(strWSDL);
                for (int i = 0; i < arrSeria.length; i++) {
                    strAppID = getThamSoHThong("APP_ID", session);
                    pkiService.approveCert(arrUserName[i].replace('/', '\\'),
                                           arrSeria[i], fromdate, todate,
                                           userApprove, strAppID);
                }
                saveVisitLog(conn, session, "QLY_NSD.QLY_CTS.DUYET",
                             "Duyet chung thu so");
            }
            frmCTS.setSerial("");
            frmCTS.setUser_name("");
        } catch (TTSPException ex) {
            throw TTSPException.createException("TTSP-9999",
                                                "Loi " + ex.getMessage());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
