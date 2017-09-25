package com.seatech.ttsp.proxy.giaodien;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangHOVO;
import com.seatech.ttsp.dts.DTSoatDAO;
import com.seatech.ttsp.dts.DTSoatVO;
import com.seatech.ttsp.dts.form.DTSoatForm;
import com.seatech.ttsp.ltt.LTTDAO;
import com.seatech.ttsp.ltt.LTTVO;
import com.seatech.ttsp.proxy.giaodien.mq.MQClient;
import com.seatech.ttsp.thamso.ThamSoHThongVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;


public class SendDTS {
    Connection conn = null;

    public SendDTS(Connection conn) {
        this.conn = conn;
    }
    private String APP_SEND_CODE = AppConstants.APP_SEND_CODE;
    private String APP_SEND_NAME = AppConstants.APP_SEND_NAME;
    private String APP_RECEIVE_CODE = "";
    private String SOURCE_APP_CODE;
    private String SOURCE_APP_NAME;
    private String DES_APP_CODE;
    private String DES_APP_NAME;
    private String MSG_TYPE_CODE;
    private String MSG_TYPE_NAME;

    private String MQ_HOSTNAME;
    private String MQ_CHANEL;
    private String MQ_PORT;
    private String MQ_MANAGER_NAME;
    private String MQ_NAME;
    private String strTranCode;
    private String strTranName;

    /**
     * Put msg vao truc tich hop
     * */
    DMNHangHOVO dmnhho = null;

    public String sendDTSToBank(DTSoatForm f,
                                Collection collSysParam, String strNgayDuyet) throws Exception {
        MsgVO msg = null;
        MsgDAO msgDAO = null;
        String strMsgID = null;
        try {
            getThamSoHThong(collSysParam);

            //Get bank info
            DMNHangDAO dmnhDAO = new DMNHangDAO(conn);
            Vector vtParam = new Vector();
            vtParam.add(new Parameter(f.getMa_don_vi_nhan_tra_soat().substring(2,
                                                                               5),
                                      true));
            dmnhho = dmnhDAO.getDMNHangHO(" and a.ma_dv = ? ", vtParam);
            if (dmnhho == null) {
                throw TTSPException.createException("TTSP-1009",
                                                    "Kh&#244;ng t&#236;m th&#7845;y th&#244;ng tin ng&#226;n h&#224;ng nh&#7853;n");
            }
            APP_RECEIVE_CODE = dmnhho.getMa_ung_dung();
            //Get msg_id
            TTSPUtils ttspUtils = new TTSPUtils(conn);
            strMsgID =
                    ttspUtils.getMsgLTTID(this.APP_SEND_CODE, dmnhho.getMa_ung_dung());

            //Build msg
            String strMsg = buildMessage(strMsgID, f, strNgayDuyet);
            //Build log msg
            msgDAO = new MsgDAO(conn);
            msg = new MsgVO();

            msg.setMsg_content(strMsg);
            msg.setMsg_id(strMsgID);
            msg.setMsg_type(strTranCode);
            msg.setSender(APP_SEND_CODE);
            msg.setReciever(APP_RECEIVE_CODE);
            //Put msg to queue
            MQClient client = new MQClient();
            client.putMsgToQueue(strMsg, MQ_HOSTNAME, MQ_CHANEL, MQ_PORT,
                                 MQ_MANAGER_NAME, MQ_NAME);
            //Ghi log
            try {
                msgDAO.insert(msg);
            } catch (Exception e) {
                Exception ex =
                    new Exception("Loi ghi log msg khi gui DTS vao HTTH(com.seatech.ttsp.proxy.giaodien.bank.SendDTS.sendMessage()): " +
                                  e.getMessage());
                ex.printStackTrace();
            }
            return strMsgID;

        } catch (Exception ex) {
            if (msg != null) {
                msg.setError_code("TTSP-1009");
                if (ex.getMessage().length() > 200) {
                    msg.setError_desc(ex.getMessage().substring(0, 200));
                } else {
                    msg.setError_desc(ex.getMessage());
                }
                msgDAO.insert(msg);
            }
            throw new Exception("com.seatech.ttsp.proxy.giaodien.bank.SendDTS.sendMessage(): " +
                                ex);
        }
    }

