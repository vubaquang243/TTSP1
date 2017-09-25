package com.seatech.ttsp.proxy.giaodien;


import com.seatech.framework.AppConstants;
import com.seatech.framework.utils.StringUtil;

import java.util.Date;

public class MsgHeader {
    public MsgHeader() {
        super();
    }
    private String header;
    private String version;
    private String sender_code;
    private String sender_name;
    private String reveiver_code;
    private String receiver_name;
    private String tran_code;
    private String msg_id;
    private String msg_refid;
    private String original_code;
    private String original_name;
    private String error_code;
    private String error_desc;
    private String spare1;
    private String spare2;
    private String spare3;
    private String send_date;

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        StringBuffer sbHeader = new StringBuffer();
        sbHeader.append("<HEADER>");
        sbHeader.append("<VERSION>");
        if (this.version == null || "".equals(this.version)) {
            sbHeader.append(AppConstants.VERSION_MSG);
        } else {
            sbHeader.append(this.version);
        }
        sbHeader.append("</VERSION>");
        sbHeader.append("<SENDER_CODE>");
        sbHeader.append(this.sender_code);
        sbHeader.append("</SENDER_CODE>");
        sbHeader.append("<SENDER_NAME>");
        sbHeader.append(this.sender_name);
        sbHeader.append("</SENDER_NAME>");
        sbHeader.append("<RECEIVER_CODE>");
        sbHeader.append(this.reveiver_code);
        sbHeader.append("</RECEIVER_CODE>");
        sbHeader.append("<RECEIVER_NAME>");
        sbHeader.append(this.receiver_name);
        sbHeader.append("</RECEIVER_NAME>");
        sbHeader.append("<TRAN_CODE>");
        sbHeader.append(this.tran_code);
        sbHeader.append("</TRAN_CODE>");
        sbHeader.append("<MSG_ID>");
        sbHeader.append(this.msg_id);
        sbHeader.append("</MSG_ID>");

        if (this.msg_id == null || "".equals(this.msg_id)) {
            sbHeader.append("<MSG_REFID/>");
        } else {
            sbHeader.append("<MSG_REFID>");
            sbHeader.append(this.msg_refid);
            sbHeader.append("</MSG_REFID>");
        }
        sbHeader.append("<SEND_DATE>");

        if (this.send_date != null && !"".equals(this.send_date)) {
            sbHeader.append(this.send_date);
        } else {
            sbHeader.append(StringUtil.DateToString(new Date(),
                                                    AppConstants.DATE_TIME_FORMAT_SEND_ORDER));
        }
        sbHeader.append("</SEND_DATE>");

        if (this.original_code == null || "".equals(this.original_code)) {
            sbHeader.append("<ORIGINAL_CODE/>");
        } else {
            sbHeader.append("<ORIGINAL_CODE>");
            sbHeader.append(this.original_code);
            sbHeader.append("</ORIGINAL_CODE>");
        }
        if (this.original_name == null || "".equals(this.original_name)) {
            sbHeader.append("<ORIGINAL_NAME/>");
        } else {
            sbHeader.append("<ORIGINAL_NAME>");
            sbHeader.append(this.original_name);
            sbHeader.append("</ORIGINAL_NAME>");
        }
        sbHeader.append("<ORIGINAL_DATE/>");

        if (this.error_code == null || "".equals(this.error_code)) {
            sbHeader.append("<ERROR_CODE/>");
        } else {
            sbHeader.append("<ERROR_CODE>");
            sbHeader.append(this.error_code);
            sbHeader.append("</ERROR_CODE>");
        }

        if (this.error_desc == null || "".equals(this.error_desc)) {
            sbHeader.append("<ERROR_DESC/>");
        } else {
            sbHeader.append("<ERROR_DESC>");
            sbHeader.append(this.error_desc);
            sbHeader.append("</ERROR_DESC>");
        }

        if (this.spare1 == null || "".equals(this.spare1)) {
            sbHeader.append("<SPARE1/>");
        } else {
            sbHeader.append("<SPARE1>");
            sbHeader.append(this.spare1);
            sbHeader.append("</SPARE1>");
        }
        if (this.spare2 == null || "".equals(this.spare2)) {
            sbHeader.append("<SPARE2/>");
        } else {
            sbHeader.append("<SPARE2>");
            sbHeader.append(this.spare2);
            sbHeader.append("</SPARE2>");
        }
        if (this.spare3 == null || "".equals(this.spare3)) {
            sbHeader.append("<SPARE3/>");
        } else {
            sbHeader.append("<SPARE3>");
            sbHeader.append(this.spare3);
            sbHeader.append("</SPARE3>");
        }

        sbHeader.append("</HEADER>");
        return sbHeader.toString();
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setSender_code(String sender_code) {
        this.sender_code = sender_code;
    }

    public String getSender_code() {
        return sender_code;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setReveiver_code(String reveiver_code) {
        this.reveiver_code = reveiver_code;
    }

    public String getReveiver_code() {
        return reveiver_code;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setTran_code(String tran_code) {
        this.tran_code = tran_code;
    }

    public String getTran_code() {
        return tran_code;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_refid(String msg_refid) {
        this.msg_refid = msg_refid;
    }

    public String getMsg_refid() {
        return msg_refid;
    }

    public void setOriginal_code(String original_code) {
        this.original_code = original_code;
    }

    public String getOriginal_code() {
        return original_code;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_desc(String error_desc) {
        this.error_desc = error_desc;
    }

    public String getError_desc() {
        return error_desc;
    }

    public void setSpare1(String spare1) {
        this.spare1 = spare1;
    }

    public String getSpare1() {
        return spare1;
    }

    public void setSpare2(String spare2) {
        this.spare2 = spare2;
    }

    public String getSpare2() {
        return spare2;
    }

    public void setSpare3(String spare3) {
        this.spare3 = spare3;
    }

    public String getSpare3() {
        return spare3;
    }

    //    public String getHeader1() {
    //        return header;
    //    }

    public void setSend_date(String send_date) {
        this.send_date = send_date;
    }

    public String getSend_date() {
        return send_date;
    }
}
