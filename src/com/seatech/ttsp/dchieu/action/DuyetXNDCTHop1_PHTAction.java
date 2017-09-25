package com.seatech.ttsp.dchieu.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.DuyetKQDCVO;
import com.seatech.ttsp.dchieu.KQDChieu1VO;
import com.seatech.ttsp.dchieu.form.DuyetXNDCTHop1Form;
import com.seatech.ttsp.proxy.giaodien.SendKQDC1;
import com.seatech.ttsp.proxy.pki.PKIService;

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


public class DuyetXNDCTHop1_PHTAction extends AppAction
{
  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
      if (!checkPermissionOnFunction(request, "DCHIEU.DUYETXNDCTHOP_PHT")) {
          return mapping.findForward("notRight");
      }

      Connection conn = null;
      try {
          conn = getConnection(request);
          HttpSession session = request.getSession();
          Collection colTTSP = new ArrayList();
          Collection colPHT = new ArrayList();
          Collection colTHDC = new ArrayList();
        DChieu1DAO dao = new DChieu1DAO(conn);
        DChieu1VO dcVO= new DChieu1VO();
          String chkdate = StringUtil.DateToString(new Date(), "dd/MM/yyyy");

          String kb_chuyen =
              session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
          String kb_code =
              session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
          String strUserInfo =
              (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
          if (strUserInfo.indexOf(AppConstants.NSD_TTV) != -1) {
              request.setAttribute("chucdanh", strUserInfo);
          } else if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
              request.setAttribute("chucdanh", strUserInfo);
          }
          
        dcVO = dao.checkTTSP(kb_code, null);
        String inStr=dcVO.getChk_ttsp();
        if("KO_TTSP".equals(inStr)){
          return mapping.findForward("notRight");
        }
        if("CO_TTSP".equals(inStr)){
          request.setAttribute("notTTSP", "notTTSP");
          request.setAttribute("pht_ttsp", "pht_ttsp");
        }
          
//          DChieu1DAO dao = new DChieu1DAO(conn);
          DuyetXNDCTHop1Form DCForm = (DuyetXNDCTHop1Form)form;
          DuyetKQDCVO duyetVO = new DuyetKQDCVO();

          String strUD =
              " and app_type ='TTSP' and (tthai_dxn_thop='01' OR (a.tthai_dxn_thop = '02' AND TO_DATE (ngay_thuc_hien_dc) = TO_DATE (SYSDATE))) and a.send_bank= '" +
              kb_chuyen + "'";
          ArrayList<DuyetKQDCVO> colDChieu =
              (ArrayList<DuyetKQDCVO>)dao.getListXNDChieu(strUD, null);
          if (colDChieu.isEmpty()) {
              return mapping.findForward("success");
          }
          request.setAttribute("colDChieu", colDChieu);

          String rowSelected = request.getParameter("rowSelected");

          if (null == rowSelected || "".equals(rowSelected) ||
              "row_qt_0".equals(rowSelected)) {
              duyetVO = colDChieu.get(0);
              BeanUtils.copyProperties(DCForm, duyetVO);
              request.setAttribute("rowSelected", "row_qt_0");
          } else {
              request.setAttribute("rowSelected", rowSelected);
          }
          String id = DCForm.getId();

          if (id != null && !"".equals(id)) {
              TTSPUtils spUtils = new TTSPUtils(conn);
              String strNoiDungKy = spUtils.getNoiDungKy(id, "065", "Y");
              DCForm.setNoi_dung_ky(strNoiDungKy);
          }

          
          String strW = "";
          strW = "'" + id + "'";
          String strTTSP = strW;
          String strPHT =
              " SELECT pht_id from sp_065 where id='" + id + "'";
          // String strTHDC= strW ;

          colTTSP = dao.getTTSP_PHT(strTTSP, null);
          colPHT = dao.getTTSP_PHT(strPHT, null);
          colTHDC = dao.getXNTHData(strTTSP, null);

          if (colTTSP.isEmpty() && colPHT.isEmpty()) {
              return mapping.findForward("success");
          }
          request.setAttribute("colTTSP", colTTSP);
          request.setAttribute("colPHT", colPHT);
        String ngay_dc = DCForm.getNgay_dc();
          if (!(ngay_dc).equals(chkdate)) {
              request.setAttribute("colTHDC", null);
          } else {
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
      Connection conn = null;
      try {

          conn = getConnection(request);
          HttpSession session = request.getSession();
          String kb_chuyen =
              session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();

          String strUserInfo =
              (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
        String manager = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
          if (strUserInfo.indexOf(AppConstants.NSD_TTV) != -1) {
              request.setAttribute("chucdanh", strUserInfo);
          } else if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
              request.setAttribute("chucdanh", strUserInfo);
          }
          if (isTokenValid(request)) {
              DChieu1DAO dao = new DChieu1DAO(conn);
              DuyetXNDCTHop1Form DCForm = (DuyetXNDCTHop1Form)form;
              KQDChieu1VO dcVO = new KQDChieu1VO();

              String id = DCForm.getId();
              String btt = request.getParameter("btton");

              //            String bk_id = DCForm.getBk_id();

              SendKQDC1 send = new SendKQDC1(conn);
              String maNH = DCForm.getReceive_bank();
              //            long so_du = DCForm.getSo_du();
              String ngay_dc = DCForm.getNgay_dc();
              //          ngay_dc =
              //                  StringUtil.DateToString(StringUtil.StringToDate(ngay_dc, "dd/MM/yyyy"),
              //                                          "dd/MM/yyyy");
              //verify
              boolean bVerifySignature = false;
              String strWSDL = getThamSoHThong("WSDL_PKI", session);
              PKIService pkiService = new PKIService(strWSDL);
              String strUserName =
                  session.getAttribute(AppConstants.APP_DOMAIN_SESSION) +
                  "\\" +
                  session.getAttribute(AppConstants.APP_USER_CODE_SESSION);
              String strSign = DCForm.getChuky_ktt();
              String strContent = DCForm.getNoi_dung_ky();
              String strCertSerial = DCForm.getCertserial();
              String strAppID = getThamSoHThong("APP_ID", session);
              String[] arrResultKy =
                  pkiService.VerifySignature(strUserName, strCertSerial,
                                             strContent, strSign, strAppID);
              if (arrResultKy != null && arrResultKy.length == 2) {
                  if (arrResultKy[0].equalsIgnoreCase("VALID")) {
                      bVerifySignature = true;
                  } else if (arrResultKy[0].equalsIgnoreCase("INVALID")) {
                      throw TTSPException.createException("TTSP-3001",
                                                          arrResultKy[1]);
                  } else if (arrResultKy[0].equalsIgnoreCase("ERROR")) {
                      throw TTSPException.createException("TTSP-3001",
                                                          arrResultKy[1]);
                  }
              }
              if (bVerifySignature) {
                  dcVO.setChuky_ktt_dxn_thop(strSign);
  //                    dcVO.setNgay_dc(ngay_dc);
//                  dcVO.setSend_bank(maNH);
                  dcVO.setManager(manager);                  
//                  dcVO.setReceive_bank(kb_chuyen);


                  String user_id =
                      session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                  String msg_th_id = "";
                    msg_th_id = send.sendMessageDCTHop(id, user_id);

                  dcVO.setId(id);
                  dcVO.setBtton(btt);
                  dcVO.setMsg_th_id(msg_th_id);
                  dcVO.setTthai_dxn_thop("02");
                  dcVO.setTthai_ttin_thop("01");
                  dao.DuyetDCTHop(dcVO);
                      conn.commit();
                  request.setAttribute("duyet", "duyet");
              } else
                  request.setAttribute("duyet", "");

          }
          resetToken(request);
          saveToken(request);
      } catch (Exception e) {
          throw e;

      } finally {
          close(conn);
      }
      return mapping.findForward("success");
  }
  
  public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
    Connection conn = null;
    try {

        conn = getConnection(request);
        HttpSession session = request.getSession();
        String kb_chuyen =
            session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();

        String strUserInfo =
            (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
      String manager = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
        if (strUserInfo.indexOf(AppConstants.NSD_TTV) != -1) {
            request.setAttribute("chucdanh", strUserInfo);
        } else if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
            request.setAttribute("chucdanh", strUserInfo);
        }
        if (isTokenValid(request)) {
            DChieu1DAO dao = new DChieu1DAO(conn);
            DuyetXNDCTHop1Form DCForm = (DuyetXNDCTHop1Form)form;
            KQDChieu1VO dcVO = new KQDChieu1VO();

            String id = DCForm.getId();

              dcVO.setTthai_dxn_thop("00");
          dcVO.setTthai_ttin_thop("01");
              dcVO.setId(id);
              dao.DuyetDCTHop(dcVO);
              conn.commit();
              request.setAttribute("huy", "huy");

        }
        resetToken(request);
        saveToken(request);
    } catch (Exception e) {
        throw e;

    } finally {
        close(conn);
    }
    return mapping.findForward("success");
  }
  
  
}
