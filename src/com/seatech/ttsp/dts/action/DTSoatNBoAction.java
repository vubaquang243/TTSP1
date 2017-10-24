package com.seatech.ttsp.dts.action;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dmnhkb.DMNHKBacDAO;
import com.seatech.ttsp.dmnhkb.DMNHKBacVO;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;
import com.seatech.ttsp.dts.form.DTSoatForm;
import com.seatech.ttsp.proxy.giaodien.SendDTS;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;
import com.seatech.ttsp.thamchieu.MaThamChieuVO;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DTSoatNBoAction extends AppAction {
    private static String STRING_EMPTY = "";
    private final static String DTSNBo = "DTS.TS_NOIBO";
    private static final String PERMISSION_PAGE = "errorQuyen";
    private static final String DTSNBO_THEM = "DTS.TS_NOIBO.XULY_DI.THEM";
    private static final String DTSNBO_SUA = "DTS.TS_NOIBO.XULY_DI.SUA";
    private static final String DTSNBO_XOA = "DTS.TS_NOIBO.XULY_DI.XOA";
    private static final String DTSNBO_DUYET = "DTS.TS_NOIBO.XULY_DI.DUYET";
    private static String FLAG_NH = "NH";
    private static String FLAG_KB = "KB";
    private static String LOAI_DTS = "N";
    private static String CD_KTT = "KTT";
    private static String CD_TTV = "TTV";
    private static String CD_GD = "GD";
    private static String REQUEST_ACTION = "action";
    private static String TTTT = "0002";
    private static final String DATE_FORMAT_CUTOFTIME =
        "dd/MM/yyyy HH24:MI:SS";
    private static String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    private static String strCutOfTime, strCurrDate, strPreviousDate;

    public DTSoatNBoAction() {
        super();
    }


    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        Vector params = null;
        String strWhereClause = null;
        String strPhanQuyen = null;
        String action = null;
        String chucdanh = null;
        List lstSDTS = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, DTSNBo)) {
                return mapping.findForward(PERMISSION_PAGE);
            } else {
                // phan quyen
                HttpSession session = request.getSession();
                params = new Vector();
                strPhanQuyen =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                conn = getConnection();
                // Hien thi da ta phan loai theo user
                Long lUserID =
                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                String nhkb_chuyen_id =
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
                strWhereClause = "";
                if(strPhanQuyen.equals("CB-TTTT")||strPhanQuyen.equals("CBPT-TTTT")){
                  strWhereClause+=" and substr(a.id,6,3) = '399' and a.LOAI_DTS ='TW' ";
                }else{
                      strWhereClause+=" and substr(a.id,6,3) = '399' and a.LOAI_DTS ='N' ";
                    }
                        
                //" (a.LTT_ID is null or a.LTT_ID = '') and (a.DTS_ID is null or a.DTS_ID = '') and a.LOAI_DTS= 'N' ";
                DTSoatForm f = (DTSoatForm)form;

                if (lUserID == null || STRING_EMPTY.equals(lUserID)) {
                    return mapping.findForward(AppConstants.FAILURE);
                } else {
                    Vector paramFind = new Vector();
                    action = request.getParameter(REQUEST_ACTION);
                    if (action == null || STRING_EMPTY.equals(action)) {
                        action = REQUEST_ACTION;
                    }
                    String whereClauseFind = "";
                    //tim kiem
                    if (action != null &&
                        action.equalsIgnoreCase(AppConstants.ACTION_FIND)) {
                        paramFind = new Vector();
                        if (f.getTtv_nhan() != null &&
                            (!f.getTtv_nhan().equals("") &&
                             !f.getTtv_nhan().equals("null"))) {
                            whereClauseFind =
                                    whereClauseFind + " and (lower(d.ma_nsd) like (?) OR lower(d.ten_nsd) like(?)) ";
                            //                            whereClauseFind +=
                            //                                    "'%" + f.getTtv_nhan().toLowerCase().trim() +
                            //                                    "%')) ";
                            paramFind.add(new Parameter("%" +
                                                        f.getTtv_nhan().toLowerCase().trim() +
                                                        "%", true));
                            paramFind.add(new Parameter("%" +
                                                        f.getTtv_nhan().toLowerCase().trim() +
                                                        "%", true));
                        }
                        if (f.getId() != null &&
                            (!f.getId().equals("") && !f.getId().equals("null"))) {
                            whereClauseFind =
                                    whereClauseFind + " AND a.id like ?";
                            //                            whereClauseFind += "'%" + f.getId().trim() + "%' ";
                            //                            paramsFind.add(new Parameter("'%" + f.getId() + "%'",
                            //                                                         true));
                            paramFind.add(new Parameter("%" +
                                                        f.getId().trim() + "%",
                                                        true));
                        }
                        if (f.getNhkb_nhan() != null &&
                            (!STRING_EMPTY.equals(f.getNhkb_nhan()) &&
                             !f.getNhkb_nhan().equals("null"))) {
                            whereClauseFind =
                                    whereClauseFind + " and b.ma_nh like (?) ";
                            //                            whereClauseFind +=
                            //                                    "'%" + f.getNhkb_nhan().trim() + "%')";
                            //                            paramsFind.add(new Parameter("'%" +
                            //                                                         f.getNhkb_nhan() +
                            //                                                         "%'", true));
                            paramFind.add(new Parameter("%" +
                                                        f.getNhkb_nhan().trim() +
                                                        "%", true));
                        }
                    }
                    DTSoatDAO dtsDAO = new DTSoatDAO(conn);
                    String chucdanh_ttv = null;
                    String chucdanh_ktt = null;
                    if (strPhanQuyen != null) {
                        String[] arrAddChucDanh = strPhanQuyen.split("\\|");
                        for (int i = 0; i < arrAddChucDanh.length; i++) {
                            chucdanh = arrAddChucDanh[i];
                            if (chucdanh.equalsIgnoreCase(CD_KTT) ||
                                chucdanh.equalsIgnoreCase(CD_GD)) {
                                chucdanh_ktt = CD_KTT;
                                break;
                            } else if (chucdanh.equalsIgnoreCase(CD_TTV)) {
                                chucdanh_ttv = CD_TTV;
                            } else {
                                chucdanh_ktt = "CBPT-TTTT";
                                chucdanh_ttv = "CB-TTTT";
                            }
                        }
                    }
                    ArrayList lstSDTSTTV = null;
                    ArrayList lstSDTSKTT = null;
                    if (chucdanh_ktt != null && chucdanh_ttv != null) {
                        String whereClause = "";
                        params = new Vector();
                        if (action != null &&
                            action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL)) {
                            String id = request.getParameter("id");
                            whereClause += " and a.id=? AND f.rv_domain like 'SP_DTS.%' ";
                            params.add(new Parameter(id, true));
                            request.setAttribute("typeExit", true);
                            lstSDTS =
                                    (ArrayList)dtsDAO.getDTSList(whereClause, params);
                        } else {
                            if (lstSDTS == null || lstSDTS.size() != 0) {
//                              if(strPhanQuyen.equals("CB-TTTT")||strPhanQuyen.equals("CBPT-TTTT")){
//                                strWhereClause+=" and (a.nhkb_chuyen = ? or (a.ktt_duyet=? or a.ktt_duyet is null))  ";
//                              }else{
//                                    strWhereClause+=" and (a.nhkb_nhan = ? or (a.ktt_duyet=? or a.ktt_duyet is null))  ";
//                                  }
                                strWhereClause +=
                                        " AND f.rv_domain like 'SP_DTS.%' and decode(a.ngay_duyet,null,a.ngay_nhan,a.ngay_duyet) >= trunc(sysdate) " +
                                        " and a.nhkb_chuyen = ? and a.trang_thai != '00' ORDER BY   a.id desc,a.trang_thai, a.ngay_nhan DESC";
                                if (whereClauseFind != null &&
                                    !STRING_EMPTY.equals(whereClauseFind)) {
                                    whereClause +=
                                            whereClauseFind + " and " + strWhereClause +
                                            ",id desc";
                                } else {
                                    whereClause +=
                                            " and " + strWhereClause + ",id desc";
                                }
                                if (paramFind != null) {
                                    params.addAll(paramFind);
                                }
                                params.add(new Parameter(nhkb_chuyen_id,
                                                         true));
//                                params.add(new Parameter(lUserID, true));
                                lstSDTSKTT =
                                        (ArrayList)dtsDAO.getDTSList(strWhereClause,
                                                                     params);
                                request.setAttribute("typeExit", false);
                            }

                        }


                    } else if (chucdanh_ktt != null &&
                               chucdanh_ktt.equalsIgnoreCase(CD_KTT)) {
                        params = new Vector();
                        String strWhereClauseKTT = "";
                        if (action != null &&
                            action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL)) {
                            String id = request.getParameter("id");
                            strWhereClauseKTT += " and a.id=? AND f.rv_domain like 'SP_DTS.%' ";
                            params.add(new Parameter(id, true));
                            request.setAttribute("typeExit", true);
                            lstSDTS =
                                    (ArrayList)dtsDAO.getDTSList(strWhereClauseKTT,
                                                                 params);
                        } else {
                            if (lstSDTS == null || lstSDTS.size() != 0) {
                                strWhereClauseKTT =
                                        " and a.LOAI_DTS= 'N' AND f.rv_domain like 'SP_DTS.%' and (a.trang_thai  = '" +
                                        AppConstants.TRANG_THAI_CHO_DUYET +
                                        "' " + " OR a.trang_thai   = '" +
                                        AppConstants.TRANG_THAI_DAY_LAI +
                                        "' " + "OR ((a.trang_thai = '" +
                                        AppConstants.TRANG_THAI_DUYET + "' " +
                                        "OR a.trang_thai   = '" +
                                        AppConstants.TRANG_THAI_HUY + "') " +
                                        "AND decode(a.ngay_duyet,null,a.ngay_nhan,a.ngay_duyet) >= trunc(sysdate) )) ";
                                strWhereClauseKTT +=
                                        whereClauseFind + " and (a.ktt_duyet=? or a.ktt_duyet is null) and a.nhkb_chuyen = ? " +
                                        "ORDER BY f.rv_high_value asc,a.id,a.trang_thai,a.ngay_nhan desc ";
                                if (paramFind != null) {
                                    params.addAll(paramFind);
                                }
                                params.add(new Parameter(lUserID, true));
                                params.add(new Parameter(nhkb_chuyen_id,
                                                         true));
                                lstSDTSKTT =
                                        (ArrayList)dtsDAO.getDTSList(strWhereClauseKTT,
                                                                     params);
                                request.setAttribute("typeExit", false);
                            }
                        }

                    } else if (chucdanh_ttv != null &&
                               chucdanh_ttv.equalsIgnoreCase(CD_TTV)) {
                        params = new Vector();
                        String whereTTV = " ";

                        if (action != null &&
                            action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL)) {
                            String id = request.getParameter("id");
                            whereTTV += " and a.id=? AND f.rv_domain like 'SP_DTS.%'";
                            params.add(new Parameter(id, true));
                            request.setAttribute("typeExit", true);
                        } else {
                            request.setAttribute("typeExit", false);
                            strWhereClause +=
                                    " and a.ttv_nhan=? and a.nhkb_chuyen = ? AND f.rv_domain like 'SP_DTS.%' and (a.trang_thai = '" +
                                    AppConstants.TRANG_THAI_CHO_DUYET + "' " +
                                    "or a.trang_thai = '" +
                                    AppConstants.TRANG_THAI_DAY_LAI +
                                    "' or ((a.trang_thai = '" +
                                    AppConstants.TRANG_THAI_DUYET +
                                    "' or a.trang_thai  = '" +
                                    AppConstants.TRANG_THAI_HUY +
                                    "') and decode(a.ngay_duyet,null,a.ngay_nhan,a.ngay_duyet) >= trunc(sysdate)  )) ";
                            whereTTV +=
                                    strWhereClause + whereClauseFind + "order by a.trang_thai,a.id";
                            params.add(new Parameter(lUserID, true));
                          params.add(new Parameter(nhkb_chuyen_id,
                                                   true));
                            if (paramFind != null) {
                                params.addAll(paramFind);
                            }
                            request.setAttribute("typeExit", false);
                        }
                        lstSDTSTTV =
                                (ArrayList)dtsDAO.getDTSList(whereTTV, params);

                    }
                    // gan list
                    if (lstSDTS == null) {
                        if ((lstSDTSTTV != null && !lstSDTSTTV.isEmpty()) &&
                            (lstSDTSKTT != null && !lstSDTSKTT.isEmpty())) {
                            lstSDTSKTT.addAll(lstSDTSTTV);
                            lstSDTS = Collections.synchronizedList(lstSDTSKTT);
                        } else if (lstSDTSTTV != null &&
                                   lstSDTSTTV.size() > 0) {
                            lstSDTS = Collections.synchronizedList(lstSDTSTTV);
                        } else if (lstSDTSKTT != null &&
                                   lstSDTSKTT.size() > 0) {
                            lstSDTS = Collections.synchronizedList(lstSDTSKTT);
                        } else {
                            lstSDTS = new ArrayList();
                        }
                    }

                    // tiim va refresh
                    if (request.getAttribute("getJson") != null ||
                        (action != null &&
                         (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                          action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                        Type listType = new TypeToken<Collection<DTSoatVO>>() {
                        }.getType();
                        String strJson = new Gson().toJson(lstSDTS, listType);
                        response.setContentType(AppConstants.CONTENT_TYPE_JSON_UTF);
                        PrintWriter out = response.getWriter();
                        out.println(strJson.toString());
                        out.flush();
                        out.close();
                    }
                }
                //end
                request.setAttribute("lstSDTS", lstSDTS);
                request.setAttribute("chucdanh", strPhanQuyen);
                saveVisitLog(conn, session, DTSNBo, "");
            }
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = null;
        String strJson = null;
        try {
            if (!checkPermissionOnFunction(request, DTSNBO_THEM)) {
                mapping.findForward(PERMISSION_PAGE);
            } else {
                conn = getConnection();
                HttpSession session = request.getSession();
                jsonObj = new JsonObject();
                if (session != null) {
                    // don vi tra soat
                    Long lNHKB_id =
                        new Long(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString());
                    String strNHKB_code =
                        (String)session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION);
                    String strNHKB_name =
                        (String)session.getAttribute(AppConstants.APP_KB_NAME_SESSION);
                    String htkb_code =
                        String.valueOf(session.getAttribute(AppConstants.APP_KB_CODE_SESSION));
                    // User
                    Long lUserId =
                        new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                    String strUserName =
                        (String)session.getAttribute(AppConstants.APP_USER_NAME_SESSION);
                    // Time
                    String strCurrentDate = StringUtil.getCurrentDate();
                    String ma_ttv_nhan =
                        session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                    DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
                    int kbType = 0;
                    DMKBacVO dmkbVO = null;
                    String whereClause = " AND ma=?";
                    Vector paramkb = new Vector();
                    paramkb.add(new Parameter(htkb_code, true));
                    dmkbVO = dmkbDAO.getDMKB(whereClause, paramkb);
                    String strDelimiter = ",";
                    if (AppConstants.MA_TTTT.indexOf(strDelimiter) > -1) {
                        String[] strFound =
                            AppConstants.MA_TTTT.split(strDelimiter);
                        for (int i = 0; i < strFound.length; i++) {
                            if (dmkbVO.getMa().toString().equalsIgnoreCase(strFound[i])) {
                                kbType = 1;
                                break;
                            } else {
                                kbType = 2;
                            }
                        }
                    }
                    // kiem tra neu ngan hang kho bac chuyen khong phai la TTTT
                    // set gia tri cho ngan hang kho bac nhan la TTTT
                    if (kbType == 2) {
                        // set ngan hang kho bac nhan la TTTT
                        DMNHKBacDAO dmnhkbDAO = new DMNHKBacDAO(conn);
                        DMNHKBacVO dmnhkbVO = new DMNHKBacVO();
                        String strWhereClauseNHKB = "shkb = ?";
                        Vector params = new Vector();
                        params.add(new Parameter(TTTT, true));
                        dmnhkbVO =
                                dmnhkbDAO.getDMNHKBac(strWhereClauseNHKB, params);
                        // Ajax add properties
                        jsonObj.addProperty("nhkb_chuyen", lNHKB_id);
                        jsonObj.addProperty("nhkb_chuyen_ma", strNHKB_code);
                        jsonObj.addProperty("nhkb_chuyen_ten", strNHKB_name);
                        jsonObj.addProperty("user_id", lUserId);
                        jsonObj.addProperty("ten_ttv_nhan", strUserName);
                        jsonObj.addProperty("ngay_nhan", strCurrentDate);
                        jsonObj.addProperty("ma_ttv_nhan", ma_ttv_nhan);
                        if (dmnhkbVO != null) {
                            jsonObj.addProperty("nhkb_nhan",
                                                dmnhkbVO.getShkb());
                            jsonObj.addProperty("nhkb_nhan_code",
                                                dmnhkbVO.getMa_nh());
                            jsonObj.addProperty("nhkb_nhan_ten",
                                                dmnhkbVO.getTen());
                        } else {
                            jsonObj.addProperty("loi_add", "error");
                        }

                    } else {
                        // Ajax add properties
                        jsonObj.addProperty("nhkb_chuyen", lNHKB_id);
                        jsonObj.addProperty("nhkb_chuyen_ma", strNHKB_code);
                        jsonObj.addProperty("nhkb_chuyen_ten", strNHKB_name);
                        jsonObj.addProperty("user_id", lUserId);
                        jsonObj.addProperty("ten_ttv_nhan", strUserName);
                        jsonObj.addProperty("ngay_nhan", strCurrentDate);
                        jsonObj.addProperty("ma_ttv_nhan", ma_ttv_nhan);
                    }
                    //
                } else {
                    mapping.findForward(AppConstants.FAILURE);
                }
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception ex) {
//            ex.printStackTrace();
        } finally {
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter writer = response.getWriter();
            writer.println(strJson.toString());
            writer.flush();
            writer.close();
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = null;
        try {
            conn = getConnection();
            DTSoatDAO dtsDAO = new DTSoatDAO(conn);
            DTSoatForm dtsForm = (DTSoatForm)form;
            DTSoatVO vo = new DTSoatVO();
            HttpSession session = request.getSession();
            Long lUserId =
                (Long)session.getAttribute(AppConstants.APP_USER_ID_SESSION);
            // kiem tra ngan hang kho bac nhan va ngan hang kho bac chuyen
            // chi duoc phep chuyen noi bo tu kb den tttt va nguoc lai
            Long ldv_nhan_ts = null;
            Long ldv_chuyen_ts = null;
            if (dtsForm.getMa_don_vi_nhan_tra_soat() != null &&
                !STRING_EMPTY.equals(dtsForm.getMa_don_vi_nhan_tra_soat())) {
                String NHKB_Code = dtsForm.getMa_don_vi_nhan_tra_soat();
                DMNHKBacDAO dmnhkbDAO = new DMNHKBacDAO(conn);
                String strWhereClauseDMNH = "ma_nh = ?";
                Vector params = new Vector();
                params.add(new Parameter(NHKB_Code, true));
                DMNHKBacVO dmnhkbVO =
                    dmnhkbDAO.getDMNHKBac(strWhereClauseDMNH, params);
                // dmnhkbVO = null ==> la ngan hang
                if (dmnhkbVO == null) {
                    jsonObj.addProperty("sohieu_kb", FLAG_NH);
                    // return lai va thong bao ko duoc phep gui noi bo vi day la gui ra ngan hang
                } else {
                    jsonObj.addProperty("sohieu_kb", FLAG_KB);
                    // set id kho bac nhan
                    ldv_nhan_ts =
                            new DTSoatNBoAction().getMaDVKB(dtsForm.getMa_don_vi_nhan_tra_soat(),
                                                            conn);
                    ldv_chuyen_ts =
                            new DTSoatNBoAction().getMaDVKB(dtsForm.getMa_don_vi_tra_soat(),
                                                            conn);
                    if (ldv_nhan_ts == null ||
                        ldv_nhan_ts.equals(STRING_EMPTY)) {
                        // neu ldv_nhan_ts bang null ==> sai ma yeu cau nhap lai
                        jsonObj.addProperty("ID_DV_KBNhan_ERR",
                                            AppConstants.FAILURE);
                    }
                }
                // tim duoc so hieu kho bac
                // tim kiem den sp_dm_htkb co ban ghi ==> kho bac
                // co ma la 0002 la TTTT
            }
            // set gia tri vao vo
            if (ldv_chuyen_ts != null && ldv_nhan_ts != null &&
                dtsForm.getNoidung() != null && lUserId != null) {
                vo.setNhkb_chuyen(ldv_chuyen_ts);
                vo.setNhkb_nhan(ldv_nhan_ts);
                vo.setNoidung(dtsForm.getNoidung());
                vo.setTtv_nhan(lUserId);
                vo.setNgay_nhan(new StringUtil().getCurrentDate());

                vo.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
              String chuc_danh =
                      (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
              if(chuc_danh.equals("CB-TTTT")){
                vo.setLoai_dts("TW");
              }else{
                vo.setLoai_dts(LOAI_DTS);
              }
                
                //end
                String strCheck = dtsDAO.insert(vo, "399");
                jsonObj.addProperty("id_dts", strCheck);
                if (strCheck.equalsIgnoreCase("-1")) {
                    return mapping.findForward(AppConstants.FAILURE);
                } else {
                    saveVisitLog(conn, session, DTSNBO_THEM, "");
                    conn.commit();
                }
            }
        } finally {
            strJson = jsonObj.toString();
            if (strJson != null) {
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter writer = response.getWriter();
                writer.println(strJson.toString());
                writer.flush();
                writer.close();
            }
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
        Collection colDMNH = null;
        DTSoatVO dtsoatVO = null;
        String whereClause = "";
        Vector params = null;
        DMNHangVO dmNHVO = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        UserVO userVO = null;
        Connection conn = null;
        String strMsgEx = "";
        boolean bCheck = false;
        try {
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            conn = getConnection();
            DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
            String action = request.getParameter(REQUEST_ACTION);
            if (action != null && !"".equalsIgnoreCase(action)) {
                //Lay trang thai truoc khi update
                if (action.equalsIgnoreCase(AppConstants.ACTION_FILL_DATA_TO_FORM)) {
                    whereClause = " AND  a.id=?";
                    String id = request.getParameter("id");
                    if (id != null && !id.equals("")) {
                        params = new Vector();
                        params.add(new Parameter(id, true));
                        dtsoatVO = dtsoatDAO.getDTS(whereClause, params);
                        if (dtsoatVO != null) {
                            // lay ten ttv va ktt
                            if (dtsoatVO.getTtv_nhan() != null) {
                                UserDAO userDAO = new UserDAO(conn);
                                String strwhereClauseUser = " a.id = ?";
                                params = new Vector();
                                params.add(new Parameter(dtsoatVO.getTtv_nhan(),
                                                         true));
                                userVO =
                                        userDAO.getUser(strwhereClauseUser, params);
                                dtsoatVO.setTen_ttv_nhan(userVO.getTen_nsd());
                            }
                            if (dtsoatVO.getKtt_duyet() != null) {
                                UserDAO userDAO = new UserDAO(conn);
                                String strwhereClauseUser = " a.id = ?";
                                params = new Vector();
                                params.add(new Parameter(dtsoatVO.getKtt_duyet(),
                                                         true));
                                userVO =
                                        userDAO.getUser(strwhereClauseUser, params);
                                dtsoatVO.setTen_ktt_duyet(userVO.getTen_nsd());
                            }
                            // end
                            //lay ten ma danh muc ngan hang
                            whereClause = " id in(?,?) ";
                            params = new Vector();
                            params.add(new Parameter(dtsoatVO.getNhkb_chuyen(),
                                                     true));
                            params.add(new Parameter(dtsoatVO.getNhkb_nhan(),
                                                     true));
                            DMNHangDAO dmNHangDAO = new DMNHangDAO(conn);
                            colDMNH =
                                    dmNHangDAO.getDMNHList(whereClause, params);
                            if (colDMNH != null && colDMNH.size() > 0) {
                                for (Iterator iter = colDMNH.iterator();
                                     iter.hasNext(); ) {
                                    dmNHVO = (DMNHangVO)iter.next();
                                    // kiem tra null
                                    if (dmNHVO != null) {
                                        if (dtsoatVO.getNhkb_chuyen() !=
                                            null &&
                                            dtsoatVO.getNhkb_nhan() != null) {
                                            bCheck = true;
                                            if (dtsoatVO.getNhkb_chuyen().toString().equals(dtsoatVO.getNhkb_nhan().toString())) {
                                                dtsoatVO.setMa_don_vi_tra_soat(dmNHVO.getMa_nh());
                                                dtsoatVO.setTen_don_vi_tra_soat(dmNHVO.getTen());
                                                dtsoatVO.setMa_don_vi_nhan_tra_soat(dmNHVO.getMa_nh());
                                                dtsoatVO.setTen_don_vi_nhan_tra_soat(dmNHVO.getTen());
                                            } else {
                                                if (dtsoatVO.getNhkb_chuyen().toString().trim().equalsIgnoreCase(dmNHVO.getId().toString())) {
                                                    dtsoatVO.setMa_don_vi_tra_soat(dmNHVO.getMa_nh());
                                                    dtsoatVO.setTen_don_vi_tra_soat(dmNHVO.getTen());
                                                } else {
                                                    dtsoatVO.setMa_don_vi_nhan_tra_soat(dmNHVO.getMa_nh());
                                                    dtsoatVO.setTen_don_vi_nhan_tra_soat(dmNHVO.getTen());
                                                }
                                            }
                                        } else {
                                            jsonObj.addProperty("exception_message",
                                                                "uncorrect");
                                            strJson = jsonObj.toString();
                                        }
                                    }
                                }
                            }
                        }
                        if (bCheck) {
                            Vector vParam = new Vector();
                            vParam.add(new Parameter(dtsoatVO.getTrang_thai(),
                                                     true));
                            String strWhereClauseMTC =
                                " rv_domain = 'SP_DTS.TRANG_THAI' and rv_low_value = ?";
                            MaThamChieuDAO matcDAO = new MaThamChieuDAO(conn);
                            MaThamChieuVO matcVO =
                                matcDAO.getMaThamChieuByKey(strWhereClauseMTC,
                                                            vParam);
                            HttpSession session = request.getSession();
                            String strPhanQuyen =
                                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                            dtsoatVO.setChuc_danh(strPhanQuyen);
                            dtsoatVO.setTrang_thai(matcVO.getRv_low_value());
                            dtsoatVO.setMo_ta_trang_thai(matcVO.getRv_meaning());
                            Type listType = new TypeToken<DTSoatVO>() {
                            }.getType();
                            strJson = new Gson().toJson(dtsoatVO, listType);
                        }
                    }
                } else if (action.equalsIgnoreCase("fillNHKB")) {
                    DMNHangDAO dmNHangDAO = new DMNHangDAO(conn);
                    if (dtsoatForm.getMa_don_vi_nhan_tra_soat() != null ||
                        dtsoatForm.getMa_don_vi_nhan_tra_soat().trim() != "") {
                        String strWhereClause = "a.ma_nh = ?";
                        Parameter param =
                            new Parameter(dtsoatForm.getMa_don_vi_nhan_tra_soat(),
                                          true);
                        params = new Vector();
                        params.add(param);
                        dmNHVO = dmNHangDAO.getDMNH(strWhereClause, params);
                        jsonObj = new JsonObject();
                        jsonObj.addProperty("ten_don_vi_nhan_tra_soat",
                                            dmNHVO.getTen());
                        jsonObj.addProperty("ma_nh", dmNHVO.getMa_nh());
                        strJson = jsonObj.toString();
                    }

                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            strMsgEx = e.getMessage();
            jsonObj.addProperty("exception_message", strMsgEx);
        } finally {
            close(conn);
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

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Connection conn = null;
        DTSoatVO vo = null;
        DTSoatForm dtsForm;
        int i = 0;
        String chucdanh = "";
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        String strActionStatus = "FAILURE";
        String strMsgEx = "";
        try {
            dtsForm = (DTSoatForm)form;
            conn = getConnection();
            DTSoatDAO dtsDao = new DTSoatDAO(conn);


            HttpSession session = request.getSession();
            //String strTempState = getStateBU(request, response, conn);
            Long nUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            String strUsername =
                session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString().trim();
            String strDomain =
                session.getAttribute(AppConstants.APP_DOMAIN_SESSION).toString().trim();
            String strPhanQuyen =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
          String strNHKB_id =
              session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
            String[] arrPhanQuyen = strPhanQuyen.split("\\|");
            String ngay_duyet = StringUtil.getCurrentDate();
            for (int count = 0; count < arrPhanQuyen.length; count++) {
                chucdanh = arrPhanQuyen[count];
                if (chucdanh != null && !STRING_EMPTY.equals(chucdanh)) {
                    String action = request.getParameter(REQUEST_ACTION);
                    // split chuc danh - cl
                    if (chucdanh.equalsIgnoreCase(AppConstants.NSD_TTV)) {
                        if (!checkPermissionOnFunction(request, DTSNBO_SUA)) {
                            return mapping.findForward("errorQuyen");
                        }
                        if (action != null &&
                            AppConstants.ACTION_CANCEL.equalsIgnoreCase(action)) {
                            if (dtsForm.getTrang_thai().equalsIgnoreCase(AppConstants.TRANG_THAI_CHO_DUYET)) {
                                i = dtsDao.deleteDTS(dtsForm.getId());
                                saveVisitLog(conn, session, DTSNBO_XOA, "");
                            } else {
                                saveVisitLog(conn, session, DTSNBO_XOA, "");
                                vo = new DTSoatVO();
                                vo.setTrang_thai(AppConstants.TRANG_THAI_HUY);
                                vo.setId(dtsForm.getId());
                                i = dtsDao.update(vo);

                            }
                        } else if (action != null &&
                                   AppConstants.ACTION_EDIT.equals(action)) {
                            saveVisitLog(conn, session, DTSNBO_SUA, "");
                            vo = new DTSoatVO();
                            Long ma_dv_nhan_ts =
                                new XuLyDTSoatTuDoAction().getKBID(dtsForm.getMa_don_vi_nhan_tra_soat(),
                                                                   conn);
                            vo.setId(dtsForm.getId());
                            vo.setNoidung(dtsForm.getNoidung());
                            vo.setNhkb_nhan(ma_dv_nhan_ts);
                            vo.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
                            // kiem tra trang thai truoc khi update
                            //Select for update
                            String whereClause = "where a.id = ?";
                            Vector params = new Vector();
                            params.add(new Parameter(dtsForm.getId(), true));
                            DTSoatVO dtsVOForUpdate =
                                dtsDao.getDTSForUpdate(whereClause, params);
                            if (dtsVOForUpdate != null) {
                                i = dtsDao.update(vo);
                            }
                        }
                    } else if (chucdanh.equalsIgnoreCase("CB-TTTT")) {
                        if (!checkPermissionOnFunction(request, DTSNBO_SUA)) {
                            return mapping.findForward("errorQuyen");
                        }
                        if (action != null &&
                            AppConstants.ACTION_CANCEL.equalsIgnoreCase(action)) {
                            if (dtsForm.getTrang_thai().equalsIgnoreCase(AppConstants.TRANG_THAI_CHO_DUYET)) {
                                i = dtsDao.deleteDTS(dtsForm.getId());
                                saveVisitLog(conn, session, DTSNBO_XOA, "");
                            } else {
                                saveVisitLog(conn, session, DTSNBO_XOA, "");
                                vo = new DTSoatVO();
                                vo.setTrang_thai(AppConstants.TRANG_THAI_HUY);
                                vo.setId(dtsForm.getId());
                                i = dtsDao.update(vo);

                            }
                        } else if (action != null &&
                                   AppConstants.ACTION_EDIT.equals(action)) {
                            saveVisitLog(conn, session, DTSNBO_SUA, "");
                            vo = new DTSoatVO();
                            Long ma_dv_nhan_ts =
                                new XuLyDTSoatTuDoAction().getKBID(dtsForm.getMa_don_vi_nhan_tra_soat(),
                                                                   conn);
                            vo.setId(dtsForm.getId());
                            vo.setNoidung(dtsForm.getNoidung());
                            vo.setNhkb_nhan(ma_dv_nhan_ts);
                            vo.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
                            // kiem tra trang thai truoc khi update
                            //Select for update
                            String whereClause = "where a.id = ?";
                            Vector params = new Vector();
                            params.add(new Parameter(dtsForm.getId(), true));
                            DTSoatVO dtsVOForUpdate =
                                dtsDao.getDTSForUpdate(whereClause, params);
                            if (dtsVOForUpdate != null) {
                                i = dtsDao.update(vo);
                            }
                        }
                    } else if (chucdanh.equalsIgnoreCase(AppConstants.NSD_KTT)) {
                        if (!checkPermissionOnFunction(request,
                                                       DTSNBO_DUYET)) {
                            return mapping.findForward("errorQuyen");
                        }
                        vo = new DTSoatVO();
                        vo.setId((dtsForm.getId() != null &&
                                  !dtsForm.getId().equals(STRING_EMPTY)) ?
                                 dtsForm.getId() : null);
                        if (action != null &&
                            action.equalsIgnoreCase(AppConstants.ACTION_APPROVED)) {
                            saveVisitLog(conn, session, DTSNBO_DUYET, "DUYET");
                            vo.setKtt_duyet(nUserID);
                            vo.setNgay_duyet(ngay_duyet);
                            vo.setTrang_thai(AppConstants.TRANG_THAI_DUYET);
                            //ky duyet
                          String strWSDL = getThamSoHThong("WSDL_PKI", session);
                          PKIService pkiService = new PKIService(strWSDL);
                            String certSerial =
                                request.getParameter("certSerial");
                            String strSign = request.getParameter("signature");

                            //Noi dung ky
                            String contenData = dtsForm.getNoidung().trim();
                            //Kiem tra chu ky
                            //                            String[] VerifySignature =
                            //                                pki.VerifySignature(strDomain + "\\" +
                            //                                                    strUsername, certSerial,
                            //                                                    contenData, strSign);
                            //
                            //                            jsonObj.addProperty("verifySign",
                            //                                                VerifySignature[0]);
                            //                            jsonObj.addProperty("verifySign_dtl",
                            //                                                VerifySignature[1]);
                            //                            if (!VerifySignature[0].equalsIgnoreCase("VALID")) {
                            //                                return null;
                            //                            }
                            //Select for update
                            String whereClause = "where a.id = ?";
                            Vector params = new Vector();
                            params.add(new Parameter(dtsForm.getId(), true));
                            DTSoatVO dtsVOForUpdate =
                                dtsDao.getDTSForUpdate(whereClause, params);
                            
                            long lTTV = dtsVOForUpdate.getTtv_nhan();
                            long lUser = nUserID.longValue();
                            if(lTTV == lUser){
                              throw new Exception("Khong duoc phep vua tao vua duyet mot DTS");
                            }
                            
                            if (AppConstants.TRANG_THAI_CHO_DUYET.equals(dtsVOForUpdate.getTrang_thai())) {
                                //Update trang thai DTS -> Da gui
                                //Send DTS to HTTH
                                Date dNgayDuyet = new Date();

                                Collection colThamSoHT =
                                    getThamSoHThong(session);
                                SendDTS sendDTS = new SendDTS(conn);
                                DTSoatVO voTemp =
                                    TTSPUtils.getDTSById(vo.getId(),
                                                         AppConstants.STATE_DTS_LTT_DI,
                                                         conn);
                                BeanUtils.copyProperties(dtsForm, voTemp);
                                // build strMsgID
                                dtsForm.setNgay_duyet(StringUtil.DateToString(dNgayDuyet,
                                                                              AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
                                dtsForm.setKtt_duyet(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());

                                String strMsgID = "";
                                //                                String strMsgID =
                                //                                    sendDTS.sendDTSToBank(dtsForm, colThamSoHT);
                                //                                String strMsgID = "";
                                //add msg_id and sign into vo
                                vo.setNgay_duyet(StringUtil.DateToString(dNgayDuyet,
                                                                         DATE_TIME_FORMAT));
                                vo.setMsg_id(strMsgID);
                                vo.setChuky_ktt(strSign);
                                i = dtsDao.update(vo);
                                if (i > 0) {
                                    // inset new row voi dts len tw 
                                    String strTChieu= " AND id=" + dtsForm.getId();
                                    String  so_tchieu= dtsDao.getSo_TChieu(strTChieu, null);
                                    if("".equals(so_tchieu)||so_tchieu==null){
                                    vo.setNhkb_chuyen(Long.parseLong(strNHKB_id)); //
                                    vo.setNhkb_nhan(Long.parseLong(dtsForm.getNhkb_nhan()));//
                                    vo.setNoidung(dtsForm.getNoidung());//
                                    vo.setNgay_nhan(new StringUtil().getCurrentDate());
                                    vo.setSo_tchieu(dtsForm.getId());
                                    vo.setTrang_thai("00");
                                    vo.setLoai_dts("TW");                              
                                    //end
                                     dtsDao.insert(vo, "399");
                                    }
                                    
                                    strActionStatus = "SUCCESS";
                                }
                            }
                            jsonObj.addProperty("action_status",
                                                strActionStatus);

                        } else if (action != null &&
                                   action.equalsIgnoreCase(AppConstants.ACTION_REJECT)) {
                            saveVisitLog(conn, session, DTSNBO_DUYET,
                                         "day lai");
                            vo.setKtt_duyet(nUserID);
                            vo.setNgay_duyet(ngay_duyet);
                            vo.setLydo_ktt_day_lai(dtsForm.getLydo_ktt_day_lai());
                            vo.setTrang_thai(AppConstants.TRANG_THAI_DAY_LAI);
                            //Select for update
                            String whereClause = "where a.id = ?";
                            Vector params = new Vector();
                            params.add(new Parameter(dtsForm.getId(), true));
                            DTSoatVO dtsVOForUpdate =
                                dtsDao.getDTSForUpdate(whereClause, params);
                            
                            if (AppConstants.TRANG_THAI_CHO_DUYET.equals(dtsVOForUpdate.getTrang_thai())) {
                                //Update trang thai DTS -> Da gui
                                i = dtsDao.update(vo);
                            }
                        }
                        break;
                    }else if (chucdanh.equalsIgnoreCase("CBPT-TTTT")) {
                        if (!checkPermissionOnFunction(request,
                                                       DTSNBO_DUYET)) {
                            return mapping.findForward("errorQuyen");
                        }
                        vo = new DTSoatVO();
                        vo.setId((dtsForm.getId() != null &&
                                  !dtsForm.getId().equals(STRING_EMPTY)) ?
                                 dtsForm.getId() : null);
                        if (action != null &&
                            action.equalsIgnoreCase(AppConstants.ACTION_APPROVED)) {
                            saveVisitLog(conn, session, DTSNBO_DUYET, "DUYET");
                            vo.setKtt_duyet(nUserID);
                            vo.setNgay_duyet(ngay_duyet);
                            vo.setTrang_thai(AppConstants.TRANG_THAI_DUYET);
                            //ky duyet
                          String strWSDL = getThamSoHThong("WSDL_PKI", session);
                          PKIService pkiService = new PKIService(strWSDL);
                            String certSerial =
                                request.getParameter("certSerial");
                            String strSign = request.getParameter("signature");

                            //Noi dung ky
                            String contenData = dtsForm.getNoidung().trim();
                            //Select for update
                            String whereClause = "where a.id = ?";
                            Vector params = new Vector();
                            params.add(new Parameter(dtsForm.getId(), true));
                            DTSoatVO dtsVOForUpdate =
                                dtsDao.getDTSForUpdate(whereClause, params);
                            if (AppConstants.TRANG_THAI_CHO_DUYET.equals(dtsVOForUpdate.getTrang_thai())) {
                                //Update trang thai DTS -> Da gui
                                //Send DTS to HTTH
                                Date dNgayDuyet = new Date();

                                Collection colThamSoHT =
                                    getThamSoHThong(session);
                                SendDTS sendDTS = new SendDTS(conn);
                                DTSoatVO voTemp =
                                    TTSPUtils.getDTSById(vo.getId(),
                                                         AppConstants.STATE_DTS_LTT_DI,
                                                         conn);
                                BeanUtils.copyProperties(dtsForm, voTemp);
                                // build strMsgID
                                dtsForm.setNgay_duyet(StringUtil.DateToString(dNgayDuyet,
                                                                              AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
                                dtsForm.setKtt_duyet(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());

                                String strMsgID = "";
                                //add msg_id and sign into vo
                                vo.setNgay_duyet(StringUtil.DateToString(dNgayDuyet,
                                                                         DATE_TIME_FORMAT));
                                vo.setMsg_id(strMsgID);
                                vo.setChuky_ktt(strSign);
                                i = dtsDao.update(vo);
                                if (i > 0) {
                                    // inset new row voi dts len tw                                   
                                  String strTChieu= " AND id=" + dtsForm.getId();
                                  String  so_tchieu= dtsDao.getSo_TChieu(strTChieu, null);
                                  if("".equals(so_tchieu)||so_tchieu==null){
                                  vo.setNhkb_chuyen(Long.parseLong(strNHKB_id)); //
                                  vo.setNhkb_nhan(Long.parseLong(dtsForm.getNhkb_nhan()));//
                                  vo.setNoidung(dtsForm.getNoidung());//
                                  vo.setNgay_nhan(new StringUtil().getCurrentDate());
                                  vo.setSo_tchieu(dtsForm.getId());
                                  vo.setTrang_thai("00");
                                  vo.setLoai_dts("N");                              
                                  //end
                                   dtsDao.insert(vo, "399");
                                  }
                                    
                                    
                                    strActionStatus = "SUCCESS";
                                }
                            }
                            jsonObj.addProperty("action_status",
                                                strActionStatus);

                        } else if (action != null &&
                                   action.equalsIgnoreCase(AppConstants.ACTION_REJECT)) {
                            saveVisitLog(conn, session, DTSNBO_DUYET,
                                         "day lai");
                            vo.setKtt_duyet(nUserID);
                            vo.setNgay_duyet(ngay_duyet);
                            vo.setLydo_ktt_day_lai(dtsForm.getLydo_ktt_day_lai());
                            vo.setTrang_thai(AppConstants.TRANG_THAI_DAY_LAI);
                            //Select for update
                            String whereClause = "where a.id = ?";
                            Vector params = new Vector();
                            params.add(new Parameter(dtsForm.getId(), true));
                            DTSoatVO dtsVOForUpdate =
                                dtsDao.getDTSForUpdate(whereClause, params);
                            if (AppConstants.TRANG_THAI_CHO_DUYET.equals(dtsVOForUpdate.getTrang_thai())) {
                                //Update trang thai DTS -> Da gui
                                i = dtsDao.update(vo);
                            }
                        }
                        break;
                    }
                }
            }
            conn.commit();
            request.setAttribute("typeExit", false);
        } catch (Exception ex) {
//            ex.printStackTrace();
            strMsgEx = ex.getMessage();
            jsonObj.addProperty("exception_message", strMsgEx);
        } finally {
            jsonObj.addProperty("sohieu_kb", "uncorrect");
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
            close(conn);
        }
        request.setAttribute("exception_message", strMsgEx);
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public DMNHKBacVO checkValidTTTT(String codeKB,
                                     Connection conn) throws Exception {
        String whereClauseKB = null;
        Vector paramsKB = new Vector();
        DMNHKBacVO nhkbVO = new DMNHKBacVO();
        DMNHKBacDAO nhkbDAO = new DMNHKBacDAO(conn);
        whereClauseKB = "a.ma_nh = ?";
        paramsKB.add(new Parameter(codeKB, true));
        nhkbVO = nhkbDAO.getDMNHKBac(whereClauseKB, paramsKB);
        return nhkbVO;
    }

    public Long getMaDVKB(String maDVTS, Connection conn) throws Exception {
        Long id = null;
        String strWhereClause = " ma_nh = ?";
        Vector params = new Vector();
        params.add(new Parameter(maDVTS, true));
        DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
        DMNHangVO vo = dmnhDAO.getDMNH(strWhereClause, params);
        if (vo != null) {
            id = vo.getId();
        }
        return id;
    }


}
