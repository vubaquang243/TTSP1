package com.seatech.ttsp.dchieungoaite.action;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.FontUtil;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieungoaite.DChieuNgoaiTeDAO;
import com.seatech.ttsp.dchieu.DChieu1VO;
import com.seatech.ttsp.dchieu.DNQTVO;
import com.seatech.ttsp.dchieu.DuyetKQDCVO;
import com.seatech.ttsp.dchieu.KQDChieu1VO;
import com.seatech.ttsp.dchieu.XNKQDCDataVO;
import com.seatech.ttsp.dchieu.form.XNDCTHop1Form;
import com.seatech.ttsp.qtoanbu.QToanBuDAO;
import com.seatech.ttsp.qtoanbu.QToanBuVO;
import com.seatech.ttsp.quyettoan.QuyetToanDAO;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;
import com.seatech.ttsp.thamsokb.ThamSoKBDAO;
import com.seatech.ttsp.thamsokb.ThamSoKBVO;

import com.seatech.ttsp.tsoSDuCOT.TSoSDuCOTDAO;
import com.seatech.ttsp.tsoSDuCOT.TSoSDuCOTVO;

import java.io.InputStream;
import java.io.PrintWriter;

import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**  
   * @modify: ThuongDT
   * @modify date: 21/02/2017   
   * @see: lay them so de nghi quyet toan tam thoi va lay so du thoi diem COT len bao cao su ly trong ham printAction
   * @track: ThuongDT-20170221
   * */

public class XNDCTHopNgoaiTeAction extends AppAction {

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.NT_DNQT")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
                return mapping.findForward("notRight");
            }


            DChieuNgoaiTeDAO dao = new DChieuNgoaiTeDAO(conn);
            DuyetKQDCVO duyetVO = new DuyetKQDCVO();
            XNDCTHop1Form thForm = (XNDCTHop1Form)form;
            Collection colTTSP = new ArrayList();
            Collection colPHT = new ArrayList();
            Collection colTHDC = new ArrayList();
            Collection colGDTCong = new ArrayList();
            Collection col066 = new ArrayList();
            DNQTVO dnqtVO = new DNQTVO();
            ThamSoKBVO kbVO = new ThamSoKBVO();
            ThamSoKBDAO kbDAO = new ThamSoKBDAO(conn);
            
            String kb_chuyen = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String nhkb_id = session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
            String id_kb = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();            

            ThamSoHThongVO tsVO = new ThamSoHThongVO();
            ThamSoHThongDAO tsDAO = new ThamSoHThongDAO(conn);
            String strTS = " ten_ts='GIOI_HAN_NGAY_LIST_DSACH_DCHIEU'";
            tsVO = tsDAO.getThamSo(strTS, null);
            String giatri_ts = tsVO.getGiatri_ts();
            String strCHK = " AND trunc(ngay_qtoan) < trunc(sysdate) AND trunc(ngay_qtoan) >= trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE -" +
                            giatri_ts + ")) AND nhkb_chuyen='" + kb_chuyen + "' AND (loai_tien <> 'VND' AND loai_tien IS NOT NULL)";
            dnqtVO = dao.chkDNQT(strCHK, null);
            Long chk_duyet = dnqtVO.getChk_duyet();
            Long chk_qtoan = dnqtVO.getChk_qtoan();
            if (chk_duyet != 0 || chk_qtoan != 0) {
                dnqtVO.setTrang_thai("06");
                dnqtVO.setNhkb_chuyen(kb_chuyen);
//                if(dao.updateInit066(dnqtVO, strCHK) > 0){
//                    if(dao.updateInit065(dnqtVO,giatri_ts) > 0){
//                        conn.commit();
//                    }
//                    else{
//                        conn.rollback();
//                        throw new Exception("Java Error. L\u1ED7i c\u1EADp nh\u1EADt 065");
//                    }
//                }
                if (dao.updateInit066(dnqtVO, strCHK) > 0 ||
                    dao.updateInit065(dnqtVO, giatri_ts) > 0) {
                    conn.commit();
                }
            }
            String tu_ngay = "";
            if (!"0".equals(giatri_ts) && !"".equals(giatri_ts) &&
                giatri_ts != null) {
                tu_ngay = "And TRUNC (d1.ngay_gd+1) > TRUNC (SYSDATE -" + giatri_ts + ")";
            }

            ArrayList<DuyetKQDCVO> colDChieu = (ArrayList<DuyetKQDCVO>)dao.getXNDCTHop(kb_chuyen, nhkb_id, tu_ngay, null);
            if (colDChieu.isEmpty()) {
                saveToken(request);
                return mapping.findForward("success");
            }
            request.setAttribute("colDChieu", colDChieu);

            String rowSelected = request.getParameter("rowSelected");
            String updteStatus = request.getAttribute("update_status")==null?"":request.getAttribute("update_status").toString();
            if (null == rowSelected || "".equals(rowSelected) ||
                "row_qt_0".equals(rowSelected) || "updated".equalsIgnoreCase(updteStatus)) {
                duyetVO = colDChieu.get(0);
                BeanUtils.copyProperties(thForm, duyetVO);
                request.setAttribute("rowSelected", "row_qt_0");
            } else {
                request.setAttribute("rowSelected", rowSelected);
            }

            String receive_bank = thForm.getReceive_bank();
            
            String ngay_dc = thForm.getNgay_dc();
            String tthai_dxn_thop = thForm.getTthai_dxn_thop();
            
            //ManhNV-20150122
