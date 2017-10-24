<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
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
<fmt:setBundle basename="com.seatech.ttsp.resource.ReportsResource"/>
<title>Bảng tổng hợp lệnh thanh toán </title>
  <script type="text/javascript">
     jQuery.noConflict();

  //************************************ LOAD PAGE **********************************
  function formAction(){      
      if(jQuery("#tu_ngay").val()==''){
        alert('Trường từ ngày không được bỏ trống');
        jQuery("#tu_ngay").focus();
        return;
      }
      if(jQuery("#kb_id").val()==''){
        alert('phải chọn ngân hàng kho bạc');
        jQuery("#kb_id").focus();
        return;
      }
        var f = document.forms[0];
        f.target='';
      f.action = "bkeLTTDenRptAction.do?action=LTT.DEN";
      var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
      newDialog = window.open('', '_form', params);          
      f.target='_form';
      f.submit();
  }
</script>
<div class="app_error"><html:errors/></div>
  <html:form styleId="frmBCBKLTT" action="/bkeLTTDenAction.do">
     <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2"><fmt:message key="reports.bkltt.den.title"/></span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
        </tbody>
      </table>  
  <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          <fmt:message key="reports.page.dieukien"/>
        </td>
      </tr>
      <tr>
        <td width="30%%"></td>
        <td align="right" width="15%">
          <label for="tu_ngay">
            <fmt:message key="reports.page.lable.tu_ngay"/>
          </label>
        </td>
       <td align="left" valign="middle" >
          <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="107"
                     style="WIDTH: 70px;vertical-align:middle;"/>
           
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="tu_ngay_btn" width="20"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "tu_ngay", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "tu_ngay_btn"// id of the button
            });
          </script>
        </td>
        <td></td>
       <tr>
         <td></td>
        <td align="right">
          <label for="den_ngay">
            <fmt:message key="reports.page.lable.den_ngay"/>
          </label>
        </td>
       <td class="promptText" align="left" valign="middle">
          <html:text property="den_ngay" styleId="den_ngay"
                     styleClass="fieldText" 
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="108"
                     style="WIDTH: 70px;vertical-align:middle;"/>           
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="den_ngay_btn" width="20"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
                Calendar.setup( {
                    inputField : "den_ngay", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "den_ngay_btn"// id of the button
                });
          </script>
        </td>
        <td>         
        </td>
       </tr>
       <tr>
        <td colspan="2" align="right">Ngân hàng kho bạc :</td>
        <td colspan="2">
          <logic:present name="lstNHHO">
            <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                          tabindex="108"
                           styleClass="fieldText" style="width:145px;height:20px;"
                           property="kb_id" styleId="kb_id" >
                <html:option value="">
                  <fmt:message key="reports.page.selectbox.chon_kbHO"/>
                </html:option>
                <html:optionsCollection name="lstNHHO" value="ma_nh" label="ten_nh"/>
              </html:select>
          </logic:present>
          <logic:notPresent name="lstNHHO">
            <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                          tabindex="108"
                           styleClass="fieldText" style="width:145px;height:20px;"
                           property="kb_id" styleId="kb_id" >
                <html:option value="">
                  <fmt:message key="reports.page.selectbox.chon_kbHO"/>
                </html:option>
              </html:select>
          </logic:notPresent>          
        </td>
        
       </tr>
       <tr>
         
        <td colspan="4" align="center" valign="top" >
            <button  id="btn_timKiem" 
                      tabindex="111" onclick="formAction();"
                      accesskey="i" 
                    class="ButtonCommon" >
              <fmt:message key="reports.page.button.in"/>
            </button>
        </td>        
        
       </tr>
      </tr>
    </tbody>
  </table>  
  </html:form>
<%@ include file="/includes/ttsp_bottom.inc"%>