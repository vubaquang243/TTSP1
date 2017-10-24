package com.seatech.ttsp.duyetLTT.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.AppKeys;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.duyetLTT.duyetLTTDAO;
import com.seatech.ttsp.duyetLTT.duyetLTTVO;
import com.seatech.ttsp.duyetLTT.form.duyetLTTForm;
import com.seatech.ttsp.logduyetlo.dao.LogDuyetLoDAO;
import com.seatech.ttsp.logduyetlo.vo.LogDuyetLoVO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
//import com.seatech.ttsp.proxy.giaodien.SendLTToan;
//import com.seatech.ttsp.proxy.giaodien.mq.QueueManager;
import com.seatech.ttsp.proxy.giaodien.BuildMsgLTT;
import com.seatech.ttsp.proxy.pki.PKIService;

import java.math.BigDecimal;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class duyetLTTAction extends AppAction {
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
      /**- Nguoi sua: ManhNV
       * - Noi dung: 
       * + Bo sung dieu kien truy van theo ngay_nhan (ngay_nhan > SYSDATE-"tham_so")
       * + Dap ung sua loi: Java heap size
       * - Ngay sua 01/11/2016
       * - Key tim kiem: 20161101-JHS-DUYETLO
       * */
      /**
       * - ManhNV
       * - 22/11/2016
       * - Sua truyen dien 2 lan
       * - key tim kiem: DOUBLE_20161122
       ***/
        String strSoNgayTruyVan = "100";
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();

          /***********20161101-JHS-DUYETLO-lay tham so tu session-BEGIN************/
          if (session.getAttribute("SO_NGAY_TRUY_VAN_XU_LY_LTT") !=
              null) {
              strSoNgayTruyVan =
                      session.getAttribute("SO_NGAY_TRUY_VAN_XU_LY_LTT").toString();
          }
          /***********20161101-JHS-DUYETLO-lay tham so tu session-END************/

            duyetLTTDAO lttDAO = new duyetLTTDAO(conn);
            //            duyetLTTVO duyetVO = new duyetLTTVO();
            duyetLTTForm frm = (duyetLTTForm)form;
            List dmucNH = null;
            List colTTV = null;
            List colTTVTABMIS = null;
            Collection colLTT = null;
            Collection colMonTien = null;

            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            String page = frm.getPageNumber();
            if (page == null || "".equals(page))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[15];

            String kb_id =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            String nhkb_id =
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
          String nsd_id =
              session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);

            request.setAttribute("chuc_danh", strUserInfo);

            String strW = " AND kb_id=" + kb_id;
            dmucNH = (List)lttDAO.getDMucNH(strW, null);
            request.setAttribute("dmucNH", dmucNH);

            String strTTV =
                " AND kb_id=" + kb_id + " AND upper(c.loai_nhom)='TTV'";
            colTTV = (List)lttDAO.getDsachTTV(strTTV, null);
            request.setAttribute("colTTV", colTTV);

            String strTTVTABMIS = "  AND a.kb_id=" + kb_id;
            colTTVTABMIS = (List)lttDAO.getDsachKTV(strTTVTABMIS, null);
            request.setAttribute("colTTVTABMIS", colTTVTABMIS);
            /***********20161101-JHS-DUYETLO-them dk ngay nhan-BEGIN************/
            String strlstLTT = " AND t.ngay_nhan > (SYSDATE-"+strSoNgayTruyVan+") AND t.nhkb_chuyen=" + nhkb_id;
