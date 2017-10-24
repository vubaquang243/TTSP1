<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<%@ page import="com.seatech.ttsp.dchieu.DChieu1VO"%>
<%@ page import="java.util.List"%>
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

<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"></script>

<title>
  <fmt:message key="TraCuuLTT.page.title"/>
</title>
<%
  String strTong_Mon = request.getAttribute("tong_mon")!=null?request.getAttribute("tong_mon").toString():"";
  
  String kb_huyen = request.getAttribute("kb_huyen")==null?"":request.getAttribute("kb_huyen").toString();
  String ngan_hang = request.getAttribute("ngan_hang")==null?"":request.getAttribute("ngan_hang").toString();
  String strTinh = request.getAttribute("dftinh")==null?"":request.getAttribute("dftinh").toString();
  String tcuu = request.getAttribute("tcuu")==null?"":request.getAttribute("tcuu").toString();
  String dchieu3 = request.getAttribute("dchieu3")==null?"":request.getAttribute("dchieu3").toString();
  String inlan_dc = request.getAttribute("inlan_dc")==null?"":request.getAttribute("inlan_dc").toString();
  String inxtthai = request.getAttribute("inxtthai")==null?"":request.getAttribute("inxtthai").toString();
  String initAction = request.getAttribute("initAction")==null?"":request.getAttribute("initAction").toString();
  String tong_row = request.getAttribute("tong_row")==null?"":request.getAttribute("tong_row").toString();
  String cur_page = request.getAttribute("currentPage")==null?"":request.getAttribute("currentPage").toString();
  
  String QTHTTW = request.getAttribute("QTHTTW")==null?"":request.getAttribute("QTHTTW").toString();
  String idxKB = request.getAttribute("idxKB")==null?"":request.getAttribute("idxKB").toString();
  String idxNH = request.getAttribute("idxNH")==null?"":request.getAttribute("idxNH").toString();

 
