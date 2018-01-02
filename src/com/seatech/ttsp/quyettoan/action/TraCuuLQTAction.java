package com.seatech.ttsp.quyettoan.action;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.quyettoan.QuyetToanDAO;
import com.seatech.ttsp.quyettoan.QuyetToanVO;
import com.seatech.ttsp.quyettoan.TThaiVO;
import com.seatech.ttsp.quyettoan.form.QuyetToanToanQuocForm;
import com.seatech.ttsp.ttthanhtoan.DMucNHVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;
import com.seatech.ttsp.dmkb.DMKBacDAO;

import java.io.PrintWriter;

import java.lang.reflect.Type;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TraCuuLQTAction extends AppAction {
    public TraCuuLQTAction() {
        super();
    }
    private static String STRING_EMPTY = "";
    private static String maT4 = "0002";
    private static String maSGD = "0003";
    private static String maKBTW = "0001";

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strWhereClauseKB = null;
        String strCapTinh = "5";
        Vector vParamsKB = null;
        Vector vParamsDMHO = null;
        Vector vParamsTT = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "QTOAN.TRACUU")) {
                return mapping.findForward("errorQuyen");
            } else {
                conn = getConnection();
                // Phan biet la kho bac don vi hay T4
                HttpSession session = request.getSession();
                String ma_kb_so_tai =
                    (String)session.getAttribute(AppConstants.APP_KB_CODE_SESSION);
                /** neu la trung tam thanh toan thi lay ra toan bo kb tinh de lua chon
                * Lay ra danh sach Kho bac tinh
                **/
                DMKBacDAO kbDAO = new DMKBacDAO(conn);
                DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
                ArrayList<DMKBacVO> lstKBTinh = new ArrayList<DMKBacVO>();
                ArrayList<DMKBacVO> lstKBHuyen = new ArrayList<DMKBacVO>();
                //20171207 thuongdt bo maSGD =>> nhu don vi binh thuong khong xem duoc toan quoc
                if (ma_kb_so_tai.equals(maT4)  ||
                    ma_kb_so_tai.equals(maKBTW)) {
                    request.setAttribute("MAT4", ma_kb_so_tai);
                    vParamsKB = new Vector();
                    strWhereClauseKB = "a.cap=?  or a.ma='" + maSGD + "' ";
                    vParamsKB.add(new Parameter(strCapTinh, true));
                    lstKBTinh =
                            (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                                     vParamsKB);
                } else {
                vParamsKB = new Vector();
                strWhereClauseKB = " AND a.ma=? ";
                vParamsKB.add(new Parameter(ma_kb_so_tai, true));
                DMKBacVO dmkbVO = kbDAO.getDMKB(strWhereClauseKB, vParamsKB);
                // Neu user thuoc cap tinh. Set kb tinh va kb huyen
                if (dmkbVO.getCap().equals(strCapTinh)) {
                    lstKBTinh.add(dmkbVO);
                    vParamsKB = new Vector();
                    strWhereClauseKB = " a.ma_cha=? ";
                    vParamsKB.add(new Parameter(dmkbVO.getMa(), true));
                    lstKBHuyen =
                            (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                                     vParamsKB);
                } else {
                    vParamsKB = new Vector();
                    strWhereClauseKB = " AND a.ma=? ";
                    if (dmkbVO.getCap().equals("2")) {
                        lstKBTinh.add(dmkbVO);
                        vParamsKB = new Vector();
                        strWhereClauseKB = " a.ma_cha=? ";
                        vParamsKB.add(new Parameter(dmkbVO.getMa_cha(), true));
                        lstKBHuyen =
                                (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                                         vParamsKB);
                    } else if (dmkbVO.getCap().equals("1")) {
                        strWhereClauseKB = " a.cap=5 ";
                        lstKBTinh =
                                (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                                         null);
                    } else {
                        lstKBHuyen.add(dmkbVO);
                        vParamsKB.add(new Parameter(dmkbVO.getMa_cha(), true));
                        lstKBTinh.add(kbDAO.getDMKB(strWhereClauseKB,
                                                    vParamsKB));
                        }
                    }
                }
                // Lay ra danh sach dm ngan hang ho
                TTThanhToanDAO hoDAO = new TTThanhToanDAO(conn);
                vParamsDMHO = new Vector();
                List<DMucNHVO> lstNHHO =
                    (List<DMucNHVO>)hoDAO.getDMucNH(null, vParamsDMHO);
              // danh sach kb tinh
              //  String whereClause = " (a.cap=? or a.ma='" + "0003" + "') ";
             //  Vector params = new Vector();
            //   params.add(new Parameter("5", true));
             //   lstKBTinh =
                 //     (ArrayList<DMKBacVO>) new DMKBacDAO(conn).getDMKBList(whereClause, params);

                // lay danh sach trang thai

                QuyetToanDAO qtDAO = new QuyetToanDAO(conn);
                String strWhereClauseTT = "a.rv_domain=? ";
                vParamsTT = new Vector();
                String strDomain = "SP_QT.TRANG_THAI";
                vParamsTT.add(new Parameter(strDomain, true));
                ArrayList<TThaiVO> lstTThai =
                    (ArrayList<TThaiVO>)qtDAO.getDSTrang_Thai(strWhereClauseTT,
                                                              vParamsTT);
                // Neu danh sach bi null
                // tranh exception khi add danh sach vao combobox
                if (lstTThai == null) {
                    lstTThai = new ArrayList<TThaiVO>();
                }
                if (lstNHHO == null) {
                    lstNHHO = new ArrayList<DMucNHVO>();
                }
                if (lstKBTinh == null) {
                    lstKBTinh = new ArrayList<DMKBacVO>();
                }
                if (lstKBHuyen == null) {
                    lstKBHuyen = new ArrayList<DMKBacVO>();
                }
                List tienTe =
                    tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ", null);

                request.setAttribute("lstTThai", lstTThai);
                request.setAttribute("lstNHHO", lstNHHO);
                request.setAttribute("lstKBTinh", lstKBTinh);
                request.setAttribute("lstKBHuyen", lstKBHuyen);
                request.setAttribute("tienTe", tienTe);
            }
        } catch (Exception e) {
            ArrayList<TThaiVO> lstTThai = new ArrayList<TThaiVO>();
            List<DMucNHVO> lstNHHO = new ArrayList<DMucNHVO>();
            ArrayList<DMKBacVO> lstKBTinh = new ArrayList<DMKBacVO>();
            ArrayList<DMKBacVO> lstKBHuyen = new ArrayList<DMKBacVO>();
            request.setAttribute("lstTThai", lstTThai);
            request.setAttribute("lstNHHO", lstNHHO);
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstKBHuyen", lstKBHuyen);
            throw new Exception("Tra c&#7913;u l&#7879;nh quy&#7871;t to&#225;n : " +
                                e.getMessage());
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
        QuyetToanDAO qtDAO = null;
        String whereClause = "";
        Vector params = null;
        String strWhereClauseKB = null;
        String strCapTinh = "5";
        Vector vParamsKB = null;
        Vector vParamsDMHO = null;
        Vector vParamsTT = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "QTOAN.TRACUU")) {
                return mapping.findForward("errorQuyen");
            } else {
                conn = getConnection();
                qtDAO = new QuyetToanDAO(conn);
                DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
                // Phan biet la kho bac don vi hay T4
                HttpSession session = request.getSession();
                String ma_kb_so_tai =
                    (String)session.getAttribute(AppConstants.APP_KB_CODE_SESSION);
                // Lay ra danh sach Kho bac huyen
                //                if (!ma_kb_so_tai.equals(maSGD) &&
                //                    !ma_kb_so_tai.equals(maT4) &&
                //                    !ma_kb_so_tai.equals(maKBTW)) {
                //                    //                    whereClause = " a.qtoan_dvi='Y'";
                //                } else
                if (ma_kb_so_tai.equals(maT4) ||
                    ma_kb_so_tai.equals(maKBTW)) {
                    //                    whereClause = " a.qtoan_dvi='N'";
                    request.setAttribute("T4", maT4);
                }
                String actionBack = null;
                QuyetToanToanQuocForm f = (QuyetToanToanQuocForm)form;
                if (request.getParameter(AppConstants.REQUEST_ACTION) !=
                    null) {
                    actionBack =
                            request.getParameter(AppConstants.REQUEST_ACTION).toString();
                }
                if (actionBack != null &&
                    actionBack.equalsIgnoreCase("Back")) {
                    String hsc_nh_back = request.getParameter("hsc_nh_back");
                    String kb_tinh_back = request.getParameter("kb_tinh_back");
                    String kb_huyen_back =
                        request.getParameter("kb_huyen_back");
                    String tu_ngay_back = request.getParameter("tu_ngay_back");
                    String den_ngay_back =
                        request.getParameter("den_ngay_back");
                    String ngay_tt_back = request.getParameter("ngay_tt_back");
                    String loai_qt_back = request.getParameter("loai_qt_back");
                    String pt_qt_back = request.getParameter("pt_qt_back");
                    String tcg_loai_tien_back =
                        request.getParameter("tcg_loai_tien_back");
                    String so_tien_back = request.getParameter("soTien");
                    if (hsc_nh_back != null &&
                        !STRING_EMPTY.equals(hsc_nh_back)) {
                        f.setHsc_nh(hsc_nh_back);
                    } else {
                        f.setHsc_nh(null);
                    }
                    if (kb_tinh_back != null &&
                        !STRING_EMPTY.equals(kb_tinh_back)) {
                        f.setKb_tinh(kb_tinh_back);
                    } else {
                        f.setKb_tinh(null);
                    }
                    if (kb_huyen_back != null &&
                        !STRING_EMPTY.equals(kb_huyen_back)) {
                        f.setKb_huyen(kb_huyen_back);
                    } else {
                        f.setKb_huyen(null);
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
                    if (ngay_tt_back != null &&
                        !STRING_EMPTY.equals(ngay_tt_back)) {
                        f.setNgay_tt(ngay_tt_back);
                    } else {
                        f.setNgay_tt(null);
                    }
                    if (loai_qt_back != null &&
                        !STRING_EMPTY.equals(loai_qt_back)) {
                        f.setLoai_qt(loai_qt_back);
                    } else {
                        f.setLoai_qt(null);
                    }
                    if (pt_qt_back != null &&
                        !STRING_EMPTY.equals(pt_qt_back)) {
                        f.setPt_qt(pt_qt_back);
                    } else {
                        f.setPt_qt(null);
                    }
                    if (tcg_loai_tien_back != null &&
                        !STRING_EMPTY.equals(tcg_loai_tien_back)) {
                        f.setTcg_loai_tien(tcg_loai_tien_back);
                    } else {
                        f.setTcg_loai_tien(null);
                    }
                    if (so_tien_back != null &&
                        !STRING_EMPTY.equals(so_tien_back)) {
                        so_tien_back = StringUtil.formatMoneyVNDToDouble(so_tien_back);
                        f.setSoTien(so_tien_back);
                    } else {
                        f.setSoTien(null);
                    }
                }
                params = new Vector();
                if (f.getHsc_nh() != null &&
                    !STRING_EMPTY.equals(f.getHsc_nh())) {
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        whereClause += " and substr(a.nhkb_chuyen,3,3) = ?";
                    } else {
                        whereClause = " substr(a.nhkb_chuyen,3,3) = ?";
                    }
                    params.add(new Parameter(f.getHsc_nh(), true));
                }
                if (f.getKb_tinh() != null &&
                    !STRING_EMPTY.equals(f.getKb_tinh())) {
                    if (f.getKb_huyen() != null &&
                        !STRING_EMPTY.equals(f.getKb_huyen())) {
                        if (whereClause != null &&
                            !STRING_EMPTY.equals(whereClause)) {
                            whereClause +=
                                    " and a.ma_kb = (select ma_nh From sp_dm_manh_shkb where shkb=?)  ";
                        } else {
                            whereClause =
                                    " a.ma_kb = (select ma_nh From sp_dm_manh_shkb where shkb=?) ";
                        }
                        params.add(new Parameter(f.getKb_huyen(), true));
                    } else {
                        if (whereClause != null &&
                            !STRING_EMPTY.equals(whereClause)) {
                            whereClause +=
                                    " and a.ma_kb in ( select ma_nh from sp_dm_manh_shkb where SHKB in(select ma from sp_dm_htkb where (ma_cha = ? or ma = ?))) ";
                        } else {
                            whereClause =
                                    " a.ma_kb in ( select ma_nh from sp_dm_manh_shkb where SHKB in(select ma from sp_dm_htkb where (ma_cha = ? or ma = ?))) ";
                        }
                        params.add(new Parameter(f.getKb_tinh(), true));
                        params.add(new Parameter(f.getKb_tinh(), true));
                    }
                }
                if (f.getLoai_qt() != null &&
                    !STRING_EMPTY.equals(f.getLoai_qt())) {
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        // phi TT - 01 : lai_phi=='02' ,loai_qtoan=='D'
                        // lai sdtk - 02 : lai_phi=='02' ,loai_qtoan=='C'
                        // bao co chenh lech ty gia - 03 : lai_phi =='04', loai_qtoan =='C'
                        // bao no chenh lech ty gia - 04 : lai_phi =='04', loai_qtoan =='D'
                        if (f.getLoai_qt().equals("01")) {
                            whereClause +=
                                    " and a.loai_qtoan = 'D' and a.lai_phi='02' ";
                        } else if (f.getLoai_qt().equals("02")) {
                            whereClause +=
                                    " and a.loai_qtoan = 'C' and a.lai_phi='02' ";
                        } else if (f.getLoai_qt().equals("03")) {
                            whereClause +=
                                    " and a.loai_qtoan = 'C' and a.lai_phi='04' ";
                        } else if (f.getLoai_qt().equals("04")) {
                            whereClause +=
                                    " and a.loai_qtoan = 'D' and a.lai_phi='04' ";
                        } else if (f.getLoai_qt().equals("05")) {
                            whereClause += " and a.lai_phi='05' ";
                        } else if (f.getLoai_qt().equals("06")) {
                            whereClause += " and a.lai_phi='06' ";
                        }
                        if (f.getLoai_qt().equals("07")) {
                            whereClause += " and sp.loai_qtoan ='04' ";
                        } else if (f.getLoai_qt().equals("08")) {
                            whereClause += " and sp.loai_qtoan ='05' ";
                        } else if (f.getLoai_qt().equals("09")) {
                            whereClause += " and sp.loai_qtoan ='07' ";
                        } else {
                            whereClause +=
                                    " and a.loai_qtoan = ? and a.lai_phi in ('01','03') ";
                            params.add(new Parameter(f.getLoai_qt(), true));
                        }
                    } else {
                        if (f.getLoai_qt().equals("01")) {
                            whereClause +=
                                    " a.loai_qtoan = 'D' and a.lai_phi='02' ";
                        } else if (f.getLoai_qt().equals("02")) {
                            whereClause +=
                                    " a.loai_qtoan = 'C' and a.lai_phi='02' ";
                        } else if (f.getLoai_qt().equals("03")) {
                            whereClause +=
                                    " a.loai_qtoan = 'C' and a.lai_phi='04' ";
                        } else if (f.getLoai_qt().equals("04")) {
                            whereClause +=
                                    " a.loai_qtoan = 'D' and a.lai_phi='04' ";
                        } else if (f.getLoai_qt().equals("05")) {
                            whereClause += " a.lai_phi='05' ";
                        } else if (f.getLoai_qt().equals("06")) {
                            whereClause += " a.lai_phi='06' ";
                        } else if (f.getLoai_qt().equals("07")) {
                            whereClause += " sp.loai_qtoan ='04' ";
                        } else if (f.getLoai_qt().equals("08")) {
                            whereClause += " sp.loai_qtoan ='05' ";
                        } else if (f.getLoai_qt().equals("09")) {
                            whereClause += " sp.loai_qtoan ='07' ";
                        } else {
                            whereClause +=
                                    " a.loai_qtoan = ? and a.lai_phi in ('01','03') ";
                            params.add(new Parameter(f.getLoai_qt(), true));
                        }
                    }
                }
                if ((f.getTu_ngay() != null &&
                     !STRING_EMPTY.equals(f.getTu_ngay())) &&
                    ((f.getDen_ngay() != null &&
                      !STRING_EMPTY.equals(f.getDen_ngay())))) {
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        whereClause +=
                                " and trunc(a.ngay_qtoan) >= to_date(?,'DD/MM/YYYY') and trunc(a.ngay_qtoan) <= to_date(?,'DD/MM/YYYY')";
                    } else {
                        whereClause =
                                " trunc(a.ngay_qtoan) >= to_date(?,'DD/MM/YYYY') and trunc(a.ngay_qtoan) <= to_date(?,'DD/MM/YYYY')";
                    }
                    params.add(new Parameter(f.getTu_ngay(), true));
                    params.add(new Parameter(f.getDen_ngay(), true));
                }

                if (f.getNgay_tt() != null &&
                    !STRING_EMPTY.equals(f.getNgay_tt())) {
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        whereClause +=
                                " and trunc(a.ngay_htoan) = to_date(?,'DD/MM/YYYY')";
                    } else {
                        whereClause =
                                " trunc(a.ngay_htoan) = to_date(?,'DD/MM/YYYY')";
                    }
                    params.add(new Parameter(f.getNgay_tt(), true));
                }
                if (f.getPt_qt() != null &&
                    !STRING_EMPTY.equals(f.getPt_qt())) {
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        whereClause += " and a.lai_phi = ? ";
                    } else {
                        whereClause = " a.lai_phi = ? ";
                    }
                    params.add(new Parameter(f.getPt_qt(), true));
                }
                if (f.getTcg_loai_tien() != null &&
                    !STRING_EMPTY.equals(f.getTcg_loai_tien())) {
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        whereClause += " and a.ma_nt = ? ";
                    } else {
                        whereClause = " a.ma_nt = ? ";
                    }
                    params.add(new Parameter(f.getTcg_loai_tien(), true));
                }
                if (f.getSoTien() != null &&
                    !STRING_EMPTY.equals(f.getSoTien())) {
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        whereClause += " and a.so_tien = ? ";
                    } else {
                        whereClause = " a.so_tien = ? ";
                    }
                    params.add(new Parameter(StringUtil.formatMoneyVNDToDouble(f.getSoTien()), true));
                }
                String page = f.getPageNumber();
                if (page == null) {
                    page = "1";
                }
                // khai bao cac bien phan trang
                Integer currentPage = new Integer(page);
                Integer numberRowOnPage = new Integer(15);
                Integer totalCount[] = new Integer[1];
                ArrayList<QuyetToanVO> lstQuyetToan =
                    (ArrayList<QuyetToanVO>)qtDAO.getTCuuLQTListWithPading(whereClause,
                                                                           params,
                                                                           currentPage,
                                                                           numberRowOnPage,
                                                                           totalCount);
                // dem tong so b?n ghi
                List<QuyetToanVO> colSum = new ArrayList<QuyetToanVO>();
                colSum =
                        (List<QuyetToanVO>)qtDAO.getTtien_Tmon(whereClause, params);
                colSum.get(0).setMa_nt(f.getTcg_loai_tien());
                request.setAttribute("colSum", colSum);

                //                f.setTong_sl(String.valueOf(tong_sl));
                //

                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("lstQuyetToan", lstQuyetToan);
                request.setAttribute("PAGE_KEY", pagingBean);
                // executeAction(mapping, form, request, response);

                /** neu la trung tam thanh toan thi lay ra toan bo kb tinh de lua chon
                  * Lay ra danh sach Kho bac tinh
                  **/
                DMKBacDAO kbDAO = new DMKBacDAO(conn);
                ArrayList<DMKBacVO> lstKBTinh = new ArrayList<DMKBacVO>();
                ArrayList<DMKBacVO> lstKBHuyen = new ArrayList<DMKBacVO>();
              //20171207 thuongdt bo maSGD =>> nhu don vi binh thuong khong sem duoc toan quoc
                if (ma_kb_so_tai.equals(maT4) ||
                    ma_kb_so_tai.equals(maKBTW)) {
                    request.setAttribute("MAT4", ma_kb_so_tai);
                    vParamsKB = new Vector();
                    strWhereClauseKB = "a.cap=?  or a.ma='" + maSGD + "' ";
                    vParamsKB.add(new Parameter(strCapTinh, true));
                    lstKBTinh =
                            (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                                     vParamsKB);
                    vParamsKB = new Vector();
                    strWhereClauseKB = " a.ma_cha=? ";
                    vParamsKB.add(new Parameter(f.getKb_tinh(), true));
                    lstKBHuyen =
                            (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                                     vParamsKB);
                } else {
                    vParamsKB = new Vector();
                    strWhereClauseKB = " AND a.ma=? ";
                    vParamsKB.add(new Parameter(ma_kb_so_tai, true));
                    DMKBacVO dmkbVO =
                        kbDAO.getDMKB(strWhereClauseKB, vParamsKB);
                    // Neu user thuoc cap tinh. Set kb tinh va kb huyen
                    if (dmkbVO.getCap().equals(strCapTinh)) {
                        lstKBTinh.add(dmkbVO);
                        vParamsKB = new Vector();
                        strWhereClauseKB = " a.ma_cha=? ";
                        vParamsKB.add(new Parameter(dmkbVO.getMa(), true));
                        lstKBHuyen =
                                (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                                         vParamsKB);
                    } else {
                        vParamsKB = new Vector();
                        strWhereClauseKB = " AND a.ma=? ";
                        if (dmkbVO.getCap().equals("2")) {
                            lstKBTinh.add(dmkbVO);
                            vParamsKB = new Vector();
                            strWhereClauseKB = " a.ma_cha=? ";
                            vParamsKB.add(new Parameter(dmkbVO.getMa_cha(),
                                                        true));
                            lstKBHuyen =
                                    (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                                             vParamsKB);
                        } else if (dmkbVO.getCap().equals("1")) {
                            strWhereClauseKB = " AND a.cap=3 ";
                            lstKBTinh =
                                    (ArrayList<DMKBacVO>)kbDAO.getDMNHKBList(strWhereClauseKB,
                                                                             vParamsKB);
                        } else {
                            lstKBHuyen.add(dmkbVO);
                            vParamsKB.add(new Parameter(dmkbVO.getMa_cha(),
                                                        true));
                            lstKBTinh.add(kbDAO.getDMKB(strWhereClauseKB,
                                                        vParamsKB));
                        }
                    }
                }
                // Lay ra danh sach dm ngan hang ho
                TTThanhToanDAO hoDAO = new TTThanhToanDAO(conn);
                vParamsDMHO = new Vector();
                List<DMucNHVO> lstNHHO =
                    (List<DMucNHVO>)hoDAO.getDMucNH(null, vParamsDMHO);

                // lay danh sach trang thai
                List tienTe =
                    tienTeDAO.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ", null);

                String strWhereClauseTT = "a.rv_domain=? ";
                vParamsTT = new Vector();
                String strDomain = "SP_QT.TRANG_THAI";
                vParamsTT.add(new Parameter(strDomain, true));
                ArrayList<TThaiVO> lstTThai =
                    (ArrayList<TThaiVO>)qtDAO.getDSTrang_Thai(strWhereClauseTT,
                                                              vParamsTT);
                // Neu danh sach bi null
                // tranh exception khi add danh sach vao combobox
                if (lstTThai == null) {
                    lstTThai = new ArrayList<TThaiVO>();
                }
                if (lstNHHO == null) {
                    lstNHHO = new ArrayList<DMucNHVO>();
                }
                if (lstKBTinh == null) {
                    lstKBTinh = new ArrayList<DMKBacVO>();
                }

                request.setAttribute("lstTThai", lstTThai);
                request.setAttribute("lstNHHO", lstNHHO);
                request.setAttribute("lstKBTinh", lstKBTinh);
                request.setAttribute("lstKBHuyen", lstKBHuyen);
                request.setAttribute("tienTe", tienTe);
            }
            String inKB = request.getParameter("inKB");
            request.setAttribute("kb_huyen", inKB);
        } catch (Exception e) {
            ArrayList<TThaiVO> lstTThai = new ArrayList<TThaiVO>();
            List<DMucNHVO> lstNHHO = new ArrayList<DMucNHVO>();
            ArrayList<DMKBacVO> lstKBTinh = new ArrayList<DMKBacVO>();
            ArrayList<DMKBacVO> lstKBHuyen = new ArrayList<DMKBacVO>();
            request.setAttribute("lstTThai", lstTThai);
            request.setAttribute("lstNHHO", lstNHHO);
            request.setAttribute("lstKBTinh", lstKBTinh);
            request.setAttribute("lstKBHuyen", lstKBHuyen);
            throw new Exception("Tra c&#7913;u l&#7879;nh quy&#7871;t to&#225;n : " +
                                e.getMessage());
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
        Connection conn = null;
        JsonObject jsonObj = new JsonObject();
        String strJson = "";
        Gson gson = null;
        try {
            conn = getConnection();
            String idkbTinh =
                request.getParameter("idkbTinh") != null ? request.getParameter("idkbTinh") :
                "";
            ArrayList<DMKBacVO> lstKBHuyen = new ArrayList<DMKBacVO>();
            DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
            String whereClause = " a.ma_cha=? or a.ma=?";
            Vector params = new Vector();
            params.add(new Parameter(idkbTinh, true));
            params.add(new Parameter(idkbTinh, true));
            lstKBHuyen =
                    (ArrayList<DMKBacVO>)dmkbDAO.getDMNHKBList(whereClause,
                                                               params);
            Type lstKBHuyenType = new TypeToken<ArrayList<DMKBacVO>>() {
            }.getType();
            gson = new GsonBuilder().setVersion(1.0).create();
            strJson = gson.toJson(lstKBHuyen, lstKBHuyenType);
            jsonObj.addProperty("lstKBHuyen", strJson);
            JsonArray jsonArr = new JsonArray();
            JsonElement jsonEle = jsonObj.get("lstKBHuyen");
            jsonArr.add(jsonEle);
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(jsonArr.getAsJsonArray().toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        } finally {
            close(conn);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
