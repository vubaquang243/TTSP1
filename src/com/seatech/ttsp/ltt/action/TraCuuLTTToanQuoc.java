package com.seatech.ttsp.ltt.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.DateUtils;
import com.seatech.framework.utils.FontUtil;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.ltt.form.LTTToanQuoc;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;
import com.seatech.ttsp.thamchieu.MaThamChieuVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TraCuuLTTToanQuoc extends AppAction {
    public TraCuuLTTToanQuoc() {
        super();
    }
    private static String STRING_EMPTY = "";
    private static String CAP_TINH = "5";
    private static String TRANG_THAI = "SP_LTT.TRANG_THAI";
    private static String FORMAT_NGAY_24H = "DD/MM/YYYY hh24:mi:ss";
    private static String SGD = "0003";

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        DMNHangDAO dmnhDAO = null;
        DMKBacDAO dmKBDAO = null;
        MaThamChieuDAO tchieuDAO = null;
        ArrayList<DMKBacVO> lstKBTinh = null;

//        ArrayList<DMNHangVO> lstNganHang = null;
        List lstNganHang = null;
        ArrayList<MaThamChieuVO> lstTrangThai = null;
        String whereClause = STRING_EMPTY;
        Vector params = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "LTT.TRACUU_TQUOC")) {
                return mapping.findForward("errorQuyen");
            }
            conn = getConnection(request);
            dmnhDAO = new DMNHangDAO(conn);
            dmKBDAO = new DMKBacDAO(conn);
            tchieuDAO = new MaThamChieuDAO(conn);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            List tienTe = tienTeDAO.simpleMaNgoaiTe("", null);
            
            // danh sach ngan hang tham gia TTSP
//            List dmucNH = null;
            TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
            lstNganHang = (List)TTdao.getDMucNH(null,null);
//            whereClause = " id in (select nh_id from sp_tknh_kb )";
//            lstNganHang =
//                    (ArrayList<DMNHangVO>)dmnhDAO.getDMNHList(whereClause,
//                                                              params);
            // danh sach kb tinh
            whereClause = " (a.cap=? or a.ma='" + SGD + "') ";
            params = new Vector();
            params.add(new Parameter(CAP_TINH, true));
            lstKBTinh =
                    (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(whereClause, params);
            // Danh sach trang thai
            whereClause = "a.rv_domain=?";
            params = new Vector();
            params.add(new Parameter(TRANG_THAI, true));
            lstTrangThai = (ArrayList<MaThamChieuVO>)tchieuDAO.getMaThamChieuList(whereClause, params);
            
            request.setAttribute("tienTe", tienTe);
        } catch (Exception ex) {
            throw new Exception("Tra c&#7913;u l&#7879;nh thanh to&#225;n to&#224;n qu&#7889;c : " +
                                ex);
        } finally {
            request.setAttribute("lstNganHang", lstNganHang);
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstTrangThai", lstTrangThai);
            close(conn);
        }
        return mapping.findForward("success");
    }

