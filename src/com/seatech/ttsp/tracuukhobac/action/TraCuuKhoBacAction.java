package com.seatech.ttsp.tracuukhobac.action;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.strustx.AppAction;

import com.seatech.ttsp.tracuukhobac.TraCuuKhoBacDAO;

import com.seatech.ttsp.tracuukhobac.form.TraCuuKhoBacForm;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.Collection;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TraCuuKhoBacAction extends AppAction{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
      Connection conn = null;
      Vector vParams = null;
      Collection lstKhoBac = null;
      JsonObject jsonObj = new JsonObject();
      String strJson = "";
      Gson gson = null;
      try{
          conn = getConnection();
          TraCuuKhoBacDAO dao = new TraCuuKhoBacDAO(conn);
          String strWhere = "";
          String ma_kb_tinh =request.getParameter("id_kb_tinh");
          String ma_kb = request.getParameter("ma_kb");
          String ten_kb = request.getParameter("ten_kb");
          if(ma_kb_tinh == null || ma_kb_tinh.equals("")){
              strWhere += "AND cap = '5' ";
              if(ma_kb != null && !ma_kb.equals("")){
                  strWhere +="AND ma like '%" + ma_kb +"%'";
              }
              if(ten_kb != null && !ten_kb.equals("")){
                  strWhere +="AND ten like '" +ten_kb+ "'";
              }
          }else{
            strWhere = "AND cap = '3' AND ma_cha ='"+ ma_kb_tinh +"' ";
            if(ma_kb != null && !ma_kb.equals("")){
                strWhere +="AND ma like '%" + ma_kb +"%'";
            }
            if(ten_kb != null && !ten_kb.equals("")){
                strWhere +="AND ten like '" +ten_kb+ "'";
            }
          }
        lstKhoBac = dao.getListKhoBac(strWhere, null);
        gson = new GsonBuilder().setVersion(1.0).create();
        strJson = gson.toJson(lstKhoBac);
        jsonObj.addProperty("lstKhoBac", strJson);
        JsonArray jsonArr = new JsonArray();
        JsonElement jsonEle = jsonObj.get("lstKhoBac");
        jsonArr.add(jsonEle);
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        out.println(jsonArr.getAsJsonArray().toString());
        out.flush();
        out.close();
      }catch(Exception e){
            e.printStackTrace();
      }finally{
          close(conn);
      }
      return mapping.findForward("success");
      }
}
