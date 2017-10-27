<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/tabber.css"/>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/tabber.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/doichieu.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"
        type="text/javascript">
</script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jshashtable-2.1.js" type="text/javascript">
</script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.number_format.js" type="text/javascript">
</script> 

<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
  String strRowSelected = request.getAttribute("rowSelected")==null?"":request.getAttribute("rowSelected").toString();
  String pht_ttsp = request.getAttribute("pht_ttsp")==null?"":request.getAttribute("pht_ttsp").toString();
  String tcong = request.getAttribute("duyettcong")==null?"":request.getAttribute("duyettcong").toString();
  String dung = request.getAttribute("dung")==null?"":request.getAttribute("dung").toString();
  String lech = request.getAttribute("lech")==null?"":request.getAttribute("lech").toString();
  String chuahoanthanh = request.getAttribute("chuahoanthanh")==null?"":request.getAttribute("chuahoanthanh").toString();
  //ThuongDT-20170405: them dieu kiem kiem tra doi voi ngay hom truoc chua ket thuc doi chieu
  String chuaketthuc = request.getAttribute("chuaketthuc")==null?"":request.getAttribute("chuaketthuc").toString();
  String dchieuclech = request.getAttribute("dchieu_clech")==null?"":request.getAttribute("dchieu_clech").toString();
  
  String notPHT = request.getAttribute("notPHT")==null?"":request.getAttribute("notPHT").toString();
  String notTTSP = request.getAttribute("notTTSP")==null?"":request.getAttribute("notTTSP").toString();
  String chenh_cot = request.getAttribute("chenh_cot")==null?"0":request.getAttribute("chenh_cot").toString();
  String chkdate = request.getAttribute("chkdate")==null?"":request.getAttribute("chkdate").toString();
  String size = request.getAttribute("size")==null?"":request.getAttribute("size").toString();
  String loai_gd = request.getAttribute("loai_gd")==null?"":request.getAttribute("loai_gd").toString();
  String chkSoDu = request.getAttribute("chkSoDu")==null?"":request.getAttribute("chkSoDu").toString();
  String qtoan_ko_dchieu = request.getAttribute("qtoan_ko_dchieu")==null?"":request.getAttribute("qtoan_ko_dchieu").toString();
  String qtth = request.getAttribute("qtth")==null?"":request.getAttribute("qtth").toString();
  //HungBM - kiem tra xem 066 da co bang ke chua - BEGIN
  String ton_tai_066 = request.getAttribute("ton_tai_066")==null?"":request.getAttribute("ton_tai_066").toString();
  //HungBM - kiem tra xem 066 da co bang ke chua - END
  //20170925 - thuongdt kiem tra so qtoan bang 0
   String dchieu_clech_qtoan = request.getAttribute("dchieu_clech_qtoan")==null?"":request.getAttribute("dchieu_clech_qtoan").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    //defaultStateFormBK();
    var strRowSelected="<%=strRowSelected%>";
    var dung="<%=dung%>";
    var lech="<%=lech%>";
    var size="<%=size%>";
     var notPHT="<%=notPHT%>";
      var notTTSP="<%=notTTSP%>";
      var chkSoDu="<%=chkSoDu%>";
       var qtth="<%=qtth%>";
       //HungBM 20170505 - begin
      var ton_tai_066 = "<%=ton_tai_066%>";
       //END
    var chuahoanthanh="<%=chuahoanthanh%>";
    var chuaketthuc="<%=chuaketthuc%>";
    var dchieuclech="<%=dchieuclech%>";
     var dchieuclechqtoan="<%=dchieu_clech_qtoan%>";
//    var qtoan_ko_dchieu="<%=qtoan_ko_dchieu%>";
//        alert(qtoan_ko_dchieu);
//        alert(size);

    if(strRowSelected!=null && '' != strRowSelected){
    rowSelectedFocusXNDC(strRowSelected);
    }
  if(dung!=null && '' != dung){
      alert(GetUnicode('T&#7841;o &#273;i&#7879;n x&#225;c nh&#7853;n &#273;&#7873; ngh&#7883; quy&#7871;t to&#225;n th&#224;nh c&#244;ng'));
    }
    if(chkSoDu!=null && '' != chkSoDu){
      alert(GetUnicode('Số dư tài khoản âm. Không thể lập đề nghị quyết toán.'));
    }
    //HungBM-20170505: Kiem tra dien 066 dien tu co ton tai hay ko
    if(ton_tai_066!=null && '' != ton_tai_066){
      alert(GetUnicode('Đã tồn tại điện 066 điện tử'));
    }
     //20170925 - thuongdt kiem tra so qtoan bang 0
    if(dchieuclechqtoan!=null && '' != dchieuclechqtoan){
      alert(GetUnicode('Số ĐNQT thu hoặc số ĐNQT chi không khớp với kết qua đối chiếu'));
    }
    
     if(qtth!=null && '' != qtth){
     alert(GetUnicode('Ngày cũ chưa hoàn thành đối chiếu.'));
      //alert(GetUnicode('Có lỗi trong quá trình tạo điện ĐNQT. Đề nghị tạo lại điện '));
    }
    
    if(chuahoanthanh!=null && '' != chuahoanthanh){
      alert(GetUnicode('Cần hoàn thành lập ĐNQT ngày trước'));
    } 
    //ThuongDT-20170405: them dieu kiem kiem tra doi voi ngay hom truoc chua ket thuc doi chieu begin    
    if(chuaketthuc!=null && '' != chuaketthuc){
      alert(GetUnicode('Chưa kết thúc đối chiếu ngày trước'));
    }
    
    if(dchieuclech!=null && '' != dchieuclech){
      alert(GetUnicode('Đối chiếu chưa khớp đúng'));
    }
    //ThuongDT-20170405: them dieu kiem kiem tra doi voi ngay hom truoc chua ket thuc doi chieu end
    jQuery(document).ready(function () {
        jQuery("#dialog-form-tcong").dialog({
      autoOpen: false,resizable : false,
//      height: "350px",
      maxHeight:"350",
      width: "700px",
      modal: true,
      buttons: {
        "Ghi": function() {

      stt= strRowSelected.substr(7,5);
      sttNext=parseInt(stt);
      ma_nh=document.getElementById("receive_bank_idx_"+sttNext).value;
      ngay_dc=document.getElementById("ngay_dc_idx_"+sttNext).value;
      pht_id = document.getElementById("pht_idx_"+sttNext).value;
      ttsp_id = document.getElementById("idx_"+sttNext).value;
      so_thu = jQuery("#dia_txt_thu_tcong").val().replace(/[.]/g,'');
      so_chi=jQuery("#dia_txt_chi_tcong").val().replace(/[.]/g,'');
      //Kiem tra noi dung nhap thu cong
      noi_dung=jQuery("#dia_txt_noi_dung").val();
      if(noi_dung !=null && noi_dung != ''){
        jQuery("#noi_dung").val(noi_dung);
      }else{
        alert('Cần nhập thông tin lý do phát sinh số thủ công');
        return false;
      }
      //----
        var f = document.forms[0];
         f.action = 'ThuChiTCongAction.do?noi_dung='+noi_dung+'&dia_txt_thu_tcong='+so_thu+'&dia_txt_chi_tcong='+so_chi+'&id_tcong='+jQuery("#dia_id").val()+'&ma_nh='+ma_nh+'&ngay_dc='+ngay_dc+'&pht_id='+pht_id+'&ttsp_id='+ttsp_id+'&loai_gd='+'<%=loai_gd%>';
         f.submit();
          jQuery( this ).dialog( "close" );
        },
        "Thoát": function() {
          jQuery( this ).dialog( "close" );
        }
      },
      "Đóng": function() {
      }
    });
 });
  }); 
