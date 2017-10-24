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
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/tabber.css"/>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabber.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.js"></script>
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
  String strRowSelected = request.getAttribute("rowSelected")==null?"":request.getAttribute("rowSelected").toString();
  String viewAcc = request.getAttribute("view")==null?"":request.getAttribute("view").toString();
  String strDuyet = request.getAttribute("duyet")==null?"":request.getAttribute("duyet").toString();
  String notPHT = request.getAttribute("notPHT")==null?"":request.getAttribute("notPHT").toString();
   String notTTSP = request.getAttribute("notTTSP")==null?"":request.getAttribute("notTTSP").toString();
  String huy = request.getAttribute("huy")==null?"":request.getAttribute("huy").toString();
  String chkdate = request.getAttribute("chkdate")==null?"":request.getAttribute("chkdate").toString();
  String bthuy = request.getAttribute("bthuy")==null?"":request.getAttribute("bthuy").toString();
  String size = request.getAttribute("size")==null?"":request.getAttribute("size").toString();
//  String strVSign = session.getAttribute(AppConstants.SETUP_VERIFY).toString();
//thuongdt-20170411 them moi bat loi duyet
String duyetloi = request.getAttribute("duyetloi")==null?"":request.getAttribute("duyetloi").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    //defaultStateFormBK();
    var strRowSelected="<%=strRowSelected%>";
    var viewAcc="<%=viewAcc%>";
    var strDuyet="<%=strDuyet%>";
    //thuongdt-20170411 them moi bat loi duyet
    var duyetloi="<%=duyetloi%>";
    if(strRowSelected!=null && '' != strRowSelected){
    rowSelectedFocusXNDC(strRowSelected);
    
    }
    if(viewAcc!=null && '' != viewAcc){
       jQuery("#row_qt_0").attr("disabled", true);
    }
     if(strDuyet!=null && '' != strDuyet){
        alert(GetUnicode('Duyệt điện ĐNQT thành công.'));
    }
    //thuongdt-20170411 them moi bat loi duyet
    if(duyetloi != null && ""!=duyetloi){
    alert(GetUnicode('Có lỗi trong quá trình ký duyêt: '+duyetloi));
    }
  });
