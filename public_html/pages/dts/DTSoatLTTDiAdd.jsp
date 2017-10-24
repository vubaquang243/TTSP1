<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dien.tra.soat.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.DTSoatLTTDiResource"/>
 
    <div id="body">
        <table border=0 cellSpacing=0 cellPadding=0 width="100%"  align=center>
          <tbody>
            <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="dtsoat_ltt_di.page.title_add"/></span>
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
                  <fmt:message key="dtsoat_ltt_di.fieldset.title.so_dien_tra_soat"/>
                </legend>
                <div style="padding:3px;">
                  <div style="width:193px;">
                      <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                        <thead>
                          <tr>
                            <th class="ui-state-default ui-th-column" height="20" width="46.5%;">
                             <fmt:message key="dtsoat_ltt_di.page.lable.so_dien"/>
                            </th>
                            <th class="ui-state-default ui-th-column">
                             <fmt:message key="dtsoat_ltt_di.page.lable.tinh_trang"/>
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
                <fmt:message key="dtsoat_ltt_di.fieldset.title.thong_tin_chung"/>
              </legend>
              <div style="padding:10px 5px 0px 5px;">
              <input type="hidden" id="focusField"/>
              <input type="hidden" id="evenButtonBefore"/>
                <html:form styleId="frmDTSAdd"   action="/dtsoatlttdiView.do">

                <html:hidden property="nhkb_nhan" styleId="nhkb_nhan"/>
                <html:hidden property="nhkb_nhan_ltt" styleId="nhkb_nhan_ltt"/>
                <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen"/>
                <html:hidden property="ttv_nhan" styleId="ttv_nhan"/>
                <table style="BORDER-COLLAPSE: collapse;"  border="1" cellSpacing=0 borderColor=#b0c4de cellPadding=0 width="99%">
                  <tbody>
                    <tr>
                      <td align="right" width="15%">
                        <label for="ltt_id"><fmt:message key="dtsoat_ltt_di.page.lable.so_lenh_thanh_toan" /></label>
                      </td>
                      <td colspan="3" align="left" class="box_common_content" width="32%">
                        <html:text property="ltt_id" styleId="ltt_id" styleClass="fieldText" style="width:160px;" onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                        <html:link action="TraCuuLTT.do?targetid=dts"><img border="0" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/select.gif"/></html:link>
                      </td>
                      <td align="right" width="16%" >
                        <label for="ngay_thanh_toan"><fmt:message key="dtsoat_ltt_di.page.lable.ngay_thanh_toan"/></label>
                      </td>
                      <td colspan="3" align="left">
                        <html:text property="ngay_thanh_toan" styleId="ngay_thanh_toan" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" />
                      </td>
                    </tr>
                    <tr>
                      <td align=right >
                        <label for="nhkb_chuyen"><fmt:message key="dtsoat_ltt_di.page.lable.don_vi_tra_soat"/></label>
                      </td>
                      <td width="10%">
                        <html:text property="ma_don_vi_tra_soat" styleId="ma_don_vi_tra_soat" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                      </td>
                      <td align="left" colspan="2">
                         <html:text property="ten_don_vi_tra_soat" styleId="ten_don_vi_tra_soat" styleClass="fieldText" style="font-weight:bold;" onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                      </td>
                      <td align=right>
                        <label for="ma_don_vi_nhan_tra_soat"><fmt:message key="dtsoat_ltt_di.page.lable.don_vi_nhan_tra_soat"/></label>
                      </td>
                      <td width="10%">
                        <html:text property="ma_don_vi_nhan_tra_soat" styleId="ma_don_vi_nhan_tra_soat" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                      </td>
                      <td align="left" colspan="2">
                        <html:text property="ten_don_vi_nhan_tra_soat" styleId="ten_don_vi_nhan_tra_soat" styleClass="fieldText" style="font-weight:bold;" onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="ttv_nhan"><fmt:message key="dtsoat_ltt_di.page.lable.nguoi_lap"/></label>
                      </td>
                      <td align="left" colspan="3">
                        <html:text property="ma_nsd" styleId="ma_nsd" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                      </td>
                      <td align=right>
                        <label for="ngay_nhan"><fmt:message key="dtsoat_ltt_di.page.lable.ngay_lap"/></label>
                      </td>
                      <td align="left" colspan="3">
                        <html:text property="ngay_nhan" styleId="ngay_nhan" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" />
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="nguoi_ks_nh"><fmt:message key="dtsoat_ltt_di.page.lable.ktt"/></label>
                      </td>
                      <td align="left" colspan="3">
                        <html:text property="nguoi_ks_nh" styleId="nguoi_ks_nh" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                      </td>
                      <td align=right>
                        <label for="ngay_ks_nh"><fmt:message key="dtsoat_ltt_di.page.lable.ngay_kiem_soat"/></label>
                      </td>
                      <td align="left" colspan="3">
                        <html:text property="ngay_ks_nh" styleId="ngay_ks_nh" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true" />
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="noi_dung"><fmt:message key="dtsoat_ltt_di.page.lable.noi_dung_tra_soat"/></label>
                      </td>
                      <td colspan="7">
                        <html:textarea property="noidung"  onkeydown="if(event.keyCode==13) event.keyCode=9;" styleId="noi_dung" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()" ></html:textarea>
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
                        <html:textarea property="thong_tin_khac" onkeydown="if(event.keyCode==13) event.keyCode=9;" styleId="thong_tin_khac" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea" onmouseover="Tip(this.value)" onmouseout="UnTip()" ></html:textarea>
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
                        <html:textarea property="lydo_ktt_day_lai" onkeydown="if(event.keyCode==13) event.keyCode=9;" styleId="lydo_ktt_day_lai" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea " disabled="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" ></html:textarea>
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
              <div style="padding:10px 20px 0;float:right;">
                <button id=btn_ghi  type="button" accessKey=G class=buttonCommon style="width:80px;">
                <fmt:message key="dtsoat_ltt_di.page.button.save"/>
                </button>
                <button id="btn_xemLTT" accessKey=X class=buttonCommon style="width:80px;">
                <fmt:message key="dtsoat_ltt_di.page.button.view_ltt"/>
                </button>
                <button id="btn_thoat" accessKey=O  class=buttonCommon  style="width:80px;">
                <fmt:message key="dtsoat_ltt_di.page.button.exit"/>
                </button>
                <input style="WIDTH: 1px; HEIGHT: 1px" type=hidden name=thebottom/>
              </div>
            </td>
          </tr>
        </tbody>  
      </table>
