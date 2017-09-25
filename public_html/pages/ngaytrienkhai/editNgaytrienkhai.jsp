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
<script src="src">
"<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
  type = "text/javascript"
</script>
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%
 

%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).ready(function () {
     jQuery('#ghi_chu').focus();
  });

</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="updateExcNgayTrienKhaiAction" method="post" styleId="TCuuDMuc">
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
            <span class="title2">Sửa ngày triển khai </span>
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
    <logic:notEmpty name="NgayTrienKhaiForm" property="action_status">
      <font color="Red">
        <bean:write name="NgayTrienKhaiForm" property="action_status"/>
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
                             value='<%=request.getParameter("ma_kb")%>'/>
                </td>
                <td width="15%" align="right">Ghi chú</td>
                <td align="left" width="30%">
                  <html:text property="ghi_chu" styleId="ghi_chu" size="35%"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             onblur="this.style.backgroundColor='#ffffff'"                       
                             styleClass="promptText"                
                             value='<%=request.getParameter("ghi_chu")%>'/>
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
                             value='<%=request.getParameter("ma_nh")%>'/>
                </td>
                <td width="15%" align="right">Ngày triển khai</td>
                <td width="15%" align="left" valign="middle">
                <html:text property="ngay_trien_khai" styleId="ngay_trien_khai"
                           styleClass="promptText" onmouseout="UnTip()"
                           onkeypress="dateBlockKey(event)"
                           onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_trien_khai');textlostfocus(this);"
                           onfocus="textfocus(this);"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"
                           style="WIDTH:40%"
                           value='<%=request.getParameter("ngay_trien_khai")%>'/>
                 
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay" width="20"
                     style="vertical-align:middle;"/>
                <script type="text/javascript">
                  Calendar.setup( {
                      inputField : "ngay_trien_khai", 
                      ifFormat : "%d/%m/%Y", 
                      button : "ngay"
                  });
                </script>
                (dd/mm/yyyy)
              </td>
              </tr>
              <tr>
              <td align="right">Mã ngoại tệ</td>
                <td align="left" width="30%">
                  <html:text property="ma_nt" styleId="ma_nt" size="35%"
                             onkeypress="true"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             onblur="this.style.backgroundColor='#ffffff'"
                             readonly="true"
                             styleClass="promptText"   
                             value='<%=request.getParameter("ma_nt")%>'/>
                </td>
                <td width="15%" align="right"></td>
                <td align="left" width="30%">
                  <html:hidden property="id" styleId="id" size="35%"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                             onblur="this.style.backgroundColor='#ffffff'"                       
                             styleClass="promptText"                
                             value='<%=request.getParameter("id")%>'/>
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
          f.action = 'listNgayTrienKhaiAction.do';
      }
      if (type == 'save') {
          var ngay_trien_khai = jQuery('#ngay_trien_khai').val();

         jQuery('#ghi_chu').val();

          if (ngay_trien_khai == null || ngay_trien_khai == '') {
              alert(GetUnicode('Hãy nhập ngày triển khai'));
              jQuery('#ngay_trien_khai').focus();
              return false;
          }
          document.getElementById("bt").disabled = true;
          f.action = 'updateExcNgayTrienKhaiAction.do';
      }

      f.submit();
  }
</script>