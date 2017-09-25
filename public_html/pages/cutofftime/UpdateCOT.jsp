<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.ui.timepicker.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.COTResource"/>

<div class="app_error">
  <html:errors/>
</div>
<script type="text/javascript">
  //************************************ LOAD PAGE **********************************
  jQuery.noConflict();
  jQuery(document).ready(function () {
      jQuery('#cut_of_time').timepicker();
       jQuery('#cut_of_time').focus();
       jQuery("#dialog:ui-dialog").dialog( "destroy" );
        //dialog message
        jQuery("#dialog-message").dialog({
          autoOpen: false,
          resizable:false,
          height:200,
          width:380,
          modal: true,
          buttons: {
            Ok: function() {jQuery(this).dialog( "close" ); }
          },
          close:function(e){
            if (jQuery("#action").val()=='SAVE'){
              jQuery("#frmCOT").html('');
              jQuery("#frmCOT").attr({action:'danhsachcot.do'});
              jQuery("#frmCOT").submit();
            }
          }
        });
  });
</script>
<div class="box_common_conten">
  <html:form action="/capnhatcotExc.do" method="post" styleId="frmCOT">
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
            <span class="title2">
              <fmt:message key="cot_kbnn.page.title_cot"/></span>
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
                <fmt:message key="cot_kbnn.page.title_cot_sua"/>
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
                <td width="10%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="cot_kbnn.page.lable.ma_kb"/>
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text style="width:95%;border:1"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="1"
                             styleClass="promptText" property="ma_nh_kb"
                             readonly="true"/>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="cot_kbnn.page.lable.ten_kb"/>
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text style="width:95%;border:1"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="1"
                             styleClass="promptText" property="ten_nh_kb"
                             readonly="true"/>
                </td>
              </tr>
               
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="cot_kbnn.page.lable.ma_nh"/>
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text style="width:95%;border:1"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="1"
                             styleClass="promptText" property="ma_nh"
                             readonly="true"/>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="cot_kbnn.page.lable.ten_nh"/>
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text style="width:95%;border:1"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="1"
                             styleClass="promptText" property="ten_nh"
                             readonly="true"/>
                </td>
              </tr>
               
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="cot_kbnn.page.lable.ngay_gd"/>
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text style="width:95%;border:1"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="1"
                             styleClass="promptText" property="ngay_gd"
                             readonly="true"/>
                </td>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="cot_kbnn.page.lable.time_cot"/>
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text style="width:95%;border:1"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="1"
                             styleClass="promptText" property="cut_of_time"
                             styleId="cut_of_time" readonly="true"/>
                </td>
              </tr>
               
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  <fmt:message key="cot_kbnn.page.lable.so_yc"/>
                </td>
                <td width="29%" align="left" bordercolor="#e1e1e1">
                  <html:text style="width:95%;border:1"
                             onkeypress="return blockKeySpecial001(event)"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;"
                             onfocus="textfocus(this);"
                             onblur="textlostfocus(this);" tabindex="1"
                             styleClass="promptText" property="so_yc_cot"
                             readonly="true"/>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </tbody>
       
      <tbody align="center" bordercolor="#e1e1e1">
        <tr>
          <td bordercolor="#e1e1e1">
            <button type="button" onclick="sbbut('save')" accesskey="g"
                    class="ButtonCommon">
              <span class="sortKey">G</span>
              hi
            </button>
             
            <button type="button" onclick="sbbut('close')" accesskey="o"
                    class="ButtonCommon">
              Th
              <span class="sortKey">o</span>
              &aacute;t
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <html:hidden property="id" styleId="id"/>
    <html:hidden property="cut_of_time" styleId="cut_of_timeOLD"/>
    <input type="hidden" id="action"/>
  </html:form>
</div>   
    <div id="dialog-message" title="<fmt:message key="cot_kbnn.page.title.dialog_message"/>">
        <p>
          <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
          <span id="message"></span>
        </p>
    </div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  function sbbut(type) {
      var f = document.forms[0];
      if (type == 'save') {
         jQuery("#action").val('SAVE');
          var bValid = true;
//          jQuery("#cut_of_timeOLD").timepicker();
//          if (jQuery("#cut_of_timeOLD").timepicker('getTimeAsDate') > jQuery("#cut_of_time").timepicker('getTimeAsDate')) {
//              jQuery("#dialog-message").html('Giờ COT phải lớn hơn Giờ COT cũ');
//              jQuery('#cut_of_time').focus();
//              jQuery('#cut_of_time').addClass("ui-state-error");
//              bValid = false;
//          }
//          jQuery("#cut_of_timeOLD").timepicker('destroy');
          if (bValid){
            jQuery("#action").val('SAVE');
              jQuery.ajax( {
                  type : "POST", url : "capnhatcotExc.do", data :  {
                      "cut_of_time" : jQuery('#cut_of_time').val(), "id" : jQuery('#id').val(), "nd" : Math.random() * 100000
                  },
                  dataType : 'json', success : function (data, textstatus) {
                      if (textstatus != null && textstatus == '<%=AppConstants.SUCCESS%>') {
                          if (data.success == 'success') {
                              jQuery("#dialog-message").html('Bạn đã sửa thành công');
                              jQuery("#dialog-message").dialog("open");
                          }else{
                              jQuery("#dialog-message").html(data.failure);
                              jQuery("#dialog-message").dialog("open");
                          }
                      }

                  },
                  error : function (textstatus) {
                      jQuery("#dialog-message").html(textstatus);
                      jQuery("#dialog-message").dialog("open");
                  }
              });
              }
      }
      if (type == 'close') {
          jQuery('#frmCOT').html('');
          f.action = 'danhsachcot.do';
          f.submit();
      }

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