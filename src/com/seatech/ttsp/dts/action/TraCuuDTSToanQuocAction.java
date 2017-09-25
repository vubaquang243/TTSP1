package com.seatech.ttsp.dts.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;
import com.seatech.ttsp.dts.form.TraCuuDTSToanQuocForm;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;
import com.seatech.ttsp.thamchieu.MaThamChieuVO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TraCuuDTSToanQuocAction extends AppAction {
    public TraCuuDTSToanQuocAction() {
        super();
    }
    private static String STRING_EMPTY = "";
    private static String CAP_TINH = "5";
    private static String TRANG_THAI = "SP_DTS";
    private static String FORMAT_NGAY_24H = "DD/MM/YYYY HH24:mi:ss";
    private static String SGD = "0003";

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        DMNHangDAO dmnhDAO = null;
        DMKBacDAO dmKBDAO = null;
        MaThamChieuDAO tchieuDAO = null;
        ArrayList<DMKBacVO> lstKBTinh = null;

        ArrayList<DMNHangVO> lstNganHang = null;
        ArrayList<MaThamChieuVO> lstTrangThai = null;
        String whereClause = STRING_EMPTY;
        Vector params = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "DTS.TRACUU_TQ")) {
                return mapping.findForward("errorQuyen");
            }
            conn = getConnection();
            dmnhDAO = new DMNHangDAO(conn);
            dmKBDAO = new DMKBacDAO(conn);
            tchieuDAO = new MaThamChieuDAO(conn);
            // danh sach ngan hang tham gia TTSP
            whereClause = " id in (select nh_id from sp_tknh_kb )";
            lstNganHang =
                    (ArrayList<DMNHangVO>)dmnhDAO.getDMNHList(whereClause,
                                                              params);
            // danh sach kb tinh
            whereClause = " (a.cap=? or a.ma='" + SGD + "') ";
            params = new Vector();
            params.add(new Parameter(CAP_TINH, true));
            lstKBTinh =
                    (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(whereClause, params);
            // Danh sach trang thai
            whereClause = " a.rv_domain like ?";
            params = new Vector();
            params.add(new Parameter("%" + TRANG_THAI + "%", true));
            lstTrangThai =
                    (ArrayList<MaThamChieuVO>)tchieuDAO.getMaThamChieuList(whereClause,
                                                                           params);


        } catch (Exception ex) {
            throw new Exception("Tra c&#7913;u &#273;i&#7879;n tra so&#225;t to&#224;n qu&#7889;c : " +
                                ex);
        } finally {
            request.setAttribute("lstNganHang", lstNganHang);
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstTrangThai", lstTrangThai);
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        ArrayList<DMKBacVO> lstKBHuyen = null;
        DMKBacDAO dmKBDAO = null;
        DMNHangDAO dmnhDAO = null;
        MaThamChieuDAO tchieuDAO = null;
        ArrayList<DMKBacVO> lstKBTinh = null;
        ArrayList<DMNHangVO> lstNganHang = null;
        ArrayList<MaThamChieuVO> lstTrangThai = null;
        String whereClause = STRING_EMPTY;
        Vector params = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "DTS.TRACUU_TQ")) {
                return mapping.findForward("errorQuyen");
            }
            TraCuuDTSToanQuocForm f = (TraCuuDTSToanQuocForm)form;