//            String loai_tien = request.getParameter("loai_tien") == null ? thForm.getLoai_tien() : request.getParameter("loai_tien");
            String loai_tien = thForm.getLoai_tien() == null?request.getParameter("loai_tien"):thForm.getLoai_tien();
            //
            String strKB = "";
            strKB = " and e.ten_ts='CHO_PHEP_QUYET_TOAN_TAM' AND a.kb_id=" + id_kb +
                    " AND b.ma_nh='" + receive_bank + "' AND a.ma_nt='"+loai_tien+"' AND a.loai_tk <> '01' ";//ManhNV them 20150122
            kbVO = kbDAO.getLoai_GD(strKB, null);

            String loai_gd = kbVO.getLoai_gd();
            String qtoan_ko_dchieu = kbVO.getGiatri_ts();

            // call package tinh so lieu doi chieu tong hop
            String ttsp_id = thForm.getTtsp_id();
            String pht_id = thForm.getPht_id();
            if ("00".equals(tthai_dxn_thop) || "".equals(tthai_dxn_thop) ||
                tthai_dxn_thop == null) {
                CallableStatement cs = null;
                cs = conn.prepareCall("{call sp_doi_chieu_ngoai_te_pkg.proc_tinh_so_qtoan_ngoai_te(?,?,?,?)}");
                if ("03".equals(loai_gd)) { // loai chuyen thu
                    cs.setString(1, "0000000000000000");
                    cs.setString(2, pht_id);
                    
                    //ManhNV: kiem tra lai cua don vi chuyen thu
                    QuyetToanDAO quyetToanDAO = new QuyetToanDAO(conn);
                    String sql = "lai_phi = '02' AND ngay_ks_nh >= sp_util_pkg.fnc_get_ngay_gio_cutoftime(?,?,?)"+
                                 " AND ngay_ks_nh-1 < ?"+
                                 " AND ma_kb = ? AND ma_nh_chuyen = ?";
                    Vector param = new Vector();
                    
                    Date dNgayDC = StringUtil.StringToDate(ngay_dc, "dd/MM/yyyy");
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dNgayDC);
                    String strNgayTruocDC = StringUtil.getPreviousNextDate(cal, -1);
                    
                    param.add(new Parameter(strNgayTruocDC, true));
                    param.add(new Parameter(kb_chuyen, true));
                    param.add(new Parameter(receive_bank, true));
                    param.add(new DateParameter(dNgayDC, true));
                    param.add(new Parameter(kb_chuyen, true));
                    param.add(new Parameter(receive_bank, true));
                    int count = quyetToanDAO.getCountQT(sql, param);
                    if(count > 0){
                      thForm.setLai_phi_chuyen_thu("CO");
                    }else{
                      thForm.setLai_phi_chuyen_thu("KHONG");
                    }
                    //
                } else if ("02".equals(loai_gd)) { // ko co PHT
                    cs.setString(1, ttsp_id);
                    cs.setString(2, "0000000000000000");
                } else { // loai co PHT
                    cs.setString(1, ttsp_id);
                    cs.setString(2, pht_id);
                }

                cs.registerOutParameter(3, java.sql.Types.VARCHAR);
                cs.registerOutParameter(4, java.sql.Types.VARCHAR);
                cs.execute();
                String p_err_code = cs.getString(3);
                String p_err_desc = cs.getString(4);
                conn.commit();
            }
            // end call;

            if ("02".equals(loai_gd)) {
                request.setAttribute("notPHT", "notPHT");
                request.setAttribute("pht_ttsp", "pht_ttsp");
            }
            if ("03".equals(loai_gd)) {
                request.setAttribute("notTTSP", "notTTSP");
                request.setAttribute("pht_ttsp", "pht_ttsp");
            }
            request.setAttribute("loai_gd", loai_gd);
            
            thForm.setLoai_tien(loai_tien);
            
            String strW = "";
            strW = "select max(id) from sp_065_ngoai_te a where a.receive_bank = '" + receive_bank +
                   "' and send_bank='" + kb_chuyen +
                   "' and to_char(a.ngay_dc,'DD/MM/RRRR') = '" + ngay_dc + "'";
            String strTTSP = strW + " AND app_type='TTSP' AND a.loai_tien = '"+loai_tien+"'";
            String strPHT = strW + " AND app_type='PHT'  AND a.loai_tien = '"+loai_tien+"'";

            String str066 = " AND a.nhkb_chuyen= '" + kb_chuyen + "' and a.nhkb_nhan='" +
                            receive_bank + "' and to_char(a.ngay_qtoan,'DD/MM/RRRR')='" +
                            ngay_dc + "' and a.loai_qtoan <> '03' AND a.loai_tien ='"+loai_tien+"'";

            String strGDTCong = " AND a.ma_kb= '" + kb_chuyen + "' AND a.loai_tien = '"+loai_tien+"' and a.ma_nh='" + 
                                receive_bank + "'  and to_char(a.ngay_gd,'DD/MM/RRRR')='" +
                                ngay_dc + "'";
            colTTSP = dao.getTTSP_PHT(strTTSP, null);
            colPHT = dao.getTTSP_PHT(strPHT, null);
            colTHDC = dao.getXNTHData(strTTSP, null);
            col066 = dao.getData066(str066, null);
            colGDTCong = dao.getSoTCong(strGDTCong, null);

            request.setAttribute("colGDTCong", colGDTCong);
            request.setAttribute("colTTSP", colTTSP);
            request.setAttribute("colPHT", colPHT);

            request.setAttribute("qtoan_ko_dchieu", qtoan_ko_dchieu);
            String sysdate = StringUtil.DateToString(new Date(), "dd/MM/yyyy");
            String ngay_cuoi_nam = ngay_dc.substring(0, 5);


