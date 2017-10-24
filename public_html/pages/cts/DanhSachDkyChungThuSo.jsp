<%@ page contentType="text/html;charset=UTF-8"%>

<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.ChungThuSoResource"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/LenhThanhToan.js"
        type="text/javascript">
</script>
<title><fmt:message key="chung_thu_so.page.duyet_cts.title"/></title>
<script type="text/javascript">
  jQuery.noConflict();
   jQuery(document).ready(function()
      {
      
       //*************************************************KHAI BAO BIEN ***********************************
          var id_field = jQuery("#id" ),
              txtFromDate=jQuery("#txtFromDate"),
              txtToDate=jQuery("#txtToDate"),
							dialog_form =jQuery("#dialog-form"),
              dialog_message=jQuery("#dialog-message"),
              dialog_confirm=jQuery("#dialog-confirm"),
              actionField=jQuery("#action"),
              frmQuanLyCTS=jQuery("#frmQuanLyCTS"),
              allFields = jQuery([]).add(id_field).add(txtFromDate).add(txtToDate);
        //*****************************************************reset form search ***********************************
         
          txtFromDate.datepicker({	
							dateFormat: 'dd/mm/yy',
							changeMonth: true,
							changeYear: true
						});
          txtToDate.datepicker({
							dateFormat: 'dd/mm/yy',
							changeMonth: true,
							changeYear: true
						});
          jQuery("#btn_duyet").hide();
          
          jQuery('option',jQuery("#ma_kb")).each(function(i){
           if(jQuery(this).val()=='<%=request.getAttribute("kbId")%>'){
                jQuery(this).attr({selected:'selected'});
                return false;
            }
          });
        //************************************ dialog message ******************************************************
        dialog_message.dialog({
          autoOpen: false,
          resizable: false,
          height:200,
          width:380,
          modal: true,
          buttons: {
            Ok: function() {
             jQuery(this).dialog( "close" );
              var fieldForcus=jQuery("#fieldNameForcus").val();
              if(fieldForcus!=null && fieldForcus!=''){
                jQuery("#"+fieldForcus).focus();
              }
             }
         }
        });
        
       //************************************ dialog confirm message	***********************************************
						dialog_confirm.dialog({
							autoOpen: false,
							resizable: false,
							height:200,
							width:380,
							modal: true,
							buttons: {
								"Có": function() {
                  jQuery(this).dialog( "close" );
                  if(actionField.val()=='<%=AppConstants.ACTION_APPROVED%>'){
                    duyetctsExc();
                    dialog_form.dialog("close");
                  }else{
                      //thuongdt-20180327 sua chuc nang thoat
                      var vform = document.getElementsByName('frmThoat')[0];            
                      vform.submit(); 
                  }
                },
								"Không": function() {
									jQuery(this).dialog("close");
                }
							}
						});
       
				//**************************dialog form Duyet CTS**************************
					dialog_form.dialog({
							autoOpen: false,
              resizable: false,
							height:300,
              width: 420,
              modal: true,
							buttons: {
                "Chấp nhận": function() {
                  //chekcdate
                  
                  allFields.removeClass("ui-state-error");
                  //thuongdt-20170404 sua lay form theo ten form
                  var form = document.getElementsByName('duyetCTSForm')[0]; 
                  
                  if(form.hieu_luc_tu_ngay.value==null || form.hieu_luc_tu_ngay.value==''){
                    txtFromDate.addClass("ui-state-error");
                    jQuery("#fieldNameForcus").val('txtFromDate');
                    dialog_message.html('<fmt:message key="dang_ky_cts.message.empty_fromdate"/>');
                    dialog_message.dialog('open');
                  }else if(form.hieu_luc_den_ngay.value==null || form.hieu_luc_den_ngay.value==''){
                    jQuery("#fieldNameForcus").val('txtToDate');
                    txtToDate.addClass("ui-state-error");
                    dialog_message.html('<fmt:message key="dang_ky_cts.message.empty_todate"/>');
                    dialog_message.dialog('open');
                  }else{
                    if(CheckDate(form.hieu_luc_tu_ngay)){
                      if(CheckDate(form.hieu_luc_den_ngay) && form.hieu_luc_den_ngay!=''){
                          // tu ngay phai nho hon den ngay
                          if (CompareDate(txtFromDate.val(),txtToDate.val())==-1) {
                              dialog_message.html('<fmt:message key="dang_ky_cts.page.message.tu_ngay_lap_nho_hon_den_ngay_lap"/>');
                              dialog_message.dialog("open");
                              jQuery("#fieldNameForcus").val('txtFromDate');
                              txtToDate.addClass( "ui-state-error");
                            }else if(!validDateCTS()){
                              dialog_message.html('<fmt:message key="dang_ky_cts.page.message.ngay_hieu_luc_khong_hop_le"/>');
                              dialog_message.dialog("open");
                            }else{
                             dialog_confirm.html('<fmt:message key="dang_ky_cts.page.message_confirm.duyet_cts"/>');
                             dialog_confirm.dialog( "open" );
                          }
                       }else{
                        jQuery("#fieldNameForcus").val('txtFromDate');
                        txtToDate.addClass("ui-state-error");
                        }
                    }else{
                      jQuery("#fieldNameForcus").val('txtFromDate');
                      txtFromDate.addClass("ui-state-error");
                      }
                  }
               
                },
								"Hủy": function() {
                  allFields.val('');
                 },
								"Thoát": function() {
									jQuery(this).dialog("close");
                  allFields.val('');
								}
                },
                close:function(){
                   actionField.val('');
							}
						});
          //**************************BUTTON Thoat CLICK********************************************
         jQuery("#btn_thoat").click(function() {
            dialog_confirm.html('<fmt:message key="dang_ky_cts.page.message_confirm.thoat"/>');
            dialog_confirm.dialog( "open" );
         });
         
         //**************************BUTTON DUYET CLICK********************************************
         jQuery("#btn_duyet").click(function() {
            actionField.val('<%=AppConstants.ACTION_APPROVED%>');
            dialog_form.dialog("open" );
         });
        //************************** CHECK ALL ********************************************  
         jQuery('#chkAll').click(function () {
              jQuery("input[name='include']").each(function (i) {
               if (jQuery('#chkAll').is(':checked')) {
                  jQuery(this).attr('checked', 'checked');
                  jQuery("#btn_duyet").show();
              }else{
                jQuery(this).attr('checked', false);
                jQuery("#btn_duyet").hide();
              }

            });

        });
        
         
       //**************************function DUYET ********************************************
        function  duyetctsExc(){
          var listSeria="";
          var listUserName="";
          jQuery("input[name='include']").each(function () {
               if (this.checked == true) {
                  var ctsVal=jQuery(this).val().split(':');
                  listSeria+=ctsVal[0]+':';
                  listUserName+=ctsVal[1].toString()+':';
               }
           });
           jQuery("#duyet_serial").val(listSeria);

           jQuery("#duyet_user_name").val(listUserName);
           jQuery("#duyetCTSForm").attr({action:'duyetctsExc.do'});
           jQuery("#duyetCTSForm").submit();
        }
       
       });
       
      //**************************LINK TRANG CLICK********************************************
         function goPage(page) {
            //thuongdt-20170327 sua lai form id           
            document.getElementById('pageNumber').value = page;
            var vform = document.getElementsByName('frmQuanLyCTS')[0];            
            vform.submit();            
        }
      //**************************HIDE - SHOW BUTTON DUYET********************************************
         function btnDuyet() {
          var duyet=false;
            jQuery("input[name='include']").each(function () {
               if (this.checked == true) {
                  jQuery("#btn_duyet").show();
                  duyet=true;
                  return false;
               }else{
                jQuery("#btn_duyet").hide();
               }
              });
            if(!duyet)
              jQuery("#btn_duyet").hide();
        }
    //**************************HIDE - SHOW BUTTON DUYET********************************************
      function validDateCTS(){
        var fromDateOnApp=jQuery("#txtFromDate").val();
        var toDateOnApp=jQuery("#txtToDate").val();
        var isReturn=true;
        jQuery("input[name='include']").each(function () {
           if (this.checked == true) {
              var ctsVal=jQuery(this).val().split(':');
              if(CompareDate(ctsVal[2],fromDateOnApp)==-1 || CompareDate(fromDateOnApp,ctsVal[3])==-1) {
                return isReturn=false;
              }else if(CompareDate(ctsVal[2],toDateOnApp)==-1 || CompareDate(toDateOnApp,ctsVal[3])==-1){
                return isReturn=false;
              }
            }
        });
        return isReturn;
      }
  </script>
   <!--thuongdt-20180327 sua chuc nang thoat-->
   <html:form action="/thoatView.do" styleId="frmThoat">
   
   </html:form>
  
  <body>
    <div id="body">
      <html:form action="/quanlydangkycts.do" styleId="frmQuanLyCTS" >
    <table border=0 cellSpacing=0 cellPadding=0 width="100%"  align=center>
          <tbody>
            <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="chung_thu_so.page.duyet_cts.title"/></span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
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
                <fmt:message key="chung_thu_so.page.dieukien"/>
              </font>
            </span>
          </td>
        </tr>
          <tr>
          <td>
            <div class="app_error"><html:errors/></div>
            <input type="hidden" id="action"/>
            <input type="hidden" id="fieldNameForcus"/>
              <html:hidden property="search_cts" value="true"/>
              
                <div style="padding-top:10px;">
                 <table style="BORDER-COLLAPSE: collapse;"  border="0" cellSpacing=0 borderColor=#b0c4de cellPadding=0  align="center" width="35%">
                    <tbody>
                      <tr>
                        <td align="right">
                          <label for="id"><fmt:message key="chung_thu_so.page.lable.ma_kho_bac" /></label>
                        </td>
                        <td align="left" colspan="5">
                         <html:select property="kb_id" styleId="ma_kb" styleClass="fieldTextCode" style="width:100%;">
                            <html:option styleClass="jecEditableOption" value="">- - - - - <fmt:message key="chung_thu_so.page.option.default_kho_bac"/> - - - - -</html:option>
                            <logic:present name="listKhoBac">
                             <logic:iterate id="items" name="listKhoBac" indexId="stt">
                               <option class="jecEditableOption" value="<bean:write name='items' property='id'/>">
                                <bean:write name="items" property="ma"/>&nbsp;&nbsp;---&nbsp;&nbsp;
                                <bean:write name="items" property="ten"/>
                              </option>
                            </logic:iterate>
                          </logic:present>
                          </html:select>
                        </td>
                      </tr>
                      <tr>
                        <td align=right width="37%">
                          <label for="ma_nguoi_duoc_cap"><fmt:message key="chung_thu_so.page.lable.ma_hoac_ten_nhan_vien" /></label>
                        </td>
                        <td>
                          <html:text property="ma_nguoi_duoc_cap" styleId="ma_nguoi_duoc_cap" styleClass="fieldText" onkeydown="if(event.keyCode==13) event.keyCode=9;" onmouseover="Tip(this.value)" onmouseout="UnTip()" maxlength="15"/>
                        </td>
                      </tr>
                      <tr>
                         <td align=right>
                          <label for="serial"><fmt:message key="chung_thu_so.page.lable.so_serial"/></label>
                        </td>
                        <td> 
                          <html:text  property="serial" styleId="serial" styleClass="fieldText"  onkeydown="if(event.keyCode==13) event.keyCode=9;" onKeyPress="return numbersonly(this,event,true)" onmouseover="Tip(this.value)" onmouseout="UnTip()" maxlength="20"/>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
                
          </td>
        </tr>
        <tr>
          <td>
            <table class="buttonbar" align="center">
            <tr>
              <td>
              <span id="tracuu">
                <button id="btn_traCuu" accesskey="r" type="submit" value="tracuu" class="ButtonCommon">
                    <fmt:message key="dang_ky_cts.page.button.search"/>
                  </button>
                </span>
                <span>
                   <button id="btn_duyet" type="button" accesskey="d" class="ButtonCommon">
                    <fmt:message key="dang_ky_cts.page.button.approved"/>
                  </button>
                  </span>
                  <span>
                  <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon">
                    <fmt:message key="dang_ky_cts.page.button.exit"/>
                  </button>
              </span>
              </td>
              </tr>
              </table>
          </td>
        </tr>
        <tr>
        <td class="title" colspan="6"
                        background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
                        height="24">
                      <font color="Gray">
                        <fmt:message key="chung_thu_so.page.ketqua"/>
                      </font>
                    </td>
        </tr>
          <tr>
            
            <td>
            <div style="width:100%; height: auto; overflow: auto;" align="left">
                  <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#999999" border="1" align="center" width="100%"
                   style="BORDER-COLLAPSE: collapse">                   
                    <thead class="TR_Selected">
                      <tr >
                        <th width="11%">
                          <fmt:message key="chung_thu_so.page.lable.ma_nhan_vien"/>
                        </th>
                        <th width="14%">
                          <fmt:message key="chung_thu_so.page.lable.ho_ten_nhan_vien"/>
                        </th>
                       <th width="14%">
                          <fmt:message key="chung_thu_so.page.lable.nguoi_cap"/>
                        </th>
                        <th>
                          <fmt:message key="chung_thu_so.page.lable.so_serial"/>
                        </th>
                        <th width="12%">
                          <fmt:message key="chung_thu_so.page.lable.ngay_dang_ky"/>
                        </th>
                        <th width="12%">
                          <fmt:message key="chung_thu_so.page.lable.hieu_luc_tu_ngay"/>
                        </th>
                        <th width="12%">
                          <fmt:message key="chung_thu_so.page.lable.hieu_luc_den_ngay"/>
                        </th>
                        <th width="12%">
                          <fmt:message key="chung_thu_so.page.lable.ngay_thu_hoi"/>
                        </th>
                         <th class="promptText" bgcolor="#f0f0f0">
                          <div align="center">
                            <input type="checkbox" name="Checkall" value="yes" id="chkAll"/>
                          </div>
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <logic:empty name="listCTSChuaDuyet">
                        <tr><td colspan="9" align="center">&nbsp;</td></tr>
                      </logic:empty>
                      <logic:notEmpty name="listCTSChuaDuyet">
                      <%
                        com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
                        int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
                      %>
                      <logic:iterate id="items" name="listCTSChuaDuyet" indexId="stt">
                        <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                          <td>
                           <bean:write name="items" property="userName"/>
                          </td>
                          <td>
                           <bean:write name="items" property="issuedTo"/>
                          </td>
                          <td>
                            <bean:write name="items" property="issuedBy"/>
                          </td>
                          <td align="center">
                            <bean:write name="items" property="certSerial"/>
                          </td>
                           <td align="center">
                           <bean:write name="items" property="uploadDate"/>
                          </td>
                          <td align="center">
                           <bean:write name="items" property="validFrom"/>
                          </td>
                          <td align="center">
                            <bean:write name="items" property="validTo"/>
                          </td>
                           <td align="center">
                           <bean:write name="items" property="revokeDate"/>
                          </td>
                         <td align="center">
                          <input type="checkbox" name="include" onclick="btnDuyet()" id="include_<bean:write name="stt"/>" value="<bean:write name="items" property="certSerial"/>:<bean:write name="items" property="userName"/>:<bean:write name="items" property="validFrom"/>:<bean:write name="items" property="validTo"/>"/>
                        </td>
                        </tr>
                      </logic:iterate>
                    <tr>
                      <td colspan="9" >
                        <div style="float:right;padding-right:40">
                         <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                        </div>
                      </td>
                    </tr>
                    <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
                    </logic:notEmpty>
                    </tbody>
                  </table>
                </div>
          </td>
          
        </tr>
      </tbody>
    </table>
    </html:form>
    </div>
  </body>
    

      

  <!------------------------------------------ Message confirm ------------------------------->
  <div id="dialog-confirm" title="<fmt:message key="chung_thu_so.page.title.dialog_confirm"/>" >
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
      <span id="message_confirm"></span>
    </p>
  </div>
  
