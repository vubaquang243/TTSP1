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
  String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
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
  String strNhan = "";
  String strCheckAllowEditMoTaiNHKB = "";
  String strSoTK_NguoiNhanStatus="";
  String strTenTK_NguoiNhanStatus="";
  String strNHKB_NguoiNhanStatus="";
  String strTTTK_NguoiNhanStatus="";
  String strActionParam = "";
  String strSpecialTKTN = "";
  String strDVQHNSAnNinh = "";
  if(request.getAttribute("StrDVQHNSAnNinh") != null)
    strDVQHNSAnNinh = request.getAttribute("StrDVQHNSAnNinh").toString();  
  if(request.getAttribute("StrSpecialTKTN") != null)
    strSpecialTKTN = request.getAttribute("StrSpecialTKTN").toString();
    
  if(request.getAttribute("so_tk_nhan_status") != null)
    strSoTK_NguoiNhanStatus = request.getAttribute("so_tk_nhan_status").toString();
  if(request.getAttribute("ten_tk_nhan_status") != null)
    strTenTK_NguoiNhanStatus = request.getAttribute("ten_tk_nhan_status").toString();
  if(request.getAttribute("nhkb_nhan_status") != null)
    strNHKB_NguoiNhanStatus = request.getAttribute("nhkb_nhan_status").toString();
  if(request.getAttribute("tttk_nhan_status") != null)
    strTTTK_NguoiNhanStatus = request.getAttribute("tttk_nhan_status").toString();
    
  if(request.getParameter("action") != null){
    strActionParam = request.getParameter("action");
  }  
  if(request.getAttribute("Nhan") != null)
    strNhan = request.getAttribute("Nhan").toString();
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
  String strNHKB_COA = "";
  if(session.getAttribute(AppConstants.APP_KB_CODE_SESSION) != null){
    strNHKB_COA = session.getAttribute(AppConstants.APP_KB_CODE_SESSION).toString();
  }
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
//  String strSoycttTK="";
//  String strNhkbchuyennhanTK="";
//  String strTrangthaiTK="";
//  String strSotienTK="";
//  String strttvNhanTK="";
//  if(request.getAttribute("soycttTK")!=null){
//    strSoycttTK = request.getAttribute("soycttTK").toString();
//  }
//  if(request.getAttribute("nhkbchuyennhanTK")!=null){
//    strNhkbchuyennhanTK = request.getAttribute("nhkbchuyennhanTK").toString();
//  }
//  if(request.getAttribute("trangthaiTK")!=null){
//    strTrangthaiTK = request.getAttribute("trangthaiTK").toString();
//  }
//  if(request.getAttribute("sotienTK")!=null){
//    strSotienTK = request.getAttribute("sotienTK").toString();
//  }
//  if(request.getAttribute("ttvnhanTK")!=null){
//    strttvNhanTK = request.getAttribute("sotienTK").toString();
//  }
%>
<input type="hidden" id="row_select" >
<div class="box_common_content">
<%if(!strUserType.equalsIgnoreCase(AppConstants.NSD_TTV)){%>
  <object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
<%}%>
   <html:form action="/lttAddExc.do" method="post">      
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
          <table  width="99%">
            <tbody>
            <tr>
              <td>
                <font color="red">
                  <html:errors/>
                </font> 
              </td>
            </tr>
            <tr>
              <td height="auto">
                  <input type="text" class="fieldTextTransErrorHide" readonly="readonly" id="error_desc"/>
              </td>
            </tr>
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
                            <fieldset class="fieldsetLTTCSS" style="height:370px">
                              <legend style="vertical-align:middle">
                                Số yêu cầu thanh toán
                              </legend>
                              <div class="sodientrasoattableLTT">
                                  <div>
                                    <table class="data-grid" cellSpacing="0" cellPadding="0" width="100%">
                                      <thead>
                                        <tr>
                                          <th class="ui-state-default ui-th-column"
                                               width="62%;">
                                           <fmt:message key="ltt_di.page.label.so_yeu_cau_tt" />
                                          </th>
                                          <th height="20" width="28%"
                                              class="ui-state-default ui-th-column">
                                           <fmt:message key="ltt_di.page.label.tinh_trang_master" />
                                          </th>
                                          <th height="20" width="10%;"
                                              class="ui-state-default ui-th-column">
                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          </th>
                                        </tr>
                                      </thead>
                                    </table>
                                  </div>
                                  <div style="height:250px;" class="ui-jqgrid-bdiv ui-widget-content">
                                  <div id="loading" style="display:none; padding-top:50px;">
                                    <p align="center"><img src="<%=request.getContextPath()%>/styles/images/ajax-loader.gif" /> <br/> Please Wait</p>
                                  </div>
                                  <div id="divScroll" style="position: relative;">
                                    <table  class="data-grid" id="data-grid"
                                              style="border:0px;width:100%"
                                             cellspacing="0" cellpadding="0"                                  
                                             width="100%">
                                      <tbody>                                      
                                        <logic:present name="<%=AppKeys.LTT_LIST_REQUEST_KEY%>">
                                          <logic:iterate id="list_ltt" name="<%=AppKeys.LTT_LIST_REQUEST_KEY%>" indexId="index" type="com.seatech.ttsp.ltt.LTTVO">
                                          <tr class="ui-widget-content jqgrow ui-row-ltr" id="row_ltt_<bean:write name="index"/>" 
                                            ondblclick="rowSelectedSetFocus('row_ltt_<bean:write name="index"/>');"
                                            onclick="loadDetailLTTJson('loadLTTDiJsonAction.do', '<bean:write name="list_ltt" property="id"/>','row_ltt_<bean:write name="index"/>', '<%=strUserType%>');"
                                            onmouseover="lttMouseOver('row_ltt_<bean:write name="index"/>');" 
                                            onmouseout="lttMouseOut('row_ltt_<bean:write name="index"/>');"
                                             >
                                            <td width="44%;" align="left">
                                              <input name="row_item" id="<bean:write name="index"/>" type="text" 
                                                value="<bean:write name="list_ltt" property="so_yctt"/>" 
                                                onkeydown="arrowUpDownLTT(event);" readonly="true" 
                                                style="border:0px;font-size:10px;float:left;" />
                                              <input  id="rowSelected" type="hidden" 
                                                value="<bean:write name="list_ltt" property="id"/>" />
                                                <input  name="tongsotien" type="hidden" 
                                                value="<bean:write name="list_ltt" property="tong_sotien"/>" /> 
                                                <input  name="ntid" type="hidden" 
                                                value="<bean:write name="list_ltt" property="nt_id"/>" />
                                            </td> 
                                            <td width="30%;" align="center">
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
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/gd_return.jpeg" border="0" title="GĐ đẩy lại"/>
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
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/send-success.jpg" border="0" title="Gửi ngân hàng thành công"/>
                                              </logic:equal>
                                              <logic:equal value="15" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg" border="0" title="Gửi ngân hàng không thành công"/>
                                              </logic:equal>
                                              <logic:equal value="16" name="list_ltt" property="trang_thai">
                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg" border="0" title="Lỗi truyền tin"/>
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
                                <div style="padding-top:5px;float: left;width:160px;">                                      
                                  <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                      <tr>
                                        <td width="80%" align="left">
                                          <fmt:message key="ltt_di.page.label.tinh_trang_master"/>: <span id="mo_ta_trang_thai" style="font-weight:bold;"></span>
                                        </td>
                                        <td width="20%" align="right">
                                          <span id="refresh" class="ui-icon ui-icon-refresh" title="Làm mới" onclick="refreshGridLTT('<%=strUserType%>', 'listLttAdd.do', 'loadLTTDiJsonAction.do');" style="cursor:pointer;"></span>
                                        </td>
                                        <td>
                                          <span id="search" class="ui-icon ui-icon-search" title="Tìm kiếm" style="cursor:pointer;"></span>
                                        </td>
                                      </tr>                                      
                                      <%
                                      String strLoaiHanhDong = request.getAttribute("loai_hanh_dong")==null?"":request.getAttribute("loai_hanh_dong").toString();
                                      if (!AppConstants.LTT_DI_CHUC_NANG_CHI_TIET.equalsIgnoreCase(strLoaiHanhDong)) {
                                      %>
                                      <tr>
                                        <td align="left" colspan="3">                                        
                                        Loại tiền: <html:select property="nt_id_tke_tong" styleId="nt_id_tke_tong" onchange="changeMaNTThongKeTongJS(this);" style="width:95px;height:20px;vlaign:middle" styleClass="fieldTextCode" >
                                          <%--<html:optionsCollection name="listDMTienTe" value="id" label="ma" />
                                          onfocus="this.defaultIndex=this.selectedIndex;" onchange="this.selectedIndex=this.defaultIndex;"123123
                                          --%>
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
                                      
                                       <!--thuongdt-20171031 phuc vu tra cuu nhanh begin-->
                                     <logic:notEmpty name="listDMTienTe">
                                          <logic:present name="listDMTienTe">
                                            <logic:iterate id="objNT" name="listDMTienTe" type="com.seatech.ttsp.dmtiente.DMTienTeVO" indexId="index">                                              
                                              <logic:notEqual value="2" name="objNT" property="id" >                                               
                                                <input  name="tempmant" type="hidden"  value="<bean:write name="objNT" property="ma"/>" />
                                              </logic:notEqual>
                                            </logic:iterate>  
                                          </logic:present>
                                        </logic:notEmpty>
                                         <!--thuongdt-20171031 phuc vu tra cuu nhanh end-->
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
                                      <%}%>
                                    </table>                                                                     
                                  </div>
                                 
                                </div>
                              </div>
                             </fieldset> 
                            </div>
                        </td>
                        <td width="85%" style="padding-bottom:2px;" height="200px;" valign="top">
                          <fieldset style="height:auto;">
                              <legend style="vertical-align:middle">Thông tin chung</legend>
                            <div style="width:auto; height: auto;">
                              <table  width="100%" cellspacing="0" cellpadding="1" style="width:100%; border: 0px solid #8eb9e5;" align="center" >
                                <tbody>
                                  <tr>
                                    <td width="19%" align="right" class="textNormal">NH/KB chuyển</td>
                                    <td width="36%" align="left">
                                      <html:text property="ma_nhkb_chuyen_cm" styleId="ma_nhkb_chuyen_cm" maxlength="8" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                                        onblur="getTenNganHang('ma_nhkb_chuyen_cm', 'ten_nhkb_chuyen_cm', 'nhkb_chuyen')" style="WIDTH: 55px;"/>&nbsp;
                                      <html:text property="ten_nhkb_chuyen_cm"  styleId="ten_nhkb_chuyen_cm" 
                                      styleClass="fieldTextTrans" readonly="true" onmouseout="UnTip()" 
                                      onmouseover="Tip(this.value)" style="WIDTH: 65%;"/>                          
                                      <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen" />
                                    </td>
                                    <td width="14%" align="right">NH/KB nhận</td>
                                    <td colspan="3" align="left">                          
                                      <html:select property="ma_nhkb_nhan_cm" styleId="ma_nhkb_nhan_cm" onblur="if(this.value!=null)getTenNganHang('ma_nhkb_nhan_cm', 'ten_nhkb_nhan_cm', 'nhkb_nhan')" 
                                        styleClass="fieldTextCode"  style="width:80px;height:20px;" 
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
                                      <html:text property="ten_nhkb_nhan_cm" styleId="ten_nhkb_nhan_cm" styleClass="fieldTextTrans" readonly="true" onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 65%;"/>
                                      <html:hidden property="nhkb_nhan" styleId="nhkb_nhan" />
                                    </td>
                                  </tr>
                                  <tr>
                                    <td align="right" >Số chứng từ gốc</td>
                                    <td width="38%" align="left">
                                      <html:text property="so_ct_goc" styleId="so_ct_goc" styleClass="fieldTextCode" maxlength="15" onkeypress="return numberBlockKey1();" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>                              
                                    </td>
                                    <td  align="right">Ngày chứng từ</td>
                                    <td width="18%" align="left" valign="middle">
                                      <html:text property="ngay_ct" styleId="ngay_ct" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                                      onblur="checkDateTTSP(this); javascript:return mask(this.value,this,'2,5','/'); CheckDateOnClient('ngay_ct');"  style="WIDTH: 70%;"/>
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
                                    <td align="right" style="display:none" id="td_loai_lable_loai_phi">
                                    Loại phí&nbsp; 
                                    </td>
                                    <td  style="display:none" id="td_loai_field_loai_phi">
                                      <html:select property="phi" styleId="phi" style="width:95px;height:20px;vlaign:middle" styleClass="fieldTextCode" >
                                          <html:option value="OUR">Phí ngoài</html:option>
                                          <html:option value="BEN">Phí trong</html:option>  
                                          <html:option value="SHA">Phí chia sẻ</html:option>  
                                      </html:select>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td align="right" >Số YCTT</td>
                                    <td width="20%" align="left">
                                      <html:text property="so_yctt" styleId="so_yctt" maxlength="20" styleClass="fieldTextCode" onmouseout="UnTip()" 
                                      onmouseover="Tip(this.value)"  
                                      onfocus="autoSelect(this);"
                                      style="WIDTH: 120px;"/>
                                    </td>
                                    <td align="right">Số LTT</td>
                                    <td width="18%" align="left">
                                      <html:text property="id" styleId="id" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                                      style="WIDTH: 110px;"/>
                                    </td>
                                    <td width="15%" align="right">Ngày TT</td>
                                    <td width="17%" align="left">
                                      <html:text property="ngay_tt" styleId="ngay_tt" styleClass="fieldTextCode" onmouseout="UnTip()" 
                                      onmouseover="Tip(this.value)" 
                                      style="WIDTH: 97px"
                                      onblur="checkDateTTSP(this); javascript:return mask(this.value,this,'2,5','/'); CheckDateOnClient('ngay_ct');"></html:text>
                                      <input type="hidden" id="ngay_tt_temp"/>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td align="right" >Người lập</td>
                                    <td width="30%" align="left">
                                      <html:text property="ma_ttv_nhan" styleId="ma_ttv_nhan" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>
                                      <html:text property="ten_ttv_nhan" styleId="ten_ttv_nhan" styleClass="fieldTextTrans" readonly="true" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>
                                      <html:hidden property="ttv_nhan" styleId="ttv_nhan" />
                                    </td>
                                    <td align="right">Ngày lập</td>
                                    <td align="left">
                                      <html:text property="ngay_hoan_thien" styleId="ngay_hoan_thien" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 110px;"/>
                                    </td>
                                    
                                    <td align="right">Nguồn</td>
                                    <td align="left">                          
                                      <span id="lenh_thucong_tabmis" ></span>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td align="right" >Người kiểm soát</td>
                                    <td width="30%" align="left">
                                      <html:text property="ma_ktt_duyet" styleId="ma_ktt_duyet" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>
                                      <html:text property="ten_ktt_duyet" styleId="ten_ktt_duyet" styleClass="fieldTextTrans" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>
                                      <html:hidden property="ktt_duyet" styleId="ktt_duyet" />
                                    </td>
                                    <td align="right">Ngày kiểm soát</td>
                                    <td align="left">
                                      <html:text property="ngay_ktt_duyet" styleId="ngay_ktt_duyet" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 110px;"/>
                                    </td>
                                    <td align="right">Loại lệnh</td>
                                    <td align="left">
                                      <select name="ttloai_lenh" id="ttloai_lenh"  class="fieldText" style="width:120px;height:20px;">
                                        <option value="01" selected="true">Lệnh chuyển có</option>
                                        <option value="02">Lệnh rút tiền</option>                                        
                                        <option value="03">TT bằng ngoại tệ khác</option>
                                      </select>
                                    </td>
                                  </tr>
                                  <tr id="trGDDuyet" style="display: none;">
                                    <td align="right" >GĐ duyệt</td>
                                    <td width="30%" align="left">
                                      <html:text property="ma_gd_duyet" styleId="ma_gd_duyet" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>                              
                                      <html:text property="ten_gd_duyet" styleId="ten_gd_duyet" styleClass="fieldTextTrans" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 90px;"/>                              
                                      <html:hidden property="gd_duyet" styleId="gd_duyet"/>
                                    </td>
                                    <td align="right">Ngày GĐ duyệt</td>
                                    <td colspan="3" align="left">
                                      <html:text property="ngay_gd_duyet" styleId="ngay_gd_duyet" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)"  style="WIDTH: 110px;"/>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td  align="right" >Tổng số tiền</td>
                                    <td width="30%" align="left">
                                      <html:text property="tong_sotien" styleId="tong_sotien" styleClass="fieldTextRightCode" onmouseout="UnTip()" onmouseover="Tip(this.value);"  style="WIDTH: 180px;"/>                              
                                      <input type="hidden" id="tip_tong_tien_mua_ban">
                                    </td>
                                    <td align="right" valign="middle" rowspan="2">Nội dung TT</td>
                                    <td align="left" valign="top" rowspan="2" colspan="3">                                      
                                      <html:textarea property="ndung_tt" styleId="ndung_tt" styleClass="fieldTextAreaRO" rows="3"  style="WIDTH: 99%;height:40px;"></html:textarea>
                                    </td>
                                  </tr>   
                                  <tr>
                                    <td id="td_nt_id" align="right" >Mã NT</td>
                                    <td width="30%" colspan="5" align="left">
                                      <html:select property="nt_id" styleId="nt_id" onchange="changeMaNT();" style="width:95px;height:20px;vlaign:middle" styleClass="fieldTextCode" >
                                          <%--<html:optionsCollection name="listDMTienTe" value="id" label="ma" />
                                          onfocus="this.defaultIndex=this.selectedIndex;" onchange="this.selectedIndex=this.defaultIndex;"
                                          --%>
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
                                  <tr style="display:none" id ="tr_mua_ban">
                                    <td align="right">Mã ngoại tệ t.toán</td>
                                    <td><html:select property="ma_nt_mua_ban" styleId="ma_nt_mua_ban" style="width:95px;height:20px;vlaign:middle" styleClass="fieldText" onchange="checkMuaVND();">
                                        <option value=""></option>
                                        <logic:notEmpty name="listDMTienTeAll">
                                          <logic:present name="listDMTienTeAll">
                                            <logic:iterate id="objNT" name="listDMTienTeAll" type="com.seatech.ttsp.dmtiente.DMTienTeVO" indexId="index">
                                                <option value="<%=objNT.getMa()%>"><%=objNT.getMa()%></option>
                                            </logic:iterate>
                                          </logic:present>
                                        </logic:notEmpty>
                                      </html:select></td>
                                      <td align="right">Số tiền ngoại tệ t.toán</td>
                                      <td><html:text property="so_tien_mua_ban" style="width:96%;" styleId="so_tien_mua_ban" onkeypress="return numberBlockKeySoTien(this, document.forms[0].nt_id.options[document.forms[0].nt_id.options.selectedIndex].innerText);"                                                        
                                                     onblur="formatNumberJQuery('so_tien_mua_ban','ma_nt_mua_ban','ma_nt_mua_ban_old');validSoTien(this);" 
                                                     onfocus="checkMaNTMuaBan();"
                                                     styleClass="fieldTextRight"/>
                                          <input type="hidden" id="ma_nt_mua_ban_old" value="" >
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>                   
                            </div>
                          </fieldset>
                          <fieldset style="height:auto;">
                            <legend style="vertical-align:middle">Thông tin người chuyển</legend>
                              <div style="width:auto; ">
                                <table align="center" style="width:100%; border: 0px solid #8eb9e5;" cellSpacing="0" cellPadding="1" >
                                    <tr>
                                      <td width="11%" align="right">
                                        Đơn vị chuyển
                                      </td>
                                      <td width="45%" align="left">
                                        <html:text property="ten_tk_chuyen" onmouseout="UnTip()" 
                                          onmouseover="Tip(this.value)"  styleId="ten_tk_chuyen" styleClass="fieldTextCode" style="width:98%;"/>
                                          <input type="hidden" id="ten_tk_chuyen_tmp"></input>
                                      </td>
                                      <td width="8%" rowspan="2" align="right" valign="top">
                                         Thông tin
                                      </td>
                                      <td width="30%" rowspan="2" align="left" valign="top">
                                        <html:textarea style="width:98%;height:18px;" cols="50" onmouseout="UnTip()" 
                                        onmouseover="Tip(this.value)" rows="2" 
                                        property="ttin_kh_chuyen" styleId="ttin_kh_chuyen" styleClass="fieldTextAreaRO"></html:textarea>
                                      </td> 
                                    </tr>
                                    <tr>
                                      <td width="10%" align="right">
                                        Tài khoản
                                      </td>
                                      <td width="45%" align="left" colspan="3">
                                        <html:text property="so_tk_chuyen" onmouseout="UnTip()" onmouseover="Tip(this.value)" styleId="so_tk_chuyen" styleClass="fieldTextCode" style="width:90px;" maxlength="12"/>
                                        Mở tại NH/KB
                                        <html:text property="ma_nhkb_chuyen" styleId="ma_nhkb_chuyen" maxlength="8" 
                                          styleClass="fieldTextCode" 
                                          onmouseout="UnTip()" 
                                          onmouseover="Tip(this.value)"
                                          onblur="getTenNganHang('ma_nhkb_chuyen', 'ten_nhkb_chuyen', 'id_nhkb_chuyen');" 
                                           style="WIDTH: 50px;"/>
                                        <html:text onkeydown="enterTab2SoTKorCOA(event);" property="ten_nhkb_chuyen" 
                                        styleId="ten_nhkb_chuyen" styleClass="fieldTextTrans" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                                        style="WIDTH: 60%;"/>
                                        <html:hidden property="id_nhkb_chuyen" styleId="id_nhkb_chuyen" />
                                      </td>
                                        
                                    </tr>
                                </table>
                              </div>
                          </fieldset>
                          <html:hidden property="sua_ten_tk_flag" styleId="sua_ten_tk_flag" />
                          <fieldset style="width:auto;">
                            <legend style="vertical-align:middle">Thông tin người nhận</legend>
                              <div style="width:auto;">
                                <table style="width:100%; border: 0px solid #8eb9e5; BORDER-COLLAPSE: collapse;" cellpadding="1" cellspacing="0">
                                  <tr>
                                    <td width="11%" align="right">
                                      Đơn vị nhận
                                    </td>
                                    <td width="45%" align="left">
                                      <html:text property="ten_tk_nhan" onmouseout="UnTip()" onmouseover="Tip(this.value)" styleId="ten_tk_nhan" styleClass="fieldTextCode" style="width:98%;"/>
                                    </td>
                                    <td width="8%" rowspan="2" align="right" valign="top">
                                       Thông tin
                                    </td>
                                    <td width="30%" rowspan="2" align="left" valign="top">
                                      <html:textarea style="width:98%;height:18px;" cols="50" onmouseout="UnTip()" onmouseover="Tip(this.value)"
                                      rows="3" property="ttin_kh_nhan" styleId="ttin_kh_nhan" styleClass="fieldTextAreaRO">
                                      </html:textarea>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td width="10%" align="right">
                                       Tài khoản
                                    </td>
                                    <td width="60%" colspan="3" align="left">
                                      <html:text property="so_tk_nhan" styleId="so_tk_nhan" onmouseout="UnTip()" 
                                      onmouseover="Tip(jQuery('#so_tk_nhan_tooltip').val())" 
                                      maxlength="35" styleClass="fieldTextCode" style="width:90px;"/>
                                      <input type="hidden" id="so_tk_nhan_tooltip"/>
                                      Mở tại NH/KB
                                      <html:text property="ma_nhkb_nhan" styleId="ma_nhkb_nhan" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                                        onblur="if(this.value != null && this.value != ''){getTenNganHang('ma_nhkb_nhan', 'ten_nhkb_nhan', 'id_nhkb_nhan')} else{id_nhkb_nhan.value=''} "  onkeydown="if(event.keyCode==13){ event.keyCode=9; }else if(event.keyCode==121){ callLov();}"
                                        style="WIDTH: 65px;" />
                                      <html:text property="ten_nhkb_nhan" styleId="ten_nhkb_nhan" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 50%;"/>
                                      <html:hidden property="id_nhkb_nhan" styleId="id_nhkb_nhan"/>
                                      <button id="btnLov" name="btnLov" type="button" onclick="callLov()">Tìm</button> 
                                      <input type="hidden" id="ma_nhkb_nhan_cu_hiden"/>
                                    </td> 
                                  </tr>
                                  <tr style="display:none" id="tr_nh_trung_gian">
                                    <td width="10%" align="right">
                                       NH trung gian
                                    </td>
                                    <td width="60%" colspan="2" align="left">                                      
                                      <html:text property="nh_tgian" styleId="nh_tgian" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" 
                                        onblur="if(this.value != null && this.value != ''){getTenNganHang('nh_tgian', 'ten_nh_tgian', 'id_nh_trung_gian')} else{id_nh_trung_gian.value=''} "   onkeydown="if(event.keyCode==13){ event.keyCode=9; }else if(event.keyCode==121){ callLovNHTgian();}"
                                        style="WIDTH: 65px;"/>
                                      <html:text property="ten_nh_tgian" styleId="ten_nh_tgian" styleClass="fieldTextCode" onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 60%;"/>
                                      <html:hidden property="id_nh_trung_gian" styleId="id_nh_trung_gian"/>
                                      <button id="btnLovNHTgian" name="btnLovNHTgian" type="button" onclick="callLovNHTgian()">Tìm</button> 
                                    </td>
                                    <td><html:checkbox property="vay_tra_no_nn" styleId="vay_tra_no_nn" value ="Y" /><font color="Blue" size="3px">Lệnh chi trả nợ nước ngoài</font></td>
                                  </tr>
                                </table>                    
                              </div>
                          </fieldset>
                          <table id="fsLyDoDayLai" style="width:99.5%; border: 1px solid rgb(197,196,202); BORDER-COLLAPSE: collapse;" cellpadding="1" align="center" cellspacing="0">
                            <tr>
                              <td width="15%" align="right" valign="middle">
                                Lý do KTT đẩy lại
                              </td>
                              <td width="30%" align="left">
                                <html:textarea onkeyup="changeBtnDuyetState();" style="width:98%;height:20px;" cols="40" 
                                rows="3" property="lydo_ktt_day_lai" styleId="lydo_ktt_day_lai" styleClass="fieldTextAreaRO" 
                                onkeydown="KTTJump2ApprovedOrReject(event, this); arrowUpDownLTT(event);"></html:textarea>
                              </td>
                              <td width="15%" align="right" valign="middle">
                                 Lý do GĐ đẩy lại
                              </td>
                              <td width="30%" align="left">
                                <html:textarea cols="40" rows="3"  style="width:98%;height:20px;" property="lydo_gd_day_lai" styleId="lydo_gd_day_lai" styleClass="fieldTextAreaRO" onkeydown="GDJump2ApprovedOrReject(event, this); arrowUpDownLTT(event);"></html:textarea>
                              </td> 
                            </tr>                           
                          </table>                    
                          <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%">
                              <tr align="right">
                                <td>
                                  <!--<span>
                                    <button id="add" class="ButtonCommon" accesskey="M" type="button">Lập <span class="sortKey">m</span>ới</button>
                                  </span>
				   <span>
                                  <button id="resend" class="ButtonCommon" type="button" accesskey="T"><span class="sortKey">T</span>ruyền lại</button>
                                  </span>
				  -->
                                  <!-- HungBM 20170414- Them button cho in giay gioi thieu - hien thi khi thoa man dien kien checkInGioiThieu() -BEGIN -->
                                  <span>
                                  <button id="print" onclick="inGioiThieu();" class="ButtonCommon" type="button" accesskey="I"><span class="sortKey">I</span>n giới thiệu</button>
                                  </span>
                                  <input type="hidden" name="in_gioi_thieu"  styleID="in_gioi_thieu" />
                                  <!-- HungBM 20170414- Them button cho in giay gioi thieu - hien thi khi thoa man dien kien checkInGioiThieu() -END -->                        
                                  <span>
                                  <button id="edit" class="ButtonCommon" type="button" accesskey="S"><span class="sortKey">S</span>ửa</button>
                                  </span>
                                  <span>
                                  <button id="save" onclick="checkSuccess('lttDiAdd');" class="ButtonCommon" type="button" accesskey="G"><span class="sortKey">G</span>hi</button>
                                  </span>
                                  <input type="hidden" id="actionForSave" />
                                  <span>
                                  <button id="completeLtt" class="ButtonCommon" type="button"  accesskey="H"><span class="sortKey">H</span>oàn thiện</button>
                                  </span>
                                  <span>
                                  <button id="rejectLtt" class="ButtonCommon" type="button"  accesskey="L">Đẩy <span class="sortKey">l</span>ại</button>
                                  </span>
                                  <span>
                                  <button id="approved" class="ButtonCommon" type="button"  accesskey="D"><span class="sortKey">D</span>uyệt</button>
                                  </span>
                                  <span>
                                  <button id="cancel" class="ButtonCommon" type="button" accesskey="U">H<span class="sortKey">ủ</span>y</button>
                                  </span>
                                  <span id="in"> 
                                    <button onclick="formAction()"
                                            id="btnIn" class="ButtonCommon"
                                            type="button" accesskey="O">
                                      <fmt:message key="ltt_di.page.button.in"/>
                                    </button>
                                  </span>
                                  <!--<span>
                                  <button id="search" class="ButtonCommon" type="button" accesskey="T"><span class="sortKey">T</span>ìm kiếm</button>
                                  </span>-->
                                  <span>
                                  <button id="exit" class="ButtonCommon" type="button" accesskey="o">Th<span class="sortKey">o</span>át</button>                     
                                  </span>
                                  <span id="span" style="display:none;"></span>
                                </td> 
                              </tr>
                            </table>
                        </td>
                      </tr>
                      <tr></tr>
                      <!--<tr>
                        <td valign="top">
                          <fieldset style="width:auto;">
                            <legend style="vertical-align:middle"><b>Thông tin người chuyển</b></legend>
                              <div style="width:auto; ">
                                <table align="center" style="width:100%; border: 1px solid #8eb9e5;" cellSpacing="0" cellPadding="0" >
                                    <tr>
                                      <td width="10%" align="right">
                                        Tên tài khoản
                                      </td>
                                      <td width="25%" align="left">
                                        &nbsp;<html:text property="ten_tk_chuyen" styleId="ten_tk_chuyen" styleClass="fieldTextCode" style="width:180px;"/>
                                      </td>
                                      <td width="20%" rowspan="3" align="right" valign="top">
                                         Thông tin khách hàng chuyển&nbsp;
                                      </td>
                                      <td width="40%" rowspan="3" align="left">
                                        <html:textarea cols="50" rows="3" property="ttin_kh_chuyen" styleId="ttin_kh_chuyen" styleClass="fieldTextAreaRO" onkeydown="skipTabReadonlyField1(event, 'so_tk_chuyen');"></html:textarea>
                                      </td> 
                                    </tr>
                                    <tr>
                                      <td width="10%" align="right">
                                         Tài khoản
                                      </td>
                                      <td width="60%" colspan="2" align="left">
                                        &nbsp;<html:text property="so_tk_chuyen" styleId="so_tk_chuyen" styleClass="fieldTextCode" style="width:90px;" maxlength="12"/>
                                      </td>                  
                                    </tr>
                                    <tr>
                                      <td width="10%" align="right">
                                        Mở tại NH/KB
                                      </td>
                                      <td width="60%" colspan="3" align="left">
                                         &nbsp;<html:text property="ma_nhkb_chuyen" styleId="ma_nhkb_chuyen" maxlength="8" 
                                          styleClass="fieldTextCode" 
                                          onmouseout="UnTip()" 
                                          onmouseover="Tip(this.value)"
                                          onblur="getTenNganHang('ma_nhkb_chuyen', 'ten_nhkb_chuyen', 'id_nhkb_chuyen');" 
                                          onkeydown="enterTab2SoTKorCOA(event);"
                                           style="WIDTH: 90px;"/>&nbsp;
                                        &nbsp;<html:text onkeydown="enterTab2SoTKorCOA(event);" property="ten_nhkb_chuyen" styleId="ten_nhkb_chuyen" styleClass="fieldTextTrans" onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 200px;"/>
                                        <html:hidden property="id_nhkb_chuyen" styleId="id_nhkb_chuyen" />
                                      </td>
                                    </tr>
                                </table>
                              </div>
                          </fieldset>
                        </td>
                      </tr>-->
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
                                                <button type="button" name="cmdAddRow" id="cmdAddRow" onclick="addDetailCOARow();"  onmouseover="Tip('Thêm mới (Alt +)')" onmouseout="UnTip()" accesskey="=">+</button>
                                            </td>
                                          </tr>
                                        </table>
                                      </td>
                                  </tr>  
                                  <tr>
                                    <td width="100%" colspan="4">
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
                                                  <td align="center">                                      
                                                     <html:text property="tkkt_ma"  styleId="tkkt_ma" onkeypress="return numberBlockKey1();" onkeyup="autoFocusNextItem(this.value,'dvsdns_ma');"
                                                       onblur="checkDMTKCheo(this, this.parentNode.parentNode);taoTaiKhoanCOA();"  styleClass="fieldTextCenterCode" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="dvsdns_ma" styleId="dvsdns_ma" onkeypress="return numberBlockKey1();" 
                                                     onblur="taoTaiKhoanCOA();taoTenDVNhan(this.value);autoFillDefaultValue('dvsdns_ma');"  styleClass="fieldTextCenterCode" maxlength="7"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="capns_ma" styleId="capns_ma" onkeypress="return numberBlockKey1();" 
                                                     onblur="taoTaiKhoanCOA();autoFillDefaultValue('capns_ma');"  styleClass="fieldTextCenterCode" maxlength="1"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="chuongns_ma"  
                                                     onblur="autoFillDefaultValue('chuongns_ma');"
                                                     styleId="chuongns_ma" onkeypress="return numberBlockKey1();" 
                                                     styleClass="fieldTextCenterCode" maxlength="3"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="nganhkt_ma"  styleId="nganhkt_ma"  onblur="autoFillDefaultValue('nganhkt_ma');"
                                                     onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="3"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="ndkt_ma"  styleId="ndkt_ma" onblur="autoFillDefaultValue('ndkt_ma');"
                                                     onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="dbhc_ma"  styleId="dbhc_ma" onblur="autoFillDefaultValue('dbhc_ma');"
                                                     onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="5"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="ctmt_ma"  styleId="ctmt_ma" onblur="autoFillDefaultValue('ctmt_ma');"
                                                     onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="5"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="ma_nguon"  styleId="ma_nguon" onblur="autoFillDefaultValue('ma_nguon');"
                                                     onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="2"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="kb_ma"  styleId="kb_ma" 
                                                     onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text property="du_phong_ma"  styleId="du_phong_ma" onblur="autoFillDefaultValue('du_phong_ma');"
                                                     onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="3"/>
                                                  </td>
                                                  <td align="left">
                                                     <html:text property="dien_giai"    styleId="dien_giai" 
                                                     styleClass="fieldTextCodeHeight" />
                                                  </td>
                                                  <td align="right">
                                                     <html:text property="so_tien"  styleId="so_tien" onkeypress="return numberBlockKey1();"  
                                                     onkeydown="skipTabReadonlyField1(event, 'id_delete_row');" 
                                                     onblur="formatNumberCOAJQuery(this);validSoTien(this);cal_tongtienCOA();" 
                                                     styleClass="fieldTextRightCode"/>
                                                  </td>
                                                  <td align="center">
                                                      <img border="0" onkeydown="enterTabFocus2Enable(event, this);" src="<%=request.getContextPath()%>/styles/images/delete1.gif" onclick="removeDetailCOARow(this);" onmouseout="UnTip()" onmouseover="Tip('Xóa')" id="id_delete_row" alt="">
                                                      <html:hidden property="sott_dong" styleId="sott_dong" />
                                                  </td>
                                                </tr>
                                                </logic:empty>
                                                <logic:notEmpty name="colLTTCTiet">
                                                <logic:iterate id="lttCTiet" name="colLTTCTiet" indexId="index">
                                                <tr id="coa_<bean:write name="index"/>">
                                                  <td align="center">
                                                      <html:text name="lttCTiet" property="ma_quy" styleId="ma_quy" onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="2"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="tkkt_ma" styleId="tkkt_ma" onkeypress="return numberBlockKey1();" 
                                                       onblur="checkDMTKCheo(this, this.parentNode.parentNode);taoTaiKhoanCOA();" styleClass="fieldTextCenterCode" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="dvsdns_ma" styleId="dvsdns_ma" onkeypress="return numberBlockKey1();" 
                                                       onblur="taoTaiKhoanCOA();autoFillDefaultValue('dvsdns_ma');"
                                                            styleClass="fieldTextCenterCode" maxlength="7"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="capns_ma" styleId="capns_ma" onkeypress="return numberBlockKey1();"  
                                                     onblur="taoTaiKhoanCOA();autoFillDefaultValue('capns_ma');" styleClass="fieldTextCenterCode" maxlength="1"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="chuongns_ma" onblur="autoFillDefaultValue('chuongns_ma');" styleId="chuongns_ma" onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="3"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="nganhkt_ma" onblur="autoFillDefaultValue('nganhkt_ma');" styleId="nganhkt_ma" onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="3"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="ndkt_ma" styleId="ndkt_ma" onblur="autoFillDefaultValue('ndkt_ma');" onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="dbhc_ma" styleId="dbhc_ma" onblur="autoFillDefaultValue('dbhc_ma');" onkeypress="return numberBlockKey1();" styleClass="fieldTextCenterCode" maxlength="5"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="ctmt_ma" styleId="ctmt_ma" onkeypress="return numberBlockKey1();" onblur="autoFillDefaultValue('ctmt_ma');"  styleClass="fieldTextCenterCode" maxlength="5"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="ma_nguon" styleId="ma_nguon" onkeypress="return numberBlockKey1();" onblur="autoFillDefaultValue('ma_nguon');"  styleClass="fieldTextCenterCode" maxlength="2"/>
                                                  </td>
                                                  <td align="center">
                                                     <html:text name="lttCTiet" property="kb_ma" styleId="kb_ma" onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="4"/>
                                                  </td>
                                                  <td align="center">
                                                    <html:text name="lttCTiet" property="du_phong_ma" onblur="autoFillDefaultValue('du_phong_ma');" styleId="du_phong_ma" onkeypress="return numberBlockKey1();"  styleClass="fieldTextCenterCode" maxlength="3"/>
                                                  </td>
                                                  <td align="left">
                                                    <html:text name="lttCTiet" property="dien_giai"  styleId="dien_giai" styleClass="fieldTextCodeHeight" />
                                                  </td>
                                                  <td align="right">
                                                     <html:text name="lttCTiet" property="so_tien"
                                                     onkeypress="return numberBlockKey1();" styleId="so_tien" 
                                                     style="width:100%"
                                                     onblur="formatNumberCOAJQuery(this);validSoTien(this);cal_tongtienCOA();" 
                                                     styleClass="fieldTextRightCode"/>
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
                      
                      
                    </tbody>
                  </table>
            </td>
          </tr>
        </tbody>
      </table>
      
      
      </td></tr></table> 
      <input type="hidden" value="hoan_thien_ltt_di" id="form_type" />
      <input type="hidden" id="nt_id_old"/>
     
      <html:hidden property="nhan" styleId="nhan" />
      <html:hidden property="gd_duyet" styleId="gd_duyet" />
      <html:hidden property="trang_thai" styleId="trang_thai" />
      <html:hidden property="chuky_ktt" styleId="chuky_ktt" />
      <html:hidden property="chuky_gd" styleId="chuky_gd" />
      <input id="id_selected" type="hidden"/>
      <input type="hidden" name="certserial" id="certserial" />
      <input type="hidden" name="signature" id="signature" />
      <input type="hidden" name="noi_dung_ky" id="noi_dung_ky" />
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
                        <!--thuongdt-20170915 sua cho phep chon tat ca-->
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
            <!--<table>
              <tr>
                <td width="100%">
                  <div id="resultSearch" class="title2" style="padding-left:55px;padding-top:20px;"></div>
                </td>
              </tr>
            </table>-->
      </div>
  </html:form>
