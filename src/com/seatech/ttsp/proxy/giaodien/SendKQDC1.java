package com.seatech.ttsp.proxy.giaodien;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DChieu2DAO;
import com.seatech.ttsp.dchieu.DuyetXNDChieu2VO;
import com.seatech.ttsp.dchieu.KQDCChiTietVO;
import com.seatech.ttsp.dchieu.KQDChieu1VO;
import com.seatech.ttsp.dchieu.KQDChieu3CTietDAO;
import com.seatech.ttsp.dchieu.KQDChieu3CTietVO;
import com.seatech.ttsp.dchieu.KQDChieu3DAO;
import com.seatech.ttsp.dchieu.KQDChieu3VO;
import com.seatech.ttsp.dchieungoaite.DChieuNgoaiTeDAO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangHOVO;
import com.seatech.ttsp.proxy.giaodien.mq.MQClient;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;


public class SendKQDC1 {
    private String MQ_HOSTNAME;
    private String MQ_CHANEL;
    private String MQ_PORT;
    private String MQ_MANAGER_NAME;
    private String MQ_NAME;

    private String APP_SEND_CODE = AppConstants.APP_SEND_CODE;
    private String APP_SEND_NAME = AppConstants.APP_SEND_NAME;
    private String APP_RECIEVE_CODE;
    private String APP_RECIEVE_NAME;

    private String TRAN_CODE_1 = "065";
    private String TRAN_CODE_3 = "069";
    private String TRAN_CODE_2 = "067";

    Connection conn = null;

    public SendKQDC1(Connection conn) {
        this.conn = conn;
    }

    public String sendMessageDC1(String strKQDCID,
                                 String user_id) throws Exception {
        String strMsgID = "";
        StringBuffer sbMsg = new StringBuffer();

        setThamSoQuyetToan();
        DChieu1DAO kqdcDAO = new DChieu1DAO(conn);
        Vector vtParam = new Vector();
        //      vtParam.add(new Parameter(strKQDCID, true));
        String strKQDC1 = "and a.id ='" + strKQDCID + "' AND app_type ='TTSP'";
        KQDChieu1VO kqdcVO = kqdcDAO.getKQDC1(strKQDC1, null);
        //
        String strMaNH = kqdcVO.getReceive_bank();
        //
        vtParam = new Vector();
        vtParam.add(new Parameter(strMaNH.substring(2, 5), true));
        DMNHangDAO dmdao = new DMNHangDAO(conn);
        DMNHangHOVO dmvo = dmdao.getDMNHangHO(" and a.ma_dv = ? ", vtParam);

        if (dmvo == null) {
            throw (new TTSPException()).createException("TTSP-1009",
                                                        "Kh&#244;ng t&#236;m th&#7845;y m&#227; &#7913;ng d&#7909;ng nh&#7853;n t&#432;&#417;ng &#7913;ng v&#7899;i m&#227; ng&#226;n h&#224;ng nh&#7853;n\n Duy&#7879;t l&#7879;nh kh&#244;ng th&#224;nh c&#244;ng.");
        }
        APP_RECIEVE_CODE = dmvo.getMa_ung_dung();
        APP_RECIEVE_NAME = dmvo.getTen_ung_dung();
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        strMsgID =
                ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);

        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setVersion(AppConstants.VERSION_MSG);
        msgHeader.setSender_code(APP_SEND_CODE);
        msgHeader.setSender_name(APP_SEND_NAME);
        msgHeader.setReveiver_code(APP_RECIEVE_CODE);
        msgHeader.setReceiver_name(APP_RECIEVE_NAME);
        msgHeader.setTran_code("065");
        msgHeader.setMsg_id(strMsgID);
        msgHeader.setMsg_refid(kqdcVO.getMsg_refid());

        sbMsg.append(AppConstants.XML_VERSION);
        sbMsg.append("<DATA>");
        sbMsg.append(msgHeader.getHeader());

