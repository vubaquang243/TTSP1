package com.seatech.ttsp.user.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.strustx.AppAction;
import com.seatech.ttsp.proxy.giaodien.MsgDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;
import com.seatech.ttsp.user.form.ReSendForm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ReSendAllAction extends AppAction {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        ReSendForm frm = null;
        int iSent = 0;
        String checkSuccess = "FALSE";
        PreparedStatement pstmt = null;
        String lstID = "";
        String type = "";
        String user = "";
        String strSQL = "";
        int msg_number = 0;
        try {
            conn = getConnection(request);
            frm = (ReSendForm)form;
            HttpSession session = request.getSession();
            user = frm.getUsername();
            String pass = frm.getPassword();
            lstID = frm.getWhere() == null ? "" : frm.getWhere();

            String[] arrID;
            String strMaNSD =
                session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
            String strPassword = session.getAttribute("PASSWORD").toString();

            if (lstID.trim().length() > 0) {

                if (!strMaNSD.equalsIgnoreCase(user) ||
                    !strPassword.equals(pass)) {
                    frm.setError_desc("Sai username hoac password!");
                    throw new Exception("Sai user hoac mat khau");
                }


                arrID = lstID.split(",");
                type = "RESEND_ALL";
                strSQL =
                        " msg_id in ('" + frm.getWhere().trim().replace(",", "','") +
                        "') and msg_type in ('195','196','103','900','910','199','065','066','086','085') and sender ='TTSP_KBA' ";

                Collection colTS = getThamSoHThong(session);
                Iterator iter = colTS.iterator();
                String strHostName = "";
                String strChanel = "";
                String strPort = "";
                String strQMName = "";
                String QName = "";
                ThamSoHThongVO tsVO = null;
                while (iter.hasNext()) {
                    tsVO = (ThamSoHThongVO)iter.next();
                    if ("MQ_CHANEL".equalsIgnoreCase(tsVO.getTen_ts())) {
                        strChanel = tsVO.getGiatri_ts();
                    } else if ("MQ_HOSTNAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                        strHostName = tsVO.getGiatri_ts();
                    } else if ("MQ_MANAGER_NAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                        strQMName = tsVO.getGiatri_ts();
                    } else if ("MQ_NAME".equalsIgnoreCase(tsVO.getTen_ts())) {
                        QName = tsVO.getGiatri_ts();
                    } else if ("MQ_PORT".equalsIgnoreCase(tsVO.getTen_ts())) {
                        strPort = tsVO.getGiatri_ts();
                    }
                }

                MsgDAO msgDAO = new MsgDAO(conn);
                iSent =
                        msgDAO.reSendLTT(strHostName, strChanel, strPort, strQMName,
                                         QName, strSQL);
                msg_number = arrID.length;
                checkSuccess = "RESEND_SUCCESS";
                frm.setError_desc("Success");
            }

        } catch (Exception e) {
            checkSuccess = e.getMessage();
            frm.setError_desc("Loi: " + e.getMessage());
            //            e.printStackTrace();
            throw e;
        } finally {
            if (lstID.trim().length() > 0) {
                try {

                    String sqlLog =
                        "insert into sp_log_resend(id, msg_id, mt_id, type,user_code, more, insert_date, ";
                    sqlLog +=
                            "send_number, sent_number) values (sp_log_resend_seq.nextval,";
                    sqlLog +=
                            "null,'" + lstID + "','" + type + "','" + user + "','" +
                            strSQL.replace("'", "") + "',SYSDATE,";
                    sqlLog += msg_number + "," + iSent + ")";
                    pstmt = conn.prepareStatement(sqlLog);
                    pstmt.executeUpdate();
                    conn.commit();

                } catch (Exception ex) {
                    //                ex.printStackTrace();
                }
                request.setAttribute("TONG_SO_SO_DIEN", msg_number);
                request.setAttribute("SO_DIEN_DA_GUI_THANH_CONG", iSent);
                request.setAttribute("TRANG_THAI_THUC_HIEN", checkSuccess);
                close(conn);
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    //                    e.printStackTrace();
                } finally {
                    pstmt = null;
                }

            }
            return mapping.findForward("success");
        }
    }
}
