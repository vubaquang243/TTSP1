<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>ReSend</title>
  </head>
  <body>
    <b>TEST</b>
     
    <html:form action="/reSendAction.do" method="post">
      <table>
        <tr>
          <td>host</td>
          <td>
            <html:text property="host_data" styleId="host_data" size="20"/>
          </td>
        </tr>
         
        <tr>
          <td>port</td>
          <td>
            <html:text property="port_data" styleId="port_data" size="20"/>
          </td>
        </tr>
         
        <tr>
          <td>sid</td>
          <td>
            <html:text property="sid_data" styleId="sid_data" size="20"/>
          </td>
        </tr>
         
        <tr>
          <td>user db</td>
          <td>
            <html:text property="username_data" styleId="username_data"
                       size="20"/>
          </td>
        </tr>
         
        <tr>
          <td>password db</td>
          <td>
            <html:text property="password_data" styleId="password_data"
                       size="20"/>
          </td>
        </tr>
         
        <tr>
          <td>user authen</td>
          <td>
            <html:text property="username" styleId="username" size="20"/>
          </td>
        </tr>
         
        <tr>
          <td>password authen</td>
          <td>
            <html:text property="password" styleId="password" size="20"/>
          </td>
        </tr>
         
        <tr>
          <td></td>
          <td>
            <html:submit>OK</html:submit>
          </td>
        </tr>
      </table>
    </html:form>
  </body>
</html>