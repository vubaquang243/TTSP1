<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
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
<fmt:setBundle basename="com.seatech.ttsp.resource.TraCuuDTSToanQuocResource"/>
<title>
  <fmt:message key="TraCuuDTSToanQuocResource.page.title"/>
</title>
<script type="text/javascript">
  var target = '<%=(request.getParameter("targetid")!=null)?"?targetid="+request.getParameter("targetid"):""%>';
  jQuery.noConflict();  
  jQuery(document).ready(function () {
  
      if(jQuery("#tinh").val() == '0003'){
        try{
          jQuery("#huyen").val("0003");
        }catch(ex){}
      }
      
      var tu_ngay_field = jQuery("#tu_ngay"), 
      den_ngay_field = jQuery("#den_ngay"),
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
      frmTraCuuDTS = jQuery("#frmTraCuuDTS");
      allFields = jQuery([]).add(tu_ngay_field).add(ma_nh_field).add(ma_kb_tinh_field).
      add(den_ngay_field).add(trang_thai_field).add(ma_kb_huyen_field).add(so_tien_field)
      .add(tu_lenh_field).add(den_lenh_field).add(loai_lenh_field);
      ma_nh_field.focus();
      
    jQuery("#so_tien_temp").val((so_tien_field.val()!='' && so_tien_field.val()!='undefined' && so_tien_field.val()!='0' )?CurrencyFormatted(so_tien_field.val()):'' );
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
                  frmTraCuuDTS.attr( {
                      action : 'thoatView.do'
                  });
                  frmTraCuuDTS.submit();
              },
              "Không" : function () {
                  jQuery(this).dialog("close");
              }
          }
      });
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
            frmTraCuuDTS.attr( {
                action : 'TraCuuDTSToanQuocList.do' + target
            });
            frmTraCuuDTS.submit();
      });
      //**************************BUTTON Thoat CLICK********************************************
      jQuery("#btn_thoat").click(function () {
          frmTraCuuDTS.attr( {
                      action : 'thoatView.do'
                  });
                  frmTraCuuDTS.submit();
      });

  });
  function changeValue(value){
    jQuery("#so_tien_temp").val(CurrencyFormatted(value));
    jQuery("#so_tien").val(value,'VND');
    
  }
  
  function makeGetRequestView(id){
    var urlRequest  =null;
    var TypeDiDen = id.substring(2,5);
    var TypeLoaiDTS = id.substring(5,8);
    var ma_nh = jQuery("#ma_nh option:selected").val();
    var tinh = jQuery("#tinh option:selected").val();
    var huyen = jQuery("#huyen option:selected").val();
    var trang_thai = jQuery("#trang_thai option:selected").val();
    var loai_lenh = jQuery("#loai_lenh option:selected").val();
    var tu_ltt = jQuery("#tu_ltt").val();
    var den_ltt = jQuery("#den_ltt").val();
    var tu_ngay = jQuery("#tu_ngay").val();
    var den_ngay = jQuery("#den_ngay").val();
    var so_dts = jQuery("#so_dts").val();
    var so_tien_temp = jQuery("#so_tien_temp").val();
    if(TypeLoaiDTS=='195'){
        if(TypeDiDen!='701'){
          urlRequest = "xulydtshoitunhtmView.do?action=VIEW_DTS_T4&id="+id+"";
        }else{
          urlRequest = "dtsoatlttdiView.do?action=VIEW_DTS_T4&id="+id+"";
        }
    }else if(TypeLoaiDTS=='196'){
        if(TypeDiDen!='701'){
          urlRequest = "dtsoattloiView.do?action=VIEW_DTS_T4&id="+id+"";
        }else{
           urlRequest = "xulydtsoattraloiView.do?action=VIEW_DTS_T4&id="+id+"";
        }
    }else{
        if(TypeDiDen!='701'){
          urlRequest = "xulydtshoitunhtmView.do?action=VIEW_DTS_T4&id="+id+"";
        }else{
           urlRequest = "XuLyDTSoatTuDo.do?action=VIEW_DTS_T4&id="+id+"";
        }
    }
    if(ma_nh != null && ma_nh != ''){
      urlRequest += "&ma_nh="+ma_nh+"";
    }
    if(tinh != null && tinh != ''){
      urlRequest += "&tinh="+tinh+"";
    }    
    if(huyen != null && huyen != ''){
      urlRequest += "&huyen="+huyen+"";
    }
    if(trang_thai != null && trang_thai != ''){
      urlRequest += "&trang_thai="+trang_thai+"";
    }
    if(loai_lenh != null && loai_lenh != ''){
    urlRequest += "&loai_lenh="+loai_lenh+"";
    }
    if(tu_ltt != null && tu_ltt != ''){
      urlRequest += "&tu_ltt="+tu_ltt+"";
    }
    if(den_ltt != null && den_ltt != ''){
      urlRequest += "&den_ltt="+den_ltt+"";
    }
    if(tu_ngay != null && tu_ngay != ''){
      urlRequest += "&tu_ngay="+tu_ngay+"";
    }
    if(den_ngay != null && den_ngay != ''){
      urlRequest += "&den_ngay="+den_ngay+"";
    }
    if(so_dts != null && so_dts != ''){
      urlRequest += "&so_dts="+so_dts+"";
    }
    if(so_tien_temp!=null && so_tien_temp!= ''){
      urlRequest += "&so_tien_temp="+so_tien_temp+"";
    }
    window.location = urlRequest;
  }
  //**************************LINK TRANG CLICK********************************************
  function goPage(page) {
      jQuery("#frmTraCuuDTS").attr( {
          action : 'TraCuuDTSToanQuocList.do' + target
      });
      jQuery("#pageNumber").val(page);
      jQuery("#frmTraCuuDTS").submit();
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
    function genKBHuyen(){
      var f = document.forms[0];
      f.action = "TraCuuDTSToanQuocView.do";
      f.submit();
    }
  function checkTTVNhan() {
      if (jQuery("#ttv_nhan").val().length == 100) {
          alert('Tên thanh toán viên không được lớn hơn 100 kí tự');
      }
  }
</script>

<html:form action="/TraCuuDTSToanQuoc.do" styleId="frmTraCuuDTS">
  <input type="hidden" id="fieldNameForcus"/>
  <div class="app_error">
  <html:errors/>
</div>
  <table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
        <tbody>
          <tr>
            <td width="13"><img width="13" height="30" src="<%=request.getContextPath()%>/styles/images/T1.jpg"></img></td>
            <td width="75%" background="<%=request.getContextPath()%>/styles/images/T2.jpg"><span class="title2"><fmt:message key="TraCuuDTSToanQuocResource.page.title"/></span></td>
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
          <fmt:message key="TraCuuDTSToanQuocResource.page.dieukien"/>
        </td>
      </tr>
   </tbody>    
  </table>
  <table  class="tableBound">
    <tbody>
      <tr>
        <td align="right" width="100">
          <label for="nh">
            <fmt:message key="TraCuuDTSToanQuocResource.page.label.nh"/>
          </label>
        </td>
        <td class="promptText" align="right" width="100">
          <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                      tabindex="102"
                       styleClass="fieldText" style="width:145px;height:20px;"
                       property="ma_nh" styleId="ma_nh" >
            <html:option value="">
              <fmt:message key="TraCuuDTSToanQuocResource.page.lable.chon_nh"/>
            </html:option>
            <html:optionsCollection name="lstNganHang" value="ma_nh" label="ten"/>
          </html:select>
        </td>
        <td align="right" width="100">
          <label for="tu_lenh">
            <fmt:message key="TraCuuDTSToanQuocResource.page.label.tuso"/>
          </label>
        </td>
        <td class="promptText" width="100" align="right" valign="middle">
          <html:text property="tu_ltt" styleId="tu_ltt"
                     styleClass="fieldText" 
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="103"
                     style="WIDTH: 97%;"/>
        </td>
        <td align="right" width="100">
          <label for="so_tien">
            <fmt:message key="TraCuuDTSToanQuocResource.page.label.sotien"/>
          </label>
        </td>
        <td class="promptText" align="right" valign="middle" width="100">
          <html:hidden  property="so_tien" styleId="so_tien"/> 
         <input type="text"  id="so_tien_temp"
             class="fieldTextRight"
             onkeydown="if(event.keyCode==13) event.keyCode=9;" onblur="if (this.value !='') {changeValue(this.value);}"
             tabindex="104" onkeypress="return numbersonly(this,event,true)"
             style="WIDTH: 95%;"/> 
        </td>
      </tr>
      <tr>
        <td align="right" width="100">
          <label for="tinh">
            <fmt:message key="TraCuuDTSToanQuocResource.page.label.tinh"/>
          </label>
        </td>
        <td class="promptText">
          <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                      tabindex="105" onchange="genKBHuyen();"
                       styleClass="fieldText" style="width:145px;height:20px;"
                       property="tinh" styleId="tinh" >
            <html:option value="">
              <fmt:message key="TraCuuDTSToanQuocResource.page.lable.chon_kbtinh"/>
            </html:option>
            <html:optionsCollection name="lstKBTinh" value="ma" label="ten"/>
          </html:select>
        </td>
        <td align="right" width="100">
          <label for="den_so">
            <fmt:message key="TraCuuDTSToanQuocResource.page.label.denso"/>
          </label>
        </td>
        <td class="promptText">
          <html:text property="den_ltt" styleId="den_ltt"
                     styleClass="fieldText"                      
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"                     
                     tabindex="106"                     
                     style="WIDTH: 97%;"/>
        </td>
        <td align="right" width="100">
          <label for="loai_lenh">
            <fmt:message key="TraCuuDTSToanQuocResource.page.label.loailenh"/>
          </label>
        </td>
        <td class="promptText">
          <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                       styleClass="fieldText" style="width:97%;height:20px;"
                       tabindex="107"
                       property="loai_lenh" styleId="loai_lenh">
            <html:option value="">
              <fmt:message key="TraCuuDTSToanQuocResource.page.lable.loai_lenh"/>
            </html:option>
            <html:option value="01">
              <fmt:message key="TraCuuDTSToanQuocResource.page.lable.195"/>
            </html:option>
            <html:option value="02">
              <fmt:message key="TraCuuDTSToanQuocResource.page.lable.196"/>
            </html:option>
            <html:option value="03">
              <fmt:message key="TraCuuDTSToanQuocResource.page.lable.199"/>
            </html:option>
          </html:select>
        </td>
      </tr>
      <tr>
        <td align="right" width="100">
          <label for="huyen">
            <fmt:message key="TraCuuDTSToanQuocResource.page.label.huyen"/>
          </label>
        </td>
        <td class="promptText">
          <logic:present name="lstKBHuyen">
            <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                        tabindex="108"
                         styleClass="fieldText" style="width:145px;height:20px;"
                         property="huyen" styleId="huyen" >
              <html:option value="">
                <fmt:message key="TraCuuDTSToanQuocResource.page.lable.chon_kbhuyen"/>
              </html:option>
              <html:optionsCollection name="lstKBHuyen" value="ma" label="ten"/>
            </html:select>
          </logic:present>
          <logic:notPresent name="lstKBHuyen">
            <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                        tabindex="108"
                         styleClass="fieldText" style="width:145px;height:20px;"
                         property="huyen" styleId="huyen" >
              <html:option value="">
                <fmt:message key="TraCuuDTSToanQuocResource.page.lable.chon_kbhuyen"/>
              </html:option>
            </html:select>
          </logic:notPresent>
        </td>
        <td align="right" width="100">
          <label for="tungay">
            <fmt:message key="TraCuuDTSToanQuocResource.page.label.tungay"/>
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
            Calendar.setup( {
                inputField : "tu_ngay", // id of the input field
                ifFormat : "%d/%m/%Y", // the date format
                button : "tu_ngay_btn"// id of the button
            });
          </script>
        </td>
        <td align="right" width="100">
          <label for="denngay">
            <fmt:message key="TraCuuDTSToanQuocResource.page.label.denngay"/>
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
        <td align="right" width="100">
          <label for="trang_thai">
            <fmt:message key="TraCuuDTSToanQuocResource.page.label.trangthai"/>
          </label>
        </td>
        <td class="promptText" align="left" valign="middle">
          <html:select onkeydown="if(event.keyCode==13) event.keyCode=9;"
                      tabindex="111"
                       styleClass="fieldText" style="width:145px;height:20px;"
                       property="trang_thai" styleId="trang_thai" >
            <html:option value="">
              <fmt:message key="TraCuuDTSToanQuocResource.page.lable.trangthai"/>
            </html:option>
            <html:optionsCollection name="lstTrangThai" value="rv_low_value" label="rv_meaning"/>
          </html:select>
        </td>
        <td align="right" width="100">
          <label for="so_Dts">
            <fmt:message key="TraCuuDTSToanQuocResource.page.label.sodts"/>
          </label>
        </td>
        <td class="promptText" align="left" valign="middle" >
          <html:text property="so_dts" styleId="so_dts" styleClass="fieldText"
                     onkeydown="if(event.keyCode==13) event.keyCode=9;"
                     tabindex="112"
                     style="WIDTH: 97%;"/>
        </td>
        <td align="right" width="100">
         
        </td>
        <td class="promptText" align="left" valign="middle" >
         
        </td>
      </tr>
    </tbody>    
  </table>
  <table class="buttonbar" align="center">
            <tr>
              <td>
                <span id="tracuu">
            <button  id="btn_timKiem" 
                      tabindex="111"
                      type="button"
                    class="ButtonCommon" >
              <fmt:message key="TraCuuDTSToanQuocResource.page.button.tracuu"/>
            </button>
            </span>
            <span>
            <button id="btn_in"  type="button"
                        class="ButtonCommon"  value="print" >
                  <fmt:message key="TraCuuDTSToanQuocResource.page.button.in"/>
            </button>
            </span>
            <span>
               <button id="btn_thoat" type="button" class="ButtonCommon"
                        tabindex="23">
                  <fmt:message key="TraCuuDTSToanQuocResource.page.button.thoat"/>
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
            <fmt:message key="TraCuuDTSToanQuocResource.page.kq.stt"/>
          </th>
          <th width="13%">
            <fmt:message key="TraCuuDTSToanQuocResource.page.kq.kb"/>
          </th>
          <th width="13%">
            <fmt:message key="TraCuuDTSToanQuocResource.page.kq.kbhuyen"/>
          </th>
          <th width="13%">
            <fmt:message key="TraCuuDTSToanQuocResource.page.kq.nh"/>
          </th>
          <th width="13%">
            <fmt:message key="TraCuuDTSToanQuocResource.page.kq.sodts"/>
          </th>
          <th width="10%">
            <fmt:message key="TraCuuDTSToanQuocResource.page.kq.ngaylap"/>
          </th>
          <th width="14%">
            <fmt:message key="TraCuuDTSToanQuocResource.page.kq.sotien"/>
          </th>
          <th width="8%">
            <fmt:message key="TraCuuDTSToanQuocResource.page.kq.loailenh"/>
          </th>
          <th width="14%">
            <fmt:message key="TraCuuDTSToanQuocResource.page.kq.trangthai"/>
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
                        <%=stt+1%>
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
                      <td align="left">
                        <a href="javascript:makeGetRequestView('<bean:write name="items" property="id"/>')">
                        <bean:write name="items" property="id"/>
                        </a>
                      </td>
                      <td align="center">
                        <bean:write name="items" property="ngay_nhan"/>
                      </td>
                      <td align="right">
                        <bean:write name="items" property="tong_sotien"
                                    format=",000"/>
                      </td>
                      <td align="left">
                        <bean:write name="items" property="loai_dien"/>
                      </td>
                      <td align="left">
                        <bean:write name="items" property="mo_ta_trang_thai"/>
                      </td>
                    </tr>
                  </logic:iterate>
                  <tr>
                    <td colspan="9">
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
     title='<fmt:message key="TraCuuDTSToanQuocResource.page.title.dialog_confirm"/>'>
  <p>
    <span class="ui-icon ui-icon-alert"
          style="float:left; margin:0 7px 20px 0;"></span>
     
    <span id="message_confirm"></span>
  </p>
</div>
<!-- ---------------------------------Message --------------------------------->
<div id="dialog-message"
     title='<fmt:message key="TraCuuDTSToanQuocResource.page.title.dialog_message"/>'>
  <p>
    <span class="ui-icon ui-icon-circle-check"
          style="float:left; margin:0 7px 50px 0;"></span>
     
    <span id="message"></span>
  </p>
</div>
<%@ include file="/includes/ttsp_bottom.inc"%>