//    public ActionForward view(ActionMapping mapping, ActionForm form,
//                              HttpServletRequest request,
//                              HttpServletResponse response) throws Exception {
//        Connection conn = null;
//        DMNHangDAO dmnhDAO = null;
//        DMKBacDAO dmKBDAO = null;
//        MaThamChieuDAO tchieuDAO = null;
//        ArrayList<DMKBacVO> lstKBTinh = null;
////        ArrayList<DMNHangVO> lstNganHang = null;
//        ArrayList<MaThamChieuVO> lstTrangThai = null;
//        ArrayList<DMKBacVO> lstKBHuyen = null;
//        List lstNganHang=null;
//
//        String whereClause = STRING_EMPTY;
//        Vector params = null;
//        try {
//            if (isCancelled(request)) {
//                return mapping.findForward(AppConstants.FAILURE);
//            }
//            if (!checkPermissionOnFunction(request, "QTOAN.TRACUU")) {
//                return mapping.findForward("errorQuyen");
//            }
//            LTTToanQuoc f = (LTTToanQuoc)form;
//            if (f.getTinh() == null || STRING_EMPTY.equals(f.getTinh())) {
//                executeAction(mapping, form, request, response);
//            }
//            conn = getConnection(request);
//            dmKBDAO = new DMKBacDAO(conn);
//            dmnhDAO = new DMNHangDAO(conn);
//            tchieuDAO = new MaThamChieuDAO(conn);
//            if (!f.getTinh().equalsIgnoreCase(SGD)) {
//                whereClause = " a.ma_cha = ?";
//                params = new Vector();
//                params.add(new Parameter(f.getTinh(), true));
//            } else {
//                whereClause = " a.ma = ?";
//                params = new Vector();
//                params.add(new Parameter(f.getTinh(), true));
//            }
//            lstKBHuyen =
//                    (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(whereClause, params);
//            request.setAttribute("lstKBHuyen",
//                                 lstKBHuyen != null ? lstKBHuyen :
//                                 new ArrayList<DMKBacVO>());
//            // danh sach ngan hang tham gia TTSP
////            whereClause = " id in (select nh_id from sp_tknh_kb )";
////            params = new Vector();
////            lstNganHang =
////                    (ArrayList<DMNHangVO>)dmnhDAO.getDMNHList(whereClause,
////                                                              params);
//             TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
//            lstNganHang = (List)TTdao.getDMucNH(null);
//            // danh sach kb tinh
//            whereClause = " (a.cap=? or a.ma='" + SGD + "') ";
//            params = new Vector();
//            params.add(new Parameter(CAP_TINH, true));
//            lstKBTinh =
//                    (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(whereClause, params);
//            // Danh sach trang thai
//            whereClause = "a.rv_domain=?";
//            params = new Vector();
//            params.add(new Parameter(TRANG_THAI, true));
//            lstTrangThai =
//                    (ArrayList<MaThamChieuVO>)tchieuDAO.getMaThamChieuList(whereClause,
//                                                                           params);
//        } catch (Exception e) {
//            throw new Exception("Tra c&#7913;u l&#7879;nh thanh to&#225;n to&#224;n qu&#7889;c : : " +
//                                e.getMessage());
//        } finally {
//            request.setAttribute("lstNganHang", lstNganHang);
//            request.setAttribute("lstKBTinh", lstKBTinh);
//            request.setAttribute("lstTrangThai", lstTrangThai);
//            close(conn);
//        }
//        if (!response.isCommitted())
//            return mapping.findForward(AppConstants.SUCCESS);
//        else
//            return null;
//    }

    public ActionForward view(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            String strMaKB;
            String strJSON;
            String strWhereClause = "";
            conn = getConnection(request);
            DChieu1DAO ttdao = new DChieu1DAO(conn);
            Collection col = null;
            //            Collection colNH = null;
            HttpSession session = request.getSession();
            String kb_code =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            strMaKB = request.getParameter("ma_tinh").toString();
            DChieu1VO vo = new DChieu1VO();
            String strCap = " and ma=" + kb_code;
            vo = ttdao.getCap(strCap, null);
            String cap = vo.getCap();
            if ("0001".equals(kb_code) || "0002".equals(kb_code) ||
                "0003".equals(kb_code)) { // SGD TTTT
                if ("0003".equals(strMaKB) || "0001".equals(strMaKB)) {
                    strWhereClause += " and a.ma='0003'";
                } else {
                    strWhereClause +=
                            " and a.ma_cha = " + strMaKB + " and a.ma<>'0003'";
                }
                col = ttdao.getDMucKB_huyen(strWhereClause, null);
            } else {
                if ("5".equals(cap)) { // cap tinh
                    strWhereClause +=
                            " and a.ma_cha = " + strMaKB; // + " and ma=" + kb_code;
                    col = ttdao.getDMucKB_cha(strWhereClause, null);
                    //strWhereClause += " and a.id_cha=" + strMaKB;
                    col = ttdao.getDMucKB_huyen(strWhereClause, null);
                } else {
                    strWhereClause += " and a.ma=" + kb_code;
                    col = ttdao.getDMucKB_huyen(strWhereClause, null);
                }
            }

            JSONObject jsonRes = new JSONObject();
            response.setHeader("X-JSON", jsonRes.toString());
            java.lang.reflect.Type listType =
                new TypeToken<Collection<DChieu1VO>>() {
            }.getType();
            strJSON = new Gson().toJson(col, listType);

            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJSON.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            JSONObject jsonRes = new JSONObject();
            jsonRes.put("executeError",
                        FontUtil.unicodeEscape("Loi: " + e.getMessage()));

            response.setHeader("X-JSON", jsonRes.toString());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }


    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        LTTDAO lttDAO = null;
        DMNHangDAO dmnhDAO = null;
        DMKBacDAO dmKBDAO = null;
        MaThamChieuDAO tchieuDAO = null;
        ArrayList<DMKBacVO> lstKBHuyen = null;
        ArrayList<DMKBacVO> lstKBTinh = null;
