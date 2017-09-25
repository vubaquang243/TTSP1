package com.seatech.ttsp.user.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.chucnang.ChucNangDAO;
import com.seatech.ttsp.dmkb.DMKBacDAO;
import com.seatech.ttsp.dmkb.DMKBacVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.nhomnsd.NhomNSDDAO;
import com.seatech.ttsp.nhomnsd.NhomNSDVO;
import com.seatech.ttsp.proxy.ad.ActiveDirectory;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;
import com.seatech.ttsp.thamsokb.ThamSoKBDAO;
import com.seatech.ttsp.thamsokb.ThamSoKBVO;
import com.seatech.ttsp.tintuc.TinTucDAO;
import com.seatech.ttsp.tintuc.TinTucVO;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserVO;
import com.seatech.ttsp.user.form.UserForm;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LoginAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";
    private static String CONFIRM = "confirm";

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            try {
                conn = getConnection();
            } catch (Exception ex) {
                ActionErrors errors = new ActionErrors();
                errors.add(ActionErrors.GLOBAL_ERROR,
                           new ActionError("errors", "Loi ket noi CSDL: " +
                                           ex.getMessage()));
                saveErrors(request, errors);
                return mapping.findForward(FAILURE);
            }
            HttpSession session = request.getSession();
            if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                null) {
                return mapping.findForward(SUCCESS);
            }
            if (request.getParameter("logout") != null &&
                request.getParameter("logout").toString().equalsIgnoreCase("true")) {
                throw TTSPException.createException("TTSP-0002",
                                                    request.getParameter("ma_nsd").toString(),
                                                    request.getParameter("ip_truycap").toString());
            } else {
                //Load tham so len session
                ThamSoHThongDAO paramDAO = new ThamSoHThongDAO(conn);
                Collection appParam = paramDAO.getThamSoList(null, null);
                session.setAttribute(AppConstants.APP_THAM_SO_SESSION,
                                     appParam);


                UserForm f = (UserForm)form;

                UserDAO userDAO = new UserDAO(conn);
                UserVO userVO = null;

                String strPassword = f.getMat_khau();
                String strUsername = f.getMa_nsd().toUpperCase();
                String strDomain = f.getDomain();
                String strMACClient =
                    f.getMac_address() == null ? "" : f.getMac_address().replace(":",
                                                                                 "");

                if (strUsername == null) {
                    return mapping.findForward(FAILURE);
                } else {
                    String strWhere =
                        " UPPER (a.ma_nsd) = ? and trang_thai = '01'";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(strUsername.toUpperCase(), true));
                    userVO = userDAO.getUser(strWhere, vParam);
//                    ThamSoHThongVO tsVO = null;
                    if (userVO == null) {

                        throw TTSPException.createException("TTSP-0001",
                                                            "Ng&#432;&#7901;i s&#7917; d&#7909;ng &#273;&#227; b&#7883; KH&#211;A " +
                                                            "ho&#7863;c b&#7883; NG&#7914;NG HO&#7840;T &#272;&#7896;NG " +
                                                            "ho&#7863;c CH&#431;A &#272;&#431;&#7906;C T&#7840;O T&#192;I KHO&#7842;N " +
                                                            "trong h&#7879; th&#7889;ng TTSP. " +
                                                            "H&#227;y li&#234;n h&#7879; v&#7899;i qu&#7843;n tr&#7883; &#273;&#7875; bi&#7871;t r&#245; h&#417;n.");
                    } else {
                        //Kiem tra dia chi MAC
                        //                        if(userVO.getMac_address() == null){
                        //                          throw TTSPException.createException("TTSP-0001","NSD ch&#432;a &#273;&#259;ng k&#253; &#273;&#7883;a ch&#7881; MAC, h&#227;y &#273;&#259;ng nh&#7853;p l&#7841;i sau khi &#273;&#259;ng k&#253; &#273;&#7883;a ch&#7881; MAC v&#7899;i Qu&#7843;n tr&#7883; h&#7879; th&#7889;ng.");
                        //                        }
                        //                        if(strMACClient.indexOf("|"+userVO.getMac_address().replace("-", "")+"|") == -1){
                        //                          throw TTSPException.createException("TTSP-0001","B&#7841;n &#273;&#259;ng nh&#7853;p h&#7879; th&#7889;ng tr&#234;n m&#225;y c&#243; &#273;&#7883;a ch&#7881; MAC kh&#244;ng &#273;&#250;ng nh&#432; &#273;&#259;ng k&#253;. &#272;&#259;ng nh&#7853;p kh&#244;ng th&#224;nh c&#244;ng");
                        //                        }
                        //********************
                        if (userVO.getSession_id() == null ||
                            "".equals(userVO.getSession_id())) {
                        } else {
                            String strOldIP =
                                userVO.getIp_truycap() == null ? "" :
                                userVO.getIp_truycap().trim();
                            if (!strOldIP.equals(f.getIp_truycap() == null ?
                                                 "" :
                                                 f.getIp_truycap().trim())) {
                                if (!"confirm".equalsIgnoreCase(f.getLogin_status())) {
                                    request.setAttribute("ip_dang_truycap",
                                                         userVO.getIp_truycap());
                                    return mapping.findForward(CONFIRM);
                                }
                                f.setLogin_status("");
                            }
                        }
                        String strWSDL =
                            getThamSoHThong("WSDL_ACTIVE_DIRECTORY", session);
                        
                        
                        ActiveDirectory ad = new ActiveDirectory(strWSDL);
                        boolean bAuthen =
                            ad.authenticateUser(strDomain + "\\" + strUsername,
                                                strPassword);
                        
                        int iSoLanChoPhepDangNhapSai = Integer.parseInt(getThamSoHThong(AppConstants.PARAM_SO_LAN_DANG_NHAP_SAI,session));                        
                        long iSoLanDangNhapSai = userVO.getLogin_failure().longValue() + 1;
                        if (!bAuthen) {
                            UserVO vo = new UserVO();
                            String strThongBaoKhoa = "";
                            if (userVO.getLogin_failure().longValue() + 1 >= iSoLanChoPhepDangNhapSai) {
                                vo.setId(userVO.getId());
                                vo.setLogin_failure(new Long(userVO.getLogin_failure().longValue() +
                                                             1));
                                vo.setTrang_thai("02");
                                strThongBaoKhoa =
                                        "B&#7841;n &#273;&#227; &#273;&#259;ng nh&#7853;p sai " + iSoLanChoPhepDangNhapSai +
                                        " l&#7847;n li&#234;n ti&#7871;p. T&#224;i kho&#7843;n c&#7911;a b&#7841;n &#273;&#227; b&#7883; kh&#243;a t&#7921; &#273;&#7897;ng.";
                            } else {
                                vo.setId(userVO.getId());
                                vo.setLogin_failure(new Long(userVO.getLogin_failure().longValue() +
                                                             1));
                                if(iSoLanDangNhapSai > 3){
                                  strThongBaoKhoa =  "B&#7841;n &#273;&#227; &#273;&#259;ng nh&#7853;p sai " + 
                                                     iSoLanDangNhapSai 
                                                     +" l&#7847;n li&#234;n ti&#7871;p. T&#224;i kho&#7843;n s&#7869; b&#7883; kh&#243;a t&#7921; &#273;&#7897;ng n&#7871;u &#273;&#259;ng nh&#7853;p sai li&#234;n ti&#7871;p " + iSoLanChoPhepDangNhapSai + " l&#7847;n";
                                }
                            }
                            userDAO.update(vo);
                            conn.commit();
                            throw TTSPException.createException("TTSP-0001",
                                                                "Sai m&#227; ng&#432;&#7901;i s&#7917; d&#7909;ng ho&#7863;c sai m&#7853;t kh&#7849;u. \n" +
                                    strThongBaoKhoa);
                        }
                    }
                    Long nID = userVO.getId();
                    //Day len session
                    session.setAttribute(AppConstants.APP_USER_ID_SESSION,
                                         nID);
                    session.setAttribute(AppConstants.APP_IP_SESSION,
                                         f.getIp_truycap());
                    session.setAttribute(AppConstants.APP_OS_USER_SESSION,
                                         f.getUser_may_truycap());
                    session.setAttribute(AppConstants.APP_COMPUTER_NAME_SESSION,
                                         f.getTen_may_truycap());


                    setEndToEndMetrics(request, conn);

                    //Get danh sach chuc nang
                    ChucNangDAO cnangDAO = new ChucNangDAO(conn);
                    Collection colChucNang =
                        cnangDAO.getChucNangListByUserID(userVO.getId());
                    session.setAttribute(AppConstants.APP_CHUC_NANG_LIST_SESSION,
                                         colChucNang);
                    List lstMenu =
                        cnangDAO.getCNangMenuFromCNangList(colChucNang);
                    session.setAttribute(AppConstants.APP_MENU_LIST_SESSION,
                                         lstMenu);
                    //
                    DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
                    //Set thong tin NSD vao AppForm
                    f.setG_ma_nsd(userVO.getMa_nsd());
                    f.setG_ten_nsd(userVO.getTen_nsd());
                    f.setG_nsd_id(String.valueOf(userVO.getId()));
                    
                    // Lay Tin Tuc
//                    TinTucDAO newsDAO = new TinTucDAO(conn);
//                    TinTucVO newsVO = null;
//                    String strNews =
//                        " AND TO_CHAR (ngay_dang,'YYYYMMDD') <=TO_CHAR (SYSDATE, 'YYYYMMDD') AND trang_thai='0'" +
//                        "  AND  TO_CHAR (ngay_het_han,'YYYYMMDD') >=TO_CHAR (SYSDATE, 'YYYYMMDD')";
//                    Collection colTinTuc = newsDAO.getColTinTuc(strNews, null);
//                    session.setAttribute(AppConstants.APP_TIN_TUC_SESSION,
//                                       colTinTuc);
                    //Lay DMKB
                    strWhere = " AND a.id = ? ";
                    vParam = new Vector();
                    vParam.add(new Parameter(userVO.getKb_id(), true));
                    DMKBacVO dmkbVO = dmkbDAO.getDMKB(strWhere, vParam);

                    //                ServletContext sc = servlet.getServletContext();
                    //                if(sc.getAttribute("manhnv_ttv") != null){
                    //                  HttpSession session2 = (HttpSession)sc.getAttribute("manhnv_ttv");
                    //                  String strTest = session2.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
                    //                  session2.invalidate();
                    //                  sc.removeAttribute("manhnv_ttv");
                    //                }
                    //                sc.setAttribute(userVO.getMa_nsd(), session);

                    //Set thong tin KB vao AppForm
                    f.setG_ma_kb(dmkbVO.getMa());
                    f.setG_ten_kb(dmkbVO.getTen());
                    f.setG_kb_id(dmkbVO.getId().toString());

                    //Lay ma nh cua kb
                    String strMaNHKB = dmkbDAO.getMaKB8So(dmkbVO.getMa());
                    f.setG_ma_nhkb(strMaNHKB);

                    //Lay ID cua ma NH KB
                    DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
                    strWhere = "a.ma_nh = ?";
                    vParam = new Vector();
                    vParam.add(new Parameter(strMaNHKB, true));
                    DMNHangVO dmnhVO = dmnhDAO.getDMNH(strWhere, vParam);
                    //set thong tin ma KBNH ID vao AppForm
                    if (dmnhVO != null) {
                        f.setG_nhkb_id(String.valueOf(dmnhVO.getId()));
                        //Ma NH cua kho bac
                        session.setAttribute(AppConstants.APP_NHKB_ID_SESSION,
                                             dmnhVO.getId());
                        session.setAttribute(AppConstants.APP_NHKB_CODE_SESSION,
                                             dmnhVO.getMa_nh());
                        session.setAttribute(AppConstants.APP_NHKB_NAME_SESSION,
                                             dmnhVO.getTen());
                    } else {
                        session.setAttribute(AppConstants.APP_NHKB_ID_SESSION,
                                             "");
                        session.setAttribute(AppConstants.APP_NHKB_CODE_SESSION,
                                             "");
                        session.setAttribute(AppConstants.APP_NHKB_NAME_SESSION,
                                             "");
                    }

                    //Thong tin user
                    session.setAttribute(AppConstants.APP_USER_CODE_SESSION,
                                         userVO.getMa_nsd());
                    session.setAttribute(AppConstants.APP_USER_NAME_SESSION,
                                         userVO.getTen_nsd());
                    session.setAttribute(AppConstants.APP_DOMAIN_SESSION,
                                         userVO.getDomain().toLowerCase());
                    session.setAttribute("PASSWORD",f.getMat_khau());
                    //Thong tin kho bac
                    session.setAttribute(AppConstants.APP_KB_ID_SESSION,
                                         dmkbVO.getId());
                    session.setAttribute(AppConstants.APP_KB_CODE_SESSION,
                                         dmkbVO.getMa());
                    session.setAttribute(AppConstants.APP_KB_NAME_SESSION,
                                         dmkbVO.getTen());
                  session.setAttribute(AppConstants.APP_KB_CAP_SESSION,
                                       dmkbVO.getCap());
                    //Lay thong tin ma NH co quan he song phuong cua KB don vi (tru SGD).
                    Vector v = new Vector();
                    v.add(new Parameter(dmkbVO.getId(), true));
                    Collection colDMNHSP =
                        dmnhDAO.getListNH(" and kb_id = ?", v);
                    if (colDMNHSP.size() == 1) {
                        Iterator iter = colDMNHSP.iterator();
                        DMNHangVO dmNHSPVO = (DMNHangVO)iter.next();
                        session.setAttribute(AppConstants.APP_NH_SP_SESSION,
                                             dmNHSPVO.getMa_nh());
                    } else {
                        session.setAttribute(AppConstants.APP_NH_SP_SESSION,
                                             "");
                    }

                    //Set role vao session
                    NhomNSDDAO nhomDAO = new NhomNSDDAO(conn);
                    Collection colNhom = nhomDAO.getNhomNSDListByUserID(nID);
                    Iterator iter = colNhom.iterator();
                    NhomNSDVO nhomVO = null;
                    StringBuffer szRole = new StringBuffer();
                    int nCounter = 0;
                    while (iter.hasNext()) {
                        nhomVO = (NhomNSDVO)iter.next();
                        if (nCounter > 0)
                            szRole.append("|");
                        szRole.append(nhomVO.getLoai_nhom());
                        nCounter++;
                    }
                    session.setAttribute(AppConstants.APP_ROLE_LIST_SESSION,
                                         szRole.toString());

                    ThamSoKBDAO thamsoKBDAO = new ThamSoKBDAO(conn);
                    String whereClause =
                        " and a.kb_id = ? ";
                    Vector params = new Vector();
                    params.add(new Parameter(userVO.getKb_id(), true));
                    Collection col =
                        thamsoKBDAO.getThamSoList(whereClause, params);
                    Iterator iterTSoKB = col.iterator();
                    ThamSoKBVO thamSoKBVO = null;
                    session.setAttribute(AppConstants.APP_CHO_PHEP_CHON_DC_KHOP_DUNG_SESSION,"Y");
                    session.setAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION,"Y");
                    while (iterTSoKB.hasNext()) {
                        thamSoKBVO = (ThamSoKBVO)iterTSoKB.next();
                        if (AppConstants.APP_CHO_PHEP_CHON_DC_KHOP_DUNG_SESSION.equalsIgnoreCase(thamSoKBVO.getTen_ts())) {
                            session.setAttribute(AppConstants.APP_CHO_PHEP_CHON_DC_KHOP_DUNG_SESSION,
                                                 thamSoKBVO.getGiatri_ts());
                        } else {
                            if (AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION.equalsIgnoreCase(thamSoKBVO.getTen_ts())) {
                                session.setAttribute(AppConstants.APP_HACH_TOAN_TABMIS_THEO_NGAY_KS_NH_SESSION,
                                                     thamSoKBVO.getGiatri_ts());
                            }
                        }
                    }
                    //Set session cho dieu hanh von
                    String strTimeOut = "";
                    iter = appParam.iterator();
                    ThamSoHThongVO tsVO = null;                    
                    while (iter.hasNext()) {
                        tsVO = (ThamSoHThongVO)iter.next();
                        if (tsVO != null) {
                            if (AppConstants.APP_TK_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH_SESSION.equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              session.setAttribute(AppConstants.APP_TK_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH_SESSION,tsVO.getGiatri_ts());
                            }else if (AppConstants.APP_TK_CHI_TRA_NO_SESSION.equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              session.setAttribute(AppConstants.APP_TK_CHI_TRA_NO_SESSION,tsVO.getGiatri_ts());
                            }else if (AppConstants.APP_NDKT_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH_SESSION.equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              session.setAttribute(AppConstants.APP_NDKT_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH_SESSION,tsVO.getGiatri_ts());
                            }else if (AppConstants.APP_HAN_MUC_DIEU_HANH_VON_BIDV_SESSION.equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              session.setAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_BIDV_SESSION,tsVO.getGiatri_ts());
                            }else if (AppConstants.APP_HAN_MUC_DIEU_HANH_VON_AGR_SESSION.equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              session.setAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_AGR_SESSION,tsVO.getGiatri_ts());
                            }else if (AppConstants.APP_HAN_MUC_DIEU_HANH_VON_VCB_SESSION.equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              session.setAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_VCB_SESSION,tsVO.getGiatri_ts());
                            }else if (AppConstants.APP_HAN_MUC_DIEU_HANH_VON_ICB_SESSION.equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              session.setAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_ICB_SESSION,tsVO.getGiatri_ts());
                            }else if (AppConstants.APP_HAN_MUC_CHAN_LTT_DI_TRONG_DHV_SESSION.equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              session.setAttribute(AppConstants.APP_HAN_MUC_CHAN_LTT_DI_TRONG_DHV_SESSION,tsVO.getGiatri_ts());
                            }else if (AppConstants.APP_MA_DVQHNS_AN_NINH_SESSION.equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              session.setAttribute(AppConstants.APP_MA_DVQHNS_AN_NINH_SESSION,tsVO.getGiatri_ts());
                            }else if (AppConstants.APP_KIEM_TRA_DIEU_HANH_VON_SESSION.equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              session.setAttribute(AppConstants.APP_KIEM_TRA_DIEU_HANH_VON_SESSION,tsVO.getGiatri_ts());
                            }else if ("SYSTEM_TIMEOUT".equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              strTimeOut = tsVO.getGiatri_ts();
                            //Bo sung tham so: So ngay truy van form master
                            }else if ("SO_NGAY_TRUY_VAN_XU_LY_LTT".equalsIgnoreCase(tsVO.getTen_ts())) {                               
                              session.setAttribute("SO_NGAY_TRUY_VAN_XU_LY_LTT",tsVO.getGiatri_ts());
                            }
                        }
                    }
                    //Thiet lap timeout                    
                    int iTimeOut = 0;
                    try {
                        iTimeOut = Integer.parseInt(strTimeOut);
                    } catch (Exception ex) {
                        iTimeOut = 30;
                    }
                    session.setMaxInactiveInterval(iTimeOut * 60);
                    //---------------

                    //Luu thong tin dang nhap vao CSDL
                    userVO = new UserVO();
                    userVO.setIp_truycap(f.getIp_truycap());
                    userVO.setSession_id(session.getId());
                    userVO.setTgian_truycap(new Date());
                    userVO.setUser_may_truycap(f.getUser_may_truycap());
                    userVO.setTen_may_truycap(f.getTen_may_truycap());
                    userVO.setId(nID);
                    userVO.setLogin_failure(new Long(0));

                    userDAO.update(userVO);

                    saveVisitLog(conn, session, "SYS.LOGIN", "");
                    
                    conn.commit();
                }
            }
        }catch(Exception ex){
          throw ex;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

}
