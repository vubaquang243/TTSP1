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
   String tcong = request.getAttribute("duyettcong")==null?"":request.getAttribute("duyettcong").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    var strRowSelected="<%=strRowSelected%>";
    var tcong="<%=tcong%>";
    if(strRowSelected!=null && '' != strRowSelected){
    rowSelectedFocusXNDC(strRowSelected);
    }
    if(tcong!=null && '' != tcong){
      alert(GetUnicode('Duy&#7879;t x&#225;c nh&#7853;n &#273;&#7889;i chi&#7871;u quy&#7871;t to&#225;n to&#224;n qu&#7889;c th&#224;nh c&#244;ng'));
    }

  });
  
</script>
<div class="app_error">
   <!--manhnv-24/06/2013-->
  <object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
  <!--manhnv-24/06/2013-->
  <html:errors/>
  <font color="red"><b><%=request.getAttribute("msgNote")==null?"":request.getAttribute("msgNote")%></b></font>
</div>
<div class="box_common_conten">
  <html:form action="ListXNDChieu3Action.do" method="post" >
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
             <div>
              Th&#244;ng tin NH &nbsp;         
               <html:select property="nhkb_nhan" styleId="abc" onchange="change()"
                             style="width: 60%"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="000">-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                    <html:optionsCollection name="dmucNH" value="ma_nh" label="ten_nh"/>
                </html:select> 
        </div>
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
                 <td align="center" width="25%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai"/></td>
                 <td align="center" width="15%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai_ttin"/></td>
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
                               fillDataDuyetXNDC3('ListXNDChieu3Action.do','<bean:write name="UDlist" property="kq_id"/>','row_qt_<bean:write name="index"/>');">
                   <td align="center">
                    <bean:write name="UDlist" property="ngay_dc"/>                    
                   </td>
                   <!--<td align="center">
                    <bean:write name="UDlist" property="ngay_thien_dc"/>                    
                   </td>-->
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
                      
                     <logic:equal name="UDlist" property="trang_thai_kq" value="01">
                        <fmt:message key="doi_chieu.page.lable.duyet.01"/>
                     </logic:equal>
                     <logic:equal name="UDlist" property="trang_thai_kq" value="02">
                        <fmt:message key="doi_chieu.page.lable.duyet.02"/>
                     </logic:equal>
 
                     <input type="hidden" name="tthai_<bean:write name="index"/>" id="tthai_<bean:write name="index"/>" value="<bean:write name="UDlist" property="trang_thai_kq"/>" />
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
               <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
               <bean:write name="UDlist" property="sodu_daungay"/>
                </fmt:formatNumber>
               <html:hidden property="kq_id" name="UDlist"/>
               <html:hidden property="bk_id" name="UDlist"/>
               <html:hidden property="send_bank" name="UDlist"/>
               </td>
             </tr>
             <tr>
               <td><fmt:message key="doi_chieu.page.lagle.tongchitrongngay"/></td>
               <td align="right">
               <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                <bean:write name="UDlist" property="tong_chi" />
                </fmt:formatNumber>
                </td>
             </tr>
             <tr>
               <td><fmt:message key="doi_chieu.page.lagle.tongthutrongngay"/></td>
               <td align="right">    
               <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
              <bean:write name="UDlist" property="tong_thu" />
              </fmt:formatNumber>
              </td>
             </tr>
             <tr>
               <td><fmt:message key="doi_chieu.page.lagle.sdutkcuoingay"/></td>
               <td align="right" >             
               <fmt:setLocale value="vi_VI"/>
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
                    <!--<th class="promptText" bgcolor="#f0f0f0">
                        <div align="center">
                            <fmt:message key="doi_chieu.page.lable.nhkbchuyen"/>
                        </div>
                    </th>
                    <th class="promptText" bgcolor="#f0f0f0">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.lable.tennhkbchuyen"/>
                        </div>
                    </th>-->
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
            <logic:notEmpty name="colMT900">
              <logic:iterate id="items" name="colMT900" indexId="stt">
                  <tr>
                     <td align="center">
                      <bean:write name="items" property="mt_id"/>
                    </td>
                    <!--<td align="center">
                      <bean:write name="items" property="ma_nh"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="ten_receive_bank"/>
                    </td>-->
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
                    <fmt:setLocale value="vi_VI"/>
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
                    <!--<th class="promptText" bgcolor="#f0f0f0">
                        <div align="center">
                            <fmt:message key="doi_chieu.page.lable.nhkbchuyen"/>
                        </div>
                    </th>
                    <th class="promptText" bgcolor="#f0f0f0">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.lable.tennhkbchuyen"/>
                        </div>
                    </th>-->
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
                    <!--<td align="center">
                      <bean:write name="items" property="ma_nh"/>
                    </td>
                    <td align="center">
                      <bean:write name="items" property="ten_receive_bank"/>
                    </td>-->
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
                    <fmt:setLocale value="vi_VI"/>
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
            <button type="button" onclick="check('create')" accesskey="t" id="bt">
              <span class="sortKey">D</span>uy&#7879;t
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


    disBt()
  function disBt(){
    var strRowSelected="<%=strRowSelected%>";
    if(strRowSelected!=null && '' != strRowSelected){
      stt= strRowSelected.substr(7,5);
      sttNext=parseInt(stt);
      tthai_dxn=document.getElementById("tthai_"+sttNext).value;
          if(tthai_dxn=='02'){
              document.getElementById("bt").disabled=true;
         }else if (tthai_dxn=='01'||tthai_dxn=='00'){
          document.getElementById("bt").disabled=false;
        }
    }else{
      document.getElementById("bt").disabled=true;
    }
  }
  

  function check(type) {  
  var f = document.forms[0];
    if (type == 'create') {
      f.action = 'DuyetXNDChieu3Action.do';
    }
      if (type == 'print') {
        f.action = 'PrintDChieu3Action.do';    
                var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';      
      }
      if (type == 'close') {
        f.action = 'mainAction.do';        
      }
      if (type == 'view') {
        var bk_id= jQuery("#id").val();
         f.action = 'CTietBKeDChieu3.do?type=kq&bk_id='+bk_id;

      }
      //manhnv-24/06/2013
        if("Y"==strChkKy){
            if(!ky()){
              alert("Ký không thành công");
              return false;
            }
          }
      //manhnv-24/06/2013
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
     function change() {
      var ma;
      var frm = document.forms[0];
      ma=jQuery('#abc').val();
      if(ma!=null && ''!=ma){
       frm.action = 'ListXNDChieu3Action.do?nhkb_nhan='+ma;
      }

       frm.submit();
  }
</script>