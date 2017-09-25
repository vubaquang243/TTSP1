<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0034)http://tcs.kbnn.vn/Load_ChungTu  -->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
  <head>
    <title>TTSP-Dang nhap he thong</title>
  </head>
  <body class="body">
    <html:form action="/testReport">
      <button onclick="sbLogin();">OK</button>
    </html:form>
  </body>
</html>
<script type="text/javascript">
  function sbLogin() {

      var frm = document.forms[0];
      
      frm.submit();
  }
</script>