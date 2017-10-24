<%@ page contentType="text/html; charset=UTF-8"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<%@ taglib uri="/WEb-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEb-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEb-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEb-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuChungTuSangTabmisResource"/>
<title><fmt:message key="TraCuuChungTuSangTabmisResource.page.title"/></title>
<div class="app_error">
  <html:errors/>
</div>
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
  <tbody>
      <tr>
        <td width="13">
         <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width="13" height="30"/>
        </td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T2.jpg" width="50%">
          <span class="title2"><fmt:message key="TraCuuChungTuSangTabmisResource.page.title"/></span>
        </td>
        <td width="62">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg"  width="62" height="30"/>
        </td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="45%">&nbsp;</td>
        <td width="4">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width="4" height="30"></img>
        </td>
      </tr>
      </tbody>
</table>
            <input type="hidden" id="fieldNameForcus"/>
            <html:form action="/TraCuuSangTabmisList.do" styleId="frmTraCuuSangTTSP">
                  <fieldset>
                   <legend><fmt:message key="TraCuuChungTuSangTabmisResource.page.dieukientk"/></legend>
                  <table style="border-collapse:collapse;" class="bordertableChungTu" border="0" cellspacing="0" width="100%">
                     <tbody>
                      <tr>
                        <td align="right" width="24%">
                          <label for="makb_nhan"><fmt:message key="TraCuuChungTuSangTabmisResource.lable.makb_nhan"/></label>
                        </td>
                        <td class="promptText" align="right" width="26%" >
                          <logic:present name="TTTT">
                            <logic:equal name="TTTT" value="true">
                              <html:text styleId="ma_kb_nhan" property="ma_kb_nhan" styleClass="fieldText" style="width:164;" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                            </logic:equal>
                            <logic:equal name="TTTT" value="false">
                              <html:text styleId="ma_kb_nhan" readonly="true" property="ma_kb_nhan" 
                                         styleClass="fieldTextCode" style="width:164;"/>
                            </logic:equal>
                          </logic:present>
                        </td>
                        <td  width="24%"  align="right">
                          <label for="manh_chuyen"><fmt:message key="TraCuuChungTuSangTabmisResource.lable.manh_chuyen"/></label>
                        </td>
                         <td class="promptText" align="right" width="26%">
                          <html:text styleId="ma_nh_chuyen" property="ma_nh_chuyen" styleClass="fieldText" style="width:164;" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                        </td>
                      </tr>
                      <tr>
                      <td align="right" width="24%">
                          <label for="so_ltt" style="padding-left:28px;"><fmt:message key="TraCuuChungTuSangTabmisResource.lable.so_ltt"/></label>
                        </td>
                        <td class="promptText" align="right" width="26%">
                          <html:text styleId="so_ltt" property="so_ltt" styleClass="fieldText" style="width:164;" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                        </td>
                        <td align="right" width="24%">
                          <label for="sotien"><fmt:message key="TraCuuChungTuSangTabmisResource.lable.sotien"/></label>
                        </td>
                         <td class="promptText" align="right" width="26%">
                          <html:text styleId="so_tien" property="so_tien" styleClass="fieldText" style="width:164;" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                        </td>
                        
                      </tr>
                      <tr>
                          <td align="right" width="24%">
                            <label for="tungay"><fmt:message key="TraCuuChungTuSangTabmisResource.lable.tungay"/></label>
                          </td>
                           <td class="promptText" align="right" width="26%">
                            <html:text styleId="tu_ngay_tt" onblur="validFormatDate(this);" styleClass="fieldText" property="tu_ngay_tt" style="width:138;" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0" id="tu_ngay_tt_btn" width="20" style="vertical-align:middle;"/>
                            <script type="text/javascript">
                              Calendar.setup( {
                              inputField : "tu_ngay_tt", // id of the input field
                              ifFormat : "%d/%m/%Y", // the date format
                              button : "tu_ngay_tt_btn"// id of the button
                              });
                            </script>
                          </td>
                          <td align="right" width="24%">
                            <label for="denngay"><fmt:message key="TraCuuChungTuSangTabmisResource.lable.denngay"/></label>
                          </td>
                           <td class="promptText" align="right" width="26%">
                            <html:text styleId="den_ngay_tt" onblur="validFormatDate(this);" styleClass="fieldText" property="den_ngay_tt" style="width:138;" onkeydown="if(event.keyCode==13) event.keyCode=9;" />
                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0" id="den_ngay_tt_btn" width="20" style="vertical-align:middle;"/>
                            <script type="text/javascript">
                              Calendar.setup( {
                              inputField : "den_ngay_tt", // id of the input field
                              ifFormat : "%d/%m/%Y", // the date format
                              button : "den_ngay_tt_btn"// id of the button
                              });
                            </script>
                          </td>
                      </tr>
                      <tr>
                       <td colspan="2" align="right">
                          <div style="padding:10px 0px 10px 0px; ">
                            <button id="btn_timKiem" accesskey="i" type="button" class="ButtonCommon"   value="search">
                             <fmt:message key="TraCuuChungTuSangTabmisResource.page.button.search"/>
                            </button>
                          </div>
                        </td>
                        <td colspan="2" align="left">
                          <div style="padding:10px 0px 10px 0px; ">
                            <button id="btn_Exit" accesskey="e" type="button" class="ButtonCommon"  value="search">
                             <fmt:message key="TraCuuChungTuSangTabmisResource.page.button.exit"/>
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
         <fmt:message key="TraCuuChungTuSangTabmisResource.page.ketqua"/>
        </legend>
         <div class="scroll_box">
          <table cellpadding="0" style="BORDER-COLLAPSE: collapse" align="center"
           cellspacing="0" border="1" width="200%">
              <thead class="TR_Selected">
              <tr >
                <th width="12%">
                  <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.tenbuttoan"/>
                </th>
                <th width="6%">
                  <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.loaibuttoan"/>
                </th>
                <th width="6%">
                  <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.makb"/>
                </th>
                <th width="6%">
                  <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.diengiai"/>
                </th>
                <th width="10%">
                  <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.manh_ncc"/>
                </th>
                <th width="6%">
                  <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.ngayhieuluc"/>
                </th>
                <th width="8%">
                  <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.ngaygiaodich"/>
                </th>
                <th width="4%">
                 <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.loaitien"/>
                </th>
                <th width="4%">
                  <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.sottdong"/>
                </th>
                <th width="6%">
                  <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.maquy"/>
                </th>
                <th width="15%">
                  <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.mota_trangthai"/>
                </th>
                <th width="16%">
                  <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.motaloi"/>
                </th>
              </tr>
            </thead>
            <tbody>
              <logic:equal value="errorHT" name="errorHeader">
                  <tr>
                     <td colspan="11" align="l" bgcolor="#ffffcc">
                        <div style="font-family: Tahoma; color: red; font-size: 12pt;">
                          <fmt:message key="TraCuuChungTuSangTabmisResource.page.sodientrasoat.errorInfo"/> <br/>
                            <fmt:message key="TraCuuChungTuSangTabmisResource.page.sodientrasoat.errorInfoHeThong"/>
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
                          <div style="font-family: Tahoma; color: red; font-size: 12pt;"><fmt:message key="TraCuuChungTuSangTabmisResource.page.sodientrasoat.errorInfo"/> <br/><bean:write name="errorHeader"/></div>
                          </td>
                        </tr>
                       <%
                       }else{
                       %>
                          <logic:empty name="lstHeThong">
                            <tr>
                              <td colspan="11" align="left" bgcolor="#ffffcc">
                                  <span style="font-family: Tahoma; color: red; font-size: 12pt;">
                                    <fmt:message key="TraCuuChungTuSangTabmisResource.page.sodientrasoat.error"/>
                                  </span>
                              </td>
                            </tr>
                          </logic:empty>
                     <%}%>
                  <logic:notEmpty name="lstHeThong">
                    <logic:iterate id="items" name="lstHeThong"
                                                       indexId="index">
                              <tr>
                                <td align="left">
                                                      <bean:write name="items"
                                                                  property="ten_but_toan"/>
                                                      
                                </td>
                                <td align="left">
                                                        <bean:write name="items"
                                                                    property="loai_but_toan"/>
                                                        
                                </td>
                                <td  align="center">
                                                        <bean:write name="items"
                                                                    property="ma_kb_nhan"/>
                                                        
                                </td>
                                <td  align="left">
                                                        <bean:write name="items"
                                                                    property="dien_giai"/>
                                                        
                                </td>
                                <td align="center">
                                                        <bean:write name="items"
                                                                    property="ma_nh_chuyen"/>
                                                        
                                </td>
                                <td  align="center">
                                                        <bean:write name="items"
                                                                    property="ngay_hieu_luc"/>
                                                        
                                </td>
                                <td  align="center">
                                                        <bean:write name="items"
                                                                    property="ngay_gd"/>
                                                        
                                </td>
                                <td align="center">
                                                        <bean:write name="items"
                                                                    property="loai_tien"/>
                                                        
                                </td>
                                <td  align="center">
                                                        <bean:write name="items"
                                                                    property="sott_dong"/>
                                                        
                                </td>
                                <td  align="center">
                                                        <bean:write name="items"
                                                                    property="ma_quy"/>
                                                        
                                </td>
                                <td  align="left">
                                    <logic:equal name="items"
                                                 property="mota_trangthai"
                                                 value="01">
                                            <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.mota_trangthai_chogd"/>
                                    </logic:equal>
                                    <logic:equal name="items"
                                                 property="mota_trangthai"
                                                 value="02">
                                            <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.mota_trangthai_danggd"/>
                                    </logic:equal>
                                    <logic:equal name="items"
                                                 property="mota_trangthai"
                                                 value="03">
                                            <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.mota_trangthai_gdthanhcong"/>
                                    </logic:equal>
                                    <logic:equal name="items"
                                                 property="mota_trangthai"
                                                 value="04">
                                            <fmt:message key="TraCuuChungTuSangTabmisResource.page.lable.mota_trangthai_gdthatbai"/>
                                    </logic:equal>
                                    <logic:equal name="items"
                                                 property="mota_trangthai"
                                                 value="00">
                                            <bean:write name="items" property="ma_xuly"/> - <bean:write name="items" property="trang_thai"/>
                                    </logic:equal>
                                
                                </td>
                                <td  align="left">
                                                        <bean:write name="items"
                                                                    property="error_des"/>
                                                        
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
          document.forms[0].action = "TraCuuSangTabmisList.do";
          document.forms[0].submit();
      });
      jQuery("#btn_Exit").click(function () {
          document.forms[0].action = "thoatView.do";
          document.forms[0].submit();
      });
  });
  
  
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>
