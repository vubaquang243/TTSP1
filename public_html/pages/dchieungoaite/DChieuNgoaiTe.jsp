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
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.ngoaite.js"></script>
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
  String strAllow = request.getAttribute("notAllow")==null?"":request.getAttribute("notAllow").toString();
  String strRowSelected = request.getAttribute("rowSelected")==null?"":request.getAttribute("rowSelected").toString();
  String clech = request.getAttribute("DXN_clech")==null?"":request.getAttribute("DXN_clech").toString();
  String dung = request.getAttribute("dung")==null?"":request.getAttribute("dung").toString();
  String chuataodxn = request.getAttribute("chuataodxn")==null?"":request.getAttribute("chuataodxn").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    //defaultStateFormBK();
    var strRowSelected="<%=strRowSelected%>";
    var clech="<%=clech%>";
    var chuataodxn="<%=chuataodxn%>";
    var dung="<%=dung%>";
    if(strRowSelected!=null && '' != strRowSelected){
    rowSelectedFocusXNDC(strRowSelected);
    }
    if(clech!=null && '' != clech){
    alert(GetUnicode('T&#7841;o &#272;XN ch&#234;nh l&#7879;ch th&#224;nh c&#244;ng'));
    }
    if(chuataodxn!=null && '' != chuataodxn){
    alert(GetUnicode('Ch&#432;a ho&#224;n th&#224;nh &#273;&#7889;i chi&#7871;u &#273;&#7847;y &#273;&#7911; c&#225;c b&#7843;ng k&#234;'));
    }
    if(dung!=null && '' != dung){
    alert(GetUnicode('T&#7841;o &#272;XN kh&#7899;p &#273;&#250;ng th&#224;nh c&#244;ng'));
    }
    
  });
  
  function show_tool_tip(){
    jQuery("#ly_do").mouseover(function() {
        jQuery("#ly_do").attr('title',jQuery("#ly_do").val())
    });
  }
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="DChieuNgoaiTeAction.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="doi_chieu.page.title.dc1"/></span>
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
       <td width="60%">
        <fieldset>
        <legend><font color="Blue">Danh s&#225;ch b&#7843;ng k&#234;</font></legend>
        <div style="height:185px;overflow-y: scroll;width:100%" >
          <table  class="data-grid" id="data-grid" border="1" cellspacing="0" cellpadding="0" width="96%">
                 <tr>
                   <td align="center" width="20%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.ngaydc"/></td>
                   <td align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.nhang"/></td>
                   <td align="center" width="7%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.lan"/></td>
                   <td align="center" width="20%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.sbke"/></td>
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
                  
                   <input type="hidden" id="trang_thai_kq_<bean:write name="index"/>" value="<bean:write name="UDlist" property="trang_thai_kq"/>" />
                    <tr class="ui-widget-content jqgrow ui-row-ltr" id="row_qt_<bean:write name="index"/>" 
                        onclick="rowSelectedFocusDC('row_qt_<bean:write name="index"/>'); 
                                bton('<bean:write name="UDlist" property="trang_thai"/>',
                                '<bean:write name="UDlist" property="trang_thai_kq"/>');
                                 fillDataTHopDC1('DChieuNgoaiTeAction.do',
                                 '<bean:write name="UDlist" property="mt_id"/>',
                                 '<bean:write name="UDlist" property="id"/>',
                                 '<bean:write name="UDlist" property="kq_id"/>',
                                 '<bean:write name="UDlist" property="send_bank"/>',
                                 'row_qt_<bean:write name="index"/>')">
                   <td align="center" >                    
                      <bean:write name="UDlist" property="ngay" format="DD/MM/YYYY"/>
                   </td>
                   <td align="center" >
                      <bean:write name="UDlist" property="send_bank"/>
                   </td>
                   <td align="center">
                      <bean:write name="UDlist" property="lan_dc"/>                    
                   </td>
                   <td align="center" id="td_qt_<bean:write name="index"/>">
                       <input name="row_item" id="col_qt_<bean:write name="index"/>" 
                              type="text" style="border:0px;font-size:10px;" onkeydown="arrowUpDownDC(event)"
                              value="<bean:write name="UDlist" property="mt_id"/> " readonly="true"/>                     
                       <input type="hidden" id="idTmp_<bean:write name="index"/>" value="<bean:write name="UDlist" property="id"/>" />
                       <input type="hidden" id="kqidTmp_<bean:write name="index"/>" value="<bean:write name="UDlist" property="mt_id"/>" />
                    </td>
                   <td align="center">
                     <logic:equal name="UDlist" property="trang_thai" value="00">
                        <fmt:message key="doi_chieu.page.lable.00"/>
                     </logic:equal>
                     <logic:equal name="UDlist" property="trang_thai" value="">
                        <fmt:message key="doi_chieu.page.lable.dc1.99"/>
                     </logic:equal>
                     <logic:equal name="UDlist" property="trang_thai" value="01">
                        <fmt:message key="doi_chieu.page.lable.01"/> -
                     </logic:equal>
                     <logic:equal name="UDlist" property="trang_thai" value="02">
                        <fmt:message key="doi_chieu.page.lable.02"/> -
                     </logic:equal>
                     <logic:notEqual name="UDlist" property="trang_thai" value="00">
                     <logic:equal name="UDlist" property="trang_thai_kq" value="00">
                        Chưa tạo ĐXN
                     </logic:equal>
                     <logic:equal name="UDlist" property="trang_thai_kq" value="01">
                        <fmt:message key="doi_chieu.page.lable.duyet.01"/>
                     </logic:equal>
                     <logic:equal name="UDlist" property="trang_thai_kq" value="02">
                        <fmt:message key="doi_chieu.page.lable.duyet.02"/>
                     </logic:equal> 
                     </logic:notEqual>
                     <input type="hidden" id="tthai_<bean:write name="index"/>" value="<bean:write name="UDlist" property="trang_thai"/>" />
                   </td>
                   <td align="center">
                   <logic:equal name="UDlist" property="trang_thai_kq" value="02">                  
                        <logic:equal name="UDlist" property="tthai_ttin" value="01">
                           &#272;&#227; g&#7917;i NH
                        </logic:equal>
                        <logic:equal name="UDlist" property="tthai_ttin" value="02">
                          NH nh&#7853;n th&#224;nh c&#244;ng
                        </logic:equal>
                        <logic:equal name="UDlist" property="tthai_ttin" value="03">
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
       <td>
        <fieldset>
        <legend><font color="Blue">T&#7893;ng h&#7907;p b&#7843;ng k&#234;</font></legend>
         <div style="HEIGHT: 185px; WIDTH: 100%; OVERFLOW-Y: scroll">
          <html:hidden property="rowSelected" styleId="rowSelected"/>
          <table width="100%" cellspacing="0" cellpadding="1" 
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse;">
             <logic:empty name="colThop">
                <td colspan="4">
                  <font color="red">Kh&#244;ng c&#243; th&#244;ng tin b&#7843;ng k&#234;</font>
                  <html:hidden property="trang_thai" value="" styleId="trang_thai_bk"/>
                  <html:hidden property="trang_thai_kq" value="" styleId="trang_thai_kq"/>
                </td>
             </logic:empty>
             <logic:notEmpty name="colThop">
             <thead>
              <tr>
                <th style="width:20%">Mã NT</th>
                <th style="width:40%">Tổng thu điện tử</th>
                <th style="width:40%">Tổng chi điện tử</th>
              </tr>
             </thead>
             <logic:iterate id="UDlist" name="colThop" indexId="index">
             <tbody>
              <tr>
                <th align="center"><bean:write name="UDlist" property="loai_tien" /></th>
                <logic:equal value="VND" name="UDlist" property="loai_tien">
                <fmt:setLocale value="vi_VI"/>
                <td align="right">
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                    <bean:write name="UDlist" property="so_tien_thu_dtu" />
                  </fmt:formatNumber>
                </td>
                <td align="right">
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                    <bean:write name="UDlist" property="sotien_dtu" />
                  </fmt:formatNumber>
                </td>
                </logic:equal>
                <logic:notEqual value="VND" name="UDlist" property="loai_tien">
                <fmt:setLocale value="en_US"/>
                  <td align="right">
                  <fmt:formatNumber type="currency" currencySymbol="">
                    <bean:write name="UDlist" property="sotien_thu" />
                  </fmt:formatNumber>
                  </td>
                  <td align="right">
                    <fmt:formatNumber type="currency" currencySymbol="">
                      <bean:write name="UDlist" property="sotien_chi" />
                    </fmt:formatNumber>
                  </td>
                </logic:notEqual> 
              </tr>
              <input type="hidden" name="mt_id" value='<bean:write name="UDlist" property="mt_id" />' />
              <html:hidden property="id" name="UDlist"/>
              <html:hidden property="send_bank" name="UDlist"/>
              <html:hidden property="ngay_dc" name="UDlist" styleId="ngay_dc"/>
              <html:hidden property="trang_thai" name="UDlist" styleId="trang_thai_bk"/>
             <!--  <html:hidden property="trang_thai_kq" name="UDlist" styleId="trang_thai_kq"/> -->
             </tbody>
             </logic:iterate>
             </logic:notEmpty>
         </table>
         </div>
        </fieldset>
       </td>
      </tr>
      <tr>
      <td align="center" colspan="2">
        <%if(request.getAttribute("view") ==null || ""==request.getAttribute("view")){%>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <button type="button" onclick="check('dchieu')" accesskey="c" style="width:170px;margin-top:5px" id="bt_dchieu">
          Đối <span class="sortKey">c</span>hi&#7871;u
        </button>
        <%}%>
      </td>
    </tr>
    <tr>
      <td>
        <%
            if(request.getAttribute("err") != null){
            String StrStatus = request.getAttribute("err").toString();
         %>
            <font color="Red" dir="ltr">
                &nbsp;&nbsp;<%=StrStatus%>
            </font>
        <%}else if(request.getAttribute("chuadc") != null){
         %>
            <font color="Red" dir="ltr">
            <fmt:message key="doi_chieu.page.lable.chuadc"/>
            </font>
        <%}else if(request.getAttribute("DXN_dung") != null){
         %>
            <font color="Red" dir="ltr">
            <fmt:message key="doi_chieu.page.lable.dxndung"/>
            </font>
        <%}else if(request.getAttribute("DXN_clech") != null){
         %>
            <font color="Red" dir="ltr">
            <fmt:message key="doi_chieu.page.lable.dxnlech"/>
            </font>
        <%}else if(request.getAttribute("createF") != null){
         %>
            <font color="Red" dir="ltr">
            <fmt:message key="doi_chieu.page.lable.dxnloi"/>
            </font>
        <%}else if(request.getAttribute("kocobangke") != null){
         %>
            <font color="Red" dir="ltr">
            <fmt:message key="doi_chieu.page.lable.kocobke"/>
            </font>
        <%}%>
      </td>
    </tr>
    <tr>
      <td colspan="2">
      <fieldset>
        <legend><font color="Blue">T&#7893;ng h&#7907;p k&#7871;t qu&#7843; &#273;&#7889;i chi&#7871;u</font></legend>
         <div>
           <table width="80%" cellspacing="0" cellpadding="2"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
                 <logic:empty name="colKQDC">
             <tr>
               <td width="23%">&nbsp;&nbsp;&nbsp;<fmt:message key="doi_chieu.page.lable.ttbke"/></td>
               <td width="20%">
                  <input type="text" name="trang_thai"  class="fieldText" value="" readonly="true"/>
                  <input type="hidden" id="kq_0" value="20" />
               </td>
               <!--<td width="25%" align="center"><fmt:message key="doi_chieu.page.lable.ttdxn"/></td>
               <td  width="22%">
                  <input type="text" style="width:99%" name="trang_thai" class="fieldText" value="" readonly="true"/>
               </td>-->
               <td>&nbsp;&nbsp;&nbsp;Lý do chênh lệch</td>
               <td><input type="text" name="ly_do" class="fieldText" id="ly_do" onmouseover="show_tool_tip()"/></td>
             </tr>
              <tr>
                 <!--<td align="left">-<fmt:message key="doi_chieu.page.lable.sdukb"/></td>
                 <td width="20%" align="right"><input type="text" name="sodu_kbnn" class="fieldTextRight" value="" readonly="true"/></td>-->
                 <td align="center" width="25%">-<fmt:message key="doi_chieu.page.lable.lttthieu"/></td>
                 <td align="center" width="25%"><input type="text" name="ltt_kb_thieu" class="fieldTextCenter" value="" readonly="true"/></td>
                 <td align="center" width="25%">-<fmt:message key="doi_chieu.page.lable.dtsthieu"/></td>
                 <td align="center" width="25%"><input type="text"  style="width:99%"  name="dts_kb_thieu" class="fieldTextCenter" value="" readonly="true"/></td>
              </tr> 
              <tr>
                 <!--<td align="left"><fmt:message key="doi_chieu.page.lable.clech"/></td>
                 <td width="20%" align="right" style="fieldNumber">
                  <input type="text" name="chenh_lech" class="fieldTextRight" style="color:red;FONT-WEIGHT: bold" value="" readonly="true"/></td>-->
                 <td align="center">-<fmt:message key="doi_chieu.page.lable.lttthua"/></td>
                 <td align="center"><input type="text" name="ltt_kb_thua" class="fieldTextCenter" value="" readonly="true"/></td>
                 <td align="center">-<fmt:message key="doi_chieu.page.lable.dtsthua"/></td>
                 <td align="center"><input type="text" style="width:99%"  name="dts_kb_thua" class="fieldTextCenter" value="" readonly="true"/></td>
              </tr>
             </logic:empty>
          <logic:notEmpty name="colKQDC">
          <logic:iterate id="colKQDC" name="colKQDC" indexId="stt">
               <tr>
               <td width="23%">&nbsp;&nbsp;&nbsp;<fmt:message key="doi_chieu.page.lable.ttbke"/></td>
               <td width="20%">
                  <logic:equal name="colKQDC" property="ket_qua" value="0">
                    <input type="text" name="ket_qua" style="color:blue;FONT-WEIGHT: bold"  class="fieldText" value=" <fmt:message key="doi_chieu.page.lable.dc1.0"/>" readonly="true"/>
                  </logic:equal>
                  <logic:equal name="colKQDC" property="ket_qua" value="1">
                    <input type="text" name="ket_qua" style="color:red;FONT-WEIGHT: bold"  class="fieldText" value=" <fmt:message key="doi_chieu.page.lable.dc1.1"/>" readonly="true"/>
                  </logic:equal>
                  <logic:equal name="colKQDC" property="ket_qua" value="2">
                    <input type="text" name="ket_qua" style="color:red;FONT-WEIGHT: bold"  class="fieldText" value=" <fmt:message key="doi_chieu.page.lable.dc1.2"/>" readonly="true"/>
                  </logic:equal>
                <input type="hidden" id="kq_0" value="<bean:write name="colKQDC" property="ket_qua"/>" />
               </td>
               <td>&nbsp;&nbsp;&nbsp;Lý do chênh lệch</td>
               <td><input type="text" name="ly_do" class="fieldText" id="ly_do" value="<bean:write name="colKQDC" property="ly_do_chenh_lech"/>" onmouseover="show_tool_tip()" readonly="true"/></td>        
             </tr>
              <tr>
                 <!--<td align="left">-<fmt:message key="doi_chieu.page.lable.sdukb"/></td>
                 <td width="20%" align="right"><input type="text" name="sodu_kbnn" style="FONT-WEIGHT: bold" class="fieldTextRight" value="<bean:write name="colKQDC" property="sodu_kbnn" format="#,###.##"/>" readonly="true"/></td>-->
                 <td align="center" width="25%">-<fmt:message key="doi_chieu.page.lable.lttthieu"/></td>
                 <td align="center" width="25%"><input type="text" name="ltt_kb_thieu" style="FONT-WEIGHT: bold; width:99%" class="fieldTextRight" value="<bean:write name="colKQDC" property="ltt_kb_thieu"/>" readonly="true"/></td>
                 <td align="center" width="25%">-<fmt:message key="doi_chieu.page.lable.dtsthieu"/></td>
                 <td align="center" width="25%"><input type="text" name="dts_kb_thieu" style="FONT-WEIGHT: bold; width:99%" class="fieldTextRight" value="<bean:write name="colKQDC" property="dts_kb_thieu"/>" readonly="true"/></td>
              </tr> 
              <tr>
                 <!--<td align="left"><fmt:message key="doi_chieu.page.lable.clech"/></td>
                 <td width="20%" align="right" style="fieldNumber">
                  <input type="text" name="chenh_lech" class="fieldTextRight" style="color:red;FONT-WEIGHT: bold" value="<bean:write name="colKQDC" property="chenh_lech" format="#,###.##"/>" readonly="true"/></td>-->
                 <td align="center">-<fmt:message key="doi_chieu.page.lable.lttthua"/></td>
                 <td  align="center"><input type="text" name="ltt_kb_thua" style="FONT-WEIGHT: bold; width:99%" class="fieldTextRight" value="<bean:write name="colKQDC" property="ltt_kb_thua"/>" readonly="true"/></td>
                 <td align="center">-<fmt:message key="doi_chieu.page.lable.dtsthua"/></td>
                 <td align="center"><input type="text" name="dts_kb_thua" style="FONT-WEIGHT: bold; width:99%" class="fieldTextRight" value="<bean:write name="colKQDC" property="dts_kb_thua"/>" readonly="true"/></td>
              </tr>
              </logic:iterate>
              </logic:notEmpty>
         </table>
         </div>
        </fieldset>   
      </td>
    </tr>    
    <tr>
    <td colspan="2">
      <fieldset>
        <legend><font color="Blue"><fmt:message key="doi_chieu.page.ketqua"/></font></legend>
        <div class="tabber" id="mytabber1" >
        <div class="tabbertab" style="height:150px;overflow-y: scroll;"><h2>L&#7879;nh thanh to&#225;n</h2>           
          <table width="100%" cellspacing="0" cellpadding="1" 
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
              <tr>
              <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.lable.ltt"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.lable.ngaytnhan"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                        <fmt:message key="doi_chieu.page.lable.nhkbchuyen"/>
                    </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.tennhkbchuyen"/>
                    </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.lable.nhkbnhan"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.lable.tennhkbnhan"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    <fmt:message key="doi_chieu.page.lable.sotchieu"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    <fmt:message key="doi_chieu.page.lable.stien"/>
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Loại tiền
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    <fmt:message key="doi_chieu.page.lable.tthai"/>
                  </div>
                </th>
                <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Lý do chênh lệch
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Chi tiết chênh lệch
                  </div>
                </th>
                <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
              </tr>
              
           <logic:notEmpty name="colKQDCCT">
           <tr>
              <td colspan="10"><b>L&#7879;nh thanh to&#225;n &#273;i</b></td>
              </tr>
          <logic:iterate id="items" name="colKQDCCT" indexId="stt">
          <logic:equal name="items" property="di_den" value="DI" > <!-- lenh thanh toan di -->
            <logic:equal name="items" property="ltt_dts" value="LTT" > <!-- lenh thanh toan di -->

              <tr id="row_dts_<bean:write name="stt"/>" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                 <td align="center">
                   <bean:write name="items" property="mt_id"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="send_date"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="send_bank"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="ten_send_bank"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="receive_bank"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="ten"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="f20"/>
                </td>
                <td  align="right">
                  <logic:equal value="VND" name="items" property="loai_tien">
                    <fmt:setLocale value="vi_VI"/>
                    <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="f32as3"/>
                    </fmt:formatNumber>
                  </logic:equal>
                  <logic:notEqual value="VND" name="items" property="loai_tien">
                    <fmt:setLocale value="en_US"/>
                    <fmt:formatNumber type="currency"  currencySymbol="">
                      <bean:write name="items" property="f32as3"/>
                    </fmt:formatNumber>
                  </logic:notEqual>
                </td>
                <td align="center">
                  <bean:write name="items" property="loai_tien"/>
                </td>
                <td align="center">
                  <logic:equal value="0" property="trang_thai" name="items">
                       <fmt:message key="doi_chieu.page.lable.tthai.0"/>
                    </logic:equal>
                    <logic:equal value="1" property="trang_thai" name="items">
                        <fmt:message key="doi_chieu.page.lable.tthai.1"/>
                    </logic:equal>
                </td>
                <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                <td align="left">                
                  <bean:write name="items" property="ldo_clech"/>
                </td>
                <td align="left">
                  <bean:write name="items" property="ctiet_clech"/>
                </td>
                <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
              </tr> 
              
             </logic:equal>
             </logic:equal>
             </logic:iterate>
             <tr><td colspan="10">&nbsp;</td></tr>
             </logic:notEmpty>             
             <logic:notEmpty name="colKQDCCT">
             <tr> 
              <td colspan="10"><b>L&#7879;nh thanh to&#225;n &#273;&#7871;n</b></td>
              </tr>
              <logic:iterate id="items" name="colKQDCCT" indexId="stt">
              <logic:equal name="items" property="di_den" value="DEN" > <!-- lenh thanh toan -->
              <logic:equal name="items" property="ltt_dts" value="LTT" ><!-- lenh thanh toan di -->
                <tr id="row_dts_<bean:write name="stt"/>" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                 <td align="center">
                    <bean:write name="items" property="mt_id"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ngay_ct"/>
                  </td>           
                  <td align="center">
                    <bean:write name="items" property="receive_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="send_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten_send_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="f20"/>
                  </td>
                  <td align="right">
                    <logic:equal value="VND" name="items" property="loai_tien">
                      <fmt:setLocale value="vi_VI"/>
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="f32as3"/>
                      </fmt:formatNumber>
                    </logic:equal>
                    <logic:notEqual value="VND" name="items" property="loai_tien">
                      <fmt:setLocale value="en_US"/>
                      <fmt:formatNumber maxFractionDigits="2"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="f32as3"/>
                      </fmt:formatNumber>
                    </logic:notEqual>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="loai_tien"/>
                  </td>
                  <td align="center">
                    <logic:equal value="0" property="trang_thai" name="items">
                       <fmt:message key="doi_chieu.page.lable.tthai.0"/>
                    </logic:equal>
                    <logic:equal value="1" property="trang_thai" name="items">
                        <fmt:message key="doi_chieu.page.lable.tthai.1"/>
                    </logic:equal>
                  </td>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                <td align="left">                
                  <bean:write name="items" property="ldo_clech"/>
                </td>
                <td align="left">
                  <bean:write name="items" property="ctiet_clech"/>
                </td>
                <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
                </tr>  
            </logic:equal>
            </logic:equal>
          </logic:iterate>
          <tr><td colspan="10">&nbsp;</td></tr>
          </logic:notEmpty>
          
         </table>              
         </div>
         <div class="tabbertab" style="height:150px;overflow-y: scroll;">
          <h2>&#272;i&#7879;n tra so&#225;t</h2>
          <table width="100%" cellspacing="0" cellpadding="1" 
                   bordercolor="#e1e1e1" border="1" align="center"
                   style="BORDER-COLLAPSE: collapse">
                <tr>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.sdts"/>
                    </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.ngayts"/>
                    </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                          <fmt:message key="doi_chieu.page.lable.dvts"/>
                      </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                        <fmt:message key="doi_chieu.page.lable.tennhkbchuyen"/>
                      </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.dvnts"/>
                    </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.tennhkbnhan"/>
                    </div>
                  </th>

                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      <fmt:message key="doi_chieu.page.lable.loaits"/>
                    </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">          
                      <fmt:message key="doi_chieu.page.lable.lttts"/>
                    </div>
                  </th>
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">          
                      <fmt:message key="doi_chieu.page.lable.tthai"/>
                    </div>
                  </th>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                  <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">          
                      Lý do chênh lệch
                    </div>
                  </th>
                   <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">          
                      Chi tiết chênh lệch
                    </div>
                  </th>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
                </tr>
               
          <logic:notEmpty name="colKQDCCT">
           <tr>
              <td colspan="9"><b>&#272;i&#7879;n tra so&#225;t &#273;i</b></td>
              </tr>
            <logic:iterate id="items" name="colKQDCCT" indexId="stt">
            <logic:equal name="items" property="di_den" value="DI" > 
            <logic:equal name="items" property="ltt_dts" value="DTS" >
            
                <tr id="row_dts_<bean:write name="stt"/>" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center">
                    <bean:write name="items" property="mt_id"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ngay_ts"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="send_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten_send_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="receive_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="mt_type"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="f21"/>
                  </td>
                  <td align="center">
                    <logic:equal value="0" property="trang_thai" name="items">
                       <fmt:message key="doi_chieu.page.lable.tthai.0"/>
                    </logic:equal>
                    <logic:equal value="1" property="trang_thai" name="items">
                        <fmt:message key="doi_chieu.page.lable.tthai.1"/>
                    </logic:equal>
                  </td>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                  <td align="left">                
                    <bean:write name="items" property="ldo_clech"/>
                  </td>
                  <td align="left">
                    <bean:write name="items" property="ctiet_clech"/>
                  </td>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
                </tr>  
                </logic:equal>
        </logic:equal>
        </logic:iterate>
        <tr><td colspan="9">&nbsp;</td></tr>
        </logic:notEmpty>
            
             
          <logic:notEmpty name="colKQDCCT">
          <tr> 
              <td colspan="9"><b>&#272;i&#7879;n tra so&#225;t &#273;&#7871;n</b></td>
              </tr>
            <logic:iterate id="items" name="colKQDCCT" indexId="stt">
            <logic:equal name="items" property="di_den" value="DEN" > 
            <logic:equal name="items" property="ltt_dts" value="DTS" > 
            
                <tr id="row_dts_<bean:write name="stt"/>" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center">
                    <bean:write name="items" property="mt_id"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ngay_ts"/>
                  </td>
                 <td align="center">
                    <bean:write name="items" property="receive_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="send_bank"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="ten_send_bank"/>
                  </td>

                  <td align="center">
                    <bean:write name="items" property="mt_type"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="f21"/>
                  </td>
                  <td align="center">
                    <logic:equal value="0" property="trang_thai" name="items">
                       <fmt:message key="doi_chieu.page.lable.tthai.0"/>
                    </logic:equal>
                    <logic:equal value="1" property="trang_thai" name="items">
                        <fmt:message key="doi_chieu.page.lable.tthai.1"/>
                    </logic:equal>
                  </td>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech start-->
                  <td align="left">                
                    <bean:write name="items" property="ldo_clech"/>
                  </td>
                  <td align="left">
                    <bean:write name="items" property="ctiet_clech"/>
                  </td>
                  <!--20171009 thuongdt bo sung chi tiet chenh lech end-->
                </tr> 
                </logic:equal>
        </logic:equal>
        </logic:iterate>
        <tr><td colspan="9">&nbsp;</td></tr>
        </logic:notEmpty>
        
        </table>
           </div>
        </div>
      </fieldset>
    </td>
  </tr>
  <tr>
  
   <td align="right" colspan="2">
      <logic:notEmpty name="colKQDC">
        <button type="button" onclick="check('create')" accesskey="t" id="bt">
          <span class="sortKey">T</span>&#7841;o &#273;i&#7879;n X&#225;c nh&#7853;n
        </button>
      </logic:notEmpty> 
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       <button  type="button" onclick="check('view')" accesskey="i" id="bt_ctiet">
          Ch<span class="sortKey">i</span> ti&#7871;t b&#7843;ng k&#234;
       </button>
       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
       <button  type="button" onclick="check('close')" accesskey="o">
          Th<span class="sortKey">o</span>&#225;t
       </button>
    </td>
  </tr>
