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
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
  String strRowSelected = request.getAttribute("rowSelected")==null?"":request.getAttribute("rowSelected").toString();
  String pht_ttsp = request.getAttribute("pht_ttsp")==null?"":request.getAttribute("pht_ttsp").toString();
  String tcong = request.getAttribute("duyettcong")==null?"":request.getAttribute("duyettcong").toString();
  String dung = request.getAttribute("dung")==null?"":request.getAttribute("dung").toString();
  String lech = request.getAttribute("lech")==null?"":request.getAttribute("lech").toString();
  String notTTSP = request.getAttribute("notTTSP")==null?"":request.getAttribute("notTTSP").toString();
  String chenh_cot = request.getAttribute("chenh_cot")==null?"0":request.getAttribute("chenh_cot").toString();
  String chkdate = request.getAttribute("chkdate")==null?"0":request.getAttribute("chkdate").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    //defaultStateFormBK();
    var strRowSelected="<%=strRowSelected%>";
    var dung="<%=dung%>";
    var lech="<%=lech%>";
    
    var chenh_cot="<%=chenh_cot%>";
//    alert(chenh_cot);
    if(strRowSelected!=null && '' != strRowSelected){
    rowSelectedFocusXNDC(strRowSelected);
    }
  if(dung!=null && '' != dung){
      alert(GetUnicode('T&#7841;o &#273;i&#7879;n x&#225;c nh&#7853;n kh&#7899;p &#273;&#250;ng th&#224;nh c&#244;ng'));
    }
  if(lech!=null && '' != lech){
      alert(GetUnicode('T&#7841;o &#273;i&#7879;n x&#225;c nh&#7853;n ch&#234;nh l&#7879;ch th&#224;nh c&#244;ng'));
    }
    jQuery(document).ready(function () {
          jQuery("#dialog-confirm").dialog( {
          autoOpen: false,
                    resizable: false,
                    height:200,
                    width:380,
                    modal: true,
                    buttons:{
                "Có" : function () {    
                    document.forms[0].action = "TaoDTSChenhLechCOTAction.do?rowSelected="+strRowSelected;
                    document.forms[0].submit();   
                    jQuery(this).dialog("close");
                },
                "Không" : function () {    
                    // thuc hien update trang thai
                    jQuery(this).dialog("close");
                }
            }
        });
         jQuery("#bt").click(function () {
            var kq_pht= document.getElementById("b").value;
            var kq_ttsp = document.getElementById("a").value;
              if(strRowSelected!=null && '' != strRowSelected){
                    stt= strRowSelected.substr(7,5);
                    sttNext=parseInt(stt);
                    tthai_kq=document.getElementById("tthaikq_"+sttNext).value;
                    ngay_dc=document.getElementById("ngaydc_"+sttNext).value;
                    if( kq_ttsp !='0'){
                          alert(GetUnicode('Chưa nhận được kết quả khớp đúng từ TTSP'));
                            return false;
                    }else if( kq_pht!='0'){
                          alert(GetUnicode('Chưa nhận được kết quả khớp đúng từ PHT'));
                            return false;
                    }else if ( kq_ttsp =='0' && kq_pht=='0'){
                        if(chenh_cot==0 || chenh_cot =='0'){
                          f.action = 'TaoDXNDCTHop1_PHTAction.do?rowSelected='+strRowSelected+"&ngay_dc="+ngay_dc;
                          f.submit();
                        }
                      }
              }
             });
          
          if(chenh_cot!=0 && chenh_cot !='0'){
                    jQuery("#dialog-confirm").html('C&#243; ch&#234;nh l&#7879;ch s&#7889; d&#432; Cut-off-time: '+ toFormatNumber("<%=chenh_cot%>",0,',') +'. B&#7841;n c&#243; mu&#7889;n t&#7841;o &#272;XN ch&#234;nh l&#7879;ch kh&#244;ng? ');
                    jQuery("#dialog-confirm").dialog("open");
            }

//      }

//    }else if((thu_kbnn!='0' && thu_kbnn!=0)||(chi_kbnn!='0' && chi_kbnn!=0)){ // co chenh lech ve so du thu cong
////      
//       jQuery("#bt").click(function () {
//          document.forms[0].action = "TaoDXNDCTHop1Action.do";
//       document.forms[0].submit();
 });
  }); 
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="XNDCTHop1_PHTAction.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="doi_chieu.page.title.xndcthop"/></span>
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
        <legend><font color="Blue">Danh s&#225;ch x&#225;c nh&#7853;n &#272;XN t&#7893;ng h&#7907;p </font></legend>
        <div  style="height:300px;overflow-y: scroll;">
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="1"
                                             cellspacing="0" cellpadding="2" >
                 <tr>
                 <td align="center" style="width:10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.ngaydc"/></td>
                 <td align="center" style="width:10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.duyet.manh"/></td>
                 <!--<td align="center" width="17%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.nhang"/></td>-->
                 <td align="center" style="width:35%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.sdxn"/></td>
                 <td align="center" style="width:30%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai"/></td>
                 <td align="center" style="width:15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai_ttin"/></td>
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
                      id="row_qt_<bean:write name="index"/>"
                      onclick="rowSelectedFocusXNDC('row_qt_<bean:write name="index"/>');
                               fillDataXNDC('XNDCTHop1_PHTAction.do','<bean:write name="UDlist" property="ngay_dc"/>','<bean:write name="UDlist" property="receive_bank"/>','<bean:write name="UDlist" property="ttsp_id"/>','<bean:write name="UDlist" property="pht_id"/>','row_qt_<bean:write name="index"/>');">
                   <td align="center">
                    <bean:write name="UDlist" property="ngay_dc"/>                    
                   </td>
                   <td align="center">
                    <bean:write name="UDlist" property="receive_bank"/>            
                   </td>
                  <td align="center" id="td_qt_<bean:write name="index"/>">
                    <bean:write name="UDlist" property="pht_id"/> 
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
                   <logic:equal name="UDlist" property="tthai_dxn_thop" value="">
                      <fmt:message key="doi_chieu.page.lable.00"/>
                   </logic:equal>
                   <logic:equal name="UDlist" property="tthai_dxn_thop" value="00">
                      <fmt:message key="doi_chieu.page.lable.00"/>
                   </logic:equal>
                   <logic:equal name="UDlist" property="tthai_dxn_thop" value="01">
                      <fmt:message key="doi_chieu.page.lable.duyet.01"/>
                   </logic:equal>
                   <logic:equal name="UDlist" property="tthai_dxn_thop" value="02">
                      <fmt:message key="doi_chieu.page.lable.duyet.02"/>
                   </logic:equal>                
                   <input type="hidden" name="tthai_<bean:write name="index"/>" id="tthai_<bean:write name="index"/>" value="<bean:write name="UDlist" property="tthai_dxn_thop"/>" />
                   <input type="hidden" name="tthaikq_<bean:write name="index"/>" id="tthaikq_<bean:write name="index"/>" value="<bean:write name="UDlist" property="trang_thai"/>" />
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
       <td  width="58%">
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
    <td>
    <%
            if(request.getAttribute("err") != null){
            String StrStatus = request.getAttribute("err").toString();
         %>
            <font color="Red" dir="ltr">
                &nbsp;&nbsp;<%=StrStatus%>
            </font>
        <%}%>
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
                                  <fmt:message key="doi_chieu.page.lable.duyet.cutoffnh"/>
                                    
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
                                    <fmt:message key="doi_chieu.page.lable.duyet.cutoffkbnn"/>
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
    <tr > 
      <td align="right" colspan="5">
          <button type="button" onclick="check('create')"  accesskey="t" id="bt">
            <span class="sortKey">T</span>&#7841;o &#273;i&#7879;n X&#225;c nh&#7853;n
          </button>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <button type="button" onclick="check('print','')"  accesskey="i" id="inbt">
            <span class="sortKey">I</span>n
          </button>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          <button  type="button" onclick="check('close','')" accesskey="o">
            Th<span class="sortKey">o</span>&#225;t
          </button>
      </td>
    </tr>
</table>
    
  </html:form>
    <div id="dialog-confirm"
     title='<fmt:message key="doi_chieu.page.title.dialog_confirm"/>'>
     
  <p class="validateTips">
  </p>
</div>
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
      tthai_kq=document.getElementById("tthaikq_"+sttNext).value;
      if(tthai_dxn=='01'|| tthai_dxn=='02'){
          document.getElementById("bt").disabled=true;
      }else if (tthai_dxn=='00'){
          document.getElementById("bt").disabled=false;
      }
//      else {
//          document.getElementById("bt").disabled=true;
//          document.getElementById("inbt").disabled=true;
//        }
    }
    else{
      document.getElementById("bt").disabled=true;
      document.getElementById("inbt").disabled=true;
    }
  }
  function check(type,bk_id) {  
   
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

</script>