    /**
     * @see: Build msg theo chuan put vao truc tich hop
     * */
    private String buildMessage(String strMsgID,
                                DTSoatForm f, String strNgayDuyet) throws Exception {


        if (f.getDts_id() == null || "".equals(f.getDts_id())) {
            if (f.getLtt_id() == null || "".equals(f.getLtt_id())) {
                strTranCode = "199";
                strTranName = "Dien tra soat tu do";
            } else {
                strTranCode = "195";
                strTranName = "Dien tra soat hoi";
            }
        } else {
            strTranCode = "196";
            strTranName = "Dien tra soat tra loi";
        }
        LTTVO lttVO = null;
        LTTDAO lttDAO = new LTTDAO(conn);
        Vector vtParam2;
        boolean isLenhDi = false;
        boolean isDTSDi = false;

        DTSoatVO dtsVO = null;
        DTSoatDAO dtsDAO = new DTSoatDAO(conn);

        if (f.getDts_id() != null && !"".equals(f.getDts_id())) {
            if ("701".equals(f.getDts_id().substring(2, 5))) {
                isDTSDi = true;
            }
            vtParam2 = new Vector();
            vtParam2.add(new Parameter(f.getDts_id(), true));
            dtsVO = dtsDAO.getDTS(" and a.id = ? ", vtParam2);
        }
        if (f.getLtt_id() != null && !"".equals(f.getLtt_id())) {
            if ("701".equals(f.getLtt_id().substring(2, 5))) {
                isLenhDi = true;
            }
            vtParam2 = new Vector();
            vtParam2.add(new Parameter(f.getLtt_id(), true));
            lttVO = lttDAO.getLTTForHachToan(" and a.id = ? ", vtParam2);
        } else {
            if (dtsVO != null) {
                if (dtsVO.getLtt_id() != null) {
                    vtParam2 = new Vector();
                    vtParam2.add(new Parameter(dtsVO.getLtt_id(), true));
                    lttVO = lttDAO.getLTTForHachToan(" and a.id = ? ", vtParam2);
                }
            }
        }

        StringBuffer szGL = new StringBuffer();
        szGL.append(AppConstants.XML_VERSION);
        szGL.append("<DATA>");
        //Tao msg header
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setVersion(AppConstants.VERSION_MSG);
        msgHeader.setSender_code(AppConstants.APP_SEND_CODE);
        msgHeader.setSender_name(AppConstants.APP_SEND_NAME);
        msgHeader.setReveiver_code(dmnhho.getMa_ung_dung());
        msgHeader.setReceiver_name(dmnhho.getTen_ung_dung());
        msgHeader.setTran_code(strTranCode);
        msgHeader.setSend_date(StringUtil.DateToString(new Date(),
                                                       AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        msgHeader.setMsg_id(strMsgID);
        msgHeader.setMsg_refid("");

        szGL.append(msgHeader.getHeader());
        //Body
        szGL.append("<BODY>");
        szGL.append("<MASTER>");
        szGL.append("<ROW>");

        szGL.append("<ID>");
        szGL.append(f.getId());
        szGL.append("</ID>");

        szGL.append("<LTT_ID>");
        szGL.append(f.getLtt_id() == null ? "" : f.getLtt_id());
        szGL.append("</LTT_ID>");

        szGL.append("<DTS_ID>");
        szGL.append(f.getDts_id() == null ? "" : f.getDts_id());
        szGL.append("</DTS_ID>");

        szGL.append("<NHKB_NHAN>");
        szGL.append(f.getMa_don_vi_nhan_tra_soat());
        szGL.append("</NHKB_NHAN>");

        szGL.append("<NHKB_CHUYEN>");
        szGL.append(f.getMa_don_vi_tra_soat());
        szGL.append("</NHKB_CHUYEN>");

        szGL.append("<NOIDUNG>");
        szGL.append(f.getNoidung() == null ? "" : StringUtil.xmlSpeReplace(f.getNoidung().replace("\n", "").replace("\r", "")));
        szGL.append("</NOIDUNG>");

        szGL.append("<NOIDUNG_TRALOI>");
        szGL.append(f.getNoidung_traloi() == null ? "" :
                    StringUtil.xmlSpeReplace(f.getNoidung_traloi().replace("\n", "").replace("\r", "")));
        szGL.append("</NOIDUNG_TRALOI>");

        szGL.append("<THONG_TIN_KHAC>");
        szGL.append(f.getThong_tin_khac() == null ? "" :
                    StringUtil.xmlSpeReplace(f.getThong_tin_khac().replace("\n", "").replace("\r", "")));
        szGL.append("</THONG_TIN_KHAC>");

        szGL.append("<TTV_NHAN>");
        szGL.append(f.getMa_ttv_nhan() == null ? "" : f.getMa_ttv_nhan());
        szGL.append("</TTV_NHAN>");

        szGL.append("<NGAY_NHAN>");
        if (f.getNgay_nhan() == null || "".equals(f.getNgay_nhan())) {
            szGL.append(StringUtil.DateToString(new Date(),
                                                AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        } else {
            szGL.append(f.getNgay_nhan().replace("/", "-"));
        }
        szGL.append("</NGAY_NHAN>");

        szGL.append("<KTT_DUYET>");
        szGL.append(f.getKtt_duyet() == null ? "" : f.getKtt_duyet());
        szGL.append("</KTT_DUYET>");

        szGL.append("<NGAY_DUYET>");
        if (f.getNgay_duyet() != null && !"".equals(f.getNgay_duyet())) {
            szGL.append(f.getNgay_duyet());
        } else {
            szGL.append(StringUtil.DateToString(new Date(),
                                                AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        }
        //        szGL.append(f.getNgay_duyet());
        szGL.append("</NGAY_DUYET>");

        szGL.append("<NGUOI_NHAP_NH>");
        szGL.append("</NGUOI_NHAP_NH>");

        szGL.append("<NGAY_NHAP_NH>");
        szGL.append("</NGAY_NHAP_NH>");

        szGL.append("<NGUOI_KS_NH>");
        szGL.append("</NGUOI_KS_NH>");

        szGL.append("<NGAY_KS_NH>");
        szGL.append("</NGAY_KS_NH>");

        szGL.append("<NGAY_LAP_LTT>");
        if (lttVO != null) {
            if (lttVO.getNgay_nhan() != null &&
                !"".equals(lttVO.getNgay_nhan())) {
                szGL.append(lttVO.getNgay_nhan().substring(0, 10));
            }
        }
        szGL.append("</NGAY_LAP_LTT>");

        szGL.append("<SO_TCHIEU_LTT>");
        if (lttVO != null) {
            szGL.append(lttVO.getSo_tham_chieu_gd() == null ? lttVO.getId() :
                        lttVO.getSo_tham_chieu_gd());
        }
        szGL.append("</SO_TCHIEU_LTT>");

        szGL.append("<NGAY_TAO_195>");
        if (dtsVO != null) {
            if (dtsVO.getNgay_nhan() != null &&
                !"".equals(dtsVO.getNgay_nhan())) {
                if (dtsVO.getNgay_nhan().length() == 10) {
                    szGL.append(dtsVO.getNgay_nhan().replace("/", "-"));
                } else if (dtsVO.getNgay_nhan().length() > 10) {
                    szGL.append(dtsVO.getNgay_nhan().substring(0,
                                                               10).replace("/",
                                                                           "-"));
                } else {
                    szGL.append(StringUtil.DateToString(new Date(),
                                                        AppConstants.DATE_FORMAT_SEND_ORDER));
                }
            } else {
                szGL.append(StringUtil.DateToString(new Date(),
                                                    AppConstants.DATE_FORMAT_SEND_ORDER));
            }
        }
        szGL.append("</NGAY_TAO_195>");

        szGL.append("<SO_TCHIEU_195>");
        if (dtsVO != null) {
            szGL.append(dtsVO.getSo_tchieu() == null ? "" :
                        dtsVO.getSo_tchieu());
        }
        szGL.append("</SO_TCHIEU_195>");

        szGL.append("<SO_TCHIEU>");
        szGL.append(f.getId() == null ? "" : f.getId());
        szGL.append("</SO_TCHIEU>");

        szGL.append("<NH_TK_CHUYEN>");
        if (strTranCode.equals("196") && dtsVO != null) {
            if (isDTSDi) {
                szGL.append(dtsVO.getNh_tk_chuyen() == null ? "" :
                            dtsVO.getNh_tk_chuyen());
            } else {
                szGL.append(dtsVO.getNh_tk_nhan() == null ? "" :
                            dtsVO.getNh_tk_nhan());
            }
        } else if (strTranCode.equals("195") && lttVO != null) {
            if (isLenhDi) {
                szGL.append(lttVO.getMa_nhkb_chuyen_cm() == null ? "" :
                            lttVO.getMa_nhkb_chuyen_cm());
            } else {
                szGL.append(lttVO.getMa_nhkb_nhan_cm() == null ? "" :
                            lttVO.getMa_nhkb_nhan_cm());
            }

        }
        szGL.append("</NH_TK_CHUYEN>");

        szGL.append("<NH_TK_NHAN>");
        if (strTranCode.equals("196") && dtsVO != null) {
            if (isDTSDi) {
                szGL.append(dtsVO.getNh_tk_nhan() == null ? "" :
                            dtsVO.getNh_tk_nhan());
            } else {
                szGL.append(dtsVO.getNh_tk_chuyen() == null ? "" :
                            dtsVO.getNh_tk_chuyen());
            }
        } else if (strTranCode.equals("195") && lttVO != null) {
            if (isLenhDi) {
                szGL.append(lttVO.getMa_nhkb_nhan_cm() == null ? "" :
                            lttVO.getMa_nhkb_nhan_cm());
            } else {
                szGL.append(lttVO.getMa_nhkb_chuyen_cm() == null ? "" :
                            lttVO.getMa_nhkb_chuyen_cm());
            }

        }
        szGL.append("</NH_TK_NHAN>");
        
        szGL.append("<MA_NT>");
        if (strTranCode.equals("195") && lttVO != null) {
          szGL.append(lttVO.getMa_nt());
        }else if (strTranCode.equals("196") && dtsVO != null) {
          szGL.append(dtsVO.getLoai_tien()); 
        }        
        szGL.append("</MA_NT>");
      
        szGL.append("</ROW>");
        szGL.append("</MASTER>");
        szGL.append("</BODY>");

        szGL.append("<SECURITY>");
        szGL.append("<SIGNATURE>");
        szGL.append("</SIGNATURE>");
        szGL.append("</SECURITY>");
        szGL.append("</DATA>");

        return szGL.toString();

    }

    /**
     *@see: Get tham so he thong
     * */
    public void getThamSoHThong(Collection collThamSoList) throws Exception {
        Iterator iter = collThamSoList.iterator();
        ThamSoHThongVO tsVO = null;
        int nCount = 0;
        while (iter.hasNext()) {
            if (nCount >= 11)
                break;
            tsVO = (ThamSoHThongVO)iter.next();
            if (tsVO != null) {
                if (AppConstants.PARAM_DES_APP_CODE.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.DES_APP_CODE = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_DES_APP_NAME.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.DES_APP_NAME = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_SOURCE_APP_CODE.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.SOURCE_APP_CODE = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_SOURCE_APP_NAME.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.SOURCE_APP_NAME = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MSG_TYPE_CODE_DTS.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.MSG_TYPE_CODE = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MSG_TYPE_NAME_DTS.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.MSG_TYPE_NAME = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MQ_CHANEL.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.MQ_CHANEL = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MQ_HOSTNAME.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.MQ_HOSTNAME = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MQ_MANAGER_NAME.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.MQ_MANAGER_NAME = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MQ_NAME.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.MQ_NAME = tsVO.getGiatri_ts();
                    nCount++;
                } else if (AppConstants.PARAM_MQ_PORT.equalsIgnoreCase(tsVO.getTen_ts().trim())) {
                    this.MQ_PORT = tsVO.getGiatri_ts();
                    nCount++;
                }
            }
        }
    }

}
