<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<%@ page import="com.seatech.ttsp.tracuusodu.TraCuuSoDuVO"%>
<%@ page import="java.util.Collection"%>
<%@ page import="java.util.List"%>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet" type="text/css" media="screen"
      href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>

<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js" type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/LenhThanhToan.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>

<script type="text/javascript">

  jQuery.noConflict();
  
  
  jQuery(document).ready(function () {    
      //getTenKhoBacLTT('','');      
      jQuery("#dialog-form-lov-dm").dialog({
          autoOpen: false,resizable : false,
          maxHeight: "700px",
          width: "550px",
          modal: true
      });
      
      //
       //lay ngay hien tai  
      var time = new Date();
      var display = "";
        if(time.getDate() < 10){
          display += "0" + (time.getDate()) + '/';
        }else display+= (time.getDate()) + '/';
        if((time.getMonth() + 1) < 10){
          display += "0" + (time.getMonth() + 1) + '/';
        }else display += (time.getMonth() + 1) + '/';
        display += time.getFullYear();
      jQuery('#ngay_gd').val(display);
      
      if(jQuery("#kb_tinh").val() == '0003'){
          try{
            jQuery("#kb_huyen").val("0003");
          }catch(ex){}
      }
      
      jQuery("#btn_Thoat").click(function () {          
          document.forms[0].action = "thoatView.do";
          document.forms[0].submit();
          jQuery(this).dialog("close");
      });
      
      jQuery("#btnTra_cuu").click(function(){
        var ma_KB_tinh = jQuery("#id_kho_bac_tinh").val();
        var ma_KB_huyen = jQuery("#id_kho_bac_huyen").val();
        var ma_ngan_hang = jQuery("#id_ngan_hang").val();
        var loai_tien = jQuery("#loai_tien").val();
        var han_muc = jQuery("#han_muc").val();
        var loai_tai_khoan = jQuery("#loai_tai_khoan").val();
        var tinh_trang_so_du = jQuery("#tinh_trang_so_du").val();
        var ngay_gd = jQuery("#ngay_gd").val();
        var pageNumber = jQuery("#pageNumber").val();       
        //20171117 thuongdt bat them code tra cuu gioi han theo ngay giao dich
        if(checkNgay_GD()){
        
        if(ma_KB_tinh == "")
        ma_KB_tinh = "000";
        document.forms[0].action = "traCuuSoDu.do?ma_bk_tinh=" + ma_KB_tinh + "&ma_KB_huyen=" +ma_KB_huyen + "&ma_ngan_hang=" + ma_ngan_hang
          +"&loai_tien="+loai_tien+"&han_muc="+han_muc+"&loai_tai_khoan="+loai_tai_khoan+"&tinh_trang_so_du="+tinh_trang_so_du+"&ngay_gd="+ngay_gd;
        if(pageNumber == undefined){
        pageNumber = 1;
        document.forms[0].action +="&page="+pageNumber;   
        }else{
        document.forms[0].action +="&page="+pageNumber;
        }
         document.forms[0].submit();
        
        }      
      });
      //20171117 thuongdt bat them code tra cuu gioi han theo ngay giao dich
      function checkNgay_GD(){
        var ngay_gd = jQuery("#ngay_gd").val();
        if(ngay_gd == null || ngay_gd == ''){
          alert('Nhập ngày giao dich.');
          jQuery("#ngay_gd").focus();
          return false;
        }else{ 
          var vngay_gd = ngay_gd.split("/");
          var vmounth = Number(vngay_gd[1])-1
          var dateTemp = new Date(vngay_gd[2],''+vmounth ,vngay_gd[0])
         if(dateTemp > new Date()){
          alert('Ngày giao dich phải nhỏ hơn hoặc bằng ngày hiện tại.');
          jQuery("#ngay_gd").focus();
          return false;
         }
         
        }
        return true;
      }
      
      //getParams
      var getUrlParameter = function getUrlParameter(sParam) {
        var sPageURL = decodeURIComponent(window.location.search.substring(1)),
          sURLVariables = sPageURL.split('&'),
          sParameterName,
          i;

        for (i = 0; i < sURLVariables.length; i++) {
          sParameterName = sURLVariables[i].split('=');

          if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
          }
        }
      };
        jQuery("#loai_tien").val(getUrlParameter('loai_tien'));
        jQuery("#han_muc").val(getUrlParameter('han_muc'));
        jQuery("#loai_tien option[value="+getUrlParameter('loai_tien')+"]").attr("selected",true);
        jQuery("#loai_tai_khoan option[value="+getUrlParameter('loai_tai_khoan')+"]").attr("selected",true);
        jQuery("#tinh_trang_so_du option[value="+getUrlParameter('tinh_trang_so_du')+"]").attr("selected",true);
        if(getUrlParameter('ngay_gd') != undefined)
        jQuery("#ngay_gd").val(getUrlParameter('ngay_gd'));
      
      jQuery("a#previous").click(function(){
        var a = jQuery("#pageNumber").val();
        if(a > 1){
          jQuey("#pageNumber").val(a-1);
        }
      });
      jQuery("a#next").click(function(){
        var a = jQuery("#pageNumber").val();
        jQuery("#pageNumber").val(a+1);
      });
      
      var page = jQuery("select#selectPage option:selected").val();
      jQuery("#pageNumber").val(page);
      
      var kb_id_tinh = jQuery('#id_kho_bac_tinh option:selected').val();
      if(kb_id_tinh != ""){
        jQuery.ajax({
        type : "POST",
        url : "getNHKBHuyen.do",
        data : {"kb_id" : kb_id_tinh},
        success : function(data, textstatus){
          if(data != null){
            var lstNHKBHuyen = new Object();
            lstNHKBHuyen = JSON.parse(data[0]);
            if(lstNHKBHuyen.size() != 0){
              jQuery('#id_kho_bac_huyen option').remove();
              jQuery('#id_kho_bac_huyen').append('<option value="0000">Chọn thông tin tra cứu<\/option>');
              for(var i = 0; i < lstNHKBHuyen.size(); i++){
                jQuery('#id_kho_bac_huyen').append('<option value="'+ lstNHKBHuyen[i].ma +'" >'+ lstNHKBHuyen[i].ten + '<\/option>');
                jQuery('#id_kho_bac_huyen option[value='+ getUrlParameter('ma_KB_huyen') +']').attr("selected",true);
              }
            }
          }
        }
        });
      }
 
 //20171204 thuongdt bo load ngan hang, loai tien begin
      
      //get ngan hang
//      var kb_id_huyen = getUrlParameter('ma_KB_huyen');//jQuery("#id_kho_bac_huyen option:selected").val();
//      if(kb_id_huyen != ""){
//        jQuery.ajax({
//          type : "POST",
//          url : "getListNganHang.do",
//          data : {"kb_id_huyen" : kb_id_huyen},
//          success : function(data, textstatus){
//            if(data != null){
//              var lstNganHang = new Object();
//              lstNganHang = JSON.parse(data[0]);
//              if(lstNganHang.size() != 0){
//                jQuery('#id_ngan_hang option').remove();
//                jQuery('#id_ngan_hang').append('<option value="">Chọn thông tin tra cứu<\/option>');
//                for(var i = 0; i < lstNganHang.size(); i++){
//                  jQuery('#id_ngan_hang').append('<option value="'+ lstNganHang[i].ma_nh +'">'+ lstNganHang[i].ten_nh +'<\/option>');
//                  jQuery('#id_ngan_hang option[value='+ getUrlParameter('ma_ngan_hang') +']').attr("selected",true);
//                }
//              }
//            }
//          }
//        });
//      }
      
      //get loai tien
//      var nganHang  = getUrlParameter('ma_ngan_hang');
//       if(nganHang != ""){
//          jQuery.ajax({
//            type : "POST",
//            url : "getCateMoney.do",
//            data : {"nganhang_id" : nganHang},
//            success : function(data, textstatus){
//              if(data != null){
//                var lstLoaiTien = new Object();
//                lstLoaiTien = JSON.parse(data[0]);
//                if(lstLoaiTien.size() != 0){
//                  jQuery('#loai_tien option').remove();
//                  jQuery('#loai_tien').append('<option value="">--Chọn loại tiền--<\/option>');
//                  for(var i = 0; i < lstLoaiTien.size(); i++){
//                    jQuery('#loai_tien').append('<option value="' + lstLoaiTien[i].loai_tien + '" >'+ lstLoaiTien[i].loai_tien +'<\/option>');
//                    jQuery('#loai_tien option[value="'+ getUrlParameter('loai_tien') +'"]').attr("selected",true);
//                  }
//                }
//              }
//            }
//          });
//       }

 //20171204 thuongdt bo load ngan hang, loai tien end
  });
  
  function goPage(page) {
      jQuery('#pageNumber').val( page);
      var vform = jQuery('#frmTraCuuSoDu')[0];       
      vform.submit();            
      var ma_KB_tinh = jQuery("#id_kho_bac_tinh").val();
        var ma_KB_huyen = jQuery("#id_kho_bac_huyen").val();
        var ma_ngan_hang = jQuery("#id_ngan_hang").val();
        var loai_tien = jQuery("#loai_tien").val();
        var han_muc = jQuery("#han_muc").val();
        var loai_tai_khoan = jQuery("#loai_tai_khoan").val();
        var tinh_trang_so_du = jQuery("#tinh_trang_so_du").val();
        var ngay_gd = jQuery("#ngay_gd").val();
        var pageNumber = jQuery("#pageNumber").val();
        if(ma_KB_tinh == "")
        ma_KB_tinh = "000";
        document.forms[0].action = "traCuuSoDu.do?ma_bk_tinh=" + ma_KB_tinh + "&ma_KB_huyen=" +ma_KB_huyen + "&ma_ngan_hang=" + ma_ngan_hang
          +"&loai_tien="+loai_tien+"&han_muc="+han_muc+"&loai_tai_khoan="+loai_tai_khoan+"&tinh_trang_so_du="+tinh_trang_so_du+"&ngay_gd="+ngay_gd;
        if(pageNumber != 1 && pageNumber != 0)document.forms[0].action +="&page="+pageNumber;
        document.forms[0].submit();
  }
  
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
    nStr += '';
    x1 = nStr;
      x1 = x1.replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1.");
    return x1;
  } 
  
  function resetInput(){
    jQuery("#so_du").val("");
    jQuery("#tu_han_muc").val("");
    jQuery("#den_han_muc").val("");
    jQuery("#so_du_COT").val("");
  }
  
  function getTypeMoney(){
    var loai_tien = jQuery("#loai_tien").val();
    var so_du = jQuery("#so_du").val();
    var tu_han_muc = jQuery("#tu_han_muc").val();
    var den_han_muc = jQuery("#den_han_muc").val();
    var so_du_cot = jQuery("#so_du_COT").val();
    if(loai_tien != ""){
    if(loai_tien == "VND"){
        jQuery("#so_du").val(changeVNDCurrency(so_du));
        jQuery("#tu_han_muc").val(changeVNDCurrency(tu_han_muc));
        jQuery("#den_han_muc").val(changeVNDCurrency(den_han_muc));
        jQuery("#so_du_COT").val(changeVNDCurrency(so_du_cot));
    }else{
        jQuery("#so_du").val(changeForeignCurrency(so_du));
        jQuery("#tu_han_muc").val(changeForeignCurrency(tu_han_muc));
        jQuery("#den_han_muc").val(changeForeignCurrency(den_han_muc));
        jQuery("#so_du_COT").val(changeForeignCurrency(so_du_cot));
    }}
  }
    function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#ma_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );      
    }
    function getThongTinKB(){
      var kb_id = jQuery("#id_kho_bac_tinh").val();
      if(kb_id != ""){
        jQuery.ajax({
        type : "POST",
        url : "getNHKBHuyen.do",
        data : {"kb_id" : kb_id},
        success : function(data, textstatus){
          if(data != null){
            var lstNHKBHuyen = new Object();
            lstNHKBHuyen = JSON.parse(data[0]);
            if(lstNHKBHuyen.size() != 0){
              jQuery('#id_kho_bac_huyen option').remove();
              jQuery('#id_kho_bac_huyen').append('<option value="selected" >Chọn thông tin tra cứu<\/option>');
              for(var i = 0; i < lstNHKBHuyen.size(); i++){
                jQuery('#id_kho_bac_huyen').append('<option value="'+ lstNHKBHuyen[i].ma +'" >'+ lstNHKBHuyen[i].ten + '<\/option>');
              }
            }
          }
        }
        });
      }else{
        jQuery('#id_kho_bac_huyen option').remove();
        jQuery('#id_kho_bac_huyen').append('<option value="selected" >Chọn thông tin tra cứu<\/option>');
        //20171204 thuongdt bo load ngan hang, loai tien
        //jQuery('#id_ngan_hang option').remove();
        //jQuery('#id_ngan_hang').append('<option value="">Chọn thông tin tra cứu<\/option>');
        //jQuery('#loai_tien option').remove();
        //jQuery('#loai_tien').append('<option value="">--Chọn loại tiền--<\/option>');
      }
    }
    
    function getThongTinKB(ma){
      var kb_id = jQuery("#id_kho_bac_tinh").val();
      if(kb_id != ""){
        jQuery.ajax({
        type : "POST",
        url : "getNHKBHuyen.do",
        data : {"kb_id" : kb_id},
        success : function(data, textstatus){
          if(data != null){
            var lstNHKBHuyen = new Object();
            lstNHKBHuyen = JSON.parse(data[0]);
            if(lstNHKBHuyen.size() != 0){
              jQuery('#id_kho_bac_huyen option').remove();
              jQuery('#id_kho_bac_huyen').append('<option value="selected" >Chọn thông tin tra cứu<\/option>');
              for(var i = 0; i < lstNHKBHuyen.size(); i++){
                jQuery('#id_kho_bac_huyen').append('<option value="'+ lstNHKBHuyen[i].ma +'" >'+ lstNHKBHuyen[i].ten + '<\/option>');
                jQuery("#id_kho_bac_huyen option[value='"+ ma +"']").attr("selected",true);
              }
            }
          }
        }
        });
      }else{
        jQuery('#id_kho_bac_huyen option').remove();
        jQuery('#id_kho_bac_huyen').append('<option value="selected" >Chọn thông tin tra cứu<\/option>');
        //20171204 thuongdt bo load ngan hang, loai tien
        //jQuery('#id_ngan_hang option').remove();
        //jQuery('#id_ngan_hang').append('<option value="">Chọn thông tin tra cứu<\/option>');
        //jQuery('#loai_tien option').remove();
        //jQuery('#loai_tien').append('<option value="">--Chọn loại tiền--<\/option>');
      }
    }
    
    function getThongTinNganHang(){
      var kb_id_huyen = jQuery("#id_kho_bac_huyen").val();
      if(kb_id_huyen != "" && kb_id_huyen != "selected"){
        jQuery.ajax({
          type : "POST",
          url : "getListNganHang.do",
          data : {"kb_id_huyen" : kb_id_huyen},
          success : function(data, textstatus){
            if(data != null){
              var lstNganHang = new Object();
              lstNganHang = JSON.parse(data[0]);
              if(lstNganHang.size() != 0){
                jQuery('#id_ngan_hang option').remove();
                jQuery('#id_ngan_hang').append('<option value="">Chọn thông tin tra cứu<\/option>');
                for(var i = 0; i < lstNganHang.size(); i++){
                  jQuery('#id_ngan_hang').append('<option value="'+ lstNganHang[i].ma_nh +'">'+ lstNganHang[i].ten_nh +'<\/option>');
                }
              }
            }
          }
        });
      }else{
        jQuery('#id_ngan_hang option').remove();
        jQuery('#id_ngan_hang').append('<option value="">Chọn thông tin tra cứu<\/option>');
        jQuery('#loai_tien option').remove();
        jQuery('#loai_tien').append('<option value="">--Chọn loại tiền--<\/option>');
      }
    }
    
    function getLoaiTien(){
       var nganHang  = jQuery('#id_ngan_hang').val();
       if(nganHang != ""){
          jQuery.ajax({
            type : "POST",
            url : "getCateMoney.do",
            data : {"nganhang_id" : nganHang},
            success : function(data, textstatus){
              if(data != null){
                var lstLoaiTien = new Object();
                lstLoaiTien = JSON.parse(data[0]);
                if(lstLoaiTien.size() != 0){
                  jQuery('#loai_tien option').remove();
                  jQuery('#loai_tien').append('<option value="">--Chọn loại tiền--<\/option>');
                  for(var i = 0; i < lstLoaiTien.size(); i++){
                    jQuery('#loai_tien').append('<option value"' + lstLoaiTien[i].loai_tien + '" >'+ lstLoaiTien[i].loai_tien +'<\/option>');
                  }
                }
              }
            }
          });
       }else{
          jQuery('#loai_tien option').remove();
          jQuery('#loai_tien').append('<option value="">--Chọn loại tiền--<\/option>');
       }
    }    
