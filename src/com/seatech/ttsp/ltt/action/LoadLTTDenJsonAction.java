package com.seatech.ttsp.ltt.action;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmdvsdns.DMDonViSdnsDAO;
import com.seatech.ttsp.dmdvsdns.DMDonViSdnsVO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.ltt.LTTCTietDAO;
import com.seatech.ttsp.ltt.LTTCTietVO;
import com.seatech.ttsp.ltt.LTTCommon;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.ltt.form.LTTForm;
import com.seatech.ttsp.user.UserVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LoadLTTDenJsonAction extends AppAction {
    private static String SUCCESS = "success";
    private static String STRING_EMPTY = "";

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Vector params = null;
        Parameter param = null;
        String whereClause = "";
        LTTVO lttVO = null;
        LTTForm lttForm = null;
        StringBuffer strLog=new StringBuffer();
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String strId = request.getParameter("idLoadDetail");
            String strUserType =
                request.getParameter("userType") != null ? request.getParameter("userType") :
                session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
            if (strId != null && !STRING_EMPTY.equalsIgnoreCase(strId)) {
                LTTDAO lttDAO = new LTTDAO(conn);
                whereClause = " t.id = ? ";
                params = new Vector();
                params.add(new Parameter(strId, true));
                lttVO = lttDAO.getLTTDi(whereClause, params);
                lttForm = new LTTForm();
                BeanUtils.copyProperties(lttForm, lttVO);
                if (strUserType.contains(AppConstants.NSD_KTT) ||
                    strUserType.contains(AppConstants.NSD_GD)) {
                    TTSPUtils spUtils = new TTSPUtils(conn);
                    String strNoiDungKy =
                        spUtils.getNoiDungKy(strId, "103", "N");
                    lttForm.setNoi_dung_ky(strNoiDungKy);
                }

                if (lttVO != null) {
                    DMNHangVO dmnhVO = null;

                    if (lttVO.getNhkb_chuyen() != null) {
                        dmnhVO =
                                LTTCommon.getDMNHang(lttVO.getNhkb_chuyen(), conn);
                        if (dmnhVO != null) {
                            lttForm.setMa_nhkb_chuyen_cm(dmnhVO.getMa_nh());
                            lttForm.setTen_nhkb_chuyen_cm(dmnhVO.getTen());
                        }
                    }

                    if (lttVO.getId_nhkb_chuyen() != null) {
                        dmnhVO = null;
                        dmnhVO =
                                LTTCommon.getDMNHang(lttVO.getId_nhkb_chuyen(),
                                                     conn);
                        if (dmnhVO != null) {
                            lttForm.setMa_nhkb_chuyen(dmnhVO.getMa_nh());
                            lttForm.setTen_nhkb_chuyen(dmnhVO.getTen());
                        }
                    }
                    if (lttVO.getNhkb_nhan() != null) {
                        dmnhVO = null;
                        dmnhVO =
                                LTTCommon.getDMNHang(lttVO.getNhkb_nhan(), conn);
                        if (dmnhVO != null) {
                            lttForm.setMa_nhkb_nhan_cm(dmnhVO.getMa_nh());
                            lttForm.setTen_nhkb_nhan_cm(dmnhVO.getTen());
                        }
                    }
                    if (lttVO.getId_nhkb_nhan() != null) {
                        dmnhVO = null;
                        dmnhVO =
                                LTTCommon.getDMNHang(lttVO.getId_nhkb_nhan(), conn);
                        if (dmnhVO != null) {
                            lttForm.setMa_nhkb_nhan(dmnhVO.getMa_nh());
                            lttForm.setTen_nhkb_nhan(dmnhVO.getTen());
                        }
                    }

                    //Lay Ma TTV ho�n thi?n neu co
                    UserVO userVO = null;
                    if (lttVO.getTtv_nhan() != null &&
                        lttVO.getTtv_nhan() != 0 &&
                        !STRING_EMPTY.equals(lttVO.getTtv_nhan())) {
                        userVO = getUserInfo(lttVO.getTtv_nhan().toString());
                        if (userVO != null) {
                            if (userVO.getMa_nsd() != null)
                                lttForm.setMa_ttv_nhan(userVO.getMa_nsd());
                            if (userVO.getTen_nsd() != null)
                                lttForm.setTen_ttv_nhan(userVO.getTen_nsd());
                        }
                    }
                    //Lay Ma KTT neu co
                    userVO = null;
                    if (lttVO.getKtt_duyet() != null &&
                        lttVO.getKtt_duyet() != 0 &&
                        !STRING_EMPTY.equals(lttVO.getKtt_duyet())) {
                        userVO = getUserInfo(lttVO.getKtt_duyet().toString());
                        if (userVO != null) {
                            if (userVO.getMa_nsd() != null)
                                lttForm.setMa_ktt_duyet(userVO.getMa_nsd());
                            if (userVO.getTen_nsd() != null)
                                lttForm.setTen_ktt_duyet(userVO.getTen_nsd());
                        }
                    }
                    whereClause = "t.ltt_id = ?";
                    //            whereClause = "t.ltt_id = ? m.tinhtrang='1' and k.tinhtrang='1' and n.tinhtrang='1' and c.tinhtrang='1' " +
                    //                "and nkt.tinhtrang='1' and ndkt.tinh_trang='1' and dbhc.tinhtrang='1' and ctmt.tinhtrang='1' and dp.tinhtrang='1'";
                    params = new Vector();
                    param = new Parameter(lttVO.getId(), true);
                    params.add(param);

                    //ghi log
                    strLog.append("\n #######################################");

                    LTTCTietDAO lttCTietDAO = new LTTCTietDAO(conn);
                    Collection collLttDtl =
                        lttCTietDAO.getLTTDiCTietList(whereClause, params);

                    // ghi log
                    strLog.append("\n\t - so dong : " +
                                   collLttDtl.size());
                    strLog.append(" | kb : " +
                                   session.getAttribute(AppConstants.APP_KB_CODE_SESSION));

                    // lay ra ten dvqhns
                    // uu tien dvqhns ban ghi gan nhat
                    if (collLttDtl != null) {
                        if (collLttDtl.size() == 0) {
                            //so_tk_nhan
                            String strMaDVQHNS = "";
                            if (lttVO.getSo_tk_nhan() != null &&
                                !STRING_EMPTY.equals(lttVO.getSo_tk_nhan())) {
                                int iSoTKNhan = lttVO.getSo_tk_nhan().length();
                                DMDonViSdnsDAO dao = new DMDonViSdnsDAO(conn);
                                String strWhereClauseDVQHNS = " a.ma=? ";
                                if (iSoTKNhan == 11 || iSoTKNhan == 16) {
                                    strMaDVQHNS =
                                            lttVO.getSo_tk_nhan().substring(4,
                                                                            11);
                                } else if (iSoTKNhan == 12 ||
                                           iSoTKNhan == 17) {
                                    strMaDVQHNS =
                                            lttVO.getSo_tk_nhan().substring(5,
                                                                            12);
                                }
                                strLog.append("\n\t\t - coa null. So tk nhan :  " +
                                               lttVO.getSo_tk_nhan());
                                if (!STRING_EMPTY.equals(strMaDVQHNS)) {
                                    Vector paramDVQHNS = new Vector();
                                    DMDonViSdnsVO dvsdnsVO = null;
                                    paramDVQHNS.add(new Parameter(strMaDVQHNS,
                                                                  true));
                                    dvsdnsVO =
                                            dao.getDMDonViSdns(strWhereClauseDVQHNS,
                                                               paramDVQHNS);
                                    if (dvsdnsVO != null) {
                                        Type TypeDVQHNS =
                                            new TypeToken<DMDonViSdnsVO>() {
                                        }.getType();
                                        strJson =
                                                new Gson().toJson(dvsdnsVO, TypeDVQHNS);
                                        jsonObj.addProperty("TenCOA", strJson);
                                    }
                                }
                                strLog.append(" | dvqhns :  " + strJson);
                            }
                        } else {
                            Iterator ito = collLttDtl.iterator();
                            DMDonViSdnsDAO dao = new DMDonViSdnsDAO(conn);
                            String strWhereClauseDVQHNS = " a.ma=? ";
                            while (ito.hasNext()) {
                                Vector paramDVQHNS = new Vector();
                                LTTCTietVO voCOA = (LTTCTietVO)ito.next();
                                DMDonViSdnsVO dvsdnsVO = null;
                                paramDVQHNS.add(new Parameter(voCOA.getDvsdns_ma(),
                                                              true));
                                dvsdnsVO =
                                        dao.getDMDonViSdns(strWhereClauseDVQHNS,
                                                           paramDVQHNS);
                                if (dvsdnsVO != null) {
                                    Type TypeDVQHNS =
                                        new TypeToken<DMDonViSdnsVO>() {
                                    }.getType();
                                    strJson =
                                            new Gson().toJson(dvsdnsVO, TypeDVQHNS);
                                    jsonObj.addProperty("TenCOA", strJson);
                                    break;
                                }
                            }
                        }
                    }
                    //
                    Type listTypeDetail = null;
                    listTypeDetail = new TypeToken<ArrayList<LTTCTietVO>>() {
                        }.getType();
                    strJson = new Gson().toJson(collLttDtl, listTypeDetail);
                    jsonObj.addProperty("details", strJson);
                }
            }
            Type listType = new TypeToken<LTTForm>() {
            }.getType();
            strJson = new Gson().toJson(lttForm, listType);

            jsonObj.addProperty("master", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("master");
            jsonArr.add(jsonEle);

            jsonEle = jsonObj.get("details");
            jsonArr.add(jsonEle);

            // ghi log
            strLog.append("\n\t - chi tiet :  " + jsonEle);
            strLog.append("\n #######################################");
           // writeLog.write(strLog.toString());
            jsonEle = jsonObj.get("TenCOA");
            if (jsonObj.get("TenCOA") == null) {
                Type TypeDVQHNS = new TypeToken<DMDonViSdnsVO>() {
                }.getType();
                strJson = new Gson().toJson(new DMDonViSdnsVO(), TypeDVQHNS);
                jsonObj.addProperty("TenCOA", strJson);
                jsonEle = jsonObj.get("TenCOA");
            }
            jsonArr.add(jsonEle);

            jsonEle = jsonObj.get("detailsTL");
            jsonArr.add(jsonEle);

            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            jsonObj = new JsonObject();
            jsonObj.addProperty("error", e.getMessage());
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
            out.flush();
            out.close();
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(SUCCESS);
        else
            return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        Parameter param = null;
        Vector params = null;
        String whereClause = "";
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            params = new Vector();
            LTTForm lttform = (LTTForm)form;
            String username = session.getAttribute("id_nsd").toString();

            String templateFileName =
                AppConstants.APP_TEMPLATE_PATH + "ltt.xls";
            String destFileName =
                AppConstants.APP_REPORT_PATH + username + "ltt.xls";
            Map map = new HashMap();
            map.put("nh_kh_nhan",
                    lttform.getMa_nhkb_nhan_cm() + "-" + lttform.getTen_nhkb_nhan_cm());
            map.put("nh_kh_chuyen",
                    lttform.getMa_nhkb_chuyen() + "-" + lttform.getTen_nhkb_chuyen_cm());
            map.put("tkhoan_chuyen", lttform.getSo_tk_chuyen());
            map.put("tkhoan_nhan", lttform.getSo_tk_nhan());
            map.put("so_ctu_goc", lttform.getSo_ct_goc());
            map.put("lct",
                    "LCC t? " + lttform.getTen_nhkb_chuyen_cm() + " sang KBNN");
            map.put("so_lenh_tt", lttform.getId());
            map.put("ngay_chuyen", lttform.getNgay_tt());
            map.put("ttin_kh_chuyen", lttform.getTtin_kh_chuyen());
            //         map.put("tkhoan_chuyen", lttform.getTtin_kh_chuyen());
            map.put("mo_tai_nh_kb_chuyen", lttform.getSo_tk_chuyen());
            map.put("ttin_kh_nhan", lttform.getTtin_kh_nhan());
            //         map.put("tkhoan_nhan", lttform.getTtin_kh_chuyen());
            map.put("mo_tai_nh_kb_nhan", lttform.getSo_tk_chuyen());
            map.put("noi_dung_tt", lttform.getNdung_tt());
            map.put("tong_so_tien", lttform.getTong_sotien());
            map.put("ma_nt", lttform.getMa_nt());
            // map.put("date",DateUtils.LongToStrDateDDMMYYYY(DateUtils.getCurrentDate()))  ;
            // map.put("thanhtoanvien", session.getAttribute(AppConstants.APP_USER_NAME_SESSION).toString());
            whereClause = "t.ltt_id = ?";
            //            whereClause = "t.ltt_id = ? m.tinhtrang='1' and k.tinhtrang='1' and n.tinhtrang='1' and c.tinhtrang='1' " +
            //                "and nkt.tinhtrang='1' and ndkt.tinh_trang='1' and dbhc.tinhtrang='1' and ctmt.tinhtrang='1' and dp.tinhtrang='1'";
            params = new Vector();
            param = new Parameter(lttform.getId(), true);
            params.add(param);
            Integer currentPage = 1;
            Integer numberRowOnPage = new Integer(15);
            Integer totalCount[] = new Integer[1];
            LTTCTietDAO lttCTietDAO = new LTTCTietDAO(conn);
            //            Collection collLttDtl =
            //                lttCTietDAO.getLTTDiCTiet4report(whereClause, params,
            //                                                 currentPage, numberRowOnPage,
            //                                                 totalCount, templateFileName,
            //                                                 destFileName, map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
        }

        return mapping.findForward(AppConstants.SUCCESS);
    }
}
