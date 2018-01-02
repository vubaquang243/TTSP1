<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
  String notPHT = request.getAttribute("notPHT")==null?"":request.getAttribute("notPHT").toString();
  String notTTSP = request.getAttribute("notTTSP")==null?"":request.getAttribute("notTTSP").toString();
  String pht_ttsp = request.getAttribute("pht_ttsp")==null?"":request.getAttribute("pht_ttsp").toString();
  String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
  String size = request.getAttribute("size")==null?"":request.getAttribute("size").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    //defaultStateFormBK();
    var strRowSelected="<%=strRowSelected%>";
    if(strRowSelected!=null && '' != strRowSelected){
    rowSelectedFocusXNDC(strRowSelected);
    }
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="ViewTTinDChieuAction.do" method="post" >
  <c:set var="isNgoaiTe" value="${false}" scope="request"/>
  <logic:empty name="TCuuTTinDChieuForm" property="ma_nt">
    <fmt:setLocale value="vi_VI"/>
  </logic:empty>
  <logic:equal value="VND" name="TCuuTTinDChieuForm" property="ma_nt">
    <fmt:setLocale value="vi_VI"/>
  </logic:equal>
 <logic:notEqual value="VND" name="TCuuTTinDChieuForm" property="ma_nt">
    <fmt:setLocale value="vi_VI"/>
    <!-- <c:set var="isNgoaiTe" value="${true}" scope="request"/> -->
  </logic:notEqual> 
     <html:hidden name="TCuuTTinDChieuForm" property="bk_id"/>
    <html:hidden  name="TCuuTTinDChieuForm" property="pageNumber"/>
    <html:hidden name="TCuuTTinDChieuForm" property="lan_dc"/>
    <html:hidden name="TCuuTTinDChieuForm" property="nhkb_huyen"/>
    <html:hidden name="TCuuTTinDChieuForm" property="nhkb_tinh"/>
    <html:hidden name="TCuuTTinDChieuForm" property="ma_dv"/>
    <html:hidden name="TCuuTTinDChieuForm" property="tu_ngay"/>
    <html:hidden name="TCuuTTinDChieuForm" property="den_ngay"/>
    <html:hidden name="TCuuTTinDChieuForm" property="ma_nt"/>
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> Đối chiếu tổng hợp - Lập đề nghị quyết toán</span>
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
       <td width="42%" align="center" colspan="4" >
        <fieldset>
        <legend><font color="Blue">Danh s&#225;ch b&#7843;ng k&#234;</font></legend>
        <div  style="height:370px;overflow-y: scroll;">
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="1"
                                             cellspacing="0" cellpadding="0" >
                 <tr>
                 <td align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.ngaydc"/></td>
                 <td align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.duyet.manh"/></td>
                 <!--<td align="center" width="17%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.nhang"/></td>-->
                 <td align="center" width="25%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.sdxn"/></td>
                 <td align="center" width="25%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai"/></td>
                 <!--<td align="center" width="20%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai_ttin"/></td>-->
                 </tr>
                 <logic:empty name="colDChieu">
                  <tr>
                    <td colspan="5">
                      <font color="Red">Kh&#244;ng c&#243; b&#7843;ng k&#234; &#273;&#7889;i chi&#7871;u</font>
                      <input type="hidden" name="ket_qua" value="" id="b"/>
                      <input type="hidden" name="ket_qua" value="" id="a"/>
                    </td>
                  </tr>
                </logic:empty>
               <logic:notEmpty name="colDChieu">
                  <logic:iterate id="UDlist" name="colDChieu" indexId="index">
                 <tr class="ui-widget-content jqgrow ui-row-ltr" 
                      id="row_qt_<bean:write name="index"/>">
                   <td align="center">
                    <bean:write name="UDlist" property="ngay_dc"/>                    
                   </td>
                   <td align="center">
                    <bean:write name="UDlist" property="receive_bank"/>            
                   </td>
                   <td align="center" id="td_qt_<bean:write name="index"/>">
                    <bean:write name="UDlist" property="id"/>
                   </td>
                   <td align="center">
                    <logic:equal name="UDlist" property="tthai_dxn_thop" value="04">Hủy</logic:equal>
                      <logic:equal name="UDlist" property="tthai_dxn_thop" value="">Chưa tạo điện</logic:equal>
                      <logic:equal name="UDlist" property="tthai_dxn_thop" value="00">Chưa tạo điện</logic:equal>
                      <logic:equal name="UDlist" property="ket_qua_dxn_thop" value="0">
                        <logic:equal name="UDlist" property="tthai_dxn_thop" value="01">Đã tạo điện</logic:equal>
                        <logic:equal name="UDlist" property="tthai_dxn_thop" value="02">Đã tạo điện</logic:equal>
                      </logic:equal>
                      <logic:equal name="UDlist" property="ket_qua_dxn_thop" value="1"> 
                        <logic:equal name="UDlist" property="tthai_dxn_thop" value="01">Xác nhận - chờ duyệt</logic:equal>
                        <logic:equal name="UDlist" property="tthai_dxn_thop" value="03">Đã xác nhận</logic:equal>
                      </logic:equal>
                   <input type="hidden" name="tthai_<bean:write name="index"/>" id="tthai_<bean:write name="index"/>" value="<bean:write name="UDlist" property="tthai_dxn_thop"/>" />
                   <html:hidden name="UDlist" property="ngay_thuc_hien_dc"/>
                   </td>
                   <!--<td align="center">
                   <logic:equal name="UDlist" property="tthai_dxn_thop" value="02">                  
                        <logic:equal name="UDlist" property="tthai_ttin_thop" value="01">
                           &#272;&#227; g&#7917;i NH
                        </logic:equal>
                        <logic:equal name="UDlist" property="tthai_ttin_thop" value="02">
                          NH nh&#7853;n th&#224;nh c&#244;ng
                        </logic:equal>
                        <logic:equal name="UDlist" property="tthai_ttin_thop" value="03">
                         G&#7917;i NH kh&#244;ng th&#224;nh c&#244;ng
                        </logic:equal>
                   </logic:equal>
                   </td>-->
                 </tr>
                  </logic:iterate>
                </logic:notEmpty>             
             </table>
           </div>
        </fieldset>
       </td>
       <td  width="58%">
        <fieldset>
            <legend><font color="Blue">T&#7893;ng h&#7907;p k&#7871;t qu&#7843; &#273;&#7889;i chi&#7871;u</font></legend>
            <div style="height:370px;">
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
                   <% if(notTTSP == null || ""==notTTSP){%>
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
                  <input type="hidden" name="ket_qua" value="" id="ket_qua_ttsp"/>
                  <%}%>
                  </logic:empty>
                  <logic:notEmpty name="colTTSP">
                  <% if(notTTSP == null || ""==notTTSP){%>
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
                      <html:hidden property="ket_qua" name="items" styleId="ket_qua_ttsp"/>
                      <html:hidden property="id" name="items" styleId="ttsp_id" />
                      <input type="hidden" name="ttsp_id" value="<bean:write property="id"  name="items"/>"/>
                      <html:hidden property="ngay_dc" name="items"/>
                      <!--<html:hidden property="tthai_dxn_thop" name="items"/>-->
                      <html:hidden property="receive_bank" name="items"/>
                      
                    </td>                   
                  </tr>
                  <tr>
                    <td colspan="7">
                     <b>-<fmt:message key="doi_chieu.page.label.dc1.dlthu"/></b>
                    </td>
                  </tr>
                  <!-- Layout right tổng hợp kết quả đối chiếu VND -->
                  <c:if test="${isNgoaiTe == false}">
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="so_mon_thu_dtu"/>
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="so_tien_thu_dtu" />
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="mon_thu_dtu_kbnn"/>
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="tien_thu_dtu_kbnn"/>
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="chenh_mthu_dtu"/>
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="chenh_tthu_dtu"/>
                      </fmt:formatNumber>
                    </td>
                  </tr>
                  </c:if>
                  <c:if test="${isNgoaiTe == true}">
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="so_mon_thu_dtu"/>
                    </td>
                    <td align="right">
                      <fmt:formatNumber type="currency"  currencySymbol="">
                        <bean:write name="items" property="so_tien_thu_dtu" />
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="mon_thu_dtu_kbnn"/>
                    </td>
                    <td align="right">
                      <fmt:formatNumber type="currency"  currencySymbol="">
                        <bean:write name="items" property="tien_thu_dtu_kbnn"/>
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="chenh_mthu_dtu"/>
                    </td>
                    <td align="right">
                      <fmt:formatNumber type="currency"  currencySymbol="">
                        <bean:write name="items" property="chenh_tthu_dtu"/>
                      </fmt:formatNumber>
                    </td>
                  </tr>
                  </c:if>
                  <tr>
                    <td colspan="7">
                      <b>-<fmt:message key="doi_chieu.page.label.dc1.dlchi"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <c:if test="${isNgoaiTe == false}">
                    <td align="center">
                      <bean:write name="items" property="somon_dtu"/>
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="sotien_dtu" />
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="mon_chi_dtu_kbnn" />
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="tien_chi_dtu_kbnn" />
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="chenh_mchi_dtu" />
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="chenh_tchi_dtu" />
                      </fmt:formatNumber>
                    </td>
                    </c:if>
                    <c:if test="${isNgoaiTe == true}">
                    <td align="center">
                      <bean:write name="items" property="somon_dtu"/>
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="sotien_dtu" />
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="mon_chi_dtu_kbnn" />
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="tien_chi_dtu_kbnn" />
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="chenh_mchi_dtu" />
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="chenh_tchi_dtu" />
                      </fmt:formatNumber>
                    </td>
                    </c:if>
                  </tr>
                  <% if(notPHT != null && ""!=notPHT){%><input type="hidden" name="ket_qua" value="0" id="ket_qua_pht"/><%}%>
                  </logic:iterate>
                  <%}%>
                  </logic:notEmpty>
                  <logic:empty name="colPHT">
                  <% if(notPHT == null || ""==notPHT){%>
                  <tr>
                    <td colspan="7"> <b>PHT:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <font style="FONT-WEIGHT: bold; color:red">
                          Ch&#432;a nh&#7853;n &#273;&#432;&#7907;c k&#7871;t qu&#7843; t&#7915; PHT
                      </font>
                      <input type="hidden" name="ket_qua" value="" id="ket_qua_pht"/>
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
                  <%}%>
                  </logic:empty>              
                  
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
                        <html:hidden property="ket_qua" name="items" styleId="ket_qua_pht"/>
                        <html:hidden property="id" name="items"  styleId="pht_id"/>
                        <input type="hidden" name="pht_id" value="<bean:write property="id"  name="items"/>"/>
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
                    <c:if test="${isNgoaiTe == false}">
                    <td align="center">
                      <bean:write name="items" property="tong_mon_pht"/>
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="tong_ps_pht" />
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="mon_thu_dtu_kbnn" />
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="tien_thu_dtu_kbnn"/>
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="chenh_mthu_dtu_pht" />
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="chenh_tthu_dtu_pht" />
                      </fmt:formatNumber>
                    </td>
                    </c:if>
                    <c:if test="${isNgoaiTe == true}">
                    <td align="center">
                      <bean:write name="items" property="tong_mon_pht"/>
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="tong_ps_pht" />
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="mon_thu_dtu_kbnn" />
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="tien_thu_dtu_kbnn"/>
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="chenh_mthu_dtu_pht" />
                    </td>
                    <td align="right">
                      <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="chenh_tthu_dtu_pht" />
                      </fmt:formatNumber>
                    </td>
                    </c:if>
                  </tr>
                  <html:hidden property="ket_qua" name="items"/>
                        <html:hidden property="trang_thai_bkdc" name="items"/>
                        <html:hidden property="ket_qua_pht" name="items"/>
                        <html:hidden property="receive_bank" name="items"/>
                    <% if(notTTSP != null && ""!=notTTSP){%>
                      <input type="hidden" name="ket_qua" value="0" id="ket_qua_ttsp"/>            
                    <%}%>
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
                        <td width="25%" align="left">Số thu thủ công</td>
                        <td width="25%" align="right">                                       
                          <input type="text" style="width:99%"  name="so_thu" disabled="disabled"  id="so_thu"  value="<fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="tien_thu_tcong_kbnn"/></fmt:formatNumber>" class="fieldTextRight" />
                        </td>
                      </tr>
                      <tr>
                        <td  align="left">Số chi thủ công</td>
                        <td  align="right"> 
                          <input type="text" style="width:99%"  name="so_chi" disabled="disabled"  id="so_chi"   value="<fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="tien_chi_tcong_kbnn"/></fmt:formatNumber>" class="fieldTextRight" />                                   
                        </td>            
                      </tr>
                      <tr>
                        <td colspan="4">
                          <b>Số dư tại thời điểm Cut-off-time: 
                            <c:if test="${isNgoaiTe == false}">
                              <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                <bean:write name="items" property="sodu_kbnn"/>
                              </fmt:formatNumber>
                            </c:if>
                            <c:if test="${isNgoaiTe == true}">
                              <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                                <bean:write name="items" property="sodu_kbnn"/>
                              </fmt:formatNumber>
                            </c:if>
                          </b> 
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
                          <input type="text"  name="txt_thu_tcong" style="width:99%" disabled="disabled"  id="txt_thu_tcong" onkeyup="checkKey('txt_thu_tcong')"  value="<fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol=""><bean:write name="items" property="ket_chuyen_thu"/></fmt:formatNumber>" class="fieldTextRight" />
                         </td>
                        <td  align="left" width="25%">Số đề nghị QT chi
                            <%if(size!="0" && !"0".equals(size)&&!"".equals(size)&&size!=null){%>
                               còn lại
                             <%}%>
                        </td>
                        <td  align="right" width="25%"> 
                          <input type="text"  name="txt_chi_tcong" style="width:99%" disabled="disabled"  id="txt_chi_tcong" onkeyup="checkKey('txt_chi_tcong')"   value="<fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol=""><bean:write name="items" property="ket_chuyen_chi"/></fmt:formatNumber>" class="fieldTextRight" />                                   
                        </td>            
                      </tr>
                      <tr>
                        <td  align="left">Ghi chú</td>
                        <td  align="left" colspan="4"> 
                          <input type="text"  name="txt_noi_dung" style="width:99%" disabled="disabled"  id="txt_noi_dung"  value="" class="fieldTextRight" />                                   
                        </td>            
                      </tr>  
                      </logic:iterate>
                    </logic:notEmpty>
                    <logic:empty name="colTHDC">                    
                      <logic:iterate id="items" name="colSoThuCong">
                      <tr>
                        <td colspan="4"> <b>Giao dịch thủ công </b> </td>
                      </tr>
                      <tr> 
                        <td width="25%" align="left">
                             Số thu thủ công
                        </td>
                        <td width="25%" align="right">                                       
                          <input type="text" style="width:99%"  name="so_thu" disabled="disabled"  id="so_thu"  value="<fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="tien_thu_tcong_kbnn"/></fmt:formatNumber>" class="fieldTextRight" />                  
                        </td>
                        <td  align="center" rowspan="2" colspan="2">&nbsp;</td>
                      </tr>
                      <tr> 
                        <td  align="left">Số chi thủ công</td>
                        <td  align="right"> 
                          <input type="text" style="width:99%"  name="so_chi" disabled="disabled"  id="so_chi"   value="<fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="tien_chi_tcong_kbnn"/></fmt:formatNumber>" class="fieldTextRight" />                                   
                        </td>            
                      </tr>
                      <tr>
                          <td colspan="4">
                            <b>Số dư tại thời điểm Cut-off-time: 
                            <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""></fmt:formatNumber></b> 
                          </td>
                        </tr>
                      <tr> 
                        <td width="25%" align="left">Số đề nghị QT thu
                          <%if(size!="0" && !"0".equals(size)&&!"".equals(size)&&size!=null){%>còn lại<%}%>
                        </td>
                         <td align="right"   width="25%">                                       
                          <input type="text"  name="txt_thu_tcong" style="width:99%" disabled="disabled"  id="txt_thu_tcong" onkeyup="checkKey('txt_thu_tcong')" />
                          <input type="hidden"  name="qtoan_thu" id="qtoan_thu"/>  
                         </td>
                        <td  align="left" width="25%">
                            Số đề nghị QT chi
                            <%if(size!="0" && !"0".equals(size)&&!"".equals(size)&&size!=null){%>còn lại<%}%>
                          </td>
                          <td  align="right"  width="25%"> 
                          <input type="text"  name="txt_chi_tcong" style="width:99%" disabled="disabled"  id="txt_chi_tcong" onkeyup="checkKey('txt_chi_tcong')" />                                                                    
                         </td>            
                      </tr>
                      <tr>
                        <td  align="left">Ghi chú</td>
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
        <td colspan="5" >
          <fieldset>
            <legend><font color="Blue">Danh sách đề nghị quyết toán</font></legend>
              <div width="50%" align="center">              
                <table cellspacing="0" width="100%" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">       
                    <thead >
                      <tr>
                        <th bgcolor="#f0f0f0" align="center" width="2%">STT</th>
                        <th bgcolor="#f0f0f0" align="center" width="7%">Ngày QT</th>
                        <th bgcolor="#f0f0f0" align="center"  width="11%">Số điện</th>
                        <th bgcolor="#f0f0f0" align="center"  width="11%">QT thu</th>                                 
                        <th bgcolor="#f0f0f0"  align="center" width="11%">QT chi</th>
                        <th bgcolor="#f0f0f0" align="center" width="5%">Loại tiền</th>
                        <th bgcolor="#f0f0f0" align="center" width="5%">Loại QT</th>
                        <th bgcolor="#f0f0f0" align="center" width="20%">Ghi chú</th>
                        <th bgcolor="#f0f0f0" align="center" width="6%">Trạng thái</th>
                        <th bgcolor="#f0f0f0" align="center" width="20%">Mô tả</th>
                        <th bgcolor="#f0f0f0" align="center" width="2%">#</th>
                      </tr>
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
                          </td>
                          <c:if test="${isNgoaiTe == false}">
                          <td align="right">
                            <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                              <bean:write name="items" property="qtoan_thu"/>
                            </fmt:formatNumber>
                            <input type="hidden" name="qtoan_thu" id="qtoan_thu_<bean:write name="stt"/>" value="<bean:write name="items" property="qtoan_thu"/>"/>
                          </td>
                          <td align="right">
                            <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                              <bean:write name="items" property="qtoan_chi"/>
                            </fmt:formatNumber>
                            <input type="hidden" name="qtoan_chi" id="qtoan_chi_<bean:write name="stt"/>" value="<bean:write name="items" property="qtoan_chi"/>"/>
                          </td>
                          </c:if>
                          <c:if test="${isNgoaiTe == true}">
                          <td align="right">
                            <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                              <bean:write name="items" property="qtoan_thu"/>
                            </fmt:formatNumber>
                            <input type="hidden" name="qtoan_thu" id="qtoan_thu_<bean:write name="stt"/>" value="<bean:write name="items" property="qtoan_thu"/>"/>
                          </td>
                          <td align="right">
                            <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                              <bean:write name="items" property="qtoan_chi"/>
                            </fmt:formatNumber>
                            <input type="hidden" name="qtoan_chi" id="qtoan_chi_<bean:write name="stt"/>" value="<bean:write name="items" property="qtoan_chi"/>"/>
                          </td>
                          </c:if>
                          <td align="center" title="<bean:write name="items" property="loai_tien"/>">
                            <bean:write name="items" property="loai_tien"/>
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
                              <div style="text-overflow:ellipsis;width:175px;white-space:nowrap;  overflow:hidden; font-size:12px">
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
                            <div style="text-overflow:ellipsis;width:175px;white-space:nowrap;  overflow:hidden; font-size:12px">
                              <bean:write name="items" property="error_code"/>-<bean:write name="items" property="error_desc"/>
                            </div>
                          </td>
                          <td>
                            <span id="print" onclick="chk_print('print','<bean:write name="items" property="id"/>','<bean:write name="items" property="kq_dxn_thop"/>')"  title="In" style="cursor:pointer;"><img src="<%=request.getContextPath()%>/styles/images/icon_print_small.png" /></span>
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
    <tr > 
      <td align="right" colspan="5">
        <%if( (notTTSP!=null&&"".equals(notTTSP)) || (  pht_ttsp!=null && !"".equals(pht_ttsp)) ){%>
         <button type="button" onclick="check('print','<bean:write name="TCuuTTinDChieuForm" property="ma_nt"/>')" accesskey="i" id="inbt">
            <span class="sortKey">I</span>n KQĐC
          </button>
          <%}%>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <button  type="button" onclick="check('close')" accesskey="o">
            Th<span class="sortKey">o</span>&#225;t
          </button>
      </td>
    </tr>