</script>
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
      <tr>
        <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
        <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg">
        <span class="title2">Tra cứu số dư</span></td>
        <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
        <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
        <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
      </tr>
    </tbody>
</table> 
<html:form styleId="frmTraCuuSoDu" action="/traCuuSoDu.do">
<font color="red"><html:errors/></font>
<fieldset>
<table cellspacing="0" cellpadding="3" width="100%">
  <tr>
    <td width="5%" align="right">Kho bạc tỉnh</td>
    <td width="10%"><html:select property="id_kho_bac_tinh" styleId="id_kho_bac_tinh" onblur="getThongTinKB();" onchange="getThongTinKB();" style="width : 170px;" onkeydown="if(event.keyCode==13) event.keyCode=9;">
          <option value="">Chọn thông tin tra cứu</option>
          <logic:notEmpty name="lstNHKBTinh">
          <html:optionsCollection name="lstNHKBTinh" label="ten" value="ma" />
          </logic:notEmpty>
        </html:select>
    </td>
    <td width="5%" align="right">Kho bạc huyện</td>
    <!--20171204 thuongdt bo load ngan hang onblur="getThongTinNganHang();" onchange="getThongTinNganHang();"-->    
    <td width="6%"><html:select property="id_kho_bac_huyen" styleId="id_kho_bac_huyen" style="width : 170px;" onkeydown="if(event.keyCode==13) event.keyCode=9;">
          <option value="">Chọn thông tin tra cứu</option>
        </html:select>
    </td>
    <td rowspan="2" colspan="3" width="10%" align="left"> 
      <button type="button" style="width:50%; height: 50px;" id="traCuuKhoBac" onclick="callLov();" class="ButtonCommon" accesskey="t" >
        <span class="sortKey">D</span>anh m&#7909;c KB
      </button>
    </td>
    </tr>
  <tr>
  <td align="right">Hệ thống ngân hàng</td>
  <td>
  <!--20171204 thuongdt bo load loai tien onblur="getLoaiTien();" onchange="getLoaiTien();-->  
    <html:select property="id_ngan_hang" styleId="id_ngan_hang" style="width : 170px;"  onkeydown="if(event.keyCode==13) event.keyCode=9;">
        <option value="">Chọn thông tin tra cứu</option>
        <html:optionsCollection name="dmNH" value="ma_dv" label="ten_nh"/>  
    </html:select>
    </td>
  <td align="right">Loại tiền</td>
  <td>
    <html:select property="loai_tien" styleId="loai_tien" onblur="resetInput();" onkeydown="if(event.keyCode==13) event.keyCode=9;">
      <option value="">--Chọn loại tiền--</option>
       <html:optionsCollection name="dmTienTe" value="ma" label="ma"/>
    </html:select>
  </td>
  </tr>
  <tr>
  <td align="right">Hạn mức</td>
  <td>
    <html:select property="han_muc" styleId="han_muc" onkeydown="if(event.keyCode==13) event.keyCode=9;">
      <option value="">Tất cả</option>
      <option value="0">0 tỷ</option>
      <option value="5000000000">5 tỷ</option>
      <option value="15000000000">15 tỷ</option>
      <option value="30000000000">30 tỷ</option>
      <option value="35000000000">35 tỷ</option>
      <option value="50000000000">50 tỷ</option>
    </html:select>
  </td>
  <td align="right">Loại tài khoản</td>
  <td>
    <html:select property="loai_tai_khoan" styleId="loai_tai_khoan" onkeydown="if(event.keyCode==13) event.keyCode=9;">
      <option value="">Chọn thông tin tra cứu</option>
      <option value="01">Thanh toán tổng hợp</option>
      <option value="02">Thanh toán</option>
      <option value="03">Chuyên thu</option>
    </html:select>
  </td>
  </tr>
  <tr>
  <td align="right">Tình trạng số dư</td>
  <td><html:select property="tinh_trang_so_du" styleId="tinh_trang_so_du">
    <option value=""> Tất cả</option>
    <!--20171130 thuongdt them tieu chi han muc nho hon 0-->
    <option value="00"> Nhỏ hơn 0 </option>
    <option value="01"> > Hạn mức</option>
    <option value="02"> = Hạn mức</option>
    <option value="03"> < Hạn mức</option>
  </html:select></td>
  <td align="right">Ngày GD</td>
  <td>
    <html:text property="ngay_gd" styleId="ngay_gd" styleClass="fieldText"
        onkeypress="return numbersonly(this,event,true) "
        onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_gd');"
        onkeydown="if(event.keyCode==13) event.keyCode=9;"
        style="WIDTH: 40%;"/>
        <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
        border="0" id="ngay_gd_btn" width="10%"
        style="vertical-align:middle;"/><span style="color:red;padding-left:5px">(*)</span>
        <script type="text/javascript">
          Calendar.setup( {
          inputField : "ngay_gd", // id of the input field
          ifFormat : "%d/%m/%Y", // the date format
          button : "ngay_gd_btn"// id of the button
          });
        </script>
  </td>
  <td colspan="3">
    <input type="button" id="btnTra_cuu" value="Tra cứu">    
    <button id="btn_Thoat">Thoát</button>
  </td>
  </tr>
</table>
</fieldset>
<div style="width : 100%; height : 10px;"></div>
<%@ include file="/pages/tracuusodu/listTraCuuSoDu.inc"%>
</html:form>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUUSODU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>