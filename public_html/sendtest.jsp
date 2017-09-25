<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title></title>
    
  </head>
  <body>
    <b>TEST</b>
     
    <html:form action="/sendTestAction.do" method="post">
      <table>
         <tr>
          <td>IP</td>
          <td><html:text property="username" styleId="username" value="192.168.1.22" /></td>
        </tr> 
         <tr>
          <td>PORT</td>
          <td><html:text property="password" styleId="password" value="1414" /></td>
        </tr> 
        <tr>
          <td>Channel</td>
          <td><html:text property="user" styleId="user" value="SYSTEM.DEF.SVRCONN" /></td>
        </tr> 
        <tr>
          <td>Queue Manager</td>
          <td><html:text property="pass" styleId="pass" value="ASC_QMGR" /></td>
        </tr> 
         <tr>
          <td>ten queue</td>
          <td><html:text property="resend" styleId="resend" value="ASC_INBOX" /></td>
        </tr> 
        <tr>
          <td>msg</td>
          <td>
            <html:textarea property="type" styleId="type" cols="100" rows="20"/>
          </td>
        </tr>
        
        <tr>
          <td></td>
          <td>
            <html:submit>SEND</html:submit>
          </td>
        </tr>
      </table>
     
    </html:form>
  </body>
</html>