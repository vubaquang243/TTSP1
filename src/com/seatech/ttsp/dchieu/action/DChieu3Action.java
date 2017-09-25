package com.seatech.ttsp.dchieu.action;


import com.google.gson.Gson;
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
import com.seatech.ttsp.dchieu.DChieu3DAO;
import com.seatech.ttsp.dchieu.DChieu3VO;
import com.seatech.ttsp.dchieu.KQDChieu3CTietDAO;
import com.seatech.ttsp.dchieu.KQDChieu3CTietVO;
import com.seatech.ttsp.dchieu.KQDChieu3DAO;
import com.seatech.ttsp.dchieu.KQDChieu3VO;
import com.seatech.ttsp.dchieu.form.DChieu3Form;
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

/**
 * @modify name: ThuongDT
 * @modify date: 09/03/2017
 * @see: bo sung tham so loai tien, su dung doi chieu voi nhieu loai ngoai te
 * @find: thuongdt-20170309
 * */
public class DChieu3Action extends AppAction {


    public DChieu3Action() {
        super();
    }


    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.DC3")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        Collection lisBangKe = null;
        try {
            conn = getConnection(request);
//          HttpSession session = request.getSession();
          DChieu3DAO dchieu3DAO = new DChieu3DAO(conn);
          
           DChieu3VO vo = new DChieu3VO();
            vo=dchieu3DAO.getMaSGD(null, null);
            DChieu3Form f = (DChieu3Form)form;
          String idxNH= request.getParameter("idxNH")==null?"":request.getParameter("idxNH");
          
          if(idxNH!=null&&!"".equals(idxNH)){
            request.setAttribute("idxNH", idxNH);
          }
            
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            List dmucNH = null;
            dmucNH = (List)TTdao.getDMucNH(null,null);
            request.setAttribute("dmucNH", dmucNH);
          
            
            String strWhere = " AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE)" + 
            " AND ( (c.ket_qua = '01' OR a.trang_thai = '00')" + 
            " AND a.id IN (  SELECT   MAX (id) FROM   sp_bk_dc3" + 
            " WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE) GROUP BY   ngay_dc, send_bank))" + 
            " OR a.trang_thai IS NULL) OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE)" + 
            " AND a.id IN (  SELECT   MAX (id)" + 
            " FROM   sp_bk_dc3 WHERE   TO_DATE (ngay_dc) = TO_DATE (SYSDATE) " + 
            " GROUP BY   ngay_dc, send_bank))" + 
            " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.trang_thai <> '00')" + 
            " OR (TRUNC (a.ngay_thien_dc) = TRUNC (SYSDATE)))";
          String send_bank = f.getNhkb_nhan();
            if(send_bank!=null && ""!=send_bank && !"000".equals(send_bank)){
              strWhere +=" and a.send_bank='"+send_bank+"'";
            }
            Vector vParam = null;
            lisBangKe = dchieu3DAO.getDChieu3List(strWhere, vParam);
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

            DChieu3DAO dchieu3DAO = new DChieu3DAO(conn);
            String strWhere = "AND a.id=?";
            Vector vParam = new Vector();
            vParam.add(new Parameter(request.getParameter("id"), true));
            colDChieuDetail = dchieu3DAO.getDChieu3List(strWhere, vParam);

            String strWhereClause =
                " AND a.bk_id = '" + request.getParameter("id") +
                "' AND a.trang_thai <> '04'";
            KQDChieu3DAO kQDChieu3DAO = new KQDChieu3DAO(conn);
            KQDChieu3VO kQDChieu3VO =
                kQDChieu3DAO.getKQDChieu3(strWhereClause);

            if (kQDChieu3VO != null) {
                vParam = new Vector();
                vParam.add(new Parameter(kQDChieu3VO.getId(), true));
                vParam.add(new Parameter(kQDChieu3VO.getId(), true));
                vParam.add(new Parameter(kQDChieu3VO.getId(), true));
                vParam.add(new Parameter(kQDChieu3VO.getId(), true));
                KQDChieu3CTietDAO kQDChieu3CTietDAO =
                    new KQDChieu3CTietDAO(conn);
                Collection colTongKQDChieu =
                    kQDChieu3CTietDAO.getTongKQDChieu3(vParam);
                if (colTongKQDChieu != null) {
                    Iterator iter = colTongKQDChieu.iterator();
                    KQDChieu3CTietVO kQDChieu3CTietVO = null;
                    while (iter.hasNext()) {
                        kQDChieu3CTietVO = (KQDChieu3CTietVO)iter.next();
                        DChieu3VO dChieu3VO = null;
                        Iterator iter2 = colDChieuDetail.iterator();
                        while (iter2.hasNext()) {
                            dChieu3VO = (DChieu3VO)iter2.next();
                            dChieu3VO.setMt900_thieu(kQDChieu3CTietVO.getMt900thieu());
                            dChieu3VO.setMt900_thua(kQDChieu3CTietVO.getMt900thua());
                            dChieu3VO.setMt910_thieu(kQDChieu3CTietVO.getMt910thieu());
                            dChieu3VO.setMt910_thua(kQDChieu3CTietVO.getMt910thua());
                            check = true;
                        }
                        colDChieuDetail_ok.add(dChieu3VO);
                    }
                }

                KQDChieu3CTietDAO dchieu3CtietDAO =
                    new KQDChieu3CTietDAO(conn);
                vParam = new Vector();
                vParam.add(new Parameter(kQDChieu3VO.getId(), true));
                strWhere = " AND a.kq_id=? AND a.mt_type='900'";
                colMT900 =
                        dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);

                strWhere = " AND a.kq_id=? AND a.mt_type='910'";
                colMT910 =
                        dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);
            }

            Type listType = new TypeToken<Collection<DChieu3VO>>() {
            }.getType();
            String strJson;
            if (check) {
                strJson = new Gson().toJson(colDChieuDetail_ok, listType);
            } else {
                strJson = new Gson().toJson(colDChieuDetail, listType);
            }
            jsonObj.addProperty("bkinf", strJson);

            listType = new TypeToken<Collection<KQDChieu3CTietVO>>() {
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
        String p_loai_tien = "VND";//thuongdt-20170309
        try {
            conn = getConnection(request);
            DChieu3Form doiChieu3Form = (DChieu3Form)form;
            String strBKeID = doiChieu3Form.getId();
          HttpSession session = request.getSession();
          DChieu3DAO dchieu3DAO = new DChieu3DAO(conn);
          DChieu3VO vo = new DChieu3VO();
           vo=dchieu3DAO.getMaSGD(null, null);
//          String kb_nhan = vo.getMa_nh();
            if (isTokenValid(request)) {

                CallableStatement cs = null;
                String p_ngay_dc = doiChieu3Form.getNgay_dc();
              
                Long p_nguoi_tao_id =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                cs =
 conn.prepareCall("{call sp_doi_chieu_pkg.proc_doi_chieu_lan_3(?,?,?,?,?,?,?)}");
                cs.setString(1, strBKeID);
                cs.setString(2, p_ngay_dc);
                cs.setLong(3, p_nguoi_tao_id);
                cs.setString(4, p_loai_tien);//thuongdt-20170309
                cs.registerOutParameter(5, java.sql.Types.VARCHAR);
                cs.registerOutParameter(6, java.sql.Types.VARCHAR);
                cs.registerOutParameter(7, java.sql.Types.VARCHAR);
                cs.execute();
                strKQuaID = cs.getString(5);
                errorCode = cs.getString(6);
            }
            if ("".equals(strKQuaID)) {
                String strWhereClause =
                    " AND a.bk_id = '" + strBKeID + "' AND a.trang_thai <> '04'";
                KQDChieu3DAO kQDChieu3DAO = new KQDChieu3DAO(conn);
                KQDChieu3VO kQDChieu3VO =
                    kQDChieu3DAO.getKQDChieu3(strWhereClause);
                if (kQDChieu3VO != null) {
                    strKQuaID = kQDChieu3VO.getId();
                }
            }


//            DChieu3DAO dchieu3DAO = new DChieu3DAO(conn);
            
            if (errorCode != null && !"00".equals(errorCode) &&
                !"01".equals(errorCode) && !"02".equals(errorCode)) {
                String strWhere = "";
                Vector vParam = new Vector();
                vParam.add(new Parameter(strKQuaID, true));
                KQDChieu3CTietDAO dchieu3CtietDAO =
                    new KQDChieu3CTietDAO(conn);
                strWhere = " AND a.kq_id=? AND a.mt_type='900'";
                colMT900 =
                        dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);

                strWhere = " AND a.kq_id=? AND a.mt_type='910'";
                colMT910 =
                        dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);

                vParam.add(new Parameter(strKQuaID, true));
                vParam.add(new Parameter(strKQuaID, true));
                vParam.add(new Parameter(strKQuaID, true));
                listTongKQBK = dchieu3CtietDAO.getTongKQDChieu3(vParam);
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
                    KQDChieu3CTietDAO dchieu3CtietDAO =
                        new KQDChieu3CTietDAO(conn);
                    strWhere = " AND a.kq_id=? AND a.mt_type='900'";
                    colMT900 =
                            dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);

                    strWhere = " AND a.kq_id=? AND a.mt_type='910'";
                    colMT910 =
                            dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);

                    vParam.add(new Parameter(strKQuaID, true));
                    vParam.add(new Parameter(strKQuaID, true));
                    vParam.add(new Parameter(strKQuaID, true));
                    listTongKQBK = dchieu3CtietDAO.getTongKQDChieu3(vParam);
                } else {
                    strMsg =
                            "&#272;&#227; &#273;&#7889;i chi&#7871;u, k&#7871;t qu&#7843; kh&#7899;p &#273;&#250;ng. H&#227;y b&#7845;m t&#7841;o &#273;i&#7879;n x&#225;c nh&#7853;n v&#7899;i ng&#226;n h&#224;ng.";
                }
                request.setAttribute("colMT900", colMT900);
                request.setAttribute("colMT910", colMT910);
                request.setAttribute("TongKQBK", listTongKQBK);
                request.setAttribute("msgNote", strMsg);
            }

          String strLst = " AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE)" + 
            " AND ( (c.ket_qua = '01' OR a.trang_thai = '00')" + 
            " AND a.id IN (  SELECT   MAX (id) FROM   sp_bk_dc3" + 
            " WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE) GROUP BY   ngay_dc, send_bank))" + 
            " OR a.trang_thai IS NULL) OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE)" + 
            " AND a.id IN (  SELECT   MAX (id)" + 
            " FROM   sp_bk_dc3 WHERE   TO_DATE (ngay_dc) = TO_DATE (SYSDATE) " + 
            " GROUP BY   ngay_dc, send_bank))" + 
            " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.trang_thai <> '00')" + 
            " OR (TRUNC (a.ngay_thien_dc) = TRUNC (SYSDATE)))";
          
          String send_bank = doiChieu3Form.getNhkb_nhan();
            if(send_bank!=null && ""!=send_bank && !"000".equals(send_bank)){
              strLst +=" and a.send_bank='"+send_bank+"'";
            }
            Collection lisBangKe = dchieu3DAO.getDChieu3List(strLst, null);
            request.setAttribute("colBangKe", lisBangKe);
          TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
          List dmucNH = null;
          dmucNH = (List)TTdao.getDMucNH(null,null);
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
            DChieu3Form doiChieu3Form = (DChieu3Form)form;
          DChieu3DAO dchieu3DAO = new DChieu3DAO(conn);
