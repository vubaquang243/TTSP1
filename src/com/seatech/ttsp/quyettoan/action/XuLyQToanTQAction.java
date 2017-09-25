package com.seatech.ttsp.quyettoan.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangHOVO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanDAO;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanVO;
//import com.seatech.ttsp.proxy.giaodien.SendLQToan;
//import com.seatech.ttsp.proxy.giaodien.mq.QueueManager;
import com.seatech.ttsp.proxy.giaodien.BuildMsgLQT;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.quyettoan.BKE_QuyetToanVO;
import com.seatech.ttsp.quyettoan.QuyetToanDAO;
import com.seatech.ttsp.quyettoan.QuyetToanVO;
import com.seatech.ttsp.quyettoan.form.BKE_QuyetToanForm;
import com.seatech.ttsp.quyettoan.form.QuyetToanForm;

import java.io.InputStream;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class XuLyQToanTQAction extends AppAction {
    public XuLyQToanTQAction() {
        super();
    }

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strWhereClause = null;
        QuyetToanDAO qtDAO = null;
        Vector params = null;
        Vector paramsQTVO = null;
        Vector paramsQT = null;
        String whereClause = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "QTOAN.TQUOC.XLyBKE")) {
                return mapping.findForward("errorQuyen");
            } else {
                conn = getConnection();
                HttpSession session = request.getSession();
                BKE_QuyetToanForm f = (BKE_QuyetToanForm)form;
                // back ve gom bang ke
                String strBack =
                    request.getParameter(AppConstants.REQUEST_ACTION);
                if (strBack != null && !"".equals(strBack)) {
                    request.setAttribute("ViewBK", strBack);
                }
                // end
                String strUserInfo =
                    (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                if (strUserInfo.indexOf(AppConstants.NSD_TTV) != -1) {
                    request.setAttribute("chucdanh", strUserInfo);
                } else if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
                    request.setAttribute("chucdanh", strUserInfo);
                } else {
                    request.setAttribute("chucdanh", strUserInfo);
                }
                params = new Vector();
                qtDAO = new QuyetToanDAO(conn);
                strWhereClause =
                        "((a.trang_thai in (?,?) and TRUNC(sysdate) <= a.ngay_ks) or a.trang_thai in(?,?))  ";
                params.add(new Parameter(AppConstants.TRANG_THAI_BK_DADUYET,
                                         true));
                params.add(new Parameter(AppConstants.TRANG_THAI_BK_HUY,
                                         true));
                params.add(new Parameter(AppConstants.TRANG_THAI_BK_CHODUYET,
                                         true));
                params.add(new Parameter(AppConstants.TRANG_THAI_BK_DAYLAI,
                                         true));
                ArrayList<BKE_QuyetToanVO> lstDanhSachBKe =
                    (ArrayList<BKE_QuyetToanVO>)qtDAO.getBKeQToanList(strWhereClause,
                                                                      params);

                request.setAttribute("lstDanhSachBKe", lstDanhSachBKe);
                // lay ra chi tiet bang ke
                paramsQTVO = new Vector();
                if (lstDanhSachBKe.size() > 0) {
                    BKE_QuyetToanVO voFirst = lstDanhSachBKe.get(0);
                    BeanUtils.copyProperties(f, voFirst);
                    f.setSo_tien(StringUtil.formatCurrencyByCode(f.getSo_tien(), f.getTcg_loai_tien()));
                    
                    // danh sach quyet toan
                    String page = f.getPageNumber();
                    if (page == null) {
                        page = "1";
                    }
                    // khai bao cac bien phan trang
                    Integer currentPage = new Integer(page);
                    Integer numberRowOnPage = new Integer(15);
                    Integer totalCount[] = new Integer[1];
                    whereClause = "a.so_bk=? ";
                    // kiem tra neu trang thai 04 - da duyet chi hien trong ngay
                    paramsQT = new Vector();
                    paramsQT.add(new Parameter(voFirst.getId(), true));
                    // set trang thai cho ban ghi dau tien
                    BKE_QuyetToanVO voMoTaTT = qtDAO.findBKeByPK(paramsQT);
                    f.setMo_ta_trang_thai(voMoTaTT.getMo_ta_trang_thai());
                    String strPhanQuyen =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                    if (strPhanQuyen.contains(AppConstants.NSD_KTT)) {
                        TTSPUtils spUtil = new TTSPUtils(conn);
                        String strNoiDungKy =
                            spUtil.getNoiDungKy(voFirst.getId(), "BK900", "N");
                        f.setNoi_dung_ky(strNoiDungKy);
                    }
                    // end
                    ArrayList<QuyetToanForm> lstQuyetToan =
                        (ArrayList<QuyetToanForm>)qtDAO.getLQTListWithPading(whereClause,
                                                                             paramsQT,
                                                                             currentPage,
                                                                             numberRowOnPage,
                                                                             totalCount);
                    PagingBean pagingBean = new PagingBean();
                    pagingBean.setCurrentPage(currentPage);
                    pagingBean.setNumberOfRow(totalCount[0].intValue());
                    pagingBean.setRowOnPage(numberRowOnPage);
                    request.setAttribute("lstQuyetToan", lstQuyetToan);
                    request.setAttribute("PAGE_KEY", pagingBean);
                }
                request.setAttribute("rowSelected", "row_qt_0");
            }
        } catch (Exception e) {
            throw new Exception("X&#7917; l&#253; quy&#7871;t to&#225;n to&#224;n qu&#7889;c : " +
                                e.getMessage());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        Vector params = null;
        Vector paramsVO = null;
        BKE_QuyetToanVO vo = null;
        String whereClause = null;
        Vector paramsQT = null;
        String strWhereClause = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            //            if (!checkPermissionOnFunction(request, "QTOAN.TQUOC.XLyBKE")) {
            //                return mapping.findForward("errorQuyen");
            //            } else {
            conn = getConnection();
            BKE_QuyetToanForm f = (BKE_QuyetToanForm)form;
            HttpSession session = request.getSession();
            QuyetToanDAO dao = new QuyetToanDAO(conn);

            String strRowSelected =
                request.getParameter("rowSelected") == null ? "" :
                request.getParameter("rowSelected");

            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            //                if (strUserInfo.indexOf(AppConstants.NSD_TTV) != -1) {
            //                    request.setAttribute("chucdanh", strUserInfo);
            //                } else if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
            request.setAttribute("chucdanh", strUserInfo);
            //                }else{
            //
            //                }
            strWhereClause =
                    "((a.trang_thai in (?,?) and TRUNC(sysdate) <= a.ngay_ks) or a.trang_thai in(?,?))  ";
            params = new Vector();
            params.add(new Parameter(AppConstants.TRANG_THAI_BK_DADUYET,
                                     true));
            params.add(new Parameter(AppConstants.TRANG_THAI_BK_HUY, true));
            params.add(new Parameter(AppConstants.TRANG_THAI_BK_CHODUYET,
                                     true));
            params.add(new Parameter(AppConstants.TRANG_THAI_BK_DAYLAI, true));
            ArrayList<BKE_QuyetToanVO> lstDanhSachBKe =
                (ArrayList<BKE_QuyetToanVO>)dao.getBKeQToanList(strWhereClause,
                                                                params);
            request.setAttribute("lstDanhSachBKe", lstDanhSachBKe);

            // chi tiet bk
            paramsVO = new Vector();
            paramsVO.add(new Parameter(request.getParameter("id"), true));
            vo = dao.findBKeByPK(paramsVO);
            vo.setChuc_danh(strUserInfo);
            BeanUtils.copyProperties(f, vo);
            
            f.setSo_tien(StringUtil.formatCurrencyByCode(f.getSo_tien(), f.getTcg_loai_tien()));
            
            TTSPUtils spUtil = new TTSPUtils(conn);
            String strNoiDungKy =
                spUtil.getNoiDungKy(request.getParameter("id"), "BK900", "N");
            f.setNoi_dung_ky(strNoiDungKy);

            // danh sach quyet toan
            String page = f.getPageNumber();
            if (page == null) {
                page = "1";
            }
            // khai bao cac bien phan trang
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = new Integer(15);
            Integer totalCount[] = new Integer[1];
            whereClause = "a.so_bk=? ";
            paramsQT = new Vector();
            paramsQT.add(new Parameter(request.getParameter("id"), true));
            ArrayList<QuyetToanForm> lstQuyetToan =
                (ArrayList<QuyetToanForm>)dao.getLQTListWithPading(whereClause,
                                                                   paramsQT,
                                                                   currentPage,
                                                                   numberRowOnPage,
                                                                   totalCount);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("lstQuyetToan", lstQuyetToan);
            request.setAttribute("PAGE_KEY", pagingBean);

            request.setAttribute("rowSelected", strRowSelected);
            //
            //            }
        } catch (Exception e) {
            throw new Exception("Xu Ly Quyet Toan Toan Quoc : " +
                                e.getMessage());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Connection conn = null;
        QuyetToanDAO qtDAO = null;
        String strActionHuy = "ACTION_REMOVE";
        String strActionDayLai = "ACTION_RETURN";
        String strActionDuyet = "ACTION_PASS";
        Vector params = null;
      /**
       * - ManhNV
       * - 22/11/2016
       * - Sua truyen dien 2 lan
       * - key tim kiem: DOUBLE_20161122
       ***/
//        QueueManager queueManager = null;//DOUBLE_20161122
        String msgID = null;
        try {
            conn = getConnection();

            HttpSession session = request.getSession();
//            queueManager = new QueueManager(getThamSoHThong(session));//DOUBLE_20161122

            String strUserKTT =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                null ?
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString() :
                "";
            params = new Vector();
            String strAction =
                request.getParameter(AppConstants.REQUEST_ACTION);
            qtDAO = new QuyetToanDAO(conn);

            BKE_QuyetToanForm bkeQtoanForm = (BKE_QuyetToanForm)form;
            bkeQtoanForm.setSo_tien(StringUtil.convertCurrencyToNumber(bkeQtoanForm.getSo_tien(), bkeQtoanForm.getTcg_loai_tien()).toString());
            int result = 0;
            if (strAction != null && !"".equals(strAction)) {
                BKE_QuyetToanVO bke_vo = new BKE_QuyetToanVO();
                BeanUtils.copyProperties(bke_vo, bkeQtoanForm);
                // Huy
                if (strAction.equalsIgnoreCase(strActionHuy)) {
                    // update bang ke
                    bke_vo.setTrang_thai(AppConstants.TRANG_THAI_BK_HUY);
                    bke_vo.setNgay_ks("SYSDATE");
                    result = qtDAO.updateBke(bke_vo);
                    if (result == 1) {
                        // update cac lenh qt co so bk = so bk bi huy
                        QuyetToanVO voQT_update = new QuyetToanVO();
                        String whereClauseRemove = " so_bk=? ";
                        String sobkRemove = bke_vo.getId();
                        voQT_update.setSo_bk("");
                        voQT_update.setTrang_thai("00");
                        result =
                                qtDAO.remove(voQT_update, whereClauseRemove, sobkRemove);
                    }
                } else if (strAction.equalsIgnoreCase(strActionDayLai)) {
                    // Day Lai
                    bke_vo.setTrang_thai(AppConstants.TRANG_THAI_BK_DAYLAI);
                    bke_vo.setNguoi_ks(strUserKTT);
                    bke_vo.setNgay_ks("SYSDATE");
                    result = qtDAO.updateBke(bke_vo);
                } else if (strAction.equalsIgnoreCase(strActionDuyet)) {

                    // thuc hien send message
//                    DOUBLE_20161122-BEGIN
//                    queueManager.setQueueManager();
//                    SendLQToan sMsgQT = new SendLQToan(conn, queueManager);
//                  DOUBLE_20161122-END
                    String strChuKy = bkeQtoanForm.getChu_ky();
                    /**
                     * verify
                     * */
                    boolean bVerifySignature = false;
                    String strWSDL = getThamSoHThong("WSDL_PKI", session);
                    PKIService pkiService = new PKIService(strWSDL);
                    String strUserName =
                        session.getAttribute(AppConstants.APP_DOMAIN_SESSION) +
                        "\\" +
                        session.getAttribute(AppConstants.APP_USER_CODE_SESSION);
                    String strSign = bkeQtoanForm.getChu_ky();
                    String strContent = bkeQtoanForm.getNoi_dung_ky();
                    String strCertSerial = bkeQtoanForm.getCertSerial();
                    String strAppID = getThamSoHThong("APP_ID", session);
                    String[] arrResultKy =
                        pkiService.VerifySignature(strUserName, strCertSerial,
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
                        bke_vo.setChu_ky(strChuKy);

                        String strHachToanTheoNgayKSNH = "Y";
                        if (session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION) !=
                            null) {
                            strHachToanTheoNgayKSNH =
                                    session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION).toString();
                        }
                        BuildMsgLQT sMsgQT = new BuildMsgLQT(conn);
                        msgID =
                                sMsgQT.sendMessage(bke_vo.getId(), "BK", session.getAttribute(AppConstants.APP_KB_CODE_SESSION) ==
                                                                         null ?
                                                                         "" :
                                                                         session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(),
                                                   strHachToanTheoNgayKSNH);
                        if (msgID != null && !"".equals(msgID)) {
                            bke_vo.setMsg_id(msgID);
                            // Duyet
                            bke_vo.setTrang_thai(AppConstants.TRANG_THAI_BK_DADUYET);
                            bke_vo.setNgay_ks("SYSDATE");
                            bke_vo.setNguoi_ks(strUserKTT);
                            result = qtDAO.updateBke(bke_vo);
                        } else {
                            throw TTSPException.createException("TTSP-9999",
                                                                "Kh&#244;ng th&#7875; update th&#244;ng tin do kh&#244;ng th&#7875; g&#7917;i message");
                        }
                    }
                }
                if (result >= 1) {
                    conn.commit();
//                  DOUBLE_20161122-BEGIN
//                    if (strAction.equalsIgnoreCase(strActionDuyet)) {
//                        try {
//                            queueManager.commitMQ();
//                        } catch (Exception ex) {
//                            //ROLLBACK DB WHEN COMMIT MQ FALSE
//                            bke_vo.setMsg_id(null);
//                            bke_vo.setTrang_thai(AppConstants.TRANG_THAI_BK_CHODUYET);
//                            bke_vo.setNgay_ks(null);
//                            bke_vo.setNguoi_ks(null);
//                            result = qtDAO.updateBke(bke_vo);
//                            conn.commit();
//                            throw ex;
//                        }
//                    }
//                  DOUBLE_20161122-END
                } 
//              DOUBLE_20161122-BEGIN
//                else {
//                    queueManager.rollbackMQ();
//                }
//              DOUBLE_20161122-END
                request.setAttribute("updateStatus", strAction);
                request.setAttribute("updateIdBK", bke_vo.getId());
            }
        } catch (Exception e) {
            //ROLLBACK MQ
//            DOUBLE_20161122-BEGIN
//            if (queueManager != null) {
//                queueManager.rollbackMQ();
//            }
//          DOUBLE_20161122-END
            throw e;
        } finally {
            close(conn);
//          DOUBLE_20161122-BEGIN
//            if (queueManager != null) {
//                queueManager.disconnectMQ();
//            }
//          DOUBLE_20161122-END
            executeAction(mapping, form, request, response);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "QTOAN.TQUOC.XLyBKE")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        String reportName = null;
        InputStream reportStream = null;
        BKE_QuyetToanForm bkForm = null;
        JasperPrint jasperPrint = null;
        DmTSoHToanDAO tsoDAO = null;
        DmTSoHToanVO tsoVO = null;
        HashMap parameterMap = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            conn = getConnection();
            bkForm = (BKE_QuyetToanForm)form;
            QuyetToanDAO qtDAO = new QuyetToanDAO(conn);
            tsoDAO = new DmTSoHToanDAO(conn);
            Vector vParam = new Vector();
            sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                             bkForm.getId() + "\" id=\"id\"></input>");
            Parameter params = new Parameter(bkForm.getId(), true);
            vParam.add(params);
            BKE_QuyetToanVO bkVO = qtDAO.findBKeByPK(vParam);
            if (bkVO != null) {
                if (bkVO.getTcg_loai_qtoan().equalsIgnoreCase("C")) {
                    reportName = "rpt_Bke_LTT_NO";
                } else {
                    reportName = "rpt_Bke_LTT_Co";
                }
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    AppConstants.REPORT_DIRECTORY +
                    AppConstants.FONT_FOR_REPORT;
                parameterMap = new HashMap();

                String p_MA_KB = AppConstants.KBNN_SGD_BANK_CODE;

                DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
                String whereClauseHO = " a.ma_nh=?";
                Vector paramHO = new Vector();
                paramHO.add(new Parameter(p_MA_KB, true));
                DMNHangVO nhVO = dmnhDAO.getDMNH(whereClauseHO, paramHO);
                String strMa_KB = nhVO.getMa_nh();
                String strTen_KB = nhVO.getTen();

                String strMaNH = bkVO.getTcg_ngan_hang();
                whereClauseHO = " and a.ma_dv=?";
                paramHO = new Vector();
                paramHO.add(new Parameter(strMaNH, true));
                DMNHangHOVO nhHOVO =
                    dmnhDAO.getDMNHangBKLTT(whereClauseHO, paramHO);
                String p_MA_NH = "";
                String p_TEN_NH = "";
                if (nhHOVO != null) {
                    p_MA_NH = nhHOVO.getMa_nh();
                    p_TEN_NH = nhHOVO.getTen_nh();
                }
                parameterMap.put("p_NGAY", bkVO.getNgay_htoan());
                parameterMap.put("p_MA_KB", strMa_KB);
                parameterMap.put("p_TEN_KB", strTen_KB);
                parameterMap.put("p_MA_NH", p_MA_NH);
                parameterMap.put("p_TEN_NH", p_TEN_NH);
                parameterMap.put("P_LOAI_TIEN", bkVO.getTcg_loai_tien());
                parameterMap.put("P_LOAI_TK", bkVO.getTcg_loai_tk());
                /*
               * Xac dinh loai hoach toan de lay tham so tk co
               * Loai Qtoan (D: No; C: Co)
               * */
                String strLoaiHToan = bkVO.getLoai_htoan();
                String strLoaiQtoan = bkVO.getTcg_loai_qtoan();
//                String strLoaiTK = bkVO.getTcg_loai_tk();
                String strKHLoaiHachToanNo = "";
                String strKHLoaiHachToanCo = "";
                if ("T".equalsIgnoreCase(strLoaiHToan)) {
                    if ("D".equalsIgnoreCase(strLoaiQtoan)) {
                      if(bkVO.getTcg_loai_tien().equals("VND")){
                        strKHLoaiHachToanNo = "HACH_TOAN_DUNG_QTOAN_TQUOC_NO";
                        strKHLoaiHachToanCo = "HACH_TOAN_DUNG_QTOAN_TQUOC_CO";
                      }else{
                        strKHLoaiHachToanNo = "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_TQUOC_CO";
                        strKHLoaiHachToanCo = "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_TQUOC_NO";
                      }
                    } else {
                      if(bkVO.getTcg_loai_tien().equals("VND")){
                        strKHLoaiHachToanNo = "HACH_TOAN_DUNG_QTOAN_TQUOC_CO";
                        strKHLoaiHachToanCo = "HACH_TOAN_DUNG_QTOAN_TQUOC_NO";
                      }else{
                        strKHLoaiHachToanNo = "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_TQUOC_NO";//da sua mot lan
                        strKHLoaiHachToanCo = "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_TQUOC_CO";
                      }
                    }
                } else {
                    if ("D".equalsIgnoreCase(strLoaiQtoan)) {
                      if(bkVO.getTcg_loai_tien().equals("VND")){
                        strKHLoaiHachToanNo = "HACH_TOAN_CXLY_QTOAN_TQUOC_NO";
                        strKHLoaiHachToanCo = "HACH_TOAN_CXLY_QTOAN_TQUOC_CO";
                      }else{
                        strKHLoaiHachToanNo = "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_TQUOC_CO";
                        strKHLoaiHachToanCo = "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_TQUOC_NO";
                      }
                    } else {
                      if(bkVO.getTcg_loai_tien().equals("VND")){
                        strKHLoaiHachToanNo = "HACH_TOAN_CXLY_QTOAN_TQUOC_CO";
                        strKHLoaiHachToanCo = "HACH_TOAN_CXLY_QTOAN_TQUOC_NO";
                      }else{
                        strKHLoaiHachToanNo = "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_TQUOC_NO";
                        strKHLoaiHachToanCo = "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_TQUOC_CO";
                      }
                    }
                }
                
                ////////////////////////////////////////////////////////////////////////////////
                String strWhereClauseTSo =
                    " and LOAI_HTOAN = ? AND ma_nh = ? ";
                Vector paramsForTSo = new Vector();
                paramsForTSo.add(new Parameter(strKHLoaiHachToanCo, true));
                paramsForTSo.add(new Parameter(strMaNH, true));
                tsoVO = tsoDAO.getDmTSoHToan(strWhereClauseTSo, paramsForTSo);
                parameterMap.put("p_TK_NO",
                                 tsoVO != null ? tsoVO.getTktn() : "");


                paramsForTSo = new Vector();
                paramsForTSo.add(new Parameter(strKHLoaiHachToanNo, true));
                paramsForTSo.add(new Parameter(strMaNH, true));
                tsoVO = tsoDAO.getDmTSoHToan(strWhereClauseTSo, paramsForTSo);
                parameterMap.put("p_TK_CO",
                                 tsoVO != null ? tsoVO.getTktn() : "");
                ////////////////////////////////////////////////////////////////////////////////
                //                  if (qtVO.getLoai_qtoan() != null &&
                //                      !STRING_EMPTY.equals(qtVO.getLoai_qtoan())) {
                //                      parameterMap.put("TYPE", qtVO.getLoai_qtoan().toUpperCase());
                //                  }
                //                  if (qtVO.getTt_in() == null || 0 == qtVO.getTt_in()) {
                //                      qtVO.setTt_in(new Long("1"));
                //                      parameterMap.put("TT_IN", 1);
                //                      if (qtDAO.update(qtVO) == 1) {
                //                          conn.commit();
                //                      }
                //                  } else {
                //                      parameterMap.put("TT_IN", 0);
                //                  }

                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                                "/" +
                                                                                                reportName +
                                                                                                ".jasper");
                parameterMap.put("p_ID_BKE", bkForm.getId());
                if(bkForm.getTcg_loai_tien().equals("VND")){
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                }else{
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
                }
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);
                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "quyettoanSGDRptAction.do";
                String strParameter = "";

                ReportUtility rpUtilites = new ReportUtility();
                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, reportName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
            }
        } catch (Exception e) {
            e.printStackTrace();

            throw (new TTSPException()).createException("TTSP-9999",
                                                        "In b&#7843;ng k&#234; quy&#7871;t to&#225;n : : " +
                                                        e);

        } finally {
            if (reportStream != null) {
                reportStream.close();
            }
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}

