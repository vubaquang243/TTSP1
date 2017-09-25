package com.seatech.ttsp.dts.action;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;
import com.seatech.ttsp.dts.form.DTSoatForm;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserVO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
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

 /*
 * @modify name: HungBM
 * @modify date: 27/4/2017
 * @see: sua loi in lan dau chi ra ban sao
 * @find: 20170509-hungbm
 * */

public class DTSoatTraLoiAction extends AppAction {
    private final String REQUEST_ACTION = "action";
    private final String TRANG_THAI_CHO_DUYET = "02";
    private final String TRANG_THAI_DUYET = "03";
    private final String TRANG_THAI = "STATE";

    public DTSoatTraLoiAction() {
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

        Collection colDTSoatTraLoi = null;
        String whereClause = "";
        Vector params = null;
        Connection conn = null;
        String chucDanh;
        boolean conditionSearchMaTTV = true;

        try {
            String action = request.getParameter(REQUEST_ACTION)!=null?request.getParameter(REQUEST_ACTION):"";
            if (!action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4)) {
                if (!checkPermissionOnFunction(request,
                                               "DTS.DEN.XACNHAN_TRLOI")) {
                    return mapping.findForward("errorQuyen");
                }
            }
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            HttpSession session = request.getSession();

            if (session != null) {
                if (conn == null || conn.isClosed())
                    conn = getConnection();
                saveVisitLog(conn, session, "DTS.DEN.XACNHAN_TRLOI", "");
                chucDanh =
                        (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
                Vector paramsFind = new Vector();
                params = new Vector();
                String whereClauseFind = "";
                if (action != null &&
                    action.equalsIgnoreCase(AppConstants.ACTION_FIND)) {
                    if (dtsoatForm.getDts_id() != null &&
                        (!dtsoatForm.getDts_id().equals("") &&
                         !dtsoatForm.getDts_id().equals("null"))) {
                        whereClauseFind =
                                whereClauseFind + " AND a.dts_id like (?)";
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getDts_id() +
                                                     "%", true));
                    }
                    if (dtsoatForm.getLtt_id() != null &&
                        (!dtsoatForm.getLtt_id().equals("") &&
                         !dtsoatForm.getLtt_id().equals("null"))) {
                        whereClauseFind =
                                whereClauseFind + " AND a.ltt_id like (?)";
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getLtt_id() +
                                                     "%", true));
                    }
                    if (dtsoatForm.getNhkb_nhan() != null &&
                        (!dtsoatForm.getNhkb_nhan().equals("") &&
                         !dtsoatForm.getNhkb_nhan().equals("null"))) {
                        whereClauseFind =
                                whereClauseFind + " AND a.nhkb_nhan like (?)";
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getNhkb_nhan() +
                                                     "%", true));
                    }
                    if (dtsoatForm.getId() != null &&
                        (!dtsoatForm.getId().equals("") &&
                         !dtsoatForm.getId().equals("null"))) {
                        whereClauseFind =
                                whereClauseFind + " AND a.id like (?)";
                        paramsFind.add(new Parameter("%" + dtsoatForm.getId() +
                                                     "%", true));
                    }
                    if (dtsoatForm.getTtv_nhan() != null &&
                        (!dtsoatForm.getTtv_nhan().equals("") &&
                         !dtsoatForm.getTtv_nhan().equals("null"))) {
                        whereClauseFind +=
                                " AND (upper(d.ma_nsd) like (?) OR upper(d.ten_nsd) like (?)) ";
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getTtv_nhan().toUpperCase() +
                                                     "%", true));
                        paramsFind.add(new Parameter("%" +
                                                     dtsoatForm.getTtv_nhan().toUpperCase() +
                                                     "%", true));
                        conditionSearchMaTTV = false;
                    }

                } else if (action != null &&
                           (action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL))) {
                    whereClauseFind = " AND a.id=? ";
                    paramsFind.add(new Parameter(dtsoatForm.getId(), true));
                } else if (action != null &&
                           (action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4))) {
                    whereClauseFind = " AND a.id=? ";
                    paramsFind.add(new Parameter(dtsoatForm.getId(), true));
                }
                if (chucDanh.toUpperCase().indexOf(AppConstants.NSD_TTV) >=
                    0) {
                    params.add(new Parameter(session.getAttribute("id_nsd"),
                                             true));
                    whereClause =
                            " AND f.rv_domain='SP_DTS.TRANG_THAI' and substr(a.id,6,3) = '196' and " +
                            " (" + "  a.ttv_nhan=? " + "  and " + "  (" +
                            "    a.trang_thai not in ('00','03') " +
                            "    OR  " + "    (" + "      a.trang_thai='03' " +
                            "      AND " + "      (" + "      (" +
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
                            "      )" + "    )" + "    )" + "  )  " +
                            "  OR a.trang_thai='00'" + ")";
                    whereClause =
                            whereClause + " AND a.nhkb_nhan=? and (a.ttv_nhan=? OR a.ttv_nhan is null) ";
                    params.add(new Parameter(request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                             true));
                    params.add(new Parameter(session.getAttribute(AppConstants.APP_USER_ID_SESSION.toString()),
                                             true));
                    whereClause =
                            whereClause + whereClauseFind + " ORDER BY a.trang_thai,a.ngay_nhan";
                    params.addAll(paramsFind);

                } else if (chucDanh.toUpperCase().indexOf(AppConstants.NSD_TTV) ==
                           -1 &&
                           chucDanh.toUpperCase().indexOf(AppConstants.NSD_KTT) >=
                           0) {
                    whereClause =
                            " AND f.rv_domain='SP_DTS.TRANG_THAI' and substr(a.id,6,3) = '196' and  " +
                            "(" + "  a.trang_thai='02' " + "  OR " + "  (" +
                            "    a.trang_thai='03' " + "    AND" + "    (" +
                            "      (" + "        (" + "          (" +
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
                            "      )" + "    )" + "    " + "  )" + ")";
                    whereClause =
                            whereClause + " AND a.nhkb_nhan=? and (a.ktt_duyet = ? or a.ktt_duyet is null) ";
                    params.add(new Parameter(request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION),
                                             true));
                    params.add(new Parameter(session.getAttribute(AppConstants.APP_USER_ID_SESSION.toString()),
                                             true));
                    whereClause =
                            whereClause + whereClauseFind + " ORDER BY a.trang_thai,a.ngay_nhan desc";
                    params.addAll(paramsFind);
                    // return mapping.findForward(AppConstants.FAILURE);
                }
                //                else if (chucDanh.toUpperCase().indexOf(AppConstants.NSD_TTV) ==
                //                           -1 &&
                //                           chucDanh.toUpperCase().indexOf(AppConstants.NSD_KTT) ==
                //                           -1) {
                //                    return mapping.findForward("errorQuyen");
                //                }

                if (action != null &&
                    action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL)) {
                    whereClause =
                            " AND f.rv_domain like '%SP_DTS%' and a.id=?  ";
                    params = new Vector();
                    params.add(new Parameter(dtsoatForm.getId(), true));
                    DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                    colDTSoatTraLoi =
                            dtsoatDAO.getDTSList(whereClause, params);
                    //                    colDTSoatTraLoi = new ArrayList();
                    //                    colDTSoatTraLoi.add(this.getDTSById(dtsoatForm.getId(),
                    //                                                        conn));
                } else if (action != null &&
                           action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4)) {
                    whereClause =
                            " AND f.rv_domain like '%SP_DTS%' and a.id=?  ";
                    params = new Vector();
                    params.add(new Parameter(dtsoatForm.getId(), true));
                    DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                    colDTSoatTraLoi =
                            dtsoatDAO.getDTSList(whereClause, params);
                    //                    colDTSoatTraLoi = new ArrayList();
                    //                    colDTSoatTraLoi.add(this.getDTSById(dtsoatForm.getId(),
                    //                                                        conn));
                } else {
                    DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                    colDTSoatTraLoi =
                            dtsoatDAO.getDTSList(whereClause, params);
                }
            } else {
                return mapping.findForward(AppConstants.FAILURE);
            }
            //response danh sach dien tra soat
            if (request.getAttribute("getJson") != null ||
                (action != null && (action.equalsIgnoreCase(AppConstants.ACTION_FIND) ||
                                    action.equalsIgnoreCase(AppConstants.ACTION_REFRESH)))) {
                Type listType = new TypeToken<Collection<DTSoatVO>>() {
                }.getType();
                String strJson = new Gson().toJson(colDTSoatTraLoi, listType);
                response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                PrintWriter out = response.getWriter();

                out.println(strJson.toString());
                out.flush();
                out.close();
            } else {
                request.setAttribute("listDTSoatTraLoi", colDTSoatTraLoi);
                request.setAttribute("chucdanh",
                                     session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString());
                //xem chi tiet
                if (action != null &&
                    (action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL) || action.equalsIgnoreCase(AppConstants.ACTION_VIEW_DETAIL_DTS_T4))) {
                    if (colDTSoatTraLoi != null) {
                        for (Iterator iter = colDTSoatTraLoi.iterator();
                             iter.hasNext(); ) {
                            DTSoatVO dtsoatVO = new DTSoatVO();
                            dtsoatVO = (DTSoatVO)iter.next();
                            BeanUtils.copyProperties(dtsoatForm, dtsoatVO);
                            if (dtsoatVO.getError_desc() != null) {
                                dtsoatForm.setError_des(dtsoatVO.getError_code() +
                                                        " - " +
                                                        dtsoatVO.getError_desc());
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(e.getMessage());
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
        String chucDanh;
        JsonObject jsonObj = new JsonObject();
        if (!checkPermissionOnFunction(request, "DTS.DEN.XACNHAN_TRLOI")) {
            return mapping.findForward("errorQuyen");
        }
        try {
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            HttpSession session = request.getSession();
            if (session == null)
                return mapping.findForward(AppConstants.FAILURE);
            conn = getConnection();
            DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
            chucDanh =
                    (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            //select before for update
            String stateReq = dtsoatForm.getTrang_thai();
            String stateAfterSelect = this.getStateBU(request, response, conn);
            String userId =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            if ((stateReq != null && stateAfterSelect != null) &&
                stateReq.equalsIgnoreCase(stateAfterSelect)) {
                if (chucDanh != null && !"".equalsIgnoreCase(chucDanh)) {
                    String action = request.getParameter(REQUEST_ACTION);
                    if (chucDanh.toUpperCase().indexOf(AppConstants.NSD_TTV) >=
                        0) {
                        //huy
                        if (action != null &&
                            action.equals(AppConstants.ACTION_ACCEPT)) {
                            {
                                //update
                                dtsoatVO = new DTSoatVO();
                                dtsoatVO.setTrang_thai(TRANG_THAI_CHO_DUYET);
                                dtsoatVO.setTtv_nhan(new Long(userId.toString()));
                                dtsoatVO.setId(dtsoatForm.getId());
                                i = dtsoatDAO.update(dtsoatVO);

                            }
                            //saveVisitLog(conn,userId, "DTS.XACNHAN_TRLOI.XACNHAN", "");

                        }
                    } if (chucDanh.toUpperCase().indexOf(AppConstants.NSD_KTT) >=
                               0) {
                        if (action != null &&
                            action.equals(AppConstants.ACTION_APPROVED)) {
                            //**************************************************
                            DTSoatVO vo = dtsoatDAO.getDTS(" and a.id = "+dtsoatForm.getId()+" ", null);
                            long ttv = vo.getTtv_nhan().longValue();
                            long nsd = new Long(userId).longValue();
                            if(ttv == nsd){
                              throw new Exception("Khong cho phep vua xac nhan vua duyet");
                            }
                            //**************************************************
                            dtsoatVO = new DTSoatVO();
                            dtsoatVO.setId((dtsoatForm.getId() != null &&
                                            !dtsoatForm.getId().equals("")) ?
                                           dtsoatForm.getId() : null);
                            if (action != null &&
                                action.equalsIgnoreCase(AppConstants.ACTION_APPROVED)) {
                                dtsoatVO.setTrang_thai(TRANG_THAI_DUYET);

                                dtsoatVO.setKtt_duyet(new Long(userId.toString()));
                                dtsoatVO.setNgay_duyet(StringUtil.getCurrentDate());
                                saveVisitLog(conn, session,
                                             "DTS.DEN.XACNHAN_TRLOI.DUYET",
                                             "");
                                //Xac thuc chu ky: hop le moi cho gui
                                String strWSDL =
                                    getThamSoHThong("WSDL_PKI", session);
                                String strAppID =
                                    getThamSoHThong("APP_ID", session);
                                PKIService pkiService =
                                    new PKIService(strWSDL);
                                String userName =
                                    session.getAttribute(AppConstants.APP_DOMAIN_SESSION).toString() +
                                    "\\" +
                                    session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                                String strSign =
                                    request.getParameter("signature");
                                String strContentData =
                                    request.getParameter("noi_dung_ky");
                                String strCertSerial =
                                    request.getParameter("certSerial");
                                String[] arrResultKy =
                                    pkiService.VerifySignature(userName,
                                                               strCertSerial,
                                                               strContentData,
                                                               strSign,
                                                               strAppID);

                                if (arrResultKy != null &&
                                    (arrResultKy[0].equals(AppConstants.INVALID) ||
                                     arrResultKy[0].equals(AppConstants.ERROR))) {
                                    jsonObj = new JsonObject();
                                    if (arrResultKy[0].equalsIgnoreCase(AppConstants.INVALID))
                                        throw new Exception("Duyet khong thanh cong. Loi verify chu ky: " +
                                                            arrResultKy[1]);
                                    else
                                        throw new Exception("Duyet khong thanh cong. Loi verify chu ky: " +
                                                            arrResultKy[1]);
                                } else {
                                    if (arrResultKy == null) {
                                        throw new Exception("Duyet khong thanh cong. Loi verify chu ky: NULL");
                                    }
                                    dtsoatVO.setChuky_ktt(strSign);
                                    i = dtsoatDAO.update(dtsoatVO);
                                }

                            }
                        }
                    }

                    conn.commit();
                    request.setAttribute("duyet_status",
                                         "Duy&#7879;t th&#224;nh c&#244;ng!");
                    list(mapping, form, request, response);

                } else {
                    return mapping.findForward(AppConstants.FAILURE);
                }
            }
        } catch (Exception e) {
            conn.rollback();
//            e.printStackTrace();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Collection colNHKB = null;
        DTSoatVO dtsoatVO = null;
        String whereClause = "";
        Vector params = null;
        DMNHangVO dmNHVO = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Connection conn = null;
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            saveVisitLog(conn, session, "DTS.DEN.XACNHAN_TRLOI", "");
            DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
            String action = request.getParameter(REQUEST_ACTION);
            if (action != null &&
                AppConstants.ACTION_FILL_DATA_TO_FORM.equalsIgnoreCase(action)) {
                whereClause = " AND  a.id=? AND c.rv_domain like '%SP_DTS%' ";
                String id = request.getParameter("id");
                if (id != null && !id.equals("")) {
                    params = new Vector();
                    params.add(new Parameter(id, true));
                    dtsoatVO = dtsoatDAO.getDTS(whereClause, params);
                    if (dtsoatVO != null) {
                        if (dtsoatVO.getTrang_thai().equals("03"))
                            dtsoatVO.setMo_ta_trang_thai("&#272;&#227; x&#225;c nh&#7853;n");
                        //lay ten ma danh muc ngan hang
                        whereClause = " id =? or id=?  ";
                        params = new Vector();
                        params.add(new Parameter(dtsoatVO.getNhkb_chuyen(),
                                                 true));
                        params.add(new Parameter(dtsoatVO.getNhkb_nhan(),
                                                 true));
                        DMNHangDAO dmNHangDAO = new DMNHangDAO(conn);
                        colNHKB = dmNHangDAO.getDMNHList(whereClause, params);
                        if (colNHKB != null && colNHKB.size() > 0) {
                            for (Iterator iter = colNHKB.iterator();
                                 iter.hasNext(); ) {
                                dmNHVO = (DMNHangVO)iter.next();
                                if (dtsoatVO.getNhkb_chuyen().toString().equals(dtsoatVO.getNhkb_nhan().toString())) {
                                    dtsoatVO.setMa_don_vi_tra_soat(dmNHVO.getMa_nh());
                                    dtsoatVO.setTen_don_vi_tra_soat(dmNHVO.getTen());
                                    dtsoatVO.setMa_don_vi_nhan_tra_soat(dmNHVO.getMa_nh());
                                    dtsoatVO.setTen_don_vi_nhan_tra_soat(dmNHVO.getTen());
                                } else {
                                    if (dtsoatVO.getNhkb_chuyen().toString().equals(dmNHVO.getId().toString())) {
                                        dtsoatVO.setMa_don_vi_tra_soat(dmNHVO.getMa_nh());
                                        dtsoatVO.setTen_don_vi_tra_soat(dmNHVO.getTen());
                                    } else {
                                        dtsoatVO.setMa_don_vi_nhan_tra_soat(dmNHVO.getMa_nh());
                                        dtsoatVO.setTen_don_vi_nhan_tra_soat(dmNHVO.getTen());
                                    }
                                }
                            }
                        } else {
                            return mapping.findForward(AppConstants.FAILURE);
                        }

                        TTSPUtils spUtil = new TTSPUtils(conn);
                        String strNoiDungKy =
                            spUtil.getNoiDungKy(dtsoatVO.getId(), "196", "N");//066
                        dtsoatVO.setNoi_dung_ky(strNoiDungKy);

                        Type listType = new TypeToken<DTSoatVO>() {
                        }.getType();
                        strJson = new Gson().toJson(dtsoatVO, listType);
                    }
                    response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                    PrintWriter out = response.getWriter();
                    out.println(strJson.toString());
                    out.flush();
                    out.close();
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            jsonObj.addProperty("error", e.getMessage());
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

    protected String[] getChucDanh(HttpServletRequest request,
                                   HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            String strListRole =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            if (strListRole != null && !"".equals(strListRole)) {
                return strListRole.split("\\|");
            }

        } catch (Exception ex) {
//            ex.printStackTrace();
            return null;
        }
        return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        String whereClause = "";
        Vector params = new Vector();
        Collection lstDTS = null;
        DMNHangVO dmnh = null;
        HttpSession session = request.getSession();
        if (!checkPermissionOnFunction(request, "DTS.DEN.XACNHAN_TRLOI")) {
            return mapping.findForward("errorQuyen");
        }
        try {
            conn = getConnection();
            request.setAttribute("p_err_desc", "");
            Collection listDMNH = new ArrayList();
            String strKBacID = "";
            String nhkbId =
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
            if (request.getSession().getAttribute("id_kb") != null) {
                strKBacID =
                        request.getSession().getAttribute("id_kb").toString();
            }
            DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
            listDMNH = dmnhDAO.getDMNH_KBByKBId(strKBacID);

            request.setAttribute("colDMNH", listDMNH);
            DTSoatForm dtsoatForm = (DTSoatForm)form;
            String action = request.getParameter(REQUEST_ACTION);
            if (action != null &&
                AppConstants.ACTION_VIEW_DETAIL.equals(action)) {
                dtsoatForm.setSearch_dts(true);
                if (request.getParameter("ttvnhan") != null) {
                    dtsoatForm.setTtv_nhan(request.getParameter("ttvnhan").toString());
                } else {
                    dtsoatForm.setTtv_nhan(null);
                }
                if (request.getParameter("sodts") != null) {
                    dtsoatForm.setId(request.getParameter("sodts").toString());
                } else {
                    dtsoatForm.setId(null);
                }
                if (request.getParameter("lttid") != null) {
                    dtsoatForm.setLtt_id(request.getParameter("lttid").toString());
                } else {
                    dtsoatForm.setLtt_id(null);
                }
                if (request.getParameter("trangthai") != null) {
                    dtsoatForm.setTrang_thai(request.getParameter("trangthai").toString());
                } else {
                    dtsoatForm.setTrang_thai(null);
                }
                if (request.getParameter("nhkbchuyen") != null) {
                    String strNhkbchuyen = request.getParameter("nhkbchuyen");
                    dtsoatForm.setNhkb_chuyen(strNhkbchuyen);
                    dmnh = getDMNHang(Long.parseLong(strNhkbchuyen), conn);
                    dtsoatForm.setMa_don_vi_tra_soat(dmnh.getMa_nh());
                    dtsoatForm.setTen_don_vi_tra_soat(dmnh.getTen());
                } else {
                    dtsoatForm.setNhkb_chuyen(null);
                    dtsoatForm.setMa_don_vi_tra_soat(null);
                    dtsoatForm.setTen_don_vi_tra_soat(null);
                }
                if (request.getParameter("nhkbnhan") != null) {
                    String strNhkbnhan = request.getParameter("nhkbnhan");
                    dtsoatForm.setNhkb_nhan(strNhkbnhan);
                    dmnh = getDMNHang(Long.parseLong(strNhkbnhan), conn);
                    dtsoatForm.setMa_don_vi_nhan_tra_soat(dmnh.getMa_nh());
                    dtsoatForm.setTen_don_vi_nhan_tra_soat(dmnh.getTen());
                } else {
                    dtsoatForm.setNhkb_nhan(null);
                    dtsoatForm.setMa_don_vi_nhan_tra_soat(null);
                    dtsoatForm.setTen_don_vi_nhan_tra_soat(null);
                }
                if (request.getParameter("tungaylapdien") != null) {
                    dtsoatForm.setTu_ngay_lap_dien(request.getParameter("tungaylapdien").toString());
                } else {
                    dtsoatForm.setTu_ngay_lap_dien(null);
                }
                if (request.getParameter("denngaylapdien") != null) {
                    dtsoatForm.setDen_ngay_lap_dien(request.getParameter("denngaylapdien").toString());
                } else {
                    dtsoatForm.setDen_ngay_lap_dien(null);
                }
                if (request.getParameter("tungaylaplenh") != null) {
                    dtsoatForm.setTu_ngay_lap_lenh(request.getParameter("tungaylaplenh").toString());
                } else {
                    dtsoatForm.setTu_ngay_lap_lenh(null);
                }
                if (request.getParameter("denngaylaplenh") != null) {
                    dtsoatForm.setDen_ngay_lap_lenh(request.getParameter("denngaylaplenh").toString());
                } else {
                    dtsoatForm.setDen_ngay_lap_lenh(null);
                }
            }
            Vector vParam = new Vector();
            vParam.add(new Parameter(AppConstants.MA_THAM_CHIEU_TRANG_THAI_DTS,
                                     true));
            if (dtsoatForm.getSearch_dts() != null &&
                dtsoatForm.getSearch_dts()) {
                SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyyy");

                //dk nguoi lap id hoac ma
                whereClause += " AND (a.dts_id is not null )";
                whereClause += " AND (a.nhkb_chuyen =? or  a.nhkb_nhan =?)";
                whereClause += " AND a.loai_dts='K'";
                params.add(new Parameter(nhkbId, true));
                params.add(new Parameter(nhkbId, true));
                if (dtsoatForm.getTtv_nhan() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getTtv_nhan())) {
                    whereClause =
                            whereClause + " AND ( b.ma_nsd like (?) or b.ten_nsd like (?)) ";
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
                //dk don vi tra soat id
                if (dtsoatForm.getNhkb_chuyen() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getNhkb_chuyen())) {
                    whereClause += " AND (a.nhkb_chuyen = ? ) ";
                    params.add(new Parameter(dtsoatForm.getNhkb_chuyen(),
                                             true));
                }
                //dk don vi nhan tra soat id
                if (dtsoatForm.getNhkb_nhan() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getNhkb_nhan())) {
                    whereClause += " AND (a.nhkb_nhan = ? ) ";
                    params.add(new Parameter(dtsoatForm.getNhkb_nhan(), true));
                }
                //dk so lenh thanh toan
                if (dtsoatForm.getLtt_id() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getLtt_id())) {
                    whereClause += "  AND a.ltt_id = ? ";
                    params.add(new Parameter(dtsoatForm.getLtt_id(), true));
                }
                //dk so dien tra soat
                if (dtsoatForm.getId() != null &&
                    !"".equalsIgnoreCase(dtsoatForm.getId())) {
                    whereClause += "  AND a.id = ? ";
                    params.add(new Parameter(dtsoatForm.getId(), true));
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
                // khai bao file nguon, file dich
                String username = session.getAttribute("id_nsd").toString();
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
//            e.printStackTrace();
        } finally {
            request.setAttribute("lstDTS", lstDTS);
            close(conn);
        }

        return mapping.findForward(AppConstants.SUCCESS);
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

    protected String getStateBU(HttpServletRequest request,
                                HttpServletResponse response,
                                Connection conn) {
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

    protected DTSoatVO getDTSById(String id, Connection conn) {
        DTSoatVO dtsoatVO = null;
        String whereClause = "";
        Vector params = new Vector();
        Collection colNHKB = null;
        DMNHangVO dmNHVO = null;
        try {
            whereClause = " AND  a.id=? AND c.rv_domain='SP_DTS.TRANG_THAI'";
            if (id != null && !id.equals("")) {
                params = new Vector();
                params.add(new Parameter(id, true));
                DTSoatDAO dtsoatDAO = new DTSoatDAO(conn);
                dtsoatVO = dtsoatDAO.getDTS(whereClause, params);
                if (dtsoatVO != null) {
                    //lay ten ma danh muc ngan hang
                    whereClause = " id =? or id=?  ";
                    params = new Vector();
                    params.add(new Parameter(dtsoatVO.getNhkb_chuyen(), true));
                    params.add(new Parameter(dtsoatVO.getNhkb_nhan(), true));
                    DMNHangDAO dmNHangDAO = new DMNHangDAO(conn);
                    colNHKB = dmNHangDAO.getDMNHList(whereClause, params);
                    if (colNHKB != null && colNHKB.size() > 0) {
                        for (Iterator iter = colNHKB.iterator();
                             iter.hasNext(); ) {
                            dmNHVO = (DMNHangVO)iter.next();
                            if (dtsoatVO.getNhkb_chuyen().toString().equals(dtsoatVO.getNhkb_nhan().toString())) {
                                dtsoatVO.setMa_don_vi_tra_soat(dmNHVO.getMa_nh());
                                dtsoatVO.setTen_don_vi_tra_soat(dmNHVO.getTen());
                                dtsoatVO.setMa_don_vi_nhan_tra_soat(dmNHVO.getMa_nh());
                                dtsoatVO.setTen_don_vi_nhan_tra_soat(dmNHVO.getTen());
                            } else {
                                if (dtsoatVO.getNhkb_chuyen().toString().equals(dmNHVO.getId().toString())) {
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
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
        return dtsoatVO;
    }

    protected DMNHangVO getDMNHang(Long nhkbId, Connection conn) {
        DMNHangVO dmnhVO = null;
        Vector params = null;
        Parameter param = null;
        String whereClause = "";

        try {
            DMNHangDAO dmnhDao = new DMNHangDAO(conn);
            whereClause = " a.id=? ";
            params = new Vector();
            param = new Parameter(nhkbId, true);
            params.add(param);
            dmnhVO = dmnhDao.getDMNH(whereClause, params);
        } catch (Exception ex) {
//            ex.printStackTrace();
        }

        return dmnhVO;
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
                reportName = "BCDTSTraLoi";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    AppConstants.REPORT_DIRECTORY +
                    AppConstants.FONT_FOR_REPORT;
                parameterMap = new HashMap();
                parameterMap.put("P_ID", dtsForm.getId());
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
                String strActionName = "dtsoattloiRpt.do";
                String strParameter = "";

                ReportUtility rpUtilites = new ReportUtility();
                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, reportName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
              //HungBM 2017009 -  update TT_IN sau khi da in bao cao thanh cong - BEGIN
              // kiem tra lan in
              if (dtsVO.getTt_in() == null || 0 == dtsVO.getTt_in()) {
                  DTSoatVO dtsVOForUpdate = new DTSoatVO();
                  dtsVOForUpdate.setId(dtsVO.getId());
                  dtsVOForUpdate.setTt_in(new Long("1"));
                  dtsDAO.update(dtsVOForUpdate);
                  conn.commit();
              }
              //HungBM 2017009 -  update TT_IN sau khi da in bao cao thanh cong - END
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