%>
<script type="text/javascript">

  var target = '<%=(request.getParameter("targetid")!=null)?"?targetid="+request.getParameter("targetid"):""%>';
  jQuery.noConflict();
  jQuery(document).init(function () {
     var tong_row="<%=tong_row%>";
     var cur_page="<%=cur_page%>";
     var total_page = Math.ceil(parseInt(tong_row)/15);
getKBHuyen('','');
      jQuery('#tong_row').val(toFormatNumber(tong_row,0,'.'));
      jQuery('#currentPage').val(cur_page);
      jQuery('#total_page').val(total_page); 
      
  });
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

  });
  
  //function cho button tim kiem
  function timKiem(){
     var idxKB = jQuery('#nhkb_huyen option:selected').index();
        var idxNH = jQuery('#ngan_hang option:selected').index() ;       
        document.getElementsByName('idxKB')[0].value = idxKB;
        document.getElementsByName('idxNH')[0].value = idxNH;

      var idkb_tinh = jQuery("#nhkb_tinh").val();
      var idkb_huyen= jQuery("#nhkb_huyen").val();
      var tthai_doichieu =  jQuery("#tthai_doichieu").val();
      var ma_nt = jQuery("#ma_nt").val();
      var ngay_dc = jQuery("#ngay_dc").val();
      var lan_dc = jQuery("#lan_dc").val();
      
      var dateTempArr = ngay_dc.split('/');
          ngay_dc = new Date(dateTempArr[2],dateTempArr[1]-1,dateTempArr[0]);

        var frm = document.forms[0];
        frm.submit();
      var sizeKBTinh = jQuery("#nhkb_tinh").length;
      if(sizeKBTinh == 1){
        jQuery("#nhkb_tinh option:eq(0)").remove();
      }
    
          
  }
  
  //function kiem soat gia tri khi thay doi
  function changeValue(value) {
      if(isNaN(value))
          value = 0;
      var ma_nt = jQuery("#ma_nt").val();
      if(ma_nt != ''){
        value = convertCurrencyToNumber(value,jQuery("#ma_nt").val());
      }      
      jQuery("#tong_so_tien").val(value);
      
      if(ma_nt != ''){
        jQuery("#so_tien").val(CurrencyFormatted(value, ma_nt)); 
      }
  }
  
  //function lay cac gia tri cho view chi tiet
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
      var kb_tinh = jQuery("#nhkb_tinh").val();
      var kb_huyen = jQuery("#nhkb_huyen").val();
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
  
  //function kiem tra ten ttv
  function checkTTVNhan() {
      if (jQuery("#ttv_nhan").val().length == 100) {
          alert('Tên thanh toán viên không được lớn hơn 100 kí tự');
      }
  }
  
  //function lay ten ngan hang
  function getNH(idObj) {
      //document.getElementById('ngan_hang').options.length = 1;// clear du lieu option cu
     jQuery("#ngan_hang option:eq(1)").remove();
      var nhkb_id = document.getElementById("nhkb_huyen").value;//document.forms[0].nhkb_huyen.value;" +
       var strTinh="<%=QTHTTW%>";
       var ngan_hang="<%=idxNH%>";

      if (nhkb_id !=null && ""!=nhkb_id){
      jQuery.ajax( {
          type : "POST", url : "getDMucNHangHuyenDC.do", data :  {
              "nhkb_id" : nhkb_id
          },
          success : function (data, textstatus) {
              if (textstatus != null && textstatus == 'success') {
                  if (data != null) {
                      jQuery.each(data, function (i, objectDM) {
                          document.getElementById('ngan_hang').options.add(new Option(objectDM.ten, objectDM.ma_nh))

                      });
                      }
                     if( strTinh==null || strTinh ==''){  // request set dftinh ==null
                          if(document.getElementById('ngan_hang').options.length==2){
                            jQuery("#ngan_hang option:eq(0)").remove();
                            //getTK_NH_KB(idObj)
                          }
                      }else if(strTinh!=null && strTinh !=''){
                         if(document.getElementById('ngan_hang').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                              jQuery("#ngan_hang option:eq(1)").attr('selected', true);
                             // getTK_NH_KB(idObj);
//                              jQuery("#ngan_hang").attr("disabled", "disabled");
                            }
                        else if(ngan_hang=='0'||ngan_hang==null||''==ngan_hang){
                        jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                       // getTK_NH_KB(idObj)
                        }
                        else if(ngan_hang!='0'){
                        jQuery('#ngan_hang option:eq('+ngan_hang+')').attr('selected', true);
                         // getTK_NH_KB(idObj)
                        }
                      }
                  }
                  if(idObj == ''){                    
                    jQuery('#ngan_hang option:eq('+ngan_hang+')').attr('selected', true);  
                                        
                  }else{
                    jQuery("#ngan_hang")[0].selectedIndex ='0';                    
                  }
          },
          error : function (textstatus) {
              alert(textstatus);
          }
      });
      }
  }
 
  //Function lay ten kho bac huyen
  function getKBHuyen(id,id_cha) { 
    var TTTT="<%=QTHTTW%>";
    document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
     var kb_id;
     
     if(TTTT!=null && ''!=TTTT){
          if (id==null || ''==id){              
                if(id_cha!=null&&''!=id_cha){               
                      kb_id=id_cha;
                      jQuery('#nhkb_tinh').val(id_cha);
                }else if (id_cha==null||''==id_cha){
                  kb_id=document.forms[0].nhkb_tinh.value;
                }               
            }else if (id!=null && ''!=id){            
                if(id_cha!=null&&''!=id_cha){                
                  kb_id=id_cha;
                  jQuery('#nhkb_tinh').val(id_cha);
                }else if (id_cha==null||''==id_cha){
                  kb_id=document.forms[0].nhkb_tinh.value;
                }
            }
       }else if(TTTT==null || ''==TTTT){
        kb_id=document.forms[0].nhkb_tinh.value;
       }
       
    var kb_huyen="<%=idxKB%>";  
    var ngan_hang="<%=idxNH%>"; 
    var strTinh="<%=QTHTTW%>";
   if (kb_id !=null && ""!=kb_id){
    jQuery.ajax( {
        type : "POST", url : "getDMucKBTHop.do", data :  {
            "kb_id" : kb_id
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    jQuery.each(data, function (i, objectDM) {
                    // truong hop 1 - luc load khong co thang nao                  
                    document.getElementById('nhkb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.id));
                    });
                    if( strTinh==null || strTinh ==''){  // request set dftinh ==null
                        if(document.getElementById('nhkb_huyen').options.length==2){
                          jQuery("#nhkb_huyen option:eq(0)").remove();
                          getNH(id);
                        }
                    }else if(strTinh!=null && strTinh !=''){
                       if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                            jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);
                          getNH(id);
                       }
                      else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                      jQuery('#ngan_hang option:eq(0)').attr('selected', true);
                          getNH(id);
                      }
                      else if(kb_huyen!='0'){
                      jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);
                          getNH(id);
                      }
                    }
                }
            }
            if (id!=null && ''!=id ){                        
                  jQuery('#nhkb_huyen').val(id);
                  
              }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
    }
}
 
 //Function duoc goi khi thya doi ma ntt
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

