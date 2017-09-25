<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <title></title>
    </head>
    <body></body>
</html>
<script type="text/javascript">
  // Popup window code
window.onload(newPopup());
  function newPopup() {
      var url = 'login.jsp?action=02';
      <%if(request.getParameter("action") != null){%>
        url = '<%=request.getParameter("action")%>';
      <%}%>      
      newwindow = window.open(url, 'popUpWindow', 'height='+screen.height+',width='+screen.width+',left=10,top=10,fullscreen=yes, resizable=yes,scrollbars=yes,toolbar=no,menubar=no,location=no,directories=no,status=yes');
      if (window.focus) {
          newwindow.focus()
      }
      close_win_without_prompt();
  }


  function close_win_without_prompt() {
      if (isV6()) {
          window.opener = null;
          window.close();
      }
      else {
          window.open('', '_self', '');
          window.close();
      }
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