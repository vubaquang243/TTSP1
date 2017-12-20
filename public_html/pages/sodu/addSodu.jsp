<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
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
        type = "text/javascript" > 
</script>


<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>


<script type="text/javascript" language="javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  function goPage(page) {
      jQuery("#pageNumber").val(page);
      var f = document.forms[0];
      f.action = 'listSoDuAction.do?pageNumber=' + page;
      jQuery("#TCuuDMuc").submit();
  } 

  
  function changeValue(txt_id, allowNegativeNumber) {  
      var value = jQuery("#"+txt_id +"").val().replace(/\s/g,"");
      var loai_tien = jQuery("#loai_tien").val();
      
      if(allowNegativeNumber == undefined){
        allowNegativeNumber = false;
      }
        
        if(loai_tien == "VND"){
          jQuery("#"+txt_id +"").val(CurrencyFormatted2(value, 'VND', allowNegativeNumber));
        }else{
          jQuery("#"+txt_id +"").val(CurrencyFormatted2(value, 'USD', allowNegativeNumber));
        }

  }
  function check(type) {
      var f = document.forms[0];

      if (type == 'close') {
          f.action = 'mainAction.do';
      }
      else if (type == 'add') {
          f.action = 'addSoDuAction.do';
      }
      f.submit();
  }

</script>
<div class="app_error">
  <html:errors/>  
</div>
<div class="box_common_conten">
  <html:form action="addExcSoDuAction" method="post" styleId="TCuuDMuc" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Số dư </span>
        </td>
        <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
        <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
      </tr>
    </tbody>
  </table>
  <logic:notEmpty name="SoDuForm" property="action_status">
      <font color="Red"><bean:write name="SoDuForm" property="action_status"/></font>
  </logic:notEmpty>
  <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound"
         bordercolor="#999999" width="100%">
    <tr>
      <td align="left">
      <fieldset>
        
        <table  class="data-grid" id="data-grid" 
                                      border="0"
                                     cellspacing="1" cellpadding="1"                                  
                                     width="100%">
        <tr>
          <td width="10%" align="right">
           Mã kho bạc
          </td>
          <td align="left" width="15%">
                <html:text property="ma_kb" 
                           maxlength="8"
                           styleId="ma_kb"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="getTenNganHang('ma_kb', 'ten_nhkb_nhan', 'id_nhkb_nhan'),textlostfocus(this); this.style.backgroundColor='#ffffff';"
                           styleClass="promptText"/><font color="Red">(*)</font>
            </td>
            <td width="35%" align="left">
            <html:text property="ten_nhkb_nhan" readonly="true" styleId="ten_nhkb_nhan"
                           styleClass="fieldTextTrans" onmouseout="UnTip()"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                 
              <html:hidden property="id_nhkb_nhan" styleId="id_nhkb_nhan" value=""/>
            </td>
            <td width="10%" align="right">Số dư
            </td>
           <td align="left" width="25%">
                <html:text property="so_du"
                           maxlength="200" 
                           styleId="so_du"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="if (this.value !='') {changeValue('so_du',true);};textlostfocus(this); this.style.backgroundColor='#ffffff';" 
                          onkeypress="return numbersonly2(event,false) "
                           styleClass="promptText"
                           style="text-align:right"/>
            <font color="Red">(*)</font>
            </td>
            </tr>
            
            <tr>         
            <td align="right">
            Mã ngân hàng
            </td>
            <td align="left">
                <html:text property="ma_nh"
                           maxlength="8"
                           styleId="ma_nh"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="getTenNganHang('ma_nh', 'ten_nhkb_nhan1', 'id_nhkb_nhan');textlostfocus(this); this.style.backgroundColor='#ffffff';"
                           styleClass="promptText"/><font color="Red">(*)</font>
            </td>
            <td>
             <html:text property="ten_nhkb_nhan1" readonly="true" styleId="ten_nhkb_nhan1"
                           styleClass="fieldTextTrans" onmouseout="UnTip()"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                 
              <html:hidden property="id_nhkb_nhan" styleId="id_nhkb_nhan" value=""/>
            </td>
             <td align="right">Số dư COT
            </td>
           <td align="left">
                <html:text property="so_du_cot"
                           maxlength="200" 
                           styleId="so_du_cot"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff';textlostfocus(this);if (this.value !='') {changeValue('so_du_cot');}"
                           onkeypress="return numbersonly2(event,true) "                         
                           styleClass="promptText"
                           style="text-align:right"/>
            </td>
        </tr>
        <tr>
              <td style="text-align:right">                           
                              Loại tiền                       
              </td>
              <td >
                    <html:select styleClass="selectBox" property="loai_tien"
                                           styleId="loai_tien" style="width:125px;height:20px"
                                           onchange="changeValue('so_du_cot');changeValue('so_du',true);">
                        <html:option value="VND">VND</html:option>
                        <html:optionsCollection value="ma" label="ma" name="lstLoaiTien"/>
                    </html:select><font color="Red">(*)</font>
              </td>
              <td>
              </td>            
           <td  align="right">Ngày giao dịch</td>
                <td align="left" valign="middle">
                <html:text property="ngay_gd" styleId="ngay_gd"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_gd');textlostfocus(this);"
                           onfocus="textfocus(this);"
                           style=("width:98px")
                           value='<%=request.getParameter("ngay_gd")%>'/>         
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "ngay_gd", 
                      ifFormat : "%d/%m/%Y", 
                      button : "ngay"
                  });
                </script>
                <font color="Red">(*)</font>(dd/mm/yyyy)
              </td>
        </tr>
         <tr>
          <td align="right">
          </td>
          <td align="left">
              <html:hidden property="id" 
                           style="width:125px" 
                           maxlength="8"
                           styleId="id"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            <td align="right">
            </td>
            <td align="left">
              <html:hidden property="insert_date"
                           style="width:125px"
                           maxlength="200" 
                           styleId="insert_date"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            </tr>
        
        <tr>
            <td align="right"> 
              Loại tài khoản
            </td>
            <td>
                <html:select styleClass="selectBox" property="loai_tk"
                                           styleId="loai_tk" style="width:125px;height:20px">
                        <html:option value="00">Chọn loại tài khoản</html:option>  
                        <html:option value="01">Thanh toán tổng hợp</html:option>
                        <html:option value="02">Thanh toán</html:option>
                        <html:option value="03">Chuyên thu</html:option>
                    </html:select><font color="Red">(*)</font>
            </td>
            
            <td align="right">
           
            </td>
            <td align="left" width="10%">
               
            </td>
            <td  align="left">
                <button type="button" onclick="check('save')" accesskey="t" id="bt">
                      <span class="sortKey">G</span>hi
                    </button>
                
                    <button type="button" onclick="check('close')" accesskey="t" id="bt">
                      <span class="sortKey">T</span>hoát
                </button> 
          </td>
        </tr>
        
        </table>
        </fieldset>
        </td>
      </tr>
 
    </table>
    
    
  </html:form>   
  
   <html:hidden property="pageNumber" value="1"/>
