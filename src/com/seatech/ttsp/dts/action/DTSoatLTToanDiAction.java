package com.seatech.ttsp.dts.action;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.DateUtils;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.dmtiente.DMTienTeVO;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;
import com.seatech.ttsp.dts.form.DTSoatForm;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.proxy.giaodien.SendDTS;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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

 /*
 * @modify name: HungBM
 * @modify date: 27/4/2017
 * @see: sua loi in lan dau chi ra ban sao
 * @find: 20170427-hungbm
 * */

public class DTSoatLTToanDiAction extends AppAction {
    String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    public DTSoatLTToanDiAction() {
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

        Collection colDTSoatLTTDi = null;
        String whereClause = "";
        Vector params = null;
        Connection conn = null;
        String action = "";
        boolean flag = true;
        //        boolean conditionSearchMaTTV = true;
        String strListRole = "";
        String whereClauseFind = "";
        Vector paramsFind = new Vector();
        action =
                request.getParameter(AppConstants.REQUEST_ACTION) != null ? request.getParameter(AppConstants.REQUEST_ACTION) :
                "";
        if (!action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4) && !action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL)) {
            if (!checkPermissionOnFunction(request, "DTS.DI")) {
                return mapping.findForward("errorQuyen");
            }
        }
        try {
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            HttpSession session = request.getSession();

            if (session != null) {
                if (conn == null || conn.isClosed())
                    conn = getConnection(request);
                strListRole =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                params = new Vector();

                //                String joinSQL = "";
                //dk tiem kiem
                if (action != null &&
                    action.equalsIgnoreCase(AppConstants.ACTION_FIND)) {
                    if (dtsoatForm.getDts_id() != null &&
                        (!dtsoatForm.getDts_id().equals("") &&
                         !dtsoatForm.getDts_id().equals("null"))) {
                        whereClauseFind += " AND a.id like (?)";
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getDts_id() +
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
                    }
                    if (dtsoatForm.getMa_ttv_nhan() != null &&
                        (!dtsoatForm.getMa_ttv_nhan().equals("") &&
                         !dtsoatForm.getMa_ttv_nhan().equals("null"))) {
                        whereClauseFind +=
                                " AND (upper(d.ma_nsd) like upper(?) OR upper(d.ten_nsd) like upper(?)) ";
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getMa_ttv_nhan().toUpperCase() +
                                                     "%", true));
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getMa_ttv_nhan().toUpperCase() +
                                                     "%", true));
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
                //                ThamSoHThongDAO thamsoDAO = new ThamSoHThongDAO(conn);
                //                ThamSoHThongVO thamsoVO =
                //                    thamsoDAO.getThamSo(" ten_ts='CUT_OF_TIME'", null);
                //                String cutOfTime =
                //                    StringUtil.getCurrentDate() + " " + thamsoVO.getGiatri_ts();
                //THANH TOAN VIEN
                if(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION) != null){
                  String strNHKB_Chuyen = session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
                  whereClause += " AND a.nhkb_chuyen = "+strNHKB_Chuyen+" ";
                }
                if ((strListRole.toUpperCase().indexOf(AppConstants.NSD_TTV) >=
                     0 &&
                     strListRole.toUpperCase().indexOf(AppConstants.NSD_KTT) ==
                     -1) && flag) {
                    whereClause +=
                            " AND " + "  (" + "    (" + "      ( " + "        (" +
                            "          ((" +
                            "            a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , b.ma_nh, c.ma_nh)" +
                            "          ))" +
                            "          AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        )" + "      )  " + "or" + "        (" +
                            "          ((" +
                            "            a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy') , b.ma_nh, c.ma_nh)" +
                            "          ))" +
                            "          AND SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        )" + "      AND " +
                            "      (a.trang_thai = '03' OR a.trang_thai = '04' OR a.trang_thai = '18' OR a.trang_thai = '19')" +
                            "    )  OR (a.trang_thai = '01' OR a.trang_thai = '02') )";
                    whereClause += " AND a.ttv_nhan=? ";
                    whereClause += " AND a.nhkb_chuyen =?";
                    whereClause += " AND substr(a.id,6,3) = '195'";
                    whereClause += " AND f.rv_domain='SP_DTS.TRANG_THAI'";
                    whereClause = whereClause + whereClauseFind;
                    params.add(new Parameter(session.getAttribute(AppConstants.APP_USER_ID_SESSION),
                                             true));
                    params.add(new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                             true));
                    params.addAll(paramsFind);

                    whereClause += " ORDER BY a.trang_thai,a.ngay_nhan asc";
                    //KE TOAN TRUONG
                } else if ((strListRole.toUpperCase().indexOf(AppConstants.NSD_TTV) ==
                            -1 &&
                            strListRole.toUpperCase().indexOf(AppConstants.NSD_KTT) >=
                            0) && flag) {
                    whereClause +=
                            " AND " + "  (" + "    (" + "      ( " + "        (" +
                            "          ((" +
                            "            a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , b.ma_nh, c.ma_nh)" +
                            "          ))" +
                            "          AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        )" + "      )  " + "or" + "        (" +
                            "          ((" +
                            "            a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy') , b.ma_nh, c.ma_nh)" +
                            "          ))" +
                            "          AND SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        )" + "      AND " +
                            "      (a.trang_thai = '03' OR a.trang_thai = '04')" +
                            "    )  OR (a.trang_thai = '01' OR a.trang_thai = '02') )";
                    whereClause +=
                            " AND (a.ktt_duyet=? or a.ktt_duyet is null) ";
                    whereClause += " AND a.nhkb_chuyen =?";
                    whereClause += " AND substr(a.id,6,3) = '195' ";
                    params.add(new Parameter(session.getAttribute(AppConstants.APP_USER_ID_SESSION),
                                             true));
                    params.add(new Parameter(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                             true));
                    whereClause = whereClause + whereClauseFind;
                    params.addAll(paramsFind);

                    whereClause += " AND f.rv_domain='SP_DTS.TRANG_THAI'";
                    whereClause +=
                            " ORDER BY f.rv_high_value ,a.ngay_nhan asc";
                } else if ((strListRole.toUpperCase().indexOf(AppConstants.NSD_TTV) >=
                            0 &&
                            strListRole.toUpperCase().indexOf(AppConstants.NSD_KTT) >=
                            0) ||
                           strListRole.toUpperCase().contains(AppConstants.NSD_GD) &&
                           flag) {

                    //USER CO NHIEU ROLE  ttv va ktt
                    whereClause +=
                            " AND ((((((" +
                            "        a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        and " +
                            "        a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "        )  " + "        or " + "        (" +
                            "          a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE-1,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "          and " +
                            "          a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy') , b.ma_nh, c.ma_nh)" +
                            "        ))" +
                            "        AND SYSDATE < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "      )" + "      or" + "        (" +
                            "          ((" +
                            "            a.ngay_nhan >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "            and " +
                            "            a.ngay_nhan < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "          )  " + "          or " + "          (" +
                            "            a.ngay_duyet >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'), b.ma_nh, c.ma_nh)" +
                            "            and " +
                            "            a.ngay_duyet < sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE+1,'dd/mm/yyyy') , b.ma_nh, c.ma_nh)" +
                            "          ))" +
                            "          AND SYSDATE > sp_util_pkg.fnc_get_ngay_gio_cutoftime(TO_CHAR(SYSDATE,'dd/mm/yyyy'),b.ma_nh, c.ma_nh)" +
                            "        )" + "    )  " + "    AND " +
                            "    (a.trang_thai = '03' OR a.trang_thai = '04' OR a.trang_thai = '18' OR a.trang_thai = '19')" +
                            "  )  " + "  OR " +
                            "  (a.trang_thai = '01' OR a.trang_thai = '02') " +
                            ")";
                    whereClause +=
                            " AND (a.ktt_duyet=? or a.ktt_duyet is null) ";
                    whereClause += " AND substr(a.id,6,3) = '195' ";
                    whereClause = whereClause + whereClauseFind;
                    params.add(new Parameter(session.getAttribute(AppConstants.APP_USER_ID_SESSION),
                                             true));
                    params.addAll(paramsFind);

                    whereClause += " AND f.rv_domain='SP_DTS.TRANG_THAI'";
                    whereClause +=
                            " ORDER BY f.rv_high_value ,a.ngay_nhan asc";
                } else if (flag) {
                    return mapping.findForward(AppConstants.FAILURE);
                }

                DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                if (action != null &&
                    (action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL) &&
                     (dtsoatForm.getId() != null &&
                      !"".equals(dtsoatForm.getId())))) {
                    colDTSoatLTTDi = new ArrayList();
                    String whereViewDetails =
                        " and a.id = ? and c.rv_domain like 'SP_DTS%' ";
                    Vector paramViewDetails = new Vector();
                    paramViewDetails.add(new Parameter(dtsoatForm.getId(),
                                                       true));
                    colDTSoatLTTDi.add(dtsoatDAO.getDTS(whereViewDetails,
                                                        paramViewDetails));
                } else if (action != null &&
                           (action.equalsIgnoreCase(AppConstants.ACTION_REFRESH) &&
                            (dtsoatForm.getId() != null &&
                             !"".equals(dtsoatForm.getId())))) {
                    colDTSoatLTTDi = new ArrayList();
                    colDTSoatLTTDi.add(TTSPUtils.getDTSById(dtsoatForm.getId(),
                                                            AppConstants.STATE_DTS_LTT_DI,
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
                    response.reset();
                    response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                    PrintWriter out = response.getWriter();
                    out.println(strJson.toString());
                    out.flush();
                    out.close();
                } else {
                    request.setAttribute("listDTSoatLTTDi", colDTSoatLTTDi);
                    request.setAttribute("chucdanh", strListRole);
                    //xem chi tiet
                    if (action != null &&
                        (action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL) ||
                         action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4))) {
                        if (colDTSoatLTTDi != null) {
                            for (Iterator iter = colDTSoatLTTDi.iterator();
                                 iter.hasNext(); ) {
                                DTSoatVO dtsoatVO = new DTSoatVO();
                                dtsoatVO = (DTSoatVO)iter.next();

                                BeanUtils.copyProperties(dtsoatForm, dtsoatVO);
                                dtsoatForm.setMa_nsd(dtsoatVO.getMa_ttv_nhan());
                                request.setAttribute("mo_ta_trang_thai",
                                                     dtsoatVO.getMo_ta_trang_thai());
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
                response.reset();
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

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        try {
            if (!checkPermissionOnFunction(request, "DTS.DI.LTT.THEM")) {
                jsonObj.addProperty(AppConstants.ERROR, AppConstants.FAILURE);
            }
            HttpSession session = request.getSession();
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            conn = getConnection(request);
            // kiem tra ltt di neu khac da gui NH va gui NH thanh cong bao loi
            LTTDAO lttDAO = new LTTDAO(conn);
            String whereClause = " t.id=?";
            Vector params = new Vector();
            params.add(new Parameter(dtsoatForm.getLtt_id(), true));
            LTTVO lttVO = lttDAO.getLTTDi(whereClause, params);
            String strLttDi = dtsoatForm.getLtt_id().substring(2, 5);
            if (strLttDi.equals("701") &&
                (!lttVO.getTrang_thai().equals(AppConstants.LTT_TRANG_THAI_DA_GUI_NGAN_HANG) &&
                 !lttVO.getTrang_thai().equals(AppConstants.LTT_TRANG_THAI_NGAN_HANG_DA_NHAN))) {
                jsonObj.addProperty(AppConstants.ERROR,
                                    "L&#7879;nh thanh to&#225;n ph&#7843;i g&#7917;i sang NH m&#7899;i &#273;&#432;&#7907;c ph&#233;p tra so&#225;t!");
            } else {
                DTSoatVO dtsoatVO = new DTSoatVO();
                DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                BeanUtils.copyProperties(dtsoatVO, dtsoatForm);
                dtsoatVO.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
                dtsoatVO.setNgay_nhan(StringUtil.DateToString(new Date(),
                                                              "dd/MM/yyyy HH:mm:ss"));
                dtsoatVO.setLoai_tien(lttVO.getNt_code());
                String strReturn =
                    dtsoatDAO.insert(dtsoatVO, AppConstants.DTS_HOI_TYPE.toString());
                
                if (strReturn != null &&
                    !"-1".equalsIgnoreCase(strReturn.trim())) {
                    //set log
                    saveVisitLog(conn, session, "DTS.DI.LTT.THEM", "");
                    jsonObj.addProperty(AppConstants.SUCCESS, strReturn);
                }
                conn.commit();
            }
        } catch (Exception e) {
//            e.printStackTrace();
            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
        } finally {
            close(conn);
            if (out != null) {
                out.println(jsonObj.toString());
                out.flush();
                out.close();
            }
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
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        JsonObject jsonObj = null;
        PrintWriter out = response.getWriter();
        try {

            DTSoatForm dtsoatForm = (DTSoatForm)form;
            HttpSession session = request.getSession();
            if (session == null)
                return mapping.findForward(AppConstants.FAILURE);
            conn = getConnection(request);
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
                //huy
                if (action != null &&
                    AppConstants.ACTION_CANCEL.equals(action)) {
                    if (!checkPermissionOnFunction(request,
                                                   "DTS.DI.LTT.HUY")) {
                        jsonObj = new JsonObject();
                        jsonObj.addProperty(AppConstants.ERROR,
                                            AppConstants.FAILURE);
                        out.println(jsonObj.toString());
                    }
                    //ManhNV-20141120:Check lap/duyet cung tay
                    DTSoatVO dtsVO = dtsoatDAO.getDTS(" AND a.id = "+dtsoatForm.getId(), null);
                    String strKTT = dtsVO.getKtt_duyet()==null?"":dtsVO.getKtt_duyet().toString();
                    if(userId.equals(strKTT)){
                      throw new Exception("Khong duoc phep day lai va huy DTS tren cung mot NSD.");
                    }
                    //**********************************************************
                    if (jsonObj == null) {
                        if (dtsoatForm.getTrang_thai().equalsIgnoreCase(AppConstants.TRANG_THAI_CHO_DUYET)) {
                            //delete
                            i = dtsoatDAO.deleteDTS(dtsoatForm.getId());
                        } else {
                            //update
                            dtsoatVO = new DTSoatVO();
                            dtsoatVO.setTrang_thai(AppConstants.TRANG_THAI_HUY);
                            dtsoatVO.setNgay_nhan(StringUtil.DateToString(new Date(),
                                                                          "dd/MM/yyyy HH:mm:ss"));
                            dtsoatVO.setId(dtsoatForm.getId());
                            i = dtsoatDAO.update(dtsoatVO);
                        }
                        saveVisitLog(conn, session, "DTS.DI.LTT.HUY", "");
                    }

                } else if (action != null &&
                           AppConstants.ACTION_EDIT.equals(action)) {
                    if (!checkPermissionOnFunction(request,
                                                   "DTS.DI.LTT.SUA")) {
                        jsonObj = new JsonObject();
                        jsonObj.addProperty(AppConstants.ERROR,
                                            AppConstants.FAILURE);
                        out.println(jsonObj.toString());
                    }
                    //ManhNV-20141120:Check lap/duyet cung tay
                    DTSoatVO dtsVO = dtsoatDAO.getDTS(" AND a.id = "+dtsoatForm.getId(), null);
                    String strKTT = dtsVO.getKtt_duyet()==null?"":dtsVO.getKtt_duyet().toString();
                    if(userId.equals(strKTT)){
                      throw new Exception("Khong duoc phep sua va duyet DTS tren cung mot NSD.");
                    }
                    //**********************************************************
                    if (jsonObj == null) {
                        dtsoatVO = new DTSoatVO();
                        dtsoatVO.setNoidung(dtsoatForm.getNoidung());
                        dtsoatVO.setThong_tin_khac(dtsoatForm.getThong_tin_khac());
                        dtsoatVO.setNgay_nhan(StringUtil.DateToString(new Date(),
                                                                      "dd/MM/yyyy HH:mm:ss"));
                        dtsoatVO.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
                        dtsoatVO.setId((dtsoatForm.getId() != null &&
                                        !dtsoatForm.getId().equalsIgnoreCase("")) ?
                                       dtsoatForm.getId() : null);
                        dtsoatVO.setTtv_nhan(new Long(userId));
                        i = dtsoatDAO.update(dtsoatVO);
                        saveVisitLog(conn, session, "DTS.DI.LTT.SUA", "");
                    }
                } else if (action != null &&
                           AppConstants.ACTION_APPROVED.equalsIgnoreCase(action)) {
                    if (!checkPermissionOnFunction(request,
                                                   "DTS.DI.LTT.DUYET")) {
                        jsonObj = new JsonObject();
                        jsonObj.addProperty(AppConstants.ERROR,
                                            AppConstants.FAILURE);
                        out.println(jsonObj.toString());
                    }
                    //ManhNV-20141120:Check lap/duyet cung tay
                    DTSoatVO dtsVO = dtsoatDAO.getDTS(" AND a.id = "+dtsoatForm.getId(), null);
                    String strTTV = dtsVO.getTtv_nhan()==null?"":dtsVO.getTtv_nhan().toString();
                    if(userId.equals(strTTV)){
                      throw new Exception("Khong duoc phep lap va duyet DTS tren cung mot NSD.");
                    }
                    //**********************************************************
                    if (jsonObj == null) {
                        dtsoatVO = new DTSoatVO();
                        dtsoatVO.setId((dtsoatForm.getId() != null &&
                                        !dtsoatForm.getId().equals("")) ?
                                       dtsoatForm.getId() : null);
                        dtsoatVO.setTrang_thai(AppConstants.TRANG_THAI_DUYET);
                        String strWSDL = getThamSoHThong("WSDL_PKI", session);
                        String strAppID = getThamSoHThong("APP_ID", session);
                        PKIService pkiService = new PKIService(strWSDL);
                        String userName =
                            session.getAttribute(AppConstants.APP_DOMAIN_SESSION).toString() +
                            "\\" +
                            session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                        String strSign = request.getParameter("signature");
                        String strContentData =
                            request.getParameter("noi_dung_ky");
                        String strCertSerial =
                            request.getParameter("certSerial");
                        String[] resultPKI =
                            pkiService.VerifySignature(userName, strCertSerial,
                                                       strContentData, strSign,
                                                       strAppID);
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
                            dtsoatVO.setChuky_ktt(request.getParameter("signature"));
                        }
                        if (jsonObj == null) {
                            dtsoatVO.setKtt_duyet(new Long(userId.toString()));

                            //Get tham so he thong tren cache app
                            Collection colThamSoHT = getThamSoHThong(session);
                            //Gui lenh
                            SendDTS sendDTS = new SendDTS(conn);
                            DTSoatVO temp =
                                TTSPUtils.getDTSById(dtsoatVO.getId(),
                                                     AppConstants.STATE_DTS_LTT_DI,
                                                     conn);
                            BeanUtils.copyProperties(dtsoatForm, temp);

                            Date dNgayDuyet = new Date();
                            String strNgayDuyet = StringUtil.DateToString(dNgayDuyet,
                                                                             AppConstants.DATE_TIME_FORMAT_SEND_ORDER);
                            dtsoatForm.setKtt_duyet(session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString());
                            dtsoatForm.setNgay_duyet(strNgayDuyet);

                            String strMsgId =
                                sendDTS.sendDTSToBank(dtsoatForm, colThamSoHT, strNgayDuyet);
                            //Update DTS (KTT, Trang thai, msg_-d
                            dtsoatVO.setMsg_id(strMsgId);
                            dtsoatVO.setNgay_duyet(strNgayDuyet);
                            dtsoatVO.setChuky_ktt(strSign);

                            i = dtsoatDAO.update(dtsoatVO);
                            saveVisitLog(conn, session, "DTS.DI.LTT.DUYET",
                                         "Duyet");
                        }
                    }

                } else if (action != null &&
                           action.equalsIgnoreCase(AppConstants.ACTION_REJECT)) {
                    if (!checkPermissionOnFunction(request,
                                                   "DTS.DI.LTT.DUYET")) {
                        jsonObj = new JsonObject();
                        jsonObj.addProperty(AppConstants.ERROR,
                                            AppConstants.FAILURE);
                        out.println(jsonObj.toString());
                    }
                    //ManhNV-20141120:Check lap/duyet cung tay
                    DTSoatVO dtsVO = dtsoatDAO.getDTS(" AND a.id = "+dtsoatForm.getId(), null);
                    String strTTV = dtsVO.getTtv_nhan()==null?"":dtsVO.getTtv_nhan().toString();
                    if(userId.equals(strTTV)){
                      throw new Exception("Khong duoc phep lap va day lai DTS tren cung mot NSD.");
                    }
                    //**********************************************************
                    if (jsonObj == null) {
                        dtsoatVO = new DTSoatVO();
                        dtsoatVO.setId((dtsoatForm.getId() != null &&
                                        !dtsoatForm.getId().equals("")) ?
                                       dtsoatForm.getId() : null);
                        dtsoatVO.setLydo_ktt_day_lai(dtsoatForm.getLydo_ktt_day_lai());
                        dtsoatVO.setTrang_thai(AppConstants.TRANG_THAI_DAY_LAI);
                        dtsoatVO.setKtt_duyet(new Long(userId.toString()));
                        dtsoatVO.setNgay_duyet(StringUtil.DateToString(new Date(),
                                                                       "dd/MM/yyyy HH:mm:ss"));
                        saveVisitLog(conn, session, "DTS.DI.LTT.DUYET", "");
                        i = dtsoatDAO.update(dtsoatVO);
                    }
                } else {
                    jsonObj = new JsonObject();
                    jsonObj.addProperty(AppConstants.ERROR,
                                        AppConstants.FAILURE);
                    out.println(jsonObj.toString());
                }
                conn.commit();
                if (i > 0) {
                    request.setAttribute("getJson", true);
                    list(mapping, form, request, response);
                }

            } else {
                jsonObj = new JsonObject();
                jsonObj.addProperty(AppConstants.ERROR, "TTSP-ERROR-0001");
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

    /**
     *@author hungpd
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
        JsonObject jsonObj = null;
        String strJson = "";
        Connection conn = null;
        response.setContentType(AppConstants.CONTENT_TYPE_JSON);
        PrintWriter out = response.getWriter();
        try {
            if (!checkPermissionOnFunction(request, "DTS.DI")) {
                jsonObj = new JsonObject();
                jsonObj.addProperty(AppConstants.ERROR, AppConstants.FAILURE);
            }
            if (jsonObj == null) {
                conn = getConnection(request);
                String action =
                    request.getParameter(AppConstants.REQUEST_ACTION);
                if (action != null &&
                    AppConstants.ACTION_FILL_DATA_TO_FORM.equalsIgnoreCase(action)) {
                    String id = request.getParameter("id");
                    DTSoatVO dtsoatVO =
                        TTSPUtils.getDTSById(id, AppConstants.STATE_DTS_LTT_DI,
                                             conn);
                    HttpSession session = request.getSession();
                    String strNoiDungKy = "";
                    String strPhanQuyen =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);

                    if (strPhanQuyen.contains(AppConstants.NSD_KTT)) {
                        TTSPUtils spUtil = new TTSPUtils(conn);
                        strNoiDungKy = spUtil.getNoiDungKy(id, "195", "Y");
                    }

                    if (dtsoatVO != null) {
                        dtsoatVO.setNoi_dung_ky(strNoiDungKy);
                        params = new Vector();
                        params.add(new Parameter(id, true));
                        Type listType = new TypeToken<DTSoatVO>() {
                        }.getType();
                        strJson = new Gson().toJson(dtsoatVO, listType);
                    }
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            jsonObj.addProperty(AppConstants.ERROR, e.getMessage());
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
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        String whereClause = "";
        Vector params = null;
        try {
            if (!checkPermissionOnFunction(request, "DTS.DI")) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            HttpSession session = request.getSession();
            conn = getConnection(request);
            LTTDAO lttDAO = new LTTDAO(conn);
            String ltt_id = request.getParameter("id");
            if (ltt_id != null && !"".equalsIgnoreCase(ltt_id)) {
                dtsoatForm.setLtt_id(ltt_id);
                //lay thong tin chi tiet lenh thanh toan
                whereClause = "t.id=?";
                params = new Vector();
                params.add(new Parameter(ltt_id, true));
                LTTVO lttVO = lttDAO.getLTTDi(whereClause, params);
                if (lttVO != null) {
                    dtsoatForm.setNgay_thanh_toan(StringUtil.DateToString(DateUtils.LongToDate(lttVO.getNgay_tt()),
                                                                          "dd/MM/yyyy"));
                    dtsoatForm.setNhkb_nhan_ltt(lttVO.getNhkb_nhan().toString());

                    //lay ten ma danh muc ngan hang
                    whereClause = " id in(?,?) ";
                    params = new Vector();
                    params.add(new Parameter(lttVO.getNhkb_chuyen(), true));
                    params.add(new Parameter(lttVO.getNhkb_nhan(), true));
                    DMNHangDAO dmNHangDAO = new DMNHangDAO(conn);
                    Collection colNHKB =
                        dmNHangDAO.getDMNHList(whereClause, params);
                    if (colNHKB != null && colNHKB.size() > 0) {
                        for (Iterator iter = colNHKB.iterator();
                             iter.hasNext(); ) {
                            DMNHangVO dmNHVO = (DMNHangVO)iter.next();
                            if (lttVO.getNhkb_chuyen().toString().equals(lttVO.getNhkb_nhan().toString())) {
                                dtsoatForm.setMa_don_vi_tra_soat(dmNHVO.getMa_nh());
                                dtsoatForm.setTen_don_vi_tra_soat(dmNHVO.getTen());
                                dtsoatForm.setMa_don_vi_nhan_tra_soat(dmNHVO.getMa_nh());
                                dtsoatForm.setTen_don_vi_nhan_tra_soat(dmNHVO.getTen());
                                dtsoatForm.setNhkb_chuyen(dmNHVO.getId().toString());
                                dtsoatForm.setNhkb_nhan(dmNHVO.getId().toString());
                            } else {
                                if (dmNHVO.getId().toString().equals(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString())) {
                                    dtsoatForm.setNhkb_chuyen(dmNHVO.getId().toString());
                                    dtsoatForm.setMa_don_vi_tra_soat(dmNHVO.getMa_nh());
                                    dtsoatForm.setTen_don_vi_tra_soat(dmNHVO.getTen());
                                } else {
                                    dtsoatForm.setNhkb_nhan(dmNHVO.getId().toString());
                                    dtsoatForm.setMa_don_vi_nhan_tra_soat(dmNHVO.getMa_nh());
                                    dtsoatForm.setTen_don_vi_nhan_tra_soat(dmNHVO.getTen());
                                }

                            }
                        }
                    }
                    //*****Build noi dung thong tin khac tu LTT*****************
                    String strThongTinKhac = "";
                    //Lay ma tien te
                    DMTienTeDAO dmtiendao = new DMTienTeDAO(conn);
                    Vector vtParam = new Vector();
                    vtParam.add(new Parameter(lttVO.getNt_id(), true));
                    DMTienTeVO dmtien =
                        dmtiendao.getDMTienTe(" a.id = ? ", vtParam);

                    strThongTinKhac =
                            DateUtils.LongToStrDateDDMMYYYY(lttVO.getNgay_tt()).replace("/",
                                                                                        "-");
                    strThongTinKhac += "/";
                    strThongTinKhac += dmtien.getMa();

                    strThongTinKhac += "/";
                    strThongTinKhac += lttVO.getTong_sotien();

                    dtsoatForm.setThong_tin_khac(strThongTinKhac);
                    //************************************************************

                    dtsoatForm.setMa_nsd((String)session.getAttribute(AppConstants.APP_USER_CODE_SESSION));
                    dtsoatForm.setTen_nsd((String)session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
                    String use_id =
                        session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
                    dtsoatForm.setTtv_nhan(use_id);
                    dtsoatForm.setNgay_nhan(StringUtil.getCurrentDate());
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        String whereClause = "";
        Vector params = new Vector();
        Collection lstTrangThai = null;
        Collection lstDTS = null;
        String STRING_EMPTY = "";
        try {
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            conn = getConnection(request);
            HttpSession session = request.getSession();
            MaThamChieuDAO thamchieuDAO = new MaThamChieuDAO(conn);
            String strWhere = "a.rv_domain like ? ";
            Vector vParam = new Vector();
            vParam.add(new Parameter("%" +
                                     AppConstants.MA_THAM_CHIEU_TRANG_THAI_DTS_TUDO +
                                     "%", true));
            lstTrangThai = thamchieuDAO.getMaThamChieuList(strWhere, vParam);
            /* Author : Chuc
           * action back
           * gan cac gia tri tim kiem vao form
          */
            String actionBack =
                request.getParameter(AppConstants.REQUEST_ACTION);
            if (actionBack != null && actionBack.equalsIgnoreCase("back")) {
                String ttv_nhan_back = request.getParameter("ttv_nhan_back");
                String trang_thai_back =
                    request.getParameter("trang_thai_back");
                String tu_ngay_back = request.getParameter("tu_ngay_back");
                String den_ngay_back = request.getParameter("den_ngay_back");
                String tu_ngay_lapdien_back =
                    request.getParameter("tu_ngay_lapdien_back");
                String den_ngay_lapdien_back =
                    request.getParameter("den_ngay_lapdien_back");
                String nhkb_chuyen_back =
                    request.getParameter("nhkb_chuyen_back");
                String nhkb_nhan_back = request.getParameter("nhkb_nhan_back");
                String so_dts_back = request.getParameter("so_dts_back");
                String so_ltt_back = request.getParameter("so_ltt_back");
                String loai_ltt_back = request.getParameter("loai_ltt_back");
                String loai_tra_soat_back =
                    request.getParameter("loai_tra_soat_back");
                //                String chieu_tra_soat_back =
                //                    request.getParameter("chieu_tra_soat_back");
                if (so_ltt_back != null && !STRING_EMPTY.equals(so_ltt_back) &&
                    !ttv_nhan_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setLtt_id(so_ltt_back);
                } else {
                    dtsoatForm.setLtt_id(null);
                }
                if (loai_ltt_back != null &&
                    !STRING_EMPTY.equals(loai_ltt_back) &&
                    !loai_ltt_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setLoai_ltt(loai_ltt_back);
                } else {
                    dtsoatForm.setLoai_ltt(null);
                }
                if (loai_tra_soat_back != null &&
                    !STRING_EMPTY.equals(loai_tra_soat_back) &&
                    !loai_tra_soat_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setLoai_tra_soat(loai_tra_soat_back);
                } else {
                    dtsoatForm.setLoai_tra_soat(null);
                }
                //                if (chieu_tra_soat_back != null &&
                //                    !STRING_EMPTY.equals(chieu_tra_soat_back) &&
                //                    !chieu_tra_soat_back.equalsIgnoreCase("null")) {
                //                    dtsoatForm.setChieu_dts(chieu_tra_soat_back);
                //                } else {
                //                    dtsoatForm.setChieu_dts(null);
                //                }
                if (ttv_nhan_back != null &&
                    !STRING_EMPTY.equals(ttv_nhan_back) &&
                    !ttv_nhan_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setTtv_nhan(ttv_nhan_back);
                } else {
                    dtsoatForm.setTtv_nhan(null);
                }
                if (trang_thai_back != null &&
                    !STRING_EMPTY.equals(trang_thai_back) &&
                    !trang_thai_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setTrang_thai(trang_thai_back);
                } else {
                    dtsoatForm.setTrang_thai(null);
                }
                if (tu_ngay_lapdien_back != null &&
                    !STRING_EMPTY.equals(tu_ngay_lapdien_back) &&
                    !tu_ngay_lapdien_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setTu_ngay_lap_dien(tu_ngay_lapdien_back);
                } else {
                    dtsoatForm.setTu_ngay_lap_dien(null);
                }
                if (den_ngay_lapdien_back != null &&
                    !STRING_EMPTY.equals(den_ngay_lapdien_back) &&
                    !den_ngay_lapdien_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setDen_ngay_lap_dien(den_ngay_lapdien_back);
                } else {
                    dtsoatForm.setDen_ngay_lap_dien(null);
                }
                if (tu_ngay_back != null &&
                    !STRING_EMPTY.equals(tu_ngay_back) &&
                    !tu_ngay_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setTu_ngay_lap_lenh(tu_ngay_back);
                } else {
                    dtsoatForm.setTu_ngay_lap_lenh(null);
                }
                if (den_ngay_back != null &&
                    !STRING_EMPTY.equals(den_ngay_back) &&
                    !den_ngay_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setDen_ngay_lap_lenh(den_ngay_back);
                } else {
                    dtsoatForm.setDen_ngay_lap_lenh(null);
                }
                if (nhkb_chuyen_back != null &&
                    !STRING_EMPTY.equals(nhkb_chuyen_back) &&
                    !nhkb_chuyen_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setMa_don_vi_tra_soat(nhkb_chuyen_back);
                } else {
                    dtsoatForm.setMa_don_vi_tra_soat(null);
                }
                if (nhkb_nhan_back != null &&
                    !STRING_EMPTY.equals(nhkb_nhan_back) &&
                    !nhkb_nhan_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setMa_don_vi_nhan_tra_soat(nhkb_nhan_back);
                } else {
                    dtsoatForm.setMa_don_vi_nhan_tra_soat(null);
                }
                if (so_dts_back != null && !STRING_EMPTY.equals(so_dts_back) &&
                    !so_dts_back.equalsIgnoreCase("null")) {
                    dtsoatForm.setId(so_dts_back);
                } else {
                    dtsoatForm.setId(null);
                }
                dtsoatForm.setSearch_dts(true);
            }
            //
            //Tim kiem
            if (dtsoatForm.getSearch_dts() != null &&
                dtsoatForm.getSearch_dts()) {
                SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyyy");
                String nhkbId =
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
                //dk nguoi lap id hoac ma
                // whereClause += " AND (a.dts_id is null )";

                // chuc them de chan dien 299
                whereClause += " AND substr(a.id,6,3) <> '299'";
                // end

                whereClause += " AND (a.nhkb_chuyen =? or  a.nhkb_nhan =?) AND a.loai_dts ='K'";
                params.add(new Parameter(nhkbId, true));
                params.add(new Parameter(nhkbId, true));
                if (dtsoatForm.getTtv_nhan() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getTtv_nhan())) {
                    whereClause +=
                            " AND (UPPER(b.ten_nsd) like UPPER(?) or UPPER(b.ma_nsd) like UPPER(?) )";
                    params.add(new Parameter("%" + dtsoatForm.getTtv_nhan() +
                                             "%", true));
                    params.add(new Parameter("%" + dtsoatForm.getTtv_nhan() +
                                             "%", true));
                }
                //dk trang thai
                if (dtsoatForm.getTrang_thai() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getTrang_thai())) {
                    whereClause += " AND a.trang_thai=? ";
                    params.add(new Parameter(dtsoatForm.getTrang_thai(),
                                             true));
                }
                //dk don vi tra soat id hoac ma
                if (dtsoatForm.getMa_don_vi_tra_soat() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getMa_don_vi_tra_soat())) {
                    whereClause +=
                            " AND a.nhkb_chuyen in (SELECT id FROM sp_dm_ngan_hang WHERE ma_nh like(?)) ";
                    params.add(new Parameter("%" +
                                             dtsoatForm.getMa_don_vi_tra_soat() +
                                             "%", true));
                }
                //dk don vi nhan tra soat id hoac ma
                if (dtsoatForm.getMa_don_vi_nhan_tra_soat() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getMa_don_vi_nhan_tra_soat())) {
                    whereClause +=
                            " AND a.nhkb_nhan in (SELECT id FROM   sp_dm_ngan_hang WHERE ma_nh like(?)) ";
                    params.add(new Parameter("%" +
                                             dtsoatForm.getMa_don_vi_nhan_tra_soat() +
                                             "%", true));
                }
                //dk so lenh thanh toan
                if (dtsoatForm.getLtt_id() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getLtt_id())) {
                    whereClause += "  AND a.ltt_id like (?) ";
                    params.add(new Parameter("%" +
                                             dtsoatForm.getLtt_id().trim() +
                                             "%", true));
                }
                //dk so dien tra soat
                if (dtsoatForm.getId() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getId())) {
                    whereClause += "  AND a.id like(?) ";
                    params.add(new Parameter("%" + dtsoatForm.getId().trim() +
                                             "%", true));
                }
                //dk lenh di hay den
                if (dtsoatForm.getLoai_ltt() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getLoai_ltt())) {
                    //loai lenh thanh toan di
                    if (dtsoatForm.getLoai_ltt().equalsIgnoreCase("01")) {
                        whereClause += " AND a.nhkb_chuyen = ?";
                        params.add(new Parameter(nhkbId, true));
                        //loai lenh thanh toan den
                    } else if (dtsoatForm.getLoai_ltt().equalsIgnoreCase("02")) {
                        whereClause += " AND a.nhkb_nhan = ?";
                        params.add(new Parameter(nhkbId, true));
                    }
                }
                //dk tra soat di hay den
                //dk tra soat di hay den
                if (dtsoatForm.getChieu_dts() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getChieu_dts())) {
                    //loai lenh thanh toan di
                    if (dtsoatForm.getChieu_dts().equalsIgnoreCase("195")) {
                        whereClause += " AND instr(a.id,'701')=3 ";
                        //loai lenh thanh toan den
                    } else if (dtsoatForm.getChieu_dts().equalsIgnoreCase("196")) {
                        whereClause += " AND instr(a.id,'701')<>3 ";
                    }
                }
                //dk loai tra soat
                if (dtsoatForm.getLoai_tra_soat() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getLoai_tra_soat())) {
                    //loai tra soat tu do
                    if (dtsoatForm.getLoai_tra_soat().equalsIgnoreCase("01")) {
                        whereClause += " AND substr(a.id,6,3) = '199' ";
                        //                                " AND a.ltt_id IS NULL AND a.dts_id is null ";

                        //loai tra soat LTT
                    } else if (dtsoatForm.getLoai_tra_soat().equalsIgnoreCase("02")) {
                        whereClause += " AND substr(a.id,6,3) = '195' ";
                        //                                " AND a.ltt_id IS NOT NULL AND a.dts_id is null";
                    } else if (dtsoatForm.getLoai_tra_soat().equalsIgnoreCase("03")) {
                        whereClause +=
                                " AND substr(a.id,6,3) = '196' "; //" AND a.dts_id is not null";
                    } else if (dtsoatForm.getLoai_tra_soat().equalsIgnoreCase("03")) {
                        whereClause +=
                                " AND substr(a.id,6,3) = '299' "; //" dien keo dai thoi gian giao dich;
                    }
                }
                //dk tu ngay lap dien
                if (dtsoatForm.getTu_ngay_lap_dien() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getTu_ngay_lap_dien())) {
                    whereClause += " AND a.ngay_nhan >= ? ";
                    params.add(new DateParameter(fm.parse(dtsoatForm.getTu_ngay_lap_dien()),
                                                 true));
                }
                //dk den ngay lap dien
                if (dtsoatForm.getDen_ngay_lap_dien() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getDen_ngay_lap_dien())) {
                    whereClause += " AND TRUNC (a.ngay_nhan) <= ? ";
                    params.add(new DateParameter(fm.parse(dtsoatForm.getDen_ngay_lap_dien()),
                                                 true));
                }
                //dk tu ngay nhap lenh
                if (dtsoatForm.getTu_ngay_lap_lenh() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getTu_ngay_lap_lenh())) {
                    whereClause += " AND c.ngay_nhan >=? ";
                    params.add(new DateParameter(fm.parse(dtsoatForm.getTu_ngay_lap_lenh()),
                                                 true));
                }
                //dk den ngay nhap lenh
                if (dtsoatForm.getDen_ngay_lap_lenh() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getDen_ngay_lap_lenh())) {
                    whereClause += "  AND TRUNC (c.ngay_nhan) <=? ";
                    params.add(new DateParameter(fm.parse(dtsoatForm.getDen_ngay_lap_lenh()),
                                                 true));
                }
                String page = dtsoatForm.getPageNumber();
                if (page == null) {
                    page = "1";
                }
                // khai bao cac bien phan trang
                //                Integer currentPage = new Integer(page);
                //                Integer numberRowOnPage = new Integer(15);
                //                Integer totalCount[] = new Integer[1];
                //                DTSoatDAO dstDAO = new DTSoatDAO(conn);
                //                lstDTS =dstDAO.getDTSByConditionSearch(whereClause, params, currentPage,numberRowOnPage,totalCount);
                //                PagingBean pagingBean = new PagingBean();
                //                pagingBean.setCurrentPage(currentPage);
                //                pagingBean.setNumberOfRow(totalCount[0].intValue());
                //                pagingBean.setRowOnPage(numberRowOnPage);
                //                request.setAttribute("PAGE_KEY", pagingBean);

                //sua de them vao bao cao
                //                String username = session.getAttribute("id_nsd").toString();
                Map map = new HashMap();
                if (dtsoatForm.getTu_ngay_lap_dien() != null ||
                    !STRING_EMPTY.equals(dtsoatForm.getTu_ngay_lap_dien())) {
                    map.put("tungay", dtsoatForm.getTu_ngay_lap_dien());
                }
                if (dtsoatForm.getDen_ngay_lap_dien() != null ||
                    !STRING_EMPTY.equals(dtsoatForm.getDen_ngay_lap_dien())) {
                    map.put("denngay", dtsoatForm.getDen_ngay_lap_dien());
                }
                map.put("date",
                        DateUtils.LongToStrDateDDMMYYYY(DateUtils.getCurrentDate()));
                map.put("thanhtoanvien",
                        session.getAttribute(AppConstants.APP_USER_NAME_SESSION).toString());
                // khai bao cac bien phan trang
                Integer currentPage = new Integer(page);
                Integer numberRowOnPage = new Integer(15);
                Integer totalCount[] = new Integer[1];
                DTSoatDAO dstDAO = new DTSoatDAO(conn);
                lstDTS =
                        dstDAO.getDTSBy4Report(whereClause, params, currentPage,
                                               numberRowOnPage, totalCount);
                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("PAGE_KEY", pagingBean);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            request.setAttribute("lstTrangThai", lstTrangThai);
            request.setAttribute("lstDTS", lstDTS);
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
                
                // thuc hien truyen tham so in bao cao
                sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                                 dtsForm.getId() + "\" id=\"id\"></input>");
                reportName = "BCDTSHoi";
                String strPathFont =
                    //                    "C:\\projects\\KBNN\\working\\SourceCode\\TTSP\\public_html\\report\\font\\times.ttf";
                    getServlet().getServletContext().getContextPath() +
                    AppConstants.REPORT_DIRECTORY +
                    AppConstants.FONT_FOR_REPORT;
                parameterMap = new HashMap();
                parameterMap.put("P_ID", dtsVO.getId());
                parameterMap.put("Type_LTT", typeLTT);
                if(dtsVO.getLoai_tien().equals("VND")) {
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI")); 
                }else{ 
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
                }
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                                "/" +
                                                                                                reportName +
                                                                                                ".jasper");
                //                JasperReport jReport =
                //                    JasperCompileManager.compileReport(reportStream);
                if (reportStream == null) {
                    throw (new TTSPException().createException("TTSP-9999",
                                                               "Kh&#244;ng t&#236;m th&#7845;y file b&#225;o c&#225;o!"));
                }

                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);
                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "dtsoatlttdiRpt.do";
                String strParameter = "";

                ReportUtility rpUtilites = new ReportUtility();
                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, reportName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
              //HungBM 20170427 -  update TT_IN sau khi da in bao cao thanh cong - BEGIN
              // kiem tra lan in
              if (!"1".equalsIgnoreCase(dtsVO.getTt_in().toString())) {
                  DTSoatVO dtsVOForUpdate = new DTSoatVO();
                  dtsVOForUpdate.setId(dtsVO.getId());
                  dtsVOForUpdate.setTt_in(new Long("1"));
                  dtsDAO.update(dtsVOForUpdate);
                  conn.commit();
              }
              //HungBM 20170427 -  update TT_IN sau khi da in bao cao thanh cong - END
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
