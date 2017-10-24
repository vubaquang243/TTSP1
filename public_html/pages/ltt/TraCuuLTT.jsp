<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ page import="com.seatech.framework.AppConstants"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuLTTResource"/>
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
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/LenhThanhToan.js"
        type="text/javascript">
</script>
<title>
  <fmt:message key="TraCuuLTT.page.title"/>
</title>
<%
  String strTong_Mon = request.getAttribute("tong_mon")!=null?request.getAttribute("tong_mon").toString():"";
%>
<script type="text/javascript">
  var target = '<%=(request.getParameter("targetid")!=null)?"?targetid="+request.getParameter("targetid"):""%>';
  jQuery.noConflict();
  jQuery(document).ready(function () {
      if (jQuery("#tong_so_tien").val() != null && '' != jQuery("#tong_so_tien").val()){      
          if (jQuery("#ma_nt").val() != null && '' != jQuery("#ma_nt").val()){      
            jQuery("#so_tien").val(CurrencyFormatted(jQuery("#tong_so_tien").val(), jQuery("#ma_nt").val()));
          }else{
            jQuery("#so_tien").val(jQuery("#tong_so_tien").val());
          }
      }
      jQuery("#tong_mon_field").val(CurrencyFormatted('<%=strTong_Mon%>', 'VND')); 
      var tu_ngay_field = jQuery("#tu_ngay"), 
      den_ngay_field = jQuery("#den_ngay"),
      tu_ngay_nhan_field = jQuery("#tu_ngay_nhan"), 
      den_ngay_nhan_field = jQuery("#den_ngay_nhan"),
      ttv_nhan_field = jQuery("#ttv_nhan"),
      trang_thai_field = jQuery("#trang_thai"),
      so_tien_field = jQuery("#so_tien"),
      ma_nhkb_chuyen_field = jQuery("#ma_nhkb_chuyen"),
      ten_nhkb_chuyen_field = jQuery("#ten_nhkb_chuyen_cm"),
      nhkb_chuyen_field = jQuery("#nhkb_chuyen"),
      nhkb_nhan_field = jQuery("#nhkb_nhan"),
      ma_nhkb_chuyen_cm_field = jQuery("#ma_nhkb_chuyen_cm"),
      ma_nhkb_nhan_cm_field = jQuery("#ma_nhkb_nhan_cm"),
      ma_nhkb_nhan_field = jQuery("#ma_nhkb_nhan"),
      ten_nhkb_nhan_field = jQuery("#ten_nhkb_nhan_cm"),
      so_yctt_field = jQuery("#so_yctt"),
      ltt_id_field = jQuery("#ltt_id"), 
      dialog_message = jQuery("#dialog-message"),
      dialog_confirm = jQuery("#dialog-confirm"),
      fieldNameForcus = jQuery("#fieldNameForcus"),
      frmTraCuuLTT = jQuery("#frmTraCuuLTT");
      allFields = jQuery([]).add(tu_ngay_field).add(tu_ngay_nhan_field).add(den_ngay_nhan_field).add(ten_nhkb_chuyen_field).add(ten_nhkb_nhan_field).
      add(den_ngay_field).add(trang_thai_field).add(ttv_nhan_field).add(so_tien_field)
      .add(ma_nhkb_chuyen_field).add(ma_nhkb_nhan_field).add(so_yctt_field).add(ltt_id_field)
      .add(nhkb_chuyen_field).add(nhkb_nhan_field).add(ma_nhkb_chuyen_cm_field).add(ma_nhkb_nhan_cm_field);
      ttv_nhan_field.focus();
      jQuery("#ma_nhkb_chuyen").attr( {
          maxlength : 8
      });

      jQuery("#ma_nhkb_nhan").attr( {
          maxlength : 8
      });

      jQuery("#ltt_id").attr( {
          maxlength : 30
      });

      //dialog message
      dialog_message.dialog( {
          autoOpen : false, modal : true, buttons :  {
              Ok : function () {
                  jQuery(this).dialog("close");
                  var fieldForcus = fieldNameForcus.val();
                  if (fieldForcus != null && fieldForcus != '') {
                      jQuery("#" + fieldForcus).focus();
                  }

              }
          }
      });

      //dialog confirm message	
      dialog_confirm.dialog( {
          autoOpen : false, resizable : false, height : 200, width : 380, modal : true, buttons :  {
              "Có" : function () {
                  jQuery(this).dialog("close");
                  frmTraCuuLTT.attr( {
                      action : 'thoatView.do'
                  });
                  frmTraCuuLTT.target='';
                  frmTraCuuLTT.submit();
              },
              "Không" : function () {
                  jQuery(this).dialog("close");
              }
          }
      });
      //************************************ HIDE-SHOW BUTTON*****************************************************
      var elementLinkTraSoat = document.getElementsByName("tra_soat");
      var elementLinkViewLTT = document.getElementsByName("view_ltt");
      if (target != null && target != '') {
          jQuery.each(elementLinkTraSoat, function (i) {
              jQuery("#tra_soat_" + i).show();
          });
          jQuery.each(elementLinkViewLTT, function (i) {
              jQuery("#view_ltt_" + i).hide();
          });
      }
      else {
          jQuery.each(elementLinkTraSoat, function (i) {
              jQuery("#tra_soat_" + i).hide();
          });
          jQuery.each(elementLinkViewLTT, function (i) {
              jQuery("#view_ltt_" + i).show();
          });
      }
      //**************************BUTTON Sua CLICK********************************************
      jQuery("#btn_timKiem").click(function () {
          document.forms[0].target = "";
          if(jQuery("#ma_nt").val() == null || jQuery("#ma_nt").val() == ''){
            if(jQuery("#ltt_id").val().trim() == '' && jQuery('#so_yctt').val().trim() == ''){
              alert("Cần nhập số lệnh thanh toán hoặc số yêu cầu thanh toán.\nNếu không hãy chọn loại tiền để tổng tiền ra chính xác.");
              jQuery("#ltt_id").val(''); jQuery('#so_yctt').val('');
              return;
            }
          }
          var tu_ngay_nhan = jQuery("#tu_ngay_nhan").val();
          var den_ngay_nhan = jQuery("#den_ngay_nhan").val();
          if(tu_ngay_nhan == ''){
              alert("Cần nhập từ ngày nhận");
              jQuery("#tu_ngay_nhan_btn").trigger("click");
              return;
          }
          if(den_ngay_nhan == ''){
              alert("Cần nhập đến ngày nhận");
              jQuery("#den_ngay_nhan_btn").trigger("click");
              return
          }
          var dateTempArr = den_ngay_nhan.split('/');
          den_ngay_nhan = new Date(dateTempArr[2],dateTempArr[1]-1,dateTempArr[0]);
          dateTempArr = tu_ngay_nhan.split('/');
          tu_ngay_nhan = new Date(dateTempArr[2],dateTempArr[1]-1,dateTempArr[0]);
          den_ngay_nhan.setDate(den_ngay_nhan.getDate()-31)
          
          if(den_ngay_nhan.getTime() > tu_ngay_nhan.getTime()){
              /**
               * - Nguoi sua: ManhNV
               * - Ngay sua: 01/11/2016
               * - Noi dung: Chi cho phep truy van trong 30 ngay
               * - BEGIN
               * **/
//              var result = confirm('Truy vấn dữ liệu > 30 ngày có thể ảnh hưởng đến hiệu năng hệ thống, bạn có muốn tiếp tục không ?')
//              if(!result){
//                return;
//              }
                alert("Chỉ được phép truy vấn dữ liệu trong 1 tháng (30 ngày). Hãy nhập lại <từ ngày nhận>, <đến ngày nhận> hợp lệ");
                jQuery("#tu_ngay_nhan_btn").trigger("click");
                return;
              //END.
          }
          
          if (jQuery("#so_tien").val() == null || jQuery("#so_tien").val() == ''){
              jQuery("#tong_so_tien").val('')
          }
          frmTraCuuLTT.attr( {
              action : 'TraCuuLTT.do' + target
          });
          frmTraCuuLTT.submit();
      });
      //**************************BUTTON Thoat CLICK********************************************
      jQuery("#btn_thoat").click(function () {
          document.forms[0].target = '';
          dialog_confirm.html('<fmt:message key="TraCuuLTT.page.message_confirm.thoat"/>');
          dialog_confirm.dialog("open");
      });

  });
  function changeValue(value) {
     var ma_nt = jQuery("#ma_nt").val();
       if(value == '' || value =='null')
          value = 0;
          
      if(ma_nt != ''){
        value = convertCurrencyToNumber(value,jQuery("#ma_nt").val());
      }      
      jQuery("#tong_so_tien").val(value);
      
      if(ma_nt != ''){
        jQuery("#so_tien").val(CurrencyFormatted(value, ma_nt)); 
      }
  }
  function makeGetRequestView(id, type) {
      var urlRequest = null;
      var idFlow = id.substring(2, 5);
      var idFlowQuyetToan = id.substring(5, 8);
      var loai_lenh = jQuery("#loai_lenh option:selected").val();
      var ttv_nhan = jQuery("#ttv_nhan").val();
      var trang_thai = jQuery("#trang_thai option:selected").val();
      var tu_ngay = jQuery("#tu_ngay").val();
      var den_ngay = jQuery("#den_ngay").val();
      var tu_ngay_nhan = jQuery("#tu_ngay_nhan").val();
      var den_ngay_nhan = jQuery("#den_ngay_nhan").val();
      var nhkb_chuyen = jQuery("#ma_nhkb_chuyen").val();
      var nhkb_nhan = jQuery("#ma_nhkb_nhan").val();
      var so_tien = jQuery("#so_tien").val();
      var so_yctt = jQuery("#so_yctt").val();
      var ltt_id = jQuery("#ltt_id").val();
      var kb_tinh = jQuery("#kb_tinh").val();
      var kb_huyen = jQuery("#kb_huyen").val();
      var ten_nhkb_chuyen = jQuery("#ten_nhkb_chuyen_cm").val();
      var ten_nhkb_nhan = jQuery("#ten_nhkb_nhan_cm").val();
      var ma_nt = jQuery("#ma_nt").val();
      if(idFlow==701){
          urlRequest = "listLttAdd.do?action=VIEW_LTTDi&so_ltt_details=" + id + "";
      }else if(idFlow!=701){
            urlRequest = "listLttDenAdd.do?action=VIEW_LTTDen&so_ltt_details=" + id + "";
      }else if(idFlowQuyetToan==101){
          urlRequest = "quyettoan.do?action=VIEW_QUYETTOAN&so_ltt=" + id + "";
      }

      if (target != null && target != '') {
          urlRequest = "themdtslttdi.do?action=add&id=" + id;
      }
      if (ttv_nhan != null && ttv_nhan != '') {
          urlRequest += "&ttv_nhan=" + ttv_nhan + "";
      }
      if (trang_thai != null && trang_thai != '') {
          urlRequest += "&trang_thai=" + trang_thai + "";
      }
      if (tu_ngay != null && tu_ngay != '') {
          urlRequest += "&tu_ngay=" + tu_ngay + "";
      }
      if (den_ngay != null && den_ngay != '') {
          urlRequest += "&den_ngay=" + den_ngay + "";
      }
      if (tu_ngay_nhan != null && tu_ngay_nhan != '') {
          urlRequest += "&tu_ngay_nhan=" + tu_ngay_nhan + "";
      }
      if (den_ngay_nhan != null && den_ngay_nhan != '') {
          urlRequest += "&den_ngay_nhan=" + den_ngay_nhan + "";
      }
      if (nhkb_chuyen != null && nhkb_chuyen != '') {
          urlRequest += "&ma_nhkb_chuyen_cm=" + nhkb_chuyen + "";
      }
      if (nhkb_nhan != null && nhkb_nhan != '') {
          urlRequest += "&ma_nhkb_nhan_cm=" + nhkb_nhan + "";
      }
      if (so_tien != null && so_tien != '') {
          urlRequest += "&tong_sotien=" + so_tien + "";
      }
      if (so_yctt != null && so_yctt != '') {
          urlRequest += "&so_yctt=" + so_yctt + "";
      }
      if (ltt_id != null && ltt_id != '') {
          urlRequest += "&ltt_id=" + ltt_id + "";
      }
      if (loai_lenh != null && loai_lenh != '') {
          urlRequest += "&loai_lenh_tt=" + loai_lenh + "";
      }
      if (ten_nhkb_chuyen != null && ten_nhkb_chuyen != '') {
          urlRequest += "&ten_nhkb_chuyen_cm=" + ten_nhkb_chuyen + "";
      }
      if (ten_nhkb_nhan != null && ten_nhkb_nhan != '') {
          urlRequest += "&ten_nhkb_nhan_cm=" + nhkb_nhan + "";
      }
      if (kb_tinh != null && kb_tinh != '') {
          urlRequest += "&kb_tinh=" + kb_tinh + "";
      }
      if (kb_huyen != null && kb_huyen != '') {
          urlRequest += "&kb_huyen=" + kb_huyen + "";
      }
      if (ma_nt != null && ma_nt != '') {
          urlRequest += "&ma_nt=" + ma_nt + "";
      }
      window.location = urlRequest;
  }
  //**************************LINK TRANG CLICK********************************************
  function goPage(page) {
  jQuery("#frmTraCuuLTT").target='';
      jQuery("#frmTraCuuLTT").attr( {
          action : 'TraCuuLTT.do' + target
      });
      jQuery("#pageNumber").val(page);
      jQuery("#frmTraCuuLTT").submit();
  }

  function go2Url(strUrl, so_yctt) {
      window.location.href = (strUrl);
      loadDetailLTTJson('lttView.do', so_yctt, '', '');
  }
  // In
  function formAction(type) {
      //var selectedPrint = jQuery("#phuongthuc_xuatfile option:selected").val();
      var f = document.forms[0];
      if (type == 'print') {
          f.action = "lttRptAction.do";
      }
      newDialog = window.open('about:blank', "_form");
      f.target = '_form';
      f.submit();
  }
  function checkTTVNhan() {
      if (jQuery("#ttv_nhan").val().length == 100) {
          alert('Tên thanh toán viên không được lớn hơn 100 kí tự');
      }
  }
  function getKBHuyen(kbTinh){
      var idkbTinh = kbTinh.value;
      jQuery.ajax( {
          type : "POST", url : "DSachKBHuyen.do", data :  {
              "idkbTinh" : idkbTinh, "nd" : Math.random() * 100000
          },
          dataType : 'json', success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      if (data.logout != null && data.logout) {
                        document.forms[0].action = 'loginAction.do?logout=true&ma_nsd=' + data.ma_nsd + '&ip_truycap=' + data.ip_truycap;
                        document.forms[0].submit();
                        }
                      else {
                        var lstKBHuyen = new Object();
                        lstKBHuyen = JSON.parse(data[0]);
                        var countKBHuyen = 0;
                        if (lstKBHuyen != null) {
                            jQuery('#kb_huyen option').remove();
                            countKBHuyen = lstKBHuyen.size();
                        }
                        if (countKBHuyen > 0) {
                            jQuery('#kb_huyen').append('<option value="" selected="selected">Chọn kho bạc huyện<\/option>');
                            for (var h = 0;h < countKBHuyen;h++) {
                              jQuery('#kb_huyen').append('<option value="' + lstKBHuyen[h].ma_nh + '">' + lstKBHuyen[h].ten + '<\/option>');
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
  }
  function changeMaNTTraCuu(){
    if(jQuery("#ma_nt").val() != ''){
      if(jQuery("#tong_so_tien").val() != ''){
        jQuery("#so_tien").val(CurrencyFormatted(jQuery("#tong_so_tien").val(), jQuery("#ma_nt").val()));
      }
    }
    if(jQuery("#ma_nt").val() == 'VND'){
      jQuery("#td_tt_bang_nt_khac").hide();
      jQuery("#td_field_tt_bang_nt_khac").hide();
    }else{
      jQuery("#td_tt_bang_nt_khac").show();
      jQuery("#td_field_tt_bang_nt_khac").show();
    }
  }
</script>

<html:form action="/TraCuuLTT.do" styleId="frmTraCuuLTT">
  <input type="hidden" id="fieldNameForcus"/>
  <div class="app_error">
    <html:errors/>
  </div>
  <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
      <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2"><fmt:message key="TraCuuLTT.page.title"/></span></td>
            <td width="62"><img width="62" height="30" src="<%=request.getContextPath()%>/styles/images/T3.jpg"></img></td>
            <td width="20%" background="<%=request.getContextPath()%>/styles/images/T4.jpg">&nbsp;</td>
            <td width="4"><img width="4" height="30" src="<%=request.getContextPath()%>/styles/images/T5.jpg"></img></td>
      </tr>
    </tbody>
  </table>
  <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
         bordercolor="#999999" width="100%">
    <tbody>
      <tr>
        <td class="title" height="24"
            background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/bg_Title.jpg"
            colspan="8" style="text-align:left;">
          <fmt:message key="TraCuuLTT.page.dieukien"/>
        </td>
      </tr>
    </tbody>
  </table>
  <table class="tableBound">
    <tbody>
      <tr>
        <td align="right" width="100">
          <label for="nguoi_lap">
            <fmt:message key="TraCuuLTT.page.lable.nguoi_lap"/>
          </label>
        </td>
        <td class="promptText" align="right" width="100">
          <html:text styleId="ttv_nhan"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     styleClass="fieldText" style="width:150px;" maxlength="100"
                     onkeyup="checkTTVNhan();"
                     tabindex="101"
                     property="ttv_nhan"/>
        </td>
        <td align="right" width="100">
          <label for="trang_thai">
            <fmt:message key="TraCuuLTT.page.lable.trang_thai"/>
          </label>
        </td>
        <td class="promptText" width="100" align="right" valign="middle">
          <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                      tabindex="102"
                       styleClass="fieldText" style="width:145px;height:20px;"
                       property="trang_thai" styleId="trang_thai" >
            <html:option value="">
              <fmt:message key="TraCuuLTT.page.lable.chon_trang_thai"/>
            </html:option>
            <html:optionsCollection name="lstTrangThai" value="rv_low_value" label="rv_meaning"/>
          </html:select>
        </td>
        <td align="right" width="100">
          <label for="loai_lenh">
            <fmt:message key="TraCuuLTT.page.lable.loai_lenh"/>
          </label>
        </td>
        <td class="promptText" align="right" valign="middle" width="100">
          <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       styleClass="fieldText" style="width:145px;height:20px;"
                       tabindex="103"
                       property="loai_lenh_tt" styleId="loai_lenh">
            <html:option value="00">
              <fmt:message key="TraCuuLTT.page.lable.chon_loai_lenh"/>
            </html:option>
            <html:option style="COLOR: red;" value="03">
              <fmt:message key="TraCuuLTT.page.lable.chon_loai_lenh_chuyen_di"/>
            </html:option>
            <html:option value="01">
              <fmt:message key="TraCuuLTT.page.lable.chon_loai_lenh_chuyen_di_tc"/>
            </html:option>
            <html:option value="04">
              <fmt:message key="TraCuuLTT.page.lable.chon_loai_lenh_chuyen_di_gd"/>
            </html:option>
            <html:option style="COLOR: red;" value="02">
              <fmt:message key="TraCuuLTT.page.lable.chon_loai_lenh_chuyen_den"/>
            </html:option>
          </html:select>
        </td>
        <td align="right" width="100">
          <label for="so_tien">
            <fmt:message key="TraCuuLTT.page.lable.so_tien"/>
          </label>
        </td>
        <td class="promptText" align="right" width="100">
            <input type="text" tabindex="104" onkeypress="return numbersonly(this,event,true)"
                   class="fieldTextRight" onkeydown="if(event.keyCode==13) event.keyCode=9;"
                   onblur="if (this.value !='') {changeValue(this.value);}" id="so_tien" maxlength="20"  style="WIDTH: 150px;"/>
          <html:hidden property="tong_sotien" styleId="tong_so_tien"/>
        </td>
      </tr>
      <tr>
        <td align="right" width="100">
          <label for="NH_chuyen">
            <fmt:message key="TraCuuLTT.page.lable.NH_chuyen"/>
          </label>
        </td>
        <td class="promptText" colspan="3">
          <html:text property="ma_nhkb_chuyen_cm" styleId="ma_nhkb_chuyen"
                     styleClass="fieldText" 
                      maxlength="8"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="getTenNganHang('ma_nhkb_chuyen', 'ten_nhkb_chuyen_cm', 'nhkb_chuyen')"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="105"
                     style="WIDTH: 39.5%;"/>
           
          <html:text property="ten_nhkb_chuyen_cm" styleId="ten_nhkb_chuyen_cm"
                     styleClass="fieldTextTrans" readonly="true"
                      onmouseout="UnTip()"
                                       onmouseover="Tip(this.value)" 
                     style="WIDTH: 58%;font-weight"/>
           
          <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen"/>
        </td>
        <td align="right" width="100">
          <label for="NH_chuyen">
            <fmt:message key="TraCuuLTT.page.lable.NH_nhan"/>
          </label>
        </td>
        <td class="promptText" colspan="3">
          <html:text property="ma_nhkb_nhan_cm" styleId="ma_nhkb_nhan"
                     styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     maxlength="8"
                     tabindex="106"
                     onblur="getTenNganHang('ma_nhkb_nhan', 'ten_nhkb_nhan_cm', 'nhkb_nhan')"
                     style="WIDTH: 38%;"/>
           
          <html:text property="ten_nhkb_nhan_cm" styleId="ten_nhkb_nhan_cm"
                     styleClass="fieldTextTrans" readonly="true"
                      onmouseout="UnTip()"
                                       onmouseover="Tip(this.value)" 
                     style="WIDTH: 58%;font-weight"/>
           
          <html:hidden property="nhkb_nhan" styleId="nhkb_nhan"/>
        </td>
      </tr>
      <tr>
        <td align="right" width="100">
          <label for="NH_chuyen">
            <fmt:message key="TraCuuLTT.page.lable.tu_ngay"/>
          </label>
        </td>
        <td class="promptText" align="left" valign="middle">
          <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="107"
                     style="WIDTH: 77%;"/>
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="tu_ngay_btn" width="16%"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "tu_ngay", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "tu_ngay_btn"// id of the button
            });
          </script>
          
        </td>
        <td align="right" width="100">
          <label for="den_ngay">
            <fmt:message key="TraCuuLTT.page.lable.den_ngay"/>
          </label>
        </td>
        <td class="promptText" align="right">
          <html:text property="den_ngay" styleId="den_ngay"
                     styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="108"
                     style="WIDTH: 77%;"/>
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="den_ngay_btn" width="16%"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "den_ngay", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "den_ngay_btn"// id of the button
            });
          </script>
        </td>
        <td align="right" width="100">
          <label for="YCTT">
            <fmt:message key="TraCuuLTT.page.lable.YCTT"/>
          </label>
        </td>
        <td class="promptText" align="right">
          <html:text styleId="so_yctt"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     maxlength="20" onmouseout="UnTip()"
                     onmouseover="Tip(this.value)" onfocus="autoSelect(this);"
                     style="width:99%" styleClass="fieldText"
                     tabindex="109"
                     property="so_yctt"/>
        </td>
        <td align="right" width="100">
          <label for="SLTT">
            <fmt:message key="TraCuuLTT.page.lable.SLTT"/>
          </label>
        </td>
        <td class="promptText" align="right" width="100">
          <html:text styleId="ltt_id"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     style="width:150px;" property="id"
                     tabindex="110"
                     onkeypress="return numbersonly(this,event,true) "
                     styleClass="fieldText"/>
        </td>
      </tr>
      <tr>
        <td align="right" width="100">
          <label for="tu_ngay_nhan">
            <fmt:message key="TraCuuLTT.page.lable.tu_ngay_nhan"/>
          </label>
        </td>
        <td class="promptText" align="left" valign="middle">
          <html:text property="tu_ngay_nhan" styleId="tu_ngay_nhan" styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay_nhan');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="111"
                     style="WIDTH: 77%;"/>
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="tu_ngay_nhan_btn" width="16%"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "tu_ngay_nhan", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "tu_ngay_nhan_btn"// id of the button
            });
          </script>
          
        </td>
        <td align="right" width="100">
          <label for="den_ngay_nhan">
            <fmt:message key="TraCuuLTT.page.lable.den_ngay_nhan"/>
          </label>
        </td>
        <td class="promptText" align="right">
          <html:text property="den_ngay_nhan" styleId="den_ngay_nhan"
                     styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay_nhan');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="112"
                     style="WIDTH: 77%;"/>
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="den_ngay_nhan_btn" width="16%"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "den_ngay_nhan", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "den_ngay_nhan_btn"// id of the button
            });
          </script>
        </td>
        <td align="right" width="100">
          <label for="KBTINH">
            <fmt:message key="TraCuuLTT.page.lable.KBTINH"/>
          </label>
        </td>
        <td class="promptText" align="right">
          <logic:present name="MAT4">
            <html:select styleClass="selectBox" property="kb_tinh"
                         onchange="getKBHuyen(this)"
                         styleId="kb_tinh" style="width:100%;height:20px" 
                         onkeydown="if(event.keyCode==13) event.keyCode=9;" >
              <html:option value="">
              <fmt:message key="TraCuuLTT.page.kbTinh.default"/>
              </html:option>
              <html:optionsCollection label="ten" value="ma" name="lstKBTinh"/>
            </html:select>
          </logic:present>
          <logic:notPresent name="MAT4">
            <html:select styleClass="selectBox" property="kb_tinh" styleId="kb_tinh" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
            <html:optionsCollection label="ten" value="ma" name="lstKBTinh"/>
          </html:select>
          </logic:notPresent>
        </td>
        <td align="right" width="100">
          <label for="KBHUYEN">
            <fmt:message key="TraCuuLTT.page.lable.KBHUYEN"/>
          </label>
        </td>
        <td class="promptText" align="right" width="100">
          <logic:present name="MAT4">
            <html:select styleClass="selectBox" property="kb_huyen" styleId="kb_huyen" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
              <html:option value="">
              <fmt:message key="TraCuuLTT.page.kbHuyen.default"/>
              </html:option>
              <html:optionsCollection label="ten" value="ma_nh" name="lstKBHuyen"/>
            </html:select>
          </logic:present>
          <logic:notPresent name="MAT4">
            <html:select styleClass="selectBox" property="kb_huyen" styleId="kb_huyen" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
            <html:optionsCollection label="ten" value="ma_nh" name="lstKBHuyen"/>
          </html:select>
          </logic:notPresent>
        </td>
      </tr>
      <tr>
        <td align="right" width="100">
            <label for="KBTINH">
              Loại tiền
            </label>
        </td>
        <td class="promptText" align="right">
          <html:select styleClass="selectBox" property="ma_nt"
                         styleId="ma_nt" style="width:100%;height:20px" 
                         onkeydown="if(event.keyCode==13) event.keyCode=9;" onchange="changeMaNTTraCuu();" >
              <%--<html:option value="">Chọn loại tiền</html:option>--%>
              <html:option value="">Chọn loại tiền</html:option>
              <html:optionsCollection value="ma" label="ma" name="tienTe"/>
          </html:select>
        </td>
        <td align="right">Loại phí</td>
        <td align="left">
        <html:select property="phi" styleId="phi">
        <html:option value="">---Chọn loại phí---</html:option>
        <html:option value="OUR">Phí ngoài</html:option>
        <html:option value="BEN">Phí trong</html:option>
        <html:option value="SHA">Phí chia sẻ</html:option>    
        </html:select>
        </td>
       
        <td align="right" style="display:none" id ="td_field_tt_bang_nt_khac">
        <html:checkbox property="ttloai_lenh" styleId="ttloai_lenh" value ="03" />
        
        </td>
         <td align="left" style="display:none" id ="td_tt_bang_nt_khac">TT bằng ngoại tệ khác
        </td>
        <td colspan="6"></td>
      </tr>
      <%--Them doan HTML de chon phuong cach in file
       <tr>
        <td align="right" width="50%" colspan="4">    
          <label for="PThuc">
            <fmt:message key="TraCuuLTT.page.lable.phuong_thuc_in"/>
          </label>
        </td>
        <td class="promptText" colspan="4" align="left">    
          <select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                         style="fieldText;width:150px" 
                         tabindex="103" id="phuongthuc_xuatfile">
                  <option value="PDF">
                    PDF
                  </option>
                  <option value="EXCEL">
                    Excel
                  </option>
                  <option value="RFT">
                    DOC
                  </option>
            </select>
        </td>
      </tr>
      Them doan HTML de chon phuong cach in file--%>      
    </tbody>
  </table>
  <table class="buttonbar" align="center">
    <tr>
      <td>
        <span id="tracuu">
            <button  id="btn_timKiem" 
                      tabindex="113"
                      accesskey="i" type="button"
                  class="ButtonCommon">
            <fmt:message key="TraCuuLTT.page.button.search"/>
            </button>
            </span>
        <span> 
            <button id="btn_in" accesskey="i" type="button"
                        class="ButtonCommon"  value="print" onclick="formAction('print')">
            <fmt:message key="TraCuuLTT.page.button.in"/>
          </button>
           </span>
        <span> 
          <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon"
                        tabindex="23">
            <fmt:message key="TraCuuLTT.page.button.exit"/>
          </button>
           </span>
            <%--
            <button id="btn_in" accesskey="i" type="button"
             class="ButtonCommon" value="print" onclick="formAction('export')">
                  <fmt:message key="TraCuuLTT.page.button.xuat"/>
            </button>
          --%>
          </div>
      </td>
    </tr>
  </table>
  <div>
    <table style="BORDER-COLLAPSE: collapse" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <thead class="TR_Selected">
        <tr>
          <th width="12%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.YCTT"/>
          </th>
          <th width="12%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.SLTT"/>
          </th>
          <th width="8%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.NH_chuyen"/>
          </th>
          <th width="8%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.NH_nhan"/>
          </th>
          <th width="12%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.so_tien"/>
          </th>
          <th width="14%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.ngay_TT"/>
          </th>
          <th width="12%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.ngayks"/>
          </th>
          <th width="15%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.trang_thai"/>
          </th>
          <th width="9%">&nbsp;</th>
        </tr>
      </thead>
       
      <tbody>
        <logic:present name="lstLTT">
          <logic:empty name="lstLTT">
            <tr>
              <td colspan="9" align="center" style="color:red">Kh&ocirc;ng c&oacute;
                                                               kết quả thỏa
                                                               m&atilde;n !</td>
            </tr>
          </logic:empty>
          <logic:notEmpty name="lstLTT">
            <%
                  com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
                  int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
                  %>
            <logic:iterate id="items" name="lstLTT" indexId="stt">
                    <tr valign="top" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                <td width="12%" height="15px;" align="center">
                  <logic:equal value="" name="items" property="so_yctt">
                    <bean:write name="items" property="so_tham_chieu_gd"/>
                  </logic:equal>
                  <logic:notEqual value="" name="items" property="so_yctt">
                    <bean:write name="items" property="so_yctt"/>
                  </logic:notEqual>
                </td>
                <td width="12%" align="center">
                  <bean:write name="items" property="id"/>
                </td>
                <td width="8%" align="center">
                  <bean:write name="items" property="ma_nhkb_chuyen_cm"/>
                </td>
                <td width="8%" align="center">
                  <bean:write name="items" property="ma_nhkb_nhan_cm"/>
                </td>
                <td width="12%" align="right">
                  <logic:equal property="nt_id" name="items" value="177">
                    <bean:parameter id="isVN" name="boolean" value="true"/>
                    <fmt:setLocale value="vi_VI"/>
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write  name="items" property="tong_sotien"/>
                    </fmt:formatNumber>
                  </logic:equal>
                  <logic:notEqual property="nt_id" name="items" value="177">
                    <fmt:setLocale value="en_US"/>
                    <fmt:formatNumber type="currency" currencySymbol="">
                      <bean:write  name="items" property="tong_sotien"/>
                    </fmt:formatNumber>
                  </logic:notEqual>
                </td>
                <td width="14%" align="center">
                  <bean:write name="items" property="ngay_tt_tmp"/>
                </td>
                <td width="12%" align="center">
                  <logic:empty name="items" property="ngay_ks_nh">
                    <logic:empty name="items" property="ngay_gd_duyet">
                        <bean:write name="items" property="ngay_ktt_duyet"/>
                    </logic:empty>
                    <logic:notEmpty name="items" property="ngay_gd_duyet">
                        <bean:write name="items" property="ngay_gd_duyet"/>
                    </logic:notEmpty>
                  </logic:empty>
                  <logic:notEmpty name="items" property="ngay_ks_nh">
                    <bean:write name="items" property="ngay_ks_nh"/>
                  </logic:notEmpty>
                </td>
                <td align="left" width="15%">
                  <bean:write name="items" property="trang_thai"/>
                </td>
                <td align="center" width="7%">
                        <a id="tra_soat_<bean:write name="stt"/>"  name="tra_soat" href="javascript:makeGetRequestView('<bean:write name="items" property="id"/>','true')">
                          <fmt:message key="TraCuuLTT.page.button.tra_soat"/>
                        </a>
                       <a id='view_ltt_<bean:write name="stt"/>' name="view_ltt"  href="javascript:makeGetRequestView('<bean:write name="items" property="id"/>','true')">
                    <fmt:message key="TraCuuLTT.page.button.view"/>
                     </a>
                </td>
              </tr>
            </logic:iterate>
            <tr>
              <td align="center">
                <b>&nbsp;Tổng món</b>
              </td>
              <td colspan="1" >
                <input type="text" class="fieldTextTrans" style="width:98%;text-align:right;font-weight:bold;" id="tong_mon_field"/>
              </td>
              <td align="center">
                <b>&nbsp;Tổng tiền</b>
              </td>
              <td colspan="2" align="right">
                <b>
                  <c:if test="${isVN eq true}">
                    <fmt:formatNumber type="currency" maxFractionDigits="0" currencySymbol="">
                      <bean:write name="lltVO" property="tong_tien" scope="request" />
                    </fmt:formatNumber>
                  </c:if>
                  <c:if test="${isVN ne true}">
                    <fmt:formatNumber type="currency" currencySymbol="">
                      <bean:write name="lltVO" property="tong_tien" scope="request" />
                    </fmt:formatNumber>
                  </c:if>
                </b>
              </td>
              <td colspan="4">
                <div style="float:right;padding-right:40">
                  <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
                </div>
              </td>
            </tr>
            <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
          </logic:notEmpty>
        </logic:present>
      </tbody>
    </table>
  </div>
   
</html:form>
<!------------------------------------------ Message confirm ------------------------------->
<div id="dialog-confirm"
     title='<fmt:message key="TraCuuLTT.page.title.dialog_confirm"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>
<!-- ---------------------------------Message --------------------------------->
<div id="dialog-message"
     title='<fmt:message key="TraCuuLTT.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>