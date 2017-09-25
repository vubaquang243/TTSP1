<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
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
<script language="JavaScript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/main.js"></script>
<fmt:setBundle basename="com.seatech.ttsp.resource.MainResource"/>
<title>Thống kê tổng hợp</title>
<%
String strDate = request.getAttribute("strDate")==null?"":request.getAttribute("strDate").toString();
%>
 <script type="text/javascript">
  jQuery.noConflict();
  jQuery(document).ready(function () {
      //*************************************************KHAI BAO BIEN ***********************************
        jQuery.noConflict();

        
        var lttDi_field=jQuery("#lttDi"),
       lttDi_chottv_field=jQuery("#lttDi_chottv"),
       lttDi_choktt_field=jQuery("#lttDi_choktt"),
       lttDi_chogd_field=jQuery("#lttDi_chogd"),
       lttDi_daduyet_field=jQuery("#lttDi_daduyet"),
       lttDi_dinh_tc_field=jQuery("#lttDi_dinh_tc"),
       lttDi_dinh_tb_field=jQuery("#lttDi_dinh_tb"),
       lttDi_huy_field=jQuery("#lttDi_huy"),
       lttDen_field=jQuery("#lttDen"),
       lttDen_chottv_field=jQuery("#lttDen_chottv"),
       lttDen_choktt_field=jQuery("#lttDen_choktt"),
       lttDen_guitabmis_field=jQuery("#lttDen_guitabmis"),
       lttDen_tabmis_tc_field=jQuery("#lttDen_tabmis_tc"),
       lttDen_tabmis_tb_field=jQuery("#lttDen_tabmis_tb"),
      // DTS
       dtsDi_field=jQuery("#dtsDi"),
       dtsDi_chottv_field=jQuery("#dtsDi_chottv"),
       dtsDi_choktt_field=jQuery("#dtsDi_choktt"),
       dtsDi_daguinh_field=jQuery("#dtsDi_daguinh"),
       dtsDi_guinh_tc_field=jQuery("#dtsDi_guinh_tc"),
       dtsDi_guinh_tb_field=jQuery("#dtsDi_guinh_tb"),
       dtsDi_huy_field=jQuery("#dtsDi_guinh_tb"),
       dtsDen_field=jQuery("#dtsDen"),
       dtsDen_chottv_field=jQuery("#dtsDen_chottv"),
       dtsDen_choktt_field=jQuery("#dtsDen_choktt"),
       dtsDen_daxly_field=jQuery("#dtsDen_daxly"),
       mainForm = jQuery("#frmDTS"),
       allFields = jQuery([]).add(lttDi_field)
                  .add(lttDi_chottv_field)
                  .add(lttDi_choktt_field)
                  .add(lttDi_chogd_field)
                  .add(lttDi_daduyet_field)
                  .add(lttDi_dinh_tc_field)
                  .add(lttDi_dinh_tb_field)
                  .add(lttDi_huy_field)
                  .add(lttDen_field)
                  .add(lttDen_chottv_field)
                  .add(lttDen_choktt_field)
                  .add(lttDen_guitabmis_field)
                  .add(lttDen_tabmis_tc_field)
                  .add(lttDen_tabmis_tb_field)
                  .add(dtsDi_field)
                  .add(dtsDi_chottv_field)
                  .add(dtsDi_choktt_field)
                  .add(dtsDi_daguinh_field)
                  .add(dtsDi_guinh_tc_field)
                  .add(dtsDi_guinh_tb_field)
                  .add(dtsDi_huy_field)
                  .add(dtsDen_field)
                  .add(dtsDen_chottv_field)
                  .add(dtsDen_choktt_field)
                  .add(dtsDen_daxly_field);
      reserFormMain(allFields);
       jQuery("#btn_capnhat").click(function(){
            document.forms[0].submit();
          })
        });
 
</script>

