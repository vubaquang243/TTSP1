package com.seatech.ttsp.qlydmsohieukbmanh;

import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;

import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;

import com.seatech.ttsp.qlydmsohieukbmanh.dmsohieukbmadvsdns.DMSoHieuKBMaDVSDNSDAO;
import com.seatech.ttsp.qlydmsohieukbmanh.dmsohieukbmadvsdns.DMSoHieuKBMaDVSDNSVO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DMSoHieuKBMaNHAction extends AppAction {

    public static final int STATUS_UNSUCCESS = 1;
    public static final int STATUS_SUCCESS = 2;
    public static final int STATUS_FALSE = 3;
    public static final int STATUS_EXIST_MANHSHKB = 5;
    public static final int STATUS_EXIST_MADVSDNS = 6;
    public static final int STATUS_MANH_FALSE = 7;

    public DMSoHieuKBMaNHAction() {
        super();
    }

    @Override
    public ActionForward list(ActionMapping mapping, ActionForm actionForm,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            DMSoHieuKBMaNHForm f = (DMSoHieuKBMaNHForm)actionForm;
            DMSoHieuKBMaNHDAO dmDataAccess = new DMSoHieuKBMaNHDAO(conn);
            //            TTThanhToanDAO ttDAO = new TTThanhToanDAO(conn);
            Vector v_param = null;
            List listSoHieuKBMaNH = new ArrayList();

            String pageNumber = f.getPageNumber();
            if (pageNumber == null)
                pageNumber = "1";
            int currentPage = Integer.parseInt(pageNumber);
            int numberRowOnPage = 20;
            Integer totalCount[] = new Integer[1];

            if (f != null) {
                String nhkb_tinh = f.getNhkb_tinh();
                String nhkb_huyen = f.getNhkb_huyen();
                String ma_nh = f.getMa_nh();
                String shkb = f.getShkb();
                String strWhere = createConditionSQL(nhkb_tinh, nhkb_huyen, ma_nh, shkb);

                listSoHieuKBMaNH = (List)dmDataAccess.getDMSoHieuKBMaNH_phantrang(strWhere,
                                                                       v_param,
                                                                       currentPage,
                                                                       numberRowOnPage,
                                                                       totalCount);
            }
            doPaging(request, listSoHieuKBMaNH, currentPage, numberRowOnPage,
                     totalCount);
            PutListKBTinh(dmDataAccess, request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    private void PutListKBTinh(DMSoHieuKBMaNHDAO dmDataAccess,
                               HttpServletRequest request) throws Exception {
        List kbTinh = (List)dmDataAccess.getKBTinh();
        request.setAttribute("dmucKBTinh", kbTinh);
    }

    private void doPaging(HttpServletRequest request, List list,
                          int currentPage, int numberRowOnPage,
                          Integer[] totalCount) {
        PagingBean pagingBean = new PagingBean();
        pagingBean.setCurrentPage(currentPage);
        pagingBean.setNumberOfRow(totalCount[0].intValue());
        pagingBean.setRowOnPage(numberRowOnPage);
        request.setAttribute("PAGE_KEY", pagingBean);
        request.setAttribute("dmSoHieuKBMaNHList", list);
    }

    private String createConditionSQL(String strNHKBT, String strNHKBH,
                                      String strMaHN, String shkb) {
        String strWhere;
        if (strNHKBT != null && !strNHKBT.equals("")) {
            strWhere = " and c.id_cha = '" + strNHKBT + "' ";
        } else {
            strWhere = "";
        }
        if (strNHKBH != null && !strNHKBH.equals("")) {
            strWhere += " and c.id = " + strNHKBH + "";
        }
        if (strMaHN != null && !strMaHN.equals("")) {
            strWhere += " and lower(ma_nh) like lower('%" + strMaHN + "%') ";
        }
        if (shkb != null && !shkb.equals("")) {
            strWhere += " and lower(a.shkb) like lower('%" + shkb + "%') ";
        }
        //AND c.ma_cha = '0010' AND c.id = 7 AND ma_nh = ? AND a.shkb = ?
        return strWhere;
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        saveToken(request);
        return mapping.findForward("success");
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                HttpSession session = request.getSession();
                conn = getConnection();
                DMSoHieuKBMaNHForm f = (DMSoHieuKBMaNHForm)form;
                DMSoHieuKBMaNHVO SHKBMNHvo = new DMSoHieuKBMaNHVO();
                DMSoHieuKBMaNHDAO SHKBMNHdao = new DMSoHieuKBMaNHDAO(conn);
                BeanUtils.copyProperties(SHKBMNHvo, f);
                
                DMSoHieuKBMaDVSDNSVO MADVSDNSvo = new DMSoHieuKBMaDVSDNSVO();
                DMSoHieuKBMaDVSDNSDAO MADSVDNSdao = new DMSoHieuKBMaDVSDNSDAO(conn);
                MADVSDNSvo.setMa_kb(f.getShkb());
                MADVSDNSvo.setMa_dvsdns(f.getMa_dvsdns());
                
                boolean flag = true;
                //Kiem tra da ton tai kho bac chua
                DMKBacVO kbVO = null;
                if (flag) {
                    kbVO = kbExist(conn, f);
                    if (kbVO == null) {
                        request.setAttribute("status", STATUS_UNSUCCESS);
                        flag = false;
                    }
                }
                
                if(flag){
                    if(SHKBMNHvo.getMa_nh() != null && !"".equals(SHKBMNHvo.getMa_nh())){
                        String strWhere = " SHKB = ? OR MA_NH = ? AND ROWNUM = 1";
                        Vector vParam = new Vector();
                        vParam.add(new Parameter(SHKBMNHvo.getShkb(), true));
                        vParam.add(new Parameter(SHKBMNHvo.getMa_nh(), true));
                        DMSoHieuKBMaNHVO tempVO = SHKBMNHdao.getDMSoHieuKBMaNH(strWhere, vParam);
                        if (tempVO != null) {
                            request.setAttribute("status", STATUS_EXIST_MANHSHKB);
                            flag = false;
                        }
                    }
                    if(MADVSDNSvo.getMa_dvsdns() != null && !"".equals(MADVSDNSvo.getMa_dvsdns())){
                        String strWhereDVSDNS = " MA_KB = ? OR MA_DVSDNS = ? AND ROWNUM = 1";
                        Vector vParamDVSDNS = new Vector();
                        vParamDVSDNS.add(new Parameter(f.getShkb(), true));
                        vParamDVSDNS.add(new Parameter(f.getMa_dvsdns(), true));
                        DMSoHieuKBMaDVSDNSVO tempVO = MADSVDNSdao.getDMSoHieuKBMaDVSDNS(strWhereDVSDNS, vParamDVSDNS);                    
                        if (tempVO != null) {
                            request.setAttribute("status", STATUS_EXIST_MADVSDNS);
                            flag = false;
                        }
                    }
                }
                
                if (flag) {
                    if(SHKBMNHvo.getMa_nh() != null && !"".equals(SHKBMNHvo.getMa_nh())){
                        SHKBMNHvo.setTen(kbVO.getTen());
                        int result = SHKBMNHdao.insert(SHKBMNHvo);
                        if(result <= 0) throw new Exception("Insert error");
                    }
                    if(MADVSDNSvo.getMa_dvsdns() != null && !"".equals(MADVSDNSvo.getMa_dvsdns())){
                        int result = MADSVDNSdao.insert(MADVSDNSvo);
                        if(result <= 0) throw new Exception("Insert error");
                    }
                    request.setAttribute("status", STATUS_SUCCESS);
                    saveVisitLog(conn, session, "Them thanh cong", "");
                    f.reset(mapping, request);
                    conn.commit();
                    list(mapping, f, request, response);
                }
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            request.setAttribute("status", STATUS_FALSE);
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

    @Override
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        DMSoHieuKBMaNHForm f = (DMSoHieuKBMaNHForm)form;
        f.setShkb(request.getParameter("shkb").trim());
        f.setMa_nh(request.getParameter("ma_nh").trim());
        f.setTen(request.getParameter("ten").trim());
        f.setMa_dvsdns(request.getParameter("ma_dvsdns").trim());
        f.setCap(request.getParameter("cap").trim());
        f.setMa_t(request.getParameter("ma_t").trim());
        f.setMa_h(request.getParameter("ma_h").trim());
        f.setId_cha(request.getParameter("id_cha").trim());
        resetToken(request);
        saveToken(request);
        return mapping.findForward("success");
    }

    @Override
    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            if (isTokenValid(request)) {
                HttpSession session = request.getSession();
                conn = getConnection();
                DMSoHieuKBMaNHForm f = (DMSoHieuKBMaNHForm)form;
                boolean flag = true;
                DMSoHieuKBMaNHVO vo = new DMSoHieuKBMaNHVO();
                BeanUtils.copyProperties(vo, f);
                DMSoHieuKBMaNHDAO dao = new DMSoHieuKBMaNHDAO(conn);
                int result = 0;

                if (flag) {
                    DMNHangDAO nhangDAO = new DMNHangDAO(conn);
                    String strWhere = " MA_NH = ? AND TINH_TRANG = 1";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(f.getMa_nh(), true));
                    DMNHangVO nhVO = nhangDAO.getDMNH(strWhere, vParam);
                    if (nhVO == null) {
                        request.setAttribute("status", STATUS_MANH_FALSE);
                        flag = false;
                    }
                }

                if (flag) {
                    result = dao.update(vo);
                }
                if (result > 0) {
                    request.setAttribute("status", STATUS_SUCCESS);
                    saveVisitLog(conn, session, "Sua shkbmanh thanh cong", "");
                    f.setShkb(null);
                    f.setTen(null);
                    f.setMa_dvsdns(null);
                    f.setMa_nh(null);
                    f.reset(mapping, request);
                    conn.commit();
                    list(mapping, f, request, response);
                    return mapping.findForward("success");
                } else {
                    request.setAttribute("status", STATUS_FALSE);
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
}
