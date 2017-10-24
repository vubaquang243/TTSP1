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
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dien.tra.soat.tloi.js"  type="text/javascript"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabcontent.js"></script>
<title><fmt:message key="doi_chieu.page.title"/></title>
 <%
                   String strketqua = "";
                   String strloaibk = "";
                   String strmessagekq="";
                   if(request.getAttribute("ketqua") != null)
                    strketqua = request.getAttribute("ketqua").toString();
                   
                    if (strketqua.equals("0"))
                    {
                     strmessagekq="Chưa nhận message";
                    } else if (strketqua.equals("4")) {
                    strmessagekq=" Đã tạo message";
                    } else if (strketqua.equals("3")) {
                    strmessagekq=" Chưa có bảng kê mới";
                    }
                    else if (strketqua.equals("2"))
                     {
                    strmessagekq=" Chưa nhận đủ message";
                    }
                    else if (strketqua.equals("1"))
                     {
                    strmessagekq="Đã nhận đủ message";
                    }
                     if(request.getAttribute("loaibk") != null)
                    strloaibk = request.getAttribute("loaibk").toString();
                     if (strloaibk.equals("0")){
                     strmessagekq += " - bảng kê lần 2";
                     }
                      if (strloaibk.equals("1")){
                     strmessagekq += " - bảng kê lần 1";
                     }
                    
                    
                    
                   %>
