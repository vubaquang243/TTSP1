package com.seatech.ttsp.DongBoHoa.Action;


import com.google.gson.JsonObject;

import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.threadmanager.wsclient.ManagerThreadServiceClient;
import com.seatech.ttsp.DongBoHoa.DongBoHoaDAO;
import com.seatech.ttsp.DongBoHoa.DongBoHoaVO;
import com.seatech.ttsp.DongBoHoa.Form.DongBoHoaForm;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
   * @modify: HungBM
   * @modify date: 11/2016   
   * @see: sua them thong tin hien thi tren form
   * */
public class DongBoHoaAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";
    //    private String forward = AppConstants.SUCCESS;
    //    private static String STRING_EMPTY = "";


    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        Vector param_dongbo = null;
        try {
            conn = getConnection(request);
            DongBoHoaDAO dongbo_dao = new DongBoHoaDAO(conn);
            Collection listDongBo = new ArrayList();
            String strDongBo = null;
            param_dongbo = new Vector();
            listDongBo = dongbo_dao.getListDongBo(strDongBo, param_dongbo);
            request.setAttribute("listDongBoNSD", listDongBo);

        } catch (Exception e) {
            return mapping.findForward(FAILURE);

        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        Vector param_dongbo = null;
        try {
            conn = getConnection(request);
            DongBoHoaForm f = (DongBoHoaForm)form;
            f.setId(request.getParameter("longid").trim());
            DongBoHoaDAO dongbo_dao = new DongBoHoaDAO(conn);
            DongBoHoaVO vo = new DongBoHoaVO();
            //            Collection listDongBo = new ArrayList();
            String strDongBo = "a.id = ?";
            param_dongbo = new Vector();
            param_dongbo.add(new Parameter(f.getId(), true));
            vo = dongbo_dao.getThread(strDongBo, param_dongbo);
            BeanUtils.copyProperties(f, vo);
        } catch (Exception e) {
            return mapping.findForward(FAILURE);
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            Long threadID = null;
            conn = getConnection(request);
            DongBoHoaForm f = (DongBoHoaForm)form;
            DongBoHoaDAO dongbo_dao = new DongBoHoaDAO(conn);
            DongBoHoaVO vo = new DongBoHoaVO();
            BeanUtils.copyProperties(vo, f);
            int i = dongbo_dao.update(vo);
            if (i > 0) {
                request.setAttribute("status",
                                     "quanlynsd.listnsd.success.sua");
                threadID = vo.getId();
                HttpSession session = request.getSession();
                //                String userID =
                //                    session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
                saveVisitLog(conn, session, "QLY_NSD.QLY.SUA", "");
                f.reset(mapping, request);
                conn.commit();
                list(mapping, f, request, response);
                return mapping.findForward(SUCCESS);
            } else {
                request.setAttribute("status",
                                     "quanlynsd.listnsd.failure.sua");
            }
            request.setAttribute("nsdID", threadID);
        } catch (Exception e) {
            throw new Exception(e);

        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        Vector param_dongbo = null;
        List<String> lst = new ArrayList<String>();
        try {
            conn = getConnection(request);
            DongBoHoaForm f = new DongBoHoaForm();
            String[] checks = request.getParameterValues("check");

            HttpSession session = request.getSession();

            String strWSDL = getThamSoHThong("WSDL_DONGBO_DM", session);
//            String strWSDL = "http://192.168.1.7:7003/datasyn/ManagerThreadSoap?WSDL";
            String strUserName =
                getThamSoHThong("USER_NAME_DONG_BO_DM", session);
            String strPassword =
                getThamSoHThong("PASSWORD_DONG_BO_DM", session);

            ManagerThreadServiceClient mgclient =
                new ManagerThreadServiceClient(strUserName, strPassword, strWSDL);
            for (int i = 0; i < checks.length; i++) {
                lst.add(checks[i]);
            }
            
            String action = request.getParameter("action");
            if (action != null && action.equals("0"))
                mgclient.controlThread(lst, false);
            else
                mgclient.controlThread(lst, true);
            list(mapping, f, request, response);

            // ham start, truyen vao class_name va true= start;false=stop

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);

    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        //        JsonObject json = new JsonObject();
        PrintWriter out = response.getWriter();
        JsonObject jsonObj = new JsonObject();
        try {
		/**
	       * - 22/11/2016
	       * - Set tham so he thong theo bien.
	       * - ThuongDT
		   22/11/2016-BEGIN **/
		
          HttpSession session = request.getSession();
          String strWSDL = getThamSoHThong("WSDL_DONGBO_DM", session);
                     
                     String strUserName =
                         getThamSoHThong("USER_NAME_DONG_BO_DM", session);
                     String strPassword =
                         getThamSoHThong("PASSWORD_DONG_BO_DM", session);
            ManagerThreadServiceClient mgclient =
                new ManagerThreadServiceClient(strUserName, strPassword,
                                               strWSDL);
			/**22/11/2016-END**/
            String logFile = request.getParameter("logFile");
            String strTrue = mgclient.ReadLogThread(logFile);
            jsonObj.addProperty("logdata", strTrue);
        } catch (Exception ex) {
//            ex.printStackTrace();
            jsonObj.addProperty("logdata", ex.getMessage());
        }
        out.println(jsonObj.toString());
        out.flush();
        out.close();

        return null;
    }
}
