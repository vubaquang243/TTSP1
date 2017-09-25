<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
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
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script language="JavaScript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dm.check.cheo.tk.js"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.dmKHCheoTKResource"/>
<title>
<fmt:message key="dmKHCheoTKResource.page.title"/>
</title>
<script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
//      resetFormDMCheoKH();
      jQuery("#dialog-message-check").dialog( {
        autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              Ok : function () {
              document.forms[0].target='';
              document.forms[0].action = 'DMKHCheoTK.do';
              document.forms[0].submit();
              jQuery(this).dialog("close");
                }
              }
      });
      var deleted = '<%=request.getAttribute("deleted")%>';
      if(deleted =="deleted"){
        jQuery("#dialog-message-check").html('<fmt:message key="dmKHCheoTKResource.page.xoa.thanhcong"/>');
        jQuery("#dialog-message-check").dialog("open");
      }
      var frmDMCheckCheo = jQuery("#frmDMCheoTK");
      jQuery("#btn_timKiem").click(function () {
        frmDMCheckCheo.target='';
        frmDMCheckCheo.submit();
      });
         //**************************BUTTON IN CLICK********************************************
      jQuery("#btn_in").click(function () {
        document.forms[0].action = 'printDMKHCheoTK.do';
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);         
        document.forms[0].target='_form';
        document.forms[0].submit();
       });
       //**************************BUTTON Thoat CLICK********************************************
      jQuery("#btn_Thoat").click(function () {
          if (confirm('Bạn có chắc chắn muốn thoát khỏi chức năng này?') == false)
              return false;
          else {
              document.forms[0].action = 'mainAction.do';
              document.forms[0].target='';
              document.forms[0].submit();
          }
       });
       jQuery("#btn_Them").click(function () {
//              addExcForm();
              document.forms[0].action = 'DMKHCheoTKAdd.do?action=ADD';
              document.forms[0].target='';
              document.forms[0].submit();
         });  
      });
      
      //**************************LINK TRANG CLICK********************************************
      function goPage(page) {
      jQuery("#frmDMCheoTK").attr( {
          action : 'DMKHCheoTK.do'
      });
      jQuery("#pageNumber").val(page);
      jQuery("#frmDMCheoTK").submit();
      
  }
</script>
<html:form action="/DMKHCheoTK.do" styleId="frmDMCheoTK">
  <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
  <input type="hidden" id="evenButtonBefore"/>
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
                  <fmt:message key="dmKHCheoTKResource.page.title"/>
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
            <td class="title" >
              <span>
                <fmt:message key="dmKHCheoTKResource.page.timkiem"/></span>
            </td>
          </tr>
        
          <tr>
            <td align="center"> 
              <table width="100%" style="margin-top:5px;"  cellspacing="0" cellpadding="0" align="center" class="bordertableChungTu">
                <tr align="center" >
                    <td width="10%">
                      <label for="tai_khoan">
                        <fmt:message key="dmKHCheoTKResource.page.timkiem.label.taikhoan"/>
                      </label>
                      <html:text styleId="tk_timkiem" property="tk"
                         onkeypress="return numberBlockKey(this,event,true)"
                         styleClass="fieldText" style="width:60%;"
                         onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                         onfocus="textfocus(this);"/>
                    </td>                    
                </tr>
                <tr>
                    <td width="100%">
                      <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%" >
                        <tr>
                          <td align="center">
                            <span>
                              <button id="btn_timKiem" accesskey="t" type="button"
                                class="ButtonCommon"  value="search">
                                <fmt:message key="dmKHCheoTKResource.page.button.timkiem"/>
                              </button> 
                            </span>
                            <span>
                              <button id="btn_in" accesskey="i" type="button"
                                class="ButtonCommon"  value="search">
                                <fmt:message key="dmKHCheoTKResource.page.button.in"/>
                              </button> 
                            </span>
                            <span>
                              <button id="btn_Them" accesskey="m" type="button"
                                class="ButtonCommon" value="search">
                                <fmt:message key="dmKHCheoTKResource.page.button.Them"/>
                              </button> 
                            </span>
                            <span>
                              <button id="btn_Thoat" accesskey="o" type="button"
                                class="ButtonCommon" value="search">
                                <fmt:message key="dmKHCheoTKResource.page.button.thoat"/>
                              </button> 
                            </span>
                          </td>
                        </tr>
                      </table>
                    </td>
                </tr>
              </table>
            </td>
          </tr>
      </tbody>
  </table>
  
