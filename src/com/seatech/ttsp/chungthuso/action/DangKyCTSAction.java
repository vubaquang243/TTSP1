package com.seatech.ttsp.chungthuso.action;


import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.chungthuso.form.ChungThuSoForm;
import com.seatech.ttsp.proxy.pki.PKIService;

import java.io.PrintWriter;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import vn.gov.vst.CertInfo;


public class DangKyCTSAction extends AppAction{
    public DangKyCTSAction() {
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
  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                           HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
      try{
        if(!checkPermissionOnFunction(request, "QLY_NSD.QLY_CTS.DKY")){
           return mapping.findForward(AppConstants.FAILURE);
        }
      }catch(Exception e){
//        e.printStackTrace();
      }
      return mapping.findForward(AppConstants.SUCCESS);
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
  
  public ActionForward addExc(ActionMapping mapping, ActionForm form,
                           HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
      response.setContentType(AppConstants.CONTENT_TYPE_JSON);
      PrintWriter out =response.getWriter();
      JsonObject jsonObj=new JsonObject();
      Connection conn=null;
      String strAppID = AppConstants.APP_ID; 
      try{
          HttpSession session =request.getSession();
          ChungThuSoForm frmCTS=(ChungThuSoForm)form;
          
          String userName=session.getAttribute(AppConstants.APP_DOMAIN_SESSION)+"\\"+session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
          if(session!=null){
            String strWSDL = getThamSoHThong("WSDL_PKI", session);
            strAppID = getThamSoHThong("APP_ID", session);
            PKIService pkiService=new PKIService(strWSDL); 
                pkiService.uploadCertificate(userName, 
                                       frmCTS.getSerial(),
                                       frmCTS.getNoi_dung(),
                                       frmCTS.getHieu_luc_tu_ngay(),
                                       frmCTS.getHieu_luc_den_ngay(),
                                       strAppID);
             conn=getConnection();
             saveVisitLog(conn,session, "QLY_NSD.QLY_CTS.DKY", "Dang ky chung thu so");
          }
          
        }catch(Exception e){
//          e.printStackTrace();
          jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
      }finally{
        out.println(jsonObj.toString());
        out.flush();
        out.close();
        conn.close();
      }
      if(!response.isCommitted())
        return mapping.findForward(AppConstants.SUCCESS);
      else
        return null;
  }

   
}
