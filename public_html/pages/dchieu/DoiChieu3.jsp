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
        
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
   String idxNH = request.getAttribute("idxNH")==null?"":request.getAttribute("idxNH").toString();
%>

<script type="text/javascript">
  jQuery.noConflict();
  var role='<%=request.getAttribute("chucdanh")%>';
  //************************************ LOAD PAGE **********************************
     jQuery(document).ready(function()
      {
      var idxNH="<%=idxNH%>";
      if(idxNH!=null && ""!=idxNH){
        jQuery('#abc option:eq('+idxNH+')').attr('selected', true);
      }
      
        defaultRowSelectedDC3(role);
        var contexRoot='<%=AppConstants.NNT_APP_CONTEXT_ROOT%>',
        sodu_daungay=jQuery("#sodu_daungay"),
        ketchuyen_chi=jQuery("#ketchuyen_chi"),
        ps_thu=jQuery("#ps_thu"),
        hanmuc=jQuery("#hanmuc"),
        ketchuyen_thu=jQuery("#ketchuyen_thu"),
        sodu_cuoingay=jQuery("#sodu_cuoingay"),
        dialog_confirm=jQuery("#dialog-confirm"),
        dialog_message=jQuery("#dialog-message"),
           allFields = jQuery([]).add( sodu_daungay ).add( ketchuyen_chi )
           .add( ps_thu )
           .add( hanmuc )
           .add( ketchuyen_thu )
           .add( sodu_cuoingay );
        tableHighlightRow();
        jQuery("#dialog:ui-dialog").dialog("destroy" );
       //****************************dialog message**********************************************
        dialog_message.dialog({
          autoOpen: false,
          resizable:false,
          height:200,
          width:380,
          modal: true,
          buttons: {
            Ok: function() {
              jQuery(this).dialog( "close" );
            }
         }
        });
        //***************************dialog confirm message	*********************************************
//        
//						dialog_confirm.dialog({
//							autoOpen: false,
//              resizable: false,
//							height:200,
//							width:380,
//							modal: true,
//							buttons: {
//								"Có": function() {
//                  if   (jQuery('#eventAction').val()=='create')
//                    TaoXacNhan();
//                  else{
//                      jQuery('#frmBangKeDC3').attr({action:'thoatView.do'});
//                      jQuery('#frmBangKeDC3').submit();
//                  }
////                  jQuery( this ).dialog( "close" );
//                },
//								"Không": function() {
//                   jQuery( this ).dialog( "close" );
//                }
//							}
//						});
				
      
      });
      
</script>
<div class="app_error">
  <html:errors/>
  <font color="red"><b><%=request.getAttribute("msgNote")==null?"":request.getAttribute("msgNote")%></b></font>
