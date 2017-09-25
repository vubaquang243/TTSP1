<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/bke.quyettoan.js"
        type="text/javascript">
</script>
<fmt:setBundle basename="com.seatech.ttsp.resource.XuLyBKeQToanResource"/>
<%
  String viewAcc = request.getAttribute("view")==null?"":request.getAttribute("view").toString();
  String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
  String strRowSelected = request.getAttribute("rowSelected")==null?"row_qt_0":request.getAttribute("rowSelected").toString();
  String strChuc_Danh = request.getAttribute("chucdanh")==null?"":request.getAttribute("chucdanh").toString();
  String strUpdate_Status = request.getAttribute("updateStatus")==null?"":request.getAttribute("updateStatus").toString();
  String strUpdate_IDBK = request.getAttribute("updateIdBK")==null?"":request.getAttribute("updateIdBK").toString();
  String strViewBK = request.getAttribute("ViewBK")==null?"":request.getAttribute("ViewBK").toString();
//  if(strAction.toUpperCase() == 'ACTION_PASS'){
//        alert(GetUnicode('Duy&#7879;t b&#7843;ng k&#234; : ' + id + ' th&#224;nh c&#244;ng !'));
//      }else if(strAction.toUpperCase() == 'ACTION_RETURN'){
//        alert(GetUnicode('&#272;&#7849;y l&#7841;i b&#7843;ng k&#234; : ' + id + ' th&#224;nh c&#244;ng !'));
//      }else if(strAction.toUpperCase() == 'ACTION_REMOVE'){
//        alert(GetUnicode('H&#7911;y b&#7843;ng k&#234; : ' + id + ' th&#224;nh c&#244;ng !'));
//      }
%>
<%
  if(!strUpdate_Status.equals("")){
%>
  <table width="99%">
    <tbody>
    <tr>
      <td><font color="blue">
          <%
            if(strUpdate_Status.equalsIgnoreCase("ACTION_PASS")){
          %>
            Duyệt bảng kê :<font color="red"> <%=strUpdate_IDBK%> </font> thành công !
          <%
          }else if(strUpdate_Status.equalsIgnoreCase("ACTION_RETURN")){
          %>
            Đẩy lại bảng kê :<font color="red"> <%=strUpdate_IDBK%> </font> thành công !
          <%
          }else if(strUpdate_Status.equalsIgnoreCase("ACTION_REMOVE")){
          %>
            Hủy bảng kê :<font color="red"> <%=strUpdate_IDBK%> </font> thành công !
          <%
          }
          %>
        </font> 
      </td></tr>
    </tbody>
  </table>
<%
  }
