package com.seatech.ttsp.dchieu.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DNQTVO;
import com.seatech.ttsp.dchieu.DuyetKQDCVO;
import com.seatech.ttsp.dchieu.KQDChieu1VO;
import com.seatech.ttsp.dchieu.XNKQDCDataVO;
import com.seatech.ttsp.dchieu.form.DuyetXNDCTHop1Form;
import com.seatech.ttsp.proxy.giaodien.Msg066;
import com.seatech.ttsp.proxy.pki.PKIService;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;
import com.seatech.ttsp.thamsokb.ThamSoKBDAO;
import com.seatech.ttsp.thamsokb.ThamSoKBVO;
import com.seatech.ttsp.dchieu.XNKQDCDataVO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.seatech.ttsp.dchieu.GDichTCongVO;

import java.math.BigDecimal;

import java.util.BitSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 * @modify: thuongdt
 * @date: 11/04/2017
 * @see: bat them exception doi voi truong hop ky duyet CTS
 * @find: thuongdt-20170411
 * */
public class DuyetXNDCTHop1Action extends AppAction {

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        if (!checkPermissionOnFunction(request, "DCHIEU.DUYET_DNQT")) {
            return mapping.findForward("notRight");
        }

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            Collection colTTSP = new ArrayList();
            Collection colPHT = new ArrayList();
            //20171009 thuongdt bo sung tra cuu qt thu ngay nghi
            Collection colPHT_T7 = new ArrayList();
            Collection colTHDC = new ArrayList();
            Collection colGDTCong = new ArrayList();
            DChieu1DAO dao = new DChieu1DAO(conn);
            ThamSoKBVO kbVO = new ThamSoKBVO();
            ThamSoKBDAO kbDAO = new ThamSoKBDAO(conn);
            String chkdate = StringUtil.DateToString(new Date(), "dd/MM/yyyy");

            String kb_chuyen =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            String id_kb =
                session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString();
            if (strUserInfo.indexOf(AppConstants.NSD_TTV) != -1) {
                request.setAttribute("chucdanh", strUserInfo);
            } else if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
                request.setAttribute("chucdanh", strUserInfo);
            }
            
          ThamSoHThongVO tsVO = new ThamSoHThongVO();
          ThamSoHThongDAO tsDAO = new ThamSoHThongDAO(conn);
          String strTS = " ten_ts='GIOI_HAN_NGAY_LIST_DSACH_DCHIEU'";
          tsVO = tsDAO.getThamSo(strTS, null);
          String giatri_ts = tsVO.getGiatri_ts();
            
            
          DNQTVO dnqtVO = new DNQTVO();
