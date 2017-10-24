<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt" %>
<%@ page  import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.QuanLyTKNHKBResource"/>
<script type="text/javascript">
    jQuery.noConflict();
    
    if('<%=request.getAttribute("thanhcong")!=null?request.getAttribute("thanhcong").toString():""%>'){
      alert("Thiết lập thành công.");
    }
    
    jQuery(document).ready(function()
      {                
      
       
      jQuery("#dialog-confirm").dialog({
      autoOpen: false,resizable : false,
//      height: "350px",
      maxHeight:"350",
      width: "700px",
      modal: true,
      buttons: {
        "Ghi": function() {      
        var f = document.forms[0];
          f.action = 'updateTSoSDuCOT.do';//?tu_ngay='+ngay_gd+"&ma_nh="+ma_nh+"&ma_kb="+ma_kb+"&so_du="+so_du;
          f.submit();
          jQuery( this ).dialog( "close" );
        },
        "Thoát": function() {
          jQuery( this ).dialog( "close" );
        }
      },
      "Đóng": function() {
      }
    });
    
      
        if ('<%=(request.getAttribute("EXIST"))%>'=="KB") {
        alert('Số tài khoản của ngân hàng này đã tồn tại.') ;
        document.getElementById('btn_Thoat').focus();
      }
      jQuery("#dialog-message").dialog("close");
      jQuery("#btnLov").click(function () {
        jQuery("#dialog-message").dialog("open");
      });
//       jQuery("#btn_Ghi").click(function (){
        
//        var tu_ngay=document.getElementById('tu_ngay').value;
//            if (isGreaterCurrDate(tu_ngay)){
//             alert('Bạn phải nhập từ ngày lớn hơn hoặc bằng ngày hiện tại');
//             document.getElementById('tu_ngay').focus();
//                      return false; 
//            }

//             if (isGreaterCurrDate(den_ngay)){
//             alert('Bạn phải nhập đến ngày lớn hơn hoặc bằng ngày hiện tại');
//              document.getElementById('hieu_luc_den_ngay').focus();
//                      return false; 
//            }

//            if (CompareDate(tu_ngay, den_ngay) ==  - 1) {
//                  alert('Bạn phải nhập đến ngày hiệu lực lớn hơn hoặc bằng từ ngày hiệu lực');
//                  document.getElementById('hieu_luc_den_ngay').focus();
//                  return false;
//              }
//           if(checkRequire()){
//            if('<%=(request.getParameter("action"))%>'=="EDIT"){
//              var id_tk = '<%=(request.getParameter("id_tk"))%>';
//              document.forms[0].action = 'suatknhkb.do?id_tk='+id_tk;
// 
//            }
//            else {
//              document.getElementById('ma_kb').focus();
//              document.forms[0].action = 'ghitknhkb.do';
//            }
             
//             document.forms[0].submit();
            
//           }
//          });
           jQuery("#btn_Thoat").click(function (){
            if (confirm('Bạn có chắc chắn muốn thoát khỏi chức năng này?') == false)
              return false;
            else {
              document.forms[0].action = 'mainAction.do';
              document.forms[0].submit();
          }
          });
          });
      function  checkRequire() {
       if ( document.getElementById('ma_kb').value.trim()=="") {
          alert('Số kho bạc không được để trống');
          document.getElementById('ma_kb').focus();
          return false;
        }
        if ( document.getElementById('ma_nh').value.trim()=="") {
          alert('Mã ngân hàng không được để trống');
          document.getElementById('ma_nh').focus();
          return false;
        }
        if ( document.getElementById('kb_id').value.trim()=="") {
          alert('Mã kho bạc không được để trống');
          document.getElementById('ma_kb').focus();
          return false;
        }
         if ( document.getElementById('nh_id').value.trim()=="") {
          alert('Mã ngân hàng không được để trống');
          document.getElementById('ma_nh').focus();
          return false;
        }
        return true;
      }
      function getTen_NH(textma,textten,textid) {
          v_ten = textten;   
          v_id = textid;
          v_ma=textma;
       var ma_nh =document.getElementById(textma).value;      
      if (ma_nh.length<8 || ma_nh.length>8){
          if (ma_nh.length<8 && ma_nh.length>0){
         alert('Mã ngân hàng phải là mã 8 số ');
       document.getElementById('ma_nh').focus(); 
                  return false;
        }
       }else if(ma_nh.length>0 || ''!=ma_nh.trim() ){
       
        var objJSON = {"ma" : ma_nh};  
        var strJSON = encodeURIComponent(JSON.stringify(objJSON));  
        new Ajax.Request("loadTenDMNH.do",
         {
           method: "post",
           async: false,
           parameters: "strJSON=" + strJSON,
           onSuccess: onSuccessNH,
           onError: onError,
           onLoading: onLoading
     });
	 }
 }
 function getTen_KB(textma,textten,textid) {
          v_ten = textten;   
          v_id = textid;
          v_ma=textma;
       var ma_nh =document.getElementById(textma).value;      
      if (ma_nh.length<8 && ma_nh.length>0){
         document.getElementById(textten).value = "";
         document.getElementById(textma).value = "";
         document.getElementById(textid).value ="";
         alert('Mã kho bạc phải có 8 số');
          document.getElementById('ma_kb').focus();
          return false ;
       }else if(ma_nh.length>0 || ''!=ma_nh.trim() ){
       
        var objJSON = {"ma_kb" : ma_nh};  
        var strJSON = encodeURIComponent(JSON.stringify(objJSON));  
        new Ajax.Request("loadTenDMKB8So.do",
         {
           method: "post",
           async: false,
           parameters: "strJSON=" + strJSON,
           dataType:'json',
           onSuccess: onSuccessKB, 
           onError: onError,
           onLoading: onLoading
     });
	 }
 }
  function onSuccessNH(transport, json){  
      if(json == null){      
       	document.getElementById(v_ten).value = "";
        document.getElementById(v_id).value = "";
      }else if(json.executeError != null && json.executeError != 'null'){
        document.getElementById(v_ten).value = "";
        document.getElementById(v_id).value = "";
        alert(convertToUTF8(json.executeError));
      }else if(json.ten != null && json.ten != 'null'){
        if(json.ten != '0'){          
          document.getElementById(v_ten).value = convertToUTF8(json.ten);
          document.getElementById(v_id).value = json.id;
        }else{
          document.getElementById(v_ten).value = "";
          document.getElementById(v_id).value = "";
          document.getElementById(v_ma).value = "";
          alert("Mã ngân hàng bạn nhập không tồn tại.");
       document.getElementById('ma_nh').focus(); 
        }
      }      
   }
   
   function onSuccessKB(transport, json){  
      if(json == null){      
       	document.getElementById(v_ten).value = "";
        document.getElementById(v_id).value = "";
      }else if(json.executeError != null && json.executeError != 'null'){
        document.getElementById(v_ten).value = "";
        document.getElementById(v_id).value = "";
        alert(convertToUTF8(json.executeError));
      }else if(json.ten != null && json.ten != 'null'){
        if(json.ten != '0'){          
          document.getElementById(v_ten).value = convertToUTF8(json.ten);
          document.getElementById(v_id).value = json.id;
        }else{
          document.getElementById(v_ten).value = "";
          document.getElementById(v_id).value = "";
          document.getElementById(v_ma).value = "";
          alert("Mã kho bạc bạn nhập không tồn tại.");
       document.getElementById('ma_kb').focus(); 
        }
      }      
   }
   function thietlapdanhmuc(){
      var f = document.forms[0];
      f.target = '';
      f.action = 'QuanLySoHieuKBMaNHListAction.do';
      f.submit();
   }
  function initDialogMsg(){
//    jQuery("#dialog-message").dialog({
//      autoOpen: false,
//      maxHeight:160,
//      width:380,
//      modal: true,
//      buttons: {
//        Ok: function() {
//          jQuery(this).dialog( "close" );
//          window.location = 'listLttDenAdd.do';
//          var idFieldFocus=jQuery("#focusField").val();
//          if(idFieldFocus!=null && idFieldFocus!='')
//            jQuery("#"+idFieldFocus).focus();
//        }
//     },
//     "Đóng": function() {
//        window.location = 'listLttDenAdd.do';
//      },
//      close: function(event, ui) {
//        window.location = 'listLttDenAdd.do';
//      }
//    });
  }  
