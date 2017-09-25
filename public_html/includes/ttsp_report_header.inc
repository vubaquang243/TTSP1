<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page  import="com.seatech.framework.AppConstants"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title></title> 
  <script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-1.3.2.min.js"></script>
  <script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/report.js"></script>
  <link rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/report.css" type="text/css" />
</head>
<body>
<form action="/printAction">
<div id="top-bar"> 
  <div id="topbar-inner">
  <input type="image" onclick="divPrint();" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/icon_print.png" alt="In báo cáo" name="print" width="40" height="40">  
  <input type="image" onclick="submitSaveRpt('word')" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/icon_word.png" alt="In báo cáo" name="word" width="40" height="40">
  <input type="image" onclick="submitSaveRpt('excel')" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/icon_excel.png" alt="In báo cáo" name="excel" width="40" height="40">
  <input type="image" onclick="submitSaveRpt('pdf')" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/icon_pdf.png" alt="In báo cáo" name="pdf" width="40" height="40">   
  </div>
</div>
<div id="mainouter">