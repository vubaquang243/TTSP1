<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dien.tra.soat.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.DTSoatLTTDiResource"/>

 <script type="text/javascript">
    jQuery.noConflict();
    var role='<%=request.getAttribute("chucdanh")%>';
  //************************************ LOAD PAGE **********************************
     jQuery(document).ready(function()
      {
        var contexRoot='<%=AppConstants.NNT_APP_CONTEXT_ROOT%>',
          noi_dung_field=jQuery("#noi_dung"),
          thong_tin_khac_field=jQuery("#thong_tin_khac"),
          row_dts_0=jQuery("#row_dts_0"),
          lydo_ktt_day_lai_field=jQuery("#lydo_ktt_day_lai"),
          rowSelectedField=jQuery("#rowSelected"),
          focusField=jQuery("#focusField"),
          evenButtonBeforeField=jQuery("#evenButtonBefore"),
          trang_thai_field=jQuery("#trang_thai"),
          nhkb_nhan_field=jQuery("#nhkb_nhan"),
          nhkb_chuyen_field=jQuery("#nhkb_chuyen"),
          ma_don_vi_nhan_tra_soat_field=jQuery("#ma_don_vi_nhan_tra_soat"),
          ma_don_vi_tra_soat_field=jQuery("#ma_don_vi_tra_soat"),
          id_field=jQuery("#id"),
          ltt_id_field=jQuery("#ltt_id"),
          ttv_nhan_field=jQuery("#ttv_nhan"),
          certSerialField=jQuery("#certSerial"),
          contenDataField=jQuery("#contenData"),
          signatureField=jQuery("#signature"),
          dialog_confirm=jQuery("#dialog-confirm" ),
          dialog_form=jQuery("#dialog-form"),
          dialog_message=jQuery("#dialog-message"),
          frmXyLyDTSLTT=jQuery("#frmDTS"),
          nhkb_nhan_ltt_field=jQuery("#nhkb_nhan_ltt"),
          allFields = jQuery([]).add( noi_dung_field ).add( thong_tin_khac_field ).add( row_dts_0 )
              .add(lydo_ktt_day_lai_field ).add(trang_thai_field)
              .add(nhkb_nhan_field ).add(nhkb_chuyen_field).add(ma_don_vi_nhan_tra_soat_field).add(ma_don_vi_tra_soat_field).add(id_field)
               .add(ltt_id_field ).add(ttv_nhan_field).add(certSerialField).add(signatureField).add(contenDataField);
               
        tableHighlightRow();
//        if(ltt_id_field.val()=='' && ltt_id_field.val()=='undefined'){
//          jQuery("#in").hide();
//        }
        jQuery("#dialog:ui-dialog").dialog( "destroy" );
         if('<%=request.getParameter("action")%>'=='null'){
          disableButton();
          resetFormDTS();
         }else{
            noi_dung_field.attr({disabled:true});
            thong_tin_khac_field.attr({disabled:true});
            row_dts_0.attr({'class':'ui-row-ltr ui-state-highlight'});
         }
        thong_tin_khac_field.keyup(function(){
          limitChars(thong_tin_khac_field.attr('id'),2000);
        });
        noi_dung_field.keyup(function(){
          limitChars(noi_dung_field.attr('id'),210);
        });
        lydo_ktt_day_lai_field.keyup(function(){
          limitChars(lydo_ktt_day_lai_field.attr('id'),146);
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
              dialog_message.dialog( "close" );
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
                      rowSelected=rowSelectedField.val();
                
                   if(action!=null && (action!='<%=AppConstants.ACTION_EXIT%>' && action!='')){
                      updateExcuteDTS(action,rowSelected);
                   }else if(action!=null && action=='<%=AppConstants.ACTION_EXIT%>'){
                     if(rowSelected!=null && rowSelected!='')
                      jQuery("#"+rowSelected).attr( {'class' : 'ui-widget-content ui-row-ltr'});
                      if('<%=request.getParameter("action")%>'=='<%=AppConstants.ACTION_VIEW_DETAIL%>'){
                        var ttv_nhan_back = '<%=request.getParameter("ttv_nhan")%>',
                         trang_thai_back = '<%=request.getParameter("trang_thai")%>',
                         tu_ngay_back = '<%=request.getParameter("tu_ngay")%>',
                         den_ngay_back ='<%=request.getParameter("den_ngay")%>',
                         tu_ngay_lapdien_back = '<%=request.getParameter("tu_ngay_lapdien")%>',
                         den_ngay_lapdien_back ='<%=request.getParameter("den_ngay_lapdien")%>',
                         nhkb_chuyen_back = '<%=request.getParameter("nhkb_chuyen")%>',
                         nhkb_nhan_back = '<%=request.getParameter("nhkb_nhan")%>',
                         so_dts_back = '<%=request.getParameter("so_dts")%>',
                         so_ltt_back = '<%=request.getParameter("so_ltt")%>',
                         loai_ltt_back = '<%=request.getParameter("loai_ltt")%>',
                         loai_tra_soat_back = '<%=request.getParameter("loai_tra_soat")%>',
                         urlBack = "tracuudts.do?action=Back",
                         chieu_tra_soat_back = '<%=request.getParameter("chieu_tra_soat")%>';
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
                       frmXyLyDTSLTT.attr({action:urlBack});
                       frmXyLyDTSLTT.submit();
                    }else{
                     frmXyLyDTSLTT.attr({action:'thoatView.do'});
                     frmXyLyDTSLTT.target = '';
                     frmXyLyDTSLTT.submit();
                   }
                  }else{
                    if(rowSelected!=null && rowSelected!='')
                      jQuery("#"+rowSelected).attr( {'class' : 'ui-widget-content ui-row-ltr'});
                    if('<%=request.getParameter("action")%>'=='null'){
                      resetFormDTS();
                      disableButton();
                    }else{
                     frmXyLyDTSLTT.attr({action:'thoatView.do'});
                     frmXyLyDTSLTT.target = '';
                     frmXyLyDTSLTT.submit();
                    }
                  }
                  dialog_confirm.dialog( "close" );
                  
                },
								"Không": function() {
                   dialog_confirm.dialog( "close" );
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
              height: 400,
							width: 300,
              resizable:false,
              modal: true,
							buttons: {
								"Tìm kiếm": function()
                {
									
                  findDTS('<%=AppConstants.NNT_APP_CONTEXT_ROOT%>',role);
                  resetFormDTS();
                  
                },
								"Thoát": function() {
									evenButtonBeforeField.val('');
									dialog_form.dialog( "close" );
								}
							},
							"Đóng": function() {
              }
						});
            
          
      //**************************BUTTON Sua CLICK********************************************
        jQuery("#btn_sua").click(function() {
          document.forms[0].target='';
            evenButtonBeforeField.val('<%=AppConstants.ACTION_EDIT%>');
            if(selectedRowBeforeClickButton()){
              var trang_thai=trang_thai_field.val();
              if(trang_thai=='<%=AppConstants.TRANG_THAI_DAY_LAI%>' || 
                trang_thai=='<%=AppConstants.TRANG_THAI_CHO_DUYET%>' ){
                btnSuaClick();
              }else{
                dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.not_edit_dts"/>');
                dialog_message.dialog( "open" );
              }
               
            }
          });
          
        //**************************BUTTON Ghi CLICK********************************************
        jQuery("#btn_ghi").click(function() {
            document.forms[0].target='';
            var bValid=true;
            var evenButtonBefore=evenButtonBeforeField.val();
            noi_dung_field.removeClass("ui-state-error" );
            thong_tin_khac_field.removeClass("ui-state-error");
            if(evenButtonBefore!=null && evenButtonBefore=='<%=AppConstants.ACTION_EDIT%>'){
              if(noi_dung_field.val()==null || noi_dung_field.val()==''){
                dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.noi_dung_empty"/>');
                dialog_message.dialog( "open" );
                bValid=false;
                focusField.val('noi_dung');
                noi_dung_field.addClass( "ui-state-error" );
              }
              if(bValid && noi_dung_field.val().length>210){
                dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.noi_dung_greater_than_210"/>');
                dialog_message.dialog( "open" );
                bValid=false;
                focusField.val('noi_dung');
                noi_dung_field.addClass( "ui-state-error" );
             
              }
               if(thong_tin_khac_field.val().length>2000){
                dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.thong_tin_khac_greater_than_2000"/>');
                dialog_message.dialog( "open" );
                bValid=false;
                focusField.val('thong_tin_khac');
                thong_tin_khac_field.addClass( "ui-state-error" );
             
              }else if(thong_tin_khac_field.val().trim()==''){
                dialog_message.html(GetUnicode('Tr&#432;&#7901;ng th&#244;ng tin kh&#225;c kh&#244;ng &#273;&#432;&#7907;c &#273;&#7875; tr&#7889;ng'));
                dialog_message.dialog( "open" );
                bValid=false;
                focusField.val('thong_tin_khac');
                thong_tin_khac_field.addClass( "ui-state-error" );
              }
              if(bValid){
               dialog_confirm.html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.sua_dts"/>');
               dialog_confirm.dialog("open");
              }
              
            }
          });
         //**************************BUTTON Huy CLICK********************************************
        jQuery("#btn_huy").click(function() {
            document.forms[0].target='';
            evenButtonBeforeField.val('<%=AppConstants.ACTION_CANCEL%>');
            if(selectedRowBeforeClickButton()){
               var trang_thai=trang_thai_field.val();
               if(trang_thai=='<%=AppConstants.TRANG_THAI_CHO_DUYET%>'|| trang_thai=='<%=AppConstants.TRANG_THAI_DAY_LAI%>'){
                  dialog_confirm.html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.huy_dts"/>');
                  dialog_confirm.dialog("open");
                }else{
                  dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.not_cancel_dts"/>');
                  dialog_message.dialog( "open" );
                }
            }
        });
        //**************************BUTTON Duyet CLICK********************************************
        jQuery("#btn_duyet").click(function() {
          document.forms[0].target='';
          try{
            if("Y"==strChkKy){
            if(!ky()){
              alert("Ký không thành công");
              return false;
            }
          }
            evenButtonBeforeField.val('<%=AppConstants.ACTION_APPROVED%>');
            if(selectedRowBeforeClickButton()){
              dialog_confirm.html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.duyet_dts"/>');
              dialog_confirm.dialog("open");
            }
          }catch(e){
            dialog_message.html(e.message);
            dialog_message.dialog("open");
          }
        });
         //**************************BUTTON Day lai CLICK********************************************
        jQuery("#btn_dayLai").click(function() {
            document.forms[0].target='';
            evenButtonBeforeField.val('<%=AppConstants.ACTION_REJECT%>');
            lydo_ktt_day_lai_field.removeClass('ui-state-error');
            if(selectedRowBeforeClickButton()){
                var bValid=true;
                if(lydo_ktt_day_lai_field.val()==null || lydo_ktt_day_lai_field.val()==''){
                  dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.lydo_ktt_day_lai_empty"/>');
                  dialog_message.dialog( "open" );
                  bValid=false;
                  focusField.val('lydo_ktt_day_lai');
                  lydo_ktt_day_lai_field.addClass( "ui-state-error" );
                }
                if(bValid && lydo_ktt_day_lai_field.val().length>146){
                  dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.lydo_day_lai_greater_than_146"/>');
                  dialog_message.dialog( "open" );
                  bValid=false;
                  focusField.val('lydo_ktt_day_lai');
                  lydo_ktt_day_lai_field.addClass( "ui-state-error" );
               } 
                if(bValid){
                 dialog_confirm.html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.day_lai_dts"/>');
                 dialog_confirm.dialog("open");
                }
              }
        });
        
        //**************************BUTTON Thoat CLICK********************************************
         jQuery("#btn_thoat").click(function() {
            document.forms[0].target='';
            if(evenButtonBeforeField.val()!=null && evenButtonBeforeField.val()=='<%=AppConstants.ACTION_EDIT%>'){
                fillDataDTS(id_field.val(),rowSelectedField.val(),role,true);
                enableButtonDTS(role,trang_thai_field.val());
                evenButtonBeforeField.val("");
                rowSelectedFocus(jQuery("tr[class='ui-row-ltr ui-state-highlight']").attr('id'));
            }else{
              evenButtonBeforeField.val('<%=AppConstants.ACTION_EXIT%>');
              if('<%=request.getParameter("action")%>'=='<%=AppConstants.ACTION_VIEW_DETAIL%>'){
              var ttv_nhan_back = '<%=request.getParameter("ttv_nhan")%>',
               trang_thai_back = '<%=request.getParameter("trang_thai")%>',
               tu_ngay_back = '<%=request.getParameter("tu_ngay")%>',
               den_ngay_back ='<%=request.getParameter("den_ngay")%>',
               tu_ngay_lapdien_back = '<%=request.getParameter("tu_ngay_lapdien")%>',
               den_ngay_lapdien_back ='<%=request.getParameter("den_ngay_lapdien")%>',
               nhkb_chuyen_back = '<%=request.getParameter("nhkb_chuyen")%>',
               nhkb_nhan_back = '<%=request.getParameter("nhkb_nhan")%>',
               so_dts_back = '<%=request.getParameter("so_dts")%>',
               so_ltt_back = '<%=request.getParameter("so_ltt")%>',
               loai_ltt_back = '<%=request.getParameter("loai_ltt")%>',
               loai_tra_soat_back = '<%=request.getParameter("loai_tra_soat")%>',
               urlBack = "tracuudts.do?action=Back",
               chieu_tra_soat_back = '<%=request.getParameter("chieu_tra_soat")%>';
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
               frmXyLyDTSLTT.attr({action:urlBack});
               frmXyLyDTSLTT.submit();
            }else if('<%=request.getParameter("action")%>'=='<%=AppConstants.ACTION_VIEW_DETAIL_DTS_T4%>'){
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
                jQuery("#dialog-confirm").html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.thoat"/>');
                jQuery("#dialog-confirm").dialog("open");
              }
            }
         });
          //**************************BUTTON xem lenh thanh toan CLICK********************************************
         jQuery("#btn_xemLTT").click(function() {          
          document.forms[0].target='';
          var urlBack = "&ActionBack="+'<%=request.getParameter("action")%>'+"&idDts="+'<%=request.getParameter("id")%>';
          if(ltt_id_field.val()==null || ltt_id_field.val()=='' || ltt_id_field.val()=='undefined'){
            alert('Không có lệnh thanh toán để xem!');
          }
          if('<%=request.getParameter("action")%>'=='<%=AppConstants.ACTION_VIEW_DETAIL_DTS_T4%>'){
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
          }else if('<%=request.getParameter("action")%>'=='<%=AppConstants.ACTION_VIEW_DETAIL%>'){
              var ttv_nhan_back = '<%=request.getParameter("ttv_nhan")%>',
               trang_thai_back = '<%=request.getParameter("trang_thai")%>',
               tu_ngay_back = '<%=request.getParameter("tu_ngay")%>',
               den_ngay_back ='<%=request.getParameter("den_ngay")%>',
               tu_ngay_lapdien_back = '<%=request.getParameter("tu_ngay_lapdien")%>',
               den_ngay_lapdien_back ='<%=request.getParameter("den_ngay_lapdien")%>',
               nhkb_chuyen_back = '<%=request.getParameter("nhkb_chuyen")%>',
               nhkb_nhan_back = '<%=request.getParameter("nhkb_nhan")%>',
               so_dts_back = '<%=request.getParameter("so_dts")%>',
               so_ltt_back = '<%=request.getParameter("so_ltt")%>',
               loai_ltt_back = '<%=request.getParameter("loai_ltt")%>',
               loai_tra_soat_back = '<%=request.getParameter("loai_tra_soat")%>',
               chieu_tra_soat_back = '<%=request.getParameter("chieu_tra_soat")%>';
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
          }
            //ltt den
           if(nhkb_nhan_ltt_field.val()=='<%=request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION)%>')
            window.location.href='listLttDenAdd.do?action=VIEW_LTTDen&so_ltt_details='+ltt_id_field.val()+'&referrer=dtsoatlttdiView'+urlBack;
           else
           //ltt di
           window.location.href='listLttAdd.do?action=VIEW_LTTDi&so_ltt_details='+ltt_id_field.val()+'&referrer=dtsoatlttdiView'+urlBack;
         });
          //**************************BUTTON Tim kiem CLICK********************************************
         jQuery("#btn_timKiem").click(function() {
						document.forms[0].target='';
						evenButtonBeforeField.val('<%=AppConstants.ACTION_FIND%>');
            jQuery("#nh_kb_nhan").val('');
            jQuery("#so_dts").val('');
            jQuery("#so_lenh_tt").val('');
            if(role.indexOf('<%=AppConstants.NSD_TTV%>')>=0){
              jQuery("#ma_ttv").hide();
							jQuery("#ma_ttvnhan").val('');
							jQuery("#dialog-form" ).dialog( "open" );
							jQuery("#nh_kb_nhan").focus();
              
            }else if(role.indexOf('<%=AppConstants.NSD_KTT%>')>=0){
             jQuery("#dialog-form" ).dialog( "open" );
             jQuery("#ma_ttvnhan").show().val('').focus();
						}
              
         });
         //**************************BUTTON TRA CUU DTS CLICK********************************************
        jQuery("#btn_tra_cuu_dts").click(function() {
          document.forms[0].target='';
           frmXyLyDTSLTT.attr({'action':'tracuudts.do'});
           frmXyLyDTSLTT.submit()
        });
        
        //********************** EXCUTE UPDATE - DELETE ***********************************  
        /*
         * action update excute
         */
       
        function updateExcuteDTS(action,rowSelected){
          var certSerialFieldValue,contenDataFieldValue,signatureFieldValue;
           if (action=='<%=AppConstants.ACTION_APPROVED%>'){
             certSerialFieldValue=certSerialField.val();            
             signatureFieldValue=signatureField.val();
             contenDataFieldValue=jQuery("#noi_dung_ky").val();
           }
           jQuery.ajax({
             type: "POST",
             url: "updatedtsExc.do",	
             data: {"action" : action,
                    "noidung" : noi_dung_field.val(),
                    "thong_tin_khac" : thong_tin_khac_field.val(),
                    "lydo_ktt_day_lai" : lydo_ktt_day_lai_field.val(),
                    "id" : id_field.val(),
                    "trang_thai" : trang_thai_field.val(),
                    "signature" : signatureFieldValue,
                    "certSerial" : certSerialFieldValue,
                    "noi_dung_ky" : contenDataFieldValue,
                    "nd" : Math.random() * 100000},
             dataType:'json',
             success: function(data,textstatus){
                 if(textstatus!=null && textstatus=='<%=AppConstants.SUCCESS%>'){
                   if(data!=null){
                    if(data.ERROR ==undefined || (data.ERROR=='undefined' || data.ERROR=='')){
                      if(rowSelected!=null && rowSelected!='')
                        jQuery("#"+rowSelected).attr( {'class' : 'ui-widget-content ui-row-ltr'});
                      fillDataTableDST(data,contexRoot,role);
                      if(action=='<%=AppConstants.ACTION_EDIT%>')
                         dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.sua_thanh_cong"/>');
                      else if(action=='<%=AppConstants.ACTION_CANCEL%>')
                         dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.huy_thanh_cong"/>');
                      else if (action=='<%=AppConstants.ACTION_APPROVED%>')
                        dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.duyet_thanh_cong"/>');
                      else if (action=='<%=AppConstants.ACTION_REJECT%>')
                        dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.day_lai_thanh_cong"/>');
                     disableButton();
                     resetFormDTS();
                    }else if(data.ERROR!=null && data.ERROR=='TTSP-ERROR-0001'){
                      dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.trang_thai_thay_doi"/>');
                    }else if(data.ERROR!=null && data.ERROR=='TTSP-ERROR-0002'){
                      dialog_message.html('<fmt:message key="dtsoat_ltt_di.error.ttsp_error_0002"/>');
                    }else if(data.ERROR!=null && data.ERROR=='TTSP-ERROR-0003'){
                      dialog_message.html('<fmt:message key="dtsoat_ltt_di.error.ttsp_error_0003"/>');
                    }else if(data.ERROR!=null && data.ERROR=='<%=AppConstants.FAILURE%>'){
                      dialog_message.html('<fmt:message key="dtsoat_ltt_di.error.ttsp_error_0004"/>');
                    }else{
                      dialog_message.html(data.ERROR);
                    }
                    dialog_message.dialog( "open" );
                 }
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
      }
      else{
        jQuery("#row_dts_0").removeAttr("onclick");
        disableButtonForFind();
      }
	
      
    });
    // In
    function formAction(){
      if(jQuery("#id").val()=='' || jQuery("#id").val()==undefined){
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
        
            var noi_dung_ky = jQuery("#noi_dung_ky").val();

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
        <table border=0 cellSpacing=0 cellPadding=0 width="100%"  align=center>
          <tbody>
            <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="dtsoat_ltt_di.page.title"/></span>
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
                <tr>
              <td>
                  <input type="text" class="fieldTextTransError" readonly="readonly" id="error_desc"/>
              </td>
            </tr>
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
                                    <fmt:message key="dtsoat_ltt_di.fieldset.title.so_dien_tra_soat"/>
                                  </legend>
                                  <div class="sodientrasoattable">
                                    <div>
                                        <table class="data-grid" cellSpacing="0"
                                              cellPadding="0" width="100%">
                                          <thead>
                                            <tr>
                                              <th class="ui-state-default ui-th-column"
                                                   width="62%;">
                                               <fmt:message key="dtsoat_ltt_di.page.lable.so_dien"/>
                                              </th>
                                              <th height="20" width="28%"
                                                  class="ui-state-default ui-th-column">
                                               <fmt:message key="dtsoat_ltt_di.page.lable.tinh_trang"/>
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
                                        <div>
                                        <table class="data-grid" id="data-grid" style="BORDER-COLLAPSE: collapse;"  cellSpacing="0" cellPadding="0"  width="100%">
                                          <tbody>
                                            <logic:present name="listDTSoatLTTDi">
                                              <logic:iterate id="list_dts_ltt" name="listDTSoatLTTDi" indexId="index" >
                                              <tr style="width:100%;" class="ui-widget-content jqgrow ui-row-ltr"
                                                  id="row_dts_<bean:write name="index"/>"                                                   
                                                  ondblclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');" 
                                                  onclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');fillDataDTS('<bean:write name="list_dts_ltt" property="id"/>','row_dts_<bean:write name="index"/>','<%=request.getAttribute("chucdanh")%>',true);">
                                                <td width="40%;"  align="center" id="td_dts_<bean:write name="index"/>">
                                                  <input tabindex="100" name="row_item" id="<bean:write name="index"/>" 
                                                  type="text" class="ui-state-highlight" style="border:0px;font-size:10px;float:left;" value="<bean:write name="list_dts_ltt" property="id"/>" onkeydown="arrowUpDown(event)" readonly="true"/>
                                                </td>
                                                <td width="28%;" align="center" >
                                                  <logic:equal value="01" name="list_dts_ltt" property="trang_thai">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg" title="&#272;&#7849;y l&#7841;i"/>
                                                  </logic:equal>
                                                  <logic:equal value="02" name="list_dts_ltt" property="trang_thai">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" title="Ch&#7901; KTT duy&#7879;t"/>
                                                  </logic:equal>
                                                  <logic:equal value="03" name="list_dts_ltt" property="trang_thai">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" title="&#272;&#227; duy&#7879;t"/>
                                                  </logic:equal>
                                                  <logic:equal value="04" name="list_dts_ltt" property="trang_thai">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif" title="&#272;&#227; h&#7911;y"/>
                                                  </logic:equal>
                                                  <logic:equal value="18" property="trang_thai" name="list_dts_ltt">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/send-success.jpg" title="Gửi NH thành công"/>
                                                  </logic:equal>
                                                  <logic:equal value="19" property="trang_thai" name="list_dts_ltt">
                                                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif" title="Gửi NH không thành công"/>
                                                  </logic:equal>
                                                </td>
                                               
                                              </tr>
                                              </logic:iterate>
                                              <logic:empty name="listDTSoatLTTDi" >
                                                <tr>
                                                  <td colspan="5" align="center">
                                                    <span style="color:red;"><fmt:message key="dtsoat_ltt_di.error.not_found"/></span>
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
                                              <div class="ui-pg-div" style="float:left;"><span id="btn_Refresh" class="ui-icon ui-icon-refresh" onclick="refreshGrid('<%=request.getParameter("id")%>','<%=AppConstants.NNT_APP_CONTEXT_ROOT%>','<%=request.getAttribute("chucdanh")%>','<%=request.getParameter("action")%>');" style="cursor:pointer;"></span></div>
                                              <div class="ui-pg-div" style="float:left;"><span id="btn_timKiem" class="ui-icon ui-icon-search" title="Tìm kiếm" style="cursor:pointer;"></span></div>
                                              <div style="float:RIGHT;FONT-WEIGHT:NORMAL" class="ui-pg-div" id="tong_ban_ghi">
                                                <fmt:message key="dtsoat_ltt_di.page.lable.tong_ban_ghi">
                                                  <fmt:param value="<%=(((java.util.Collection)request.getAttribute(\"listDTSoatLTTDi\")).size())%>" />
                                                </fmt:message>
                                              </div>
                                            </td>
                                          </tr>
                                        </thead>
                                      </table>
                                      <div style="padding-top:10px;float: left;"><fmt:message key="dtsoat_ltt_di.page.lable.trang_thai"/>: <span id="mo_ta_trang_thai" style="font-weight:bold;"><%=request.getAttribute("mo_ta_trang_thai")%></span></div>
                                    </div>
                                  </div>
                                 </fieldset>
                                </div>
                             </td>
                             <td width="85%" valign="top">
                              <fieldset class="fieldsetCSS">
                                <legend style="vertical-align:middle">
                                  <fmt:message key="dtsoat_ltt_di.fieldset.title.thong_tin_chung"/>
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
                                <html:form styleId="frmDTS" action="/dtsoatlttdiView.do">
                                  <%if(request.getAttribute("chucdanh").toString().indexOf(AppConstants.NSD_KTT) >=0){%>
                                   <object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
                                  <%}%>
                                  <html:hidden property="search_dts" value="true"/>
                                  <html:hidden property="id" styleId="id"/>
                                  <html:hidden property="nhkb_nhan" styleId="nhkb_nhan"/>
                                  <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen"/>
                                  <html:hidden property="trang_thai" styleId="trang_thai"/>
                                  <html:hidden property="ttv_nhan" styleId="ttv_nhan"/>
                                  <table style="BORDER-COLLAPSE: collapse;" border="1" cellSpacing="0" borderColor="#b0c4de" cellPadding="0" width="99%">
                                   <tbody>
                                      <tr>
                                        <td align="right" width="15%">
                                          <label for="ltt_id"><fmt:message key="dtsoat_ltt_di.page.lable.so_lenh_thanh_toan" /></label>
                                        </td>
                                        <td colspan="3" align="left" class="box_common_content" width="34%">
                                          <html:text style="width:98%" tabindex="101" property="ltt_id" styleId="ltt_id" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                                        </td>
                                        <td align="right" width="16%">
                                          <label for="ngay_thanh_toan"><fmt:message key="dtsoat_ltt_di.page.lable.ngay_thanh_toan"/></label>
                                        </td>
                                        <td colspan="3" align="left">
                                          <html:text style="width:98%" tabindex="102" property="ngay_thanh_toan" styleId="ngay_thanh_toan" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" />
                                        </td>
                                      </tr>
                                      <tr>
                                        <td align=right >
                                          <label for="nhkb_chuyen"><fmt:message key="dtsoat_ltt_di.page.lable.don_vi_tra_soat"/></label>
                                        </td>
                                        <td width="8%">
                                          <html:text style="width:92%" tabindex="103" property="ma_don_vi_tra_soat" styleId="ma_don_vi_tra_soat" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                                        </td>
                                        <td align="left" colspan="2">
                                          <!--<label for="ten_don_vi_tra_soat"><fmt:message key="dtsoat_ltt_di.page.lable.ten"/></label>:-->
                                          <html:text  property="ten_don_vi_tra_soat" styleId="ten_don_vi_tra_soat" styleClass="fieldText" readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" style="font-weight:bold;width:97.5%" />
                                        </td>
                                        <td align=right>
                                          <label for="ma_don_vi_tra_soat"><fmt:message key="dtsoat_ltt_di.page.lable.don_vi_nhan_tra_soat"/></label>
                                        </td>
                                        <td width="8%">
                                          <html:text style="width:92%" tabindex="104" property="ma_don_vi_nhan_tra_soat" styleId="ma_don_vi_nhan_tra_soat" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                                        </td>
                                        <td align="left" colspan="2">
                                          <!--<label for="ten_don_vi_nhan_tra_soat"><fmt:message key="dtsoat_ltt_di.page.lable.ten"/>:</label>-->
                                          <html:text property="ten_don_vi_nhan_tra_soat" styleId="ten_don_vi_nhan_tra_soat" styleClass="fieldText" readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" style="font-weight:bold;width:98%"/>
                                          <!--<b><span id="ten_don_vi_nhan_tra_soat"><%=request.getAttribute("ten_don_vi_nhan_tra_soat")!=null?request.getAttribute("ten_don_vi_nhan_tra_soat"):""%></span></b>-->
                                        </td>
                                      </tr>
                                      <tr>
                                        <td align=right>
                                          <label for="ttv_nhan"><fmt:message key="dtsoat_ltt_di.page.lable.nguoi_lap"/></label>
                                        </td>
                                        <td align="left" colspan="3">
                                          <html:text style="width:98%" tabindex="105" property="ma_nsd" styleId="ma_nsd" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                        </td>
                                        <td align=right>
                                          <label for="ngay_nhan"><fmt:message key="dtsoat_ltt_di.page.lable.ngay_lap"/></label>
                                        </td>
                                        <td align="left" colspan="3">
                                          <html:text property="ngay_nhan" tabindex="106" style="width:98%" styleId="ngay_nhan" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td align=right>
                                          <label for="ma_ktt"><fmt:message key="dtsoat_ltt_di.page.lable.ktt"/></label>
                                        </td>
                                        <td align="left" colspan="3">
                                          <html:text style="width:98%" tabindex="107" property="ma_ktt" styleId="ma_ktt" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                        </td>
                                        <td align=right>
                                          <label for="ngay_duyet"><fmt:message key="dtsoat_ltt_di.page.lable.ngay_kiem_soat"/></label>
                                        </td>
                                        <td align="left" colspan="3">
                                          <html:text property="ngay_duyet" tabindex="108" style="width:98%" styleId="ngay_duyet" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                                        </td>
                                      </tr>
                                      <tr>
                                        <td align=right>
                                          <label for="noi_dung"><fmt:message key="dtsoat_ltt_di.page.lable.noi_dung_tra_soat"/></label>
                                        </td>
                                        <td colspan="7">
                                          <html:textarea property="noidung" tabindex="109"  styleId="noi_dung" cols="3" rows="2" style="width:99%;" styleClass="fieldTextArea" disabled="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" onkeydown="if(event.keyCode==13) event.keyCode=9;"></html:textarea>
                                          <span style="color:#FF0000" id="word_count_noi_dung">
                                          <fmt:message key="dtsoat_ltt_di.page.lable.tong_so_ki_tu"/>
                                          <span id="display_count_noi_dung">210</span></span>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td align=right>
                                          <label for="thong_tin_khac"><fmt:message key="dtsoat_ltt_di.page.lable.thong_tin_khac"/></label>
                                        </td>
                                        <td colspan="7">
                                          <html:textarea property="thong_tin_khac" tabindex="110" styleId="thong_tin_khac" cols="3" rows="2" style="width:99%;" styleClass="fieldTextArea" disabled="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" onkeydown="if(event.keyCode==13) event.keyCode=9;"></html:textarea>
                                          <span style="color:#FF0000" id="word_count_thong_tin_khac">
                                          <fmt:message key="dtsoat_ltt_di.page.lable.tong_so_ki_tu"/>
                                          <span id="display_count_thong_tin_khac">2000</span></span>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td align=right>
                                          <label for="lydo_ktt_day_lai"><fmt:message key="dtsoat_ltt_di.page.lable.ktt_day_lai"/></label>
                                        </td>
                                        <td colspan="7">
                                          <html:textarea property="lydo_ktt_day_lai" tabindex="111"  styleId="lydo_ktt_day_lai" cols="3" rows="2" style="width:99%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()" onkeydown="forcusButtonDayLai(event);arrowUpDown(event)"></html:textarea>
                                          <span style="color:#FF0000" id="word_count_lydo_ktt_day_lai">
                                          <fmt:message key="dtsoat_ltt_di.page.lable.tong_so_ki_tu"/>
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
                                <div style="float:right;">
                                  <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%"                >
                                     <tr align="right">
                                      <td>
                                           <span id="sua">
                                            <button tabindex="112" id=btn_sua accessKey=A>
                                              <fmt:message key="dtsoat_ltt_di.page.button.edit"/>
                                            </button>
                                            </span>
                                            <span id="them"> 
                                             <button id=btn_ghi tabindex="112" accessKey=G class=buttonCommon>
                                              <fmt:message key="dtsoat_ltt_di.page.button.save"/>
                                            </button>
                                            </span>
                                            <span id="huy"> 
                                            <button id=btn_huy accessKey=H class=buttonCommon>
                                              <fmt:message key="dtsoat_ltt_di.page.button.cancel"/>
                                            </button>
                                            </span>
                                            <span id="duyet"> 
                                            <button id=btn_duyet tabindex="112" accessKey=D class=buttonCommon>
                                              <fmt:message key="dtsoat_ltt_di.page.button.approved"/>
                                            </button>
                                            </span>
                                            <span id="daylai"> 
                                            <button id=btn_dayLai tabindex="112" accessKey=L  class=buttonCommon>
                                              <fmt:message key="dtsoat_ltt_di.page.button.move_again"/>
                                            </button>
                                            </span>
                                            <span id="xem"> 
                                            <button id="btn_xemLTT" tabindex="112" accessKey=X class=buttonCommon>
                                              <fmt:message key="dtsoat_ltt_di.page.button.view_ltt"/>
                                            </button>
                                            </span>
                                            <span id="in"> 
                                            <button id="btn_printLTT" tabindex="112" onclick="formAction();" class=buttonCommon>
                                              <fmt:message key="dtsoat_ltt_di.page.button.print"/>
                                            </button>
                                            </span>
                                           <!--<span id="timKiem">           
                                              <button id="btn_timKiem" accessKey=I class=buttonCommon>
                                                <fmt:message key="dtsoat_ltt_di.page.button.find"/>
                                              </button>
                                            </span>-->
                                            <span id="thoat"> 
                                              <button id="btn_thoat" tabindex="112" accessKey=o  class=buttonCommon>
                                                <fmt:message key="dtsoat_ltt_di.page.button.exit"/>
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
        
      
    <div id="dialog-message" title="<fmt:message key="dtsoat_ltt_di.page.title.dialog_message"/>">
        <p>
          <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
          <span id="message"></span>
        </p>
    </div>
   <div id="dialog-form" title="<fmt:message key="dtsoat_ltt_di.page.title.dialog_form"/>">
        <p class="validateTips"></p>
          <form id="tra-cuu-dts">
              <div id=ma_ttv style="padding-top:10px;">
                <label for="ma_ttv" style="padding-left:60px;">
                  <fmt:message key="dtsoat_ltt_di.page.lable.ma_ttv"/>
                </label>
                <input type="text" name="ma_ttv" id="ma_ttvnhan" class=" text ui-widget-content ui-corner-all" onkeydown="if(event.keyCode==13) event.keyCode=9;" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
              </div>
              <div style="padding-top:10px;">
                <label for="nh_bk_nhan" style="padding-left:30px;">
                  <fmt:message key="dtsoat_ltt_di.page.lable.nh_bk_nhan"/>
                </label>
                <input type="text" name="nh_bk_nhan" id="nh_kb_nhan" class=" text ui-widget-content ui-corner-all" onkeydown="if(event.keyCode==13) event.keyCode=9;" onKeyPress="return numbersonly(this,event,true)" maxlength="8" onmouseover="Tip(this.value)" onmouseout="UnTip()"/> 
              </div>
              <div style="padding-top:10px;">
                <label for="so_dts" style="padding-left:16px;">
                  <fmt:message key="dtsoat_ltt_di.page.lable.so_dts" />
                </label>
                <input type="text" name="so_dts" id="so_dts" class=" text ui-widget-content ui-corner-all" onkeydown="if(event.keyCode==13) event.keyCode=9;" onKeyPress="return numbersonly(this,event,true)" onmouseover="Tip(this.value)" onmouseout="UnTip()"/> 
              </div>
              <div style="padding-top:10px;">
                <label for="so_lenh_tt" style="padding-left:40px;">
                  <fmt:message key="dtsoat_ltt_di.page.lable.so_lenh_tt"/>
                </label>
                <input type="text" name="so_lenh_tt" id="so_lenh_tt" class=" text ui-widget-content ui-corner-all" onkeydown="if(event.keyCode==13) event.keyCode=9;" onKeyPress="return numbersonly(this,event,true)" onmouseover="Tip(this.value)" onmouseout="UnTip()"/> 
              </div>
          </form>
      </div>
     
      </div>
      <div id="dialog-confirm" title="<fmt:message key="dtsoat_ltt_di.page.title.dialog_confirm"/>" >
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
          <span id="message_confirm"></span>
        </p>
      </div>

  <%@ include file="/includes/ttsp_bottom.inc"%>
