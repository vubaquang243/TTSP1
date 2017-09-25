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
<meta http-equiv="refresh" content="300;url=KeoDaiTGGD.do"/>  
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/keodaiTGGD.js"
        type="text/javascript">
</script>
<fmt:setBundle basename="com.seatech.ttsp.resource.KeoDaiTGGDResource"/>
<%
  String strChucdanh = request.getAttribute("chucdanh")!=null?request.getAttribute("chucdanh").toString():"";
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).ready(function () {
     var bExit = false;
     f=document.forms[0];
     var strAction="";
     var strChucDanh = '<%=strChucdanh%>';
     defaultButton(strChucDanh);
     defaultRowSelected();
     defaultStateFormKDTG();
     //dialog message
      jQuery("#dialog-message-check").dialog( {
        autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
              Ok : function () {    
              jQuery(this).dialog("close");
                }
              }
      });
    //dialog confirm message	
      jQuery("#dialog-confirm").dialog( {
          autoOpen : false, resizable : false, height : 200, width : 380, modal : true, buttons :  {
              "Có" : function () {     
                document.forms[0].action = "thoatView.do";
                document.forms[0].submit();
                jQuery(this).dialog("close");
              },              
              "Không" : function () {
                  jQuery(this).dialog("close");
              }
          }
      });
      jQuery("#btnExit").click(function () { 
        if(!bExit){
          jQuery("#dialog-confirm").html('<fmt:message key="TGGD.page.message_confirm.thoat"/>');
          jQuery("#dialog-confirm").dialog("open");
        }else{
          returnViewDTS();
        }
      });
//      jQuery("#btnDayLai").click(function () {
//       // set Type Action
//       if(jQuery("#lydo_ktt_day_lai").val().trim() == ""){
//         alert("Bạn phải nhập lý do kế toán trưởng đẩy lại");
//         jQuery("#lydo_ktt_day_lai").focus();
//         return;
//       }
//        strAction="ACTION_DAYLAI";
//        f.action="KeoDaiTGGDUpdateExc.do?action="+strAction+"&id="+jQuery("#id").val();
//        f.submit();
//      });
//      jQuery("#btnDuyet").click(function () {
//       // set Type Action
//        strAction="ACTION_DUYET";
//        f.action="KeoDaiTGGDUpdateExc.do?action="+strAction+"&id="+jQuery("#id").val();
//        f.submit();
//      });
      jQuery("#btnTraLoi").click(function () {
        defaultButton();
        dataGridReadonly();
        // set exit la back lai form kdtg
        bExit=true;
        // set Type Action
        strAction="ACTION_TL";
        jQuery("#ghi").show();
        jQuery("#noidung_traloiY").removeAttr("disabled");
        jQuery("#noidung_traloiY").attr('checked', 'checked');
        jQuery("#noidung_traloiN").removeAttr("disabled");
        
      });
      jQuery("#btnGhi").click(function () {
        if(strAction!=null || "undefined"!=strAction){
          if(strAction=="ACTION_CREATE"){
            if(jQuery("#ma_don_vi_nhan_tra_soat").val().trim()==''){
              alert("Phải nhập thông tin NHKB nhận !");              
              jQuery("#ma_don_vi_nhan_tra_soat").focus();
              return false;
            }
            if(jQuery("#thoi_gian_keo_dai").val().trim()==''){
              alert("Phải nhập thông tin ngày kéo dài giao dịch !");
              jQuery("#thoi_gian_keo_dai").focus();
              return false;
            }
            if(jQuery("#gio_keo_dai").val().trim()==''){
              alert("Phải nhập thông tin giờ kéo dài giao dịch !");
              jQuery("#gio_keo_dai").focus();
              return false;
            }
            if(jQuery("#phut_keo_dai").val().trim()==''){
              alert("Phải nhập thông tin phút kéo dài giao dịch !");
              jQuery("#phut_keo_dai").focus();
              return false;
            }
            f.action="KeoDaiTGGDAddExc.do";
            f.submit();
          }else if(strAction=='ACTION_TL'){
            f.action="KeoDaiTGGDUpdateExc.do?action="+strAction+"&id="+jQuery("#id").val();
            f.submit();
          }else if(strAction=='ACTION_UPDATE'){
            f.action="KeoDaiTGGDUpdateExc.do?action="+strAction+"&id="+jQuery("#id").val();
            f.submit();
          }else if(strAction=='ACTION_XACNHAN'){
            f.action="KeoDaiTGGDUpdateExc.do?action="+strAction+"&id="+jQuery("#id").val();
            f.submit();
          }
        }
      });
//      jQuery("#btnSua").click(function () {
//        defaultButton();
//        dataGridReadonly();
//        // set exit la back lai form kdtg
//        bExit=true;
//        // set Type Action
//        strAction="ACTION_UPDATE";
//        jQuery("#ghi").show();
//        jQuery("#thoi_gian_keo_dai").removeAttr("readonly");
//        jQuery("#gio_keo_dai").removeAttr("readonly");
//        jQuery("#phut_keo_dai").removeAttr("readonly");
//        jQuery("#thoi_gian_keo_dai").attr({"class":"fieldText"});
//        jQuery("#gio_keo_dai").attr({"class":"fieldText"});
//        jQuery("#phut_keo_dai").attr({"class":"fieldText"});
//        jQuery("#thoi_gian_keo_dai").focus();
//      });
      jQuery("#btnXacNhan").click(function () {
        defaultButton();
        dataGridReadonly();
        bExit=true;
        strAction="ACTION_XACNHAN";
        jQuery("#ghi").show();
      });
      jQuery("#btnTao").click(function () {
        defaultButton();
        dataGridReadonly();
        defaultStateFormKDTG();
        jQuery("#noidung_traloiY").attr('checked', '');
        jQuery("#noidung_traloiN").attr('checked', '');
        // set exit la back lai form kdtg
        bExit=true;
        // set Type Action
        strAction="ACTION_CREATE";
        fillDataForCreate();
        jQuery("#ghi").show();
        jQuery("#ma_don_vi_nhan_tra_soat").removeAttr("disabled");
        jQuery("#ten_don_vi_nhan_tra_soat").val("");
        jQuery("#thoi_gian_keo_dai").removeAttr("readonly");
        jQuery("#gio_keo_dai").removeAttr("readonly");
        jQuery("#phut_keo_dai").removeAttr("readonly");
        jQuery("#ma_don_vi_nhan_tra_soat").attr({"class":"fieldText"}).focus();
        jQuery("#thoi_gian_keo_dai").attr({"class":"fieldText"});
        jQuery("#gio_keo_dai").attr({"class":"fieldText"});
        jQuery("#phut_keo_dai").attr({"class":"fieldText"});
      });
  });
