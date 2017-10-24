package com.seatech.ttsp.thamso.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;
import com.seatech.ttsp.thamso.form.ThamSoHThongForm;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
    /* @HungBM: edit
     * @Date: 16/10/2016
     * @see: sua ham view
     * @track: 20161209
     * */
	
	/* @HungBM: edit
     * @Date: 12/12/2016
     * @see: Sua print
     * @track: 20161212
     * */
public class ThamSoHThongAction extends AppAction {
    private static String SUCCESS = "success";
    //    private static String FAILURE = "failure";
    //    private String forward = AppConstants.SUCCESS;
    //    private static String STRING_EMPTY = "";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "SYS.TS_HT.QLY_TS")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        //        Collection coll = null;
        ThamSoHThongForm f = null;
        try {
            conn = getConnection(request);
            f = (ThamSoHThongForm)form;
            HttpSession session = request.getSession();
            saveVisitLog(conn, session, "SYS.TS_HT.QLY_TS", "");
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            String strid = null;
            String strmota = null;
            String strma = null;
            if (f != null) {
                strid = f.getId();
                strmota = f.getMo_ta();
                strma = f.getTen_ts();
            }
            // khai bao bien phan trang.
            //          String page = f.getPageNumber();
            String strRoleList = "";
            if (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) !=
                null) {
                strRoleList =
                        session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
            }
            String strWhere = " and cho_phep_sua = 'Y' ";
            String[] rollList = strRoleList.split("\\|");
            if (rollList.length > 0) {
                strWhere += " and ( ";
                for (int i = 0; i < rollList.length; i++) {
                    if (i == 0) {
                        strWhere += "dvi_sua like '%" + rollList[i] + "%' ";
                    } else {
                        strWhere += "or dvi_sua like '%" + rollList[i] + "%' ";
                    }
                }
                strWhere += ") ";
            } else {
                strWhere += " and dvi_sua = '" + strRoleList + "' ";
            }