%>
<script type="text/javascript">
  jQuery.noConflict();
  
  //************************************ LOAD PAGE **********************************
  jQuery(document).ready(function () {
    var strRowSelected="<%=strRowSelected%>";
//    var strUpdate_Status ="<%=strUpdate_Status%>";
//    var strUpdate_IDBK = "<%=strUpdate_IDBK%>";
    var strChuc_Danh="<%=strChuc_Danh%>";
    var viewAcc="<%=viewAcc%>";
    var strTrang_Thai=jQuery("#trang_thai").val();
    jQuery("#so_tien").val(jQuery("#so_tien").val());
    var strAction=null;
    if(viewAcc!=null && '' != viewAcc){
      jQuery("#refreshBtn").removeAttr("onclick");
      jQuery("#row_qt_0").removeAttr("ondblclick");
      jQuery("#row_qt_0").removeAttr("onclick");
      jQuery("#row_qt_0").attr("readonly", true);
    }
//    if(strUpdate_Status!=""){
//        messageUpdate(strUpdate_IDBK,strUpdate_Status);
//    }

    rowSelectedFocusBKQT(strRowSelected);
    roleUserBKQT(strChuc_Danh,strTrang_Thai);
    jQuery("#dialog-confirm").dialog( {
    autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
          "Có" : function () {    
              // thuc hien update trang thai
              if(viewAcc!=null && '' != viewAcc){
                var tcuu="<%=tcuu%>";
                 if(tcuu!=null && ""!=tcuu){
                    document.forms[0].action = "ViewTCuuBKeQToan.do"+ tcuu;
                }else if (tcuu==null || ""==tcuu){
                    document.forms[0].action = "TCuuBKeQToanList.do";
                }
              }else if(viewAcc==null || '' == viewAcc){
              document.forms[0].action = "thoatView.do";
              }
              document.forms[0].target = '';
              document.forms[0].submit();   
              jQuery(this).dialog("close");
          },
          "Không" : function () {    
              // thuc hien update trang thai
              jQuery(this).dialog("close");
          }
      }
  });
  jQuery("#dialog-message-check").dialog( {
    autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
          "Có" : function () {    
              // thuc hien update trang thai                
                updateExcuteBKQT(strAction);
                jQuery("dialog-message-check").dialog("close");
          },
          "Không" : function () {    
              // thuc hien update trang thai
              jQuery("dialog-message-check").dialog("close");
          }
      }
  });
    jQuery("#btnDaylai").click(function () {
    var f = document.forms[0];
    document.forms[0].target='';
    if(jQuery("#lydo_daylai").val() == null || ''== jQuery("#lydo_daylai").val().trim()){
            alert('Phải nhập lý do đẩy lại!');
            jQuery("#lydo_daylai").focus();
            return false;
    }
    strAction="ACTION_RETURN";
    jQuery("#dialog-message-check").html('<fmt:message key="XLy.BKe.QToan.page.message.confirm_daylai"/>');
    jQuery("#dialog-message-check").dialog("open");
  });
  jQuery("#btnDuyet").click(function () {
    var f = document.forms[0];
    document.forms[0].target='';
    strAction="ACTION_PASS";
     if("Y"==strChkKy){
            if(!ky()){
              alert("Ký không thành công");
              return false;
            }
          }
    jQuery("#dialog-message-check").html('<fmt:message key="XLy.BKe.QToan.page.message.confirm_duyet"/>');
    jQuery("#dialog-message-check").dialog("open");
  });
  jQuery("#btnHuy").click(function () {
    var f = document.forms[0];
    document.forms[0].target='';
    strAction="ACTION_REMOVE";
    jQuery("#dialog-message-check").html('<fmt:message key="XLy.BKe.QToan.page.message.confirm_huy"/>');
    jQuery("#dialog-message-check").dialog("open");
  });
  jQuery("#btnIn").click(function () {
      var f = document.forms[0];
      f.action = "quyettoanSGDRptAction.do";
      var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
      newDialog = window.open('', '_form', params);          
      f.target='_form';
      f.submit();
  });
    jQuery("#btnThoat").click(function () {
      var f = document.forms[0];
      document.forms[0].target='';
      if('<%=strViewBK%>'!="" && '<%=strViewBK%>'=='ViewBK'){
        f.action = "QuyetToanToanQuoc.do";        
        f.submit();
      }else{
        jQuery("#dialog-confirm").html('<fmt:message key="XLy.BKe.QToan.page.message.confirm.thoat"/>');
        jQuery("#dialog-confirm").dialog("open");
      }
  });
  });
  function viewLQT(id,type){ 
  var tcuu="<%=tcuu%>";
  document.forms[0].target = '';
  document.forms[0].action="XuLyLenhQuyetToan.do?id="+id+"&typeView="+type+"&rowSelected="+'<%=request.getAttribute("rowSelected")%>';

  document.forms[0].submit();
//    var urlRequest  ="XuLyLenhQuyetToanView.do?id="+id+"&typeView="+type;
//    window.location = urlRequest;
  }
  // In
//    function formAction(){
//      var f = document.forms[0];
//      f.action = "quyettoanSGDRptAction.do";
////      if(asyncCommand != null)
////      {
////         asyncCommand.Cancel();
////      }
//       var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
//      newDialog = window.open('', '_form', params);          
//      f.target='_form';
//      f.submit();
//    } 
    function ky(){
    	try {
            var cert = jQuery("#eSign")[0];
            cert.InitCert();                   
            var serial = cert.Serial;
            jQuery("#certSerial").val(serial);
            
            var noi_dung_ky = jQuery("#noi_dung_ky").val();
            var sign = cert.Sign(noi_dung_ky);
            jQuery("#chu_ky").val(sign);
            return true;
        }
        catch (e) {
            alert(e.description);
            return false;
        }
    }
    //**************************LINK TRANG CLICK********************************************    
  function goPage(page) {
  
  jQuery("#frmBKQT").target='';
      jQuery("#frmBKQT").attr( {
          action : 'XuLyQToanTQuocView.do?rowSelected='+'<%=request.getAttribute("rowSelected")%>'
      });
      jQuery("#pageNumber").val(page);
      jQuery("#frmBKQT").submit();
  }