//        ArrayList<DMNHangVO> lstNganHang = null;
        ArrayList<MaThamChieuVO> lstTrangThai = null;
        String whereClause = STRING_EMPTY;
        Vector params = null;
        List lstNganHang=null;
        List tienTe = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            //
            //if (!checkPermissionOnFunction(request, "QTOAN.TRACUU")) {
            if (!checkPermissionOnFunction(request, "LTT.TRACUU_TQUOC")) {
                return mapping.findForward("errorQuyen");
            } else {
                conn = getConnection(request);
                dmnhDAO = new DMNHangDAO(conn);
                dmKBDAO = new DMKBacDAO(conn);
                tchieuDAO = new MaThamChieuDAO(conn);
                // danh sach ngan hang tham gia TTSP
//                whereClause = " id in (select nh_id from sp_tknh_kb )";
//                lstNganHang =
//                        (ArrayList<DMNHangVO>)dmnhDAO.getDMNHList(whereClause,
//                                                                  params);
                TTThanhToanDAO TTdao = new TTThanhToanDAO(conn);
                DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
                lstNganHang = (List)TTdao.getDMucNH(null,null);
                tienTe = tienTeDAO.simpleMaNgoaiTe("", null);
                // danh sach kb tinh
                whereClause = " (a.cap=? or a.ma='" + SGD + "') ";
                params = new Vector();
                params.add(new Parameter(CAP_TINH, true));
                lstKBTinh =
                        (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(whereClause,
                                                                 params);
                // Danh sach trang thai
                whereClause = "a.rv_domain=?";
                params = new Vector();
                params.add(new Parameter(TRANG_THAI, true));
                lstTrangThai =
                        (ArrayList<MaThamChieuVO>)tchieuDAO.getMaThamChieuList(whereClause,
                                                                               params);

                lttDAO = new LTTDAO(conn);
                LTTToanQuoc f = (LTTToanQuoc)form;
                String actionBack = null;
                if (request.getParameter(AppConstants.REQUEST_ACTION) !=
                    null) {
                    actionBack =
                            request.getParameter(AppConstants.REQUEST_ACTION).toString();
                }
                if (actionBack != null &&
                    actionBack.equalsIgnoreCase("back")) {
                    String ma_nh_back = request.getParameter("ma_nh_back");
                    String tinh_back =
                        request.getParameter("tinh_back") != null ?
                        request.getParameter("tinh_back") : "";
                    String huyen_back = request.getParameter("huyen_back");
                    String trang_thai_back =
                        request.getParameter("trang_thai_back");
                    String loai_lenh_back =
                        request.getParameter("loai_lenh_back");
                    String tu_ltt_back = request.getParameter("tu_ltt_back");
                    String den_ltt_back = request.getParameter("den_ltt_back");
                    String tu_ngay_back = request.getParameter("tu_ngay_back");
                    String den_ngay_back =
                        request.getParameter("den_ngay_back");
                    String tu_ngay_nhan_back =
                        request.getParameter("tu_ngay_nhan_back");
                    String den_ngay_nhan_back =
                        request.getParameter("den_ngay_nhan_back");
                    String so_tien_back = request.getParameter("so_tien_back");
                    String ma_nt_back = request.getParameter("ma_nt_back");
                    if (f != null && f.getTinh() != null) {
                        if (!f.getTinh().equalsIgnoreCase(SGD)) {
                            whereClause = " a.ma_cha = ?";
                            params = new Vector();
                            params.add(new Parameter(f.getTinh(), true));
                        } else {
                            whereClause = " a.ma = ?";
                            params = new Vector();
                            params.add(new Parameter(f.getTinh(), true));
                        }
                        lstKBHuyen =
                                (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(whereClause,
                                                                         params);
                        request.setAttribute("lstKBHuyen",
                                             lstKBHuyen != null ? lstKBHuyen :
                                             new ArrayList<DMKBacVO>());
                    } else {
                        if (!tinh_back.equalsIgnoreCase(SGD)) {
                            whereClause = " a.ma_cha = ?";
                            params = new Vector();
                            params.add(new Parameter(tinh_back, true));
                        } else {
                            whereClause = " a.ma = ?";
                            params = new Vector();
                            params.add(new Parameter(tinh_back, true));
                        }
                        lstKBHuyen =
                                (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(whereClause,
                                                                         params);
                        request.setAttribute("lstKBHuyen",
                                             lstKBHuyen != null ? lstKBHuyen :
                                             new ArrayList<DMKBacVO>());
                    }

                    if (so_tien_back != null &&
                        !STRING_EMPTY.equals(so_tien_back)) {
                        f.setSo_tien(new Long(so_tien_back));
                    } else {
                        f.setSo_tien(null);
                    }
                    if (den_ngay_back != null &&
                        !STRING_EMPTY.equals(den_ngay_back)) {
                        f.setDen_ngay(den_ngay_back);
                    } else {
                        f.setDen_ngay(null);
                    }
                    if (tu_ngay_back != null &&
                        !STRING_EMPTY.equals(tu_ngay_back)) {
                        f.setTu_ngay(tu_ngay_back);
                    } else {
                        f.setTu_ngay(null);
                    }
                    if (den_ngay_nhan_back != null &&
                        !STRING_EMPTY.equals(den_ngay_nhan_back)) {
                        f.setDen_ngay_nhan(den_ngay_nhan_back);
                    } else {
                        f.setDen_ngay_nhan(null);
                    }
                    if (tu_ngay_nhan_back != null &&
                        !STRING_EMPTY.equals(tu_ngay_nhan_back)) {
                        f.setTu_ngay_nhan(tu_ngay_nhan_back);
                    } else {
                        f.setTu_ngay_nhan(null);
                    }
                    if (den_ltt_back != null &&
                        !STRING_EMPTY.equals(den_ltt_back)) {
                        f.setDen_ltt(den_ltt_back);
                    } else {
                        f.setDen_ltt(null);
                    }
                    if (tu_ltt_back != null &&
                        !STRING_EMPTY.equals(tu_ltt_back)) {
                        f.setTu_ltt(tu_ltt_back);
                    } else {
                        f.setTu_ltt(null);
                    }
                    if (loai_lenh_back != null &&
                        !STRING_EMPTY.equals(loai_lenh_back)) {
                        f.setLoai_lenh(loai_lenh_back);
                    } else {
                        f.setLoai_lenh(null);
                    }
                    if (trang_thai_back != null &&
                        !STRING_EMPTY.equals(trang_thai_back)) {
                        f.setTrang_thai(trang_thai_back);
                    } else {
                        f.setTrang_thai(null);
                    }
                    if (ma_nh_back != null &&
                        !STRING_EMPTY.equals(ma_nh_back)) {
                        f.setMa_nh(ma_nh_back);
                    } else {
                        f.setMa_nh(null);
                    }
                    if (tinh_back != null && !STRING_EMPTY.equals(tinh_back)) {
                        f.setTinh(tinh_back);
                    } else {
                        f.setTinh(null);
                    }
                    if (ma_nt_back != null && !STRING_EMPTY.equals(ma_nt_back)) {
                        f.setMa_nt(ma_nt_back);
                    } else {
                        f.setMa_nt(null);
                    }
                    if (huyen_back != null &&
                        !STRING_EMPTY.equals(huyen_back)) {
                        f.setHuyen(huyen_back);
                    } else {
                        f.setHuyen(null);
                    }
                }
                //                String strCutOfTime =
                //                    getThamSoHThong("CUT_OF_TIME"); //16:30:00
                //                if (strCutOfTime == null || strCutOfTime.isEmpty() ||
                //                    strCutOfTime.equalsIgnoreCase("null")) {
                //                    throw TTSPException.createException("TTSP-1001",
                //                                                        "Khong lay duoc gio CUT OF TIME cua he thong");
                //                    //strCutOfTime = "16:30:00";
                //                }
                //
                //                String strCurrDate = StringUtil.getCurrentDate();
                //                String strPreviousDate = StringUtil.getPreviousNextDate(-1);
                //
                //                //So sanh thoi gian hien tai va cutoftime
                //                if (!LTTCommon.isCurTimeLessThanCutOfTime(strCutOfTime.trim())) {
                //                    strPreviousDate = StringUtil.getCurrentDate();
                //                    strCurrDate = StringUtil.getPreviousNextDate(+1);
                //                }
                //                if (!strCutOfTime.startsWith(" "))
                //                    strCutOfTime = " " + strCutOfTime;
                //                strPreviousDate += strCutOfTime;
                //                strCurrDate += strCutOfTime;

                params = new Vector();
                whereClause = "";
                if (f.getMa_nh() != null &&
                    !STRING_EMPTY.equals(f.getMa_nh())) {
                    whereClause += " and (( substr(e.ma_nh,3,3) = ? ) OR ( substr(f.ma_nh,3,3) = ? )) ";
                    params.add(new Parameter(f.getMa_nh(), true));
                    params.add(new Parameter(f.getMa_nh(), true));
                }
                if (f.getTinh() != null && !STRING_EMPTY.equals(f.getTinh())) {
                    if (f.getHuyen() != null &&
                        !STRING_EMPTY.equals(f.getHuyen())) {
                        String strWhereNHKB =
                            " (SELECT	 d1.id FROM	 sp_dm_ngan_hang d1, sp_dm_manh_shkb d2 WHERE	 d1.ma_nh = d2.ma_nh and d2.shkb = ?) ";
                        whereClause +=
                                " and (t.nhkb_nhan = " + strWhereNHKB + " or t.nhkb_chuyen= " +
                                strWhereNHKB + ")";
                        params.add(new Parameter(f.getHuyen(), true));
                        params.add(new Parameter(f.getHuyen(), true));
                    } else {
                        String strWhereNHKBTinh =
                            "(SELECT   d1.id FROM   sp_dm_ngan_hang d1, sp_dm_manh_shkb d2, sp_dm_htkb d3" + 
                            " WHERE 		d1.ma_nh = d2.ma_nh AND d2.shkb = d3.ma AND d3.ma_cha = ?)";
                        whereClause +=
                                " and (t.nhkb_nhan in(" + strWhereNHKBTinh +
                                ")" + " or t.nhkb_chuyen in(" +
                                strWhereNHKBTinh + "))";
                        params.add(new Parameter(f.getTinh(), true));
                        params.add(new Parameter(f.getTinh(), true));
                    }
                }
                if ((f.getDen_ltt() != null &&
                     !STRING_EMPTY.equals(f.getDen_ltt()))) {
                    whereClause += " and t.id <= ? ";
                    params.add(new Parameter(f.getDen_ltt(), true));
                }
                if ((f.getTu_ltt() != null &&
                     !STRING_EMPTY.equals(f.getTu_ltt()))) {
                    whereClause += " and t.id >= ?";
                    params.add(new Parameter(f.getTu_ltt(), true));
                }
                if (f.getSo_tien() != null &&
                    !STRING_EMPTY.equals(f.getSo_tien()) &&
                    f.getSo_tien() != 0) {
                    whereClause += " and t.tong_sotien = ?";
                    params.add(new Parameter(f.getSo_tien(), true));
                }
                if (f.getTrang_thai() != null &&
                    !STRING_EMPTY.equals(f.getTrang_thai())) {
                    whereClause += " and t.trang_thai = ?";
                    params.add(new Parameter(f.getTrang_thai(), true));
                }
                if (f.getTu_ngay() != null &&
                    !STRING_EMPTY.equals(f.getTu_ngay())) {
                    Long lTu_ngay = DateUtils.DateToLong(f.getTu_ngay());
                    whereClause += " and t.ngay_tt >= ?";
                    params.add(new Parameter(lTu_ngay, true));
                }
                if (f.getDen_ngay() != null &&
                    !STRING_EMPTY.equals(f.getDen_ngay())) {
                    Long lDen_ngay = DateUtils.DateToLong(f.getDen_ngay());
                    whereClause += " and t.ngay_tt <= ?";
                    params.add(new Parameter(lDen_ngay, true));
                }
                if (f.getTu_ngay_nhan() != null &&
                    !STRING_EMPTY.equals(f.getTu_ngay_nhan())) {
                    whereClause += " and trunc(t.ngay_nhan) >= to_date(?,'dd/mm/yyyy')";
                    params.add(new Parameter(f.getTu_ngay_nhan(), true));
                }
                if (f.getDen_ngay_nhan() != null &&
                    !STRING_EMPTY.equals(f.getDen_ngay_nhan())) {
                    whereClause += " and trunc(t.ngay_nhan) <= to_date(?,'dd/mm/yyyy')";
                    params.add(new Parameter(f.getDen_ngay_nhan(), true));
                }
                //                else {
                //                    Long lDen_ngay =
                //                        DateUtils.DateToLong(StringUtil.getCurrentDate());
                //                    whereClause += " and t.ngay_tt <= ?";
                //                    params.add(new Parameter(lDen_ngay, true));
                //                }
                if (f.getLoai_lenh() != null &&
                    !STRING_EMPTY.equals(f.getLoai_lenh())) {
                    String whereLoaiLenh = "";
                    if (f.getLoai_lenh().equals("01")) {
                        whereLoaiLenh = " (instr(t.id, '701') = 3) ";
                    } else if (f.getLoai_lenh().equals("02")) {
                        whereLoaiLenh = " (instr(t.id, '701') <> 3) ";
                    } else if (f.getLoai_lenh().equals("03")) {
                      whereLoaiLenh = " t.ttloai_lenh = '03' ";
                    }
                    whereClause += " and " + whereLoaiLenh;
                }
                
                if (f.getMa_nt() != null && !STRING_EMPTY.equals(f.getMa_nt())) {
                    whereClause += " and a.ma = ?";
                    params.add(new Parameter(f.getMa_nt(), true));
                }
                if(f.getPhi() !=null && !STRING_EMPTY.equals(f.getPhi())){
                  whereClause += " and t.phi = ? ";
                   params.add(new Parameter(f.getPhi(), true));
                }
                
                String page = f.getPageNumber();
                if (page == null) {
                    page = "1";
                }
                // khai bao cac bien phan trang
                Integer currentPage = new Integer(page);
                Integer numberRowOnPage = new Integer(15);
                Integer totalCount[] = new Integer[1];
                ArrayList<LTTVO> lstLTTTmp = (ArrayList<LTTVO>)lttDAO.getLTTTQListWithPading(whereClause, params, currentPage, numberRowOnPage, totalCount);
                LTTVO voSum = new LTTVO();
                voSum = lttDAO.getSumTongTienTQ(whereClause.toString(), params);
                request.setAttribute("tong_mon", voSum.getTong_mon());
                request.setAttribute("lltVO", voSum);

                ArrayList<LTTVO> lstLTT = new ArrayList<LTTVO>();
                for (LTTVO vo : lstLTTTmp) {
                    if (vo.getNgay_tt() != null &&
                        !STRING_EMPTY.equals(vo.getNgay_tt())) {
                        vo.setNgay_tt_tmp(DateUtils.LongToStrDateDDMMYYYY(vo.getNgay_tt()));
                    }
                    lstLTT.add(vo);
                }
              String inKB = request.getParameter("inKB");
                request.setAttribute("kb_huyen", inKB);
                
                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("lstLTT", lstLTT);
                request.setAttribute("PAGE_KEY", pagingBean);
                if (actionBack == null ||
                    !actionBack.equalsIgnoreCase("back")) {
                    view(mapping, form, request, response);
                }
            }
        } catch (Exception e) {
            throw new Exception("Tra c&#7913;u l&#7879;nh thanh to&#225;n to&#224;n qu&#7889;c : " +
                                e.getMessage());
        } finally {
            request.setAttribute("lstNganHang", lstNganHang);
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstTrangThai", lstTrangThai);
            request.setAttribute("tienTe", tienTe);
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
