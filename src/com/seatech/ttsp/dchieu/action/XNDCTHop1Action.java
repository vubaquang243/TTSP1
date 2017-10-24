package com.seatech.ttsp.dchieu.action;


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
import com.seatech.ttsp.dchieu.DChieu1DAO;
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

import java.util.Iterator;
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
   * @modify: HungBM
   * @modify date: 11/2016   
   * @see: sua them thong tin ddien xac nhan
   * @Track: 201611
   *
   * @modify: ThuongDT
   * @modify date: 14/11/2016   
   * @see: lay so du thoi diem COT len bao cao su ly trong ham printAction
   * @track: 20161114
   * */
   
 /*
 * @modify: ThuongDT
 * @modify date:13/02/2017
 * @see: lay quyet toan thu, chi tam bo sung them vao bao cao
 * @Find: ThuongDT-20170213
 */

 /*
 * @modify: ThuongDT
 * @modify date:15/02/2017
 * @see: lay them so du dau ngay bo sung them vao bao cao
 * @Find: ThuongDT-20170215
 */

 /*
 * @modify: ThuongDT
 * @modify date:05/04/2017
 * @see: them dieu kiem kiem tra doi voi ngay hom truoc chua ket thuc doi chieu
 * @Find: ThuongDT-20170405
 */
 /*
 * @modify: HungBM
 * @modify date:05/05/2017
 * @see: Kiem tra dieu kien truoc khi cho phep tao dien 066
 * @Find: HungBM-20170505
 */ 
 
