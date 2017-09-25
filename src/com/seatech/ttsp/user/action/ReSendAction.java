package com.seatech.ttsp.user.action;


import com.seatech.framework.AppKeys;
import com.seatech.framework.utils.TTSPUtils;
import com.seatech.ttsp.proxy.giaodien.MsgDAO;
import com.seatech.ttsp.thamso.ThamSoHThongDAO;
import com.seatech.ttsp.thamso.ThamSoHThongVO;
import com.seatech.ttsp.user.form.ReSendForm;

import java.sql.Connection;
import java.sql.DriverManager;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ReSendAction extends Action {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        Connection conn = null;
        ReSendForm f = new ReSendForm();
        String strType = "";
        String strWhere = "";
        try {
            f = (ReSendForm)form;
            strType = f.getType();
            if (f.getWhere() != null && !"".equals(f.getWhere())) {
                strWhere += "and msg_id in ( " + f.getWhere().trim() + " )";
            }
            try {
                conn =
getConnection(f.getHost_data(), f.getPort_data(), f.getSid_data(),
              f.getUsername_data(), f.getPassword_data());
            } catch (Exception ex) {
                f.setError_desc("Loi ket noi CSDL: " + ex.getMessage());
                return mapping.findForward(FAILURE);
            }
            //Lay tong so msg can resend
            MsgDAO msg = new MsgDAO(conn);
            int iResend = msg.countReSend(strType, strWhere);
            //Xac thuc
            String strUserName = f.getUsername();
            String strPassword = f.getPassword();
            String user = "@dmin@"+iResend;            
            if (!"admin".equals(strUserName) || !user.equals(strPassword)) {
                f.setError_desc("Sai username hoac password!");
                return mapping.findForward(FAILURE);
            }
            ThamSoHThongDAO ts = new ThamSoHThongDAO(conn);

            Collection colTS = ts.getThamSoReSend("", null);
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

            
            int iSuccess =
                msg.reSend(strHostName, strChanel, strPort, strQMName, QName,
                           strType, strWhere);
            f.setSuccess(String.valueOf(iSuccess));
            f.setResend(String.valueOf(iResend));
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
    protected org.apache.struts.action.ActionServlet servlet;

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

    public Connection getConnection(String serverName, String portNumber,
                                    String sid, String username,
                                    String password) throws Exception {
        Connection conn;
        try {
            String driverName = "oracle.jdbc.driver.OracleDriver";
            Class.forName(driverName);
            if (serverName == null || "".equals(serverName)) {
                serverName = "10.96.1.93";
            }
            if (portNumber == null || "".equals(portNumber)) {
                portNumber = "1521";
            }
            if (sid == null || "".equals(sid)) {
                sid = "TTSP";
            }
            String url =
                "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" +
                sid;
            if (username == null || "".equals(username)) {
                username = "ttsp_owner2";
            }
            if (password == null || "".equals(password)) {
                password = "oracle1";
            }
            conn = DriverManager.getConnection(url, username, password);

            if (conn != null) {
                conn.setAutoCommit(false);
            } else {
                throw new Exception("ReSend:getConnection() - Khong ket noi duoc CSDL.");
            }
        } catch (Exception e) {
            throw e;
        }
        return conn;
    }
}
