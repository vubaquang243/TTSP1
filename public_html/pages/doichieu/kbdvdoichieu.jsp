<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/tabcontent.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabcontent.js"></script>
<title><fmt:message key="doi_chieu.page.title"/></title>
 <%
                  String userID="";
                   if(request.getAttribute("userID") != null)
                   userID=request.getAttribute("userID").toString();
                   %>
<script type="text/javascript">
  jQuery.noConflict(); 	
   jQuery(document).ready(function()
    {
      jQuery("#btn_thoat").click(function() {
         if (confirm('Bạn có chắc chắn muốn thoát khỏi chức năng này?') == false)
            return false;
          else {
              document.forms[0].action = 'mainAction.do';
              document.forms[0].submit();
          }
       });
     });
      //**************************LINK TRANG CLICK********************************************
    function makeGetRequest(id,trangthai,ttv, ktt,type){
    if (trangthai=='1') {
      alert('Lệnh này đã được hủy.');
      return;
    }
     if( ktt!="") {
      if (ktt !='<%=userID%>' ) {
        alert('Bạn không có quyền hủy lệnh này.');
        return; 
      }
     }else {
      if (ttv !='<%=userID%>' ) {
        alert('Bạn không có quyền hủy lệnh này.');
        return; 
      }  
     }
      var urlRequest  =null;
      urlRequest="huykbdvDChieu.do?action=cancel&id="+id+"&type="+type;
      window.location = urlRequest;
      alert('Thực hiện hủy thành công');
    }
      
  </script>
    <div class="app_error"><html:errors/></div>
   <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
              <fmt:message key="doi_chieu.page.title"/></span>
        </td>
        <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
          </tr>
      </tbody>
    </table>  
      <table class="tableBound">
        <html:form action="loadformDC" styleId="frmDoiChieu">
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
                            <td>
                              <input type="hidden" id="fieldNameForcus"/>
                              <ul id="countrytabs" class="shadetabs">
                              <li><a href="#" rel="div_ltt" class="selected">Lệnh thanh toán/ Quyết toán</a></li>
                              <li><a href="#" rel="div_dts">Điện tra soát</a></li>
                              </ul>
                                 <div  style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 10px">
                                      <div id="div_ltt"  class="scroll_box" style="height:300;overflow-y: scroll;overflow-x: scroll;">
                                       <table border="2" align="center" width="140%" class="borderTableChungTu">
                                          <thead class="TR_Selected">
                                            <tr >
                                              <th width="80">
                                                <fmt:message key="doi_chieu.page.lable.dv_chuyen"/>
                                              </th>
                                              <th >
                                                <fmt:message key="doi_chieu.page.lable.ten_dv_chuyen"/>
                                              </th>
                                              <th width="80">
                                                <fmt:message key="doi_chieu.page.lable.dv_nhan"/>
                                              </th>
                                              <th width="120">
                                                <fmt:message key="doi_chieu.page.lable.ten_dv_nhan"/>
                                              </th>
                                              <th width="70">
                                                <fmt:message key="doi_chieu.page.lable.so_tien"/>
                                              </th>
                                              <th width="100">
                                                <fmt:message key="doi_chieu.page.lable.so_ltt"/>
                                              </th>
                                              <th width="60">
                                                <fmt:message key="doi_chieu.page.lable.ngay_tt"/>
                                              </th>
                                               <th width="20">
                                                <fmt:message key="doi_chieu.page.lable.nguyen_te"/>
                                              </th>
                                              <th width="60">
                                                <fmt:message key="doi_chieu.page.lable.nguoi_lap"/>
                                              </th>
                                              <th width="60">
                                               <fmt:message key="doi_chieu.page.lable.nguoi_ks"/>
                                              </th>
                                              <th width="60">
                                               <fmt:message key="doi_chieu.page.lable.trang_thai"/>
                                              </th>
                                               <th width="80">
                                               <fmt:message key="doi_chieu.page.lable.tinh_trang"/>
                                              </th>
                                               <th width="20">
                                              </th>
                                            </tr>
                                          </thead>
                                          <tbody>
                                          <logic:empty name="colDCLTT">
                                            <TR><td colspan="10"> 
                                             <%
                                              if(request.getAttribute("colDCLTT") != null){
                                              %>
                                            <font color="Red" dir="ltr">
                                               Đối chiếu đã khớp
                                              </font>
                                              <% }%></td> </tr>
                                          </logic:empty>
                                            <logic:notEmpty name="colDCLTT">
                                            <logic:iterate id="items" name="colDCLTT" indexId="stt" type="com.seatech.ttsp.doichieu.DoiChieuLTTDTSVO">
                                            <tr >
                                                <td align="center">
                                                 <bean:write name="items" property="nhkb_chuyen"/>
                                                </td>
                                               <td align="left">
                                                  <bean:write name="items" property="ten_nhkb_chuyen"/>
                                                </td>
                                                <td align="center">
                                                 <bean:write name="items" property="nhkb_nhan"/>
                                                </td>
                                                <td  align="left">
                                                 <bean:write name="items" property="ten_nhkb_nhan"/>
                                                </td>
                                                <td align="right">
                                                  <bean:write name="items" property="tong_sotien"/>
                                                </td>
                                                <td align="center">
                                                 <bean:write name="items" property="id"/>
                                                </td>
                                                <td align="center">
                                                  <bean:write name="items" property="ngay_tt"/>
                                                </td>
                                                 <td align="center">
                                                  <bean:write name="items" property="nt_id"/>
                                                </td>
                                                <td align="left">
                                                 <bean:write name="items" property="ttv_nhan"/>
                                                </td>
                                                <td align="left">
                                                  <bean:write name="items" property="ktt_duyet"/>
                                                </td>
                                                 <td align="left">
                                                  <bean:write name="items" property="content"/>
                                                </td>
                                                <td align="left">
                                                   <logic:equal value="0" name="items" property="trang_thai">
                                                     Chưa hủy
                                                    </logic:equal>
                                                     <logic:equal value="1" name="items" property="trang_thai">
                                                     Đã hủy
                                                    </logic:equal>
                                                </td>
                                                 <td align="left">
                                                  <%-- <logic:equal value="701" name="items" property="loai_lenh">
                                                   
                                                     <html:link page="/XuLyDTSoatTuDo.do?action=VIEW_DETAIL_TUDO" paramId="id" paramNameitems" paramProperty="id">Xem DTS</html:link>--%>
                                                    <%
                                                    String strLoaiLenh = "";
                                                    strLoaiLenh=items.getLoai_lenh().toString()+items.getTt_dc().toString()+items.getTrang_thai().toString();
                                                    if (strLoaiLenh.equals("70140")||strLoaiLenh.equals("701430"))
                                                    {
                                                   %>
                                                    <a href="javascript:makeGetRequest(<bean:write name="items" property="id"/>, '<bean:write name="items" property="trang_thai"/>',
                                                     '<bean:write name="items" property="ttv_nhan"/>','<bean:write name="items" property="ktt_duyet"/>', 103)">Hủy</a>
                                                     <%}%>
                                                   <%-- </logic:equal>--%>
                                                </td>
                                             </tr>
                                             </logic:iterate>
                                             </logic:notEmpty>
                                          </tbody>
                                        </table>
                                      </div>
                                      <div id="div_dts"  class="scroll_box" style="height:300;overflow-y: scroll; overflow-x: scroll;display:none">
                                       <table border="2" align="center" width="140%" class="borderTableChungTu">
                                          <thead class="TR_Selected">
                                            <tr >
                                              <th width="80">
                                                <fmt:message key="doi_chieu.page.lable.dv_chuyen"/>
                                              </th>
                                              <th width="100">
                                                <fmt:message key="doi_chieu.page.lable.ten_dv_chuyen"/>
                                              </th>
                                              <th width="80">
                                                <fmt:message key="doi_chieu.page.lable.dv_nhan"/>
                                              </th>
                                              <th width="100">
                                                <fmt:message key="doi_chieu.page.lable.ten_dv_nhan"/>
                                              </th>
                                              <th width="80">
                                                <fmt:message key="doi_chieu.page.lable.so_ltt"/>
                                              </th>
                                              <th width="70">
                                                <fmt:message key="doi_chieu.page.lable.dts_id"/>
                                              </th>
                                               <th width="70">
                                                <fmt:message key="doi_chieu.page.lable.so_dts"/>
                                              </th>
                                              <th >
                                                <fmt:message key="doi_chieu.page.lable.noi_dung"/>
                                              </th>
                                               <th width="60">
                                                <fmt:message key="doi_chieu.page.lable.nguoi_lap"/>
                                              </th>
                                              <th width="60">
                                               <fmt:message key="doi_chieu.page.lable.nguoi_ks"/>
                                              </th>
                                              <th width="60">
                                               <fmt:message key="doi_chieu.page.lable.trang_thai"/>
                                              </th>
                                               <th width="80">
                                               <fmt:message key="doi_chieu.page.lable.tinh_trang"/>
                                              </th>
                                              <th width="20">
                                              </th>
                                            </tr>
                                          </thead>
                                          <tbody>
                                          <logic:empty name="colDCDTS">
                                           <TR><td colspan="10"> 
                                             <%
                                              if(request.getAttribute("colDCDTS") != null){
                                              %>
                                            <font color="Red" dir="ltr">
                                               Đối chiếu Điện tra soát đã khớp
                                              </font>
                                              <% }%></td> </tr>
                                          </logic:empty>
                                            <logic:notEmpty name="colDCDTS">
                                            <logic:iterate id="items" name="colDCDTS" indexId="stt"  type="com.seatech.ttsp.doichieu.DoiChieuLTTDTSVO">
                                            <tr >
                                                <td align="center">
                                                 <bean:write name="items" property="nhkb_nhan"/>
                                                </td>
                                                <td  align="left">
                                                 <bean:write name="items" property="ten_nhkb_nhan"/>
                                                </td>
                                                <td align="center">
                                                 <bean:write name="items" property="nhkb_chuyen"/>
                                                </td>
                                               <td align="left">
                                                  <bean:write name="items" property="ten_nhkb_chuyen"/>
                                                </td>
                                                <td align="center">
                                                 <bean:write name="items" property="ltt_id"/>
                                                </td>
                                                <td align="center">
                                                  <bean:write name="items" property="id"/>
                                                </td>
                                                 <td align="center">
                                                  <bean:write name="items" property="dts_id"/>
                                                </td>
                                                <td align="left">
                                                 <bean:write name="items" property="noi_dung"/>
                                                </td>
                                                 <td align="left">
                                                 <bean:write name="items" property="ttv_nhan"/>
                                                </td>
                                                <td  align="left">
                                                 <bean:write name="items" property="ktt_duyet"/>
                                                </td>
                                                 <td align="left">
                                                  <bean:write name="items" property="content"/>
                                                </td>
                                                  <td align="left">
                                                   <logic:equal value="0" name="items" property="trang_thai">
                                                     Chưa hủy
                                                    </logic:equal>
                                                     <logic:equal value="1" name="items" property="trang_thai">
                                                     Đã hủy
                                                    </logic:equal>
                                                </td>
                                                 <td  align="left">
                                                   <%-- <logic:equal value="195" name="items" property="loai_dien">
                                                    <logic:notEqual value="195" name="items" property="tt_dc">
                                                    
                                                    </logic:notEqual>
                                                     -<html:link page="/XuLyDTSoatTuDo.do?action=VIEW_DETAIL_TUDO" paramId="id" paramNameitems" paramProperty="id">Xem DTS</html:link>--%>
                                                       <%
                                                    String strLoaiLenh = "";
                                                    strLoaiLenh=items.getLoai_dien().toString()+items.getTt_dc().toString()+items.getTrang_thai().toString();
                                                   
                                                    if (strLoaiLenh.equals("19540")||strLoaiLenh.equals("195430"))
                                                    { %>
                                                     <a href="javascript:makeGetRequest(<bean:write name="items" property="id"/>,'<bean:write name="items" property="trang_thai"/>',
                                                      '<bean:write name="items" property="ttv_nhan"/>','<bean:write name="items" property="ktt_duyet"/>',195)">Hủy</a>
                                                      <%}%>
                                                   <%-- </logic:equal>--%>
                                                </td>
                                             </tr>
                                             </logic:iterate>
                                             </logic:notEmpty>
                                          </tbody>
                                        </table>
                                      </div>
                                     </div>
                              <script type="text/javascript">
                              
                              var countries=new ddtabcontent("countrytabs")
                              countries.setpersist(true)
                              countries.setselectedClassTarget("link") //"link" or "linkparent"
                              countries.init()
                              
                              </script>
                                   
                                     <div style="padding:10px 10px 10px 0px;float:right ">
                                      <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon" tabindex="23">
                                        <fmt:message key="doi_chieu.page.button.exit"/>
                                      </button>
                                    </div>
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
        </html:form>
      </table>
    

<%@ include file="/includes/ttsp_bottom.inc"%>
