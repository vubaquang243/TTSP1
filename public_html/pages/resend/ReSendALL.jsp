<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
 <%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ include file="/includes/ttsp_header.inc"%>

<%
  String tcong = request.getAttribute("SO_DIEN_DA_GUI_THANH_CONG")!=null?request.getAttribute("SO_DIEN_DA_GUI_THANH_CONG").toString():"";
  String tong_so = request.getAttribute("TONG_SO_SO_DIEN")!=null?request.getAttribute("TONG_SO_SO_DIEN").toString():"";
%>

<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
  var tcong="<%=tcong%>";
  var tong_so="<%=tong_so%>";
  if(tcong!=null&&''!=tcong){
    jQuery('#success').val(tcong);
  }
  if(tong_so!=null&&''!=tong_so){
    jQuery('#resend').val(tong_so);
  }
  });
</script>
<html:form action="/reSendAllAction.do" method="post">
  <table>
     
    <tr>
      <td>Tên NSD</td>
      <td>
        <html:text property="username" styleId="username" size="20"/> <font color="Red">(*)</font>
      </td>
    </tr>
     
    <tr>
      <td>Mật khẩu</td>
      <td>
        <html:password property="password" styleId="password" size="20"/> <font color="Red">(*)</font>
      </td>
    </tr>
    <tr>
      <td>MSG_ID (Danh sách cần truyền lại)</td>
      <td>
        <html:textarea property="where" styleId="where" cols="100"
                       rows="5"/><br/>
        (Mỗi MSG_ID cách nhau bởi dấu (,). Ví Dụ: TTSP_KBA123,TTSP_KBA1234,...,TTSP_KBA12345)
      </td>
    </tr>
     
    <tr>
      <td></td>
      <td>
         <button type="button" onclick="checkTxt()">
        OK
      </button>
      </td>
    </tr>
  </table>
  <table>
    <tr>
      <td>sent:</td>
      <td>
        <html:text property="resend" style="color:red" styleId="resend" readonly="true" size="20"/>
      </td>
    </tr>
     
    <tr>
      <td>sucess:</td>
      <td>
        <html:text property="success" style="color:red" styleId="success" readonly="true" size="20"/>
      </td>
    </tr><tr>
      <td>error:</td>
      <td>
        <html:text property="error_desc" readonly="true" size="100"/>
      </td>
    </tr>
  </table>
</html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
 function checkTxt(){
 var f = document.forms[0];
    var user= jQuery('#username').val(),
        pass= jQuery('#password').val();
    if(user.trim().length<=0){
      alert(GetUnicode('Cần nhập thông tin tên NSD'));
      return false;
    }
//    if(pass.trim().length<=0){
//      alert(GetUnicode('Cần nhập thông tin mật khẩu'));
//       return false;
//    }
          f.action = 'reSendAllAction.do'; 
          f.submit();
 }
</script>