</script>
<div class="app_error">
 <!--manhnv-24/06/2013-->
  <object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
  <!--manhnv-24/06/2013-->
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="DuyetXNDCTHop1Action.do" method="post" >
  <!--manhnv-24/06/2013-->
  <html:hidden property="chuky_ktt" styleId="chuky_ktt" />  
  <html:hidden property="certserial" styleId="certserial" />  
  <html:hidden property="noi_dung_ky" styleId="noi_dung_ky" />
  <html:hidden property="ndung_ky_066" styleId="ndung_ky_066" />
  
  <!--manhnv-24/06/2013-->
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> Duyệt đối chiếu tổng hợp - Lập đề nghị quyết toán</span>
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
       <td width="50%">
        <fieldset>
        <legend><font color="Blue">Danh s&#225;ch &#273;i&#7879;n x&#225;c nh&#7853;n</font></legend>
        <div  style="height:390px;overflow-y: scroll;">
          <table  class="data-grid" id="data-grid" 
                                              border="1"
                                             cellspacing="0" cellpadding="0"                                  
                                             width="100%">
                 <tr>
                 <td align="center" width="12%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.ngaydc"/></td>
                 <td align="center" width="13%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.duyet.manh"/></td>
                 <td align="center" width="20%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.sdxn"/></td>
                 <td align="center" width="10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.lan"/></td>
                 <td align="center" width="20%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai"/></td>
                 </tr>
                 <logic:empty name="colDChieu">
                  <tr>
                    <td colspan="6">
                      <font color="Red">Kh&#244;ng c&#243; b&#7843;ng k&#234; &#273;&#7889;i chi&#7871;u</font>
                      <!--<input type="hidden" id="tthai_0" value="02" />-->
                    </td>
                  </tr>
                </logic:empty>
               <logic:notEmpty name="colDChieu">
                  <logic:iterate id="UDlist" name="colDChieu" indexId="index">
                 <tr class="ui-widget-content jqgrow ui-row-ltr" 
                       id="row_qt_<bean:write name="index"/>"
                      onclick="rowSelectedFocusXNDC('row_qt_<bean:write name="index"/>');
                               fillDataDuyetXNTH('DuyetXNDCTHop1Action.do','<bean:write name="UDlist" property="id"/>','<bean:write name="UDlist" property="ngay_dc"/>','row_qt_<bean:write name="index"/>','<bean:write name="UDlist" property="receive_bank"/>','<bean:write name="UDlist" property="tthai_dxn_thop"/>');">
                    <td align="center">
                    <bean:write name="UDlist" property="ngay_dc"/>                    
                   </td>
                    <td align="center">
                    <input type="text" name="receive_bank" style="border:0;font-size:10px;text-align:center; width:98%" align="center" value="<bean:write name="UDlist" property="receive_bank"/>"/>                   
                    
                   </td>
                   <td align="center" id="td_qt_<bean:write name="index"/>">
                   <input name="row_item"  id="col_qt_<bean:write name="index"/>"
                      type="text" style="border:0px;font-size:10px;text-align:center; width:98%" onkeydown="arrowUpDownXNDC(event)"
                      value="<bean:write name="UDlist" property="id"/>" 
                      readonly="true"/>                     
                   </td>
                   <td align="center">
                    <bean:write name="UDlist" property="lan_dc"/>                    
                   </td>
                   <td align="center">
                        <logic:equal name="UDlist" property="tthai_dxn_thop" value="">
                            Chưa tạo điện
                         </logic:equal>
                         <logic:equal name="UDlist" property="tthai_dxn_thop" value="00">
                            Chưa tạo điện
                         </logic:equal>
                    <logic:equal name="UDlist" property="ket_qua_dxn_thop" value="0">                                                    
                         <logic:equal name="UDlist" property="tthai_dxn_thop" value="01">
                            Đã tạo điện
                         </logic:equal>
                         <logic:equal name="UDlist" property="tthai_dxn_thop" value="02">                            
                              Đã tạo điện
                            </logic:equal>
                            <logic:equal name="UDlist" property="tthai_dxn_thop" value="03">                            
                              Đã xác nhận
                            </logic:equal>
                      </logic:equal>
                      <logic:equal name="UDlist" property="ket_qua_dxn_thop" value="1"> 
                           <logic:equal name="UDlist" property="tthai_dxn_thop" value="01">                            
                              Xác nhận - chờ duyệt
                           </logic:equal>
                            <logic:equal name="UDlist" property="tthai_dxn_thop" value="03">                            
                              Đã xác nhận
                            </logic:equal>
                      </logic:equal>
                   <input type="hidden" name="tthai_hidden" id="tthai_<bean:write name="index"/>" value="<bean:write name="UDlist" property="tthai_dxn_thop"/>" />
                   <input type="hidden" name="ngaydc_hidden" id="ngaydc_<bean:write name="index"/>" value="<bean:write name="UDlist" property="ngay_dc"/>" />
                   <input type="hidden" name="tthaikq_hidden" id="tthaikq_<bean:write name="index"/>" value="<bean:write name="UDlist" property="trang_thai"/>" />
                   </td>
                 </tr>
                  </logic:iterate>
                </logic:notEmpty>             
             </table>
           </div>
        </fieldset>
       </td>
       <html:hidden property="id" styleId="kq_id"/> 
       <td width="50%">
        <fieldset>
            <legend><font color="Blue">T&#7893;ng h&#7907;p k&#7871;t qu&#7843; &#273;&#7889;i chi&#7871;u</font></legend>
            <div style="height:390px;">
              <table width="100%" cellspacing="0" cellpadding="2"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
                 <tr>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:25%" rowspan="2">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.thdlieu"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:25%" colspan="2">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.slieunh"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:25%" colspan="2">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.slieukb"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:25%" colspan="2">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.slieuchenh"/>
                        </div>
                   </th>
                  </tr>
                  <tr>
                    <th class="promptText" bgcolor="#f0f0f0" style="width:7%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.smon"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:20%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.stien"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:6%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.smon"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:20%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.stien"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:6%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.smon"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:20%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.stien"/>
                        </div>
                   </th>
                  </tr>
                 <logic:empty name="colTTSP">
                  <% if(notTTSP == null || ""==notTTSP){
                    %>
                  <tr>
                    <td colspan="7">
                      <b>TTSP</b>: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                      <font style="FONT-WEIGHT: bold; color:red">Ch&#432;a nh&#7853;n dc k&#7871;t qu&#7843; &#273;&#7889;i chi&#7871;u t&#7915; TTSP </font>
                    </td>                   
                  </tr>
                  <tr>
                    <td colspan="7">
                     <b>-<fmt:message key="doi_chieu.page.label.dc1.dlthu"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td>
                      <input type="text" style="border:0" name="so_mon_thu_dtu" class="fieldTextRight" value="" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="so_tien_thu_dtu" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="7">
                      <b>-<fmt:message key="doi_chieu.page.label.dc1.dlchi"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td>
                      <input type="text" style="border:0" name="somon_dtu" class="fieldTextRight" value="" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="sotien_dtu" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_chi_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_chi_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_thu_tcong_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_thu_tcong_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                  </tr>
                  <input type="hidden" name="ket_qua" value="" id="a"/>
                  <%}%>
                  </logic:empty>
                  
                  <logic:notEmpty name="colTTSP">
                   <% if(notTTSP == null || ""==notTTSP){
                    %>
                  <logic:iterate id="items" name="colTTSP">
                  <tr>
                    <td colspan="7">
                      <b>TTSP</b>: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                      
                      <logic:equal name="items" property="ket_qua" value="0">
                        <font style="FONT-WEIGHT: bold; color:blue"><fmt:message key="doi_chieu.page.lable.pht.0"/></font>
                      </logic:equal>
                    <font style="FONT-WEIGHT: bold; color:red">
                      <logic:equal name="items" property="ket_qua" value="1">
                        <fmt:message key="doi_chieu.page.lable.pht.1"/>
                      </logic:equal>
                      <logic:equal name="items" property="ket_qua" value="2">
                        <fmt:message key="doi_chieu.page.lable.pht.2"/>
                      </logic:equal>
                      </font>
                      <html:hidden property="ket_qua" name="items" styleId="a"/>
                      <html:hidden property="id" name="items" styleId="ttsp_id" />
                      <!--<input type="hidden" name="ttsp_id" value="<bean:write property="id"  name="items"/>"/>-->
                      <%--<html:hidden property="ngay_dc" name="items"/>--%>
                      
                    </td>                   
                  </tr>
                  <tr>
                    <td colspan="7">
                     <b>-<fmt:message key="doi_chieu.page.label.dc1.dlthu"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="so_mon_thu_dtu"/></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="so_tien_thu_dtu" /></fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="mon_thu_dtu_kbnn"/></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="tien_thu_dtu_kbnn"/></fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="chenh_mthu_dtu"/></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="chenh_tthu_dtu"/></fmt:formatNumber>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="7">
                      <b>-<fmt:message key="doi_chieu.page.label.dc1.dlchi"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="somon_dtu"/></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="sotien_dtu" /></fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="mon_chi_dtu_kbnn" /></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="tien_chi_dtu_kbnn" /></fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="chenh_mchi_dtu" /></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="chenh_tchi_dtu" /></fmt:formatNumber>
                    </td>
                  </tr>
                 <% if(notPHT != null && ""!=notPHT){
                    %>
                    <input type="hidden" name="ket_qua" value="0" id="b"/>            
                    <%}%>
                  </logic:iterate>
                  <%}%>
                  </logic:notEmpty>
                  
                    <% if(notPHT == null || ""==notPHT){
                    %>                   
                  <logic:empty name="colPHT">
                  <tr>
                    <td colspan="7"> <b>PHT:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                      <font style="FONT-WEIGHT: bold; color:red">
                          Ch&#432;a nh&#7853;n &#273;&#432;&#7907;c k&#7871;t qu&#7843; t&#7915; PHT
                      </font>
                      <input type="hidden" name="ket_qua" value="" id="b"/>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="7">
                     <b>-<fmt:message key="doi_chieu.page.label.dc1.dlthu"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td>
                      <input type="text" style="border:0" name="so_mon_thu_dtu" class="fieldTextRight" value="" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="so_tien_thu_dtu" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_thu_tcong_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_thu_tcong_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                  </tr>
                  </logic:empty>                  
                  <%}%>
                  
                  <logic:notEmpty name="colPHT">
                  <logic:iterate id="items" name="colPHT">
                  <tr>
                    <td colspan="7"> <b>PHT:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                      
                        <logic:equal name="items" property="ket_qua" value="0">
                          <font style="FONT-WEIGHT: bold; color:blue"><fmt:message key="doi_chieu.page.lable.pht.0"/></font>
                        </logic:equal>
                      <font style="FONT-WEIGHT: bold; color:red">
                        <logic:equal name="items" property="ket_qua" value="1">
                          <fmt:message key="doi_chieu.page.lable.pht.1"/>
                        </logic:equal>
                        <logic:equal name="items" property="ket_qua" value="2">
                          <fmt:message key="doi_chieu.page.lable.pht.2"/>
                        </logic:equal>
                        <html:hidden property="ket_qua" name="items" styleId="b"/>
                        <html:hidden property="id" name="items"  styleId="pht_id"/>
                        <!--<input type="hidden" name="pht_id" value="<bean:write property="id"  name="items"/>"/>-->
                      </font>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="">
                     <b>-<fmt:message key="doi_chieu.page.label.dc1.dlthu"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="tong_mon_pht"/></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="tong_ps_pht" /></fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="mon_thu_dtu_kbnn" /></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="tien_thu_dtu_kbnn"/></fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="chenh_mthu_dtu_pht" /></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="chenh_tthu_dtu_pht" /></fmt:formatNumber>
                    </td>
                  </tr>
                  <html:hidden property="ket_qua" name="items"/>
                        <html:hidden property="trang_thai_bkdc" name="items"/>
                        <html:hidden property="ket_qua_pht" name="items"/>
                        <html:hidden property="receive_bank" name="items"/>
                        <%--<html:hidden property="ngay_dc" name="items"/>--%>
                  </logic:iterate>
                  </logic:notEmpty>                
              </table>
              <br/>
              <fieldset>
               <div>
              <table width="100%" cellspacing="0" cellpadding="2"
                           bordercolor="#e1e1e1" border="0" align="center"
                           style="BORDER-COLLAPSE: collapse">
                    <logic:notEmpty name="colTHDC">
                        <logic:iterate id="items" name="colTHDC">
                        <tr>
                          <td colspan="4"> <b>Giao dịch thủ công </b> </td>
                        </tr>
                      <tr> 
                        <td width="25%" align="left">
                             Số thu thủ công
                        </td>
                         <td width="25%" align="right">                                       
                          <input type="text" style="width:99%"  name="so_thu" disabled="disabled"  id="so_thu"  value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="tien_thu_tcong_kbnn"/></fmt:formatNumber>" class="fieldTextRight" />
                         
                         </td>
                      </tr>
                      <tr>
                        <td  align="left">
                            Số chi thủ công
                          </td>
                          <td  align="right"> 
                          <input type="text" style="width:99%"  name="so_chi" disabled="disabled"  id="so_chi"   value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="tien_chi_tcong_kbnn"/></fmt:formatNumber>" class="fieldTextRight" />                                   
                         </td>            
                      </tr>
                      <tr>
                          <td colspan="4">
                          <b>Số dư tại thời điểm Cut-off-time: 
                            <fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="sodu_kbnn"/></fmt:formatNumber></b> 
                          </td>
                        </tr>
                      <tr> 
                        <td width="25%" align="left">
                             Số đề nghị QT thu
                             <%if(size!="0" && !"0".equals(size)&&!"".equals(size)&&size!=null){%>
                               còn lại
                             <%}%>
                        </td>
                         <td width="25%" align="right">                                       
                          <input type="text"  name="txt_thu_tcong" style="width:99%" disabled="disabled"  id="txt_thu_tcong" onkeyup="checkKey('txt_thu_tcong')"  value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="ket_chuyen_thu"/></fmt:formatNumber>" class="fieldTextRight" />
                          <input type="hidden"  name="qtoan_thu" id="qtoan_thu"  value="<bean:write name="items" property="ket_chuyen_thu"/>"/>  
                         </td>
                         <!--<td  align="center" rowspan="2">
                           <button type="button"  accesskey="t" id="bt_update" onclick="update_TCong()">
                              <span class="sortKey">S</span>ửa
                            </button>
                            <html:hidden property="cho_phep_sua" styleId="cho_phep_sua" />
                         </td>-->
                        <td  align="left" width="25%">
                            Số đề nghị QT chi
                            <%if(size!="0" && !"0".equals(size)&&!"".equals(size)&&size!=null){%>
                               còn lại
                             <%}%>
                          </td>
                          <td  align="right" width="25%"> 
                          <input type="text"  name="txt_chi_tcong" style="width:99%" disabled="disabled"  id="txt_chi_tcong" onkeyup="checkKey('txt_chi_tcong')"   value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="ket_chuyen_chi"/></fmt:formatNumber>" class="fieldTextRight" />                                   
                          <input type="hidden"  name="qtoan_chi" id="qtoan_chi"  value="<bean:write name="items" property="ket_chuyen_chi"/>"/>                                   
                         </td>            
                      </tr>
                      <tr>
                        <td  align="left">
                           Ghi chú
                          </td>
                          <td  align="left" colspan="4"> 
                          <input type="text"  name="txt_noi_dung" style="width:99%" disabled="disabled"  id="txt_noi_dung"  value="" class="fieldTextRight" />                                   
                         </td>            
                      </tr>  
                      </logic:iterate>
                    </logic:notEmpty>
                    <logic:empty name="colTHDC">                    
                      <logic:iterate id="items" name="colGDTCong">
                      <tr>
                          <td colspan="4"> <b>Giao dịch thủ công </b> </td>
                        </tr>
                      <tr> 
                        <td width="25%" align="left">
                             Số thu thủ công
                        </td>
                         <td width="25%" align="right">                                       
                          <input type="text"   style="width:99%" name="so_thu" disabled="disabled"  id="so_thu" value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="so_thu"/></fmt:formatNumber>" class="fieldTextRight"/>                    
                          </td>
                          <td  align="center" rowspan="2" colspan="2">
                           &nbsp;
                         </td>
                        </tr>
                      <tr> 
                        <td  align="left">
                            Số chi thủ công
                          </td>
                          <td  align="right"> 
                          <input type="text"  style="width:99%" name="so_chi" disabled="disabled"  id="so_chi" value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="so_chi"/></fmt:formatNumber>" class="fieldTextRight"/>                                   
                         </td>            
                      </tr>
                      <tr>
                          <td colspan="4">
                          <b>Số dư tại thời điểm Cut-off-time: 
                            <fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""></fmt:formatNumber></b> 
                          </td>
                        </tr>
                      <tr> 
                        <td width="25%" align="left">
                             Số đề nghị QT thu
                             <%if(size!="0" && !"0".equals(size)&&!"".equals(size)&&size!=null){%>
                               còn lại
                             <%}%>
                        </td>
                         <td align="right"   width="25%">                                       
                          <input type="text"  name="txt_thu_tcong" style="width:99%" disabled="disabled"  id="txt_thu_tcong" onkeyup="checkKey('txt_thu_tcong')" />
                          <input type="hidden"  name="qtoan_thu" id="qtoan_thu"/>  
                         </td>
                        <td  align="left" width="25%">
                            Số đề nghị QT chi
                            <%if(size!="0" && !"0".equals(size)&&!"".equals(size)&&size!=null){%>
                               còn lại
                             <%}%>
                          </td>
                          <td  align="right"  width="25%"> 
                          <input type="text"  name="txt_chi_tcong" style="width:99%" disabled="disabled"  id="txt_chi_tcong" onkeyup="checkKey('txt_chi_tcong')" />                                   
                          <input type="hidden"  name="qtoan_chi" id="qtoan_chi" />                                   
                         </td>            
                      </tr>
                      <tr>
                        <td  align="left">
                           Ghi chú
                          </td>
                          <td  align="left" colspan="3" width="70%"> 
                          <input type="text"  name="txt_noi_dung" style="width:99%" disabled="disabled"  id="txt_noi_dung"  value="" />                                   
                         </td>            
                      </tr> 
                      </logic:iterate>
                    </logic:empty>
                </table>
            </div>
            </fieldset>
            </div>
            </fieldset>
       </td>
      </tr>   
      
      <tr>
      <td align="center" colspan="6">
            <%if (chkdate!=null && !"".equals(chkdate)){%>
              <%if (bthuy!=null && !"".equals(bthuy)){
              %>
               <button type="button" onclick="check('huy','','')" accesskey="H" style="width:60px" name="btton" id="huy_bt">
                <span class="sortKey">H</span>&#7911;y
              </button> 
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
              
              <button type="button" onclick="check('duyet','','')" accesskey="D" style="width:60px" name="btton" id="bt">
                <span class="sortKey">D</span>uy&#7879;t
              </button>   
              <%}%>
              <%}%>
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
               <button type="button" onclick="check('print','','')" accesskey="D" style="width:100px" name="btton" id="inbt">
                <span class="sortKey">I</span>n k&#7871;t qu&#7843;
              </button>           
               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
              <button  type="button" onclick="check('close','','')" accesskey="o" style="width:60px">
                Th<span class="sortKey">o</span>&#225;t
              </button>
        
          </td>
      </tr>
      
      <tr>
        <td colspan="5" >
          <fieldset>
            <legend><font color="Blue">Danh sách đề nghị quyết toán</font></legend>
              <div width="50%" align="center">              
                <table cellspacing="0" width="100%" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">       
                    <thead >
                        <th bgcolor="#f0f0f0" align="center" width="3%">
                            STT
                         </th>
                         <th bgcolor="#f0f0f0" align="center" width="7%">
                            Ngày QT
                         </th>
                          <th  bgcolor="#f0f0f0" align="center"  width="11%"> 
                          Số điện
                         </th>
                         <th  bgcolor="#f0f0f0" align="center"  width="12%"> 
                          QT thu
                         </th>                                 
                        <th  bgcolor="#f0f0f0"  align="center" width="12%">
                            QT chi
                          </th>
                          <th  bgcolor="#f0f0f0" align="center" width="6%">                                   
                            Loại QT
                         </th>
                         <th  bgcolor="#f0f0f0" align="center" width="17%">                                   
                            Ghi chú
                         </th>
                         <th  bgcolor="#f0f0f0" align="center" width="6%">                                   
                           Trạng thái
                         </th>
                         <th  bgcolor="#f0f0f0" align="center" width="17%">                                   
                           Mô tả
                         </th>
                         <th  bgcolor="#f0f0f0" align="center"  width="9%">
                          
                         </th>
                      </thead>
                      <tbody class="navigateable focused"  cellspacing="0"   cellpadding="1" bordercolor="#e1e1e1" id="tbodyTTinTToan">    
                      <logic:notEmpty name="col066">
                        <logic:iterate id="items" name="col066" indexId="stt">
                        <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' id="066_row_qt_<bean:write name="stt"/>">
                          <td align="center">
                           <%=stt+1%>
                           
                          </td>
                          <td  align="center">
                            <bean:write name="items" property="ngay_qtoan"/>
                          </td>
                          <td align="center">
                            <bean:write name="items" property="id"/>
                            <input type="hidden" name="ndung_ky_066" id="ndung_ky_066_<bean:write name="items" property="id"/>" value="<bean:write name="items" property="ndung_ky_066"/>"/>
                          </td>
                          <td align="right">
                            <b><fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                            <bean:write name="items" property="qtoan_thu"/>
                             </fmt:formatNumber></b>
                            <input type="hidden" name="qtoan_thu" id="qtoan_thu_<bean:write name="stt"/>" value="<bean:write name="items" property="qtoan_thu"/>"/>
                          </td>
                          <td align="right">
                          <b><fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                            <bean:write name="items" property="qtoan_chi"/>
                            </fmt:formatNumber></b>
                            <input type="hidden" name="qtoan_chi" id="qtoan_chi_<bean:write name="stt"/>" value="<bean:write name="items" property="qtoan_chi"/>"/>
                          </td>
                          <td align="center">
                            <logic:equal value="01" property="loai_qtoan" name="items">
                               Tự động
                            </logic:equal>
                            <logic:equal value="02" property="loai_qtoan" name="items">
                               Lập mới
                            </logic:equal>
                          </td>
                          <td align="left" title="<bean:write name="items" property="ndung_qtoan"/>">
                              <div style="text-overflow:ellipsis;width:150px;white-space:nowrap;  overflow:hidden; font-size:12px">
                                 <bean:write name="items" property="ndung_qtoan"/>
                              </div>
                            </td>
                          <td align="center">
                            <logic:equal value="01" property="trang_thai" name="items">
                              Chờ duyệt
                            </logic:equal>
                            <logic:equal value="02" property="trang_thai" name="items">
                               Đã duyệt
                            </logic:equal>
                            <logic:equal value="03" property="trang_thai" name="items">
                               Hủy
                            </logic:equal>
                            <logic:equal value="04" property="trang_thai" name="items">
                               Gửi NH thành công
                            </logic:equal>
                            <logic:equal value="05" property="trang_thai" name="items">
                               Gửi NH không thành công
                            </logic:equal>
                            <logic:equal value="06" property="trang_thai" name="items">
                               Hết hiệu lực
                            </logic:equal>
                            <input type="hidden" name="trang_thai" id="trang_thai_<bean:write name="stt"/>" value="<bean:write name="items" property="trang_thai"/>"/>
                          </td>
                          <td align="left" title="<bean:write name="items" property="error_desc"/>">
                            <div style="text-overflow:ellipsis;width:150px;white-space:nowrap;  overflow:hidden; font-size:12px">
                              <bean:write name="items" property="error_code"/>-<bean:write name="items" property="error_desc"/>
                            </div>
                          </td>
                          <td align="center" >
                          <logic:equal value="01" property="trang_thai" name="items">
                              <!--<button type="button" onclick="check('huy','<bean:write name="items" property="id"/>','<bean:write name="items" property="loai_qtoan"/>')" accesskey="D" style="width:50px" name="btton">
                                <span class="sortKey">H</span>&#7911;y
                              </button>-->                   
                              <%if (bthuy==null||"".equals(bthuy)){%>
                                <%if(chkdate==null||"".equals(chkdate)){%>
                              <!--<span id="duyet" onclick="check('duyet','<bean:write name="items" property="id"/>','')"  title="Duyet" style="cursor:pointer;"><img src="<%=request.getContextPath()%>/styles/images/ok-icon.png" /></span>-->
                              <br/>
                              <button type="button" onclick="check('duyet','<bean:write name="items" property="id"/>','<bean:write name="items" property="loai_qtoan"/>')" accesskey="D" style="width:50px" id="bt" name="btton">
                                  <span class="sortKey">D</span>uy&#7879;t
                                </button>
                                 <p/>
                                 
                                 <button type="button" onclick="check('huy','<bean:write name="items" property="id"/>','<bean:write name="items" property="loai_qtoan"/>')"  id="huy_bt" accesskey="H" style="width:50px" name="btton">
                                    <span class="sortKey">H</span>&#7911;y
                                  </button>
                                  <%}%>
                                <!--<button type="button" onclick="check('duyet','<bean:write name="items" property="id"/>','')" accesskey="D" style="width:50px" name="btton">
                                  <span class="sortKey">D</span>uy&#7879;t
                                </button>-->
                                <!--<span id="huy" onclick="check('huy','<bean:write name="items" property="id"/>','<bean:write name="items" property="loai_qtoan"/>')"  title="Huy" style="cursor:pointer;"><img src="<%=request.getContextPath()%>/styles/images/delete-icon.png" /></span>-->
                              <%}%>
                          </logic:equal>
                          </td>
                          
                        </tr>
                        <input type="hidden" name="idx" id="total_row" value="<bean:write name="stt"/>"/>
                        </logic:iterate>
                      </logic:notEmpty>
                      
                      
                </table>
              </div>
          </fieldset>
        </td>
    </tr>
    
    </table>
    
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  var f = document.forms[0];
    disBt()
 function disBt(){
    var strRowSelected="<%=strRowSelected%>";
    var chkdate = "<%=chkdate%>";
    if(strRowSelected!=null && '' != strRowSelected){
      stt= strRowSelected.substr(7,5);
      sttNext=parseInt(stt);
      tthai_dxn=document.getElementById("tthai_"+sttNext).value;
         if(tthai_dxn=='02'||tthai_dxn=='03'){
              if(document.getElementById("bt") != null){
                  document.getElementById("bt").disabled = true;
              }
              if(document.getElementById("huy_bt") != null){
                  document.getElementById("huy_bt").disabled=true;
              }
         }else if (tthai_dxn=='01'||tthai_dxn=='00'){
            if(document.getElementById("bt") != null){
                document.getElementById("bt").disabled=false;
            }
        }
    }else{
      document.getElementById("bt").disabled=true;
      document.getElementById("inbt").disabled=true;
      document.getElementById("huy_bt").disabled=true;
    }
  }
  function check(type,id_066,loai_qtoan) {  
      var id=document.getElementById("kq_id").value;
      var strRowSelected="<%=strRowSelected%>";
//      var ndung_ky_066 = jQuery('#ndung_ky_066_'+id_066).val();
     if (type == 'duyet') {
        if (""==id|| id==null ){
          if(id_066!=null && ''!=id_066){
           stt= parseInt(strRowSelected.substr(7,5));
            ngay_dc =document.getElementById("ngaydc_"+stt).value;
            tthai_dxn=document.getElementById("tthai_"+sttNext).value;
          //manhnv-24/06/2013
              if("Y"==strChkKy){
                if(id_066!=null && ''!=id_066){
                  if(!ky(id_066)){
                    alert("Ký không thành công");
                    return false;
                  }
                }
              }
          f.action = 'exDuyetXNDCTHop1Action.do?rowSelected='+strRowSelected+'&ngay_dc='+ngay_dc+'&id_066='+id_066+"&tthai_dxn_thop="+tthai_dxn+"&loai_qtoan"+loai_qtoan; 
          }else{
          alert(GetUnicode('Không có thông tin bảng kê'));
          return false;
          }
      }else  if (""!=id && id!=null ){
      //manhnv-24/06/2013
      if("Y"==strChkKy){
        if(id_066!=null && ''!=id_066){
          if(!ky(id_066)){
            alert("Ký không thành công");
            return false;
          }
        }
      }
      //manhnv-24/06/2013
       stt= parseInt(strRowSelected.substr(7,5));
        ngay_dc =document.getElementById("ngaydc_"+stt).value;
        tthai_dxn=document.getElementById("tthai_"+sttNext).value;
        f.action = 'exDuyetXNDCTHop1Action.do?rowSelected='+strRowSelected+'&id='+id+'&ngay_dc='+ngay_dc+'&id_066='+id_066+"&tthai_dxn_thop="+tthai_dxn+"&loai_qtoan="+loai_qtoan;    
      }        
      }
      if (type == 'huy') {
           
          if (""==id|| id==null ){
            if(id_066!=null||''!=id_066){
            var r = confirm("Xác nhận hủy điện 066?");
              if (r == true)
                  {
                  stt= parseInt(strRowSelected.substr(7,5));
                   ngay_dc =document.getElementById("ngaydc_"+stt).value;
                  f.action = 'HuyXNDCTHop1Action.do?rowSelected='+strRowSelected+'&ngay_dc='+ngay_dc+'&id_066='+id_066;
                  }
                else
                  {
                  return false;
                  }
               
            }else{
            alert(GetUnicode('Không có thông tin bảng kê'));
            return false;
            }
          }else  if (""!=id&& id!=null ){
          var r = confirm("Xác nhận không duyệt ?");
              if (r == true)
                  {
                stt= parseInt(strRowSelected.substr(7,5));
                  ngay_dc =document.getElementById("ngaydc_"+stt).value;
                  f.action = 'HuyXNDCTHop1Action.do?rowSelected='+strRowSelected+'&id='+id+'&ngay_dc='+ngay_dc+'&id_066='+id_066+"&loai_qtoan="+loai_qtoan;    
                  }
                  else
                    {
                    return false;
                    }
          }    
      }
    if (type == 'print') {
        f.action = 'PrintXNDCTHop1Action.do';    
       var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';        
      }
      
     if (type == 'close') {
        f.action = 'mainAction.do';          
      } 
       f.submit();
  }
  //<!--manhnv-24/06/2013-->
  function ky(id_066){
    	try {
            jQuery('#ndung_ky_066').val(jQuery('#ndung_ky_066_'+id_066).val());
            var cert = jQuery("#eSign")[0];
            cert.InitCert();                   
            var serial = cert.Serial;
            jQuery("#certserial").val(serial);          
            var noi_dung_ky = jQuery('#ndung_ky_066_'+id_066).val();
            var sign = cert.Sign(noi_dung_ky);            
            jQuery("#chuky_ktt").val(sign);
            return true;
        }
        catch (e) {
            alert(e.description);
            return false;
        }
    }
    //<!--manhnv-24/06/2013-->
    
  sumSelected();
 
