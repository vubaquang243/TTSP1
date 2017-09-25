  <%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<!--<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dts.noibo.js"
        type="text/javascript">
</script>-->
<script language="JavaScript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dts_noibo.js"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.DTSoatNoiBoResource"/>
<title><fmt:message key="DTSoatNoiBo.page.title"/></title>
<%
  // tham so truyen vao de focus len lydo_ktt_day_lai
  String strLoaiUserFocus = "";  
  strLoaiUserFocus = request.getAttribute("chucdanh").toString();   
%>
<script type="text/javascript">
      jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).ready(function () {
      //role user
      if('<%=request.getAttribute("typeExit")%>' == "true"){
        selectedTopRowNoiBoDi(jQuery("#col_dts_0").val(),"row_dts_0",'<%=request.getAttribute("chucdanh")%>',true);
      }else{
        selectedTopRowNoiBoDi(jQuery("#col_dts_0").val(),"row_dts_0",'<%=request.getAttribute("chucdanh")%>',false);
//        roleUserDTSNoiBoDi()
      }
      // chu ky duyet 
      strLoaiUser = '<%=request.getAttribute("chucdanh")%>'; 
      var levelExit =0;
      //
      //litmit char truong noi dung va ly do
      jQuery("#noidung").keyup(function () {
          limitCharsDTSNoiBo(jQuery("#noidung").attr('id'), 210);
      });
      jQuery("#lydo_ktt_day_lai").keyup(function () {
          limitCharsDTSNoiBo(jQuery("#lydo_ktt_day_lai").attr('id'), 210);
      }); 
      //end
      //dialog message
      jQuery("#dialog-message-check").dialog( {
        autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              Ok : function () {    
              jQuery(this).dialog("close");
              var idFieldFocus = jQuery("#focusField").val();
                  if (idFieldFocus != null && idFieldFocus != ''){
                    jQuery("#" + idFieldFocus).addClass('ui-state-error'); 
                    jQuery("#" + idFieldFocus).focus(); 
                  }
                }
              }
      });
      jQuery("#dialog-message").dialog( {
          autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              Ok : function () {
                  refreshGridDTSNoiBo();
                  jQuery("#ma_don_vi_nhan_tra_soat").removeClass('ui-state-error');
                  jQuery("#noidung").removeClass('ui-state-error');
                  jQuery("#lydo_ktt_day_lai").removeClass('ui-state-error');
                  jQuery(this).dialog("close");                  
//                  defaultRowSelectedNBo();
              }
          }
      });
      
      //dialog confirm message	
      jQuery("#dialog-confirm").dialog( {
          autoOpen : false, resizable : false, height : 200, width : 380, modal : true, buttons :  {
              "Có" : function () {
                  var action = jQuery("#evenButtonBefore").val();                  
                  if(exitTemp==null){
                        if (action != null && action != '<%=AppConstants.ACTION_EXIT%>') {                            
                            if (action == '<%=AppConstants.ACTION_CANCEL%>' || 
                                 action == '<%=AppConstants.ACTION_EDIT%>') {
                                //check status
                                updateExcuteDTSNoiBo(action);                                
                            }else if(action == '<%=AppConstants.ACTION_REJECT%>'){
                                if(jQuery("#lydo_ktt_day_lai").val().trim() == ''){
                                   jQuery("#dialog-message-check").html('Yêu cầu nhập lý do đẩy lại !');
                                   jQuery("#dialog-message-check").dialog("open");
                                   jQuery("#focusField").val('lydo_ktt_day_lai');
                                }else{
                                  updateExcuteDTSNoiBo(action);   
                                }
                            }else if(action == '<%=AppConstants.ACTION_APPROVED%>'){
//                              if(!kyDTSNoiBoDi()){
//                                alert("Bạn ký Không thành công");
//                              }else{
                                 updateExcuteDTSNoiBo(action);  
//                               }
                            }
                            else if (action == '<%=AppConstants.ACTION_ADD%>') {
                                //check status
                                addExcFormDTSNoiBo();                                
                            }
                       } 
                        
                  } else{
                      if(levelExit ==1){
                        returnViewDTSNoiBo();
                      }else{
                        exitViewDTSNoiBo();
                     }                 
                 }  
                jQuery(this).dialog("close");    
              },              
              "Không" : function () {
                  exitTemp = null;
                  jQuery(this).dialog("close");
              }
          }
      });
      
      //dialog form Tra cuu dien tra soat
      jQuery("#dialog-form").dialog( {
          autoOpen : false, height : 350, width : 330, modal : true, buttons :  {
              "Tìm kiếm" : function () {                  
                  findDTSNoiBoDi();
                  jQuery(this).dialog("close");
              },
             "Thoát" : function () {
                  jQuery(this).dialog("close");
              }
          },
              "Đóng" : function () {
          }
      });
      //end 
      //**************************BUTTON Them moi DTS CLICK********************************************
      jQuery("#btnThem").click(function () {
          jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_ADD%>');
          resetFormThemDTSNoiBo();
          fillDataThemDTSNoiBo();
          buttonThemDTSNBo(); 
          levelExit = 1;
  });

      //**************************BUTTON Sua CLICK********************************************
      jQuery("#btnSua").click(function () {
           jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_EDIT%>');
           if (selectedRowBeforeClickButtonNoiBo()) {
              var trang_thai_correct = jQuery("#trang_thai").val();              
              if(trang_thai_correct == '01'){
                  jQuery("#noidung").val();
                  buttonSuaDTSNBo();
                  levelExit = 1;
              }
              else{
                if(trang_thai_correct == '03'){
                  jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.khong_duoc_sua_duyet"/>');
                  jQuery("#dialog-message-check").dialog("open");
                }else if(trang_thai_correct == '02'){
                  jQuery("#dialog-message-check").html('<fmt:message key="XuLyDTSoatTuDo.page.message.khong_duoc_sua_choKTT"/>');
                  jQuery("#dialog-message-check").dialog("open");
                }else{
                  jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.khong_duoc_sua"/>');
                  jQuery("#dialog-message-check").dialog("open");
                }
              } 
           }
      });
      //**************************BUTTON Ghi CLICK********************************************
      jQuery("#btnGhi").click(function () {
          var bValid = true;
          var evenButtonBefore = jQuery("#evenButtonBefore").val();
          
          exitTemp = null;
          if (evenButtonBefore != null && evenButtonBefore == 'ADD') {
              if (jQuery("#noidung").val() == null || jQuery("#noidung").val() == '') {
                  jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.noi_dung_empty"/>');
                  jQuery("#dialog-message-check").dialog("open");
                  bValid = false;
                  jQuery("#focusField").val('noidung');
              }else if(jQuery("#ma_don_vi_nhan_tra_soat").val() == null || jQuery("#ma_don_vi_nhan_tra_soat").val() == ''){              
              
                  jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.nh_kb_nhan_empty"/>');
                  jQuery("#dialog-message-check").dialog("open");
                  bValid = false;
                  jQuery("#focusField").val('ma_don_vi_nhan_tra_soat');
              }
              if (bValid) {
                  var NH_KB_chuyen_Code = jQuery("#ma_don_vi_tra_soat").val();
                  var NH_KB_nhan_Code = jQuery("#ma_don_vi_nhan_tra_soat").val();
                  if(NH_KB_chuyen_Code == NH_KB_nhan_Code){
                    jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.nhkb_trungnhau"/>');
                    jQuery("#dialog-message-check").dialog("open"); 
                    //jQuery("#ma_don_vi_nhan_tra_soat").jec('').focus();
                  }else if(NH_KB_nhan_Code.length!=8){
                    jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.nhkb_nhan_maxlength"/>');
                    jQuery("#dialog-message-check").dialog("open"); 
                  }else{
                    jQuery("#dialog-confirm").html('<fmt:message key="DTSoatNoiBo.page.message_confirm.them_dts"/>');
                    jQuery("#dialog-confirm").dialog("open");
                  }                  
                  
              }
          }
          if (jQuery("#evenButtonBefore") != null && jQuery("#evenButtonBefore").val() == 'EDIT') {
              if (jQuery("#noidung").val() == null || jQuery("#noidung").val() == '') {
                  jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.noi_dung_empty"/>');
                  jQuery("#dialog-message-check").dialog("open");
                  bValid = false;
                  jQuery("#focusField").val('noidung');
              }else if(jQuery("#ma_don_vi_nhan_tra_soat").val() == null || jQuery("#ma_don_vi_nhan_tra_soat").val() == ''){              
                  jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.nh_kb_nhan_empty"/>');
                  jQuery("#dialog-message-check").dialog("open");
                  bValid = false;
                  jQuery("#focusField").val('ma_don_vi_nhan_tra_soat');
              }
              if (bValid) {
                  var NH_KB_chuyen_Code_Sua = jQuery("#ma_don_vi_tra_soat").val();
                  var NH_KB_nhan_Code_Sua = jQuery("#ma_don_vi_nhan_tra_soat").val();
                  if(NH_KB_chuyen_Code_Sua == NH_KB_nhan_Code_Sua){
                    jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.nhkb_trungnhau"/>');
                    jQuery("#dialog-message-check").dialog("open"); 
                    jQuery("#focusField").val('ma_don_vi_nhan_tra_soat');
                  }else if(NH_KB_nhan_Code_Sua.length!=8){
                    jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.nhkb_nhan_maxlength"/>');
                    jQuery("#dialog-message-check").dialog("open"); 
                    jQuery("#focusField").val('ma_don_vi_nhan_tra_soat');
                  }else{
                    jQuery("#dialog-confirm").html('<fmt:message key="DTSoatNoiBo.page.message_confirm.sua_dts"/>');
                    jQuery("#dialog-confirm").dialog("open");
                  }
              }
          }
          
          if (evenButtonBefore != null && evenButtonBefore == 'CANCEL') {
          if (bValid) {
                  jQuery("#dialog-confirm").html('<fmt:message key="DTSoatNoiBo.page.message_confirm.huy_dts"/>');
                  jQuery("#dialog-confirm").dialog("open");
              }
              
          }
      });

      //**************************BUTTON Huy CLICK********************************************
      jQuery("#btnHuy").click(function () {
          jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_CANCEL%>');
          var evenButtonBefore = jQuery("#evenButtonBefore").val();
          if (selectedRowBeforeClickButtonNoiBo()) {
              if (evenButtonBefore != null && evenButtonBefore == 'CANCEL') {
                      var trang_thai_correct = jQuery("#trang_thai").val();
                      if(trang_thai_correct != '03' && trang_thai_correct != '04'){
                        jQuery("#dialog-confirm").html('<fmt:message key="DTSoatNoiBo.page.message_confirm.huy_dts"/>');
                        jQuery("#dialog-confirm").dialog("open");
                      }else{
                          if(trang_thai_correct == '04'){
                            jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.khong_duoc_huy"/>');
                            jQuery("#dialog-message-check").dialog("open");
                          }else{
                            jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.khong_duoc_huy_duyet"/>');
                            jQuery("#dialog-message-check").dialog("open");
                          }
                          
                      }
                      
              }
          }
      });
      //**************************BUTTON Duyet CLICK********************************************
      jQuery("#btnDuyet").click(function () { 
          jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_APPROVED%>');
          var evenButtonBefore = jQuery("#evenButtonBefore").val();
          if (selectedRowBeforeClickButtonNoiBo()) {
             if (evenButtonBefore != null && evenButtonBefore == 'APPROVED') {   
                        var trang_thai_correct = jQuery("#trang_thai").val();
                        if(trang_thai_correct == '02'){                           
                           jQuery("#dialog-confirm").html('<fmt:message key="DTSoatNoiBo.page.message_confirm.duyet_dts"/>');                           
                           jQuery("#dialog-confirm").dialog("open");                           
                        }
                        else{
                          jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.khong_duoc_duyet"/>');
                          jQuery("#dialog-message-check").dialog("open");
                        }   
                  }
          } 
      });
      function kyDTSNoiBoDi() {
          try {
              var cert = jQuery("#eSign")[0];
              cert.InitCert();
              var serial = cert.Serial;
              jQuery("#certSerial").val(serial);
              var nd = jQuery("#noidung").val();
              var sign = cert.Sign(nd);
              jQuery("#signature").val(sign);
              return true;
          }
          catch (e) {
              alert("Ký duyệt điện tra soát nội bộ đi không thành công, chữ ký số không hợp lệ ! \n"+ e.description);
              return false;
          }
      }
      //**************************BUTTON Day lai CLICK********************************************
      jQuery("#btnDayLai").click(function () {
          jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_REJECT%>');
          var evenButtonBefore = jQuery("#evenButtonBefore").val();
          if (selectedRowBeforeClickButtonNoiBo()) {
              var trang_thai_correct = jQuery("#trang_thai").val();
                  if (evenButtonBefore != null && evenButtonBefore == 'REJECT') {                 
                       if(trang_thai_correct == '02'){
                        jQuery("#dialog-confirm").html('<fmt:message key="DTSoatNoiBo.page.message_confirm.day_lai_dts"/>');
                        jQuery("#dialog-confirm").dialog("open");
                       }
                       else{
                          jQuery("#dialog-message-check").html('<fmt:message key="DTSoatNoiBo.page.message.khong_duoc_daylai"/>');
                          jQuery("#dialog-message-check").dialog("open");
                    }   
                      
                  }
          }
      });
      //**************************BUTTON Thoat CLICK********************************************
      var exitTemp = null;
      jQuery("#btnExit").click(function () {           
          exitTemp = '<%=AppConstants.ACTION_EXIT%>';     
          var bCheck = '<%=request.getAttribute("typeExit")%>';
          
           if(bCheck=='true'){
           var ttv_nhan_back = '<%=request.getParameter("ttv_nhan")%>';
           var trang_thai_back= '<%=request.getParameter("trang_thai")%>';
           var tu_ngay_back= '<%=request.getParameter("tu_ngay")%>';
           var den_ngay_back= '<%=request.getParameter("den_ngay")%>';
           var so_dts_back= '<%=request.getParameter("so_dts")%>';
           var ten_nhkb_chuyen_back = '<%=request.getParameter("ten_nhkb_chuyen")%>';
           var ten_nhkb_nhan_back = '<%=request.getParameter("ten_nhkb_nhan")%>';
           var urlBack = "TraCuuDTSoatNoiBo.do?action=Back";
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
            if(so_dts_back != null &&so_dts_back!='null'){
             urlBack += "&so_dts_back="+so_dts_back+"";
           }
           if(ten_nhkb_chuyen_back!=null && ten_nhkb_chuyen_back!= 'null'){
              urlBack += "&ten_nhkb_chuyen_back="+ten_nhkb_chuyen_back+"";
            }
            if(ten_nhkb_nhan_back!=null && ten_nhkb_nhan_back!= 'null'){
               urlBack += "&ten_nhkb_nhan_back="+nhkb_nhan_back+"";
            }
           document.forms[0].action = urlBack;
           document.forms[0].submit();
          }else{
           jQuery("#dialog-confirm").html('<fmt:message key="DTSoatNoiBo.page.message_confirm.thoat"/>');
          jQuery("#dialog-confirm").dialog("open");
          }
      });

      //**************************BUTTON Tim kiem CLICK********************************************
      jQuery("#btnTim").click(function () {
          jQuery("#evenButtonBefore").val('<%=AppConstants.ACTION_FIND%>');
          jQuery("#ma_ttv_tk").val('');
          jQuery("#nh_kb_nhan_tk").val('');
          jQuery("#so_dts_tk").val('');
          jQuery("#lydo_ktt_day_lai").val('')
          if ('<%=request.getAttribute("chucdanh")%>' == 'TTV') {
              jQuery("#nh_kb_nhan_tk").focus();
              jQuery("#ma_ttv_timkiem").hide();
              jQuery("#ma_ttv_timkiem").val('');
              
          }
          else {
            jQuery("#ma_ttv_timkiem").show();
            jQuery("#ma_ttv_tk").focus();
          }
          jQuery("#dialog-form").dialog("open");
      });
  });
      //********************** EXCUTE UPDATE - DELETE ***********************************  
    /*
       * action update excute
       */
    function updateExcuteDTSNoiBo(action) {
        if (action == '<%=AppConstants.ACTION_CANCEL%>' || action == '<%=AppConstants.ACTION_REJECT%>' || action == '<%=AppConstants.ACTION_APPROVED%>' || action == '<%=AppConstants.ACTION_EDIT%>') {
            jQuery.ajax( {
                type : "POST", url : "DTSoatNoiBoUpdateExc.do", data :  {
                    "action" : action,"certSerial" :jQuery("#certSerial").val(),"signature":jQuery("#signature").val(),"noidung" : jQuery("#noidung").val(), "id" : jQuery("#id").val(), "trang_thai" : jQuery("#trang_thai").val(), "ktt_duyet" : jQuery("#ktt_duyet").val(), "ngay_duyet" : jQuery("#ngay_duyet").val(), "ma_don_vi_nhan_tra_soat" : jQuery("#ma_don_vi_nhan_tra_soat").val(), "lydo_ktt_day_lai" : jQuery("#lydo_ktt_day_lai").val(), "nd" : Math.random() * 100000
                },
                success : function (data, textstatus) {
                      if(data!=null){
                              if (data.logout != null && data.logout) {
                              document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                              document.forms[0].submit();
                              }
                              else {
                                refreshGridDTSNoiBo();
                                if(data.exception_message !=null && data.exception_message != ""){
                                  jQuery("#dialog-message").html("Duy&#7879;t &#273;i&#7879;n tra so&#225;t kh&#244;ng th&#224;nh c&#244;ng : \n" + data.exception_message);
                                    jQuery("#dialog-message").dialog("open");
                                }
                                if (action == 'EDIT') {
                                    jQuery("#dialog-message").html('<fmt:message key="DTSoatNoiBo.page.message.sua_thanh_cong"/>');
                                    jQuery("#dialog-message").dialog("open");
                                }
                                else if (action == 'CANCEL') {
                                    jQuery("#dialog-message").html('<fmt:message key="DTSoatNoiBo.page.message.huy_thanh_cong"/>');
                                    jQuery("#dialog-message").dialog("open");
                                }
                                else if (action == 'REJECT') {
                                    jQuery("#dialog-message").html('<fmt:message key="DTSoatNoiBo.page.message.day_lai_thanh_cong"/>');
                                    jQuery("#dialog-message").dialog("open");
                                }
                                else if (action == 'APPROVED') {
                                     var sign = data.verifySign;
                                     var sign_dtl =  data.verifySign_dtl;
                                    if(sign.indexOf("ERROR") != -1){
                                      alert("Duyệt không thành công!\n"+sign_dtl);
                                    }
                                    if(sign.indexOf("INVALID") != -1){
                                      alert("Duyệt không thành công!\n"+sign_dtl);
                                    }
                                    var action_status = data.action_status;
                                    if(action_status == 'SUCCESS'){
                                      jQuery("#dialog-message").html('<fmt:message key="DTSoatNoiBo.page.message.duyet_thanh_cong"/>');      
                                      refreshGridDTSNoiBo();
                                      jQuery("#dialog-message").dialog("open");
                                    }else{
                                     var exception_message =  data.exception_message;
                                      if(exception_message != null && exception_message != 'null'){
                                        alert("Duyệt không thành công! \n"+exception_message);
                                      }
                                      else{
                                        alert("Duyệt không thành công! \n");
                                      }
                                    }
                                }
                            }
                      }
                },
                error : function (textstatus) {
                    jQuery("#dialog-message").html(textstatus);
                    jQuery("#dialog-message").dialog("open");
                }
            });
        }
    }
    
    //*******************************************************************************************
    //*******************************************************************************************
    function getTen_donvi_nhan_dts_NoiBo(ma_nhkb_nhan_dts) {
    var ma_nh_nhan = jQuery("#ma_don_vi_nhan_tra_soat").val();
    if (!(ma_nh_nhan == null || ma_nh_nhan == '')) {
        jQuery.ajax( {
            type : "POST", url : "XuLyDTSoatTuDoView.do", data :  {
                "ma_don_vi_nhan_tra_soat" : ma_nh_nhan, "action" : 'fillNHKB', "nd" : Math.random() * 100000
            },
            dataType : 'json', success : function (data, textstatus) {
                if (textstatus != null && textstatus == 'success') {
                    if (data != null) {
                       if (data.logout != null && data.logout) {
                          document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                          document.forms[0].submit();
                        }
                        else {
                          if(data.ten_don_vi_tra_soat != null){
                             jQuery("#ten_don_vi_nhan_tra_soat").val(data.ten_don_vi_tra_soat);  
                          }
                          else{
                            alert('Mã đơn vị kho bạc nhận không hợp lệ');
                          }
                      }
                    }
                }
            },
            error : function (textstatus) {
                alert(textstatus.messge);
            }
        });
    }
    
}
    //*******************************************************************************************
