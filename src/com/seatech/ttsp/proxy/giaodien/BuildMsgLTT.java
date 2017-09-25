package com.seatech.ttsp.proxy.giaodien;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.utils.DateUtils;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmcapns.DMCapNSDAO;
import com.seatech.ttsp.dmcapns.DMCapNSVO;
import com.seatech.ttsp.dmdvsdns.DMDonViSdnsDAO;
import com.seatech.ttsp.dmdvsdns.DMDonViSdnsVO;
import com.seatech.ttsp.dmndkt.DMNDKTeDAO;
import com.seatech.ttsp.dmndkt.DMNDKTeVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangHOVO;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanDAO;
import com.seatech.ttsp.dmthamsohachtoan.DmTSoHToanVO;
import com.seatech.ttsp.dmtkkt.DMTKKTDAO;
import com.seatech.ttsp.dmtkkt.DMTKKTVO;
import com.seatech.ttsp.ltt.LTTCTietDAO;
import com.seatech.ttsp.ltt.LTTCTietVO;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.tknhkb.TKNHKBacDAO;
import com.seatech.ttsp.tknhkb.TKNHKBacVO;

import java.math.BigDecimal;

import java.sql.Connection;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpSession;


public class BuildMsgLTT {
    private Connection conn = null;
    private String APP_SEND_CODE = AppConstants.APP_SEND_CODE;
    private String APP_SEND_NAME = AppConstants.APP_SEND_NAME;
    private String APP_RECIEVE_CODE = AppConstants.TABMIS_CODE;
    private String APP_RECIEVE_NAME = AppConstants.TABMIS_NAME;
    private String TRAN_CODE = "103";
    private String CATEGORY = "TTSP";//"LNH";

    private String GL_SOURCE_MODULE = AppConstants.GL_SOURCE_MODULE;

 
    private String SEND_DETAIL_LTT = "N";

    private String HACH_TOAN_TK_TIEN_GUI = "HACH_TOAN_DUNG_TK_TGUI_TAI_KB";
    private String HACH_TOAN_TK_CHO_XLY_CO = "HACH_TOAN_CHO_XLY_LTT_CO";
    /**
     * - ManhNV
     * - 20161127
     * - Them moi class BuildMsgLQT (thay the cho class SendLTToan)
     * - Class nay buil msg LTT va insert vao bang tam
     * - Sua loi truyen lenh 2 lan
     * */
    public BuildMsgLTT(Connection conn) {
        this.conn = conn;
        
    }

    public String sendMessage(String strID, String strMaKB,
                              String strNguoiDuyet, String strNgayDuyet,
                              String strHachToanTheoNgayKSNH,
                              HttpSession session,
                              String strLoaiDuyet) throws Exception {
        if (strID.substring(2, 5).equals("701")) {
            return sendMessageToBank(strID, strNguoiDuyet, strNgayDuyet,
                                     session, strLoaiDuyet);
        } else {
            return sendMessageToTabmis(strID, strMaKB,
                                       strHachToanTheoNgayKSNH);
        }
    }

    /**
     * @see: Ham build message va gui vao truc tich hop -> gui vao tabmis
     * @param: strType: Hach toan theo bang ke hay hach toan theo chung tu ("BK"/"CT"); strID: ID cua lenh qtoan hoac ID bang ke
     * @return: String msg_id
     * */
    public String sendMessageToTabmis(String strID, String strMaKB,
                                      String strHachToanTheoNgayKSNH) throws Exception {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer sbMsg = new StringBuffer();
        String strMsgID = "";
        String strKyHieuHachToan = "";
        boolean checkHachToanTKTienGui = false;
        int iCounter = 0;
        //Lay tham so he thong cho hach toan tabmis
//        setThamSoHeThong();
        //Lay lenh thanh toan
        LTTDAO lttDAO = new LTTDAO(conn);
        LTTCTietDAO lttctietDAO = new LTTCTietDAO(conn);

        String strWhere = " AND a.id = ?";
        Vector vParam = new Vector();
        vParam.add(new Parameter(strID, true));
        LTTVO lttVO = lttDAO.getLTTForHachToan(strWhere, vParam);

        if (lttVO == null) {
            throw (new TTSPException()).createException("TTSP-1009",
                                                        "Kh&#244;ng t&#236;m th&#7845;y LTT ID = " +
                                                        strID + " trong CSDL");
        }
        //Lay thong tin NH nhan
        DMNHangDAO dmDAO = new DMNHangDAO(conn);
        String strMa3SoNH = lttVO.getMa_nhkb_chuyen().substring(2, 5);
        String strWhereClause = " and a.ma_dv = ? ";
        Vector parameters = new Vector();
        parameters.add(new Parameter(strMa3SoNH, true));
        DMNHangHOVO nhHOvo = dmDAO.getDMNHangHO(strWhereClause, parameters);

        if (nhHOvo == null) {
            throw (new TTSPException()).createException("TTSP-1009",
                                                        "Kh&#244;ng t&#236;m th&#7845;y m&#227; &#7913;ng d&#7909;ng nh&#7853;n t&#432;&#417;ng &#7913;ng v&#7899;i m&#227; ng&#226;n h&#224;ng nh&#7853;n\n Duy&#7879;t l&#7879;nh kh&#244;ng th&#224;nh c&#244;ng.");
        }

        //Lay chi tiet lenh thanh toan
        strWhere = "t.ltt_id = ?";
        Collection listLTTCtiet =
            lttctietDAO.getLTTDiCTietList(strWhere, vParam);

        //Lay tham so hach toan
        if (lttVO.getLoai_hach_toan() != null &&
            "01".equals(lttVO.getLoai_hach_toan())) {
            if("VND".equalsIgnoreCase(lttVO.getMa_nt()) || lttVO.getMa_nt()==null){
              strKyHieuHachToan = "HACH_TOAN_DUNG_LTT";
            }else{
                //Kiem tra lenh co quyet toan ko
                TKNHKBacDAO tknhDAO = new TKNHKBacDAO(conn);
              String strWhereTK =
                  " AND b.ma_nh = ? AND c.ma = ? AND a.ma_nt = ? AND a.trang_thai = '01' and a.loai_tk in ('02','03') ";
                Vector vtParamTK = new Vector();
                vtParamTK.add(new Parameter(lttVO.getMa_nhkb_chuyen(), true));
                vtParamTK.add(new Parameter(strMaKB, true));
                vtParamTK.add(new Parameter(lttVO.getMa_nt(), true));
                TKNHKBacVO tkVO =
                    tknhDAO.getTK_NH_KB_VO(strWhereTK, vtParamTK);
                if(tkVO == null){
                  throw new Exception("Khong tim duoc thong tin tai khoan");
                }
                
                if ("N".equalsIgnoreCase(tkVO.getQuyet_toan())) {
                    strKyHieuHachToan = "NGTE_KO_QTOAN_HACH_TOAN_DUNG_LTT";
                } else {
              strKyHieuHachToan = "NGOAI_TE_HACH_TOAN_DUNG_LTT";
                }
              HACH_TOAN_TK_TIEN_GUI = "NGOAI_TE_" + HACH_TOAN_TK_TIEN_GUI;
              HACH_TOAN_TK_CHO_XLY_CO = "NGOAI_TE_" + HACH_TOAN_TK_CHO_XLY_CO;

            }
        } else {
          if("VND".equalsIgnoreCase(lttVO.getMa_nt()) || lttVO.getMa_nt()==null){
            strKyHieuHachToan = "HACH_TOAN_CHO_XLY_LTT_NO";
          }else{
            strKyHieuHachToan = "NGOAI_TE_HACH_TOAN_CHO_XLY_LTT_NO";
            HACH_TOAN_TK_TIEN_GUI = "NGOAI_TE_" + HACH_TOAN_TK_TIEN_GUI;
            HACH_TOAN_TK_CHO_XLY_CO = "NGOAI_TE_" + HACH_TOAN_TK_CHO_XLY_CO;
          }
        }
        DmTSoHToanDAO dmTSoHToanDAO = new DmTSoHToanDAO(conn);
        String whereClause = "and ma_nh=? and loai_htoan = ?";
        Vector params = new Vector();
        params.add(new Parameter(strMa3SoNH, true));
        params.add(new Parameter(strKyHieuHachToan, true));
        DmTSoHToanVO dmTSoHToanVO =
            dmTSoHToanDAO.getDmTSoHToan(whereClause, params);
        if(dmTSoHToanVO == null){
          throw new Exception("Chua khai bao danh muc tai khoan hach toan tabmis. Hay lien he voi KBNN TW de kiem tra.");
        }
        //Sinh msg_id
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        strMsgID =
                ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);
        //KT ngay dau thang
        String strNgayHienTai =
            StringUtil.DateToString(new Date(), "yyyyMMdd");
        String strNgayLamViecDauThang =
            StringUtil.DateToString(new Date(), "yyyyMM") + "01";
        strNgayLamViecDauThang =
                ttspUtils.getNgayLamViec(strNgayLamViecDauThang);
        if (strNgayHienTai.equals(strNgayLamViecDauThang)) {
            strHachToanTheoNgayKSNH = "Y";
        }
        //------------


        sbBody.append("<BODY>");
        sbBody.append("<MASTER>");

