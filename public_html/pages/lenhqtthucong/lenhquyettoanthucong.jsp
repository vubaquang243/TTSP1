<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys" %>
<%@ page import="com.seatech.framework.AppConstants" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.seatech.ttsp.tknhkb.TKNHKBacVO" %>
<%@ page import="com.seatech.ttsp.quyettoan.UpdateQuyetToanVO" %>
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
<%
  //Collection dmNH = (Collection)request.getAttribute("dmNH");
  UpdateQuyetToanVO vo = (UpdateQuyetToanVO)(request.getAttribute("quyetToanObject") == null ? null : request.getAttribute("quyetToanObject"));
  String msg1 = request.getAttribute("msg1") == null ? "" : request.getAttribute("msg1").toString();
  String userLogin = session.getAttribute(AppConstants.APP_USER_NAME_SESSION) == null ? "" : session.getAttribute(AppConstants.APP_USER_NAME_SESSION).toString();
%>
<script type="text/javascript">
  $(document).ready(function(){
      $('#maNHKBNhan').attr('disabled',true);
      $('#tenTaiKhoanNhanLenh').attr('disabled',true);
      $('#taiKhoanNhanLenh').attr('disabled',true);
      $('#maNHNhanLenh').attr('disabled',true);
      $('#maNHKBChuyen').focus();
      $('#maNHKBChuyen :first-child').attr("selected", true);
      $('#nguoiNhap').attr("disabled",true);
      $('#soLenhQuyetToan').attr("disabled",true);
      $('#maNHPhatLenh').attr("disabled",true);
      $('#taiKhoanPhatLenh').attr("disabled",true);
      $('#tenTaiKhoanPhatLenh').attr("disabled",true);
      //$('#tenTaiKhoanPhatLenh').prop("readonly",true);
      var msg1 = '<%=msg1%>';
      if(msg1 != ""){
        alert(msg1);
        window.location.replace("XuLyLenhQuyetToanList.do");
      }
      <%if(vo == null){%>
        $('#nguoiNhap').val("<%=userLogin%>");
      <%}%>
      <%if(vo != null){%>
      $('#soLenhQuyetToan').attr("disabled",true);
     
      var nhkb_chuyen = '<%= vo.getNhkb_chuyen()%>';       
      var nhkb_nhan = '<%=vo.getNhkb_nhan()%>';
      var ten_nhkb_nhan = '<%=vo.getTen_nhkb_nhan()%>';
      var ngay_htoan = '<%=vo.getNgay_htoan()%>';
      var ngay_qtoan = '<%=vo.getNgay_qtoan()%>';      
      var loai_qtoan = '<%=vo.getLoai_qtoan()%>';
      var so_lenh = '<%=vo.getId()%>';
      var ma_tchieu_gd = '<%=vo.getMa_tchieu_gd()%>';
      var soThamChieuLienQuan = '<%=vo.getSo_tchieu()%>';
      var loai_tien = '<%=vo.getMa_nt()%>';
      var so_tien = '<%=vo.getSo_tien()%>';
      var ndung_tt = '<%=vo.getNdung_tt()%>';
      var nguoi_nhap_nh = '<%=vo.getNguoi_nhap_nh()%>';
      var ngay_nhap_nh = '<%=vo.getNgay_nhap_nh()%>';
      var nguoi_ks_nh = '<%=vo.getNguoi_ks_nh()%>';
      var ngay_ks_nh = '<%=vo.getNgay_ks_nh()%>';
      var tk_chuyen = '<%=vo.getTk_chuyen()%>';
        
        $('#type_action').val("update");
        $('#ma_nh_chuyen_update').val(nhkb_chuyen);
        $("#maNHKBChuyen option:contains(" + nhkb_chuyen + ")").attr('selected', 'selected');
        $('#maNHKBNhan').val(nhkb_nhan);
        $('#ten_NHKBNhan').text(ten_nhkb_nhan);
        $('#ngayHachToan').val(ngay_htoan);
        $('#ngayQuyetToan').val(ngay_qtoan);
        if(loai_qtoan == "D"){
          $('#loaiQuyetToan option[value="900"]').attr("selected",true);
        }else{
          $('#loaiQuyetToan option[value="910"]').attr("selected",true);
        }
        $('#soLenhQuyetToan').val(so_lenh);
        $('#id_ref').val(so_lenh);
        $('#soLenhQuyetToan').attr('readonly',true);
        if(ma_tchieu_gd == "null" || ma_tchieu_gd == null) ma_tchieu_gd = "";
        $('#soThamChieuGiaoDich').val(ma_tchieu_gd);
        if(soThamChieuLienQuan == "null" || soThamChieuLienQuan == null) soThamChieuLienQuan = "";
        $('#soThamChieuLienQuan').val(soThamChieuLienQuan);
        if(loai_tien=="VND"){
          $('#soTien').val(changeVNDCurrency(so_tien));
        }else{
          $('#soTien').val(changeForeignCurrency(so_tien));
        }
         if(ndung_tt == "null" || ndung_tt == null)
        $('#noiDungThanhToan').val(ndung_tt);
        if(nguoi_nhap_nh == "null" || nguoi_nhap_nh == null) nguoi_nhap_nh = "";
        $('#nguoiNhap').val(nguoi_nhap_nh);
        if(ngay_nhap_nh == null || ngay_nhap_nh == "null") ngay_nhap_nh = "";
        $('#ngayNhap').val(ngay_nhap_nh);
        if(nguoi_ks_nh == "null" || nguoi_ks_nh == null) nguoi_ks_nh = "";
        $('#nguoiKiemSoat').val(nguoi_ks_nh);
        if(ngay_ks_nh != "null" || ngay_ks_nh == null) ngay_ks_nh = "";
        $('#ngayKiemSoat').val(ngay_ks_nh);
        $('#taiKhoanPhatLenh').val(tk_chuyen);
        var ma_nh_chuyen = '<%=vo.getMa_nh_chuyen()%>';
        $('#maNHPhatLenh').val(ma_nh_chuyen);
        //getName(ma_nh_chuyen);
        var ten_nh_chuyen = '<%= vo.getTen_nh_chuyen()%>';
        $('#tenNH').text(ten_nh_chuyen);
        $('#tenNHPhatLenh').val(ten_nh_chuyen);
        var ten_kh_chuyen = '<%=vo.getTen_kh_chuyen()%>';
        $('#tenTaiKhoanPhatLenh').val(ten_kh_chuyen);
        var tk_chuyen = '<%=vo.getTk_chuyen()%>';
        $('#taiKhoanPhatLenh').val(tk_chuyen);
      var ten_nh_nhan = '<%=vo.getTen_nh_nhan()%>';
      $('#nhNhanLenh').text(ten_nh_nhan);
      $('#tenNHNhanLenh').val(ten_nh_nhan);
      var ten_kh_nhan = '<%=vo.getTen_kh_nhan()%>';
      $('#tenTaiKhoanNhanLenh').val(ten_kh_nhan);
      var tk_nhan = '<%=vo.getTk_nhan()%>';
      $('#taiKhoanNhanLenh').val(tk_nhan);
      var ma_nh_nhan = '<%=vo.getMa_nh_nhan()%>';
      $('#maNHNhanLenh').val(ma_nh_nhan);
      //var loai_hach_toan = '<%=vo.getLoai_hach_toan()%>';
     // $('#phuongAnHachToan option[value="'+loai_hach_toan+'"]').attr("selected",true);
    //  getLyDo();
      var ldo_hach_toan = '<%=vo.getLdo_hach_toan()%>';
      $('#ly_do_hach_toan').val(ldo_hach_toan);
      $('#ly_do').val(ldo_hach_toan);
      <%}%>
      
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
                    <%if(vo !=null){%>
                     var loai_tien = '<%=vo.getMa_nt()%>';
                      $('#loaiTien option[value="'+loai_tien+'"]').attr("selected",true);
                    <%}%>
                  }
              }
          }
        }
      });
    }
    
    //
    $(document).ready(function(){
       var time = new Date();
        var display = "";
        if(time.getDate() < 10){
          display += "0" + (time.getDate()) + '/';
        }else display+= (time.getDate()) + '/';
        if((time.getMonth() + 1) < 10){
          display += "0" + (time.getMonth() + 1) + '/';
        }else display += (time.getMonth() + 1) + '/';
        display += time.getFullYear();
        $('#ngayHachToan').val(display);
        $('#ngayQuyetToan').val(display);
         $('#ngayHachToan').attr("disabled",true);
         $('#ngayQuyetToan').attr("disabled",true);
      });
    
  });
  function submitData(){
  document.forms[0].submit();
  }
  
 /* function getLyDo(){
      var a = $('#phuongAnHachToan').val();
      $('td.myLabelID a#ly_do_a').remove();
      $('td.myID input#ly_do').remove();
      $('td.myID span').remove();
      $('#ly_do_hach_toan').val("");
      if(a == "P"){
        $('td.myLabelID').append('<a id="ly_do_a">Lý do hạch toán<\/a>');
        $('td.myID').append('<input type="text" id="ly_do" name="ly_do" style="width : 95%" onkeydown="if(event.keyCode==13) event.keyCode=9;" onblur="getValuea();" /> <span style="color : red;">(*)<\/span>');
      }else{
        $('td.myLabelID a#ly_do_a').remove();
        $('td.myID input#ly_do').remove();
        $('td.myID span').remove();
        $('#ly_do_hach_toan').val("");
      }
    }
   */ 
    function getValuea(){
    var a = $('input#ly_do').val();
      $('#ly_do_hach_toan').val(a);
    }
  function myFunction(){
      
  }
  $('input.blur').focus(function(){
    this.blur();
  });
  
  function thoatFunction(){
  <%if(vo == null){%>
    window.location.replace("mainAction.do");
    <%}else{%>
    window.location.replace("XuLyLenhQuyetToanList.do");
    <%}%>
  }
  
  function formatDataTime(strDate){
      var date = strDate.value;
      var comp = date.split("/");
      var d = parseInt(comp[0], 10);
  }
  
  //xu ly dinh dang tien te nuoc ngoai
  function changeForeignCurrency(nStr){
      nStr += '';
        x = nStr.split(',');
        x1 = x[0];
        x2 = x.length > 1 ? ',' + x[1] : '';
       var rgx = /(\d+)(\d{3})/;
       while (rgx.test(x1)) {
          x1 = x1.replace(rgx, '$1' + '.' + '$2');
        }
        return x1 + x2;
  }
  //xu ly dinh dang tien te viet nam
  function changeVNDCurrency(nStr){
    nStr = nStr*1;
    nStr += '';
    nStr = nStr.replace(/\./g,"");
    nStr = nStr.replace(/\,/g,"");
    
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
      currency = currency.replace(".","");
      currency = currency.replace(",","");
      if(!isNaN(currency)){
        currency = currency*1;
      }else{
        currency = "";
      }
      $('#soTien').val(changeVNDCurrency(currency));
    }else{
      currency = currency.replace(".","");
      $('#soTien').val(changeForeignCurrency(currency));
    }}
  }
  //getName ten chi nhanh ngan hang
  function getName(str){
    var id = $('#'+str).val();
    var ma_nh_chuyen_update = $('#ma_nh_chuyen_update').val();
    var type_action = $('#type_action').val();
    if(id != ""){
    $('#'+str).val($('#maNHKBChuyen option:selected').text());
    jQuery.ajax({
      type : "post",
      url : "traCuuTenNH.do",
      data : {"id" : id , "ma_nh_kb_chuyen" : ma_nh_chuyen_update, "action_type" : type_action},
      success : function(data, textstatus){
        if(data != null){
          var lstNganHang = new Object();
          lstNganHang = JSON.parse(data[0]);
          if(lstNganHang != null && lstNganHang != "fail"){
            if(lstNganHang.length != 0){
              if(lstNganHang != 0){
                $('#tenNH').text(lstNganHang[0].tennh);
                $('#tenNHPhatLenh').val(lstNganHang[0].tennh);
                $('#tenTaiKhoanPhatLenh').val(lstNganHang[0].tenkb);
                $('#taiKhoanPhatLenh').val(lstNganHang[0].sotk);
              }else{
                alert("Không đủ thông tin tài khoản phát lệnh !");
                $('#maNHKBChuyen option[value=""]').attr("selected",true);
                $('#tenNH').text("");
                $('#maNHKBChuyen').focus();
                $('#tenNHPhatLenh').val("");
                $('#tenTaiKhoanPhatLenh').val("");
                $('#taiKhoanPhatLenh').val("");
              }
            }else{
              alert("Không đủ thông tin tài khoản phát lệnh !");
              $('#maNHKBChuyen option[value=""]').attr("selected",true);
              $('#maNHKBChuyen').focus();
              $('#tenNH').text("");
              $('#tenNHPhatLenh').val("");
              $('#tenTaiKhoanPhatLenh').val("");
              $('#taiKhoanPhatLenh').val("");
            }
          }else{
            alert("Liên hệ trung ương để được cho phép nhập QT từ CT ngân hàng gửi.");
            $('#maNHKBChuyen option[value=""]').attr("selected",true);
            $('#maNHKBChuyen').focus();
             $('#tenNH').text("");
             $('#tenNHPhatLenh').val("");
             $('#tenTaiKhoanPhatLenh').val("");
             $('#taiKhoanPhatLenh').val("");
          }
        }else{
          alert("Không đủ thông tin tài khoản phát lệnh !");
             $('#maNHKBChuyen option[value=""]').attr("selected",true);
             $('#maNHKBChuyen').focus();
             $('#tenNH').text("");
             $('#tenNHPhatLenh').val("");
             $('#tenTaiKhoanPhatLenh').val("");
             $('#taiKhoanPhatLenh').val("");
        }
      }
    });
    }
  }
  //Get thong tin nhan lenh
  function getThongTinNhanLenh(){
    var maNHKBChuyen = "";
    if($('#maNHKBChuyen option:selected').val() != ""){
      maNHKBChuyen = $('#maNHKBChuyen option:selected').text() == "" ?  $('#maNHKBChuyen option:first').text() : $('#maNHKBChuyen option:selected').text();
    }
    var loaiTien = $('#loaiTien').val();
    $('#tenTaiKhoanNhanLenh').val("");
    $('#taiKhoanNhanLenh').val("");
    $('#maNHNhanLenh').val("");
    $('#nhNhanLenh').text("");
    $('#tenNHNhanLenh').val("");
    if(maNHKBChuyen != "" && loaiTien != ""){
        jQuery.ajax({
      type : "POST",
      url : "traCuuNguoiKiemSoat.do",
      data : {"maNHKBChuyen" : maNHKBChuyen, "loaiTien" : loaiTien},
      success : function(data, textstatus){
        if(data != null){
          var strValue = new Object();
          strValue = JSON.parse(data[0]);
          if(strValue != null && strValue != "fail" && strValue.size() != 0){
            $('#tenTaiKhoanNhanLenh').val(strValue[0].tenkb);
            $('#taiKhoanNhanLenh').val(strValue[0].sotk);
            $('#maNHNhanLenh').val(strValue[0].manh);
            $('#nhNhanLenh').text(strValue[0].tennh);
            $('#tenNHNhanLenh').val(strValue[0].tennh);
          }else{
            alert("Thông tin nhận lệnh không tồn tại");
            $('#maNHKBChuyen option[value=""]').attr("selected",true);
            $('#maNHKBChuyen').focus();
          }
        }else{
          alert("Thông tin nhận lệnh không tồn tại");
          $('#maNHKBChuyen option[value=""]').attr("selected",true);
          $('#maNHKBChuyen').focus();
        }
      }
    });
    }else{
       $('#tenTaiKhoanNhanLenh').val("");
       $('#taiKhoanNhanLenh').val("");
       $('#maNHNhanLenh').val("");
       $('#nhNhanLenh').text("");
       $('#tenNHNhanLenh').val("");
    }
  }
  
  //get loai tien
  function getLoaiTien(str){
    var maNH = $('#maNHKBChuyen option:selected').text();
    var tenNH = $('#maNHKBChuyen option:selected').val();
    if(tenNH != ""){
    $('#maNHPhatLenh').val(maNH);
    $('#nhkbChuyen').val(maNH);
    }else{
      $('#maNHPhatLenh').val("");
      $('#nhkbChuyen').val("");
      $('#tenTaiKhoanNhanLenh').val("");
      $('#taiKhoanNhanLenh').val("");
      $('#nhNhanLenh').text("");
      $('#tenNHNhanLenh').val("");
    }
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
  }
  // Check dữ liệu trước khi submit
  function validateData(){
     var nhkbChuyen = $('#maNHKBChuyen').val();
     var nhkbNhan = $('#maNHKBNhan').val();
     var ngayHachToan = $('#ngayHachToan').val();
     var ngayQuyetToan = $('#ngayQuyetToan').val();
     var loaiQuyetToan = $('#loaiQuyetToan option:selected').val();
     var soLenhQuyetToan = $('#soLenhQuyetToan').val();
     var soTien = $('#soTien').val();
     var loaiTien = $('#loaiTien').val();
     var soThamChieuGiaoDich = $('#soThamChieuGiaoDich').val();
     var maNHPhatLenh = $('#maNHPhatLenh').val();
     var taiKhoanPhatLenh = $('#taiKhoanPhatLenh').val();
     var tenTaiKhoanPhatLenh = $('#tenTaiKhoanPhatLenh').val();
     var tentknhanlenh = $('#tenTaiKhoanNhanLenh').val();
     var tknhanlenh = $('#taiKhoanNhanLenh').val();
     var maNHnhanlenh = $('#maNHNhanLenh').val();
     if(nhkbChuyen == ""){
      alert("Trường ngân hàng kho bạc chuyển không được để trống");
      $('#maNHKBChuyen').focus();
      return false;
     }
     if(ngayHachToan == ""){
      alert("Trường ngày hạch toán không được để trống");   
      $('#ngayHachToan').focus();
      return false;
     }
     if(ngayQuyetToan == ""){
      alert("Trường ngày quyết toán không được để trống");   
       $('#ngayQuyetToan').focus();
      return false;
     }
     if(ngayHachToan != ngayQuyetToan){
      alert("Ngày quyết toán phải bằng ngày hạch toán");   
       $('#ngayQuyetToan').focus();
      return false;
     }
     if(loaiQuyetToan == ""){
        alert("Trường loại quyết toán không được để trống");
        $('#loaiQuyetToan').focus();
        return false;
     }
     if(soThamChieuGiaoDich == ""){
        alert("Trường số tham chiếu giao dịch không được để trống");
        $('#soThamChieuGiaoDich').focus();
        return false;
     }
     if(soLenhQuyetToan == ""){
        alert("Trường số lệnh quyết toán không được để trống");
        $('#soLenhQuyetToan').focus();
        return false;
     }
     if(soTien == ""){
        alert("Trường số tiền không được để trống");
        $('#soTien').focus();
        return false;
     }
     if(loaiTien== ""){
        alert("Trường loại tiền không được để trống");
        $('#loaiTien').focus();
        return false;
     }
     if(tenTaiKhoanPhatLenh == ""){
        alert("Trường tên tài khoản phát lệnh không được để trống");
        $('#tenTaiKhoanPhatLenh').focus();
        return false;
     }
     if(taiKhoanPhatLenh ==""){
        alert("Trường tài khoản phát lệnh không được để trống");
        $('#taiKhoanPhatLenh').focus();
        return false;
     }
     if(maNHPhatLenh == ""){
        alert("Trường mã ngân hàng phát lệnh không được để trống");
        $('#maNHPhatLenh').focus();
        return false;
     }
     if(tentknhanlenh =="" || tknhanlenh =="" || maNHnhanlenh ==""){
      alert("Thông tin nhận lệnh không chính xác ! Vui lòng chọn loại tiền khác ");
      $('#loaiTien').focus();
     }
    <%if(vo != null){%>
      $('#frmLenhQuyetToan').attr('action','/TTSP/updateLenhQuyetToanThuCong.do');
      <%}else{%>
      document.forms[0].action = "addLenhQuyetToanThuCong.do";
      <%}%>
      $('#maNHKBNhan').attr('disabled',false);
      $('#tenTaiKhoanNhanLenh').attr('disabled',false);
      $('#taiKhoanNhanLenh').attr('disabled',false);
      $('#maNHNhanLenh').attr('disabled',false);
      $('#soLenhQuyetToan').attr("disabled",false);
      $('#nguoiNhap').attr("disabled",false);
      $('#soLenhQuyetToan').attr("disabled",false);
      $('#maNHPhatLenh').attr("disabled",false);
      $('#taiKhoanPhatLenh').attr("disabled",false);
      $('#tenTaiKhoanPhatLenh').attr("disabled",false);
      $('#ngayHachToan').attr("disabled",false);
      $('#ngayQuyetToan').attr("disabled",false);
    
     return true;
  }
  function getMaNHKBChuyen(){
    var tenNHKBChuyen = $('#maNHKBChuyen option:selected').val();
    var maNHKBChuyen = $('#maNHKBChuyen option:selected').text();
    $('#maNHSL').text(tenNHKBChuyen);
    var sli = document.getElementById("maNHKBChuyen").selectedIndex ;
    var loaitk = document.getElementsByName('loai_tk')[sli-1].value;    
    $('#loaiTaiKhoan').val(loaitk);
    if(loaitk == "03"){
      $('#loaiQuyetToan option').remove();
      $('#loaiQuyetToan').append('<option value="" selected="selected" >Chọn loại quyết toán<\/option>');
      $('#loaiQuyetToan').append('<option value="900">Quyết toán thu<\/option>');
    }else{
      $('#loaiQuyetToan option').remove();
      $('#loaiQuyetToan').append('<option value="" selected="selected" >Chọn loại quyết toán<\/option>');
      $('#loaiQuyetToan').append('<option value="900">Quyết toán thu<\/option>');
      $('#loaiQuyetToan').append('<option value="910">Quyết toán chi<\/option>');
    }
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
  
  function getSoLenh(){
    var loaiQuyetToan = $('#loaiQuyetToan').val();
    if(loaiQuyetToan != ""){
      $.ajax({
        type : "POST",
        url : "getSoLenhQuyetToan.do",
        data : {"loaiQuyetToan" : loaiQuyetToan},
        success : function(data, textstatus){
          if(data != null){
            var soLenh = new Object();
            soLenh = JSON.parse(data[0]);
      
            if(soLenh.length==16){            
              $('#soLenhQuyetToan').val(soLenh);
            }else{
              alert(soLenh);
              $('#loaiQuyetToan').focus();
            }
          }
        }
      });
    }
  }
  // taidd 21/11/2017
    function checkSpecialKey2(event){
      var specialKeys = [34,38,39,60,62]; // 34 : "; 38 : &; 39 : '; 60 : <; 62 : >
      if(specialKeys.indexOf(event.keyCode) >= 0) {
        return false;
      }
      return true;
    }
    
    function checkSpecialKeyOnBlur(id){
          var value = jQuery("#" + id).val();
          value = value.replace(/[<&>'"]/g,"");
          jQuery("#" + id).val(value);
    }
    
    // taidd dinh dang lai tien 20171130
   function changeValue(txt_id, allowNegativeNumber) {  
      var value = jQuery("#"+txt_id +"").val().replace(/\s/g,"");
      var loai_tien = jQuery("#loaiTien").val();
      
      if(allowNegativeNumber == undefined){
        allowNegativeNumber = false;
      }
      
      if(value == ""){
        return;
      }
        if(value == 0){
          jQuery("#"+txt_id).val("");
        }else{
          if(loai_tien == "VND"){
            jQuery("#"+txt_id ).val(CurrencyFormatted2(value, 'VND', allowNegativeNumber));
          }else{
            jQuery("#"+txt_id ).val(CurrencyFormatted2(value, 'USD', allowNegativeNumber));
          }
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
    
 <html:form action="/addLenhQuyetToanThuCong.do" styleId="frmLenhQuyetToan">
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
                styleId="maNHKBChuyen" style="width : 100px" onchange="getLoaiTien(this); getMaNHKBChuyen(); getName('maNHPhatLenh');" onblur="getThongTinNhanLenh();" >
                <option value="" >Chọn ngân hàng</option>
                <html:optionsCollection label="ma_nh" value="ten" name="dmNH"/>
             </html:select>
             <logic:notEmpty name="dmNH">
             <logic:iterate id="items" name="dmNH">
                <input type="hidden" name="loai_tk" value="<bean:write name='items' property='loai_tk'/>" />
              </logic:iterate>
              </logic:notEmpty>
             <html:hidden property="loaiTaiKhoan" styleId="loaiTaiKhoan" />
            <html:hidden property="maNHKBChuyen" styleId="nhkbChuyen" />
             <a id="maNHSL"></a> <span style="color : red"> (*)</span></td>
            <td id="label" style="text-align : right;" >NH/KB Nhận </td>
            <td><html:text property="maNHKBNhan" styleClass="blur" styleId="maNHKBNhan"
               onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width : 30%" value="<%= id %>" /> <a id="ten_NHKBNhan"><%= name %></a></td>
        </tr>
        <tr style="height : 30px;">
            <td id="label" style="text-align : right;" >Ngày hạch toán </td>
            <td>
                <html:text property="ngayHachToan" styleId="ngayHachToan"
                       styleClass="fieldText" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)"
                       onblur="javascript:mask(this.value,this,'2,5','/');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       style="WIDTH: 30%;" maxlength="10" /> <!--
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
            </script> -->
            </td>
            <td id="label" style="text-align : right;">Ngày quyết toán </td>
            <td>
                <html:text property="ngayQuyetToan" styleId="ngayQuyetToan"
                       styleClass="fieldText" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)"
                       onblur="javascript:mask(this.value,this,'2,5','/');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       style="WIDTH: 30%;" maxlength="10" /> <!--
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
            </script> -->
            </td>
        </tr>
        <tr style="height : 30px;">
            <td id="label" style="text-align : right;">Loại quyết toán </td>
            <td><html:select property="loaiQuyetToan" styleId="loaiQuyetToan" onchange="getSoLenh();">
                <option value="" selected="selected" >Chọn loại quyết toán</option>
                <option value="900">Quyết toán thu</option>
                <option value="910">Quyết toán chi</option>  
            </html:select> <span style="color : red">(*)</span></td>
            <td id="label" style="text-align : right;">Số tham chiếu liên quan </td>
            <td><html:text property="soThamChieuLienQuan" styleId="soThamChieuLienQuan" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="50" /> </td>
        </tr>
        <tr style="height : 30px;">
            <td id="label" style="text-align : right;">Số lệnh quyết toán </td>
            <td><html:text property="soLenhQuyetToan" styleId="soLenhQuyetToan" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="16" /> <span style="color : red">(*)</span>
                       <html:hidden property="id_ref" styleId="id_ref" />
                       </td>
            <td id="label" style="text-align : right;">Số tham chiếu GD (Số lệnh QT của NH) </td>
            <td><html:text property="soThamChieuGiaoDich" styleId="soThamChieuGiaoDich" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="20" /> <span style="color : red">(*)</span></td>
        </tr>
        <tr style="height : 30px;">
         <td id="label" style="text-align : right;">Loại tiền </td>
            <td><html:select property="loaiTien" styleId="loaiTien" onchange="changeValue('soTien'); getThongTinNhanLenh();" >
                <option  selected="selected" value="">--Chọn loại tiền--</option>
            </html:select> <span style="color : red">(*)</span></td>
            <td id="label" style="text-align : right;">Số tiền </td>
            <td><html:text property="soTien" styleId="soTien" onblur="changeValue('soTien');" 
            onkeydown="if(event.keyCode==13) event.keyCode=9;" onkeypress="return numbersonly2(event,true);" maxlength="20"/> <span style="color : red">(*)</span></td>
        </tr>
      </table>
      <table cellpadding="3" cellspacing="0" border="0" width="100%" style="float : left;">
        <tr style="height : 30px;">
            <td width="15.5%" id="label" style="text-align : right;">Nội dung thanh toán </td>
            <td width="72%"><html:text property="noiDungThanhToan" styleId="noiDungThanhToan" 
                onblur="checkSpecialKeyOnBlur('noiDungThanhToan');"
                styleClass="form-control" style="width : 92%" onkeydown="if(event.keyCode==13) event.keyCode=9;" 
                onkeypress="if(!checkSpecialKey2(event)){return false;}"
                maxlength="200" /> </td>
            <td></td>
            <td></td>
        </tr>
      </table>
      <table cellpadding="3" cellspacing="0" border="0" width="100%">
          <tr style="height : 30px;">
              <td id="label" style="text-align : right;  width :15.5%;">Người nhập NH</td>
              <td width="45%" ><html:text property="nguoiNhap" styleId="nguoiNhap" onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="50" /></td>
              <td id="label" style="text-align : right; width : 11%" class="canle" >Ngày nhập NH</td>
              <td><html:text property="ngayNhap" styleId="ngayNhap"
                       styleClass="fieldText" onmouseout="UnTip()"
                       onmouseover="Tip(this.value)"
                       onblur="javascript:mask(this.value,this,'2,5','/');"
                       style="width : 44%" onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       onkeypress="return numberBlockKey1();" readonly="true" maxlength="19" />
             &nbsp; 
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                 border="0" id="ngay_nhap_btn" width="20"
                 style="vertical-align:middle;"/>
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
              <td id="label" style="text-align : right;" width="13%">Người kiểm soát NH</td>
              <td width="42%" ><html:text property="nguoiKiemSoat" styleId="nguoiKiemSoat"
                onkeydown="if(event.keyCode==13) event.keyCode=9;" maxlength="50"/></td>
              <td id="label" style="text-align : right;" >Ngày kiểm soát NH</td>
              <td><html:text property="ngayKiemSoat" styleId="ngayKiemSoat"
                       styleClass="fieldText"  onmouseout="UnTip()" onmouseover="Tip(this.value)"
                       onblur="javascript:mask(this.value,this,'2,5','/');"
                       onkeypress="return numberBlockKey1();"
                       style="width : 44%" onkeydown="if(event.keyCode==13) event.keyCode=9;" readonly="true" maxlength="19" />
             &nbsp; 
            <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                 border="0" id="ngay_ks_btn" width="20"
                 style="vertical-align:middle;"/>
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
            <td><html:text property="maNHPhatLenh" styleId="maNHPhatLenh" style="width : 30%;" onkeydown="if(event.keyCode==13) event.keyCode=9;"  maxlength="8"/> 
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
 <!-- <div class="form-control-input">
    <fieldset>
      <legend>Thông tin hạch toán</legend>
      <table cellpadding="3" cellspacing="0" border="0" width="100%">
        <tr style="height : 30px;">
            <td id="label" style="text-align : right;" width="15%">Phương án hạch toán </td>
            <td width="20%"><html:select property="phuongAnHachToan" styleId="phuongAnHachToan" onblur="getLyDo();" >
                <option value="T" selected="selected">Hạch toán đúng</option>
                <option value="P">Hạch toán chờ xử lý</option>
            </html:select> <span style="color : red;">(*)</span>
            </td>
            <td class="myLabelID" width="10%">
            <html:hidden property="ly_do_hach_toan" styleId="ly_do_hach_toan" value="" /></td>
            <td class="myID" width="55%"></td>
        </tr>
      </table>
    </fieldset> 
  </div> -->
  <div class="form-control-input"></div>
  <div style=" width : 100%; height : 10px;" ></div>
  <div class="form-control-input" align="right">
      <input type="hidden" id="type_action" value="insert" />
      <input type="hidden" id="ma_nh_chuyen_update" value="" />
      <input type="submit" value="Ghi" id="btnEdit" onclick="myFunction(); return validateData(); submitData();" />
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