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
      jQuery('#ma_kb').focus();
  });
</script>
<div class="app_error">
  <html:errors/>  
</div>
<div class="box_common_conten">
  <html:form action="addExcNgayTrienKhaiAction" method="post" styleId="TCuuDMuc" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Thêm mới ngày triển khai </span>
        </td>
        <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
        <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
      </tr>
    </tbody>
  </table>
  <logic:notEmpty name="NgayTrienKhaiForm" property="action_status">
      <font color="Red"><bean:write name="NgayTrienKhaiForm" property="action_status"/></font>
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
           Mã kho bạc
          </td>
          <td align="left" width="20%">
               <html:text property="ma_kb" styleId="ma_kb" 
                             onblur="getTenNganHang('ma_kb', 'ten_nhkb_nhan', 'id_nhkb_nhan');textlostfocus(this); this.style.backgroundColor='#ffffff';"
                             maxlength="8"
                             onfocus="this.style.backgroundColor='#ffffb5'"
                                                     
                             styleClass="promptText"></html:text><font color="Red">(*)</font>
            </td>
            <td width="32%" align="left">
             <html:text property="ten_nhkb_nhan" readonly="true" styleId="ten_nhkb_nhan"
                           styleClass="fieldTextTrans" onmouseout="UnTip()"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>               
             <html:hidden property="id_nhkb_nhan" styleId="id_nhkb_nhan" value=""/>
            </td>
            
            <td width="10%" align="right">Ghi chú
            </td>
           <td align="left" width="28%">
                <html:textarea property="ghi_chu"
                           style="width:90%"
                           maxlength="200" 
                           styleId="ghi_chu"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
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
           <td width="10%" align="left">
             <html:text property="ten_nhkb_nhan1" readonly="true" styleId="ten_nhkb_nhan1"
                           styleClass="fieldTextTrans" onmouseout="UnTip()"
                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>               
             <html:hidden property="id_nhkb_nhan" styleId="id_nhkb_nhan" value=""/>
            </td>
             <td align="right">Ngày triển khai</td>
                <td align="left" valign="middle">
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
                (dd/mm/yyyy)<font color="Red">(*)</font>
              </td>
        </tr>
        <tr>
         <td style="text-align:right">                           
                              Mã ngoại tệ                       
                          </td>
                          <td>
                              <html:select styleClass="selectBox" property="ma_nt"
                                           styleId="ma_nt" style="width:50%;height:20px">
                                <html:option value="VND">VND</html:option>
                                <html:optionsCollection value="ma" label="ma" name="lstLoaiTien"/>
                              </html:select><font color="Red">(*)</font>
                          </td>
           <td align="right">
            </td>
           <td align="left">
                <html:hidden property="id"
                           style="width:90%"
                           maxlength="8" 
                           styleId="id"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
        </tr>
        
        <tr>
            <td> 
            </td>
            <td align="right">
                <button type="button" onclick="check('save')" accesskey="t" id="bt">
                  <span class="sortKey">G</span>hi
                </button></td>
                <td align="left">
            
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
          f.action = 'listNgayTrienKhaiAction.do';
      }
      if (type == 'save') {
          var ma_kb = jQuery('#ma_kb').val();
          var ma_nh = jQuery('#ma_nh').val();
          var ma_nt = jQuery('#ma_nt').val();
          var ngay_trien_khai = jQuery('#ngay_trien_khai').val();
          
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
          else if (ma_nt == null || ma_nt == '') {
              alert('Hãy nhập mã ngoại tệ');
              jQuery('#ma_nt').focus();
              return false;
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
           else if (ngay_trien_khai == null || ngay_trien_khai == '') {
              alert('Phải nhập ngày triển khai');
              jQuery('#ngay_trien_khai').focus();
              return false;
          }

          document.getElementById("bt").disabled = true;
          f.action = 'addExcNgayTrienKhaiAction.do';
      }

      f.submit();
  }  
</script>