//          String strCHK=" AND trunc(ngay_qtoan) >= trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE -"+giatri_ts+")) AND nhkb_chuyen='" + kb_chuyen + "'";
          //20150903
          String strCHK =
              " AND trunc(ngay_qtoan) < trunc(sysdate) AND trunc(ngay_qtoan) >= trunc(sp_util_pkg.fnc_get_ngay_lam_viec_truoc (SYSDATE -" +
              giatri_ts + ")) AND nhkb_chuyen='" + kb_chuyen + "' AND (loai_tien = 'VND' OR loai_tien IS NULL)";
            
          dnqtVO =  dao.chkDNQT(strCHK, null);
          Long chk_duyet= dnqtVO.getChk_duyet();
          Long chk_qtoan= dnqtVO.getChk_qtoan();
          //          Long qtoan_tdong = dnqtVO.getQtoan_tdong();
            if(chk_duyet!=0 || chk_qtoan!=0 ){
              dnqtVO.setTrang_thai("06");
              dnqtVO.setNhkb_chuyen(kb_chuyen);
              if(dao.updateInit066(dnqtVO, strCHK) > 0 || dao.updateInit065(dnqtVO,giatri_ts) > 0){
                  conn.commit();
              }
            }

            DuyetXNDCTHop1Form DCForm = (DuyetXNDCTHop1Form)form;
            DuyetKQDCVO duyetVO = new DuyetKQDCVO();

            
            String tu_ngay = "";
            if (!"0".equals(giatri_ts) && !"".equals(giatri_ts) &&
                giatri_ts != null) {
                tu_ngay =
                        "And TRUNC (d1.ngay_gd+1) > TRUNC (SYSDATE -" + giatri_ts +
                        ")";
            }
            ArrayList<DuyetKQDCVO> colDChieu =
                (ArrayList<DuyetKQDCVO>)dao.getListXNDChieu_066(kb_chuyen,
                                                                tu_ngay, null);
            if (colDChieu.isEmpty()) {
                return mapping.findForward("success");
            }
            request.setAttribute("colDChieu", colDChieu);

            String rowSelected = request.getParameter("rowSelected");
            String task = request.getParameter("task");
            if ("huy".equalsIgnoreCase(task)) {
                rowSelected = null;
            }

            if (null == rowSelected || "".equals(rowSelected) ||
                "row_qt_0".equals(rowSelected)) {
                duyetVO = colDChieu.get(0);
                BeanUtils.copyProperties(DCForm, duyetVO);
                request.setAttribute("rowSelected", "row_qt_0");
            } else {
                request.setAttribute("rowSelected", rowSelected);
            }
            String id = DCForm.getId();

            if (id != null && !"".equals(id)) {
                TTSPUtils spUtils = new TTSPUtils(conn);
                String strNoiDungKy = spUtils.getNoiDungKy(id, "065", "Y");
                DCForm.setNoi_dung_ky(strNoiDungKy);
            }

            String receive_bank = DCForm.getReceive_bank();

            String strKB = "";
            strKB =
                    " AND a.kb_id=" + id_kb + " AND b.ma_nh='" + receive_bank + "' AND a.ma_nt='VND' AND a.loai_tk <> '01' ";//ManhNV them 20150122
            kbVO = kbDAO.getLoai_GD(strKB, null);

            String loai_gd = kbVO.getLoai_gd();


            if ("02".equals(loai_gd)) { // ko co PHT
                request.setAttribute("notPHT", "notPHT");
                request.setAttribute("pht_ttsp", "pht_ttsp");
            }
            if ("03".equals(loai_gd)) { // ko co TTSP
                request.setAttribute("notTTSP", "notTTSP");
                request.setAttribute("pht_ttsp", "pht_ttsp");
            }

            String strW = "";
            strW = "'" + id + "'";
            String strTTSP = strW;
            String strPHT = " SELECT pht_id from sp_065 where id='" + id + "'";
            // String strTHDC= strW ;
            String ngay_dc = DCForm.getNgay_dc();
            Collection col066 = null;
