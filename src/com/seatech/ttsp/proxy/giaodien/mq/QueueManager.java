package com.seatech.ttsp.proxy.giaodien.mq;


import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;

import com.seatech.ttsp.thamso.ThamSoHThongVO;

import java.util.Collection;
import java.util.Iterator;

public class QueueManager {
    public QueueManager() {
        super();
    }

    public QueueManager(Collection thamSoHThongList) {
        Iterator iter = thamSoHThongList.iterator();
        ThamSoHThongVO tsVO = null;
        String strTenTS = "";
        int counter = 0;
        while (iter.hasNext()) {
            tsVO = (ThamSoHThongVO)iter.next();
            if (counter == 5)
                break;
            if (tsVO != null) {
                strTenTS = tsVO.getTen_ts();
                if (strTenTS.equalsIgnoreCase("MQ_HOSTNAME")) {
                    this.hostName = tsVO.getGiatri_ts();
                    counter++;
                } else if (strTenTS.equalsIgnoreCase("MQ_CHANEL")) {
                    this.chanel = tsVO.getGiatri_ts();
                    counter++;
                } else if (strTenTS.equalsIgnoreCase("MQ_PORT")) {
                    this.port = tsVO.getGiatri_ts();
                    counter++;
                } else if (strTenTS.equalsIgnoreCase("MQ_NAME")) {
                    this.queueName = tsVO.getGiatri_ts();
                    counter++;
                } else if (strTenTS.equalsIgnoreCase("MQ_MANAGER_NAME")) {
                    this.queueManagerName = tsVO.getGiatri_ts();
                    counter++;
                }
            }
        }
    }
    private MQQueueManager queueManager;
    private String hostName;
    private String chanel;
    private String port;
    private String queueManagerName;
    private String queueName;

    public void setQueueManager(MQQueueManager queueManager) {
        this.queueManager = queueManager;
    }

    public MQQueueManager getQueueManager() {
        return queueManager;
    }

    public void setQueueManager(String strHostName, String strChanel,
                                String strPort,
                                String strQMName) throws Exception {
        try {
            MQEnvironment.hostname = strHostName;
            MQEnvironment.channel = strChanel; //Kenh ket noi MQ
            MQEnvironment.port = Integer.parseInt(strPort); // 1414;
            MQQueueManager queueManager = new MQQueueManager(strQMName);
            this.queueManager = queueManager;
        } catch (Exception ex) {
            throw new Exception("Loi xay ra khi KET NOI QUEUE MANAGER: " +
                                ex.getMessage());
        }
    }

    public void setQueueManager() throws Exception {
        try {
            MQEnvironment.hostname = this.hostName;
            MQEnvironment.channel = this.chanel; //Kenh ket noi MQ
            MQEnvironment.port = Integer.parseInt(this.port); // 1414;
            MQQueueManager queueManager =
                new MQQueueManager(this.queueManagerName);
            this.queueManager = queueManager;
        } catch (Exception ex) {
            throw new Exception("Loi xay ra khi KET NOI QUEUE MANAGER: " +
                                ex.getMessage());
        }
    }


    /*    public void putQueue(String msg, String QName) throws Exception {
        MQQueue queue = null;
        MQQueueManager queueManager = this.queueManager;
        MQMessage mqMessage = null;
        MQPutMessageOptions pmo = null;
        try {
            int openOptions = MQConstants.MQOO_OUTPUT;
            queue =
                    queueManager.accessQueue(QName, openOptions, null, null, null);

            pmo = new MQPutMessageOptions();
            pmo.options = CMQC.MQGMO_SYNCPOINT;
            mqMessage = new MQMessage();
            mqMessage.writeString(msg);
            queue.put(mqMessage, pmo);

        } catch (Exception ex) {
            throw new Exception("Loi xay ra khi PUT MSG TO QUEUE: " +
                                ex.getMessage());
        }
    }


    public void putQueue(MQQueueManager queueManager, String QName,
                         String msg) throws Exception {
        MQQueue queue = null;
        MQMessage mqMessage = null;
        MQPutMessageOptions pmo = null;
        try {
            int openOptions = MQConstants.MQOO_OUTPUT;
            queue =
                    queueManager.accessQueue(QName, openOptions, null, null, null);

            pmo = new MQPutMessageOptions();
            pmo.options = CMQC.MQGMO_SYNCPOINT;
            mqMessage = new MQMessage();
            mqMessage.writeString(msg);
            queue.put(mqMessage, pmo);

        } catch (Exception ex) {
            throw new Exception("Loi xay ra khi PUT MSG TO QUEUE: " +
                                ex.getMessage());
        }
    }*/

    /**
     * Put msg to queue
     * **/
    public void putMQ(String msg) throws Exception {
        MQQueue queue = null;
        MQMessage mqMessage = null;
        MQPutMessageOptions pmo = null;
        try {
            int openOptions = MQConstants.MQOO_OUTPUT;
            queue =
                    this.queueManager.accessQueue(this.queueName, openOptions, null,
                                                  null, null);

            pmo = new MQPutMessageOptions();
            pmo.options = CMQC.MQGMO_SYNCPOINT;
            mqMessage = new MQMessage();
            
            mqMessage.format = MQConstants.MQFMT_STRING;
            mqMessage.characterSet = 1208;
            
            mqMessage.writeString(msg);
            queue.put(mqMessage, pmo);

        } catch (Exception ex) {
            throw new Exception("Loi xay ra khi PUT MSG TO QUEUE: " +
                                ex.getMessage());
        }
    }

    public void commitMQ() throws Exception {
        try {
            queueManager.commit();
        } catch (Exception ex) {
            throw new Exception("Loi xay ra khi COMMIT QUEUE: " +
                                ex.getMessage());
        }
    }

    public void commitMQ(MQQueueManager queueManager) throws Exception {
        try {
            queueManager.commit();
        } catch (Exception ex) {
            throw new Exception("Loi xay ra khi COMMIT QUEUE: " +
                                ex.getMessage());
        }
    }

    public void rollbackMQ() throws Exception {
        try {
            if (queueManager != null) {
                queueManager.backout();
            }
        } catch (Exception ex) {
            throw new Exception("Loi xay ra khi ROLLBACK QUEUE: " +
                                ex.getMessage());
        }
    }

    public void rollbackMQ(MQQueueManager queueManager) throws Exception {
        try {
            if (queueManager != null) {
                queueManager.backout();
            }
        } catch (Exception ex) {
            throw new Exception("Loi xay ra khi ROLLBACK QUEUE: " +
                                ex.getMessage());
        }
    }

    public void disconnectMQ() throws Exception {
        try {
            if (this.queueManager != null) {
                if (this.queueManager.isConnected()) {
                    //BEGIN-20160107-manhnv-rollback truoc khi disconnect
                    queueManager.backout();
                    //END-20160107-manhnv-rollback truoc khi disconnect
                    this.queueManager.disconnect();
                }
            }
        } catch (MQException e) {
            throw new Exception("Loi xay ra khi DISCONNECT QUEUE: " +
                                e.getMessage());
        }
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setChanel(String chanel) {
        this.chanel = chanel;
    }

    public String getChanel() {
        return chanel;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setQueueManagerName(String queueManagerName) {
        this.queueManagerName = queueManagerName;
    }

    public String getQueueManagerName() {
        return queueManagerName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }
}