</script>
<div id="body">
 <html:form  action="/initTSoSDuCOT.do">

<table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
         <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2>
                  Thiết lập thông tin COT và số dư
                </span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr> 
      </tbody>
    </table>
      <table  border="0" cellspacing="2" cellpadding="2" class="bordertableChungTu" width="100%">
      <tbody>
        <tr>
          <td> 
           <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center" class="bordertableChungTu">
           <tr>
              <td align="right" bordercolor="#e1e1e1"> 
                Loại thiết lập
              </td>
              <td>
                <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                              styleClass="fieldText"
                             style="width:145px;height:20px;" property="loai_ttin" onchange="getLTT();"
                             styleId="loai_ttin">
                  <html:option value="COT">Thiết lập COT</html:option>
                   <html:option value="SO_DU">Thiết lập số dư</html:option>
                </html:select>
              </td>
            </tr>
            <tr>
              <td align="right" width="14%">
                <label for="kho_bac">
                  <fmt:message key="tknhkb.page.lable.kho_bac"/>
                </label>
              </td>
              <td class="promptText" align="right" width="36%">
                 <html:text property="ma_kb" styleId="ma_kb" styleClass="fieldText" style="width:120px;" maxlength="8"
                   onkeydown="nextFocus();"  onblur="textlostfocus(this);getTen_KB('ma_kb','ten_kb','kb_id')" onfocus="textfocus(this);"/>
                <html:text property="ten_kb" styleId="ten_kb" styleClass="fieldTextTrans" readonly="true" onkeydown="nextFocus();"
                     onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 200px;"/>
                 <html:hidden property="kb_id" styleId="kb_id"/>
              </td>
              <td width="14%" align="right">
                <label for="ngan_hang">
                  <fmt:message key="tknhkb.page.lable.ten_nh"/>
                </label>
              </td>
            <td class="promptText" width="36%">
            <html:text property="ma_nh" styleId="ma_nh" styleClass="fieldText" style="width:120px;"  maxlength="8"
                     onkeydown="nextFocus();" onblur="textlostfocus(this);getTen_NH('ma_nh','ten_nh','nh_id')" onfocus="textfocus(this);"/>
                <html:text property="ten_nh" styleId="ten_nh" styleClass="fieldTextTrans" readonly="true" onkeydown="nextFocus();"
                     onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 200px;"/>
                 <html:hidden property="nh_id" styleId="nh_id"/>
                 
            </td>
          </tr>
          
          <tr id="tr_so_du">
            <td align="right">
                Ngày bắt đầu triển khai
            </td>
            <td>
                <html:text property="tu_ngay_sdu" styleId="tu_ngay_sdu" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay_sdu');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:36%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="tu_ngay_sdu_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "tu_ngay_sdu", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "tu_ngay_sdu_btn"// id of the button
                      });
                    </script>
            </td>

               
            <td align="right">
             Số dư
            </td>
            <td>
                <html:text property="so_du" styleId="so_du" style="width:50%"  styleClass="fieldText"  onblur="if (this.value !='') {changeValue(this.value);}" 
                        onkeypress="return numbersonly(this,event,true) " />
            </td>
          </tr>
          <tr id="tr_cot">
            <td align="right">
                Ngày bắt đầu
            </td>
            <td>
                <html:text property="tu_ngay_cot" styleId="tu_ngay_cot" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay_cot');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:36%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="tu_ngay_cot_btn"
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                      Calendar.setup( {
                          inputField : "tu_ngay_cot", // id of the input field
                          ifFormat : "%d/%m/%Y", // the date format
                          button : "tu_ngay_cot_btn"// id of the button
                      });
                    </script>
            </td>
            
          </tr>
          <tr id="row-loaiTien">
            <td align="right" bordercolor="#e1e1e1" >Loại tiền</td>
            <td>
              <html:select property="loai_tien" style="width:37%" styleId="loai_tien" onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                <html:option value="">Chọn loại tiền</html:option>
                <html:optionsCollection name="tienTe" value="ma" label="ma"/>
              </html:select>
            </td>
          </tr>
          <tr>
            <td colspan="8" align="center">
              <div style="padding:10px 0px 10px 0px; ">
               <span id="thietlapdm"> 
            <button type="button" onclick="thietlapdanhmuc()"
                    class="ButtonCommon" accesskey="m" style="width:152px">
              <span class="sortKey">T</span>hiết lập danh mục
            </button>
             </span>
              <SPAN>
                <button id="btn_Ghi" accesskey="g" type="button" onclick="chk_tso()"
                class="ButtonCommon"  value="ghi">
                  <fmt:message key="tknhkb.page.button.save"/>
                </button>
                </span>
                <span> <button id="btn_Thoat" accesskey="o" type="button"
                        class="ButtonCommon" value="thoat">
                  <fmt:message key="tknhkb.page.button.exit"/> </button></span>
              </div>
            </td>
          </tr>
          </table>
          </td>
          </tr>
      </tbody>
    </table>
    <html:hidden property="currentDate" styleId="currentDate"/>
    </html:form>
    <div id="dialog-confirm"
     title='Xác nhận cập nhật số dư'>   
      <p class="validateTips"> Đã tồn tại số dư trong ứng dung. Anh/Chị có muốn tiếp tục
      </p>
    </div>
