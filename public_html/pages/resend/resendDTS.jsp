<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
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
<script type="text/javascript" src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/lov.js"></script>
<title>
  Xử lý truyền tin DTS
</title>
<%--<%
  String strTong_Tien = request.getAttribute("tong_tien")!=null?request.getAttribute("tong_tien").toString():"";
  String strTong_Mon = request.getAttribute("tong_mon")!=null?request.getAttribute("tong_mon").toString():"";
%>--%>
<script type="text/javascript">
  var target = '<%=(request.getParameter("targetid")!=null)?"?targetid="+request.getParameter("targetid"):""%>';
  jQuery.noConflict();
  jQuery(document).ready(function () {
      if (jQuery("#tong_so_tien").val() != null && '' != jQuery("#tong_so_tien").val())
          jQuery("#so_tien").val(CurrencyFormatted(jQuery("#tong_so_tien").val(), 'VND'));

      var msg_id_field = jQuery("#msg_id"),
      tu_ngay_field = jQuery("#tu_ngay"), 
      den_ngay_field = jQuery("#den_ngay"),
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
      frmTraCuuDTS = jQuery("#frmTraCuuDTS");
      allFields = jQuery([]).add(ten_nhkb_chuyen_field).add(ten_nhkb_nhan_field).add(trang_thai_field).add(so_tien_field)
      .add(ma_nhkb_chuyen_field).add(ma_nhkb_nhan_field).add(so_yctt_field).add(ltt_id_field).add(tu_ngay_field).add(den_ngay_field)
      .add(nhkb_chuyen_field).add(nhkb_nhan_field).add(ma_nhkb_chuyen_cm_field).add(ma_nhkb_nhan_cm_field).add(msg_id_field);
      
      jQuery("#ma_nhkb_chuyen").attr( {
          maxlength : 8
      });

      jQuery("#ma_nhkb_nhan").attr( {
          maxlength : 8
      });

//      jQuery("#ltt_id").attr( {
//          maxlength : 30
//      });

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
      
      jQuery("#dialog-form-lov-dm").dialog({
      autoOpen: false,resizable : false,
      maxHeight: "700px",
      width: "550px",
      modal: true
      });

      //dialog confirm message	
      dialog_confirm.dialog( {
          autoOpen : false, resizable : false, height : 200, width : 380, modal : true, buttons :  {
              "Có" : function () {
                  jQuery(this).dialog("close");
                  frmTraCuuD.attr( {
                      action : 'thoatView.do'
                  });
                  frmTraCuuDTS.target='';
                  frmTraCuuDTS.submit();
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
          frmTraCuuDTS.attr( {
              action : 'resendDTSListAction.do' + target
          });
          frmTraCuuDTS.submit();
      });
      //**************************BUTTON Thoat CLICK********************************************
      jQuery("#btn_thoat").click(function () {
          document.forms[0].target = '';
          dialog_confirm.html('<fmt:message key="TraCuuLTT.page.message_confirm.thoat"/>');
          dialog_confirm.dialog("open");
      });
      jQuery("#chklistAll").click(function(){
        jQuery("#colCheckList").find(':checkbox').attr('checked', this.checked);
      });

  });
  function changeValue(value) {
      if(isNaN(value))
          value = 0;
      jQuery("#tong_so_tien").val(value);
      jQuery("#so_tien").val(CurrencyFormatted(value, 'VND'));

  }
  function makeGetRequestView(id, type) {
      var urlRequest = null;
      var idFlow = id.substring(2, 5);
      var idFlowQuyetToan = id.substring(5, 8);
      var loai_lenh = jQuery("#loai_lenh option:selected").val();
      var msg_id = jQuery("#msg_id").val();
      var tu_ngay = jQuery("#tu_ngay").val();
      var den_ngay = jQuery("#den_ngay").val();
      var trang_thai = jQuery("#trang_thai option:selected").val();      
      var nhkb_chuyen = jQuery("#ma_nhkb_chuyen").val();
      var nhkb_nhan = jQuery("#ma_nhkb_nhan").val();
      var so_tien = jQuery("#so_tien").val();
      var so_yctt = jQuery("#so_yctt").val();
      var ltt_id = jQuery("#ltt_id").val();
      var kb_tinh = jQuery("#kb_tinh").val();
      var kb_huyen = jQuery("#kb_huyen").val();
      var ten_nhkb_chuyen = jQuery("#ten_nhkb_chuyen_cm").val();
      var ten_nhkb_nhan = jQuery("#ten_nhkb_nhan_cm").val();
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
      if (msg_id != null && msg_id != '') {
          urlRequest += "&msg_id=" + msg_id + "";
      }
      if (tu_ngay != null && tu_ngay != '') {
          urlRequest += "&tu_ngay=" + tu_ngay + "";
      }
      if (den_ngay != null && den_ngay != '') {
          urlRequest += "&den_ngay=" + den_ngay + "";
      }
      if (trang_thai != null && trang_thai != '') {
          urlRequest += "&trang_thai=" + trang_thai + "";
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
      window.location = urlRequest;
  }
  //**************************LINK TRANG CLICK********************************************
  function goPage(page) {
  jQuery("#frmTraCuuDTS").target='';
      jQuery("#frmTraCuuDTS").attr( {
          action : 'resendDTSAction.do' + target
      });
      jQuery("#pageNumber").val(page);
      jQuery("#frmTraCuuDTS").submit();
  }

  function go2Url(strUrl, so_yctt) {
      window.location.href = (strUrl);
      loadDetailLTTJson('lttView.do', so_yctt, '', '');
  }
  // In
  function formAction(type) {       
      checkboxes = document.getElementsByName('chklist');
      var lstId="";
      var f = document.forms[0];     
      for(var i=0, n=checkboxes.length;i<n;i++) {    
         if(checkboxes[i].checked){
            var pLoai_ht = document.getElementById("tblBlah").rows[i+1].cells[7].childNodes[0];             
            lstId += pLoai_ht.value+",";
         }
      }      
      if(lstId!=""){
        f.action = "resendLTTAction.do?type="+type+"&lstId="+lstId+"&loai_tt=DTS";
      }else{
        alert(GetUnicode('B&#7841;n ph&#7843;i ch&#7885;n &#237;t nh&#7845;t 1 l&#7879;nh thanh to&#225;n!'));
        return false;
      } 
      f.submit();
  }
  
  function getKBHuyen(kbTinh, type){
      var idkbTinh
      if(type == 'object'){
        idkbTinh = kbTinh.value;  
      }else{
        idkbTinh = kbTinh;
      }
      
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
                            jQuery('#kb_huyen').append('<option value="' + lstKBHuyen[h].ma + '">' + lstKBHuyen[h].ten + '<\/option>');
                          }
                          if(type != 'object'){
                            jQuery("#kb_huyen").val(type);
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
  function callLov(){      
      jQuery("#loai_lov").val("DMKBTCUU");
      jQuery("#ma_field_id_lov").val("ma_nhkb_nhan");
      jQuery("#ten_field_id_lov").val("ten_nhkb_nhan");
      jQuery("#id_field_id_lov").val("id_nhkb_huyen");
      jQuery("#ma_cha_field_id_lov").val("id_nhkb_tinh");
      jQuery("#dialog-form-lov-dm").dialog( "open" );      
    }  
    function getTenKhoBacLTT(ma,ma_cha) { 
      //alert(ma+"---"+ma_cha);
      document.getElementById('kb_huyen').options.length = 1;// clear du lieu option cu
      getKBHuyen(ma_cha, ma);
      jQuery("#kb_tinh").val(ma_cha);      
  }
</script>

<html:form action="/resendDTSListAction.do" styleId="frmTraCuuDTS">
  <input type="hidden" id="fieldNameForcus"/>
  <div class="app_error">
    <html:errors/>
  </div>
  <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
    <tbody>
      <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2">
             XỬ LÝ TRUYỀN TIN DTS</span></td>
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
  <table class="tableBound" border="0">
    <tbody>
      <tr>
        <td align="right" width="100">
          <label for="nguoi_lap">
            MSG_ID
          </label>
        </td>
        <td class="promptText" align="right" width="100">
          <html:text styleId="msg_id"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     styleClass="fieldText" style="width:150px;" maxlength="100"                     
                     tabindex="101"
                     property="msg_id"/>
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
          <label for="YCTT">
            <fmt:message key="TraCuuLTT.page.lable.YCTT"/>
          </label>
        </td>
        <td class="promptText" align="left" style="width:15%" valign="middle">          
          <html:text styleId="so_tchieu"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     maxlength="20" onmouseout="UnTip()"
                     onmouseover="Tip(this.value)" onfocus="autoSelect(this);"
                     style="width:90%" styleClass="fieldText"
                     tabindex="109"
                     property="so_tchieu"/>
        </td>  
        <td align="right" width="45">
          <label for="so_LTT">
            Số LTT
          </label>
        </td>
        <td class="promptText" align="left" valign="middle">          
          <html:text styleId="ltt_id"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     maxlength="20" onmouseout="UnTip()"
                     onmouseover="Tip(this.value)" onfocus="autoSelect(this);"
                     style="width:98%" styleClass="fieldText"
                     tabindex="109"
                     property="ltt_id"/>
        </td>  
      </tr>
      </tr>
      <tr>
        <td align="right" width="100">
          <label for="KBTINH">
            <fmt:message key="TraCuuLTT.page.lable.KBTINH"/>
          </label>
        </td>
        <td class="promptText" align="right">
          <logic:present name="MAT4">
            <html:select styleClass="selectBox" property="kb_tinh"
                         onchange="getKBHuyen(this, 'object')"
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
              <html:optionsCollection label="ten" value="ma" name="lstKBHuyen"/>
            </html:select>
          </logic:present>
          <logic:notPresent name="MAT4">
            <html:select styleClass="selectBox" property="kb_huyen" styleId="kb_huyen" style="width:100%;height:20px" onkeydown="if(event.keyCode==13) event.keyCode=9;" >
            <html:optionsCollection label="ten" value="ma" name="lstKBHuyen"/>
          </html:select>
          </logic:notPresent>
        </td>
        <td align="right" width="100">
          <label for="NH_chuyen">
            Ngày TT
          </label>
        </td>
        <td class="promptText" align="left" valign="middle">
          <html:text property="ngay_nhan" styleId="ngay_nhan" styleClass="fieldText"
                     onkeypress="return numbersonly(this,event,true) "
                     onblur="javascript:mask(this.value,this,'2,5','/');CheckDate(this);CheckDateOnClient('ngay_nhan');"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="107"
                     style="WIDTH: 70%;"/>
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
               border="0" id="ngay_nhan_btn" width="16%"
               style="vertical-align:middle;"/>
          <script type="text/javascript">
            Calendar.setup( {
                inputField : "ngay_nhan", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "ngay_nhan_btn"// id of the button
            });
          </script>
          
        </td>
        
      <tr>        
        <td></td><td><button type="button" onclick="callLov()" accesskey="L" >
                          <span class="sortKey">L</span>ov danh m&#7909;c KB
                  </button></td>
        <td align="right" colspan="4" width="100">
          &nbsp;
        </td>
        <td class="promptText" align="right">
          &nbsp;
        </td>
        <td align="right" width="100">
         &nbsp;
        </td>
      </tr>   
    </tbody>
  </table>
  <table class="buttonbar" align="center">
    <tr>
      <td>
        <span>
            <button  id="btn_timKiem" 
                      tabindex="113"
                      accesskey="i" type="button"
                  class="ButtonCommon">
            <fmt:message key="TraCuuLTT.page.button.search"/>
            </button>
          </span>
            
           <span> 
            <button id="btn_update_status" accesskey="i" type="button"
                        class="ButtonCommon"  value="btn_update_status" onclick="formAction('btn_update_status')">
            Update Status
            </button>
           </span>
           
           <span> 
            <button id="btn_resend" accesskey="l" type="button"
                        class="ButtonCommon"  value="btn_resend" onclick="formAction('btn_resend')">
            Resend
           </button>
           </span>
           
        <span> 
          <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon"
                        tabindex="23">
            <fmt:message key="TraCuuLTT.page.button.exit"/>
          </button>
           </span>
          </div>
      </td>
    </tr>
  </table>
  <div>
    <table style="BORDER-COLLAPSE: collapse" id="tblBlah" border="1" cellspacing="0"
           bordercolor="#999999" width="100%">
      <thead class="TR_Selected">
        <tr>
          <th width="15%">
            ID
          </th>
          <th width="14%">
            Msg_id
          </th>
          <th width="14%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.SLTT"/>
          </th>
          <th width="10%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.NH_chuyen"/>
          </th>
          <th width="10%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.NH_nhan"/>
          </th>
         
          <th width="14%">
            Ngày nhận
          </th>

          <th width="16%">
            <fmt:message key="TraCuuLTT.page.fieldset.legend.ketqua.trang_thai"/>
          </th>
          <th width="10%">
            <input type="checkbox" name="chklistAll" value="" id="chklistAll"/>
          </th>
        </tr>
      </thead>
       
      <tbody id="colCheckList">
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
                  int rowBegin = (pagingBean.getCurrentPage() -1) * 30;
                  %>
            <logic:iterate id="items" name="lstLTT" indexId="stt">
                    <tr valign="top" class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>'>
                <td width="12%" height="15px;" align="center">
                    <bean:write name="items" property="id"/>
                </td>
                <td width="12%" height="15px;" align="center">
                    <bean:write name="items" property="msg_id"/>
                </td>
                <td width="14%" align="center">
                  <bean:write name="items" property="ltt_id"/>
                </td>
                <td width="10%" align="center">
                  <logic:equal value="701" property="di_den" name="items">
                    <bean:write name="items" property="ma_kb_huyen"/>
                  </logic:equal>
                  <logic:notEqual value="701" property="di_den" name="items">
                    <bean:write name="items" property="ma_nh"/>
                  </logic:notEqual>
                </td>
                <td width="10%" align="center">
                  <logic:equal value="701" property="di_den" name="items">
                    <bean:write name="items" property="ma_nh"/>
                  </logic:equal>
                  <logic:notEqual value="701" property="di_den" name="items">
                    <bean:write name="items" property="ma_kb_huyen"/>
                  </logic:notEqual>
                </td>
              
                <td width="14%" align="center">
                    <bean:write name="items" property="ngay_nhan"/>
                </td>
                <td align="left" width="16%">
                  <bean:write name="items" property="mo_ta_trang_thai"/>
                </td>
                <td align="center" width="8%">
                    <input type="checkbox" id="checklist_<%=stt%>" name="chklist"  value='<bean:write name="items" property="id"/>' >
                </td>
              </tr>
            </logic:iterate>
            <tr>
              <td colspan="10">
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
<div id="dialog-form-lov-dm" title="Tra c&#7913;u danh m&#7909;c Kho b&#7841;c">
  <p class="validateTips"></p>
  <%@include file="/pages/lov/lovDMKBTCUULTT.jsp" %>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>