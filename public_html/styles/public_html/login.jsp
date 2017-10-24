<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0034)http://tcs.kbnn.vn/Load_ChungTu.do -->
<%@page  import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<HTML>
<HEAD>
  <TITLE>TTSP-Dang nhap he thong</TITLE>
  <META content="text/html; charset=UTF-8" http-equiv=Content-Type></META>
  
  <LINK rel=stylesheet href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css" media=screen>
  
  <META content=no-cache http-equiv=Pragma>
  <META content=-1 http-equiv=Expires>
  <META name=GENERATOR content="MSHTML 8.00.7600.16821">
</HEAD>
<BODY class="body">
    <%
      if(request.getSession().getAttribute("ma_nsd") != null){
        response.sendRedirect(request.getContextPath()+"/loginAction.do");
      }
      String ipAddress  = request.getHeader("X-FORWARDED-FOR");  
      if(ipAddress == null)  
      {  
        ipAddress = request.getRemoteAddr();  
      }  
    %>
  <SCRIPT language=JavaScript1.2 type=text/javascript src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tcs.htm" > 
  </SCRIPT>
  <SCRIPT language=JavaScript1.2 type=text/javascript src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/json.htm" > 
  </SCRIPT>
  <SCRIPT language=JavaScript1.2 type=text/javascript src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/prototype.htm" > 
  </SCRIPT>
  <SCRIPT language=JavaScript1.2 type=text/javascript src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/login.htm" > 
  </SCRIPT>

  <TABLE border=0 cellSpacing=0 cellPadding=0 width=770 align=center>
    <TBODY>
      <TR>
        <TD height=70 background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/banner1.jpg>&nbsp; </TD>
      </TR>
      <TR>
        <TD height=26 background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_menu.jpg></TD>
      </TR>
    </TBODY>
  </TABLE>
  <TABLE style="border-left: 1px #cdebe8 solid; border-right: 1px #cdebe8 solid; border-bottom: 1px #cdebe8 solid"  cellSpacing=0 cellPadding=0 width=770 
   align=center>
    <TBODY>
    <TR>
      <TD>
        <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%">
          <TBODY>
            <TR>
              <TD width=16><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi1.jpg" width=16 
                height=25></TD>
              <TD class=title2 background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi2.jpg width="42%"></TD>
              <TD width=50><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi3.jpg" width=46  height=25></TD>
              <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi22.jpg width="25%"><SPAN class=textlink3></SPAN> </TD>
              <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/navi22.jpg width="32%"><SPAN class=textlink3></SPAN> </TD>
            <TR>
              <TD width=16></TD>
              <TD width="42%"></TD>
              <TD width=50></TD>
              <TD width="25%"><SPAN class=textlink3><!-- Chế độ làm việc với AD: </SPAN><FONT color="red"><SPAN class="textlink5">Online --></SPAN></FONT></TD>
            </TR>
          </TBODY>
        </TABLE>
        <BR>
        <TABLE border=0 cellSpacing=0 cellPadding=0 width="99%">
          <TBODY>
          <TR>
            <TD width=35><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B1.jpg" width=35 height=15></TD>
            <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B2.jpg></TD>
            <TD width=12><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B3.jpg" width=12 height=15></TD></TR>
          <TR>
            <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B4.jpg>&nbsp;</TD>
            <TD>              
              <html:form action="/loginAction">  
              
              <TABLE width="99%">
                <TBODY>
                  <TR>
                    <TD><div class="app_error"><html:errors/></div></TD>
                  </TR>
                </TBODY>
              </TABLE>
              
              <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" align=center>
                <TBODY>
                <TR>
                  <TD width=13><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30></TD>
                  <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T2.jpg width="75%"><SPAN class=title2>Dang nhap he thong</SPAN></TD>
                  <TD width=62><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30></TD>
                  <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg width="20%">&nbsp;</TD>
                  <TD width=4><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30></TD>
                </TR>
                </TBODY>
              </TABLE>
              <TABLE border=0 cellSpacing=0 cellPadding=0 width=716 align=center>
          <TBODY>
          <TR>
            <TD height=226 background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_login.jpg>
              <TABLE border=0 cellSpacing=0 cellPadding=2 width="60%" align=center>
                <TBODY>
                <TR>
                  <TD>&nbsp;</TD>
                  <TD>&nbsp;</TD>
                  <TD>&nbsp;</TD>
                </TR>
                <TR>
                  <TD width="24%">Domain</TD>
                  <TD><html:select property="domain" styleId="domain_id" style="WIDTH: 270px" onkeydown="if(event.keyCode==13) event.keyCode=9;" styleClass="passwordFieldText">                  
                      <html:option value="TW">TW</html:option>
                      <html:option value="MB">MB</html:option>
                      <html:option value="MN">MN</html:option>
                    </html:select> 
                  </TD>
                  <TD width="10%">&nbsp;</TD>
                </TR>
                <TR>
                  <TD>Ma nhan vien</TD>
                  <TD>
                    <html:text style="WIDTH: 270px" onkeydown="if(event.keyCode==13) event.keyCode=9;" 
                              styleClass="passwordFieldText" onkeypress="return blockKeySpecial(event);" property="ma_nsd" styleId="username" value=""/></TD>
                  <TD width="15%">&nbsp;</TD>
                </TR>
                <TR>
                  <TD>Mat khau</TD>
                  <TD>
                    <html:password style="WIDTH: 270px" onkeydown="if(event.keyCode==13) event.keyCode=9;" 
                               styleClass="passwordFieldText" onkeypress="capLock(event)" maxLength="20"  property="mat_khau" styleId="password" value=""/>
                  </TD>
                    
                  <TD><SPAN>
                    <DIV style="VISIBILITY: hidden" id=divMayus><FONT 
                    color=red>Capslock</FONT></DIV></SPAN></TD></TR>
                <TR>
                  <TD>&nbsp;</TD>
                  <TD>&nbsp;</TD>
                  <TD>&nbsp;</TD></TR>
                <TR>
                  <TD>&nbsp;</TD>
                  <TD id=button>
                    <DIV align=center>
                      <BUTTON accessKey=d style="WIDTH: 70px" 
                      class=ButtonCommon onclick=sbLogin();><SPAN 
                      class=sortKey>&#272;</SPAN>&#259;ng nh&#7853;p</BUTTON>                      
                    </DIV>
                  </TD>
                  <TD>&nbsp;</TD>
                </TR>
                <TR>
                  <TD>&nbsp;</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
                  
                <html:hidden property="ip_truycap" value="<%=ipAddress%>"/>
              </html:form>
             
            </TD>
            <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B5.jpg>&nbsp;</TD></TR>
          <TR>
            <TD><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B6.jpg" width=35 height=16></TD>
            <TD background=<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B77.jpg>
              <DIV align=right><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/B7.jpg" width=663 
              height=16></DIV></TD>
            <TD><IMG src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/b8.jpg" width=12 
          height=16></TD></TR></TBODY></TABLE><BR></TD></TR></TBODY>
        </TABLE>



