
<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.ChungThuSoResource"/>
<title><fmt:message key="chung_thu_so.page.duyet_cts.title"/></title>
<script type="text/javascript">
   jQuery.noConflict();
   jQuery(document).ready(function()
      {
       //*************************************************KHAI BAO BIEN ***********************************
          var id_field = jQuery("#id" ),
              txtFromDate=jQuery("#txtFromDate"),
              txtToDate=jQuery("#txtToDate"),
              hieuLucTuNgay=jQuery("#hieu_luc_tu_ngay"),
              hieuLucDenNgay=jQuery("#hieu_luc_den_ngay"),
              ma_nguoi_duoc_cap_cts=jQuery("#ma_nguoi_duoc_cap_cts"),
              ten_nguoi_duoc_cap_cts=jQuery("#ten_nguoi_duoc_cap_cts"),
              nha_cung_cap_cts=jQuery("#nha_cung_cap_cts"),
              serial_cts=jQuery("#serial_cts"),
              dialog_form =jQuery("#dialog-form"),
              dialog_message=jQuery("#dialog-message"),
              dialog_confirm=jQuery("#dialog-confirm"),
              actionField=jQuery("#action"),
              frmTraCuuCTS=jQuery("#frmTraCuuCTS"),
              allFields = jQuery([]).add(id_field).add(txtFromDate).add(txtToDate),
              allFieldsSua = jQuery([]).add(hieuLucTuNgay).add(hieuLucDenNgay).add(ma_nguoi_duoc_cap_cts)
                           .add(ten_nguoi_duoc_cap_cts).add(nha_cung_cap_cts).add(serial_cts);
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
          hieuLucTuNgay.datepicker({	
							dateFormat: 'dd/mm/yy',
							changeMonth: true,
							changeYear: true
						});
          hieuLucDenNgay.datepicker({	
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
                  //thuongdt-20180327 sua chuc nang thoat
                  var vform = document.getElementsByName('frmThoat')[0];            
                  vform.submit(); 
                },
								"Không": function() {
									jQuery(this).dialog("close");
                }
							}
						});
          //**************************dialog form SUA CTS**************************
         dialog_form.dialog({
							autoOpen: false,
              width: 600,
              height: 300,
              modal: true,
							buttons: {
                "Ghi": function() {
                  allFieldsSua.removeClass("ui-state-error");
                  //thuongdt-20170404 sua lay form theo ten form
                  var form = document.getElementsByName('suaCTSForm')[0];  
                  if(form.hieu_luc_tu_ngay.value==null || form.hieu_luc_tu_ngay.value==''){
                    hieuLucTuNgay.addClass("ui-state-error");
                    jQuery("#fieldNameForcus").val('hieu_luc_tu_ngay');
                    dialog_message.html('<fmt:message key="dang_ky_cts.message.empty_fromdate"/>');
                    dialog_message.dialog('open');
                  }else if(form.hieu_luc_den_ngay.value==null || form.hieu_luc_den_ngay.value==''){
                    jQuery("#fieldNameForcus").val('hieu_luc_den_ngay');
                    hieuLucDenNgay.addClass("ui-state-error");
                    dialog_message.html('<fmt:message key="dang_ky_cts.message.empty_todate"/>');
                    dialog_message.dialog('open');
                  }else{
                    if(CheckDate(form.hieu_luc_tu_ngay)){
                      if(CheckDate(form.hieu_luc_den_ngay) && form.hieu_luc_den_ngay!=''){
                          // tu ngay phai nho hon den ngay
                          if (CompareDate(hieuLucTuNgay.val(),hieuLucDenNgay.val())==-1) {
                              dialog_message.html('<fmt:message key="dang_ky_cts.page.message.tu_ngay_lap_nho_hon_den_ngay_lap"/>');
                              dialog_message.dialog("open");
                              jQuery("#fieldNameForcus").val('hieu_luc_den_ngay');
                              hieuLucDenNgay.addClass( "ui-state-error");
                            }else if(!validDateCTS()){
                               dialog_message.html('<fmt:message key="dang_ky_cts.page.message.ngay_hieu_luc_khong_hop_le"/>');
                               dialog_message.dialog("open");
                            }else{
                              jQuery("#suaCTSForm").attr({action:'suatgianhieuluc.do'});
                              jQuery("#suaCTSForm").submit();
                          }
                       }else{
                        jQuery("#fieldNameForcus").val('hieu_luc_den_ngay');
                        hieuLucDenNgay.addClass("ui-state-error");
                        }
                    }else{
                      jQuery("#fieldNameForcus").val('hieu_luc_den_ngay');
                      hieuLucTuNgay.addClass("ui-state-error");
                      }
                  }
                  
                },
                "Thoát": function() {
									jQuery(this).dialog("close");
                  allFieldsSua.val('');
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
         //**************************BUTTON PRINT*************************
         jQuery("#btn_in").click(function() { 
            var f = document.forms[0]; 
            f.action = "tracuuctsRptAction.do";
            var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
            newDialog = window.open('', '_form', params); 
            f.target='_form';
            f.submit();
          });
         //***************************************************************
       });
       
      //**************************SUA CTS CLICK********************************************
          function popupCTS(param){
             var arrParam=param.split(':')
             jQuery.ajax({
             type: "POST",
             url: "viewdetailcts.do",	
             data: {"serial" : arrParam[0],
                    "user_name":arrParam[1],
                    "nd" : Math.random() * 100000},
             dataType:'json',
             success: function(data,textstatus){
                 if(textstatus!=null && textstatus=='<%=AppConstants.SUCCESS%>'){
                   if(data!=null){
                    if(data.ERROR ==undefined || data.ERROR==''){                     
                      jQuery("#ma_nguoi_duoc_cap_cts").val(data.userName);
                      jQuery("#ten_nguoi_duoc_cap_cts").val(data.issuedTo);
                      jQuery("#nha_cung_cap_cts").val(data.issuedBy);
                      jQuery("#serial_cts").val(data.certSerial);
                      // thuongdt-20170320 load lai ngay hieu luc tu ngay, den ngay theo mac dinh tu ngay, den ngay da duoc phe duyet begin
                      //jQuery("#hieu_luc_tu_ngay").val(data.validOnAppFrom);
                      //jQuery("#hieu_luc_den_ngay").val(data.validOnAppTo);
                      jQuery("#hieu_luc_tu_ngay").val(data.validFrom);
                      jQuery("#hieu_luc_den_ngay").val(data.validTo);
                      // thuongdt-20170320 load lai ngay hieu luc tu ngay, den ngay theo mac dinh tu ngay, den ngay da duoc phe duyet end
                      jQuery("#validFrom").val(data.validFrom);
                      jQuery("#validTo").val(data.validTo);
                      jQuery("#dialog-form").dialog("open");
                    }else{
                      jQuery("#dialog_message").html('L&#7895;i '+data.ERROR);
                      jQuery("#dialog_message").dialog('open');
                    }
                 }
                } 
              },
              error:function(textstatus){
                jQuery("#dialog_message").html(textstatus);
                jQuery("#dialog_message").dialog("open");
             }
            });
           
           }
      //**************************LINK TRANG CLICK********************************************
         function goPage(page) {
             //thuongdt-20170327 sua lai form id           
            document.getElementById('pageNumber').value = page;
            var vform = document.getElementsByName('frmTraCuuCTS')[0];            
            vform.submit(); 
         
        }
       //**************************HIDE - SHOW BUTTON DUYET********************************************
        function validDateCTS(){
          var validFrom=jQuery("#validFrom").val(),
           validTo=jQuery("#validTo").val(),
           fromDateOnApp=jQuery("#hieu_luc_tu_ngay").val(),
           toDateOnApp=jQuery("#hieu_luc_den_ngay").val();
          if(CompareDate(validFrom,fromDateOnApp)==-1 || CompareDate(fromDateOnApp,validTo)==-1) {
            return false;
          }else if(CompareDate(validFrom,toDateOnApp)==-1 || CompareDate(toDateOnApp,validTo)==-1){
            return false;
          }
          return true;
        }
   
  </script>
   <!--thuongdt-20180327 sua chuc nang thoat-->
   <html:form action="/thoatView.do" styleId="frmThoat">
   
   </html:form>
  <html:form action="/tracuucts.do" styleId="frmTraCuuCTS">
     <table border=0 cellSpacing=0 cellPadding=0 width="100%"  align=center>
          <tbody>
            <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2><fmt:message key="chung_thu_so.page.title.danh_sach_cts"/></span>
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
          <td>
      <div class="app_error"><html:errors/></div>
      <input type="hidden" id="action"/>
      <input type="hidden" id="fieldNameForcus"/>
    
      <html:hidden property="search_cts" value="true"/>
      <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
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
          <table style="BORDER-COLLAPSE: collapse;"  border="0" cellSpacing=0 borderColor=#b0c4de cellPadding=0 width="65%" align="center">
            <tbody>
            <tr>
                <td align="right">
                  <label for="id"><fmt:message key="chung_thu_so.page.lable.ma_kho_bac" /></label>
                </td>
                <td align="left" colspan="5">
                 <html:select property="kb_id" styleId="ma_kb" styleClass="fieldTextCode" style="width:38.5%;">
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
                <td align=right>
                  <label for="ma_nguoi_duoc_cap"><fmt:message key="chung_thu_so.page.lable.ma_hoac_ten_nhan_vien" /></label>
                </td>
                <td>
                  <html:text property="ma_nguoi_duoc_cap" styleId="ma_nguoi_duoc_cap" styleClass="fieldText"  onkeydown="if(event.keyCode==13) event.keyCode=9;" onmouseover="Tip(this.value)" onmouseout="UnTip()" />
                </td>
                <td align=right>
                  <label for="serial"><fmt:message key="chung_thu_so.page.lable.so_serial"/></label>
                </td>
                <td  width="30%">
                  <html:text property="serial" styleId="serial" styleClass="fieldText" onkeydown="if(event.keyCode==13) event.keyCode=9;" onKeyPress="return numbersonly(this,event,true)" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                </td>
              </tr>
              <tr>
                <td align="right">
                  <label for="hieu_luc_tu_ngay" >
                    <fmt:message key="chung_thu_so.page.lable.hieu_luc_tu_ngay"/>
                  </label>
              </td>
              <td width="30%">
                <html:text  property="hieu_luc_tu_ngay" styleId="txtFromDate"  onblur = "CheckDate(this);"  styleClass="fieldText" onkeydown="if(event.keyCode==13) event.keyCode=9;" onmouseover="Tip(this.value)" onmouseout="UnTip()" />
              </td>
              <td align="right">
                <label for="hieu_luc_den_ngay">
                  <fmt:message key="chung_thu_so.page.lable.hieu_luc_den_ngay"/>
                </label>
              </td>
              <td>
                <html:text  property="hieu_luc_den_ngay" styleId="txtToDate" onblur = "CheckDate(this);" styleClass="fieldText" onkeydown="if(event.keyCode==13) event.keyCode=9;" onmouseover="Tip(this.value)" onmouseout="UnTip()"/> 
              </td>
              </tr>
            </tbody>
          </table>
          </td>
        </tr>
        <tr>
          <td>
          <table class="buttonbar" align="center">
            <tr>
              <td>
                <span id="tracuu">
                 <button id="btn_traCuu" accesskey="r" value="tracuu"  class="ButtonCommon" type="submit" >
                  <fmt:message key="dang_ky_cts.page.button.search"/>
                </button>
                </span>
                <span>
                <button id="btn_in" accesskey="i" type="button"
                              class="ButtonCommon">
                        <fmt:message key="dang_ky_cts.page.button.in"/>
                      </button></span>
                      <span>
                 <button id="btn_duyet" type="button" accesskey="d"  class="ButtonCommon">
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
              <table class="navigateable focused" cellspacing="0" cellpadding="1"
                   bordercolor="#999999" border="1" align="center" width="100%"
                   style="BORDER-COLLAPSE: collapse"> 
              <thead class="TR_Selected">
                <tr >
                  <th width="11%">
                    <fmt:message key="chung_thu_so.page.lable.ma_nhan_vien"/>
                  </th>
                  <th>
                    <fmt:message key="chung_thu_so.page.lable.ho_ten_nhan_vien"/>
                  </th>
                  <th width="14%">
                    <fmt:message key="chung_thu_so.page.lable.nguoi_cap"/>
                  </th>
                  <th  width="10%">
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
                   <th width="1%">
                    &nbsp;
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
                     <bean:write name="items" property="validOnAppFrom"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="validOnAppTo"/>
                    </td>
                    <td align="center">
                     <bean:write name="items" property="revokeDate"/>
                    </td>
                    <td align="center">
                      <div style="cursor: pointer;" onclick="popupCTS('<bean:write name="items" property="certSerial"/>:<bean:write name="items" property="userName"/>');"><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/edit.gif"/></div>
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
              
            </logic:notEmpty>
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
      </html:form>


  

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
   <div id="dialog-form" title="<fmt:message key="chung_thu_so.page.quan_ly_thoi_gian_cts.title"/>">
      <p class="validateTips"></p>
        <input type="hidden" Id="validTo"/>
        <input type="hidden" Id="validFrom"/>
        <html:form action="/quanlydangkycts.do" styleId="suaCTSForm">
          <table style="BORDER-COLLAPSE: collapse;"  border="1" cellSpacing=0 borderColor=#b0c4de cellPadding=0 align="center">
            <tbody>
              <tr>
                <td align="right">
                  <label for="ma_nguoi_duoc_cap"><fmt:message key="chung_thu_so.page.lable.nguoi_duoc_cap" /></label>
                </td>
                <td align="left" width="10%;">
                  <html:text property="ma_nguoi_duoc_cap" styleId="ma_nguoi_duoc_cap_cts" styleClass="fieldText"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                </td>
                <td colspan="4" align="left">
                  <html:text property="ten_nguoi_duoc_cap" styleId="ten_nguoi_duoc_cap_cts" styleClass="fieldText"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                </td>
              </tr>
              <tr>
                <td align=right>
                  <label for="nha_cung_cap"><fmt:message key="chung_thu_so.page.lable.nguoi_cap" /></label>
                </td>
                <td colspan="2" width="30%;">
                  <html:text property="nha_cung_cap" styleId="nha_cung_cap_cts" styleClass="fieldText"  onmouseover="Tip(this.value)" onmouseout="UnTip()" readonly="true"/>
                </td>
                <td align=right>
                  <label for="serial"><fmt:message key="chung_thu_so.page.lable.so_serial"/></label>
                </td>
                <td colspan="2">
                  <html:text property="serial" styleId="serial_cts" styleClass="fieldText"  readonly="true" onmouseover="Tip(this.value)" onmouseout="UnTip()" />
                </td>
              </tr>
              <tr>
                <td align=right>
                  <label for="hieu_luc_tu_ngay"><fmt:message key="chung_thu_so.page.lable.hieu_luc_tu_ngay"/></label>
                </td>
                <td colspan="2">
                  <html:text property="hieu_luc_tu_ngay" styleId="hieu_luc_tu_ngay"  onblur="CheckDate(this);" styleClass="fieldText"  onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                </td>
                 <td align=right>
                  <label for="hieu_luc_den_ngay"><fmt:message key="chung_thu_so.page.lable.hieu_luc_den_ngay"/></label>
                </td>
                <td colspan="2">
                  <html:text property="hieu_luc_den_ngay" styleId="hieu_luc_den_ngay"  onblur="CheckDate(this);" styleClass="fieldText" onmouseover="Tip(this.value)" onmouseout="UnTip()"/>
                </td>
              </tr>
            </tbody>
          </table>
       </html:form>
    </div>
<%@ include file="/includes/ttsp_bottom.inc"%>