</div>    
  <%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
var f = document.forms[0];

var dd='<%=request.getAttribute("strCurDate")%>';

   if(dd!=null&& ''!=dd){
     jQuery('#currentDate').val(dd);
   }
   
   function isGreaterCurrDate(dateValue){
    var dayDiff;
    var curDate=jQuery('#currentDate').val();
    
//    day= d.substr(0,2);
//     m=d.substr(3,2);
//     y=d.substr(6,4);
//    if (m<10) m="0"+m;
//    if (day<10) day="0"+day;
//    var curDate=day+"/"+m+"/"+y
//    alert(curDate +'----'+dateValue);
    date1 = new Date(dateValue);	
    date2 = new Date(curDate);
    if(CompareDate(curDate, dateValue) ==  - 1)
      return  true;
    else
      return false;
  }

// function fmt_money(){
//  var tien = jQuery('#so_du').val();
////  tien=tien.replace(/[.]/g,'');
////  alert(tien.replace(/ /g,''));
//  jQuery('#so_du').val(toFormatNumber(tien.replace(/ /g,''),0,'.'));
//}
  function changeValue(value) {
      if(isNaN(value))
          value = 0;
//      jQuery("#so_du").val(value);
      jQuery("#so_du").val(CurrencyFormatted(value, 'VND'));

  }
 getLTT();
    function getLTT() {
      var loai_ttin_field = jQuery("#loai_ttin").val();
      if(loai_ttin_field=='COT'){
        jQuery('#tr_so_du').hide();
        jQuery('#tr_cot').show();
        jQuery('#row-loaiTien').hide();
      }
      if(loai_ttin_field=='SO_DU'){
          jQuery('#tr_cot').hide();
          jQuery('#tr_so_du').show();
          jQuery('#row-loaiTien').show();
      }   
  }
  function check(type) {  
     if (type == 'add') {
//        var tu_ngay=document.getElementById('tu_ngay').value;
//          if (isGreaterCurrDate(tu_ngay)){
//           alert('Bạn phải nhập từ ngày lớn hơn hoặc bằng ngày hiện tại');
//           document.getElementById('tu_ngay').focus();
//                    return false; 
//          }else
        f.action = 'addTSoSDuCOT.do';     
      }
     if (type == 'close') {
        f.action = 'mainAction.do';          
      } 
       f.submit();
    }
    
