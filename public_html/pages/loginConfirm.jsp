<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page  import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds//fmt.tld" prefix="fmt" %>
<LINK rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css" media="screen">
<fmt:setBundle basename="com.seatech.ttsp.resource.LoginResource"/>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>loginConfirm</title>
    
  </head>
  <body>    
    <html:form action="/loginAction.do">
    <table width="100%">
      <tr>
        <td>&nbsp;<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>&nbsp;</td>
      </tr>
      <tr>
        <td width="10%"></td>        
        <td>
          <table width="100%" border="0">
            <tr>
              <fmt:message key="confirmlogin.page.msg"> 
                <fmt:param><%=request.getAttribute("ip_dang_truycap")%> </fmt:param> 
                <fmt:param><bean:write name="loginForm" property="ma_nsd"/> </fmt:param> 
              </fmt:message>    
            </tr>
          </table>
        </td>
        <td width="10%"></td>
      </tr>
      <tr>
        <td width="10%">&nbsp;<br/><br/><br/><br/></td>
        <td align="center">
          <button type="button" style="width: 100px" id="btnYes" onclick="onSubmit('yes');" accesskey="c"
                            class="ButtonCommon">
                      <span class="sortKey">C</span>ó
          </button>
          <button type="button" style="width: 100px" id="btnNo" onclick="onSubmit('no');" accesskey="k"
                            class="ButtonCommon">
                      <span class="sortKey">K</span>hông
          </button>
        </td>
        <td width="10%"></td>
      </tr>
    </table>
      <html:hidden property="ip_truycap"/>;
      <html:hidden property="ma_nsd"/>
      <html:hidden property="mat_khau"/>
      <html:hidden property="domain"/>
      <html:hidden property="mac_address"/>
      <html:hidden property="user_may_truycap"/>
      <html:hidden property="ten_may_truycap"/>
      <html:hidden property="login_status" value="confirm"/>            
    </html:form>  
  </body>
</html>
<script type="text/javascript">
  document.getElementById('btnYes').focus();
  function onSubmit(type){
    var f = document.forms[0];
    if( type == 'yes'){
      f.action = 'loginAction.do';      
    }else{
      f.action = 'login.jsp?action=02';
    }
    f.submit();
  }
</script>


