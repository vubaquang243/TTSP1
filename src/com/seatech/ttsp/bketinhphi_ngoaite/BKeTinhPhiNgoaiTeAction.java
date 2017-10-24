package com.seatech.ttsp.bketinhphi_ngoaite;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.bketinhphi.BKeTinhPhiDAO;
import com.seatech.ttsp.dchieu.BKeDChieuDAO;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.InputStream;

import java.sql.Connection;

import java.util.Collections;
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

public class BKeTinhPhiNgoaiTeAction extends AppAction {
    @Override
    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
             conn = getConnection(request);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            BKeTinhPhiNgoaiTeDAO bkeTinhPhiNgoaiTeDAO = new BKeTinhPhiNgoaiTeDAO(conn);
            BKeTinhPhiNgoaiTeForm frm = (BKeTinhPhiNgoaiTeForm)form;
            pullDMKhoBacTinh(request, conn);
            List dmTienTe = tienTeDAO.simpleMaNgoaiTe("",null);
            
            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            String page = frm.getPageNumber();
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];
            
            List listPhiDetail = null;
            String condition = null;
            //firstTime
            if(!isTokenValid(request)){
                HttpSession session = request.getSession();
                String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
                if(request.getAttribute("QTHTTW") == null){
                    condition = " AND c.shkb = '" + kb_code+"'";
                }
            }
            resetToken(request);
            saveToken(request);
            //firstTime
            Map mapCondition = getPhiNgoaiTePTCondition(frm);
            condition = condition == null ? mapCondition.get("condition").toString() : condition;
            if(listPhiDetail == null)
              listPhiDetail =
                bkeTinhPhiNgoaiTeDAO.getListBKeTinhPhiNgoaiTe_PTrang(
                    condition, (Vector)mapCondition.get("params") , currentPage, numberRowOnPage, totalCount);
            
            request.setAttribute("listPhiDetail", listPhiDetail);
            request.setAttribute("dmTienTe", dmTienTe);
            request.setAttribute("idxTK", frm.getSoTk());
            
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
    
    @Override
    protected ActionForward printAction(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            BKeTinhPhiNgoaiTeDAO bkeTinhPhiNgoaiTeDAO = new BKeTinhPhiNgoaiTeDAO(conn);
            TKNHKBacDAO tknhkBacDAO = new TKNHKBacDAO(conn);
            HashMap reportParams = new HashMap();
            InputStream reportStream = null;
            JasperPrint jasperPrint = null;
            StringBuffer sbSubHTML = new StringBuffer();
            
            String reportName = "";
            String type = request.getParameter("type").toString();
            if(type.equals("tonghop")){
                reportName = "/report/ReportBKeTinhPhiNgoaiTe_TongHop.jasper";
            }else if(type.equals("chitiet")){
                reportName = "/report/ReportBKeTinhPhiNgoaiTe_ChiTiet.jasper";
            }
            String strAction = request.getParameter(AppConstants.REQUEST_ACTION);
            String strPathFont = getServlet().getServletContext().getContextPath() + AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
            Vector vParam = new Vector();
            
            //Theo tung bang ke 
            vParam.add(new Parameter(request.getParameter("id"),true));
            BKeTinhPhiNgoaiTeVO instanceBKePhiNT = bkeTinhPhiNgoaiTeDAO.findBKePhiNgoaiTe(vParam);
            //
            
//            String strWhere = "AND e.ma_nh = ? AND b.ma_nh = ? AND a.ma_nt = 'VND' ";
            String strWhere = "AND e.ma_nh = ? AND b.ma_nh = ? AND a.so_tk = '"+instanceBKePhiNT.getSo_tk()+"' ";
            vParam.clear();
            vParam.add(new Parameter(instanceBKePhiNT.getReceive_bank(),true)); 
            vParam.add(new Parameter(instanceBKePhiNT.getSend_bank(),true));
            TKNHKBacVO tknhkBac = tknhkBacDAO.getTK_NH_KB_VO_2(strWhere, vParam);

            reportParams.put("P_ID", instanceBKePhiNT.getId());
            reportParams.put("P_MA_NH", instanceBKePhiNT.getSend_bank());
            reportParams.put("P_SO_TK", tknhkBac.getSo_tk());
            reportParams.put("P_TEN_HT_NH", tknhkBac.getTen());
            reportParams.put("P_TEN_NH", tknhkBac.getTen_nh());
            reportParams.put("P_MA_KB", tknhkBac.getMa_kb());
            reportParams.put("P_TEN_KB", tknhkBac.getTen_kb());
            reportParams.put("P_LOAI_TK", tknhkBac.getLoai_tk());
            if (type.equals("tonghop"))
                reportParams.put("P_PHI_BANG_CHU", instanceBKePhiNT.getTien_bang_chu());
            
            reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(reportName);
            jasperPrint = JasperFillManager.fillReport(reportStream, reportParams, conn);
            String strTypePrintAction = strAction == null ? "" : strAction;
            String strActionName = "printBKeTinhPhiNgoaiTe.do";
            String strParameter = "";
            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    reportName, strPathFont, strActionName,
                                    sbSubHTML.toString(), strParameter);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
    
    private void pullDMKhoBacTinh(HttpServletRequest request, Connection conn) throws Exception {
        HttpSession session = request.getSession();
        
        String kb_code = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
        String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
        
        DChieu1DAO dao = new DChieu1DAO(conn);
        DChieu1VO vo = new DChieu1VO();
        List dmucNH = null;
        TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
        dmucNH = (List)TTdao.getDMucNH(null,null);
        request.setAttribute("dmucNH", dmucNH);
        
        String strCap = " and ma=" + kb_code;
        vo = dao.getCap(strCap, null);
        List dmuckb_cha = null;
        String cap = vo.getCap();
        //ThuongDT-sua chi load DB tinh-20/10/2016-begin
        if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
            String strWhere = " "; //AND cap=5 OR ma='0003'
            dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
            request.setAttribute("QTHTTW", "QTHTTW");
        } else if ("0003".equals(kb_code)) {
            String strWhere = " AND a.ma='0003' ";
            dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
        } else if ("5".equals(cap)) {
            String strWhere = " and c.ma=" + kb_code;
            dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
        } else {
            String strWhere = " and c.id in (select id_cha from sp_dm_htkb where ma=" + kb_code + ")";
            dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
            request.setAttribute("dmuckb_tinh", dmuckb_cha);
        }
		//ThuongDT-sua chi load DB tinh-20/10/2016-end
    }

    private Map getPhiNgoaiTePTCondition(BKeTinhPhiNgoaiTeForm frm) {
        //Trong // dung de debug
        StringBuilder condition = new StringBuilder();
        Vector params = new Vector();
        
        if(frm.getSendBank() != null && !"".equals(frm.getSendBank().trim())){
          condition.append(" AND a.send_bank = ?");
        //          condition.append(" AND a.send_bank = '"+frm.getSendBank().trim()+"'");
          params.add(new Parameter(frm.getSendBank(),true));
        }
        if(frm.getLoaiTien() != null && !"".equals(frm.getLoaiTien().trim())){
          condition.append(" AND e.ma_nt = ? ");
        //          condition.append(" AND b.ma_nt = '"+frm.getLoaiTien()+"' ");
          params.add(new Parameter(frm.getLoaiTien(),true));
        }
        if(frm.getTuNgay() != null && !"".equals(frm.getTuNgay().trim())){
          condition.append(" AND b.ngay_tt >= to_date(?,'dd/mm/yyyy')");
        //          condition.append(" AND b.ngay_tt >= to_date('"+frm.getTuNgay()+"','dd/mm/yyyy')");
          params.add(new Parameter(frm.getTuNgay(),true));
        }
        if(frm.getDenNgay() != null && !"".equals(frm.getDenNgay().trim())){
          condition.append(" AND b.ngay_tt <= to_date(?,'dd/mm/yyyy')");
        //          condition.append(" AND b.ngay_tt <= to_date('"+frm.getDenNgay()+"','dd/mm/yyyy')");
          params.add(new Parameter(frm.getDenNgay(),true));
        }
        if(frm.getSoTk() != null && !"".equals(frm.getSoTk().trim())){
          condition.append(" AND a.so_tk = ?");
        //          condition.append(" AND a.so_tk = '"+frm.getSoTk()+"'");
          params.add(new Parameter(frm.getSoTk(),true));
        }
        Map result = new HashMap();
        result.put("condition", condition);
        result.put("params", params);
        return result;
    }
}