</script>
<div class="app_error"><html:errors/></div>
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
  <tbody>
         <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
         <%
          if(request.getAttribute("typeExit").equals("true")){
        %>
        Thông tin chi tiết điện tra soát
        <%
          }else{
        %>
          <fmt:message key="DTSoatNoiBo.page.title"/></span>
        <%
          }
        %>
      </td>
      <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
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
                <table class="borderChungTu" cellspacing="1" cellpadding="0" width="100%">
                  <tbody>
                    <tr>
                      <td valign="top">
                        <table class="bordertableChungTu" cellspacing="0" cellpadding="0"
                               width="100%">
                        <html:form styleId="frmDTS" action="/DTSoatNoiBo.do">
                          <tbody>
                            <tr>
                              <td width="15%" valign="top">
                                <div class="clearfix">
                                  <fieldset class="fieldsetCSS">
                                    <legend style="vertical-align:middle">
                                      <fmt:message key="DTSoatNoiBo.page.sodientrasoat"/>
                                    </legend>
                                    <div  class="sodientrasoattable">
                                      <div>
                                        <table class="data-grid" cellspacing="0" cellpadding="0"
                                               width="100%">
                                          <thead>
                                            <tr>
                                              <th class="ui-state-default ui-th-column"
                                                  height="20" width="62%;">
                                                <fmt:message key="DTSoatNoiBo.page.sodientrasoat.DTS"/>
                                              </th>
                                              <th height="20" width="28%"
                                                class="ui-state-default ui-th-column">
                                                <fmt:message key="DTSoatNoiBo.page.sodientrasoat.TinhTrang"/>
                                              </th>
                                              <th height="20" width="10%;"
                                                  class="ui-state-default ui-th-column">
                                               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                              </th>
                                            </tr>
                                          </thead>
                                        </table>
                                      </div>                   
                                      <div class="ui-jqgrid-bdiv ui-widget-content"
                                           style="height:300px;">
                                        <div>
                                          <table  class="data-grid" id="data-grid"
                                                  style="border:0px;width:100%"
                                                 cellspacing="0" cellpadding="0"                                  
                                                 width="100%">
                                            <tbody>
                                              <logic:present name="lstSDTS">
                                                <div>
                                                  <logic:notEmpty name="lstSDTS">
                                                    <logic:iterate id="items" name="lstSDTS"
                                                                   indexId="index">
                                                      <!--<tr style="border:1px;width:100%"
                                                          class="ui-widget-content jqgrow ui-row-ltr"
                                                          id='row_dts_<bean:write name="index"/>'
                                                          onclick="fillDataDTSNoiBo('<bean:write name="items" property="id"/>','row_dts_<bean:write name="index"/>',false);"
                                                          >
                                                        <td width="68%;" align="center">
                                                          <bean:write name="items"
                                                                      property="id"/>
                                                          
                                                        </td>
                                                        -->
                                                      <tr class="ui-widget-content jqgrow ui-row-ltr" 
                                                        id="row_dts_<bean:write name="index"/>" 
                                                        onkeydown="focusDayLaiDTSNoiBo('<%=strLoaiUserFocus%>',trang_thai);"
                                                        ondblclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');"
                                                        onclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');
                                                        fillDataDTSNoiBo('<bean:write name="items" property="id"/>','row_dts_<bean:write name="index"/>',false);">
                                                        <td width="40%;"  align="center" id="td_dts_<bean:write name="index"/>">
                                                          <input tabindex="0" name="row_item" id="col_dts_<bean:write name="index"/>" 
                                                          type="text" style="border:0px;font-size:10px;float:left;" 
                                                          value="<bean:write name="items" property="id"/>" 
                                                          onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)" readonly="true"/>
                                                        </td>
                                                        <td width="28%;" align="center">
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="02">
                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" border="0" title="Chờ KTT duyệt"/>
                                                          </logic:equal>
                                                           
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="01">
                                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg"
                                                                 border="0"
                                                                 title="KTT đẩy lại"/>
                                                           
                                                          </logic:equal>
                                                           
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="03">
                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif"
                                                                 border="0" title="Đã duyệt"/>
                                                          </logic:equal>
                                                           
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="04">
                                                                        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif"
                                                                 border="0" title="Hủy"/>                                            
                                                          </logic:equal>
                                                        </td>
                                                      </tr>
                                                    </logic:iterate>
                                                  </logic:notEmpty>
                                                   
                                                  <logic:empty name="lstSDTS">
                                                    <tr>
                                                      <td colspan="5" align="center">
                                                        <span class="text_red">
                                                          <fmt:message key="DTSoatNoiBo.page.sodientrasoat.error"/></span>
                                                      </td>
                                                    </tr>
                                                  </logic:empty>
                                                </div>
                                              </logic:present>
                                            </tbody>
                                          </table>
                                        </div>
                                      </div>
                                      <div>
                                        <table class="data-grid" cellspacing="0" cellpadding="0"
                                               width="100%">
                                          <thead>
                                            <tr id="refreshButton">
                                              <td  class="ui-state-default ui-th-column"
                                                  title="Refresh" id="refresh">
                                                <div style="float:left;cursor:pointer" class="ui-pg-div">
                                                  <span class="ui-icon ui-icon-refresh"
                                                        onclick="refreshGridDTSNoiBo();"></span>
                                                </div>
                                                <div class="ui-pg-div" style="float:left;"><span id="btnTim" class="ui-icon ui-icon-search" title="Tìm kiếm" style="cursor:pointer;"></span></div>
                                              </td>
                                            </tr>
                                          </thead>
                                        </table>
                                        <div style="padding-top:10px;float: left;">
                                          <fmt:message key="DTSoatNoiBo.page.label.trang_thai"/>: 
                                          <html:text property="mo_ta_trang_thai"
                                                      readonly="readonly"
                                                       styleClass="fieldTextTrans"
                                                       onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                       style="WIDTH: 120px;border:0px;font-weight:bold;" 
                                                      styleId="mo_ta_trang_thai"/>
                                          <!--<span id="mo_ta_trang_thai" style="font-weight:bold;"></span>-->
                                        </div>
                                      </div>
                                    </div>
                                  </fieldset>
                                </div>
                              </td>
                              <td width="85%" valign="top">
                                <fieldset class="fieldsetCSS">
                                  <legend style="vertical-align:middle">
                                    <fmt:message key="DTSoatNoiBo.page.thongtinchung"/>
                                  </legend>
                                  <div style="padding:5px 5px 5px 5px;">
                                      <input type="hidden" id="rowSelected"/>
                                      <input type="hidden" id="evenButtonBefore"/>
                                      <input type="hidden" id="focusField"/>
                                      <input type="hidden" id="row_selectedId"/>
                                      <input type="hidden" name="certSerial" id="certSerial" />
                                      <input type="hidden" name="signature" id="signature" />     
                                      <html:hidden property="id" styleId="id"/>
                                      <html:hidden property="nhkb_nhan" styleId="nhkb_nhan"/>
                                      <html:hidden property="nhkb_chuyen"
                                                   styleId="nhkb_chuyen"/>
                                      <html:hidden property="trang_thai" styleId="trang_thai"/>
                                     <table style="BORDER-COLLAPSE: collapse;" border="1"
                                             cellspacing="0" bordercolor="#b0c4de"
                                             cellpadding="0" width="99%">
                                        <tbody>
                                          <tr style="width:100%">
                                            <td align="right" width="16%;">
                                              <label for="nhkb_chuyen">
                                                <fmt:message key="DTSoatNoiBo.page.thongtinchung.donviTS"/>
                                              </label>
                                            </td>
                                            <td width="34%;" align="left">
                                              <html:text property="ma_don_vi_tra_soat"
                                                         styleId="ma_don_vi_tra_soat"
                                                         styleclass="fieldTextCode"
                                                         onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                         style="WIDTH:30%;"
                                                         />
                                              <html:text property="ten_don_vi_tra_soat"
                                                       styleId="ten_don_vi_tra_soat" 
                                                       readonly="readonly"
                                                       styleClass="fieldTextTrans"
                                                       onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                       onmouseout="UnTip()"
                                                       onmouseover="Tip(this.value)" 
                                                       style="WIDTH:65%;border:0px;font-weight:bold;"/>
                                            </td>
                                            <!--<td align="left" colspan="2" style="width:25%;">
                                              <b>
                                                <html:text property="ten_don_vi_tra_soat"
                                                       styleId="ten_don_vi_tra_soat" 
                                                       readonly="readonly"
                                                       styleClass="fieldTextTrans"
                                                       onmouseout="UnTip()"
                                                       onmouseover="Tip(this.value)" 
                                                       style="border:0px;font-weight:bold;"/>
                                              </b>
                                            </td>-->
                                            <td align="right" width="16%">
                                              <label for="ma_don_vi_tra_soat">
                                                <fmt:message key="DTSoatNoiBo.page.thongtinchung.donvinhanTS"/>
                                              </label>
                                            </td>
                                            <td width="34%" align="left">
                                              <%--<html:select property="ma_don_vi_nhan_tra_soat"  styleId="ma_don_vi_nhan_tra_soat" 
                                                          onblur="getTenNganHang('ma_don_vi_nhan_tra_soat', 'ten_don_vi_nhan_tra_soat', 'nhkb_nhan')"                                         
                                                          style="WIDTH:35%" tabindex="101" onkeydown="if(event.keyCode==13) event.keyCode=9; else if(event.keyCode==8) event.keyCode=46;">
                                                <logic:notEmpty name="colDMNH">
                                                  <html:optionsCollection name="colDMNH" value="ma_nh" label="ma_nh"/>
                                                </logic:notEmpty>
                                                <html:option styleClass="jecEditableOption" value=""></html:option>
                                              </html:select>--%>
                                              <html:text property="ma_don_vi_nhan_tra_soat"
                                                         styleId="ma_don_vi_nhan_tra_soat"
                                                         styleClass="fieldTextCode"
                                                         onmouseover="Tip(this.value)"
                                                         onmouseout="UnTip()"
                                                         tabindex="101"
                                                         onkeypress="return numbersonly(this,event,true) "
                                                         onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                         onblur="getTenNganHang('ma_don_vi_nhan_tra_soat', 'ten_don_vi_nhan_tra_soat', 'nhkb_nhan')"
                                                         style="width:30%;"
                                                         readonly="readonly"
                                                         maxlength="8"/>
                                              <html:text property="ten_don_vi_nhan_tra_soat" 
                                                       readonly="readonly"
                                                       styleClass="fieldTextTrans"
                                                       onmouseout="UnTip()"
                                                       onmouseover="Tip(this.value)"
                                                       onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                       style="WIDTH:65%;border:0px;font-weight:bold;" 
                                                       styleId="ten_don_vi_nhan_tra_soat"/>
                                              <%--<html:text property="ma_don_vi_nhan_tra_soat"
                                                         styleId="ma_don_vi_nhan_tra_soat"
                                                         styleclass="fieldTextCode"
                                                         onmouseover="Tip(this.value)"
                                                         onmouseout="UnTip()"
                                                         onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                                         onblur="getTen_donvi_nhan_dts_tudo(ma_don_vi_nhan_tra_soat)"
                                                         style="width:60px;"
                                                         maxlength="8"/>--%>                               
                                          
                                            </td>
                                            <!--<td align="left" colspan="2" style="width:25%;">
                                              <b>
                                                
                                                </b>
                                            </td>-->
                                          </tr>
                                          <tr>
                                            <td align="right" style="WIDTH:16%">
                                              <label for="ttv_nhan">
                                                <fmt:message key="DTSoatNoiBo.page.thongtinchung.nguoilap"/>
                                              </label>
                                            </td>
                                            <td align="left" style="WIDTH:34%">
                                              <html:text styleClass="fieldTextCode"
                                                         style="WIDTH:30%"
                                                         property="ma_ttv_nhan"
                                                         onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                         styleId="ma_ttv_nhan"/>
                                              <html:text readonly="readonly"
                                                       styleClass="fieldTextTrans"
                                                       onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                       onmouseout="UnTip()"
                                                       onmouseover="Tip(this.value)" 
                                                       style="WIDTH:65%;border:0px;font-weight:bold;" 
                                                         property="ttv_nhan"
                                                         styleId="ttv_nhan"/>         
                                            </td>
                                                                             
                                            <td align="right" style="WIDTH:16%">
                                              <label for="ngay_nhan">
                                                <fmt:message key="DTSoatNoiBo.page.thongtinchung.ngaylap"/>
                                              </label>
                                            </td>
                                            <td align="left" style="WIDTH:34%">
                                              <html:text styleClass="fieldTextCode"
                                                         style="WIDTH:30%"
                                                         property="ngay_nhan"
                                                         onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                         styleId="ngay_nhan"/>
                                            </td>
                                          </tr>
                                          <tr>
                                            <td align="right" style="WIDTH:16%">
                                              <label for="nguoi_ks_nh">
                                                <fmt:message key="DTSoatNoiBo.page.thongtinchung.KTT"/>
                                              </label>
                                            </td>
                                            <td align="left" style="WIDTH:34%">
                                              <html:text styleClass="fieldTextCode"
                                                         style="WIDTH:30%"
                                                         property="ma_ktt"
                                                         onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                         styleId="ma_ktt"/>
                                              <html:text readonly="readonly"
                                                       styleClass="fieldTextTrans"
                                                       onmouseout="UnTip()"
                                                       onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                       onmouseover="Tip(this.value)" 
                                                       style="WIDTH:65%;border:0px;font-weight:bold;" 
                                                         property="ktt_duyet"
                                                         styleId="ktt_duyet"/>
                                            </td>
                                            
                                            <td align="right" style="WIDTH:16%">
                                              <label for="ngay_ks_nh">
                                                <fmt:message key="DTSoatNoiBo.page.thongtinchung.ngaykiemsoat"/>
                                              </label>
                                            </td>
                                            <td align="left" style="WIDTH:34%">
                                              <html:text styleClass="fieldTextCode"
                                                         style="WIDTH: 30%;"
                                                         styleId="ngay_duyet"
                                                         onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                         property="ngay_duyet"/>
                                            </td>
                                          </tr>
                                          <tr>
                                            <td align="right" style="WIDTH:16%">
                                              <label for="noidung">
                                                <fmt:message key="DTSoatNoiBo.page.thongtinchung.noidung"/>
                                              </label>
                                            </td>
                                            <td colspan="4" style="WIDTH:84%">
                                              <html:textarea property="noidung"
                                                             styleId="noidung" cols="3"
                                                             onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                             rows="5" style="width:99.5%;WRAP:HARD" 
                                                             styleClass="fieldTextArea"></html:textarea>
                                               
                                              <span style="color:#FF0000"
                                                    id="word_count_noidung">Tổng số
                                                <span id="display_count">210 kí tự</span></span>
                                            </td>
                                          </tr>
                                          <tr>
                                            <td align="right" style="WIDTH:16%">
                                              <label for="lydo_ktt_day_lai">
                                                <fmt:message key="DTSoatNoiBo.page.thongtinchung.lydo"/>
                                              </label>
                                            </td>
                                            <td colspan="4" style="WIDTH:84%">
                                              <html:textarea property="lydo_ktt_day_lai"
                                                             styleId="lydo_ktt_day_lai" cols="3"
                                                             rows="5" style="width:99.5%;"
                                                             onkeydown="if(event.keyCode==13) event.keyCode=9;arrowUpDownDTSNBoDi(event)"
                                                             styleClass="fieldTextArea "></html:textarea>
                                               
                                              <span style="color:#FF0000"
                                                    id="word_count_lydo_ktt_day_lai">Tổng số
                                                <span id="display_count">210 kí tự</span></span>
                                            </td>
                                          </tr>
                                        </tbody>
                                      </table>
                                  </div>
                                </fieldset>
                              </td>
                            </tr>
                          </tbody>
                        </html:form>
                        </table>
                      </td>
                    </tr>
                    <tr align="left" style="width:auto">
                      <td height="30" align="right" valign="middle">
                        <div style="width:auto;padding:10px 5px 10px 5px;">
                          <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%">
                            <tr align="right">
                              <td>
                                <span id="them" style="padding-left:10px;">
                                  <button 
                                          id="btnThem" class="ButtonCommon"
                                          accesskey="T" type="button">
                                    <fmt:message key="DTSoatNoiBo.page.button.themmoi"/>
                                  </button></span>
                                 
                                <span id="sua" style="padding-left:10px;"> 
                                  <button 
                                          id="btnSua" class="ButtonCommon" type="button"
                                          accesskey="S">
                                    <fmt:message key="DTSoatNoiBo.page.button.sua"/>
                                  </button>
                                   </span>
                                 
                                <span id="ghi" style="padding-left:10px;"> 
                                  <button tabindex="103"
                                          id="btnGhi"class="ButtonCommon"
                                          type="button" accesskey="G">
                                    <fmt:message key="DTSoatNoiBo.page.button.ghi"/>
                                  </button>
                                   </span>
                                 
                                <span id="huy" style="padding-left:10px;"> 
                                  <button 
                                          id="btnHuy"  class="ButtonCommon"
                                          type="button" accesskey="H">
                                    <fmt:message key="DTSoatNoiBo.page.button.huy"/>
                                  </button>
                                   </span>
                                 
                                <!--<span id="timKiem" style="padding-left:10px;"> 
                                  <button 
                                          id="btnTim" class="ButtonCommon"
                                          type="button" accesskey="K">
                                    <fmt:message key="DTSoatNoiBo.page.button.timkiem"/>
                                  </button>
                                   </span>-->
                                 
                                <span id="duyet" style="padding-left:10px;"> 
                                  <button 
                                          id="btnDuyet" class="ButtonCommon" type="button"
                                          accesskey="U">
                                    <fmt:message key="DTSoatNoiBo.page.button.duyet"/>
                                  </button>
                                   </span>
                                 
                                <span id="dayLai" style="padding-left:10px;"> 
                                  <button 
                                          id="btnDayLai"  class="ButtonCommon"
                                          type="button" accesskey="A">
                                    <fmt:message key="DTSoatNoiBo.page.button.daylai"/>
                                  </button>
                                   </span>
                                 
                                <span id="thoatDTSTD" style="padding-left:10px;"> 
                                  <button 
                                          id="btnExit"  class="ButtonCommon"
                                          type="button" accesskey="O">
                                    <fmt:message key="DTSoatNoiBo.page.button.thoat"/>
                                  </button>
                                   </span>
                                 
                                <span style="padding-left:10px;"> 
                                  <input style="WIDTH: 1px; HEIGHT: 1px" type="hidden"
                                         name="thebottom"/>
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

