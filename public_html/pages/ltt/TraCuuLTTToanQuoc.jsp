<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page import="com.seatech.framework.AppKeys"%>
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
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/LenhThanhToan.js"
        type="text/javascript">
</script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<%
  String strTong_Mon = request.getAttribute("tong_mon")!=null?request.getAttribute("tong_mon").toString():"";
%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuLTTToanQuocResource"/>
<title>
  <fmt:message key="TraCuuLTTToanQuocResource.page.title"/>
</title>
<%
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
%>
<script type="text/javascript">
  var target = '<%=(request.getParameter("targetid")!=null)?"?targetid="+request.getParameter("targetid"):""%>';
  jQuery.noConflict();  
  jQuery(document).ready(function () {
  getTenKhoBacLTT('','');
  jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
    });
      if(jQuery("#tinh").val() == '0003'){
        try{
          jQuery("#huyen").val("0003");
        }catch(ex){}
      }
      jQuery("#tong_mon_field").val(CurrencyFormatted('<%=strTong_Mon%>', 'VND'));
      var tu_ngay_field = jQuery("#tu_ngay"), 
      den_ngay_field = jQuery("#den_ngay"),
      den_ngay_nhan_field = jQuery("#den_ngay_nhan"),
      tu_ngay_nhan_field = jQuery("#tu_ngay_nhan"),
      trang_thai_field = jQuery("#trang_thai"),
      so_tien_field = jQuery("#so_tien"),
      ma_nh_field = jQuery("#ma_nh"),
      ma_kb_tinh_field = jQuery("#tinh"),
      ma_kb_huyen_field = jQuery("#huyen"),
      tu_lenh_field = jQuery("#tu_ltt"),
      den_lenh_field = jQuery("#nhkb_nhan"),
      loai_lenh_field = jQuery("#loai_lenh"),
      dialog_message = jQuery("#dialog-message"),
      dialog_confirm = jQuery("#dialog-confirm"),
      fieldNameForcus = jQuery("#fieldNameForcus"),
      frmTraCuuLTT = jQuery("#frmTraCuuLTT");
      frmTraCuuLTT.target='';
      allFields = jQuery([]).add(tu_ngay_field).add(ma_nh_field).add(ma_kb_tinh_field).
      add(den_ngay_field).add(trang_thai_field).add(ma_kb_huyen_field).add(so_tien_field)
      .add(tu_lenh_field).add(den_lenh_field).add(loai_lenh_field).add(den_ngay_nhan_field).add(tu_ngay_nhan_field);
      ma_nh_field.focus();
      jQuery("#so_tien_temp").val((so_tien_field.val()!='' && so_tien_field.val()!='undefined' && so_tien_field.val()!='0' )?CurrencyFormatted(so_tien_field.val(),'VND'):'' );
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
//      dialog_confirm.dialog( {
//          autoOpen : false, resizable : false, height : 200, width : 380, modal : true, buttons :  {
//              "Có" : function () {
//                  jQuery(this).dialog("close");
//                  frmTraCuuLTT.attr( {
//                      action : 'thoatView.do'
//                  });
//                  frmTraCuuLTT.submit();
//              },
//              "Không" : function () {
//                  jQuery(this).dialog("close");
//              }
//          }
//      });
      //************************************ HIDE-SHOW BUTTON*****************************************************
