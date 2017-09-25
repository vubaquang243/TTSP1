package com.seatech.ttsp.dts.action;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
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

import java.io.InputStream;
import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class XuLyDTSoatTuDoAction extends AppAction {
    private static String FAILURE = "failure";
    private static final String STRING_EMPTY = "";
    private final String CHUC_DANH_TTV = "TTV";
    private final String REQUEST_ACTION = "action";
    private final String CHUC_DANH_KTT = "KTT";
    private final String CHUC_DANH_GD = "GD";
    private final String TRANG_THAI = "STATE";
    private static String DTS_DI_TUDO = "DTS.DI.TUDO";
    public static final String DATE_FORMAT_NOW = "MM/dd/yyyy HH:mm:ss";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        Connection conn = null;
        List lstSDTS = null;
        String strWhereClause = null;
        String strWhereClauseKTT = "";
        Vector vParam = null;
        String chucDanh = null;
        String strPhanQuyen = null;
        String action = null;
        Vector paramFind = null;
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        action =
                request.getParameter(REQUEST_ACTION) != null ? request.getParameter(REQUEST_ACTION) :
                "";
        if (action != null &&
            !action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_TUDO) &&
            !action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4)) {
            if (!checkPermissionOnFunction(request, "DTS.DI.TUDO")) {
                return mapping.findForward("errorQuyen");
            }
        }
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            Long lUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            String nhkb_chuyen_id =
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION) !=
                null ?
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString() :
                "";
            if (lUserID == null) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            // khai bao form
            DTSoatForm f = (DTSoatForm)form;

            if (action == null || STRING_EMPTY.equals(action)) {
                action = REQUEST_ACTION;
            }
            if (session != null) {
                strPhanQuyen =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);

                paramFind = new Vector();
                vParam = new Vector();
                String whereClauseFind = "";
                // dieu kien select DTS tu do
                strWhereClause =
                        " and substr(a.id,6,3) = '199' AND f.rv_domain like 'SP_DTS%' ";
                //                            " LOAI_DTS = 'K' and LTT_ID is null and DTS_ID is null ";

                //tim kiem
                if (action != null &&
                    action.equalsIgnoreCase(AppConstants.ACTION_FIND)) {
                    paramFind = new Vector();
                    if (f.getTtv_nhan() != null &&
                        (!f.getTtv_nhan().equals("") &&
                         !f.getTtv_nhan().equals("null"))) {
                        whereClauseFind =
                                whereClauseFind + " and (lower(d.ma_nsd) like lower(?) OR lower(d.ten_nsd) like lower(?)) ";
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
                        whereClauseFind = whereClauseFind + " AND a.id like ?";
                        //                            whereClauseFind += "'%" + f.getId().trim() + "%' ";
                        //                            paramsFind.add(new Parameter("'%" + f.getId() + "%'",
                        //                                                         true));
                        paramFind.add(new Parameter("%" + f.getId().trim() +
                                                    "%", true));
                    }
                    if (f.getNhkb_nhan() != null &&
                        (!STRING_EMPTY.equals(f.getNhkb_nhan()) &&
                         !f.getNhkb_nhan().equals("null"))) {
                        whereClauseFind =
                                whereClauseFind + " AND c.ma_nh like (?) ";
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

                /*
                           * TTV
                           * TThai = 01,03,04
                           * order by time desc
                           * */
                String chucdanh_ttv = null;
                String chucdanh_ktt = null;
                if (strPhanQuyen != null) {
                    String[] arrAddChucDanh = strPhanQuyen.split("\\|");
                    for (int i = 0; i < arrAddChucDanh.length; i++) {
                        chucDanh = arrAddChucDanh[i];
                        if (chucDanh.equalsIgnoreCase(CHUC_DANH_KTT) ||
                            chucDanh.equalsIgnoreCase(CHUC_DANH_GD)) {
                            chucdanh_ktt = CHUC_DANH_KTT;
                            break;
                        } else if (chucDanh.equalsIgnoreCase(CHUC_DANH_TTV)) {
                            chucdanh_ttv = CHUC_DANH_TTV;
                        }
                    }
                }
                ArrayList lstSDTSTTV = null;
                ArrayList lstSDTSKTT = null;
                if (chucdanh_ttv != null && chucdanh_ktt != null) {
//                    strWhereClauseKTT =
//                            " and instr(a.id,199) = 6 AND f.rv_domain  like 'SP_DTS%' and (a.trang_thai='" +
//                            AppConstants.TRANG_THAI_CHO_DUYET +
//                            "' or a.trang_thai='" +
//                            AppConstants.TRANG_THAI_DAY_LAI + "'" +
//                            " or a.trang_thai='" +
//                            AppConstants.TRANG_THAI_DUYET +
//                            "' or a.trang_thai='" +
//                            AppConstants.TRANG_THAI_HUY + "')";
                    vParam = new Vector();
                    // chi tiet dien tra soat tu do
                    if (action != null &&
                        action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_TUDO)) {
                        String id = request.getParameter("id");
                        strWhereClauseKTT += " and a.id=? ";
                        vParam.add(new Parameter(id, true));
                        request.setAttribute("typeExit", "true");
                    } else {
                        request.setAttribute("typeExit", "false");
//                        strWhereClauseKTT +=
//                      " and instr(a.id,199) = 6 AND f.rv_domain like 'SP_DTS%' ";
                      strWhereClauseKTT += 
                      " and instr(a.id,199) = 6 AND f.rv_domain  like 'SP_DTS%'" +
                      " and  a.nhkb_chuyen = " + nhkb_chuyen_id +" "+
                      "AND (((a.trang_thai ='01' AND (a.ttv_nhan is null or a.ttv_nhan = "+lUserID+")) " +
                      "or (a.trang_thai ='02' AND (a.ktt_duyet is null or a.ktt_duyet = "+lUserID+"))) " +
                      "OR ((a.ktt_duyet = "+lUserID+" OR a.ttv_nhan = "+lUserID+")" +
                      " and (((a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)  " +
                      " and         a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)) " +
                      " and         SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)        )  " +
                      " or        ((a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)   " +
                      " and         a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)) " +
                      " and         SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))))) " +
                      "ORDER BY a.trang_thai, a.id desc";
//                        strWhereClauseKTT +=
//                                whereClauseFind + " and  a.nhkb_chuyen =?  " +
//                                " ORDER BY a.trang_thai, a.id desc ";

//                        if (paramFind != null) {
//                            vParam.addAll(paramFind);
//                        }
//                        vParam.add(new Parameter(nhkb_chuyen_id, true));
                    }

                    // end
                    DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                    lstSDTSKTT =
                            (ArrayList)dtsoatDAO.getDTSList(strWhereClauseKTT,
                                                            null);
                } else if (chucdanh_ttv != null &&
                           chucdanh_ttv.equalsIgnoreCase(CHUC_DANH_TTV)) {
                    vParam = new Vector();
                    if (action != null &&
                        (action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_TUDO) ||
                         action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL))) {
                        String id = request.getParameter("id");
                        strWhereClause += " and a.id=? ";
                        vParam.add(new Parameter(id, true));
                        request.setAttribute("typeExit", "true");
                    } else {
                        request.setAttribute("typeExit", "false");
                        strWhereClause +=
                                " and a.ttv_nhan=? AND ((a.trang_thai = '" +
                                AppConstants.TRANG_THAI_CHO_DUYET + "' " +
                                " OR a.trang_thai = '" +
                                AppConstants.TRANG_THAI_DAY_LAI +
                                "') OR ((a.trang_thai = '" +
                                AppConstants.TRANG_THAI_DUYET + "' " +
                                " OR a.trang_thai = '" +
                                AppConstants.TRANG_THAI_HUY + "' ) ";
                        strWhereClause +=
                                " and (" + "        ((a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                "        and " +
                                "        a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                                "        and " +
                                "        SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                "        )" + "        or" +
                                "        ((a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                "        and " +
                                "        a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                                "        and " +
                                "        SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                                "        )" + "      )))";
                        strWhereClause = strWhereClause + whereClauseFind;
                        vParam.add(new Parameter(session.getAttribute("id_nsd"),
                                                 true));
                        if (paramFind != null) {
                            vParam.addAll(paramFind);
                        }
                        // chi tiet dien tra soat tu do

                        strWhereClause += " ORDER BY a.trang_thai,a.id";
                    }

                    // end
                    DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                    lstSDTSTTV =
                            (ArrayList)dtsoatDAO.getDTSList(strWhereClause,
                                                            vParam);
                }
                /*
                         * KTT
                         * TThai = 01
                         * order by time desc
                         * */
                else if (chucdanh_ktt != null &&
                         chucdanh_ktt.equalsIgnoreCase(CHUC_DANH_KTT)) {
                    strWhereClauseKTT =
                            " and instr(a.id,199) = 6 AND f.rv_domain like 'SP_DTS%' ";
                    strWhereClauseKTT +=
                            " and ((a.trang_thai = '" + AppConstants.TRANG_THAI_CHO_DUYET +
                            "' " + " OR a.trang_thai = '" +
                            AppConstants.TRANG_THAI_DAY_LAI +
                            "') OR ((a.trang_thai = '" +
                            AppConstants.TRANG_THAI_DUYET + "' " +
                            " OR a.trang_thai = '" +
                            AppConstants.TRANG_THAI_HUY + "' ) ";
                    strWhereClauseKTT +=
                            " and (" + "        ((a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        and " +
                            "        a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                            "        and " +
                            "        SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        )" + "        or" +
                            "        ((a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        and " +
                            "        a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))" +
                            "        and " +
                            "        SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        )" + "      )))";

                    vParam = new Vector();
                    DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                    // chi tiet dien tra soat tu do
                    if (action != null &&
                        (action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_TUDO) ||
                         action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL))) {
                        String id = request.getParameter("id");
                        strWhereClauseKTT =
                                " and a.id=? and f.rv_domain like 'SP_DTS%'";
                        vParam.add(new Parameter(id, true));
                        lstSDTSKTT =
                                (ArrayList)dtsoatDAO.getDTSList(strWhereClauseKTT,
                                                                vParam);
                    } else {
                        strWhereClauseKTT +=
                                whereClauseFind + " and (a.ktt_duyet=? or a.ktt_duyet is null) and a.nhkb_chuyen =? " +
                                " ORDER BY f.rv_high_value asc , a.ngay_nhan desc ";
                        if (paramFind != null) {
                            vParam.addAll(paramFind);
                        }
                        vParam.add(new Parameter(session.getAttribute("id_nsd"),
                                                 true));
                        vParam.add(new Parameter(nhkb_chuyen_id, true));

                        // end
                        lstSDTSKTT =
                                (ArrayList)dtsoatDAO.getDTSList(strWhereClauseKTT,
                                                                vParam);
                    }

                } else {
                    if (action != null &&
                        action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4)) {
                        String id = request.getParameter("id");
                        strWhereClause =
                                " and a.id=? and f.rv_domain like 'SP_DTS%'";
                        vParam.add(new Parameter(id, true));
                    }
                    // end
                    DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                    lstSDTSTTV =
                            (ArrayList)dtsoatDAO.getDTSList(strWhereClause,
                                                            vParam);
                }
                // gan list
                if (lstSDTSTTV != null && lstSDTSKTT != null) {
                    lstSDTSKTT.addAll(lstSDTSTTV);
                    lstSDTS = Collections.synchronizedList(lstSDTSKTT);
                } else if (lstSDTSTTV != null && lstSDTSTTV.size() > 0) {
                    lstSDTS = Collections.synchronizedList(lstSDTSTTV);
                } else if (lstSDTSKTT != null && lstSDTSKTT.size() > 0) {
                    lstSDTS = Collections.synchronizedList(lstSDTSKTT);
                } else {
                    lstSDTS = new ArrayList();
                }

                // ds ngan hang

                String strKBacID = "";
                if (session.getAttribute("id_kb") != null) {
                    strKBacID = session.getAttribute("id_kb").toString();
                }
                Collection listDMNH = new ArrayList();
                DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
                //Lay ra danh sach cac ngan hang ma kho bac mo tai khoan
                String strWhere = " and c.id = ?";
                Vector vtParam = new Vector();
                vtParam.add(new Parameter(strKBacID, true));


                listDMNH = dmnhDAO.getDMNHListByKBId(strWhere, vtParam);

                DMNHangVO dmnhVO = null;
                for (Iterator it = listDMNH.iterator(); it.hasNext(); ) {
                    dmnhVO = (DMNHangVO)it.next();
                    if (dmnhVO != null)
                        break;
                }
                request.setAttribute("colDMNH", listDMNH);

                // end
            } else {
                return mapping.findForward(AppConstants.FAILURE);
            }
            //response danh sach dien tra soat
            if (request.getAttribute("getJson") != null ||
                (action != null && (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                                    action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                Type listType = new TypeToken<Collection<DTSoatVO>>() {
                }.getType();
                String strJson = new Gson().toJson(lstSDTS, listType);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON_UTF);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
            } else {
                request.setAttribute("lstSDTS", lstSDTS);
                request.setAttribute("chucdanh", strPhanQuyen);
                if (action != null &&
                    (action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_TUDO) ||
                     action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL))) {
                    request.setAttribute("typeExit", "true");
                    if (lstSDTS != null) {
                        for (Iterator iter = lstSDTS.iterator();
                             iter.hasNext(); ) {
                            DTSoatVO dtsoatVO = new DTSoatVO();
                            dtsoatVO = (DTSoatVO)iter.next();
                            BeanUtils.copyProperties(lstSDTS, dtsoatVO);
                            Collection listDMNH = new ArrayList();
                            DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
                            //Lay ra danh sach cac ngan hang ma kho bac mo tai khoan
                            vParam = new Vector();
                            vParam.add(new Parameter(dtsoatVO.getNhkb_nhan().toString(),
                                                     true));
                            // get list ds ngan hang
                            String strWhereClauseIdNhkb = "id = ? ";
                            listDMNH =
                                    dmnhDAO.getDMNHList(strWhereClauseIdNhkb,
                                                        vParam);
                            String strMa_nhkb_chuyen = null;
                            DMNHangVO dmnhVO = null;
                            vParam = new Vector();
                            vParam.add(new Parameter(dtsoatVO.getNhkb_chuyen().toString(),
                                                     true));
                            dmnhVO =
                                    dmnhDAO.getDMNH(strWhereClauseIdNhkb, vParam);
                            //set gia tri cho nhkb chuyen, noi dung, ngay nhan
                            f.setMa_don_vi_tra_soat(dmnhVO.getMa_nh());
                            f.setTen_don_vi_tra_soat(dmnhVO.getTen());
                            f.setNoidung(dtsoatVO.getNoidung());
                            f.setNgay_nhan(dtsoatVO.getNgay_nhan());
                            //set ten TTV
                            UserDAO userDAO = new UserDAO(conn);
                            String strwhereClauseUser = " a.id = ?";
                            vParam = new Vector();
                            vParam.add(new Parameter(dtsoatVO.getTtv_nhan(),
                                                     true));
                            UserVO userVO =
                                userDAO.getUser(strwhereClauseUser, vParam);
                            //end
                            f.setTtv_nhan(userVO.getTen_nsd());
                            // kiem tra ktt va ngay duyet
                            if (dtsoatVO.getKtt_duyet() != null &&
                                !STRING_EMPTY.equals(dtsoatVO.getKtt_duyet())) {
                                userVO = null;
                                vParam = new Vector();
                                vParam.add(new Parameter(dtsoatVO.getKtt_duyet(),
                                                         true));
                                userVO =
                                        userDAO.getUser(strwhereClauseUser, vParam);
                                f.setKtt_duyet(userVO.getTen_nsd());
                                f.setNgay_duyet(dtsoatVO.getNgay_duyet());
                            }
                            if (dtsoatVO.getLydo_ktt_day_lai() != null &&
                                !STRING_EMPTY.equals(dtsoatVO.getLydo_ktt_day_lai())) {
                                f.setLydo_ktt_day_lai(dtsoatVO.getLydo_ktt_day_lai());
                            }
                            //set mo ta trang thai
                            if (dtsoatVO.getTrang_thai() != null) {
                                String strWhereClauseMTC = " rv_low_value = ?";
                                vParam = new Vector();
                                vParam.add(new Parameter(dtsoatVO.getTrang_thai(),
                                                         true));
                                MaThamChieuDAO matcDAO =
                                    new MaThamChieuDAO(conn);
                                MaThamChieuVO matcVO =
                                    matcDAO.getMaThamChieuByKey(strWhereClauseMTC,
                                                                vParam);
                                f.setMo_ta_trang_thai(matcVO.getRv_meaning());
                            }
                            //end
                            dmnhVO = null;
                            for (Iterator it = listDMNH.iterator();
                                 it.hasNext(); ) {
                                dmnhVO = (DMNHangVO)it.next();
                                String ten_nhkb_nhan = dmnhVO.getTen();
                                //set gia tri cho ten nhkb nhan
                                f.setTen_don_vi_nhan_tra_soat(ten_nhkb_nhan);
                                //end
                                if (dmnhVO != null)
                                    break;
                            }
                            // set ma nh cho dv nh kb nhan
                            request.setAttribute("colDMNH", listDMNH);
                        }
                    }
                } else if (action != null &&
                           action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4)) {
                    request.setAttribute("typeExit", "T4");
                } else {
                    request.setAttribute("typeExit", "false");
                }
            }
            saveVisitLog(conn, session, DTS_DI_TUDO, "");

        } catch (Exception e) {
            if (request.getAttribute("getJson") != null ||
                (action != null && (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                                    action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                response.reset();
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty("error", e.getMessage());
                out.println(jsonObj.toString());
                out.flush();
                out.close();
            }else{
              throw e;
            }
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }


    protected String getChucDanh(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Connection conn) {
        String whereClause = "";
        Vector params = null;
        UserVO userVO = null;
        try {

            String maNSD =
                (String)request.getSession().getAttribute(AppConstants.APP_USER_CODE_SESSION);
            //lay chuc danh nguoi sd
            UserDAO userDAO = new UserDAO(conn);
            whereClause = " a.ma_nsd=?";
            params = new Vector();
            params.add(new Parameter(maNSD, true));
            userVO = userDAO.getUser(whereClause, params);
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        return userVO != null ? userVO.getChuc_danh() : null;
    }

    protected String getStateBU(HttpServletRequest request, Connection conn) {
        DTSoatVO dtsoatVO = null;
        try {
            DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
            dtsoatVO = dtsoatDAO.findByPK(request.getParameter("id"));
            return (dtsoatVO != null) ? dtsoatVO.getTrang_thai() : "";
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        return "";
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = null;
        String strJson = null;
        String strMsgEx = "";
        Collection colDMNH = null;
        Gson gson = null;
        /*
       * Set cac gia tri cho form
       * don vi tra soat + ten don vi tra soat
       * nguoi lap + ngay lap
       * */
        try {
            if (!checkPermissionOnFunction(request, "DTS.DI.TUDO.THEM")) {
                return mapping.findForward("errorQuyen");
            } else {
                conn = getConnection();
                HttpSession session = request.getSession();
                if (session != null) {
                    Long strNHKB_id =
                        new Long(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString());
                    Long strKB_id =
                        new Long(session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString());
                    // ma don vi tra soat
                    String strNHKB_code =
                        (String)session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION);
                    // ten don vi tra soat
                    String strNHKB_name =
                        (String)session.getAttribute(AppConstants.APP_KB_NAME_SESSION);
                    // id user
                    Long lUserID =
                        new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                    // ten user
                    String strUsername =
                        session.getAttribute(AppConstants.APP_USER_NAME_SESSION).toString();
                    // gio hien tai
                    String strCurrentTime = StringUtil.getCurrentDate();
                    String ma_ttv_nhan =
                        session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                    // Set gia tri vao property
                    DTSoatVO dtsVO = new DTSoatVO();
                    dtsVO.setNhkb_chuyen(strNHKB_id);
                    dtsVO.setTen_don_vi_tra_soat(strNHKB_name);
                    dtsVO.setMa_don_vi_tra_soat(strNHKB_code);
                    dtsVO.setTen_ttv_nhan(strUsername);
                    dtsVO.setNgay_nhan(strCurrentTime);
                    dtsVO.setMa_ttv_nhan(ma_ttv_nhan);
                    jsonObj = new JsonObject();
                    //                    jsonObj.addProperty("nhkb_chuyen", strNHKB_id);
                    //                    jsonObj.addProperty("nhkb_chuyen_ma", strNHKB_code);
                    //                    jsonObj.addProperty("nhkb_chuyen_ten", strNHKB_name);
                    //                    jsonObj.addProperty("user_id", lUserID);
                    //                    jsonObj.addProperty("ten_ttv_nhan", strUsername);
                    //                    jsonObj.addProperty("ngay_nhan", strCurrentTime);
                    //                    jsonObj.addProperty("ma_ttv_nhan", ma_ttv_nhan);
                    //lay ten ma danh muc ngan hang

                    DMNHangDAO dmNHangDAO = new DMNHangDAO(conn);

                    String strWhere =
                        " and c.id = ? and b.loai_tk = ? and b.trang_thai = ?";
                    Vector vtParam = new Vector();
                    vtParam.add(new Parameter(strKB_id, true));
                    vtParam.add(new Parameter("02", true));
                    vtParam.add(new Parameter("01", true));

                    colDMNH = dmNHangDAO.getDMNHListByKBId(strWhere, vtParam);

                    Type listType = new TypeToken<DTSoatVO>() {
                    }.getType();
                    gson = new GsonBuilder().setVersion(1.0).create();
                    strJson = gson.toJson(dtsVO, listType);
                    jsonObj.addProperty("master", strJson);

                    Type listTypeDMNH = new TypeToken<Collection>() {
                    }.getType();
                    //                  strJson = new Gson().toJson(listDMNH, listTypeDMNH);
                    //                  jsonObj.addProperty("dmnhNhan", strJson);

                    gson = new GsonBuilder().
                            //.registerTypeAdapter(Vector.class, new JsonVectorAdapter())
                            setVersion(1.1).create();
                    strJson = gson.toJson(colDMNH, listTypeDMNH);
                    jsonObj.addProperty("dmnhNhan", strJson);

                    JsonArray jsonArr = new JsonArray();
                    JsonElement jsonEle = jsonObj.get("master");
                    jsonArr.add(jsonEle);
                    JsonElement jsonEle1 = jsonObj.get("dmnhNhan");
                    jsonArr.add(jsonEle1);
                    response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                    PrintWriter out = response.getWriter();
                    out.println(jsonArr.getAsJsonArray().toString());
//                    out.println(strJson.toString());
                    out.flush();
                    out.close();
                } else {
                    return mapping.findForward(FAILURE);
                }
            }

        } catch (Exception e) {
//            e.printStackTrace();
            strMsgEx = e.getMessage();
            jsonObj.addProperty("exception_message", strMsgEx);
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public Long getKBID(String NHKBcode, Connection conn) throws Exception {
        Long id = null;
        String strWhereClause = "a.ma_nh = ?";
        Vector params = new Vector();
        params.add(new Parameter(NHKBcode, true));
        DMNHangVO dmnhVO = new DMNHangVO();
        DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
        dmnhVO = dmnhDAO.getDMNH(strWhereClause, params);
        id = dmnhVO.getId();
        return id;
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = null;
        try {
            if (!checkPermissionOnFunction(request, "DTS.DI.TUDO.THEM")) {
                return mapping.findForward("errorQuyen");
            }
            conn = getConnection();
            DTSoatDAO dtsDAO = new DTSoatDAO(conn);
            DTSoatForm dtsForm = (DTSoatForm)form;
            HttpSession session = request.getSession();
            Long nUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            if (session != null) {
                if (dtsForm.getMa_don_vi_nhan_tra_soat() != null &&
                    !STRING_EMPTY.equals(dtsForm.getMa_don_vi_nhan_tra_soat())) {
                    String NHKB_Nhan_Code =
                        dtsForm.getMa_don_vi_nhan_tra_soat();
                    String NHKB_Chuyen_Code = dtsForm.getMa_don_vi_tra_soat();
                    /* kiem tra kho bac
                     * KIEM TRA HOP LE NEU LA CHUYEN TOI NGAN HANG
                     * DMNHKBVO null
                     */
                    Vector params = null;
                    DMNHKBacDAO dmnhkbacDAO = new DMNHKBacDAO(conn);
                    String strWhereClauseDMNHKB = "ma_nh = ?";
                    params = new Vector();
                    params.add(new Parameter(NHKB_Chuyen_Code, true));
                    DMNHKBacVO dmnhkbChuyenVO =
                        dmnhkbacDAO.getDMNHKBac(strWhereClauseDMNHKB, params);
                    // kho bac nhan
                    params = new Vector();
                    params.add(new Parameter(NHKB_Nhan_Code, true));
                    DMNHKBacVO dmnhkbVO =
                        dmnhkbacDAO.getDMNHKBac(strWhereClauseDMNHKB, params);
                    /* truong hop 1 :
                     * Tu ngan hang chuyen den kho bac
                     * dmnhkbVO = null ==> ngan hang
                     * dmnhkbChuyenVO != null ==> kho bac
                    */
                    if ((dmnhkbChuyenVO != null && dmnhkbVO == null) ||
                        (dmnhkbChuyenVO == null && dmnhkbVO != null)) {
                        // id KB chuyen
                        Long NHKB_chuyen_id =
                            new Long(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString());
                        // id KB chuyen

                        Long NHKB_Chuyen_id =
                            new XuLyDTSoatTuDoAction().getKBID(NHKB_Chuyen_Code,
                                                               conn);
                        // id KB Nhan
                        Long NHKB_Nhan_id =
                            new XuLyDTSoatTuDoAction().getKBID(NHKB_Nhan_Code,
                                                               conn);
                        // id user
                        Long lUserID =
                            new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());

                        // gio hien tai
                        String strCurrentTime = StringUtil.getCurrentDate();
                        // noi dung
                        String noidung = dtsForm.getNoidung();
                        // insert action
                        DTSoatVO dtsVO = new DTSoatVO();
                        dtsVO.setNhkb_nhan(NHKB_Nhan_id);
                        dtsVO.setNhkb_chuyen(NHKB_chuyen_id);
                        dtsVO.setNoidung(noidung);
                        dtsVO.setTtv_nhan(lUserID);
                        dtsVO.setNgay_nhan(strCurrentTime);
                        dtsVO.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
                        dtsVO.setLoai_dts("K");
                        String strCheck = dtsDAO.insert(dtsVO, "199");
                        jsonObj.addProperty("id_dts", strCheck);
                        if (strCheck.equalsIgnoreCase("-1")) {
                            return mapping.findForward(FAILURE);
                        } else {
                            saveVisitLog(conn, session, "DTS.DI.TUDO.THEM",
                                         "");
                            conn.commit();
                            //                            request.setAttribute("getJson", true);
                            //                            list(mapping, form, request, response);
                        }
                        jsonObj.addProperty("sohieu_kb", "");

                    } else {
                        jsonObj.addProperty("sohieu_kb", "uncorrect");
                    }
                    strJson = jsonObj.toString();
                    response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                    PrintWriter out = response.getWriter();
                    out.println(strJson.toString());
                    out.flush();
                    out.close();
                }
            }
        } catch (Exception e) {
            conn.rollback();
//            e.printStackTrace();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            jsonObj.addProperty("ERROR", e.getMessage());
            out.println(jsonObj.toString());
            out.flush();
            out.close();
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
        DTSoatVO vo = null;
        DTSoatForm dtsForm;
        int i = 0;
        JsonObject jsonObj = new JsonObject();
        String strJson = null;
        String chucdanh = "";
        String strActionStatus = "FAILURE";
        String strMsgEx = "";
        try {
            dtsForm = (DTSoatForm)form;
            conn = getConnection();
            DTSoatDAO dtsDao = new DTSoatDAO(conn);
            HttpSession session = request.getSession();
            if (session != null) {
                chucdanh =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            }
            String chucdanh_ttv = null;
            String chucdanh_ktt = null;
            String chucdanh_gd = null;
            if (chucdanh != null) {
                String[] arrAddChucDanh = chucdanh.split("\\|");
                for (int count = 0; count < arrAddChucDanh.length; count++) {
                    String strchucDanh = arrAddChucDanh[count];
                    if (strchucDanh.equalsIgnoreCase(CHUC_DANH_KTT)) {
                        chucdanh_ktt = CHUC_DANH_KTT;
                    } else if (strchucDanh.equalsIgnoreCase(CHUC_DANH_TTV)) {
                        chucdanh_ttv = CHUC_DANH_TTV;
                    } else if (strchucDanh.equalsIgnoreCase(CHUC_DANH_GD)) {
                        chucdanh_gd = CHUC_DANH_GD;
                    }
                }
            }
            //Get NSD_ID from session
            Long nUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            String strUsername =
                session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString().trim();
            String strDomain =
                session.getAttribute(AppConstants.APP_DOMAIN_SESSION).toString().trim();
            if (chucdanh != null && !STRING_EMPTY.equals(chucdanh)) {
                String action = request.getParameter(REQUEST_ACTION);
                if (chucdanh_ttv != null ||
                    !STRING_EMPTY.equals(chucdanh_ttv)) {
                    if (AppConstants.NSD_TTV.equalsIgnoreCase(chucdanh_ttv)) {
                        if (!checkPermissionOnFunction(request,
                                                       "DTS.DI.TUDO.SUA")) {
                            return mapping.findForward("errorQuyen");
                        }
                        if (action != null &&
                            AppConstants.ACTION_CANCEL.equalsIgnoreCase(action)) {
                            //Ghi log truy cap chuc nang
                            saveVisitLog(conn, session, "DTS.DI.TUDO.HUY", "");
                            vo = new DTSoatVO();
                            vo.setTrang_thai(AppConstants.TRANG_THAI_HUY);
                            vo.setId(dtsForm.getId());
                            i = dtsDao.update(vo);

                            //                            }
                        } else if (action != null &&
                                   AppConstants.ACTION_EDIT.equals(action)) {
                            //Ghi log truy cap chuc nang
                            saveVisitLog(conn, session, "DTS.DI.TUDO.SUA", "");

                            vo = new DTSoatVO();
                            Long ma_dv_nhan_ts =
                                new XuLyDTSoatTuDoAction().getKBID(dtsForm.getMa_don_vi_nhan_tra_soat(),
                                                                   conn);
                            vo.setId(dtsForm.getId());
                            vo.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
                            vo.setNoidung(dtsForm.getNoidung());
                            vo.setNhkb_nhan(ma_dv_nhan_ts);
                            // kiem tra trang thai truoc khi update
                            //Select for update
                            String whereClause = "where a.id = ?";
                            Vector params = new Vector();
                            params.add(new Parameter(dtsForm.getId(), true));
                            DTSoatVO dtsVOForUpdate =
                                dtsDao.getDTSForUpdate(whereClause, params);
                            if (AppConstants.TRANG_THAI_DAY_LAI.equals(dtsVOForUpdate.getTrang_thai())) {
                                i = dtsDao.update(vo);
                            }
                        }
                    }
                }
                //Kiem tra NSD co quyen KTT ko?
                if (chucdanh_ktt != null ||
                    !STRING_EMPTY.equals(chucdanh_ktt)) {
                    String strNgayDuyet =
                        StringUtil.DateToString(new Date(), AppConstants.DATE_TIME_FORMAT_SEND_ORDER);
                    //                    String strNgayDuyetUpdate =
                    //                        StringUtil.DateToString(new Date(),
                    //                                                "dd/MM/yyyy HH:mm:ss");
                    if (AppConstants.NSD_KTT.equalsIgnoreCase(chucdanh_ktt)) {
                        if (!checkPermissionOnFunction(request,
                                                       "DTS.DI.TUDO.DUYET")) {
                            return mapping.findForward("errorQuyen");
                        }
                        String ngay_duyet = StringUtil.getCurrentDate();
                        vo = new DTSoatVO();
                        vo.setId((dtsForm.getId() != null &&
                                  !dtsForm.getId().equals(STRING_EMPTY)) ?
                                 dtsForm.getId() : null);
                        if (action != null &&
                            action.equalsIgnoreCase(AppConstants.ACTION_APPROVED)) {
                            //Ghi log truy cap chuc nang
                            saveVisitLog(conn, session, "DTS.DI.TUDO.DUYET",
                                         "duyet");
                            vo.setKtt_duyet(nUserID);
                            vo.setNgay_duyet(strNgayDuyet);
                            vo.setTrang_thai(AppConstants.TRANG_THAI_DUYET);
                            //ky duyet
                            String strWSDL =
                                getThamSoHThong("WSDL_PKI", session);
                            String strAppID =
                                getThamSoHThong("APP_ID", session);
                            PKIService pkiService = new PKIService(strWSDL);
                            String certSerial =
                                request.getParameter("certSerial");
                            String strSign = request.getParameter("signature");

                            //Noi dung ky
                            String contenData =
                                request.getParameter("noi_dung_ky");
                            //Kiem tra chu ky
                            String[] VerifySignature =
                                pkiService.VerifySignature(strDomain + "\\" +
                                                           strUsername,
                                                           certSerial,
                                                           contenData, strSign,
                                                           strAppID);

                            jsonObj.addProperty("verifySign",
                                                VerifySignature[0]);
                            jsonObj.addProperty("verifySign_dtl",
                                                VerifySignature[1]);
                            if (!VerifySignature[0].equalsIgnoreCase("VALID")) {
                                return null;
                            }
                            //Select for update
                            String whereClause = "where a.id = ?";
                            Vector params = new Vector();
                            params.add(new Parameter(dtsForm.getId(), true));
                            DTSoatVO dtsVOForUpdate =
                                dtsDao.getDTSForUpdate(whereClause, params);
                            //**************************************************
                            long lTTV = dtsVOForUpdate.getTtv_nhan();
                            if(lTTV == nUserID.longValue()){
                                throw new Exception("Khong cho phep vua lap vua duyet mot DTS");
                            }
                            //**************************************************
                            if (AppConstants.TRANG_THAI_CHO_DUYET.equals(dtsVOForUpdate.getTrang_thai())) {


                                //Send DTS to HTTH

                                Collection colThamSoHT =
                                    getThamSoHThong(session);
                                SendDTS sendDTS = new SendDTS(conn);
                                DTSoatVO voTemp =
                                    TTSPUtils.getDTSById(vo.getId(),
                                                         AppConstants.STATE_DTS_LTT_DI,
                                                         conn);
                                BeanUtils.copyProperties(dtsForm, voTemp);

                                dtsForm.setKtt_duyet(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());
                                dtsForm.setNgay_duyet(strNgayDuyet);
                                String strMsgID =
                                    sendDTS.sendDTSToBank(dtsForm, colThamSoHT,
                                                          strNgayDuyet);
                                //                                String strMsgID = "";
                                //add msg_id and sign into vo
                                vo.setChuky_ktt(strSign);
                                vo.setNgay_duyet(strNgayDuyet);
                                vo.setMsg_id(strMsgID);
                                vo.setChuky_ktt(strSign);
                                //Update trang thai DTS -> Da gui
                                i = dtsDao.update(vo);
                                if (i > 0) {
                                    strActionStatus = "SUCCESS";
                                }
                            }
                            jsonObj.addProperty("action_status",
                                                strActionStatus);
                        } else if (action != null &&
                                   AppConstants.ACTION_REJECT.equalsIgnoreCase(action)) {
                            //Ghi log truy cap
                            saveVisitLog(conn, session, "DTS.DI.TUDO.DUYET",
                                         "Day lai");

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
                            //**************************************************
                            long lTTV = dtsVOForUpdate.getTtv_nhan();
                            if(lTTV == nUserID.longValue()){
                                throw new Exception("Khong cho phep vua lap vua duyet mot DTS");
                            }
                            //**************************************************
                            if (AppConstants.TRANG_THAI_CHO_DUYET.equals(dtsVOForUpdate.getTrang_thai())) {
                                //Update trang thai DTS -> Da gui
                                i = dtsDao.update(vo);
                              if (i > 0) {
                                  strActionStatus = "SUCCESS";
                              }
                            }
                            jsonObj.addProperty("action_status",
                                              strActionStatus);
                        }

                    }
                }

            }
            conn.commit();
            request.setAttribute("getJson", true);

        } catch (Exception ex) {
//            ex.printStackTrace();
            strMsgEx = ex.getMessage();
            throw ex;
        } finally {
            jsonObj.addProperty("sohieu_kb", "uncorrect");
            jsonObj.addProperty("exception_message", strMsgEx);
            strJson = jsonObj.toString();
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
        try {
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            conn = getConnection();
            DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
            String action = request.getParameter(REQUEST_ACTION);
            if (action != null && !"".equalsIgnoreCase(action)) {
                //Lay trang thai truoc khi update
                if (action.equalsIgnoreCase("checkStateBU")) {
                    jsonObj.addProperty(TRANG_THAI, getStateBU(request, conn));
                    strJson = jsonObj.toString();
                    //chon row ben so dien tra soat day thong tin form
                } else if (action.equalsIgnoreCase(AppConstants.ACTION_FILL_DATA_TO_FORM)) {
                    whereClause = " AND  a.id=?";
                    String id = request.getParameter("id");
                    if (id != null && !id.equals("")) {
                        params = new Vector();
                        params.add(new Parameter(id, true));
                        dtsoatVO =
                                dtsoatDAO.getDTSTuDoByKey(whereClause, params);
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
                                // lay ra ten nguoi su dung
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
                                }
                            }
                        }
                        Vector vParam = new Vector();
                        vParam.add(new Parameter(dtsoatVO.getTrang_thai(),
                                                 true));
                        String strWhereClauseMTC =
                            " rv_domain like 'SP_DTS%' and rv_low_value = ?";
                        MaThamChieuDAO matcDAO = new MaThamChieuDAO(conn);
                        MaThamChieuVO matcVO =
                            matcDAO.getMaThamChieuByKey(strWhereClauseMTC,
                                                        vParam);
                        HttpSession session = request.getSession();
                        String strPhanQuyen =
                            (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);

                        if (strPhanQuyen.contains(AppConstants.NSD_KTT)) {
                            TTSPUtils spUtil = new TTSPUtils(conn);
                            String strNoiDungKy =
                                spUtil.getNoiDungKy(id, "199", "Y");
                            dtsoatVO.setNoi_dung_ky(strNoiDungKy);
                        }

                        dtsoatVO.setChuc_danh(strPhanQuyen);
                        dtsoatVO.setMo_ta_trang_thai(matcVO.getRv_meaning());
                        dtsoatVO.setTrang_thai(matcVO.getRv_low_value());
                        Type listType = new TypeToken<DTSoatVO>() {
                        }.getType();
                        strJson = new Gson().toJson(dtsoatVO, listType);
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
                        jsonObj.addProperty("ten_don_vi_tra_soat",
                                            dmNHVO.getTen());
                        jsonObj.addProperty("ma_nh", dmNHVO.getMa_nh());
                        strJson = jsonObj.toString();
                    }

                }
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                out.println(strJson.toString());
                out.flush();
                out.close();
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
            strMsgEx = ex.getMessage();
        } finally {
            close(conn);
            jsonObj.addProperty("exception_message", strMsgEx);
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

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        String typeLTT = "";
        Connection conn = null;
        String reportName = null;
        InputStream reportStream = null;
        JasperPrint jasperPrint = null;
        HashMap parameterMap = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            conn = getConnection();
            DTSoatForm dtsForm = (DTSoatForm)form;
            DTSoatDAO dtsDAO = new DTSoatDAO(conn);
            String whereClause = " and a.id=? ";
            Vector params = new Vector();
            params.add(new Parameter(dtsForm.getId(), true));
            DTSoatVO dtsVO = dtsDAO.getDTS(whereClause, params);
            if (dtsVO != null) {
                if (dtsVO.getId().substring(2, 5).equalsIgnoreCase("701")) {
                    typeLTT = "DI";
                } else {
                    typeLTT = "DEN";
                }
                // kiem tra lan in
                if (dtsVO.getTt_in() == null || 0 == dtsVO.getTt_in()) {
                    DTSoatVO dtsVOForUpdate = new DTSoatVO();
                    dtsVOForUpdate.setId(dtsVO.getId());
                    dtsVOForUpdate.setTt_in(new Long("1"));
                    dtsDAO.update(dtsVOForUpdate);
                    conn.commit();
                }
                // thuc hien truyen tham so in bao cao
                sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                                 dtsForm.getId() + "\" id=\"id\"></input>");
                reportName = "BCDTSHoi";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    AppConstants.REPORT_DIRECTORY +
                    AppConstants.FONT_FOR_REPORT;
                parameterMap = new HashMap();
                parameterMap.put("P_ID", dtsVO.getId());
                parameterMap.put("Type_LTT", typeLTT);
                parameterMap.put("REPORT_LOCALE",
                                 new java.util.Locale("vi", "VI"));
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                                "/" +
                                                                                                reportName +
                                                                                                ".jasper");
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);
                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "XuLyDTSoatTuDoRpt.do";
                String strParameter = "";

                ReportUtility rpUtilites = new ReportUtility();
                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, reportName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);

            } else {
                throw new Exception("Kh&#244;ng t&#236;m &#273;&#432;&#7907;c ch&#7913;ng t&#7915;!");
            }
        } catch (Exception e) {
            //throw (new TTSPException()).createException("", "");
//            e.printStackTrace();
            throw e;
        } finally {
            if (reportStream != null)
                reportStream.close();
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}

