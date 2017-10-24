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
  String notTTSP = request.getAttribute("notTTSP")==null?"":request.getAttribute("notTTSP").toString();
  String huy = request.getAttribute("huy")==null?"":request.getAttribute("huy").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    //defaultStateFormBK();
    var strRowSelected="<%=strRowSelected%>";
    var viewAcc="<%=viewAcc%>";
    var strDuyet="<%=strDuyet%>";
    var huy="<%=huy%>";
    if(strRowSelected!=null && '' != strRowSelected){
    rowSelectedFocusXNDC(strRowSelected);
    }
    if(viewAcc!=null && '' != viewAcc){
       jQuery("#row_qt_0").attr("disabled", true);
    }
     if(strDuyet!=null && '' != strDuyet){
        alert(GetUnicode('T&#7841;o &#272;XN t&#7893;ng h&#7907;p th&#224;nh c&#244;ng'));
    }
    if(huy!=null && '' != huy){
        alert(GetUnicode('H&#7911;y &#272;XN t&#7893;ng h&#7907;p th&#224;nh c&#244;ng'));
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
  <!--manhnv-24/06/2013-->
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="doi_chieu.page.title.duyetxndcthop"/></span>
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
        <div  style="height:300px;overflow-y: scroll;">
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
                 <td align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai_ttin"/></td>
                 
                 </tr>
                 <logic:empty name="colDChieu">
                  <tr>
                    <td colspan="6">
                      <font color="Red">Kh&#244;ng c&#243; b&#7843;ng k&#234; &#273;&#7889;i chi&#7871;u</font>
                      <input type="hidden" id="tthai_0" value="02" />
                    </td>
                  </tr>
                </logic:empty>
               <logic:notEmpty name="colDChieu">
                  <logic:iterate id="UDlist" name="colDChieu" indexId="index">
                 <tr class="ui-widget-content jqgrow ui-row-ltr" 
                       id="row_qt_<bean:write name="index"/>"
                      onclick="rowSelectedFocusXNDC('row_qt_<bean:write name="index"/>');
                               fillDataDuyetXNTH('DuyetXNDCTHop1_PHTAction.do','<bean:write name="UDlist" property="id"/>','<bean:write name="UDlist" property="ngay_dc"/>','row_qt_<bean:write name="index"/>');">
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
                   <logic:notEqual name="UDlist" property="tthai_dxn_thop" value="00">
                       <logic:equal name="UDlist" property="ket_qua_dxn_thop" value="0">
                          <fmt:message key="doi_chieu.page.lable.bk02"/>-
                       </logic:equal>
                       <logic:equal name="UDlist" property="ket_qua_dxn_thop" value="1">
                          <fmt:message key="doi_chieu.page.lable.bk01"/>-
                       </logic:equal> 
                   </logic:notEqual>
                   <logic:equal name="UDlist" property="tthai_dxn_thop" value="01">
                      <fmt:message key="doi_chieu.page.lable.duyet.01"/>
                   </logic:equal>
                   <logic:equal name="UDlist" property="tthai_dxn_thop" value="02">
                      <fmt:message key="doi_chieu.page.lable.duyet.02"/>
                   </logic:equal>
                   <input type="hidden" name="tthai_<bean:write name="index"/>" id="tthai_<bean:write name="index"/>" value="<bean:write name="UDlist" property="tthai_dxn_thop"/>" />
                   <input type="hidden" name="ngaydc_<bean:write name="index"/>" id="ngaydc_<bean:write name="index"/>" value="<bean:write name="UDlist" property="ngay_dc"/>" />
                   </td>
                   <td align="center">
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
            <div style="height:300px;">
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
                  <input type="hidden" name="ket_qua" value="" id="a"/>
                  <%}%></%}%>
                  </logic:empty>
                  
                  <logic:notEmpty name="colTTSP">
                  <logic:iterate id="items" name="colTTSP">
                    <input type="hidden" name="ttsp_id" value="<bean:write property="id"  name="items"/>"/>
                  </logic:iterate>
                  </logic:notEmpty>
                  

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
                  
                  <logic:notEmpty name="colPHT">
                  <logic:iterate id="items" name="colPHT">
                  <% if(notTTSP != null && ""!=notTTSP){
                    %>
                    <input type="hidden" name="ket_qua" value="0" id="a"/>            
                    <%}%>
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
                    <td align="center">
                      <fmt:formatNumber pattern="###,###"><bean:write name="items" property="tong_mon_pht"/></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:formatNumber pattern="###,###"><bean:write name="items" property="tong_ps_pht" /></fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:formatNumber pattern="###,###"><bean:write name="items" property="mon_thu_dtu_kbnn" /></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:formatNumber pattern="###,###"><bean:write name="items" property="tien_thu_dtu_kbnn"/></fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:formatNumber pattern="###,###"><bean:write name="items" property="chenh_mthu_dtu_pht" /></fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:formatNumber pattern="###,###"><bean:write name="items" property="chenh_tthu_dtu_pht" /></fmt:formatNumber>
                    </td>
                  </tr>
                  <html:hidden property="ket_qua" name="items"/>
                        <html:hidden property="trang_thai_bkdc" name="items"/>
                        <html:hidden property="ket_qua_pht" name="items"/>
                        <html:hidden property="receive_bank" name="items"/>
                        
                  </logic:iterate>
                  </logic:notEmpty>              
              </table>
              <br/>
              <table width="100%" cellspacing="0" cellpadding="2"
                           bordercolor="#e1e1e1" border="0" align="center"
                           style="BORDER-COLLAPSE: collapse">
                    <tr>
                      <td>
                      <div width="50%">              
                            <table width="100%" cellspacing="0" cellpadding="2"
                               bordercolor="#e1e1e1" border="1" align="center"
                               style="BORDER-COLLAPSE: collapse">
                               <tr>
                                <th class="promptText" bgcolor="#f0f0f0" style="width:50%">
                                  <div align="center">
                                     
                                  </div>
                                 </th>
                                <th class="promptText" bgcolor="#f0f0f0" style="width:50%">
                                  <div align="center">                                 
                                     <fmt:message key="doi_chieu.page.lable.stien"/>
                                  </div>
                                 </th>
                               </tr>              
                              <logic:notEmpty name="colTHDC">
                                <logic:iterate id="items" name="colTHDC">
                                 <tr> 
                                    <td width="25%" align="left">
                                        Ch&#234;nh l&#7879;ch s&#7889; k&#7871;t chuy&#7875;n thu
                                      </td>
                                      <td width="25%" align="right"> 
                                      <logic:notEqual name="items" property="tien_thu_tcong_kbnn" value="">
                                        <b><bean:write property="tien_thu_tcong_kbnn" name="items" format="###,###"/></b>
                                      </logic:notEqual>
                                     </td>
                                  </tr>
                                  <!--<tr>
                                    <td width="25%" align="left">
                                        <fmt:message key="doi_chieu.page.lable.duyet.chitc"/>
                                      </td>
                                      <td width="25%" align="right"> 
                                      <logic:notEqual name="items" property="tien_chi_tcong_kbnn" value="">
                                        <b><bean:write property="tien_chi_tcong_kbnn" name="items" format="###,###"/></b>
                                      </logic:notEqual>
                                     </td>            
                                  </tr>-->
                              </logic:iterate>
                              </logic:notEmpty>
                              <logic:empty name="colTHDC">
                                 <tr> 
                                    <td width="25%" align="left">
                                        Ch&#234;nh l&#7879;ch s&#7889; k&#7871;t chuy&#7875;n thu
                                      </td>
                                      <td width="25%" align="right"> 
                                      &nbsp;
                                     </td>
                                  </tr>
                                  <!--<tr>
                                    <td width="25%" align="left">
                                       <fmt:message key="doi_chieu.page.lable.duyet.chitc"/>
                                      </td>
                                      <td width="25%" align="right"> 
                                      &nbsp;
                                     </td>            
                                  </tr>-->
                              </logic:empty>
                            
                            </table>
                      </div>
                      </td>
                  </tr>
                </table>
            </div>
            </fieldset>
       </td>
      </tr>    
      <tr>
        <td colspan="5" width="70%">
          <fieldset>
            <legend><font color="Blue"><fmt:message key="doi_chieu.page.ketchuyen"/></font></legend>
                <table width="100%" cellspacing="0" cellpadding="2"
                           bordercolor="#e1e1e1" border="0" align="center"
                           style="BORDER-COLLAPSE: collapse">
                    <tr>
                      <td>&nbsp;</td>
                      <td>
                      <div width="40%">              
                            <table width="100%" cellspacing="0" cellpadding="2"
                               bordercolor="#e1e1e1" border="1" align="center"
                               style="BORDER-COLLAPSE: collapse">
                               <tr>
                                <th class="promptText" bgcolor="#f0f0f0" style="width:25%">
                                  <div align="center">
                                     
                                  </div>
                                 </th>
                                 <th class="promptText" bgcolor="#f0f0f0" style="width:25%">
                                  <div align="center">
                                     
                                  </div>
                                 </th>
                               </tr>              
                              <logic:notEmpty name="colTHDC">
                                <logic:iterate id="items" name="colTHDC">
                                 <tr> 
                                    <td width="50%" align="left">
                                        <fmt:message key="doi_chieu.page.label.dc1.thunh"/>
                                      </td>
                                      <td width="50%" align="right"> 
                                      <logic:notEqual name="items" property="so_ketchuyen" value="">
                                        <b><bean:write property="so_ketchuyen" name="items" format="###,###"/></b>
                                      </logic:notEqual>
                                     </td>
                                  </tr>
                                  <!--<tr>
                                    <td width="50%" align="left">
                                        <fmt:message key="doi_chieu.page.label.dc1.chinh"/>
                                      </td>
                                      <td width="50%" align="right"> 
                                      <logic:notEqual name="items" property="ps_chi" value="">
                                        <b><bean:write property="ps_chi" name="items" format="###,###"/></b>
                                      </logic:notEqual>
                                     </td>                                               
                                  </tr>-->
                              </logic:iterate>
                              </logic:notEmpty>
                              <logic:empty name="colTHDC">
                                 <tr> 
                                    <td width="50%" align="left">
                                        <fmt:message key="doi_chieu.page.label.dc1.thunh"/>
                                      </td>
                                      <td width="50%" align="right"> 
                                      &nbsp;
                                     </td>
                                  </tr>
                                  <!--<tr>
                                    <td width="50%" align="left">
                                        <fmt:message key="doi_chieu.page.label.dc1.chinh"/>
                                      </td>
                                      <td width="50%" align="right"> 
                                      &nbsp;
                                     </td>           
                                  </tr>-->
                              </logic:empty>
                              <tr>
                                <td colspan="2">
                                  &nbsp;
                                </td>
                              </tr>
                            </table>
                      </div>
                      </td>
                      <td>&nbsp;</td>
                      <td>
                        <div width="40%">              
                            <table width="100%" cellspacing="0" cellpadding="2"
                               bordercolor="#e1e1e1" border="1" align="center"
                               style="BORDER-COLLAPSE: collapse">
                               <tr>
                                  <th class="promptText" bgcolor="#f0f0f0" style="width:50%">
                                  <div align="center">
                                     <fmt:message key="doi_chieu.page.lable.sducutoff"/>
                                  </div>
                                 </th>
                                 <th class="promptText" bgcolor="#f0f0f0" style="width:50%">
                                  <div align="center">
                                     
                                     <fmt:message key="doi_chieu.page.lable.stien"/>
                                  </div>
                                 </th>
                               </tr>
                           <logic:notEmpty name="colTHDC">
                            <logic:iterate id="items" name="colTHDC">
                               <tr>                     
                                  <td align="left">
                                    <fmt:message key="doi_chieu.page.lable.duyet.cutoffkbnn"/>
                                  </td>
                                  <td  align="right">                      
                                      <b><bean:write property="so_du" name="items" format="###,###"/></b>
                                      <html:hidden property="so_du" name="items" styleId="nhtm"/>
                                      <html:hidden property="tien_thu_tcong_kbnn" name="items" styleId="thu_kbnn"/>
                                      <html:hidden property="tien_chi_tcong_kbnn" name="items" styleId="chi_kbnn"/>
                                  </td>
                                </tr>
                                <tr>                     
                                  <td align="left">
                                    <fmt:message key="doi_chieu.page.lable.duyet.cutoffnh"/>
                                  </td>
                                  <td align="right">                      
                                       <b><bean:write property="sodu_kbnn" name="items" format="###,###"/></b>
                                      <html:hidden property="sodu_kbnn" name="items" styleId="kbnn"/>
                                   </td>
                                 </tr>
                                 <tr>                     
                                  <td align="left">
                                    <fmt:message key="doi_chieu.page.lable.duyet.chenhlech"/>
                                  </td>
                                  <td align="right">                      
                                      <font color="red"><b><bean:write property="chenh_lech" name="items" format="###,###"/></b></font>
                                      <html:hidden property="chenh_lech" name="items" styleId="kbnn"/>
                                   </td> 
                               </tr>
                              </logic:iterate>
                            </logic:notEmpty>
                            <logic:empty name="colTHDC">
     
                               <tr>                     
                                  <td align="left">
                                    <fmt:message key="doi_chieu.page.lable.duyet.cutoffkbnn"/>
                                  </td>
                                  <td  align="right">                      
                                      &nbsp;
                                  </td>
                                </tr>
                                <tr>                     
                                  <td align="left">
                                    <fmt:message key="doi_chieu.page.lable.duyet.cutoffnh"/>
                                  </td>
                                  <td align="right">                      
                                      &nbsp;
                                   </td>
                                 </tr>
                                 <tr>                     
                                  <td align="left">
                                    <fmt:message key="doi_chieu.page.lable.duyet.chenhlech"/>
                                  </td>
                                  <td align="right">                      
                                      &nbsp;
                                   </td> 
                                </tr>
 
                            </logic:empty>
                            </table>
                        </div>
                      </td>
                      <td>&nbsp;</td>
                  </tr>
                </table>
          </fieldset>
        </td>
    </tr>
    <tr>
    <td align="center" colspan="6">
             <button type="button" onclick="check('huy')" accesskey="D" style="width:60px" name="btton" id="huy_bt">
              <span class="sortKey">H</span>&#7911;y
            </button>           
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
            <button type="button" onclick="check('duyet')" accesskey="D" style="width:60px" name="btton" id="bt">
              <span class="sortKey">D</span>uy&#7879;t
            </button>           
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
             <button type="button" onclick="check('print')" accesskey="D" style="width:100px" name="btton" id="inbt">
              <span class="sortKey">I</span>n k&#7871;t qu&#7843;
            </button>           
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
            <button  type="button" onclick="check('close')" accesskey="o" style="width:60px">
              Th<span class="sortKey">o</span>&#225;t
            </button>
      
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
    if(strRowSelected!=null && '' != strRowSelected){
      stt= strRowSelected.substr(7,5);
      sttNext=parseInt(stt);
      tthai_dxn=document.getElementById("tthai_"+sttNext).value;
          if(tthai_dxn=='02'){
              document.getElementById("bt").disabled=true;
              document.getElementById("huy_bt").disabled=true;
         }else if (tthai_dxn=='01'||tthai_dxn=='00'){
          document.getElementById("bt").disabled=false;
        }
    }else{
      document.getElementById("bt").disabled=true;
      document.getElementById("inbt").disabled=true;
      document.getElementById("huy_bt").disabled=true;
    }
  }
  
  function check(type) {  
      var id=document.getElementById("kq_id").value;
var strRowSelected="<%=strRowSelected%>";
      
     if (type == 'duyet') {
      if (""==id|| id==null ){
        alert(GetUnicode('Không có thông tin bảng kê'));
        return false;
      }else  if (""!=id|| id!=null ){
      //manhnv-24/06/2013
        if("Y"==strChkKy){
            if(!ky()){
              alert("Ký không thành công");
              return false;
            }
          }
      //manhnv-24/06/2013
       stt= parseInt(strRowSelected.substr(7,5));
        ngay_dc =document.getElementById("ngaydc_"+stt).value;
        f.action = 'exDuyetXNDCTHop1_PHTAction.do?rowSelected='+strRowSelected+'&id='+id+'&ngay_dc='+ngay_dc;    
      }
        
      }
         if (type == 'huy') {
      if (""==id|| id==null ){
        alert(GetUnicode('Không có thông tin bảng kê'));
        return false;
      }else  if (""!=id|| id!=null ){
      stt= parseInt(strRowSelected.substr(7,5));
        ngay_dc =document.getElementById("ngaydc_"+stt).value;
        f.action = 'HuyXNDCTHop1_PHTAction.do?rowSelected='+strRowSelected+'&id='+id+'&ngay_dc='+ngay_dc;    

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
  function ky(){
    	try {
            var cert = jQuery("#eSign")[0];
            cert.InitCert();                   
            var serial = cert.Serial;
            jQuery("#certserial").val(serial);
            
            var noi_dung_ky = jQuery("#noi_dung_ky").val();
                           
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
</script>