</html:form>
<div style="padding:10px 0px 10px 0px;">
    <table border="2" align="center" width="100%" class="borderTableChungTu"
           >
      <thead class="TR_Selected">
        <tr>
          <td class="title" colspan="12">
            <span>
              <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua"/></span>
          </td>
        </tr>
        <tr>
          <th width="11%"  class="th">
            <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.tk"/>
          </th>
          <th width="6%" class="th">
            <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_cap"/>
          </th>          
          <th width="6%" class="th">
            <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_chuong"/>
          </th>
          <th width="6%" class="th">
            <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_nganh"/>
          </th>
          <th width="6%" class="th">
            <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_ndkt"/>
          </th>
          <th width="6%" class="th">
            <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_dvsdns"/>
          </th>
          <th width="6%" class="th">
            <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_diaban"/>
          </th>
          <th width="6%" class="th">
            <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_quy"/>
          </th>
         <th width="6%" class="th">
            <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_ctmt"/>
          </th>
          <th width="6%" class="th">
            <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_nguon"/>
          </th>
          <th width="8%" class="th">
            <fmt:message key="dmKHCheoTKResource.page.timkiem.ketqua.label.ma_dphong"/>
          </th>
          <th width="5%" class="th">
                               &nbsp;</th>
        </tr>
      </thead>
       
      <tbody class="navigateable focused" cellspacing="0" cellpadding="1"
             bordercolor="#e1e1e1">
       <logic:notEmpty name="danhsachDMKH">
          <%
                com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
                int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
          %>
          <logic:iterate id="items" name="danhsachDMKH" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' height="20">
              <td align="center">
                <bean:write name="items" property="tk"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ma_cap"/>
              </td>             
              <td align="center">
                <bean:write name="items" property="ma_chuong"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ma_nganh"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ma_ndkt"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ma_dvsdns"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ma_diaban"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ma_quy"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ma_ctmt"/>
              </td>
               <td align="center">
                <bean:write name="items" property="ma_nguon"/>
              </td>
               <td align="center">
                <bean:write name="items" property="ma_dphong"/>
              </td>
               <td align="left">
               <a href='<html:rewrite page="/DMKHCheoTKAdd.do"/>?idTK=<bean:write name="items" property="tk"/>&action=<%=AppConstants.ACTION_EDIT%>'>
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/ctu_00.gif"
                               style="border-style: none"/> 
              </a>
              <%--<html:link onclick="editForm();" styleId="edit" paramId="tk" paramName="items" paramProperty="tk">
                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/ctu_00.gif"
                               style="border-style: none"/> 
              </html:link>--%>
                               &nbsp;
                <a href='<html:rewrite page="/DMKHCheoTKExc.do"/>?idTK=<bean:write name="items" property="tk"/>&action=<%=AppConstants.ACTION_CANCEL%>'
                        onclick="return confirm('Bạn có muốn xóa tài khoản này ?')">
                        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif"
                        style="border-style: none" />
                </a>
                
              </td>
            </tr>
          </logic:iterate>
          <tr>
            <td colspan="12">
              <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
            </td>
          </tr>
        </logic:notEmpty>
        
      </tbody>
    </table>
  </div>
<div id="dialog-message-check"
     title='<fmt:message key="dmKHCheoTKResource.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>