</div>
<div id="dialog-message" title="<fmt:message key="ltt_di.page.title.dialog_message"/>">
    <p style="vertical-align:middle;">
      <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="message"></span>
    </p>
</div>
<div id="dialog" title="<fmt:message key="ltt_di.page.title.dialog_message"/>">
    <p style="vertical-align:middle;">
      <span class="ui-icon ui-icon-circle-check" style="float:left; margin:0 7px 50px 0;"></span>
      <span id="dlgMessage"></span>
    </p>
</div>
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
    //Global variable TKTN dac biet, an ninh
    arrSpecialTKTN = new Array(<%=strSpecialTKTN%>);
    arrDVQHNSAnNinh = new Array(<%=strDVQHNSAnNinh%>);  
    // set limit char for 2 field 
    jQuery("#ttin_kh_nhan").keyup(function () {
        limitChars(jQuery("#ttin_kh_nhan").attr('id'), 146);
    });
    jQuery("#ttin_kh_chuyen").keyup(function () {
        limitChars(jQuery("#ttin_kh_chuyen").attr('id'), 146);
    }); 
    // end 
    
      document.getElementById('row_select').value="row_ltt_0";       
      if(document.getElementById('row_select')!=null || document.getElementById('row_select')!='undefined')
        rowSelectedSetFocus(document.getElementById('row_select').value);
    //HUNGBM - 20170417 - BEGIN
    //An button In gioi thieu luc load trang
    //jQuery("#print").hide();
    //HUNGBM - 20170417 - END
    // arrFieldId is declare in LenhThanhToan
    jQuery("#resend").hide();
    jQuery("#edit").hide();
    jQuery("#save").hide();
    jQuery("#completeLtt").hide();
    jQuery("#rejectLtt").hide();
    jQuery("#approved").hide();
    jQuery("#cancel").hide();
    jQuery("#cmdAddRow").attr({"disabled":true});
    
    jQuery("#ma_nhkb_nhan_cm").attr({"disabled":true});
    jQuery("#ttloai_lenh").attr({"disabled":true});
    jQuery("#ngay_ct_btn").hide();
    jQuery("#nt_id").attr({"disabled":true});
    