</script>
<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="XNDCTHop1Action.do" method="post" >
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2> Đối chiếu tổng hợp - Lập đề nghị quyết toán</span>
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
       <td width="42%" align="center" colspan="4" >
        <fieldset>
        <legend><font color="Blue">Danh s&#225;ch x&#225;c nh&#7853;n &#272;XN t&#7893;ng h&#7907;p </font></legend>
        <div  style="height:370px;overflow-y: scroll;">
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="1"
                                             cellspacing="0" cellpadding="2" >
                 <tr>
                 <td align="center" style="width:10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.ngaydc"/></td>
                 <td align="center" style="width:10%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.duyet.manh"/></td>
                 <!--<td align="center" width="17%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.nhang"/></td>-->
                 <td align="center" style="width:35%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.sdxn"/></td>
                 <td align="center" style="width:30%" class="ui-state-default ui-th-column"><fmt:message key="doi_chieu.page.lable.tthai"/></td>
                 </tr>
                 <logic:empty name="colDChieu">
                  <tr>
                    <td colspan="5">
                      <font color="Red">Kh&#244;ng c&#243; b&#7843;ng k&#234; &#273;&#7889;i chi&#7871;u</font>
                      <input type="hidden" name="ket_qua" value="" id="ket_qua_pht"/>
                      <input type="hidden" name="ket_qua" value="" id="ket_qua_ttsp"/>
                      
                    </td>
                  </tr>
                </logic:empty>
               <logic:notEmpty name="colDChieu">
                  <logic:iterate id="UDlist" name="colDChieu" indexId="index">
                  <!--20170926 bo sung them ham disBt_onclick() disible tat ca cac button-->
                 <tr class="ui-widget-content jqgrow ui-row-ltr" 
                      id="row_qt_<bean:write name="index"/>"
                      onclick=" disBt_onclick();rowSelectedFocusXNDC('row_qt_<bean:write name="index"/>');                               
                               fillDataXNDC('XNDCTHop1Action.do','<bean:write name="UDlist" property="ngay_dc"/>','<bean:write name="UDlist" property="receive_bank"/>','<bean:write name="UDlist" property="ttsp_id"/>','<bean:write name="UDlist" property="pht_id"/>','row_qt_<bean:write name="index"/>','<bean:write name="UDlist" property="tthai_dxn_thop"/>');">
                   <td align="center">
                    <bean:write name="UDlist" property="ngay_dc"/>                    
                   </td>
                   <td align="center">
                    <bean:write name="UDlist" property="receive_bank"/>            
                   </td>
                  <td align="center" id="td_qt_<bean:write name="index"/>">
                    <bean:write name="UDlist" property="ttsp_id"/> 
                   </td>
                   <td align="center">
                    <logic:equal name="UDlist" property="tthai_dxn_thop" value="">
                            Chưa tạo điện
                         </logic:equal>
                         <logic:equal name="UDlist" property="tthai_dxn_thop" value="00">
                            Chưa tạo điện
                         </logic:equal>
                    <logic:equal name="UDlist" property="ket_qua_dxn_thop" value="0">                            
                         <logic:equal name="UDlist" property="tthai_dxn_thop" value="01">
                            Đã tạo điện
                         </logic:equal>
                         <logic:equal name="UDlist" property="tthai_dxn_thop" value="02">                            
                              Đã tạo điện
                            </logic:equal>
                      </logic:equal>
                     
                      
                      <logic:equal name="UDlist" property="ket_qua_dxn_thop" value="1"> 
                           <logic:equal name="UDlist" property="tthai_dxn_thop" value="01">                            
                              Xác nhận - chờ duyệt
                           </logic:equal>
                           <logic:equal name="UDlist" property="tthai_dxn_thop" value="03">                            
                              Đã xác nhận
                            </logic:equal>
                      </logic:equal>
                    
                   <input type="hidden" name="tthai" id="tthai_<bean:write name="index"/>" value="<bean:write name="UDlist" property="tthai_dxn_thop"/>" />
                   <input type="hidden" name="pht_idx" id="pht_idx_<bean:write name="index"/>" value="<bean:write name="UDlist" property="pht_id"/>" />
                   <input type="hidden" name="idx" id="idx_<bean:write name="index"/>" value="<bean:write name="UDlist" property="ttsp_id"/>" />
                   <input type="hidden" name="tthaikq" id="tthaikq_<bean:write name="index"/>" value="<bean:write name="UDlist" property="trang_thai"/>" />
                   <input type="hidden" name="receive_bank_idx" id="receive_bank_idx_<bean:write name="index"/>" value="<bean:write name="UDlist" property="receive_bank"/>" />
                   <input type="hidden" name="ngay_dc_idx" id="ngay_dc_idx_<bean:write name="index"/>" value="<bean:write name="UDlist" property="ngay_dc"/>" />    
                   
                   
                   </td>
                 </tr>
                  </logic:iterate>
                </logic:notEmpty>             
             </table>
             <input type="hidden" name="qtoan_ko_dchieu" id="qtoan_ko_dchieu" value="<%=qtoan_ko_dchieu%>"/>
           </div>
        </fieldset>
       </td>
       <td  width="58%">
        <fieldset>
            <legend><font color="Blue">T&#7893;ng h&#7907;p k&#7871;t qu&#7843; &#273;&#7889;i chi&#7871;u</font></legend>
            <div style="height:370px;">
              <table width="100%" cellspacing="0" cellpadding="2"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
                 <tr>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:25%" rowspan="2">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.thdlieu"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:25%" colspan="2">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.slieunh"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:25%" colspan="2">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.slieukb"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:25%" colspan="2">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.slieuchenh"/>
                        </div>
                   </th>
                  </tr>
                  <tr>
                    <th class="promptText" bgcolor="#f0f0f0" style="width:7%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.smon"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:20%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.stien"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:6%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.smon"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:20%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.stien"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:6%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.smon"/>
                        </div>
                   </th>
                   <th class="promptText" bgcolor="#f0f0f0" style="width:20%">
                        <div align="center">
                          <fmt:message key="doi_chieu.page.label.dc1.stien"/>
                        </div>
                   </th>
                  </tr>
                  <logic:empty name="colTTSP">
                   <% if(notTTSP == null || ""==notTTSP){
                    %>
                  <tr>
                    <td colspan="7">
                      <b>TTSP</b>: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                      <font style="FONT-WEIGHT: bold; color:red">Ch&#432;a nh&#7853;n dc k&#7871;t qu&#7843; &#273;&#7889;i chi&#7871;u t&#7915; TTSP </font>
                    </td>                   
                  </tr>
                  <tr>
                    <td colspan="7">
                     <b>-<fmt:message key="doi_chieu.page.label.dc1.dlthu"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td>
                      <input type="text" style="border:0" name="so_mon_thu_dtu" class="fieldTextRight" value="" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="so_tien_thu_dtu" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="7">
                      <b>-<fmt:message key="doi_chieu.page.label.dc1.dlchi"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td>
                      <input type="text" style="border:0" name="somon_dtu" class="fieldTextRight" value="" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="sotien_dtu" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_chi_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_chi_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_thu_tcong_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_thu_tcong_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                  </tr>
                  <% if(notTTSP != null && ""!=notTTSP){
                    %>
                     <input type="hidden" name="ket_qua" value="0" id="ket_qua_ttsp"/>            
                    <%}%>
                    <% if(notPHT != null && ""!=notPHT){
                    %>
                    <input type="hidden" name="ket_qua" value="0" id="ket_qua_pht"/> 
                              
                    <%}else{%>
                    <input type="hidden" name="ket_qua" value="" id="ket_qua_ttsp"/> 
                    <%}if ("Y".equals(qtoan_ko_dchieu)){%>
                        <input type="hidden" name="ket_qua" value="0" id="ket_qua_pht"/>
                        <input type="hidden" name="ket_qua" value="0" id="ket_qua_ttsp"/>
                      <%}%>
                  <%}%>
                  </logic:empty>
                  
                  <logic:notEmpty name="colTTSP">
                  <% if(notTTSP == null || ""==notTTSP){
                    %>
                  <logic:iterate id="items" name="colTTSP">
                  <tr>
                    <td colspan="7">
                      <b>TTSP</b>: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                      
                      <logic:equal name="items" property="ket_qua" value="0">
                         <font style="FONT-WEIGHT: bold; color:blue"><fmt:message key="doi_chieu.page.lable.pht.0"/></font>
                      </logic:equal>
                      <font style="FONT-WEIGHT: bold; color:red">
                          <logic:equal name="items" property="ket_qua" value="1">
                            <fmt:message key="doi_chieu.page.lable.pht.1"/>
                          </logic:equal>
                          <logic:equal name="items" property="ket_qua" value="2">
                            <fmt:message key="doi_chieu.page.lable.pht.2"/>
                          </logic:equal>
                      </font>
                      <html:hidden property="ket_qua" name="items" styleId="ket_qua_ttsp"/>
                      <html:hidden property="id" name="items" styleId="ttsp_id" />
                      <input type="hidden" name="ttsp_id" value="<bean:write property="id"  name="items"/>"/>
                      <html:hidden property="ngay_dc" name="items"/>
                      <!--<html:hidden property="tthai_dxn_thop" name="items"/>-->
                      <html:hidden property="receive_bank" name="items"/>
                      
                    </td>                   
                  </tr>
                  <tr>
                    <td colspan="7">
                     <b>-<fmt:message key="doi_chieu.page.label.dc1.dlthu"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="so_mon_thu_dtu"/>
                    </fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="so_tien_thu_dtu" />
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="mon_thu_dtu_kbnn"/>
                      </fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="tien_thu_dtu_kbnn"/>
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="chenh_mthu_dtu"/>
                      </fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="chenh_tthu_dtu"/>
                      </fmt:formatNumber>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="7">
                      <b>-<fmt:message key="doi_chieu.page.label.dc1.dlchi"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="somon_dtu"/>
                      </fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="sotien_dtu" />
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      
                      <bean:write name="items" property="mon_chi_dtu_kbnn" />
                      </fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="tien_chi_dtu_kbnn" />
                    </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="chenh_mchi_dtu" />
                    
                    </fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="chenh_tchi_dtu" />
                    </fmt:formatNumber>
                    </td>
                  </tr>
                  </logic:iterate>
                  <%}%>
                  <% if(notTTSP != null && ""!=notTTSP){
                    %>
                    <input type="hidden" name="ket_qua" value="0" id="ket_qua_ttsp"/>            
                    <%}%>
                    <% if(notPHT != null && ""!=notPHT){
                    %>
                    <input type="hidden" name="ket_qua" value="0" id="ket_qua_pht"/>            
                    <%}%>
                  </logic:notEmpty>
                  

                  <logic:empty name="colPHT">
                  <% if(notPHT == null || ""==notPHT){
                    %>
                  <tr>
                    <td colspan="7"> <b>PHT:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    
                      <font style="FONT-WEIGHT: bold; color:red">
                          Ch&#432;a nh&#7853;n &#273;&#432;&#7907;c k&#7871;t qu&#7843; t&#7915; PHT
                      </font>
                      
                    </td>
                  </tr>
                  <tr>
                    <td colspan="7">
                     <b>-<fmt:message key="doi_chieu.page.label.dc1.dlthu"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td>
                      <input type="text" style="border:0" name="so_mon_thu_dtu" class="fieldTextRight" value="" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="so_tien_thu_dtu" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_thu_dtu_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="mon_thu_tcong_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                    <td>
                      <input type="text" name="tien_thu_tcong_kbnn" class="fieldTextRight" value="" style="border:0" readonly="true"/>
                    </td>
                  </tr>
                  <% if(notTTSP != null && ""!=notTTSP){
                    %>
                    <input type="hidden" name="ket_qua" value="0" id="ket_qua_ttsp"/>            
                    <%}%>
                    <% if(notPHT != null && ""!=notPHT){
                    %>
                    <input type="hidden" name="ket_qua" value="0" id="ket_qua_pht"/>            
                    <%}else{%>
                      <input type="hidden" name="ket_qua" value="" id="ket_qua_pht"/>    
                    <%}if ("Y".equals(qtoan_ko_dchieu)){%>
                        <input type="hidden" name="ket_qua" value="0" id="ket_qua_pht"/>
                        <input type="hidden" name="ket_qua" value="0" id="ket_qua_ttsp"/>
                      <%}%>
                  <%}%>
                  </logic:empty>              
                  
                  <logic:notEmpty name="colPHT">
                  <logic:iterate id="items" name="colPHT">
                  <tr>
                    <td colspan="7"> <b>PHT:</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                     
                        <logic:equal name="items" property="ket_qua" value="0">
                           <font style="FONT-WEIGHT: bold; color:blue"><fmt:message key="doi_chieu.page.lable.pht.0"/></font>
                        </logic:equal>
                       <font style="FONT-WEIGHT: bold; color:red">
                        <logic:equal name="items" property="ket_qua" value="1">
                          <fmt:message key="doi_chieu.page.lable.pht.1"/>
                        </logic:equal>
                        <logic:equal name="items" property="ket_qua" value="2">
                          <fmt:message key="doi_chieu.page.lable.pht.2"/>
                        </logic:equal>
                        <html:hidden property="ket_qua" name="items" styleId="ket_qua_pht"/>
                        <html:hidden property="id" name="items"  styleId="pht_id"/>
                        <input type="hidden" name="pht_id" value="<bean:write property="id"  name="items"/>"/>
                      </font>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="">
                     <b>-<fmt:message key="doi_chieu.page.label.dc1.dlthu"/></b>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      + <fmt:message key="doi_chieu.page.label.dc1.dldtu"/>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="tong_mon_pht"/>
                    </fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="tong_ps_pht" />
                    </fmt:formatNumber>
                    </td>
                    <td align="center">
                     <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                     <bean:write name="items" property="mon_thu_dtu_kbnn" />
                     </fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="tien_thu_dtu_kbnn"/>
                      </fmt:formatNumber>
                    </td>
                    <td align="center">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="chenh_mthu_dtu_pht" />
                    </fmt:formatNumber>
                    </td>
                    <td align="right">
                      <fmt:setLocale value="vi_VI"/>
                  <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                      <bean:write name="items" property="chenh_tthu_dtu_pht" />
                    </fmt:formatNumber>
                    </td>
                  </tr>                  
                  <html:hidden property="ket_qua" name="items"/>
                        <html:hidden property="trang_thai_bkdc" name="items"/>
                        <html:hidden property="ket_qua_pht" name="items"/>
                        <html:hidden property="receive_bank" name="items"/>
                    
                    <% if(notTTSP != null && ""!=notTTSP){
                    %>
                    <input type="hidden" name="ket_qua" value="0" id="ket_qua_ttsp"/>            
                    <%}%>
                    <% if(notPHT != null && ""!=notPHT){
                    %>
                    <input type="hidden" name="ket_qua" value="0" id="ket_qua_pht"/>            
                    <%}%>
                  </logic:iterate>
                  </logic:notEmpty>
                  <!--20171009 thuongdt bo sung du lieu thu doi voi ngay nghi start-->  
                  <logic:notEmpty name="colPHT_T7">
                  <logic:iterate id="items" name="colPHT_T7">
                      <tr>
                          <td>
                            + Dữ liệu thu(thứ 7)
                          </td>
                          <td align="center">
                            <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                            <bean:write name="items" property="tong_mon_pht"/>
                          </fmt:formatNumber>
                          </td>
                          <td align="right">
                            <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                            <bean:write name="items" property="tong_ps_pht" />
                          </fmt:formatNumber>
                          </td>
                          <td align="center">
                           <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                           <bean:write name="items" property="mon_thu_dtu_kbnn" />
                           </fmt:formatNumber>
                          </td>
                          <td align="right">
                            <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                            <bean:write name="items" property="tien_thu_dtu_kbnn"/>
                            </fmt:formatNumber>
                          </td>
                          <td align="center">
                            <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                            <bean:write name="items" property="chenh_mthu_dtu_pht" />
                          </fmt:formatNumber>
                          </td>
                          <td align="right">
                            <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                            <bean:write name="items" property="chenh_tthu_dtu_pht" />
                          </fmt:formatNumber>
                          </td>
                        </tr>
                  </logic:iterate>
                  </logic:notEmpty>
                   <!--20171009 thuongdt bo sung du lieu thu doi voi ngay nghi start-->
              </table>
              <br/>
               <fieldset>
               <div>
              <table width="100%" cellspacing="0" cellpadding="2"
                           bordercolor="#e1e1e1" border="0" align="center"
                           style="BORDER-COLLAPSE: collapse">
                    <logic:notEmpty name="colGDTCong">
                        <logic:iterate id="items" name="colGDTCong">
                        <tr>
                          <td colspan="4"> <b>Giao dịch thủ công </b> </td>
                        </tr>
                        <!--20171009 thuongdt bo sung them lai chuyen thu start -->
                      <tr> 
                        <td width="25%" align="left">
                             Số thu thủ công
                        </td>
                         <td width="25%" align="right">                                       
                          <input type="text" title="<bean:write name="items" property="so_thu_chu"/> đồng"  name="so_thu" disabled="disabled"  id="so_thu"  value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="so_thu"/></fmt:formatNumber>" class="fieldTextRight" />
                         
                         </td>
                          <%if(loai_gd.equals("03")){%>
                         <td width="20%" align="left" style="padding-left:15px">
                             Lãi chuyên thu
                        </td>
                         <td  align="center" colspan="2">
                          <input type="text" title="<bean:write name="items" property="lai_chuyen_thu_chu"/> đồng"  name="so_lai_thu" disabled="disabled"  id="so_lai_thu"  value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="lai_chuyen_thu"/></fmt:formatNumber>" class="fieldTextRight" />
                         </td>
                         <%}else{%>
                           <td width="20%" align="left" style="padding-left:15px">
                              
                          </td>
                           <td  align="center" colspan="2">
                            
                           </td>
                         <%}%>
                      </tr>
                      <tr>
                        <td  align="left">
                            Số chi thủ công
                          </td>
                          <td  align="right"> 
                          <input type="text"  name="so_chi"  title="<bean:write name="items" property="so_chi_chu"/> đồng"  disabled="disabled"  id="so_chi"   value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="so_chi"/></fmt:formatNumber>" class="fieldTextRight" />                                   
                          <html:hidden property="ttsp_id"/>
                         </td>
                          <td  align="center" colspan="2">
                          <%if(chkdate==null || "".equals(chkdate)){%>
                           <button type="button"  accesskey="t" id="bt_tcong" onclick="update_TCong('TCONG')">
                              Cập nhật số thu chi <span class="sortKey">t</span>hủ công
                            </button>
                          <%}%>
                            <html:hidden property="cho_phep_sua" styleId="cho_phep_sua" />
                         </td>
                      </tr>
                       <!--20171009 thuongdt bo sung them lai chuyen thu end -->
                      </logic:iterate>
                      </logic:notEmpty>
                      
                      <logic:empty name="colGDTCong"> 
                                        
                      <tr>
                          <td colspan="4"> <b>Giao dịch thủ công </b> </td>
                        </tr>
                      <tr> 
                        <td width="25%" align="left">
                             Số thu thủ công
                        </td>
                         <td width="25%" align="right">                                       
                          <input type="text"  name="so_thu" disabled="disabled"  id="so_thu"  value="" class="fieldTextRight" />
                         </td>
                        <%if(loai_gd.equals("03")){%>
                         <td width="20%" align="left" style="padding-left:15px">
                             Lãi chuyên thu
                        </td>
                         <td width="25%" align="right">                                       
                          <input type="text"  name="so_lai_thu" disabled="disabled"  id="so_lai_thu"  value="" class="fieldTextRight" />
                         </td>
                         <%}else{%>
                           <td width="20%" align="left" style="padding-left:15px">                              
                          </td>
                           <td width="25%" align="right"> 
                           </td>
                         
                         <%}%>
                      </tr>
                      <tr>
                        <td  align="left">
                            Số chi thủ công
                          </td>
                          <td  align="right" > 
                          <input type="text"  name="so_chi" disabled="disabled"  id="so_chi"   value="" class="fieldTextRight" />                                   
                          <html:hidden property="ttsp_id"/>
                         </td>  
                         <td  align="center" colspan="2">
                           <button type="button"  accesskey="t" id="bt_tcong" onclick="update_TCong('TCONG')">
                              Cập nhật <span class="sortKey">t</span>hủ công
                            </button>
                         </td>
                      </tr>
                      </logic:empty>
                      
                      <logic:notEmpty name="colTHDC">
                        <logic:iterate id="items" name="colTHDC">
                        <tr>
                          <td colspan="4">
                          <b>Số dư tại thời điểm Cut-off-time:
                            <fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="sodu_kbnn"/></fmt:formatNumber></b> 
                          </td>
                        </tr>
                      <tr> 
                        <td width="25%" align="left">
                             Số đề nghị QT thu
                             <%if(size!="0" && !"0".equals(size)&&!"".equals(size)&&size!=null){%>
                               còn lại
                             <%}%>
                        </td>
                         <td width="25%" align="right">                                       
                          <input type="text"  name="txt_thu_tcong" disabled="disabled"  id="txt_thu_tcong" onkeyup="checkKey('txt_thu_tcong')"  value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="ket_chuyen_thu"/></fmt:formatNumber>" class="fieldTextRight" />
                          <input type="hidden"  name="qtoan_thu" id="qtoan_thu"  value="<bean:write name="items" property="ket_chuyen_thu"/>"/>  
                         </td>
                         <!--<td  align="center" rowspan="2">
                           <button type="button"  accesskey="t" id="bt_update" onclick="update_TCong()">
                              <span class="sortKey">S</span>ửa
                            </button>
                            <html:hidden property="cho_phep_sua" styleId="cho_phep_sua" />
                         </td>-->
                        <td  align="left">
                            Số đề nghị QT chi
                            <%if(size!="0" && !"0".equals(size)&&!"".equals(size)&&size!=null){%>
                               còn lại
                             <%}%>
                          </td>
                          <td  align="right"> 
                          <input type="text"  name="txt_chi_tcong" disabled="disabled"  id="txt_chi_tcong" onkeyup="checkKey('txt_chi_tcong')"   value="<fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""><bean:write name="items" property="ket_chuyen_chi"/></fmt:formatNumber>" class="fieldTextRight" />                                   
                          <input type="hidden"  name="qtoan_chi" id="qtoan_chi"  value="<bean:write name="items" property="ket_chuyen_chi"/>"/>                                   
                          <html:hidden property="ttsp_id"/>
                         </td>            
                      </tr>
                      <tr>
                        <td  align="left">
                           Ghi chú
                          </td>
                          <td  align="left" colspan="3"> 
                          <input type="text"  name="txt_noi_dung" disabled="disabled"  id="txt_noi_dung"  value="" class="fieldTextRight" />                                   
                         </td>            
                      </tr>
                      </logic:iterate>
                    </logic:notEmpty>
                    
                      <logic:empty name="colTHDC">
                        <tr>
                          <td colspan="4">
                          <b>Số dư tại thời điểm Cut-off-time: 
                            <fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol=""> </fmt:formatNumber></b> 
                          </td>
                        </tr>
                      <tr> 
                        <td width="25%" align="left">
                             Số đề nghị QT thu
                             <%if(size!="0" && !"0".equals(size)&&!"".equals(size)&&size!=null){%>
                               còn lại
                             <%}%>
                        </td>
                         <td width="25%" align="right">                                       
                          <input type="text"  name="txt_thu_tcong" disabled="disabled"  id="txt_thu_tcong" onkeyup="checkKey('txt_thu_tcong')"  class="fieldTextRight" />
                          <input type="hidden"  name="qtoan_thu" id="qtoan_thu"   />  
                         </td>
                         <!--<td  align="center" rowspan="2">
                           <button type="button"  accesskey="t" id="bt_update" onclick="update_TCong()">
                              <span class="sortKey">S</span>ửa
                            </button>
                            <html:hidden property="cho_phep_sua" styleId="cho_phep_sua" />
                         </td>-->
                        <td  align="left">
                            Số đề nghị QT chi
                            <%if(size!="0" && !"0".equals(size)&&!"".equals(size)&&size!=null){%>
                               còn lại
                             <%}%>
                          </td>
                          <td  align="right"> 
                          <input type="text"  name="txt_chi_tcong" disabled="disabled"  id="txt_chi_tcong" onkeyup="checkKey('txt_chi_tcong')"    class="fieldTextRight" />                                   
                          <input type="hidden"  name="qtoan_chi" id="qtoan_chi" />                                   
                          <html:hidden property="ttsp_id"/>
                         </td>            
                      </tr>
                      <tr>
                        <td  align="left">
                           Ghi chú
                          </td>
                          <td  align="left" colspan="3"> 
                          <input type="text"  name="txt_noi_dung" disabled="disabled"  id="txt_noi_dung"  value="" class="fieldTextRight" />                                   
                         </td>            
                      </tr>
                    </logic:empty>
                </table>                
            </div>
            </fieldset>
            
             </div>
            <!-- 
            <logic:equal name="XNDCTHop1Form" property="lai_phi_chuyen_thu" value="CO">
                <i><font color="Blue">Chú ý: Ngân hàng đã gửi báo lãi ngày <bean:write property="ngay_dc"  name="XNDCTHop1Form"/>,hãy liên hệ với KBNN TW để thực hiện nhập thủ công vào hệ thống.</font></i>
             </logic:equal>
             -->
            </fieldset>
       </td>
      </tr>
      <tr > 
      <td align="right" colspan="5">
          <%if(chkdate==null || "".equals(chkdate)){%>
         <button type="button"  accesskey="l" id="bt_update" onclick="update_TCong('TAM')">
            <span class="sortKey">L</span>ập mới
          </button>
          <html:hidden property="cho_phep_sua" styleId="cho_phep_sua" />
          <html:hidden property="cho_phep_qtoan_tam" value="" styleId="cho_phep_qtoan_tam" />
          <html:hidden property="cho_phep_nhap_tcong" value="" styleId="cho_phep_nhap_tcong" />
          <!--20170926 disabled button khi load man hinh-->
          <button type="button"  accesskey="t" onclick="check('create','')" id="bt"   disabled="disabled">
            <span class="sortKey">T</span>&#7841;o &#273;i&#7879;n đề nghị
          </button>
          <%}else{%>
          <!--20170926 disabled button khi load man hinh-->
          <button type="button"  accesskey="x" onclick="check('XN','')" id="bt" disabled="disabled">
            <span class="sortKey">X</span>ác nhận
          </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          <%}%>           
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
          <%if(notTTSP!=null&&"".equals(notTTSP)){%>
         <button type="button" onclick="check('print')" accesskey="i" id="inbt">
            <span class="sortKey">I</span>n KQĐC
          </button>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          <%}%>
           
          <button  type="button" onclick="check('close','')" accesskey="o">
            Th<span class="sortKey">o</span>&#225;t
          </button>
      </td>
    </tr>
        <tr>
    <td>
    <%
            if(request.getAttribute("err") != null){
            String StrStatus = request.getAttribute("err").toString();
         %>
            <font color="Red" dir="ltr">
                &nbsp;&nbsp;<%=StrStatus%>
            </font>
        <%}%>
    </td>
