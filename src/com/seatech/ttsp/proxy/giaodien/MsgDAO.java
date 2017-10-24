package com.seatech.ttsp.proxy.giaodien;


import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;

import com.seatech.framework.utils.DateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;


public class MsgDAO extends AppDAO {
    Connection conn = null;
    String strValueObject = "com.seatech.ttsp.proxy.giaodien.MsgVO";

    public MsgDAO(Connection conn) {
        this.conn = conn;
    }

    public int insert(MsgVO msg) throws Exception {
        try {
            PreparedStatement pstmt = null;

            Long nId = getSeqNextValue("sp_msg_log_seq", conn);

            StringBuffer szSQL = new StringBuffer();
            szSQL.append("insert into sp_msg_log (id, msg_id, msg_content, error_code, error_desc, ");
            szSQL.append("msg_type, insert_date, sender, reciever) values (?,?,?,?,?,?,sysdate,?,?)");
            pstmt = conn.prepareStatement(szSQL.toString());

            java.sql.Clob clob =
                oracle.sql.CLOB.createTemporary(conn, false, oracle.sql.CLOB.DURATION_SESSION);
            clob.setString(1, msg.getMsg_content());

            pstmt.setLong(1, nId);
            pstmt.setString(2, msg.getMsg_id());
            pstmt.setClob(3, clob);
            pstmt.setString(4, msg.getError_code());
            pstmt.setString(5, msg.getError_desc());
            pstmt.setString(6, msg.getMsg_type());
            pstmt.setString(7, msg.getSender());
            pstmt.setString(8, msg.getReciever());

            return pstmt.executeUpdate(); //update vao sp_msg_log
        } catch (Exception e) {
            throw new Exception("MsgDAO.insert(): " + e.getMessage());
        }
    }
    public int countReSend(String strType, String strWhere) throws Exception {
      if("".equalsIgnoreCase(strType)){
        strType = "sp_msg_resend";
      }
      String strSQL =
          "select count(0) c  from "+strType+" where ghi_chu = '0' or ghi_chu is null "+ strWhere;
      ResultSet rs = executeSelectStatement(strSQL, null, conn);
      if(rs.next()){
        return rs.getInt("c");
      }
      return 0;
    }
    public int reSend(String strHostName, String strChanel, String strPort,
                      String strQMName, String QName, String strType, String strWhere) throws Exception {
        MQQueueManager qMgr=null;
        String mqInputName="";
        int iCount=0;
        try {

            MQEnvironment.hostname = strHostName;
            MQEnvironment.channel = strChanel; //Kenh ket noi MQ
            MQEnvironment.port = Integer.parseInt(strPort); // 1414;
            qMgr = new MQQueueManager(strQMName); //Ten Queue man
            mqInputName = QName; //Ten queue put
            //            mqOutputName = "OUTPUT";//

            int openInputOptions =
                MQConstants.MQOO_OUTPUT + MQConstants.MQOO_FAIL_IF_QUIESCING;

            MQQueue queueIn =
                qMgr.accessQueue(mqInputName, openInputOptions, null, null,
                                 null); // "EBANK.INPUT"

            MQPutMessageOptions putOptions = new MQPutMessageOptions();
            putOptions.options =
                    MQC.MQPMO_NEW_MSG_ID | MQC.MQPMO_NEW_CORREL_ID;
            
            
             
            
            if("".equalsIgnoreCase(strType)){
              strType = "sp_msg_resend";
            }
            String strSQL =
                "select msg_id, msg_content, msg_type, ghi_chu  from "+strType+" where (ghi_chu = '0' or ghi_chu is null) "+ strWhere;
            Collection coll =
                executeSelectStatement(strSQL, null, strValueObject, conn);
            
            Iterator iter = coll.iterator();
            MsgVO msgVO = null;
            while (iter.hasNext()) {
                msgVO = (MsgVO)iter.next();
                String xmlRequest = msgVO.getMsg_content();
                MQMessage mqMessage = new MQMessage();
                mqMessage.format = MQConstants.MQFMT_STRING;
                mqMessage.characterSet = 1208;
                mqMessage.writeString(xmlRequest);
                queueIn.put(mqMessage, putOptions);

                strSQL =
                        "Update "+strType+" Set ghi_chu = ? Where msg_id = ? ";
                Vector vtParam = new Vector();
                vtParam.add(new Parameter("1", true));
                vtParam.add(new Parameter(msgVO.getMsg_id(), true));

                executeStatement(strSQL, vtParam, conn);
                iCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (qMgr != null) {
                    if (qMgr.isConnected()) {
                        qMgr.disconnect();
                    }
                }
            } catch (MQException e) {
                throw e;
            }
        }
        return iCount;
    }
  public int reSendLTT(String strHostName, String strChanel, String strPort,
                    String strQMName, String QName,String strWhere) throws Exception {
      MQQueueManager qMgr=null;
      String mqInputName="";
      int iCount=0;
      try {

          MQEnvironment.hostname = strHostName;
          MQEnvironment.channel = strChanel; //Kenh ket noi MQ
          MQEnvironment.port = Integer.parseInt(strPort); // 1414;
          qMgr = new MQQueueManager(strQMName); //Ten Queue man
          mqInputName = QName; //Ten queue put
          //            mqOutputName = "OUTPUT";//

          int openInputOptions =
              MQConstants.MQOO_OUTPUT + MQConstants.MQOO_FAIL_IF_QUIESCING;

          MQQueue queueIn =
              qMgr.accessQueue(mqInputName, openInputOptions, null, null,
                               null); // "EBANK.INPUT"

          MQPutMessageOptions putOptions = new MQPutMessageOptions();
          putOptions.options =
                  MQC.MQPMO_NEW_MSG_ID | MQC.MQPMO_NEW_CORREL_ID;
          
        
          String strSQL =
              "select msg_id, msg_content, msg_type, ghi_chu  from sp_msg_log where "+ strWhere;
          Collection coll =
              executeSelectStatement(strSQL, null, strValueObject, conn);
          
          Iterator iter = coll.iterator();
          MsgVO msgVO = null;
          while (iter.hasNext()) {
              msgVO = (MsgVO)iter.next();
              String message = msgVO.getMsg_content();
              boolean messageIsTrue = message.indexOf("<SPARE3/>")==-1?false:true;
              String xmlRequest = null;
              if(!messageIsTrue){
                  messageIsTrue = message.indexOf("<SPARE3></SPARE3>")==-1?false:true;
                if(!messageIsTrue){
                  int start = msgVO.getMsg_content().indexOf("<SPARE3>");
                  int last = msgVO.getMsg_content().indexOf("</SPARE3>")+9;
                  String tempR = msgVO.getMsg_content().substring(start, last);
                  xmlRequest = msgVO.getMsg_content().replace(tempR, "<SPARE3/>");
                }
              }else{
                  xmlRequest = msgVO.getMsg_content();
              }
              
              MQMessage mqMessage = new MQMessage();
              mqMessage.format = MQConstants.MQFMT_STRING;
              mqMessage.characterSet = 1208;
              mqMessage.writeString(xmlRequest);
              queueIn.put(mqMessage, putOptions);

//              strSQL =
//                      "Update "+strType+" Set ghi_chu = ? Where msg_id = ? ";
//              Vector vtParam = new Vector();
//              vtParam.add(new Parameter("1", true));
//              vtParam.add(new Parameter(msgVO.getMsg_id(), true));

//              executeStatement(strSQL, vtParam, conn);
              iCount++;
          }
      } catch (Exception e) {
          e.printStackTrace();
          throw e;
      } finally {
          try {
              if (qMgr != null) {
                  if (qMgr.isConnected()) {
                      qMgr.disconnect();
                  }
              }
          } catch (MQException e) {
              throw e;
          }
      }
      return iCount;
  }
}
