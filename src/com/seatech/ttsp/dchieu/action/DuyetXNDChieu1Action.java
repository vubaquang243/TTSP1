package com.seatech.ttsp.dchieu.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DuyetKQDCVO;
import com.seatech.ttsp.dchieu.KQDChieu1VO;
import com.seatech.ttsp.dchieu.form.DuyetXNDChieu1Form;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.proxy.giaodien.SendKQDC1;
import com.seatech.ttsp.proxy.giaodien.SendLTToan;
import com.seatech.ttsp.proxy.giaodien.mq.QueueManager;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.thamsokb.ThamSoKBDAO;
import com.seatech.ttsp.thamsokb.ThamSoKBVO;

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


public class DuyetXNDChieu1Action extends AppAction {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.XNHANDCHIEU")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String kb_chuyen =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();

            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            if (strUserInfo.indexOf(AppConstants.NSD_TTV) != -1) {
                request.setAttribute("chucdanh", strUserInfo);
            } else if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
                request.setAttribute("chucdanh", strUserInfo);
            }

            DChieu1DAO dao = new DChieu1DAO(conn);
            DuyetXNDChieu1Form DCForm = (DuyetXNDChieu1Form)form;

            KQDChieu1VO dcVO = new KQDChieu1VO();
            DuyetKQDCVO duyetVO = new DuyetKQDCVO();
            Collection colTHBKDC = new ArrayList();
            Collection colKQDCCT = new ArrayList();
            SendKQDC1 send = new SendKQDC1(conn);

            String check_id = DCForm.getId();
            