//            String str066 =
//                " AND nhkb_chuyen= '" + kb_chuyen + "' and nhkb_nhan='" +
//                receive_bank + "' and to_char(ngay_qtoan,'DD/MM/RRRR')='" +
//                ngay_dc + "'";
            String str066 = "";
			
			// 20170923 thuongdt bo sung lay du lieu sau khi duyet bi mat du lieu tren form begin
            if(id == null || "".equals(id)) { 
                str066 =
              " AND a.nhkb_chuyen= '" + kb_chuyen + "' and a.nhkb_nhan='" +
              receive_bank + "' and to_char(a.ngay_qtoan,'DD/MM/RRRR')='" + 
              ngay_dc + "' and a.loai_qtoan <> '03' AND a.loai_tien ='VND' ";
            }else{
              str066 =
              " AND a.nhkb_chuyen= '" + kb_chuyen + "' and a.nhkb_nhan in (select receive_bank from sp_065 where id = '"+id+"') and to_char(a.ngay_qtoan,'DD/MM/RRRR')='" +
              ngay_dc + "' and a.loai_qtoan <> '03' AND a.loai_tien ='VND' "; 
            }
			// 20170923 thuongdt bo sung lay du lieu sau khi duyet bi mat du lieu tren form end
            col066 = dao.getData066(str066, null);

            colTTSP = dao.getTTSP_PHT(strTTSP, null);
            colPHT = dao.getTTSP_PHT(strPHT, null);
            colTHDC = dao.getXNTHData(strTTSP, null);
            
          String strWhere = 
              " AND a.ma_kb= '" + kb_chuyen + "' and a.ma_nh='" +
              receive_bank + "' and to_char(a.ngay_gd,'DD/MM/RRRR')='" +
              ngay_dc + "' AND a.loai_tien = 'VND' ";
           
          //20171009 thuongdt bo sung them tim kiem du lieu ngay nghi begin
            colPHT_T7 = dao.getPHT_PS_T7( receive_bank, kb_chuyen, ngay_dc);
            String strLaiCThu = dao.getLaiChuyenThu(strWhere);
            GDichTCongVO gdTCongVO = new GDichTCongVO();
            XNKQDCDataVO xNKQDCDataVO = null;
            Iterator iter = colTHDC.iterator();
            while(iter.hasNext()){
              xNKQDCDataVO = (XNKQDCDataVO)iter.next();
              gdTCongVO.setSo_thu(xNKQDCDataVO.getTien_thu_tcong_kbnn().add(new BigDecimal("-"+strLaiCThu)));
              gdTCongVO.setSo_chi(xNKQDCDataVO.getTien_chi_tcong_kbnn());
              gdTCongVO.setLai_chuyen_thu(new BigDecimal("0")); 
            }
            
          //20171009 thuongdt bo sung them tim kiem du lieu ngay nghi end
            
            colGDTCong.add(gdTCongVO);

            String ngay_cuoi_nam = ngay_dc.substring(0, 5);
            String tthai_dxn_thop = DCForm.getTthai_dxn_thop();

