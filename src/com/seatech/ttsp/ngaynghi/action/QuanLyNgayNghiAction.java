package com.seatech.ttsp.ngaynghi.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.ngaynghi.NgayNghiDAO;
import com.seatech.ttsp.ngaynghi.NgayNghiVO;
import com.seatech.ttsp.ngaynghi.form.NgayNghiForm;

import java.io.InputStream;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/*
 * @modify name: ThuongDT
 * @modify date: 01/03/2017
 * @see: sua loi xoa ngay nghi ca thang khi nguoi su dung thuc hien xoa ngay nghi theo ngay
 * @find: 20170301-thuongdt
 * */
 /*
 * @modify name: HungBM
 * @modify date: 27/4/2017
 * @see: sua loi thay doi ngay nghi sau khi tim kiem 1 ngay duy nhat
 * @find: 20170427-hungbm
 * */

public class QuanLyNgayNghiAction extends AppAction {
    public QuanLyNgayNghiAction() {
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
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        Collection colNgayNghi = null;
        Vector params = null;
        Connection conn = null;
        try {
            NgayNghiForm ngaynghiForm = (NgayNghiForm)form;
            HttpSession session = request.getSession();
            if (session != null) {
                if (conn == null || conn.isClosed())
                    conn = getConnection(request);
                //                Vector  paramsFind=new Vector();
                params = new Vector();

                NgayNghiDAO ngaynghiDAO = new NgayNghiDAO(conn);

                colNgayNghi =
                        ngaynghiDAO.getDSNgayTrongThang(ngaynghiForm.getNgaytk(),
                                                        ngaynghiForm.getThangtk(),
                                                        ngaynghiForm.getNamtk());
            }
            request.setAttribute("colNgayNghi", colNgayNghi);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            conn.close();
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Collection colNgayNghi = null;
        Vector params = null;
        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        String fileName = "/dsachNgayNghi";
        try {
            NgayNghiForm ngaynghiForm = (NgayNghiForm)form;
            if (ngaynghiForm.getNgaytk() != null &&
                !"".equals(ngaynghiForm.getNgaytk())) {
                sbSubHTML.append("<input type=\"hidden\" name=\"ngaytk\" value=\"" +
                                 ngaynghiForm.getNgaytk() +
                                 "\" id=\"ngaytk\"></input>");
            }
            if (ngaynghiForm.getNamtk() != null &&
                !"".equals(ngaynghiForm.getNamtk())) {
                sbSubHTML.append("<input type=\"hidden\" name=\"namtk\" value=\"" +
                                 ngaynghiForm.getNamtk() +
                                 "\" id=\"namtk\"></input>");
            }
            if (ngaynghiForm.getThangtk() != null &&
                !"".equals(ngaynghiForm.getThangtk())) {
                sbSubHTML.append("<input type=\"hidden\" name=\"thangtk\" value=\"" +
                                 ngaynghiForm.getThangtk() +
                                 "\" id=\"thangtk\"></input>");
            }
            HttpSession session = request.getSession();
            if (session != null) {
                if (conn == null || conn.isClosed())
                    conn = getConnection(request);
                params = new Vector();
                NgayNghiDAO ngaynghiDAO = new NgayNghiDAO(conn);
                colNgayNghi =
                        ngaynghiDAO.getDSNgayTrongThang(ngaynghiForm.getNgaytk(),
                                                        ngaynghiForm.getThangtk(),
                                                        ngaynghiForm.getNamtk());
            }
            JasperPrint jasperPrint = null;
            HashMap parameterMap = null;

            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                            fileName +
                                                                                            ".jasper");
            JRDataSource jrDS = new JRBeanCollectionDataSource(colNgayNghi);
            
            parameterMap = new HashMap();
            parameterMap.put("P_THANG", ngaynghiForm.getThangtk());
            
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 jrDS);
            String strTypePrintAction =
                request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                "" :
                request.getParameter(AppConstants.REQUEST_ACTION).toString();
            String strActionName = "inNgayNghi.do";
            String strParameter = "";
            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    fileName, strPathFont, strActionName,
                                    sbSubHTML.toString(), strParameter);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            conn.close();
            try {
                reportStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        String ngay_tk = "";
        Connection conn = null;
        try {
            //            if (isTokenValid(request)) {
            HttpSession session = request.getSession();
            if (session != null) {
                if (conn == null || conn.isClosed())
                    conn = getConnection(request);
                NgayNghiForm ngaynghiForm = (NgayNghiForm)form;
                Long nUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                //Ghi log truy cap
                saveVisitLog(conn, session, "QLY.NGAY.NGHI", "Cap nhat ngay nghi");

                String thang = ngaynghiForm.getThangtk();
                if (Integer.parseInt(thang) < 10) {
                    thang = "0" + thang;
                }
                
                /*20170301-thuongdt sua cap nhat ngay nghi theo tung ngay begin */
                String thangnam = ngaynghiForm.getNamtk().toString() + thang;               
                NgayNghiDAO ngaynghiDAO = new NgayNghiDAO(conn);
                String[] arrCheck = request.getParameterValues("chklist");
                String[] arrdateCheck = request.getParameterValues("dlist");
                /* 20170427-hungbm - Kiem tra danh sach ngay nghi dang hien thi co gia tri hay khong - BEGIN */
                if(arrdateCheck != null){               
                     for(int j = 0; j< arrdateCheck.length; j++){
                      ngay_tk =  arrdateCheck[j]; 
                      ngaynghiDAO.deleteNgayNghi(ngay_tk);
                    }
                  }
                /* 20170427-hungbm - Kiem tra danh sach ngay nghi dang hien thi co gia tri hay khong - END */
                /*20170301-thuongdt sua cap nhat ngay nghi theo tung ngay end */
                
                
                NgayNghiVO ngaynghivo = new NgayNghiVO();
                if (arrCheck != null) {
                    for (int i = 0; i < arrCheck.length; i++) {
                        ngaynghivo.setNgay(new Long(arrCheck[i]));
                        ngaynghivo.setCreated_by(nUserID);
                        ngaynghivo.setCreated_date(StringUtil.getCurrentDate());
                        ngaynghivo.setMo_ta("");
                        String strReturn =
                            ngaynghiDAO.inertNgayNghi(ngaynghivo);
                        if (strReturn != null &&
                            !"-1".equalsIgnoreCase(strReturn.trim())) {
                        } else {
                            conn.rollback();
                            throw new Exception("C&#243; l&#7895;i khi thay &#273;&#7893;i d&#7919; li&#7879;u ng&#224;y l&#224;m vi&#7879;c");
                        }

                    }
                }
                conn.commit();
                Collection colNgayNghi = null;
                colNgayNghi =
                        ngaynghiDAO.getDSNgayTrongThang(ngaynghiForm.getNgaytk(),
                                                        ngaynghiForm.getThangtk(),
                                                        ngaynghiForm.getNamtk());
                request.setAttribute("colNgayNghi", colNgayNghi);
                //                }
                //                resetToken(request);
                //                saveToken(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            conn.close();
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }


    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        //      if(!checkPermissionOnFunction(request, "LTT.DI")){
        //          forward = AppConstants.FAILURE;
        //          return mapping.findForward(forward);
        //      }
        return mapping.findForward(AppConstants.SUCCESS);
    }
}