        sbMsg.append("<BODY>");
        sbMsg.append("<MT_ID>");
        sbMsg.append(strKQDCID);
        sbMsg.append("</MT_ID>");
        sbMsg.append("<BK_ID>");
        sbMsg.append(kqdcVO.getBk_id());
        sbMsg.append("</BK_ID>");
        sbMsg.append("<KET_QUA>");
        sbMsg.append(kqdcVO.getKet_qua());
        sbMsg.append("</KET_QUA>");
        sbMsg.append("<LAN_DC>");
        sbMsg.append(kqdcVO.getLan_dc());
        sbMsg.append("</LAN_DC>");
        sbMsg.append("<NGAY_DC>");
        sbMsg.append(kqdcVO.getNgay_dc());
        sbMsg.append("</NGAY_DC>");
        sbMsg.append("<SEND_BANK>");
        sbMsg.append(kqdcVO.getSend_bank());
        sbMsg.append("</SEND_BANK>");
        sbMsg.append("<RECEIVE_BANK>");
        sbMsg.append(kqdcVO.getReceive_bank());
        sbMsg.append("</RECEIVE_BANK>");
        sbMsg.append("<CREATED_DATE>");
        sbMsg.append(kqdcVO.getCreated_date());
        sbMsg.append("</CREATED_DATE>");
        sbMsg.append("<CREATOR>");
        sbMsg.append(kqdcVO.getMa_nsd());
        sbMsg.append("</CREATOR>");
        sbMsg.append("<MANAGER>");
        sbMsg.append(user_id);
        sbMsg.append("</MANAGER>");
        sbMsg.append("<VERIFIED_DATE>");
        sbMsg.append(StringUtil.DateToString(new Date(),
                                             AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        sbMsg.append("</VERIFIED_DATE>");

        sbMsg.append("<SODU_KBNN>");
        sbMsg.append(kqdcVO.getSodu_kbnn()==null?"0":kqdcVO.getSodu_kbnn());
        sbMsg.append("</SODU_KBNN>");

        sbMsg.append("<CHENH_LECH>");
        sbMsg.append(kqdcVO.getChenh_lech());
        sbMsg.append("</CHENH_LECH>");

        Collection colKQDCCTiet = null;
        KQDCChiTietVO kqCTietVO = null;
        if (!"0".equals(kqdcVO.getKet_qua())) {
            vtParam = new Vector();
            //        vtParam.add(new Parameter(strKQDCID,true));
            String strKQDCCTiet =
                "and a.BKQ_ID ='" + strKQDCID + "' AND app_type ='TTSP'";
            colKQDCCTiet = kqdcDAO.getKQDChieuCtiet(strKQDCCTiet, null);
        }
        sbMsg.append("<KB_THIEU>");
        sbMsg.append("<LTT>");

        if (colKQDCCTiet != null) {
            Iterator iter1 = colKQDCCTiet.iterator();
            while (iter1.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter1.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if ("103".equals(mt_type) && "0".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    if (kqCTietVO.getSend_date() == null ||
                        "".equals(kqCTietVO.getSend_date())) {
                        sbMsg.append("<SEND_DATE/>");
                    } else {
                        sbMsg.append("<SEND_DATE>");
                        sbMsg.append(kqCTietVO.getSend_date());
                        sbMsg.append("</SEND_DATE>");
                    }
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    if (kqCTietVO.getF20() == null ||
                        "".equals(kqCTietVO.getF20())) {
                        sbMsg.append("<CTU_GOC/>");
                    } else {
                        sbMsg.append("<CTU_GOC>");
                        sbMsg.append(kqCTietVO.getF20());
                        sbMsg.append("</CTU_GOC>");
                    }
                    if (kqCTietVO.getNgay_ct() == null ||
                        "".equals(kqCTietVO.getNgay_ct())) {
                        sbMsg.append("<NGAY_CT/>");
                    } else {
                        sbMsg.append("<NGAY_CT>");
                        sbMsg.append(kqCTietVO.getNgay_ct());
                        sbMsg.append("</NGAY_CT>");
                    }

                    sbMsg.append("<TTIEN>");
                    sbMsg.append(kqCTietVO.getF32as3());
                    sbMsg.append("</TTIEN>");
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU/>");
                    } else {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }
                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</LTT>");
        sbMsg.append("<DTS>");

        if (colKQDCCTiet != null) {
            Iterator iter2 = colKQDCCTiet.iterator();
            while (iter2.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter2.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if (("195".equals(mt_type) || "196".equals(mt_type)) &&
                    "0".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    if (kqCTietVO.getNgay_ts() == null ||
                        "".equals(kqCTietVO.getNgay_ts())) {
                        sbMsg.append("<NGAY_TS/>");
                    } else {
                        sbMsg.append("<NGAY_TS>");
                        sbMsg.append(kqCTietVO.getNgay_ts());
                        sbMsg.append("</NGAY_TS>");
                    }
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<TS_TYPE>");
                    sbMsg.append(kqCTietVO.getMt_type());
                    sbMsg.append("</TS_TYPE>");
                    if (kqCTietVO.getF21() == null ||
                        "".equals(kqCTietVO.getF21())) {
                        sbMsg.append("<F21/>");
                    } else {
                        sbMsg.append("<F21>");
                        sbMsg.append(kqCTietVO.getF21());
                        sbMsg.append("</F21>");
                    }
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU/>");
                    } else {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }

                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</DTS>");
        sbMsg.append("</KB_THIEU>");

        sbMsg.append("<KB_THUA>");
        sbMsg.append("<LTT>");

        if (colKQDCCTiet != null) {
            Iterator iter3 = colKQDCCTiet.iterator();
            while (iter3.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter3.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if ("103".equals(mt_type) && "1".equals(tthai)) {
                    sbMsg.append("<ROW>");

                    if (kqCTietVO.getSend_date() == null ||
                        "".equals(kqCTietVO.getSend_date())) {
                        sbMsg.append("<SEND_DATE/>");
                    } else {
                        sbMsg.append("<SEND_DATE>");
                        sbMsg.append(kqCTietVO.getSend_date());
                        sbMsg.append("</SEND_DATE>");
                    }
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    if (kqCTietVO.getF20() == null ||
                        "".equals(kqCTietVO.getF20())) {
                        sbMsg.append("<CTU_GOC/>");
                    } else {
                        sbMsg.append("<CTU_GOC>");
                        sbMsg.append(kqCTietVO.getF20());
                        sbMsg.append("</CTU_GOC>");
                    }
                    if (kqCTietVO.getNgay_ct() == null ||
                        "".equals(kqCTietVO.getNgay_ct())) {
                        sbMsg.append("<NGAY_CT/>");
                    } else {
                        sbMsg.append("<NGAY_CT>");
                        sbMsg.append(kqCTietVO.getNgay_ct());
                        sbMsg.append("</NGAY_CT>");
                    }

                    sbMsg.append("<TTIEN>");
                    sbMsg.append(kqCTietVO.getF32as3());
                    sbMsg.append("</TTIEN>");
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU/>");
                    } else {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }
                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</LTT>");
        sbMsg.append("<DTS>");

        if (colKQDCCTiet != null) {
            Iterator iter4 = colKQDCCTiet.iterator();
            while (iter4.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter4.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if (("195".equals(mt_type) || "196".equals(mt_type)) &&
                    "1".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    if (kqCTietVO.getNgay_ts() == null ||
                        "".equals(kqCTietVO.getNgay_ts())) {
                        sbMsg.append("<NGAY_TS/>");
                    } else {
                        sbMsg.append("<NGAY_TS>");
                        sbMsg.append(kqCTietVO.getNgay_ts());
                        sbMsg.append("</NGAY_TS>");
                    }
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<TS_TYPE>");
                    sbMsg.append(kqCTietVO.getMt_type());
                    sbMsg.append("</TS_TYPE>");
                    if (kqCTietVO.getF21() == null ||
                        "".equals(kqCTietVO.getF21())) {
                        sbMsg.append("<F21/>");
                    } else {
                        sbMsg.append("<F21>");
                        sbMsg.append(kqCTietVO.getF21());
                        sbMsg.append("</F21>");
                    }
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU/>");
                    } else {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }

                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</DTS>");
        sbMsg.append("</KB_THUA>");
        sbMsg.append("</BODY>");
        sbMsg.append("<SECURITY>");
        sbMsg.append("<SIGNATURE>");
        sbMsg.append("</SIGNATURE>");
        sbMsg.append("</SECURITY>");
        sbMsg.append("</DATA>");
        //Build log msg
        MsgDAO msgDAO = new MsgDAO(conn);
        MsgVO msg = new MsgVO();

        msg.setMsg_content(sbMsg.toString());
        msg.setMsg_id(strMsgID);
        msg.setMsg_type(TRAN_CODE_1);
        msg.setSender(APP_SEND_CODE);
        msg.setReciever(APP_RECIEVE_CODE);
        //Put msg to queue
//        if (!"0".equals(kqdcVO.getKet_qua())) {
            MQClient client = new MQClient();
            client.putMsgToQueue(sbMsg.toString(), MQ_HOSTNAME, MQ_CHANEL,
                                 MQ_PORT, MQ_MANAGER_NAME, MQ_NAME);

            //Ghi log
            try {
                msgDAO.insert(msg);
            } catch (Exception e) {
                Exception ex =
                    new Exception("Loi ghi log(com.seatech.ttsp.proxy.giaodien.bank.SendKQDC1.sendMessage()): " +
                                  e.getMessage());
                ex.printStackTrace();
            }
        return strMsgID;
    }
    
    public String sendMessageDCNgoaiTe(String strKQDCID, String user_id) throws Exception {
        String strMsgID = "";
        StringBuffer sbMsg = new StringBuffer();
  
        setThamSoQuyetToan();
        DChieuNgoaiTeDAO kqdcDAO = new DChieuNgoaiTeDAO(conn);
        Vector vtParam = new Vector();
        //      vtParam.add(new Parameter(strKQDCID, true));
        String strKQDC1 = "and a.mt_id ='" + strKQDCID + "' AND app_type ='TTSP' AND rownum = 1 ";
        KQDChieu1VO kqdcVO = kqdcDAO.getKQDCNgoaiTe(strKQDC1, null);
        //
        String strMaNH = kqdcVO.getReceive_bank();
        //
        vtParam = new Vector();
        vtParam.add(new Parameter(strMaNH.substring(2, 5), true));
        DMNHangDAO dmdao = new DMNHangDAO(conn);
        DMNHangHOVO dmvo = dmdao.getDMNHangHO(" and a.ma_dv = ? ", vtParam);
  
        if (dmvo == null) {
            throw (new TTSPException()).createException("TTSP-1009",
                                                        "Kh&#244;ng t&#236;m th&#7845;y m&#227; &#7913;ng d&#7909;ng nh&#7853;n t&#432;&#417;ng &#7913;ng v&#7899;i m&#227; ng&#226;n h&#224;ng nh&#7853;n\n Duy&#7879;t l&#7879;nh kh&#244;ng th&#224;nh c&#244;ng.");
        }
        APP_RECIEVE_CODE = dmvo.getMa_ung_dung();
        APP_RECIEVE_NAME = dmvo.getTen_ung_dung();
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        strMsgID =
                ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);
  
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setVersion(AppConstants.VERSION_MSG);
        msgHeader.setSender_code(APP_SEND_CODE);
        msgHeader.setSender_name(APP_SEND_NAME);
        msgHeader.setReveiver_code(APP_RECIEVE_CODE);
        msgHeader.setReceiver_name(APP_RECIEVE_NAME);
        msgHeader.setTran_code("085");
        msgHeader.setMsg_id(strMsgID);
        msgHeader.setMsg_refid(kqdcVO.getMsg_refid());
  
        sbMsg.append(AppConstants.XML_VERSION);
        sbMsg.append("<DATA>");
        sbMsg.append(msgHeader.getHeader());
  
        sbMsg.append("<BODY>");
        sbMsg.append("<MT_ID>");
        sbMsg.append(strKQDCID);
        sbMsg.append("</MT_ID>");
        sbMsg.append("<BK_ID>");
        sbMsg.append(kqdcVO.getBk_id());
        sbMsg.append("</BK_ID>");
        sbMsg.append("<KET_QUA>");
        sbMsg.append(kqdcVO.getKet_qua());
        sbMsg.append("</KET_QUA>");
        sbMsg.append("<LAN_DC>");
        sbMsg.append(kqdcVO.getLan_dc());
        sbMsg.append("</LAN_DC>");
        sbMsg.append("<NGAY_DC>");
        sbMsg.append(kqdcVO.getNgay_dc());
        sbMsg.append("</NGAY_DC>");
        sbMsg.append("<SEND_BANK>");
        sbMsg.append(kqdcVO.getSend_bank());
        sbMsg.append("</SEND_BANK>");
        sbMsg.append("<RECEIVE_BANK>");
        sbMsg.append(kqdcVO.getReceive_bank());
        sbMsg.append("</RECEIVE_BANK>");
        sbMsg.append("<CREATED_DATE>");
        sbMsg.append(kqdcVO.getCreated_date());
        sbMsg.append("</CREATED_DATE>");
        sbMsg.append("<CREATOR>");
        sbMsg.append(kqdcVO.getMa_nsd());
        sbMsg.append("</CREATOR>");
        sbMsg.append("<MANAGER>");
        sbMsg.append(user_id);
        sbMsg.append("</MANAGER>");
        sbMsg.append("<VERIFIED_DATE>");
        sbMsg.append(StringUtil.DateToString(new Date(),
                                             AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        sbMsg.append("</VERIFIED_DATE>");
  
        sbMsg.append("<SODU_KBNN>");
        if(kqdcVO.getSodu_kbnn() == null){
          sbMsg.append("0");
        }else{
          sbMsg.append(kqdcVO.getSodu_kbnn());
        }
        sbMsg.append("</SODU_KBNN>");
  
        sbMsg.append("<CHENH_LECH>");
        sbMsg.append(kqdcVO.getChenh_lech());
        sbMsg.append("</CHENH_LECH>");
        
        if(kqdcVO.getLy_do_chenh_lech()==null){
          sbMsg.append("<LYDO_CHENH_LECH>");
          sbMsg.append("");
          sbMsg.append("</LYDO_CHENH_LECH>");
        }else{
          sbMsg.append("<LYDO_CHENH_LECH>");
          sbMsg.append(kqdcVO.getLy_do_chenh_lech());
          sbMsg.append("</LYDO_CHENH_LECH>");
        }
      
  
        Collection colKQDCCTiet = null;
        KQDCChiTietVO kqCTietVO = null;
        if (!"0".equals(kqdcVO.getKet_qua())) {
            vtParam = new Vector();
            //        vtParam.add(new Parameter(strKQDCID,true));
            String strKQDCCTiet =
                "and a.BKQ_ID ='" + strKQDCID + "' AND app_type ='TTSP'";
            colKQDCCTiet = kqdcDAO.getKQDChieuCtiet(strKQDCCTiet, null);
        }
        sbMsg.append("<KB_THIEU>");
        sbMsg.append("<LTT>");
  
        if (colKQDCCTiet != null) {
            Iterator iter1 = colKQDCCTiet.iterator();
            while (iter1.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter1.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if ("103".equals(mt_type) && "0".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    if (kqCTietVO.getSend_date() == null ||
                        "".equals(kqCTietVO.getSend_date())) {
                        sbMsg.append("<SEND_DATE/>");
                    } else {
                        sbMsg.append("<SEND_DATE>");
                        sbMsg.append(kqCTietVO.getSend_date());
                        sbMsg.append("</SEND_DATE>");
                    }
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    if (kqCTietVO.getF20() == null ||
                        "".equals(kqCTietVO.getF20())) {
                        sbMsg.append("<CTU_GOC/>");
                    } else {
                        sbMsg.append("<CTU_GOC>");
                        sbMsg.append(kqCTietVO.getF20());
                        sbMsg.append("</CTU_GOC>");
                    }
                    if (kqCTietVO.getNgay_ct() == null ||
                        "".equals(kqCTietVO.getNgay_ct())) {
                        sbMsg.append("<NGAY_CT/>");
                    } else {
                        sbMsg.append("<NGAY_CT>");
                        sbMsg.append(kqCTietVO.getNgay_ct());
                        sbMsg.append("</NGAY_CT>");
                    }
  
                    sbMsg.append("<TTIEN>");
                    sbMsg.append(kqCTietVO.getF32as3());
                    sbMsg.append("</TTIEN>");

                    sbMsg.append("<MA_NT>");
                    sbMsg.append(kqCTietVO.getLoai_tien());
                    sbMsg.append("</MA_NT>");
                  
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU/>");
                    } else {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }
                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</LTT>");
        sbMsg.append("<DTS>");
  
        if (colKQDCCTiet != null) {
            Iterator iter2 = colKQDCCTiet.iterator();
            while (iter2.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter2.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if (("195".equals(mt_type) || "196".equals(mt_type)) &&
                    "0".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    if (kqCTietVO.getNgay_ts() == null ||
                        "".equals(kqCTietVO.getNgay_ts())) {
                        sbMsg.append("<NGAY_TS/>");
                    } else {
                        sbMsg.append("<NGAY_TS>");
                        sbMsg.append(kqCTietVO.getNgay_ts());
                        sbMsg.append("</NGAY_TS>");
                    }
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<TS_TYPE>");
                    sbMsg.append(kqCTietVO.getMt_type());
                    sbMsg.append("</TS_TYPE>");
                    if (kqCTietVO.getF21() == null ||
                        "".equals(kqCTietVO.getF21())) {
                        sbMsg.append("<F21/>");
                    } else {
                        sbMsg.append("<F21>");
                        sbMsg.append(kqCTietVO.getF21());
                        sbMsg.append("</F21>");
                    }
                    
                    sbMsg.append("<MA_NT>");
                    sbMsg.append(kqCTietVO.getLoai_tien());
                    sbMsg.append("</MA_NT>");
                    
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU/>");
                    } else {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }
  
                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</DTS>");
        sbMsg.append("</KB_THIEU>");
  
        sbMsg.append("<KB_THUA>");
        sbMsg.append("<LTT>");
  
        if (colKQDCCTiet != null) {
            Iterator iter3 = colKQDCCTiet.iterator();
            while (iter3.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter3.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if ("103".equals(mt_type) && "1".equals(tthai)) {
                    sbMsg.append("<ROW>");
  
                    if (kqCTietVO.getSend_date() == null ||
                        "".equals(kqCTietVO.getSend_date())) {
                        sbMsg.append("<SEND_DATE/>");
                    } else {
                        sbMsg.append("<SEND_DATE>");
                        sbMsg.append(kqCTietVO.getSend_date());
                        sbMsg.append("</SEND_DATE>");
                    }
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    if (kqCTietVO.getF20() == null ||
                        "".equals(kqCTietVO.getF20())) {
                        sbMsg.append("<CTU_GOC/>");
                    } else {
                        sbMsg.append("<CTU_GOC>");
                        sbMsg.append(kqCTietVO.getF20());
                        sbMsg.append("</CTU_GOC>");
                    }
                    if (kqCTietVO.getNgay_ct() == null ||
                        "".equals(kqCTietVO.getNgay_ct())) {
                        sbMsg.append("<NGAY_CT/>");
                    } else {
                        sbMsg.append("<NGAY_CT>");
                        sbMsg.append(kqCTietVO.getNgay_ct());
                        sbMsg.append("</NGAY_CT>");
                    }
  
                    sbMsg.append("<TTIEN>");
                    sbMsg.append(kqCTietVO.getF32as3());
                    sbMsg.append("</TTIEN>");
                    
                    sbMsg.append("<MA_NT>");
                    sbMsg.append(kqCTietVO.getLoai_tien());
                    sbMsg.append("</MA_NT>");
                    
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU/>");
                    } else {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }
                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</LTT>");
        sbMsg.append("<DTS>");
  
        if (colKQDCCTiet != null) {
            Iterator iter4 = colKQDCCTiet.iterator();
            while (iter4.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter4.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if (("195".equals(mt_type) || "196".equals(mt_type)) &&
                    "1".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    if (kqCTietVO.getNgay_ts() == null ||
                        "".equals(kqCTietVO.getNgay_ts())) {
                        sbMsg.append("<NGAY_TS/>");
                    } else {
                        sbMsg.append("<NGAY_TS>");
                        sbMsg.append(kqCTietVO.getNgay_ts());
                        sbMsg.append("</NGAY_TS>");
                    }
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<TS_TYPE>");
                    sbMsg.append(kqCTietVO.getMt_type());
                    sbMsg.append("</TS_TYPE>");
                    if (kqCTietVO.getF21() == null ||
                        "".equals(kqCTietVO.getF21())) {
                        sbMsg.append("<F21/>");
                    } else {
                        sbMsg.append("<F21>");
                        sbMsg.append(kqCTietVO.getF21());
                        sbMsg.append("</F21>");
                    }
                    
                    sbMsg.append("<MA_NT>");
                    sbMsg.append(kqCTietVO.getLoai_tien());
                    sbMsg.append("</MA_NT>");
                    
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU/>");
                    } else {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }
  
                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</DTS>");
        sbMsg.append("</KB_THUA>");
        sbMsg.append("</BODY>");
        sbMsg.append("<SECURITY>");
        sbMsg.append("<SIGNATURE>");
        sbMsg.append("</SIGNATURE>");
        sbMsg.append("</SECURITY>");
        sbMsg.append("</DATA>");
        //Build log msg
        MsgDAO msgDAO = new MsgDAO(conn);
        MsgVO msg = new MsgVO();
  
        msg.setMsg_content(sbMsg.toString());
        msg.setMsg_id(strMsgID);
        msg.setMsg_type("085");
        msg.setSender(APP_SEND_CODE);
        msg.setReciever(APP_RECIEVE_CODE);
        //Put msg to queue
    //        if (!"0".equals(kqdcVO.getKet_qua())) {
            MQClient client = new MQClient();
            client.putMsgToQueue(sbMsg.toString(), MQ_HOSTNAME, MQ_CHANEL,
                                 MQ_PORT, MQ_MANAGER_NAME, MQ_NAME);
  
            //Ghi log
            try {
                msgDAO.insert(msg);
            } catch (Exception e) {
                Exception ex =
                    new Exception("Loi ghi log(com.seatech.ttsp.proxy.giaodien.bank.SendKQDC1.sendMessage()): " +
                                  e.getMessage());
                ex.printStackTrace();
            }
        return strMsgID;
    }

    public String sendMessageDCTHop(String strKQDCID,
                                    String user_id) throws Exception {
        String strMsgID = "";       
        StringBuffer sbMsg = new StringBuffer();

        setThamSoQuyetToan();
        DChieu1DAO kqdcDAO = new DChieu1DAO(conn);
        Vector vtParam = new Vector();
        //      vtParam.add(new Parameter(strKQDCID, true));
        String strKQDC1 = "and a.id ='" + strKQDCID + "' AND app_type ='TTSP'";
        KQDChieu1VO kqdcVO = kqdcDAO.getKQDC1(strKQDC1, null);        
        //
        String strMaNH = kqdcVO.getReceive_bank();            
        //
        vtParam = new Vector();
        vtParam.add(new Parameter(strMaNH.substring(2, 5), true));
        DMNHangDAO dmdao = new DMNHangDAO(conn);
        DMNHangHOVO dmvo = dmdao.getDMNHangHO(" and a.ma_dv = ? ", vtParam);

        if (dmvo == null) {
            throw (new TTSPException()).createException("TTSP-1009",
                                                        "Kh&#244;ng t&#236;m th&#7845;y m&#227; &#7913;ng d&#7909;ng nh&#7853;n t&#432;&#417;ng &#7913;ng v&#7899;i m&#227; ng&#226;n h&#224;ng nh&#7853;n\n Duy&#7879;t l&#7879;nh kh&#244;ng th&#224;nh c&#244;ng.");
        }
        APP_RECIEVE_CODE = dmvo.getMa_ung_dung();
        APP_RECIEVE_NAME = dmvo.getTen_ung_dung();
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        strMsgID =
                ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);

        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setVersion(AppConstants.VERSION_MSG);
        msgHeader.setSender_code(APP_SEND_CODE);
        msgHeader.setSender_name(APP_SEND_NAME);
        msgHeader.setReveiver_code(APP_RECIEVE_CODE);
        msgHeader.setReceiver_name(APP_RECIEVE_NAME);
        msgHeader.setTran_code("065");
        msgHeader.setMsg_id(strMsgID);
        msgHeader.setMsg_refid(kqdcVO.getMsg_refid());

        sbMsg.append(AppConstants.XML_VERSION);
        sbMsg.append("<DATA>");
        sbMsg.append(msgHeader.getHeader());

        sbMsg.append("<BODY>");
        sbMsg.append("<MT_ID>");
        sbMsg.append(strKQDCID);
        sbMsg.append("</MT_ID>");
        sbMsg.append("<BK_ID>");
        sbMsg.append(kqdcVO.getBk_id());
        sbMsg.append("</BK_ID>");
        sbMsg.append("<KET_QUA>");
        sbMsg.append(kqdcVO.getKet_qua_dxn_thop());
        sbMsg.append("</KET_QUA>");
        sbMsg.append("<LAN_DC>");
        sbMsg.append(kqdcVO.getLan_dc());
        sbMsg.append("</LAN_DC>");
        sbMsg.append("<NGAY_DC>");
        sbMsg.append(kqdcVO.getNgay_dc());
        sbMsg.append("</NGAY_DC>");
        sbMsg.append("<SEND_BANK>");
        sbMsg.append(kqdcVO.getSend_bank());
        sbMsg.append("</SEND_BANK>");
        sbMsg.append("<RECEIVE_BANK>");
        sbMsg.append(kqdcVO.getReceive_bank());
        sbMsg.append("</RECEIVE_BANK>");
        sbMsg.append("<CREATED_DATE>");
        sbMsg.append(kqdcVO.getCreated_date());
        sbMsg.append("</CREATED_DATE>");
        sbMsg.append("<CREATOR>");
        sbMsg.append(kqdcVO.getMa_nsd());
        sbMsg.append("</CREATOR>");
        sbMsg.append("<MANAGER>");
        sbMsg.append(user_id);
        sbMsg.append("</MANAGER>");
        sbMsg.append("<VERIFIED_DATE>");
        sbMsg.append(StringUtil.DateToString(new Date(),
                                             AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        sbMsg.append("</VERIFIED_DATE>");
        sbMsg.append("<SODU_KBNN>");
        sbMsg.append(kqdcVO.getSodu_kbnn()==null?"0":kqdcVO.getSodu_kbnn());
        sbMsg.append("</SODU_KBNN>");
        sbMsg.append("<CHENH_LECH>");
        sbMsg.append(kqdcVO.getChenh_lech());
        sbMsg.append("</CHENH_LECH>");

        Collection colKQDCCTiet = null;
        KQDCChiTietVO kqCTietVO = null;
        if (!"0".equals(kqdcVO.getKet_qua())) {
            vtParam = new Vector();
            //        vtParam.add(new Parameter(strKQDCID,true));
            String strKQDCCTiet =
                "and a.BKQ_ID ='" + strKQDCID + "' AND app_type ='TTSP'";
            colKQDCCTiet = kqdcDAO.getKQDChieuCtiet(strKQDCCTiet, null);
        }
        sbMsg.append("<KB_THIEU>");
        sbMsg.append("<LTT>");

        if (colKQDCCTiet != null) {
            Iterator iter1 = colKQDCCTiet.iterator();
            while (iter1.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter1.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if ("103".equals(mt_type) && "0".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    sbMsg.append("<SEND_DATE>");
                    sbMsg.append(kqCTietVO.getSend_date());
                    sbMsg.append("</SEND_DATE>");
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");

                    sbMsg.append("<CTU_GOC>");
                    sbMsg.append(kqCTietVO.getF20() == null ? "" :
                                 kqCTietVO.getF20());
                    sbMsg.append("</CTU_GOC>");

                    sbMsg.append("<NGAY_CT>");
                    sbMsg.append(kqCTietVO.getNgay_ct());
                    sbMsg.append("</NGAY_CT>");
                    sbMsg.append("<TTIEN>");
                    sbMsg.append(kqCTietVO.getF32as3());
                    sbMsg.append("</TTIEN>");
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append("</GHI_CHU>");
                    } else if (!"".equals(kqCTietVO.getGhi_chu()) &&
                               kqCTietVO.getGhi_chu() != null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }
                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</LTT>");
        sbMsg.append("<DTS>");

        if (colKQDCCTiet != null) {
            Iterator iter2 = colKQDCCTiet.iterator();
            while (iter2.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter2.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if (("195".equals(mt_type) || "196".equals(mt_type)) &&
                    "0".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    sbMsg.append("<NGAY_TS>");
                    sbMsg.append(kqCTietVO.getSend_date());
                    sbMsg.append("</NGAY_TS>");
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<TS_TYPE>");
                    sbMsg.append(kqCTietVO.getMt_type());
                    sbMsg.append("</TS_TYPE>");
                    sbMsg.append("<F21>");
                    sbMsg.append(kqCTietVO.getF21());
                    sbMsg.append("</F21>");
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append("</GHI_CHU>");
                    } else if (!"".equals(kqCTietVO.getGhi_chu()) ||
                               kqCTietVO.getGhi_chu() != null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }

                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</DTS>");
        sbMsg.append("</KB_THIEU>");

        sbMsg.append("<KB_THUA>");
        sbMsg.append("<LTT>");

        if (colKQDCCTiet != null) {
            Iterator iter3 = colKQDCCTiet.iterator();
            while (iter3.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter3.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if ("103".equals(mt_type) && "1".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    sbMsg.append("<SEND_DATE>");
                    sbMsg.append(kqCTietVO.getSend_date());
                    sbMsg.append("</SEND_DATE>");
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    if ("".equals(kqCTietVO.getF20()) ||
                        kqCTietVO.getF20() == null) {
                        sbMsg.append("<CTU_GOC/>");
                    } else if (!"".equals(kqCTietVO.getF20()) &&
                               kqCTietVO.getF20() != null) {
                        sbMsg.append("<CTU_GOC>");
                        sbMsg.append(kqCTietVO.getF20());
                        sbMsg.append("</CTU_GOC>");
                    }
                    sbMsg.append("<NGAY_CT>");
                    sbMsg.append(kqCTietVO.getNgay_ct());
                    sbMsg.append("</NGAY_CT>");
                    sbMsg.append("<TTIEN>");
                    sbMsg.append(kqCTietVO.getF32as3());
                    sbMsg.append("</TTIEN>");
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append("</GHI_CHU>");
                    } else if (!"".equals(kqCTietVO.getGhi_chu()) ||
                               kqCTietVO.getGhi_chu() != null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }

                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</LTT>");
        sbMsg.append("<DTS>");

        if (colKQDCCTiet != null) {
            Iterator iter4 = colKQDCCTiet.iterator();
            while (iter4.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter4.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if (("195".equals(mt_type) || "196".equals(mt_type)) &&
                    "1".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    sbMsg.append("<NGAY_TS>");
                    sbMsg.append(kqCTietVO.getSend_date());
                    sbMsg.append("</NGAY_TS>");
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<TS_TYPE>");
                    sbMsg.append(kqCTietVO.getMt_type());
                    sbMsg.append("</TS_TYPE>");
                    sbMsg.append("<F21>");
                    sbMsg.append(kqCTietVO.getF21());
                    sbMsg.append("</F21>");
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append("</GHI_CHU>");
                    } else if (!"".equals(kqCTietVO.getGhi_chu()) ||
                               kqCTietVO.getGhi_chu() != null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }

                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</DTS>");
        sbMsg.append("</KB_THUA>");
        sbMsg.append("</BODY>");
        sbMsg.append("<SECURITY>");
        sbMsg.append("<SIGNATURE>");
        sbMsg.append("</SIGNATURE>");
        sbMsg.append("</SECURITY>");
        sbMsg.append("</DATA>");
        //Build log msg
        MsgDAO msgDAO = new MsgDAO(conn);
        MsgVO msg = new MsgVO();

        msg.setMsg_content(sbMsg.toString());
        msg.setMsg_id(strMsgID);
        msg.setMsg_type(TRAN_CODE_1);
        //Put msg to queue

        MQClient client = new MQClient();
        client.putMsgToQueue(sbMsg.toString(), MQ_HOSTNAME, MQ_CHANEL, MQ_PORT,
                             MQ_MANAGER_NAME, MQ_NAME);

        //Ghi log
        try {
            msgDAO.insert(msg);
        } catch (Exception e) {
            Exception ex =
                new Exception("Loi ghi log(com.seatech.ttsp.proxy.giaodien.bank.SendKQDC1.sendMessage()): " +
                              e.getMessage());
            ex.printStackTrace();
        }

        return strMsgID;
    }

    public String sendMessageDCTHop2(String strKQDCID, String user_id) throws Exception {
        String strMsgID = "";
        StringBuffer sbMsg = new StringBuffer();

        setThamSoQuyetToan();
        DChieu1DAO kqdcDAO = new DChieu1DAO(conn);
        Vector vtParam = new Vector();
        //      vtParam.add(new Parameter(strKQDCID, true));
        String strKQDC1 = "and a.id ='" + strKQDCID + "' AND app_type ='TTSP'";
        KQDChieu1VO kqdcVO = kqdcDAO.getKQDC1(strKQDC1, null);
        //
        String strMaNH = kqdcVO.getReceive_bank();
        vtParam = new Vector();
        vtParam.add(new Parameter(strMaNH.substring(2, 5), true));
        DMNHangDAO dmdao = new DMNHangDAO(conn);
        DMNHangHOVO dmvo = dmdao.getDMNHangHO(" and a.ma_dv = ? ", vtParam);

        if (dmvo == null) {
            throw (new TTSPException()).createException("TTSP-1009",
                                                        "Kh&#244;ng t&#236;m th&#7845;y m&#227; &#7913;ng d&#7909;ng nh&#7853;n t&#432;&#417;ng &#7913;ng v&#7899;i m&#227; ng&#226;n h&#224;ng nh&#7853;n\n Duy&#7879;t l&#7879;nh kh&#244;ng th&#224;nh c&#244;ng.");
        }
        APP_RECIEVE_CODE = dmvo.getMa_ung_dung();
        APP_RECIEVE_NAME = dmvo.getTen_ung_dung();
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        strMsgID =
                ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);

        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setVersion(AppConstants.VERSION_MSG);
        msgHeader.setSender_code(APP_SEND_CODE);
        msgHeader.setSender_name(APP_SEND_NAME);
        msgHeader.setReveiver_code(APP_RECIEVE_CODE);
        msgHeader.setReceiver_name(APP_RECIEVE_NAME);
        msgHeader.setTran_code("065");
        msgHeader.setMsg_id(strMsgID);
        msgHeader.setMsg_refid("");

        sbMsg.append(AppConstants.XML_VERSION);
        sbMsg.append("<DATA>");
        sbMsg.append(msgHeader.getHeader());

        sbMsg.append("<BODY>");
        sbMsg.append("<MT_ID>");
        sbMsg.append(strKQDCID);
        sbMsg.append("</MT_ID>");
        sbMsg.append("<BK_ID>");
        sbMsg.append("");
        sbMsg.append("</BK_ID>");
        sbMsg.append("<KET_QUA>");
        sbMsg.append(kqdcVO.getKet_qua());
        sbMsg.append("</KET_QUA>");
        sbMsg.append("<LAN_DC>");
        sbMsg.append(kqdcVO.getLan_dc());
        sbMsg.append("</LAN_DC>");
        sbMsg.append("<NGAY_DC>");
        sbMsg.append(kqdcVO.getNgay_dc());
        sbMsg.append("</NGAY_DC>");
        sbMsg.append("<SEND_BANK>");
        sbMsg.append(kqdcVO.getSend_bank());
        sbMsg.append("</SEND_BANK>");
        sbMsg.append("<RECEIVE_BANK>");
        sbMsg.append(kqdcVO.getReceive_bank());
        sbMsg.append("</RECEIVE_BANK>");
        sbMsg.append("<CREATED_DATE>");
        sbMsg.append(kqdcVO.getCreated_date());
        sbMsg.append("</CREATED_DATE>");
        sbMsg.append("<CREATOR>");
        sbMsg.append(kqdcVO.getMa_nsd());
        sbMsg.append("</CREATOR>");
        sbMsg.append("<MANAGER>");
        sbMsg.append(user_id);
        sbMsg.append("</MANAGER>");
        sbMsg.append("<VERIFIED_DATE>");
        sbMsg.append(StringUtil.DateToString(new Date(),
                                             AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        sbMsg.append("</VERIFIED_DATE>");
        sbMsg.append("<SODU_KBNN>");
        sbMsg.append(kqdcVO.getSodu_kbnn()==null?"0":kqdcVO.getSodu_kbnn());
        sbMsg.append("</SODU_KBNN>");
        sbMsg.append("<CHENH_LECH>");
        sbMsg.append(kqdcVO.getChenh_lech());
        sbMsg.append("</CHENH_LECH>");

        Collection colKQDCCTiet = null;
        KQDCChiTietVO kqCTietVO = null;
        if (!"0".equals(kqdcVO.getKet_qua())) {
            vtParam = new Vector();
            //        vtParam.add(new Parameter(strKQDCID,true));
            String strKQDCCTiet =
                "and a.bkq_id ='" + strKQDCID + "' AND app_type ='TTSP'";
            colKQDCCTiet = kqdcDAO.getKQDChieuCtiet(strKQDCCTiet, null);
        }
        sbMsg.append("<KB_THIEU>");
        sbMsg.append("<LTT>");

        if (colKQDCCTiet != null) {
            Iterator iter1 = colKQDCCTiet.iterator();
            while (iter1.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter1.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if ("103".equals(mt_type) && "0".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    sbMsg.append("<SEND_DATE>");
                    sbMsg.append(kqCTietVO.getSend_date());
                    sbMsg.append("</SEND_DATE>");
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<CTU_GOC>");
                    sbMsg.append(kqCTietVO.getF20());
                    sbMsg.append("</CTU_GOC>");
                    sbMsg.append("<NGAY_CT>");
                    sbMsg.append(kqCTietVO.getNgay_ct());
                    sbMsg.append("</NGAY_CT>");
                    sbMsg.append("<TTIEN>");
                    sbMsg.append(kqCTietVO.getF32as3());
                    sbMsg.append("</TTIEN>");
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append("</GHI_CHU>");
                    } else if (!"".equals(kqCTietVO.getGhi_chu()) ||
                               kqCTietVO.getGhi_chu() != null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }

                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</LTT>");
        sbMsg.append("<DTS>");

        if (colKQDCCTiet != null) {
            Iterator iter2 = colKQDCCTiet.iterator();
            while (iter2.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter2.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if (("195".equals(mt_type) || "196".equals(mt_type)) &&
                    "0".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    sbMsg.append("<NGAY_TS>");
                    sbMsg.append(kqCTietVO.getSend_date());
                    sbMsg.append("</NGAY_TS>");
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<TS_TYPE>");
                    sbMsg.append(kqCTietVO.getMt_type());
                    sbMsg.append("</TS_TYPE>");
                    sbMsg.append("<F21>");
                    sbMsg.append(kqCTietVO.getF21());
                    sbMsg.append("</F21>");
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append("</GHI_CHU>");
                    } else if (!"".equals(kqCTietVO.getGhi_chu()) ||
                               kqCTietVO.getGhi_chu() != null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }

                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</DTS>");
        sbMsg.append("</KB_THIEU>");

        sbMsg.append("<KB_THUA>");
        sbMsg.append("<LTT>");

        if (colKQDCCTiet != null) {
            Iterator iter3 = colKQDCCTiet.iterator();
            while (iter3.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter3.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if ("103".equals(mt_type) && "1".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    sbMsg.append("<SEND_DATE>");
                    sbMsg.append(kqCTietVO.getSend_date());
                    sbMsg.append("</SEND_DATE>");
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<CTU_GOC>");
                    sbMsg.append(kqCTietVO.getF20());
                    sbMsg.append("</CTU_GOC>");
                    sbMsg.append("<NGAY_CT>");
                    sbMsg.append(kqCTietVO.getNgay_ct());
                    sbMsg.append("</NGAY_CT>");
                    sbMsg.append("<TTIEN>");
                    sbMsg.append(kqCTietVO.getF32as3());
                    sbMsg.append("</TTIEN>");
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append("</GHI_CHU>");
                    } else if (!"".equals(kqCTietVO.getGhi_chu()) ||
                               kqCTietVO.getGhi_chu() != null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }

                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</LTT>");
        sbMsg.append("<DTS>");

        if (colKQDCCTiet != null) {
            Iterator iter4 = colKQDCCTiet.iterator();
            while (iter4.hasNext()) {
                kqCTietVO = (KQDCChiTietVO)iter4.next();
                String tthai = kqCTietVO.getTrang_thai();
                String mt_type = kqCTietVO.getMt_type();
                if (("195".equals(mt_type) || "196".equals(mt_type)) &&
                    "1".equals(tthai)) {
                    sbMsg.append("<ROW>");
                    sbMsg.append("<NGAY_TS>");
                    sbMsg.append(kqCTietVO.getSend_date());
                    sbMsg.append("</NGAY_TS>");
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<TS_TYPE>");
                    sbMsg.append(kqCTietVO.getMt_type());
                    sbMsg.append("</TS_TYPE>");
                    sbMsg.append("<F21>");
                    sbMsg.append(kqCTietVO.getF21());
                    sbMsg.append("</F21>");
                    if ("".equals(kqCTietVO.getGhi_chu()) ||
                        kqCTietVO.getGhi_chu() == null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append("</GHI_CHU>");
                    } else if (!"".equals(kqCTietVO.getGhi_chu()) ||
                               kqCTietVO.getGhi_chu() != null) {
                        sbMsg.append("<GHI_CHU>");
                        sbMsg.append(kqCTietVO.getGhi_chu());
                        sbMsg.append("</GHI_CHU>");
                    }

                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</DTS>");
        sbMsg.append("</KB_THUA>");
        sbMsg.append("</BODY>");
        sbMsg.append("<SECURITY>");
        sbMsg.append("<SIGNATURE>");
        sbMsg.append("</SIGNATURE>");
        sbMsg.append("</SECURITY>");
        sbMsg.append("</DATA>");
        //Build log msg
        MsgDAO msgDAO = new MsgDAO(conn);
        MsgVO msg = new MsgVO();

        msg.setMsg_content(sbMsg.toString());
        msg.setMsg_id(strMsgID);
        msg.setMsg_type(TRAN_CODE_1);
        //Put msg to queue
        MQClient client = new MQClient();
        client.putMsgToQueue(sbMsg.toString(), MQ_HOSTNAME, MQ_CHANEL, MQ_PORT,
                             MQ_MANAGER_NAME, MQ_NAME);
        //Ghi log
        try {
            msgDAO.insert(msg);
        } catch (Exception e) {
            Exception ex =
                new Exception("Loi ghi log(com.seatech.ttsp.proxy.giaodien.bank.SendKQDC1.sendMessage()): " +
                              e.getMessage());
            ex.printStackTrace();
        }
        return strMsgID;
    }

    public String sendMessageDC2(String strKQDCID, String user_id) throws Exception {
        String strMsgID = "";
        StringBuffer sbMsg = new StringBuffer();

        setThamSoQuyetToan();
        DChieu2DAO kqdcDAO = new DChieu2DAO(conn);
        Vector vtParam = new Vector();
        //      vtParam.add(new Parameter(strKQDCID, true));
        String strKQDC2 = "and a.id ='" + strKQDCID + "'";
        DuyetXNDChieu2VO kqdcVO = kqdcDAO.getKQDC2(strKQDC2, null);
        //
        String strMaNH = kqdcVO.getReceive_bank();
        vtParam = new Vector();
        vtParam.add(new Parameter(strMaNH.substring(2, 5), true));
        DMNHangDAO dmdao = new DMNHangDAO(conn);
        DMNHangHOVO dmvo = dmdao.getDMNHangHO(" and a.ma_dv = ? ", vtParam);

        if (dmvo == null) {
            throw (new TTSPException()).createException("TTSP-1009",
                                                        "Kh&#244;ng t&#236;m th&#7845;y m&#227; &#7913;ng d&#7909;ng nh&#7853;n t&#432;&#417;ng &#7913;ng v&#7899;i m&#227; ng&#226;n h&#224;ng nh&#7853;n\n Duy&#7879;t l&#7879;nh kh&#244;ng th&#224;nh c&#244;ng.");
        }
        APP_RECIEVE_CODE = dmvo.getMa_ung_dung();
        APP_RECIEVE_NAME = dmvo.getTen_ung_dung();
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        strMsgID =
                ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);

        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setVersion(AppConstants.VERSION_MSG);
        msgHeader.setSender_code(APP_SEND_CODE);
        msgHeader.setSender_name(APP_SEND_NAME);
        msgHeader.setReveiver_code(APP_RECIEVE_CODE);
        msgHeader.setReceiver_name(APP_RECIEVE_NAME);
        msgHeader.setTran_code(TRAN_CODE_2);
        msgHeader.setMsg_id(strMsgID);
        msgHeader.setMsg_refid("");

        sbMsg.append(AppConstants.XML_VERSION);
        sbMsg.append("<DATA>");
        sbMsg.append(msgHeader.getHeader());

        sbMsg.append("<BODY>");
        sbMsg.append("<MT_ID>");
        sbMsg.append(strKQDCID);
        sbMsg.append("</MT_ID>");
        sbMsg.append("<BK_ID>");
        sbMsg.append(kqdcVO.getBk_id());
        sbMsg.append("</BK_ID>");
        sbMsg.append("<KET_QUA>");
        sbMsg.append(kqdcVO.getKet_qua());
        sbMsg.append("</KET_QUA>");
        sbMsg.append("<LAN_DC>");
        sbMsg.append(kqdcVO.getLan_dc());
        sbMsg.append("</LAN_DC>");
        sbMsg.append("<SEND_BANK>");
        sbMsg.append(kqdcVO.getSend_bank());
        sbMsg.append("</SEND_BANK>");
        sbMsg.append("<RECEIVE_BANK>");
        sbMsg.append(kqdcVO.getReceive_bank());
        sbMsg.append("</RECEIVE_BANK>");
        sbMsg.append("<CREATED_DATE>");
        sbMsg.append(kqdcVO.getCreated_date());
        sbMsg.append("</CREATED_DATE>");
        sbMsg.append("<CREATOR>");
        sbMsg.append(kqdcVO.getMa_nsd());
        sbMsg.append("</CREATOR>");
        sbMsg.append("<MANAGER>");
        sbMsg.append(user_id);
        sbMsg.append("</MANAGER>");
        sbMsg.append("<VERIFIED_DATE>");
        sbMsg.append(StringUtil.DateToString(new Date(),
                                             AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        sbMsg.append("</VERIFIED_DATE>");
        sbMsg.append("<SODU_CUOINGAY_KB>");
        sbMsg.append(kqdcVO.getSodu_cuoingay_kb());
        sbMsg.append("</SODU_CUOINGAY_KB>");
        sbMsg.append("<SODU_CLECH>");
        sbMsg.append(kqdcVO.getSodu_clech());
        sbMsg.append("</SODU_CLECH>");
        sbMsg.append("<KETCHUYEN_THU_KB>");
        sbMsg.append(kqdcVO.getKetchuyen_thu_kb());
        sbMsg.append("</KETCHUYEN_THU_KB>");
        sbMsg.append("<KETCHUYEN_THU_CLECH>");
        sbMsg.append(kqdcVO.getKetchuyen_thu_clech());
        sbMsg.append("</KETCHUYEN_THU_CLECH>");
        sbMsg.append("<KETCHUYEN_CHI_KB>");
        sbMsg.append(kqdcVO.getKetchuyen_chi_kb());
        sbMsg.append("</KETCHUYEN_CHI_KB>");
        sbMsg.append("<KETCHUYEN_CHI_CLECH>");
        sbMsg.append(kqdcVO.getKetchuyen_chi_clech());
        sbMsg.append("</KETCHUYEN_CHI_CLECH>");
        sbMsg.append("</BODY>");
        sbMsg.append("<SECURITY>");
        sbMsg.append("<SIGNATURE>");
        sbMsg.append("</SIGNATURE>");
        sbMsg.append("</SECURITY>");
        sbMsg.append("</DATA>");
        //Build log msg
        MsgDAO msgDAO = new MsgDAO(conn);
        MsgVO msg = new MsgVO();

        msg.setMsg_content(sbMsg.toString());
        msg.setMsg_id(strMsgID);
        msg.setMsg_type(TRAN_CODE_2);
        //Put msg to queue
        MQClient client = new MQClient();
        client.putMsgToQueue(sbMsg.toString(), MQ_HOSTNAME, MQ_CHANEL, MQ_PORT,
                             MQ_MANAGER_NAME, MQ_NAME);
        //Ghi log
        try {
            msgDAO.insert(msg);
        } catch (Exception e) {
            Exception ex =
                new Exception("Loi ghi log(com.seatech.ttsp.proxy.giaodien.bank.SendKQDC1.sendMessage()): " +
                              e.getMessage());
            ex.printStackTrace();
        }
        return strMsgID;
    }



  public String sendMessageDC3(String strKQDCID, String user_id) throws Exception {
      String strMsgID = "";
      StringBuffer sbMsg = new StringBuffer();

      setThamSoQuyetToan();
      KQDChieu3DAO kqdcDAO = new KQDChieu3DAO(conn);

      Vector vtParam = new Vector();
      String strKQDC1 = " and a.id ='" + strKQDCID + "'";
      KQDChieu3VO kqdcVO = kqdcDAO.getKQDC3(strKQDC1, null);
      //
      String strMaNH = kqdcVO.getReceive_bank();
      vtParam = new Vector();
      vtParam.add(new Parameter(strMaNH.substring(2, 5), true));
      DMNHangDAO dmdao = new DMNHangDAO(conn);
      DMNHangHOVO dmvo = dmdao.getDMNHangHO(" and a.ma_dv = ? ", vtParam);

      if (dmvo == null) {
          throw (new TTSPException()).createException("TTSP-1009",
                                                      "Kh&#244;ng t&#236;m th&#7845;y m&#227; &#7913;ng d&#7909;ng nh&#7853;n t&#432;&#417;ng &#7913;ng v&#7899;i m&#227; ng&#226;n h&#224;ng nh&#7853;n\n Duy&#7879;t l&#7879;nh kh&#244;ng th&#224;nh c&#244;ng.");
      }
      APP_RECIEVE_CODE = dmvo.getMa_ung_dung();
      APP_RECIEVE_NAME = dmvo.getTen_ung_dung();
      TTSPUtils ttspUtils = new TTSPUtils(conn);
      strMsgID =
              ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);

      MsgHeader msgHeader = new MsgHeader();
      msgHeader.setVersion(AppConstants.VERSION_MSG);
      msgHeader.setSender_code(APP_SEND_CODE);
      msgHeader.setSender_name(APP_SEND_NAME);
      msgHeader.setReveiver_code(APP_RECIEVE_CODE);
      msgHeader.setReceiver_name(APP_RECIEVE_NAME);
      msgHeader.setTran_code("069");
      msgHeader.setMsg_id(strMsgID);
      msgHeader.setMsg_refid(kqdcVO.getMsg_refid() == null ? "" : kqdcVO.getMsg_refid());

      sbMsg.append(AppConstants.XML_VERSION);
      sbMsg.append("<DATA>");
      sbMsg.append(msgHeader.getHeader());

      sbMsg.append("<BODY>");
      sbMsg.append("<MT_ID>");
      sbMsg.append(strKQDCID);
      sbMsg.append("</MT_ID>");
      sbMsg.append("<BK_ID>");
      sbMsg.append(kqdcVO.getBk_id());
      sbMsg.append("</BK_ID>");
      sbMsg.append("<KET_QUA>");
//      sbMsg.append(kqdcVO.getKet_qua());
      sbMsg.append(Integer.parseInt(kqdcVO.getKet_qua()));
      sbMsg.append("</KET_QUA>");
      sbMsg.append("<LAN_DC>");
      sbMsg.append(kqdcVO.getLan_dc());
      sbMsg.append("</LAN_DC>");
      sbMsg.append("<NGAY_DC>");
      sbMsg.append(kqdcVO.getNgay_dc().replace("/","-"));
      sbMsg.append("</NGAY_DC>");
      sbMsg.append("<SEND_BANK>");
      sbMsg.append(kqdcVO.getSend_bank());
      sbMsg.append("</SEND_BANK>");
      sbMsg.append("<RECEIVE_BANK>");
      sbMsg.append(kqdcVO.getReceive_bank());
      sbMsg.append("</RECEIVE_BANK>");
      sbMsg.append("<CREATED_DATE>");
      sbMsg.append(kqdcVO.getCreated_date().replace("/","-"));
      sbMsg.append("</CREATED_DATE>");
      sbMsg.append("<CREATOR>");
      sbMsg.append(kqdcVO.getMa_nsd());
      sbMsg.append("</CREATOR>");
      sbMsg.append("<MANAGER>");
      sbMsg.append(user_id);
      sbMsg.append("</MANAGER>");
      sbMsg.append("<VERIFIED_DATE>");
      sbMsg.append(StringUtil.DateToString(new Date(),
                                           AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
      sbMsg.append("</VERIFIED_DATE>");

      sbMsg.append("<SODU_KBNN>");
      sbMsg.append(kqdcVO.getSo_du_kbnn() == null ? "0" :
                 kqdcVO.getSo_du_kbnn());
      sbMsg.append("</SODU_KBNN>");

      sbMsg.append("<CHENH_LECH>");
    sbMsg.append(kqdcVO.getChenh_lech() == null ? "" :
               kqdcVO.getChenh_lech());
      sbMsg.append("</CHENH_LECH>");

    KQDChieu3CTietDAO dchieu3CtietDAO= new KQDChieu3CTietDAO(conn);
    KQDChieu3CTietVO kqCTietVO = null;
    
    Collection colMT900 = null;
    String strWhere = " AND a.kq_id='"+strKQDCID+"' AND a.mt_type='900'";    
     colMT900 = dchieu3CtietDAO.getKQDChieu3CTietList(strWhere, null);
      sbMsg.append("<MT900>");
      sbMsg.append("<KB_THIEU>");
      if (colMT900 != null) {
          Iterator iter1 = colMT900.iterator();
          while (iter1.hasNext()) {
              kqCTietVO = (KQDChieu3CTietVO)iter1.next();
              String tthai = kqCTietVO.getTrang_thai();
              if ("00".equals(tthai)) {
                  sbMsg.append("<ROW>");
                      sbMsg.append("<NGAY_CT>");
                sbMsg.append(kqCTietVO.getNgay_ct() == null ? "" :
                             kqCTietVO.getNgay_ct().replace("/", "-"));
                      sbMsg.append("</NGAY_CT>");
                  sbMsg.append("<MA_KB>");
                  sbMsg.append(kqCTietVO.getMa_kb());
                  sbMsg.append("</MA_KB>");
                  sbMsg.append("<TEN_KB>");
                  sbMsg.append(kqCTietVO.getTen_kb());
                  sbMsg.append("</TEN_KB>");
                  sbMsg.append("<MT_ID>");
                  sbMsg.append(kqCTietVO.getMt_id());
                  sbMsg.append("</MT_ID>");
                  sbMsg.append("<SO_TIEN>");
                  sbMsg.append(kqCTietVO.getSo_tien());
                  sbMsg.append("</SO_TIEN>");
                  sbMsg.append("</ROW>");
              }
          }
      }
      sbMsg.append("</KB_THIEU>");
      
        sbMsg.append("<KB_THUA>");
        if (colMT900 != null) {
            Iterator iter1 = colMT900.iterator();
            while (iter1.hasNext()) {
                kqCTietVO = (KQDChieu3CTietVO)iter1.next();
                String tthai = kqCTietVO.getTrang_thai();
                if ("01".equals(tthai)) {
                    sbMsg.append("<ROW>");
                        sbMsg.append("<NGAY_CT>");
                  sbMsg.append(kqCTietVO.getNgay_ct() == null ? "" :
                               kqCTietVO.getNgay_ct().replace("/", "-"));
                        sbMsg.append("</NGAY_CT>");
                    sbMsg.append("<MA_KB>");
                    sbMsg.append(kqCTietVO.getMa_kb());
                    sbMsg.append("</MA_KB>");
                    sbMsg.append("<TEN_KB>");
                    sbMsg.append(kqCTietVO.getTen_kb());
                    sbMsg.append("</TEN_KB>");
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<SO_TIEN>");
                    sbMsg.append(kqCTietVO.getSo_tien());
                    sbMsg.append("</SO_TIEN>");
                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</KB_THUA>");
      
      sbMsg.append("</MT900>");

    Collection colMT910 = null;
    String strMT910 = " AND a.kq_id='"+strKQDCID+"' AND a.mt_type='910'";    
     colMT910 = dchieu3CtietDAO.getKQDChieu3CTietList(strMT910, null);
      sbMsg.append("<MT910>");
      sbMsg.append("<KB_THIEU>");
      if (colMT910 != null) {
          Iterator iter1 = colMT910.iterator();
          while (iter1.hasNext()) {
              kqCTietVO = (KQDChieu3CTietVO)iter1.next();
              String tthai = kqCTietVO.getTrang_thai();
              if ("00".equals(tthai)) {
                  sbMsg.append("<ROW>");
                      sbMsg.append("<NGAY_CT>");
                sbMsg.append(kqCTietVO.getNgay_ct() == null ? "" :
                             kqCTietVO.getNgay_ct().replace("/", "-"));
                      sbMsg.append("</NGAY_CT>");
                  sbMsg.append("<MA_KB>");
                  sbMsg.append(kqCTietVO.getMa_kb());
                  sbMsg.append("</MA_KB>");
                  sbMsg.append("<TEN_KB>");
                  sbMsg.append(kqCTietVO.getTen_kb());
                  sbMsg.append("</TEN_KB>");
                  sbMsg.append("<MT_ID>");
                  sbMsg.append(kqCTietVO.getMt_id());
                  sbMsg.append("</MT_ID>");
                  sbMsg.append("<SO_TIEN>");
                  sbMsg.append(kqCTietVO.getSo_tien());
                  sbMsg.append("</SO_TIEN>");
                  sbMsg.append("</ROW>");
              }
          }
      }
      sbMsg.append("</KB_THIEU>");
      
        sbMsg.append("<KB_THUA>");
        if (colMT910 != null) {
            Iterator iter1 = colMT910.iterator();
            while (iter1.hasNext()) {
                kqCTietVO = (KQDChieu3CTietVO)iter1.next();
                String tthai = kqCTietVO.getTrang_thai();
                if ("01".equals(tthai)) {
                    sbMsg.append("<ROW>");
                        sbMsg.append("<NGAY_CT>");
                  sbMsg.append(kqCTietVO.getNgay_ct() == null ? "" :
                               kqCTietVO.getNgay_ct().replace("/", "-"));
                        sbMsg.append("</NGAY_CT>");
                    sbMsg.append("<MA_KB>");
                    sbMsg.append(kqCTietVO.getMa_kb());
                    sbMsg.append("</MA_KB>");
                    sbMsg.append("<TEN_KB>");
                    sbMsg.append(kqCTietVO.getTen_kb());
                    sbMsg.append("</TEN_KB>");
                    sbMsg.append("<MT_ID>");
                    sbMsg.append(kqCTietVO.getMt_id());
                    sbMsg.append("</MT_ID>");
                    sbMsg.append("<SO_TIEN>");
                    sbMsg.append(kqCTietVO.getSo_tien());
                    sbMsg.append("</SO_TIEN>");
                    sbMsg.append("</ROW>");
                }
            }
        }
        sbMsg.append("</KB_THUA>");
      
      sbMsg.append("</MT910>");
      sbMsg.append("</BODY>");
      sbMsg.append("<SECURITY>");
      sbMsg.append("<SIGNATURE>");
      sbMsg.append("</SIGNATURE>");
      sbMsg.append("</SECURITY>");
      sbMsg.append("</DATA>");
      //Build log msg
      MsgDAO msgDAO = new MsgDAO(conn);
      MsgVO msg = new MsgVO();

      msg.setMsg_content(sbMsg.toString());
      msg.setMsg_id(strMsgID);
      msg.setMsg_type(TRAN_CODE_3);
      msg.setSender(APP_SEND_CODE);
      msg.setReciever(APP_RECIEVE_CODE);
      //Put msg to queue
          MQClient client = new MQClient();
          client.putMsgToQueue(sbMsg.toString(), MQ_HOSTNAME, MQ_CHANEL,
                               MQ_PORT, MQ_MANAGER_NAME, MQ_NAME);

          //Ghi log
          try {
              msgDAO.insert(msg);
          } catch (Exception e) {
              Exception ex =
                  new Exception("Loi ghi log(com.seatech.ttsp.proxy.giaodien.bank.SendKQDC1.sendMessage()): " +
                                e.getMessage());
              ex.printStackTrace();
          }
      return strMsgID;
  }




    /**
     * @see: Lay tham so quyet toan tu CSDL
     * @param: strType: Loai hach toan theo bang ke hay theo chung tu
     * */
    private void setThamSoQuyetToan() throws Exception {
        ThamSoHThongDAO tshtDAO = new ThamSoHThongDAO(conn);
        String strWhereClauseTS = " and ten_ts in (?, ?, ?, ?, ?)";

        Vector vtParam = new Vector();
        vtParam.add(new Parameter("MQ_HOSTNAME", true));
        vtParam.add(new Parameter("MQ_CHANEL", true));
        vtParam.add(new Parameter("MQ_PORT", true));
        vtParam.add(new Parameter("MQ_MANAGER_NAME", true));
        vtParam.add(new Parameter("MQ_NAME", true));

        Collection tsList = tshtDAO.getThamSoList(strWhereClauseTS, vtParam);
        Iterator iter = tsList.iterator();
        ThamSoHThongVO tsVO = null;
        String strTenTS = "";
        while (iter.hasNext()) {
            tsVO = (ThamSoHThongVO)iter.next();
            strTenTS = tsVO.getTen_ts();
            if (strTenTS.equalsIgnoreCase("MQ_HOSTNAME")) {
                MQ_HOSTNAME = tsVO.getGiatri_ts();
            } else if (strTenTS.equalsIgnoreCase("MQ_CHANEL")) {
                MQ_CHANEL = tsVO.getGiatri_ts();
            } else if (strTenTS.equalsIgnoreCase("MQ_PORT")) {
                MQ_PORT = tsVO.getGiatri_ts();
            } else if (strTenTS.equalsIgnoreCase("MQ_NAME")) {
                MQ_NAME = tsVO.getGiatri_ts();
            } else if (strTenTS.equalsIgnoreCase("MQ_MANAGER_NAME")) {
                MQ_MANAGER_NAME = tsVO.getGiatri_ts();
            }
        }
    }
}
