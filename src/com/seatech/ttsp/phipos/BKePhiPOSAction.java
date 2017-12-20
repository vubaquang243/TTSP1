package com.seatech.ttsp.phipos;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.InputStream;

import java.sql.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @modify: thuongdt
 * @modify-date: 27/06/2017
 * @see: cho phep user trung uong tra cuu tat ca
 * @find:20170627
 * 
 * */
public class BKePhiPOSAction extends AppAction {
    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String fileName = "/ReportBKePhiPOS";

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);

            BKePhiPOSDAO bkePhiPOSDAO = new BKePhiPOSDAO(conn);
            BKePhiPOSForm frm = (BKePhiPOSForm)form;
            pullDMKhoBacTinh(request, conn);


            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            String page = frm.getPageNumber();
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];

            List listPhiPOSDetail = null;
            String condition = "";
            HttpSession session = request.getSession();
            String ma_nhkb =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            //20170627 bo sung co phep trung uong co the tra cuu
            if (!"".equals(ma_nhkb) && ma_nhkb != null && !ma_nhkb.equals("01701001") ) {
                condition = " AND a.ma_kb = '" + ma_nhkb + "' ";
            }


            //firstTime
            Map mapCondition = getPhiPOSQueryCondition(frm);
            condition =
                    mapCondition != null ? condition + mapCondition.get("condition").toString() :
                    condition;
            if (listPhiPOSDetail == null)
                listPhiPOSDetail =
                        bkePhiPOSDAO.getListBKePhiPOSPhanTrang(condition,
                                                               (Vector)mapCondition.get("params"),
                                                               currentPage,
                                                               numberRowOnPage,
                                                               totalCount);

            request.setAttribute("listLaiDetail", listPhiPOSDetail);

            request.setAttribute("idxTK", frm.getSo_tk());

            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(15);
            request.setAttribute("PAGE_KEY", pagingBean);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);

            BKePhiPOSDAO bkePhiPOSDAO = new BKePhiPOSDAO(conn);
            BKePhiPOSForm frm = (BKePhiPOSForm)form;

            List listPhiPOSCTiet = null;

            String strSQLWhere = " and mt_id = ?";
            Vector vtParam = new Vector();
            vtParam.add(new Parameter(frm.getMt_id(), true));

            if (listPhiPOSCTiet == null)
                listPhiPOSCTiet =
                        bkePhiPOSDAO.getCTietPhiPOS(strSQLWhere, vtParam);

            request.setAttribute("listPhiPOSCTiet", listPhiPOSCTiet);


        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    /**
     * View chi tiet giao dich POS
     * */
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);

            BKePhiPOSDAO bkePhiPOSDAO = new BKePhiPOSDAO(conn);
            BKePhiPOSForm frm = (BKePhiPOSForm)form;


            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            String page = frm.getPageNumber();
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];

            List listGDPOSDetail = null;
            String condition =
                " AND b.ma_kb = ? AND b.ma_nh = ? AND b.ngay_bk = TO_DATE(?,'DD/MM/YYYY') ";

            Vector vtParam = new Vector();
            vtParam.add(new Parameter(frm.getMa_kb_2(), true));
            vtParam.add(new Parameter(frm.getMa_nh_2(), true));
            vtParam.add(new Parameter(frm.getNgay_gd(), true));


            listGDPOSDetail =
                    bkePhiPOSDAO.getListGDPOS(condition, vtParam, currentPage,
                                              numberRowOnPage, totalCount);

            request.setAttribute("listGDPOSDetail", listGDPOSDetail);


            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(15);
            request.setAttribute("PAGE_KEY", pagingBean);


        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection();
            BKePhiPOSForm frm = (BKePhiPOSForm)form;

            HttpSession session = request.getSession();

            String strMtId = frm.getMt_id() == null ? "" : frm.getMt_id();


            sbSubHTML.append("<input type=\"hidden\" name=\"mt_id\" value=\"" +
                             strMtId + "\" id=\"ngan_hang\"></input>");

            JasperPrint jasperPrint = null;
            HashMap parameterMap = new HashMap();
            ReportUtility rpUtilites = new ReportUtility();

           
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                            fileName +
                                                                                            ".jasper");
            parameterMap.put("P_ID", strMtId);


            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 conn);

            String strTypePrintAction =
                request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                "" :
                request.getParameter(AppConstants.REQUEST_ACTION).toString();
            String strActionName = "printBKePhiPOSAction.do";
            String strParameter = "";
            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                REPORT_DIRECTORY + strFontTimeRoman;

            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    fileName, strPathFont, strActionName,
                                    sbSubHTML.toString(), strParameter);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                reportStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            close(conn);
        }

        return mapping.findForward("success");
    }

    private void pullDMKhoBacTinh(HttpServletRequest request,
                                  Connection conn) throws Exception {
        HttpSession session = request.getSession();

        String kb_code =
            session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
        //      String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

        DChieu1DAO dao = new DChieu1DAO(conn);
        DChieu1VO vo = new DChieu1VO();
        List dmucNH = null;
        TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
        dmucNH = (List)TTdao.getDMucNH(null, null);
        request.setAttribute("dmucNH", dmucNH);

        String strCap = " and ma=" + kb_code;
        vo = dao.getCap(strCap, null);
        List dmuckb_cha = null;
        String cap = vo.getCap();

        if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
            String strWhere = " "; //AND cap=5 OR ma='0003'
            dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
            request.setAttribute("QTHTTW", "QTHTTW");
        } else if ("0003".equals(kb_code)) {
            String strWhere = " AND a.ma='0003' ";
            dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
        } else if ("5".equals(cap)) {
            String strWhere = " and c.ma=" + kb_code;
            dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
        } else {
            String strWhere =
                " and c.id in (select id_cha from sp_dm_htkb where ma=" +
                kb_code + ")";
            dmuckb_cha = (List)dao.getDMucKB_cha(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
        }
    }

    private Map getPhiPOSQueryCondition(BKePhiPOSForm frm) {

        StringBuilder condition = new StringBuilder();
        Vector params = new Vector();

        if (frm.getMa_nh() != null && !"".equals(frm.getMa_nh().trim())) {
            condition.append(" AND a.ma_nh = ?");
            params.add(new Parameter(frm.getMa_nh(), true));
        }
        if (frm.getTu_ngay() != null && !"".equals(frm.getTu_ngay().trim())) {
            condition.append(" AND a.den_ngay >= to_date(?,'dd/mm/yyyy')");
            params.add(new Parameter(frm.getTu_ngay(), true));
        }
        if (frm.getDen_ngay() != null &&
            !"".equals(frm.getDen_ngay().trim())) {
            condition.append(" AND a.den_ngay <= to_date(?,'dd/mm/yyyy')");
            params.add(new Parameter(frm.getDen_ngay(), true));
        }
        if (frm.getSo_tk() != null && !"".equals(frm.getSo_tk().trim())) {
            condition.append(" AND a.so_tk = ?");
            params.add(new Parameter(frm.getSo_tk(), true));
        }

        Map result = new HashMap();
        result.put("condition", condition);
        result.put("params", params);
        return result;
    }
}