</tr>
    <tr>
        <td colspan="5" >
          <fieldset>
            <legend><font color="Blue">Danh sách đề nghị quyết toán</font></legend>
              <div width="50%" align="center">              
                <table cellspacing="0" width="100%" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse;table-layout:fixed">       
                    <thead >
                        <th bgcolor="#f0f0f0" align="center" width="3%">
                            STT
                         </th>
                         <th bgcolor="#f0f0f0" align="center" width="7%">
                            Ngày QT
                         </th>
                          <th  bgcolor="#f0f0f0" align="center"  width="11%"> 
                          Số điện
                         </th>
                         <th  bgcolor="#f0f0f0" align="center"  width="12%"> 
                          QT thu
                         </th>                                 
                        <th  bgcolor="#f0f0f0"  align="center" width="12%">
                            QT chi
                          </th>
                          <th  bgcolor="#f0f0f0" align="center" width="6%">                                   
                            Loại QT
                         </th>
                         <th  bgcolor="#f0f0f0" align="center" width="19%">                                   
                            Ghi chú
                         </th>
                         <th  bgcolor="#f0f0f0" align="center" width="6%">                                   
                           Trạng thái
                         </th>
                         <th  bgcolor="#f0f0f0" align="center" width="19%">                                   
                           Mô tả
                         </th>
                         <th  bgcolor="#f0f0f0" align="center" width="5%">                                   
                         </th>
                      </thead>
                      <tbody class="navigateable focused"  cellspacing="0"   cellpadding="1" bordercolor="#e1e1e1" id="tbodyTTinTToan">    
                      <logic:notEmpty name="col066">
                        <logic:iterate id="items" name="col066" indexId="stt">
                        <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' id="066_row_qt_<bean:write name="stt"/>">
                          <td align="center">
                           <%=stt+1%>
                           
                          </td>
                          <td  align="center">
                            <bean:write name="items" property="ngay_qtoan"/>
                          </td>
                          <td align="center">
                            <bean:write name="items" property="id"/>
                          </td>
                          <td align="right">
                            <b><fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                            <bean:write name="items" property="qtoan_thu"/>
                             </fmt:formatNumber></b>
                            <input type="hidden" name="qtoan_thu" id="qtoan_thu_<bean:write name="stt"/>" value="<bean:write name="items" property="qtoan_thu"/>"/>
                          </td>
                          <td align="right">
                          <b><fmt:setLocale value="vi_VI"/><fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                            <bean:write name="items" property="qtoan_chi"/>
                            </fmt:formatNumber></b>
                            <input type="hidden" name="qtoan_chi" id="qtoan_chi_<bean:write name="stt"/>" value="<bean:write name="items" property="qtoan_chi"/>"/>
                          </td>
                          <td align="center">
                            <logic:equal value="01" property="loai_qtoan" name="items">
                               Tự động
                            </logic:equal>
                            <logic:equal value="02" property="loai_qtoan" name="items">
                               Lập mới
                            </logic:equal>
                          </td>
                            <td align="left" title="<bean:write name="items" property="ndung_qtoan"/>">
                              <div style="text-overflow:ellipsis;width:175px;white-space:nowrap;  overflow:hidden; font-size:12px">
                                 <bean:write name="items" property="ndung_qtoan"/>
                              </div>
                            </td>
                          <td align="center">
                            <logic:equal value="01" property="trang_thai" name="items">
                              Chờ duyệt
                            </logic:equal>
                            <logic:equal value="02" property="trang_thai" name="items">
                               Đã duyệt
                            </logic:equal>
                            <logic:equal value="03" property="trang_thai" name="items">
                               Hủy
                            </logic:equal>
                            <logic:equal value="04" property="trang_thai" name="items">
                               Gửi NH thành công
                            </logic:equal>
                            <logic:equal value="05" property="trang_thai" name="items">
                               Gửi NH không thành công
                            </logic:equal>
                            <logic:equal value="06" property="trang_thai" name="items">
                               Hết hiệu lực
                            </logic:equal>
                            <input type="hidden" name="trang_thai" id="trang_thai_<bean:write name="stt"/>" value="<bean:write name="items" property="trang_thai"/>"/>
                          </td>
                          <td align="left" title="<bean:write name="items" property="error_desc"/>">
                            <div style="text-overflow:ellipsis;width:175px;white-space:nowrap;  overflow:hidden; font-size:12px">
                              <bean:write name="items" property="error_code"/>-<bean:write name="items" property="error_desc"/>
                            </div>
                          </td>
                          <td>
                            <span id="refresh" onclick="chk_print('print','<bean:write name="items" property="id"/>','<bean:write name="items" property="kq_dxn_thop"/>')"  title="In" style="cursor:pointer;"><img src="<%=request.getContextPath()%>/styles/images/icon_print_small.png" /></span>
                          </td>
                        </tr>
                        <input type="hidden" name="idx" id="total_row" value="<bean:write name="stt"/>"/>
                        </logic:iterate>
                      </logic:notEmpty>
                </table>
              </div>
          </fieldset>
        </td>
    </tr>
    