public class XNDCTHop1Action extends AppAction {

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.DNQT")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
            String sysdate = StringUtil.DateToString(new Date(), "dd/MM/yyyy");
            HttpSession session = request.getSession();
            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
                return mapping.findForward("notRight");
            }

            DChieu1DAO dao = new DChieu1DAO(conn);
            DuyetKQDCVO duyetVO = new DuyetKQDCVO();
            XNDCTHop1Form thForm = (XNDCTHop1Form)form;
            Collection colTTSP = new ArrayList();
            Collection colPHT = new ArrayList();
            Collection colPHT_T7 = new ArrayList();
            Collection colTHDC = new ArrayList();
            Collection colGDTCong = new ArrayList();
            Collection col066 = new ArrayList();
            DNQTVO dnqtVO = new DNQTVO();
            ThamSoKBVO kbVO = new ThamSoKBVO();
            ThamSoKBDAO kbDAO = new ThamSoKBDAO(conn);
            String kb_chuyen =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String nhkb_id =
                session.getAttribute(AppConstants.APP_NHKB_ID_SESSION).toString();
            String id_kb =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            //thuongdt-20170503 bo sung them kiem tra cho phep quyet toan tam
            String cho_phep_qtoan_tam = thForm.getCho_phep_qtoan_tam();
            
            ThamSoHThongVO tsVO = new ThamSoHThongVO();
            ThamSoHThongDAO tsDAO = new ThamSoHThongDAO(conn);
            String strTS = " ten_ts='GIOI_HAN_NGAY_LIST_DSACH_DCHIEU'";
            tsVO = tsDAO.getThamSo(strTS, null);
            String giatri_ts = tsVO.getGiatri_ts();
            String strCHK =
                " AND trunc(ngay_qtoan) < trunc(sysdate) AND trunc(ngay_qtoan) >= trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE -" +
                giatri_ts + ")) AND nhkb_chuyen='" + kb_chuyen + "' AND (loai_tien = 'VND' OR loai_tien IS NULL)";
            dnqtVO = dao.chkDNQT(strCHK, null);
            Long chk_duyet = dnqtVO.getChk_duyet();
            Long chk_qtoan = dnqtVO.getChk_qtoan();
            if (chk_duyet != 0 || chk_qtoan != 0) {
                dnqtVO.setTrang_thai("06");
                dnqtVO.setNhkb_chuyen(kb_chuyen);

                if (dao.updateInit066(dnqtVO, strCHK) > 0 ||
                    dao.updateInit065(dnqtVO, giatri_ts) > 0) {
                    conn.commit();
                }
            }

            String tu_ngay = "";
            if (!"0".equals(giatri_ts) && !"".equals(giatri_ts) &&
                giatri_ts != null) {
//Manhnv-20150423***************************************************************                
//                tu_ngay =
//                        "And TRUNC (d1.ngay_gd+1) > TRUNC (SYSDATE -" + giatri_ts +
//                        ")";
//******************************************************************************
            }

            ArrayList<DuyetKQDCVO> colDChieu =
                (ArrayList<DuyetKQDCVO>)dao.getXNDCTHop(kb_chuyen, nhkb_id,
                                                        tu_ngay, null);
            if (colDChieu.isEmpty()) {
                saveToken(request);
                return mapping.findForward("success");
            }
            request.setAttribute("colDChieu", colDChieu);

            String rowSelected = request.getParameter("rowSelected");
            if (null == rowSelected || "".equals(rowSelected) ||
                "row_qt_0".equals(rowSelected)) {
                duyetVO = colDChieu.get(0);
                BeanUtils.copyProperties(thForm, duyetVO);
                request.setAttribute("rowSelected", "row_qt_0");
            } else {

                request.setAttribute("rowSelected", rowSelected);
            }

            String receive_bank = thForm.getReceive_bank();
            
            String ngay_dc = thForm.getNgay_dc();
            String tthai_dxn_thop = thForm.getTthai_dxn_thop();

            String strKB = "";
            strKB =
                    " and e.ten_ts='CHO_PHEP_QUYET_TOAN_TAM' AND a.kb_id=" + id_kb +
                    " AND b.ma_nh='" + receive_bank + "' AND a.ma_nt='VND' AND a.loai_tk <> '01' "+//ManhNV them 20150122
                    " and a.TRANG_THAI = '01' ";//ThuongDT-20170417 them trang thai hoat dong
            kbVO = kbDAO.getLoai_GD(strKB, null);

            String loai_gd = kbVO.getLoai_gd();
            String qtoan_ko_dchieu = kbVO.getGiatri_ts();
           
            // call package tinh so lieu doi chieu tong hop
            String ttsp_id = thForm.getTtsp_id();
            String pht_id = thForm.getPht_id();
            if ("00".equals(tthai_dxn_thop) || "".equals(tthai_dxn_thop) ||
                tthai_dxn_thop == null) {
                CallableStatement cs = null;
                cs = conn.prepareCall("{call sp_doi_chieu_pkg.proc_tinh_so_quyet_toan(?,?,?,?)}");
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
            String strW = "";
            strW = "select max(id) from sp_065 a where a.receive_bank = '" + receive_bank +
                   "' and send_bank='" + kb_chuyen +
                   "' and to_char(a.ngay_dc,'DD/MM/RRRR') = '" + ngay_dc + "'";
            String strTTSP = strW + " AND app_type='TTSP'";
            String strPHT = strW + " AND app_type='PHT'";

//            String str066 =
//                " AND nhkb_chuyen= '" + kb_chuyen + "' and nhkb_nhan='" +
//                receive_bank + "' and to_char(ngay_qtoan,'DD/MM/RRRR')='" +
//                ngay_dc + "'";

            String str066 = " AND a.nhkb_chuyen= '" + kb_chuyen + "' and a.nhkb_nhan='" +
                          receive_bank + "' and to_char(a.ngay_qtoan,'DD/MM/RRRR')='" +
                          ngay_dc + "' and a.loai_qtoan <> '03' AND a.loai_tien ='VND'";

            String strGDTCong = 
                " AND a.ma_kb= '" + kb_chuyen + "' and a.ma_nh='" +
                receive_bank + "' and to_char(a.ngay_gd,'DD/MM/RRRR')='" +
                ngay_dc + "' AND a.loai_tien = 'VND' ";
             if(ttsp_id != null && !"".equals(ttsp_id))
              colTTSP = dao.get065ByID(ttsp_id); 
            if(pht_id != null  && !"".equals(pht_id)){
              //20171009 thuongdt bo sung them tim kiem du lieu ngay nghi
              colPHT = dao.get065ByID(pht_id);
              //20171009 thuongdt bo sung them tim kiem du lieu ngay nghi
              colPHT_T7 = dao.getPHT_PS_T7( receive_bank, kb_chuyen, ngay_dc);
            }          
              
         
            col066 = dao.getData066(str066, null);
            colGDTCong = dao.getSoTCong(strGDTCong, null);
			
            request.setAttribute("colGDTCong", colGDTCong);
			//ThuongDT-20161114 -------- BEGIN -------Check colTTSP co gia tri kko
            if(colTTSP.size()>0){
              if ((ngay_dc).equals(sysdate)) {
                colTHDC = dao.getXNTHData_PS_T7(strTTSP, null,receive_bank, kb_chuyen, ngay_dc);
               // colTHDC = dao.getXNTHData(strTTSP, null);
              }
              request.setAttribute("colTTSP", colTTSP);              
            }else{
              if ((ngay_dc).equals(sysdate)) {                  
                colTHDC = dao.getDeNghiQuyetToanTHData( kb_chuyen,receive_bank,ngay_dc);
              }
              colTTSP = dao.getDeNghiQuyetToan( kb_chuyen,receive_bank,ngay_dc);
              request.setAttribute("colTTSP", colTTSP);
                
            }
			//ThuongDT-20161114--------- END--------------
            request.setAttribute("colPHT", colPHT);
            request.setAttribute("colPHT_T7", colPHT_T7);
            request.setAttribute("qtoan_ko_dchieu", qtoan_ko_dchieu);
//            String sysdate = StringUtil.DateToString(new Date(), "dd/MM/yyyy");
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
                //            saveToken(request);
                //              return mapping.findForward("success");
            }

            if ("03".equals(loai_gd)) {
                colDChieu =
                        (ArrayList<DuyetKQDCVO>)dao.getXNDCTHop(kb_chuyen, nhkb_id,
                                                                tu_ngay, null);
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
        if (!checkPermissionOnFunction(request, "DCHIEU.DNQT")) {
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
                DChieu1DAO dao = new DChieu1DAO(conn);
                XNDCTHop1Form frm = (XNDCTHop1Form)form;
                KQDChieu1VO dcVO = new KQDChieu1VO();


                String ngay_dc = frm.getNgay_dc();
//                String sysdate = StringUtil.DateToString(new Date(), "dd/MM/yyyy");

               
                String ttsp_id = request.getParameter("ttsp_id");
                String pht_id = request.getParameter("pht_id");
                String type = frm.getType();
                String loai_gd = frm.getLoai_gd();
                String kb_chuyen = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
                String ma_nh = frm.getReceive_bank();
                String loai_kq066 = frm.getLoai_kq066();
//                String tthai_dxn_thop = frm.getTthai_dxn_thop();
                String cho_phep_nhap_tcong = frm.getCho_phep_nhap_tcong();
                String cho_phep_qtoan_tam = frm.getCho_phep_qtoan_tam();

                String strChkSoDu =
                    " AND ma_kb='" + kb_chuyen + "' and ma_nh='" + ma_nh +
                    "' and to_char(ngay_gd,'DD/MM/YYYY')='" + ngay_dc + "' AND a.loai_tien = 'VND' ";

                ArrayList<DNQTVO> colChkSoDu = (ArrayList<DNQTVO>)dao.chkSoDu(strChkSoDu, null);
                if (colChkSoDu.isEmpty() && !"Y".equals(cho_phep_qtoan_tam)) {                    
                    request.setAttribute("qtth", "qtth");
                    return mapping.findForward("success");
                }
//20150128-manhnv***************************************************************
//                if ("Y".equals(cho_phep_qtoan_tam)) {
//                    if (sysdate.equals(ngay_dc) && (tthai_dxn_thop == null || "".equals(tthai_dxn_thop) || "00".equals(tthai_dxn_thop))) {
//                        dcVO.setTthai_dxn_thop("00");
//                    }else {
//                        dcVO.setTthai_dxn_thop("01");
//                    }
//                } else if (!"Y".equals(cho_phep_qtoan_tam)) {
                    dcVO.setTthai_dxn_thop("01");
//                }
//20150128-manhnv***************************************************************
 //20170926 - thuongdt bat them kiem tra lai so QThu chi bang 0 begin
               String strQToanThu = frm.getQtoan_thu() == null ? "0" : frm.getQtoan_thu();
               String strQtoanChi = frm.getQtoan_chi() == null ? "0" : frm.getQtoan_chi(); 
              BigDecimal fQTThu = new BigDecimal(0);
              BigDecimal fQTChi = new BigDecimal(0);
              BigDecimal fTongThu = new BigDecimal(0);
              BigDecimal fTongChi = new BigDecimal(0);
              if ("Y".equals(cho_phep_nhap_tcong) || "Y".equals(cho_phep_qtoan_tam)){
              }else{  
               if("0".equals(strQToanThu) || "0".equals(strQtoanChi)){ 
                 Collection colTHDC = dao.getXNTHData("'"+ttsp_id+"'", null);
                    String receive_bank = frm.getReceive_bank();
                String str066 = " AND a.nhkb_chuyen= '" + kb_chuyen + "' and a.nhkb_nhan='" +
                                  receive_bank + "' and to_char(a.ngay_qtoan,'DD/MM/RRRR')='" +
                                  ngay_dc + "' and a.loai_qtoan <> '03' AND a.trang_thai <> '03' AND a.loai_tien ='VND'";
                 Collection col066 = dao.getData066(str066, null);
                 Iterator itr066  = col066.iterator();
                 while(itr066.hasNext()){
                  XNKQDCDataVO xnvo = (XNKQDCDataVO)itr066.next();   
                  fQTThu = fQTThu.add(new BigDecimal(xnvo.getQtoan_thu()));
                  fQTChi = fQTChi.add(new BigDecimal(xnvo.getQtoan_chi()));;   
                }
            //lay so lieu 066 doi voi truong hop co quyet toan tam trong ngay end
                 Iterator itr = colTHDC.iterator();
                 if(itr.hasNext()){
                    XNKQDCDataVO xn = (XNKQDCDataVO)itr.next(); 
                      fTongThu = xn.getKet_chuyen_thu().subtract(fQTThu);
                      fTongChi = xn.getKet_chuyen_chi().subtract(fQTChi); 
                    if(fTongThu.compareTo(new BigDecimal(strQToanThu)) != 0  || fTongChi.compareTo(new BigDecimal(strQtoanChi)) != 0 ) 
                      {
                      request.setAttribute("dchieu_clech_qtoan", "dchieu_clech_qtoan");
                      return mapping.findForward("success"); 
                      }
                    }   
                  }
                }
              //thuongdt-20170407 check lai truong hop de lot chenh lech nhưng van cho tao dien begin
              DChieu1VO dcKQVO = new DChieu1VO();
              String strWhereClause = " and id = '"+ttsp_id+"' ";      
              Collection clkq = dao.getCheckKQDC(strWhereClause,null);
              Iterator itr = clkq.iterator();
              while(itr.hasNext()) {
                dcKQVO = (DChieu1VO)itr.next();                
              }
              if(!"0".equals(dcKQVO.getKet_qua()) &&  !"Y".equals(cho_phep_qtoan_tam)){
                request.setAttribute("dchieu_clech", "dchieu_clech");
                return mapping.findForward("success"); 
              }                    
              //thuongdt-20170407 check lai truong hop de lot chenh lech nhưng van cho tao dien end
                    
                if (ttsp_id != null && !"".equals(ttsp_id)) {
                    if ("C".equals(type)) {
                        dcVO.setKet_qua_dxn_thop("0"); // trang thai khi doi chieu ngay cu
                    } else if ("XN".equals(type)) {
                        dcVO.setKet_qua_dxn_thop("1");
                    }
                    String strChkTT =
                        " AND d1.ma_nh_kb = '" + kb_chuyen + "' AND trunc(d1.ngay_gd+1) < TO_DATE ('" +
                        ngay_dc +
                        "', 'DD/MM/YYYY') AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN " +
                        " (SELECT ngay FROM sp_ngay_nghi)) AND APP_type='TTSP' AND send_bank='" +
                        kb_chuyen + "' AND receive_bank='" + ma_nh + "')";
                    ArrayList<DChieu1VO> chkcol = (ArrayList<DChieu1VO>)dao.getCheckTThai(strChkTT, null);
                  //manhnv-20150423***********************************************
                    if (chkcol.isEmpty()) {
                        DChieu1VO vo = new DChieu1VO();
//                        String chkNgayBDau =
//                            " AND d1.ma_nh='" + ma_nh + "' and d1.ma_nh_kb ='" + kb_chuyen +
//                            "'  AND TRUNC (d1.ngay_gd+1) < TO_DATE ('" + ngay_dc + "', 'DD-MM-YYYY')" +
//                            " AND TO_CHAR (d1.ngay_gd+1, 'YYYYMMDD') NOT IN (SELECT  ngay FROM sp_ngay_nghi)";
                        
                        ArrayList<DChieu1VO> chkNgay = (ArrayList<DChieu1VO>)dao.getNgayBDau(kb_chuyen, ma_nh, ngay_dc);
                    //manhnv-20150423***********************************************
                        vo = chkNgay.get(0);
                        if ("".equals(vo.getNgay()) || vo.getNgay() == null) {
                            if ("C".equals(type)) {
                                dcVO.setKet_qua_dxn_thop("0"); // trang thai khi doi chieu ngay cu
                            } else if ("XN".equals(type)) {
                                dcVO.setKet_qua_dxn_thop("1"); // trang thai khi doi chieu ngay hien tai
                            }
                        } else {
                            request.setAttribute("chuahoanthanh","chuahoanthanh");
                            return mapping.findForward("success");
                        }
                        
                        
                    } else {
                        DChieu1VO vo = chkcol.get(0); 
                        //ThuongDT-20170405: them dieu kiem kiem tra doi voi ngay hom truoc chua ket thuc doi chieu begin
                        if("1".equals(vo.getKet_qua()) &&  !"Y".equals(cho_phep_qtoan_tam)){
                          request.setAttribute("chuaketthuc","chuaketthuc");
                          return mapping.findForward("success");
                        }
                      //ThuongDT-20170405: them dieu kiem kiem tra doi voi ngay hom truoc chua ket thuc doi chieu end
                        if (!"02".equals(vo.getTthai_dxn_thop()) && !"03".equals(vo.getTthai_dxn_thop())) {
                            request.setAttribute("chuahoanthanh","chuahoanthanh");
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
                    String strSoDu =
                        " AND a.ma_kb='" + kb_chuyen + "' and a.ma_nh='" +
                        frm.getReceive_bank() + "'";

                    XNKQDCDataVO kqdcVO = new XNKQDCDataVO();
                    kqdcVO = dao.getSoDuCOT(strSoDu, null);
                    Long chkSoDu = Long.parseLong("0");
                    if (kqdcVO != null) {
                        chkSoDu = kqdcVO.getChksodu();
                    }
//                    if (chkSoDu >= 0 || "Y".equals(cho_phep_qtoan_tam) ||
//                        ngay_dc.equals("31/12/2014")) {
                    if (chkSoDu >= 0 || "Y".equals(cho_phep_qtoan_tam)) {
                        TTSPUtils ut = new TTSPUtils(conn);
                        String id_066 = ut.getMT_ID("066", "");
                        String qtoan_thu = frm.getQtoan_thu() == null ? "0" : frm.getQtoan_thu();
                        String qtoan_chi = frm.getQtoan_chi() == null ? "0" : frm.getQtoan_chi();
                        String txt_thu_tcong = frm.getTxt_thu_tcong() == null ? "0" : frm.getTxt_thu_tcong();
                        String txt_chi_tcong = frm.getTxt_chi_tcong() == null ? "0" : frm.getTxt_chi_tcong();
                        String noi_dung = frm.getTxt_noi_dung() == null ? "" : frm.getTxt_noi_dung();
                        
                        Long.parseLong(txt_chi_tcong.trim().replace(".", ""));
                        
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
                            if ((frm.getTxt_thu_tcong() != null && !"".equals(frm.getTxt_thu_tcong())) || (frm.getTxt_chi_tcong() != null &&
                                 !"".equals(frm.getTxt_chi_tcong()))) {
                                vo066.setLoai_qtoan("02"); // Lap moi
                                vo066.setQtoan_thu(txt_thu_tcong.trim().replace(".", ""));
                                vo066.setQtoan_chi(txt_chi_tcong.trim().replace(".", ""));
                            } else if (frm.getTxt_thu_tcong() == null && frm.getTxt_chi_tcong() == null) {
                                vo066.setLoai_qtoan("02"); // lap moi
                                vo066.setQtoan_thu(qtoan_thu.trim().replace(".", ""));
                                vo066.setQtoan_chi(qtoan_chi.trim().replace(".", ""));
                            }
                        } else if (!"Y".equals(cho_phep_qtoan_tam)) {
                            vo066.setLoai_qtoan("01"); // tu dong
                            vo066.setQtoan_thu(qtoan_thu.trim().replace(".", ""));
                            vo066.setQtoan_chi(qtoan_chi.trim().replace(".", ""));
                        }
                        vo066.setNdung_qtoan(noi_dung);
                        vo066.setKq_dc_pht(pht_id);
                        vo066.setKq_dc_ttsp(ttsp_id);
                        vo066.setKq_dc_ttsp(ttsp_id);
                        vo066.setKq_dxn_thop(loai_kq066);
                        vo066.setTrang_thai("01");
                        vo066.setLoai_tien("VND");
                        //HungBM-20170505 - BEGIN
                        //Kiem tra xem voi ID cua 065 tuong ung, da co ton tai 066 voi loai_qtoan = 01 hay chua
                        boolean ton_tai_ban_ghi = dao.checkDien066(ttsp_id);
                        //Neu so ban ghi  = 0 -> cho phep tao dien 066
                        if (!ton_tai_ban_ghi){
                          dao.insert066(vo066);
                        }else {
                          request.setAttribute("ton_tai_066", "ton_tai_066");
                        }
                        //HungBM-20170505 - END

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
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.DNQT")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String nsd_id = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();

            DChieu1DAO dao = new DChieu1DAO(conn);
            XNDCTHop1Form frm = (XNDCTHop1Form)form;
            XNKQDCDataVO vo = new XNKQDCDataVO();

            String so_thu = frm.getDia_txt_thu_tcong() == null || "".equals(frm.getDia_txt_thu_tcong().trim()) ? "0" : frm.getDia_txt_thu_tcong().trim();
            String so_chi = frm.getDia_txt_chi_tcong() == null || "".equals(frm.getDia_txt_chi_tcong().trim()) ? "0" : frm.getDia_txt_chi_tcong().trim();
            String ngay_dc = frm.getNgay_dc() == null ? "" : frm.getNgay_dc();
            String ma_nh = frm.getMa_nh() == null ? "" : frm.getMa_nh();
            String ma_kb = session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String ghi_chu = frm.getNoi_dung();
            vo.setSo_thu(new BigDecimal(so_thu));
            vo.setSo_chi(new BigDecimal(so_chi));
            vo.setNsd_id(nsd_id);

            vo.setMa_kb(ma_kb);
            vo.setMa_nh(ma_nh);
            vo.setGhi_chu(ghi_chu);
            vo.setNgay_dc(ngay_dc);
            dao.insertTcong(vo);

            conn.commit();
            String loai_gd = frm.getLoai_gd();

            // call package tinh so lieu doi chieu tong hop
            String ttsp_id = frm.getTtsp_id();
            String pht_id = frm.getPht_id();

            CallableStatement cs = null;
            cs = conn.prepareCall("{call sp_doi_chieu_pkg.proc_tinh_so_quyet_toan(?,?,?,?)}");
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
            DChieu1DAO dao = new DChieu1DAO(conn);
            XNDCTHop1Form frm = (XNDCTHop1Form)form;
            //            XNKQDCDataVO dcVO = new XNKQDCDataVO();
            Collection colTSKB = null;
            String strJSON = null;
            String id_kb =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();


            //            String ttsp_id = frm.getTtsp_id();
            String loai_ts = frm.getLoai_ts();

            String strTS = " AND kb_id=" + id_kb;
            if ("TAM".equals(loai_ts)) {
                strTS += " AND ten_ts='CHO_PHEP_QUYET_TOAN_TAM' ";
            } else if ("TCONG".equals(loai_ts)) {
                strTS += " AND ten_ts='CHO_PHEP_NHAP_THU_CONG' ";
            }
            colTSKB = dao.getTso_KB(strTS, null);

            //            String cho_phep_sua =dcVO.getCho_phep_sua();

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
			//ThuongDT-20161125-khoi tao DAO -------------BEGIN---------------------
                TSoSDuCOTDAO tsDAO = new TSoSDuCOTDAO(conn);
			//ThuongDT-20161125-khoi tao DAO -------------END-----------------------
                String ttsp_id = f.getTtsp_id();
                String pht_id = f.getPht_id();
                DChieu1DAO dao = new DChieu1DAO(conn);
                String kq_pht = f.getKet_qua_pht();
                String kq_ttsp = f.getKet_qua_ttsp();
              
                // String kb_code= session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
                DuyetKQDCVO vo = dao.getTSoDC1(ttsp_id, null);

                String ngay_dc = vo.getNgay_dc();
				//ThuongDT-20161125 -------------BEGIN---------------------
                ngay_dc = ngay_dc.replace("-", "/");
				//ThuongDT-20161125 -------------END---------------------
                String receive_bank = vo.getReceive_bank();
                String send_bank = vo.getSend_bank();
                String ten_tinh = vo.getTen_tinh();
                String ten_huyen = vo.getTen_huyen();
                String ten_nhang = vo.getTen_nhang();
				//ThuongDT-20161125-lay so du COT -------------BEGIN
                TSoSDuCOTVO soDuVO = new TSoSDuCOTVO();
                soDuVO.setMa_nh(receive_bank);
                soDuVO.setMa_nh_kb(send_bank);
                soDuVO.setNgay_gd(ngay_dc);
                soDuVO.setLoai_tien("VND");
                
                Long so_du_cot = tsDAO.getSoDuCOT(soDuVO);
				//ThuongDT-20161125-lay so du COT -------------END
                
				//ThuongDT-20170215 lay them so du dau ngay -begin
				    Long sodu = 0l;
				    TTSPUtils ttsp_util = new TTSPUtils(conn);
				    String ngaytruoc = ttsp_util.previousDay(ngay_dc);
				    soDuVO.setNgay_gd(ngaytruoc);
				    TSoSDuCOTVO sudu_VO = tsDAO.getSoDu(soDuVO);
				    if(sudu_VO != null)
				      sodu = sudu_VO.getSo_du();
				    
				//ThuongDT-20170215 lay them so du dau ngay-END
                
                
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
                if ("0".equals(kq_pht) && "0".equals(kq_ttsp)) {
                    fileName = "/rpt_doi_chieu_1_kd112016";//25/11/2016 ThuongDT sua bao cao dap ung theo mau moi
                } else if (!"0".equals(kq_pht) || !"0".equals(kq_ttsp)) {
                    fileName = "/rpt_doi_chieu_1_kl";
                }
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
																								
																								
              //ThuongDT-20170213 lay them so de nghi quyet toan tam thoi-begin 
              BigDecimal strQT_Thu = new BigDecimal(0);
              BigDecimal strQT_Chi = new BigDecimal(0);
              String whereClause = " and id in (select max(id) id FROM SP_066 where kq_dc_ttsp = ? )";
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
              //ThuongDT-20170213 lay them so de nghi quyet toan tam thoi- end																				
																								
                parameterMap.put("p_ID_TTSP", ttsp_id);
                parameterMap.put("p_ID_PHT", pht_id);
                parameterMap.put("p_NGAY", ngay_dc);
                parameterMap.put("p_MA_NH", receive_bank);
                parameterMap.put("p_KB_HUYEN", send_bank);
                parameterMap.put("p_KB_TINH", ten_tinh);
                parameterMap.put("p_TEN_KB", ten_huyen);
                parameterMap.put("p_TEN_NH", ten_nhang);
				//ThuongDT-20161125-Sua dinh dang tien -------------BEGIN------------ 
                parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                parameterMap.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_VND);
                parameterMap.put("P_SODUCOT", so_du_cot!=null?so_du_cot:"0");
                parameterMap.put("P_SODU_DAUNGAY", sodu);
				//ThuongDT-20161125-Sua dinh dang tien -------------END---------------------
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "PrintXNDCTHop1Action.do?type=065";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    REPORT_DIRECTORY + strFontTimeRoman;

                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
            } else if ("066".equals(type)) {
			//ThuongDT-201611-lay loai tien-------------BEGIN---------------------
              String loaitien = (String)request.getParameter("loaitien");
			//ThuongDT-201611-lay loai tien -------------END---------------------
                String id_066 = f.getId_066();
                String loai_kq066 = f.getLoai_kq066();

                String creator =
                    session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();


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
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
                parameterMap.put("P_ID", id_066);
                parameterMap.put("P_TTV", creator);
				//ThuongDT-20161125 -------------BEGIN---------------------
				//Check loai tien 
                if("VND".equals(loaitien)){
                  parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                  parameterMap.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_VND);
                }                
                else{
                  //parameterMap.put("REPORT_LOCALE", new java.util.Locale("en", "US"));
                  parameterMap.put("REPORT_LOCALE", new java.util.Locale("vi", "VI"));
                  parameterMap.put("p_CURRENCY_FRMT_PARTERN", AppConstants.FORMAT_CURRENTCEY_PATERN_NT); //thuongdt-20170518 sua format tien
                }                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);
				//ThuongDT-20161125 -------------END---------------------
                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "PrintXNDCTHop1Action.do?type=066";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    REPORT_DIRECTORY + strFontTimeRoman;

                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                reportStream.close();
            } catch (Exception e) {
                //                e.printStackTrace();
            }
            close(conn);
        }

        return mapping.findForward("success");
    }
}
