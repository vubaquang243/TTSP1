package com.seatech.ttsp.theodoiht.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmnhkb.DMHTKBacVO;
import com.seatech.ttsp.dmnhkb.DMNHKBacDAO;
import com.seatech.ttsp.theodoiht.TheoDoiHeThongDAO;
import com.seatech.ttsp.theodoiht.TheoDoiHeThongTabmisVO;
import com.seatech.ttsp.theodoiht.form.TraCuuTabmisForm;

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


public class TraCuuChungTuSangTabmisAction extends AppAction {
    private final String STRING_EMPTY = "";
    private final String TTSP_TABMIS = "SYS.TRCUU_CTU_GD.TTSP_TABMIS";

    public TraCuuChungTuSangTabmisAction() {
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
            String strKBCode = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            Collection colThamSoHT = getThamSoHThong(session);
            TheoDoiHeThongDAO dao = new TheoDoiHeThongDAO(conn);
            TraCuuTabmisForm tracuuForm = (TraCuuTabmisForm)form;
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
            DMHTKBacVO voDMNH = nhkbDAO.getDMHTKBac(whereClauseKB, params); //lay danh muc ngan hang cua nguoi tra cuu
            if (voDMNH != null) {
                if (AppConstants.MA_TTTT.indexOf(",") > -1) {
                    String[] strFound = AppConstants.MA_TTTT.split(",");
                    for (int i = 0; i < strFound.length; i++) {
                        if (voDMNH.getMa().toString().equalsIgnoreCase(strFound[i])) {
                            bIsTTTT = true;
                            break;
                        }
                    }
                }
                // neu khong phai TTTT se gan ma kb nhan la kho bac cua user dang nhap
                if (!bIsTTTT) {
                    tracuuForm.setMa_kb_nhan(session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString());
                }
                List<TheoDoiHeThongTabmisVO> lstHeThong = new ArrayList<TheoDoiHeThongTabmisVO>();
                String strMsg = dao.sendMessageTabmis(tracuuForm, colThamSoHT);// msgID * note
                if (strMsg != null) {
                    // kiem tra header hop le
                    //            moi getmessage  
                    String strErrorHeader = dao.validError(); //response note********
                    // ErrorCode va ErrorDes rong thi thuc hien getmessage
                    if (STRING_EMPTY.equals(strErrorHeader)) {
                        lstHeThong = dao.getMessageTabmis(); //* note
                        saveVisitLog(conn, session, TTSP_TABMIS,"");
                        if (lstHeThong == null) {
                            strErrorHeader = "errorHT";
                        }
                    }
                    lstHeThong = createTemplate();//template
                    request.setAttribute("errorHeader", strErrorHeader);
                    request.setAttribute("lstHeThong", lstHeThong);
                    forward = AppConstants.SUCCESS;
                }
                request.setAttribute("TTTT", bIsTTTT);
            }
        } catch (Exception ex) {
            throw new Exception("Tra Cuu Chung Tu Sang Tabmis: " + ex);
        } finally {
            close(conn);
        }
        return mapping.findForward(forward);
    }
    private List createTemplate(){
        List<TheoDoiHeThongTabmisVO> lstVO = new ArrayList<TheoDoiHeThongTabmisVO>(); ;
        TheoDoiHeThongTabmisVO vo = new TheoDoiHeThongTabmisVO();
        vo.setTen_but_toan("Temp");
        vo.setLoai_but_toan("Temp");
        vo.setMa_kb_nhan("Temp");
        vo.setDien_giai("Temp");
        vo.setMa_nh_chuyen("Temp");
        vo.setNgay_hieu_luc("Temp");
        vo.setngay_gd("Temp");
        vo.setLoai_tien("Temp");
        vo.setSott_dong("Temp");
        vo.setMa_quy("Temp");
        vo.setError_des("Temp");
        vo.setMa_xuly("Temp");
        vo.setTrang_thai("Temp");
        vo.setMota_trangthai("Temp");
        lstVO.add(vo);
        return lstVO;
    }
}