<div id="dialog-message"
     title='<fmt:message key="DTSoatNoiBo.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<div id="dialog-message-check"
     title='<fmt:message key="DTSoatNoiBo.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<div id="dialog-form"
     title='<fmt:message key="DTSoatNoiBo.page.title.dialog_form"/>'>
  <p class="validateTips"></p>
    <table>
      <tr id="ma_ttv_timkiem" style="padding-top:10px;">
        <td align="right">          
            <label for="ma_ttv_timkiem" style="padding-left:60px;">
              <fmt:message key="DTSoatNoiBo.page.form.timkiem.ma_ttv"/>
            </label> 
        </td>
        <td>
          <input onkeydown="if(event.keyCode==13) event.keyCode=9;" type="text" name="ma_ttv_tk" id="ma_ttv_tk"
             class=" text ui-widget-content ui-corner-all"/>
        </td>        
      </tr>
       <tr id="nh_kb_nhan_timkiem" style="padding-top:10px;">
        <td align="right">
        <div style="padding-top:10px;">
        <label for="nh_bk_nhan_timkiem" style="padding-left:30px;">
          <fmt:message key="DTSoatNoiBo.page.form.timkiem.nhkb_nhan"/>
        </label>        
      </div> 
        </td>
        <td>
        <div id="nh_kb_nhan_timkiem" style="padding-top:10px;">           
        <input onKeyPress="return numbersonly(this,event,true)" onkeydown="if(event.keyCode==13) event.keyCode=9;" type="text" name="nh_kb_nhan_tk" id="nh_kb_nhan_tk"
               class=" text ui-widget-content ui-corner-all"/>
        </div>
        </td>
      </tr>
       <tr id="so_dts_timkiem" style="padding-top:10px;">
        <td align="right">
          <div id="so_dts_timkiem" style="padding-top:10px;">
            <label for="so_dts_timkiem" style="padding-left:40px;">
              <fmt:message key="DTSoatNoiBo.page.form.timkiem.sodien_trasoat"/>
            </label>
         </div>
        </td>
        <td>
        <div id="so_dts_timkiem" style="padding-top:10px;">
           <input onKeyPress="return numbersonly(this,event,true) " onkeydown="if(event.keyCode==13) event.keyCode=9;" type="text" name="so_dts_tk" id="so_dts_tk"
             class=" text ui-widget-content ui-corner-all"/>
        </div>
        </td>
      </tr>
    </table>
</div>
<div id="dialog-confirm"
     title='<fmt:message key="DTSoatNoiBo.page.title.dialog_confirm"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>

<%@ include file="/includes/ttsp_bottom.inc"%>
