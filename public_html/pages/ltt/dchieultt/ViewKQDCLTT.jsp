<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.seatech.ttsp.ltt.dchieultt.LTT_KQ_DChieuVO"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
 <script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"></script>
       
<%@ page import="com.seatech.framework.common.jsp.PagingBean"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.DoichieuResource"/>
<%
  String strRowSelected = request.getAttribute("rowSelected")==null?"":request.getAttribute("rowSelected").toString();
  String notPHT = request.getAttribute("notPHT")==null?"":request.getAttribute("notPHT").toString();
  String notTTSP = request.getAttribute("notTTSP")==null?"":request.getAttribute("notTTSP").toString();
  String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
  String size = request.getAttribute("size")==null?"":request.getAttribute("size").toString();
  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String idHolder = request.getAttribute("id")==null?"":request.getAttribute("id").toString();
  String ngayDcHolder = request.getAttribute("ngay_dc")==null?"":request.getAttribute("ngay_dc").toString();
  String trang_thaiHolder = request.getAttribute("trang_thai")==null?"":request.getAttribute("trang_thai").toString();
%>
<script type="text/javascript">
  jQuery.noConflict();
  //************************************ LOAD PAGE **********************************
  jQuery(document).init(function () {
    //defaultStateFormBK();
    var strRowSelected="<%=strRowSelected%>";
    if(strRowSelected!=null && '' != strRowSelected){
    rowSelectedFocusXNDC(strRowSelected);
    }
  });
  //************************************Doi Chieu*************************************
  function fillDataDCLTT(act,trang_thai,id,ngay_dc,ma_kb,shkb, tr_id) {
    document.forms[0].action= act +"?trang_thai="+trang_thai+"&id="+id+"&shkb="+shkb+"&ma_nguon="+ma_kb+ "&ngay_dc="+ngay_dc+"&rowSelected=" + tr_id;
    document.forms[0].submit();
    }  
    function pickRow(rowNum,trang_thai,action,id,ngay_dc,ma_kb,shkb){
          
           rowSelectedFocusDC(rowNum);
           disbtn(trang_thai);
           fillDataDCLTT(action,trang_thai,id,ngay_dc,ma_kb,shkb,rowNum);
}
//Xac nhan chenh lech dung.
  function confirm(){
    var id_bk ="<%=idHolder%>";
    var ly_do =  document.getElementById('ly_do').value;;
    if (ly_do==null ||ly_do==""){
      alert('Cần nhập lý do');
      return;
    }else
    if (id_bk != null || id_bk!=""){
      jQuery.ajax({
        type: "POST", url:"XacNhanChenhLech.do", data:{
          action:"XacNhanChenhLech", id_bk:id_bk,ly_do:ly_do, "nd": Math.random() * 100000
        }, success : function (data,textstatus){
        if (textstatus !=null && textstatus =='success'){
          alert ("Xác nhận thành công");
             document.getElementById('tdTrang_thai').innerHTML = "Xác nhận - Đã xử lý";
             document.getElementById('inTrang_thai').value='Xác nhận - Đã xử lý';
            document.getElementById("xnbt").disabled=true;
            jQuery("#dialog-form-confirm").dialog( "close" ); 
          if (data!=null){
              if (data.logout != null && data.logout){
              document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
              document.forms[0].submit();
            }
          }else{
          
          }
        }
        }, error: function (textstatus){
          alert(textstatus);
        }
      })
    }
  }
  //Function doi chieu bang ke
  function doichieu(){
    var id_bk ="<%=idHolder%>";
    var ngayDC= "<%=ngayDcHolder%>";
    document.forms[0].action= "DoiChieuLTT.do?id_bk="+id_bk+"&ngayDC="+ngayDC;
    document.forms[0].submit();
  }
  //Function cho button xac nhan
  function xacnhan(){
  var id_bk ="<%=idHolder%>";
   var lydo =  document.getElementsByName('iplydo')[0].value;  
    if (lydo==null ||lydo==""){
      alert('Cần nhập lý do');
    }else {
      document.forms[0].lydo.value= lydo;
      document.forms[0].id.value=id_bk;

      document.forms[0].action= "XacNhanChenhLech.do";
     document.forms[0].submit();
    }
  }
  
 var f = document.forms[0];
 jQuery(document).init(function () {

  
  var strRowSelected="<%=strRowSelected%>";
    if ('row_qt_0'==strRowSelected ){
      var tthai= document.getElementById('trang_thai').value;
      disbtn(tthai);
    }else{
      var tthai1= document.getElementById('trang_thai').value;
       disbtn(tthai1);
    }

});