<div class="app_error">
  <html:errors/>
</div>

<div class="box_common_conten">
  <html:form action="TraCuuDoiChieuLTT.do" styleId="frmTraCuuDoiChieuLTT" method="post" >
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
       <td>
        <fieldset>
        <legend><font color="blue">&#272;i&#7873;u ki&#7879;n t&#236;m ki&#7871;m </font></legend>
        <div>
          <table  class="data-grid" id="data-grid" 
                                              style="width:100%" border="0"
                                             cellspacing="0" cellpadding="2" >
              <tr>
                <td width="15%" align="right" bordercolor="#e1e1e1">
                  Kho bạc tỉnh:
                </td>
                  <td width="20%">                  
                  <html:select property="kb_tinh" styleId="nhkb_tinh" style="font-size:12px;width:80%"  onchange="getKBHuyen('','','')"
                               onkeydown="if(event.keyCode==13) event.keyCode=9;"> 
                               <%if(request.getAttribute("QTHTTW") != null){
                           %>
                               <html:option value="">---Chọn kho bạc tỉnh---</html:option>
                          <%}%>     
                      <html:optionsCollection  name="lstKBTinh"  value="id_cha" label="kb_tinh"/>                    
                  </html:select>
                  </td>
                  <td width="15%" align="right" bordercolor="#e1e1e1">
                  Kho bạc huyện &nbsp;
                </td>
                  <td width="20%">
                  
                <html:select property="kb_huyen" styleId="nhkb_huyen" style="font-size:12px;width:80%" onchange="getNH('');"
                                onkeydown="if(event.keyCode==13) event.keyCode=9;">               
                               <html:option value="">-----Ch&#7885;n kho bạc huyện-----</html:option>                           
                  </html:select>
                  </td>
                  
                   <td >
                    <button type="button" style="width:120px" onclick="callLov();" class="ButtonCommon" accesskey="t" >
                      <span class="sortKey">D</span>anh m&#7909;c KB
                    </button>
                    &nbsp;&nbsp;
                    
                         
              </td>
                </tr>
            
              <tr>
                  
                  <td align="right" bordercolor="#e1e1e1">
                   Trạng thái ĐC &nbsp;
                  </td>
                  <td>
                    <html:select property="tthai_doichieu" styleId="tthai_doichieu" style="font-size:12px;width:80%" >  
                        <html:option value="">---Chọn trạng thái---</html:option>
                        <html:option value="00">Chưa đối chiếu</html:option>
                        <html:option value="01">Chênh lệch</html:option>
                        <html:option value="02">Khớp đúng</html:option>
                    </html:select>
                  </td>
                    <td colspan="1" bordercolor="#e1e1e1" align="right">
              Ngày đối chiếu </td>
              <td>

              <html:text property="ngay_dc" styleId="ngay_dc" styleClass="fieldText" 
                        onkeypress="return numbersonly(this,event,true) "
                       onblur="javascript:mask(this.value,this,'2,5','/');"
                       onkeydown="if(event.keyCode==13) event.keyCode=9;" style="width:60%"
                       tabindex="107" />
                  <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                     border="0" id="ngay_dc_btn" 
                     style="vertical-align:middle;width:20"/>   
                    <script type="text/javascript">
                          Calendar.setup( {
                              inputField : "ngay_dc", // id of the input field
                              ifFormat : "%d/%m/%Y", // the date format
                              button : "ngay_dc_btn"// id of the button
                          });
                    </script> &nbsp;&nbsp;&nbsp;
                </td>
                
               <td width="20%">
                    <button style="width: 120px;" id="btn_timKiem" 
                              type="button" onclick="timKiem()"
                          class="ButtonCommon">
                    Tìm Kiếm
                    </button>
                  </td>
              </tr>
             
              
           </table>
           
         </div>
        </fieldset>
       </td>
      </tr>
      <%
        com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
      int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
      %>
      <tr>
        <td>
         <fieldset>
            <legend>K&#7871;t qu&#7843; tra cứu</legend>
            <div>
              <table width="100%" cellspacing="0" cellpadding="2" class="navigateable focused"
                 bordercolor="#e1e1e1" border="1" align="center"
                 style="BORDER-COLLAPSE: collapse">
                <thead>
                <tr>
                <th class="promptText" bgcolor="#f0f0f0" width="5%">
                  <div align="center" >
                    STT
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center" >
                    Số hiệu KB
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center" >
                    Mã Kho Bạc
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="20%">
                  <div align="center">
                    Tên kho bạc
                  </div>
                </th>
                
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    Ngày đối chiếu
                  </div>
                </th>               
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    Trạng thái đối chiếu
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                     Tổng lệnh đi
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    Tổng lệnh đến
                  </div>
                </th>
                <th class="promptText" bgcolor="#f0f0f0" width="10%">
                  <div align="center">
                    Chi tiết
                  </div>
                </th>
                </tr>
                </thead>
              <tbody class="navigateable focused" cellspacing="0" style="width:100%" cellpadding="1" bordercolor="#e1e1e1">
              
              <logic:notEmpty name="lstTraCuu">
              <logic:present name="lstTraCuu" >          
                <logic:iterate id="items" name="lstTraCuu" indexId="stt">
                <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                  <td align="center"> 
                    <%=stt+1+rowBegin%>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="shkb"/>
                  </td>
                  <td align="center">
                  <div title="<bean:write name="items" property="ma_nh"/>" style="text-overflow:ellipsis;white-space:nowrap;   overflow:hidden; font-size:11px">
                    <bean:write name="items" property="ma_nh"/>
                    </div>
                  </td>
                  <td align="center">
                  <div title="<bean:write name="items" property="kb_huyen"/>" style="text-overflow:ellipsis;white-space:nowrap;   overflow:hidden; font-size:11px">
                    <bean:write name="items" property="kb_huyen"/>
                    </div>
                  </td>
                  
                  <td align="center">
                    <bean:write name="items" property="ngay_dc"/>
                  </td>                
                  <td align="center">
                    <%-- <bean:write name="items" property="trang_thai"/> --%>
                    <logic:equal value="01" property="trang_thai" name="items">
                        Chênh lệch
                    </logic:equal>
                    <logic:equal value="02" property="trang_thai" name="items">
                        Khớp đúng
                    </logic:equal>
                    <logic:equal value="00" property="trang_thai" name="items">
                        Chưa Đối chiếu
                    </logic:equal>
                    <logic:equal value="04" property="trang_thai" name="items">
                        Xác nhận-Đã xử lý
                    </logic:equal>
                    <logic:equal value="03" property="trang_thai" name="items">
                        Hủy
                    </logic:equal>
                  </td>    
                  <td align="center">
                   <bean:write name="items" property="tong_ltt_di"/>
                  </td>
                  <td align="center">
                    <bean:write name="items" property="tong_ltt_den"/>
                  </td>
                  <td align="center">
                    <!--<a href="<html:rewrite page="/ViewKQDCLTT.do"/>?id=<bean:write name="items" property="id"/>&ma_nh=<bean:write name="items" property="ma_nh"/>&ngay_dc=<bean:write name="items" property="ngay_dc"/>&trang_thai=<bean:write name="items" property="trang_thai"/>&shkb=<bean:write name="items" property="shkb"/>">Chi tiet </a> -->
                    <a href=" javascript:viewChitiet('<bean:write name="items" property="id"/>')">Chi tiet </a>
                  </td>                                                          
                </tr>                
                </logic:iterate>
                </logic:present>
              </logic:notEmpty>
              <logic:empty name="lstTraCuu">
                <tr>
                <td colspan="11">
                  <font color="red">Không có bản ghi</font>
                </td>
                </tr>
               </logic:empty> 
              </tbody>
              </table>
            </div>
          </fieldset>
          </td>
      </tr>
      <tr>
          <td >
          T&#7893;ng s&#7889; b&#7843;n ghi: <html:text property="tong_row" readonly="true" style="text-align:left; border:0;width:140px;font-weight:bold;font-size:11px" value="0" styleId="tong_row"/>
          <html:text property="currentPage" onkeydown="arrowUpDownTCuuDC(event)" style="text-align:right; border:1;width:20px;font-weight:bold;font-size:11px" value="0" styleId="currentPage"/>/<html:text property="total_page" readonly="true" style="text-align:left; border:0;width:20px;font-weight:bold;font-size:11px" value="0" styleId="total_page"/>
          <a href="#" onclick="goPage()">&#272;&#7871;n</a>
          </td>
      </tr>
      <tr>
        <td align="right">
          <button type="button" onclick="check('close')" class="ButtonCommon" accesskey="o">
                          Th<span class="sortKey">o</span>&#225;t
          </button>
        </td>
      </tr>
      
    </table> 
    <html:hidden property="pageNumber" styleId="pageNumber"/>
  <input type="hidden" name ="idxKB"/>
   <input type="hidden" name ="idxNH"/>
   <input type="hidden" name ="id_kq"/>
  </html:form>
