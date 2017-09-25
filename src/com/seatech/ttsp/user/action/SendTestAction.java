package com.seatech.ttsp.user.action;

import com.seatech.ttsp.proxy.giaodien.mq.MQClient;
import com.seatech.ttsp.user.form.ReSendForm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SendTestAction extends Action {
    public SendTestAction() {
        super();
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        ReSendForm f = new ReSendForm();
        String msg = "";
        try {
            f = (ReSendForm)form;
            msg = f.getType();
            String queuename = f.getResend();//"TDTT.TCS.INBOX.QUEUE"
            String port = f.getPassword();
            String ip = f.getUsername();
            String qMgr = f.getPass();
            String channel = f.getUser();
            
/*            String msg = "";
            File fileDir = new File("c:/msgtest.txt");

            BufferedReader in =
                new BufferedReader(new InputStreamReader(new FileInputStream(fileDir),
                                                         "UTF8"));

            String str;

            while ((str = in.readLine()) != null) {
                msg += str;
            }

            in.close();*/


            MQClient mqc = new MQClient();
            mqc.putMsgToQueue(msg, ip, channel, port,
                              qMgr, queuename);

        } catch (Exception ex) {
            return mapping.findForward("failure");
        } finally {

        }
        return mapping.findForward("success");
    }
}
