package com.seatech.ttsp.duyetdnqt.action;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.duyetdnqt.DuyetDNQTDAO;

import com.seatech.ttsp.duyetdnqt.form.DuyetDNQTForm;
import com.seatech.ttsp.proxy.giaodien.Msg066;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.Collection;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Class dùng cho việc duyệt đè nghị quyết toán
 *
 */
public class DuyetDNQTAction extends AppAction {
    /*
     *
     * Hàm main
     *
     * */

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        Collection listData = null;
        Vector params = null;
        DuyetDNQTForm frmDuyet = (DuyetDNQTForm) form;
        try {
            params = new Vector();
            conn = getConnection();
            DuyetDNQTDAO dao = new DuyetDNQTDAO(conn);
            HttpSession session = request.getSession();
            String strQuery = "and 1=1 ";
            String page = frmDuyet.getPageNumber();
            if(page == null){
                page = "1"; }
            Integer currentPage = new Integer(page);
            Integer row = new Integer(15);
            Integer totalCount[] = new Integer[1];
            listData = dao.getData(strQuery, params, currentPage, row, totalCount);
            request.setAttribute("listData", listData);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setNumberOfRow(row);
            request.setAttribute("PAGE_KEY", pagingBean);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return mapping.findForward("success");
    }

    /*
     * Duyet de nghi quyet toan
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        Vector params = null;
        try {
            conn = getConnection();
            String strID = request.getParameter("soLenh");
            HttpSession session = request.getSession();
            String strManager =
                session.getAttribute(AppConstants.APP_USER_CODE_SESSION) ==
                null ? "" :
                session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
            if (!strManager.equals("")) {
                Msg066 msg066 = new Msg066(conn);
                DuyetDNQTDAO dao = new DuyetDNQTDAO(conn);
                String result = msg066.sendMessage(strID, strManager);
                dao.updateData(strID, params);
                gson = new GsonBuilder().setVersion(1.0).create();
                strJson = gson.toJson(result);
                jsonObj.addProperty("result", strJson);
                JsonArray jsonArr = new JsonArray();
                JsonElement jsonEle = jsonObj.get("result");
                jsonArr.add(jsonEle);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(jsonArr.getAsJsonArray().toString());
                out.flush();
                out.close();
                conn.commit();
            } else {
                String result = "fail";
                gson = new GsonBuilder().setVersion(1.0).create();
                strJson = gson.toJson(result);
                jsonObj.addProperty("result", strJson);
                JsonArray jsonArr = new JsonArray();
                JsonElement jsonEle = jsonObj.get("result");
                jsonArr.add(jsonEle);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(jsonArr.getAsJsonArray().toString());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            String result = "fail";
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(result);
            jsonObj.addProperty("result", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("result");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
            conn.rollback();
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

  /*
   * Huy de nghi quyet toan
   */
    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        Vector params = null;
        String result = "";
        try {
            conn = getConnection();
            DuyetDNQTDAO dao = new DuyetDNQTDAO(conn);
            String strID = request.getParameter("soLenh");
            int a = dao.changeStatusSp066(strID, params);
            if (a == 1) {
                result = "success";
                gson = new GsonBuilder().setVersion(1.0).create();
                strJson = gson.toJson(result);
                jsonObj.addProperty("result", strJson);
                JsonArray jsonArr = new JsonArray();
                JsonElement jsonEle = jsonObj.get("result");
                jsonArr.add(jsonEle);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(jsonArr.getAsJsonArray().toString());
                out.flush();
                out.close();                
                conn.commit();
            } else {
                result = "fail";
                gson = new GsonBuilder().setVersion(1.0).create();
                strJson = gson.toJson(result);
                jsonObj.addProperty("result", strJson);
                JsonArray jsonArr = new JsonArray();
                JsonElement jsonEle = jsonObj.get("result");
                jsonArr.add(jsonEle);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(jsonArr.getAsJsonArray().toString());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            result = "fail";
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(result);
            jsonObj.addProperty("result", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("result");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
            conn.rollback();
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
}
