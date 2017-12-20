<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys" %>
<%@ page import="com.seatech.framework.AppConstants" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.seatech.ttsp.ltt.LTTVO"%>
<%@ page import="com.seatech.framework.utils.StringUtil"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.LTTDiResource" />
<%@ include file="/includes/ttsp_header.inc"%> 
<link rel="stylesheet"  type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css" />
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/ltt.quyettoan.check.valid.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/LenhThanhToan.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/date.format.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
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
  
  String strQuyenLttDi ="";  
  if(request.getAttribute("QuyenLttDi") != null)
    strQuyenLttDi = request.getAttribute("QuyenLttDi").toString().toUpperCase();
  String strMsgLTTDi ="";
  String strUserType = "";  
  String strUserId = "";
  String strLastAction = "";
  String strTrangThai = "";
  String strLastForm = "";
  String strSoLTTDi = "";
  String strMaNsd = "";
  String strTenNsd = "";
  String strErrDescription = "";
  String strCheckAllowEditMoTaiNHKB = "";
  String strSpecialTKTN = "";
  String strTKTNAnNinh = "";
  if(request.getAttribute("StrTKTNAnNinh") != null)
    strTKTNAnNinh = request.getAttribute("StrTKTNAnNinh").toString();  
  if(request.getAttribute("StrSpecialTKTN") != null)
    strSpecialTKTN = request.getAttribute("StrSpecialTKTN").toString();
  if(request.getAttribute("MsgErrDesLTTDi") != null)
    strErrDescription = request.getAttribute("MsgErrDesLTTDi").toString();
  if(request.getAttribute("MsgDialogLTTDi") != null)
    strMsgLTTDi = request.getAttribute("MsgDialogLTTDi").toString();
  if(request.getAttribute("LastAction") != null)
    strLastAction = request.getAttribute("LastAction").toString();
  if(request.getAttribute("TrangThai") != null)
    strTrangThai = request.getAttribute("TrangThai").toString();
  if(request.getAttribute("LastForm") != null)
    strLastForm = request.getAttribute("LastForm").toString();  
  if(request.getAttribute("SoLTTDi") != null)
    strSoLTTDi = request.getAttribute("SoLTTDi").toString();
  if(request.getAttribute("CheckAllowEditMoTaiNHKB") != null)
    strCheckAllowEditMoTaiNHKB = request.getAttribute("CheckAllowEditMoTaiNHKB").toString();
  
  if(session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) != null){
    strUserType = session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
  }
  if(session.getAttribute(AppConstants.APP_USER_ID_SESSION) != null){
    strUserId = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
  }
  if(session.getAttribute(AppConstants.APP_USER_CODE_SESSION) != null){
    strMaNsd = session.getAttribute(AppConstants.APP_USER_CODE_SESSION).toString();
  }
  if(session.getAttribute(AppConstants.APP_USER_NAME_SESSION) != null){
    strTenNsd = session.getAttribute(AppConstants.APP_USER_NAME_SESSION).toString();
  }
  String strClass = "";  
  String strNHKB_COA = "";
  if(session.getAttribute(AppConstants.APP_KB_CODE_SESSION) != null){
    strNHKB_COA = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
  }