function chk_tso() {
  var ma_kb = jQuery('#ma_kb').val(),
      ma_nh= jQuery('#ma_nh').val(),
      ngay_sdu = jQuery('#tu_ngay_sdu').val(), ngay_cot=jQuery('#tu_ngay_cot').val(),
      loai_ttin = jQuery('#loai_ttin').val();
      if(ma_kb.trim().length==0 || ma_nh.trim().length==0){
          alert('Cần nhập thông tin ngân hàng và kho bạc triển khai');
          return false;
      }
      if (ngay_sdu.trim().length>0 && isGreaterCurrDate(ngay_sdu)||ngay_cot.trim().length>0 &&isGreaterCurrDate(ngay_cot)){
          alert('Bạn phải nhập từ ngày lớn hơn hoặc bằng ngày hiện tại');
          return false; 
      }
      if(ngay_sdu.trim().length==0 && ngay_cot.trim().length==0){
          alert('Cần nhập thông tin ngày thiết lập');
          return false; 
      }
      if("SO_DU"==loai_ttin){
          if(jQuery('#so_du').val().trim().length==0){
              alert('Cần nhập thông tin số dư thiết lập');
              jQuery('#so_du').focus();
              return false;
          }
          if(jQuery('#loai_tien').val().trim().length==0){
              alert('Cần chọn loại tiền');
              return false;
          }
      }
      
      
    jQuery.ajax( {
        type : "POST", url : "chkTSoSDuCOT.do", 
        data :  { "loai_ttin" : loai_ttin,
                  "ma_kb": ma_kb,
                  "ma_nh":ma_nh,
                  "tu_ngay_sdu": ngay_sdu,
                  "tu_ngay_cot": ngay_cot    
        },   
        success : function (data, textstatus) {   
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {              
                    jQuery.each(data, function (i, objectDC) { 
                    var ts_cot=  objectDC.ts_cot;
                    var ts_sdu =  objectDC.ts_sdu;
                    if(ts_sdu !='undifined'&&ts_sdu!=null && ''!=ts_sdu && '0'!=ts_sdu && ts_sdu!=0&&'SO_DU'==loai_ttin){                   
                       jQuery("#dialog-confirm").dialog( "open" );
                    }else if(ts_sdu !='undifined'&& ts_cot!=null && ''!=ts_cot && '0'!=ts_cot && ts_cot!=0&&'COT'==loai_ttin){                   
                       alert(' Kho bạc đã được thiết lập COT');
                    } else {
                      check('add');
                    }
                    });
                }
            }
        }, 
        error : function (textstatus) {
            alert(textstatus);
        }
    });
}  
  
</script>