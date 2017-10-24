<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************  
  jQuery(document).init(function () {
  });
  
  function click_event(type) {
      var f = document.forms[0];
      if (type == 'save') {
          if (f.shkb.value == "") {
              alert('Số hiệu kho bạc không được để trống');
              f.shkb.focus();
              return;
          }
          if(f.shkb.value.length < 4){
            alert('SHKB phải là 4 số');
              f.shkb.focus();
              return;
          }
      }
      if (type == 'close') {
          f.shkb.value = "";
          f.ma_nh.value = "";
          f.action = 'mainAction.do';
      }
      f.submit();
  }
  
  function blockKeySpecial001(e) {
      //      e.keyCode
      var code;
      if (!e)
      // var e = window.event;
          if (e.keyCode)
              code = e.keyCode;
          else if (e.which)
              code = e.which;
      var character = String.fromCharCode(code);
      var pattern = / [!@ # $ %  ^  &  * ()_ += '"\[\]\.\,\:\;\{\}\<\>\?\\]/;
      if (pattern.match(character)) {
          character.replace(character, "");
          return false;
      }
      else {
          return true;
      }
  }
</script>
<div class="box_common_conten">
  <html:form action="/GhiThietLapTS.do" method="post">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width="13">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
                 width="13" height="30"/>
          </td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title2">Thiết lập tham số</span>
          </td>
          <td width="62">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg"
                 width="62" height="30"/>
          </td>
          <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg"
              width="20%">&nbsp;</td>
          <td width="4">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg"
                 width="4" height="30"/>
          </td>
        </tr>
      </tbody>
    </table>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <tbody>
        <tr>
          <td class="title" colspan="6"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="100%">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">Thiết lập tham số kho bạc</font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <font color="Red" dir="ltr">
            <%
        if(request.getAttribute("status") != null){
          int result = Integer.parseInt(request.getAttribute("status").toString());
          if(result == 1){
              out.print("Thiết lập thất bại");
          }else if(result == 2){
              out.print("Thiết lập tham số thành công");   
          }
        }
      %>
          </font>
        </td>
      </tr>
       
      <tr>
        <td>
          <br/>
          <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
            <tr>
              <td align="right" bordercolor="#e1e1e1">Số hiệu Kho Bạc</td>
              <td>
                <html:text property="shkb" maxlength="4"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
              </td>
            </tr>
          </table>
          <br/>
        </td>
      </tr>
    </table>
    <table class="buttonbar" align="center">
      <tr>
        <td bordercolor="#e1e1e1">
          <button type="button" onclick="click_event('save')" accesskey="t"
                  class="ButtonCommon">
            <span class="sortKey">T</span>hiết lập
          </button>
           
          <button type="button" onclick="click_event('close')" accesskey="o"
                  class="ButtonCommon">
            Th
            <span class="sortKey">o</span>
            &aacute;t
          </button>
        </td>
      </tr>
    </table>
  </html:form>
</div>