<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds//struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds//struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds//struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds//fmt.tld" prefix="fmt" %>
<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dien.tra.soat.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.DTSoatLTtdiResource"/>

 <script type="text/javascript">
  //************************************ LOAD PAGE **********************************
     jQuery(document).ready(function()
      {
        var contectRoot='<%=request.getContextPath()%>';
        tableHighlightRow();
        jQuery("#dialog:ui-dialog").dialog( "destroy" );
         if('<%=request.getParameter("action")%>'=='null'){
          resetFormDTS();
          resetButtonByRoleUser('<%=request.getAttribute("chucdanh")%>');
         }else{
            resetButtonByRoleUser(null);
            jQuery("#noi_dung").attr({disabled:true});
            jQuery("#thong_tin_khac").attr({disabled:true});
            jQuery("#row_dts_0").attr({'class':'ui-row-ltr ui-state-highlight'});
            
            
          }
        jQuery("#thong_tin_khac").keyup(function(){
          limitChars(jQuery("#thong_tin_khac").attr('id'),146);
        });
        jQuery("#noi_dung").keyup(function(){
          limitChars(jQuery("#noi_dung").attr('id'),146);
        });
        jQuery("#lydo_ktt_day_lai").keyup(function(){
          limitChars(jQuery("#lydo_ktt_day_lai").attr('id'),146);
        });
        
        //dialog message
        jQuery( "#dialog-message").dialog({
          autoOpen: false,
          height:200,
          width:380,
          modal: true,
          buttons: {
            Ok: function() {
              jQuery(this).dialog( "close" );
              var idFieldFocus=jQuery("#focusField").val();
              if(idFieldFocus!=null && idFieldFocus!='')
                jQuery("#"+idFieldFocus).focus();
            }
         }
        });
        //dialog confirm message	
						jQuery("#dialog-confirm" ).dialog({
							autoOpen: false,
							resizable: false,
							height:200,
							width:380,
							modal: true,
							buttons: {
								"Có": function() {
                  var action=jQuery("#evenButtonBefore").val(),
                      rowSelected=jQuery("#rowSelected").val();
                 
                  if(rowSelected!=null && rowSelected!='')
                    jQuery("#"+rowSelected).attr( {'class' : 'ui-widget-content ui-row-ltr'});
                  if(action!=null && action!='<%=AppConstants.ACTION_EXIT%>'){
                      updateExcuteDTS(action);
                  }else if(action!=null && action=='<%=AppConstants.ACTION_EXIT%>'){
                    if('<%=request.getParameter("action")%>'=='null'){
                      resetFormDTS();
                      resetButtonByRoleUser('<%=request.getAttribute("chucdanh")%>');
                    }else{
                      jQuery("#frmDTS").attr({action:'<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/thoatView.do'});
                      jQuery("#frmDTS").target = '';
                      jQuery("#frmDTS").submit();
                    }
                    
                  }
                  
                  jQuery(this).dialog("close");
                  
                },
								"Không": function() {
									jQuery(this).dialog("close");
								}
							}
						});
						
				
        //dialog form Tra cuu dien tra soat
					jQuery("#dialog-form").dialog({
							autoOpen: false,
              modal: true,
							buttons: {
								"Tìm kiếm": function() {
                  findDTS();
                },
								"Thoát": function() {
									//resetForm();
									jQuery(this).dialog("close");
								}
							},
							"Đóng": function() {
							}
						});
            
          //dialog form Tra cuu lenh thanh toan
					jQuery("#dialog-form-tra-cuu-ltt").dialog({
							autoOpen: false,
							height: 400,
							width: 600,
							modal: true,
							buttons: {
								"Tra soát": function() {
                  findDTS();
                },
								"Thoát": function() {
									//resetForm();
									jQuery(this).dialog("close");
								}
							},
							"Đóng": function() {
							}
						});
         
          
      //**************************BUTTON Sua CLICK********************************************
        jQuery("#btn_sua").click(function() {
            jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_EDIT%>');
            if(selectedRowBeforeClickButton()){
              var trang_thai=jQuery("#trang_thai").val();
              if(trang_thai=='01' || trang_thai=='02' ){
                btnSuaClick();
              }else{
                jQuery("#dialog-message" ).html('<fmt:message key="dtsoat_ltt_di.page.message.not_edit_dts"/>');
                jQuery("#dialog-message" ).dialog( "open" );
              }
               
            }
          });
          
        //**************************BUTTON Ghi CLICK********************************************
        jQuery("#btn_ghi").click(function() {
            var bValid=true;
            var evenButtonBefore=jQuery("#evenButtonBefore").val();
            if(evenButtonBefore!=null && evenButtonBefore=='<%=AppConstants.ACTION_EDIT%>'){
              if(jQuery("#noi_dung").val()==null || jQuery("#noi_dung").val()==''){
                jQuery("#dialog-message" ).html('<fmt:message key="dtsoat_ltt_di.page.message.noi_dung_empty"/>');
                jQuery("#dialog-message" ).dialog( "open" );
                bValid=false;
                jQuery("#focusField").val('noi_dung');
                jQuery("#noi_dung").addClass( "ui-state-error" );
              }
              if(bValid){
               jQuery("#dialog-confirm" ).html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.sua_dts"/>');
               jQuery("#dialog-confirm" ).dialog("open");
              }
              
            }else if(evenButtonBefore!=null && evenButtonBefore=='<%=AppConstants.ACTION_REJECT%>'){
              if(jQuery("#lydo_ktt_day_lai").val()==null || jQuery("#lydo_ktt_day_lai").val()==''){
                jQuery("#dialog-message" ).html('<fmt:message key="dtsoat_ltt_di.page.message.lydo_ktt_day_lai_empty"/>');
                jQuery("#dialog-message" ).dialog( "open" );
                bValid=false;
                jQuery("#focusField").val('lydo_ktt_day_lai');
                jQuery("#lydo_ktt_day_lai").addClass( "ui-state-error" );
              }
              if(bValid){
               jQuery("#dialog-confirm" ).html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.day_lai_dts"/>');
               jQuery("#dialog-confirm" ).dialog("open");
              }
            }
          });
         //**************************BUTTON Huy CLICK********************************************
        jQuery("#btn_huy").click(function() {
            jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_CANCEL%>');
            if(selectedRowBeforeClickButton()){
               var trang_thai=jQuery("#trang_thai").val();
               if(trang_thai=='01'){
                  jQuery("#dialog-confirm" ).html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.huy_dts"/>');
                  jQuery("#dialog-confirm" ).dialog("open");
                }else{
                  jQuery("#dialog-message" ).html('<fmt:message key="dtsoat_ltt_di.page.message.not_cancel_dts"/>');
                  jQuery("#dialog-message" ).dialog( "open" );
                }
            }
        });
        //**************************BUTTON Duyet CLICK********************************************
        jQuery("#btn_duyet").click(function() {
          jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_APPROVED%>');
           if(selectedRowBeforeClickButton()){
              jQuery("#dialog-confirm" ).html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.duyet_dts"/>');
              jQuery("#dialog-confirm" ).dialog("open");
            }
        });
         //**************************BUTTON Day lai CLICK********************************************
        jQuery("#btn_dayLai").click(function() {
            jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_REJECT%>');
            if(selectedRowBeforeClickButton()){
                btnDayLaiClick();
            }
        });
        //**************************BUTTON Thoat CLICK********************************************
         jQuery("#btn_thoat").click(function() {
            jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_EXIT%>');
            jQuery("#dialog-confirm" ).html('<fmt:message key="dtsoat_ltt_di.page.message_confirm.thoat"/>');
            jQuery("#dialog-confirm" ).dialog( "open" );
         });
         
          //**************************BUTTON Tim kiem CLICK********************************************
         jQuery("#btn_timKiem").click(function() {
            jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_FIND%>');
            jQuery("#nhkb_nhan").val('');
            jQuery("#so_dts").val('');
            jQuery("#so_lenh_tt").val('');
            if('<%=request.getAttribute("chucdanh")%>'=='TTV'){
              jQuery("#ma_ttv").hide();
              jQuery("#ma_ttv").val('');
            }else
              jQuery("#ma_ttv").show();
             jQuery("#dialog-form" ).dialog( "open" );
         });
         //**************************BUTTON TRA CUU DTS CLICK********************************************
        jQuery("#btn_tra_cuu_dts").click(function() {
           jQuery('#frmDTS').attr({'action':'tracuudts.do'});
           jQuery('#frmDTS').submit()
        });
      });
      
        
     
  //********************** EXCUTE UPDATE - DELETE ***********************************  
  /*
   * action update excute
   */
   
    function updateExcuteDTS(action){
         jQuery.ajax({
             type: "POST",
             url: "updatedtsExc.do",	
             data: {"action" : action,
                    "noidung" : jQuery("#noi_dung").val(),
                    "thong_tin_khac" : jQuery("#thong_tin_khac").val(),
                    "id" : jQuery("#id").val(),
                    "trang_thai" : jQuery("#trang_thai").val(),
                    "nd" : Math.random() * 100000},
             dataType:'json',
             success: function(data,textstatus){
                 if(textstatus!=null && textstatus=='<%=AppConstants.SUCCESS%>'){
                    if(data.error!='undefined' && data.error!=''){
                      fillDataTableDST(data);
                      if(action=='<%=AppConstants.ACTION_EDIT%>')
                         jQuery("#dialog-message" ).html('<fmt:message key="dtsoat_ltt_di.page.message.sua_thanh_cong"/>');
                       else if(action=='<%=AppConstants.ACTION_CANCEL%>')
                         jQuery("#dialog-message" ).html('<fmt:message key="dtsoat_ltt_di.page.message.huy_thanh_cong"/>');
                       else if (action=='<%=AppConstants.ACTION_APPROVED%>')
                        jQuery("#dialog-message" ).html('<fmt:message key="dtsoat_ltt_di.page.message.duyet_thanh_cong"/>');
                        else if (action=='<%=AppConstants.ACTION_REJECT%>')
                        jQuery("#dialog-message" ).html('<fmt:message key="dtsoat_ltt_di.page.message.day_lai_thanh_cong"/>');
                         resetButtonByRoleUser('<%=request.getAttribute("chucdanh")%>');
                         resetFormDTS();
                       jQuery("#dialog-message" ).dialog( "open" );
                    }else if(data.error!=null && data.error=='TTSP_ERROR_0001'){
                    
                    }
                 }
                 
              },
              error:function(textstatus){
                jQuery("#dialog-message" ).html(textstatus);
                jQuery("#dialog-message" ).dialog( "open" );
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
                <span class=title2><fmt:message key="dtsoat_ltt_di.page.title"/></span>
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
                <div style="padding:3px;height:300px; ">
                  <div style="width:220px;">
                      <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                        <thead>
                          <tr>
                            <th class="ui-state-default ui-th-column" height="20" width="122px;">
                             <fmt:message key="dtsoat_ltt_di.page.lable.so_dien"/>
                            </th>
                            <th class="ui-state-default ui-th-column">
                             <fmt:message key="dtsoat_ltt_di.page.lable.tinh_trang"/>
                            </th>
                            <th class="ui-state-default ui-th-column" width="15px;">&nbsp;</th>
                          </tr>
                        </thead>
                      </table>
                    </div>
                    <div class="ui-jqgrid-bdiv ui-widget-content" style="height: 214px; width:220px;">
                      <table class="data-grid" id="data-grid" style="BORDER-COLLAPSE: collapse;"  cellSpacing="0" cellPadding="0"  width="100%">
                        <tbody>
                          <logic:present name="listDTSoatLTTDi">
                            <logic:iterate id="list_dts_ltt" name="listDTSoatLTTDi" indexId="index">
                            <tr class="ui-widget-content jqgrow ui-row-ltr"   id="row_dts_<bean:write name="index"/>" onclick="fillDataDTS('<bean:write name="list_dts_ltt" property="id"/>','row_dts_<bean:write name="index"/>');">
                              <td width="121px;"  align="center"><bean:write name="list_dts_ltt" property="id"/></td>
                              <td align="center" ><bean:write name="list_dts_ltt" property="trang_thai"/></td>
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
                   <div style="width:220px;">
                    <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                      <thead>
                        <tr>
                        
                          <td class="ui-state-default ui-th-column" title="Refresh" id="refresh">
                            <div class="ui-pg-div" style="float:left;"><span class="ui-icon ui-icon-refresh" onclick="refreshGrid('<%=request.getParameter("id")%>');" style="cursor:pointer;"></span></div>
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
            <div class="clearfix">
             <fieldset class="fieldsetCSS">
              <legend style="vertical-align:middle">
                <fmt:message key="dtsoat_ltt_di.fieldset.title.thong_tin_chung"/>
              </legend>
              <div style="padding:10px 5px 10px 5px;height:300px;">
              <input type="hidden" id="rowSelected"/>
              <input type="hidden" id="evenButtonBefore"/>
              <input type="hidden" id="focusField"/>
              <html:form styleId="frmDTS" action="/dtsoatlttdiView.do">
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
                      <td colspan="3" align="left" class="box_common_content" width="32%">
                        <html:text property="ltt_id" styleId="ltt_id" styleClass="fieldTextCode" readonly="true"/>
                      </td>
                      <td align="right" width="16%">
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
                      <td width="8%">
                        <html:text property="ma_don_vi_nhan_tra_soat" styleId="ma_don_vi_nhan_tra_soat" styleClass="fieldTextCode"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                      </td>
                      <td align="left" colspan="2">
                        <!--<label for="ten_don_vi_tra_soat"><fmt:message key="dtsoat_ltt_di.page.lable.ten"/></label>:-->
                        <b><span id="ten_don_vi_tra_soat"><%=request.getAttribute("ten_don_vi_tra_soat")!=null?request.getAttribute("ten_don_vi_tra_soat"):""%></span></b>
                      </td>
                      <td align=right>
                        <label for="ma_don_vi_tra_soat"><fmt:message key="dtsoat_ltt_di.page.lable.don_vi_nhan_tra_soat"/></label>
                      </td>
                      <td width="8%">
                        <html:text property="ma_don_vi_tra_soat" styleId="ma_don_vi_tra_soat" styleClass="fieldTextCode"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                      </td>
                      <td align="left" colspan="2" width="100px">
                        <!--<label for="ten_don_vi_nhan_tra_soat"><fmt:message key="dtsoat_ltt_di.page.lable.ten"/>:</label>-->
                        <b><span id="ten_don_vi_nhan_tra_soat"><%=request.getAttribute("ten_don_vi_nhan_tra_soat")!=null?request.getAttribute("ten_don_vi_nhan_tra_soat"):""%></span></b>
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
                        <html:text property="ngay_nhan" styleId="ngay_nhan" styleClass="fieldTextCode"  readonly="true" />
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
                        <html:textarea property="noidung"   styleId="noi_dung" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea"></html:textarea>
                        <span style="color:#FF0000" id="word_count_noi_dung">
                        <fmt:message key="dtsoat_ltt_di.page.lable.tong_so_ki_tu"/>
                        <span id="display_count_noi_dung">146</span></span>
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="thong_tin_khac"><fmt:message key="dtsoat_ltt_di.page.lable.thong_tin_khac"/></label>
                      </td>
                      <td colspan="7">
                        <html:textarea property="thong_tin_khac" styleId="thong_tin_khac" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea"></html:textarea>
                        <span style="color:#FF0000" id="word_count_thong_tin_khac">
                        <fmt:message key="dtsoat_ltt_di.page.lable.tong_so_ki_tu"/>
                        <span id="display_count_thong_tin_khac">146</span></span>
                      </td>
                    </tr>
                    <tr>
                      <td align=right>
                        <label for="lydo_ktt_day_lai"><fmt:message key="dtsoat_ltt_di.page.lable.ktt_day_lai"/></label>
                      </td>
                      <td colspan="7">
                        <html:textarea property="lydo_ktt_day_lai" styleId="lydo_ktt_day_lai" cols="3" rows="2" style="width:99.5%;" styleClass="fieldTextArea " disabled="true"></html:textarea>
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
            </div>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <div style="padding:20px 20px 10px;float:right;">
              <span id="tra_cuu_dts" style="padding-left:10px;">
                  <button id=btn_tra_cuu_dts accessKey=r=class=buttonCommon tabIndex=102>
                    T<span class="sortKey">r</span>a cuu DTS
                  </button>
                </span>
                <span id="sua" style="padding-left:10px;">
                  <button id=btn_sua accessKey=L=class=buttonCommon tabIndex=102>
                    <fmt:message key="dtsoat_ltt_di.page.button.edit"/>
                  </button>
                </span>
                <span id="ghi" style="padding-left:10px;">
                  <button id=btn_ghi accessKey=I class=buttonCommon>
                    <fmt:message key="dtsoat_ltt_di.page.button.save"/>
                  </button>
                </span>
                <span id="huy" style="padding-left:10px;">
                  <button id=btn_huy accessKey=H class=buttonCommon>
                    <fmt:message key="dtsoat_ltt_di.page.button.cancel"/>
                  </button>
                </span>
                <span id="duyet" style="padding-left:10px;">
                  <button id=btn_duyet  accessKey=K class=buttonCommon>
                    <fmt:message key="dtsoat_ltt_di.page.button.approved"/>
                  </button>
                </span>
                <span id="dayLai" style="padding-left:10px;">
                  <button id=btn_dayLai accessKey=o  class=buttonCommon tabIndex=103 >
                    <fmt:message key="dtsoat_ltt_di.page.button.move_again"/>
                  </button>
                </span>
                <!--<span id="xemLTT" style="padding-left:10px;">
                  <button  id="btn_xemLTT" accessKey=H class=buttonCommon>
                    <fmt:message key="dtsoat_ltt_di.page.button.view_ltt"/>
                  </button>
                </span>-->
                <span  id="timKiem" style="padding-left:10px;">
                  <button id="btn_timKiem"  accessKey=K class=buttonCommon>
                    <fmt:message key="dtsoat_ltt_di.page.button.find"/>
                  </button>
                </span>
                <span style="padding-left:10px;">
                  <button id="btn_thoat" accessKey=o  class=buttonCommon tabIndex=103>
                    <fmt:message key="dtsoat_ltt_di.page.button.exit"/>
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
                <input type="text" name="ma_ttv" id="ma_ttv" class=" text ui-widget-content ui-corner-all"/>
              </div>
              <div style="padding-top:10px;">
                <label for="nh_bk_nhan" style="padding-left:30px;">
                  <fmt:message key="dtsoat_ltt_di.page.lable.nh_bk_nhan"/>
                </label>
                <input type="text" name="nh_bk_nhan" id="nh_bk_nhan" class=" text ui-widget-content ui-corner-all"/> 
              </div>
              <div style="padding-top:10px;">
                <label for="so_dts" style="padding-left:16px;">
                  <fmt:message key="dtsoat_ltt_di.page.lable.so_dts" />
                </label>
                <input type="text" name="so_dts" id="so_dts" class=" text ui-widget-content ui-corner-all"/> 
              </div>
              <div style="padding-top:10px;">
                <label for="so_lenh_tt" style="padding-left:40px;">
                  <fmt:message key="dtsoat_ltt_di.page.lable.so_lenh_tt"/>
                </label>
                <input type="text" name="so_lenh_tt" id="so_lenh_tt" class=" text ui-widget-content ui-corner-all"/> 
              </div>
          </form>
      </div>
      <div id="dialog-form-tra-cuu-ltt" title="<fmt:message key="dtsoat_ltt_di.page.title.dialog_form"/>">
      <p class="validateTips"></p>
        <form id="tra-cuu-ltt">
          <table>
            <tr>
              <td>
                <div id=ma_ttv style="padding-top:10px;">
                  <label for="ma_ttv" style="padding-left:60px;">
                    <fmt:message key="dtsoat_ltt_di.page.lable.nguoi_lap"/>
                  </label>
                  <input type="text" name="nguoi_lap" id="nguoi_lap" class=" text ui-widget-content ui-corner-all"/>
                </div>
                <div style="padding-top:10px;">
                  <label for="nh_bk_chuyen" style="padding-left:30px;">
                    <fmt:message key="dtsoat_ltt_di.page.lable.nh_bk_chuyen"/>
                  </label>
                  <input type="text" name="nh_bk_chuyen" id="nh_bk_chuyen" class=" text ui-widget-content ui-corner-all"/> 
                </div>
                <div style="padding-top:10px;">
                  <label for="tu_ngay" style="padding-left:16px;">
                    <fmt:message key="dtsoat_ltt_di.page.lable.tu_ngay" />
                  </label>
                  <input type="text" name="tu_ngay" id="tu_ngay" class=" text ui-widget-content ui-corner-all"/> 
                </div>
                <div style="padding-top:10px;">
                  <label for="so_yctt" style="padding-left:40px;">
                    <fmt:message key="dtsoat_ltt_di.page.lable.so_yctt"/>
                  </label>
                  <input type="text" name="so_yctt" id="so_yctt" class=" text ui-widget-content ui-corner-all"/> 
                </div>
                 <div style="padding-top:10px;">
                  <label for="so_lenh_thanh_toan" style="padding-left:40px;">
                    <fmt:message key="dtsoat_ltt_di.page.lable.so_lenh_thanh_toan"/>
                  </label>
                  <input type="text" name="so_lenh_thanh_toan" id="so_lenh_thanh_toan" class=" text ui-widget-content ui-corner-all"/> 
                </div>
              </td>
              <td>
                <div style="padding-top:10px;">
                  <label for="trang_thai_ltt" style="padding-left:60px;">
                    <fmt:message key="dtsoat_ltt_di.page.lable.trang_thai"/>
                  </label>
                  <input type="text" name="trang_thai_ltt" id="trang_thai_ltt" class=" text ui-widget-content ui-corner-all"/>
                </div>
                <div style="padding-top:10px;">
                  <label for="nh_bk_nhan" style="padding-left:30px;">
                    <fmt:message key="dtsoat_ltt_di.page.lable.nh_bk_chuyen"/>
                  </label>
                  <input type="text" name="nh_bk_nhan" id="nh_bk_nhan" class=" text ui-widget-content ui-corner-all"/> 
                </div>
                <div style="padding-top:10px;">
                  <label for="so_dts" style="padding-left:16px;">
                    <fmt:message key="dtsoat_ltt_di.page.lable.so_dts" />
                  </label>
                  <input type="text" name="so_dts" id="so_dts" class=" text ui-widget-content ui-corner-all"/> 
                </div>
                <div style="padding-top:10px;">
                  <label for="so_lenh_tt" style="padding-left:40px;">
                    <fmt:message key="dtsoat_ltt_di.page.lable.so_lenh_tt"/>
                  </label>
                  <input type="text" name="so_lenh_tt" id="so_lenh_tt" class=" text ui-widget-content ui-corner-all"/> 
                </div>
              </td>
              <td>
                <div>
                  <button id="btn_thoat"><fmt:message key="dtsoat_ltt_di.page.button.exit"/></button>
                </div>
              </td>
            </tr>
          </table>
        </form>
      </div>
      <div id="dialog-confirm" title="<fmt:message key="dtsoat_ltt_di.page.title.dialog_confirm"/>" >
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
          <span id="message_confirm"></span>
        </p>
      </div>

  <%@ include file="/includes/ttsp_bottom.inc"%>
