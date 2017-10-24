package com.seatech.ttsp.dts.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnhkb.DMNHKBacDAO;
import com.seatech.ttsp.dmnhkb.DMNHKBacVO;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.form.DTSoatForm;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class XuLyDTSoatNoiBoAction extends AppAction {
    private static String TRACUU_NOIBO = "DTS.TS_NOIBO.TRACUU";
    private static String STRING_EMPTY = "";
    private static String FORMAT_DATE = "DD/MM/YYYY";

    public XuLyDTSoatNoiBoAction() {
        super();
    }

    public DMNHKBacVO checkValidTTTT(String codeKB,
                                     Connection conn) throws Exception {
        String whereClauseKB = null;
        Vector paramsKB = new Vector();
        DMNHKBacVO nhkbVO = new DMNHKBacVO();
        DMNHKBacDAO nhkbDAO = new DMNHKBacDAO(conn);
        whereClauseKB = "a.ma_nh = ?";
        paramsKB.add(new Parameter(codeKB, true));
        nhkbVO = nhkbDAO.getDMNHKBac(whereClauseKB, paramsKB);
        return nhkbVO;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        String whereClause = null;
        Vector params = null;
        Vector paramsFind = null;
        List lstDTSNoiBo = null;
        boolean FLAG_TTTT = true;
        String strPhanQuyen = "";
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, TRACUU_NOIBO)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        try {
            HttpSession session = request.getSession();
            strPhanQuyen =
                    (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            Long lUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            
            conn = getConnection();
            StringBuffer szWhereClause = new StringBuffer();
            whereClause =  "";
            if(strPhanQuyen.equals("CB-TTTT")||strPhanQuyen.equals("CBPT-TTTT")){
              whereClause+="  and LOAI_DTS= 'TW' ";
            }
            else{
              whereClause+="  and LOAI_DTS= 'N' ";
            }
                
            DTSoatForm dtsForm = null;
            DTSoatDAO dao = new DTSoatDAO(conn);
            dtsForm = (DTSoatForm)form;
            String actionBack =
                request.getParameter(AppConstants.REQUEST_ACTION);
            if (actionBack != null && actionBack.equalsIgnoreCase("back")) {
                String ttv_nhan_back = request.getParameter("ttv_nhan_back");
                String trang_thai_back =
                    request.getParameter("trang_thai_back");
                String tu_ngay_back = request.getParameter("tu_ngay_back");
                String den_ngay_back = request.getParameter("den_ngay_back");
                String nhkb_chuyen_back =
                    request.getParameter("nhkb_chuyen_back");
                String nhkb_nhan_back = request.getParameter("nhkb_nhan_back");
                String so_dts_back = request.getParameter("so_dts_back");
                String ten_nhkb_chuyen_back =
                    request.getParameter("ten_nhkb_chuyen_back");
                String ten_nhkb_nhan_back =
                    request.getParameter("ten_nhkb_nhan_back");
                if (ttv_nhan_back != null &&
                    !STRING_EMPTY.equals(ttv_nhan_back) &&
                    !ttv_nhan_back.equalsIgnoreCase("null")) {
                    dtsForm.setTtv_nhan(ttv_nhan_back);
                } else {
                    dtsForm.setTtv_nhan(null);
                }
                if (trang_thai_back != null &&
                    !STRING_EMPTY.equals(trang_thai_back) &&
                    !trang_thai_back.equalsIgnoreCase("null")) {
                    dtsForm.setTrang_thai(trang_thai_back);
                } else {
                    dtsForm.setTrang_thai(null);
                }
                if (tu_ngay_back != null &&
                    !STRING_EMPTY.equals(tu_ngay_back) &&
                    !tu_ngay_back.equalsIgnoreCase("null")) {
                    dtsForm.setTu_ngay_lap_dien(tu_ngay_back);
                } else {
                    dtsForm.setNgay_nhan(null);
                }
                if (den_ngay_back != null &&
                    !STRING_EMPTY.equals(den_ngay_back) &&
                    !den_ngay_back.equalsIgnoreCase("null")) {
                    dtsForm.setDen_ngay_lap_dien(den_ngay_back);
                } else {
                    dtsForm.setNgay_duyet(null);
                }
                if (nhkb_chuyen_back != null &&
                    !STRING_EMPTY.equals(nhkb_chuyen_back) &&
                    !nhkb_chuyen_back.equalsIgnoreCase("null")) {
                    dtsForm.setMa_don_vi_tra_soat(nhkb_chuyen_back);
                } else {
                    dtsForm.setMa_don_vi_tra_soat(null);
                }
                if (nhkb_nhan_back != null &&
                    !STRING_EMPTY.equals(nhkb_nhan_back) &&
                    !nhkb_nhan_back.equalsIgnoreCase("null")) {
                    dtsForm.setMa_don_vi_nhan_tra_soat(nhkb_nhan_back);
                } else {
                    dtsForm.setMa_don_vi_nhan_tra_soat(null);
                }
                if (so_dts_back != null && !STRING_EMPTY.equals(so_dts_back) &&
                    !so_dts_back.equalsIgnoreCase("null")) {
                    dtsForm.setId(so_dts_back);
                } else {
                    dtsForm.setId(null);
                }
                if (ten_nhkb_chuyen_back != null &&
                    !STRING_EMPTY.equals(ten_nhkb_chuyen_back)) {
                    dtsForm.setTen_don_vi_tra_soat(ten_nhkb_chuyen_back);
                } else {
                    dtsForm.setTen_don_vi_tra_soat(null);
                }
                if (ten_nhkb_nhan_back != null &&
                    !STRING_EMPTY.equals(ten_nhkb_nhan_back)) {
                    dtsForm.setTen_don_vi_nhan_tra_soat(ten_nhkb_nhan_back);
                } else {
                    dtsForm.setTen_don_vi_nhan_tra_soat(null);
                }
            }
            String page = dtsForm.getPageNumber();
            if (page == null) {
                page = "1";
            }
            // khai bao cac bien phan trang
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage =
                new Integer(AppConstants.APP_NUMBER_ROW_ON_PAGE);
            //            Integer numberRowOnPage =
            //            new Integer(5);
            Integer totalCount[] = new Integer[1];
            // find
            paramsFind = new Vector();
            if (dtsForm.getTtv_nhan() != null &&
                !STRING_EMPTY.equals(dtsForm.getTtv_nhan())) {
                szWhereClause.append(" AND a.ttv_nhan in (select b.id from sp_nsd b where lower(b.ten_nsd)  like " +
                                     "lower(?) or lower(b.ma_nsd)  like lower(?))");
                paramsFind.add(new Parameter("%" +
                                             dtsForm.getTtv_nhan().trim() +
                                             "%", true));
                paramsFind.add(new Parameter("%" +
                                             dtsForm.getTtv_nhan().trim() +
                                             "%", true));
            }
            if (dtsForm.getTrang_thai() != null &&
                !STRING_EMPTY.equals(dtsForm.getTrang_thai())) {
                szWhereClause.append(" AND a.trang_thai = ? ");
                paramsFind.add(new Parameter(dtsForm.getTrang_thai().trim(),
                                             true));
            }
            if (dtsForm.getId() != null &&
                !STRING_EMPTY.equals(dtsForm.getId())) {
                szWhereClause.append(" AND a.id like (?) ");
                paramsFind.add(new Parameter("%" + dtsForm.getId().trim() +
                                             "%", true));
            }
            if ((dtsForm.getTu_ngay_lap_dien() != null &&
                 !STRING_EMPTY.equals(dtsForm.getTu_ngay_lap_dien())) &&
                dtsForm.getDen_ngay_lap_dien() != null &&
                !STRING_EMPTY.equals(dtsForm.getDen_ngay_lap_dien())) {
                // ca 2 deu khong null
                //                strWhere += " and a.ngay_tdoi between TO_DATE('"+f.getTu_ngay()+"','DD-MM-YYYY') AND TO_DATE('"+f.getDen_ngay()+"','DD-MM-YYYY') ";
                szWhereClause.append(" and To_Date(?,'" + FORMAT_DATE +
                                     "') <= trunc(a.ngay_nhan) and trunc(a.ngay_nhan) <= To_Date(?,'" +
                                     FORMAT_DATE + "') ");
                paramsFind.add(new Parameter(dtsForm.getTu_ngay_lap_dien().trim(),
                                             true));
                paramsFind.add(new Parameter(dtsForm.getDen_ngay_lap_dien().trim(),
                                             true));

            } else if (dtsForm.getTu_ngay_lap_dien() != null &&
                       !STRING_EMPTY.equals(dtsForm.getTu_ngay_lap_dien())) {
                // tu ngay khong null
                szWhereClause.append(" and trunc(a.ngay_nhan)  >= To_Date(?,'" +
                                     FORMAT_DATE + "') ");
                paramsFind.add(new Parameter(dtsForm.getTu_ngay_lap_dien().trim(),
                                             true));
            } else if (dtsForm.getDen_ngay_lap_dien() != null &&
                       !STRING_EMPTY.equals(dtsForm.getDen_ngay_lap_dien())) {
                szWhereClause.append(" and trunc(a.ngay_nhan)  >= To_Date(?,'" +
                                     FORMAT_DATE + "') ");
                paramsFind.add(new Parameter(dtsForm.getDen_ngay_lap_dien().trim(),
                                             true));
                // den ngay khong null
            }
            //
            //kiem tra neu la trung tam thanh toan thi moi duyet don vi tra soat
            Long lNHKB_id =
                new Long(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString());
            String htkb_code =
                String.valueOf(session.getAttribute(AppConstants.APP_KB_CODE_SESSION));
            DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
            int kbType = 0;
            DMKBacVO dmkbVO = null;
            String whereClauseKB = " AND ma=?";
            Vector paramkb = new Vector();
            paramkb.add(new Parameter(htkb_code, true));
            dmkbVO = dmkbDAO.getDMKB(whereClauseKB, paramkb);
            String strDelimiter = ",";
            if (AppConstants.MA_TTTT.indexOf(strDelimiter) > -1) {
                String[] strFound = AppConstants.MA_TTTT.split(strDelimiter);
                for (int i = 0; i < strFound.length; i++) {
                    if (dmkbVO.getMa().toString().equalsIgnoreCase(strFound[i])) {
                        kbType = 1;
                        break;
                    } else {
                        kbType = 2;
                    }
                }
            }
            //
            if (kbType == 1) {
                if (dtsForm.getMa_don_vi_tra_soat() != null &&
                    !STRING_EMPTY.equals(dtsForm.getMa_don_vi_tra_soat())) {
                    szWhereClause.append(" and a.nhkb_chuyen in (select b.id from sp_dm_ngan_hang b where b.ma_nh like ?) ");
                    paramsFind.add(new Parameter("%" +
                                                 dtsForm.getMa_don_vi_tra_soat().trim() +
                                                 "%", true));
                }
                if (dtsForm.getMa_don_vi_nhan_tra_soat() != null &&
                    !STRING_EMPTY.equals(dtsForm.getMa_don_vi_nhan_tra_soat())) {
                    szWhereClause.append(" and a.nhkb_nhan in (select b.id from sp_dm_ngan_hang b where b.ma_nh like ?) ");
                    paramsFind.add(new Parameter("%" +
                                                 dtsForm.getMa_don_vi_nhan_tra_soat().trim() +
                                                 "%", true));
                }
            } else {
                FLAG_TTTT = false;
            }
            // neu khong phai la TTTT thi chi tra cuu theo don vi
            if (!FLAG_TTTT) {
              if(strPhanQuyen.equals("CB-TTTT")||strPhanQuyen.equals("CBPT-TTTT")){
                whereClause+="  and LOAI_DTS= 'TW' ";
              }
              else{
                whereClause+="  and LOAI_DTS= 'N' ";
              }
                szWhereClause.append(" and (a.nhkb_chuyen = ? or a.nhkb_nhan = ? ) ");
                paramsFind.add(new Parameter(lNHKB_id, true));
                paramsFind.add(new Parameter(lNHKB_id, true));
            }
            if (paramsFind != null) {
                params = new Vector();
                params.addAll(paramsFind);
            }
            whereClause +=
                    szWhereClause.toString() + "order by a.ngay_nhan desc";
            lstDTSNoiBo =
                    (List)dao.getDTSByConditionSearch(whereClause.toString(),
                                                      params, currentPage,
                                                      numberRowOnPage,
                                                      totalCount);
            saveVisitLog(conn, session, TRACUU_NOIBO, "");
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstTraCuuDTSNoiBo", lstDTSNoiBo);
          request.setAttribute("chucdanh", strPhanQuyen);
            request.setAttribute("FlagTTTT", FLAG_TTTT);

        } catch (Exception e) {
//            e.printStackTrace();
            throw new Exception(" list() : " + e.getMessage());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        Connection conn = null;
        String reportName = "TraCuuDTSNoiBoRptForm";
        String whereClause = "";
        InputStream reportStream = null;
        ResultSet resultSet = null;
        DTSoatForm dtsForm = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = null;
        Vector params = null;
        Vector paramsFind = null;
        boolean FLAG_TTTT = true;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            conn = getConnection();
            // lay ra danh sach trang thai
            dtsForm = (DTSoatForm)form;
            DTSoatDAO dao = new DTSoatDAO(conn);
            HttpSession session = request.getSession();
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                            "/" +
                                                                                            reportName +
                                                                                            ".jasper");
            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
            if (reportStream != null) {
                params = new Vector();
                StringBuffer szWhereClause = new StringBuffer();
                whereClause = "  and LOAI_DTS= 'N' ";
                paramsFind = new Vector();
                if (dtsForm.getTtv_nhan() != null &&
                    !STRING_EMPTY.equals(dtsForm.getTtv_nhan())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"ttv_nhan\" value=\"" +
                                     dtsForm.getTtv_nhan() +
                                     "\" id=\"ttv_nhan\"></input>");
                    szWhereClause.append(" AND a.ttv_nhan in (select b.id from sp_nsd b where lower(b.ten_nsd)  like " +
                                         "lower(?) or lower(b.ma_nsd)  like lower(?))");
                    paramsFind.add(new Parameter("%" +
                                                 dtsForm.getTtv_nhan().trim() +
                                                 "%", true));
                    paramsFind.add(new Parameter("%" +
                                                 dtsForm.getTtv_nhan().trim() +
                                                 "%", true));
                }
                if (dtsForm.getTrang_thai() != null &&
                    !STRING_EMPTY.equals(dtsForm.getTrang_thai())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"trang_thai\" value=\"" +
                                     dtsForm.getTrang_thai() +
                                     "\" id=\"trang_thai\"></input>");
                    szWhereClause.append(" AND a.trang_thai = ? ");
                    paramsFind.add(new Parameter(dtsForm.getTrang_thai().trim(),
                                                 true));
                }
                if (dtsForm.getId() != null &&
                    !STRING_EMPTY.equals(dtsForm.getId())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                                     dtsForm.getId() +
                                     "\" id=\"id\"></input>");
                    szWhereClause.append(" AND a.id like (?) ");
                    paramsFind.add(new Parameter("%" + dtsForm.getId().trim() +
                                                 "%", true));
                }
                if ((dtsForm.getTu_ngay_lap_dien() != null &&
                     !STRING_EMPTY.equals(dtsForm.getTu_ngay_lap_dien())) &&
                    dtsForm.getDen_ngay_lap_dien() != null &&
                    !STRING_EMPTY.equals(dtsForm.getDen_ngay_lap_dien())) {
                    // ca 2 deu khong null
                    //                strWhere += " and a.ngay_tdoi between TO_DATE('"+f.getTu_ngay()+"','DD-MM-YYYY') AND TO_DATE('"+f.getDen_ngay()+"','DD-MM-YYYY') ";
                    sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                                     dtsForm.getTu_ngay_lap_dien() +
                                     "\" id=\"tu_ngay\"></input>");
                    sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay\" value=\"" +
                                     dtsForm.getDen_ngay_lap_dien() +
                                     "\" id=\"den_ngay\"></input>");
                    szWhereClause.append(" and To_Date(?,'" + FORMAT_DATE +
                                         "') <= trunc(a.ngay_nhan) and trunc(a.ngay_nhan) <= To_Date(?,'" +
                                         FORMAT_DATE + "') ");
                    paramsFind.add(new Parameter(dtsForm.getTu_ngay_lap_dien().trim(),
                                                 true));
                    paramsFind.add(new Parameter(dtsForm.getDen_ngay_lap_dien().trim(),
                                                 true));

                } else if (dtsForm.getTu_ngay_lap_dien() != null &&
                           !STRING_EMPTY.equals(dtsForm.getTu_ngay_lap_dien())) {
                    // tu ngay khong null
                    sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay_lap_dien\" value=\"" +
                                     dtsForm.getTu_ngay_lap_dien() +
                                     "\" id=\"tu_ngay_lap_dien\"></input>");
                    szWhereClause.append(" and trunc(a.ngay_nhan)  >= To_Date(?,'" +
                                         FORMAT_DATE + "') ");
                    paramsFind.add(new Parameter(dtsForm.getTu_ngay_lap_dien().trim(),
                                                 true));
                } else if (dtsForm.getDen_ngay_lap_dien() != null &&
                           !STRING_EMPTY.equals(dtsForm.getDen_ngay_lap_dien())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay_lap_dien\" value=\"" +
                                     dtsForm.getDen_ngay_lap_dien() +
                                     "\" id=\"den_ngay_lap_dien\"></input>");
                    szWhereClause.append(" and trunc(a.ngay_nhan)  >= To_Date(?,'" +
                                         FORMAT_DATE + "') ");
                    paramsFind.add(new Parameter(dtsForm.getDen_ngay_lap_dien().trim(),
                                                 true));
                    // den ngay khong null
                }
                //
                //kiem tra neu la trung tam thanh toan thi moi duyet don vi tra soat
                Long lNHKB_id =
                    new Long(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString());
                String htkb_code =
                    String.valueOf(session.getAttribute(AppConstants.APP_KB_CODE_SESSION));
                DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
                int kbType = 0;
                DMKBacVO dmkbVO = null;
                String whereClauseKB = " AND ma=?";
                Vector paramkb = new Vector();
                paramkb.add(new Parameter(htkb_code, true));
                dmkbVO = dmkbDAO.getDMKB(whereClauseKB, paramkb);
                String strDelimiter = ",";
                if (AppConstants.MA_TTTT.indexOf(strDelimiter) > -1) {
                    String[] strFound =
                        AppConstants.MA_TTTT.split(strDelimiter);
                    for (int i = 0; i < strFound.length; i++) {
                        if (dmkbVO.getMa().toString().equalsIgnoreCase(strFound[i])) {
                            kbType = 1;
                            break;
                        } else {
                            kbType = 2;
                        }
                    }
                }
                //
                if (kbType == 1) {
                    if (dtsForm.getMa_don_vi_tra_soat() != null &&
                        !STRING_EMPTY.equals(dtsForm.getMa_don_vi_tra_soat())) {
                        sbSubHTML.append("<input type=\"hidden\" name=\"ma_don_vi_tra_soat\" value=\"" +
                                         dtsForm.getMa_don_vi_tra_soat() +
                                         "\" id=\"ma_don_vi_tra_soat\"></input>");
                        szWhereClause.append(" and a.nhkb_chuyen in (select b.id from sp_dm_ngan_hang b where b.ma_nh like ?) ");
                        paramsFind.add(new Parameter("%" +
                                                     dtsForm.getMa_don_vi_tra_soat().trim() +
                                                     "%", true));
                    }
                    if (dtsForm.getMa_don_vi_nhan_tra_soat() != null &&
                        !STRING_EMPTY.equals(dtsForm.getMa_don_vi_nhan_tra_soat())) {
                        sbSubHTML.append("<input type=\"hidden\" name=\"ma_don_vi_nhan_tra_soat\" value=\"" +
                                         dtsForm.getMa_don_vi_nhan_tra_soat() +
                                         "\" id=\"ma_don_vi_nhan_tra_soat\"></input>");
                        szWhereClause.append(" and a.nhkb_nhan in (select b.id from sp_dm_ngan_hang b where b.ma_nh like ?) ");
                        paramsFind.add(new Parameter("%" +
                                                     dtsForm.getMa_don_vi_nhan_tra_soat().trim() +
                                                     "%", true));
                    }
                } else {
                    FLAG_TTTT = false;
                }
                // neu khong phai la TTTT thi chi tra cuu theo don vi
                if (!FLAG_TTTT) {
                    szWhereClause.append(" and a.nhkb_chuyen = ? ");
                    paramsFind.add(new Parameter(lNHKB_id, true));
                }
                if (paramsFind != null) {
                    params = new Vector();
                    params.addAll(paramsFind);
                }
                whereClause +=
                        szWhereClause.toString() + "order by a.ngay_nhan desc";
                resultSet = dao.getDTSByPrint(whereClause, params);
                if (resultSet == null) {
                    request.setAttribute("status",
                                         "tra_cuu_dts.page.warning.ketqua.null");
                } else {
                    parameterMap = new HashMap();
                    parameterMap.put("SESS_ID",
                                     session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
                    parameterMap.put("REPORT_LOCALE",
                                     new java.util.Locale("vi", "VI"));
                    jasperPrint =
                            JasperFillManager.fillReport(reportStream, parameterMap,
                                                         new JRResultSetDataSource(resultSet));
                    String strTypePrintAction =
                        request.getParameter(AppConstants.REQUEST_ACTION) ==
                        null ? "" :
                        request.getParameter(AppConstants.REQUEST_ACTION).toString();
                    String strActionName = "tracuudtsNoiBoRptAction.do";
                    String strParameter = "";
                    ReportUtility rpUtilites = new ReportUtility();
                    rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                            response, reportName, strPathFont,
                                            strActionName,
                                            sbSubHTML.toString(),
                                            strParameter);

                }
            } else
                System.out.println("khong tim thay file jasper");
        } catch (Exception e) {
//            e.printStackTrace();
            throw e;
        } finally {
            if (reportStream != null)
                reportStream.close();
            conn.close();
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