</div>
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUU.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>
  
<script type="text/javascript">
     jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
    });
    
    //Function load dialog tra cuu kho bac theo ma
function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#id_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );
      
    }
    
    //function lay ten kho bac
function getTenKhoBacDC(id,id_cha) { 
    var TTTT="<%=QTHTTW%>";
    document.getElementById('nhkb_huyen').options.length = 1;// clear du lieu option cu
     var kb_id;
     if(TTTT!=null && ''!=TTTT){
          if (id==null || ''==id){       
                if(id_cha!=null&&''!=id_cha){               
                      kb_id=id_cha;
                      jQuery('#nhkb_tinh').val(id_cha);
                }else if (id_cha==null||''==id_cha){
                  kb_id=document.forms[0].nhkb_tinh.value;
                }
            }else if (id!=null && ''!=id){
                if(id_cha!=null&&''!=id_cha){                
                  kb_id=id_cha;
                  jQuery('#nhkb_tinh').val(id_cha);
                }else if (id_cha==null||''==id_cha){
                  kb_id=document.forms[0].nhkb_tinh.value;
                }
            }
       }else if(TTTT==null || ''==TTTT){
        kb_id=document.forms[0].nhkb_tinh.value;
       }
    var kb_huyen="<%=idxKB%>";   
    var strTinh="<%=strTinh%>";
   if (kb_id !=null && ""!=kb_id){
    jQuery.ajax( {
        type : "POST", url : "getDMucKBTHop.do", data :  {
            "kb_id" : kb_id
        },
        success : function (data, textstatus) {
            if (textstatus != null && textstatus == 'success') {
                if (data != null) {
                    jQuery.each(data, function (i, objectDM) {
                    // truong hop 1 - luc load khong co thang nao                  
                    document.getElementById('nhkb_huyen').options.add(new Option(objectDM.kb_huyen, objectDM.id));
                    });
                    if( strTinh==null || strTinh ==''){  // request set dftinh ==null
                        if(document.getElementById('nhkb_huyen').options.length==2){
                          jQuery("#nhkb_huyen option:eq(0)").remove();

                        }
                    }else if(strTinh!=null && strTinh !=''){
                       if(document.getElementById('nhkb_huyen').options.length==2){ // select dong thu 2 neu select box co 2 value voi user cap tinh
                            jQuery("#nhkb_huyen option:eq(1)").attr('selected', true);

                       }
                      else if(kb_huyen=='0'||kb_huyen==null||''==kb_huyen){
                      jQuery('#ngan_hang option:eq(0)').attr('selected', true);

                      }
                      else if(kb_huyen!='0'){
                      jQuery('#nhkb_huyen option:eq('+kb_huyen+')').attr('selected', true);

                      }
                    }
                }
            }
              if (id!=null && ''!=id){                        
                        jQuery('#nhkb_huyen').val(id);
                    }
        },
        error : function (textstatus) {
            alert(textstatus);
        }
    });
    }
}    
    