</script>
<object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
<html:form styleId="frmBKQT" action="/XuLyQToanTQuoc.do">
<html:hidden property="chu_ky" styleId="chu_ky"/>
<html:hidden property="noi_dung_ky" styleId="noi_dung_ky"/>
<html:hidden property="certSerial" styleId="certSerial"/>
 
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
              <%
                  String strChucDanh = request.getAttribute("chucdanh").toString();
                  if(strChucDanh!=null && !"".equals(strChucDanh)){
                    if(strChucDanh.equalsIgnoreCase(AppConstants.NSD_TTV)){
              %>
                <fmt:message key="XLy.BKe.QToan.page.label.title.ttv"/>
              <%
               } else{
              %>
                <fmt:message key="XLy.BKe.QToan.page.label.title.ktt"/>
                <%
                }
              }
              %>
              
              </span>
            </td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
        </tbody>
    </table>  
    <table class="tableBound" border="1">
      <tr>
        <td>
          <table width="99%">
            <tbody>
            <tr>
              <td><font color="red">
                  <html:errors/>
                </font> 
              </td></tr>
            </tbody>
          </table>
          <table cellspacing="0" cellpadding="0" width="100%">
            <tbody>
              <tr>
                <td valign="top">
                  <table class="bordertableChungTu"  cellspacing="0" cellpadding="0"
                         width="100%">
                      <tbody>
                        <tr>
                          <td width="13%" height="100%;" valign="top">
                            <div>
                              <fieldset style="display: inline;height:250px;width:99%;max-width:97%;">      
                                <legend style="vertical-align:middle">
                                  <fmt:message key="XLy.BKe.QToan.page.label.master.sobk"/>
                                </legend>
                                <div style="height:100%;" class="sodientrasoattable">
                                  <div>
                                    <table  class="data-grid" cellspacing="0" cellpadding="0"
                                           width="98%">
                                      <thead>
                                        <tr>
                                          <th class="ui-state-default ui-th-column"
                                               width="62%;">
                                            <fmt:message key="XLy.BKe.QToan.page.label.master.sobk"/>
                                          </th>
                                          <th height="20" width="28%"
                                              class="ui-state-default ui-th-column">
                                            <fmt:message key="XLy.BKe.QToan.page.label.master.trangthai"/>
                                          </th>
                                          <th height="20" width="10%;"
                                              class="ui-state-default ui-th-column">
                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          </th>
                                        </tr>
                                      </thead>
                                    </table>
                                  </div>
                                  <div style="height:60%;width:97%" class="ui-jqgrid-bdiv ui-widget-content">
                                    <div>
                                      <table  class="data-grid" id="data-grid"
                                              style="border:0px;width:100%"
                                             cellspacing="0" cellpadding="0"                                  
                                             width="100%">
                                        <tbody>
                                          <logic:present name="lstDanhSachBKe">
                                            <div>
                                              <logic:notEmpty name="lstDanhSachBKe">
                                                <logic:iterate id="items" name="lstDanhSachBKe"
                                                               indexId="index">
                                                    <tr  class="ui-widget-content jqgrow ui-row-ltr" 
                                                        id="row_qt_<bean:write name="index"/>"
                                                        ondblclick="rowSelectedFocusBKQT('row_qt_<bean:write name="index"/>');"
                                                        onclick="rowSelectedFocusBKQT('row_qt_<bean:write name="index"/>');
                                                                 fillDataBKQuyetToan('<bean:write name="items" property="id"/>','row_qt_<bean:write name="index"/>');"
                                                        >
                                                        <td width="41%;"  align="center" id="td_qt_<bean:write name="index"/>">
                                                          <input name="row_item" id="col_qt_<bean:write name="index"/>" 
                                                          type="text" style="border:0px;font-size:10px;" onkeydown="arrowUpDownBKQT(event)"
                                                          value="<bean:write name="items" property="id"/>" 
                                                          readonly="true"/> 
                                                        </td>
                                                        <td width="28%;" align="center">
                                                         <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="01">
                                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" border="0" title="Chờ KTT duyệt"/>
                                                          </logic:equal>
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="02">
                                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif"
                                                                 border="0" title="Đã duyệt"/>                                        
                                                          </logic:equal>                                           
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="03">
                                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg"
                                                                 border="0"
                                                                 title="KTT đẩy lại"/>                                           
                                                          </logic:equal>                                           
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="04">
                                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif"
                                                                 border="0" title="Hủy"/>      
                                                          </logic:equal>
                                                        </td>
                                                      </tr>
                                                </logic:iterate>
                                              </logic:notEmpty>
                                              <logic:empty name="lstDanhSachBKe">
                                                <tr>
                                                  <td colspan="5" align="center">
                                                    <span style="color:red;">
                                                      <fmt:message key="XLy.BKe.QToan.sobk.empty"/>
                                                      </span>
                                                  </td>
                                                </tr>
                                              </logic:empty>
                                            </div>
                                          </logic:present>
                                        </tbody>
                                      </table>
                                    </div>
                                  </div>
                                  <div>
                                    <table class="data-grid" cellspacing="0" cellpadding="0"
                                           width="98%">
                                      <thead>
                                        <tr id="refreshButton">
                                          <td  class="ui-state-default ui-th-column"
                                              title="Refresh" id="refresh">
                                            <div style="float:left;cursor:pointer" class="ui-pg-div">
                                              <span id="refreshBtn" class="ui-icon ui-icon-refresh"
                                                    onclick="refreshGridBKQT();"></span>
                                            </div>
                                          </td>
                                        </tr>
                                      </thead>
                                    </table>
                                    <div style="padding-top:10px;float: left;">
                                      <fmt:message key="XLy.BKe.QToan.page.label.trang_thai"/>: 
                                      <logic:equal name="BKE_QuyetToanForm" value="01" property="trang_thai">
                                        <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" readonly="true" style="WIDTH: 145px;border:0px;font-weight:bold;"   type="text" id="mo_ta_trang_thai" value="Chờ KTT duyệt">
                                      </logic:equal>
                                      <logic:equal name="BKE_QuyetToanForm" value="02" property="trang_thai">
                                        <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" readonly="true" style="WIDTH: 145px;border:0px;font-weight:bold;"  type="text" id="mo_ta_trang_thai" value="Đã duyệt">
                                      </logic:equal>
                                      <logic:equal name="BKE_QuyetToanForm" value="03" property="trang_thai">
                                        <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" readonly="true" style="WIDTH: 145px;border:0px;font-weight:bold;"  type="text" id="mo_ta_trang_thai" value="KTT đẩy lại">
                                      </logic:equal>
                                      <logic:equal name="BKE_QuyetToanForm" value="04" property="trang_thai">
                                        <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" readonly="true" style="WIDTH: 145px;border:0px;font-weight:bold;"   type="text" id="mo_ta_trang_thai" value="Hủy">
                                      </logic:equal>
                                      <!--<span id="mo_ta_trang_thai" style="font-weight:bold;"></span>-->
                                    </div>
                                  </div>
                                </div>
                              </fieldset>
                            </div>
                          </td>
                          <td width="80%" valign="top">
                            <fieldset  style="display: inline;height:250px;width:99%;max-width:99">
                              <legend style="vertical-align:middle">
                                <fmt:message key="XLy.BKe.QToan.page.label.ttinbke"/>
                              </legend>
                              <div style="padding:5px 5px 5px 5px;">
                               
                               <input type="hidden" id="rowSelected"/>
                               <table style="BORDER-COLLAPSE: collapse;" border="1"
                                       cellspacing="0" bordercolor="#b0c4de"
                                       cellpadding="0" width="100%">
                                  <tbody>
                                    <tr style="width:100%">
                                      <td width="20%" align="right"><fmt:message key="XLy.BKe.QToan.page.label.ttinbke.sobke"/></td>
                                      <td width="30%" colspan="2">
                                         <html:text property="id"
                                           styleId="id"
                                           styleClass="fieldTextCode" tabindex="101"
                                           onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)"
                                           style="height:100%;width:98%"
                                           />
                                      </td>
                                      <td width="20%" align="right">
                                        <fmt:message key="XLy.BKe.QToan.page.label.ttinbke.trangthai"/>
                                      </td>
                                      <td width="30%" colspan="2">
                                          <logic:equal name="BKE_QuyetToanForm" value="01" property="trang_thai">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="mo_ta_trang_thai" value="Chờ KTT duyệt">
                                          </logic:equal>
                                          <logic:equal name="BKE_QuyetToanForm" value="02" property="trang_thai">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="mo_ta_trang_thai" value="Đã duyệt">
                                          </logic:equal>
                                          <logic:equal name="BKE_QuyetToanForm" value="03" property="trang_thai">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="mo_ta_trang_thai" value="KTT đẩy lại">
                                          </logic:equal>
                                          <logic:equal name="BKE_QuyetToanForm" value="04" property="trang_thai">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="mo_ta_trang_thai" value="Hủy">
                                          </logic:equal>
                                          
                                        <%--<html:text property="mo_ta_trang_thai"
                                           styleId="mo_ta_trang_thai"
                                           styleClass="fieldTextCode" tabindex="101"
                                           onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)"
                                           style="height:100%;width:99%"/>--%>
                                           <html:hidden property="trang_thai" styleId="trang_thai"/>
                                      </td>
                                    </tr>
                                    <tr style="width:100%">
                                      <td align="right">
                                        <fmt:message key="XLy.BKe.QToan.page.label.ttinbke.tongsotien"/>
                                      </td>
                                      <td colspan="2">
                                        <html:text property="so_tien"
                                           styleId="so_tien"
                                           styleClass="fieldTextRightCode" tabindex="101"
                                           onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)"
                                           style="height:100%;width:98%">
                                           </html:text>
                                      </td>
                                      <td align="right">
                                      Loại tiền
                                      </td>
                                      <td colspan="2" align="left">
                                      <html:text property="tcg_loai_tien"
                                               styleId="tcg_loai_tien"
                                               styleClass="fieldTextCode" tabindex="101"
                                               onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)"
                                               style="height:100%;width:30%"
                                          />
                                      </td>
                                    </tr>
                                    <tr style="width:100%">
                                      <td align="right"><fmt:message key="XLy.BKe.QToan.page.label.ttinbke.nguoitao"/></td>
                                      <td colspan="2">
                                          <html:text property="nguoi_tao"
                                               styleId="nguoi_tao"
                                               styleClass="fieldTextCode" tabindex="101"
                                               onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)"
                                               style="height:100%;width:30%"
                                          />
                                          <html:text property="ten_nguoi_tao"
                                               styleId="ten_nguoi_tao" 
                                               readonly="readonly"
                                               styleClass="fieldTextTrans"
                                               onkeydown="arrowUpDownBKQT(event)"
                                               onmouseout="UnTip()"
                                               onmouseover="Tip(this.value)"                                              
                                               style="border:0px;font-weight:bold;height:100%;width:65%"/>
                                      </td>
                                      
                                      <td align="right"><fmt:message key="XLy.BKe.QToan.page.label.ttinbke.ngaytao"/></td>
                                      <td colspan="2">
                                        <html:text property="ngay_tao"
                                               styleId="ngay_tao"
                                               styleClass="fieldTextCode" tabindex="101"
                                               onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)"
                                               style="height:100%;width:98%"
                                          />
                                      </td>
                                    </tr>
                                    <tr style="width:100%">
                                      <td align="right"><fmt:message key="XLy.BKe.QToan.page.label.ttinbke.nguoiks"/></td>
                                      <td colspan="2">
                                        <html:text property="nguoi_ks"
                                               styleId="nguoi_ks"
                                               styleClass="fieldTextCode" tabindex="101"
                                               onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)"
                                               style="height:100%;width:30%"
                                          />
                                          <html:text property="ten_nguoi_ks"
                                               styleId="ten_nguoi_ks" 
                                               readonly="readonly"
                                               styleClass="fieldTextTrans"
                                               onkeydown="arrowUpDownBKQT(event)"
                                               onmouseout="UnTip()"
                                               onmouseover="Tip(this.value)"                                                
                                               style="border:0px;font-weight:bold;height:100%;width:65%"/>
                                      </td>
                                      <td align="right"><fmt:message key="XLy.BKe.QToan.page.label.ttinbke.ngayks"/></td>
                                      <td colspan="2">
                                        <html:text property="ngay_ks"
                                               styleId="ngay_ks"
                                               styleClass="fieldTextCode" tabindex="101"
                                               onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)"
                                               style="height:100%;width:98%"
                                          />
                                      </td>
                                    </tr>
                                    <tr style="width:100%">
                                      <td align="right"><fmt:message key="XLy.BKe.QToan.page.label.ttinbke.lydo"/></td>
                                      <td colspan="5">
                                        <html:textarea property="lydo_daylai" 
                                               styleId="lydo_daylai" cols="3"  
                                               styleClass="fieldTextArea" tabindex="101"
                                               rows="3" style="WRAP:HARD;height:100%;width:98%" 
                                               onkeydown="arrowUpDownBKQT(event)"
                                               ></html:textarea>
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>                                
                            </div>
                            <fieldset  style="display: inline;height:100px;width:99%;max-width:98">
                              <legend style="vertical-align:middle">
                                Các tiêu chi gom của bảng kê
                              </legend>
                              <table style="BORDER-COLLAPSE: collapse;" border="1"
                                       cellspacing="0" bordercolor="#b0c4de"
                                       cellpadding="0" width="100%">
                                  <tbody>
                                     <tr style="width:100%">
                                      <td width="15%" align="right">Ngân hàng</td>
                                      <td width="15%">
                                         <logic:equal name="BKE_QuyetToanForm" value="201" property="tcg_ngan_hang">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="tcg_ngan_hang" value="NH Công Thương">
                                          </logic:equal>
                                          <logic:equal name="BKE_QuyetToanForm" value="202" property="tcg_ngan_hang">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="tcg_ngan_hang" value="NH Đầu Tư">
                                          </logic:equal>
                                          <logic:equal name="BKE_QuyetToanForm" value="203" property="tcg_ngan_hang">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="tcg_ngan_hang" value="NH Ngoại Thương">
                                          </logic:equal>
                                          <logic:equal name="BKE_QuyetToanForm" value="204" property="tcg_ngan_hang">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="tcg_ngan_hang" value="NH Nông Nghiệp">
                                          </logic:equal>
                                      </td>
                                      <td width="15%" align="right">
                                        Loại quyết toán
                                      </td>
                                      <td width="15%">
                                          <logic:equal name="BKE_QuyetToanForm" value="D" property="tcg_loai_qtoan">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="tcg_loai_qtoan" value="QT Thu">
                                          </logic:equal>
                                          <logic:equal name="BKE_QuyetToanForm" value="C" property="tcg_loai_qtoan">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="tcg_loai_qtoan" value="QT Chi">
                                          </logic:equal>                                      
                                      </td>
                                      <td width="15%" align="right">
                                        Loại tài khoản
                                      </td>
                                      <td width="15%">
                                          <logic:equal name="BKE_QuyetToanForm" value="01" property="tcg_loai_tk">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="tcg_loai_tk" value="TK tiền gửi">
                                          </logic:equal>
                                          <logic:equal name="BKE_QuyetToanForm" value="02" property="tcg_loai_tk">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="tcg_loai_tk" value="TK Thanh Toán">
                                          </logic:equal>
                                          <logic:equal name="BKE_QuyetToanForm" value="03" property="tcg_loai_tk">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="tcg_loai_tk" value="TK chuyên thu">
                                          </logic:equal>
                                          <logic:equal name="BKE_QuyetToanForm" value="04" property="tcg_loai_tk">
                                            <input onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)" tabindex="101" class="fieldTextCode" style="height:100%;width:98%" type="text" id="tcg_loai_tk" value="TK khác">
                                          </logic:equal>                                  
                                      </td>
                                    </tr>  
                                    <tr>
                                      <td width="15%" align="right">Ngày quyết toán</td>
                                      <td>
                                      <html:text property="tcg_ngay_qtoan"
                                               styleId="tcg_ngay_qtoan"
                                               styleClass="fieldTextCode" tabindex="101"
                                               onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)"
                                               style="height:100%;width:98%"
                                          />
                                      </td>
                                      <td width="15%" align="right">Ngày hạch toán</td>
                                      <td>
                                      <html:text property="tcg_ngay_ttoan"
                                               styleId="tcg_ngay_ttoan"
                                               styleClass="fieldTextCode" tabindex="101"
                                               onkeydown="nextElementFocusBKQT(event);arrowUpDownBKQT(event)"
                                               style="height:100%;width:98%"
                                          />
                                      <td>
                                      </td>
                                      <td></td>
                                    </tr>
                                  </tbody>
                                  </table>
                            </fieldset>
                           </fieldset>                           
                          </td>                          
                        </tr>
                        <tr style="width:80%" valign="top">
                          <td colspan="2" height="height:79%;" valign="top">
                            <fieldset  style="display: inline;height:auto;width:99%">
                              <legend style="vertical-align:middle">
                                <fmt:message key="XLy.BKe.QToan.page.label.chitiet"/>
                              </legend>
                              <div style="padding:5px 5px 5px 5px;">
                                      <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
                                             bordercolor="#999999" width="100%">
                                        <thead class="TR_Selected">
                                          <tr>
                                            <th width="2%">
                                              <fmt:message key="XLy.BKe.QToan.page.label.chitiet.STT"/>
                                            </th>
                                            <th width="14%">
                                              <fmt:message key="XLy.BKe.QToan.page.label.chitiet.nganhangcn"/>
                                            </th>
                                            <th width="14%">
                                              <fmt:message key="XLy.BKe.QToan.page.label.chitiet.khobachuyen"/>
                                            </th>
                                            <th width="9%">
                                              <fmt:message key="XLy.BKe.QToan.page.label.chitiet.ngayqt"/>
                                            </th>
                                            <th width="9%">
                                              <fmt:message key="XLy.BKe.QToan.page.label.chitiet.ngaytt"/>
                                            </th>
                                            <th width="10%">
                                              <fmt:message key="XLy.BKe.QToan.page.label.chitiet.taikhoannhan"/>
                                            </th>
                                            <th width="15%">
                                              <fmt:message key="XLy.BKe.QToan.page.label.chitiet.solenhqt"/>
                                            </th>
                                            <th width="12%">
                                              <fmt:message key="XLy.BKe.QToan.page.label.chitiet.sotien"/>
                                            </th>
                                            <th width="5%">
                                              <fmt:message key="XLy.BKe.QToan.page.label.chitiet.loaiqt"/>
                                            </th>
                                            <th width="10%">
                                              <fmt:message key="XLy.BKe.QToan.page.label.chitiet.loaiht"/>
                                            </th>
                                          </tr>
                                          <tbody>
                                            <logic:present name="lstQuyetToan">
                                              <logic:empty name="lstQuyetToan">
                                                  <tr>
                                                    <td colspan="10" align="center" style="color:red">
                                                      <fmt:message key="XLy.BKe.QToan.page.ketqua.empty"/>
                                                    </td>
                                                  </tr>
                                                </logic:empty>
                                                <logic:notEmpty name="lstQuyetToan">
                                                     <%
                                                      com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
                                                      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
                                                    %>
                                                    <logic:iterate id="items" name="lstQuyetToan" indexId="stt">
                                                      <tr valign="top" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                                                        <td align="center">
                                                          <%=stt+1%>
                                                        </td>
                                                        <td>
                                                          <bean:write name="items" property="ten_don_vi_chuyen"/>
                                                        </td>
                                                        <td>
                                                          <bean:write name="items" property="ten_kb_huyen"/>
                                                        </td>
                                                        <td>
                                                          <bean:write name="items" property="ngay_qtoan"/>
                                                        </td>
                                                        <td>
                                                          <bean:write name="items" property="ngay_htoan"/>
                                                        </td>
                                                        <td>
                                                          <bean:write name="items" property="tk_nhan"/>
                                                        </td>
                                                        <td align="center">
                                                          <a  href="javascript:viewLQT('<bean:write name="items" property="id"/>','true')">
                                                           <bean:write name="items" property="id"/>
                                                          </a>                                                          
                                                        </td>
                                                        <td align="right">
                                                          <logic:equal value="VND" property="tcg_loai_tien" name="BKE_QuyetToanForm">
                                                            <fmt:setLocale value="vi_VI"/>
                                                            <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                                                              <bean:write name="items" property="so_tien"/>
                                                            </fmt:formatNumber>
                                                          </logic:equal>
                                                          <logic:notEqual value="VND" property="tcg_loai_tien" name="BKE_QuyetToanForm">
                                                            <fmt:setLocale value="en_US"/>
                                                            <bean:write name="items"  property="so_tien" format="#,##0.00"/>
                                                          </logic:notEqual>
                                                        </td>
                                                        <td>
                                                          <logic:equal value="D" name="items" property="loai_qtoan">
                                                            <fmt:message key="XLy.BKe.QToan.page.loai_quyet_toan.thu"/>
                                                           </logic:equal>
                                                           <logic:equal value="C" name="items" property="loai_qtoan">
                                                            <fmt:message key="XLy.BKe.QToan.page.loai_quyet_toan.chi"/>
                                                           </logic:equal>
                                                        </td>
                                                        <td>
                                                          <logic:equal value="T" name="items" property="loai_hach_toan">
                                                            <fmt:message key="XLy.BKe.QToan.page.loai_hach_toan.dung"/>
                                                           </logic:equal>
                                                           <logic:equal value="P" name="items" property="loai_hach_toan">
                                                            <fmt:message key="XLy.BKe.QToan.page.loai_hach_toan.choxl"/>
                                                           </logic:equal>
                                                        </td>
                                                      </tr>
                                                    </logic:iterate>
                                                      <tr>
                                                        <td colspan="10">
                                                          <div style="float:right;padding-right:40">
                                                            <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                                                          </div>
                                                        </td>
                                                      </tr>
                                                  <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
                                                 </logic:notEmpty>
                                            </logic:present>
                                          </tbody>                                          
                                        </thead>
                                      </table>
                              </div>
                           </fieldset>
                          </td>
                        </tr>                        
                      </tbody>
                    </table>
                </td>
            </tr>
            </tbody>
          </table>
        <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="98%">
          <tr>
            <td> 
              <span id="duyet"> 
                <button id="btnDuyet" class="ButtonCommon"
                        type="button" accesskey="O">
                  <fmt:message key="XLy.BKe.QToan.page.button.duyet"/>
                </button>
              </span>
              <span id="daylai"> 
                <button id="btnDaylai" class="ButtonCommon"
                        type="button" accesskey="O">
                  <fmt:message key="XLy.BKe.QToan.page.button.daylai"/>
                </button>
              </span>
              <span id="in"> 
                <button id="btnIn" class="ButtonCommon"
                        type="button">
                  <fmt:message key="XLy.BKe.QToan.page.button.in"/>
                </button>
              </span>
              <span id="huy"> 
                <button id="btnHuy" class="ButtonCommon"
                        type="button" accesskey="O">
                  <fmt:message key="XLy.BKe.QToan.page.button.huy"/>
                </button>
              </span>
              <span id="thoat"> 
                <button id="btnThoat" class="ButtonCommon"
                        type="button" accesskey="O">
                  <fmt:message key="XLy.BKe.QToan.page.button.thoat"/>
                </button>
              </span>
            </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    <div id="dialog-confirm"
     title='<fmt:message key="XLy.BKe.QToan.page.title.dialog_confirm"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>
<div id="dialog-message-check"
     title='<fmt:message key="XLy.BKe.QToan.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
</html:form>

<%@ include file="/includes/ttsp_bottom.inc"%>