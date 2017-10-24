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
  //*********************************** LOAD PAGE **********************************
  jQuery(document).ready(function () {
      jQuery('#shkb').focus();
  });
</script>
<div class="app_error">
  <html:errors/>  
</div>
<div class="box_common_conten">
  <html:form action="addExcDMMaNHangSHKBAction" method="post" styleId="TCuuDMuc" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Thêm mới danh mục số hiệu kho bạc </span>
        </td>
        <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
        <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
      </tr>
    </tbody>
  </table>
  <logic:notEmpty name="DMMaNHangSHKBForm" property="action_status">
      <font color="Red"><bean:write name="DMMaNHangSHKBForm" property="action_status"/></font>
  </logic:notEmpty>
  <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound"
         bordercolor="#999999" width="100%">
    <tr>
      <td align="left" >
      <fieldset>
        
        <table  class="data-grid" id="data-grid" 
                                      border="0"
                                     cellspacing="1" cellpadding="1"                                  
                                     width="100%">
        <tr>
          <td width="15%" align="right">
          Số hiệu kho bạc
          </td>
          <td align="left" width="30%">
                <html:text property="shkb" 
                           style="width:90%" 
                           maxlength="4"
                           styleId="shkb"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/><font color="Red">(*)</font>
            </td>
            <td width="15%" align="right">Mã ngân hàng
            </td>
           <td align="left" width="30%">
                <html:text property="ma_nh"
                           style="width:90%"
                           maxlength="8" 
                           styleId="ma_nh"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/><font color="Red">(*)</font>
            </td>
            </tr>
            <tr>
            
          
            <td align="right">
            Tên
            </td>
            <td align="left" width="30%">
                <html:text property="ten"
                           style="width:90%"
                           maxlength="200"
                           styleId="ten"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/><font color="Red">(*)</font>
            </td>
        </tr>
        
        <tr>
            <td align="right"> 
            </td>
            <td align="left">
                <button type="button" onclick="check('save')" accesskey="t" id="bt">
                  <span class="sortKey">G</span>hi
                </button>
            
                <button type="button" onclick="check('close')" accesskey="t" id="bt">
                  <span class="sortKey">T</span>hoát
                </button> 
            </td>
            <td align="right">
           
            </td>
            <td align="left" width="30%">
               
            </td>
            <td  align="left">
           
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

<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      if (type == 'close') {
          f.action = 'listDMMaNHangSHKBAction.do';
      }
      if (type == 'save') {
          var ma_nh = jQuery('#ma_nh').val();
          var shkb = jQuery('#shkb').val();
          var ten = jQuery('#ten').val();

          if (shkb == null || shkb == '') {
              alert(GetUnicode('Hãy nhập số hiệu kho bạc'));
              jQuery('#shkb').focus();
              return false;
          }
          else if (f.shkb.value.length != 4) {
              alert('Số hiệu kho bạc phải là mã 4 kí tự');
              jQuery("shkb").focus();
              return;
          }
          else if (ten == null || ten == '') {
              alert(GetUnicode('Hãy nhập tên'));
              jQuery('#ten').focus();
              return false;
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
          else if (f.ma_nh.value.substring(2, 5) != '701') {
              alert("Mã NHKB không đúng định dạng");
              jQuery('ma_nh').focus();
              return;
          }

          document.getElementById("bt").disabled = true;
          f.action = 'addExcDMMaNHangSHKBAction.do';
      }

      f.submit();
  }  
</script>