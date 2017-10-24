<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>


<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.multiselect.filter.css" />
<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/prettify.css"/>

<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery.multiselect.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/jquery.multiselect.filter.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/widget/prettify.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.ui.timepicker.js"  ></script>

<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/COTNHDiKB.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.COTResource"/>

<script type="text/javascript">
    jQuery.noConflict();
    var role='<%=request.getAttribute("chucdanh")%>';
    var obj = jQuery.parseJSON('<%=request.getAttribute("lstKBHuyen")%>');
  //************************************ LOAD PAGE **********************************

     jQuery(document).ready(function()
      {
      jQuery("#gio_cot_moi_kb").timepicker();
      jQuery('#gio_cot_moi').timepicker();
      var contexRoot='<%=AppConstants.NNT_APP_CONTEXT_ROOT%>',            
              cot_id=jQuery("#id"),
              id=jQuery("#cot_id"),
              ma_nh =  jQuery("#ma_nh"),
    			 		loai_cot =  jQuery("#loai_cot"),
              ma_kb_tinh =  jQuery("#ma_kb_tinh"),
              nguoi_lap=jQuery("#nguoi_lap"),
              ngay_lap=jQuery("#ngay_lap"),
              ngay_cot=jQuery("#ngay_cot"),
							dialog_message = jQuery("#dialog-message"),
							focusField = jQuery("#focusField"),
              ma_kb_huyen=jQuery("#ma_kb_huyen"),
              dialog_confirm =  jQuery("#dialog-confirm" ),
              certSerialField=jQuery("#certSerial"),
              contenDataField=jQuery("#contenData"),
              signatureField=jQuery("#signature"),
             	evenButtonBefore = jQuery("#evenButtonBefore"),
              rowSelected=jQuery("#rowSelected"),
							gio_cot_moi =jQuery("#gio_cot_moi" ),
              gio_cot_cu =jQuery("#gio_cot_cu" ),
              lydo_cot_kb=jQuery("#lydo_cot_kb"),
              lydo_daylai=jQuery("#lydo_daylai"),
              lydo_ko_dongy=jQuery("#lydo_ko_dongy"),
              trang_thai=jQuery("#trang_thai"),
              lydo_cot_nh=jQuery("#lydo_cot_nh"),
              frmCOT=jQuery("#frmCOT");
            	allFields = jQuery([]).add( ma_nh ).add( loai_cot ).add(ma_kb_huyen ).add(ma_kb_tinh).add(nguoi_lap).add(nguoi_lap) .add(gio_cot_cu )
              .add(gio_cot_moi ).add(lydo_cot_nh).add(trang_thai).add(lydo_ko_dongy).add(lydo_daylai);
               tableHighlightRow();

        jQuery("#dialog:ui-dialog").dialog( "destroy" );
         if('<%=request.getParameter("action")%>'=='null'){
//          disableButton();
          resetFormCOT();
           
         }else{
            disableButton();
            jQuery("#btn_xemLTT").hide();
            jQuery("#btn_timKiem").hide();
            lydo_cot.attr({disabled:true});
            row_dts_0.attr({'class':'ui-row-ltr ui-state-highlight'});
         }
       
              
        lydo_ko_dongy.keyup(function(){
          limitChars(lydo_ko_dongy.attr('id'),146);
        });
        lydo_daylai.keyup(function(){
          limitChars(lydo_daylai.attr('id'),146);
        });
        
        //dialog message
        dialog_message.dialog({
          autoOpen: false,
          resizable:false,
          height:200,
          width:380,
          modal: true,
          buttons: {
            Ok: function() {
              jQuery(this).dialog( "close" );
            }
         },
         close:function(e){
             var focusFieldVal=focusField.val(),
              action=evenButtonBefore.val();
              if(focusFieldVal!=null && focusFieldVal!='')
                jQuery("#"+focusFieldVal).focus();
              else if(action!=null && (action!='<%=AppConstants.ACTION_EXIT%>' && action!='')){
                defaultRowSelected(role);
              }
         }
        });
        //dialog confirm message	
						dialog_confirm.dialog({
							autoOpen: false,
              resizable: false,
							height:200,
							width:380,
							modal: true,
							buttons: {
								"Có": function() {
                  var action=evenButtonBefore.val(),
                      rowSelectedVal=rowSelected.val();
                
                   if(action!=null && (action!='<%=AppConstants.ACTION_EXIT%>' && action!='')){
                      updateExcuteCOT(action,rowSelectedVal);
                   }else if(action!=null && action=='<%=AppConstants.ACTION_EXIT%>'){
                     if(rowSelectedVal!=null && rowSelectedVal!='')
                      jQuery("#"+rowSelectedVal).attr( {'class' : 'ui-widget-content ui-row-ltr'});
                      if('<%=request.getParameter("action")%>'=='<%=AppConstants.ACTION_VIEW_DETAIL%>'){
                      frmCOT.attr({action:urlBack});
                       frmCOT.submit();
                    }else{
                     frmCOT.attr({action:'thoatView.do'});
                     frmCOT.submit();
                   }
                  }else{
                    if(rowSelectedVal!=null && rowSelectedVal!='')
                      jQuery("#"+rowSelectedVal).attr( {'class' : 'ui-widget-content ui-row-ltr'});
                    if('<%=request.getParameter("action")%>'=='null'){
                      resetFormCOT();
                      disableButton();
                    }else{
                     frmCOT.attr({action:'thoatView.do'});
                     frmCOT.submit();
                    }
                  }
                  jQuery(this).dialog("close");
                  
                },
								"Không": function() {
                   jQuery(this).dialog("close");
                }
							},
              close:function(e){
                 if(evenButtonBefore.val()=='<%=AppConstants.ACTION_REJECT%>')
                    lydo_daylai.focus();
                 else
                    rowSelectedFocus(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
              }
						});
				
        //**************************BUTTON Duyet CLICK: DONG Y NOI GIO********************************************
        jQuery("#btn_duyet").click(function() {
          var f = document.forms[0];
          try{
            var bValid = true;
            if(bValid && (lydo_cot_kb.val()==null || lydo_cot_kb.val()=='')){
//              dialog_message.html('<fmt:message key="cot_kbnn.page.message.thongtin_phanhoi_empty"/>');
//              dialog_message.dialog( "open" );
//              bValid=false;
//              focusField.val('lydo_cot_kb');
//              lydo_cot_kb.addClass("ui-state-error" );
            }
            if(bValid && lydo_cot_kb.val().length>146){
              dialog_message.html('<fmt:message key="cot_kbnn.page.message.thongtin_phanhoi.greater_than_146"/>');
              dialog_message.dialog( "open" );
              bValid=false;
              focusField.val('lydo_cot_kb');
              lydo_cot_kb.addClass("ui-state-error" );
            }
//            if("Y"==strChkKy){
//              if(bValid && !ky()){
//                alert("Ký không thành công");
//                bValid= false;
//              }
//            }
            if(bValid){
              evenButtonBefore.val('<%=AppConstants.ACTION_APPROVED%>');
              if(selectedRowBeforeClickButton()){
                var r = confirm('Bạn có chắc chắn đồng ý?');
                if(r){
                  f.action = 'duyetNoiGioTuNganHang.do?dong_y=Y&trang_thai=06';
                  f.submit()
                }
              }
            }
          }catch(e){
            alert(e.message);
          }
        });
        //**************************BUTTON CLICK:KO DONG Y NOI GIO********************************************
        jQuery("#btn_khongdongy").click(function() { 
          var f = document.forms[0];
          try{
            var bValid = true;
            if(bValid && (lydo_cot_kb.val()==null || lydo_cot_kb.val()=='')){
//              dialog_message.html('<fmt:message key="cot_kbnn.page.message.thongtin_phanhoi_empty"/>');
//              dialog_message.dialog( "open" );
              alert("Cần nhập lý do không đồng ý.");
              bValid=false;
              focusField.val('lydo_cot_kb');
              lydo_cot_kb.addClass("ui-state-error" );
            }
            if(bValid && lydo_cot_kb.val().length>146){
//              dialog_message.html('<fmt:message key="cot_kbnn.page.message.thongtin_phanhoi.greater_than_146"/>');
//              dialog_message.dialog( "open" );
              alert("Cần nhập lý do không đồng ý hợp lệ (<146 ký tự).");
              bValid=false;
              focusField.val('lydo_cot_kb');
              lydo_cot_kb.addClass("ui-state-error" );
            }
            
            if(bValid){
              evenButtonBefore.val('<%=AppConstants.ACTION_APPROVED%>');
              if(selectedRowBeforeClickButton()){
                var r = confirm('Bạn có chắc chắn không đồng ý ?');
                if(r){
                  f.action = 'duyetNoiGioTuNganHang.do?dong_y=N&trang_thai=07';
                  f.submit()
                }
              }
            }
          }catch(e){
            alert(e.message);
          }
        });
        
         //**************************BUTTON Day lai CLICK********************************************
        jQuery("#btn_dayLai").click(function() {
            evenButtonBefore.val('<%=AppConstants.ACTION_REJECT%>');
            lydo_daylai.removeClass('ui-state-error');
            if(selectedRowBeforeClickButton()){
                var bValid=true;
                if(lydo_daylai.val()==null || lydo_daylai.val()==''){
                  dialog_message.html('<fmt:message key="cot_kbnn.page.message.lydo_ktt_day_lai_empty"/>');
                  dialog_message.dialog( "open" );
                  bValid=false;
                  focusField.val('lydo_daylai');
                  lydo_daylai.addClass( "ui-state-error" );
                }
                if(bValid && lydo_daylai.val().length>146){
                  dialog_message.html('<fmt:message key="cot_kbnn.page.message.lydo_day_lai_greater_than_146"/>');
                  dialog_message.dialog( "open" );
                  bValid=false;
                  focusField.val('lydo_daylai');
                  lydo_daylai.addClass( "ui-state-error" );
               } 
                if(bValid){
                 dialog_confirm.html('<fmt:message key="cot_kbnn.page.message_confirm.day_lai_cot"/>');
                 dialog_confirm.dialog("open");
                }
              }
        });
        
        //**************************BUTTON Thoat CLICK********************************************
         jQuery("#btn_thoat").click(function() {
            if(evenButtonBefore.val()!=null && evenButtonBefore.val()=='<%=AppConstants.ACTION_EDIT%>'){
                fillDataDTS(cot_field.val(),rowSelected.val(),role,true);
                enableButtonCOT(role,trang_thai.val());
                evenButtonBefore.val("");
                rowSelectedFocus(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
            }else{
              evenButtonBefore.val('<%=AppConstants.ACTION_EXIT%>');
              if('<%=request.getParameter("action")%>'=='<%=AppConstants.ACTION_VIEW_DETAIL%>'){
                 frmCOT.attr({action:urlBack});
                 frmCOT.submit();
              }else{
                jQuery("#dialog-confirm").html('<fmt:message key="cot_kbnn.page.message_confirm.thoat"/>');
                jQuery("#dialog-confirm").dialog("open");
              }
            }
         });
          //**************************BUTTON btn_chuyenLD CLICK********************************************
         jQuery("#btn_dongy").click(function() {
              rowSelectedVal=rowSelected.val();
              evenButtonBefore.val('<%=AppConstants.ACTION_YES%>');
              focusField.val('');
              updateExcuteCOT("<%=AppConstants.ACTION_YES%>",rowSelectedVal);
              
         });
        //**************************BUTTON btn_chuyenLD CLICK********************************************
//         jQuery("#btn_khongdongy").click(function() {
//              var bValid =true;
//               allFields.removeClass('ui-state-error');
//              if(bValid && (lydo_ko_dongy.val()==null || lydo_ko_dongy.val()=='')){
//                jQuery('#lydo_ko_dongy').attr({disabled:false});
//                dialog_message.html('<fmt:message key="cot_kbnn.page.message.lydo_ko_dongy_empty"/>');
//                dialog_message.dialog( "open" );
//                bValid=false;
//                focusField.val('lydo_ko_dongy');
//                lydo_ko_dongy.addClass("ui-state-error" );
//             
//              }
//              if(bValid && lydo_ko_dongy.val().length>146){
//                dialog_message.html('<fmt:message key="cot_kbnn.page.message.lydo_ko_dongy.greater_than_146"/>');
//                dialog_message.dialog( "open" );
//                bValid=false;
//                focusField.val('lydo_ko_dongy');
//                lydo_ko_dongy.addClass("ui-state-error" );
//             
//              }
//              if(bValid){
//                rowSelectedVal=rowSelected.val();
//                evenButtonBefore.val('<%=AppConstants.ACTION_NO%>');
//                focusField.val('');
//                updateExcuteCOT("<%=AppConstants.ACTION_NO%>",rowSelectedVal)
//              }
//         });
        
        //********************** EXCUTE UPDATE - DELETE ***********************************  
        /*
         * action update excute
         */
       
        function updateExcuteCOT(action,rowSelectedVal){
          var certSerialFieldValue,contenDataFieldValue,signatureFieldValue;
           if (action=='<%=AppConstants.ACTION_APPROVED%>'){
             certSerialFieldValue=certSerialField.val();            
             signatureFieldValue=signatureField.val();
             contenDataFieldValue=jQuery("#noi_dung_ky").val();
           }
           jQuery.ajax({
             type: "POST",
             url: "updateCOTNHExc.do",	
             data: {"action"      :action,
                    "ma_nh"       :ma_nh.val(),
                    "loai_cot"    :loai_cot.val(),
                    "ma_kb_huyen" :ma_kb_huyen.val(),
                    "ma_kb_tinh"  :ma_kb_tinh.val(),
                    "nguoi_lap"   :nguoi_lap.val(),
                    "f_ngay_lap"  :ngay_lap.val(),
                    "f_ngay_cot"  :ngay_cot.val(),
                    "gio_cot_cu"  :gio_cot_cu.val(),
                    "gio_cot_moi" :gio_cot_moi.val(),
                    "lydo_cot"    :lydo_cot_kb.val(),
                    "lydo_ko_dongy":lydo_ko_dongy.val(),
                    "lydo_daylai":lydo_daylai.val(),
                    "trang_thai" : trang_thai.val(),
                    "signature" : signatureFieldValue,
                    "certSerial" : certSerialFieldValue,
                    "noi_dung_ky" : contenDataFieldValue,
                    "id" : cot_id.val(),
                    "cot_id":id.val(),
                    "nd" : Math.random() * 100000},
             dataType:'json',
             success: function(data,textstatus){
                 if(textstatus!=null && textstatus=='<%=AppConstants.SUCCESS%>'){
                    if(data.ERROR ==undefined || (data.ERROR=='undefined' || data.ERROR=='')){
                      fillDataTableCOT(data,contexRoot,role);
                      if((rowSelectedVal!=null && rowSelectedVal!='')  && data!='')
                        jQuery("#"+rowSelectedVal).attr({'class':'ui-widget-content ui-row-ltr'});
                      if(action=='<%=AppConstants.ACTION_YES%>')
                         dialog_message.html('<fmt:message key="cot_kbnn.page.message.dongy_thanh_cong"/>');
                      else if(action=='<%=AppConstants.ACTION_NO%>')
                         dialog_message.html('<fmt:message key="cot_kbnn.page.message.khongdongy_thanh_cong"/>');
                      else if (action=='<%=AppConstants.ACTION_APPROVED%>')
                        dialog_message.html('<fmt:message key="cot_kbnn.page.message.duyet_thanh_cong"/>');
                      else if (action=='<%=AppConstants.ACTION_REJECT%>')
                        dialog_message.html('<fmt:message key="cot_kbnn.page.message.day_lai_thanh_cong"/>');
                     disableButton();
                     resetFormCOT();
                    }else if(data.ERROR!=null && data.ERROR=='TTSP-ERROR-0001'){
                      dialog_message.html('<fmt:message key="cot_kbnn.page.message.trang_thai_thay_doi"/>');
                    }else if(data.ERROR!=null && data.ERROR=='TTSP-ERROR-0002'){
                      dialog_message.html('<fmt:message key="cot_kbnn.error.ttsp_error_0002"/>');
                    }else if(data.ERROR!=null && data.ERROR=='TTSP-ERROR-0003'){
                      dialog_message.html('<fmt:message key="cot_kbnn.error.ttsp_error_0003"/>');
                    }else if(data.ERROR!=null && data.ERROR=='<%=AppConstants.FAILURE%>'){
                      dialog_message.html('<fmt:message key="cot_kbnn.error.ttsp_error_0004"/>');
                    }
                    dialog_message.dialog( "open" );
                 }
                
              },
              error:function(textstatus){
                dialog_message.html(textstatus);
                dialog_message.dialog( "open" );
             }
         });
        
       }
      if('<%=request.getParameter("action")%>'=='null')
        defaultRowSelected(role);
      else
        jQuery("#row_dts_0").removeAttr("onclick");
	
      
    });
   
    function ky(){
    	try {
            var cert = jQuery("#eSign")[0];
            cert.InitCert();                   
            var serial = cert.Serial;
            jQuery("#certSerial").val(serial);
            var noi_dung_ky=null;
            if(jQuery('#trang_thai').val()=='07')
              noi_dung_ky = jQuery("#lydo_ko_dongy").val();
            else
                noi_dung_ky = jQuery("#lydo_cot_kb").val();
            var sign = cert.Sign(noi_dung_ky);
            jQuery("#signature").val(sign);
            return true;
        }
        catch (e) {
            alert(e.description);
            return false;
        }
    }
     
    </script>
<div id="body">
    <table border=0 cellSpacing=0 cellPadding=0 width="100%" align=center>
        <tbody>
            <tr>
                <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
                <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                    <span class=title2><fmt:message key="cot_kbnn.page.title_nh"/></span>
                </td>
                <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
                <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
                <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr>
        </tbody>
    </table> 
    <table class="tableBound">
        <tr>
            <td>
                <table width="99%">
                    <tbody>
                        <tr>
                            <td>
                                <font color="red">
                                    <html:errors/>
                                </font> 
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table class=bordertableChungTu cellSpacing=0 cellPadding=0 width="100%">
                    <tbody>
                        <tr>
                            <td vAlign=top>
                                <div class="clearfix">
                                    <fieldset class="fieldsetCSS">
                                        <legend style="vertical-align:middle">
                                            <fmt:message key="cot_kbnn.page.title_cot_yc_noigio"/>
                                        </legend>
                                        <div class="sodientrasoattable">
                                            <div>
                                                <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                                                    <thead>
                                                        <tr>
                                                            <th class="ui-state-default ui-th-column" width="62%;">
                                                                <fmt:message key="cot_kbnn.page.lable.so_cot"/>
                                                            </th>
                                                            <th height="20" width="28%" class="ui-state-default ui-th-column">
                                                                <fmt:message key="cot_kbnn.page.lable.tinh_trang"/>
                                                            </th> 
                                                            <th height="20" width="10%;" class="ui-state-default ui-th-column">
                                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                </table>
                                            </div>
                                            <div style="height:550px;" class="ui-jqgrid-bdiv ui-widget-content">
                                                <table class="data-grid" id="data-grid" style="BORDER-COLLAPSE: collapse;"  cellSpacing="0" cellPadding="0"  width="100%">
                                                    <tbody>
                                                        <logic:present name="listCOTDi">
                                                            <logic:iterate id="list_cot" name="listCOTDi" indexId="index" >
                                                                <tr style="width:100%;" class="ui-widget-content jqgrow ui-row-ltr"
                                                                    id="row_dts_<bean:write name="index"/>"                                                   
                                                                    ondblclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');" 
                                                                    onclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');fillDataDTS('<bean:write name="list_cot" property="id"/>','row_dts_<bean:write name="index"/>','<%=request.getAttribute("chucdanh")%>',true);">
                                                                    <td width="40%;"  align="center" id="td_dts_<bean:write name="index"/>">
                                                                        <input tabindex="100" name="row_item" id="<bean:write name="index"/>" type="text" class="ui-state-highlight" style="border:0px;font-size:10px;float:left;" value="<bean:write name="list_cot" property="id"/>" onkeydown="arrowUpDown(event)" readonly="true"/>
                                                                    </td>
                                                                    <td width="28%;" align="center" >
                                                                        <logic:equal value="05" name="list_cot" property="trang_thai">
                                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" title="Chờ trả lời"/>
                                                                        </logic:equal>
                                                                        <logic:equal value="06" name="list_cot" property="trang_thai">
                                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" title="Đồng ý"/>
                                                                        </logic:equal>
                                                                        <logic:equal value="07" name="list_cot" property="trang_thai">
                                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg" title="Không đồng ý"/>
                                                                        </logic:equal>
                                                                    </td>
                                                                </tr>
                                                            </logic:iterate>
                                                            <logic:empty name="list_cot" >
                                                                <tr>
                                                                    <td colspan="5" align="center">
                                                                        <span style="color:red;"><fmt:message key="cot_kbnn.error.not_found"/></span>
                                                                    </td>
                                                                </tr>
                                                            </logic:empty>
                                                        </logic:present>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div>
                                                <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                                                    <thead>
                                                        <tr>
                                                            <td class="ui-state-default ui-th-column" title="Refresh" id="refresh">
                                                                <!--<div class="ui-pg-div" style="float:left;"><span class="ui-icon ui-icon-refresh" onclick="refreshGrid('<%=request.getParameter("id")%>','<%=AppConstants.NNT_APP_CONTEXT_ROOT%>','<%=request.getAttribute("chucdanh")%>','<%=request.getParameter("action")%>');" style="cursor:pointer;"></span></div>
                                                                <div style="float:RIGHT;FONT-WEIGHT:NORMAL" class="ui-pg-div" id="tong_ban_ghi">
                                                                    <fmt:message key="cot_kbnn.page.lable.tong_ban_ghi">
                                                                        <fmt:param value="<%=(((java.util.Collection)request.getAttribute(\"listCOTDi\")).size())%>" />
                                                                    </fmt:message>
                                                                </div>-->
                                                            </td>
                                                        </tr>
                                                    </thead>
                                                </table>
                                                <div style="padding-top:10px;float: left;"><fmt:message key="cot_kbnn.page.lable.trang_thai"/>: <span id="mo_ta_trang_thai" style="font-weight:bold;"><%=request.getAttribute("mo_ta_trang_thai")%></span></div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </div>
                            </td>
                            <td width="85%" valign="top">
                                <input type="hidden" id="rowSelected"/>
                                <input type="hidden" id="evenButtonBefore"/>
                                <input type="hidden" id="focusField"/>
                                <input type="hidden" id="signature"/>
                                <input type="hidden" id="certSerial"/>
                                <input type="hidden" id="noi_dung_ky"/>
                                <input type="hidden" id="contenData"/>
                                <input type="hidden" id="nhkb_nhan_ltt"/>
                                <html:form styleId="frmCOT" action="/CutOffTimeKBView.do">
                                    <fieldset>
                                        <legend style="vertical-align:middle">
                                            <fmt:message key="cot_kbnn.fieldset.title.thong_tin_yc_nh"/>
                                        </legend>
                                        <div style="padding:10px 5px 10px 5px;">
                                            <!--<html:hidden property="search_dts" value="true"/>-->
                                            <html:hidden property="id" styleId="id"/>
                                            <html:hidden property="cot_id" styleId="cot_id"/>
                                            <html:hidden property="trang_thai" styleId="trang_thai"/>
                                            <table style="BORDER-COLLAPSE: collapse;"  border="1" cellSpacing=0 borderColor=#b0c4de cellPadding=0 width="99%">
                                                <tbody>
                                                    <tr>
                                                        <td align="right" width="15%">
                                                            <label for="ngan_hang"><fmt:message key="cot_kbnn.page.lable.ngan_hang" /></label>
                                                        </td>
                                                        <td align="left" class="box_common_content" width="32%">
                                                            <html:select property="ma_nh" styleId="ma_nh" styleClass="fieldText" style="width:160px;" onmouseover="Tip(this.value)" onmouseout="UnTip()" disabled="true">
                                                                <html:option value="">-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                                                                <html:optionsCollection name="colDMNH" value="ma_nh" label="ten_nh"/>
                                                            </html:select>
                                                        </td>
                                                        <td align="right" width="16%" >
                                                            <label for="loai_cot"><fmt:message key="cot_kbnn.page.lable.loai_cot"/></label>
                                                        </td>
                                                        <td align="left">
                                                            <html:select property="loai_cot" styleId="loai_cot" styleClass="fieldText" style="width:160px;" onmouseover="Tip(this.value)" onmouseout="UnTip()" disabled="true">
                                                                <html:option value="00" >To&#224;n h&#7879; th&#7889;ng</html:option>
                                                                <html:option value="01">T&#7915;ng &#273;&#7883;a ph&#432;&#417;ng</html:option>
                                                                <html:option value="02">N&#7899;i gi&#7901; truy&#7873;n nh&#7853;n</html:option>
                                                            </html:select>
                                                        </td>                   
                                                    </tr>
                                                    <tr>
                                                        <td align=right>
                                                            <label for="nguoi_lap"><fmt:message key="cot_kbnn.page.lable.nguoi_lap"/></label>
                                                        </td>
                                                        <td align="left">
                                                            <html:text property="ten_nguoi_lap"  styleId="ten_nguoi_lap" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()"  readonly="true" />
                                                        </td>
                                                        <td align=right>
                                                            <label for="ngay_lap"><fmt:message key="cot_kbnn.page.lable.ngay_lap"/></label>
                                                        </td>
                                                        <td align="left">
                                                            <html:text property="f_ngay_lap" styleId="ngay_lap" styleClass="fieldTextCode"   onmouseover="Tip(this.value)" onmouseout="UnTip()"  readonly="true" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align=right>
                                                            <label for="nguoi_ks"><fmt:message key="cot_kbnn.page.lable.nguoi_kiem_soat"/></label>
                                                        </td>
                                                        <td align="left">
                                                            <html:text property="ten_nguoi_ks" styleId="ten_nguoi_ks" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()"  readonly="true"/>
                                                        </td>
                                                        <td align=right>
                                                            <label for="ngay_kiem_soat"><fmt:message key="cot_kbnn.page.lable.ngay_kiem_soat"/></label>
                                                        </td>
                                                        <td align="left" >
                                                            <html:text property="f_ngay_ks" styleId="ngay_ks" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()"  readonly="true" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td align=right>
                                                            <label for="ngay"><fmt:message key="cot_kbnn.page.lable.ngay"/></label>
                                                        </td>
                                                        <td align="left" >
                                                            <html:text property="f_ngay_cot" styleId="ngay_cot" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                                        </td>
                                                        <td align=right>
                                                            <span id="span_label_gio_cot_cu"><label for="gio_cot_cu"><fmt:message key="cot_kbnn.page.lable.gio_cot_cu"/></label></span>
                                                            <span id="span_label_gio_tn_cu" style="display:none">Giờ truyền nhận cũ</span>
                                                        </td>
                                                        <td align="left">
                                                            <span id="span_gio_cot_cu"><html:text property="gio_cot_cu" styleId="gio_cot_cu" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" /></span>
                                                            <span id="span_tn_cu" style="display:none"><html:text property="tn_cu" styleId="tn_cu" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/></span>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="2"></td>
                                                       
                                                         <td align=right>
                                                            <span id="span_label_gio_cot_moi"><label for="gio_cot_moi"><fmt:message key="cot_kbnn.page.lable.gio_cot_moi"/></label></span>
                                                            <span id="span_label_gio_tn_moi" style="display:none">Giờ truyền nhận mới</span>
                                                         </td>
                                                         <td align="left" colspan="3">
                                                            <span id="span_gio_cot_moi"><html:text property="gio_cot_moi" styleId="gio_cot_moi" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/></span>
                                                            <span id="span_tn_moi" style="display:none"><html:text property="tn_moi" styleId="tn_moi" styleClass="fieldText"  onmouseover="Tip(this.value)" onmouseout="UnTip()" /></span>
                                                         </td>
                                                    </tr>
                                                    <tr>
                                                        <td align=right>
                                                            <label for="lydo_cot_nh"><fmt:message key="cot_kbnn.page.lable.lydo_cot"/></label>
                                                        </td>
                                                        <td colspan="3">
                                                            <html:textarea property="lydo_cot_nh"  onkeydown="if (event.keyCode == 13)
                                                                        event.keyCode = 9;" styleId="lydo_cot_nh" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()" ></html:textarea>
                                                            <span style="color:#FF0000" id="word_count_noi_dung">
                                                                <fmt:message key="cot_kbnn.page.lable.tong_so_ki_tu"/>
                                                                <span id="display_count_noi_dung">146</span></span>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </fieldset>
                                    <div>
                                        <fieldset>
                                            <legend style="vertical-align:middle">Th&#244;ng tin ph&#7843;n h&#7891;i t&#7915; KB
                                            </legend>
                                            <table style="BORDER-COLLAPSE: collapse;"  border="1" cellSpacing=0 borderColor=#b0c4de cellPadding=0 width="99%">
                                                <!--<tr id="cbtttt">
                                                    <td align=right width="15%">
                                                        <label for="lydo_ko_dongy"><fmt:message key="cot_kbnn.page.lable.lydo_khongdongy"/></label>
                                                    </td>
                                                    <td  colspan="3">
                                                        <html:textarea property="lydo_ko_dongy"  styleId="lydo_ko_dongy" onkeydown="if (event.keyCode == 13)
                                                                    event.keyCode = 9;"  cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()" ></html:textarea>
                                                        <span style="color:#FF0000" id="word_count_noi_dung">
                                                            <fmt:message key="cot_kbnn.page.lable.tong_so_ki_tu"/>
                                                            <span id="display_count_noi_dung">146</span></span>
                                                    </td>
                                                </tr>-->
                                                <tr id="cbpttttt">
                                                    <td align=right width="20%">Người kiểm soát&nbsp;</td>
                                                    <td align="left" width="50%">
                                                        NKS
                                                    </td>
                                                    <td align=right>
                                                        <label for="gio_cot_moi"><fmt:message key="cot_kbnn.page.lable.gio_cot_moi"/></label>
                                                    </td>
                                                    <td align="left">
                                                        <html:text property="gio_cot_moi" styleId="gio_cot_moi_kb" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                                                    </td>
                                                </tr>
                                                <tr id="cbpttttt1">
                                                    <td align="right" width="20%">
                                                        <label for="lydo_cot"><fmt:message key="cot_kbnn.page.lable.thongtin_phanhoi"/></label>
                                                    </td>
                                                    <td width="80%" colspan="3">
                                                        <html:textarea property="lydo_cot"  styleId="lydo_cot_kb" onkeydown="if (event.keyCode == 13)
                                                                    event.keyCode = 9;"  cols="3" rows="5" style="width:99.5%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()" ></html:textarea>
                                                        <span style="color:#FF0000" id="word_count_noi_dung">
                                                            <fmt:message key="cot_kbnn.page.lable.tong_so_ki_tu"/>
                                                            <span id="display_count_noi_dung">146</span></span>
                                                    </td>
                                                </tr>
                                                <!--<tr>
                                                    <td align="right">
                                                        <label for="lydo_daylai"><fmt:message key="cot_kbnn.page.lable.lydo_daylai"/></label>
                                                    </td>
                                                    <td  colspan="3">
                                                        <html:textarea property="lydo_daylai"  styleId="lydo_daylai" onkeydown="if (event.keyCode == 13)
                                                                    event.keyCode = 9;"  cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()" ></html:textarea>
                                                        <span style="color:#FF0000" id="word_count_noi_dung">
                                                            <fmt:message key="cot_kbnn.page.lable.tong_so_ki_tu"/>
                                                            <span id="display_count_noi_dung">146</span></span>
                                                    </td>
                                                </tr>-->
                                            </table>
                                        </fieldset>
                                    </div>
                                    <fieldset>
                                        <legend style="vertical-align:middle">Thông tin chi tiết kho bạc tỉnh</legend>
                                        <div style="padding:5px 5px 5px 5px;height: 254px;overflow: scroll;">
                                            <table style="BORDER-COLLAPSE: collapse;" border="1" cellspacing="0" bordercolor="#999999" width="100%" id="tableKBHuyen">
                                                <thead>
                                                    <tr>
                                                        <th class="promptText" bgcolor="#f0f0f0">
                                                            <div align="center">Mã KB</div>
                                                        </th>
                                                        <th class="promptText" bgcolor="#f0f0f0">
                                                            <div align="center">Tên KB</div>
                                                        </th>
                                                        <th class="promptText" bgcolor="#f0f0f0">
                                                            <div align="center">Mã NH</div>
                                                        </th>
                                                        <th class="promptText" bgcolor="#f0f0f0">
                                                            <div align="center">Giờ COT Cũ</div>
                                                        </th>
                                                        <th class="promptText" bgcolor="#f0f0f0">
                                                            <div align="center">Giờ COT Mới</div>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody class="navigateable focused" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1" style="font-size:1em">
                                                </tbody>
                                            </table>
                                            <html:hidden property="dong_y" styleId="dong_y"/>
                                            
                                        </div>
                                    </fieldset>
                                </html:form>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div style="float:right;">                                   
                                    
                                    <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%"                >
                                        <tr align="right">
                                            <td>
                                                <span id="duyet"> 
                                                    <button id=btn_duyet tabindex="112" accessKey=D class=buttonCommon>
                                                        Đồng ý
                                                    </button>
                                                </span>
                                                <span id="sua">
                                                    <button tabindex="112" id=btn_khongdongy accessKey=K>
                                                        Không đồng ý
                                                    </button>
                                                </span>
                                                <span id="thoat"> 
                                                    <button id="btn_thoat" tabindex="112" accessKey=o  class=buttonCommon>
                                                        <fmt:message key="cot_kbnn.page.button.exit"/>
                                                    </button>
                                                </span>
                                                <span>
                                                    <input style="WIDTH: 1px; HEIGHT: 1px" type=hidden name=thebottom/>
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
    </table>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>