</script>
<%
String loai_tthai ="";
String thua_di_tab = "0";
String thua_di_sp = "0";
String thua_den_tab = "0";
String thua_den_sp = "0";
String qt_thua_den_tab = "0";
Collection colTHKQDC = (Collection)request.getAttribute("colTHKQDC");
LTT_KQ_DChieuVO kqVO = null;
if(colTHKQDC != null){
  Iterator itr = colTHKQDC.iterator();
  while(itr.hasNext()) { 
    kqVO = (LTT_KQ_DChieuVO)itr.next();
    loai_tthai = kqVO.getTrang_thai();
    if(kqVO.getLoai_lenh() != null && kqVO.getLoai_lenh().equals("DI") && kqVO.getChenh_lech().equals("TABMIS"))
      thua_di_tab = kqVO.getTonglenh();
    if(kqVO.getLoai_lenh() != null && kqVO.getLoai_lenh().equals("DI") && kqVO.getChenh_lech().equals("TTSP"))
      thua_di_sp = kqVO.getTonglenh();
    if(kqVO.getLoai_lenh() != null && kqVO.getLoai_lenh().equals("DEN") && kqVO.getChenh_lech().equals("TABMIS"))
      thua_den_tab = kqVO.getTonglenh();
    if(kqVO.getLoai_lenh() != null && kqVO.getLoai_lenh().equals("QT") && kqVO.getChenh_lech().equals("TTSP"))
      qt_thua_den_tab = kqVO.getTonglenh();
    if(kqVO.getLoai_lenh() != null && kqVO.getLoai_lenh().equals("DEN") && kqVO.getChenh_lech().equals("TTSP"))
      thua_den_sp = kqVO.getTonglenh();
  }
}


%>


<div class="app_error">
  <html:errors/>
