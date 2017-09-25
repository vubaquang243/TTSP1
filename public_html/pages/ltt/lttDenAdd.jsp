<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys" %>
<%@ page import="com.seatech.framework.AppConstants" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.seatech.ttsp.ltt.LTTVO"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.LTTDiResource" />
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css" />
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/ltt.quyettoan.check.valid.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/LenhThanhToan.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/date.format.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<%
  String lydo_ngatketnoi ="";
  if(request.getAttribute("LyDoNgatKetNoi")!=null){
    lydo_ngatketnoi = (String)request.getAttribute("LyDoNgatKetNoi");
  }
  String ngat_ketnoi ="";
  if(request.getAttribute("ngatkn")!=null){
    ngat_ketnoi = (String)request.getAttribute("ngatkn");    
  }
  String strMa_nhkb_nhan_cm = "";
  if(request.getAttribute("Ma_nhkb_nhan_cm") != null){
    strMa_nhkb_nhan_cm = (String)request.getAttribute("Ma_nhkb_nhan_cm");
  }
  String strTen_nhkb_nhan_cm = "";
  if(request.getAttribute("Ten_nhkb_nhan_cm") !=null)
    strTen_nhkb_nhan_cm = (String)request.getAttribute("Ten_nhkb_nhan_cm");
  String strNhkb_nhan = "";
  if(request.getAttribute("Nhkb_nhan") != null)
    strNhkb_nhan = request.getAttribute("Nhkb_nhan").toString();
  
  String strQuyenLttDen ="";  
  if(request.getAttribute("QuyenLttDen") != null)
    strQuyenLttDen = request.getAttribute("QuyenLttDen").toString().toUpperCase();
  String strMsgLTTDen ="";
  String strUserType = "";  
  String strUserId = "";
  String strLastAction = "";
  String strTrangThai = "";
  String strLastForm = "";
  String strSoLTTDen = "";
  String strErrorAt = "";
  String strErrorAtCol = "";
  String strErrorAtRow = "0";
  String strErrDescription = "";
  String strActionParam = "";
  String strSpecialTKTN = "";
  String strTKTNAnNinh = "";
  if(request.getAttribute("StrTKTNAnNinh") != null)
    strTKTNAnNinh = request.getAttribute("StrTKTNAnNinh").toString();  
  if(request.getAttribute("StrSpecialTKTN") != null)
    strSpecialTKTN = request.getAttribute("StrSpecialTKTN").toString();
  if(request.getParameter("action") != null){
    strActionParam = request.getParameter("action");
  }
  if(request.getAttribute("MsgErrDesLTTDen") != null)
    strErrDescription = request.getAttribute("MsgErrDesLTTDen").toString();  
  if(request.getAttribute("MsgDialogLTTDen") != null)
    strMsgLTTDen = request.getAttribute("MsgDialogLTTDen").toString();
  if(request.getAttribute("LastAction") != null)
    strLastAction = request.getAttribute("LastAction").toString();
  if(request.getAttribute("TrangThai") != null)
    strTrangThai = request.getAttribute("TrangThai").toString();
  if(request.getAttribute("LastForm") != null)
    strLastForm = request.getAttribute("LastForm").toString();  
  if(request.getAttribute("SoLTTDen") != null)
    strSoLTTDen = request.getAttribute("SoLTTDen").toString();
  if(request.getAttribute("ErrorAt") != null){
    strErrorAt = request.getAttribute("ErrorAt").toString();
    if(strErrorAt.contains("-")){
      String[] arrTmp = strErrorAt.split("-");
      if(arrTmp.length == 2){
        strErrorAtCol = arrTmp[0];
        strErrorAtRow = arrTmp[1];
      }
    }
  }    
  if(session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) != null){
    strUserType = session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
  }
  if(session.getAttribute(AppConstants.APP_USER_ID_SESSION) != null){
    strUserId = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
  }
  String strNHKB_COA = "";
  if(session.getAttribute(AppConstants.APP_KB_CODE_SESSION) != null){
    strNHKB_COA = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
  }