//    jQuery("#phi").attr({"disabled":true});
    
    //jQuery("#tblThongTinChiTietCOA tr input").attr("disabled", true);
    jQuery("#ngay_tt_temp").val('<%=StringUtil.DateToString(new Date(),"dd/MM/yyyy")%>');
    soLTTDi = '<%=strSoLTTDi%>';
    strLoaiUser = "<%=strUserType%>";    //alert(strLoaiUser);
    // chuc them 
    row_default="row_ltt_0"; 
    input_default = jQuery('#rowSelected');   
    // Dieu khien an hien cac nut ban dau vao
    var quyenLttDi = "<%=strQuyenLttDi%>"!=null?"<%=strQuyenLttDi%>":"";
    
    //end
    //An field ma_ttv cho viec tim kiem neu la ttv, hien thi lydo_day_lai  
    if(strLoaiUser != null){
      if(strLoaiUser.indexOf("<%=AppConstants.NSD_TTV%>") != -1){
        jQuery("#ma_ttv").hide();
//        loadDetailLTTJson('loadLTTDiJsonAction.do', input_default.val(),row_default, '<%=AppConstants.NSD_TTV%>',quyenLttDi);
      //20171118 thuongdt cho phep qthttw, can bo TTTT duoc xem thong tin giam doc duyet LTT      
      }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_GD%>") != -1 || strLoaiUser.indexOf("<%=AppConstants.NSD_QTHT_TW%>") != -1 || strLoaiUser.indexOf("<%=AppConstants.NSD_CB_TTTT%>") != -1){
        jQuery("#trGDDuyet").removeAttr("style");
//        loadDetailLTTJson('loadLTTDiJsonAction.do', input_default.val(),row_default, '<%=AppConstants.NSD_GD%>',quyenLttDi);
      }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_KTT%>") != -1){
        loadDetailLTTJson('loadLTTDiJsonAction.do', input_default.val(),row_default, '<%=AppConstants.NSD_KTT%>',quyenLttDi);
      }else{
//        loadDetailLTTJson('loadLTTDiJsonAction.do', input_default.val(),row_default, '<%=AppConstants.NSD_CB_TTTT%>',quyenLttDi);
      }
      loadDetailLTTJson('loadLTTDiJsonAction.do', input_default.val(),row_default, strLoaiUser,quyenLttDi);
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
    initDialogTimKiem(); 
    initDialogConfrm();
    dialogMsg("");
    initDialogKHC();
    initDialogLov();
    
    
    //Quyen Hoan thien(=Sua), quyen huy thi fai click chon row ltt moi hien len
    if(quyenLttDi.indexOf("THOAT") != -1){
      strLastAct = "TraCuuLTT";
      // khong cho phep click nut refresh
        jQuery("#refresh").removeAttr("onclick");
        jQuery("#row_ltt_0").removeAttr("onclick");        
    }else if(quyenLttDi.indexOf("T4") != -1){
      strLastAct = "TraCuuLTTToanQuoc";
      // khong cho phep click nut refresh
        jQuery("#refresh").removeAttr("onclick");
        jQuery("#row_ltt_0").removeAttr("onclick");
    }else if(quyenLttDi.indexOf("LO") != -1){
//       '<%=request.getParameter("referrer")%>'!='null'
//      type lo tw , duyet
      var type = '<%=request.getParameter("typeLO")%>';
      if(type!=undefined && type!=''){
        if(type=='TINH'){
          strLastAct = "loadDuyetLTTTinh";
        }else if(type=='TW'){
          strLastAct = "loadDuyetLTTTW";
        }else{
        strLastAct = "loadDuyetLTT";  
        }
      }else{
        strLastAct = "loadDuyetLTT";  
      }
      // khong cho phep click nut refresh
        jQuery("#refresh").removeAttr("onclick");
        jQuery("#row_ltt_0").removeAttr("onclick");
    }else if(quyenLttDi.indexOf("DHV") != -1){
       
      strLastAct = "listTCuuDHV";
      // khong cho phep click nut refresh
        jQuery("#refresh").removeAttr("onclick");
        jQuery("#row_ltt_0").removeAttr("onclick");
    }else{
      assignValue2Field("ma_nhkb_chuyen_cm", "<%=session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION)%>");
      assignValue2Field("nhkb_chuyen", "<%=session.getAttribute(AppConstants.APP_NHKB_ID_SESSION)%>");
      assignValue2Field("ten_nhkb_chuyen_cm", "<%=session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION)%>");
    }
    
    //Cho NHKB Nhan 
    jQuery(function () {
        jQuery(':editable');
        jQuery('#ma_nhkb_nhan_cm').jec();
    });
    jQuery('#ma_nhkb_nhan_cm').change(function() {
        var str1 = ""; 
        jQuery("select option:selected").each(function () { 
          str1 = jQuery(this).val(); 
        }); 
        jQuery('#ma_nhkb_nhan_cm').val(str1);
		}).change();
    //Local variable
    var msgLTTDi = '<%=strMsgLTTDi%>';
    var strLastFrm = '<%=strLastForm%>';
    var nhan = '<%=strNhan%>';
    var actionParam = '<%=strActionParam%>'
    var lydo_nkn ="";
    lydo_nkn ="<%=lydo_ngatketnoi%>";	
    if(actionParam != ''){
      jQuery("#search").hide();
    }
    
    //Global variable
    arrFieldReadonly = new Array("id", "ma_ttv_nhan", "ten_ttv_nhan", "ngay_hoan_thien", "ma_ktt_duyet", 
      "ten_ktt_duyet", "ngay_ktt_duyet", "tong_sotien", "ten_nhkb_chuyen_cm", "ten_nhkb_nhan_cm", "ma_nhkb_chuyen_cm","lydo_ktt_day_lai","lydo_gd_day_lai", "ten_nh_tgian");
    action = '<%=strLastAction%>';
    currentAction = '';
    firstCheck = false;
    counter = 0;  
    errDescription = '<%=strErrDescription%>'; 
    
    if(nhan == 'N'){
      jQuery("#lenh_thucong_tabmis").html("Lệnh thủ công");
    }else if(nhan == 'Y'){
      jQuery("#lenh_thucong_tabmis").html("Lệnh từ giao diện");
    }
    if(msgLTTDi != null && msgLTTDi != '' && action != ''){
      if(strLastFrm != ''){
          jQuery("#tblThongTinChiTietCOA tr input").attr("readOnly", false);
          //readOnly mot so fields ko fai nhap trong COA
          // kiem tra neu nhan tu tabmis thi readonly tat ca truong trong COA
          var resultEveryFocus = false;
          if(nhan != 'Y'){
                    resultEveryFocus = focusEveryTKTN();
          }
          //Enable fields disabled
          jQuery("#ma_nhkb_nhan_cm").attr("disabled", false);
          //jQuery("#nt_id").attr("disabled", false);     
          
          jQuery("#cmdAddRow").attr({"disabled":false});
          counter = 0;  
          
          if(strLastFrm == 'LAST.FORM.TRUNG.SOYCTT'){
              currentAction = '<%=AppConstants.ACTION_ADD%>';
              msgAlertByActionForm(action, msgLTTDi);
              jQuery("#dialog-confirm").dialog( "open" );
              //Hien thi truong nao con cho phep nhap, truong ko cho
              
          }else if(strLastFrm == 'LAST.FORM.EDIT' || strLastFrm == 'LAST.FORM.ADD'){        
              jQuery("#data-grid tr").removeAttr("onclick");
              jQuery("#data-grid tr").removeAttr("ondblclick");
              jQuery("#data-grid tr").removeAttr("onmouseout");
              jQuery("#data-grid tr").removeAttr("onmouseover");
              jQuery("#fsLyDoDayLai").hide();          
//              jQuery("#add").hide();
              jQuery("#save").show();
              jQuery("#search").hide();
              if('LTT.DI.THEM.LAI' == action){
                currentAction = '<%=AppConstants.ACTION_ADD%>';
                assignValue2Field("save", "ghi");
                jQuery("#save").html("&nbsp;<span class=\"sortKey\">G<\/span>hi");
                jQuery("#ngay_ct_btn").show();
                
                //Cho phep sua tat ca cac fields
                readOnlyFields(arrFieldId, false);   
                jQuery.each(arrFieldId, function(index, value){
                    strId = value.toString();
                    if (document.getElementById(strId) != null) {
                        setClass2Field(strId, "fieldText");
                    }
                });                
                //Khong cho phep sua mot so fields
                readOnlyFields(arrFieldReadonly, true);      
                                
                jQuery.each(arrFieldReadonly, function(index, value){
                    strId = value.toString();
                    if (document.getElementById(strId) != null) {
                        setClass2Field(strId, "fieldTextCode");
                    }
                });
                
                setClass2Field('ndung_tt', 'fieldTextArea');
                setClass2Field('ttin_kh_chuyen', 'fieldTextArea');
                setClass2Field('ttin_kh_nhan', 'fieldTextArea');
              }else if('LTT.DI.SUA.LAI' == action){      
                document.forms[0].action = 'lttUpdateExc.do';
                currentAction = '<%=AppConstants.ACTION_EDIT%>';
                assignValue2Field("save", "capnhat");
                jQuery("#save").html("<span class=\"sortKey\">G<\/span>hi");
                jQuery("#ngay_ct_btn").hide();
                
                //Neu Ltt den tu tabmis, hoac ko la nhap bang tay thi add them element to arrFieldReadonly khac nhau
                setAttrAfterErrorCheckInput(nhan);               
              }   
              
   
              if(msgLTTDi != null && msgLTTDi != '' && msgLTTDi != 'undefined'){                
                if(!(msgLTTDi=="success" && (action=='<%=AppConstants.ACTION_EDIT%>' ||
                               action=='<%=AppConstants.ACTION_CANCEL%>' ||
                               action=='<%=AppConstants.ACTION_APPROVED%>' ||
                               action=='<%=AppConstants.ACTION_REJECT%>'))){
                  msgAlertByActionForm(action, msgLTTDi);                
                  if(msgLTTDi == 'KHC_ERROR' || msgLTTDi == 'KHC_INVALID'){
                  //jQuery("#dialog-khc" ).dialog( "open" ); //ko dung dc, vi khi update lan gap loi KHC se loi tu dong chuyen sang lttAddExc.do
                    alert(errDescription);
                  }else{
                    jQuery("#dialog" ).dialog( "open" );
                  }
                }else{
                   document.forms[0].action= 'listLttAdd.do';
                   document.forms[0].submit(); 
                }
              }
          }  
      }else{
          clearForm(arrFieldId);
          if(!(msgLTTDi=="success" && (action=='<%=AppConstants.ACTION_EDIT%>' ||
                               action=='<%=AppConstants.ACTION_CANCEL%>' ||
                               action=='<%=AppConstants.ACTION_APPROVED%>' ||
                               action=='<%=AppConstants.ACTION_REJECT%>'))){
                                  currentAction = '<%=AppConstants.ACTION_ADD+AppConstants.ACTION_EDIT%>';//muc dich chi can currentAction khac trong
                                  msgAlertByActionForm(action, msgLTTDi);
                                  jQuery("#dialog-message" ).dialog( "open" );
           }else{
             
             document.forms[0].action= 'listLttAdd.do';
             document.forms[0].submit(); 
           }
      }
    }
    //Change style when focus to element input
