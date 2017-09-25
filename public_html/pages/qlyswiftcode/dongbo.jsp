<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>

<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
      
      
<H1>CHỨC NĂNG ĐỒNG BỘ DANH MỤC SWIFT CODE</h1>
<h3>Đồng bộ danh mục swift code</h3>
<%
  String strErrorCode = request.getAttribute("p_err_code")==null?"":request.getAttribute("p_err_code").toString();
  String strErrorDesc = request.getAttribute("p_err_desc")==null?"":request.getAttribute("p_err_desc").toString();
  if(!"".equals(strErrorCode)){
%>
  <b><font color="Red">Kết quả đồng bộ:</font></b>
  <br>
  <b>Mã lỗi:</b> <%=strErrorCode%>
  <br>
  <b>Mô tả lỗi:</b><%=strErrorDesc%>
  <br>
<%
  }
%>

<html:form action="dongboSwiftCode" method="post">
    <font color="red"><html:errors/></font> 
    <html:submit>Đồng bộ</html:submit>
    <p><i><font color="Red">(Sau khi click "Đồng bộ" danh mục swift code mới sẽ được đồng bộ. Hay kiểm tra lại bằng chức năng "Quản lý swift code" hoặc bảng CSDL: "sp_dm_swift_code")</font></i></p>
    
</html:form>


<%@ include file="/includes/ttsp_bottom.inc"%>