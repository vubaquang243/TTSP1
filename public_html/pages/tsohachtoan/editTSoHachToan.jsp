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
      jQuery('#tktn').focus();
      jQuery('#ma_nh').val('<%=request.getParameter("ma_nh")%>');
  });
</script>
<div class="app_error">
  <html:errors/>  
</div>
<div class="box_common_conten">
  <html:form action="updateExcTsoHachToanAction" method="post" styleId="TCuuDMuc" >
  <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Sửa tham số hạch toán </span>
        </td>
        <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
        <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
      </tr>
    </tbody>
  </table>
  <logic:notEmpty name="TsoHachToanForm" property="action_status">
      <font color="Red"><bean:write name="TsoHachToanForm" property="action_status"/></font>
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
          <td align="right">
         Mã ngân hàng
          </td>
          <td colspan="2">
             <html:select disabled="true" property="ma_nh" styleId="ma_nh" style="width:85%">
                            <html:option value="">--Chọn ngân hàng--</html:option>
                            <html:option value="201">Ngân hàng Công Thương</html:option>
                            <html:option value="202">Ngân hàng Đầu Tư</html:option>
                            <html:option value="203">Ngân hàng Ngoại Thương</html:option>
                            <html:option value="204">Ngân hàng Nông Nghiệp</html:option>
            </html:select>
          </td>
          <td>
            </td>
            <td align="right">Loại hạch toán
            </td>
           <td colspan="3" align="left" >
              <html:text   property="loai_htoan"
                           style="width:93%"  
                           maxlength="200"
                           readonly="true"
                           styleId="loai_htoan"
                           value='<%=request.getParameter("loai_htoan")%>'
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
        </tr>     
        <tr>
        <td  width="12.5%" align="right">
         Tài khoản tự nhiên
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="tktn" 
                           style="width:80%" 
                           maxlength="4"
                           styleId="tktn"
                           value='<%=request.getParameter("tktn")%>'
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            <td  width="12.5%" align="right">
         Mã quỹ
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="quy" 
                           style="width:80%" 
                           maxlength="2"
                           styleId="quy"
                           value='<%=request.getParameter("quy")%>'
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            <td  width="12.5%" align="right">
         ĐVSDNS
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="dvsdns" 
                           style="width:80%" 
                           maxlength="7"
                           value='<%=request.getParameter("dvsdns")%>'
                           styleId="dvsdns"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            <td  width="12.5%" align="right">
         Mã cấp ngân sách
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="cap_ns" 
                           style="width:80%" 
                           maxlength="1"
                           value='<%=request.getParameter("cap_ns")%>'
                           styleId="cap_ns"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>  
        </tr>
        <tr>
         <td  width="12.5%" align="right">
         Mã chương
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="chuong" 
                           style="width:80%" 
                           maxlength="3"
                           styleId="chuong"
                           value='<%=request.getParameter("chuong")%>'
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            <td  width="12.5%" align="right">
         Mã ngành kinh tế
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="nganh_kt" 
                           style="width:80%" 
                           maxlength="3"
                           value='<%=request.getParameter("nganh_kt")%>'
                           styleId="nganh_kt"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            <td  width="12.5%" align="right">
         Mã nội dung kinh tế
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="ndkt" 
                           style="width:80%" 
                           maxlength="4"
                           onkeypress="return numberBlockKey(event)"
                           value='<%=request.getParameter("ndkt")%>'
                           styleId="ndkt"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            <td  width="12.5%" align="right">
         Mã hành địa bàn 
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="dbhc" 
                           style="width:80%" 
                           maxlength="5"
                           styleId="dbhc"
                           onkeypress="return numberBlockKey(event)"
                           value='<%=request.getParameter("dbhc")%>'
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
        </tr>
        <tr>
        <td  width="12.5%" align="right">
         Mã CTMT
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="ctmt" 
                           style="width:80%" 
                           maxlength="5"
                           value='<%=request.getParameter("ctmt")%>'
                           styleId="ctmt"
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            <td  width="12.5%" align="right">
         Mã nguồn
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="nguon" 
                           style="width:80%" 
                           maxlength="2"
                           value='<%=request.getParameter("nguon")%>'
                           styleId="nguon"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            <td  width="12.5%" align="right">
         Mã kho bạc
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="ma_kb" 
                           style="width:80%" 
                           maxlength="4"
                           styleId="ma_kb"
                           value='<%=request.getParameter("ma_kb")%>'
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            <td  width="12.5%" align="right">
         Mã dự phòng
          </td>
          <td  align="left" width = "12.5%">
                <html:text property="du_phong" 
                           style="width:80%" 
                           maxlength="3"
                           styleId="du_phong"
                           value='<%=request.getParameter("du_phong")%>'
                           onkeypress="return numberBlockKey(event)"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
        </tr>
          <tr>
          <td align="right">
         Mô tả
          </td>
          <td colspan="3" align="left">
            <html:textarea property="mo_ta" 
                           style="width:93%" 
                           value='<%=request.getParameter("mo_ta")%>'
                           styleId="mo_ta"
                           onfocus="this.style.backgroundColor='#ffffb5'"
                           onblur="this.style.backgroundColor='#ffffff'"                           
                           styleClass="promptText"/>
            </td>
            <td align="right">Ghi chú
            </td>
           <td colspan="3" align="left">
                <html:textarea property="ghi_chu"
                               style="width:93%"
                               value='<%=request.getParameter("ghi_chu")%>'
                               styleId="ghi_chu"
                               onfocus="this.style.backgroundColor='#ffffb5'"
                               onblur="this.style.backgroundColor='#ffffff'"                           
                               styleClass="promptText"/>
            </td>
        </tr>
        <tr>
        <td>
        <html:hidden property="ma_nh" styleId="ma_nh" value='<%=request.getParameter("ma_nh")%>'></html:hidden>
        </td>
        <td>
        <html:hidden property="id" styleId="id" value='<%=request.getParameter("id")%>'></html:hidden>
        </td>
        </tr>
        
        <tr>
            <td align="right"> 
            </td>
            <td align="right">
                <button type="button" onclick="check('save')" accesskey="t" id="bt">
                  <span class="sortKey">G</span>hi
                </button>
            </td>
            <td align="left">
                <button type="button" onclick="check('close')" accesskey="t" id="bt">
                  <span class="sortKey">T</span>hoát
                </button> 
            </td>
            <td align="right">
           
            </td>
            <td align="left">
               
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
          f.action = 'listTsoHachToanAction.do';
      }
      if (type == 'save') {
          var ma_nh = jQuery('#ma_nh').val();
          var tktn = jQuery('#tktn').val();
          var quy = jQuery('#quy').val();
          var dvsdns =jQuery('#dvsdns').val();
          var cap_ns =jQuery('#cap_ns').val();
          var chuong = jQuery('#chuong').val();
          var nganh_kt = jQuery('#nganh_kt').val();
          var ndkt = jQuery('#ndkt').val();
          var dbhc = jQuery('#dbhc').val();
          var ctmt = jQuery('#ctmt').val();
          var nguon = jQuery('#nguon').val();
          var ma_kb = jQuery('#ma_kb').val();
          var du_phong = jQuery('#du_phong').val();
          var loai_htoan = jQuery('#loai_htoan').val();
          if (ma_nh == null || ma_nh == '') {
              alert('Hãy chọn ngân hàng');
              jQuery('#ma_nh').focus();
              return false;
          }
          else if (tktn == null || tktn == '') {
              alert('Hãy nhập tài khoản tự nhiên');
              jQuery('#tktn').focus();
              return false;
          }
          else if (f.tktn.value.length != 4) {
              alert('Tài khoản tự nhiên phải có 4 ký tự');
              jQuery("ma_kb").focus();
              return;
          }
          else if (f.quy.value.length != 2) {
              alert('Mã quỹ phải có 2 ký tự');
              jQuery("#quy").focus();
              return;
          }
          else if (quy == null || quy == ''){
              alert('Hãy nhập mã quỹ)');
              jQuery('#quy').focus();
          }
          else if (dvsdns == null || dvsdns == '') {
              alert('Hãy nhập mã đơn vị sử dụng ngân sách');
              jQuery('#dvsdns').focus();
              return false;
          }
          else if (f.dvsdns.value.length != 7){
              alert('Mã đơn vị sử dụng ngân sách phải có 7 ký tự');
              jQuery('#dvsdns').focus();
              return false;
          }
          else if (cap_ns == null || cap_ns == '') {
              alert('Hãy nhập mã cấp ngân sách');
              jQuery('#cap_ns').focus();
              return false;
          }
         else if (chuong == null || chuong == '') {
              alert('Hãy nhập mã chương');
              jQuery('#chuong').focus();
              return false;
          }
          else if (f.chuong.value.length != 3){
              alert('Mã chương phải có 3 ký tự');
              jQuery('#chuong').focus();
              return false;
          }
        else if (nganh_kt == null || nganh_kt == '') {
              alert('Hãy nhập mã ngành kinh tế');
              jQuery('#nganh_kt').focus();
              return false;
          }
          else if (f.nganh_kt.value.length != 3){
              alert('Mã ngành kinh tế phải có 3 ký tự');
              jQuery('#nganh_kt').focus();
              return false;
          }
          else if (ndkt == null || ndkt == '') {
              alert('Hãy nhập mã nội dung kinh tế');
              jQuery('#ndkt').focus();
              return false;
          }
          else if (f.ndkt.value.length != 4){
              alert('Mã nội dung kinh tế phải có 4 ký tự');
              jQuery('#ndkt').focus();
              return false;
          }
          else if (dbhc == null || dbhc == '') {
              alert('Hãy nhập mã địa bàn hành chính');
              jQuery('#dbhc').focus();
              return false;
          }
          else if (f.dbhc.value.length != 5){
              alert('Mã địa bàn hành chính phải có 5 ký tự');
              jQuery('#dbhc').focus();
              return false;
          }
          else if (ctmt == null || ctmt == '') {
              alert('Hãy nhập mã chương trình mục tiêu');
              jQuery('#ctmt').focus();
              return false;
          }
          else if (f.ctmt.value.length != 5){
              alert('Mã chương trình mục tiêu phải có 5 ký tự');
              jQuery('#ctmt').focus();
              return false;
          }
          else if (nguon == null || nguon == '') {
              alert('Hãy nhập mã nguồn');
              jQuery('#nguon').focus();
              return false;
          }
          else if (f.nguon.value.length != 2){
              alert('Mã nguồn phải có 2 ký tự');
              jQuery('#nguon').focus();
              return false;
          }
          else if (f.ma_kb.value.length != 4 && ma_kb !=='N/A'){
              alert('Mã kho bạc phải có 4 ký tự');
              jQuery('#ma_kb').focus();
              return false;
          }
          else if (du_phong == null || du_phong == '') {
              alert('Hãy nhập mã dự phòng');
              jQuery('#du_phong').focus();
              return false;
          }
          else if (f.du_phong.value.length != 3){
              alert('Mã dự phòng phải có 3 ký tự');
              jQuery('#du_phong').focus();
              return false;
          }
         else if (loai_htoan == null || loai_htoan == '') {
              alert('Hãy nhập loại hạch toán');
              jQuery('#loai_htoan').focus();
              return false;
         }

          document.getElementById("bt").disabled = true;
          f.action = 'updateExcTsoHachToanAction.do';
      }

      f.submit();
  } 

</script>