%>
<input type="hidden" id="row_select" >
<div class="box_common_content">
<%if(!strUserType.equalsIgnoreCase(AppConstants.NSD_TTV)){%>
  <object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
<%}%>
   <html:form action="/lttDenUpdateExc.do" method="post">      
      <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">Lệnh thanh toán đến</span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
        </tbody>
      </table>      
      <!-table vien xung quanh-->
       <table class="tableBound" width="100%">
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
            <%
              if(ngat_ketnoi!=null && ""!=ngat_ketnoi){
            %>
                    <table width="99%">
                      <tbody>
                      <tr>
                        <td><font color="red">
                            <fmt:message key="ltt_di.page.label.ngat_ketnoi"/> (<%=lydo_ngatketnoi%>)
                          </font> 
                        </td></tr>
                      </tbody>
                    </table>      
            <%}%>
        </td>
       </tr>
      <tr>
      <td>
      
      <table cellspacing="0" cellpadding="0" width="100%">
        <tbody>
          <tr style="height:360px">
            <td valign="top">   
                  <table class="bordertableChungTu" cellspacing="0" cellpadding="0"
                  width="100%">
                    <tbody>
                      <tr valign="top">
                        <td valign="top" width="15%" rowspan="2">
                            <div class="clearfixLTT">
                            <fieldset class="fieldsetLTTCSS" style="height:380px">
                              <legend style="vertical-align:middle">
                                <b>Số lệnh thanh toán</b>
                              </legend>
                              <div class="sodientrasoattableLTT">
                                    <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                                      <thead>
                                        <tr>
                                          <th class="ui-state-default ui-th-column" height="20" width="62%">
                                           Số lệnh TT
                                          </th>
                                          <th width="28%;" class="ui-state-default ui-th-column">
                                           <fmt:message key="ltt_di.page.label.tinh_trang_master" />
                                          </th>
                                          <th height="20" width="10%;"
                                              class="ui-state-default ui-th-column">
                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          </th>
                                        </tr>
                                      </thead>
                                    </table>
                                  <div style="height:270px;" class="ui-jqgrid-bdiv ui-widget-content">
                                  <div id="loading" style="display:none; padding-top:50px; ">
                                    <p align="center"><img src="<%=request.getContextPath()%>/styles/images/ajax-loader.gif" /> <br/> Please Wait</p>
                                  </div>
                                  <div style="position: relative;">
                                    <table  class="data-grid" id="data-grid"
                                              style="border:0px;"
                                             cellspacing="0" cellpadding="0"                                  
                                             width="100%">
                                      <tbody>
                                        <logic:present name="<%=AppKeys.LTT_LIST_REQUEST_KEY%>">
                                          <logic:iterate id="list_ltt" name="<%=AppKeys.LTT_LIST_REQUEST_KEY%>" indexId="index" type="com.seatech.ttsp.ltt.LTTVO">
                                          <tr class="ui-widget-content jqgrow ui-row-ltr" id="row_ltt_<bean:write name="index"/>" 
                                            ondblclick="rowSelectedSetFocus('row_ltt_<bean:write name="index"/>');"
                                            onclick="loadDetailLTTJson('loadLTTDenJsonAction.do', '<bean:write name="list_ltt" property="id"/>','row_ltt_<bean:write name="index"/>', '<%=strUserType%>');"
                                            onmouseover="lttMouseOver('row_ltt_<bean:write name="index"/>');"
                                            onmouseout="lttMouseOut('row_ltt_<bean:write name="index"/>');"
                                             >
                                            <td width="44%;" align="left">
                                              <input name="row_item" id="<bean:write name="index"/>" type="text" 
                                                value="<bean:write name="list_ltt" property="id"/>" 
                                                onkeydown="arrowUpDownLTT(event);" readonly="true"
                                                style="border:0px;font-size:10px;float:left;" />
                                              <input  id="rowSelected" type="hidden" 
                                                value="<bean:write name="list_ltt" property="id"/>" />
                                            </td>
                                            <td  width="30%;" align="center">
                                              <logic:equal value="01" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg" border="0" title="KTT đẩy lại"/>
                                              </logic:equal>
                                              <logic:equal value="02" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/edit.gif" border="0" title="Chờ TTV hoàn thiện"/>
                                              </logic:equal>
                                              <logic:equal value="03" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" border="0" title="Chờ KTT duyệt"/>
                                              </logic:equal>                                  
                                              <logic:equal value="04" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg" border="0" title="GĐ đẩy lại"/>
                                              </logic:equal>
                                              <logic:equal value="05" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait-gd.png" border="0" title="Chờ GĐ duyệt"/>
                                              </logic:equal>
                                              <logic:equal value="06" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/delete1.gif" border="0" title="Hủy"/>
                                              </logic:equal>
                                              <logic:equal value="07" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-but.jpg" border="0" title="Đã gửi - Chưa vào giao diện"/>
                                              </logic:equal>
                                              
                                              <logic:equal value="11" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-but.jpg" border="0" title="Đã gửi - Chờ chạy giao diện"/>
                                              </logic:equal>
                                              <logic:equal value="12" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/send-success.jpg" border="0" title="Đã gửi - Giao diện thành công"/>
                                              </logic:equal>
                                              <logic:equal value="13" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg" border="0" title="Đã gửi - Giao diện thất bại"/>
                                              </logic:equal>
                                              <logic:equal value="15" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg" border="0" title="Gửi TABMIS không thành công"/>
                                              </logic:equal>
                                              <logic:equal value="16" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg" border="0" title="Lỗi đường truyền"/>
                                              </logic:equal>
                                            </td>
                                          </tr>
                                          </logic:iterate>
                                        </logic:present>
                                        <logic:empty name="<%=AppKeys.LTT_LIST_REQUEST_KEY%>" >
                                          <tr>
                                            <td colspan="5" align="center">
                                              <span class="text_red">Chưa có dữ liệu</span>
                                            </td>
                                          </tr>
                                        </logic:empty>
                                     </tbody>
                                    </table>
                                </div>
                              </div>
                              <div style="width:160px;">
                                  <div style="width:160px;padding-top:5px;float: left;">
                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                      <tr>
                                        <td width="92%" align="left">
                                          <fmt:message key="ltt_di.page.label.tinh_trang_master"/>: <span id="mo_ta_trang_thai" style="font-weight:bold;"></span>
                                        </td>
                                        <td id="refreshButton" width="8%" align="right">
                                          <span id="refresh" class="ui-icon ui-icon-refresh" title="Làm mới" onclick="refreshGridLTT('<%=strUserType%>', 'listLttDenAdd.do', 'loadLTTDenJsonAction.do');" style="cursor:pointer;"></span>
                                        </td>
                                        <td>
                                          <span id="search" class="ui-icon ui-icon-search" title="Tìm kiếm" style="cursor:pointer;"></span>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td align="left" colspan="3">
                                        Loại tiền: <html:select property="nt_id_tke_tong" styleId="nt_id_tke_tong" onchange="changeMaNTThongKeTong(this,'getTgMonTgTienLTTDenAction.do');" style="width:95px;height:20px;vlaign:middle" styleClass="fieldTextCode" >
                                          <%--<html:optionsCollection name="listDMTienTe" value="id" label="ma" />
                                          onfocus="this.defaultIndex=this.selectedIndex;" onchange="this.selectedIndex=this.defaultIndex;"
                                          --%>
                                        <html:option value="177">VND</html:option>                                    
                                        <logic:notEmpty name="listDMTienTe">
                                          <logic:present name="listDMTienTe">
                                            <logic:iterate id="objNT" name="listDMTienTe" type="com.seatech.ttsp.dmtiente.DMTienTeVO" indexId="index">                                              
                                              <logic:notEqual value="2" name="objNT" property="id" >
                                                <html:option value="<%=objNT.getId().toString()%>"><bean:write name="objNT" property="ma"/></html:option>                                    
                                              </logic:notEqual>
                                            </logic:iterate>  
                                          </logic:present>
                                        </logic:notEmpty>
                                      </html:select>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td align="left" colspan="3">
                                          Tổng món : <html:text property="tong_so_mon" styleId="tong_so_mon" styleClass="fieldTextTrans" readonly="true" onmouseout="UnTip()" 
                                                    onmouseover="Tip(this.value)" style="WIDTH: 60%;"/>      
                                        </td>
                                                  
                                      </tr>
                                      <tr >
                                        <td colspan="3"  align="left">
                                          Tổng tiền : 
                                       
                                          <html:text property="tong_so_tien" styleId="tong_so_tien" styleClass="fieldTextTrans" readonly="true" onmouseout="UnTip()" 
                                                    onmouseover="Tip(this.value)" style="WIDTH: 60%;"/>     
                                        </td>
                                      </tr>
                                    </table>
                                                                
                                  </div>
                                </div>
                              </div>
                             </fieldset>
                            </div>
                        </td>
                      </tr>
                    </tbody>
                    </table>
              
            </td>
            <td width="85%" style="padding-bottom:2px;" height="100%" valign="top">
              <fieldset style="width:auto;height:70%">
                <legend style="vertical-align:middle;"><b>Thông tin chung</b></legend>
                <div style="width:auto; height: auto;">
                  <table  width="100%" cellspacing="0" cellpadding="1" style="width:100%; border: 0px solid #8eb9e5;" align="center" >
                    <tbody>
                      <tr>
                        <td width="16%" align="right" class="textNormal">NH/KB chuyển</td>
                        <td width="34%" align="left">
                          <html:text property="ma_nhkb_chuyen_cm" styleId="ma_nhkb_chuyen_cm" maxlength="8" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                            onblur="getTenNganHang('ma_nhkb_chuyen_cm', 'ten_nhkb_chuyen_cm', 'nhkb_chuyen')" style="WIDTH: 30%;"/>
                          <html:text property="ten_nhkb_chuyen_cm" styleId="ten_nhkb_chuyen_cm" styleClass="fieldTextTrans" readonly="true" onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 64%;"/>
                          <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen" />
                        </td>
                        <td width="14%" align="right">NH/KB nhận</td>
                        <td colspan="3" align="left">
                          <html:text property="ma_nhkb_nhan_cm" styleId="ma_nhkb_nhan_cm" maxlength="8" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                            onblur="getTenNganHang('ma_nhkb_nhan_cm', 'ten_nhkb_nhan_cm', 'nhkb_nhan')" style="WIDTH: 55px;"/>&nbsp;
                          <html:text property="ten_nhkb_nhan_cm" styleId="ten_nhkb_nhan_cm" styleClass="fieldTextTrans" readonly="true" onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 76%;"/>
                          <html:hidden property="nhkb_nhan" styleId="nhkb_nhan" />
                        </td>
                      </tr>
                      <tr>
                        <td align="right" >Số tham chiếu</td>
                        <td width="20%" align="left">
                          <html:text property="so_tham_chieu_gd" styleId="so_tham_chieu_gd" maxlength="20" styleClass="fieldTextCode" onmouseout="UnTip()" 
                          onmouseover="Tip(this.value)" onkeydown="skipTabReadonlyField(event, 'ndung_tt');" 
                          onfocus="autoSelect(this);"
                          style="WIDTH: 120px;"/>
                        </td>
                        <td align="right">Số LTT</td>
                        <td width="17%" align="left">
                          <html:text property="id" styleId="id" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                          style="WIDTH: 110px;"/>
                        </td>
                        <td width="15%" align="right">Ngày TT</td>
                        <td width="20%" align="left">
                          <html:text property="ngay_tt" styleId="ngay_tt" styleClass="fieldTextCode" onmouseout="UnTip()" 
                          onmouseover="Tip(this.value)" style="WIDTH: 97px"></html:text>                          
                        </td>
                      </tr>
                      <tr>
                        <td align="right" >Người kiểm soát</td>
                        <td width="30%" align="left">
                          <html:text property="nguoi_ks_nh" styleId="nguoi_ks_nh" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>                              
                          <html:text property="ten_nguoi_ks_nh" styleId="ten_nguoi_ks_nh" styleClass="fieldTextTrans" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>
                        </td>
                        <td align="right">Ngày kiểm soát</td>
                        <td colspan="3" align="left">
                          <html:text property="ngay_ks_nh" styleId="ngay_ks_nh" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 110px;"/>
                        </td>
                      </tr>                     
                      <tr >
                        <td  align="right" >Tổng số tiền</td>
                        <td width="30%"  align="left">
                          <html:text property="tong_sotien"  styleId="tong_sotien" styleClass="fieldTextRightCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 180px;"/>                              
                        </td>
                        <td rowspan="2" align="right" >Nội dung TT</td>
                        <td  colspan="3" rowspan="2" align="left" valign="top">
                          <html:textarea property="ndung_tt"  styleId="ndung_tt" styleClass="fieldTextAreaRO" rows="3"   onkeydown="skipTabReadonlyField(event, 'ten_tk_chuyen');" style="WIDTH: 99%;height: 98%;"></html:textarea>
                        </td>
                      </tr>  
                      <tr>
                        <td  align="right" >Mã NT</td>
                        <td  width="30%" colspan="5" align="left">
                          <html:select property="nt_id" styleId="nt_id" style="width:100px;height:20px;" styleClass="fieldTextCode">                            
                            <logic:notEmpty name="listDMTienTe">
                              <logic:present name="listDMTienTe">
                                <logic:iterate id="objNT" name="listDMTienTe" type="com.seatech.ttsp.dmtiente.DMTienTeVO" indexId="index">
                                  <logic:equal value="2" name="objNT" property="id" >
                                    <option value="<%=objNT.getId().toString()%>" selected="selected"><%=objNT.getMa()%></option>
                                  </logic:equal>
                                  <logic:notEqual value="2" name="objNT" property="id" >
                                    <html:option value="<%=objNT.getId().toString()%>"><bean:write name="objNT" property="ma"/></html:option>                                    
                                  </logic:notEqual>
                                </logic:iterate>  
                              </logic:present>
                            </logic:notEmpty>
                          </html:select>
                          <input type="hidden" id="rpt_nt_id" name="rpt_nt_id" />
                        </td>
                      </tr> 
                      <tr>
                        <td align="right" >TTV Hoàn thiện</td>
                        <td width="30%" align="left">
                          <html:text property="ma_ttv_nhan" styleId="ma_ttv_nhan" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>
                          <html:text property="ten_ttv_nhan" styleId="ten_ttv_nhan" styleClass="fieldTextTrans" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>
                          <html:hidden property="ttv_nhan" styleId="ttv_nhan"/>
                        </td>
                        <td align="right">Ngày hoàn thiện</td>
                        <td colspan="3" align="left">
                          <html:text property="ngay_hoan_thien" styleId="ngay_hoan_thien" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 110px;"/>
                        </td>
                      </tr>
                      <tr>
                        <td align="right" >KTT</td>
                        <td width="30%" align="left">
                          <html:text property="ma_ktt_duyet" styleId="ma_ktt_duyet" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>
                          <html:text property="ten_ktt_duyet" styleId="ten_ktt_duyet" styleClass="fieldTextTrans" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>
                          <html:hidden property="ktt_duyet" styleId="ktt_duyet" />                              
                        </td>
                        <td align="right">Ngày kiểm soát</td>
                        <td colspan="3" align="left">
                          <html:text property="ngay_ktt_duyet" styleId="ngay_ktt_duyet" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 110px;"/>
                        </td>
                      </tr>                      
                    </tbody>
                  </table> 
                </div>
              </fieldset>
              <fieldset style="width:auto;height:20.5%">
                <legend style="vertical-align:middle"><b>Thông tin người chuyển</b></legend>
                  <div style="width:auto; ">
                    <table style="width:100%; border: 0px solid #8eb9e5;" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="10%" align="right">
                          Đơn vị chuyển
                        </td>
                        <td width="30%" align="left">
                          <html:text property="ten_tk_chuyen" styleId="ten_tk_chuyen" styleClass="fieldTextCode" style="width:93%;"/>
                        </td>
                        <td width="10%"  align="right" valign="middle">
                           Thông tin
                        </td>
                        <td width="40%"  align="left" valign="top">
                          <html:textarea style="width:98%;height:18px;" cols="50" rows="3" property="ttin_kh_chuyen" styleId="ttin_kh_chuyen" styleClass="fieldTextAreaRO" onkeydown="skipTabReadonlyField(event, 'ma_nhkb_chuyen');"></html:textarea>
                        </td> 
                      </tr>
                      <tr>
                        <td width="10%" align="right">
                           Tài khoản
                        </td>
                        <td width="60%" colspan="3" align="left">
                          <html:text property="so_tk_chuyen" styleId="so_tk_chuyen" styleClass="fieldTextCode" style="width:90px;"/>
                          Mở tại NH/KB
                          <html:text property="ma_nhkb_chuyen" styleId="ma_nhkb_chuyen" maxlength="8" styleClass="fieldTextCode" 
                            onmouseout="UnTip()" onmouseover="Tip(this.value)" onblur="getTenNganHang('ma_nhkb_chuyen', 'ten_nhkb_chuyen', 'id_nhkb_chuyen')" 
                            onkeydown="skipTabReadonlyField(event, 'tkkt_ma');" style="WIDTH: 51px;"/>
                          <html:text property="ten_nhkb_chuyen" styleId="ten_nhkb_chuyen" styleClass="fieldTextTrans" 
                          onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 380px;"/>
                          <html:hidden property="id_nhkb_chuyen" styleId="id_nhkb_chuyen" />
                        </td>                  
                      </tr>
                    </table>    
                  </div>
              </fieldset>
              <fieldset style="width:auto;height:20.5%">
                <legend style="vertical-align:middle"><b>Thông tin người nhận</b></legend>
                  <div style="width:auto; ">
                    <table style="width:100%; border: 0px solid #8eb9e5;" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="10%" align="right">
                          Đơn vị nhận
                        </td>
                        <td width="30%" align="left">
                          <html:text property="ten_tk_nhan" styleId="ten_tk_nhan" styleClass="fieldTextCode" style="width:93%;"/>
                        </td>
                        <td width="10%"  align="right" valign="middle">
                           Thông tin
                        </td>
                        <td width="40%"  align="left" valign="top">
                          <html:textarea style="width:98%;height:18px;" cols="50" rows="3" property="ttin_kh_nhan" 
                          styleId="ttin_kh_nhan" styleClass="fieldTextAreaRO" onkeydown="skipTabReadonlyField(event, 'ma_nhkb_nhan');"></html:textarea>
                        </td> 
                      </tr>
                      <tr>
                        <td width="10%" align="right">
                           Tài khoản
                        </td>
                        <td width="60%" colspan="3" align="left">
                          <html:text property="so_tk_nhan" styleId="so_tk_nhan" styleClass="fieldTextCode" style="width:90px;"/>
                          Mở tại NH/KB
                          <html:text property="ma_nhkb_nhan" styleId="ma_nhkb_nhan" maxlength="8" styleClass="fieldTextCode" 
                            onmouseout="UnTip()" onmouseover="Tip(this.value)" onblur="getTenNganHang('ma_nhkb_nhan', 'ten_nhkb_nhan', 'id_nhkb_nhan')" 
                            onkeydown="skipTabReadonlyField(event, 'loai_hach_toan');" style="WIDTH: 51px;"/>
                          <html:text property="ten_nhkb_nhan" styleId="ten_nhkb_nhan" styleClass="fieldTextTrans" onmouseout="UnTip()" 
                          onmouseover="Tip(this.value)" style="WIDTH: 390px;"/>
                          <html:hidden property="id_nhkb_nhan" styleId="id_nhkb_nhan" />
                        </td>                  
                      </tr>
                    </table>    
                  </div>
              </fieldset>
              <table style="width:99.5%; border: 1px solid rgb(197,196,202);" cellpadding="0" align="center" cellspacing="0">
                <tr>
                  <td width="14%" align="right" valign="middle">
                    P.Á hạch toán
                  </td>
                  <td width="10%" align="left">
                    <select name="loai_hach_toan" onchange="enableLyDoHT();" id="loai_hach_toan"  class="fieldTextCode" style="width:120px;height:20px;">
                      <option value="01" selected="true">Hạch toán đúng</option>
                      <option value="02">Chờ xử lý </option>
                    </select>
                    <html:hidden property="loai_hach_toan" styleId="loai_hach_toan_selected"/>
                  </td>
                  <td width="15%" align="right" valign="middle">
                     Lý do KTT đẩy lại
                  </td>
                  <td width="60%" align="left">
                    <html:textarea cols="40" style="width:98%" rows="1" property="lydo_ktt_day_lai" styleId="lydo_ktt_day_lai" styleClass="fieldTextAreaRO" onkeydown="arrowUpDownLTT(event);"></html:textarea>
                  </td> 
                </tr>
                <tr>
                  <td  colspan="3" align="right" valign="middle">
                    Lý do hạch toán
                  </td>
                  <td  align="left">
                    <html:textarea cols="40" rows="1" style="width:98%" property="ly_do_htoan" styleId="ly_do_htoan" styleClass="fieldTextAreaRO" onkeydown="arrowUpDownLTT(event);"></html:textarea>
                  </td>
                </tr>
              </table>                    
          
              <table style="width:100%; border: 0px; margin-top:5px;margin-bottom:5px;">
                  <tr>
                    <td width="15%" align="right" valign="middle">
                      <button id="save" onclick="checkSuccess('lttDenAdd');" class="ButtonCommon" type="button" accesskey="G"><span class="sortKey">G</span>hi</button>                      
                      <button id="completeLtt" class="ButtonCommon" type="button" style="width:100px;" accesskey="H"><span class="sortKey">H</span>oàn thiện</button>
                      <button id="rejectLtt" class="ButtonCommon" type="button" style="width:100px;" accesskey="L">Đẩy <span class="sortKey">l</span>ại</button>
                      <button id="approved" class="ButtonCommon" type="button" style="width:100px;" accesskey="D"><span class="sortKey">D</span>uyệt</button>
                       <%
                      String url = request.getContextPath()+"/report/"+request.getSession().getAttribute(AppConstants.APP_USER_ID_SESSION)+"ltt.xls";
                       %>
                     
                      <!--<button id="inChungTu" class="ButtonCommon" type="button" style="width:70pt;" accesskey="I" onclick="startDownload('<%=url%>')"><span class="sortKey">I</span>n chứng từ</button>-->
                      <span id="in"> 
                                    <button onclick="formAction()"
                                            id="btnIn" class="ButtonCommon"
                                            type="button">
                                      <fmt:message key="ltt_di.page.button.in"/>
                                    </button>
                      </span>
                      <!--<button id="search" class="ButtonCommon" type="button" accesskey="T"><span class="sortKey">T</span>ìm kiếm</button>-->
                      <button id="exit" class="ButtonCommon" type="button" accesskey="o">Th<span class="sortKey">o</span>át</button>                     
                    </td> 
                  </tr>
                </table>
            </td>
          </tr>        
          
          <tr>
            <td width="100%" colspan="4" align="center">
               <div style="width:auto; ">
                <table style="width:100%; border: 1px solid #8eb9e5;border-bottom-color:#ffffff" cellpadding="0" cellspacing="0">                           
                  <tr>
                    <td width="20%" align="left">
                       <b>Chi tiết các khoản mục</b>
                    </td>
                    <td  align="right" id="tdIdTraLai">
                      Số ID lệnh gốc :
                      <input type="text" id="idTraLai"/>
                    </td>
                    <td width="25%" align="left"><button id="tralai" class="ButtonCommon" onclick="createCOAFromId()" type="button" accesskey="T" style="width:200px;"> Lấy <span class="sortKey">C</span>OA từ lệnh gốc</button></td>
                    <td width="2%" align="right">
                        <button type="button" name="cmdAddRow" id="cmdAddRow" onmouseover="Tip('Thêm mới (Alt +)')" onmouseout="UnTip()" accesskey="=">+</button>
                    </td>
                  </tr>
                   <tr>
                      <td width="100px;" align="left" colspan="2">                          
                         DVQHNS : <input type="text" id="so_tk_dvnhan" class="fieldTextTrans" style="width:250px;"/> 
                      </td>
                  </tr>
                </table>
              </div>
              </td>
            </tr>
          <tr>
            <td width="100%" colspan="4" align="center">
                <div style="overflow-y:auto; height:120px; width:100%;">
                   <table id="tblThongTinChiTietCOA" style="width:100%; border: 1px solid #8eb9e5;" cellpadding="0" cellspacing="0">                           
                    <tbody>
                        <tr>
                          <th width="5%" align="center">
                             Mã quỹ
                          </th>
                          <th width="8%" align="center">
                             TK tự nhiên
                          </th>
                          <th width="6%" align="center">
                             DVQHNS
                          </th>
                          <th width="5%" align="center">
                             Cấp NS
                          </th>
                          <th width="5%" align="center">
                             Chương
                          </th>
                          <th width="7%" align="center">
                             Ngành KT
                          </th>
                          <th width="5%" align="center">
                             NDKT
                          </th>
                          <th width="5%" align="center">
                             ĐB
                          </th>
                          <th width="5%" align="center">
                             CTMT
                          </th>
                          <th width="5%" align="center">
                             MN
                          </th>
                          <th width="5%" align="center">
                             Kho bạc
                          </th>
                          <th width="5%" align="center">
                             DP
                          </th>
                          <th width="15%" align="center">
                             Diễn giải
                          </th>
                          <th width="15%" align="center">
                             Số tiền
                          </th>
                          <th width="4%">
                            Xóa
                          </th>
                        </tr>
                        <logic:empty name="colLTTCTiet">
                        <tr id="coa_0">
                          <td align="center">
                             <html:text property="ma_quy" styleId="ma_quy" onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="2"/>
                          </td>
                          <td align="center" id="tkktma">
                             <html:text property="tkkt_ma" styleId="tkkt_ma" onkeypress="return numberBlockKey1();" 
                             onblur="checkDMTKCheo(this, this.parentNode.parentNode, true);" styleClass="fieldTextCenterCode" maxlength="4"/>
                          </td>
                          <td align="center">
                             <html:text property="dvsdns_ma" styleId="dvsdns_ma" onkeypress="return numberBlockKey1();"  
                             styleClass="fieldTextCenterCode" maxlength="7"/>
                          </td>
                          <td align="center">
                             <html:text property="capns_ma" styleId="capns_ma" onkeypress="return numberBlockKey1();"  
                             styleClass="fieldTextCenterCode" maxlength="1"/>
                          </td>
                          <td align="center">
                             <html:text property="chuongns_ma" styleId="chuongns_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="3"/>
                          </td>
                          <td align="center">
                             <html:text property="nganhkt_ma" styleId="nganhkt_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="3"/>
                          </td>
                          <td align="center">
                             <html:text property="ndkt_ma" styleId="ndkt_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="4"/>
                          </td>
                          <td align="center">
                             <html:text property="dbhc_ma" styleId="dbhc_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="5"/>
                          </td>
                          <td align="center">
                             <html:text property="ctmt_ma" styleId="ctmt_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="5"/>
                          </td>
                          <td align="center">
                             <html:text property="ma_nguon" styleId="ma_nguon" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="2"/>
                          </td>
                          <td align="center">
                             <html:text property="kb_ma" styleId="kb_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="4"/>
                          </td>
                          <td align="center">
                             <html:text property="du_phong_ma" styleId="du_phong_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="3"/>
                          </td>
                          <td align="center">
                             <html:text property="dien_giai" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleId="dien_giai" styleClass="fieldTextCodeHeight" />
                          </td>
                          <td align="center">
                             <html:text property="so_tien" onkeydown="skipTabReadonlyField(event, 'loai_hach_toan');" styleId="so_tien" onblur="validSoTien(this);" 
                             styleClass="fieldTextRightCode"/>
                          </td>
                          <td align="center">
                              <img border="0" src="<%=request.getContextPath()%>/styles/images/delete1.gif" onclick="removeDetailCOARow(this);" onmouseout="UnTip()" onmouseover="Tip('Xóa')" id="id_delete_row" alt="">
                          </td>
                        </tr>
                        </logic:empty>
                        <logic:notEmpty name="colLTTCTiet">
                        <logic:iterate id="lttCTiet" name="colLTTCTiet" indexId="index">
                        <tr id="coa_<bean:write name="index"/>">
                          <td align="center">
                              <html:text name="lttCTiet" property="ma_quy" styleId="ma_quy" onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="2"/>
                          </td>
                          <td align="center" id="tkktma">
                            <html:text name="lttCTiet" property="tkkt_ma" styleId="tkkt_ma" onkeypress="return numberBlockKey1();" 
                              onblur="checkDMTKCheo(this, this.parentNode.parentNode, true);" styleClass="fieldTextCenterCode" maxlength="4"/>
                          </td>
                          <td align="center">
                            <html:text name="lttCTiet" property="dvsdns_ma" styleId="dvsdns_ma" onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="7"/>
                          </td>
                          <td align="center">
                            <html:text name="lttCTiet" property="capns_ma" styleId="capns_ma" onkeypress="return numberBlockKey1();" styleClass="fieldTextCenterCode" maxlength="1"/>
                          </td>
                          <td align="center">
                            <html:text name="lttCTiet" property="chuongns_ma" styleId="chuongns_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="3"/>
                          </td>
                          <td align="center">
                            <html:text name="lttCTiet" property="nganhkt_ma" styleId="nganhkt_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="3"/>
                          </td>
                          <td align="center">
                            <html:text name="lttCTiet" property="ndkt_ma" styleId="ndkt_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="4"/>
                          </td>
                          <td align="center">
                            <html:text name="lttCTiet" property="dbhc_ma" styleId="dbhc_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="5"/>
                          </td>
                          <td align="center">
                            <html:text name="lttCTiet" property="ctmt_ma" styleId="ctmt_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="5"/>
                          </td>
                          <td align="center">
                            <html:text name="lttCTiet" property="ma_nguon" styleId="ma_nguon" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="2"/>
                          </td>
                          <td align="center">
                             <html:text name="lttCTiet" property="kb_ma" styleId="kb_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="4"/>
                          </td>
                          <td align="center">
                            <html:text name="lttCTiet" property="du_phong_ma" styleId="du_phong_ma" onkeypress="return numberBlockKey1();" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleClass="fieldTextCenterCode" maxlength="3"/>
                          </td>
                          <td align="center">
                            <html:text name="lttCTiet" property="dien_giai" onkeydown="enterTabFocus2EnableLTTDen(event, this);" styleId="dien_giai" styleClass="fieldTextCodeHeight" />
                          </td>
                          <td align="center">
                             <html:text name="lttCTiet" property="so_tien" styleId="so_tien" onblur="formatNumberCOAJQuery(this);validSoTien(this);cal_tongtienCOA();" 
                             styleClass="fieldTextRightCode"/>
                          </td>
                          <td align="center">
                              <img border="0" src="<%=request.getContextPath()%>/styles/images/delete1.gif" onclick="removeDetailCOARow(this);" onmouseout="UnTip()" onmouseover="Tip('Xóa')" id="id_delete_row" alt="">
                          </td>
                        </tr>
                        </logic:iterate>
                        </logic:notEmpty>
                      </tbody>
                   </table>
               </div>
               
            </td>             
          </tr>          
        </tbody>
      </table>      
      
      </td>
      </tr>
      </table>
      <input type="hidden" value="hoan_thien_ltt_di" id="form_type" />
      <html:hidden property="nhan" styleId="nhan" value=""/>
      <html:hidden property="gd_duyet" styleId="gd_duyet" value=""/>
      <html:hidden property="trang_thai" styleId="trang_thai" />
      <html:hidden property="chuky_ktt" styleId="chuky_ktt" />
      <input type="hidden" id="ma_kb_sotai" />
       <input id="id_selected" type="hidden"/>
      <input type="hidden" name="certserial" id="certserial" />
      <input type="hidden" name="noi_dung_ky" id="noi_dung_ky" />
      <input type="hidden" name="signature" id="signature" />
      <input type="hidden" id="focusField"/>
      <div id="dialog-form-tim-kiem" title="<fmt:message key="ltt_di.page.title.dialog_form_tim_kiem"/>">
  <p class="validateTips"></p>
      <table style="width:100%;height:100%;">
              <tr>
                <td id="ma_ttv" align="left" colspan="2">                  
                    <label for="ttvnhan" style="padding-left:55px;">
                      <fmt:message key="ltt_di.page.label.ma_ttv"/>
                    </label>
                    <input type="text" name="ttvnhan" id="ttvnhan" class=" text ui-widget-content ui-corner-all"/>                  
                </td>
                <td align="right">                  
                    <label for="nhkbnhan">
                      <fmt:message key="ltt_di.page.label.nh_kb_nhanTK"/>
                    </label>
                </td>
                <td align="left">
                    <select name="nhkbchuyennhan" id="nhkbchuyennhan" style="width:100%;" class=" text ui-widget-content ui-corner-all">
                       <option value="">Tất cả</option>
                        <logic:notEmpty name="colDMNH">
                            <logic:iterate id="nhkbchuyennhan" name="colDMNH">
                              <option value='<bean:write name="nhkbchuyennhan" property="ma_nh" />'> 
                                <bean:write name="nhkbchuyennhan" property="ten" />
                              </option>
                            </logic:iterate>
                          </logic:notEmpty>                
                    </select>                  
                </td>
                <td align="right">
                    <label for="soyctt" style="padding-left:50px;">
                      <fmt:message key="ltt_di.page.label.so_ycttTK"/>
                    </label>
                </td>
                <td align="left">
                    <input type="text" name="soyctt" id="soyctt" class=" text ui-widget-content ui-corner-all"/>                   
                </td>
                
                </tr>
                <tr>
                <td align="right">
                    <label for="trangthai" style="padding-left:45px;">
                      <fmt:message key="ltt_di.page.label.trang_thai"/>
                    </label>
                </td>
                <td align="left">
                    <select name="trangthai" id="trangthai" class=" text ui-widget-content ui-corner-all">
                       <option value="00">Tất cả</option>
                        <logic:notEmpty name="colTrangThai">
                            <logic:iterate id="trangthai" name="colTrangThai">
                              <option value='<bean:write name="trangthai" property="rv_low_value" />'> 
                                <bean:write name="trangthai" property="rv_meaning" />
                              </option>
                            </logic:iterate>
                          </logic:notEmpty>                
                    </select>
                </td>
                <td align="right">Loại tiền</td>
                <td align="left">
                  <select name="nt_id_find" id="nt_id_find" onchange="formatNumberCOAJQueryDivTimkiem();" class=" text ui-widget-content ui-corner-all">
                       <option value="VND">VND</option>
                        <logic:notEmpty name="listDMTienTe">
                            <logic:iterate id="tiente" name="listDMTienTe">
                              <option value='<bean:write name="tiente" property="ma" />'> 
                                <bean:write name="tiente" property="ma" />
                              </option>
                            </logic:iterate>
                          </logic:notEmpty> 
                    </select>
                    <input type="hidden" id="nt_id_find_old" name="nt_id_find_old">
                </td>
                <td align="right">
                    <label for="sotien" style="padding-left:60px;">
                      <fmt:message key="ltt_di.page.label.so_tien" />
                    </label>
                    <input type="text" name="sotien" id="sotien" onblur="formatNumberCOAJQueryDivTimkiem();" class=" text ui-widget-content ui-corner-all"/>          
                </td>               
              </tr>
            </table>
      <table>
        <tr>
          <td width="100%">
            <div id="resultSearch" class="title2" style="padding-left:55px;padding-top:20px;"></div>
          </td>
        </tr>
      </table>
