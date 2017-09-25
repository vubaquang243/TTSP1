package com.seatech.ttsp.theodoiht.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmnhkb.DMHTKBacVO;
import com.seatech.ttsp.dmnhkb.DMNHKBacDAO;
import com.seatech.ttsp.theodoiht.TheoDoiHeThongDAO;
import com.seatech.ttsp.theodoiht.TheoDoiHeThongVO;
import com.seatech.ttsp.theodoiht.form.TraCuuTTSPForm;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TraCuuChungTuSangTTSPAction extends AppAction {
    private final String STRING_EMPTY = "";
    private final String TABMIS_TTSP = "SYS.TRCUU_CTU_GD.TABMIS_TTSP";

    public TraCuuChungTuSangTTSPAction() {
        super();
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        boolean bIsTTTT = false;
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String strKBCode =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            /**
          * Kiem tra TTTT
          * 1. Hien thi tat ca neu la TTTT
          * 2. Chi hien thi ban ghi don vi
          * @param:strNHKB_code
          * */
            DMNHKBacDAO nhkbDAO = new DMNHKBacDAO(conn);
            String whereClauseKB = "ma = ?";
            Vector params = new Vector();
            params.add(new Parameter(strKBCode, true));
            DMHTKBacVO voDMNH = nhkbDAO.getDMHTKBac(whereClauseKB, params);
            if (voDMNH != null) {
                String strDelimiter = ",";
                if (AppConstants.MA_TTTT.indexOf(strDelimiter) > -1) {
                    String[] strFound =
                        AppConstants.MA_TTTT.split(strDelimiter);
                    for (int i = 0; i < strFound.length; i++) {
                        if (voDMNH.getMa().toString().equalsIgnoreCase(strFound[i])) {
                            bIsTTTT = true;
                            break;
                        }
                    }
                }
            }
            request.setAttribute("TTTT", bIsTTTT);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String forward = null;
        Connection conn = null;
        boolean bIsTTTT = false;
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String strKBCode =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            Collection colThamSoHT = getThamSoHThong(session);
            TheoDoiHeThongDAO dao = new TheoDoiHeThongDAO(conn);
            TraCuuTTSPForm tracuuForm = (TraCuuTTSPForm)form;
            /**
          * Kiem tra TTTT
          * 1. Hien thi tat ca neu la TTTT
          * 2. Chi hien thi ban ghi don vi
          * @param:strNHKB_code
          * */
            DMNHKBacDAO nhkbDAO = new DMNHKBacDAO(conn);
            String whereClauseKB = "ma = ?";
            Vector params = new Vector();
            params.add(new Parameter(strKBCode, true));
            DMHTKBacVO voDMNH = nhkbDAO.getDMHTKBac(whereClauseKB, params);
            if (voDMNH != null) {
                String strDelimiter = ",";
                if (AppConstants.MA_TTTT.indexOf(strDelimiter) > -1) {
                    String[] strFound =
                        AppConstants.MA_TTTT.split(strDelimiter);
                    for (int i = 0; i < strFound.length; i++) {
                        if (voDMNH.getMa().toString().equalsIgnoreCase(strFound[i])) {
                            bIsTTTT = true;
                            break;
                        }
                    }
                }
                // neu khong phai TTTT se gan ma kb nhan la kho bac cua user dang nhap
                if (!bIsTTTT) {
                    tracuuForm.setMakb_chuyen(session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString());
                }
                List<TheoDoiHeThongVO> lstHeThong =
                    //                null;
                    new ArrayList<TheoDoiHeThongVO>();
                String strMsg = dao.sendMessage(tracuuForm, colThamSoHT);
                if (strMsg != null) {
                    // kiem tra header hop le
                    //            moi getmessage
                    String strErrorHeader = dao.validError();
                    // ErrorCode va ErrorDes rong thi thuc hien getmessage
                    if (STRING_EMPTY.equals(strErrorHeader)) {
                        lstHeThong = dao.getMessage();
                        saveVisitLog(conn, session, TABMIS_TTSP,"");
                        if (lstHeThong == null) {
                            strErrorHeader = "errorHT";
                        }
                    }
                    request.setAttribute("errorHeader", strErrorHeader);
                    request.setAttribute("lstHeThong", lstHeThong);
                    forward = AppConstants.SUCCESS;
                    //
                }
                request.setAttribute("TTTT", bIsTTTT);
            }
        } catch (Exception ex) {
            throw new Exception("Tra Cuu Chung Tu Sang TTSP: " + ex);
        } finally {
            close(conn);
        }
        return mapping.findForward(forward);
    }
}

