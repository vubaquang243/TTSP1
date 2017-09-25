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
        type="text/javascript">
</script>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%
 

%>
<script type="text/javascript">
  jQuery.noConflict();
   //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {

  });
</script>
<div class="app_error">
  <html:errors/>
  
</div>
<div class="box_common_conten">
  <html:form action="loadTCuuDMuc.do" method="post" styleId="TCuuDMuc" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Thêm mới danh mục </span>
        </td>
        <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
        <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
      </tr>
    </tbody>
  </table>
  <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound"
         bordercolor="#999999" width="100%">
    <tr>
      <td align="left" >
      <fieldset>
        <legend></legend>
        
        <table  class="data-grid" id="data-grid" 
                                      border="0"
                                     cellspacing="1" cellpadding="1"                                  
                                     width="100%">
        <tr>
          <td width="15%" align="right">
          Lo&#7841;i danh m&#7909;c
          </td>
          <td  align="left" bordercolor="#e1e1e1" width="30%">          
            <html:select property="table_name" styleId="table_name" style="font-size:12px;width:90%" onchange="tbl_name()"
                                   onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                          <html:option value="">Ch&#7885;n danh m&#7909;c</html:option>
                          <html:option value="SP_DM_CAPNS">DM c&#7845;p ng&#226;n s&#225;ch</html:option>
                          <html:option value="SP_DM_CHUONG">DM ch&#432;&#417;ng</html:option>
                          <html:option value="SP_DM_HTKB">DM h&#7879; th&#7889;ng kho b&#7841;c</html:option>
                          <html:option value="SP_DM_MA_QUY">DM m&#227; qu&#7929;</html:option>
                          <html:option value="SP_DM_NGAN_HANG">DM ng&#226;n h&#224;ng</html:option>
                          <html:option value="SP_DM_NGANHKT">DM ng&#224;nh kinh t&#7871;</html:option>
                          <html:option value="SP_DM_NGUONCHI">DM ngu&#7891;n chi</html:option>
                          <html:option value="SP_DM_DVSDNS">DM &#273;&#417;n v&#7883; s&#7917; d&#7909;ng ng&#226;n s&#225;ch</html:option>
                          <html:option value="SP_DM_TIENTE">DM ti&#7873;n t&#7879;</html:option>
                          <html:option value="SP_DM_TKKT">DM t&#224;i kho&#7843;n k&#7871; to&#225;n</html:option>                      
              </html:select> <font color="Red">(*)</font>
            </td>
            <td width="15%" align="right">Mã
            </td>
            <td align="left" width="30%">
                <html:text property="ma" style="width:90%" maxlength="200" onkeypress="return numberBlockKey(event)"  styleId="ma"/><font color="Red">(*)</font>
            </td>
            </tr>
            <tr>
            <td align="right"> 
            </td>
            <td align="left">
                
            </td>
            <td align="right">
            T&#234;n 
            </td>
            <td align="left" width="30%">
                <html:text property="ten" style="width:90%" maxlength="200" styleId="ten"/><font color="Red">(*)</font>
            </td>
            <td  align="left">
          
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
    <logic:notEmpty name="TCuuDMucForm" property="action_status">
      <font color="Red"><bean:write name="TCuuDMucForm" property="action_status"/></font>
    </logic:notEmpty>
  </html:form>   
   <html:hidden property="pageNumber" value="1"/>
</div>

<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  function tbl_name() {
      var table_name;
      table_name=document.getElementById("table_name").value; 
      return table_name;
  }
  function goPage(page) {
    jQuery("#pageNumber").val(page);
      var f = document.forms[0];   
      f.action = 'loadTCuuDMuc.do?pageNumber='+page;
      jQuery("#TCuuDMuc").submit();
    }
function check(type) { 
 var f = document.forms[0];
     if (type == 'save') {
     var table_name = jQuery('#table_name').val();
     var ma = jQuery('#ma').val();
     var ten = jQuery('#ten').val();
      if(table_name==null||''==table_name){
        alert(GetUnicode('C&#7847;n ch&#7885;n danh m&#7909;c'));
        jQuery('#table_name').focus();
        return false;
      }else if(ma == null || ma == ''){
        alert(GetUnicode('Hay nhap ma DM'));
        jQuery('#ma').focus();
        return false;
      }else if(ten == null || ten == ''){
        alert(GetUnicode('Hay nhap ten DM'));
        jQuery('#ten').focus();
        return false;
      }else{
        document.getElementById("bt").disabled=true;
        f.action = 'addExcDMuc.do';
      }
      }
     if (type == 'close') {
        f.action = 'loadTCuuDMuc.do';          
      } 
         f.submit();
}
</script>