//      var elementLinkTraSoat = document.getElementsByName("tra_soat");
//      var elementLinkViewLTT = document.getElementsByName("view_ltt");
//      if (target != null && target != '') {
//          jQuery.each(elementLinkTraSoat, function (i) {
//              jQuery("#tra_soat_" + i).show();
//          });
//          jQuery.each(elementLinkViewLTT, function (i) {
//              jQuery("#view_ltt_" + i).hide();
//          });
//      }
//      else {
//          jQuery.each(elementLinkTraSoat, function (i) {
//              jQuery("#tra_soat_" + i).hide();
//          });
//          jQuery.each(elementLinkViewLTT, function (i) {
//              jQuery("#view_ltt_" + i).show();
//          });
//      }
      //**************************BUTTON Sua CLICK********************************************
      jQuery("#btn_timKiem").click(function () {
            document.forms[0].target="";
            if(jQuery("#ma_nt").val() == null || jQuery("#ma_nt").val() == ''){
              if(jQuery("#ltt_id").val() == null || jQuery("#ltt_id").val() == ''){
                  alert("Cần nhập số lệnh thanh toán.\nNếu không hãy chọn loại tiền để tổng tiền ra chính xác.");
                  return;
              }
            }
            var inKB = jQuery('#huyen option:selected').index();
            var from_date = jQuery('#tu_ngay_nhan').val();
            var to_date = jQuery('#den_ngay_nhan').val();
            
            if(from_date == null || from_date == ''){
                alert('Phải nhập \"Từ ngày nhận\"');
                jQuery("#tu_ngay_nhan_btn").trigger("click");
            }else if(to_date == null || to_date == ''){
                alert('Phải nhập \"Đến ngày nhận\"');
                jQuery("#den_ngay_nhan_btn").trigger("click");
            }else{
	    	var dateTempArr = to_date.split('/');
	        to_date = new Date(dateTempArr[2],dateTempArr[1]-1,dateTempArr[0]);
	        dateTempArr = from_date.split('/');
	        from_date = new Date(dateTempArr[2],dateTempArr[1]-1,dateTempArr[0]);
	        to_date.setDate(to_date.getDate()-30)
          
	        if(to_date.getTime() > from_date.getTime()){
	        	var result = confirm('Truy vấn dữ liệu > 30 ngày có thể ảnh hưởng đến hiệu năng hệ thống, bạn có muốn tiếp tục không ?')
	              	if(!result){
	                	return;
	              	}
	        }
	    
                frmTraCuuLTT.attr( {
                    action : 'TraCuuLTTToanQuocList.do?inKB='+inKB + target
                });
                frmTraCuuLTT.submit();
            }
      });
      //**************************BUTTON Thoat CLICK********************************************
      jQuery("#btn_thoat").click(function () {      
          frmTraCuuLTT.attr( {
            action : 'thoatView.do'
          });
          frmTraCuuLTT.submit();
      });

  });
  function changeValue(value){
    jQuery("#so_tien_temp").val(CurrencyFormatted(value,'VND'));
    jQuery("#so_tien").val(value);
  }
  
  function makeGetRequestView(id){
    var urlRequest  ="";
    var urlParam = "";
    var ma_nh = jQuery("#ma_nh option:selected").val();
    var tinh = jQuery("#tinh option:selected").val();
    var huyen = jQuery("#huyen option:selected").val();
    var trang_thai = jQuery("#trang_thai option:selected").val();
    var loai_lenh= jQuery("#loai_lenh option:selected").val();
    var tu_ltt = jQuery("#tu_ltt").val();
    var den_ltt = jQuery("#den_ltt").val();
    var tu_ngay = jQuery("#tu_ngay").val();
    var den_ngay = jQuery("#den_ngay").val();
    var tu_ngay_nhan = jQuery("#tu_ngay_nhan").val();
    var den_ngay_nhan = jQuery("#den_ngay_nhan").val();
    var so_tien = jQuery("#so_tien").val();
    var ma_nt = jQuery("#ma_nt").val();
    var typeID = id.substring(2,5);
    if(typeID == '701'){
      urlRequest = "listLttAdd.do?action=VIEWS_LTTDIT4&";
    }else{
      urlRequest = "listLttDenAdd.do?action=VIEW_LTTDenT4&";
    }
    if(id!=null && id !=''){
      urlParam += "so_ltt="+id+"";
    }
    if(ma_nt!=null && ma_nt !=''){
      urlParam += "&ma_nt="+ma_nt+"";
    }
    if(ma_nh != null && ma_nh != ''){
      urlParam += "&ma_nh="+ma_nh+"";
    }
    if(tinh != null && tinh != ''){
      urlParam += "&tinh="+tinh+"";
    }    
    if(huyen != null && huyen != ''){
      urlParam += "&huyen="+huyen+"";
    }
    if(trang_thai != null && trang_thai != ''){
      urlParam += "&trang_thai="+trang_thai+"";
    }
    if(loai_lenh != null && loai_lenh != ''){
      urlParam += "&loai_lenh="+loai_lenh+"";
    }
    if(tu_ltt != null && tu_ltt != ''){
      urlParam += "&tu_ltt="+tu_ltt+"";
    }
    if(den_ltt != null && den_ltt != ''){
      urlParam += "&den_ltt="+den_ltt+"";
    }
    if(tu_ngay != null && tu_ngay != ''){
      urlParam += "&tu_ngay="+tu_ngay+"";
    }
    if(den_ngay != null && den_ngay != ''){
      urlParam += "&den_ngay="+den_ngay+"";
    }
    if(tu_ngay_nhan != null && tu_ngay_nhan != ''){
      urlParam += "&tu_ngay_nhan="+tu_ngay_nhan+"";
    }
    if(den_ngay_nhan != null && den_ngay_nhan != ''){
      urlParam += "&den_ngay_nhan="+den_ngay_nhan+"";
    }
    if(so_tien != null && so_tien !=0 && so_tien != ''){
      urlParam += "&so_tien="+so_tien+"";
    }
//    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//    xmlhttp.send(urlRequest);
    window.location = urlRequest+urlParam;
  }
  //**************************LINK TRANG CLICK********************************************
  function goPage(page) {
      jQuery("#frmTraCuuLTT").attr( {
          action : 'TraCuuLTTToanQuocList.do' + target
      });
      jQuery("#pageNumber").val(page);
      jQuery("#frmTraCuuLTT").submit();
  }

  function go2Url(strUrl, so_yctt) {
      window.location.href = (strUrl);
      loadDetailLTTJson('lttView.do', so_yctt, '', '');
  }
  // In
    function formAction(type){
      //var selectedPrint = jQuery("#phuongthuc_xuatfile option:selected").val();
      var f = document.forms[0];
      if(type=='print'){
        f.action = "lttRptAction.do";
      }
      newDialog = window.open('about:blank', "_form");
      f.target='_form';
      f.submit();
    }    
    //
