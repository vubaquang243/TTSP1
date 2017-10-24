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
   String type_dc3 = request.getAttribute("type")==null?"":request.getAttribute("type").toString();
   String idxNH = request.getAttribute("idxNH")==null?"":request.getAttribute("idxNH").toString();
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

<div class="box_common_conten">
  <html:form action="CTietBKeDChieu3NgoaiTe.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>Chi tiết bảng kê đối chiếu ngoại tệ</span>
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
       <td >
        <fieldset>
        <legend><font color="Blue">T&#7893;ng h&#7907;p b&#7843;ng k&#234;</font></legend>
         <div style="height:100px;">
         <br/>
          <table width="80%" cellspacing="2" cellpadding="2"
                 bordercolor="#e1e1e1" border="0" align="center"
                 style="BORDER-COLLAPSE: collapse">
             <logic:notEmpty name="colTHBK">
             <logic:iterate id="UDlist" name="colTHBK" indexId="index">
             <tr>
             <td><fmt:message key="doi_chieu.page.lagle.tongthutrongngay"/></td>
               <td align="right">              
              <html:text name="UDlist" property="tong_thu" styleId="tong_thu" style="FONT-WEIGHT: bold"  styleClass="fieldTextRight" 
                              maxlength="20" readonly="true"/>
              </td><td>&nbsp;</td>
               <td width="25%"><fmt:message key="doi_chieu.page.lagle.sdutkdaungay"/></td>
               <td align="right" width="20%">
               <html:text name="UDlist" property="sodu_daungay" style="FONT-WEIGHT: bold" styleId="sodu_daungay" styleClass="fieldTextRight" 
                              maxlength="20" readonly="true" />
               <html:hidden property="bk_id" styleId="bk_id" name="UDlist"/>
                <html:hidden property="kq_id" styleId="kq_id" name="UDlist"/>
               <html:hidden property="send_bank" name="UDlist"/>
               <html:hidden property="ngay_dc" name="UDlist"/>
                <html:hidden property="lan_dc" name="UDlist"/>
               </td>
               
             </tr>
             <tr>
             <td width="25%"><fmt:message key="doi_chieu.page.lagle.tongchitrongngay"/></td>
               <td align="right" width="20%">
                <html:text name="UDlist" property="tong_chi" style="FONT-WEIGHT: bold" styleId="tong_chi" styleClass="fieldTextRight" 
                              maxlength="20" readonly="true"/>
                </td><td>&nbsp;</td>
             <td><fmt:message key="doi_chieu.page.lagle.sdutkcuoingay"/></td>
               <td align="right" >             
               <html:text name="UDlist" property="so_du_cuoi_ngay" style="FONT-WEIGHT: bold" styleId="so_du_cuoi_ngay" styleClass="fieldTextRight" 
                              maxlength="20" readonly="true"/>
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
                        <fmt:message key="doi_chieu.page.lable.stien"/>
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
                      <bean:write name="items" property="receive_bank"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="ten_receive_bank"/>
                    </td>

                     <td align="center">
                      <bean:write name="items" property="ngay_ct"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="so_tien"/>
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
                  </tr>
            <logic:notEmpty name="colMT910">
              <logic:iterate id="items" name="colMT910" indexId="stt">
                   <tr id="row_dts_<bean:write name="stt"/>">
                    <td align="center">
                      <bean:write name="items" property="mt_id"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="receive_bank"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="ten_receive_bank"/>
                    </td>
                    
                     <td align="center">
                      <bean:write name="items" property="ngay_ct"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="so_tien"/>
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
        <button type="button" accesskey="t" style="width:170px" id="btnInBKDC" onclick="check('print')" >
         In chi tiết bảng kê
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
jQuery("#tong_thu").val(toFormatNumber(jQuery("#tong_thu").val(),0,','));
jQuery("#tong_chi").val(toFormatNumber(jQuery("#tong_chi").val(),0,','));
jQuery("#sodu_daungay").val(toFormatNumber(jQuery("#sodu_daungay").val(),0,','));
jQuery("#so_du_cuoi_ngay").val(toFormatNumber(jQuery("#so_du_cuoi_ngay").val(),0,','));


function check(type) {
var f = document.forms[0];
    if (type == 'print') {
        var bk_id= jQuery("#bk_id").val();
        var kq_id= jQuery("#kq_id").val();
        f.action = 'printBKeDChieu3NgoaiTeAction.do?type=dc3&bk_id='+bk_id+'&kq_id='+kq_id;    
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';  
        f.submit();
      }
     if (type == 'close') {
       var tcuu="<%=tcuu%>";
       var type_dc3="<%=type_dc3%>";
       var bk_id= jQuery("#bk_id").val();
         if(tcuu!=null && ""!=tcuu){
            f.action = 'ViewTTinDChieu3NgoaiTeAction.do'+tcuu+'&id='+bk_id;           
         }else if (tcuu==null || ""==tcuu){
            if(type_dc3=='bk'){
                var idxNH="<%=idxNH%>";
                if(idxNH!=null && ""!=idxNH){
                  f.action = 'DChieu3NgoaiTeAction.do?idxNH='+idxNH;
                }else{
                f.action = 'DChieu3NgoaiTeAction.do';
                }
                
            }else if(type_dc3=='kq'){
              f.action = 'ListXNDChieu3NgoaiTeAction.do';
            }
      } 
         f.submit();
}
}
</script>