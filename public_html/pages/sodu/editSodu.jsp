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
<%
 

%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).ready(function () {
     jQuery('#so_du').focus();
     var loai_tk = <%=request.getParameter("loai_tk")%> + "";
     if(loai_tk !== ""){
        loai_tk++;
        jQuery("#loai_tk").find(":nth-child(" + loai_tk +")").attr("selected", true);
     }
  });
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
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="updateExcSoDuAction" method="post" styleId="TCuuDMuc">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width="13">
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
                 width="13" height="30"/>
          </td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title2">Sửa số dư </span>
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
    <logic:notEmpty name="SoDuForm" property="action_status">
      <font color="Red">
        <bean:write name="SoDuForm" property="action_status"/>
      </font>
    </logic:notEmpty>
    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0"
           class="tableBound" bordercolor="#999999" width="100%">
      <tr>
        <td align="left">
          <fieldset>
            <table class="data-grid" id="data-grid" border="0" cellspacing="1"
                   cellpadding="1" width="100%">
              <tr>
                <td width="15%" align="right">Mã kho bạc</td>
                <td align="left" width="20%">
                  <html:text property="ma_kb" styleId="ma_kb"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             onblur="this.style.backgroundColor='#ffffff'"
                             readonly="true"
                             styleClass="promptText"
                             size="35%"
                             value='<%=request.getParameter("ma_kb")%>'
                             style='width:200px'/>
                </td>
                
                <td width="15%" align="right">Ngày giao dịch</td>
                <td align="left" width="30%">
                  <html:text property="ngay_gd" styleId="ngay_gd" size="35%"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             onblur="this.style.backgroundColor='#ffffff'"                       
                             styleClass="promptText"
                             readonly="true"
                             value='<%=request.getParameter("ngay_gd")%>'
                             style='width:200px'/>
                </td>
              </tr>
               
              <tr>
                <td align="right">Mã ngân hàng</td>
                <td align="left" width="30%">
                  <html:text property="ma_nh" styleId="ma_nh" size="35%"
                             onkeypress="true"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             onblur="this.style.backgroundColor='#ffffff'"
                             readonly="true"
                             styleClass="promptText"   
                             value='<%=request.getParameter("ma_nh")%>'
                             style='width:200px'/>
                </td>
               <td width="15%" align="right">Số dư</td>
                <td align="left" width="30%">
                  <html:text property="so_du" styleId="so_du" size="35%"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             onblur="if (this.value !='') {changeValue('so_du');};textlostfocus(this); this.style.backgroundColor='#ffffff';" 
                              onkeypress="return numbersonly2(event,false) "
                             styleClass="promptText"
                             value='<%=request.getParameter("so_du")%>'
                             style="text-align:right;width:200px"/>
                </td>
              </tr>
              <tr>
              <td align="right">Loại tiền</td>
                <td align="left" width="30%">
                  <html:text property="loai_tien" styleId="loai_tien" size="35%"
                             onkeypress="true"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             onblur="this.style.backgroundColor='#ffffff'"
                             onchange="changeValue('so_du_cot');changeValue('so_du', true);"
                             readonly="true"
                             styleClass="promptText"   
                             value='<%=request.getParameter("loai_tien")%>'
                             style='width:200px'/>
                </td>
                <td width="15%" align="right">Số dư COT</td>
                <td align="left" width="30%">
                  <html:text property="so_du_cot" styleId="so_du_cot" size="35%"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             styleClass="promptText"
                             onblur="this.style.backgroundColor='#ffffff';textlostfocus(this);if (this.value !='') {changeValue('so_du_cot');}"
                            onkeypress="return numbersonly2(event,true) "
                             value='<%=request.getParameter("so_du_cot")%>'
                             style="text-align:right;width:200px"
                             />
                </td>             
              </tr>
              <tr>
               <td align="right"></td>
                <td align="left" width="30%">
                  <html:hidden property="id" styleId="id"
                  value='<%=request.getParameter("id")%>'/>   
               </td>
                <td width="15%" align="right"></td>
                <td align="left" width="30%">
                  <html:hidden property="insert_date" styleId="insert_date"
                  value='<%=request.getParameter("insert_date")%>'/>
                </td>              
              </tr>
              <tr>
            <td align="right"> 
              Loại tài khoản
            </td>
            <td>
                <html:select styleClass="selectBox" property="loai_tk"
                                           styleId="loai_tk" style="width:200px;height:20px">                  
                        <html:option value="00">Chọn loại tài khoản</html:option>  
                        <html:option value="01">Thanh toán tổng hợp</html:option>
                        <html:option value="02">Thanh toán</html:option>
                        <html:option value="03">Chuyên thu</html:option>
                    </html:select>
            
            <td align="right">
           
            </td>
            <td align="left" width="10%">
               
            </td>
            
        </tr>
              <tr>
                <td align="right"></td>
                <td align="left">
                  <button type="button" onclick="check('save')" accesskey="t"
                          id="bt">
                    <span class="sortKey">G</span>
                    hi
                  </button>
                   
                  <button type="button" onclick="check('close')" accesskey="t"
                          id="bt">
                    <span class="sortKey">T</span>
                    ho&aacute;t
                  </button>
                </td>
                <td align="right"></td>
                <td align="left" width="30%"></td>
                <td align="left"></td>
              </tr>
              
            </table>
          </fieldset>
        </td>
      </tr>
    </table>
  </html:form>

  <html:hidden property="pageNumber" value="1"/>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      if (type == 'close') {
          f.action = 'listSoDuAction.do';
      }
      if (type == 'save') {
          var so_du = jQuery('#so_du').val();
          var loai_tk = jQuery('#loai_tk').val();

         jQuery('#so_du_cot').val();

          if (so_du == null || so_du == '') {
              alert(GetUnicode('Hãy nhập số dư'));
              jQuery('#so_du').focus();
              return false;
          }else if(loai_tk === '00'){
              alert('Phải chọn loại tài khoản!');
              jQuery('#loai_tk').focus();
              return false;
          }
          document.getElementById("bt").disabled = true;
          f.action = 'updateExcSoDuAction.do';
      }
      
      

      f.submit();
      
  }
  function checkKey123(txt_id){
    var e= window.event;
    var keyCode = e.keyCode || e.charCode, arrow={
        backspace : 8, del : 46 ,left : 37, right : 39, up : 38, down : 40, enter : 13, esc : 27
    };
    //var matches = /^\$?(\d{1,3},?(\d{3},?)*\d{3}(\.\d{0,2})?|\d{1,3}(\.\d{0,2})?|\.\d{1,2}?)/; [^\-0-9]
  //??????????????????????????????? note need ok goodbye :D:DD:D:D:D:D:D: 
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