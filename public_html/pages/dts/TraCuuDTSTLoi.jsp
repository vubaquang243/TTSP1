<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ page import="com.seatech.framework.AppKeys"%>
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuDTSTLoiResource"/>
<%@ include file="/includes/ttsp_header.inc"%>
<link type="text/css" rel="stylesheet" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/style.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery.ui.all.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/ui.jqgrid.css"/>
<link rel="stylesheet"  type="text/css" media="screen" href="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/css/jquery-ui-1.8.2.custom.css"/>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/jquery-ui-1.8.11.custom.min.js"  type="text/javascript"></script>
<script src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/dien.tra.soat.tloi.js"  type="text/javascript"></script>
<title><fmt:message key="tra_cuu_dts_traloi.page.title"/></title>
<title>
  <fmt:message key="tra_cuu_dts_traloi.page.title"/>
</title>
<script type="text/javascript">
  jQuery(document).ready(function () {
  jQuery("#ttv_nhan").focus();
      //*************************************************KHAI BAO BIEN ***********************************
      var den_ngay_lap_ltt_field = jQuery("#den_ngay_lap_lenh"), tu_ngay_lap_ltt_field = jQuery("#tu_ngay_lap_lenh"), den_ngay_lap_field = jQuery("#den_ngay_lap_dien"), tu_ngay_lap_field = jQuery("#tu_ngay_lap_dien"), ttv_nhan_field = jQuery("#ttv_nhan"), trang_thai_field = jQuery("#trang_thai"), loai_ltt_field = jQuery("#loai_ltt"), loai_tra_soat_field = jQuery("#loai_tra_soat"), ma_nhkb_chuyen_field = jQuery("#nhkb_chuyen"), ma_nhkb_nhan_field = jQuery("#nhkb_nhan"), id_field = jQuery("#id"), ltt_id_field = jQuery("#ltt_id"), dialog_message = jQuery("#dialog-message"), dialog_confirm = jQuery("#dialog-confirm"), fieldNameForcus = jQuery("#fieldNameForcus"), frmTraCuuDTS = jQuery("#frmTraCuuDTS");
     allFields = jQuery([]).add(den_ngay_lap_ltt_field).add(tu_ngay_lap_ltt_field).add(den_ngay_lap_field).add(trang_thai_field).add(ttv_nhan_field).add(ma_nhkb_chuyen_field).add(ma_nhkb_nhan_field).add(loai_ltt_field).add(ma_nhkb_chuyen_field).add(ma_nhkb_nhan_field).add(ltt_id_field).add(tu_ngay_lap_field).add(loai_tra_soat_field).add(id_field);
      //*****************************************************reset form search ***********************************
      if ('<%=request.getParameter("search_dts")%>' == null || '<%=request.getParameter("search_dts")%>' == 'null') {
       //   allFields.val('');
      }
      //************************************ dialog message ******************************************************
      dialog_message.dialog( {
          autoOpen : false, height : 200, width : 380, modal : true, buttons :  {
              Ok : function () {
                  jQuery(this).dialog("close");
                  var fieldForcus = fieldNameForcus.val();
                  if (fieldForcus != null && fieldForcus != '') {
                      jQuery("#" + fieldForcus).focus();
                  }

              }
          }
      });

      //************************************ dialog confirm message	***********************************************
      dialog_confirm.dialog( {
          autoOpen : false, resizable : false, height : 200, width : 380, modal : true, buttons :  {
              "Có" : function () {
                  jQuery("#frmTraCuuDTS").attr( {
                      action : '<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/thoatView.do'
                  });
                  jQuery("#frmTraCuuDTS").submit();
                  jQuery(this).dialog("close");
              },
          "Không" : function () {
                  jQuery(this).dialog("close");
              }
          }
      });

      //**************************BUTTON Sua CLICK********************************************
      jQuery("#btn_timKiem").click(function () {
          var flag = true;
          allFields.removeClass('ui-state-error');
          var tu_ngay_lap = tu_ngay_lap_field.val(), den_ngay_lap = den_ngay_lap_field.val(), tu_ngay_lap_ltt = tu_ngay_lap_ltt_field.val(), den_ngay_lap_ltt = den_ngay_lap_ltt_field.val();
          dv_trasoat = ma_nhkb_chuyen_field.val();
          dv_nhan_trasoat = ma_nhkb_nhan_field.val();
          if ((tu_ngay_lap != null && tu_ngay_lap != '') && (den_ngay_lap != null && den_ngay_lap != '')) {
              if (CompareDate(tu_ngay_lap, den_ngay_lap) ==  - 1) {
                  alert('Từ ngày lập điện không được lớn hơn đến ngày lập điện');
                  flag = false;
                  return;
              }
          }
          if ((tu_ngay_lap_ltt != null && tu_ngay_lap_ltt != '') && (den_ngay_lap_ltt != null && den_ngay_lap_ltt != '')) {
              if (CompareDate(tu_ngay_lap_ltt, den_ngay_lap_ltt) ==  - 1) {
                  alert('Từ ngày lập lệnh thanh toán không được lớn hơn đến ngày lập lệnh thanh toán');
                  flag = false;
                  return;
              }
          }
          if ((dv_trasoat != null && dv_trasoat != '') && (dv_nhan_trasoat != null && dv_nhan_trasoat != '')) {
              if (dv_trasoat == dv_nhan_trasoat) {
                  alert('Đơn vị tra soát phải khác đơn vị nhận tra soát');
                  flag = false;
                  return;
              }
          }
          if ((dv_trasoat != null && dv_trasoat != '') || (dv_nhan_trasoat != null && dv_nhan_trasoat != '')) {
              if (dv_trasoat != '<%= request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION) %>' && dv_nhan_trasoat != '<%= request.getSession().getAttribute(AppConstants.APP_NHKB_ID_SESSION) %>') {
                  alert('Đơn vị tra soát hoặc đơn vị nhận tra soát phải là kho bạc');
                  flag = false;
                  return;
              }
          }
          if (flag) {
              frmTraCuuDTS.submit();
          }
      });
      //**************************BUTTON Thoat CLICK********************************************
      jQuery("#btn_thoat").click(function () {
          if (confirm('Bạn có chắc chắn muốn thoát khỏi chức năng này?') == false)
              return false;
          else {
              document.forms[0].action = 'mainAction.do';
              document.forms[0].submit();
          }
      });
//      jQuery("#btn_in").click(function () {
//             
//              startDownload(url);
//      });
  });
  //**************************LINK TRANG CLICK********************************************
  function goPage(page) {
      jQuery("#frmTraCuuDTS").attr( {
          action : '<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/tracuudtstraloi.do'
      });
      jQuery("#pageNumber").val(page);
      jQuery("#frmTraCuuDTS").submit();
  }
  
