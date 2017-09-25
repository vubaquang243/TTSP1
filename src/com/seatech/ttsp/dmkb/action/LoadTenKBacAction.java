package com.seatech.ttsp.dmkb.action;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LoadTenKBacAction extends AppAction {
    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {
            String strMaKB;
            String strJSON;
            String strWhereClause;
            Vector vParam;

            conn = getConnection();
            strJSON = request.getParameter("strJSON");
            JSONObject jsonReq = JSONObject.fromObject(strJSON);
            strMaKB = jsonReq.get("ma").toString();

            DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
            strWhereClause = " AND ma = ? ";
            vParam = new Vector();
            vParam.add(new Parameter(strMaKB, true));
            DMKBacVO dmkbVO = dmkbDAO.getDMKB(strWhereClause, vParam);
            JSONObject jsonRes = new JSONObject();
           JsonObject jsonObj=new JsonObject();
            if (dmkbVO != null && dmkbVO.getTen() != null) {
                jsonRes.put("ten", FontUtil.unicodeEscape(dmkbVO.getTen()));
            } else {
                jsonRes.put("ten", "0");
            }     
          if (dmkbVO != null && dmkbVO.getId() != null) {
              jsonRes.put("id",dmkbVO.getId().toString());
          } else {
              jsonRes.put("id", "0");
          }  
            
           response.setHeader("X-JSON", jsonRes.toString());
            Type listType = new TypeToken<DMKBacVO>() {}.getType();
            String strJson = new Gson().toJson(dmkbVO, listType);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out =response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            JSONObject jsonRes = new JSONObject();
            jsonRes.put("executeError",FontUtil.unicodeEscape("Lỗi: "+e.getMessage()));

            response.setHeader("X-JSON", jsonRes.toString());
        }finally{
          close(conn);
        }

        return mapping.findForward("success");
    }    
  public ActionForward list(ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
      Connection conn = null;

      try {
          String strMaKB;
          String strJSON;
          String strWhereClause;
          Vector vParam;

          conn = getConnection();
          strJSON = request.getParameter("strJSON");
          JSONObject jsonReq = JSONObject.fromObject(strJSON);
          strMaKB = jsonReq.get("ma_kb").toString();

          DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
          strWhereClause = " AND b.ma_nh = ? ";
          vParam = new Vector();
          vParam.add(new Parameter(strMaKB, true));
          DMKBacVO dmkbVO = dmkbDAO.getDMKB(strWhereClause, vParam);
          JSONObject jsonRes = new JSONObject();
         JsonObject jsonObj=new JsonObject();
          if (dmkbVO != null && dmkbVO.getTen() != null) {
              jsonRes.put("ten", FontUtil.unicodeEscape(dmkbVO.getTen()));
          } else {
              jsonRes.put("ten", "0");
          }     
        if (dmkbVO != null && dmkbVO.getId() != null) {
            jsonRes.put("id",dmkbVO.getId().toString());
        } else {
            jsonRes.put("id", "0");
        }  
          
         response.setHeader("X-JSON", jsonRes.toString());
              Type listType = new TypeToken<DMKBacVO>() {}.getType();
              String strJson = new Gson().toJson(dmkbVO, listType);
              response.setContentType(AppConstants.CONTENT_TYPE_JSON);
              PrintWriter out =response.getWriter();
              out.println(strJson.toString());
              out.flush();
              out.close();
      } catch (Exception e) {
          JSONObject jsonRes = new JSONObject();
          jsonRes.put("executeError",FontUtil.unicodeEscape("Lỗi: "+e.getMessage()));

          response.setHeader("X-JSON", jsonRes.toString());
      }finally{
        close(conn);
      }

      return mapping.findForward("success");
  }    
}