loadKB();
function loadKB(){
 var idxKB = '<%=idxKB%>';  
 var idxNH = '<%=idxNH%>';  
 
  var vtinh = jQuery('#nhkb_tinh option:selected').index();
  if(vtinh != null && vtinh !=''){  
    getKBHuyen('',idxKB,idxNH);
  }
   
}

//function duoc goi khi bam xem view chi tiet
  function viewChitiet(id_kq){
    var idxKB = jQuery('#nhkb_huyen option:selected').index();
        var idxNH = jQuery('#ngan_hang option:selected').index() ;       
        document.getElementsByName('idxKB')[0].value = idxKB;
        document.getElementsByName('idxNH')[0].value = idxNH;
        var ngay_dc = jQuery("#ngay_dc").val();
        var dateTempArr = ngay_dc.split('/');
        ngay_dc = new Date(dateTempArr[2],dateTempArr[1]-1,dateTempArr[0]);
        var f = document.forms[0];
        document.getElementsByName('id_kq')[0].value = id_kq;
        f.action = 'ViewKQDCLTT.do';
        f.submit();
      var sizeKBTinh = jQuery("#nhkb_tinh").length;
      if(sizeKBTinh == 1){
        jQuery("#nhkb_tinh option:eq(0)").remove();
      }
  }
  function check(type,ma_nt) {  
    var f = document.forms[0];
    if (type == 'close') {
      var tcuu="<%=tcuu%>";
      if(tcuu!=null && ""!=tcuu){
        f.action = 'mainAction.do'+tcuu;
      }else if (tcuu==null || ""==tcuu){
        f.action = 'mainAction.do';
      } 
      f.submit();
    }
  }



</script>