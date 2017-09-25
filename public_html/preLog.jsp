<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>closeWindow</title>
  </head>
  <body>
  <html:errors/>

  </body>
</html>

<script type="text/javascript">
  close_win_without_prompt();
  function close_win_without_prompt() {
      var error_content = null;
      if(document.getElementById("error_conten") != null && document.getElementById("error_conten") != 'null'){
        error_content = document.getElementById("error_conten").innerHTML        
      }
      
      if (isV6()) {
          window.opener = null;
          window.close();
      }
      else {
          window.open('', '_self', '');
          window.close();
      }      
      
      window.open('login.jsp?error_desc='+ error_content,'','fullscreen=yes, scrollbars=auto');	
  }

  function getBrowser() {
      var sBrowser = navigator.userAgent;
      if (sBrowser.toLowerCase().indexOf('msie') >  - 1)
          return 'ie';
      else if (sBrowser.toLowerCase().indexOf('firefox') >  - 1)
          return 'firefox';
      else 
          return 'mozilla';
  }

  function isV6() {
      return (navigator.appVersion.indexOf("MSIE 6") > -1);
  }
</script>