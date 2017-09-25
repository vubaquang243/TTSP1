package com.seatech.ttsp.quyettoan.action;


import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.dmtiente.DMTienTeVO;
import com.seatech.ttsp.quyettoan.QuyetToanDAO;
import com.seatech.ttsp.quyettoan.QuyetToanVO;
import com.seatech.ttsp.quyettoan.TThaiVO;
import com.seatech.ttsp.quyettoan.form.QuyetToanToanQuocForm;
import com.seatech.ttsp.ttthanhtoan.DMucNHVO;
import com.seatech.ttsp.ttthanhtoan.TTThanhToanDAO;

import java.io.PrintWriter;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class QuyetToanToanQuocAction extends AppAction {
    public QuyetToanToanQuocAction() {
        super();
    }
    private static final String STRING_EMPTY = "";

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
            if (!checkPermissionOnFunction(request, "QTOAN.TQUOC.TAOBKE")) {
                return mapping.findForward("errorQuyen");
            } else {
                conn = getConnection();
                // Lay ra danh sach Kho bac tinh
                DMKBacDAO kbDAO = new DMKBacDAO(conn);
                vParamsKB = new Vector();
                strWhereClauseKB = "a.cap=? ";
                vParamsKB.add(new Parameter(strCapTinh, true));
                ArrayList<DMKBacVO> lstKBTinh =
                    (ArrayList<DMKBacVO>)kbDAO.getDMKBList(strWhereClauseKB,
                                                           vParamsKB);
                // Lay ra danh sach dm ngan hang ho
                TTThanhToanDAO hoDAO = new TTThanhToanDAO(conn);
                vParamsDMHO = new Vector();
                List<DMucNHVO> lstNHHO =
                    (List<DMucNHVO>)hoDAO.getDMucNH(null,vParamsDMHO);

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
                //Lay danh sach ma tien te
              //Lay danh sach ma tien te
              DMTienTeDAO dmTien = new DMTienTeDAO(conn);
              ArrayList<DMTienTeVO> lstLoaiTien = (ArrayList<DMTienTeVO>)dmTien.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);
                
                request.setAttribute("lstTThai", lstTThai);
                request.setAttribute("lstNHHO", lstNHHO);
                request.setAttribute("lstKBTinh", lstKBTinh);
                request.setAttribute("lstLoaiTien", lstLoaiTien);
            }
        } catch (Exception e) {
            throw new Exception("T&#7841;o b&#7843;ng k&#234; : " +
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
        String whereClause = null;
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
            if (!checkPermissionOnFunction(request, "QTOAN.TQUOC.TAOBKE")) {
                return mapping.findForward("errorQuyen");
            } else {
                conn = getConnection();
                qtDAO = new QuyetToanDAO(conn);
                // Lay ra danh sach Kho bac tinh
//******************************************************************************
//ManhNV-20170909-sua dap ung thay doi don vi quan ly TK TT tong hop-BEGIN
//                whereClause =
//                        " a.so_bk is null AND a.lai_phi in ('01','03') and a.qtoan_dvi='N' and a.ma_kb <> '" +
//                        AppConstants.KBNN_SGD_BANK_CODE + "' ";
                whereClause =
                      " a.so_bk is null AND a.lai_phi in ('01','03') and a.qtoan_dvi='N'";
//ManhNV-20170909-sua dap ung thay doi don vi quan ly TK TT tong hop-END
//******************************************************************************
                QuyetToanToanQuocForm f = (QuyetToanToanQuocForm)form;
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
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        whereClause +=
                                " and a.ma_kb in (select a.ma_nh from sp_dm_manh_shkb a,sp_dm_htkb b where a.shkb = b.ma and b.ma_cha = ?)";
                    } else {
                        whereClause =
                                " a.ma_kb in (select a.ma_nh from sp_dm_manh_shkb a,sp_dm_htkb b where a.shkb = b.ma and b.ma_cha = ?)";
                    }
                    params.add(new Parameter(f.getKb_tinh(), true));
                }
                if (f.getLoai_qt() != null &&
                    !STRING_EMPTY.equals(f.getLoai_qt())) {
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        whereClause += " and a.loai_qtoan = ?";
                    } else {
                        whereClause = " a.loai_qtoan = ?";
                    }
                    params.add(new Parameter(f.getLoai_qt(), true));
                }
                if (f.getNgay_qt() != null &&
                    !STRING_EMPTY.equals(f.getNgay_qt())) {
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        whereClause +=
                                " and trunc(a.ngay_qtoan) = to_date(?,'DD/MM/YYYY')";
                    } else {
                        whereClause =
                                " trunc(a.ngay_qtoan) = to_date(?,'DD/MM/YYYY')";
                    }
                    params.add(new Parameter(f.getNgay_qt(), true));
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
                if (f.getLoai_tk() != null &&
                    !STRING_EMPTY.equals(f.getLoai_tk())) {
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        whereClause += " and a.loai_tk = ?";
                    } else {
                        whereClause = " a.loai_tk = ?";
                    }
                    params.add(new Parameter(f.getLoai_tk(), true));
                }
                if (f.getTcg_loai_tien() != null && !STRING_EMPTY.equals(f.getTcg_loai_tien())) {
                    if (whereClause != null &&
                        !STRING_EMPTY.equals(whereClause)) {
                        whereClause += " and a.ma_nt = ?";
                    } else {
                        whereClause = " a.ma_nt = ?";
                    }
                    params.add(new Parameter(f.getTcg_loai_tien(), true));
                }
                String page = f.getPageNumber();
                if (page == null) {
                    page = "1";
                }
                // khai bao cac bien phan trang
                Integer currentPage = new Integer(page);
                Integer numberRowOnPage = new Integer(15);
                Integer totalCount[] = new Integer[1];
                ArrayList<QuyetToanVO> lstQuyetToan = (ArrayList<QuyetToanVO>)qtDAO.getLQTListWithPading(whereClause, params, currentPage, numberRowOnPage, totalCount);
                BigDecimal tongTienCacQuyetToan = qtDAO.getTongTien(whereClause, params);
                // Tinh tong so tien
                BigDecimal tong_sotien = new BigDecimal(0);
                if(f.getTcg_loai_tien() != null && !"".equals(f.getTcg_loai_tien())){
                    for (QuyetToanVO voTongSoTien : lstQuyetToan) {
                        tong_sotien = tong_sotien.add(voTongSoTien.getSo_tien());
                    }
                    f.setTong_st(StringUtil.formatCurrencyByCode(tong_sotien.toString(), f.getTcg_loai_tien()));
                }else{
                    f.setTong_st("");
                }                
                //

                PagingBean pagingBean = new PagingBean();
                pagingBean.setCurrentPage(currentPage);
                pagingBean.setNumberOfRow(totalCount[0].intValue());
                pagingBean.setRowOnPage(numberRowOnPage);
                request.setAttribute("lstQuyetToan", lstQuyetToan);
                request.setAttribute("PAGE_KEY", pagingBean);
                request.setAttribute("tongTienCacQuyetToan", tongTienCacQuyetToan);
                // executeAction(mapping, form, request, response);
                // Lay ra danh sach Kho bac tinh
                DMKBacDAO kbDAO = new DMKBacDAO(conn);
                vParamsKB = new Vector();
                strWhereClauseKB = "a.cap=? ";
                vParamsKB.add(new Parameter(strCapTinh, true));
                ArrayList<DMKBacVO> lstKBTinh =
                    (ArrayList<DMKBacVO>)kbDAO.getDMKBList(strWhereClauseKB,
                                                           vParamsKB);
                // Lay ra danh sach dm ngan hang ho
                TTThanhToanDAO hoDAO = new TTThanhToanDAO(conn);
                vParamsDMHO = new Vector();
                List<DMucNHVO> lstNHHO =
                    (List<DMucNHVO>)hoDAO.getDMucNH(null,vParamsDMHO);

                // lay danh sach trang thai

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
                //Lay danh sach ma tien te
                DMTienTeDAO dmTien = new DMTienTeDAO(conn);
                ArrayList<DMTienTeVO> lstLoaiTien =
                  (ArrayList<DMTienTeVO>)dmTien.simpleMaNgoaiTe(" AND a.MA_NT <> 'VND' ",null);
                
                request.setAttribute("lstTThai", lstTThai);
                request.setAttribute("lstNHHO", lstNHHO);
                request.setAttribute("lstKBTinh", lstKBTinh);
                request.setAttribute("lstLoaiTien", lstLoaiTien);
              
            }
        } catch (Exception e) {
            throw new Exception("Tao Bang ke : " + e.getMessage());
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
        //        QuyetToanDAO qtDAO = null;
        //        QuyetToanVO qtVO = null;
        //        String strMaKB = "";
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "QTOAN.TQUOC.TAOBKE")) {
                return mapping.findForward("errorQuyen");
            } else {
                //                conn = getConnection();
                //                QuyetToanToanQuocForm f = (QuyetToanToanQuocForm)form;
                //                HttpSession session = request.getSession();
                //                Long lUserID =
                //                    new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
                //                qtDAO = new QuyetToanDAO(conn);
                //                qtVO = new QuyetToanVO();
                //                String[] arrCheck = request.getParameterValues("chklist");
                //                String strValues = request.getParameter("value");
                //
                //                if ((arrCheck != null && arrCheck.length > 0)) {
                //                    // thuc hien update lenh quyet toan trong mang arrCheck
                //                    TTSPUtils ttspUtils = new TTSPUtils(conn);
                //                    strMaKB =
                //                            session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
                //                    String strSoBK = ttspUtils.getSoBKeQtoan(strMaKB);
                //                    // insert bang sobk
                //                    BKE_QuyetToanVO bke_qtVO = new BKE_QuyetToanVO();
                //                    bke_qtVO.setId(strSoBK);
                //                    bke_qtVO.setTrang_thai(AppConstants.TRANG_THAI_BK_CHODUYET);
                //                    bke_qtVO.setNgay_tao(StringUtil.getCurrentDate());
                //                    bke_qtVO.setNguoi_tao(lUserID.toString());
                //                    bke_qtVO.setSo_tien(new Long(f.getTong_st()));
                //                    bke_qtVO.setNgay_htoan(f.getNgay_tt());
                //                    bke_qtVO.setTcg_kb_tinh(f.getKb_tinh());
                //                    bke_qtVO.setTcg_ngan_hang(f.getHsc_nh());
                //                    bke_qtVO.setTcg_loai_qtoan(f.getLoai_qt());
                //                    bke_qtVO.setTcg_ngay_qtoan(f.getNgay_qt());
                //                    bke_qtVO.setTcg_ngay_ttoan(f.getNgay_tt());
                //                    if (qtDAO.insert(bke_qtVO) < 0) {
                //                        return mapping.findForward(AppConstants.FAILURE);
                //                    }
                //                    if (strValues != null && !STRING_EMPTY.equals(strValues)) {
                //                        String[] arrValues = strValues.trim().split(",");
                //                        for (int i = 0; i < arrValues.length; i++) {
                //                            String[] strQT = arrValues[i].split("\\|");
                //                            for (int j = 0; j < arrCheck.length; j++) {
                //                                qtVO.setId(arrCheck[j].trim());
                //                                qtVO.setSo_bk(strSoBK);
                //                                if (strQT[1].trim().equalsIgnoreCase(arrCheck[j].trim())) {
                //                                    // Update bang QT
                //                                    qtVO.setLoai_hach_toan(strQT[0]);
                //                                    if (qtDAO.update(qtVO) < 0) {
                //                                        return mapping.findForward(AppConstants.FAILURE);
                //                                    }
                //                                }
                //                            }
                //                        }
                //                    } else {
                //                        for (int j = 0; j < arrCheck.length; j++) {
                //                            if (arrCheck[j].trim() != "") {
                //                                qtVO.setId(arrCheck[j].trim());
                //                                qtVO.setSo_bk(strSoBK);
                //                                if (qtDAO.update(qtVO) < 0) {
                //                                    return mapping.findForward(AppConstants.FAILURE);
                //                                }
                //                                conn.commit();
                //                            }
                //                        }
                //                    }
                //
                //                }
                //                conn.commit();

                /*
                 * Yeu cau moi gom bang ke tu dong
                 * */
                conn = getConnection();
                QuyetToanToanQuocForm f = (QuyetToanToanQuocForm)form;
                HttpSession session = request.getSession();
                CallableStatement cs = null;
                cs =
 conn.prepareCall("call pkg_bang_ke_quyet_toan.pro_gom_bke_qtoan(?,?,?,?,?,?,?,?,?,?)");
                cs.setString("p_ma_nh",
                             f.getHsc_nh() != null ? f.getHsc_nh() : "");
                cs.setString("p_ngay_ttoan",
                             f.getNgay_tt() != null ? f.getNgay_tt() : "");
                cs.setString("p_ngay_qtoan",
                             f.getNgay_qt() != null ? f.getNgay_qt() : "");
                cs.setString("p_loai_qtoan",
                             f.getLoai_qt() != null ? f.getLoai_qt() : "");
                cs.setString("p_loai_tk",
                             f.getLoai_tk() != null ? f.getLoai_tk() : "");
                cs.setString("p_loai_tien",
                           f.getTcg_loai_tien() != null ? f.getTcg_loai_tien() : "");
                cs.setString("p_nguoi_tao",
                             session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                             null ?
                             session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString() :
                             "");
                cs.setString("p_ma_kb_tinh",
                             f.getKb_tinh() != null ? f.getKb_tinh() : "");
                cs.registerOutParameter("p_error_code", Types.VARCHAR);
                cs.registerOutParameter("p_error_desc", Types.VARCHAR);
                try {
                    cs.execute();
                } catch (Exception ex) {
                    request.setAttribute("p_err_desc",
                                         "Gom b&#7843;ng k&#234; th&#7845;t b&#7841;i ! \n" +
                            ex);
                }
                String p_err_code = cs.getString("p_error_code");
                String p_err_desc = cs.getString("p_error_desc");
                String strNumberBKCreated = "";
                String strSoBKCreated = "";
                if (p_err_desc != null && !STRING_EMPTY.equals(p_err_desc)) {
                    String strRegex = "\\|";
                    String strNewRegex = ", ";
                    String[] strResult = p_err_desc.split(strRegex);
                    strNumberBKCreated = strResult[0];
                    for (int i = 1; i < strResult.length; i++) {
                        strSoBKCreated += strResult[i] + strNewRegex;
                    }
                    if (strSoBKCreated.length() > 200) {
                        strSoBKCreated.substring(0, 200).concat("...");
                    }
                }
                if (p_err_code != null) {
                    if (p_err_code.equals("00")) {
                        request.setAttribute("p_err_desc",
                                             "Gom th&#224;nh c&#244;ng " +
                                             strNumberBKCreated +
                                             " b&#7843;ng k&#234; ! \n" +
                                strSoBKCreated);
                    } else {
                        request.setAttribute("p_err_desc",
                                             "Gom b&#7843;ng k&#234; th&#7845;t b&#7841;i ! \n" +
                                p_err_desc);
                    }
                }
            }
        } catch (Exception e) {
            conn.rollback();
            throw new Exception("T&#7841;o b&#7843;ng k&#234; : " +
                                e.getMessage());
        } finally {
            close(conn);
            list(mapping, form, request, response);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        QuyetToanDAO qtDAO = null;
        QuyetToanVO qtVO = null;
        JsonObject jsonObj = null;
        String strJson = null;
        try {
            if (isCancelled(request)) {
                return mapping.findForward(AppConstants.FAILURE);
            }
            if (!checkPermissionOnFunction(request, "QTOAN.TQUOC.TAOBKE")) {
                return mapping.findForward("errorQuyen");
            } else {
                conn = getConnection();
                jsonObj = new JsonObject();
                qtDAO = new QuyetToanDAO(conn);
                String id = request.getParameter("id");
                String value = request.getParameter("value");
                if (id != null && !STRING_EMPTY.equals(id)) {
                    qtVO = new QuyetToanVO();
                    qtVO.setId(id);
                    qtVO.setLoai_hach_toan(value);
                    if (qtDAO.update(qtVO) > 0)
                        conn.commit();
                }
            }
        } catch (Exception e) {
            conn.rollback();
            jsonObj.addProperty("exception_message", e.getMessage());
        } finally {
            strJson = jsonObj.toString();
            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJson.toString());
            out.flush();
            out.close();
            close(conn);
            list(mapping, form, request, response);
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }
}
