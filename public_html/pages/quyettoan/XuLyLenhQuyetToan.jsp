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
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/quyettoan.js"
        type="text/javascript">
</script>
<fmt:setBundle basename="com.seatech.ttsp.resource.XuLyLenhQuyetToanResource"/>
<object id="eSign" name="eSign" height="0" width="0" classid="CLSID:7525E7C6-84C6-4180-AFA3-A5FED8C8A261" VIEWASTEXT codebase='VSTeTokenSetup.cab'></object>
    <html:form styleId="frmQT" action="/XuLyLenhQuyetToanList.do">
    <html:hidden property="chu_ky" styleId="chu_ky"/>
    <html:hidden property="noi_dung_ky" styleId="noi_dung_ky"/>
    <html:hidden property="certSerial" styleId="certSerial"/>
    <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
          <fmt:message key="XuLyLenhQT.page.title"/></span></td>
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
            </tbody>
          </table>
          <table cellspacing="0" cellpadding="0" width="100%">
            <tbody>
              <tr>
                <td valign="top">
                  <table class="bordertableChungTu" cellspacing="0" cellpadding="0"
                         width="100%">
                      <tbody>
                        <tr>
                          <td width="15%" valign="top">
                            <div class="clearfix">
                              <fieldset  style="height:210px;">
                                <legend style="vertical-align:middle">
                                  <fmt:message key="XuLyLenhQT.page.solenhquyettoan"/>
                                </legend>
                                <div class="sodientrasoattable">
                                  <div>
                                    <table class="data-grid" cellspacing="0" cellpadding="0"
                                           width="98%">
                                      <thead>
                                        <tr>
                                          <th class="ui-state-default ui-th-column"
                                               width="62%;">
                                            <fmt:message key="XuLyLenhQT.page.solenhquyettoan"/>
                                          </th>
                                          <th height="20" width="28%"
                                              class="ui-state-default ui-th-column">
                                            <fmt:message key="XuLyLenhQT.page.TinhTrang"/>
                                          </th>
                                          <th height="20" width="10%;"
                                              class="ui-state-default ui-th-column">
                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                          </th>
                                        </tr>
                                      </thead>
                                    </table>
                                  </div>                   
                                  <div style="height:120px;width:97%" class="ui-jqgrid-bdiv ui-widget-content">
                                    <div>
                                      <table  class="data-grid" id="data-grid"
                                              style="border:0px;width:100%"
                                             cellspacing="0" cellpadding="0"                                  
                                             width="100%">
                                        <tbody>
                                          <logic:present name="lstDanhSachQuyetToan">
                                            <div>
                                              <logic:notEmpty name="lstDanhSachQuyetToan">
                                                <logic:iterate id="items" name="lstDanhSachQuyetToan"
                                                               indexId="index">
                                                    <tr class="ui-widget-content jqgrow ui-row-ltr" 
                                                        id="row_qt_<bean:write name="index"/>"
                                                        ondblclick="rowSelectedFocusQT('row_qt_<bean:write name="index"/>');"
                                                        onclick="rowSelectedFocusQT('row_qt_<bean:write name="index"/>');
                                                                 fillDataQuyetToan('<bean:write name="items" property="id"/>','row_qt_<bean:write name="index"/>');"
                                                        >
                                                        <td width="40%;"  align="center" id="td_qt_<bean:write name="index"/>">
                                                          <input type="hidden" id="col_qt_<bean:write name="index"/>" 
                                                                  value="<bean:write name="items" property="id"/>" 
                                                          /> 
                                                          <input name="row_item" id="col_qt" 
                                                          type="text" style="border:0px;font-size:10px;" onkeydown="arrowUpDownQT(event)"
                                                          value="<bean:write name="items" property="id_ref"/>" 
                                                          readonly="true"/> 
                                                        </td>
                                                        <td width="28%;" align="center">
                                                         <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="00">
                                                                        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/edit.gif" border="0" title="Chờ TTV hoàn thiện"/>                                         
                                                          </logic:equal>
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="01">
                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/wait.jpeg" border="0" title="Chờ KTT duyệt"/>
                                                          </logic:equal>                                           
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="02">
                                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/return.jpeg"
                                                                 border="0"
                                                                 title="KTT đẩy lại"/> 
                                                           </logic:equal>  
                                                           <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="04">
                                                                <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/active.gif"
                                                                 border="0"
                                                                 title="Đã tạo bảng kê"/> 
                                                          </logic:equal>                                           
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="03">
                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-but.jpg"
                                                                 border="0" title="Đã duyệt"/>
                                                          </logic:equal>
                                                         <!-- new
                                                        -->
                                                           <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="11">
                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-but.jpg"
                                                                 border="0" title="Chờ chạy giao diện"/>
                                                          </logic:equal>
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="12">
                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/send-success.jpg"
                                                                 border="0" title="Giao diện thành công"/>
                                                          </logic:equal>
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="13">
                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg"
                                                                 border="0" title="Giao diện không thành công"/>
                                                          </logic:equal>
                                                          <logic:equal name="items"
                                                                       property="trang_thai"
                                                                       value="16">
                                                            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/sended-false.jpg"
                                                                 border="0" title="Lỗi truyền tin"/>
                                                          </logic:equal>
                                                        </td>
                                                      </tr>
                                                </logic:iterate>
                                              </logic:notEmpty>
                                              <logic:empty name="lstDanhSachQuyetToan">
                                                <tr>
                                                  <td colspan="5" align="center">
                                                    <span style="color:red;">
                                                      <fmt:message key="XuLyLenhQT.page.solenhquyettoan.empty"/>
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
                                              <span id="refreshSpn" class="ui-icon ui-icon-refresh"
                                                    onclick="refreshGridQT();"></span>
                                            </div>
                                          </td>
                                        </tr>
                                      </thead>
                                    </table>
                                    <div style="padding-top:10px;float: left;">
                                      <html:hidden property="so_bk" styleId="so_bk"/>
                                      <fmt:message key="XuLyLenhQT.page.label.trang_thai"/>: 
                                      <html:text property="mo_ta_trang_thai"
                                                  readonly="true"
                                                  onkeydown="nextElementFocusQT(event);"
                                                   style="WIDTH: 145px;border:0px;font-weight:bold;" 
                                                  styleId="mo_ta_trang_thai"/>
                                      <!--<span id="mo_ta_trang_thai" style="font-weight:bold;"></span>-->
                                    </div>
                                  </div>
                                </div>
                              </fieldset>
                            </div>
                          </td>
                          <td width="85%" valign="top">
                            <fieldset  style="height:210px;">
                              <legend style="vertical-align:middle">
                                <fmt:message key="XuLyLenhQT.page.thongtinchung"/>
                              </legend>
                              <div style="padding:5px 5px 5px 5px;">
                              <input type="hidden" id="rowSelected"/>
                                <table style="BORDER-COLLAPSE: collapse;" border="1"
                                       cellspacing="0" bordercolor="#b0c4de"
                                       cellpadding="0" width="100%">
                                  <tbody>
                                    <tr style="width:100%">
                                      <td width="50%" valign="middle">
                                       
                                        <div  style="float:left;width:30%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.nhkbchuyen"/>
                                        </div>
                                        <div style="float:left;width:20%;" align="left">
                                          <html:text property="nhkb_chuyen"
                                           styleId="nhkb_chuyen"
                                           styleClass="fieldTextCode" tabindex="101"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;width:100%"
                                           />
                                        </div>
                                        <div style="float:right;width:48%;" align="left">
                                          <html:text property="ten_don_vi_chuyen"
                                               styleId="ten_don_vi_chuyen" 
                                               readonly="readonly"
                                               styleClass="fieldTextTrans"
                                               onmouseout="UnTip()"
                                               onmouseover="Tip(this.value)"                                                
                                               style="border:0px;font-weight:bold;"/>
                                        </div>                                       
                                      </td>
                                      <td width="50%" valign="middle">
                                        <div style="float:left;width:35%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.nhkbnhan"/>
                                        </div>
                                        <div style="float:left;width:20%;" align="left">
                                          <html:text property="nhkb_nhan"
                                           styleId="nhkb_nhan"
                                           styleClass="fieldTextCode" tabindex="102"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;width:100%"
                                           />
                                        </div>
                                        <div style="float:right;width:44%;" align="left">
                                          <html:text property="ten_don_vi_nhan"
                                               styleId="ten_don_vi_nhan" 
                                               readonly="readonly"
                                               styleClass="fieldTextTrans"
                                               onmouseout="UnTip()"
                                               onmouseover="Tip(this.value)"                                                
                                               style="border:0px;font-weight:bold;"/>
                                        </div>
                                      </td>
                                    </tr>
                                    <tr style="width:100%">
                                      <td width="50%" valign="middle">
                                       
                                          <div  style="float:left;width:30%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.ngayhachtoan"/>
                                        </div>
                                        <div style="float:left;width:20%;" align="left">
                                          <html:text property="ngay_htoan"
                                           styleId="ngay_htoan"
                                           styleClass="fieldTextCode" tabindex="103"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;"
                                           />
                                        </div>                                                                        
                                      </td>
                                      <td width="50%" valign="middle">
                                        <div style="float:left;width:35%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.ngayquyettoan"/>
                                        </div>
                                        <div style="float:left;width:20%;" align="left">
                                          <html:text property="ngay_qtoan"
                                           styleId="ngay_qtoan"
                                           styleClass="fieldTextCode" tabindex="104"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;"
                                           />
                                        </div>
                                      </td>
                                    </tr>
                                    <tr style="width:100%" valign="middle">
                                      <td width="50%">
                                          <div  style="float:left;width:30%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.loaiquyettoan"/>
                                        </div>
                                        <div style="float:left;width:33%;" align="left">
                                        <html:text property="loai_qtoan"
                                           styleId="loai_qtoan"
                                           styleClass="fieldTextCode" tabindex="104"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;width:20%" 
                                           >
                                           </html:text> 
                                           <html:text property="lai_phi"
                                           styleId="lai_phi" 
                                           styleClass="fieldTextCode" tabindex="115"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;width:70%"
                                           />
                                        </div>
                                      </td>
                                      <td width="50%" valign="middle">
                                        <div style="float:left;width:35%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.sothamchieulienquan"/>
                                        </div>
                                        <div style="float:left;width:33%;" align="left">
                                          <html:text property="so_tchieu"
                                           styleId="so_tchieu"
                                           styleClass="fieldTextCode" tabindex="106"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;"
                                           />
                                        </div>
                                      </td>
                                    </tr>
                                    <tr style="width:100%" valign="middle">
                                      <td width="50%">
                                          <div  style="float:left;width:30%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.solenhquyettoan"/>
                                        </div>
                                        <div style="float:left;width:33%;" align="left">
                                          <html:text property="id_ref"
                                           styleId="id_ref"
                                           styleClass="fieldTextCode" tabindex="107"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;"
                                           />
                                           <html:hidden property="id" styleId="id" />
                                        </div>                                                                      
                                      </td>
                                      <td width="50%" valign="middle">
                                        <div style="float:left;width:35%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.sothamchieugiaodich"/>
                                        </div>
                                        <div style="float:left;width:33%;" align="left">
                                          <html:text property="ma_tchieu_gd"
                                           styleId="ma_tchieu_gd"
                                           styleClass="fieldTextCode" tabindex="108"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;"
                                           />
                                        </div>                                        
                                      </td>
                                    </tr>
                                    <tr style="width:100%" valign="middle">
                                      <td width="50%">                                       
                                          <div  style="float:left;width:30%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.nguoinhap"/>
                                        </div>
                                        <div style="float:left;width:33%;" align="left">
                                          <html:text property="nguoi_nhap_nh"
                                           styleId="nguoi_nhap_nh"
                                           styleClass="fieldTextCode" tabindex="109"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;"
                                           />
                                        </div>
                                      </td>
                                      <td width="50%" valign="middle">
                                        <div style="float:left;width:35%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.ngaynhap"/>
                                        </div>
                                        <div style="float:left;width:33%;" align="left">
                                          <html:text property="ngay_nhap_nh"
                                           styleId="ngay_nhap_nh"
                                           styleClass="fieldTextCode" tabindex="110"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;"
                                           />
                                        </div>
                                      </td>
                                    </tr>
                                    <tr style="width:100%" valign="middle">
                                      <td width="50%">
                                          <div  style="float:left;width:30%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.nguoikiemsoat"/>
                                        </div>
                                        <div style="float:left;width:33%;" align="left">
                                          <html:text property="nguoi_ks_nh"
                                           styleId="nguoi_ks_nh"
                                           styleClass="fieldTextCode" tabindex="111"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;"
                                           />
                                        </div>
                                      </td>
                                      <td width="50%" valign="middle">
                                        <div style="float:left;width:35%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.ngaykiemsoat"/>
                                        </div>
                                        <div style="float:left;width:33%;" align="left">
                                          <html:text property="ngay_ks_nh"
                                           styleId="ngay_ks_nh"
                                           styleClass="fieldTextCode" tabindex="112"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;"
                                           />
                                        </div>
                                      </td>
                                    </tr>
                                    <tr style="width:100%" valign="middle">
                                      <td width="100%" colspan="2">                                       
                                          <div  style="float:left;width:15%;"  align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.noidungthanhtoan"/>
                                        </div>
                                        <div style="float:left;width:69%;" align="left">
                                          <html:text property="ndung_tt"
                                           styleId="ndung_tt"
                                           styleClass="fieldTextCode" tabindex="113"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;width:100%"
                                           />
                                        </div>
                                      </td>
                                    </tr>
                                    <tr style="width:100%" valign="middle">
                                      <td width="50%">                                       
                                          <div  style="float:left;width:30%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.sotien"/>
                                        </div>
                                        <div style="float:left;width:33%;" align="left">
                                          <%--<html:text property="so_tien"
                                           styleId="so_tien" 
                                           styleClass="fieldTextRightCode" tabindex="114"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;"
                                           />--%>
                                           <input type="text" class="fieldTextRightCode" tabindex="114" 
                                                  onkeydown="nextElementFocusQT(event);"
                                                  style="height:100%;"
                                                  id="so_tien" value="<bean:write name="QuyetToanForm" property="so_tien" format="#,000"/>">
                                            </input>
                                        </div>                                                                       
                                      </td>
                                      <td width="50%" valign="middle">
                                        <div style="float:left;width:35%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.loaitien"/>
                                        </div>
                                        <div style="float:left;width:33%;" align="left">
                                          <html:text property="ma_nt"
                                           styleId="ma_nt"
                                           styleClass="fieldTextCode" tabindex="115"
                                           onkeydown="nextElementFocusQT(event);"
                                           style="height:100%;"
                                           />
                                        </div>                                        
                                      </td>
                                    </tr>
                                    <tr id="tr_ttin_lenh_goc">
                                      <td colspan="2" align="center">
                                        <table width="98%" border="0">
                                          <tr>
                                            <td width="14%">
                                              Thông tin lệnh gốc
                                            </td>
                                            <td>
                                              <html:textarea property="ttin_lenh_goc" 
                                                       styleId="ttin_lenh_goc" cols="3"                                             
                                                       rows="2"
                                                       styleClass="fieldTextArea"
                                                       style="WRAP:HARD" ></html:textarea>
                                            </td>
                                          </tr>
                                        </table>
                                      </td>
                                    </tr>
                                    
                                    <!--<tr style="width:100%">
                                      <td width="50%" valign="middle">                                       
                                        <div  style="float:left;width:30%;" align="right">
                                          <fmt:message key="XuLyLenhQT.page.thongtinchung.laiphi"/>
                                        </div>
                                        <div style="float:left;width:33%;" align="left">
                                          
                                          
                                        </div>                                                                       
                                      </td>
                                      <td width="50%">
                                                                    
                                      </td>
                                    </tr>-->
                                  </tbody>
                                </table>
                              </div>
                            </fieldset>
                          </td>
                        </tr>                        
                      </tbody>
                  </table>
                </td>
              </tr>
              <tr>
                <td  height="100px;" width="100%" >
                   <table width="100%"  cellpadding="0" cellspacing="0" align="left">
                              <tr>
                                <td width="48%" align="left" valign="top">
                                  <fieldset style="height:100px;">
                                    <legend style="vertical-align:middle">
                                      <fmt:message key="XuLyLenhQT.page.thongtinphatlenh"/>
                                    </legend>
                                      <table align="center" style="margin-top:5px;" cellpadding="5px;" cellspacing="0;" border="0" width="100%">
                                        <tr>
                                          <td width="20%" align="right">
                                            <fmt:message key="XuLyLenhQT.page.thongtinphatlenh.tentaikhoan"/>
                                          </td>
                                          <td align="left">
                                            <html:text property="ten_kh_chuyen"
                                                     styleId="ten_kh_chuyen"
                                                     styleClass="fieldTextCode" tabindex="102"
                                                     onkeydown="nextElementFocusQT(event);"
                                                     style="height:100%;width:100%"/>
                                          </td>
                                        </tr>
                                        <tr>
                                          <td align="right">
                                            <fmt:message key="XuLyLenhQT.page.thongtinphatlenh.taikhoan"/>
                                          </td>
                                          <td align="left">
                                            <html:text property="tk_chuyen"
                                                     styleId="tk_chuyen"
                                                     styleClass="fieldTextCode" tabindex="102"
                                                     onkeydown="nextElementFocusQT(event);"
                                                     style="height:100%;width:40%"/>
                                          </td>
                                        </tr>
                                        <tr>
                                          <td align="right">
                                            <fmt:message key="XuLyLenhQT.page.thongtinphatlenh.tainh"/>
                                          </td>
                                          <td align="left">
                                            <html:text property="ma_nh_chuyen"
                                                       styleId="ma_nh_chuyen"
                                                       styleClass="fieldTextCode" tabindex="102"
                                                       onkeydown="nextElementFocusQT(event);"
                                                       style="height:100%;width:20%"/>
                                            <html:text property="ten_nh_chuyen"
                                                       styleId="ten_nh_chuyen"
                                                       styleClass="fieldTextTrans" tabindex="102"
                                                       onkeydown="nextElementFocusQT(event);"
                                                       style="border-bottom: 0px; border-left: 0px; border-top: 0px; font-weight: bold; border-right: 0px;height:100%;width:75%"/>
                                          </td>
                                        </tr>
                                      </table>                                      
                                  </fieldset>
                                </td>
                                <td width="48%"  height="100px;" valign="top">
                                      <fieldset style="height:100px;">
                                        <legend style="vertical-align:middle">
                                          <fmt:message key="XuLyLenhQT.page.thongtinnhanlenh"/>
                                        </legend>
                                        <table align="center" style="margin-top:5px;" cellpadding="5px;" cellspacing="0;" border="0" width="100%">
                                        <tr>
                                          <td width="20%" align="right">
                                            <fmt:message key="XuLyLenhQT.page.thongtinnhanlenh.tentaikhoan"/>
                                          </td>
                                          <td align="left">
                                            <html:text property="ten_kh_nhan"
                                                     styleId="ten_kh_nhan"
                                                     styleClass="fieldTextCode" tabindex="102"
                                                     onkeydown="nextElementFocusQT(event);"
                                                     style="height:100%;width:100%"/>
                                          </td>
                                        </tr>
                                        <tr>
                                          <td align="right">
                                            <fmt:message key="XuLyLenhQT.page.thongtinnhanlenh.taikhoan"/>
                                          </td>
                                          <td align="left">
                                            <html:text property="tk_nhan"
                                                     styleId="tk_nhan"
                                                     styleClass="fieldTextCode" tabindex="102"
                                                     onkeydown="nextElementFocusQT(event);"
                                                     style="height:100%;width:40%"/>
                                          </td>
                                        </tr>
                                        <tr>
                                          <td align="right">
                                            <fmt:message key="XuLyLenhQT.page.thongtinnhanlenh.tainh"/>
                                          </td>
                                          <td align="left">
                                            <html:text property="ma_nh_nhan"
                                                       styleId="ma_nh_nhan"
                                                       styleClass="fieldTextCode" tabindex="102"
                                                       onkeydown="nextElementFocusQT(event);"
                                                       style="height:100%;width:20%"/>
                                            <html:text property="ten_nh_nhan"
                                                       styleId="ten_nh_nhan"
                                                       styleClass="fieldTextTrans" tabindex="102"
                                                       onkeydown="nextElementFocusQT(event);"
                                                       style="border-bottom: 0px; border-left: 0px; border-top: 0px; font-weight: bold; border-right: 0px;height:100%;width:75%"/>
                                          </td>
                                        </tr>
                                      </table>
                                      </fieldset>
                                </td>
                              </tr>
                    </table>
                </td>
              </tr>
              <tr>
                <td  height="100px;" width="100%" >
                        <fieldset style="height:120px;">
                          <legend style="vertical-align:middle">
                            <fmt:message key="XuLyLenhQT.page.thongtinhoanthien"/>
                          </legend>
                          <div style="margin-top:2px;padding-top:2px;float:left;width:100%">
                            <fmt:message key="XuLyLenhQT.page.thongtinhoanthien.ttvhoanthien"/>
                            <html:text property="ma_ttv_hoanthien"
                                                       styleId="ma_ttv_hoanthien"
                                                       styleClass="fieldTextCode" tabindex="102"
                                                       onkeydown="nextElementFocusQT(event);"
                                                       style="height:100%;width:24%"/>
                            <fmt:message key="XuLyLenhQT.page.thongtinhoanthien.ngayhoanthien"/>
                            <html:text property="ngay_chuyen_ks"
                                                       styleId="ngay_chuyen_ks"
                                                       styleClass="fieldTextCode" tabindex="102"
                                                       onkeydown="nextElementFocusQT(event);"
                                                       style="height:100%;width:13%"/>
                            <fmt:message key="XuLyLenhQT.page.thongtinhoanthien.ktt"/>
                            <html:text property="ma_ktt_hoanthien"
                                                       styleId="ma_ktt_hoanthien"
                                                       styleClass="fieldTextCode" tabindex="102"
                                                       onkeydown="nextElementFocusQT(event);"
                                                       style="height:100%;width:24%"/>
                            <fmt:message key="XuLyLenhQT.page.thongtinhoanthien.ngaykiemsoat"/>
                            <html:text property="ngay_ks"
                                                       styleId="ngay_ks"
                                                       styleClass="fieldTextCode" tabindex="102"
                                                       onkeydown="nextElementFocusQT(event);"
                                                       style="height:100%;width:13%"/>
                          </div>
                            <table width="99%" style="margin-top:30px;" border="0">
                              <tr>
                                <td width="10%">
                                  <fmt:message key="XuLyLenhQT.page.thongtinhoanthien.loaihachtoan"/>
                                </td>
                                <td  colspan="3">
                                  <html:select styleClass="selectBox" property="loai_hach_toan"
                                     styleId="loai_hach_toan" style="width:18%;" onchange="removeValue(this);"
                                     onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                                     onfocus="textfocus(this);">
                                        <option value='T'/>
                                          <fmt:message key="XuLyLenhQT.page.thongtinhoanthien.loai_hach_toan.dung"/>
                                        <option value='P'/>
                                          <fmt:message key="XuLyLenhQT.page.thongtinhoanthien.loai_hach_toan.choxuly"/>                      
                                 </html:select>
                                </td>
                              </tr>
                              <tr>
                                <td width="10%">
                                  <fmt:message key="XuLyLenhQT.page.thongtinhoanthien.lydohachtoan"/>
                                </td>
                                <td width="35%">
                                  <html:textarea property="ldo_hach_toan" 
                                               styleId="ldo_hach_toan" cols="3"    
                                               styleClass="fieldTextArea"
                                               rows="3" style="WRAP:HARD" 
                                               onkeydown="nextElementFocusQT(event);arrowUpDownQT(event)"
                                               ></html:textarea>
                                </td>
                                <td width="10%" align="right">
                                  <fmt:message key="XuLyLenhQT.page.thongtinhoanthien.lydokttdaylai"/>
                                </td>
                                <td width="35%">
                                  <html:textarea property="ldo_day_lai" 
                                             styleId="ldo_day_lai" cols="3"                                             
                                             rows="3" 
                                             onkeydown="nextElementFocusQT(event);arrowUpDownQT(event)"
                                             styleClass="fieldTextArea"
                                             style="WRAP:HARD" ></html:textarea>
                                </td>
                              </tr>
                            </table>
                        </fieldset>
                </td>
              </tr>
              <tr>
                <td  height="100px;" width="100%" >
                    <table class="buttonbar" border="0" cellspacing="0" cellpadding="0" width="100%">
                      <tr align="right">
                        <td> 
                        <span id="duyet"> 
                            <button 
                                    id="btnDuyet" class="ButtonCommon"
                                    type="button" accesskey="O">
                              <fmt:message key="XuLyLenhQT.page.button.duyet"/>
                            </button>
                          </span>
                          <span id="daylai"> 
                            <button 
                                    id="btnDaylai" class="ButtonCommon"
                                    type="button" accesskey="O">
                              <fmt:message key="XuLyLenhQT.page.button.daylai"/>
                            </button>
                          </span>
                          <span id="in"> 
                            <button onclick="formAction()"
                                    id="btnIn" class="ButtonCommon"
                                    type="button" accesskey="O">
                              <fmt:message key="XuLyLenhQT.page.button.in"/>
                            </button>
                          </span>
                          <span id="chuyen"> 
                            <button 
                                    id="btnChuyen" class="ButtonCommon"
                                    type="button" accesskey="O">
                              <fmt:message key="XuLyLenhQT.page.button.chuyen"/>
                            </button>
                          </span>
                          <span id="thoat"> 
                            <button 
                                    id="btnExit" class="ButtonCommon"
                                    type="button" accesskey="O">
                              <fmt:message key="XuLyLenhQT.page.button.thoat"/>
                            </button>
                          </span>
                        </td>
                      </tr>
                    </table>
                </td>
              </tr>
            </tbody>
          </table>
        </td>
      </tr>
    </table>   
  
    </html:form>