        LTTCTietVO lttCtietVO = null;
        //Check co phai hach toan dung khong
        if (lttVO.getLoai_hach_toan() != null &&
            "01".equals(lttVO.getLoai_hach_toan())) {
            if (listLTTCtiet != null) {
                if (listLTTCtiet.size() > 0) {
                    Iterator iter = listLTTCtiet.iterator();
                    while (iter.hasNext()) {
                        lttCtietVO = (LTTCTietVO)iter.next();
                        iCounter++;
                        sbBody.append("<ROW>");
                        sbBody.append("<ID>");
                        sbBody.append(lttVO.getId() +
                                      StringUtil.lpad(String.valueOf(iCounter),
                                                      3, '0'));
                        sbBody.append("</ID>");
                        sbBody.append("<FILE_NAME>");
                        sbBody.append("</FILE_NAME>");
                        sbBody.append("<SOURCE_MODULE>");
                        sbBody.append(GL_SOURCE_MODULE);
                        sbBody.append("</SOURCE_MODULE>");
                        sbBody.append("<INTER_TRY_DESTINATION>");
                        if (lttCtietVO.getKb_ma() != null &&
                            !"".equals(lttCtietVO.getKb_ma())) {
                            strMaKB = lttCtietVO.getKb_ma();
                        }
                        sbBody.append(strMaKB);

                        sbBody.append("</INTER_TRY_DESTINATION>");
                        sbBody.append("<DESTINATION_JOURNAL_NAME>");
                        sbBody.append(strMaKB +
                                      AppConstants.APP_NAME_HACH_TOAN_TABMIS +
                                      lttVO.getId());
                        sbBody.append("</DESTINATION_JOURNAL_NAME>");
                        sbBody.append("<CATEGORY>");
                        sbBody.append(CATEGORY);
                        sbBody.append("</CATEGORY>");
                        sbBody.append("<DESCRIPTION>");
                        sbBody.append(StringUtil.xmlSpeReplace(lttCtietVO.getDien_giai()));
                        sbBody.append("</DESCRIPTION>");
                        sbBody.append("<ACCOUNTING_DATE>");

                        if ("Y".equalsIgnoreCase(strHachToanTheoNgayKSNH)) {
                            sbBody.append(lttVO.getNgay_ks_nh().substring(0,
                                                                          10));
                        } else {
                            sbBody.append(lttVO.getNgay_nhan().substring(0,
                                                                         10));
                        }

                        sbBody.append("</ACCOUNTING_DATE>");
                        sbBody.append("<TRANACTION_DATE>");
                        sbBody.append(StringUtil.DateToString(new Date(),
                                                              AppConstants.DATE_FORMAT_SEND_ORDER));
                        sbBody.append("</TRANACTION_DATE>");
                        sbBody.append("<CURRENCY>");
                        sbBody.append(lttVO.getMa_nt());
                        sbBody.append("</CURRENCY>");
                        sbBody.append("<LINE_NUMBER>");
                        sbBody.append(iCounter);
                        sbBody.append("</LINE_NUMBER>");
                        sbBody.append("<FUND_TYPE>");
                        sbBody.append(lttCtietVO.getMa_quy());
                        sbBody.append("</FUND_TYPE>");
                        sbBody.append("<NATURAL_ACCOUNT>");
                        sbBody.append(lttCtietVO.getTkkt_ma());
                        sbBody.append("</NATURAL_ACCOUNT>");
                        sbBody.append("<ECONOMIC_CODE>");
                        sbBody.append(lttCtietVO.getNdkt_ma());
                        sbBody.append("</ECONOMIC_CODE>");
                        sbBody.append("<BUDGET_LEVEL>");
                        sbBody.append(lttCtietVO.getCapns_ma());
                        sbBody.append("</BUDGET_LEVEL>");
                        sbBody.append("<ORGANISATION>");
                        sbBody.append(lttCtietVO.getDvsdns_ma());
                        sbBody.append("</ORGANISATION>");
                        sbBody.append("<LOCATION>");
                        sbBody.append(lttCtietVO.getDbhc_ma());
                        sbBody.append("</LOCATION>");
                        sbBody.append("<CHAPTER_LEVEL>");
                        sbBody.append(lttCtietVO.getChuongns_ma());
                        sbBody.append("</CHAPTER_LEVEL>");
                        sbBody.append("<FUNCTION_CODE>");
                        sbBody.append(lttCtietVO.getNganhkt_ma());
                        sbBody.append("</FUNCTION_CODE>");
                        sbBody.append("<TARGETED_PROG>");
                        sbBody.append(lttCtietVO.getCtmt_ma());
                        sbBody.append("</TARGETED_PROG>");
                        sbBody.append("<TREASURY>");
                        sbBody.append(strMaKB);
                        sbBody.append("</TREASURY>");
                        sbBody.append("<SOURCE>");
                        sbBody.append(lttCtietVO.getMa_nguon());
                        sbBody.append("</SOURCE>");
                        sbBody.append("<SPARED>");
                        sbBody.append(lttCtietVO.getDu_phong_ma());
                        sbBody.append("</SPARED>");
                        sbBody.append("<DEBIT_AMOUNT>");
                        sbBody.append("</DEBIT_AMOUNT>");
                        sbBody.append("<CREDIT_AMOUNT>");
                        sbBody.append(lttCtietVO.getSo_tien());
                        sbBody.append("</CREDIT_AMOUNT>");
                        sbBody.append("<PROCESS_CODE>");
                        sbBody.append("</PROCESS_CODE>");
                        sbBody.append("<STATUS>");
                        sbBody.append("</STATUS>");
                        sbBody.append("<DATA_LINE>");
                        sbBody.append("</DATA_LINE>");
                        sbBody.append("<ERROR_CODE>");
                        sbBody.append("</ERROR_CODE>");
                        sbBody.append("<ERROR_DESCRIPTION>");
                        sbBody.append("</ERROR_DESCRIPTION>");
                        sbBody.append("<ACK_SENT>");
                        sbBody.append("</ACK_SENT>");
                        sbBody.append("<PROCESS_DATE>");
                        sbBody.append("</PROCESS_DATE>");
                        sbBody.append("<SOURCE_REFER_NUMBER>");
                        sbBody.append("</SOURCE_REFER_NUMBER>");
                        sbBody.append("<MSG_ID>");
                        sbBody.append(strMsgID);
                        sbBody.append("</MSG_ID>");
                        sbBody.append("<TABMIS_PROCESS_DATE>");
                        sbBody.append(StringUtil.DateToString(new Date(),
                                                              AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
                        sbBody.append("</TABMIS_PROCESS_DATE>");
                        sbBody.append("<OLD_STATUS>");
                        sbBody.append("</OLD_STATUS>");
                        sbBody.append("<BANK_CODE_SENDER>");
                        sbBody.append(lttVO.getMa_nhkb_chuyen());
                        sbBody.append("</BANK_CODE_SENDER>");
                        sbBody.append("<BANK_CODE_RECEIVER>");
                        sbBody.append(lttVO.getMa_nhkb_nhan());
                        sbBody.append("</BANK_CODE_RECEIVER>");
                        sbBody.append("</ROW>");
                    }
                } else {
                    checkHachToanTKTienGui = true;
                }
            } else {
                checkHachToanTKTienGui = true;
            }
            //Hach toan tai khoan tien gui (truong hop khong co MLNS)
            if (checkHachToanTKTienGui) {
                params = new Vector();
                params.add(new Parameter(strMa3SoNH, true));
                params.add(new Parameter(HACH_TOAN_TK_TIEN_GUI, true));
                DmTSoHToanVO dmTSoHToanVO_TG =
                    dmTSoHToanDAO.getDmTSoHToan(whereClause, params);

                String strTKTN;
                String strDVQHNS;
                String strCapNS;
                String strTKNguoiNhan =
                    lttVO.getSo_tk_nhan() == null ? "" : lttVO.getSo_tk_nhan().trim();
                if (strTKNguoiNhan.length() == 12) {
                    strTKTN = strTKNguoiNhan.substring(0, 4);
                    strCapNS = strTKNguoiNhan.substring(4, 5);
                    strDVQHNS = strTKNguoiNhan.substring(5, 12);
                    //Kiem tra ma CAPNS
                    DMCapNSDAO dmCap = new DMCapNSDAO(conn);
                    Vector vtParam = new Vector();
                    vtParam.add(new Parameter(strCapNS, true));
                    DMCapNSVO capNS =
                        dmCap.getDMCapNS(" c.ma = ? and c.tinhtrang = 1 ",
                                         vtParam);
                    if (capNS == null) {
                        throw new Exception("Hach toan tabmis khong thanh cong. Cap NS " +
                                            strCapNS + " khong dung.");
                    }
                    //Kiem tra ma DVDHNS
                    vtParam = new Vector();
                    vtParam.add(new Parameter(strDVQHNS, true));
                    DMDonViSdnsDAO dmDVSD = new DMDonViSdnsDAO(conn);
                    DMDonViSdnsVO dmDSSDVO =
                        dmDVSD.getDMDonViSdns(" a.ma = ? and a.tinh_trang = 1",
                                              vtParam);
                    if (dmDSSDVO == null) {
                        throw new Exception("Hach toan tabmis khong thanh cong. Ma DVSDNS " +
                                            strDVQHNS + " khong dung.");
                    }
                    //Kiem tra tai khoan kt
                    vtParam = new Vector();
                    vtParam.add(new Parameter(strTKTN, true));
                    DMTKKTDAO tktnDAO = new DMTKKTDAO(conn);
                    DMTKKTVO tktnVO =
                        tktnDAO.getDMTKKT(" t.ma = ? and t.tinhtrang = 1",
                                          vtParam);
                    if (tktnVO == null) {
                        throw new Exception("Hach toan tabmis khong thanh cong. Ma DVSDNS " +
                                            strTKTN + " khong dung.");
                    }
                } else {
                    throw new Exception("Hach toan tabmis khong thanh cong. So tai khoan nguoi nhan khong dung dinh dang. ");
                }

                dmTSoHToanVO_TG.setCap_ns(strCapNS);
                dmTSoHToanVO_TG.setTktn(strTKTN);
                dmTSoHToanVO_TG.setDvsdns(strDVQHNS);

                iCounter++;
                //Row trong truong khong co MLNS
                sbBody.append("<ROW>");
                sbBody.append("<ID>");
                sbBody.append(lttVO.getId() +
                              StringUtil.lpad(String.valueOf(iCounter), 3,
                                              '0'));
                sbBody.append("</ID>");
                sbBody.append("<FILE_NAME>");
                sbBody.append("</FILE_NAME>");
                sbBody.append("<SOURCE_MODULE>");
                sbBody.append(GL_SOURCE_MODULE);
                sbBody.append("</SOURCE_MODULE>");
                sbBody.append("<INTER_TRY_DESTINATION>");
                sbBody.append(strMaKB);
                sbBody.append("</INTER_TRY_DESTINATION>");
                sbBody.append("<DESTINATION_JOURNAL_NAME>");
                sbBody.append(strMaKB +
                              AppConstants.APP_NAME_HACH_TOAN_TABMIS +
                              lttVO.getId());
                sbBody.append("</DESTINATION_JOURNAL_NAME>");
                sbBody.append("<CATEGORY>");
                sbBody.append(CATEGORY);
                sbBody.append("</CATEGORY>");
                sbBody.append("<DESCRIPTION>");
                sbBody.append(StringUtil.xmlSpeReplace(lttVO.getNdung_tt()));
                sbBody.append("</DESCRIPTION>");
                sbBody.append("<ACCOUNTING_DATE>");

                if ("Y".equalsIgnoreCase(strHachToanTheoNgayKSNH)) {
                    sbBody.append(lttVO.getNgay_ks_nh().substring(0, 10));
                } else {
                    sbBody.append(lttVO.getNgay_nhan().substring(0, 10));
                }

                sbBody.append("</ACCOUNTING_DATE>");
                sbBody.append("<TRANACTION_DATE>");
                sbBody.append(StringUtil.DateToString(new Date(),
                                                      AppConstants.DATE_FORMAT_SEND_ORDER));
                sbBody.append("</TRANACTION_DATE>");
                sbBody.append("<CURRENCY>");
                sbBody.append(lttVO.getMa_nt());
                sbBody.append("</CURRENCY>");
                sbBody.append("<LINE_NUMBER>");
                sbBody.append(iCounter);
                sbBody.append("</LINE_NUMBER>");
                sbBody.append("<FUND_TYPE>");
                sbBody.append(dmTSoHToanVO_TG.getQuy());
                sbBody.append("</FUND_TYPE>");
                sbBody.append("<NATURAL_ACCOUNT>");

                sbBody.append(dmTSoHToanVO_TG.getTktn());

                sbBody.append("</NATURAL_ACCOUNT>");
                sbBody.append("<ECONOMIC_CODE>");
                sbBody.append(dmTSoHToanVO_TG.getNdkt());
                sbBody.append("</ECONOMIC_CODE>");
                sbBody.append("<BUDGET_LEVEL>");
                sbBody.append(dmTSoHToanVO_TG.getCap_ns());
                sbBody.append("</BUDGET_LEVEL>");
                sbBody.append("<ORGANISATION>");
                sbBody.append(dmTSoHToanVO_TG.getDvsdns());
                sbBody.append("</ORGANISATION>");
                sbBody.append("<LOCATION>");
                sbBody.append(dmTSoHToanVO_TG.getDbhc());
                sbBody.append("</LOCATION>");
                sbBody.append("<CHAPTER_LEVEL>");
                sbBody.append(dmTSoHToanVO_TG.getChuong());
                sbBody.append("</CHAPTER_LEVEL>");
                sbBody.append("<FUNCTION_CODE>");
                sbBody.append(dmTSoHToanVO_TG.getNganh_kt());
                sbBody.append("</FUNCTION_CODE>");
                sbBody.append("<TARGETED_PROG>");
                sbBody.append(dmTSoHToanVO_TG.getCtmt());
                sbBody.append("</TARGETED_PROG>");
                sbBody.append("<TREASURY>");
                sbBody.append(strMaKB);
                sbBody.append("</TREASURY>");
                sbBody.append("<SOURCE>");
                sbBody.append(dmTSoHToanVO_TG.getNguon());
                sbBody.append("</SOURCE>");
                sbBody.append("<SPARED>");
                sbBody.append(dmTSoHToanVO_TG.getDu_phong());
                sbBody.append("</SPARED>");
                sbBody.append("<DEBIT_AMOUNT>");
                sbBody.append("</DEBIT_AMOUNT>");
                sbBody.append("<CREDIT_AMOUNT>");
                sbBody.append(lttVO.getTong_sotien());
                sbBody.append("</CREDIT_AMOUNT>");
                sbBody.append("<PROCESS_CODE>");
                sbBody.append("</PROCESS_CODE>");
                sbBody.append("<STATUS>");
                sbBody.append("</STATUS>");
                sbBody.append("<DATA_LINE>");
                sbBody.append("</DATA_LINE>");
                sbBody.append("<ERROR_CODE>");
                sbBody.append("</ERROR_CODE>");
                sbBody.append("<ERROR_DESCRIPTION>");
                sbBody.append("</ERROR_DESCRIPTION>");
                sbBody.append("<ACK_SENT>");
                sbBody.append("</ACK_SENT>");
                sbBody.append("<PROCESS_DATE>");
                sbBody.append("</PROCESS_DATE>");
                sbBody.append("<SOURCE_REFER_NUMBER>");
                sbBody.append("</SOURCE_REFER_NUMBER>");
                sbBody.append("<MSG_ID>");
                sbBody.append(strMsgID);
                sbBody.append("</MSG_ID>");
                sbBody.append("<TABMIS_PROCESS_DATE>");
                sbBody.append(StringUtil.DateToString(new Date(),
                                                      AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
                sbBody.append("</TABMIS_PROCESS_DATE>");
                sbBody.append("<OLD_STATUS>");
                sbBody.append("</OLD_STATUS>");
                sbBody.append("<BANK_CODE_SENDER>");
                sbBody.append(lttVO.getMa_nhkb_chuyen());
                sbBody.append("</BANK_CODE_SENDER>");
                sbBody.append("<BANK_CODE_RECEIVER>");
                sbBody.append(lttVO.getMa_nhkb_nhan());
                sbBody.append("</BANK_CODE_RECEIVER>");
                sbBody.append("</ROW>");

            }
        } else {
            params = new Vector();
            params.add(new Parameter(strMa3SoNH, true));
            params.add(new Parameter(HACH_TOAN_TK_CHO_XLY_CO, true));
            DmTSoHToanVO dmTSoHToanVO_CHO_XLY_CO =
                dmTSoHToanDAO.getDmTSoHToan(whereClause, params);


            iCounter++;
            sbBody.append("<ROW>");
            sbBody.append("<ID>");
            sbBody.append(lttVO.getId() +
                          StringUtil.lpad(String.valueOf(iCounter), 3, '0'));
            sbBody.append("</ID>");
            sbBody.append("<FILE_NAME>");
            sbBody.append("</FILE_NAME>");
            sbBody.append("<SOURCE_MODULE>");
            sbBody.append(GL_SOURCE_MODULE);
            sbBody.append("</SOURCE_MODULE>");
            sbBody.append("<INTER_TRY_DESTINATION>");
            sbBody.append(strMaKB);
            sbBody.append("</INTER_TRY_DESTINATION>");
            sbBody.append("<DESTINATION_JOURNAL_NAME>");
            sbBody.append(strMaKB + AppConstants.APP_NAME_HACH_TOAN_TABMIS +
                          lttVO.getId());
            sbBody.append("</DESTINATION_JOURNAL_NAME>");
            sbBody.append("<CATEGORY>");
            sbBody.append(CATEGORY);
            sbBody.append("</CATEGORY>");
            sbBody.append("<DESCRIPTION>");
            sbBody.append(StringUtil.xmlSpeReplace(lttVO.getNdung_tt()));
            sbBody.append("</DESCRIPTION>");
            sbBody.append("<ACCOUNTING_DATE>");

            if ("Y".equalsIgnoreCase(strHachToanTheoNgayKSNH)) {
                sbBody.append(lttVO.getNgay_ks_nh().substring(0, 10));
            } else {
                sbBody.append(lttVO.getNgay_nhan().substring(0, 10));
            }

            sbBody.append("</ACCOUNTING_DATE>");
            sbBody.append("<TRANACTION_DATE>");
            sbBody.append(StringUtil.DateToString(new Date(),
                                                  AppConstants.DATE_FORMAT_SEND_ORDER));
            sbBody.append("</TRANACTION_DATE>");
            sbBody.append("<CURRENCY>");
            sbBody.append(lttVO.getMa_nt());
            sbBody.append("</CURRENCY>");
            sbBody.append("<LINE_NUMBER>");
            sbBody.append(iCounter);
            sbBody.append("</LINE_NUMBER>");
            sbBody.append("<FUND_TYPE>");
            sbBody.append(dmTSoHToanVO_CHO_XLY_CO.getQuy());
            sbBody.append("</FUND_TYPE>");
            sbBody.append("<NATURAL_ACCOUNT>");
            sbBody.append(dmTSoHToanVO_CHO_XLY_CO.getTktn());
            sbBody.append("</NATURAL_ACCOUNT>");
            sbBody.append("<ECONOMIC_CODE>");
            sbBody.append(dmTSoHToanVO_CHO_XLY_CO.getNdkt());
            sbBody.append("</ECONOMIC_CODE>");
            sbBody.append("<BUDGET_LEVEL>");
            sbBody.append(dmTSoHToanVO_CHO_XLY_CO.getCap_ns());
            sbBody.append("</BUDGET_LEVEL>");
            sbBody.append("<ORGANISATION>");
            sbBody.append(dmTSoHToanVO_CHO_XLY_CO.getDvsdns());
            sbBody.append("</ORGANISATION>");
            sbBody.append("<LOCATION>");
            sbBody.append(dmTSoHToanVO_CHO_XLY_CO.getDbhc());
            sbBody.append("</LOCATION>");
            sbBody.append("<CHAPTER_LEVEL>");
            sbBody.append(dmTSoHToanVO_CHO_XLY_CO.getChuong());
            sbBody.append("</CHAPTER_LEVEL>");
            sbBody.append("<FUNCTION_CODE>");
            sbBody.append(dmTSoHToanVO_CHO_XLY_CO.getNganh_kt());
            sbBody.append("</FUNCTION_CODE>");
            sbBody.append("<TARGETED_PROG>");
            sbBody.append(dmTSoHToanVO_CHO_XLY_CO.getCtmt());
            sbBody.append("</TARGETED_PROG>");
            sbBody.append("<TREASURY>");
            sbBody.append(strMaKB);
            sbBody.append("</TREASURY>");
            sbBody.append("<SOURCE>");
            sbBody.append(dmTSoHToanVO_CHO_XLY_CO.getNguon());
            sbBody.append("</SOURCE>");
            sbBody.append("<SPARED>");
            sbBody.append(dmTSoHToanVO_CHO_XLY_CO.getDu_phong());
            sbBody.append("</SPARED>");
            sbBody.append("<DEBIT_AMOUNT>");
            sbBody.append("</DEBIT_AMOUNT>");
            sbBody.append("<CREDIT_AMOUNT>");
            sbBody.append(lttVO.getTong_sotien());
            sbBody.append("</CREDIT_AMOUNT>");
            sbBody.append("<PROCESS_CODE>");
            sbBody.append("</PROCESS_CODE>");
            sbBody.append("<STATUS>");
            sbBody.append("</STATUS>");
            sbBody.append("<DATA_LINE>");
            sbBody.append("</DATA_LINE>");
            sbBody.append("<ERROR_CODE>");
            sbBody.append("</ERROR_CODE>");
            sbBody.append("<ERROR_DESCRIPTION>");
            sbBody.append("</ERROR_DESCRIPTION>");
            sbBody.append("<ACK_SENT>");
            sbBody.append("</ACK_SENT>");
            sbBody.append("<PROCESS_DATE>");
            sbBody.append("</PROCESS_DATE>");
            sbBody.append("<SOURCE_REFER_NUMBER>");
            sbBody.append("</SOURCE_REFER_NUMBER>");
            sbBody.append("<MSG_ID>");
            sbBody.append(strMsgID);
            sbBody.append("</MSG_ID>");
            sbBody.append("<TABMIS_PROCESS_DATE>");
            sbBody.append(StringUtil.DateToString(new Date(),
                                                  AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
            sbBody.append("</TABMIS_PROCESS_DATE>");
            sbBody.append("<OLD_STATUS>");
            sbBody.append("</OLD_STATUS>");
            sbBody.append("<BANK_CODE_SENDER>");
            sbBody.append(lttVO.getMa_nhkb_chuyen());
            sbBody.append("</BANK_CODE_SENDER>");
            sbBody.append("<BANK_CODE_RECEIVER>");
            sbBody.append(lttVO.getMa_nhkb_nhan());
            sbBody.append("</BANK_CODE_RECEIVER>");
            sbBody.append("</ROW>");

        }
        iCounter++;
        //But toan doi ung
        sbBody.append("<ROW>");
        sbBody.append("<ID>");
        sbBody.append(lttVO.getId() +
                      StringUtil.lpad(String.valueOf(iCounter), 3, '0'));
        sbBody.append("</ID>");
        sbBody.append("<FILE_NAME>");
        sbBody.append("</FILE_NAME>");
        sbBody.append("<SOURCE_MODULE>");
        sbBody.append(GL_SOURCE_MODULE);
        sbBody.append("</SOURCE_MODULE>");
        sbBody.append("<INTER_TRY_DESTINATION>");
        sbBody.append(strMaKB);
        sbBody.append("</INTER_TRY_DESTINATION>");
        sbBody.append("<DESTINATION_JOURNAL_NAME>");
        sbBody.append(strMaKB + AppConstants.APP_NAME_HACH_TOAN_TABMIS +
                      lttVO.getId());
        sbBody.append("</DESTINATION_JOURNAL_NAME>");
        sbBody.append("<CATEGORY>");
        sbBody.append(CATEGORY);
        sbBody.append("</CATEGORY>");
        sbBody.append("<DESCRIPTION>");
        sbBody.append(StringUtil.xmlSpeReplace(lttVO.getNdung_tt()));
        sbBody.append("</DESCRIPTION>");
        sbBody.append("<ACCOUNTING_DATE>");

        if ("Y".equalsIgnoreCase(strHachToanTheoNgayKSNH)) {
            sbBody.append(lttVO.getNgay_ks_nh().substring(0, 10));
        } else {
            sbBody.append(lttVO.getNgay_nhan().substring(0, 10));
        }
        sbBody.append("</ACCOUNTING_DATE>");
        sbBody.append("<TRANACTION_DATE>");
        sbBody.append(StringUtil.DateToString(new Date(),
                                              AppConstants.DATE_FORMAT_SEND_ORDER));
        sbBody.append("</TRANACTION_DATE>");
        sbBody.append("<CURRENCY>");
        sbBody.append(lttVO.getMa_nt());
        sbBody.append("</CURRENCY>");
        sbBody.append("<LINE_NUMBER>");
        sbBody.append(iCounter);
        sbBody.append("</LINE_NUMBER>");
        sbBody.append("<FUND_TYPE>");
        sbBody.append(dmTSoHToanVO.getQuy());
        sbBody.append("</FUND_TYPE>");
        sbBody.append("<NATURAL_ACCOUNT>");

        sbBody.append(dmTSoHToanVO.getTktn());
        sbBody.append("</NATURAL_ACCOUNT>");
        sbBody.append("<ECONOMIC_CODE>");
        sbBody.append(dmTSoHToanVO.getNdkt());
        sbBody.append("</ECONOMIC_CODE>");
        sbBody.append("<BUDGET_LEVEL>");
        sbBody.append(dmTSoHToanVO.getCap_ns());
        sbBody.append("</BUDGET_LEVEL>");
        sbBody.append("<ORGANISATION>");
        sbBody.append(dmTSoHToanVO.getDvsdns());
        sbBody.append("</ORGANISATION>");
        sbBody.append("<LOCATION>");
        sbBody.append(dmTSoHToanVO.getDbhc());
        sbBody.append("</LOCATION>");
        sbBody.append("<CHAPTER_LEVEL>");
        sbBody.append(dmTSoHToanVO.getChuong());
        sbBody.append("</CHAPTER_LEVEL>");
        sbBody.append("<FUNCTION_CODE>");
        sbBody.append(dmTSoHToanVO.getNganh_kt());
        sbBody.append("</FUNCTION_CODE>");
        sbBody.append("<TARGETED_PROG>");
        sbBody.append(dmTSoHToanVO.getCtmt());
        sbBody.append("</TARGETED_PROG>");
        sbBody.append("<TREASURY>");
        sbBody.append(strMaKB);
        sbBody.append("</TREASURY>");
        sbBody.append("<SOURCE>");
        sbBody.append(dmTSoHToanVO.getNguon());
        sbBody.append("</SOURCE>");
        sbBody.append("<SPARED>");
        sbBody.append(dmTSoHToanVO.getDu_phong());
        sbBody.append("</SPARED>");
        sbBody.append("<DEBIT_AMOUNT>");
        sbBody.append(lttVO.getTong_sotien());
        sbBody.append("</DEBIT_AMOUNT>");
        sbBody.append("<CREDIT_AMOUNT>");
        sbBody.append("</CREDIT_AMOUNT>");
        sbBody.append("<PROCESS_CODE>");
        sbBody.append("</PROCESS_CODE>");
        sbBody.append("<STATUS>");
        sbBody.append("</STATUS>");
        sbBody.append("<DATA_LINE>");
        sbBody.append("</DATA_LINE>");
        sbBody.append("<ERROR_CODE>");
        sbBody.append("</ERROR_CODE>");
        sbBody.append("<ERROR_DESCRIPTION>");
        sbBody.append("</ERROR_DESCRIPTION>");
        sbBody.append("<ACK_SENT>");
        sbBody.append("</ACK_SENT>");
        sbBody.append("<PROCESS_DATE>");
        sbBody.append("</PROCESS_DATE>");
        sbBody.append("<SOURCE_REFER_NUMBER>");
        sbBody.append("</SOURCE_REFER_NUMBER>");
        sbBody.append("<MSG_ID>");
        sbBody.append(strMsgID);
        sbBody.append("</MSG_ID>");
        sbBody.append("<TABMIS_PROCESS_DATE>");
        sbBody.append(StringUtil.DateToString(new Date(),
                                              AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        sbBody.append("</TABMIS_PROCESS_DATE>");
        sbBody.append("<OLD_STATUS>");
        sbBody.append("</OLD_STATUS>");
        sbBody.append("<BANK_CODE_SENDER>");
        sbBody.append(lttVO.getMa_nhkb_chuyen());
        sbBody.append("</BANK_CODE_SENDER>");
        sbBody.append("<BANK_CODE_RECEIVER>");
        sbBody.append(lttVO.getMa_nhkb_nhan());
        sbBody.append("</BANK_CODE_RECEIVER>");
        sbBody.append("</ROW>");

        sbBody.append("</MASTER>");
        sbBody.append("</BODY>");
        //Build Header
        //Tao msg header
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setVersion(AppConstants.VERSION_MSG);
        msgHeader.setSender_code(APP_SEND_CODE);
        msgHeader.setSender_name(APP_SEND_NAME);
        msgHeader.setReveiver_code(APP_RECIEVE_CODE);
        msgHeader.setReceiver_name(APP_RECIEVE_NAME);
        msgHeader.setTran_code(TRAN_CODE);
        msgHeader.setMsg_id(strMsgID);
        msgHeader.setMsg_refid("");
        //Noi header va body -> tao msg
        sbMsg.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sbMsg.append("<DATA>");
        sbMsg.append(msgHeader.getHeader());
        sbMsg.append(sbBody).toString();
        sbMsg.append("<SECURITY>");
        sbMsg.append("<SIGNATURE>");
        sbMsg.append("</SIGNATURE>");
        sbMsg.append("</SECURITY>");
        sbMsg.append("</DATA>");
        
        //Build log msg
        MsgQueueDAO msgDAO = new MsgQueueDAO(conn);
        MsgQueueVO msg = new MsgQueueVO();

        msg.setMsg(sbMsg.toString());
        msg.setMsg_id(strMsgID);
        msg.setTran_code(TRAN_CODE);
        msg.setSender_code(APP_SEND_CODE);
        msg.setReceiver_code(APP_RECIEVE_CODE);
        msg.setMt_id(strID);
        msg.setStatus("0");
        
        msgDAO.insert(msg);
        
        return strMsgID;
    }

    /**
     * @see: Ham build message va gui vao truc tich hop -> gui vao tabmis
     * @param: strType: Hach toan theo bang ke hay hach toan theo chung tu ("BK"/"CT"); strID: ID cua lenh qtoan hoac ID bang ke
     * @return: String msg_id
     * */
    public String sendMessageToBank(String strID, String strManager,
                                    String strNgayDuyet, HttpSession session,
                                    String strLoaiDuyet) throws Exception {
        StringBuffer sbBody = new StringBuffer();
        StringBuffer sbMsg = new StringBuffer();
        String strMsgID = "";
        //        int iCounter = 0;
        //Lay tham so he thong cho hach toan tabmis
        //        setThamSoHeThong();
        //Lay lenh thanh toan
        LTTDAO lttDAO = new LTTDAO(conn);
        LTTCTietDAO lttctietDAO = new LTTCTietDAO(conn);

        String strWhere = " AND a.id = ?";
        Vector vParam = new Vector();
        vParam.add(new Parameter(strID, true));
        LTTVO lttVO = lttDAO.getLTTForHachToan(strWhere, vParam);

        if (lttVO == null) {
            throw new Exception("Kh&#244;ng t&#236;m th&#7845;y LTT ID = " +
                                                        strID + " trong CSDL");
        }

        //Lay thong tin NH nhan
        DMNHangDAO dmDAO = new DMNHangDAO(conn);
        String strMa3SoNH = lttVO.getMa_nhkb_nhan().substring(2, 5);
        String strWhereClause = " and a.ma_dv = ? ";
        Vector parameters = new Vector();
        parameters.add(new Parameter(strMa3SoNH, true));
        DMNHangHOVO nhHOvo = dmDAO.getDMNHangHO(strWhereClause, parameters);

        if (nhHOvo == null) {
            throw (new TTSPException()).createException("TTSP-1009",
                                                        "Kh&#244;ng t&#236;m th&#7845;y m&#227; &#7913;ng d&#7909;ng nh&#7853;n t&#432;&#417;ng &#7913;ng v&#7899;i m&#227; ng&#226;n h&#224;ng nh&#7853;n\n Duy&#7879;t l&#7879;nh kh&#244;ng th&#224;nh c&#244;ng.");
        }
        APP_RECIEVE_CODE = nhHOvo.getMa_ung_dung();
        APP_RECIEVE_NAME = nhHOvo.getTen_ung_dung();

        //Lay chi tiet lenh thanh toan
        strWhere = "t.ltt_id = ?";
        Collection listLTTCtiet =
            lttctietDAO.getLTTDiCTietList(strWhere, vParam);
        if (listLTTCtiet != null) {
            if (listLTTCtiet.size() < 1) {
                throw new Exception("Kh&#244;ng t&#236;m th&#7845;y chi ti&#7871;t c&#7911;a LTT ID = " +
                                                            strID +
                                                            " trong CSDL");
            }
        } else {
            throw (new TTSPException()).createException("TTSP-1009",
                                                        "Kh&#244;ng t&#236;m th&#7845;y chi ti&#7871;t c&#7911;a LTT ID = " +
                                                        strID + " trong CSDL");
        }
        //Kiem tra dieu hanh von -----------------------------------------------

        if (!"TW".equalsIgnoreCase(strLoaiDuyet)) {
            String strKiemTraDHV = "N";
            if (session.getAttribute(AppConstants.APP_KIEM_TRA_DIEU_HANH_VON_SESSION) !=
                null) {
                strKiemTraDHV =
                        session.getAttribute(AppConstants.APP_KIEM_TRA_DIEU_HANH_VON_SESSION).toString();
            }
            if ("Y".equalsIgnoreCase(strKiemTraDHV)) {
                Iterator iter_dhv = listLTTCtiet.iterator();
                LTTCTietVO lttCtietVO_dhv = null;
                while (iter_dhv.hasNext()) {
                    lttCtietVO_dhv = (LTTCTietVO)iter_dhv.next();
                    break;
                }
                boolean bCheckDHV =
                    checkDieuHanhVon(lttCtietVO_dhv.getTkkt_ma(),
                                     lttCtietVO_dhv.getDvsdns_ma(),
                                     lttCtietVO_dhv.getNdkt_ma(),
                                     lttVO.getTong_sotien(), strMa3SoNH,
                                     session, conn);
                if (!bCheckDHV) {
                    return AppConstants.DIEU_HANH_VON;
                }
            }
        }
        //----------------------------------------------------------------------
        //Kiem tra qua gio cut-of-time
        TTSPUtils ttspUtil = new TTSPUtils(conn);
        String strCurDate = StringUtil.DateToString(new Date(), "dd-MM-yyyy");

        String strCOT =
            ttspUtil.getGioCutOfTime(strCurDate, lttVO.getMa_nhkb_chuyen(),
                                     lttVO.getMa_nhkb_nhan());
        Date dateCOT =
            StringUtil.StringToDate(strCurDate + strCOT, "dd-MM-yyyyHH:mm");

        String strNgayTT =
            DateUtils.LongToStrDateDDMMYYYY(lttVO.getNgay_tt()).replace("/",
                                                                        "-");
        String strSpare3 = "";

        Date dateCurrent = new Date();
        //ManhNV-20170119-begin-------------------------------------
        //Sua: ky lenh = COT van di NH 
        //if (dateCOT.compareTo(dateCurrent) < 0) {
        if (dateCOT.compareTo(dateCurrent) <=0) {
        //ManhNV-20170119-end---------------------------------------
            if (strNgayTT.equals(strCurDate)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
                String strNextDate =
                    dateFormat.format(dateCurrent.getTime() + MILLIS_IN_DAY);
                strNgayTT = ttspUtil.getNgayLamViec(strNextDate);

                strNgayTT =
                        DateUtils.LongToStrDateDDMMYYYY(new Long(strNgayTT)).replace("/",
                                                                                     "-");
            }
            strSpare3 =
                    strNgayTT.substring(6, 10) + strNgayTT.substring(3, 5) +
                    strNgayTT.substring(0, 2);
        } else {
            strNgayTT = strCurDate;
        }


        sbBody.append("<BODY>");
        sbBody.append("<MASTER>");
        sbBody.append("<ROW>");
        sbBody.append("<ID>");
        sbBody.append(lttVO.getId());
        sbBody.append("</ID>");

        sbBody.append("<NHKB_NHAN>");
        sbBody.append(lttVO.getMa_nhkb_nhan() == null ? "" :
                      lttVO.getMa_nhkb_nhan());
        sbBody.append("</NHKB_NHAN>");

        sbBody.append("<NHKB_CHUYEN>");
        sbBody.append(lttVO.getMa_nhkb_chuyen() == null ? "" :
                      StringUtil.xmlSpeReplace(lttVO.getMa_nhkb_chuyen()));
        sbBody.append("</NHKB_CHUYEN>");

        sbBody.append("<TTV_NHAN>");
        if(lttVO.getTen_ttv_nhan() == null || "".equals(lttVO.getTen_ttv_nhan())){
          throw new Exception("L&#7879;nh thanh to&#225;n ch&#432;a g&#7855;n v&#7899;i TTV n&#224;o, c&#7847;n &#273;&#7849;y l&#7841;i &#273;&#7875; TTV ho&#224;n thi&#7879;n v&#224; th&#7921;c hi&#7879;n k&#253; duy&#7879;t l&#7841;i.!");  
        }      
        sbBody.append(StringUtil.xmlSpeReplace(lttVO.getTen_ttv_nhan()));
        sbBody.append("</TTV_NHAN>");

        sbBody.append("<NGAY_NHAN>");
        sbBody.append(lttVO.getNgay_nhan());
        sbBody.append("</NGAY_NHAN>");

        sbBody.append("<KTV_CHUYEN/>");

        sbBody.append("<NGUOI_DUYET>");
        if (strManager == null || "".equals(strManager)) {
            strManager = lttVO.getTen_nguoi_duyet();
        }
        sbBody.append(strManager);
        sbBody.append("</NGUOI_DUYET>");

        sbBody.append("<NGAY_DUYET>");

        if (strNgayDuyet != null && !"".equals(strNgayDuyet)) {
            sbBody.append(strNgayDuyet);
        } else {
            sbBody.append(StringUtil.DateToString(new Date(),
                                                  AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        }
        sbBody.append("</NGAY_DUYET>");

        sbBody.append("<SO_LAN_TRUYEN>1</SO_LAN_TRUYEN>");
        sbBody.append("<MA_XU_LY>CRED</MA_XU_LY>");
        sbBody.append("<TY_GIA>1</TY_GIA>");
        sbBody.append("<LOAI_TK>C</LOAI_TK>");
        //sbBody.append("<PHI>1</PHI>");
        sbBody.append("<LOAI_BUT_TOAN>LNH</LOAI_BUT_TOAN>");

        sbBody.append("<SO_CT_GOC>");
        sbBody.append(lttVO.getSo_ct_goc() == null ? "" :
                      StringUtil.xmlSpeReplace(lttVO.getSo_ct_goc()));
        sbBody.append("</SO_CT_GOC>");

        sbBody.append("<NGAY_CT>");
        if (lttVO.getNgay_tt() != null) {
            sbBody.append(LongToStrDateDDMMYYYY(lttVO.getNgay_tt()));
        }
        sbBody.append("</NGAY_CT>");

        sbBody.append("<NT_ID>");
        sbBody.append(lttVO.getMa_nt() == null ? "VND" : lttVO.getMa_nt());
        sbBody.append("</NT_ID>");

        sbBody.append("<SO_YCTT>");
        sbBody.append(lttVO.getId() == null ? "" : lttVO.getId());
        sbBody.append("</SO_YCTT>");

        sbBody.append("<NGAY_TT>");
        if (lttVO.getNgay_tt() != null) {
            sbBody.append(strNgayTT);
        }
        sbBody.append("</NGAY_TT>");

        sbBody.append("<NDUNG_TT>");
        sbBody.append(lttVO.getNdung_tt() == null ? "" :
                      StringUtil.xmlSpeReplace(lttVO.getNdung_tt()));
        sbBody.append("</NDUNG_TT>");

        sbBody.append("<TONG_SOTIEN>");
        sbBody.append(lttVO.getTong_sotien());
        sbBody.append("</TONG_SOTIEN>");

        sbBody.append("<SO_TK_CHUYEN>");
        sbBody.append(lttVO.getSo_tk_chuyen() == null ? "" :
                      StringUtil.xmlSpeReplace(lttVO.getSo_tk_chuyen()));
        sbBody.append("</SO_TK_CHUYEN>");

        sbBody.append("<TEN_TK_CHUYEN>");
        sbBody.append(lttVO.getTen_tk_chuyen() == null ? "" :
                      StringUtil.xmlSpeReplace(lttVO.getTen_tk_chuyen()));
        sbBody.append("</TEN_TK_CHUYEN>");

        sbBody.append("<TTIN_KH_CHUYEN>");
        //ManhNV sua (20140819)
        String strTenTKChuyen = lttVO.getTen_tk_chuyen() == null ? "" :lttVO.getTen_tk_chuyen();
        String strTtinKHChuyen = lttVO.getTtin_kh_chuyen() == null ? "" :lttVO.getTtin_kh_chuyen();
        if((strTenTKChuyen +" "+ strTtinKHChuyen).length() > 146){
            if("".equals(strTtinKHChuyen)){
              strTtinKHChuyen = strTenTKChuyen;
            }
        }else{
          strTtinKHChuyen = strTenTKChuyen +" "+ strTtinKHChuyen;
        }
        if(strTtinKHChuyen.length() > 146){
          throw new Exception("T&#234;n t&#224;i kho&#7843;n chuy&#7875;n c&#243; s&#7889; k&#253; t&#7921; v&#432;&#7907;t qu&#225; quy &#273;inh cho ph&#233;p, c&#7847;n &#273;&#7849;y l&#7841;i TTV ho&#224;n thi&#7879;n");  
        }
        strTtinKHChuyen = StringUtil.xmlSpeReplace(strTtinKHChuyen);
        
        sbBody.append(strTtinKHChuyen);        
        //20140819)
        sbBody.append("</TTIN_KH_CHUYEN>");

        sbBody.append("<ID_NHKB_CHUYEN>");
        sbBody.append(lttVO.getMa_nhkb_chuyen_cm() == null ? "" :
                      lttVO.getMa_nhkb_chuyen_cm());
        sbBody.append("</ID_NHKB_CHUYEN>");

        sbBody.append("<TEN_NHKB_CHUYEN>");
        sbBody.append(lttVO.getTen_nhkb_chuyen() == null ? "" :
                      StringUtil.xmlSpeReplace(lttVO.getTen_nhkb_chuyen()));
        sbBody.append("</TEN_NHKB_CHUYEN>");

        sbBody.append("<SO_TK_NHAN>");
        sbBody.append(lttVO.getSo_tk_nhan() == null ? "" :
                      StringUtil.xmlSpeReplace(lttVO.getSo_tk_nhan()));
        sbBody.append("</SO_TK_NHAN>");

        sbBody.append("<TEN_TK_NHAN>");
        sbBody.append(lttVO.getTen_tk_nhan() == null ? "" :
                      StringUtil.xmlSpeReplace(lttVO.getTen_tk_nhan()));
        sbBody.append("</TEN_TK_NHAN>");

        sbBody.append("<TTIN_KH_NHAN>");
      //ManhNV sua (20141209)
//        String strTenTKNhan = lttVO.getTen_tk_nhan() == null ? "" :lttVO.getTen_tk_nhan();
//        String strTtinKHNhan = lttVO.getTtin_kh_nhan() == null ? "" :lttVO.getTtin_kh_nhan();        
//        if((strTenTKNhan +" "+ strTtinKHNhan).length() > 146){
//            if("".equals(strTtinKHNhan)){
//              strTtinKHNhan = strTenTKNhan;
//            }
//        }else{
//          strTtinKHNhan = strTenTKNhan +" "+ strTtinKHNhan;
//        }
//        if(strTtinKHNhan.length() > 146){
//          throw new Exception("T&#234;n t&#224;i kho&#7843;n chuy&#7875;n c&#243; s&#7889; k&#253; t&#7921; v&#432;&#7907;t qu&#225; quy &#273;inh cho ph&#233;p, c&#7847;n &#273;&#7849;y l&#7841;i TTV ho&#224;n thi&#7879;n");  
//        }
//        strTtinKHNhan = StringUtil.xmlSpeReplace(strTtinKHNhan);
//        
//        sbBody.append(strTtinKHNhan);        
        //20141209)       
        
        sbBody.append(lttVO.getTen_tk_nhan() == null ? "" :
                      StringUtil.xmlSpeReplace(lttVO.getTen_tk_nhan()));
        sbBody.append(" ");
        sbBody.append(lttVO.getTtin_kh_nhan() == null ? "" :
                      StringUtil.xmlSpeReplace(lttVO.getTtin_kh_nhan()));
        sbBody.append("</TTIN_KH_NHAN>");

        sbBody.append("<ID_NHKB_NHAN>");
        sbBody.append(lttVO.getMa_nhkb_nhan_cm() == null ? "" :
                      lttVO.getMa_nhkb_nhan_cm());
        sbBody.append("</ID_NHKB_NHAN>");

        sbBody.append("<TEN_NHKB_NHAN>");
        sbBody.append(lttVO.getTen_nhkb_nhan() == null ? "" :
                      StringUtil.xmlSpeReplace(lttVO.getTen_nhkb_nhan()));
        sbBody.append("</TEN_NHKB_NHAN>");

        sbBody.append("<NGUOI_NHAP_NH/>");

        sbBody.append("<NGAY_NHAP_NH/>");

        sbBody.append("<NGUOI_KS_NH/>");

        sbBody.append("<NGAY_KS_NH/>");
        //20150626-ManhNV-NH trung gian
        if("VND".equalsIgnoreCase(lttVO.getMa_nt()) || lttVO.getMa_nt()==null){
          sbBody.append("<NH_TGIAN>");
          sbBody.append(lttVO.getMa_nhkb_nhan_cm() == null ? "" :
                        lttVO.getMa_nhkb_nhan_cm());
          sbBody.append("</NH_TGIAN>");
        }else{
          if(lttVO.getNh_tgian() != null && !"".equals(lttVO.getNh_tgian())){
            sbBody.append("<NH_TGIAN>");
            sbBody.append(lttVO.getNh_tgian());
            sbBody.append("</NH_TGIAN>");
          }else{
            sbBody.append("<NH_TGIAN/>");
          }
        }
        if(lttVO.getTen_nh_tgian() != null && !"".equals(lttVO.getTen_nh_tgian())){
          sbBody.append("<TEN_NH_TGIAN>");
          sbBody.append(lttVO.getTen_nh_tgian());
          sbBody.append("</TEN_NH_TGIAN>");
        }else{
          sbBody.append("<TEN_NH_TGIAN/>");
        }
        //20150626
        sbBody.append("<NGAY_GUI_NH/>");
        sbBody.append("<SO_THAM_CHIEU_GD/>");
        if(lttVO.getMa_nt_mua_ban()==null || "".equals(lttVO.getMa_nt_mua_ban())){
          sbBody.append("<MA_NT_MUA_BAN/>");
          sbBody.append("<SO_TIEN_MUA_BAN/>");
        }else{
          sbBody.append("<MA_NT_MUA_BAN>");
          sbBody.append(lttVO.getMa_nt_mua_ban());  
          sbBody.append("</MA_NT_MUA_BAN>");
          sbBody.append("<SO_TIEN_MUA_BAN>");
          if("VND".equalsIgnoreCase(lttVO.getMa_nt_mua_ban())){
              if(lttVO.getSo_tien_mua_ban() == null){
                sbBody.append("");
              }else{
                sbBody.append(lttVO.getSo_tien_mua_ban().compareTo(new BigDecimal("0"))==0?"":lttVO.getSo_tien_mua_ban());    
              }
          }else{
            sbBody.append(lttVO.getSo_tien_mua_ban());  
          }
          sbBody.append("</SO_TIEN_MUA_BAN>");
        }
        //ManhNV20150521-Gui loai phi sang VCB
        sbBody.append("<PHI>");
        sbBody.append(lttVO.getPhi() == null ? "" :
                      lttVO.getPhi());
        sbBody.append("</PHI>");
        if("Y".equals(lttVO.getVay_tra_no_nn())){
          sbBody.append("<VAY_TRA_NO_NN>");
          sbBody.append("VTN");
          sbBody.append("</VAY_TRA_NO_NN>");
        }else{
          sbBody.append("<VAY_TRA_NO_NN/>");
        }
        //
        sbBody.append("</ROW>");
        sbBody.append("</MASTER>");

        if ("Y".equalsIgnoreCase(SEND_DETAIL_LTT)) {
            int iCounter = 0;
            sbBody.append("<DETAIL>");
            Iterator iter = listLTTCtiet.iterator();
            LTTCTietVO lttCtietVO = null;
            while (iter.hasNext()) {
                lttCtietVO = (LTTCTietVO)iter.next();
                iCounter++;
                sbBody.append("<ROW>");

                sbBody.append("<LTT_ID>");
                sbBody.append(lttVO.getId());
                sbBody.append("</LTT_ID>");

                sbBody.append("<MA_QUY_ID>");
                sbBody.append(lttCtietVO.getMa_quy());
                sbBody.append("</MA_QUY_ID>");

                sbBody.append("<TKKT_ID>");
                sbBody.append(lttCtietVO.getTkkt_ma());
                sbBody.append("</TKKT_ID>");

                sbBody.append("<DVSDNS_MA>");
                sbBody.append(lttCtietVO.getDvsdns_ma());
                sbBody.append("</DVSDNS_MA>");

                sbBody.append("<CAPNS_ID>");
                sbBody.append(lttCtietVO.getCapns_ma());
                sbBody.append("</CAPNS_ID>");

                sbBody.append("<CHUONGNS_ID>");
                sbBody.append(lttCtietVO.getChuongns_ma());
                sbBody.append("</CHUONGNS_ID>");

                sbBody.append("<NGANHKT_ID>");
                sbBody.append(lttCtietVO.getNganhkt_ma());
                sbBody.append("</NGANHKT_ID>");

                sbBody.append("<NDKT_ID>");
                sbBody.append(lttCtietVO.getNdkt_ma());
                sbBody.append("</NDKT_ID>");

                sbBody.append("<DBHC_ID>");
                sbBody.append(lttCtietVO.getDbhc_ma());
                sbBody.append("</DBHC_ID>");

                sbBody.append("<CTMT_ID>");
                sbBody.append(lttCtietVO.getCtmt_ma());
                sbBody.append("</CTMT_ID>");

                sbBody.append("<DU_PHONG_ID>");
                sbBody.append(lttCtietVO.getDu_phong_ma());
                sbBody.append("</DU_PHONG_ID>");

                sbBody.append("<MA_NGUON>");
                sbBody.append(lttCtietVO.getMa_nguon());
                sbBody.append("</MA_NGUON>");

                sbBody.append("<KB_ID>");
                sbBody.append(lttCtietVO.getKb_ma());
                sbBody.append("</KB_ID>");

                sbBody.append("<SO_TIEN>");
                sbBody.append(lttCtietVO.getSo_tien());
                sbBody.append("</SO_TIEN>");

                sbBody.append("<DIEN_GIAI>");
                sbBody.append(StringUtil.xmlSpeReplace(lttCtietVO.getDien_giai()));
                sbBody.append("</DIEN_GIAI>");

                sbBody.append("<SO_TIEN_NO>");

                sbBody.append("</SO_TIEN_NO>");

                sbBody.append("<SO_TIEN_CO>");
                sbBody.append(StringUtil.xmlSpeReplace(lttCtietVO.getDien_giai()));
                sbBody.append("</SO_TIEN_CO>");

                sbBody.append("<SOURCE_MODULE/>");

                sbBody.append("<STT_DONG>");
                sbBody.append(iCounter);
                sbBody.append("</STT_DONG>");

                sbBody.append("</ROW>");

            }
            sbBody.append("</DETAIL>");
        } else {
            sbBody.append("<DETAIL/>");
        }
        sbBody.append("</BODY>");


        //Sinh msg_id
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        if (lttVO.getMsg_id() != null && !"".equals(lttVO.getMsg_id()) &&
            !"DHV".equals(lttVO.getMsg_id())) {
            strMsgID = lttVO.getMsg_id();
        } else {
            strMsgID =
                    ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);
        }

        //Tao msg header
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setVersion(AppConstants.VERSION_MSG);
        msgHeader.setSender_code(APP_SEND_CODE);
        msgHeader.setSender_name(APP_SEND_NAME);
        msgHeader.setReveiver_code(APP_RECIEVE_CODE);
        msgHeader.setReceiver_name(APP_RECIEVE_NAME);
        msgHeader.setTran_code(TRAN_CODE);
        msgHeader.setSend_date(strNgayDuyet);
        msgHeader.setMsg_id(strMsgID);
        msgHeader.setMsg_refid("");
        msgHeader.setSpare3(strSpare3);

        //Noi header va body -> tao msg
        sbMsg.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sbMsg.append("<DATA>");
        sbMsg.append(msgHeader.getHeader());
        sbMsg.append(sbBody).toString();
        sbMsg.append("<SECURITY>");
        sbMsg.append("<SIGNATURE>");
        sbMsg.append("</SIGNATURE>");
        sbMsg.append("</SECURITY>");
        sbMsg.append("</DATA>");

        //Build log msg
        MsgQueueDAO msgDAO = new MsgQueueDAO(conn);
        MsgQueueVO msg = new MsgQueueVO();

        msg.setMsg(sbMsg.toString());
        msg.setMsg_id(strMsgID);
        msg.setTran_code(TRAN_CODE);
        msg.setSender_code(APP_SEND_CODE);
        msg.setReceiver_code(APP_RECIEVE_CODE);
        msg.setMt_id(strID);
        msg.setStatus("0");
        
        msgDAO.insert(msg);
        return strMsgID;
    }

    /**
     * @see: Lay tham so hach toan tu CSDL
     * @param:
     * */
    //    private void setThamSoHeThong() throws Exception {
    //        ThamSoHThongDAO tshtDAO = new ThamSoHThongDAO(conn);
    //        String strWhereClauseTS = "";
    //
    //        strWhereClauseTS = " and ten_ts in (?,?,?,?,?,?,?)";
    //
    //        Vector vtParam = new Vector();
    //
    //        vtParam.add(new Parameter("GL_SOURCE_MODULE", true));
    //        vtParam.add(new Parameter("MQ_HOSTNAME", true));
    //        vtParam.add(new Parameter("MQ_CHANEL", true));
    //        vtParam.add(new Parameter("MQ_PORT", true));
    //        vtParam.add(new Parameter("MQ_MANAGER_NAME", true));
    //        vtParam.add(new Parameter("MQ_NAME", true));
    //        vtParam.add(new Parameter("SEND_DETAIL_LTT", true));
    //
    //        Collection tsList = tshtDAO.getThamSoList(strWhereClauseTS, vtParam);
    //        Iterator iter = tsList.iterator();
    //        ThamSoHThongVO tsVO = null;
    //        String strTenTS = "";
    //        while (iter.hasNext()) {
    //            tsVO = (ThamSoHThongVO)iter.next();
    //            strTenTS = tsVO.getTen_ts();
    //            if (strTenTS.equalsIgnoreCase("GL_SOURCE_MODULE")) {
    //                GL_SOURCE_MODULE = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("MQ_HOSTNAME")) {
    //                MQ_HOSTNAME = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("MQ_CHANEL")) {
    //                MQ_CHANEL = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("MQ_PORT")) {
    //                MQ_PORT = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("MQ_NAME")) {
    //                MQ_NAME = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("MQ_MANAGER_NAME")) {
    //                MQ_MANAGER_NAME = tsVO.getGiatri_ts();
    //            } else if (strTenTS.equalsIgnoreCase("SEND_DETAIL_LTT")) {
    //                SEND_DETAIL_LTT = tsVO.getGiatri_ts();
    //            }
    //
    //        }
    //    }

    public static String LongToStrDateDDMMYYYY(Long nYYYYMMDD) {
        if (nYYYYMMDD == null)
            return "";
        String strDDMMYYYY = nYYYYMMDD.toString();
        if (strDDMMYYYY == null || strDDMMYYYY.equalsIgnoreCase("") ||
            strDDMMYYYY.length() != 8) {
            return "";
        } else {
            String strDate, strMonth, strYear;
            strYear = strDDMMYYYY.substring(0, 4);
            strMonth = strDDMMYYYY.substring(4, 6);
            strDate = strDDMMYYYY.substring(6, 8);
            strDDMMYYYY = strDate + "-" + strMonth + "-" + strYear;
            return strDDMMYYYY;
        }
    }


    /*
   * -------------------------------------------------------------------------
   * @see: Kiem tra LTT thuoc nhom nao?
   * - An ninh quoc phong
   * - Chi tra no
   * - Chi ca nhan va an sinh xa hoi
   * **/

    public String getNhomLTT(String strTKTN, String strDVQHNS, String strNDKT,
                             HttpSession session) throws Exception {
        if (session.getAttribute(AppConstants.APP_MA_DVQHNS_AN_NINH_SESSION) ==
            null) {
            throw new Exception("Khong tim thay tham so MA_DVQHNS_AN_NINH!");
        } else {
            String strDVQHNSChiANQP =
                session.getAttribute(AppConstants.APP_MA_DVQHNS_AN_NINH_SESSION).toString();
            if (strDVQHNSChiANQP.contains(strDVQHNS)) {
                return "01"; //ANQP
            }
        }
        if (session.getAttribute(AppConstants.APP_TK_CHI_TRA_NO_SESSION) ==
            null) {
            throw new Exception("Khong tim thay tham so TK_CHI_TRA_NO!");
        } else {
            String strTKChiTraNo =
                session.getAttribute(AppConstants.APP_TK_CHI_TRA_NO_SESSION).toString();
            String[] arrStrTKChiTraNo = strTKChiTraNo.split(",");
            for (int i = 0; i < arrStrTKChiTraNo.length; i++) {
                strTKChiTraNo =
                        arrStrTKChiTraNo[i].trim().replace("x", ".").replace("X",
                                                                             ".");
                if (strTKTN.matches(strTKChiTraNo)) {
                    return "02"; //TK chi tra no
                }
            }
        }
        if (session.getAttribute(AppConstants.APP_TK_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH_SESSION) ==
            null ||
            session.getAttribute(AppConstants.APP_NDKT_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH_SESSION) ==
            null) {
            throw new Exception("Khong tim thay tham so TK_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH hoac NDKT_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH!");
        } else {
            String strTKChiCaNhanAnSinhXH =
                session.getAttribute(AppConstants.APP_TK_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH_SESSION).toString();
            String[] arrStrTKChiCaNhanAnSinhXH =
                strTKChiCaNhanAnSinhXH.split(",");
            String strNDKTChiCaNhanAnSinhXH =
                session.getAttribute(AppConstants.APP_NDKT_CHI_CA_NHAN_VA_CSACH_AN_SINH_XH_SESSION).toString();

            strNDKTChiCaNhanAnSinhXH =
                    "'" + strNDKTChiCaNhanAnSinhXH.replace(",", "','") + "'";
            DMNDKTeDAO dmdao = new DMNDKTeDAO(conn);

            //            String[] arrStrNDKTChiCaNhanAnSinhXH =
            //                strNDKTChiCaNhanAnSinhXH.split(",");
            for (int i = 0; i < arrStrTKChiCaNhanAnSinhXH.length; i++) {
                strTKChiCaNhanAnSinhXH =
                        arrStrTKChiCaNhanAnSinhXH[i].trim().replace("x",
                                                                    ".").replace("X",
                                                                                 ".");
                if (strTKTN.matches(strTKChiCaNhanAnSinhXH)) {
                    //                    for (int j = 0; j < arrStrNDKTChiCaNhanAnSinhXH.length;
                    //                         j++) {
                    //                        strNDKTChiCaNhanAnSinhXH =
                    //                                arrStrNDKTChiCaNhanAnSinhXH[j].trim().replace("x",
                    //                                                                              ".").replace("X",
                    //                                                                                           ".");
                    //                        if (strNDKT.matches(strNDKTChiCaNhanAnSinhXH)) {
                    //                            return "03"; //Chi ca nhan va chinh sach an sinh xa hoi
                    //                        }
                    //                    }
                    String strWhere =
                        " a.ma = ? and (a.ma_cha in (" + strNDKTChiCaNhanAnSinhXH +
                        ") or a.ma in (" + strNDKTChiCaNhanAnSinhXH + "))";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(strNDKT, true));
                    DMNDKTeVO dmvo = dmdao.getDMNDKTe(strWhere, vParam);
                    if (dmvo != null) {
                        return "03"; //Chi ca nhan va chinh sach an sinh xa hoi
                    }
                }
            }
        }
        return "00";
    }
    /*---------------------------------------------------------------------------
  * @see: Kiem tra LTT co can kiem tra dieu hanh von khong
  * Neu LTT thuoc nhom "00" -> Kiem tra so tien vuot han muc se kiem tra dieu hanh von
  */

    public boolean checkDieuHanhVon(String strTKTN, String strDVQHNS,
                                    String strNDKT, BigDecimal lTongSoTien,
                                    String strMaNH, HttpSession session,
                                    Connection conn) throws Exception {
        String strNhomLTT = getNhomLTT(strTKTN, strDVQHNS, strNDKT, session);
        if ("00".equals(strNhomLTT)) {
            if (session.getAttribute(AppConstants.APP_HAN_MUC_CHAN_LTT_DI_TRONG_DHV_SESSION) ==
                null) {
                throw new Exception("Khong tim thay tham so HAN_MUC_CHAN_LTT_DI_TRONG_DHV!");
            } else {
                String strDVQHNSChiANQP =
                    session.getAttribute(AppConstants.APP_HAN_MUC_CHAN_LTT_DI_TRONG_DHV_SESSION).toString();
                Long lHanMuc = new Long(strDVQHNSChiANQP);
                if (lTongSoTien.longValue() < lHanMuc.longValue()) {
                    return true; //Lenh khong can check dieu hanh von
                } else {
                    String strHanMucSoDu = "0";
                    Long lSoDuOnline = null;
                    Long lHanMucSoDu = null;
                    //Lay tham so han muc so du
                    if ("201".equals(strMaNH)) {
                        strHanMucSoDu =
                                session.getAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_ICB_SESSION).toString();
                    } else if ("202".equals(strMaNH)) {
                        strHanMucSoDu =
                                session.getAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_BIDV_SESSION).toString();
                    } else if ("204".equals(strMaNH)) {
                        strHanMucSoDu =
                                session.getAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_AGR_SESSION).toString();
                    } else if ("203".equals(strMaNH)) {
                        strHanMucSoDu =
                                session.getAttribute(AppConstants.APP_HAN_MUC_DIEU_HANH_VON_VCB_SESSION).toString();
                    }
                    lHanMucSoDu = new Long(strHanMucSoDu);
                    //Lay so du online
                    TTSPUtils ttspUtils = new TTSPUtils(conn);
                    lSoDuOnline =
                            ttspUtils.getSoDuOnlineTKTheoHThongNH(strMaNH, "");
                    //Kiem tra neu so du duoi han muc thi kiem tra dieu hanh von
                    if (lHanMucSoDu.longValue() > lSoDuOnline.longValue()) {
                        return false; //Lenh can check dieu hanh von
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            AppDAO dao = new AppDAO();
            Connection conn = dao.getConnectionTest();
            SendLTToan send = new SendLTToan(conn, null);
            send.sendMessageToTabmis("1420310300000267", "0", "Y");
//            TTSPUtils ttspUtils = new TTSPUtils(conn);
//            String strNgayHienTai =
//                StringUtil.DateToString(new Date(), "yyyyMMdd");
//            String strNgayLamViecDauThang =
//                StringUtil.DateToString(new Date(), "yyyyMM") + "01";
//            strNgayLamViecDauThang =
//                    ttspUtils.getNgayLamViec(strNgayLamViecDauThang);
//            if (strNgayHienTai.equals(strNgayLamViecDauThang)) {
////                String strHachToanTheoNgayKSNH = "Y";
//            }
//
//
//            String strNgayTT = "dd-mm-yyyy";
//            String strSpare3 =
//                strNgayTT.substring(6, 10) + strNgayTT.substring(3, 5) +
//                strNgayTT.substring(0, 2);

            //            String str = "11&110123&4567";
            //          String strMsg = str.toString().replace("&", " ");
            //            String str2;
            //            String str3;
            //            String str4; //04;45;5, 12
            //            str2 = str.substring(5, 12);
            //            str3 = str.substring(4, 1);
            //            str4 = str.substring(6, 7);
            //            System.out.println(str2);
            //            System.out.println(str3);
//            System.out.println(strSpare3);


            //            AppDAO dao = new AppDAO();
            //            Connection conn = dao.getConnectionTest();
            //            SendLTToan sendLQToan = new SendLTToan(conn);
            //            // sendLQToan.sendMessage("1320390000000015", "CT");
            //            sendLQToan.sendMessageToTabmis("1220110300000519", "0003");
            //            String s = "xxxxxxxxxx";
            //            while (true) {
            //                try {
            //                    s += s;
            //                    System.out.println("Size: " + s.length());
            //                } catch (Exception ex) {
            //                    s += "x";
            //                }
            //            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
