<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/xu.ly.dtsoat.hoi.NHTM.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.XuLyDTSoatHoiTuNHTM"/>

 <script type="text/javascript">
    jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
     var role='<%=request.getAttribute("chucdanh")%>';
     jQuery(document).ready(function()
      {
        //KHAI BAO BIEN
        var trang_thai_field=jQuery('#trang_thai'),
          noi_dung_field=jQuery("#noi_dung"),
          lydo_ktt_day_lai_field=jQuery("#lydo_ktt_day_lai"),
          id_field=jQuery("#id"),
          rowSelected_hdn=jQuery("#rowSelected"),
          thong_tin_khac_field=jQuery("#thong_tin_khac"),
          row_dts_0=jQuery("#row_dts_0"),
          frmDTSHoiTuNHTM=jQuery("#frmDTSHoiTuNHTM"),
          evenButtonBeforeField=jQuery("#evenButtonBefore"),
          dialog_message=jQuery("#dialog-message"),
          dialog_confirm=jQuery("#dialog-confirm"),
          focusField=jQuery("#focusField"),
          certSerialField=jQuery("#certSerial"),
          contenDataField=jQuery("#contenData"),
          signatureField=jQuery("#signature"),
          nhkb_nhan_field=jQuery("#nhkb_nhan"),
          dialog_form=jQuery("#dialog-form"),
          nhkb_chuyen_field=jQuery("#nhkb_chuyen");
          
        tableHighlightRow();
//        if(id_field.val()=='' || id_field.val()=='undefined'){
//          jQuery("#print").hide();
//        }
        jQuery("#dialog:ui-dialog").dialog( "destroy" );         
         
        thong_tin_khac_field.keyup(function(){
          limitChars(thong_tin_khac_field.attr('id'),146);
        });
        noi_dung_field.keyup(function(){
          limitChars(noi_dung_field.attr('id'),146);
        });
        lydo_ktt_day_lai_field.keyup(function(){
          limitChars(lydo_ktt_day_lai_field.attr('id'),146);
        });
        
        //dialog message
        jQuery("#dialog-message").dialog({
          autoOpen: false,
          resizable: false,
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
              action=evenButtonBeforeField.val();
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
                   var action=evenButtonBeforeField.val(),
                      rowSelected=rowSelected_hdn.val();
                   if(rowSelected!=null && rowSelected!='')
                    jQuery("#"+rowSelected).attr( {'class' : 'ui-widget-content ui-row-ltr'});
                    if(action!=null && (action=='<%=AppConstants.ACTION_ACCEPT%>'
                      ||action=='<%=AppConstants.ACTION_REJECT%>')){
                        updateExcuteDTS(action);
                    }else{
                       frmDTSHoiTuNHTM.attr({action:'thoatView.do'});
                       frmDTSHoiTuNHTM.target = '';
                       frmDTSHoiTuNHTM.submit();
                    }
                    jQuery(this).dialog("close");
                },
								"Không": function() {
									jQuery(this).dialog("close");
								}
							},
              close:function(e){
                if(evenButtonBeforeField.val()=='<%=AppConstants.ACTION_REJECT%>')
                    lydo_ktt_day_lai_field.focus();
                 else
                    rowSelectedFocus(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
              }
						});
          //dialog form Tra cuu dien tra soat
					dialog_form.dialog({
							autoOpen: false,
              resizable: false,
              height: 400,
							width: 300,
              modal: true,
							buttons: {
								"Tìm kiếm": function() {
                  findDTSTraLoi('<%=AppConstants.NNT_APP_CONTEXT_ROOT%>',role);
                },
								"Thoát": function() {
									evenButtonBeforeField.val('');
									jQuery(this).dialog("close");
								}
							},
              close:function(e){
              
              }
						});
          
          //**************************BUTTON Tim kiem CLICK********************************************
         jQuery("#btn_timKiem").click(function() {
            document.forms[0].target='';
            evenButtonBeforeField.val('<%=AppConstants.ACTION_FIND%>');
            jQuery("#nh_kb_nhan").val('');
            jQuery("#so_dts").val('');
            jQuery("#so_lenh_tt").val('');
            jQuery("#ma_ttvnhan").val('');
           if(role.indexOf('<%=AppConstants.NSD_KTT%>')>=0){
              jQuery("#ma_ttv").show();
							jQuery("#dialog-form" ).dialog( "open" );
							jQuery("#ma_ttv").focus();
           }else{
             jQuery("#ma_ttv").hide();
             jQuery("#dialog-form" ).dialog( "open" );
						 jQuery("#nh_kb_nhan").focus();
						}
         });
						
			   //**************************BUTTON Thoat CLICK********************************************
         jQuery("#btn_thoat").click(function() {
          document.forms[0].target='';
          if('<%=request.getAttribute("typeExit")%>' == "true"){
          var ttv_nhan_back = '<%=request.getParameter("ttv_nhan")%>';
          var trang_thai_back = '<%=request.getParameter("trang_thai")%>';
          var tu_ngay_back = '<%=request.getParameter("tu_ngay")%>';
          var den_ngay_back ='<%=request.getParameter("den_ngay")%>';
          var tu_ngay_lapdien_back = '<%=request.getParameter("tu_ngay_lapdien")%>';
          var den_ngay_lapdien_back ='<%=request.getParameter("den_ngay_lapdien")%>';
          var nhkb_chuyen_back = '<%=request.getParameter("nhkb_chuyen")%>';
          var nhkb_nhan_back = '<%=request.getParameter("nhkb_nhan")%>';
          var so_dts_back = '<%=request.getParameter("so_dts")%>';
          var so_ltt_back = '<%=request.getParameter("so_ltt")%>';
          var loai_ltt_back = '<%=request.getParameter("loai_ltt")%>';
          var loai_tra_soat_back = '<%=request.getParameter("loai_tra_soat")%>';
          var chieu_tra_soat_back = '<%=request.getParameter("chieu_tra_soat")%>';
          var urlBack = "tracuudts.do?action=Back";
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
           if(tu_ngay_lapdien_back != null &&tu_ngay_lapdien_back!='null'){
             urlBack += "&tu_ngay_lapdien_back="+tu_ngay_lapdien_back+"";
           }
            if(den_ngay_lapdien_back != null &&den_ngay_lapdien_back!='null'){
             urlBack += "&den_ngay_lapdien_back="+den_ngay_lapdien_back+"";
           }   
            if(so_dts_back != null &&so_dts_back!='null'){
             urlBack += "&so_dts_back="+so_dts_back+"";
           }
            if(nhkb_chuyen_back!=null && nhkb_chuyen_back!= 'null'){
               urlBack += "&nhkb_chuyen_back="+nhkb_chuyen_back+"";
            }
            if(nhkb_nhan_back!=null && nhkb_nhan_back!= 'null'){
               urlBack += "&nhkb_nhan_back="+nhkb_nhan_back+"";
            }
            if(so_ltt_back!=null && so_ltt_back!= 'null'){
               urlBack += "&so_ltt_back="+so_ltt_back+"";
            }
            if(loai_ltt_back!=null && loai_ltt_back!= 'null'){
               urlBack += "&loai_ltt_back="+loai_ltt_back+"";
            }
            if(loai_tra_soat_back!=null && loai_tra_soat_back!= 'null'){
               urlBack += "&loai_tra_soat_back="+loai_tra_soat_back+"";
            }
             if(chieu_tra_soat_back!=null && chieu_tra_soat_back!= 'null'){
               urlBack += "&chieu_tra_soat_back="+chieu_tra_soat_back ;
            }
            document.forms[0].action = urlBack;
            document.forms[0].submit();
          }else if('<%=request.getAttribute("typeExit")%>' == "T4"){
              var ma_nh_back = '<%=request.getParameter("ma_nh")%>';
              var tinh_back = '<%=request.getParameter("tinh")%>';
              var huyen_back = '<%=request.getParameter("huyen")%>';
              var trang_thai_back ='<%=request.getParameter("trang_thai")%>';
              var loai_lenh_back = '<%=request.getParameter("loai_lenh")%>';
              var tu_ltt_back ='<%=request.getParameter("tu_ltt")%>';
              var den_ltt_back = '<%=request.getParameter("den_ltt")%>';
              var tu_ngay_back = '<%=request.getParameter("tu_ngay")%>';
              var den_ngay_back = '<%=request.getParameter("den_ngay")%>';
              var so_dts_back = '<%=request.getParameter("so_dts")%>';
              var so_tien_temp_back = '<%=request.getParameter("so_tien_temp")%>';
              var urlBack = "TraCuuDTSToanQuocList.do?action=Back";
              if(ma_nh_back != null && ma_nh_back != '' && ma_nh_back != 'null'){
                  urlBack += "&ma_nh_back="+ma_nh_back+"";
                }
                if(tinh_back != null && tinh_back != '' && tinh_back != 'null'){
                  urlBack += "&tinh_back="+tinh_back+"";
                }    
                if(huyen_back != null && huyen_back != '' && huyen_back != 'null'){
                  urlBack += "&huyen_back="+huyen_back+"";
                }
                if(trang_thai_back != null && trang_thai_back != '' && trang_thai_back != 'null'){
                  urlBack += "&trang_thai_back="+trang_thai_back+"";
                }
                if(loai_lenh_back != null && loai_lenh_back != '' && loai_lenh_back != 'null'){
                urlBack += "&loai_lenh_back="+loai_lenh_back+"";
                }
                if(tu_ltt_back != null && tu_ltt_back != '' && tu_ltt_back != 'null'){
                  urlBack += "&tu_ltt_back="+tu_ltt_back+"";
                }
                if(den_ltt_back != null && den_ltt_back != '' && den_ltt_back != 'null'){
                  urlBack += "&den_ltt_back="+den_ltt_back+"";
                }
                if(tu_ngay_back != null && tu_ngay_back != '' && tu_ngay_back != 'null'){
                  urlBack += "&tu_ngay_back="+tu_ngay_back+"";
                }
                if(den_ngay_back != null && den_ngay_back != '' && den_ngay_back != 'null'){
                  urlBack += "&den_ngay_back="+den_ngay_back+"";
                }
                if(so_dts_back != null && so_dts_back != '' && so_dts_back != 'null'){
                  urlBack += "&so_dts_back="+so_dts_back+"";
                }
                if(so_tien_temp_back!=null && so_tien_temp_back!= '' && so_tien_temp_back != 'null'){
                  urlBack += "&so_tien_temp_back="+so_tien_temp_back+"";
                }
                document.forms[0].action = urlBack;
                document.forms[0].submit();
          }else{
            lydo_ktt_day_lai_field.removeClass('ui-state-error');
            evenButtonBeforeField.val('<%=AppConstants.ACTION_EXIT%>');
            dialog_confirm.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message_confirm.thoat"/>');
            dialog_confirm.dialog( "open" );
          }
         });
         
         //**************************BUTTON XAC NHAN CLICK********************************************
        jQuery("#btn_xacNhan").click(function() {
            document.forms[0].target='';
            try{
               if(role.indexOf('<%=AppConstants.NSD_TTV%>')>=0 && role.indexOf('<%=AppConstants.NSD_KTT%>')==-1){
                  if(trang_thai_field.val()=='<%=AppConstants.SP_DTS_HOI_NH_STATE_10%>'||
                    trang_thai_field.val()=='<%=AppConstants.SP_DTS_HOI_NH_STATE_11%>'){
                    evenButtonBeforeField.val('<%=AppConstants.ACTION_ACCEPT%>');
                    dialog_confirm.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message_confirm.xac_nhan_dts"/>');
                    dialog_confirm.dialog( "open" );
                 }else{
                    dialog_message.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message.not_accept_dts"/>');
                    dialog_message.dialog( "open" );
                 }
                 
                }else if(role.indexOf('<%=AppConstants.NSD_KTT%>')>=0){
//                  var v_cert = jQuery("#eSign")[0];
//                  v_cert.InitCert();
//                  certSerialField.val(v_cert.Serial); // Số serial
//                  contenDataField.val(v_cert.CertContent); //Nội dung cert (Ko cần hiện thi cho NSD)
//                  signatureField.val(v_cert.Sign(id_field.val())); //Generate chữ ký
                  if(trang_thai_field.val()=='<%=AppConstants.SP_DTS_HOI_NH_STATE_12%>'||
                    trang_thai_field.val()=='<%=AppConstants.SP_DTS_HOI_NH_STATE_13%>'){
                    evenButtonBeforeField.val('<%=AppConstants.ACTION_ACCEPT%>');
                    dialog_confirm.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message_confirm.xac_nhan_dts"/>');
                    dialog_confirm.dialog( "open" );
                 }else{
                    dialog_message.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message.not_accept_dts"/>');
                    dialog_message.dialog( "open" );
                  
                 }
                }
              }catch(e){
                evenButtonBeforeField.val('<%=AppConstants.ACTION_ACCEPT%>');
                dialog_message.html(e.message);
                dialog_message.dialog( "open" );
              }
        });
        //**************************BUTTON TRA LOI CLICK********************************************
        jQuery("#btn_traLoi").click(function() {
           document.forms[0].target='';
           document.forms[0].action='nhapdtsoattraloi.do';           
           document.forms[0].submit();
        });
      //**************************BUTTON Day lai CLICK********************************************
        jQuery("#btn_dayLai").click(function() {
            document.forms[0].target='';
            evenButtonBeforeField.val('<%=AppConstants.ACTION_REJECT%>');
            if(selectedRowBeforeClickButton()){
                var bValid=true;
                lydo_ktt_day_lai_field.removeClass( "ui-state-error" );
                if(lydo_ktt_day_lai_field.val()==null || lydo_ktt_day_lai_field.val()==''){
                  dialog_message.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message.lydo_ktt_day_lai_empty"/>');
                  dialog_message.dialog( "open" );
                  bValid=false;
                  focusField.val('lydo_ktt_day_lai');
                  lydo_ktt_day_lai_field.addClass( "ui-state-error" );
                }
                if(bValid && lydo_ktt_day_lai_field.val().length>146){
                  dialog_message.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message.lydo_day_lai_greater_than_146"/>');
                  dialog_message.dialog( "open" );
                  bValid=false;
                  focusField.val('lydo_ktt_day_lai');
                  lydo_ktt_day_lai_field.addClass( "ui-state-error" );
               } 
                if(bValid){
                 dialog_confirm.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message_confirm.day_lai_dts"/>');
                 dialog_confirm.dialog("open");
                }
              }
        });
        //**************************BUTTON Ghi CLICK********************************************
        jQuery("#btn_ghi").click(function() {
            document.forms[0].target='';
            var bValid=true;
            var evenButtonBefore=evenButtonBeforeField.val();
            if(evenButtonBefore!=null && evenButtonBefore=='<%=AppConstants.ACTION_EDIT%>'){
              if(noi_dung_field.val()==null || noi_dung_field.val()==''){
                dialog_message.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message.noi_dung_empty"/>');
                dialog_message.dialog( "open" );
                bValid=false;
                focusField.val('noi_dung');
                noi_dung_field.addClass( "ui-state-error" );
              }
              if(bValid && noi_dung_field.val().length>210){
                dialog_message.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message.noi_dung_greater_than_210"/>');
                dialog_message.dialog( "open" );
                bValid=false;
                focusField.val('noi_dung');
                noi_dung_field.addClass( "ui-state-error" );
             
              }
               if(bValid && thong_tin_khac_field.val().length>2000){
                dialog_message.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message.thong_tin_khac_greater_than_2000"/>');
                dialog_message.dialog( "open" );
                bValid=false;
                focusField.val('thong_tin_khac');
                thong_tin_khac_field.addClass( "ui-state-error" );
             
              }
              if(bValid){
               dialog_confirm.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message_confirm.sua_dts"/>');
               dialog_confirm.dialog("open");
              }
              
            }
          });
    //********************** EXCUTE UPDATE - DELETE ***********************************  
    /*
     * action update excute
     */
     
     function updateExcuteDTS(action){
        var certSerialFieldValue,contenDataFieldValue,signatureFieldValue;
//           if (action=='<%=AppConstants.ACTION_ACCEPT%>'){
//             certSerialFieldValue=certSerialField.val();
//             contenDataFieldValue=contenDataField.val();
//             signatureFieldValue=signatureField.val();
//           }
         jQuery.ajax({
             type: "POST",
             url: "xacnhandtshoinhtm.do",	
             data: {"action" : action,
                    "noidung" : noi_dung_field.val(),
                    "thong_tin_khac" : thong_tin_khac_field.val(),
                    "lydo_ktt_day_lai" : lydo_ktt_day_lai_field.val(),
                    "id" : id_field.val(),
                    "trang_thai" : trang_thai_field.val(),
                    "signature" : signatureFieldValue,
                    "certSerial" : certSerialFieldValue,
                    "contenData" : contenDataFieldValue,
                    "nhkb_nhan" : nhkb_nhan_field.val(),
                    "nhkb_chuyen" : nhkb_chuyen_field.val(),
                    "nd" : Math.random() * 100000},
             dataType:'json',
             success: function(data,textstatus){
                 if(textstatus!=null && textstatus=='<%=AppConstants.SUCCESS%>'){
                    if(data!=null){
                      if(data.error == undefined || (data.error!='undefined' && data.error!='')){
                        fillDataTableDST(data,'<%=AppConstants.NNT_APP_CONTEXT_ROOT%>',role);
                        if(action=='<%=AppConstants.ACTION_REJECT%>')
                          dialog_message.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message.day_lai_thanh_cong"/>');
                        else if (action=='<%=AppConstants.ACTION_ACCEPT%>')
                          dialog_message.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message.xac_nhan_thanh_cong"/>');
                        disableButton();
                        resetFormDTS();
                      }else if(data.error!=null && data.error=='TTSP_ERROR_0001'){
                          dialog_message.html('<fmt:message key="xuly_dtsoat_hoi_nhtm.page.message.trang_thai_thay_doi"/>');
                      }else
                          dialog_message.html(data.error);
                     dialog_message.dialog( "open" );
                     defaultRowSelected(role);
                  }
                }
              },
              error:function(textstatus){
                dialog_message.html(textstatus.responseText);
                dialog_message.dialog( "open" );
             }
         });
      }
      if('<%=request.getParameter("action")%>'=='null'){
          resetFormDTS();
          defaultRowSelected(role);
          if(jQuery("#id").val()=='' || jQuery("#id").val()==undefined){
          disableButton();
          }
       }else{
          defaultRowSelected(role);
          disableButton();
          jQuery("#btn_timKiem").hide();
          jQuery("#btn_refresh").hide();
          noi_dung_field.attr({disabled:true});
          thong_tin_khac_field.attr({disabled:true});
          row_dts_0.attr({'class':'ui-row-ltr ui-state-highlight'});
          //set value ten nhkb
      }
    
    
   });
   // In
    function formAction(){
      if(jQuery("#id").val()=='' || jQuery("#id").val()==undefined){
        alert('Không có bản ghi nào để thực hiện in!');
        return;
      }     
      var f = document.forms[0];
      f.action = "xulydtshoitunhtmRpt.do";
      var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
      newDialog = window.open('', '_form', params);          
      f.target='_form';
      f.submit();
    } 
    </script>
   
    <div id="body">
      <table border=0 cellSpacing=0 cellPadding=0 width="100%"  align=center>
            <tbody>
              <tr>
                <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
                <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                  <span class=title2><fmt:message key="xuly_dtsoat_hoi_nhtm.page.title.xu_ly_dts_hoi_tu_nhtm"/></span>
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
                                  <td width="15%"  vAlign=top>
                                  <div class="clearfix">
                                    <fieldset class="fieldsetCSS">
                                      <legend style="vertical-align:middle">
                                        <fmt:message key="xuly_dtsoat_hoi_nhtm.fieldset.title.so_dien_tra_soat"/>
                                      </legend>
                                      <div class="sodientrasoattable">
                                          <div>
                                              <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                                                <thead>
                                                  <tr>
                                                    <th class="ui-state-default ui-th-column"
                                                        width="62%;">
                                                     <fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.so_dien"/>
                                                    </th>
                                                    <th height="20" width="28%"
                                                        class="ui-state-default ui-th-column">
                                                     <fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.tinh_trang"/>
                                                    </th>
                                                    <th height="20" width="10%;"
                                                        class="ui-state-default ui-th-column">
                                                     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    </th>
                                                  </tr>
                                                </thead>
                                              </table>
                                            </div>
                                            <div style="height:300px;" class="ui-jqgrid-bdiv ui-widget-content">
                                              <table class="data-grid" border="0" id="data-grid" style="BORDER-COLLAPSE: collapse;"  cellSpacing="0" cellPadding="0"  width="100%">
                                                <tbody>
                                                  <logic:present name="listDTSoatLTTDi">
                                                    <logic:iterate id="listDTSoatHoiNH" name="listDTSoatLTTDi" indexId="index">
                                                    <tr class="ui-widget-content jqgrow ui-row-ltr"   id="row_dts_<bean:write name="index"/>" ondblclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');" onclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');fillDataDTS('<bean:write name="listDTSoatHoiNH" property="id"/>','row_dts_<bean:write name="index"/>','<%=request.getAttribute("chucdanh")%>');">
                                                      <td width="40%;"  align="center" id="td_dts_<bean:write name="index"/>">
                                                        <input tabindex="0" name="row_item" id="<bean:write name="index"/>" type="text" style="border:0px;font-size:10px;float:left;" value="<bean:write name="listDTSoatHoiNH" property="id"/>" onkeydown="arrowUpDown(event)" readonly="true"/>
                                                      </td>
                                                      <td width="28%;" align="center" >
                                                        <logic:equal value="01" name="listDTSoatHoiNH" property="trang_thai">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg" title="&#272;&#7849;y l&#7841;i"/>
                                                        </logic:equal>
                                                        <logic:equal value="02" name="listDTSoatHoiNH" property="trang_thai">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" title="Ch&#7901; KTT duy&#7879;t"/>
                                                        </logic:equal>
                                                        <logic:equal value="03" name="listDTSoatHoiNH" property="trang_thai">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" title="&#272;&#227; duy&#7879;t"/>
                                                        </logic:equal>
                                                        <logic:equal value="04" name="listDTSoatHoiNH" property="trang_thai">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif" title="&#272;&#227; h&#7911;y"/>
                                                        </logic:equal>
                                                        <logic:equal value="10" property="trang_thai" name="listDTSoatHoiNH">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg" title="KTT đẩy lại"/>
                                                        </logic:equal>  
                                                        <logic:equal value="11" property="trang_thai" name="listDTSoatHoiNH">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" title="Chờ xử lý"/>
                                                        </logic:equal>
                                                        <logic:equal value="12" property="trang_thai" name="listDTSoatHoiNH">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" title="Đã xử lý"/>
                                                        </logic:equal>
                                                        <logic:equal value="13" property="trang_thai" name="listDTSoatHoiNH">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" title="Đã trả lời"/>
                                                        </logic:equal>
                                                        <logic:equal value="14" property="trang_thai" name="listDTSoatHoiNH">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" title="Chờ KTT duyệt"/>
                                                        </logic:equal>
                                                        <logic:equal value="15" property="trang_thai" name="listDTSoatHoiNH">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" title="Đã gửi"/>
                                                        </logic:equal>
                                                        <logic:equal value="16" property="trang_thai" name="listDTSoatHoiNH">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" title="Đã xác nhận"/>
                                                        </logic:equal>
                                                        <logic:equal value="17" property="trang_thai" name="listDTSoatHoiNH">
                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif" title="Đã hủy"/>
                                                        </logic:equal>
                                                       </td>
                                                    </tr>
                                                    </logic:iterate>
                                                    <logic:empty name="listDTSoatLTTDi" >
                                                      <tr>
                                                        <td colspan="5" align="center">
                                                          <span style="color:red;"><fmt:message key="xuly_dtsoat_hoi_nhtm.error.not_found"/></span>
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
                                                <tr id="refreshButton">
                                                  <td class="ui-state-default ui-th-column" title="Refresh" id="refresh">
                                                    <div class="ui-pg-div" style="float:left;"><span id="btn_refresh" class="ui-icon ui-icon-refresh" onclick="refreshGrid('<%=request.getParameter("id")%>','<%=AppConstants.NNT_APP_CONTEXT_ROOT%>','<%=request.getAttribute("chucdanh")%>');" style="cursor:pointer;"></span></div>
                                                    <div class="ui-pg-div" style="float:left;"><span id="btn_timKiem" class="ui-icon ui-icon-search" title="Tìm kiếm" style="cursor:pointer;"></span></div>
                                                    <div style="float:RIGHT;FONT-WEIGHT:NORMAL" class="ui-pg-div" id="tong_ban_ghi">
                                                      <fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.tong_ban_ghi">
                                                        <fmt:param value="<%=(((java.util.Collection)request.getAttribute(\"listDTSoatLTTDi\")).size())%>" />
                                                      </fmt:message>
                                                    </div>
                                                  </td>
                                                </tr>
                                              </thead>
                                            </table>
                                            <div style="padding-top:10px;float: left;"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.trang_thai"/>: <span id="mo_ta_trang_thai" style="font-weight:bold;"><%=request.getAttribute("mo_ta_trang_thai")%></span></div>
                                          </div>
                                        </div>
                                     </fieldset>
                                    </div>
                                 </td>
                                 <td width="85%" valign="top">
                                  <fieldset class="fieldsetCSS">
                                    <legend style="vertical-align:middle">
                                      <fmt:message key="xuly_dtsoat_hoi_nhtm.fieldset.title.thong_tin_chung"/>
                                    </legend>
                                    <div style="padding:5px 5px 5px 5px;">
                                    <input type="hidden" id="rowSelected"/>
                                    <input type="hidden" id="evenButtonBefore"/>
                                    <input type="hidden" id="focusField"/>
                                    <input type="hidden" id="signature"/>
                                    <input type="hidden" id="certSerial"/>
                                    <input type="hidden" id="contenData"/>                                    
                                        <html:form styleId="frmDTSHoiTuNHTM" action="/xulydtshoitunhtmView.do">

                                       <%if(request.getAttribute("chucdanh").toString().indexOf(AppConstants.NSD_KTT) >=0){%>
                                       <!--<object id="eSign" name="eSign" height="31" width="177" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>-->
                                      <%}%>
                                      <html:hidden property="nhkb_nhan" styleId="nhkb_nhan"/>
                                      <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen"/>
                                      <html:hidden property="trang_thai" styleId="trang_thai"/>
                                      <html:hidden property="ttv_nhan" styleId="ttv_nhan"/>
                                      <html:hidden property="loai_dts" styleId="loai_dts"/>
                                      <html:hidden property="so_tchieu" styleId="so_tchieu"/>
                                      <!--<html:hidden property="id" styleId="id"/>-->
                                      <table style="BORDER-COLLAPSE: collapse;"  border="1" cellSpacing="0" borderColor="#b0c4de" cellPadding="0" width="99%">
                                       <tbody>
                                          <tr>
                                              <td align="right">
                                                <label for="id">
                                                  <fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.so_dts"/>
                                                </label>
                                              </td>
                                              <td colspan="3">
                                                <html:text property="id" style="width:98%" styleId="id" styleClass="fieldTextCode"  readonly="true"/>
                                              </td>
                                            </tr>
                                          <tr>
                                            <td align="right" width="15%">
                                              <label for="ltt_id"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.so_lenh_thanh_toan" /></label>
                                            </td>
                                            <td colspan="3" align="left" class="box_common_content" width="34%">
                                              <html:text property="ltt_id" style="width:98%" styleId="ltt_id" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                                            </td>
                                            <td align="right" width="16%">
                                              <label for="ngay_thanh_toan"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.ngay_thanh_toan"/></label>
                                            </td>
                                            <td colspan="3" align="left">
                                              <html:text property="ngay_thanh_toan" style="width:99%" styleId="ngay_thanh_toan" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" />
                                            </td>
                                          </tr>
                                          <tr>
                                            <td align=right >
                                              <label for="ma_don_vi_tra_soat"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.don_vi_tra_soat"/></label>
                                            </td>
                                            <td width="8%">
                                               <html:text property="ma_don_vi_tra_soat" style="width:91%" styleId="ma_don_vi_tra_soat" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                                            </td>
                                            <td align="left" colspan="2">
                                              <html:text property="ten_don_vi_tra_soat"  styleId="ten_don_vi_tra_soat" styleClass="fieldText" readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" style="font-weight:bold;width:98%" />
                                            </td>
                                            <td align=right>
                                              <label for="ma_don_vi_tra_soat"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.don_vi_nhan_tra_soat"/></label>
                                            </td>
                                            <td width="8%">
                                                <html:text property="ma_don_vi_nhan_tra_soat" style="width:91%" styleId="ma_don_vi_nhan_tra_soat" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                                            </td>
                                            <td align="left" colspan="2">
                                              <html:text property="ten_don_vi_nhan_tra_soat" styleId="ten_don_vi_nhan_tra_soat" styleClass="fieldText" readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" style="font-weight:bold;width:98%"/>
                                            </td>
                                          </tr>
                                          <tr>
                                            <td align=right>
                                              <label for="nguoi_nhap_nh"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.nguoi_lap"/></label>
                                            </td>
                                            <td align="left" colspan="3">
                                              <html:text property="nguoi_nhap_nh" style="width:98%" styleId="nguoi_nhap_nh" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                            </td>
                                            <td align=right>
                                              <label for="ngay_nhap_nh"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.ngay_lap"/></label>
                                            </td>
                                            <td align="left" colspan="3">
                                              <html:text property="ngay_nhap_nh" style="width:99%" styleId="ngay_nhap_nh" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                                            </td>
                                          </tr>
                                          <tr>
                                            <td align=right>
                                              <label for="nguoi_ks_nh"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.ktt"/></label>
                                            </td>
                                            <td align="left" colspan="3">
                                              <html:text property="nguoi_ks_nh" style="width:98%" styleId="nguoi_ks_nh" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                            </td>
                                            <td align=right>
                                              <label for="ngay_ks_nh"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.ngay_kiem_soat"/></label>
                                            </td>
                                            <td align="left" colspan="3">
                                              <html:text property="ngay_ks_nh" style="width:99%" styleId="ngay_ks_nh" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                            </td>
                                          </tr>
                                          <tr>
                                            <td align=right>
                                              <label for="noi_dung"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.noi_dung_tra_soat"/></label>
                                            </td>
                                            <td colspan="7">
                                              <html:textarea property="noidung" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="noi_dung" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()"></html:textarea>
                                              <span style="color:#FF0000" id="word_count_noi_dung">
                                              <fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.tong_so_ki_tu"/>
                                              <span id="display_count_noi_dung">210</span></span>
                                            </td>
                                          </tr>
                                          <tr>
                                            <td align=right>
                                              <label for="thong_tin_khac"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.thong_tin_khac"/></label>
                                            </td>
                                            <td colspan="7">
                                              <html:textarea property="thong_tin_khac" onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="readonly" styleId="thong_tin_khac" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()"></html:textarea>
                                              <span style="color:#FF0000" id="word_count_thong_tin_khac">
                                              <fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.tong_so_ki_tu"/>
                                              <span id="display_count_thong_tin_khac">2000</span></span>
                                            </td>
                                          </tr>
                                          <tr>
                                            <td align=right>
                                              <label for="lydo_ktt_day_lai"><fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.ktt_day_lai"/></label>
                                            </td>
                                            <td colspan="7">
                                              <html:textarea property="lydo_ktt_day_lai" onkeydown="forcusButtonDayLai(event);arrowUpDown(event)" styleId="lydo_ktt_day_lai" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea"  onmouseover="Tip(this.value)" onmouseout="UnTip()"></html:textarea>
                                              <span style="color:#FF0000" id="word_count_lydo_ktt_day_lai">
                                              <fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.tong_so_ki_tu"/>
                                              <span id="display_count_lydo_ktt_day_lai">146</span></span>
                                            </td>
                                          </tr>
                                        </tbody>
                                      </table> 
                                      </html:form>
                                     </div>
                                    </fieldset>
                                  </td>
                                </tr>
                                <tr>
                                  <td colspan="2">
                                    <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%">
                                      <tr align="right">
                                        <td>
                                          <span>
                                            <button id=btn_ghi accessKey=x  class=buttonCommon>
                                              <fmt:message key="xuly_dtsoat_hoi_nhtm.page.button.save"/>
                                            </button>
                                          </span>
                                          <span>
                                            <button id=btn_xacNhan accessKey=x  class=buttonCommon>
                                              <fmt:message key="xuly_dtsoat_hoi_nhtm.page.button.confirm"/>
                                            </button>
                                          </span>
                                          <span>
                                            <button id=btn_dayLai accessKey=l  class=buttonCommon>
                                              <fmt:message key="xuly_dtsoat_hoi_nhtm.page.button.move_again"/>
                                            </button>
                                          </span>
                                          <span>
                                            <button id=btn_traLoi accessKey=r  class=buttonCommon >
                                              <fmt:message key="xuly_dtsoat_hoi_nhtm.page.button.answer"/>
                                            </button>
                                          </span>
                                          <span id="print"> 
                                            <button 
                                                    id="btnIn" onclick="formAction()" class="ButtonCommon"
                                                    type="button" >
                                              <fmt:message key="xuly_dtsoat_hoi_nhtm.page.button.print"/>
                                            </button>
                                          </span>
                                          <!--<span>
                                            <button id="btn_timKiem" accessKey=I class=buttonCommon>
                                                <fmt:message key="xuly_dtsoat_hoi_nhtm.page.button.find"/>
                                            </button>
                                          </span>-->
                                          <span>
                                             <button id="btn_thoat" accessKey=o  class=buttonCommon>
                                              <fmt:message key="xuly_dtsoat_hoi_nhtm.page.button.exit"/>
                                            </button>
                                          </span>
                                          <span>
                                            <input style="WIDTH: 1px; HEIGHT: 1px" type=hidden name=thebottom/>
                                          </span>
                                        </td>
                                      </tr>
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
      
      <div id="dialog-message" title="<fmt:message key="xuly_dtsoat_hoi_nhtm.page.title.dialog_message"/>">
        <p>
          <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
          <span id="message"></span>
        </p>
      </div>
      <div id="dialog-form" title="<fmt:message key="xuly_dtsoat_hoi_nhtm.page.title.dialog_form"/>">
        <p class="validateTips"></p>
          <form id="tra-cuu-dts">
              <div id=ma_ttv style="padding-top:10px;">
                <label for="ma_ttv" style="padding-left:60px;">
                  <fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.ma_ttv"/>
                </label>
                <input type="text" name="ma_ttv" id="ma_ttvnhan" class=" text ui-widget-content ui-corner-all" onkeydown="if(event.keyCode==13) event.keyCode=9;" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
              </div>
              <div style="padding-top:10px;">
                <label for="nh_bk_nhan" style="padding-left:30px;">
                  <fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.nh_bk_nhan"/>
                </label>
                <input type="text" name="nh_bk_nhan" id="nh_kb_nhan" class=" text ui-widget-content ui-corner-all" onkeydown="if(event.keyCode==13) event.keyCode=9;" onmouseover="Tip(this.value)" onmouseout="UnTip()" onKeyPress="return numbersonly(this,event,true)" maxlength="8"/> 
              </div>
              <div style="padding-top:10px;">
                <label for="so_dts" style="padding-left:16px;">
                  <fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.so_dts" />
                </label>
                <input type="text" name="so_dts" id="so_dts" class=" text ui-widget-content ui-corner-all" onkeydown="if(event.keyCode==13) event.keyCode=9;" onmouseover="Tip(this.value)" onmouseout="UnTip()" onKeyPress="return numbersonly(this,event,true)" /> 
              </div>
              <div style="padding-top:10px;">
                <label for="so_lenh_tt" style="padding-left:40px;">
                  <fmt:message key="xuly_dtsoat_hoi_nhtm.page.lable.so_lenh_tt"/>
                </label>
                <input type="text" name="so_lenh_tt" id="so_lenh_tt" class=" text ui-widget-content ui-corner-all" onkeydown="if(event.keyCode==13) event.keyCode=9;" onmouseover="Tip(this.value)" onmouseout="UnTip()" onKeyPress="return numbersonly(this,event,true)" /> 
              </div>
         
      </div>
      <div id="dialog-confirm" title="<fmt:message key="xuly_dtsoat_hoi_nhtm.page.title.dialog_confirm"/>" >
        <p>
          <span class="ui-icon ui-icon-hide()" style="float:left; margin:0 7px 20px 0;"></span>
          <span id="message_confirm"></span>
        </p>
      </div>
 <%@ include file="/includes/ttsp_bottom.inc"%>