            Vector v_param = null;
            if (strid != null && !strid.equals("")) {
                strWhere = "and a.id=? ";
                v_param = new Vector();
                v_param.add(new Parameter(strid, true));
            }
            if (strmota != null && !strmota.equals("")) {

                strWhere =
                        "and lower(a.mo_ta) like lower('%" + strmota.trim() +
                        "%') ";
            }
            if (strma != null && !strma.equals("")) {
                strWhere +=
                        " and lower(a.ten_ts) like lower('%" + strma.trim() +
                        "%') ";
            }
            ThamSoHThongDAO tshtdao = new ThamSoHThongDAO(conn);
            List lstTS = null;
            lstTS = (List)tshtdao.getThamSoList(strWhere, v_param);
            if (lstTS.isEmpty()) {
                request.setAttribute("status",
                                     "QuanLyTSHT.listQLTSHT.failure.null.ketqua");
            }
            request.setAttribute("listTS", lstTS);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public  String fileName = "/DSachTSoHT";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "SYS.TS_HT.QLY_TS")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        //        Collection coll = null;
        ThamSoHThongForm f = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection(request);
            f = (ThamSoHThongForm)form;
            HttpSession session = request.getSession();
            saveVisitLog(conn, session, "SYS.TS_HT.QLY_TS", "");
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            String strid = null;
            String strmota = null;
            String strma = null;
            if (f != null) {
                strid = f.getId();
                strmota = f.getMo_ta();
                strma = f.getTen_ts();
                if (strid != null) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                                     strid + "\" id=\"id\"></input>");
                }
                if (strmota != null) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"mo_ta\" value=\"" +
                                     strmota + "\" id=\"mo_ta\"></input>");
                }
                if (strma != null) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"ten_ts\" value=\"" +
                                     strma + "\" id=\"ten_ts\"></input>");
                }
            }

            String strWhere = null;
            Vector v_param = null;
            if (strid != null && !strid.equals("")) {
                strWhere = "and a.id=? ";
                v_param = new Vector();
                v_param.add(new Parameter(strid, true));
            }
            if (strmota != null && !strmota.equals("")) {

                strWhere =
                        "and lower(a.mo_ta) like lower('%" + strmota.trim() +
                        "%') ";
            } else {
                strWhere = "";
            }
            if (strma != null && !strma.equals("")) {
                strWhere +=
                        " and lower(a.ten_ts) like lower('%" + strma.trim() +
                        "%') ";
            }
            ThamSoHThongDAO tshtdao = new ThamSoHThongDAO(conn);
            ResultSet lstTS = null;
            lstTS = tshtdao.getThamSoResultSet(strWhere, v_param);
            if (lstTS == null) {
                request.setAttribute("status",
                                     "QuanLyTSHT.listQLTSHT.failure.null.ketqua");
            } else {    
			//20161212-HungBM
			/*
			 * Them dieu kien tra cuu theo ngay va tinh huyen
			 */    
			 /**************************START************************/     
              String tu_ngay = null;
              String den_ngay = null;
              String nhkb_tinh = null;
              String nhkb_huyen  = null;
              if (f != null) {
                  strmota = f.getMo_ta();
                  strma = f.getTen_ts();
                  tu_ngay = f.getTu_ngay();
                  den_ngay = f.getDen_ngay();
                  nhkb_tinh = f.getNhkb_tinh();
                  nhkb_huyen = f.getNhkb_huyen();
              }
              String strBCWhere = null;
              
              if (strid != null && !strid.equals("")) {
                  strBCWhere = "and a.id=? ";
                  v_param = new Vector();
                  v_param.add(new Parameter(strid, true));
              }
              if (strmota != null && !strmota.equals("")) {

                  strBCWhere =
                          "and lower(a.mo_ta) like lower('%" + strmota + "%') ";
              }
              if ((den_ngay == null || "".equals(den_ngay)) &&
                  (tu_ngay != null && !"".equals(tu_ngay))) {
                  strBCWhere =
                          " and ((a.ngay_cap_nhat <=  to_date(sysdate) and a.ngay_cap_nhat >=  to_date('" +
                          tu_ngay + "','DD-MM-YYYY'))) ";
              } else if (den_ngay != null && !"".equals(den_ngay) &&
                         (tu_ngay == null || "".equals(tu_ngay))) {
                  strBCWhere =
                          " and (a.ngay_cap_nhat <=  to_date('" + den_ngay + "','DD-MM-YYYY')) ";
              } else if (tu_ngay != null && !"".equals(tu_ngay) &&
                         den_ngay != null && !"".equals(den_ngay)) {
                  strBCWhere =
                          " and ((a.ngay_cap_nhat >=  to_date('" + tu_ngay + "','DD-MM-YYYY') and a.ngay_cap_nhat <=  to_date('" +
                          den_ngay + "','DD-MM-YYYY'))) ";
              } else {
                  strBCWhere = "";
              }
              if (strma != null && !strma.equals("")) {
                  strBCWhere +=
                          " and lower(a.ten_ts) like lower('%" + strma.trim() +
                          "%') ";
              }
              if (nhkb_tinh != null && !nhkb_tinh.equals("")) {
                strBCWhere +=
                        " and c.id_cha = '"+nhkb_tinh+"' ";
              }
              if (nhkb_huyen != null && !nhkb_huyen.equals("")) {
                strBCWhere +=
                        " and c.id = '"+nhkb_huyen+"' ";
              }
                
                
                JasperPrint jasperPrint = null;
              HashMap parameterMap = new HashMap();
              parameterMap.put("p_tungay", f.getTu_ngay());
              parameterMap.put("p_denngay", f.getDen_ngay());
              parameterMap.put("p_whereclause", strBCWhere);
			  /**************************END************************/
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
                JRDataSource jrDS = new JRResultSetDataSource(lstTS);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     jrDS);
                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "ThamSoHTPrintAction.do";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    AppConstants.REPORT_DIRECTORY +
                    AppConstants.FONT_FOR_REPORT;
                ReportUtility rpUtilites = new ReportUtility();
                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
            }

            request.setAttribute("listTS", new ArrayList());
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
        return mapping.findForward(SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "SYS.TS_HT.QLY_TS.SUA")) {
            return mapping.findForward("errorQuyen");
        }
        ThamSoHThongForm f = (ThamSoHThongForm)form;
        f.setGiatri_ts_moi(request.getParameter("strgiatri").trim());
        f.setGiatri_ts(request.getParameter("strgiatri").trim());
        f.setCho_phep_sua(request.getParameter("strchophepsua").trim());
        f.setId(request.getParameter("longid").trim());
        f.setTen_ts(request.getParameter("strtents").trim());
        f.setMo_ta(request.getParameter("strmota").trim());

        return mapping.findForward("success");
    }
    //action deer thucj hien update

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "SYS.TS_HT.QLY_TS.SUA")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        try {
            HttpSession session = request.getSession();

            //        String mathamso = null;
            Long nUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            Long lKB =
                new Long(session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString());

            conn = getConnection(request);

            saveVisitLog(conn, session, "SYS.TS_HT.QLY_TS.SUA",
                         "Cap nhat tham so he thong");
            ThamSoHThongForm f = (ThamSoHThongForm)form;
            ThamSoHThongVO vo = new ThamSoHThongVO();
            f.setId(f.getId().trim());
            //vo.setTen(f.getTen());
            // vo.setId(Long.valueOf(f.getId().trim()));
            ThamSoHThongDAO tsDAO = new ThamSoHThongDAO(conn);
            BeanUtils.copyProperties(vo, f);
            vo.setKb_id(lKB);
            //          vo.setGia_tri_ts_moi(f.getGiatri_ts_moi());
            vo.setNsd_id(nUserID);
            request.setAttribute("nsdID", f.getTen_ts());
            if (f.getCho_phep_sua().equalsIgnoreCase("Y") ||
                f.getCho_phep_sua().equalsIgnoreCase("y")) {
                int i = tsDAO.update(vo);
                if (i > 0) {
                    Long j = tsDAO.insert(vo);
                    if (j.longValue() == 0) {
                        request.setAttribute("status",
                                             "QuanLyTSHT.listQLTSHT.failure.sua");

                    }

                    request.setAttribute("status",
                                         "QuanLyTSHT.listQLTSHT.success.sua");
                    f.setMo_ta("");
                    f.setTen_ts("");

                } else {
                    request.setAttribute("status",
                                         "QuanLyTSHT.listQLTSHT.failure.sua");
                }
            } else {
                request.setAttribute("status",
                                     "QuanLyTSHT.listQLTSHT.failure.sua.kchophep");
            }
            conn.commit();
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        ThamSoHThongForm fff = new ThamSoHThongForm();
        return list(mapping, fff, request, response);
        //       return mapping.findForward("success");

    }