function startDownload(url) {   
      document.forms[0].action = 'tracuudtstraloi.do';
       document.forms[0].submit();
      window.open(url,'report'); 
} 

  //**************************validFormatDate********************************************
  function validFormatDate(field) {
      allFields.removeClass('ui-state-error');
      var fieldId = field.id;
      if (!CheckDate(field)) {
          jQuery("#" + fieldId).val('');
          jQuery("#" + fieldId).addClass("ui-state-error");
          jQuery("#" + fieldId).focus();
      }
  }
  function makeGetRequest(id){
    var ulrlink="";
    if (jQuery("#ttv_nhan").val().trim()!="") {
    ulrlink +="&ttv_nhan=" +jQuery("#ttv_nhan").val().trim();
    }
    if (jQuery("#ltt_id").val().trim()!="") {
    ulrlink +="&ltt_id=" +jQuery("#ltt_id").val().trim();
    }
    if (jQuery("#trang_thai").val().trim()!="") {
    ulrlink +="&trang_thai=" +jQuery("#trang_thai").val().trim();
    }
    if (jQuery("#nhkb_chuyen").val().trim()!="") {
    ulrlink +="&nhkb_chuyen=" +jQuery("#nhkb_chuyen").val().trim();
    }
    if (jQuery("#nhkb_nhan").val().trim()!="") {
    ulrlink +="&nhkb_nhan=" +jQuery("#nhkb_nhan").val().trim();
    }
    if (jQuery("#tu_ngay_lap_dien").val().trim()!="") {
    ulrlink +="&tu_ngay_lap_dien=" +jQuery("#tu_ngay_lap_dien").val().trim();
    }
     if (jQuery("#den_ngay_lap_dien").val().trim()!="") {
    ulrlink +="&den_ngay_lap_dien=" +jQuery("#den_ngay_lap_dien").val().trim();
    }
     if (jQuery("#tu_ngay_lap_lenh").val().trim()!="") {
    ulrlink +="&tu_ngay_lap_lenh=" +jQuery("#tu_ngay_lap_lenh").val().trim();
    }
     if (jQuery("#den_ngay_lap_lenh").val().trim()!="") {
    ulrlink +="&den_ngay_lap_lenh=" +jQuery("#den_ngay_lap_lenh").val().trim();
    }
    if (jQuery("#id").val().trim()!="") {
    ulrlink +="&sodts=" +jQuery("#id").val().trim();
    }
    window.location = "dtsoattloiView.do?action=VIEW_DETAIL&id="+id +ulrlink;
  }