</table>
    
  </html:form>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
jQuery(document).init(function () {
//alert(jQuery("#sodu_daungay").val());
//alert(jQuery("#sotien_dtu").val());
jQuery("#sodu_daungay").val(toFormatNumber(jQuery("#sodu_daungay").val(),0,'.'));
jQuery("#so_du").val(toFormatNumber(jQuery("#so_du").val(),0,'.'));
jQuery("#sotien_thu").val(toFormatNumber(jQuery("#sotien_thu").val(),0,'.'));
jQuery("#so_tien_thu_tcong").val(toFormatNumber(jQuery("#so_tien_thu_tcong").val(),0,'.'));
jQuery("#so_tien_thu_dtu").val(toFormatNumber(jQuery("#so_tien_thu_dtu").val(),0,'.'));
jQuery("#sotien_chi").val(toFormatNumber(jQuery("#sotien_chi").val(),0,'.'));
jQuery("#sotien_tcong").val(toFormatNumber(jQuery("#sotien_tcong").val(),0,'.'));
jQuery("#sotien_dtu").val(toFormatNumber(jQuery("#sotien_dtu").val(),0,'.'));

  //defaultStateFormBK();
  
  var strRowSelected="<%=strRowSelected%>";
    if ('row_qt_0'==strRowSelected ){
      var tthai= document.getElementById('trang_thai_bk').value;
      var tthai_kq= document.getElementById('trang_thai_kq_0').value;
      bton(tthai,tthai_kq);
    }else{
      var str=strRowSelected;
      var num = str.replace(/[^0-9]/g,'');
      tthai_kq1_id='trang_thai_kq_'+num;
      var tthai1= document.getElementById('trang_thai_bk').value;
      var tthai_kq1= document.getElementById(tthai_kq1_id).value;
//      alert('---------'+tthai1);
//      alert('+++++++++++'+tthai_kq1);
       bton(tthai1,tthai_kq1);
    }

});