</div>
  </html:form>
</div>
<div id="dialog-message" title="<fmt:message key="ltt_di.page.title.dialog_message"/>">
    <p style="vertical-align:middle;">
      <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="message"></span>
    </p>
</div>
<div id="dialog-khc" title="<fmt:message key="ltt_di.page.title.dialog_message"/>" >
    <p style="vertical-align:middle;"><span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="dlgMessage-khc"></span>
    </p>
</div>

<script type="text/javascript" charset="utf-8">
  jQuery.noConflict(); 	
  
  jQuery(document).ready(function(){
  // set gia tri kho bac so tai
    jQuery("#ma_kb_sotai").val('<%=strNHKB_COA%>');
    //Global variable TKTN dac biet, an ninh
    arrSpecialTKTN = new Array(<%=strSpecialTKTN%>);
    arrTKTNAnNinh = new Array(<%=strTKTNAnNinh%>);
    // Dieu khien an hien cac nut ban dau vao
    var quyenLttDen = "<%=strQuyenLttDen%>";
    document.getElementById('row_select').value="row_ltt_0";         
    rowSelectedSetFocus(document.getElementById('row_select').value);
    
    // arrFieldId is declare in LenhThanhToan
    readOnlyFields(arrFieldId, true);
    jQuery("#save").hide();
    jQuery("#completeLtt").hide();
    jQuery("#rejectLtt").hide();
    jQuery("#approved").hide();
    jQuery("#btn_in").hide();
    jQuery("#cmdAddRow").attr({"disabled":true});
    // chuc them 
    row_default="row_ltt_0";
    input_default = jQuery('#rowSelected');      
    //end
    // Dieu khien an hien cac nut ban dau vao
    //end
    //An field ma_ttv cho viec tim kiem neu la ttv
    strLoaiUser = "<%=strUserType%>";
    if(strLoaiUser != null){
      if(strLoaiUser.indexOf("<%=AppConstants.NSD_TTV%>") != -1){ 
        jQuery("#ma_ttv").hide();
//        loadDetailLTTJson('loadLTTDenJsonAction.do', input_default.val(),row_default, '<%=AppConstants.NSD_TTV%>',quyenLttDen)
      }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_GD%>") != -1){
//        loadDetailLTTJson('loadLTTDenJsonAction.do', input_default.val(),row_default, '<%=AppConstants.NSD_GD%>',quyenLttDen)
      }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_KTT%>") != -1){
//        loadDetailLTTJson('loadLTTDenJsonAction.do', input_default.val(),row_default,'<%=AppConstants.NSD_KTT%>',quyenLttDen)
      }else{
//        loadDetailLTTJson('loadLTTDenJsonAction.do', input_default.val(),row_default, '<%=AppConstants.NSD_CB_TTTT%>',quyenLttDen);
      }
      loadDetailLTTJson('loadLTTDenJsonAction.do', input_default.val(),row_default, strLoaiUser, quyenLttDen);
    }