<!-- ---------------------------------Message --------------------------------->
  <div id="dialog-message" title="<fmt:message key="chung_thu_so.page.title.dialog_message"/>">
    <p>
      <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="message"></span>
    </p>
  </div>
   <div id="dialog-form" title="<fmt:message key="chung_thu_so.page.title.dialog_form"/>">
      <p class="validateTips"></p>
        <html:form action="/quanlydangkycts.do" styleId="duyetCTSForm">
          <html:hidden property="serial" value="" styleId="duyet_serial"/>
          <html:hidden property="user_name" value="" styleId="duyet_user_name"/>
            <div id=ma_ttv style="padding-top:10px;">
              <label for="hieu_luc_tu_ngay" style="padding-left:60px;">
                <fmt:message key="chung_thu_so.page.lable.hieu_luc_tu_ngay"/>
              </label>
              <html:text  property="hieu_luc_tu_ngay" styleId="txtFromDate"  onblur = "CheckDate(this);"  styleClass=" text ui-widget-content ui-corner-all"/>
            </div>
            <div style="padding-top:10px;">
              <label for="hieu_luc_den_ngay" style="padding-left:50px;">
                <fmt:message key="chung_thu_so.page.lable.hieu_luc_den_ngay"/>
              </label>
             <html:text  property="hieu_luc_den_ngay" styleId="txtToDate" onblur = "CheckDate(this);" styleClass=" text ui-widget-content ui-corner-all"/> 
          </div>
       </html:form>
    </div>
<%@ include file="/includes/ttsp_bottom.inc"%>
