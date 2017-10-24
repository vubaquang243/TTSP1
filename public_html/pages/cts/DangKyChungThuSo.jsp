<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dien.tra.soat.js" type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.ChungThuSoResource"/>
<script type="text/javascript">
  jQuery.noConflict();

  function activex() {
      jQuery("#messageError").val('');
      try {
          var v_cert = jQuery("#eSign")[0];
          v_cert.InitCert();
          jQuery("#serial").val(v_cert.Serial);// Số serial
          jQuery("#noi_dung").val(v_cert.CertContent);//Nội dung cert (Ko cần hiện thi cho NSD)
          jQuery("#ma_nguoi_duoc_cap").val(v_cert.IssuedTo);//Người được cấp
          jQuery("#nha_cung_cap").val(v_cert.IssuedBy);//Người cấp
          jQuery("#hieu_luc_tu_ngay").val(convertDateToFormatDDMMYYYY(v_cert.ValidFrom));//Hiệu lực từ ngày
          jQuery("#hieu_luc_den_ngay").val(convertDateToFormatDDMMYYYY(v_cert.ValidTo));//Hiệu lực den ngay
      }
      catch (e) {
          jQuery("#messageError").val(e.message);
          jQuery("#dialog-message").html(e.message);
          jQuery("#dialog-message").dialog("open");
      }
  }

  function convertDateToFormatDDMMYYYY(date) {
      var tempDate =new Date(date);
      var dayOfMonth = (tempDate.getDate() >= 10) ? tempDate.getDate(): '0' + tempDate.getDate();
      var month = tempDate.getMonth()+1 ;
      month=(month>= 10) ? month : '0' + month;
      return dayOfMonth + '/' + month + '/' + tempDate.getFullYear();
  }
</script>
<script type="text/javascript">
  //************************************ LOAD PAGE **********************************
  jQuery(document).ready(function () {      
      var evenButtonBeforeField = jQuery("#evenButtonBefore"), dialog_confirm = jQuery("#dialog-confirm"), dialog_message = jQuery("#dialog-message"), frmDangKyCTS = jQuery("#frmDangKyCTS");
      //************************************ dialog message ******************************************************
      dialog_message.dialog( {
          autoOpen : false, resizable : false, height : 250, width : 400, modal : true, buttons :  {
              Ok : function () {
                  jQuery(this).dialog("close");
                  jQuery("#serial").val('');// Số serial
                  jQuery("#noi_dung").val('');//Nội dung cert (Ko cần hiện thi cho NSD)
                  jQuery("#ma_nguoi_duoc_cap").val('');//Người được cấp
                  jQuery("#nha_cung_cap").val('');//Người cấp
                  jQuery("#hieu_luc_tu_ngay").val('');//Hiệu lực từ ngày
                  jQuery("#hieu_luc_den_ngay").val('');//Hieu lực đến ngày
              }
          }
      });
      //************************************ dialog confirm message	******************************************************
      dialog_confirm.dialog( {
          autoOpen : false, resizable : false, height : 200, width : 380, modal : true, buttons :  {
              "Có" : function () {
                  var action = evenButtonBeforeField.val();
                  if (action != null && action == '<%=AppConstants.ACTION_EXIT%>') {
                      frmDangKyCTS.attr( {
                          action : 'thoatView.do'
                      });
                      frmDangKyCTS.submit();
                  }
                  jQuery(this).dialog("close");
              },
"Không" : function () {
                  jQuery(this).dialog("close");
              }
          }
      });
      //************************************ CLICK BUTTON THOAT **********************************
      jQuery("#btn_thoat").click(function () {
          evenButtonBeforeField.val('<%=AppConstants.ACTION_EXIT%>');
          dialog_confirm.html('<fmt:message key="dang_ky_cts.page.message_confirm.thoat"/>');
          dialog_confirm.dialog("open");
      });
      //************************************ CLICK BUTTON DANG KY **********************************
      jQuery("#btn_dangKy").click(function () {
          var messageError = jQuery("#messageError").val();
          if (messageError == null || messageError == '') {
              jQuery.ajax( {
                  type : "POST", url : "dangkyctsExc.do", data :  {
                      "noi_dung" : jQuery("#noi_dung").val(), "serial" : jQuery("#serial").val(), "hieu_luc_tu_ngay" : jQuery("#hieu_luc_tu_ngay").val(), "hieu_luc_den_ngay" : jQuery("#hieu_luc_den_ngay").val(), "nd" : Math.random() * 100000
                  },
                  dataType : 'json', success : function (data, textstatus) {
                      if (textstatus != null && textstatus == '<%=AppConstants.SUCCESS%>') {
                          if (data != null) {
                              if (data.ERROR == undefined || data.ERROR == 'undefined') {
                                  dialog_message.html('<fmt:message key="dang_ky_cts.page.message.dang_ky_thanh_cong"/>');
                              }
                              else {
                                  dialog_message.html('<div><span style=\"color:red;\">Lỗi! ' + data.ERROR + '</span></div>');
                              }
                              dialog_message.dialog("open");

                          }
                      }

                  },
                  error : function (xhr) {
                      dialog_message.html(xhr.responseText);
                      dialog_message.dialog("open");
                  }
              });
          }
          else {
              dialog_message.html(messageError);
              dialog_message.dialog("open");
          }
      });
      //load axtive x
      activex();
  });
