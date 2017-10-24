package com.seatech.ttsp.qlydmsohieukbmanh.dmsohieukbmadvsdns;

import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.qlydmsohieukbmanh.DMSoHieuKBMaNHAction;
import com.seatech.ttsp.qlydmsohieukbmanh.DMSoHieuKBMaNHDAO;
import com.seatech.ttsp.qlydmsohieukbmanh.DMSoHieuKBMaNHForm;
import com.seatech.ttsp.qlydmsohieukbmanh.DMSoHieuKBMaNHVO;

import java.sql.Connection;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DMSoHieuKBMaDVSDNSAction extends AppAction {
    public static final int STATUS_UNSUCCESS = 1;
    public static final int STATUS_SUCCESS = 2;
    public static final int STATUS_FALSE = 3;
    public static final int STATUS_EXIST = 4;

    public DMSoHieuKBMaDVSDNSAction() {
        super();
    }


    @Override
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        saveToken(request);
        return mapping.findForward("success");
    }

    @Override
    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                HttpSession session = request.getSession();
                conn = getConnection();
                DMSoHieuKBMaNHForm f = (DMSoHieuKBMaNHForm)form;
                DMSoHieuKBMaDVSDNSVO vo = new DMSoHieuKBMaDVSDNSVO();
                DMSoHieuKBMaDVSDNSDAO dao = new DMSoHieuKBMaDVSDNSDAO(conn);
                BeanUtils.copyProperties(vo, f);
                vo.setMa_kb(f.getShkb());
                boolean flag = true;
                if (flag) {
                    DMKBacVO kbVO = kbExist(conn, f);
                    if (kbVO == null) {
                        request.setAttribute("status", STATUS_UNSUCCESS);
                        flag = false;
                    }
                }

                if (flag) {
                    String strWhere = " MA_KB = ? OR MA_DVSDNS = ? AND ROWNUM = 1";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(f.getShkb(), true));
                    vParam.add(new Parameter(f.getMa_dvsdns(), true));
                    DMSoHieuKBMaDVSDNSVO tempVO = dao.getDMSoHieuKBMaDVSDNS(strWhere, vParam);
                    if (tempVO != null) {
                        request.setAttribute("status", STATUS_EXIST);
                        flag = false;
                    }
                }
                
                if (flag) {
                    int i = dao.insert(vo);
                    if (i > 0) {
                        request.setAttribute("status", STATUS_SUCCESS);
                        saveVisitLog(conn, session, "Thêm danh m?c ch??ng thành công", "");
                        f.setMa_nh(null);
                        f.setShkb(null);
                        f.reset(mapping, request);
                        conn.commit();
                        list(mapping, f, request, response);
                    } else {
                        request.setAttribute("status", STATUS_FALSE);
                    }
                }
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    private DMKBacVO kbExist(Connection conn,
                             DMSoHieuKBMaNHForm f) throws Exception {
        //Kiem tra da ton tai kho bac chua
        String strWhere = " AND ma = ? AND tinhtrang = ? ";
        Vector vParam = new Vector();
        vParam.add(new Parameter(f.getShkb(), true));
        vParam.add(new Parameter(1, true));
        DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
        DMKBacVO kbVO = dmkbDAO.getDMKB(strWhere, vParam);
        return kbVO;
    }
}
