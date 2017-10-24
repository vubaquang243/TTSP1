<%@ page contentType="text/html; charset=UTF-8"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/xu.ly.dtsoat.hoi.NHTM.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.NhapDienTraSoatTraLoi"/>
 <script type="text/javascript">
  //************************************ LOAD PAGE **********************************
     jQuery(document).ready(function()
      {
        //KHAI BAO BIEN
          var thong_tin_khac_field =  jQuery("#thong_tin_khac"),
    			 		noi_dung_field =  jQuery("#noi_dung"),
              lydo_ktt_day_lai_field =  jQuery("#lydo_ktt_day_lai"),
							dialog_message = jQuery("#dialog-message"),
							focus_field = jQuery("#focusField"),
              id_field=jQuery("#id"),
              trang_thai_field=jQuery('#trang_thai'),
              ttv_nhan_field=jQuery("#ttv_nhan"),
              ngay_nhan_field=jQuery("#ngay_nhan"),
              dialog_confirm =  jQuery("#dialog-confirm" ),
							evenButtonBefore = jQuery("#evenButtonBefore"),
							ltt_id_field =jQuery("#ltt_id" ),
              nhkb_nhan_field=jQuery("#nhkb_nhan" ),
              nhkb_chuyen_field=jQuery("#nhkb_chuyen" ),
              frmNhapDTSoatTraLoi=jQuery("#frmNhapDTSoatTraLoi"),
              noidung_traloi_field=jQuery("#noidung_traloi"),
              nguoi_nhap_nh_field=jQuery("#nguoi_nhap_nh"),
              ngay_nhap_nh_field=jQuery("#ngay_nhap_nh"),
              nguoi_ks_nh_field=jQuery("#nguoi_ks_nh"),
              ngay_ks_nh_field=jQuery("#ngay_ks_nh"),
              loai_dts_field=jQuery("#loai_dts");
							
        
        jQuery("#dialog:ui-dialog").dialog( "destroy" );
       
        noidung_traloi_field.keyup(function(){
          limitChars(noidung_traloi_field.attr('id'),146);
        });
        
        //dialog message
        dialog_message.dialog({
          autoOpen: false,
					resizable: false,
          height:200,
          width:460,
          modal: true,
          buttons: {
            Ok: function() {
              evenButtonBefore.val('');
              frmNhapDTSoatTraLoi.attr({"action":'xulydtshoitunhtmView.do'});
              frmNhapDTSoatTraLoi.submit();
              dialog_message.dialog("close");
            }
					}	,
          close: function(event, ui) {
              var id_field_focus=focus_field.val();
              if(evenButtonBefore.val()!=null && evenButtonBefore.val()=='<%=AppConstants.SUCCESS%>'){                  
                  frmNhapDTSoatTraLoi.attr({"action":'xulydtshoitunhtmView.do'});
                  frmNhapDTSoatTraLoi.submit();
              }else{
                jQuery("#"+id_field_focus).focus();
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
                  dialog_confirm.dialog("close");
                  var action=evenButtonBefore.val();
                  if(action!=null && action=='<%=AppConstants.ACTION_ADD%>'){
                    addExcuteDTS();
                  }
                },
								"Không": function() {
									dialog_confirm.dialog("close");
                  evenButtonBefore.val('');
								}
							}
						});
						
				 //**************************BUTTON Ghi CLICK********************************************
        jQuery("#btn_ghi").click(function() {
            noidung_traloi_field.removeClass('ui-state-error');
            var bValid=true;
             if(noidung_traloi_field.val()==null || noidung_traloi_field.val()==''){
                dialog_message.html('<fmt:message key="nhap_dien_tra_loi_tra_soat.page.message.noi_dung_tra_loi_empty"/>');
                dialog_message.dialog( "open" );
                focus_field.val('noidung_traloi');
                noidung_traloi_field.addClass( "ui-state-error" );
                bValid=false;
              }
              if(bValid && noidung_traloi_field.val().length>146){
                dialog_message.html('<fmt:message key="nhap_dien_tra_loi_tra_soat.page.message.noi_dung_tra_loi_greater_than_146"/>');
                dialog_message.dialog( "open" );
                bValid=false;
                focusField.val('noidung_traloi');
                noidung_traloi_field.addClass( "ui-state-error" );
             
              }
              if(bValid){
               evenButtonBefore.val('<%=AppConstants.ACTION_ADD%>');
               dialog_confirm.html('<fmt:message key="nhap_dien_tra_loi_tra_soat.page.message_confirm.them_moi_dts"/>');
               dialog_confirm.dialog("open");
              }
          });
        //**************************BUTTON Thoat CLICK********************************************
         jQuery("#btn_thoat").click(function() {
            frmNhapDTSoatTraLoi.attr({"action":'xulydtshoitunhtmView.do'});
            frmNhapDTSoatTraLoi.submit();
         });
        //********************** EXCUTE ADD ***********************************  
        /*
         * action update excute
         */
         
        function addExcuteDTS(){
             jQuery.ajax({
                 type: "POST",
                 url: "traloidtsoathoitunhtm.do",	
                 data: {"id":id_field.val(),
                        "ltt_id":ltt_id_field.val(),
                        "dts_id":id_field.val(),
                        "nhkb_nhan":nhkb_nhan_field.val(),
                        "nhkb_chuyen" : nhkb_chuyen_field.val(),
                        "noidung":noi_dung_field.val(),
                        "noidung_traloi" : noidung_traloi_field.val(),
                        "thong_tin_khac":thong_tin_khac_field.val(),
                        "ttv_nhan" : ttv_nhan_field.val(),
                        "ngay_nhan" : ngay_nhan_field.val(),
                        "lydo_ktt_day_lai":lydo_ktt_day_lai_field.val(),
                        "nguoi_nhap_nh":nguoi_nhap_nh_field.val(),
                        "ngay_nhap_nh":ngay_nhap_nh_field.val(),
                        "nguoi_ks_nh":nguoi_ks_nh_field.val(),
                        "ngay_ks_nh":ngay_ks_nh_field.val(),
                        "loai_dts":loai_dts_field.val(),
                        "trang_thai":trang_thai_field.val(),
                        "nd" : Math.random() * 100000},
                 dataType:'json',
                 success: function(data,textstatus){
                     if(textstatus!=null && textstatus=='<%=AppConstants.SUCCESS%>'){
                        if(data!=null){
                          if(data.success!=undefined && data.success!='undefined'){
                             evenButtonBefore.val('<%=AppConstants.SUCCESS%>');
                             dialog_message.html('<fmt:message key="nhap_dien_tra_loi_tra_soat.page.message.tao_dien_tra_loi_thanh_cong"><fmt:param value="'+data.success+'"/></fmt:message>');
                             dialog_message.dialog("open");
                             
                          }else{
                            focus_field.val(data.ERROR);
                            if(data.ERROR!=null && data.ERROR=='TTSP-ERROR-0001'){
                              dialog_message.html('<fmt:message key="nhap_dien_tra_loi_tra_soat.page.message.trang_thai_thay_doi"/>');
                            }else if(data.ERROR!=null && data.ERROR=='TTSP-ERROR-0002'){
                              dialog_message.html('<fmt:message key="nhap_dien_tra_loi_tra_soat.error.ttsp_error_0002"/>');
                            }else if(data.ERROR!=null && data.ERROR=='TTSP-ERROR-0003'){
                              dialog_message.html('<fmt:message key="nhap_dien_tra_loi_tra_soat.error.ttsp_error_0003"/>');
                            }
                             dialog_message.dialog("open");
                          }
                        }
                     }
                     
                  },
                  error:function(xhr,status,error){
                    focus_field.val(status);
                    dialog_message.html(status +' ' + xhr.responseText);
                    dialog_message.dialog("open");
                 }
             });
          }
        //focus
          noidung_traloi_field.val('');
          noidung_traloi_field.focus();
      });
    </script>
    <div id="body">
        <table border=0 cellSpacing=0 cellPadding=0 width="100%"  align=center>
          <tbody>
            <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="nhap_dien_tra_loi_tra_soat.page.title.nhap_dien_tra_loi_tra_soat"/></span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
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
                  <fmt:message key="nhap_dien_tra_loi_tra_soat.fieldset.title.so_dien_tra_soat"/>
                </legend>
                <div style="padding:3px;">
                  <div style="width:193px;">
                      <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                        <thead>
                          <tr>
                            <th class="ui-state-default ui-th-column" height="20" width="46.5%;">
                             <fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.so_dien"/>
                            </th>
                            <th class="ui-state-default ui-th-column">
                             <fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.tinh_trang"/>
                            </th>
                            <th class="ui-state-default ui-th-column" width="14px;">&nbsp;</th>
                          </tr>
                        </thead>
                      </table>
                    </div>
                    <div class="ui-jqgrid-bdiv ui-widget-content" style="height: 230px; width:192px;background:#F0F0F0;"></div>
                    <div style="width:193px;">
                        <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                          <thead><tr><td class="ui-state-default ui-th-column">&nbsp;</td></tr></thead>
                        </table>
                    </div>
                </div>
               </fieldset>
              </div>
           </td>
           <td width="85%" valign="top">
             <fieldset class="fieldsetCSS">
              <legend style="vertical-align:middle">
                <fmt:message key="nhap_dien_tra_loi_tra_soat.fieldset.title.thong_tin_chung"/>
              </legend>
              <div style="padding:10px 5px 0px 5px;">
              <input type="hidden" id="focusField"/>
              <input type="hidden" id="evenButtonBefore"/>
              <html:form styleId="frmNhapDTSoatTraLoi"   action="/nhapdtsoattraloi.do">
                <html:hidden property="nhkb_nhan" styleId="nhkb_nhan"/>
                <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen"/>
                <html:hidden property="ttv_nhan" styleId="ttv_nhan"/>
                <html:hidden property="ktt_duyet" styleId="ktt_duyet"/>
                <html:hidden property="ngay_duyet" styleId="ngay_duyet"/>
                <html:hidden property="nguoi_nhap_nh" styleId="nguoi_nhap_nh"/>
                <html:hidden property="ngay_nhap_nh" styleId="ngay_nhap_nh"/>
                <html:hidden property="nguoi_ks_nh" styleId="nguoi_ks_nh"/>
                <html:hidden property="ngay_ks_nh" styleId="ngay_ks_nh"/>
                <html:hidden property="loai_dts" styleId="loai_dts"/>
                <html:hidden property="trang_thai" styleId="trang_thai"/>
                <table style="BORDER-COLLAPSE: collapse;"  border="1" cellSpacing=0 borderColor=#b0c4de cellPadding=0 width="99%">
                  <tbody>
                    <tr>
                      <td align="right">
                        <label for="id">
                          <fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.so_dts"/>
                        </label>
                      </td>
                      <td colspan="3">
                        <html:text property="id" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="id" styleClass="fieldTextCode"  readonly="true"/>
                      </td>
                    </tr>
                    <tr>
                      <td align="right" width="15%">
                        <label for="ltt_id"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.so_lenh_thanh_toan" /></label>
                      </td>
                      <td colspan="3" align="left" class="box_common_content" width="32%">
                        <html:text property="ltt_id" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="ltt_id" styleClass="fieldTextCode" readonly="true"/>
                      </td>
                      <td align="right" width="16%" >
                        <label for="ngay_thanh_toan"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.ngay_thanh_toan"/></label>
                      </td>
                      <td colspan="3" align="left">
                        <html:text property="ngay_thanh_toan" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="ngay_thanh_toan" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" />
                      </td>
                    </tr>
                    <tr>
                      <td align=right >
                        <label for="ma_don_vi_tra_soat"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.don_vi_tra_soat"/></label>
                      </td>
                      <td width="10%">
                        <html:text property="ma_don_vi_tra_soat" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="ma_don_vi_tra_soat" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                      </td>
                      <td align="left" colspan="2">
                         <html:text property="ten_don_vi_tra_soat" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="ten_don_vi_tra_soat" styleClass="fieldText" readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" style="font-weight:bold;" />
                      </td>
                      <td align=right>
                        <label for="ma_don_vi_nhan_tra_soat"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.don_vi_nhan_tra_soat"/></label>
                      </td>
                      <td width="10%">
                        <html:text property="ma_don_vi_nhan_tra_soat" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="ma_don_vi_nhan_tra_soat" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                      </td>
                      <td align="left" colspan="2">
                           <html:text property="ten_don_vi_nhan_tra_soat" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="ten_don_vi_nhan_tra_soat" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()"  style="font-weight:bold;"/>
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="ttv_nhan"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.nguoi_lap"/></label>
                      </td>
                      <td align="left" colspan="3">
                        <html:text property="ma_nsd" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="ma_nsd" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                      </td>
                      <td align=right>
                        <label for="ngay_nhan"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.ngay_lap"/></label>
                      </td>
                      <td align="left" colspan="3">
                        <html:text property="ngay_nhan" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="ngay_nhan" styleClass="fieldTextCode"  readonly="true" />
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="ma_ktt"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.ktt"/></label>
                      </td>
                      <td align="left" colspan="3">
                        <html:text property="ma_ktt" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="ma_ktt" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                      </td>
                      <td align=right>
                        <label for="ngay_duyet"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.ngay_kiem_soat"/></label>
                      </td>
                      <td align="left" colspan="3">
                        <html:text property="ngay_duyet" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="ngay_duyet" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="noi_dung"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.noi_dung_tra_soat"/></label>
                      </td>
                      <td colspan="7">
                        <html:textarea property="noidung"   onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="noi_dung" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" disabled="true"></html:textarea>
                        <span style="color:#FF0000" id="word_count_noi_dung">
                        <fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.tong_so_ki_tu"/>
                        <span id="display_count_noi_dung">146</span></span>
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="noidung_traloi"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.noi_dung_tra_loi"/></label>
                      </td>
                      <td colspan="7">
                        <html:textarea property="noidung_traloi" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="noidung_traloi" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea"></html:textarea>
                        <span style="color:#FF0000" id="word_count_noidung_traloi">
                        <fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.tong_so_ki_tu"/>
                        <span id="display_count_noidung_traloi">146</span></span>
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="thong_tin_khac"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.thong_tin_khac"/></label>
                      </td>
                      <td colspan="7">
                        <html:textarea property="thong_tin_khac" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="thong_tin_khac" disabled="true" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea"></html:textarea>
                        <span style="color:#FF0000" id="word_count_thong_tin_khac">
                        <fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.tong_so_ki_tu"/>
                        <span id="display_count_thong_tin_khac">146</span></span>
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="lydo_ktt_day_lai"><fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.ktt_day_lai"/></label>
                      </td>
                      <td colspan="7">
                        <html:textarea property="lydo_ktt_day_lai" onkeydown="if(event.keyCode==13) event.keyCode=9;"  styleId="lydo_ktt_day_lai" disabled="true" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea "></html:textarea>
                        <span style="color:#FF0000" id="word_count_lydo_ktt_day_lai">
                        <fmt:message key="nhap_dien_tra_loi_tra_soat.page.lable.tong_so_ki_tu"/>
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
              <div style="padding:10px 20px 0;float:right;">
                <span id="ghi" style="padding-left:10px;">
                  <button id=btn_ghi accessKey=I class=buttonCommon>
                    <fmt:message key="nhap_dien_tra_loi_tra_soat.page.button.save"/>
                  </button>
                </span>
                <span style="padding-left:10px;">
                  <button id="btn_thoat" accessKey=o  class=buttonCommon tabIndex=103>
                    <fmt:message key="nhap_dien_tra_loi_tra_soat.page.button.exit"/>
                  </button>
                </span>
                <span style="padding-left:10px;">
                  <input style="WIDTH: 1px; HEIGHT: 1px" type=hidden name=thebottom/>
                </span>
              </div>
            </td>
          </tr>
        </tbody>  
      </table>
    <div id="dialog-message" title="<fmt:message key="nhap_dien_tra_loi_tra_soat.page.title.dialog_message"/>">
        <p>
          <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
          <span id="message"></span>
        </p>
    </div>
    <div id="dialog-confirm" title="<fmt:message key="nhap_dien_tra_loi_tra_soat.page.title.dialog_confirm"/>" >
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
          <span id="message_confirm"></span>
        </p>
      </div>
  <%@ include file="/includes/ttsp_bottom.inc"%>