</script>
<title><fmt:message key="TGGD.page.title"/></title>
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
          <fmt:message key="TGGD.page.title"/></span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
        </tbody>
      </table> 
      <table class="tableBound">
        <tr>
          <td>
            <table width="99%">
              <tbody>
              <tr>
                <td><font color="red">
                    <html:errors/>
                  </font> 
                </td></tr>
                <tr>
                  <td align="center">
                    <%
                      String createSuccess=
                         request.getAttribute("createSuccess")!=null?(String)request.getAttribute("createSuccess"):"";    
                      String ACTION_TL =
                        request.getAttribute("ACTION_TL")!=null?(String)request.getAttribute("ACTION_TL"):"";   
                      String ACTION_DAYLAI = request.getAttribute("ACTION_DAYLAI")!=null?(String)request.getAttribute("ACTION_DAYLAI"):"";   
                      String ACTION_DUYET = request.getAttribute("ACTION_DUYET")!=null?(String)request.getAttribute("ACTION_DUYET"):"";  
                      String ACTION_UPDATE = request.getAttribute("ACTION_UPDATE")!=null?(String)request.getAttribute("ACTION_UPDATE"):"";  
                      if(createSuccess!=null && !"".equals(createSuccess)){
                    %>
                    <font color="blue">
                      Thêm mới DTS gia hạn thời gian  <%=createSuccess%> thành công !
                    </font>
                    <%
                      }                      
                    %>
                    <%
                      if(ACTION_TL!=null && !"".equals(ACTION_TL)){
                    %>
                    <font color="blue">
                      Trả lời DTS đến gia hạn thời gian  <%=ACTION_TL%> thành công !
                    </font>
                    <%
                    }
                    %>
                    <%
                      if(ACTION_DAYLAI!=null && !"".equals(ACTION_DAYLAI)){
                    %>
                    <font color="blue">
                      Đẩy lại DTS gia hạn thời gian  <%=ACTION_DAYLAI%> thành công !
                    </font>
                    <%
                    }
                    %>
                    <%
                      if(ACTION_DUYET!=null && !"".equals(ACTION_DUYET)){
                    %>
                    <font color="blue">
                      Duyệt DTS gia hạn thời gian  <%=ACTION_DUYET%> thành công !
                    </font>
                    <%
                    }
                    %>
                    <%
                      if(ACTION_UPDATE!=null && !"".equals(ACTION_UPDATE)){
                    %>
                    <font color="blue">
                      sửa DTS gia hạn thời gian  <%=ACTION_UPDATE%> thành công !
                    </font>
                    <%
                    }
                    %>
                  </td>
                </tr>
              </tbody>
            </table>
            <table cellspacing="0" cellpadding="0" width="100%">
              <tbody>
                <tr>
                  <td valign="top">
                    <table class="bordertableChungTu" cellspacing="0" cellpadding="0"
                           width="100%">
                    <html:form styleId="frmTGGD" action="/KeoDaiTGGD.do">
                      <tbody>
                        <tr>
                          <td width="15%" valign="top">
                            <div class="clearfix">
                              <table>
                                <tr>
                                  <td width="100%">
                                    <fieldset  style="height:150px;">
                                      <legend style="vertical-align:middle">
                                        <fmt:message key="TGGD.page.label.diendi"/>
                                      </legend>
                                      <div class="sodientrasoattable">
                                        <div>
                                          <table class="data-grid" cellspacing="0" cellpadding="0"
                                                 width="100%">
                                            <thead>
                                              <tr>
                                                <th class="ui-state-default ui-th-column"
                                                     width="62%;">
                                                  <fmt:message key="TGGD.page.label.sodien"/>
                                                </th>
                                                <th height="20" width="28%"
                                                    class="ui-state-default ui-th-column">
                                                  <fmt:message key="TGGD.page.label.tinhtrang"/>
                                                </th>
                                                <th height="20" width="10%;"
                                                    class="ui-state-default ui-th-column">
                                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                </th>
                                              </tr>
                                            </thead>
                                          </table>
                                        </div>                   
                                        <div style="height:80px;" class="ui-jqgrid-bdiv ui-widget-content">
                                          <div>
                                            <table  class="data-grid" id="data-grid"
                                                    style="border:0px;width:100%"
                                                   cellspacing="0" cellpadding="0"                                  
                                                   width="100%">
                                              <tbody>
                                                <logic:present name="lstDTSDi">
                                                  <div>
                                                    <logic:notEmpty name="lstDTSDi">
                                                      <logic:iterate id="items" name="lstDTSDi"
                                                                     indexId="index">
                                                       <tr class="ui-widget-content jqgrow ui-row-ltr" 
                                                          id="row_dts_<bean:write name="index"/>"                                        
                                                          ondblclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');"
                                                          onclick="rowSelectedFocus('row_dts_<bean:write name="index"/>');fillDataTGGD('<bean:write name="items" property="id"/>','row_dts_<bean:write name="index"/>',false);">
                                                          <td width="37%;"  align="center" id="td_dts_<bean:write name="index"/>">
                                                            <input onkeydown="arrowUpDownKDTG(event)"  name="row_item" id="col_dts_<bean:write name="index"/>" 
                                                            type="text" style="border:0px;font-size:10px;" 
                                                            value="<bean:write name="items" property="id"/>" 
                                                            readonly="true"/>
                                                          </td>
                                                          <td width="30%;" align="center">
                                                            <logic:equal name="items"
                                                                         property="trang_thai"
                                                                         value="02">
                                                              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" border="0" title="Chờ KTT duyệt"/>
                                                            </logic:equal>                                           
                                                            <logic:equal name="items"
                                                                         property="trang_thai"
                                                                         value="01">
                                                                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg"
                                                                   border="0"
                                                                   title="KTT đẩy lại"/>                                           
                                                            </logic:equal>                                           
                                                            <logic:equal name="items"
                                                                         property="trang_thai"
                                                                         value="03">
                                                              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif"
                                                                   border="0" title="Đã duyệt"/>
                                                            </logic:equal>                                           
                                                            <logic:equal name="items"
                                                                         property="trang_thai"
                                                                         value="04">
                                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif"
                                                                   border="0" title="Hủy"/>                                            
                                                            </logic:equal>
                                                            <logic:equal name="items"
                                                                         property="trang_thai"
                                                                         value="05">
                                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/send-success.jpg"
                                                                   border="0" title="Đã xử lý"/>                                            
                                                            </logic:equal>
                                                          </td>
                                                         
                                                        </tr>
                                                      </logic:iterate>
                                                    </logic:notEmpty>
                                                     
                                                    <logic:empty name="lstDTSDi">
                                                      <tr>
                                                        <td colspan="5" align="center">
                                                          <span style="color:red;">
                                                            <fmt:message key="TGGD.page.msg.empty"/>
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
                                                 width="100%">
                                            <thead>
                                              <tr id="refreshButton">
                                                <td  class="ui-state-default ui-th-column"
                                                    title="Refresh" id="refresh">
                                                  <div style="float:left;cursor:pointer" class="ui-pg-div">
                                                    <span class="ui-icon ui-icon-refresh"
                                                          onclick="refreshGridDTSTD();"></span>
                                                  </div>
                                                </td>
                                              </tr>
                                            </thead>
                                          </table>
                                          <!--<div style="padding-top:2px;float: left;">
                                            <fmt:message key="TGGD.page.label.trangthai"/>: 
                                            <html:text property="mo_ta_trang_thai"
                                                        readonly="true"
                                                        onkeydown="nextElementFocusKDTG(event);"
                                                         style="WIDTH: 110px;border:0px;font-weight:bold;" 
                                                        styleId="mo_ta_trang_thai"/>
                                            --><!--<span id="mo_ta_trang_thai" style="font-weight:bold;"></span>--><!--
                                          </div>-->
                                        </div>
                                      </div>
                                    </fieldset>
                                  </td>
                                </tr>
                                <tr>
                                  <td width="100%">
                                    <fieldset style="height:160px;">
                                      <legend style="vertical-align:middle">
                                        <fmt:message key="TGGD.page.label.dienden"/>
                                      </legend>
                                      <div class="sodientrasoattable">
                                        <div>
                                          <table class="data-grid" cellspacing="0" cellpadding="0"
                                                 width="100%">
                                            <thead>
                                              <tr>
                                                <th class="ui-state-default ui-th-column"
                                                     width="62%;">
                                                  <fmt:message key="TGGD.page.label.sodien"/>
                                                </th>
                                                <th height="20" width="28%"
                                                    class="ui-state-default ui-th-column">
                                                  <fmt:message key="TGGD.page.label.tinhtrang"/>
                                                </th>
                                                <th height="20" width="10%;"
                                                    class="ui-state-default ui-th-column">
                                                 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                </th>
                                              </tr>
                                            </thead>
                                          </table>
                                        </div>                   
                                        <div style="height:80px;" class="ui-jqgrid-bdiv ui-widget-content">
                                          <div>
                                            <table  class="data-dtsden-grid" id="data-dtsden-grid"
                                                    style="border:0px;width:100%"
                                                   cellspacing="0" cellpadding="0"                                  
                                                   width="100%">
                                              <tbody>
                                                <logic:present name="lstDTSDen">
                                                  <div>
                                                    <logic:notEmpty name="lstDTSDen">
                                                      <logic:iterate id="items" name="lstDTSDen"
                                                                     indexId="index">
                                                       <tr class="ui-widget-content jqgrow ui-row-ltr" 
                                                          id="row_dts_den_<bean:write name="index"/>"                                        
                                                          ondblclick="rowDTSDenSelectedFocus('row_dts_den_<bean:write name="index"/>');"
                                                          onclick="rowDTSDenSelectedFocus('row_dts_den_<bean:write name="index"/>');fillDataTGGD('<bean:write name="items" property="id"/>','row_dts_den_<bean:write name="index"/>',false);">
                                                          <td width="37%;"  align="center" id="td_dts_<bean:write name="index"/>">
                                                            <input onkeydown="arrowUpDownDTSDen(event)"  name="row_item" id="col_dts_den_<bean:write name="index"/>" 
                                                            type="text" style="border:0px;font-size:10px;" 
                                                            value="<bean:write name="items" property="id"/>" 
                                                            readonly="true"/>
                                                          </td>
                                                          <td width="30%;" align="center">
                                                            <logic:equal name="items"
                                                                         property="trang_thai"
                                                                         value="00">
                                                              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/edit.gif" border="0" title="Chờ TTV xử lý"/>
                                                            </logic:equal> 
                                                            <logic:equal name="items"
                                                                         property="trang_thai"
                                                                         value="02">
                                                              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" border="0" title="Chờ KTT duyệt"/>
                                                            </logic:equal>                                           
                                                            <logic:equal name="items"
                                                                         property="trang_thai"
                                                                         value="01">
                                                                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg"
                                                                   border="0"
                                                                   title="KTT đẩy lại"/>                                           
                                                            </logic:equal>                                           
                                                            <logic:equal name="items"
                                                                         property="trang_thai"
                                                                         value="03">
                                                              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif"
                                                                   border="0" title="Đã duyệt"/>
                                                            </logic:equal>                                           
                                                            <logic:equal name="items"
                                                                         property="trang_thai"
                                                                         value="04">
                                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif"
                                                                   border="0" title="Hủy"/>                                            
                                                            </logic:equal>
                                                            <logic:equal name="items"
                                                                         property="trang_thai"
                                                                         value="05">
                                                                          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/send-success.jpg"
                                                                   border="0" title="Đã xử lý"/>                                            
                                                            </logic:equal>
                                                          </td>
                                                         
                                                        </tr>
                                                      </logic:iterate>
                                                    </logic:notEmpty>
                                                     
                                                    <logic:empty name="lstDTSDen">
                                                      <tr>
                                                        <td colspan="5" align="center">
                                                          <span style="color:red;">
                                                            <fmt:message key="TGGD.page.msg.empty"/>
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
                                                 width="100%">
                                            <thead>
                                              <tr id="refreshButton">
                                                <td  class="ui-state-default ui-th-column"
                                                    title="Refresh" id="refresh">
                                                  <div style="float:left;cursor:pointer" class="ui-pg-div">
                                                    <span class="ui-icon ui-icon-refresh"
                                                          onclick="refreshGridDTSTD();"></span>
                                                  </div>
                                                </td>
                                              </tr>
                                            </thead>
                                          </table>
                                          <div style="padding-top:2px;float: left;">
                                            <fmt:message key="TGGD.page.label.trangthai"/>: 
                                            <html:text property="mo_ta_trang_thai"
                                                        readonly="true"
                                                        onkeydown="nextElementFocusKDTG(event);"
                                                         style="WIDTH: 110px;border:0px;font-weight:bold;" 
                                                        styleId="mo_ta_trang_thai"/>
                                            <!--<span id="mo_ta_trang_thai" style="font-weight:bold;"></span>-->
                                          </div>
                                        </div>
                                      </div>
                                    </fieldset>
                                  </td>
                                </tr>
                              </table>
                            </div>
                          </td>
                          <td  width="85%" valign="top">
                                    <fieldset style="height:320px;">
                                    <legend style="vertical-align:middle">
                                      <fmt:message key="TGGD.page.label.thongtinct"/>
                                    </legend>
                                    <div style="padding:5px 5px 5px 5px;">
                                    <input type="hidden" id="rowSelected"/>
                                    <input type="hidden" id="id"/>
                                      <table style="BORDER-COLLAPSE: collapse;" border="1"
                                             cellspacing="0" bordercolor="#b0c4de"
                                             cellpadding="0" width="99%">
                                        <tbody>
                                           <tr style="width:100%">
                                              <td width="15%">
                                                <fmt:message key="TGGD.page.label.nhkbchuyen"/>
                                              </td>
                                              <td width="10%">
                                                <html:text property="ma_don_vi_tra_soat"
                                                   styleId="ma_don_vi_tra_soat"
                                                   styleClass="fieldTextCode" 
                                                   tabindex="101" maxlength="8" onkeydown="nextElementFocusKDTG(event);" 
                                                   onkeypress="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                   style="WIDTH:94%;height:100%;"
                                                   />
                                              </td>
                                              <td width="25%">
                                                <html:text property="ten_don_vi_tra_soat"
                                                           styleId="ten_don_vi_tra_soat" 
                                                           readonly="readonly"
                                                           styleClass="fieldTextTrans"
                                                           onmouseout="UnTip()"                                                           
                                                           onmouseover="Tip(this.value)"  onkeydown="nextElementFocusKDTG(event);" 
                                                           onkeypress="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                           style="WIDTH:95%;border:0px;font-weight:bold;"/>
                                                           <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen"/>
                                              </td>
                                              <td width="15%">
                                                <fmt:message key="TGGD.page.label.nhkbnhan"/>
                                              </td>
                                              <td width="12%">
                                                <html:select styleClass="selectBox" onkeypress="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                             property="ma_don_vi_nhan_tra_soat" styleId="ma_don_vi_nhan_tra_soat" 
                                                             onblur="getTenNganHang('ma_don_vi_nhan_tra_soat', 'ten_don_vi_nhan_tra_soat', 'nhkb_nhan')" 
                                                             onchange="getTenNganHang('ma_don_vi_nhan_tra_soat', 'ten_don_vi_nhan_tra_soat', 'nhkb_nhan')" 
                                                             onclick="getTenNganHang('ma_don_vi_nhan_tra_soat', 'ten_don_vi_nhan_tra_soat', 'nhkb_nhan')" 
                                                             style="width:94%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
                                                    <html:optionsCollection label="ma_nh" value="ma_nh" name="lstNHHO"/>
                                                  </html:select>
                                                <%--<html:text property="ma_don_vi_nhan_tra_soat"
                                                   styleId="ma_don_vi_nhan_tra_soat"
                                                   styleClass="fieldTextCode"
                                                   tabindex="102" maxlength="8" onkeydown="nextElementFocusKDTG(event);" 
                                                   onblur="getTenNganHang('ma_don_vi_nhan_tra_soat', 'ten_don_vi_nhan_tra_soat', 'nhkb_nhan')"
                                                   onkeypress="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                   style="WIDTH:94%;height:100%;"
                                                   />--%>
                                                   <html:hidden property="nhkb_nhan" styleId="nhkb_nhan"/>
                                              </td>
                                              <td width="25%">
                                                <html:text property="ten_don_vi_nhan_tra_soat" 
                                                           onkeydown="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                           readonly="readonly"
                                                           styleClass="fieldTextTrans" 
                                                           onmouseout="UnTip()"                                                           
                                                           onmouseover="Tip(this.value)" 
                                                           style="WIDTH:95%;border:0px;font-weight:bold;" 
                                                           styleId="ten_don_vi_nhan_tra_soat"/>
                                              </td>
                                           </tr>
                                           <tr style="width:100%">
                                              <td width="15%">
                                                <fmt:message key="TGGD.page.label.nguoilap"/>
                                              </td>
                                              <td width="10%">
                                                <html:text property="ma_ttv_nhan"
                                                   styleId="ma_ttv_nhan"
                                                   styleClass="fieldTextCode" 
                                                   tabindex="103" onkeydown="nextElementFocusKDTG(event);" 
                                                   onkeypress="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                   style="WIDTH:94%;height:100%;"
                                                   />
                                                   <html:hidden property="ttv_nhan" styleId="ttv_nhan"/>
                                              </td>
                                              <td width="25%">
                                                <html:text property="ten_ttv_nhan" 
                                                           onkeydown="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                           readonly="readonly"
                                                           styleClass="fieldTextTrans" 
                                                           onmouseout="UnTip()"                                                           
                                                           onmouseover="Tip(this.value)" 
                                                           style="WIDTH:95%;border:0px;font-weight:bold;" 
                                                           styleId="ten_ttv_nhan"/>
                                              </td>
                                              <td width="15%">
                                                <fmt:message key="TGGD.page.label.ngaylap"/>
                                              </td>
                                              <td width="10%" colspan="2">
                                                <html:text property="ngay_nhan"
                                                   styleId="ngay_nhan" onkeydown="nextElementFocusKDTG(event);" 
                                                   styleClass="fieldTextCode" tabindex="104"
                                                   onkeypress="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                   style="WIDTH:94%;height:100%;"
                                                   />
                                              </td>
                                           </tr>
                                            <tr style="width:100%">
                                              <td width="15%">
                                                <fmt:message key="TGGD.page.label.nguoiks"/>
                                              </td>
                                              <td width="10%">
                                                <html:text property="ma_ktt"
                                                   styleId="ma_ktt" onkeydown="nextElementFocusKDTG(event);" 
                                                   styleClass="fieldTextCode"  tabindex="105"
                                                   onkeypress="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                   style="WIDTH:94%;height:100%;"
                                                   />
                                                   <html:hidden property="ktt_duyet" styleId="ktt_duyet"/>
                                              </td>
                                              <td width="25%">
                                                <html:text property="ten_ktt_duyet" 
                                                           onkeydown="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                           readonly="readonly"
                                                           styleClass="fieldTextTrans" 
                                                           onmouseout="UnTip()"                                                           
                                                           onmouseover="Tip(this.value)" 
                                                           style="WIDTH:95%;border:0px;font-weight:bold;" 
                                                           styleId="ten_ktt_duyet"/>
                                              </td>
                                              <td width="15%">
                                                <fmt:message key="TGGD.page.label.ngayks"/>
                                              </td>
                                              <td width="10%" colspan="2">
                                                <html:text property="ngay_duyet"
                                                   styleId="ngay_duyet" tabindex="106"
                                                   styleClass="fieldTextCode" onkeydown="nextElementFocusKDTG(event);" 
                                                   onkeypress="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                   style="WIDTH:94%;height:100%;"
                                                   />
                                              </td>
                                           </tr>
                                           <tr style="width:100%">
                                              <td width="15%">
                                                <fmt:message key="TGGD.page.label.noidung"/>
                                              </td>
                                              <td colspan="5">
                                                <%--<html:text property="ma_don_vi_tra_soat"
                                                   styleId="ma_don_vi_tra_soat"
                                                   styleClass="fieldTextCode" tabindex="101"
                                                   onkeypress="nextElementFocusKDTG(event);arrowUpDownKDTG(event)"
                                                   style="WIDTH:100%;height:100%;"
                                                   />--%>
                                                   <html:textarea property="noidung" 
                                                     styleId="noidung" cols="3"  tabindex="107"                                            
                                                     rows="5" style="width:99.5%;WRAP:HARD" 
                                                     onkeydown="nextElementFocusKDTG(event);" 
                                                     styleClass="fieldTextArea"></html:textarea>
                                              </td>
                                           </tr>
                                           <tr style="width:100%">
                                              <td width="15%">
                                                <fmt:message key="TGGD.page.label.thoigian"/>
                                              </td>
                                              <td colspan="5">
                                                <fmt:message key="TGGD.page.label.ngay"/>
                                                   <html:text styleId="thoi_gian_keo_dai" onkeypress="return numbersonly(this,event,true) " tabindex="108"
                                                       onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('thoi_gian_keo_dai');"
                                                      styleClass="fieldText" property="thoi_gian_keo_dai" style="width:10%;" maxlength="10"
                                                      onkeydown="nextElementFocusKDTG(event);"  />
                                                      <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0"
                                                      id="thoi_gian_keo_dai_btn" width="20" style="vertical-align:middle;"/>
                                                      <script type="text/javascript">
                                                        Calendar.setup( {
                                                        inputField : "thoi_gian_keo_dai", // id of the input field
                                                        ifFormat : "%d/%m/%Y", // the date format
                                                        button : "thoi_gian_keo_dai_btn"// id of the button
                                                        });
                                                      </script>
                                                <fmt:message key="TGGD.page.label.gio"/>
                                                    <html:text styleId="gio_keo_dai" maxlength="2" tabindex="108" onkeypress="return numbersonly(this,event,true) "
                                                      styleClass="fieldText" property="gio_keo_dai" style="width:4%;"
                                                      onkeydown="nextElementFocusKDTG(event);"  />
                                                <fmt:message key="TGGD.page.label.phut"/>
                                                  <html:text styleId="phut_keo_dai" maxlength="2" tabindex="108" onkeypress="return numbersonly(this,event,true) "
                                                      styleClass="fieldText" property="phut_keo_dai" style="width:4%;"
                                                     onkeydown="nextElementFocusKDTG(event);"  />
                                              </td>
                                           </tr>
                                            <tr style="width:100%">
                                              <td width="15%">
                                                <fmt:message key="TGGD.page.label.noidungtl"/>
                                              </td>
                                              <td colspan="5">
                                              <input type="radio" tabindex="109" onclick="handleClick(this);" name="noidung_traloi" id="noidung_traloiY" value="Y"> 
                                                 <fmt:message key="TGGD.page.label.noidungtl.chapnhan"/>
                                              <input type="radio"  tabindex="110" onclick="handleClick(this);" name="noidung_traloi" id="noidung_traloiN" value="N"> 
                                                <fmt:message key="TGGD.page.label.noidungtl.kochapnhan"/>
                                                <input type="hidden" id="noidung_traloi_hidden"/>
                                              </td>
                                           </tr>
                                           <tr style="width:100%">
                                              <td width="15%">
                                                <fmt:message key="TGGD.page.label.lydo"/>
                                              </td>
                                              <td colspan="5">
                                                <html:textarea property="lydo_ktt_day_lai" 
                                                     styleId="lydo_ktt_day_lai" cols="3"                                             
                                                     rows="5" style="width:99.5%;WRAP:HARD" tabindex="111"
                                                     onkeydown="nextElementFocusKDTG(event);"
                                                     styleClass="fieldTextArea"></html:textarea>
                                              </td>
                                           </tr>
                                        </tbody>
                                      </table>
                                    </div>
                                  </fieldset>
                          </td>
                        </tr>
                      </tbody>
                    </html:form>
                    </table>
                  </td>
                </tr>
                <tr align="right" style="width:auto">
                  <td height="30" align="right" valign="middle">
                    <div>
                      <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%">
                        <tr align="right">
                          <td>
                            <span id="tao">
                              <button 
                                      id="btnTao"  class="ButtonCommon"
                                      accesskey="T" type="button">
                                <fmt:message key="TGGD.page.button.taodien"/>
                              </button></span>
                            <span id="traloi">
                              <button 
                                      id="btnTraLoi"  class="ButtonCommon"
                                      accesskey="T" type="button">
                                <fmt:message key="TGGD.page.button.traloi"/>
                              </button></span>
                             <span id="xacnhan">
                              <button 
                                      id="btnXacNhan"  class="ButtonCommon"
                                      accesskey="T" type="button">
                                <fmt:message key="TGGD.page.button.xacnhan"/>
                              </button></span>
                            <!--<span id="sua"> 
                              <button 
                                      id="btnSua" class="ButtonCommon" type="button"
                                      accesskey="S">
                                <fmt:message key="TGGD.page.button.sua"/>
                              </button>
                               </span>-->
                               <span id="ghi"> 
                              <button 
                                      id="btnGhi" tabindex="103" class="ButtonCommon"
                                      type="button" accesskey="G">
                                <fmt:message key="TGGD.page.button.ghi"/>
                              </button>
                               </span>
                             <!--<span id="huy"> 
                              <button 
                                      id="btnHuy"  class="ButtonCommon"
                                      type="button" accesskey="H">
                                <fmt:message key="TGGD.page.button.huy"/>
                              </button>
                               </span>-->
                            <!--<span id="duyet"> 
                              <button 
                                      id="btnDuyet" class="ButtonCommon" type="button"
                                      accesskey="U">
                                <fmt:message key="TGGD.page.button.duyet"/>
                              </button>
                               </span>-->
                             
                            <!--<span id="dayLai"> 
                              <button 
                                      id="btnDayLai"  class="ButtonCommon"
                                      type="button" accesskey="A">
                                <fmt:message key="TGGD.page.button.daylai"/>
                              </button>
                               </span>-->
                             
                            <span id="thoat"> 
                              <button 
                                      id="btnExit"  class="ButtonCommon"
                                      type="button" accesskey="O">
                                <fmt:message key="TGGD.page.button.thoat"/>
                              </button>
                               </span>
                             
                            <span> 
                              <input style="WIDTH: 1px; HEIGHT: 1px" type="hidden"
                                     name="thebottom"/>
                               </span>
                          </td>
                        </tr>
                      </table>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
      </table>
<div id="dialog-confirm"
     title='<fmt:message key="TGGD.page.title.dialog_confirm"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>
<div id="dialog-message-check"
     title='<fmt:message key="TGGD.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>