//          String strlstLTT = " AND t.nhkb_chuyen=" + nhkb_id;
            /***********20161101-JHS-DUYETLO-them dk ngay nhan-BEGIN************/
            if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
                strlstLTT += " AND t.trang_thai='03' AND t.ttv_nhan <> "+nsd_id+" ";
            } else if (strUserInfo.indexOf(AppConstants.NSD_GD) != -1) {
                strlstLTT += " AND t.trang_thai='05'";
            }
            String ma_ttv = frm.getMa_nsd() == null ? "" : frm.getMa_nsd();
            String ma_tabmis =
                frm.getMa_tabmis() == null ? "" : frm.getMa_tabmis();
            String so_ltt = frm.getSo_ltt() == null ? "" : frm.getSo_ltt();
            String so_yctt = frm.getSo_yctt() == null ? "" : frm.getSo_yctt();
            String so_tien = frm.getSo_tien() == null ? "" : frm.getSo_tien();
            String ma_nh = frm.getMa_nh() == null ? "" : frm.getMa_nh();
            String loai_tien = frm.getNt_id() == null ? "" : frm.getNt_id();

            if (ma_ttv != null && !"".equals(ma_ttv)) {
                strlstLTT += " AND t.ttv_nhan=" + ma_ttv;
            }
            if (ma_tabmis != null && !"".equals(ma_tabmis)) {
                strlstLTT += " AND t.ktv_chuyen=" + ma_tabmis;
            }
            if (so_ltt != null && !"".equals(so_ltt)) {
                strlstLTT += " AND t.id like '%" + so_ltt.trim() + "%'";
            }
            if (so_yctt != null && !"".equals(so_yctt)) {
                strlstLTT += " AND t.so_yctt like '%" + so_yctt.trim() + "%'";
            }
            if (so_tien != null && !"".equals(so_tien)) {
                BigDecimal bdSoTien = StringUtil.convertCurrencyToNumber(so_tien, loai_tien);   
                if(bdSoTien.compareTo(new BigDecimal(0))>0){
                  strlstLTT +=
                          " AND t.tong_sotien=" + bdSoTien;
                }
            }
            if (ma_nh != null && !"".equals(ma_nh)) {
                strlstLTT += " AND t.nhkb_nhan=" + ma_nh.trim();
            }
            if (loai_tien != null && !"".equals(loai_tien)) {
                strlstLTT += " AND t.nt_id=" + loai_tien.trim();
            }else{
               strlstLTT += " AND t.nt_id= 177 ";
               frm.setNt_id("177");
            }
            
            colLTT =
                    lttDAO.getlstLTT_PTrang(strlstLTT, null, currentPage, numberRowOnPage,
                                            totalCount);
            colMonTien = lttDAO.getTienMon(strlstLTT, null);
            
            //Danh sach tien te
            DMTienTeDAO dmTienTeDAO = new DMTienTeDAO(conn);
            Collection listDMTienTe =
                dmTienTeDAO.getDMTienTeCuaDViList(" and a.tinh_trang = '01' and b.kb_id = " + kb_id +
                                                  " and b.loai_tk in ('02','03') ",
                                                  null);
            request.setAttribute("listDMTienTe", listDMTienTe);
            /////

            request.setAttribute("colMonTien", colMonTien);
            request.setAttribute("colLTT", colLTT);
            PagingBean pagingBean = new PagingBean();

            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(15);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("initAction", "initAction");
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
//        QueueManager queueManager = null;//DOUBLE_20161122
      /**- Nguoi sua: ManhNV
       * - Noi dung: 
       * + Sua code quan ly global transaction (MQ, DB)
       * + Dap ung sua loi: Truyen lenh 2 lan
       * - Ngay sua 01/11/2016
       * - Key tim kiem: 20161101-DOUBLE-DUYETLO
       * */
        try {
            if (isTokenValid(request)) {
                conn = getConnection(request);
                HttpSession session = request.getSession();

                //            duyetLTTDAO duyetDAO = new duyetLTTDAO(conn);
                LTTDAO lttDAO = new LTTDAO(conn);
                duyetLTTForm frm = (duyetLTTForm)form;
                duyetLTTVO duyetVO = new duyetLTTVO();

                LogDuyetLoDAO log = new LogDuyetLoDAO(conn);
                LogDuyetLoVO vo = null;
                LTTVO lttVO = new LTTVO();
                String[] idArr = request.getParameterValues("selector");
                String[] chuky_gd = request.getParameterValues("chuky_gd");
                String[] chuky_ktt = request.getParameterValues("chuky_ktt");
                //            String[] so_yctt = request.getParameterValues("so_yctt");

                int len = idArr.length;
                String idList = "";
                String strUserInfo =
                    (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                Long nUserID =
                    Long.parseLong(session.getAttribute("id_nsd").toString());

                duyetVO.setNgay_hoan_thien("SYSDATE");
                String strStatusNewest = "";
                String strTrangThai = "";
                String strChuKy = "";

                LTTVO lttVOForUpdate = new LTTVO();
                String whereClause = "";
                String strNgayKy = "";
                String strMsgId = "";
                String lst_yctt = "";
                String lst_yctt_false = "";
                String gui_false = "";
                String ex_false = "";
                String yctt = null;
                Long id = null;
                String strNgayLamViec = "";
                //ManhNV-20150226
                TTSPUtils ttspUtil = null;
                String strSetLock = "";
                String strSoLTT = "";
                String strUserId = "";
                int nCounter = 0;
                //*******
//                DOUBLE_20161122-BEGIN
//                queueManager = new QueueManager(getThamSoHThong(session));
//                if (strUserInfo.indexOf(AppConstants.NSD_GD) != -1) {
//                    queueManager.setQueueManager();
//                }
//              DOUBLE_20161122-END
                for (int i = 0; i < len; ++i) {
                    nCounter ++;
                    if (!idArr[i].equals("")) {
                        try {
                            strSoLTT = "";
                            vo = new LogDuyetLoVO();
                            Date dateKy = new Date();
                            Long lNgayTT = new Long(0);
                            idList = idArr[i];
                            String strID =
                                idList.substring(0, idList.indexOf("se@techit"));
                            yctt =
idList.substring(idList.indexOf("se@techit") + 9,
                 idList.indexOf("yctt@seatechit"));
                            String strContent =
                                idList.substring(idList.indexOf("yctt@seatechit") +
                                                 14);

                            strNgayKy =
                                    StringUtil.DateToString(dateKy, AppConstants.DATE_TIME_FORMAT_SEND_ORDER);

                            strNgayLamViec =
                                    StringUtil.DateToString(new Date(),
                                                            "yyyyMMdd");
                            lNgayTT = new Long(strNgayLamViec);

                            boolean bVerifySignature = false;
                            String strWSDL =
                                getThamSoHThong("WSDL_PKI", session);
                            PKIService pkiService = new PKIService(strWSDL);
                            String strUserName =
                                session.getAttribute(AppConstants.APP_DOMAIN_SESSION) +
                                "\\" +
                                session.getAttribute(AppConstants.APP_USER_CODE_SESSION);

                            String strSign = "";

                            if (strUserInfo.indexOf(AppConstants.NSD_KTT) !=
                                -1) {
                                strSign = chuky_ktt[i];

                            } else if (strUserInfo.indexOf(AppConstants.NSD_GD) !=
                                       -1) {
                                strSign = chuky_gd[i];
                            }


                            //= frm.getNoi_dung_ky();
                            String strCertSerial = frm.getCertserial();
                            String strAppID =
                                getThamSoHThong("APP_ID", session);
                            String[] arrResultKy =
                                pkiService.VerifySignature(strUserName,
                                                           strCertSerial,
                                                           strContent, strSign,
                                                           strAppID);
                            if (arrResultKy != null &&
                                arrResultKy.length == 2) {
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
                                whereClause = " t.id = " + strID;
                                //                                lttVOForUpdate =
                                //                                        lttDAO.getLTTVOForUpdate(whereClause,
                                //                                                                 null);
                                //******************************************************************************
                                //Manhnv-20150226: Sua bo for update
                                //Kiem tra thay the for update
                                ttspUtil = new TTSPUtils(conn);
                                strSoLTT = strID;
                                strUserId =
                                        session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
                                strSetLock =
                                        ttspUtil.setLock(strSoLTT, "APPROVE-LTT",
                                                         new Long(strUserId));
                                if (!"SUCCESS".equalsIgnoreCase(strSetLock)) {
                                    if(nCounter == len){
                                      throw new Exception("LTT &#273;ang &#273;&#432;&#7907;c x&#7917; l&#253; b&#7903;i NSD kh&#225;c(" +
                                                          strSetLock +
                                                          "), h&#227;y ch&#7885;n LTT kh&#225;c &#273;&#7875; ti&#7871;p t&#7909;c.");
                                    }else{
                                      continue;
                                    }
                                }
                                lttVOForUpdate =
                                        lttDAO.getLTTVO(whereClause, null);
                                //******************************************************************************
                                strStatusNewest =
                                        lttVOForUpdate.getTrang_thai();

                                if (strUserInfo.indexOf(AppConstants.NSD_KTT) !=
                                    -1) {
                                    if (strStatusNewest.equalsIgnoreCase("03")) {
                                        strTrangThai = "05";
                                        strChuKy = chuky_ktt[i];
                                        lttVO.setChuky_ktt(strChuKy);
                                        lst_yctt = lst_yctt + ",\\n" +
                                                yctt;
                                    } else {
                                        lst_yctt_false =
                                                lst_yctt_false + ",\\n" +
                                                yctt;
                                    }
                                    String strTrangThaiTW = "00";
                                    if (AppConstants.DIEU_HANH_VON.equals(strMsgId)) {
                                        if (AppConstants.KBNN_SGD_BANK_CODE.equals(session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION))) {
                                            strTrangThaiTW = "02";
                                        } else {
                                            strTrangThaiTW = "01";
                                        }
                                    }
                                    id = Long.parseLong(strID);
                                    lttVO.setId(id);
                                    lttVO.setTrang_thai(strTrangThai);
                                    lttDAO.updateStatus(id, strTrangThai,
                                                        nUserID, strUserInfo,
                                                        null, strChuKy,
                                                        strMsgId, dateKy,
                                                        lNgayTT,
                                                        strTrangThaiTW);
                                    
                                    if (lst_yctt != null &&
                                        !"".equals(lst_yctt)) {
                                        vo.setError_code("00");
                                        vo.setError_desc("Thanh cong");
                                        vo.setGhi_chu("");
                                        vo.setNgay_duyet(new Date());
                                        vo.setLtt_id(id);
                                        vo.setSo_yctt(yctt);
                                        vo.setMa_kb(session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString());
                                        vo.setMa_nsd(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());

                                        log.insertLogDuyetLo(vo);
                                    }

                                    conn.commit();

                                } else if (strUserInfo.indexOf(AppConstants.NSD_GD) !=
                                           -1) {
                                    if (strStatusNewest.equalsIgnoreCase("05")) {
                                        strTrangThai = "08";
                                        strChuKy = chuky_gd[i];
                                        lttVO.setChuky_gd(strChuKy);
                                        lst_yctt = lst_yctt + ",\\n" +
                                                yctt;
                                    } else {
                                        lst_yctt_false =
                                                lst_yctt_false + ",\\n" +
                                                yctt;
                                    }
                                    if ("08".equals(strTrangThai)) {
                                        String strHachToanTheoNgayKSNH = "Y";
                                        if (session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION) !=
                                            null) {
                                            strHachToanTheoNgayKSNH =
                                                    session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION).toString();
                                        }
                                        //send ltt
//                                        SendLTToan sendLTT =
//                                            new SendLTToan(conn, queueManager); //DOUBLE_20161122
                                        BuildMsgLTT sendLTT =  new BuildMsgLTT(conn);
                                        strMsgId =
                                                sendLTT.sendMessage(strID, session.getAttribute(AppConstants.APP_KB_CODE_SESSION) ==
                                                                           null ?
                                                                           "" :
                                                                           session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(),
                                                                    session.getAttribute(AppConstants.APP_USER_CODE_SESSION) ==
                                                                    null ? "" :
                                                                    session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString(),
                                                                    strNgayKy,
                                                                    strHachToanTheoNgayKSNH,
                                                                    session,
                                                                    "");
                                        if (strMsgId == null ||
                                            "".equals(strMsgId)) {
                                            gui_false = gui_false + ",\\n" +
                                                    yctt;
                                        } else {
                                            id = Long.parseLong(strID);
                                            lttVO.setId(id);
                                            lttVO.setTrang_thai(strTrangThai);
                                            String strTrangThaiTW = "00";
                                            if (AppConstants.DIEU_HANH_VON.equals(strMsgId)) {
                                                if (AppConstants.KBNN_SGD_BANK_CODE.equals(session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION))) {
                                                    strTrangThaiTW = "02";
                                                } else {
                                                    strTrangThaiTW = "01";
                                                }
                                            }
                                            lttDAO.updateStatus(id,
                                                                strTrangThai,
                                                                nUserID,
                                                                strUserInfo,
                                                                null, strChuKy,
                                                                strMsgId,
                                                                dateKy,
                                                                lNgayTT,
                                                                strTrangThaiTW);


                                            if (lst_yctt != null &&
                                                !"".equals(lst_yctt)) {
                                                vo.setError_code("00");
                                                vo.setError_desc("Thanh cong");
                                                vo.setGhi_chu("");
                                                vo.setNgay_duyet(new Date());
                                                vo.setLtt_id(id);
                                                vo.setSo_yctt(yctt);
                                                vo.setMa_kb(session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString());
                                                vo.setMa_nsd(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());

                                                log.insertLogDuyetLo(vo);
                                            }
                                            conn.commit();
//                                          DOUBLE_20161122-BEIGN
//                                            try {
//                                                queueManager.commitMQ();
//                                            } catch (Exception ex) {
//                                                lttDAO.updateStatus(id, "05",
//                                                                    null, null,
//                                                                    null, null,
//                                                                    null, null,
//                                                                    null,
//                                                                    null);
//                                                conn.commit();
//                                                throw ex;
//                                            }
//                                          DOUBLE_20161122-END
                                        }
                                    }
                                }


                            }

                        } catch (TTSPException e) {
//                          DOUBLE_20161122-BEGIN
//                            if (queueManager != null) {
//                                queueManager.rollbackMQ();
//                            }
//                          DOUBLE_20161122-END
                            conn.rollback();////20161101-DOUBLE-DUYETLO
                            
                            ServletContext context =
                                servlet.getServletContext();
                            HashMap errorMap =
                                (HashMap)context.getAttribute(AppKeys.ERROR_LIST_APPLICATION_KEY);
                            String msgError = e.getMessage(errorMap);
                            //                      System.err.println(msgError);
                            ex_false = ex_false + ",\\n" +
                                    yctt + msgError;
                            vo.setError_code("01");
                            vo.setError_desc(msgError);
                            vo.setGhi_chu("");
                            vo.setNgay_duyet(new Date());
                            vo.setLtt_id(id);
                            vo.setSo_yctt(yctt);
                            vo.setMa_kb(session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString());
                            vo.setMa_nsd(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());

                            log.insertLogDuyetLo(vo);
                            conn.commit();
                            throw e;
                        } catch (Exception e) {
//                            DOUBLE_20161122-BEGIN
//                            if (queueManager != null) {
//                                queueManager.rollbackMQ();
//                            }
//                          DOUBLE_20161122-END
                            conn.rollback();////20161101-DOUBLE-DUYETLO
//                            ServletContext context =
//                                servlet.getServletContext();
//                            HashMap errorMap =
//                                (HashMap)context.getAttribute(AppKeys.ERROR_LIST_APPLICATION_KEY);
                            String msgError = e.getMessage();
                            ex_false = ex_false + ",\\n" +
                                    yctt + msgError;
                            vo.setError_code("01");
                            vo.setError_desc(msgError);
                            vo.setGhi_chu("");
                            vo.setNgay_duyet(new Date());
                            vo.setLtt_id(id);
                            vo.setSo_yctt(yctt);
                            vo.setMa_kb(session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString());
                            vo.setMa_nsd(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());

                            log.insertLogDuyetLo(vo);
                            conn.commit();
                            throw e;
                        } finally {
                            //unlock
                            if ("SUCCESS".equalsIgnoreCase(strSetLock)) {
                                if (ttspUtil == null) {
                                    ttspUtil = new TTSPUtils(conn);
                                }
                                ttspUtil.unLock(strSoLTT, "APPROVE-LTT");
                            }
                            //
                        }
                    }

                }
                if (lst_yctt != null && !"".equals(lst_yctt)) {
                    String lst_ok = lst_yctt.substring(1);
                    request.setAttribute("lst_ok", lst_ok);
                }
                if (lst_yctt_false != null && !"".equals(lst_yctt_false)) {
                    String notOk = lst_yctt_false.substring(1);
                    request.setAttribute("notOk", notOk);
                }
                if (gui_false != null && !"".equals(gui_false)) {
                    String gui = gui_false.substring(1);
                    request.setAttribute("gui", gui);
                }
                if (ex_false != null && !"".equals(ex_false)) {
                    String ex_fal = ex_false.substring(1);
                    request.setAttribute("ex_fal", ex_fal);
                }
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
//          DOUBLE_20161122-BEGIN
//            if (queueManager != null) {
//                queueManager.disconnectMQ();
//            }
//          DOUBLE_20161122-END
            close(conn);
        }
        return mapping.findForward("success");
    }
}
