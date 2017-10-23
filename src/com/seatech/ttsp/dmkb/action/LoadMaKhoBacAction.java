package com.seatech.ttsp.dmkb.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.AppKeys;
import com.seatech.framework.strustx.AppAction;

import com.seatech.framework.utils.FontUtil;

import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmkb.DMKBacDAO;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class LoadMaKhoBacAction extends AppAction {
    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        Vector params = null;
        try {

            conn = getConnection();
            String shkb = request.getParameter("sh_kb");
            DMKBacDAO dKBacDAO = new DMKBacDAO(conn);
            String ma_nhkb = dKBacDAO.getMaKB8So(shkb);

            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(ma_nhkb);
            jsonObj.addProperty("result", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("result");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }

        return mapping.findForward(AppConstants.SUCCESS);
    }


}