//            if (!(ngay_dc).equals(chkdate) && !"31/12".equals(ngay_cuoi_nam)) {
            if (!(ngay_dc).equals(chkdate)) {
                if (col066.size() > 0) {
                    //                  request.setAttribute("colTHDC", colTHDC);
                    request.setAttribute("col066", col066);
                    //                    request.setAttribute("size", col066.size());
                    request.setAttribute("chkdate", "chkdate");
                    if ("01".equals(tthai_dxn_thop)) {
                        request.setAttribute("bthuy", "bthuy");
                    }
                } else {

                    if ("02".equals(loai_gd)) {
                        if (colTTSP.size() > 0) {
                            request.setAttribute("colTHDC", null);
                            request.setAttribute("col066", null);
                            request.setAttribute("chkdate", "chkdate");
                            if ("01".equals(tthai_dxn_thop)) {
                                request.setAttribute("bthuy", "bthuy");
                            }
                        } else {
                            request.setAttribute("colTHDC", null);
                            request.setAttribute("col066", null);
                        }
                    }
                    if ("03".equals(loai_gd)) {
                        if (colPHT.size() > 0) {
                            request.setAttribute("colTHDC", null);
                            request.setAttribute("col066", null);
                            request.setAttribute("chkdate", "chkdate");
                            if ("01".equals(tthai_dxn_thop)) {
                                request.setAttribute("bthuy", "bthuy");
                            }
                        } else {
                            request.setAttribute("colTHDC", null);
                            request.setAttribute("col066", null);
                        }
                    } else {
                        if (colTTSP.size() > 0 && colPHT.size() > 0) {
                            request.setAttribute("colTHDC", null);
                            request.setAttribute("col066", null);
                            request.setAttribute("chkdate", "chkdate");
                            if ("01".equals(tthai_dxn_thop)) {
                                request.setAttribute("bthuy", "bthuy");
                            }
                        } else {
                            request.setAttribute("colTHDC", null);
                            request.setAttribute("col066", null);
                        }
                    }
                }

            } else {
                request.setAttribute("colTHDC", colTHDC);
                request.setAttribute("col066", col066);
                request.setAttribute("size", col066.size());
            }
          //20171009 thuongdt bo sung tra cuu qt thu ngay nghi
            request.setAttribute("colPHT_T7", colPHT_T7);
            request.setAttribute("loai_gd", loai_gd);
            request.setAttribute("laicthu", strLaiCThu);
          
            
            
            request.setAttribute("colTTSP", colTTSP);
            request.setAttribute("colPHT", colPHT);
            request.setAttribute("colGDTCong", colGDTCong);
          

            saveToken(request);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {

            conn = getConnection(request);
            HttpSession session = request.getSession();
            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            String manager =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            if (strUserInfo.indexOf(AppConstants.NSD_TTV) != -1) {
                request.setAttribute("chucdanh", strUserInfo);
            } else if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
                request.setAttribute("chucdanh", strUserInfo);
            }
            if (isTokenValid(request)) {
                DChieu1DAO dao = new DChieu1DAO(conn);
                DuyetXNDCTHop1Form DCForm = (DuyetXNDCTHop1Form)form;
                KQDChieu1VO dcVO = new KQDChieu1VO();
                DNQTVO vo066 = new DNQTVO();
              String chkdate = StringUtil.DateToString(new Date(), "dd/MM/yyyy");
              String ngay_dc = DCForm.getNgay_dc();
                String id = DCForm.getId();

                String user_id =
                    session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                Msg066 sms = new Msg066(conn);
                String msg_id = "";

                String id_066 =
                    DCForm.getId_066() == null ? "" : DCForm.getId_066();

                //verify

                //  03032014
                boolean bVerifySignature = false;
                String strWSDL = getThamSoHThong("WSDL_PKI", session);
                PKIService pkiService = new PKIService(strWSDL);
                String strUserName =
                    session.getAttribute(AppConstants.APP_DOMAIN_SESSION) +
                    "\\" +
                    session.getAttribute(AppConstants.APP_USER_CODE_SESSION);
                String strSign = DCForm.getChuky_ktt();
                String strContent = DCForm.getNdung_ky_066();
                String strCertSerial = DCForm.getCertserial();
                String strAppID = getThamSoHThong("APP_ID", session);
                if (id_066 != null && !"".equals(id_066)) {
                  String[] arrResultKy = null;
                  //thuongdt-20170411 them try catch duyet CTS begin
                    try{
                      arrResultKy =
                        pkiService.VerifySignature(strUserName, strCertSerial,
                                                   strContent, strSign,
                                                   strAppID);
                    }catch(Exception ex){
                      request.setAttribute("duyetloi", ex.getMessage());  
                      //thuongdt-20170608 throw loi verify chu ky khi khong check duoc
                      throw TTSPException.createException("TTSP-3001",  "Có lỗi trong quá trình kiểm tra chữ ký số.");
                    }
                  //thuongdt-20170411 them try catch duyet CTS end
                    if (arrResultKy != null && arrResultKy.length == 2) {
                        if (arrResultKy[0].equalsIgnoreCase("VALID")) {
                            bVerifySignature = true;
                        } else if (arrResultKy[0].equalsIgnoreCase("INVALID")) {
                            throw TTSPException.createException("TTSP-3001",
                                                                arrResultKy[1]);
                        } else if (arrResultKy[0].equalsIgnoreCase("ERROR")) {
                            throw TTSPException.createException("TTSP-3001",
                                                                arrResultKy[1]);
                        }
                    }
                }
                if (id_066 == null || "".equals(id_066) || !chkdate.equals(ngay_dc)){
                    dcVO.setId(id);
                    dcVO.setManager(manager);
                    dcVO.setTthai_dxn_thop("03");
                    dcVO.setKet_qua_dxn_thop("1");
                    dao.DuyetDCTHop(dcVO);
                    conn.commit();
                    request.setAttribute("duyet", "duyet");
                }
                if (bVerifySignature) {
                    String tthai_dxn_thop = DCForm.getTthai_dxn_thop();
                    String loai_qtoan = DCForm.getLoai_qtoan();
                    dcVO.setManager(manager);
                    msg_id = sms.sendMessage(id_066, user_id);
                    dcVO.setId(id);
                    if (!"00".equals(tthai_dxn_thop) && "01".equals(loai_qtoan)) {
                        dcVO.setTthai_ttin_thop("01");
                        dcVO.setTthai_dxn_thop("02");
                    }
                    dcVO.setChuky_ktt_dxn_thop(strSign);
                    dcVO.setManager(manager);
                    vo066.setId(id_066);
                    vo066.setTrang_thai("02");
                    vo066.setMsg_id(msg_id);
                    vo066.setChu_ky(strSign);
                    vo066.setNguoi_ks(manager);
                    dao.DuyetDCTHop(dcVO);
                    dao.update066(vo066);
                    conn.commit();
                    request.setAttribute("duyet", "duyet");
                } 
            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
//            e.printStackTrace();
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward("success");
    }
    // huy tao dien

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {

            conn = getConnection(request);
            HttpSession session = request.getSession();
            String kb_chuyen =
                session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION).toString();
            String manager =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String strUserInfo =
                (String)session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION);
            String ngay_ks_xn_qtoan =
                StringUtil.DateToString(new Date(), AppConstants.DATE_TIME_FORMAT_SEND_ORDER);
            if (strUserInfo.indexOf(AppConstants.NSD_TTV) != -1) {
                request.setAttribute("chucdanh", strUserInfo);
            } else if (strUserInfo.indexOf(AppConstants.NSD_KTT) != -1) {
                request.setAttribute("chucdanh", strUserInfo);
            }
            if (isTokenValid(request)) {
                DChieu1DAO dao = new DChieu1DAO(conn);
                DuyetXNDCTHop1Form DCForm = (DuyetXNDCTHop1Form)form;
                KQDChieu1VO dcVO = new KQDChieu1VO();
                DNQTVO vo = new DNQTVO();
                String chkdate =
                    StringUtil.DateToString(new Date(), "dd/MM/yyyy");

                String id = DCForm.getId();
                String id_066 =
                    DCForm.getId_066() == null ? "" : DCForm.getId_066();
                if (id_066 == null || "".equals(id_066)) {
                    dcVO.setTthai_dxn_thop("00");
                    dcVO.setId(id);
                    dao.DuyetDCTHop(dcVO);
                } else {
                    String loai_qtoan = DCForm.getLoai_qtoan();
                    String receive_bank = DCForm.getReceive_bank();
                    String ngay_dc = DCForm.getNgay_dc();
                    Collection col066 = null;
                    String str066 =
                      " AND a.nhkb_chuyen= '" + kb_chuyen + "' and a.nhkb_nhan='" +
                      receive_bank + "' and to_char(a.ngay_qtoan,'DD/MM/RRRR')='" +
                      ngay_dc + "' and a.loai_qtoan <> '03' AND a.loai_tien ='VND' ";
                  
                    col066 = dao.getData066(str066, null);
                    if (col066.size() >= 1) {
                        if (chkdate.equals(ngay_dc) &&
                            "01".equals(loai_qtoan)) {
                            dcVO.setTthai_dxn_thop("00");
                        }
                        dcVO.setId(id);
                        dao.DuyetDCTHop(dcVO);
                    }
                    vo.setId(id_066);
                    vo.setTrang_thai("03");
                    vo.setNguoi_ks(manager);
                    vo.setNgay_ks(ngay_ks_xn_qtoan);
                    dao.update066(vo);

                }
                conn.commit();
                request.setAttribute("huy", "huy");

            }
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return executeAction(mapping, form, request, response);
        //mapping.findForward("success");
    }


}
