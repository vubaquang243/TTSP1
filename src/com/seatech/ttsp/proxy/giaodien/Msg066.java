package com.seatech.ttsp.proxy.giaodien;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.utils.StringUtil;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.dchieu.DChieu1DAO;
import com.seatech.ttsp.dchieu.DNQTVO;
import com.seatech.ttsp.dmnh.DMNHangDAO;
import com.seatech.ttsp.dmnh.DMNHangHOVO;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;


public class Msg066 {
  private Connection conn;
  private String APP_SEND_CODE = AppConstants.APP_SEND_CODE;
  private String APP_SEND_NAME = AppConstants.APP_SEND_NAME;
  private String APP_RECIEVE_CODE = "";
  private String APP_RECIEVE_NAME = "";
  private String TRAN_CODE = "066";
  private String TRAN_CODE_NGOAI_TE = "086";
  
  private String MQ_HOSTNAME;
  private String MQ_CHANEL;
  private String MQ_PORT;
  private String MQ_MANAGER_NAME;
  private String MQ_NAME;
  
  public Msg066(Connection conn) {
      this.conn = conn;
  }
  /**
   * @code: Manh
   * @see: Sua theo giai phap truyen lenh moi (qua job). chong truyen 2 lan.
   * @since: 26/03/2017
   * @link: 20170326
   * **/
  private void setThamSoHeThong() throws Exception {
      ThamSoHThongDAO tshtDAO = new ThamSoHThongDAO(conn);
      String strWhereClauseTS = "";

      strWhereClauseTS = " and ten_ts in (?,?,?,?,?)";

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
  public String sendMessage(String strID, String strManager) throws Exception{
    String strMsgID = "";
    StringBuffer sbBody = new StringBuffer();
    StringBuffer sbMsg = new StringBuffer();
    
    TTSPUtils ttspUtils = new TTSPUtils(conn);
    //Lay tham so he thong cho hach toan tabmis
    setThamSoHeThong();
    //Lay du lieu 066
    DChieu1DAO dc1DAO = new DChieu1DAO(conn);
    String strWhere = " and a.id = ?";
    Vector vParam = new Vector();
    vParam.add(new Parameter(strID, true));
    DNQTVO data066 = dc1DAO.getData066SendBank(strWhere, vParam);
    if(data066 == null){
      throw new Exception("Khong tim duoc du lieu dien 066 theo ID="+strID);
    }
    //Lay thong tin NH nhan
    DMNHangDAO dmDAO = new DMNHangDAO(conn);
    String strMa3SoNH = data066.getNhkb_nhan().substring(2, 5);
    String strWhereClause = " and a.ma_dv = ? ";
    Vector parameters = new Vector();
    parameters.add(new Parameter(strMa3SoNH, true));
    DMNHangHOVO nhHOvo = dmDAO.getDMNHangHO(strWhereClause, parameters);

    if (nhHOvo == null) {
        throw new Exception("Khong tim duoc thong tin ngan hang");
    }
    APP_RECIEVE_CODE = nhHOvo.getMa_ung_dung();
    APP_RECIEVE_NAME = nhHOvo.getTen_ung_dung();
    
    sbBody.append("<BODY>");
    
    sbBody.append("<MT_ID>");
    sbBody.append(data066.getId());
    sbBody.append("</MT_ID>");

    sbBody.append("<SEND_BANK>");
    sbBody.append(data066.getNhkb_chuyen());
    sbBody.append("</SEND_BANK>");

    sbBody.append("<RECEIVE_BANK>");
    sbBody.append(data066.getNhkb_nhan());
    sbBody.append("</RECEIVE_BANK>");

    sbBody.append("<CREATED_DATE>");
    sbBody.append(data066.getNgay_tao());
    sbBody.append("</CREATED_DATE>");

    sbBody.append("<CREATOR>");
    sbBody.append(data066.getNguoi_tao());
    sbBody.append("</CREATOR>");

    sbBody.append("<MANAGER>");
    sbBody.append(strManager);
    sbBody.append("</MANAGER>");

    sbBody.append("<VERIFIED_DATE>");
    sbBody.append(StringUtil.DateToString(new Date(), "dd-MM-yyyy HH:mm:ss"));
    sbBody.append("</VERIFIED_DATE>");

    sbBody.append("<NGAY_QTOAN>");
    sbBody.append(data066.getNgay_qtoan());
    sbBody.append("</NGAY_QTOAN>");

    sbBody.append("<QTOAN_THU>");
    sbBody.append(data066.getQtoan_thu());
    sbBody.append("</QTOAN_THU>");

    sbBody.append("<QTOAN_CHI>");
    sbBody.append(data066.getQtoan_chi());
    sbBody.append("</QTOAN_CHI>");

    sbBody.append("<NOI_DUNG>");
    sbBody.append(StringUtil.xmlSpeReplace(data066.getNdung_qtoan().replace("\n", "").replace("\r", "")));
    sbBody.append("</NOI_DUNG>");
    
    sbBody.append("</BODY>");
    //Tao msg header
    strMsgID = ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);
    MsgHeader msgHeader = new MsgHeader();
    msgHeader.setVersion(AppConstants.VERSION_MSG);
    msgHeader.setSender_code(APP_SEND_CODE);
    msgHeader.setSender_name(APP_SEND_NAME);
    msgHeader.setReveiver_code(APP_RECIEVE_CODE);
    msgHeader.setReceiver_name(APP_RECIEVE_NAME);
    msgHeader.setTran_code(TRAN_CODE);
    msgHeader.setSend_date(StringUtil.DateToString(new Date(), "dd-MM-yyyy HH:mm:ss"));
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

    //20170326-BEGIN------------------------------------------------------------
    //Build log msg
//    MsgDAO msgDAO = new MsgDAO(conn);
//    MsgVO msg = new MsgVO();
//
//    msg.setMsg_content(sbMsg.toString());
//    msg.setMsg_id(strMsgID);
//    msg.setMsg_type(TRAN_CODE);
//    msg.setSender(APP_SEND_CODE);
//    msg.setReciever(APP_RECIEVE_CODE);
//    //Put msg to queue
//    MQClient client = new MQClient();
//    client.putMsgToQueue(sbMsg.toString(), MQ_HOSTNAME, MQ_CHANEL, MQ_PORT,
//                         MQ_MANAGER_NAME, MQ_NAME);
//    //Ghi log
//    try {
//        msgDAO.insert(msg);
//    } catch (Exception e) {
//        Exception ex =
//            new Exception("Loi ghi log(com.seatech.ttsp.proxy.giaodien.bank.SendLTToan.sendMessage()): " +
//                          e.getMessage());
//        ex.printStackTrace();
//    }
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
    //20170326-END--------------------------------------------------------------
    return strMsgID;
  }
  public String sendMessageNgoaiTe(String strID, String strManager) throws Exception{
    String strMsgID = "";
    StringBuffer sbBody = new StringBuffer();
    StringBuffer sbMsg = new StringBuffer();
    
    TTSPUtils ttspUtils = new TTSPUtils(conn);
    //Lay tham so he thong cho hach toan tabmis
    setThamSoHeThong();
    //Lay du lieu 066
    DChieu1DAO dc1DAO = new DChieu1DAO(conn);
    String strWhere = " and a.id = ?";
    Vector vParam = new Vector();
    vParam.add(new Parameter(strID, true));
    DNQTVO data066 = dc1DAO.getData066SendBank(strWhere, vParam);
    if(data066 == null){
      throw new Exception("Khong tim duoc du lieu dien 066 theo ID="+strID);
    }
    //Lay thong tin NH nhan
    DMNHangDAO dmDAO = new DMNHangDAO(conn);
    String strMa3SoNH = data066.getNhkb_nhan().substring(2, 5);
    String strWhereClause = " and a.ma_dv = ? ";
    Vector parameters = new Vector();
    parameters.add(new Parameter(strMa3SoNH, true));
    DMNHangHOVO nhHOvo = dmDAO.getDMNHangHO(strWhereClause, parameters);

    if (nhHOvo == null) {
        throw new Exception("Khong tim duoc thong tin ngan hang");
    }
    APP_RECIEVE_CODE = nhHOvo.getMa_ung_dung();
    APP_RECIEVE_NAME = nhHOvo.getTen_ung_dung();
    
    sbBody.append("<BODY>");
    
    sbBody.append("<MT_ID>");
    sbBody.append(data066.getId());
    sbBody.append("</MT_ID>");

    sbBody.append("<SEND_BANK>");
    sbBody.append(data066.getNhkb_chuyen());
    sbBody.append("</SEND_BANK>");

    sbBody.append("<RECEIVE_BANK>");
    sbBody.append(data066.getNhkb_nhan());
    sbBody.append("</RECEIVE_BANK>");

    sbBody.append("<CREATED_DATE>");
    sbBody.append(data066.getNgay_tao());
    sbBody.append("</CREATED_DATE>");

    sbBody.append("<CREATOR>");
    sbBody.append(data066.getNguoi_tao());
    sbBody.append("</CREATOR>");

    sbBody.append("<MANAGER>");
    sbBody.append(strManager);
    sbBody.append("</MANAGER>");

    sbBody.append("<VERIFIED_DATE>");
    sbBody.append(StringUtil.DateToString(new Date(), "dd-MM-yyyy HH:mm:ss"));
    sbBody.append("</VERIFIED_DATE>");

    sbBody.append("<NGAY_QTOAN>");
    sbBody.append(data066.getNgay_qtoan());
    sbBody.append("</NGAY_QTOAN>");

    sbBody.append("<QTOAN_THU>");
    sbBody.append(data066.getQtoan_thu());
    sbBody.append("</QTOAN_THU>");

    sbBody.append("<QTOAN_CHI>");
    sbBody.append(data066.getQtoan_chi());
    sbBody.append("</QTOAN_CHI>");
    
    sbBody.append("<MA_NT>");
    sbBody.append(data066.getLoai_tien());
    sbBody.append("</MA_NT>");

    sbBody.append("<NOI_DUNG>");
    sbBody.append(StringUtil.xmlSpeReplace(data066.getNdung_qtoan().replace("\n", "").replace("\r", "")));
    sbBody.append("</NOI_DUNG>");
    
    sbBody.append("</BODY>");
    //Tao msg header
    strMsgID = ttspUtils.getMsgLTTID(this.APP_SEND_CODE, this.APP_RECIEVE_CODE);
    MsgHeader msgHeader = new MsgHeader();
    msgHeader.setVersion(AppConstants.VERSION_MSG);
    msgHeader.setSender_code(APP_SEND_CODE);
    msgHeader.setSender_name(APP_SEND_NAME);
    msgHeader.setReveiver_code(APP_RECIEVE_CODE);
    msgHeader.setReceiver_name(APP_RECIEVE_NAME);
    msgHeader.setTran_code(TRAN_CODE_NGOAI_TE);
    msgHeader.setSend_date(StringUtil.DateToString(new Date(), "dd-MM-yyyy HH:mm:ss"));
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

    //20170326-BEGIN------------------------------------------------------------
    //Build log msg
//    MsgDAO msgDAO = new MsgDAO(conn);
//    MsgVO msg = new MsgVO();
//
//    msg.setMsg_content(sbMsg.toString());
//    msg.setMsg_id(strMsgID);
//    msg.setMsg_type(TRAN_CODE_NGOAI_TE);
//    msg.setSender(APP_SEND_CODE);
//    msg.setReciever(APP_RECIEVE_CODE);
//    //Put msg to queue
//    MQClient client = new MQClient();
//    client.putMsgToQueue(sbMsg.toString(), MQ_HOSTNAME, MQ_CHANEL, MQ_PORT,
//                         MQ_MANAGER_NAME, MQ_NAME);
//    //Ghi log
//    try {
//        msgDAO.insert(msg);
//    } catch (Exception e) {
//        Exception ex =
//            new Exception("Loi ghi log(com.seatech.ttsp.proxy.giaodien.bank.SendLTToan.sendMessage()): " +
//                          e.getMessage());
//        ex.printStackTrace();
//    }
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
    //20170326-END------------------------------------------------------------
    return strMsgID;
  }
  public static void main(String[] args) {
      try{
    AppDAO d = new AppDAO();
    Connection conn = d.getConnectionTest();
    Msg066 m = new Msg066(conn);
          m.sendMessage("1470106600000403", "DCMM");
          conn.commit();
      }catch(Exception e){
        e.printStackTrace();
      }
  }
}
