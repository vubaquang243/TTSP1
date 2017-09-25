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
import com.seatech.ttsp.proxy.giaodien.SendDTS;
import com.seatech.ttsp.proxy.pki.PKIService;

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


public class XuLyDTSoatTraLoiAction extends AppAction {

    public XuLyDTSoatTraLoiAction() {
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
        String action = null;
        Connection conn = null;
        Collection colDTSoatLTTDi = null;
        Vector params = new Vector();
        String whereClause = "";
        boolean flag = true;
        boolean conditionSearchMaTTV = true;
        String strListRole = "";
        try {
            action = request.getParameter(AppConstants.REQUEST_ACTION)!=null?request.getParameter(AppConstants.REQUEST_ACTION):"";
            if(!action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4)){
              if (!checkPermissionOnFunction(request, "DTS.DI.TRLOI")) {
                  return mapping.findForward("errorQuyen");
              }  
            }
            conn = getConnection();
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            HttpSession session = request.getSession();

            if (session != null) {
                if (conn == null || conn.isClosed())
                    conn = getConnection();
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

                        whereClauseFind += " AND b.ma_nh like (?) ";
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getMa_don_vi_nhan_tra_soat() +
                                                     "%", true));
                        conditionSearchMaTTV = false;
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
                        conditionSearchMaTTV = false;
                    }
                    //xem chi tiet
                } else if (action != null &&
                           action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4)) {
                    flag = false;
                } else if (action != null &&
                           ((action.equalsIgnoreCase(AppConstants.ACTION_REFRESH) &&
                             (dtsoatForm.getId() != null &&
                              !"".equals(dtsoatForm.getId()))))) {
                    flag = false;
                }
                if ((strListRole.toUpperCase().indexOf(AppConstants.NSD_TTV) >=
                     0 &&
                     strListRole.toUpperCase().indexOf(AppConstants.NSD_KTT) ==
                     -1) && flag) {
                    whereClause = " and a.ttv_nhan=?";
                    whereClause +=
                            " AND substr(a.id,6,3) = '196' and f.rv_domain like 'SP_DTS%'";
                    whereClause +=
                            " AND (" + "  (" + "    (" + "      (" + "        ((" +
                            "          a.ngay_nhan >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "          and " +
                            "          a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        )" + "        OR " + "        (" +
                            "          a.ngay_duyet >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "          and " +
                            "          a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        ))" +
                            "        and SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "      ) " + "      or" + "      (" +
                            "        ((" +
                            "          a.ngay_nhan >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "          and " +
                            "          a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        )" + "        OR " + "        (" +
                            "          a.ngay_duyet >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "          and " +
                            "          a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        ))" +
                            "        and SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "      )" + "      AND " +
                            "      (a.trang_thai = '15' OR  a.trang_thai='17')  " +
                            "    ) " + "    OR " +
                            "    (a.trang_thai = '14' OR a.trang_thai = '10')" +
                            "  )" + ")";
                    whereClause = whereClause + whereClauseFind;
                    params.add(new Parameter(session.getAttribute(AppConstants.APP_USER_ID_SESSION),
                                             true));
                    params.addAll(paramsFind);
                    whereClause += " ORDER BY a.trang_thai,a.ngay_nhan ASC";
                } else if ((strListRole.toUpperCase().indexOf(AppConstants.NSD_TTV) ==
                            -1 &&
                            strListRole.toUpperCase().indexOf(AppConstants.NSD_KTT) >=
                            0) && flag) {
                    whereClause =
                            " and a.nhkb_chuyen =? and (a.ktt_duyet=? or a.ktt_duyet is null) ";
                    whereClause +=
                            " AND (" + "  (" + "    (" + "      (" + "        ((" +
                            "          a.ngay_nhan >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "          and " +
                            "          a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        )" + "        OR " + "        (" +
                            "          a.ngay_duyet >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "          and " +
                            "          a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        ))" +
                            "        and SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "      ) " + "      or" + "      (" +
                            "        ((" +
                            "          a.ngay_nhan >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "          and " +
                            "          a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        )" + "        OR " + "        (" +
                            "          a.ngay_duyet >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "          and " +
                            "          a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        ))" +
                            "        and SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "      )" + "      AND " +
                            "      (a.trang_thai = '15' OR  a.trang_thai='17')  " +
                            "    ) " + "    OR " +
                            "    (a.trang_thai = '14' OR a.trang_thai = '10')" +
                            "  )" + ")";
                    whereClause +=
                            "AND substr(a.id,6,3) = '196' and f.rv_domain like 'SP_DTS%' ";

                    params.add(new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                             true));
                    params.add(new Parameter(session.getAttribute(AppConstants.APP_USER_ID_SESSION),
                                             true));
                    params.addAll(paramsFind);
                    whereClause = whereClause + whereClauseFind;
                    whereClause +=
                            " ORDER BY f.rv_high_value ,a.ngay_nhan asc";
                } else if ((strListRole.toUpperCase().indexOf(AppConstants.NSD_TTV) >=
                            0 &&
                            strListRole.toUpperCase().indexOf(AppConstants.NSD_KTT) >=
                            0) ||
                           strListRole.toUpperCase().contains(AppConstants.NSD_GD) &&
                           flag) {
                    //USER CO NHIEU ROLE  ttv va ktt
//                    whereClause = " and a.nhkb_chuyen =? ";
//                    whereClause +=
//                            " AND (" + "  (" + "    (" + "      (" + "        ((" +
//                            "          a.ngay_nhan >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
//                            "          and " +
//                            "          a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
//                            "        )" + "        OR " + "        (" +
//                            "          a.ngay_duyet >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
//                            "          and " +
//                            "          a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
//                            "        ))" +
//                            "        and SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
//                            "      ) " + "      or" + "      (" +
//                            "        ((" +
//                            "          a.ngay_nhan >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
//                            "          and " +
//                            "          a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
//                            "        )" + "        OR " + "        (" +
//                            "          a.ngay_duyet >=sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
//                            "          and " +
//                            "          a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
//                            "        ))" +
//                            "        and SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
//                            "      )" + "      AND " +
//                            "      (a.trang_thai = '15' OR  a.trang_thai='17')  " +
//                            "    ) " + "    OR " +
//                            "    (a.trang_thai = '14' OR a.trang_thai = '10')" +
//                            "  )" + ")";
//                    whereClause +=
//                            "AND substr(a.id,6,3) = '196' and f.rv_domain like 'SP_DTS%' ";
                    String user_id = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
                                          whereClause += 
                      " and instr(a.id,196) = 6 AND f.rv_domain  like 'SP_DTS%'" +
                      " and  a.nhkb_chuyen = " + session.getAttribute(AppConstants.APP_NHKB_ID_SESSION) +" "+
                      "AND (((a.trang_thai in ('11','10','14') AND (a.ttv_nhan is null or a.ttv_nhan = "+user_id+")) " +
                      "or (a.trang_thai in ('11','10','14') AND (a.ktt_duyet is null or a.ktt_duyet = "+user_id+"))) " +
                      "OR ((a.ktt_duyet = "+user_id+" OR a.ttv_nhan = "+user_id+")" +
                      " and (((a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)  " +
                      " and         a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)) " +
                      " and         SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)        )  " +
                      " or        ((a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)   " +
                      " and         a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)) " +
                      " and         SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh))))) ";
                  
                    
                    params.addAll(paramsFind);
                    whereClause = whereClause + whereClauseFind;
                    whereClause +=
                            " ORDER BY f.rv_high_value ,a.ngay_nhan asc";
                } else if (flag) {
                    return mapping.findForward(AppConstants.FAILURE);
                }
            }

            DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
            if (action != null &&
                action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL)) {
                colDTSoatLTTDi = new ArrayList();
                colDTSoatLTTDi.add(TTSPUtils.getDTSById(dtsoatForm.getId(),
                                                        "SP_DTS",
                                                        conn));
            } else if (action != null &&
                           action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4)) {
                    colDTSoatLTTDi = new ArrayList();
                    String whereViewDetails =
                        " and a.id = ? and c.rv_domain like 'SP_DTS%' ";
                    Vector paramViewDetails = new Vector();
                    paramViewDetails.add(new Parameter(dtsoatForm.getId(),
                                                       true));
                    colDTSoatLTTDi.add(dtsoatDAO.getDTS(whereViewDetails,
                                                        paramViewDetails));
            }else {
                colDTSoatLTTDi = dtsoatDAO.getDTSList(whereClause, params);
            }

            //response danh sach dien tra soat
            if (request.getAttribute("getJson") != null ||
                (action != null && (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                                    action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                Type listType = new TypeToken<Collection<DTSoatVO>>() {
                }.getType();
                String strJson = new Gson().toJson(colDTSoatLTTDi, listType);
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
                    (action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL) || action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4))) {
                    if (colDTSoatLTTDi != null) {
                        for (Iterator iter = colDTSoatLTTDi.iterator();
                             iter.hasNext(); ) {
                            DTSoatVO dtsoatVO = new DTSoatVO();
                            dtsoatVO = (DTSoatVO)iter.next();
                            BeanUtils.copyProperties(dtsoatForm, dtsoatVO);
                            request.setAttribute("mo_ta_trang_thai",
                                                 dtsoatVO.getMo_ta_trang_thai());
                        }
                    }
                }
            }

        } catch (Exception e) {
//            e.printStackTrace();
            if (request.getAttribute("getJson") != null ||
                (action != null && (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                                    action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();
                JsonObject jsonObj = new JsonObject();
                jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
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
            HttpSession session = request.getSession();
            if (!checkPermissionOnFunction(request, "DTS.DI.TRLOI")) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            DTSoatForm frmDTS = (DTSoatForm)form;
            conn = getConnection();
            String action = request.getParameter(AppConstants.REQUEST_ACTION);
            if (action != null &&
                AppConstants.ACTION_FILL_DATA_TO_FORM.equalsIgnoreCase(action)) {
                String id = frmDTS.getId();
                DTSoatVO dtsoatVO =
                    TTSPUtils.getDTSById(id, AppConstants.STATE_DTS_HOI_TU_NHTM,
                                         conn);

                String strPhanQuyen =
                    (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);

                if (strPhanQuyen.contains(AppConstants.NSD_KTT)) {
                    TTSPUtils spUtil = new TTSPUtils(conn);
                    String strNoiDungKy = spUtil.getNoiDungKy(id, "196", "Y");
                    dtsoatVO.setNoi_dung_ky(strNoiDungKy);
                }


                if (dtsoatVO != null) {
                    params = new Vector();
                    params.add(new Parameter(id, true));
                    Type listType = new TypeToken<DTSoatVO>() {
                    }.getType();
                    strJson = new Gson().toJson(dtsoatVO, listType);
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
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
        JsonObject jsonObj = null;
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        try {
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            HttpSession session = request.getSession();
            conn = getConnection();
            DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
            //select before for update
            String stateReq = dtsoatForm.getTrang_thai();
            String stateAfterSelect =
                TTSPUtils.getStateBU(request, response, conn);
            String userId =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            if ((stateReq != null && stateAfterSelect != null) &&
                stateReq.equalsIgnoreCase(stateAfterSelect)) {
                String action =
                    request.getParameter(AppConstants.REQUEST_ACTION);
                if (action != null &&
                    action.equals(AppConstants.ACTION_EDIT)) {
                    if (!checkPermissionOnFunction(request,
                                                   "DTS.DI.TRLOI.SUA")) {
                        jsonObj = new JsonObject();
                        jsonObj.addProperty(AppConstants.ERROR,
                                            AppConstants.FAILURE);
                        out.println(jsonObj.toString());
                    }
                    if (jsonObj == null) {
                        dtsoatVO = new DTSoatVO();
                        dtsoatVO.setTtv_nhan(new Long(userId));
                        dtsoatVO.setNgay_nhan(StringUtil.DateToString(new Date(),
                                                                      "dd/MM/yyyy HH:mm:ss"));
                        dtsoatVO.setNoidung_traloi(dtsoatForm.getNoidung_traloi());
                        dtsoatVO.setTrang_thai(AppConstants.SP_DTS_HOI_NH_STATE_14);
                        dtsoatVO.setId((dtsoatForm.getId() != null &&
                                        !dtsoatForm.getId().equalsIgnoreCase("")) ?
                                       dtsoatForm.getId() : null);
                        i = dtsoatDAO.update(dtsoatVO);
                        saveVisitLog(conn, session, "DTS.DI.TRLOI.SUA", "");
                    }
                } else if (action != null &&
                           action.equals(AppConstants.ACTION_CANCEL)) {
                    if (!checkPermissionOnFunction(request,
                                                   "DTS.DI.TRLOI.XOA")) {
                        jsonObj = new JsonObject();
                        jsonObj.addProperty(AppConstants.ERROR,
                                            AppConstants.FAILURE);
                        out.println(jsonObj.toString());
                    }
                    if (jsonObj == null) {
                        if (stateReq.equalsIgnoreCase(AppConstants.SP_DTS_HOI_NH_STATE_14)) {
                            //delete
                            i = dtsoatDAO.deleteDTS(dtsoatForm.getId());
                        } else {
                            //update
                            dtsoatVO = new DTSoatVO();
                            dtsoatVO.setTrang_thai(AppConstants.SP_DTS_HOI_NH_STATE_17);
                            dtsoatVO.setNgay_nhan(StringUtil.DateToString(new Date(),
                                                                          "dd/MM/yyyy HH:mm:ss"));
                            dtsoatVO.setId(dtsoatForm.getId());
                            i = dtsoatDAO.update(dtsoatVO);
                        }
                        if (i > 0) {
                            dtsoatVO = new DTSoatVO();
                            dtsoatVO.setId(dtsoatForm.getDts_id());
                            dtsoatVO.setTrang_thai(AppConstants.SP_DTS_HOI_NH_STATE_11);
                            dtsoatVO.setNhkb_chuyen((dtsoatForm.getNhkb_nhan() !=
                                                     null) ?
                                                    new Long(dtsoatForm.getNhkb_nhan()) :
                                                    null);
                            dtsoatVO.setNhkb_nhan((dtsoatForm.getNhkb_chuyen() !=
                                                   null) ?
                                                  new Long(dtsoatForm.getNhkb_chuyen()) :
                                                  null);
                            dtsoatVO.setTtv_nhan(new Long("0"));
                            dtsoatVO.setLydo_ktt_day_lai("");
                            dtsoatVO.setNoidung_traloi("");
                            dtsoatVO.setDts_id("");
                            dtsoatVO.setNgay_nhan(StringUtil.DateToString(new Date(),
                                                                          "dd/MM/yyyy HH:mm:ss"));
                            i = dtsoatDAO.update(dtsoatVO);
                            saveVisitLog(conn, session, "DTS.DI.TRLOI.XOA",
                                         "");
                        }
                    }
                } else if (action != null &&
                           action.equalsIgnoreCase(AppConstants.ACTION_APPROVED)) {
                    if (!checkPermissionOnFunction(request,
                                                   "DTS.DI.TRLOI.DUYET")) {
                        jsonObj = new JsonObject();
                        jsonObj.addProperty(AppConstants.ERROR,
                                            AppConstants.FAILURE);
                        out.println(jsonObj.toString());
                    }
                    if (jsonObj == null) {
                        dtsoatVO = new DTSoatVO();
                        dtsoatVO.setId((dtsoatForm.getId() != null &&
                                        !dtsoatForm.getId().equals("")) ?
                                       dtsoatForm.getId() : null);
                        dtsoatVO.setTrang_thai(AppConstants.SP_DTS_HOI_NH_STATE_15);
                        String strSign = request.getParameter("signature");
                        String strWSDL = getThamSoHThong("WSDL_PKI", session);
                        String strAppID = getThamSoHThong("APP_ID", session);
                        PKIService pkiService = new PKIService(strWSDL);
                        String userName =
                            session.getAttribute(AppConstants.APP_DOMAIN_SESSION).toString() +
                            "\\" +
                            session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                        String[] resultPKI =
                            pkiService.VerifySignature(userName,
                                                       request.getParameter("certSerial"),
                                                       request.getParameter("noi_dung_ky"),
                                                       strSign, strAppID);
                        if (resultPKI != null &&
                            (resultPKI[0].equals(AppConstants.INVALID) ||
                             resultPKI[0].equals(AppConstants.ERROR))) {
                            jsonObj = new JsonObject();
                            if (resultPKI[0].equalsIgnoreCase(AppConstants.INVALID))
                                jsonObj.addProperty(AppConstants.ERROR,
                                                    "TTSP-ERROR-0002");
                            else
                                jsonObj.addProperty(AppConstants.ERROR,
                                                    "TTSP-ERROR-0003");
                            out.println(jsonObj.toString());
                        } else {
                            dtsoatVO.setChuky_ktt(strSign);
                        }
                        Date dNgayDuyet = new Date();
                        String strNgayDuyet = StringUtil.DateToString(dNgayDuyet, AppConstants.DATE_TIME_FORMAT_SEND_ORDER);

                        dtsoatVO.setKtt_duyet(new Long(userId.toString()));
                        dtsoatVO.setNgay_duyet(StringUtil.DateToString(new Date(),
                                                                       "dd/MM/yyyy HH:mm:ss"));
                        Collection colThamSoHT = getThamSoHThong(session);
                        SendDTS sendDTS = new SendDTS(conn);
                        DTSoatVO temp =
                            TTSPUtils.getDTSById(dtsoatVO.getId(), AppConstants.STATE_DTS_HOI_TU_NHTM,
                                                 conn);
                        BeanUtils.copyProperties(dtsoatForm, temp);

                        dtsoatForm.setNgay_duyet(strNgayDuyet);
                        dtsoatForm.setKtt_duyet(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());
                        //******************************************************
                        DTSoatVO vo = dtsoatDAO.getDTS(" AND a.id = " + dtsoatForm.getId(), null);
                        long ttv_nhan = vo.getTtv_nhan().longValue();
                        long user_id = new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()).longValue();
                        if(ttv_nhan == user_id){
                          throw new Exception("Khong cho phep vua lap vua duyet DTS");
                        }
                      //******************************************************
                        String strMsgId =
                            sendDTS.sendDTSToBank(dtsoatForm, colThamSoHT, strNgayDuyet);
                        //update DTS
                        dtsoatVO.setNgay_duyet(strNgayDuyet);
                        dtsoatVO.setMsg_id(strMsgId);
                        i = dtsoatDAO.update(dtsoatVO);
                        saveVisitLog(conn, session, "DTS.DI.TRLOI.DUYET", "");
                    }
                } else if (action != null &&
                           action.equalsIgnoreCase(AppConstants.ACTION_REJECT)) {
                    if (!checkPermissionOnFunction(request,
                                                   "DTS.DI.TRLOI.DUYET")) {
                        jsonObj = new JsonObject();
                        jsonObj.addProperty(AppConstants.ERROR,
                                            AppConstants.FAILURE);
                        out.println(jsonObj.toString());
                    }
                    if (jsonObj == null) {
                        dtsoatVO = new DTSoatVO();
                        dtsoatVO.setId((dtsoatForm.getId() != null &&
                                        !dtsoatForm.getId().equals("")) ?
                                       dtsoatForm.getId() : null);
                        dtsoatVO.setLydo_ktt_day_lai(dtsoatForm.getLydo_ktt_day_lai());
                        dtsoatVO.setTrang_thai(AppConstants.SP_DTS_HOI_NH_STATE_10);
                        dtsoatVO.setKtt_duyet(new Long(userId.toString()));
                        dtsoatVO.setNgay_duyet(StringUtil.DateToString(new Date(),
                                                                       "dd/MM/yyyy HH:mm:ss"));
                        i = dtsoatDAO.update(dtsoatVO);
                        saveVisitLog(conn, session, "DTS.DI.TRLOI.DUYET", "");

                    }
                } else {
                    jsonObj = new JsonObject();
                    jsonObj.addProperty(AppConstants.ERROR, "TTSP_ERROR_0001");
                    out.println(jsonObj.toString());
                }
                conn.commit();
                if (i > 0) {
                    request.setAttribute("getJson", true);
                    list(mapping, form, request, response);
                }

            } else {
                //Trang thai thay doi boi nsd khac
                jsonObj = new JsonObject();
                jsonObj.addProperty(AppConstants.ERROR, "TTSP_ERROR_0001");
                out.println(jsonObj.toString());
            }
        } catch (Exception e) {
            conn.rollback();
//            e.printStackTrace();
            jsonObj = new JsonObject();
            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
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
                reportName = "BCDTSTraLoi";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    AppConstants.REPORT_DIRECTORY +
                    AppConstants.FONT_FOR_REPORT;
                parameterMap = new HashMap();
                parameterMap.put("P_ID", dtsForm.getId());
                parameterMap.put("Type_LTT", typeLTT);
                if(dtsVO.getLoai_tien().equals("VND")){
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                }else{
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
                }
                
                
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
                String strActionName = "xulydtsoattraloiRpt.do";
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