</BODY>
</HTML>
<script type="text/javascript">
  getValueFromCookie('domain');
  //alert(domainCookie);
  function sbLogin() {
      var frm = document.forms[0];
      //set cookie      
      setValueToCookie('domain',frm.domain.value);      
      
      frm.action = 'loginAction.do';
      frm.submit();
  }

  function blockKeySpecial(e) {
      var key;

      if (window.event)// IE
      {
          key = e.keyCode;
      }
      else if (e.which)// Netscape/Firefox/Opera
      {
          key = e.which;
      }
      if (key == 37 || key == 38 || key == 39 || key == 40 || key == 16//arrow key
 || key == 35 || key == 36 || key == 9//home,end,tab
 || key == 46 || key == 8//del,insert,backspace
 || (key > 31 && key < 44) || (key > 57 && key < 65) || (key > 90 && key < 97) || (key > 123 && key < 127))//Cac ki tu dac biet
          event.returnValue = false;
      else 
          return textToUpper();
  }

    // Use this function to retrieve a cookie.
    function getCookie(name) {
        var cname = name + "=";
        var dc = document.cookie;
        if (dc.length > 0) {
            begin = dc.indexOf(cname);
            if (begin !=  - 1) {
                begin += cname.length;
                end = dc.indexOf(";", begin);
                if (end ==  - 1)
                    end = dc.length;
                return unescape(dc.substring(begin, end));
            }
        }
        return null;
    }

    // Use this function to save a cookie.
    function setCookie(name, value, expires) {
        document.cookie = name + "=" + escape(value) + "; path=/" + ((expires == null) ? "" : "; expires=" + expires.toGMTString());
    }

    // Use this function to delete a cookie.
    function delCookie(name) {
        document.cookie = name + "=; expires=Thu, 01-Jan-70 00:00:01 GMT" + "; path=/";
    }

    // Function to retrieve form element's value.
    function getValueFromCookie(name) {
        var value = getCookie(name); 
        if(value != null && value != 'null'){
          document.getElementById('domain_id').value = value;
        }
    }

    // Function to save form element's value.
    function setValueToCookie(name, value) {
        setCookie(name, value, null);
    }
</script> 