//    function genKBHuyen(){
//      var f = document.forms[0];
//      f.action = "TraCuuLTTToanQuocView.do";
//      f.submit();
//    }
//    function getTenKBacLTT(ma_huyen,ma_tinh){
//    alert(ma_huyen+'---'+ma_tinh);
////    var kb_id;
////      var f = document.forms[0];
////      f.action = "TraCuuLTTToanQuocView.do?tinh="+ma_tinh;
////      f.submit();
//           if (ma_huyen==null || ''==ma_huyen){       
//                  if(ma_tinh!=null&&''!=ma_tinh){               
////                        kb_id=id_cha;
//                        jQuery('#tinh').val(ma_tinh);
//                  } 
//              }else if (ma_huyen!=null && ''!=ma_huyen){
//                  if(ma_tinh!=null&&''!=ma_tinh){                
////                    kb_id=id_cha;
//                  alert(ma_huyen+'---'+ma_tinh);
//                    jQuery('#tinh').val(ma_tinh);
//                    jQuery('#huyen').val(ma_huyen);
//                  }
//              }
//
//    }

function getTenKhoBacLTT(ma,ma_cha) { 

      document.getElementById('huyen').options.length = 1;// clear du lieu option cu
       var ma_tinh;
        ma_tinh=document.forms[0].tinh.value;
//       alert(ma_tinh);
//       alert('ma='+ma+'ma_cha='+ma_cha);
            if (ma==null || ''==ma){       
                  if(ma_cha!=null&&''!=ma_cha){               
                        ma_tinh=ma_cha;
                        jQuery('#tinh').val(ma_cha);
                  }else if (ma_cha==null||''==ma_cha){
                    ma_tinh=document.forms[0].tinh.value;
                  }
              }else if (ma!=null && ''!=ma){
                  if(ma_cha!=null&&''!=ma_cha){                
                    ma_tinh=ma_cha;
                    jQuery('#tinh').val(ma_cha);
                  }else if (ma_cha==null||''==ma_cha){
                    ma_tinh=document.forms[0].tinh.value;
                  }
              }
    var back_huyen = jQuery('#huyen_back').val();
    var kb_huyen="<%=kb_huyen%>";      
     if (ma_tinh !=null && ""!=ma_tinh){
      jQuery.ajax( {
          type : "POST", url : "getDMucKBTCuuLTT.do", data :  {
              "ma_tinh" : ma_tinh
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      jQuery.each(data, function (i, objectDM) {
                      // truong hop 1 - luc load khong co thang nao                  
                      document.getElementById('huyen').options.add(new Option(objectDM.kb_huyen, objectDM.ma));
                      });
                         if(document.getElementById('huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#huyen option:eq(1)").attr('selected', true);
                         }
                        else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                            if(back_huyen!=null&& ''!=back_huyen){
                              jQuery('#huyen').val(back_huyen);
                            }else if(back_huyen==null|| ''==back_huyen){
                              jQuery('#huyen option:eq(0)').attr('selected', true);
                            }
                        }
                        else if(kb_huyen!='0'){
                        jQuery('#huyen option:eq('+kb_huyen+')').attr('selected', true);
                        }
                      }
                  }

                if (ma!=null && ''!=ma){                          
                  jQuery('#huyen').val(ma);
               }
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }

  function checkTTVNhan() {
      if (jQuery("#ttv_nhan").val().length == 100) {
          alert('Tên thanh toán viên không được lớn hơn 100 kí tự');
      }
  }
  
  function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#ma_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );      
    }
  
</script>

<html:form action="/TraCuuLTTToanQuoc.do" styleId="frmTraCuuLTT">
  <input type="hidden" id="fieldNameForcus"/>
  <div class="app_error">
  <html:errors/>
</div>
  <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2"><fmt:message key="TraCuuLTTToanQuocResource.page.title"/></span></td>
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
          <fmt:message key="TraCuuLTTToanQuocResource.page.dieukien"/>
        </td>
      </tr>
   </tbody>    
  </table>
  <table  class="tableBound" border="0" align="center">
    <tbody>
      <tr>
        <td align="right" width="60">
          <label for="nh">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.nh"/>
          </label>
        </td>
        <td class="promptText" align="right" width="150">
          <html:select 
                      tabindex="102"
                       styleClass="center_pull backend_dropbox" style="width:100%;height:20px;"
                       property="ma_nh" styleId="ma_nh" >
            <html:option value="">
              <fmt:message key="TraCuuLTTToanQuocResource.page.lable.chon_nh"/>
            </html:option>
            <html:optionsCollection name="lstNganHang" value="ma_dv" label="ten_nh"/>
          </html:select>
        </td>
        <td rowspan="4" width="25" align="center">
        <button type="button" style="width:100%" onclick="callLov();" class="ButtonCommon" accesskey="t" >
                  <span class="sortKey">D</span>anh m&#7909;c KB
          </button>
        </td>
        <td align="right" width="60">
          <label for="tu_lenh">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.tuso"/>
          </label>
        </td>
        <td class="promptText" width="100" align="right" valign="middle">
          <html:text property="tu_ltt" styleId="tu_ltt"
                     styleClass="fieldText" 
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="103"
                     style="WIDTH: 97%;"/>
        </td>
        <td align="right" width="60">
          <label for="so_tien">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.sotien"/>
          </label>
        </td>
        <td class="promptText" align="right" valign="middle" width="100">
          <html:hidden  property="so_tien" styleId="so_tien"/> 
         <input type="text"  id="so_tien_temp"
             class="fieldTextRight"
             onkeydown="if(event.keyCode==13) event.keyCode=9;" onblur="changeValue(this.value);"
             tabindex="104" onkeypress="return numbersonly(this,event,true)"
             style="WIDTH: 95%;"/> 
        </td>
      </tr>
      <tr>
        <td align="right" >
          <label for="tinh">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.tinh"/>
          </label>
        </td>
        <td class="promptText">
          <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                      tabindex="105" onchange="getTenKhoBacLTT('','');"
                       styleClass="center_pull backend_dropbox" style="width:100%;height:20px;"
                       property="tinh" styleId="tinh" >
            <html:option value="">
              <fmt:message key="TraCuuLTTToanQuocResource.page.lable.chon_kbtinh"/>
            </html:option>
            <html:optionsCollection name="lstKBTinh" value="ma" label="ten"/>
          </html:select>
          
        </td>
        <td align="right" >
          <label for="den_so">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.denso"/>
          </label>
        </td>
        <td class="promptText">
          <html:text property="den_ltt" styleId="den_ltt"
                     styleClass="fieldText"                      
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"                     
                     tabindex="106"                     
                     style="WIDTH: 97%;"/>
        </td>
        <td align="right" >
          <label for="loai_lenh">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.loailenh"/>
          </label>
        </td>
        <td class="promptText">
          <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       styleClass="center_pull backend_dropbox" style="width:97%;height:20px;"
                       tabindex="107"
                       property="loai_lenh" styleId="loai_lenh">
            <html:option value="">
              <fmt:message key="TraCuuLTTToanQuocResource.page.lable.loai_lenh"/>
            </html:option>
            <html:option value="01">
              <fmt:message key="TraCuuLTTToanQuocResource.page.lable.chon_loai_lenh_chuyen_di"/>
            </html:option>
            <html:option value="02">
              <fmt:message key="TraCuuLTTToanQuocResource.page.lable.chon_loai_lenh_chuyen_den"/>
            </html:option>
            <html:option value="03">TT bằng ngoại tệ khác</html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <td align="right" >
          <label for="huyen">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.huyen"/>
          </label>
        </td>
        <td class="promptText">
              <html:select property="huyen" styleId="huyen" style="font-size:12px;width:100%"
                    onkeydown="if(event.keyCode==13) event.keyCode=9;">  
                  
                  <html:option value=""><fmt:message key="TraCuuLTTToanQuocResource.page.lable.chon_kbhuyen"/></html:option>
                  
              </html:select>
        
          <!--<logic:present name="lstKBHuyen">
            <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                        tabindex="108"
                         styleClass="center_pull backend_dropbox" style="width:100%;height:20px;"
                         property="huyen" styleId="huyen" >
              <html:option value="">
                <fmt:message key="TraCuuLTTToanQuocResource.page.lable.chon_kbhuyen"/>
              </html:option>
              <html:optionsCollection name="lstKBHuyen" value="ma" label="ten"/>
            </html:select>
          </logic:present>
          <logic:notPresent name="lstKBHuyen">
            <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                        tabindex="108"
                         styleClass="center_pull backend_dropbox" style="width:100%;height:20px;"
                         property="huyen" styleId="huyen" >
              <html:option value="">
                <fmt:message key="TraCuuLTTToanQuocResource.page.lable.chon_kbhuyen"/>
              </html:option>
            </html:select>
          </logic:notPresent>-->
        </td>
        <td align="right" >
          <label for="tungay">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.tungay"/>
          </label>
        </td>
        <td class="promptText">
          <html:text property="tu_ngay" styleId="tu_ngay" styleClass="fieldText"
                      onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="109"
                     style="WIDTH: 77%;"/>
           <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="tu_ngay_btn" width="16%"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup({
                inputField : "tu_ngay", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "tu_ngay_btn"// id of the button
            });
          </script>
        </td>
        <td align="right" >
          <label for="denngay">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.denngay"/>
          </label>
        </td>
        <td class="promptText">
          <html:text property="den_ngay" styleId="den_ngay" styleClass="fieldText"
                      onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="110"
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
      </tr>
      <tr>
        <td align="right" >
          <label for="trang_thai">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.trangthai"/>
          </label>
        </td>
        <td class="promptText" align="left" valign="middle">
          <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                      tabindex="111"
                       styleClass="center_pull backend_dropbox" style="width:100%;height:20px;"
                       property="trang_thai" styleId="trang_thai" >
            <html:option value="">
              <fmt:message key="TraCuuLTTToanQuocResource.page.lable.trangthai"/>
            </html:option>
            <html:optionsCollection name="lstTrangThai" value="rv_low_value" label="rv_meaning"/>
          </html:select>
        </td>
        <!--tu ngay,den ngay nhan-->
        <td align="right" >
          <label for="tungay_nhan">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.tungay_nhan"/>
          </label>
        </td>
        <td class="promptText">
          <html:text property="tu_ngay_nhan" styleId="tu_ngay_nhan" styleClass="fieldText"
                      onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('tu_ngay_nhan');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="112"
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
        <td align="right" >
          <label for="denngay_nhan">
            <fmt:message key="TraCuuLTTToanQuocResource.page.label.denngay_nhan"/>
          </label>
        </td>
        <td class="promptText">
          <html:text property="den_ngay_nhan" styleId="den_ngay_nhan" styleClass="fieldText"
                      onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('den_ngay_nhan');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="113"
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
        <!--tu ngay,den ngay nhan-->
      </tr>
      <tr>
        <td align="right" >
          Loại tiền
        </td>
        <td class="promptText" align="left" valign="middle">
          <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:100%;height:20px;"
                       property="ma_nt" styleId="ma_nt" >
            <html:option value="">--Chọn loại tiền--</html:option>
            <html:optionsCollection name="tienTe" value="ma" label="ma"/>
          </html:select>
        </td>
        <td></td>
                 <td align="right">Loại phí</td>
        <td align="left">
        <html:select property="phi" styleId="phi" style="width:100%;height:20px;">
        <html:option value="">--Chọn loại phí--</html:option>
        <html:option value="OUR">Phí ngoài</html:option>
        <html:option value="BEN">Phí trong</html:option>
        <html:option value="SHA">Phí chia sẻ</html:option>    
        </html:select>
        </td>
      
        </tr>
       
    </tbody>    
  </table>
  <table class="buttonbar" align="center">
            <tr>
            
              <td>
              
                <span id="tracuu">
            <button  id="btn_timKiem" 
                      tabindex="114"
                      type="button"
                    class="ButtonCommon" >
              <fmt:message key="TraCuuLTTToanQuocResource.page.button.tracuu"/>
            </button>
            </span>
            <span>
            <button id="btn_in"  type="button"
                        class="ButtonCommon"  value="print" >
                  <fmt:message key="TraCuuLTTToanQuocResource.page.button.in"/>
            </button>
            </span>
            <span>
               <button id="btn_thoat" type="button" class="ButtonCommon">
                  <fmt:message key="TraCuuLTTToanQuocResource.page.button.thoat"/>
                </button>
            </span>
            <%--
            <button id="btn_in" accesskey="i" type="button"
                        class="ButtonCommon"  value="print" onclick="formAction('export')">
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
          <th width="2%">
            <fmt:message key="TraCuuLTTToanQuocResource.page.kq.stt"/>
          </th>
          <th width="13%">
            <fmt:message key="TraCuuLTTToanQuocResource.page.kq.kb"/>
          </th>
          <th width="13%">
            <fmt:message key="TraCuuLTTToanQuocResource.page.kq.kbhuyen"/>
          </th>
          <th width="13%">
            <fmt:message key="TraCuuLTTToanQuocResource.page.kq.nh"/>
          </th>
          <th width="13%">
            <fmt:message key="TraCuuLTTToanQuocResource.page.kq.solenh"/>
          </th>
          <th width="10%">
            <fmt:message key="TraCuuLTTToanQuocResource.page.kq.ngaytt"/>
          </th>
          <th width="14%">
            <fmt:message key="TraCuuLTTToanQuocResource.page.kq.sotien"/>
          </th>
          <th width="8%">
            <fmt:message key="TraCuuLTTToanQuocResource.page.kq.loailenh"/>
          </th>
          <th width="14%">
            <fmt:message key="TraCuuLTTToanQuocResource.page.kq.trangthai"/>
          </th>
          
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
                      <td align="center">
                        <%=stt+1+rowBegin%>
                      </td>
                      <td align="left">
                        <bean:write name="items" property="ten_kb_tinh"/>
                      </td>
                      <td align="left">
                        <bean:write name="items" property="ten_kb_huyen"/>
                      </td>
                      <td align="left">
                        <bean:write name="items" property="ten_nh"/>
                      </td>
                      <td align="center">
                        <a href="javascript:makeGetRequestView('<bean:write name="items" property="id"/>')">
                          <bean:write name="items" property="id"/>
                        </a>
                      </td>
                      <td align="center">
                        <bean:write name="items" property="ngay_tt_tmp"/>
                      </td>
                      <td align="right">
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
                        <logic:equal  value="01" property="loai_lenh" name="items">
                          <td align="left">
                          Đi
                          </td>
                        </logic:equal>
                        <logic:equal  value="02" property="loai_lenh" name="items">
                        <td align="right">
                          Đến
                          </td>
                        </logic:equal>
                     
                      <td align="left">
                        <bean:write name="items" property="trang_thai"/>
                      </td>
                    </tr>
                  </logic:iterate>
                  <tr>
                  <td align="center" colspan="2">
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
               <td align="center">
                <b>&nbsp;Tổng món</b>
              </td>
              <td colspan="1" >
                <input type="text" class="fieldTextTrans" style="width:98%;text-align:right;font-weight:bold;" id="tong_mon_field"/>
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
   <html:hidden property="huyen_back" styleId="huyen_back"/>
</html:form>
<!------------------------------------------ Message confirm ------------------------------->
<div id="dialog-confirm"
     title='<fmt:message key="TraCuuLTTToanQuocResource.page.title.dialog_confirm"/>'>
  <p>
    <span style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>
<!-- ---------------------------------Message --------------------------------->
<div id="dialog-message"
     title='<fmt:message key="TraCuuLTTToanQuocResource.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUULTT.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>