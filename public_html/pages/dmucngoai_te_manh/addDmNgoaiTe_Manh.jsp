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
      jQuery('#ma').focus();
  });
</script>
<div class="app_error">
  <html:errors/>  
</div>
<div class="box_common_conten">
  <html:form action="addExcDMNgoaiTe_ManhAction" method="post" styleId="TCuuDMuc" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Thiết lập mã ngoại tệ mạnh </span>
        </td>
        <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
        <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
      </tr>
    </tbody>
  </table>
  <logic:notEmpty name="DMNgoaiTe_ManhForm" property="action_status">
      <font color="Red"><bean:write name="DMNgoaiTe_ManhForm" property="action_status"/></font>
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
          <td align="right" width="15%">Mã ngoại tệ</td>
                <td align="left" width="25%">
                <html:select styleClass="selectBox" property="ma"
                                           styleId="ma" style="width:50%;height:20px">
                                <html:option value="VND">VND</html:option>
                                <html:optionsCollection value="ma" label="ma" name="lstLoaiTien"/>
                              </html:select>
                </td>
                <td align="right">Ngày hiệu lực</td>
                <td align="left" valign="middle">
                <html:text property="ngay_hieu_luc" styleId="ngay_hieu_luc"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onblur="CheckDate(this);CheckDateOnClient('ngay_hieu_luc');textlostfocus(this);"
                           onfocus="textfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH:40%"
                           />
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "ngay_hieu_luc", 
                      ifFormat : "%d/%m/%Y", 
                      button : "ngay"                     
                  });
                </script>
                (dd/mm/yyyy)<font color="Red">(*)</font>
              </td>
         
            </tr>
            <tr>
            <td>
            </td>
            <td align="left">
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

<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  function check(type) {
      var f = document.forms[0];
      if (type == 'close') {
          f.action = 'listDMNgoaiTe_ManhAction.do';
      }
      if (type == 'save') {
          var ma = jQuery('#ma').val();
          var ngay_hieu_luc = jQuery('#ngay_hieu_luc').val();
          
          if (ma == null || ma == '') {
              alert(GetUnicode('Hãy nhập mã ngoại tệ'));
              jQuery('#ma').focus();
              return false;
          }
         
          else if (ngay_hieu_luc == null || ngay_hieu_luc == '') {
              alert(GetUnicode('Hãy nhập ngày hiệu lực'));
              jQuery('#ngay_hieu_luc').focus();
              return false;
          }
          document.getElementById("bt").disabled = true;
          f.action = 'addExcDMNgoaiTe_ManhAction.do';
      }

      f.submit();
  }  
</script>