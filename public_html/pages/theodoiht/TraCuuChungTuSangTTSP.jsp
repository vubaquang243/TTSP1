<%@ page contentType="text/html; charset=UTF-8"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<%@ taglib uri="/WEb-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEb-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEb-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEb-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuChungTuSangTTSPResource"/>
<title>
  <fmt:message key="TraCuuChungTuSangTTSPResource.page.title"/>
</title>
<div class="app_error">
  <html:errors/>
</div>
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
  <tbody>
    <tr>
      <td width="13">
        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
             width="13" height="30"/>
      </td>
      <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T2.jpg"
          width="50%">
        <span class="title2">
          <fmt:message key="TraCuuChungTuSangTTSPResource.page.title"/></span>
      </td>
      <td width="62">
        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg"
             width="62" height="30"/>
      </td>
      <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg"
          width="45%">&nbsp;</td>
      <td width="4">
        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg"
             width="4" height="30"></img>
      </td>
    </tr>
  </tbody>
</table>
<input type="hidden" id="fieldNameForcus"/>
<html:form action="/TraCuuSangTTSPList.do" styleId="frmTraCuuSangTTSP">
  <fieldset>
    <legend>
      <fmt:message key="TraCuuChungTuSangTTSPResource.page.dieukientk"/>
    </legend>
    <table style="border-collapse:collapse;" class="bordertableChungTu"
           border="0" cellspacing="0" width="100%">
      <tbody>
        <tr>
          <td align="right" width="24%">
            <label for="makb_chuyen">
              <fmt:message key="TraCuuChungTuSangTTSPResource.lable.makb_chuyen"/>
            </label>
          </td>
          <td class="promptText" align="right" width="26%">
          <logic:present name="TTTT">
                            <logic:equal name="TTTT" value="true">
                              <html:text styleId="makb_chuyen" property="makb_chuyen"
                                           styleClass="fieldText" style="width:164;"
                                           onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
                            </logic:equal>
                            <logic:equal name="TTTT" value="false">
                                <html:text styleId="makb_chuyen" readonly="true" property="makb_chuyen"
                                           styleClass="fieldTextCode" style="width:164;"/>
                            </logic:equal>
          </logic:present>
          </td>
          <td width="24%" align="right">
            <label for="manh_chuyen">
              <fmt:message key="TraCuuChungTuSangTTSPResource.lable.manh_chuyen"/>
            </label>
          </td>
          <td class="promptText" align="right" width="26%">
            <html:text styleId="manh_chuyen" property="manh_chuyen"
                       styleClass="fieldText" style="width:164;"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
          </td>
        </tr>
        <tr>
          <td align="right" width="24%">
            <label for="sotien">
              <fmt:message key="TraCuuChungTuSangTTSPResource.lable.sotien"/>
            </label>
          </td>
          <td class="promptText" align="right" width="26%">
            <html:text styleId="sotien" property="sotien" styleClass="fieldText"
                       style="width:164;"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
          </td>
          <td align="right" width="24%">
            <label for="manh_nhan" style="padding-left:28px;">
              <fmt:message key="TraCuuChungTuSangTTSPResource.lable.manh_nhan"/>
            </label>
          </td>
          <td class="promptText" align="right" width="26%">
            <html:text styleId="manh_nhan" property="manh_nhan"
                       styleClass="fieldText" style="width:164;"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
          </td>
        </tr>
        <tr>
          <td align="right" width="24%">
            <label for="ngaythanhtoan">
              <fmt:message key="TraCuuChungTuSangTTSPResource.lable.ngaythanhtoan"/>
            </label>
          </td>
          <td class="promptText" align="right" width="26%">
            <html:text styleId="ngaythanhtoan" onblur="validFormatDate(this);"
                       styleClass="fieldText" property="ngaythanhtoan"
                       style="width:138;"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
             
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                 border="0" id="tu_ngay_lap_btn" width="20"
                 style="vertical-align:middle;"/>
            <script type="text/javascript">
              Calendar.setup( {
                  inputField : "ngaythanhtoan", // id of the input field
                  ifFormat : "%d/%m/%Y", // the date format
                  button : "tu_ngay_lap_btn"// id of the button
              });
            </script>
          </td>
          <td align="right" width="24%">
            <label for="taikhoanno">
              <fmt:message key="TraCuuChungTuSangTTSPResource.lable.taikhoanno"/>
            </label>
          </td>
          <td class="promptText" align="right" width="26%">
            <html:text styleId="taikhoanno" property="taikhoanno"
                       styleClass="fieldText" style="width:164;"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
          </td>
        </tr>
        <tr>
          <td  align="right" width="24%">
            <label for="so_yctt">
              <fmt:message key="TraCuuChungTuSangTTSPResource.lable.so_yctt"/>
            </label>
          </td>
          <td class="promptText" align="right" width="26%">
            <html:text styleId="so_yctt" property="so_yctt"
                       styleClass="fieldText" style="width:164;"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"/>
          </td>
        </tr>
        <tr>
          <td colspan="2" align="right">
            <div style="padding:10px 0px 10px 0px; ">
              <button id="btn_timKiem" accesskey="i" type="button"
                      class="ButtonCommon" value="search">
                <fmt:message key="TraCuuChungTuSangTTSPResource.page.button.search"/>
              </button>
            </div>
          </td>
          <td colspan="2" align="left">
            <div style="padding:10px 0px 10px 0px; ">
              <button id="btn_Exit" accesskey="e" type="button"
                      class="ButtonCommon" value="search">
                <fmt:message key="TraCuuChungTuSangTTSPResource.page.button.exit"/>
              </button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </fieldset>
