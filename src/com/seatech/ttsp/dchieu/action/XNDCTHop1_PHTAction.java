package com.seatech.ttsp.dchieu.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.DuyetKQDCVO;
import com.seatech.ttsp.dchieu.KQDChieu1VO;
import com.seatech.ttsp.dchieu.form.XNDCTHop1Form;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class XNDCTHop1_PHTAction extends AppAction {
  public ActionForward list(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
      if (!checkPermissionOnFunction(request, "DCHIEU.XNDCTHOP")) {
          return mapping.findForward("notRight");
      }

      Connection conn = null;
      try {
          conn = getConnection(request);
          HttpSession session = request.getSession();



          String strUserInfo =
              (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
          if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
              return mapping.findForward("notRight");
          }

          DChieu1DAO dao = new DChieu1DAO(conn);
          DuyetKQDCVO duyetVO = new DuyetKQDCVO();
          XNDCTHop1Form thForm = (XNDCTHop1Form)form;
          Collection colTTSP = new ArrayList();
          Collection colPHT = new ArrayList();
          Collection colTHDC = new ArrayList();
        DChieu1VO dcVO= new DChieu1VO();
        String chkdate =
            StringUtil.DateToString(new Date(), "dd/MM/yyyy");
          String kb_chuyen =
              session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
          String kb_code =
              session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
          String id_kb =
              session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();

          dcVO = dao.checkTTSP(kb_code, null);
          String inStr=dcVO.getChk_ttsp();
          if("KO_TTSP".equals(inStr)){
            return mapping.findForward("notRight");
          }
          if("CO_TTSP".equals(inStr)){
            request.setAttribute("notTTSP", "notTTSP");
            request.setAttribute("pht_ttsp", "pht_ttsp");
          }
          

          //            String strUD = " SELECT MAX (id) FROM sp_065 WHERE app_type = 'TTSP' AND send_bank ='"+kb_chuyen+"' GROUP BY ngay_dc, receive_bank";
          ArrayList<DuyetKQDCVO> colDChieu =
              (ArrayList<DuyetKQDCVO>)dao.getXNDCTHop_PHT(kb_chuyen, id_kb,
                                                      null);
          if (colDChieu.isEmpty()) {
              return mapping.findForward("success");
          }
          request.setAttribute("colDChieu", colDChieu);

          String rowSelected = request.getParameter("rowSelected");
          if (null == rowSelected || "".equals(rowSelected) ||
              "row_qt_0".equals(rowSelected)) {
              duyetVO = colDChieu.get(0);
              BeanUtils.copyProperties(thForm, duyetVO);
              request.setAttribute("rowSelected", "row_qt_0");
          } else {

              request.setAttribute("rowSelected", rowSelected);
          }

        String receive_bank = thForm.getReceive_bank();
        String ngay_dc = thForm.getNgay_dc();
        String tthai_dxn_thop = thForm.getTthai_dxn_thop();

        // call package tinh so lieu doi chieu tong hop
        String ttsp_id = thForm.getTtsp_id();
        String pht_id = thForm.getPht_id();
        if ("00".equals(tthai_dxn_thop)||"".equals(tthai_dxn_thop)|| tthai_dxn_thop==null) {
            CallableStatement cs = null;

            cs =
        conn.prepareCall("{call sp_doi_chieu_pkg.proc_doi_tong_hop(?,?,?,?)}");
            cs.setString(1, "0000000000000000");
            cs.setString(2, pht_id);
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);

            cs.execute();
          String p_err_code = cs.getString(3);
          String p_err_desc = cs.getString(4);
            conn.commit();

        }
        // end call;

        String strW = "";
        strW =
        "select max(id) from sp_065 a where a.receive_bank = '" + receive_bank +
        "' and send_bank='" + kb_chuyen +
        "' and to_char(a.ngay_dc,'DD/MM/RRRR') = '" + ngay_dc + "'";
        String strTTSP = strW + " AND app_type='TTSP'";
        String strPHT = strW + " AND app_type='PHT'";
        //           String strTHDC= strW + " group by  app_type";

        colTTSP = dao.getTTSP_PHT(strTTSP, null);
        colPHT = dao.getTTSP_PHT(strPHT, null);
        colTHDC = dao.getXNTHData(strTTSP, null);
        if (colTTSP.isEmpty() && colPHT.isEmpty()) {
            return mapping.findForward("success");
        }
        //            }
        request.setAttribute("colTTSP", colTTSP);
        request.setAttribute("colPHT", colPHT);            
        if (!(ngay_dc).equals(chkdate)){
          request.setAttribute("colTHDC", null);           
        }else{
          request.setAttribute("colTHDC", colTHDC); 
        }
          saveToken(request);
        } catch (Exception e) {
        throw e;

        } finally {
          
        close(conn);
        }
      return mapping.findForward("success");
  }
  
  public ActionForward update(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
      if (!checkPermissionOnFunction(request, "DCHIEU.XNDCTHOP")) {
          return mapping.findForward("notRight");
      }

      Connection conn = null;
 try {
     conn = getConnection(request);
     HttpSession session = request.getSession();

     String strUserInfo =
         (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
     
   String creator = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
     
     if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
         return mapping.findForward("notRight");
     }
//     if (isTokenValid(request)) {
         DChieu1DAO dao = new DChieu1DAO(conn);
         XNDCTHop1Form DCForm = (XNDCTHop1Form)form;
         KQDChieu1VO dcVO = new KQDChieu1VO();
         
         
         
         String ngay_dc = DCForm.getNgay_dc();
         String sysdate =
             StringUtil.DateToString(new Date(), "dd/MM/yyyy");
         String ttsp_id = request.getParameter("ttsp_id");
         String pht_id = request.getParameter("pht_id");

         CallableStatement cs = null;

         cs =
 conn.prepareCall("{call sp_doi_chieu_pkg.proc_doi_tong_hop(?,?,?,?)}");
         cs.setString(1, "0000000000000000");
         cs.setString(2, pht_id);
         cs.registerOutParameter(3, java.sql.Types.VARCHAR);
         cs.registerOutParameter(4, java.sql.Types.VARCHAR);

         cs.execute();

         String p_err_code = cs.getString(3);
         String p_err_desc = cs.getString(4);

         if (!"00".equals(p_err_code)) {
             request.setAttribute("err", p_err_desc);
         } else {
             String strKq = " AND a.id='" + ttsp_id + "'";
             dcVO = dao.getKQPHT(strKq, null);
             double thu_tcong = dcVO.getTien_thu_tcong_kbnn().doubleValue();
             double chi_tcong = dcVO.getTien_chi_tcong_kbnn().doubleValue();
             double tien_cot_kbnn = dcVO.getSodu_kbnn().doubleValue();
             double tien_cot_nh = dcVO.getSo_du().doubleValue();
             
             double chenh_cot =
                 Math.abs(tien_cot_kbnn - tien_cot_nh);
           dcVO.setCreator(creator);
             if ((ngay_dc).equals(sysdate)) {
                 if (thu_tcong == 0 && chi_tcong == 0) {
                     if (chenh_cot == 0) { // trang thai dxn khop dung
                         dcVO.setTtsp_id(ttsp_id);
                         dcVO.setPht_id(pht_id);
                         dcVO.setKet_qua_dxn_thop("0");
                         dao.updateTThaiTHop(dcVO);
                         conn.commit();
                         request.setAttribute("dung", "dung");
                     } else if (chenh_cot != 0) {
                         request.setAttribute("chenh_cot", chenh_cot);
                         return mapping.findForward("success");
                     }
                 } else if (thu_tcong != 0 || chi_tcong != 0) {
                     request.setAttribute("chenh_cot", chenh_cot);
                     request.setAttribute("thu_tcong", thu_tcong);
                     request.setAttribute("chi_tcong", chi_tcong);
                     return mapping.findForward("chenhlech");
                 }
             } else if (!(ngay_dc).equals(sysdate)) {
                 dcVO.setTtsp_id(ttsp_id);
                 dcVO.setPht_id(pht_id);
                 dcVO.setKet_qua_dxn_thop("0");
                 dao.updateTThaiTHop(dcVO);
                 conn.commit();
                 request.setAttribute("dung", "dung");
             }
         }
//     }
//     resetToken(request);
//     saveToken(request);
 } catch (Exception e) {
     throw e;
 } finally {
     close(conn);
 }

 return mapping.findForward("success");
  }
}
