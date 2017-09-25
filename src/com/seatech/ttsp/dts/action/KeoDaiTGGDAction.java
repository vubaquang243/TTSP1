package com.seatech.ttsp.dts.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;
import com.seatech.ttsp.dts.form.DTSoatForm;
import com.seatech.ttsp.ltt.LTTCommon;
import com.seatech.ttsp.proxy.giaodien.Send299;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;
import com.seatech.ttsp.thamchieu.MaThamChieuVO;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;
import com.seatech.ttsp.ttthanhtoan.DMucNHVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserVO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class KeoDaiTGGDAction extends AppAction {
    public KeoDaiTGGDAction() {
        super();
    }
    private static final String DATE_FORMAT_CUTOFTIME =
        "dd/MM/yyyy HH24:MI:SS";
    private static final String STRING_EMPTY = "";

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "DTS.KDAI_TGGD")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        String strWhereClause = null;
        Vector vParams = null;
        String strCutOfTime, strCurrDate, strPreviousDate;
        DTSoatDAO dtsDAO = null;
        ArrayList<DTSoatVO> lstDTSDi = null;
        ArrayList<DTSoatVO> lstDTSDen = null;
        Vector vParamsDMHO = null;
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String strPhanQuyen =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            Long lUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            String strChucDanh = "";
            // neu strChucDanh = null thi ttv ko co chuc danh ==> return false
            if (strPhanQuyen != null && !STRING_EMPTY.equals(strPhanQuyen)) {
                if (strPhanQuyen.indexOf(AppConstants.NSD_TTV) != -1) {
                    strChucDanh = AppConstants.NSD_TTV;
                } else if (strPhanQuyen.indexOf(AppConstants.NSD_GD) != -1 ||
                           strPhanQuyen.indexOf(AppConstants.NSD_KTT) != -1) {
                    strChucDanh = AppConstants.NSD_KTT;
                }
            }
            if (strChucDanh == null || STRING_EMPTY.equals(strChucDanh)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            // get cutoftime
            ThamSoHThongDAO thamsoDAO = new ThamSoHThongDAO(conn);
            ThamSoHThongVO thamsoVO =
                thamsoDAO.getThamSo(" ten_ts='CUT_OF_TIME'", null);
            strCutOfTime = thamsoVO.getGiatri_ts();
            strCurrDate = StringUtil.getCurrentDate();
            strPreviousDate = StringUtil.getPreviousNextDate(-1);

            //So sanh thoi gian hien tai va cutoftime
            if (!LTTCommon.isCurTimeLessThanCutOfTime(strCutOfTime.trim())) {
                strPreviousDate = StringUtil.getCurrentDate();
                strCurrDate = StringUtil.getPreviousNextDate(+1);
            }
            if (!strCutOfTime.startsWith(" "))
                strCutOfTime = " " + strCutOfTime;
            strCurrDate += strCutOfTime;
            strPreviousDate += strCutOfTime;
            // end cutoftime
            dtsDAO = new DTSoatDAO(conn);
            if (strChucDanh.equals(AppConstants.NSD_TTV)) {
                String nhkb_chuyen_id =
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
                // lay danh sach dien di
                strWhereClause =
                        " (instr(a.id, '701') = 3) and (instr(a.id, '299') = 6) and (a.ttv_nhan is null or a.ttv_nhan=?) and a.nhkb_chuyen=? and ((a.ngay_nhan >= TO_DATE(?,?) and a.ngay_nhan <= TO_DATE(?,?) and " +
                        " a.trang_thai in (?,?,?)) or a.trang_thai in(?,?)) ORDER BY a.trang_thai, a.id desc";
                vParams = new Vector();
                vParams.add(new Parameter(lUserID, true));
                vParams.add(new Parameter(nhkb_chuyen_id, true));
                vParams.add(new Parameter(strPreviousDate, true));
                vParams.add(new Parameter(DATE_FORMAT_CUTOFTIME, true));
                vParams.add(new Parameter(strCurrDate, true));
                vParams.add(new Parameter(DATE_FORMAT_CUTOFTIME, true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_DA_XULY,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_DUYET,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_HUY, true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_CHO_DUYET,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_DAY_LAI,
                                          true));
                lstDTSDi =
                        (ArrayList<DTSoatVO>)dtsDAO.getDTSList(strWhereClause,
                                                               vParams);
                // lay danh sach dien den
                // lay danh sach dien di

                strWhereClause =
                        " (instr(a.id, '701') <> 3) and (instr(a.id, '299') = 6) and (a.ttv_nhan is null or a.ttv_nhan=?) and a.nhkb_nhan=? and ((a.ngay_nhan >= TO_DATE(?,?) and a.ngay_nhan <= TO_DATE(?,?) and " +
                        " a.trang_thai in (?,?,?)) or a.trang_thai in(?,?,?)) ORDER BY a.trang_thai, a.id desc";
                vParams = new Vector();
                vParams.add(new Parameter(lUserID, true));
                vParams.add(new Parameter(nhkb_chuyen_id, true));
                vParams.add(new Parameter(strPreviousDate, true));
                vParams.add(new Parameter(DATE_FORMAT_CUTOFTIME, true));
                vParams.add(new Parameter(strCurrDate, true));
                vParams.add(new Parameter(DATE_FORMAT_CUTOFTIME, true));

                vParams.add(new Parameter(AppConstants.TRANG_THAI_DUYET,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_HUY, true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_DA_XULY,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_CHO_XULY,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_CHO_DUYET,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_DAY_LAI,
                                          true));
                lstDTSDen =
                        (ArrayList<DTSoatVO>)dtsDAO.getDTSList(strWhereClause,
                                                               vParams);

            } else {
                String nhkb_chuyen_id =
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
                // lay danh sach dien di
                strWhereClause =
                        " (instr(a.id, '701') = 3) and (instr(a.id, '299') = 6) and (a.ktt_duyet is null or a.ktt_duyet =?) and a.nhkb_chuyen=? and ((a.ngay_duyet >= TO_DATE(?,?) and a.ngay_duyet <= TO_DATE(?,?) and " +
                        " a.trang_thai in (?,?,?)) or  a.trang_thai in (?,?)) ORDER BY a.trang_thai, a.id desc";
                vParams = new Vector();
                vParams.add(new Parameter(lUserID, true));
                vParams.add(new Parameter(nhkb_chuyen_id, true));
                vParams.add(new Parameter(strPreviousDate, true));
                vParams.add(new Parameter(DATE_FORMAT_CUTOFTIME, true));
                vParams.add(new Parameter(strCurrDate, true));
                vParams.add(new Parameter(DATE_FORMAT_CUTOFTIME, true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_DUYET,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_DA_XULY,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_HUY, true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_DAY_LAI,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_CHO_DUYET,
                                          true));


                lstDTSDi =
                        (ArrayList<DTSoatVO>)dtsDAO.getDTSList(strWhereClause,
                                                               vParams);
                // lay danh sach dien den
                // lay danh sach dien di

                strWhereClause =
                        " (instr(a.id, '701') <> 3) and (instr(a.id, '299') = 6) and (a.ktt_duyet is null or a.ktt_duyet =?) and a.nhkb_nhan=? and ((a.ngay_duyet >= TO_DATE(?,?) and a.ngay_duyet <= TO_DATE(?,?) and " +
                        " a.trang_thai in (?,?,?)) or  a.trang_thai in (?,?)) ORDER BY a.trang_thai, a.id desc";
                vParams = new Vector();
                vParams.add(new Parameter(lUserID, true));
                vParams.add(new Parameter(nhkb_chuyen_id, true));
                vParams.add(new Parameter(strPreviousDate, true));
                vParams.add(new Parameter(DATE_FORMAT_CUTOFTIME, true));
                vParams.add(new Parameter(strCurrDate, true));
                vParams.add(new Parameter(DATE_FORMAT_CUTOFTIME, true));

                vParams.add(new Parameter(AppConstants.TRANG_THAI_DUYET,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_HUY, true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_DA_XULY,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_CHO_DUYET,
                                          true));
                vParams.add(new Parameter(AppConstants.TRANG_THAI_DAY_LAI,
                                          true));
                lstDTSDen =
                        (ArrayList<DTSoatVO>)dtsDAO.getDTSList(strWhereClause,
                                                               vParams);
            }
            // Lay ra danh sach dm ngan hang ho
            TTThanhToanDAO hoDAO = new TTThanhToanDAO(conn);
            vParamsDMHO = new Vector();
            List<DMucNHVO> lstNHHO =
                (List<DMucNHVO>)hoDAO.getDMucNH(null,vParamsDMHO);
            if (lstNHHO == null) {
                lstNHHO = new ArrayList<DMucNHVO>();
            }
            request.setAttribute("lstNHHO", lstNHHO);
            request.setAttribute("lstDTSDi", lstDTSDi);
            request.setAttribute("lstDTSDen", lstDTSDen);
            request.setAttribute("chucdanh", strChucDanh);


        } catch (Exception e) {
            // TODO: Add catch code
            throw e;
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
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "DTS.KDAI_TGGD")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        DTSoatDAO dtsDAO = null;
        String strChucDanh = null;
        String strJson = null;
        try {
            conn = getConnection();
            dtsDAO = new DTSoatDAO(conn);
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            DTSoatVO dtsVO = dtsDAO.findByPKWithDate(id);

            String strPhanQuyen =
                session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) !=
                null ?
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) :
                "";
            if (!STRING_EMPTY.equals(strPhanQuyen)) {
                if (strPhanQuyen.equals(AppConstants.NSD_TTV)) {
                    strChucDanh = AppConstants.NSD_TTV;
                } else if (strPhanQuyen.equals(AppConstants.NSD_KTT) ||
                           strPhanQuyen.equals(AppConstants.NSD_GD)) {
                    strChucDanh = AppConstants.NSD_KTT;
                }
            }
            // mo ta trang thai
            String strWhereClauseMTC =
                " rv_domain = 'SP_DTS.TRANG_THAI' and rv_low_value = ?";
            Vector vParam = new Vector();
            vParam.add(new Parameter(dtsVO.getTrang_thai(), true));
            MaThamChieuDAO matcDAO = new MaThamChieuDAO(conn);
            MaThamChieuVO matcVO =
                matcDAO.getMaThamChieuByKey(strWhereClauseMTC, vParam);
            dtsVO.setMo_ta_trang_thai(matcVO.getRv_meaning());
            //end
            dtsVO.setChuc_danh(strChucDanh);
            Type listType = new TypeToken<DTSoatVO>() {
            }.getType();
            strJson = new Gson().toJson(dtsVO, listType);
        } catch (Exception e) {
            // TODO: Add catch code
            throw e;
        } finally {
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
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

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "DTS.KDAI_TGGD")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        DTSoatDAO dtsDAO = null;
        String strJson = null;
        try {
            conn = getConnection();
            dtsDAO = new DTSoatDAO(conn);
            HttpSession session = request.getSession();
            String strNHKB_chuyen =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) !=
                null ?
                (String)session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION) :
                "";
            String strTenNHKB_chuyen =
                session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION) !=
                null ?
                (String)session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION) :
                "";
            Long strIDNHKB_chuyen =
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION) !=
                null ?
                (Long)(session.getAttribute(AppConstants.APP_NHKB_ID_SESSION)) :
                0;
            Long lUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            String userCode =
                session.getAttribute(AppConstants.APP_USER_CODE_SESSION) !=
                null ?
                (String)session.getAttribute(AppConstants.APP_USER_CODE_SESSION) :
                "";
            String strUserName =
                session.getAttribute(AppConstants.APP_USER_NAME_SESSION) !=
                null ?
                (String)session.getAttribute(AppConstants.APP_USER_NAME_SESSION) :
                "";
            DTSoatVO dtsVO = new DTSoatVO();
            dtsVO.setNhkb_chuyen(strIDNHKB_chuyen);
            dtsVO.setTen_don_vi_tra_soat(strTenNHKB_chuyen);
            dtsVO.setMa_don_vi_tra_soat(strNHKB_chuyen);
            dtsVO.setTtv_nhan(lUserID);
            dtsVO.setMa_ttv_nhan(userCode);
            dtsVO.setTen_ttv_nhan(strUserName);
            dtsVO.setNgay_nhan(StringUtil.getCurrentDate());
            dtsVO.setNoidung("&#272;&#7873; ngh&#7883; k&#233;o d&#224;i th&#7901;i gian giao d&#7883;ch TT&#272;TSPTT gi&#7919;a KBNN v&#224; NHTM ");
            Type listType = new TypeToken<DTSoatVO>() {
            }.getType();
            strJson = new Gson().toJson(dtsVO, listType);
        } catch (Exception e) {
            // TODO: Add catch code
            throw e;
        } finally {
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
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

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "DTS.KDAI_TGGD")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        DTSoatDAO dtsDAO = null;
        DTSoatVO dtsVO = null;
        try {
            conn = getConnection();
            dtsDAO = new DTSoatDAO(conn);
            DTSoatForm f = (DTSoatForm)form;
            //HttpSession session = request.getSession();
            String gio_keo_dai =
                (f.getGio_keo_dai() != null && !STRING_EMPTY.equals(f.getGio_keo_dai())) ?
                f.getGio_keo_dai() : "00";
            String phut_keo_dai =
                (f.getPhut_keo_dai() != null && !STRING_EMPTY.equals(f.getPhut_keo_dai())) ?
                f.getPhut_keo_dai() : "00";
            String thoi_gian_keo_dai =
                f.getThoi_gian_keo_dai() + " " + gio_keo_dai + ":" +
                phut_keo_dai + ":00";
            f.setThoi_gian_keo_dai(thoi_gian_keo_dai);
            dtsVO = new DTSoatVO();
            BeanUtils.copyProperties(dtsVO, f);
            dtsVO.setTrang_thai(AppConstants.TRANG_THAI_DA_XULY);
            // end
            String type = "299";
            TTSPUtils ttspUtil = new TTSPUtils(conn);
            String strID = ttspUtil.getSoDTS(type);

            // lay thong tin de send message
            // lay ten NSD
            String strTTV = "";
            strTTV = f.getMa_ttv_nhan();
            String strMTID = strID;
            String strSendBank =
                f.getMa_don_vi_tra_soat() != null ? f.getMa_don_vi_tra_soat().toString() :
                "";
            String strRecieveBank = f.getMa_don_vi_nhan_tra_soat() !=null ? f.getMa_don_vi_nhan_tra_soat() : "";
            String strCreator = strTTV != null ? strTTV : "";
            String strManager = strTTV != null ? strTTV : "";
            // so tham chieu id
            String strMaThamChieu = strID;
            String strThoiGianKeoDai =
                f.getThoi_gian_keo_dai().replace("/", "-");
            String strNoiDungTraLoi = f.getNoidung_traloi()!=null? f.getNoidung_traloi():"";
            String strMsgRefID = f.getMsg_id() != null ? f.getMsg_id() : "";
            Send299 sendMsg = new Send299(conn);
            String strMsgID ="";
//                sendMsg.sendMessage(strMTID, strSendBank, strRecieveBank,
//                                    strCreator, strManager, strMaThamChieu,
//                                    strThoiGianKeoDai, strNoiDungTraLoi,
//                                    strMsgRefID);
            // end sendMsg
            dtsVO.setMsg_id(strMsgID);
            String idCreate = dtsDAO.insert(dtsVO, strID);
            if (idCreate != "-1") {
                request.setAttribute("createSuccess", idCreate);
                conn.commit();
            }
        } catch (Exception e) {
            // TODO: Add catch code
            throw e;
        } finally {
            executeAction(mapping, form, request, response);
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
        if (isCancelled(request)) {
            return mapping.findForward(AppConstants.FAILURE);
        }
        if (!checkPermissionOnFunction(request, "DTS.KDAI_TGGD")) {
            return mapping.findForward("errorQuyen");
        }
        Connection conn = null;
        DTSoatDAO dtsDAO = null;
        DTSoatVO dtsVO = null;
        try {
            conn = getConnection();
            dtsDAO = new DTSoatDAO(conn);
            DTSoatForm f = (DTSoatForm)form;
            HttpSession session = request.getSession();
            dtsVO = new DTSoatVO();
            Long lUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            // end
            String strAction =
                request.getParameter(AppConstants.REQUEST_ACTION) != null ?
                request.getParameter(AppConstants.REQUEST_ACTION) : "";

            dtsVO.setId(request.getParameter("id") != null ?
                        request.getParameter("id") : "");
            if (STRING_EMPTY.equals(dtsVO.getId())) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (STRING_EMPTY.equals(strAction)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (strAction.equals("ACTION_TL")) {
                // lay thong tin de send message
                DTSoatVO voForSendMsg = new DTSoatVO();
                voForSendMsg = dtsDAO.findByPKWithDate(f.getId());
                // lay ten NSD
                String strTTV = "";
                String strKTT = "";
                Vector paramUser = null;
                String whereClauseUser = " a.id=? ";
                if (voForSendMsg.getTtv_nhan() != null &&
                    0 != voForSendMsg.getTtv_nhan()) {
                    paramUser = new Vector();
                    paramUser.add(new Parameter(voForSendMsg.getTtv_nhan(),
                                                true));
                    UserDAO userDAO = new UserDAO(conn);
                    UserVO userVO = new UserVO();
                    userVO = userDAO.getUser(whereClauseUser, paramUser);
                    strTTV = userVO.getTen_nsd();
                }
                if (lUserID != null && 0 != lUserID) {
                    paramUser = new Vector();
                    paramUser.add(new Parameter(lUserID, true));
                    UserDAO userDAO = new UserDAO(conn);
                    UserVO userVO = new UserVO();
                    userVO = userDAO.getUser(whereClauseUser, paramUser);
                    strKTT = userVO.getTen_nsd();
                }
                String strMTID = voForSendMsg.getId();
                String strSendBank =
                    voForSendMsg.getMa_don_vi_tra_soat() != null ?
                    voForSendMsg.getMa_don_vi_tra_soat().toString() : "";
                String strRecieveBank =
                    voForSendMsg.getMa_don_vi_nhan_tra_soat() != null ?
                    voForSendMsg.getMa_don_vi_nhan_tra_soat().toString() : "";
                String strCreator = strTTV != null ? strTTV : "";
                String strManager = strKTT != null ? strKTT : "";
                String strMaThamChieu = voForSendMsg.getSo_tchieu();
                String strThoiGianKeoDai =
                    voForSendMsg.getThoi_gian_keo_dai().replace("/", "-");
                String strNoiDungTraLoi = voForSendMsg.getNoidung_traloi();
                String strMsgRefID =
                    voForSendMsg.getMsg_id() != null ? voForSendMsg.getMsg_id() :
                    "";
                Send299 sendMsg = new Send299(conn);
                String strMsgID ="";
//                    sendMsg.sendMessage(strMTID, strSendBank, strRecieveBank,
//                                        strCreator, strManager, strMaThamChieu,
//                                        strThoiGianKeoDai, strNoiDungTraLoi,
//                                        strMsgRefID);
                // end sendMsg
                dtsVO.setMsg_id(strMsgID);
                dtsVO.setNoidung_traloi(f.getNoidung_traloi());
                dtsVO.setTrang_thai(AppConstants.TRANG_THAI_DA_XULY);
                // tao ban ghi moi co cac thong tin cua ban ghi cu va co idRef = id ban ghi cu
                DTSoatVO voForTraLoi = new DTSoatVO();
                BeanUtils.copyProperties(voForTraLoi, f);
                voForTraLoi.setSo_tchieu(dtsVO.getId());
                voForTraLoi.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
                // dao nhkb
                voForTraLoi.setNhkb_chuyen(voForTraLoi.getNhkb_nhan());
                voForTraLoi.setNhkb_nhan(voForTraLoi.getNhkb_chuyen());
                String type = "299";
                if (dtsDAO.insert(voForTraLoi, type).equals("-1")) {
                    throw TTSPException.createException("TTSP-9999",
                                                        "L&#7895;i khi th&#7921;c hi&#7879;n tr&#7843; l&#7901;i &#273;i&#7879;n y&#234;u c&#7847;u gia h&#7841;n th&#7901;i gian giao d&#7883;ch");
                }
            } else if (strAction.equals("ACTION_XACNHAN")) {
                dtsVO.setTtv_nhan(lUserID);
                dtsVO.setNgay_nhan(StringUtil.getCurrentDate());
                dtsVO.setTrang_thai(AppConstants.TRANG_THAI_DA_XULY);
            }
            //            else if (strAction.equals("ACTION_DUYET")) {
            //                dtsVO.setKtt_duyet(lUserID);
            //                dtsVO.setNgay_duyet(StringUtil.getCurrentDate());
            //                dtsVO.setTrang_thai(AppConstants.TRANG_THAI_DUYET);
            //                Send299 sendMsg = new Send299(conn);
            //                // lay thong tin de send message
            //                DTSoatVO voForSendMsg = new DTSoatVO();
            //                voForSendMsg = dtsDAO.findByPKWithDate(dtsVO.getId());
            //                // lay ten NSD
            //                String strTTV = "";
            //                String strKTT = "";
            //                Vector paramUser = null;
            //                String whereClauseUser = " a.id=? ";
            //                if (voForSendMsg.getTtv_nhan() != null &&
            //                    0 != voForSendMsg.getTtv_nhan()) {
            //                    paramUser = new Vector();
            //                    paramUser.add(new Parameter(voForSendMsg.getTtv_nhan(),
            //                                                true));
            //                    UserDAO userDAO = new UserDAO(conn);
            //                    UserVO userVO = new UserVO();
            //                    userVO = userDAO.getUser(whereClauseUser, paramUser);
            //                    strTTV = userVO.getTen_nsd();
            //                }
            //                if (lUserID != null &&
            //                    0 != lUserID) {
            //                    paramUser = new Vector();
            //                    paramUser.add(new Parameter(lUserID,
            //                                                true));
            //                    UserDAO userDAO = new UserDAO(conn);
            //                    UserVO userVO = new UserVO();
            //                    userVO = userDAO.getUser(whereClauseUser, paramUser);
            //                    strKTT = userVO.getTen_nsd();
            //                }
            //                //end
            //
            //                String strMTID = voForSendMsg.getId();
            //                String strSendBank =
            //                    voForSendMsg.getMa_don_vi_tra_soat() != null ?
            //                    voForSendMsg.getMa_don_vi_tra_soat().toString() : "";
            //                String strRecieveBank =
            //                    voForSendMsg.getMa_don_vi_nhan_tra_soat() != null ?
            //                    voForSendMsg.getMa_don_vi_nhan_tra_soat().toString() : "";
            //                String strCreator = strTTV != null ? strTTV : "";
            //                String strManager = strKTT != null ? strKTT : "";
            //                String strMaThamChieu = voForSendMsg.getSo_tchieu();
            //                String strThoiGianKeoDai =
            //                    voForSendMsg.getThoi_gian_keo_dai().replace("/", "-");
            //                String strNoiDungTraLoi = voForSendMsg.getNoidung_traloi();
            //                String strMsgRefID =
            //                    voForSendMsg.getMsg_id() != null ? voForSendMsg.getMsg_id() :
            //                    "";
            //                String strMsgID =
            //                    sendMsg.sendMessage(strMTID, strSendBank, strRecieveBank,
            //                                        strCreator, strManager, strMaThamChieu,
            //                                        strThoiGianKeoDai, strNoiDungTraLoi,
            //                                        strMsgRefID);
            //                dtsVO.setMsg_id(strMsgID);
            //                if (strMsgID.equals(STRING_EMPTY)) {
            //                    throw TTSPException.createException("TTSP-9999",
            //                                                        "Duy&#7879;t DTS kh&#244;ng th&#224;nh c&#244;ng");
            //                }
            //
            //            }
            //            else if (strAction.equals("ACTION_DAYLAI")) {
            //                /* neu la dien di update cac thong tin sau
            //                 * kttid
            //                 * ngay day lai
            //                */
            //                dtsVO.setKtt_duyet(lUserID);
            //                dtsVO.setNgay_duyet(StringUtil.getCurrentDate());
            //                dtsVO.setTrang_thai(AppConstants.TRANG_THAI_DAY_LAI);
            //                dtsVO.setLydo_ktt_day_lai(f.getLydo_ktt_day_lai());
            //            }
            //            else if (strAction.equals("ACTION_UPDATE")) {
            //                String gio_keo_dai =
            //                    (f.getGio_keo_dai() != null && !STRING_EMPTY.equals(f.getGio_keo_dai())) ?
            //                    f.getGio_keo_dai() : "00";
            //                String phut_keo_dai =
            //                    (f.getPhut_keo_dai() != null && !STRING_EMPTY.equals(f.getPhut_keo_dai())) ?
            //                    f.getPhut_keo_dai() : "00";
            //                String thoi_gian_keo_dai =
            //                    f.getThoi_gian_keo_dai() + " " + gio_keo_dai + ":" +
            //                    phut_keo_dai + ":00";
            //                dtsVO.setThoi_gian_keo_dai(thoi_gian_keo_dai);
            //                dtsVO.setTrang_thai(AppConstants.TRANG_THAI_CHO_DUYET);
            //            }
            int iUpdate = 0;
            if (!STRING_EMPTY.equals(dtsVO.getId()))
                iUpdate = dtsDAO.update(dtsVO);
            if (iUpdate == 1) {
                request.setAttribute(strAction, dtsVO.getId());
                conn.commit();
            }

        } catch (Exception e) {
            // TODO: Add catch code
            throw e;
        } finally {
            executeAction(mapping, form, request, response);
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
