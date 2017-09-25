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
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.QuanLyTKNHKBResource"/>
 <script type="text/javascript">
    jQuery(document).ready(function()
      {
       jQuery("#btn_Ghi").click(function (){
           if(checkRequire()){
             document.forms[0].action = 'suatknhkb.do';
             document.forms[0].submit();
           }
          });
           jQuery("#btn_Thoat").click(function (){
            if (confirm('Bạn có chắc chắn muốn thoát khỏi chức năng này?') == false)
              return false;
            else {
              document.forms[0].action = 'thoatghitknhkb.do';
              document.forms[0].submit();
          }
          });
          });
      function  checkRequire() {
       if ( document.getElementById('ma_kb').value.trim()=="") {
          alert('Số kho bạc không được để trống');
          return false;
        }
        if ( document.getElementById('ma_nh').value.trim()=="") {
          alert('Mã ngân hàng không được để trống');
          return false;
        }
        if ( document.getElementById('so_tk').value.trim()=="") {
          alert('Số tài khoản không được để trống');
          return false;
        }
        return true;
      }
      function getTen_NH(textma,textten,textid) {
          v_ten = textten;   
          v_id = textid;
       var ma_nh =document.getElementById(textma).value;      
         if (ma_nh.length<8 || ma_nh.length>8){
         document.getElementById(textten).value = "";
         document.getElementById(textma).value = "";
         document.getElementById(textid).value ="";
       }else{
       
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
       var ma_nh =document.getElementById(textma).value;      
         if (ma_nh.length<4 || ma_nh.length>4){
         document.getElementById(textten).value = "";
         document.getElementById(textma).value = "";
         document.getElementById(textid).value ="";
       }else{
       
        var objJSON = {"ma" : ma_nh};  
        var strJSON = encodeURIComponent(JSON.stringify(objJSON));  
        new Ajax.Request("loadTenDMKB.do",
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
          alert("MÃ£ KB khÃ´ng tá»“n táº¡i.");
        }
      }      
   }
   function onLoading(){
        
      	document.getElementById('indicator').style.display = "";               
   }
   function onError(){
       alert("Error");
   } 
</script>
<div id="body">
 <html:form styleId="frmsuatknhkb"   action="/suatknhkb.do">
         <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
    <tbody>
      <tr >
        <td width="13">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
               width="13" height="30"/>
        </td>
       <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title" style="height:15px;">
              <font color="#006699" size="2" >
                <fmt:message key="tknhkb.page.title_themmoi"/>
              </font></span>
        </td>
        <td width="62">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg"
               width="62" height="30"/>
        </td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg"
            width="20%">&nbsp;</td>
        <td width="4">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg"
               width="4" height="30"/>
        </td>
        
      </tr>
    </tbody>
  </table>

      <table  border="1" cellspacing="0" class="bordertableChungTu" width="100%">
      <tbody>
        <tr>
          <td> 
           <table width="100%" cellspacing="0" cellpadding="0" align="center" class="bordertableChungTu">
            <tr>
              <td align="right" width="14%">
                <label for="kho_bac">
                  <fmt:message key="tknhkb.page.lable.kho_bac"/>
                </label>
              </td>
              <td class="promptText" align="right" width="36%">
                 <html:text property="ma_kb" styleId="ma_kb" styleClass="fieldText" style="width:80;" disabled="true"
                    onkeydown="nextFocus();"  onblur="textlostfocus(this);getTen_KB('ma_kb','ten_kb','kb_id')" onfocus="textfocus(this);"/>
                <html:text property="ten_kb" styleId="ten_kb" styleClass="fieldTextTransparent" readonly="true"
                     onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 200px;"/>
                 <html:hidden property="kb_id" styleId="kb_id"/>
              </td>
              <td width="14%" align="right">
                <label for="ngan_hang">
                  <fmt:message key="tknhkb.page.lable.ten_nh"/>
                </label>
              </td>
            <td class="promptText">
            <html:text property="ma_nh" styleId="ma_nh" styleClass="fieldText" style="width:100;" disabled="true"
                    onkeydown="nextFocus();" onblur="textlostfocus(this);getTen_NH('ma_nh','ten_nh','nh_id')" onfocus="textfocus(this);"/>
                <html:text property="ten" styleId="ten_nh" styleClass="fieldTextTransparent" readonly="true"
                     onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 200px;"/>
                 <html:hidden property="nh_id" styleId="nh_id"/>
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="so_tk">
                <fmt:message key="tknhkb.page.lable.so_tai_khoan"/>
              </label>
            </td>
            <td class="promptText">
             <html:text styleId="so_tk" property="so_tk" disabled="true"
                           styleClass="fieldText" style="width:120;"
                           onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                           onfocus="textfocus(this);"/>
            </td>
            <td align="right" width="13%">
              <label for="ma_don_vi_nhan_tra_soat">
                <fmt:message key="tknhkb.page.lable.trang_thai"/>
              </label>
            </td>
            <td class="promptText" colspan="3">
            <html:select styleClass="selectBox" property="trang_thai"
                           styleId="trang_thai" style="width:100;"
                           onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                           onfocus="textfocus(this);">
              <html:option value="01" >Hoạt động</html:option>  
              <html:option value="00" >Ngừng hoạt động</html:option>
              </html:select>
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="id">
                <fmt:message key="tknhkb.page.lable.han_muc_co"/>
              </label>
            </td>
            <td class="promptText" align="left">
              <html:text property="han_muc_co" styleId="han_muc_co" styleClass="fieldText" style="width:120;"
                         onKeyPress="return numbersonly(this,event,true)"  onkeydown="nextFocus();"
                         onblur="textlostfocus(this);"
                         onfocus="textfocus(this);"/>
            </td>
            <td align="right">
              <label for="ltt_id">
                <fmt:message key="tknhkb.page.lable.han_muc_no"/>
              </label>
            </td>
            <td class="promptText" align="right">
              <html:text property="han_muc_no" styleId="han_muc_no" styleClass="fieldText"
                         style="width:120;"  onkeydown="nextFocus();"
                         onKeyPress="return numbersonly(this,event,true)"
                         onblur="textlostfocus(this);"
                         onfocus="textfocus(this);"/>
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="tu_ngay_hieu_luc">
                <fmt:message key="tknhkb.page.lable.tu_ngay_hieu_luc"/>
              </label>
            </td>
            <td class="promptText" align="right">
              <html:text styleId="hieu_luc_tungay"
                         onfocus="textfocus(this);"  onkeydown="nextFocus();"
                         onblur="javascript:mask(this.value,this,'2,5','/'); validFormatDate(this);textlostfocus(this);"
                         styleClass="fieldText" property="hieu_luc_tungay"
                         style="width:120;"/>
               
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="hieu_luc_tungay_btn" width="20"
                   style="vertical-align:middle;"/>
               
              <script type="text/javascript">
                Calendar.setup( {
                    inputField : "hieu_luc_tungay", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "hieu_luc_tungay_btn"// id of the button
                });
              </script>
            </td>
            <td align="right">
              <label for="den_ngay_hieu_luc">
                <fmt:message key="tknhkb.page.lable.den_ngay_hieu_luc"/>
              </label>
            </td>
            <td class="promptText" align="right">
              <html:text styleId="hieu_luc_den_ngay" property="hieu_luc_den_ngay"
                         onfocus="textfocus(this);"  onblur="javascript:mask(this.value,this,'2,5','/');validFormatDate(this);textlostfocus(this);"
                         styleClass="fieldText" style="width:120;"/>
               
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="hieu_luc_den_ngay_btn" width="20"
                   style="vertical-align:middle;"/>
               
              <script type="text/javascript">
                Calendar.setup( {
                    inputField : "hieu_luc_den_ngay", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "hieu_luc_den_ngay_btn"// id of the button
                });
              </script>
            </td>
          </tr>
          <tr>
            <td colspan="8" align="center">
              <div style="padding:10px 0px 10px 0px; ">
              <SPAN>
                <button id="btn_Ghi" accesskey="g" type="button"
                        class="ButtonCommon" tabindex="21" value="ghi">
                  <fmt:message key="tknhkb.page.button.save"/>
                </button>
                </span>
                <span> <button id="btn_Thoat" accesskey="o" type="button"
                        class="ButtonCommon" tabindex="21" value="thoat">
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
