package com.seatech.ttsp.proxy.giaodien;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TTSPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.cutofftime.CutOffTimeDAO;
import com.seatech.ttsp.cutofftime.CutOffTimeVO;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeDAO;
import com.seatech.ttsp.cutoftime.TSoCutOfTimeVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangHOVO;
import com.seatech.ttsp.dmnh.DMNHangVO;
import com.seatech.ttsp.proxy.giaodien.mq.MQClient;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;


public class Send299 {
    private String MQ_HOSTNAME;
    private String MQ_CHANEL;
    private String MQ_PORT;
    private String MQ_MANAGER_NAME;
    private String MQ_NAME;

    private String APP_SEND_CODE = AppConstants.APP_SEND_CODE;
    private String APP_SEND_NAME = AppConstants.APP_SEND_NAME;
    private String APP_RECIEVE_CODE;
    private String APP_RECIEVE_NAME;

    private String TRAN_CODE = "299";

    Connection conn = null;

    public Send299(Connection conn) {
        this.conn = conn;
    }

    public String sendMessage(CutOffTimeVO currentCOTvo, String type) throws Exception {
        String strMsgID = "";
        StringBuffer sbMsg = new StringBuffer();
        setThamSo();

        CutOffTimeDAO cotDAO = new CutOffTimeDAO(conn);
        TSoCutOfTimeDAO tsoCOT = new TSoCutOfTimeDAO(conn);
        Vector vtParam = new Vector();
        String strCOT = "and a.id ='" + currentCOTvo.getId() + "'";
        CutOffTimeVO cotVO = null;
        //        if(type==null){
        cotVO = cotDAO.getTTinXML(strCOT, null);
        //        }else{
        //          cotVO = currentCOTvo;
        //        }
        String strMaNH = "";
        if (AppConstants.KBNN_TW_BANK_CODE.equals(cotVO.getNhkb_nhan())) {
            strMaNH = cotVO.getNhkb_chuyen();
        } else {
            strMaNH = cotVO.getNhkb_nhan();
        }
        DMNHangDAO dmdao = new DMNHangDAO(conn);
        DMNHangHOVO dmvo = null;
        String strMa3SoNH = "";


        if (type == null) {
            vtParam = new Vector();
            vtParam.add(new Parameter(strMaNH, true));
            dmvo = dmdao.getDMNHangHO(" and a.ma_nh = ? ", vtParam);
            APP_RECIEVE_CODE = dmvo.getMa_ung_dung();
            APP_RECIEVE_NAME = dmvo.getTen_ung_dung();
            strMa3SoNH = dmvo.getMa_dv();
            if (dmvo == null) {
                throw (new TTSPException()).createException("TTSP-1009",
                                                            "Kh&#244;ng t&#236;m th&#7845;y m&#227; &#7913;ng d&#7909;ng nh&#7853;n t&#432;&#417;ng &#7913;ng v&#7899;i m&#227; ng&#226;n h&#224;ng nh&#7853;n\n Duy&#7879;t l&#7879;nh kh&#244;ng th&#224;nh c&#244;ng.");
            }
        } else {
            APP_RECIEVE_CODE = type;
            APP_RECIEVE_NAME = "TCS Phoi hop thu";
            if("02".equals(cotVO.getLoai_cot())){
              return "";
            }
        }
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
        if(currentCOTvo.getDong_y() == null || "".equals(currentCOTvo.getDong_y())){
          sbMsg.append(currentCOTvo.getId());
        }else{
          sbMsg.append(currentCOTvo.getCot_id());
        }
        sbMsg.append("</MT_ID>");
        sbMsg.append("<SEND_BANK>");
        sbMsg.append(AppConstants.KBNN_TW_BANK_CODE);
        sbMsg.append("</SEND_BANK>");
        sbMsg.append("<RECEIVE_BANK>");
        //        if(type==null)
        //          sbMsg.append(strMaNH);
        //        else{
        if (AppConstants.KBNN_TW_BANK_CODE.equals(cotVO.getNhkb_chuyen())) {
            sbMsg.append(cotVO.getNhkb_nhan());
        } else {
            sbMsg.append(cotVO.getNhkb_chuyen());
        }
        //        }
        sbMsg.append("</RECEIVE_BANK>");
        sbMsg.append("<CREATED_DATE>");
        if(cotVO.getNgay_lap() == null){
          sbMsg.append(StringUtil.DateToString(new Date(),
                                               AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        }else{
          sbMsg.append(StringUtil.DateToString(cotVO.getNgay_lap(),
                                             AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        }
        sbMsg.append("</CREATED_DATE>");
        sbMsg.append("<CREATOR>");
        if (null == currentCOTvo.getMa_nsd() || "".equals(currentCOTvo.getMa_nsd())) {
            sbMsg.append(cotVO.getMa_nsd());
        } else {
            sbMsg.append(currentCOTvo.getMa_nsd());
        }
        sbMsg.append("</CREATOR>");
        sbMsg.append("<VERIFIED_DATE>");
        sbMsg.append(StringUtil.DateToString(new Date(),
                                             AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        sbMsg.append("</VERIFIED_DATE>");
        sbMsg.append("<MANAGER>");
        sbMsg.append(currentCOTvo.getMa_nsd_ks());
        sbMsg.append("</MANAGER>");
        sbMsg.append("<F20>");
        sbMsg.append(currentCOTvo.getId());
        sbMsg.append("</F20>");
        if(currentCOTvo.getDong_y() == null || "".equals(currentCOTvo.getDong_y())){
          sbMsg.append("<F21/>");
        }else{
          sbMsg.append("<F21>");
          sbMsg.append(currentCOTvo.getId());
          sbMsg.append("</F21>");
        }        
        sbMsg.append("<F79>");
        if(currentCOTvo.getDong_y() == null || "".equals(currentCOTvo.getDong_y())){
          sbMsg.append(cotVO.getLydo_cot() == null ? "" :
                       cotVO.getLydo_cot());
        }else{
          sbMsg.append(cotVO.getLydo_cot_nh());
        }
        sbMsg.append("</F79>");
        if(currentCOTvo.getDong_y() == null || "".equals(currentCOTvo.getDong_y())){
          sbMsg.append("<F79C1/>");
        }else{
          sbMsg.append("<F79C1>");
          sbMsg.append(currentCOTvo.getDong_y());
          sbMsg.append("</F79C1>");
        }
        sbMsg.append("<F79C2>");
        sbMsg.append(cotVO.getLydo_cot() == null ? "" :
                     cotVO.getLydo_cot());
        sbMsg.append("</F79C2>");
        sbMsg.append("<F79C3>");
        if ("00".equals(cotVO.getLoai_cot())) { // toan he thong
            sbMsg.append("HT");
        } else if ("01".equals(cotVO.getLoai_cot())) {
            sbMsg.append("DV");
        } else {
            sbMsg.append("TN");
        }
        sbMsg.append("</F79C3>");
        if ("00".equals(cotVO.getLoai_cot())) {
            sbMsg.append("<F79C4>");
            sbMsg.append(cotVO.getNew_cot());
            sbMsg.append("</F79C4>");
            if (cotVO.getCur_cot() != null) {
                sbMsg.append("<F79C5>");
                sbMsg.append(cotVO.getCur_cot());
                sbMsg.append("</F79C5>");
            } else {
                sbMsg.append("<F79C5/>");
            }
        } else {
            sbMsg.append("<F79C4/>");
            sbMsg.append("<F79C5/>");
        }
        sbMsg.append("<F79C6>");
        sbMsg.append(StringUtil.DateToString(new Date(),
                                             AppConstants.DATE_FORMAT_SEND_ORDER));
        sbMsg.append("</F79C6>");
        //ManhNV-20150421
        if ("02".equals(cotVO.getLoai_cot())) {
            sbMsg.append("<F79C7>");
            sbMsg.append(cotVO.getTn_moi());
            sbMsg.append("</F79C7>");
            sbMsg.append("<F79C8>");
            sbMsg.append(cotVO.getTn_cu());
            sbMsg.append("</F79C8>");
        } else {
            sbMsg.append("<F79C7/>");
            sbMsg.append("<F79C8/>");
        }
        //**************
        sbMsg.append("<F79D>");
        if ("01".equals(cotVO.getLoai_cot())) { //tung dia phuong
            String ma_kb_huyen =
                cotVO.getMa_kb_huyen().replace(",", "").trim();
            String cot_cu_list = cotVO.getCot_cu_list()==null?"":cotVO.getCot_cu_list().replace(",", "").trim();
            String nhkb_chuyen = "";
            int nCOTIndex = 0;
            for (int i = 0; i < ma_kb_huyen.length(); i++) {
                if (i % 8 == 0) {
                    sbMsg.append("<ROW>");
                    nhkb_chuyen = ma_kb_huyen.substring(i, i + 8);
                    vtParam = new Vector();
                    vtParam.add(new Parameter(nhkb_chuyen, true));
                    DMNHangVO nhVO =
                        dmdao.getNH299(" and d.ma_nh = ? and b.ma_nh like '__" +
                                       strMa3SoNH + "%' and rownum = 1",
                                       vtParam);

                    sbMsg.append("<F79D1>");
                    sbMsg.append(cotVO.getNew_cot());
                    sbMsg.append("</F79D1>");
                    
//                    if(currentCOTvo.getDong_y() == null || "".equals(currentCOTvo.getDong_y())){
//                      String whereCondition =
//                          " a.ma_nh_kb = ? and a.ma_nh = ? and a.ngay_gd = trunc(sysdate) ";
//                      Vector vparam = new Vector();
//                      vparam.add(new Parameter(nhkb_chuyen, true));
//                      vparam.add(new Parameter(nhVO.getMa_nh(), true));
//                      TSoCutOfTimeVO tSoCOTvo =
//                          tsoCOT.getTSoCTO(whereCondition, vparam);
//                      if (tSoCOTvo == null) {
//                          throw new NullPointerException("KB : " + nhkb_chuyen +
//                                                         " khong the noi gio vi chua khai bao gio COT cho don vi nay");
//                      } else {
//                          sbMsg.append("<F79D2>");
//                          sbMsg.append(tSoCOTvo.getCut_of_time());
//                          sbMsg.append("</F79D2>");
//                      }
//                    }else{
                      sbMsg.append("<F79D2>");
                      sbMsg.append(cot_cu_list.substring(nCOTIndex, nCOTIndex + 5));
                      sbMsg.append("</F79D2>");
//                    }
                    sbMsg.append("<F79D3>");
                    sbMsg.append(nhkb_chuyen); //cotVO.getNhkb_chuyen()
                    sbMsg.append("</F79D3>");
                    sbMsg.append("<F79D4>");
                    sbMsg.append(nhVO.getMa_nh());
                    sbMsg.append("</F79D4>");
                    sbMsg.append("</ROW>");
                    nCOTIndex = nCOTIndex + 5;
                }

            }
        }

        sbMsg.append("</F79D>");
        sbMsg.append("</BODY>");
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
                new Exception("Loi ghi log(com.seatech.ttsp.proxy.giaodien.bank.Send99.sendMessage()): " +
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
        vtParam.add(new Parameter("MQ_NAME_COT", true));

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
            } else if (strTenTS.equalsIgnoreCase("MQ_NAME_COT")) {
                MQ_NAME = tsVO.getGiatri_ts();
            } else if (strTenTS.equalsIgnoreCase("MQ_MANAGER_NAME")) {
                MQ_MANAGER_NAME = tsVO.getGiatri_ts();
            }
        }
    }

    public static void main(String[] args) {
        try {
            AppDAO app = new AppDAO();
            Connection conn = app.getConnectionTest();
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }
}