</div>
<div class="box_common_conten">
  <html:form action="ViewKQDCLTT.do" method="post" >
  <c:set var="isNgoaiTe" value="${false}" scope="request"/>
  <logic:empty name="lttTabmisForm" property="ma_nt">
    <fmt:setLocale value="vi_VI"/>
  </logic:empty>
  <logic:equal value="VND" name="lttTabmisForm" property="ma_nt">
    <fmt:setLocale value="vi_VI"/>
  </logic:equal>
  <logic:notEqual value="VND" name="lttTabmisForm" property="ma_nt">
    <fmt:setLocale value="en_US"/>
    <c:set var="isNgoaiTe" value="${true}" scope="request"/>
  </logic:notEqual>
  <input type="hidden" name="lttTabmisForm" value="<bean:write name="lttTabmisForm" property="trang_thai"/>" id="trang_thai_hidden"/>
   <table border="0" cellspacing="0" cellpadding="0" width="100%"
           align="center">
      <tbody>
        <tr>
          <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width=13 height=30/></td>
          <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
            <span class=title2>Đối chiếu số LTT giữa TABMIS và TTSP</span>
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
       <td width="30%" align="center" colspan="4" >
        <fieldset>
        <legend><font color="Blue">Danh s&#225;ch b&#7843;ng k&#234;</font></legend>
        <div  style="height:180px;overflow-y: scroll;">
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="1"
                                             cellspacing="0" cellpadding="0" >
                 <tr>
                 
                 <td align="center" width="30%" class="ui-state-default ui-th-column">Kho bạc</td>
                 <td align="center" width="30%" class="ui-state-default ui-th-column">Ngày đối chiếu</td>                 
                 <td align="center" width="30%" class="ui-state-default ui-th-column">Trạng Thái</td>
                 </tr>
                 
               
                 
                 <logic:empty name="lstTraCuu">
                  <tr>
                    <td colspan="5">
                      <font color="Red">Kh&#244;ng c&#243; b&#7843;ng k&#234; &#273;&#7889;i chi&#7871;u</font>
                      <input type="hidden" name="ket_qua" value="" id="b"/>
                      <input type="hidden" name="ket_qua" value="" id="a"/>
                    </td>
                  </tr>
                </logic:empty>
               <logic:notEmpty name="lstTraCuu">
                  <logic:iterate id="UDlist" name="lstTraCuu" indexId="index">
                  <tr class="ui-widget-content jqgrow ui-row-ltr"
                      id="row_qt_<bean:write name="index"/>"
                      onclick="pickRow('row_qt_<bean:write name="index"/>','<bean:write name="UDlist" property="trang_thai"/>','ViewKQDCLTT.do','<bean:write name="UDlist" property="id"/>','<bean:write name="UDlist" property="ngay_dc"/>','<bean:write name="UDlist" property="ma_kb"/>','<bean:write name="UDlist" property="shkb"/>')">
                 <input type="hidden" name="UDlist" value="<bean:write name="UDlist" property="id"/>" id="id_bk"/>
                 <input type="hidden" name="UDlist" value="<bean:write name="UDlist" property="ngay_dc"/>" id="ngayDC"/>
                 <input type="hidden" name="UDlist" value="<bean:write name="UDlist" property="trang_thai"/>" id="trang_thai"/>
                   <!-- <td align="center">
                        <%=ngan_hang%>            
                   </td> -->
                   <td align="center">
                    <bean:write name="UDlist" property="ma_kb"/>            
                   </td>
                   <td align="center">
                    <bean:write name="UDlist" property="ngay_dc"/>
                   </td>
                   <td id="tdTrang_thai" align="center">                  
                      <logic:equal name="UDlist" property="trang_thai" value="01">Chênh lệch</logic:equal>
                      <logic:equal name="UDlist" property="trang_thai" value="00">Chưa đối chiếu</logic:equal>
                      <logic:equal name="UDlist" property="trang_thai" value="02">Khớp đúng</logic:equal>
                      <logic:equal name="UDlist" property="trang_thai" value="04">Xác nhận - Đã xử lý</logic:equal>
                      <logic:equal name="UDlist" property="trang_thai" value="03">Hủy</logic:equal> 
                   </td>               
                 </tr>
                  </logic:iterate>
                </logic:notEmpty>             
             </table>
           </div>
        </fieldset>
       </td>
       <td  width="70%">
        <fieldset>
            <legend><font color="Blue">Thông tin tổng hợp bảng kê</font></legend>
            <div title="" class="tabbertab   " style="height:180px;overflow-y: scroll;">
                 <table width="100%" cellspacing="0" cellpadding="2"
                 bordercolor="#e1e1e1" border="1" align="center" 
                 style="BORDER-COLLAPSE: collapse; text-align:center">
                 <tr>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:10%; text-align:center" rowspan="3">
                  Loại tiền
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:30%; text-align:center" align="center" colspan="2">
                  LTT đi <br/>
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:30%; text-align:center" align="center" colspan="2">
                  LTT đến <br/>
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:30%; text-align:center" align="center" colspan="6">
                  Lệnh quyết toán <br/>
                 </th>
                 </tr>
                 <tr>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center" rowspan="2">
                 Tổng lệnh
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center"  rowspan="2">
                 Tổng tiền
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center"  rowspan="2">
                 Tổng lệnh
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center"  rowspan="2">
                 Tổng tiền
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center" colspan="2">
                 Quyết toán thu
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center" colspan="2">
                 Quyết toán chi
                 </th>
                 
                  <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center">
                 CL tỷ giá
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center">
                 Lãi phí
                 </th>
                 </tr>
                 <tr>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center" >
                 Tổng lệnh
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center" >
                 Tổng tiền
                 </th>
                  <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center" >
                 Tổng lệnh
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center" >
                 Tổng tiền
                 </th>                 
                  <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center" >
                 Tổng lệnh
                 </th>
                 <th class="promptText" bgcolor="#f0f0f0" style="width:15%; text-align:center" align="center" >
                 Tổng lệnh
                 </th>
                 </tr>
                 <logic:notEmpty name="colTTTHBK">
                    <logic:iterate id="UDlist1" name="colTTTHBK" >
                    <tr>
                      <input type="hidden" value="<bean:write name="UDlist1" property="id"/>"/>
                      <th  class="promptText"  style="width:20; text-align:center">
                      <bean:write name="UDlist1" property="ma"/>
                      </th>
                      <td  class="promptText"  style="width:10%; text-align:center">
                      <bean:write name="UDlist1" property="tong_di"/>
                      </td>
                      <td  class="promptText"  style="width:10%; text-align:center">
                      <logic:equal value="VND" name="UDlist1" property="ma">
                        <fmt:setLocale value="vi_VI"/>
                         <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="UDlist1" property="so_tien_di"/>
                      </fmt:formatNumber> 
                      </logic:equal>
                      <logic:notEqual value="VND" name="UDlist1" property="ma">
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber maxFractionDigits="10"  type="currency"  currencySymbol="">
                        <bean:write name="UDlist1" property="so_tien_di"/>
                      </fmt:formatNumber> 
                      </logic:notEqual>
                     
                       
                      </td>
                      <td  class="promptText"  style="width:10%; text-align:center">
                      <bean:write name="UDlist1" property="tong_den"/>
                      </td>
                      <td  class="promptText"  style="width:10%; text-align:center">
                      <logic:equal value="VND" name="UDlist1" property="ma">
                        <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="UDlist1" property="so_tien_den"/>
                      </fmt:formatNumber> 
                      </logic:equal>
                      <logic:notEqual value="VND" name="UDlist1" property="ma">
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber maxFractionDigits="10"  type="currency"  currencySymbol="">
                        <bean:write name="UDlist1" property="so_tien_den"/>
                      </fmt:formatNumber> 
                      </logic:notEqual> 
                      </td>
                      
                      <td  class="promptText"  style="width:10%; text-align:center">
                      <bean:write name="UDlist1" property="tong_qtoan_thu"/>
                      </td>
                      <td  class="promptText"  style="width:10%; text-align:center">
                      <logic:equal value="VND" name="UDlist1" property="ma">
                        <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="UDlist1" property="so_tien_qtoan_thu"/>
                      </fmt:formatNumber> 
                      </logic:equal>
                      <logic:notEqual value="VND" name="UDlist1" property="ma">
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber maxFractionDigits="10"  type="currency"  currencySymbol="">
                        <bean:write name="UDlist1" property="so_tien_qtoan_thu"/>
                      </fmt:formatNumber> 
                      </logic:notEqual> 
                      </td> 
                      <td  class="promptText"  style="width:10%; text-align:center">
                      <bean:write name="UDlist1" property="tong_qtoan_chi"/>
                      </td>
                      <td  class="promptText"  style="width:10%; text-align:center">
                      <logic:equal value="VND" name="UDlist1" property="ma">
                        <fmt:setLocale value="vi_VI"/>
                        <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="UDlist1" property="so_tien_qtoan_chi"/>
                      </fmt:formatNumber> 
                      </logic:equal>
                      <logic:notEqual value="VND" name="UDlist1" property="ma">
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber maxFractionDigits="10"  type="currency"  currencySymbol="">
                        <bean:write name="UDlist1" property="so_tien_qtoan_chi"/>
                      </fmt:formatNumber> 
                      </logic:notEqual> 
                      </td>
                      
                      <td  class="promptText"  style="width:9%; text-align:center">
                      <bean:write name="UDlist1" property="tong_ty_gia"/>
                      </td>
                      <td  class="promptText"  style="width:9%; text-align:center">
                      <bean:write name="UDlist1" property="tong_lai_phi"/>
                      </td>
                    </tr>
                    </logic:iterate>
                  </logic:notEmpty>
                 </table> 
              <br/> 
             </div>
            </fieldset>
       </td>
      </tr>
      <tr>
        <td colspan="10">
        <fieldset>        
                <legend><font color="Blue">Tổng hợp kết quả đối chiếu</font></legend>
           
           <table style="BORDER-COLLAPSE: collapse" align="center" border="0" bordercolor="#e1e1e1" cellpadding="2" cellspacing="0" width="90%">
                 
             <tbody>
             <tr>
                  <td  width="25%"> Kết quả đối chiếu: </td>                 
                  <td width="25%" align="center">
                  <% if(loai_tthai.equals("01")){ %>                 
                  <input  id="inTrang_thai" type="text" name="ket_qua" style="border: 0;font-size:14px; width: 200px;color:red;FONT-WEIGHT: bold"  class="fieldText" value=" Chênh lệch" readonly="true"/>
                <% }else if(loai_tthai.equals("00")){%>                
                  <input id="inTrang_thai" type="text" name="ket_qua" style="border: 0;font-size:14px; width: 200px;color:red;FONT-WEIGHT: bold"  class="fieldText" value="Chưa đối chiếu" readonly="true"/>
                <% }else if(loai_tthai.equals("02")){%>
                   <input  id="inTrang_thai" type="text" name="ket_qua" style="border: 0;font-size:14px; width: 200px;color:blue;FONT-WEIGHT: bold"  class="fieldText" value="Khớp đúng" readonly="true"/>
                <% }else if(loai_tthai.equals("03")){%>
                   <input  id="inTrang_thai" type="text" name="ket_qua" style="border: 0;font-size:14px; width: 200px;color:red;FONT-WEIGHT: bold"  class="fieldText" value="Hủy" readonly="true"/>         
                <% }else if(loai_tthai.equals("04")){%>  
                   <input  id="inTrang_thai" type="text" name="ket_qua" style="border: 0;font-size:14px; width: 200px;color:blue;FONT-WEIGHT: bold"  class="fieldText" value="Xác nhận - Đã xử lý" readonly="true"/>
                 <%}else {%>
                  <input  id="inTrang_thai" type="text" name="ket_qua" style="border: 0;font-size:14px; width: 200px;color:red;FONT-WEIGHT: bold" class="fieldText" value="" readonly="true"/>
                 <%}%>     
                  </td> 
                        
             </tr>         
              <tr>                 
                 
                 <td  width="25%">-LTT đi thừa tại TABMIS - thiếu tại TTSP</td>
                 <td align="center" width="10%"><input  style="width:99%"  class="fieldTextCenter" value="<%=thua_di_tab%>" readonly="true" type="text"></td>
                 <td width="25%" >-LTT đến thừa tại TTSP - thiếu tại TABMIS</td>
                 <td width="25%" align="center"><input  class="fieldTextCenter" value="<%=thua_den_sp%>" readonly="true" type="text"></td>
                 
              </tr> 
              
              <tr>                
                 <td  width="25%">-LTT đi thiếu tại TABMIS - thừa tại TTSP</td>                 
                 <td align="center" width="10%"><input  class="fieldTextCenter" value="<%=thua_di_sp%>" readonly="true" type="text"></td>
                 <td width="25%" >-LQT thừa tại TTSP - Thiếu tại Tabmis</td>
                 <td width="25%" align="center"><input  class="fieldTextCenter" value="<%=qt_thua_den_tab%>" readonly="true" type="text"></td> 
              </tr>
             
          
         </tbody>              
         </table>
                     
        </fieldset>
        
        </td>
      </tr>
      
      <tr>
      <td colspan="10">
      <fieldset>
        <legend><font color="Blue">Chi tiết kết quả đối chiếu</font></legend>
        <div class="tabberlive" id="mytabber1">
       <div class="tabber" id="mytabber1" >
        <div title="" class="tabbertab  " style="height:150px;overflow-y: scroll;"><h2>Lệnh thanh toán đi</h2>           
          <table style="BORDER-COLLAPSE: collapse" align="center" border="1" bordercolor="#e1e1e1" cellpadding="1" cellspacing="0" width="100%">
              <tbody><tr>
              <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Số yêu cầu thanh toán
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                        NH/KB chuyển
                    </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      Tên NH/KB chuyển
                    </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    NH/KB nhận
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Tên NH/KB nhận
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Loại tiền
                  </div>
                </th>
                
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Số tiền
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Loại TK
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Trạng thái
                  </div>
                </th>
              </tr>
              <logic:notEmpty name="colLTTCtietDi">
               
                <logic:iterate id="items" name="colLTTCtietDi" indexId="stt">
                <tr  class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                 <td bgcolor="#f0f0f0">
                    <div align="center">
                      <bean:write name="items" property="so_yctt"/>
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                      <div align="center">
                          <bean:write name="items" property="nhkb_chuyen"/>
                      </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                      <div align="left">
                        <bean:write name="items" property="ten_nhkb_chuyen"/>
                      </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="center">
                      <bean:write name="items" property="nhkb_nhan"/>
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="left">
                     <bean:write name="items" property="ten_nhkb_nhan"/>
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="center">
                      <bean:write name="items" property="ma_nt"/>
                    </div>
                  </td>
                 
                  <td bgcolor="#f0f0f0">
                    <div align="right">          
                      <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="so_tien"/>
                      </fmt:formatNumber>  
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="center">
                    <logic:equal name="items" property="trang_thai" value="TG">Tiền Gửi</logic:equal>
                    <logic:equal name="items" property="trang_thai" value="TT">Thanh Toán</logic:equal>
                    <logic:equal name="items" property="trang_thai" value="CT">Chuyên Thu</logic:equal>
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="left">                                          
                    <logic:equal name="items" property="chenh_lech" value="TTSP"> LTT đi thiếu tại TABMIS - thừa tại TTSP</logic:equal>
                    <logic:equal name="items" property="chenh_lech" value="TABMIS"> LTT đi thừa tại TABMIS - thiếu tại TTSP</logic:equal>
                   
                      <!-- <bean:write name="items" property="loai_lenh"/> -->
                    </div>
                  </td>
                </tr>
                </logic:iterate>
               
              </logic:notEmpty>
         </tbody>
         
         </table>
         </div>
         
         <div title="Lệnh thanh toán đến" class="tabbertab  tabbertabhide" style="height:150px;overflow-y: scroll;">
          <h2>Lệnh thanh toán đến</h2>
          <table style="BORDER-COLLAPSE: collapse" align="center" border="1" bordercolor="#e1e1e1" cellpadding="1" cellspacing="0" width="100%">
                <tr>
              <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Số lệnh thanh toán
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                        NH/KB chuyển
                    </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      Tên NH/KB chuyển
                    </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    NH/KB nhận
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Tên NH/KB nhận
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Loại tiền
                  </div>
                </th>
                
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Số tiền
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Loại TK
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Trạng thái
                  </div>
                </th>
              </tr>
                <logic:notEmpty name="colLTTCtietDen">                
                <logic:iterate id="items" name="colLTTCtietDen" indexId="stt">
                <tr  class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                <td bgcolor="#f0f0f0">
                    <div align="center">
                      <bean:write name="items" property="so_ltt"/>
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                      <div align="center">
                          <bean:write name="items" property="nhkb_chuyen"/>
                      </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                      <div align="left">
                        <bean:write name="items" property="ten_nhkb_chuyen"/>
                      </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="center">
                      <bean:write name="items" property="nhkb_nhan"/>
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="left">
                      <bean:write name="items" property="ten_nhkb_nhan"/>
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="center">
                      <bean:write name="items" property="ma_nt"/>
                    </div>
                  </td>
                  
                  <td bgcolor="#f0f0f0">
                    <div align="right">
                     <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="so_tien"/>
                      </fmt:formatNumber>  
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="center">
                    <logic:equal name="items" property="trang_thai" value="TG">Tiền Gửi</logic:equal>
                    <logic:equal name="items" property="trang_thai" value="TT">Thanh Toán</logic:equal>
                    <logic:equal name="items" property="trang_thai" value="CT">Chuyên Thu</logic:equal>
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="left">  
                    
                     <logic:equal name="items" property="chenh_lech" value="TTSP"> LTT đến thừa tại TTSP - thiếu tại TABMIS</logic:equal>
                    <logic:equal name="items" property="chenh_lech" value="TABMIS"> LTT đến thiếu tại TTSP - thừa tại TABMIS</logic:equal>
                      <!-- <bean:write name="items" property="loai_lenh"/> -->
                    </div>
                  </td>
                </tr>
                </logic:iterate>
                
              </logic:notEmpty>
        </table>
           </div> 
           
           <div title="Lệnh quyết toán" class="tabbertab  tabbertabhide" style="height:150px;overflow-y: scroll;">
          <h2>Lệnh quyết toán</h2>
          <table style="BORDER-COLLAPSE: collapse" align="center" border="1" bordercolor="#e1e1e1" cellpadding="1" cellspacing="0" width="100%">
                <tr>
              <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Số lệnh quyết toán
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                        NH/KB chuyển
                    </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                    <div align="center">
                      Tên NH/KB chuyển
                    </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    NH/KB nhận
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Tên NH/KB nhận
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">
                    Loại tiền
                  </div>
                </th>
                
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Số tiền
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Loại TK
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0">
                  <div align="center">          
                    Trạng thái
                  </div>
                </th>
              </tr>
                <logic:notEmpty name="colLQT">                
                <logic:iterate id="items" name="colLQT" indexId="stt">
                <tr  class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                <td bgcolor="#f0f0f0">
                    <div align="center">
                      <bean:write name="items" property="so_ltt"/>
                    </div>
                  </td>
                  
                  <td bgcolor="#f0f0f0">
                      <div align="center">
                          <bean:write name="items" property="nhkb_chuyen"/>
                      </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                      <div align="left">
                        <bean:write name="items" property="ten_nhkb_chuyen"/>
                      </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="center">
                      <bean:write name="items" property="nhkb_nhan"/>
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="left">
                      <bean:write name="items" property="ten_nhkb_nhan"/>
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="center">
                      <bean:write name="items" property="ma_nt"/>
                    </div>
                  </td>
                  
                  <td bgcolor="#f0f0f0">
                    <div align="right">
                     <fmt:formatNumber maxFractionDigits="0"  type="currency"  currencySymbol="">
                        <bean:write name="items" property="so_tien"/>
                      </fmt:formatNumber>  
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="center">
                    <logic:equal name="items" property="trang_thai" value="TG">Tiền Gửi</logic:equal>
                    <logic:equal name="items" property="trang_thai" value="TT">Thanh Toán</logic:equal>
                    <logic:equal name="items" property="trang_thai" value="CT">Chuyên Thu</logic:equal>
                    </div>
                  </td>
                  <td bgcolor="#f0f0f0">
                    <div align="left">  
                    
                     <logic:equal name="items" property="chenh_lech" value="TTSP"> LQT thừa tại TTSP - thiếu tại TABMIS</logic:equal>
                    <logic:equal name="items" property="chenh_lech" value="TABMIS"> LQT thiếu tại TTSP - thừa tại TABMIS</logic:equal>
                    </div>
                  </td>
                </tr>
                </logic:iterate>
                
              </logic:notEmpty>
        </table>
           </div>  
        </div>
        </div>
      </fieldset>
    </td>
      </tr>
    
  
      
    <tr > 
   
      <td align="right" colspan="5"> 
      <button id="xnbt" type="button" style="width:120px" onclick="callCfm();" class="ButtonCommon" accesskey="x" >
                      <span class="sortKey">X</span>ác nhận
                    </button>
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <button  type="button" onclick="check('close')" accesskey="o">
            Th<span class="sortKey">o</span>&#225;t
          </button>
      </td>
    </tr>
    <tr>
     
    </tr>
