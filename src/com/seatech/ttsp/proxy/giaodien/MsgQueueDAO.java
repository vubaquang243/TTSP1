package com.seatech.ttsp.proxy.giaodien;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MsgQueueDAO {
    Connection conn = null;

    public MsgQueueDAO(Connection conn) {
        this.conn = conn;
    }

    public int insert(MsgQueueVO msg) throws Exception {
        try {
            PreparedStatement pstmt = null;


            StringBuffer szSQL = new StringBuffer();
            szSQL.append("insert into sp_msg_queue (mt_id, msg, status, insert_date, sent_date, tran_code, job_session, msg_id, sender_code, receiver_code, other_info)");
            szSQL.append(" values (?,?,?,SYSDATE,?,?,?,?,?,?,?)");
            pstmt = conn.prepareStatement(szSQL.toString());

            java.sql.Clob clob =
                oracle.sql.CLOB.createTemporary(conn, false, oracle.sql.CLOB.DURATION_SESSION);
            clob.setString(1, msg.getMsg());

            pstmt.setString(1, msg.getMt_id());
            pstmt.setClob(2, clob);
            pstmt.setString(3, msg.getStatus());
            pstmt.setString(4, msg.getSend_date());
            pstmt.setString(5, msg.getTran_code());
            pstmt.setString(6, msg.getJob_session());
            pstmt.setString(7, msg.getMsg_id());
            pstmt.setString(8, msg.getSender_code());
            pstmt.setString(9, msg.getReceiver_code());
            pstmt.setString(10, msg.getOther_info());

            return pstmt.executeUpdate(); //update vao sp_msg_log
        } catch (Exception e) {
            throw new Exception("MsgQueueDAO.insert(): " + e.getMessage());
        }
    }
}