<script type="text/javascript">
  jQuery.noConflict(); 	
   jQuery(document).ready(function()
    {
     ketqua = '<%=strketqua%>';//document.getElementById('status').value;
    
      if(document.getElementById('status').value!="" && document.getElementById('status').value!="03"){
      jQuery("#btn_thuchien").attr({disabled:''});
      }else{
      jQuery("#btn_thuchien").attr({disabled:true});
      }
     if(document.getElementById('status').value=="02"){
      jQuery("#btn_taodoichieu").attr({disabled:false});
     }else{
       jQuery("#btn_taodoichieu").attr({disabled:true}); 
     }
      if(document.getElementById('status').value=="01" && document.getElementById('dxn_id').value!=""){
      jQuery("#btn_taolaidoichieu").attr({disabled:false});
     }else{
       jQuery("#btn_taolaidoichieu").attr({disabled:true}); 
     }
      if( "<%=strmessagekq%>" !="")
      jQuery("#kq_nhanmsg").val("<%=strmessagekq%>");
      jQuery("#btn_thoat").click(function() {
         if (confirm('Bạn có chắc chắn muốn thoát khỏi chức năng này?') == false)
            return false;
          else {
              document.forms[0].action = 'mainAction.do';
              document.forms[0].submit();
          }
       });
       jQuery("#btn_xem").click(function() {
       if (jQuery("#tu_ngan_hang option:selected").val()=='') {
         alert('Phải chọn 1 ngân hàng để đối chiếu');
         return false;
       }
            document.forms[0].action = 'xemmsgDC.do';
            document.forms[0].submit();
       });
        jQuery("#btn_thuchien").click(function() {
            jQuery("#btn_taodoichieu").attr({"disabled":false});
            document.forms[0].action = 'thuchiendoichieu.do';
            document.forms[0].submit();
       });
     
     });
      //**************************LINK TRANG CLICK********************************************
    function makeGetRequestView(id,type){
      var urlRequest  =null;
      var nhkb_chuyen = jQuery("#tu_ngan_hang option:selected").val();
      var ma_nh = jQuery("#ma_ngan_hang").val();
        urlRequest="themdtslttdi.do?action=add&referrer=thuchiendoichieu&id="+id;
      
      if(nhkb_chuyen != null && nhkb_chuyen != ''){
      urlRequest += "&nhkb_chuyen_cm="+nhkb_chuyen+"";
      }
       if(ma_nh != null && ma_nh != ''){
      urlRequest += "&ma_nhkb="+ma_nh+"";
      }
      window.location = urlRequest;
    }
    function taoDoiChieu(){
      document.forms[0].action = 'dcAddExc.do';
      document.forms[0].submit();
      alert('Tạo điện thành công');
    }
    function taoLaiDoiChieu(){
      document.forms[0].action = 'dcUpdateExc.do';
      document.forms[0].submit();
      alert('Tạo lại thành công');
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
                                  <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
                                  <html:hidden property="loai_bk"  styleId="loai_bk"/>
                                  <table style="border-collapse:collapse;" class="bordertableChungTu" border="0" cellspacing="0" width="100%">
                                    <tbody>
                                      <tr>
                                        <td align="left" width="40%" valign="top">
                                        <table style="border-collapse:collapse;" border="0" cellspacing="0" width="100%">
                                        <tr>
                                        <td valign="top">
                                           <label for="ngan_hang" ><fmt:message key="doi_chieu.page.lable.ngan_hang"/></label>
                                          </td>
                                           <td class="promptText"  align="left" >
                                           <html:select styleClass="selectBox"
                                               property="tu_ngan_hang"
                                               styleId="tu_ngan_hang" style="width:164px" onchange="getTenNganHang_KB('tu_ngan_hang', 'ten_ngan_hang', 'ma_ngan_hang')"
                                               onblur="getTenNganHang_KB('tu_ngan_hang', 'ten_ngan_hang', 'ma_ngan_hang');textlostfocus(this);"
                                               onfocus="textfocus(this);"
                                               onkeydown="nextFocus();">
                                                <html:option value="">
                                                  <fmt:message key="doi_chieu.page.option.ngan_hang.default"/>
                                                </html:option>
                                              <logic:notEmpty name="colDMNH">
                                                <html:optionsCollection name="colDMNH" value="ma_nh" label="ma_nh"/>
                                              </logic:notEmpty>
                                          </html:select>
                                            </td>
                                            <td rowspan="2" align="center" valign="top">
                                            <button id="btn_xem" accesskey="i" type="button" class="ButtonCommon"  value="xem">
                                             <fmt:message key="doi_chieu.page.button.xem"/>
                                            </button>
                                          </td>
                                            </tr>
                                          <tr> <td colspan="2">
                                          <html:text property="ten_ngan_hang" styleId="ten_ngan_hang" styleClass="fieldTextTrans"  readonly="true"
                                                 onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 200px;"/>
                                            </td><td>
                                                  <html:hidden property="ma_ngan_hang" styleId="ma_ngan_hang"/>
                                          </td></tr>
                                        </table>
                                        </td>
                                        <td>
                                        <table style="border-collapse:collapse;" cellpadding="4"  border="0" cellspacing="0" width="100%">
                                        <tr>
                                          <td class="promptText" align="right" width="26%" >
                                           <label for="thong_bao"><fmt:message key="doi_chieu.page.lable.thong_bao"/></label>
                                           
                                          </td>
                                           <td class="promptText"  >
                                            <html:text property="kq_nhanmsg" styleId="kq_nhanmsg" styleClass="fieldTextTrans" style="width:200;" onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true"/>
                                            <html:hidden property="bk_id" styleId="bk_id" />
                                            <html:hidden property="status" styleId="status" />
                                            <html:hidden property="dxn_id" styleId="dxn_id" />
                                           </td>
                                          <td class="promptText" align="right" width="26%" >
                                           <label for="tong_tien"><fmt:message key="doi_chieu.page.lable.tong_tien"/></label>
                                          </td>
                                           <td class="promptText"  >
                                           <html:text property="tong_tien" styleId="tong_tien" styleClass="fieldText" style="width:163;" onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true"/>
                                           </td>
                                        </tr>
                                         <tr>
                                          <td class="promptText" align="right" width="26%" >
                                           <label for="lenh_di"><fmt:message key="doi_chieu.page.lable.lenh_di"/></label>
                                          </td>
                                           <td class="promptText">
                                           <html:text property="lenh_di" styleId="lenh_di" styleClass="fieldText" style="width:163;" onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true"/>
                                        </td>
                                        <td class="promptText" align="right" width="26%" >
                                           <label for="tra_soat_di"><fmt:message key="doi_chieu.page.lable.tra_soat_di"/></label>
                                          </td>
                                           <td class="promptText"  >
                                           <html:text property="dien_di" styleId="dien_di" styleClass="fieldText" style="width:163;" onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true"/>
                                         </td>
                                        </tr>
                                        <tr>
                                         <td class="promptText" align="right" width="26%" >
                                           <label for="lenh_den"><fmt:message key="doi_chieu.page.lable.lenh_den"/></label>
                                          </td>
                                           <td class="promptText"  >
                                           <html:text property="lenh_den" styleId="lenh_den" styleClass="fieldText" style="width:163;" onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true"/>
                                         </td>
                                          
                                         <td class="promptText" align="right" width="26%">
                                           <label for="tra_soat_den"><fmt:message key="doi_chieu.page.lable.tra_soat_den"/></label>
                                          </td>
                                           <td class="promptText"   >
                                           <html:text property="dien_den" styleId="dien_den" styleClass="fieldText" style="width:163;" onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true"/>
                                         </td>
                                        </tr>
                                         <tr>
                                        <td colspan="4" align="center">
                                          <div style="padding:10px 0px 10px 0px; ">
                                            <button id="btn_thuchien" accesskey="i" type="button" class="ButtonCommon" disabled="disabled" style="width:150px;height:25px;" >
                                             <fmt:message key="doi_chieu.page.button.thuc_hien"/>
                                            </button>
                                            &nbsp;
                                          </div>
                                        </td>
                                      </tr>
                                        </table>
                                        </td>
                                      </tr>
                                     
                                    </tbody>
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
          </td>
        </tr>
        </html:form>
      </table>
      
<fieldset>
<legend><fmt:message key="doi_chieu.page.ketqua"/></legend>
<ul id="countrytabs" class="shadetabs">
<li><a href="#" rel="div_ltt" >Lệnh thanh toán/ Quyết toán</a></li>
<li><a href="#" rel="div_dts">Điện tra soát</a></li>
</ul>
<div  style="border:1px solid gray; width:100%; margin-bottom: 1em; padding: 2px">
        <div id="div_ltt"  class="scroll_box" style="height:300;overflow-y: scroll;">
         <table border="1" align="center" width="100%" class="borderTableChungTu">
            <thead class="TR_Selected">
              <tr >
               <th width="80">
                  <fmt:message key="doi_chieu.page.lable.dv_nhan"/>
                </th>
                <th width="120">
                  <fmt:message key="doi_chieu.page.lable.ten_dv_nhan"/>
                </th>
                <th width="80">
                  <fmt:message key="doi_chieu.page.lable.dv_chuyen"/>
                </th>
                <th >
                  <fmt:message key="doi_chieu.page.lable.ten_dv_chuyen"/>
                </th>
                <th width="70">
                  <fmt:message key="doi_chieu.page.lable.so_tien"/>
                </th>
                <th width="80">
                  <fmt:message key="doi_chieu.page.lable.so_ltt"/>
                </th>
                <th width="60">
                  <fmt:message key="doi_chieu.page.lable.ngay_tt"/>
                </th>
                 <th width="20">
                  <fmt:message key="doi_chieu.page.lable.nguyen_te"/>
                </th>
                <th width="70">
                  <fmt:message key="doi_chieu.page.lable.nguoi_lap"/>
                </th>
                <th width="70">
                 <fmt:message key="doi_chieu.page.lable.nguoi_ks"/>
                </th>
                <th width="80">
                 <fmt:message key="doi_chieu.page.lable.tinh_trang"/>
                </th>
              </tr>
            </thead>
            <tbody>
            <logic:empty name="colDCLTT">
              <TR><td colspan="10"> 
               <%
                if(request.getAttribute("colDCLTT") != null){
                if(request.getAttribute("loaibk").toString().equals("0")){
                %>
              <font color="Red" dir="ltr">
                 Đối chiếu Lệnh quyết toán đã khớp
                </font>
                <% } else %>
                 <font color="Red" dir="ltr">
                 Đối chiếu Lệnh thanh toán đã khớp
                </font>
                <%}%></td> </tr>
            </logic:empty>
              <logic:notEmpty name="colDCLTT">
              <logic:iterate id="items" name="colDCLTT" indexId="stt">
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
                 
               </tr>
               </logic:iterate>
               </logic:notEmpty>
            </tbody>
          </table>
        </div>
        <div id="div_dts"  class="scroll_box" style="height:300;overflow-y: scroll;display:none">
         <table border="1" align="center" width="100%" class="borderTableChungTu">
            <thead class="TR_Selected">
              <tr >
                <th width="80">
                  <fmt:message key="doi_chieu.page.lable.dv_nhan"/>
                </th>
                <th width="100">
                  <fmt:message key="doi_chieu.page.lable.ten_dv_nhan"/>
                </th>
                <th width="80">
                  <fmt:message key="doi_chieu.page.lable.dv_chuyen"/>
                </th>
                <th width="100">
                  <fmt:message key="doi_chieu.page.lable.ten_dv_chuyen"/>
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
                 <th width="70">
                  <fmt:message key="doi_chieu.page.lable.nguoi_lap"/>
                </th>
                <th width="70">
                 <fmt:message key="doi_chieu.page.lable.nguoi_ks"/>
                </th>
                 <th width="80">
                 <fmt:message key="doi_chieu.page.lable.tinh_trang"/>
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
              <logic:iterate id="items" name="colDCDTS" indexId="stt">
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
     
     </fieldset>
       <div style="padding:10px 10px 10px 0px;float:right ">
        <button id="btn_taodoichieu" accesskey="x" type="button" class="ButtonCommon" disabled="disabled"  onclick="return taoDoiChieu();" style="with:100px">
                     <fmt:message key="doi_chieu.page.button.tao_dxn"/>
                    </button>                    
                    &nbsp;
                    <button id="btn_taolaidoichieu" accesskey="l" type="button" class="ButtonCommon" disabled="disabled" style="with:100px"  onclick="return taoLaiDoiChieu();">
                      <fmt:message key="doi_chieu.page.button.tao_lai"/>
                    </button>
        <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon" tabindex="23">
          <fmt:message key="doi_chieu.page.button.exit"/>
        </button>
      </div>

<%@ include file="/includes/ttsp_bottom.inc"%>