</html:form>
<fieldset>
  <legend>
    <fmt:message key="TraCuuChungTuSangTTSPResource.page.ketqua"/>
  </legend>
  <div class="scroll_box">
    <table cellpadding="0" style="BORDER-COLLAPSE: collapse" align="center"
           cellspacing="0" border="1" width="200%">
      <thead class="TR_Selected">
        <tr>
          <th width="8%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.manh_kb"/>
          </th>
          <!--co the dai them
          -->
          <th width="9%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.tennh_kb"/>
          </th>
          <th width="6%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.makb"/>
          </th>
          <th width="6%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.tenkb"/>
          </th>
          <th width="9%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.manh_ncc"/>
          </th>
          <th width="6%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.ten_ncc"/>
          </th>
          <th width="10%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.tennh_ncc"/>
          </th>
          <th width="8%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.sotk_ncc"/>
          </th>
          <th width="8%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.so_tien"/>
          </th>
          <th width="6%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.thongtinkhac"/>
          </th>
          <th width="6%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.chungtu"/>
          </th>
          <th width="8%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.mota_trangthai"/>
          </th>
          <th width="10%">
            <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.motaloi"/>
          </th>
        </tr>
      </thead>
       
      <tbody>
      <logic:equal value="errorHT" name="errorHeader">
            <tr>
               <td colspan="11" align="l" bgcolor="#ffffcc">
                  <div style="font-family: Tahoma; color: red; font-size: 12pt;">
                    <fmt:message key="TraCuuChungTuSangTTSPResource.page.sodientrasoat.errorInfo"/> <br/>
                      <fmt:message key="TraCuuChungTuSangTTSPResource.page.sodientrasoat.errorInfoHeThong"/>
                    </div>
               </td>
            </tr>
            </logic:equal>
        <logic:present name="lstHeThong">
                       <%
                          if(request.getAttribute("errorHeader") != null && !"".equals(request.getAttribute("errorHeader"))){
                       %>
                        <tr>
                          <td colspan="11" align="l" bgcolor="#ffffcc">
                          <div style="font-family: Tahoma; color: red; font-size: 12pt;"><fmt:message key="TraCuuChungTuSangTTSPResource.page.sodientrasoat.errorInfo"/> <br/><bean:write name="errorHeader"/></div>
                          </td>
                        </tr>
                       <%
                       }else{
                       %>
                          <logic:empty name="lstHeThong">
                            <tr>
                              <td colspan="11" align="left" bgcolor="#ffffcc">
                                  <span style="font-family: Tahoma; color: red; font-size: 12pt;">
                                    <fmt:message key="TraCuuChungTuSangTTSPResource.page.sodientrasoat.error"/>
                                  </span>
                              </td>
                            </tr>
                          </logic:empty>
                     <%}%>
          <logic:notEmpty name="lstHeThong">
            <logic:iterate id="items" name="lstHeThong" indexId="index">
              <tr>
                <td align="center">
                  <bean:write name="items" property="treasury_bank_code"/>
                </td>
                <td align="left">
                  <bean:write name="items" property="treasury_bank_name"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="treasury_code"/>
                </td>
                <td align="left">
                  <bean:write name="items" property="treasury_name"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="supplier_bank_code"/>
                </td>
                <td align="left">
                  <bean:write name="items" property="supplier_name"/>
                </td>
                <td align="left">
                  <bean:write name="items" property="supplier_bank_name"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="supplier_bank_number"/>
                </td>
                <td align="right">
                  <bean:write name="items" property="sotien"/>
                </td>
                <td align="left">
                  <bean:write name="items" property="thongtinkhac"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="so_chungtu"/>
                </td>
                <td align="left">
                  <logic:equal name="items"
                               property="mota_trangthai"
                               value="00">
                                            <bean:write name="items" property="payment_err"/>  <bean:write name="items" property="payment_app"/>
                  </logic:equal>
                  <logic:equal name="items"
                               property="mota_trangthai"
                               value="01">
                                        <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.mota_trangthai_chuagui"/>
                  </logic:equal>
                  <logic:equal name="items"
                               property="mota_trangthai"
                               value="02">
                                         <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.mota_trangthai_thieu_COA"/>
                  </logic:equal>
                  <logic:equal name="items"
                               property="mota_trangthai"
                               value="03">
                                         <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.mota_trangthai_thua_COA"/>
                  </logic:equal>
                  <logic:equal name="items"
                               property="mota_trangthai"
                               value="04">
                                        <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.mota_trangthai_glDate"/>
                  </logic:equal>
                  <logic:equal name="items"
                               property="mota_trangthai"
                               value="05">
                                        <fmt:message key="TraCuuChungTuSangTTSPResource.page.lable.mota_trangthai_dagui"/>
                  </logic:equal>
                </td>
                <td align="left">
                  <bean:write name="items" property="error_des"/>
                </td>
              </tr>
            </logic:iterate>
          </logic:notEmpty>
        </logic:present>
      </tbody>
    </table>
  </div>
</fieldset>
<script type="text/javascript">
      jQuery.noConflict(); 	
      jQuery(document).ready(function () {
          jQuery("#btn_timKiem").click(function () {
              document.forms[0].action = "TraCuuSangTTSPList.do";
              document.forms[0].submit();
          });
      jQuery("#btn_Exit").click(function () {
          document.forms[0].action = "thoatView.do";
          document.forms[0].submit();
      });
});
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>