//20150121-ManhNV: Sua loi******************************************            
//            ThamSoKBVO kbVO = new ThamSoKBVO();
//            ThamSoKBDAO kbDAO = new ThamSoKBDAO(conn);
//            String strKB = "";
//            strKB =
//                    " and e.ten_ts='CHO_PHEP_QUYET_TOAN_TAM' AND a.kb_id=" +
//                    session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString() + " AND a.loai_gd='03'";
//            kbVO = kbDAO.getLoai_GD(strKB, null);
//
//            String ma_nh=null;
//              if(kbVO!=null){
//                ma_nh=kbVO.getMa_nh();
//              }
//******************************************************************            
            if ("".equals(check_id) || check_id == null) {
              
                String strUD =
                    " and (a.trang_thai = '01' or ( a.trang_thai <> '01' and to_date(ngay_thuc_hien_dc)= to_date(sysdate))) and app_type='TTSP' and a.send_bank= '" +
//                    kb_chuyen + "' AND a.receive_bank<>'"+ma_nh+"'";
                    kb_chuyen +"' ";
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
                String strWhere = "'" + id + "'";

                if (id != null && !"".equals(id)) {
                    TTSPUtils spUtils = new TTSPUtils(conn);
                    String strNoiDungKy = spUtils.getNoiDungKy(id, "065", "Y");
                    DCForm.setNoi_dung_ky(strNoiDungKy);
                }

                colTHBKDC = dao.getXNKQData(strWhere, null);
                request.setAttribute("colTHBKDC", colTHBKDC);

                colKQDCCT = dao.getKQDCChiTiet(id, null);
                request.setAttribute("colKQDCCT", colKQDCCT);
            } else if (!"".equals(check_id) || check_id != null) {
                String id = DCForm.getId();
                String btt = DCForm.getBtton();
                if (!"huy".equals(btt) && !"duyet".equals(btt)) {
                    if (id != null && !"".equals(id)) {
                        TTSPUtils spUtils = new TTSPUtils(conn);
                        String strNoiDungKy =
                            spUtils.getNoiDungKy(id, "065", "Y");
                        DCForm.setNoi_dung_ky(strNoiDungKy);
                    }
                }
                if (isTokenValid(request)) {
                    if ("huy".equals(btt)) { // chenh lech
                        String bk_id = DCForm.getBk_id();
                        dcVO.setBk_id(bk_id);
                        dcVO.setId(id);
                        dcVO.setBtton(btt);
                        dao.XNHuy_Duyet(dcVO);
                        dao.updateTT(dcVO);
                        conn.commit();
                        request.setAttribute("huy", "huy");
                    }
                    if ("duyet".equals(btt)) { // chenh lech
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
                            pkiService.VerifySignature(strUserName,
                                                       strCertSerial,
                                                       strContent, strSign,
                                                       strAppID);
                        if (arrResultKy != null && arrResultKy.length == 2) {
                            if (arrResultKy[0].equalsIgnoreCase("VALID")) {
                                bVerifySignature = true;
                            } else if (arrResultKy[0].equalsIgnoreCase("INVALID")) {
                                throw TTSPException.createException("TTSP-1001",
                                                                    arrResultKy[1]);
                            } else if (arrResultKy[0].equalsIgnoreCase("ERROR")) {
                                throw TTSPException.createException("TTSP-1001",
                                                                    arrResultKy[1]);
                            }
                        }
                        if (bVerifySignature) {
                            String user_id =
                                session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                            dcVO.setId(id);
                            dcVO.setBtton(btt);
                            dcVO.setChuky_ktt(strSign);
                            String msg_id = "";
                            msg_id = send.sendMessageDC1(id, user_id);
                            dcVO.setMsg_id(msg_id);
                            dao.XNHuy_Duyet(dcVO);
                            conn.commit();
                            request.setAttribute("duyet", "duyet");
                        } else
                            request.setAttribute("duyet", "");
                    }
                }
                resetToken(request);
                saveToken(request);

                String strWhere = "'" + id + "'";
                colTHBKDC = dao.getXNKQData(strWhere, null);
                request.setAttribute("colTHBKDC", colTHBKDC);

                colKQDCCT = dao.getKQDCChiTiet(id, null);
                request.setAttribute("colKQDCCT", colKQDCCT);

                String strUD =
                    " and (a.trang_thai = '01' or ( a.trang_thai <> '01' and to_date(ngay_thuc_hien_dc)= to_date(sysdate))) and app_type='TTSP' and a.send_bank= '" +
//                    kb_chuyen + "'  AND a.receive_bank<>'"+ma_nh+"'";//20140121-ManhNV
                    kb_chuyen +"' ";
                ArrayList<DuyetKQDCVO> colDChieu =
                    (ArrayList<DuyetKQDCVO>)dao.getListXNDChieu(strUD, null);
                if (colDChieu.isEmpty()) {
                    return mapping.findForward("success");
                }
                request.setAttribute("colDChieu", colDChieu);

                String rowSelected = request.getParameter("rowSelected");

                if (null == rowSelected || "".equals(rowSelected)) {
                    duyetVO = colDChieu.get(0);
                    BeanUtils.copyProperties(DCForm, duyetVO);
                    request.setAttribute("rowSelected", "row_qt_0");
                } else {
                    request.setAttribute("rowSelected", rowSelected);
                }
            }
         

        } catch (Exception e) {
            throw e;

        } finally {
          saveToken(request);
            close(conn);
        }
        return mapping.findForward("success");
    }
  // chuyen qua ngay sau <Gui NH>
  public ActionForward update(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
  //      if (!checkPermissionOnFunction(request, "DCHIEU.DC1")) {
  //          return mapping.findForward("notRight");
  //      }

        Connection conn = null;
        QueueManager queueManager = null;
      try {
        conn = getConnection(request);
//          if (isTokenValid(request)) {
            String strMsgId = null;
            DuyetXNDChieu1Form frm = (DuyetXNDChieu1Form)form;
            LTTDAO lttDAO = new LTTDAO(conn);
            LTTVO lttVO = new LTTVO();

            HttpSession session = request.getSession();
            queueManager = new QueueManager(getThamSoHThong(session));
//          String ma_KB= frm.getReceive_bank();
//          String ma_NH= frm.getSend_bank();
            Date dateKy = new Date();         
            String strNgayKy =
                StringUtil.DateToString(dateKy, AppConstants.DATE_TIME_FORMAT_SEND_ORDER);
            
            String strHachToanTheoNgayKSNH = "Y";
            if(session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION) != null){
              strHachToanTheoNgayKSNH = session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION).toString();
            }        
            //Send msg
            queueManager.setQueueManager();
            SendLTToan sendLTT = new SendLTToan(conn, queueManager);
            strMsgId =
                    sendLTT.sendMessage(frm.getMt_id(), session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(), 
                                        session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString(),
                                        strNgayKy,"", session, "");
          if (strMsgId!=null||""!=strMsgId){
              
            
//            String strW= " AND id = '" + frm.getMt_id() +"') and ma_nh_kb='"+ma_NH + "' and ma_nh='"+ma_KB +"'";
//            Long strNgay_tt = lttDAO.getNextNgayTT(strW, null);
            long mt_id= Long.parseLong(frm.getMt_id() == null ? "" : frm.getMt_id());
            lttVO.setId(mt_id);
//            lttVO.setNgay_tt(strNgay_tt);
            lttVO.setMsg_id(strMsgId);
            lttDAO.update(lttVO); 
                conn.commit();
                try {
                    queueManager.commitMQ();
                } catch (Exception ex) {
//                    ex.printStackTrace();
          }
            }
            //          }
            //          resetToken(request);
            //          saveToken(request);
      } catch (Exception e) {
            if (queueManager != null) {
                queueManager.rollbackMQ();
            }
          throw e;

      } finally {
            if (queueManager != null) {
                queueManager.disconnectMQ();
            }
          close(conn);
      }
      return mapping.findForward("success");
  }
  
  // update lai trang thai <Duyet Lai>
        public ActionForward updateExc (ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
  //      if (!checkPermissionOnFunction(request, "DCHIEU.DC1")) {
  //          return mapping.findForward("notRight");
  //      }

        Connection conn = null;
        try {
            //          if (isTokenValid(request)) {
            //            String strMsgId = null;
            DuyetXNDChieu1Form frm = (DuyetXNDChieu1Form)form;
            conn = getConnection(request);
            LTTDAO lttDAO = new LTTDAO(conn);
            DChieu1DAO dao = new DChieu1DAO(conn);
            KQDChieu1VO kqVO = new KQDChieu1VO();
            LTTVO lttVO = new LTTVO();     
             Long mt_id= Long.parseLong(frm.getMt_id() == null ? "" : frm.getMt_id());
          String type=frm.getType();
        lttVO.setId(mt_id);
          if("LTT".equals(type)){
               String strW= " AND id="+mt_id;
              lttVO = lttDAO.checkGDDuyet(strW, null);
              Long gd_duyet=lttVO.getGd_duyet();
                if(gd_duyet!=null && 0!=gd_duyet){
                  lttVO.setId(mt_id);
                  lttVO.setTrang_thai("05");
                  lttDAO.update(lttVO);
                  //          conn.commit();
                }else if(gd_duyet==null || 0==gd_duyet){
                  lttVO.setId(mt_id);
                  lttVO.setTrang_thai("03");
                  lttDAO.update(lttVO);
                  //          conn.commit();
                }   
          }else if("DTS".equals(type)){
            lttVO.setId(mt_id);
            lttVO.setTrang_thai("02");
            lttDAO.update(lttVO);
                      conn.commit();          
          }
            kqVO.setMt_id(mt_id.toString());
            kqVO.setTthai_duyet("01");
            dao.updateTTCTiet(kqVO);
            conn.commit();
              
//          }
//          resetToken(request);
//          saveToken(request);
      } catch (Exception e) {
          throw e;

      } finally {
          close(conn);
      }
      return mapping.findForward("success");
  }
}
