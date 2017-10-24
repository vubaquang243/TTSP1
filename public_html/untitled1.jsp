<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page  import="com.seatech.framework.AppConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title></title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	  <style type="text/css">
		#top-bar {
		  position: fixed;
		  top: 0;
		  left: 0;
		  z-index: 999;
		  width: 100%;
		  height: 40px;
		}
		#topbar-inner {
		  height: 40px;
		  background: #bbb;
		}
		#mainouter {
		  position: relative;
		  z-index: 2;
		  padding-top: 23px;
		  padding-bottom: 40px;
		  margin-left: 0px;
		  //background: #ccc;
		  min-height: 100%;
		}
		#bottom {
		  position: fixed;
		  bottom: 0;
		  left: 0;
		  z-index: 999;
		  width: 100%;
		  height: 40px;
		}
		#bottom-inner {
		  height: 40px;
		  background: #aaa;
		}
	</style>
  <style type="text/css">
    a {text-decoration: none}   
  </style>
  <style type="text/css">
      @media print {
        body * {
          display:none;
        }

        body .printable {
          display:block;
        }
      }
  </style>
    <script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery/1.3.2/jquery.min.js"></script>
    <script type="text/javascript">
      function divPrint() {
        // Some logic determines which div should be printed...
        // This example uses div3.
        $("#mainouter").addClass("printable");
        window.print();
      }
      function submitSaveRpt(type) {
        var f = document.forms[0];    
        f.action = "dtsGanLTTRptAction.do?action=pdf";
        alert(f.action);
        f.submit();
      }
    </script>
    <link rel="stylesheet" href="header_report.css" type="text/css" />
</head>
<body>
<form action="dtsGanLTTRptAction" target="_blank" method="post">
<div id="top-bar"> 
  <div id="topbar-inner">
  <button type="button" style="border: 0; background: transparent" onclick="divPrint()">
    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/icon_word.png" width="40" heght="40" alt="submit" />
  </button>
  <button type="button" onclick="submitSaveRpt('word')"> OK</button>
  <input type="image" onclick="submitSaveRpt('word')" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/icon_word.png" alt="In báo cáo" name="word" width="40" height="40">
  <input type="image" onclick="submitSaveRpt('excel')" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/icon_excel.png" alt="In báo cáo" name="excel" width="40" height="40">
  <input type="image" onclick="submitSaveRpt('pdf')" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/icon_pdf.png" alt="In báo cáo" name="pdf" width="40" height="40">   
  </div>
</div>
<div id="mainouter"> 


</div>
<!--<div id="bottom"> 
  <div id="bottom-inner">Fixed Bottom</div>
</div>-->
</form>
</body>
</html>
