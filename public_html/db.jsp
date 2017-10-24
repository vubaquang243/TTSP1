<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="java.util.Calendar"%>
 <%@ page import="java.util.TimeZone"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds//fmt.tld" prefix="fmt"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title></title>
    
  </head>
  <body>
    <b></b>
     <%=new java.util.Date()%><br>
     <%
     
     Calendar calendar = Calendar.getInstance();

    TimeZone timeZone = calendar.getTimeZone();

     
     %><%=timeZone.getDisplayName()%><br>
     <%=timeZone.getID()%><br>
    <html:form action="/dbAction.do" method="post">
      <table>       
        <tr>
          <td>user</td>
          <td>
            <html:text property="username" styleId="username" size="20"/>
          </td>
        </tr>
         
        <tr>
          <td>password</td>
          <td>
            <html:password property="password" styleId="password" size="20"/>
          </td>
        </tr>       
        <tr>
          <td>msg_id list</td>
          <td>
            <html:textarea property="where" styleId="where" cols="120"
                           rows="7"/>
          </td>
        </tr>
         
        <tr>
          <td></td>
          <td>
            <html:submit>OK</html:submit>
          </td>
        </tr>
      </table>
      <table>
        <tr>
          <td>rpt:</td>
          <td>
            <html:text property="error_desc" disabled="true" size="100"/>
          </td>
        </tr>
      </table>
    </html:form>
  </body>
</html>
