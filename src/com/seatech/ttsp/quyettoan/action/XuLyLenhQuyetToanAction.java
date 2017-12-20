package com.seatech.ttsp.quyettoan.action;


//import com.seatech.ttsp.proxy.giaodien.SendLQToan;
//import com.seatech.ttsp.proxy.giaodien.mq.QueueManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanDAO;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanVO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.proxy.giaodien.BuildMsgLQT;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.quyettoan.QuyetToanDAO;
import com.seatech.ttsp.quyettoan.QuyetToanVO;
import com.seatech.ttsp.quyettoan.UpdateQuyetToanVO;
import com.seatech.ttsp.quyettoan.XuLyLenhQuyetToanThuCongVO;
import com.seatech.ttsp.quyettoan.form.QuyetToanForm;

import com.seatech.ttsp.tknhkb.TKNHKBacDAO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
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


public class XuLyLenhQuyetToanAction extends AppAction {
    public XuLyLenhQuyetToanAction() {
        super();
    }
    //    private static final String TT_CHOTTV = "00";
    //    private static final String TT_CHOKTT = "01";
    //    private static final String TT_DAYLAI = "02";
    //    private static final String TT_DADUYET = "03";
    private static final String DAYLAI = "ACTION_RETURN";
    private static final String DUYET = "ACTION_PASS";
    private static final String CHUYEN_KS = "CHUYEN_KS";
    private static final String STRING_EMPTY = "";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        /**
       * - ManhNV
       * - 22/11/2016
       * - Sua truyen dien 2 lan
       * - key tim kiem: DOUBLE_20161122
       ***/
        Connection conn = null;
        List lstDanhSachQuyetToan = null;
        String strWhereClause = null;
        Vector vParams = null;
        QuyetToanDAO dao = null;
        String action = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "QTOAN.XULY")) {
                return mapping.findForward("errorQuyen");
            } else {
                conn = getConnection();
                QuyetToanForm qtForm = (QuyetToanForm)form;
                HttpSession session = request.getSession();
                if (session != null) {
                    // Kiem tra loai user
                    String strUserInfo =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                    String strNHKB_Nhan =
                        (String)session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION);
                    strWhereClause =
                            " d.rv_domain = 'SP_QT.TRANG_THAI' and a.ma_kb=? and a.qtoan_dvi = 'Y' ";
                    dao = new QuyetToanDAO(conn);
                    if (request.getParameter("action") != null &&
                        request.getParameter("action").equals(AppConstants.ACTION_VIEW_DETAIL)) {
                        strWhereClause =
                                " a.id=? and d.rv_domain = 'SP_QT.TRANG_THAI'";
                        vParams = new Vector();
                        vParams.add(new Parameter(qtForm.getId(), true));
                        request.setAttribute("typeView",
                                             request.getParameter("action"));
                    } else {
                        vParams = new Vector();
                        vParams.add(new Parameter(strNHKB_Nhan, true));
                        if (strUserInfo.indexOf(AppConstants.NSD_TTV) != -1) {
                            strWhereClause +=
                                    " and (a.trang_thai in (?,?,?,?) or TRUNC(sysdate) <= a.ngay_ks)  ";
                            vParams.add(new Parameter(AppConstants.TRANG_THAI_QT_CHOHOANTHIEN,
                                                      true));
                            vParams.add(new Parameter(AppConstants.TRANG_THAI_QT_CHODUYET,
                                                      true));
                            vParams.add(new Parameter(AppConstants.TRANG_THAI_QT_BKE,
                                                      true));
                            vParams.add(new Parameter(AppConstants.TRANG_THAI_QT_DAYLAI,
                                                      true));
                            strWhereClause +=
                                    " ORDER BY d.rv_high_value,a.id ";

                        } else if (strUserInfo.indexOf(AppConstants.NSD_KTT) !=
                                   -1) {
                            strWhereClause +=
                                    " and (a.trang_thai in (?,?,?) or TRUNC(sysdate) <= a.ngay_ks)  and a.trang_thai  <> ?  ";
                            vParams.add(new Parameter(AppConstants.TRANG_THAI_QT_CHODUYET,
                                                      true));
                            vParams.add(new Parameter(AppConstants.TRANG_THAI_QT_BKE,
                                                      true));
                            vParams.add(new Parameter(AppConstants.TRANG_THAI_QT_DAYLAI,
                                                      true));
                            vParams.add(new Parameter(AppConstants.TRANG_THAI_QT_CHOHOANTHIEN,
                                                      true));
                            strWhereClause += " ORDER BY d.rv_low_value,a.id ";
                        }
                    }
                    // check nhom quyen nguoi su dung
                    String idNSD = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
                    String kb_id = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
                    String strWhere = "and a.kb_id='"+kb_id+"' and a.id='"+idNSD+"'";
                    //end check nhom quyen nguoi su dung
                    lstDanhSachQuyetToan =
                            (List)dao.getQTList(strWhereClause, vParams);
                    request.setAttribute("lstDanhSachQuyetToan",
                                         lstDanhSachQuyetToan);
                    request.setAttribute("chucdanh", strUserInfo);
                }

                action = request.getParameter(AppConstants.REQUEST_ACTION);
                if ("REFRESH".equals(action)) {
                    Type listType = new TypeToken<Collection<QuyetToanVO>>() {
                    }.getType();
                    String strJson =
                        new Gson().toJson(lstDanhSachQuyetToan, listType);
                    response.setContentType(AppConstants.CONTENT_TYPE_JSON_UTF);
                    PrintWriter out = response.getWriter();
                    out.println(strJson.toString());
                    out.flush();
                    out.close();
                }
            }
        } catch (Exception ex) {
            throw new Exception("X&#7917; l&#253; l&#7879;nh quy&#7871;t to&#225;n: " +
                                ex.getMessage());
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
        //        QueueManager queueManager = null;//DOUBLE_20161122
        QuyetToanDAO dao = null;
        QuyetToanVO vo = null;
        JsonObject jsonObj = null;
        String strJson = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "QTOAN.XULY")) {
                return mapping.findForward("errorQuyen");
            } else {
                conn = getConnection();
                dao = new QuyetToanDAO(conn);
                QuyetToanForm f = (QuyetToanForm)form;
                HttpSession session = request.getSession();
              String userLogin = session.getAttribute(AppConstants.APP_USER_NAME_SESSION) == null ? "" : 
                  session.getAttribute(AppConstants.APP_USER_NAME_SESSION).toString();
                //ManhNV-20141119: Kiem tra ky va hoan thien cung 1 NSD********
                Vector vParam = new Vector();
                vParam.add(new Parameter(f.getId(), true));
                QuyetToanVO quyetToanVO = dao.findByPK(vParam);
                String strNSD =
                    session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
                String strTTV =
                    quyetToanVO.getTtv_chuyen_ks() == null ? "" : quyetToanVO.getTtv_chuyen_ks().toString();
                String strKTT =
                    quyetToanVO.getKtt_ks() == null ? "" : quyetToanVO.getKtt_ks().toString();
                //*************************************************************
                vo = new QuyetToanVO();
                jsonObj = new JsonObject();

                //manhnv-12/05
                //                queueManager = new QueueManager(getThamSoHThong(session));//DOUBLE_20161122
                //manhnv-12/05
                if (f.getSo_tien() == null) {
                    f.setSo_tien("0");
                }
                BeanUtils.copyProperties(vo, f);
                //
                Long lUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                String action =
                    request.getParameter(AppConstants.REQUEST_ACTION);
                if (action.equalsIgnoreCase(CHUYEN_KS)) {
                    if (strNSD.equals(strKTT)) {
                        throw new Exception("Kh&#244;ng &#273;&#432;&#7907;c ph&#233;p v&#7915;a ho&#224;n thi&#7879;n v&#7915;a k&#253; duy&#7879;t tr&#234;n m&#7897;t ch&#7913;ng t&#7915;");
                    }
                    vo.setTtv_chuyen_ks(lUserID);
                    vo.setNgay_chuyen_ks(StringUtil.getCurrentDate());
                    vo.setTrang_thai(AppConstants.TRANG_THAI_QT_CHODUYET);
                    if (dao.update(vo) > 0) {
                    request.setAttribute("trang_thai_cho_duyet", "01");
                        conn.commit();
                        jsonObj.addProperty("Success", true);
                        jsonObj.addProperty("id", vo.getId());
                    } else {
                        jsonObj.addProperty("Success", false);
                        jsonObj.addProperty("id", vo.getId());
                    }
                } else {
                    if (strNSD.equals(strTTV)) {
                        throw new Exception("Kh&#244;ng &#273;&#432;&#7907;c ph&#233;p v&#7915;a ho&#224;n thi&#7879;n v&#7915;a k&#253; duy&#7879;t tr&#234;n m&#7897;t ch&#7913;ng t&#7915;");
                    }
                    if (action.equalsIgnoreCase(DAYLAI)) {
                        vo.setKtt_ks(lUserID);
                        //                        vo.setNgay_ks_nh(StringUtil.getCurrentDate());
                        vo.setNgay_ks(StringUtil.getCurrentDate());
                        vo.setTrang_thai(AppConstants.TRANG_THAI_QT_DAYLAI);
                        jsonObj.addProperty("typeAction", "daylai");
                        vo.setLoai_hach_toan("T");
                        if (dao.update(vo) > 0) {
                            conn.commit();
                            jsonObj.addProperty("Success", true);
                            jsonObj.addProperty("id", vo.getId());
                        } else {
                            jsonObj.addProperty("Success", false);
                            jsonObj.addProperty("id", vo.getId());
                        }
                    }
                    if (action.equalsIgnoreCase(DUYET)) {
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
                        String strSign = f.getChu_ky();
                        String strContent = f.getNoi_dung_ky();
                        String strCertSerial = f.getCertSerial();
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
                                jsonObj.addProperty("Failure", arrResultKy[1]);
                            } else if (arrResultKy[0].equalsIgnoreCase("ERROR")) {
                                jsonObj.addProperty("Failure", arrResultKy[1]);
                            }
                        }
                        if (bVerifySignature) {
                            vo.setChu_ky(strSign);
                            vo.setKtt_ks(lUserID);
                            //                            vo.setNgay_ks_nh(StringUtil.getCurrentDate());
                            vo.setNgay_ks(StringUtil.getCurrentDate());
                            vo.setTrang_thai(AppConstants.TRANG_THAI_QT_DADUYET);
                            jsonObj.addProperty("typeAction", "duyet");

                            String msgID = null;
                            try {
                                // thuc hien send message
                                //ManhNV - 12/05
                                //                                DOUBLE_20161122-BEGIN
                                //                                queueManager.setQueueManager();
                                //                                SendLQToan sMsgQT =
                                //                                    new SendLQToan(conn, queueManager);
                                //                              DOUBLE_20161122-END
                                //ManhNV - 12/05

                                if (DUYET.equalsIgnoreCase(action)) {

                                    String strHachToanTheoNgayKSNH = "Y";
                                    if (session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION) !=
                                        null) {
                                        strHachToanTheoNgayKSNH =
                                                session.getAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION).toString();
                                    }

                                    BuildMsgLQT sMsgQT =
                                        new BuildMsgLQT(conn); //DOUBLE_20161122
                                    msgID =
                                            sMsgQT.sendMessage(vo.getId(), "CT",
                                                               session.getAttribute(AppConstants.APP_KB_CODE_SESSION) ==
                                                               null ? "" :
                                                               session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString(),
                                                               strHachToanTheoNgayKSNH);
                                    if (msgID != null && !"".equals(msgID)) {
                                        vo.setMsg_id(msgID);
                                        vo.setNguoi_ks_nh(userLogin);
                                        if (dao.update(vo) > 0) {
                                            // insert 1 ban ghi vao sp_quyet_toan
                                        String idQT = f.getId();
                                            XuLyLenhQuyetToanThuCongVO lenhQTTCVO =
                                                dao.getThongTinQuyetToanById(idQT,
                                                                             null);
                                            if (lenhQTTCVO.getNhap_thu_cong().equals("Y")) {
                                                lenhQTTCVO.setQtoan_dvi("N");
                                                lenhQTTCVO.setTrang_thai("03");
                                                TTSPUtils utils =
                                                    new TTSPUtils(conn);
                                                String id = "";
                                                if (lenhQTTCVO.getLoai_qtoan().equals("D")) {
                                                    id = utils.getSoLTT("900");
                                                    lenhQTTCVO.setId(id);
                                                } else {
                                                    id = utils.getSoLTT("910");
                                                    lenhQTTCVO.setId(id);
                                                }
                                            }
                                            Vector vParams = new Vector();
                                            int sqlResult =
                                                dao.insertLenhQuyetToan(lenhQTTCVO,
                                                                        vParams);
                                            if (sqlResult > 0)
                                                conn.commit();
                                            else
                                                throw new Exception("Không insert vào được bảng kê quyết toán");
                                            jsonObj.addProperty("Success",
                                                                true);
                                            jsonObj.addProperty("id",
                                                                vo.getId());
                                        } else {
                                            //20160317-BEGIN-Rollback MQ khi ko update dc CSDL
                                            //                                            DOUBLE_20161122-BEGIN
                                            //                                            if (queueManager != null) {
                                            //                                                queueManager.rollbackMQ();
                                            //                                            }
                                            //                                          DOUBLE_20161122-END
                                            //20160317-END-Rollback MQ khi ko update dc CSDL
                                            jsonObj.addProperty("Success",
                                                                false);
                                            jsonObj.addProperty("id",
                                                                vo.getId());
                                        }
                                    }
                                }
                            } catch (Exception ex) {
                                //                              DOUBLE_20161122-BEGIN
                                //                                if (queueManager != null) {
                                //                                    queueManager.rollbackMQ();
                                //                                }
                                //                              DOUBLE_20161122-END
                                jsonObj.addProperty("Failure",
                                                    "SendLQToan.sendBKeMessage() " +
                                                    ex.getMessage());
                            } finally {
                                //Close MQ
                                //                                DOUBLE_20161122-BEGIN
                                //                                if (queueManager != null) {
                                //                                    queueManager.disconnectMQ();
                                //                                }
                                //                              DOUBLE_20161122-END
                            }
                        }
                        jsonObj.addProperty("typeAction", "duyet");
                    }
                }
            }
        } catch (Exception e) {
            // TODO: Add catch code
            jsonObj.addProperty("Failure", e.getMessage());
            strJson = jsonObj.toString();
            throw new Exception("X&#7917; l&#253; l&#7879;nh quy&#7871;t to&#225;n: " +
                                e.getMessage());
        } finally {
            //Close DB
            close(conn);

            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        QuyetToanVO voQT = null;
        Vector params = null;
        List lstDanhSachQuyetToan = null;
        String strWhereClause = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            //            if (!checkPermissionOnFunction(request, "QTOAN.XULY")) {
            //                return mapping.findForward("errorQuyen");
            //            } else {
            HttpSession session = request.getSession();
            conn = getConnection();
            QuyetToanDAO dao = new QuyetToanDAO(conn);
            params = new Vector();
            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            params.add(new Parameter(request.getParameter("id"), true));
            voQT = dao.findByPK(params);
            String strPhanQuyen =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            voQT.setChuc_danh(strPhanQuyen);
            BeanUtils.copyProperties(form, voQT);
            // lay ra ban ghi
            strWhereClause = " d.rv_domain='SP_QT.TRANG_THAI' and a.id=? ";
            lstDanhSachQuyetToan = (List)dao.getQTList(strWhereClause, params);
            request.setAttribute("lstDanhSachQuyetToan", lstDanhSachQuyetToan);
            request.setAttribute("chucdanh", strUserInfo);
            request.setAttribute("typeView", request.getParameter("typeView"));
            session.setAttribute("PrintSGD",
                                 request.getParameter("typeView") != null ?
                                 request.getParameter("typeView") :
                                 STRING_EMPTY);
            request.setAttribute("rowSelected",
                                 request.getParameter("rowSelected"));

            //            }
        } catch (Exception e) {
            // TODO: Add catch code
            throw new Exception("Chi ti&#7871;t l&#7879;nh quy&#7871;t to&#225;n : " +
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
        QuyetToanVO voQT = null;
        Vector params = null;
        String strJson = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            //            if (!checkPermissionOnFunction(request, "QTOAN.XULY")) {
            //                return mapping.findForward("errorQuyen");
            //            } else {
            HttpSession session = request.getSession();
            conn = getConnection();
            QuyetToanDAO dao = new QuyetToanDAO(conn);
            params = new Vector();
            params.add(new Parameter(request.getParameter("id"), true));
            voQT = dao.findByPK(params);
            String strPhanQuyen =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            voQT.setChuc_danh(strPhanQuyen);
            if (strPhanQuyen.contains(AppConstants.NSD_KTT)) {
                TTSPUtils spUtil = new TTSPUtils(conn);
                String strNoiDungKy =
                    spUtil.getNoiDungKy(request.getParameter("id"), "900",
                                        "N");
                voQT.setNoi_dung_ky(strNoiDungKy);
            }
            if ("04".equals(voQT.getLai_phi()) &&
                !"".equals(voQT.getMt_refid())) {
                LTTDAO lttDAO = new LTTDAO(conn);
                LTTVO lttVO =
                    lttDAO.getLTTDi("t.id = " + voQT.getMt_refid(), null);
                String strTtinLenhGoc = "";
                if (lttVO != null) {
                    //Set thong tin lenh goc
                    if ("03".equals(lttVO.getTtloai_lenh())) {
                        strTtinLenhGoc +=
                                "S&#7889; YCTT: " + lttVO.getSo_yctt() +
                                "; Lo&#7841;i l&#7879;nh:  Mua ngo&#7841;i t&#7879;; S&#7889; ti&#7873;n mua:  " +
                                lttVO.getSo_tien_mua_ban() +
                                "; M&#227; NT mua: " +
                                lttVO.getMa_nt_mua_ban() +
                                "; S&#7889; ti&#7873;n b&#225;n d&#7921; t&#237;nh:  " +
                                lttVO.getTong_sotien() +
                                "; M&#227; NT b&#225;n:" + lttVO.getNt_code();
                    }
                    voQT.setTtin_lenh_goc(strTtinLenhGoc);
                }
            }
            Type listType = new TypeToken<QuyetToanVO>() {
            }.getType();
            strJson = new Gson().toJson(voQT, listType);
            //            }
        } catch (Exception e) {
            throw new Exception("X&#7917; l&#253; l&#7879;nh quy&#7871;t to&#225;n : " +
                                e.getMessage());
        } finally {
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
            close(conn);
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
        //        if (!checkPermissionOnFunction(request, "QTOAN.XULY")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        String reportName = null;
        InputStream reportStream = null;
        QuyetToanForm qtForm = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            conn = getConnection();
            qtForm = (QuyetToanForm)form;
            QuyetToanDAO qtDAO = new QuyetToanDAO(conn);
            Vector vParam = new Vector();
            sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                             qtForm.getId() + "\" id=\"id\"></input>");
            Parameter params = new Parameter(qtForm.getId(), true);
            vParam.add(params);
            QuyetToanVO qtVO = qtDAO.findByPK(vParam);
            if (qtVO == null) {
                throw new Exception("Kh&#244;ng t&#236;m &#273;&#432;&#7907;c ch&#7913;ng t&#7915;!");
            }
            if (qtVO.getLai_phi().equalsIgnoreCase("02")) {
                reportName = "LenhQuyetToanLaiPhi";
            } else if (qtVO.getLai_phi().equalsIgnoreCase("04")) {
                if (qtVO.getLoai_qtoan().equals("C"))
                    reportName = "LenhQuyetToanLaiPhi_ChechLenhNT_BaoCo";
                if (qtVO.getLoai_qtoan().equals("D"))
                    reportName = "LenhQuyetToanLaiPhi_ChechLenhNT";
            } else if (qtVO.getLai_phi().equalsIgnoreCase("05")) {
                reportName = "LenhBaoNoCoPOS";
            } else if (qtVO.getLai_phi().equalsIgnoreCase("06")) {
                reportName = "LenhBaoNoCoPOS";
            } else {
                reportName = "LenhQuyetToan";
            }
            //            }
            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
            parameterMap = new HashMap();
            if (qtVO.getLoai_qtoan() != null &&
                !STRING_EMPTY.equals(qtVO.getLoai_qtoan())) {
                parameterMap.put("TYPE", qtVO.getLoai_qtoan().toUpperCase());
            }
            if (qtVO.getTt_in() == null || 0 == qtVO.getTt_in()) {
                QuyetToanVO vo_update = new QuyetToanVO();
                vo_update.setId(qtVO.getId());
                vo_update.setTt_in(new Long("1"));
                parameterMap.put("TT_IN", 1);
                if (qtDAO.update(vo_update) == 1) {
                    conn.commit();
                }
            } else {
                parameterMap.put("TT_IN", 0);
            }
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                            "/" +
                                                                                            reportName +
                                                                                            ".jasper");
            //Lay NO_TK/CO_TK
            String strMaKB = qtVO.getMa_kb().trim();
            String strMaNH = qtVO.getNhkb_chuyen().substring(2, 5);
            String strLoaiHToan =
                qtVO.getLoai_hach_toan() == null ? "" : qtVO.getLoai_hach_toan().trim();
            String strLoaiQToan =
                qtVO.getLoai_qtoan() == null ? "" : qtVO.getLoai_qtoan();
            String strKHLoaiHachToanNo = "";
            String strKHLoaiHachToanCo = "";
            if ("02".equals(qtVO.getLai_phi()) ||
                "06".equals(qtVO.getLai_phi())) {
                if (strLoaiQToan.equalsIgnoreCase("D")) {
                    if (strLoaiHToan.equalsIgnoreCase("T")) {
                        strKHLoaiHachToanNo = "HACH_TOAN_DUNG_QTOAN_PHI_NO";
                        strKHLoaiHachToanCo = "HACH_TOAN_DUNG_QTOAN_PHI_CO";
                    } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                        strKHLoaiHachToanNo = "HACH_TOAN_CXLY_QTOAN_PHI_NO";
                        strKHLoaiHachToanCo = "HACH_TOAN_CXLY_QTOAN_PHI_CO";
                    } else if (strLoaiHToan.equalsIgnoreCase("1339")) {
                        strKHLoaiHachToanNo = "HACH_TOAN_1339_QTOAN_PHI_NO";
                        strKHLoaiHachToanCo = "HACH_TOAN_1339_QTOAN_PHI_CO";
                    } else if (strLoaiHToan.equalsIgnoreCase("3999")) {
                        strKHLoaiHachToanNo = "HACH_TOAN_3999_QTOAN_LAI_NO";
                        strKHLoaiHachToanCo = "HACH_TOAN_3999_QTOAN_LAI_CO";
                    }
                } else {
                    //Chuyen thu
                    if ("03".equals(qtVO.getLoai_tk())) {
                        if (strLoaiHToan.equalsIgnoreCase("T")) {
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_CHUYEN_THU_DUNG_QTOAN_LAI_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_CHUYEN_THU_DUNG_QTOAN_LAI_CO";
                        } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_CHUYEN_THU_CXLY_QTOAN_LAI_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_CHUYEN_THU_CXLY_QTOAN_LAI_CO";
                        } else if (strLoaiHToan.equalsIgnoreCase("3999")) {
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_CHUYEN_THU_3999_QTOAN_LAI_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_CHUYEN_THU_3999_QTOAN_LAI_CO";
                        }
                    } else {
                        if (strLoaiHToan.equalsIgnoreCase("T")) {
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_DUNG_QTOAN_LAI_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_DUNG_QTOAN_LAI_CO";
                        } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_CXLY_QTOAN_LAI_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_CXLY_QTOAN_LAI_CO";
                        } else if (strLoaiHToan.equalsIgnoreCase("3999")) {
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_3999_QTOAN_LAI_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_3999_QTOAN_LAI_CO";
                        }
                    }
                }
            } else if ("04".equals(qtVO.getLai_phi())) {
                if (strLoaiQToan.equalsIgnoreCase("D")) {
                    if (strLoaiHToan.equalsIgnoreCase("T")) {
                        strKHLoaiHachToanNo =
                                "NGOAI_TE_HACH_TOAN_DUNG_CLECH_TGIA_NO";
                        strKHLoaiHachToanCo =
                                "NGOAI_TE_HACH_TOAN_DUNG_CLECH_TGIA_CO";
                    } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                        strKHLoaiHachToanNo =
                                "NGOAI_TE_HACH_TOAN_CXLY_CLECH_TGIA_NO";
                        strKHLoaiHachToanCo =
                                "NGOAI_TE_HACH_TOAN_CXLY_CLECH_TGIA_CO";
                    }
                } else {
                    if (strLoaiHToan.equalsIgnoreCase("T")) {
                        strKHLoaiHachToanNo =
                                "NGOAI_TE_HACH_TOAN_DUNG_CLECH_TGIA_CO";
                        strKHLoaiHachToanCo =
                                "NGOAI_TE_HACH_TOAN_DUNG_CLECH_TGIA_NO";
                    } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                        strKHLoaiHachToanNo =
                                "NGOAI_TE_HACH_TOAN_CXLY_CLECH_TGIA_CO";
                        strKHLoaiHachToanCo =
                                "NGOAI_TE_HACH_TOAN_CXLY_CLECH_TGIA_NO";
                    }
                }
            } else if ("05".equals(qtVO.getLai_phi())) {
                //BEGIN 20151123: Dao lai cap hach toan
                if (strLoaiHToan.equalsIgnoreCase("T")) {
                    strKHLoaiHachToanCo = "HACH_TOAN_DUNG_THU_POS_NO";
                    strKHLoaiHachToanNo = "HACH_TOAN_DUNG_THU_POS_CO";
                } else {
                    strKHLoaiHachToanCo = "HACH_TOAN_CXLY_THU_POS_NO";
                    strKHLoaiHachToanNo = "HACH_TOAN_CXLY_THU_POS_CO";
                }
                //END 20151119
            } else {
                if (AppConstants.KBNN_SGD_BANK_CODE.equals(strMaKB)) {
                    if (strLoaiQToan.equalsIgnoreCase("D")) {
                        if (strLoaiHToan.equalsIgnoreCase("T")) {
                            if (qtVO.getMa_nt().equals("VND")) {
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_DUNG_QTOAN_SGD_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_DUNG_QTOAN_SGD_CO";
                            } else {
                                strKHLoaiHachToanNo =
                                        "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_SGD_NO";
                                strKHLoaiHachToanCo =
                                        "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_SGD_CO";
                            }
                        } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                            if (qtVO.getMa_nt().equals("VND")) {
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_CXLY_QTOAN_SGD_NO";
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_CXLY_QTOAN_SGD_CO";
                            } else {
                                strKHLoaiHachToanNo =
                                        "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_SGD_NO";
                                strKHLoaiHachToanCo =
                                        "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_SGD_CO";
                            }
                        }
                    } else {
                        if (strLoaiHToan.equalsIgnoreCase("T")) {
                            if (qtVO.getMa_nt().equals("VND")) {
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_DUNG_QTOAN_SGD_NO";
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_DUNG_QTOAN_SGD_CO";
                            } else {
                                strKHLoaiHachToanCo =
                                        "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_SGD_NO";
                                strKHLoaiHachToanNo =
                                        "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_SGD_CO";
                            }
                        } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                            if (qtVO.getMa_nt().equals("VND")) {
                                strKHLoaiHachToanCo =
                                        "HACH_TOAN_CXLY_QTOAN_SGD_NO";
                                strKHLoaiHachToanNo =
                                        "HACH_TOAN_CXLY_QTOAN_SGD_CO";
                            } else {
                                strKHLoaiHachToanCo =
                                        "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_SGD_CO";
                                strKHLoaiHachToanNo =
                                        "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_SGD_NO";
                            }
                        }
                    }
                } else {
                    //Chuyen thu
                    if ("03".equals(qtVO.getLoai_tk())) {
                        if (strLoaiHToan.equalsIgnoreCase("T")) {
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_CHUYEN_THU_QTOAN_DUNG_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_CHUYEN_THU_QTOAN_DUNG_CO";
                        } else {
                            strKHLoaiHachToanNo =
                                    "HACH_TOAN_CHUYEN_THU_QTOAN_CXL_NO";
                            strKHLoaiHachToanCo =
                                    "HACH_TOAN_CHUYEN_THU_QTOAN_CXL_CO";

                        }
                        //Thanh toan
                    } else {
                        if (strLoaiQToan.equalsIgnoreCase("D")) {
                            if (strLoaiHToan.equalsIgnoreCase("T")) {
                                if (qtVO.getMa_nt().equals("VND")) {
                                    strKHLoaiHachToanNo =
                                            "HACH_TOAN_DUNG_QTOAN_HUYEN_NO";
                                    strKHLoaiHachToanCo =
                                            "HACH_TOAN_DUNG_QTOAN_HUYEN_CO";
                                } else {
                                    strKHLoaiHachToanNo =
                                            "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_HUYEN_NO";
                                    strKHLoaiHachToanCo =
                                            "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_HUYEN_CO";
                                }
                            } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                                if (qtVO.getMa_nt().equals("VND")) {
                                    strKHLoaiHachToanNo =
                                            "HACH_TOAN_CXLY_QTOAN_HUYEN_NO";
                                    strKHLoaiHachToanCo =
                                            "HACH_TOAN_CXLY_QTOAN_HUYEN_CO";
                                } else {
                                    strKHLoaiHachToanNo =
                                            "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_HUYEN_NO";
                                    strKHLoaiHachToanCo =
                                            "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_HUYEN_CO";
                                }
                            }
                        } else {
                            if (strLoaiHToan.equalsIgnoreCase("T")) {
                                if (qtVO.getMa_nt().equals("VND")) {
                                    strKHLoaiHachToanCo =
                                            "HACH_TOAN_DUNG_QTOAN_HUYEN_NO";
                                    strKHLoaiHachToanNo =
                                            "HACH_TOAN_DUNG_QTOAN_HUYEN_CO";
                                } else {
                                    strKHLoaiHachToanCo =
                                            "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_HUYEN_NO";
                                    strKHLoaiHachToanNo =
                                            "NGOAI_TE_HACH_TOAN_DUNG_QTOAN_HUYEN_CO";
                                }
                            } else if (strLoaiHToan.equalsIgnoreCase("P")) {
                                if (qtVO.getMa_nt().equals("VND")) {
                                    strKHLoaiHachToanCo =
                                            "HACH_TOAN_CXLY_QTOAN_HUYEN_NO";
                                    strKHLoaiHachToanNo =
                                            "HACH_TOAN_CXLY_QTOAN_HUYEN_CO";
                                } else {
                                    strKHLoaiHachToanCo =
                                            "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_HUYEN_NO";
                                    strKHLoaiHachToanNo =
                                            "NGOAI_TE_HACH_TOAN_CXLY_QTOAN_HUYEN_CO";
                                }
                            }
                        }

                    }
                }
            }
            if (!"".equals(strKHLoaiHachToanNo) &&
                !"".equals(strKHLoaiHachToanCo)) {
                DmTSoHToanVO tsoVO = null;
                DmTSoHToanDAO tsoDAO = new DmTSoHToanDAO(conn);
                String strWhereClauseTSo =
                    " and LOAI_HTOAN = ? AND ma_nh = ? ";
                Vector paramsForTSo = new Vector();
                paramsForTSo.add(new Parameter(strKHLoaiHachToanCo, true));
                paramsForTSo.add(new Parameter(strMaNH, true));
                tsoVO = tsoDAO.getDmTSoHToan(strWhereClauseTSo, paramsForTSo);

                //Sua tam thoi
                //                if (reportName.equalsIgnoreCase("LenhQuyetToanLaiPhi")) {
                //                    parameterMap.put("P_COTK", "");
                //                } else {
                //                    parameterMap.put("P_COTK", tsoVO != null ? tsoVO.getTktn() : "");
                //                }
                //------
                parameterMap.put("P_COTK",
                                 tsoVO != null ? tsoVO.getTktn() : "");

                strWhereClauseTSo = " and LOAI_HTOAN = ? AND ma_nh = ? ";
                paramsForTSo = new Vector();
                paramsForTSo.add(new Parameter(strKHLoaiHachToanNo, true));
                paramsForTSo.add(new Parameter(strMaNH, true));
                tsoVO = tsoDAO.getDmTSoHToan(strWhereClauseTSo, paramsForTSo);

                //Sua tam thoi
                //                if (reportName.equalsIgnoreCase("LenhQuyetToanLaiPhi")) {
                //                    parameterMap.put("P_NOTK", "");
                //                } else {
                //                    parameterMap.put("P_NOTK",
                //                                     tsoVO != null ? tsoVO.getTktn() : "");
                //                }
                //----
                parameterMap.put("P_NOTK",
                                 tsoVO != null ? tsoVO.getTktn() : "");
            }
            //
            parameterMap.put("ID", qtVO.getId());
            parameterMap.put("REPORT_LOCALE",
                             new java.util.Locale("vi", "VI"));
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 conn);
            String strTypePrintAction =
                request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                "" :
                request.getParameter(AppConstants.REQUEST_ACTION).toString();
            String strActionName = "quyettoanRptAction.do";
            String strParameter = "";

            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    reportName, strPathFont, strActionName,
                                    sbSubHTML.toString(), strParameter);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("In l&#7879;nh quy&#7871;t to&#225;n : " +
                                e.getMessage());
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

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            QuyetToanDAO dao = new QuyetToanDAO(conn);
            TKNHKBacDAO tkbhkbDAO = new TKNHKBacDAO(conn);
            QuyetToanForm f = (QuyetToanForm)form;
            UpdateQuyetToanVO vo =
                dao.getThongTinQuyetToan(f.getId_ref(), null);
            HttpSession session = request.getSession();

            String nhkbNhan_id =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) ==
                null ? "" :
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String nhkbNhan_name =
                session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION) ==
                null ? "" :
                session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION).toString();
            String NHKBChuyen =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION) == null ?
                "" :
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

            String strQuery = "and a.kb_id = " + NHKBChuyen;
            Collection dmNH = tkbhkbDAO.getNH_KB(strQuery, null);
            request.setAttribute("dmNH", dmNH);
            request.setAttribute("quyetToanObject", vo);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }
}