</div>
<div id="dialog-confirm"
     title='<fmt:message key="XuLyLenhQT.page.title.dialog_confirm"/>'>
  <p>
    <span class="
    icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
    
    <span id="message_confirm"></span>
  </p>
</div>

<%@ include file="/includes/ttsp_bottom.inc"%>

<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      if (type == 'close') {
          f.action = 'listSoDuAction.do';
      }
      if (type == 'save') {
          var ma_kb = jQuery('#ma_kb').val();
          var ma_nh = jQuery('#ma_nh').val();
          var ngay_gd = jQuery('#ngay_gd').val();
          var so_du = jQuery('#so_du').val();
          var loai_tk = jQuery('#loai_tk').val();
          
          if (ma_kb == null || ma_kb == '') {
              alert(GetUnicode('Hãy nhập mã kho bạc'));
              jQuery('#ma_kb').focus();
              return false;
          }
          else if (f.ma_kb.value.length != 8) {
              alert('Mã kho bạc phải là mã 8 kí tự');
              jQuery("ma_kb").focus();
              return;
          }
          else if (ma_nh == null || ma_nh == '') {
              alert(GetUnicode('Hãy nhập mã ngân hàng'));
              jQuery('#ma_nh').focus();
              return false;
          }
          else if (f.ma_nh.value.length != 8) {
              alert('Mã ngân hàng phải là mã 8 kí tự');
              jQuery("ma_nh").focus();
              return;
          }
          else if (f.ma_kb.value.substring(2, 5) != '701') {
              alert("Mã kho bạc không đúng định dạng");
              jQuery('#ma_kb').focus();
              return;
          }
          else if (f.ma_nh.value.substring(2, 5) == '701') {
              alert("Mã ngân hàng không đúng định dạng");
              jQuery('#ma_nh').focus();
              return;
          }
           else if (ngay_gd == null || ngay_gd == '') {
              alert('Phải nhập ngày giao dịch');
              jQuery('#ngay_gd').focus();
              return false;
          }
          else if (so_du == null || so_du ==''){
            alert('Phải nhập số dư');
            jQuery('#so_du').focus();
            return false;
          }else if(loai_tk === '00'){
              alert('Phải chọn loại tài khoản!');
              jQuery('#loai_tk').focus();
              return false;
          }

          document.getElementById("bt").disabled = true;
          f.action = 'addExcSoDuAction.do';
      }

      f.submit();
      
  }  
   function checkKey123(txt_id){
    var e= window.event;
    var keyCode = e.keyCode || e.charCode, arrow={
        backspace : 8, del : 46 ,left : 37, right : 39, up : 38, down : 40, enter : 13, esc : 27
    };
    //var matches = /^\$?(\d{1,3},?(\d{3},?)*\d{3}(\.\d{0,2})?|\d{1,3}(\.\d{0,2})?|\.\d{1,2}?)/; [^\-0-9]
    var matches = /^[-]?\d*\.?\d*$/; 
    var need_match = jQuery("#"+txt_id+"").val();
    if(!matches.test(need_match)){
      jQuery("#"+txt_id+"").val('');
    }
    var count_dot = jQuery("#"+txt_id+"").val().split(".")
    if(count_dot.length > 1){
        if(count_dot[1].length > 2){
            jQuery("#"+txt_id+"").val('');
        }
    }
  }

</script>