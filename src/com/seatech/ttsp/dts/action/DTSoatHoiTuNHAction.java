package com.seatech.ttsp.dts.action;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;
import com.seatech.ttsp.dts.form.DTSoatForm;

import java.io.InputStream;
import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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


public class DTSoatHoiTuNHAction extends AppAction {
    String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public DTSoatHoiTuNHAction() {
        super();
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
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        String action = "";
        Connection conn = null;
        Collection colDTSoatLTTDi = null;
        Vector params = new Vector();
        String strListRole = "";
        String whereClause = "";
        boolean flag = true;
        try {
            conn = getConnection(request);
            action =
                    request.getParameter(AppConstants.REQUEST_ACTION) != null ?
                    request.getParameter(AppConstants.REQUEST_ACTION) : "";
            if (!action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL) &&
                !action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4)) {
                if (!checkPermissionOnFunction(request, "DTS.DEN.NHTM_HOI")) {
                    return mapping.findForward("errorQuyen");
                }
            }
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            HttpSession session = request.getSession();

            if (session != null) {
                if (conn == null || conn.isClosed())
                    conn = getConnection(request);
                strListRole =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                Vector paramsFind = new Vector();
                params = new Vector();
                String whereClauseFind = "";
                //dk tiem kiem
                if (action != null &&
                    action.equalsIgnoreCase(AppConstants.ACTION_FIND)) {
                    if (dtsoatForm.getId() != null &&
                        (!dtsoatForm.getId().equals("") &&
                         !dtsoatForm.getId().equals("null"))) {
                        whereClauseFind += " AND a.id like (?)";
                        paramsFind.add(new Parameter("%" + dtsoatForm.getId() +
                                                     "%", true));
                    }
                    if (dtsoatForm.getLtt_id() != null &&
                        (!dtsoatForm.getLtt_id().equals("") &&
                         !dtsoatForm.getLtt_id().equals("null"))) {
                        whereClauseFind += " AND a.ltt_id like (?)";
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getLtt_id() +
                                                     "%", true));
                    }
                    if (dtsoatForm.getMa_don_vi_nhan_tra_soat() != null &&
                        (!dtsoatForm.getMa_don_vi_nhan_tra_soat().equals("") &&
                         !dtsoatForm.getMa_don_vi_nhan_tra_soat().equals("null"))) {
                        whereClauseFind += " AND c.ma_nh like (?) ";
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getMa_don_vi_nhan_tra_soat() +
                                                     "%", true));
                    }
                    if (dtsoatForm.getMa_ttv_nhan() != null &&
                        (!dtsoatForm.getMa_ttv_nhan().equals("") &&
                         !dtsoatForm.getMa_ttv_nhan().equals("null"))) {
                        whereClauseFind +=
                                " AND (d.ma_nsd like (?) OR lower(d.ten_nsd) like(?)) ";
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getMa_ttv_nhan() +
                                                     "%", true));
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getMa_ttv_nhan().toLowerCase() +
                                                     "%", true));
                    }
                } else if (action != null &&
                           ((action.equalsIgnoreCase(AppConstants.ACTION_REFRESH) &&
                             (dtsoatForm.getId() != null &&
                              !"".equals(dtsoatForm.getId()))))) {
                    flag = false;
                }
                //                boolean bCheckCOT = false;
                //                String strCutOfTime =
                //                    getThamSoHThong("CUT_OF_TIME", session); //16:30:00
                //                if (!LTTCommon.isCurTimeLessThanCutOfTime(strCutOfTime.trim())) {
                //                    //                  lon hon thi + 1 them 1
                //                    bCheckCOT = true;
                //                }
                if ((strListRole.toUpperCase().indexOf(AppConstants.NSD_TTV) >=
                     0 &&
                     strListRole.toUpperCase().indexOf(AppConstants.NSD_KTT) ==
                     -1)) {
                    whereClause = " and (a.ttv_nhan=? OR a.ttv_nhan is null)";
                    whereClause += " AND a.nhkb_nhan =?";
                    whereClause +=
                            " AND (substr(a.id,6,3) = '195' or substr(a.id,6,3) = '199')  AND f.rv_domain like '%SP_DTS%' ";
                    //                    if (bCheckCOT) {
                    whereClause +=
                            " AND " + " (" + "  (" + "    (      " + "      (" +
                            "        (" + "          (" +
                            "          a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)" +
                            "          )" + "        )" +
                            "        and SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "      )" + "      or" + "      (" + "        (" +
                            "          (" +
                            "          a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)" +
                            "          )" + "        )" +
                            "        and SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "      )" + "    )  " + "    AND " +
                            "    (a.trang_thai = '12' OR a.trang_thai = '13' or a.trang_thai='16')" +
                            "  )  " + "  OR " +
                            "  (a.trang_thai = '11' OR a.trang_thai = '10') " +
                            ") ";
                    //                    } else {
                    //                        whereClause +=
                    //                                " AND (((" + " (a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                    //                                "  and a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh))" +
                    //                                "  or (a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                    //                                "  and a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)))" +
                    //                                "  AND (a.trang_thai = '12' OR a.trang_thai = '13' or a.trang_thai='16'))" +
                    //                                "  OR (a.trang_thai = '11' OR a.trang_thai = '10')" +
                    //                                " )";
                    //                    }

                    whereClause = whereClause + whereClauseFind;
                    params.add(new Parameter(session.getAttribute(AppConstants.APP_USER_ID_SESSION.toString()),
                                             true));
                    params.add(new Parameter(request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                             true));
                    params.addAll(paramsFind);
                    whereClause += " ORDER BY a.trang_thai,a.ngay_nhap_nh ASC";
                } else if ((strListRole.toUpperCase().indexOf(AppConstants.NSD_TTV) ==
                            -1 &&
                            strListRole.toUpperCase().indexOf(AppConstants.NSD_KTT) >=
                            0)) {

                    whereClause +=
                            " and a.nhkb_nhan =? and (a.ktt_duyet = ? or a.ktt_duyet is null)";
                    whereClause +=
                            " AND (substr(a.id,6,3) = '195' or substr(a.id,6,3) = '199') AND f.rv_domain like '%SP_DTS%' ";
                    //                    if (bCheckCOT) {
                    whereClause +=
                            " AND " + " (" + "  (" + "    (      " + "      (" +
                            "        (" + "          (" +
                            "          a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)" +
                            "          )" + "        )" +
                            "        and SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "      )" + "      or" + "      (" + "        (" +
                            "          (" +
                            "          a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)" +
                            "          )" + "        )" +
                            "        and SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "      )" + "    )  " + "    AND " +
                            "    (a.trang_thai = '12' OR a.trang_thai = '13' or a.trang_thai='16')" +
                            "  )  " + "  OR " +
                            "  (a.trang_thai = '11' OR a.trang_thai = '10') " +
                            ") ";
                    //                    } else {
                    //                        whereClause +=
                    //                                " AND (((" + " (a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                    //                                "  and a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh))" +
                    //                                "  or (a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                    //                                "  and a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)))" +
                    //                                "  AND (a.trang_thai = '12' OR (a.trang_thai = '13' or a.trang_thai='16')))" +
                    //                                "  OR (a.trang_thai = '11' OR a.trang_thai = '10')" +
                    //                                " )";
                    //                    }

                    whereClause = whereClause + whereClauseFind;
                    params.add(new Parameter(request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                             true));
                    params.add(new Parameter(session.getAttribute(AppConstants.APP_USER_ID_SESSION.toString()),
                                             true));
                    params.addAll(paramsFind);
                    whereClause +=
                            " ORDER BY f.rv_high_value,a.ngay_nhap_nh ASC";
                } else if ((strListRole.toUpperCase().indexOf(AppConstants.NSD_TTV) >=
                            0 &&
                            strListRole.toUpperCase().indexOf(AppConstants.NSD_KTT) >=
                            0)) {
                    //USER CO NHIEU ROLE  ttv va ktt
                    whereClause = " and a.nhkb_nhan=? ";
                    whereClause +=
                            " AND (substr(a.id,6,3) = '195' or substr(a.id,6,3) = '199')  AND f.rv_domain like '%SP_DTS%' ";
                    //                    if (bCheckCOT) {
                    whereClause +=
                            " AND " + " (" + "  (" + "    (      " + "      (" +
                            "        (" + "          (" +
                            "          a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)" +
                            "          )" + "        )" +
                            "        and SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "      )" + "      or" + "      (" + "        (" +
                            "          (" +
                            "          a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)" +
                            "          )" + "        )" +
                            "        and SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "      )" + "    )  " + "    AND " +
                            "    (a.trang_thai = '12' OR a.trang_thai = '13' or a.trang_thai='16')" +
                            "  )  " + "  OR " +
                            "  (a.trang_thai = '11' OR a.trang_thai = '10') " +
                            ") ";

                    whereClause += " ORDER BY a.trang_thai,a.ngay_nhap_nh ASC";
                    params.add(new Parameter(request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                             true));
                } else {
                    // gd
                    whereClause = " and a.nhkb_nhan=? ";
                    whereClause +=
                            " AND (substr(a.id,6,3) = '195' or substr(a.id,6,3) = '199')  AND f.rv_domain like '%SP_DTS%' ";
                    //                    if (bCheckCOT) {
                    whereClause +=
                            " AND " + " (" + "  (" + "    (      " + "      (" +
                            "        (" + "          (" +
                            "          a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)" +
                            "          )" + "        )" +
                            "        and SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "      )" + "      or" + "      (" + "        (" +
                            "          (" +
                            "          a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), c.ma_nh, b.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy') , c.ma_nh, b.ma_nh)" +
                            "          )" + "        )" +
                            "        and SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),c.ma_nh, b.ma_nh)" +
                            "      )" + "    )  " + "    AND " +
                            "    (a.trang_thai = '12' OR a.trang_thai = '13' or a.trang_thai='16')" +
                            "  )  " + "  OR " +
                            "  (a.trang_thai = '11' OR a.trang_thai = '10') " +
                            ") ";

                    whereClause += " ORDER BY a.trang_thai,a.ngay_nhap_nh ASC";
                    params.add(new Parameter(request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                             true));
                }
                // select them truong hop giam doc
                DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                if (action != null &&
                    action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL)) {
                    request.setAttribute("typeExit", true);
                    colDTSoatLTTDi = new ArrayList();
                    //                    colDTSoatLTTDi.add(TTSPUtils.getDTSById(dtsoatForm.getId(),
                    //                                                            "SP_DTS_HOI_NH.STATE",
                    //                                                            conn));
                    String whereViewDetails =
                        " and a.id = ? and c.rv_domain like 'SP_DTS%' ";
                    Vector paramViewDetails = new Vector();
                    paramViewDetails.add(new Parameter(dtsoatForm.getId(),
                                                       true));
                    colDTSoatLTTDi.add(dtsoatDAO.getDTS(whereViewDetails,
                                                        paramViewDetails));
                } else if (action != null &&
                           action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4)) {
                    request.setAttribute("typeExit", "T4");
                    colDTSoatLTTDi = new ArrayList();
                    //                    colDTSoatLTTDi.add(TTSPUtils.getDTSById(dtsoatForm.getId(),
                    //                                                            "SP_DTS_HOI_NH.STATE",
                    //                                                            conn));
                    String whereViewDetails =
                        " and a.id = ? and c.rv_domain like 'SP_DTS%' ";
                    Vector paramViewDetails = new Vector();
                    paramViewDetails.add(new Parameter(dtsoatForm.getId(),
                                                       true));
                    colDTSoatLTTDi.add(dtsoatDAO.getDTS(whereViewDetails,
                                                        paramViewDetails));
                } else {
                    colDTSoatLTTDi = dtsoatDAO.getDTSList(whereClause, params);
                }

                //response danh sach dien tra soat
                if (request.getAttribute("getJson") != null ||
                    (action != null &&
                     (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                      action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                    Type listType = new TypeToken<Collection<DTSoatVO>>() {
                    }.getType();
                    String strJson =
                        new Gson().toJson(colDTSoatLTTDi, listType);
                    response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                    PrintWriter out = response.getWriter();
                    out.println(strJson.toString());
                    out.flush();
                    out.close();
                } else {
                    request.setAttribute("listDTSoatLTTDi", colDTSoatLTTDi);
                    request.setAttribute("chucdanh",
                                         session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString());
                    //xem chi tiet
                    if (action != null &&
                        action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL)) {
                        if (colDTSoatLTTDi != null) {
                            for (Iterator iter = colDTSoatLTTDi.iterator();
                                 iter.hasNext(); ) {
                                DTSoatVO dtsoatVO = new DTSoatVO();
                                dtsoatVO = (DTSoatVO)iter.next();
                                BeanUtils.copyProperties(dtsoatForm, dtsoatVO);
                                request.setAttribute("mo_ta_trang_thai",
                                                     dtsoatVO.getMo_ta_trang_thai());
                                request.setAttribute("ten_don_vi_nhan_tra_soat",
                                                     dtsoatVO.getTen_don_vi_nhan_tra_soat());
                                request.setAttribute("ten_don_vi_tra_soat",
                                                     dtsoatVO.getTen_don_vi_tra_soat());

                            }
                        }
                    }
                }
            } else {
                return mapping.findForward(AppConstants.FAILURE);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            if (request.getAttribute("getJson") != null ||
                (action != null && (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                                    action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty("error", e.getMessage());
                out.println(jsonObj.toString());
                out.flush();
                out.close();
            } else {
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
        Vector params = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Connection conn = null;
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        try {
            DTSoatForm frmDTS = (DTSoatForm)form;
            conn = getConnection(request);
            String action = request.getParameter(AppConstants.REQUEST_ACTION);
            if (action != null &&
                AppConstants.ACTION_FILL_DATA_TO_FORM.equalsIgnoreCase(action)) {
                String id = frmDTS.getId();
                DTSoatVO dtsoatVO =
                    TTSPUtils.getDTSById(id, AppConstants.MA_THAM_CHIEU_TRANG_THAI_DTS_TUDO,
                                         conn);
                if (dtsoatVO != null) {
                    HttpSession session = request.getSession();
                    String strPhanQuyen =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);

                    if (strPhanQuyen.contains(AppConstants.NSD_KTT)) {
                        TTSPUtils spUtil = new TTSPUtils(conn);
                        String strNoiDungKy =
                            spUtil.getNoiDungKy(id, "196", "Y");
                        dtsoatVO.setNoi_dung_ky(strNoiDungKy);
                    }

                    params = new Vector();
                    params.add(new Parameter(id, true));
                    Type listType = new TypeToken<DTSoatVO>() {
                    }.getType();
                    strJson = new Gson().toJson(dtsoatVO, listType);
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            jsonObj.addProperty("error", e.getMessage());
            strJson = jsonObj.toString();

        } finally {
            if (out != null) {
                out.println(strJson.toString());
                out.flush();
                out.close();
            }
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
    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        DTSoatVO dtsoatVO = null;
        int i = 0;
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        String strListRole = "";
        try {
            if (!checkPermissionOnFunction(request, "DTS.DEN.XACNHAN_TRLOI")) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            HttpSession session = request.getSession();
            if (session == null)
                return mapping.findForward(AppConstants.FAILURE);
            conn = getConnection(request);
            DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
            strListRole =
                    (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            //select before for update
            String stateReq = dtsoatForm.getTrang_thai();
            String stateAfterSelect =
                TTSPUtils.getStateBU(request, response, conn);
            String userId =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            if ((stateReq != null && stateAfterSelect != null) &&
                stateReq.equalsIgnoreCase(stateAfterSelect)) {
                if (strListRole != null && !"".equalsIgnoreCase(strListRole)) {
                    String action =
                        request.getParameter(AppConstants.REQUEST_ACTION);
                    if ((strListRole.toUpperCase().indexOf(AppConstants.NSD_TTV) >=
                         0 &&
                         strListRole.toUpperCase().indexOf(AppConstants.NSD_KTT) ==
                         -1)) {
                        if (action != null &&
                            action.equals(AppConstants.ACTION_ACCEPT)) {
                            dtsoatVO = new DTSoatVO();
                            dtsoatVO.setTtv_nhan(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                            dtsoatVO.setNgay_nhan(StringUtil.DateToString(new Date(),
                                                                          "dd/MM/yyyy HH:mm:ss"));
                            dtsoatVO.setTrang_thai(AppConstants.SP_DTS_HOI_NH_STATE_12);
                            //                  if(dtsoatForm.getNhkb_nhan().equalsIgnoreCase(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString())){
                            //                    dtsoatVO.setNhkb_nhan(new Long(dtsoatForm.getNhkb_chuyen()));
                            //                    dtsoatVO.setNhkb_chuyen(new Long(dtsoatForm.getNhkb_nhan()));
                            //                  }else{
                            //                    dtsoatVO.setNhkb_nhan(new Long(dtsoatForm.getNhkb_nhan()));
                            //                    dtsoatVO.setNhkb_chuyen(new Long(dtsoatForm.getNhkb_chuyen()));
                            //                  }
                            dtsoatVO.setId((dtsoatForm.getId() != null &&
                                            !dtsoatForm.getId().equalsIgnoreCase("")) ?
                                           dtsoatForm.getId() : null);
                            dtsoatVO.setDts_id(dtsoatForm.getId());
                            i = dtsoatDAO.update(dtsoatVO);
                            saveVisitLog(conn, session,
                                         "DTS.DEN.XACNHAN_TRLOI", "");
                        }
                    } else if (strListRole.toUpperCase().indexOf(AppConstants.NSD_KTT) >=
                               0) {
                        dtsoatVO = new DTSoatVO();
                        dtsoatVO.setId((dtsoatForm.getId() != null &&
                                        !dtsoatForm.getId().equals("")) ?
                                       dtsoatForm.getId() : null);
                        if (action != null &&
                            action.equalsIgnoreCase(AppConstants.ACTION_ACCEPT)) {
                            dtsoatVO.setTrang_thai(AppConstants.SP_DTS_HOI_NH_STATE_16);

                        } else if (action != null &&
                                   action.equalsIgnoreCase(AppConstants.ACTION_REJECT)) {
                            dtsoatVO.setLydo_ktt_day_lai(dtsoatForm.getLydo_ktt_day_lai());
                            dtsoatVO.setTrang_thai(AppConstants.SP_DTS_HOI_NH_STATE_10);
                        }
                        dtsoatVO.setKtt_duyet(new Long(userId.toString()));
                        dtsoatVO.setNgay_duyet(StringUtil.DateToString(new Date(),
                                                                       DATE_TIME_FORMAT));
                        //                  PKIService pkiService=new PKIService();
                        //                  String userName=session.getAttribute(AppConstants.APP_DOMAIN_SESSION).toString() + "\\" + session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                        //
                        //                  String[] resultPKI=pkiService.VerifySignature(userName, request.getParameter("certSerial"), dtsoatForm.getId(), request.getParameter("signature"));
                        //                  if(resultPKI!=null && (resultPKI[0].equals(AppConstants.INVALID)||resultPKI[0].equals(AppConstants.ERROR))){
                        //                    jsonObj=new JsonObject();
                        //                    if(resultPKI[0].equalsIgnoreCase(AppConstants.INVALID))
                        //                      jsonObj.addProperty(AppConstants.ERROR, "TTSP-ERROR-0002");
                        //                    else
                        //                      jsonObj.addProperty(AppConstants.ERROR, "TTSP-ERROR-0003");
                        //                    out.println(jsonObj.toString());
                        //                  }else{
                        //                    dtsoatVO.setChuky_ktt(request.getParameter("signature"));
                        //                  }
                        //send MSG
                        if (action != null &&
                            (action.equalsIgnoreCase(AppConstants.ACTION_ACCEPT) ||
                             action.equalsIgnoreCase(AppConstants.ACTION_REJECT))) {
                            if (!action.equalsIgnoreCase(AppConstants.ACTION_REJECT)) {
                                //                                Collection colThamSoHT =
                                //                                    getThamSoHThong(session);
                                //                                SendDTS sendDTS = new SendDTS(conn);
                                DTSoatVO temp =
                                    TTSPUtils.getDTSById(dtsoatVO.getId(),
                                                         AppConstants.STATE_DTS_HOI_TU_NHTM,
                                                         conn);
                                BeanUtils.copyProperties(dtsoatForm, temp);

                                Date dDuyet = new Date();
                                String strNgayDuyet =
                                    StringUtil.DateToString(dDuyet,
                                                            AppConstants.DATE_TIME_FORMAT_SEND_ORDER);
                                dtsoatForm.setNgay_duyet(strNgayDuyet);
                                dtsoatForm.setKtt_duyet(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());

                                //                                String strMsgId =
                                //                                    sendDTS.sendDTSToBank(dtsoatForm,
                                //                                                          colThamSoHT, strNgayDuyet);
                                //
                                //                                dtsoatVO.setMsg_id(strMsgId);
                                dtsoatVO.setNgay_duyet(strNgayDuyet);
                            }
                            i = dtsoatDAO.update(dtsoatVO);
                            saveVisitLog(conn, session,
                                         "DTS.DEN.XACNHAN_TRLOI", "Duyet");
                        }

                    }

                    conn.commit();
                    if (i > 0) {
                        request.setAttribute("getJson", true);
                        list(mapping, form, request, response);
                    }
                } else {
                    return mapping.findForward(AppConstants.FAILURE);
                }
            } else {
                //Trang thai thay doi boi nsd khac
                jsonObj.addProperty("error", "TTSP_ERROR_0001");
                out.println(jsonObj.toString());
            }
        } catch (Exception e) {
            conn.rollback();
//            e.printStackTrace();
            jsonObj.addProperty("error", e.getMessage());
            out.println(jsonObj.toString());

        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
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
            params.add(new Parameter(dtsForm.getId() != null ?
                                     dtsForm.getId() :
                                     request.getParameter("id"), true));
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
                    //                    getServlet().getServletContext().getContextPath() +
                    //                    AppConstants.REPORT_DIRECTORY +
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
                //                JasperReport jReport =
                //                    JasperCompileManager.compileReport(reportStream);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);
                //                jasperPrint =
                //                        JasperFillManager.fillReport(reportStream, parameterMap,
                //                                                     conn);
                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "xulydtshoitunhtmRpt.do";
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
