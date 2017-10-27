<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys" %>
<%@ page import="com.seatech.framework.AppConstants" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.seatech.framework.utils.StringUtil"%>
<%@ include file="/includes/ttsp_header.inc"%>

<link rel="stylesheet"  type="text/css" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css" />
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>

<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery.jec-1.3.2.js"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/LenhThanhToan.js" type="text/javascript" />
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/ltt.quyettoan.check.valid.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>

<script type="text/javascript">
  $(document).ready(function(){
      $('#maNHKBNhan').attr('disabled',true);
      $('#tenTaiKhoanNhanLenh').attr('disabled',true);
      $('#taiKhoanNhanLenh').attr('disabled',true);
      $('#maNHNhanLenh').attr('disabled',true);
      
      $('#maNHKBChuyen :first-child').attr("selected", true);
      
    $('#maNHSL').text($('#maNHKBChuyen option:selected').val());
    
    //get loai tien
    var maNH = $('#maNHKBChuyen option:selected').text();
    $('#nhkbChuyen').val(maNH);
    if(maNH != ""){
      jQuery.ajax({
        type : "POST",
        url : "getLoaiTien.do",
        data : {"maNH" : maNH},
        success : function(data, textstatus){
          if(data !=  null){
              var lstLoaiTien = new Object;
              lstLoaiTien = JSON.parse(data[0]);
              if(lstLoaiTien != null && lstLoaiTien != "fail"){
                  jQuery('#loaiTien option').remove();
                  jQuery('#loaiTien').append('<option value="" selected="selected">---Chọn loại tiền---<\/option>');
                  for(var i = 0; i < lstLoaiTien.size(); i++){
                    jQuery('#loaiTien').append('<option value="'+ lstLoaiTien[i].ma_nt + '" >' + lstLoaiTien[i].ma_nt + '<\/option>');
                  }
              }
          }
        }
      });
    }
  });
  function myFunction(){
      $('#maNHKBNhan').attr('disabled',false);
      $('#tenTaiKhoanNhanLenh').attr('disabled',false);
      $('#taiKhoanNhanLenh').attr('disabled',false);
      $('#maNHNhanLenh').attr('disabled',false);
  }
  $('input.blur').focus(function(){
    this.blur();
  });
  
  function thoatFunction(){
    window.location.replace("mainAction.do");
  }
  
  function formatDataTime(strDate){
      var date = strDate.value;
      var comp = date.split("/");
      var d = parseInt(comp[0], 10);
  }
  
  //xu ly dinh dang tien te nuoc ngoai
  function changeForeignCurrency(nStr){
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
      x1 = x1.replace(rgx, '$1' + ',' + '$2');
    }
    return x1 + x2;
  }
  //xu ly dinh dang tien te viet nam
  function changeVNDCurrency(nStr){
    nStr += '';
    x1 = nStr;
      x1 = x1.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
    return x1;
  }
  //dung cho viec thay doi dinh dang tien te
  function changeCurrency(){
    var cateCurrency = $('#loaiTien').val();
    var currency = $('#soTien').val();
    if(cateCurrency != ""){
    if(cateCurrency == "VND"){
      $('#soTien').val(changeVNDCurrency(currency));
    }else{
      $('#soTien').val(changeForeignCurrency(currency));
    }}
  }
  //getName ten chi nhanh ngan hang
  function getName(str){
    var id = str.value;
    if(id != "")
    jQuery.ajax({
      type : "post",
      url : "traCuuTenNH.do",
      data : {"id" : id },
      success : function(data, textstatus){
        if(data != null){
          var lstNganHang = new Object();
          lstNganHang = JSON.parse(data[0]);
          if(lstNganHang != null && lstNganHang != "fail" && lstNganHang.length != 0){
            $('#tenNH').text(lstNganHang[0].ten);
            $('#tenNHPhatLenh').val(lstNganHang[0].ten);
          }else{
            alert("Mã ngân hàng không tồn tại !");
          }
        }else{
          alert("Mã ngân hàng không tồn tại !");
        }
      }
    });
  }
  
  //Get thong tin nhan lenh
  function getThongTinNhanLenh(){
    var maNHKBChuyen = $('#maNHKBChuyen option:selected').text();
    var loaiTien = $('#loaiTien').val();
    if(maNHKBChuyen != "" && loaiTien != ""){
        jQuery.ajax({
      type : "POST",
      url : "traCuuNguoiKiemSoat.do",
      data : {"maNHKBChuyen" : maNHKBChuyen, "loaiTien" : loaiTien},
      success : function(data, textstatus){
        if(data != null){
          var strValue = new Object();
          strValue = JSON.parse(data[0]);
          if(strValue != null && strValue != "fail"){
            $('#tenTaiKhoanNhanLenh').val(strValue[0].tenkb);
            $('#taiKhoanNhanLenh').val(strValue[0].sotk);
            $('#maNHNhanLenh').val(strValue[0].manh);
            $('#nhNhanLenh').text(strValue[0].tennh);
            $('#tenNHNhanLenh').val(strValue[0].tennh);
          }else{
            alert("Thông tin nhận lệnh không tồn tại");
          }
        }else{
          alert("Thông tin nhận lệnh không tồn tại");
        }
      }
    });
    }
  }
  
  //get loai tien
  function getLoaiTien(str){
    var maNH = $('#maNHKBChuyen option:selected').text();
    $('#nhkbChuyen').val(maNH);
    if(maNH != ""){
      jQuery.ajax({
        type : "POST",
        url : "getLoaiTien.do",
        data : {"maNH" : maNH},
        success : function(data, textstatus){
          if(data !=  null){
              var lstLoaiTien = new Object;
              lstLoaiTien = JSON.parse(data[0]);
              if(lstLoaiTien != null && lstLoaiTien != "fail"){
                  jQuery('#loaiTien option').remove();
                  jQuery('#loaiTien').append('<option value="" selected="selected">---Chọn loại tiền---<\/option>');
                  for(var i = 0; i < lstLoaiTien.size(); i++){
                    jQuery('#loaiTien').append('<option value="'+ lstLoaiTien[i].ma_nt + '" >' + lstLoaiTien[i].ma_nt + '<\/option>');
                  }
              }else{
                alert("aaa");
              }
          }
        }
      });
    }
  }
  
  function validateData(){
     var nhkbChuyen = $('#maNHKBChuyen').val();
     var nhkbNhan = $('#maNHKBNhan').val();
     var ngayHachToan = $('#ngayHachToan').val();
     var ngayQuyetToan = $('#ngayQuyetToan').val();
     var loaiQuyetToan = $('#loaiQuyetToan').val();
     var soLenhQuyetToan = $('#soLenhQuyetToan').val();
     var soTien = $('#soTien').val();
     var loaiTien = $('#loaiTien').val();
     var ngayNhap = $('#ngayNhap').val();
     var ngayKiemSoat = $('#ngayKiemSoat').val();
     var maNHPhatLenh = $('#maNHPhatLenh').val();
     var taiKhoanPhatLenh = $('#taiKhoanPhatLenh').val();
     var tenTaiKhoanPhatLenh = $('#tenTaiKhoanPhatLenh').val();
     if(nhkbChuyen == ""){
      alert("Trường ngân hàng kho bạc chuyển không được để trống");
      return false;
     }
     if(ngayHachToan == ""){
      alert("Trường ngày hạch toán không được để trống");   
      return false;
     }
     if(ngayQuyetToan == ""){
      alert("Trường ngày quyết toán không được để trống");   
      return false;
     }
     if(loaiQuyetToan == ""){
        alert("Trường loại quyết toán không được để trống");
        return false;
     }
     if(soLenhQuyetToan == ""){
        alert("Trường số lệnh quyết toán không được để trống");
        return false;
     }
     if(soTien == ""){
        alert("Trường số tiền không được để trống");
        return false;
     }
     if(loaiTien== ""){
        alert("Trường loại tiền không được để trống");
        return false;
     }
     if(ngayNhap == ""){
        alert("Trường ngày nhập không được để trống");
        return false;
     }
     if(ngayKiemSoat ==""){
        alert("Trường ngày kiểm soát không được để trống");
        return false;
     }
     if(tenTaiKhoanPhatLenh == ""){
        alert("Trường tên tài khoản phát lệnh không được để trống");
        return false;
     }
     if(taiKhoanPhatLenh ==""){
        alert("Trường tài khoản phát lệnh không được để trống");
        return false;
     }
     if(maNHPhatLenh == ""){
        alert("Trường mã ngân hàng phát lệnh không được để trống");
        return false;
     }
     
     return true;
  }
  function getMaNHKBChuyen(){
    var maNHKBChuyen = $('#maNHKBChuyen option:selected').val();
    $('#maNHSL').text(maNHKBChuyen);
  }
  function validDateAndHour(str){
    var a = str.value;
    var day = a.substr(0,2);
    var month = a.substr(3,2);
    var year = a.substr(6,4);
    var hour = a.substr(10,9);
    var date = month + "/" + day + "/" + year + " " + hour;
    if(date.length != 20){
      alert("Sai định dạng ngày tháng");
    }
  }
  
