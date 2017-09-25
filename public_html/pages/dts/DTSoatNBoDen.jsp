<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script language="JavaScript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dts.noibo.den.js"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.DTSoatNoiBoResourceDen"/>
<title><fmt:message key="DTSoatNoiBoDen.page.title"/></title>
<%
  // tham so truyen vao de focus len lydo_ktt_day_lai
  String strLoaiUserFocus = "";  
  strLoaiUserFocus = request.getAttribute("chucdanh").toString();   
%>
<script type="text/javascript">
      jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).ready(function () {
      if('<%=request.getAttribute("typeExit")%>' == "true"){
        selectedTopRowNoiBoDen(jQuery("#col_dts_0").val(),"row_dts_0",'<%=request.getAttribute("chucdanh")%>',true);
      }else{
        if(jQuery("#col_dts_0").val()!=null){
           selectedTopRowNoiBoDen(jQuery("#col_dts_0").val(),"row_dts_0",'<%=request.getAttribute("chucdanh")%>',false);
        }else{
          selectedTopRowNoiBoDen('',"row_dts_0",'<%=request.getAttribute("chucdanh")%>',false);
        }
      }
      // chu ky duyet 
      strLoaiUser = '<%=request.getAttribute("chucdanh")%>'; 
      // end 
      var exitTemp = null;
      //
      //litmit char truong noi dung va ly do
      jQuery("#noidung").keyup(function () {
          limitCharsDTSNoiBoDen(jQuery("#noidung").attr('id'), 210);
      });
      jQuery("#lydo_ktt_day_lai").keyup(function () {
          limitCharsDTSNoiBoDen(jQuery("#lydo_ktt_day_lai").attr('id'), 210);
      }); 
      //end

      // open cac dialog 
      jQuery("#dialog-message-check").dialog( {
        autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              Ok : function () {
              jQuery(this).dialog("close");
              var idFieldFocus = jQuery("#focusField").val();
                  if (idFieldFocus != null && idFieldFocus != ''){
                    jQuery("#" + idFieldFocus).focus(); 
                  }
                }
              }
      });
      jQuery("#dialog-message").dialog( {
          autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              Ok : function () {
//                  selectedTopRowNoiBoDen(jQuery("#col_dts_0").val(),"row_dts_0",'<%=request.getAttribute("chucdanh")%>',false);
//                  jQuery("#ma_don_vi_nhan_tra_soat").removeClass('ui-state-error');
//                  jQuery("#noidung").removeClass('ui-state-error');                  
                  jQuery(this).dialog("close");                  
                  }                   
              }          
      });
      
      //dialog confirm message	
      jQuery("#dialog-confirm").dialog( {
          autoOpen : false, resizable : false, height : 200, width : 380, modal : true, buttons :  {
              "Có" : function () {
                  var action = jQuery("#evenButtonBefore").val();                  
                  if(exitTemp==null){
                        if (action != null && action != '<%=AppConstants.ACTION_EXIT%>') {                            
                            if (action == '<%=AppConstants.ACTION_APPROVED%>') {
//                                 if(!kyDTSNoiBoDen()){
//                                    alert("Bạn ký Không thành công");
//                                    return false;
//                                  }else{
                                    updateExcuteDTSNoiBoDen(action);    
//                                  }
                            }else if(action == '<%=AppConstants.ACTION_WAIT_PROCESS%>'){
                              updateExcuteDTSNoiBoDen(action);
                            }
                            jQuery(this).dialog("close");
                       } 
                        
                  } else{
                      jQuery(this).dialog("close");    
                        exitViewDTSNoiBoDen();
                  }                
              },              
              "Không" : function () {
                  exitTemp = null;
                  jQuery(this).dialog("close");
              }
          }
      });
      
      //dialog form Tra cuu dien tra soat
      jQuery("#dialog-form").dialog( {          
          autoOpen : false, height : 350, width : 330, modal : true, buttons :  {
              "Tìm kiếm" : function () {                  
                  findDTSNoiBoDen();
                  jQuery(this).dialog("close");
              },
             "Thoát" : function () {
                  jQuery(this).dialog("close");
              }
          },
              "Đóng" : function () {
          }
      });
      //**************************BUTTON Them moi DTS CLICK********************************************
      jQuery("#btnXacNhan").click(function () {
          jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_WAIT_PROCESS%>');
          var evenButtonBefore = jQuery("#evenButtonBefore").val();
          if (selectedRowBeforeClickButtonNoiBoDen()) {
             if (evenButtonBefore != null && evenButtonBefore == '<%=AppConstants.ACTION_WAIT_PROCESS%>') {   
                        var trang_thai_correct = jQuery("#trang_thai").val();
                        if(trang_thai_correct == '00'){
                           jQuery("#dialog-confirm").html('<fmt:message key="DTSoatNoiBoDen.page.message_confirm.xuly_dts"/>');                           
                           jQuery("#dialog-confirm").dialog("open");                           
                        }
                        else{
                          jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBoDen.page.message.khong_duoc_xuly"/>');
                          jQuery("#dialog-message-check").dialog("open");
                        }   
                  }
          } 
      });
      jQuery("#btnTim").click(function () {
          jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_FIND%>');
          jQuery("#ma_ttv_tk").val('');
          jQuery("#nh_kb_nhan_tk").val('');
          jQuery("#so_dts_tk").val('');
          if ('<%=request.getAttribute("chucdanh")%>' == 'TTV') {
              jQuery("#nh_kb_nhan_ttk").focus();
              jQuery("#ma_ttv_timkiem").hide();
              jQuery("#ma_ttv_timkiem").val('');
              
          }
          else {
            jQuery("#ma_ttv_timkiem").show();
            jQuery("#ma_ttv_tk").focus();
          }
          jQuery("#dialog-form").dialog("open");
      });
      jQuery("#btnDuyet").click(function () {
          jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_APPROVED%>');
          var evenButtonBefore = jQuery("#evenButtonBefore").val();
          if (selectedRowBeforeClickButtonNoiBoDen()) {
             if (evenButtonBefore != null && evenButtonBefore == 'APPROVED') {   
                        var trang_thai_correct = jQuery("#trang_thai").val();
                        if(trang_thai_correct == '02'){                        
                           jQuery("#dialog-confirm").html('<fmt:message key="DTSoatNoiBoDen.page.message_confirm.duyet_dts"/>');                           
                           jQuery("#dialog-confirm").dialog("open");                           
                        }
                        else{
                          jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBoDen.page.message.khong_duoc_duyet"/>');
                          jQuery("#dialog-message-check").dialog("open");
                        }   
                  }
          } 
      });
      function kyDTSNoiBoDen() {
            try {
                var cert = jQuery("#eSign")[0];
//                alert(cert);
                cert.InitCert();
                var serial = cert.Serial;
                jQuery("#certSerial").val(serial);
                var nd = jQuery("#noidung").val();
                var sign = cert.Sign(nd);
                jQuery("#signature").val(sign);
                return true;
            }
            catch (e) {
                alert("Ký duyệt điện tra soát nội bộ đi không thành công, chữ ký số không hợp lệ ! \n"+ e.description);
                return false;
            }
        }
      //**************************BUTTON Thoat CLICK********************************************
      
      jQuery("#btnExit").click(function () {           
          exitTemp = '<%=AppConstants.ACTION_EXIT%>';
          var bCheck ='<%=request.getAttribute("typeExit")%>' ; 
           if(bCheck== 'true'){
           var ttv_nhan_back = '<%=request.getParameter("ttv_nhan")%>';
           var trang_thai_back= '<%=request.getParameter("trang_thai")%>';
           var tu_ngay_back= '<%=request.getParameter("tu_ngay")%>';
           var den_ngay_back= '<%=request.getParameter("den_ngay")%>';
           var nhkb_chuyen_back= '<%=request.getParameter("nhkb_chuyen")%>';
           var nhkb_nhan_back= '<%=request.getParameter("nhkb_nhan")%>';
           var so_dts_back= '<%=request.getParameter("so_dts")%>';
           var ten_nhkb_chuyen_back = '<%=request.getParameter("ten_nhkb_chuyen")%>';
           var ten_nhkb_nhan_back = '<%=request.getParameter("ten_nhkb_nhan")%>';
           var urlBack = "TraCuuDTSoatNoiBo.do?action=Back";
           if(ttv_nhan_back != null &&ttv_nhan_back!='null' ){
             urlBack += "&ttv_nhan_back="+ttv_nhan_back+"";
           }
            if(trang_thai_back != null &&trang_thai_back!='null'){
             urlBack += "&trang_thai_back="+trang_thai_back+"";
           }
            if(tu_ngay_back != null &&tu_ngay_back!='null'){
             urlBack += "&tu_ngay_back="+tu_ngay_back+"";
           }
            if(den_ngay_back != null &&den_ngay_back!='null'){
             urlBack += "&den_ngay_back="+den_ngay_back+"";
           }
            if(so_dts_back != null &&so_dts_back!='null'){
             urlBack += "&so_dts_back="+so_dts_back+"";
           }
            if(nhkb_chuyen_back != null && nhkb_chuyen_back != 'null'){
             urlBack += "&nhkb_chuyen_back="+nhkb_chuyen_back+"";
           }
            if(nhkb_nhan_back != null && nhkb_nhan_back != 'null'){
             urlBack += "&nhkb_nhan_back="+nhkb_nhan_back+"";
           }
           if(ten_nhkb_chuyen_back!=null && ten_nhkb_chuyen_back!= 'null'){
              urlBack += "&ten_nhkb_chuyen_back="+ten_nhkb_chuyen_back+"";
            }
            if(ten_nhkb_nhan_back!=null && ten_nhkb_nhan_back!= 'null'){
               urlBack += "&ten_nhkb_nhan_back="+nhkb_nhan_back+"";
            }
           document.forms[0].action = urlBack;
           document.forms[0].submit();
          }else{
           jQuery("#dialog-confirm").html('<fmt:message key="DTSoatNoiBoDen.page.message_confirm.thoat"/>');
            jQuery("#dialog-confirm").dialog("open");
          }
          
      });
      
  });
  function updateExcuteDTSNoiBoDen(action) {
        if (action == '<%=AppConstants.ACTION_APPROVED%>' || action == '<%=AppConstants.ACTION_WAIT_PROCESS%>') {
            jQuery.ajax( {
                type : "POST", url : "DTSoatNoiBoDenUpdateExc.do", data :  {
                    "action" : action,"certSerial" :jQuery("#certSerial").val(),"signature":jQuery("#signature").val(), "noidung" : jQuery("#noidung").val(), 
                    "id" : jQuery("#id").val(), "trang_thai" : jQuery("#trang_thai").val(), "ktt_duyet" : jQuery("#ktt_duyet").val(), "ngay_duyet" : jQuery("#ngay_duyet").val(), "ma_don_vi_nhan_tra_soat" : jQuery("#ma_don_vi_nhan_tra_soat").val(), "lydo_ktt_day_lai" : jQuery("#lydo_ktt_day_lai").val(), "nd" : Math.random() * 100000
                },
                success : function (data, textstatus) {
                        if(data!=null){
                          if (data.logout != null && data.logout) {
                            document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                            document.forms[0].submit();
                          }
                          else {
                              refreshGridDTSNoiBoDen();
                              if(data.exception_message !=null && data.exception_message != ""){
                                  jQuery("#dialog-message").html("Duy&#7879;t &#273;i&#7879;n tra so&#225;t kh&#244;ng th&#224;nh c&#244;ng : \n" + data.exception_message);
                                  jQuery("#dialog-message").dialog("open");
                              }
                              if (action == 'APPROVED') {
                                    var sign = data.verifySign;
                                    var sign_dtl =  data.verifySign_dtl;
                                    if(sign.indexOf("ERROR") != -1){
                                      alert("Duyệt không thành công!\n"+sign_dtl);
                                    }
                                    if(sign.indexOf("INVALID") != -1){
                                      alert("Duyệt không thành công!\n"+sign_dtl);
                                    }
                                    var action_status = data.action_status;
                                    if(action_status == 'SUCCESS'){
                                      jQuery("#dialog-message").html('<fmt:message key="DTSoatNoiBoDen.page.message.duyet_thanh_cong"/>');      
                                      jQuery("#dialog-message").dialog("open");
                                    }else{
                                      var exception_message =  data.exception_message;
                                        if(exception_message != null && exception_message != 'null'){
                                          alert("Duyệt không thành công! \n"+exception_message);
                                        }else{
                                          alert("Duyệt không thành công! \n");
                                     }
                                  }
                                }else if(action == '<%=AppConstants.ACTION_WAIT_PROCESS%>'){
                                      jQuery("#dialog-message").html('<fmt:message key="DTSoatNoiBoDen.page.message.xuly_thanh_cong"/>');
                                      jQuery("#dialog-message").dialog("open");
                                }
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
    
</script>
<div class="app_error"><html:errors/></div>
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
  <tbody>
    <tr>
      <tr>
            
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg">
              <span class="title2">
              &#272;i&#7879;n tra so&#225;t n&#7897;i b&#7897; &#273;&#7871;n
              </span>
            </td>
             <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
    </tr>
  </tbody>
</table>
<table class="tableBound">
        <tr>
          <td>
            <table width="99%">
              <tbody>
              <tr>
                <td><font color="red">
                    <html:errors/>
                  </font> 
                </td></tr>
              </tbody>
            </table>

        <table cellspacing="0" cellpadding="0" width="100%">
          <tbody>
            <tr>
              <td valign="top">
                <table class="borderChungTu" cellspacing="1" cellpadding="0" width="100%">
                  <tbody>
                    <tr>
                      <td valign="top">
                        <table class="borderChungTu" cellspacing="1" cellpadding="0" width="100%">
                          <tbody>
                            <tr>
                              <td valign="top">
                                <table class="bordertableChungTu" cellspacing="0" cellpadding="0"
                                       width="100%">        
                                <html:form styleId="frmDTS" action="/DTSoatNoiBoDen.do">
                                  <tbody>
                                    <tr>
                                      <td width="15%" valign="top">
                                        <div class="clearfix">
                                          <fieldset class="fieldsetCSS">
                                            <legend style="vertical-align:middle">
                                              <fmt:message key="DTSoatNoiBoDen.page.sodientrasoat"/>
                                            </legend>
                                            <div class="sodientrasoattable">
                                              <div>
                                                <table class="data-grid" cellspacing="0" cellpadding="0"
                                                       width="100%">
                                                  <thead>
                                                    <tr>
                                                      <th class="ui-state-default ui-th-column"
                                                          height="20" width="62%;">
                                                        <fmt:message key="DTSoatNoiBoDen.page.sodientrasoat.DTS"/>
                                                      </th>
                                                      <th height="20" width="28%"
                                                          class="ui-state-default ui-th-column">
                                                        <fmt:message key="DTSoatNoiBoDen.page.sodientrasoat.TinhTrang"/>
                                                      </th>
                                                      <th height="20" width="10%;"
                                                          class="ui-state-default ui-th-column">
                                                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                      </th>
                                                    </tr>
                                                  </thead>
                                                </table>
                                              </div>                   
                                              <div class="ui-jqgrid-bdiv ui-widget-content"
                                                   style="height:300px;">
                                                <div style="position: relative;">
                                                  <table  class="data-grid" id="data-grid"
                                                          style="border:0px;width:100%"
                                                         cellspacing="0" cellpadding="0"                                  
                                                         width="100%">
                                                    <tbody>
                                                      <logic:present name="lstSDTS">
                                                        <div>
                                                          <logic:notEmpty name="lstSDTS">
                                                            <logic:iterate id="items" name="lstSDTS"
                                                                           indexId="index">
                                                              <!--<tr style="border:1px;width:100%"
                                                                  class="ui-widget-content jqgrow ui-row-ltr"
                                                                  id='row_dts_<bean:write name="index"/>'
                                                                  onclick="fillDataDTSNoiBoDen('<bean:write name="items" property="id"/>','row_dts_<bean:write name="index"/>',false);"
                                                                  >
                                                                <td width="68%;" align="center">
                                                                  <bean:write name="items"
                                                                              property="id"/>
                                                                  
                                                                </td>
                                                                -->
                                                                <tr class="ui-widget-content jqgrow ui-row-ltr" 
                                                                id="row_dts_<bean:write name="index"/>"
                                                                onkeydown="focusDayLaiDTSNBoDen('<%=strLoaiUserFocus%>',trang_thai);"	
                                                                ondblclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');"
                                                                onclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');fillDataDTSNoiBoDen('<bean:write name="items" property="id"/>','row_dts_<bean:write name="index"/>',false);">
                                                                <td width="40%;" align="center" id="td_dts_<bean:write name="index"/>">
                                                                  <input tabindex="0" name="row_item" id="col_dts_<bean:write name="index"/>" 
                                                                  type="text" style="border:0px;font-size:10px;float:left;" 
                                                                  value="<bean:write name="items" property="id"/>" 
                                                                  onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)" readonly="true"/>
                                                                </td>
                                                                <td width="28%;" align="center">
                                                                  <logic:equal name="items"
                                                                               property="trang_thai"
                                                                               value="02">
                                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" border="0" title="Chờ KTT duyệt"/>
                                                                  </logic:equal>
                                                                   
                                                                  <logic:equal name="items"
                                                                               property="trang_thai"
                                                                               value="01">
                                                                        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg"
                                                                         border="0"
                                                                         title="KTT đẩy lại"/>
                                                                   
                                                                  </logic:equal>
                                                                   
                                                                  <logic:equal name="items"
                                                                               property="trang_thai"
                                                                               value="03">
                                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif"
                                                                         border="0" title="Đã duyệt"/>
                                                                  </logic:equal>
                                                                   
                                                                  <logic:equal name="items"
                                                                               property="trang_thai"
                                                                               value="04">
                                                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif"
                                                                         border="0" title="Hủy"/>                                            
                                                                  </logic:equal>
                                                                   <logic:equal name="items"
                                                                               property="trang_thai"
                                                                               value="00">
                                                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/choxuly.jpg"
                                                                         border="0" title="Chờ xử lý"/>                                            
                                                                  </logic:equal>
                                                                </td>
                                                              </tr>
                                                            </logic:iterate>
                                                          </logic:notEmpty>
                                                           
                                                          <logic:empty name="lstSDTS">
                                                            <tr>
                                                              <td colspan="5" align="center">
                                                                <span class="text_red">
                                                                  <fmt:message key="DTSoatNoiBoDen.page.sodientrasoat.error"/></span>
                                                              </td>
                                                            </tr>
                                                          </logic:empty>
                                                        </div>
                                                      </logic:present>
                                                    </tbody>
                                                  </table>
                                                </div>
                                              </div>
                                              <div>
                                                <table class="data-grid" cellspacing="0" cellpadding="0"
                                                       width="100%">
                                                  <thead>
                                                    <tr id="refreshButton">
                                                      <td  class="ui-state-default ui-th-column"
                                                          title="Refresh" id="refresh">
                                                        <div style="float:left;cursor:pointer" class="ui-pg-div">
                                                          <span class="ui-icon ui-icon-refresh"
                                                                onclick="refreshGridDTSNoiBoDen();"></span>
                                                        </div>
                                                        
                                                        <div class="ui-pg-div" style="float:left;"><span id="btnTim" class="ui-icon ui-icon-search" title="Tìm kiếm" style="cursor:pointer;"></span></div>
                                                      </td>
                                                    </tr>
                                                  </thead>
                                                </table>
                                                <div style="padding-top:10px;float: left;">
                                                  <fmt:message key="DTSoatNoiBoDen.page.label.trang_thai"/>: 
                                                  <html:text property="mo_ta_trang_thai"
                                                              readonly="readonly"
                                                              onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                               styleClass="fieldTextCodeTrans"
                                                               style="WIDTH: 120px;border:0px;font-weight:bold;" 
                                                              styleId="mo_ta_trang_thai"/>
                                                  <!--<span id="mo_ta_trang_thai" style="font-weight:bold;"></span>-->
                                                </div>
                                              </div>
                                            </div>
                                          </fieldset>
                                        </div>
                                      </td>
                                      <td width="85%" valign="top">
                                        <fieldset class="fieldsetCSS">
                                          <legend style="vertical-align:middle">
                                            <fmt:message key="DTSoatNoiBoDen.page.thongtinchung"/>
                                          </legend>
                                          <div style="padding:5px 5px 5px 5px;">
                                              <input type="hidden" id="rowSelected"/>
                                              <input type="hidden" id="evenButtonBefore"/>
                                              <input type="hidden" id="focusField"/>
                                              <input type="hidden" id="row_selectedId"/>
                                              <input type="hidden" name="certSerial" id="certSerial" />
                                              <input type="hidden" name="signature" id="signature" />     
                                              <html:hidden property="id" styleId="id"/>
                                              <html:hidden property="nhkb_nhan" styleId="nhkb_nhan"/>
                                              <html:hidden property="nhkb_chuyen"
                                                           styleId="nhkb_chuyen"/>
                                              <html:hidden property="trang_thai" styleId="trang_thai"/>
                                              <table style="BORDER-COLLAPSE: collapse;" border="1"
                                                     cellspacing="0" bordercolor="#b0c4de"
                                                     cellpadding="0" width="99%">
                                                <tbody>
                                                  <tr style="width:100%">
                                                    <td align="right" width="16%;">
                                                      <label for="nhkb_chuyen">
                                                        <fmt:message key="DTSoatNoiBoDen.page.thongtinchung.donviTS"/>
                                                      </label>
                                                    </td>
                                                    <td width="34%;" align="left">
                                                      <html:text property="ma_don_vi_tra_soat"
                                                                 styleId="ma_don_vi_tra_soat"
                                                                 styleclass="fieldTextCode"
                                                                 style="WIDTH:30%;"
                                                                 onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                                 />
                                                      <html:text property="ten_don_vi_tra_soat"
                                                               styleId="ten_don_vi_tra_soat" 
                                                               readonly="readonly"
                                                               styleClass="fieldTextTrans"
                                                               onmouseout="UnTip()"
                                                               onmouseover="Tip(this.value)" 
                                                               onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                               style="WIDTH:65%;border:0px;font-weight:bold;"/>
                                                    </td>
                                                    <!--<td align="left" colspan="2" style="width:25%;">
                                                      <b>
                                                        <html:text property="ten_don_vi_tra_soat"
                                                               styleId="ten_don_vi_tra_soat" 
                                                               readonly="readonly"
                                                               styleClass="fieldTextTrans"
                                                               onmouseout="UnTip()"
                                                               onmouseover="Tip(this.value)" 
                                                               style="border:0px;font-weight:bold;"/>
                                                      </b>
                                                    </td>-->
                                                    <td align="right" width="16%">
                                                      <label for="ma_don_vi_tra_soat">
                                                        <fmt:message key="DTSoatNoiBoDen.page.thongtinchung.donvinhanTS"/>
                                                      </label>
                                                    </td>
                                                    <td width="34%" align="left">
                                                      <%--<html:select property="ma_don_vi_nhan_tra_soat"  styleId="ma_don_vi_nhan_tra_soat" 
                                                                  onblur="getTenNganHang('ma_don_vi_nhan_tra_soat', 'ten_don_vi_nhan_tra_soat', 'nhkb_nhan')"                                         
                                                                  style="WIDTH:35%" tabindex="101" onkeydown="if(event.keyCode==13) event.keyCode=9; else if(event.keyCode==8) event.keyCode=46;">
                                                        <logic:notEmpty name="colDMNH">
                                                          <html:optionsCollection name="colDMNH" value="ma_nh" label="ma_nh"/>
                                                        </logic:notEmpty>
                                                        <html:option styleClass="jecEditableOption" value=""></html:option>
                                                      </html:select>--%>
                                                      <html:text property="ma_don_vi_nhan_tra_soat"
                                                                 styleId="ma_don_vi_nhan_tra_soat"
                                                                 styleClass="fieldTextCode"
                                                                 onmouseover="Tip(this.value)"
                                                                 onmouseout="UnTip()"
                                                                 onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                                 onblur="getTenNganHang('ma_don_vi_nhan_tra_soat', 'ten_don_vi_nhan_tra_soat', 'nhkb_nhan')"
                                                                 style="width:30%;"
                                                                 maxlength="8"/>
                                                      <html:text property="ten_don_vi_nhan_tra_soat" 
                                                               readonly="readonly"
                                                               styleClass="fieldTextTrans"
                                                               onmouseout="UnTip()"
                                                               onmouseover="Tip(this.value)" 
                                                               onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                               style="WIDTH:65%;border:0px;font-weight:bold;" 
                                                               styleId="ten_don_vi_nhan_tra_soat"/>
                                                      <%--<html:text property="ma_don_vi_nhan_tra_soat"
                                                                 styleId="ma_don_vi_nhan_tra_soat"
                                                                 styleclass="fieldTextCode"
                                                                 onmouseover="Tip(this.value)"
                                                                 onmouseout="UnTip()"
                                                                 onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                                                 onblur="getTen_donvi_nhan_dts_tudo(ma_don_vi_nhan_tra_soat)"
                                                                 style="width:60px;"
                                                                 maxlength="8"/>--%>                               
                                                  
                                                    </td>
                                                    <!--<td align="left" colspan="2" style="width:25%;">
                                                      <b>
                                                        
                                                        </b>
                                                    </td>-->
                                                  </tr>
                                                  <tr>
                                                    <td align="right" style="WIDTH:16%">
                                                      <label for="ttv_nhan">
                                                        <!--<fmt:message key="DTSoatNoiBoDen.page.thongtinchung.nguoilap"/>-->
                                                        Người nhận
                                                      </label>
                                                    </td>
                                                    <td align="left" style="WIDTH:34%">
                                                      <html:text styleClass="fieldTextCode"
                                                                 style="WIDTH:30%"
                                                                 property="ma_ttv_nhan"
                                                                 onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                                 styleId="ma_ttv_nhan"/>
                                                      <html:text readonly="readonly"
                                                               styleClass="fieldTextTrans"
                                                               onmouseout="UnTip()"
                                                               onmouseover="Tip(this.value)"
                                                               onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                               style="WIDTH:65%;border:0px;font-weight:bold;" 
                                                                 property="ttv_nhan"
                                                                 styleId="ttv_nhan"/>         
                                                    </td>
                                                                                     
                                                    <td align="right" style="WIDTH:16%">
                                                      <label for="ngay_nhan">
                                                        <fmt:message key="DTSoatNoiBoDen.page.thongtinchung.ngaylap"/>
                                                      </label>
                                                    </td>
                                                    <td align="left" style="WIDTH:34%">
                                                      <html:text styleClass="fieldTextCode"
                                                                 style="WIDTH:30%"
                                                                 onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                                 property="ngay_nhan"
                                                                 styleId="ngay_nhan"/>
                                                    </td>
                                                  </tr>
                                                  <tr>
                                                    <td align="right" style="WIDTH:16%">
                                                      <label for="nguoi_ks_nh">
                                                        <!--<fmt:message key="DTSoatNoiBoDen.page.thongtinchung.KTT"/>-->
                                                        Người KS
                                                      </label>
                                                    </td>
                                                    <td align="left" style="WIDTH:34%">
                                                      <html:text styleClass="fieldTextCode"
                                                                 style="WIDTH:30%"
                                                                 property="ma_ktt"
                                                                 onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                                 styleId="ma_ktt"/>
                                                      <html:text readonly="readonly"
                                                               styleClass="fieldTextTrans"
                                                               onmouseout="UnTip()"
                                                               onmouseover="Tip(this.value)"
                                                               onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                               style="WIDTH:65%;border:0px;font-weight:bold;" 
                                                                 property="ktt_duyet"
                                                                 styleId="ktt_duyet"/>
                                                    </td>
                                                    
                                                    <td align="right" style="WIDTH:16%">
                                                      <label for="ngay_ks_nh">
                                                        <fmt:message key="DTSoatNoiBoDen.page.thongtinchung.ngaykiemsoat"/>
                                                      </label>
                                                    </td>
                                                    <td align="left" style="WIDTH:34%">
                                                      <html:text styleClass="fieldTextCode"
                                                                 style="WIDTH: 30%;"
                                                                 styleId="ngay_duyet"
                                                                 onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                                 property="ngay_duyet"/>
                                                    </td>
                                                  </tr>
                                                  <tr>
                                                    <td align="right" style="WIDTH:16%">
                                                      <label for="noidung">
                                                        <fmt:message key="DTSoatNoiBoDen.page.thongtinchung.noidung"/>
                                                      </label>
                                                    </td>
                                                    <td colspan="4" style="WIDTH:84%">
                                                      <html:textarea property="noidung"
                                                                     styleId="noidung" cols="3"
                                                                     onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDen(event)"
                                                                     rows="5" style="width:99.5%;WRAP:HARD" 
                                                                     styleClass="fieldTextArea"></html:textarea>
                                                       
                                                      <span style="color:#FF0000"
                                                            id="word_count_noidung">Tổng số
                                                        <span id="display_count">210 kí tự</span></span>
                                                    </td>
                                                  </tr>
                                                  <tr>
                                                    <td align="right" style="WIDTH:16%">
                                                      <label for="lydo_ktt_day_lai">
                                                        <fmt:message key="DTSoatNoiBoDen.page.thongtinchung.lydo"/>
                                                      </label>
                                                    </td>
                                                    <td colspan="4" style="WIDTH:84%">
                                                      <html:textarea property="lydo_ktt_day_lai"
                                                                     styleId="lydo_ktt_day_lai" cols="3"
                                                                     rows="5" style="width:99.5%;" 
                                                                     onkeydown="arrowUpDownDTSNBoDen(event);if(this.readonly){if(event.keyCode==13) event.keyCode=9;}"
                                                                     styleClass="fieldTextArea "></html:textarea>
                                                       
                                                      <span style="color:#FF0000"
                                                            id="word_count_lydo_ktt_day_lai">Tổng số
                                                        <span id="display_count">210 kí tự</span></span>
                                                    </td>
                                                  </tr>
                                                </tbody>
                                              </table>
                                          </div>
                                        </fieldset>
                                      </td>
                                    </tr>
                                  </tbody>
                                </html:form>
                                </table>
                              </td>
                            </tr>
                            <tr align="left" style="width:auto">
                              <td height="30" align="right" valign="middle">
                                <div style="width:auto;padding:10px 5px 10px 5px;">
                                  <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%">
                                    <tr align="right">
                                      <td>
                                        <span id="xacnhan" style="padding-left:10px;">
                                          <button 
                                                  id="btnXacNhan"  class="ButtonCommon"
                                                  accesskey="n" type="button">
                                            <fmt:message key="DTSoatNoiBoDen.page.button.xacnhan"/>
                                          </button></span>                 
                                        <!--<span id="timKiem" style="padding-left:10px;"> 
                                          <button 
                                                  id="btnTim" class="ButtonCommon"
                                                  type="button" accesskey="K">
                                            <fmt:message key="DTSoatNoiBoDen.page.button.timkiem"/>
                                          </button>
                                           </span>-->                 
                                        <span id="duyet" style="padding-left:10px;"> 
                                          <button 
                                                  id="btnDuyet" class="ButtonCommon" type="button"
                                                  accesskey="U">
                                            <fmt:message key="DTSoatNoiBoDen.page.button.duyet"/>
                                          </button>
                                           </span>
                                        <span id="thoatDTSTD" style="padding-left:10px;"> 
                                          <button 
                                                  id="btnExit"  class="ButtonCommon"
                                                  type="button" accesskey="O">
                                            <fmt:message key="DTSoatNoiBoDen.page.button.thoat"/>
                                          </button>
                                           </span>
                                         
                                        <span style="padding-left:10px;"> 
                                          <input style="WIDTH: 1px; HEIGHT: 1px" type="hidden"
                                                 name="thebottom"/>
                                           </span>
                                      </td>
                                    </tr>
                                  </table>
                                </div>
                              </td>
                            </tr>
                          </tbody>
                        </table>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </td>
            </tr>
          </tbody>
        </table>
      </td>
    </tr>
  </table>
                      
                      
                      

<div id="dialog-message"
     title='<fmt:message key="DTSoatNoiBoDen.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<div id="dialog-message-check"
     title='<fmt:message key="DTSoatNoiBoDen.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<div id="dialog-form"
     title='<fmt:message key="DTSoatNoiBoDen.page.title.dialog_form"/>'>
  <p class="validateTips"></p>
    <table>
      <tr id="ma_ttv_timkiem" style="padding-top:10px;">
        <td align="right">          
            <label for="ma_ttv_timkiem" style="padding-left:60px;">
              <fmt:message key="DTSoatNoiBoDen.page.form.timkiem.ma_ttv"/>
            </label> 
        </td>
        <td>
          <input  onkeydown="if(event.keyCode==13) event.keyCode=9;"  type="text" name="ma_ttv_tk" id="ma_ttv_tk"
             class=" text ui-widget-content ui-corner-all"/>
        </td>        
      </tr>
       <tr id="nh_kb_nhan_timkiem" style="padding-top:10px;">
        <td align="right">
        <div style="padding-top:10px;">
        <label for="nh_bk_nhan_timkiem" style="padding-left:30px;">
          <fmt:message key="DTSoatNoiBoDen.page.form.timkiem.nhkb_nhan"/>
        </label>        
      </div> 
        </td>
        <td>
        <div id="nh_kb_nhan_timkiem" style="padding-top:10px;">           
        <input onKeyPress="return numbersonly(this,event,true)" onkeydown="if(event.keyCode==13) event.keyCode=9;"  type="text" name="nh_kb_nhan_tk" id="nh_kb_nhan_tk"
               class=" text ui-widget-content ui-corner-all"/>
        </div>
        </td>
      </tr>
       <tr id="so_dts_timkiem" style="padding-top:10px;">
        <td align="right">
          <div id="so_dts_timkiem" style="padding-top:10px;">
            <label for="so_dts_timkiem" style="padding-left:40px;">
              <fmt:message key="DTSoatNoiBoDen.page.form.timkiem.sodien_trasoat"/>
            </label>
         </div>
        </td>
        <td>
        <div id="so_dts_timkiem" style="padding-top:10px;">
           <input onKeyPress="return numbersonly(this,event,true) "  onkeydown="if(event.keyCode==13) event.keyCode=9;"  type="text" name="so_dts_tk" id="so_dts_tk"
             class=" text ui-widget-content ui-corner-all"/>
        </div>
        </td>
      </tr>
    </table>
</div>
<div id="dialog-confirm"
     title='<fmt:message key="DTSoatNoiBoDen.page.title.dialog_confirm"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