</div>
<div class="box_common_conten">

  <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
    <tbody>
      <tr>
        <td width="13">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width="13" height="30"/>
        </td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class="title2"><fmt:message key="doi_chieu.page.title2"/></span>
        </td>
        <td width="62">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width="62" height="30"/>
        </td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
        <td width="4">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg"
               width="4" height="30"/>
        </td>
      </tr> 
    </tbody> 
  </table>
  <html:form action="THienDChieu3Action.do" method="post" styleId="frmBangKeDC3">

  <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound" bordercolor="#999999" width="100%">
  <tr>
  </tr>
    <tr>
    
      <td width="40%">
              <div align="center">
              Th&#244;ng tin NH&nbsp;         
               <html:select property="nhkb_nhan" styleId="abc" onchange="change()"
                             style="width: 60%"
                             onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                    <html:option value="000">-----Ch&#7885;n ng&#226;n h&#224;ng-----</html:option>
                    <html:optionsCollection name="dmucNH" value="ma_nh" label="ten_nh"/>
                </html:select> 
        </div>
        <fieldset>
          <legend>Danh s&#225;ch b&#7843;ng k&#234;</legend>
          <div>
            <table cellspacing="0" width="100%">
              <thead>
                <tr>
                  <th width="25%" class="ui-state-default ui-th-column">
                    <fmt:message key="doi_chieu.page.lable.nhang"/>
                  </th>
                   <th width="20%" class="ui-state-default ui-th-column">
                    Ngày ĐC
                  </th>
                  <th width="10%" class="ui-state-default ui-th-column">
                    Lần
                  </th>
                  <th width="30%" class="ui-state-default ui-th-column">
                    <fmt:message key="doi_chieu.page.lable.tthai"/>
                  </th>
                  <th width="15%" class="ui-state-default ui-th-column">
                    <fmt:message key="doi_chieu.page.lable.tthai_ttin"/>
                  </th>
                </tr>
              </thead>
            </table>
          </div>
          <div style="height:175px;" class="ui-jqgrid-bdiv ui-widget-content">
            <div> 
              <table class="data-grid" id="data-grid"
                     style="BORDER-COLLAPSE: collapse;" cellspacing="0"
                     cellpadding="0" width="100%">
                <tbody>
                  <logic:present name="colBangKe">
                    <logic:iterate id="bangkelist" name="colBangKe" indexId="index">                       
                       <tr style="width:100%;" class="ui-widget-content jqgrow ui-row-ltr"
                            id="row_dts_<bean:write name="index"/>"                                                   
                            ondblclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');" 
                            onclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');DChieu3Detail('<bean:write name="bangkelist" property="id"/>');">
                          <td  width="25%" align="center" id="td_dts_<bean:write name="index"/>">
                            <input size="8" tabindex="100" name="row_item" id="<bean:write name="index"/>" 
                            type="text" style="border:0px;" value="<bean:write name="bangkelist" property="send_bank"/>" 
                            onkeydown="arrowUpDown(event)" readonly="true"/>        
                            <input align="center" type="hidden" id="id_<bean:write name="index"/>" value="<bean:write name="bangkelist" property="id"/>"/> 
                          <td width="20%" align="center">
                            <bean:write name="bangkelist" property="ngay_dc"/>
                          </td>
                          <td width="10%" align="center">
                            <bean:write name="bangkelist" property="lan_dc"/>
                          </td>
                          <td align="center" width="30%">
                            <logic:equal value="00" name="bangkelist"
                                         property="trang_thai">Chưa đối chiếu</logic:equal>
                             
                            <logic:equal value="01" name="bangkelist"
                                         property="trang_thai">
                              <logic:equal value="00" name="bangkelist"
                                         property="trang_thai_kq">             
                                         Ch&ecirc;nh lệch-Chưa tạo ĐXN
                              </logic:equal>
                              <logic:equal value="01" name="bangkelist"
                                         property="trang_thai_kq">             
                                         Ch&ecirc;nh lệch-Đợi duyệt
                              </logic:equal>
                              <logic:equal value="02" name="bangkelist"
                                         property="trang_thai_kq">             
                                         Ch&ecirc;nh lệch-Đã duyệt
                              </logic:equal>
                            </logic:equal>
                             
                            <logic:equal value="02" name="bangkelist"
                                         property="trang_thai">
                              <logic:equal value="00" name="bangkelist"
                                         property="trang_thai_kq">             
                                         Khớp đ&uacute;ng-Chưa tạo ĐXN
                              </logic:equal>
                              <logic:equal value="01" name="bangkelist"
                                         property="trang_thai_kq">             
                                         Khớp đ&uacute;ng-Đợi duyệt
                              </logic:equal>
                              <logic:equal value="02" name="bangkelist"
                                         property="trang_thai_kq">             
                                         Khớp đ&uacute;ng-Đã duyệt
                              </logic:equal>
                            </logic:equal>
                          </td>
                          <td align="center" width="15%">
                   <logic:equal name="bangkelist" property="trang_thai_kq" value="02">                  
                        <logic:equal name="bangkelist" property="tthai_ttin" value="01">
                           &#272;&#227; g&#7917;i NH
                        </logic:equal>
                        <logic:equal name="bangkelist" property="tthai_ttin" value="02">
                          NH nh&#7853;n th&#224;nh c&#244;ng
                        </logic:equal>
                        <logic:equal name="bangkelist" property="tthai_ttin" value="03">
                         G&#7917;i NH kh&#244;ng th&#224;nh c&#244;ng
                        </logic:equal>
                   </logic:equal>
                   </td>
                      </tr>
                    </logic:iterate>
                  </logic:present>
                  <logic:empty name="colBangKe">
                  <tr>
                  <td colspan="5">
                    <font color="Red"> Kh&#244;ng c&#243; th&#244;ng tin b&#7843;ng k&#234;</font>
                  </td>
                  </tr>
                  </logic:empty>
                </tbody>
              </table>
            </div>
          </div>
        </fieldset>
      </td>

      <td>
        <fieldset>
          <legend>T&#7893;ng h&#7907;p b&#7843;ng k&#234;</legend>
          <div style="height:200px;">
            <html:hidden property="id"  styleId="id"/>
            <html:hidden property="send_bank" styleId="send_bank"/>
            <html:hidden property="ngay_dc"  styleId="ngay_dc"/>
            <html:hidden property="trang_thai_in"  styleId="trang_thai_in"/>
            <html:hidden property="kq_id"  styleId="kq_id"/>
            <html:hidden property="lan_dc"  styleId="lan_dc"/>
            
              <table width="99%" cellspacing="0" cellpadding="1"
                     bordercolor="#e1e1e1" border="1" align="center"
                     style="BORDER-COLLAPSE: collapse" height="198px;">
                <tr>
                  <td width="70%">
                    <fmt:message key="doi_chieu.page.lagle.sdutkdaungay"/>
                  </td>
                  <td>
                    <html:text property="sodu_daungay" style="border:1px"
                               styleId="sodu_daungay"
                               styleClass="fieldTextRight" maxlength="20"
                               readonly="true" size="30px"/>
                  </td>
                </tr>                 
                <tr>
                  <td>
                    <fmt:message key="doi_chieu.page.lagle.tongchitrongngay"/>
                  </td>
                  <td>
                    <html:text property="tong_chi" style="border:0px;"
                               styleClass="fieldTextRight" maxlength="20"
                               styleId="tong_chi" readonly="true"/>
                  </td>
                </tr>
                 
                <tr>
                  <td>
                    <fmt:message key="doi_chieu.page.lagle.tongthutrongngay"/>
                  </td>
                  <td>
                    <html:text property="tong_thu" maxlength="20"
                               style="border:0px;" styleClass="fieldTextRight"
                               styleId="tong_thu" readonly="true"/>
                  </td>
                </tr>
                 
                <tr>
                  <td>
                    <fmt:message key="doi_chieu.page.lagle.sdutkcuoingay"/>
                  </td>
                  <td>
                    <html:text property="so_du_cuoi_ngay" style="border:0px;"
                               styleClass="fieldTextRight" maxlength="20"
                               styleId="so_du_cuoi_ngay" readonly="true"/>
                  </td>
                </tr>
              </table>
          </div>
        </fieldset>
        
         </td>
     </tr>
    <tr>
      <td align="center" colspan="3">
       <button type="button" accesskey="t" style="width:170px" id="btnInBKDC" onclick="check('print')" >
         In kết quả đối chiếu
        </button>
        <button type="button" accesskey="t" style="width:170px" id="btnDC" onclick="SubmitDC3('frmBangKeDC3')">
          <fmt:message key="doi_chieu.page.button.thuc_hien"/>
        </button>
      </td>
    </tr>
    </table>
 </html:form>  
    <table style="BORDER-COLLAPSE: collapse" border="0" cellspacing="0" class="tableBound" bordercolor="#999999" width="100%">
    <tr>
      <td colspan="2">
        <fieldset>
          <legend>Chi ti&#7871;t ch&#234;nh l&#7879;ch</legend>
           <div class="tabber" id="mytabber1" >
            <div class="tabbertab" style="height:300;overflow-y: scroll;">
              <h2><fmt:message key="doi_chieu.page.lagle.dienmt900"/></h2> 
               <table width="100%" cellspacing="0" cellpadding="1" 
                   bordercolor="#e1e1e1" border="1" align="center"
                   style="BORDER-COLLAPSE: collapse" id="tblMT900">
                   <thead>
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
                </thead>
          <logic:notEmpty name="colMT900">
            <logic:iterate id="items" name="colMT900" indexId="stt">
            <tbody id="tbdMT900">
                <tr>
                   <td align="center">
                    <bean:write name="items" property="mt_id"/>
                  </td>
                  <!--<td align="center">
                    aaaaaaaaaaaaaaaa
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
                </tbody>
        </logic:iterate>
        </logic:notEmpty>
        </table>
        </div>   
            <div class="tabbertab">
              <h2><fmt:message key="doi_chieu.page.lagle.dienmt910"/></h2> 
              <table width="100%" cellspacing="0" cellpadding="1" 
                   bordercolor="#e1e1e1" border="1" align="center"
                   style="BORDER-COLLAPSE: collapse" id="tblMT910">
                   <thead>
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
                </thead>
          <logic:notEmpty name="colMT910">
            <logic:iterate id="items" name="colMT910" indexId="stt">
            <tbody>
                 <tr id="row_dts_<bean:write name="stt"/>">
                  <td align="center">
                    <bean:write name="items" property="mt_id"/>
                  </td>
                  <!--<td align="center">
                   aaaaaaaaaaaaaaaaaaaaaaaaaaaaaadddddddddd
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
                </tbody>
         </logic:iterate>
        </logic:notEmpty>
        </table>
           </div>   
           </div>
        </fieldset>
      </td>
    </tr>
     
    <tr>
      <td align="right" colspan="3">
        <input type="hidden" id="eventAction"/>
        <button type="button" onclick="confirmTaoDXNhan(); check('duyet')" id="btnTaoDXN" accesskey="t">
          <span class="sortKey">T</span>
          ạo điện X&aacute;c nhận
        </button>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <button  type="button" onclick="check('view')" accesskey="i">
              Ch<span class="sortKey">i</span> ti&#7871;t b&#7843;ng k&#234;
            </button>
             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <button type="button" onclick="check('close')" accesskey="o">
          Th
          <span class="sortKey">o</span>
          &aacute;t
        </button>
      </td>
    </tr>
  </table>