<div id="dialog-message" title="<fmt:message key="dtsoat_ltt_di.page.title.dialog_message"/>">
        <p>
          <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
          <span id="message"></span>
        </p>
    </div>
    <div id="dialog-confirm" title="<fmt:message key="dtsoat_ltt_di.page.title.dialog_confirm"/>" >
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
          <span id="message_confirm"></span>
        </p>
      </div>
<script type="text/javascript">
  //************************************ LOAD PAGE **********************************
     jQuery.noConflict();
     jQuery(document).ready(function()
      {
        //KHAI BAO BIEN
          var thong_tin_khac_field =  jQuery("#thong_tin_khac"),
    			 		noi_dung_field =  jQuery("#noi_dung"),
              lydo_ktt_day_lai =  jQuery("#lydo_ktt_day_lai"),
							dialog_message = jQuery("#dialog-message"),
							focus_field = jQuery("#focusField"),
              ttv_nhan_field=jQuery("#ttv_nhan"),
              dialog_confirm =  jQuery("#dialog-confirm" ),
							evenButtonBefore = jQuery("#evenButtonBefore"),
							ltt_id_field =jQuery("#ltt_id" ),
              nhkb_nhan_field=jQuery("#nhkb_nhan"),
              nhkb_chuyen_field=jQuery("#nhkb_chuyen" ),
              ngay_nhan_field=jQuery("#ngay_nhan" ),
              ma_don_vi_nhan_tra_soat_field=jQuery("#ma_don_vi_nhan_tra_soat"),
              ten_don_vi_tra_soat_field=jQuery("#ten_don_vi_tra_soat"),
              ten_don_vi_nhan_tra_soat_field=jQuery("#ten_don_vi_nhan_tra_soat"),
              ngay_thanh_toan_field=jQuery("#ngay_thanh_toan"),
              ma_don_vi_tra_soat_field=jQuery("#ma_don_vi_tra_soat"),
              ma_nsd_field=jQuery("#ma_nsd"),
              nhkb_nhan_ltt_field=jQuery("#nhkb_nhan_ltt"),
							allFields = jQuery([]).add( thong_tin_khac_field ).add( noi_dung_field ).add( lydo_ktt_day_lai )
              .add(ltt_id_field ).add(ngay_nhan_field).add(nhkb_nhan_field).add(nhkb_chuyen_field).add(ttv_nhan_field)
              .add(ma_don_vi_nhan_tra_soat_field).add(ten_don_vi_tra_soat_field).add(ngay_thanh_toan_field)
              .add(ma_don_vi_tra_soat_field).add(ma_nsd_field).add(ten_don_vi_nhan_tra_soat_field);
            
        jQuery("#dialog:ui-dialog").dialog( "destroy" );
        thong_tin_khac_field.keyup(function(){
          limitChars(thong_tin_khac_field.attr('id'),2000);
        });
        noi_dung_field.val('');
        noi_dung_field.focus();
        noi_dung_field.keyup(function(){
          limitChars(noi_dung_field.attr('id'),210);
        });
        lydo_ktt_day_lai.keyup(function(){
          limitChars(noi_dung_field.attr('id'),146);
        });
        
       //dialog message
        dialog_message.dialog({
          autoOpen: false,
          height:200,
          resizable: false,
          width:380,
          modal: true,
          buttons: {
            Ok: function() {
              dialog_message.dialog("close");
            }
         },
         close:function(){
            var id_field_focus=focus_field.val();
              if(id_field_focus==null || id_field_focus==''){
                  allFields.val("")
                 //quay ve man hinh tra cuu ltt
                  jQuery("#frmDTSAdd").attr({action:'TraCuuLTT.do?targetid=dts'});
                  jQuery("#frmDTSAdd").submit();
              }else
                 jQuery("#"+id_field_focus).focus();
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
                  dialog_confirm.dialog( "close" );
                  var action=evenButtonBefore.val();
                  if(action!=null && action=='<%=AppConstants.ACTION_ADD%>'){
                    addExcuteDTS();
                    allFields.val("");
                  }else{
                    allFields.val("");
                    //quay ve man hinh tra cuu ltt
                    if('<%=request.getParameter("referrer")%>'!='null'){
                    strLastAct='<%=request.getParameter("referrer")%>';
                      if(strLastAct=='thuchiendoichieu')
                    {
                    var tu_nh='<%=request.getParameter("nhkb_chuyen_cm")%>';
                    var ma_nh='<%=request.getParameter("ma_nhkb")%>';
                    jQuery("#frmDTSAdd").attr({action:'thuchiendoichieu.do?action=back&nhkb_chuyen_cm_back='+tu_nh+'&ma_nh_back='+ma_nh});
                    jQuery("#frmDTSAdd").submit();
                    }
                    }else
                    
                    jQuery("#frmDTSAdd").attr({action:'TraCuuLTT.do?targetid=dts'});
                    jQuery("#frmDTSAdd").submit();
                  }
                },
								"Không": function() {
                  evenButtonBefore.val('');
									jQuery(this).dialog("close");
								}
							}
						});
						
				 //**************************BUTTON Ghi CLICK********************************************
        jQuery("#btn_ghi").click(function() {
            allFields.removeClass('ui-state-error');
            var bValid=true;
             if(noi_dung_field.val()==null || noi_dung_field.val()==''){
                dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.noi_dung_empty"/>');
                dialog_message.dialog( "open" );
                focus_field.val('noi_dung');
                noi_dung_field.addClass( "ui-state-error" );
                bValid=false;
              }
             if(bValid && noi_dung_field.val().length>210){
                dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.noi_dung_greater_than_210"/>');
                dialog_message.dialog( "open" );
                bValid=false;
                focus_field.val('noi_dung');
                noi_dung_field.addClass( "ui-state-error" );
             }
              if(bValid && thong_tin_khac_field.val().length>2000){
                dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.thong_tin_khac_greater_than_2000"/>');
                dialog_message.dialog( "open" );
                bValid=false;
                focus_field.val('thong_tin_khac');
                thong_tin_khac_field.addClass("ui-state-error" );
             
              }
              if(bValid && thong_tin_khac_field.val().trim()==''){
                dialog_message.html("Bạn phải nhập vào nội dung khác.");
                dialog_message.dialog( "open" );
                bValid=false;
                focus_field.val('thong_tin_khac');
                thong_tin_khac_field.addClass("ui-state-error" );
             
              }
              if(bValid){
               evenButtonBefore.val('<%=AppConstants.ACTION_ADD%>');
               dialog_confirm.html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.them_moi_dts"/>');
               dialog_confirm.dialog("open");
              }
          });
        //**************************BUTTON xem lenh thanh toan CLICK********************************************
         jQuery("#btn_xemLTT").click(function() {
            //ltt den
           if(nhkb_nhan_ltt_field.val()=='<%=request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION)%>')
           {
             window.location.href ='listLttDenAdd.do?action=VIEW_LTTDen&so_ltt_details='+ltt_id_field.val()+'&referrer=themdtslttdi';
           }
           else{
           //ltt di
           window.location.href ='listLttAdd.do?action=VIEW_LTTDi&so_ltt_details='+ltt_id_field.val()+'&referrer=themdtslttdi';
           }
         });
        //**************************BUTTON Thoat CLICK********************************************
         jQuery("#btn_thoat").click(function() {
            var urlBack = "TraCuuLTT.do?targetid=dts&action=AddBack";
            var ttv_nhan_back= '<%=request.getParameter("ttv_nhan")%>';
            var trang_thai_back= '<%=request.getParameter("trang_thai")%>';
            var tu_ngay_back= '<%=request.getParameter("tu_ngay")%>';
            var den_ngay_back= '<%=request.getParameter("den_ngay")%>';
            var ma_nhkb_chuyen_cm_back= '<%=request.getParameter("ma_nhkb_chuyen_cm")%>';
            var ma_nhkb_nhan_cm_back= '<%=request.getParameter("ma_nhkb_nhan_cm")%>';
            var tong_sotien_back= '<%=request.getParameter("tong_sotien")%>';
            var so_yctt_back= '<%=request.getParameter("so_yctt")%>';
            var ltt_id_back= '<%=request.getParameter("ltt_id")%>';
            var loai_lenh_tt_back= '<%=request.getParameter("loai_lenh_tt")%>';
            if(ttv_nhan_back != null && ttv_nhan_back!='null' ){
             urlBack += "&ttv_nhan_back="+ttv_nhan_back+"";
            }
            if(trang_thai_back != null && trang_thai_back!='null' ){
             urlBack += "&trang_thai_back="+trang_thai_back+"";
            }
            if(tu_ngay_back != null && tu_ngay_back!='null' ){
             urlBack += "&tu_ngay_back="+tu_ngay_back+"";
            }
            if(den_ngay_back != null && den_ngay_back!='null' ){
             urlBack += "&den_ngay_back="+den_ngay_back+"";
            }
            if(ma_nhkb_chuyen_cm_back != null && ma_nhkb_chuyen_cm_back!='null' ){
             urlBack += "&ma_nhkb_chuyen_cm_back="+ma_nhkb_chuyen_cm_back+"";
            }
            if(ma_nhkb_nhan_cm_back != null && ma_nhkb_nhan_cm_back!='null' ){
             urlBack += "&ma_nhkb_nhan_cm_back="+ma_nhkb_nhan_cm_back+"";
            }
            if(tong_sotien_back != null &&tong_sotien_back!='null' ){
             urlBack += "&tong_sotien_back="+tong_sotien_back+"";
            }
            if(so_yctt_back != null &&so_yctt_back!='null' ){
             urlBack += "&so_yctt_back="+so_yctt_back+"";
            }
            if(ltt_id_back != null &&ltt_id_back!='null' ){
             urlBack += "&ltt_id_back="+ltt_id_back+"";
            }
            if(loai_lenh_tt_back != null &&loai_lenh_tt_back!='null' ){
             urlBack += "&loai_lenh_tt_back="+loai_lenh_tt_back+"";
            }
            document.forms[0].action = urlBack;
            document.forms[0].submit();
//            dialog_confirm.html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.thoat"/>');
//            dialog_confirm.dialog( "open" );
         });
        //********************** EXCUTE ADD ***********************************  
        /*
         * action update excute
         */
         
        function addExcuteDTS(){
             jQuery.ajax({
                 type: "POST",
                 url: "themdtslttdiExc.do",	
                 data: {"noidung" : noi_dung_field.val(),
                        "ltt_id" : ltt_id_field.val(),
                        "thong_tin_khac" : thong_tin_khac_field.val(),
                        "nhkb_nhan" : nhkb_nhan_field.val(),
                        "nhkb_chuyen" : nhkb_chuyen_field.val(),
                        "ttv_nhan" : ttv_nhan_field.val(),
                        "ngay_nhan" : ngay_nhan_field.val(),
                        "nd" : Math.random() * 100000},
                 dataType:'json',
                 success: function(data,textstatus){
                     if(textstatus!=null && textstatus=='<%=AppConstants.SUCCESS%>'){
                        focus_field.val('');
                        if(data.success!=undefined && data.success!='undefined'){
                           dialog_message.html('<fmt:message key="dtsoat_ltt_di.page.message.them_moi_thanh_cong"><fmt:param value="'+data.success+'"/></fmt:message>');
                        }else if(data.ERROR!=null && data.ERROR=='<%=AppConstants.FAILURE%>'){
                          dialog_message.html('<fmt:message key="dtsoat_ltt_di.error.ttsp_error_0004"/>');
                        }else{
                           dialog_message.html('<span style=\"color:red;\">'+data.ERROR+'</span>');
                        }
                        dialog_message.dialog( "open" );
                        
                     }
                     
                  },
                  error:function(xhr,status,error){
                    focus_field.val(status);
                    dialog_message.html(status+ xhr.responseText);
                    dialog_message.dialog( "open" );
                 }
             });
          }
      });
    </script>
  <%@ include file="/includes/ttsp_bottom.inc"%>
