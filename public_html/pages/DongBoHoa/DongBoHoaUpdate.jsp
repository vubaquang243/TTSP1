<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.QuanLyNSDResource"/>

<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="DongBoHoaUpdateAction" method="post">
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
            <span class="title2" height="24"
                  background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/bg_Title.jpg"
                  colspan="6">
              <font size="2">
                Quản lý Thread Tự Động
              </font></span>
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
           bordercolor="#999999" width="100%" align="left">
      <tbody>
        <tr>
          <td class="title" colspan="6" bordercolor="#e1e1e1"
              background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
              height="24">
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                Sửa Thread
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tbody>
        <tr>
          <td>
            <table width="80%" cellspacing="0" cellpadding="1"
                   bordercolor="#e1e1e1" border="0" align="center"
                   style="BORDER-COLLAPSE: collapse">
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Tên Thread</td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="thread_name" styleId="thread_name"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             size="40%"
                             onblur="this.style.backgroundColor='#ffffff'"
                             styleclass="promptText"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Thời gian bắt đầu</td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="time_start"
                             onkeypress="return blockKeySpecial001(event)"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             size="40%"
                             onblur="this.style.backgroundColor='#ffffff'"
                             styleclass="promptText"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
              </tr><tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Thời gian dừng</td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="time_stop"
                             onkeypress="return blockKeySpecial001(event)"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             size="40%"
                             onblur="this.style.backgroundColor='#ffffff'"
                             styleclass="promptText"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Chu kỳ</td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="time_sleeping"
                             onkeypress="return blockKeySpecial001(event)"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             size="40%"
                             onblur="this.style.backgroundColor='#ffffff'"
                             styleclass="promptText"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
                 </tr><tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Mô tả</td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="description"
                             onkeypress="return blockKeySpecial001(event)"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             size="40%"
                             onblur="this.style.backgroundColor='#ffffff'"
                             styleclass="promptText"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Ngày cập nhật</td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="update_date" styleId="update_date"
                             size="40%" 
                             styleclass="promptText" onkeydown="if(event.keyCode==13) event.keyCode=9;"                     
                             />
                </td>
                 </tr><tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Mã NSD</td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="user_id" styleId="user_id"
                             size="40%"
                             styleclass="promptText" onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             />
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Tên lớp</td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="class_name" styleId="class_name"
                             size="40%" onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             styleclass="promptText"
                             />
                </td>
              </tr>
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">Thông tin Log</td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="log_info"
                             onkeypress="return blockKeySpecial001(event)"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             size="40%"
                             onblur="this.style.backgroundColor='#ffffff'"
                             styleclass="promptText"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">Thông tin lỗi</td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text property="log_error"
                             onkeypress="return blockKeySpecial001(event)"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             size="40%"
                             onblur="this.style.backgroundColor='#ffffff'"
                             styleclass="promptText"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </tbody>
       
      
        <tr>
          <td bordercolor="#e1e1e1">
            <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%" >
              <tr>
                <td align="center">
                  <span>
                    <button type="button" onclick="sbbut('save')" accesskey="g"
                            class="ButtonCommon">
                      <span class="sortKey">G</span>hi
                    </button>
                     </span>
                     <span>
                    <button type="button" onclick="sbbut('close')" accesskey="o"
                            class="ButtonCommon">
                      Th<span class="sortKey">o</span>&aacute;t
                    </button>
                    </span>
                  </td>
                </tr>
              </table>
          </td>
        </tr>
      
    </table>
    <html:hidden property="id"/>
  </html:form>
</div>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(
    function () {
          jQuery("#update_date").attr( {
                readonly :"readonly"
            }).val(showNowDate());
          jQuery("#thread_name").focus();
          jQuery("#class_name").attr( {
                readonly :"readonly"
            });
            jQuery("#user_id").attr( {
                readonly :"readonly"
            });
        }
    );

  function sbbut(type) {
      var f = document.forms[0];
      if (type == 'save') {
          f.action = 'DongBoHoaUpdateExcAction.do';
      }
      if (type == 'close') {
          f.action = 'DongBoHoaListAction.do';
      }
      f.submit();
  }

  function blockKeySpecial001(e) {
      //      e.keyCode
      var code;
      if (!e)
          var e = window.event;
      if (e.keyCode)
          code = e.keyCode;
      else if (e.which)
          code = e.which;
      var character = String.fromCharCode(code);
      var pattern = /[!@#$%^&*()_+='"\[\]\.\,\:\;\{\}\<\>\?\\]/;
      if (pattern.match(character)) {
          character.replace(character, "");
          return false;
      }
      else {
          return true;
      }
  }
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>