</table>   
    
     <div id="dialog-form-confirm" title="Xác nhận - Đã xử lý">
        <p class="validateTips"></p>
        <%@include file="/pages/ltt/dchieultt/XacNhan.jsp" %>
      </div>
      <html:hidden property="lydo" styleId="lydo"/>
      <html:hidden property="id" styleId="id"/>
  </html:form>
</div>

<%@ include file="/includes/ttsp_bottom.inc"%>
<script type="text/javascript">

 jQuery("#dialog-form-confirm").dialog({
      autoOpen: false,resizable : false,
       maxHeight: "700px",
      width: "550px",
      modal: true
    });




//function kiem soat an button xac nhan
 function disbtn(tthai){
   var trangthai = document.getElementById("trang_thai_hidden").value;
    
   if (trangthai =='01' ){
     document.getElementById("xnbt").disabled=false;
   }
   else if(trangthai=='00'){
     document.getElementById("xnbt").disabled=true;
   }
   else {
     document.getElementById("xnbt").disabled=true;
   }
 }
 
//function cho button
  function check(type,ma_nt) {  
   var strRowSelected="<%=strRowSelected%>";
   var f = document.forms[0];
    if (type == 'dchieu') { 
          var id_bk ="<%=idHolder%>";
          var ngayDC= "<%=ngayDcHolder%>";
          //var id= f.id.value;
          var row = f.rowSelected.value;
          document.getElementById("bt").disabled=true;
          f.action = 'DoiChieuLTT.do?id='+ id_bk +"&ngay_dc="+ngayDC+ "&rowSelected=" + row;  
      }
    if (type == 'print') {
        var kq_pht= document.getElementById("ket_qua_pht").value;
        var kq_ttsp = document.getElementById("ket_qua_ttsp").value;
        f.action = 'printTTinDChieuAction.do?type=kqdc1&ket_qua_pht='+kq_pht+'&ket_qua_ttsp='+kq_ttsp+'&ma_nt='+ma_nt;    
        var params = ['scrollbars=1,height='+(screen.height-100),'width='+screen.width].join(',');            
        newDialog = window.open('', '_form', params);  
        f.target='_form';  
        f.submit();
    }
    if (type == 'close') {
      var tcuu="<%=tcuu%>";
      if(tcuu!=null && ""!=tcuu){
        f.action = 'TraCuuDoiChieuLTT.do'+tcuu;
      }else if (tcuu==null || ""==tcuu){
        f.action = 'TraCuuDoiChieuLTT.do';
      } 
      f.submit();
    }
  }
  
  //open dialog xac nhan cho bang ke
function callCfm(){ 
      var bke_id ="<%=idHolder%>";
      jQuery("#bke_id").val(bke_id);
      jQuery("#dialog-form-confirm").dialog( "open" );      
    }

</script>