//            if (f.getTinh() == null || STRING_EMPTY.equals(f.getTinh())) {
//                executeAction(mapping, form, request, response);
//            }
            conn = getConnection();
            dmKBDAO = new DMKBacDAO(conn);
            if(f.getTinh()!=null){
                if (!f.getTinh().equalsIgnoreCase(SGD)) {
                    whereClause = " a.ma_cha = ?";
                    params = new Vector();
                    params.add(new Parameter(f.getTinh(), true));
                } else {
                    whereClause = " a.ma = ?";
                    params = new Vector();
                    params.add(new Parameter(f.getTinh(), true));
                }
            }
            lstKBHuyen =
                    (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(whereClause, params);
            // set lai thong tin


            dmnhDAO = new DMNHangDAO(conn);
            dmKBDAO = new DMKBacDAO(conn);
            tchieuDAO = new MaThamChieuDAO(conn);
            // danh sach ngan hang tham gia TTSP
            whereClause = " id in (select nh_id from sp_tknh_kb )";
            params = new Vector();
            lstNganHang =
                    (ArrayList<DMNHangVO>)dmnhDAO.getDMNHList(whereClause,
                                                              params);
            // danh sach kb tinh
            whereClause = " (a.cap=? or a.ma='" + SGD + "') ";
            params = new Vector();
            params.add(new Parameter(CAP_TINH, true));
            lstKBTinh =
                    (ArrayList<DMKBacVO>)dmKBDAO.getDMKBList(whereClause, params);
            // Danh sach trang thai
            whereClause = " a.rv_domain like ?";
            params = new Vector();
            params.add(new Parameter("%" + TRANG_THAI + "%", true));
            lstTrangThai =
                    (ArrayList<MaThamChieuVO>)tchieuDAO.getMaThamChieuList(whereClause,
                                                                           params);
        } catch (Exception e) {
            throw new Exception("Tra c&#7913;u &#273;i&#7879;n tra so&#225;t to&#224;n qu&#7889;c : " +
                                e.getMessage());
        } finally {
            request.setAttribute("lstKBHuyen",
                                 lstKBHuyen != null ? lstKBHuyen :
                                 new ArrayList<DMKBacVO>());
            request.setAttribute("lstNganHang", lstNganHang);
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstTrangThai", lstTrangThai);

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
        DTSoatDAO dtsDAO = null;
        String whereClause = STRING_EMPTY;
        Vector params = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "DTS.TRACUU_TQ")) {
                return mapping.findForward("errorQuyen");
            } else {
                conn = getConnection();
                dtsDAO = new DTSoatDAO(conn);
                TraCuuDTSToanQuocForm f = (TraCuuDTSToanQuocForm)form;
                params = new Vector();
                String actionBack = null;
                if (request.getParameter(AppConstants.REQUEST_ACTION) !=
                    null) {
                    actionBack =
                            request.getParameter(AppConstants.REQUEST_ACTION).toString();
                }
                if (actionBack != null &&
                    actionBack.equalsIgnoreCase("Back")) {
                    String ma_nh_back = request.getParameter("ma_nh_back")!=null?request.getParameter("ma_nh_back"):"";
                    String tinh_back = request.getParameter("tinh_back")!=null?request.getParameter("tinh_back"):"";
                    String huyen_back = request.getParameter("huyen_back")!=null?request.getParameter("huyen_back"):"";
                    String trang_thai_back =
                        request.getParameter("trang_thai_back")!=null?request.getParameter("trang_thai_back"):"";
                    String loai_lenh_back =
                        request.getParameter("loai_lenh_back")!=null?request.getParameter("loai_lenh_back"):"";
                    String tu_ltt_back = request.getParameter("tu_ltt_back")!=null?request.getParameter("tu_ltt_back"):"";
                    String den_ltt_back = request.getParameter("den_ltt_back")!=null?request.getParameter("den_ltt_back"):"";
                    String tu_ngay_back = request.getParameter("tu_ngay_back")!=null?request.getParameter("tu_ngay_back"):"";
                    String den_ngay_back =
                        request.getParameter("den_ngay_back")!=null?request.getParameter("den_ngay_back"):"";
                    String so_dts_back = request.getParameter("so_dts_back")!=null?request.getParameter("so_dts_back"):"";
                    String so_tien_temp_back =
                        request.getParameter("so_tien_temp_back")!=null?request.getParameter("so_tien_temp_back"):"";
                    if (request.getParameter("so_tien_temp_back") != null && !STRING_EMPTY.equals(request.getParameter("so_tien_temp_back"))) {
                        so_tien_temp_back =
                                request.getParameter("so_tien_temp_back").replace("\\.",
                                                                                  "");
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
                    if (huyen_back != null &&
                        !STRING_EMPTY.equals(huyen_back)) {
                        f.setHuyen(huyen_back);
                    } else {
                        f.setHuyen(null);
                    }
                    if (trang_thai_back != null &&
                        !STRING_EMPTY.equals(trang_thai_back)) {
                        f.setTrang_thai(trang_thai_back);
                    } else {
                        f.setTrang_thai(null);
                    }
                    if (loai_lenh_back != null &&
                        !STRING_EMPTY.equals(loai_lenh_back)) {
                        f.setLoai_lenh(loai_lenh_back);
                    } else {
                        f.setLoai_lenh(null);
                    }
                    if (tu_ltt_back != null &&
                        !STRING_EMPTY.equals(tu_ltt_back)) {
                        f.setTu_ltt(tu_ltt_back);
                    } else {
                        f.setTu_ltt(null);
                    }
                    if (den_ltt_back != null &&
                        !STRING_EMPTY.equals(den_ltt_back)) {
                        f.setDen_ltt(den_ltt_back);
                    } else {
                        f.setDen_ltt(null);
                    }
                    if (so_dts_back != null &&
                        !STRING_EMPTY.equals(so_dts_back)) {
                        f.setSo_dts(so_dts_back);
                    } else {
                        f.setSo_dts(null);
                    }
                    if (tu_ngay_back != null &&
                        !STRING_EMPTY.equals(tu_ngay_back)) {
                        f.setTu_ngay(tu_ngay_back);
                    } else {
                        f.setTu_ngay(null);
                    }
                    if (den_ngay_back != null &&
                        !STRING_EMPTY.equals(den_ngay_back)) {
                        f.setDen_ngay(den_ngay_back);
                    } else {
                        f.setDen_ngay(null);
                    }
                    if (so_tien_temp_back != null &&
                        !STRING_EMPTY.equals(so_tien_temp_back)) {
                        f.setSo_tien(new Long(so_tien_temp_back));
                    } else {
                        f.setSo_tien(null);
                    }
                }
                if (f.getMa_nh() != null &&
                    !STRING_EMPTY.equals(f.getMa_nh())) {
                    whereClause += " and (e.ma_nh = ? or f.ma_nh=?) ";
                    params.add(new Parameter(f.getMa_nh(), true));
                    params.add(new Parameter(f.getMa_nh(), true));
                }
                if (f.getTinh() != null && !STRING_EMPTY.equals(f.getTinh())) {
                    if (f.getHuyen() != null &&
                        !STRING_EMPTY.equals(f.getHuyen())) {
                        String strWhereNHKB =
                            " (select id from sp_dm_ngan_hang where ma_nh = (select ma_nh from sp_dm_manh_shkb where shkb=?)) ";
                        whereClause +=
                                " and (a.nhkb_nhan = " + strWhereNHKB + " or a.nhkb_chuyen= " +
                                strWhereNHKB + ")";
                        params.add(new Parameter(f.getHuyen(), true));
                        params.add(new Parameter(f.getHuyen(), true));
                    } else {
                        String strWhereNHKBTinh =
                            "(select id from sp_dm_ngan_hang where ma_nh in((select ma_nh from sp_dm_manh_shkb " +
                            "where shkb in(select ma from sp_dm_htkb where ma_cha=?))))";
                        whereClause +=
                                " and (a.nhkb_nhan in(" + strWhereNHKBTinh +
                                ")" + " or a.nhkb_chuyen in(" +
                                strWhereNHKBTinh + "))";
                        params.add(new Parameter(f.getTinh(), true));
                        params.add(new Parameter(f.getTinh(), true));
                    }
                }
                if ((f.getTu_ltt() != null &&
                     !STRING_EMPTY.equals(f.getTu_ltt())) &&
                    (f.getDen_ltt() != null &&
                     !STRING_EMPTY.equals(f.getDen_ltt()))) {
                    whereClause += " and a.id between ? and ?";
                    params.add(new Parameter(f.getTu_ltt(), true));
                    params.add(new Parameter(f.getDen_ltt(), true));
                } else if ((f.getDen_ltt() != null &&
                            !STRING_EMPTY.equals(f.getDen_ltt())) &&
                           (f.getTu_ltt() == null ||
                            STRING_EMPTY.equals(f.getTu_ltt()))) {
                    whereClause +=
                            " and a.ltt_id between (select min(id) from sp_ltt) and ? ";
                    params.add(new Parameter(f.getDen_ltt(), true));
                } else if ((f.getDen_ltt() == null ||
                            STRING_EMPTY.equals(f.getDen_ltt())) &&
                           (f.getTu_ltt() != null &&
                            !STRING_EMPTY.equals(f.getTu_ltt()))) {
                    whereClause +=
                            " and a.ltt_id between ? and (select max(id) from sp_ltt)";
                    params.add(new Parameter(f.getTu_ltt(), true));
                }
                if (f.getSo_tien() != null &&
                    !STRING_EMPTY.equals(f.getSo_tien()) &&
                    f.getSo_tien() != 0) {
                    whereClause += " and c.tong_sotien = ?";
                    params.add(new Parameter(f.getSo_tien(), true));
                }
                if (f.getTrang_thai() != null &&
                    !STRING_EMPTY.equals(f.getTrang_thai())) {
                    whereClause += " and a.trang_thai = ?";
                    params.add(new Parameter(f.getTrang_thai(), true));
                }

                if (f.getTu_ngay() != null &&
                    !STRING_EMPTY.equals(f.getTu_ngay())) {
                    whereClause += " and trunc(a.ngay_nhan) >= to_date(?,?)";
                    params.add(new Parameter(f.getTu_ngay(), true));
                    params.add(new Parameter(FORMAT_NGAY_24H, true));
                }
                if (f.getDen_ngay() != null &&
                    !STRING_EMPTY.equals(f.getDen_ngay())) {
                    whereClause += " and trunc(a.ngay_nhan) <= to_date(?,?)";
                    params.add(new Parameter(f.getDen_ngay(), true));
                    params.add(new Parameter(FORMAT_NGAY_24H, true));
                } else {
                    whereClause += " and trunc(a.ngay_nhan) <= SYSDATE";
                }
                if (f.getLoai_lenh() != null &&
                    !STRING_EMPTY.equals(f.getLoai_lenh())) {
                    String whereLoaiLenh = "";
                    if (f.getLoai_lenh().equals("01")) {
                        whereLoaiLenh = " (instr(a.id, '195') = 6) ";
                    } else if (f.getLoai_lenh().equals("02")) {
                        whereLoaiLenh = " (instr(a.id, '196') = 6) ";
                    } else if (f.getLoai_lenh().equals("03")) {
                        whereLoaiLenh = " (instr(a.id, '199') = 6) ";
                    }
                    whereClause += " and " + whereLoaiLenh;
                }
                if (f.getSo_dts() != null &&
                    !STRING_EMPTY.equals(f.getSo_dts())) {
                    whereClause += " and a.id = ?";
                    params.add(new Parameter(f.getSo_dts(), true));
                }
                String page = f.getPageNumber();
                if (page == null) {
                    page = "1";
                }
                // khai bao cac bien phan trang
                Integer currentPage = new Integer(page);
                Integer numberRowOnPage = new Integer(15);
                Integer totalCount[] = new Integer[1];
                ArrayList<DTSoatVO> lstLTT =
                    (ArrayList<DTSoatVO>)dtsDAO.getDTSTQByConditionSearch(whereClause,
                                                                          params,
                                                                          currentPage,
                                                                          numberRowOnPage,
                                                                          totalCount);

                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("lstLTT", lstLTT);
                request.setAttribute("PAGE_KEY", pagingBean);
                view(mapping, form, request, response);
            }
        } catch (Exception e) {
            throw new Exception("Tra c&#7913;u &#273;i&#7879;n tra so&#225;t to&#224;n qu&#7889;c : " +
                                e.getMessage());
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
