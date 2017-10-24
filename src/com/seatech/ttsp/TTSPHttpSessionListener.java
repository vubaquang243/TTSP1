package com.seatech.ttsp;


import com.seatech.framework.AppConstants;
import com.seatech.framework.AppKeys;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.lstruycap.LSuTruyCapDAO;
import com.seatech.ttsp.user.UserDAO;
import com.seatech.ttsp.user.UserVO;

import java.sql.Connection;

import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import javax.sql.DataSource;


public class TTSPHttpSessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent hse) {
        HttpSession session = hse.getSession();        
    }

    public void sessionDestroyed(HttpSessionEvent hse) {
        HttpSession session = hse.getSession();
        ServletContext context = session.getServletContext();
        Connection conn = null;
        try {
            DataSource ds =
                (DataSource)context.getAttribute(AppKeys.DATASOURCE_APPLICATION_KEY);
            conn = ds.getConnection();
            //Set quyen cho user cua ung dung
            TTSPUtils ttspUtils = new TTSPUtils(conn);
            ttspUtils.grantAccess();
            conn.setAutoCommit(false);
            
            LSuTruyCapDAO lSuTruyCapDAO = new LSuTruyCapDAO(conn);            
            
            if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                null) {                
                String strOSUser = session.getAttribute(AppConstants.APP_OS_USER_SESSION)==null?"":session.getAttribute(AppConstants.APP_OS_USER_SESSION).toString();
                String strCompName = session.getAttribute(AppConstants.APP_COMPUTER_NAME_SESSION)==null?"":session.getAttribute(AppConstants.APP_COMPUTER_NAME_SESSION).toString();
                String strUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
                String strIP =  session.getAttribute(AppConstants.APP_IP_SESSION)==null?"": session.getAttribute(AppConstants.APP_IP_SESSION).toString();
                lSuTruyCapDAO.saveVisitLog(strUserID, "SYS.LOGOUT", strIP, "", strOSUser, strCompName);
                //Khoi tao userDAO
                UserDAO udao = new UserDAO(conn);
                //Kiem tra
                String strWhere = " id = ? ";
                Vector vParams = new Vector();
                vParams.add(new Parameter(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString(),
                                          true));
                UserVO uvoCheck = udao.getUser(strWhere, vParams);
                if (session.getId().equals(uvoCheck.getSession_id())) {
                    //Khoi tao UserVO
                    UserVO uvo = new UserVO();
                    uvo.setIp_truycap("");
                    uvo.setSession_id("");
                    uvo.setId(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                    //Clear session trong db
                    udao.update(uvo);
                    //commit
                    conn.commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