<div id="dialog-message-check"
     title='<fmt:message key="XuLyLenhQT.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<div id="dialog-message"
     title='<fmt:message key="XuLyLenhQT.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<div id="dialog-confirm"
     title='<fmt:message key="XuLyLenhQT.page.title.dialog_confirm"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>
<script type="text/javascript">
  jQuery.noConflict();
  var strActionKTT=null;
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    defaultButton();
    var checkExit = '<%=request.getAttribute("typeView")%>' == 'null' ? false : '<%=request.getAttribute("typeView")%>';
    if(checkExit=='true'){
      jQuery("#refreshSpn").removeAttr("onclick");
      defaultStateForm();
      jQuery("#chuyen").hide();
      jQuery("#daylai").hide();
      jQuery("#duyet").hide();
      jQuery("#duyet").hide();
      jQuery("#in").show();
      strActionKTT = "PrintSGD";
      //20150320-sua dap ung mua ban ngoai te      
      if(jQuery("#lai_phi").val()=='02'){
        if(jQuery("#loai_qtoan").val() == 'D'){
          jQuery("#lai_phi").val("Phí");
        }else{
          jQuery("#lai_phi").val("Lãi")
        }
      }else if(jQuery("#lai_phi").val()=='04'){
        if(jQuery("#loai_qtoan").val() == 'D'){
          jQuery("#lai_phi").val("Báo nợ chênh lệch tỷ giá");
        }else{
          jQuery("#lai_phi").val("Báo có chênh lệch tỷ giá")
        }
      }else if(jQuery("#lai_phi").val()=='06'){
          jQuery("#lai_phi").val("Báo nợ thu phi POS");
      }else if(jQuery("#lai_phi").val()=='05'){
          jQuery("#lai_phi").val("Báo có thu POS");
      }else{
        jQuery("#lai_phi").val("Quyết toán")
      }
      //*************************************
      jQuery("#loai_qtoan").val() == 'D' ? jQuery("#loai_qtoan").val("Thu"):jQuery("#loai_qtoan").val("Chi");
      jQuery("#so_tien").val(CurrencyFormatted(jQuery("#so_tien").val(),jQuery("#ma_nt").val()));
      jQuery("#data-grid tr").attr("readonly",true);
      jQuery("#loai_hach_toan").attr("readonly",true);
      jQuery("#data-grid tr").removeAttr("onclick");
    }else if(checkExit=="VIEW_DETAIL"){
      defaultStateForm();
      defaultRowSelectedQT();
      changeStateArea();
      jQuery("#btnChuyen").hide();
      jQuery("#btnDuyet").hide();
      jQuery("#btnDaylai").hide();
      jQuery("#refreshSpn").removeAttr("onclick");
      jQuery("#data-grid tr").attr("readonly",true);
      jQuery("#loai_hach_toan").attr("readonly",true);
      jQuery("#data-grid tr").removeAttr("onclick");
    }else{
      defaultStateForm();
      defaultRowSelectedQT();
      changeStateArea();
    }
   
    //dialog message
  jQuery("#dialog-message-check").dialog( {
    autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
          "Có" : function () {    
              // thuc hien update trang thai
                updateExcuteQT(strActionKTT);
                jQuery("#dialog-message-check").dialog("close");
          },
          "Không" : function () {    
              // thuc hien update trang thai
              jQuery("#dialog-message-check").dialog("close");
          }
      }
  });
  jQuery("#dialog-confirm").dialog( {
    autoOpen : false, modal : true, height : 200, width : 430, buttons :  {
          "Có" : function () {    
              // thuc hien update trang thai
              var action='<%=request.getParameter("action")%>';
              if(action!='null' && action=='<%=AppConstants.ACTION_VIEW_DETAIL%>'){
                var hsc_nh = '<%=request.getParameter("hsc_nh")%>'
                var kb_tinh = '<%=request.getParameter("kb_tinh")%>'
                var tu_ngay = '<%=request.getParameter("tu_ngay_qtoan")%>' 
                var den_ngay = '<%=request.getParameter("tu_ngay_qtoan")%>'
                var ngay_tt ='<%=request.getParameter("ngay_tt")%>'
                var loai_qt = '<%=request.getParameter("loai_qt")%>'
                var urlRequest="TraCuuQToanList.do?";
              if(kb_tinh != 'null' && kb_tinh != ''){
                urlRequest += "kb_tinh="+kb_tinh+"";
              }
              if(hsc_nh !='null' && hsc_nh != ''){
                urlRequest += "&hsc_nh="+hsc_nh+"";
              }
             
               if(tu_ngay != 'null' && tu_ngay != ''){
                urlRequest += "&tu_ngay="+tu_ngay_qtoan+"";
              }
              if(den_ngay != 'null' && den_ngay != ''){
                urlRequest += "&den_ngay="+den_ngay_qtoan+"";
              }
               if(ngay_tt != 'null' && ngay_tt != ''){
                urlRequest += "&ngay_tt="+ngay_tt+"";
              }
              if(loai_qt != 'null' && loai_qt != ''){
                urlRequest += "&loai_qt="+loai_qt+"";
              }
              window.location = urlRequest;
          }else{
              document.forms[0].target = '';
              document.forms[0].action = "thoatView.do";
              document.forms[0].submit();
          }
              jQuery("#dialog-confirm").dialog("close");
          },
          "Không" : function () {    
              // thuc hien update trang thai
              jQuery("#dialog-confirm").dialog("close");
          }
      }
  });
  jQuery("#dialog-message").dialog( {
          autoOpen : false,resizable : false, modal : true, height : 200, width : 430, buttons :  {
              "Ok" : function () {
//                  refreshGridDTSTD();
                  refreshGridQT();
                  jQuery("#dialog-message").dialog("close");
              }
          }
      });
     
  jQuery("#btnDaylai").click(function () {
    document.forms[0].target='';
    if(jQuery("#ldo_day_lai").val() == null || ''== jQuery("#ldo_day_lai").val().trim()){
            alert('Phải nhập lý do đẩy lại!');
            jQuery("#ldo_day_lai").focus();
            return false;
    }
    strActionKTT="ACTION_RETURN";
    jQuery("#dialog-message-check").html('<fmt:message key="XuLyLenhQT.page.message.confirm_daylai"/>');
    jQuery("#dialog-message-check").dialog("open");
  });
  jQuery("#btnDuyet").click(function () {
      document.forms[0].target='';
      strActionKTT="ACTION_PASS";
      if("Y"==strChkKy){
            if(!ky()){
              alert("Ký không thành công");
              return false;
            }
          }
      jQuery("#dialog-message-check").html('<fmt:message key="XuLyLenhQT.page.message.confirm_duyet"/>');
      jQuery("#dialog-message-check").dialog("open");
  });
  jQuery("#btnChuyen").click(function () { 
      document.forms[0].target='';
      strActionKTT="CHUYEN_KS";
      if(jQuery("#loai_hach_toan").val() == null || ''==jQuery("#loai_hach_toan").val()){
        alert('Phải chọn giá trị trường loại hạch toán');
        jQuery("#loai_hach_toan").focus();
        return false;
      }else if(jQuery("#loai_hach_toan").val().toUpperCase() == 'P'){
          if(jQuery("#ldo_hach_toan").val() == null || ''== jQuery("#ldo_hach_toan").val().trim()){
            alert('Phải nhập lý do hạch toán trong trường hợp chờ xử lý!');
            jQuery("#ldo_hach_toan").focus();
            return false;
          }
      }
      jQuery("#dialog-message-check").html('<fmt:message key="XuLyLenhQT.page.message.confirm"/>');
      jQuery("#dialog-message-check").dialog("open");
  });
  
  jQuery("#btnExit").click(function () {
  document.forms[0].target='';
  if(checkExit=='true'){
      var rowSelected='<%=request.getAttribute("rowSelected")%>' == null ? "" : '<%=request.getAttribute("rowSelected")%>';
      document.forms[0].action="XuLyQToanTQuocView.do?id="+jQuery("#so_bk").val()+"&rowSelected="+rowSelected;
      document.forms[0].target='';
      document.forms[0].submit();
  }else if(checkExit=="VIEW_DETAIL"){
    var strActionBack = "TraCuuQToanList.do?action=Back";
    var hsc_nh_back = '<%=request.getParameter("hsc_nh")%>';
    var kb_tinh_back = '<%=request.getParameter("kb_tinh")%>';
    var kb_huyen_back = '<%=request.getParameter("kb_huyen")%>';
    var tu_ngay_back = '<%=request.getParameter("tu_ngay")%>';
    var den_ngay_back = '<%=request.getParameter("den_ngay")%>';
    var ngay_tt_back = '<%=request.getParameter("ngay_tt")%>';
    var loai_qt_back = '<%=request.getParameter("loai_qt")%>';
    var pt_qt_back = '<%=request.getParameter("pt_qt")%>';
    var tcg_loai_tien_back = '<%=request.getParameter("tcg_loai_tien")%>';
    if(hsc_nh_back != 'null'){
      strActionBack += "&hsc_nh_back="+hsc_nh_back+"";
    }
    if(kb_tinh_back != 'null'){
      strActionBack += "&kb_tinh_back="+kb_tinh_back+"";
    }
    if(kb_huyen_back != 'null'){
      strActionBack += "&kb_huyen_back="+kb_huyen_back+"";
    }
    if(tu_ngay_back != 'null'){
      strActionBack += "&tu_ngay_back="+tu_ngay_back+"";
    }
    if(den_ngay_back != 'null'){
      strActionBack += "&den_ngay_back="+den_ngay_back+"";
    }
    if(ngay_tt_back != 'null'){
      strActionBack += "&ngay_tt_back="+ngay_tt_back+"";
    }
    if(loai_qt_back != 'null'){
      strActionBack += "&loai_qt_back="+loai_qt_back+"";
    }
    if(pt_qt_back != 'null'){
      strActionBack += "&pt_qt_back="+pt_qt_back+"";
    }
    if(tcg_loai_tien_back != 'null'){
      strActionBack += "&tcg_loai_tien_back="+tcg_loai_tien_back + "";
    }
    document.forms[0].action=strActionBack;    
    document.forms[0].submit();
  }else{
    jQuery("#dialog-confirm").html('<fmt:message key="XuLyLenhQT.page.message.confirm.thoat"/>');
        jQuery("#dialog-confirm").dialog("open");
      }
  });
   
});

    //HungBM edit - 20170606 - Thay function cua in sang su dung Jquery --START
    jQuery("#btnIn").click(function () {
      if(jQuery("#id").val()==null || jQuery("#id").val()==undefined || jQuery("#id").val()==''){
        alert('Không có bản ghi nào để in!');
        return;
      }
      var f = document.forms[0];
      f.action = "quyettoanRptAction.do";
      var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
      newDialog = window.open('', '_form', params);          
      f.target='_form';
      f.submit();
   
});
    //END
    
   // In
//    function formAction(){
//    alert("ayylmao1");
//      if(jQuery("#id").val()==null || jQuery("#id").val()==undefined || jQuery("#id").val()==''){
//        alert('Không có bản ghi nào để in!');
//        return;
//      }
//      alert("ayylmao");
//      var f = document.forms[0];
//      f.action = "quyettoanRptAction.do";
//      var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
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
    function removeValue(lhtoan){
      if(lhtoan.value=="T"){
        jQuery("#ldo_hach_toan").val('');
      }
    }
    document.onkeydown =keyDownLHTQT;
</script>
<%@ include file="/includes/ttsp_bottom.inc"%>