</script>
<input type="hidden" id="fieldNameForcus"/>
<html:form action="/tracuudtstraloi.do" styleId="frmTraCuuDTS">
  <html:hidden property="search_dts" value="true"/>
  <html:hidden property="pageNumber" value="1" styleId="pageNumber"/>
  <table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
    <tbody>
      <tr >
        <td width="13">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T1.jpg"
               width="13" height="30"/>
        </td>
       <td background="<%=request.getContextPath()%>/styles/images/T2.jpg"
              width="75%">
            <span class="title2" style="height:15px;">
              <font color="#006699" size="2" >
                <fmt:message key="tra_cuu_dts_traloi.page.title"/>
              </font></span>
        </td>
        <td width="62">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T3.jpg"
               width="62" height="30"/>
        </td>
        <td background="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T4.jpg"
            width="20%">&nbsp;</td>
        <td width="4">
          <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/images/T5.jpg"
               width="4" height="30"/>
        </td>
        
      </tr>
    </tbody>
  </table>

      <table  border="1" cellspacing="0" class="bordertableChungTu" width="100%">
      <tbody>
        <tr> 
          <td class="title" >
            <span>
              <fmt:message key="tra_cuu_dts_traloi.page.dieukien"/></span>
          </td>
        </tr>
      
        <tr>
          <td> 
           
          <table width="100%" cellspacing="0" cellpadding="0" align="center" class="bordertableChungTu">
           <tr>
            <td align="right" width="14%">
              <label for="nguoi_lap">
                <fmt:message key="tra_cuu_dts_traloi.page.lable.nguoi_lap"/>
              </label>
            </td>
            <td class="promptText" align="right" width="36%">
              <html:text styleId="ttv_nhan" property="ttv_nhan"  maxlength="50"
                         styleClass="fieldText" style="width:164;"
                         onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                         onfocus="textfocus(this);"/>
            </td>
            <td width="14%" align="right">
              <label for="trang_thai">
                <fmt:message key="tra_cuu_dts_traloi.page.lable.trang_thai"/>
              </label>
            </td>
            <td class="promptText">
              <html:select styleClass="selectBox" property="trang_thai"
                           styleId="trang_thai" style="width:164;"
                           onblur="textlostfocus(this);"  onkeydown="nextFocus();"
                           onfocus="textfocus(this);">
                <html:option value="">
                  <fmt:message key="tra_cuu_dts_traloi.page.option.trang_thai.default"/>
                </html:option>
                <html:option value='00'>Chờ xử l&yacute; </html:option>
                <html:option value='02'>Chờ KTT duyệt </html:option>
                <html:option value='03'>Đ&atilde; duyệt</html:option>
               </html:select>
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="ma_don_vi_tra_soat">
                <fmt:message key="tra_cuu_dts_traloi.page.lable.don_vi_tra_soat"/>
              </label>
            </td>
            <td class="promptText">
              <html:select styleClass="selectBox" property="ma_don_vi_tra_soat" 
                           styleId="ma_don_vi_tra_soat" style="width:164;"
                           onblur="getTenNganHang_KB('ma_don_vi_tra_soat', 'ten_don_vi_tra_soat', 'nhkb_chuyen');textlostfocus(this);"
                           onfocus="textfocus(this);"
                           onkeydown="nextFocus();">
                <logic:notEmpty name="colDMNH">
                  <html:option value="">
                    <fmt:message key="tra_cuu_dts_traloi.page.option.don_vi_ts.default"/>
                  </html:option>
                  <html:optionsCollection name="colDMNH" value="ma_nh"
                                          label="ma_nh"/>
                </logic:notEmpty>
              </html:select>
               
              <html:text property="ten_don_vi_tra_soat"
                         styleId="ten_don_vi_tra_soat"
                         styleClass="fieldTextTrans" readonly="true"
                         onmouseout="UnTip()" onmouseover="Tip(this.value)"
                         style="WIDTH: 120px;"/>
               
              <html:hidden property="nhkb_chuyen" styleId="nhkb_chuyen"/>
            </td>
            <td align="right" width="13%">
              <label for="ma_don_vi_nhan_tra_soat">
                <fmt:message key="tra_cuu_dts_traloi.page.lable.don_vi_nhan_tra_soat"/>
              </label>
            </td>
            <td class="promptText" colspan="3">
              <html:select styleClass="selectBox"
                           property="ma_don_vi_nhan_tra_soat"
                           styleId="ma_don_vi_nhan_tra_soat" style="width:164;"
                           onblur="getTenNganHang_KB('ma_don_vi_nhan_tra_soat', 'ten_don_vi_nhan_tra_soat', 'nhkb_nhan');textlostfocus(this);"
                           onfocus="textfocus(this);"
                           onkeydown="nextFocus();">
                <logic:notEmpty name="colDMNH">
                  <html:option value="">
                    <fmt:message key="tra_cuu_dts_traloi.page.option.don_vi_nhan_ts.default"/>
                  </html:option>
                  <html:optionsCollection name="colDMNH" value="ma_nh"
                                          label="ma_nh"/>
                </logic:notEmpty>
              </html:select>
               
              <html:text property="ten_don_vi_nhan_tra_soat"
                         styleId="ten_don_vi_nhan_tra_soat"
                         styleClass="fieldTextTrans"  readonly="true"
                         onmouseout="UnTip()" onmouseover="Tip(this.value)" style="WIDTH: 120px;"/>
               
              <html:hidden property="nhkb_nhan" styleId="nhkb_nhan"/>
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="id">
                <fmt:message key="tra_cuu_dts_traloi.page.lable.so_dien_tra_soat"/>
              </label>
            </td>
            <td class="promptText" align="left">
              <html:text property="id" styleId="id" styleClass="fieldText" style="width:164;"
                         onKeyPress="return numbersonly(this,event,true)"  onkeydown="nextFocus();"
                         onblur="textlostfocus(this);"
                         onfocus="textfocus(this);"/>
            </td>
            <td align="right">
              <label for="ltt_id">
                <fmt:message key="tra_cuu_dts_traloi.page.lable.so_lenh_thanh_toan"/>
              </label>
            </td>
            <td class="promptText" align="right">
              <html:text property="ltt_id" styleId="ltt_id" styleClass="fieldText"
                         style="width:163;"  onkeydown="nextFocus();"
                         onKeyPress="return numbersonly(this,event,true)"
                         onblur="textlostfocus(this);"
                         onfocus="textfocus(this);"/>
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="tu_ngay_lap_dien">
                <fmt:message key="tra_cuu_dts_traloi.page.lable.tu_ngay_lap_dien"/>
              </label>
            </td>
            <td class="promptText" align="right">
              <html:text styleId="tu_ngay_lap_dien"
                         onfocus="textfocus(this);"  onkeydown="nextFocus();"
                         onblur="validFormatDate(this);textlostfocus(this);"
                         styleClass="fieldText" property="tu_ngay_lap_dien"
                         style="width:138;"/>
               
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="tu_ngay_lap_btn" width="20"
                   style="vertical-align:middle;"/>
               
              <span>
                <fmt:message key="tra_cuu_dts_traloi.page.lable.format_date"/></span>
              <script type="text/javascript">
                Calendar.setup( {
                    inputField : "tu_ngay_lap_dien", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "tu_ngay_lap_btn"// id of the button
                });
              </script>
            </td>
            <td align="right">
              <label for="den_ngay_lap_dien">
                <fmt:message key="tra_cuu_dts_traloi.page.lable.den_ngay_lap_dien"/>
              </label>
            </td>
            <td class="promptText" align="right">
              <html:text styleId="den_ngay_lap_dien" property="den_ngay_lap_dien"
                         onfocus="textfocus(this);"  onkeydown="nextFocus();"
                         onblur="validFormatDate(this);textlostfocus(this);"
                         styleClass="fieldText" style="width:138;"/>
               
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="den_ngay_lap_btn" width="20"
                   style="vertical-align:middle;"/>
               
              <span>
                <fmt:message key="tra_cuu_dts_traloi.page.lable.format_date"/></span>
              <script type="text/javascript">
                Calendar.setup( {
                    inputField : "den_ngay_lap_dien", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "den_ngay_lap_btn"// id of the button
                });
              </script>
            </td>
          </tr>
          <tr>
            <td align="right">
              <label for="tu_ngay_lap_lenh">
                <fmt:message key="tra_cuu_dts_traloi.page.lable.tu_ngay_lap_ltt"/>
              </label>
            </td>
            <td class="promptText" align="right">
              <html:text styleId="tu_ngay_lap_lenh" property="tu_ngay_lap_lenh"
                         styleClass="fieldText" style="width:138;"
                         onfocus="textfocus(this);"  onkeydown="nextFocus();"
                         onblur="validFormatDate(this);textlostfocus(this);"/>
               
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="tu_ngay_lap_ltt_btn" width="20"
                   style="vertical-align:middle;"/>
               
              <span>
                <fmt:message key="tra_cuu_dts_traloi.page.lable.format_date"/></span>
              <script type="text/javascript">
                Calendar.setup( {
                    inputField : "tu_ngay_lap_lenh", // id of the input field
                    ifFormat : "%d/%m/%Y", // the date format
                    button : "tu_ngay_lap_ltt_btn"// id of the button
                });
              </script>
            </td>
            <td align="right">
              <label for="den_ngay_lap_lenh">
                <fmt:message key="tra_cuu_dts_traloi.page.lable.den_ngay_lap_ltt"/>
              </label>
            </td>
            <td class="promptText" align="right">
              <html:text styleId="den_ngay_lap_lenh" property="den_ngay_lap_lenh"
                         onblur="javascript:mask(this.value,this,'2,5','/');validFormatDate(this);textlostfocus(this);"
                         onfocus="textfocus(this);"  onkeydown="nextFocus();"
                         styleClass="fieldText" style="width:138;"/>
               
              <img src="<%=AppConstants.NNT_APP_CONTEXT_ROOT%>/styles/js/calendar/calbtn.gif"
                   border="0" id="den_ngay_lap_ltt_btn" width="20"
                   style="vertical-align:middle;"/>
               
              <span>
                <fmt:message key="tra_cuu_dts_traloi.page.lable.format_date"/></span>
              <script type="text/javascript">
                    Calendar.setup( {
                        inputField : "den_ngay_lap_lenh", // id of the input field
                        ifFormat : "%d/%m/%Y", // the date format
                        button : "den_ngay_lap_ltt_btn"// id of the button
                    });
              </script>
            </td>
          </tr>
          <tr>
            <td colspan="8" align="center">
              <div style="padding:10px 0px 10px 0px; ">
                <button id="btn_timKiem" accesskey="i" type="button"
                        class="ButtonCommon"  value="search">
                  <fmt:message key="tra_cuu_dts_traloi.page.button.search"/>
                </button>
                  <%
           String url = request.getContextPath()+"/report/"+request.getSession().getAttribute(AppConstants.APP_USER_ID_SESSION)+"dtstraloi.xls";
           %>
              <!--<a href="<%=url%>" target="_blank">exp </a>--> 
                 <span id="in" style="padding-left:10px;">
                  <button id="btn_in" accesskey="i" type="button"
                        class="ButtonCommon"  value="search" onclick="startDownload('<%=url%>')">
                  <fmt:message key="tra_cuu_dts_traloi.page.button.in"/>
                </button>
                </span>
              </div>
            </td>
          </tr>
          </table>
          </td>
      </tbody>
    </table>
