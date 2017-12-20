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
             //    jQuery('#loai_tk option:eq(3)').attr('selected', 'selected');

    jQuery.noConflict();
    initDialogMsg();



    jQuery(document).ready(function()
      {                
       disabletext();
        if ('<%=(request.getAttribute("EXIST"))%>'=="KB") {
        alert('Số tài khoản của ngân hàng này đã tồn tại.') ;
        document.getElementById('btn_Thoat').focus();
      }
      jQuery("#dialog-message").dialog("close");
      jQuery("#btnLov").click(function () {
        jQuery("#dialog-message").dialog("open");
      });
       jQuery("#btn_Ghi").click(function (){
          var hieu_luc_tungay = jQuery('#hieu_luc_tungay').val();
          var hieu_luc_denngay = jQuery('#hieu_luc_den_ngay').val();
          if(hieu_luc_tungay != "" && hieu_luc_denngay != ""){
             if(hieu_luc_tungay > hieu_luc_denngay){
              alert("Ngày hiệu lực không được lớn hơn ngày hết hiệu lực.");
              return false;
             }
          }
        
//        var tu_ngay=document.getElementById('hieu_luc_tungay').value;
//        var den_ngay=document.getElementById('hieu_luc_den_ngay').value;
//            if (isGreaterCurrDate(tu_ngay)){
//             alert('Bạn phải nhập từ ngày lớn hơn hoặc bằng ngày hiện tại');
//             document.getElementById('hieu_luc_tungay').focus();
//                      return false; 
//            }
//             if (isGreaterCurrDate(den_ngay)){
//             alert('Bạn phải nhập đến ngày lớn hơn hoặc bằng ngày hiện tại');
//              document.getElementById('hieu_luc_den_ngay').focus();
//                      return false; 
//            }
//
//            if (CompareDate(tu_ngay, den_ngay) ==  - 1) {
//                  alert('Bạn phải nhập đến ngày hiệu lực lớn hơn hoặc bằng từ ngày hiệu lực');
//                  document.getElementById('hieu_luc_den_ngay').focus();
//                  return false;
//              }
           if(checkRequire()){
            if('<%=(request.getParameter("action"))%>'=="EDIT"){
              var id_tk = '<%=(request.getParameter("id_tk"))%>';
              document.forms[0].action = 'suatknhkb.do?id_tk='+id_tk;
 
            }
            else {
              document.getElementById('ma_kb').focus();
              document.forms[0].action = 'ghitknhkb.do';
            }
             document.forms[0].submit();
            
           }
          });
           jQuery("#btn_Thoat").click(function (){
            if (confirm('Bạn có chắc chắn muốn thoát khỏi chức năng này?') == false)
              return false;
            else {
            var rowid='<%=(request.getParameter("rowid"))%>';
              document.forms[0].action = 'thoatghitknhkb.do?rowid='+rowid;
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
        if ( document.getElementById('so_tk').value.trim()=="") {
          alert('Số tài khoản không được để trống');
          document.getElementById('so_tk').focus();
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
        if ( document.getElementById('ma_nt').value.trim()=="") {
          alert('Phải chọn loại tiền');
          document.getElementById('ma_nt').focus();
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
      if (ma_nh.length<4 && ma_nh.length>0){
         document.getElementById(textten).value = "";
         document.getElementById(textma).value = "";
         document.getElementById(textid).value ="";
         alert('Mã kho bạc phải có 4 số');
          document.getElementById('ma_kb').focus();
          return false ;
       }else if(ma_nh.length>0 || ''!=ma_nh.trim() ){
       
        var objJSON = {"ma" : ma_nh};  
        var strJSON = encodeURIComponent(JSON.stringify(objJSON));  
        new Ajax.Request("loadTenDMKB.do",
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
   
   function onLoading(){
        
      	document.getElementById('indicator').style.display = "";               
   }
   function onError(){
       alert("Error");
   } 
   function disabletext() {
      if('<%=(request.getParameter("action"))%>'=="EDIT"){
         document.getElementById('trang_thai').focus();
         document.getElementById('ma_kb').disabled = true;
         document.getElementById('ma_nh').disabled = true;
//         document.getElementById('so_tk').disabled = true;
      }
      else {
         document.getElementById('ma_kb').disabled = false;
         document.getElementById('ma_nh').disabled = false;
         document.getElementById('so_tk').disabled = false;  
         document.getElementById('ma_kb').focus();
      }
   }
   function isGreaterCurrDate(dateValue){
    var dayDiff;	
    var d=new Date();
    var y=d.getFullYear();
    var m=d.getMonth()+1;
    var day=d.getDate();
    if (m<10) m="0"+m;
    if (day<10) day="0"+day;
    var curDate=day+"/"+m+"/"+y
    date1 = new Date(dateValue);	
    date2 = new Date(curDate);
    if(CompareDate(curDate, dateValue) ==  - 1)
      return  true;
    else
      return false;
  }
  function initDialogMsg(){
    jQuery("#dialog-message").dialog({
      autoOpen: false,
      maxHeight:160,
      width:380,
      modal: true,
      buttons: {
        Ok: function() {
          jQuery(this).dialog( "close" );
          window.location = 'listLttDenAdd.do';
          var idFieldFocus=jQuery("#focusField").val();
          if(idFieldFocus!=null && idFieldFocus!='')
            jQuery("#"+idFieldFocus).focus();
        }
     },
     "Đóng": function() {
        window.location = 'listLttDenAdd.do';
      },
      close: function(event, ui) {
        window.location = 'listLttDenAdd.do';
      }
    });
  }  
</script>
<div id="body">
 <html:form styleId="frmthemmoitknhkb"   action="/themtaikhoan.do">
        
<table border="0" cellspacing="2" cellpadding="0" width="100%"
           align="center">
      <tbody>
         <tr>
              <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
              <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
                <span class=title2>
                <fmt:message key="tknhkb.page.title_themmoi"/>
                </span>
              </td>
              <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
              <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
              <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
            </tr> 
      </tbody>
    </table>
      <logic:notEmpty name="message">
        <font color="Red"><%=request.getAttribute("message")%></font>
      </logic:notEmpty>
      <table  border="0" cellspacing="2" cellpadding="2" class="bordertableChungTu" width="100%">
      <tbody>
        <tr>
          <td> 
           <table width="100%" cellspacing="2" cellpadding="0" align="center" class="bordertableChungTu">
            <tr>
              <td align="right" width="10%">
                <label for="kho_bac" style="padding-right:10px">
                  <fmt:message key="tknhkb.page.lable.kho_bac"/>
                </label>
              </td>
              <td class="promptText" align="right" width="40%">
                 <html:text property="ma_kb" styleId="ma_kb" styleClass="fieldText" style="width:140px;" maxlength="4"
                   onkeydown="nextFocus();"  onblur="textlostfocus(this);getTen_KB('ma_kb','ten_kb','kb_id')" onfocus="textfocus(this);"/>
                <html:text property="ten_kb" styleId="ten_kb" styleClass="fieldTextTrans" readonly="true" onkeydown="nextFocus();"
                     onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 200px;"/>
                 <html:hidden property="kb_id" styleId="kb_id"/>
                  <html:hidden property="id" styleId="id"/>
              </td>
              <td width="10%" align="right">
                <label for="ngan_hang" style="padding-right:10px">
                  <fmt:message key="tknhkb.page.lable.ten_nh"/>
                </label>
              </td>
            <td class="promptText">
            <html:text property="ma_nh" styleId="ma_nh" styleClass="fieldText" style="width:140px;"  maxlength="8"
                     onkeydown="nextFocus();" onblur="textlostfocus(this);getTen_NH('ma_nh','ten_nh','nh_id')" onfocus="textfocus(this);"/>
                <html:text property="ten" styleId="ten_nh" styleClass="fieldTextTrans" readonly="true" onkeydown="nextFocus();"
                     onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 180px;"/>
                 <html:hidden property="nh_id" styleId="nh_id"/>
                 
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="so_tk" style="padding-right:10px">
                <fmt:message key="tknhkb.page.lable.so_tai_khoan"/>
              </label>
            </td>
            <td class="promptText">
             <html:text styleId="so_tk" property="so_tk"
                           styleClass="fieldText" style="width:140px;" maxlength="18" 
                           onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                           onfocus="textfocus(this);"/>
            </td>
            <td align="right" width="13%">
              <label for="ma_don_vi_nhan_tra_soat" style="padding-right:10px">
                <fmt:message key="tknhkb.page.lable.trang_thai"/>
              </label>
            </td>
            <td class="promptText" colspan="3">
            <html:select styleClass="selectBox" property="trang_thai"
                           styleId="trang_thai" style="width:142px;"
                           onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                           onfocus="textfocus(this);">
              <html:option value="01" >Hoạt động</html:option>  
              <html:option value="02" >Khóa</html:option>
              </html:select>
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="id" style="padding-right:10px">
                <fmt:message key="tknhkb.page.lable.han_muc_co"/>
              </label>
            </td>
            <td class="promptText" align="left">
              <html:text property="han_muc_co" styleId="han_muc_co" styleClass="fieldText" style="width:140px;"
                         onKeyPress="return numbersonly(this,event,true)"  onkeydown="nextFocus();"  maxlength="18"
                         onblur="textlostfocus(this);"
                         onfocus="textfocus(this);"/>
            </td>
            <td align="right">
              <label for="ltt_id" style="padding-right:10px">
                <fmt:message key="tknhkb.page.lable.han_muc_no"/>
              </label>
            </td>
            <td class="promptText" align="right">
              <html:text property="han_muc_no" styleId="han_muc_no" styleClass="fieldText"
                         style="width:140px;" maxlength="18"  onkeydown="nextFocus();"
                         onKeyPress="return numbersonly(this,event,true)"
                         onblur="textlostfocus(this);"
                         onfocus="textfocus(this);"/>
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="loai_tk" style="padding-right:10px">
                Lo&#7841;i TK
              </label>
            </td>
            <td class="promptText" align="right">
              <html:select styleClass="selectBox" property="loai_tk"
                           styleId="loai_tk" style="width:142px;"
                           onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                           onfocus="textfocus(this);">
              <html:option value="01" >TK thanh toán tổng hợp</html:option>  
              <html:option value="02" >TK thanh to&#225;n</html:option>
              <html:option value="03" >TK chuy&#234;n thu</html:option>
              </html:select>
            </td>
            <td align="right">
              <label for="loai_gd" style="padding-right:10px">
                Lo&#7841;i giao d&#7883;ch
              </label>
            </td>
            <td class="promptText" align="right">
              <html:select styleClass="selectBox" property="loai_gd"
                           styleId="loai_gd" style="width:142px;"
                           onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                           onfocus="textfocus(this);">
              <html:option value="01" >C&#243; TTSP v&#224; PHT</html:option>  
              <html:option value="02" >Ch&#7881; c&#243; TTSP</html:option>
              <html:option value="03" >Ch&#7881; c&#243; PHT</html:option>
              </html:select>
            </td>
          </tr>
          <tr>
            <td align="right" bordercolor="#e1e1e1" style="padding-right:10px" >Loại tiền</td>
            <td>
              <html:select property="ma_nt" styleId="ma_nt" onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:142px;">  
                <html:option value="">Chọn loại tiền</html:option>
                <html:optionsCollection name="tienTe" value="ma" label="ma"/>
              </html:select>
            </td>
            <td align="right" bordercolor="#e1e1e1" style="padding-right:10px">Quyết toán</td>
            <td>
              <html:select property="quyet_toan" styleId="quyet_toan" onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                <html:option value="Y">Có</html:option>
                <html:option value="N">Không</html:option>
              </html:select>
              <font color="Red"><i>(Chọn không đối với TK của các ngoại tệ yếu không QT)</i></font>
            </td>
          </tr>
          <tr>
             <td align="right" bordercolor="#e1e1e1" style="padding-right:10px">Ngày hiệu lực</td>
            <td>
              <html:text property="hieu_luc_tungay" styleId="hieu_luc_tungay" styleClass="fieldText"
                       onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('hieu_luc_tungay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       style="WIDTH: 112px;"/>
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="tu_ngay_btn" width="7%"
                   style="vertical-align:middle;"/>
              <script type="text/javascript">
                Calendar.setup( {
                    inputField : "hieu_luc_tungay", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "tu_ngay_btn"// id of the button
                });
              </script>
            </td>
            <td align="right" bordercolor="#e1e1e1" style="padding-right:10px">Ngày hết hiệu lực</td>
            <td>
              <html:text property="hieu_luc_den_ngay" styleId="hieu_luc_den_ngay" styleClass="fieldText"
                       onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('hieu_luc_den_ngay');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       style="WIDTH: 112px;"/>
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="den_ngay_btn" width="7%"
                   style="vertical-align:middle;"/>
              <script type="text/javascript">
                Calendar.setup( {
                    inputField : "hieu_luc_den_ngay", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "den_ngay_btn"// id of the button
                });
              </script>
            </td>
          </tr>
          <tr>
            <td colspan="8" align="center">
              <div style="padding:10px 0px 10px 0px; ">
              <SPAN>
                <button id="btn_Ghi" accesskey="g" type="button"
                        class="ButtonCommon"  value="ghi">
                  <fmt:message key="tknhkb.page.button.save"/>
                </button>
                </span>
                <span><button id="btn_Thoat" accesskey="o" type="button"
                        class="ButtonCommon" value="thoat">
                  <fmt:message key="tknhkb.page.button.exit"/> </button></span>
              </div>
            </td>
          </tr>
          </table>
          </td>
      </tbody>
    </table>
    </html:form>
  <%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
    var loai_tk = '<%=(request.getParameter("p_loai_tk"))%>';
    var loai_gd = '<%=(request.getParameter("p_loai_gd"))%>';
    
    jQuery('#loai_tk option[value="'+loai_tk+'"]').attr('selected', true);
    jQuery('#loai_gd option[value="'+loai_gd+'"]').attr('selected', true);

</script>