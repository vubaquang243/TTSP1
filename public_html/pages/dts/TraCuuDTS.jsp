<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuDTSResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<title><fmt:message key="tra_cuu_dts.page.title"/></title>
<script type="text/javascript">
  jQuery.noConflict();
   jQuery(document).ready(function()
      {
        //*************************************************KHAI BAO BIEN ***********************************
          var den_ngay_lap_ltt_field =  jQuery("#den_ngay_lap_lenh"),
    			 		tu_ngay_lap_ltt_field =  jQuery("#tu_ngay_lap_lenh"),
              den_ngay_lap_field =  jQuery("#den_ngay_lap_dien"),
    			 		tu_ngay_lap_field =  jQuery("#tu_ngay_lap_dien"),
              ttv_nhan_field =  jQuery("#ttv_nhan"),
							trang_thai_field = jQuery("#trang_thai" ),
							loai_ltt_field = jQuery("#loai_ltt" ),
              loai_tra_soat_field = jQuery("#loai_tra_soat" ),
              ma_nhkb_chuyen_field =  jQuery("#ma_nhkb_chuyen"),
							ma_nhkb_nhan_field = jQuery("#ma_nhkb_nhan" ),
							id_field = jQuery("#id" ),
							ltt_id_field =jQuery("#ltt_id" ),
              dialog_message=jQuery("#dialog-message"),
              dialog_confirm=jQuery("#dialog-confirm"),
              fieldNameForcus=jQuery("#fieldNameForcus"),
              frmTraCuuDTS=jQuery("#frmTraCuuDTS"),
//              chieu_tra_soat= jQuery("#chieu_dts").val();
							allFields = jQuery([]).add(den_ngay_lap_ltt_field).add(tu_ngay_lap_ltt_field).add(den_ngay_lap_field ).
                add(trang_thai_field).add( ttv_nhan_field).add( ma_nhkb_chuyen_field).add( ma_nhkb_nhan_field).
                add(loai_ltt_field).add( ma_nhkb_chuyen_field).add( ma_nhkb_nhan_field).add(ltt_id_field ).
                add(tu_ngay_lap_field).add(loai_tra_soat_field).add(id_field);
        
				//*****************************************************reset form search ***********************************
//          alert('<%=request.getParameter("search_dts")%>');
//          if('<%=request.getParameter("search_dts")%>'==null||'<%=request.getParameter("search_dts")%>'=='null'){
//            allFields.val('');
//          }
        //************************************ dialog message ******************************************************
        frmTraCuuDTS.target = '';
        dialog_message.dialog({
          autoOpen: false,
          height:200,
          width:380,
          modal: true,
          buttons: {
            Ok: function() {
             jQuery(this).dialog( "close" );
              var fieldForcus=fieldNameForcus.val();
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
                  jQuery("#frmTraCuuDTS").target='';
                  jQuery("#frmTraCuuDTS").attr({action:'thoatView.do'});
                  jQuery("#frmTraCuuDTS").submit();
                  jQuery(this).dialog("close");
                },
								"Không": function() {
									jQuery(this).dialog("close");
								}
							}
						});
       
					//**************************BUTTON Sua CLICK********************************************
        jQuery("#btn_timKiem").click(function() {  
          frmTraCuuDTS = document.forms[0]; 
          frmTraCuuDTS.target = '';
          frmTraCuuDTS.action = 'tracuudts.do';
          
          var flag=true;
          allFields.removeClass('ui-state-error');
          var tu_ngay_lap = tu_ngay_lap_field.val(),
             den_ngay_lap = den_ngay_lap_field.val(),
             tu_ngay_lap_ltt=tu_ngay_lap_ltt_field.val(),
             den_ngay_lap_ltt = den_ngay_lap_ltt_field.val();
            if((tu_ngay_lap != null && tu_ngay_lap != '') && (den_ngay_lap != null && den_ngay_lap != '')){
              if (CompareDate(tu_ngay_lap,den_ngay_lap)==-1) {
                dialog_message.html('<fmt:message key="tra_cuu_dts.page.message.tu_ngay_lap_nho_hon_den_ngay_lap"/>');
                dialog_message.dialog("open");
                fieldNameForcus.val("tu_ngay_lap");
                tu_ngay_lap_field.addClass( "ui-state-error" );
                flag=false;
              }
            }
            if((tu_ngay_lap_ltt != null && tu_ngay_lap_ltt != '') && (den_ngay_lap_ltt != null && den_ngay_lap_ltt != '')){
              if (CompareDate(tu_ngay_lap_ltt,den_ngay_lap_ltt)==-1) {
                dialog_message.html('<fmt:message key="tra_cuu_dts.page.message.tu_ngay_lap_ltt_nho_hon_den_ngay_lap_ltt"/>');
                dialog_message.dialog("open");
                fieldNameForcus.val("tu_ngay_lap_ltt");
                tu_ngay_lap_ltt_field.addClass( "ui-state-error" );
                flag=false;
              }
            }
           if(flag){
            frmTraCuuDTS.submit();
           }   
          });
          //**************************BUTTON Thoat CLICK********************************************
         jQuery("#btn_thoat").click(function() {
            jQuery("#frmTraCuuDTS").target = '';
            jQuery("#frmTraCuuDTS").attr({action:'thoatView.do'});
            jQuery("#frmTraCuuDTS").submit();
         });
         jQuery("#ttv_nhan").focus();
        //**************************BUTTON PRINT*************************
         jQuery("#btn_in").click(function() { 
            var f = document.forms[0]; 
            f.action = "tracuudtsRptAction.do";
            var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
            newDialog = window.open('', '_form', params);         
            f.target='_form';
            f.submit();
          });
         //***************************************************************	
       });
       
    
    //**************************LINK TRANG CLICK********************************************
    function makeGetRequest(id,type){
      jQuery("#frmTraCuuDTS").target = ''; 
      var ttv_nhan = jQuery("#ttv_nhan").val();
      var trang_thai = jQuery("#trang_thai").val();
      var tu_ngay = jQuery("#tu_ngay_lap_lenh").val();
      var den_ngay =jQuery("#den_ngay_lap_lenh").val();
      var tu_ngay_lapdien = jQuery("#tu_ngay_lap_dien").val();
      var den_ngay_lapdien =jQuery("#den_ngay_lap_dien").val();
      var nhkb_chuyen = jQuery("#ma_nhkb_chuyen").val();
      var nhkb_nhan = jQuery("#ma_nhkb_nhan").val();
      var so_dts = jQuery("#id").val();
      var so_ltt = jQuery("#ltt_id").val();
      var loai_ltt = jQuery("#loai_ltt").val();
      var loai_tra_soat = jQuery("#loai_tra_soat").val();
//      var chieu_tra_soat= jQuery("#chieu_dts").val();
      var urlRequest  =null;
      var subid= id.toString().substring(2,5);
      var subtudo = id.toString().substring(5,8);
      if(subtudo==195){
        if(subid!='701'){
          urlRequest = "xulydtshoitunhtmView.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id="+id;
        }else{
          urlRequest = "dtsoatlttdiView.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id="+id;
        }
      }else if(subtudo==199){
        if(subid!='701'){
          urlRequest = "xulydtshoitunhtmView.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id="+id;
        }else{
          urlRequest = "XuLyDTSoatTuDo.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id="+id; 
        }    
      }else if(subtudo==196){
        if(subid!='701'){
          urlRequest = "dtsoattloiView.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id="+id;
        }else{
          urlRequest = "xulydtsoattraloiView.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id="+id; 
        }
      }
//      else if(type==299){
//        urlRequest = "KeoDaiTGGDView.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id="+id; 
//      }
//      else{
//        if (subid=='701'){
//        urlRequest = "xulydtsoattraloiView.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id="+id; 
//        }else{
//         urlRequest ="dtsoattloiView.do?action=VIEW_DETAIL&id="+id;
//        }
//      }
      if(ttv_nhan != null && ttv_nhan != ''){
        urlRequest += "&ttv_nhan="+ttv_nhan+"";
      }
      if(trang_thai != null && trang_thai != ''){
        urlRequest += "&trang_thai="+trang_thai+"";
      }    
      if(tu_ngay != null && tu_ngay != ''){
        urlRequest += "&tu_ngay="+tu_ngay+"";
      }
      if(den_ngay != null && den_ngay != ''){
        urlRequest += "&den_ngay="+den_ngay+"";
      }
       if(tu_ngay_lapdien != null && tu_ngay_lapdien != ''){
        urlRequest += "&tu_ngay_lapdien="+tu_ngay_lapdien+"";
      }
       if(den_ngay_lapdien != null && den_ngay_lapdien != ''){
        urlRequest += "&den_ngay_lapdien="+den_ngay_lapdien+"";
      }
      if(so_dts != null && so_dts != ''){
        urlRequest += "&so_dts="+so_dts+"";
      }
      if(so_ltt != null && so_ltt != ''){
        urlRequest += "&so_ltt="+so_ltt+"";
      }
      if(loai_ltt != null && loai_ltt != ''){
        urlRequest += "&loai_ltt="+loai_ltt+"";
      }
      if(loai_tra_soat != null && loai_tra_soat != ''){
        urlRequest += "&loai_tra_soat="+loai_tra_soat+"";
      }
//       if(loai_tra_soat != null && loai_tra_soat != ''){
//        urlRequest += "&chieu_tra_soat="+chieu_tra_soat;
//      }
      if(nhkb_chuyen != null && nhkb_chuyen != ''){
      urlRequest += "&nhkb_chuyen="+nhkb_chuyen+"";
      }
      if(nhkb_nhan != null && nhkb_nhan != ''){
        urlRequest += "&nhkb_nhan="+nhkb_nhan+"";
      }
      window.location = urlRequest;
    }
      //**************************LINK TRANG CLICK********************************************
         function goPage(page) {
            jQuery("#frmTraCuuDTS").target = ''; 
            jQuery("#frmTraCuuDTS").attr({action:'tracuudts.do'});
            jQuery("#pageNumber").val(page);
            jQuery("#frmTraCuuDTS").submit();
        }

      //**************************validFormatDate********************************************
         function validFormatDate(field) {
            allFields.removeClass('ui-state-error');
            var fieldId=field.id;
            if(!CheckDate(field)){
              jQuery("#"+fieldId).val('');
              jQuery("#"+fieldId).addClass("ui-state-error");
              jQuery("#"+fieldId).focus();
            }
        }
      function startDownload(url) {   
      document.forms[0].action = 'tracuudts.do';
       document.forms[0].submit();
      window.open(url,'report'); 
} 
  </script>
  <html:form action="/tracuudts.do"  styleId="frmTraCuuDTS">
    <input type="hidden" id="fieldNameForcus"/>
    <div class="app_error"><html:errors/></div>
      <html:hidden property="search_dts" value="true"/>
      <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
    <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
       <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg">
              <span class="title2">
                <fmt:message key="tra_cuu_dts.page.title"/>
              </span>
        </td>
        <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
      </tbody>
    </table>
      <table class="tableBound">
        <tbody>
          <tr>
            <td class="title" height="24"
                background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
                colspan="8" style="text-align:left;">
              <fmt:message key="tra_cuu_dts.page.dieukien"/>
            </td>
          </tr>
          <tr>
            <td>
              <table style="border-collapse:collapse;" class="bordertableChungTu" cellspacing="0" width="100%">
               <tbody>
               <tr>
                <td align="right" width="24%">
                  <label for="nguoi_lap"><fmt:message key="tra_cuu_dts.page.lable.nguoi_lap"/></label>
                </td>
                <td class="promptText" align="right" width="26%" >
                 <html:text styleId="ttv_nhan"  property="ttv_nhan" styleClass="fieldText" style="width:98%;" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                </td>
                <td  width="24%" valign="middle" align="right">
                  <label for="loai_tra_soat"><fmt:message key="tra_cuu_dts.page.lable.loai_tra_soat"/></label>
                </td>
                <td align="right" width="26%">
                  <html:select styleClass="selectBox"  property="loai_tra_soat" styleId="loai_tra_soat"  style="width:100%;height:20px;" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                    <html:option value=""><fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.default"/></html:option>
                    <html:option value="01"><fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.tu_do"/></html:option>
                    <html:option value="02"><fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.gan_ltt"/></html:option>
                    <html:option value="03"><fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.tra_loi"/></html:option>
                    <%--<html:option value="04"><fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.keodai"/></html:option>--%>
                  </html:select>
                </td>
              </tr>
              <%--<tr>
                <td align="right" valign="middle" width="24%">
                  <!--<label for="chieu_tra_soat"><fmt:message key="tra_cuu_dts.page.lable.chieu_tra_soat"/></label>-->
                </td>
                <td align="right" width="26%" >
                 <html:select styleClass="selectBox"  property="chieu_dts" styleId="chieu_dts"  style="width:100%;height:20px;" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                    <html:option value=""><fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.default"/></html:option>
                    <html:option value="195"><fmt:message key="tra_cuu_dts.page.option.chieu_tra_soat.di"/></html:option>
                    <html:option value="196"><fmt:message key="tra_cuu_dts.page.option.chieu_tra_soat.den"/></html:option>
                  </html:select>
                </td>
                <td  width="24%" valign="middle" align="right">
                  <!--<label for="loai_tra_soat"><fmt:message key="tra_cuu_dts.page.lable.loai_tra_soat"/></label>-->
                </td>
                 <td align="right" width="26%">
                  <html:select styleClass="selectBox"  property="loai_tra_soat" styleId="loai_tra_soat"  style="width:100%;height:20px;" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                    <html:option value=""><fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.default"/></html:option>
                    <html:option value="01"><fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.tu_do"/></html:option>
                    <html:option value="02"><fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.gan_ltt"/></html:option>
                    <html:option value="03"><fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.tra_loi"/></html:option>
                  </html:select>
                </td>
              </tr>--%>
              <tr>
                <td align="right" valign="middle">
                  <label for="trang_thai"><fmt:message key="tra_cuu_dts.page.lable.trang_thai"/></label>
                </td>
                <td align="right" width="26%">
                  <html:select styleClass="selectBox"  property="trang_thai" styleId="trang_thai" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                    <html:option value=""><fmt:message key="tra_cuu_dts.page.option.trang_thai.default"/></html:option>
                    <html:optionsCollection label="rv_meaning" value="rv_low_value" name="lstTrangThai"/>
                  </html:select>
                </td>
                <td align="right" valign="middle">
                  <label for="loai_ltt" style="padding-left:28px;"><fmt:message key="tra_cuu_dts.page.lable.loai_lenh"/></label>
                </td>
                <td align="right" width="26%">
                  <html:select styleClass="selectBox"  property="loai_ltt" styleId="loai_ltt" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                    <html:option value=""><fmt:message key="tra_cuu_dts.page.option.loai_lenh.default"/></html:option>
                    <html:option value="01"><fmt:message key="tra_cuu_dts.page.option.loai_lenh.chuyen_di"/></html:option>
                    <html:option value="02"><fmt:message key="tra_cuu_dts.page.option.loai_lenh.chuyen_den"/></html:option>
                     
                  </html:select>
                </td>
              </tr>
              <tr>
                <td align="right">
                  <label for="ma_don_vi_tra_soat"><fmt:message key="tra_cuu_dts.page.lable.don_vi_tra_soat"/></label>
                </td>
                <td class="promptText" >
                  <html:text property="ma_don_vi_tra_soat" styleId="ma_nhkb_chuyen" styleClass="fieldText" onkeydown="if(event.keyCode==13) event.keyCode=9;"  onKeyPress="return numbersonly(this,event,true)" maxlength="8" style="width:98%;" />
                </td>
                <td align="right" width="24%">
                  <label for="ma_don_vi_nhan_tra_soat"><fmt:message key="tra_cuu_dts.page.lable.don_vi_nhan_tra_soat"/></label>
                </td>
                <td class="promptText" >
                  <html:text property="ma_don_vi_nhan_tra_soat"  styleId="ma_nhkb_nhan"  styleClass="fieldText" style="width:98%;" onkeydown="if(event.keyCode==13) event.keyCode=9;"  onKeyPress="return numbersonly(this,event,true)" maxlength="8" />
                </td>
              </tr>
              <tr>
                <td align="right">
                  <label for="id"><fmt:message key="tra_cuu_dts.page.lable.so_dien_tra_soat"/></label>
                </td>
                <td class="promptText" align="left">
                  <html:text property="id" styleId="id" styleClass="fieldText" onkeydown="if(event.keyCode==13) event.keyCode=9;"  onKeyPress="return numbersonly(this,event,true)" style="width:98%;" />
                </td>
                <td align="right">
                  <label for="ltt_id"><fmt:message key="tra_cuu_dts.page.lable.so_lenh_thanh_toan"/></label>
                </td>
                <td class="promptText" align="right">
                  <html:text property="ltt_id" styleId="ltt_id" styleClass="fieldText" style="width:98%;" onkeydown="if(event.keyCode==13) event.keyCode=9;"  onKeyPress="return numbersonly(this,event,true)"/>
                </td>
              </tr>
              <tr>
                  <td align="right">
                    <label for="tu_ngay_lap_dien"><fmt:message key="tra_cuu_dts.page.lable.tu_ngay_lap_dien"/></label>
                  </td>
                  <td class="promptText" align="right">
                    <html:text styleId="tu_ngay_lap_dien" onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay_lap_dien');"
                    styleClass="fieldText" property="tu_ngay_lap_dien" style="width:58%;"
                    onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0"
                    id="tu_ngay_lap_dien_btn" width="20" style="vertical-align:middle;"/>
                    <span><fmt:message key="tra_cuu_dts.page.lable.format_date"/></span>
                    <script type="text/javascript">
                      Calendar.setup( {
                      inputField : "tu_ngay_lap_dien", // id of the input field
                      ifFormat : "%d/%m/%Y", // the date format
                      button : "tu_ngay_lap_dien_btn"// id of the button
                      });
                    </script>
                  </td>
                  <td align="right">
                    <label for="den_ngay_lap_dien"><fmt:message key="tra_cuu_dts.page.lable.den_ngay_lap_dien"/></label>
                  </td>
                  <td class="promptText" align="right">
                    <html:text styleId="den_ngay_lap_dien"  property="den_ngay_lap_dien" 
                    onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay_lap_dien');"
                    styleClass="fieldText" style="width:58%;" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                    <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0" id="den_ngay_lap_dien_btn" width="20" style="vertical-align:middle;"/>
                    <span><fmt:message key="tra_cuu_dts.page.lable.format_date"/></span>
                    <script type="text/javascript">
                      Calendar.setup( {
                      inputField : "den_ngay_lap_dien", // id of the input field
                      ifFormat : "%d/%m/%Y", // the date format
                      button : "den_ngay_lap_dien_btn"// id of the button
                      });
                    </script>
                  </td>
              </tr>
              <tr>
              <tr>
                <td align="right">
                  <label for="tu_ngay_lap_lenh"><fmt:message key="tra_cuu_dts.page.lable.tu_ngay_lap_ltt"/></label>
                </td>
                <td class="promptText" align="right">
                  <html:text styleId="tu_ngay_lap_lenh"  property="tu_ngay_lap_lenh" styleClass="fieldText" style="width:58%;" 
                  onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay_lap_lenh');" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0" id="tu_ngay_lap_ltt_btn" width="20" style="vertical-align:middle;"/>
                  <span><fmt:message key="tra_cuu_dts.page.lable.format_date"/></span>
                  <script type="text/javascript">
                    Calendar.setup( {
                    inputField : "tu_ngay_lap_lenh", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "tu_ngay_lap_ltt_btn"// id of the button
                    });
                  </script>
                </td>
                <td align="right">
                  <label for="den_ngay_lap_lenh"><fmt:message key="tra_cuu_dts.page.lable.den_ngay_lap_ltt"/></label>
                </td>
                <td class="promptText" align="right">
                  <html:text styleId="den_ngay_lap_lenh" property="den_ngay_lap_lenh" 
                  onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay_lap_lenh');"
                     styleClass="fieldText" style="width:58%;" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0" id="den_ngay_lap_ltt_btn" width="20" style="vertical-align:middle;"/>
                  <span><fmt:message key="tra_cuu_dts.page.lable.format_date"/></span>
                  <script type="text/javascript">
                    Calendar.setup( {
                    inputField : "den_ngay_lap_lenh", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "den_ngay_lap_ltt_btn"// id of the button
                    });
                  </script>
                </td>
              </tr>
              <tr>
                <td colspan="8" align="center">
                  <div style="padding:10px 0px 10px 0px; ">
                    <button id="btn_timKiem" accesskey="T" type="button" class="ButtonCommon"  value="search">
                     <fmt:message key="tra_cuu_dts.page.button.search"/>
                    </button>
                 <span id="in" style="padding-left:10px;">
                  <!--<button id="btn_in" accesskey="i" type="button"
                        class="ButtonCommon">
                  <fmt:message key="tra_cuu_dts.page.button.in"/>
                </button>-->
                </span>
                  </div>
                </td>
              </tr>
            </tbody>
           
            </table>
            </td>
          </tr>
        </tbody>
      </table>
     
    </html:form>
      
        <div style="padding:10px 0px 10px 0px;">
         <table border="2" align="center" width="100%" class="borderTableChungTu"
            <thead class="TR_Selected">
              <tr >
                <th width="14%">
                  <fmt:message key="tra_cuu_dts.page.lable.so_dien_tra_soat"/>
                </th>
                <th width="9%">
                  <fmt:message key="tra_cuu_dts.page.lable.ngay_lap_dien"/>
                </th>
                <th width="14%">
                  <fmt:message key="tra_cuu_dts.page.lable.so_lenh_thanh_toan"/>
                </th>
                <th width="9%">
                  <fmt:message key="tra_cuu_dts.page.lable.ngay_lap_lenh"/>
                </th>
                <th width="15%">
                  <fmt:message key="tra_cuu_dts.page.lable.don_vi_tra_soat"/>
                </th>
                <th width="15%">
                  <fmt:message key="tra_cuu_dts.page.lable.don_vi_nhan_tra_soat"/>
                </th>
                <th width="13%">
                  <fmt:message key="tra_cuu_dts.page.lable.trang_thai"/>
                </th>
                <th width="5%">
                 <fmt:message key="tra_cuu_dts.page.lable.loai_tra_soat"/>
                </th><th width="6%">&nbsp;</th>
              </tr>
            </thead>
            <tbody>
              <logic:empty name="lstDTS">
                <tr><td colspan="9" align="center"><font color="Red">Không tìm thấy bản ghi nào!</font></td></tr>
              </logic:empty>
              <logic:notEmpty name="lstDTS">
              <%
                com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
                int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
              %>
              <logic:iterate id="items" name="lstDTS" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center">
                   <bean:write name="items" property="id"/>
                  </td>
                  <td  align="center">
                   <bean:write name="items" property="ngay_nhan"/>
                  </td>
                  <td align="center">
                   <bean:write name="items" property="ltt_id"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ngay_nhan"/>
                  </td>
                  <td align="left">
                    <bean:write name="items" property="ten_don_vi_tra_soat"/>
                  </td>
                  <td align="left">
                   <bean:write name="items" property="ten_don_vi_nhan_tra_soat"/>
                  </td>
                  <td align="left">
                    <bean:write name="items" property="mo_ta_trang_thai"/>
                  </td>
                    <td>
                      <bean:write name="items" property="loai_tra_soat"/>
                      <%--<logic:equal value="199" name="items" property="loai_tra_soat">
                        <fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.tu_do"/>
                      </logic:equal>
                      <logic:equal value="195" name="items" property="loai_tra_soat">
                        <fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.hoi"/>
                      </logic:equal>
                      <logic:equal value="196" name="items" property="loai_tra_soat">
                        <fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.tra_loi"/>
                      </logic:equal>
                      <%--<logic:equal value="299" name="items" property="loai_tra_soat">
                        <fmt:message key="tra_cuu_dts.page.option.loai_tra_soat.keodai"/>
                      </logic:equal>--%>
                    </td>
                    <td align="center">
                      <logic:equal value="199" name="items" property="loai_tra_soat">
                        <a href="javascript:makeGetRequest(<bean:write name="items" property="id"/>,199)">Xem DTS</a>
                      </logic:equal>
                       <logic:equal value="195" name="items" property="loai_tra_soat">
                        <a href="javascript:makeGetRequest(<bean:write name="items" property="id"/>,195)">Xem DTS</a>
                      </logic:equal>
                      <logic:equal value="196" name="items" property="loai_tra_soat">
                        <a href="javascript:makeGetRequest(<bean:write name="items" property="id"/>,196)">Xem DTS</a>
                      </logic:equal>
                      <%--<logic:equal value="299" name="items" property="loai_tra_soat">
                        <a href="javascript:makeGetRequest(<bean:write name="items" property="id"/>,299)">Xem DTS</a>
                      </logic:equal>--%>
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
        </div>
      
      <div style="padding:10px 10px 10px 0px;float:right ">
        <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon" tabindex="23">
          <fmt:message key="tra_cuu_dts.page.button.exit"/>
        </button>
      </div>
      <%-- ************************************--%>
    <%-- hiển thị trạng thái thêm sửa xóa KTV--%>
    <%
    if(request.getAttribute("status") != null){
    String StrStatus = request.getAttribute("status").toString();
    String id = request.getAttribute("nsdID")==null?"":request.getAttribute("nsdID").toString();
    %>
    <font color="Red" dir="ltr">
      <fmt:message key="<%=StrStatus%>">
        <fmt:param>
          <%=id%>
        </fmt:param>
      </fmt:message>
    </font>
    <%}%>
    <%-- ************************************--%>
  <!------------------------------------------ Message confirm ------------------------------->
  <div id="dialog-confirm" title="<fmt:message key="tra_cuu_dts.page.title.dialog_confirm"/>" >
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
      <span id="message_confirm"></span>
    </p>
  </div>
  
<!-- ---------------------------------Message --------------------------------->
  <div id="dialog-message" title="<fmt:message key="tra_cuu_dts.page.title.dialog_message"/>">
    <p>
      <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="message"></span>
    </p>
  </div>
<%@ include file="/includes/ttsp_bottom.inc"%>
