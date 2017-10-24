
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
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

<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/COTKBDiNH.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.COTResource"/>

 <script type="text/javascript">
    jQuery.noConflict();
    var role='<%=request.getAttribute("chucdanh")%>';
    var obj = jQuery.parseJSON('<%=request.getAttribute("lstKBHuyen")%>');
  //************************************ LOAD PAGE **********************************
     
     jQuery(document).ready(function()
      {
      jQuery('#gio_cot_moi').timepicker();
      var contexRoot='<%=AppConstants.NNT_APP_CONTEXT_ROOT%>',            
              cot_id=jQuery("#id"),
              maxTime=jQuery("#maxTime"),
              ma_nh =  jQuery("#ma_nh"),
    			 		loai_cot =  jQuery("#loai_cot"),
              ma_kb_tinh =  jQuery("#ma_kb_tinh"),
              nguoi_lap=jQuery("#nguoi_lap"),
              ngay_lap=jQuery("#ngay_lap"),
              ngay_cot=jQuery("#ngay_cot"),
							dialog_message = jQuery("#dialog-message"),
							focusField = jQuery("#focusField"),
              ma_kb_huyen=jQuery("#kb_id"),
              dialog_confirm =  jQuery("#dialog-confirm" ),
              certSerialField=jQuery("#certSerial"),
              contenDataField=jQuery("#contenData"),
              signatureField=jQuery("#signature"),
              evenButtonBefore = jQuery("#evenButtonBefore"),
              rowSelected=jQuery("#rowSelected"),
							gio_cot_moi =jQuery("#gio_cot_moi" ),
              gio_cot_cu =jQuery("#gio_cot_cu" ),
              lydo_cot=jQuery("#lydo_cot"),
              lydo_daylai=jQuery("#lydo_daylai"),
              trang_thai=jQuery("#trang_thai"),
              frmCOT=jQuery("#frmCOT");
            	allFields = jQuery([]).add( ma_nh ).add( loai_cot ).add(ma_kb_huyen ).add(ma_kb_tinh).add(nguoi_lap).add(nguoi_lap) .add(gio_cot_cu )
              .add(gio_cot_moi ).add(lydo_cot).add(trang_thai).add(focusField);
               tableHighlightRow();
//        if(ltt_cot_field.val()=='' && ltt_cot_field.val()=='undefined'){
//          jQuery("#in").hide();
//        }
        //ManhNV-20150424
        if(loai_cot.val() == '02'){          
            jQuery("#span_tn_cu").show();
            jQuery("#span_tn_moi").show();
            jQuery("#span_gio_cot_moi").hide();
            jQuery("#span_gio_cot_cu").hide();
        }else{
            jQuery("#span_tn_cu").hide();
            jQuery("#span_tn_moi").hide();
            jQuery("#span_gio_cot_moi").show();
            jQuery("#span_gio_cot_cu").show();    
        }
        //ManhNV-20150424
        
        jQuery("#dialog:ui-dialog").dialog( "destroy" );
         if('<%=request.getParameter("action")%>'=='null'){
          disableButton();
          resetFormCOT();

         }else{
            disableButton();
            jQuery("#btn_xemLTT").hide();
            jQuery("#btn_timKiem").hide();
            lydo_cot.attr({disabled:true});
            row_dts_0.attr({'class':'ui-row-ltr ui-state-highlight'});
         }
       
              
        lydo_cot.keyup(function(){
          limitChars(lydo_cot.attr('id'),146);
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
             var idFieldFocus=focusField.val(),
              action=evenButtonBefore.val();
              if(idFieldFocus!=null && idFieldFocus!='')
                jQuery("#"+idFieldFocus).focus();
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
				
          //**************************BUTTON Sua CLICK********************************************
        jQuery("#btn_sua").click(function() {
            evenButtonBefore.val('<%=AppConstants.ACTION_EDIT%>');
            if(selectedRowBeforeClickButton()){
              var trangthai=trang_thai.val();
              if(trangthai=='<%=AppConstants.TRANG_THAI_DAY_LAI%>' || 
                trangthai=='<%=AppConstants.TRANG_THAI_CHO_XULY%>' ){
                btnSuaClick();
              }else{
                dialog_message.html('<fmt:message key="cot_kbnn.page.message.not_edit_dts"/>');
                dialog_message.dialog( "open" );
              }
               
            }
        
          });
        jQuery("#btn_Themmoi").click(function() {
                    frmCOT.attr({action:'COTAdd.do'});
                     frmCOT.submit();
        });
        /*
         * List daanh sach kho bac huyen
         */
       /*
         * List daanh sach kho bac huyen
         */
      jQuery("#ma_kb_tinh").change(function (){
        var state=true, ma_kb_tinh=jQuery("#ma_kb_tinh").val();
            if(ma_kb_tinh==null || ma_kb_tinh==''){
              jQuery("#kb_id").html('');
              jQuery("#kb_id").attr({disabled:true});
            }else{
              state = !state;
              jQuery("#kb_id").multiselect(state ? 'disable' : 'enable');              
              if (ma_kb_tinh!=null){
               jQuery("#kb_id").html('');
               jQuery.each(ma_kb_tinh, function (i, value) {
                var optgrp=jQuery('<optgroup>');
                optgrp.attr('label',jQuery("#ma_kb_tinh option[value='"+value+"']").text());
                  obj.filter(function(item) {
                    if(item.id_cha === value){
                       var opt = jQuery('<option />', {value: item.ma_nh,text: item.ten}); 
                       opt.appendTo(optgrp);
                    }
                   });
                  optgrp.appendTo(jQuery("#kb_id") );
                });
                
                
              }
            }
            jQuery("#kb_id").multiselect('refresh'); 
        });

        jQuery("#kb_id").change(function (){
             var kbIdVal=jQuery("#kb_id").val();
            if(kbIdVal!=null && kbIdVal.length==1){
              jQuery.ajax({
                 type: "POST",
                 url: "danhsachkbhuyen.do",	
                 data: {"ma_kb_huyen":kbIdVal[0].toString(),
                        "nd" : Math.random() * 100000},
                 dataType:'json',
                 success: function(data,textstatus){
                     if(textstatus!=null && textstatus=='<%=AppConstants.SUCCESS%>'){
                      jQuery("#gio_cot_cu").val(data.cut_of_time);
                     }
                    },
                  error:function(xhr,status,error){
                    focus_field.val(status);
                    dialog_message.html(status+ xhr.responseText);
                    dialog_message.dialog( "open" );
                 }
             });    
            }else{
              jQuery("#gio_cot_cu").val('');
            }
        });
        //**************************BUTTON Ghi CLICK********************************************
        jQuery("#btn_ghi").click(function() {
            var bValid=true;
            var evenButtonBeforeVal=evenButtonBefore.val();
               allFields.removeClass('ui-state-error');
            if(evenButtonBeforeVal!=null && evenButtonBeforeVal=='<%=AppConstants.ACTION_EDIT%>'){
              if(ma_nh.val()==null || ma_nh.val()==''){
                dialog_message.html('<fmt:message key="cot_kbnn.page.message.ma_nh"/>');
                focusField.val('ma_nh');
                ma_nh.addClass( "ui-state-error" );
                bValid=false;
              }
              if(bValid && loai_cot.val()=='01' ){
                if(ma_kb_tinh==null || (ma_kb_tinh.val()!=null && ma_kb_tinh.val().toString()=='')){
                  dialog_message.html('<fmt:message key="cot_kbnn.page.message.ma_kb_tinh"/>');
                  focusField.val('ma_kb_tinh');
                  ma_kb_tinh.addClass( "ui-state-error" );
                  bValid=false;
                }
              }
              if(bValid && !ma_kb_tinh.val()=='' ){
                if(ma_kb_huyen==null || (ma_kb_huyen.val()!=null && ma_kb_huyen.val().toString()=='')){
                  dialog_message.html('<fmt:message key="cot_kbnn.page.message.ma_kb_huyen"/>');
                  focusField.val('kb_id');
                  ma_kb_huyen.addClass( "ui-state-error" );
                  bValid=false;
                }
              }
              //manhnv-20150424
              if(loai_cot.val()!='02' ){
                if(bValid && (gio_cot_moi.val()==null || gio_cot_moi.val()=='')){
                    dialog_message.html('<fmt:message key="cot_kbnn.page.message.gio_cot_moi"/>');
                    focusField.val('gio_cot_moi');
                    gio_cot_moi.addClass( "ui-state-error" );
                    bValid=false;
                }else if(bValid && (gio_cot_cu.val()!=null && gio_cot_cu.val()!='')){        
                   gio_cot_cu.timepicker();
                    if(gio_cot_cu.timepicker('getTimeAsDate')>=gio_cot_moi.timepicker('getTimeAsDate'))
                    {
                      dialog_message.html('Gi&#7901; COT m&#7899;i ph&#7843;i l&#7899;n h&#417;n Gi&#7901; COT c&#361;');
                      focusField.val('gio_cot_moi');
                      gio_cot_moi.addClass( "ui-state-error" );
                      bValid=false;
                    }
                    gio_cot_cu.timepicker('destroy');
                }
              }else{
                if(bValid && jQuery("#tn_moi").val()==''){
                  dialog_message.html('Cần nhập giờ truyền nhận mới');
                  focusField.val('tn_moi');
                  jQuery("#tn_moi").addClass( "ui-state-error" );
                  bValid=false;
                }
              }
              //manhnv-20150424
               if(bValid && (lydo_cot.val()==null || lydo_cot.val()=='')){
                dialog_message.html('<fmt:message key="cot_kbnn.page.message.lydo_cot_empty"/>');
                bValid=false;
                focusField.val('lydo_cot');
                lydo_cot.addClass("ui-state-error" );
             
              }
              if(bValid && lydo_cot.val().length>146){
                dialog_message.html('<fmt:message key="cot_kbnn.page.message.lydo_cot_greater_than_146"/>');
                bValid=false;
                focusField.val('lydo_cot');
                lydo_cot.addClass("ui-state-error" );
             
              }
              if(bValid){
               var action=evenButtonBefore.val(),
                      rowSelectedVal=rowSelected.val();                
               if(action!=null && (action!='<%=AppConstants.ACTION_EXIT%>' && action!=''))
                  focusField.val('');
                  updateExcuteCOT(action,rowSelectedVal);
                
              }else{
                dialog_message.dialog( "open" );
              }
              
            }
          });
         //**************************BUTTON Huy CLICK********************************************
        jQuery("#btn_huy").click(function() {
            evenButtonBefore.val('<%=AppConstants.ACTION_CANCEL%>');
            if(selectedRowBeforeClickButton()){
               var trangthai=trang_thai.val();
               if(trangthai=='<%=AppConstants.TRANG_THAI_CHO_XULY%>'|| trangthai=='<%=AppConstants.TRANG_THAI_DAY_LAI%>'){
                  dialog_confirm.html('<fmt:message key="cot_kbnn.page.message_confirm.huy_dts"/>');
                  dialog_confirm.dialog("open");
                }else{
                  dialog_message.html('<fmt:message key="cot_kbnn.page.message.not_cancel_dts"/>');
                  dialog_message.dialog( "open" );
                }
            }
        });
        //**************************BUTTON Duyet CLICK********************************************
        jQuery("#btn_duyet").click(function() {
          
            try{
                if("Y"==strChkKy){
                  if(!ky()){
                    alert("Ký không thành công");
                    return false;
                }
            }
            evenButtonBefore.val('<%=AppConstants.ACTION_APPROVED%>');
            if(selectedRowBeforeClickButton()){
              dialog_confirm.html('<fmt:message key="cot_kbnn.page.message_confirm.duyet_cot"/>');
              dialog_confirm.dialog("open");
            }
          }catch(e){
            dialog_message.html(e.message);
            dialog_message.dialog("open");
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
                  focusField.val('lydo_daylai');
                  lydo_daylai.addClass( "ui-state-error" );
                  bValid=false;
                  
                }
                if(bValid && lydo_daylai.val().length>146){
                  dialog_message.html('<fmt:message key="cot_kbnn.page.message.lydo_day_lai_greater_than_146"/>');
                  bValid=false;
                  focusField.val('lydo_daylai');
                  lydo_daylai.addClass( "ui-state-error" );
               } 
                if(bValid){
                 focusField.val('');
                 dialog_confirm.html('<fmt:message key="cot_kbnn.page.message_confirm.day_lai_cot"/>');
                 dialog_confirm.dialog("open");
                }else{                  
                  dialog_message.dialog("open");
                }
              }
        });
        
        //**************************BUTTON Thoat CLICK********************************************
         jQuery("#btn_thoat").click(function() {
            if(evenButtonBefore.val()!=null && evenButtonBefore.val()=='<%=AppConstants.ACTION_EDIT%>'){
                fillDataDTS(cot_id.val(),rowSelected.val(),role,true);
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
        
         //**************************BUTTON TRA CUU COT CLICK********************************************
        jQuery("#btn_tra_cuu_dts").click(function() {
           frmCOT.attr({'action':'tracuudts.do'});
           frmCOT.submit()
        });
        
        //********************** EXCUTE UPDATE - DELETE ***********************************  
        /*
         * action update excute
         */
       
        function updateExcuteCOT(action,rowSelectedVal){
          var certSerialFieldValue,contenDataFieldValue,signatureFieldValue;
           if (action=='<%=AppConstants.ACTION_APPROVED%>'){
             certSerialFieldValue=certSerialField.val();            
             signatureFieldValue=signatureField.val();
             contenDataFieldValue=jQuery("#lydo_cot").val();
           }
           jQuery.ajax({
             type: "POST",
             url: "updateCOTExc.do",	
             data: {"action" : action,
                    "ma_nh" : ma_nh.val(),
                    "loai_cot":loai_cot.val(),
                    "ma_kb_huyen" : ma_kb_huyen.val(),
                    "ma_kb_huyen_list" : jQuery("#ma_kb_huyen_list").val(),
                    "ma_kb_tinh":ma_kb_tinh.val(),
                    "nguoi_lap" : nguoi_lap.val(),
                    "f_ngay_lap" : ngay_lap.val(),
                    "f_ngay_cot" : ngay_cot.val(),
                    "gio_cot_cu" : gio_cot_cu.val(),
                    "gio_cot_moi" : gio_cot_moi.val(),
                    "lydo_cot":lydo_cot.val(),
                     "lydo_daylai":lydo_daylai.val(),
                    "trang_thai" : trang_thai.val(),
                    "signature" : signatureFieldValue,
                    "certSerial" : certSerialFieldValue,
                    "noi_dung_ky" : contenDataFieldValue,
                    "maxTime" : maxTime.val(),
                    "id" : cot_id.val(),
                    "nd" : Math.random() * 100000},
             dataType:'json',
             success: function(data,textstatus){
                 if(textstatus!=null && textstatus=='<%=AppConstants.SUCCESS%>'){
                    if(data.ERROR ==undefined || (data.ERROR=='undefined' || data.ERROR=='')){
                      fillDataTableCOT(data,contexRoot,role);
                      if((rowSelectedVal!=null && rowSelectedVal!='')  && data!='')
                        jQuery("#"+rowSelectedVal).attr({'class':'ui-widget-content ui-row-ltr'});
                      if(action=='<%=AppConstants.ACTION_EDIT%>')
                         dialog_message.html('<fmt:message key="cot_kbnn.page.message.sua_thanh_cong"/>');
                      else if(action=='<%=AppConstants.ACTION_CANCEL%>')
                         dialog_message.html('<fmt:message key="cot_kbnn.page.message.huy_thanh_cong"/>');
                      else if (action=='<%=AppConstants.ACTION_APPROVED%>')
                        dialog_message.html('<fmt:message key="cot_kbnn.page.message.duyet_thanh_cong"/>');
                      else if (action=='<%=AppConstants.ACTION_REJECT%>')
                        dialog_message.html('<fmt:message key="cot_kbnn.page.message.day_lai_thanh_cong"/>');
                         else if (action=='<%=AppConstants.ACTION_ACCEPT%>')
                        dialog_message.html('<fmt:message key="cot_kbnn.page.message.chuyen_thanh_cong"/>');
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
                    }else
                      dialog_message.html(data.ERROR);
                    dialog_message.dialog( "open" );
                 }
                
              },
              error:function(textstatus){
                dialog_message.html(textstatus);
                dialog_message.dialog( "open" );
             }
         });
        
       }
      if('<%=request.getParameter("action")%>'=='null'){
        defaultRowSelected(role);
            
      }else
        jQuery("#row_dts_0").removeAttr("onclick");
	
      
    });
    // In
    function formAction(){
      if(jQuery("#ltt_id").val()=='' || jQuery("#ltt_id").val()=='undefined'){
          alert('Không có bản ghi nào để thực hiện in!');
          return;
        }
      
      var f = document.forms[0];
      f.action = "dtsoatlttdiRpt.do";
      var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
      newDialog = window.open('', '_form', params);          
      f.target='_form';
      f.submit();
    } 
    function ky(){
    	try {
            var cert = jQuery("#eSign")[0];
            cert.InitCert();                   
            var serial = cert.Serial;
            jQuery("#certSerial").val(serial);        
            var noi_dung_ky = jQuery("#lydo_cot").val();
            var sign = cert.Sign(noi_dung_ky);
            jQuery("#signature").val(sign);
            return true;
        }
        catch (e) {
            alert(e.description);
            return false;
        }
    }
     function danhsachkbhuyen(ma_kb_tinh) {
      jQuery.ajax( {
          type : "POST", url : "kbhuyenlist.do", data :  {
              "kb_id" : ma_kb_tinh, "nd" : Math.random() * 100000
          },
          dataType : 'json', success : function (data, textstatus) {
              if (textstatus != null && textstatus == '<%=AppConstants.SUCCESS%>') {
                  var ma_kb_huyen = jQuery("#kb_id");
                  var options = ma_kb_huyen.attr('options');
                  jQuery("#kb_id").html('');
                  options[0] = new Option('--- Chọn KB huyện----', '')
                  jQuery.each(data, function (i, kbhuyen) {
                      options[i + 1] = new Option(kbhuyen.kb_huyen,kbhuyen.ma_nh);
                  });
                  if ('<%=request.getParameter("kb_id")%>' != null && '<%=request.getParameter("kb_id")%>' != '') {
                      jQuery("#kb_id").val('<%=request.getParameter("kb_id")%>')
                      jQuery("#kb_id").attr( {
                          disabled : false
                      });
                      
                  }
              }
          },
          error : function (xhr, status, error) {
              focusField.val(status);
              dialog_message.html(status + xhr.responseText);
              dialog_message.dialog("open");
          }
      });
  }
    </script>
    <div id="body">
        <table border=0 cellSpacing=0 cellPadding=0 width="100%"  align=center>
          <tbody>
            <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="cot_kbnn.page.title"/></span>
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
                                        <table class="data-grid" cellSpacing="0"
                                              cellPadding="0" width="100%">
                                          <thead>
                                            <tr>
                                              <th class="ui-state-default ui-th-column"
                                                   width="62%;">
                                               <fmt:message key="cot_kbnn.page.lable.so_cot"/>
                                              </th>
                                              <th height="20" width="28%"
                                                  class="ui-state-default ui-th-column">
                                               <fmt:message key="cot_kbnn.page.lable.tinh_trang"/>
                                              </th> 
                                              <th height="20" width="10%;"
                                                  class="ui-state-default ui-th-column">
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                              </th>
                                            </tr>
                                          </thead>
                                        </table>
                                      </div>
                                      <div style="height:550px;" class="ui-jqgrid-bdiv ui-widget-content">
                                        <div>
                                        <table class="data-grid" id="data-grid" style="BORDER-COLLAPSE: collapse;"  cellSpacing="0" cellPadding="0"  width="100%">
                                          <tbody>
                                            <logic:present name="listCOTDi">
                                              <logic:iterate id="list_cot" name="listCOTDi" indexId="index" >
                                              <tr style="width:100%;" class="ui-widget-content jqgrow ui-row-ltr"
                                                  id="row_dts_<bean:write name="index"/>"                                                   
                                                  ondblclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');" 
                                                  onclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');fillDataDTS('<bean:write name="list_cot" property="id"/>','row_dts_<bean:write name="index"/>','<%=request.getAttribute("chucdanh")%>',true);">
                                                <td width="40%;"  align="center" id="td_dts_<bean:write name="index"/>">
                                                  <input tabindex="100" name="row_item" id="<bean:write name="index"/>" 
                                                  type="text" class="ui-state-highlight" style="border:0px;font-size:10px;float:left;" value="<bean:write name="list_cot" property="id"/>" onkeydown="arrowUpDown(event)" readonly="true"/>
                                                </td>
                                                <td width="28%;" align="center" >
                                                  <logic:equal value="00" name="list_cot" property="trang_thai">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg" title="&#272;&#7849;y l&#7841;i"/>
                                                  </logic:equal>
                                                  <logic:equal value="02" name="list_cot" property="trang_thai">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" title="Ch&#7901; duy&#7879;t"/>
                                                  </logic:equal>
                                                  <logic:equal value="03" name="list_cot" property="trang_thai">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" title="&#272;&#227; duy&#7879;t"/>
                                                  </logic:equal>
                                                  <logic:equal value="04" name="list_cot" property="trang_thai">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif" title="&#272;&#227; h&#7911;y"/>
                                                  </logic:equal>
                                                  <logic:equal value="08" name="list_cot" property="trang_thai">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/dong_y.png" title="NH đồng ý"/>
                                                  </logic:equal>
                                                  <logic:equal value="09" name="list_cot" property="trang_thai">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete-icon.png" title="NH đồng ý - Không hợp lệ"/>
                                                  </logic:equal>
                                                  <logic:equal value="10" name="list_cot" property="trang_thai">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/Ko_dong_y.png" title="NH không đồng ý"/>
                                                  </logic:equal>
                                                  <logic:equal value="18" property="trang_thai" name="list_cot">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/send-success.jpg" title="Gửi NH thành công"/>
                                                  </logic:equal>
                                                  <logic:equal value="19" property="trang_thai" name="list_cot">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg" title="Gửi NH không thành công"/>
                                                  </logic:equal>
                                                 
                                                </td>
                                               
                                              </tr>
                                              </logic:iterate>
                                              <logic:empty name="list_cot" >
                                                <tr id="row_dts_0">
                                                  <td colspan="5" align="center">
                                                    <span style="color:red;"><fmt:message key="cot_kbnn.error.not_found"/></span>
                                                  </td>
                                                </tr>
                                              </logic:empty>
                                            </logic:present>
                                         </tbody>
                                        </table>
                                        </div>
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
                              <fieldset class="fieldsetCSS">
                                <legend style="vertical-align:middle">
                                  <fmt:message key="cot_kbnn.fieldset.title.thong_tin_chung"/>
                                </legend>
                                <div style="padding:10px 5px 10px 5px;">
                                <input type="hidden" id="rowSelected"/>
                                <input type="hidden" id="evenButtonBefore"/>
                                <input type="hidden" id="focusField"/>
                                <input type="hidden" id="signature"/>
                                <input type="hidden" id="certSerial"/>
                                <input type="hidden" id="noi_dung_ky"/>
                                <input type="hidden" id="contenData"/>
                                <input type="hidden" id="nhkb_nhan_ltt"/>
                                <input type="hidden" id="maxTime"/>
                                <html:form styleId="frmCOT" action="/CutOffTimeKBView.do">
                                 <%if(request.getAttribute("chucdanh").toString().indexOf(AppConstants.NSD_CBPT_PTTT) >=0){%>
                                   <object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
                                  <%}%>
                                  <!--<html:hidden property="search_dts" value="true"/>-->
                                  <html:hidden property="id" styleId="id"/>
                                  <html:hidden property="trang_thai" styleId="trang_thai"/>
                                  <table style="BORDER-COLLAPSE: collapse;"  border="1" cellSpacing=0 borderColor=#b0c4de cellPadding=0 width="99%">
                                          <tbody>
                                            <tr>
                                              <td align="right" width="15%">
                                                <label for="ngan_hang"><fmt:message key="cot_kbnn.page.lable.ngan_hang" /></label>
                                              </td>
                                              <td  align="left" class="box_common_content" width="32%">
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
                                                <html:option value="00">To&#224;n h&#7879; th&#7889;ng</html:option>
                                                <html:option value="01">T&#7915;ng &#273;&#7883;a ph&#432;&#417;ng</html:option>
                                                <html:option value="02">Nới giờ truyền nhận toàn hệ thống</html:option>
                                              </html:select>
                                              </td>                   
                                            </tr>
                                            <tr>
                                               <td align=right >
                                                <label for="kb_tinh"><fmt:message key="cot_kbnn.page.lable.kb_tinh"/></label>
                                              </td>
                                              <td width="10%">
                                               <html:select multiple="multiple" property="ma_kb_tinh" styleId="ma_kb_tinh" styleClass="fieldText" style="width:160px;" onmouseover="Tip(this.value)" onmouseout="UnTip()">
                                                  <html:optionsCollection name="lstKBTinh" value="id_cha" label="ten"/>
                                              </html:select>
                                              </td>
                                              <td align=right >
                                                <label for="kb_huyen"><fmt:message key="cot_kbnn.page.lable.kb_huyen"/></label>
                                              </td>
                                              <td width="10%">
                                               <html:select multiple="multiple" property="ma_kb_huyen" styleId="kb_id" styleClass="fieldText" style="width:160px;" onmouseover="Tip(this.value)" onmouseout="UnTip()">
                                              </html:select>
                                              <html:hidden property="ma_kb_huyen_list" styleId="ma_kb_huyen_list"></html:hidden>
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
                                                <label for="gio_cot_cu"><fmt:message key="cot_kbnn.page.lable.gio_cot_cu"/></label>
                                              </td>
                                            
                                               <td align="left">
                                                <span id="span_gio_cot_cu"><html:text property="gio_cot_cu" styleId="gio_cot_cu" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" /></span>
                                                <span id="span_tn_cu"><html:text property="tn_cu" styleId="tn_cu" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/></span>
                                              </td>
                                            </tr>
                                            <tr>
                                             <td align=right>Trạng thái: </td>
                                             <td align="left">                                                                                               
                                                 <span id="mo_ta_trang_thai2" style="font-weight:bold; color:red"><%=request.getAttribute("mo_ta_trang_thai")%></span>
                                             
                                             </td>
                                             <td align=right>
                                                <label for="gio_cot_moi"><fmt:message key="cot_kbnn.page.lable.gio_cot_moi"/></label>
                                              </td>
                                              <td align="left" colspan="3">
                                                <span id="span_gio_cot_moi"><html:text property="gio_cot_moi" styleId="gio_cot_moi" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/></span>
                                                <span id="span_tn_moi"><html:text property="tn_moi" styleId="tn_moi" styleClass="fieldText"  onmouseover="Tip(this.value)" onmouseout="UnTip()" /></span>
                                              </td>
                                            </tr>
                                            <tr>
                                               <td align=right>
                                                <label for="lydo_cot"><fmt:message key="cot_kbnn.page.lable.lydo_cot"/></label>
                                              </td>
                                              <td  colspan="3">
                                                <html:textarea property="lydo_cot"  onkeydown="if(event.keyCode==13) event.keyCode=9;" styleId="lydo_cot" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()" ></html:textarea>
                                                <span style="color:#FF0000" id="word_count_noi_dung">
                                                <fmt:message key="cot_kbnn.page.lable.tong_so_ki_tu"/>
                                                <span id="display_count_noi_dung">146</span></span>
                                              </td>
                                            </tr>
                                             <tr>
                                               <td align=right>
                                                <label for="lydo_daylai"><fmt:message key="cot_kbnn.page.lable.lydo_daylai"/></label>
                                              </td>
                                              <td  colspan="3">
                                                <html:textarea property="lydo_daylai"  onkeydown="if(event.keyCode==13) event.keyCode=9;" styleId="lydo_daylai" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()" ></html:textarea>
                                                <span style="color:#FF0000" id="word_count_noi_dung">
                                                <fmt:message key="cot_kbnn.page.lable.tong_so_ki_tu"/>
                                                <span id="display_count_noi_dung">146</span></span>
                                              </td>
                                            </tr>
                                          </tbody>
                                        </table>
                                  </html:form>
                                  <div id="fieldset_ds_kb_noi_gio">
                                    <fieldset>
                                      <legend style="vertical-align:middle">
                                        Thông tin kho bạc tỉnh
                                      </legend>
                                      <div style="padding:5px 5px 5px 5px;height: 300px;overflow: scroll;">
                                      <table style="BORDER-COLLAPSE: collapse;" border="1" cellspacing="0" bordercolor="#999999" width="100%" id="tableKBHuyen">
                                          <thead>
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
                                          </thead>
                                          <tbody class="navigateable focused" cellspacing="0" cellpadding="1" bordercolor="#e1e1e1" style="font-size:1em">
                                          </tbody>
                                      </table>
                                      </div>
                                    </fieldset>
                                </div>
                              </div>
                            </fieldset>
                              </td>
                            </tr>
                           
                            <tr>
                              <td colspan="2">
                                <div style="float:right;">
                                  <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%"                >
                                     <tr align="right">
                                      <td>
                                         <span id="in"> 
                                            <button id="btn_Themmoi" tabindex="112" class=buttonCommon accessKey=T>
                                              <fmt:message key="cot_kbnn.page.button.themmoi"/>
                                            </button>
                                            </span>
                                           <span id="sua">
                                            <button tabindex="112" id=btn_sua accessKey=A>
                                              <fmt:message key="cot_kbnn.page.button.edit"/>
                                            </button>
                                            </span>
                                            <span id="them"> 
                                             <button id="btn_ghi"
                                             tabindex="112" accessKey=G class=buttonCommon>
                                              <fmt:message key="cot_kbnn.page.button.save"/>
                                            </button>
                                            </span>
                                            <span id="huy"> 
                                            <button id=btn_huy accessKey=H class=buttonCommon>
                                              <fmt:message key="cot_kbnn.page.button.cancel"/>
                                            </button>
                                            </span>
                                            <span id="duyet"> 
                                            <button id=btn_duyet tabindex="112" accessKey=D class=buttonCommon>
                                              <fmt:message key="cot_kbnn.page.button.approved"/>
                                            </button>
                                            </span>
                                            <span id="daylai"> 
                                            <button id=btn_dayLai tabindex="112" accessKey=L  class=buttonCommon>
                                              <fmt:message key="cot_kbnn.page.button.move_again"/>
                                            </button>
                                            </span>
                                           
                                          <!-- <span id="timKiem">           
                                              <button id="btn_timKiem" accessKey=I class=buttonCommon>
                                                <fmt:message key="cot_kbnn.page.button.find"/>
                                              </button>
                                            </span>-->
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
              </tbody>
            </table>
          </td>
        </tr>
      </table>        
      
    <div id="dialog-message" title="<fmt:message key="cot_kbnn.page.title.dialog_message"/>">
        <p>
          <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
          <span id="message"></span>
        </p>
    </div>
      
      </div>
      <div id="dialog-confirm" title="<fmt:message key="cot_kbnn.page.title.dialog_confirm"/>" >
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
          <span id="message_confirm"></span>
        </p>
      </div>
    <script type="text/javascript">
        jQuery("#ma_kb_tinh").multiselect({noneSelectedText: "---- Chọn KB tỉnh ----",
        selectedText:"Bạn đã chọn # KB tỉnh"}).multiselectfilter();
        jQuery("#kb_id").multiselect({noneSelectedText: "---- Chọn KB huyện ----",
        selectedText:"Bạn đã chọn # KB huyện"}).multiselectfilter();
      </script>
  <%@ include file="/includes/ttsp_bottom.inc"%>