function sumSelected(){ 
    var size="<%=size%>";
    var valThu = [];
    var valChi = [];
    var tong_thu=0;
    var tong_chi=0;
    
    if(''!=size && size !=null&&'0'!=size && size >0){
      for (i=0;i<size;i++){
        var trang_thai = jQuery('#trang_thai_'+i).val();
        if(trang_thai=='03' || '03'==trang_thai){
          valThu[i] = 0;
          valChi[i] = 0;
          var thu = valThu[i].toString();
          var chi = valChi[i].toString();
        }else{
          valThu[i] = jQuery('#qtoan_thu_'+i).val();
          valChi[i] = jQuery('#qtoan_chi_'+i).val();
          var thu = valThu[i].toString();
          var chi= valChi[i].toString();                 
          if(jQuery('#trang_thai_'+i).val()=='01'||'01'==jQuery('#trang_thai_'+i).val()){           
            document.getElementById("bt").disabled=false
            document.getElementById("huy_bt").disabled=false
          }
        }
        tong_thu=tong_thu+parseInt(thu);
        tong_chi=tong_chi+parseInt(chi);
      }
      txt_thu_tcong = parseInt(jQuery('#qtoan_thu').val()) - tong_thu;
      txt_chi_tcong = parseInt(jQuery('#qtoan_chi').val()) - tong_chi;
      jQuery('#txt_thu_tcong').val(toFormatNumber(txt_thu_tcong,0,'.'));
      jQuery('#txt_chi_tcong').val(toFormatNumber(txt_chi_tcong,0,'.'));
    }
}
    

</script>
