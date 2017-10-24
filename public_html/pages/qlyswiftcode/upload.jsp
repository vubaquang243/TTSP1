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
<h3>Upload file danh mục swift code (xls)</h3>


<html:form action="uploadSwiftCode" method="post" enctype="multipart/form-data">
    <font color="red"><html:errors/></font> 
    <html:file property="file"/>
    <html:submit value="Upload"/>
</html:form>
<font color="Red"><i>(Chấp nhận file xls phiên bản trên 97, Xem hướng dẫn bên dưới)</i></font>
<br/><font color="Blue"><i>(Sau khi click "Upload" danh mục swift code mới insert vào bản tạm "sp_dm_swift_code_temp", có thể kiểm tra bảng này trước khi thực hiện các bước tiếp theo)</i></font>

<H2>Hướng dẫn các bước tạo file excel cho việc đồng bộ:</H2>

<i>
B1: Bôi đen một số cột excel (Bằng số cột tương ứng trong file word NH gửi), chuyển format về text.<br/>

B2: Copy nội dung file swift code (File text) NH gửi vào file excel<br/>

B3: Save file exel dưới dạng xls (excel 97-2003)<br/>
</i>

<%@ include file="/includes/ttsp_bottom.inc"%>