package com.seatech.ttsp.proxy.giaodien;

public class MsgQueueVO {
    private String mt_id;
    private String msg;
    private String insert_date;
    private String send_date;
    private String tran_code;
    private String status;
    private String job_session;
    private String sender_code;
  private String receiver_code;
  private String other_info;
  private String msg_id;

    public MsgQueueVO() {
        super();
    }

    public void setMt_id(String mt_id) {
        this.mt_id = mt_id;
    }

    public String getMt_id() {
        return mt_id;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setSend_date(String send_date) {
        this.send_date = send_date;
    }

    public String getSend_date() {
        return send_date;
    }

 
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setJob_session(String job_session) {
        this.job_session = job_session;
    }

    public String getJob_session() {
        return job_session;
    }

    public void setTran_code(String tran_code) {
        this.tran_code = tran_code;
    }

    public String getTran_code() {
        return tran_code;
    }

    public void setSender_code(String sender_code) {
        this.sender_code = sender_code;
    }

    public String getSender_code() {
        return sender_code;
    }

    public void setReceiver_code(String receiver_code) {
        this.receiver_code = receiver_code;
    }

    public String getReceiver_code() {
        return receiver_code;
    }

    public void setOther_info(String other_info) {
        this.other_info = other_info;
    }

    public String getOther_info() {
        return other_info;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }
}