</html:form>
  <div style="padding:10px 0px 10px 0px;">
    <table border="2" align="center" width="100%" class="borderTableChungTu"
           >
      <thead class="TR_Selected">
        <tr>
          <td class="title" colspan="8">
            <span>
              <fmt:message key="tra_cuu_dts_traloi.page.ketqua"/></span>
          </td>
        </tr>
        <tr>
          <th width="11%"  class="th">
            <fmt:message key="tra_cuu_dts_traloi.page.lable.so_dien_tra_soat"/>
          </th>
          <th width="9%" class="th">
            <fmt:message key="tra_cuu_dts_traloi.page.lable.ngay_lap_dien"/>
          </th>
          <th width="14%" class="th">
            <fmt:message key="tra_cuu_dts_traloi.page.lable.so_lenh_thanh_toan"/>
          </th>
          <th width="9%" class="th">
            <fmt:message key="tra_cuu_dts_traloi.page.lable.ngay_lap_lenh"/>
          </th>
          <th class="th">
            <fmt:message key="tra_cuu_dts_traloi.page.lable.don_vi_tra_soat"/>
          </th>
          <th class="th">
            <fmt:message key="tra_cuu_dts_traloi.page.lable.don_vi_nhan_tra_soat"/>
          </th>
          <th width="9%" class="th">
            <fmt:message key="tra_cuu_dts_traloi.page.lable.trang_thai"/>
          </th>
          <!-- <th width="12%">
                 <fmt:message key="tra_cuu_dts_traloi.page.lable.loai_tra_soat"/>
                </th>-->
          <th width="5%" class="th">&nbsp;</th>
        </tr>
      </thead>
       
      <tbody class="navigateable focused" cellspacing="0" cellpadding="1"
             bordercolor="#e1e1e1">
       
        <logic:notEmpty name="lstDTS">
          <%
                com.seatech.framework.common.jsp.PagingBean pagingBean = (com.seatech.framework.common.jsp.PagingBean)request.getAttribute("PAGE_KEY");
                int rowBegin = (pagingBean.getCurrentPage() -1) * 15;
              %>
          <logic:iterate id="items" name="lstDTS" indexId="stt">
            <tr class='<%=stt % 2 == 0 ? "trDanhSachChan" : "trDanhSachLe"%>' height="20">
              <td align="center">
                <bean:write name="items" property="id"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ngay_nhan"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ltt_id"/>
              </td>
              <td align="center">
                <bean:write name="items" property="ngay_lap_lenh"/>
              </td>
              <td align="left">
                <bean:write name="items" property="ten_don_vi_nhan_tra_soat"/>
              </td>
              <td align="left">
                <bean:write name="items" property="ten_don_vi_tra_soat"/>
              </td>
              <td>
                <bean:write name="items" property="mo_ta_trang_thai"/>
              </td>
             <td align="center">
             <a href="javascript:makeGetRequest(<bean:write name="items" property="id"/>)">XemDTS</a>
              <!--<a href="dtsoattloiView.do?action=<%=AppConstants.ACTION_VIEW_DETAIL%>&id=<bean:write name="items" property="id"/>&ten_nsd= + javascript:document.getElementById('ttv_nhan').value()" >XemDTS</a>
             --> </td>
            </tr>
          </logic:iterate>
          <tr>
            <td colspan="8">
              <%= com.seatech.framework.common.jsp.JspUtil.pagingHTML(pagingBean, "#0000ff")%>
            </td>
          </tr>
        </logic:notEmpty>
      </tbody>
    </table>
  </div>
<div style="padding:10px 10px 10px 0px;float:right ">
  <button id="btn_thoat" type="button" accesskey="o" class="ButtonCommon"
          tabindex="23">
    <fmt:message key="tra_cuu_dts_traloi.page.button.exit"/>
  </button>
</div>
<!------------------------------------------ Message confirm ------------------------------->
<div id="dialog-confirm"
     title='<fmt:message key="tra_cuu_dts_traloi.page.title.dialog_confirm"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>
<!-- ---------------------------------Message --------------------------------->
<div id="dialog-message"
     title='<fmt:message key="tra_cuu_dts_traloi.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>