//            if (!(ngay_dc).equals(sysdate) || "31/12".equals(ngay_cuoi_nam)) {
            if (!(ngay_dc).equals(sysdate)) {
                if (col066.size() > 0) {
                    request.setAttribute("col066", col066);
                    request.setAttribute("size", col066.size());
                    request.setAttribute("chkdate", "chkdate");
                } else {
                    request.setAttribute("colTHDC", null);
                    request.setAttribute("chkdate", "chkdate");
                }
            } else {
                request.setAttribute("colTHDC", colTHDC);
                request.setAttribute("col066", col066);
                request.setAttribute("size", col066.size());
            }

            if (colTTSP.isEmpty() || colPHT.isEmpty()) {
                request.setAttribute("qtoan_ko_dchieu", qtoan_ko_dchieu);
                if (col066.size() > 0) {
                    request.setAttribute("col066", col066);
                    request.setAttribute("size", col066.size());
                }
                if ("02".equals(loai_gd)) {
                    request.setAttribute("colTTSP", colTTSP);
                    request.setAttribute("colPHT", null);
                }
                if ("03".equals(loai_gd)) {
                    request.setAttribute("colTTSP", null);
                    request.setAttribute("colPHT", colPHT);
                }
            }

            if ("03".equals(loai_gd)) {
                colDChieu = (ArrayList<DuyetKQDCVO>)dao.getXNDCTHop(kb_chuyen, nhkb_id, tu_ngay, null);
                if (colDChieu.isEmpty()) {
                    saveToken(request);
                    return mapping.findForward("success");
                }
                request.setAttribute("colDChieu", colDChieu);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            saveToken(request);
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.NT_DNQT")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String strUserInfo = (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            Long kb_id = (Long)session.getAttribute(AppConstants.APP_KB_ID_SESSION);
            String creator = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();

            if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
                return mapping.findForward("notRight");
            }
            if (isTokenValid(request)) {
                DChieuNgoaiTeDAO dao = new DChieuNgoaiTeDAO(conn);
                XNDCTHop1Form frm = (XNDCTHop1Form)form;
                KQDChieu1VO dcVO = new KQDChieu1VO();

                String ngay_dc = frm.getNgay_dc();
                String sysdate = StringUtil.DateToString(new Date(), "dd/MM/yyyy");

                String ttsp_id = request.getParameter("ttsp_id");
                String pht_id = request.getParameter("pht_id");
                String type = frm.getType();
                String loai_gd = frm.getLoai_gd();
                String kb_chuyen = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
                String ma_nh = frm.getReceive_bank();
                String loai_kq066 = frm.getLoai_kq066();
                String tthai_dxn_thop = frm.getTthai_dxn_thop();
                String cho_phep_nhap_tcong = frm.getCho_phep_nhap_tcong();
                String cho_phep_qtoan_tam = frm.getCho_phep_qtoan_tam();
                String loai_tien = frm.getLoai_tien();
                    
                String strChkSoDu =
                    " AND ma_kb='" + kb_chuyen + "' and ma_nh='" + ma_nh +"' and a.LOAI_TIEN = '"+loai_tien+"' and to_char(ngay_gd,'DD/MM/YYYY')='" + ngay_dc + "'";

                ArrayList<DNQTVO> colChkSoDu = (ArrayList<DNQTVO>)dao.chkSoDu(strChkSoDu, null);
                if (colChkSoDu.isEmpty() && !"Y".equals(cho_phep_qtoan_tam)) {
                    request.setAttribute("qtth", "qtth");
                    return mapping.findForward("success");
                }
//20150128-manhnv***************************************************************
//                if ("Y".equals(cho_phep_qtoan_tam)) {
//                    if (sysdate.equals(ngay_dc) && (tthai_dxn_thop == null || "".equals(tthai_dxn_thop) || "00".equals(tthai_dxn_thop))) {
//                        dcVO.setTthai_dxn_thop("00");
//                    } else {
//                        dcVO.setTthai_dxn_thop("01");
//                    }
//                } else if (!"Y".equals(cho_phep_qtoan_tam)) {
                    dcVO.setTthai_dxn_thop("01");
//                }
//20150128-manhnv***************************************************************
                if (ttsp_id != null && !"".equals(ttsp_id)) {
                    if ("C".equals(type)) {
                        dcVO.setKet_qua_dxn_thop("0"); // trang thai khi doi chieu ngay cu
                    } else if ("XN".equals(type)) {
                        dcVO.setKet_qua_dxn_thop("1");
                    }
                    String strChkTT = 
                        " AND d1.ma_nh_kb = '" + kb_chuyen + "' AND trunc(d1.ngay_gd+1) < TO_DATE ('" +ngay_dc +
                        "', 'DD/MM/YYYY') AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN " +
                        " (SELECT ngay FROM sp_ngay_nghi) AND d1.ngay_gd+1 > sp_util_pkg.fnc_get_ngay_trien_khai('"+kb_chuyen+"','"+ ma_nh +"','"+loai_tien+"')) AND APP_type='TTSP' AND send_bank='" +
                        kb_chuyen + "' AND receive_bank='" + ma_nh + "' AND loai_tien = '"+loai_tien+"')";
                    ArrayList<DChieu1VO> chkcol = (ArrayList<DChieu1VO>)dao.getCheckTThai(strChkTT, null);

                    if (chkcol.isEmpty()) {                        
                        //ManhNV-20141120***************************************
//                        DChieu1VO vo = new DChieu1VO();
//                        String chkNgayBDau = " AND a.ma_kb = '"+kb_chuyen+"'"+
//                               " AND a.ma_nh = '"+ma_nh+"'"+
//                               " AND a.loai_tien = '"+loai_tien+"'"+
//                               " AND TRUNC (a.ngay_gd+1) < sp_util_pkg.fnc_get_ngay_lam_viec_truoc(TO_DATE ('"+ngay_dc+"', 'DD/MM/YYYY'))";
//                        ArrayList<DChieu1VO> chkNgay = (ArrayList<DChieu1VO>)dao.getNgayBDauTheoSoDu(chkNgayBDau, null);
//                      
//                          String chkNgayBDau =
//                            " AND d1.ma_nh='" + ma_nh + "' and d1.ma_nh_kb ='" +
//                            kb_chuyen +
//                            "'  AND TRUNC (d1.ngay_gd+1) < TO_DATE ('" +
//                            ngay_dc + "', 'DD-MM-YYYY')" +
//                            " AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN (SELECT  ngay FROM sp_ngay_nghi)"; 
                        
//                        ArrayList<DChieu1VO> chkNgay =
//                            (ArrayList<DChieu1VO>)dao.getNgayBDau(chkNgayBDau,
//                                                                  null);
                        
//                        vo = chkNgay.get(0);                        
//                        if ("".equals(vo.getNgay()) || vo.getNgay() == null) {
                        if(dao.checkDonViMoiTK(kb_chuyen, ma_nh, loai_tien, ngay_dc)){
                          //******************************************************
                            if ("C".equals(type)) {
                                dcVO.setKet_qua_dxn_thop("0"); // trang thai khi doi chieu ngay cu
                            } else if ("XN".equals(type)) {
                                dcVO.setKet_qua_dxn_thop("1"); // trang thai khi doi chieu ngay hien tai
                            }
                        } else {
                            request.setAttribute("chuahoanthanh", "chuahoanthanh");
                            return mapping.findForward("success");
                        }
                    } else {
                        DChieu1VO vo = chkcol.get(0);
                        if (!"02".equals(vo.getTthai_dxn_thop()) && !"03".equals(vo.getTthai_dxn_thop())) {
                            request.setAttribute("chuahoanthanh", "chuahoanthanh");
                            return mapping.findForward("success");
                        } else {
                            if ("C".equals(type)) {
                                dcVO.setKet_qua_dxn_thop("0"); // trang thai khi doi chieu ngay cu
                            } else if ("XN".equals(type)) {
                                dcVO.setKet_qua_dxn_thop("1"); // trang thai khi doi chieu ngay hien tai
                            }
                        }
                    }
                    dcVO.setTtsp_id(ttsp_id);
                    dcVO.setPht_id(pht_id);
                    if ("notTTSP".equals(loai_gd)) {
                        dcVO.setTrang_thai("02");
                    }
                    if (!"Y".equals(cho_phep_qtoan_tam)) {
                      dao.updateTThaiTHop(dcVO);
                    }
                }
                if ("C".equals(type)) {
                    String strSoDu = " AND a.ma_kb='" + kb_chuyen + "' and a.ma_nh='" + frm.getReceive_bank() + "' AND a.loai_tien = '"+loai_tien+"' ";

                    XNKQDCDataVO kqdcVO = new XNKQDCDataVO();
                    kqdcVO = dao.getSoDuCOT(strSoDu, null);
                    Long chkSoDu = Long.parseLong("0");
                    if (kqdcVO != null) {
                        chkSoDu = kqdcVO.getChksodu();
                    }
//                  if (chkSoDu >= 0 || "Y".equals(cho_phep_qtoan_tam) || ngay_dc.equals("31/12/2014")) {
                    if (chkSoDu >= 0 || "Y".equals(cho_phep_qtoan_tam)) {
                        TTSPUtils ut = new TTSPUtils(conn);
                        String id_066 = ut.getMT_ID("066", "");
                        String qtoan_thu = frm.getQtoan_thu() == null ? "0" : frm.getQtoan_thu();
                        String qtoan_chi = frm.getQtoan_chi() == null ? "0" : frm.getQtoan_chi();

                        String txt_thu_tcong = frm.getTxt_thu_tcong() == null ? "0" : frm.getTxt_thu_tcong().trim().replace(",", "");
                        String txt_chi_tcong = frm.getTxt_chi_tcong() == null ? "0" : frm.getTxt_chi_tcong().trim().replace(",", "");

                        String noi_dung = frm.getTxt_noi_dung() == null ? "" : frm.getTxt_noi_dung();
                        String nhkb_chuyen = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
                        String nhkb_nhan = frm.getReceive_bank();
                        //                    }
                        DNQTVO vo066 = new DNQTVO();
                        vo066.setId(id_066);
                        vo066.setNhkb_chuyen(nhkb_chuyen);
                        vo066.setNhkb_nhan(nhkb_nhan);
                        vo066.setNgay_qtoan(ngay_dc);
                        vo066.setNguoi_tao(creator);
                        if ("Y".equals(cho_phep_qtoan_tam)) {
                            if ((frm.getTxt_thu_tcong() != null && !"".equals(frm.getTxt_thu_tcong())) || (frm.getTxt_chi_tcong() != null && !"".equals(frm.getTxt_chi_tcong()))) {
                                vo066.setLoai_qtoan("02"); // Lap moi
                                vo066.setQtoan_thu(txt_thu_tcong);
                                vo066.setQtoan_chi(txt_chi_tcong);
                            } else if (frm.getTxt_thu_tcong() == null && frm.getTxt_chi_tcong() == null) {
                                vo066.setLoai_qtoan("02"); // lap moi
                                vo066.setQtoan_thu(qtoan_thu);
                                vo066.setQtoan_chi(qtoan_chi);
                            }
                        } else if (!"Y".equals(cho_phep_qtoan_tam)) {
                            vo066.setLoai_qtoan("01"); // tu dong
                            vo066.setQtoan_thu(qtoan_thu.trim());
                            vo066.setQtoan_chi(qtoan_chi.trim());
                        }
                        vo066.setNdung_qtoan(noi_dung);
                        vo066.setKq_dc_pht(pht_id);
                        vo066.setKq_dc_ttsp(ttsp_id);
                        vo066.setKq_dxn_thop(loai_kq066);
                        vo066.setTrang_thai("01");
                        vo066.setLoai_tien(loai_tien);
                        dao.insert066(vo066);

                        ThamSoKBVO tsVO = new ThamSoKBVO();
                        ThamSoKBDAO tsDAO = new ThamSoKBDAO(conn);

                        if ("Y".equals(cho_phep_nhap_tcong) || "Y".equals(cho_phep_qtoan_tam)) {
                            tsVO.setGiatri_ts("N");
                            tsVO.setKb_id(kb_id);
                            tsDAO.update_TSKB(tsVO);
                        }
                    } else if (chkSoDu < 0 && !"Y".equals(cho_phep_qtoan_tam)) {
                        request.setAttribute("chkSoDu", "chkSoDu");
                        return mapping.findForward("success");
                    }
                }
                conn.commit();
                request.setAttribute("dung", "dung");
                request.setAttribute("update_status", "updated");
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.NT_DNQT")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String nsd_id = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();

            DChieuNgoaiTeDAO dao = new DChieuNgoaiTeDAO(conn);
            XNDCTHop1Form frm = (XNDCTHop1Form)form;
            XNKQDCDataVO vo = new XNKQDCDataVO();

            BigDecimal so_thu = new BigDecimal(0);
            BigDecimal so_chi = new BigDecimal(0);
            if(frm.getDia_txt_thu_tcong() != null && !"".equals(frm.getDia_txt_thu_tcong().trim()) )
               so_thu = new BigDecimal(frm.getDia_txt_thu_tcong());
            if(frm.getDia_txt_chi_tcong() != null && !"".equals(frm.getDia_txt_chi_tcong().trim()))
               so_chi = new BigDecimal(frm.getDia_txt_chi_tcong());
            
            String ngay_dc = frm.getNgay_dc() == null ? "" : frm.getNgay_dc();
            String ma_nh = frm.getMa_nh() == null ? "" : frm.getMa_nh();
            String ma_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String ghi_chu = frm.getNoi_dung();
            //******************************************************************
            //ManhNV-20141203
            vo.setSo_thu(so_thu);
            vo.setSo_chi(so_chi);
            //******************************************************************
            vo.setNsd_id(nsd_id);
            vo.setLoai_tien(frm.getLoai_tien());
            vo.setMa_kb(ma_kb);
            vo.setMa_nh(ma_nh);
            vo.setGhi_chu(ghi_chu);
            vo.setNgay_dc(ngay_dc);
            dao.insertTcong(vo);
            //          }
                       conn.commit();
            String loai_gd = frm.getLoai_gd();

            // call package tinh so lieu doi chieu tong hop
            String ttsp_id = frm.getTtsp_id();
            String pht_id = frm.getPht_id();

            CallableStatement cs = null;
            cs = conn.prepareCall("{call sp_doi_chieu_ngoai_te_pkg.proc_tinh_so_qtoan_ngoai_te(?,?,?,?)}");
            if ("03".equals(loai_gd)) { // loai chuyen thu
                cs.setString(1, "0000000000000000");
                cs.setString(2, pht_id);
            } else if ("02".equals(loai_gd)) { // ko co PHT
                cs.setString(1, ttsp_id);
                cs.setString(2, "0000000000000000");
            } else { // loai co PHT
                cs.setString(1, ttsp_id);
                cs.setString(2, pht_id);
            }

            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);

            cs.execute();

            String p_err_code = cs.getString(3);
            String p_err_desc = cs.getString(4);

            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }
    // cap nhat so thu cong NSD nhap

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        conn = getConnection(request);
        HttpSession session = request.getSession();

        try {
            DChieuNgoaiTeDAO dao = new DChieuNgoaiTeDAO(conn);
            XNDCTHop1Form frm = (XNDCTHop1Form)form;

            Collection colTSKB = null;
            String strJSON = null;
            String id_kb = session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();

            String loai_ts = frm.getLoai_ts();

            String strTS = " AND kb_id=" + id_kb;
            if ("TAM".equals(loai_ts)) {
                strTS += " AND ten_ts='CHO_PHEP_QUYET_TOAN_TAM' ";
            } else if ("TCONG".equals(loai_ts)) {
                strTS += " AND ten_ts='CHO_PHEP_NHAP_THU_CONG' ";
            }
            colTSKB = dao.getTso_KB(strTS, null);

            java.lang.reflect.Type cho_phep_sua =
                new TypeToken<Collection<DNQTVO>>() {
            }.getType();
            strJSON = new Gson().toJson(colTSKB, cho_phep_sua);

            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
            PrintWriter out = response.getWriter();
            out.println(strJSON.toString());
            out.flush();
            out.close();
        } catch (Exception e) {
            JSONObject jsonRes = new JSONObject();
            jsonRes.put("executeError",
                        FontUtil.unicodeEscape("Lỗi: " + e.getMessage()));

            response.setHeader("X-JSON", jsonRes.toString());
        } finally {
            conn.close();
        }
        return mapping.findForward("success");
    }


    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    //    public static final String fileName = "/rpt_doi_chieu_1";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        Connection conn = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection();
            XNDCTHop1Form f = (XNDCTHop1Form)form;
            String type = f.getType();
            // l� ng d�ng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            HttpSession session = request.getSession();
            if ("065".equals(type)) {
                String ttsp_id = f.getTtsp_id();
                String pht_id = f.getPht_id();
                DChieuNgoaiTeDAO dao = new DChieuNgoaiTeDAO(conn);
                String kq_pht = f.getKet_qua_pht();
                String kq_ttsp = f.getKet_qua_ttsp();

                //            String kb_code= session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
                DuyetKQDCVO vo = dao.getTSoDC1(ttsp_id, null);

                String ngay_dc = vo.getNgay_dc();
                String receive_bank = vo.getReceive_bank();
                String send_bank = vo.getSend_bank();
                String ten_tinh = vo.getTen_tinh();
                String ten_huyen = vo.getTen_huyen();
                String ten_nhang = vo.getTen_nhang();

                sbSubHTML.append("<input type=\"hidden\" name=\"ttsp_id\" value=\"" +
                                 ttsp_id + "\" id=\"ttsp_id\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"pht_id\" value=\"" +
                                 pht_id + "\" id=\"pht_id\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dc\" value=\"" +
                                 ngay_dc + "\" id=\"ngay_dc\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"receive_bank\" value=\"" +
                                 receive_bank +
                                 "\" id=\"receive_bank\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"send_bank\" value=\"" +
                                 send_bank + "\" id=\"send_bank\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ten_tinh\" value=\"" +
                                 ten_tinh + "\" id=\"ten_tinh\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ten_huyen\" value=\"" +
                                 ten_huyen + "\" id=\"ten_huyen\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ten_nhang\" value=\"" +
                                 ten_nhang + "\" id=\"ten_nhang\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ket_qua_pht\" value=\"" +
                                 kq_pht + "\" id=\"ket_qua_pht\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"ket_qua_ttsp\" value=\"" +
                                 kq_ttsp + "\" id=\"ket_qua_ttsp\"></input>");

                JasperPrint jasperPrint = null;
                HashMap parameterMap = new HashMap();
                ReportUtility rpUtilites = new ReportUtility();

                String fileName = "";
                
              //ThuongDT-20170221 lay them so de nghi quyet toan tam thoi va tham so du -begin               
                Long so_du_cot ;
                BigDecimal strQT_Thu = new BigDecimal(0);
                BigDecimal strQT_Chi = new BigDecimal(0);
               String whereClause = " and id in (select max(id) id FROM SP_066 where kq_dc_ttsp = ? ) and loai_qtoan = '02'";
                Vector vt = new Vector(); 
                vt.add(new Parameter(ttsp_id,true));
                QToanBuDAO qtoanDAO = new QToanBuDAO(conn);
                QToanBuVO qtoanVO = qtoanDAO.getQToanBu(whereClause, vt);
                if(qtoanVO != null){
                  strQT_Thu = qtoanVO.getQtoan_thu();
                  strQT_Chi = qtoanVO.getQtoan_chi();
                }
                parameterMap.put("p_qtoan_thu_tam", strQT_Thu);
                parameterMap.put("p_qtoan_chi_tam", strQT_Chi);
                
               
                String ngaytemp = ngay_dc.replaceAll("-", "/");                    
                TSoSDuCOTVO soDuVO = new TSoSDuCOTVO();
                soDuVO.setMa_nh(receive_bank);
                soDuVO.setMa_nh_kb(send_bank);
                soDuVO.setNgay_gd(ngaytemp);
                soDuVO.setLoai_tien(f.getLoai_tien());
                TSoSDuCOTDAO tsDAO = new TSoSDuCOTDAO(conn);
                so_du_cot = tsDAO.getSoDuCOT(soDuVO);
                // lay them so du dau ngay -begin
                    Long sodu = 0l;
                    TTSPUtils ttsp_util = new TTSPUtils(conn);
                    String ngaytruoc = ttsp_util.previousDay(ngaytemp);
                    soDuVO.setNgay_gd(ngaytruoc);
                    TSoSDuCOTVO sudu_VO = tsDAO.getSoDu(soDuVO);
                    if(sudu_VO != null)
                      sodu = sudu_VO.getSo_du();
                    parameterMap.put("P_SODU_DAUNGAY", sodu);
                    parameterMap.put("P_SODUCOT", so_du_cot!=null?so_du_cot:"0");
                // lay them so du dau ngay -end
                
                //ThuongDT-20170221 lay them so de nghi quyet toan tam thoi-end 
                
                
                if (f.getLoai_tien().equals("VND")){
                    if ("0".equals(kq_pht) && "0".equals(kq_ttsp)) {
                        fileName = "/rpt_doi_chieu_1_kd";
                    } else if (!"0".equals(kq_pht) || !"0".equals(kq_ttsp)) {
                        fileName = "/rpt_doi_chieu_1_kl";
                    }
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                }else{
                    if ("0".equals(kq_pht) && "0".equals(kq_ttsp)) {
                        fileName = "/rpt_doi_chieu_1_kd_ngoai_te";
                    } else if (!"0".equals(kq_pht) || !"0".equals(kq_ttsp)) {
                        fileName = "/rpt_doi_chieu_1_kl_ngoai_te";
                    }
                    parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
                }
                reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + fileName + ".jasper");
                parameterMap.put("p_ID_TTSP", ttsp_id);
                parameterMap.put("p_ID_PHT", pht_id);
                parameterMap.put("p_NGAY", ngay_dc);
                parameterMap.put("p_MA_NH", receive_bank);
                parameterMap.put("p_KB_HUYEN", send_bank);
                parameterMap.put("p_KB_TINH", ten_tinh);
                parameterMap.put("p_TEN_KB", ten_huyen);
                parameterMap.put("p_TEN_NH", ten_nhang);
                parameterMap.put("P_LOAI_TIEN", f.getLoai_tien());
                
                jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);

                String strTypePrintAction = request.getParameter(AppConstants.REQUEST_ACTION) == null ? "" : request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "PrintXNDCTHop1Action.do?type=065";
                String strParameter = "";
                String strPathFont = getServlet().getServletContext().getContextPath() + REPORT_DIRECTORY + strFontTimeRoman;

                rpUtilites.exportReport(jasperPrint, strTypePrintAction, response, fileName, strPathFont, strActionName, sbSubHTML.toString(), strParameter);
            } else if ("066".equals(type)) {
                String id_066 = f.getId_066();
                String loai_kq066 = f.getLoai_kq066();
                
                String creator = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString(); 
                
                sbSubHTML.append("<input type=\"hidden\" name=\"id_066\" value=\"" +
                                 id_066 + "\" id=\"id_066\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"loai_kq066\" value=\"" +
                                 loai_kq066 + "\" id=\"loai_kq066\"></input>");
                sbSubHTML.append("<input type=\"hidden\" name=\"creator\" value=\"" +
                                 creator + "\" id=\"creator\"></input>");

                JasperPrint jasperPrint = null;
                HashMap parameterMap = new HashMap();
                ReportUtility rpUtilites = new ReportUtility();

                String fileName = "/rpt_denghi_qtoan";
                reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + fileName + ".jasper");
                parameterMap.put("P_ID", id_066);
                parameterMap.put("P_TTV", creator);
                
                
                jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);

                String strTypePrintAction = request.getParameter(AppConstants.REQUEST_ACTION) == null ? "" : request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "PrintXNDCTHop1Action.do?type=066";
                String strParameter = "";
                String strPathFont = getServlet().getServletContext().getContextPath() + REPORT_DIRECTORY + strFontTimeRoman;

                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
            try {
                reportStream.close();
            } catch (Exception e) {
                throw e;
            }
        }
        return mapping.findForward("success");
    }
}