</table>
    <!-- Dialog nhap so thu cong-->
      <div id="dialog-form-tcong" align="center" title="C&#7853;p nh&#7853;t ph&#225;t inh th&#7911; c&#244;ng">
        <p class="validateTips"></p>
            <table style="width:70%;height:100%;">
              <tr>
                <td width="25%">
                    <div style="padding-top:10px;">
                        <label style="padding-left:35px;">
                          S&#7889; ti&#7873;n thu
                        </label>
                    </div>
                </td>
                <td width="25%" align="left">
                    <div style="padding-top:10px;">
                      <input type="text" name="dia_txt_thu_tcong" style="width:50%" id="dia_txt_thu_tcong" onkeyup="checkKey('dia_txt_thu_tcong')"  class="fieldTextRight" />                                                       
                      <input type="hidden"  name="dia_id" id="dia_id"  class="fieldTextRight" />     
                      <input type="hidden"  name="dia_so_thu" id="dia_so_thu"/> 
                     </div>
                </td>
              </tr>
              <tr>
                <td>
                      <div style="padding-top:10px;">
                        <label style="padding-left:35px;">
                          S&#7889; ti&#7873;n chi
                        </label>
                      </div>
                </td>
                <td  align="left">
                    <div style="padding-top:10px;">
                      <input type="text" name="dia_txt_chi_tcong"  style="width:50%" id="dia_txt_chi_tcong" onkeyup="checkKey('dia_txt_chi_tcong')"  class="fieldTextRight" />                            
                      <input type="hidden"  name="dia_so_chi" id="dia_so_chi"/> 
                   </div>
                </td>             
              </tr>
             <tr>
                <td>
                      <div style="padding-top:10px;">
                        <label style="padding-left:35px;">
                          Lý do
                        </label>
                      </div>
                </td>
                <td>
                    <div style="padding-top:10px;">
                      <textarea cols="60" rows="3" name="dia_txt_noi_dung" id="dia_txt_noi_dung" ></textarea>
                   </div>
                </td>             
              </tr> 
            </table>            
      </div>
      <html:hidden property="noi_dung" styleId="noi_dung" />
  </html:form>
    <div id="dialog-confirm"
     title='<fmt:message key="doi_chieu.page.title.dialog_confirm"/>'>   
      <p class="validateTips">
      </p>
    </div>
    
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">
var f = document.forms[0];
function checkKey(txt_id){
  var e= window.event;
        var keyCode = e.keyCode || e.charCode, arrow = {
            backspace : 8, del : 46 ,left : 37, right : 39, up : 38, down : 40, enter : 13, esc : 27
        };
        var matches = /[^\-0-9]/g;
      jQuery('#'+txt_id+'').val(jQuery('#'+txt_id+'').val().replace(matches, ''),true);
        if(keyCode==arrow.enter){
          tien= jQuery('#'+txt_id+'').val().replace(/[.]/g,'');
          jQuery('#'+txt_id+'').val(toFormatNumber(tien.replace(/ /g,''),0,'.'));
        }else return false;
}

  disBt();
  function disBt(){
    var strRowSelected="<%=strRowSelected%>";
    if(strRowSelected!=null && '' != strRowSelected){
      var chkdate = "<%=chkdate%>";
//      var qtoan_ko_dchieu="<%=qtoan_ko_dchieu%>";
      stt= strRowSelected.substr(7,5);
      sttNext=parseInt(stt);
      tthai_dxn=document.getElementById("tthai_"+sttNext).value;
      tthai_kq=document.getElementById("tthaikq_"+sttNext).value;
      //thuongdt-20170516 them check trang thai tt=02, tt dxn 00,01 cho phep xac nhan
      if(tthai_kq =='02' &&(tthai_dxn=='01' || tthai_dxn=='00')){
           document.getElementById("bt").disabled=false;
      }else if(tthai_dxn=='01'||tthai_kq!='02'){
          //Them cau if duoi day de khi tthai_kq==00 thi bt sang
          if(tthai_kq=='00'){
            document.getElementById("bt").disabled=false;
          }
      }else if (tthai_kq=='00'){     
        if(tthai_dxn=='00'||tthai_dxn==null || ''==tthai_dxn){
          document.getElementById("bt").disabled=false;
          document.getElementById("inbt").disabled=true;
          }
      }else if (tthai_dxn=='02'||tthai_dxn=='03'){
            
            document.getElementById("bt_tcong").disabled=true;
      }
      if(chkdate!=null && ""!=chkdate){
        document.getElementById("bt_update").disabled=true; 
        document.getElementById("bt_tcong").disabled=true;
      }
    }else{
          document.getElementById("bt").disabled=true;
          document.getElementById("inbt").disabled=true;
      }
  }