//    strBgColor = '';
//    jQuery('input[type="text"],textarea').focus(function() {
//      strBgColor = this.style.backgroundColor;
//      //alert("strBgColor="+strBgColor);
//      if(!this.readOnly){
//        this.style.backgroundColor = '#ffeeaa';
//      }
//    });
//    jQuery('input[type="text"],textarea').blur(function() {
//      if(!this.readOnly){
//        this.style.backgroundColor = '';
//        strBgColor = '';
//      }
//    });
    //************ Click nut truyen lai *************
    
    jQuery("#resend").click(function(){
      var id= jQuery("#id_selected").val();
      jQuery.ajax( {
        type : "POST", url : "truyenlaiLTT.do", data :  {
           "idTL":id, "nd" : Math.random() * 100000
        },
        dataType : 'json', success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                        var exception_message = data.exception_message;
                        if (exception_message != undefined  ) {
                            alert("Truyền lại lệnh thanh toán không thành công! \n" + exception_message);
                        }
                        else {
                            var success = data.success;
                            if(success=="success"){
                              alert("Truyền lại thành công!");
                            }else{
                              alert(GetUnicode("Truy&#7873;n l&#7841;i kh&#244;ng th&#224;nh c&#244;ng :" + success));
                            }
                        }
                    }
                }
            }
        },
        error : function (textstatus) {
            alert(textstatus.messge);
        }
      });
    }); 
    
    jQuery("#approved").click(function(){ 
    //ManhNV sua (20140819)
    //Kiem tra do dai ten tai khoan chuyen/nhan neu dai hon 140 thi cho phep sua       
        if(jQuery("#ten_tk_chuyen").val().length > 146){ 
          alert(GetUnicode('T&#234;n &#273;&#417;n v&#7883; chuy&#7875;n v&#432;&#7907;t qu&#225; s&#7889; k&#253; t&#7921; cho ph&#233;p, c&#7847;n chuy&#7875;n l&#7841;i TTV ho&#224;n thi&#7879;n'));
          return;
        }
        if(jQuery("#ten_tk_nhan").val().length > 146){            
            alert(GetUnicode('T&#234;n &#273;&#417;n v&#7883; nh&#7853;n v&#432;&#7907;t qu&#225; s&#7889; k&#253; t&#7921; cho ph&#233;p, c&#7847;n chuy&#7875;n l&#7841;i TTV ho&#224;n thi&#7879;n'));
            return;
        }
        // PhuongNTM07 add on 01/08/2016 start - 
        // Neu la dien lien ngan hang thi noidung_tt < 210 ki tu
        // Neu khong thi < 220 ki tu
        if(jQuery("#ma_nhkb_nhan_cm").val().substring(2,5)!=jQuery("#ma_nhkb_nhan").val().substring(2,5)) {
            if(jQuery("#ndung_tt").val().length > 210){            
            alert(GetUnicode('N&#7897;i dung thanh to&#225;n v&#432;&#7907;t qu&#225; s&#7889; k&#253; t&#7921; cho ph&#233;p, c&#7847;n chuy&#7875;n l&#7841;i TTV ho&#224;n thi&#7879;n'));
            return;
        }      
            if(jQuery("#ttin_kh_nhan").val().length + jQuery("#ten_tk_nhan").val().length > 70){            
                alert(GetUnicode('T&#7893;ng &#273;&#7897; d&#224;i t&#234;n t&#224;i kho&#7843;n nh&#7853;n v&#224; &#273;&#7897; d&#224;i th&#244;ng tin kh&#225;ch h&#224;ng nh&#7853;n v&#432;&#7907;t qu&#225; s&#7889; k&#253; t&#7921; cho ph&#233;p, c&#7847;n chuy&#7875;n l&#7841;i TTV ho&#224;n thi&#7879;n'));
                return;
            }
            if(jQuery("#ttin_kh_chuyen").val().length + jQuery("#ten_tk_chuyen").val().length > 70){            
                alert(GetUnicode('T&#7893;ng &#273;&#7897; d&#224;i t&#234;n t&#224;i kho&#7843;n chuy&#7875;n v&#224; &#273;&#7897; d&#224;i th&#244;ng tin kh&#225;ch h&#224;ng chuy&#7875;n v&#432;&#7907;t qu&#225; s&#7889; k&#253; t&#7921; cho ph&#233;p, c&#7847;n chuy&#7875;n l&#7841;i TTV ho&#224;n thi&#7879;n'));
                return;
            }
        }
        else if(jQuery("#ndung_tt").val().length > 220){            
            alert(GetUnicode('N&#7897;i dung thanh to&#225;n v&#432;&#7907;t qu&#225; s&#7889; k&#253; t&#7921; cho ph&#233;p, c&#7847;n chuy&#7875;n l&#7841;i TTV ho&#224;n thi&#7879;n'));
            return;
        }
        //if(jQuery("#ndung_tt").val().length > 220){            
        //    alert(GetUnicode('N&#7897;i dung thanh to&#225;n v&#432;&#7907;t qu&#225; s&#7889; k&#253; t&#7921; cho ph&#233;p, c&#7847;n chuy&#7875;n l&#7841;i TTV ho&#224;n thi&#7879;n'));
        //    return;
        //}
        // PhuongNTM07 add on 01/08/2016 end - 
      //Kiem tra tong tien chi tiet
      if(!checkTongTienChiTiet()){
        return false;
      }
      //
      currentAction = '<%=AppConstants.ACTION_APPROVED%>';
      document.forms[0].target='';
      var id = jQuery("#id").val();
      if("Y"==strChkKy){
      if(!ky(strLoaiUser)){
        alert("Bạn ký Không thành công");
        return false;
      }
      }
      checkBeforeApprove(id);
    });
    
    jQuery("#cancel").click(function(){
      currentAction = '<%=AppConstants.ACTION_CANCEL%>';
      cancelLTTDi();
  });
    //**************************BUTTON Tim kiem CLICK********************************************
    jQuery("#search").click(function(){      
//      currentAction = '<%=AppConstants.ACTION_FIND%>';
      jQuery("#dialog-form-tim-kiem-so-yctt").dialog( "open" );           
      if(strLoaiUser != null && strLoaiUser != 'null'){
        if(strLoaiUser.indexOf("<%=AppConstants.NSD_TTV%>") != -1){
          jQuery("#nhkbchuyennhan").focus(); 
        }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_KTT%>") != -1){
          jQuery("#ttvnhan").focus();
        }
      }
    });
    
    jQuery("#exit").click(function(){ 
      jQuery("#exit").focus();
      var param = '';
      if('<%=request.getParameter("referrer")%>'!='null'){
        strLastAct='<%=request.getParameter("referrer")%>';
        if(strLastAct=='themdtslttdi')
          param='?action=add&id=<%=request.getParameter("so_ltt_details")%>';
        else if(strLastAct=='dtsoattloiView')
          param='?action=VIEW_DETAIL&id=<%=request.getParameter("id")%>';
      }else if(strLastAct == 'TraCuuLTT'){
        strLastAct = 'TraCuuLTT.do';
      }else if(strLastAct == 'loadDuyetLTT'){
         param='?1=1'+'<%=tcuu%>';    
      }else if(strLastAct == 'TraCuuLTTToanQuoc'){
        strLastAct = 'TraCuuLTTToanQuocList.do';
      }else if(strLastAct == 'listTCuuDHV'){
         strLastAct = strLastAct; 
      }
      else if(strLastAct == 'loadDuyetLTTTW'){
         strLastAct = strLastAct; 
      }
      else if(strLastAct == 'loadDuyetLTTTinh'){
         strLastAct = strLastAct; 
      }
      else if(currentAction != ''){
        strLastAct = 'listLttAdd.do';
      }else if(currentAction == ''){
        strLastAct = 'mainAction.do';
      }
      sbExitLttDi(strLastAct,param);
    });
  });  
  jQuery("#rejectLtt").click(function(){
      currentAction = '<%=AppConstants.ACTION_REJECT%>';
      document.forms[0].target='';
      var result = false;
      var imgPath = "/TTSP/styles/images/return.jpeg";
      if(strLoaiUser.indexOf("<%=AppConstants.NSD_GD%>") != -1){
        result = checkInputDayLai('lydo_gd_day_lai', 'GĐ');
        imgPath = "/TTSP/styles/images/gd_return.jpeg";
      }else if(strLoaiUser.indexOf("<%=AppConstants.NSD_KTT%>") != -1){                
        result = checkInputDayLai('lydo_ktt_day_lai', 'KTT');
      }
      if(result){
        rejectLttDi(imgPath);
      }
    });
  function completeActionLttDi(){ 
    var id= jQuery("#id_selected").val();
    var typeLtt = jQuery("#nhan").val();
    var trang_thai = jQuery("#trang_thai").val();
    var tong_sotien = jQuery("#tong_sotien").val();
    var ngay_ct = jQuery("#ngay_ct").val();
    var ngay_tt = jQuery("#ngay_tt").val();
    var ttv_nhan = jQuery("#ttv_nhan").val();
    var nt_id = jQuery("#nt_id").val();
    var ten_tk_chuyen = jQuery("#ten_tk_chuyen").val();
    var ndung_tt = jQuery("#ndung_tt").val();
    var ma_nhkb_nhan_cm = jQuery("#ma_nhkb_nhan_cm").val();
    var nhkb_nhan = jQuery("#nhkb_nhan").val();
    var ma_nhkb_nhan = jQuery("#ma_nhkb_nhan").val();
    var ten_nhkb_nhan = jQuery("#ten_nhkb_nhan").val();
    var id_nhkb_nhan = jQuery("#id_nhkb_nhan").val();
//    ma_nhkb_nhan_cm,nhkb_nhan,ma_nhkb_nhan,id_nhkb_nhan
    var so_ct_goc = jQuery("#so_ct_goc").val();
    var so_yctt = jQuery("#so_yctt").val();
    var so_tk_chuyen = jQuery("#so_tk_chuyen").val();
    var ma_nhkb_chuyen = jQuery("#ma_nhkb_chuyen").val();
    var id_nhkb_chuyen = jQuery("#id_nhkb_chuyen").val();
    var ttin_kh_chuyen = jQuery("#ttin_kh_chuyen").val();
    var ten_tk_nhan = jQuery("#ten_tk_nhan").val();
    var ttin_kh_nhan = jQuery("#ttin_kh_nhan").val();
    var so_tk_nhan = jQuery("#so_tk_nhan").val();
    var so_tien_mua_ban = jQuery("#so_tien_mua_ban").val();
    var ma_nt_mua_ban = jQuery("#ma_nt_mua_ban").val();
    var loai_phi = jQuery("#phi").val();
    var nh_tgian = jQuery("#nh_tgian").val();
    var ten_nh_tgian = jQuery("#ten_nh_tgian").val();
    var ma_nhkb_nhan_cu_hiden = jQuery("#ma_nhkb_nhan_cu_hiden").val();
    var sua_ten_tk_flag = jQuery("#sua_ten_tk_flag").val();
    
    var vay_tra_no_nn = "";    
    if(document.getElementById("vay_tra_no_nn").checked){
      vay_tra_no_nn = "Y";
    }
    
//  so_ct_goc,so_yctt,so_tk_chuyen,ma_nhkb_chuyen,id_nhkb_chuyen,ttin_kh_chuyen,ten_tk_nhan,ttin_kh_nhan,so_tk_nhan
    var arrTkkt_ma = document.getElementsByName("tkkt_ma"), arrMa_quy = document.getElementsByName("ma_quy"), arrDvsdns_ma = document.getElementsByName("dvsdns_ma"), arrCapns_ma = document.getElementsByName("capns_ma"), arrChuongns_ma = document.getElementsByName("chuongns_ma"), arrNganhkt_ma = document.getElementsByName("nganhkt_ma"), arrNdkt_ma = document.getElementsByName("ndkt_ma"), arrDbhc_ma = document.getElementsByName("dbhc_ma"), arrCtmt_ma = document.getElementsByName("ctmt_ma"), arrMa_nguon = document.getElementsByName("ma_nguon"), arrDu_phong_ma = document.getElementsByName("du_phong_ma"), arrMa_kb = document.getElementsByName("kb_ma"), arrDien_giai = document.getElementsByName("dien_giai"), arrSo_tien = document.getElementsByName("so_tien");
    jQuery.ajaxSettings.traditional = true;
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
        // truong hop khong co coa thi khong check nua
    }
   
    var row_id_select_b4 = document.getElementById('row_select').value;    
    var strImg = "";    
    var strTitle="";
    jQuery.ajax( {
        type : "POST", url : "lttUpdateExc.do", data :  {
           "id" : id,"trang_thai":trang_thai,"tong_sotien":tong_sotien,"ten_tk_chuyen" : ten_tk_chuyen,
           "ngay_ct":ngay_ct, "ngay_tt" :ngay_tt,"ttv_nhan":ttv_nhan,"nt_id":nt_id,"ndung_tt":ndung_tt,
           "ma_nhkb_nhan_cm":ma_nhkb_nhan_cm,"nhkb_nhan":nhkb_nhan,"ma_nhkb_nhan":ma_nhkb_nhan,"id_nhkb_nhan":id_nhkb_nhan,
           "so_ct_goc":so_ct_goc,"so_yctt":so_yctt,"so_tk_chuyen":so_tk_chuyen,"ma_nhkb_chuyen":ma_nhkb_chuyen,
           "id_nhkb_chuyen":id_nhkb_chuyen,"ttin_kh_chuyen":ttin_kh_chuyen,"ten_tk_nhan":ten_tk_nhan,"ttin_kh_nhan":ttin_kh_nhan,
           "so_tk_nhan":so_tk_nhan,"typeLtt":typeLtt,"ten_nhkb_nhan":ten_nhkb_nhan,
           "tkkt_ma" : arrTkkt_ma_res, "ma_quy" : arrMa_quy_res, "dvsdns_ma" : arrDvsdns_ma_res, "capns_ma" : arrCapns_ma_res,
           "chuongns_ma" : arrChuongns_ma_res, "nganhkt_ma" : arrNganhkt_ma_res, "ndkt_ma" : arrNdkt_ma_res, "dbhc_ma" : arrDbhc_ma_res, 
           "ctmt_ma" : arrCtmt_ma_res, "ma_nguon" : arrMa_nguon_res,
           "du_phong_ma" : arrDu_phong_ma_res, "kb_ma" : arrMa_kb_res, "dien_giai" : arrDien_giai_res, "so_tien" : arrSo_tien_res, 
           "so_tien_mua_ban":so_tien_mua_ban, "ma_nt_mua_ban":ma_nt_mua_ban, "phi":loai_phi, "nh_tgian":nh_tgian, "ten_nh_tgian":ten_nh_tgian, 
           "vay_tra_no_nn":vay_tra_no_nn, "ma_nhkb_nhan_cu_hiden":ma_nhkb_nhan_cu_hiden, "sua_ten_tk_flag":sua_ten_tk_flag,
           "nd" : Math.random() * 100000
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
                              // xoa het style tren row cu va add style mac dinh
                              jQuery("#" + row_id_select_b4+" td:nth-child(1)").find('input').removeAttr('class');
                              jQuery("#" + row_id_select_b4+" td:nth-child(2)").removeAttr('class');
                              jQuery("#" + row_id_select_b4).attr( {
                                  'class' : 'ui-widget-content jqgrow ui-row-ltr'
                              });
                              // set anh va title cho anh mo ta trang thai
                              // set mo ta trang thai 
                              if(data.strTrangThai!=undefined && data.strTrangThai!=''){
                                if(data.strTrangThai =='03'){
                                  strTitle ="Chờ KTT duyệt";
                                  strImg = "/TTSP/styles/images/wait.jpeg";
                                }else{
                                  strTitle ="Chờ GD duyệt";
                                  strImg = "/TTSP/styles/images/wait-gd.png";
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
                              if(firstRow!='' && firstRow!=undefined){
                                var chucdanh="";
                                var quyenLttDi = "<%=strQuyenLttDi%>"!=null?"<%=strQuyenLttDi%>":"";
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
                                defaultRowSelectedFirstRowLTT('loadLTTDiJsonAction.do', firstValue,firstRow, chucdanh,quyenLttDi);
                              }
                          }
                          else if (data.strMsgEx=='<%=AppConstants.FAILURE%>') {
                            alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.sua_that_bai"/>'));
                          }
                          else if (data.strMsgEx=='<%=AppConstants.LTT_KHONG_CO_QUYEN%>') {
                            alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.khong_du_quyen"/>'));
                          }
                          else if (data.strMsgEx=='<%=AppConstants.LTT_NGAT_KET_NOI%>') {
                            alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.daylai_ngat_ket_noi"/>  \n ' + data.LyDoNgatKetNoi));
                          }
                          else if(data.strMsgEx=='LTT_TRANG_THAI_DA_THAY_DOI'){
                            alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi '+ id + '<fmt:message key="ltt_di.page.message.trang_thai_thay_doi"/>'));
                          }
                          else {
                            alert(GetUnicode('Ho&#224;n thi&#7879;n b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i ! \n' + data.strMsgEx));
                          }
                        }else{
                          alert(GetUnicode('&#272;&#227; c&#243; l&#7895;i x&#7843;y ra trong qu&#225; tr&#236;nh th&#7921;c hi&#7879;n Ho&#224;n thi&#7879;n b&#7843;n ghi '+ id));
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
  function rejectLttDi(strImgReturnPath){
    var id= jQuery("#id_selected").val();
    var lydo_ktt_day_lai= jQuery("#lydo_ktt_day_lai").val();
    var lydo_gd_day_lai= jQuery("#lydo_gd_day_lai").val();
    var row_id_select_b4 = document.getElementById('row_select').value;    
    var strImg = strImgReturnPath;
    var strTitle="";
  
    jQuery.ajax( {
        type : "POST", url : "lttReject.do", data :  {
           "id" : id,"lydo_ktt_day_lai":lydo_ktt_day_lai,"lydo_gd_day_lai":lydo_gd_day_lai, "nd" : Math.random() * 100000
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
                              // thong bao thanh cong
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
                                var quyenLttDi = "<%=strQuyenLttDi%>"!=null?"<%=strQuyenLttDi%>":"";
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
                                defaultRowSelectedFirstRowLTT('loadLTTDiJsonAction.do', firstValue,firstRow, chucdanh,quyenLttDi);
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
  function cancelLTTDi(){
    var id= jQuery("#id_selected").val();
    var trang_thai= jQuery("#trang_thai").val();
    var nhan= jQuery("#nhan").val();
    var row_id_select_b4 = document.getElementById('row_select').value;    
    var strImg = "/TTSP/styles/images/delete1.gif";
    var strTitle="Hủy";
    
    jQuery.ajax( {
        type : "POST", url : "lttCancel.do", data :  {
           "id" : id, "trang_thai" :  trang_thai, "nhan" : nhan, "nd" : Math.random() * 100000
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
//                        
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
                                var quyenLttDi = "<%=strQuyenLttDi%>"!=null?"<%=strQuyenLttDi%>":"";
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
                                defaultRowSelectedFirstRowLTT('loadLTTDiJsonAction.do', firstValue,firstRow, chucdanh,quyenLttDi);
                              }
                          }else if (data.strMsgEx=='<%=AppConstants.FAILURE%>') {
                            alert(GetUnicode('H&#7911;y b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.huy_that_bai"/>'));
                          }
                          else if (data.strMsgEx=='<%=AppConstants.LTT_KHONG_CO_QUYEN%>') {
                            alert(GetUnicode('H&#7911;y b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.khong_du_quyen"/>'));
                          }
                          else if (data.strMsgEx=='<%=AppConstants.LTT_TRANG_THAI_DA_THAY_DOI%>') {
                            alert(GetUnicode('H&#7911;y b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.trang_thai_da_thay_doi"/>'));
                          }
                          else if (data.strMsgEx=='<%=AppConstants.LTT_NGAT_KET_NOI%>') {
                            alert(GetUnicode('H&#7911;y b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.cancel.ngat_ket_noi"/> \n ' + data.LyDoNgatKetNoi));
                          }
                          else if (data.strMsgEx=='<%=AppConstants.LTT_TRANG_THAI_DA_THAY_DOI%>') {
                            alert(GetUnicode('H&#7911;y b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i : ' + '<fmt:message key="ltt_di.page.message.trang_thai_thay_doi"/>'));
                          }
                          else {
                            alert(GetUnicode('H&#7911;y b&#7843;n ghi '+ id + ' th&#7845;t b&#7841;i :' + data.strMsgEx));
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
  function sbExitLttDi(strLastAct, param) {	 
      if(strLastAct != null && strLastAct != ""){
        //window.history.go(-1);
        if(strLastAct.indexOf('.do') == -1){
          strLastAct = strLastAct + ".do" + param;
        }else{
          strLastAct = strLastAct + param;
        } 
        if(strLastAct == 'TraCuuLTT.do'){
          var ttv_nhan_back = '<%=request.getParameter("ttv_nhan")%>';
          var trang_thai_back= '<%=request.getParameter("trang_thai")%>';
          var tu_ngay_back= '<%=request.getParameter("tu_ngay")%>';
          var den_ngay_back= '<%=request.getParameter("den_ngay")%>';
          var tu_ngay_nhan_back= '<%=request.getParameter("tu_ngay_nhan")%>';
          var den_ngay_nhan_back= '<%=request.getParameter("den_ngay_nhan")%>';
          var so_ltt_back= '<%=request.getParameter("ltt_id")%>';
          var loai_lenh_tt_back = '<%=request.getParameter("loai_lenh_tt")%>';
          var ma_nhkb_chuyen_cm_back = '<%=request.getParameter("ma_nhkb_chuyen_cm")%>';
          var ma_nhkb_nhan_cm_back = '<%=request.getParameter("ma_nhkb_nhan_cm")%>';
          var ten_nhkb_chuyen_cm_back = '<%=request.getParameter("ten_nhkb_chuyen_cm")%>';
          var ten_nhkb_nhan_cm_back = '<%=request.getParameter("ten_nhkb_nhan_cm")%>';
          var so_tien_back = '<%=request.getParameter("tong_sotien")%>';
          var so_yctt_back = '<%=request.getParameter("so_yctt")%>';
          var kb_tinh_back = '<%=request.getParameter("kb_tinh")%>';
          var kb_huyen_back = '<%=request.getParameter("kb_huyen")%>';
          var ma_nt_back = '<%=request.getParameter("ma_nt")%>';
           
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
           if(tu_ngay_nhan_back != 'null'){
             strLastAct += "&tu_ngay_nhan_back="+tu_ngay_nhan_back+"";
           }
            if(den_ngay_nhan_back != 'null'){
             strLastAct += "&den_ngay_nhan_back="+den_ngay_nhan_back+"";
           }
           if(so_ltt_back != 'null'){
             strLastAct += "&so_ltt_back="+so_ltt_back+"";
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
           if(ma_nt_back != 'null'){
             strLastAct += "&ma_nt_back="+ma_nt_back+"";
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
          var ma_nt_back = '<%=request.getParameter("ma_nt")%>';
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
           if(ma_nt_back != 'null'){
             strLastAct += "&ma_nt_back="+ma_nt_back+"";
           }
           if(tu_ngay_nhan_back != 'null'){
             strLastAct += "&tu_ngay_nhan_back="+tu_ngay_nhan_back+"";
           }
           if(den_ngay_nhan_back != 'null'){
             strLastAct += "&den_ngay_nhan_back="+den_ngay_nhan_back+"";
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
          strLastAct +=urlBack;
        }
        window.location = strLastAct;
      }
      else{
         if(confirm('Bạn có chắc muốn thoát khỏi chức năng này?')==false)
            return false;
         else {
            window.location="listLttAdd.do";
            //window.location="mainAction.do";
            //window.location = strLastAct + ".do";         
         }
      } 
  } 
  //************* Click nut Hoan thien *************
    function completeLttClicked(trang_thai){
      firstCheck = true;
      jQuery("#id").show();  
      assignValue2Field("save", "capnhat");
      jQuery("#save").html("<span onclick=\"checkSuccess('lttDiAdd');\" class=\"sortKey\">G<\/span>hi");    
      jQuery("#save").show();
      jQuery("#actionForSave").val("update");
      jQuery("#completeLtt").hide();
      jQuery("#cancel").hide();
      jQuery("#search").show();   
      jQuery("#in").show();       
      if(jQuery("#trang_thai").val()=='01')
        jQuery("#fsLyDoDayLai").show();
      else
        jQuery("#fsLyDoDayLai").hide();
      jQuery("#cmdAddRow").attr({"disabled":false});
      jQuery('#ma_nhkb_nhan_cm').append('<option value="" class="jecEditableOption"> <\/option>');
      //ManhNV-20141101
      if(jQuery("#trang_thai").val()=='02'){
        jQuery("#ttv_nhan").val('<%=strUserId%>');      
        jQuery("#ma_ttv_nhan").val('<%=strMaNsd%>');      
        jQuery("#ten_ttv_nhan").val('<%=strTenNsd%>');
      }
      jQuery("#ttloai_lenh").attr({"disabled":true});
    
      var strNhan = jQuery("#nhan").val();
      //20150618**************************************************
        //Cho nhap ma NH trung gian
        jQuery("#nh_tgian").attr({"disabled":false});
        jQuery("#nh_tgian").removeAttr("readonly");
        setClass2Field('nh_tgian', 'fieldText');
        //20150618**************************************************
      if(strNhan == 'Y'){     
              // dvqhns = 0000000 cho phep sua ten dv chuyen
              if(jQuery("#dvsdns_ma").val()=='0000000' || jQuery("#dvsdns_ma").val()==''){
                jQuery("#ten_tk_chuyen").removeAttr("readonly");
                setClass2Field('ten_tk_chuyen', 'fieldText');
              }else if(!checkExistSpecialTKTN()){
                if(checkExistDVQHNSAnNinh()){
                  jQuery("#ten_tk_chuyen").attr({'readOnly':false});
                  setClass2Field('ten_tk_chuyen', 'fieldText');
                }            
              }
            // Set trang thai cho thong tin nguoi nhan ten_tk_nhan
              var soTK_Nhan = '<%=strSoTK_NguoiNhanStatus%>';
              var tenTK_Nhan = '<%=strTenTK_NguoiNhanStatus%>';
              var nhKB_Nhan = '<%=strNHKB_NguoiNhanStatus%>';
              var ttTK_Nhan = '<%=strTTTK_NguoiNhanStatus%>';
              if(soTK_Nhan == 'Y'){
                jQuery("#so_tk_nhan").attr({"disabled":""});
                jQuery("#so_tk_nhan").removeAttr("readonly");
                setClass2Field('so_tk_nhan', 'fieldText');
              }else{
                jQuery("#so_tk_nhan").attr({"disabled":false});
                jQuery("#so_tk_nhan").attr({"readonly":true});
                setClass2Field('so_tk_nhan', 'fieldTextCode');
              }
              if(tenTK_Nhan == 'Y'){
                jQuery("#ten_tk_nhan").attr({"disabled":false});
                jQuery("#ten_tk_nhan").removeAttr("readonly");
                setClass2Field('ten_tk_nhan', 'fieldText');
              }else{
                jQuery("#ten_tk_nhan").attr({"disabled":false});
                jQuery("#ten_tk_nhan").attr("readOnly", true);
                setClass2Field('ten_tk_nhan', 'fieldTextCode');
              }
              if(nhKB_Nhan == 'Y'){
                jQuery("#ma_nhkb_nhan").attr({"disabled":false});
                jQuery("#ma_nhkb_nhan").removeAttr("readonly");
                setClass2Field('ma_nhkb_nhan', 'fieldText');
                jQuery("#ten_nhkb_nhan").attr({"disabled":false});
                jQuery("#ten_nhkb_nhan").removeAttr("readonly");
                setClass2Field('ten_nhkb_nhan', 'fieldTextTrans');
              }else{
                jQuery("#ma_nhkb_nhan").attr({"disabled":false});
                jQuery("#ma_nhkb_nhan").attr("readOnly", true);
                setClass2Field('ma_nhkb_nhan', 'fieldTextCode');
                //Kiem tra co phai ngan hang nuoc ngoai khong
                //Neu la NH nuoc ngoai thi cho phep sua ma,ten NH
                if(jQuery("#ma_nhkb_nhan").val() == '99999999'){
                  jQuery("#ma_nhkb_nhan").attr({"disabled":false});
                  jQuery("#ma_nhkb_nhan").removeAttr("readonly");
                  setClass2Field('ma_nhkb_nhan', 'fieldText');     
                  //20150716:set gia tri hiden de nhan biet ma nhkb nhan cu la 99999999
                  jQuery("#ma_nhkb_nhan_cu_hiden").val(jQuery("#ma_nhkb_nhan").val());
                }else{                  
                  jQuery("#ma_nhkb_nhan").attr({"disabled":false});
                  jQuery("#ma_nhkb_nhan").attr("readOnly", true);
                  setClass2Field('ma_nhkb_nhan', 'fieldTextCode');
                  //20150716:set gia tri hiden de nhan biet ma nhkb nhan cu la 99999999
                  jQuery("#ma_nhkb_nhan_cu_hiden").val(jQuery("#ma_nhkb_nhan").val());
                }
              }
              if(ttTK_Nhan == 'Y'){
                jQuery("#ttin_kh_nhan").attr({"disabled":false});
                jQuery("#ttin_kh_nhan").removeAttr("readonly");
                setClass2Field('ttin_kh_nhan', 'fieldTextArea');
              }else{
                jQuery("#ttin_kh_nhan").attr({"disabled":false});
                jQuery("#ttin_kh_nhan").attr("readOnly", true);
                setClass2Field('ttin_kh_nhan', 'fieldTextAreaRO');
              }
            //Khoa cac truong: NHKB nhan, ngay_ct, nt_id
            jQuery("#ma_nhkb_nhan_cm").attr({"disabled":true});
            jQuery("#ngay_ct_btn").hide();
            jQuery("#nt_id").attr({"disabled":true});
                        
            jQuery("#ma_nhkb_nhan_cm").attr({'readOnly':true});
            
            jQuery("#cmdAddRow").attr({"disabled":true});
            //20150317
            jQuery("#ma_nt_mua_ban").attr({"readOnly":false});
            setClass2Field('ma_nt_mua_ban', 'fieldText');
            jQuery("#so_tien_mua_ban").attr({"readOnly":false});            
                        
            jQuery("#phi").attr({"disabled":true});
            setClass2Field('phi', 'fieldTextCode');
            //
            
            jQuery("#ndung_tt").attr({"readOnly":false});
            setClass2Field('ndung_tt', 'fieldTextArea');
            //jQuery("#tkkt_ma").focus();
            jQuery("#ndung_tt").focus();
            
        }else if(strNhan == 'N'){
              /*
             * 01/08/2013
             * SUA YEU CAU KHI HOAN THI
             * CHUCLH
             * 
             * */
            jQuery("#so_tk_nhan").attr({"disabled":""});
            jQuery("#so_tk_nhan").removeAttr("readonly");
            setClass2Field('so_tk_nhan', 'fieldText');
            jQuery("#ten_tk_nhan").attr({"disabled":false});
            jQuery("#ten_tk_nhan").removeAttr("readonly");
            setClass2Field('ten_tk_nhan', 'fieldText');
            jQuery("#ma_nhkb_nhan").attr({"disabled":false});
            jQuery("#ma_nhkb_nhan").removeAttr("readonly");
            setClass2Field('ma_nhkb_nhan', 'fieldText');
            jQuery("#ten_nhkb_nhan").attr({"disabled":false});
            jQuery("#ten_nhkb_nhan").removeAttr("readonly");
            setClass2Field('ten_nhkb_nhan', 'fieldText');
            jQuery("#ttin_kh_nhan").attr({"disabled":false});
            jQuery("#ttin_kh_nhan").removeAttr("readonly");
            setClass2Field('ttin_kh_nhan', 'fieldText');
             /*
              * END SUA YEU CAU KHI HOAN THIEN
              * */
            jQuery("#tblThongTinChiTietCOA tr input").removeAttr("readOnly");
            //readOnly mot so fields ko fai nhap trong COA
            focusEveryTKTN();
            jQuery("#cmdAddRow").removeAttr("disabled");
            //Mo khoa cac truong: NHKB nhan, ngay_ct, nt_id
            jQuery("#ma_nhkb_nhan_cm").attr({"disabled":false});
            jQuery("#ngay_ct_btn").show();
 //           jQuery("#nt_id").attr({"disabled":false});
            
            readOnlyFields(arrFieldId, false);  
            jQuery("#ma_nhkb_nhan_cm").focus();
            
            jQuery.each(arrFieldId, function(index, value){
                strId = value.toString();
                if (document.getElementById(strId) != null) {
                    setClass2Field(strId, "fieldText");
                }
            });
//            jQuery.each(arrFieldIdLttCTiet, function(index, value){
//                strId = value.toString();
//                if (document.getElementById(strId) != null) {
//                  if(strId== 'so_tien'){
//                    setClass2Field(strId, "fieldTextRight");
//                  }else{
//                    setClass2Field(strId, "fieldTextCenter");
//                  }
//                }
//            });
                    
            readOnlyFields(arrFieldReadonly, true);      
            jQuery.each(arrFieldReadonly, function(index, value){
                strId = value.toString();
                if (document.getElementById(strId) != null) {
                    setClass2Field(strId, "fieldTextCode");
                }
            });
            setClass2Field('ndung_tt', 'fieldTextArea');
            setClass2Field('ttin_kh_chuyen', 'fieldTextArea');
//            setClass2Field('ttin_kh_nhan', 'fieldTextArea');
        }  
        var tabIndex=101;
        jQuery.each(arrFieldFocusTTChung, function (index, value) {
            strId = value.toString();
            if (document.getElementById(strId) != null) {
                if(document.getElementById(strId).readOnly!=true)
                {
                  document.getElementById(strId).tabIndex  = ++tabIndex;
                }else{
                  jQuery("#"+strId).removeAttr("tabIndex");
                }
            }
        });  
        jQuery.each(arrFieldFocusTTNT, function (index, value) {
            strId = value.toString();
            if (document.getElementById(strId) != null) {
                if(document.getElementById(strId).readOnly!=true)
                {
                  document.getElementById(strId).tabIndex  = ++tabIndex;
                }else{
                  jQuery("#"+strId).removeAttr("tabIndex");
                }
            }
        }); 
        jQuery.each(arrFieldIdLttCTiet, function(index, value){
                strId = value.toString();
                if (document.getElementById(strId) != null) {
                    if(document.getElementById(strId).readOnly!=true)
                    {
                      document.getElementById(strId).tabIndex  = ++tabIndex;
                    }else{
                      jQuery("#"+strId).removeAttr("tabIndex");
                    }
                }
            });
        document.getElementById("save").tabIndex  = ++tabIndex;
         if(jQuery("#ma_nt_mua_ban").val() == 'VND'){  
          jQuery("#so_tien_mua_ban").val('');
          jQuery("#so_tien_mua_ban").attr("readonly", true);
          jQuery("#so_tien_mua_ban").removeAttr("class");
          jQuery("#so_tien_mua_ban").addClass("fieldTextCode");
        }else {
          jQuery("#so_tien_mua_ban").removeAttr("readonly");
          jQuery("#so_tien_mua_ban").removeAttr("class");
          jQuery("#so_tien_mua_ban").addClass("fieldText");
        }       
//    });
    }
  function msgAlertByActionForm(action, msgLTTDi){
    var lydo_nkn = "<%=lydo_ngatketnoi%>";
    if(lydo_nkn=='null' || lydo_nkn==null || lydo_nkn=='undefined'){
      lydo_nkn=''
    }
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
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
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
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.add.ngat_ket_noi"/> <br/> ' + lydo_nkn);
          } 
    }else if(action=='<%=AppConstants.ACTION_EDIT%>'){
          if(msgLTTDi == '<%=AppConstants.FAILURE%>'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.sua_that_bai"/>');
          }else if(msgLTTDi == 'KHONG_CO_QUYEN'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
          }else if(msgLTTDi == 'NGAT_KET_NOI') {
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.hoanthien_ngat_ket_noi"/>  <br/> ' + lydo_nkn);
          }
          else if(msgLTTDi == 'MA_KHONG_HOP_LE'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ma_coa_khong_hop_le"/>');
          }else if(msgLTTDi == 'INVALID'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_khong_hop_le"/>');
          }else if(msgLTTDi == 'ERROR'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ket_hop_cheo_loi"/>');
          }else if(msgLTTDi == 'LTT_TRANG_THAI_DA_THAY_DOI'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.trang_thai_thay_doi"/>');
          }
      
    } else if(action=='<%=AppConstants.ACTION_CANCEL%>'){
          if(msgLTTDi == '<%=AppConstants.FAILURE%>'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.huy_that_bai"/>');
          }else if(msgLTTDi == 'KHONG_CO_QUYEN'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
          }else if(msgLTTDi == 'TRANGTHAI_DA_THAY_DOI'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.trang_thai_da_thay_doi"/>');
          }else if(msgLTTDi == 'NGAT_KET_NOI') {
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.cancel.ngat_ket_noi"/> \n ' + lydo_nkn);
          }else if(msgLTTDi == 'LTT_TRANG_THAI_DA_THAY_DOI'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.trang_thai_thay_doi"/>');
          }
      
    }else if (action=='<%=AppConstants.ACTION_APPROVED%>'){
          if(msgLTTDi == '<%=AppConstants.FAILURE%>'){        
            if(errDescription == 'ltt_di.error.khong_gui_duoc'){
              jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.duyet_that_bai"/>'+' Lỗi không gửi được lệnh này');
            }else if(errDescription == 'ltt_di.error.khong_duoc_phep_duyet'){
              jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.duyet_that_bai"/>'+' Bạn không được phép duyệt lệnh này');
            }else if(errDescription == 'ltt_di.error.cap_nhat_loi'){
              jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.duyet_that_bai"/>'+' Lỗi trong quá trình cập nhật lệnh này');
            }else if(errDescription == 'ltt_di.error.chu_ky_so'){
              jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.duyet_that_bai"/>'+' Lỗi Chữ ký số');
            }else{
              jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.duyet_that_bai"/>');
            }
        
      }else if(msgLTTDi == 'KHONG_CO_QUYEN'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
      }else if(msgLTTDi == 'NGAT_KET_NOI') {
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.duyet_ngat_ket_noi"/> \n ' + lydo_nkn);
      }
      else if(msgLTTDi == 'INVALID'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.chu_ky_khong_hop_le"/>');
      }else if(msgLTTDi == 'ERROR') {
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.chu_ky_loi"/>');
      }
            
    }else if (action=='<%=AppConstants.ACTION_REJECT%>'){
          if(msgLTTDi == '<%=AppConstants.FAILURE%>'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.day_lai_that_bai"/>');
          }else if(msgLTTDi == 'KHONG_CO_QUYEN'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
          }else if(msgLTTDi == 'NGAT_KET_NOI') {
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.daylai_ngat_ket_noi"/> \n ' + lydo_nkn);
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
            jQuery("#dlgMessage" ).html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
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
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.ngat_ket_noi"/> \n ' + lydo_nkn);
          }         
    }else if (action=='LTT.DI.SUA.LAI'){
          if(msgLTTDi == '<%=AppConstants.SUCCESS%>'){
            jQuery("#dlgMessage" ).html('<fmt:message key="ltt_di.page.message.sua_thanh_cong"/>');
          }else if(msgLTTDi == '<%=AppConstants.FAILURE%>'){
            jQuery("#dlgMessage" ).html('<fmt:message key="ltt_di.page.message.sua_that_bai"/>');
          }else if(msgLTTDi == 'KHONG_CO_QUYEN'){
            jQuery("#dlgMessage" ).html('<fmt:message key="ltt_di.page.message.khong_du_quyen"/>');
          }else if(msgLTTDi == 'NGAT_KET_NOI') {
            jQuery("#dlgMessage" ).html('<fmt:message key="ltt_di.page.message.ngat_ket_noi"/>');
          }
          else if(msgLTTDi == 'MA_KHONG_HOP_LE'){
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
          }else if(msgLTTDi == 'LTT_TRANG_THAI_DA_THAY_DOI'){
            jQuery("#message" ).html('<fmt:message key="ltt_di.page.message.trang_thai_thay_doi"/>');
          }
    }
  }
  function initDialogTimKiem(){
    jQuery("#dialog-form-tim-kiem-so-yctt").dialog({
      autoOpen: false,resizable : false,
//      height: "350px",
      maxHeight:"350",
      width: "800px",
      modal: true,
      buttons: {
        "Tìm kiếm": function() {
          findLTTSoYCTT('listLttAdd.do', '<%=strUserType%>', 'loadLTTDiJsonAction.do');
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
  function initDialogMsg(soLtt){
    jQuery("#dialog-message").dialog({
      autoOpen: false,
      maxHeight:"160px",
      width:"380px",
      modal: true,
      buttons: {
        Ok: function() {
          jQuery(this).dialog( "close" );
          if(soLtt != "" && soLtt != null)
            window.location = 'listLttAdd.do?sid='+soLtt;
          else
            window.location = 'listLttAdd.do?sid=""';
          var idFieldFocus=jQuery("#focusField").val();
          if(idFieldFocus!=null && idFieldFocus!='')
            jQuery("#"+idFieldFocus).focus();
        }
     },
     "Đóng": function() {
//        if(soLtt != "" && soLtt != null)
//          window.location = 'listLttAdd.do?sid='+soLtt;
//        else
//          window.location = 'listLttAdd.do';
      },
      close: function(event, ui) {
//        window.location = 'listLttAdd.do';
      }
    });
  }
  function dialogMsg(fieldFocus){
    jQuery("#dialog").dialog({
      autoOpen: false,
      maxHeight:"160px",
      width:"380px",
      modal: true,
      buttons: {
        Ok: function() {
          jQuery(this).dialog( "close" );          
          if(fieldFocus!=null && fieldFocus!='')
            jQuery("#"+fieldFocus).focus();
        }
     },
     "Đóng": function() {
        jQuery(this).dialog( "close" );
      }
    });
  }

  function initDialogConfrm(){
    jQuery("#dialog-confirm").dialog({
      autoOpen: false,
      resizable: false,
      maxHeight:"200px",
      width:"380px",
      modal: true,
      buttons: {
        "Có": function() {
          document.forms[0].target='';
          //action here
          if (checkInput(jQuery("#actionForSave").val(),jQuery("#ttloai_lenh").val())) {
          if(jQuery("#actionForSave").val()=='update'){
            document.forms[0].action = 'lttUpdateExc.do';
          }else{
            document.forms[0].action = 'lttDiAddExc.do';
          }
            document.forms[0].submit();
          }
          jQuery(this).dialog("close");       
        },
        "Không": function() {
          jQuery(this).dialog("close");
          //Van o man hinh them moi, cho nguoi dung thao tac nhu khi nhap them moi (Khong goi lai duoc addClicked vi mat COA ...)
          //Enable, disable fields disabled
          jQuery("#data-grid tr").removeAttr("onclick");
          jQuery("#data-grid tr").removeAttr("ondblclick");
          jQuery("#data-grid tr").removeAttr("onmouseout");
          jQuery("#data-grid tr").removeAttr("onmouseover");
          jQuery("#refresh").attr("disabled", true);
          jQuery("#ma_nhkb_nhan_cm").attr("disabled", false);
//          jQuery("#nt_id").attr("disabled", false); 
                    
          //Hien nut            
          jQuery("#save").show(); 
          assignValue2Field("save", "ghi");
          jQuery("#save").html("&nbsp;<span class=\"sortKey\">G<\/span>hi"); 
          jQuery("#search").hide();
          jQuery("#fsLyDoDayLai").hide();
          //Cho phep sua tat ca cac fields
          readOnlyFields(arrFieldId, false);   
          jQuery.each(arrFieldId, function(index, value){
              strId = value.toString();
              if (document.getElementById(strId) != null) {
                  setClass2Field(strId, "fieldText");
              }
          });          
          //Khong cho phep sua mot so fields
          readOnlyFields(arrFieldReadonly, true);
          jQuery.each(arrFieldReadonly, function(index, value){
              strId = value.toString();
              if (document.getElementById(strId) != null) {
                  setClass2Field(strId, "fieldTextCode");
              }
          });
          
          setClass2Field('ndung_tt', 'fieldTextArea');
          setClass2Field('ttin_kh_chuyen', 'fieldTextArea');
          setClass2Field('ttin_kh_nhan', 'fieldTextArea');
          
          jQuery("#so_yctt").select();
          jQuery("#so_yctt").focus();
        }
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
            var strNhan = jQuery("#nhan").val();
            firstCheck = true;
            
            if(('LTT.DI.THEM.LAI' == action) || ('LTT.DI.SUA.LAI' == action && strNhan == 'N')){
                //Van o man hinh them moi, cho nguoi dung thao tac nhu khi nhap them moi (Khong goi lai duoc addClicked vi mat COA ...)
                //Enable, disable fields disabled
                jQuery("#data-grid tr").removeAttr("onclick");
                jQuery("#data-grid tr").removeAttr("ondblclick");
                jQuery("#data-grid tr").removeAttr("onmouseout");
                jQuery("#data-grid tr").removeAttr("onmouseover");
                jQuery("#refresh").attr("disabled", true);
                jQuery("#ma_nhkb_nhan_cm").attr("disabled", false);
//                jQuery("#nt_id").attr("disabled", false); 
                          
                //Hien nut            
                jQuery("#save").show(); 
                assignValue2Field("save", "ghi");
                jQuery("#save").html("&nbsp;<span class=\"sortKey\">G<\/span>hi"); 
                jQuery("#search").hide();
                jQuery("#fsLyDoDayLai").hide();
                //Cho phep sua tat ca cac fields
                readOnlyFields(arrFieldId, false);   
                jQuery.each(arrFieldId, function(index, value){
                    strId = value.toString();
                    if (document.getElementById(strId) != null) {
                        setClass2Field(strId, "fieldText");
                    }
                });          
                //Khong cho phep sua mot so fields
                readOnlyFields(arrFieldReadonly, true);
                jQuery.each(arrFieldReadonly, function(index, value){
                    strId = value.toString();
                    if (document.getElementById(strId) != null) {
                        setClass2Field(strId, "fieldTextCode");
                    }
                });
                
                setClass2Field('ndung_tt', 'fieldTextArea');
                setClass2Field('ttin_kh_chuyen', 'fieldTextArea');
                setClass2Field('ttin_kh_nhan', 'fieldTextArea');              
            }else if('LTT.DI.SUA.LAI' == action && strNhan == 'Y'){
                jQuery("#ma_nhkb_nhan_cm").attr({"disabled":true});
                jQuery("#ngay_ct_btn").hide();
//                jQuery("#nt_id").attr({"disabled":true});
                jQuery("#tblThongTinChiTietCOA tr input").attr("readOnly", true);
//                alert();
                //focusEveryTKTN();
                
                jQuery("#ma_nhkb_nhan_cm").attr({'readOnly':true});
                jQuery("#tkkt_ma").focus();
            }
        }
      }
    });
  }
  function checkBeforeApprove(idValue){
    
    jQuery.ajax( {
        type : "POST", url : "checkBeforeUpdate.do", data :  {
             "id":idValue,"nd" : Math.random() * 100000
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                    }
                    else {
                        var exception_message = data.exception_message;
                        if (exception_message != null && exception_message != '' && exception_message != 'null') {
                            alert(GetUnicode(("Load thêm DTS không thành công! \n" + exception_message)));
                        }
                        var vParam=data.resultValue;
                        
                        if(vParam=='KHONG_DUOC_DUYET_CUNG_TAY'){
                          alert("Không ký duyệt được lệnh do chính mình nhập hoặc hoàn thiện ở vai trò TTV");
                          return false;
                        }else if(vParam=='PASSTIME'){
                          if(confirm('Đã sau giờ COT, Ngày thanh toán sẽ chuyển sang ngày kế tiếp. Bạn có chắc chắn duyệt lệnh không?')==false)
                              return false;
                            else {
                              approveLttDi(vParam);   
                            }
                        }else{
                              approveLttDi(vParam); 
                        }
                    }
                }

            }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
    
  }
  function approveLttDi(COT){
    var id= jQuery("#id_selected").val();
    var tong_sotien= jQuery("#tong_sotien").val();
    var nhan= jQuery("#nhan").val();
    var row_id_select_b4 = document.getElementById('row_select').value;    
    var strImg = "";
    var strTitle="";
    var certserial = jQuery("#certserial").val();
    var signature = jQuery("#signature").val();
    var noi_dung_ky = jQuery("#noi_dung_ky").val();
    var chuky_ktt = jQuery("#chuky_ktt").val();
    var chuky_gd = jQuery("#chuky_gd").val();
    var nt_id = jQuery("#nt_id").val();
    jQuery.ajax( {
        type : "POST", url : "lttApproved.do", data :  {
           "id" : id,
           "checkCOT":COT,
           "tong_sotien":tong_sotien,
           "chuky_ktt":chuky_ktt,
           "chuky_gd":chuky_gd,
           "certserial":certserial,
           "signature":signature,
           "noi_dung_ky":noi_dung_ky,
           "nhan":nhan,
           "nt_id":nt_id,
           "nd" : Math.random() * 100000
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
                              if(data.strThongBaoDHV =='<%=AppConstants.DIEU_HANH_VON%>'){
                                //Rao tam thoi
                                //alert(GetUnicode('L&#7879;nh thanh to&#225;n c&#7847;n c&#7845;p tr&#234;n ph&#234; duy&#7879;t &#273;&#7875; &#273;&#7843;m b&#7843;o t&#7891;n ng&#226;n.\nV&#224;o ch&#7913;c n&#259;ng "Tra c&#7913;u LTT c&#7847;n c&#7845;p tr&#234;n ph&#234; duy&#7879;t" &#273;&#7875; ti&#7871;p t&#7909;c theo d&#245;i.'));
                              }
                              hideBtnLTTDiDenForFind();
                              // xoa het style tren row cu va add style mac dinh
                              jQuery("#" + row_id_select_b4+" td:nth-child(1)").find('input').removeAttr('class');
                              jQuery("#" + row_id_select_b4+" td:nth-child(2)").removeAttr('class');
                              jQuery("#" + row_id_select_b4).attr( {
                                  'class' : 'ui-widget-content jqgrow ui-row-ltr'
                              });
                              // set anh va title cho anh mo ta trang thai
                              // set mo ta trang thai 
                                if(data.strTrangThai =='05'){
                                  strTitle ="Chờ GD duyệt";
                                   strImg = "/TTSP/styles/images/wait-gd.png";
                                }else if(data.strTrangThai =='08'){
                                  strTitle ="Đã gửi ngân hàng";
                                  strImg = "/TTSP/styles/images/sended-but.jpg";
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
                                var quyenLttDi = "<%=strQuyenLttDi%>"!=null?"<%=strQuyenLttDi%>":"";
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
                                defaultRowSelectedFirstRowLTT('loadLTTDiJsonAction.do', firstValue,firstRow, chucdanh,quyenLttDi);
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
                          alert(GetUnicode('&#272;&#227; c&#243; l&#7895;i x&#7843;y ra trong qu&#225; tr&#236;nh th&#7921;c hi&#7879;n duy&#7879;t b&#7843;n ghi '+ id));
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
  function ky(strUserType){
    	try {
            var cert = jQuery("#eSign")[0];
            //alert(cert);
            cert.InitCert();     
            
            // 20171120 thuongdt bo sung canh bao han su dung CTS
             var strdomain = '<%=strdomain%>';
            var struser_name = '<%=struser_name%>';
            var strcheckcts = '<%=strcheckcts%>';           
            if(!checkCTSdate(cert,strdomain+'/'+struser_name,strcheckcts)){
             return false;
            }
            
            var serial = cert.Serial;
            jQuery("#certserial").val(serial);
            var nd = jQuery("#noi_dung_ky").val();
            var sign = cert.Sign(nd);
            jQuery("#signature").val(sign);
//            alert(nd);
            if(strUserType != null){
              if(strUserType.indexOf("<%=AppConstants.NSD_KTT%>") != -1){
                jQuery("#chuky_ktt").val(sign);
              }else if(strUserType.indexOf("<%=AppConstants.NSD_GD%>") != -1){
                jQuery("#chuky_gd").val(sign);
              }
            }
            return true;
        }
        catch (e) {
            alert(e.description);
            return false;
        }
    }
    function checkInputDayLai(fieldId, strKTTGD){
      currentAction = '<%=AppConstants.ACTION_REJECT%>';
      if(fieldId == null) return false;
      var result = true;      
      var strFieldId = "#"+fieldId;
      if(jQuery(strFieldId).val() == null || jQuery(strFieldId).val() == ""){      
        alert("Bạn phải nhập Lý do "+strKTTGD+" đẩy lại!");
        jQuery(strFieldId).focus();
        return false;
      }else if(jQuery(strFieldId).val().length > 500) {
        alert("Lý do "+strKTTGD+" đẩy lại phải nhỏ hơn hoặc bằng 500 ký tư!");
        jQuery(strFieldId).focus();
        return false;
      }
      return result;
    }    
    function addClicked(){
        readOnlyFields(arrFieldId, false);
            
        jQuery("#data-grid tr").removeAttr("onclick");
        jQuery("#data-grid tr").removeAttr("ondblclick");
        jQuery("#data-grid tr").removeAttr("onmouseout");
        jQuery("#data-grid tr").removeAttr("onmouseover");
        jQuery("#refresh").attr("disabled", true);
        
        assignValue2Field("save", "ghi");      
        jQuery("#save").html("&nbsp;<span class=\"sortKey\">G<\/span>hi");//De dau cach truoc G phan biet voi update
//        jQuery("#add").hide();  
        jQuery("#completeLtt").hide();  
        jQuery("#cancel").hide();  
        jQuery("#save").show(); 
        jQuery("#search").hide();
        jQuery("#in").hide();
        jQuery("#fsLyDoDayLai").hide();
              
        jQuery("#ma_nhkb_nhan_cm").attr({"disabled":false});
        jQuery("#ngay_ct_btn").show();
//        jQuery("#nt_id").attr({"disabled":false});
        
        jQuery("#tblThongTinChiTietCOA tr input").attr("readOnly", false);
        assignValue2Field("ma_nhkb_chuyen_cm", "<%=session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION)%>");
        assignValue2Field("nhkb_chuyen", "<%=session.getAttribute(AppConstants.APP_NHKB_ID_SESSION)%>");
        assignValue2Field("ten_nhkb_chuyen_cm", "<%=session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION)%>");
        
        jQuery("#ma_nhkb_nhan_cm").val("<%=strMa_nhkb_nhan_cm%>");
        assignValue2Field("ten_nhkb_nhan_cm", "<%=strTen_nhkb_nhan_cm%>");      
        assignValue2Field("nhkb_nhan", "<%=strNhkb_nhan%>");
        
        assignValue2Field("ngay_tt", '<%=StringUtil.DateToString(new Date(),"dd/MM/yyyy")%>');
        assignValue2Field("ngay_hoan_thien", '<%=StringUtil.DateToString(new Date(),"dd/mm/yyyy  HH:MM:ss")%>');
        
        assignValue2Field("ma_nhkb_chuyen", "<%=session.getAttribute(AppConstants.APP_NHKB_CODE_SESSION)%>");
        assignValue2Field("ten_nhkb_chuyen", "<%=session.getAttribute(AppConstants.APP_NHKB_NAME_SESSION)%>");
        assignValue2Field("id_nhkb_chuyen", "<%=session.getAttribute(AppConstants.APP_NHKB_ID_SESSION)%>");
        
        assignValue2Field("ma_nhkb_nhan", "<%=strMa_nhkb_nhan_cm%>");
        assignValue2Field("ten_nhkb_nhan", "<%=strTen_nhkb_nhan_cm%>");
        assignValue2Field("id_nhkb_nhan", "<%=strNhkb_nhan%>");
        
        jQuery("#nt_id").val(2);
        jQuery("#ma_nhkb_nhan_cm").focus();
        jQuery("#ttv_nhan").val('<%=strUserId%>');      
        jQuery("#ma_ttv_nhan").val('<%=strMaNsd%>');      
        jQuery("#ten_ttv_nhan").val('<%=strTenNsd%>');
        
        checkAllowEditMoTaiNHKB = '<%=strCheckAllowEditMoTaiNHKB%>';
        if(checkAllowEditMoTaiNHKB == 'NO' || checkAllowEditMoTaiNHKB == '' || checkAllowEditMoTaiNHKB == 'undefined'){
          jQuery("#ma_nhkb_chuyen").attr({readOnly:true});
          setClass2Field("ma_nhkb_chuyen", "fieldTextCode");
          jQuery("#ten_nhkb_chuyen").attr({readOnly:true});
          setClass2Field("ma_nhkb_chuyen", "fieldTextTranparent");
        }
        
        var strId = "";
        jQuery.each(arrFieldId, function(index, value){
            strId = value.toString();
            if (document.getElementById(strId) != null) {
                setClass2Field(strId, "fieldText");
            }
        });
          
        readOnlyFields(arrFieldReadonly, true);      
        jQuery.each(arrFieldReadonly, function(index, value){
            strId = value.toString();
            if (document.getElementById(strId) != null) {
                setClass2Field(strId, "fieldTextCode");
            }
        });
        
        jQuery.each(arrTextAriaId, function(index, value){
            strId = value.toString();
            if (document.getElementById(strId) != null) {
                setClass2Field(strId, "fieldTextArea");
            }
        });
        
        var tabIndex=101;
        jQuery.each(arrFieldFocusTTChung, function (index, value) {
            strId = value.toString();
            if (document.getElementById(strId) != null) {
                if(document.getElementById(strId).readOnly!=true)
                {
                  document.getElementById(strId).tabIndex  = ++tabIndex;
                }
            }
        }); 
        var tabIndexTTNT=1001;
        
        jQuery.each(arrFieldFocusTTNT, function (index, value) {
            strId = value.toString();
            if (document.getElementById(strId) != null) {
                if(document.getElementById(strId).readOnly!=true)
                {
                  document.getElementById(strId).tabIndex  = tabIndexTTNT;
                }
            }
        }); 
//        jQuery.each(arrFieldIdLttCTiet, function (index, value) {
//            strId = value.toString();
//            if (document.getElementById(strId) != null) {
//                if(document.getElementById(strId).readOnly!=true)
//                {
//                  document.getElementById(strId).tabIndex  = tabContinue++;
//                  tabContinue=tabIndex;
//                }
//            }
//        }); 
        
        deleteRowsInCOA("tblThongTinChiTietCOA");
        resetLTTDiCOA();
        jQuery.each(arrFieldIdLttCTiet, function(index, value){
            strId = value.toString();
            if (document.getElementById(strId) != null) {
                setClass2Field(strId, "fieldTextCenter");
            }
        });
        setClass2Field("dien_giai", "fieldText");
        setClass2Field("so_tien", "fieldTextRight");
        jQuery("#ma_quy").val('01');
        var ma_nhkb = '<%=strNHKB_COA%>';
        jQuery("#kb_ma").val(ma_nhkb);
    }
    // In
    function formAction(){ 
      document.getElementsByName("in_gioi_thieu")[0].value = "";
      var f = document.forms[0];
      f.action = "lttDiRptAction.do";
//      if(asyncCommand != null)
//      {
//         asyncCommand.Cancel();
//      }
       var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
      newDialog = window.open('', '_form', params);          
      f.target='_form';
      f.submit();
    }     
    function setAttrAfterErrorCheckInput(nhan){
        var strId = "";
        //Tu tabmis den
        if(nhan == 'Y'){        
            //Khoa cac truong: NHKB nhan, ngay_ct, nt_id
            jQuery("#ma_nhkb_nhan_cm").attr({"disabled":true});
            jQuery("#ngay_ct_btn").hide();
            jQuery("#nt_id").attr({"disabled":true});
            
            jQuery("#ma_nhkb_nhan_cm").attr({'readOnly':true});
            var arrTTinNChuyen = new Array("ten_tk_chuyen", "so_tk_chuyen", "ma_nhkb_chuyen", "ttin_kh_chuyen");
            if(jQuery("#ma_nhkb_chuyen_cm").val() != jQuery("#ma_nhkb_chuyen").val()){
              readOnlyFields(arrTTinNChuyen, false);
              jQuery.each(arrTTinNChuyen, function(index, value){
                strId = value.toString();
                if (document.getElementById(strId) != null) {
                    setClass2Field(strId, "fieldText");
                }
              });
              setClass2Field('ttin_kh_chuyen', 'fieldTextArea');
              jQuery("#ten_tk_chuyen").focus();
            }else{
              jQuery("#so_tk_chuyen").attr({'readOnly':true});
              //jQuery("#tkkt_ma").focus();
              jQuery("#ten_nhkb_chuyen").trigger('focus');
            }                    
        }else if(nhan == 'N'){
            //Mo khoa cac truong: NHKB nhan, ngay_ct, nt_id
            jQuery("#ma_nhkb_nhan_cm").attr({"disabled":false});
            jQuery("#ngay_ct_btn").show();
//            jQuery("#nt_id").attr({"disabled":false});
            
            readOnlyFields(arrFieldId, false);  
            jQuery("#ma_nhkb_nhan_cm").focus();
            
            jQuery.each(arrFieldId, function(index, value){
                strId = value.toString();
                if (document.getElementById(strId) != null) {
                    setClass2Field(strId, "fieldText");
                }
            });
                    
            readOnlyFields(arrFieldReadonly, true);      
            jQuery.each(arrFieldReadonly, function(index, value){
                strId = value.toString();
                if (document.getElementById(strId) != null) {
                    setClass2Field(strId, "fieldTextCode");
                }
            });
            setClass2Field('ndung_tt', 'fieldTextArea');
            setClass2Field('ttin_kh_chuyen', 'fieldTextArea');
            setClass2Field('ttin_kh_nhan', 'fieldTextArea');
        }
    }    
    document.onkeydown = keyDownLTT; 
//    document.onfocus=keyDownLTT;
    //document.onkeydown = showDown;  
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
    function initDialogLov(){
    jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px", 
      modal: true
    });
  }
  //HungBM 20170414 - Function goi ham in giay gioi thieu - BEGIN
  function inGioiThieu(){
    document.getElementsByName("in_gioi_thieu")[0].value = "inGioiThieu";
    var f = document.forms[0];
      f.action = "lttDiRptAction.do";
       var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
      newDialog = window.open('', '_form', params);          
      f.target='_form';
      f.submit(); 
  } 
 changeMaNTThongKeTongJS(null);
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>