//          HttpSession session = request.getSession();
//          DChieu3DAO dchieu3DAO = new DChieu3DAO(conn);
          DChieu3VO vo = new DChieu3VO();
           vo=dchieu3DAO.getMaSGD(null, null);
            //if (isTokenValid(request)) {
            if (1 == 1) {
                conn = getConnection(request);
                String strBK_ID = doiChieu3Form.getId();

                String strWhereClause =
                    " AND a.bk_id = '" + strBK_ID + "' AND a.trang_thai <> '04'";
                KQDChieu3DAO kQDChieu3DAO = new KQDChieu3DAO(conn);
                KQDChieu3VO kQDChieu3VO =
                    kQDChieu3DAO.getKQDChieu3(strWhereClause);
                if (kQDChieu3VO == null) {
                    return mapping.findForward(AppConstants.SUCCESS);
                }
                String kqId = kQDChieu3VO.getId();



                String strWhere = "";
                Vector vParam = new Vector();
                vParam.add(new Parameter(kqId, true));
                KQDChieu3CTietDAO dchieu3CtietDAO =
                    new KQDChieu3CTietDAO(conn);
                strWhere = " AND a.kq_id=? AND a.mt_type='900'";
                colMT900 =
                        dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);

                strWhere = " AND a.kq_id=? AND a.mt_type='910'";
                colMT910 =
                        dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, vParam);

                vParam.add(new Parameter(kqId, true));
                vParam.add(new Parameter(kqId, true));
                vParam.add(new Parameter(kqId, true));
                listTongKQBK = dchieu3CtietDAO.getTongKQDChieu3(vParam);
                request.setAttribute("colMT900", colMT900);
                request.setAttribute("colMT910", colMT910);
                request.setAttribute("TongKQBK", listTongKQBK);
                request.setAttribute("msgNote", strMsg);
            }
            
          String strLst = " AND ( (TRUNC (a.ngay_dc) < TRUNC (SYSDATE)" + 
            " AND ( (c.ket_qua = '01' OR a.trang_thai = '00')" + 
            " AND a.id IN (  SELECT   MAX (id) FROM   sp_bk_dc3" + 
            " WHERE   TO_DATE (ngay_dc) < TO_DATE (SYSDATE) GROUP BY   ngay_dc, send_bank))" + 
            " OR a.trang_thai IS NULL) OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE)" + 
            " AND a.id IN (  SELECT   MAX (id)" + 
            " FROM   sp_bk_dc3 WHERE   TO_DATE (ngay_dc) = TO_DATE (SYSDATE) " + 
            " GROUP BY   ngay_dc, send_bank))" + 
            " OR (TRUNC (a.ngay_dc) = TRUNC (SYSDATE) AND a.trang_thai <> '00')" + 
            " OR (TRUNC (a.ngay_thien_dc) = TRUNC (SYSDATE)))";   
            
          String send_bank = doiChieu3Form.getNhkb_nhan();
            if(send_bank!=null && ""!=send_bank && !"000".equals(send_bank)){
              strLst +=" and a.send_bank='"+send_bank+"'";
            }
            Collection lisBangKe = dchieu3DAO.getDChieu3List(strLst, null);
            request.setAttribute("colBangKe", lisBangKe);
          TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
          List dmucNH = null;
          dmucNH = (List)TTdao.getDMucNH(null,null);
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
        if (!checkPermissionOnFunction(request, "DCHIEU.DC3")) {
            return mapping.findForward("errorQuyen");
        }

        Connection conn = null;
        Collection lisBangKe = null;
        try {
            conn = getConnection(request);
            DChieu3Form doiChieu3Form = (DChieu3Form)form;
            String strID = doiChieu3Form.getId(); //request.getParameter("id");
            //xem chi tiet
            KQDChieu3DAO kqDChieu3DAO = new KQDChieu3DAO(conn);
            KQDChieu3VO kqDChieu3VO = new KQDChieu3VO();
            kqDChieu3VO.setBk_id(strID);
            kqDChieu3VO.setTrang_thai("01");
            kqDChieu3VO.setNgay_thien_dc(StringUtil.DateToString(new Date(),
                                                                 "dd/MM/yyyy"));
            kqDChieu3DAO.updateKQDC3(kqDChieu3VO);

            DChieu3DAO dchieu3DAO = new DChieu3DAO(conn);
            String strWhere = "";
            Vector vParam = null;
            lisBangKe = dchieu3DAO.getDChieu3List(strWhere, vParam);
            request.setAttribute("colBangKe", lisBangKe);
            request.setAttribute("colMT900", null);
            request.setAttribute("colMT910", null);
            request.setAttribute("TongKQBK", null);

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
//    public static final String fileName = "/rpt_DChieu_KQua_TGui";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection(request);
            DChieu3Form f = (DChieu3Form)form;
            KQDChieu3DAO kqDChieu3DAO = new KQDChieu3DAO(conn);
            KQDChieu3VO kqDChieu3VO = new KQDChieu3VO();
            // l� ng d�ng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
//            HttpSession session = request.getSession();
            String ngay_dc = f.getNgay_dc();
            String trang_thai=f.getTrang_thai_in();
            String lan_dc = f.getLan_dc();
            ngay_dc = ngay_dc.replace("/", "-");
            String send_bank = f.getSend_bank();
            String strW = "'" + send_bank + "'";
            kqDChieu3VO = kqDChieu3DAO.getTenNH(strW, null);
            String tenNH = kqDChieu3VO.getTen();
//            String user_id =
//                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            //        SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy");
            //        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
            //            ngay_dc = myFormat.format(fromUser.parse(ngay_dc));


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
                               trang_thai + "\" id=\"trang_thai_in\"></input>");
                JasperPrint jasperPrint = null;
                HashMap parameterMap = new HashMap();
                ReportUtility rpUtilites = new ReportUtility();
              String fileName="";
                if (trang_thai.equals("02")){
                  fileName = "/rpt_DChieu_KQua_TGui_kd";
                }
                else if(trang_thai.equals("01")){
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
                                             new java.util.Locale("vi", "VI"));
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);
              
                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "PrintDChieu3Action.do";
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
//                e.printStackTrace();
            }
            close(conn);
        }

        return mapping.findForward("success");
    }


}