function update_TCong(type) {
//var qtoan_ko_dchieu="<%=qtoan_ko_dchieu%>";

var chkdate="<%=chkdate%>";
    var strRowSelected="<%=strRowSelected%>";
      stt= strRowSelected.substr(7,5);
      sttNext=parseInt(stt);
      trang_thai=document.getElementById("tthai_"+sttNext).value;
            jQuery.ajax( {
                type : "POST", url : "updateTCongAction.do", data :  { "loai_ts" : type     
                },   
                success : function (data, textstatus) {   
                    if (textstatus != null && textstatus == 'success') {
                        if (data != null) {    
                            jQuery.each(data, function (i, objectDC) { 
                            
                            jQuery("#cho_phep_sua").val(objectDC.giatri_ts);
                           
                            if(type=='TAM'){
                                  if(objectDC.giatri_ts=='Y'){
//                                  jQuery("#txt_chi_tcong").removeAttr('disabled');
//                                  jQuery("#txt_thu_tcong").removeAttr('disabled');
//                                  jQuery("#txt_noi_dung").removeAttr('disabled');
                                  jQuery('#txt_thu_tcong').focus();
                                  jQuery("#cho_phep_qtoan_tam").val(objectDC.giatri_ts);
                                  jQuery("#qtoan_ko_dchieu").val(objectDC.giatri_ts);
                                  jQuery("#bt").removeAttr('disabled'); 
                                  }else{
                                    alert(GetUnicode('Liên hệ với trung ương khi cần quyết toán tạm'));
                                    jQuery("#txt_chi_tcong").attr("disabled", "disabled");
                                    jQuery("#txt_thu_tcong").attr("disabled", "disabled"); 
              //                      jQuery("#bt").attr("disabled", "disabled");
                                    return false
                                  }  
                            }else if(type=='TCONG'){
                              if(objectDC.giatri_ts=='Y' && (chkdate==null || ''==chkdate) && (trang_thai=='00'||""==trang_thai||trang_thai==null)){
                                    jQuery("#cho_phep_nhap_tcong").val(objectDC.giatri_ts);
                                    jQuery("#dialog-form-tcong").dialog( "open" );  
                                jQuery("#bt").removeAttr('disabled'); 
                                }else if(objectDC.giatri_ts=='Y' &&((trang_thai=='01'||trang_thai=='02'||trang_thai=='03'||trang_thai=='04')||(chkdate!=null && ''!=chkdate))){
                                    alert(GetUnicode('Điện đã được xử lý. Không thể nhập số thủ công.'));
                                  return false 
                                }else {
                                  alert(GetUnicode('Liên hệ với trung ương khi cần nhập thủ công'));
                                  return false
                                } 
                            }
                            });
                        }
                    }
                }, 
                error : function (textstatus) {
                    alert(textstatus);
                }
            });
}

  sumSelected();
