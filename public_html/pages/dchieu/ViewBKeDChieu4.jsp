<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/tabber.css"/>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabber.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/DChieu3.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.js"></script>
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
  String strRowSelected = request.getAttribute("rowSelected")==null?"":request.getAttribute("rowSelected").toString();
   String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    var strRowSelected="<%=strRowSelected%>";
    if(strRowSelected!=null && '' != strRowSelected){
    rowSelectedFocusXNDC(strRowSelected);
    }
  });
  
</script>
<div class="app_error">
  
  <object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
  <html:errors/>
  <font color="red"><b><%=request.getAttribute("msgNote")==null?"":request.getAttribute("msgNote")%></b></font>
</div>
<div class="box_common_conten">
  <html:form action="ListXNDChieu4Action.do" method="post" >  
  <html:hidden property="chuky_ktt" styleId="chuky_ktt" />  
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> <fmt:message key="doi_chieu.page.title.duyetdc3"/></span>
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
       <td width="55%" align="center" colspan="4" >
        <fieldset>
        <legend><font color="Blue">Danh s&#225;ch b&#7843;ng k&#234;</font></legend>
        <div  style="height:185px;overflow-y: scroll;">
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="1"
                                             cellspacing="0" cellpadding="0" >
                 <tr>
                 <td align="center" width="10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.ngaydc"/></td>
                 <!--<td align="center" width="10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.dc2.ngaythien"/></td>-->
                 <td align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.duyet.manh"/></td>
                 <td align="center" width="10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.lan"/></td>
                 <td align="center" width="25%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.sdxn"/></td>
                 <td align="center" width="40%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai"/></td>
                 </tr>
                 <logic:empty name="colList">
                  <tr>
                    <td colspan="5">
                      <font color="Red">Kh&#244;ng c&#243; b&#7843;ng k&#234; &#273;&#7889;i chi&#7871;u</font>
                    </td>
                  </tr>
                </logic:empty>
               <logic:notEmpty name="colList">
                  <logic:iterate id="UDlist" name="colList" indexId="index">
                 <tr class="ui-widget-content jqgrow ui-row-ltr" 
                      id="row_qt_<bean:write name="index"/>" onclick="rowSelectedFocusXNDC('row_qt_<bean:write name="index"/>');
                               fillDataDuyetXNDC3('ListXNDChieu4Action.do','<bean:write name="UDlist" property="kq_id"/>','row_qt_<bean:write name="index"/>');">
                   <td align="center">
                    <bean:write name="UDlist" property="ngay_dc"/>                    
                   </td>
                   <td align="center">
                    <bean:write name="UDlist" property="send_bank"/>            
                   </td>
                   <td align="center">
                    <bean:write name="UDlist" property="lan_dc"/>            
                   </td>
                  <td align="center" id="td_qt_<bean:write name="index"/>">
                    <bean:write name="UDlist" property="bk_id"/> 
                   </td>
                   <td align="center">
                     <logic:equal name="UDlist" property="trang_thai_bk" value="00">
                        <fmt:message key="doi_chieu.page.lable.00"/>
                     </logic:equal>
                     <logic:equal name="UDlist" property="trang_thai_bk" value="01">
                        <fmt:message key="doi_chieu.page.lable.01"/> -
                     </logic:equal>
                     <logic:equal name="UDlist" property="trang_thai_bk" value="02">
                        <fmt:message key="doi_chieu.page.lable.02"/> -
                     </logic:equal>
                     <logic:notEqual name="UDlist" property="trang_thai_bk" value="00">
                     <logic:equal name="UDlist" property="trang_thai_kq" value="01">
                        <fmt:message key="doi_chieu.page.lable.duyet.01"/>
                     </logic:equal>
                     <logic:equal name="UDlist" property="trang_thai_kq" value="02">
                        <fmt:message key="doi_chieu.page.lable.duyet.02"/>
                     </logic:equal>
                     </logic:notEqual>
                     <input type="hidden" name="tthai_<bean:write name="index"/>" id="tthai_<bean:write name="index"/>" value="<bean:write name="UDlist" property="trang_thai_kq"/>" />
                     <input type="hidden" name="kq_id" id="kqid" value="<bean:write name="UDlist" property="kq_id"/>"/>
                     <input type="hidden" name="bk_id" id="bkid" value="<bean:write name="UDlist" property="bk_id"/>"/>
                     <html:hidden property="ngay_dc" name="UDlist"/>
                     <html:hidden property="trang_thai_bk" name="UDlist"/>
                     <html:hidden property="send_bank" name="UDlist"/>
                     <html:hidden property="lan_dc" styleId="landc" name="UDlist"/>
                   </td>
                 </tr>
                  </logic:iterate>
                </logic:notEmpty>             
             </table>
           </div>
        </fieldset>
       </td>
       <td width="45%">
        <fieldset>
        <legend><font color="Blue">T&#7893;ng h&#7907;p b&#7843;ng k&#234;</font></legend>
         <div style="height:185px;">
          <table width="100%" cellspacing="2" cellpadding="2"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
              <logic:empty name="colTHBK">
                <td colspan="4">
                  <font color="red">Kh&#244;ng c&#243; th&#244;ng tin b&#7843;ng k&#234;</font>     
                </td>
              </logic:empty>
             <logic:notEmpty name="colTHBK">
             <logic:iterate id="UDlist" name="colTHBK" indexId="index">
             <tr>
               <td width="70%"><fmt:message key="doi_chieu.page.lagle.sdutkdaungay"/></td>
               <td align="right">
               <fmt:setLocale value="us_US"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
               <bean:write name="UDlist" property="sodu_daungay"/>
                </fmt:formatNumber>
               <html:hidden property="kq_id" name="UDlist"/>
               <html:hidden property="send_bank" name="UDlist"/>
               </td>
             </tr>
             <tr>
               <td><fmt:message key="doi_chieu.page.lagle.tongchitrongngay"/></td>
               <td align="right">
               <fmt:setLocale value="us_US"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                <bean:write name="UDlist" property="tong_chi"/>
                </fmt:formatNumber>
                </td>
             </tr>
             <tr>
               <td><fmt:message key="doi_chieu.page.lagle.tongthutrongngay"/></td>
               <td align="right"> 
               <fmt:setLocale value="us_US"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
              <bean:write name="UDlist" property="tong_thu"/>
              </fmt:formatNumber>
              </td>
             </tr>
             <tr>
               <td><fmt:message key="doi_chieu.page.lagle.sdutkcuoingay"/></td>
               <td align="right" > 
               <fmt:setLocale value="us_US"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
               <bean:write name="UDlist" property="so_du_cuoi_ngay"/>
               </fmt:formatNumber>
               </td>
             </tr>
                </logic:iterate>
             </logic:notEmpty>
         </table>
         </div>
        </fieldset>
       
       </td>
      </tr>
      <tr>
      <td align="center" colspan="5">
       <button type="button" accesskey="t" style="width:170px" id="btnInBKDC" onclick="check('print')" >
         In kết quả đối chiếu
        </button>
      </td>
    </tr>
      <tr>
        <td colspan="5">
          <fieldset>
            <legend><font color="Blue">Chi ti&#7871;t k&#7871;t qu&#7843; &#273;&#7889;i chi&#7871;u</font></legend>
             <div class="tabber" id="mytabber1">
              <div class="tabbertab" style="height:300;overflow-y: scroll;">
                <h2><fmt:message key="doi_chieu.page.lagle.dienmt900"/></h2> 
                 <table width="100%" cellspacing="0" cellpadding="1" 
                     bordercolor="#e1e1e1" border="1" align="center"
                     style="BORDER-COLLAPSE: collapse">
                  <tr>
                    <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                        <fmt:message key="doi_chieu.page.lagle.solenhqt"/>
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
                        <fmt:message key="doi_chieu.page.lagle.ngayct"/>
                      </div>
                    </th>
                    <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                        Loại tiền
                      </div>
                    </th>
                    <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                        <fmt:message key="doi_chieu.page.lable.stien"/>
                      </div>
                    </th>
                    <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">          
                        <fmt:message key="doi_chieu.page.lable.tthai"/>
                      </div>
                    </th>
                  </tr>
            <logic:notEmpty name="colMT900">
              <logic:iterate id="items" name="colMT900" indexId="stt">
                  <tr>
                     <td align="center">
                      <bean:write name="items" property="mt_id"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="ma_kb"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="ten_kb"/>
                    </td>
                     <td align="center">
                      <bean:write name="items" property="ngay_ct"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="loai_tien"/>
                    </td>
                    <td align="center">
                    <fmt:setLocale value="us_US"/>
                  <fmt:formatNumber maxFractionDigits="5"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="so_tien"/>
                  </fmt:formatNumber>
                    </td>
                   
                    <td align="center">
                      <logic:equal value="00" property="trang_thai" name="items">
                        SGD KBNN thiếu  – Hội sở chính NH thừa
                      </logic:equal>
                      <logic:equal value="01" property="trang_thai" name="items">
                         SGD KBNN  thừa – Hội sở chính NH thiếu 
                      </logic:equal>
                    </td>
                  </tr>
          </logic:iterate>
          </logic:notEmpty>
          </table>
          </div>   
              <div class="tabbertab">
                <h2><fmt:message key="doi_chieu.page.lagle.dienmt910"/></h2> 
                <table width="100%" cellspacing="0" cellpadding="1" 
                     bordercolor="#e1e1e1" border="1" align="center"
                     style="BORDER-COLLAPSE: collapse">
                  <tr>
                    <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                        <fmt:message key="doi_chieu.page.lagle.solenhqt"/>
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
                        <fmt:message key="doi_chieu.page.lagle.ngayct"/>
                      </div>
                    </th>
                    <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">
                        <fmt:message key="doi_chieu.page.lable.stien"/>
                      </div>
                    </th>
                    <th class="promptText" bgcolor="#f0f0f0">
                      <div align="center">          
                        <fmt:message key="doi_chieu.page.lable.tthai"/>
                      </div>
                    </th>
                  </tr>
            <logic:notEmpty name="colMT910">
              <logic:iterate id="items" name="colMT910" indexId="stt">
                   <tr id="row_dts_<bean:write name="stt"/>">
                    <td align="center">
                      <bean:write name="items" property="mt_id"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="ma_kb"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="ten_kb"/>
                    </td>
                     <td align="center">
                      <bean:write name="items" property="ngay_ct"/>
                    </td>
                    <td align="center">
                    <fmt:setLocale value="us_US"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="so_tien"/>
                    </fmt:formatNumber>
                    </td>
                   
                    <td align="center">
                      <logic:equal value="00" property="trang_thai" name="items">
                        SGD KBNN thiếu  – Hội sở chính NH thừa
                      </logic:equal>
                      <logic:equal value="01" property="trang_thai" name="items">
                         SGD KBNN  thừa – Hội sở chính NH thiếu 
                      </logic:equal>
                    </td>
                    
                  </tr>  
           </logic:iterate>
          </logic:notEmpty>
          </table>
             </div>   
             </div>
          </fieldset>
        </td>
      </tr>
      <tr > 
        <td align="right" colspan="5">
            <button  type="button" onclick="check('view')" accesskey="i">
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
function check(type) {
var f = document.forms[0];
var tcuu="<%=tcuu%>";
    if (type == 'print') {
        var kq_id= jQuery("#kqid").val();
        var lan_dc= jQuery("#landc").val();
        f.action = 'printTTinDChieu4Action.do?type=kqdc3&kq_id='+kq_id+'&lan_dc='+lan_dc;    
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';  
        f.submit();
      }
     if (type == 'close') {
       
         if(tcuu!=null && ""!=tcuu){
        f.action = 'FindTTinDChieu4Action.do'+tcuu;           
         }else if (tcuu==null || ""==tcuu){
         f.action = 'TCuuTTinDChieu4Action.do';
      }
         f.submit();
      }
      if (type == 'view') {
        var bk_id= jQuery("#bkid").val();
         f.action = 'CTietBKeDChieu4.do'+tcuu+'&loai_dc=3&bk_id='+ bk_id;
         f.submit();
      } 
}
 bton();
function bton(){
var kq_id= jQuery("#kqid").val();
   if(kq_id==null|| ''==kq_id){
        document.getElementById("btnInBKDC").disabled=true;
   }
 }


</script>