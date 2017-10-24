package com.seatech.ttsp.user.action;


import com.seatech.framework.strustx.AppAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LogoutAction extends AppAction {
    private static String SUCCESS = "success";

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();

//        Connection conn = null;
        try {
//            String relog = request.getParameter("relog")==null?"":request.getParameter("relog").toString();
//            conn = getConnection(request);
//            //Ghi log truy cap chuc nang
//            saveVisitLog(conn, session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString() , "SYS.LOGOUT", session.getAttribute(AppConstants.APP_IP_SESSION).toString(),"");
//            //Khoi tao userDAO
//            UserDAO udao = new UserDAO(conn);
//            //Khoi tao UserVO
//            UserVO uvo = new UserVO();
//            uvo.setIp_truycap("");
//            uvo.setSession_id("");
//            uvo.setId(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
//            //Clear session trong db
//            udao.update(uvo);
//            //commit
//            conn.commit();
            //Clear session
            session.invalidate();            
//            if("no".equalsIgnoreCase(relog)){
//              return mapping.findForward("close");
//            }
        } finally {
//            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
}