function sumSelected(){   
        var valThu = [];
        var valChi = [];
        var tong_thu=0;
        var tong_chi=0;
        var size="<%=size%>";
        if(''!=size && size !=null&&'0'!=size && size >0){
            for (i=0;i<size;i++){
            var trang_thai=jQuery('#trang_thai_'+i).val();
                if(trang_thai=='03' || '03'==trang_thai){
                  valThu[i] = 0;
                  var thu= valThu[i].toString();
                  valChi[i] = 0;
                  var chi= valChi[i].toString();
                }else{
                  valThu[i] = jQuery('#qtoan_thu_'+i).val();
                  var thu= valThu[i].toString();
                  valChi[i] = jQuery('#qtoan_chi_'+i).val();
                  var chi= valChi[i].toString();
                }
              
               tong_thu=tong_thu+parseInt(thu);
               tong_chi=tong_chi+parseInt(chi);
               
            }
          if(jQuery('#trang_thai_0').val()=='03'||'03'==jQuery('#trang_thai_0').val()){
            document.getElementById("bt").disabled=false
          }        
        txt_thu_tcong = parseInt(jQuery('#qtoan_thu').val()) - tong_thu;
        txt_chi_tcong = parseInt(jQuery('#qtoan_chi').val()) - tong_chi;
 //       alert('thuongdt test:'+jQuery('#qtoan_chi').val()+' ; '+tong_chi)
        
//        alert(tong_thu+'--'+tong_chi)
//        alert('thuongdt test:'+txt_thu_tcong+'--'+txt_chi_tcong)
        jQuery('#txt_thu_tcong').val(toFormatNumber(txt_thu_tcong,0,'.'));
        jQuery('#txt_chi_tcong').val(toFormatNumber(txt_chi_tcong,0,'.'));
        jQuery('#qtoan_thu').val(txt_thu_tcong);
        jQuery('#qtoan_chi').val(txt_chi_tcong);
//        alert(jQuery('#qtoan_thu').val()+'----'+jQuery('#qtoan_chi').val());
        }
  } 
 
