package com.seatech.ttsp.ktvtmselect.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.ktvtmselect.KTVTabmisSelectDAO;
import com.seatech.ttsp.ktvtmselect.KTVTabmisSelectVO;
import com.seatech.ttsp.ktvtmselect.form.KTVTabmisSelectForm;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class KTVTabmisSelectAction extends AppAction {
    private static String SUCCESS = "success";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "LTT.CHON_KTVTABMIS")) {
            return mapping.findForward("errorQuyen");
        }
        
        Long nUserID = null;
        String nUserName = null;
        Long nKbID = null;
        String nKbName = null;
        Connection conn = null;
        try {
            HttpSession session = request.getSession();
            KTVTabmisSelectForm f = (KTVTabmisSelectForm)form;
            conn = getConnection(request);
            nUserID = new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            nUserName = session.getAttribute(AppConstants.APP_USER_NAME_SESSION).toString();
            nKbID = new Long(session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString());
            nKbName = session.getAttribute(AppConstants.APP_KB_NAME_SESSION).toString();
            f.setNsd_id(nUserID.toString());
            f.setNsd_name(nUserName);
            f.setKb_id(nKbID.toString());
            f.setKb_name(nKbName);
            
            String orderBy = "";
            if(f.isIsCheckSort()){
                orderBy = " DESC ";
            }
            
            // lấy trong KTVtabmisDAO hàm getKTVTabmisListByNSD để hiển thị ng check
            KTVTabmisSelectDAO dao = new KTVTabmisSelectDAO(conn);
            Collection listKTV = dao.getListTTVSelectKTVTABMIST(nKbID, orderBy);
            request.setAttribute("lstKTV", listKTV);
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

        Connection conn = null;
        HttpSession session = request.getSession();
        try {
            if (isTokenValid(request)) {
                conn = getConnection(request);
                String[] checks = request.getParameterValues("check");
                if(session.getAttribute(AppConstants.APP_KB_ID_SESSION)==null){
                  throw new Exception("Khong tim duoc SHKB");
                }
                
                String strWhere = " ttv_id in (select id from sp_nsd where kb_id = ?)";
                Vector vParam = new Vector();
                Long nUserID = new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                Long nKbID = new Long(session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString());
                vParam.add(new Parameter(nKbID, true));
                KTVTabmisSelectDAO ktvDAO = new KTVTabmisSelectDAO(conn);
                ktvDAO.delete(strWhere, vParam);
                KTVTabmisSelectVO ktvVO = new KTVTabmisSelectVO();
                if (checks != null) {
                    for (int i = 0; i < checks.length; i++) {
                        if (checks[i] != null) {
                            String[] arrParameter = checks[i].split("_");
                            ktvVO.setNsd_id(nUserID);
                            ktvVO.setKtv_id(new Long(arrParameter[1]));
                            ktvVO.setTtv_id(new Long(arrParameter[0]));
                            ktvDAO.insert(ktvVO);
                        }
                    }
                }
                conn.commit();
                request.setAttribute("status", "ktvtmselect.success.save");
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            (new Exception("KTVTabmisSelectAction.addExc: " +
                           e)).printStackTrace();
            throw (new Exception("KTVTabmisSelectAction.addExc: " + e));
        } finally {
            close(conn);
        }
        KTVTabmisSelectForm fff = new KTVTabmisSelectForm();
        return list(mapping, fff, request, response);

    }

}