//check();
//Function check loai button
function check(type) { 
     var strRowSelected="<%=strRowSelected%>";
     var f = document.forms[0];
     
     if (type == 'dchieu') { 
          var id= f.id.value;
          var row = f.rowSelected.value;
          document.getElementById("bt_dchieu").disabled=true;
          //document.getElementById("bt").disabled=true;
          f.action = 'THienDChieuNgoaiTeAction.do?id='+ id + "&row=" + row;  
     }
     if (type == 'create') {
        stt = parseInt(strRowSelected.substr(7,5))
        mt_id = document.getElementById("kqidTmp_"+stt).value;
        document.getElementById("bt").disabled=true;
        f.action = 'TaoDTSNgoaiTeAction.do?mt_id='+mt_id;
     }
     if (type == 'view') {
        if(strRowSelected!=null && '' != strRowSelected){
            stt= strRowSelected.substr(7,5);
            sttNext=parseInt(stt);
            bk_id=document.getElementById("idTmp_"+sttNext).value;
            f.action = 'CTietBKeDChieuNgoaiTe.do?type=bk&bk_id='+bk_id;
        }
     }
     if (type == 'close') {
        f.action = 'mainAction.do';          
     } 
     f.submit();
}
//function kiem soat trang thai cho button doi chieu
  function bton(trang_thai_bk,trang_thai_kq){
     // if (trang_thai_bk=='02'){
     //   document.getElementById("bt_dchieu").disabled=true;
     // }
       if(trang_thai_bk=='01'|| trang_thai_bk=='02'){
          if(document.getElementById("bt") != null){
              document.getElementById("bt").disabled=true;
          document.getElementById("bt_dchieu").disabled=true;
          }
          if (trang_thai_kq=='00'){
            document.getElementById("bt").disabled=false;
          document.getElementById("bt_dchieu").disabled=false;
          }
       }else if (trang_thai_bk=='00'){
          if(trang_thai_kq=='01' || trang_thai_kq=='02'){
              document.getElementById("bt").disabled=true;
              document.getElementById("bt_dchieu").disabled=true;
          }else if(trang_thai_kq=='00' || trang_thai_kq=='04'){
             // document.getElementById("bt").disabled=false;
              document.getElementById("bt_dchieu").disabled=false;
          }
          var strRowSelected="<%=strRowSelected%>";
          if(strRowSelected!=null&&''!=strRowSelected){
              stt= strRowSelected.substr(7,5);
              sttNext=parseInt(stt);
              kq_id=jQuery("#kqidTmp_"+sttNext).val();          
              
              if(kq_id !=null && ''!=kq_id){
//                document.getElementById("bt").disabled=false;
              }else{
//                document.getElementById("bt").disabled=true;
              }
          }
       }else if (trang_thai_bk==null||""==trang_thai_bk){
//          document.getElementById("bt").disabled=true;
          document.getElementById("bt_ctiet").disabled=true;
          document.getElementById("bt_dchieu").disabled=true;
       }
  }
 
</script>