function fncUpdateTCong(){
//      jQuery("#dia_txt_thu_tcong").val(toFormatNumber(thu_tcong,0,'.'));
//      jQuery("#dia_txt_chi_tcong").val(toFormatNumber(chi_tcong,0,'.'));
//      jQuery("#dia_so_thu").val(thu_tcong);
//      jQuery("#dia_so_chi").val(chi_tcong);
//      jQuery("#dia_id").val(ttsp_id);
      jQuery("#dialog-form-tcong").dialog( "open" );    
    } 
 function fmt_money(tien_type,tien){
  tien=tien.replace(/[.]/g,'');
//  alert(tien.replace(/ /g,''));
  jQuery('#'+tien_type+'').val(toFormatNumber(tien.replace(/ /g,''),0,'.'));
}

  function check(type,bk_id) { 
  var strRowSelected="<%=strRowSelected%>";
//  var qtoan_ko_dchieu="<%=qtoan_ko_dchieu%>";
  var notTTSP="<%=notTTSP%>";
  var notPHT="<%=notPHT%>";
  var qtoan_ko_dchieu=document.getElementById("qtoan_ko_dchieu").value;
        if (type == 'create') {
         var txt_thu_tcong=  jQuery('#txt_thu_tcong').val();
          var txt_chi_tcong=    jQuery('#txt_chi_tcong').val();
          var vqtoan_thu = jQuery('#qtoan_thu').val();
          var vqtoan_chi=    jQuery('#qtoan_chi').val();
          stt= strRowSelected.substr(7,5);
          sttNext=parseInt(stt);
          trang_thai=document.getElementById("tthai_"+sttNext).value;
           var ttsp_id = document.getElementById("idx_"+sttNext).value;
          if(notTTSP!=null && ''!=notTTSP){
            var kq_ttsp ='0';
            var kq_pht=document.getElementById("ket_qua_pht").value;      
          }
          if(notPHT!=null && ''!=notPHT){
             kq_pht ='0';       
             kq_ttsp=jQuery('#ket_qua_ttsp').val(); 
          }else if ((notPHT==null || ''==notPHT)&&(notTTSP==null || ''==notTTSP)){
             kq_ttsp=jQuery('#ket_qua_ttsp').val();          
             kq_pht=jQuery('#ket_qua_pht').val();
          }      
           ngay_dc=document.getElementById("ngay_dc_idx_"+sttNext).value;
                    if(strRowSelected!=null && '' != strRowSelected){
                          if('Y'==qtoan_ko_dchieu || qtoan_ko_dchieu=='Y'){
                           var receive_bank = jQuery('#receive_bank_idx_'+sttNext).val();
                          
                            if(txt_thu_tcong==null || ''==txt_thu_tcong){
                              alert(GetUnicode('Cần nhập thông tin quyết toán thu'));
                              jQuery('#txt_thu_tcong').focus();
                              return false;
                            }else if(txt_chi_tcong==null || ''==txt_chi_tcong){
                              alert(GetUnicode('Cần nhập thông tin quyết toán chi'));
                              jQuery('#txt_chi_tcong').focus();
                              return false;
                            }
                            //20170926 - kiem tra so qtoan bang 0 canh bao nsd kiem tra lai
                           if(vqtoan_thu==null || vqtoan_thu == '0' ){
                              if(!confirm("Số ĐNQT thu bằng 0, bạn có chắc chắn số liệu là đúng?"))
                                return false;
                           }
                            document.getElementById("bt").disabled=true;
                            f.action = 'TaoDXNDCTHop1Action.do?rowSelected='+strRowSelected+"&type=C&ngay_dc="+ngay_dc+"&receive_bank="+receive_bank+"&loai_gd="+notTTSP+"&loai_kq066="+kq_ttsp+kq_pht+"&tthai_dxn_thop="+trang_thai+"&ttsp_id="+ttsp_id;
                                
                          }else{
                          tthai_kq=document.getElementById("tthaikq_"+sttNext).value;
                          if(tthai_kq!='02'){
                            alert(GetUnicode('Kế toán trưởng chưa duyệt đối chiếu truyền tin'));
                                  return false;
                          }else if( kq_ttsp !='0'){
                                alert(GetUnicode('Chưa nhận được kết quả khớp đúng từ TTSP'));
                                  return false;
                          }else if( kq_pht!='0'){
                                alert(GetUnicode('Chưa nhận được kết quả khớp đúng từ PHT'));
                                  return false;
                          }else if ( kq_ttsp =='0' && kq_pht=='0'){
                            if(txt_thu_tcong==null || ''==txt_thu_tcong){
                              alert(GetUnicode('Cần nhập thông tin quyết toán thu'));
                              jQuery('#txt_thu_tcong').forcus();
                              return false;
                            }else if(txt_chi_tcong==null || ''==txt_chi_tcong){
                              alert(GetUnicode('Cần nhập thông tin quyết toán chi'));
                              jQuery('#txt_chi_tcong').forcus();
                              return false;
                            }
                          //20170926 canh bao nsd khi DQNT thu la 0
                           if(vqtoan_thu==null || vqtoan_thu == '0' ){
                              if(!confirm("Số ĐNQT thu bằng 0, bạn có chắc chắn số liệu là đúng?"))
                                return false;
                           }
                            document.getElementById("bt").disabled=true;
                                f.action = 'TaoDXNDCTHop1Action.do?rowSelected='+strRowSelected+"&type=C&ngay_dc="+ngay_dc+"&loai_gd="+notTTSP+"&loai_kq066="+kq_ttsp+kq_pht+"&tthai_dxn_thop="+trang_thai+"&ttsp_id="+ttsp_id;
                               
//                                }
                                }
                            }
                    }
                    }
        if (type == 'XN') {
              stt= strRowSelected.substr(7,5);
                sttNext=parseInt(stt);                
                 ngay_dc=document.getElementById("ngay_dc_idx_"+sttNext).value;
                   kq_pht= document.getElementById("ket_qua_pht").value;
                   kq_ttsp = document.getElementById("ket_qua_ttsp").value;
                   ttsp_id = document.getElementById("idx_"+sttNext).value;
                    if(strRowSelected!=null && '' != strRowSelected){
                          stt= strRowSelected.substr(7,5);
                          sttNext=parseInt(stt);
                          tthai_kq=document.getElementById("tthaikq_"+sttNext).value;
                          if(tthai_kq!='02'){
                            alert(GetUnicode('Kế toán trưởng chưa duyệt đối chiếu truyền tin'));
                                  return false;
                          }else if( kq_ttsp !='0'){
                                alert(GetUnicode('Chưa nhận được kết quả khớp đúng từ TTSP'));
                                  return false;
                          }else if( kq_pht!='0'){
                                alert(GetUnicode('Chưa nhận được kết quả khớp đúng từ PHT'));
                                  return false;
                          }else if ( kq_ttsp =='0' && kq_pht=='0'){                      
                          document.getElementById("bt").disabled=true;
                                f.action = 'TaoDXNDCTHop1Action.do?rowSelected='+strRowSelected+"&type=XN&ngay_dc="+ngay_dc+"&loai_gd="+notTTSP+"&ttsp_id="+ttsp_id;;
                            }
                    }
        }
      if (type == 'print') {
       var ttsp_id = document.getElementById("idx_"+sttNext).value;
          if(notTTSP!=null && ''!=notTTSP){
            var kq_ttsp ='0';
            var kq_pht=jQuery('#ket_qua_pht').val();
          }
          if(notPHT!=null && ''!=notPHT){
             kq_pht ='0';
             kq_ttsp=jQuery('#ket_qua_ttsp').val(); 
          }else{
             kq_ttsp=jQuery('#ket_qua_ttsp').val();          
             kq_pht=jQuery('#ket_qua_pht').val();
          }
          if(ttsp_id==null||''==ttsp_id){
            alert(GetUnicode('Chưa đầy đủ thông tin để thực hiện.'));
            return false;
          }
        f.action = 'PrintXNDCTHop1Action.do?type=065&ket_qua_pht='+kq_pht+"&ket_qua_ttsp="+kq_ttsp+'&ttsp_id='+ttsp_id;    
       var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';      
      }
     if (type == 'close') {
        f.action = 'mainAction.do';          
      } 
       f.submit();
  }
  function chk_print(type,bk_id,kq_dxn_Thop) {
    if (type == 'print') {
      
        f.action = 'PrintXNDCTHop1Action.do?type=066&id_066='+bk_id+"&loai_kq066="+kq_dxn_Thop;    
       var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';      
      }
      f.submit();
  }
//  function formatNumberVND(e) {
//    e.parseNumber({ format: "#,##0", locale: "us" });
//    e.formatNumber({ format: "#,##0", locale: "us" });
//}
//20170926 bo sung them ham disible tat ca cac button
function disBt_onclick(){
  try
    { 
      var vbt = document.getElementById("bt");
      var vinbt = document.getElementById("inbt");
      var vbt_update = document.getElementById("bt_update");
      var vbt_tcong = document.getElementById("bt_tcong");
      if(vbt != null)
       document.getElementById("bt").disabled=true;
      if(vbt_update != null)
        document.getElementById("bt_update").disabled=true;
      if(vbt_tcong != null)
       document.getElementById("bt_tcong").disabled=true;
       if(vinbt != null)
       document.getElementById("inbt").disabled=true;
   }
    catch(error)
    {      
        return false;
    }
    return true;
}
</script>

`