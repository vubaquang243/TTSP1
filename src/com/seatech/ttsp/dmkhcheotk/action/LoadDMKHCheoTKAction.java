package com.seatech.ttsp.dmkhcheotk.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmkhcheotk.DMKHCheoTKDAO;
import com.seatech.ttsp.dmkhcheotk.DMKHCheoTKVO;
import com.seatech.ttsp.dmtkkt.DMTKKTDAO;
import com.seatech.ttsp.dmtkkt.DMTKKTVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LoadDMKHCheoTKAction extends AppAction {
    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection();
            String strWhereTKTN = "t.ma = ? ";
            String strTKTuNhien = request.getParameter("tktn");
            Vector vParam = new Vector();
            vParam.add(new Parameter(strTKTuNhien, true));
            DMTKKTDAO dmtkDAO = new DMTKKTDAO(conn);
            DMTKKTVO dmtkVO = dmtkDAO.getDMTKKT(strWhereTKTN, vParam);

            String strWhere = "a.tk = ? ";
            vParam = new Vector();
            vParam.add(new Parameter(strTKTuNhien, true));
            DMKHCheoTKDAO dmDAO = new DMKHCheoTKDAO(conn);
            DMKHCheoTKVO dmVO = dmDAO.getDMKHCheoTK(strWhere, vParam);
            if (dmVO != null) {
                Type listType = new TypeToken<DMKHCheoTKVO>() {
                }.getType();
                String strJson = new Gson().toJson(dmVO, listType);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
            } else {
                Type listType = new TypeToken<DMTKKTVO>() {
                }.getType();
                String strJson = new Gson().toJson(dmtkVO, listType);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
            out.flush();
            out.close();
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