%>
<div class="box_common_content">
   <html:form action="/lttDiAddExc.do" method="post">      
      <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">Lệnh thanh toán đi</span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
        </tbody>
      </table>
      <table class="tableBound" width="100%">
      <tr>
        <td>
          <table width="99%">
            <tbody>
            <tr>
              <td><font color="red">
                  <html:errors/>
                  <label id="err"/>
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
          <table cellspacing="0" cellpadding="0" width="100%">
            <tbody>
              <tr>
                <td valign="top">   
                  <table class="bordertableChungTu" cellspacing="0" cellpadding="0"
                  width="100%">
                    <tbody>
                      <tr valign="top">
                        <td valign="top" width="15%" rowspan="2">
                          <div class="clearfixLTT">
                            <fieldset style="height:383px;" class="fieldsetLTTCSS">
                              <legend style="vertical-align:middle">
                                <b>Số yêu cầu thanh toán</b>
                              </legend>
                              <div class="sodientrasoattableLTT">
                                  <div>
                                    <table cellSpacing="0" cellPadding="0" width="100%">
                                      <thead>
                                        <tr>
                                          <th class="ui-state-default ui-th-column" height="20" width="54%;">
                                           <fmt:message key="ltt_di.page.label.so_yeu_cau_tt" />
                                          </th>
                                          <th width="28%;" class="ui-state-default ui-th-column">
                                           <fmt:message key="ltt_di.page.label.tinh_trang_master" />
                                          </th>
                                          <th height="20" width="8%;"
                                              class="ui-state-default ui-th-column">
                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          </th>
                                        </tr>
                                      </thead>
                                    </table>
                                  </div>
                                  <div style="height:270px;" class="ui-jqgrid-bdiv ui-widget-content">
                                  <div id="loading" style="display:none; padding-top:50px;">
                                    <p align="center"><img src="<%=request.getContextPath()%>/styles/images/ajax-loader.gif" /> <br/> Please Wait</p>
                                  </div>
                                  <div style="position: relative;">
                                    <table  class="data-grid" id="data-grid"
                                              style="border:0px;width:100%"
                                             cellspacing="0" cellpadding="0"                                  
                                             width="100%">
                                      <tbody>
                                        <logic:present name="<%=AppKeys.LTT_LIST_REQUEST_KEY%>">
                                          <logic:iterate id="list_ltt" name="<%=AppKeys.LTT_LIST_REQUEST_KEY%>" indexId="index" type="com.seatech.ttsp.ltt.LTTVO">
                                            <% 
                                              if(!"".equalsIgnoreCase(strSoLTTDi)){
                                                if(strSoLTTDi.equalsIgnoreCase(list_ltt.getId().toString())){
                                                  strClass = "ui-row-ltr ui-state-highlight";
                                                }else{
                                                  strClass = "ui-widget-content ui-row-ltr";
                                                }
                                              }else{
                                                if(index == 0){
                                                  strClass = "ui-row-ltr ui-state-highlight";
                                                } else{
                                                  strClass = "ui-widget-content ui-row-ltr";
                                                }  
                                              }
                                            %>
                                          <tr class="<%=strClass%>" id="row_ltt_<bean:write name="index"/>" 
                                            onmouseover="lttMouseOver('row_ltt_<bean:write name="index"/>');" 
                                            onmouseout="lttMouseOut('row_ltt_<bean:write name="index"/>');">
                                            <td width="44%;" align="left">
                                            <bean:write name="list_ltt" property="so_yctt"/>
                                            <input type="hidden" name="list_ltt_tongtien" value="<bean:write name="list_ltt" property="tong_sotien"/>" />
                                            <input type="hidden" name="list_ltt_ntid" value="<bean:write name="list_ltt" property="nt_id"/>" />
                                            </td>
                                            <td width="23%;" align="center">
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
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif" border="0" title="Đã gửi"/>
                                              </logic:equal>  
                                              <logic:equal value="08" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-but.jpg" border="0" title="Đã gửi Ngân hàng"/>
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
                                              
                                              <logic:equal value="14" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-but.jpg" border="0" title="Đã gửi - Ngân hàng đã nhận"/>
                                              </logic:equal>
                                              <logic:equal value="15" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/send-success.jpg" border="0" title="Đã gửi - Ngân hàng xử lý thành công"/>
                                              </logic:equal>
                                              <logic:equal value="16" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg" border="0" title="Đã gửi - Ngân hàng xử lý thất bại"/>
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
                                <div style="padding-top:5px;float: left;">
                                  <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                      <tr>
                                        <td width="92%" align="left">
                                          <fmt:message key="ltt_di.page.label.tinh_trang_master"/>: <span id="mo_ta_trang_thai" style="font-weight:bold;"></span>
                                        </td>
                                        <td width="8%" align="right"> 
                                          <span id="refresh" class="ui-icon ui-icon-refresh" onclick="refreshGridAddLTT('<%=strUserType%>', 'lttDiAdd.do', 'loadAddLTTDiJsonAction.do');" style="cursor:pointer;"></span>
                                        </td>
                                         <td>
                                          <span id="search" class="ui-icon ui-icon-search" title="Tìm kiếm" style="cursor:pointer;"></span>
                                        </td>
                                      </tr>
                                     <tr>
                                        <td align="left" colspan="3">                                        
                                        Loại tiền: <html:select property="nt_id_tke_tong" styleId="nt_id_tke_tong" onchange="loadLoaiTien(this);" style="width:95px;height:20px;vlaign:middle" styleClass="fieldTextCode" >
                                          <!--thuongdt-20170915 sua cho phep chon tat ca-->
                                        <html:option value="">Tất cả</html:option>  
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
                        <td width="85%" style="padding-bottom:2px;" height="200px;" valign="top">
                          <fieldset style="height:230px;">
                            <legend style="vertical-align:middle;"><b>Thông tin chung</b></legend>
                            <div style="width:auto; height: 200px;">
                              <table width="100%" cellspacing="0" cellpadding="1" bordercolor="#b0c4de" border="1" align="center" style="BORDER-COLLAPSE: collapse">
                                <tbody>
                                  <tr>
                                    <td width="18%" align="right" class="textNormal">NH/KB chuyển</td>
                                    <td width="25%" align="left">
                                      <html:text property="ma_nhkb_chuyen_cm" styleId="ma_nhkb_chuyen_cm" maxlength="8" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                                        onblur="getTenNganHang('ma_nhkb_chuyen_cm', 'ten_nhkb_chuyen_cm', 'nhkb_chuyen')" style="WIDTH: 55px;"/>&nbsp;
                                      <html:text property="ten_nhkb_chuyen_cm" styleId="ten_nhkb_chuyen_cm"
                                      styleClass="fieldTextTrans" readonly="true" onmouseout="UnTip()"
                                      onmouseover="Tip(this.value)" style="WIDTH: 60%;"/>                          
                                      <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen" />
                                    </td>
                                    <td width="15%" align="right"><fmt:message key="ltt_di.page.label.nh_kb_nhan"/></td>
                                    <td colspan="3" align="left" valign="top">                          
                                      <html:select property="ma_nhkb_nhan_cm" styleId="ma_nhkb_nhan_cm"
                                      onblur="getTenNganHang('ma_nhkb_nhan_cm', 'ten_nhkb_nhan_cm', 'nhkb_nhan')" 
                                        styleClass="fieldText"
                                        style="width:28%;height: 20px;" 
                                        >
                                        <logic:notEmpty name="colDMNH">
                                          <logic:iterate id="dmnh" name="colDMNH">
                                            <logic:equal value="<%=strMa_nhkb_nhan_cm%>" name="dmnh" property="ma_nh">
                                              <option value='<bean:write name="dmnh" property="ma_nh" />' selected="selected"> <bean:write name="dmnh" property="ma_nh" /> </option>
                                            </logic:equal>
                                            <logic:notEqual value="<%=strMa_nhkb_nhan_cm%>" name="dmnh" property="ma_nh">
                                              <option value='<bean:write name="dmnh" property="ma_nh" />'> <bean:write name="dmnh" property="ma_nh" /> </option>
                                            </logic:notEqual>
                                          </logic:iterate>
                                        </logic:notEmpty>
                                        <html:option styleClass="jecEditableOption" value=""></html:option>
                                      </html:select>
                                      <html:text property="ten_nhkb_nhan_cm" styleId="ten_nhkb_nhan_cm" 
                                      styleClass="fieldTextTrans" readonly="true" onmouseout="UnTip()" 
                                      onmouseover="Tip(this.value)" style="WIDTH: 69%;"/>
                                      <html:hidden property="nhkb_nhan" styleId="nhkb_nhan" value="" />
                                    </td>
                                  </tr>
                                  <tr>
                                    <td width="18%" align="right" ><fmt:message key="ltt_di.page.label.so_ct_goc"/></td>
                                    <td width="30%" align="left">
                                      <html:text property="so_ct_goc" styleId="so_ct_goc" styleClass="fieldText" maxlength="15" onkeypress="return numberBlockKey1();" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>                              
                                    </td>
                                    <td width="20%" align="right"><fmt:message key="ltt_di.page.label.ngay_ct"/> </td>
                                    <td width="16%" align="left" valign="middle">
                                      <html:text property="ngay_ct" styleId="ngay_ct" styleClass="fieldText" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                                      onblur="checkDateTTSP(this); javascript:return mask(this.value,this,'2,5','/'); CheckDateOnClient('ngay_ct');"  style="WIDTH: 60px;"/>
                                      <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif" border="0" id="ngay_ct_btn" width="20" style="vertical-align:middle;" />														
                                        <script type="text/javascript">
                                          Calendar.setup(
                                          {
                                              inputField  : "ngay_ct",      // id of the input field
                                              ifFormat    : "%d/%m/%Y",      // the date format
                                              button      : "ngay_ct_btn"    // id of the button
                                            } );
                                        </script>
                                    </td>
                                    <td width="10%" align="right">Mã NT</td>
                                    <td id="td_nt_id" width="6%" align="left">
                                      <html:select property="nt_id" styleId="nt_id" onchange="changeMaNT();" style="width:115px;height:20px;" styleClass="fieldText" >
                                        <logic:notEmpty name="listDMTienTe">
                                          <logic:present name="listDMTienTe">
                                            <logic:iterate id="objNT" name="listDMTienTe" type="com.seatech.ttsp.dmtiente.DMTienTeVO" indexId="index">
                                                <option value="<%=objNT.getId().toString()%>"><%=objNT.getMa()%></option>
                                            </logic:iterate>
                                          </logic:present>
                                        </logic:notEmpty>
                                      </html:select>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td align="right" ><fmt:message key="ltt_di.page.label.so_yctt"/></td>
                                    <td  align="left">
                                      <html:text  property="so_yctt" 
                                      styleId="so_yctt" maxlength="20" styleClass="fieldText" onmouseout="UnTip()" 
                                      onmouseover="Tip(this.value)" 
                                      onfocus="autoSelect(this);" style="WIDTH: 120px;"/>
                                    </td>
                                    <td align="right">Số LTT</td>
                                    <td align="left">
                                      <html:text property="id" styleId="id" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                                      style="WIDTH: 110px;"/>
                                    </td>
                                    <td align="right">Ngày TT</td>
                                    <td  align="left">
                                      <html:text property="ngay_tt" styleId="ngay_tt" styleClass="fieldText" onmouseout="UnTip()" 
                                      onmouseover="Tip(this.value)" style="WIDTH: 110px"
                                      onblur="checkDateTTSP(this); javascript:return mask(this.value,this,'2,5','/'); CheckDateOnClient('ngay_ct');"></html:text>                        
                                      <input type="hidden" id="ngay_tt_temp"/>
                                    </td>                        
                                  </tr>
                                  <tr>
                                    <td align="right" >Người lập</td>
                                    <td  align="left">
                                      <html:text property="ma_ttv_nhan" 
                                        styleId="ma_ttv_nhan" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  
                                        style="WIDTH: 28%;"/>
                                      <html:text property="ten_ttv_nhan" 
                                      styleId="ten_ttv_nhan" styleClass="fieldTextTrans" readonly="true" onmouseout="UnTip()" 
                                      onmouseover="Tip(this.value)"  style="WIDTH: 64%;"/>
                                      <html:hidden property="ttv_nhan" styleId="ttv_nhan" />
                                    </td>
                                    <td align="right">Ngày lập</td>
                                    <td align="left">
                                      <html:text property="ngay_hoan_thien" 
                                      styleId="ngay_hoan_thien" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 110px;"/>
                                    </td>
                                    <td align="right">Nguồn</td>
                                    <td align="left">                          
                                      <span id="lenh_thucong_tabmis" ></span>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td align="right" >Người kiểm soát</td>
                                    <td align="left">
                                      <html:text property="ma_ktt_duyet"  styleId="ma_ktt_duyet" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 28%;"/>
                                      <html:text property="ten_ktt_duyet"  styleId="ten_ktt_duyet" styleClass="fieldTextTrans" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 64%;"/>
                                      <html:hidden property="ktt_duyet" styleId="ktt_duyet" />
                                    </td>
                                    <td align="right">Ngày kiểm soát</td>
                                    <td align="left">
                                      <html:text property="ngay_ktt_duyet" styleId="ngay_ktt_duyet" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 110px;"/>
                                    </td>
                                     <td align="right">Loại lệnh</td>
                                    <td align="left">
                                      <select name="ttloai_lenh" id="ttloai_lenh" onchange="showHideMuaBanNT()"  class="fieldText" style="width:115px;height:20px;">
                                        <option value="01" selected="true">Lệnh chuyển có</option>
                                        <option value="02">Lệnh rút tiền</option>
                                        <option value="03">TT bằng ngoại tệ khác</option>
                                      </select>
                                    </td>
                                  </tr>                                  
                                  <tr>
                                    <td  align="right" >Tổng số tiền</td>
                                    <td width="30%" colspan="5" align="left" id="td_tong_sotien">
                                      <html:text property="tong_sotien" styleId="tong_sotien" styleClass="fieldTextRightCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 209px;"/>
                                      
                                    </td>
                                    <td align="right" style="display:none" id="td_loai_lable_loai_phi">
                                    Loại phí&nbsp; 
                                    </td>
                                    <td  style="display:none" id="td_loai_field_loai_phi">
                                      <html:select property="phi" styleId="phi" styleClass="fieldText" style="width:115px;height:20px;">
                                          <html:option value="OUR">Phí ngoài</html:option>
                                          <html:option value="BEN">Phí trong</html:option>  
                                          <html:option value="SHA">Phí chia sẻ</html:option>  
                                      </html:select>
                                    </td>
                                  </tr> 
                                  <tr>
                                    <td align="right" ><fmt:message key="ltt_di.page.label.nd_tt"/></td>
                                    <td width="30%" colspan="5" align="left">
                                      <html:textarea property="ndung_tt" styleId="ndung_tt" styleClass="fieldTextArea" rows="3"   style="WIDTH: 440px;"></html:textarea>
                                    </td>
                                  </tr>
                                  <tr style="display:none;" id="tr_mua_ban">
                                    <td align="right" >Mã ngoại tệ t.toán:</td>
                                    <td width="30%" align="left">
                                      <html:select property="ma_nt_mua_ban" styleId="ma_nt_mua_ban" style="width:115px;height:20px;" styleClass="fieldText"  onchange="checkMuaVND();">
                                        <option value=""></option>
                                        <logic:notEmpty name="listDMTienTeAll">
                                          <logic:present name="listDMTienTeAll">
                                            <logic:iterate id="objNT" name="listDMTienTeAll" type="com.seatech.ttsp.dmtiente.DMTienTeVO" indexId="index">
                                                <option value="<%=objNT.getMa()%>"><%=objNT.getMa()%></option>
                                            </logic:iterate>
                                          </logic:present>
                                        </logic:notEmpty>
                                      </html:select>
                                    </td>
                                    <td align="right">Số tiền ngoại tệ t.toán:</td>
                                    <td width="30%" colspan="3" align="left">                                      
                                      <html:text property="so_tien_mua_ban" style="width:96%;" styleId="so_tien_mua_ban" onkeypress="return numberBlockKeySoTien(this, document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText);"                                                        
                                                     onblur="formatNumberJQuery('so_tien_mua_ban','ma_nt_mua_ban','ma_nt_mua_ban_old');" 
                                                     onfocus="checkMaNTMuaBan();"
                                                     styleClass="fieldTextRight"/>
                                      <input type="hidden" id="ma_nt_mua_ban_old" value="" >
                                    </td>
                                  </tr>           
                                  </tbody>
                                </table> 
                            </div>
                          </fieldset>
                          <fieldset style="height:80px;padding-top:6px;">
                            <legend style="vertical-align:middle"><b>Thông tin người chuyển</b></legend>
                              <div style="width:auto; ">
                                <table style="width:100%; border: 1px solid #8eb9e5; input focus: background-color: #ffffb5;" cellpadding="0" cellspacing="0">
                                  <tr>
                                      <td width="12%" align="right">
                                        <fmt:message key="ltt_di.page.label.dv_chuyen"/>
                                      </td>
                                      <td width="30%" align="left">
                                        &nbsp;<html:text property="ten_tk_chuyen" onmouseout="UnTip()" 
                                          onmouseover="Tip(this.value)" styleId="ten_tk_chuyen" styleClass="fieldText" style="width:98%;"/>
                                      </td>
                                      <td width="20%" rowspan="3" align="right" valign="middle">
                                         <fmt:message key="ltt_di.page.label.tt_kh_chuyen"/>
                                      </td>
                                      <td width="30%" rowspan="3" align="left">
                                        <html:textarea style="width:98%" cols="50" rows="4" property="ttin_kh_chuyen" styleId="ttin_kh_chuyen" styleClass="fieldTextArea"></html:textarea>
                                      </td> 
                                    </tr>
                                  <tr>
                                    <td width="10%" align="right">
                                       <fmt:message key="ltt_di.page.label.tk"/>
                                    </td>
                                    <td width="60%" colspan="2" align="left">
                                      &nbsp;<html:text property="so_tk_chuyen" styleId="so_tk_chuyen" styleClass="fieldText" style="width:90px;" maxlength="12"/>
                                    </td>                  
                                  </tr>
                                  <tr>
                                    <td width="10%" align="right">
                                      <fmt:message key="ltt_di.page.label.mo_tai_nh_kb"/>
                                    </td>
                                    <td width="60%" colspan="3" align="left">
                                       &nbsp;<html:text property="ma_nhkb_chuyen" styleId="ma_nhkb_chuyen" maxlength="8" styleClass="fieldText" 
                                        onmouseout="UnTip()" onmouseover="Tip(this.value)"
                                        onblur="getTenNganHang('ma_nhkb_chuyen', 'ten_nhkb_chuyen', 'id_nhkb_chuyen');" 
                                        onkeydown="enterTab2SoTKorCOA(event);" style="WIDTH: 90px;"/>&nbsp;
                                      &nbsp;<html:text property="ten_nhkb_chuyen" styleId="ten_nhkb_chuyen" styleClass="fieldTextTrans" onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 200px;"/>
                                      <html:hidden property="id_nhkb_chuyen" styleId="id_nhkb_chuyen" />
                                    </td>
                                  </tr>                                  
                                </table>    
                              </div>
                          </fieldset> 
                        </td>
                      </tr>
                      <tr></tr>
                      <tr>
                        <td width="100%" colspan="2">
                            <div style="width:auto; ">
                                <table style="width:100%; border: 1px solid #8eb9e5;" cellpadding="0" cellspacing="0">
                                  <tr>
                                    <td width="100%" colspan="4">
                                       <table border="0" cellpadding="0" cellspacing="0" width="100%">                            
                                          <tr>
                                            <td width="20%" align="left">
                                               <b>Chi tiết các khoản mục</b>
                                            </td>
                                            <td width="60%" colspan="3" align="right">                                                
                                                <button type="button" name="cmdAddRow" id="cmdAddRow" onclick="addDetailCOARow();" onmouseover="Tip('Thêm mới (Alt +)')" onmouseout="UnTip()" accesskey="=">+</button>
                                            </td>
                                          </tr>
                                        </table>
                                      </td>
                                  </tr>  
                                  <tr>
                                    <td width="100%" colspan="4">
                                        <div style="overflow-y:auto; height:120px; width:100%;">
                                           <table id="tblThongTinChiTietCOA" style="width:100%; border: 1px solid #8eb9e5; border-collapse:collapse" border="1" cellpadding="0" cellspacing="0">                            
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
                                                     <html:text property="ma_quy" styleId="ma_quy" onkeypress="return numberBlockKey1();" style="width:95%;"  styleClass="fieldTextCenter" maxlength="2"/>
                                                  </td>
                                                  <td align="center">                                      
                                                     <html:text property="tkkt_ma"  styleId="tkkt_ma" onkeypress="return numberBlockKey1();" 
                                                       onblur="checkDMTKCheo(this, this.parentNode.parentNode);taoTaiKhoanFromCOA();" style="width:95%;" styleClass="fieldTextCenter" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="dvsdns_ma" styleId="dvsdns_ma" onkeypress="return numberBlockKey1();" 
                                                     onblur="taoTaiKhoanFromCOA();taoTenDVNhan(this.value);autoFillDefaultValue('dvsdns_ma');"
                                                            style="width:95%;" styleClass="fieldTextCenter" maxlength="7"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="capns_ma" styleId="capns_ma" onkeypress="return numberBlockKey1();" 
                                                     onblur="taoTaiKhoanFromCOA();autoFillDefaultValue('capns_ma');" style="width:95%;" styleClass="fieldTextCenter" maxlength="1"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="chuongns_ma" style="width:95%;" styleId="chuongns_ma" onkeypress="return numberBlockKey1();" onblur="autoFillDefaultValue('chuongns_ma');" styleClass="fieldTextCenter" maxlength="3"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="nganhkt_ma" style="width:95%;" styleId="nganhkt_ma" onkeypress="return numberBlockKey1();" onblur="autoFillDefaultValue('nganhkt_ma');" styleClass="fieldTextCenter" maxlength="3"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="ndkt_ma" style="width:95%;" styleId="ndkt_ma" onkeypress="return numberBlockKey1();" onblur="autoFillDefaultValue('ndkt_ma');" styleClass="fieldTextCenter" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="dbhc_ma" style="width:95%;" styleId="dbhc_ma" onkeypress="return numberBlockKey1();" onblur="autoFillDefaultValue('dbhc_ma');"  styleClass="fieldTextCenter" maxlength="5"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="ctmt_ma" style="width:95%;" styleId="ctmt_ma" onkeypress="return numberBlockKey1();" onblur="autoFillDefaultValue('ctmt_ma');"  styleClass="fieldTextCenter" maxlength="5"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="ma_nguon" style="width:95%;" styleId="ma_nguon" onblur="autoFillDefaultValue('ma_nguon');" onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenter" maxlength="2"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="kb_ma" style="width:95%;" styleId="kb_ma" onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenter" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="du_phong_ma" style="width:95%;" styleId="du_phong_ma" onkeypress="return numberBlockKey1();" onblur="autoFillDefaultValue('du_phong_ma');" styleClass="fieldTextCenter" maxlength="3"/>
                                                  </td>
                                                  <td align="left">
                                                     <html:text property="dien_giai"  style="width:97%;"  styleId="dien_giai"  styleClass="fieldText" />
                                                  </td>
                                                  <td align="right">
                                                     <html:text property="so_tien" style="width:96%;" styleId="so_tien" onkeypress="return numbersonly2(event, true);"  
                                                     onkeydown="skipTabReadonlyField1(event, 'id_delete_row');" 
                                                     onblur="formatNumberCOAJQuery(this);validSoTien(this);cal_tongtienCOA();" 
                                                     onfocus="checkSotienBeforForcus(this, document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText);"
                                                     styleClass="fieldTextRight"/>
                                                  </td>
                                                  <td align="center">
                                                      <img border="0" src="<%=request.getContextPath()%>/styles/images/delete1.gif" onclick="removeDetailCOARow(this);" onmouseout="UnTip()" onmouseover="Tip('Xóa')" id="id_delete_row" alt="">
                                                      <html:hidden property="sott_dong" styleId="sott_dong" />
                                                  </td>
                                                </tr>
                                                </logic:empty>
                                                <logic:notEmpty name="colLTTCTiet">
                                                <logic:iterate id="lttCTiet" name="colLTTCTiet" indexId="index">
                                                <tr id="coa_<bean:write name="index"/>">
                                                  <td align="center">
                                                      <html:text name="lttCTiet" property="ma_quy" styleId="ma_quy" onkeypress="return numberBlockKey1();" styleClass="fieldTextCenter" maxlength="2"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="tkkt_ma" styleId="tkkt_ma" onkeypress="return numberBlockKey1();" 
                                                       onblur="checkDMTKCheo(this, this.parentNode.parentNode);taoTaiKhoanFromCOA();" styleClass="fieldTextCenter" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="dvsdns_ma" styleId="dvsdns_ma" onkeypress="return numberBlockKey1();" 
                                                       onblur="taoTaiKhoanFromCOA();taoTenDVNhan(this.value);"
                                                            styleClass="fieldTextCenter" maxlength="7"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="capns_ma" styleId="capns_ma" onkeypress="return numberBlockKey1();" 
                                                     onblur="taoTaiKhoanFromCOA()" styleClass="fieldTextCenter" maxlength="1"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="chuongns_ma" styleId="chuongns_ma" onkeypress="return numberBlockKey1();" styleClass="fieldTextCenter" maxlength="3"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="nganhkt_ma" styleId="nganhkt_ma" onkeypress="return numberBlockKey1();" styleClass="fieldTextCenter" maxlength="3"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="ndkt_ma" styleId="ndkt_ma" onkeypress="return numberBlockKey1();" styleClass="fieldTextCenter" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="dbhc_ma" styleId="dbhc_ma" onkeypress="return numberBlockKey1();" styleClass="fieldTextCenter" maxlength="5"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="ctmt_ma" styleId="ctmt_ma" onkeypress="return numberBlockKey1();" styleClass="fieldTextCenter" maxlength="5"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="ma_nguon" styleId="ma_nguon" onkeypress="return numberBlockKey1();" styleClass="fieldTextCenter" maxlength="2"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text name="lttCTiet" property="kb_ma" styleId="kb_ma" onkeypress="return numberBlockKey1();" styleClass="fieldTextCenter" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="du_phong_ma" styleId="du_phong_ma" onkeypress="return numberBlockKey1();" styleClass="fieldTextCenter" maxlength="3"/>
                                                  </td>
                                                  <td align="left">
                                                    <html:text name="lttCTiet" property="dien_giai" styleId="dien_giai" styleClass="fieldText" />
                                                  </td>
                                                  <td align="right">
                                                     <html:text name="lttCTiet" property="so_tien" styleId="so_tien" onblur="formatNumberCOAJQuery(this);validSoTien(this);cal_tongtienCOA();" 
                                                     styleClass="fieldTextRightBold"/>
                                                  </td>
                                                  <td align="center">
                                                      <img border="0" src="<%=request.getContextPath()%>/styles/images/delete1.gif" onclick="removeDetailCOARow(this);" onmouseout="UnTip()" onmouseover="Tip('Xóa')" id="id_delete_row" alt="">
                                                      <html:hidden name="lttCTiet" property="sott_dong" styleId="sott_dong" />
                                                  </td>
                                                </tr>
                                                </logic:iterate>
                                                </logic:notEmpty>
                                              </tbody>
                                           </table>
                                       </div>
                                    </td>             
                                  </tr>
                                </table>
                            </div>
                        </td>
                      </tr>
                      <tr>
                        <td width="100%" colspan="2">
                          <fieldset style="width:auto;">
                            <legend style="vertical-align:middle"><b>Thông tin người nhận</b></legend>
                              <div style="width:auto;">
                                <table style="width:100%; border: 1px solid #8eb9e5; BORDER-COLLAPSE: collapse;" cellpadding="1" cellspacing="0">
                                  <tr>
                                    <td width="10%" align="right">
                                      <fmt:message key="ltt_di.page.label.tt_kh_nhan.dv_nhan"/>
                                    </td>
                                    <td width="30%" align="left">
                                      &nbsp;<html:text property="ten_tk_nhan" styleId="ten_tk_nhan" styleClass="fieldText" style="width:98%;"/>
                                    </td>
                                    <td width="25%" rowspan="3" align="right" valign="middle">
                                       <fmt:message key="ltt_di.page.label.tt_kh_nhan"/>
                                    </td>
                                    <td width="35%" rowspan="3" align="left">
                                      <html:textarea cols="50" style="width:98%" rows="3" property="ttin_kh_nhan" styleId="ttin_kh_nhan" styleClass="fieldTextArea" onkeydown="if(event.keyCode==13) event.keyCode=9;"></html:textarea>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td width="10%" align="right">
                                       <fmt:message key="ltt_di.page.label.tk"/>
                                    </td>
                                    <td width="60%" colspan="2" align="left">
                                      &nbsp;<html:text property="so_tk_nhan" styleId="so_tk_nhan" maxlength="35" styleClass="fieldText" style="width:180px;"/>
                                    </td> 
                                  </tr>
                                  <tr>
                                    <td width="10%" align="right">
                                      <fmt:message key="ltt_di.page.label.mo_tai_nh_kb"/>
                                    </td>
                                    <td width="60%" colspan="3" align="left">                          
                                      &nbsp;<html:text property="ma_nhkb_nhan" styleId="ma_nhkb_nhan" styleClass="fieldText" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                                        onblur="if(this.value != null && this.value != ''){getTenNganHang('ma_nhkb_nhan', 'ten_nhkb_nhan', 'id_nhkb_nhan')} else{id_nhkb_nhan.value=''} "  onkeydown="if(event.keyCode==13){ event.keyCode=9; }else if(event.keyCode==121){ callLov();}"
                                        style="WIDTH: 90px;"/>&nbsp;
                                      &nbsp;<html:text property="ten_nhkb_nhan" styleId="ten_nhkb_nhan" styleClass="fieldText" onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 200px;"/>
                                      <html:hidden property="id_nhkb_nhan" styleId="id_nhkb_nhan"/>
                                      <button id="btnLov" name="btnLov" type="button" onclick="callLov()">Tìm</button>                                    </td>                  
                                  </tr>
                                  <tr style="display:none" id="tr_nh_trung_gian">
                                    <td width="10%" align="right">
                                       NH trung gian
                                    </td>
                                    <td width="60%" colspan="2" align="left">                                      
                                      &nbsp;<html:text property="nh_tgian" styleId="nh_tgian" styleClass="fieldText" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                                        onblur="if(this.value != null && this.value != ''){getTenNganHang('nh_tgian', 'ten_nh_tgian', 'id_nh_trung_gian')} else{id_nh_trung_gian.value=''}; "  onkeydown="if(event.keyCode==13) event.keyCode=9;"
                                        style="WIDTH: 90px;"/>&nbsp;&nbsp;
                                      <html:text property="ten_nh_tgian" styleId="ten_nh_tgian" styleClass="fieldText" onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 200px;" onblur="jQuery('#save').focus();"/>
                                      <html:hidden property="id_nh_trung_gian" styleId="id_nh_trung_gian"/>
                                      <button id="btnLovNHTgian" name="btnLovNHTgian" type="button" onclick="callLovNHTgian()">Tìm</button> 
                                    </td>                     
                                    <td><html:checkbox property="vay_tra_no_nn" styleId="vay_tra_no_nn" value ="Y" /><font color="Blue" size="4px">Lệnh chi trả nợ nước ngoài</font></td>
                                  </tr>
                                </table>                    
                              </div>
                          </fieldset>
                        </td>
                       </tr>
                       <tr>
                        <td width="100%" colspan="2">
                          <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%">
                              <tr align="right">
                                <td>
                                  <input type="hidden" id="actionForSave" />
                                  <span>
                                  <button id="save" onclick="checkSuccess('lttAdd');" class="ButtonCommon" type="button" accesskey="G"><span class="sortKey">G</span>hi</button>
                                  </span>
                                  <span>
                                  <button id="exit" class="ButtonCommon" type="button" accesskey="o">Th<span class="sortKey">o</span>át</button>                     
                                  </span>
                                  <span id="span" style="display:none;"></span>
                                </td> 
                              </tr>
                            </table>
                        </td>
                      </tr>
                    </tbody>
                  </table>                  
                </td>                
              </tr>
              
              
            </tbody>
          </table>
        </td>
      </tr>
      </table>
      
     
      
      <input type="hidden" id="nt_id_old"/>
      <input type="hidden" id="focusField" name="focusField" value=""/>
      
      
            <div id="dialog-form-tim-kiem-so-yctt"  title="<fmt:message key="ltt_di.page.title.dialog_form_tim_kiem_so_yctt"/>">
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
                        <!--thuongdt-20170915 sua cho phep chon tat ca-->
                       <option value="">Tất cả</option>
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
      </div>
      
      
  </html:form>