</script>
<div id="body">
    <table border="0" cellspacing="0" cellpadding="0" width="100%"
         align="center">
    <tbody>
      <tr>
        <td width=13><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg" width="13px" height="30px"/></td>
        <td background="<%=request.getContextPath()%>/styles/images/T2.jpg" width="75%">
          <span class=title2> Nhập lệnh quyết toán thủ công</span>
        </td>
        <td width=62><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg" width=62 height=30/></td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg" width="20%">&nbsp;</td>
        <td width=4><img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg" width=4 height=30/></td>
      </tr>
    </tbody>
    </table>
  <html:form action="addLenhQuyetToanThuCong.do">
  <div class="form-control-input">
    <fieldset>
      <legend>Thông tin chung từ Ngân hàng</legend>
      
      <div class="app_error">
      <html:errors/>
      </div>
      
      <table cellpadding="3" cellspacing="0" border="0" width="100%" >
      <%
        String id =request.getAttribute("nhkbNhan_id") == null ? "" : request.getAttribute("nhkbNhan_id").toString();
        String name =request.getAttribute("nhkbNhan_name") == null ? "" : request.getAttribute("nhkbNhan_name").toString();
      %>
        <tr style="height : 30px;">
            <td id="label" style="text-align : right;" >NH/KB Chuyển </td>
            <td>
             <html:select styleClass="selectBox" property="ma_nh"
                styleId="maNHKBChuyen" style="width : 30%" onchange="getLoaiTien(this); getMaNHKBChuyen();" >
                <html:optionsCollection label="ma_nh" value="ten" name="dmNH"/>
             </html:select>
            <html:hidden property="maNHKBChuyen" styleId="nhkbChuyen" />
             <a id="maNHSL"></a> <span style="color : red"> (*)</span></td>
            <td id="label" style="text-align : right;" >NH/KB Nhận </td>
            <td><html:text property="maNHKBNhan" styleClass="blur" styleId="maNHKBNhan"
               onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width : 20%" value="<%= id %>" /> <%= name %></td>
        </tr>
        <tr style="height : 30px;">
            <td id="label" style="text-align : right;" >Ngày hạch toán </td>
            <td>
                <html:text property="ngayHachToan" styleId="ngayHachToan"
                       styleClass="fieldText" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)"
                       onblur="javascript:mask(this.value,this,'2,5','/');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       style="WIDTH: 30%;" maxlength="10" />
             &nbsp; 
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                 border="0" id="ngay_hach_toan" width="20"
                 style="vertical-align:middle;" /> <span style="color : red">(*)</span>
            <script type="text/javascript">
              Calendar.setup( {
                  inputField : "ngayHachToan", // id of the input field
                  ifFormat : "%d/%m/%Y", // the date format
                  button : "ngay_hach_toan"// id of the button
              });
            </script>
            </td>
            <td id="label" style="text-align : right;">Ngày quyết toán </td>
            <td>
                <html:text property="ngayQuyetToan" styleId="ngayQuyetToan"
                       styleClass="fieldText" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)"
                       onblur="javascript:mask(this.value,this,'2,5','/');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       style="WIDTH: 30%;" maxlength="10" />
             &nbsp; 
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                 border="0" id="ngay_quyet_toan" width="20"
                 style="vertical-align:middle;"/> <span style="color : red">(*)</span>
            <script type="text/javascript">
              Calendar.setup( {
                  inputField : "ngayQuyetToan", // id of the input field
                  ifFormat : "%d/%m/%Y", // the date format
                  button : "ngay_quyet_toan"// id of the button
              });
            </script>
            </td>
        </tr>
        <tr style="height : 30px;">
            <td id="label" style="text-align : right;">Loại quyết toán </td>
            <td><html:select property="loaiQuyetToan" styleId="loaiQuyetToan">
                <option value="" selected="selected" >Chọn loại quyết toán</option>
                <option value="900">Quyết toán thu</option>
                <option value="910">Quyết toán chi</option>  
            </html:select> <span style="color : red">(*)</span></td>
            <td id="label" style="text-align : right;">Số than chiếu liên quan </td>
            <td><html:text property="soThamChieuLienQuan" styleId="soThamChieuLienQuan" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="50" /> </td>
        </tr>
        <tr style="height : 30px;">
            <td id="label" style="text-align : right;">Số lệnh quyết toán </td>
            <td><html:text property="soLenhQuyetToan" styleId="soLenhQuyetToan" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="20" /> <span style="color : red">(*)</span></td>
            <td id="label" style="text-align : right;">Số tham chiếu giao dịch </td>
            <td><html:text property="soThamChieuGiaoDich" styleId="soThamChieuGiaoDich" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="20" /></td>
        </tr>
        <tr style="height : 30px;">
         <td id="label" style="text-align : right;">Loại tiền </td>
            <td><html:select property="loaiTien" styleId="loaiTien" onchange="changeCurrency(); getThongTinNhanLenh();" >
                <option  selected="selected" value="">--Chọn loại tiền--</option>
            </html:select> <span style="color : red">(*)</span></td>
            <td id="label" style="text-align : right;">Số tiền </td>
            <td><html:text property="soTien" styleId="soTien" onblur="changeCurrency();" 
            onkeydown="if(event.keyCode==13) event.keyCode=9;" onkeypress="return numberBlockKey1();" maxlength="12"/> <span style="color : red">(*)</span></td>
        </tr>
      </table>
      <table cellpadding="3" cellspacing="0" border="0" width="100%" style="float : left;">
        <tr style="height : 30px;">
            <td width="15.5%" id="label" style="text-align : right;">Nội dung thanh toán </td>
            <td width="72%"><html:text property="noiDungThanhToan" styleId="noiDungThanhToan" 
                styleClass="form-control" style="width : 92%" onkeydown="if(event.keyCode==13) event.keyCode=9;" /> </td>
            <td></td>
            <td></td>
        </tr>
      </table>
      <table cellpadding="3" cellspacing="0" border="0" width="100%">
          <tr style="height : 30px;">
              <td id="label" style="text-align : right;  width :15.5%;">Người nhập </td>
              <td width="45%" ><html:text property="nguoiNhap" styleId="nguoiNhap" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="50" /></td>
              <td id="label" style="text-align : right; width : 11%" class="canle" >Ngày nhập </td>
              <td><html:text property="ngayNhap" styleId="ngayNhap"
                       styleClass="fieldText" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)"
                       onblur="javascript:mask(this.value,this,'2,5','/');"
                       style="width : 44%" onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       onkeypress="return numberBlockKey1();" readonly="true" maxlength="19" />
             &nbsp; 
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                 border="0" id="ngay_nhap_btn" width="20"
                 style="vertical-align:middle;"/> <span style="color : red">(*)</span>
            <script type="text/javascript">
              Calendar.setup( {
                  inputField : "ngayNhap", // id of the input field
                  ifFormat : "%d/%m/%Y 00:00:00", // the date format
                  button : "ngay_nhap_btn"// id of the button
              });
              $(document).ready(function(){
                $('#ngay_nhap_btn').click(function(){
                  $('#ngayNhap').attr('readonly',false);
                });
              });
            </script></td>
          </tr>
          <tr style="height : 30px;">
              <td id="label" style="text-align : right;" width="13%">Người kiểm soát </td>
              <td width="42%" ><html:text property="nguoiKiemSoat" styleId="nguoiKiemSoat"
                onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="50"/></td>
              <td id="label" style="text-align : right;" >Ngày kiểm soát </td>
              <td><html:text property="ngayKiemSoat" styleId="ngayKiemSoat"
                       styleClass="fieldText"  onmouseout="UnTip()" onmouseover="Tip(this.value)"
                       onblur="javascript:mask(this.value,this,'2,5','/');"
                       onkeypress="return numberBlockKey1();"
                       style="width : 44%" onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true" maxlength="19" />
             &nbsp; 
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                 border="0" id="ngay_ks_btn" width="20"
                 style="vertical-align:middle;"/> <span style="color : red">(*)</span>
            <script type="text/javascript">
              Calendar.setup( {
                  inputField : "ngayKiemSoat", // id of the input field
                  ifFormat : "%d/%m/%Y 00:00:00", // the date format
                  button : "ngay_ks_btn"// id of the button
              });
              $(document).ready(function(){
                $('#ngay_ks_btn').click(function(){
                  $('#ngayKiemSoat').attr('readonly',false);
                });
              });
            </script></td>
          </tr>
      </table>
    </fieldset>
  </div>
  <div class="space" style="height : 10px; width : 100%;"></div>
    <div class="left" style="width : 50%; float : left;">
      <fieldset>
        <legend>Thông tin phát lệnh</legend>
        <table cellpadding="3" cellspacing="0" border="0">
         <tr style="height : 30px;">
            <td id="label" style="text-align : right;" width="17%" >Tên tài khoản </td>
            <td><html:text property="tenTaiKhoanPhatLenh" styleId="tenTaiKhoanPhatLenh" style="width : 94%;" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="200" /> <span style="color : red;">(*)</span></td>
         </tr>
         <tr style="height : 30px;">
            <td id="label" style="text-align : right;">Tài khoản </td>
            <td><html:text property="taiKhoanPhatLenh" styleId="taiKhoanPhatLenh" style="width : 94%;" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="20" /> <span style="color : red;">(*)</span></td>
         </tr>
         <tr style="height : 30px;">
            <td id="label" style="text-align : right;">Tại NH </td>
            <td><html:text property="maNHPhatLenh" styleId="maNHPhatLenh" style="width : 30%;" onblur="getName(this);" onkeydown="if(event.keyCode==13) event.keyCode=9;"  maxlength="8"/> 
            <a id="tenNH"></a><html:hidden property="tenNHPhatLenh" styleId="tenNHPhatLenh" /><span style="color : red;">(*)</span></td>
         </tr>
        </table>
      </fieldset>
    </div>
    <div class="right" style="width : 50%; float : right;">
      <fieldset>
        <legend>Thông tin nhận lệnh</legend>
        <table cellpadding="3" cellspacing="0" border="0">
         <tr style="height : 30px;">
            <td id="label" width="17%" style="text-align : right;" >Tên tài khoản </td>
            <td><html:text property="tenTaiKhoanNhanLenh" onkeydown="if(event.keyCode==13) event.keyCode=9;" styleId="tenTaiKhoanNhanLenh" style="width : 94%;" readonly="true" maxlength="200" /> <span style="color : red;">(*)</span></td>
         </tr>
         <tr style="height : 30px;">
            <td id="label" style="text-align : right;">Tài khoản </td>
            <td><html:text property="taiKhoanNhanLenh" styleId="taiKhoanNhanLenh" style="width : 94%;" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="20" /> <span style="color : red;">(*)</span></td>
         </tr>
         <tr style="height : 30px;">
            <td id="label" style="text-align : right;">Tại NH </td>
            <td><html:text property="maNHNhanLenh" styleId="maNHNhanLenh" style="width : 30%;" readonly="true" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="8" /> 
            <a id="nhNhanLenh"></a><html:hidden property="tenNHNhanLenh" styleId="tenNHNhanLenh" /> <span style="color : red;">(*)</span></td>
         </tr>
        </table>
      </fieldset>
    </div>
    <div style="width : 100%"><a style="color : white;">-</a></div>
  <div class="form-control-input">
    <fieldset>
      <legend>Thông tin hạch toán</legend>
      <table cellpadding="3" cellspacing="0" border="0">
        <tr style="height : 30px;">
            <td id="label" style="text-align : right;">Phương án hạch toán </td>
            <td><html:select property="phuongAnHachToan" styleId="phuongAnHachToan" >
                <option value="T" selected="selected">Hạch toán đúng</option>
                <option value="P">Hạch toán chờ xử lý</option>
            </html:select> <span style="color : red;">(*)</span>
            </td>
        </tr>
      </table>
    </fieldset>
  </div>
  <div class="form-control-input"></div>
  <div style=" width : 100%; height : 10px;" ></div>
  <div class="form-control-input" align="right">
      <input type="submit" value="Ghi" id="submit" onclick="myFunction(); return validateData();" />
      <input type="button" id="thoat" value="Thoát" onclick="thoatFunction();" />
  </div>
   </html:form>
</div>
<% String msg = request.getAttribute("msg") == null ? "" : request.getAttribute("msg").toString();%> 
<script type="text/javascript">

var vmsg ="<%=msg%>";
if(vmsg != 'null' && vmsg != '')
alert(vmsg);

</script>

<%@ include file="/includes/ttsp_bottom.inc"%>