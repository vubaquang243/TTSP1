package com.seatech.ttsp.proxy.giaodien.mq;


import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;

import java.io.BufferedWriter;
import java.io.FileWriter;


public class MQClient {
    public MQClient() {
        super();
    }
    private static MQQueueManager qMgr;
    private static String mqInputName;
    String msg;

    public void putMsgToQueue(String strMsg) throws Exception {       
        try {
            // Create file
            FileWriter fstream = new FileWriter("c:/msg.xml");
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(strMsg);
            
            //Close the output stream
            out.close();
        } catch (Exception e) { //Catch exception if any
            throw e;
        }
    }


    public void putMsgToQueue(String xmlRequest, String strHostName,
                              String strChanel, String strPort,
                              String strQMName,
                              String QName) throws Exception {       
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
//            String a = ""+MQC.MQCA_Q_NAME;
//            System.out.println("No day="+a);
            MQMessage mqMessage = new MQMessage();
            mqMessage.format = MQConstants.MQFMT_STRING;
            mqMessage.characterSet = 1208;
            mqMessage.writeString(xmlRequest);
            //            byte[] bMsg = xmlRequest.getBytes("UTF-8");
            //            mqMessage.write(bMsg);

            queueIn.put(mqMessage, putOptions);

        } catch (Exception e) {
            throw e;
        } finally {
            disconnectMQ();
        }
    }

    public void disconnectMQ() throws Exception {
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
    public static void main(String[] args) {
     MQClient c = new MQClient();
     try{
     c.putMsgToQueue("a", "10.96.1.93", "JAVA_CHANNEL", "1414", "KBNNMQMGR", "TTSP.INBOX.QUEUE");
       

      // JAVA_CHANNEL
       
     }catch(Exception e){
       e.printStackTrace();
     }
    }
}