</div>
<div id="dialog-message" title="<fmt:message key="ltt_di.page.title.dialog_message"/>">
    <p style="vertical-align:middle;">
      <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="message"></span>
    </p>
</div>
<!--<div id="dialog" title="<fmt:message key="ltt_di.page.title.dialog_message"/>">
    <p style="vertical-align:middle;">
      <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="dlgMessage"></span>
    </p>
</div>-->
<div id="dialog-confirm" title="<fmt:message key="ltt_di.page.title.dialog_confirm"/>" >
    <p style="vertical-align:middle;"><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="msg-confirm"></span>
    </p>
</div>
<div id="dialog-khc" title="<fmt:message key="ltt_di.page.title.dialog_message"/>" >
    <p style="vertical-align:middle;"><span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="dlgMessage-khc"></span>
    </p>
</div>
<div id="dialog-form-lov-dm" title="<fmt:message key="ltt_di.page.title.dialog_form_tim_kiem"/>">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDM.jsp" %>
</div>

<script type="text/javascript" charset="utf-8"> 
  jQuery.noConflict(); 	
  jQuery(document).ready(function(){
    jQuery("#actionForSave").val("addnew");
    //Global variable TKTN dac biet, an ninh
    arrSpecialTKTN = new Array(<%=strSpecialTKTN%>);
    arrTKTNAnNinh = new Array(<%=strTKTNAnNinh%>);
    //alert(arrTKTNAnNinh[0]);    
    // set limit char for 2 field 
    
    jQuery("#ttin_kh_nhan").keyup(function () {
        limitChars(jQuery("#ttin_kh_nhan").attr('id'), 146);
    });
    jQuery("#ttin_kh_chuyen").keyup(function () {
        limitChars(jQuery("#ttin_kh_chuyen").attr('id'), 146);
    }); 
    // end 
     
    jQuery("#save").show();
    assignValue2Field("save", "ghi");
    jQuery("#save").html("&nbsp;<span class=\"sortKey\">G<\/span>hi");
    assignValue2Field("ngay_tt", '<%=StringUtil.DateToString(new Date(),"dd/MM/yyyy")%>');
    assignValue2Field("ngay_ct", '<%=StringUtil.DateToString(new Date(),"dd/MM/yyyy")%>');
    assignValue2Field("ngay_tt_temp", '<%=StringUtil.DateToString(new Date(),"dd/MM/yyyy")%>');
    assignValue2Field("ngay_hoan_thien", '<%=StringUtil.DateToString(new Date(),"dd/MM/yyyy HH:mm:ss")%>');
    var ma_nhkb = '<%=strNHKB_COA%>';
    jQuery("#kb_ma").val(ma_nhkb);
    jQuery("#nt_id").val (jQuery("#nt_id option:contains('VND')").val());
    jQuery("#ma_nhkb_nhan_cm").focus();
    jQuery("#ttv_nhan").val('<%=strUserId%>');      
    jQuery("#ma_ttv_nhan").val('<%=strMaNsd%>');      
    jQuery("#ten_ttv_nhan").val('<%=strTenNsd%>');
    
    assignValue2Field("ma_nhkb_chuyen_cm", "<%=session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION)%>");
    assignValue2Field("nhkb_chuyen", "<%=session.getAttribute(AppConstants.APP_NHKB_ID_SESSION)%>");
    assignValue2Field("ten_nhkb_chuyen_cm", "<%=session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION)%>");
    
    jQuery("#ma_nhkb_nhan_cm").val("<%=strMa_nhkb_nhan_cm%>");
    assignValue2Field("ten_nhkb_nhan_cm", "<%=strTen_nhkb_nhan_cm%>");      
    assignValue2Field("nhkb_nhan", "<%=strNhkb_nhan%>");
        
    assignValue2Field("ma_nhkb_chuyen", "<%=session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION)%>");
    assignValue2Field("ten_nhkb_chuyen", "<%=session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION)%>");
    assignValue2Field("id_nhkb_chuyen", "<%=session.getAttribute(AppConstants.APP_NHKB_ID_SESSION)%>");
    
    if('<%=strMa_nhkb_nhan_cm%>' != '')
      assignValue2Field("ma_nhkb_nhan", "<%=strMa_nhkb_nhan_cm%>");
    if('<%=strTen_nhkb_nhan_cm%>' != '')
      assignValue2Field("ten_nhkb_nhan", "<%=strTen_nhkb_nhan_cm%>");
    if('<%=strNhkb_nhan%>' != '')
      assignValue2Field("id_nhkb_nhan", "<%=strNhkb_nhan%>");

    checkAllowEditMoTaiNHKB = '<%=strCheckAllowEditMoTaiNHKB%>';
    if(checkAllowEditMoTaiNHKB == 'NO' || checkAllowEditMoTaiNHKB == '' || checkAllowEditMoTaiNHKB == 'undefined'){
      jQuery("#ma_nhkb_chuyen").attr({readOnly:true});
      setClass2Field("ma_nhkb_chuyen", "fieldTextCode");
      jQuery("#ten_nhkb_chuyen").attr({readOnly:true});
    }
    
    soLTTDi = '<%=strSoLTTDi%>';
    strLoaiUser = "<%=strUserType%>";    //alert(strLoaiUser);
    //An field ma_ttv cho viec tim kiem neu la ttv, hien thi lydo_day_lai        
    if(strLoaiUser != null){
      if(strLoaiUser.indexOf("<%=AppConstants.NSD_TTV%>") != -1){
        jQuery("#ma_ttv").hide();
      }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_KTT%>") != -1){
      }
    }
    
    var strLastAct = "";
    var strTrangThai = '<%=strTrangThai%>';
    if(strTrangThai != '' && strTrangThai != null){
      strTrangThai = getStrTrangThai(strTrangThai);
      jQuery("#mo_ta_trang_thai").html(strTrangThai);
    }
    
    //init dialog: message, Tim kiem so_yctt, confirm
    jQuery("#dialog:ui-dialog").dialog( "destroy" );
    initDialogMsg(soLTTDi);
    initDialogConfrm();
    initDialogLov();
    
    //
    //dialogMsg("");
    initDialogKHC();
    // Dieu khien an hien cac nut ban dau vao
    var quyenLttDi = "<%=strQuyenLttDi%>";
                
    //Cho NHKB Nhan 
    jQuery(function () {
        jQuery(':editable');
        jQuery('#ma_nhkb_nhan_cm').jec();
    });
    /*jQuery('#ma_nhkb_nhan_cm').change(function() {
        var str1 = ""; 
        jQuery("select option:selected").each(function () { 
          str1 = jQuery(this).val(); 
        }); 
        jQuery('#ma_nhkb_nhan_cm').val(str1);
		}).change();*/
    //Local variable
    var msgLTTDi = '<%=strMsgLTTDi%>';
    var strLastFrm = '<%=strLastForm%>';
    //Global variable
    arrFieldReadonly = new Array("id", "ma_ttv_nhan", "ten_ttv_nhan", "ngay_hoan_thien", "ma_ktt_duyet", 
      "ten_ktt_duyet", "ngay_ktt_duyet", "tong_sotien", "ten_nhkb_chuyen_cm", "ten_nhkb_nhan_cm", "ma_nhkb_chuyen_cm");
    action = '<%=strLastAction%>';
    currentAction = '';
    firstCheck = false;
    counter = 0;  
    errDescription = '<%=strErrDescription%>'; 
    
    jQuery("#lenh_thucong_tabmis").html("Lệnh thủ công");
    
    readOnlyFields(arrFieldReadonly, true);      
    jQuery.each(arrFieldReadonly, function(index, value){
        strId = value.toString();
        if (document.getElementById(strId) != null) {
            setClass2Field(strId, "fieldTextCode");
        }
    });
     var tabIndex=101;     
        jQuery.each(arrFieldFocusTTChungLapMoi, function (index, value) {
            strId = value.toString();
            if (document.getElementById(strId) != null) {
                if(document.getElementById(strId).readOnly!=true)
                {
                  document.getElementById(strId).tabIndex  = ++tabIndex;
                }
            }
        }); 
        var tabIndexTTNT=1001;
        
        jQuery.each(arrFieldFocusTTNTLapMoi, function (index, value) {
            strId = value.toString();
            if (document.getElementById(strId) != null) {
                if(document.getElementById(strId).readOnly!=true)
                {
                  document.getElementById(strId).tabIndex  = tabIndexTTNT;
                }
            }
        });         
    if(msgLTTDi != null && msgLTTDi != '' && action != ''){
      if(msgLTTDi == 'KHONG_CO_QUYEN'){
        jQuery("#err").html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
      }
      else  if(strLastFrm != ''){
          jQuery("#tblThongTinChiTietCOA tr input").attr("readOnly", false);
          //readOnly mot so fields ko fai nhap trong COA
          var resultEveryFocus = false;
          resultEveryFocus = focusEveryTKTN();
          
          counter = 0;  
          
          if(strLastFrm == 'LAST.FORM.TRUNG.SOYCTT'){
              currentAction = '<%=AppConstants.ACTION_ADD%>';
              msgAlertByActionForm(action, msgLTTDi);
              jQuery("#dialog-confirm").dialog( "open" );
              //Hien thi truong nao con cho phep nhap, truong ko cho
              
          }else if(strLastFrm == 'LAST.FORM.ADD'){
          jQuery("#data-grid tr").removeAttr("onmouseout");
          jQuery("#data-grid tr").removeAttr("onmouseover");
              if('LTT.DI.THEM.LAI' == action){
                currentAction = '<%=AppConstants.ACTION_ADD%>';
                assignValue2Field("save", "ghi");
                jQuery("#save").html("&nbsp;<span class=\"sortKey\">G<\/span>hi");
              }   
              if(msgLTTDi != null && msgLTTDi != '' && msgLTTDi != 'undefined'){
                msgAlertByActionForm(action, msgLTTDi);                
                if(msgLTTDi == 'KHC_ERROR' || msgLTTDi == 'KHC_INVALID'){
                  //jQuery("#dialog-khc" ).dialog( "open" ); //ko dung dc, vi khi update lan gap loi KHC se loi tu dong chuyen sang lttAddExc.do
                  alert(errDescription);
                }else{
                  jQuery("#dialog" ).dialog( "open" );
                }
              }
          }  
      }else{
          currentAction = '<%=AppConstants.ACTION_ADD+AppConstants.ACTION_EDIT%>';//muc dich chi can currentAction khac trong
          msgAlertByActionForm(action, msgLTTDi);
          clearForm(arrFieldId);
      
          jQuery("#dialog-message" ).dialog( "open" );
      }
    }
    jQuery("#exit").click(function(){ 
      jQuery("#exit").focus();
      var param = '';
      if('<%=request.getParameter("referrer")%>'!='null'){
        strLastAct='<%=request.getParameter("referrer")%>';
        
        if(strLastAct=='themdtslttdi')
          param='?action=add&id=<%=request.getParameter("so_ltt")%>';
        else if(strLastAct=='dtsoattloiView')
          param='?action=VIEW_DETAIL&id=<%=request.getParameter("id")%>';
      }else if(strLastAct == 'TraCuuLTT'){
        strLastAct = 'TraCuuLTT.do';
      }
      else if(currentAction != ''){
        strLastAct = 'listLttAdd.do';
      }else if(currentAction == ''){
        strLastAct = 'mainAction.do';
      }
      sbExitLttDi(strLastAct,param);
    });
  });  
  function callLov(){      
      jQuery("#loai_lov").val("DMNH");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_nhan");
      jQuery("#dialog-form-lov-dm").dialog( "open" );      
    }
    
  function callLovNHTgian(){      
      jQuery("#loai_lov").val("DMNH");
      jQuery("#ma_field_id_lov").val("nh_tgian");
      jQuery("#ten_field_id_lov").val("ten_nh_tgian");
      jQuery("#id_field_id_lov").val("id_nh_tgian");
      jQuery("#dialog-form-lov-dm").dialog( "open" );      
    }
  function sbExitLttDi(strLastAct, param) {	 
      if(strLastAct != null && strLastAct != ""){
        //window.history.go(-1);
        if(strLastAct.indexOf('.do') == -1){
          strLastAct = strLastAct + ".do" + param;
        }else{
          strLastAct = strLastAct + param;
        }        
        window.location = strLastAct;         
      }else{
         if(confirm('Bạn có chắc muốn thoát khỏi chức năng này?')==false)
            return false;
         else {
            window.location="listLttAdd.do";
            //window.location="mainAction.do";
         }
      } 
  }      
  function msgAlertByActionForm(action, msgLTTDi){
    if(action=='<%=AppConstants.ACTION_ADD%>'){
      msgAlert = '';
      if(msgLTTDi == '<%=AppConstants.SUCCESS%>'){
        //jQuery("#dialog-message" ).html('<fmt:message key="ltt_di.page.message.them_moi_thanh_cong"/>');        
        msgAlert = "Bạn đã lập LTT thành công ";
        //soLTTDi = '<%=strSoLTTDi%>';
        if(soLTTDi != null && soLTTDi != 0){
          msgAlert = msgAlert + "với số Lệnh thanh toán là: "+soLTTDi;
        }
        jQuery("#message" ).html(msgAlert);
      }else if(msgLTTDi == '<%=AppConstants.FAILURE%>'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.them_moi_that_bai"/>');
      }else if(msgLTTDi == 'KHONG_CO_QUYEN'){
        jQuery("#message").html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');        
      }else if(msgLTTDi == 'MA_KHONG_HOP_LE'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ma_coa_khong_hop_le"/>');
      }else if(msgLTTDi == 'INVALID'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_khong_hop_le"/>');
      }else if(msgLTTDi == 'ERROR'){
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_loi"/>');
      }
      else if(msgLTTDi == 'TRUNG_SO_YCTT'){        
        msgAlert = 'Số yêu cầu thanh toán đã tồn tại. \n Bạn có muốn tiếp tục?';
        jQuery("#msg-confirm").html(msgAlert);
        //jQuery("#message" ).html('Số yêu cầu thanh toán đã tồn tại! <br/> Bạn phải nhập một số khác!');
      }else if(msgLTTDi == 'NGAT_KET_NOI') {
        jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ngat_ket_noi"/>');
      } 
    }else if (action=='LTT.DI.THEM.LAI'){
      msgAlert = '';
      if(msgLTTDi == '<%=AppConstants.SUCCESS%>'){       
        msgAlert = "Bạn đã lập LTT thành công ";
        //soLTTDi = '<%=strSoLTTDi%>';
        if(soLTTDi != null && soLTTDi != 0){
          msgAlert = msgAlert + "với số Lệnh thanh toán là: "+soLTTDi;
        }
        jQuery("#dlgMessage" ).html(msgAlert);
      }else if(msgLTTDi == '<%=AppConstants.FAILURE%>'){
        jQuery("#dlgMessage" ).html('<fmt:message key="ltt_di.page.message.them_moi_that_bai"/>');
      }else if(msgLTTDi == 'KHONG_CO_QUYEN'){
        jQuery("#dlgMessage").html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
//        jQuery("#err_khongduquyen").html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
      }else if(msgLTTDi == 'MA_KHONG_HOP_LE'){
        jQuery("#dlgMessage" ).html('<fmt:message key="ltt_di.page.message.ma_coa_khong_hop_le"/>');
      }else if(msgLTTDi == 'KHC_INVALID'){
        if(errDescription != '' && errDescription != 'undefined'){
          jQuery("#dlgMessage-khc" ).html(errDescription);
        }else{
          jQuery("#dlgMessage-khc" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_khong_hop_le"/>');
        }
      }else if(msgLTTDi == 'ERROR'){
        jQuery("#dlgMessage" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_loi"/>');
      }else if(msgLTTDi == 'KHC_ERROR'){
        if(errDescription != '' && errDescription != 'undefined'){
          jQuery("#dlgMessage-khc" ).html(errDescription);
        }else{
          jQuery("#dlgMessage-khc" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_loi"/>');
        }
      }
      else if(msgLTTDi == 'TRUNG_SO_YCTT'){        
        msgAlert = 'Số yêu cầu thanh toán đã tồn tại. Bạn có muốn tiếp tục?';
        jQuery("#msg-confirm").html(msgAlert);
        //jQuery("#dlgMessage" ).html('Số yêu cầu thanh toán đã tồn tại! <br/> Bạn phải nhập một số khác!');
      }else if(msgLTTDi == 'NGAT_KET_NOI') {
        jQuery("#dlgMessage" ).html('<fmt:message key="ltt_di.page.message.ngat_ket_noi"/>');
      }         
    }
  }
  
  function initDialogMsg(soLtt){
    jQuery("#dialog-message").dialog({
      autoOpen: false,
      height:180,
      width:380,
      modal: true,
      buttons: {
        Ok: function() {
          jQuery(this).dialog("close");
          if(soLtt != "" && soLtt != null)
            window.location = 'lttDiAdd.do?id='+soLtt;
          else
            window.location = 'lttDiAdd.do';
          var idFieldFocus=jQuery("#focusField").val();
          if(idFieldFocus!=null && idFieldFocus!='')
            jQuery("#"+idFieldFocus).focus();
        }
     },
     "Đóng": function() {
        if(soLtt != "" && soLtt != null)
          window.location = 'lttDiAdd.do?id='+soLtt;
        else
          window.location = 'lttDiAdd.do';
      }
    });
  }
  
  function initDialogConfrm(){
    jQuery("#dialog-confirm" ).dialog({
      autoOpen: false,
      resizable: false,
      height:200,
      width:380,
      modal: true,
      buttons: {
        "Có": function() {
          //action here
          if (checkInput(jQuery("#actionForSave").val(),jQuery("#ttloai_lenh").val())) {
          document.forms[0].action = 'lttDiAddExc.do?dup_soyctt=Y';
          document.forms[0].submit();
          }
          jQuery(this).dialog("close");     
        },
        "Không": function() {
          jQuery(this).dialog("close");
          //Enable, disable fields disabled
          jQuery("#data-grid tr").removeAttr("onmouseout");
          jQuery("#data-grid tr").removeAttr("onmouseover");
          jQuery("#refresh").attr("disabled", true);
                             
          jQuery("#so_yctt").select();
          jQuery("#so_yctt").focus();
        }
      }
    });
  } 
  function showHideMuaBanNT(){
    var v_loai_lenh = jQuery("#ttloai_lenh").val();
    
    if(v_loai_lenh == '03'){
      jQuery("#tr_mua_ban").show();      
//      jQuery("#lbl_comment").text('(Số tiền dự tính bán)');
    }else{
      jQuery("#tr_mua_ban").hide();
//      jQuery("#lbl_comment").text('');
    }
  }
  function initDialogLov(){
    jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
//      buttons: {
//        "Tìm kiếm": function() {
//          findLTTSoYCTT('listLttDenAdd.do', '<%=strUserType%>', 'loadLTTDenJsonAction.do');
//        },
//        "Thoát": function() {        
//          jQuery( this ).dialog( "close" );
//        }
//      },
//      "Đóng": function() {
//      jQuery( this ).dialog( "close" );
//      }
    });
  }
  function initDialogKHC(){
    jQuery("#dialog-khc" ).dialog({
      autoOpen: false,
      resizable: false,
      height:200,
      width:380,
      modal: true,
      buttons: {
        "OK": function() {
            jQuery(this).dialog("close");
            firstCheck = true;
            
            if('LTT.DI.THEM.LAI' == action){
                //Van o man hinh them moi, cho nguoi dung thao tac nhu khi nhap them moi (Khong goi lai duoc addClicked vi mat COA ...)
                //Enable, disable fields disabled
          jQuery("#data-grid tr").removeAttr("onmouseout");
          jQuery("#data-grid tr").removeAttr("onmouseover");
                jQuery("#refresh").attr("disabled", true);
                          
                //Hien nut            
//                jQuery("#save").show(); 
//                assignValue2Field("save", "ghi");
//                jQuery("#save").html("&nbsp;<span class=\"sortKey\">G<\/span>hi"); 
                             
            }
        }
      }
    });
  }
   
    document.onkeydown = keyDownLTT; 
    
    
function refreshGridAddLTT(strUserType, strUrlRefresh, strUrlAction) {
    var urlRefresh = '';
    if (strUrlRefresh != null && strUrlRefresh != '') {
        urlRefresh = strUrlRefresh;
    }
    else {
        urlRefresh = 'listLttAdd.do';
    }
    jQuery.ajax( {
        type : "POST", url : urlRefresh, data :  {
            "action" : 'REFRESH', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (data != null) {
                if (data.logout != null && data.logout) {
                    document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                    document.forms[0].submit();
                }
                else {
                    if (data.error != undefined) {
                        jQuery("#dialog-message").html(data.error);
                        jQuery("#dialog-message").dialog("open");
                    }
                    else if (data.error == undefined) {                    
                        fillDataTableMasterAddLTTDi(data, strUserType, strUrlAction);
                    }
                }

            }
        },
        error : function (textstatus) {
            jQuery("#dialog-message").html(textstatus.responseText);
            jQuery("#dialog-message").dialog("open");
        }
    });
}    
 function fillDataTableMasterAddLTTDi(data, strUserType, strUrlAction) {
    var strTableData = "";
    var strImg = "";
    var strTitle = "";
    var strSoYCTT_ID = "";
    var id_ltt_default = 0;
    jQuery("#data-grid").html('');
    var tonglenh = 0;
    var tongtien = 0.0;
    var nt_idTemp = '';
    var check_nt = true;
    var vformat = 'VND';
    var sotien = '';
    if (data != null && data != 'undefined' && data != '') {
        jQuery.each(data, function (i, objectLTTDi) {
            
            tonglenh = tonglenh+1;
            sotien = objectLTTDi.tong_sotien;
            tongtien = tongtien + objectLTTDi.tong_sotien;           
            if (nt_idTemp !='' && objectLTTDi.nt_id != nt_idTemp)
              check_nt = false;
            nt_idTemp = objectLTTDi.nt_id;
            if (objectLTTDi.trang_thai == '01') {
                strImg = "/TTSP/styles/images/return.jpeg";
                strTitle = "KTT &#273;&#7849;y l&#7841;i";
            }
            else if (objectLTTDi.trang_thai == '02') {
                strImg = "/TTSP/styles/images/edit.gif";
                strTitle = "Ch&#7901; TTV ho&#224;n thi&#7879;n";
            }
            else if (objectLTTDi.trang_thai == '03') {
                strImg = "/TTSP/styles/images/wait.jpeg";
                strTitle = "Ch&#7901; KTT dy&#7879;t";
            }
            else if (objectLTTDi.trang_thai == '04') {
                strImg = "/TTSP/styles/images/gd_return.jpeg";
                strTitle = "G&#272; &#273;&#7849;y l&#7841;i";
            }
            else if (objectLTTDi.trang_thai == '05') {
                strImg = "/TTSP/styles/images/wait-gd.png";
                strTitle = "Ch&#7901; G&#272; duy&#7879;t";
            }
            else if (objectLTTDi.trang_thai == '06') {
                strImg = "/TTSP/styles/images/delete1.gif";
                strTitle = "H&#7911;y";
            }
            else if (objectLTTDi.trang_thai == '07') {
                strImg = "/TTSP/styles/images/sended-but.jpg";
                strTitle = "&#272;&#227; g&#7917;i - ch&#432;a v&#224;o giao di&#7879;n";
            }
            else if (objectLTTDi.trang_thai == '08') {
                strImg = "/TTSP/styles/images/sended-but.jpg";
                strTitle = "&#272;&#227; g&#7917;i Ng&#226;n h&#224;ng";
            }
            else if (objectLTTDi.trang_thai == '11') {
                strImg = "/TTSP/styles/images/sended-but.jpg";
                strTitle = "&#272;&#227; g&#7917;i - Ch&#7901; ch&#7841;y giao di&#7879;n";
            }
            else if (objectLTTDi.trang_thai == '12') {
                strImg = "/TTSP/styles/images/send-success.jpg";
                strTitle = "&#272;&#227; g&#7917;i - Giao di&#7879;n th&#224;nh c&#244;ng";
            }
            else if (objectLTTDi.trang_thai == '13') {
                strImg = "/TTSP/styles/images/send-false.jpg";
                strTitle = "&#272;&#227; g&#7917;i - Giao di&#7879;n th&#7845;t b&#7841;i";
            }

            else if (objectLTTDi.trang_thai == '14') {
                strImg = "/TTSP/styles/images/send-success.jpg";
                strTitle = "G&#7917;i ng&#226;n h&#224;ng th&#224;nh c&#244;ng";
            }
            else if (objectLTTDi.trang_thai == '15') {
                strImg = "/TTSP/styles/images/send-false.jpg";
                strTitle = "G&#7917;i ng&#226;n h&#224;ng kh&#244;ng th&#224;nh c&#244;ng";
            }
            else if (objectLTTDi.trang_thai == '16') {
                strImg = "/TTSP/styles/images/sended-false.jpg";
                strTitle = "&#272;&#227; g&#7917;i - Ng&#226;n h&#224;ng x&#7917; l&#253; th&#7845;t b&#7841;i";
            }

            if (strUrlAction == 'loadLTTDenJsonAction.do' || strUrlAction == 'loadQuyetToanAction.do')
                strSoYCTT_ID = objectLTTDi.id;
            else 
                strSoYCTT_ID = objectLTTDi.so_yctt;

            strSoYCTT_ID = "<input name=\"row_item\" id=\"" + i + "\" type=\"text\" value=\"" + strSoYCTT_ID + "\" onkeydown=\"arrowUpDownLTT(event);\" readonly=\"true\"  style=\"border:0px;font-size:10px;float:left;width:106px;\" />" + "<input  id=\"rowSelected\" type=\"hidden\" value=\'" + objectLTTDi.id + "\'/>";

            strTableData = strTableData + "<tr onmouseover=\"lttMouseOver('row_ltt_" + i + "');\"  onmouseout=\"lttMouseOut('row_ltt_" + i + "');\" " + "class=\"ui-widget-content jqgrow ui-row-ltr\" id=\"row_ltt_" + i + "\" >" + "<td width=\"53%;\" align=\"left\">" + strSoYCTT_ID + " <input type=\"hidden\" name=\"list_ltt_tongtien\" value='"+sotien+"' /> <input type=\"hidden\" name=\"list_ltt_ntid\" value='"+nt_idTemp+"' />  <\/td>" + "<td width=\"28%;\" align=\"center\"> <img src=\"" + strImg + "\" border=\"0\" title=\"" + strTitle + "\" <\/td>" + "<\/tr>";

            if (i == 0)
                id_ltt_default = objectLTTDi.id;
        });
       jQuery("#tong_so_mon").val(tonglenh);
       if(check_nt == true){
        if(nt_idTemp != '177')
           vformat = 'USD';
           jQuery("#tong_so_tien").val(CurrencyFormatted(tongtien, vformat)); 
          document.getElementById('nt_id_tke_tong').value = nt_idTemp;
       }else{
          jQuery("#tong_so_tien").val(CurrencyFormatted('0.0', vformat)); 
          document.getElementById('nt_id_tke_tong').value = '';
      }
          
    }
    else {
        deleteRowsInCOA("tblThongTinChiTietCOA");
        strTableData = '<tr><td colspan="5" align="center"><span style="color:red;">Kh&#244;ng t&#236;m th&#7845;y b&#7843;n ghi n&#224;o</span></td></tr>';        
    }
    jQuery("#data-grid").html('<tbody>' + strTableData + '<\/tbody>');
    
//    var strNumberResultSearch = 0;
//    if (data != null && data != 'undefined')
//        strNumberResultSearch = data.length;
//    var strAlert = "K&#7871;t qu&#7843;: " + strNumberResultSearch + " L&#7879;nh thanh to&#225;n th&#7887;a m&#227;n.";
//    jQuery("#resultSearch").html(GetUnicode(strAlert));
}   
 
     jQuery("#search").click(function(){     
      jQuery("#dialog-form-tim-kiem-so-yctt").dialog( "open" );           
      if(strLoaiUser != null && strLoaiUser != 'null'){
        if(strLoaiUser.indexOf("<%=AppConstants.NSD_TTV%>") != -1){
          jQuery("#nhkbchuyennhan").focus(); 
        }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_KTT%>") != -1){
          jQuery("#ttvnhan").focus();
        }
      }
    });
    initDialogTimKiem();
   function initDialogTimKiem(){
    jQuery("#dialog-form-tim-kiem-so-yctt").dialog({
      autoOpen: false,resizable : false,
//      height: "350px",
      maxHeight:"350",
      width: "800px",
      modal: true,
      buttons: {
        "Tìm kiếm": function() {
          findAddLTTSoYCTT('lttDiAdd.do', '<%=strUserType%>', 'loadAddLTTDiJsonAction.do');
          jQuery(this).dialog("close");
          jQuery('#row_ltt_0').find('input').focus();
        },
        "Thoát": function() {
          jQuery(this).dialog("close");
          jQuery('#row_ltt_0').find('input').focus();
        }
      },
      "Đóng": function() {
      }
    });
  }
  
  function findAddLTTSoYCTT(strUrlRefresh, strUserType, strUrlAction) {
    var urlRefresh = '';
    if (strUrlRefresh != null && strUrlRefresh != '') {
        urlRefresh = strUrlRefresh;
    }
    else {
        urlRefresh = 'listLttAdd.do';
    }
    var ttvnhan = jQuery("#ttvnhan").val(), nhkbchuyennhan = jQuery("#nhkbchuyennhan").val(), sotien = jQuery("#sotien").val(), soyctt = jQuery("#soyctt").val(), trangthai = jQuery("#trangthai").val();
    var soltt = jQuery("#soltt").val();
    var nt_id_find = jQuery("#nt_id_find").val();
    
    jQuery.ajax( {
        type : "POST", url : urlRefresh, data :  {
            "nt_id_find":nt_id_find,"ttvnhan" : ttvnhan, "nhkbchuyennhan" : nhkbchuyennhan, "sotien" : sotien, "soyctt" : soyctt, "trangthai" : trangthai, "soltt" : soltt, "action" : 'FIND_SoYCTT', "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (data != null && textstatus != null && textstatus == 'success') {
                if (data.logout != null && data.logout != 'undefined') {
                    document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                    document.forms[0].submit();
                }
                else {
                    if (data.error != undefined) {
                        jQuery("#dialog-message").html(data.error);
                        jQuery("#dialog-message").dialog("open");
                    }
                    else if (data.error == undefined) {
                        fillDataTableMasterAddLTTDi(data, strUserType, strUrlAction);
                    }
                }

            }
        },
        error : function (textstatus) {
            jQuery("#message").html(textstatus.responseText);
            jQuery("#dialog-message").dialog("open");
        }
    });
}
   function changeMaNTThongKe(obj, url){   
   
    nt_id = obj.value;
   
    if (nt_id != null && nt_id != '') {
        jQuery.ajax( {
            type : "POST", url : url, data :  {
                "action" : 'CHANGE_NT', "nt_id_find" : nt_id, "type":url, "nd" : Math.random() * 100000
            },
            dataType : 'json',  success : function (data, textstatus) {
                if (textstatus != null && textstatus == 'success') {
                    if (data == null) {
                        alert('Không lấy được dữ liệu');
                        return;
                    }
                    else { 
                   
                    jQuery.each(data, function (i, objectLTTtemp) {
                        jQuery("#tong_so_mon").val(objectLTTtemp.tong_so_mon);
                        jQuery("#tong_so_tien").val(CurrencyFormatted(objectLTTtemp.tong_so_tien, nt_id));    
                    })
                                            
                    }
                }
            }
        });
    }
} 
loadform();
function loadform(){
    var tongmon = 0;    
    var tongtien = 0.0;
    var nt_idTemp = '';
    var check_nt = true;
    var vformat = 'VND';
    var sotien = document.getElementsByName('list_ltt_tongtien');
    var ma_nt = document.getElementsByName('list_ltt_ntid');
    tongmon = ma_nt.length;
    for(var i =0; i<ma_nt.length;i++){
      tongtien = tongtien + sotien[i];           
      if (nt_idTemp !='' && ma_nt[i] != nt_idTemp)
        check_nt = false;
      nt_idTemp = ma_nt[i];
    } 
    
    jQuery("#tong_so_mon").val(tongmon);
    if(check_nt == true){
       if(nt_idTemp != '177')
       vformat = 'USD';
       jQuery("#tong_so_tien").val(CurrencyFormatted(tongtien, vformat)); 
      document.getElementById('nt_id_tke_tong').value = nt_idTemp;
   }else{
      jQuery("#tong_so_tien").val(CurrencyFormatted('0.0', vformat)); 
      document.getElementById('nt_id_tke_tong').value = '';
  }
    
} 
function loadLoaiTien(ojbNT_ID){
    var vnt_id = ojbNT_ID.value;
    var tongmon = 0;    
    var tongtien = 0.0;
    var nt_idTemp = '';
    var vformat = 'VND';
    var sotien = document.getElementsByName('list_ltt_tongtien');
    var ma_nt = document.getElementsByName('list_ltt_ntid');
     
    if(vnt_id != ''){
    for(var i =0; i<ma_nt.length;i++){
      if( ma_nt[i].value == vnt_id){
        tongmon = tongmon+1;
        tongtien = tongtien + sotien[i].value;
      }
    } 
    jQuery("#tong_so_mon").val(tongmon);
     if(nt_idTemp != '177')
     vformat = 'USD';
     jQuery("#tong_so_tien").val(CurrencyFormatted(tongtien, vformat)); 
    document.getElementById('nt_id_tke_tong').value = vnt_id;
    }else{
     loadform();
    }
    
} 
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>