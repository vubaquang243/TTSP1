package com.seatech.ttsp.proxy.giaodien;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
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


public class Send071 {

    private String MQ_HOSTNAME;
    private String MQ_CHANEL;
    private String MQ_PORT;
    private String MQ_MANAGER_NAME;
    private String MQ_NAME;

    private String APP_SEND_CODE = AppConstants.APP_SEND_CODE;
    private String APP_SEND_NAME = AppConstants.APP_SEND_NAME;
    private String APP_RECIEVE_CODE;
    private String APP_RECIEVE_NAME;

    private String TRAN_CODE = "071";

    Connection conn = null;

    public Send071(Connection conn) {
        this.conn = conn;
    }

    public String sendMessage(String strMTID, String strRecieveBank,
                              String strSendBank, String strCreator,
                              String strManager) throws Exception {
        String strMsgID = "";
        String strContent = "";
        StringBuffer sbMsg = new StringBuffer();

        setThamSo();

        Vector vtParam = new Vector();
        vtParam.add(new Parameter(strRecieveBank.substring(2, 5), true));
        DMNHangDAO dmdao = new DMNHangDAO(conn);
        DMNHangHOVO dmvo = dmdao.getDMNHangHO(" and a.ma_dv = ? ", vtParam);

        if (dmvo == null) {
            throw (new TTSPException()).createException("TTSP-1009",
                                                        "Kh&#244;ng t&#236;m th&#7845;y m&#227; &#7913;ng d&#7909;ng nh&#7853;n t&#432;&#417;ng &#7913;ng v&#7899;i m&#227; ng&#226;n h&#224;ng nh&#7853;n\n Duy&#7879;t l&#7879;nh kh&#244;ng th&#224;nh c&#244;ng.");
        }
        APP_RECIEVE_CODE = dmvo.getMa_ung_dung();
        APP_RECIEVE_NAME = dmvo.getTen_ung_dung();
        strContent =
                "Tra cuu online tinh hinh thanh toan cua KBNN tai " + dmvo.getTen_nh();
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        strMsgID =
                ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);

        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setVersion(AppConstants.VERSION_MSG);
        msgHeader.setSender_code(APP_SEND_CODE);
        msgHeader.setSender_name(APP_SEND_NAME);
        msgHeader.setReveiver_code(APP_RECIEVE_CODE);
        msgHeader.setReceiver_name(APP_RECIEVE_NAME);
        msgHeader.setTran_code(TRAN_CODE);
        msgHeader.setMsg_id(strMsgID);
        msgHeader.setMsg_refid("");

        sbMsg.append(AppConstants.XML_VERSION);
        sbMsg.append("<DATA>");
        sbMsg.append(msgHeader.getHeader());

        sbMsg.append("<BODY>");
        sbMsg.append("<MT_ID>");
        sbMsg.append(strMTID);
        sbMsg.append("</MT_ID>");

        sbMsg.append("<MT_TYPE>");
        sbMsg.append(TRAN_CODE);
        sbMsg.append("</MT_TYPE>");

        sbMsg.append("<SEND_BANK>");
        sbMsg.append(strSendBank);
        sbMsg.append("</SEND_BANK>");

        sbMsg.append("<RECEIVE_BANK>");
        sbMsg.append(strRecieveBank);
        sbMsg.append("</RECEIVE_BANK>");

        sbMsg.append("<CREATED_DATE>");
        sbMsg.append(StringUtil.DateToString(new Date(),
                                             AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        sbMsg.append("</CREATED_DATE>");
        sbMsg.append("<CREATOR>");
        sbMsg.append(strCreator);
        sbMsg.append("</CREATOR>");
        sbMsg.append("<MANAGER>");
        sbMsg.append(strManager);
        sbMsg.append("</MANAGER>");
        sbMsg.append("<VERIFIED_DATE>");
        sbMsg.append(StringUtil.DateToString(new Date(),
                                             AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        sbMsg.append("</VERIFIED_DATE>");
        sbMsg.append("<NOI_DUNG>");
        sbMsg.append(strContent);
        sbMsg.append("</NOI_DUNG>");

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
        msg.setMsg_type(TRAN_CODE);
        msg.setSender(APP_SEND_CODE);
        msg.setReciever(APP_RECIEVE_CODE);
        //Put msg to queue
        MQClient client = new MQClient();
        client.putMsgToQueue(sbMsg.toString(), MQ_HOSTNAME, MQ_CHANEL, MQ_PORT,
                             MQ_MANAGER_NAME, MQ_NAME);
        //Ghi log
        try {
            msgDAO.insert(msg);
        } catch (Exception e) {
            Exception ex =
                new Exception("Loi ghi log(com.seatech.ttsp.proxy.giaodien.bank.Send701.sendMessage()): " +
                              e.getMessage());
            ex.printStackTrace();
        }
        return strMsgID;
    }

    /**
     * @see: Lay tham so quyet toan tu CSDL
     * @param: strType: Loai hach toan theo bang ke hay theo chung tu
     * */
    private void setThamSo() throws Exception {
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