<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
  <tbody>
    <tr>
      <td width="13">
        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
             width="13" height="30"/>
      </td>
      <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
          width="75%">
        <span class="title2">
          <fmt:message key="MainResource.page.label.title"/></span>
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

<table class="tableBound">
  <tr>
    <td>
      <table width="99%">
        <tbody>
        <tr>
          <td><font color="red">
              <html:errors/>
            </font> 
          </td></tr>
        </tbody>
      </table>
      
      <table cellspacing="0" cellpadding="0" width="100%">
        <tbody>
        <tr>
        <td valign="top">
          <table  cellspacing="1"  border="0" cellpadding="0" width="100%">
            <tbody>
              <html:form styleId="frmDTS" action="/mainAction.do">
              <tr>
              
                <td valign="top" >
                  <table bordercolor="#e1e1e1" cellspacing="0" cellpadding="0" 
                         width="100%">
                      <tbody>
                        <logic:present name="userType">
                              <tr >
                              <td width="50%" align="left" valign="top">
                                <div class="clearfix">                    
                                  <fieldset class="fieldsetCSS" style="width:450px; height:180px">
                                    <div  style="padding:10px 5px 10px 5px;width:100%">
                                      <table style="BORDER-COLLAPSE: collapse;" 
                                           cellspacing="2" border="0"
                                           cellpadding="2" width="99%" >
                                      <tbody>
                                        <tr >
                                          <td align="center" style="font-size:13px" colspan="2" >
                                            <label for="lttDi" >
                                              <b><fmt:message key="MainResource.page.label.ltt.lttDi"/></b>
                                            </label>
                                          </td>
                                          <td width="20%" align="right">
                                            <html:text  property="lttDi" style="text-align:right; font-weight:bold; font-size:13px"
                                                       styleId="lttDi" />
                                          </td>
                                         </tr>
                                         
                                         <tr >
                                          <td width="25%">&nbsp;</td>
                                          <td align="left" style="font-size:13px">
                                            <label for="lttnhtc">
                                              <fmt:message key="MainResource.page.trangthai.ltt.di.nh_tc"/>
                                            </label>
                                          </td>
                                          <td align="right" class="textLevel2">
                                            <html:text property="lttDi_dinh_tc" style="text-align:right;width:98%; font-size:13px"
                                                       styleId="lttDi_dinh_tc"
                                                       />
                                          </td>
                                         </tr>
                                         <tr  >
                                         <td></td>
                                          <td align="left" style="font-size:13px">
                                            <label for="lttnhtb">
                                              - &#272;i NH kh&#244;ng th&#224;nh c&#244;ng
                                            </label>
                                          </td>
                                          <td width="20%" align="right" class="textLevel2">
                                            <html:text property="lttDi_dinh_tb" style="text-align:right;width:98%; color:red;font-weight:bold;font-color:red; font-size:13px"
                                                       styleId="lttDi_dinh_tb"
                                                       />
                                          </td>
                                         </tr>
                                         <tr >
                                         <td></td>
                                          <td align="left" style="font-size:13px">
                                            <label for="lttDiHuy">
                                             - C&#225;c TT kh&#225;c
                                            </label>
                                          </td>
                                          <td width="20%" align="right" >
                                            <html:text property="lttDi_huy" style="text-align:right;width:98%; font-weight:bold; font-size:13px"
                                                       styleId="lttDi_huy"
                                                       />
                                          </td>
                                         </tr>
                                         <tr>
                                          <td align="center" style="font-size:13px; font-weight:bold" colspan="2" >
                                            <label for="lttDen">
                                              <fmt:message key="MainResource.page.label.ltt.lttDen"/>
                                            </label>
                                          </td>
                                          <td width="20%" align="right" class="textLevel1">
                                            <html:text property="lttDen" style="text-align:right; font-weight:bold; font-size:13px"
                                                       styleId="lttDen" />
                                          </td>
                                         </tr>
                                         
                                       </tbody>
                                     </table>
                                   </div>
                                  </fieldset>
                                </div>
                              </td>
                              <td width="50%" align="left" valign="top">
                                <div class="clearfix">                    
                                  <fieldset class="fieldsetCSS" style="width:450px; height:180px">
                                  <div  style="padding:10px 5px 10px 5px;width:100%">
                                      <table style="BORDER-COLLAPSE: collapse;" border="0"
                                           cellspacing="2"
                                           cellpadding="2" width="99%">
                                      <tbody>
                                        <tr >
                                          <td align="center" colspan="2" style="font-size:13px; font-weight:bold">
                                            <label for="dtsDi">
                                              <fmt:message key="MainResource.page.label.dts.dtsDi"/>
                                            </label>
                                          </td>
                                          <td width="20%" align="right" >
                                            <html:text property="dtsDi" style="text-align:right; font-weight:bold; font-size:13px"
                                                       styleId="dtsDi"/>
                                          </td>
                                         </tr>
                                         
                                         <tr >
                                         <td width="25%">&nbsp;</td>
                                          <td align="left" style="font-size:13px">              
                                            <label for="dtsDi_nhtc">
                                              <fmt:message key="MainResource.page.trangthai.ltt.di.nh_tc"/>
                                            </label>
                                          </td>
                                          <td align="right"  >
                                            <html:text property="dtsDi_guinh_tc"style="text-align:right;width:98%; font-size:13px"
                                                       styleId="dtsDi_guinh_tc"                                          
                                                       />
                                          </td>
                                         </tr>
                                         <tr >
                                         <td ></td>
                                          <td align="left"   style="font-size:13px">
                                            <label for="dtsDi_nhtb">
                                              - &#272;i NH kh&#244;ng th&#224;nh c&#244;ng
                                            </label>
                                          </td>
                                          <td width="20%" align="right" class="textLevel2">
                                            <html:text property="dtsDi_guinh_tb" style="text-align:right;width:98%; color:red;font-weight:bold; font-size:13px"
                                                       styleId="dtsDi_guinh_tb"
                                                       />
                                          </td>
                                         </tr>
                                         <tr >
                                         <td  ></td>
                                         <td align="left" style="font-size:13px">
                                            <label for="dtsDi_huy">
                                             - C&#225;c TT Kh&#225;c
                                            </label>
                                          </td>
                                          <td width="20%" align="right" class="textLevel2">
                                            <html:text property="dtsDi_huy" style="text-align:right;width:98%; font-weight:bold; font-size:13px"
                                                       styleId="dtsDi_huy"
                                                       />
                                          </td>
                                         </tr>
                                         <tr >
                                          <td align="center"  colspan="2" style="font-size:13px; font-weight:bold">
                                            <label for="dtsDen">
                                              <fmt:message key="MainResource.page.label.dts.NHTM"/>
                                            </label>
                                          </td>
                                          <td width="20%" align="right" >
                                            <html:text property="dtsDen" style="text-align:right; font-weight:bold; font-size:13px"
                                                       styleId="dtsDen" />
                                          </td>
                                         </tr>
                                         
                                       </tbody>
                                     </table>
                                  </div>
                                </fieldset>
                                </div>
                              </td>
                            </tr>
                        </logic:present>
                       </tbody> 
                  </table>
                </td>
              </tr>
              <tr valign="top" align="center">
                  <td width="90%" colspan="5"> 
                    <button id="btn_capnhat" accesskey="i" type="button"
                          class="ButtonCommon" tabindex="21" value="search">
                    <fmt:message key="MainResource.page.button.capnhat"/>
                  </button> 
                  &nbsp;&nbsp;&nbsp;&nbsp; Gi&#7901; c&#7853;p nh&#7853;t: <%=strDate%>
                  </td>
 
             </tr>
              </html:form>
            </tbody>
          </table>
        </td>
        </tr>
        </tbody>
      </table>
    </td>
  </tr>
</table>


<%@ include file="/includes/ttsp_bottom.inc"%>
