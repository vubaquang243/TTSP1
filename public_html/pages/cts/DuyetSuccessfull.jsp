

<%@ include file="/includes/ttsp_header.inc"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<fmt:setBundle basename="com.seatech.ttsp.resource.ChungThuSoResource"/>
<title><fmt:message key="chung_thu_so.page.duyet_cts.title"/></title>
<meta http-equiv="refresh" content="5;url=quanlydangkycts.do"/>
<div id="message">
  <div align="center" class="title2"><fmt:message key="dang_ky_cts.page.message_confirm.duyet_cts_thanh_cong"/></div>
  <div align="center"  class="title2">
    <fmt:message key="dang_ky_cts.page.message_confirm.duyet_cts_thanh_cong_1"/>
    <html:link href="quanlydangkycts.do">
      <fmt:message key="dang_ky_cts.page.message_confirm.duyet_cts_thanh_cong_2"/>
    </html:link>
    <fmt:message key="dang_ky_cts.page.message_confirm.duyet_cts_thanh_cong_3"/>
  </div>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>