//    if(strLoaiUser != null && strLoaiUser.indexOf("<%=AppConstants.NSD_TTV%>") != -1){
//      jQuery("#ma_ttv").hide();
//      loadDetailLTTJson('loadLTTDenJsonAction.do', input_default.val(),row_default, '<%=AppConstants.NSD_TTV%>')
//    }
    jQuery("#ngay_ct_btn").hide();
    jQuery("#nt_id").attr({"disabled":true});
    jQuery("#tblThongTinChiTietCOA tr input").attr("readOnly", true);    
    jQuery("#loai_hach_toan").attr({"disabled":true});
        
    var strLastAct = "";
    var strTrangThai = '<%=strTrangThai%>';
    
    if(strTrangThai != '' && strTrangThai != null){
      strTrangThai = getStrTrangThai(strTrangThai);
      jQuery("#mo_ta_trang_thai").html(strTrangThai);
    }
        
    //init dialog: message, Tim kiem so_yctt, confirm
    jQuery("#dialog:ui-dialog").dialog( "destroy" );
    initDialogMsg();
    initDialogTimKiem();
    initDialogKHC();        
    
    //alert("quyenLttDen = "+quyenLttDen);
    if(quyenLttDen.indexOf("THOAT") != -1){
      strLastAct = "TraCuuLTT";
      // khong cho phep click nut refresh
        jQuery("#refresh").removeAttr("onclick");
        jQuery("#row_ltt_0").removeAttr("onclick");
        hideBtnLTTDiDenForFind();        
    }else if(quyenLttDen.indexOf("T4") != -1){
      strLastAct = "TraCuuLTTToanQuoc";
      // khong cho phep click nut refresh
        jQuery("#refresh").removeAttr("onclick");
        jQuery("#row_ltt_0").removeAttr("onclick");
        hideBtnLTTDiDenForFind();
    }else{
      //Cho NHKB Nhan (Ko can!!!)
      assignValue2Field("ma_nhkb_nhan_cm", "<%=session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION)%>");
      assignValue2Field("nhkb_nhan", "<%=session.getAttribute(AppConstants.APP_NHKB_ID_SESSION)%>");
      assignValue2Field("ten_nhkb_nhan_cm", "<%=session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION)%>");
    }
    
    //Dat read only field cho LTT DEN !!!
    
    var msgLTTDen = '<%=strMsgLTTDen%>';    
    var strLastFrm = '<%=strLastForm%>';
    var strErrorAt = '<%=strErrorAtCol%>';
    var errorAtRow = 0;
    // Bien toan cuc
    arrFieldReadonly = new Array("id", "ma_ttv_nhan", "ten_ttv_nhan", "ngay_tt", "ngay_hoan_thien", "ma_ktt_duyet", 
      "ten_ktt_duyet", "ngay_ktt_duyet", "tong_sotien", "ten_nhkb_chuyen_cm", "ten_nhkb_nhan_cm");
    action = '<%=strLastAction%>';
    currentAction = '';
    firstCheck = false;
    errorAtRow = <%=Integer.parseInt(strErrorAtRow)%>;
    errorAtRow = errorAtRow + 1; //cong 1 vi bo tr header
    counter = 0;
    errDescription = '<%=strErrDescription%>';
    var actionParam = '<%=strActionParam%>'
    if(actionParam != ''){ 
      jQuery("#search").hide();
      jQuery("#btn_in").hide();      
    }
    if(msgLTTDen != null && msgLTTDen != '' && action != ''){
      if(strLastFrm != ''){
        jQuery("#tblThongTinChiTietCOA tr input").attr("readOnly", false);
        jQuery("#cmdAddRow").attr({"disabled":false});
        //readOnly mot so fields ko fai nhap trong COA
        var resultEveryFocus = false;
        resultEveryFocus = focusEveryTKTN(true);
        counter = 0;
        
        if('LTT.DEN.SUA.LAI' == action){            
          jQuery("#data-grid tr").removeAttr("onclick");
          jQuery("#data-grid tr").removeAttr("ondblclick");
          jQuery("#data-grid tr").removeAttr("onmouseout");
          jQuery("#data-grid tr").removeAttr("onmouseover");
          currentAction = '<%=AppConstants.ACTION_EDIT%>';
          if(strErrorAt != null && strErrorAt != ""){
            //Lay ra so dong trong table tblThongTinChiTietCOA, Lay ra objRow roi gan focus to collumn fieldFocus
            if(resultEveryFocus){
              focus2CellInCOA(errorAtRow, strErrorAt);
            }else{
              alert("Bạn phải click chuột vào Tài khoản tự nhiên của từng dòng trong Chi tiết khoản mục");
            }
          }else if(msgLTTDen != null && msgLTTDen != '' && msgLTTDen != 'undefined'){
            msgAlertByAction(action, msgLTTDen);                
            if(msgLTTDen == 'KHC_ERROR' || msgLTTDen == 'KHC_INVALID'){
              jQuery("#dialog-khc" ).dialog( "open" );
            }else{
              jQuery("#dialog-message" ).dialog( "open" );
            }
          }
          
          jQuery("#loai_hach_toan").attr({"disabled":false});
          setClass2Field('loai_hach_toan', "fieldText");
          
          assignValue2Field("save", "capnhat"); 
        }
        jQuery("#save").html("<span class=\"sortKey\">G<\/span>hi");
        jQuery("#save").show(); 
        
        //jQuery("#fsLyDoDayLai").hide();                  
        jQuery("#search").hide();
        
      }else{
          clearForm(arrFieldId);    
          if(!(msgLTTDen=="success" && (action=='<%=AppConstants.ACTION_EDIT%>' ||
                               action=='<%=AppConstants.ACTION_CANCEL%>' ||
                               action=='<%=AppConstants.ACTION_APPROVED%>' ||
                               action=='<%=AppConstants.ACTION_REJECT%>'))){
                                  msgAlertByAction(action, msgLTTDen);
                                  jQuery("#dialog-message").dialog( "open" );
           }else{
             document.forms[0].action= 'listLttDenAdd.do';
             document.forms[0].submit(); 
           }
        
      }
    }
   
    
    jQuery("#rejectLtt").click(function(){
      currentAction = '<%=AppConstants.ACTION_REJECT%>';     
      document.forms[0].target='';
      var result = false;
      result = checkInputDayLai('lydo_ktt_day_lai', 'KTT');
      if(result){
        rejectLttDen();
      }
    });
    
    jQuery("#approved").click(function(){
      currentAction = '<%=AppConstants.ACTION_APPROVED%>';     
      document.forms[0].target='';
      if("Y"==strChkKy){
      if(!ky(strLoaiUser)){
        alert("Bạn ký Không thành công");
        return false;
      }    
      }
      approveLttDen();
    });
    
