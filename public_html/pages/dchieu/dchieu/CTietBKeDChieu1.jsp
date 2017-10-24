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
   String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
   String type_dc1 = request.getAttribute("type")==null?"":request.getAttribute("type").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
     
  });
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="DChieu1Action.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="doi_chieu.page.title.ctietbk1"/></span>
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
       <td>
        <fieldset>
        <legend><font color="Blue">T&#7893;ng h&#7907;p b&#7843;ng k&#234;</font></legend>
         <div style="height:100px;">  
          <table width="80%" cellspacing="0" cellpadding="1"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
              <logic:empty name="colThop">
                <td colspan="4">
                  <font color="red">Kh&#244;ng c&#243; th&#244;ng tin b&#7843;ng k&#234;</font>                
                </td>
              </logic:empty>
             <logic:notEmpty name="colThop">
             <logic:iterate id="UDlist" name="colThop" indexId="index">
              <tr>
               <td width="25%">-<fmt:message key="doi_chieu.page.lable.sdudngay"/></td>
               <td width="25%">
                 <html:text property="sodu_daungay" name="UDlist" style="border:0px;" 
                              styleId="sodu_daungay" styleClass="fieldTextRight" 
                              maxlength="20" readonly="true"/>
                  
                </td>
               <td width="25%">-<fmt:message key="doi_chieu.page.lable.sducutoff"/></td>
               <td width="25%">
               <html:text property="so_du" maxlength="20" name="UDlist"
                                   style="border:0px;" styleClass="fieldTextRight" 
                                   styleId="so_du" readonly="true" /></td>
             </tr>
             <tr>
               <td>-<fmt:message key="doi_chieu.page.lable.dlthu"/></td>
               <td><html:text property="sotien_thu"   name="UDlist"
                                   style="border:0px;" styleClass="fieldTextRight" maxlength="20"  
                                   styleId="sotien_thu" readonly="true"/>
                </td>
                <td>-<fmt:message key="doi_chieu.page.lable.dlchi"/></td>
               <td ><html:text property="sotien_chi"   name="UDlist"
                                     style="border:0px;" styleClass="fieldTextRight" maxlength="20"
                                   styleId="sotien_chi" readonly="true"/></td>   
              </tr>
              <tr>
               <td>&nbsp;&nbsp; +<fmt:message key="doi_chieu.page.lable.tcong"/></td>
               <td> <!--<logic:notEqual value="0" property="so_tien_thu_tcong"  name="UDlist">
                    <html:text property="so_tien_thu_tcong"  name="UDlist" 
                                       style="border:0px;" styleClass="fieldTextRight" maxlength="20"
                                       styleId="so_tien_thu_tcong" readonly="true"/>
                     </logic:notEqual>   -->            
                  </td>
                <td>&nbsp;&nbsp;+<fmt:message key="doi_chieu.page.lable.tcong"/></td>
               <td>
               <!--<logic:notEqual value="0" property="sotien_tcong"  name="UDlist">
                <html:text property="sotien_tcong"   name="UDlist" 
                                   style="border:0px;" styleClass="fieldTextRight" maxlength="20"
                                   styleId="sotien_tcong" readonly="true"/>
                    </logic:notEqual> -->
                </td>
                </tr>
              <tr>
               <td>&nbsp;&nbsp; +<fmt:message key="doi_chieu.page.lable.dtu"/></td>
               <td><html:text property="so_tien_thu_dtu"   name="UDlist"
                                     style="border:0px;" styleClass="fieldTextRight" maxlength="20"
                                   styleId="so_tien_thu_dtu" readonly="true"/></td>              
               <td>&nbsp;&nbsp;+<fmt:message key="doi_chieu.page.lable.dtu"/></td>
               <td><html:text property="sotien_dtu"  name="UDlist" 
                                     style="border:0px;" styleClass="fieldTextRight" maxlength="20"
                                   styleId="sotien_dtu" readonly="true"/></td>
                  <html:hidden property="id" styleId="bkid" name="UDlist"/>
                  <html:hidden property="send_bank" name="UDlist"/>
                  <html:hidden property="receive_bank" name="UDlist"/>
                  <html:hidden property="lan_dc" name="UDlist"/>
                  <html:hidden property="ngay_dc" name="UDlist" styleId="ngay_dc"/>
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
        <div class="tabbertab" style="height:230px;overflow-y: scroll;"><h2>L&#7879;nh thanh to&#225;n</h2>           
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
              </tr>
              
           <logic:notEmpty name="colKQDCCT">
           <tr>
              <td colspan="9"><b>L&#7879;nh thanh to&#225;n &#273;i</b></td>
              </tr>
          <logic:iterate id="items" name="colKQDCCT" indexId="stt">
          <logic:equal name="items" property="di_den" value="DI" > <!-- lenh thanh toan di --> 
            <logic:equal name="items" property="ltt_dts" value="LTT" > <!-- lenh thanh toan di --> 

              <tr id="row_dts_<bean:write name="stt"/>" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                 <td align="center">
                   <bean:write name="items" property="mt_id"/>
                </td>
                <td align="center">
                  <bean:write name="items" property="f32as1"/>
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
                <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                  <bean:write name="items" property="f32as3"/>
                </fmt:formatNumber>
                </td>
              </tr> 
             </logic:equal>
             </logic:equal>
             </logic:iterate>
             <tr><td colspan="9">&nbsp;</td></tr>
             </logic:notEmpty>             
             <logic:notEmpty name="colKQDCCT">
             <tr> 
              <td colspan="9"><b>L&#7879;nh thanh to&#225;n &#273;&#7871;n</b></td>
              </tr>
              <logic:iterate id="items" name="colKQDCCT" indexId="stt">
              <logic:equal name="items" property="di_den" value="DEN" >  <!-- lenh thanh toan --> 
              <logic:equal name="items" property="ltt_dts" value="LTT">  <!-- lenh thanh toan di --> 
                <tr id="row_dts_<bean:write name="stt"/>" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                 <td align="center">
                    <bean:write name="items" property="mt_id"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="f32as1"/>
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
                    <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                    <bean:write name="items" property="f32as3"/>
                  </fmt:formatNumber>
                  </td>
                </tr>  
            </logic:equal>
            </logic:equal>
          </logic:iterate>
          <tr><td colspan="9">&nbsp;</td></tr>
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
                    <bean:write name="items" property="f32as1"/>
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
                    <bean:write name="items" property="f32as1"/>
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
  <tr > 
    <td align="right" colspan="2">     
        <!--<button type="button" accesskey="t" style="width:170px" id="btnInBKDC" onclick="check('print')" >
         In chi tiết bảng kê
        </button>-->
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
jQuery("#sodu_daungay").val(toFormatNumber(jQuery("#sodu_daungay").val(),0,'.'));
jQuery("#so_du").val(toFormatNumber(jQuery("#so_du").val(),0,'.'));
jQuery("#sotien_thu").val(toFormatNumber(jQuery("#sotien_thu").val(),0,'.'));
jQuery("#sotien_chi").val(toFormatNumber(jQuery("#sotien_chi").val(),0,'.'));
jQuery("#so_tien_thu_tcong").val(toFormatNumber(jQuery("#so_tien_thu_tcong").val(),0,'.'));
jQuery("#sotien_tcong").val(toFormatNumber(jQuery("#sotien_tcong").val(),0,'.'));
jQuery("#so_tien_thu_dtu").val(toFormatNumber(jQuery("#so_tien_thu_dtu").val(),0,'.'));
jQuery("#sotien_dtu").val(toFormatNumber(jQuery("#sotien_dtu").val(),0,'.'));

var bk_id= jQuery("#bkid").val();
});
 function check(type) {
var f = document.forms[0];
if (type == 'print') {
        var bk_id= jQuery("#bkid").val();
        f.action = 'printBKeDChieuAction.do?type=dc1&bk_id='+bk_id;    
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';  
        f.submit();
      }
     if (type == 'close') {
       var tcuu="<%=tcuu%>";
       var type_dc1="<%=type_dc1%>";
       var bk_id= jQuery("#bkid").val();
         if(tcuu!=null && ""!=tcuu){
            f.action = 'ViewTTinDChieuAction.do'+tcuu+'&type=bk&bk_id='+bk_id;           
         }else if (tcuu==null || ""==tcuu){
            if(type_dc1=='bk'){
            f.action = 'DChieu1Action.do';
            }else if(type_dc1=='kq'){
              f.action = 'DuyetXNDChieu1Action.do';
            }
      } 
     }
         f.submit();

}
</script>
