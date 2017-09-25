package com.seatech.ttsp.tracuuGiamSatCSDL.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.tracuuGiamSatCSDL.TraCuuCSDLDAO;
import com.seatech.ttsp.tracuuGiamSatCSDL.TraCuuCSDLVO;
import com.seatech.ttsp.tracuuGiamSatCSDL.form.TraCuuCSDLForm;

import java.sql.Connection;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TraCuuCSDLAction extends AppAction {
//    private static String SUCCESS = "success";
//    private static String FAILURE = "failure";
//    private String forward = AppConstants.SUCCESS;
//    private static String STRING_EMPTY = "";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        if (!checkPermissionOnFunction(request, "SYS.TRACUU.CSDL")) {
            return mapping.findForward("errorQuyen");
        }
        
        Connection conn = null;
        Vector v_param = null;
        List listCSDL = null;

        //khai bao bien find.
        String strloai_tacdong = "";
        String strten_bang = "";
        String struser_dangnhap_csdl = "";
        String struser_client = "";
        String strhost_client = "";
        String strma_nsd= "";
        //        String strma_nsd = "";
        try {
            
            conn = getConnection(request);
//            save log
            HttpSession session = request.getSession();
            saveVisitLog(conn, session,
                       "SYS.TRACUU.CSDL",
                        "");
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            TraCuuCSDLForm f = (TraCuuCSDLForm)form;
            TraCuuCSDLDAO csdl_dao = new TraCuuCSDLDAO(conn);
//            TraCuuCSDLVO csdl_vo = new TraCuuCSDLVO();
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            String str_csdl = "";
            
          String tu_ngay =
              f.getTu_ngay() == null ? "" : f.getTu_ngay();
          String den_ngay =
              f.getDen_ngay() == null ? "" : f.getDen_ngay();
            
            v_param = new Vector();
            if (!"".equals(f.getLoai_tac_dong()) &&
                null != f.getLoai_tac_dong()) {
                strloai_tacdong = f.getLoai_tac_dong().toUpperCase().trim();
                str_csdl += " and a.statement_type = '" + strloai_tacdong+"'";
            }
            if (!"".equals(f.getObject_name()) && null != f.getObject_name()) {
                strten_bang = f.getObject_name().toUpperCase().trim();
                str_csdl += " and lower(a.object_name) like lower ('%" + strten_bang + "%')";
                
            }
            if (!"".equals(f.getDb_user()) && null != f.getDb_user()) {
                struser_dangnhap_csdl = f.getDb_user().toUpperCase().trim();
                str_csdl += " and lower(a.db_user) like lower ('%"+ struser_dangnhap_csdl +"%')";
            }
            if (!"".equals(f.getOs_user()) && null != f.getOs_user()) {
                struser_client = f.getOs_user().toUpperCase().trim();
                str_csdl += " and lower(a.os_user) like lower ('%" + struser_client + "%')";
            }
            if (!"".equals(f.getUserhost()) && null != f.getUserhost()) {
                strhost_client = f.getUserhost().toUpperCase().trim();
                str_csdl += " and lower(a.userhost) like lower('%"+strhost_client+"%')";
            }
            if (!"".equals(f.getClient_id()) &&
                null != f.getClient_id()) {
                strma_nsd = f.getClient_id().toUpperCase().trim();
              str_csdl += " and lower(b.ma_nsd) like lower('%"+strma_nsd+"%')";
             
            }
          v_param = new Vector();
          if (tu_ngay != null && !"".equals(tu_ngay) && den_ngay != null &&
              !"".equals(den_ngay)) {        
              str_csdl += " and  trunc(a.timestamp) >=   ? and trunc (a.timestamp) <= ?  ";
              
            v_param.add(new DateParameter(StringUtil.StringToDate(tu_ngay,
                                                                  "dd/MM/yyyy"),
                                          true));
            v_param.add(new DateParameter(StringUtil.StringToDate(den_ngay,
                                                                  "dd/MM/yyyy"),
                                          true));
          }
          if ((den_ngay == null || "".equals(den_ngay)) && tu_ngay != null &&
              !"".equals(tu_ngay)) {
              str_csdl +=
                      " and (TRUNC (a.timestamp) <=  trunc(sysdate) and TRUNC (a.timestamp) >= ?)) ";
            v_param.add(new DateParameter(StringUtil.StringToDate(tu_ngay,
                                                                  "dd/MM/yyyy"),
                                          true));
//            v_param.add(new DateParameter(StringUtil.StringToDate(den_ngay,
//                                                                  "dd/MM/yyyy"),
//                                          true));
          } else if (den_ngay != null && !"".equals(den_ngay) &&
                     (tu_ngay == null || "".equals(tu_ngay))) {
              str_csdl += " and TRUNC (a.timestamp) <= ?";
//            v_param.add(new DateParameter(StringUtil.StringToDate(tu_ngay,
//                                                                  "dd/MM/yyyy"),
//                                          true));
            v_param.add(new DateParameter(StringUtil.StringToDate(den_ngay,
                                                                  "dd/MM/yyyy"),
                                          true));
          }else if (den_ngay == null || "".equals(den_ngay) &&
                     (tu_ngay == null || "".equals(tu_ngay))) {
              str_csdl += " and TRUNC (a.timestamp) = trunc(sysdate)";
          }
            
            listCSDL =
                    (List)csdl_dao.getGiamSatCSDL_ptrang(str_csdl, v_param, currentPage,
                                                         numberRowOnPage,
                                                         totalCount);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstCSDL", listCSDL);


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

        if (!checkPermissionOnFunction(request, "SYS.TRACUU.CSDL")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        Vector v_param = null;
//        List listCSDL = null;
        try {
            conn = getConnection(request);
            TraCuuCSDLForm f = (TraCuuCSDLForm)form;
            f.setSession_id(request.getParameter("sessionId").trim());
            TraCuuCSDLDAO csdl_dao = new TraCuuCSDLDAO(conn);
            TraCuuCSDLVO csdl_vo = new TraCuuCSDLVO();
            String str_CSDL = " a.session_id = ?";
            v_param = new Vector();
            v_param.add(new Parameter(f.getSession_id(), true));
            csdl_vo = csdl_dao.getCSDL(str_CSDL, v_param);
            BeanUtils.copyProperties(f, csdl_vo);
            return mapping.findForward("success");
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }

    }


}