</div>
  <div id="dialog-message" title="<fmt:message key="doi_chieu.page.title.dialog_message"/>">
      <p>
        <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
        <span id="message"></span>
      </p>
  </div>
<div id="dialog-confirm"
     title='<fmt:message key="doi_chieu.page.title.dialog_confirm"/>'>
  <p>
     
    
  </p>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
  function SubmitDC3(fname) {
    var f = document.forms[fname];
      f.submit();
  }
  
  function check(type) {  
  var f = document.forms[0];	
      if (type == 'print') {
        f.action = 'PrintDChieu3Action.do';    
                var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';
        
      }
      else if (type == 'duyet') {      
        f.action = 'TaoXacNhanDCAction.do';
        document.getElementById("btnTaoDXN").disabled=true;
      }
      if (type == 'view') {
        var bk_id= jQuery("#id").val();
        var idxNH = jQuery('#abc option:selected').index();
         f.action = 'CTietBKeDChieu3.do?type=bk&bk_id='+bk_id+"&idxNH="+idxNH;

      }
       if (type == 'close') {
        f.action = 'mainAction.do';          
      } 
       f.submit();
  }
   function change() {
      var ma;
      
      var frm = document.forms[0];
      ma=jQuery('#abc').val();
//      alert(ma);
      if(ma!=null && ''!=ma){
       frm.action = 'DChieu3Action.do?nhkb_nhan='+ma;
      }
       frm.submit();
  } 
  
</script>