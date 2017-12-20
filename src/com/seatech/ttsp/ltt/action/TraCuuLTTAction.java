package com.seatech.ttsp.ltt.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.DateUtils;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPLoadDMuc;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.dmnhkb.DMHTKBacVO;
import com.seatech.ttsp.dmnhkb.DMNHKBacDAO;
import com.seatech.ttsp.dmtiente.DMTienTeDAO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.ltt.form.LTTForm;
import com.seatech.ttsp.thamchieu.MaThamChieuDAO;

import java.io.InputStream;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class TraCuuLTTAction extends AppAction {
    private static String SUCCESS = "success";
    private static String STRING_EMPTY = "";
    private static String TRACUU_LTT = "LTT.TRACUU";

    public DMHTKBacVO checkValidTTTT(String codeKB,
                                     Connection conn) throws Exception {
        try {
            String whereClauseKB = null;
            Vector paramsKB = new Vector();
            DMHTKBacVO nhkbVO = new DMHTKBacVO();
            DMNHKBacDAO nhkbDAO = new DMNHKBacDAO(conn);
            whereClauseKB = "ma = ?";
            paramsKB.add(new Parameter(codeKB, true));
            nhkbVO = nhkbDAO.getDMHTKBac(whereClauseKB, paramsKB);
            return nhkbVO;
        } catch (Exception e) {
            throw new Exception("Tra Cuu LTT checkValidTTTT(): " + e);
        }
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;

        try {
            conn = getConnection(request);

            //            LTTForm lttForm = (LTTForm)form;
            /*
         * MaThamChieuDAO
         * lstTrangThai
         * luu tru cac gia tri cua trang thai
         * */
            List lstTrangThai = null;
            MaThamChieuDAO thamchieuDAO = new MaThamChieuDAO(conn);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            String strWhere = "a.rv_domain = ?";
            Vector vParam = new Vector();
            vParam.add(new Parameter(AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT,
                                     true));
            lstTrangThai =
                    (List)thamchieuDAO.getMaThamChieuList(strWhere, vParam);

            request.setAttribute("lstTrangThai", lstTrangThai);
            
            // GET cap, DMKBHuyen, DMKBTinh -> Session
            TTSPLoadDMuc tTSPLoadDMuc = new TTSPLoadDMuc(conn);
            tTSPLoadDMuc.getDanhMucKB(request,response, null);
            
            List tienTe = tienTeDAO.simpleMaNgoaiTe("", null);
            /**
             * - Nguoi sua: ManhNV
             * - Ngay sua: 05/11/2016
             * - Noi dung: Sua loi ho tro muc cao, loi ko co danh muc
             * - BEGIN
             * **/
           ;
            
            request.setAttribute("tienTe", tienTe);
        } catch (Exception ex) {
            throw new Exception("Tra Cuu LTT: " + ex);
        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        /**
         * - Nguoi sua: ManhNV
         * - Ngay sua: 01/11/2016
         * - Noi dung: Sua loi Java heap size (Ko su dung ham voi truong partition)
         * - Key tim kiem: 20161101-JVH-TCLTT
         * */
        try {
            conn = getConnection(request);

            LTTForm lttForm = (LTTForm)form;
            /*
             * MaThamChieuDAO
             * lstTrangThai
             * luu tru cac gia tri cua trang thai
             * */
            List lstTrangThai = null;
            MaThamChieuDAO thamchieuDAO = new MaThamChieuDAO(conn);
            String strWhere = "a.rv_domain = ?";
            Vector vParam = new Vector();
            vParam.add(new Parameter(AppConstants.MA_THAM_CHIEU_TRANG_THAI_LTT,
                                     true));
            lstTrangThai =
                    (List)thamchieuDAO.getMaThamChieuList(strWhere, vParam);

            request.setAttribute("lstTrangThai", lstTrangThai);
            //tra ve danh sach tim kiem va phan trang
            LTTDAO dao = new LTTDAO(conn);
            DMTienTeDAO tienTeDAO = new DMTienTeDAO(conn);
            List<LTTVO> lstLTT = new ArrayList();
            String actionBack = null;
            if (request.getParameter(AppConstants.REQUEST_ACTION) != null) {
                actionBack =
                        request.getParameter(AppConstants.REQUEST_ACTION).toString();
            }
            if (actionBack != null && actionBack.equalsIgnoreCase("AddBack")) {
                String ttv_nhan_back = request.getParameter("ttv_nhan_back");
                String trang_thai_back =
                    request.getParameter("trang_thai_back");
                String tu_ngay_back = request.getParameter("tu_ngay_back");
                String den_ngay_back = request.getParameter("den_ngay_back");
                String ma_nhkb_chuyen_cm_back =
                    request.getParameter("ma_nhkb_chuyen_cm_back");
                String ma_nhkb_nhan_cm_back =
                    request.getParameter("ma_nhkb_nhan_cm_back");
                String tong_sotien_back = "";
                if (request.getParameter("tong_sotien_back") != null) {
                    tong_sotien_back =
                            request.getParameter("tong_sotien_back").replace("\\.",
                                                                             "");
                }
                String so_yctt_back = request.getParameter("so_yctt_back");
                String ltt_id_back = request.getParameter("ltt_id_back");
                String loai_lenh_tt_back = request.getParameter("loai_lenh_tt_back");
                
                if (ttv_nhan_back != null &&
                    !STRING_EMPTY.equals(ttv_nhan_back)) {
                    lttForm.setTtv_nhan(ttv_nhan_back);
                } else {
                    lttForm.setTtv_nhan(null);
                }
                if (trang_thai_back != null &&
                    !STRING_EMPTY.equals(trang_thai_back)) {
                    lttForm.setTrang_thai(trang_thai_back);
                } else {
                    lttForm.setTrang_thai(null);
                }
                if (tu_ngay_back != null &&
                    !STRING_EMPTY.equals(tu_ngay_back)) {
                    lttForm.setTu_ngay(tu_ngay_back);
                } else {
                    lttForm.setTu_ngay(null);
                }
                if (den_ngay_back != null &&
                    !STRING_EMPTY.equals(den_ngay_back)) {
                    lttForm.setDen_ngay(den_ngay_back);
                } else {
                    lttForm.setDen_ngay(null);
                }
                if (ma_nhkb_chuyen_cm_back != null &&
                    !STRING_EMPTY.equals(ma_nhkb_chuyen_cm_back)) {
                    lttForm.setMa_nhkb_chuyen_cm(ma_nhkb_chuyen_cm_back);
                } else {
                    lttForm.setMa_nhkb_chuyen_cm(null);
                }
                if (ma_nhkb_nhan_cm_back != null &&
                    !STRING_EMPTY.equals(ma_nhkb_nhan_cm_back)) {
                    lttForm.setMa_nhkb_nhan_cm(ma_nhkb_nhan_cm_back);
                } else {
                    lttForm.setMa_nhkb_nhan_cm(null);
                }
                if (tong_sotien_back != null &&
                    !STRING_EMPTY.equals(tong_sotien_back)) {
                    lttForm.setTong_sotien(tong_sotien_back);
                } else {
                    lttForm.setTong_sotien(null);
                }
                if (so_yctt_back != null &&
                    !STRING_EMPTY.equals(so_yctt_back)) {
                    lttForm.setSo_yctt(so_yctt_back);
                } else {
                    lttForm.setSo_yctt(null);
                }
                if (ltt_id_back != null && !STRING_EMPTY.equals(ltt_id_back)) {
                    lttForm.setId(ltt_id_back);
                } else {
                    lttForm.setId(null);
                }
                if (loai_lenh_tt_back != null &&
                    !STRING_EMPTY.equals(loai_lenh_tt_back)) {
                    lttForm.setLoai_lenh_tt(loai_lenh_tt_back);
                } else {
                    lttForm.setLoai_lenh_tt(null);
                }
                
            } else if (actionBack != null &&
                       actionBack.equalsIgnoreCase("back")) {
                String ttv_nhan_back = request.getParameter("ttv_nhan_back");
                String trang_thai_back =
                    request.getParameter("trang_thai_back");
                String tu_ngay_back = request.getParameter("tu_ngay_back");
                String den_ngay_back = request.getParameter("den_ngay_back");
                String tu_ngay_nhan_back =
                    request.getParameter("tu_ngay_nhan_back");
                String den_ngay_nhan_back =
                    request.getParameter("den_ngay_nhan_back");
                String so_ltt_back = request.getParameter("so_ltt_back");
                String loai_lenh_tt_back =
                    request.getParameter("loai_lenh_tt_back");
                String nhkb_chuyen_back =
                    request.getParameter("ma_nhkb_chuyen_cm_back");
                String nhkb_nhan_back =
                    request.getParameter("ma_nhkb_nhan_cm_back");
                String so_tien_back = request.getParameter("so_tien_back");
                String so_yctt_back = request.getParameter("so_yctt_back");
                String ten_nhkb_chuyen_back =
                    request.getParameter("ten_nhkb_chuyen_cm_back");
                String ten_nhkb_nhan_back =
                    request.getParameter("ten_nhkb_nhan_cm_back");
                String kb_tinh_back = request.getParameter("kb_tinh_back");
                String kb_huyen_back = request.getParameter("kb_huyen_back");
                String ma_nt_back = request.getParameter("ma_nt_back");
                
                if (ttv_nhan_back != null &&
                    !STRING_EMPTY.equals(ttv_nhan_back)) {
                    lttForm.setTtv_nhan(ttv_nhan_back);
                } else {
                    lttForm.setTtv_nhan(null);
                }
                if (trang_thai_back != null &&
                    !STRING_EMPTY.equals(trang_thai_back)) {
                    lttForm.setTrang_thai(trang_thai_back);
                } else {
                    lttForm.setTrang_thai(null);
                }
                if (tu_ngay_back != null &&
                    !STRING_EMPTY.equals(tu_ngay_back)) {
                    lttForm.setTu_ngay(tu_ngay_back);
                } else {
                    lttForm.setTu_ngay(null);
                }
                if (den_ngay_back != null &&
                    !STRING_EMPTY.equals(den_ngay_back)) {
                    lttForm.setDen_ngay(den_ngay_back);
                } else {
                    lttForm.setDen_ngay(null);
                }
                if (tu_ngay_nhan_back != null &&
                    !STRING_EMPTY.equals(tu_ngay_nhan_back)) {
                    lttForm.setTu_ngay_nhan(tu_ngay_nhan_back);
                } else {
                    lttForm.setTu_ngay_nhan(null);
                }
                if (den_ngay_nhan_back != null &&
                    !STRING_EMPTY.equals(den_ngay_nhan_back)) {
                    lttForm.setDen_ngay_nhan(den_ngay_nhan_back);
                } else {
                    lttForm.setDen_ngay_nhan(null);
                }

                if (so_ltt_back != null && !STRING_EMPTY.equals(so_ltt_back)) {
                    lttForm.setId(so_ltt_back);
                } else {
                    lttForm.setId(null);
                }
                if (loai_lenh_tt_back != null &&
                    !STRING_EMPTY.equals(loai_lenh_tt_back)) {
                    lttForm.setLoai_lenh_tt(loai_lenh_tt_back);
                } else {
                    lttForm.setLoai_lenh_tt(null);
                }

                if (nhkb_chuyen_back != null &&
                    !STRING_EMPTY.equals(nhkb_chuyen_back)) {
                    lttForm.setMa_nhkb_chuyen_cm(nhkb_chuyen_back);
                } else {
                    lttForm.setMa_nhkb_chuyen_cm(null);
                }
                if (nhkb_nhan_back != null &&
                    !STRING_EMPTY.equals(nhkb_nhan_back)) {
                    lttForm.setMa_nhkb_nhan_cm(nhkb_nhan_back);
                } else {
                    lttForm.setMa_nhkb_nhan_cm(null);
                }
                if (so_tien_back != null &&
                    !STRING_EMPTY.equals(so_tien_back)) {
                    lttForm.setTong_sotien(so_tien_back);
                } else {
                    lttForm.setTong_sotien(null);
                }
                if (so_yctt_back != null &&
                    !STRING_EMPTY.equals(so_yctt_back)) {
                    lttForm.setSo_yctt(so_yctt_back);
                } else {
                    lttForm.setSo_yctt(null);
                }
                if (ten_nhkb_chuyen_back != null &&
                    !STRING_EMPTY.equals(ten_nhkb_chuyen_back)) {
                    lttForm.setTen_nhkb_chuyen_cm(ten_nhkb_chuyen_back);
                } else {
                    lttForm.setTen_nhkb_chuyen_cm(null);
                }
                if (ten_nhkb_nhan_back != null &&
                    !STRING_EMPTY.equals(ten_nhkb_nhan_back)) {
                    lttForm.setTen_nhkb_nhan_cm(ten_nhkb_nhan_back);
                } else {
                    lttForm.setTen_nhkb_nhan_cm(null);
                }
                if (kb_tinh_back != null &&
                    !STRING_EMPTY.equals(kb_tinh_back)) {
                    lttForm.setKb_tinh(kb_tinh_back);
                } else {
                    lttForm.setKb_tinh(null);
                }
                if (kb_huyen_back != null &&
                    !STRING_EMPTY.equals(kb_huyen_back)) {
                    lttForm.setKb_huyen(kb_huyen_back);
                } else {
                    lttForm.setKb_huyen(null);
                }
                if (ma_nt_back != null && !STRING_EMPTY.equals(ma_nt_back)) {
                    lttForm.setMa_nt(ma_nt_back);
                } else {
                    lttForm.setMa_nt(null);
                }
            }

            String page = lttForm.getPageNumber();
            if (page == null) {
                page = "1";
            }
            // khai bao cac bien phan trang
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = new Integer(15);
            Integer totalCount[] = new Integer[1];
            // khai bao cac bien tuong tac voi tang DAO
            StringBuffer szWhereClause = new StringBuffer();
            Vector v_Param_ltt = new Vector();
            Parameter param_ltt = null;


            /*
             * @sea:kiem tra gia tri cua form va set gia tri cho vector
             * ttv_nhan,trang_thai,loai_lenh_tt,tong_sotien,NH_chuyen,NH_nhan,ngay_nhap,so YCTT,SLTT
             *             String strFromDate = lttForm.getNgay_nhap_nh();
                            String strToDate = lttForm.getNgay_nhap_nh();
             * */
            if (lttForm.getTtv_nhan() != null &&
                !"".equals(lttForm.getTtv_nhan())) {
                szWhereClause.append("and t.ttv_nhan in (select b.id from sp_nsd b where (lower(b.ma_nsd)  like lower(?)) or (lower(b.ten_nsd)  like lower(?))) ");
                param_ltt =
                        new Parameter("%" + lttForm.getTtv_nhan().trim() + "%",
                                      true);
                v_Param_ltt.add(param_ltt);
                param_ltt =
                        new Parameter("%" + lttForm.getTtv_nhan().trim() + "%",
                                      true);
                v_Param_ltt.add(param_ltt);
            }
            if (lttForm.getTrang_thai() != null &&
                !"".equals(lttForm.getTrang_thai()) &&
                !lttForm.getTrang_thai().equalsIgnoreCase("00")) {
                szWhereClause.append("and t.trang_thai = ? ");
                param_ltt =
                        new Parameter(lttForm.getTrang_thai().trim(), true);
                v_Param_ltt.add(param_ltt);
            }

            if (lttForm.getTu_ngay() != null &&
                !"".equals(lttForm.getTu_ngay().trim())) {
                Long lTu_ngay = DateUtils.DateToLong(lttForm.getTu_ngay());
                szWhereClause.append("and t.ngay_tt >= ? ");
                param_ltt = new Parameter(lTu_ngay, true);
                v_Param_ltt.add(param_ltt);
            }
            if (lttForm.getDen_ngay() != null &&
                !"".equals(lttForm.getDen_ngay().trim())) {
                Long lDen_ngay = DateUtils.DateToLong(lttForm.getDen_ngay());
                szWhereClause.append("and t.ngay_tt <= ? ");
                param_ltt = new Parameter(lDen_ngay, true);
                v_Param_ltt.add(param_ltt);
            }
            if (lttForm.getTu_ngay_nhan() != null &&
                !"".equals(lttForm.getTu_ngay_nhan().trim())) {
                String strTu_ngay = lttForm.getTu_ngay_nhan();
                //20161101-JVH-TCLTT: Bo ham trunc doi voi truong partiion ngay_nhan-BEGIN
                //szWhereClause.append("and trunc(t.ngay_nhan) >= to_date(?,'dd/mm/yyyy') ");
                szWhereClause.append("and t.ngay_nhan >= to_date(?,'dd/mm/yyyy') ");
                //20161101-JVH-TCLTT: Bo ham trunc doi voi truong partiion ngay_nhan-END
                param_ltt = new Parameter(strTu_ngay, true);
                v_Param_ltt.add(param_ltt);
            }
            if (lttForm.getDen_ngay_nhan() != null &&
                !"".equals(lttForm.getDen_ngay_nhan().trim())) {
                String strDen_ngay = lttForm.getDen_ngay_nhan();
                //20161101-JVH-TCLTT: Bo ham trunc doi voi truong partiion ngay_nhan-BEGIN
                //szWhereClause.append("and trunc(t.ngay_nhan) <= to_date(?,'dd/mm/yyyy') ");
                szWhereClause.append("and t.ngay_nhan <= to_date(?,'dd/mm/yyyy hh24:mi') ");
                //20161101-JVH-TCLTT: Bo ham trunc doi voi truong partiion ngay_nhan-END
                
                param_ltt = new Parameter(strDen_ngay+" 23:59", true);
                v_Param_ltt.add(param_ltt);
            }
            if (lttForm.getTtloai_lenh() != null &&
                !"".equals(lttForm.getTtloai_lenh().trim())) {
                String strTtloai_lenh = lttForm.getTtloai_lenh();
                szWhereClause.append("and t.ttloai_lenh = ? ");
                param_ltt = new Parameter(strTtloai_lenh, true);
                v_Param_ltt.add(param_ltt);
            }
            if (lttForm.getPhi() != null &&
                !"".equals(lttForm.getPhi().trim())) {
                String strPhi = lttForm.getPhi();
                szWhereClause.append("and t.phi = ? ");
                param_ltt = new Parameter(strPhi, true);
                v_Param_ltt.add(param_ltt);
            }


            //loai lenh
            HttpSession session = request.getSession();
            String strNHKBacID =
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
            String strKBCode =
                session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
            String loailenh_tt = lttForm.getLoai_lenh_tt();
            if (loailenh_tt == null || loailenh_tt.equals("")) {
                loailenh_tt = "00";
            }
            if (loailenh_tt.trim().equalsIgnoreCase("01")) {
                szWhereClause.append("and (substr(t.id,3,3) = '701' ) and t.nhan='N' ");
                if (request.getParameter("targetid") != null &&
                    request.getParameter("targetid").equalsIgnoreCase("dts")) {
                    szWhereClause.append(" AND t.trang_thai in('07','08','14','15','16') ");
                }
            } else if (loailenh_tt.trim().equalsIgnoreCase("04")) {
                szWhereClause.append("and (substr(t.id,3,3) = '701') and t.nhan='Y' ");
                if (request.getParameter("targetid") != null &&
                    request.getParameter("targetid").equalsIgnoreCase("dts")) {
                    szWhereClause.append(" AND t.trang_thai in('07','08','14','15','16') ");
                }
            } else if (loailenh_tt.trim().equalsIgnoreCase("02")) {
                szWhereClause.append("and (substr(t.id,3,3) <> '701') ");
            } else if (loailenh_tt.trim().equalsIgnoreCase("03")) {
                szWhereClause.append(" and (substr(t.id,3,3) = '701') ");
            } else {
                if (request.getParameter("targetid") != null &&
                    request.getParameter("targetid").equalsIgnoreCase("dts")) {
                    szWhereClause.append(" AND ((t.nhkb_chuyen=? AND t.trang_thai in('07','08','14','15','16')) OR t.nhkb_nhan=?)");
                    v_Param_ltt.add(new Parameter(new Long(strNHKBacID),
                                                  true));
                    v_Param_ltt.add(new Parameter(new Long(strNHKBacID),
                                                  true));
                }
            }

            if (lttForm.getTong_sotien() != null &&
                !"".equals(lttForm.getTong_sotien())) {
                szWhereClause.append("and t.tong_sotien =? ");
                BigDecimal bigSoTien = null;
                // 20171129 : taidd dịnh dang lai tien theo mau moi
                  bigSoTien =
                     new BigDecimal(StringUtil.formatMoneyVNDToDouble(lttForm.getTong_sotien().trim()));

                param_ltt =
                        new Parameter(bigSoTien, true);
                v_Param_ltt.add(param_ltt);
            }

            Long ma_nhkb_chuyen = null;
            Long ma_nhkb_nhan = null;
            if (lttForm.getMa_nhkb_chuyen_cm() != null &&
                !"".equals(lttForm.getMa_nhkb_chuyen_cm())) {
                ma_nhkb_chuyen =
                        getMaDVKB(lttForm.getMa_nhkb_chuyen_cm(), conn);
                if (ma_nhkb_chuyen != null) {
                    szWhereClause.append("and t.nhkb_chuyen = ? ");
                    param_ltt = new Parameter(ma_nhkb_chuyen, true);
                    v_Param_ltt.add(param_ltt);
                } else {
                    szWhereClause.append("and t.nhkb_chuyen in (select n.id from sp_dm_ngan_hang n where n.ma_nh like (?) )");
                    param_ltt =
                            new Parameter("%" + lttForm.getMa_nhkb_chuyen_cm() +
                                          "%", true);
                    v_Param_ltt.add(param_ltt);
                }
            }
            //            else {
            //                //                ma_nhkb_chuyen = new Long("10");
            //            }
            if (lttForm.getMa_nhkb_nhan_cm() != null &&
                !"".equals(lttForm.getMa_nhkb_nhan_cm())) {
                ma_nhkb_nhan = getMaDVKB(lttForm.getMa_nhkb_nhan_cm(), conn);
                if (ma_nhkb_nhan != null) {
                    szWhereClause.append("and t.nhkb_nhan = ? ");
                    param_ltt = new Parameter(ma_nhkb_nhan, true);
                    v_Param_ltt.add(param_ltt);
                } else {
                    szWhereClause.append("and t.nhkb_nhan in (select n.id from sp_dm_ngan_hang n where n.ma_nh like (?) )");
                    param_ltt =
                            new Parameter("%" + lttForm.getMa_nhkb_nhan_cm() +
                                          "%", true);
                    v_Param_ltt.add(param_ltt);
                }
            } else {
                //                ma_nhkb_nhan = new Long("10");
            }
            if (lttForm.getSo_yctt() != null &&
                !"".equals(lttForm.getSo_yctt())) {
                szWhereClause.append("and (t.so_yctt like (?) or t.so_tham_chieu_gd like (?) ) ");
                param_ltt =
                        new Parameter("%" + lttForm.getSo_yctt().trim() + "%",
                                      true);
                v_Param_ltt.add(param_ltt);
                v_Param_ltt.add(param_ltt);
            }
            if (lttForm.getId() != null && !"".equals(lttForm.getId())) {
                szWhereClause.append("and t.id like (?) ");
                param_ltt =
                        new Parameter("%" + lttForm.getId().trim() + "%", true);
                v_Param_ltt.add(param_ltt);
            }
            if (lttForm.getKb_tinh() != null &&
                !"".equals(lttForm.getKb_tinh())) {
                if (lttForm.getKb_huyen() != null &&
                    !"".equals(lttForm.getKb_huyen())) {
                    szWhereClause.append("and (a.ma_nh = ? " +
                                         "or b.ma_nh = ? )");
                    param_ltt = new Parameter(lttForm.getKb_huyen(), true);
                    v_Param_ltt.add(param_ltt);
                    v_Param_ltt.add(param_ltt);
                } else {
                    szWhereClause.append("and (a.ma_nh in (select ma_nh from sp_dm_manh_shkb where shkb in (select ma from sp_dm_htkb where ma_cha = ? or ma = ?)) " +
                                         "or b.ma_nh in (select ma_nh from sp_dm_manh_shkb where shkb in (select ma from sp_dm_htkb where ma_cha = ? or ma = ?)))");
                    param_ltt = new Parameter(lttForm.getKb_tinh(), true);
                    v_Param_ltt.add(param_ltt);
                    v_Param_ltt.add(param_ltt);
                    v_Param_ltt.add(param_ltt);
                    v_Param_ltt.add(param_ltt);
                }
            }

            if (lttForm.getMa_nt() != null && !"".equals(lttForm.getMa_nt())) {
                szWhereClause.append(" and d.ma = ? ");
                param_ltt = new Parameter(lttForm.getMa_nt(), true);
                v_Param_ltt.add(param_ltt);
            }
            /**
             * Kiem tra TTTT
             * 1. Hien thi tat ca neu la TTTT
             * 2. Chi hien thi ban ghi don vi
             * @param:strNHKB_code
             * */

            //            DMHTKBacVO voDMNH = checkValidTTTT(strKBCode, conn);
            //            String strDelimiter = ",";
            //            if (AppConstants.MA_TTTT.indexOf(strDelimiter) > -1) {
            //                String[] strFound = AppConstants.MA_TTTT.split(strDelimiter);
            //                for (int i = 0; i < strFound.length; i++) {
            //                    if (!voDMNH.getMa().toString().equalsIgnoreCase(strFound[i])) {
            //                        szWhereClause.append(" and (t.nhkb_chuyen = ? or t.nhkb_nhan = ?)");
            //                        param_ltt = new Parameter(strNHKBacID, true);
            //                        v_Param_ltt.add(param_ltt);
            //                        v_Param_ltt.add(param_ltt);
            //                        break;
            //                    }
            //                }
            //            }
            lstLTT =
                    (List<LTTVO>)dao.getLTTDiListWithPading(szWhereClause.toString(),
                                                            v_Param_ltt,
                                                            currentPage,
                                                            numberRowOnPage,
                                                            totalCount);
            LTTVO voSum = new LTTVO();
            voSum = dao.getSumTongTien(szWhereClause.toString(), v_Param_ltt);
            request.setAttribute("tong_mon", voSum.getTong_mon());
            request.setAttribute("lltVO", voSum);

            ArrayList<LTTVO> lstLTTTmp = new ArrayList<LTTVO>();
            for (LTTVO vo : lstLTT) {
                if (vo.getNgay_tt() != null &&
                    !STRING_EMPTY.equals(vo.getNgay_tt())) {
                    vo.setNgay_tt_tmp(DateUtils.LongToStrDateDDMMYYYY(vo.getNgay_tt()));
                }
                lstLTTTmp.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstLTT", lstLTTTmp);
            /*
             * LTTDAO
             * End
             * */
            saveVisitLog(conn, session, TRACUU_LTT, "");
            /*
             * lay lai thong tin kb tinh huyen
             *
             * */
            /** neu la trung tam thanh toan thi lay ra toan bo kb tinh de lua chon
              * Lay ra danh sach Kho bac tinh
              **/
          
          // GET cap, DMKBHuyen, DMKBTinh -> Session
          TTSPLoadDMuc tTSPLoadDMuc = new TTSPLoadDMuc(conn);
          tTSPLoadDMuc.getDanhMucKB(request,response, lttForm.getKb_tinh());
            
            List tienTe = tienTeDAO.simpleMaNgoaiTe("", null);
            ;
            request.setAttribute("tienTe", tienTe);
            request.setAttribute("isVN", "VND".equals(lttForm.getMa_nt()));
        } catch (Exception ex) {
            throw new Exception("Tra Cuu LTT: " + ex);
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public Long getMaDVKB(String maDVTS, Connection conn) throws Exception {
        Long id = null;
        String strWhereClause = " ma_nh = ?";
        Vector params = new Vector();
        params.add(new Parameter(maDVTS, true));
        DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
        DMNHangVO vo = dmnhDAO.getDMNH(strWhereClause, params);
        if (vo != null) {
            id = vo.getId();
        }
        return id;
    }

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        Connection conn = null;
        String reportName = "LTTRptForm";
        //String reportName = "LTT_e";
        StringBuffer szWhereClause = null;
        Vector v_Param_ltt = null;
        Parameter param_ltt = null;
        InputStream reportStream = null;
        ResultSet resultSet = null;
        LTTForm lttForm = null;
        JasperPrint jasperPrint = null;


        StringBuffer sbSubHTML = new StringBuffer();
        try {
            conn = getConnection(request);
            szWhereClause = new StringBuffer();
            v_Param_ltt = new Vector();

            lttForm = (LTTForm)form;
            LTTDAO dao = new LTTDAO(conn);
            // tao Map truyen vao report
            // cac parameter dieu kien tra cuu
            // Hien thi da ta phan loai theo user
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                            "/" +
                                                                                            reportName +
                                                                                            ".jasper");
            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
            if (reportStream != null) {
                //Tim kiem
                if (lttForm.getTtv_nhan() != null &&
                    !"".equals(lttForm.getTtv_nhan())) {

                    sbSubHTML.append("<input type=\"hidden\" name=\"ttv_nhan\" value=\"" +
                                     lttForm.getTtv_nhan() +
                                     "\" id=\"ttv_nhan\"></input>");
                    szWhereClause.append("and t.ttv_nhan in (select b.id from sp_nsd b where (lower(b.ma_nsd)  like lower(?)) or (lower(b.ten_nsd)  like lower(?))) ");
                    param_ltt =
                            new Parameter("%" + lttForm.getTtv_nhan().trim() +
                                          "%", true);
                    v_Param_ltt.add(param_ltt);
                    param_ltt =
                            new Parameter("%" + lttForm.getTtv_nhan().trim() +
                                          "%", true);
                    v_Param_ltt.add(param_ltt);
                }
                if (lttForm.getTrang_thai() != null &&
                    !"".equals(lttForm.getTrang_thai()) &&
                    !lttForm.getTrang_thai().equalsIgnoreCase("00")) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"trang_thai\" value=\"" +
                                     lttForm.getTrang_thai() +
                                     "\" id=\"trang_thai\"></input>");
                    szWhereClause.append("and t.trang_thai = ? ");
                    param_ltt =
                            new Parameter(lttForm.getTrang_thai().trim(), true);
                    v_Param_ltt.add(param_ltt);
                }

                if (lttForm.getTu_ngay() != null &&
                    !"".equals(lttForm.getTu_ngay().trim())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay\" value=\"" +
                                     lttForm.getTu_ngay() +
                                     "\" id=\"tu_ngay\"></input>");
                    Long lTu_ngay = DateUtils.DateToLong(lttForm.getTu_ngay());
                    szWhereClause.append("and t.ngay_tt >= ?");
                    param_ltt = new Parameter(lTu_ngay, true);
                    v_Param_ltt.add(param_ltt);
                }
                if (lttForm.getDen_ngay() != null &&
                    !"".equals(lttForm.getDen_ngay().trim())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay\" value=\"" +
                                     lttForm.getDen_ngay() +
                                     "\" id=\"den_ngay\"></input>");
                    szWhereClause.append("and t.ngay_tt <= ?");
                    Long lDen_ngay =
                        DateUtils.DateToLong(lttForm.getDen_ngay());
                    param_ltt = new Parameter(lDen_ngay, true);
                    v_Param_ltt.add(param_ltt);
                }

                // ngay truyen nhan

                if (lttForm.getTu_ngay_nhan() != null &&
                    !"".equals(lttForm.getTu_ngay_nhan().trim())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"tu_ngay_nhan\" value=\"" +
                                     lttForm.getTu_ngay_nhan() +
                                     "\" id=\"tu_ngay_nhan\"></input>");
                    szWhereClause.append("and trunc(t.ngay_nhan) >= to_date(?" +
                                         ",'DD/MM/YYYY') ");
                    param_ltt =
                            new Parameter(lttForm.getTu_ngay_nhan().trim(), true);
                    v_Param_ltt.add(param_ltt);
                }
                if (lttForm.getDen_ngay_nhan() != null &&
                    !"".equals(lttForm.getDen_ngay_nhan().trim())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"den_ngay_nhan\" value=\"" +
                                     lttForm.getDen_ngay_nhan() +
                                     "\" id=\"den_ngay_nhan\"></input>");
                    szWhereClause.append("and trunc(t.ngay_nhan) <= to_date(?," +
                                         "'DD/MM/YYYY') ");
                    param_ltt =
                            new Parameter(lttForm.getDen_ngay_nhan().trim(),
                                          true);
                    v_Param_ltt.add(param_ltt);
                }

                //loai lenh
                HttpSession session = request.getSession();
                String strNHKBacID =
                    session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
                String strKBCode =
                    session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
                String loailenh_tt = lttForm.getLoai_lenh_tt();
                if (loailenh_tt == null || loailenh_tt.equals("")) {
                    loailenh_tt = "00";
                }
                if (loailenh_tt.trim().equalsIgnoreCase("01")) {
                    szWhereClause.append("and (instr(t.id, '701') = 3) and t.nhkb_chuyen = ? and t.nhan='N' ");
                    param_ltt = new Parameter(new Long(strNHKBacID), true);
                    v_Param_ltt.add(param_ltt);
                    if (request.getParameter("targetid") != null &&
                        request.getParameter("targetid").equalsIgnoreCase("dts")) {
                        szWhereClause.append(" AND t.trang_thai in('07','08','14','15','16') ");
                    }
                } else if (loailenh_tt.trim().equalsIgnoreCase("04")) {
                    szWhereClause.append("and (instr(t.id, '701') = 3) and t.nhkb_chuyen = ? and t.nhan='Y' ");
                    param_ltt = new Parameter(new Long(strNHKBacID), true);
                    v_Param_ltt.add(param_ltt);
                    if (request.getParameter("targetid") != null &&
                        request.getParameter("targetid").equalsIgnoreCase("dts")) {
                        szWhereClause.append(" AND t.trang_thai in('07','08','14','15','16') ");
                    }
                } else if (loailenh_tt.trim().equalsIgnoreCase("02")) {
                    szWhereClause.append("and (instr(t.id, '701') <> 3) and t.nhkb_nhan = ? ");
                    param_ltt = new Parameter(new Long(strNHKBacID), true);
                    v_Param_ltt.add(param_ltt);
                } else if (loailenh_tt.trim().equalsIgnoreCase("03")) {
                    szWhereClause.append(" and (instr(t.id, '701') = 3) and t.nhkb_chuyen = ? ");
                    param_ltt = new Parameter(new Long(strNHKBacID), true);
                    v_Param_ltt.add(param_ltt);
                } else {
                    if (request.getParameter("targetid") != null &&
                        request.getParameter("targetid").equalsIgnoreCase("dts")) {
                        szWhereClause.append(" AND ((t.nhkb_chuyen=? AND t.trang_thai in('07','08','14','15','16')) OR t.nhkb_nhan=?)");
                        v_Param_ltt.add(new Parameter(new Long(strNHKBacID),
                                                      true));
                        v_Param_ltt.add(new Parameter(new Long(strNHKBacID),
                                                      true));
                    }
                }

                if (lttForm.getTong_sotien() != null &&
                    !"".equals(lttForm.getTong_sotien())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"tong_sotien\" value=\"" +
                                     lttForm.getTong_sotien() +
                                     "\" id=\"tong_sotien\"></input>");
                    szWhereClause.append("and t.tong_sotien = ? ");
                    param_ltt =
                            new Parameter(lttForm.getTong_sotien().trim(), true);
                    v_Param_ltt.add(param_ltt);
                }

                Long ma_nhkb_chuyen = null;
                Long ma_nhkb_nhan = null;
                if (lttForm.getMa_nhkb_chuyen_cm() != null &&
                    !"".equals(lttForm.getMa_nhkb_chuyen_cm())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"ma_nhkb_chuyen_cm\" value=\"" +
                                     lttForm.getMa_nhkb_chuyen_cm() +
                                     "\" id=\"ma_nhkb_chuyen_cm\"></input>");
                    ma_nhkb_chuyen =
                            getMaDVKB(lttForm.getMa_nhkb_chuyen_cm(), conn);
                    if (ma_nhkb_chuyen != null) {
                        szWhereClause.append("and t.nhkb_chuyen = ? ");
                        param_ltt = new Parameter(ma_nhkb_chuyen, true);
                        v_Param_ltt.add(param_ltt);
                    } else {
                        szWhereClause.append("and t.nhkb_chuyen in (select n.id from sp_dm_ngan_hang n where n.ma_nh like (?) )");
                        param_ltt =
                                new Parameter("%" + lttForm.getMa_nhkb_chuyen_cm() +
                                              "%", true);
                        v_Param_ltt.add(param_ltt);
                    }
                }

                if (lttForm.getMa_nhkb_nhan_cm() != null &&
                    !"".equals(lttForm.getMa_nhkb_nhan_cm())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"ma_nhkb_nhan_cm\" value=\"" +
                                     lttForm.getMa_nhkb_nhan_cm() +
                                     "\" id=\"ma_nhkb_nhan_cm\"></input>");
                    ma_nhkb_nhan =
                            getMaDVKB(lttForm.getMa_nhkb_nhan_cm(), conn);
                    if (ma_nhkb_nhan != null) {
                        szWhereClause.append("and t.nhkb_nhan = ? ");
                        param_ltt = new Parameter(ma_nhkb_nhan, true);
                        v_Param_ltt.add(param_ltt);
                    } else {
                        szWhereClause.append("and t.nhkb_nhan in (select n.id from sp_dm_ngan_hang n where n.ma_nh like (?) )");
                        param_ltt =
                                new Parameter("%" + lttForm.getMa_nhkb_nhan_cm() +
                                              "%", true);
                        v_Param_ltt.add(param_ltt);
                    }
                }
                if (lttForm.getSo_yctt() != null &&
                    !"".equals(lttForm.getSo_yctt())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"so_yctt\" value=\"" +
                                     lttForm.getSo_yctt() +
                                     "\" id=\"so_yctt\"></input>");
                    szWhereClause.append("and t.so_yctt like (?) ");
                    param_ltt =
                            new Parameter("%" + lttForm.getSo_yctt().trim() +
                                          "%", true);
                    v_Param_ltt.add(param_ltt);
                }
                if (lttForm.getId() != null && !"".equals(lttForm.getId())) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                                     lttForm.getId() +
                                     "\" id=\"id\"></input>");
                    szWhereClause.append("and t.id like (?) ");
                    param_ltt =
                            new Parameter("%" + lttForm.getId().trim() + "%",
                                          true);
                    v_Param_ltt.add(param_ltt);
                }
                /**
                   * Kiem tra TTTT
                   * 1. Hien thi tat ca neu la TTTT
                   * 2. Chi hien thi ban ghi don vi
                   * @param:strNHKB_code
                   * */

                DMHTKBacVO voDMNH = checkValidTTTT(strKBCode, conn);
                String strDelimiter = ",";
                if (AppConstants.MA_TTTT.indexOf(strDelimiter) > -1) {
                    String[] strFound =
                        AppConstants.MA_TTTT.split(strDelimiter);
                    for (int i = 0; i < strFound.length; i++) {
                        if (!voDMNH.getMa().toString().equalsIgnoreCase(strFound[i])) {
                            szWhereClause.append(" and (t.nhkb_chuyen = ? or t.nhkb_nhan = ?)");
                            param_ltt = new Parameter(strNHKBacID, true);
                            v_Param_ltt.add(param_ltt);
                            v_Param_ltt.add(param_ltt);
                            break;
                        }
                    }
                }
                resultSet =
                        dao.getLTTDiListByPrint(szWhereClause.toString(), v_Param_ltt);
                if (resultSet == null) {
                    request.setAttribute("status",
                                         "tra_cuu_dts.page.warning.ketqua.null");
                } else {
                    HashMap parameterMap = new HashMap();
                    parameterMap.put("SESS_ID",
                                     session.getAttribute(AppConstants.APP_USER_NAME_SESSION));
                    parameterMap.put("REPORT_LOCALE",
                                     new java.util.Locale("vi", "VI"));
                    jasperPrint =
                            JasperFillManager.fillReport(reportStream, parameterMap,
                                                         new JRResultSetDataSource(resultSet));
                    String strTypePrintAction =
                        request.getParameter(AppConstants.REQUEST_ACTION) ==
                        null ? "" :
                        request.getParameter(AppConstants.REQUEST_ACTION).toString();
                    String strActionName = "lttRptAction.do";
                    String strParameter = "";
                    ReportUtility rpUtilites = new ReportUtility();
                    rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                            response, reportName, strPathFont,
                                            strActionName,
                                            sbSubHTML.toString(),
                                            strParameter);
                }

            } else
                throw new Exception("Kh&#244;ng t&#236;m th&#7845;y file jasper report");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            reportStream.close();
            conn.close();
        }
        if (!response.isCommitted())
            return mapping.findForward(AppConstants.SUCCESS);
        else
            return null;
    }

}