/* HungBM: edit view
 * 16/11/2016
 * Them code cung cap dieu kien tra cuu
 * theo danh muc kho bac
 * 20161209
 * */
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "SYS.TS_HT.LS_TS")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        //      Collection coll = null;


        try {
            conn = getConnection(request);
            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            ThamSoHThongForm f = (ThamSoHThongForm)form;
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            String page = f.getPageNumber();
            if (page == null)
                page = "1"; 
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            String strid = null;
            String strmota = null;
            String strma = null;
            String tu_ngay = null;
            String den_ngay = null;
            String nhkb_tinh = null;
            String nhkb_huyen  = null;
            //20161209-HungBM
            //Them code cung cap dieu kien tra cuu
            /*--------------START------------------*/
            String idxKB = f.getIdxKB() == null ? "" : f.getIdxKB();
            
            //Goi session va DAO + VO
            HttpSession session = request.getSession();
            DChieu1DAO dao = new DChieu1DAO(conn);
            DChieu1VO vo = new DChieu1VO();
            //Lay code cua kho bac
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            //Lay cap
            String strCap = " and ma=" + kb_code;
            vo = dao.getCap(strCap, null);
            List dmuckb_cha = null;
            String cap = vo.getCap();
            
          if ("0001".equals(kb_code) || "0002".equals(kb_code)) {
              String strWhere = " ";
              dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
              request.setAttribute("dmuckb_tinh", dmuckb_cha);
              request.setAttribute("dftinh", "dftinh");
              request.setAttribute("TTTT", "TTTT");
          } else if ("0003".equals(kb_code)) {
              String strWhere = " AND a.ma='0003' ";
              dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
              request.setAttribute("dmuckb_tinh", dmuckb_cha);
          } else if ("5".equals(cap)) {
              String strWhere = "";
              strWhere += " and c.ma=" + kb_code;
              dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
              request.setAttribute("dmuckb_tinh", dmuckb_cha);
          } else {
              String strWhere = "";
              strWhere +=
                      " and c.id in (select id_cha from sp_dm_htkb where ma=" +
                      kb_code + ")";
              dmuckb_cha = (List)dao.getDMucKB_Tinh(strWhere, null);
              request.setAttribute("dmuckb_tinh", dmuckb_cha);
          }    
            /*--------------END--------------------*/
            if (f != null) {
                strmota = f.getMo_ta();
                strma = f.getTen_ts();
                tu_ngay = f.getTu_ngay();
                den_ngay = f.getDen_ngay();
                nhkb_tinh = f.getNhkb_tinh();
                nhkb_huyen = f.getNhkb_huyen();
            }
            String strWhere = null;
            Vector v_param = null;
            if (strid != null && !strid.equals("")) {
                strWhere = "and a.id=? ";
                v_param = new Vector();
                v_param.add(new Parameter(strid, true));
            }
            if (strmota != null && !strmota.equals("")) {

                strWhere =
                        "and lower(a.mo_ta) like lower('%" + strmota + "%') ";
            }
            if ((den_ngay == null || "".equals(den_ngay)) &&
                (tu_ngay != null && !"".equals(tu_ngay))) {
                strWhere =
                        " and ((trunc(a.ngay_cap_nhat) <=  to_date(sysdate) and trunc(a.ngay_cap_nhat) >=  to_date('" +
                        tu_ngay + "','DD-MM-YYYY'))) ";
            } else if (den_ngay != null && !"".equals(den_ngay) &&
                       (tu_ngay == null || "".equals(tu_ngay))) {
                strWhere =
                        " and (trunc(a.ngay_cap_nhat) <=  to_date('" + den_ngay + "','DD-MM-YYYY')) ";
            } else if (tu_ngay != null && !"".equals(tu_ngay) &&
                       den_ngay != null && !"".equals(den_ngay)) {
                strWhere =
                        " and ((trunc(a.ngay_cap_nhat) >=  to_date('" + tu_ngay + "','DD-MM-YYYY') and trunc(a.ngay_cap_nhat) <=  to_date('" +
                        den_ngay + "','DD-MM-YYYY'))) ";
            } else {
                strWhere = "";
            }
            if (strma != null && !strma.equals("")) {
                strWhere +=
                        " and lower(a.ten_ts) like lower('%" + strma.trim() +
                        "%') ";
            }
          if (nhkb_tinh != null && !nhkb_tinh.equals("")) {
              strWhere +=
                      " and c.id_cha = '"+nhkb_tinh+"' ";
          }
          if (nhkb_huyen != null && !nhkb_huyen.equals("")) {
              strWhere +=
                      " and c.id = '"+nhkb_huyen+"' ";
          }

            ThamSoHThongDAO tshtdao = new ThamSoHThongDAO(conn);
            List lstTS = null;
            lstTS =
                    (List)tshtdao.getThamSoLSuList(strWhere, v_param, currentPage,
                                                   numberRowOnPage,
                                                   totalCount);
            request.setAttribute("listTS", lstTS);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("idxKB", idxKB);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }


  public static final String REPORT_DIRECTORY = "/report";
  public static final String strFontTimeRoman = "/font/times.ttf";
 // public static final String fileName = "/LSuTSoHT";
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
      fileName = "/LSuTSoHT";
        if (!checkPermissionOnFunction(request, "SYS.TS_HT.LS_TS")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        //      Collection coll = null;

        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection(request);

            ThamSoHThongForm f = (ThamSoHThongForm)form;
          String strid = null;
          String strmota = null;
          String strma = null;
          String tu_ngay = null;
          String den_ngay = null;
          String nhkb_tinh = null;
          String nhkb_huyen  = null;
          String strTuNgay = ".....";
          String strDenNgay = ".....";
          if (f != null) {
              strmota = f.getMo_ta();
              strma = f.getTen_ts();
              tu_ngay = f.getTu_ngay();
              den_ngay = f.getDen_ngay();
              nhkb_tinh = f.getNhkb_tinh();
              nhkb_huyen = f.getNhkb_huyen();
          }
          String strWhere = null;
          Vector v_param = null;
          if (strid != null && !strid.equals("")) {
              strWhere = "and a.id=? ";
              v_param = new Vector();
              v_param.add(new Parameter(strid, true));
          }
          if (strmota != null && !strmota.equals("")) {

              strWhere =
                      "and lower(a.mo_ta) like lower('%" + strmota + "%') ";
          }
		  //HungBM-Sua code de them tim kiem theo ngay thang-begin
          if ((den_ngay == null || "".equals(den_ngay)) &&
              (tu_ngay != null && !"".equals(tu_ngay))) {
              strWhere =
                      " and ((trunc(a.ngay_cap_nhat) <=  to_date(sysdate) and trunc(a.ngay_cap_nhat) >=  to_date('" +
                      tu_ngay + "','DD-MM-YYYY'))) ";  
          } else if (den_ngay != null && !"".equals(den_ngay) &&
                     (tu_ngay == null || "".equals(tu_ngay))) {
              strWhere =
                      " and (trunc(a.ngay_cap_nhat) <=  to_date('" + den_ngay + "','DD-MM-YYYY')) ";
          } else if (tu_ngay != null && !"".equals(tu_ngay) &&
                     den_ngay != null && !"".equals(den_ngay)) {
              strWhere =
                      " and ((trunc(a.ngay_cap_nhat)>=  to_date('" + tu_ngay + "','DD-MM-YYYY') and trunc(a.ngay_cap_nhat) <=  to_date('" +
                      den_ngay + "','DD-MM-YYYY'))) ";
          } else {
              strWhere = "";
          }
          if (strma != null && !strma.equals("")) {
              strWhere +=
                      " and lower(a.ten_ts) like lower('%" + strma.trim() +
                      "%') ";
          }
		  //Them dieu kien tim kiem theo tinh huyenh
          if (nhkb_tinh != null && !nhkb_tinh.equals("")) {
            strWhere +=
                    " and c.id_cha = '"+nhkb_tinh+"' ";
          }
          if (nhkb_huyen != null && !nhkb_huyen.equals("")) {
            strWhere +=
                    " and c.id = '"+nhkb_huyen+"' ";
          }

            //prints
          JasperPrint jasperPrint = null;
          HashMap parameterMap = new HashMap();
          ReportUtility rpUtilites = new ReportUtility();
          // set parameter bao cao
          if(tu_ngay !=null &&  !tu_ngay.equals("")) 
              strTuNgay =  tu_ngay;
          if(den_ngay !=null &&  !den_ngay.equals(""))
              strDenNgay =  den_ngay;
          parameterMap.put("p_tu_ngay", strTuNgay);
          parameterMap.put("p_den_ngay", strDenNgay);
          parameterMap.put("p_whereclause", strWhere);
            
          reportStream =
                  getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                          fileName +
                                                                                          ".jasper");  
          jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
          String strTypePrintAction =
              request.getParameter(AppConstants.REQUEST_ACTION) == null ?
              "" :
              request.getParameter(AppConstants.REQUEST_ACTION).toString();
           //set action cho moi lan forward
          String strActionName = "ThamSoHTLSuPrintAction.do";
          String strParameter = "";
          String strPathFont =
              getServlet().getServletContext().getContextPath() +
              REPORT_DIRECTORY + strFontTimeRoman;
		  //HungBM-Sua code de them tim kiem theo ngay thang-end
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
        return mapping.findForward(SUCCESS);
    }
}