//    jQuery("#cancel").click(function(){
//      currentAction = '<%=AppConstants.ACTION_CANCEL%>';
//      document.forms[0].action = 'lttCancel.do';
//      document.forms[0].submit();
//    });
    //**************************BUTTON Tim kiem CLICK********************************************
    jQuery("#search").click(function(){      
//      currentAction = '<%=AppConstants.ACTION_FIND%>';      
      jQuery("#dialog-form-tim-kiem").dialog( "open" );
      if(strLoaiUser != null){
        if(strLoaiUser.indexOf("<%=AppConstants.NSD_TTV%>") != -1){
          jQuery("#nhkbnhan").focus(); 
        }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_KTT%>") != -1){
          jQuery("#ttvnhan").focus();
        }
      }
    });
    
    jQuery("#exit").click(function(){
      sbExitLttDen(strLastAct);
    });
  });
  function approveLttDen(){
    var id= jQuery("#id_selected").val();
    var tong_sotien= jQuery("#tong_sotien").val();
    var nhan= jQuery("#nhan").val();
    var row_id_select_b4 = document.getElementById('row_select').value;    
    var strImg = "/TTSP/styles/images/sended-but.jpg";
    var strTitle="";
    var certserial = jQuery("#certserial").val();
    var signature = jQuery("#signature").val();
    var noi_dung_ky = jQuery("#noi_dung_ky").val();
    var chuky_ktt = jQuery("#chuky_ktt").val();
    var nt_id = jQuery("#nt_id").val();
    jQuery.ajax( {
        type : "POST", url : "lttDenApproved.do", data :  {
           "id" : id,"tong_sotien":tong_sotien,"chuky_ktt":chuky_ktt,"nt_id":nt_id,
           "certserial":certserial,"signature":signature,"noi_dung_ky":noi_dung_ky,"nhan":nhan, "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                        if (data.strMsgEx != null) {
                          // tim ra row va col
                          if (data.strMsgEx=='<%=AppConstants.SUCCESS%>') {
                              hideBtnLTTDiDenForFind();
                              // xoa het style tren row cu va add style mac dinh
                              jQuery("#" + row_id_select_b4+" td:nth-child(1)").find('input').removeAttr('class');
                              jQuery("#" + row_id_select_b4+" td:nth-child(2)").removeAttr('class');
                              jQuery("#" + row_id_select_b4).attr( {
                                  'class' : 'ui-widget-content jqgrow ui-row-ltr'
                              });
                              // set anh va title cho anh mo ta trang thai
                              // set mo ta trang thai 
                              if(data.strTrangThai!=undefined && data.strTrangThai!=''){
                                if(data.strTrangThai =='07'){
                                  strTitle ="Đã gửi - Chưa vào giao diện";
                                }
                              }
                              jQuery("#" + row_id_select_b4+" td:nth-child(2)").children('img').attr("src",strImg);
                              jQuery("#" + row_id_select_b4+" td:nth-child(2)").children('img').attr("title",strTitle);
                              // add row xong cuoi cung va xoa row tai vi tri cu
                              jQuery("#data-grid tbody").append('<tr>'+jQuery("#" + row_id_select_b4)[0].outerHTML+'<\/tr>');
                              jQuery("#" + row_id_select_b4).remove();
                              // select len row tren cung
                              var firstRow = jQuery("#data-grid tbody tr:first").attr('id');
                              var firstValue = jQuery("#data-grid tbody tr:first").find('input[id=rowSelected]').val();
                              jQuery("#cancel").hide();
                              if(firstRow!='' && firstRow!=undefined){
                                var chucdanh="";
                                var quyenLttDi = "<%=strQuyenLttDen%>"!=null?"<%=strQuyenLttDen%>":"";
                                if(strLoaiUser != null){
                                  if(strLoaiUser.indexOf("<%=AppConstants.NSD_TTV%>") != -1){
                                    chucdanh = '<%=AppConstants.NSD_TTV%>';
                                  }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_GD%>") != -1){
                                    chucdanh = '<%=AppConstants.NSD_GD%>';
                                  }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_KTT%>") != -1){
                                    chucdanh = '<%=AppConstants.NSD_KTT%>';
                                  }else{
                                    chucdanh = '<%=AppConstants.NSD_CB_TTTT%>';
                                  }
                                }                               
                                defaultRowSelectedFirstRowLTT('loadLTTDenJsonAction.do', firstValue,firstRow, chucdanh,quyenLttDi);
                              }
                          }
                          else if (data.strMsgEx=='<%=AppConstants.FAILURE%>') {
                            alert(GetUnicode('Duy&#7879;t b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : L&#7895;i trong qu&#225; tr&#236;nh c&#7853;p nh&#7853;t l&#7879;nh n&#224;y'));
                          }
                          else if (data.strMsgEx=='ltt_di.error.chu_ky_so') {
                            alert(GetUnicode('Duy&#7879;t b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : L&#7895;i Ch&#7919; k&#253; s&#7889;'));
                          }
                          else if (data.strMsgEx=='<%=AppConstants.LTT_NGAT_KET_NOI%>') {
                            alert(GetUnicode('Duy&#7879;t b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + data.LyDoNgatKetNoi));
                          } 
                          else if (data.strMsgEx=='ltt_di.error.khong_duoc_phep_duyet') {
                            alert(GetUnicode('Duy&#7879;t b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : B&#7841;n kh&#244;ng &#273;&#432;&#7907;c ph&#233;p duy&#7879;t l&#7879;nh n&#224;y'));
                          } 
                          else if (data.strMsgEx=='TIEN_GD_DUYET_TC') {
                            alert(GetUnicode('Duy&#7879;t b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : Kh&#244;ng l&#7845;y &#273;&#432;&#7907;c l&#432;&#7907;ng ti&#7873;n GD duy&#7879;t th&#7911; c&#244;ng '));
                          }
                          else if (data.strMsgEx=='TIEN_GD_DUYET_TM') {
                            alert(GetUnicode('Duy&#7879;t b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : Kh&#244;ng l&#7845;y &#273;&#432;&#7907;c l&#432;&#7907;ng ti&#7873;n GD duy&#7879;t Tabmis '));
                          }
                          else if (data.strMsgEx=='ltt_di.error.khong_gui_duoc') {
                            alert(GetUnicode('Duy&#7879;t b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : L&#7895;i kh&#244;ng g&#7917;i &#273;&#432;&#7907;c l&#7879;nh n&#224;y'));
                          }                           
                          else if(data.strMsgEx == 'INVALID'){
                            alert(GetUnicode('Duy&#7879;t b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i :' + '<fmt:message key="ltt_di.page.message.chu_ky_khong_hop_le"/>'));
                          }
                          else if(data.strMsgEx == 'ERROR') {
                            alert(GetUnicode('Duy&#7879;t b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i :' + '<fmt:message key="ltt_di.page.message.chu_ky_loi"/>'));
                          }  
                          else if (data.strMsgEx=='<%=AppConstants.LTT_KHONG_CO_QUYEN%>') {
                            alert(GetUnicode('Duy&#7879;t b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.khong_du_quyen"/>'));
                          }
                          else {
                            alert(GetUnicode('Duy&#7879;t b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i :' + data.strMsgEx));
                          }
                        }else{
                          alert('Duy&#7879;t kh&#244;ng th&#224;nh c&#244;ng. '+data.strMsgEx);
//                          alert(GetUnicode('&#272;&#227; c&#243; l&#7895;i x&#7843;y ra trong qu&#225; tr&#236;nh th&#7921;c hi&#7879;n h&#7911;y b&#7843;n ghi '+ id));
                        }
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus.messge);
        }
      });
  }
  function rejectLttDen(){
    var id= jQuery("#id_selected").val();
    var lydo_ktt_day_lai= jQuery("#lydo_ktt_day_lai").val();
    var lydo_gd_day_lai= jQuery("#lydo_gd_day_lai").val();
    var row_id_select_b4 = document.getElementById('row_select').value;    
    var strImg = "/TTSP/styles/images/return.jpeg";
    var strTitle="";
    
    jQuery.ajax( {
        type : "POST", url : "lttDenReject.do", data :  {
           "id" : id,"lydo_ktt_day_lai":lydo_ktt_day_lai,"lydo_gd_day_lai":lydo_gd_day_lai,  "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                        if (data.strMsgEx != null) {
                          // tim ra row va col
                          if (data.strMsgEx=='<%=AppConstants.SUCCESS%>') {
                              hideBtnLTTDiDenForFind();
                              // xoa het style tren row cu va add style mac dinh
                              jQuery("#" + row_id_select_b4+" td:nth-child(1)").find('input').removeAttr('class');
                              jQuery("#" + row_id_select_b4+" td:nth-child(2)").removeAttr('class');
                              jQuery("#" + row_id_select_b4).attr( {
                                  'class' : 'ui-widget-content jqgrow ui-row-ltr'
                              });
                              // set anh va title cho anh mo ta trang thai
                              jQuery("#" + row_id_select_b4+" td:nth-child(2)").children('img').attr("src",strImg);
                              // set mo ta trang thai 
                              if(data.strTrangThai!=undefined && data.strTrangThai!=''){
                                if(data.strTrangThai =='01'){
                                  strTitle ="KTT đẩy lại";
                                }else{
                                  strTitle ="GD đẩy lại";
                                }
                              }
                                jQuery("#" + row_id_select_b4+" td:nth-child(2)").children('img').attr("title",strTitle);
                              // add row xong cuoi cung va xoa row tai vi tri cu
                              jQuery("#data-grid tbody").append('<tr>'+jQuery("#" + row_id_select_b4)[0].outerHTML+'<\/tr>');
                              jQuery("#" + row_id_select_b4).remove();
                              // select len row tren cung
                              var firstRow = jQuery("#data-grid tbody tr:first").attr('id');
                              var firstValue = jQuery("#data-grid tbody tr:first").find('input[id=rowSelected]').val();
                              jQuery("#cancel").hide();
                              if(firstRow!='' && firstRow!=undefined){
                                var chucdanh="";
                                var quyenLttDi = "<%=strQuyenLttDen%>"!=null?"<%=strQuyenLttDen%>":"";
                                if(strLoaiUser != null){
                                  if(strLoaiUser.indexOf("<%=AppConstants.NSD_TTV%>") != -1){
                                    chucdanh = '<%=AppConstants.NSD_TTV%>';
                                  }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_GD%>") != -1){
                                    chucdanh = '<%=AppConstants.NSD_GD%>';
                                  }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_KTT%>") != -1){
                                    chucdanh = '<%=AppConstants.NSD_KTT%>';
                                  }else{
                                    chucdanh = '<%=AppConstants.NSD_CB_TTTT%>';
                                  }
                                }                               
                                defaultRowSelectedFirstRowLTT('loadLTTDenJsonAction.do', firstValue,firstRow, chucdanh,quyenLttDi);
                              }
                          }
                          else if (data.strMsgEx=='<%=AppConstants.FAILURE%>') {
                            alert(GetUnicode('&#272;&#7849;y l&#7841;i b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.day_lai_that_bai"/>'));
                          }
                          else if (data.strMsgEx=='<%=AppConstants.LTT_KHONG_CO_QUYEN%>') {
                            alert(GetUnicode('&#272;&#7849;y l&#7841;i b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.khong_du_quyen"/>'));
                          }
                          else if (data.strMsgEx=='<%=AppConstants.LTT_NGAT_KET_NOI%>') {
                            alert(GetUnicode('&#272;&#7849;y l&#7841;i b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.daylai_ngat_ket_noi"/>  \n ' + data.LyDoNgatKetNoi));
                          }
                          else if(data.strMsgEx=='ID_NULL'){
                            alert(GetUnicode('&#272;&#7849;y l&#7841;i b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i!'));
                          }
                          else {
                            alert(GetUnicode('&#272;&#7849;y l&#7841;i b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i :' + data.strMsgEx));
                          }
                        }else{
                          alert(GetUnicode('&#272;&#227; c&#243; l&#7895;i x&#7843;y ra trong qu&#225; tr&#236;nh th&#7921;c hi&#7879;n h&#7911;y b&#7843;n ghi '+ id));
                        }
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus.messge);
        }
      });
  }
   //Change style when focus to element input
 function completeActionLttDen(checkLTT) {
    // hien thi lai nut ghi 
    var id = jQuery("#id_selected").val();
    var loai_hach_toan = jQuery("#loai_hach_toan").val();
    var trang_thai = jQuery("#trang_thai").val();
    var tong_sotien = jQuery("#tong_sotien").val();
    var ngay_ct = jQuery("#ngay_ct").val();
    var ngay_tt = jQuery("#ngay_tt").val();
    var ttv_nhan = jQuery("#ttv_nhan").val();
    var nt_id = jQuery("#nt_id").val();
    
    var arrTkkt_ma = document.getElementsByName("tkkt_ma"), arrMa_quy = document.getElementsByName("ma_quy"), arrDvsdns_ma = document.getElementsByName("dvsdns_ma"), arrCapns_ma = document.getElementsByName("capns_ma"), arrChuongns_ma = document.getElementsByName("chuongns_ma"), arrNganhkt_ma = document.getElementsByName("nganhkt_ma"), arrNdkt_ma = document.getElementsByName("ndkt_ma"), arrDbhc_ma = document.getElementsByName("dbhc_ma"), arrCtmt_ma = document.getElementsByName("ctmt_ma"), arrMa_nguon = document.getElementsByName("ma_nguon"), arrDu_phong_ma = document.getElementsByName("du_phong_ma"), arrMa_kb = document.getElementsByName("kb_ma"), arrDien_giai = document.getElementsByName("dien_giai"), arrSo_tien = document.getElementsByName("so_tien");
    var arrTkkt_ma_res = new Array();
    var arrMa_quy_res = new Array();
    var arrDvsdns_ma_res = new Array();
    var arrCapns_ma_res = new Array();
    var arrChuongns_ma_res = new Array();
    var arrNganhkt_ma_res = new Array();
    var arrNdkt_ma_res = new Array();
    var arrDbhc_ma_res = new Array();
    var arrCtmt_ma_res = new Array();
    var arrMa_nguon_res = new Array();
    var arrDu_phong_ma_res = new Array();
    var arrMa_kb_res = new Array();
    var arrDien_giai_res = new Array();
    var arrSo_tien_res = new Array();
    var so_tien_compare = 0;
    for (i = 0;i < arrTkkt_ma.length;i++) {
        // add gia tri vao mang
        //arrTkkt_ma_res
        arrTkkt_ma_res[i] = arrTkkt_ma[i].value;

        //arrMa_quy_res
        arrMa_quy_res[i] = arrMa_quy[i].value;
        //arrDvsdns_ma_res
        arrDvsdns_ma_res[i] = arrDvsdns_ma[i].value;
        //arrCapns_ma_res
        arrCapns_ma_res[i] = arrCapns_ma[i].value;
        //arrChuongns_ma_res
        arrChuongns_ma_res[i] = arrChuongns_ma[i].value;
        //arrNganhkt_ma_res
        arrNganhkt_ma_res[i] = arrNganhkt_ma[i].value;
        //arrNdkt_ma_res
        arrNdkt_ma_res[i] = arrNdkt_ma[i].value;
        //arrDbhc_ma_res
        arrDbhc_ma_res[i] = arrDbhc_ma[i].value;
        //arrCtmt_ma_res
        arrCtmt_ma_res[i] = arrCtmt_ma[i].value;
        //arrMa_nguon_res
        arrMa_nguon_res[i] = arrMa_nguon[i].value;
        //arrDu_phong_ma_res
        arrDu_phong_ma_res[i] = arrDu_phong_ma[i].value;
        //arrMa_kb_res
        arrMa_kb_res[i] = arrMa_kb[i].value;
        //arrDien_giai_res
        arrDien_giai_res[i] = arrDien_giai[i].value;
        //arrSo_tien_res
        arrSo_tien_res[i] = arrSo_tien[i].value;
//        so_tien_compare += parseInt(arrSo_tien[i].value.replace(/\./gi, ''), 0);
        so_tien_compare += convertCurrencyToNumber(arrSo_tien[i].value, nt_id)*100;
        // truong hop khong co coa thi khong check nua
    }
    so_tien_compare = so_tien_compare/100;
    if (arrSo_tien_res[0] == "") {
        alert(GetUnicode("S&#7889; ti&#7873;n trong chi ti&#7871;t kho&#7843;n m&#7909;c kh&#244;ng c&#243; d&#7919; li&#7879;u"));
        return false;
    }
//    if (so_tien_compare != parseInt(tong_sotien.replace(/\./gi, ''), 0)) {
    if (so_tien_compare != convertCurrencyToNumber(tong_sotien, nt_id)) {
        alert(GetUnicode("T&#7893;ng s&#7889; ti&#7873;n trong chi ti&#7871;t kho&#7843;n m&#7909;c kh&#225;c s&#7889; ti&#7873;n c&#7911;a LTT"));
        return false;
    }    
    if ('<%=strNHKB_COA%>' != arrMa_kb_res[0]) {
        alert(GetUnicode("M&#227; kho b&#7841;c trong chi ti&#7871;t kho&#7843;n m&#7909;c kh&#244;ng ph&#7843;i m&#227; kho b&#7841;c s&#7903; t&#7841;i"));
        return false;
    }
    var ly_do_htoan = jQuery("#ly_do_htoan").val();
    var row_id_select_b4 = document.getElementById('row_select').value;
    var strImg = "";
    var strTitle = "";
    jQuery.ajaxSettings.traditional = true;
    jQuery.ajax( {
        type : "POST", url : "lttDenUpdateExc.do", data :  {
            cache: false,
            contentType: false,
            processData: false,
            "idHT" : id, "trang_thai" : trang_thai,"tong_sotien" : tong_sotien, "checkLTT" : checkLTT, "ngay_ct" : ngay_ct, "ngay_tt" : ngay_tt,
            "ttv_nhan" : ttv_nhan, "nt_id" : nt_id, "loai_hach_toan" : loai_hach_toan, "tkkt_ma" : arrTkkt_ma_res, "ma_quy" : arrMa_quy_res,
            "dvsdns_ma" : arrDvsdns_ma_res, "capns_ma" : arrCapns_ma_res, "chuongns_ma" : arrChuongns_ma_res, "nganhkt_ma" : arrNganhkt_ma_res,
            "ndkt_ma" : arrNdkt_ma_res, "dbhc_ma" : arrDbhc_ma_res, "ctmt_ma" : arrCtmt_ma_res, "ma_nguon" : arrMa_nguon_res, "du_phong_ma" : arrDu_phong_ma_res,
            "kb_ma" : arrMa_kb_res, "dien_giai" : arrDien_giai_res, "so_tien" : arrSo_tien_res, "ly_do_htoan" : ly_do_htoan, "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                        if (data.strMsgEx != null) {
                            // tim ra row va col
                            if (data.strMsgEx == '<%=AppConstants.SUCCESS%>') {
                                hideBtnLTTDiDenForFind();
                                // xoa het style tren row cu va add style mac dinh
                                jQuery("#" + row_id_select_b4 + " td:nth-child(1)").find('input').removeAttr('class');
                                jQuery("#" + row_id_select_b4 + " td:nth-child(2)").removeAttr('class');
                                jQuery("#" + row_id_select_b4).attr( {
                                    'class' : 'ui-widget-content jqgrow ui-row-ltr'
                                });
                                // set anh va title cho anh mo ta trang thai
                                // set mo ta trang thai 
                                if (data.strTrangThai != undefined && data.strTrangThai != '') {
                                    if (data.strTrangThai == '03') {
                                        strTitle = "Chờ KTT duyệt";
                                        strImg = "/TTSP/styles/images/wait.jpeg";
                                    }
                                    else {
                                        strTitle = "Chờ GD duyệt";
                                        strImg = "/TTSP/styles/images/wait-gd.png";
                                    }
                                }
                                jQuery("#" + row_id_select_b4 + " td:nth-child(2)").children('img').attr("src", strImg);
                                jQuery("#" + row_id_select_b4 + " td:nth-child(2)").children('img').attr("title", strTitle);
                                // add row xong cuoi cung va xoa row tai vi tri cu
                                jQuery("#data-grid tbody").append('<tr>' + jQuery("#" + row_id_select_b4)[0].outerHTML + '<\/tr>');
                                jQuery("#" + row_id_select_b4).remove();
                                // select len row tren cung
                                var firstRow = jQuery("#data-grid tbody tr:first").attr('id');
                                var firstValue = jQuery("#data-grid tbody tr:first").find('input[id=rowSelected]').val();
                                jQuery("#cancel").hide();
                                if (firstRow != '' && firstRow != undefined) {
                                    var chucdanh = "";
                                    var quyenLttDi = "<%=strQuyenLttDen%>" != null ? "<%=strQuyenLttDen%>" : "";
                                    if (strLoaiUser != null) {
                                        if (strLoaiUser.indexOf("<%=AppConstants.NSD_TTV%>") !=  - 1) {
                                            chucdanh = '<%=AppConstants.NSD_TTV%>';
                                        }
                                        else if (strLoaiUser.indexOf("<%=AppConstants.NSD_GD%>") !=  - 1) {
                                            chucdanh = '<%=AppConstants.NSD_GD%>';
                                        }
                                        else if (strLoaiUser.indexOf("<%=AppConstants.NSD_KTT%>") !=  - 1) {
                                            chucdanh = '<%=AppConstants.NSD_KTT%>';
                                        }
                                        else {
                                            chucdanh = '<%=AppConstants.NSD_CB_TTTT%>';
                                        }
                                    }
                                    defaultRowSelectedFirstRowLTT('loadLTTDenJsonAction.do', firstValue, firstRow, chucdanh, quyenLttDi);
                                }
                            }
                            else if (data.strMsgEx == 'ErrorTien') {
                                alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id + ' kh&#244;ng th&#224;nh c&#244;ng : ' + 'T&#7893;ng s&#7889; ti&#7873;n trong chi ti&#7871;t kho&#7843;n m&#7909;c kh&#244;ng b&#7857;ng t&#7893;ng s&#7889; ti&#7873;n c&#7911;a l&#7879;nh'));
                            }
                            else if (data.strMsgEx == 'ErrorKB') {
                                alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id + ' kh&#244;ng th&#224;nh c&#244;ng : ' + 'kho b&#7841;c trong chi ti&#7871;t kho&#7843;n m&#7909;c kh&#244;ng ph&#7843;i kho b&#7841;c s&#7903; t&#7841;i'));
                            }
                            else if (data.strMsgEx == 'ErrorTienEmpty') {
                                alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id + ' kh&#244;ng th&#224;nh c&#244;ng : ' + 's&#7889; ti&#7873;n trong chi ti&#7871;t kho&#7843;n m&#7909;c kh&#244;ng c&#243; gi&#225; tr&#7883;'));
                            }
                            else if (data.strMsgEx == '<%=AppConstants.FAILURE%>') {
                                alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id + ' kh&#244;ng th&#224;nh c&#244;ng : ' + '<fmt:message key="ltt_di.page.message.sua_that_bai"/>'));
                            }
                            else if (data.strMsgEx == '<%=AppConstants.LTT_KHONG_CO_QUYEN%>') {
                                alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id + ' kh&#244;ng th&#224;nh c&#244;ng : ' + '<fmt:message key="ltt_di.page.message.khong_du_quyen"/>'));
                            }
                            else if (data.strMsgEx == '<%=AppConstants.LTT_NGAT_KET_NOI%>') {
                                alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id + ' kh&#244;ng th&#224;nh c&#244;ng : ' + '<fmt:message key="ltt_di.page.message.daylai_ngat_ket_noi"/>  \n ' + data.LyDoNgatKetNoi));
                            }
                            else if (data.strMsgEx == 'LTT_TRANG_THAI_DA_THAY_DOI') {
                                alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id + '<fmt:message key="ltt_di.page.message.trang_thai_thay_doi"/>'));
                            }
                            else if (data.strMsgEx == 'MA_KHONG_HOP_LE') {
                                alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id + '<fmt:message key="ltt_di.page.message.ma_coa_khong_hop_le"/>'));
                            }
                            else if (data.strMsgEx == 'KHC_INVALID') {
                                alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id + '<fmt:message key="ltt_di.page.message.ket_hop_cheo_khong_hop_le"/>'));
                            }
                            else if (data.strMsgEx == 'ERROR') {
                                alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id + '<fmt:message key="ltt_di.page.message.ket_hop_cheo_loi"/>'));
                            }
                            else {
                                alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id + ' kh&#244;ng th&#224;nh c&#244;ng ! \n' + data.strMsgEx));
                            }
                        }
                        else {
                            alert(GetUnicode('&#272;&#227; c&#243; l&#7895;i x&#7843;y ra trong qu&#225; tr&#236;nh th&#7921;c hi&#7879;n Ho&#224;n thi&#7879;n b&#7843;n ghi ' + id));
                        }
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus.messge);
        }
    });
}
  function completeLttDenClicked(){
      //************* Click nut Hoan thien *************
//      currentAction = '<%=AppConstants.ACTION_EDIT%>';
      firstCheck = true;
      jQuery("#id").show();
      jQuery("#cmdAddRow").attr( {         disabled : 'disabled'     });
      assignValue2Field("save", "capnhat");
      jQuery("#save").html("<span class=\"sortKey\">G<\/span>hi");    
      jQuery("#save").show();
      if(jQuery("#loai_hach_toan").val()!='01' && jQuery("#loai_hach_toan").val()!='02')
        jQuery("#loai_hach_toan").val("01");
      jQuery("#loai_hach_toan").attr({"class":"fieldText"});
      jQuery("#completeLtt").hide();
      jQuery("#in").show();
      //jQuery("#cancel").hide();
      jQuery("#search").show();
//      jQuery("#data-grid tr").removeAttr("onclick");
//          jQuery("#data-grid tr").removeAttr("ondblclick");
//          jQuery("#data-grid tr").removeAttr("onmouseout");
//          jQuery("#data-grid tr").removeAttr("onmouseover");
      jQuery("#cmdAddRow").attr({"disabled":true});
      jQuery("#id_delete_row").attr({"disabled":true});     
      
      jQuery("#ten_nhkb_chuyen_cm").attr({'readOnly':true});
      jQuery("#tblThongTinChiTietCOA tr input").attr("readOnly", false);       
      jQuery("#loai_hach_toan").attr({"disabled":false});
      //readOnly mot so fields ko fai nhap trong COA
      focusEveryTKTN(true);
                      
      var strId = ""; 
//      jQuery("#kb_ma").attr({
//        'onkeydown':"skipTabReadonlyField(event, 'du_phong_ma')"
//      }).focus();

      //set lai style for edit
      var tableCOA = document.getElementById('tblThongTinChiTietCOA');
      var countRowCOA = tableCOA.rows.length; 
      if(countRowCOA > 2){         
        var arrCOA = new Array();
        for(i=1; i<countRowCOA; i++){
          jQuery.each(arrFieldIdLttCTiet, function(index, value){
              arrCOA = value.toString();               
              if (arrCOA != null ) {
                if(jQuery('#'+arrCOA[i]).attr('class') == 'fieldTextCenterCode')
                  setClass2Field(arrCOA[i], "fieldTextRight");   
                else if(jQuery('#'+arrCOA[i]).attr('class') == 'fieldTextRightCode')
                  setClass2Field(arrCOA[i], "fieldTextRight");   
                else if(jQuery('#'+arrCOA[i]).attr('class') == 'fieldTextCode')
                  setClass2Field(arrCOA[i], "fieldText");   
              }              
          });
          tabContinue=200;
          var root = tableCOA.getElementsByTagName('tr')[1].parentNode;
          for (var ii = 0;ii < countRowCOA-1;ii++) {
            var rootTR = root.getElementsByTagName('tr')[ii + 1];
            jQuery.each(arrFieldIdLttCTiet, function (index, value) { 
             strId = value.toString();
              if (document.getElementById(strId) != null) {
                  if(rootTR.getElementsByTagName('td')[index].childNodes[0].readOnly!=true)
                  { 
                    rootTR.getElementsByTagName('td')[index].childNodes[0].tabIndex  = tabContinue++;
                  }
              }
            }); 
          }
            jQuery("#loai_hach_toan").attr({"tabIndex":tabContinue++});
            jQuery("#lydo_ktt_day_lai").attr({"tabIndex":tabContinue++});
           jQuery("#save").attr({"tabIndex":tabContinue++});
        }
      }        
//    });
    }
  function sbExitLttDen(strLastAct){
    if('<%=request.getParameter("referrer")%>'!='null'){
      var param = '';
      strLastAct='<%=request.getParameter("referrer")%>';
      if(strLastAct=='themdtslttdi')
        param='?action=add&id=<%=request.getParameter("so_ltt")%>';
      else if(strLastAct=='dtsoattloiView')
        param='?action=VIEW_DETAIL&id=<%=request.getParameter("id")%>';
      
      if(strLastAct.indexOf('.do') == -1){
        strLastAct = strLastAct + ".do" + param;
      }else{
        strLastAct = strLastAct + param;
      }
      window.location = strLastAct;
    }else if(strLastAct == 'TraCuuLTT'){
      strLastAct = 'TraCuuLTT.do';
    }else if(strLastAct == 'TraCuuLTTToanQuoc'){
        strLastAct = 'TraCuuLTTToanQuocList.do';
    }
    else if(currentAction != ''){
      strLastAct = 'listLttDenAdd.do';
    }else if(currentAction == ''){
      strLastAct = 'mainAction.do';
    }
  
    if(strLastAct != null && strLastAct != ""){
      if(strLastAct == "TraCuuLTT.do"){
        var ttv_nhan_back = '<%=request.getParameter("ttv_nhan")%>';
        var trang_thai_back= '<%=request.getParameter("trang_thai")%>';
        var tu_ngay_back= '<%=request.getParameter("tu_ngay")%>';
        var den_ngay_back= '<%=request.getParameter("den_ngay")%>';
        var so_ltt_back= '<%=request.getParameter("ltt_id")%>';
        var tu_ngay_nhan_back= '<%=request.getParameter("tu_ngay_nhan")%>';
        var den_ngay_nhan_back= '<%=request.getParameter("den_ngay_nhan")%>';
        var loai_lenh_tt_back = '<%=request.getParameter("loai_lenh_tt")%>';
        var ma_nhkb_chuyen_cm_back = '<%=request.getParameter("ma_nhkb_chuyen_cm")%>';
        var ma_nhkb_nhan_cm_back = '<%=request.getParameter("ma_nhkb_nhan_cm")%>';
        var ten_nhkb_chuyen_cm_back = '<%=request.getParameter("ten_nhkb_chuyen_cm")%>';
        var ten_nhkb_nhan_cm_back = '<%=request.getParameter("ten_nhkb_nhan_cm")%>';
        var so_tien_back = '<%=request.getParameter("tong_sotien")%>';
        var so_yctt_back = '<%=request.getParameter("so_yctt")%>';
        var kb_tinh_back = '<%=request.getParameter("kb_tinh")%>';
        var kb_huyen_back = '<%=request.getParameter("kb_huyen")%>';
         strLastAct += "?action=Back";
         if(ttv_nhan_back != 'null'){
           strLastAct += "&ttv_nhan_back="+ttv_nhan_back+"";
         }
          if(trang_thai_back != 'null'){
           strLastAct += "&trang_thai_back="+trang_thai_back+"";
         }
          if(tu_ngay_back != 'null'){
           strLastAct += "&tu_ngay_back="+tu_ngay_back+"";
         }
          if(den_ngay_back != 'null'){
           strLastAct += "&den_ngay_back="+den_ngay_back+"";
         }
         if(so_ltt_back != 'null'){
           strLastAct += "&so_ltt_back="+so_ltt_back+"";
         }
         if(tu_ngay_nhan_back != 'null'){
             strLastAct += "&tu_ngay_nhan_back="+tu_ngay_nhan_back+"";
           }
            if(den_ngay_nhan_back != 'null'){
             strLastAct += "&den_ngay_nhan_back="+den_ngay_nhan_back+"";
           }
         if(loai_lenh_tt_back != 'null'){
           strLastAct += "&loai_lenh_tt_back="+loai_lenh_tt_back+"";
         }
         if(ma_nhkb_chuyen_cm_back != 'null'){
           strLastAct += "&ma_nhkb_chuyen_cm_back="+ma_nhkb_chuyen_cm_back+"";
         }
         if(ma_nhkb_nhan_cm_back != 'null'){
           strLastAct += "&ma_nhkb_nhan_cm_back="+ma_nhkb_nhan_cm_back+"";
         }
         if(ten_nhkb_chuyen_cm_back != 'null'){
           strLastAct += "&ten_nhkb_chuyen_cm_back="+ten_nhkb_chuyen_cm_back+"";
         }
         if(ten_nhkb_nhan_cm_back != 'null'){
           strLastAct += "&ten_nhkb_nhan_cm_back="+ten_nhkb_nhan_cm_back+"";
         }
         if(so_tien_back != 'null'){
           strLastAct += "&so_tien_back="+so_tien_back+"";
         }
         if(so_yctt_back != 'null'){
           strLastAct += "&so_yctt_back="+so_yctt_back+"";
         }
         if(kb_tinh_back != 'null'){
           strLastAct += "&kb_tinh_back="+kb_tinh_back+"";
         }
         if(kb_huyen_back != 'null'){
           strLastAct += "&kb_huyen_back="+kb_huyen_back+"";
         }
      }else if(strLastAct == 'TraCuuLTTToanQuocList.do'){
          var ma_nh_back = '<%=request.getParameter("ma_nh")%>';
          var tinh_back = '<%=request.getParameter("tinh")%>';
          var huyen_back = '<%=request.getParameter("huyen")%>';
          var trang_thai_back = '<%=request.getParameter("trang_thai")%>';
          var loai_lenh_back= '<%=request.getParameter("loai_lenh")%>';
          var tu_ltt_back = '<%=request.getParameter("tu_ltt")%>';
          var den_ltt_back = '<%=request.getParameter("den_ltt")%>';
          var tu_ngay_back = '<%=request.getParameter("tu_ngay")%>';
          var den_ngay_back = '<%=request.getParameter("den_ngay")%>';
          var tu_ngay_nhan_back = '<%=request.getParameter("tu_ngay_nhan")%>';
          var den_ngay_nhan_back = '<%=request.getParameter("den_ngay_nhan")%>';
          var so_tien_back = '<%=request.getParameter("so_tien")%>';
           strLastAct += "?action=Back";
           if(ma_nh_back != 'null'){
             strLastAct += "&ma_nh_back="+ma_nh_back+"";
           }
            if(tinh_back != 'null'){
             strLastAct += "&tinh_back="+tinh_back+"";
           }
            if(huyen_back != 'null'){
             strLastAct += "&huyen_back="+huyen_back+"";
           }
            if(trang_thai_back != 'null'){
             strLastAct += "&trang_thai_back="+trang_thai_back+"";
           }
           if(loai_lenh_back != 'null'){
             strLastAct += "&loai_lenh_back="+loai_lenh_back+"";
           }
           
           if(tu_ltt_back != 'null'){
             strLastAct += "&tu_ltt_back="+tu_ltt_back+"";
           }
           if(den_ltt_back != 'null'){
             strLastAct += "&den_ltt_back="+den_ltt_back+"";
           }
           if(tu_ngay_back != 'null'){
             strLastAct += "&tu_ngay_back="+tu_ngay_back+"";
           }
           if(den_ngay_back != 'null'){
             strLastAct += "&den_ngay_back="+den_ngay_back+"";
           }
            if(tu_ngay_nhan_back != 'null'){
             strLastAct += "&tu_ngay_nhan_back="+tu_ngay_nhan_back+"";
           }
           if(den_ngay_nhan_back != 'null'){
             strLastAct += "&den_ngay_nhan_back="+den_ngay_nhan_back+"";
           }
           if(so_tien_back != 'null'){
             strLastAct += "&so_tien_back="+so_tien_back+"";
           }
        }else if(strLastAct=='dtsoatlttdiView.do'){
          var actionBack = '<%=request.getParameter("ActionBack")%>';
          var idBack = '<%=request.getParameter("idDts")%>';
          var urlBack="";
          strLastAct += "?action="+actionBack+"&id="+idBack;
          if(actionBack=='<%=AppConstants.ACTION_VIEW_DETAIL_DTS_T4%>'){
              var ma_nh = '<%=request.getParameter("ma_nh_back")%>';
              var tinh = '<%=request.getParameter("tinh_back")%>';
              var huyen = '<%=request.getParameter("huyen_back")%>';
              var trang_thai ='<%=request.getParameter("trang_thai_back")%>';
              var loai_lenh = '<%=request.getParameter("loai_lenh_back")%>';
              var tu_ltt ='<%=request.getParameter("tu_ltt_back")%>';
              var den_ltt = '<%=request.getParameter("den_ltt_back")%>';
              var tu_ngay = '<%=request.getParameter("tu_ngay_back")%>';
              var den_ngay= '<%=request.getParameter("den_ngay_back")%>';
              var so_dts = '<%=request.getParameter("so_dts_back")%>';
              var so_tien_temp = '<%=request.getParameter("so_tien_temp_back")%>';
              if(ma_nh != null && ma_nh != '' && ma_nh != 'null'){
                  urlBack += "&ma_nh="+ma_nh+"";
              }
              if(tinh != null && tinh != '' && tinh != 'null'){
                urlBack += "&tinh="+tinh+"";
              }    
              if(huyen != null && huyen != '' && huyen != 'null'){
                urlBack += "&huyen="+huyen+"";
              }
              if(trang_thai != null && trang_thai != '' && trang_thai != 'null'){
                urlBack += "&trang_thai="+trang_thai+"";
              }
              if(loai_lenh != null && loai_lenh != '' && loai_lenh != 'null'){
              urlBack += "&loai_lenh="+loai_lenh+"";
              }
              if(tu_ltt != null && tu_ltt != '' && tu_ltt != 'null'){
                urlBack += "&tu_ltt="+tu_ltt+"";
              }
              if(den_ltt != null && den_ltt != '' && den_ltt != 'null'){
                urlBack += "&den_ltt="+den_ltt+"";
              }
              if(tu_ngay != null && tu_ngay != '' && tu_ngay != 'null'){
                urlBack += "&tu_ngay="+tu_ngay+"";
              }
              if(den_ngay != null && den_ngay != '' && den_ngay != 'null'){
                urlBack += "&den_ngay="+den_ngay+"";
              }
              if(so_dts != null && so_dts != '' && so_dts != 'null'){
                urlBack += "&so_dts="+so_dts+"";
              }
              if(so_tien_temp!=null && so_tien_temp!= '' && so_tien_temp != 'null'){
                urlBack += "&so_tien_temp="+so_tien_temp+"";
              }
        }else if(actionBack=='<%=AppConstants.ACTION_VIEW_DETAIL%>'){
            var ttv_nhan = '<%=request.getParameter("ttv_nhan_back")%>',
             trang_thai = '<%=request.getParameter("trang_thai_back")%>',
             tu_ngay = '<%=request.getParameter("tu_ngay_back")%>',
             den_ngay ='<%=request.getParameter("den_ngay_back")%>',
             tu_ngay_lapdien = '<%=request.getParameter("tu_ngay_lapdien_back")%>',
             den_ngay_lapdien ='<%=request.getParameter("den_ngay_lapdien_back")%>',
             nhkb_chuyen = '<%=request.getParameter("nhkb_chuyen_back")%>',
             nhkb_nhan = '<%=request.getParameter("nhkb_nhan_back")%>',
             so_dts = '<%=request.getParameter("so_dts_back")%>',
             so_ltt = '<%=request.getParameter("so_ltt_back")%>',
             loai_ltt = '<%=request.getParameter("loai_ltt_back")%>',
             loai_tra_soat = '<%=request.getParameter("loai_tra_soat_back")%>',
             chieu_tra_soat = '<%=request.getParameter("chieu_tra_soat_back")%>';
            if(ttv_nhan != null &&ttv_nhan!='null' ){
             urlBack += "&ttv_nhan="+ttv_nhan+"";
            }
            if(trang_thai != null &&trang_thai!='null'){
              urlBack += "&trang_thai="+trang_thai+"";
            }
            if(tu_ngay != null &&tu_ngay!='null'){
              urlBack += "&tu_ngay="+tu_ngay+"";
            }
            if(den_ngay != null &&den_ngay!='null'){
              urlBack += "&den_ngay="+den_ngay+"";
            }      
            if(tu_ngay_lapdien != null &&tu_ngay_lapdien!='null'){
              urlBack += "&tu_ngay_lapdien="+tu_ngay_lapdien+"";
            }
            if(den_ngay_lapdien != null &&den_ngay_lapdien!='null'){
              urlBack += "&den_ngay_lapdien="+den_ngay_lapdien+"";
            }   
            if(so_dts != null &&so_dts!='null'){
              urlBack += "&so_dts="+so_dts+"";
            }
            if(nhkb_chuyen!=null && nhkb_chuyen!= 'null'){
             urlBack += "&nhkb_chuyen="+nhkb_chuyen+"";
            }
            if(nhkb_nhan!=null && nhkb_nhan!= 'null'){
              urlBack += "&nhkb_nhan="+nhkb_nhan+"";
            }
            if(so_ltt!=null && so_ltt!= 'null'){
              urlBack += "&so_ltt="+so_ltt+"";
            }
            if(loai_ltt!=null && loai_ltt!= 'null'){
              urlBack += "&loai_ltt="+loai_ltt+"";
            }
            if(loai_tra_soat!=null && loai_tra_soat!= 'null'){
             urlBack += "&loai_tra_soat="+loai_tra_soat+"";
            }
            if(chieu_tra_soat!=null && chieu_tra_soat!= 'null'){
              urlBack += "&chieu_tra_soat="+chieu_tra_soat ;
            }
          }
        }
      window.location = strLastAct;
    }else{
       if(confirm('Bạn có chắc muốn thoát khỏi chức năng này?')==false)
          return false;
       else {
          window.location="mainAction.do";
       }
    }
      
  }
    
  function msgAlertByAction(action, msgLTTDen){
    if(action=='<%=AppConstants.ACTION_EDIT%>'){
      if(msgLTTDen == '<%=AppConstants.SUCCESS%>'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.sua_thanh_cong"/>');
      }else if(msgLTTDen == '<%=AppConstants.FAILURE%>'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.sua_that_bai"/>');
      }else if(msgLTTDen == 'KHONG_CO_QUYEN'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
      }else if(msgLTTDen == 'NGAT_KET_NOI') {
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ngat_ket_noi"/>');
      }else if(msgLTTDen == 'MA_KHONG_HOP_LE'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ma_coa_khong_hop_le"/>');
      }else if(msgLTTDen == 'KHC_INVALID'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_khong_hop_le"/>');
      }else if(msgLTTDen == 'ERROR'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_loi"/>');
      }else if(msgLTTDen == 'KHC_ERROR'){
        if(errDescription != '' && errDescription != 'undefined'){
          jQuery("#dlgMessage-khc" ).html(errDescription);
        }else{
          jQuery("#dlgMessage-khc" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_loi"/>');
        }
      }else if(msgLTTDi == 'LTT_TRANG_THAI_DA_THAY_DOI'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.trang_thai_thay_doi"/>');
      }
      
    } else if (action=='LTT.DEN.SUA.LAI'){
      if(msgLTTDen == '<%=AppConstants.SUCCESS%>'){
        jQuery("#dlgMessage-khc" ).html('<fmt:message key="ltt_di.page.message.sua_thanh_cong"/>');
      }else if(msgLTTDen == '<%=AppConstants.FAILURE%>'){
        jQuery("#dlgMessage-khc" ).html('<fmt:message key="ltt_di.page.message.sua_that_bai"/>');
      }else if(msgLTTDen == 'KHONG_CO_QUYEN'){
        jQuery("#dlgMessage-khc" ).html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
      }else if(msgLTTDen == 'NGAT_KET_NOI') {
        jQuery("#dlgMessage-khc" ).html('<fmt:message key="ltt_di.page.message.ngat_ket_noi"/>');
      }
      else if(msgLTTDen == 'MA_KHONG_HOP_LE'){
        jQuery("#dlgMessage-khc" ).html('<fmt:message key="ltt_di.page.message.ma_coa_khong_hop_le"/>');
      }else if(msgLTTDen == 'KHC_INVALID'){
        if(errDescription != '' && errDescription != 'undefined'){
          jQuery("#dlgMessage-khc" ).html(errDescription);
        }else{
          jQuery("#dlgMessage-khc" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_khong_hop_le"/>');
        }
      }else if(msgLTTDen == 'ERROR'){
        jQuery("#dlgMessage-khc" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_loi"/>');
      }else if(msgLTTDen == 'KHC_ERROR'){
        if(errDescription != '' && errDescription != 'undefined'){
          jQuery("#dlgMessage-khc" ).html(errDescription);
        }else{
          jQuery("#dlgMessage-khc" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_loi"/>');
        }
      }else if(msgLTTDi == 'LTT_TRANG_THAI_DA_THAY_DOI'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.trang_thai_thay_doi"/>');
      }
    } else if (action=='<%=AppConstants.ACTION_APPROVED%>'){
      errDescription = '';
      if(msgLTTDen.indexOf("---")){
        var arrErrDesc = msgLTTDen.split("---");
        msgLTTDen = arrErrDesc[0];
        errDescription = arrErrDesc[1];
      }
      
      if(msgLTTDen == '<%=AppConstants.SUCCESS%>'){
        jQuery("#message").html('<fmt:message key="ltt_di.page.message.duyet_thanh_cong"/>');
      }else if(msgLTTDen == '<%=AppConstants.FAILURE%>'){
        if(errDescription != ''){
          errDescription = '<fmt:message key="ltt_di.error.ngoai_te_so_tien"/>';
          jQuery("#message" ).html(errDescription);
        }else{
          jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.duyet_that_bai"/>');
        }
      }else if(msgLTTDen == 'KHONG_CO_QUYEN'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
      }else if(msgLTTDen == 'NGAT_KET_NOI') {
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ngat_ket_noi"/>');
      }   
      
    }else if (action=='<%=AppConstants.ACTION_REJECT%>'){
      if(msgLTTDen == '<%=AppConstants.SUCCESS%>'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.day_lai_thanh_cong"/>');
      }else if(msgLTTDen == '<%=AppConstants.FAILURE%>'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.day_lai_that_bai"/>');
      }else if(msgLTTDen == 'KHONG_CO_QUYEN'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
      }else if(msgLTTDen == 'NGAT_KET_NOI') {
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ngat_ket_noi"/>');
      }          
    }
  }
  function initDialogTimKiem(){
    jQuery("#dialog-form-tim-kiem").dialog({
      autoOpen: false,resizable : false,
      maxHeight:"350",
      width: "800px",
      modal: true,
      buttons: {
        "Tìm kiếm": function() {
          findLTTSoYCTT('listLttDenAdd.do', '<%=strUserType%>', 'loadLTTDenJsonAction.do');
        },
        "Thoát": function() {        
          jQuery(this).dialog("close");
          jQuery('#row_ltt_0').find('input').focus();
        }
      },
      "Đóng": function() {
      jQuery(this).dialog("close");
      }
    });
  }
  function initDialogMsg(){
    jQuery("#dialog-message").dialog({
      autoOpen: false,
      maxHeight:160,
      width:380,
      modal: true,
      buttons: {
        Ok: function() {
          jQuery(this).dialog( "close" );
          window.location = 'listLttDenAdd.do';
          var idFieldFocus=jQuery("#focusField").val();
          if(idFieldFocus!=null && idFieldFocus!='')
            jQuery("#"+idFieldFocus).focus();
        }
     },
     "Đóng": function() {
//        window.location = 'listLttDenAdd.do';
      },
      close: function(event, ui) {
//        window.location = 'listLttDenAdd.do';
      }
    });
  }  
  function initDialogKHC(){
    jQuery("#dialog-khc" ).dialog({
      autoOpen: false,
      resizable: false,
      maxHeight:200,
      width:380,
      modal: true,
      buttons: {
        "OK": function() {
          jQuery(this).dialog("close");
          if('LTT.DEN.THEM.LAI' == action){
              //Van o man hinh them moi, cho nguoi dung thao tac nhu khi nhap them moi (Khong goi lai duoc addClicked vi mat COA ...)
              //Enable, disable fields disabled
              jQuery("#data-grid tr").removeAttr("onclick");
          jQuery("#data-grid tr").removeAttr("ondblclick");
          jQuery("#data-grid tr").removeAttr("onmouseout");
          jQuery("#data-grid tr").removeAttr("onmouseover");
              jQuery("#refresh").attr("disabled", true);
              jQuery("#ma_nhkb_nhan_cm").attr("disabled", false);
              jQuery("#nt_id").attr("disabled", false); 
                        
              //Hien nut            
              jQuery("#save").show(); 
              assignValue2Field("save", "ghi");
              jQuery("#save").html("&nbsp;<span class=\"sortKey\">G<\/span>hi"); 
              jQuery("#search").hide();
              jQuery("#fsLyDoDayLai").hide();
              
          }
        }
      }
    });
  }
  
  function ky(strUserType){
    try {
          var cert = jQuery("#eSign")[0];
          cert.InitCert();                   
          var serial = cert.Serial;       

          jQuery("#certserial").val(serial);
          var nd = jQuery("#noi_dung_ky").val();
          var sign = cert.Sign(nd);
         
          jQuery("#signature").val(sign);          
          if(strUserType != null){
            if(strUserType.indexOf("<%=AppConstants.NSD_KTT%>") != -1){
              jQuery("#chuky_ktt").val(sign);
            }
          }
          return true;
      }
      catch (e) {
          alert(e.description);
          return false;
      }
  }
  // In
    function formAction(){
      var f = document.forms[0];
      f.action = "lttDenRptAction.do";
       var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
      newDialog = window.open('', '_form', params);          
      f.target='_form';
      f.submit();
    } 
  function checkInputDayLai(){    
    currentAction = '<%=AppConstants.ACTION_REJECT%>';
    var result = true;
    if(jQuery("#lydo_ktt_day_lai").val() == null || jQuery("#lydo_ktt_day_lai").val() == ""){      
      alert("Bạn phải nhập Lý do KTT đẩy lại!");
      jQuery("#lydo_ktt_day_lai").focus();
      return false;
    }else if(jQuery("#lydo_ktt_day_lai").val().length > 500) {
      alert("Lý do KTT đẩy lại phải nhỏ hơn hoặc bằng 500 ký tư!");
      jQuery("#lydo_ktt_day_lai").focus();
      return false;
    }
    return result;
  }
  
  document.onkeydown = keyDownLTT;
  
  function startDownload(url) {   
      document.forms[0].action = 'inLTTDenJsonAction.do';
      document.forms[0].submit();
      window.open(url,'report'); 
} 
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>