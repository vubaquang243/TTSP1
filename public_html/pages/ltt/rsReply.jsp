
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.KTVTabmisResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
  <body>
  <br/>
  <br/>
  <br/>
  <br/>
   <h3 align="center">
   <%
     int iSent = 0;
     int iSend = 0;
     String sendStatus = "FALSE";
     if(request.getAttribute("SO_DIEN_DA_GUI_THANH_CONG") != null){
      iSent = Integer.parseInt(request.getAttribute("SO_DIEN_DA_GUI_THANH_CONG").toString());
     }
     if(request.getAttribute("SO_DIEN_DA_CHON") != null){
      iSend = Integer.parseInt(request.getAttribute("SO_DIEN_DA_CHON").toString());
     }
     if(request.getAttribute("TRANG_THAI_THUC_HIEN") != null){
      sendStatus = request.getAttribute("TRANG_THAI_THUC_HIEN").toString();
     }
     if("RESEND_SUCCESS".equals(sendStatus)){
     %>
      <font color="Blue" >TRUYỀN THÀNH CÔNG, SỐ LƯỢNG: <font color="Red" ><b><%=iSent%>/<%=iSend%></b></font> MSG, TRA CỨU ĐỂ KIỂM TRA LẠI.</font>
     <%
     }else if("UPDATE_SUCCESS".equals(sendStatus)){
     %>
      <font color="Blue" >CẬP NHẬT THÀNH CÔNG, SỐ LƯỢNG: <font color="Red" ><b><%=iSent%>/<%=iSend%></b></font> , TRA CỨU ĐỂ KIỂM TRA LẠI.</font>
     <%
     }else{
     %>
      <font color="Red" >KIỂM TRA LẠI, CÓ LỖI: <b><%=sendStatus%></b></font>
     <%
     }
   %>
   </h3>   
   <br/>
  <br/>
  <br/>
  <br/>
 </body>
<%@ include file="/includes/ttsp_bottom.inc"%>