</table>
    <html:hidden property="bk_id"/>
    <html:hidden property="pageNumber"/>
    <html:hidden property="lan_dc"/>
    <html:hidden property="nhkb_huyen"/>
    <html:hidden property="nhkb_tinh"/>
    <html:hidden property="ma_dv"/>
    <html:hidden property="tu_ngay"/>
    <html:hidden property="den_ngay"/>
    <html:hidden property="ma_nt"/>
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
 var f = document.forms[0];
 
 //Lay parameter tu url
 function getParameterByName(name) {

    var  url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

  function check(type,ma_nt) {  
 
//  var ttsp_id=jQuery('#ttsp_id').val();
//  var pht_id=jQuery('#pht_id').val();
    if (type == 'print') {
        if(ma_nt == null || ma_nt==''){
          ma_nt = getParameterByName('ma_nt');
        }
        var kq_pht= document.getElementById("ket_qua_pht").value;
        var kq_ttsp = document.getElementById("ket_qua_ttsp").value;
        f.action = 'printTTinDChieuAction.do?type=kqdc1&ket_qua_pht='+kq_pht+'&ket_qua_ttsp='+kq_ttsp+'&ma_nt='+ma_nt;    
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';  
        f.submit();
    }
    if (type == 'close') {
      var tcuu="<%=tcuu%>";
      
      if(tcuu!=null && ""!=tcuu){
        //f.action = 'FindTTinDChieuAction.do'+tcuu;
        window.close();
      }else if (tcuu==null || ""==tcuu){
        f.action = 'TCuuTTinDChieuAction.do';
      } 
      f.submit();
    }
  }

function chk_print(type,id_066,kq_dxn_Thop) {
    if (type == 'print') {    
        f.action = 'PrintXNDCTHop1Action.do?type=066&id_066='+id_066+"&loai_kq066="+kq_dxn_Thop;    
       var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';      
      }
      f.submit();
  }

sumSelected();
 
function sumSelected(){ 
var size="<%=size%>";
        var valThu = [];
        var valChi = [];
        var tong_thu=0;
        var tong_chi=0;
        
        if(''!=size && size !=null&&'0'!=size && size >0){
            for (i=0;i<size;i++){
            var trang_thai=jQuery('#trang_thai_'+i).val();
                if(trang_thai=='03' || '03'==trang_thai){
                  valThu[i] = 0;
                  var thu= valThu[i].toString();
                  valChi[i] = 0;
                  var chi= valChi[i].toString();
                }else{
                  valThu[i] = jQuery('#qtoan_thu_'+i).val();
                  var thu= valThu[i].toString();
                  valChi[i] = jQuery('#qtoan_chi_'+i).val();
                  var chi= valChi[i].toString();
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

