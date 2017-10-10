package com.seatech.ttsp.dchieu.action;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dchieu.DChieu4CTietDAO;
import com.seatech.ttsp.dchieu.DChieu4DAO;
import com.seatech.ttsp.dchieu.DChieu4VO;
import com.seatech.ttsp.dchieu.KQDChieu4CTietDAO;
import com.seatech.ttsp.dchieu.KQDChieu4CTietVO;
import com.seatech.ttsp.dchieu.KQDChieu4DAO;
import com.seatech.ttsp.dchieu.KQDChieu4VO;
import com.seatech.ttsp.dchieu.form.DChieu4Form;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DChieu4Action extends AppAction {


    public DChieu4Action() {
        super();
    }


    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        // if (!checkPermissionOnFunction(request, "DCHIEU.DC4")) {
        //   return mapping.findForward("errorQuyen");
        // }
        Connection conn = null;
        Collection lisBangKe = null;
        try {
            conn = getConnection(request);
            //          HttpSession session = request.getSession();
            DChieu4DAO dchieu4DAO = new DChieu4DAO(conn);

            DChieu4VO vo = new DChieu4VO();
            vo = dchieu4DAO.getMaSGD(null, null);
            DChieu4Form f = (DChieu4Form)form;
            String idxNH =
                request.getParameter("idxNH") == null ? "" : request.getParameter("idxNH");

            if (idxNH != null && !"".equals(idxNH)) {
                request.setAttribute("idxNH", idxNH);
            }

            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            List dmucNH = null;
            dmucNH = (List)TTdao.getDMucNH(null, null);
            request.setAttribute("dmucNH", dmucNH);

            String strWhere =
                " AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE)" + " AND ( (c.ket_qua = '01' OR a.trang_thai = '00')" +
                " AND a.mt_id IN (  SELECT   MAX (mt_id) FROM   sp_bk_dc3_ngoai_te" +
                " WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE) GROUP BY   ngay_dc, send_bank))" +
                " OR a.trang_thai IS NULL) OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE)" +
                " AND a.mt_id IN (  SELECT   MAX (mt_id)" +
                " FROM   sp_bk_dc3_ngoai_te WHERE   TO_DATE (ngay_dc) = TO_DATE (SYSDATE) " +
                " GROUP BY   ngay_dc, send_bank))" +
                " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.trang_thai <> '00')" +
                " OR (TRUNC (a.ngay_thien_dc) = TRUNC (SYSDATE)))";
            String send_bank = f.getNhkb_nhan();
            if (send_bank != null && "" != send_bank &&
                !"000".equals(send_bank)) {
                strWhere += " and a.send_bank='" + send_bank + "'";
            }
            Vector vParam = null;
            lisBangKe = dchieu4DAO.getDChieu4List(strWhere, vParam);
            request.setAttribute("colBangKe", lisBangKe);
            request.setAttribute("colMT900", null);
            request.setAttribute("colMT910", null);
            request.setAttribute("TongKQBK", null);
            saveToken(request);
        } catch (TTSPException e) {
            throw e;
        } finally {
            conn.close();
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        boolean check = false;
        try {
            conn = getConnection(request);
            JsonObject jsonObj = new JsonObject();
            //xem chi tiet
            Collection colDChieuDetail = new ArrayList();
            Collection colDChieuDetail_ok = new ArrayList();
            Collection colMT900 = null;
            Collection colMT910 = null;

            DChieu4DAO dchieu4DAO = new DChieu4DAO(conn);
            String strWhere = "AND a.id=?";
            Vector vParam = new Vector();
            vParam.add(new Parameter(request.getParameter("id"), true));
            colDChieuDetail = dchieu4DAO.getDChieu4List(strWhere, vParam);

            String strWhereClause =
                " AND a.bk_id = '" + request.getParameter("id") +
                "' AND a.trang_thai <> '04'";
            KQDChieu4DAO kQDChieu4DAO = new KQDChieu4DAO(conn);
            KQDChieu4VO kQDChieu4VO =
                kQDChieu4DAO.getKQDChieu4(strWhereClause);

            if (kQDChieu4VO != null) {
                vParam = new Vector();
                vParam.add(new Parameter(kQDChieu4VO.getId(), true));
                vParam.add(new Parameter(kQDChieu4VO.getId(), true));
                vParam.add(new Parameter(kQDChieu4VO.getId(), true));
                vParam.add(new Parameter(kQDChieu4VO.getId(), true));
                KQDChieu4CTietDAO kQDChieu4CTietDAO =
                    new KQDChieu4CTietDAO(conn);
                Collection colTongKQDChieu =
                    kQDChieu4CTietDAO.getTongKQDChieu4(vParam);
                if (colTongKQDChieu != null) {
                    Iterator iter = colTongKQDChieu.iterator();
                    KQDChieu4CTietVO kQDChieu4CTietVO = null;
                    while (iter.hasNext()) {
                        kQDChieu4CTietVO = (KQDChieu4CTietVO)iter.next();
                        DChieu4VO dChieu4VO = null;
                        Iterator iter2 = colDChieuDetail.iterator();
                        while (iter2.hasNext()) {
                            dChieu4VO = (DChieu4VO)iter2.next();
                            dChieu4VO.setMt900_thieu(kQDChieu4CTietVO.getMt900thieu());
                            dChieu4VO.setMt900_thua(kQDChieu4CTietVO.getMt900thua());
                            dChieu4VO.setMt910_thieu(kQDChieu4CTietVO.getMt910thieu());
                            dChieu4VO.setMt910_thua(kQDChieu4CTietVO.getMt910thua());
                            check = true;
                        }
                        colDChieuDetail_ok.add(dChieu4VO);
                    }
                }

                KQDChieu4CTietDAO dchieu4CtietDAO =
                    new KQDChieu4CTietDAO(conn);
                vParam = new Vector();
                vParam.add(new Parameter(kQDChieu4VO.getId(), true));
                strWhere = " AND a.kq_id=? AND a.mt_type='900'";
                colMT900 =
                        dchieu4CtietDAO.getKQDChieu4CTietList(strWhere, vParam);

                strWhere = " AND a.kq_id=? AND a.mt_type='910'";
                colMT910 =
                        dchieu4CtietDAO.getKQDChieu4CTietList(strWhere, vParam);
            }

            Type listType = new TypeToken<Collection<DChieu4VO>>() {
            }.getType();
            String strJson;
            if (check) {
                strJson = new Gson().toJson(colDChieuDetail_ok, listType);
            } else {
                strJson = new Gson().toJson(colDChieuDetail, listType);
            }
            jsonObj.addProperty("bkinf", strJson);

            listType = new TypeToken<Collection<KQDChieu4CTietVO>>() {
                }.getType();
            strJson = new Gson().toJson(colMT900, listType);
            jsonObj.addProperty("mt900inf", strJson);
            strJson = new Gson().toJson(colMT910, listType);
            jsonObj.addProperty("mt910inf", strJson);

            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("bkinf");
            jsonArr.add(jsonEle);

            jsonEle = jsonObj.get("mt900inf");
            if (jsonEle != null)
                jsonArr.add(jsonEle);

            jsonEle = jsonObj.get("mt910inf");
            if (jsonEle != null)
                jsonArr.add(jsonEle);

            response.reset();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (TTSPException ttspEx) {
            throw ttspEx;
        } catch (Exception ex) {
            throw TTSPException.createException("TTSP-1000", ex.toString());
        } finally {
            close(conn);
        }

        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
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
        Connection conn = null;
        Collection colMT910 = null;
        Collection listTongKQBK = null;
        Collection colMT900 = null;
        String strMsg = "";
        String strKQuaID = "";
        String errorCode = "";
        try {
            conn = getConnection(request);
            DChieu4Form doiChieu4Form = (DChieu4Form)form;
            String strBKeID = doiChieu4Form.getId();
            HttpSession session = request.getSession();
            DChieu4DAO dchieu4DAO = new DChieu4DAO(conn);
            DChieu4VO vo = new DChieu4VO();
            vo = dchieu4DAO.getMaSGD(null, null);

            if (isTokenValid(request)) {

                CallableStatement cs = null;
                String p_ngay_dc = doiChieu4Form.getNgay_dc();
                String p_loai_tien = doiChieu4Form.getLoai_tien();
                String p_loai_dc = "THUCONG";
                Long p_nguoi_tao_id =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                cs =
 conn.prepareCall("{call SP_DOI_CHIEU_3_NGOAITE_PKG.proc_doi_chieu_lan_3_ngoaite(?,?,?,?,?,?,?,?)}");
                cs.setString(1, strBKeID);
                cs.setString(2, p_ngay_dc);
                cs.setLong(3, p_nguoi_tao_id);
                cs.setLong(4, p_nguoi_tao_id);
                cs.setString(5, p_loai_dc);
                cs.registerOutParameter(6, java.sql.Types.VARCHAR);
                cs.registerOutParameter(7, java.sql.Types.VARCHAR);
                cs.registerOutParameter(8, java.sql.Types.VARCHAR);
                cs.execute();
                strKQuaID = cs.getString(6);
                errorCode = cs.getString(7);
            }
            if ("".equals(strKQuaID)) {
                String strWhereClause =
                    " AND a.bk_id = '" + strBKeID + "' AND a.trang_thai <> '04'";
                KQDChieu4DAO kQDChieu4DAO = new KQDChieu4DAO(conn);
                KQDChieu4VO kQDChieu4VO =
                    kQDChieu4DAO.getKQDChieu4(strWhereClause);
                if (kQDChieu4VO != null) {
                    strKQuaID = kQDChieu4VO.getId();
                }
            }
            if (errorCode != null && !"00".equals(errorCode) &&
                !"01".equals(errorCode) && !"02".equals(errorCode)) {
                String strWhere = "";
                Vector vParam = new Vector();
                vParam.add(new Parameter(strKQuaID, true));
                KQDChieu4CTietDAO dchieu4CtietDAO =
                    new KQDChieu4CTietDAO(conn);
                strWhere = " AND a.kq_id=? AND a.mt_type='900'";
                colMT900 =
                        dchieu4CtietDAO.getKQDChieu4CTietList(strWhere, vParam);

                strWhere = " AND a.kq_id=? AND a.mt_type='910'";
                colMT910 =
                        dchieu4CtietDAO.getKQDChieu4CTietList(strWhere, vParam);

                vParam.add(new Parameter(strKQuaID, true));
                vParam.add(new Parameter(strKQuaID, true));
                vParam.add(new Parameter(strKQuaID, true));
                listTongKQBK = dchieu4CtietDAO.getTongKQDChieu4(vParam);
                request.setAttribute("colMT900", colMT900);
                request.setAttribute("colMT910", colMT910);
                request.setAttribute("TongKQBK", listTongKQBK);
                request.setAttribute("msgNote", strMsg);
            } else {
                if ("01".equals(errorCode)) {
                    strMsg =
                            "&#272;&#227; &#273;&#7889;i chi&#7871;u, k&#7871;t qu&#7843; ch&#234;nh l&#7879;ch.";
                    String strWhere = "";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(strKQuaID, true));
                    KQDChieu4CTietDAO dchieu4CtietDAO =
                        new KQDChieu4CTietDAO(conn);
                    strWhere = " AND a.kq_id=? AND a.mt_type='900'";
                    colMT900 =
                            dchieu4CtietDAO.getKQDChieu4CTietList(strWhere, vParam);

                    strWhere = " AND a.kq_id=? AND a.mt_type='910'";
                    colMT910 =
                            dchieu4CtietDAO.getKQDChieu4CTietList(strWhere, vParam);

                    vParam.add(new Parameter(strKQuaID, true));
                    vParam.add(new Parameter(strKQuaID, true));
                    vParam.add(new Parameter(strKQuaID, true));
                    listTongKQBK = dchieu4CtietDAO.getTongKQDChieu4(vParam);
                } else {
                    strMsg =
                            "&#272;&#227; &#273;&#7889;i chi&#7871;u, k&#7871;t qu&#7843; kh&#7899;p &#273;&#250;ng. H&#227;y b&#7845;m t&#7841;o &#273;i&#7879;n x&#225;c nh&#7853;n v&#7899;i ng&#226;n h&#224;ng.";
                }
                request.setAttribute("colMT900", colMT900);
                request.setAttribute("colMT910", colMT910);
                request.setAttribute("TongKQBK", listTongKQBK);
                request.setAttribute("msgNote", strMsg);
            }

            String strLst =
                " AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE)" + " AND ( (c.ket_qua = '01' OR a.trang_thai = '00')" +
                " AND a.mt_id IN (  SELECT   MAX (mt_id) FROM   sp_bk_dc3_ngoai_te" +
                " WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE) GROUP BY   ngay_dc, send_bank))" +
                " OR a.trang_thai IS NULL) OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE)" +
                " AND a.mt_id IN (  SELECT   MAX (mt_id)" +
                " FROM   sp_bk_dc3_ngoai_te WHERE   TO_DATE (ngay_dc) = TO_DATE (SYSDATE) " +
                " GROUP BY   ngay_dc, send_bank))" +
                " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.trang_thai <> '00')" +
                " OR (TRUNC (a.ngay_thien_dc) = TRUNC (SYSDATE)))";

            String send_bank = doiChieu4Form.getNhkb_nhan();
            if (send_bank != null && "" != send_bank &&
                !"000".equals(send_bank)) {
                strLst += " and a.send_bank='" + send_bank + "'";
            }
            Collection lisBangKe = dchieu4DAO.getDChieu4List(strLst, null);
            request.setAttribute("colBangKe", lisBangKe);
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            List dmucNH = null;
            dmucNH = (List)TTdao.getDMucNH(null, null);
            request.setAttribute("dmucNH", dmucNH);

            resetToken(request);
            saveToken(request);
        } catch (TTSPException e) {
            throw e;
        } catch (Exception e) {
            throw TTSPException.createException("TTSP-9999", e.getMessage());
        } finally {
            close(conn);
        }

        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        Collection colMT910 = null;
        Collection listTongKQBK = null;
        Collection colMT900 = null;
        String strMsg = "";
        try {
            DChieu4Form doiChieu4Form = (DChieu4Form)form;
            DChieu4DAO dchieu4DAO = new DChieu4DAO(conn);
            DChieu4VO vo = new DChieu4VO();
            vo = dchieu4DAO.getMaSGD(null, null);
            //if (isTokenValid(request)) {
            if (1 == 1) {
                conn = getConnection(request);
                String strBK_ID = doiChieu4Form.getId();

                String strWhereClause =
                    " AND a.bk_id = '" + strBK_ID + "' AND a.trang_thai <> '04'";
                KQDChieu4DAO kQDChieu4DAO = new KQDChieu4DAO(conn);
                KQDChieu4VO kQDChieu4VO =
                    kQDChieu4DAO.getKQDChieu4(strWhereClause);
                if (kQDChieu4VO == null) {
                    return mapping.findForward(AppConstants.SUCCESS);
                }
                String kqId = kQDChieu4VO.getId();
                String strWhere = "";
                Vector vParam = new Vector();
                vParam.add(new Parameter(kqId, true));
                KQDChieu4CTietDAO dchieu4CtietDAO =
                    new KQDChieu4CTietDAO(conn);
                strWhere = " AND a.kq_id=? AND a.mt_type='900'";
                colMT900 =
                        dchieu4CtietDAO.getKQDChieu4CTietList(strWhere, vParam);

                strWhere = " AND a.kq_id=? AND a.mt_type='910'";
                colMT910 =
                        dchieu4CtietDAO.getKQDChieu4CTietList(strWhere, vParam);

                vParam.add(new Parameter(kqId, true));
                vParam.add(new Parameter(kqId, true));
                vParam.add(new Parameter(kqId, true));
                listTongKQBK = dchieu4CtietDAO.getTongKQDChieu4(vParam);
                request.setAttribute("colMT900", colMT900);
                request.setAttribute("colMT910", colMT910);
                request.setAttribute("TongKQBK", listTongKQBK);
                request.setAttribute("msgNote", strMsg);
            }

            String strLst =
                " AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE)" + " AND ( (c.ket_qua = '01' OR a.trang_thai = '00')" +
                " AND a.mt_id IN (  SELECT   MAX (mt_id) FROM   sp_bk_dc3_ngoai_te" +
                " WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE) GROUP BY   ngay_dc, send_bank))" +
                " OR a.trang_thai IS NULL) OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE)" +
                " AND a.mt_id IN (  SELECT   MAX (mt_id)" +
                " FROM   sp_bk_dc3_ngoai_te WHERE   TO_DATE (ngay_dc) = TO_DATE (SYSDATE) " +
                " GROUP BY   ngay_dc, send_bank))" +
                " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.trang_thai <> '00')" +
                " OR (TRUNC (a.ngay_thien_dc) = TRUNC (SYSDATE)))";

            String send_bank = doiChieu4Form.getNhkb_nhan();
            if (send_bank != null && "" != send_bank &&
                !"000".equals(send_bank)) {
                strLst += " and a.send_bank='" + send_bank + "'";
            }
            Collection lisBangKe = dchieu4DAO.getDChieu4List(strLst, null);
            request.setAttribute("colBangKe", lisBangKe);
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            List dmucNH = null;
            dmucNH = (List)TTdao.getDMucNH(null, null);
            request.setAttribute("dmucNH", dmucNH);
            resetToken(request);
            saveToken(request);

        } catch (TTSPException e) {
            throw e;
        } catch (Exception e) {
            throw TTSPException.createException("TTSP-9999", e.getMessage());
        } finally {
            close(conn);
        }

        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        // if (!checkPermissionOnFunction(request, "DCHIEU.DC4")) {
        //  return mapping.findForward("errorQuyen");
        // }

        Connection conn = null;
        Collection lisBangKe = null;
        try {
            conn = getConnection(request);
            DChieu4Form doiChieu4Form = (DChieu4Form)form;
            String strID = doiChieu4Form.getId();
            KQDChieu4DAO kqDChieu4DAO = new KQDChieu4DAO(conn);
            KQDChieu4VO kqDChieu4VO = new KQDChieu4VO();
            kqDChieu4VO.setBk_id(strID);
            kqDChieu4VO.setTrang_thai("01");
            kqDChieu4VO.setNgay_thien_dc(StringUtil.DateToString(new Date(),
                                                                 "dd/MM/yyyy"));
            kqDChieu4DAO.updateKQDC4(kqDChieu4VO);

            DChieu4DAO dchieu4DAO = new DChieu4DAO(conn);
            String strWhere = "";
            Vector vParam = null;
            lisBangKe = dchieu4DAO.getDChieu4List(strWhere, vParam);
            request.setAttribute("colBangKe", lisBangKe);
            request.setAttribute("colMT900", null);
            request.setAttribute("colMT910", null);
            request.setAttribute("TongKQBK", null);
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            List dmucNH = null;
            dmucNH = (List)TTdao.getDMucNH(null, null);
            request.setAttribute("dmucNH", dmucNH);

            conn.commit();
            saveToken(request);
        } catch (TTSPException ttspEx) {
            throw ttspEx;
        } catch (Exception ex) {
            throw TTSPException.createException("TTSP-1000", ex.toString());
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);

    }

    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection(request);
            DChieu4Form f = (DChieu4Form)form;
            KQDChieu4DAO kqDChieu4DAO = new KQDChieu4DAO(conn);
            KQDChieu4VO kqDChieu4VO = new KQDChieu4VO();
            String ngay_dc = f.getNgay_dc();
            String trang_thai = f.getTrang_thai_in();
            String lan_dc = f.getLan_dc();
            ngay_dc = ngay_dc.replace("/", "-");
            String send_bank = f.getSend_bank();
            String strW = "'" + send_bank + "'";
            kqDChieu4VO = kqDChieu4DAO.getTenNH(strW, null);
            String tenNH = kqDChieu4VO.getTen();

            if (ngay_dc != null && !"".equals(ngay_dc)) {
                sbSubHTML.append("<input type=\"hidden\" name=\"lan_dc\" value=\"" +
                                 lan_dc + "\" id=\"lan_dc\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dc\" value=\"" +
                                 ngay_dc + "\" id=\"ngay_dc\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"send_bank\" value=\"" +
                                 send_bank + "\" id=\"send_bank\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"tenNH\" value=\"" +
                                 tenNH + "\" id=\"tenNH\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"lan_dc\" value=\"" +
                                 f.getLan_dc() + "\" id=\"lan_dc\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"kq_id\" value=\"" +
                                 f.getKq_id() + "\" id=\"kq_id\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"trang_thai_in\" value=\"" +
                                 trang_thai +
                                 "\" id=\"trang_thai_in\"></input>");
                JasperPrint jasperPrint = null;
                HashMap parameterMap = new HashMap();
                ReportUtility rpUtilites = new ReportUtility();
                String fileName = "";
                if (trang_thai.equals("02")) {
                    fileName = "/rpt_DChieu_KQua_TGui_kd";
                } else if (trang_thai.equals("01")) {
                    fileName = "/rpt_DChieu_KQua_TGui_kl";
                }
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
                parameterMap.put("p_LAN", f.getLan_dc());
                parameterMap.put("p_ID_KQ", f.getKq_id());
                parameterMap.put("p_NGAY", ngay_dc);
                parameterMap.put("p_MA_NH", send_bank);
                parameterMap.put("p_TEN_NH", tenNH);

                parameterMap.put("REPORT_LOCALE",
                                 new java.util.Locale("us", "US"));
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "PrintDChieu4Action.do";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    REPORT_DIRECTORY + strFontTimeRoman;

                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                reportStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        Vector params = null;
        try {
            conn = getConnection();
            params = new Vector();
            String ngay_doi_chieu =
                request.getParameter("ngay_doi_chieu").toString();
            String mt_id = request.getParameter("mt_id").toString();
            DChieu4DAO dao = new DChieu4DAO(conn);
            String strWhere = " AND a.mt_id = '" + mt_id +"' AND a.ngay_dc = TO_DATE('"+ngay_doi_chieu+"','dd/mm/yyyy') ";
            Collection lstChiTietBangKe = dao.getTTBangKe(strWhere, params);
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(lstChiTietBangKe);
            jsonObj.addProperty("lstChiTietBangKe", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("lstChiTietBangKe");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            throw e;
        }finally{
            close(conn);
        }
        return mapping.findForward("success");
    }

}