</script>
<body>
  <div id="body">
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
              <fmt:message key="chung_thu_so.page.dang_ky_cts.title"/></span>
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
            <span>&nbsp;&nbsp;&nbsp;&nbsp;<font color="Gray">
                <fmt:message key="chung_thu_so.page.title.thongtin"/>
              </font></span>
          </td>
        </tr>
      </tbody>
       
      <tr>
        <td>
          <input type="hidden" id="evenButtonBefore"/>
           
          <input type="hidden" id="messageError"/>
           
          <html:form action="dangkycts.do" styleId="frmDangKyCTS">
            <html:hidden property="noi_dung" styleId="noi_dung"/>
            <object id="eSign" name="eSign" height="31" width="177"
                    classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261"
                    viewastext="VIEWASTEXT" codebase='VSTeTokenSetup.cab'></object>
            <div style="padding-top:10px;">
              <table style="BORDER-COLLAPSE: collapse;" border="0"
                     cellspacing="0" bordercolor="#b0c4de" cellpadding="0"
                     width="100%" align="center">
                <tbody>
                  <tr>
                    <td align="right">
                      <label for="ma_nguoi_duoc_cap">
                        <fmt:message key="chung_thu_so.page.lable.nguoi_duoc_cap"/>
                      </label>
                    </td>
                    <td align="left" colspan="3">
                      <html:text property="ma_nguoi_duoc_cap"
                                 styleId="ma_nguoi_duoc_cap"
                                 styleClass="promptText"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                 onmouseover="Tip(this.value)"
                                 onmouseout="UnTip()" readonly="true"/>
                       
                      <html:text property="ten_nguoi_duoc_cap"
                                 styleId="ten_nguoi_duoc_cap"
                                 styleClass="promptText"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                 onmouseover="Tip(this.value)"
                                 onmouseout="UnTip()" readonly="true"/>
                    </td>
                  </tr>
                  <tr>
                    <td align="right" width="25%">
                      <label for="nha_cung_cap">
                        <fmt:message key="chung_thu_so.page.lable.nguoi_cap"/>
                      </label>
                    </td>
                    <td width="25%">
                      <html:text property="nha_cung_cap" styleId="nha_cung_cap"
                                 styleClass="promptText"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                 onmouseover="Tip(this.value)"
                                 onmouseout="UnTip()" readonly="true"/>
                    </td>
                    <td align="right" width="15%">
                      <label for="serial">
                        <fmt:message key="chung_thu_so.page.lable.so_serial"/>
                      </label>
                    </td>
                    <td>
                      <html:text property="serial" styleId="serial"
                                 styleClass="promptText" readonly="true"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                 onmouseover="Tip(this.value)"
                                 onmouseout="UnTip()"/>
                    </td>
                  </tr>
                  <tr>
                    <td align="right">
                      <label for="hieu_luc_tu_ngay">
                        <fmt:message key="chung_thu_so.page.lable.hieu_luc_tu_ngay"/>
                      </label>
                    </td>
                    <td>
                      <html:text property="hieu_luc_tu_ngay"
                                 styleId="hieu_luc_tu_ngay"
                                 styleClass="promptText"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                 readonly="true" onmouseover="Tip(this.value)"
                                 onmouseout="UnTip()"/>
                    </td>
                    <td align="right">
                      <label for="hieu_luc_den_ngay">
                        <fmt:message key="chung_thu_so.page.lable.hieu_luc_den_ngay"/>
                      </label>
                    </td>
                    <td>
                      <html:text property="hieu_luc_den_ngay"
                                 styleId="hieu_luc_den_ngay"
                                 styleClass="promptText"
                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                 readonly="true" onmouseover="Tip(this.value)"
                                 onmouseout="UnTip()"/>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </html:form>
        </td>
      </tr>
       
    </table>
  </div>
  <div style="padding-top:10px;" align="center">
    <table class="buttonbar" align="center">
      <tr>
        <td>
          <span id="dk">
            <button id="btn_dangKy" accesskey="k" tabindex="102">
              <fmt:message key="dang_ky_cts.page.button.registry"/>
            </button></span>
           
          <span id="exit"> 
            <button id="btn_thoat" accesskey="O" class="buttonCommon">
              <fmt:message key="dang_ky_cts.page.button.exit"/>
            </button>
             </span>
        </td>
      </tr>
    </table>
  </div>
  <div id="dialog-confirm"
       title='<fmt:message key="chung_thu_so.page.title.dialog_confirm"/>'>
    <p>
      <span class="ui-icon ui-icon-alert"
            style="float:left; margin:0 7px 20px 0;"></span>
       
      <span id="message_confirm"></span>
    </p>
  </div>
  <div id="dialog-message"
       title='<fmt:message key="chung_thu_so.page.title.dialog_message"/>'>
    <p>
      <span class="ui-icon ui-icon-circle-check"
            style="float:left; margin:0 7px 50px 0;"></span>
       
      <span id="message"></span>
    </p>
  </div>
</body>
<%@ include file="/includes/ttsp_bottom.inc"%>